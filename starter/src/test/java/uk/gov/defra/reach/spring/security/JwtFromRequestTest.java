package uk.gov.defra.reach.spring.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

class JwtFromRequestTest {

  private MockHttpServletRequest request;

  @BeforeEach
  void setup() {
    request = new MockHttpServletRequest();
  }

  @Test
  void getJwtFromRequest_returnsToken_whenValid() {
    request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer token123");

    String result = JwtFromRequest.getJwtFromRequest(request);
    assertThat(result).isEqualTo("token123");
  }

  @Test
  void getJwtFromRequest_returnsNull_whenBearerMissing() {
    request = new MockHttpServletRequest();
    request.addHeader(HttpHeaders.AUTHORIZATION, "token123");

    String result = JwtFromRequest.getJwtFromRequest(request);
    assertThat(result).isNull();
  }

  @Test
  void getJwtFromRequest_returnsNull_whenInvalid() {
    request.addHeader(HttpHeaders.AUTHORIZATION, "Bear token123");

    String result = JwtFromRequest.getJwtFromRequest(request);
    assertThat(result).isNull();
  }
}
