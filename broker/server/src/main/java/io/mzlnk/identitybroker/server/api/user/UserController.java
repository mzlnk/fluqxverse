package io.mzlnk.identitybroker.server.api.user;

import io.mzlnk.identitybroker.server.api.user.dto.UserDetails;
import io.mzlnk.identitybroker.server.api.user.dto.UserMapper;
import io.mzlnk.identitybroker.server.domain.user.UserStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserStorage userStorage;

    @GetMapping("/me")
    public ResponseEntity<UserDetails> getUserFromToken() {
        // TODO: implement it
        return ResponseEntity.ok(null);
    }

}
