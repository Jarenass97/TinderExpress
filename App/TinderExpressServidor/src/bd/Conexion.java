package bd;

import auxiliar.Constantes;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import object.Mensaje;
import object.Preferencia;
import object.SolicitudAmistad;
import object.Usuario;

public class Conexion {

    //********************* Atributos *************************
    private java.sql.Connection Conex;
    //Atributo a través del cual hacemos la conexión física.
    private java.sql.Statement Sentencia_SQL;
    //Atributo que nos permite ejecutar una sentencia SQL
    private java.sql.ResultSet Conj_Registros;
    //(Cursor) En él están almacenados los datos.

    //********************** Constructores **************************
    //----------------------------------------------------------
    public Conexion() {
        //this.abrirConexion();
    }

    public void abrirConexion() {
        try {
            //Cargar el driver/controlador
            String controlador = "com.mysql.jdbc.Driver";
            Class.forName(controlador).newInstance();
            String URL_BD = "jdbc:mysql://localhost:3306/" + Constantes.bbdd;
            //Realizamos la conexión a una BD con un usuario y una clave.
            Conex = java.sql.DriverManager.getConnection(URL_BD, Constantes.usuariobd, Constantes.passwdbd);
            Sentencia_SQL = Conex.createStatement();
            System.out.println("Conexión realizada con éxito");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //********************** Métodos **************************
    //----------------------------------------------------------
    public int obtenerDatosTabla(String nom_tabla) {
        int cod = 0;
        String Sentencia = "SELECT * FROM " + nom_tabla;
        try {
            Conj_Registros = Sentencia_SQL.executeQuery(Sentencia);
        } catch (SQLException ex) {
            cod = ex.getErrorCode();
        }
        return cod;
    }

    //----------------------------------------------------------
    public void mostrarFilaActual() {
        int i, Num_Cols;
        try {
            Num_Cols = Conj_Registros.getMetaData().getColumnCount();
            for (i = 1; i <= Num_Cols; i++) {
                System.out.println(Conj_Registros.getString(i));
            }
        } catch (SQLException ex) {
        }
    }

    //----------------------------------------------------------
    public void mostrarTabla(String tabla) {
        try {
            obtenerDatosTabla(tabla);

            //Conj_Registros.first();
            while (Conj_Registros.next()) {
                mostrarFilaActual();
            }
        } catch (SQLException ex) {
        }
    }

    //----------------------------------------------------------
    public int modificarDato(String tabla, String campo, String where, String Nuevo_Nombre) {
        int cod = 0;
        String Sentencia = "UPDATE " + tabla + " SET " + campo + " = '" + Nuevo_Nombre + "' WHERE " + where;        
        try {
            Sentencia_SQL.executeUpdate(Sentencia);
        } catch (SQLException ex) {
            cod = ex.getErrorCode();
        }
        return cod;
    }

    //----------------------------------------------------------
    public int insertarUsuario(String email, String nombre, String passwd, String fecha_nacimiento, int rol) {
        String Sentencia = "INSERT INTO " + Constantes.TablaUsuarios + " VALUES ('" + email + "'," + "'" + nombre + "'," + "'" + passwd + "'," + "'" + fecha_nacimiento + "'," + "'" + rol + "')";
        int cod = 0;
        try {
            Sentencia_SQL.executeUpdate(Sentencia);
        } catch (SQLException sq) {
            cod = sq.getErrorCode();
        }
        return cod;
    }

    //----------------------------------------------------------
    public int borrarDato(String tabla, String where) {
        int cod = 0;
        String Sentencia = "DELETE FROM " + tabla + " WHERE " + where;
        try {
            Sentencia_SQL.executeUpdate(Sentencia);
        } catch (SQLException ex) {
            cod = ex.getErrorCode();
        }
        return cod;
    }

    //------------------------------------------------------
    public String obtenerPrimero(String Campo) {
        String valor = "";
        try {
            Conj_Registros.first();
            valor = Conj_Registros.getString(Campo);

        } catch (SQLException ex) {
        }
        return valor;
    }

    //------------------------------------------------------
    public String obtenerUltimo(String Campo) {
        String valor = "";
        try {
            Conj_Registros.last();
            valor = Conj_Registros.getString(Campo);
        } catch (SQLException ex) {
        }
        return valor;
    }

    //------------------------------------------------------
    public String obtenerActual(String Campo) {
        String valor = "";
        try {
            valor = Conj_Registros.getString(Campo);
        } catch (SQLException ex) {            
        }
        return valor;
    }

    //------------------------------------------------------
    public boolean irSiguiente() {
        boolean conseguido = false;
        try {
            conseguido = Conj_Registros.next();            
        } catch (SQLException ex) {
        }
        return conseguido;
    }

    //------------------------------------------------------
    public boolean irAnterior() {
        boolean conseguido = false;
        try {
            conseguido = Conj_Registros.previous();
        } catch (SQLException ex) {
        }
        return conseguido;
    }

    //------------------------------------------------------
    public int vaciarTabla(String tabla) {
        String sentencia = "TRUNCATE " + tabla;
        int cod = 0;
        try {
            Sentencia_SQL.executeUpdate(sentencia);
        } catch (SQLException ex) {
        }
        return cod;
    }

    //------------------------------------------------------
    public String obtenerPrimer(String Campo) {
        String valor = "";
        try {
            Conj_Registros.first();
            valor = Conj_Registros.getString(Campo);
        } catch (SQLException ex) {
        }
        return valor;
    }

    //----------------------------------------------------------
    public String obtenerValor(String tabla, String where, String campo) throws SQLException {
        String sentencia = "SELECT * from " + tabla + " WHERE " + where;
        Conj_Registros = Sentencia_SQL.executeQuery(sentencia);
        if (Conj_Registros.next()) {
            return Conj_Registros.getString(campo);
        } else {
            return null;
        }
    }

    //---------------------------------------------------------
    public void cerrarConexion() {
        try {
            // resultado.close();
            this.Conex.close();
            System.out.println("Desconectado de la Base de Datos"); // Opcional para seguridad
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de Desconexion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Usuario getUsuario(String where) throws SQLException {
        String sentencia = "SELECT * from " + Constantes.TablaUsuarios + " WHERE " + where;        
        ResultSet usuarios = Sentencia_SQL.executeQuery(sentencia);
        if (usuarios.next()) {
            Usuario u = new Usuario();
            u.setEmail(usuarios.getString(Constantes.usuariosEmail));
            u.setNombre(usuarios.getString(Constantes.usuariosNombre));
            u.setPassResumida(usuarios.getBytes(Constantes.usuariosPass));
            u.setFechaNac(usuarios.getString(Constantes.usuariosFecha_Nac));
            u.setRol(usuarios.getInt(Constantes.usuariosRol));
            return u;
        } else {
            return null;
        }
    }

    //---------------------------------------------------------------------------------------------------------------
    public int insertarPreferencia(Preferencia p) {
        String Sentencia = "INSERT INTO " + Constantes.TablaPreferencias + " VALUES ('" + p.getEmail() + "'," + p.isRelacionSeria() + "," + p.getDeportivos() + "," + p.getArtisticos() + "," + p.getPoliticos() + "," + p.isQuiereHijos() + ")";
        int cod = 0;
        try {
            Sentencia_SQL.executeUpdate(Sentencia);
        } catch (SQLException sq) {
            cod = sq.getErrorCode();
        }
        return cod;
    }

    public boolean existePreferencia(String where) throws SQLException {
        String sentencia = "SELECT * from " + Constantes.TablaPreferencias + " WHERE " + where;
        Conj_Registros = Sentencia_SQL.executeQuery(sentencia);
        if (Conj_Registros.next()) {
            return true;
        } else {
            return false;
        }
    }

    public void modPreferencia(Preferencia p) {
        borrarDato(Constantes.TablaPreferencias, (Constantes.preferenciasEmail + " = '" + p.getEmail() + "'"));
        insertarPreferencia(p);
    }

    public Preferencia getPreferencia(String where) throws SQLException {
        String sentencia = "SELECT * from " + Constantes.TablaPreferencias + " WHERE " + where;        
        ResultSet preferencias = Sentencia_SQL.executeQuery(sentencia);
        if (preferencias.next()) {
            Preferencia p = new Preferencia();
            p.setEmail(preferencias.getString(Constantes.preferenciasEmail));
            p.setRelacionSeria(preferencias.getBoolean(Constantes.preferenciasRelacion));
            p.setDeportivos(preferencias.getInt(Constantes.preferenciasDeportivos));
            p.setArtisticos(preferencias.getInt(Constantes.preferenciasArtisticos));
            p.setPoliticos(preferencias.getInt(Constantes.preferenciasPoliticos));
            p.setQuiereHijos(preferencias.getBoolean(Constantes.preferenciasHijos));
            return p;
        } else {
            return null;
        }

    }

    public ArrayList<Usuario> listaUsuarios(Usuario conectado) throws SQLException {
        ArrayList<Usuario> ListaUsuarios=new ArrayList<>();
        String sentencia = "SELECT * from " + Constantes.TablaUsuarios + " WHERE "+Constantes.usuariosEmail+" != '"+conectado.getEmail()+"'";        
        ResultSet usuarios = Sentencia_SQL.executeQuery(sentencia);
        while (usuarios.next()) {
            Usuario u = new Usuario();
            u.setEmail(usuarios.getString(Constantes.usuariosEmail));
            u.setNombre(usuarios.getString(Constantes.usuariosNombre));
            u.setPassResumida(usuarios.getBytes(Constantes.usuariosPass));
            u.setFechaNac(usuarios.getString(Constantes.usuariosFecha_Nac));
            u.setRol(usuarios.getInt(Constantes.usuariosRol));
            ListaUsuarios.add(u);
        }
        return ListaUsuarios;
    }

    public int insertaSolicitudAmistad(SolicitudAmistad solicitud_amistad) {        
        String Sentencia = "INSERT INTO " + Constantes.TablaAmigos + " VALUES ('" + solicitud_amistad.getEmailA()+ "','" + solicitud_amistad.getEmailB() + "'," + solicitud_amistad.isMatch() + ")";        
        int cod = 0;
        try {
            Sentencia_SQL.executeUpdate(Sentencia);
        } catch (SQLException sq) {
            cod = sq.getErrorCode();
        }
        return cod;
    }

    public ArrayList<Usuario> getListaNoSolicitados(Usuario conectado) throws SQLException {
        ArrayList<Usuario> ListaUsuarios=new ArrayList<>();
        String sentencia = "SELECT * FROM "+Constantes.TablaUsuarios+" WHERE "+Constantes.usuariosEmail+" NOT IN (SELECT "+Constantes.usuariosEmail+" from " + Constantes.TablaUsuarios + " JOIN "+Constantes.TablaAmigos+" ON "+Constantes.usuariosEmail+" = "+Constantes.amigosEmailB+" WHERE "+Constantes.amigosEmailA+" = '"+conectado.getEmail()+"') AND "+Constantes.usuariosEmail+" != '"+conectado.getEmail()+"'";
        ResultSet solicitados = Sentencia_SQL.executeQuery(sentencia);
        while (solicitados.next()) {
            Usuario u = new Usuario();
            u.setEmail(solicitados.getString(Constantes.usuariosEmail));
            u.setNombre(solicitados.getString(Constantes.usuariosNombre));
            u.setPassResumida(solicitados.getBytes(Constantes.usuariosPass));
            u.setFechaNac(solicitados.getString(Constantes.usuariosFecha_Nac));
            u.setRol(solicitados.getInt(Constantes.usuariosRol));
            ListaUsuarios.add(u);
        }        
        return ListaUsuarios;
    }

    public SolicitudAmistad getSolicitud(String emailA, String emailB) throws SQLException {
        String sentencia = "SELECT * from " + Constantes.TablaAmigos + " WHERE " + Constantes.amigosEmailA+" = '"+emailB+"' AND "+ Constantes.amigosEmailB+" = '"+emailA+"'";        
        ResultSet solicitudes = Sentencia_SQL.executeQuery(sentencia);
        if (solicitudes.next()) {
            SolicitudAmistad sa = new SolicitudAmistad();
            sa.setEmailA(solicitudes.getString(Constantes.amigosEmailA));
            sa.setEmailB(solicitudes.getString(Constantes.amigosEmailB));
            sa.setMatch(solicitudes.getBoolean(Constantes.amigosMatch));
            return sa;
        } else {
            return null;
        }
    }  

    public int creaMatch(String emailA, String emailB) {
        int cod = 0;
        String Sentencia = "UPDATE " + Constantes.TablaAmigos + " SET " + Constantes.TablaAmigos+"."+Constantes.amigosMatch + " = 1 WHERE " + Constantes.amigosEmailA+" = '"+emailA+"' AND "+ Constantes.amigosEmailB+" = '"+emailB+"'";        
        try {
            Sentencia_SQL.executeUpdate(Sentencia);
        } catch (SQLException ex) {
            cod = ex.getErrorCode();
        }
        return cod;
    }

    public ArrayList<Usuario> getListaAmigos(Usuario conectado) throws SQLException {
        ArrayList<Usuario> ListaUsuarios=new ArrayList<>();
        String sentencia = "SELECT * FROM "+Constantes.TablaUsuarios+" WHERE "+Constantes.usuariosEmail+" IN (SELECT "+Constantes.amigosEmailB+" from " + Constantes.TablaUsuarios + " JOIN "+Constantes.TablaAmigos+" ON "+Constantes.usuariosEmail+" = "+Constantes.amigosEmailA+" WHERE "+Constantes.TablaAmigos+"."+Constantes.amigosMatch+" = 1 AND "+Constantes.amigosEmailA+" = '"+conectado.getEmail()+"') AND "+Constantes.usuariosEmail+" != '"+conectado.getEmail()+"'";        
        ResultSet solicitados = Sentencia_SQL.executeQuery(sentencia);
        while (solicitados.next()) {
            Usuario u = new Usuario();
            u.setEmail(solicitados.getString(Constantes.usuariosEmail));
            u.setNombre(solicitados.getString(Constantes.usuariosNombre));
            u.setPassResumida(solicitados.getBytes(Constantes.usuariosPass));
            u.setFechaNac(solicitados.getString(Constantes.usuariosFecha_Nac));
            u.setRol(solicitados.getInt(Constantes.usuariosRol));
            ListaUsuarios.add(u);
        }        
        return ListaUsuarios;
    }   
    
    public int insertaMensaje(Mensaje msg) {
        String Sentencia = "INSERT INTO " + Constantes.TablaMensajes + " VALUES ('" + msg.getTransmisor()+ "','" + msg.getReceptor() + "','" + msg.getContenido() + "',0)";        
        int cod = 0;
        try {
            Sentencia_SQL.executeUpdate(Sentencia);
        } catch (SQLException sq) {
            cod = sq.getErrorCode();
        }
        return cod;
    }

    public ArrayList<Mensaje> getListaMensajes(Usuario conectado) throws SQLException {
        ArrayList<Mensaje> ListaMensajes=new ArrayList<>();
        String sentencia = "SELECT * FROM "+Constantes.TablaMensajes+" WHERE "+Constantes.mensajesReceptor+" = '"+conectado.getEmail()+"' AND "+Constantes.mensajesLeido+" = 0";        
        ResultSet solicitados = Sentencia_SQL.executeQuery(sentencia);
        while (solicitados.next()) {
            Mensaje msg = new Mensaje();
            msg.setTransmisor(solicitados.getString(Constantes.mensajesTransmisor));
            msg.setReceptor(solicitados.getString(Constantes.mensajesReceptor));
            msg.setContenido(solicitados.getString(Constantes.mensajesContenido));            
            ListaMensajes.add(msg);
        }        
        return ListaMensajes;
    }

    public int leerMensaje(Mensaje msg) {
        int cod = 0;
        String Sentencia = "UPDATE " + Constantes.TablaMensajes + " SET " + Constantes.mensajesLeido+" = 1 WHERE " + Constantes.mensajesTransmisor+" = '"+msg.getTransmisor()+"' AND "+ Constantes.mensajesReceptor+" = '"+msg.getReceptor()+"' AND "+Constantes.mensajesContenido+" = '"+msg.getContenido()+"'";
        System.out.println(Sentencia);
        try {
            Sentencia_SQL.executeUpdate(Sentencia);
        } catch (SQLException ex) {
            cod = ex.getErrorCode();
        }
        return cod;
    }

}
