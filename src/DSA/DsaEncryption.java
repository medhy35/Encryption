package DSA;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

/**
 * DsaEncryption permet de signer digitalement un texte puis de vérifier sa
 * signature.
 */
public class DsaEncryption {

  final static int KEY_SIZE = 1024; // [512..1024]
  final static boolean CHEAT_TEXT = false;
  final static boolean CHEAT_SIGNATURE = false;
  final static String message = "Transfer $2000 to account S314542.0";

  public static KeyPair generatedDSAKey() {
    System.out.println("\nGenerating a pair of DSA keys...");
    KeyPairGenerator keyPairGen;
	try {
		keyPairGen = KeyPairGenerator.getInstance("DSA");
		 keyPairGen.initialize(KEY_SIZE, new SecureRandom());
		    KeyPair kp = keyPairGen.generateKeyPair();
		    return kp;
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   return null;
  }
  public static byte[] signature(KeyPair kp, byte[] text) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException{
    System.out.println("Signing the text...");
    Signature signature = Signature.getInstance("DSA");
    signature.initSign(kp.getPrivate());
    signature.update(text);
    byte[] sig = signature.sign();
    return sig;
  }
  public static void verifySig(KeyPair kp, byte[] sig, byte[] text) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
    System.out.println("\nVerifying the signature...");
    Signature signature = Signature.getInstance("DSA");
    signature.initVerify(kp.getPublic());
    signature.update(text);
    boolean ok = signature.verify(sig);
    System.out.println("Signature is " + (ok ? "OK" : "NOT OK") + " !\n");
  }



  public static void main(String args[]) {
    try {
      byte[] text = message.getBytes();
     KeyPair kp = generatedDSAKey();
     byte[] sig = signature(kp,text);

      if (CHEAT_TEXT)
        text[0]++;
      if (CHEAT_SIGNATURE)
        sig[4]++; // changing sig[0] produces an exception

      verifySig(kp,sig,text);
    }
    catch (Exception e) {
      System.out.println(e);
    }


}
}
