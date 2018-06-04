package com.example.user.studentpurse.Domain;


import java.io.Serializable;

public class Balance implements Serializable{
    public double Balance;

    public Balance(double balance)
    {
        this.Balance = balance;
    }
    public Balance()
    {
        this.Balance =0;
    }
    public Balance(Balance balance)
    {
        this.Balance = balance.Balance;
    }
    public double getBalance() {
        return Balance;
    }
    public void setBalance(double balance) {
        Balance = balance;
    }
}

