package io.mzlnk.identitybroker.server.api.identityprovider;

import io.mzlnk.identitybroker.server.api.identityprovider.dto.IdentityProviderDetails;
import io.mzlnk.identitybroker.server.api.identityprovider.dto.IdentityProviderMapper;
import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/identity-providers")
public class IdentityProviderController {

    private final IdentityProviderMapper identityProviderMapper;
    private final IdentityProviderService identityProviderService;

    @GetMapping
    public ResponseEntity<List<IdentityProviderDetails>> listIdentityProviders() {
        var providers = identityProviderService.listIdentityProviders().stream()
                .map(identityProviderMapper::toIdentityProviderDetails)
                .toList();

        return ResponseEntity.ok(providers);
    }

}
