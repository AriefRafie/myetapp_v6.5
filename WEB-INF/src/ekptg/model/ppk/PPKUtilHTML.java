package ekptg.model.ppk;
/**
 * Created by Mohamad Rosli
 * Create alternative utilities for PPK 
 */
import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;

import ekptg.model.entities.Tblppkrujunit;
import ekptg.model.entities.Tblrujcarabayar;
import ekptg.model.entities.Tblrujjenisbayaran;
import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.utils.FrmCaraBayarData;
import ekptg.model.utils.FrmJenisBayaranData;

public class PPKUtilHTML {
	/**
	 * Create selection Unit Pusaka 
	 * */
	static Logger myLog = Logger.getLogger(ekptg.model.ppk.PPKUtilHTML.class);
	/**
	 *  Dibuat Pada 2017/10/29, salinan dari HTML
	 * @param id_jkptg
	 * @param selectName
	 * @param selectedValue
	 * @param disability
	 * @param jsFunction
	 * @return
	 * @throws Exception
	 */
	public static String SelectTempatBicaraByPejabatJKPTG(String myId,String idJkptg,
		String selectName, Long selectedValue, String disability,
		String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");

			Vector<Tblrujpejabatjkptg> v = PPKUtilData.getTempatBicaraByPejabatJKPTG(myId,idJkptg);
			Tblrujpejabatjkptg f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpejabatjkptg) v.get(i);
				if (f.getIdPejabatjkptg().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPejabatjkptg()
						+ ">" + f.getKodJkptg() + " - "
						+ f.getNamaPejabat().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	public static String SelectUnitPPK(String selectName,
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
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector<?> v = PPKUtilData.getUnitPPK();
			Hashtable<?, ?> f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Hashtable<?, ?>) v.get(i);
				if (f.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.get("id")
						+ ">" + f.get("keterangan").toString().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}
	/**
	* Create selection Unit Pusaka mengikut negeri untuk Laporan
	*/
	public static String SelectUnitPPKLaporan(
		String selectName,Long selectedValue, String disability,String jsFunction,String idnegeri) 
		throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			String s_ = "";
			if(String.valueOf(selectedValue).equals("0"))
				s_ = "selected";

			sb.append("<option value=\"-1\">SILA PILIH</option>\n");
			sb.append("<option " + s_ + "  value=\"0\">SEMUA UNIT</option>\n");

			Vector<?> v = PPKUtilData.getUnitPPK(idnegeri);
			Hashtable<?, ?> f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Hashtable<?, ?>) v.get(i);
				if (f.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.get("id")
						+ ">" + f.get("keterangan").toString().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}
	/**
	 * Create selection Unit Pusaka mengikut negeri 
	 * */
	public static String SelectUnitPPK(String selectName,Long selectedValue, String disability,
			String jsFunction,String idnegeri)throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"0\">Sila Pilih</option>\n");
			Vector<?> v = PPKUtilData.getUnitPPK(idnegeri);
			Hashtable<?, ?> f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Hashtable<?, ?>) v.get(i);
				if (f.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.get("id")
						+ ">" + f.get("keterangan").toString().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	/**
	 * Create selection Cara Bayaran 
	 * */
	public static String SelectCaraBayaran(String selectName,Long selectedValue, String disability,
			String jsFunction)throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"0\">Sila Pilih</option>\n");
			Vector<?> v = FrmCaraBayarData.getList();
			Tblrujcarabayar f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujcarabayar) v.get(i);
				if (f.getIdCarabayar().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdCarabayar()
						+ ">" + f.getKeterangan().toString().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	/**
	 * Create selection Jenis Bayaran
	 * */
	public static String SelectJenisBayaranBySeksyen(String idSeksyen,String selectName,Long selectedValue, String disability,
			String jsFunction)throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"0\">Sila Pilih</option>\n");
			Vector<?> v = FrmJenisBayaranData.getJenisBayaranBySeksyen(idSeksyen);
			Tblrujjenisbayaran f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenisbayaran) v.get(i);
				if (f.getIdJenisbayaran().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenisbayaran()
						+ ">" + f.getKeterangan().toString().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	public static String SelectPegawaiLaporan(String idNegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");
			/*sb.append("<option value='\"\"'>SILA PILIH</option>\n");*/
			
			Vector<Tblppkrujunit> v = PPKUtilData.getSenaraiPegawaiLaporan(idNegeri);
			Tblppkrujunit f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppkrujunit) v.get(i);
				if (f.getidunitpk().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
//				sb.append("<option " + s + " value=" + f.getidunitpk() + ">"
//						+ f.getkod() + " - " + f.getnamapegawai() + " "
//						+ f.getcatatan() + "</option>\n");
				sb.append("<option " + s + " value=" + f.getidunitpk() + ">"
						+ f.getnamapegawai() + " "
						+ f.getcatatan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}
	
	public static String SelectPegawaiLaporan_KPP(String idNegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction,  boolean kpp_hq) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");
			/*sb.append("<option value='\"\"'>SILA PILIH</option>\n");*/
			
			Vector<Tblppkrujunit> v = PPKUtilData.getSenaraiPegawaiLaporan_KPP(idNegeri,kpp_hq);
			Tblppkrujunit f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppkrujunit) v.get(i);
				if (f.getidunitpk().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
//				sb.append("<option " + s + " value=" + f.getidunitpk() + ">"
//						+ f.getkod() + " - " + f.getnamapegawai() + " "
//						+ f.getcatatan() + "</option>\n");
				sb.append("<option " + s + " value=" + f.getidunitpk() + ">"
						+ f.getnamapegawai() + " "
						+ f.getcatatan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}


}