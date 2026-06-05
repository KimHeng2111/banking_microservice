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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
	private final AccountService service;
	private final RateLimiterRegistry rateLimiterRegistry;

	@PostMapping
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto) {
		AccountDTO account = service.createAccount(dto);
		return ResponseEntity.ok(account);
	}

	@GetMapping("{customer_id}")
	public ResponseEntity<List<AccountDTO>> findAccountByCustomerId(@PathVariable("customer_id") String cumstomerId) {
		List<AccountDTO> byCustomerId = service.findByCustomerId(cumstomerId);
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
			System.out.println("==============GetAccountListSuccess================");
		} catch (RequestNotPermitted e) {
			System.out.println("==============GetAccountListFailed================");
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
