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
    public static final int CAMBIAR_PASSWORD=6;
    public static final int MODIFICAR_PERFIL=7;
    public static final int CARGAR_AFINES=8;
    public static final int SOLICITAR_AMISTAD=9;
    public static final int CARGAR_AMIGOS=10;
    public static final int ENVIAR_MENSAJE=11;
    public static final int CARGAR_MENSAJES=12;
    public static final int LEER_MENSAJE=13;
    public static final int CARGAR_USUARIOS=14;
    public static final int DAR_PERMISOS=15;
    public static final int QUITAR_PERMISOS=16;
    public static final int DEL_USER=17;
    
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
    
    //--------------- TABLA AMIGOS ----------------------
    public static final String TablaAmigos = "amigos"; 
    public static final String amigosEmailA = "email1"; 
    public static final String amigosEmailB = "email2"; 
    public static final String amigosMatch = "match"; 
    
     //--------------- TABLA MENSAJES ----------------------
    public static final String TablaMensajes = "mensajes"; 
    public static final String mensajesTransmisor = "email_transmisor"; 
    public static final String mensajesReceptor = "email_receptor"; 
    public static final String mensajesContenido = "contenido"; 
    public static final String mensajesLeido = "leido"; 
    
    //--------------- TABLA ROLES ----------------------
    public static final String TablaRoles = "roles";
    public static final String rolesID = "id";
    public static final String rolesRol = "rol";
    
    
}
