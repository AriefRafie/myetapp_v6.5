package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.hibernate.Session;

import ekptg.helpers.DB;
import ekptg.helpers.File;

public class FrmKJPDaftarOnlineData {
	
	private static Vector list = new Vector();
	private static Format formatter = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public static void addSPT(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try
	    {

		  db = new Db();
		      
	      //Table pptpermohonan
	      String tarikh_permohonan = (String)data.get("tarikh_permohonan");
	      String tarikh_kehendaki = (String)data.get("tarikh_kehendaki");
	      String tarikh_surat = (String)data.get("tarikh_surat");	       
	      String flag_peruntukan = (String)data.get("flag_peruntukan");
	      String flag_segera = (String)data.get("flag_segera");
	      String tujuan = (String)data.get("tujuan");
	      String alamat1 = (String)data.get("alamat1");
	      String alamat2 = (String)data.get("alamat2");
	      String alamat3 = (String)data.get("alamat3");
	      String poskod = (String)data.get("poskod");
	      String bandar = (String)data.get("bandar");
	      String rujukan_surat = (String)data.get("rujukan_kementerian");	      
	      String id_agensi = (String)data.get("agensi");  
	      String id_negeri = (String)data.get("negeri");
	      String id_daerah = (String)data.get("daerah");
	      
	      //table pfdfail
	      String suburusan = (String)data.get("suburusan");
	      String projek_negeri = (String)data.get("projek_negeri");
	      String kementerian = (String)data.get("kementerian");

	      //convert to date
	      String TP = "to_date('" + tarikh_permohonan + "','dd/MM/yyyy')";
	      String TK = "to_date('" + tarikh_kehendaki + "','dd/MM/yyyy')";
	      String TS = "to_date('" + tarikh_surat + "','dd/MM/yyyy')";
 
	      //status "PERMOHONAN HQ"
	      int status = 5;
	      //status "PERSERAHAN KAUNTER"
	      int flag_jenisserahan = 2;
	      
	      //generate no permohonan "JKPTG/PPT/kod_suburusan/this_year-000001
	      long idPermohonan = DB.getNextID("TBLPPTPERMOHONAN_SEQ");
	      long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
	      long idSenaraiSemak = DB.getNextID("TBLPPTSENARAISEMAK_SEQ");
	      int seq_number1 = 01;
	      int seq_number2 = 02;
	      int seq_number3 = 03;
	      int seq_number4 = 04;
	      String kodSubUrusan = "";
	      String kodJabatan = "JKPTG";
	      String module = "PPT";
	      Date now = new Date();
	      SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
	      String tahun = formatter.format(now);
	
	      
	      Statement stmturus = db.getStatement();
	      SQLRenderer rUrus = new SQLRenderer();				 
	      rUrus.add("id_Suburusan");
	      rUrus.add("kod_Suburusan");				      
	      rUrus.add("id_Suburusan",suburusan);				      
	      sql = rUrus.getSQLSelect("Tblrujsuburusan");
	      ResultSet rsUrus = stmturus.executeQuery(sql);
	      while (rsUrus.next()) {
	    	  kodSubUrusan = rsUrus.getString("kod_Suburusan");
	      }

	      
	      String noPermohonan = kodJabatan+"/"+module+"/"+kodSubUrusan+"/"+tahun+"-"+String.format("%06d",File.getSeqNo(seq_number1, seq_number2, seq_number3, seq_number4));

	      
	      Statement stmtF = db.getStatement();
	      SQLRenderer rF = new SQLRenderer();
	      rF.add("id_fail",idFail);
	      rF.add("id_suburusan", suburusan);
	      rF.add("id_negeri", projek_negeri);
	      rF.add("id_kementerian", kementerian);
	      sql = rF.getSQLInsert("tblpfdfail");
	      stmtF.executeUpdate(sql);
	      
	      Statement stmtPH = db.getStatement();
	      SQLRenderer rPH = new SQLRenderer();	
	      rPH.add("id_permohonan",idPermohonan);
	      rPH.add("no_permohonan",noPermohonan);
	      rPH.add("id_fail",idFail);
	      rPH.add("flag_peruntukan", flag_peruntukan);
	      rPH.add("flag_segera", flag_segera);
	      rPH.add("tujuan", tujuan);
	      rPH.add("alamat1", alamat1);
	      rPH.add("alamat2", alamat2);
	      rPH.add("alamat3", alamat3);
	      rPH.add("id_mukim", bandar);
	      rPH.add("poskod", poskod);
	      rPH.add("no_rujukan_surat", rujukan_surat);
	      rPH.add("tarikh_permohonan", rPH.unquote(TP));
	      rPH.add("tarikh_kehendaki", rPH.unquote(TK));
	      rPH.add("tarikh_surat", rPH.unquote(TS));
	      rPH.add("id_negeri", id_negeri);
	      rPH.add("id_daerah", id_daerah);
	      rPH.add("id_status", status);
	      rPH.add("flag_jenispermohonan", flag_jenisserahan);
	      sql = rPH.getSQLInsert("tblpptpermohonan");
	      stmtPH.executeUpdate(sql);
	      
	      if(id_agensi!="")
	      {
	    	  Statement stmtA = db.getStatement();
	    	  SQLRenderer rA = new SQLRenderer();	
	    	  rA.update("id_permohonan",idPermohonan); 
	    	  rA.add("id_agensi", id_agensi);
	    	  sql = rA.getSQLUpdate("tblpptpermohonan");
	    	  stmtA.executeUpdate(sql);
	      }
	  
	      
	      Statement stmtS = db.getStatement();
	      SQLRenderer rS = new SQLRenderer();
	      rS.add("id_senaraisemak",idSenaraiSemak);
	      rS.add("id_permohonan", idPermohonan);
	      sql = rS.getSQLInsert("tblpptsenaraisemak");
	      stmtS.executeUpdate(sql);
	      
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close add
	
	public static void add(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    try
	    {
	      //Table pptpermohonan
	      String tarikh_permohonan = (String)data.get("tarikh_permohonan");
	      String tarikh_kehendaki = (String)data.get("tarikh_kehendaki");
	      
	      String flag_peruntukan = (String)data.get("flag_peruntukan");
	      String flag_segera = (String)data.get("flag_segera");
	      String tujuan = (String)data.get("tujuan");
	      String alamat1 = (String)data.get("alamat1");
	      String alamat2 = (String)data.get("alamat2");
	      String alamat3 = (String)data.get("alamat3");
	      String poskod = (String)data.get("poskod");
	      String bandar = (String)data.get("bandar");
	      String rujukan_surat = (String)data.get("rujukan_kementerian");
	      
	      int id_bandar = Integer.parseInt(bandar);
	      String id_agensi = (String)data.get("agensi");  
	      String id_negeri = (String)data.get("negeri");
	      String id_daerah = (String)data.get("daerah");
	      
	      //table pfdfail
	      String suburusan = (String)data.get("suburusan");
	      String projek_negeri = (String)data.get("projek_negeri");
	      String kementerian = (String)data.get("kementerian");
	      
	      String TP = "to_date('" + tarikh_permohonan + "','dd/MM/yyyy')";
	      String TK = "to_date('" + tarikh_kehendaki + "','dd/MM/yyyy')";
	     
	      
	      int id_suburusan = Integer.parseInt(suburusan);
	      int id_projek_negeri = Integer.parseInt(projek_negeri);
	      int id_kementerian = Integer.parseInt(kementerian);
	      
	      int agensi=0;
	      
	      if(id_agensi!="")
	      {
	       agensi = Integer.parseInt(id_agensi);
	      }
	      int negeri = Integer.parseInt(id_negeri);
	      int daerah = Integer.parseInt(id_daerah);
	      
	      //status "DAFTAR"
	      int status = 5;
	      
	      db = new Db();
	      
	      //generate no permohonan "JKPTG/PPT/kod_suburusan/this_year/000001
	      long idPermohonan = DB.getNextID("TBLPPTPERMOHONAN_SEQ");
	      long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
	      long idSenaraiSemak = DB.getNextID("TBLPPTSENARAISEMAK_SEQ");
	      int seq_number1 = 01;
	      int seq_number2 = 02;
	      int seq_number3 = 03;
	      int seq_number4 = 04;
	      String kodSubUrusan = "";
	      String kodJabatan = "JKPTG";
	      String module = "PPT";
	      Date now = new Date();
	      SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
	      String tahun = formatter.format(now);
	    	
	      
	      Statement stmturus = db.getStatement();
	      SQLRenderer rUrus = new SQLRenderer();				 
	      rUrus.add("id_Suburusan");
	      rUrus.add("kod_Suburusan");				      
	      rUrus.add("id_Suburusan",id_suburusan);				      
	      sql = rUrus.getSQLSelect("Tblrujsuburusan");
	      ResultSet rsUrus = stmturus.executeQuery(sql);
	      while (rsUrus.next()) {
	    	  kodSubUrusan = rsUrus.getString("kod_Suburusan");
	      }
	      
	
	      
	      String noPermohonan = kodJabatan+"/"+module+"/"+kodSubUrusan+"/"+tahun+"/"+String.format("%06d",File.getSeqNo(seq_number1, seq_number2, seq_number3, seq_number4));

	      
	      Statement stmtF = db.getStatement();
	      SQLRenderer rF = new SQLRenderer();
	      rF.add("id_fail",idFail);
	  //  rF.add("no_fail",noFail);
	      rF.add("id_suburusan", id_suburusan);
	      rF.add("id_negeri", id_projek_negeri);
	      rF.add("id_kementerian", id_kementerian);
	      sql = rF.getSQLInsert("tblpfdfail");
	      stmtF.executeUpdate(sql);
	      
	      Statement stmtPH = db.getStatement();
	      SQLRenderer rPH = new SQLRenderer();	
	      rPH.add("id_permohonan",idPermohonan);
	      rPH.add("no_permohonan",noPermohonan);
	      rPH.add("id_fail",idFail);
	      rPH.add("flag_peruntukan", flag_peruntukan);
	      rPH.add("flag_segera", flag_segera);
	      rPH.add("tujuan", tujuan);
	      rPH.add("alamat1", alamat1);
	      rPH.add("alamat2", alamat2);
	      rPH.add("alamat3", alamat3);
	      rPH.add("id_mukim", id_bandar);
	      rPH.add("poskod", poskod);
	      rPH.add("no_rujukan_surat", rujukan_surat);
	      rPH.add("tarikh_permohonan", rPH.unquote(TP));
	      rPH.add("tarikh_kehendaki", rPH.unquote(TK));
	   //   rPH.add("id_agensi", agensi);
	      rPH.add("id_negeri", negeri);
	      rPH.add("id_daerah", daerah);
	      rPH.add("id_status", status);
	      sql = rPH.getSQLInsert("tblpptpermohonan");
	      stmtPH.executeUpdate(sql);
	      
	      if(agensi!=0)
	      {
	      Statement stmtA = db.getStatement();
	      SQLRenderer rA = new SQLRenderer();	
	      rA.update("id_permohonan",idPermohonan); 
	      rA.add("id_agensi", agensi);
	      sql = rA.getSQLUpdate("tblpptpermohonan");
	      stmtA.executeUpdate(sql);
	      }
	  
	      
	      Statement stmtS = db.getStatement();
	      SQLRenderer rS = new SQLRenderer();
	      rS.add("id_senaraisemak",idSenaraiSemak);
	      rS.add("id_permohonan", idPermohonan);
	      sql = rS.getSQLInsert("tblpptsenaraisemak");
	      stmtS.executeUpdate(sql);
	      
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close add

	  private static Session getSession() {
		return null;
	}

	private static String toString(long id_pptPermohonan) {
		return null;
	}

	public static Vector setList(String id_permohonan) throws Exception {
		  
	    Db db = null;
	    list.clear();
	    String sql = "";
	    try {
	    
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	     
	    		sql = "SELECT DISTINCT p.id_permohonan, p.id_fail, p.no_permohonan, f.id_suburusan, p.tarikh_permohonan, p.id_status, "; 
	    		sql += " f.id_kementerian, k.nama_kementerian, d.nama_daerah, pn.nama_negeri, p.id_agensi, p.flag_peruntukan, "; 
	    		sql += " p.flag_segera, f.id_negeri, p.id_daerah, p.tujuan, p.no_rujukan_surat, p.tarikh_kehendaki, "; 
	    		sql += " p.alamat1, p.alamat2, p.alamat3, p.poskod, p.id_negeri, p.id_mukim, f.no_fail, "; 
	    		sql += " p.no_rujukan_PTD, p.no_rujukan_PTG, p.no_rujukan_UPT, s.keterangan, "; 
				sql += " p.flag_jenispermohonan, p.tarikh_borangb, ag.nama_agensi ";  
				sql += " FROM Tblpfdfail f, Tblrujdaerah d, Tblrujnegeri pn, Tblrujkementerian k, Tblpptpermohonan p, Tblrujstatus s, Tblrujagensi ag "; 
				sql += " WHERE pn.id_negeri = f.id_negeri ";  
				sql += " AND p.id_agensi = ag.id_agensi(+) ";
				sql += " AND f.id_fail = p.id_fail ";  
				sql += " AND k.id_kementerian(+) = f.id_kementerian ";  
				sql += " AND s.id_status(+) = p.id_status ";  
				sql += " AND d.id_daerah(+) = p.id_daerah ";  
				sql += " AND p.id_permohonan = '"+id_permohonan+"'";
	      
	    		ResultSet rs = stmt.executeQuery(sql);
	    		Hashtable h;
	    		int bil = 1;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
	    			h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    			h.put("tarikh_borangb", rs.getString("tarikh_borangb")==null?"":rs.getString("tarikh_borangb"));
	        
	    			h.put("no_permohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));
	    			h.put("id_suburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
	    			h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
	    			list.addElement(h);
	    			bil++;
	    		}
	    		return list;
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}
	  }
	  
	  public static Vector getList(){
		  return list;
	  } 

	  public static void update(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    try
	    {
	     
	    	int id_permohonan = Integer.parseInt(data.get("id_permohonan").toString());
	    	int id_fail = Integer.parseInt(data.get("id_fail").toString());
	    	long id_suburusan = Long.parseLong(data.get("id_suburusan").toString());
	    	long id_kementerian = Long.parseLong(data.get("id_kementerian").toString());
	    	long id_agensi = Long.parseLong(data.get("id_agensi").toString());
	    	String flag_peruntukan = (String)data.get("flag_peruntukan");
	    	String flag_segera = (String)data.get("flag_segera");
	    	long id_negeri_projek = Long.parseLong(data.get("id_negeri_projek").toString());
	    	long id_daerah = Long.parseLong(data.get("id_daerah").toString());
	    	String tujuan = (String)data.get("tujuan");
	    	String no_rujukan_surat = (String)data.get("no_rujukan_surat");
	    	String tarikh_kehendaki = (String)data.get("tarikh_kehendaki");
	    	String TK = "to_date('" + tarikh_kehendaki + "','dd/MM/yyyy')";
	    	String alamat1 = (String)data.get("alamat1");
	    	String alamat2 = (String)data.get("alamat2");
	    	String alamat3 = (String)data.get("alamat3");
	    	String poskod = (String)data.get("poskod");
	    	long id_negeri = Long.parseLong(data.get("id_negeri").toString());
	    	int id_mukim = Integer.parseInt((String) data.get("id_mukim"));
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	SQLRenderer r = new SQLRenderer();
	    	r.update("id_permohonan", id_permohonan);
	    	r.add("id_agensi", id_agensi);
	    	r.add("flag_peruntukan", flag_peruntukan);
	    	r.add("flag_segera", flag_segera);
	    	r.add("id_daerah", id_daerah);
	    	r.add("tujuan", tujuan);
	    	r.add("no_rujukan_surat", no_rujukan_surat);
	    	r.add("tarikh_kehendaki",r.unquote(TK));
	    	r.add("alamat1", alamat1);
	    	r.add("alamat2", alamat2);
	    	r.add("alamat3", alamat3);
	    	r.add("poskod", poskod);
	    	r.add("id_negeri", id_negeri);
	    	r.add("id_mukim", id_mukim);
	    	sql = r.getSQLUpdate("Tblpptpermohonan");
	    	stmt.executeUpdate(sql);
	      
	    	Statement stmtF = db.getStatement();
	    	SQLRenderer rF = new SQLRenderer();
	    	rF.update("id_fail", id_fail);
	    	rF.add("id_suburusan", id_suburusan);
	    	rF.add("id_kementerian", id_kementerian);
	      
	    	r.add("id_negeri", id_negeri_projek);
	    	sql = rF.getSQLUpdate("Tblpfdfail");
	    	stmtF.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void delete(String uid) throws Exception {
	    Db db = null;
	    int idNegeri= Integer.parseInt(uid);
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_negeri", idNegeri);
	      sql = r.getSQLDelete("tblrujnegeri");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  public static void setData(String id_permohonan)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT distinct p.id_permohonan, p.no_permohonan, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan ";
		      sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Tblrujagensi a ";
		      sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		      sql +="AND p.id_status = s.id_status ";
		      if(id_permohonan != null)
		    	  sql +="AND p.no_permohonan LIKE '"+id_permohonan+"' ";

		      ResultSet rs = stmt.executeQuery(sql);
		      //Vector list = new Vector();
		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("no_permohonan",rs.getString("no_permohonan"));
		    	  if(rs.getString("nama_suburusan") == null){
			    	h.put("id_suburusan","");
		    	  }else{
			    	h.put("id_suburusan",rs.getString("nama_suburusan"));
		    	  }
		    	  h.put("tarikh_permohonan",formatter.format(rs.getDate("tarikh_permohonan")));
		    	  h.put("id_status",rs.getString("keterangan"));
		    	  if(rs.getString("nama_kementerian") == null){
		    		 h.put("id_kementerian","");
		    	  }else{
		    		 h.put("id_kementerian",rs.getString("nama_kementerian"));
		    	  }
		    	  if(rs.getString("id_agensi") == null){
		    		 h.put("id_agensi","");
		    	  }else{
			    	h.put("id_agensi",rs.getString("id_agensi"));
		    	  }
		    	  h.put("flag_peruntukan",rs.getString("flag_peruntukan"));
		    	  h.put("flag_segera",rs.getString("flag_segera"));
		    	  if(rs.getString("id_negeri") == null){
		    		h.put("id_negeri_projek","");
		    	  }else{
			    	h.put("id_negeri_projek",rs.getString("id_negeri"));
		    	  }
		    	  if(rs.getString("id_daerah") == null){
			    	h.put("id_daerah","");
		    	  }else{
			    	h.put("id_daerah",rs.getString("id_daerah"));
		    	  }
		    	  h.put("no_rujukan_surat",rs.getString("no_rujukan_surat"));
		    	  if(rs.getString("tarikh_kehendaki") == null){
			    	h.put("tarikh_kehendaki","");
		    	  }else{
			    	h.put("tarikh_kehendaki",formatter.format(rs.getDate("tarikh_kehendaki")));
		    	  }
		    	  h.put("alamat1",rs.getString("alamat1"));
		    	  h.put("alamat2",rs.getString("alamat2"));
		    	  h.put("alamat3",rs.getString("alamat3"));
		    	  h.put("poskod",rs.getString("poskod"));
		    	  if(rs.getString("id_negeri") == null){
		    		h.put("id_negeri","");
		    	  }else{
			    	h.put("id_negeri",rs.getString("id_negeri"));
		    	  }
		    	  if(rs.getString("id_mukim") == null){
			    	h.put("id_mukim","");
		    	  }else{
			    	h.put("id_mukim",rs.getString("id_mukim"));
		    	  }
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	  
	  public static void sahkan(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		     
		      int id_permohonan = (Integer)data.get("id_permohonan");
		      long id_status = Long.parseLong(data.get("id_status").toString());
		      
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_permohonan", id_permohonan);
		      r.add("id_status", id_status);
		      
		      sql = r.getSQLUpdate("Tblpptpermohonan");
		      stmt.executeUpdate(sql);
		      		
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	  
	  public static void lulus(Hashtable data) throws Exception {
		  Db db = null;
		    String sql = "";
		    try
		    {
		     
		      int id_permohonan = (Integer)data.get("id_permohonan");
		      int id_fail = (Integer)data.get("id_fail");
		      int id_negeri = (Integer)data.get("id_negeri");
		      int id_kementerian = (Integer)data.get("id_kementerian");
		      long id_status = Long.parseLong(data.get("id_status").toString());
		      
//		      generate no Fail "JKPTG(S)/101/SPT/881/kod_kementerian/kod_negeri-seq_fail
		      String KodFailJabatan = "JKPTG(S)/101/SPT/881";
//		      String SPT = "SPT";
		      String kod_kementerian = "";
		      String kodMampu = "";
		      
		      db = new Db();
		      
		      Statement stmtMT = db.getStatement();
		      SQLRenderer rMT = new SQLRenderer();				 
		      rMT.add("id_kementerian");
		      rMT.add("kod_kementerian");				      
		      rMT.add("id_kementerian",id_kementerian);				      
		      sql = rMT.getSQLSelect("Tblrujkementerian");
		      ResultSet rsMT = stmtMT.executeQuery(sql);
		      while (rsMT.next()) {
		    	  kod_kementerian = rsMT.getString("kod_kementerian");
		      }
		      
		      Statement stmtnegeri = db.getStatement();
		      SQLRenderer rnegeri = new SQLRenderer();				 
		      rnegeri.add("id_negeri");
		      rnegeri.add("kod_mampu");				      
		      rnegeri.add("id_negeri",id_negeri);				      
		      sql = rnegeri.getSQLSelect("Tblrujnegeri");
		      ResultSet rsnegeri = stmtnegeri.executeQuery(sql);
		      while (rsnegeri.next()) {
		    	  kodMampu = rsnegeri.getString("kod_mampu");
		      }
		      
		      String noFail = KodFailJabatan+"/"+kod_kementerian+"/"+kodMampu+"-"+String.format("%06d",File.getSeqNo(1, 17, id_kementerian, id_negeri));
		      
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_permohonan", id_permohonan);
		      r.add("id_status", id_status);
		      
		      sql = r.getSQLUpdate("Tblpptpermohonan");
		      stmt.executeUpdate(sql);
		      
		      Statement stmtF = db.getStatement();
			  SQLRenderer rF = new SQLRenderer();
			  rF.update("id_fail", id_fail);
			  rF.add("no_Fail", noFail);
		      
		      sql = rF.getSQLUpdate("Tblpfdfail");
		      stmtF.executeUpdate(sql);
		      		
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	  
	 	public static String generateNoFail(Integer negeri, Integer seksyen, Integer urusan, String suburusan, String taraf) throws Exception{
	 		String sql = "";
			String noFail = "";
			String noFailStandard = "JKPTG/101/";
			Db db = null;
			db = new Db();
		    Statement stmt = db.getStatement();
		    
		    if (suburusan != ""){
		    	sql = "select b.kod_Suburusan, c.kod_Urusan, d.kod_Taraf_Keselamatan, e.abbrev, f.kod_Seksyen"+
				" from  tblrujsuburusan b,tblrujurusan c,tblpfdrujtarafkeselamatan d, tblrujnegeri e, tblrujseksyen f"+
				" where b.id_Suburusan = " + suburusan + 
				" and c.id_Urusan = " + urusan + 
				" and d.id_Tarafkeselamatan = " + taraf +
				" and e.id_Negeri = " + negeri +
				" and f.id_Seksyen = " + seksyen;
		    	
		    	ResultSet rs = stmt.executeQuery(sql);
				rs.next();
				
				if (negeri != null){
					
					if  (negeri == 16){
						
						if (rs.getString(3) != null){
						
							if (urusan != null && suburusan != null){
								noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(5) + "/" + rs.getString(2) + "/" + rs.getString(1) + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));
							}
							else if (urusan != null && suburusan == null){
								noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(5) + "/" + rs.getString(2) + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));	
							}
						}
						else{
							if (urusan != null && suburusan != null){
								noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(2) + "/" + rs.getString(1) + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));
							}
							else if (urusan != null && suburusan == null){
								noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(2) + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));

							}
						}
						
					}
					else{
						
						if (rs.getString(3) != null){
							
							if (urusan != null && suburusan != null){
								noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(2) + "/" + rs.getString(1) + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));
							}
							else if (urusan != null && suburusan == null){
								noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(2) + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));

							}
						}
						else{
							
							if (urusan != null && suburusan != null){
								noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(2) + "/" + rs.getString(1) + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));
							}
							else if (urusan != null && suburusan == null){
								noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(2) + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));

							}
						}
						
					}
				}

				return noFail;
			}
		    else{
		    	sql = "select c.kod_Urusan, d.kod_Taraf_Keselamatan, e.abbrev, f.kod_Seksyen"+
				" from tblrujurusan c,tblpfdrujtarafkeselamatan d, tblrujnegeri e, tblrujseksyen f"+
				" where c.id_Urusan = " + urusan + 
				" and d.id_Tarafkeselamatan = " + taraf +
				" and e.id_Negeri = " + negeri +
				" and f.id_Seksyen = " + seksyen;
		    	
		    	ResultSet rs = stmt.executeQuery(sql);
				rs.next();
				
				if (negeri != null){
					
					if  (negeri == 16){
						
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(4) + "/" + rs.getString(1) + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));

						}
						else{
						
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(1) + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));

						}						
					}
					else{
						
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(1) + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));

						}
						else{
						
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(1) + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));

						}
						
					}
				}

				return noFail;
		    	
		    }
			
	 	}
	 	
	 	public static Vector getNamaAgensi(String idAgensi)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("a.nama_agensi");	 
		     
		      r.add("a.id_agensi",idAgensi);
		      
		      sql = r.getSQLSelect("tblrujagensi a");
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("nama_agensi",rs.getString("nama_agensi"));	    	 
		    	  list.addElement(h);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }	
	 	
	 	public static void hantar(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		      String id_user = (String)data.get("id_user");
			  String id_permohonan = (String)data.get("id_permohonan");
		      
			
			  String status_notisawam = "52"; //notis awam
			 
			  db = new Db();
		      Statement stmtP = db.getStatement();
		      SQLRenderer rP = new SQLRenderer();
		      
		      rP.update("id_permohonan", id_permohonan );
		      rP.add("id_status", status_notisawam); 	
		      rP.add("id_kemaskini",id_user);
			  rP.add("tarikh_kemaskini",rP.unquote("sysdate"));
		      sql = rP.getSQLUpdate("tblpptpermohonan");
		      stmtP.executeUpdate(sql);
 
		     
		    }
		    finally {
		      if (db != null) db.close();
		    }
	}//close hantar
}
