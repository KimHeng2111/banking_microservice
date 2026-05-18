package com.kimheng.account.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.kimheng.account.dto.AccountDTO;
import com.kimheng.account.entity.Account;
import com.kimheng.account.entity.Customer;

@Component
public class AccountMapper {
	public Account toAccount(AccountDTO dto) {
		Account ac = new Account();
		ac.setAccountNumber(dto.getAcccountNumber());
		ac.setAccountType(dto.getAccountType());
		ac.setBranchAddress(dto.getBranchAddress());
		ac.setCreateDate(LocalDate.parse(dto.getCreateDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		Customer cu = new Customer();
		cu.setId(dto.getCustomerId());
		ac.setCustomer(cu);
		return ac;
	}
	public AccountDTO toDto(Account account) {
		AccountDTO dto = new AccountDTO();
		dto.setAcccountNumber(account.getAccountNumber());
		dto.setAccountType(account.getAccountType());
		dto.setBranchAddress(account.getBranchAddress());
		dto.setCreateDate(account.getCreateDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		dto.setCustomerId(account.getCustomer().getId());
		return dto;
	}
}
