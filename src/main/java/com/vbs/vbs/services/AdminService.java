package com.vbs.vbs.services;


import com.vbs.vbs.dto.AdminDto;
import org.springframework.stereotype.Service;

@Service
public interface AdminService{
    AdminDto findAdminByEmail(String adminMail);
}
