
package Chat;

/**
 *
 * @author gand
 */

import java.net.*;
import java.io.*;
import java.util.*;


public class Servidor {
    static int PTO=15000;
       
    static ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    static int nUsu=1;
    
    public static void main(String [] args){
       
       System.out.println("+-------------------------------------------------------------------------+");
       System.out.println("|                         +---------------------+                         |");
       System.out.println("| <<-<-<-<+>->-> #### <<<<| Calculadora Abierta |>>>> #### <-<-<+>->->->> |");
       System.out.println("|                         +---------------------+                         |");
       System.out.println("+-------------------------------------------------------------------------+");
        try(
                     ServerSocket serv = new ServerSocket(PTO);
        )
        {
            HiloExit ext=new HiloExit();
            Thread ser=new Thread(ext);
            ser.start();
           while(true){
              HiloServidor mh = new HiloServidor(serv.accept(), nUsu);
              Thread hilo = new Thread(mh);
              hilo.setName("Conexion Usuario[" + nUsu + "]");
              hilo.start();
              nUsu++;
           }

        }catch(IOException ex){
           System.err.println("Error al INICIALIZAR servidor, mensaje de error: " + ex.getMessage());
           
        }

    }
    //-----------------------------------------------------------------------------------------

    
}//Fin Servidor