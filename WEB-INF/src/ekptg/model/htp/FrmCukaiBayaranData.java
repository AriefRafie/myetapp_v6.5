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

public class FrmCukaiBayaranData {
	private static Vector list = new Vector();
	private static Vector list2 = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLog = Logger.getLogger(ekptg.model.htp.FrmCukaiBayaranData.class);

	public static Vector getAmaunBaucer(String id_baucer)throws Exception {
		Db db = null;
		list2.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
				sql = "SELECT A.AMAUN_BAUCER,A.ID_BAUCER"
						+ " FROM TBLHTPBAUCER A"
						+ " WHERE A.ID_BAUCER =" +id_baucer;
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("amaun", rs.getString("AMAUN_BAUCER") == null ? "" : Util.formatDecimal(rs.getDouble("AMAUN_BAUCER")));
				h.put("idBaucer", rs.getString("ID_BAUCER") == null ? "" : rs.getString("ID_BAUCER"));
				list2.addElement(h);
			}
			return list2;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public static Vector getListBayaran(int idNegeri)throws Exception {
		Db db = null;
		list.clear();
		String sql = "SELECT DISTINCT thbc.id_peringkatbayaran, thbc.nama_bank, thbc.amaun" +
			" , thbc.no_rujbayaran, "+
			"  thbc.tarikh_bayaran, thbc.id_baucer, thb.tarikh_resit,thb.no_resit,"+ // 
			" thbc.id_bayarancukai, thb.id_negeri, thb.id_daerah, trd.nama_daerah "+
			" FROM tblhtpbayarancukai thbc, tblhtpbaucer thb, tblrujdaerah trd "+
			" WHERE thbc.id_baucer = thb.id_baucer" +
		    " AND thb.id_negeri =" +idNegeri+
			" AND trd.id_daerah = thb.id_daerah";		
//		myLog.info("FrmCukaiBayaranData:::getListBayaran::sql::"+sql);	
		try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      		     
		      Hashtable h;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  if(rs.getString("id_bayarancukai") != null){
		    		  h.put("id_bayarancukai", rs.getString("id_bayarancukai"));
		    	  }else{
		    		  h.put("id_bayarancukai", "0");
		    	  }
		    	  if(rs.getString("id_baucer") != null){
		    		  h.put("idBaucer", rs.getString("id_baucer"));
		    	  }else{
		    		  h.put("idBaucer", "0");
		    	  }
		    	  if(rs.getString("id_negeri") != null){
		    		  h.put("idNegeri", rs.getString("id_negeri"));
		    	  }else{
		    		  h.put("idNegeri", "0");
		    	  }
		    	  if(rs.getString("id_daerah") != null){
		    		  h.put("idDaerah", rs.getString("id_daerah"));
		    	  }else{
		    		  h.put("idDaerah", "0");
		    	  }
		    	  if(rs.getString("id_peringkatbayaran") != null){
		    		  h.put("idPeringkatbayaran", rs.getString("id_peringkatbayaran"));
		    	  }else{
		    		  h.put("idPeringkatbayaran", "0");
		    	  }
		    	  if(rs.getString("tarikh_bayaran") != null){
		    		  h.put("tkh_bayaran", Format.format(rs.getDate("tarikh_bayaran")));
		    	  }else{
		    		  h.put("tkh_bayaran", "");
		    	  }
		    	  if(rs.getString("nama_bank") != null){
		    		  h.put("nama_bank", rs.getString("nama_bank"));
		    	  }else{
		    		  h.put("nama_bank", "0");
		    	  }
		    	  if(rs.getString("nama_daerah") != null){
		    		  h.put("nama_daerah", rs.getString("nama_daerah"));
		    	  }else{
		    		  h.put("nama_daerah", "0");
		    	  }
		    	  h.put("amaun", rs.getString("amaun") == null || "0".equals(rs.getString("amaun").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("amaun")));
		    	  
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
		    	  if(rs.getString("no_rujbayaran") != null){
		    		  h.put("no_rujbayaran", rs.getString("no_rujbayaran"));
		    	  }else{
		    		  h.put("no_rujbayaran", "");
		    	  }	    	  
		    	  list.addElement(h);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
	}
	
