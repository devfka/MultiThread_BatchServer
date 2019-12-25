package com.multithreading.batchserver.jobs;

import com.multithreading.batchserver.constants.Constants;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class BillGeneration implements Job, Runnable
{
    private String word = StringUtils.EMPTY;
    private AtomicInteger dailyLimit = new AtomicInteger(0);


    public void add(String value)
    {
        synchronized (this)
        {
            this.word = this.word + value;
        }
    }


    @Override public long uniqueId()
    {
        return Constants.BillGenerationJobId;
    }


    @Override public void run()
    {
        if (this.dailyLimit.intValue() < 5)
        {
            for (int i = 0; i < 5; i++)
            {

                this.add(RandomStringUtils.randomAlphabetic(i));

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
        System.out.println(Thread.currentThread().getName() + " --- Batch job Id :" + this.uniqueId() + " --- word : " + this.word);

    }
}
