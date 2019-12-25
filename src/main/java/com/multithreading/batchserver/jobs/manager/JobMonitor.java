package com.multithreading.batchserver.jobs.manager;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class JobMonitor implements Runnable
{
    private ThreadPoolExecutor executor;
    private int seconds;
    private boolean run = true;

    public JobMonitor(ThreadPoolExecutor executor, int delay)
    {
        this.executor = executor;
        this.seconds = delay;
    }


    public void shutdown()
    {
        this.run = false;
    }


    @Override
    public void run()
    {
        while (run)
        {
            System.out.println(
                String.format(
                    "[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                    this.executor.getPoolSize(),
                    this.executor.getCorePoolSize(),
                    this.executor.getActiveCount(),
                    this.executor.getCompletedTaskCount(),
                    this.executor.getTaskCount(),
                    this.executor.isShutdown(),
                    this.executor.isTerminated()));

            for (Map.Entry me : JobManager.getInstance().getStatusMap().entrySet())
            {
                System.out.println("Key: " + me.getKey() + " & Value: " + JobManager.getInstance().getStatusMap().get(me.getKey()));
            }

            try
            {
                Thread.sleep(seconds * 3000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }
}
