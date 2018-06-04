package com.example.user.studentpurse.Domain;


import java.io.Serializable;

public class Balance implements Serializable{
    public double Balance;
    public String Storage;

    public Balance(double balance, String storage)
    {
        this.Balance = balance;
        this.Storage = storage;
    }
    public Balance()
    {
        this.Balance = 0;
        this.Storage = "Наличные";
    }
    public Balance(Balance balance)
    {
        this.Balance = balance.Balance;
        this.Storage = balance.Storage;
    }
}

