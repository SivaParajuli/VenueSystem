package com.vbs.vbs.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.vbs.vbs.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(ADMIN_READ,ADMIN_WRITE)),
    CLIENT(Sets.newHashSet(CLIENT_READ,CLIENT_WRITE)),
    VENUE(Sets.newHashSet(VENUE_READ,VENUE_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public  Set<SimpleGrantedAuthority> getGrantedAuthorities(){
         Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission-> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
         permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
         return permissions;
    }
}
