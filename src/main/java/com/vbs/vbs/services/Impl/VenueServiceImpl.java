package com.vbs.vbs.services.Impl;

import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.models.Venue;
import com.vbs.vbs.repo.BookingRepo;
import com.vbs.vbs.repo.ClientRepo;
import com.vbs.vbs.repo.VenueRepo;
import com.vbs.vbs.services.VenueService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VenueServiceImpl  implements VenueService {

    private final VenueRepo venueRepo;
    private final BookingRepo bookingRepo;
    private final ClientRepo clientRepo;


    public VenueServiceImpl(VenueRepo venueRepo, BookingRepo bookingRepo, ClientRepo clientRepo) {
        this.venueRepo = venueRepo;
        this.bookingRepo = bookingRepo;
        this.clientRepo = clientRepo;
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
//                .filePath(fileStorageUtils.getBase64FileFromFilePath(entity.getFilePath()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public VenueDto findVenueByEmail(String email) throws UsernameNotFoundException {
        Optional<Venue> venue= venueRepo.findVenueByEmail(email);
        if(venue.isPresent()){
            Venue venue1 = venue.get();
            return VenueDto.builder()
                    .id(venue1.getId())
                    .userName(venue1.getUserName())
                    .venueName(venue1.getVenueName())
                    .email(venue1.getEmail())
                    .contactNumber(venue1.getContactNumber())
                    .address(venue1.getAddress())
//                    .filePath(fileStorageUtils.getBase64FileFromFilePath(venue1.getFilePath()))
                    .build();
        }
        return null;
    }

    @Override
    public VenueDto findById(Integer id) {
        Optional<Venue> venue =venueRepo.findById(id);
        if(venue.isPresent()){
            Venue venue1 = venue.get();
            return VenueDto.builder()
                    .venueName(venue1.getVenueName())
                    .address(venue1.getAddress())
                    .contactNumber(venue1.getContactNumber())
                    .email(venue1.getEmail())
                    .userName(venue1.getUserName())
//                    .filePath(fileStorageUtils.getBase64FileFromFilePath(venue1.getFilePath()))
                    .build();
        }
        return null;
    }

    @Override
    public void deleteBYId(Integer integer) {
        venueRepo.deleteById(integer);
    }

    @Override
    public List<Booking> getRequestedBooking(String email) {
        Optional<Venue> venue1 = venueRepo.findVenueByEmail(email);
        if (venue1.isPresent()) {
            List<Booking> requestList = bookingRepo.getAllPendingBookingRequest(venue1.get().getId(), BookingStatus.PENDING);
            return requestList.stream().map(entity -> Booking.builder()
                    .id(entity.getId())
                    .bookingDate(entity.getBookingDate())
                    .client(entity.getClient())
                    .contactNumber(entity.getContactNumber())
                    .functionType(entity.getFunctionType())
                    .calculatedPayment(entity.getCalculatedPayment())
                    .requiredCapacity(entity.getRequiredCapacity())
                    .build()).collect(Collectors.toList());
        }
        return null;
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

    public List<VenueDto> getAllVerifiedVenue() {
        List<Venue> venueList= venueRepo.findAllVerifiedVenue(VenueStatus.VERIFY);
        return venueList.stream().map(entity-> VenueDto.builder()
                .id(entity.getId())
                .venueName(entity.getVenueName())
                .contactNumber(entity.getContactNumber())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .userName(entity.getUserName())
                .description(entity.getDescription())
                .venueStatus(entity.getVenueStatus())
//                .filePath(fileStorageUtils.getBase64FileFromFilePath(entity.getFilePath()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<?> getAllBookedDate(Integer id) {
        List<?> dateList = venueRepo.getBookedVenueDateById(id, BookingStatus.UNSUCCESSFUL);
        return new ArrayList<>(dateList);
    }
}

