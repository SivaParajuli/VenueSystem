package com.vbs.vbs.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {
    private Integer id;
    private String name;
    private String email;
    private String mobile_no;
    private String city_name;
    private String street_name;
    private String password;
}