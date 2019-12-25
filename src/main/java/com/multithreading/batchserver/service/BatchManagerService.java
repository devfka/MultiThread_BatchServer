package com.multithreading.batchserver.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface BatchManagerService
{
    void execute(long customerId, long batchId) throws InterruptedException, ExecutionException;
}
