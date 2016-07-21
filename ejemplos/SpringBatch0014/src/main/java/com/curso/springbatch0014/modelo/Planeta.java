/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springbatch0014.modelo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public final class Planeta {

    private int codigo;
    private String nombre;
    private long diametro;
    private String tipo;
    private String significado;

    public final Planeta setCodigo(int codigo) {
        this.codigo = codigo;
        return this;
    }

    public final Planeta setDiametro(long diametro) {
        this.diametro = diametro;
        return this;
    }

    public final Planeta setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public final Planeta setSignificado(String significado) {
        this.significado = significado;
        return this;
    }

    public final Planeta setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    @Override
    public final String toString() {
        return String.format(
                "El planeta %s (código %d), con %d km de diámetro, es de tipo %s. Su nombre significa %s.",
                nombre, codigo, diametro, tipo, significado
        );
    }

}
