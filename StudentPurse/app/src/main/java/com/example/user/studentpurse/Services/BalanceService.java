package com.example.user.studentpurse.Services;

import android.content.Context;

import com.example.user.studentpurse.Domain.Balance;
import com.example.user.studentpurse.Domain.SmallPurseParameters;
import com.example.user.studentpurse.Domain.Spending;
import com.example.user.studentpurse.WorkOfFile.JSONHelper;

import java.io.IOException;

public class BalanceService implements IBalanceService {

    Context Context;
    public BalanceService(Context context) {
        Context = context;
    }

    @Override
    public void subBalance(Spending spending) throws IOException {
        try {
            SmallPurseParameters parameters = JSONHelper.importFromJSON(Context);
            parameters.balance.Balance -= spending.Value;
            JSONHelper.exportToJSON(Context, parameters);
        } catch (IOException e)
        {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public void addBalance(Balance balance) throws IOException {
        try {
            SmallPurseParameters parameters = JSONHelper.importFromJSON(Context);
            parameters.balance.Balance += balance.Balance;
            JSONHelper.exportToJSON(Context, parameters);
        } catch (IOException e)
        {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public void setBalance(Balance balance) throws IOException {
        try {
            SmallPurseParameters parameters = JSONHelper.importFromJSON(Context);
            parameters.balance.Balance = balance.Balance;
            JSONHelper.exportToJSON(Context, parameters);
        } catch (IOException e)
        {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public Balance getBalance() throws IOException {
        try {
            SmallPurseParameters parameters = JSONHelper.importFromJSON(Context);
            return parameters.balance;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public String toString(Balance balance) {
        return balance.Balance + " руб";
    }
}


