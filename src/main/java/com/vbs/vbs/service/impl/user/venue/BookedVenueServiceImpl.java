package com.vbs.vbs.service.impl.user.venue;

import com.vbs.vbs.dto.user.venue.BookedDto;
import com.vbs.vbs.entity.user.venue.BookedVenue;
import com.vbs.vbs.repo.user.venue.BookedVenueRepo;
import com.vbs.vbs.service.user.venue.BookedVenueService;
import org.springframework.stereotype.Service;


@Service
public class BookedVenueServiceImpl implements BookedVenueService {
    private final BookedVenueRepo bookedVenueRepo;

    public BookedVenueServiceImpl(BookedVenueRepo bookedVenueRepo) {
        this.bookedVenueRepo = bookedVenueRepo;
    }

    @Override
    public BookedDto save (BookedDto bookedDto)  {
        BookedVenue entity=new BookedVenue();
        entity.setId(bookedDto.getId());
        entity.setBookedDate(bookedDto.getBookedDate());
        entity.setDate(bookedDto.getDate());
        entity.setFunction(bookedDto.getFunction());
        entity.setRequiredCapacity(bookedDto.getRequiredCapacity());
        bookedVenueRepo.save(entity);
        return bookedDto;
    }
}
