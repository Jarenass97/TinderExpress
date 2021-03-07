package bd;

import auxiliar.Constantes;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
    private void mostrarFilaActual() {
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
        String Sentencia = "UPDATE " + tabla + " SET " + campo + " = '" + Nuevo_Nombre + "' WHERE "+where;
        try {
            Sentencia_SQL.executeUpdate(Sentencia);
        } catch (SQLException ex) {
            cod = ex.getErrorCode();
        }
        return cod;
    }

    //----------------------------------------------------------
    public int insertarUsuario(String email, String nombre, String passwd, String fecha_nacimiento, int rol) {
        String Sentencia = "INSERT INTO "+Constantes.TablaUsuarios+" VALUES ('" + email + "'," + "'" + nombre + "'," + "'" + passwd + "'," + "'" + fecha_nacimiento + "'," + "'" + rol + "')";        
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
    public String obtenerValor(String tabla, String where,String campo) throws SQLException {
        String sentencia = "SELECT * from " + tabla + " WHERE "+where;
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

}
