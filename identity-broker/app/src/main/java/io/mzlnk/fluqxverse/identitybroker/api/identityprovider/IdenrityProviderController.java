package io.mzlnk.fluqxverse.identitybroker.api.identityprovider;

import io.mzlnk.fluqxverse.identitybroker.api.identityprovider.dto.IdentityProviderDetails;
import io.mzlnk.fluqxverse.identitybroker.api.identityprovider.dto.IdentityProviderMapper;
import io.mzlnk.fluqxverse.identitybroker.domain.identityprovider.IdentityProviderStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/idb/api/v1/identity-providers")
public class IdenrityProviderController {

    private final IdentityProviderMapper identityProviderMapper;
    private final IdentityProviderStorage identityProviderStorage;

    @GetMapping
    public ResponseEntity<List<IdentityProviderDetails>> list() {
        var providers = identityProviderStorage.listIdentityProviders().stream()
                .map(identityProviderMapper::toIdentityProviderDetails)
                .toList();

        return ResponseEntity.ok(providers);
    }

}
