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
    private String clientEmail;
    private String venueEmail;
    private Integer contactNumber;
    private Date BookingDate;
    private String functionType;
    private Double OfferedPayment;
    private Integer requiredCapacity;
    private BookingStatus bookingStatus;
}
