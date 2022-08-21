package com.vbs.vbs.services;

import com.vbs.vbs.dto.ClientDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.models.Client;

import java.util.List;

public interface ClientService {
  Client findClientByEmail(String email);
  List<Client> findAll();
  List<Booking> getBooking(String email);
  Integer updateClient(ClientDto clientDto, String email);
}
