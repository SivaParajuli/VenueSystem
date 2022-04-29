package com.vbs.vbs.controller.admin;

import com.vbs.vbs.controller.BaseController;
import com.vbs.vbs.dto.user.admin.AdminRegisterDto;
import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.service.user.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("admin")
public class  AdminController extends BaseController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("create")
    public ResponseEntity<ResponseDto> createAdmin(@RequestBody AdminRegisterDto adminRegisterDto){
       adminRegisterDto = adminService.create(adminRegisterDto);
        if(adminRegisterDto !=null) {
            return new ResponseEntity<>
                    (successResponse("created successfully.", adminRegisterDto), HttpStatus.CREATED);

        }
        else{
            return new ResponseEntity<>
                    (errorResponse("creation Failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

