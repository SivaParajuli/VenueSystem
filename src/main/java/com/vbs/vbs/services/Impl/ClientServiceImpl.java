package com.vbs.vbs.services.Impl;

import com.vbs.vbs.dto.ClientDto;
import com.vbs.vbs.models.Client;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.repo.ClientRepo;
import com.vbs.vbs.security.user.UserRepo;
import com.vbs.vbs.services.ClientService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  ClientServiceImpl implements ClientService {
    private final ClientRepo clientRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepo clientRepo, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.clientRepo = clientRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Client findClientByEmail(String email) {
        Optional<Client> clientOptional= clientRepo.findClientByEmail(email);
        if(clientOptional.isPresent()){
            Client entity=clientOptional.get();
            return Client.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .email(entity.getEmail())
                    .city_name(entity.getCity_name())
                    .street_name(entity.getStreet_name())
                    .mobile_no(entity.getMobile_no())
                    .build();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clientList = clientRepo.findAll();
        return clientList.stream().map(entity-> Client.builder()
                .name(entity.getName())
                .mobile_no(entity.getMobile_no())
                .email(entity.getEmail())
                .street_name(entity.getStreet_name())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<Booking> getBooking(String email) {
        List<Booking> requestList= clientRepo.getAllBookingRequests(email);
        return requestList.stream().map(entity-> Booking.builder()
                .id(entity.getId())
                .bookingDate(entity.getBookingDate())
                .venue(entity.getVenue())
                .contactNumber(entity.getContactNumber())
                .functionType(entity.getFunctionType())
                .calculatedPayment(entity.getCalculatedPayment())
                .requiredCapacity(entity.getRequiredCapacity())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Integer updateClient(ClientDto clientDto,String email) {
        Integer client = clientRepo.updateClient(
                clientDto.getUsername(),
                clientDto.getMobile_no(),
                passwordEncoder.encode(clientDto.getPassword()),
                email);
        if(client != null){
            Integer user = userRepo.update(
                    clientDto.getUsername(),
                    passwordEncoder.encode(clientDto.getPassword()),
                    email);
        }
        return client;
    }

}
