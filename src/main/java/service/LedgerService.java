package service;

import model.Account;
import repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service

public class LedgerService {

        private final AccountRepository accountRepository;
        // This lets the Service use the Repository
        public LedgerService(AccountRepository accountRepository) {
            this.accountRepository = accountRepository;
        }

        @Transactional // The most important part for a bank!
        public void transferMoney(Long fromId, Long toId, BigDecimal amount) {
            // 1. Fetch the accounts
            Account fromAccount = accountRepository.findById(fromId)
                    .orElseThrow(() -> new RuntimeException("Sender not found"));
            Account toAccount = accountRepository.findById(toId)
                    .orElseThrow(() -> new RuntimeException("Receiver not found"));

            // 2. Security Check: Use Algebra to ensure balance isn't negative
            if (fromAccount.getBalance().compareTo(amount) < 0) {
                throw new RuntimeException("Insufficient funds!");
            }

            // 3. Mathematical Update: Move the money
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            toAccount.setBalance(toAccount.getBalance().add(amount));

            // 4. Save the changes back to the database
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);
        }
    }

