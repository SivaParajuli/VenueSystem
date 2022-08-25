package com.vbs.vbs.dto;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDto {
    private Integer id;
    private String functionType;
    private Date bookingDate;
    private String requiredCapacity;
    private String contactNumber;
}
