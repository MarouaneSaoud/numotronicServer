package org.sid.config;

public final class SecurityParams {
    public static final String JWT_HEADER_NAME = "Authorization";
    public static final String SECRET = "numo";
    public static final long EXPIRATION = 10 * 24 * 3600;
    public static final String HEADER_PREFIX = "Bearer ";

    private SecurityParams() {
    }
}
