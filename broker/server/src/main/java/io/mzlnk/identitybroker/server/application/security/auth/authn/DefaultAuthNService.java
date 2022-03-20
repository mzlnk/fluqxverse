package io.mzlnk.identitybroker.server.application.security.auth.authn;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Payload;
import io.mzlnk.identitybroker.server.application.config.callback.AuthCallbackTokenProperties;
import io.mzlnk.identitybroker.server.application.security.auth.credentials.JwtAuthCredentials;
import io.mzlnk.identitybroker.server.application.security.auth.credentials.TokenReader;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.security.interfaces.RSAPublicKey;
import java.util.Optional;

public class DefaultAuthNService implements AuthNService {

    private final TokenReader tokenReader;
    private final JWTVerifier jwtVerifier;

    public DefaultAuthNService(TokenReader tokenReader,
                               AuthCallbackTokenProperties tokenProperties,
                               RSAPublicKey publicKey) {
        this.tokenReader = tokenReader;
        this.jwtVerifier = JWT.require(Algorithm.RSA256(publicKey, null))
                .withIssuer(tokenProperties.getIssuer())
                .build();
    }

    @Override
    public Long getPrincipal(HttpServletRequest httpRequest) {
        return Optional.ofNullable(tokenReader.readToken(httpRequest))
                .map(JWT::decode)
                .map(Payload::getSubject)
                .map(Long::parseLong)
                .orElseThrow(() -> new PreAuthenticatedCredentialsNotFoundException("Unable to authenticate user"));
    }

    @Override
    public JwtAuthCredentials getCredential(HttpServletRequest httpRequest) {
        try {
            String token = tokenReader.readToken(httpRequest);
            DecodedJWT decoded = jwtVerifier.verify(token);

            Long userId = Long.parseLong(decoded.getSubject());

            return new JwtAuthCredentials(token, userId);
        } catch (JWTVerificationException e) {
            throw new BadCredentialsException("Unable to authenticate user", e);
        }
    }

}
