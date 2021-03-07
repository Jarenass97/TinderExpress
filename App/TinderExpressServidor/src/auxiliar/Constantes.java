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
    
    //--------------- TABLA ROLES ----------------------
    public static final String TablaRoles = "roles";
    public static final String rolesID = "id";
    public static final String rolesRol = "rol";
    
    
}
