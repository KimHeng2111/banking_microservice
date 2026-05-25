package com.kimheng.loan.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class LoanDTO {
	
	private Long loanNumber;
	private String customerId;
	private LocalDate startDate;
	private String loanType;
	private BigDecimal totalLoan;
	private BigDecimal amountPaid;
	private BigDecimal outstandingAmount;
	private LocalDate createDate;
}
