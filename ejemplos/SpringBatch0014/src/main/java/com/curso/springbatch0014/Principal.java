/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springbatch0014;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;

/**
 *
 * @author khepherer
 */
public class Principal {

    private static final Logger LOG = Logger.getLogger(Principal.class.getName());

    public static void main(String... args) {
        try {
            LOG.log(Level.INFO, "Arrancando el job...");
            CommandLineJobRunner.main(
                    new String[]{
                        "spring-batch-demo.xml",
                        "trabajoBatch",
                        "fecha=" + new Date(),
                        "id=" + System.nanoTime()}
            );
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ha ocurrido un error", ex);
        }
    }

}
