package com.kimheng.apigateway.config;

import java.time.LocalDateTime;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {
	@Bean
	public RouteLocator customerRoute(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/account/**")
						.filters(f -> f.prefixPath("/api")
						.addResponseHeader("X-RESPONSE-TIME", LocalDateTime.now().toString()))
						.uri("lb://ACCOUNT")
						).build();
	}
}
