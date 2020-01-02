package ekptg.model.utils;
 
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.BufferedImageLuminanceSource;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
 
public class QRCode {
	
	String returnVal = "";
	String path = "code.png";
	String charset = "UTF-8";

	public static void main(String args[]) throws Exception {
		new QRCode();
	}
	public QRCode() {
		
	}
	
	public QRCode(String noFail, String noFailEncode) {
		System.out.println("noFail = "+noFail);
				
		String slash = "/";
		String noFailremoveslash = noFail.replaceAll(slash,"");
		System.out.println("noFailremoveslash = "+noFailremoveslash);
		String path = "/usr/local/tomcat-fat/webapps/v6-fat/reports/ppk/qrcode/"+noFailremoveslash+".png";
		//String path = "http://myetapp.gov.my/v6-fat/"+noFailremoveslash+".png";
		String charset = "UTF-8";
		
		Map map = new HashMap();
		
		map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		try {
			encript(noFailEncode, path, charset, map, 600, 600);
			System.out.println("Read here QRCode");
			//System.out.println("No error occured...");
			//returnVal = decript(path, charset, map);
			//System.out.print(returnVal);
			
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	
	private String getReportPath(ServletContext context,String rtype){
		String realPathReport = context.getRealPath(File.separator + rtype + File.separator)
			.replace("johor" + File.separator, "")
			.replace("kedah" + File.separator, "")
			.replace("ekptgv3" + File.separator, "")
			.replace("kelantan" + File.separator, "")
			.replace("melaka" + File.separator, "")
			.replace("ns" + File.separator, "")
			.replace("pahang" + File.separator, "")
			.replace("penang" + File.separator, "")
			.replace("perak" + File.separator, "")
			.replace("perlis" + File.separator, "")
			.replace("selangor" + File.separator, "")
			.replace("terengganu" + File.separator, "")
			.replace("hq" + File.separator, "")
			.replace("ekptgv2" + File.separator, "")
			.replace("wp" + File.separator, "");
			//.replace(getAppContext() + File.separator, "");
		//myLogger.info("realPathReport="+realPathReport);
		return realPathReport;
	
	}
	
	@SuppressWarnings("deprecation")
	
	
	public void encript(String output, String file, String charset, Map hintMap, int width, int height) throws WriterException, IOException{
		System.out.println("output = "+output);
		System.out.println("file = "+file);
		System.out.println("charset = "+charset);
		BitMatrix mat = new MultiFormatWriter().encode(new String(output.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
		System.out.println("Read here1");
		//file = "JKPTGPK100609172017.png";
		MatrixToImageWriter.writeToFile(mat, file.substring(file.lastIndexOf('.') + 1), new File(file));
		System.out.println("Read encript"); 
	}
	public String decript(String file, String charset, Map map) throws IOException, NotFoundException{
		System.out.println("decript");
		BinaryBitmap binBit = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new File(file)))));
		Result result = new MultiFormatReader().decode(binBit, map);
		return result.getText();
	
	}
	

}

