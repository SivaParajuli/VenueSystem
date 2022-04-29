package com.vbs.vbs.service.impl.user.client;

import com.vbs.vbs.dto.user.client.ClientRegisterDto;
import com.vbs.vbs.entity.user.client.Client;
import com.vbs.vbs.repo.user.client.ClientRegisterRepo;
import com.vbs.vbs.service.user.client.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRegisterRepo clientRegisterRepo;

    public ClientServiceImpl(ClientRegisterRepo clientRegisterRepo) {
        this.clientRegisterRepo = clientRegisterRepo;
    }

    @Override
    public ClientRegisterDto create(ClientRegisterDto clientRegisterDto) {

        Client entity= Client.builder()
                .id(clientRegisterDto.getId())
                .name(clientRegisterDto.getName())
                .mobile_no(clientRegisterDto.getMobile_no())
                .email(clientRegisterDto.getEmail())
                .city_name(clientRegisterDto.getCity_name())
                .street_name(clientRegisterDto.getStreet_name())
                .password(clientRegisterDto.getPassword())
                .build();
        entity= clientRegisterRepo.save(entity);
        return ClientRegisterDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .mobile_no(entity.getMobile_no())
                .email(entity.getEmail())
                .city_name(entity.getCity_name())
                .street_name(entity.getStreet_name())
                .password(entity.getPassword())
                .build();
    }

}
