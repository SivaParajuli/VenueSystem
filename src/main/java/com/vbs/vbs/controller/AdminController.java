package com.vbs.vbs.controller;

import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.StatusChangeReq;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.models.Admin;
import com.vbs.vbs.services.AdminService;
import com.vbs.vbs.services.RegisterService;
import com.vbs.vbs.services.VenueService;
import com.vbs.vbs.utils.EmailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("admin-")
public class AdminController extends BaseController {

    private final VenueService venueService;
    private final RegisterService registerService;
    private final AdminService adminService;
    private final EmailSenderService emailSenderService;


    public AdminController(VenueService venueService, RegisterService registerService, AdminService adminService, EmailSenderService emailSenderService) {
        this.venueService = venueService;
        this.registerService = registerService;
        this.adminService = adminService;
        this.emailSenderService = emailSenderService;
    }

    @GetMapping("registerRequests")
    public ResponseEntity<ResponseDto>getAllRegisterRequests(){
        List<VenueDto> venueList =registerService.getAllPendingRegister();
        if(venueList !=null) {
            return new ResponseEntity<>
                    (successResponse("Requested Booking List  Fetched.",venueList), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Venue Fetched Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="{email}")
    public ResponseEntity<ResponseDto> findUser(@PathVariable String email){
                Admin currentUser = adminService.findAdminByEmail(email);
        if(currentUser !=null){
            return new ResponseEntity<>
                    (successResponse("CurrentUser", currentUser), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>
                    (errorResponse("sorry",null),HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "*",methods = RequestMethod.PUT,maxAge = 86400,allowedHeaders = "*")
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseDto>verifyVenue(@RequestBody StatusChangeReq statusChangeReq, @PathVariable("id") Integer id) {
        Integer venue = registerService.updateVenueStatus(statusChangeReq.getStatus(), id);
        if (venue != null) {
            return new ResponseEntity<>
                    (successResponse("Updating Successful.", venue), HttpStatus.OK);
        } else {
            return new ResponseEntity<>
                    (errorResponse("Updating venue verification status failed.", null), HttpStatus.BAD_REQUEST);
        }
    }
}

