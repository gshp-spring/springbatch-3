/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.java.springbatch0002;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.batch.item.ItemWriter;

public class ConsolaItemWriter implements ItemWriter<Planeta> {

    private static final Logger LOG = Logger.getLogger(ConsolaItemWriter.class.getName());

    @Override
    public void write(List<? extends Planeta> item) throws Exception {
        LOG.log(Level.INFO, "Elementos: {0}", item.size());
        item.forEach(planeta -> LOG.log(Level.INFO, "Planeta leido: {0}", planeta.getNombre()));
    }

}
