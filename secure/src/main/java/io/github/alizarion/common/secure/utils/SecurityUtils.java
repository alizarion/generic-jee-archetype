package io.github.alizarion.common.secure.utils;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Date;
import java.util.Map;

/**
 * @author selim@openlinux.fr.
 */
public final class SecurityUtils {

    public static void validateToken(final String token){

    }

    public static String createToken(final Map<String,String> claims){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(application.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(claims.get("id"))
                .setIssuedAt(now)
                .setSubject("titi")
                .setIssuer("toto")
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        builder.setExpiration(new Date(new Date().getTime()+100000));

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }


}
