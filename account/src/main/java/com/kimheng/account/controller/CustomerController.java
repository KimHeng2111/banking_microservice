package com.kimheng.account.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimheng.account.dto.CustomerDTO;
import com.kimheng.account.dto.CustomerDetailResponeDTO;
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
	@GetMapping("{customerId}")
	public ResponseEntity<CustomerDTO> getCustoemrById(@PathVariable String customerId){
		CustomerDTO dto = service.getCustomerById(customerId);
		return ResponseEntity.ok(dto);
	}
	@GetMapping("detail/{customerId}")
	
	public ResponseEntity<CustomerDetailResponeDTO> getCustoemrDetail(@PathVariable String customerId){
		CustomerDetailResponeDTO customerDetail = service.customerDetailById(customerId);
		return ResponseEntity.ok(customerDetail);
	}
	@GetMapping()
	public ResponseEntity<List<Customer>> getCustoemrs(){
		return ResponseEntity.ok(service.getAllCustomer());
	}
//	public ResponseEntity<CustomerDetailResponeDTO> getError(String customerId, Throwable throwable){
//		CustomerDetailResponeDTO customerDetail = service.loanError(customerId, throwable);
//		return ResponseEntity.ok(customerDetail);
//	}
}
