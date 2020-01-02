package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;
import ekptg.report.Utils;

public class REVLaporanPerjanjianSewaHampirTamatBulanan extends EkptgReportServlet {

	public REVLaporanPerjanjianSewaHampirTamatBulanan () {
		super.setReportName("REVLaporanPerjanjianSewaHampirTamatBulanan");
		super.setFolderName("php2\\REV\\LAPORAN");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String idBulan = (String)parameters.get("BULAN");
		String bulan = Utils.getBulan(Integer.parseInt(idBulan));
		String tahun = (String)parameters.get("TAHUN");

		parameters.put("ID_BULAN",ekptg.helpers.Utils.digitLastFormatted(idBulan, 2));
		parameters.put("BULAN",bulan);
		parameters.put("TAHUN",ekptg.helpers.Utils.digitLastFormatted(tahun, 4));
	}
}
