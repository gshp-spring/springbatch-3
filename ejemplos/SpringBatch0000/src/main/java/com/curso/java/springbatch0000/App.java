package com.curso.java.springbatch0000;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        ejecutarConApplicationContext();
//        ejecutarConCommandLineJobRunner();
    }

    private static void ejecutarConApplicationContext() {
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-batch-demo.xml");
            System.out.println("Contexto cargado");
            JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");
            Job job = (Job) ctx.getBean("trabajoBatch");
            jobLauncher.run(job, new JobParameters());
            System.out.println("Job ejecutado");
        } catch (JobExecutionAlreadyRunningException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JobRestartException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JobInstanceAlreadyCompleteException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JobParametersInvalidException ex) {
        }
    }

    private static void ejecutarConCommandLineJobRunner() {

        try {
            CommandLineJobRunner.main(new String[]{"spring-batch-demo.xml", "trabajoBatch"});
            System.out.println("Job ejecutado");

        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
