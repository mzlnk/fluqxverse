package io.mzlnk.fluqxverse.identitybroker.application.security.auth.credentials;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class HttpHeaderTokenReader implements TokenReader {

    @Override
    public String readToken(HttpServletRequest httpRequest) {
        return Optional.ofNullable(httpRequest.getHeader("Authorization"))
                .map(header -> header.substring(7))
                .orElse(null);
    }

}
