package com.kimheng.account.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kimheng.account.dto.LoanResponseDTO;

@FeignClient("loan")
public interface LoanFeign {
	@GetMapping("api/v1/loan/customer/{id}")
	List<LoanResponseDTO>	getLoanById(@PathVariable("id") String customerId);
}
