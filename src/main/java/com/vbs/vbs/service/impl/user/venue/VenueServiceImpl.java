package com.vbs.vbs.service.impl.user.venue;

import com.vbs.vbs.dto.user.client.BookingRequestDto;
import com.vbs.vbs.dto.user.venue.VenueDto;
import com.vbs.vbs.entity.user.venue.Venue;
import com.vbs.vbs.entity.user.venue.VenueBookingRequest;
import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.repo.user.venue.VenueBookingRequestRepo;
import com.vbs.vbs.repo.user.venue.VenueRepo;
import com.vbs.vbs.service.user.venue.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VenueServiceImpl  implements VenueService {

    private final VenueRepo venueRepo;

    private final VenueBookingRequestRepo venueBookingRequestRepo;



    public VenueServiceImpl(VenueRepo venueRepo, VenueBookingRequestRepo venueBookingRequestRepo) {
        this.venueRepo = venueRepo;
        this.venueBookingRequestRepo = venueBookingRequestRepo;
    }


    @Override
    public VenueDto create(VenueDto venueDto) {
        Venue entity=new Venue();
        entity.setId(venueDto.getId());
        entity.setV_name(venueDto.getV_name());
        entity.setCity_name(venueDto.getCity_name());
        entity.setCapacity(venueDto.getCapacity());
        entity.setContact(venueDto.getContact());
        entity.setEmail(venueDto.getEmail());
        entity.setStreet_name(venueDto.getStreet_name());
        if(venueDto.getVenueStatus()==null) {
            entity.setVenueStatus(VenueStatus.PENDING);
        }
        else
            entity.setVenueStatus(venueDto.getVenueStatus());

        entity = venueRepo.save(entity);
        return VenueDto.builder()
                .id(entity.getId())
                .v_name(entity.getV_name())
                .email(entity.getEmail())
                .contact(entity.getContact())
                .city_name(venueDto.getCity_name())
                .street_name(venueDto.getStreet_name())
                .capacity(entity.getCapacity())
                .build();
    }

    @Override
    public List<VenueDto> findAll() {
        List<Venue> venueList = venueRepo.findAll();
        return venueList.stream().map(entity-> VenueDto.builder()
                .id(entity.getId())
                .v_name(entity.getV_name())
                .contact(entity.getContact())
                .email(entity.getEmail())
                .capacity(entity.getCapacity())
                .city_name(entity.getCity_name())
                .street_name(entity.getStreet_name())
                .password(entity.getPassword())
                .build()).collect(Collectors.toList());
    }

    @Override
    public VenueDto findById(Integer id) {
        Optional<Venue> venueOptional= venueRepo.findById(id);
        if(venueOptional.isPresent()){
            Venue entity=venueOptional.get();
            return VenueDto.builder()
                    .id(entity.getId())
                    .v_name(entity.getV_name())
                    .email(entity.getEmail())
                    .contact(entity.getContact())
                    .city_name(entity.getCity_name())
                    .street_name(entity.getStreet_name())
                    .capacity(entity.getCapacity())
                    .password(entity.getPassword())
                    .build();
        }
        return null;
    }

    @Override
    public List<VenueDto> findInMainPage() {
        List<Venue> venueList = venueRepo.findInMainPage();
        return venueList.stream().map(entity-> VenueDto.builder()
                .id(entity.getId())
                .v_name(entity.getV_name())
                .capacity(entity.getCapacity())
                .city_name(entity.getCity_name())
                .street_name(entity.getStreet_name())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<VenueDto> findInAdminPage() {
        List<Venue> venueList = venueRepo.findInAdminPage();
        return venueList.stream().map(entity->VenueDto.builder()
                .v_name(entity.getV_name())
                .capacity(entity.getCapacity())
                .contact(entity.getContact())
                .email(entity.getEmail())
                .city_name(entity.getCity_name())
                .street_name(entity.getStreet_name())
                .venueStatus(entity.getVenueStatus())
                .build()).collect(Collectors.toList());

    }

    @Override
    public List<VenueDto> findByCity_name(String city_name) {
        List<Venue> venueList = venueRepo.findByCity_name( city_name);
        return venueList.stream().map(entity-> VenueDto.builder()
                .id(entity.getId())
                .v_name(entity.getV_name())
                .capacity(entity.getCapacity())
                .city_name(entity.getCity_name())
                .street_name(entity.getStreet_name())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<VenueDto> findByStreet_name(String street_name) {
        List<Venue> venueList = venueRepo.findByStreet_name( street_name);
        return venueList.stream().map(entity-> VenueDto.builder()
                .id(entity.getId())
                .v_name(entity.getV_name())
                .capacity(entity.getCapacity())
                .city_name(entity.getCity_name())
                .street_name(entity.getStreet_name())
                .build()).collect(Collectors.toList());
    }

    @Override
    public void deleteBYId(Integer integer) {
        venueRepo.deleteById(integer);
    }

    @Override
    public List<BookingRequestDto> getVenueBookingRequestByClient(Integer venueId) {
        List<VenueBookingRequest> requestList= venueBookingRequestRepo.getVenueBookingRequestByClient(venueId);
        return requestList.stream().map(entity->BookingRequestDto.builder()
                .BookingDate(entity.getBookingDate())
                .clientId(entity.getId())
                .functionType(entity.getFunctionType())
                .payment(entity.getPayment())
                .requiredCapacity(entity.getRequiredCapacity())
                .build()).collect(Collectors.toList());
    }

}

