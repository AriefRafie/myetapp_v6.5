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

public class REV_SenaraiAkaunBelumTerima extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public String doTemplate2() throws Exception {

		String idKategori = getParam("idKategori");Date currentDate = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(currentDate);
		int tahun = cal.get(Calendar.YEAR);

		if ("7".equals(idKategori)) {
			context.put("tarikhMula", getParam("tarikhMula") == null || "".equals(getParam("tarikhMula"))? sdf.format(currentDate) : getParam("tarikhMula"));
			context.put("tarikhHingga", getParam("tarikhHingga") == null || "".equals(getParam("tarikhHingga"))? sdf.format(currentDate) : getParam("tarikhHingga"));
		} else if ("8".equals(idKategori)) {
			context.put("tahun", getParam("tahun") == null || "".equals(getParam("tahun"))? tahun : getParam("tahun"));
			context.put("tahunHingga", getParam("tahunHingga") == null || "".equals(getParam("tahunHingga"))? tahun : getParam("tahunHingga"));
		}

		this.context.put("idKategori", idKategori);
		return "app/php2/laporan/REV_SenaraiAkaunBelumTerima.jsp";
	}
}
