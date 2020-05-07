package ekptg.model.utils.rujukan;

//import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

//import ekptg.helpers.EkptgCache;
//import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujpejabat;


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
	
	public static Vector<Tblrujpejabat> getMTByPermohonan(String idPermohonan) throws Exception {
		String key = "DBPPT.getMTByPermohonan";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujpejabat>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "";
			Vector<Tblrujpejabat> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_pejabat");
				r.add("nama_pejabat");
				r.add("id_permohonan",idPermohonan);
				sql = r.getSQLSelect("VPPT_PTGPTD");
//				myLog.info("sql="+sql);
				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujpejabat>();
				Tblrujpejabat j = null;
				while (rs.next()) {
					j = new Tblrujpejabat();
					j.setIdPejabat(rs.getLong("id_pejabat"));
					j.setNamaPejabat(rs.getString("nama_pejabat"));
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
