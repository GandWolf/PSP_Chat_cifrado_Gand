package Servidor;

/**
 * Created by gand on 25/01/17.
 */
public class vzxc {
    public static void main(String[] args) {
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
        System.out.println(passw);
    }
}
