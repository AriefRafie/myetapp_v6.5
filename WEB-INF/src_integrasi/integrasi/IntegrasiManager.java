package integrasi;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class IntegrasiManager {

	static Logger myLog = Logger.getLogger(integrasi.IntegrasiManager.class);
	
	private String kataLaluan;
	private String idPengguna;
	private String sumber ;
	private String tujuan;
	private String url;
		
	public IntegrasiManager() {}
	 
	public IntegrasiManager(String kod)  throws Exception{
		getData(kod);
	}
		
	public boolean isUrlValid(String url) {
	    try {
	        URL obj = new URL(url);
	        obj.toURI();
	        return true;
	      
	    } catch (MalformedURLException e) {
	    	return false;
	    } catch (URISyntaxException e) {
	    	return false;
	      
	    }
	   
	}
	
	//Setter & Getter
	public String getKataLaluan() {
		return kataLaluan;
	}

	public void setKataLaluan(String kataLaluan) {
		this.kataLaluan = kataLaluan;
	}

	public String getIdPengguna() {
		return idPengguna;
	}

	public void setIdPengguna(String idPengguna) {
		this.idPengguna = idPengguna;
	}

	public String getSumber() {
		return sumber;
	}	
	
	public void setSumber(String sumber) {
		this.sumber = sumber;
	}

	public String geTujuan() {
		return tujuan;
	}

	public void seTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}

	  
	public Hashtable<String,String> getData(String kod) throws Exception{
	    Db db = null;
	    String sql = "";
	    Hashtable<String,String> h =null;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("WS_PASSWORD");
	      r.add("WS_USERNAME");
	      r.add("WS_SOURCE");
	      r.add("KETERANGAN");
	      r.add("WS_URL");
	      r.add("KOD",kod);
	      r.add("FLAG_AKTIF","Y");
	      sql = r.getSQLSelect("TBLINTWSCONFIG");
//	      if (kod != null) 
//	    	  sql = sql + " WHERE KOD = '" + kod + "' AND FLAG_AKTIF='Y' "; 
	      myLog.info(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      while (rs.next()) {
	    	  h = new Hashtable<String,String>();
	    	  setKataLaluan(Utils.isNull(rs.getString("WS_PASSWORD")));
	    	  setIdPengguna(Utils.isNull(rs.getString("WS_USERNAME")));
	    	  setSumber(Utils.isNull(rs.getString("WS_SOURCE")));
	    	  seTujuan(rs.getString("KETERANGAN"));
	    	  setURL(rs.getString("WS_URL"));
	    	  
	    	  h.put("kataLaluan", getKataLaluan());
	    	  h.put("idPengguna",getIdPengguna());
	    	  h.put("sumber",getSumber());
	    	  h.put("tujuan",geTujuan());
	    	  h.put("url",getURL());

	      }
	      return h;
	      
	    } catch (Exception e) {
	    	myLog.info(e.getMessage());
	    	return null;
	    }finally {
	      if (db != null) db.close();
	    }
	    
	}
	
	public static String getIdJenisHakmilik(String kodJenisHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_JENISHAKMILIK FROM TBLRUJJENISHAKMILIK WHERE KOD_JENIS_HAKMILIK = '" + kodJenisHakmilik + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_JENISHAKMILIK");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public static String getIdNegeri(String kodNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_NEGERI FROM TBLRUJNEGERI WHERE KOD_NEGERI = '" + kodNegeri + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_NEGERI");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public static String getIdDaerah(String kodNegeri, String kodDaerah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE ID_NEGERI = '" + getIdNegeri(kodNegeri) + "' AND KOD_DAERAH = '" + kodDaerah + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_DAERAH");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public static String getIdMukim(String kodNegeri, String kodDaerah, String kodMukim) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_MUKIM FROM TBLRUJMUKIM WHERE ID_DAERAH = '" + getIdDaerah(kodNegeri, kodDaerah) + "' AND KOD_MUKIM = '" + kodMukim + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_MUKIM");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public static String cleanDataString(String input) {
		String temp = input;
		if (input == null) {
			temp = "";
		} else if ("null".equalsIgnoreCase(input.trim())) {
			temp = input.trim().replaceAll("null", "");
			temp = temp.toUpperCase();
		} else {
			temp = input.trim().toUpperCase();
		}
		return temp;
	}
	
		  
}
