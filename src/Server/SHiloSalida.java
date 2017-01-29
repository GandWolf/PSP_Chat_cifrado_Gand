
package Server;

/**
 *
 * @author gand
 */

import java.util.Scanner;


public class SHiloSalida implements Runnable {

    private Cifrado cf = new Cifrado();

    @Override
    public void run() {
        while (true){
            Scanner entrada = new Scanner(System.in);
            String cad = entrada.nextLine();
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
