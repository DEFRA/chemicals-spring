package uk.gov.defra.reach.spring.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-dev.properties")
class JwtTokenValidatorTest {

  @Value("${test.jwt.token}")
  private String testJwtToken;

  @Value("${reach.file.jwt.secret}")
  private String testJwtKey;

  private JwtTokenValidator jwtTokenValidator;

  @BeforeEach
  void setup() {
    jwtTokenValidator = new JwtTokenValidator(testJwtKey);
  }

  @Test
  void validateToken_shouldReturnTrue_whenTokenIsValid() {
    assertThat(jwtTokenValidator.validateToken(testJwtToken)).isNotNull();
  }

  @Test
  void validateToken_shouldReturnFalse_whenTokenIsInvalid() {
    assertThat(jwtTokenValidator.validateToken("invalidToken")).isNull();
  }
}
