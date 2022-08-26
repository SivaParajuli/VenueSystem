package com.vbs.vbs.services.Impl;

import com.vbs.vbs.dto.EventDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.enums.EventType;
import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.models.FunctionType;
import com.vbs.vbs.models.Venue;
import com.vbs.vbs.repo.FunctionRepo;
import com.vbs.vbs.repo.VenueRepo;
import com.vbs.vbs.security.user.UserRepo;
import com.vbs.vbs.services.VenueService;
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


    public VenueServiceImpl(VenueRepo venueRepo, UserRepo userRepo, PasswordEncoder passwordEncoder, FunctionRepo functionRepo) {
        this.venueRepo = venueRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.functionRepo = functionRepo;
    }

    @Override
    public List<VenueDto> findAll() {
        List<Venue> venueList = venueRepo.findAll();
        return venueList.stream().map(entity-> VenueDto.builder()
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
        Optional<Venue> venue= venueRepo.findVenueByEmail(email);
        if(venue.isPresent()){
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
        Optional<Venue> venue =venueRepo.findById(id);
        if(venue.isPresent()){
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
    public Integer update(VenueDto venueDto , String email) {
        Integer venue = venueRepo.update(
                venueDto.getVenueName(),
                venueDto.getUserName(),
                venueDto.getAddress(),
                venueDto.getContactNumber(),
                passwordEncoder.encode(venueDto.getPassword()),
                venueDto.getDescription(),
                email);
        if(venue != null){
            Integer user = userRepo.update(
                    venueDto.getUserName(),
                    passwordEncoder.encode(venueDto.getPassword()),
                    email);
        }
        return venue;
    }

    public List<VenueDto> getAllVerifiedVenue() {
        List<Venue> venueList= venueRepo.findAllVerifiedVenue(VenueStatus.VERIFY);
        return venueList.stream().map(entity-> VenueDto.builder()
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
        List<Booking> requestList= venueRepo.getAllBookingList(email);
        return requestList.stream().map(entity-> Booking.builder()
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
    public EventDto uploadEventDetails(EventDto eventDto, String email) {
        Venue venue = venueRepo.findVenueByEmail(email).orElseThrow(()->new RuntimeException("venueNotFound"));
        FunctionType marriage = eventDto.getMarriage();
        FunctionType f1 = FunctionType.builder()
                .id(101)
                .event(EventType.MARRIAGE)
                .baseCost(marriage.getBaseCost())
                .rate(marriage.getRate())
                .venue1(venue)
                .build();
        FunctionType conclave = eventDto.getConclave();
        FunctionType f2 = FunctionType.builder()
                .id(102)
                .event(EventType.CONCLAVE)
                .baseCost(conclave.getBaseCost())
                .rate(conclave.getRate())
                .venue1(venue)
                .build();
        FunctionType collegeEvent = eventDto.getCollegeEvent();
        FunctionType f3 = FunctionType.builder()
                .id(103)
                .event(EventType.COLLEGE_EVENT)
                .baseCost(collegeEvent.getBaseCost())
                .rate(collegeEvent.getRate())
                .venue1(venue)
                .build();
        FunctionType annualMeet = eventDto.getAnnualMeet();
        FunctionType f4 = FunctionType.builder()
                .id(104)
                .event(EventType.ANNUAL_MEET)
                .baseCost(annualMeet.getBaseCost())
                .rate(annualMeet.getRate())
                .venue1(venue)
                .build();
        FunctionType familyParty = eventDto.getFamilyParty();
        FunctionType f5 = FunctionType.builder()
                .id(105)
                .event(EventType.FAMILY_PARTY)
                .baseCost(familyParty.getBaseCost())
                .rate(familyParty.getRate())
                .venue1(venue)
                .build();
        List<FunctionType> functions = Arrays.asList(f1, f2, f3, f4, f5);
        Iterable<FunctionType> persistedFunctions = functionRepo.saveAll(functions);
        if(persistedFunctions != null){
            return eventDto;
        }
        return null;
    }
}

