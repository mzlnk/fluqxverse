package io.mzlnk.identitybroker.server.application.converter;

import io.mzlnk.identitybroker.server.api.auth.AuthContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class AuthContextConverter implements Converter<String, AuthContext> {

    @Override
    public AuthContext convert(String s) {
        try {
            String[] data = s.split(",");

            String redirectUri = getValue(data, "ruri");
            return new AuthContext(redirectUri);
        } catch (Exception e) {
            log.error("Could not parse auth context", e);
            return null;
        }
    }

    private String getValue(String[] data, String key) {
        for (String d : data) {
            if (d.startsWith(key)) {
                return d.split("=")[1];
            }
        }
        return null;
    }

}
