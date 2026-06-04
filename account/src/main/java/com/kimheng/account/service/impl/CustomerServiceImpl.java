package com.kimheng.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kimheng.account.dto.AccountDTO;
import com.kimheng.account.dto.CustomerDTO;
import com.kimheng.account.dto.CustomerDetailResponeDTO;
import com.kimheng.account.dto.LoanResponseDTO;
import com.kimheng.account.entity.Customer;
import com.kimheng.account.feign.LoanFeign;
import com.kimheng.account.mapper.CustomerMapper;
import com.kimheng.account.repository.CustomerRepository;
import com.kimheng.account.service.AccountService;
import com.kimheng.account.service.CustomerService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository repository;
	private final AccountService accountService;
	private final CustomerMapper mapper;
	private final LoanFeign loanFeign;
	@Override
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return repository.save(customer);
	}
	@Override
	public CustomerDTO getCustomerById(String Id) {
		Customer customer = repository.findById(Id).orElseThrow(() -> new RuntimeException("CustomerID : %s is not fuound!!!!".formatted(Id)));
		return mapper.toDto(customer);
	}
	@Override
	@CircuitBreaker(name = "LoanFeigngetLoanByIdString", fallbackMethod = "getLoanError")
	public CustomerDetailResponeDTO customerDetailById(String customerId) {
		List<LoanResponseDTO> loans = loanFeign.getLoanById(customerId);
		List<AccountDTO> account = accountService.findByCustomerId(customerId);
		Customer customer = repository.findById(customerId).orElseThrow(() -> new RuntimeException("CustomerID : %s is not fuound!!!!".formatted(customerId)));
		CustomerDetailResponeDTO customerDetail = new CustomerDetailResponeDTO();
		customerDetail.setCustomer(mapper.toDto(customer));
		customerDetail.setAccounts(account);
		customerDetail.setLoans(loans);
		return customerDetail;
	}
	public CustomerDetailResponeDTO getLoanError(String customerId,Throwable throwable) {
		List<AccountDTO> account = accountService.findByCustomerId(customerId);
		Customer customer = repository.findById(customerId).orElseThrow(() -> new RuntimeException("CustomerID : %s is not fuound!!!!".formatted(customerId)));
		CustomerDetailResponeDTO customerDetail = new CustomerDetailResponeDTO();
		List<LoanResponseDTO> loans = new ArrayList<LoanResponseDTO>();
		customerDetail.setCustomer(mapper.toDto(customer));
		customerDetail.setAccounts(account);
		customerDetail.setLoans(loans);
		return customerDetail;
	}
	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> customers = repository.findAll();
		return customers;
	}
	

}
