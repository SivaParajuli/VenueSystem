package com.vbs.vbs.services;

import com.vbs.vbs.models.Admin;

public interface AdminService{
    Admin findAdminByEmail(String adminMail);
}
