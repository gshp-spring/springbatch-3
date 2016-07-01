/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.java.springbatch0007;

import java.util.List;
import org.springframework.batch.core.listener.ItemListenerSupport;

public class PlanetaItemListener extends ItemListenerSupport<Planeta, Planeta> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onProcessError(Planeta item, Exception throwable) {
        throw new UnsupportedOperationException("El step no tiene configurado un processor");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onReadError(Exception throwable) {
        super.onReadError(throwable);

        System.out.println("WARNING!!! PlanetaItemListener - Exception en read" + throwable.getMessage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onWriteError(Exception throwable, List<? extends Planeta> item) {
        super.onWriteError(throwable, item);

        if (item == null || item.isEmpty()) {
            System.out.println("WARNING!!! PlanetaItemListener - El write recibió un item nulo" + throwable.getMessage());
            return;
        }

        for (Planeta planeta : item) {
            System.out.println("WARNING!!! PlanetaItemListener - Exception escribiendo el planeta: " + planeta.getNombre() + " - " + throwable.getMessage());
        }

    }
}
