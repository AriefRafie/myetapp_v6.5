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

public class FrmPembelianPemilikData {
	private static Vector list = new Vector();
	private static Logger log = Logger.getLogger(FrmPembelianPemilikData.class);
	private static Db db = null;
	private static Connection conn = null;
	private static Vector pemilikList = new Vector();
        private static Vector pemilikListByID;
	
	//*** query data from database
	public static void setListPemilik(String idHakmilikurusan)throws Exception {
	    
	    list.clear();
	    String sql;
	    try {
	    	
	      db = new Db(); 
	      Statement stmt = db.getStatement();
//	      SQLRenderer r = new SQLRenderer();
//	 
//	      r.add("pihak.id_Pihakberkepentingan");
//	      r.add("pihak.id_Hakmilikurusan");
//	      r.add("pihak.Nama");
//	      r.add("pihak.id_Jenisnopb");
//	      r.add("pihak.no_Rujukan");
//	      r.add("pihak.Alamat1");
//	      r.add("pihak.Alamat2");
//	      r.add("pihak.Alamat3");
//	      r.add("pihak.Poskod");
//	      r.add("pihak.id_Daerah");
//	      r.add("pihak.id_Negeri");
//	      r.add("pihak.No_Tel");
//	      r.add("pihak.No_Fax");
//
//	      r.add("pihak.id_hakmilikurusan", idHakmilikurusan,"=");
//	      
//	      sql = r.getSQLSelect("Tblhtppihakberkepentingan pihak");
	      
	      sql = "SELECT pihak.id_Pihakberkepentingan, pihak.id_Hakmilikurusan, pihak.Nama, ";
	      sql += "pihak.id_Jenisnopb, pihak.no_Rujukan, pihak.Alamat1, pihak.Alamat2, ";
	      sql += "pihak.Alamat3, pihak.Poskod, pihak.id_Daerah, pihak.id_Negeri, ";
	      sql += "pihak.No_Tel, pihak.No_Fax, HM.NO_HAKMILIK ";
	      sql += "FROM Tblhtppihakberkepentingan pihak, tblhtphakmilikurusan hm ";
	      sql += "WHERE pihak.id_hakmilikurusan =" + idHakmilikurusan;
	      sql += " AND PIHAK.ID_HAKMILIKURUSAN = HM.ID_HAKMILIKURUSAN";
	     
	      
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      log.info(sql);
	      //Vector list = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("IdPihakberkepentingan", rs.getString("id_Pihakberkepentingan"));
	    	  h.put("Nama", rs.getString("Nama") == null ? "" : rs.getString("Nama"));
	    	  h.put("idJenisnopb", rs.getString("id_Jenisnopb") == null ? "0" : rs.getString("id_Jenisnopb"));
	    	  h.put("noRujukan", rs.getString("no_Rujukan") == null ? "" : rs.getString("no_Rujukan"));
	    	  h.put("NoHakmilik", rs.getString("no_hakmilik") == null ? "" : rs.getString("no_hakmilik"));
	    	  if(rs.getString("Alamat1") != null){
	    		  h.put("Alamat1", rs.getString("Alamat1"));
	    	  }else{
	    		  h.put("Alamat1", "");
	    	  }
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
	  
	  public static Vector getListPemilik(){
		  log.info("FrmPembelianPemilikData : getListPemilik : " +list.size());
		  return list;
	  } 
	  
	//*** update data in database
	  public static void update(Hashtable data) throws Exception {

	    String sql = "";
	    try
	    {
	      String IdPihakberkepentingan = (String)data.get("idPihakberkepentingan");
	      String nama = (String)data.get("namaPemaju");
	      String idJenisnopb = (String)data.get("idJenisNoPB");
	      String noRujukan = (String)data.get("noRuj");
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
		  r.update("id_Pihakberkepentingan", IdPihakberkepentingan);
		  r.add("nama",nama);
		  r.add("id_Jenisnopb", idJenisnopb);
		  r.add("no_Rujukan",noRujukan);
		  r.add("alamat1",alamat1);
		  r.add("alamat2",alamat2);
		  r.add("alamat3",alamat3);
		  r.add("poskod",poskod);
		  r.add("id_Daerah",idDaerah);
		  r.add("id_Negeri",idNegeri);
		  r.add("no_Tel",noTelefon);
		  r.add("no_Fax",noFax);
		  sql = r.getSQLUpdate("Tblhtppihakberkepentingan");
		  log.info("FrmPembelianPemilikData::Update::Tblhtppihakberkepentingan = "+sql);
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
	  public static void simpan(Hashtable data) throws Exception {

	    String sql = "";
	    try
	    {
	      long IdPihakberkepentingan = DB.getNextID("TBLHTPPIHAKBERKEPENTINGAN_SEQ");
	      String idHakmilikurusan = (String)data.get("idHakmilikurusan");
	      String nama = (String)data.get("namaPemaju");
	      String idJenisnopb = (String)data.get("idJenisNoPB");
	      int idJenispb = Integer.parseInt("1");
	      String noRujukan = (String)data.get("noRuj");
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
	      int idDaerah = Integer.parseInt(data.get("idDaerah").toString());
	      int idNegeri = Integer.parseInt(data.get("idNegeri").toString());
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
		  r.add("id_Pihakberkepentingan", IdPihakberkepentingan);
		  r.add("id_Hakmilikurusan", idHakmilikurusan);
		  r.add("nama", nama);
		  r.add("id_Jenisnopb", idJenisnopb);
		  r.add("id_Jenispb", idJenispb);
		  r.add("no_Rujukan",noRujukan);		  
		  r.add("alamat1", alamat1);
		  r.add("alamat2", alamat2);
		  r.add("alamat3", alamat3);
		  r.add("poskod", poskod);
		  r.add("id_Daerah", idDaerah);
		  r.add("id_Negeri", idNegeri);
		  r.add("no_Tel", noTelefon);
		  r.add("no_Fax", noFax);
		  sql = r.getSQLInsert("Tblhtppihakberkepentingan");
		  
		  log.info("FrmPembelianPemilikData::Insert::Tblhtppihakberkepentingan = "+sql);
		  
		  stmt.executeUpdate(sql);	
		 
		  conn.commit();
		  
	    }
	    catch(Exception e){
	    	conn.rollback();
	    	e.printStackTrace();
	    }
	    
	    finally {
	      if (db != null) db.close();
	      if (conn != null)conn.close();
	    }
	  }
	  
	  public static void ListAllPemilik(String idPermohonan) throws Exception{
		  
		  String sql;
		  Hashtable h;
		  pemilikList.clear();
		  
		  try{
			  db = new Db();
			  conn = db.getConnection();
			  Statement stmt = db.getStatement();
			  
			  sql = "SELECT pihak.id_Pihakberkepentingan, pihak.id_Hakmilikurusan, ";
			  sql += "pihak.Nama,  HM.NO_HAKMILIK ";
			  sql += "FROM Tblhtppihakberkepentingan pihak, tblhtphakmilikurusan hm ";
			  sql += "WHERE PIHAK.ID_HAKMILIKURUSAN = HM.ID_HAKMILIKURUSAN ";
			  sql += "AND HM.ID_PERMOHONAN = " + idPermohonan;
			  
			  log.info("FrmPembelianPemilikData::ListAllPemilik::"+sql);
			  
			  ResultSet rs = stmt.executeQuery(sql);
			  
			  int bil = 1;
			  
			  while(rs.next()){
				  h = new Hashtable();
				  h.put("bil",bil);
				  h.put("idPihakKepentingan",rs.getString("id_pihakberkepentingan") == null ? "" : rs.getString("id_pihakberkepentingan"));
				  h.put("idHakmilikUrusan", rs.getString("id_hakmilikurusan") == null ? "" : rs.getString("id_hakmilikurusan"));
				  h.put("nama", rs.getString("nama")== null ? "" : rs.getString("nama"));
				  h.put("noHakmilik", rs.getString("no_hakmilik") == null ? "" : rs.getString("no_hakmilik"));
				  pemilikList.add(h);
				  bil++;
				  
			  }
			  
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  finally{
			  if (conn != null)conn.close();
			  if (db != null)db.close();
		  }
		  
	  }

          public static void ListPemilikById(String idPihakBerkepentingan) throws Exception{
                String sql;
		  Hashtable h;

		  try{
			  db = new Db();
			  conn = db.getConnection();
			  Statement stmt = db.getStatement();
                          pemilikListByID = new Vector();

			  sql = "SELECT pihak.id_Pihakberkepentingan, pihak.id_Hakmilikurusan, pihak.Nama,  ";
			  sql += "pihak.id_Jenisnopb, pihak.no_Rujukan, pihak.Alamat1, pihak.Alamat2, ";
			  sql += "pihak.Alamat3, pihak.Poskod, pihak.id_Daerah, pihak.id_Negeri,  ";
			  sql += "pihak.No_Tel, pihak.No_Fax, HM.NO_HAKMILIK  ";
              sql += "FROM Tblhtppihakberkepentingan pihak, tblhtphakmilikurusan hm  ";
              sql += "WHERE PIHAK.ID_HAKMILIKURUSAN = HM.ID_HAKMILIKURUSAN  ";
			  sql += "AND PIHAK.ID_PIHAKBERKEPENTINGAN = " + idPihakBerkepentingan;

			  log.info("FrmPembelianPemilikData::ListPemilikByID::"+sql);

			  ResultSet rs = stmt.executeQuery(sql);

			  while(rs.next()){
				  h = new Hashtable();
				  h.put("idPihakKepentingan", rs.getString("id_pihakberkepentingan") == null ? "" : rs.getString("id_pihakberkepentingan"));
				  h.put("idHakmilikUrusan",  rs.getString("id_hakmilikurusan") == null ? "" : rs.getString("id_hakmilikurusan"));
				  h.put("nama", rs.getString("nama") == null ? "" : rs.getString("nama"));
				  h.put("ic", rs.getString("no_rujukan") == null ? "" : rs.getString("no_rujukan"));
                  h.put("alamat1", rs.getString("alamat1") == null ? "" : rs.getString("alamat1"));
                  h.put("alamat2", rs.getString("alamat2") == null ? "" : rs.getString("alamat2"));
                  h.put("alamat3", rs.getString("alamat3") == null ? "" : rs.getString("alamat3"));
                  h.put("poskod", rs.getString("poskod") == null ? "" : rs.getString("poskod"));
                  h.put("idDaerah", rs.getString("id_daerah") == null ? "" : rs.getString("id_daerah"));
                  h.put("idNegeri", rs.getString("id_negeri") == null ? "" : rs.getString("id_negeri"));
                  h.put("tel", rs.getString("no_tel") == null ? "" : rs.getString("no_tel"));
                  h.put("fax", rs.getString("no_fax") == null ? "" : rs.getString("no_fax"));
                  h.put("noHakmilik", rs.getString("no_hakmilik") == null ? "" :rs.getString("no_hakmilik"));
				  pemilikListByID.add(h);

			  }

		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  finally{
			  if (conn != null)conn.close();
			  if (db != null)db.close();
		  	}
          }
	  
	  public static Vector getListAllPemilik(){
		  log.info("FrmPembelianPemilikData : getListAllPemilik : " +pemilikList.size());
		  return pemilikList;
	  }

          public static Vector getListPemilikByID(){
              log.info("FrmPembelianPemilikData : getListPemilikByID : " + pemilikListByID.size());
		  return pemilikListByID;
          }
	  
	  
}
