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
				.route("account-route", p -> p
	                    .path("/account/**")
	                    .filters(f -> f.prefixPath("/api")
	                            // FIX: Uses a lambda to evaluate the current time per request
	                            .addRequestHeader("X-RESPONSE-TIME", LocalDateTime.now().toString()))
	                    .uri("lb://ACCOUNT"))
	            
	            .route("customer-route", p -> p
	                    .path("/customer/**")
	                    .filters(f -> f.prefixPath("/api"))
	                    // KEPT: Routing to ACCOUNT service per your business setup
	                    .uri("lb://ACCOUNT")) 
	            
	            .route("loan-route", t -> t
	                    .path("/loan/**")
	                    .filters(f -> f.prefixPath("/api")
	                            // FIX: Standardized header casing and made time dynamic
	                            .addRequestHeader("X-RESPONSE-TIME", LocalDateTime.now().toString()))
	                    .uri("lb://LOAN"))
	            .build();
//				.route(p -> p
//						.path("/account/**")
//						.filters(f -> f.prefixPath("/api")
//						.addResponseHeader("X-RESPONSE-TIME", ctx ->  LocalDateTime.now().toString()
//						.uri("lb://ACCOUNT"))
//				.route(p -> p
//						.path("/customer/**")
//						.filters(f -> f.prefixPath("/api")
//						.uri("lb://ACCOUNT")
//						)
//				.route(t -> t.path("/loan/**")
//						.filters(f -> f.prefixPath("/api")
//								.addRequestHeader("X-RESPONSE-TIMe", LocalDateTime.now().toString())
//						.uri("lb://LOAN"))
//						
//				.build();
	}
}
