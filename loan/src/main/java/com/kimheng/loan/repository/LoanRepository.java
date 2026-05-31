package com.kimheng.loan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimheng.loan.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan,String>{
	List<Loan> findByCustomerId(String cutomserId);
}
