package io.mzlnk.identitybroker.server.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
@Table(name = "USERS")
@NoArgsConstructor
public class User {

    private static final String USERS_SEQUENCE = "USERS_SEQ";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USERS_SEQUENCE)
    private Long id;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<IdentityProviderUser> identityProviderUsers;

}
