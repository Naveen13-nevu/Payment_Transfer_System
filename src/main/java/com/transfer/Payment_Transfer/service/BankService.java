package com.transfer.Payment_Transfer.service;

import com.transfer.Payment_Transfer.entity.Account;
import com.transfer.Payment_Transfer.exception.ResourceNotFoundException;
import com.transfer.Payment_Transfer.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BankService {

    private final AccountRepository accountRepository;


    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    // GET ACCOUNT
    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found"));
    }

    // DEPOSIT MONEY
    @Transactional
    public Account deposit(String accountNumber, Double amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found"));

        account.setBalance(account.getBalance() + amount);

        return account;
    }

    // TRANSFER MONEY
    @Transactional
    public String transferMoney(String fromAccount,
                                String toAccount,
                                Double amount) {

        Account sender = accountRepository.findByAccountNumber(fromAccount)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sender not found"));

        Account receiver = accountRepository.findByAccountNumber(toAccount)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Receiver not found"));

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient Balance");
        }


        sender.setBalance(sender.getBalance() - amount);

        receiver.setBalance(receiver.getBalance() + amount);

        return "Transfer Successful";
    }

    public String deleteAccount(Long id) {

        Account account = getAccount(id);

        accountRepository.delete(account);

        return "Account Deleted";
    }
}