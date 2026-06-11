package com.kimheng.apigateway.filter;

import java.util.UUID;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class TtracingFilter {
	private final FilterUtility filter;
	@Bean
	@Order(1)
	public GlobalFilter preTracingFilter() {
		return (exchange , chain) -> {
			HttpHeaders requestHeader = exchange.getRequest().getHeaders();
			if(isCorrelationIdPresent(requestHeader)) {
				log.debug("kimhengbank-corelation-id found in RequestTracingFilter {}",filter.getCorrelationId(requestHeader));
			}else {
				String correlationId = generateCorrelationId();
				exchange = filter.setCorrelationId(exchange, correlationId);
			}
			return chain.filter(exchange);
		};
	}
	@Bean
	@Order(2)
	public GlobalFilter postTracingFilter() {
		return (exchange , chain) -> {
//			HttpHeaders responseHeader = exchange.getResponse().getHeaders();
//			if(isCorrelationIdPresent(responseHeader)) {
//				log.debug("kimhengbank-corelation-id found in RequestTracingFilter {}",filter.getCorrelationId(responseHeader));
//			}else {
//				String correlationId = generateCorrelationId();
//				exchange = filter.setCorrelationId(exchange, correlationId);
//			}
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				HttpHeaders headers = exchange.getRequest().getHeaders();
				String correlationId = filter.getCorrelationId(headers);
				log.debug("Update the corelation id to the outbound header: {}",correlationId);
				exchange.getResponse().getHeaders().add(FilterUtility.CORRELATION_ID, correlationId);
			}));
		};
	}
	private boolean isCorrelationIdPresent(HttpHeaders requestHeader) {
		if(!requestHeader.containsHeader(FilterUtility.CORRELATION_ID)) {
			return false;
		}
		return true;
	}
	private String generateCorrelationId() {
		return UUID.randomUUID().toString();
	}
}
