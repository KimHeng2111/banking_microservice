package com.kimheng.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimheng.account.dto.CustomerDTO;
import com.kimheng.account.entity.Customer;
import com.kimheng.account.mapper.CustomerMapper;
import com.kimheng.account.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerService service;
	private final CustomerMapper mapper;
	@PostMapping
	public ResponseEntity<Customer> saveCustomer(@RequestBody CustomerDTO dto){
		Customer customer = mapper.toCustomer(dto);
		Customer saveCustomer = service.saveCustomer(customer);
		return ResponseEntity.ok(saveCustomer);
	}
}
