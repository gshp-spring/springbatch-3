/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.java.springbatch0002;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaslang.API.Match;
import javaslang.control.Try;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author formacion
 */
/*
Esta lógica podría haberse hecho empleando "JdbcBatchItemWriter" predefinido
por Spring Batch sin codificar nada en Java, sino por configuración
 */
@Component
public class JdbcItemWriter implements ItemWriter<Planeta> {

    private static final Logger LOG = Logger.getLogger(JdbcItemWriter.class.getName());

    @Autowired
    private DataSource ds;

    @Resource
    private ItemWriter<Planeta> consolaItemWriter;

    @PostConstruct
    private void init() {
        try {
            ds.getConnection().createStatement().executeUpdate("delete from nombres");
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void write(List<? extends Planeta> items) throws Exception {
        Try.of(() -> escribirPlanetas(items)).getOrElseThrow(
                ex -> new Exception("Error de acceso a la base de datos: " + ex.getMessage(),ex)
        );
        LOG.log(Level.INFO,"ExecuteBatch finalizado");
        consolaItemWriter.write(items);
    }

    private int[] escribirPlanetas(List<? extends Planeta> items) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into nombres values (?,?)");
        for (Planeta planeta : items) {
            ps.setInt(1, planeta.getCodigo());
            ps.setString(2, planeta.getNombre() + System.currentTimeMillis());
            ps.addBatch();
        }
        return ps.executeBatch();
    }
}
