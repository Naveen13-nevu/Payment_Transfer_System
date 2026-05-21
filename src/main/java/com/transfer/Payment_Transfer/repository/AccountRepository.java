package com.transfer.Payment_Transfer.repository;

import com.transfer.Payment_Transfer.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
