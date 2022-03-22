package io.mzlnk.fluqxverse.authn.api.user;

import io.mzlnk.fluqxverse.authn.api.user.dto.UserDetails;
import io.mzlnk.fluqxverse.authn.api.user.dto.UserMapper;
import io.mzlnk.fluqxverse.authn.application.security.context.UserContext;
import io.mzlnk.fluqxverse.authn.domain.user.User;
import io.mzlnk.fluqxverse.authn.domain.user.UserStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authn/api/v1/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserStorage userStorage;

    @GetMapping("/me")
    public ResponseEntity<UserDetails> getUserFromToken() {
        User user = userStorage.getById(UserContext.getUserId());
        return ResponseEntity.ok(userMapper.toUserDetails(user));
    }

}
