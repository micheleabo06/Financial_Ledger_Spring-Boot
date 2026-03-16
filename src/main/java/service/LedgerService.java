package service;

import model.Account;
import repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service

public class LedgerService {

        private final AccountRepository accountRepository;
        // this lets the Service use the Repository
        public LedgerService(AccountRepository accountRepository) {
            this.accountRepository = accountRepository;
        }

        @Transactional 
        public void transferMoney(Long fromId, Long toId, BigDecimal amount) {
            //fetch the accounts
            Account fromAccount = accountRepository.findById(fromId)
                    .orElseThrow(() -> new RuntimeException("Sender not found"));
            Account toAccount = accountRepository.findById(toId)
                    .orElseThrow(() -> new RuntimeEx'ception("Receiver not found"));

            //security check (to make sure balance isnt negative)
            if (fromAccount.getBalance().compareTo(amount) < 0) {
                throw new RuntimeException("Insufficient funds!");
            }

            //move the money
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            toAccount.setBalance(toAccount.getBalance().add(amount));

            //save the changes back to the database
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);
        }
    }

