package ekptg.scheduler;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {
	
	static Logger myLogger = Logger.getLogger("TestJob");

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		myLogger.info("Executing TestJob on : " + new Date());
		System.out.println("Executing TestJob on : " + new Date());
		
		//WRITE YOUR CODE HERE
		
		myLogger.info("Finish TestJob on : " + new Date());
		System.out.println("Finish TestJob on : " + new Date());
	}
}
