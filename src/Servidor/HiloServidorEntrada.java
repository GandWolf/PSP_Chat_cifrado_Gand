/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author usuario
 */
public class HiloServidorEntrada implements Runnable {

    Socket conexion;
    
    PrintWriter printWriter;
    public HiloServidorEntrada(Socket conexion)
    {
        this.conexion=conexion;
    }

    
    @Override
    public void run() 
    {
        try
            (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                
            )
        {
                        
            while(true)
            {
                
                String textoRecibido=entrada.readLine();
                System.out.println("<<<<<<<<<MENSAJE CLIENTE CIFRADO>>>>>>>>\n" + textoRecibido);
                System.out.println("\n>>>>>>>>>MENSAJE CLIENTE DESCIFRADO<<<<<<<<<\n" + Cifrado2.descifrar(textoRecibido));
                
                
            }
        } 
            catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
}
