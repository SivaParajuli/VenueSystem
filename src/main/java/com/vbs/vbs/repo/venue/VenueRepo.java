package com.vbs.vbs.repo.venue;

import com.vbs.vbs.models.venue.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VenueRepo extends JpaRepository<Venue, Integer>{

    @Query(value = "select  * from tbl_venue where  city_address= ?1",nativeQuery = true)
    List<Venue> findByCity_name(String city_name);

    @Query(value="select * from tbl_venue",nativeQuery = true)
    List<Venue>findInMainPage();


    @Query(value = "select  * from tbl_venue where  street_address= ?1",nativeQuery = true)
    List<Venue> findByStreet_name(String street_name);

    @Query(value = "select  * from tbl_venue where  v_name= ?1",nativeQuery = true)
    Venue findByV_name(String v_name);

    @Query(value = "select  * from tbl_venue where  email= ?1" ,nativeQuery = true)
    Optional<Venue> findUserByEmail(String email);

    @Query(value="select * from tbl_venue where venueUpdateStatus=PENDING",nativeQuery = true)
    List<Venue>findInAdminPage();
}
