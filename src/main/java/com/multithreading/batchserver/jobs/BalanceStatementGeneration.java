package com.multithreading.batchserver.jobs;

import com.multithreading.batchserver.constants.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class BalanceStatementGeneration implements Job, Runnable
{
    private String star = StringUtils.EMPTY;


    public void add()
    {
        synchronized (this)
        {
            this.star = this.star + "*";
        }
    }


    @Override public long uniqueId()
    {
        return Constants.BalanceStatementJobId;
    }


    @Override public void run()
    {
        for (int i = 0; i < 5; i++)
        {
            this.add();
            try
            {
                Thread.sleep(2L * 1000L);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " --- Batch job Id :" + this.uniqueId() + " --- star : " + this.star);

    }
}
