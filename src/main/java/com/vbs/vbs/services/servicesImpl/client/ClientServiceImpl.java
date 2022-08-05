package com.vbs.vbs.services.servicesImpl.client;
import com.vbs.vbs.dto.client.ClientDto;
import com.vbs.vbs.models.User;
import com.vbs.vbs.models.client.Client;
import com.vbs.vbs.models.venue.BookingRequest;
import com.vbs.vbs.repo.UserRepo;
import com.vbs.vbs.repo.client.ClientRepo;
import com.vbs.vbs.security.ApplicationUserRole;
import com.vbs.vbs.services.client.ClientService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  ClientServiceImpl implements ClientService {
    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public ClientServiceImpl(ClientRepo clientRepo, PasswordEncoder passwordEncoder, UserRepo userRepo) {

        this.clientRepo = clientRepo;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    @Override
    public ClientDto create(ClientDto clientDto) {

        Client entity= Client.builder()
                .name(clientDto.getName())
                .mobile_no(clientDto.getMobile_no())
                .email(clientDto.getEmail())
                .city_name(clientDto.getCity_name())
                .street_name(clientDto.getStreet_name())
                .password(passwordEncoder.encode(clientDto.getPassword()))
                .applicationUserRole(ApplicationUserRole.CUSTOMER)
                .build();
        User entity1= User.builder()
                .email(clientDto.getEmail())
                .password(passwordEncoder.encode(clientDto.getPassword()))
                .applicationUserRole(ApplicationUserRole.CUSTOMER).build();
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
    public ClientDto findClientByEmail(String email) {
        Optional<Client> clientOptional= clientRepo.findClientByEmail(email);
        if(clientOptional.isPresent()){
            Client entity=clientOptional.get();
            return ClientDto.builder()
                    .name(entity.getName())
                    .email(entity.getEmail())
                    .mobile_no(entity.getMobile_no())
                    .build();
        }
        return null;
    }

    @Override
    public List<ClientDto> findAll() {
        List<Client> clientList = clientRepo.findAll();
        return clientList.stream().map(entity-> ClientDto.builder()
                .name(entity.getName())
                .mobile_no(entity.getMobile_no())
                .email(entity.getEmail())
                .street_name(entity.getStreet_name())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<BookingRequest> getAllRequests(String email) {
        List<BookingRequest> requestList= clientRepo.getAllBookingRequests(email);
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

}
