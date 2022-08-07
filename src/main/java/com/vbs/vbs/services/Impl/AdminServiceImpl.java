package com.vbs.vbs.services.Impl;

import com.vbs.vbs.dto.AdminDto;
import com.vbs.vbs.repo.AdminRepo;
import com.vbs.vbs.services.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public AdminDto findAdminByEmail(String adminMail) {
        return null;
    }
}
