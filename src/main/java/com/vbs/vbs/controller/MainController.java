//package com.vbs.vbs.controller;
//
//import com.vbs.vbs.dto.user.venue.VenueDto;
//import com.vbs.vbs.service.user.venue.VenueService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/")
//public class MainController extends BaseController{
//    private final  VenueService venueService;
//
//
//    public MainController(VenueService venueService) {
//        this.venueService = venueService;
//    }
//    @GetMapping
//    public ResponseEntity findInMainPage(){
//        List<VenueDto> venueDtoList = venueService.findInMainPage();
//        return new ResponseEntity<>
//                (successResponse("Venue List for main page Fetched", venueDtoList), HttpStatus.OK);
//
//    }
//}
