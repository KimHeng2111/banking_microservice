package com.kimheng.account.service.impl;

import java.util.List;

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
	public List<AccountDTO> findByCustomerId(String customerId) {
		List<AccountDTO> accounts = repository.findByCustomerId(customerId).stream().map(account -> mapper.toDto(account)).toList();
	    return accounts;
	}

	@Override
	public List<AccountDTO> findAllAccount() {
		// TODO Auto-generated method stub
		return repository.findAll().stream().map(account -> mapper.toDto(account)).toList();
	}
	
}
