package com.vbs.vbs.controller;

import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.StatusChangeReq;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.models.Admin;
import com.vbs.vbs.models.Venue;
import com.vbs.vbs.services.AdminService;
import com.vbs.vbs.services.RegisterService;
import com.vbs.vbs.services.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*",maxAge = 86400,allowedHeaders = "*")
@RestController
@RequestMapping("admin-")
public class AdminController extends BaseController {

    private final VenueService venueService;
    private final RegisterService registerService;
    private final AdminService adminService;


    public AdminController(VenueService venueService, RegisterService registerService, AdminService adminService) {
        this.venueService = venueService;
        this.registerService = registerService;
        this.adminService = adminService;
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


    @CrossOrigin(methods = RequestMethod.PUT)
    @GetMapping("update/{email}")
    public ResponseEntity<ResponseDto>verifyVenue(@RequestParam("status") Integer status, @PathVariable("email") String email){
      Integer venue= registerService.updateVenueStatus(status,email);
        if(venue != null) {
            return new ResponseEntity<>
                    (successResponse("Updating Sucessfull.", venue), HttpStatus.OK);
        }

        else{
            return new ResponseEntity<>
                    (errorResponse("Updating venue verification status failed.", null), HttpStatus.BAD_REQUEST);
        }
    }


}

