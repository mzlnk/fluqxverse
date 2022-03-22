package io.mzlnk.fluqxverse.identitybroker.domain.identity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityRepository extends JpaRepository<Identity, Identity.IdentityId> {

}
