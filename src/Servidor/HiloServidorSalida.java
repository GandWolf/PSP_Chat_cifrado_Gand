/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class HiloServidorSalida implements Runnable {

    Socket conexion;
    
    PrintWriter printWriter;
    public HiloServidorSalida(Socket conexion)
    {
        this.conexion=conexion;
    }

    
    @Override
    public void run() 
    {
        Scanner teclado = new Scanner(System.in);
        try
            (
                
                PrintWriter salida = new PrintWriter(conexion.getOutputStream(),true);
            )
        {
            
            salida.println(Cifrado2.cifrar("Servidor: Conectado con el servidor"));
            while(true)
            {
                String textoEnviar = teclado.nextLine();
                String textoEnviarCifrado = Cifrado2.cifrar("Servidor: " + textoEnviar);
                //System.out.println(Cifrado2.cifrar("Servidor: " + textoEnviar));
                salida.println(textoEnviarCifrado);
                
            }
        } 
            catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
}
