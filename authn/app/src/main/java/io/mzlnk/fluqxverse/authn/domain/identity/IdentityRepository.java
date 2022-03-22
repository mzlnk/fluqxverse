package io.mzlnk.fluqxverse.authn.domain.identity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityRepository extends JpaRepository<Identity, Identity.IdentityId> {

}
