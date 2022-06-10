package com.vbs.vbs.controller.user.admin;

import com.vbs.vbs.controller.BaseController;
import com.vbs.vbs.dto.user.venue.VenueDto;
import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.service.user.admin.AdminService;
import com.vbs.vbs.service.user.venue.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("admin")
public class  AdminController extends BaseController {

    private final AdminService adminService;

    private final VenueService venueService;


    public AdminController(AdminService adminService, VenueService venueService) {
        this.adminService = adminService;
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity findInAdminPage(){

        List<VenueDto> venueDtoList= venueService.findInAdminPage();
        return new ResponseEntity<>
                (successResponse("Successfull",venueDtoList),HttpStatus.OK);

    }

    @PostMapping("/updateStatus/{id}/{enums}")
    public void updateVenueStatus(@PathVariable("id") Integer id,VenueDto venueDto,@PathVariable("enums") String enums){
        venueDto=venueService.findById(id);
        if(enums=="VERIFY") {
            venueDto.setVenueStatus(VenueStatus.VERIFY);
            venueService.create(venueDto);
        }
        else{
            venueService.deleteBYId(id);
        }
    }

}

