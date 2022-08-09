package com.vbs.vbs.repo;

import com.vbs.vbs.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {
 @Query(value="select a from Admin a where a.email= :e")
 Admin findAdminByEmail(@Param("e")String email);


}
