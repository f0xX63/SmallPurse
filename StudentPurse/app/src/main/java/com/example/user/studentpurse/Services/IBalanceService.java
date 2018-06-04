package com.example.user.studentpurse.Services;

import com.example.user.studentpurse.Domain.Balance;
import com.example.user.studentpurse.Domain.Spending;

import java.io.IOException;

public interface IBalanceService {
    void subBalance(Spending spending) throws IOException;
    void addBalance(Balance balance) throws IOException;
    void setBalance(Balance balance) throws IOException;
    Balance getBalance() throws IOException;
    Boolean checkFileExists();
}

