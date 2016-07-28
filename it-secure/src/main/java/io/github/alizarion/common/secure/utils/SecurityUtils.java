package io.github.alizarion.common.secure.utils;


import io.github.alizarion.common.secure.exception.ApplicationError;
import io.github.alizarion.common.secure.exception.BadTokenException;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.login.LoginException;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author selim@openlinux.fr.
 */
public final class SecurityUtils {

    public static Set<Map.Entry<String,Object>> validateToken(final String token) throws LoginException {
        try {
       return Jwts.parser().setSigningKeyResolver(new SigningKeyResolverAdapter() {
            @Override
            public byte[] resolveSigningKeyBytes(JwsHeader header, Claims claims) {
                //inspect the header or claims, lookup and return the signing key

                try {
                    return KeyStoreUtils.loadKey().getEncoded(); //implement me
                } catch (IOException | NoSuchAlgorithmException e) {
                    throw new ApplicationError();
                }
            }
        })
                .parseClaimsJws(token).getBody().entrySet();
        }catch (ExpiredJwtException e){
            throw new LoginException();
        }
    }

    public static String createToken(final String subject,
                                     final String issuer,
                                     final Map<String,Object> claims) throws IOException, NoSuchAlgorithmException {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        //byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(KeyStoreUtils.loadKey().);
        byte[] apiKeySecretBytes = KeyStoreUtils.loadKey().getEncoded();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        if (claims.get("id")!= null){
            builder.setId(claims.get("id").toString());
        }
        for (Map.Entry<String,Object> entry : claims.entrySet()){
                builder.claim(entry.getKey(),entry.getValue());
        }

        //if it has been specified, let's add the expiration
        builder.setExpiration(new Date(new Date().getTime()+100000));

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }


}
