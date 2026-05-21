package com.transfer.Payment_Transfer.controller;

import com.transfer.Payment_Transfer.service.BankService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class AccountController {

    private final BankService bankService;

    public AccountController(BankService bankService) {
        this.bankService = bankService;
    }

    // Transfer Money API
    @PostMapping("/transfer")
    public String transferMoney(
            @RequestParam Long fromId,
            @RequestParam Long toId,
            @RequestParam double amount) {

        bankService.transferMoney(fromId, toId, amount);

        return "Money transferred successfully!";
    }
}