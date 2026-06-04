package com.kimheng.account.service;

import java.util.List;

import com.kimheng.account.dto.AccountDTO;

public interface AccountService {
	AccountDTO createAccount(AccountDTO dto);
	List<AccountDTO> findByCustomerId(String customerId);
	List<AccountDTO> findAllAccount();
}
