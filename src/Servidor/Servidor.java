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
import java.util.Random;

/**
 *
 * @author usuario
 */
public class Servidor {

    InetAddress ip;
    static int port=15000;
    static int max=5;
    public static void main(String[] args)   {
        System.out.println("Servidor 0.9\n INICIANDO...");
        try
            (ServerSocket servidor=new ServerSocket(port,max))
        {

            while(true){
                Socket socket = servidor.accept();
            
                HiloServidorEntrada hiloServidorEntrada = new HiloServidorEntrada(socket);
                Thread threadEntrada=new Thread(hiloServidorEntrada);
                threadEntrada.start();


                HiloServidorSalida hiloServidorSalida = new HiloServidorSalida(socket);
                Thread threadSalida = new Thread(hiloServidorSalida);
                threadSalida.start();
            }
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
       }
    
    }

    void generarClave(){
        String mayus = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String minus = "abcdefghijklmnopqrstuvwxyz";
        String num = "1234567890";
        String espec = "ñÑ!@·$%&/()=?¿<>";

        char [] contrasena = new char[10];

        while (true){
            boolean may = false;
            boolean min = false;
            boolean nume = false;
            boolean esp = false;
            for (int i = 0; i < contrasena.length; i++){
                switch ((int) (Math.random()*4)){
                    case 1: contrasena[i] = mayus.charAt((int) (Math.random()*mayus.length()));
                        may= true;
                        break;
                    case 2: contrasena[i] = minus.charAt((int) (Math.random()*minus.length()));
                        min=true;
                        break;
                    case 3: contrasena[i] = num.charAt((int) (Math.random()*num.length()));
                        nume=true;
                        break;
                    case 4: contrasena[i] = espec.charAt((int) (Math.random()*espec.length()));
                        esp=true;
                        break;
                }
            }
            if(may && min && nume && esp)
                break;
        }
        String passw = new String(contrasena);
    }
}