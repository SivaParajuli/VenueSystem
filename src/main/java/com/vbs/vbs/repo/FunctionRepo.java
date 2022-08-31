package com.vbs.vbs.repo;

import com.vbs.vbs.models.EventsCostAndRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionRepo extends JpaRepository<EventsCostAndRate,Integer> {

}
