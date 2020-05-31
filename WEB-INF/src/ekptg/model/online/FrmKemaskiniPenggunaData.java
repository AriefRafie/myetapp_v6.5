package ekptg.model.online;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.PasswordService;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmKemaskiniPenggunaData {
	static Logger myLogger = Logger.getLogger(FrmKemaskiniPenggunaData.class);
	private static FrmKemaskiniPenggunaData instance = null;
	
	public static FrmKemaskiniPenggunaData getInstance(){
	    if (instance == null) instance = new FrmKemaskiniPenggunaData();
	    return instance;
	  
	}
	
	public void addBasicPengguna(String nama
		,String username
		,String password
		,String no_kp_baru
		,String id_negeri
		,String emel
		,String idKategori
		,String alamat1,String alamat2,String alamat3,String poskod
		,String xInd) throws Exception{
		 
		Db db = null;		 	
	 	Connection conn = null;
	    String sql = "";
	    try{	 
	    	if (isUserNamePenggunaExist(username)) {
	    		throw new Exception("User "+ username +" Already Exist");
	    	}
		    	
	    	long userId = DB.getNextID("USERS_SEQ");
	    	db = new Db();
	    	//Azam - Set AutoCommit to False.
	    	conn = db.getConnection();
	    	conn.setAutoCommit(false);
	    	Statement stmt = db.getStatement();	
	    	SQLRenderer r = new SQLRenderer();
			      
	    	//USERS
	    	r = new SQLRenderer();
	    	r.add("user_id",userId);
	    	r.add("user_login",username);
	    	r.add("user_password",PasswordService.encrypt(password)); 
	    	r.add("user_name", nama);
	    	r.add("user_role","online");
	    	r.add("date_registered",r.unquote("sysdate"));
	    	r.add("user_type","online");
	    	r.add("tarikh_masuk",r.unquote("sysdate"));
	    	sql = r.getSQLInsert("users");
	    	stmt.executeUpdate(sql);
			      
	    	//USERS_ONLINE
	    	r.clear();
			      
	    	r.add("user_id",userId);
	    	r.add("emel",emel); 
	    	r.add("no_kp_baru",no_kp_baru); 
	    	r.add("id_negeri",id_negeri);
	    	r.add("kategori", idKategori);
	    	r.add("tarikh_masuk",r.unquote("sysdate"));
	    	r.add("alamat1", alamat1);
	    	r.add("alamat2", alamat2);
	    	r.add("alamat3", alamat3);
	    	r.add("poskod", poskod);
	    	r.add("jenisxind", xInd);
	    	sql = r.getSQLInsert("users_online");
	    	stmt.executeUpdate(sql);
			     
	    	conn.commit();
		
	    } catch (SQLException se) { 
	    	try {
	    		conn.rollback();
		  	} catch (SQLException se2) {	
		  		throw new Exception("Rollback error:"+se2.getMessage());
		  	}throw new Exception("Ralat Pendaftaran Pengguna:"+se.getMessage());
			    
	    } finally {
	    	if (db != null) db.close();
	    }
			      
	}
	  
	public void addBasicPengguna(String nama,String username,String password,
				 String no_kp_baru,String id_negeri,String emel,String idKategori,
				 String alamat1,String alamat2,String alamat3,String poskod) throws Exception{
			 	Db db = null;
			 	
			 	Connection conn = null;
			    String sql = "";
			    Date now = new Date();
			    try
			    {	 
			    	  if (isUserNamePenggunaExist(username)) {
			    		  throw new Exception("User "+ username +" Already Exist");
			    	  }
			    	
			    	  long userId = DB.getNextID("USERS_SEQ");
				      db = new Db();
				      //Azam - Set AutoCommit to False.
			          conn = db.getConnection();
			          conn.setAutoCommit(false);
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();

				      
				      //USERS
				      r = new SQLRenderer();
				      r.add("user_id",userId);
				      r.add("user_login",username);
				      r.add("user_password",PasswordService.encrypt(password)); 
				      r.add("user_name", nama);
				      r.add("user_role","online");
				      r.add("date_registered",r.unquote("sysdate"));
				      r.add("user_type","online");
				      r.add("tarikh_masuk",r.unquote("sysdate"));
				      sql = r.getSQLInsert("users");
				      stmt.executeUpdate(sql);
				      
				      //USERS_ONLINE
				      //r = new SQLRenderer(); //No need to create new SQLRenderer.Instead, just use r.clear
				      r.clear();
				      
				      r.add("user_id",userId);
				      r.add("emel",emel); 
				      r.add("no_kp_baru",no_kp_baru); 
				      r.add("id_negeri",id_negeri);
				      r.add("kategori", idKategori);
				      r.add("tarikh_masuk",r.unquote("sysdate"));
				      
				      //diba tambah
				      r.add("alamat1", alamat1);
				      r.add("alamat2", alamat2);
				      r.add("alamat3", alamat3);
				      r.add("poskod", poskod);
				      
				      sql = r.getSQLInsert("users_online");
				      
				      stmt.executeUpdate(sql);
				      conn.commit();
				    } catch (SQLException se) { 
				    	try {
				    		conn.rollback();
				    	} catch (SQLException se2) {
				    		throw new Exception("Rollback error:"+se2.getMessage());
				    	}
				    	throw new Exception("Ralat Pendaftaran Pengguna:"+se.getMessage());
				    } finally {
				      if (db != null) db.close();
				    }
				      
			    }
	  
	 public void addPengguna(Hashtable data) throws Exception{
		 
		 	
		 	Db db = null;
		 	Connection conn = null;
		    String sql = "";
		    Date now = new Date();
		    try
		    {	 
		    	 String idPengguna = (String)data.get("idPengguna");
		    	  if (isUserNamePenggunaExist(idPengguna)) {
		    		  throw new Exception("User "+ idPengguna +" Already Exist");
		    	  }
		    	
		    	  long userId = DB.getNextID("USERS_SEQ");
		    	  String nama = (String)data.get("nama");
		    	  String noKP = (String)data.get("noKP");
			      String tarikhLahir = (String)data.get("tarikhLahir");
			      String tkhLahir = "to_date('" + tarikhLahir + "','dd/MM/yyyy')";
			      String umur = (String)data.get("umur");
			      String jantina = (String)data.get("jantina");
			      String tarafPerkahwinan = (String)data.get("tarafPerkahwinan");
			      String alamat1 = (String)data.get("alamat1");
			      String alamat2 = (String)data.get("alamat2");
			      String alamat3 = (String)data.get("alamat3");
			      String poskod = (String)data.get("poskod");
			      String id_negeri = (String)data.get("negeri");
			      String idKategori = (String)data.get("kategori");
			      String noTelRumah = (String)data.get("noTelRumah");
			      String noTelBimbit = (String)data.get("noTelBimbit");
			      String noFaks = (String)data.get("noFaks");
			      String emel = (String)data.get("emel");
			      
			      String kataLaluan = (String)data.get("kataLaluan");
			      
			      db = new Db();
			      //Azam - Set AutoCommit to False.
		          conn = db.getConnection();
		          conn.setAutoCommit(false);
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();

			      
			      //USERS
			      r = new SQLRenderer();
			      r.add("user_id",userId);
			      r.add("user_login",idPengguna);
			      r.add("user_password",PasswordService.encrypt(kataLaluan)); 
			      r.add("user_name", nama);
			      r.add("user_role","online");
			      r.add("date_registered",r.unquote("sysdate"));
			      r.add("user_type","online");
			      
			      sql = r.getSQLInsert("users");
			      stmt.executeUpdate(sql);
			      
			      //USERS_ONLINE
			      //r = new SQLRenderer(); //No need to create new SQLRenderer.Instead, just use r.clear
			      r.clear();
			      
			      r.add("user_id",userId);
			      r.add("no_kp_baru", noKP);
			      r.add("tarikh_lahir", r.unquote(tkhLahir));
			      r.add("umur", umur);
			      r.add("jantina", jantina);
			      r.add("taraf_perkahwinan", tarafPerkahwinan);
			      r.add("alamat1", alamat1);
			      r.add("alamat2", alamat2);
			      r.add("alamat3", alamat3);
			      r.add("poskod", poskod);
			      r.add("id_negeri", id_negeri);
			      r.add("kategori", idKategori);
			      r.add("no_tel", noTelRumah);
			      r.add("no_hp", noTelBimbit);
			      r.add("no_fax",noFaks); 
			      r.add("emel",emel); 
			      
			      sql = r.getSQLInsert("users_online");
			      
			      stmt.executeUpdate(sql);
			      conn.commit();
			    } catch (SQLException se) { 
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	throw new Exception("Ralat Pendaftaran Pengguna:"+se.getMessage());
			    } finally {
			      if (db != null) db.close();
			    }
			      
		    }
	 
	 public boolean isUserNamePenggunaExist(String user_login) {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();
			 r.add("user_login");
			 r.add("user_login",user_login);
			 sql = r.getSQLSelect("users");
			 ResultSet rs = stmt.executeQuery(sql);
			 if (rs.next()) {
				 return true;
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			 if (db != null) db.close();
		 }
		 return false;
	 }

	 public boolean isNoKPPenggunaExist(String no_kp_baru) {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();
			 r.add("no_kp_baru");
			 r.add("no_kp_baru",no_kp_baru);
			 sql = r.getSQLSelect("users_online");
			 //myLogger.info(sql);
			 ResultSet rs = stmt.executeQuery(sql);
			 if (rs.next()) {
				 return true;
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			 if (db != null) db.close();
		 }
		 return false;
	 }
}
