package io.mzlnk.fluqxverse.identitybroker.api.identity;

import io.mzlnk.fluqxverse.identitybroker.api.identity.dto.IdentityProviderMapper;
import io.mzlnk.fluqxverse.identitybroker.domain.identity.provider.IdentityProviderStorage;
import io.mzlnk.fluqxverse.identitybroker.api.identity.dto.IdentityProviderDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/idb/api/v1/identity-providers")
public class IdentityProviderController {

    private final IdentityProviderMapper identityProviderMapper;
    private final IdentityProviderStorage identityProviderStorage;

    @GetMapping
    public ResponseEntity<List<IdentityProviderDetails>> listIdentityProviders() {
        var providers = identityProviderStorage.listIdentityProviders().stream()
                .map(identityProviderMapper::toIdentityProviderDetails)
                .toList();

        return ResponseEntity.ok(providers);
    }

}
