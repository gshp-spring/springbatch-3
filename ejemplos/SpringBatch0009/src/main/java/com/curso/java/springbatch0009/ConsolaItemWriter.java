/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.java.springbatch0009;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.batch.item.ItemWriter;

public class ConsolaItemWriter implements ItemWriter<Planeta> {

    private static final Logger LOG = Logger.getLogger(ConsolaItemWriter.class.getName());

    @Override
    public void write(List<? extends Planeta> item) throws Exception {
        item.forEach(
                planeta -> LOG.log(Level.INFO, "Planeta: {0}. Significado: {1}", new Object[]{planeta.getNombre(), planeta.getSignificado()})
        );
    }
}
