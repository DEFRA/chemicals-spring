package uk.gov.defra.reach.spring.security;

import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwt.consumer.JwtContext;
import org.jose4j.keys.HmacKey;

@Slf4j
public class JwtTokenValidator {

  private final JwtConsumer jwtConsumer;

  public JwtTokenValidator(final String jwtSecret) {

    this.jwtConsumer = new JwtConsumerBuilder()
        .setAllowedClockSkewInSeconds(30)
        .setRequireExpirationTime()
        .setVerificationKey(new HmacKey(jwtSecret.getBytes()))
        .setRelaxVerificationKeyValidation()
        .build();
  }

  JwtContext validateToken(String authToken) {
    try {
      return jwtConsumer.process(authToken);
    } catch (InvalidJwtException e) {
      log.error("Invalid JWT token", e);
      return null;
    }
  }
}
