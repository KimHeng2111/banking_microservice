package com.kimheng.account.entity;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "accounts")
public class Account {
	private Long accountNumber;
	private String accountType;
	private String branchAddress;
	private LocalDate createDate;
	
	private Customer customer;
}
