package com.vbs.vbs;

import com.vbs.vbs.services.RegisterService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class VenueBookingSystemApplication {
//    private final RegisterService registerService;

//    public VenueBookingSystemApplication(RegisterService registerService) {
//        this.registerService = registerService;
//    }

    public static void main(String[] args) {
        SpringApplication.run(VenueBookingSystemApplication.class, args);

    }
//    @EventListener(ApplicationReadyEvent.class)
//    public void registerAdmin(){
//       registerService.registerAdmin();
//
//    }
}
