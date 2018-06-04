package com.example.user.studentpurse.Services;

import com.example.user.studentpurse.Domain.Spending;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IStatisticService {
    List<Spending> getAllStatistics() throws IOException;
    List<Spending> getStatisticForPeriod(Date begin, Date end) throws IOException;
}
