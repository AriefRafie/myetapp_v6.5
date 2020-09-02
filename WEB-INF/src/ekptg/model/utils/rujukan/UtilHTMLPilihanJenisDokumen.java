package ekptg.model.utils.rujukan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import ekptg.model.entities.Tblrujjenisdokumen;
import ekptg.model.entities.Tblsemakan;
import ekptg.model.utils.IUtilHTMLPilihan;
import org.apache.log4j.Logger;
import lebah.db.Db;


public class UtilHTMLPilihanJenisDokumen implements IUtilHTMLPilihan{

	static Logger myLog = Logger.getLogger(ekptg.model.utils.rujukan.UtilHTMLPilihanJenisDokumen.class);
	
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
			Vector<Tblrujjenisdokumen> v = getJenisDokumen(disability);

			Tblrujjenisdokumen f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenisdokumen) v.get(i);
				if (String.valueOf(f.getIdJenisdokumen()).equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenisdokumen() + ">"
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
	
	public static Vector<Tblrujjenisdokumen> getJenisDokumen(String idSeksyen) throws Exception {
		Db db = null;
		String sql = "select id_jenisdokumen,kod_jenis_dokumen,keterangan" + " from tblrujjenisdokumen ";
		if(!idSeksyen.equals("0"))
			sql += " where id_seksyen="+ idSeksyen;
		
		sql +=" order by keterangan";
		myLog.info("getJenisDokumen:sql="+sql);
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenisdokumen> v = new Vector<Tblrujjenisdokumen>();
			Tblrujjenisdokumen s = null;
			while (rs.next()) {
				s = new Tblrujjenisdokumen();
				s.setIdJenisdokumen(rs.getLong("id_Jenisdokumen"));
				s.setKodJenisDokumen(rs.getString("kod_Jenis_Dokumen"));
				s.setKeterangan(rs.getString("keterangan"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
}