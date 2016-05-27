package com.coolprimes.f1numbers;

/**
 * Created: Heimir Sverrisson on 01/05/2016.
 */
import android.util.ArrayMap;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtProvider {
    private byte[] byteKey;

    JwtProvider(String stringKey){
        this.byteKey = stringKey.getBytes(StandardCharsets.US_ASCII);
    }

    String getJwtString(String roleName){
        Map<String, Object> claims = new ArrayMap<String, Object>();
        claims.put("role", roleName);
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, byteKey).compact();
    }
}
