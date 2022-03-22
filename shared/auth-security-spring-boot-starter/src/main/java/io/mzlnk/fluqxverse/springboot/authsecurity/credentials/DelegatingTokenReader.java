package io.mzlnk.fluqxverse.springboot.authsecurity.credentials;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

public class DelegatingTokenReader implements TokenReader {

    public static DelegatingTokenReaderBuilder builder() {
        return new DelegatingTokenReaderBuilder();
    }

    private final Set<TokenReader> delegates;

    private DelegatingTokenReader(Set<TokenReader> delegates) {
        this.delegates = delegates;
    }

    @Override
    public String readToken(HttpServletRequest httpRequest) {
        for (TokenReader delegate : delegates) {
            String token = delegate.readToken(httpRequest);
            if (token != null) {
                return token;
            }
        }
        return null;
    }

    public static class DelegatingTokenReaderBuilder {

        private final Set<TokenReader> delegates = new HashSet<>();

        public DelegatingTokenReaderBuilder delegate(TokenReader delegate) {
            this.delegates.add(delegate);
            return this;
        }

        public DelegatingTokenReader build() {
            return new DelegatingTokenReader(this.delegates);
        }

    }

}
