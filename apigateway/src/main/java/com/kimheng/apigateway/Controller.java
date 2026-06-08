package com.kimheng.apigateway;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("test")
public class Controller {
	@GetMapping("ip")
	public Mono<Map<String, Object>> test(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        Map<String, Object> details = new LinkedHashMap<>();

        // 1. Basic Connection Details
        details.put("id", request.getId()); // Unique request ID
        details.put("method", request.getMethod().name());
        details.put("uri", request.getURI().toString());
        details.put("path", request.getPath().value());
        
        // 2. Client IP & Remote Address
        details.put("remoteAddress", request.getRemoteAddress() != null 
                ? request.getRemoteAddress().toString() : "Unknown");
        details.put("clientHostAddress", request.getRemoteAddress() != null 
                ? request.getRemoteAddress().getAddress().getHostAddress() : "Unknown");

        return Mono.just(details);
    }
}
