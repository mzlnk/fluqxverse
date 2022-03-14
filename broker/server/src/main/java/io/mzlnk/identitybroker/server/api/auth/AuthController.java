package io.mzlnk.identitybroker.server.api.auth;

import io.mzlnk.identitybroker.server.domain.auth.OAuth2ExchangeService;
import io.mzlnk.identitybroker.server.domain.identityprovider.IdentityProviderType;
import io.mzlnk.oauth2.exchange.core.authorizationcode.FacebookAuthorizationCodeExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final OAuth2ExchangeService oAuth2ExchangeService;

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
    public ResponseEntity<Void> handleGoogleCallback(@RequestParam(name = "code") String code) {
        // TODO: implement it
        return ResponseEntity.ok(null);
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

}
