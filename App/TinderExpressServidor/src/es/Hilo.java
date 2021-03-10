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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SealedObject;
import object.*;
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
                        break;
                    case Constantes.LOGEAR:
                        loggear();
                        break;
                    case Constantes.GET_USER:
                        getUser();
                        break;
                    case Constantes.SAVEPREFERENCES:
                        savePreferences();
                        break;
                    case Constantes.GET_PREFERENCE:
                        getPreference();
                        break;
                    case Constantes.COMPROBAR_PRIMERA:
                        comprobarPrimera();
                        break;
                    case Constantes.CAMBIAR_PASSWORD:
                        cambiarPassword();
                        break;
                    case Constantes.MODIFICAR_PERFIL:
                        modPerfil();
                        break;
                    case Constantes.CARGAR_AFINES:
                        cargarAfines();
                        break;
                    case Constantes.SOLICITAR_AMISTAD:
                        solicitarAmistad();
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

    private void registrar() throws Exception {
        Usuario u = (Usuario) e.leer();
        conex.abrirConexion();
        conex.insertarUsuario(u.getEmail(), u.getNombre(), Seguridad.Hexadecimal(u.getPassResumida()), u.getFechaNac(), u.getRol());
        conex.cerrarConexion();
        e.escribir(true);
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

    private void loggear() throws Exception {
        Usuario u = (Usuario) e.leer();
        String email = u.getEmail();
        String pass = Seguridad.Hexadecimal(u.getPassResumida());
        conex.abrirConexion();
        String passEnBDD = conex.obtenerValor(Constantes.TablaUsuarios, where(Constantes.usuariosEmail, "=", email), Constantes.usuariosPass);
        if (MessageDigest.isEqual(pass.getBytes(), passEnBDD.getBytes())) {
            e.escribir(true);
        } else {
            e.escribir(false);
        }
        conex.cerrarConexion();
    }

    private void getUser() throws Exception {
        String id = (String) e.leer();
        conex.abrirConexion();
        Usuario u = conex.getUsuario(where(Constantes.usuariosEmail, "=", id));
        conex.cerrarConexion();
        e.escribir(u);
    }

    private void savePreferences() throws Exception {
        Preferencia p = (Preferencia) e.leer();
        conex.abrirConexion();
        if (!conex.existePreferencia(where(Constantes.preferenciasEmail, "=", p.getEmail()))) {
            conex.insertarPreferencia(p);
        } else {
            conex.modPreferencia(p);
        }
        conex.cerrarConexion();
        e.escribir(true);
    }

    private void getPreference() throws Exception {
        String id = (String) e.leer();
        conex.abrirConexion();
        Preferencia p = conex.getPreferencia(where(Constantes.preferenciasEmail, "=", id));
        conex.cerrarConexion();
        e.escribir(p);
    }

    private void comprobarPrimera() throws Exception {
        Usuario u = (Usuario) e.leer();
        conex.abrirConexion();
        boolean primeraVez = !conex.existePreferencia(where(Constantes.preferenciasEmail, "=", u.getEmail()));
        conex.cerrarConexion();
        e.escribir(primeraVez);
    }

    private void cambiarPassword() throws Exception {
        Usuario u = (Usuario) e.leer();
        String passActual = Seguridad.Hexadecimal((byte[]) e.leer());
        String nuevaPass = Seguridad.Hexadecimal((byte[]) e.leer());
        conex.abrirConexion();
        String passEnBDD = conex.obtenerValor(Constantes.TablaUsuarios, where(Constantes.usuariosEmail, "=", u.getEmail()), Constantes.usuariosPass);
        if (MessageDigest.isEqual(passActual.getBytes(), passEnBDD.getBytes())) {
            conex.modificarDato(Constantes.TablaUsuarios, Constantes.usuariosPass, where(Constantes.usuariosEmail, "=", u.getEmail()), nuevaPass);
            e.escribir(true);
        } else {
            e.escribir(false);
        }
        conex.cerrarConexion();
    }

    private void modPerfil() throws Exception {
        Usuario u = (Usuario) e.leer();
        String email = u.getEmail();
        conex.abrirConexion();
        conex.modificarDato(Constantes.TablaUsuarios, Constantes.usuariosNombre, where(Constantes.usuariosEmail, "=", email), u.getNombre());
        conex.modificarDato(Constantes.TablaUsuarios, Constantes.usuariosFecha_Nac, where(Constantes.usuariosEmail, "=", email), u.getFechaNac());
        conex.modificarDato(Constantes.TablaUsuarios, Constantes.usuariosEmail, where(Constantes.usuariosEmail, "=", email), u.getEmail());
        conex.cerrarConexion();
    }

    private void cargarAfines() throws Exception {
        Usuario conectado = (Usuario) e.leer();
        conex.abrirConexion();
        Preferencia pConectado = conex.getPreferencia(where(Constantes.preferenciasEmail, "=", conectado.getEmail()));
        conex.obtenerDatosTabla(Constantes.TablaUsuarios);
        ArrayList<Usuario> usuarios = conex.listaUsuarios(conectado);
        ArrayList<Usuario> enviados = new ArrayList<>();
        for (Usuario u : usuarios) {
            Preferencia p = conex.getPreferencia(where(Constantes.preferenciasEmail, "=", u.getEmail()));
            if (p != null) {
                if (muyPreferente(pConectado, p)) {
                    e.escribir(true);
                    e.escribir(u);
                    enviados.add(u);
                }
            }
        }
        for (Usuario u : usuarios) {
            if (!userSend(u, enviados)) {
                Preferencia p = conex.getPreferencia(where(Constantes.preferenciasEmail, "=", u.getEmail()));
                if (p != null) {
                    if (Preferente(pConectado, p)) {
                        e.escribir(true);
                        e.escribir(u);
                        enviados.add(u);
                    }
                }
            }
        }
        for (Usuario u : usuarios) {
            if (!userSend(u, enviados)) {
                Preferencia p = conex.getPreferencia(where(Constantes.preferenciasEmail, "=", u.getEmail()));
                e.escribir(true);
                e.escribir(u);
                enviados.add(u);
            }
        }
        e.escribir(false);
        conex.cerrarConexion();
    }

    private boolean muyPreferente(Preferencia pref_conectado, Preferencia pref_amigo) {
        boolean pref = false;
        if (pref_conectado.isRelacionSeria() == pref_amigo.isRelacionSeria() && pref_conectado.isQuiereHijos() == pref_amigo.isQuiereHijos()) {
            if (gustosAfines(pref_conectado, pref_amigo, 10)) {
                pref = true;
            }
        }
        System.out.println(pref);
        return pref;
    }

    private boolean gustosAfines(Preferencia pref_conectado, Preferencia pref_amigo, int diferencia) {
        int gustoDepConec = pref_conectado.getDeportivos();
        int gustoArtConec = pref_conectado.getArtisticos();
        int gustoPolConec = pref_conectado.getPoliticos();
        return (pref_amigo.getDeportivos() <= gustoDepConec + diferencia && pref_amigo.getDeportivos() >= gustoDepConec - diferencia)
                || (pref_amigo.getArtisticos() <= gustoArtConec + diferencia && pref_amigo.getArtisticos() >= gustoArtConec - diferencia)
                || (pref_amigo.getPoliticos() <= gustoPolConec + diferencia && pref_amigo.getPoliticos() >= gustoPolConec - diferencia);
    }

    private boolean userSend(Usuario u, ArrayList<Usuario> enviados) {
        boolean encontrado = false;
        for (int i = 0; i < enviados.size() && !encontrado; i++) {
            if (u.getEmail().equals(enviados.get(i).getEmail())) {
                encontrado = true;
            }
        }
        return encontrado;

    }

    private boolean Preferente(Preferencia pref_conectado, Preferencia pref_amigo) {
        boolean pref = false;
        if (pref_conectado.isRelacionSeria() == pref_amigo.isRelacionSeria() && pref_conectado.isQuiereHijos() == pref_amigo.isQuiereHijos()) {
            if (gustosAfines(pref_conectado, pref_amigo, 25)) {
                pref = true;
            }
        }
        System.out.println(pref);
        return pref;
    }

    private void solicitarAmistad() throws Exception {
        SolicitudAmistad sa =  (SolicitudAmistad) e.leer();        
        conex.abrirConexion();
        conex.insertaSolicitudAmistad(sa);
        conex.cerrarConexion();
    }

}
