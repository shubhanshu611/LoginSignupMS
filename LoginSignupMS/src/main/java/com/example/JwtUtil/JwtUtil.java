package com.example.JwtUtil;





import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;


    public String parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

//            User u = new User();
//            u.setUsername(body.getSubject());
//            u.setId(Long.parseLong((String) body.get("userId")));
//            u.setRole((String) body.get("role"));

            String username = body.getSubject();
            return username;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    public boolean validateToken(String jwt, UserDetails user) {
        return this.parseToken(jwt).equalsIgnoreCase(user.getUsername());
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param user the user for which the token will be generated
     * @return the JWT token
     */
    public String generateToken(UserDetails user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
//        claims.put("userId", u.getId() + "");
        claims.put("role", user.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


}
