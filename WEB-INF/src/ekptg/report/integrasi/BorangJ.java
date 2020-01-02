package ekptg.report.integrasi;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.DbException;
import ekptg.report.EkptgReportServlet;

public class BorangJ extends EkptgReportServlet {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception, DbException {
		String REPORT_TYPE = (String) parameters.get("rType");
		
		if ("1".equalsIgnoreCase(REPORT_TYPE)) {
			// borang J
			super.setReportName("BorangJ");
			super.setFolderName("integrasi");

			String NamaPenerima = (String) parameters.get("NamaPenerima");
			String AlamatPenerima1 = (String) parameters.get("AlamatPenerima1");
			String AlamatPenerima2 = (String) parameters.get("AlamatPenerima2");
			String AlamatPenerima3 = (String) parameters.get("AlamatPenerima3");
			String AlamatPenerima4 = (String) parameters.get("AlamatPenerima4");
			String TajukSurat = (String) parameters.get("TajukSurat");
			String RujukanTuan = (String) parameters.get("RujukanTuan");
			String RujukanKami = (String) parameters.get("RujukanKami");
			String TarikhHantarMasehi = (String) parameters.get("TarikhHantarMasehi");
			String TarikhHantarHijrah = (String) parameters.get("TarikhHantarHijrah");
			String TarikhSurat = (String) parameters.get("TarikhSurat");
			String TarikhTerima = (String) parameters.get("TarikhTerima");
			String NamaPengirim = (String) parameters.get("NamaPengirim");
			String JawatanPengirim = (String) parameters.get("JawatanPengirim");
			String JabatanPengirim = (String) parameters.get("JabatanPengirim");

			NamaPenerima = sanitizeReportField(NamaPenerima);
			AlamatPenerima1 = sanitizeReportField(AlamatPenerima1);
			AlamatPenerima2 = sanitizeReportField(AlamatPenerima2);
			AlamatPenerima3 = sanitizeReportField(AlamatPenerima3);
			AlamatPenerima4 = sanitizeReportField(AlamatPenerima4);
			TajukSurat = sanitizeReportField(TajukSurat);
			RujukanTuan = sanitizeReportField(RujukanTuan);
			RujukanKami = sanitizeReportField(RujukanKami);
			TarikhHantarMasehi = sanitizeReportField(TarikhHantarMasehi);
			TarikhHantarHijrah = sanitizeReportField(TarikhHantarHijrah);
			TarikhSurat = sanitizeReportField(TarikhSurat);
			TarikhTerima = sanitizeReportField(TarikhTerima);
			NamaPengirim = sanitizeReportField(NamaPengirim);
			JawatanPengirim = sanitizeReportField(JawatanPengirim);
			JabatanPengirim = sanitizeReportField(JabatanPengirim);
			
			parameters.put("NamaPenerima", NamaPenerima);
			parameters.put("AlamatPenerima1", AlamatPenerima1);
			parameters.put("AlamatPenerima2", AlamatPenerima2);
			parameters.put("AlamatPenerima3", AlamatPenerima3);
			parameters.put("AlamatPenerima4", AlamatPenerima4);
			parameters.put("TajukSurat", TajukSurat);
			parameters.put("RujukanTuan", RujukanTuan);
			parameters.put("RujukanKami", RujukanKami);
			parameters.put("TarikhHantarMasehi", TarikhHantarMasehi);
			parameters.put("TarikhHantarHijrah", TarikhHantarHijrah);
			parameters.put("TarikhSurat", TarikhSurat);
			parameters.put("TarikhTerima", TarikhTerima);
			parameters.put("NamaPengirim", NamaPengirim);
			parameters.put("JawatanPengirim", JawatanPengirim);
			parameters.put("JabatanPengirim", JabatanPengirim);
		}
	}
	
	private static String sanitizeReportField(String REPORT_FIELD) throws Exception {
		String returnValue = "";
		
		try {
			returnValue = REPORT_FIELD;
			returnValue = returnValue == null ? "" : returnValue;
			returnValue = returnValue.replace("/", "/");
			returnValue = returnValue.replace("&", "&");
		} finally {
			
		}
		return returnValue;
	}
}