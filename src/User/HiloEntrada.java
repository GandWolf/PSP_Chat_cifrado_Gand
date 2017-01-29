
package User;

/**
 *
 * @author gand
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


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
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(sc.getInputStream()));

//            IN=in;
            String cadena = "";
            while(true) {
                cadena = in.readLine().trim();

                if (Cliente.passw == null) {
                    Cliente.passw = cadena;
                } else {

                    cf.setFrase(cadena);
                    String cad = cf.Decodifica(Cliente.passw);
                    if (cad.trim().equals("exit") || cad.trim().equals("quit")) {
                        System.out.println("Cerrando cliente...");
                        break;
                    } else {
                        System.out.println(cad);
                    }
                }
            }

        }catch(NullPointerException ex){
           System.err.println("Pero porrrrrrrrrrr queeeeeeee"+ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

   }//Fin run


}//Fin class