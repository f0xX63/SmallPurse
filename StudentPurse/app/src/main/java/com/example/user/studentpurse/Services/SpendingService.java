package com.example.user.studentpurse.Services;

import android.content.Context;

import com.example.user.studentpurse.Domain.SmallPurseParameters;
import com.example.user.studentpurse.Domain.Spending;
import com.example.user.studentpurse.WorkOfFile.JSONHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpendingService implements ISpendingService{

    Context context;
    public SpendingService(Context context)
    {
        this.context = context;
    }
    @Override
    public void addSpending(Spending spending) throws IOException {
        SmallPurseParameters parameters = JSONHelper.importFromJSON(context);
        List<Spending> spendings = new ArrayList<>(Arrays.asList(parameters.spendings));
        spendings.add(spending);
        parameters.spendings = new Spending[spendings.size()];
        parameters.spendings = spendings.toArray(parameters.spendings);
        JSONHelper.exportToJSON(context, parameters);
    }

    @Override
    public void deleteSpending(Spending spending) throws IOException {
        /*ArrayList<Spending> spendings = (ArrayList<Spending>) repositorySpending.getAllData();
        spendings.remove(spending);
        repositorySpending.saveAllData(spendings);*/
    }

    @Override
    public int getLastId() throws IOException {
       SmallPurseParameters parameters = JSONHelper.importFromJSON(context);
       return parameters.spendings.length;
    }
}
