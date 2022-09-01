package com.vbs.vbs.services.Impl;

import com.vbs.vbs.dto.BookingDto;
import com.vbs.vbs.dto.EventsCostCalculation;
import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.enums.EventType;
import com.vbs.vbs.models.Client;
import com.vbs.vbs.models.EventsCostAndRate;
import com.vbs.vbs.models.Venue;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.repo.ClientRepo;
import com.vbs.vbs.repo.BookingRepo;
import com.vbs.vbs.repo.VenueRepo;
import com.vbs.vbs.services.BookingServices;
import com.vbs.vbs.utils.BookingUtils;
import com.vbs.vbs.utils.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Optional;


@Service
public class BookingServicesImpl implements BookingServices {
    private final BookingRepo bookingRepo;
    private final VenueRepo venueRepo;
    private final ClientRepo clientRepo;
    private final EmailSenderService emailSenderService;
    private final BookingUtils bookingUtils;


    @Autowired
    public BookingServicesImpl(BookingRepo bookingRepo,
                               VenueRepo venueRepo, ClientRepo clientRepo, EmailSenderService emailSenderService, BookingUtils bookingUtils) {
        this.bookingRepo = bookingRepo;
        this.venueRepo = venueRepo;
        this.clientRepo = clientRepo;
        this.emailSenderService = emailSenderService;
        this.bookingUtils = bookingUtils;
    }

    @Override
    public Booking VenueBookingRequest(BookingDto bookingDto, String vEmail, String email ) throws IOException {
        Client client1 = clientRepo.findClientByEmail(email).orElseThrow(()->new RuntimeException("clientNotFound"));
        Venue venue1 = venueRepo.findVenueByEmail(vEmail).orElseThrow(()->new RuntimeException("venueNotFound"));
        if(Integer.parseInt(bookingDto.getRequiredCapacity()) > Integer.parseInt(venue1.getCapacity())){
           throw new IOException("invalid requirement");
        }
        EventType eventType = bookingUtils.getEvent(bookingDto.getFunctionType());
        EventsCostCalculation rateAndCost = venueRepo.getRateAndCost(vEmail);
        Integer cPayment = Integer.parseInt(bookingDto.getRequiredCapacity());
        Booking entity = Booking.builder()
                .bookingDate(bookingDto.getBookingDate())
                .eventType(eventType)
                .calculatedPayment(bookingUtils.calculatePayment(rateAndCost.getRate(), bookingUtils.getCost(bookingDto.getFunctionType(),vEmail),cPayment))
                .bookingStatus(BookingStatus.PENDING)
                .requiredCapacity(bookingDto.getRequiredCapacity())
                .contactNumber(bookingDto.getContactNumber())
                .client(client1)
                .venue(venue1).build();
        entity = bookingRepo.save(entity);
        return Booking.builder()
                .venue(entity.getVenue())
                .bookingDate(entity.getBookingDate())
                .eventType(entity.getEventType())
                .bookingStatus(entity.getBookingStatus())
                .contactNumber(entity.getContactNumber())
                .build();

    }

    @Override
    public Integer VenueBookingResponse(Integer bookingStatus, Integer id) {
        if (bookingStatus == 1) {
            Optional<Booking> booking = bookingRepo.findById(id);
            if (booking.isPresent()) {
                Booking booking1 = booking.get();
                emailSenderService.sendEmail(booking1.getClient().getEmail(),
                        "Booking Response",
                        "Mr/Miss " + booking1.getClient().getName() +" Your Booking is Successful for "+booking1.getBookingDate()
                );
                return bookingRepo.updateBookingStatus(BookingStatus.ACCEPTED, id);
            }
        }
        if (bookingStatus == 2) {
            Optional<Booking> booking = bookingRepo.findById(id);
            if (booking.isPresent()) {
                Booking booking1 = booking.get();
                emailSenderService.sendEmail(booking1.getClient().getEmail(),
                        "Booking Response",
                        "Mr/Miss " + booking1.getClient().getName() + " Your Booking Request is Denied.please with another."
                );
                return bookingRepo.updateBookingStatus(BookingStatus.CANCELED, id);
            }
        }
        return null;
    }

    @Override
    public Booking findById(Integer id) {
        Optional<Booking> optionalBooking =bookingRepo.findById(id);
        if(optionalBooking.isPresent()){
           Booking booking = optionalBooking.get();
            return Booking.builder()
                    .client(booking.getClient())
                    .venue(booking.getVenue())
                    .contactNumber(booking.getContactNumber())
                    .build();
        }
        return null;
    }
}