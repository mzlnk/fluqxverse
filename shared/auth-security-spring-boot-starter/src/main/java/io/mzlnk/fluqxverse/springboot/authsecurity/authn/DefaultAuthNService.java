package io.mzlnk.fluqxverse.springboot.authsecurity.authn;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Payload;
import io.mzlnk.fluqxverse.springboot.authsecurity.credentials.JwtAuthCredentials;
import io.mzlnk.fluqxverse.springboot.authsecurity.credentials.TokenProperties;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

import java.util.Optional;

public class DefaultAuthNService implements AuthNService {

    private final JWTVerifier jwtVerifier;

    public DefaultAuthNService(TokenProperties tokenProperties) {
        this.jwtVerifier = JWT.require(Algorithm.RSA256(tokenProperties.getPublicKey(), null))
                .withIssuer(tokenProperties.getIssuer())
                .build();
    }

    @Override
    public Long getPrincipal(String token) {
        return Optional.ofNullable(token)
                .map(JWT::decode)
                .map(Payload::getSubject)
                .map(Long::parseLong)
                .orElseThrow(() -> new PreAuthenticatedCredentialsNotFoundException("Unable to authenticate user"));
    }

    @Override
    public JwtAuthCredentials getCredentials(String token) {
        try {
            DecodedJWT decoded = jwtVerifier.verify(token);

            Long userId = Long.parseLong(decoded.getSubject());

            return new JwtAuthCredentials(token, userId);
        } catch (JWTVerificationException e) {
            throw new BadCredentialsException("Unable to authenticate user", e);
        }
    }

}
