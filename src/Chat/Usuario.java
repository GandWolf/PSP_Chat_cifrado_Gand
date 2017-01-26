
package Chat;

/**
 *
 * @author gand
 */

import java.io.*;


public class Usuario{
   private String nombre;
   private int id;
   private BufferedReader entrada;
   private PrintWriter salida;
   public Usuario(String n, int i, BufferedReader a, PrintWriter b){
      nombre=n;
      id=i;
      entrada=a;
      salida=b;
   }
   public Usuario(String n, int i){
      nombre=n;
      id=i;
   }
   //-----------------------------------------------------------------------------------
   public String getNombre()            {      return nombre;   }
   public int getId()                   {      return id;   }
   public BufferedReader getEntrada()   {      return entrada;   }
   public PrintWriter getSalida()       {      return salida;   }

   //-----------------------------------------------------------------------------------
   public void setEntrada(BufferedReader b) {      entrada=b;   }
   public void setSalida(PrintWriter p)     {      salida=p;   }
}//Fin Cliente