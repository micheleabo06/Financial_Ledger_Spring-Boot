package controller;

import service.LedgerService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/ledger")
public class LedgerController {
    private final LedgerService ledgerService;

    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    // this allows money movement via the URL
    @PostMapping("/transfer")
    public String transfer(@RequestParam Long from,
                           @RequestParam Long to,
                           @RequestParam BigDecimal amount) {
        try {
            ledgerService.transferMoney(from, to, amount);
            return "Transfer successful!";
        } catch (Exception e) {
            return "Transfer failed: " + e.getMessage();
        }
    }

    @GetMapping("/") //for 'localhost:8080' address
    public String welcome() {
        return "Welcome to my Ledger API! The server is now live";
    }
    @GetMapping("/test")
    public String test() {
        return "The Controller is working!";
    }

}
