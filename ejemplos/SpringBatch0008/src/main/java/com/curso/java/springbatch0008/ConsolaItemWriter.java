/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.java.springbatch0008;

import java.util.List;
import org.springframework.batch.item.ItemWriter;

public class ConsolaItemWriter implements ItemWriter<Planeta> {

    public void write(List<? extends Planeta> item) throws Exception {
        for (Planeta planeta : item) {
            System.out.println("Thread: " + Thread.currentThread().getId() + " - Planeta leido: " + planeta.getNombre());
        }
    }
}
