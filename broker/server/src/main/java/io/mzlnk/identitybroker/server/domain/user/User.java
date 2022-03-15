package io.mzlnk.identitybroker.server.domain.user;

import io.mzlnk.identitybroker.server.domain.identity.Identity;
import lombok.*;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "user")
    private Set<Identity> identities;

}
