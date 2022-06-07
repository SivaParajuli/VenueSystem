package com.vbs.vbs.repo.user.admin;

import com.vbs.vbs.entity.user.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRegisterRepo extends JpaRepository<Admin,Integer> {
    public Admin findAdminRegisterByEmail(String email);


}
