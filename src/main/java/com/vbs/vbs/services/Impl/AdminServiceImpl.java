package com.vbs.vbs.services.Impl;

import com.vbs.vbs.enums.ApplicationUserRole;
import com.vbs.vbs.models.Admin;
import com.vbs.vbs.repo.AdminRepo;
import com.vbs.vbs.security.user.User;
import com.vbs.vbs.services.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepo adminRepo;
    private final Admin admin;
    private final User user;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepo adminRepo, Admin admin, User user, PasswordEncoder passwordEncoder) {
        this.adminRepo = adminRepo;
        this.admin = admin;
        this.user = user;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public Admin updateAdmin() {
        admin.setEmail("svenuebooking.spad001@gmail.com");
        admin.setPassword(passwordEncoder.encode("Zxcvbnm@1234"));
        admin.setApplicationUserRole(ApplicationUserRole.ADMIN);
        admin.setName("Admin001");
        user.setEmail("svenuebooking.spad001@gmail.com");
        user.setPassword(passwordEncoder.encode("Zxcvbnm@1234"));
        user.setApplicationUserRole(ApplicationUserRole.ADMIN);
        user.setUname("Admin001");

        return null;
    }
}
