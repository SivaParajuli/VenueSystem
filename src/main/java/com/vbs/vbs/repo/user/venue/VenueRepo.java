package com.vbs.vbs.repo.user.venue;

import com.vbs.vbs.entity.user.venue.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueRepo extends JpaRepository<Venue, Integer>{
    public Venue findVenueRegisterByEmail(String email);

    @Query(value = "select  * from tbl_venue where  city_address= ?1",nativeQuery = true)
    List<Venue> findByCity_name(String city_name);

    @Query(value="select * from tbl_venue",nativeQuery = true)
    List<Venue>findInMainPage();


    @Query(value = "select  * from tbl_venue where  street_address= ?1",nativeQuery = true)
    List<Venue> findByStreet_name(String street_name);

    @Query(value = "select  * from tbl_venue where  v_name= ?1",nativeQuery = true)
    Venue findByV_name(String v_name);

}
