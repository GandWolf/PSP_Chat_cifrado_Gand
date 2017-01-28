
package Chat.Server;

/**
 *
 * @author gand
 */

import java.net.*;
import java.io.*;
import java.util.Iterator;


public class HiloUsuarios implements Runnable{
   private Socket micon;
   private int id_usu;
   private BufferedReader IN;
   private PrintWriter OUT;
   private Usuario usu;
   private boolean nullpointer=false;

    Cifrado cf = new Cifrado();

   public HiloUsuarios(Socket con, int n) {
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
            OUT.println(usu.getPassw());

            cf.setFrase("-> Conectado Correctamente Chat V1.0 ;-)");
            OUT.println(cf.Codifica(usu.getPassw()));

            cf.setFrase("-> Teclee \"exit\" o \"quit\" para salir.");
            OUT.println(cf.Codifica(usu.getPassw()));

            String cadena = "";
            while(true){
                cadena = IN.readLine();

                cf.setFrase(cadena);
                String cad = cf.Decodifica(usu.getPassw());
                if(cad.trim().equals("exit") || cad.trim().equals("quit") || cad==null) break;
                System.out.println("["+ usu.getNombre() + "]> " + cad);

                publicarMensaje(cad);
            }

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

    //-----------------------------------------------------------------------------------------------------------------
    public void publicarMensaje(String cad){
        String cadena = ("["+ usu.getNombre() + "]> " + cad);
        cf.setFrase(cadena);
        Iterator<Usuario> losUsuarios = Servidor.listaUsuarios.iterator();
        while(losUsuarios.hasNext()){
            Usuario u = losUsuarios.next();
            if(u.getId()!=id_usu){
                u.getSalida().println(cf.Codifica(u.getPassw()));
            }
        }
    }

    //-----------------------------------------------------------------------------------------------------------------
   public void generaUsuario(){
      String nombre = "Usuario[" + id_usu +"]";
      String passw = generarPassw();
      usu = new Usuario(nombre, id_usu, IN, OUT, passw);
      Servidor.listaUsuarios.add(usu);
   }
    //------------------------------------------------------------------------------------------------------------------

    private String generarPassw() {
        String mayus = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String minus = "abcdefghijklmnopqrstuvwxyz";
        String num = "1234567890";
        String espec = "ñÑ!@·$%&/()=?¿<>";

        char [] contrasena = new char[10];
        boolean pass = false;


        while (!pass){
            boolean may = false;
            boolean min = false;
            boolean nume = false;
            boolean esp = false;
            for (int i = 0; i < contrasena.length; i++){
                switch ((int) (Math.random()*5)){
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

            String passw = new String(contrasena);
            if(may && min && nume && esp){
                pass=true;
                break;
            }
        }
        String passw = new String(contrasena);
        return passw;
    }

}//Fin class