package ekptg.model.utils.rujukan;

//import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

//import ekptg.helpers.EkptgCache;
//import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujjenishakmilik;
//import ekptg.model.htp.UtilHTML;
//import ekptg.model.ppk.PPKUtilData;
//import ekptg.model.ppk.PPKUtilHTML;


public class DBPPT extends ekptg.helpers.DB{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3931596818574745533L;
	private static Logger myLog = Logger.getLogger(ekptg.model.utils.rujukan.DBPPT.class);
	//private Db db = null;
	//private String sql ="";
	//private Vector<Hashtable<String,String>> senarai = null;
	
	public static Vector<Tblrujjenishakmilik> getJenisHakmiliks(String idJenisHakmilik) throws Exception {
		String key = "DBPPT.getJenisHakmilik";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujjenishakmilik>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "";
			Vector<Tblrujjenishakmilik> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_jenishakmilik");
				r.add("kod_jenis_hakmilik");
				r.add("keterangan");
				r.add("kod_jenis_hakmilik","TDK","!=");
				sql = r.getSQLSelect("tblrujjenishakmilik", "lpad(replace(kod_jenis_hakmilik,'00','A'),100)");
//				myLog.info("sql="+sql);
				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujjenishakmilik>();
				Tblrujjenishakmilik j = null;
				while (rs.next()) {
					j = new Tblrujjenishakmilik();
					j.setIdJenishakmilik(rs.getLong("id_jenishakmilik"));
					j.setKodJenisHakmilik(rs.getString("kod_jenis_Hakmilik"));
					if (rs.getString("keterangan") == null) {
						j.setKeterangan("");
					} else {
						j.setKeterangan(rs.getString("keterangan"));
					}
					v.addElement(j);
					
				}
				myCache.put(new Element(key, v));
				return v;
				
			} finally {
				if (db != null)
					db.close();
			}
		}
		
	}	
	
	
}
