package com.microservices.mscart;

import java.time.Duration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@SpringBootApplication
@EnableEurekaClient
public class MsCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCartApplication.class, args);
	}

	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
			.failureRateThreshold(50)
			.waitDurationInOpenState(Duration.ofMillis(1000))
			.slidingWindowSize(2)
			.build();
		TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
			.timeoutDuration(Duration.ofSeconds(4))
			.build();

		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
			.timeLimiterConfig(timeLimiterConfig)
			.circuitBreakerConfig(circuitBreakerConfig)
			.build());
	}
}
