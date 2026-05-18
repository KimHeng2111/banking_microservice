package com.kimheng.account.service.impl;

import org.springframework.stereotype.Service;

import com.kimheng.account.dto.AccountDTO;
import com.kimheng.account.entity.Account;
import com.kimheng.account.mapper.AccountMapper;
import com.kimheng.account.repository.AccountRepository;
import com.kimheng.account.service.AccountService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountServiceimpl implements AccountService{
	
	private final AccountRepository repository;
	private final AccountMapper mapper;
	@Override
	public AccountDTO createAccount(AccountDTO dto) {
		Account account = mapper.toAccount(dto);
		Account save = repository.save(account);
		return mapper.toDto(save);
	}

	@Override
	public AccountDTO findByCustomerId(String customerId) {
		Account account = repository.findByCustomerId(customerId)
	            .orElseThrow(() -> new RuntimeException("Customer ID: %s does not have any account".formatted(customerId)));
	    return mapper.toDto(account);
	}

}
