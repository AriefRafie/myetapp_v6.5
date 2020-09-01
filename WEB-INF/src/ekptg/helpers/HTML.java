package ekptg.helpers;

import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;

import ekptg.model.entities.Tblpdtagendamesyuarat;
import ekptg.model.entities.Tblpdtaktabab;
import ekptg.model.entities.Tblpdtaktabahagian;
import ekptg.model.entities.Tblpdtaktapenggal;
import ekptg.model.entities.Tblpdtaktaseksyen;
import ekptg.model.entities.Tblpdtenakmenbab;
import ekptg.model.entities.Tblpdtenakmenbahagian;
import ekptg.model.entities.Tblpdtenakmenseksyen;
import ekptg.model.entities.Tblpdtminitmesyuarat;
import ekptg.model.entities.Tblpdtrujdokumenpekeliling;
import ekptg.model.entities.Tblpdtrujperkarapekeliling;
import ekptg.model.entities.Tblpfdfail;
import ekptg.model.entities.Tblpfdrujfaharasat;
import ekptg.model.entities.Tblpfdrujlokasifail;
import ekptg.model.entities.Tblpfdrujlokasimesyuarat;
import ekptg.model.entities.Tblphprujdokumen;
import ekptg.model.entities.Tblphprujjenistujuan;
import ekptg.model.entities.Tblphprujtujuankaitan;
import ekptg.model.entities.Tblppkpeguam;
import ekptg.model.entities.Tblppkrujjenisperintah;
import ekptg.model.entities.Tblppkrujunit;
import ekptg.model.entities.Tblppthakmilik;
import ekptg.model.entities.Tblpptjabatanteknikal;
import ekptg.model.entities.Tblpptpihakberkepentingan;
import ekptg.model.entities.Tblrujagensi;
import ekptg.model.entities.Tblrujbandar;
import ekptg.model.entities.Tblrujbangsa;
import ekptg.model.entities.Tblrujbank;
import ekptg.model.entities.Tblrujbebanan;
import ekptg.model.entities.Tblrujbulan;
import ekptg.model.entities.Tblrujcarabayar;
import ekptg.model.entities.Tblrujdaerah;
import ekptg.model.entities.Tblrujdaerahpenggawa;
import ekptg.model.entities.Tblrujjawatan;
import ekptg.model.entities.Tblrujjenisaduan;
import ekptg.model.entities.Tblrujjenisbayaran;
import ekptg.model.entities.Tblrujjenisdokumen;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujjenisnopb;
import ekptg.model.entities.Tblrujjenispb;
import ekptg.model.entities.Tblrujjenisrizab;
import ekptg.model.entities.Tblrujjenistanah;
import ekptg.model.entities.Tblrujkategori;
import ekptg.model.entities.Tblrujkategoripemohon;
import ekptg.model.entities.Tblrujkementerian;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.entities.Tblrujluas;
import ekptg.model.entities.Tblrujmukim;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujpegawai;
import ekptg.model.entities.Tblrujpejabat;
import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.entities.Tblrujpfdtarafkeselamatan;
import ekptg.model.entities.Tblrujseksyen;
import ekptg.model.entities.Tblrujstatus;
import ekptg.model.entities.Tblrujsubkategori;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.entities.Tblrujulasanringkasan;
import ekptg.model.entities.Tblrujurusan;
import ekptg.model.entities.Tblrujwarganegara;
import ekptg.model.entities.Users;
import ekptg.model.ppk.PPKUtilHTML;

public class HTML {
	
	static Logger myLogger = Logger.getLogger(HTML.class);

	public static String SelectNegeri(String selectName) throws Exception {
		return SelectNegeri(selectName, null, null, null);
	}

	public static String SelectNegeri(String selectName, String jsFunction)
			throws Exception {
		return SelectNegeri(selectName, null, null, jsFunction);
	} 

	public static String SelectNegeri(String selectName, Long selectedValue)
			throws Exception {
		return SelectNegeri(selectName, selectedValue, null, null);
	}

	public static String SelectNegeri(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectNegeri(selectName, selectedValue, disability, null);
	}
	
	public static String SelectNegeriWithOnChange(String selectName) throws Exception {
		return SelectNegeriWOC(selectName);
	}

