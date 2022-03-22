package io.mzlnk.fluqxverse.identitybroker.application.security.auth.credentials;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class HttpCookieTokenReader implements TokenReader {

    @Override
    public String readToken(HttpServletRequest httpRequest) {
        return Optional.ofNullable(httpRequest.getCookies()).stream()
                .flatMap(Arrays::stream)
                .filter(cookie -> cookie.getName().equals("TOKEN"))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }

}
