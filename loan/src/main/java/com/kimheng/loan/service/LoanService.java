package com.kimheng.loan.service;

import java.util.List;

import com.kimheng.loan.dto.LoanDTO;
import com.kimheng.loan.entity.Loan;

public interface LoanService  {
	Loan createLoan(LoanDTO dto);
	LoanDTO getLoanById(String id);
	List<LoanDTO> getAllLoan();
	List<LoanDTO> getLoanByCustomerId(String customerId);
}
