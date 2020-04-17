/**
 * 
 */
package ekptg.view.php2.laporan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;

public class REV_LaporanPerjanjianSewaHampirTamat extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	

	@Override
	public String doTemplate2() throws Exception {
		String idKategori = getParam("idKategori");
		Date currentDate = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(currentDate);
		int bulan = cal.get(Calendar.MONTH) + 1;
		int tahun = cal.get(Calendar.YEAR);
		
		if ("1".equals(idKategori)) {
			context.put("tarikhHingga", getParam("tarikhHingga") == null || "".equals(getParam("tarikhHingga"))? sdf.format(currentDate) : getParam("tarikhHingga"));
		} else if ("2".equals(idKategori)) {
			this.context.put("selectBulan", HTML.SelectBulan("socBulan", Long.valueOf(bulan)));
			context.put("tahun", getParam("tahun") == null || "".equals(getParam("tahun"))? tahun : getParam("tahun"));
		} else if ("3".equals(idKategori)) {
			context.put("tahun", getParam("tahun") == null || "".equals(getParam("tahun"))? tahun : getParam("tahun"));
		} else if ("4".equals(idKategori)) {
			context.put("tarikhMula", getParam("tarikhMula") == null || "".equals(getParam("tarikhMula"))? sdf.format(currentDate) : getParam("tarikhMula"));
			context.put("tarikhHingga", getParam("tarikhHingga") == null || "".equals(getParam("tarikhHingga"))? sdf.format(currentDate) : getParam("tarikhHingga"));
		}
		
		context.put("idKategori", idKategori);
		return "app/php2/laporan/REV_LaporanPerjanjianSewaHampirTamat.jsp";
	}
}
