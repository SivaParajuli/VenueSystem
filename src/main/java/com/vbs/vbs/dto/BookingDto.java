package com.vbs.vbs.dto.venue;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookedDto {
    private Integer id;
    private Timestamp date;
    private String function;
    private Date bookedDate;
    private Number requiredCapacity;

}
