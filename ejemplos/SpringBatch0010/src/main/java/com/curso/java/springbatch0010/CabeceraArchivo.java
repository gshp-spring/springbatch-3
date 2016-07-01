/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.java.springbatch0010;

import java.io.Serializable;

/**
 * Contenido de la cabecera de un archivo recibido.
 */
public class CabeceraArchivo implements Serializable {

    private String descripcion;
    private int cantidadRegistros;

    public int getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(int cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
