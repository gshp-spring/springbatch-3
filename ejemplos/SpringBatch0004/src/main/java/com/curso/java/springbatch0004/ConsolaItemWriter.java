/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.java.springbatch0004;

import java.util.List;
import org.springframework.batch.item.ItemWriter;

public class ConsolaItemWriter implements ItemWriter<Planeta> {

    @Override
    public void write(List<? extends Planeta> list) throws Exception {
        list.forEach((planeta) -> {
            System.out.println(planeta.getNombre());
        });
    }
}
