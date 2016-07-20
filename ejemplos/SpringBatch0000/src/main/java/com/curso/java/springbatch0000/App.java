package com.curso.java.springbatch0000;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;

/**
 * Hello world!
 *
 */
public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        try {            
            CommandLineJobRunner.main(new String[]{"spring-batch-demo.xml", "trabajoBatch"});
            LOG.log(Level.INFO, "Job ejecutado");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ha ocurrido un error", ex);
        }
    }
}
