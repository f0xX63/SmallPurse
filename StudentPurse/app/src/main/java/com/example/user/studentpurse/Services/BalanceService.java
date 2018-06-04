package com.example.user.studentpurse.Services;

import android.content.Context;

import com.example.user.studentpurse.Domain.Balance;
import com.example.user.studentpurse.Domain.SmallPurseParameters;
import com.example.user.studentpurse.Domain.Spending;
import com.example.user.studentpurse.WorkOfFile.JSONHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BalanceService implements IBalanceService {

    Context Context;
    public BalanceService(Context context) {
        Context = context;
    }

    @Override
    public void subBalance(Spending spending) throws IOException {
        SmallPurseParameters parameters = JSONHelper.importFromJSON(Context);
        Balance[] balances = parameters.balances;
        for (int i = 0; i < balances.length; i++) {
            if (balances[i].Storage.equals(spending.Storage)) {
                balances[i].Balance -= spending.Value;
                break;
            }
        }
        parameters.balances = balances;
        JSONHelper.exportToJSON(Context, parameters);
    }

    @Override
    public void addBalance(Balance balance) throws IOException {
        SmallPurseParameters parameters = JSONHelper.importFromJSON(Context);
        Balance[] balances = parameters.balances;
        for (int i = 0; i < balances.length; i++) {
            if (balances[i].Storage.equals(balance.Storage)) {
                balances[i].Balance += balance.Balance;
                break;
            }
        }
        parameters.balances = balances;
        JSONHelper.exportToJSON(Context, parameters);
    }

    @Override
    public void setBalance(Balance balance) throws IOException {
        SmallPurseParameters parameters = JSONHelper.importFromJSON(Context);
        Balance[] balances = parameters.balances;
        for (int i = 0; i < balances.length; i++) {
            if (balances[i].Storage.equals(balance.Storage)) {
                balances[i].Balance = balance.Balance;
                break;
            }
        }
        parameters.balances = balances;
        JSONHelper.exportToJSON(Context, parameters);
    }

    @Override
    public Balance getBalance(String storage) throws IOException {
        SmallPurseParameters parameters = JSONHelper.importFromJSON(Context);
        Balance[] balances = parameters.balances;
        for (int i = 0; i < balances.length; i++) {
            if (balances[i].Storage.equals(storage)) {
                return balances[i];
            }
        }
        return null;
    }

    @Override
    public List<String> getAllStorage() throws IOException {
        SmallPurseParameters parameters = JSONHelper.importFromJSON(Context);
        List<String> storages = new ArrayList<String>();
        for (Balance balance: parameters.balances){
            storages.add(balance.Storage);
        }
        return storages;
    }

    @Override
    public String toString(Balance balance) {
        return balance.Balance + " руб";
    }

    @Override
    public Balance getCommonBalance() throws IOException {
        SmallPurseParameters parameters = JSONHelper.importFromJSON(Context);
        Balance[] balances = parameters.balances;
        Balance result = new Balance(0, "Общие");
        for (int i = 0; i < balances.length ; i++) {
            result.Balance+=balances[i].Balance;
        }
        return result;
    }
}


