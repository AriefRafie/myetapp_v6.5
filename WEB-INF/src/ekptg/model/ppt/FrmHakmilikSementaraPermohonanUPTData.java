package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.File;

public class FrmHakmilikSementaraPermohonanUPTData {
	static Logger myLogger = Logger.getLogger(FrmHakmilikSementaraPermohonanUPTData.class);
	
	Vector listPohon = null;
	Vector listPohon2 = null;
	Vector listMaklumatTanah = null;
	Vector listDokumen = null;
	Vector negeriLogin = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private static FrmHakmilikSementaraPermohonanUPTData instance = null;
	
	 public static FrmHakmilikSementaraPermohonanUPTData getInstance()
	  {
	    if (instance == null) instance = new FrmHakmilikSementaraPermohonanUPTData();
	    return instance;
	  }
	//--Get alamat Kementerian--//
	 public static Vector getAlamatKementerian(String idK)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT k.alamat1, k.alamat2, k.alamat3, k.poskod, n.id_negeri ";
		      sql +="FROM Tblrujkementerian k, Tblrujnegeri n ";
		      sql +="WHERE k.id_negeri = n.id_negeri ";
		      sql +="AND k.id_kementerian = "+idK+" ";

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = null;
		      
		      Hashtable h = null;
		     
		      while (rs.next()) {
		    	  list = new Vector();
		    	  h = new Hashtable();
		    	  h.put("id_negeri", rs.getString("id_negeri"));
		    	  h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
		    	  h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
		    	  h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
		    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    	  list.addElement(h);
		    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }//find alamat kementerian
	 public static Vector getJenisHakmilik(String idJenisHakmilik)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK =  '"+idJenisHakmilik +"'";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = null;
		      
		      Hashtable h = null;
		     
