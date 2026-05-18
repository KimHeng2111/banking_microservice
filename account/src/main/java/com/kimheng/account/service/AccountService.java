package com.kimheng.account.service;

import com.kimheng.account.dto.AccountDTO;

public interface AccountService {
	AccountDTO createAccount(AccountDTO dto);
	AccountDTO findByCustomerId(String customerId);
}
