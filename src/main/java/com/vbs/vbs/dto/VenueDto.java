package com.vbs.vbs.dto;

import com.vbs.vbs.enums.ApplicationUserRole;
import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.models.FunctionType;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class VenueDto {

    private Integer id;
    private String venueName;
    private String userName;
    private String contactNumber;
    private String email;
    private ApplicationUserRole applicationUserRole;
    private String address;
    private String password;
    private VenueStatus venueStatus;
    private String description;
    //used while saving
    private MultipartFile venueFile;
    //used while listing and sending data to front end
    private String filePath;
    private String capacity;
    private List<FunctionType> functionList;

}
