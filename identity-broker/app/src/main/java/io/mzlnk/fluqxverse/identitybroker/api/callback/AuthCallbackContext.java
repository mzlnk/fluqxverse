package io.mzlnk.fluqxverse.identitybroker.api.callback;

public record AuthCallbackContext(String redirectUri,
                                  String nonce) {

}

