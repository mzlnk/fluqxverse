package io.mzlnk.fluqxverse.authn.api.identity;

import io.mzlnk.fluqxverse.authn.api.identity.dto.IdentityMapper;
import io.mzlnk.fluqxverse.authn.domain.identity.IdentityStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authn/api/v1/identities")
public class IdentityController {

    private final IdentityMapper identityMapper;
    private final IdentityStorage identityStorage;

}
