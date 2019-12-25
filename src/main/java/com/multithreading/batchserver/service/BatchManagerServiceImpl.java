package com.multithreading.batchserver.service;

import com.multithreading.batchserver.jobs.Job;
import com.multithreading.batchserver.jobs.factory.JobFactory;
import com.multithreading.batchserver.jobs.manager.JobManager;
import com.multithreading.batchserver.type.JobResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.*;

@Service
public class BatchManagerServiceImpl implements BatchManagerService
{

    @Override
    public void execute(long customerId, long batchId) throws InterruptedException, ExecutionException
    {
        //find job
        Runnable runnable = JobFactory.getBatchJobType(batchId);
        Job job = (Job) runnable;

        //add to queue
        if (runnable != null)
        {
            System.out.println("Job Deque size : " + JobManager.getInstance().getJobDeque().size());
            JobManager.getInstance().getJobDeque().add(runnable);
        }

        //run job
        System.out.println("burdayim");
        Runnable runnableToNext = JobManager.getInstance().getJobDeque().takeLast();
        JobManager.getInstance().getThreadPoolExecutor().submit(runnableToNext);

        JobResult jobResult = new JobResult(customerId, batchId, LocalDateTime.now(), LocalDateTime.now(), null);

        //add map
        addResultMap(customerId, jobResult);
    }


    private void addResultMap(long customerId, JobResult jobResult)
    {
        if (JobManager.getInstance().getStatusMap().get(customerId) != null)
        {
            JobManager.getInstance().getStatusMap().get(customerId).add(jobResult);
        }
        else
        {
            CopyOnWriteArrayList<JobResult> jobResults = new CopyOnWriteArrayList<>();
            jobResults.add(jobResult);
            JobManager.getInstance().getStatusMap().put(customerId, jobResults);
        }
    }

}
