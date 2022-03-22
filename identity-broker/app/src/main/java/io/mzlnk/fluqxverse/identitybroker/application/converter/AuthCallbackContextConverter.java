package io.mzlnk.fluqxverse.identitybroker.application.converter;

import io.mzlnk.fluqxverse.identitybroker.api.callback.AuthCallbackContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class AuthCallbackContextConverter implements Converter<String, AuthCallbackContext> {

    @Override
    public AuthCallbackContext convert(String s) {
        try {
            String[] data = s.split(",");

            String redirectUri = getValue(data, "ruri", "");
            String nonce = getValue(data, "nce", "");
            return new AuthCallbackContext(redirectUri, nonce);
        } catch (Exception e) {
            log.error("Could not parse auth context", e);
            return null;
        }
    }

    private String getValue(String[] data, String key, String defaultValue) {
        for (String d : data) {
            if (d.startsWith(key)) {
                return d.split("=")[1];
            }
        }
        return defaultValue;
    }

}
