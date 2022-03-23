package io.mzlnk.fluqxverse.authn.api.identity;

import io.mzlnk.fluqxverse.authn.api.identity.dto.IdentityCreateRequest;
import io.mzlnk.fluqxverse.authn.api.identity.dto.IdentityMapper;
import io.mzlnk.fluqxverse.authn.api.identity.dto.IdentityResponse;
import io.mzlnk.fluqxverse.authn.domain.identity.Identity;
import io.mzlnk.fluqxverse.authn.domain.identity.IdentityStorage;
import io.mzlnk.fluqxverse.authn.domain.user.User;
import io.mzlnk.fluqxverse.authn.domain.user.UserStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authn/api/v1/identities")
public class IdentityController {

    private final IdentityMapper identityMapper;

    private final IdentityStorage identityStorage;
    private final UserStorage userStorage;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<IdentityResponse> create(@RequestBody @Valid IdentityCreateRequest createRequest) {
        User user = userStorage.getById(createRequest.userId());

        var createDetails = new IdentityStorage.IdentityCreateDetails(createRequest.id(), createRequest.provider(), user);
        Identity identity = identityStorage.createIdentity(createDetails);

        return ResponseEntity.ok(identityMapper.toIdentityResponse(identity));
    }

}
