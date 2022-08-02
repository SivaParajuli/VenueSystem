package com.vbs.vbs.services.servicesImpl.venue;


import com.vbs.vbs.repo.venue.VenueBookingRequestRepo;
import com.vbs.vbs.repo.venue.VenueRepo;
import com.vbs.vbs.services.venue.VenueBookingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class  VenueBookingRequestServiceImpl implements VenueBookingRequestService {
    private final VenueBookingRequestRepo venueBookingRequestRepo;
    private final VenueRepo venueRepo;


    @Autowired
    public VenueBookingRequestServiceImpl(VenueBookingRequestRepo venueBookingRequestRepo,
                                          VenueRepo venueRepo) {
        this.venueBookingRequestRepo = venueBookingRequestRepo;
        this.venueRepo = venueRepo;
    }

//    @Override
//    public BookingRequestDto VenueBookingRequest(BookingRequestDto bookingRequestDto,String email) {
//
//        Optional<Venue> venue = venueBookingRequestRepo.findVenueByEmail(email);
//        Optional<Venue> venue1 = venueRepo.findUserByEmail(email);
//       if (!venue.isPresent() || (venueBookingRequestRepo.getBookingDateByEmail(email)==bookingRequestDto.getBookingDate())){
//           VenueBookingRequest entity=new VenueBookingRequest();
//           entity.setBookingDate(bookingRequestDto.getBookingDate());
//           entity.setFunctionType(bookingRequestDto.getFunctionType());
//           entity.setBookingStatus(BookingStatus.PENDING);
//           entity.setPayment(bookingRequestDto.getOfferedPayment());
//           entity.setRequiredCapacity(bookingRequestDto.getRequiredCapacity());
//           entity.setContactNumber(bookingRequestDto.getContactNumber());
//           entity.setVenue(venue1.get());
//           venueBookingRequestRepo.save(entity);
//       }

//       if (bookingRequestDto.getId() ==null){
//           entity.setBookingStatus(BookingStatus.PENDING);
//       }
//       return null;
//    }

}
