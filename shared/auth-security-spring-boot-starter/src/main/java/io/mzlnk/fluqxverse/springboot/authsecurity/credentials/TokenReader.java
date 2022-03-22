package io.mzlnk.fluqxverse.springboot.authsecurity.credentials;

import javax.servlet.http.HttpServletRequest;

public interface TokenReader {

    String readToken(HttpServletRequest httpRequest);

}
