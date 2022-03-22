package io.mzlnk.fluqxverse.authn.api.identity;

import io.mzlnk.fluqxverse.authn.api.identity.dto.IdentityMapper;
import io.mzlnk.fluqxverse.authn.api.identity.dto.IdentityDetails;
import io.mzlnk.fluqxverse.authn.domain.identity.IdentityStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authn/api/v1/identities")
public class IdentityController {

    private final IdentityMapper identityMapper;
    private final IdentityStorage identityStorage;

}
