package User;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Gand on 28/01/17.
 */
public class HiloEntradaRaquel implements Runnable{
    private BufferedReader entrada;
    private CifradoCli cifrador;

    public HiloEntradaRaquel(BufferedReader entrada, CifradoCli cifrador) {
        this.entrada = entrada;
        this.cifrador = cifrador;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (Cliente.passw == null){
                    Cliente.passw = entrada.readLine().trim();
                }else {
                    cifrador.setFrase(entrada.readLine().trim());
                    String mensaje = cifrador.Decodifica(Cliente.passw);
                    System.out.println(mensaje);
                }


            } catch (IOException e) {
                System.err.println("Has perdido la conexión con el servidor");
                System.exit(0);
            }
        }
    }
}

