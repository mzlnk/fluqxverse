package io.mzlnk.identitybroker.server.domain.identity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityRepository extends JpaRepository<Identity, Identity.IdentityId> {

}
