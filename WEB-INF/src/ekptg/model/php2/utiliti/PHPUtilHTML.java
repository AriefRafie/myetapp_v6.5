package ekptg.model.php2.utiliti;

/**
 * Created by Nurul Ain
 * Create alternative utilities for PHP 
 */
import java.util.Vector;

import org.apache.log4j.Logger;

import ekptg.model.entities.Tblphprujjenistujuan;

public class PHPUtilHTML {

	static Logger myLogger = Logger.getLogger(PHPUtilHTML.class);

	public static String SelectTujuanByIdSuburusan(String idSuburusan,
			String selectName, Long selectedValue, String disability)
			throws Exception {
			return SelectTujuanByIdSuburusan(idSuburusan, selectName, selectedValue,
					disability, null);
		}
	
	public static String SelectTujuanByIdSuburusan(String idSuburusan, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector<Tblphprujjenistujuan> v = PHPUtilData.getTujuanBySubUrusan(idSuburusan);
			Tblphprujjenistujuan jenistujuan = null;
			String tujuan = "";
			for (int i = 0; i < v.size(); i++) {
				jenistujuan = (Tblphprujjenistujuan) v.get(i);
				if (jenistujuan.getIdJenistujuan().equals(selectedValue)) {
					tujuan = "selected";
				} else {
					tujuan = "";
				}
				sb.append("<option " + tujuan + " value=" + jenistujuan.getIdJenistujuan() + ">"
						+ jenistujuan.getKodJenistujuan() + " - " + jenistujuan.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}
	
	public static String SelectJenisNoFail(String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (selectedValue.intValue() == 1) {
				sb.append("<option selected value=1>NO. FAIL NEGERI</option>\n");
			} else {
				sb.append("<option value=1>NO. FAIL NEGERI</option>\n");
			}
			if (selectedValue.intValue() == 2) {
				sb.append("<option selected value=2>NO. FAIL IBU PEJEBAT</option>\n");
			} else {
				sb.append("<option value=2>NO. FAIL IBU PEJEBAT</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
 
		return sb.toString();
	}
}