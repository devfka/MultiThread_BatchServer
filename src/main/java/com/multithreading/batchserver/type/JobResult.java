package com.multithreading.batchserver.type;

import java.time.LocalDateTime;
import java.util.concurrent.Future;

public class JobResult
{
    private long customerId;
    private long jobId;
    private LocalDateTime startTime;
    private LocalDateTime completionTime;
    private Future result;

    public JobResult(long customerId, long jobId, LocalDateTime startTime, LocalDateTime completionTime, Future result)
    {
        this.customerId = customerId;
        this.jobId = jobId;
        this.startTime = startTime;
        this.completionTime = completionTime;
        this.result = result;
    }

    public long getCustomerId()
    {
        return customerId;
    }


    public void setCustomerId(long customerId)
    {
        this.customerId = customerId;
    }


    public long getJobId()
    {
        return jobId;
    }


    public void setJobId(long jobId)
    {
        this.jobId = jobId;
    }


    public LocalDateTime getStartTime()
    {
        return startTime;
    }


    public void setStartTime(LocalDateTime startTime)
    {
        this.startTime = startTime;
    }


    public LocalDateTime getCompletionTime()
    {
        return completionTime;
    }


    public void setCompletionTime(LocalDateTime completionTime)
    {
        this.completionTime = completionTime;
    }


    public Future getResult()
    {
        return result;
    }


    public void setResult(Future result)
    {
        this.result = result;
    }


    @Override public String toString()
    {
        return "Job{" +
            "customerId=" + customerId +
            ", jobId=" + jobId +
            ", startTime=" + startTime +
            ", completionTime=" + completionTime +
            '}';
    }
}
