package ekptg.model.php2.utiliti;

/**
 * Created by Nurul Ain
 * Create alternative utilities for PHP 
 */
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.model.entities.Tblpfdfail;
import ekptg.model.entities.Tblphprujjenistujuan;
import ekptg.model.entities.Tblrujsubsuburusan;

public class PHPUtilHTML {

	static Logger myLogger = Logger.getLogger(PHPUtilHTML.class);

	public static String SelectTujuanByIdSuburusan(String idSuburusan,
			String selectName, Long selectedValue, String disability)
			throws Exception {
			return SelectSubsuburusanByIdSuburusan(idSuburusan, selectName, selectedValue,
					disability, null);
		}
	
	public static String SelectSubsuburusanByIdSuburusan(String idSuburusan, String selectName,
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
			Vector<Tblrujsubsuburusan> v = PHPUtilData.getSubsuburusanBySubUrusan(idSuburusan);
			Tblrujsubsuburusan t = null;
			String ss = "";
			for (int i = 0; i < v.size(); i++) {
				t = (Tblrujsubsuburusan) v.get(i);
				if (t.getIdSubsuburusan().equals(selectedValue)) {
					ss = "selected";
				} else {
					ss = "";
				}
				sb.append("<option " + ss + " value=" + t.getIdSubsuburusan() + ">"
						+ t.getNamaSubsuburusan()
//						+ t.getKodSubsuburusan() + " - " + t.getNamaSubsuburusan()
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
	
	public static String SelectNoFailByIdPemohon(String userId, String selectName,
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
			Vector<Tblpfdfail> v = PHPUtilData.getNoFailByIdPemohon(userId);
			Tblpfdfail senaraiNoFail = null;
			String noFail = "";
			for (int i = 0; i < v.size(); i++) {
				senaraiNoFail = (Tblpfdfail) v.get(i);
				if (senaraiNoFail.getIdFail().equals(selectedValue)) {
					noFail = "selected";
				} else {
					noFail = "";
				}
				sb.append("<option " + noFail + " value=" + senaraiNoFail.getIdFail() + ">"
						+ senaraiNoFail.getNoFail()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();		
	}
	
	//yati tambah
		public static String SelectJenisTujuanAPB(String selectName,
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
				Vector v = PHPUtilData.getJenistujuanAPB();
				Tblphprujjenistujuan f = null;
				String s = "";
				for (int i = 0; i < v.size(); i++) {
					f = (Tblphprujjenistujuan) v.get(i);
					if (f.getIdJenistujuan().equals(selectedValue)) {
						s = "selected";
					} else {
						s = "";
					}
					sb.append("<option " + s + " value=" + f.getIdJenistujuan()
							+ ">" + f.getKodJenistujuan() + " - "
							+ f.getKeterangan() + "</option>\n");
				}
				sb.append("</select>");
			} catch (Exception ex) {
				ex.printStackTrace();
				throw ex;
			}

			return sb.toString();
		}
	
	

}