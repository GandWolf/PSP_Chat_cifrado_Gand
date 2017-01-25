/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat_cifrado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author gand
 */
public class HiloServidor implements Runnable {
    private Socket micon;
    private int id_cli;
    private BufferedReader IN;
    private PrintWriter OUT;
    private Clientes cli;

    private Cifrado descrip = new Cifrado("clave15");

    public HiloServidor(Socket con, int n) {
        micon=con;
        id_cli=n;
        mensaje();
    }

    //---------------------------------------------------------------------------------------------------------------
    @Override
    public void run(){

            String cad="";
            try(

                    BufferedReader IN = new BufferedReader(new InputStreamReader(micon.getInputStream()));
                    PrintWriter OUT = new PrintWriter(micon.getOutputStream(), true);
            )
            {
                    OUT.println(">Conectado al Servidor, id_cli=" + id_cli + ", \"quit\" o \"exit\" para salir");
                    while(true){
                        cad=IN.readLine();
                            System.out.println("[cliente (" + id_cli + ")]> Mensaje Encriptado: " + cad);
                            System.out.println("[cliente (" + id_cli + ")]> Desencriptando datos... ...  ");
                        String cadDescrip = new String(descrip.descifrado(cad));
                        System.out.println("[cliente (" + id_cli + ")]>" + cadDescrip);
                        if(cad.trim().equals("quit") || cad.trim().equals("exit") || cad==null) break;
                    }
            }catch(IOException ex){
                    System.err.println(">Error al inicializar Usuarios id=" + id_cli + ", mensaje: " + ex.getMessage());
            }catch(NullPointerException ex){
                    System.err.println("Se cerro la CONEXION del cliente: " + id_cli + ", inesperadamente");
            }finally{
                    System.out.println("Se cerro la CONEXION del cliente: " + id_cli );
            }

    }

    //---------------------------------------------------------------------------------------------------------------------------
    public void mensaje(){
            System.out.println("Usuarios id_cli= " + id_cli + ", Direccion IP= " + micon.getLocalAddress() + ":" + micon.getPort() + ", CONECTADO.");
    }
    public void generaCliente(){
        String nombre = "Cliente[" + id_cli +"]";
        cli = new Clientes(nombre, id_cli, IN, OUT);
        Servidor.listaUsuarios.add(cli);
    }

    public void publicarMensaje(String cad){
        Iterator<Clientes> losUsuarios = Servidor.listaUsuarios.iterator();
        while(losUsuarios.hasNext()){
            Clientes c = losUsuarios.next();
            if(c.getId()!=id_cli)
                c.getSalida().println("["+ cli.getNombre() + "]> " + cad);
        }
    }
}//Fin HiloServidor