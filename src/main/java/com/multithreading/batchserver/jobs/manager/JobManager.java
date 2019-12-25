package com.multithreading.batchserver.jobs.manager;

import com.multithreading.batchserver.constants.Constants;
import com.multithreading.batchserver.type.JobResult;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class JobManager
{
    private volatile static JobManager jobManagerInstance;
    private BlockingDeque<Runnable> jobDeque = new LinkedBlockingDeque<>(1000);
    //private ExecutorService executorService = Executors.newFixedThreadPool(Constants.Thread_Pool_Size, new LinkedBlockingQueue<Runnable>());
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Constants.Core_Thread_Pool_Size, Constants.Max_Thread_Pool_Size, 1, TimeUnit.MINUTES, jobDeque);
    private ConcurrentHashMap<Long, CopyOnWriteArrayList<JobResult>> statusMap = new ConcurrentHashMap<>();
    private AtomicInteger dailyLimit = new AtomicInteger(0);

    public synchronized BlockingDeque<Runnable> getJobDeque()
    {
        return jobDeque;
    }

    public synchronized ConcurrentHashMap<Long, CopyOnWriteArrayList<JobResult>> getStatusMap()
    {
        return statusMap;
    }

    public synchronized ThreadPoolExecutor getThreadPoolExecutor()
    {
        return threadPoolExecutor;
    }

    public int getDailyLimit()
    {
        System.out.println(this.dailyLimit);
        return this.dailyLimit.incrementAndGet();
    }

    public static synchronized JobManager getInstance()
    {
        if (jobManagerInstance == null)
        {
            synchronized (JobManager.class)
            {
                if (jobManagerInstance == null)
                {
                    jobManagerInstance = new JobManager();
                    System.out.println("Job manager class has been initialized");
                }
            }
        }

        return jobManagerInstance;
    }
}
