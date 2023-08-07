package org.numo.config;

public final class SecurityParams {
    public static final String JWT_HEADER_NAME = "Authorization";
    public static final String SECRET = "numo";
    public static final long EXPIRATION = 86400000;
    public static final String HEADER_PREFIX = "Bearer ";

    private SecurityParams() {
    }
}
