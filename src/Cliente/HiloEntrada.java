/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


/**
 *
 * @author usuario
 */
public class HiloEntrada implements Runnable {

    static String ipDestino="127.0.0.1";
    static int puerto=15000;
    private final Socket socket;
   
    public HiloEntrada(Socket socket)
    {
        this.socket=socket;
    }

    
    @Override
    public void run() 
    {
        try
            (
               
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                )
        {

            while(true)
            {
                String textoRecibidoCifrado = entrada.readLine();
                String textoRecibidoDescifrado = Cifrado2.descifrar(textoRecibidoCifrado);
                System.out.println("<<<<<<<<<<<MENSAJE SERVIDOR CIFRADO>>>>>>>>>>\n" + textoRecibidoCifrado);
                System.out.println("\n>>>>>>>>>>>MENSAJE SERVIDOR DESCIFRADO<<<<<<<<<<\n" + textoRecibidoDescifrado);
            }
        }
        
       catch (IOException ex) 
       {   
           System.err.println("Error: " + ex.getMessage());
       } 
    }
}
