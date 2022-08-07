package com.vbs.vbs.repo.admin;

import com.vbs.vbs.models.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {
 Admin findAdminByEmail(String email);


}
