package com.kimheng.account.service;

import java.util.List;

import com.kimheng.account.dto.CustomerDTO;
import com.kimheng.account.dto.CustomerDetailResponeDTO;
import com.kimheng.account.entity.Customer;

public interface CustomerService {
	Customer saveCustomer(Customer customer);
	CustomerDetailResponeDTO customerDetailById(String customerId);
	CustomerDTO getCustomerById(String Id);
	List<Customer> getAllCustomer();
}
