package com.vbs.vbs.controller.user.admin;

import com.vbs.vbs.controller.BaseController;
import com.vbs.vbs.dto.user.admin.AdminDto;
import com.vbs.vbs.dto.ResponseDto;
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

    @PostMapping("create")
    public ResponseEntity<ResponseDto> createAdmin(@RequestBody AdminDto adminDto){
       adminDto = adminService.create(adminDto);
        if(adminDto !=null) {
            return new ResponseEntity<>
                    (successResponse("created successfully.", adminDto), HttpStatus.CREATED);

        }
        else{
            return new ResponseEntity<>
                    (errorResponse("creation Failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity findInAdminPage(){

        List<VenueDto> venueDtoList= venueService.findInMainPage();
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

