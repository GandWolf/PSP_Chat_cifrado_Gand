/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


/**
 *
 * @author usuario
 */
public class HiloSalida implements Runnable {

    static String ipDestino="127.0.0.1";
    static int puerto=15000;
    private final Socket socket;
   
    public HiloSalida(Socket socket)
    {
        this.socket=socket;
    }

    
    @Override
    public void run() 
    {
        Scanner teclado=new Scanner(System.in);
        try
            (
                
                PrintWriter salida = new PrintWriter(socket.getOutputStream(),true);
                )
        {
            salida.println(Cifrado2.cifrar("CLIENTE: Conexion de salida establecida"));
            System.out.println("Cliente: Servidor a la escucha...");
            while(true)
            {
                String textoEnviar = teclado.nextLine();
                salida.println(Cifrado2.cifrar("Cliente: " + textoEnviar));
            }
        }
        
       catch (IOException ex) 
       {   
           System.err.println("Error: " + ex.getMessage());
       } 
    }
}
