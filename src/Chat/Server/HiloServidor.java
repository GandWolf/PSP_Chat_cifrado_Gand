
package Chat.Server;

/**
 *
 * @author gand
 */

import java.util.Iterator;
import java.util.Scanner;


public class HiloServidor implements Runnable {

    private Cifrado cf = new Cifrado();

    @Override
    public void run() {
        Scanner entrada=new Scanner(System.in);
        
        while (true){  
            String cad=entrada.nextLine();
            if (cad.equalsIgnoreCase("exit")){
                System.out.println("Cerrando Servidor...");
                System.exit(0); 
            }else{
                for (Usuario u : Servidor.listaUsuarios){
                    u.getSalida().println(cf.Codifica(u.getPassw()));
                }
            }
        }
    }//Fin run
}
