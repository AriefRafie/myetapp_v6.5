package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmPajakanDrafData {
	private static Vector list = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
        private static Db db;
        private static Connection conn;
        private static Logger log = Logger.getLogger(FrmPajakanDrafData.class);
	
	//*** query data from database
	public static void setListDraf(String idPermohonan)throws Exception {
	    
	    list.clear();
	    String sql;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	 
	      r.add("dp.id_Dokumenperjanjian");
	      r.add("dp.id_Permohonan");
	      r.add("dp.tarikh_Hantar");
	      r.add("dp.tarikh_Terima");
	      r.add("dp.pihak");
	      r.add("dp.ulasan");

	      r.add("dp.id_Permohonan", idPermohonan);
	      
	      sql = r.getSQLSelect("Tblhtpdokumenperjanjian dp");
	      log.info("FrmPajakanDrafData::setDraf : "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("idDokumenperjanjian", rs.getString("id_Dokumenperjanjian"));
	    	  h.put("idPermohonan", rs.getString("id_Permohonan"));
	    	  h.put("tarikhHantarDraf", rs.getString("tarikh_Hantar")==null?"":Format.format(rs.getDate("tarikh_Hantar")));
	    	  h.put("tarikhTerimaDraf", rs.getString("tarikh_Terima")==null?"":Format.format(rs.getDate("tarikh_Terima")));
	    	  h.put("pihak", rs.getString("pihak"));
	    	  h.put("ulasanLA", rs.getString("ulasan")==null?"":rs.getString("ulasan"));
	    	  list.addElement(h);
	      }

	    } finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  public static Vector getListDraf(){
		  log.info("FrmPajakanDrafData : getListDraf : " +list.size());
		  return list;
	  } 
	  
	//*** update data in database
	  public static void updateDraf(Hashtable data) throws Exception {
	    String sql;
	    try{

	      String IdDokumenperjanjian = (String)data.get("idDokumenperjanjian");
	      String IdPermohonan = (String)data.get("idPermohonan");
	      String TarikhHantar = (String)data.get("tarikhHantar");
	      String TH = "to_date('" + TarikhHantar + "','dd/MM/yyyy')";
	      String TarikhTerima = (String)data.get("tarikhTerima");
	      String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
	      String ulasan;

	      if(data.get("ulasan") != null){
                  ulasan = (String)data.get("ulasan");
              }else{
                  ulasan = "";
              }
	    	  
	
                db = new Db();
                Statement stmt = db.getStatement();
                SQLRenderer r = new SQLRenderer();
                r.update("dp.id_Dokumenperjanjian", IdDokumenperjanjian);
                r.update("dp.id_Permohonan", IdPermohonan);
                r.add("dp.tarikh_Hantar", r.unquote(TH));
                r.add("dp.tarikh_Terima", r.unquote(TT));
                r.add("dp.ulasan", ulasan);

                sql = r.getSQLUpdate("Tblhtpdokumenperjanjian dp");
                log.info("FrmPajakanDrafData::Update::Tblhtpdokumenperjanjian = " + sql);
                stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	  
	//*** save data in database
	  public static void simpanDraf(Hashtable data) throws Exception {
	    String sql;
	    try{

                  db = new Db();
                  conn = db.getConnection();
                  conn.setAutoCommit(false);
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();

	      long IdDokumenperjanjian = DB.getNextID("TBLHTPDOKUMENPERJANJIAN_SEQ");
	      String IdPermohonan = (String)data.get("idPermohonan");
	      String TarikhHantar = (String)data.get("tarikhHantar");
	      String TH = "to_date('" + TarikhHantar + "','dd/MM/yyyy')";
	      String TarikhTerima = (String)data.get("tarikhTerima");
	      String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
	      String Pihak = "JKPTG";
	      String Ulasan;
	      if(data.get("ulasan") != null){
	    	  Ulasan = (String)data.get("ulasan");
              }else{
	    	  Ulasan = "";
              }
		
		  r.add("dp.id_Dokumenperjanjian", IdDokumenperjanjian);
	      r.add("dp.id_Permohonan", IdPermohonan);
	      r.add("dp.tarikh_Hantar", r.unquote(TH));
	      r.add("dp.tarikh_Terima", r.unquote(TT));
	      r.add("dp.pihak", Pihak);
	      r.add("dp.ulasan", Ulasan);
	      
		  sql = r.getSQLInsert("Tblhtpdokumenperjanjian dp");
		  log.info("FrmPajakanDrafData::Insert::Tblhtpdokumenperjanjian = "+sql);
		  stmt.executeUpdate(sql);

                  conn.commit();
	    }
	    finally {
	      if (db != null) db.close();
              if (conn!= null ) conn.close();
	    }
	  }
}
