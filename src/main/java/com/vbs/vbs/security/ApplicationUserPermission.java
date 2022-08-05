package com.vbs.vbs.security;

public enum ApplicationUserPermission {
    CLIENT_READ("client:read"),
    CLIENT_WRITE("client:write"),
    VENUE_READ("venue:read"),
    VENUE_WRITE("venue:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