	private static String SelectNegeriWOC(String selectName)  throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' id='"+selectName+"'");
			sb.append(" onchange='javascript:doChangeNegeri()'> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getNegeri();
			Tblrujnegeri f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujnegeri) v.get(i);
				sb.append("<option " + s + " value=" + f.getIdNegeri() + ">"
						+ f.getKodNegeri() + " - " + f.getNamaNegeri()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectNegeri(String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select id=\""+selectName+"\" name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getNegeri();
			Tblrujnegeri f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujnegeri) v.get(i);
				if (f.getIdNegeri().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdNegeri() + ">"
						+ f.getKodNegeri() + " - " + f.getNamaNegeri()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// select negeri yg ada laut
	public static String SelectNegeriAPB(String selectName, Long selectedValue,
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
			Vector v = DB.getNegeriPerairan();
			Tblrujnegeri f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujnegeri) v.get(i);
				if (f.getIdNegeri().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdNegeri() + ">"
						+ f.getKodNegeri() + " - " + f.getNamaNegeri()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// * SELECT NEGERI FILTER BY ADA MAHKAMAH - ELLY 140909
	public static String SelectNegeriByMahkamah(String selectName)
			throws Exception {
		return SelectNegeriByMahkamah(selectName, null, null, null);
	}

	public static String SelectNegeriByMahkamah(String selectName,
			String jsFunction) throws Exception {
		return SelectNegeriByMahkamah(selectName, null, null, jsFunction);
	}

	public static String SelectNegeriByMahkamah(String selectName,
			Long selectedValue) throws Exception {
		return SelectNegeriByMahkamah(selectName, selectedValue, null, null);
	}

	public static String SelectNegeriByMahkamah(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectNegeriByMahkamah(selectName, selectedValue, disability,
				null);
	}

	public static String SelectNegeriByMahkamah(String selectName,
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
			Vector v = DB.getNegeriByMahkamah();
			Tblrujnegeri f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujnegeri) v.get(i);
				if (f.getIdNegeri().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdNegeri() + ">"
						+ f.getKodNegeri() + " - " + f.getNamaNegeri()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// end

	// * SELECT NEGERI FILTER BY ADA MAHKAMAH SYARIAH - ELLY 230909

	public static String SelectNegeriByMahkamahSyariah(String selectName)
			throws Exception {
		return SelectNegeriByMahkamahSyariah(selectName, null, null, null);
	}

	public static String SelectNegeriByMahkamahSyariah(String selectName,
			String jsFunction) throws Exception {
		return SelectNegeriByMahkamahSyariah(selectName, null, null, jsFunction);
	}

	public static String SelectNegeriByMahkamahSyariah(String selectName,
			Long selectedValue) throws Exception {
		return SelectNegeriByMahkamahSyariah(selectName, selectedValue, null,
				null);
	}

	public static String SelectNegeriByMahkamahSyariah(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectNegeriByMahkamahSyariah(selectName, selectedValue,
				disability, null);
	}

	public static String SelectNegeriByMahkamahSyariah(String selectName,
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
			Vector v = DB.getNegeriByMahkamahSyariah();
			Tblrujnegeri f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujnegeri) v.get(i);
				if (f.getIdNegeri().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdNegeri() + ">"
						+ f.getKodNegeri() + " - " + f.getNamaNegeri()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// end

	public static String SelectNegeriExcludePelbagaiNegeri(String selectName,
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
			Vector v = DB.getNegeriExcludePelbagaiNegeri();
			Tblrujnegeri f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujnegeri) v.get(i);
				if (f.getIdNegeri().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdNegeri() + ">"
						+ f.getKodNegeri() + " - " + f.getNamaNegeri()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ** Daerah
	public static String SelectDaerah(String selectName) throws Exception {
		return SelectDaerah(selectName, null, null);
	}

	public static String SelectDaerah(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getDaerah();
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getKodDaerah() + " - " + f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	public static String SelectDaerah(String selectName, Long selectedValue,
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
			Vector v = DB.getDaerah();
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getKodDaerah() + " - " + f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ** Daerah mengikut negeri

	public static String SelectDaerahByNegeri(String idnegeri, String selectName)
			throws Exception {
		return SelectDaerahByNegeri(idnegeri, selectName, null, null, null);
	}

	public static String SelectDaerahByNegeri(String idnegeri,
			String selectName, String jsFunction) throws Exception {
		return SelectDaerahByNegeri(idnegeri, selectName, null, null,
				jsFunction);
	}

	public static String SelectDaerahByNegeri(String idnegeri,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectDaerahByNegeri(idnegeri, selectName, selectedValue,
				disability, null);
	}
	
	public static String SelectDaerahByNegeri_KPP(String idnegeri,
			String selectName, Long selectedValue, String disability, String userId, boolean kpp_hq)
			throws Exception {		
	
		return SelectDaerahByNegeri_KPP(idnegeri, selectName, selectedValue,
				disability,null, userId, kpp_hq);
	}

	public static String SelectDaerahByNegeri(String idnegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getDaerahByNegeri(idnegeri);
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getKodDaerah() + " - " + f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}
	
	public static String SelectDaerahByNegeri_KPP(String idnegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction,String userId, boolean kpp_hq ) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getDaerahByNegeri_KPP(idnegeri,userId,kpp_hq);
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getKodDaerah() + " - " + f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// *** Kementerian
	public static String SelectKementerian(String selectName) throws Exception {
		return SelectKementerian(selectName, null, null);
	}

	public static String SelectKementerian(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getKementerian();
			Tblrujkementerian f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkementerian) v.get(i);
				if (f.getIdKementerian().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKementerian()
						+ ">" + f.getKodKementerian() + " - "
						+ f.getNamaKementerian() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// *** Agensi
	public static String SelectAgensi(String selectName) throws Exception {
		return SelectAgensi(selectName, null, null);
	}

	public static String SelectAgensi(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getAgensi();
			Tblrujagensi f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujagensi) v.get(i);
				if (f.getIdAgensi().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdAgensi() + ">"
						+ f.getKodAgensi() + " - " + f.getNamaAgensi()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// **** Sub urusan
	public static String SelectSuburusan(String selectName) throws Exception {
		return SelectSuburusan(selectName, null, null);
	}

	public static String SelectSuburusan(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getSubUrusan();
			Tblrujsuburusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSuburusan() + ">"
						+ f.getNamaSuburusan()
//						+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	/**
	 * Created by  : Mohamad Rosli
	 * Created on  : 28/07/2009
	 * Modified on : 28/07/2009
	 * @param idUrusan
	 * @param selectName
	 * @param selectedValue
	 * @param disability
	 * @return
	 * @throws Exception
	 */
	public static String SelectSuburusanByIdUrusan(String idUrusan,
		String selectName, Long selectedValue, String disability)
		throws Exception {
		return SelectSuburusanByIdUrusan(idUrusan, selectName, selectedValue,
				disability, null);
	}
	/**
	 * Created by	: Mohamad Rosli
	 * Created on  	: 28/07/2009
	 * Modified on 	: 28/07/2009
	 * @param idUrusan
	 * @param selectName
	 * @param selectedValue
	 * @param disability
	 * @param jsFunction
	 * @return
	 * @throws Exception
	 */
	public static String SelectSuburusanByIdUrusan(String idUrusan,
		String selectName, Long selectedValue, String disability,
		String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector<Tblrujsuburusan> v = DB.getSubUrusanByUrusan(idUrusan);
			Tblrujsuburusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSuburusan() + ">"
						+ f.getNamaSuburusan()
						//+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}
	// dat 17072010
	public static String SelectSubUrusanPelepasan(String idUrusan,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getSubUrusanPelepasan(idUrusan);
			Tblrujsuburusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSuburusan() + ">"
						+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectSubUrusanPelepasanOnlineKJP(String idUrusan,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getSubUrusanPelepasanOnlineKJP(idUrusan);
			Tblrujsuburusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSuburusan() + ">"
						+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectUrusan(String selectName) throws Exception {
		return SelectUrusan(selectName, null, null);
	}

	public static String SelectUrusan(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectUrusan(selectName, selectedValue, disability, null);
	}

	public static String SelectUrusan(String selectName, Long selectedValue,
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
			Vector v = DB.getUrusan();
			Tblrujurusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujurusan) v.get(i);
				if (f.getIdUrusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdUrusan() + ">"
						+ f.getNamaUrusan()
//						+ f.getKodUrusan() + " - " + f.getNamaUrusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectUrusanPHPPenyewaan(String selectName,
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
			Vector v = DB.getUrusanPHPPenyewaan();
			Tblrujurusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujurusan) v.get(i);
				if (f.getIdUrusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdUrusan() + ">"
						+ f.getNamaUrusan()
//						+ f.getKodUrusan() + " - " + f.getNamaUrusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectSeksyen(String selectName) throws Exception {
		return SelectSeksyen(selectName, null, null);
	}

	public static String SelectSeksyen(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getSeksyen();
			Tblrujseksyen f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujseksyen) v.get(i);
				if (f.getIdSeksyen().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSeksyen() + ">"
						+ f.getKodSeksyen() + " - " + f.getNamaSeksyen()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// public static String SelectUnit(String selectName, Long selectedValue)
	// throws Exception {
	// return SelectUnit(selectName, selectedValue, null, null);
	// }

	// public static String SelectUnit(String selectName, Long selectedValue,
	// String disability)
	// throws Exception {
	// return SelectUnit(selectName, selectedValue, disability, null);
	// }

	// public static String SelectUnit(String selectName, Long selectedValue,
	// String disability, String jsFunction) throws Exception {
	// StringBuffer sb = new StringBuffer("");
	// try {
	// sb.append("<select name='" + selectName + "'");
	// if (disability != null)
	// sb.append(disability);
	// if (jsFunction != null)
	// sb.append(jsFunction);
	// sb.append(" > ");
	// sb.append("<option value=>SILA PILIH</option>\n");
	// Vector v = DB.getUnit();
	// Tblrujunit f = null;
	// String s = "";
	// for (int i = 0; i < v.size(); i++) {
	// f = (Tblrujunit) v.get(i);
	// if (f.getIdUnit().equals(selectedValue)) {
	// s = "selected";
	// } else {
	// s = "";
	// }
	// sb.append("<option " + s + " value=" + f.getIdUnit() + ">"
	// + f.getNamaUnit()
	// + "</option>\n");
	// }
	// sb.append("</select>");
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// throw ex;
	// }
	//
	// return sb.toString();
	// }

	public static String SelectSeksyen(String selectName, Long selectedValue,
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
			Vector v = DB.getSeksyen();
			Tblrujseksyen f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujseksyen) v.get(i);
				if (f.getIdSeksyen().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSeksyen() + ">"
						+ f.getKodSeksyen() + " - " + f.getNamaSeksyen()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPengarahBySeksyen(String idSeksyen,
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
			Vector v = DB.getPengarahBySeksyen(idSeksyen);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("nama") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectSeksyenByUser(String idSeksyen,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getSeksyen(idSeksyen);
			Tblrujseksyen f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujseksyen) v.get(i);
				if (f.getIdSeksyen().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSeksyen() + ">"
						+ f.getKodSeksyen() + " - " + f.getNamaSeksyen()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectTarafKeselamatan(String selectName)
			throws Exception {
		return SelectTarafKeselamatan(selectName, null, null, null);
	}

	public static String SelectTarafKeselamatan(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectTarafKeselamatan(selectName, selectedValue, disability,
				null);
	}

	public static String SelectTarafKeselamatan(String selectName,
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
			Vector v = DB.getTarafKeselamatan();
			Tblrujpfdtarafkeselamatan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpfdtarafkeselamatan) v.get(i);
				if (f.getIdTarafKeselamatan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ f.getIdTarafKeselamatan() + ">"
						+ f.getKodTarafKeselamatan() + " - "
						+ f.getTarafKeselamatan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectStatusFail(String selectName) throws Exception {
		return SelectStatusFail(selectName, null, null);
	}

	public static String SelectStatusFail(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusFail();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectStatus(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatus();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	
	/*select status fail Gadaian*/
	
	public static String SelectStatusFailGadaian(String selectName) throws Exception {
		return SelectStatusFailGadaian(selectName, null, null);
	}

	public static String SelectStatusFailGadaian(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusFailGadaian();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	
/*select status fail Hapus*/
	
	public static String SelectStatusFailHapus(String selectName) throws Exception {
		return SelectStatusFailHapus(selectName, null, null);
	}

	public static String SelectStatusFailHapus(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusFailHapus();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	
/*select status fail Perletakhakan*/
	
	public static String SelectStatusFailPerletakhakan(String selectName) throws Exception {
		return SelectStatusFailPerletakhakan(selectName, null, null);
	}

	public static String SelectStatusFailPerletakhakan(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusFailPerletakhakan();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	
/*select status fail Penswastaan*/
	
	public static String SelectStatusFailPenswastaan(String selectName) throws Exception {
		return SelectStatusFailPenswastaan(selectName, null, null);
	}

	public static String SelectStatusFailPenswastaan(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusFailPenswastaan();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
/*select status fail PajakanKecil*/
	
	public static String SelectStatusFailPajakanKecil(String selectName) throws Exception {
		return SelectStatusFailPajakanKecil(selectName, null, null);
	}

	public static String SelectStatusFailPajakanKecil(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusFailPajakanKecil();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	
/*select status fail Pembelian*/
	
	public static String SelectStatusFailPembelian(String selectName) throws Exception {
		return SelectStatusFailPembelian(selectName, null, null);
	}

	public static String SelectStatusFailPembelian(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusFailPembelian();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	
/*select status fail Pajakan*/
	
	public static String SelectStatusFailPajakan(String selectName) throws Exception {
		return SelectStatusFailPajakan(selectName, null, null);
	}

	public static String SelectStatusFailPajakan(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusFailPajakan();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	
/*select status fail Perizapan*/
	
	public static String SelectStatusFailPerizapan(String selectName) throws Exception {
		return SelectStatusFailPerizapan(selectName, null, null);
	}

	public static String SelectStatusFailPerizapan(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusFailPerizapan();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	
/*select status fail Pemberimilikan*/
	
	public static String SelectStatusFailPemberimilikan(String selectName) throws Exception {
		return SelectStatusFailPemberimilikan(selectName, null, null);
	}

	public static String SelectStatusFailPemberimilikan(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusFailPemberimilikan();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	
/*select status FailKeseluruhan*/
	
	public static String SelectStatusFailKeseluruhan(String selectName) throws Exception {
		return SelectStatusFailKeseluruhan(selectName, null, null);
	}

	public static String SelectStatusFailKeseluruhan(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusFailKeseluruhan();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	

	public static String SelectLokasiFail(String selectName) throws Exception {
		return SelectLokasiFail(selectName, null, null);
	}

	public static String SelectLokasiFail(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getLokasiFail();
			Tblpfdrujlokasifail f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpfdrujlokasifail) v.get(i);
				if (f.getIdLokasifail().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdLokasifail()
						+ ">" + f.getLokasiFail() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectLokasiFailNegeri(String selectName,
			String idNegeri) throws Exception {
		return SelectLokasiFailNegeri(selectName, null, null, idNegeri);
	}

	public static String SelectLokasiFailNegeri(String selectName,
			Long selectedValue, String disability, String idNegeri)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getLokasiFailNegeri(idNegeri);
			Tblpfdrujlokasifail f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpfdrujlokasifail) v.get(i);
				if (f.getIdLokasifail().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdLokasifail()
						+ ">" + f.getLokasiFail() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectFaharasat(String selectName) throws Exception {
		return SelectFaharasat(selectName, null, null);
	}

	public static String SelectFaharasat(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getFaharasat();
			Tblpfdrujfaharasat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpfdrujfaharasat) v.get(i);
				if (f.getIdFaharasat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdFaharasat() + ">"
						+ f.getFaharasat() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectJenisDokumen(String selectName) throws Exception {
		return SelectJenisDokumen(selectName, null, null);
	}

	public static String SelectJenisDokumen(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getJenisDokumen();
			Tblrujjenisdokumen f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenisdokumen) v.get(i);
				if (f.getIdJenisdokumen().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenisdokumen()
						+ ">" + f.getKodJenisDokumen() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectJenisDokumenSKPP(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getJenisDokumenSKPP();
			Tblrujjenisdokumen f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenisdokumen) v.get(i);
				if (f.getIdJenisdokumen().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenisdokumen()
						+ ">" + f.getKodJenisDokumen() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPegawai(String selectName) throws Exception {
		return SelectPegawai(selectName, null, null);
	}

	public static String SelectPegawai(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=''>SILA PILIH PEGAWAI</option>\n");
			Vector v = DB.getPegawai();
			Tblrujpegawai f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpegawai) v.get(i);
				if (f.getIdPegawai().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPegawai() + ">"
						+ f.getNamaPegawai() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ELLY 04012010 - PPT

	public static String SelectPegawaiJKPTG(String selectName, String idnegeri)
			throws Exception {
		return SelectPegawaiJKPTG(selectName, idnegeri, null, null);
	}

	public static String SelectPegawaiJKPTG(String selectName, String idnegeri,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=''>SILA PILIH PEGAWAI</option>\n");
			Vector v = DB.getPegawaiJKPTG(idnegeri);
			Tblrujjawatan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjawatan) v.get(i);
				if (f.getIdJawatan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJawatan() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// *** Mukim
	public static String SelectMukim(String selectName) throws Exception {
		return SelectMukim(selectName, null, null);
	}

	public static String SelectMukim(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getMukim();
			Tblrujmukim f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujmukim) v.get(i);
				if (f.getIdMukim().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdMukim() + ">"
						+ f.getKodMukim() + " - " + f.getNamaMukim()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ** Select Mukim NO KOD
	public static String SelectMukimNoKod(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getMukim();
			Tblrujmukim f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujmukim) v.get(i);
				if (f.getIdMukim().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdMukim() + ">"
						+ f.getNamaMukim().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// *** Jenis HakMilik
	public static String SelectJenisHakmilik(String selectName)
			throws Exception {
		return SelectJenisHakmilik(selectName, null, null, null);
	}

	public static String SelectJenisHakmilik(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectJenisHakmilik(selectName, selectedValue, disability, null);
	}

	public static String SelectJenisHakmilik(String selectName,
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

			Vector v = DB.getJenisHakmilik();
			Tblrujjenishakmilik f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenishakmilik) v.get(i);
				if (f.getIdJenishakmilik().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenishakmilik()
						+ ">" + f.getKodJenisHakmilik().toUpperCase() + " - "
						+ f.getKeterangan().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// *** SelectStatusPelepasan - HILDA
	public static String SelectStatusPelepasan(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusPelepasan();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// *** SelectStatusPenyewaan - HILDA
	public static String SelectStatusPenyewaan(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusPenyewaan();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// *** SelectStatusPelepasan - DAT
	public static String SelectStatusTukarguna(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusTukarguna();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// *** SelectStatusPenawaran -
	public static String SelectStatusPenawaran(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusPenawaran();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// *** Jenis HakMilik Selangor
	public static String SelectJenisHakmilikSelangor(String selectName)
			throws Exception {
		return SelectJenisHakmilikSelangor(selectName, null, null, null);
	}

	public static String SelectJenisHakmilikSelangor(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectJenisHakmilikSelangor(selectName, selectedValue,
				disability, null);
	}

	public static String SelectJenisHakmilikSelangor(String selectName,
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

			Vector v = DB.getJenisHakmilikSelangor();
			Tblrujjenishakmilik f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenishakmilik) v.get(i);
				if (f.getIdJenishakmilik().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenishakmilik()
						+ ">" + f.getKodJenisHakmilik() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// **** Lot
	public static String SelectLot(String selectName) throws Exception {
		return SelectLot(selectName, null, null, null);
	}

	public static String SelectLot(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectLot(selectName, selectedValue, disability, null);
	}

	public static String SelectLot(String selectName, Long selectedValue,
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
			Vector v = DB.getLot();
			Tblrujlot f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujlot) v.get(i);
				if (f.getIdLot().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdLot() + ">"
						+ f.getKodLot() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ** Luas
	public static String SelectLuas(String selectName) throws Exception {
		return SelectLuas(selectName, null, null, null);
	}

	public static String SelectLuas(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectLuas(selectName, selectedValue, disability, null);
	}

	public static String SelectLuas(String selectName, Long selectedValue,
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
			Vector v = DB.getLuas();
			Tblrujluas f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujluas) v.get(i);
				if (f.getIdLuas().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdLuas() + ">"
						+ f.getKodLuas() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// **** Rizab
	public static String SelectRizab(String selectName) throws Exception {
		return SelectRizab(selectName, null, null);
	}

	public static String SelectRizab(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectRizab(selectName, selectedValue, disability, null);
	}

	public static String SelectRizab(String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=''>SILA PILIH </option>\n");
			Vector v = DB.getRizab();
			Tblrujjenisrizab f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenisrizab) v.get(i);
				if (f.getIdRizab().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdRizab() + ">"
						+ f.getKodRizab() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ********** Kategori

	public static String SelectKategori(String selectName) throws Exception {
		return SelectKategori(selectName, null, null, null);
	}

	public static String SelectKategori(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectKategori(selectName, selectedValue, disability, null);
	}

	public static String SelectKategori(String selectName, Long selectedValue,
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
			Vector v = DB.getKategori();
			Tblrujkategori f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkategori) v.get(i);
				if (f.getIdKategori().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKategori() + ">"
						+ f.getKodKategori() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectKategoriPemohonPHPPLP(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getKategoriPemohonPHPPLP();
			Tblrujkategoripemohon f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkategoripemohon) v.get(i);
				if (f.getIdKategoripemohon().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKategoripemohon()
						+ ">" + f.getKodKategoripemohon() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectLokasiMesyuarat(String selectName)
			throws Exception {
		return SelectLokasiMesyuarat(selectName, null, null, null);
	}

	public static String SelectLokasiMesyuarat(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectLokasiMesyuarat(selectName, selectedValue, disability,
				null);
	}

	public static String SelectLokasiMesyuarat(String selectName,
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
			Vector v = DB.getLokasiMesyuarat();
			Tblpfdrujlokasimesyuarat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpfdrujlokasimesyuarat) v.get(i);
				if (f.getIdLokasi().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdLokasi() + ">"
						+ f.getLokasi() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectLokasiMesyuaratNegeri(String selectName,
			Long selectedValue, String disability, String idNegeri)
			throws Exception {
		return SelectLokasiMesyuaratNegeri(selectName, selectedValue,
				disability, null, idNegeri);
	}

	public static String SelectLokasiMesyuaratNegeri(String selectName,
			Long selectedValue, String disability, String jsFunction,
			String idNegeri) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getLokasiMesyuaratNegeri(idNegeri);
			Tblpfdrujlokasimesyuarat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpfdrujlokasimesyuarat) v.get(i);
				if (f.getIdLokasi().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdLokasi() + ">"
						+ f.getLokasi() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectFail(String selectName) throws Exception {
		return SelectFail(selectName, null, null);
	}

	public static String SelectFail(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getFail();
			Tblpfdfail f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpfdfail) v.get(i);
				if (f.getIdFail().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdFail() + ">"
						+ f.getNoFail() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectAgenda(String selectName) throws Exception {
		return SelectAgenda(selectName, null, null);
	}

	public static String SelectAgenda(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getAgenda();
			Tblpdtagendamesyuarat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtagendamesyuarat) v.get(i);
				if (f.getIdAgendamesyuarat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdAgendamesyuarat()
						+ ">" + f.getAgendaMesyuarat() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectAgenda(String selectName, Long selectedValue,
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
			Vector v = DB.getAgenda();
			Tblpdtagendamesyuarat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtagendamesyuarat) v.get(i);
				if (f.getIdAgendamesyuarat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdAgendamesyuarat()
						+ ">" + f.getAgendaMesyuarat() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectAgenda(String idMesyuarat, String selectName,
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
			Vector v = DB.getAgenda(idMesyuarat);
			Tblpdtagendamesyuarat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtagendamesyuarat) v.get(i);
				if (f.getIdAgendamesyuarat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdAgendamesyuarat()
						+ ">" + f.getAgendaMesyuarat() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPerkaraMinit(String selectName) throws Exception {
		return SelectPerkaraMinit(selectName, null, null);
	}

	public static String SelectPerkaraMinit(String selectName,
			Long selectedValue, String disability) throws Exception {

		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPerkaraMinit();
			Tblpdtminitmesyuarat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtminitmesyuarat) v.get(i);
				if (f.getIdMinitmesyuarat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdMinitmesyuarat()
						+ ">" + f.getTajukMinit() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();

	}

	public static String SelectPerkaraMinit(String idAgenda, String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPerkaraMinit(idAgenda);
			Tblpdtminitmesyuarat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtminitmesyuarat) v.get(i);
				if (f.getIdMinitmesyuarat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdMinitmesyuarat()
						+ ">" + f.getTajukMinit() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectJenisNoPb(String selectName) throws Exception {
		return SelectJenisNoPb(selectName, null, null);
	}

	public static String SelectJenisNoPb(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectJenisNoPb(selectName, selectedValue, disability, null);
	}

	public static String SelectJenisNoPb(String selectName, Long selectedValue,
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
			Vector v = DB.getRujJenisNoPB();
			Tblrujjenisnopb f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenisnopb) v.get(i);
				if (f.getIdJenisnopb().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenisnopb() + ">"
						+ f.getKodJenisNopb() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectJenisPbNoSyarikat(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectJenisPbNoSyarikat(selectName, selectedValue, disability,
				null);
	}

	public static String SelectJenisPbNoSyarikat(String selectName,
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
			Vector v = DB.getRujJenisNoPBSyarikat();
			Tblrujjenisnopb f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenisnopb) v.get(i);
				if (f.getIdJenisnopb().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenisnopb() + ">"
						+ f.getKodJenisNopb() + " - "
						+ f.getKeterangan().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectJenisNoPbIndividu(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectJenisNoPbIndividu(selectName, selectedValue, disability,
				null);
	}

	public static String SelectJenisNoPbIndividu(String selectName,
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
			Vector v = DB.getRujJenisNoPBIndividu();
			Tblrujjenisnopb f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenisnopb) v.get(i);
				if (f.getIdJenisnopb().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenisnopb() + ">"
						+ f.getKodJenisNopb() + " - "
						+ f.getKeterangan().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	public static String SelectJenisNoPbIndividuLain(String selectName,
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
			Vector v = DB.getRujJenisNoPBIndividuLain();
			Tblrujjenisnopb f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenisnopb) v.get(i);
				if (f.getIdJenisnopb().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenisnopb() + ">"
						+ f.getKeterangan().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}


	public static String SelectJenisNoPbIndividuSyarikat(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getRujJenisNoPBIndividuSyarikat();
			Tblrujjenisnopb f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenisnopb) v.get(i);
				if (f.getIdJenisnopb().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenisnopb() + ">"
						+ f.getKodJenisNopb() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectUnitJKPTGByIdNegeri(String selectName,
			Long selectedValue, String disability, String jsFunction,
			String idNegeri) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getUnitJKPTGByIdNegeri(idNegeri);
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
						+ ">" + f.getNamaPejabat().toUpperCase() + " "
						+ f.getAlamat1().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectPejabat(String selectName, Long selectedValue,
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
			Vector v = DB.getPejabat();
			Tblrujpejabat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpejabat) v.get(i);
				if (f.getIdPejabat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPejabat() + ">"
						+ f.getNamaPejabat() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPejabatJKPTG(String selectName,
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
			Vector v = DB.getPejabatJKPTG();
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
						+ ">" + f.getNamaPejabat() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectUnitJKPTG(String selectName, Long selectedValue,
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
			Vector v = DB.getUnitJKPTG();
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
						+ ">" + f.getNamaPejabat().toUpperCase() + " "
						+ f.getAlamat1().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	/**
	 * Created by   : Mohamad Rosli 
	 * Created on   : 30/05/2009 
	 * Propose		: Digunakan apabila agensi dipilih mengikut
	 * 				 Kementerian
	 * 
	 * @param selectName
	 * @param idKementerian
	 * @param selectedValue
	 * @param disability
	 * @return
	 * @throws Exception
	 */
	public static String SelectAgensiByKementerian(String selectName,
		String idKementerian, Long selectedValue, String disability)
		throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getAgensiByKementerian(idKementerian);
			Tblrujagensi f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujagensi) v.get(i);
				if (f.getIdAgensi().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdAgensi() + ">"
						+ f.getKodAgensi() + " - " + f.getNamaAgensi()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}

	public static String SelectKementerian(String selectName, String jsFunction)
			throws Exception {
		return SelectKementerian(selectName, null, null, jsFunction);
	}

	public static String SelectKementerian(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select id='" + selectName + "'  name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector<Tblrujkementerian> v = DB.getKementerian();
			Tblrujkementerian f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkementerian) v.get(i);
				if (f.getIdKementerian().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKementerian()
						+ ">" + f.getKodKementerian() + " - "
						+ f.getNamaKementerian() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ** Bandar
	public static String SelectBandar(String selectName) throws Exception {
		return SelectBandar(selectName, null, null);
	}

	public static String SelectBandar(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getBandar();
			Tblrujbandar f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujbandar) v.get(i);
				if (f.getIdBandar().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdBandar() + ">"
						+ f.getKodBandar() + " - "
						+ f.getKeterangan().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}
	
	// ** Bandar

		public static String SelectBandar2(String selectName, Long selectedValue,
				String disability) throws Exception {
			StringBuffer sb = new StringBuffer("");
			try {
				sb.append("<select name='" + selectName + "' " + disability + "> ");
				sb.append("<option value=>SILA PILIH</option>\n");
				Vector v = DB.getBandar();
				Tblrujbandar f = null;
				String s = "";
				for (int i = 0; i < v.size(); i++) {
					f = (Tblrujbandar) v.get(i);
					if (f.getIdBandar().equals(selectedValue)) {
						s = "selected";
					} else {
						s = "";
					}
					sb.append("<option " + s + " value=" + f.getIdBandar() + ">"
							+ f.getKodBandar() + " - "
							+ f.getKeterangan().toUpperCase() + "</option>\n");
				}
				sb.append("</select>");
			} catch (Exception ex) {
				ex.printStackTrace();
				throw ex;
			}
			return sb.toString();

		}

	/** Bandar mengikut negeri*/
	public static String SelectBandarByNegeri(String idnegeri, String selectName)
			throws Exception {
		return SelectBandarByNegeri(idnegeri, selectName, null, null, null);
	}

	public static String SelectBandarByNegeri(String idnegeri,
			String selectName, String jsFunction) throws Exception {
		return SelectBandarByNegeri(idnegeri, selectName, null, null,
				jsFunction);
	}
	public static String SelectBandarByNegeri(String idnegeri,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectBandarByNegeri(idnegeri, selectName, selectedValue,
				disability, null);
	}	

	public static String SelectBandarByNegeri(String idnegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getBandarByNegeri(idnegeri);
			Tblrujbandar f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujbandar) v.get(i);
				if (f.getIdBandar().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdBandar() + ">"
						+ f.getKodBandar() + " - "
						+ f.getKeterangan().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	/**
	 * added by elly to cater status/penerangan no fail for UPT(PPT)
	 * 
	 * @return
	 * @throws Exception
	 *             //----------HTML.java
	 */

	public static String SelectStatusSPT(String selectName) throws Exception {
		return SelectStatusSPT(selectName, null, null);
	}

	public static String SelectStatusSPT(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusUPT();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	/**
	 * added by elly to cater status/penerangan no fail for PPTHakmilikSek4
	 * 
	 * @return
	 * @throws Exception
	 *             //----------HTML.java
	 */

	public static String SelectStatusHakmilik(String selectName)
			throws Exception {
		return SelectStatusHakmilik(selectName, null, null);
	}

	public static String SelectStatusHakmilik(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusHakmilik();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	/**
	 * added by elly to cater status/penerangan no fail for LaporanAwalTanah
	 */

	public static String SelectStatusLaporanAwalTanah(String selectName)
			throws Exception {
		return SelectStatusLaporanAwalTanah(selectName, null, null);
	}

	public static String SelectStatusLaporanAwalTanah(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusLaporanAwalTanah();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// **added by elly for urusan permohonan

	// **** Sub urusan utk UPT

	public static String SelectSuburusanUPT(String selectName) throws Exception {
		return SelectSuburusanUPT(selectName, null, null, null);
	}

	public static String SelectSuburusanUPT(String selectName, String jsFunction)
			throws Exception {
		return SelectSuburusanUPT(selectName, null, null, jsFunction);
	}

	public static String SelectSuburusanUPT(String selectName,
			Long selectedValue) throws Exception {
		return SelectSuburusanUPT(selectName, selectedValue, null, null);
	}

	public static String SelectSuburusanUPT(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectSuburusanUPT(selectName, selectedValue, disability, null);
	}

	public static String SelectSuburusanUPT(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getSubUrusanUPT();
			Tblrujsuburusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSuburusan() + ">"
						+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
						+ "</option>\n");
			}
			sb.append("</select");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// **added by elly for warganegara dlm UPT
	// ****Warganegara utk app hakmilik

	public static String SelectWarganegara(String selectName) throws Exception {
		return SelectWarganegara(selectName, null, null, null);
	}

	public static String SelectWarganegara(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectWarganegara(selectName, selectedValue, disability, null);
	}

	public static String SelectWarganegara(String selectName,
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
			Vector v = DB.getWarganegara();

			Tblrujwarganegara f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujwarganegara) v.get(i);
				if (f.getIdWarganegara().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdWarganegara()
						+ ">" + f.getKodWarga() + " - "
						+ f.getKeterangan().toUpperCase() + "</option>\n");

			}
			sb.append("</select");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// **added by elly for bangsa dlm UPT
	// ****bangsa utk app hakmilik

	public static String SelectBangsa(String selectName) throws Exception {
		return SelectBangsa(selectName, null, null, null);
	}

	public static String SelectBangsa(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectBangsa(selectName, selectedValue, disability, null);
	}

	public static String SelectBangsa(String selectName, Long selectedValue,
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
			Vector v = DB.getBangsa();

			Tblrujbangsa f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujbangsa) v.get(i);
				if (f.getIdBangsa().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdBangsa() + ">"
						+ f.getKodBangsa() + " - "
						+ f.getKeterangan().toUpperCase() + "</option>\n");

			}
			sb.append("</select");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPHPRujDokumen(String selectName,
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
			Vector v = DB.getPHPRujDokumen();
			Tblphprujdokumen f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblphprujdokumen) v.get(i);
				if (f.getIdDokumen().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDokumen() + ">"
						+ f.getKodDokumen() + " - "
						+ f.getKeterangan().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// DOKUMEN UNTUK NOTIS PERINGATAN PENGUATKUASAAN
	public static String SelectPHPRujDokumenNotisPeringatan(String selectName,
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
			Vector v = DB.getPHPRujDokumenNotisPeringatan();
			Tblphprujdokumen f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblphprujdokumen) v.get(i);
				if (f.getIdDokumen().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDokumen() + ">"
						+ f.getKodDokumen() + " - "
						+ f.getKeterangan().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	// DOKUMEN UNTUK NOTIS PERINGATAN PENGUATKUASAAN
		public static String SelectPHPRujDokumenPanggilMesyuarat(String selectName,
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
				Vector v = DB.getPHPRujDokumenPanggilMesyuarat();
				Tblphprujdokumen f = null;
				String s = "";
				for (int i = 0; i < v.size(); i++) {
					f = (Tblphprujdokumen) v.get(i);
					if (f.getIdDokumen().equals(selectedValue)) {
						s = "selected";
					} else {
						s = "";
					}
					sb.append("<option " + s + " value=" + f.getIdDokumen() + ">"
							+ f.getKodDokumen() + " - "
							+ f.getKeterangan().toUpperCase() + "</option>\n");
				}
				sb.append("</select>");
			} catch (Exception ex) {
				ex.printStackTrace();
				throw ex;
			}

			return sb.toString();
		}


	// **added by elly for no PB in Hakmilik UPT
	public static String SelectJenisPb(String selectName) throws Exception {
		return SelectJenisPb(selectName, null, null);
	}

	public static String SelectJenisPb(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");

			Vector v = DB.getRujKodJenisPB();
			Tblrujjenispb f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenispb) v.get(i);
				if (f.getIdJenispb().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenispb() + ">"
						+ f.getKodJenisPb() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// added by hidayah 04/03/2010
	/*
	 * public static String SelectJenisPb2(String selectName) throws Exception {
	 * return SelectJenisPb2(selectName, null, null); } public static String
	 * SelectJenisPb2(String selectName, Long selectedValue, String disability)
	 * throws Exception { StringBuffer sb = new StringBuffer(""); try {
	 * sb.append("<select name='" + selectName + "' " + disability + "> ");
	 * sb.append("<option value=>SILA PILIH</option>\n");
	 * 
	 * Vector v = DB.getRujKodJenisPB2(); Tblrujjenispb f = null; String s = "";
	 * for (int i = 0; i < v.size(); i++) { f = (Tblrujjenispb) v.get(i); if
	 * (f.getIdJenispb().equals(selectedValue)) { s = "selected"; } else { s =
	 * ""; } sb.append("<option " + s + " value=" + f.getIdJenispb() + ">" +
	 * f.getKodJenisPb() + " - " + f.getKeterangan() + "</option>\n"); }
	 * sb.append("</select>"); } catch (Exception ex) { ex.printStackTrace();
	 * throw ex; }
	 * 
	 * return sb.toString(); }
	 */

	public static String SelectFailByIdSeksyen(String idSeksyen,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getFail(idSeksyen);
			Tblpfdfail f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpfdfail) v.get(i);
				if (f.getIdFail().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdFail() + ">"
						+ f.getNoFail() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectFailByUserId(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");

		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getFail();
			Tblpfdfail f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpfdfail) v.get(i);
				if (f.getIdFail().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdFail() + ">"
						+ f.getNoFail() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectKategoriPekeliling(String user_role,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getKategoriPekeliling(user_role);
			Tblpdtrujdokumenpekeliling f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtrujdokumenpekeliling) v.get(i);
				if (f.getIdDokumenpekeliling().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ f.getIdDokumenpekeliling() + ">"
						+ f.getKodJenisDokumenpekeliling() + " - "
						+ f.getJenisDokumenPekeliling() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectKategoriPekeliling(String selectName,
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
			Vector v = DB.getKategoriPekeliling();
			Tblpdtrujdokumenpekeliling f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtrujdokumenpekeliling) v.get(i);
				if (f.getIdDokumenpekeliling().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ f.getIdDokumenpekeliling() + ">"
						+ f.getKodJenisDokumenpekeliling() + " - "
						+ f.getJenisDokumenPekeliling() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectPerkaraPekeliling(String selectName,
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
			Vector v = DB.getPerkaraPekeliling();
			Tblpdtrujperkarapekeliling f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtrujperkarapekeliling) v.get(i);
				if (f.getIdPerkarapekeliling().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ f.getIdPerkarapekeliling() + ">"
						+ f.getKodPerkaraPekeliling() + " - "
						+ f.getPerkaraPekeliling() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// ** Mukim by Daerah

	public static String SelectMukimByDaerah(String iddaerah, String selectName)
			throws Exception {
		return SelectMukimByDaerah(iddaerah, selectName, null, null, null);
	}

	public static String SelectMukimByDaerah(String iddaerah,
			String selectName, String jsFunction) throws Exception {
		return SelectMukimByDaerah(iddaerah, selectName, null, null, jsFunction);
	}

	public static String SelectMukimByDaerah(String iddaerah,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectMukimByDaerah(iddaerah, selectName, selectedValue,
				disability, null);
	}

	public static String SelectMukimByDaerah(String iddaerah,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getMukimByDaerah(iddaerah);
			Tblrujmukim f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujmukim) v.get(i);
				if (f.getIdMukim().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdMukim() + ">"
						+ f.getKodMukim() + " - " + f.getNamaMukim()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}// close select mukim by daerah

	// ** MUKIM BY NEGERI
	// ** ALTER BY ELLY 151209
	public static String SelectMukimByNegeri(String idnegeri, String selectName)
			throws Exception {
		return SelectMukimByNegeri(idnegeri, selectName, null, null, null);
	}

	public static String SelectMukimByNegeri(String idnegeri,
			String selectName, String jsFunction) throws Exception {
		return SelectMukimByNegeri(idnegeri, selectName, null, null, jsFunction);
	}

	public static String SelectMukimByNegeri(String idnegeri,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectMukimByNegeri(idnegeri, selectName, selectedValue,
				disability, null);
	}

	public static String SelectMukimByNegeri(String idnegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getMukimByNegeri(idnegeri);
			Tblrujmukim f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujmukim) v.get(i);
				if (f.getIdMukim().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdMukim() + ">"
						+ f.getKodMukim() + " - " + f.getNamaMukim()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}// close select mukim by daerah

	public static String SelectJantina(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectJantina(selectName, selectedValue, disability, null);
	}

	public static String SelectJantina(String selectName, Long selectedValue,
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
				sb.append("<option selected value=1> L - LELAKI</option>\n");
			} else {
				sb.append("<option value=1> L - LELAKI</option>\n");
			}
			if (selectedValue.intValue() == 2) {
				sb.append("<option selected value=2> P - PEREMPUAN</option>\n");
			} else {
				sb.append("<option value=2> P - PEREMPUAN</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectKategoriPemohonIndividuAndSyarikat(
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectKategoriPemohonIndividuAndSyarikat(selectName,
				selectedValue, disability, null);
	}

	public static String SelectKategoriPemohonIndividuAndSyarikat(
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getKategoriPemohonIndividuAndSyarikat();
			Tblrujkategoripemohon f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkategoripemohon) v.get(i);
				if (f.getIdKategoripemohon().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKategoripemohon()
						+ ">" + f.getKodKategoripemohon() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	public static String SelectKategoriPemohonOrganisasiAndSyarikat(
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getKategoriPemohonOrganisasiAndSyarikat();
			Tblrujkategoripemohon f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkategoripemohon) v.get(i);
				if (f.getIdKategoripemohon().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKategoripemohon()
						+ ">" + f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	public static String SelectKategoriPemohonIndividu(
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getKategoriIndividu();
			Tblrujkategoripemohon f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkategoripemohon) v.get(i);
				if (f.getIdKategoripemohon().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKategoripemohon()
						+ ">" + f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	public static String SelectKategoriIndividuBukanIndividu(
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getKategoriIndividuBukanIndividu();
			Tblrujkategoripemohon f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkategoripemohon) v.get(i);
				if (f.getIdKategoripemohon().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKategoripemohon()
						+ ">" + f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// create by nurul fazilah
	// select jenis kategori pemohon individu,syarikat, ptg,
	// ptd,koperasi/pbt,jkptg (semua)
	public static String SelectKategoriPemohonPenguatkuasa(String selectName,
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
			Vector v = DB.getKategoriPemohonPenguatkuasa();
			Tblrujkategoripemohon f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkategoripemohon) v.get(i);
				if (f.getIdKategoripemohon().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKategoripemohon()
						+ ">" + f.getKodKategoripemohon() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectKategoriPenawaran(String selectName,
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
			Vector v = DB.getKategoriPenawaran();
			Tblrujkategoripemohon f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkategoripemohon) v.get(i);
				if (f.getIdKategoripemohon().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKategoripemohon()
						+ ">" + f.getKodKategoripemohon() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPHPRujJenisTujuan(String selectName,
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
			Vector v = DB.getPHPRujJenisTujuan();
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

	public static String SelectPHPRujTujuanKaitan(String selectName,
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
			Vector v = DB.getPHPRujTujuanKaitan();
			Tblphprujtujuankaitan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblphprujtujuankaitan) v.get(i);
				if (f.getIdTujuankaitan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdTujuankaitan()
						+ ">" + f.getKodTujuankaitan() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String Selectjawatan(String selectName) throws Exception {
		return SelectJawatan(selectName, null, null, null);
	}

	public static String SelectJawatan(String selectName, String jsFunction)
			throws Exception {
		return SelectJawatan(selectName, null, null, jsFunction);
	}

	public static String SelectJawatan(String selectName, Long selectedValue)
			throws Exception {
		return SelectJawatan(selectName, selectedValue, null, null);
	}

	public static String SelectJawatan(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectJawatan(selectName, selectedValue, disability, null);
	}

	public static String SelectJawatan(String selectName, Long selectedValue,
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
			Vector v = DB.getJawatan();
			Tblrujjawatan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjawatan) v.get(i);
				if (f.getIdJawatan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				String kodJawatan = null;
				if(f.getKodJawatan() != null){
					kodJawatan = f.getKodJawatan();
				} else {
					kodJawatan = "00";
				}
				sb.append("<option " + s + " value=" + f.getIdJawatan() + ">"
//						+ kodJawatan + " - "
						+ f.getKeterangan().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectJenisAduan(String selectName,
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
			Vector v = DB.getJenisAduan();
			Tblrujjenisaduan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenisaduan) v.get(i);
				if (f.getIdJenisaduan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenisaduan()
						+ ">" + f.getKodJenisAduan() + " - "
						+ f.getJenisAduan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ** Jenis Tanah by Elly
	public static String SelectJenisTanah(String selectName) throws Exception {
		return SelectJenisTanah(selectName, null, null, null);
	}

	public static String SelectJenisTanah(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectJenisTanah(selectName, selectedValue, disability, null);
	}

	public static String SelectJenisTanah(String selectName,
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
			Vector v = DB.getJenisTanah();
			Tblrujjenistanah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenistanah) v.get(i);
				if (f.getIdJenistanah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenistanah()
						+ ">" + f.getKodJenistanah() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	public static String SelectJenisLesen(String selectName,
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
			if (selectedValue.intValue() == 2) {
				sb.append("<option selected value = 2> BORANG 2</option>\n");
			} else {
				sb.append("<option value = 2> BORANG 2(LESEN PASIR)</option>\n");
			}
			if (selectedValue.intValue() == 3) {
				sb.append("<option selected value = 3> BORANG 3</option>\n");
			} else {
				sb.append("<option value = 3> BORANG 3(LESEN MENJELAJAH/MENCARIGALI/MENGGEREK SELAIN PASIR)</option>\n");
			}
			if (selectedValue.intValue() == 4) {
				sb.append("<option selected value = 4> BORANG 4</option>\n");
			} else {
				sb.append("<option value = 4> BORANG 4(LESEN GALIAN SELAIN PASIR)</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectFlagYaTidak(String selectName,
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
			if (selectedValue.intValue() == 1) {
				sb.append("<option selected value=1> Y - YA</option>\n");
			} else {
				sb.append("<option value=1> Y - YA</option>\n");
			}
			if (selectedValue.intValue() == 2) {
				sb.append("<option selected value=2> T - TIDAK</option>\n");
			} else {
				sb.append("<option value=2> T - TIDAK</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	public static String SelectJenisPermohonanAPB(String selectName,
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
			if (selectedValue.intValue() == 1) {
				sb.append("<option selected value = 1> PERMOHONAN BARU</option>\n");
			} else {
				sb.append("<option value = 1> PERMOHONAN BARU</option>\n");
			}
			if (selectedValue.intValue() == 2) {
				sb.append("<option selected value = 2> PEMBAHARUAN LESEN</option>\n");
			} else {
				sb.append("<option value = 2> PEMBAHARUAN LESEN</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPHPJenisPerjanjian(String selectName,
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
			if (selectedValue.intValue() == 1) {
				sb.append("<option selected value=1>MEMORANDUM PERSEFAHAMAN</option>\n");
			} else {
				sb.append("<option value=1>MEMORANDUM PERSEFAHAMAN</option>\n");
			}
			if (selectedValue.intValue() == 2) {
				sb.append("<option selected value=2>SURAT MINAT PEMBELIAN</option>\n");
			} else {
				sb.append("<option value=2>SURAT MINAT PEMBELIAN</option>\n");
			}
			if (selectedValue.intValue() == 3) {
				sb.append("<option selected value=3>SURAT PERJANJIAN</option>\n");
			} else {
				sb.append("<option value=3>SURAT PERJANJIAN</option>\n");
			}
			if (selectedValue.intValue() == 4) {
				sb.append("<option selected value=4>SURAT TAWARAN PEMBELIAN</option>\n");
			} else {
				sb.append("<option value=4>SURAT TAWARAN PEMBELIAN</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectKategoriPengaduAPB(String selectName,
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
			Vector v = DB.getKategoriPengaduABP();
			Tblrujkategoripemohon f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkategoripemohon) v.get(i);
				if (f.getIdKategoripemohon().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKategoripemohon()
						+ ">" + f.getKodKategoripemohon() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPHPStatusJabatanTeknikal(String selectName,
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
			if (selectedValue.intValue() == 1) {
				sb.append("<option selected value=1>BELUM DIHANTAR</option>\n");
				sb.append("<option value=2>TELAH DIHANTAR</option>\n");
				sb.append("<option value=3>ULANGAN PERTAMA</option>\n");
				sb.append("<option value=4>ULANGAN KEDUA</option>\n");
				sb.append("<option value=5>TELAH DITERIMA</option>\n");
			} else if (selectedValue.intValue() == 2) {
				sb.append("<option value=1>BELUM DIHANTAR</option>\n");
				sb.append("<option selected value=2>TELAH DIHANTAR</option>\n");
				sb.append("<option value=3>ULANGAN PERTAMA</option>\n");
				sb.append("<option value=4>ULANGAN KEDUA</option>\n");
				sb.append("<option value=5>TELAH DITERIMA</option>\n");
			} else if (selectedValue.intValue() == 3) {
				sb.append("<option value=1>BELUM DIHANTAR</option>\n");
				sb.append("<option value=2>TELAH DIHANTAR</option>\n");
				sb.append("<option selected value=3>ULANGAN PERTAMA</option>\n");
				sb.append("<option value=4>ULANGAN KEDUA</option>\n");
				sb.append("<option value=5>TELAH DITERIMA</option>\n");
			} else if (selectedValue.intValue() == 4) {
				sb.append("<option value=1>BELUM DIHANTAR</option>\n");
				sb.append("<option value=2>TELAH DIHANTAR</option>\n");
				sb.append("<option value=3>ULANGAN PERTAMA</option>\n");
				sb.append("<option selected value=4>ULANGAN KEDUA</option>\n");
				sb.append("<option value=5>TELAH DITERIMA</option>\n");
			} else if (selectedValue.intValue() == 5) {
				sb.append("<option value=1>BELUM DIHANTAR</option>\n");
				sb.append("<option value=2>TELAH DIHANTAR</option>\n");
				sb.append("<option value=3>ULANGAN PERTAMA</option>\n");
				sb.append("<option value=4>ULANGAN KEDUA</option>\n");
				sb.append("<option selected value=5>TELAH DITERIMA</option>\n");
			} else {
				sb.append("<option value=1>BELUM DIHANTAR</option>\n");
				sb.append("<option value=2>TELAH DIHANTAR</option>\n");
				sb.append("<option value=3>ULANGAN PERTAMA</option>\n");
				sb.append("<option value=4>ULANGAN KEDUA</option>\n");
				sb.append("<option value=5>TELAH DITERIMA</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectBulanTahun(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if(selectedValue!=null){
				if (selectedValue.intValue() == 1) {
					sb.append("<option selected value=1> B - BULAN</option>\n");
				} else {
					sb.append("<option value=1> B - BULAN</option>\n");
				}
				if (selectedValue.intValue() == 2) {
					sb.append("<option selected value=2> T - TAHUN</option>\n");
				} else {
					sb.append("<option value=2> T - TAHUN</option>\n");
				}
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectAgensi(String selectName, String jsFunction)
			throws Exception {
		return SelectAgensi(selectName, null, null, jsFunction);
	}

	public static String SelectAgensi(String selectName, Long selectedValue,
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
			Vector v = DB.getAgensi();
			Tblrujagensi f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujagensi) v.get(i);
				if (f.getIdAgensi().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdAgensi() + ">"
						+ f.getKodAgensi() + " - " + f.getNamaAgensi()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectAgensiByKementerian(String selectName,
			String idKementerian, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getAgensiByKementerian(idKementerian);
			Tblrujagensi f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujagensi) v.get(i);
				if (f.getIdAgensi().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdAgensi() + ">"
						+ f.getKodAgensi() + " - " + f.getNamaAgensi()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	/**
	 * added by elly to cater status for Maklumat Jabatan Teknikal
	 */

	public static String SelectStatusJabatanTeknikal(String selectName)
			throws Exception {
		return SelectStatusJabatanTeknikal(selectName, null, null);
	}

	public static String SelectStatusJabatanTeknikal(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			// Vector v = DB.getStatusLaporanAwalTanah();
			Vector v = DB.getStatusJabatanTeknikal();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectYaTidak(String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (selectedValue.intValue() == 1) {
				sb.append("<option selected value=1> Y - YA</option>\n");
			} else {
				sb.append("<option value=1> Y - YA</option>\n");
			}
			if (selectedValue.intValue() == 2) {
				sb.append("<option selected value=2> T - TIDAK</option>\n");
			} else {
				sb.append("<option value=2> T - TIDAK</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectJam(String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'" + "style='"
					+ "width:40px;" + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(" > ");
			if (selectedValue.intValue() == 0) {
				sb.append("<option selected value=0> 00 </option>\n");
			} else {
				sb.append("<option value=0> 00 </option>\n");
			}
			if (selectedValue.intValue() == 1) {
				sb.append("<option selected value=1> 01 </option>\n");
			} else {
				sb.append("<option value=1> 01 </option>\n");
			}
			if (selectedValue.intValue() == 2) {
				sb.append("<option selected value=2> 02 </option>\n");
			} else {
				sb.append("<option value=2> 02 </option>\n");
			}
			if (selectedValue.intValue() == 3) {
				sb.append("<option selected value=3> 03 </option>\n");
			} else {
				sb.append("<option value=3> 03 </option>\n");
			}
			if (selectedValue.intValue() == 4) {
				sb.append("<option selected value=4> 04 </option>\n");
			} else {
				sb.append("<option value=4> 04 </option>\n");
			}
			if (selectedValue.intValue() == 5) {
				sb.append("<option selected value=5> 05 </option>\n");
			} else {
				sb.append("<option value=5> 05 </option>\n");
			}
			if (selectedValue.intValue() == 6) {
				sb.append("<option selected value=6> 06 </option>\n");
			} else {
				sb.append("<option value=6> 06 </option>\n");
			}
			if (selectedValue.intValue() == 7) {
				sb.append("<option selected value=7> 07 </option>\n");
			} else {
				sb.append("<option value=7> 07 </option>\n");
			}
			if (selectedValue.intValue() == 8) {
				sb.append("<option selected value=8> 08 </option>\n");
			} else {
				sb.append("<option value=8> 08 </option>\n");
			}
			if (selectedValue.intValue() == 9) {
				sb.append("<option selected value=9> 09 </option>\n");
			} else {
				sb.append("<option value=9> 09 </option>\n");
			}
			if (selectedValue.intValue() == 10) {
				sb.append("<option selected value=10> 10 </option>\n");
			} else {
				sb.append("<option value=10> 10 </option>\n");
			}
			if (selectedValue.intValue() == 11) {
				sb.append("<option selected value=11> 11 </option>\n");
			} else {
				sb.append("<option value=11> 11 </option>\n");
			}
			if (selectedValue.intValue() == 12) {
				sb.append("<option selected value=12> 12 </option>\n");
			} else {
				sb.append("<option value=12> 12 </option>\n");
			}
			if (selectedValue.intValue() == 13) {
				sb.append("<option selected value=13> 13 </option>\n");
			} else {
				sb.append("<option value=13> 13 </option>\n");
			}
			if (selectedValue.intValue() == 14) {
				sb.append("<option selected value=14> 14 </option>\n");
			} else {
				sb.append("<option value=14> 14 </option>\n");
			}
			if (selectedValue.intValue() == 15) {
				sb.append("<option selected value=15> 15 </option>\n");
			} else {
				sb.append("<option value=15> 15 </option>\n");
			}
			if (selectedValue.intValue() == 16) {
				sb.append("<option selected value=16> 16 </option>\n");
			} else {
				sb.append("<option value=16> 16 </option>\n");
			}
			if (selectedValue.intValue() == 17) {
				sb.append("<option selected value=17> 17 </option>\n");
			} else {
				sb.append("<option value=17> 17 </option>\n");
			}
			if (selectedValue.intValue() == 18) {
				sb.append("<option selected value=18> 18 </option>\n");
			} else {
				sb.append("<option value=18> 18 </option>\n");
			}
			if (selectedValue.intValue() == 19) {
				sb.append("<option selected value=19> 19 </option>\n");
			} else {
				sb.append("<option value=19> 19 </option>\n");
			}
			if (selectedValue.intValue() == 20) {
				sb.append("<option selected value=20> 20 </option>\n");
			} else {
				sb.append("<option value=20> 20 </option>\n");
			}
			if (selectedValue.intValue() == 21) {
				sb.append("<option selected value=21> 21 </option>\n");
			} else {
				sb.append("<option value=21> 21 </option>\n");
			}
			if (selectedValue.intValue() == 22) {
				sb.append("<option selected value=22> 22 </option>\n");
			} else {
				sb.append("<option value=22> 22 </option>\n");
			}
			if (selectedValue.intValue() == 23) {
				sb.append("<option selected value=23> 23 </option>\n");
			} else {
				sb.append("<option value=23> 23 </option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectMinit(String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'" + "style='"
					+ "width:40px;" + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(" > ");
			// sb.append("<option value=>SILA PILIH</option>\n");
			if (selectedValue.intValue() == 0) {
				sb.append("<option selected value=0> 00 </option>\n");
			} else {
				sb.append("<option value=0> 00 </option>\n");
			}
			if (selectedValue.intValue() == 1) {
				sb.append("<option selected value=1> 05 </option>\n");
			} else {
				sb.append("<option value=1> 05 </option>\n");
			}
			if (selectedValue.intValue() == 2) {
				sb.append("<option selected value=2> 10 </option>\n");
			} else {
				sb.append("<option value=2> 10 </option>\n");
			}
			if (selectedValue.intValue() == 3) {
				sb.append("<option selected value=3> 15 </option>\n");
			} else {
				sb.append("<option value=3> 15 </option>\n");
			}
			if (selectedValue.intValue() == 4) {
				sb.append("<option selected value=4> 20 </option>\n");
			} else {
				sb.append("<option value=4> 20 </option>\n");
			}
			if (selectedValue.intValue() == 5) {
				sb.append("<option selected value=5> 25 </option>\n");
			} else {
				sb.append("<option value=5> 25 </option>\n");
			}
			if (selectedValue.intValue() == 6) {
				sb.append("<option selected value=6> 30 </option>\n");
			} else {
				sb.append("<option value=6> 30 </option>\n");
			}
			if (selectedValue.intValue() == 7) {
				sb.append("<option selected value=7> 35 </option>\n");
			} else {
				sb.append("<option value=7> 35 </option>\n");
			}
			if (selectedValue.intValue() == 8) {
				sb.append("<option selected value=8> 40 </option>\n");
			} else {
				sb.append("<option value=8> 40 </option>\n");
			}
			if (selectedValue.intValue() == 9) {
				sb.append("<option selected value=9> 45 </option>\n");
			} else {
				sb.append("<option value=9> 45 </option>\n");
			}
			if (selectedValue.intValue() == 10) {
				sb.append("<option selected value=10> 50 </option>\n");
			} else {
				sb.append("<option value=10> 50 </option>\n");
			}
			if (selectedValue.intValue() == 11) {
				sb.append("<option selected value=11> 55 </option>\n");
			} else {
				sb.append("<option value=11> 55 </option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectUlasanRingkasan(String selectName,
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
			Vector v = DB.getUlasanRingkasan();
			Tblrujulasanringkasan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujulasanringkasan) v.get(i);
				if (f.getIdUlasanringkasan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdUlasanringkasan()
						+ ">" + f.getKodUlasanringkasan() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPenggalAkta(int idAkta, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getPenggal(idAkta);
			Tblpdtaktapenggal f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtaktapenggal) v.get(i);
				if (f.getIdAktapenggal().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + "value=" + f.getIdAktapenggal()
						+ ">" + f.getNoPenggal() + " - " + f.getTajukPenggal()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectPenggalEnakmen(int idEnakmen, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getPenggalEnakmen(idEnakmen);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_enakmenpenggal").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ h.get("id_enakmenpenggal") + ">"
						+ h.get("tajuk_penggal") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectBahagianAkta(int idAkta, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getBahagian(idAkta);
			Tblpdtaktabahagian f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtaktabahagian) v.get(i);
				if (f.getIdAktabahagian().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdAktabahagian()
						+ ">" + f.getNoBahagian() + " - "
						+ f.getTajukBahagian() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectBahagianEnakmen(int idEnakmen,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getBahagianEnakmen(idEnakmen);
			Tblpdtenakmenbahagian f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtenakmenbahagian) v.get(i);
				if (f.getIdEnakmenbahagian().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdEnakmenbahagian()
						+ ">" + f.getNoBahagian() + " - "
						+ f.getTajukBahagian() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectBabEnakmen(int idEnakmen, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getBabEnakmen(idEnakmen);
			Tblpdtenakmenbab f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtenakmenbab) v.get(i);
				if (f.getIdEnakmenbab().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdEnakmenbab()
						+ ">" + f.getNoBab() + " - " + f.getTajukBab()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectSeksyenEnakmen(int idEnakmen, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getSeksyenEnakmen(idEnakmen);
			Tblpdtenakmenseksyen f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtenakmenseksyen) v.get(i);
				if (f.getIdEnakmenseksyen().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdEnakmenseksyen()
						+ ">" + f.getSeksyen() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectSubSeksyenEnakmen(int idEnakmen,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getSubSeksyenEnakmen(idEnakmen);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_enakmensubseksyen").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ h.get("id_enakmensubseksyen") + ">"
						+ h.get("sub_seksyen") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectParaEnakmen(int idEnakmen, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getParaEnakmen(idEnakmen);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_enakmenpara").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id_enakmenpara")
						+ ">" + h.get("para") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectJadualEnakmen(int idEnakmen, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getJadualEnakmen(idEnakmen);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_jadual").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id_jadual") + ">"
						+ h.get("no_jadual") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectParaJadualParaEnakmen(int idEnakmen,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getParaJadualParaEnakmen(idEnakmen);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_jadualpara").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id_jadualpara")
						+ ">" + h.get("para") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectSubParaJadualParaEnakmen(int idEnakmen,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getSubParaJadualParaEnakmen(idEnakmen);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_jadualsubpara").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ h.get("id_jadualsubpara") + ">" + h.get("sub_para")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectBabAkta(int idAkta, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getBab(idAkta);
			Tblpdtaktabab f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtaktabab) v.get(i);
				if (f.getIdAktabab().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdAktabab() + ">"
						+ f.getNoBab() + " - " + f.getTajukBab()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectSeksyenAkta(int idAkta, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getSeksyenAkta(idAkta);
			Tblpdtaktaseksyen f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtaktaseksyen) v.get(i);
				if (f.getIdAktaseksyen().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdAktaseksyen()
						+ ">" + f.getSeksyen() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectSubSeksyenAkta(int idAkta, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getSubSeksyenAkta(idAkta);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_aktasubseksyen").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ h.get("id_aktasubseksyen") + ">"
						+ h.get("sub_seksyen") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectParaAkta(int idAkta, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getPara(idAkta);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_aktapara").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id_aktapara")
						+ ">" + h.get("para") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectJadualAkta(int idAkta, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getJadualAkta(idAkta);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_jadual").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id_jadual") + ">"
						+ h.get("no_jadual") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectParaJadualParaAkta(int idAkta,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getJadualPara(idAkta);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_jadualpara").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id_jadualpara")
						+ ">" + h.get("para") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectSubParaJadualParaAkta(int idAkta,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector v = DB.getJadualSubPara(idAkta);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_jadualsubpara").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ h.get("id_jadualsubpara") + ">" + h.get("sub_para")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectStatusAduan(String selectName) throws Exception {
		return SelectStatusAduan(selectName, null, null);
	}

	public static String SelectStatusAduan(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusAduan();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectJenisBayaran(String selectName) throws Exception {
		return SelectJenisBayaran(selectName, null, null);
	}

	public static String SelectJenisBayaran(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getJenisBayaran();
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
						+ ">" + f.getKodJenisBayar() + "-" + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectBank(String selectName) throws Exception {
		return SelectBank(selectName, null, null);
	}

	public static String SelectBank(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getBank();
			Tblrujbank f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujbank) v.get(i);
				if (f.getIdBank().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdBank() + ">"
						+ f.getKodBank().toUpperCase() + "-"
						+ f.getNamaBank().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPPKJenisKp(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (selectedValue.intValue() == 1) {
				sb.append("<option selected value=1>No. KP Baru</option>\n");
			} else {
				sb.append("<option value=1>No. KP Baru</option>\n");
			}
			if (selectedValue.intValue() == 2) {
				sb.append("<option selected value=2>No. KP Lama</option>\n");
			} else {
				sb.append("<option value=2>No. KP Lama</option>\n");
			}
			if (selectedValue.intValue() == 3) {
				sb.append("<option selected value=3>No. KP Lain</option>\n");
			} else {
				sb.append("<option value=3>No. KP Lain</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ** Peg Pengendali from Tblppkrujunit
	// ** by elly (080709)
	public static String SelectPegawaiPengendali(String selectName)
			throws Exception {
		return SelectPegawaiPengendali(selectName, null, null, null);
	}

	public static String SelectPegawaiPengendali(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectPegawaiPengendali(selectName, selectedValue, disability,
				null);
	}

	public static String SelectPegawaiPengendali(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");

			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");

			Vector v = DB.getPegawaiPengendali();
			Tblppkrujunit f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppkrujunit) v.get(i);
				if (f.getidunitpk().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getidunitpk() + ">"
						+ f.getkod() + " - " + f.getnamapegawai()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ** List of Mahkamah from Tblrujpejabat (PPK)
	// ** by elly (120709)
	public static String SelectMahkamah(String selectName) throws Exception {
		return SelectMahkamah(selectName, null, null, null);
	}

	public static String SelectMahkamah(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectMahkamah(selectName, selectedValue, disability, null);
	}

	public static String SelectMahkamah(String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");

			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");

			Vector v = DB.getMahkamah();
			Tblrujpejabat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpejabat) v.get(i);
				if (f.getIdPejabat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPejabat() + ">"
						+ f.getKodPejabat() + " - "
						+ f.getNamaPejabat().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ** Mahkamah mengikut negeri @eLLy [250809]
	public static String SelectMahkamahByNegeri(Long idnegeri, String selectName)
			throws Exception {
		return SelectMahkamahByNegeri(idnegeri, selectName, null, null, null);
	}

	public static String SelectMahkamahByNegeri(Long idnegeri,
			String selectName, String jsFunction) throws Exception {
		return SelectMahkamahByNegeri(idnegeri, selectName, null, null,
				jsFunction);
	}

	public static String SelectMahkamahByNegeri(Long idnegeri,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectMahkamahByNegeri(idnegeri, selectName, selectedValue,
				disability, null);
	}

	public static String SelectMahkamahByNegeri(Long idnegeri,
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
			Vector v = DB.getMahkamahByNegeri(idnegeri);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod_pejabat") + " - "
						+ ((String) h.get("namaPejabat")).toUpperCase()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	} // CLOSE

	// ** Mahkamah Syariah mengikut negeri @eLLy [230909]
	public static String SelectMahkamahSyariahByNegeri(Long idnegeri,
			String selectName) throws Exception {
		return SelectMahkamahSyariahByNegeri(idnegeri, selectName, null, null,
				null);
	}

	public static String SelectMahkamahSyariahByNegeri(Long idnegeri,
			String selectName, String jsFunction) throws Exception {
		return SelectMahkamahSyariahByNegeri(idnegeri, selectName, null, null,
				jsFunction);
	}

	public static String SelectMahkamahSyariahByNegeri(Long idnegeri,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectMahkamahSyariahByNegeri(idnegeri, selectName,
				selectedValue, disability, null);
	}

	public static String SelectMahkamahSyariahByNegeri(Long idnegeri,
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
			Vector v = DB.getMahkamahSyariahByNegeri(idnegeri);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("id") + " - "
						+ ((String) h.get("namaPejabat")).toUpperCase() + " - "
						+ h.get("namaDaerah") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	} // CLOSE

	// --Tempat bicara @syah

	public static String SelectTempatBicara(String selectName) throws Exception {
		return SelectTempatBicara(selectName, null, null, null);
	}

	public static String SelectTempatBicara(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectTempatBicara(selectName, selectedValue, disability, null);
	}

	public static String SelectTempatBicara(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getTempatBicara();
			Tblppkrujunit f = null;
			String s = "";
			String getketeranganunitpsk = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppkrujunit) v.get(i);
				if (f.getidunitpk().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				getketeranganunitpsk = f.getketeranganunitpsk();
				if (getketeranganunitpsk != null) {
					getketeranganunitpsk = getketeranganunitpsk.toUpperCase();
				}
				sb.append("<option " + s + " value=" + f.getidunitpk() + ">"
						+ f.getkod() == null ? "" : f.getkod() + " - "
						+ f.getnamapejabat().toUpperCase() + " "
						+ getketeranganunitpsk + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ** Tempat bicara mengikut negeri @syah
	public static String SelectTempatBicaraByNegeri(String idnegeri,
			String selectName) throws Exception {
		return SelectTempatBicaraByNegeri(idnegeri, selectName, null, null,
				null);
	}

	public static String SelectTempatBicaraByNegeri(String idnegeri,
			String selectName, String jsFunction) throws Exception {
		return SelectTempatBicaraByNegeri(idnegeri, selectName, null, null,
				jsFunction);
	}

	public static String SelectTempatBicaraByNegeri(String idnegeri,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectTempatBicaraByNegeri(idnegeri, selectName, selectedValue,
				disability, null);
	}

	public static String SelectTempatBicaraByNegeri(String idnegeri,
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

			Vector v = DB.getTempatBicaraByNegeri(idnegeri);
			Tblppkrujunit f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppkrujunit) v.get(i);
				if (f.getidJkptg().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getidJkptg() + ">"
						+ f.getnamapejabat().toUpperCase() + " "
						+ f.getalamat1().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// ** Tempat bicara mengikut pejabatjkptg @syah

	public static String SelectTempatBicaraByPejabatJKPTG(String id_jkptg,
			String selectName) throws Exception {
		return SelectTempatBicaraByPejabatJKPTG(id_jkptg, selectName, null,
				null, null);
	}

	public static String SelectTempatBicaraByPejabatJKPTG(String id_jkptg,
			String selectName, String jsFunction) throws Exception {
		return SelectTempatBicaraByPejabatJKPTG(id_jkptg, selectName, null,
				null, jsFunction);
	}

	public static String SelectTempatBicaraByPejabatJKPTG(String id_jkptg,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectTempatBicaraByPejabatJKPTG(id_jkptg, selectName,
				selectedValue, disability, null);
	}

	public static String SelectTempatBicaraByPejabatJKPTG(String id_jkptg,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		myLogger.info("id_jkptg = "+id_jkptg);
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");

			Vector v = DB.getTempatBicaraByPejabatJKPTG(id_jkptg);
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

	public static String SelectJenisPerintahSek8(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getJenisPerintahSek8();
			Tblppkrujjenisperintah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppkrujjenisperintah) v.get(i);
				if (f.getIdJenisperintah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenisperintah()
						+ ">" + f.getKod() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ** pegawai pengendali mengikut id pejabatjkptg @syah

	public static String SelectPegawaiPengendaliByJKPTG(String id_jkptg,
			String selectName) throws Exception {
		return SelectPegawaiPengendaliByJKPTG(id_jkptg, selectName, null, null,
				null);
	}

	public static String SelectPegawaiPengendaliByJKPTG(String id_jkptg,
			String selectName, String jsFunction) throws Exception {
		return SelectPegawaiPengendaliByJKPTG(id_jkptg, selectName, null, null,
				jsFunction);
	}

	public static String SelectPegawaiPengendaliByJKPTG(String id_jkptg,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectPegawaiPengendaliByJKPTG(id_jkptg, selectName,
				selectedValue, disability, null);
	}

	public static String SelectPegawaiPengendaliByJKPTG(String id_jkptg,
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

			Vector v = DB.getPegawaiPengendaliByJKPTG(id_jkptg);
			Tblppkrujunit f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppkrujunit) v.get(i);
				if (f.getidunitpk().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getidunitpk() + ">"
						+ f.getkod() + " - " + f.getnamapegawai()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// ** pegawai pengendali mengikut id negeri @syah
	public static String SelectPegawaiPengendaliByNegeri(String id_negeri,
			String selectName) throws Exception {
		return SelectPegawaiPengendaliByNegeri(id_negeri, selectName, null,
				null, null);
	}

	public static String SelectPegawaiPengendaliByNegeri(String id_negeri,
			String selectName, String jsFunction) throws Exception {
		return SelectPegawaiPengendaliByNegeri(id_negeri, selectName, null,
				null, jsFunction);
	}

	public static String SelectPegawaiPengendaliByNegeri(String id_negeri,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectPegawaiPengendaliByNegeri(id_negeri, selectName,
				selectedValue, disability, null);

	}

	public static String XSelectPegawaiPengendaliByNegeri(String id_negeri,
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

			Vector v = DB.getPegawaiPengendaliByNegeri(id_negeri);
			Tblppkrujunit f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppkrujunit) v.get(i);
				if (f.getidunitpk().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getidunitpk() + ">"
						+ f.getkod() + " - " + f.getnamapegawai()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	public static String SelectPegawaiPengendaliByNegeri(String idNegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		return PPKUtilHTML.SelectPegawaiLaporan(idNegeri, selectName, selectedValue,
				disability, jsFunction);

	}
	
	public static String SelectPegawaiPengendaliByNegeri_KPP(String idNegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction, boolean hq_kpp) throws Exception {
		return PPKUtilHTML.SelectPegawaiLaporan_KPP(idNegeri, selectName, selectedValue,
				disability, jsFunction, hq_kpp);

	}

	public static String SelectFirmaTerdahuluByPemohon(String id_pemohon,
			String id_permohonan, String selectName) throws Exception {
		return SelectFirmaTerdahuluByPemohon(id_pemohon, id_permohonan,
				selectName, null, null, null);
	}

	public static String SelectFirmaTerdahuluByPemohon(String id_pemohon,
			String id_permohonan, String selectName, String jsFunction)
			throws Exception {
		return SelectFirmaTerdahuluByPemohon(id_pemohon, id_permohonan,
				selectName, null, null, jsFunction);
	}

	public static String SelectFirmaTerdahuluByPemohon(String id_pemohon,
			String id_permohonan, String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectFirmaTerdahuluByPemohon(id_pemohon, id_permohonan,
				selectName, selectedValue, disability, null);
	}

	public static String SelectFirmaTerdahuluByPemohon(String id_pemohon,
			String id_permohonan, String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");

			Vector v = DB.getFirmaTerdahuluByPemohon(id_pemohon, id_permohonan);
			Tblppkpeguam f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppkpeguam) v.get(i);
				if (f.getIdPeguam().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPeguam() + ">"
						+ f.getNamaFirma().toUpperCase() + "</option>\n");
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
			
			Vector v = DB.getSenaraiPegawaiLaporan(idNegeri);
			Tblppkrujunit f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppkrujunit) v.get(i);
				if (f.getidunitpk().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getidunitpk() + ">"
						+ f.getkod() + " - " + f.getnamapegawai() + " "
						+ f.getcatatan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		myLogger.info("sb.toString() = " +sb.toString());
		return sb.toString();

	}

	public static String SelectStatusSek8(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getSenaraiStatusPPKSek8();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectDaerahForSuratIringanTerengganu(
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
			Vector v = DB.getDaerahForSuratIringanTerengganu();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("namaDaerah")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectUnitPPKByNegeri(String selectName,
			Long selectedValue, String disability, String jsFunction,
			String idNegeri) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getUnitPPKByNegeri(idNegeri);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	/*public static String SelectBulan(String selectName, Long selectedValue,

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
					Vector v = DB.getBulan();

					Tblrujbulan f = null;
					String s = "";
					for (int i = 0; i < v.size(); i++) {
						f = (Tblrujbulan) v.get(i);
						if (f.getIdBulan().equals(selectedValue)) {
							s = "selected";
						} else {
							s = "";
						}
						sb.append("<option " + s + " value=" + f.getIdBulan() + ">"
								+ f.getNamaBulan() + "</option>\n");
					}
					sb.append("</select>");
				} catch (Exception ex) {
					ex.printStackTrace();
					throw ex;
				}

				return sb.toString();
			}*/

	public static String SelectTahun(String selectName, String selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getTahun();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (selectedValue.compareToIgnoreCase(h.get("id").toString()) == 0) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("tahun") + "</option>\n");
			}
			sb.append("</select>");

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	public static String SelectTahunH(String selectName, String selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getTahunH();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (selectedValue.compareToIgnoreCase(h.get("id").toString()) == 0) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("tahun") + "</option>\n");
			}
			sb.append("</select>");

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	

	public static String SelectHTAByIdSimati(String idsimati,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getHTAByIdSimati(idsimati);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("noHakmilik") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// ** pejabat tanah by pejabatkptg @syah

	public static String SelectPejabatTanahByJKPTG(String id_jkptg,
			String selectName) throws Exception {
		return SelectPejabatTanahByJKPTG(id_jkptg, selectName, null, null, null);
	}

	public static String SelectPejabatTanahByJKPTG(String id_jkptg,
			String selectName, String jsFunction) throws Exception {
		return SelectPejabatTanahByJKPTG(id_jkptg, selectName, null, null,
				jsFunction);
	}

	public static String SelectPejabatTanahByJKPTG(String id_jkptg,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectPejabatTanahByJKPTG(id_jkptg, selectName, selectedValue,
				disability, null);
	}

	public static String SelectPejabatTanahByJKPTG(String id_jkptg,
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

			Vector v = DB.getPejabatTanahByJKPTG(id_jkptg);
			Tblrujpejabat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpejabat) v.get(i);
				if (f.getIdPejabat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPejabat() + ">"
						+ f.getNamaPejabat().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	public static String SelectPejabatTanah(String selectName) throws Exception {
		return SelectPejabatTanah(selectName, null, null, null);
	}

	public static String SelectPejabatTanah(String selectName, String jsFunction)
			throws Exception {
		return SelectPejabatTanah(selectName, null, null, jsFunction);
	}

	public static String SelectPejabatTanah(String selectName,
			Long selectedValue) throws Exception {
		return SelectPejabatTanah(selectName, selectedValue, null, null);
	}

	public static String SelectPejabatTanah(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectPejabatTanah(selectName, selectedValue, disability, null);
	}

	public static String SelectPejabatTanah(String selectName,
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
			Vector v = DB.getPejabatTanah();
			Tblrujpejabat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpejabat) v.get(i);
				if (f.getIdPejabat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPejabat() + ">"
						+ f.getNamaPejabat() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectDaerahByUnitPPK(String selectName,
			Long selectedValue, String disability, String jsFunction,
			String idPejabatJKPTG) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getDaerahByUnitPPK(idPejabatJKPTG);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPejabatPenasihatUndangUndangByNegeri(
			String idnegeri, String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {

		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");

			Vector v = DB.getPejabatPenasihatUndangUndangByNegeri(idnegeri);
			Tblrujpejabat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpejabat) v.get(i);
				if (f.getIdPejabat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPejabat() + ">"
						+ f.getNamaPejabat().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	public static String SelectPejabatPenasihatUndangUndang(String selectName,
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
			Vector v = DB.getPejabatPenasihatUndangUndang();
			Tblrujpejabat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpejabat) v.get(i);
				if (f.getIdPejabat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPejabat() + ">"
						+ f.getNamaPejabat() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ELLY 04112010 - PIHAK YANG MEMBANTAH DR TBLRUJJENISPB

	public static String SelectPihakBantah(String selectName) throws Exception {
		return SelectPihakBantah(selectName, null, null);
	}

	public static String SelectPihakBantah(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getJenisPB();
			Tblrujjenispb f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenispb) v.get(i);
				if (f.getIdJenispb().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenispb() + ">"
						+ f.getKodJenisPb() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// Status seksyen 4 PPT
	public static String SelectStatusPPT(String selectName) throws Exception {
		return SelectStatusPPT(selectName, null, null, null);
	}

	public static String SelectStatusPPT(String selectName, String jsFunction)
			throws Exception {
		return SelectStatusPPT(selectName, null, null, jsFunction);
	}

	public static String SelectStatusPPT(String selectName, Long selectedValue)
			throws Exception {
		return SelectStatusPPT(selectName, selectedValue, null, null);
	}

	public static String SelectStatusPPT(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectStatusPPT(selectName, selectedValue, disability, null);
	}

	public static String SelectStatusPPT(String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusPPT();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKodStatus() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// GET HAKMILIK BY PB (PPT) - ELLY 13012010

	public static String SelectHakmilik(String id_hakmilik,
			String id_pihakberkepentingan, String selectName) throws Exception {
		return SelectHakmilik(id_hakmilik, id_pihakberkepentingan, selectName,
				null, null, null);

	}

	public static String SelectHakmilik(String id_hakmilik,
			String id_pihakberkepentingan, String selectName, String jsFunction)
			throws Exception {
		return SelectHakmilik(id_hakmilik, id_pihakberkepentingan, selectName,
				null, null, jsFunction);

	}

	public static String SelectHakmilik(String id_hakmilik,
			String id_pihakberkepentingan, String selectName, Long selectedValue)
			throws Exception {

		return SelectHakmilik(id_hakmilik, id_pihakberkepentingan, selectName,
				selectedValue, null, null);

	}

	public static String SelectHakmilik(String id_hakmilik,
			String id_pihakberkepentingan, String selectName,
			Long selectedValue,

			String disability) throws Exception {

		return SelectHakmilik(id_hakmilik, id_pihakberkepentingan, selectName,
				selectedValue, disability, null);

	}

	public static String SelectHakmilik(String id_hakmilik,
			String id_pihakberkepentingan, String selectName,
			Long selectedValue,

			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			// sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getHakmilik(id_hakmilik, id_pihakberkepentingan);

			Tblppthakmilik f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppthakmilik) v.get(i);
				if (f.getIdHakmilik().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdHakmilik() + ">"
				// + f.getIdHakmilik() + " - " + f.getNoHakmilik()
						+ f.getNoHakmilik() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// GET NOLOT BY HAKMILIK (PPT) - ELLY 13012010
	public static String SelectNoLotByHakmilik(String id_hakmilik,
			String selectName) throws Exception {
		return SelectNoLotByHakmilik(id_hakmilik, selectName, null, null, null);
	}

	public static String SelectNoLotByHakmilik(String id_hakmilik,
			String selectName, String jsFunction) throws Exception {
		return SelectNoLotByHakmilik(id_hakmilik, selectName, null, null,
				jsFunction);
	}

	public static String SelectNoLotByHakmilik(String id_hakmilik,
			String selectName, Long selectedValue) throws Exception {
		return SelectNoLotByHakmilik(id_hakmilik, selectName, selectedValue,
				null, null);
	}

	public static String SelectNoLotByHakmilik(String id_hakmilik,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectNoLotByHakmilik(id_hakmilik, selectName, selectedValue,
				disability, null);
	}

	public static String SelectNoLotByHakmilik(String id_hakmilik,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			// sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getNoLotByHakmilik(id_hakmilik);
			Tblppthakmilik f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppthakmilik) v.get(i);
				if (f.getIdHakmilik().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdHakmilik() + ">"
				// + f.getIdHakmilik() + " - " + f.getNoLot()
						+ f.getNoLot() + "</option>\n");
			}

			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// GET NAMA PEMBANTAH BY HAKMILIK (PPT) - ELLY 13012010
	public static String SelectNamaPembantahByHakmilik(String id_hakmilik,
			String selectName) throws Exception {
		return SelectNamaPembantahByHakmilik(id_hakmilik, selectName, null,
				null, null);
	}

	public static String SelectNamaPembantahByHakmilik(String id_hakmilik,
			String selectName, String jsFunction) throws Exception {
		return SelectNamaPembantahByHakmilik(id_hakmilik, selectName, null,
				null, jsFunction);
	}

	public static String SelectNamaPembantahByHakmilik(String id_hakmilik,
			String selectName, Long selectedValue) throws Exception {
		return SelectNamaPembantahByHakmilik(id_hakmilik, selectName,
				selectedValue, null, null);
	}

	public static String SelectNamaPembantahByHakmilik(String id_hakmilik,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectNamaPembantahByHakmilik(id_hakmilik, selectName,
				selectedValue, disability, null);
	}

	public static String SelectNamaPembantahByHakmilik(String id_hakmilik,
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
			Vector v = DB.getPihakBantahByHakmilik(id_hakmilik);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_pihakberkepentingan").equals(selectedValue)) {

					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ h.get("id_pihakberkepentingan") + ">"
						+ h.get("id_pihakberkepentingan") + " - "
						+ h.get("nama_pb") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// GET NAMA PEMBANTAH BY ID_PIHAKBERKEPENTINGAN (PPT) - ELLY 10022010
	public static String SelectNamaPembantahByIdPihakBerkepentingan(
			String id_hakmilikpb, String selectName) throws Exception {
		return SelectNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb,
				selectName, null, null, null);
	}

	public static String SelectNamaPembantahByIdPihakBerkepentingan(
			String id_hakmilikpb, String selectName, String jsFunction)
			throws Exception {
		return SelectNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb,
				selectName, null, null, jsFunction);
	}

	public static String SelectNamaPembantahByIdPihakBerkepentingan(
			String id_hakmilikpb, String selectName, Long selectedValue)
			throws Exception {
		return SelectNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb,
				selectName, selectedValue, null, null);
	}

	public static String SelectNamaPembantahByIdPihakBerkepentingan(
			String id_hakmilikpb, String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb,
				selectName, selectedValue, disability, null);
	}

	public static String SelectNamaPembantahByIdPihakBerkepentingan(
			String id_hakmilikpb, String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			// sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB
					.getNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_pihakberkepentingan").equals(selectedValue)) {

					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id_hakmilikpb")
						+ ">"
						// + h.get("id_hakmilikpb") + " - " + h.get("nama_pb") +
						// "</option>\n");
						+ h.get("nama_pb") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// GET JENIS PIHAK PEMBANTAH BY ID PIHAKBERKEPENTINGAN (PPT) - ELLY 13012010
	public static String SelectPihakBantahByIdPihakBerkepentingan(
			String id_pihakberkepentingan, String selectName) throws Exception {
		return SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan,
				selectName, null, null, null);
	}

	public static String SelectPihakBantahByIdPihakBerkepentingan(
			String id_pihakberkepentingan, String selectName, String jsFunction)
			throws Exception {
		return SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan,
				selectName, null, null, jsFunction);
	}

	public static String SelectPihakBantahByIdPihakBerkepentingan(
			String id_pihakberkepentingan, String selectName, Long selectedValue)
			throws Exception {
		return SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan,
				selectName, selectedValue, null, null);
	}

	public static String SelectPihakBantahByIdPihakBerkepentingan(
			String id_pihakberkepentingan, String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan,
				selectName, selectedValue, disability, null);
	}

	// public static String SelectPihakBantahByIdPihakBerkepentingan(String
	// id_pihakberkepentingan,String selectName, Long selectedValue, String
	// disability, String jsFunction)
	//
	// throws Exception {
	// StringBuffer sb = new StringBuffer("");
	// // try {
	// // sb.append("<select name='" + selectName + "' ");
	// // if (disability != null)
	// // sb.append(disability);
	// // if (jsFunction != null)
	// // sb.append(jsFunction);
	// // sb.append(">");
	// // //sb.append("<option value=>SILA PILIH</option>\n");
	// Vector v =
	// DB.getPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan);
	// // Hashtable h;
	// // String s = "";
	// // for (int i = 0; i < v.size(); i++) {
	// // h = new Hashtable();
	// // h = (Hashtable) v.get(i);
	// // if (h.get("id_jenispb").equals(selectedValue)) {
	// // s = "selected";
	// // } else {
	// // s = "";
	// // }
	// // sb.append("<option " + s + " value=" + h.get("id_jenispb") + ">"
	// // + h.get("kod_jenispb") + " - " + h.get("keterangan") + "</option>\n");
	// // }
	// // sb.append("</select>");
	// // } catch (Exception ex) {
	// // ex.printStackTrace();
	// // throw ex;
	// // }
	// if (v.size() > 0) {
	//
	// sb.append("<select name='" + selectName + "' ");
	// if (disability != null)
	// sb.append(disability);
	// if (jsFunction != null)
	// sb.append(jsFunction);
	// sb.append(">");
	//
	// Hashtable h;
	// String s = "";
	// for (int i = 0; i < v.size(); i++) {
	// h = new Hashtable();
	// h = (Hashtable) v.get(i);
	// if (h.get("id_jenispb").equals(selectedValue)) {
	// s = "selected";
	// } else {
	// s = "";
	// }
	// sb.append("<option " + s + " value=" + h.get("id_jenispb") + ">"
	// + h.get("kod_jenispb") + " - " + h.get("keterangan") + "</option>\n");
	// }
	// sb.append("</select>");
	//
	// } else {
	// sb.append("TIADA MAKLUMAT");
	// }
	// return sb.toString();
	// }

	public static String SelectPihakBantahByIdPihakBerkepentingan(
			String id_pihakberkepentingan, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			// sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB
					.getPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_jenispb").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id_jenispb")
						+ ">" + h.get("kod_jenispb") + " - "
						+ h.get("keterangan") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// GET CARA BAYAR PAMPASAN BANTAHAN (PPT) - ELLY 19012010
	public static String selectCaraBayar(String selectName) throws Exception {
		return selectCaraBayar(selectName, null, null);
	}

	public static String selectCaraBayar(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getCaraBayar();
			Tblrujcarabayar f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujcarabayar) v.get(i);
				if (f.getIdCarabayar().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdCarabayar() + ">"
						+ f.getKodCaraBayar() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String selectCaraBayar(String selectName, Long selectedValue,
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
			Vector v = DB.getCaraBayar();
			Tblrujcarabayar f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujcarabayar) v.get(i);
				if (f.getIdCarabayar().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdCarabayar() + ">"
						+ f.getKodCaraBayar() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	// GET CARA BAYAR HASIL (PHP) - PEJE
	public static String selectCaraBayaran(String selectName,
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
			Vector v = DB.getCaraBayaran();
			Hashtable h;

			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_CARABAYAR").toString().trim()
						.equals(String.valueOf(selectedValue).trim())) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_CARABAYAR")
						+ ">" + h.get("KETERANGAN") + "</option>\n");
			}
			sb.append("</select>");

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	// 21012009 Kategori Tanah -Syah
	public static String SelectKategoriTanah(String selectName,
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
			Vector v = DB.getKategoriTanah();
			Tblrujkategori f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkategori) v.get(i);
				if (f.getIdKategori().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKategori() + ">"
						+ f.getKodKategori() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectSubKategoriTanah(String idKategoriTanah,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getSubKategoriTanah(idKategoriTanah);
			Tblrujsubkategori f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsubkategori) v.get(i);
				if (f.getIdSubkategori().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSubkategori()
						+ ">" + f.getKodSubkategori() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// GET PILIHAN BANK BANTAHAN (PPT) - ELLY 19012010
	public static String selectBank(String selectName) throws Exception {
		return selectBank(selectName, null, null);
	}

	public static String selectBank(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getJenisBank();
			Tblrujbank f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujbank) v.get(i);
				if (f.getIdBank().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdBank() + ">"
						+ f.getKodBank().toUpperCase() + " - " + f.getNamaBank().toUpperCase()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	public static String SelectJenisBayaranFiAPB(String selectName, Long selectedValue,
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
			Vector v = DB.getJenisBayaranFiAPB();
			Tblrujjenisbayaran f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenisbayaran) v.get(i);
				if (f.getIdJenisbayaran().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenisbayaran() + ">"
						 + f.getKeterangan().toUpperCase()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}
	
	public static String selectBank(String selectName, Long selectedValue,
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
			Vector v = DB.getJenisBank();
			Tblrujbank f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujbank) v.get(i);
				if (f.getIdBank().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdBank() + ">"
						+ f.getKodBank().toUpperCase() + " - " + f.getNamaBank().toUpperCase()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}
	

	// 24012009 Jenis Bebanan -Syah
	public static String SelectBebanan(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectBebanan(selectName, selectedValue, disability, null);
	}

	public static String SelectBebanan(String selectName, Long selectedValue,
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
			Vector v = DB.getJenisBebanan();
			Tblrujbebanan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujbebanan) v.get(i);
				if (f.getIdRujbebanan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdRujbebanan()
						+ ">" + f.getKodBebanan() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// 26012009 Jabatan Teknikal -Syah
	public static String SelectJabatanTeknikal(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getJabatanTeknikal();
			Tblpptjabatanteknikal f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpptjabatanteknikal) v.get(i);
				if (f.getIdJabatanteknikal().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJabatanteknikal()
						+ ">" + f.getKodJabatanTeknikal() + " - "
						+ f.getNamaJabatan() + "</option>\n");
			}
			sb.append("</select");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// 02022010 get PTG by negeri -Syah
	public static String SelectPejabatPTGbyNegeri(String idnegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPejabatPTGbyNegeri(idnegeri);
			Tblrujpejabat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpejabat) v.get(i);
				if (f.getIdPejabat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPejabat() + ">"
						+ f.getKodPejabat() + " - " + f.getNamaPejabat()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// 02022010 get PTG -Syah
	public static String SelectPejabatPTG(String selectName,
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
			Vector v = DB.getPejabatPTG();
			Tblrujpejabat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpejabat) v.get(i);
				if (f.getIdPejabat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPejabat() + ">"
						+ f.getKodPejabat() + " - " + f.getNamaPejabat()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}// close get PTG

	// 02022010 get PTD by negeri -Syah
	public static String SelectPejabatPTDbyNegeri(String idnegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPejabatPTDbyNegeri(idnegeri);
			Tblrujpejabat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpejabat) v.get(i);
				if (f.getIdPejabat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPejabat() + ">"
						+ f.getKodPejabat() + " - " + f.getNamaPejabat()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// 02022010 get PTG -Syah
	public static String SelectPejabatPTD(String selectName,
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
			Vector v = DB.getPejabatPTD();
			Tblrujpejabat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpejabat) v.get(i);
				if (f.getIdPejabat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPejabat() + ">"
						+ f.getKodPejabat() + " - " + f.getNamaPejabat()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}// close get PTG
	/**
	 * Created by 	: Mohamad Rosli 
	 * Created on 	: 06/02/2010 
	 * Updated by 	: 06/02/2010 
	 * Propose 		: Menyenarai kakitangan yang berjawatan Pegawai Pra syarat : Pengguna biasa
	 * 				  hendaklah diassign kepada sub -Pegawai- terlebih dahulu
	 */
	public static String SelectPegawaiMengikutSeksyen(String idSeksyen,
		String idPegawai, String selectName, Long selectedValue,
		String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");

			Vector v = ekptg.model.utils.FrmMappingUserPegawaiData
					.getSenaraiPegawai(idSeksyen, idPegawai, null);
			Hashtable<String, String> f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Hashtable<String, String>) v.get(i);
				if (f.get("idpegawai").equals("" + selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.get("idpegawai") + ">"
						+ f.get("namapegawai") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	public static String SelectKategoriPemohonPTDPTGJKPTG(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getKategoriPemohonPTDPTGJKPTG();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	public static String SelectJenisPejabat(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPejabatKJP();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPejabatJKPTGByIdNegeri(String selectName,
			Long selectedValue, String disability, String jsFunction,
			String idNegeri) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPejabatJKPTGByIdNegeri(idNegeri);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("namaPejabat")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPejabatByIdNegeriAndJenisPejabat(
			String selectName, Long selectedValue, String disability,
			String jsFunction, String idNegeri, String jenisPejabat)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPejabatByIdNegeriAndJenisPejabat(idNegeri,
					jenisPejabat);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("namaPejabat")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ADDED BY PEJE
	public static String SelectPejabatKPTGByIdNegeri(String selectName,
			Long selectedValue, String disability, String jsFunction,
			String idNegeri) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPejabatKPTGByIdNegeri(idNegeri);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("namaPejabat")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	public static String SelectPejabatKPTGByIdNegeriIdSeksyen(String selectName,
			Long selectedValue, String disability, String jsFunction,
			String idNegeri, String idSeksyen) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPejabatKPTGByIdNegeriIdSeksyen(idNegeri, idSeksyen);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("namaPejabat")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ADDED BY HIDAYAH 08022010
	public static String SelectNamaPB(String idPermohonan, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getNamaPB(idPermohonan);
			Tblpptpihakberkepentingan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpptpihakberkepentingan) v.get(i);
				if (f.getIdPihakberkepentingan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ f.getIdPihakberkepentingan() + ">" + f.getNamaPb()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ADDED BY HIDAYAH 11022010
	public static String SelectHakmilikSementara(String id_permohonan,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getHakmilikSementara(id_permohonan);
			Tblppthakmilik f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppthakmilik) v.get(i);
				if (f.getIdHakmilik().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdHakmilik() + ">"
						+ f.getIdHakmilik() + " - " + f.getNoHakmilik()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ADDED BY HIDAYAH 17022010

	public static String SelectPB(String idHakmilik, String selectName,
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
			Vector v = DB.getPB(idHakmilik);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_HAKMILIKPB").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_HAKMILIKPB")
						+ ">" + h.get("NAMA_PB") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// 10022010 get PB by hakmilik -Syah

	public static String SelectPBbyHakmilik(String idhakmilik, String selectName)
			throws Exception {
		return SelectPBbyHakmilik(idhakmilik, selectName, null, null, null);
	}

	public static String SelectPBbyHakmilik(String idhakmilik,
			String selectName, String jsFunction) throws Exception {
		return SelectPBbyHakmilik(idhakmilik, selectName, null, null,
				jsFunction);
	}

	public static String SelectPBbyHakmilik(String idhakmilik,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectPBbyHakmilik(idhakmilik, selectName, selectedValue,
				disability, null);
	}

	public static String SelectPBbyHakmilik(String idhakmilik,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPBbyHakmilik(idhakmilik);
			Tblpptpihakberkepentingan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpptpihakberkepentingan) v.get(i);
				if (f.getIdPihakberkepentingan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ f.getIdPihakberkepentingan() + ">"
						+ f.getNamaPb().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	// 10022010 get PB by permohonan -Syah

	public static String SelectPBbyPermohonan(String idpermohonan,
			String selectName) throws Exception {
		return SelectPBbyPermohonan(idpermohonan, selectName, null, null, null);
	}

	public static String SelectPBbyPermohonan(String idpermohonan,
			String selectName, String jsFunction) throws Exception {
		return SelectPBbyPermohonan(idpermohonan, selectName, null, null,
				jsFunction);
	}

	public static String SelectPBbyPermohonan(String idpermohonan,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectPBbyPermohonan(idpermohonan, selectName, selectedValue,
				disability, null);
	}

	public static String SelectPBbyPermohonan(String idpermohonan,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPBbyPermohonan(idpermohonan);
			Tblpptpihakberkepentingan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpptpihakberkepentingan) v.get(i);
				if (f.getIdPihakberkepentingan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ f.getIdPihakberkepentingan() + ">"
						+ f.getNamaPb().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	// 10022010 get Hakmilik by permohonan -Syah

	public static String SelectHakmilikByPermohonan(String idpermohonan,
			String selectName) throws Exception {
		return SelectHakmilikByPermohonan(idpermohonan, selectName, null, null,
				null);
	}

	public static String SelectHakmilikByPermohonan(String idpermohonan,
			String selectName, String jsFunction) throws Exception {
		return SelectHakmilikByPermohonan(idpermohonan, selectName, null, null,
				jsFunction);
	}

	public static String SelectHakmilikByPermohonan(String idpermohonan,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectHakmilikByPermohonan(idpermohonan, selectName,
				selectedValue, disability, null);
	}

	public static String SelectHakmilikByPermohonan(String idpermohonan,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getHakmilikByPermohonan(idpermohonan);
			Tblppthakmilik f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppthakmilik) v.get(i);
				if (f.getIdHakmilik().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdHakmilik() + ">"
						+ f.getNoHakmilik().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	// 10022010 get Hakmilik by permohonan *yang dah buat siasatan -Syah

	public static String SelectHakmilikByPermohonanWithSiasatan(
			String idpermohonan, String selectName) throws Exception {
		return SelectHakmilikByPermohonanWithSiasatan(idpermohonan, selectName,
				null, null, null);
	}

	public static String SelectHakmilikByPermohonanWithSiasatan(
			String idpermohonan, String selectName, String jsFunction)
			throws Exception {
		return SelectHakmilikByPermohonanWithSiasatan(idpermohonan, selectName,
				null, null, jsFunction);
	}

	public static String SelectHakmilikByPermohonanWithSiasatan(
			String idpermohonan, String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectHakmilikByPermohonanWithSiasatan(idpermohonan, selectName,
				selectedValue, disability, null);
	}

	public static String SelectHakmilikByPermohonanWithSiasatan(
			String idpermohonan, String selectName, Long selectedValue,
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
			Vector v = DB.getHakmilikByPermohonanWithSiasatan(idpermohonan);
			Tblppthakmilik f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppthakmilik) v.get(i);
				if (f.getIdHakmilik().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdHakmilik() + ">"
						+ f.getNoHakmilik().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	// select negeri with kod mampu
	public static String SelectNegeriMampu(String selectName) throws Exception {
		return SelectNegeriMampu(selectName, null, null, null);
	}

	public static String SelectNegeriMampu(String selectName, String jsFunction)
			throws Exception {
		return SelectNegeriMampu(selectName, null, null, jsFunction);
	}

	public static String SelectNegeriMampu(String selectName, Long selectedValue)
			throws Exception {
		return SelectNegeriMampu(selectName, selectedValue, null, null);
	}

	public static String SelectNegeriMampu(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectNegeriMampu(selectName, selectedValue, disability, null);
	}

	public static String SelectNegeriMampu(String selectName,
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
			Vector v = DB.getNegeriMampu();
			Tblrujnegeri f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujnegeri) v.get(i);
				if (f.getIdNegeri().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdNegeri() + ">"
						+ f.getKodMampu() + " - " + f.getNamaNegeri()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// 18032010 tblrujdaerahpenggawa -Syah
	public static String SelectDaerahPenggawa(String selectName,
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
			Vector v = DB.getDaerahPenggawa();
			Tblrujdaerahpenggawa f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerahpenggawa) v.get(i);
				if (f.getIdDaerahPenggawa().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerahPenggawa()
						+ ">" + f.getKodDaerahPenggawa() + " - "
						+ f.getNamaDaerahPenggawa() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// select pegawai ppt by negeri 22/03/2010
	public static String SelectPegawaiPPTByNegeri(String idNegeri,
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

			Vector v = DB.getSenaraiPegawaiPPTByNegeri(idNegeri);
			Users f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Users) v.get(i);
				if (f.getUserId().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getUserId() + ">"
						+ f.getUserName().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// select pegawai ppt by negeri 22/03/2010
	public static String SelectPegawaiPPT(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");

			Vector v = DB.getSenaraiPegawaiPPT();
			Users f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Users) v.get(i);
				if (f.getUserId().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getUserId() + ">"
						+ " - " + f.getUserName().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// added by hidayah 04/03/2010
	public static String SelectJenisPb2(String selectName) throws Exception {
		return SelectJenisPb2(selectName, null, null);
	}

	public static String SelectJenisPb2(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");

			Vector v = DB.getRujKodJenisPB2();
			Tblrujjenispb f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenispb) v.get(i);
				if (f.getIdJenispb().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenispb() + ">"
						+ f.getKodJenisPb() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// 26032010 get PB Pampasan by hakmilik -Syah

	public static String SelectPBPampasanByHakmilik(String idhakmilik,
			String idpihakberkepentingan, String idterimabayaran,
			String selectName) throws Exception {
		return SelectPBPampasanByHakmilik(idhakmilik, idpihakberkepentingan,
				idterimabayaran, selectName, null, null, null);
	}

	public static String SelectPBPampasanByHakmilik(String idhakmilik,
			String idpihakberkepentingan, String idterimabayaran,
			String selectName, String jsFunction) throws Exception {
		return SelectPBPampasanByHakmilik(idhakmilik, idpihakberkepentingan,
				idterimabayaran, selectName, null, null, jsFunction);
	}

	public static String SelectPBPampasanByHakmilik(String idhakmilik,
			String idpihakberkepentingan, String idterimabayaran,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectPBPampasanByHakmilik(idhakmilik, idpihakberkepentingan,
				idterimabayaran, selectName, selectedValue, disability, null);
	}

	public static String SelectPBPampasanByHakmilik(String idhakmilik,
			String idpihakberkepentingan, String idterimabayaran,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPBPampasanByHakmilik(idhakmilik, idterimabayaran,
					idpihakberkepentingan);
			Tblpptpihakberkepentingan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpptpihakberkepentingan) v.get(i);
				if (f.getIdPihakberkepentingan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ f.getIdPihakberkepentingan() + ">"
						+ f.getNamaPb().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	// GET HAKMILIK BY AP (PPT) - ELLY 04042010
	public static String SelectHakmilikByAP(String id_hakmilik,
			String selectName) throws Exception {
		return SelectHakmilikByAP(id_hakmilik, selectName, null, null, null);

	}

	public static String SelectHakmilikByAP(String id_hakmilik,
			String selectName, String jsFunction) throws Exception {
		return SelectHakmilikByAP(id_hakmilik, selectName, null, null,
				jsFunction);

	}

	public static String SelectHakmilikByAP(String id_hakmilik,
			String selectName, Long selectedValue) throws Exception {

		return SelectHakmilikByAP(id_hakmilik, selectName, selectedValue, null,
				null);

	}

	public static String SelectHakmilikByAP(String id_hakmilik,
			String selectName, Long selectedValue,

			String disability) throws Exception {

		return SelectHakmilikByAP(id_hakmilik, selectName, selectedValue,
				disability, null);

	}

	public static String SelectHakmilikByAP(String id_hakmilik,
			String selectName, Long selectedValue,

			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			// sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getHakmilikByAP(id_hakmilik);

			Tblppthakmilik f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppthakmilik) v.get(i);
				if (f.getIdHakmilik().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdHakmilik() + ">"
						+ f.getNoHakmilik() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// Pegawai setiap hakmilik by permohonan /syah /06042010

	public static String SelectPegawaiHMbyPermohonan(String idpermohonan,
			String selectName, Long selectedValue, String disability,
			String jsFunction, String urusan) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPegawaiHMbyPermohonan(idpermohonan, urusan);
			Users f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Users) v.get(i);
				if (f.getUserId().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}

				sb.append("<option " + s + " value=" + f.getUserId() + ">"
						+ f.getUserName().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}// close SelectPegawaiHMbyPermohonan

	// **** unit pt exclude "LOT"
	public static String SelectUnitPT(String selectName) throws Exception {
		return SelectUnitPT(selectName, null, null, null);
	}

	public static String SelectUnitPT(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectUnitPT(selectName, selectedValue, disability, null);
	}

	public static String SelectUnitPT(String selectName, Long selectedValue,
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
			Vector v = DB.getUnitPT();
			Tblrujlot f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujlot) v.get(i);
				if (f.getIdLot().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdLot() + ">"
						+ f.getKodLot() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ** get Bahagian pb by hakmilik

	public static String SelectBahagianPBbyHakmilik(String id_hakmilik,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {

			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getBahagianPBbyHakmilik(id_hakmilik);
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				Hashtable h = new Hashtable();
				h = (Hashtable) v.get(i);

				if (h.get("ID_BAHAGIANPB")
						.equals(String.valueOf(selectedValue))) {
					s = "selected";
				} else {
					s = "";
				}

				sb.append("<option " + s + " value=" + h.get("ID_BAHAGIANPB")
						+ ">" + h.get("NAMA_PB") + "</option>\n");
			}
			sb.append("</select>");

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String selectPejabatJPPH(String selectName,
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
			Vector v = DB.getPejabatJPPH();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEJABAT").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_PEJABAT")
						+ ">" + h.get("KOD_PEJABAT") + " - "
						+ h.get("NAMA_PEJABAT") + " " + h.get("NAMA_DAERAH")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	// SelectPejabat jpph by negeri
	public static String selectPejabatJPPHByNegeri(String id_negeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {

			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPejabatJPPHByNegeri(id_negeri);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEJABAT").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}

				if (v.size() > 1) {
					sb.append("<option " + s + " value=" + h.get("ID_PEJABAT")
							+ ">" + h.get("KOD_PEJABAT") + " - "
							+ h.get("NAMA_PEJABAT") + " "
							+ h.get("NAMA_DAERAH") + "</option>\n");
				} else {
					sb.append("<option " + s + " value=" + h.get("ID_PEJABAT")
							+ ">" + h.get("KOD_PEJABAT") + " - "
							+ h.get("NAMA_PEJABAT") + " "
							+ h.get("NAMA_NEGERI") + "</option>\n");
				}

			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}// close SelectPejabat jpph by negeri

	// ---- add by Rizuan - Select file status for register file --------

	public static String SelectStatusFailA(String selectName) throws Exception {
		return SelectStatusFailA(selectName, null, null);
	}

	public static String SelectStatusFailA(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusFailA();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectStatusFailB(String selectName) throws Exception {
		return SelectStatusFailB(selectName, null, null);
	}

	public static String SelectStatusFailB(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusFailB();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectNegeriA(String selectName) throws Exception {
		return SelectNegeriA(selectName, null, null, null);
	}

	public static String SelectNegeriA(String selectName, String jsFunction)
			throws Exception {
		return SelectNegeriA(selectName, null, null, jsFunction);
	}

	public static String SelectNegeriA(String selectName, Long selectedValue)
			throws Exception {
		return SelectNegeriA(selectName, selectedValue, null, null);
	}

	public static String SelectNegeriA(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectNegeriA(selectName, selectedValue, disability, null);
	}

	public static String SelectNegeriA(String selectName, Long selectedValue,
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
			Vector v = DB.getNegeri();
			Tblrujnegeri f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujnegeri) v.get(i);
				if (f.getIdNegeri().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdNegeri() + ">"
				// + f.getKodNegeri() + " - " + f.getNamaNegeri()
						+ f.getNamaNegeri() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectUrusan1(String selectName) throws Exception {
		return SelectUrusan1(selectName, null, null);
	}

	public static String SelectUrusan1(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectUrusan1(selectName, selectedValue, disability, null);
	}

	public static String SelectUrusan1(String selectName, Long selectedValue,
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
			Vector v = DB.getUrusan1();
			Tblrujurusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujurusan) v.get(i);
				if (f.getIdUrusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdUrusan() + ">"
						+ f.getNamaUrusan() + " - " + f.getKodUrusan()
						// + f.getKodUrusan() + " - " + f.getNamaUrusan()
						// + f.getNamaUrusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectDaerahByIdNegeri(String idNegeriB,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectDaerahByIdNegeri(idNegeriB, selectName, selectedValue,
				disability, null);
	}

	public static String SelectDaerahByIdNegeri(String idNegeriB,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getDaerahByNegeri(idNegeriB);
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getKodDaerah() + " - " + f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectUrusanByIdSeksyen(String idSeksyenB,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		// TODO Auto-generated method stub
		return SelectUrusanByIdSeksyen(idSeksyenB, selectName, selectedValue,
				disability, null);
	}

	private static String SelectUrusanByIdSeksyen(String idSeksyenB,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=>SILA PILIH </option>\n");
			Vector v = DB.getUrusanByIdSeksyen(idSeksyenB);
			Tblrujurusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujurusan) v.get(i);
				if (f.getIdUrusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdUrusan() + ">"
						+ f.getKodUrusan() + " - " + f.getNamaUrusan()
						// + f.getNamaUrusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectDaerah1(String selectName) throws Exception {
		return SelectDaerah1(selectName, null, null);
	}

	public static String SelectDaerah1(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getDaerah1();
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getKodDaerah() + " - " + f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	public static String SelectDaerahByIdDaerah(String socDaerahB,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectDaerahByIdDaerah(socDaerahB, selectName, selectedValue,
				disability, null);
	}

	public static String SelectDaerahByIdDaerah(String socDaerahB,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getDaerahByIdDaerah(socDaerahB);
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getKodDaerah() + " - " + f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectSuburusanByIdSuburusan(String socSuburusanB,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectSuburusanByIdSuburusan(socSuburusanB, selectName,
				selectedValue, disability, null);
	}

	public static String SelectSuburusanByIdSuburusan(String socSuburusanB,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getSuburusanByIdSuburusan(socSuburusanB);
			Tblrujsuburusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSuburusan() + ">"
						+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
						+ "</option>\n");
			}
			sb.append("</select");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectSubUrusan1(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectSubUrusan1(selectName, selectedValue, disability, null);
	}

	public static String SelectSubUrusan1(String selectName,
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
			Vector v = DB.getSubUrusan();
			Tblrujsuburusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSuburusan() + ">"
						+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
						// + f.getNamaUrusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectSubSubUrusan(String selectName,
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
			Vector v = DB.getSubSubUrusan();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_SubSuburusan").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id_SubSuburusan")
						+ ">" + h.get("kod_Suburusan") + "/"
						+ h.get("kod_SubSuburusan") + "-"
						+ h.get("nama_SubSuburusan") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectSubSuburusanByIdSubUrusan(String idSuburusan,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=>SILA PILIH </option>\n");
			Vector v = DB.getSubSubUrusanByIdSubUrusan(idSuburusan);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_SUBSUBURUSAN").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_SUBSUBURUSAN")
						+ ">" + h.get("KOD_SUBSUBURUSAN") + " - "
						+ h.get("NAMA_SUBSUBURUSAN") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectSubSubSuburusanByIdSubSubUrusan(
			String idSubSuburusan, String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=>SILA PILIH </option>\n");
			Vector v = DB.getSubSubSubUrusanByIdSubSubUrusan(idSubSuburusan);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_SUBSUBSUBURUSAN").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ h.get("ID_SUBSUBSUBURUSAN") + ">"
						+ h.get("KOD_SUBSUBSUBURUSAN") + " - "
						+ h.get("NAMA_SUBSUBSUBURUSAN") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static Object SelectSeksyenPAR(String selectName,
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
			Vector v = DB.getSeksyen();
			Tblrujseksyen f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujseksyen) v.get(i);
				if (f.getIdSeksyen().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSeksyen() + ">"
						+ f.getKodSeksyen() + " - " + f.getNamaSeksyen()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// public static String SelectPAR(String selectName) throws Exception {
	// return SelectPAR(selectName, null, null);
	// }
	//
	// private static String SelectPAR(String selectName, Long selectedValue,
	// String disability) throws Exception {
	// return ;
	//
	// }

	public static String SelectPARByIdSeksyen(String idSeksyen,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {

			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPARByIdSeksyen(idSeksyen);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEGAWAI").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_PEGAWAI")
						+ ">" + h.get("NAMA_PEGAWAI") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectPARById(String socPAR, String selectName,
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
			Vector v = DB.getPARById(socPAR);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEGAWAI").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_PEGAWAI")
						+ ">" + h.get("NAMA_PEGAWAI") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectSubSubUrusanByIdSuburusan(
			String selectSubSubUrusanC, String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=>SILA PILIH </option>\n");
			Vector v = DB.getSubSubUrusanByIdSubUrusan(selectSubSubUrusanC);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_SUBSUBURUSAN").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_SUBSUBURUSAN")
						+ ">" + h.get("KOD_SUBSUBURUSAN")
						+ h.get("NAMA_SUBSUBURUSAN") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectSubSubSubUrusanByIdSubSuburusan(
			String selectSubUrusanC, String selectName, Long selectedValue,
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
			Vector v = DB.getSubSubUrusanByIdSubUrusan(selectSubUrusanC);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_SUBSUBSUBURUSAN").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ h.get("ID_SUBSUBSUBURUSAN") + ">"
						+ h.get("KOD_SUBSUBSUBURUSAN")
						+ h.get("NAMA_SUBSUBSUBURUSAN") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectSubSuburusan(String selectName,
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
			Vector v = DB.getSubSubUrusan();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_SubSuburusan").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id_SubSuburusan")
						+ ">" + h.get("kod_SubSuburusan")
						+ h.get("nama_SubSuburusan") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectSubSubSuburusan(String selectName,
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
			Vector v = DB.getSubSubSubUrusan();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_SubSubSuburusan").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ h.get("id_SubSubSuburusan") + ">"
						+ h.get("kod_SubSubSuburusan") + "-"
						+ h.get("nama_SubSubSuburusan") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectUrusanSeksyen(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectUrusanSeksyen(selectName, selectedValue, disability, null);
	}

	public static String SelectUrusanSeksyen(String selectName,
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
			Vector v = DB.getUrusanSeksyen();
			Tblrujurusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujurusan) v.get(i);
				if (f.getIdUrusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdUrusan() + ">"
						+ f.getNamaUrusan() + " - " + f.getKodUrusan()
						// + f.getKodUrusan() + " - " + f.getNamaUrusan()
						// + f.getNamaUrusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectUrusanArkib(String selectName,
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
			Vector v = DB.getUrusanArkib();
			Tblrujurusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujurusan) v.get(i);
				if (f.getIdUrusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdUrusan() + ">"
						+ f.getNamaUrusan() + " - " + f.getKodUrusan()
						// + f.getKodUrusan() + " - " + f.getNamaUrusan()
						// + f.getNamaUrusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectUrusanNegeri(String selectName,
			Long selectedValue, String id_negeri, String disability)
			throws Exception {
		return SelectUrusanNegeri(selectName, selectedValue, id_negeri,
				disability, null);
	}

	public static String SelectUrusanNegeri(String selectName,
			Long selectedValue, String id_negeri, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {

			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getUrusanNegeri(id_negeri);
			Tblrujurusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujurusan) v.get(i);
				if (f.getIdUrusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdUrusan() + ">"
						+ f.getNamaUrusan() + " - " + f.getKodUrusan()
						// + f.getKodUrusan() + " - " + f.getNamaUrusan()
						// + f.getNamaUrusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// public static String SelectUnitByNegeri(String id_Negeri,String
	// selectName, Long selectedValue, String disability) throws Exception {
	// return SelectUnitByNegeri(id_Negeri, selectName, selectedValue,
	// disability, null);
	// }
	//
	// private static String SelectUnitByNegeri(String id_Negeri,String
	// selectName, Long selectedValue,
	// String disability, String jsFunction) throws Exception {
	// StringBuffer sb = new StringBuffer("");
	// try {
	// sb.append("<select name='" + selectName + "'");
	// if (disability != null)
	// sb.append(disability);
	// if (jsFunction != null)
	// sb.append(jsFunction);
	// sb.append(" > ");
	// sb.append("<option value=>SILA PILIH</option>\n");
	// Vector v = DB.getUnitByNegeri(id_Negeri);
	// Tblrujunit f = null;
	// String s = "";
	// for (int i = 0; i < v.size(); i++) {
	// f = (Tblrujunit) v.get(i);
	// if (f.getIdUnit().equals(selectedValue)) {
	// s = "selected";
	// } else {
	// s = "";
	// }
	// sb.append("<option " + s + " value=" + f.getIdUnit() + ">"
	// //+ f.getKodDaerah() + " - " + f.getNamaDaerah()
	// + f.getNamaUnit()
	// + "</option>\n");
	// }
	// sb.append("</select>");
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// throw ex;
	// }
	// return sb.toString();
	// }
	// ---- end by Rizuan - Select file status for register file --------

	// --------------------------------------
	// k-mie, 19/05/2010
	// guna kat view PendaftaranFail.java, sbb Puan Aminah taknak ada kod urusan
	// sebelum keterangan urusan
	public static String SelectUrusanPFDFail(String selectName)
			throws Exception {
		return SelectUrusanPFDFail(selectName, null, null);
	}

	public static String SelectUrusanPFDFail(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectUrusanPFDFail(selectName, selectedValue, disability, null);
	}

	public static String SelectUrusanPFDFail(String selectName,
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
			Vector v = DB.getUrusan();
			Tblrujurusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujurusan) v.get(i);
				if (f.getIdUrusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdUrusan() + ">"
						+ f.getNamaUrusan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectTarafKeselamatanPFDFail(String selectName)
			throws Exception {
		return SelectTarafKeselamatanPFDFail(selectName, null, null, null);
	}

	public static String SelectTarafKeselamatanPFDFail(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectTarafKeselamatanPFDFail(selectName, selectedValue,
				disability, null);
	}

	public static String SelectTarafKeselamatanPFDFail(String selectName,
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
			Vector v = DB.getTarafKeselamatan();
			Tblrujpfdtarafkeselamatan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpfdtarafkeselamatan) v.get(i);
				if (f.getIdTarafKeselamatan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ f.getIdTarafKeselamatan() + ">"
						+ f.getTarafKeselamatan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectStatusTindakan(String selectName)
			throws Exception {
		return SelectStatusTindakan(selectName, null, null);
	}

	public static String SelectStatusTindakan(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusTindakan();
			Hashtable f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Hashtable) v.get(i);
				if (f.get("id_statustindakan").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ f.get("id_statustindakan") + ">"
						+ f.get("status_tindakan") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// --------------------------------------
	// public static String SelectStatusFailMesyuarat(String selectName,
	// Long selectedValue, String disability) throws Exception {
	// StringBuffer sb = new StringBuffer("");
	// try {
	// sb.append("<select name='" + selectName + "' " + disability + "> ");
	// sb.append("<option value=>SILA PILIH</option>\n");
	// Vector v = DB.getStatusFailMesyuarat();
	// Tblrujstatus f = null;
	// String s = "";
	// for (int i = 0; i < v.size(); i++) {
	// f = (Tblrujstatus) v.get(i);
	// if (f.getIdStatus().equals(selectedValue)) {
	// s = "selected";
	// } else {
	// s = "";
	// }
	// sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
	// + f.getKeterangan() + "</option>\n");
	// }
	// sb.append("</select>");
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// throw ex;
	// }
	//
	// return sb.toString();
	// }

	// ** Mukim by Daerah without kod

	public static String SelectMukimNoKodByDaerah(String iddaerah,
			String selectName) throws Exception {
		return SelectMukimNoKodByDaerah(iddaerah, selectName, null, null, null);
	}

	public static String SelectMukimNoKodByDaerah(String iddaerah,
			String selectName, String jsFunction) throws Exception {
		return SelectMukimNoKodByDaerah(iddaerah, selectName, null, null,
				jsFunction);
	}

	public static String SelectMukimNoKodByDaerah(String iddaerah,
			String selectName, Long selectedValue, String disability)
			throws Exception {
		return SelectMukimNoKodByDaerah(iddaerah, selectName, selectedValue,
				disability, null);
	}

	public static String SelectMukimNoKodByDaerah(String iddaerah,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getMukimByDaerah(iddaerah);
			Tblrujmukim f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujmukim) v.get(i);
				if (f.getIdMukim().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdMukim() + ">"
						+ f.getNamaMukim().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}// close select mukim by daerah without kod

	// GET BULAN (PHP) - ELLY 24062010
	public static String SelectBulan(String selectName) throws Exception {
		return SelectBulan(selectName, null, null, null);

	}

	public static String SelectBulan(String selectName, String jsFunction)
			throws Exception {
		return SelectBulan(selectName, null, null, jsFunction);

	}

	public static String SelectBulan(String selectName, Long selectedValue)
			throws Exception {

		return SelectBulan(selectName, selectedValue, null, null);

	}

	public static String SelectBulan(String selectName, Long selectedValue,

	String disability) throws Exception {

		return SelectBulan(selectName, selectedValue, disability, null);

	}

	public static String SelectBulan(String selectName, Long selectedValue,
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
			Vector v = DB.getBulan();

			Tblrujbulan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujbulan) v.get(i);
				if (f.getIdBulan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdBulan() + ">"
						+ f.getNamaBulan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String selectPegawaiUnitPelepasan(String selectName,
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
			Vector v = DB.getPegawaiUnitPelepasan();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEGAWAI").equals(selectedValue.toString())) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_PEGAWAI")
						+ ">" + h.get("NAMA_PEGAWAI") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}
	
	public static String selectPegawaiUnitPenyewaanByNegeri(String idNegeri, String selectName,
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
			Vector v = DB.getPegawaiUnitPenyewaanByNegeri(idNegeri);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEGAWAI").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_PEGAWAI")
						+ ">" + h.get("NAMA_PEGAWAI") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String selectPegawaiUnitAPB(String selectName,
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
			Vector v = DB.getPegawaiUnitAPB();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEGAWAI").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_PEGAWAI")
						+ ">" + h.get("NAMA_PEGAWAI") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}
	
	public static String selectPegawaiUnitPenguatkuasaan(String selectName,
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
			Vector v = DB.getPegawaiUnitPenguatkuasaan();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEGAWAI").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_PEGAWAI")
						+ ">" + h.get("NAMA_PEGAWAI") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectLuasKegunaan(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (selectedValue.intValue() == 1) {
				sb.append("<option selected value=1> K - KESELURUHAN</option>\n");
			} else {
				sb.append("<option value=1> K - KESELURUHAN</option>\n");
			}
			if (selectedValue.intValue() == 2) {
				sb.append("<option selected value=2> S - SEBAHAGIAN</option>\n");
			} else {
				sb.append("<option value=2> S - SEBAHAGIAN</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectJenisProjek(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (selectedValue.intValue() == 1) {
				sb.append("<option selected value=1> P - PERSEKUTUAN</option>\n");
			} else {
				sb.append("<option value=1> P - PERSEKUTUAN</option>\n");
			}
			if (selectedValue.intValue() == 2) {
				sb.append("<option selected value=2> N - NEGERI</option>\n");
			} else {
				sb.append("<option value=2> N - NEGERI</option>\n");
			}
			if (selectedValue.intValue() == 3) {
				sb.append("<option selected value=3> I - INDIVIDU</option>\n");
			} else {
				sb.append("<option value=3> I - INDIVIDU</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPBbyHakmilikWithIdAward(String idhakmilik,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPBbyHakmilikWithIdAward(idhakmilik);
			Tblpptpihakberkepentingan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpptpihakberkepentingan) v.get(i);
				if (f.getIdPihakberkepentingan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ f.getIdPihakberkepentingan() + ">"
						+ f.getNamaPb().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectPBbyHakmilikWithPenerimaanBorangH(
			String idhakmilik, String selectName, Long selectedValue,
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
			Vector v = DB.getPBbyHakmilikWithPenerimaanBorangH(idhakmilik);
			Tblpptpihakberkepentingan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpptpihakberkepentingan) v.get(i);
				if (f.getIdPihakberkepentingan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ f.getIdPihakberkepentingan() + ">"
						+ f.getNamaPb().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	public static String SelectKategoriPemohonPenawaran(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getKategoriPemohonPenawaran();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	public static String SelectKategoriPemohonPNW(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getKategoriPemohonPNW();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	

	public static String SelectKategoriPemohonPelepasan(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getKategoriPemohonPelepasan();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// author : hilda
	// date : 05/08/2010
	// PBT - Pihak Berkuasa Tempatan
	public static String getPejabatPBT(String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPejabatPBT();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// author : hilda
	// date : 05/08/2010
	// PBT - Pihak Berkuasa Tempatan by negeri
	public static String SelectPejabatPBTByNegeri(String idnegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPejabatPBTByNegeri(idnegeri);
			Tblrujpejabat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpejabat) v.get(i);
				if (f.getIdPejabat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPejabat() + ">"
						+ f.getKodPejabat() + " - "
						+ f.getNamaPejabat().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// added by hilda
	// 10/08/2010
	public static String SelectLuasAPB(String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' id='"+selectName +"'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			if(selectedValue==null){
				sb.append("<option value="+0+" selected >SILA PILIH</option>\n");
			}else{
				sb.append("<option value="+0+">SILA PILIH</option>\n");
			}
			
			Vector v = DB.getLuasAPB();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// 20092010 Jenis Bayaran FPX -Syah
	public static String SelectJenisBayaranFPX(String selectName,
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
			Vector v = DB.getJenisBayaranFPX();
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
						+ ">" + f.getKodJenisBayar() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// 23092010 Pilihan sub urusan PPK -Syah
	public static String SelectSuburusanPPK(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectSuburusanPPK(selectName, selectedValue, disability, null);
	}

	public static String SelectSuburusanPPK(String selectName,
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
			Vector v = DB.getSuburusanPPK();
			Tblrujsuburusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSuburusan() + ">"
						+ f.getKodSuburusan() + " - "
						+ f.getNamaSuburusan().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// 23092010 Pilihan unit dengan nama daerah PPK -Syah
	public static String SelectUnitJKPTGWithDaerah(String selectName,
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
			Vector v = DB.getUnitJKPTGWithDaerah();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEJABATJKPTG").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_PEJABATJKPTG")
						+ ">" + h.get("NAMA_PEJABAT") + " "
						+ h.get("NAMA_DAERAH") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}// close

	// 23092010 Pilihan unit dengan nama daerah by negeri PPK -Syah
	public static String SelectUnitJKPTGWithDaerahbyIdNegeri(String id_negeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {

			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getUnitJKPTGWithDaerahByNegeri(id_negeri);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEJABATJKPTG").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_PEJABATJKPTG")
						+ ">" + h.get("NAMA_PEJABAT") + " "
						+ h.get("NAMA_DAERAH") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}// close

	// --Unit Laporan @dayah

	public static String SelectUnit(String selectName) throws Exception {
		return SelectUnit(selectName, null, null, null);
	}

	public static String SelectUnit(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectUnit(selectName, selectedValue, disability, null);
	}

	public static String SelectUnit(String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");

		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getUnitJKPTGByIdPjbt();
			Tblrujpejabatjkptg f = null;
			String s = "";
			// String getketeranganunitpsk = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpejabatjkptg) v.get(i);
				if (f.getIdPejabatjkptg().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				// getketeranganunitpsk = f.getketeranganunitpsk();
				// if (getketeranganunitpsk != null) {
				// getketeranganunitpsk = getketeranganunitpsk.toUpperCase();
				// }
				sb.append("<option " + s + " value=" + f.getIdPejabatjkptg()
						+ ">" + f.getKodJkptg() == null ? "" : f.getKodJkptg()
						+ " - " + f.getNamaPejabat().toUpperCase()
						+ "</option>\n");
			}

			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// Unit by Id_Pejabatjkptg @ dayah

	public static String SelectUnitById_Pjbt(String selectName)
			throws Exception {
		return SelectUnitById_Pjbt(null, selectName, null, null, null);
	}

	public static String SelectUnitById_Pjbt(String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectUnitById_Pjbt(null, selectName, selectedValue, disability,
				null);
	}

	public static String SelectUnitById_Pjbt(String idnegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {

			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getUnitJKPTGByIdNegeri(idnegeri);
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
						+ ">" + f.getNamaPejabat().toUpperCase()
						+ "</option>\n");
			}

			sb.append("</select>");

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectUnitByIdNegeri(String idnegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {

			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getUnitJKPTGByIdNegeriUnit(idnegeri);
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
						+ ">" + f.getNamaPejabat().toUpperCase()
						+ "</option>\n");
			}

			sb.append("</select>");

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ** Unit mengikut negeri @dayah
	public static String SelectUnitByNegeri(String idnegeri, String selectName)
			throws Exception {
		return SelectUnitByNegeri(idnegeri, selectName, null, null, null);
	}

	public static String SelectUnitByNegeri(String idnegeri, String selectName,
			String jsFunction) throws Exception {
		return SelectUnitByNegeri(idnegeri, selectName, null, null, jsFunction);
	}

	public static String SelectUnitByNegeri(String idnegeri, String selectName,
			Long selectedValue, String disability) throws Exception {
		return SelectUnitByNegeri(idnegeri, selectName, selectedValue,
				disability, null);
	}

	public static String SelectUnitByNegeri(String idnegeri, String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");

			Vector v = DB.getTempatBicaraByNegeri(idnegeri);
			Tblppkrujunit f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppkrujunit) v.get(i);
				if (f.getidJkptg().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getidJkptg() + ">"
						+ f.getnamapejabat().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// ADDED BY DAYAH 10/01/2011

	public static String SelectPegawaiPengendaliByDaerah(String id_jkptg,
			String id_daerah, String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");

			Vector v = DB.getPegawaiPengendaliByDaerah(id_jkptg, id_daerah);
			Tblppkrujunit f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblppkrujunit) v.get(i);
				if (f.getidunitpk().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getidunitpk() + ">"
						+ f.getnamapegawai() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	public static String SelectPejabatJPPHPelepasan(String idPermohonan,
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
			Vector v = DB.getPejabatJPPHPelepasan(idPermohonan);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	// 04112010 pilihan Kod daerah mengikut flag
	// 1 = sebelum 01/10/2010
	// 2 = selepas 01/10/2010
	public static String SelectDaerahByNegeriKOD(String flagJenisKod,
			String idnegeri, String selectName, Long selectedValue,
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
			Vector v = DB.getDaerahByNegeriKOD(idnegeri, flagJenisKod);
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getKodDaerah() + " - " + f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// 04112010 pilihan Kod daerah mengikut flag
	// 1 = sebelum 01/10/2010
	// 2 = selepas 01/10/2010
	public static String SelectDaerahKOD(String flagJenisKod,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getDaerahKOD(flagJenisKod);
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getKodDaerah() + " - " + f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectStatusPenguatkuasan(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusPenguatkuasaan();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("kod") + " - " + h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// add 11/11/2010
	public static String SelectDaerahLaporan(String selectName,
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
			sb.append("<option value='000'>KESELURUHAN NEGERI</option>\n");
			Vector v = DB.getDaerah();
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getKodDaerah() + " - " + f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// add 11/11/2010
	public static String SelectDaerahLaporanByIdPejabatJKPTG(
			String id_pejabatjkptg, String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value='000'>KESELURUHAN NEGERI</option>\n");
			Vector v = DB.getDaerahLaporanByIdPejabatJKPTG(id_pejabatjkptg);
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getKodDaerah() + " - " + f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	// edityati 7/2/2018
	public static String SelectDaerahLaporanByIdPejabatJKPTGPPT(
			String id_pejabatjkptg, String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value='000'>KESELURUHAN NEGERI</option>\n");
			Vector v = DB.getDaerahLaporanByIdPejabatJKPTGPPT(id_pejabatjkptg);
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getKodDaerah() + " - " + f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// add 11/11/2010 edited by yati
	public static String SelectPejabatJKPTGLaporan(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH PEJABAT</option>\n");
			Vector v = DB.getPejabatJKPTGLaporan();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id_negeri").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id_negeri")
						+ ">" + h.get("nama_negeri") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}


	// [PPT] Untuk KL sahaja 13042011
	public static String SelectNegeriMampuKL(String selectName,
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
			Vector v = DB.getNegeriMampuKL();
			Tblrujnegeri f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujnegeri) v.get(i);
				if (f.getIdNegeri().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdNegeri() + ">"
						+ f.getKodMampu() + " - " + f.getNamaNegeri()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// select pengarah dan bekas pengarah 14/09/2011
	public static String SelectPengarahDanBekasPengarah(String idNegeri,
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

			Vector v = DB.getPengarahDanBekasPengarah(idNegeri);
			Users f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Users) v.get(i);
				if (f.getUserId().equals(selectedValue) || v.size() == 1) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getUserId() + ">"
						+ f.getUserName().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// select penolong pengarah 27022012
	public static String SelectPenolongPengarahPPT(String idNegeri,
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

			Vector v = DB.getPenolongPengarahPPT(idNegeri);
			Users f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Users) v.get(i);
				if (f.getUserId().equals(selectedValue) || v.size() == 1) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getUserId() + ">"
						+ f.getUserName().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// SelectPejabat arb by negeri
	public static String selectPejabatARBByNegeri(String id_negeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {

			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPejabatARBByNegeri(id_negeri);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEJABAT").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}

				if (v.size() > 1) {
					sb.append("<option " + s + " value=" + h.get("ID_PEJABAT")
							+ ">" + h.get("KOD_PEJABAT") + " - "
							+ h.get("NAMA_PEJABAT") + " "
							+ h.get("NAMA_DAERAH") + "</option>\n");
				} else {
					sb.append("<option " + s + " value=" + h.get("ID_PEJABAT")
							+ ">" + h.get("KOD_PEJABAT") + " - "
							+ h.get("NAMA_PEJABAT") + " "
							+ h.get("NAMA_NEGERI") + "</option>\n");
				}

			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}// close SelectPejabat arb by negeri

	public static String selectPejabatARB(String selectName,
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
			Vector v = DB.getPejabatARB();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEJABAT").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_PEJABAT")
						+ ">" + h.get("KOD_PEJABAT") + " - "
						+ h.get("NAMA_PEJABAT") + " " + h.get("NAMA_DAERAH")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	// ADD BY PEJE
	public static String SelectKategoriMonitoring(String selectName,
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
			Vector v = DB.getKategoriMonitoring();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_KATEGORI").toString().trim()
						.equals(selectedValue.toString().trim())) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_KATEGORI")
						+ ">" + h.get("KETERANGAN") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	// ADD BY PEJE
	public static String SelectStatusMonitoring(String selectName,
			String selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if ("Y".equals(selectedValue)) {
				sb.append("<option selected value='Y'>AKTIF</option>\n");
			} else {
				sb.append("<option value='Y'>AKTIF</option>\n");
			}
			if ("T".equals(selectedValue)) {
				sb.append("<option selected value='T'>LUPUT</option>\n");
			} else {
				sb.append("<option value='T'>LUPUT</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectjawatanKJP(String selectName) throws Exception {
		return SelectjawatanKJP(selectName, null, null, null);
	}

	public static String SelectjawatanKJP(String selectName, Long selectedValue)
			throws Exception {
		return SelectjawatanKJP(selectName, selectedValue, null, null);
	}

	public static String SelectjawatanKJP(String selectName,
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
			Vector v = DB.getJawatanKJP();
			Tblrujjawatan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjawatan) v.get(i);
				if (f.getIdJawatan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJawatan() + ">"
						+ f.getKodJawatan() + " - "
						+ f.getKeterangan().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPBbyHakmilikUnder18(String idhakmilik,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPBbyHakmilikUnder18(idhakmilik);
			Tblpptpihakberkepentingan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpptpihakberkepentingan) v.get(i);
				if (f.getIdPihakberkepentingan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value="
						+ f.getIdPihakberkepentingan() + ">"
						+ f.getNamaPb().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	// select pegawai ppt by negeri 22/03/2010
	public static String SelectPPengarahPPTByNegeri(String idNegeri,
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

			Vector v = DB.getSenaraiPPengarahPPTByNegeri(idNegeri);
			Users f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Users) v.get(i);
				if (f.getUserId().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getUserId() + ">"
						+ f.getUserName().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	// select pegawai ppt by negeri 22/03/2010
	public static String SelectPegawaiPPTByNegeriExceptPPdanPgh(
			String idNegeri, String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");

			Vector v = DB.getSenaraiPegawaiPPTByNegeriExceptPPdanPgh(idNegeri);
			Users f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Users) v.get(i);
				if (f.getUserId().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getUserId() + ">"
						+ f.getUserName().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	public static String SelectUsiaABT(String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (selectedValue.intValue() == 1) {
				sb.append("<option selected value=1> HINGGA ENAM (6) BULAN</option>\n");
			} else {
				sb.append("<option value=1> HINGGA ENAM (6) BULAN</option>\n");
			}
			if (selectedValue.intValue() == 2) {
				sb.append("<option selected value=2> LEBIH DARI ENAM (6) BULAN HINGGA DUA BELAS (12) BULAN</option>\n");
			} else {
				sb.append("<option value=2> LEBIH DARI ENAM (6) BULAN HINGGA DUA BELAS (12) BULAN</option>\n");
			}
			if (selectedValue.intValue() == 3) {
				sb.append("<option selected value=3> LEBIH DARI DUA BELAS (12) BULAN HINGGA TIGA PULUH ENAM (36) BULAN</option>\n");
			} else {
				sb.append("<option value=3> LEBIH DARI DUA BELAS (12) BULAN HINGGA TIGA PULUH ENAM (36) BULAN</option>\n");
			}
			if (selectedValue.intValue() == 4) {
				sb.append("<option selected value=4> LEBIH DARI TIGA PULUH ENAM (36) BULAN HINGGA TUJUH PULUH DUA (72) BULAN</option>\n");
			} else {
				sb.append("<option value=4> LEBIH DARI TIGA PULUH ENAM (36) BULAN HINGGA TUJUH PULUH DUA (72) BULAN</option>\n");
			}
			if (selectedValue.intValue() == 5) {
				sb.append("<option selected value=5> LEBIH DARI TUJUH PULUH DUA (72) BULAN</option>\n");
			} else {
				sb.append("<option value=5> LEBIH DARI TUJUH PULUH DUA (72) BULAN</option>\n");
			}
			if (selectedValue.intValue() == 6) {
				sb.append("<option selected value=6> KESELURUHAN</option>\n");
			} else {
				sb.append("<option value=6> KESELURUHAN</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectPYWPenolongPegawaiTanahHQ(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getPYWPenolongPegawaiTanahHQ();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEGAWAI").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_PEGAWAI") + ">"
						+ h.get("NAMA_PEGAWAI") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	// ADD BY PEJE
	public static String SelectStatusAktifTidak(String selectName,
			String selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if ("Y".equals(selectedValue)) {
				sb.append("<option selected value='Y'>AKTIF</option>\n");
			} else {
				sb.append("<option value='Y'>AKTIF</option>\n");
			}
			if ("T".equals(selectedValue)) {
				sb.append("<option selected value='T'>TIDAK AKTIF</option>\n");
			} else {
				sb.append("<option value='T'>TIDAK AKTIF</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	//added by Asna
	
	public static String selectPegawaiUnitHasil(String selectName,
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
			Vector v = DB.getPegawaiUnitHasil();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEGAWAI").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_PEGAWAI")
						+ ">" + h.get("NAMA_PEGAWAI") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}
	
	public static String selectNumberRange(String selectName, Integer selectedValue, String disability, String jsFunction, Integer rangefrom, Integer rangeTo) throws Exception {
		
		StringBuffer sb = new StringBuffer("");
		
		try {

			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");

			String s = "";
			for (int i = rangefrom; i <= rangeTo; i++) {
				if (selectedValue != null && i == selectedValue) {
					s = "selected";
				} else {
					s = "";
				}
				
				sb.append("<option " + s + " value=" + i
						+ ">" + i + "</option>\n");
				
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}
	
	public static String SelectPenolongDanPengarahHTP(String selectName, Long selectedValue, String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");
			
			Vector v = DB.getSenaraiPenolongDanPengarahHTP();
			Users f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Users) v.get(i);
				if (f.getUserId().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getUserId() + ">"
						+ " - " + f.getUserName().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	
	public static String SelectStatusAPB(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getStatusAPB();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("keterangan")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectLuasKjpRekod(String selectName, Long selectedValue,
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
			Vector v = DB.getLuasKjpRekod();
			Tblrujluas f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujluas) v.get(i);
				if (f.getIdLuas().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdLuas() + ">"
						+ f.getKodLuas() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	//add by Asna
	public static String SelectModBayaran(String selectName,
			String selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if ("P".equals(selectedValue)) {
				sb.append("<option selected value='P'>POS</option>\n");
			} else {
				sb.append("<option value='P'>POS</option>\n");
			}
			if ("K".equals(selectedValue)) {
				sb.append("<option selected value='K'>KAUNTER</option>\n");
			} else {
				sb.append("<option value='K'>KAUNTER</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	
	// close class
	public static String selectNamaPejabat(String selectName,
	Long selectedValue, String disability, String jsFunction, String noFail)	
	
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
			Vector v = DB.getNamaPejabat(noFail);
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_PEJABAT").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_PEJABAT")+ ">" + h.get("NAMA_PEJABAT")+ " </option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		
		
		return sb.toString();
	}
	
	public static String selectPejabatByIdFailAndIdJenisPejabat(String selectName,
			Long selectedValue, String idFail, String idJenisPejabat, String disability, String jsFunction)
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
			Vector v = DB.getNamaPejabatByIdJenisPejabat(idJenisPejabat,idFail,"");
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				Long idPej = null;
				if(h.get("idPejabat")!=null)
					idPej = Long.parseLong(h.get("idPejabat").toString());
					
				if (idPej.equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("idPejabat")+ ">" + h.get("namaPejabat")+ " </option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static Vector selectAlamatByIdPejabat(String idPejabat) throws Exception {
		Vector v = DB.getNamaPejabatByIdJenisPejabat("", "", idPejabat);
		return v;
	}

	public static String SelectUrusanKJPOnline(String selectName,
			Long selectedValue, String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {

			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getUrusanKJPOnline();
			Tblrujurusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujurusan) v.get(i);
				if (f.getIdUrusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdUrusan() + ">"
						+ f.getKodUrusan() + " - " + f.getNamaUrusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectSuburusanKJPOnline(String selectName,
			Long selectedValue, String disability, String jsFunction, Long parentValue) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {

			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getSuburusanKJPOnline(parentValue);
			Tblrujsuburusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSuburusan() + ">"
						+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	

	//sort negeri sarawak ikut list yg diminta
	public static String SelectSortDaerahByNegeri(String idnegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = DB.getSortDaerahByNegeri(idnegeri);
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getKodDaerah() + " - " + f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}
	
	public static String SelectStatusPerjanjianSewa(String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(" " + jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			if (selectedValue.intValue() == 1) {
				sb.append("<option selected value=1>PERJANJIAN AKTIF</option>\n");
			} else {
				sb.append("<option value=1>PERJANJIAN AKTIF</option>\n");
			}
			if (selectedValue.intValue() == 2) {
				sb.append("<option selected value=2>PERJANJIAN TAMAT</option>\n");
			} else {
				sb.append("<option value=2>PERJANJIAN TAMAT</option>\n");
			}
			if (selectedValue.intValue() == 3) {
				sb.append("<option selected value=3>PERJANJIAN DITAMATKAN</option>\n");
			} else {
				sb.append("<option value=3>PERJANJIAN DITAMATKAN</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
}