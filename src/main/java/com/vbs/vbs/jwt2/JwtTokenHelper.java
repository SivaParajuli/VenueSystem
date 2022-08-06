//package com.vbs.vbs.jwt2;
//
//import com.vbs.vbs.auth.ApplicationUser;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.security.spec.InvalidKeySpecException;
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.Set;
//
//@Component
//public class JwtTokenHelper {
//
//    @Autowired
//    private final JwtSecretKey jwtSecretKey;
//    @Autowired
//    private final JwtConfig jwtConfig;
//
//    public JwtTokenHelper(JwtSecretKey jwtSecretKey, JwtConfig jwtConfig) {
//        this.jwtSecretKey = jwtSecretKey;
//        this.jwtConfig = jwtConfig;
//    }
//
//
//    private Claims getAllClaimsFromToken(String token) {
//        Claims claims;
//        try {
//            claims = Jwts.parser()
//                    .setSigningKey(jwtSecretKey.secretKey())
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch (Exception e) {
//            claims = null;
//        }
//        return claims;
//    }
//
//
//    public String getUsernameFromToken(String token) {
//        String username;
//        try {
//            final Claims claims = this.getAllClaimsFromToken(token);
//            username = claims.getSubject();
//        } catch (Exception e) {
//            username = null;
//        }
//        return username;
//    }
//
//    public String generateToken(String username) throws InvalidKeySpecException {
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration((java.sql.Date.valueOf(LocalDate.now().plusWeeks(jwtConfig.getTokenExpirationAfterDays()))))
//                .signWith(jwtSecretKey.secretKey())
//                .compact();
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = getUsernameFromToken(token);
//        return (
//                username != null &&
//                        username.equals(userDetails.getUsername()) &&
//                        !isTokenExpired(token)
//        );
//    }
//
//    public boolean isTokenExpired(String token) {
//        Date expireDate=getExpirationDate(token);
//        return expireDate.before(new Date());
//    }
//
//
//    private Date getExpirationDate(String token) {
//        Date expireDate;
//        try {
//            final Claims claims = this.getAllClaimsFromToken(token);
//            expireDate = claims.getExpiration();
//        } catch (Exception e) {
//            expireDate = null;
//        }
//        return expireDate;
//    }
//
//
//    public Date getIssuedAtDateFromToken(String token) {
//        Date issueAt;
//        try {
//            final Claims claims = this.getAllClaimsFromToken(token);
//            issueAt = claims.getIssuedAt();
//        } catch (Exception e) {
//            issueAt = null;
//        }
//        return issueAt;
//    }
//
//    public String getToken( HttpServletRequest request ) {
//
//        String authHeader = getAuthHeaderFromHeader( request );
//        if ( authHeader != null && authHeader.startsWith("Bearer ")) {
//            return authHeader.substring(7);
//        }
//
//        return null;
//    }
//
//    public String getAuthHeaderFromHeader( HttpServletRequest request ) {
//        return request.getHeader("Authorization");
//    }
//}
