package com.vbs.vbs.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventsCostCalculation {
    private Double marriage;
    private Double conclave;
    private Double collegeEvent;
    private Double annualMeet;
    private Double familyParty;
    private Double rate;
}
