package com.multithreading.batchserver.jobs;

/**
 * Defines a piece of work to be executed on behalf of a customer
 */
public interface Job
{
    long uniqueId();

    //void execute();
}
