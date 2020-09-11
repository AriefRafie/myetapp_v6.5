package ekptg.ppt.helpers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.entities.Tblintrujkategoritanah;
import ekptg.model.entities.Tblrujjenisbangunan;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujpejabat;
import ekptg.model.meps.PPT_modeldata;
import ekptg.model.ppt.FrmUPTSek8InfoTanahTerperinciBangunanData;
import ekptg.view.ppt.FrmSek8JabatanTeknikal;
import lebah.db.Db;
import lebah.db.SQLRenderer;

public class HTMLPPT {
	static Logger myLogger = Logger.getLogger(HTMLPPT.class);

	private static Db db = null;
	private static String sql = "";
	//10092020
	public static Vector<Hashtable<String,String>> getSenaraiTuanTanah() throws Exception {
		 Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("i.id_jenis_keterangantanah");
	      r.add("i.nama_keterangantanah");
  
	      sql = r.getSQLSelect("tblrujjenisketerangantanah i","i.id_jenis_keterangantanah");
	      myLogger.info("Semakan : " + sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable<String,String> h;

	      while (rs.next()) {
	    	  h = new Hashtable<String,String>();
	    	  h.put("id", rs.getString("id_jenis_keterangantanah"));
	    	  h.put("keterangan", rs.getString("nama_keterangantanah"));
	    	  list.addElement(h);
	    	  
	      }
	      
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally {
	      if (db != null){
	    	  db.close();
	      }
	    }
	    return list;
	 }
	//JENIS KETERANGAN TUAN TANAH
	
	public static String SelectTanah(String selectName, Long selectedValue,
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
			FrmUPTSek8InfoTanahTerperinciBangunanData Data = new FrmUPTSek8InfoTanahTerperinciBangunanData();
			Vector v = Data.senaraiTanah();
			Tblintrujkategoritanah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblintrujkategoritanah) v.get(i);
				if (f.getIdKategoriTanah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKategoriTanah() + ">"
						+ f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	//JENIS BANGUNAN

	public static String SelectBangunan(String selectName, Long selectedValue,
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
			FrmUPTSek8InfoTanahTerperinciBangunanData Data = new FrmUPTSek8InfoTanahTerperinciBangunanData();
			Vector v = Data.senaraiBangunan();
			Tblrujjenisbangunan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenisbangunan) v.get(i);
				if (f.getIdJenisBangunan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenisBangunan() + ">"
						+ f.getNamaBangunan() + " - " + f.getKeteranganBangunan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
//SELECT NEGERI MEP
	public static String SelectNegeri(String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		myLogger.info("SelectNegeri ::::::::::::::::::::: PPT");
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select id=\""+selectName+"\" name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			PPT_modeldata Data = new PPT_modeldata();
			Vector v = Data.getNegeri();
			
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
	
	

	// 08032018-TAMBAH JABATAN TEKNIKAL YATI
	public static String SelectJabatanTeknikalPPT(String idnegeri, 
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=>SILA PILIH</option>\n");
			//Vector v = DB.getJabatanTeknikal();
			Vector v = FrmSek8JabatanTeknikal.getJabatanTeknikal(idnegeri);
			Tblrujpejabat f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujpejabat) v.get(i);
				if (f.getIdPejabat().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdPejabat()
						+ ">" + f.getNamaPejabat() + "</option>\n");
			}
			sb.append("</select");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}



}


