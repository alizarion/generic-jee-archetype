package io.github.alizarion.common.secure.utils;

import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.codec.DecoderException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.apache.commons.codec.binary.Hex.decodeHex;
import static org.apache.commons.codec.binary.Hex.encodeHex;
import static org.apache.commons.io.FileUtils.readFileToByteArray;
import static org.apache.commons.io.FileUtils.writeStringToFile;

/**
 * @author selim@openlinux.fr.
 */
public class KeyStoreUtils {

    private static final String ALGO = "HS512";

    private static final File KEYSTORE =
            new File(System.getProperty("java.io.tmp") +
            File.separator + "it_secure.keystore");

       public static SecretKey generateKey() throws NoSuchAlgorithmException
       {
           return  MacProvider.generateKey();
       }

       public static void saveKey(final SecretKey key) throws IOException
       {
           byte[] encoded = key.getEncoded();
           char[] hex = encodeHex(encoded);
           String data = String.valueOf(hex);
           writeStringToFile(KEYSTORE, data);
       }

       public static SecretKey loadKey() throws
               IOException,
               NoSuchAlgorithmException {

           if (!KEYSTORE.exists()){
               saveKey(generateKey());
           }
           String data = new String(readFileToByteArray(KEYSTORE));
           char[] hex = data.toCharArray();
           byte[] encoded;
           try
           {
               encoded = decodeHex(hex);
           }
           catch (DecoderException e)
           {
               // TODO Auto-generated catch block
               e.printStackTrace();
               return null;
           }
           return new SecretKeySpec(encoded, ALGO);
       }
}
