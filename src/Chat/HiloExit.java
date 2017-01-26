
package Chat;

/**
 *
 * @author gand
 */

import java.util.Scanner;


class HiloExit implements Runnable {
    @Override
    public void run() {
        Scanner entrada=new Scanner(System.in);
        
        while (true){  
            String cad=entrada.nextLine();
            if (cad.equalsIgnoreCase("exit")){
                System.out.println("Cerrando Servidor, hay que repostar!");
                System.exit(0); 
            }
        }
    }//Fin run
}
