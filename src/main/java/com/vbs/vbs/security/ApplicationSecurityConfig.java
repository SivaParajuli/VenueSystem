package com.vbs.vbs.security;

import com.vbs.vbs.auth.ApplicationUserService;
import com.vbs.vbs.jwt.JwtConfig;
import com.vbs.vbs.jwt.JwtTokenVerifier;
import com.vbs.vbs.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.crypto.SecretKey;
import java.util.Arrays;


@Configuration
@CrossOrigin(origins = "*")
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


        http.csrf().disable();
        http
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig,secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey,jwtConfig),JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests((request)->request
                        .antMatchers("/","index","/css/**","/js/**","/client/register/**","/venue/**").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET,"/login").permitAll().anyRequest()
                        .authenticated())

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
                ;
    }

@Bean
public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        config.addAllowedHeader("Access-Control-Allow-Origin");
        config.addAllowedHeader("cache-control");
        config.addAllowedHeader("authentication");
        config.addAllowedHeader("access-control-allow-headers");
        config.addAllowedHeader("access-control-allow-methods");
        config.addAllowedHeader("credentials");
        config.addExposedHeader("Access-Control-Allow-Credentials");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
        }

@Bean
public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
        }


    @Bean
    @CrossOrigin(origins = "*")
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    @Override
    @CrossOrigin(origins = "*")
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
}
