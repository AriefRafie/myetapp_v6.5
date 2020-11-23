package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmCukaiBaucerData {
	private static Vector list = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLog = Logger.getLogger(ekptg.model.htp.FrmCukaiBaucerData.class);
	
	public static Vector getListBaucer(int idNegeri, int idPeringkatbayaran, int peringkat_bayaran)throws Exception {		
		Db db = null;
		list.clear();
		String sql = "";
		
			try {
			      db = new Db();
//			      conn = db.getConnection();
//			      conn.setAutoCommit(false);
			      
			      sql = "SELECT DISTINCT trd.nama_daerah, trd.id_daerah, thb.id_baucer, thb.no_baucer, thb.tarikh_baucer, thb.tarikh_terima, " +
						" thb.no_resit, thb.tarikh_resit, thb.amaun_baucer, thb.id_peringkatbayaran, thpb.peringkat_bayaran " +
						"FROM tblhtpbaucer thb, tblrujdaerah trd, tblrujpejabat trp, tblhtpperingkatbayaran thpb " +
						"WHERE trd.id_daerah = trp.id_daerah " +
							" AND trp.id_daerah = thb.id_daerah " +
							//" AND trp.jenis_pejabat = '02' " +
							" AND trp.id_negeri = "+ idNegeri +
							" AND thb.id_peringkatbayaran = "+ idPeringkatbayaran +
							" AND thpb.peringkat_bayaran = "+ peringkat_bayaran;
	
			      System.out.println("FrmCukaiBaucerData:::getListBaucer::sql::"+sql);
	
			      Statement stmt = db.getStatement();
			      ResultSet rs = stmt.executeQuery(sql);			     
			      Hashtable h;
		
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  if(rs.getString("nama_daerah") != null){
			    		  h.put("nama_daerah", rs.getString("nama_daerah"));
			    	  }else{
			    		  h.put("nama_daerah", "");
			    	  }
			    	  if(rs.getString("id_daerah") != null){
			    		  h.put("idDaerah", rs.getString("id_daerah"));
			    	  }else{
			    		  h.put("idDaerah", "0");
			    	  }
			    	  if(rs.getString("id_baucer") != null){
			    		  h.put("id_baucer", rs.getString("id_baucer"));
			    	  }else{
			    		  h.put("id_baucer", "0");
			    	  }
			    	  if(rs.getString("no_baucer") != null){
			    		  h.put("no_baucer", rs.getString("no_baucer"));
			    	  }else{
			    		  h.put("no_baucer", "");
			    	  }
			    	  if(rs.getString("id_peringkatbayaran") != null){
			    		  h.put("idPeringkatbayaran", rs.getString("id_peringkatbayaran"));
			    	  }else{
			    		  h.put("idPeringkatbayaran", "0");
			    	  }
			    	  if(rs.getString("peringkat_bayaran") != null){
			    		  h.put("peringkat_bayaran", rs.getString("peringkat_bayaran"));
			    	  }else{
			    		  h.put("peringkat_bayaran", "0");
			    	  }
			    	  if(rs.getString("tarikh_baucer") != null){
			    		  h.put("tkh_baucer", Format.format(rs.getDate("tarikh_baucer")));
			    	  }else{
			    		  h.put("tkh_baucer", "");
			    	  }
			    	  if(rs.getString("no_resit") != null){
			    		  h.put("no_resit", rs.getString("no_resit"));
			    	  }else{
			    		  h.put("no_resit", "");
			    	  }
			    	  if(rs.getString("tarikh_resit") != null){
			    		  h.put("tkh_resit", Format.format(rs.getDate("tarikh_resit")));
			    	  }else{
			    		  h.put("tkh_resit", "");
			    	  }
			    	  h.put("amaun_baucer", rs.getString("amaun_baucer") == null || "0".equals(rs.getString("amaun_baucer").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("amaun_baucer")));
			    	  
			    	  if(rs.getString("tarikh_terima") != null){
			    		  h.put("tkh_terima", Format.format(rs.getDate("tarikh_terima")));
			    	  }else{
			    		  h.put("tkh_terima", "");
			    	  }
			    	  list.addElement(h);
			      }
//			      conn.commit();
			      return list;
			      
			    } finally {
//			      if(conn != null)conn.close();
			      if (db != null) db.close();
			    }
	}
	
	//Tambah Baucer
	public static int simpanTBaucer(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {      
	    	
	      String no_resit;
	      String tkh_resit;
	      String TR = "";
	      long id_baucer = DB.getNextID("TBLHTPBAUCER_SEQ");
	      int idPeringkatbayaran = (Integer)data.get("idPeringkatbayaran");
	      myLog.info("FrmCukaiBaucerData::simpanTBaucer:::socDaerah = "+data.get("socDaerah"));
	      int socDaerah = Integer.parseInt(data.get("socDaerah").toString());
	      String tkh_baucer = (String)data.get("tkh_baucer");
	      String TB = "to_date('" + tkh_baucer + "','dd/MM/yyyy')";
	      
	      if(data.get("no_resit") != null)
	    	  no_resit = (String)data.get("no_resit");
	      else
	    	  no_resit = "";
	      
	      if(data.get("tkh_resit") != null){
	    	  tkh_resit = (String)data.get("tkh_resit");
	    	  TR = "to_date('" + tkh_resit + "','dd/MM/yyyy')";
	      }else
	    	  tkh_resit = "";
	      
	      String tkh_terima = (String)data.get("tkh_terima");
		  String TT = "to_date('" + tkh_terima + "','dd/MM/yyyy')";
		  Double amaun_baucer = Double.parseDouble(Utils.RemoveSymbol(data.get("amaun_baucer").toString()));
		  int idNegeri = (Integer)data.get("idNegeri");
		  String noBaucer = (String)data.get("no_baucer");
		  String idCukai = (String)data.get("idCukai");
		  String tahun = (String)data.get("tahun");

		  db = new Db();
	      Statement stmtBaucer = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_baucer", id_baucer);
	      r.add("no_baucer", noBaucer);
	      r.add("id_peringkatbayaran", idPeringkatbayaran);
	      r.add("id_daerah", socDaerah);
	      r.add("tarikh_baucer", r.unquote(TB));
	      r.add("no_resit", no_resit);
	      if(data.get("tkh_resit") != null){
	    	  r.add("tarikh_resit", r.unquote(TR));
	      }
	      if(data.get("tkh_resit") != null){
	    	  r.add("tarikh_terima", r.unquote(TT));
	      }
	      r.add("amaun_baucer", amaun_baucer);
	      r.add("id_negeri", idNegeri);
	      r.add("id_cukaiutama", r.unquote(idCukai));
	      r.add("tahun", tahun);
	      sql = r.getSQLInsert("Tblhtpbaucer");
	      myLog.info("FrmCukaiBaucerData::Insert::Tblhtpbaucer = "+sql);
	      stmtBaucer.executeUpdate(sql);
	      
	      //conn.commit();
	      return (int)id_baucer;
	      
	    } 
	    finally {
	    	//if(conn != null)conn.close();
	    	if (db != null) db.close();
	    }
	}
	
	public static void updateTBaucer(Hashtable data) throws Exception {
		
		Db db = null;
//		Connection conn = null;
		
		
		
	    String sql = "";
	    try {
	    	
	      db = new Db();	
//	      conn = db.getConnection();
//		  conn.setAutoCommit(false);
		    
	      int idBaucer = (Integer)data.get("idBaucer");
	      int idPeringkatbayaran = (Integer)data.get("idPeringkatbayaran");
	      String tkh_baucer = (String)data.get("tkh_baucer");
	      String TB = "to_date('" + tkh_baucer + "','dd/MM/yyyy')";
	      
	      String no_resit;
	      if(data.get("no_resit") != null)
	    	  no_resit = (String)data.get("no_resit");
	      else
	    	  no_resit = "";
	      
	      String tkh_resit;
	      if(data.get("tkh_resit") != null)
	    	  tkh_resit = (String)data.get("tkh_resit");
	      else
	    	  tkh_resit = "";
	      String TR = "to_date('" + tkh_resit + "','dd/MM/yyyy')";
	      
	      String tkh_terima = (String)data.get("tkh_terima");
		  String TT = "to_date('" + tkh_terima + "','dd/MM/yyyy')";
		  double amaun_baucer;
		  String tahun = (String)data.get("tahun");
		  
		  if(data.get("amaun_baucer") != ""){
			 amaun_baucer = Double.parseDouble(Utils.RemoveSymbol(data.get("amaun_baucer").toString()));
		  }
		  else{
			  amaun_baucer = 0.0;
		  }
		  int idNegeri = (Integer)data.get("idNegeri");
		  String noBaucer = (String)data.get("no_baucer");
		  
	      Statement stmtBaucer = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.update("id_baucer", idBaucer);
	      r.add("no_baucer", noBaucer);
	      r.add("id_peringkatbayaran", idPeringkatbayaran);
	      r.add("tarikh_baucer", r.unquote(TB));
	      r.add("no_resit", no_resit);
	      if(data.get("tkh_resit") != null){
	    	  r.add("tarikh_resit", r.unquote(TR));
	      }
	      if(data.get("tkh_terima") != null){
	    	  r.add("tarikh_terima", r.unquote(TT));
	      }
	      r.add("amaun_baucer", amaun_baucer);
	      r.add("id_negeri", idNegeri);
	      r.add("tahun", tahun);
	      
	      sql = r.getSQLUpdate("Tblhtpbaucer");
	      System.out.println("FrmCukaiBaucerData::Update::Tblhtpbaucer = "+sql);
	      stmtBaucer.executeUpdate(sql);
//	      conn.commit();
	      
	    } finally {
//	    	if(conn != null)conn.close();
	    	if (db != null) db.close();
	    }
	}
	
	public static Vector getListTBaucer(int idNegeri,int idBaucer,int idPeringkatbayaran)throws Exception {		
		Db db = null;
		String sql = "SELECT DISTINCT trd.id_daerah,trd.nama_daerah, thb.id_baucer, thb.no_baucer, thb.tarikh_baucer, " +
						"thb.no_resit, thb.tarikh_resit, thb.tarikh_terima, thb.amaun_baucer, thb.id_peringkatbayaran " +
					 "FROM tblhtpbaucer thb, tblrujdaerah trd, tblrujpejabat trp, tblhtpperingkatbayaran thpb " +
					 "WHERE trd.id_daerah = trp.id_daerah " +
					 	"AND trp.id_daerah = thb.id_daerah " +
					 	"AND trp.jenis_pejabat = '02' AND trp.id_negeri = "+ idNegeri +
					 	"AND thb.id_peringkatbayaran = "+ idPeringkatbayaran +
					 	"AND thb.id_baucer = "+ idBaucer;
					 			
		System.out.println("FrmCukaiBaucerData:::getListTBaucer::sql::"+sql);
		
			try {
			      db = new Db();
//			      conn = db.getConnection();
//				  conn.setAutoCommit(false);
			      
			      Statement stmt = db.getStatement();
			      ResultSet rs = stmt.executeQuery(sql);
			      
			     
			      Hashtable h;
		
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  if(rs.getString("nama_daerah") != null){
			    		  h.put("nama_daerah", rs.getString("nama_daerah"));
			    	  }else{
			    		  h.put("nama_daerah", "");
			    	  }
			    	  if(rs.getString("id_daerah") != null){
			    		  h.put("idDaerah", rs.getString("id_daerah"));
			    	  }else{
			    		  h.put("idDaerah", "0");
			    	  }
			    	  if(rs.getString("id_baucer") != null){
			    		  h.put("id_baucer", rs.getString("id_baucer"));
			    	  }else{
			    		  h.put("id_baucer", "0");
			    	  }
			    	  if(rs.getString("no_baucer") != null){
			    		  h.put("no_baucer", rs.getString("no_baucer"));
			    	  }else{
			    		  h.put("no_baucer", "");
			    	  }
			    	  if(rs.getString("id_peringkatbayaran") != null){
			    		  h.put("idPeringkatbayaran", rs.getString("id_peringkatbayaran"));
			    	  }else{
			    		  h.put("idPeringkatbayaran", "0");
			    	  }
			    	  if(rs.getString("tarikh_baucer") != null){
			    		  h.put("tkh_baucer", Format.format(rs.getDate("tarikh_baucer")));
			    	  }else{
			    		  h.put("tkh_baucer", "");
			    	  }
			    	  if(rs.getString("no_resit") != null){
			    		  h.put("no_resit", rs.getString("no_resit"));
			    	  }else{
			    		  h.put("no_resit", "");
			    	  }
			    	  if(rs.getString("tarikh_resit") != null){
			    		  h.put("tkh_resit", Format.format(rs.getDate("tarikh_resit")));
			    	  }else{
			    		  h.put("tkh_resit", "");
			    	  }
			    	  h.put("amaun_baucer", rs.getString("amaun_baucer") == null || "0".equals(rs.getString("amaun_baucer").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("amaun_baucer")));
			    	  
			    	  if(rs.getString("tarikh_terima") != null){
			    		  h.put("tkh_terima", Format.format(rs.getDate("tarikh_terima")));
			    	  }else{
			    		  h.put("tkh_terima", "");
			    	  }
			    	  list.addElement(h);
			      }
			      
//			      conn.commit();
			      return list;
			      
			    } finally {
//			      if(conn != null)conn.close();
			      if (db != null) db.close();
			    }
	}
	
	public static void kemaskiniBaucerBayaran(Hashtable data) throws Exception {	
		Db db = null;
	    String sql = "";
	    try {	    	
	      db = new Db();	
	      int idBaucer = (Integer)data.get("idBaucer");
	      String no_resit;
	      if(data.get("noResit") != null)
	    	  no_resit = (String)data.get("noResit");
	      else
	    	  no_resit = "";
	      
	      String tkh_resit;
	      if(data.get("tkh_resit") != null)
	    	  tkh_resit = (String)data.get("tkh_resit");
	      else
	    	  tkh_resit = "";
	      String TR = "to_date('" + tkh_resit + "','dd/MM/yyyy')";
	      
	      Statement stmtBaucer = db.getStatement();
	      SQLRenderer r = new SQLRenderer();	      
	      r.update("id_baucer", idBaucer);
	      r.add("no_resit", no_resit);
	      if(data.get("tkh_resit") != null){
	    	  r.add("tarikh_resit", r.unquote(TR));
	      }
	      sql = r.getSQLUpdate("Tblhtpbaucer");
	      stmtBaucer.executeUpdate(sql);
	      
	    } finally {
	    	if (db != null) db.close();
	    }
	}
	
	
}
