package com.curso.java.springbatch0004;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Esta demo realiza las siguientes actividades: 1) ejecuta un query en la base
 * de datos contra la tabla PLANETA. 2) por cada fila del query: 2.1)
 * transformar la fila a un objeto Planeta 2.2) imprimir el objeto Planeta por
 * consola
 *
 * Este test necesita para funcionar que se encuentre configurada una base de
 * datos con las tablas de Spring Batch.
 *
 * Además se necesita tener creada la tabla PLANETA
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-batch-demo.xml"})
public class TablaHaciaConsolaTest {

    /**
     * Este objeto es el encargado de lanzar una tarea
     */
    @Autowired
    private SimpleJobLauncher launcher;

    /**
     * La tarea a ejecutar.
     */
    @Autowired
    private Job job;

    @Test
    public void iniciarJob() throws Exception {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addDate("Ejecucion", new Date());
        builder.addString("jobName", "Mostrar tabla por consola");
        JobParameters parameters = builder.toJobParameters();

        launcher.run(job, parameters);
    }
}
