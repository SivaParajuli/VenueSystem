package com.vbs.vbs.controller.client;

import com.vbs.vbs.controller.BaseController;
import com.vbs.vbs.dto.responses.ResponseDto;
import com.vbs.vbs.dto.client.ClientDto;
import com.vbs.vbs.models.venue.BookingRequest;
import com.vbs.vbs.services.client.ClientService;
import com.vbs.vbs.services.venue.VenueBookingRequestService;
import com.vbs.vbs.services.venue.VenueService;
import com.vbs.vbs.utils.CurrentUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="client")

public class ClientController extends BaseController {
    private final VenueBookingRequestService venueBookingRequestService;
    private final VenueService venueService;
    private final ClientService clientService;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    JwtTokenHelper jwtTokenHelper;
//
//    @Autowired
//    private UserDetailsService userDetailsService;


    public ClientController(VenueBookingRequestService venueBookingRequestService, VenueService venueService, ClientService clientService) {
        this.venueBookingRequestService = venueBookingRequestService;
        this.venueService = venueService;
        this.clientService = clientService;
    }



//    @PostMapping("login")
//    public ResponseEntity<?> login(@RequestBody UsernameAndPasswordAuthenticationRequest authenticationRequest) throws InvalidKeySpecException {
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                    authenticationRequest.getUsername(),
//                    authenticationRequest.getPassword()
//            );
//
//        final Authentication authenticate = authenticationManager.authenticate(authentication);
//
//        SecurityContextHolder.getContext().setAuthentication(authenticate);
//
//        ApplicationUser user=(ApplicationUser) authenticate.getPrincipal();
//        String jwtToken=jwtTokenHelper.generateToken(user.getUsername());
//
//        LoginResponse response=new LoginResponse();
//        response.setToken(jwtToken);
//
//
//        return ResponseEntity.ok(response);
//    }

    @PostMapping(path="register")
    public ResponseEntity<ResponseDto> createClient(@RequestBody ClientDto clientDto) throws IOException {
        clientDto =clientService.create(clientDto);
        if(clientDto !=null){
            return new ResponseEntity<>
                    (successResponse("Client Created.", clientDto), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Client Creation Failed",null),HttpStatus.BAD_REQUEST);
        }
    }

//TODO
    @GetMapping
    public ResponseEntity findAll(){
        List<ClientDto> clientDtoList =clientService.findAll();
        return new ResponseEntity<>
                (successResponse("Venue List Fetched", clientDtoList),HttpStatus.OK);
    }

    @PostMapping(path="book-venue/{email}")
    public ResponseEntity<ResponseDto> BookingRequest(@RequestBody BookingRequest bookingRequest, @PathVariable String email){
        CurrentUser user = new CurrentUser();
         String clientMail = user.CurrentUserName((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        bookingRequest = venueBookingRequestService.VenueBookingRequest(bookingRequest,email,clientMail);
        if(bookingRequest!=null){
            return new ResponseEntity<>
                    (successResponse("Request Sent",bookingRequest), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>
                    (errorResponse("Request Sent",null),HttpStatus.BAD_REQUEST);


    }



    @GetMapping(path="{email}")
    public ResponseEntity<ResponseDto>findClientByEmail(@PathVariable String email){
        ClientDto clientDto=clientService.findClientByEmail(email);
        if(clientDto !=null) {
            return new ResponseEntity<>
                    (successResponse("Client   Fetched.", clientDto), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Client Fetched Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

}
