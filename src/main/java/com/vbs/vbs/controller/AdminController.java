package com.vbs.vbs.controller;

import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.models.Venue;
import com.vbs.vbs.services.RegisterService;
import com.vbs.vbs.services.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("admin")
public class  AdminController extends BaseController {

    private final VenueService venueService;
    private final RegisterService registerService;


    public AdminController(VenueService venueService, RegisterService registerService) {
        this.venueService = venueService;
        this.registerService = registerService;
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

