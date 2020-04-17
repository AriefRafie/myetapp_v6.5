package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class REVSenaraiAkaunBelumTerima extends EkptgReportServlet {

	public REVSenaraiAkaunBelumTerima () {
		super.setReportName("REVSenaraiAkaunBelumTerima");
		super.setFolderName("php2\\REV\\LAPORAN");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String tajuk = "";
		String usiaABTDari = "0";
		String usiaABTHingga = "0";
		String idUsiaABT = (String)parameters.get("ID_USIA_ABT");
		
		if ("1".equals(idUsiaABT)) {
			tajuk = "SENARAI AKAUN BELUM TERIMA HINGGA ENAM (6) BULAN";
			usiaABTDari = "0";
			usiaABTHingga = "6";
		} else if ("2".equals(idUsiaABT)) {
			tajuk = "SENARAI AKAUN BELUM TERIMA LEBIH DARI ENAM (6) BULAN HINGGA DUA BELAS (12) BULAN";
			usiaABTDari = "6";
			usiaABTHingga = "12";
		} else if ("3".equals(idUsiaABT)) {
			tajuk = "SENARAI AKAUN BELUM TERIMA LEBIH DARI DUA BELAS (12) BULAN HINGGA TIGA PULUH ENAM (36) BULAN";
			usiaABTDari = "12";
			usiaABTHingga = "36";
		} else if ("4".equals(idUsiaABT)) {
			tajuk = "SENARAI AKAUN BELUM TERIMA LEBIH DARI TIGA PULUH ENAM (36) BULAN HINGGA TUJUH PULUH DUA (72) BULAN";
			usiaABTDari = "36";
			usiaABTHingga = "72";
		} else if ("5".equals(idUsiaABT)) {
			tajuk = "SENARAI AKAUN BELUM TERIMA LEBIH DARI TUJUH PULUH DUA (72) BULAN";
			usiaABTDari = "72";
			usiaABTHingga = "1000";
		} else {
			tajuk = "SENARAI AKAUN BELUM TERIMA KESELURUHAN";
			usiaABTDari = "0";
			usiaABTHingga = "1000";
		}
		
		parameters.put("TAJUK", tajuk);
		parameters.put("USIA_ABT_DARI", usiaABTDari);
		parameters.put("USIA_ABT_HINGGA", usiaABTHingga);
		
	}
}
