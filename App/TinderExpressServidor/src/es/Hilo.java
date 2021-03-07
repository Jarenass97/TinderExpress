/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es;

import auxiliar.Constantes;
import bd.Conexion;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SealedObject;
import object.Claves;
import object.Escritor;
import object.Usuario;
import seguridad.Seguridad;

/**
 *
 * @author jorge
 */
public class Hilo extends Thread {

    private Socket cliente;
    private Conexion conex;
    private Escritor e;
    private Claves claves;

    public Hilo(Socket cliente) throws Exception {
        this.cliente = cliente;
        this.conex = new Conexion();
        Claves claves = new Claves();
        Escritor e = new Escritor(cliente, claves);
        gestionClaves(claves, e);
        this.claves = claves;
        this.e = e;
    }

    @Override
    public void run() {
        DataInputStream dis = null;
        try {
            Conexion conex = new Conexion();
            Usuario u;
            while ((boolean) e.leer()) {
                int op = (int) e.leer();
                switch (op) {
                    case Constantes.REGISTRAR:
                        registrar();
                        u = (Usuario) e.leer();
                        conex.abrirConexion();
                        System.out.println("Insertando..." + u.toString());
                        conex.insertarUsuario(u.getEmail(), u.getNombre(), Seguridad.Hexadecimal(u.getPassResumida()), u.getFechaNac(), u.getRol());
                        conex.cerrarConexion();
                        e.escribir(true);
                        break;
                    case Constantes.LOGEAR:
                        u = (Usuario) e.leer();
                        String email = u.getEmail();
                        String pass = Seguridad.Hexadecimal(u.getPassResumida());
                        conex.abrirConexion();
                        String passEnBDD = conex.obtenerValor(Constantes.TablaUsuarios, where(Constantes.usuariosEmail, "=", email), Constantes.usuariosPass);
                        System.out.println(pass+"  |  "+passEnBDD);
                        if (MessageDigest.isEqual(pass.getBytes(), passEnBDD.getBytes())) {
                            e.escribir(true);
                        } else {
                            e.escribir(false);
                        }
                        conex.cerrarConexion();
                        break;

                }
            }
            System.out.println("Cliente desconectado");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                cliente.close();
            } catch (IOException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void registrar() {

    }

    private void gestionClaves(Claves claves, Escritor escritor) throws Exception {
        //Generamos ambas claves
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair par = keyGen.generateKeyPair();
        claves.setPrivada(par.getPrivate());
        claves.setPublica(par.getPublic());

        //Recibimos la clave del otro extremo
        claves.setOtroExtremo((PublicKey) escritor.ois().readObject());
        //Mandamos la clave publica al otro extremo
        escritor.oos().writeObject(claves.getPublica());
    }

    private String where(String campo, String comparador, String valor) {
        return campo + " " + comparador + " '" + valor + "'";
    }

}