		      while (rs.next()) {
		    	  list = new Vector();
		    	  h = new Hashtable();
		    	  h.put("kodJenisHakmilik", rs.getString("KOD_JENIS_HAKMILIK"));
		    	  list.addElement(h);
		    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }//find a
	//sahkan (status change to "127")
		public static void sahkan(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		   
		    try
		    {
		     
		      String id_permohonan = (String)data.get("id_permohonan");
		      String id_fail = (String)data.get("id_fail");
		      String projeknegeri = (String)data.get("projeknegeri");
		      String id_kementerian = (String)data.get("id_kementerian");
		      String id_daerah = (String)data.get("id_daerah");
		      String daerah = (String)data.get("daerah");
		      String id_status = (String)data.get("id_status");
		      
		      //-- Generate no Fail "JKPTG(S)/101/SPT/881/kod_kementerian/kod_negeri-seq_fail
		      String KodFailJabatan = "JKPTG(S).";
		      String kod_kementerian = "";
		      String abbrev = "";
		      String kodDaerah = "";
		      int seq_id_seksyen = 1;
		      int seq_id_urusan = 17;
		      
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
		      rnegeri.add("abbrev");				      
		      rnegeri.add("id_negeri",projeknegeri);				      
		      sql = rnegeri.getSQLSelect("Tblrujnegeri");
		
		      ResultSet rsnegeri = stmtnegeri.executeQuery(sql);
		      while (rsnegeri.next()) {
		    	  abbrev = rsnegeri.getString("abbrev");
		      }
		      
		      Statement stmtDaerah = db.getStatement();
		      SQLRenderer rDaerah = new SQLRenderer();	
		      rDaerah.add("id_daerah");
		      rDaerah.add("kod_daerah");				      
		      rDaerah.add("id_daerah",id_daerah);				      
		      sql = rDaerah.getSQLSelect("Tblrujdaerah");
		      ResultSet rsDaerah = stmtnegeri.executeQuery(sql);
		      while (rsDaerah.next()) {
		    	  kodDaerah = rsDaerah.getString("kod_daerah");
		      }
		      
		      String seq = String.format("%06d",File.getSeqNo(seq_id_seksyen,seq_id_urusan));
		      String noFail = KodFailJabatan + abbrev + "/"+ kodDaerah + "/881/" +kod_kementerian+"/"+seq;
		      
		      //update status 128
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_permohonan", id_permohonan);
		      r.add("id_status", id_status);
		      
		      sql = r.getSQLUpdate("Tblpptpermohonan");
		      stmt.executeUpdate(sql);
		      
		      //masukkan nofail(generated) ke pfdfail
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
		  }//close sahkan
		//luluskan id status = 128 & generate no fail baru
		public static void lulus(Hashtable data) throws Exception {
			  Db db = null;
			    String sql = "";
			    try
			    {
			     
			      String id_permohonan = (String)data.get("id_permohonan");
			      String id_fail = (String)data.get("id_fail");
			      String id_negeri = (String)data.get("id_negeri");
			      String id_kementerian = (String)data.get("id_kementerian");
			      String id_status = (String)data.get("id_status");
			      
			      //-- Generate no Fail "JKPTG(S)/101/SPT/881/kod_kementerian/kod_negeri-seq_fail
			      String KodFailJabatan = "JKPTG(S)/101/SPT/881";
			      String kod_kementerian = "";
			      String kodMampu = "";
			      int seq_number1 = 01;
			      int seq_number2 = 02;
			      int seq_number3 = 03;
			      int seq_number4 = 04;
			      
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
			      
			      String noFail = KodFailJabatan+"/"+kod_kementerian+"/"+kodMampu+"-"+String.format("%06d",File.getSeqNo(seq_number1, seq_number2, seq_number3, seq_number4));
			      
			      //update status 128
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_permohonan", id_permohonan);
			      r.add("id_status", id_status);
			      
			      sql = r.getSQLUpdate("Tblpptpermohonan");
			      stmt.executeUpdate(sql);
			      
			      //masukkan nofail(generated) ke pfdfail
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
		}//close luluskan
	 public static String addUPT(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    String output = "";
	   
	    try
	    {
	      //Table pptpermohonan
	      String tarikh_permohonan = (String)data.get("tarikh_permohonan");
	      String tarikh_kehendaki = (String)data.get("tarikh_kehendaki");
	      String tarikh_surat = (String)data.get("tarikh_surat");
	      String noRujPTG = (String)data.get("noRujPTG");
	      String noRujPTD = (String)data.get("noRujPTD");
	      String flag_peruntukan = (String)data.get("flag_peruntukan");
	      String flag_segera = (String)data.get("flag_segera");
	      String tujuan = (String)data.get("tujuan");
	      String alamat1 = (String)data.get("alamat1");
	      String alamat2 = (String)data.get("alamat2");
	      String alamat3 = (String)data.get("alamat3");
	      String poskod = (String)data.get("poskod");
//	      String id_bandar = (String)data.get("bandar");
	      String rujukan_surat = (String)data.get("rujukan_kementerian");
	      String id_agensi = (String)data.get("agensi");  
	      String id_negeri = (String)data.get("negeri");
	      String id_daerah = (String)data.get("daerah");
	      String id_user = (String)data.get("id_user");
	      //table pfdfail
	      String id_suburusan = (String)data.get("suburusan");
	      String id_projek_negeri = (String)data.get("projek_negeri");
	      String id_kementerian = (String)data.get("kementerian");
	      
	      SimpleDateFormat abc =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
	      
	      String TP = "to_date('" + tarikh_permohonan + "','dd/MM/yyyy')";
	      String TK = "to_date('" + tarikh_kehendaki + "','dd/MM/yyyy')";
	      String TS = "to_date('" + tarikh_surat + "','dd/MM/yyyy')";
	       
	      //status "PERMOHONAN HQ"
	      int status = 11;
	      //status "PERMOHONAN ONLINE"
	      int flag_jenisserahan = 2;
	      
	      db = new Db();
	      
	      //generate no permohonan "JKPTG/PPT/kod_suburusan/this_year-000001
	      long idPermohonan = DB.getNextID("TBLPPTPERMOHONAN_SEQ");
	      long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
	      long idSenaraiSemak = DB.getNextID("TBLPPTSENARAISEMAK_SEQ");
	      int seq_id_seksyen = 1;
		  int seq_id_urusan = 17;    	
	      String kodSubUrusan = "";
	      String kodJabatan = "JKPTG";
	      String module = "PPT";
	      Date now = new Date();
	      SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
	      String tahun = formatter.format(now);
	      int thn = Integer.parseInt(tahun);
	    	     
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
	      
	      String seq = String.format("%06d",File.getSeqNo(1,1,0,0,0,false,false,thn,0));	    	
	      String noPermohonan = tahun+"-"+seq;
	      	      
	      
	      Statement stmtF = db.getStatement();
	      SQLRenderer rF = new SQLRenderer();
	      rF.add("id_fail",idFail);
	      rF.add("id_suburusan", id_suburusan);
	      rF.add("id_negeri", id_projek_negeri);
	      rF.add("id_kementerian", id_kementerian);
	      rF.add("tarikh_masuk",rF.unquote("sysdate"));
		  rF.add("id_masuk",id_user);
	      sql = rF.getSQLInsert("tblpfdfail");
	      stmtF.executeUpdate(sql);
	      
	      Statement stmtPH = db.getStatement();
	      SQLRenderer rPH = new SQLRenderer();	
	      rPH.add("id_permohonan",idPermohonan);
	      rPH.add("no_permohonan",noPermohonan);
	      rPH.add("no_rujukan_ptg",noRujPTG);
	      rPH.add("no_rujukan_ptd",noRujPTD);
	      rPH.add("id_fail",idFail);
	      rPH.add("flag_peruntukan", flag_peruntukan);
	      rPH.add("flag_segera", flag_segera);
	      rPH.add("tujuan", tujuan);
	      rPH.add("alamat1", alamat1);
	      rPH.add("alamat2", alamat2);
	      rPH.add("alamat3", alamat3);
//	      rPH.add("id_mukim", id_bandar);
	      rPH.add("poskod", poskod);
	      rPH.add("no_rujukan_surat", rujukan_surat);
	      rPH.add("tarikh_permohonan", rPH.unquote(TP));
	      rPH.add("tarikh_kehendaki", rPH.unquote(TK));
	      rPH.add("tarikh_surat", rPH.unquote(TS));
	      rPH.add("id_agensi", id_agensi);
	      rPH.add("id_negeri", id_negeri);
	      rPH.add("id_daerah", id_daerah);
	      rPH.add("id_status", status);
	      rPH.add("flag_jenispermohonan", flag_jenisserahan);
	      rPH.add("tarikh_masuk",rPH.unquote("sysdate"));
		  rPH.add("id_masuk",id_user);
	      sql = rPH.getSQLInsert("tblpptpermohonan");
	      stmtPH.executeUpdate(sql);
	      
	      output = ""+idPermohonan;
	     	
	      
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	    return output;
	  }//close add
	 
	 public void setListPohon(String id) throws Exception {
			Db db = null;
			String sql = "";
			
			
			try{
				listPohon = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			
			r.add("p.id_permohonan");
			r.add("p.id_status");
			r.add("p.flag_jenispermohonan");
			r.add("p.no_permohonan");
			r.add("f.no_fail");
			r.add("f.id_fail");
			r.add("f.id_negeri as projek_negeri");
			r.add("n.nama_negeri");
			r.add("n.id_negeri");
			r.add("p.tarikh_permohonan");
			r.add("p.tujuan");
			r.add("k.nama_kementerian");
			r.add("k.id_kementerian");
			r.add("s.keterangan");
			r.add("d.nama_daerah");
			r.add("d.id_daerah");
			r.add("p.tarikh_kehendaki");
			r.add("p.tarikh_surat");
			//r.add("a.nama_agensi");
			r.add("p.id_agensi");
			r.add("p.no_rujukan_surat");
			r.add("p.flag_peruntukan");
			r.add("p.flag_segera");
//			r.add("p.id_mukim");
			r.add("p.alamat1");
			r.add("p.alamat2");
			r.add("p.alamat3");
			r.add("us.nama_suburusan");
			r.add("us.id_suburusan");
			r.add("p.poskod");
//			r.add("m.nama_mukim");
			r.add("p.no_rujukan_ptg");
			r.add("p.no_rujukan_ptd");
			
			r.add("f.id_fail",r.unquote("p.id_fail"));
			r.add("k.id_kementerian",r.unquote("f.id_kementerian"));
			r.add("n.id_negeri",r.unquote("p.id_negeri"));
			r.add("o.id_negeri",r.unquote("f.id_negeri"));
			r.add("s.id_status",r.unquote("p.id_status"));
			r.add("d.id_daerah",r.unquote("p.id_daerah"));
			//r.add("a.id_agensi",r.unquote("p.id_agensi"));
			r.add("us.id_suburusan",r.unquote("f.id_suburusan"));
//			r.add("d.id_daerah",r.unquote("m.id_daerah"));
//			r.add("m.id_mukim",r.unquote("p.id_mukim"));
			
			r.add("p.id_Permohonan",id);
			
			sql = r.getSQLSelect("Tblrujsuburusan us, Tblpfdfail f, Tblrujdaerah d, Tblrujnegeri n, Tblrujkementerian k, Tblrujstatus s, Tblpptpermohonan p, Tblrujnegeri o");
			myLogger.info("SQL setListPohon :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
//				h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
//				h.put("mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
				h.put("idNegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
				h.put("idFail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
				h.put("idDaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
				h.put("idSuburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
				h.put("idAgensi", rs.getString("id_agensi")==null?"0":rs.getString("id_agensi"));
				h.put("idKementerian", rs.getString("id_kementerian")==null?"":rs.getString("id_kementerian"));
				h.put("noPermohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));
				h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
				h.put("namaNegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
				h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));
				h.put("tujuan", rs.getString("tujuan")==null?"":rs.getString("tujuan"));
				h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
				h.put("status", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
				h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"":sdf.format(rs.getDate("tarikh_kehendaki")));
				h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":sdf.format(rs.getDate("tarikh_surat")));
				//h.put("nama_agensi", rs.getString("nama_agensi"));
				h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"":rs.getString("no_rujukan_surat"));
				h.put("flag_peruntukan", rs.getString("flag_peruntukan")==null?"":rs.getString("flag_peruntukan"));
				h.put("flag_segera", rs.getString("flag_segera")==null?"":rs.getString("flag_segera"));
				h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
				h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
				h.put("noRujPTG", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
				h.put("noRujPTD", rs.getString("no_rujukan_ptd")==null?"":rs.getString("no_rujukan_ptd"));
				h.put("projek_negeri", rs.getString("projek_negeri")==null?"":rs.getString("projek_negeri"));

				
				if(rs.getLong("flag_jenispermohonan") == 1){
		    		h.put("flag_jenispermohonan","PERMOHONAN ONLINE");
		    	}
				else if(rs.getLong("flag_jenispermohonan") == 2){
		    		h.put("flag_jenispermohonan","PERMOHONAN KAUNTER");
		    	}else{
		    		h.put("flag_jenispermohonan","-");
		    	}
				listPohon.addElement(h);
			}
			}
			finally {
				if(db != null) db.close();
			}
			
		}//close setlistpohon
	 
	 public void setListPohon2(String id) throws Exception {
			Db db = null;
			String sql = "";
			
			try{
				listPohon2 = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			
			r.add("p.id_permohonan");
			r.add("p.id_fail");
			r.add("n.nama_negeri");
			r.add("n.id_negeri");
			
			r.add("f.id_fail",r.unquote("p.id_fail"));
			r.add("n.id_negeri",r.unquote("f.id_negeri"));
			
		
			r.add("p.id_Permohonan",id);
			
			sql = r.getSQLSelect("Tblpfdfail f, Tblrujnegeri n, Tblpptpermohonan p");
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
				h.put("projek_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
				h.put("idProjekNegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		
				listPohon2.addElement(h);
			}
			}
			finally {
				if(db != null) db.close();
			}
			
		}//close list pohon2
	 
	 public void setListMaklumatTanah(String id) throws Exception{
			Db db = null;
			String sql = "";
			
			try {
				listMaklumatTanah = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("p.id_permohonan");			
				r.add("ls.keterangan");
				//r.add("ls2.keterangan");
				r.add("lt.keterangan");
				r.add("m.id_hakmilik");
				r.add("m.id_negeri");
				r.add("n.nama_negeri");
				r.add("m.id_jenishakmilik");
				r.add("m.id_daerah");
				r.add("m.id_mukim");
				r.add("mk.nama_mukim");
				r.add("m.id_unitluaslot");
				//r.add("m.id_unitluasambil");
				r.add("m.luas_ambil");
				r.add("m.no_hakmilik");
				r.add("m.no_lot");
				r.add("m.luas_lot");
				r.add("m.no_pt");
				
			
				r.add("m.id_Permohonan",r.unquote("p.id_Permohonan(+)"));
				r.add("m.id_negeri",r.unquote("n.id_negeri"));
				r.add("ls.id_luas",r.unquote("m.id_unitluaslot"));
				r.add("m.id_lot",r.unquote("lt.id_lot"));
				//r.add("ls2.id_luas",r.unquote("m.id_unitluasambil"));
				r.add("m.id_mukim",r.unquote("mk.id_mukim"));
				//r.add("m.id_luas",r.unquote("ls.id_luas"));
				
				r.add("p.id_Permohonan",id);
				
				
				sql = r.getSQLSelect("Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m ");
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
				int count = 0;
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("kod_lot", rs.getString(3)==null?"":rs.getString(3));
					h.put("unitluaslot", rs.getString(2)==null?"":rs.getString(2));
					//h.put("unitluasambil", rs.getString(3)==null?"":rs.getString(3));
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
					h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
					h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
					//h.put("id_unitluasambil", rs.getString("id_unitluasambil")==null?"":rs.getString("id_unitluasambil"));
					h.put("luas_ambil", rs.getString("luas_ambil")==null?"":rs.getString("luas_ambil"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik").toUpperCase());
					h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
					h.put("luas_lot", rs.getString("luas_lot")==null?"":rs.getString("luas_lot"));
					h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
					listMaklumatTanah.addElement(h);
					bil++;	
					count++;
				}
				if (count == 0){
					h = new Hashtable();
					h.put("bil", "");
					h.put("id_permohonan", "");
					h.put("kod_lot","");
					h.put("unitluaslot", "");
					//h.put("unitluasambil", rs.getString(3)==null?"":rs.getString(3));
					h.put("id_hakmilik", "");
					h.put("id_negeri", "");
					h.put("nama_negeri", "");
					h.put("id_jenishakmilik", "");
					h.put("id_daerah", "");
					h.put("id_mukim", "");
					h.put("nama_mukim", "");
					h.put("id_unitluaslot", "");
					//h.put("id_unitluasambil", rs.getString("id_unitluasambil")==null?"":rs.getString("id_unitluasambil"));
					h.put("luas_ambil", "");
					h.put("no_hakmilik", "Tiada rekod.");
					h.put("no_lot", "");
					h.put("luas_lot", "");
					h.put("no_pt", "");
					listMaklumatTanah.addElement(h);
					
				}
				//return list;
			}finally {
				if(db != null) db.close();
			}
			}//close setDataListKertas
	 
	 public static void update(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		   
		    try
		    {
		      
		      String id = (String)data.get("id_permohonan");
		      String idFail = (String)data.get("id_fail");
		      String idUrusan = (String)data.get("id_urusan");
		      String id_user = (String)data.get("id_user");
		    
		     
		      //System.out.println("update check id :: " + idUrusan);
		      
		      //String tarikh_pohon = (String)data.get("tarikh_pohon"); 
			 //String suburusan = (String)data.get("suburusan");
			  String daerah = (String)data.get("daerah");
			  String projeknegeri = (String)data.get("projeknegeri");
			  String negeri = (String)data.get("negeri");
//			  String bandar = (String)data.get("bandar");
			  String agensi = (String)data.get("agensi");
			  String _kementerian = (String)data.get("kementerian");
			  String noRujPTG = (String)data.get("noRujPTG");
		      String noRujPTD = (String)data.get("noRujPTD");
			  String poskod = (String)data.get("poskod");
			  String tujuan = (String)data.get("tujuan");
			  String rujukan_kementerian = (String)data.get("rujukan_kementerian");
			  String tarikh_hendak = (String)data.get("tarikh_hendak");
			  String tarikh_surat = (String)data.get("tarikh_surat");
			  String alamat1 = (String)data.get("alamat1");
			  String alamat2 = (String)data.get("alamat2");
			  String alamat3 = (String)data.get("alamat3");
			  String flagPeruntukan = (String)data.get("flag_peruntukan");
			  String flagSegera = (String)data.get("flag_segera");
			  
			  
			  //String TP = "to_date('" + tarikh_pohon + "','dd/MM/yyyy')";
			  String TH = "to_date('" + tarikh_hendak + "','dd/MM/yyyy')";
			  String TS = "to_date('" + tarikh_surat + "','dd/MM/yyyy')";
			  //int idSuburusan = Integer.parseInt(suburusan);
			  int idDaerah = Integer.parseInt(daerah);
			  int idProjekNegeri = Integer.parseInt(projeknegeri);
			  int idNegeri = Integer.parseInt(negeri);
//			  int idBandar = Integer.parseInt(bandar);
			  //int idAgensi = Integer.parseInt(agensi);
			  int idKementerian = Integer.parseInt(_kementerian);
			  
			  int id_agensi=0;
		      
		      if(agensi!="")
		      {
		    	  id_agensi = Integer.parseInt(agensi);
		      }
			  
			  
			  db = new Db();
			  
			  if(id_agensi!=0)
		      {
		      Statement stmtA = db.getStatement();
		      SQLRenderer rA = new SQLRenderer();	
		      rA.update("id_permohonan",id); 
		      rA.add("id_agensi", id_agensi);
		      sql = rA.getSQLUpdate("tblpptpermohonan");
		      stmtA.executeUpdate(sql);
		      }
			  if(id_agensi==0)
		      {
		      Statement stmtA = db.getStatement();
		      SQLRenderer rA = new SQLRenderer();	
		      rA.update("id_permohonan",id); 
		      rA.add("id_agensi", "");
		      sql = rA.getSQLUpdate("tblpptpermohonan");
		      stmtA.executeUpdate(sql);
		      }
			  
			  
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_permohonan", id);
			  //r.add("tarikh_permohonan", r.unquote(TP));
			  r.add("no_rujukan_ptg",noRujPTG);
		      r.add("no_rujukan_ptd",noRujPTD);
			  r.add("tarikh_kehendaki", r.unquote(TH));
			  r.add("tarikh_surat", r.unquote(TS));
			  r.add("poskod", poskod);
			  r.add("tujuan", tujuan);
			  r.add("no_rujukan_surat", rujukan_kementerian);
//			  r.add("alamat1", alamat1);
//			  r.add("alamat2", alamat2);
//			  r.add("alamat3", alamat3);
//			  r.add("id_negeri",idNegeri);
			  //r.add("id_agensi",idAgensi);
			  r.add("id_daerah",idDaerah);
//			  r.add("id_mukim",idBandar);
			  r.add("flag_peruntukan", flagPeruntukan);
			  r.add("flag_segera", flagSegera);
			  
		      sql = r.getSQLUpdate("tblpptpermohonan");
		      stmt.executeUpdate(sql);
		      
		      //---projek negeri---//
		      Statement stmtF = db.getStatement();
		      SQLRenderer rF = new SQLRenderer();
		      rF.update("id_fail",idFail);
		      //rF.add("id_suburusan", idSuburusan);
		      rF.add("id_negeri", idProjekNegeri);
		      rF.add("id_kementerian", idKementerian);
		      sql = rF.getSQLUpdate("tblpfdfail");
		      stmtF.executeUpdate(sql);
		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }//close update
		
		
	//view list dokumen by id
	 public void  setListDokumen(String id)throws Exception {
		    Db db = null;
		    String sql = "";
		    
		    try {
		    	listDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_permohonan");
		      r.add("a.id_Dokumen");
		      r.add("a.nama_Fail");
		      r.add("a.jenis_Mime");
		      r.add("a.tajuk");
		      r.add("a.keterangan");
		      r.add("a.content");
		      
		      r.add("p.id_permohonan",id);
		      r.add("p.id_permohonan",r.unquote("a.id_permohonan"));
		
		      sql = r.getSQLSelect("Tblpptdokumen a, tblpptpermohonan p");
		     
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  
		    	  h = new Hashtable();
		    	 
		    	  h.put("bil", bil);
		    	  h.put("id_permohonan",rs.getString("id_permohonan"));
		    	  h.put("id_Dokumen", rs.getString("id_Dokumen")== null?"":rs.getString("id_Dokumen"));
		    	  h.put("nama_Fail", rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
		    	  h.put("jenis_Mime",rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));
		    	  h.put("tajuk",rs.getString("tajuk")== null?"":rs.getString("tajuk"));
		    	  h.put("keterangan",rs.getString("keterangan")== null?"":rs.getString("keterangan"));
		          
		    	  listDokumen.addElement(h);
		    	  bil++;	  
		    	  count++;
		      }	
		      
		      if(count == 0){
		    	  
		    	  h = new Hashtable();
			    	 
		    	  h.put("bil", "");
		    	  h.put("id_permohonan","");
		    	  h.put("id_Dokumen", "");
		    	  h.put("nama_Fail", "");
		    	  h.put("jenis_Mime","");
		    	  h.put("tajuk","Tiada rekod.");
		    	  h.put("keterangan","");
		          
		    	  listDokumen.addElement(h);
		    	  
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		}
	 
	 public  boolean check_no_hakmilik(String no_hakmilik,String jenis_hakmilik, String id_hakmilik)throws Exception {
			Db db = null;
			boolean a = false;
		
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();			
				
				sql = "SELECT COUNT(ID_JENISHAKMILIK) AS JENIS,COUNT(NO_HAKMILIK) AS NO_HAKMILIK FROM TBLPPTHAKMILIK WHERE NO_HAKMILIK = '"+no_hakmilik+"'" + "AND ID_JENISHAKMILIK = '" +jenis_hakmilik +"'";
			
				if(!id_hakmilik.equals("") && !id_hakmilik.equals(null))
				{
					sql+="AND ID_HAKMILIK <> '"+id_hakmilik+"'";
				}
				
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				  if (rs.next()) {

                   if ((rs.getInt("JENIS") > 0) && (rs.getInt("NO_HAKMILIK") > 0)) {

                         a = true;

                   }

             }
				
				
				/*
				
				while (rs.next())
				{	
			
		     	String no_pt =rs.getString("NO_PT")==null?"":rs.getString("NO_PT");	
		     	
			  if ((no_pt!="" && no_pt!="" &&  no_pt.equals(no_lotpt))  )
			   	{
					//System.out.println("DUPLICATE LA NO LOT");	
			    	a = true;
				}
			
			    
				
			    }
			    */
			
			}finally {
				if(db != null) db.close();
				
			}
		    return a;
			}


	 public Vector getIdNegeriByLogin (String userid)throws Exception{
		 Db db = null;
		 String sql = "";
		 
		 try{
			 negeriLogin = new Vector();
		     db = new Db();
		     Statement stmt = db.getStatement();
		     
		     sql = "SELECT ID_NEGERI FROM USERS_INTERNAL WHERE USER_ID = '"+userid+"'";
		     ResultSet rs = stmt.executeQuery(sql);
		     Hashtable h = null;
		     while (rs.next()) {
		    	  
		    	  h = new Hashtable();
		    	  h.put("ID_NEGERI",rs.getString("ID_NEGERI"));
		    	  negeriLogin.addElement(h);
		    	  
		     }
		     
		     return negeriLogin;
			 
		 }
		 finally {
				if(db != null) db.close();
				
			}
		 
	 }
	 public Vector getListDokumen(){
			return listDokumen;
		}
	 
	 public Vector getListPohon(){
			return listPohon;
		}
	 
	 public Vector getListPohon2(){
			return listPohon2;
		}
	 
	 public Vector getListMaklumatTanah(){
			return listMaklumatTanah;
		}
}
