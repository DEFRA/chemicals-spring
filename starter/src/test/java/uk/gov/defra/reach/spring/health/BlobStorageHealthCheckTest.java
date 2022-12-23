package uk.gov.defra.reach.spring.health;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import uk.gov.defra.reach.ExternalSystem;

class BlobStorageHealthCheckTest {

  @Test
  void returnsDownWhenStorageUnhealthy() {
    Health health = new BlobStorageHealthCheck(new ExternalSystem() {
      @Override
      public Supplier<Boolean> getHealthCheck() {
        return () -> false;
      }
    }).health();

    assertThat(health.getStatus()).isEqualTo(Status.DOWN);
  }

  @Test
  void returnsUpWhenStorageHealthy() {
    Health health = new BlobStorageHealthCheck(new ExternalSystem() {
      @Override
      public Supplier<Boolean> getHealthCheck() {
        return () -> true;
      }
    }).health();

    assertThat(health.getStatus()).isEqualTo(Status.UP);
  }

}
