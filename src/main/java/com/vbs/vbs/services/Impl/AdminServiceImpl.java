package com.vbs.vbs.services.Impl;

import com.vbs.vbs.models.Admin;
import com.vbs.vbs.repo.AdminRepo;
import com.vbs.vbs.services.AdminService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepo adminRepo;

    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }


    @Override
    public Admin findAdminByEmail(String adminMail) {
        Admin admin= adminRepo.findAdminByEmail(adminMail);
        if (admin != null){
            return Admin.builder()
                    .name(admin.getName())
                    .email(admin.getEmail())
                    .build();
        }
        return null;
    }
}
