package com.loan.loanapproval.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loan.loanapproval.entity.LoanApplication;

public interface LoanApplicationRepository
        extends JpaRepository<LoanApplication, Long> {

}