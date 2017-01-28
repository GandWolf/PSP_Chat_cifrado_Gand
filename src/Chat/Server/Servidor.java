
package Chat.Server;

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
       
       System.out.println("+----------------------------+");
       System.out.println("|<<<<| SERVIDOR CIFRADO |>>>>|");
       System.out.println("+----------------------------+");
        try(
                     ServerSocket serv = new ServerSocket(PTO);
        )
        {
            HiloServidor ext=new HiloServidor();
            Thread ser=new Thread(ext);
            ser.start();
           while(true){
              HiloUsuarios mh = new HiloUsuarios(serv.accept(), nUsu);
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