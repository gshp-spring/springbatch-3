/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springbatch0014.config;

import com.curso.springbatch0014.modelo.Planeta;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import javax.sql.DataSource;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author khepherer
 */
@Configuration
@PropertySource("bd.properties")
public class Configuracion {

    @Bean(name = "consolaItemWriter")
    public ItemWriter<Planeta> daIgual() {
        return items -> items.forEach(planeta -> System.out.println(planeta));
    }

    @Autowired
    private Environment e;
    
    @Bean(name = "dataSource")
    public DataSource daIgual1() throws PropertyVetoException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setDriverClass(e.getProperty("driver"));
        ds.setJdbcUrl(e.getProperty("url"));
        ds.setUser(e.getProperty("usuario"));
        ds.setPassword(e.getProperty("clave"));
        ds.setMaxPoolSize(Integer.parseInt(e.getProperty("max")));
        ds.setMinPoolSize(Integer.parseInt(e.getProperty("min")));
        ds.setMaxStatements(Integer.parseInt(e.getProperty("maxSentencias")));
        ds.setTestConnectionOnCheckout(Boolean.parseBoolean(e.getProperty("testOut")));
        return ds;
    }

    @Bean(name = "taskExecutor")
    public TaskExecutor daiGual2() {
        SimpleAsyncTaskExecutor te = new SimpleAsyncTaskExecutor();
        te.setConcurrencyLimit(5);
        return te;
    }
    
    @Bean(name = "transactionManager",autowire = Autowire.BY_TYPE)
    @Lazy(true)
    public PlatformTransactionManager daIgual3(){
        return new DataSourceTransactionManager();
    }
}
