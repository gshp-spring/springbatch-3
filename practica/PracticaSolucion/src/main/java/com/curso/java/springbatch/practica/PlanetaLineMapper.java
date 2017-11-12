/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.java.springbatch.practica;

import java.util.Map;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.JsonLineMapper;

/**
 *
 * @author usuario
 */
public class PlanetaLineMapper implements LineMapper<Planeta> {

    private JsonLineMapper jsonLineMapper;

    /**
     *
     * @param line
     * @param lineNumber
     * @return
     * @throws Exception
     */
    @Override
    public Planeta mapLine(String line, int lineNumber) throws Exception {
        Map<String, Object> mapa = jsonLineMapper.mapLine(line, lineNumber);
        Planeta planeta = new Planeta();
        planeta.setCodigo((int) mapa.getOrDefault("codigo",0));
        planeta.setNombre(mapa.getOrDefault("nombre","nada").toString());
        return planeta;
    }

    public void setJsonLineMapper(JsonLineMapper jsonLineMapper) {
        this.jsonLineMapper = jsonLineMapper;
    }
}
