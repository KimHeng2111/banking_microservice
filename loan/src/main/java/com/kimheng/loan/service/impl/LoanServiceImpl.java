package com.kimheng.loan.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kimheng.loan.dto.LoanDTO;
import com.kimheng.loan.entity.Loan;
import com.kimheng.loan.mapper.LoanMapper;
import com.kimheng.loan.repository.LoanRepository;
import com.kimheng.loan.service.LoanService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
	private final LoanRepository repository;
	private final LoanMapper mapper;
	@Override
	public Loan createLoan(LoanDTO dto) {
		Loan loan = mapper.toLoan(dto);
		Loan save = repository.save(loan);
		return save;
	}

	@Override
	public LoanDTO getLoanById(String id) {
		Optional<Loan> loanOpt = repository.findById(id);
		Loan loan = loanOpt.orElseThrow(() -> new RuntimeException("Loan ID : %s is Not Found !!!!!!".formatted(id)));
		return mapper.toDto(loan);
	}

	@Override
	public List<LoanDTO> getAllLoan() {
		List<LoanDTO> collect = repository.findAll().stream().map(loan -> mapper.toDto(loan)).collect(Collectors.toList());
		return collect;
	}

}
