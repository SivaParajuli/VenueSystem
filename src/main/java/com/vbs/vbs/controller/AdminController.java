package com.vbs.vbs.controller;

import com.vbs.vbs.dto.AdminDto;
import com.vbs.vbs.dto.ClientDto;
import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.models.Venue;
import com.vbs.vbs.services.AdminService;
import com.vbs.vbs.services.RegisterService;
import com.vbs.vbs.services.VenueService;
import com.vbs.vbs.utils.CurrentUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("admin-/{email}")
public class  AdminController extends BaseController {

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
        List<Venue> venueList =registerService.getAllPendingRegister();
        if(venueList !=null) {
            return new ResponseEntity<>
                    (successResponse("Requested Booking List  Fetched.",venueList), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Venue Fetched Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDto> findUser(@PathVariable String email){
                AdminDto currentUser = adminService.findAdminByEmail(email);
        if(currentUser !=null){
            return new ResponseEntity<>
                    (successResponse("CurrentUser", currentUser), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>
                    (errorResponse("sorry",null),HttpStatus.BAD_REQUEST);
    }


//    @PostMapping("/updateStatus/{id}/{enums}")
//    public void updateVenueStatus(@PathVariable("id") Integer id,VenueDto venueDto,@PathVariable("enums") String enums) throws IOException {
//        venueDto=venueService.findById(id);
//        if(enums=="VERIFY") {
//            venueDto.setVenueStatus(VenueStatus.VERIFY);
//            venueService.create(venueDto);
//        }
//        else{
//            venueService.deleteBYId(id);
//        }
//    }

}