	public static Vector getListBayaran(String idNegeri,String peringkatBayar)throws Exception {
		Db db = null;
		list.clear();
		String sql = "SELECT DISTINCT thbc.id_peringkatbayaran, thbc.nama_bank, thbc.amaun" +
			" , thbc.no_rujbayaran, "+
			"  thbc.tarikh_bayaran, thbc.id_baucer, thb.tarikh_resit,thb.no_resit,"+ // 
			" thbc.id_bayarancukai, thb.id_negeri, thb.id_daerah, trd.nama_daerah "+
			" FROM tblhtpbayarancukai thbc, tblhtpbaucer thb, tblrujdaerah trd "+
			" WHERE thbc.id_baucer = thb.id_baucer" +
		    " AND thb.id_negeri =" +idNegeri+
			" AND trd.id_daerah = thb.id_daerah";
		if(peringkatBayar.equals("1")){
			sql = "" +
					" SELECT b.id_baucer,b.id_daerah,b.amaun_baucer,b.id_peringkatbayaran" +
					" ,B.tarikh_resit,B.no_resiT " +
					" ,RN.ID_NEGERI,RN.NAMA_NEGERI NAMA_DAERAH" +
					" ,thbc.id_bayarancukai,thbc.tarikh_bayaran,thbc.id_peringkatbayaran, thbc.nama_bank, thbc.amaun, thbc.no_rujbayaran " +					
					" FROM tblhtpbaucer B, TBLRUJNEGERI RN, tblhtpbayarancukai THBC " +
					" WHERE B.id_NEGERI = RN.id_NEGERI AND THBC.id_baucer = B.id_baucer " +
				    " AND B.id_negeri ='" +idNegeri+"' "+
					" ";
			
		}
//		myLog.info("FrmCukaiBayaranData:::getListBayaran::sql::"+sql);	
		try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      		     
		      Hashtable h;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  if(rs.getString("id_bayarancukai") != null){
		    		  h.put("id_bayarancukai", rs.getString("id_bayarancukai"));
		    	  }else{
		    		  h.put("id_bayarancukai", "0");
		    	  }
		    	  if(rs.getString("id_baucer") != null){
		    		  h.put("idBaucer", rs.getString("id_baucer"));
		    	  }else{
		    		  h.put("idBaucer", "0");
		    	  }
		    	  if(rs.getString("id_negeri") != null){
		    		  h.put("idNegeri", rs.getString("id_negeri"));
		    	  }else{
		    		  h.put("idNegeri", "0");
		    	  }
		    	  if(rs.getString("id_daerah") != null){
		    		  h.put("idDaerah", rs.getString("id_daerah"));
		    	  }else{
		    		  h.put("idDaerah", "0");
		    	  }
		    	  if(rs.getString("id_peringkatbayaran") != null){
		    		  h.put("idPeringkatbayaran", rs.getString("id_peringkatbayaran"));
		    	  }else{
		    		  h.put("idPeringkatbayaran", "0");
		    	  }
		    	  if(rs.getString("tarikh_bayaran") != null){
		    		  h.put("tkh_bayaran", Format.format(rs.getDate("tarikh_bayaran")));
		    	  }else{
		    		  h.put("tkh_bayaran", "");
		    	  }
		    	  if(rs.getString("nama_bank") != null){
		    		  h.put("nama_bank", rs.getString("nama_bank"));
		    	  }else{
		    		  h.put("nama_bank", "0");
		    	  }
		    	  if(rs.getString("nama_daerah") != null){
		    		  h.put("nama_daerah", rs.getString("nama_daerah"));
		    	  }else{
		    		  h.put("nama_daerah", "0");
		    	  }
		    	  h.put("amaun", rs.getString("amaun") == null || "0".equals(rs.getString("amaun").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("amaun")));
		    	  
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
		    	  if(rs.getString("no_rujbayaran") != null){
		    		  h.put("no_rujbayaran", rs.getString("no_rujbayaran"));
		    	  }else{
		    		  h.put("no_rujbayaran", "");
		    	  }	    	  
		    	  list.addElement(h);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
	}	
	
