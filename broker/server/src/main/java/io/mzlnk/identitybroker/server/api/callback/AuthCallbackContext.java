package io.mzlnk.identitybroker.server.api.callback;

public record AuthCallbackContext(String redirectUri,
                                  String nonce) {

}

