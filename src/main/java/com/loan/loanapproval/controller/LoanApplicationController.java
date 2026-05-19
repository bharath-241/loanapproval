package com.loan.loanapproval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.loan.loanapproval.dto.LoanApplicationRequest;
import com.loan.loanapproval.entity.LoanApplication;
import com.loan.loanapproval.service.LoanApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/loans")
@Validated
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService service;

    @PostMapping
    public LoanApplication createLoan(
            @Valid @RequestBody LoanApplicationRequest request) {

        return service.createLoan(request);
    }

    @GetMapping
    public List<LoanApplication> getAllLoans() {
        return service.getAllLoans();
    }

    @GetMapping("/{id}")
    public LoanApplication getLoanById(@PathVariable Long id) {
        return service.getLoanById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteLoan(@PathVariable Long id) {
        return service.deleteLoan(id);
    }

    @PutMapping("/{id}/verify")
    public LoanApplication verifyLoan(@PathVariable Long id) {
        return service.verifyLoan(id);
    }

    @PutMapping("/{id}/approve")
    public LoanApplication approveLoan(@PathVariable Long id) {
        return service.approveLoan(id);
    }

    @PutMapping("/{id}/reject")
    public LoanApplication rejectLoan(@PathVariable Long id) {
        return service.rejectLoan(id);
    }
}