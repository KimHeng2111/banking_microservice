package com.kimheng.account.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.kimheng.account.dto.CustomerDTO;
import com.kimheng.account.entity.Customer;
@Component
public class CustomerMapper {
	public Customer toCustomer(CustomerDTO dto) {
		Customer cus = new Customer();
		cus.setName(dto.getName());
		cus.setEmail(dto.getEmail());
		cus.setMobileNumber(dto.getMobileNumber());
		cus.setCreateDate(LocalDate.parse(dto.getCreateDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		return cus;
	}
	public CustomerDTO toDto(Customer cus) {
		CustomerDTO dto = new CustomerDTO();
		dto.setName(cus.getName());
		dto.setEmail(cus.getEmail());
		dto.setMobileNumber(cus.getMobileNumber());
		dto.setCreateDate(cus.getCreateDate().toString());
		return dto;
	}
}
