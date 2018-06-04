package com.example.user.studentpurse.Services;

import com.example.user.studentpurse.Domain.Balance;
import com.example.user.studentpurse.Domain.Spending;

import java.io.IOException;
import java.util.List;

public interface IBalanceService {
    void subBalance(Spending spending) throws IOException;
    void addBalance(Balance balance) throws IOException;
    void setBalance(Balance balance) throws IOException;
    Balance getBalance(String storage) throws IOException;
    List<String> getAllStorage() throws IOException;
    String toString(Balance balance);
}

