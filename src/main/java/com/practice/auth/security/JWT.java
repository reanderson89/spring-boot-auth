package com.practice.auth.security;

import com.practice.auth.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JWT {

    // Generate key - in a real-world application you would not generate a new key on each request
    String encodedKey = "zCdQccvxPwCT/nq3KkfePM42K3ufo6KcBCsvwoAByKM=";
    byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
    private Key key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");

    public String generateJWT(User user) {


        // Get current time
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // Set token expiration to 1 hour from now
        long expMillis = nowMillis + 3600000;
        Date exp = new Date(expMillis);

        // Create JWT
        String jwt = Jwts.builder()
                .setIssuer("your-app")
                .setAudience("your-client")
                .setIssuedAt(now)
                .setExpiration(exp)
                .setSubject(user.getUsername())
                .claim("username", user.getUsername())
                .signWith(key)
                .compact();

        // Print out JWT
        System.out.println("JWT: " + jwt);

        try {
            // Parse JWT
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);

            Claims claims = claimsJws.getBody();

            // Print out the claims stored in the JWT
            System.out.println("Issuer: " + claims.getIssuer());
            System.out.println("Audience: " + claims.getAudience());
            System.out.println("Issued at: " + claims.getIssuedAt());
            System.out.println("Expiration: " + claims.getExpiration());
            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Additional Data: " + claims.get("additional-data"));
        } catch (ExpiredJwtException e) {
            System.out.println("JWT has expired");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT");
        } catch (MalformedJwtException e) {
            System.out.println("Malformed JWT");
        } catch (SignatureException e) {
            System.out.println("Invalid signature");
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument");
        }

        return jwt;
    }

    public Jws<Claims> parseJWT(String jwt) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt);
    }
}
