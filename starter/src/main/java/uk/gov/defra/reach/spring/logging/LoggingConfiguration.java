package uk.gov.defra.reach.spring.logging;

import javax.servlet.Filter;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {

  @Bean
  public RestTemplateCustomizer restTemplateCustomizer() {
    return restTemplate -> restTemplate.getInterceptors().add(new ReachClientHttpRequestInterceptor());
  }

  @Bean
  public Filter loggingFilter() {
    return new IncomingRequestFilter();
  }

}
