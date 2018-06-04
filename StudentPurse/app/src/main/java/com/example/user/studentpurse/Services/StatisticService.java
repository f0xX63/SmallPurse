package com.example.user.studentpurse.Services;

import com.example.user.studentpurse.Domain.Spending;
import com.example.user.studentpurse.WorkOfFile.Repository;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatisticService implements IStatisticService
{
    Repository<Spending> statisticRepository;
    ISpendingService spendingService;

    public StatisticService(Repository<Spending> statisticRepository, ISpendingService spendingService)
    {
        this.statisticRepository = statisticRepository;
        this.spendingService = spendingService;
    }
    @Override
    public List<Spending> getAllStatistics() throws IOException {
        List<Spending> allStatistics = null;
        try {
            allStatistics = statisticRepository.getAllData();
            return allStatistics;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

    }

    @Override
    public List<Spending> getStatisticForPeriod(Date begin, Date end) throws IOException {
        try {
            List<Spending> spendings = statisticRepository.getAllData();
            List<Spending> result = new ArrayList<Spending>();
            for (Spending spend : spendings) {
                if (spend.Date.before(end) && spend.Date.after(begin)) {
                    result.add(spend);
                }
            }
            return result;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

    }
}

