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
public class Preferencia implements Serializable{

    private String email;
    private int deportivos, artisticos, politicos;
    private boolean relacionSeria, quiereHijos;

    public Preferencia(String email, int deportivos, int artisticos, int politicos, boolean relacionSeria, boolean quiereHijos) {
        this.email = email;
        this.deportivos = deportivos;
        this.artisticos = artisticos;
        this.politicos = politicos;
        this.relacionSeria = relacionSeria;
        this.quiereHijos = quiereHijos;
    }

    public Preferencia() {        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDeportivos() {
        return deportivos;
    }

    public void setDeportivos(int deportivos) {
        this.deportivos = deportivos;
    }

    public int getArtisticos() {
        return artisticos;
    }

    public void setArtisticos(int artisticos) {
        this.artisticos = artisticos;
    }

    public int getPoliticos() {
        return politicos;
    }

    public void setPoliticos(int politicos) {
        this.politicos = politicos;
    }

    public boolean isRelacionSeria() {
        return relacionSeria;
    }

    public void setRelacionSeria(boolean relacionSeria) {
        this.relacionSeria = relacionSeria;
    }

    public boolean isQuiereHijos() {
        return quiereHijos;
    }

    public void setQuiereHijos(boolean quiereHijos) {
        this.quiereHijos = quiereHijos;
    }

}
