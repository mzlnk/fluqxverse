package io.mzlnk.fluqxverse.authn.domain.identity;

import io.mzlnk.fluqxverse.authn.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Builder
@Setter(AccessLevel.PACKAGE)
@Table(name = "IDP_USERS")
@NoArgsConstructor
@AllArgsConstructor
public class Identity {

    private static final String IDP_USERS_SEQUENCE = "IDP_USERS_SEQ";

    @EmbeddedId
    private IdentityId identityId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public String getId() {
        return identityId.getId();
    }

    public IdentityProviderType getIdentityProviderType() {
        return identityId.getIdentityProviderType();
    }

    @Getter
    @Builder
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IdentityId implements Serializable {

        @Column(name = "ID")
        private String id;

        @Column(name = "IDENTITY_PROVIDER")
        @Enumerated(EnumType.STRING)
        private IdentityProviderType identityProviderType;

        public static IdentityId from(String id, IdentityProviderType providerType) {
            return IdentityId.builder()
                    .id(id)
                    .identityProviderType(providerType)
                    .build();
        }

    }

}
