package com.todo.app.util;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.todo.app.model.User;
import com.todo.app.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.KeyPair;
 
@Component
public class JwtTokenUtil {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour
 
    @Autowired
    UserRepository userRepository;
    
   JwtBuilder builder = Jwts.builder();
   static KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
 
 
	public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
                .setIssuer("Todo")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
                 
    }
	
	public static Jws<Claims> parseJwt(String jwtString) {
	               

	    Jws<Claims> jwt = Jwts.parserBuilder()
	            .setSigningKey(keyPair.getPrivate())
	            .build()
	            .parseClaimsJws(jwtString);
     System.out.print(jwt);
	    return jwt;
	}
}