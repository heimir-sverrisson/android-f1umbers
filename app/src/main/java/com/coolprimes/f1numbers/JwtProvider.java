package com.coolprimes.f1numbers;

import android.util.Base64;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

/**
 * Created by Heimir Sverrisson on 01/05/2016.
 */
public class JwtProvider {
    private byte[] key;

    JwtProvider(String stringKey){
        byte[] encodedKey     = Base64.decode(stringKey, Base64.DEFAULT);
        this.key = encodedKey;
    }

    String getJwtString(String subject){
        return Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS512, key).compact();
    }
}
