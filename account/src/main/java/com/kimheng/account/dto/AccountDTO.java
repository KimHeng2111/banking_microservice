package com.kimheng.account.dto;

import lombok.Data;

@Data
public class AccountDTO {
	private Long accountNumber;
	private String accountType;
	private String branchAddress;
	private String createDate;
	private String customerId;
}
