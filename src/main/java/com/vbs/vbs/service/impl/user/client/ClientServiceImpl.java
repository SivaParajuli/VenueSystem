//package com.vbs.vbs.service.impl.user.client;
//import com.vbs.vbs.repo.user.client.ClientRepo;
//import com.vbs.vbs.service.user.client.ClientService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class  ClientServiceImpl implements ClientService {
//    private final ClientRepo clientRepo;
//
//    public ClientServiceImpl(ClientRepo clientRepo) {
//
//        this.clientRepo = clientRepo;
//    }

//    @Override
//    public ClientDto create(ClientDto clientDto) {
//
//        Client entity= Client.builder()
//                .id(clientDto.getId())
//                .name(clientDto.getName())
//                .mobile_no(clientDto.getMobile_no())
//                .email(clientDto.getEmail())
//                .city_name(clientDto.getCity_name())
//                .street_name(clientDto.getStreet_name())
//                .password(clientDto.getPassword())
//                .build();
//        entity= clientRepo.save(entity);
//        return ClientDto.builder()
//                .id(entity.getId())
//                .name(entity.getName())
//                .mobile_no(entity.getMobile_no())
//                .email(entity.getEmail())
//                .city_name(entity.getCity_name())
//                .street_name(entity.getStreet_name())
//                .password(entity.getPassword())
//                .build();
//    }

//}
