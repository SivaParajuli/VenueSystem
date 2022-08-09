package com.vbs.vbs.services.Impl;

import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.models.Venue;
import com.vbs.vbs.repo.VenueRepo;
import com.vbs.vbs.services.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VenueServiceImpl  implements VenueService {

    private final VenueRepo venueRepo;

    @Autowired
    public VenueServiceImpl(VenueRepo venueRepo) {
        this.venueRepo = venueRepo;
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
    public Optional<Venue> findVenueByEmail(String email) throws UsernameNotFoundException {
        Optional<Venue> venue= venueRepo.findVenueByEmail(email);
        if(venue.isPresent()){
           Venue entity = venue.get();
            return Optional.ofNullable(Venue.builder()
                    .id(entity.getId())
                    .userName(entity.getUserName())
                    .venueName(entity.getVenueName())
                    .email(entity.getEmail())
                    .contactNumber(entity.getContactNumber())
                    .address(entity.getAddress())
                    .build());
        }
        return Optional.empty();
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
    public List<Booking> getRequestedBooking(String email) {
        List<Booking> requestList= venueRepo.getAllPendingBookingRequest(email, BookingStatus.PENDING);
        return requestList.stream().map(entity-> Booking.builder()
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

    public List<Venue> getAllVerifiedVenue() {
        List<Venue> venueList= venueRepo.findAllVerifiedVenue(VenueStatus.VERIFY);
        return venueList.stream().map(entity-> Venue.builder()
                .id(entity.getId())
                .venueName(entity.getVenueName())
                .contactNumber(entity.getContactNumber())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .userName(entity.getUserName())
                .description(entity.getDescription())
                .build()).collect(Collectors.toList());
    }
}

