package com.vbs.vbs.services.client;

import com.vbs.vbs.dto.client.ClientDto;
import com.vbs.vbs.dto.venue.VenueDto;

import java.util.List;

public interface ClientService {
  ClientDto create(ClientDto clientDto);
  ClientDto findUserByEmail(String email);
  List<ClientDto> findAll();

}
