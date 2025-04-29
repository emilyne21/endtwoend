package org.e2e.labe2e01.driver.infrastructure;

import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.user.infrastructure.BaseUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends BaseUserRepository<Driver> {
}
