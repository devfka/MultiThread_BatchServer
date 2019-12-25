package com.multithreading.batchserver.jobs;

import com.multithreading.batchserver.constants.Constants;

import java.util.concurrent.atomic.AtomicInteger;

public class RiskReportGeneration implements Job, Runnable
{
    private int count = 0;
    private AtomicInteger dailyLimit = new AtomicInteger(0);

    public void add(int value)
    {
        synchronized (this)
        {
            this.count = this.count + value;
        }
    }

    @Override public long uniqueId()
    {
        return Constants.RiskReportJobId;
    }

    @Override public void run()
    {
        if(this.dailyLimit.intValue() < 15)
        {
            for (int i = 0; i < 5; i++)
            {
                this.add((int) this.uniqueId());

                try
                {
                    Thread.sleep(2L * 1000L);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + " --- Batch job Id :" + this.uniqueId() + " --- count : " + this.count);

    }
}
