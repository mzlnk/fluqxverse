package io.mzlnk.identitybroker.server.application.security.auth.credentials;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Builder
@RequiredArgsConstructor
public class DelegatingTokenReader implements TokenReader {

    @Singular
    private final List<TokenReader> delegates;

    @Override
    public String readToken(HttpServletRequest httpRequest) {
        for(TokenReader delegate : delegates) {
            String token = delegate.readToken(httpRequest);
            if(token != null) {
                return token;
            }
        }
        return null;
    }
}
