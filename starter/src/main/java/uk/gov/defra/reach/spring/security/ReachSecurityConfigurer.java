package uk.gov.defra.reach.spring.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "reach.web.security")
public class ReachSecurityConfigurer {

  private String jwtSecret;

  private String[] unsecuredEndpoints = new String[] {"/", "/healthcheck"};

}
