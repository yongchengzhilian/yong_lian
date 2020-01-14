package com.aidou.util.tool;

import com.aidou.util.exception.ExpiredTokenException;
import com.aidou.util.exception.InvalidTokenException;
import io.jsonwebtoken.*;

import java.util.Calendar;

public class JwtUtils {


    public static Jws<Claims> parseClaims(com.aidou.util.tool.JwtConfig settings, String token) throws InvalidTokenException, ExpiredTokenException {
        try {
            return Jwts.parser().setSigningKey(settings.getTokenSigningKey()).parseClaimsJws(token);
        } catch (NullPointerException| UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            throw new InvalidTokenException();
        } catch (ExpiredJwtException expiredException) {
            throw new ExpiredTokenException();
        }
    }

    public static String createToken(com.aidou.util.tool.JwtConfig settings, Long userId) {
        Claims claims = Jwts.claims().setSubject(userId + "");
        Calendar currentTime  = Calendar.getInstance();
        Calendar expireTime = Calendar.getInstance();
        expireTime.add(Calendar.MINUTE, settings.getTokenExpirationTime());
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(currentTime.getTime())
                .setExpiration(expireTime.getTime())
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();
        return token;

    }
}
