package io.mzlnk.identitybroker.server.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserStorage {

    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User create(CreateUserDetails createDetails) {
        User user = User.builder()
                .email(createDetails.email())
                .build();

        return userRepository.save(user);
    }

    public static record CreateUserDetails(String email) {

    }

}
