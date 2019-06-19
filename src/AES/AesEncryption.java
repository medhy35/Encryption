package AES;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AesEncryption
 */
public class AesEncryption {
  private Cipher encodeCipher;
  private Cipher decodeCipher;

public AesEncryption(SecretKey key){
  try {
    encodeCipher = Cipher.getInstance("AES");
    decodeCipher = Cipher.getInstance("AES");
    encodeCipher.init(Cipher.ENCRYPT_MODE, key);
    decodeCipher.init(Cipher.DECRYPT_MODE,key);
  } catch (Exception e) {

    e.printStackTrace();
  }

}
public String encrypt(String stream){
  try {
    byte[] utf8 = stream.getBytes("UTF-8");
    byte[] encode =encodeCipher.doFinal(utf8);
    return new BASE64Encoder().encode(encode);
  } catch (Exception e) {

  }
  return null;
}
public String decrypt(String stream){
  try{
    byte[] decode = new BASE64Decoder().decodeBuffer(stream);
    byte[] str = decodeCipher.doFinal(decode);
    return new String(str,"UTF-8");
  } catch(Exception e){
    e.printStackTrace();
  }
  return null;
}

  public static void main(String[] args) {
    String mykey = "1234567891234567";
    SecretKeySpec key = new SecretKeySpec(mykey.getBytes(), "AES");
    AesEncryption encrypter = new AesEncryption(key);
    String toencrypt = "Encrypt me 1&%¤£?#*µ-yassine-";
    System.out.println("Original String :" + toencrypt);
    long startTime = System.currentTimeMillis();
    System.out.println("Encrypted data :" + encrypter.encrypt(toencrypt));
    System.out.println("Encrypted in :" + (System.currentTimeMillis() - startTime) + " ms");
    long startTime2 = System.currentTimeMillis();
    String encrypted_data = "xWIjsB3jT7S3YJCDBSTY7NFD8h0Q9CZiJ6eA+rB/MjFUVd4aE9uYT2Z9B6DaKLB0";
    System.out.println("Decrypted data :" + encrypter.decrypt(encrypted_data));
    System.out.println("Decrypted in :" + (System.currentTimeMillis() - startTime2) + " ms");
  }

}
