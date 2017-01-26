
package Chat;

/**
 *
 * @author gand
 */

import java.net.*;
import java.io.*;


public class HiloServidor implements Runnable{
   private Socket micon;
   private int id_usu;
      private BufferedReader IN;
      private PrintWriter OUT;
      private Usuario usu;
      private boolean nullpointer=false;
      private boolean salir=false;
      public HiloServidor(Socket con,  int n) {
         micon=con;
         id_usu=n;
      }
      //---------------------------------------------------------------------------------------------------------------
      @Override
      public void run(){
         try(
               BufferedReader in = new BufferedReader(new InputStreamReader(micon.getInputStream()));
               PrintWriter out = new PrintWriter(micon.getOutputStream(), true);
            )
         
            {   
                IN=in;
                OUT=out;
                generaUsuario();
                System.out.println("Nueva CONEXION del Usuario[" + id_usu + "]");
                
                menu();
                
                
            }catch(IOException ex){
               System.err.println("|:|:|:|:|:+>Error al inicializar Usuario id=" + id_usu + ", mensaje: " + ex.getMessage());
            }catch(NullPointerException ex){
               System.err.println("Se cerro la CONEXION del Usuario: " + id_usu + ", inesperadamente");
               nullpointer=true;
            }finally{
               if (!nullpointer) System.out.println("Se cerro la CONEXION del Usuario: " + id_usu );
               Servidor.listaUsuarios.remove(usu);
            }
			
      }//Fin run

    private void menu() throws IOException {
        String cad="";
        OUT.println("\n\t\tMENÚ PRINCIPAL");
        OUT.println("\t\t--------------");
        OUT.println("\t1.- Sumar.");
        OUT.println("\t2.- Restar.");
        OUT.println("\t3.- Dividir.");
        OUT.println("\t4.- Resolver ecuación \"Ax²+Bx+C=0\".");
        OUT.println("\t5.- SALIR.");
        
        OUT.println("\nSu opción (1-5)?");
        
        while(!salir){
            cad = IN.readLine();
            int num=0;
            try{
                num = Integer.parseInt(cad.trim());
            }catch(NumberFormatException ex){
                OUT.println("Se esperaba un número, deja de jugar a las casitas!!!!");
            }
            
            switch(num){
                case 1: suma();
                        menu();
                        break;
                case 2: resta();
                        menu();
                        break;
                case 3: division();
                        menu();
                        break;
                case 4: ecuacion();
                        menu();
                        break;
                case 5: salir=true;
                        break;
                default: menu();
            }
        }
    }
   
   //-----------------------------------------------------------------------------------------------------------------------------
   public void generaUsuario(){
      String nombre = "Usuario[" + id_usu +"]";
      usu = new Usuario(nombre, id_usu, IN, OUT);
      Servidor.listaUsuarios.add(usu);
   }
   //-----------------------------------------------------------------------------------------------------------------------------

    private void suma() throws IOException {
        OUT.println("-> SUMA DE DOS NÚMEROS:");
        OUT.println("--->Introduce el primer número: ");
        double num1=introducirDouble();
        OUT.println("--->Introduce el segundo número: ");
        double num2=introducirDouble();
        System.out.println(""+usu.getNombre()+", Sumando: "+num1+" y "+ num2+", Mandando solución: "+(num1+num2));
        OUT.println("La SUMA de "+num1+" + "+num2+" = "+ (num1+num2));
    }

    private void resta() throws IOException {
        OUT.println("-> RESTA DE DOS NÚMEROS:");
        OUT.println("--->Introduce el primer número: ");
        double num1=introducirDouble();
        OUT.println("--->Introduce el segundo número: ");
        double num2=introducirDouble();
        System.out.println(""+usu.getNombre()+", Restando: "+num1+" y "+ num2+", Mandando solución: "+(num1-num2));
        OUT.println("La RESTA de "+num1+" - "+num2+" = "+ (num1-num2));
    }

    private void division() throws IOException {
        OUT.println("-> DIVISIÓN DE DOS NÚMEROS:");
        OUT.println("--->Introduce el dividendo: ");
        double num1=introducirDouble();
        OUT.println("--->Introduce el divisor (NO debe ser 0): ");
        double num2=introducirDoubleNoCero();
        System.out.println(""+usu.getNombre()+", Dividiendo: "+num1+" entre "+ num2+", Mandando solución: "+(num1/num2));
        OUT.println("La DIVISIÓN de "+num1+" / "+num2+" = "+ (num1/num2));        
    }

    private void ecuacion() throws IOException {
        OUT.println("-> ECUACIÓN DE SEGUNDO GRADO Ax²+Bx+C=0 :");
        OUT.println("--->Introduce el número A (No debe ser 0): ");
        double numA=introducirDoubleNoCero();
        OUT.println("--->Introduce el número B: ");
        double numB=introducirDouble();
        OUT.println("--->Introduce el número C: ");
        double numC=introducirDouble();
        
        double raiz=Math.sqrt((numB*numB)-4*numA*numC);
        
        if (Double.isNaN(raiz)){
            System.out.println(""+usu.getNombre()+", ECUACIÓN: "+numA+"x² + "+ numB+"x + "+numC+" = 0, "
                    + "NO tiene solución, RAIZ NEGATIVA");
            OUT.println("La ECUACIÓN: "+numA+"x²"+ numB+"x"+numC+"=0, "
                    + "NO tiene solución, RAIZ NEGATIVA");
        }else{
            double s1=(-1*numB)+(raiz/2*numA);
            double s2=(-1*numB)-(raiz/2*numA);
            System.out.println(""+usu.getNombre()+", ECUACIÓN: "+numA+"x² + "+ numB+"x + "+numC+" = 0, "
                    + "\nSolución 1 = "+s1+""
                    + "\nSolución 2 = "+s2);
            OUT.println("La ECUACIÓN: "+numA+"x² + "+ numB+"x + "+numC+" = 0, "
                    + "\nSolución 1 = "+s1+""
                    + "\nSolución 2 = "+s2);
        }
    }

    private double introducirDouble() throws IOException {
        double num=0;
        boolean ok=false;
        while (!ok){
            String cad = IN.readLine();
            
            try{
                num = Double.parseDouble(cad.trim());
                ok=true;
            }catch(NumberFormatException ex){
                OUT.println("Se esperaba un número, deja de jugar a las casitas!!!!");
            }
        }
        return num;
    }

    private double introducirDoubleNoCero() throws IOException {
        double num=0;
        boolean ok=false;
        while (!ok){
            try{
                while (num==0){
                    String cad = IN.readLine();
            
                    num = Double.parseDouble(cad.trim());
                    ok=true;
                    if (num==0){
                      OUT.println("El número no puede ser 0, deja de estrellar aviones!!!!");  
                    }
                }
            }catch(NumberFormatException ex){
                OUT.println("Se esperaba un número, deja de jugar a las casitas!!!!");
            }
        }
        return num;
    }
    
}//Fin class