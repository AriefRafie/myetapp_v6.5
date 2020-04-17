package etapp.util;

import java.io.File;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;


public class DocumentUtil {
	
	
	public static void main(String[] args) throws Exception {
		
		String file1 = "c:/test-jod/abc_html_test.html";
		String file2 = "c:/test-jod/abc_html_test.doc";
		convertDocument(file1, file2);	
		
	}

	public static void convertDocument(String file1, String file2) throws Exception {
		File fileIn = new File(file1);
		File fileOut = new File(file2);
		
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
		try {
			connection.connect();
		} catch(Exception e) {
			throw new Exception("Connection to OpenOffice not available.");
		}
		DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
	    converter.convert(fileIn, fileOut);

	    connection.disconnect();
	}

}
