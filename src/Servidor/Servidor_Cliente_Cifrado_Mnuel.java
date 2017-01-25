/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author usuario
 */
public class Servidor_Cliente_Cifrado_Mnuel {

    InetAddress ip;
    static int port=15000;
    static int max=5;
    public static void main(String[] args)
    {
        System.out.println("Servidor 0.9\n INICIANDO...");
        try
            (ServerSocket servidor=new ServerSocket(port,max))
        {
            Socket socket = servidor.accept();
            
            HiloServidorEntrada hiloServidorEntrada = new HiloServidorEntrada(socket);
            Thread threadEntrada=new Thread(hiloServidorEntrada);
            threadEntrada.start();
            
            
            HiloServidorSalida hiloServidorSalida = new HiloServidorSalida(socket);
            Thread threadSalida = new Thread(hiloServidorSalida);
            threadSalida.start();
        }
        
       catch (IOException ex)
       {
            System.err.println("Error: " + ex.getMessage());
       }
    
}
}