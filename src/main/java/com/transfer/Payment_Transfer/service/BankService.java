package com.transfer.Payment_Transfer.service;

import com.transfer.Payment_Transfer.entity.Account;
import com.transfer.Payment_Transfer.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {

    private final AccountRepository accountRepository;

    public BankService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(Long fromId, Long toId, double amount) {

        Account sender = accountRepository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Account receiver = accountRepository.findById(toId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));


        sender.setBalance(sender.getBalance() - amount);


        if (amount > 5000) {
            throw new RuntimeException("Transfer limit exceeded!");
        }


        receiver.setBalance(receiver.getBalance() + amount);

        accountRepository.save(sender);
        accountRepository.save(receiver);
    }
}