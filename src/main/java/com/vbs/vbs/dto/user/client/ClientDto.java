package com.vbs.vbs.dto.user.client;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientDto {
    private Integer id;
    private String name;
    private String email;
    private String mobile_no;
    private String password;
    private String city_name;
    private String street_name;
}
