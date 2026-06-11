package com.kimheng.account.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimheng.account.dto.AccountDTO;
import com.kimheng.account.service.AccountService;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
@Slf4j
public class AccountController {
	private final AccountService service;
	private final RateLimiterRegistry rateLimiterRegistry;
	@PostMapping
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto,@RequestHeader("KIMHENG-BANK-CORRELATION-ID") String correlationId) {
		AccountDTO account = service.createAccount(dto);
		log.info("correlationID : %s is created account!!!!!".formatted(correlationId),account);
		return ResponseEntity.ok(account);
	}

	@GetMapping("{customer_id}")
	public ResponseEntity<List<AccountDTO>> findAccountByCustomerId(@PathVariable("customer_id") String cumstomerId,@RequestHeader("KIMHENG-BANK-CORRELATION-ID") String correlationId) {
		List<AccountDTO> byCustomerId = service.findByCustomerId(cumstomerId);
		log.info("correlationID : %s is findAccount account!!!!!".formatted(correlationId));
		return ResponseEntity.ok(byCustomerId);
	}

	@GetMapping
	public ResponseEntity<List<AccountDTO>> findAllAccount(HttpServletRequest request) {
		String userId = request.getRemoteAddr();
		RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter(userId, () -> RateLimiterConfig.custom()
				.limitForPeriod(2).limitRefreshPeriod(Duration.ofSeconds(20)).timeoutDuration(Duration.ZERO).build());
		List<AccountDTO> accounts = new ArrayList<AccountDTO>();
		try {
			accounts = RateLimiter.decorateSupplier(rateLimiter, () -> service.findAllAccount()).get();
			log.info("==============GetAccountListSuccess================");
		} catch (RequestNotPermitted e) {
			log.error("==============GetAccountListFailed================");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.ok(accounts);
	}
	// Test RateLimiter
//	@RateLimiter(name = "testRateLimiter" , fallbackMethod = "sayHi")
//	@GetMapping("sayHellow")
//	public String sayHello() {
//		return "<h1>Hello To YOu </h1>";
//	}
//	public String sayHi(Throwable e) {
//		return "<h1>Hi To YOu </h1>";
//	}

}
