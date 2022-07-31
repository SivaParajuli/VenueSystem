package com.vbs.vbs.services.servicesImpl.admin;

import com.vbs.vbs.repo.admin.AdminRepo;
import com.vbs.vbs.services.admin.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepo adminRegisterRepo;
//    private final VenueServiceImpl venueService;

    public AdminServiceImpl(AdminRepo adminRegisterRepo/*, VenueServiceImpl venueService*/) {
        this.adminRegisterRepo = adminRegisterRepo;
//        this.venueService = venueService;
    }

//    public void book(VenueRegister venueRegister){
//       VenueRegisterDto venueRegister1 = venueService.findById(venueRegister.getAddress());
//       venueRegister1.setContact("724959473");
//       venueService.create(venueRegister1);
//    }

}
