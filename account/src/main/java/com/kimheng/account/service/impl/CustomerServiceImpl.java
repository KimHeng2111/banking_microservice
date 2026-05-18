package com.kimheng.account.service.impl;

import org.springframework.stereotype.Service;

import com.kimheng.account.entity.Customer;
import com.kimheng.account.repository.CustomerRepository;
import com.kimheng.account.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository repository;
	@Override
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return repository.save(customer);
	}

}
