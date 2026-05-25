package com.kimheng.loan.mapper;

import org.springframework.stereotype.Component;

import com.kimheng.loan.dto.LoanDTO;
import com.kimheng.loan.entity.Loan;

@Component
public class LoanMapper {

	public Loan toLoan(LoanDTO dto) {
		Loan loan = new Loan();
		loan.setAmountPaid(dto.getAmountPaid());
		loan.setCreateDate(dto.getCreateDate());
		loan.setCustomerId(dto.getCustomerId());
		loan.setLoanNumber(dto.getLoanNumber());
		loan.setLoanType(dto.getLoanType());
		loan.setOutstandingAmount(dto.getOutstandingAmount());
		loan.setStartDate(dto.getStartDate());
		loan.setTotalLoan(dto.getTotalLoan());
		return loan;
	}

	public LoanDTO toDto(Loan loan) {

		LoanDTO dto = new LoanDTO();
		dto.setAmountPaid(loan.getAmountPaid());
		dto.setCreateDate(loan.getCreateDate());
		dto.setCustomerId(loan.getCustomerId());
		dto.setLoanNumber(loan.getLoanNumber());
		dto.setLoanType(loan.getLoanType());
		dto.setOutstandingAmount(loan.getOutstandingAmount());
		dto.setStartDate(loan.getStartDate());
		dto.setTotalLoan(loan.getTotalLoan());
		return dto;
	}
}
