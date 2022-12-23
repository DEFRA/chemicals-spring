package uk.gov.defra.reach.spring.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.net.URI;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uk.gov.defra.reach.spring.logging.ReachClientHttpRequestInterceptor;

@ExtendWith(MockitoExtension.class)
class ReachClientHttpRequestInterceptorTest {

  private static final String JWT_TOKEN = "DUMMY JWT";

  private ReachClientHttpRequestInterceptor headerInterceptor = new ReachClientHttpRequestInterceptor();

  @Mock
  private ClientHttpRequestExecution execution;

  @Test
  @SneakyThrows
  void shouldSetHeaders() {
    mockAuthentication();
    mockMdc();
    ClientHttpRequest request = new SimpleClientHttpRequestFactory().createRequest(URI.create("http://somewhere.com"), HttpMethod.GET);
    headerInterceptor.intercept(request, new byte[0], execution);

    assertThat(request.getHeaders().get(HttpHeaders.AUTHORIZATION)).containsExactly("Bearer DUMMY JWT");
    assertThat(request.getHeaders().get("correlation-id")).containsExactly("correlation id 1");
    assertThat(request.getHeaders().get("x-remote-user")).containsExactly("user 1");
    assertThat(request.getHeaders().get("x-forwarded-for")).containsExactly("0.0.0.0");

    MDC.clear();
  }

  private static void mockAuthentication() {
    Authentication authentication = Mockito.mock(Authentication.class);
    when(authentication.getCredentials()).thenReturn(JWT_TOKEN);
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  private static void mockMdc() {
    MDC.put("correlation-id", "correlation id 1");
    MDC.put("userid", "user 1");
    MDC.put("x-forwarded-for", "0.0.0.0");
  }

}
