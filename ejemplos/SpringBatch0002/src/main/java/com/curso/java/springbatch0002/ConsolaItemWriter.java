/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.java.springbatch0002;

import java.util.List;
import org.springframework.batch.item.ItemWriter;

public class ConsolaItemWriter implements ItemWriter<Planeta> {

    @Override
    public void write(List<? extends Planeta> item) throws Exception {
        item.forEach(planeta -> System.out.println("Planeta leido: " + planeta.getNombre()));
    }

}
