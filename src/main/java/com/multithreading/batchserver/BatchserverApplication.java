package com.multithreading.batchserver;

import com.multithreading.batchserver.constants.Constants;
import com.multithreading.batchserver.jobs.manager.JobManager;
import com.multithreading.batchserver.jobs.manager.JobMonitor;
import com.multithreading.batchserver.service.BatchManagerServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Future;

@SpringBootApplication
public class BatchserverApplication implements CommandLineRunner
{

	public static void main(String[] args) {
		SpringApplication.run(BatchserverApplication.class, args);
	}


	@Override public void run(String... args) throws Exception
	{
		JobMonitor monitorJob = new JobMonitor(JobManager.getInstance().getThreadPoolExecutor(), 1);
		new Thread(monitorJob).start();

		BatchManagerServiceImpl batchManagerService = new BatchManagerServiceImpl();

		batchManagerService.execute(1, Constants.RiskReportJobId);
		batchManagerService.execute(2, Constants.BillGenerationJobId);
		batchManagerService.execute(3, Constants.BalanceStatementJobId);
		batchManagerService.execute(4, Constants.RiskReportJobId);
		batchManagerService.execute(5, Constants.BillGenerationJobId);
		batchManagerService.execute(6, Constants.BalanceStatementJobId);
		batchManagerService.execute(7, Constants.RiskReportJobId);
		batchManagerService.execute(8, Constants.BillGenerationJobId);
		batchManagerService.execute(9, Constants.BalanceStatementJobId);
		batchManagerService.execute(10, Constants.RiskReportJobId);
		batchManagerService.execute(11, Constants.BillGenerationJobId);
		batchManagerService.execute(12, Constants.BalanceStatementJobId);
		batchManagerService.execute(13, Constants.RiskReportJobId);
		batchManagerService.execute(14, Constants.BillGenerationJobId);
		batchManagerService.execute(15, Constants.BalanceStatementJobId);
	}
}
