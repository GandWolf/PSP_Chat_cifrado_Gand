/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat_cifrado;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
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
 * @author gand
 */
public class Cifrado {
    private String clave;
    private SecretKey clave_priv;
    Cipher cifrar;
    
    Cifrado (String clave){
        this.clave = clave;
        iniciarSK();
    }
    
    
    String cifrado(String cad){
        String frase = cad;
        byte [] bfrase=null; //frase a cifrar en bytes 
        byte [] befrase=null; //frase cifrada en bytes
       
        try {
            bfrase=frase.getBytes("UTF8");
            
            cifrar.init(Cipher.ENCRYPT_MODE, clave_priv);
           
            befrase=cifrar.doFinal(bfrase);
           
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
        Base64.Encoder encoder = Base64.getEncoder();
        String b = encoder.encodeToString(befrase);  
        
        return b;
   } 
    
    String descifrado(String cad){
       Base64.Decoder decoder = Base64.getDecoder();
       byte[] decodedByteArray = decoder.decode(cad);
       byte [] bdfrase=null;
               
       try {

           cifrar.init(Cipher.DECRYPT_MODE, clave_priv);
           bdfrase=cifrar.doFinal(decodedByteArray);
           
           

        } catch (InvalidKeyException ex) { 
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return new String(bdfrase);
   } 

    private void iniciarSK() {
        byte [] bclaveprov=null; //clave en bytes provi
       byte [] bclave; //clave en bytes de 24 la buena.
       KeySpec ks; 
       SecretKeyFactory skf;
       
       try {
           bclaveprov = clave.getBytes("UTF8");
           bclave=copyOf(bclaveprov, 24);
           ks= new DESedeKeySpec(bclave);
           skf = SecretKeyFactory.getInstance("DESede");
           clave_priv=skf.generateSecret(ks);
           cifrar = Cipher.getInstance("DESede");
           
       } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}