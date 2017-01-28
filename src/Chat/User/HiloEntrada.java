
package Chat.User;

/**
 *
 * @author gand
 */

import Chat.Server.Cifrado;
import Chat.Server.Servidor;
import Chat.Server.Usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;


public class HiloEntrada implements Runnable{
   private Socket sc;
   private BufferedReader IN;

    CifradoCli cf;

   public HiloEntrada(Socket con, CifradoCli cifrado) {
       sc = con;
       cf = cifrado;
   }
  //---------------------------------------------------------------------------------------------------------------
   @Override
   public void run(){
        try(
           BufferedReader in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
        )

        {
            IN=in;
            String cadena = "";
            while(true) {
                cadena = IN.readLine();

                if (Cliente.passw == null) {
                    Cliente.passw = cadena;
                } else {

                    cf.setFrase(cadena);
                    String cad = cf.Decodifica(Cliente.passw);
                    if (cad.trim().equals("exit") || cad.trim().equals("quit") || cad == null) {
                        System.out.println("Cerrando cliente...");
                        break;
                    } else {
                        System.out.println("cad");
                    }
                }
            }
        }catch(IOException ex){
           System.err.println(ex.getMessage());
        }catch(NullPointerException ex){
           System.err.println(ex.getMessage());
        }

   }//Fin run
}//Fin class