	//Tambah Bayaran
	public static int simpanTBayaran(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      
	      long id_bayarancukai = DB.getNextID("TBLHTPBAYARANCUKAI_SEQ");
	      String idPeringkatbayaran = String.valueOf(data.get("idPeringkatbayaran"));
	      int socBaucer = Integer.parseInt(data.get("socBaucer").toString());
	      String tkh_bayaran = (String)data.get("tkh_bayaran");
	      String TB = "to_date('" + tkh_bayaran + "','dd/MM/yyyy')";
	      String nama_bank = (String)data.get("nama_bank");
	      //Double amaunBayaran = Double.parseDouble(Utils.RemoveSymbol(data.get("amaunBayaran").toString()));
	      Double amaunBayaran = Double.parseDouble(data.get("amaunBayaran").toString());
	      String noRujBayaran = (String)data.get("noRujBayaran");
//	      String noCek = (String)data.get("noCek");
	      String noResit = (String)data.get("noResit");
	      String tkh_resit = (String)data.get("tkh_resit");
	      String TR = "to_date('" + tkh_resit + "','dd/MM/yyyy')";
	      
		  db = new Db();
	      Statement stmtBayaran = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_bayarancukai", id_bayarancukai);
	      r.add("id_peringkatbayaran", idPeringkatbayaran);
	      r.add("id_baucer", socBaucer);
	      r.add("tarikh_bayaran", r.unquote(TB));
	      r.add("nama_bank", nama_bank);
	      r.add("amaun", amaunBayaran);
	      r.add("no_rujbayaran", noRujBayaran);	
	      //r.add("no_cek", noCek);
//	      r.add("no_resit", noResit);
	      if(!tkh_resit.equals(""))
	    	  r.add("tarikh_terima_bayaran", r.unquote(TR));
	      
	      sql = r.getSQLInsert("Tblhtpbayarancukai");
//	      myLog.info("FrmCukaiBayaranData::Insert::Tblhtpbayarancukai = "+sql);
	      stmtBayaran.executeUpdate(sql);
	      return (int)id_bayarancukai;
	    } 
	    finally {
	    	if (db != null) db.close();
	    }
	}
	
	public static void updateTBayaran(Hashtable data) throws Exception {
		Db db = null;
	    String sql = "";
	    try {
	      db = new Db();	     
	      int id_bayarancukai = (Integer)data.get("idBayaranCukai");
	      //int idPeringkatbayaran = (Integer)data.get("idPeringkatbayaran");
	      System.out.println("FrmCukaiBaucerData::simpanTBayaran:::idBaucer = "+data.get("idBaucer"));
	      int idBaucer = Integer.parseInt(data.get("idBaucer").toString());
	      String tkh_bayaran = (String)data.get("tkh_bayaran");
	      String TB = "to_date('" + tkh_bayaran + "','dd/MM/yyyy')";
	      String nama_bank = (String)data.get("nama_bank");
	      System.out.println("FrmCukaiBaucerData::simpanTBayaran:::kemaskini::amaunBayaran = "+data.get("amaunBayaran"));
		  double amaunBayaran;
		  if(data.get("amaunBayaran") != ""){
			  amaunBayaran = Double.parseDouble(Utils.RemoveSymbol(data.get("amaunBayaran").toString()));
		  }
		  else{
			  amaunBayaran = 0.0;
		  }
	      String noRujBayaran = (String)data.get("noRujBayaran");
//	      String noCek = (String)data.get("noCek");
	      String noResit = (String)data.get("noResit");
	      String tkh_resit = (String)data.get("tkh_resit");
	      String TR = "to_date('" + tkh_resit + "','dd/MM/yyyy')";
	      
		  db = new Db();
	      Statement stmtBaucer = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.update("id_bayarancukai", id_bayarancukai);
	      //r.add("id_peringkatbayaran", idPeringkatbayaran);
	      r.add("id_baucer", idBaucer);
	      r.add("tarikh_bayaran", r.unquote(TB));
	      r.add("nama_bank", nama_bank);
	      r.add("amaun", amaunBayaran);
	      r.add("no_rujbayaran", noRujBayaran);	  
	    //r.add("no_cek", noCek);
//	      if(noResit != ""){
//	      r.add("no_resit", noResit);}
//	      else{
//	    	  r.add("no_resit", "");
//	      }
//	      r.add("tarikh_resit", r.unquote(TR));
	      sql = r.getSQLUpdate("Tblhtpbayarancukai");
//	      System.out.println("FrmCukaiBayaranData::Insert::Tblhtpbayarancukai = "+sql);
	      stmtBaucer.executeUpdate(sql);
	      
	      FrmCukaiBaucerData.kemaskiniBaucerBayaran(data);
	      
	    } finally {
	    	if (db != null) db.close();
	    }
	}
	
	public static Vector getListTBayaran(int idNegeri,int idBayaranCukai)throws Exception {
		Db db = null;
		
		String sql = "SELECT DISTINCT thbc.id_bayarancukai, thbc.id_baucer, thbc.id_peringkatbayaran, thbc.tarikh_bayaran, "+
	 					"thbc.nama_bank, thbc.amaun, thbc.no_rujbayaran,THB.NO_RESIT,THB.TARIKH_RESIT,thb.id_negeri, " +
	 					"trd.nama_daerah, trd.id_daerah "+
	 				 "FROM tblhtpbayarancukai thbc, tblhtpbaucer thb, tblrujnegeri trn, tblrujdaerah trd, tblhtpperingkatbayaran thpb "+
	 				 "WHERE "+
	 				 	" thbc.id_baucer = thb.id_baucer"+
	 					" AND thbc.id_bayarancukai = "+ idBayaranCukai +
	 					" AND thbc.id_peringkatbayaran = thpb.id_peringkatbayaran"+
	 					" AND thb.id_negeri = trn.id_negeri " +
	 					" AND thb.id_daerah = trd.id_daerah";
					 	
		
		System.out.println("FrmCukaiBayaranData:::getListTBaucer::sql::"+sql);
		
			try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      ResultSet rs = stmt.executeQuery(sql);
			      
			     
			      Hashtable h;
		
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  if(rs.getString("id_bayarancukai") != null){
			    		  h.put("idBayaranCukai", rs.getString("id_bayarancukai"));
			    	  }else{
			    		  h.put("idBayaranCukai", "0");
			    	  }
			    	  if(rs.getString("id_baucer") != null){
			    		  h.put("idBaucer", rs.getString("id_baucer"));
			    	  }else{
			    		  h.put("idBaucer", "0");
			    	  }
			    	  if(rs.getString("id_peringkatbayaran") != null){
			    		  h.put("id_peringkatbayaran", rs.getString("id_peringkatbayaran"));
			    	  }else{
			    		  h.put("id_peringkatbayaran", "0");
			    	  }
			    	  if(rs.getString("tarikh_bayaran") != null){
			    		  h.put("tkh_bayaran", Format.format(rs.getDate("tarikh_bayaran")));
			    	  }else{
			    		  h.put("tkh_bayaran", "");
			    	  }
			    	  if(rs.getString("nama_bank") != null){
			    		  h.put("nama_bank", rs.getString("nama_bank"));
			    	  }else{
			    		  h.put("nama_bank", "");
			    	  }
			    	  h.put("amaun", rs.getString("amaun") == null || "0".equals(rs.getString("amaun").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("amaun")));
			    	  
//			    	  if(rs.getString("no_cek") != null){
//			    		  h.put("no_cek", rs.getString("no_cek"));
//			    	  }else{
//			    		  h.put("no_cek", "");
//			    	  }
			    	  
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
			    	  if(rs.getString("no_rujbayaran") != null){
			    		  h.put("no_rujbayaran", rs.getString("no_rujbayaran"));
			    	  }else{
			    		  h.put("no_rujbayaran", "");
			    	  }
			    	  if(rs.getString("id_negeri") != null){
			    		  h.put("idNegeri", rs.getString("id_negeri"));
			    	  }else{
			    		  h.put("idNegeri", "0");
			    	  }
			    	  if(rs.getString("id_daerah") != null){
			    		  h.put("idDaerah", rs.getString("id_daerah"));
			    	  }else{
			    		  h.put("idDaerah", "0");
			    	  }
			    	  if(rs.getString("nama_daerah") != null){
			    		  h.put("nama_daerah", rs.getString("nama_daerah"));
			    	  }else{
			    		  h.put("nama_daerah", "");
			    	  }
			    	  list.addElement(h);
			      }
			      return list;
			    } finally {
			      if (db != null) db.close();
			    }
	}
	
	public static Vector getListTBayaran(String idNegeri,String idBayaranCukai)throws Exception {
		Db db = null;
		
		String sql = "SELECT DISTINCT thbc.id_bayarancukai, thbc.id_baucer, thbc.id_peringkatbayaran, thbc.tarikh_bayaran, "+
	 		"thbc.nama_bank, thbc.amaun, thbc.no_rujbayaran,THB.NO_RESIT,THB.TARIKH_RESIT,thb.id_negeri, " +
	 		"trd.nama_daerah, trd.id_daerah "+
	 		"FROM tblhtpbayarancukai thbc, tblhtpbaucer thb, tblrujnegeri trn, tblrujdaerah trd, tblhtpperingkatbayaran thpb "+
	 		"WHERE "+
	 		" thbc.id_baucer = thb.id_baucer"+
	 		" AND thbc.id_bayarancukai = '"+ idBayaranCukai +"'"+
	 		" AND thbc.id_peringkatbayaran = thpb.id_peringkatbayaran"+
	 		" AND thb.id_negeri = trn.id_negeri " +
	 		" AND thb.id_daerah = trd.id_daerah";
					 	
		
		System.out.println("FrmCukaiBayaranData:::getListTBaucer::sql::"+sql);
		
			try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      ResultSet rs = stmt.executeQuery(sql);
			      
			     
			      Hashtable h;
		
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  if(rs.getString("id_bayarancukai") != null){
			    		  h.put("idBayaranCukai", rs.getString("id_bayarancukai"));
			    	  }else{
			    		  h.put("idBayaranCukai", "0");
			    	  }
			    	  if(rs.getString("id_baucer") != null){
			    		  h.put("idBaucer", rs.getString("id_baucer"));
			    	  }else{
			    		  h.put("idBaucer", "0");
			    	  }
			    	  if(rs.getString("id_peringkatbayaran") != null){
			    		  h.put("id_peringkatbayaran", rs.getString("id_peringkatbayaran"));
			    	  }else{
			    		  h.put("id_peringkatbayaran", "0");
			    	  }
			    	  if(rs.getString("tarikh_bayaran") != null){
			    		  h.put("tkh_bayaran", Format.format(rs.getDate("tarikh_bayaran")));
			    	  }else{
			    		  h.put("tkh_bayaran", "");
			    	  }
			    	  if(rs.getString("nama_bank") != null){
			    		  h.put("nama_bank", rs.getString("nama_bank"));
			    	  }else{
			    		  h.put("nama_bank", "");
			    	  }
			    	  h.put("amaun", rs.getString("amaun") == null || "0".equals(rs.getString("amaun").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("amaun")));
			    	  
//			    	  if(rs.getString("no_cek") != null){
//			    		  h.put("no_cek", rs.getString("no_cek"));
//			    	  }else{
//			    		  h.put("no_cek", "");
//			    	  }
			    	  
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
			    	  if(rs.getString("no_rujbayaran") != null){
			    		  h.put("no_rujbayaran", rs.getString("no_rujbayaran"));
			    	  }else{
			    		  h.put("no_rujbayaran", "");
			    	  }
			    	  if(rs.getString("id_negeri") != null){
			    		  h.put("idNegeri", rs.getString("id_negeri"));
			    	  }else{
			    		  h.put("idNegeri", "0");
			    	  }
			    	  if(rs.getString("id_daerah") != null){
			    		  h.put("idDaerah", rs.getString("id_daerah"));
			    	  }else{
			    		  h.put("idDaerah", "0");
			    	  }
			    	  if(rs.getString("nama_daerah") != null){
			    		  h.put("nama_daerah", rs.getString("nama_daerah"));
			    	  }else{
			    		  h.put("nama_daerah", "");
			    	  }
			    	  list.addElement(h);
			      }
			      return list;
			    } finally {
			      if (db != null) db.close();
			    }
	}
	
}
