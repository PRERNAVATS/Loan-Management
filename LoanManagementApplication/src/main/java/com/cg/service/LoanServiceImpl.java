package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Loan;
import com.cg.exception.DuplicateLoanApplicationException;
import com.cg.exception.InvalidLoanAmountException;
import com.cg.exception.LoanNotFoundException;
import com.cg.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository repo;

    @Override
    public Loan createLoan(Loan loan) {

        // Rule 1 & 2: Amount validation
        if (loan.getLoanAmount() <= 0 || loan.getLoanAmount() > 5000000) {
            throw new InvalidLoanAmountException("Loan amount must be between 1 and 5000000");
        }

        // Rule 3: Duplicate pending loan check
        repo.findByApplicantNameAndStatus(loan.getApplicantName(), "PENDING")
            .ifPresent(l -> {
                throw new DuplicateLoanApplicationException("User already has a pending loan");
            });

        loan.setStatus("PENDING");
        return repo.save(loan);
    }

    @Override
    public List<Loan> getAllLoans() {
        return repo.findAll();
    }

    @Override
    public Loan getLoanById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));
    }

    @Override
    public Loan updateLoanStatus(Long id, String status) {

        Loan loan = repo.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));

        if (!status.equals("APPROVED") && !status.equals("REJECTED")) {
            throw new IllegalArgumentException("Status must be APPROVED or REJECTED");
        }

        loan.setStatus(status);
        return repo.save(loan);
    }
}