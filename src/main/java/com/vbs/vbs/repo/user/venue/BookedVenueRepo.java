package com.vbs.vbs.repo.user.venue;

import com.vbs.vbs.entity.user.venue.BookedVenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookedVenueRepo extends JpaRepository<BookedVenue,Integer>{

}
