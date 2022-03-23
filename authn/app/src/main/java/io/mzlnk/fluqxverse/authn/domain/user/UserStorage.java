package io.mzlnk.fluqxverse.authn.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

import static io.mzlnk.fluqxverse.authn.domain.user.UserNotFoundException.userNotFound;

@Service
@RequiredArgsConstructor
public class UserStorage {

    private final UserRepository userRepository;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(userNotFound(email));
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(userNotFound(id));
    }

    public User create(CreateUserDetails createDetails) {
        User user = User.builder()
                .email(createDetails.email())
                .username(createDetails.username())
                .identities(Collections.emptySet())
                .build();

        return userRepository.save(user);
    }

    public User update(UpdateUserDetails updateDetails) {
        User user = userRepository.findById(updateDetails.id()).orElseThrow(userNotFound(updateDetails.id()));

        user.setUsername(updateDetails.username());

        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public static record CreateUserDetails(String email,
                                           String username) { }

    public static record UpdateUserDetails(Long id,
                                           String username) { }

}
