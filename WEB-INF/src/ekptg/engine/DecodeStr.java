package ekptg.engine;

import java.io.File;
import java.io.FileOutputStream;

import sun.misc.BASE64Decoder;
// needed only for main() method.

/**
 * Provides encoding of raw bytes to base64-encoded characters, and
 * decoding of base64 characters to raw bytes.
 *
 * @author Kevin Kelley (kelley@ruralnet.net)
 * @version 1.3
 */
public class DecodeStr {

	
	public static void main( String[] args )
    {     
        String encodedBytes = "x"; 
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decodedBytes;
            FileOutputStream fop;
            decodedBytes = new BASE64Decoder().decodeBuffer(encodedBytes);
            File file = new File("C://decode/test.txt");
            fop = new FileOutputStream(file);

            fop.write(decodedBytes);

            fop.flush();
            fop.close();
            System.out.println("Created");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
