package io.mzlnk.fluqxverse.authn.api.user;

import io.mzlnk.fluqxverse.authn.api.user.dto.UserCreateRequest;
import io.mzlnk.fluqxverse.authn.api.user.dto.UserResponse;
import io.mzlnk.fluqxverse.authn.api.user.dto.UserMapper;
import io.mzlnk.fluqxverse.authn.domain.user.User;
import io.mzlnk.fluqxverse.authn.domain.user.UserStorage;
import io.mzlnk.fluqxverse.springboot.authsecurity.context.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authn/api/v1/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserStorage userStorage;

    @GetMapping(path = "/me", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getByToken() {
        User user = userStorage.getById(UserContext.getUserId());
        return ResponseEntity.ok(userMapper.toUserResponse(user));
    }

    @GetMapping(path = "/email/{email}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getByEmail(@PathVariable @Valid @Email String email) {
        User user = userStorage.getByEmail(email);

        return ResponseEntity.ok(userMapper.toUserResponse(user));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserCreateRequest createRequest) {
        UserStorage.CreateUserDetails createDetails = new UserStorage.CreateUserDetails(createRequest.email(), createRequest.username());
        User user = userStorage.create(createDetails);

        return ResponseEntity.ok(userMapper.toUserResponse(user));
    }

}
