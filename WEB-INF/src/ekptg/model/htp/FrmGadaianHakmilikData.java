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
import ekptg.helpers.Utils;

public class FrmGadaianHakmilikData {
	private IHtp iHTP = null;  
	private static Vector list = new Vector();
//	private static Vector list;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.FrmGadaianHakmilikData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static Connection conn;
	private static Db db;
	private static String idBandarStr = "0";

	//*** query data from database
	//public static void setListHakmilik(int idHakmilikurusan)throws Exception {
	public static void setListHakmilik(String idHakmilikurusan)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    try {
	      db = new Db();
//	      list = new Vector();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	 
	      r.add("pihak.id_Pihakberkepentingan");
	      r.add("pihak.id_hakmilikurusan");
	      r.add("pihak.Nama");
	      r.add("pihak.Alamat1");
	      r.add("pihak.Alamat2");
	      r.add("pihak.Alamat3");
	      r.add("pihak.Poskod");
	      r.add("pihak.id_Daerah");
	      r.add("pihak.ID_BANDAR");
	      r.add("pihak.id_Negeri");
	      r.add("pihak.No_Tel");
	      r.add("pihak.No_Fax");
	      r.add("beban.id_Bebanan");
	      r.add("beban.No_Perserahan");	      
	      r.add("beban.JILID");	      
	      r.add("beban.FOLIO");	      
	      r.add("TO_CHAR(beban.TARIKH_DAFTAR,'dd/MM/yyyy') TARIKH_DAFTAR");	      
	      r.add("pihak.id_Pihakberkepentingan",r.unquote("beban.id_Pihakberkepentingan"));

	      r.add("pihak.id_hakmilikurusan", idHakmilikurusan,"=");
	      
	      sql = r.getSQLSelect("Tblhtppihakberkepentingan pihak, Tblhtpbebanan beban");
	      myLog.info("setListHakmilik("+idHakmilikurusan+":"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      //Vector list = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("IdPihakberkepentingan", Utils.isNull(rs.getString("ID_PIHAKBERKEPENTINGAN")));
	    	  h.put("Nama", Utils.isNull(rs.getString("Nama")));
	    	  h.put("Alamat1", Utils.isNull(rs.getString("Alamat1")));
	    	  h.put("Alamat2", Utils.isNull(rs.getString("Alamat2")));
	    	  h.put("Alamat3", Utils.isNull(rs.getString("Alamat3")));	    	  
	    	  h.put("IdDaerah", Utils.isNull(rs.getString("ID_DAERAH")));	    	  
	    	  h.put("idBandar", rs.getString("ID_BANDAR")==null?"0":rs.getString("ID_BANDAR"));	    	  
	    	  h.put("IdNegeri", Utils.isNull(rs.getString("ID_NEGERI")));	    	  
	    	  h.put("Poskod", Utils.isNull(rs.getString("POSKOD")));	    	  
    		  h.put("NoTel", Utils.isNull(rs.getString("NO_TEL")));
    		  h.put("NoFax", Utils.isNull(rs.getString("No_FAX")));
	    	  h.put("IdBebanan", Utils.isNull(rs.getString("ID_BEBANAN")));
	    	  h.put("NoPerserahan", Utils.isNull(rs.getString("NO_PERSERAHAN")));
	    	  h.put("jilid", Utils.isNull(rs.getString("JILID")));
	    	  h.put("folio", Utils.isNull(rs.getString("FOLIO")));
	    	  h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
	    	  list.addElement(h);
	      }
	      //return list;
	    
	    }catch(Exception e){
	    	e.printStackTrace();
	    
	    }finally {
	      if (db != null) {
	    	  db.close();
	      }
	      
	    }
	    
	}

	public Vector<Hashtable<String,String>> getSenaraiHakmilik(String idHakmilikurusan)throws Exception {
		Db db = null;
	    list.clear();
	    String sql = "";
	    try {
	    	db = new Db();
	    	list = new Vector<Hashtable<String,String>>();
	    	Statement stmt = db.getStatement();
	    	SQLRenderer r = new SQLRenderer();
	 
	    	r.add("pihak.id_Pihakberkepentingan");
	    	r.add("pihak.id_hakmilikurusan");
	    	r.add("pihak.Nama");
	    	r.add("pihak.Alamat1");
	    	r.add("pihak.Alamat2");
	    	r.add("pihak.Alamat3");
	    	r.add("pihak.Poskod");
	    	r.add("pihak.id_Daerah");
	    	r.add("pihak.ID_BANDAR");
	    	r.add("pihak.id_Negeri");
	    	r.add("pihak.No_Tel");
	    	r.add("pihak.No_Fax");
	    	r.add("beban.id_Bebanan");
	    	r.add("beban.No_Perserahan");	      
	    	r.add("beban.JILID");	      
	 	    r.add("beban.FOLIO");	      
	 	    r.add("TO_CHAR(beban.TARIKH_DAFTAR,'dd/MM/yyyy') TARIKH_DAFTAR");	      
	    	r.add("pihak.id_Pihakberkepentingan",r.unquote("beban.id_Pihakberkepentingan"));
	    	r.add("pihak.id_hakmilikurusan", idHakmilikurusan);
	    	//r.add("pihak.id_hakmilikurusan", idHakmilikurusan,"=");
	      
	    	sql = r.getSQLSelect("Tblhtppihakberkepentingan pihak, Tblhtpbebanan beban");
	    	myLog.info("setListHakmilik("+idHakmilikurusan+":"+sql);
	    	ResultSet rs = stmt.executeQuery(sql);
	    	Hashtable<String,String> h = null;

	    	while (rs.next()) {
	    		h = new Hashtable<String,String>();
	    		h.put("IdPihakberkepentingan", Utils.isNull(rs.getString("ID_PIHAKBERKEPENTINGAN")));
	    		h.put("Nama", Utils.isNull(rs.getString("Nama")));
	    		h.put("Alamat1", Utils.isNull(rs.getString("Alamat1")));
	    		h.put("Alamat2", Utils.isNull(rs.getString("Alamat2")));
	    		h.put("Alamat3", Utils.isNull(rs.getString("Alamat3")));	    	  
	    	  	h.put("IdDaerah", Utils.isNull(rs.getString("ID_DAERAH")));	    	  
	    	  	h.put("idBandar", rs.getString("ID_BANDAR")==null?"0":rs.getString("ID_BANDAR"));	    	  
	    	  	h.put("IdNegeri", Utils.isNull(rs.getString("ID_NEGERI")));	    	  
	    	  	h.put("Poskod", Utils.isNull(rs.getString("POSKOD")));	    	  
	    	  	h.put("NoTel", Utils.isNull(rs.getString("NO_TEL")));
	    	  	h.put("NoFax", Utils.isNull(rs.getString("No_FAX")));
	    	  	h.put("IdBebanan", Utils.isNull(rs.getString("ID_BEBANAN")));
	    	  	h.put("NoPerserahan", Utils.isNull(rs.getString("NO_PERSERAHAN")));
	    	  	h.put("idHakmilikUrusan", Utils.isNull(rs.getString("ID_HAKMILIKURUSAN")));
	    	  	h.put("jilid", Utils.isNull(rs.getString("JILID")));
		    	h.put("folio", Utils.isNull(rs.getString("FOLIO")));
		    	h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
	    	  	list.addElement(h);
	    	}	      
	    
	    }catch(Exception e){
	    	e.printStackTrace();
	    
	    }finally {
	      if (db != null) {
	    	  db.close();
	      }
	      
	    }
	    return list;
	    
	}
	
	  public static Vector getListHakmilik(){
		  myLog.info("FrmGadaianHakmilikData::getListHakMilik: "+list.size());
		  return list;
	  } 
	  
	  //*** update data in database
	  public static void update(Hashtable<String,String> data) throws Exception {
		  Db db = null;
		  String sql = "";
		  try {
		      String IdPihakberkepentingan = String.valueOf(data.get("idPihakberkepentingan"));
		      String nama = (String)data.get("nama");
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
		      int idDaerah = Integer.parseInt(String.valueOf(data.get("idDaerah")));
		      int idNegeri = Integer.parseInt(String.valueOf(data.get("idNegeri")));
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
		      
		      int idBebanan = Integer.parseInt(String.valueOf(data.get("idBebanan")));
		      String noPerserahan = (String)data.get("noPerserahan");

			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_Pihakberkepentingan", IdPihakberkepentingan);
			  r.add("nama",nama);
			  r.add("alamat1",alamat1);
			  r.add("alamat2",alamat2);
			  r.add("alamat3",alamat3);
			  r.add("poskod",poskod);
			  r.add("id_Daerah",idDaerah);
			  r.add("id_Negeri",idNegeri);
			  r.add("no_Tel",noTelefon);
			  r.add("no_Fax",noFax);
			  sql = r.getSQLUpdate("Tblhtppihakberkepentingan");
			  //log.info("FrmGadaianHakmilikData::Update::Tblhtppihakberkepentingan = "+sql);
			  stmt.executeUpdate(sql);
	
			  String jilid = "0"; //(String)data.get("jilid");
		      String folio = "0";//(String)data.get("folio");
		      String tarikhDaftar = "TO_DATE('"+String.valueOf(data.get("suratGadaian"))+"','dd/MM/yyyy')";
		      
		      Statement stmtP = db.getStatement();
			  SQLRenderer rP = new SQLRenderer();
			  rP.update("id_Bebanan", idBebanan);
			  rP.add("no_Perserahan",noPerserahan);
			  rP.add("jilid", jilid);
			  rP.add("folio", folio);
			  //rP.add("TARIKH_DAFTAR", rP.unquote(tarikhDaftar));
			  sql = rP.getSQLUpdate("Tblhtpbebanan");
		      //log.info("FrmGadaianHakmilikData::Update::Tblhtpbebanan = "+sql);
		      stmtP.executeUpdate(sql);
		      
		    }catch(Exception e){
		    	e.printStackTrace();
		    
		    }finally {
		      if (db != null) db.close();
		    
		    }
		 
	  }	  
	  //*** update data in database
	  public static void updatePelepasan(Hashtable<String,String> data) throws Exception {
		  Db db = null;
		  String sql = "";
		  try {
		      String IdPihakberkepentingan = String.valueOf(data.get("idPihakberkepentingan"));
		      String nama = (String)data.get("nama");
		      String alamat1 = (String)data.get("alamat1");
		      idBandarStr =  String.valueOf(data.get("idBandar"));
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
		      int idNegeri = Integer.parseInt(String.valueOf(data.get("idNegeri")));
		      int idDaerah = Integer.parseInt(getDaerahMengikutBandar(idBandarStr));
		      int idBandar = Integer.parseInt(idBandarStr);
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
		      
		      String idBebanan = String.valueOf(data.get("idBebanan"));
		      String noPerserahan = (String)data.get("noPerserahan");

			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_Pihakberkepentingan", IdPihakberkepentingan);
			  r.add("nama",nama);
			  r.add("alamat1",alamat1);
			  r.add("alamat2",alamat2);
			  r.add("alamat3",alamat3);
			  r.add("poskod",poskod);
			  r.add("id_Daerah",idDaerah);
			  r.add("ID_BANDAR", idBandar);
			  r.add("id_Negeri",idNegeri);
			  r.add("no_Tel",noTelefon);
			  r.add("no_Fax",noFax);
			  sql = r.getSQLUpdate("Tblhtppihakberkepentingan");
			  myLog.info("FrmGadaianHakmilikData::Update::Tblhtppihakberkepentingan = "+sql);
			  stmt.executeUpdate(sql);
	
			  String jilid = (String)data.get("jilid");
		      String folio = (String)data.get("folio");
		      String tarikhDaftar = "TO_DATE('"+String.valueOf(data.get("suratGadaian"))+"','dd/MM/yyyy')";
		      
		      Statement stmtP = db.getStatement();
			  SQLRenderer rP = new SQLRenderer();
			  rP.update("id_Bebanan", idBebanan);
			  rP.add("no_Perserahan",noPerserahan);
			  rP.add("jilid", jilid);
			  rP.add("folio", folio);
			  rP.add("TARIKH_DAFTAR", rP.unquote(tarikhDaftar));
			  //rP.add("TARIKH_DAFTAR", rP.unquote(String.valueOf(data.get("suratGadaian"))));
		      sql = rP.getSQLUpdate("Tblhtpbebanan");
		      myLog.info("FrmGadaianHakmilikData::Update::Tblhtpbebanan = "+sql);
		      stmtP.executeUpdate(sql);
		      
		    }catch(Exception e){
		    	e.printStackTrace();
		    
		    }finally {
		      if (db != null) db.close();
		    
		    }
		 
	  }
	  
	  //*** save data in database
	  public static void simpan(Hashtable<String,String> data) throws Exception {
		  Db db = null;
		  String sql = "";
		  try {
		      String IdPihakberkepentingan = String.valueOf(DB.getNextID("TBLHTPPIHAKBERKEPENTINGAN_SEQ"));
		      String idHakmilikurusan = String.valueOf(data.get("idHakmilikurusan"));
		      int idJenisnopb = Integer.parseInt("1");
		      int idJenispb = Integer.parseInt("1");
		      String nama = (String)data.get("nama");
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
		      int idDaerah = Integer.parseInt(String.valueOf(data.get("idDaerah")));
		      int idNegeri = Integer.parseInt(String.valueOf(data.get("idNegeri")));
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
		      
		      long idBebanan = DB.getNextID("TBLHTPBEBANAN_SEQ");
		      String noPerserahan = (String)data.get("noPerserahan");
		      String jilid = "1";
		      String folio = "1";
		      //String tarikhDaftar = "TO_DATE('"+Format.format(data.get("suratGadaian"))+"','dd/MM/yyyy'))";
		      String tarikhDaftar = "TO_DATE('"+String.valueOf(data.get("suratGadaian"))+"','dd/MM/yyyy')";

		      //Date now = new Date();
		      //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		      //String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		      int IdRujbebanan = Integer.parseInt("224");
		      int IdRujpihakberkepentingan = Integer.parseInt("1");

			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.add("id_Pihakberkepentingan", IdPihakberkepentingan);
			  r.add("id_Hakmilikurusan", idHakmilikurusan);
			  r.add("id_Jenisnopb", idJenisnopb);
			  r.add("id_Jenispb", idJenispb);
			  r.add("nama", nama);
			  r.add("alamat1", alamat1);
			  r.add("alamat2", alamat2);
			  r.add("alamat3", alamat3);
			  r.add("poskod", poskod);
			  r.add("id_Daerah", idDaerah);
			  r.add("id_Negeri", idNegeri);
			  r.add("no_Tel", noTelefon);
			  r.add("no_Fax", noFax);
			  sql = r.getSQLInsert("Tblhtppihakberkepentingan");
			  //log.info("FrmGadaianHakmilikData::Insert::Tblhtppihakberkepentingan = "+sql);
			  stmt.executeUpdate(sql);
		      
		      Statement stmtP = db.getStatement();
			  SQLRenderer rP = new SQLRenderer();
			  rP.add("id_Bebanan", idBebanan);
			  rP.add("id_Pihakberkepentingan", IdPihakberkepentingan);
			  rP.add("nama_Pihak_Berkepentingan", nama);
			  rP.add("no_Perserahan", noPerserahan);
			  rP.add("jilid", jilid);
			  rP.add("folio", folio);
			  rP.add("alamat1", alamat1);
			  rP.add("alamat2", alamat2);
			  rP.add("alamat3", alamat3);
			  rP.add("poskod", poskod);
			  rP.add("id_Daerah", idDaerah);
			  rP.add("id_Negeri", idNegeri);
			  rP.add("no_Tel", noTelefon);
			  rP.add("no_Fax", noFax);
			  rP.add("id_Rujbebanan", IdRujbebanan);
			  rP.add("TARIKH_DAFTAR", rP.unquote("sysdate"));
			  rP.add("Id_JENISPB", IdRujpihakberkepentingan);
		      sql = rP.getSQLInsert("Tblhtpbebanan");
		      //log.info("FrmGadaianHakmilikData::Insert::Tblhtpbebanan = "+sql);
		      stmtP.executeUpdate(sql);
		    
		  }catch(Exception e){
		    	e.printStackTrace();
		  
		  }finally {
		      if (db != null) db.close();
		    
		  }
	  
	  }	  
	  
	//*** save data in database
	  public static void simpanPelepasan(Hashtable<String,String> data) throws Exception {
		  Db db = null;
		  String sql = "";
		  try {
		      long IdPihakberkepentingan = DB.getNextID("TBLHTPPIHAKBERKEPENTINGAN_SEQ");
		      String idHakmilikurusan = String.valueOf(data.get("idHakmilikurusan"));
		      int idJenisnopb = Integer.parseInt("1");
		      int idJenispb = Integer.parseInt("1");
		      String nama = (String)data.get("nama");
		      String alamat1 = (String)data.get("alamat1");
		      idBandarStr = String.valueOf(data.get("idBandar"));
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
		      //int idDaerah = (Integer)data.get("idDaerah");
		      int idDaerah = Integer.parseInt(getDaerahMengikutBandar(idBandarStr));
		      int idNegeri = Integer.parseInt(String.valueOf(data.get("idNegeri")));
		      int idBandar = Integer.parseInt(idBandarStr);
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
		      
		      long idBebanan = DB.getNextID("TBLHTPBEBANAN_SEQ");
		      String noPerserahan = (String)data.get("noPerserahan");
		      String jilid = (String)data.get("jilid");
		      String folio = (String)data.get("folio");
		      //String tarikhDaftar = "TO_DATE('"+Format.format(data.get("suratGadaian"))+"','dd/MM/yyyy'))";
		      String tarikhDaftar = "TO_DATE('"+String.valueOf(data.get("suratGadaian"))+"','dd/MM/yyyy')";

		      //Date now = new Date();
		      //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		      //String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		      int IdRujbebanan = Integer.parseInt("224");
		      int IdRujpihakberkepentingan = Integer.parseInt("1");

			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.add("id_Pihakberkepentingan", IdPihakberkepentingan);
			  r.add("id_Hakmilikurusan", idHakmilikurusan);
			  r.add("id_Jenisnopb", idJenisnopb);
			  r.add("id_Jenispb", idJenispb);
			  r.add("nama", nama);
			  r.add("alamat1", alamat1);
			  r.add("alamat2", alamat2);
			  r.add("alamat3", alamat3);
			  r.add("poskod", poskod);
			  r.add("id_Daerah", idDaerah);
			  r.add("ID_BANDAR", idBandar);
			  r.add("id_Negeri", idNegeri);
			  r.add("no_Tel", noTelefon);
			  r.add("no_Fax", noFax);
			  sql = r.getSQLInsert("Tblhtppihakberkepentingan");
			  //log.info("FrmGadaianHakmilikData::Insert::Tblhtppihakberkepentingan = "+sql);
			  stmt.executeUpdate(sql);
		      
		      Statement stmtP = db.getStatement();
			  SQLRenderer rP = new SQLRenderer();
			  rP.add("id_Bebanan", idBebanan);
			  rP.add("id_Pihakberkepentingan", IdPihakberkepentingan);
			  rP.add("nama_Pihak_Berkepentingan", nama);
			  rP.add("no_Perserahan", noPerserahan);
			  rP.add("jilid", jilid);
			  rP.add("folio", folio);
			  //rP.add("tarikh_Daftar", rP.unquote("sysdate"));
			  rP.add("alamat1", alamat1);
			  rP.add("alamat2", alamat2);
			  rP.add("alamat3", alamat3);
			  rP.add("poskod", poskod);
			  rP.add("id_Daerah", idDaerah);
			  //r.add("ID_BANDAR", (String)data.get("idBandar"));
			  rP.add("id_Negeri", idNegeri);
			  rP.add("no_Tel", noTelefon);
			  rP.add("no_Fax", noFax);
			  rP.add("id_Rujbebanan", IdRujbebanan);
			  rP.add("TARIKH_DAFTAR", rP.unquote(tarikhDaftar));
			  //rP.add("Id_Rujpihakberkepentingan", IdRujpihakberkepentingan);
			  rP.add("Id_JENISPB", IdRujpihakberkepentingan);
		      sql = rP.getSQLInsert("Tblhtpbebanan");
		      //log.info("FrmGadaianHakmilikData::Insert::Tblhtpbebanan = "+sql);
		      stmtP.executeUpdate(sql);
		    
		  }catch(Exception e){
		    	e.printStackTrace();
		  
		  }finally {
		      if (db != null) db.close();
		    
		  }
	  
	  }
	  
	  public static String SimpanGeran(Hashtable<String,String> hash)throws Exception{	  
		  String idHtppermohonan = "0";
		  String idPermohonan = null;
		  String sql = null;
		  String catatan = null;
		  String userID = null;
		  String idHtpMaklumatGadaian = "0";
		  
		  try{
			  idPermohonan = String.valueOf(hash.get("idPermohonan"));			  
			  db = new Db();
			  conn = db.getConnection();
			  conn.setAutoCommit(false);		  
			  Statement stmt = db.getStatement();		  
			  sql = "SELECT A.ID_HTPPERMOHONAN ";
			  sql += "FROM TBLHTPPERMOHONAN A ";
			  sql += "WHERE A.ID_PERMOHONAN = '" + idPermohonan + "'";
			  myLog.info("SimpanGeran::"+sql);
			  ResultSet rs = stmt.executeQuery(sql);		  
			  while(rs.next()){
				  idHtppermohonan = rs.getString("ID_HTPPERMOHONAN");
			  }

			  //TBLHTPSIMPANGERAN
			  idHtpMaklumatGadaian = String.valueOf(DB.getNextID("TBLHTPSIMPANGERAN_SEQ"));			  
			  catatan = hash.get("keterangan").toString();
			  userID = hash.get("userID").toString();		  
			  
			  SQLRenderer r = new SQLRenderer();
			  r.add("ID_HTPMAKLUMATGADAIAN",idHtpMaklumatGadaian);
			  r.add("ID_HTPPERMOHONAN",idHtppermohonan);
			  
			  if (!"".equals(hash.get("TarikhTerima"))){
					r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("TarikhTerima") + "','dd/MM/yyyy')"));
			  }
			  
			  r.add("CATATAN",catatan);
			  r.add("ID_MASUK",userID);
			  r.add("TARIKH_MASUK",r.unquote("SYSDATE"));
			  
			  sql = r.getSQLInsert("TBLHTPSIMPANGERAN");
			  //log.info("sql TBLHTPSIMPANGERAN : " + sql);
			  stmt.executeUpdate(sql);
			  conn.commit();
			  
		  }catch(Exception e){
			  conn.rollback();
			  e.printStackTrace();
		  
		  }
		  return String.valueOf(idHtpMaklumatGadaian);
	  
	  }
	  
	  
	  public static void SimpanUpdateGeran(Hashtable<String,String> hash)throws Exception{		  
		  String idHTPGadaian = null;
		  //String idPermohonan = null;
		  String sql = null;
		  String catatan = null;
		  String userID = null;
		  
		  try{
			  //idPermohonan = hash.get("idPermohonan").toString();
			  idHTPGadaian = hash.get("idHTPGadaian").toString();
			  catatan = hash.get("keterangan").toString();
			  userID = hash.get("userID").toString();
			  
			  db = new Db();
			  conn = db.getConnection();
			  conn.setAutoCommit(false);
			  
			  Statement stmt = db.getStatement();		  
			  //TBLHTPSIMPANGERAN	  
			  SQLRenderer r = new SQLRenderer();
			  //r.update("ID_HTPMAKLUMATGADAIAN", idHTPGadaian);			  
			  r.update("ID_HTPPERMOHONAN", idHTPGadaian);			  
			  if (!"".equals(hash.get("TarikhTerima"))){
					r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("TarikhTerima") + "','dd/MM/yyyy')"));
			  }			  
			  r.add("CATATAN",catatan);
			  r.add("ID_KEMASKINI",userID);
			  r.add("TARIKH_KEMASKINI",r.unquote("SYSDATE"));		  
			  sql = r.getSQLUpdate("TBLHTPSIMPANGERAN");		  
			  //log.info("sql UPDATE TBLHTPSIMPANGERAN : " + sql);			  
			  stmt.executeUpdate(sql);		  
			  conn.commit();
			  
		  }catch(Exception e){
			  conn.rollback();
			  e.printStackTrace();
		  
		  }
		  	
	  }
	  
	  public static Vector<Hashtable<String,String>> setGeranInfo(String idHtpMaklumatGadaian)throws Exception{  
		  String sql = null;
		  Vector<Hashtable<String,String>> geranList = null;
		  Hashtable<String,String> hashGeran = null;	  
		  try{
			  geranList = new Vector<Hashtable<String,String>>();		  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  
			  sql = "SELECT A.ID_HTPMAKLUMATGADAIAN, A.ID_HTPPERMOHONAN, A.TARIKH_TERIMA, A.CATATAN ";
			  sql += "FROM TBLHTPSIMPANGERAN A ";
			  sql += "WHERE A.ID_HTPPERMOHONAN = '" + idHtpMaklumatGadaian + "'";		
			  //sql += "WHERE A.ID_HTPMAKLUMATGADAIAN = '" + idHtpMaklumatGadaian + "'";		
			  myLog.info("Geran :: " +sql);
			  ResultSet rs = stmt.executeQuery(sql);			  
			  while(rs.next()){
				  hashGeran = new Hashtable<String,String>();
				  hashGeran.put("idHakmilikgadaian", rs.getString("ID_HTPMAKLUMATGADAIAN") == null ? "" : rs.getString("ID_HTPMAKLUMATGADAIAN"));
				  hashGeran.put("idHtppermohonan", rs.getString("ID_HTPMAKLUMATGADAIAN") == null ? "" : rs.getString("ID_HTPMAKLUMATGADAIAN"));
				  hashGeran.put("TarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : Format.format(rs.getDate("TARIKH_TERIMA")));
				  hashGeran.put("keterangan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				  geranList.addElement(hashGeran);
				  
			  }
			  
		  }catch(Exception e){
			  e.printStackTrace();			  
		  }
		  return geranList;
		  
	  }
	  
		public static String getDaerahMengikutBandar(String idBandar) throws Exception {
			Db db = null;
			String sql = "";
			String returnValue = "0";
			String negeriValue = "0";
			sql = "Select id_daerah,id_negeri from tblrujbandar"
					+ " where id_bandar='" + idBandar +"'"
					+ " ";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				myLog.info("getDaerahMengikutBandar:sql="+sql);
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					returnValue = rs.getString("id_daerah")==null?"0":rs.getString("id_daerah");
					negeriValue = rs.getString("id_negeri");
				}
				if(returnValue.equals("0")){
					returnValue = getDaerah00ByNegeri(negeriValue);
				}	
				return returnValue;
				
			} finally {
				if (db != null)
					db.close();
			}
		}  
		
		public static String getDaerah00ByNegeri(String idNegeri) throws Exception {
			Db db = null;
			String sql = "";
			String returnValue = "148"; //default id_daerah untuk id_negeri=0 
			sql = "Select id_Daerah,kod_Daerah,nama_Daerah from tblrujdaerah"
					+ " where id_negeri='" + idNegeri +"' AND NAMA_DAERAH like 'TIADA MAKLUMAT' "
					+ " ORDER BY lpad(kod_Daerah,10)";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					returnValue = rs.getString(1);
				}
				return returnValue;
				
			} finally {
				if (db != null)
					db.close();
			}
		}

	  
}
