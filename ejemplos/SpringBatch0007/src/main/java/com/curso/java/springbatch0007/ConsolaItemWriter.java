/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.java.springbatch0007;

import java.util.Collection;
import java.util.List;
import org.springframework.batch.item.ItemWriter;

public class ConsolaItemWriter implements ItemWriter<Planeta> {

    public void write(List<? extends Planeta> item) throws Exception {
        Collection<Planeta> col = (Collection<Planeta>) item;
        for (final Planeta planeta : col) {
            System.out.println("Planeta leido: " + planeta.getNombre());
        }
        throw new RuntimeException("Error en writer");
    }
}
