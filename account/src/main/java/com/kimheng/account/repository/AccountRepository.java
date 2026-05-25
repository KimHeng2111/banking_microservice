package com.kimheng.account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kimheng.account.entity.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
	// 1. Find an account by its unique account number
	Optional<Account> findByAccountNumber(Long accountNumber);

    // 2. Find all accounts belonging to a specific customer using the @DocumentReference link
	Optional<Account> findByCustomerId(String customerId);
    
    // 3. Find accounts by account type (e.g., "Savings")
    List<Account> findByAccountType(String accountType);
}
