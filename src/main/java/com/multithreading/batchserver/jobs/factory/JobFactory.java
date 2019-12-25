package com.multithreading.batchserver.jobs.factory;

import com.multithreading.batchserver.constants.Constants;
import com.multithreading.batchserver.jobs.BalanceStatementGeneration;
import com.multithreading.batchserver.jobs.BillGeneration;
import com.multithreading.batchserver.jobs.RiskReportGeneration;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.concurrent.Callable;

public class JobFactory
{
    public static Runnable getBatchJobType(long batchId)
    {
        if (batchId == Constants.BalanceStatementJobId)
        {
            return new BalanceStatementGeneration();
        }
        else if (batchId == Constants.BillGenerationJobId)
        {
            return new BillGeneration();
        }
        else if (batchId == Constants.RiskReportJobId)
        {
            return new RiskReportGeneration();
        }
        return null;
    }
}
