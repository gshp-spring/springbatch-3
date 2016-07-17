package com.curso.springbatch0013;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger LOG = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOG.info("!!! JOB FINALIZADO! Verificando resultados en la BD");

			List<Persona> resultados = jdbcTemplate.query("SELECT nombre, apellido FROM personas", new RowMapper<Persona>() {
				@Override
				public Persona mapRow(ResultSet rs, int row) throws SQLException {
					return new Persona(rs.getString(1), rs.getString(2));
				}
			});

			for (Persona persona : resultados) {
				LOG.info("Encontrada <" + persona + "> en la BD.");
			}

		}
	}
}
