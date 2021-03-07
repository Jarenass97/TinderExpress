/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jorge
 */
public class Usuario implements Serializable{

    private String email,nombre,fechaNac;    
    private byte[] passResumida;
    private int rol;

    public Usuario(String email, String nombre, String fechaNac, byte[] passResumida,int rol) {
        this.email = email;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.passResumida = passResumida;
        this.rol=rol;
    }

    public Usuario(String email, byte[] passResumida) {
        this.email = email;
        this.passResumida = passResumida;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public byte[] getPassResumida() {
        return passResumida;
    }

    public void setPassResumida(byte[] passResumida) {
        this.passResumida = passResumida;
    }    

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", nombre=" + nombre + ", fechaNac=" + fechaNac + ", passResumida=" + passResumida + ", rol=" + rol + '}';
    }        
    
    public boolean validarEmail(String email){
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }

}
