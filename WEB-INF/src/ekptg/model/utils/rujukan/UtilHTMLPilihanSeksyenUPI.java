package ekptg.model.utils.rujukan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import ekptg.model.utils.IUtilHTMLPilihan;
import org.apache.log4j.Logger;
import lebah.db.Db;


public class UtilHTMLPilihanSeksyenUPI implements IUtilHTMLPilihan{

	static Logger myLog = Logger.getLogger(ekptg.model.utils.rujukan.UtilHTMLPilihanSeksyenUPI.class);
	
	public String Pilihan(String name) throws Exception {
		return Pilihan(name, null, null, null);		
	}

	public String Pilihan(String name, String jsFunction) throws Exception {
		return Pilihan(name, null, null, jsFunction);		
	}

	public String Pilihan(String name, String selectedValue,String disability) throws Exception {
		return Pilihan(name, selectedValue, disability, null);		
	}
	
	public String Pilihan(String name, String selectedValue,String disability, String jsFunction) 
		throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + name + "'");
//			if (disability != null)
//				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector<Hashtable<String,String>> v = getSeksyen(disability);

			Hashtable<String,String> f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Hashtable<String,String>) v.get(i);
				if (String.valueOf(f.get("id")).equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.get("id") + ">"
						+ f.get("nama")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}
	
	public static Vector<Hashtable<String,String>> getSeksyen(String idMukim) throws Exception {
		Db db = null;
		String sql = "select id_seksyenupi,kod_seksyenupi,nama_seksyenupi" + " from tblrujseksyenupi ";
		if(!idMukim.equals("0"))
			sql += " where id_mukim="+ idMukim;
		
		sql +=" order by nama_seksyenupi";
//		myLog.info("getJenisDokumen:sql="+sql);
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String,String>> v = new Vector<Hashtable<String,String>>();
			Hashtable<String,String> s = null;
			while (rs.next()) {
				s = new Hashtable<String,String>();
				s.put("id",rs.getString("id_seksyenupi"));
				s.put("kod",rs.getString("kod_seksyenupi"));
				s.put("nama",rs.getString("nama_seksyenupi"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
}