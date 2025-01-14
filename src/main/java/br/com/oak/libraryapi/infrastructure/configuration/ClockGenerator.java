package br.com.oak.libraryapi.infrastructure.configuration;

import java.time.Clock;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ClockGenerator {

  public Clock clock() {
    return Clock.systemUTC();
  }
}
