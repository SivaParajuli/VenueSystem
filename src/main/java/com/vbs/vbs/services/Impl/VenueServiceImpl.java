package com.vbs.vbs.services.Impl;

import com.vbs.vbs.dto.EventDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.enums.ApplicationUserRole;
import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.enums.EventType;
import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.models.EventsCostAndRate;
import com.vbs.vbs.models.Venue;
import com.vbs.vbs.repo.FunctionRepo;
import com.vbs.vbs.repo.VenueRepo;
import com.vbs.vbs.security.user.UserRepo;
import com.vbs.vbs.services.VenueService;
import com.vbs.vbs.utils.FileStorageUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VenueServiceImpl  implements VenueService {

    private final VenueRepo venueRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final FunctionRepo functionRepo;
    private final FileStorageUtils fileStorageUtils;


    public VenueServiceImpl(VenueRepo venueRepo, UserRepo userRepo, PasswordEncoder passwordEncoder, FunctionRepo functionRepo, FileStorageUtils fileStorageUtils) {
        this.venueRepo = venueRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.functionRepo = functionRepo;
        this.fileStorageUtils = fileStorageUtils;
    }

    @Override
    public List<VenueDto> findAll() {
        List<Venue> venueList = venueRepo.findAll();
        return venueList.stream().map(entity -> VenueDto.builder()
                .venueName(entity.getVenueName())
                .userName(entity.getUserName())
                .capacity(entity.getCapacity())
                .contactNumber(entity.getContactNumber())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .build()).collect(Collectors.toList());
    }

    @Override
    public VenueDto findVenueByEmail(String email) throws UsernameNotFoundException {
        Optional<Venue> venue = venueRepo.findVenueByEmail(email);
        if (venue.isPresent()) {
            Venue venue1 = venue.get();
            return VenueDto.builder()
                    .id(venue1.getId())
                    .userName(venue1.getUserName())
                    .description(venue1.getDescription())
                    .venueName(venue1.getVenueName())
                    .capacity(venue1.getCapacity())
                    .email(venue1.getEmail())
                    .contactNumber(venue1.getContactNumber())
                    .address(venue1.getAddress())
                    .filePath(venue1.getImage())
                    .functionList(venue1.getFunctionList())
                    .build();
        }
        return null;
    }

    @Override
    public VenueDto findById(Integer id) {
        Optional<Venue> venue = venueRepo.findById(id);
        if (venue.isPresent()) {
            Venue venue1 = venue.get();
            return VenueDto.builder()
                    .venueName(venue1.getVenueName())
                    .address(venue1.getAddress())
                    .capacity(venue1.getCapacity())
                    .contactNumber(venue1.getContactNumber())
                    .email(venue1.getEmail())
                    .userName(venue1.getUserName())
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
        List<Booking> requestList = venueRepo.getAllPendingBookingRequest(email, BookingStatus.PENDING);
        return requestList.stream().map(entity -> Booking.builder()
                .id(entity.getId())
                .bookingDate(entity.getBookingDate())
                .client(entity.getClient())
                .contactNumber(entity.getContactNumber())
                .eventType(entity.getEventType())
                .calculatedPayment(entity.getCalculatedPayment())
                .requiredCapacity(entity.getRequiredCapacity())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Integer update(VenueDto venueDto, String email) {
        Integer venue = venueRepo.update(
                venueDto.getVenueName(),
                venueDto.getUserName(),
                venueDto.getAddress(),
                venueDto.getContactNumber(),
                passwordEncoder.encode(venueDto.getPassword()),
                venueDto.getDescription(),
                email);
        if (venue != null) {
            Integer user = userRepo.update(
                    venueDto.getUserName(),
                    passwordEncoder.encode(venueDto.getPassword()),
                    email);
        }
        return venue;
    }

    public List<VenueDto> getAllVerifiedVenue() {
        List<Venue> venueList = venueRepo.findAllVerifiedVenue(VenueStatus.VERIFY);
        return venueList.stream().map(entity -> VenueDto.builder()
                .id(entity.getId())
                .venueName(entity.getVenueName())
                .contactNumber(entity.getContactNumber())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .capacity(entity.getCapacity())
                .userName(entity.getUserName())
                .description(entity.getDescription())
                .venueStatus(entity.getVenueStatus())
                .filePath(entity.getImage())
                .functionList(entity.getFunctionList())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<Booking> getBookingList(String email) {
        List<Booking> requestList = venueRepo.getAllBookingList(email);
        return requestList.stream().map(entity -> Booking.builder()
                .id(entity.getId())
                .bookingDate(entity.getBookingDate())
                .client(entity.getClient())
                .contactNumber(entity.getContactNumber())
                .bookingStatus(entity.getBookingStatus())
                .eventType(entity.getEventType())
                .calculatedPayment(entity.getCalculatedPayment())
                .requiredCapacity(entity.getRequiredCapacity())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<?> getAllBookedDate(String email) {
        List<?> dateList = venueRepo.getBookedVenueDateById(email, BookingStatus.CANCELED);
        return new ArrayList<>(dateList);
    }

    @Override
    public EventsCostAndRate uploadEventDetails(EventDto eventDto, String email) {
        Venue venue = venueRepo.findVenueByEmail(email).orElseThrow(() -> new RuntimeException("venueNotFound"));
        EventsCostAndRate entity = EventsCostAndRate.builder()
                .marriageCost(Double.parseDouble(eventDto.getMarriage()))
                .annualMeetCost(Double.parseDouble(eventDto.getAnnualMeet()))
                .collegeEventCost(Double.parseDouble(eventDto.getCollegeEvent()))
                .conclaveCost(Double.parseDouble(eventDto.getConclave()))
                .familyFunctionCost(Double.parseDouble(eventDto.getFamilyParty()))
                .rate(Double.parseDouble(eventDto.getRate()))
                .venue1(venue)
                .build();
        entity = functionRepo.save(entity);
        return EventsCostAndRate.builder()
                .familyFunctionCost(entity.getFamilyFunctionCost())
                .id(entity.getId())
                .collegeEventCost(entity.getCollegeEventCost())
                .marriageCost(entity.getMarriageCost())
                .annualMeetCost(entity.getAnnualMeetCost())
                .build();
    }
}

