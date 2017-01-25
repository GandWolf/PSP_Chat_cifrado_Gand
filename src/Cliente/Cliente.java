/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;


import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author usuario
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    static String ipDestino="127.0.0.1";
    static int puerto = 15000;
    static Socket socket;
    public static void main(String[] args)
    {
        
        System.out.println("Cliente v0.9");
        System.out.println("\tINICIANDO");
        try
        {
            Socket socket=new Socket(ipDestino,puerto);
            HiloSalida hiloSalida = new HiloSalida(socket);
            Thread threadSalida = new Thread(hiloSalida);
            threadSalida.start();
            
            
            HiloEntrada hiloEntrada = new HiloEntrada(socket);
            Thread threadEntrada = new Thread(hiloEntrada);
            threadEntrada.start();
        }
        
       catch (IOException ex) 
       {   
           System.err.println("Error: " + ex.getMessage());
       } 
        
        
            

        
    }
    
}
