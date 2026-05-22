package com.transfer.Payment_Transfer.controller;

import com.transfer.Payment_Transfer.entity.Account;
import com.transfer.Payment_Transfer.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final BankService bankService;


    @PostMapping
    public Account create(@RequestBody Account account) {
        return bankService.createAccount(account);
    }


    @GetMapping("/{id}")
    public Account get(@PathVariable Long id) {
        return bankService.getAccount(id);
    }


    @PutMapping("/deposit")
    public Account deposit(@RequestParam String accountNumber,
                           @RequestParam Double amount) {

        return bankService.deposit(accountNumber, amount);
    }


    @PostMapping("/transfer")
    public String transfer(@RequestParam String fromAccount,
                           @RequestParam String toAccount,
                           @RequestParam Double amount) {

        return bankService.transferMoney(
                fromAccount,
                toAccount,
                amount
        );
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return bankService.deleteAccount(id);
    }
}