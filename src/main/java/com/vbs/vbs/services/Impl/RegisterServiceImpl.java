package com.vbs.vbs.services.Impl;

import com.vbs.vbs.dto.ClientDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.enums.ApplicationUserRole;
import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.models.Client;
import com.vbs.vbs.models.Venue;
import com.vbs.vbs.repo.ClientRepo;
import com.vbs.vbs.repo.VenueRepo;
import com.vbs.vbs.security.user.User;
import com.vbs.vbs.security.user.UserRepo;
import com.vbs.vbs.services.RegisterService;
import com.vbs.vbs.utils.FileStorageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private final FileStorageUtils fileStorageUtils;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final ClientRepo clientRepo;
    @Autowired
    private final VenueRepo venueRepo;
    @Autowired
    private final UserRepo userRepo;

    public RegisterServiceImpl(FileStorageUtils fileStorageUtils, PasswordEncoder passwordEncoder,
                               ClientRepo clientRepo, VenueRepo venueRepo, UserRepo userRepo) {
        this.fileStorageUtils = fileStorageUtils;
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
        MultipartFile multipartFile = venueDto.getVenueFile();
        if(multipartFile.isEmpty()){
            throw new InvalidPropertiesFormatException("invalid type");
        }
        //need to save this file
        String filepath=fileStorageUtils.storeFile(multipartFile);

        Venue entity=new Venue();
        entity.setVenueName(venueDto.getVenueName());
        entity.setPassword(passwordEncoder.encode(venueDto.getPassword()));
        entity.setUserName(venueDto.getUserName());
        entity.setEmail(venueDto.getEmail());
        entity.setAddress(venueDto.getAddress());
        entity.setContactNumber(venueDto.getContactNumber());
        entity.setDescription(venueDto.getDescription());
        entity.setApplicationUserRole(ApplicationUserRole.VENUE);
        entity.setFilePath(filepath);
        entity.setVenueStatus(VenueStatus.PENDING);
        entity = venueRepo.save(entity);
        return VenueDto.builder()
                .id(entity.getId())
                .venueName(entity.getVenueName())
                .email(entity.getEmail())
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
}
