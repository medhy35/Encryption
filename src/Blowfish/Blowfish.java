package Blowfish;

import java.math.BigInteger;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * Blowfish utilise une taille de bloc de 64 bits et la clé de longueur variable
 * peut aller de 32 à 448 bits. Elle est basée sur l'idée qu'une bonne sécurité
 * contre les attaques de cryptanalyse peut être obtenue en utilisant de très
 * grandes clés pseudo-aléatoires.
 */
public class Blowfish {
  public final static int SizeKey = 128; // [32..448]
  private Key passCode;

  public Blowfish(){}
  public Key getPassCode() {return passCode;}
  public byte[] getPassCodeInBytes(){ return passCode.getEncoded();}

  public void setPassCode(Key passCode) { this.passCode=passCode;}

  public void generateKey(){
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("Blowfish");
      keyGen.init(SizeKey);
      passCode = keyGen.generateKey();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e);

    }
  }
  public byte[] crypt(byte[] plaintext){
    try{
      Cipher ciph=Cipher.getInstance("Blowfish");
      ciph.init(Cipher.ENCRYPT_MODE,passCode);
      return ciph.doFinal(plaintext);
    }catch(Exception e){System.out.println(e);}
    return null;
  }

  public byte[] crypt(String plaintext) {
    return crypt(plaintext.getBytes());
  }

  public byte[] decryptInBytes(byte[] ciphertext) {
    try{
      Cipher cipher = Cipher.getInstance("Blowfish");
      cipher.init(Cipher.DECRYPT_MODE, passCode);
      return cipher.doFinal(ciphertext);
    } catch(Exception e){System.out.println(e);}
    return null;
  }

  public String decryptInString(byte[] ciphertext) {
    return new String(decryptInBytes(ciphertext));
  }

  private void setPassCode(byte[] keyData) {
    passCode = new SecretKeySpec(keyData, "Blowfish");
  }

  public static void main(String[] args) {
    String plaintext = "Lorem Ipsum";
    System.out.println("Text = "+plaintext);
    Blowfish off = new Blowfish();

    off.generateKey();
    byte[] secretKey = off.getPassCodeInBytes();
    byte[] ciphertext = off.crypt(plaintext);
    System.out.println("ciphertext = " + new BigInteger(ciphertext));

    off.setPassCode(secretKey);
  //  String plaintext2 = off.decryptInString(ciphertext);
   // System.out.println("plaintext2 = " + plaintext2);
   // if (!plaintext2.equals(plaintext))
     // System.out.println("Error: plaintext2 != plaintext");
  }

}


