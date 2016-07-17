/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.java.springbatch0004;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.batch.item.ItemWriter;

public class ConsolaItemWriter implements ItemWriter<Planeta> {

    private static final Logger LOG = Logger.getLogger(ConsolaItemWriter.class.getName());

    @Override
    public void write(List<? extends Planeta> list) throws Exception {
        LOG.log(Level.INFO, "Elementos: {0}", list.size());
        list.forEach((planeta) -> {
            System.out.println(planeta.getNombre());
        });
    }
}
