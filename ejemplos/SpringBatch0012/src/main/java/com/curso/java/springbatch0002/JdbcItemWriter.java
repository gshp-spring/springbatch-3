/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.java.springbatch0002;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
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

    @Autowired
    private DataSource ds;

    @Resource
    private ItemWriter<Planeta> consolaItemWriter;
    
    @Override
    public void write(List<? extends Planeta> items) throws Exception {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into nombres values (?,?)");
        for (Planeta planeta : items) {
            ps.setInt(1,planeta.getCodigo());
            ps.setString(2, planeta.getNombre() + System.currentTimeMillis());
            ps.addBatch();
        }
        ps.executeBatch();
        System.out.println("ExecuteBatch finalizado");
        consolaItemWriter.write(items);
    }
}
