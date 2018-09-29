package com.curso.SpringBatch0015;

import javax.sql.DataSource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConfiguracionTest.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    StepScopeTestExecutionListener.class})
@AutoConfigureTestDatabase
public class SpringBatch0015ApplicationTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    // Por si necesitamos manipular la base de datos
    @Autowired
    private DataSource ds;

    @Test
    public void testDs() {
        assertNotNull(ds);
    }

    @Test
    public void testJob() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }

    @Test
    public void testStep() {
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("paso1");
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }

    @Autowired
    private FlatFileItemReader<Persona> reader;

    // Necesario para probar el reader.
    // Automáticamente invocado por StepScopeTestExecutionListener
    public StepExecution getStepExecution() {
        return MetaDataInstanceFactory.createStepExecution();
    }

    @Test
    public void testReader() throws Exception {
        reader.setResource(new ClassPathResource("personasTest.csv"));
        reader.open(new ExecutionContext());
        assertNotNull(reader.read());
        reader.close();
    }
}
