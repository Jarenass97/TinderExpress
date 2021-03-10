/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.Serializable;

/**
 *
 * @author jorge
 */
public class Mensaje implements Serializable {

    private String transmisor, receptor, contenido;

    public Mensaje(String transmisor, String receptor, String contenido) {
        this.transmisor = transmisor;
        this.receptor = receptor;
        this.contenido = contenido;
    }

    public Mensaje() {
    }

    public String getTransmisor() {
        return transmisor;
    }

    public void setTransmisor(String transmisor) {
        this.transmisor = transmisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

}
