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
    private String v_name;
    private String contact;
    private String email;
    private String capacity;
    private String city_name;
    private String street_name;
    private String password;
    private VenueStatus venueStatus;

}
