package com.vbs.vbs.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {
    private Boolean status;
    private String message;
    private Object data;

}
