package com.kimheng.account.dto;

import java.util.List;

import lombok.Data;
@Data
public class CustomerDetailResponeDTO {
	private CustomerDTO customer;
	private AccountDTO accounts;
	private List<LoanResponseDTO> loans;
}
