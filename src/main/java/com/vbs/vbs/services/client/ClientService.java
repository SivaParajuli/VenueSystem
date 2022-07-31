package com.vbs.vbs.services.client;

import com.vbs.vbs.dto.client.ClientDto;
import com.vbs.vbs.dto.venue.VenueDto;

public interface ClientService {
  ClientDto create(ClientDto clientDto);
  ClientDto findUserByEmail(String email);

}
