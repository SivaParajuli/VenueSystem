package com.vbs.vbs.jwt;

import com.google.common.net.HttpHeaders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;


@Component
@CrossOrigin(origins = "*")
@ConfigurationProperties(prefix="application.jwt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtConfig {

    private String secretKey;

    private String tokenPrefix;

    private Integer tokenExpirationAfterDays;


    public String getAuthorizationHeader(){
        return HttpHeaders.AUTHORIZATION;
    }
}
