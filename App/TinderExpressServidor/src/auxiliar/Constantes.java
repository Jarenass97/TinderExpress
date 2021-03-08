/*
 * Esta clase es muy recomendable, para centralizar aquí los datos comunes de la aplicación.
 */
package auxiliar;

/**
 *
 * @author fernando
 */
public class Constantes {
    //--------------- ACCIONES ----------------------
    public static final int REGISTRAR=0;
    public static final int LOGEAR=1;
    public static final int GET_USER=2;
    public static final int SAVEPREFERENCES=3;
    public static final int GET_PREFERENCE=4;
    public static final int COMPROBAR_PRIMERA=5;
    
    //--------------- BASE DE DATOS ----------------------
    public static final String bbdd = "tinderpsp";
    public static final String usuariobd = "root";
    public static final String passwdbd = "";
    
    //--------------- TABLA USUARIOS ----------------------
    public static final String TablaUsuarios = "usuarios"; 
    public static final String usuariosEmail = "email"; 
    public static final String usuariosNombre = "nombre"; 
    public static final String usuariosPass = "password"; 
    public static final String usuariosFecha_Nac = "fecha_nac"; 
    public static final String usuariosRol = "rol"; 
    
    //--------------- TABLA PREFERENCIAS ----------------------
    public static final String TablaPreferencias = "preferencias"; 
    public static final String preferenciasEmail = "email_u"; 
    public static final String preferenciasRelacion = "relacion_seria"; 
    public static final String preferenciasDeportivos = "deportivos"; 
    public static final String preferenciasArtisticos = "artisticos"; 
    public static final String preferenciasPoliticos = "politicos"; 
    public static final String preferenciasHijos = "quiere_hijos"; 
    
    //--------------- TABLA ROLES ----------------------
    public static final String TablaRoles = "roles";
    public static final String rolesID = "id";
    public static final String rolesRol = "rol";
    
    
}
