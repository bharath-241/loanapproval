package com.loan.loanapproval.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loan.loanapproval.entity.AuditLog;

public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {

}