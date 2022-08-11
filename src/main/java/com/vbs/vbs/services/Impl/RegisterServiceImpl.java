package com.vbs.vbs.services.Impl;

import com.vbs.vbs.dto.ClientDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.enums.ApplicationUserRole;
import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.models.Admin;
import com.vbs.vbs.models.Client;
import com.vbs.vbs.models.Venue;
import com.vbs.vbs.repo.AdminRepo;
import com.vbs.vbs.repo.ClientRepo;
import com.vbs.vbs.repo.VenueRepo;
import com.vbs.vbs.security.user.User;
import com.vbs.vbs.security.user.UserRepo;
import com.vbs.vbs.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegisterServiceImpl implements RegisterService {
    private final AdminRepo adminRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final ClientRepo clientRepo;
    @Autowired
    private final VenueRepo venueRepo;
    @Autowired
    private final UserRepo userRepo;

    public RegisterServiceImpl(AdminRepo adminRepo, PasswordEncoder passwordEncoder,
                               ClientRepo clientRepo, VenueRepo venueRepo, UserRepo userRepo) {
        this.adminRepo = adminRepo;
        this.passwordEncoder = passwordEncoder;
        this.clientRepo = clientRepo;
        this.venueRepo = venueRepo;
        this.userRepo = userRepo;

    }

    @Override
    public ClientDto clientRegister(ClientDto clientDto) {
        Client entity= Client.builder()
                .name(clientDto.getName())
                .mobile_no(clientDto.getMobile_no())
                .email(clientDto.getEmail())
                .city_name(clientDto.getCity_name())
                .street_name(clientDto.getStreet_name())
                .password(passwordEncoder.encode(clientDto.getPassword()))
                .applicationUserRole(ApplicationUserRole.CLIENT)
                .build();
        User entity1= User.builder()
                .email(clientDto.getEmail())
                .uname(clientDto.getName())
                .password(passwordEncoder.encode(clientDto.getPassword()))
                .applicationUserRole(ApplicationUserRole.CLIENT).build();
        userRepo.save(entity1);
        entity= clientRepo.save(entity);
        return ClientDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .mobile_no(entity.getMobile_no())
                .email(entity.getEmail())
                .city_name(entity.getCity_name())
                .street_name(entity.getStreet_name())
                .build();
    }

    @Override
    public VenueDto venueRegister(VenueDto venueDto) throws IOException {
//        MultipartFile multipartFile = venueDto.getVenueFile();
//        //need to save this file
//        String filepath=fileStorageUtils.storeFile(multipartFile);

        Venue entity = new Venue().builder()
                .id(venueDto.getId())
                .venueName(venueDto.getVenueName())
                .password(passwordEncoder.encode(venueDto.getPassword()))
                .contactNumber(venueDto.getContactNumber())
                .email(venueDto.getEmail())
                .description(venueDto.getDescription())
                .userName(venueDto.getUserName())
                .applicationUserRole(ApplicationUserRole.VENUE)
                .venueStatus(VenueStatus.PENDING)
//                .filePath(filepath)
                .build();
        entity = venueRepo.save(entity);
        return VenueDto.builder()
                .id(entity.getId())
                .venueName(entity.getVenueName())
                .email(entity.getEmail())
                .userName(entity.getUserName())
                .applicationUserRole(entity.getApplicationUserRole())
                .venueStatus(entity.getVenueStatus())
                .filePath(entity.getFilePath())
                .build();
    }

    public List<VenueDto> getAllPendingRegister() {
        List<VenueDto> venueList= venueRepo.findPendingRegister(VenueStatus.PENDING);
        return venueList.stream().map(entity-> VenueDto.builder()
                .id(entity.getId())
                .venueName(entity.getVenueName())
                .contactNumber(entity.getContactNumber())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Venue updateVenueStatus(Integer status,String email) {
        if (status == 0) {
           Optional<Venue> venue = venueRepo.findVenueByEmail(email);
            if (venue.isPresent()) {
                Venue venue1 = venue.get();
                User user = new User();
                user.setEmail(venue1.getEmail());
                user.setUname(venue1.getVenueName());
                user.setPassword(venue1.getPassword());
                user.setApplicationUserRole(venue1.getApplicationUserRole());
                userRepo.save(user);
                return venueRepo.updateVenueStatus(VenueStatus.VERIFY, email);
            }
        }
        return venueRepo.updateVenueStatus(VenueStatus.DELETED, email);
    }

//    @Override
//    public Admin registerAdmin() {
//        Admin entity= Admin.builder()
//                .name("admin001")
//                .email("svenuebooking.spad001@gmail.com")
//                .password(passwordEncoder.encode("Zxcvbnm@1234"))
//                .applicationUserRole(ApplicationUserRole.ADMIN)
//                .build();
//        User entity1= User.builder()
//                .email("svenuebooking.spad001@gmail.com")
//                .uname("admin001")
//                .password(passwordEncoder.encode("Zxcvbnm@1234"))
//                .applicationUserRole(ApplicationUserRole.CLIENT).build();
//        userRepo.save(entity1);
//        adminRepo.save(entity);
//        return Admin.builder()
//                .email(entity.getEmail())
//                .build();
//    }
}
