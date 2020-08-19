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
	String path = "code.png";
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
	
		String path =  getAppContext()+"/reports/php2/qrcode/"+noFailremoveslash+".png";
		String charset = "UTF-8";
		
		Map<EncodeHintType, ErrorCorrectionLevel> map = new HashMap<EncodeHintType, ErrorCorrectionLevel>();		
		map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		try {
			encript(noFailEncode, path, charset, map, 600, 600);
			myLog.info("Read here QRCode");
			
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} 
	
	@SuppressWarnings("deprecation")
	public void encript(String output, String file, String charset, Map<EncodeHintType, ErrorCorrectionLevel> hintMap, int width, int height) 
		throws WriterException, IOException{
		myLog.info("encript:file"+file);
		BitMatrix mat = new MultiFormatWriter().encode(new String(output.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
		//file = "JKPTGPK100609172017.png";
		MatrixToImageWriter.writeToFile(mat, file.substring(file.lastIndexOf('.') + 1), new File(file));
	
	}
	
	public String decript(String file, String charset, Map<DecodeHintType, ?> map) throws IOException, NotFoundException{
		myLog.info("decript:file"+file);
		BinaryBitmap binBit = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new File(file)))));
		Result result = new MultiFormatReader().decode(binBit, map);
		return result.getText();
	
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

