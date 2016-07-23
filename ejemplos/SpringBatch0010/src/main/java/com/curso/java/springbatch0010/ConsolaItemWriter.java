/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.java.springbatch0010;

import java.util.List;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

public class ConsolaItemWriter implements ItemWriter<Planeta> {

    private StepExecution stepExecution;

    private int numeroInformado = 1;

    public int getNumeroInformado() {
        return numeroInformado;
    }

    public void setNumeroInformado(int numeroInformado) {
        this.numeroInformado = numeroInformado;
    }

    public StepExecution getStepExecution() {
        return stepExecution;
    }

    @Override
    public void write(List<? extends Planeta> item) throws Exception {

        CabeceraArchivo cabeceraArchivo = (CabeceraArchivo) getStepExecution().getExecutionContext().get(CabeceraHandler.KEY_CABECERA);
        item.stream().map(planeta -> {
            System.out.println("Registro " + getNumeroInformado() + " de "
                    + cabeceraArchivo.getCantidadRegistros() + ". "
                    + cabeceraArchivo.getDescripcion() + ": " + planeta.getNombre());
            return planeta;
        }).forEach((_item) -> {
//            System.out.println("_Item: " + _item);
            setNumeroInformado(getNumeroInformado() + 1);
        });
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

}
