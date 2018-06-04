package com.example.user.studentpurse.Services;

import com.example.user.studentpurse.Domain.Spending;

import java.io.IOException;

public interface ISpendingService {
    void addSpending(Spending spending) throws IOException;
    void deleteSpending(Spending spending) throws IOException;
    int getLastId() throws IOException;
}
