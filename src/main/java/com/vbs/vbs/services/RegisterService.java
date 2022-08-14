package com.vbs.vbs.services;

import com.vbs.vbs.dto.ClientDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.models.Admin;

import java.io.IOException;
import java.util.List;

public interface RegisterService {

    ClientDto clientRegister(ClientDto clientDto);
    VenueDto venueRegister(VenueDto venueDto) throws IOException;
    List<VenueDto> getAllPendingRegister();
    Integer updateVenueStatus(Integer status, String email);
    Admin registerAdmin(Admin admin);


}
