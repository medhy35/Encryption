
package DES;

import java.io.UnsupportedEncodingException;
import java.security.*;
import javax.crypto.*;

/**
 * Le DES  utilise  des clés de 56 bits.
 * Son emploi n'est plus recommandé aujourd'hui, du fait de sa lenteur à
 * l'exécution et de son espace de clés trop petit permettant une attaque
 * systématique en un temps raisonnable. Quand il est encore utilisé c'est
 * généralement en Triple DES
 */

class DesEncryption {

  private static Cipher cipherProvider() throws NoSuchAlgorithmException, NoSuchPaddingException {
    Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    System.out.println("\n" + cipher.getProvider().getInfo());
    return cipher;
  }

  private static void decode(Key key, Cipher cipher, byte[] cipherText)
      throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    System.out.println("\nStart decryption");
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] newPlainText = cipher.doFinal(cipherText);
    System.out.println("Finish decryption: ");
    System.out.println(new String(newPlainText, "UTF8"));
  }

  private static byte[] encode(byte[] plainText, Key key, Cipher cipher)
      throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    System.out.println("\nStart encryption");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] cipherText = cipher.doFinal(plainText);
    System.out.println("Finish encryption: ");
    System.out.println(new String(cipherText, "UTF8"));
    return cipherText;
  }

  private static Key generatedKey() throws NoSuchAlgorithmException {
    System.out.println("\nStart generating DES key");
    KeyGenerator keyGen = KeyGenerator.getInstance("DES");
    keyGen.init(56);
    Key key = keyGen.generateKey();
    System.out.println("Finish generating DES key");
    return key;
  }

  public static void main(String[] args) throws Exception {

    String ss = "Lorem Ipsum...";

    byte[] plainText = ss.getBytes();

    // get a DES private key
    Key key = generatedKey();


    // get a DES cipher object and print the provider
    Cipher cipher = cipherProvider();

    // encrypt
    byte[] cipherText = encode(plainText, key, cipher);


    // decrypt
    decode(key, cipher, cipherText);

  }



}
