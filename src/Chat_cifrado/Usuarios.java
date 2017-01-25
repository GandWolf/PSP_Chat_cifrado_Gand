/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat_cifrado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import static java.util.Arrays.copyOf;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 *
 * @author gand
 */
public class Usuarios {
    static String cadena="", cadCod="";
    static InetAddress ipDestino=null;
    static int puerto=0;
    static Scanner teclado = new Scanner(System.in);

    static Cifrado encrip = new Cifrado("clave15");
    
    
    public static void main(String [] args){
        try{
            ipDestino = InetAddress.getByName("127.0.0.1");
            puerto=Integer.parseInt("15000");
        }catch(UnknownHostException ex) {
            System.err.println("Imposible resolver direccion Servidor, verífique sintaxis!!!");
            System.exit(-1);
        }catch(NumberFormatException ex) {
            System.err.println("Formato de puerto INVÁLIDO!!!, debe ser un numero 1-65000");
            System.exit(-1);
        }
          //--------------------------------------------------------
        try(
            Socket conexionCliente = new Socket(ipDestino, puerto);
            PrintWriter salida = new PrintWriter(conexionCliente.getOutputStream(), true);
//            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexionCliente.getInputStream()));
        ){
            while (true){
                cadena = teclado.nextLine().trim();
                cadCod = encrip.cifrado(cadena);
                salida.println(cadCod);
            }
        }catch(Exception ex) {
           
        }
    }
   
}//Fin Usuarios