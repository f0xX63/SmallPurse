package com.example.user.studentpurse.Services;

import com.example.user.studentpurse.Domain.Spending;
import com.example.user.studentpurse.WorkOfFile.Repository;

import java.io.IOException;
import java.util.ArrayList;

public class SpendingService implements ISpendingService{
    Repository<Spending> repositorySpending;

    public SpendingService(Repository<Spending> repositorySpending)
    {
        this.repositorySpending = repositorySpending;
    }
    @Override
    public void addSpending(Spending spending) throws IOException {

        try {
            repositorySpending.add(spending);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public void deleteSpending(Spending spending) throws IOException {
        ArrayList<Spending> spendings = (ArrayList<Spending>) repositorySpending.getAllData();
        spendings.remove(spending);
        repositorySpending.saveAllData(spendings);
    }

    @Override
    public int getLastId() throws IOException {
        try {
            ArrayList<Spending> spendings = (ArrayList<Spending>) repositorySpending.getAllData();
            return spendings.size();
        } catch (IOException e) {
           throw new IOException(e.getMessage());
        }
    }
}
