package io.mzlnk.identitybroker.server.domain.callback;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.mzlnk.identitybroker.server.application.config.callback.AuthCallbackTokenProperties;
import io.mzlnk.identitybroker.server.domain.identity.Identity;
import lombok.RequiredArgsConstructor;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

@RequiredArgsConstructor
public class AuthCallbackTokenService {

    private static final long ONE_SECOND_IN_MILLIS = 1000;

    private final AuthCallbackTokenProperties tokenProperties;
    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;

    public String createAndSignToken(Identity identity) {
        final String sub = identity.getUser().getId().toString();
        final String iss = tokenProperties.getIssuer();
        final Date iat = new Date();
        final Date exp = new Date(iat.getTime() + tokenProperties.getExpirationTime() * ONE_SECOND_IN_MILLIS);

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
