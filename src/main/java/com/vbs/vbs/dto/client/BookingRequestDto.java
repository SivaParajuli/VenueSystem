package com.vbs.vbs.dto.client;


import com.vbs.vbs.enums.BookingStatus;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingRequestDto {
    private Integer id;
    private Date BookingDate;
    private Double payment;
    private String functionType;
    private Integer requiredCapacity;
    private String clientName;
    private Integer contactNumber;
    private Integer venueId;
    private BookingStatus bookingStatus;
}
