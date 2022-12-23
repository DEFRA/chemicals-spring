package uk.gov.defra.reach.spring.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(ReachSecurityConfigurer.class)
public class ReachSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final ReachSecurityConfigurer reachSecurityConfigurer;

  public ReachSecurityConfiguration(ReachSecurityConfigurer reachSecurityConfigurer) {
    this.reachSecurityConfigurer = reachSecurityConfigurer;
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers(reachSecurityConfigurer.getUnsecuredEndpoints());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().disable().csrf().disable().authorizeRequests()
        .anyRequest().authenticated()
        .and().exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        .and().addFilter(jwtAuthenticationFilter()).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  private JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
    return new JwtAuthenticationFilter(authenticationManager(), jwtTokenValidator());
  }

  private JwtTokenValidator jwtTokenValidator() {
    return new JwtTokenValidator(reachSecurityConfigurer.getJwtSecret());
  }

}
