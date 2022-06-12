package com.vbs.vbs.service.user.venue;

import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.user.client.BookingRequestDto;
import org.springframework.http.ResponseEntity;

public interface VenueBookingRequestService {
      BookingRequestDto VenueBookingRequest(BookingRequestDto bookingRequestDto);

}
