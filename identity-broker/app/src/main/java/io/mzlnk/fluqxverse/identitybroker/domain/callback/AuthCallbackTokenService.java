package io.mzlnk.fluqxverse.identitybroker.domain.callback;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.mzlnk.fluqxverse.identitybroker.application.config.callback.AuthCallbackTokenProperties;
import io.mzlnk.fluqxverse.identitybroker.domain.identityprovider.Identity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthCallbackTokenService {

    private static final long ONE_SECOND_IN_MILLIS = 1000;

    private final AuthCallbackTokenProperties tokenProperties;

    public String createAndSignToken(Identity identity) {
        final String sub = identity.getUser().getId().toString();
        final String iss = tokenProperties.getIssuer();
        final Date iat = new Date();
        final Date exp = new Date(iat.getTime() + tokenProperties.getExpirationTime() * ONE_SECOND_IN_MILLIS);

        Algorithm algorithm = Algorithm.RSA256(null, tokenProperties.getPrivateKey());

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
