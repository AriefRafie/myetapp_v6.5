package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmPembelianPeguamData {
	private static Vector list = new Vector();
	private static Logger log = Logger.getLogger(FrmPembelianPeguamData.class);
	private static Connection conn = null;
	private static Db db = null;
	
	//*** query data from database
	@Deprecated
	public static void setListPeguam(String idPermohonan)throws Exception {
		
	    Db db = null;
	    list.clear();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	 
	      r.add("peg.id_Peguam");
	      r.add("peg.id_Permohonan");
	      r.add("peg.Nama");
	      r.add("peg.no_Rujukan");
	      r.add("peg.Alamat1");
	      r.add("peg.Alamat2");
	      r.add("peg.Alamat3");
	      r.add("peg.Poskod");
	      r.add("peg.id_Daerah");
	      r.add("peg.id_Negeri");
	      r.add("peg.No_Tel");
	      r.add("peg.No_Fax");
	
	      r.set("peg.id_Permohonan", idPermohonan);
	      
	      sql = r.getSQLSelect("Tblhtppeguam peg");
	      ResultSet rs = stmt.executeQuery(sql);
	      log.info(sql);
	      //Vector list = new Vector();
	      Hashtable h;
	
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("IdPeguam", rs.getString("id_Peguam") == null ? "" : rs.getString("id_Peguam"));
	    	  h.put("NamaPeguam", rs.getString("Nama") == null ? "" : rs.getString("Nama"));
	    	  if(rs.getString("no_Rujukan") != null){
	    		  h.put("noRujukan", rs.getString("no_Rujukan"));
	    	  }else{
	    		  h.put("noRujukan", "");
	    	  }
	    	  h.put("Alamat1", rs.getString("Alamat1") == null ? "" : rs.getString("Alamat1"));
	    	  if(rs.getString("Alamat2") != null){
	    		  h.put("Alamat2", rs.getString("Alamat2"));
	    	  }else{
	    		  h.put("Alamat2", "");
	    	  }
	    	  if(rs.getString("Alamat3") != null){
	    		  h.put("Alamat3", rs.getString("Alamat3"));
	    	  }else{
	    		  h.put("Alamat3", "");
	    	  }	    	  
	    	  h.put("Poskod", rs.getString("Poskod") == null ? "" : rs.getString("Poskod"));
	    	  if(rs.getString("id_Daerah") != null){
	    		  h.put("IdDaerah", rs.getString("id_Daerah"));
	    	  }else{
	    		  h.put("IdDaerah", "");
	    	  }	    	  
	    	  h.put("IdNegeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
	    	  if(rs.getString("No_Tel") != null){
	    		  h.put("NoTel", rs.getString("No_Tel"));
	    	  }else{
	    		  h.put("NoTel", "");
	    	  }
	    	  if(rs.getString("No_Fax") != null){
	    		  h.put("NoFax", rs.getString("No_Fax"));
	    	  }else{
	    		  h.put("NoFax", "");
	    	  }	    	  
	    	  list.addElement(h);
	      }
	      //return list;  
	    } 
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    
	    finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  public static Vector getListPeguam(){
		  log.info("FrmPembelianPeguamData :: getListPeguam : " + list.size());
		  return list;
	  }
	  
	//*** update data in database
	  @Deprecated
	  public static void update(Hashtable data) throws Exception {

	    String sql = "";
	    try
	    {
	      String idPeguam = (String)data.get("idPeguam");
	      String nama = (String)data.get("nama");
	      String noRujukan;
	      if(data.get("kod") != null)
	    	  noRujukan = (String)data.get("kod");
	      else
	    	  noRujukan = "";
	      String alamat1 = (String)data.get("alamat1");
	      String alamat2;
	      if(data.get("alamat2") != null)
	    	  alamat2 = (String)data.get("alamat2");
	      else
	    	  alamat2 = "";
	      String alamat3;
	      if(data.get("alamat3") != null)
	    	  alamat3 = (String)data.get("alamat3");
	      else
	    	  alamat3 = "";		      
	      String poskod = (String)data.get("poskod");
	      int idDaerah = (Integer)data.get("idDaerah");
	      int idNegeri = (Integer)data.get("idNegeri");
	      String noTelefon;
	      if(data.get("noTelefon") != null)
	    	  noTelefon = (String)data.get("noTelefon");
	      else
	    	  noTelefon = "";
	      String noFax;
	      if(data.get("noFax") != null)
	    	  noFax = (String)data.get("noFax");
	      else
	    	  noFax = "";

		  db = new Db();
		  conn = db.getConnection();
		  conn.setAutoCommit(false);
		  
		  
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  r.update("id_Peguam", idPeguam);
		  r.add("nama",nama);
		  r.add("no_Rujukan",noRujukan);
		  r.add("alamat1",alamat1);
		  r.add("alamat2",alamat2);
		  r.add("alamat3",alamat3);
		  r.add("poskod",poskod);
		  r.add("id_Daerah",idDaerah);
		  r.add("id_Negeri",idNegeri);
		  r.add("no_Tel",noTelefon);
		  r.add("no_Fax",noFax);
		  sql = r.getSQLUpdate("Tblhtppeguam");
		  log.info("FrmPembelianPeguamData::Update::Tblhtppeguam = "+sql);
		  stmt.executeUpdate(sql);
		  
		  conn.commit();
	    }
	    
	    catch(Exception e){
	    	conn.rollback();
	    	e.printStackTrace();
	    }
	    
	    finally {
	    	if (conn != null)conn.close();
	      if (db != null) db.close();
	    }
	  }
	  
	//*** save data in database
	  @Deprecated
	  public static void simpan(Hashtable data) throws Exception {
	   
	    String sql = "";
	    try
	    {
	      long idPeguam = DB.getNextID("TBLHTPPEGUAM_SEQ");
	      String idPermohonan = (String)data.get("idPermohonan");
	      String nama = (String)data.get("nama");
	      String noRujukan;
	      if(data.get("kod") != null)
	    	  noRujukan = (String)data.get("kod");
	      else
	    	  noRujukan = "";
	      String alamat1 = (String)data.get("alamat1");
	      String alamat2;
	      if(data.get("alamat2") != null)
	    	  alamat2 = (String)data.get("alamat2");
	      else
	    	  alamat2 = "";
	      String alamat3;
	      if(data.get("alamat3") != null)
	    	  alamat3 = (String)data.get("alamat3");
	      else
	    	  alamat3 = "";		      
	      String poskod = (String)data.get("poskod");
	      
	      
//	      int idDaerah = (Integer)data.get("idDaerah");
	      int idDaerah;
	      if(!data.get("idDaerah").toString().equalsIgnoreCase(null) && !data.get("idDaerah").toString().equalsIgnoreCase("") ){
	    	 
	    	  idDaerah = (Integer)data.get("idDaerah");
	      }
	      else{
	    	  idDaerah = Integer.parseInt("00");
	      }
	      
	      
//	      int idNegeri = (Integer)data.get("idNegeri");
	      int idNegeri;
	      if(!data.get("idNegeri").toString().equalsIgnoreCase(null) && !data.get("idNegeri").toString().equalsIgnoreCase("") ){
	    	  
	    	  idNegeri = (Integer)data.get("idNegeri");
	      }
	      else{
	    	  idNegeri = Integer.parseInt("00");
	      }
	      
	      
	      
	      String noTelefon;
	      if(data.get("noTelefon") != null)
	    	  noTelefon = (String)data.get("noTelefon");
	      else
	    	  noTelefon = "";
	      String noFax;
	      if(data.get("noFax") != null)
	    	  noFax = (String)data.get("noFax");
	      else
	    	  noFax = "";

		  db = new Db();
		  conn = db.getConnection();
		  conn.setAutoCommit(false); 
		  
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  r.add("id_Peguam", idPeguam);
		  r.add("id_Permohonan", idPermohonan);
		  r.add("nama",nama);
		  r.add("no_Rujukan",noRujukan);
		  r.add("alamat1",alamat1);
		  r.add("alamat2",alamat2);
		  r.add("alamat3",alamat3);
		  r.add("poskod",poskod);
		  r.add("id_Daerah",idDaerah);
		  r.add("id_Negeri",idNegeri);
		  r.add("no_Tel",noTelefon);
		  r.add("no_Fax",noFax);
		  sql = r.getSQLInsert("Tblhtppeguam");
		  log.info("FrmPembelianPeguamData::Insert::Tblhtppeguam = "+sql);
		  stmt.executeUpdate(sql);
		  
		  conn.commit();
	    }
	    catch(Exception e){
	    	conn.rollback();
	    	e.printStackTrace();
	    }
	    
	    finally {
	    	if (conn != null)conn.close();
	      if (db != null) db.close();
	    }
	  }
}
