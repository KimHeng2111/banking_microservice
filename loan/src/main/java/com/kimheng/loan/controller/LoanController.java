package com.kimheng.loan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimheng.loan.dto.LoanDTO;
import com.kimheng.loan.entity.Loan;
import com.kimheng.loan.service.LoanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/loan")
public class LoanController {
	private final LoanService service;
	private LoanDTO loan;
	
	@PostMapping
	public ResponseEntity<Loan> CreateNewLoan(@RequestBody LoanDTO dto){
		Loan loan = service.createLoan(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(loan);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<LoanDTO> getLoanById(@PathVariable("id") String id){
		loan = service.getLoanById(id);
		return ResponseEntity.status(HttpStatus.OK).body(loan);
	}
	
	@GetMapping
	public ResponseEntity<List<LoanDTO>> getAllLoans(){
		List<LoanDTO> allLoan = service.getAllLoan();
		return ResponseEntity.ok(allLoan);
	}
	@GetMapping("customer/{id}")
	public ResponseEntity<List<LoanDTO>> getLoanByCustomerId(@PathVariable("id") String customerId){
		List<LoanDTO> loands = service.getLoanByCustomerId(customerId);
		return ResponseEntity.ok(loands);
	}
}
