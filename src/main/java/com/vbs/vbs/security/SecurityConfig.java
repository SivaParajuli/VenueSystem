//package com.vbs.vbs.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import static com.vbs.vbs.enums.UserRoles.*;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public SecurityConfig(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/","/index/*","/css/*","/js/*").permitAll()
//                .antMatchers("/admin/**").hasRole(ADMIN.name())
//                .antMatchers("/client/**").hasRole(CLIENT.name())
//                .antMatchers("/venue/**").hasRole(VENUE.name())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin();
//    }
//
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//    UserDetails venueUser = User.builder()
//              .username("ElonMusk")
//              .password(passwordEncoder.encode("password"))
//              .roles(VENUE.name())  //ROLE_VENUE
//              .build();
//    UserDetails clientUser=User.builder()
//            .username("LionelMessi")
//            .password(passwordEncoder.encode("password1"))
//            .roles(CLIENT.name()) //ROLE_CLIENT
//            .build();
//
//        UserDetails adminUser=User.builder()
//                .username("Ronaldo")
//                .password(passwordEncoder.encode("password12"))
//                .roles(ADMIN.name()) //ROLE_ADMIN
//                .build();
//
//      return new InMemoryUserDetailsManager(
//              venueUser,
//              clientUser,
//              adminUser
//
//      );
//
//    }
//}
