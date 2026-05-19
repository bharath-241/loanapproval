package com.loan.loanapproval.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.loanapproval.entity.AuditLog;
import com.loan.loanapproval.entity.LoanApplication;
import com.loan.loanapproval.dto.LoanApplicationRequest;
import com.loan.loanapproval.enums.LoanStatus;
import com.loan.loanapproval.exception.ResourceNotFoundException;
import com.loan.loanapproval.repository.AuditLogRepository;
import com.loan.loanapproval.repository.LoanApplicationRepository;

@Service
public class LoanApplicationService {

    @Autowired
    private LoanApplicationRepository repository;

    @Autowired
    private AuditLogRepository auditRepository;

    public LoanApplication createLoan(LoanApplicationRequest request) {

        LoanApplication loan = new LoanApplication();

        loan.setApplicantName(request.getApplicantName());
        loan.setEmail(request.getEmail());
        loan.setSalary(request.getSalary());
        loan.setLoanAmount(request.getLoanAmount());
        loan.setPurpose(request.getPurpose());

        loan.setStatus(LoanStatus.SUBMITTED);

        loan.setCreatedAt(LocalDateTime.now());
        loan.setUpdatedAt(LocalDateTime.now());

        LoanApplication savedLoan = repository.save(loan);

        saveAudit(
                savedLoan.getId(),
                "CREATE_LOAN",
                savedLoan.getStatus().name());

        return savedLoan;
    }

    public List<LoanApplication> getAllLoans() {
        return repository.findAll();
    }

    public LoanApplication getLoanById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loan not found with ID: " + id));
    }

    public String deleteLoan(Long id) {

        LoanApplication loan = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loan not found with ID: " + id));

        repository.delete(loan);

        return "Loan deleted successfully";
    }

    public LoanApplication verifyLoan(Long id) {

        LoanApplication loan = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loan not found with ID: " + id));

        loan.setStatus(LoanStatus.VERIFIED);
        loan.setUpdatedAt(LocalDateTime.now());

        LoanApplication updatedLoan = repository.save(loan);

        saveAudit(
                updatedLoan.getId(),
                "VERIFY_LOAN",
                updatedLoan.getStatus().name());

        return updatedLoan;
    }

    public LoanApplication approveLoan(Long id) {

        LoanApplication loan = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loan not found with ID: " + id));

        if (loan.getStatus() == LoanStatus.REJECTED) {
            throw new RuntimeException(
                    "Cannot approve rejected loan");
        }

        loan.setStatus(LoanStatus.APPROVED);
        loan.setUpdatedAt(LocalDateTime.now());

        LoanApplication updatedLoan = repository.save(loan);

        saveAudit(
                updatedLoan.getId(),
                "APPROVE_LOAN",
                updatedLoan.getStatus().name());

        return updatedLoan;
    }

    public LoanApplication rejectLoan(Long id) {

        LoanApplication loan = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loan not found with ID: " + id));

        if (loan.getStatus() == LoanStatus.APPROVED) {
            throw new RuntimeException(
                    "Cannot reject approved loan");
        }

        loan.setStatus(LoanStatus.REJECTED);
        loan.setUpdatedAt(LocalDateTime.now());

        LoanApplication updatedLoan = repository.save(loan);

        saveAudit(
                updatedLoan.getId(),
                "REJECT_LOAN",
                updatedLoan.getStatus().name());

        return updatedLoan;
    }

    private void saveAudit(
            Long loanId,
            String action,
            String status) {

        AuditLog audit = new AuditLog();

        audit.setLoanId(loanId);
        audit.setAction(action);
        audit.setStatus(status);
        audit.setTimestamp(LocalDateTime.now());

        auditRepository.save(audit);
    }
}