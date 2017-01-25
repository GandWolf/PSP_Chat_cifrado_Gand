/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat_cifrado;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 *
 * @author gand
 */
public class Servidor {
     static int PTO=15000, MAX=10;

     static ArrayList<Clientes> listaUsuarios = new ArrayList<Clientes>();

    public static void main(String [] args){
    	int ncli=1;
    	System.out.println("\n <<<< ... ... Servidor Encriptado Abierto ... ... >>>>\n");
        try(
			            ServerSocket serv = new ServerSocket(PTO, MAX);
        )
        {
        	while(true){
        		HiloServidor mh = new HiloServidor(serv.accept(), ncli);
        		Thread hilo = new Thread(mh);
        		hilo.setName("Conexion Usuarios: " + ncli);
        		hilo.start();
        		ncli++;
        	}

        }catch(IOException ex){
        	System.err.println("Error al INICIALIZAR servidor, mensaje de error: " + ex.getMessage());
        	
        }
    }
}
