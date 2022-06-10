package com.vbs.vbs.dto.user.venue;

import com.vbs.vbs.enums.VenueStatus;
import lombok.*;

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
    private String address;
    private String password;

    private VenueStatus venueStatus;

}
