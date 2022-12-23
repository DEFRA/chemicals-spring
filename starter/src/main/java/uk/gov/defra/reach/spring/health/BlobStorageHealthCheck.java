package uk.gov.defra.reach.spring.health;

import java.util.function.BooleanSupplier;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import uk.gov.defra.reach.ExternalSystem;

public class BlobStorageHealthCheck implements HealthIndicator {

  private final BooleanSupplier healthSupplier;

  public BlobStorageHealthCheck(ExternalSystem externalSystem) {
    healthSupplier = externalSystem.getHealthCheck()::get;
  }

  @Override
  public Health health() {
    if (healthSupplier.getAsBoolean()) {
      return Health.up().build();
    } else {
      return Health.down().build();
    }
  }

}
