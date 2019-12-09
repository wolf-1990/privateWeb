package com.bluesky.zhz.core.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {
	
	@Value("${customer.token.periodofvalidity.millisecond}")
    private long periodOfValidity;
	@Value("${customer.token.privatekey}")
    private String privateKey;
	@Value("${customer.token.subject}")
    private String subject;
//	private static final String privateKey = "7786df7fc3a34e26a61c034d5ec8245d";

	public String createToken(String userId) {
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	    long nowMillis = System.currentTimeMillis();
	    long ttlMillis = nowMillis + periodOfValidity;
	    Date now = new Date(nowMillis);
	    Date exp = new Date(ttlMillis);
	    HashMap<String, Object> claims = new HashMap<String, Object>();
	    claims.put("userId", userId);
	    JwtBuilder jwtBuilder = Jwts.builder()
	            .setClaims(claims)
	            .setId(UUID.randomUUID().toString())
	            .setIssuedAt(now)
	            .setExpiration(exp)
	            .setSubject(subject)
	            .signWith(signatureAlgorithm, generalKey());
	    return jwtBuilder.compact();
	}
	
	public SecretKey generalKey(){
        byte[] encodedKey = Base64.decodeBase64(privateKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
	
	/**
     * 解密jwt
     * @param jwt
     * @return
     * @throws Exception
     */
    public String parseJWT(String jwt) throws Exception{
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
           .setSigningKey(key)
           .parseClaimsJws(jwt).getBody();
        return (String) claims.get("userId");
    }
    
}
