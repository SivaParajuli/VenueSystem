package com.vbs.vbs.repo.user.admin;

import com.vbs.vbs.entity.user.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRegisterRepo extends JpaRepository<Admin,Integer> {
    public Admin findAdminRegisterByEmail(String email);

}
