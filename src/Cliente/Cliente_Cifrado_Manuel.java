/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;


import static cliente_cifrado_manuel.HiloEntrada.ipDestino;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Cliente_Cifrado_Manuel {

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
