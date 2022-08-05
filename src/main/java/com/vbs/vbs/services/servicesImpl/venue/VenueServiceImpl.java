package com.vbs.vbs.services.servicesImpl.venue;

import com.vbs.vbs.dto.venue.VenueDto;
import com.vbs.vbs.models.User;
import com.vbs.vbs.models.venue.BookingRequest;
import com.vbs.vbs.models.venue.Venue;
import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.repo.UserRepo;
import com.vbs.vbs.repo.venue.VenueBookingRequestRepo;
import com.vbs.vbs.repo.venue.VenueRepo;
import com.vbs.vbs.security.ApplicationUserRole;
import com.vbs.vbs.services.venue.VenueService;
import com.vbs.vbs.utils.FileStorageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VenueServiceImpl  implements VenueService {

    private final VenueRepo venueRepo;
    private final VenueBookingRequestRepo venueBookingRequestRepo;
    private final FileStorageUtils fileStorageUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;


    @Autowired
    public VenueServiceImpl(VenueRepo venueRepo, VenueBookingRequestRepo venueBookingRequestRepo,
                            FileStorageUtils fileStorageUtils, PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.venueRepo = venueRepo;
        this.venueBookingRequestRepo = venueBookingRequestRepo;
        this.fileStorageUtils = fileStorageUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }


    @Override
    public VenueDto create(VenueDto venueDto) throws IOException {
        MultipartFile multipartFile=venueDto.getVenueFile();
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
        entity.setApplicationUserRole(ApplicationUserRole.OWNER);
        entity.setFilePath(filepath);
        if(venueDto.getVenueStatus()==null) {
            entity.setVenueStatus(VenueStatus.PENDING);
        }
        else {
            entity.setVenueStatus(venueDto.getVenueStatus());
            User user = new User();
            user.setEmail(venueDto.getEmail());
            user.setPassword(passwordEncoder.encode(venueDto.getPassword()));
            user.setApplicationUserRole(ApplicationUserRole.OWNER);
            userRepo.save(user);
        }
        entity = venueRepo.save(entity);

        return VenueDto.builder()
                .id(entity.getId())
                .venueName(entity.getVenueName())
                .email(entity.getEmail())
                .build();
    }

    @Override
    public List<VenueDto> findAll() {
        List<Venue> venueList = venueRepo.findAll();
        return venueList.stream().map(entity-> VenueDto.builder()
                .venueName(entity.getVenueName())
                .userName(entity.getUserName())
                .contactNumber(entity.getContactNumber())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .build()).collect(Collectors.toList());
    }

    @Override
    public VenueDto findVenueByEmail(String email) {
        Optional<Venue> venue= venueRepo.findVenueByEmail(email);
        if(venue.isPresent()){
           Venue entity = venue.get();
            return VenueDto.builder()
                    .id(entity.getId())
                    .userName(entity.getUserName())
                    .venueName(entity.getVenueName())
                    .email(entity.getEmail())
                    .contactNumber(entity.getContactNumber())
                    .address(entity.getAddress())
                    .build();
        }
        return null;
    }



    @Override
    public List<VenueDto> findInAdminPage() {
        List<Venue> venueList = venueRepo.findInAdminPage();
        return venueList.stream().map(entity->VenueDto.builder()
                .venueName(entity.getVenueName())
                .contactNumber(entity.getContactNumber())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .venueStatus(entity.getVenueStatus())
                .build()).collect(Collectors.toList());

    }

    @Override
    public VenueDto findById(Integer id) {
        return null;
    }

    @Override
    public void deleteBYId(Integer integer) {
        venueRepo.deleteById(integer);
    }

    @Override
    public List<BookingRequest> getRequestedBooking(String email) {
        List<BookingRequest> requestList= venueRepo.getAllPendingBookingRequest(email);
        return requestList.stream().map(entity->BookingRequest.builder()
                .id(entity.getId())
                .bookingDate(entity.getBookingDate())
                .client(entity.getClient())
                .contactNumber(entity.getContactNumber())
                .functionType(entity.getFunctionType())
                .offeredPayment(entity.getOfferedPayment())
                .requiredCapacity(entity.getRequiredCapacity())
                .build()).collect(Collectors.toList());
    }

    //TODO
    @Override
    public VenueDto update(Integer id ,VenueDto venueDto) {
        Venue entity =venueRepo.findById(id).orElseThrow(()->new RuntimeException("Invalid id"));
        if(venueDto.getVenueName()==null)
            entity.setVenueName(entity.getVenueName());
        else
            entity.setVenueName(venueDto.getVenueName());
        if(venueDto.getUserName()==null)
            entity.setUserName(entity.getUserName());
        else
            entity.setUserName(venueDto.getUserName());
        if(venueDto.getAddress()==null)
            entity.setAddress(entity.getAddress());
        else
            entity.setAddress(venueDto.getAddress());
        if(venueDto.getEmail()==null)
            entity.setEmail(entity.getEmail());
        else
            entity.setEmail(venueDto.getEmail());
        if(venueDto.getContactNumber()==null)
            entity.setContactNumber(entity.getContactNumber());
        else
            entity.setContactNumber(venueDto.getContactNumber());
        entity = venueRepo.save(entity);
        return VenueDto.builder()
                .id(entity.getId())
                .venueName(entity.getVenueName())
                .email(entity.getEmail())
                .contactNumber(entity.getContactNumber())
                .address(venueDto.getAddress())
                .build();
    }

    //    @Override
//    public List<VenueDto> findInMainPage() {
//        List<Venue> venueList = venueRepo.findInMainPage();
//        return venueList.stream().map(entity-> VenueDto.builder()
//                .id(entity.getId())
//                .v_name(entity.getV_name())
//                .capacity(entity.getCapacity())
//                .city_name(entity.getCity_name())
//                .street_name(entity.getStreet_name())
//                .build()).collect(Collectors.toList());
//    }

}

