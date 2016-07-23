/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.java.springbatch0007;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.batch.core.listener.ItemListenerSupport;

public class PlanetaItemListener extends ItemListenerSupport<Planeta, Planeta> {

    private static final Logger LOG = Logger.getLogger(PlanetaItemListener.class.getName());

    @Override
    public void onProcessError(Planeta item, Exception throwable) {
        throw new UnsupportedOperationException("El step no tiene configurado un processor");
    }

    @Override
    public void onReadError(Exception throwable) {
        super.onReadError(throwable);
        LOG.log(Level.SEVERE, "ATENCIÓN!!! PlanetaItemListener - Exception en read: " + throwable.getMessage());
    }

    @Override
    public void onWriteError(Exception throwable, List<? extends Planeta> item) {
        super.onWriteError(throwable, item);

        if (item == null || item.isEmpty()) {
            LOG.log(Level.SEVERE, "ATENCI\u00d3N!!! PlanetaItemListener - El write recibi\u00f3 un item nulo{0}", throwable.getMessage());
            return;
        }

        item.forEach(
                planeta -> LOG.log(Level.SEVERE, "ATENCI\u00d3N!!! PlanetaItemListener - Exception escribiendo el planeta: {0} - {1}", new Object[]{planeta.getNombre(), throwable.getMessage()})
        );
    }
}
