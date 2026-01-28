package model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity


public class Account {
    //entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String ownerName;
    private BigDecimal balance;

    //getters and setters
    public Long getId(){
        return Id;
    }
    public String getOwnerName(){
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


}
