package XOR;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class XorEncryption {

  private static String encode(final byte[] input,final byte[] pass) {
    final byte[] output = new byte[input.length];
    if(pass.length==0){throw new IllegalArgumentException("empty security key");}
    int spos = 0;
    for(int i=0;i<input.length;i++){
      output[i] = (byte) (input[i] ^ pass[i]);
      ++spos;
      if(spos >= pass.length){
        spos = 0;
      }
    }
    return new BASE64Encoder().encode(output);
  }

  private static String decode(String stream, final byte[] key){
    int spos = 0;
    try {
      byte[] output = new BASE64Decoder().decodeBuffer(stream);
      for(int i=0;i<output.length;i++){
        output[i]= (byte)(output[i]^key[spos]);
        ++spos;
        if(spos >= key.length) { spos =0;}
      }
      return new String(output,"UTF-8");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }

  public static void main(String[] args) {

    try {
      // Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
      String orig = "Lorem";
      String s = "LRUXFxk=";
      String key = "azerty123456789";
      byte[] input = orig.getBytes("UTF-8");
      byte[] keyb = key.getBytes();
      System.out.println("Original String : " + orig);
      System.out.println("String after encrytion :  " + encode(input, keyb));
      System.out.println("String after decrytion :  " + decode(s, keyb));
    } catch (Exception e) {

    }

  }

}
