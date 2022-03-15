package io.mzlnk.identitybroker.server.api.auth;

import io.mzlnk.identitybroker.server.application.auth.AuthCallbackProperties;
import io.mzlnk.identitybroker.server.application.auth.jwt.JwtService;
import io.mzlnk.identitybroker.server.domain.identity.Identity;
import io.mzlnk.identitybroker.server.domain.identity.IdentityManager;
import io.mzlnk.identitybroker.server.domain.identity.OAuth2AuthorizationCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType.GOOGLE;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthCallbackProperties authCallbackProperties;
    private final IdentityManager identityManager;
    private final JwtService jwtService;

    @GetMapping("/callback/facebook")
    public ResponseEntity<Void> handleFacebookCallback(@RequestParam(name = "code") String code) {
        // TODO: implement it
        return ResponseEntity.ok(null);
    }

    @GetMapping("/callback/github")
    public ResponseEntity<Void> handleGitHubCallback(@RequestParam(name = "code") String code) {
        // TODO: implement it
        return ResponseEntity.ok(null);
    }

    @GetMapping("/callback/google")
    public ResponseEntity<Void> handleGoogleCallback(@RequestParam(name = "code") String code,
                                                     @RequestParam(name = "state") AuthContext context) {
        Identity identity = identityManager.establishIdentity(new OAuth2AuthorizationCode(code, GOOGLE));
        String token = jwtService.createAndSignToken(identity);

        return prepareResponse(token, context);
    }

    @GetMapping("/callback/keycloak")
    public ResponseEntity<Void> handleKeycloakCallback(@RequestParam(name = "code") String code) {
        // TODO: implement it
        return ResponseEntity.ok(null);
    }

    @GetMapping("/callback/microsoft")
    public ResponseEntity<Void> handleMicrosoftCallback(@RequestParam(name = "code") String code) {
        // TODO: implement it
        return ResponseEntity.ok(null);
    }

    @GetMapping("/callback/okta")
    public ResponseEntity<Void> handleOktaCallback(@RequestParam(name = "code") String code) {
        // TODO: implement it
        return ResponseEntity.ok(null);
    }

    private ResponseEntity<Void> prepareResponse(String token, AuthContext context) {
        var cookieParams = authCallbackProperties.getCookie();

        ResponseCookie cookie = ResponseCookie.from(cookieParams.getName(), token)
                .domain(cookieParams.getDomain())
                .path(cookieParams.getPath())
                .sameSite(cookieParams.getSameSite())
                .httpOnly(cookieParams.getHttpOnly())
                .secure(cookieParams.getSecure())
                .maxAge(cookieParams.getMaxAge())
                .build();

        return ResponseEntity.status(MOVED_PERMANENTLY)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .header(HttpHeaders.LOCATION, context.redirectUri())
                .build();
    }

}
