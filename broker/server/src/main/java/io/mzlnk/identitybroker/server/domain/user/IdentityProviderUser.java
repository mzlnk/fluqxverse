package io.mzlnk.identitybroker.server.domain.user;

import io.mzlnk.identitybroker.server.domain.identityprovider.IdentityProviderType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
@Table(name = "IDP_USERS")
@NoArgsConstructor
public class IdentityProviderUser {

    private static final String IDP_USERS_SEQUENCE = "IDP_USERS_SEQ";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = IDP_USERS_SEQUENCE)
    private String id;

    @Column(name = "IDENTITY_PROVIDER")
    @Enumerated(EnumType.STRING)
    private IdentityProviderType identityProviderType;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

}
