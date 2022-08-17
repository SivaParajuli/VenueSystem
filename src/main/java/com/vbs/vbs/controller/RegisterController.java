package com.vbs.vbs.controller;

import com.vbs.vbs.dto.ClientDto;
import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.models.Admin;
import com.vbs.vbs.services.FileService;
import com.vbs.vbs.services.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="register")
public class RegisterController extends BaseController {

    private final RegisterService registerService;
    private final FileService fileService;

    public RegisterController(RegisterService registerService, FileService fileService) {
        this.registerService = registerService;
        this.fileService = fileService;
    }

    @PostMapping(path="client")
    public ResponseEntity<ResponseDto> createClient(@RequestBody ClientDto clientDto) {
        clientDto =registerService.clientRegister(clientDto);
        if(clientDto !=null){
            return new ResponseEntity<>
                    (successResponse("Client Created.", clientDto), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Client Creation Failed",null),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="venue")
    public ResponseEntity<ResponseDto> createVenue(@RequestBody VenueDto venueDto) {
        venueDto =registerService.venueRegister(venueDto);
        if(venueDto !=null){
//            emailSenderService.sendEmail("svenuebooking.spad01@gmail.com",
//                    "Registration Request",
//                    venueDto.getVenueName() +" wants to be registered with requirements in vbs.");

            return new ResponseEntity<>
                    (successResponse("Registration Request Sent Successfully.", venueDto), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Venue Creation Failed",null),HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "*",methods = RequestMethod.PUT,maxAge = 86400,allowedHeaders = "*")
    @PutMapping(path="uploadImage/{email}")
    public ResponseEntity<ResponseDto> uploadImage(@PathVariable("email")String email , @RequestPart MultipartFile file) throws IOException {
         String filepath = fileService.uploadImage(email,file);
        if(filepath !=null){

            return new ResponseEntity<>
                    (successResponse("Image upload Successfully.", filepath), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("upload failed",null),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="admin")
    public ResponseEntity<ResponseDto> registerAdmin(@RequestBody Admin admin) {
        admin =registerService.registerAdmin(admin);
        if(admin !=null){
            return new ResponseEntity<>
                    (successResponse("Client Created.", admin), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Client Creation Failed",null),HttpStatus.BAD_REQUEST);
        }
    }
}
