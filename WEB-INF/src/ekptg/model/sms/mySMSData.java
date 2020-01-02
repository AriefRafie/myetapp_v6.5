package ekptg.model.sms;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class mySMSData {
	
	static Logger myLog = Logger.getLogger(ekptg.model.sms.mySMSData.class);
	private Db db = null;
	Vector<Hashtable<String,String>> semakPendaftaran = null;
	Vector<Hashtable<String,String>> semakPerbicaraan = null;
	Vector<Hashtable<String,String>> semakPerintah = null;
	Vector semakKodPejabat = null;
	SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private String sql = "";

	public  void semakPendaftaran(String tarikhDari, String tarikhHingga,String userId)throws Exception{
	    try {
	    	semakPendaftaran = new Vector<Hashtable<String,String>>();
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	sql = "SELECT NO_RUJSEKSYEN,NO_KP_SIMATI,STATUS_FAIL,NO_TEL,HP_NO,TARIKH_DAFTAR_FAIL FROM MAKLUMAT_PENDAFTARAN_SMS";
		    sql += " WHERE ID_DAERAHMHN IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '" + userId + "')";
		      		      
		    if(tarikhDari != null && tarikhHingga != null){
		    	if (!tarikhDari.toString().trim().equals("")){
		    		if (!tarikhHingga.toString().trim().equals("")){
		    			SimpleDateFormat day = new SimpleDateFormat("dd");
		    			SimpleDateFormat month = new SimpleDateFormat("MM");
		    			SimpleDateFormat year = new SimpleDateFormat("yyyy");
		    			sql +=" AND TO_CHAR(TARIKH_DAFTAR_FAIL,'dd') BETWEEN '" + day.format(Format.parse(tarikhDari)).toUpperCase()+ "' AND '" +day.format(Format.parse(tarikhHingga)).toUpperCase()+ "'";
		    			sql +=" AND TO_CHAR(TARIKH_DAFTAR_FAIL,'MM') BETWEEN '" + month.format(Format.parse(tarikhDari)).toUpperCase()+ "' AND '" +month.format(Format.parse(tarikhHingga)).toUpperCase()+ "'";
		    			sql +=" AND TO_CHAR(TARIKH_DAFTAR_FAIL,'yyyy') BETWEEN '" + year.format(Format.parse(tarikhDari)).toUpperCase()+ "' AND '" +year.format(Format.parse(tarikhHingga)).toUpperCase()+ "'";
				    	  
		    		}
		    	  
		    	}
		      
		    }
		    myLog.info("sql="+sql);
		    ResultSet rs = stmt.executeQuery(sql);  
		    Hashtable<String,String> h;
		    int bil = 1;
		    int count = 0;
		    while (rs.next()) {
		    	h = new Hashtable<String,String>();
		    	h.put("bil", String.valueOf(bil));
		    	h.put("NO_RUJSEKSYEN", rs.getString("NO_RUJSEKSYEN")==null?"":rs.getString("NO_RUJSEKSYEN"));
		    	h.put("NO_KP_SIMATI", rs.getString("NO_KP_SIMATI")==null?"":rs.getString("NO_KP_SIMATI"));
		    	h.put("STATUS_FAIL", rs.getString("STATUS_FAIL")==null?"":rs.getString("STATUS_FAIL"));
		    	h.put("NO_TEL", rs.getString("NO_TEL")==null?"":rs.getString("NO_TEL"));
		    	h.put("HP_NO", rs.getString("HP_NO")==null?"":rs.getString("HP_NO"));
		    	h.put("TARIKH_DAFTAR_FAIL", rs.getString("TARIKH_DAFTAR_FAIL")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
		    	semakPendaftaran.addElement(h);
		    	bil++;
		    	count++;
		    	  
		    }
		    if(count == 0){
		    	h = new Hashtable<String,String>();
		    	h.put("bil", String.valueOf(bil));
		    	h.put("NO_RUJSEKSYEN", "Tiada rekod.");
		    	h.put("NO_KP_SIMATI", "");
		    	h.put("STATUS_FAIL", "");
		    	h.put("NO_TEL", "");
		    	h.put("HP_NO", "");
		    	h.put("TARIKH_DAFTAR_FAIL", "");		    	
		    	semakPendaftaran.addElement(h);
		    	  
		    }
		     
	    } finally {
	    	if (db != null) db.close();
	    }
		      		      
	}
	public  Vector<Hashtable<String,String>> getSemakPendaftaran(){
		return semakPendaftaran;
		
	}
	
	public  void semakPerbicaraan(String tarikhDari, String tarikhHingga, String userId)throws Exception{
		Db db = null;
	    String sql = "";
	    try {
	    	  semakPerbicaraan = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();		      
		      sql = "SELECT NO_RUJSEKSYEN,TARIKH_NOTIS,TARIKH_BICARA,MASA_BICARA,TEMPAT_BICARA,NO_TEL,HP_NO FROM MAKLUMAT_PERBICARAAN_SMS";		      
		      
		      if(tarikhDari != null && tarikhHingga != null){
		    	  if (!tarikhDari.toString().trim().equals("")){
		    		  if (!tarikhHingga.toString().trim().equals("")){
		    	 
		    	  SimpleDateFormat day = new SimpleDateFormat("dd");
		    	  SimpleDateFormat month = new SimpleDateFormat("MM");
		    	  SimpleDateFormat year = new SimpleDateFormat("yyyy");
		    	  sql +=" WHERE TO_CHAR(TARIKH_BICARA,'dd') BETWEEN '" + day.format(Format.parse(tarikhDari)).toUpperCase()+ "' AND '" +day.format(Format.parse(tarikhHingga)).toUpperCase()+ "'";
		    	  sql +=" AND TO_CHAR(TARIKH_BICARA,'MM') BETWEEN '" + month.format(Format.parse(tarikhDari)).toUpperCase()+ "' AND '" +month.format(Format.parse(tarikhHingga)).toUpperCase()+ "'";
		    	  sql +=" AND TO_CHAR(TARIKH_BICARA,'yyyy') BETWEEN '" + year.format(Format.parse(tarikhDari)).toUpperCase()+ "' AND '" +year.format(Format.parse(tarikhHingga)).toUpperCase()+ "'";
		    		  }
		    	  }
		      }
		      
		      sql += " AND ID_DAERAHMHN IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '" + userId + "')";
		      
		      ResultSet rs = stmt.executeQuery(sql);  
		      myLog.info("mySMS Perbicaraan : "+sql);
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("NO_RUJSEKSYEN", rs.getString("NO_RUJSEKSYEN")==null?"":rs.getString("NO_RUJSEKSYEN"));
		    	  h.put("TARIKH_NOTIS", rs.getString("TARIKH_NOTIS")==null?"":Format.format(rs.getDate("TARIKH_NOTIS")));
		    	  h.put("TARIKH_BICARA", rs.getString("TARIKH_BICARA")==null?"":Format.format(rs.getDate("TARIKH_BICARA")));
		    	  h.put("MASA_BICARA", rs.getString("MASA_BICARA")==null?"":rs.getString("MASA_BICARA"));
		    	  h.put("TEMPAT_BICARA", rs.getString("TEMPAT_BICARA")==null?"":rs.getString("TEMPAT_BICARA"));
		    	  h.put("NO_TEL", rs.getString("NO_TEL")==null?"":rs.getString("NO_TEL"));
		    	  h.put("HP_NO", rs.getString("HP_NO")==null?"":rs.getString("HP_NO"));		    	 
		    	  semakPerbicaraan.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      if(count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("NO_RUJSEKSYEN","Tiada rekod.");
		    	  h.put("TARIKH_NOTIS", "");
		    	  h.put("TARIKH_BICARA", "");
		    	  h.put("MASA_BICARA", "");
		    	  h.put("TEMPAT_BICARA","");
		    	  h.put("NO_TEL","");
		    	  h.put("HP_NO","");
		    	  semakPerbicaraan.addElement(h);
		      }
		     
		    } finally {
		      if (db != null) db.close();
		    }
		    
	}
	
	public  Vector<Hashtable<String,String>> getSemakPerbicaraan(){
		return semakPerbicaraan;
		
	}
	
	public  void semakPerintah(String tarikhDari, String tarikhHingga,String userId)throws Exception{
		Db db = null;
	    String sql = "";
	    try {
	    	  semakPerintah = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT NO_RUJSEKSYEN,TARIKH_SELESAI,STATUS_FAIL,HP_NO FROM MAKLUMAT_PERINTAH_SMS";
		      
		      if(tarikhDari != null && tarikhHingga != null){
		    	  if (!tarikhDari.toString().trim().equals("")){
		    		  if (!tarikhHingga.toString().trim().equals("")){
		    			  SimpleDateFormat day = new SimpleDateFormat("dd");
				    	  SimpleDateFormat month = new SimpleDateFormat("MM");
				    	  SimpleDateFormat year = new SimpleDateFormat("yyyy");
				    	  sql +=" WHERE TO_CHAR(TARIKH_SELESAI,'dd') BETWEEN '" + day.format(Format.parse(tarikhDari)).toUpperCase()+ "' AND '" +day.format(Format.parse(tarikhHingga)).toUpperCase()+ "'";
				    	  sql +=" AND TO_CHAR(TARIKH_SELESAI,'MM') BETWEEN '" + month.format(Format.parse(tarikhDari)).toUpperCase()+ "' AND '" +month.format(Format.parse(tarikhHingga)).toUpperCase()+ "'";
				    	  sql +=" AND TO_CHAR(TARIKH_SELESAI,'yyyy') BETWEEN '" + year.format(Format.parse(tarikhDari)).toUpperCase()+ "' AND '" +year.format(Format.parse(tarikhHingga)).toUpperCase()+ "'";
				    		  }
				    	  }
				      }
				      
				      sql += " AND ID_DAERAHMHN IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '" + userId + "')";
				      
		      
		      ResultSet rs = stmt.executeQuery(sql);  
		      myLog.info("Mysms Perintah : "+sql);
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("NO_RUJSEKSYEN", rs.getString("NO_RUJSEKSYEN")==null?"":rs.getString("NO_RUJSEKSYEN"));
		    	  h.put("TARIKH_SELESAI", rs.getString("TARIKH_SELESAI")==null?"":Format.format(rs.getDate("TARIKH_SELESAI")));
		    	  h.put("STATUS_FAIL", rs.getString("STATUS_FAIL")==null?"":rs.getString("STATUS_FAIL"));
		    	  h.put("HP_NO", rs.getString("HP_NO")==null?"":rs.getString("HP_NO"));

		    	  semakPerintah.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      if(count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("NO_RUJSEKSYEN", "Tiada rekod");
		    	  h.put("TARIKH_SELESAI", "");
		    	  h.put("STATUS_FAIL", "");
		    	  h.put("HP_NO", "");

		    	  semakPerintah.addElement(h);
		      }
		     
		    } finally {
		      if (db != null) db.close();
		    }
		      
		      
	}
	public  Vector<Hashtable<String,String>> getSemakPerintah(){
		return semakPerintah;
		
	}
	
	public  void semakKodPejabat(String userid)throws Exception{
		Db db = null;
	    String sql = "";
	    try {
	    	  semakKodPejabat = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.ID_PEJABATJKPTG,B.KOD_JKPTG FROM USERS_INTERNAL A,TBLRUJPEJABATJKPTG B " +
		      		"WHERE A.ID_PEJABATJKPTG = B.ID_PEJABATJKPTG AND USER_ID = '"+userid+"'";
		      
		     
		      ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("ID_PEJABATJKPTG", rs.getString("ID_PEJABATJKPTG")==null?"":rs.getString("ID_PEJABATJKPTG"));
		    	  h.put("KOD_JKPTG", rs.getString("KOD_JKPTG")==null?"":rs.getString("KOD_JKPTG"));
		    	 
		    	  semakKodPejabat.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		     
		    } finally {
		      if (db != null) db.close();
		    }
		      
		      
	}
	public  Vector getSemakKodPejabat(){
		return semakKodPejabat;
		
	}
	public static void simpanmySMSHistory(Hashtable data)throws Exception {
		Db db = null;
		 String sql = "";
		 String id = "";
		 
		    try
		    {
		    	long idmySMSHistory = DB.getNextID("MYSMS_HISTORY_SEQ");
		    	String jenis_SMS = (String)data.get("jenis_SMS");
		    	String filename = (String)data.get("filename");
		    	String user_id = (String)data.get("user_id");
		    	String foldername = (String)data.get("foldername");
		    	String idpejabatjkptg = (String)data.get("idpejabatjkptg");
		    			    	
		    	 db = new Db();
			     Statement stmt = db.getStatement();
			     SQLRenderer r = new SQLRenderer();
			     r.add("id_mysms_history",idmySMSHistory);
			     r.add("jenis_sms", jenis_SMS);
			     r.add("filename", filename);
			     r.add("tarikh_masuk", r.unquote("sysdate"));
			     r.add("user_id",user_id);
			     r.add("folder_name",foldername);
			     r.add("id_pejabatjkptg",idpejabatjkptg);
			     			     
			     sql = r.getSQLInsert("MYSMS_HISTORY");
			     stmt.executeUpdate(sql);
			     
			      
		    }finally {
			      if (db != null) db.close();
		    }
		    
	}
	
	

}
