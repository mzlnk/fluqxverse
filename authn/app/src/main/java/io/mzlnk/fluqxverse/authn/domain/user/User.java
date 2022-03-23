package io.mzlnk.fluqxverse.authn.domain.user;

import io.mzlnk.fluqxverse.authn.domain.identity.Identity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
@Builder
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private static final String USERS_SEQUENCE = "USERS_SEQ";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USERS_SEQUENCE)
    private Long id;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Identity> identities;

}
