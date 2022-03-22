package io.mzlnk.fluqxverse.identitybroker.domain.identity.provider;

import java.util.function.Supplier;

public class IdentityProviderNotSupportedException extends RuntimeException {

    private static final String MESSAGE = "Identity provider for `%s` is not supported";

    public IdentityProviderNotSupportedException(IdentityProviderType providerType) {
        super(MESSAGE.formatted(providerType.getBrandName()));
    }

    public static Supplier<IdentityProviderNotSupportedException> identityProviderNotSupported(IdentityProviderType providerType) {
        return () -> new IdentityProviderNotSupportedException(providerType);
    }

}
