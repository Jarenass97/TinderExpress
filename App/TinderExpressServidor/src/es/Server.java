/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es;

import bd.Conexion;
import java.net.*;
import java.security.*;
import java.sql.*;
import javax.crypto.SealedObject;
import object.Usuario;
import seguridad.Seguridad;

/**
 *
 * @author jorge
 */
public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(1050);
        System.out.println("Servidor iniciado...");
        Conexion conex=new Conexion();
        conex.abrirConexion();
        while (true) {
            Socket cliente = servidor.accept();
            Hilo h = new Hilo(cliente);
            h.start();
        }
    }   
}
