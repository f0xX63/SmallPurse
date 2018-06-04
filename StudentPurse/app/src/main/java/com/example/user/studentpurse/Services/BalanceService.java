package com.example.user.studentpurse.Services;
import com.example.user.studentpurse.Domain.Balance;
import com.example.user.studentpurse.Domain.Spending;
import com.example.user.studentpurse.WorkOfFile.Repository;

import java.io.IOException;
import java.util.ArrayList;

public class BalanceService implements IBalanceService {

    Repository<Balance> balanceRepository;

    public BalanceService(Repository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public void subBalance(Spending spending) throws IOException {
        Balance balance = getBalance();
        balance.Balance -= spending.Value;
        ArrayList<Balance> balanceList = new ArrayList<Balance>();
        balanceList.add(balance);
            balanceRepository.saveAllData(balanceList);

    }

    @Override
    public void addBalance(Balance balance) throws IOException {
        Balance bal = getBalance();
        bal.Balance += balance.Balance;
        ArrayList<Balance> balanceList = new ArrayList<Balance>();
        balanceList.add(bal);
        try {
            balanceRepository.saveAllData(balanceList);
        } catch (IOException ex) {
            throw new IOException("При сохранении возникли ошибки");
        }
    }

    @Override
    public void setBalance(Balance balance) throws IOException {
        ArrayList<Balance> balances = new ArrayList<Balance>();
        balances.add(balance);
        try {
            balanceRepository.saveAllData(balances);
        } catch (IOException ex) {
            throw new IOException("При сохранении возникли ошибки");
        }
    }

    @Override
    public Balance getBalance() throws IOException {
        try {
            return balanceRepository.getAllData().get(0);
        } catch (IOException e) {
            throw new IOException((e.getMessage()));
        }
    }

    @Override
    public Boolean checkFileExists() {
        return balanceRepository.checkFileExists();
    }
}


