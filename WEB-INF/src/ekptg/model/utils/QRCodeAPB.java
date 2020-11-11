package ekptg.model.utils;
 
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.BufferedImageLuminanceSource;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import ekptg.report.EkptgReportServlet;
 
public class QRCodeAPB {
	
	static Logger myLog = Logger.getLogger(ekptg.model.utils.QRCodeAPB.class);
	String returnVal = "";
	//String path = "code.png";
	String charset = "UTF-8";

	public static void main(String args[]) throws Exception {
		new QRCodeAPB();
	}
	
	public QRCodeAPB() {	}
	
	public QRCodeAPB(String noFail, String noFailEncode) {				
		String slash = "/";
		String noFailremoveslash = noFail.replaceAll(slash,"");
		noFailremoveslash = noFailremoveslash.replaceAll("\\s+","");
		myLog.info("noFail = "+noFail+",noFailremoveslash = "+noFailremoveslash);
		
		//String path =  getAppContext()+"/reports/php2/qrcode/"+noFailremoveslash+".png";
		
		ResourceBundle rb = ResourceBundle.getBundle("file");
		String path = rb.getString("qrcodepath")+noFailremoveslash+".png";
		
		//---untuk local dev----
		//String path =  "C:/eclipse/workspace/reports/php2/qrcode/"+noFailremoveslash+".png";
		
		String charset = "UTF-8";
		Map<EncodeHintType, ErrorCorrectionLevel> hashMap
        = new HashMap<EncodeHintType,
                      ErrorCorrectionLevel>();

		hashMap.put(EncodeHintType.ERROR_CORRECTION,
                ErrorCorrectionLevel.L);

		// Create the QR code and save
		// in the specified folder
    	// as a jpg file
		try {
			createQR(noFailEncode, path, charset, hashMap, 200, 200);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("QR Code Generated!!! ");
	}
		
		// Function to create the QR code
	    public static void createQR(String data, String path,
	                                String charset, Map hashMap,
	                                int height, int width)
	        throws WriterException, IOException
	    {
	 
	        BitMatrix matrix = new MultiFormatWriter().encode(
	            new String(data.getBytes(charset), charset),
	            BarcodeFormat.QR_CODE, width, height);
	 
	        MatrixToImageWriter.writeToFile(
	                matrix,
	                path.substring(path.lastIndexOf('.') + 1),
	                new File(path));
	    }
	/**
	 * Fungsi mendapatkan nama context (folder fizikal applikasi)
	 * Dibuat Oleh	: Mohamad Rosli
	 * Dibuat Pada	: 21/01/2020, salin dari EkptgReportServlet
	 * Dikemaskini Oleh	: 
	 * Dikemaskini Pada :
	 * @return
	 */
	private String getAppContext(){
		String appContext ="myetapp";
		ResourceBundle rb = ResourceBundle.getBundle("file");
		if(rb.getString("context_name") != null)
			appContext = rb.getString("context_name");
		
		myLog.info("getAppContext="+appContext);
		return appContext;
	}
}

