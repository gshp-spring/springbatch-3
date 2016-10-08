/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springbatch0014;

import com.curso.springbatch0014.modelo.Planeta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author khepherer
 */
@Component
public class CsvItemReader implements ItemReader<Planeta> {

    private static final Logger LOG = Logger.getLogger(CsvItemReader.class.getName());

    private BufferedReader reader;

    @PostConstruct
    private void init() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("planetas.csv");
        LOG.log(Level.INFO, "Archivo 'planetas.csv' encontrado");
        reader = new BufferedReader(new InputStreamReader(stream));
    }

    @PreDestroy
    private void limpiar() {
        if (reader != null) {
            try {
                reader.close();
                LOG.log(Level.INFO, "Archivo cerrado");
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Planeta read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return Optional.ofNullable(reader.readLine())
                .map(linea -> factoriaPlanetas(linea))
                .orElse(null);
    }

    @Autowired
    private ApplicationContext ctx;

    private Planeta factoriaPlanetas(String linea) throws NumberFormatException, BeansException {
        String[] valores = linea.split(",");
        return ctx.getBean(Planeta.class)
                .setCodigo(Integer.parseInt(valores[0]))
                .setNombre(valores[1])
                .setDiametro(Long.parseLong(valores[2]))
                .setTipo(valores[3])
                .setSignificado(valores[4]);
    }
}
