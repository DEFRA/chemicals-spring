package uk.gov.defra.reach.spring.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import uk.gov.defra.reach.health.ServiceHealthController;
import uk.gov.defra.reach.spring.logging.LoggingConfiguration;
import uk.gov.defra.reach.spring.security.ReachSecurityConfiguration;

@AutoConfigureBefore(name = {"org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration"})
@PropertySource("classpath:/reach-spring-support.properties")
@Import({ReachSecurityConfiguration.class,
         LoggingConfiguration.class,
         ServiceHealthController.class})
public class ReachSpringSupportAutoconfiguration {

}
