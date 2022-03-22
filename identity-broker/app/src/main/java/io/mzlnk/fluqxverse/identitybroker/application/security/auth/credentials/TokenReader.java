package io.mzlnk.fluqxverse.identitybroker.application.security.auth.credentials;

import javax.servlet.http.HttpServletRequest;

public interface TokenReader {

    String readToken(HttpServletRequest httpRequest);

}
