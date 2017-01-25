package Servidor;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import static java.util.Arrays.copyOf;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 *
 * @author usuario
 */
public class Cifrado2 {
   public static String cifrarBase64(byte [] a)
   {
        Base64.Encoder encoder = Base64.getEncoder();
        String b = encoder.encodeToString(a);        
        return b;
    }
 
    public static byte [] descifrarBase64(String a)
    {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(a);
        return decodedByteArray;        
        
    }
    
    
    
   public static String cifrar(String args){
              String clave ="clave15";
       String frase=args;
       byte [] arrayBitesClaveProvicional=null; //clave en bytes provi
       byte [] arrayBitesClaveBuena; //clave en bytes de 24 la buena.
       byte [] arrayBitesFraseOriginal=null; //frase a cifrar en bytes 
       byte [] arrayBitesFraseCifrada=null; //frase cifrada en bytes
       byte [] arrayBitesFraseDescifrada=null; //frase descifrada en bytes
       KeySpec ks; 
       SecretKeyFactory skf;
       Cipher cifrar;
       SecretKey clave_priv;
       
       try {
           arrayBitesClaveProvicional = clave.getBytes("UTF8");
           //Al agoritmo DESede espera un array de bytes de 24
           arrayBitesClaveBuena=copyOf(arrayBitesClaveProvicional, 24);
           ks= new DESedeKeySpec(arrayBitesClaveBuena);
           skf = SecretKeyFactory.getInstance("DESede");
           clave_priv=skf.generateSecret(ks);
           cifrar = Cipher.getInstance("DESede");
           //Ciframos-----------------------------------
           //Pasamos la frase a array de bytes
           arrayBitesFraseOriginal=frase.getBytes("UTF8");
           //POnemos cifrar en modo cifrando
           cifrar.init(Cipher.ENCRYPT_MODE, clave_priv);
           //Ciframos y guardamos lo cifrado en array de bytes, como NO...
           arrayBitesFraseCifrada=cifrar.doFinal(arrayBitesFraseOriginal);
           
           
           //Ahora desfiframos
           //1.- Ponemos cifrar en modo descifrar
            cifrar.init(Cipher.DECRYPT_MODE, clave_priv);
           //2.- Desciframos y miramos que es lo que tenemos
           arrayBitesFraseDescifrada = cifrar.doFinal(arrayBitesFraseCifrada);
           //Comprobamos todo, para verlo codificamos a base64
           
           
       } catch (UnsupportedEncodingException ex) {
           System.err.println("Codificación NO soportada");
       } catch (InvalidKeyException ex) {
           System.err.println("No puedo generar clave esperada");
       } catch (NoSuchAlgorithmException ex) {
           System.err.println("Error, Algoritmo DESede NO disponible");
       } catch (NoSuchPaddingException ex) {
           System.err.println("Error al generar Cipher");
       } catch (InvalidKeySpecException ex) {
           System.err.println("Error al generar la clave Privada");
       } catch (IllegalBlockSizeException ex) {
          System.err.println("Error: " + ex.getMessage());
       } catch (BadPaddingException ex) {
           System.err.println("Error: " + ex.getMessage());
       }
       //System.out.println("Frase Original: " + frase);
       //System.out.println("Frase cifrada utilizando TripleDES (DESede)");
       String fraseCifrada = cifrarBase64(arrayBitesFraseCifrada);
       //System.out.println(cifrarBase64(arrayBitesFraseCifrada));
       //System.out.println("Frase vuelta a descifrar: ");
       //Pasamos el array descifrado a base64 y lo decodificamos
       //String df = cifrarBase64(arrayBitesFraseDescifrada);
       //System.out.println(descifrarBase64(df));
      
       
       return fraseCifrada;
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    public static String descifrar(String args){
             String clave ="clave15";
       String frase=args;
       byte [] arrayBitesClaveProvicional=null; //clave en bytes provi
       byte [] arrayBitesClaveBuena; //clave en bytes de 24 la buena.
       byte [] arrayBitesFraseOriginal=null; //frase a cifrar en bytes 
       byte [] arrayBitesFraseCifrada=descifrarBase64(frase); //frase cifrada en bytes
       byte [] arrayBitesFraseDescifrada=null; //frase descifrada en bytes
       KeySpec ks; 
       SecretKeyFactory skf;
       Cipher cifrar;
       SecretKey clave_priv;
       
       try {
           arrayBitesClaveProvicional = clave.getBytes("UTF8");
           //Al agoritmo DESede espera un array de bytes de 24
           arrayBitesClaveBuena=copyOf(arrayBitesClaveProvicional, 24);
           ks= new DESedeKeySpec(arrayBitesClaveBuena);
           skf = SecretKeyFactory.getInstance("DESede");
           clave_priv=skf.generateSecret(ks);
           cifrar = Cipher.getInstance("DESede");
           //Ciframos-----------------------------------
           //Pasamos la frase a array de bytes
           
           arrayBitesFraseOriginal=frase.getBytes("UTF8");
           //POnemos cifrar en modo cifrando
           //cifrar.init(Cipher.ENCRYPT_MODE, clave_priv);
           //Ciframos y guardamos lo cifrado en array de bytes, como NO...
           //arrayBitesFraseCifrada=cifrar.doFinal(arrayBitesFraseOriginal);
           
           
           //Ahora desfiframos
           //1.- Ponemos cifrar en modo descifrar
            cifrar.init(Cipher.DECRYPT_MODE, clave_priv);
           //2.- Desciframos y miramos que es lo que tenemos
           arrayBitesFraseDescifrada = cifrar.doFinal(arrayBitesFraseCifrada);
           //Comprobamos todo, para verlo codificamos a base64
           
           
       } catch (UnsupportedEncodingException ex) {
           System.err.println("Codificación NO soportada");
       } catch (InvalidKeyException ex) {
           System.err.println("No puedo generar clave esperada");
       } catch (NoSuchAlgorithmException ex) {
           System.err.println("Error, Algoritmo DESede NO disponible");
       } catch (NoSuchPaddingException ex) {
           System.err.println("Error al generar Cipher");
       } catch (InvalidKeySpecException ex) {
           System.err.println("Error al generar la clave Privada");
       } catch (IllegalBlockSizeException ex) {
          System.err.println("Error: " + ex.getMessage());
       } catch (BadPaddingException ex) {
           System.err.println("Error: " + ex.getMessage());
       }
       //System.out.println("Frase Original: " + frase);
       //System.out.println("Frase cifrada utilizando TripleDES (DESede)");
      // System.out.println(cifrarBase64(arrayBitesFraseCifrada));
      // System.out.println("Frase vuelta a descifrar: ");
       //Pasamos el array descifrado a base64 y lo decodificamos
       String df = new String(arrayBitesFraseDescifrada);

       return df;
   }
}
