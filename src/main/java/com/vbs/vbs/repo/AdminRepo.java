package com.vbs.vbs.repo;

import com.vbs.vbs.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {
 Admin findAdminByEmail(String email);


}
