package com.vbs.vbs.service.impl.user.admin;

import com.vbs.vbs.dto.user.admin.AdminDto;
import com.vbs.vbs.entity.user.admin.Admin;
import com.vbs.vbs.repo.user.admin.AdminRegisterRepo;
import com.vbs.vbs.service.user.admin.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRegisterRepo adminRegisterRepo;
//    private final VenueServiceImpl venueService;

    public AdminServiceImpl(AdminRegisterRepo adminRegisterRepo/*, VenueServiceImpl venueService*/) {
        this.adminRegisterRepo = adminRegisterRepo;
//        this.venueService = venueService;
    }

    @Override
    public AdminDto create(AdminDto adminDto) {
        Admin entity= Admin.builder()
                .id(adminDto.getId())
                .name(adminDto.getName())
                .email(adminDto.getEmail())
                .password(adminDto.getPassword())
                .build();
        entity= adminRegisterRepo.save(entity);
        return AdminDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }

//    public void book(VenueRegister venueRegister){
//       VenueRegisterDto venueRegister1 = venueService.findById(venueRegister.getAddress());
//       venueRegister1.setContact("724959473");
//       venueService.create(venueRegister1);
//    }

}
