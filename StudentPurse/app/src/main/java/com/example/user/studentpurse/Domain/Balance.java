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
}

