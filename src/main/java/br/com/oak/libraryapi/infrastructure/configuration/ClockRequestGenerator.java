package br.com.oak.libraryapi.infrastructure.configuration;


import jakarta.servlet.http.HttpServletRequest;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
@Profile("dev")
public class ClockRequestGenerator {

  @Bean
  @RequestScope
  public Clock clock(HttpServletRequest request) {
    String addDays = request.getParameter("addDays");
    if (addDays != null) {
      Instant instant = Instant.now()
          .plus(Integer.parseInt(addDays), ChronoUnit.DAYS);
      return Clock.fixed(instant,
          ZoneId.systemDefault());
    }
    return Clock.systemUTC();
  }
}
