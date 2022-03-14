package io.mzlnk.identitybroker.server.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityProviderUserRepository extends JpaRepository<IdentityProviderUser, String> {

}
