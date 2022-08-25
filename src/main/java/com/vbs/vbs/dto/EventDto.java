package com.vbs.vbs.dto;

import com.vbs.vbs.models.FunctionType;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private FunctionType marriage;
    private FunctionType conclave;
    private FunctionType collegeEvent;
    private FunctionType annualMeet;
    private FunctionType familyParty;
}
