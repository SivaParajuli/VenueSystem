package com.vbs.vbs.security;

import com.vbs.vbs.auth.ApplicationUserService;
import com.vbs.vbs.jwt.JwtConfig;
import com.vbs.vbs.jwt.JwtTokenVerifier;
import com.vbs.vbs.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final ApplicationUserService applicationUserService;
    @Autowired
    private final SecretKey secretKey;
    @Autowired
    private final JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig,secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey,jwtConfig),JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/","index","/css/**","/js/**","/client/register/**","/venue/create/**","/client/**","/login/**").permitAll()
//                .antMatchers("/client/api/**").hasRole(CUSTOMER.name())
//                .antMatchers("/owner/api/**").hasRole(OWNER.name())
//                .antMatchers("/admin/api/**").hasRole(ADMIN.name())
//                .antMatchers(HttpMethod.DELETE,"/client/api/**").hasAuthority(CUSTOMER_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/client/api/**").hasAuthority(CUSTOMER_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/client/api/**").hasAuthority(CUSTOMER_WRITE.getPermission())
//                .antMatchers(HttpMethod.DELETE,"/venue/api/**").hasAuthority(OWNER_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/venue/api/**").hasAuthority(OWNER_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/venue/api/**").hasAuthority(OWNER_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/admin/api/**").hasAuthority(ADMIN_READ.getPermission())
//                .antMatchers(HttpMethod.GET,"/admin/api/**").hasAnyRole(CUSTOMER_READ.name(),ADMIN_READ.name())
                .anyRequest()
                .authenticated();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
}
