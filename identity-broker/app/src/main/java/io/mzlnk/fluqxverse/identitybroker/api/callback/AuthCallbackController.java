package io.mzlnk.fluqxverse.identitybroker.api.callback;

import io.mzlnk.fluqxverse.identitybroker.application.config.callback.AuthCallbackProperties;
import io.mzlnk.fluqxverse.identitybroker.domain.callback.AuthDetails;
import io.mzlnk.fluqxverse.identitybroker.domain.callback.AuthCallbackService;
import io.mzlnk.fluqxverse.identitybroker.domain.callback.OAuth2AuthorizationCodeDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.mzlnk.fluqxverse.identitybroker.domain.identity.provider.IdentityProviderType.GITHUB;
import static io.mzlnk.fluqxverse.identitybroker.domain.identity.provider.IdentityProviderType.GOOGLE;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/idb/api/v1/auth")
public class AuthCallbackController {

    private final AuthCallbackProperties authCallbackProperties;
    private final AuthCallbackService authCallbackService;

    @GetMapping("/callback/github")
    public ResponseEntity<Void> handleGitHubCallback(@RequestParam(name = "code") String code,
                                                     @RequestParam(name = "state") AuthCallbackContext context) {
        // TODO: provide verification for nonce retrieved from state
        AuthDetails authDetails = authCallbackService.establishAuthenticity(new OAuth2AuthorizationCodeDetails(code, GITHUB));
        return prepareResponse(authDetails.token(), context);
    }

    @GetMapping("/callback/google")
    public ResponseEntity<Void> handleGoogleCallback(@RequestParam(name = "code") String code,
                                                     @RequestParam(name = "state") AuthCallbackContext context) {
        AuthDetails authDetails = authCallbackService.establishAuthenticity(new OAuth2AuthorizationCodeDetails(code, GOOGLE));
        return prepareResponse(authDetails.token(), context);
    }

    private ResponseEntity<Void> prepareResponse(String token, AuthCallbackContext context) {
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
