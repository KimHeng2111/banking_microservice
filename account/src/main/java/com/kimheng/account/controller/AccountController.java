package com.kimheng.account.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimheng.account.dto.AccountDTO;
import com.kimheng.account.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
	private final AccountService service;
	@PostMapping
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto){
		AccountDTO account = service.createAccount(dto);
		return ResponseEntity.ok(account);
	}
	@GetMapping("{customer_id}")
	public ResponseEntity<List<AccountDTO>> findAccountByCustomerId(@PathVariable("customer_id") String cumstomerId){
		List<AccountDTO> byCustomerId = service.findByCustomerId(cumstomerId);
		return ResponseEntity.ok(byCustomerId);
	}
	@GetMapping
	public ResponseEntity<List<AccountDTO>> findAllAccount(){
		
		return ResponseEntity.ok(service.findAllAccount());
	}
	//Test RateLimiter
//	@RateLimiter(name = "testRateLimiter" , fallbackMethod = "sayHi")
//	@GetMapping("sayHellow")
//	public String sayHello() {
//		return "<h1>Hello To YOu </h1>";
//	}
//	public String sayHi(Throwable e) {
//		return "<h1>Hi To YOu </h1>";
//	}

}
