package com.kimheng.apigateway.filter;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {
	public static final String CORRELATION_ID = "KIMHENG-BANK-CORRELATION-ID";
	//Get CorrelationId
	public String getCorrelationId(HttpHeaders requestHeader) {
		if (requestHeader.get(CORRELATION_ID) != null) {
			List<String> list = requestHeader.get(CORRELATION_ID);
			return list.stream().findFirst().get();
		}
		return null;
	}
	public ServerWebExchange setRequestHeader(ServerWebExchange exchange,String name,String value) {
		return exchange.mutate()
				.request(exchange.getRequest().mutate().header(name, value).build()).build();
	}
	public ServerWebExchange setCorrelationId(ServerWebExchange exchange,String correlationID) {
		return this.setRequestHeader(exchange, CORRELATION_ID, correlationID);
	}
	
}
