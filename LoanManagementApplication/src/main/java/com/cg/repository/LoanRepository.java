package com.cg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByApplicantNameAndStatus(String applicantName, String status);
}