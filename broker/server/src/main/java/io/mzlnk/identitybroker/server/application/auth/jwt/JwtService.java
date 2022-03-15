package io.mzlnk.identitybroker.server.application.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.mzlnk.identitybroker.server.domain.identity.Identity;
import lombok.RequiredArgsConstructor;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

@RequiredArgsConstructor
public class JwtService {

    private final JwtTokenProperties jwtTokenProperties;
    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;

    public String createAndSignToken(Identity identity) {
        final String sub = identity.getUser().getId().toString();
        final String iss = jwtTokenProperties.getIssuer();
        final Date iat = new Date();
        final Date exp = new Date(iat.getTime() + jwtTokenProperties.getExpirationTime());

        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);

        return JWT.create()
                .withIssuer(iss)
                .withIssuedAt(iat)
                .withExpiresAt(exp)
                .withSubject(sub)
                .sign(algorithm);
    }

    public DecodedJWT decodeToken(String jwtToken) {
        return JWT.decode(jwtToken);
    }

}
