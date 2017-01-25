package Chat_cifrado;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Created by gand on 25/01/17.
 */
public class Clientes {
    private String nombre;
    private int id;
    private BufferedReader entrada;
    private PrintWriter salida;
    public Clientes(String n, int i, BufferedReader a, PrintWriter b){
        nombre=n;
        id=i;
        entrada=a;
        salida=b;
    }
    public Clientes(String n, int i){
        nombre=n;
        id=i;
    }
    //-----------------------------------------------------------------------------------
    public String getNombre(){
        return nombre;
    }
    public int getId(){
        return id;
    }
    public BufferedReader getEntrada(){
        return entrada;
    }
    public PrintWriter getSalida(){
        return salida;
    }

    //-----------------------------------------------------------------------------------
    public void setEntrada(BufferedReader b){
        entrada=b;
    }
    public void setSalida(PrintWriter p){
        salida=p;
    }

}
