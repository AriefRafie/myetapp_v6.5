package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.helpers.File;

public class UPTDaftar {

	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
		private static  Vector list = new Vector();
		private  Vector listPohon = new Vector();
		private  Vector listPohon2 = new Vector();
		private  Vector listMaklumatTuanTanah = new Vector();
		private  Vector listMaklumatTanah = new Vector();
		private  Vector maklumatTanah = new Vector();
		private Vector listPermohonan = new Vector();
		
		public static  Vector getList(){
			return list;
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
		public Vector getMaklumatTanah(){
			return maklumatTanah;
		}
		//elise
		public Vector getListPermohonan(){
			return listPermohonan;
		}
		
		public static void setList(String carianPermohonan, String carianTarikh, int cSuburusan)throws Exception {
		    Db db = null;
		    list.clear();
		    String sql = "";
		    String cariTarikh = "to_date('" + carianTarikh + "','dd/MM/yyyy')";
		    
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT distinct p.id_permohonan, p.no_permohonan, p.no_rujukan_surat, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan ";
		      sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		      sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		      sql +="AND p.id_status = s.id_status ";
		      if(carianPermohonan != null)
		      {
		    	  sql +="AND p.no_permohonan LIKE '%"+carianPermohonan+"%' ";
		      }
		      if(carianTarikh != "")
		      {
		    	  sql +="AND p.tarikh_permohonan = "+cariTarikh+" ";
		      }
		      if(cSuburusan != 0)
		      {	  
		    	  sql +="AND su.id_suburusan = "+cSuburusan+" ";
		      }
		      sql +="ORDER by p.tarikh_permohonan desc ";
		    
		    
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("id_fail", rs.getString("id_fail"));
		    	  h.put("id_permohonan", rs.getString("id_permohonan"));
		    	  h.put("no_permohonan", rs.getString("no_permohonan")==null?"-":rs.getString("no_permohonan"));
		    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"-":Format.format(rs.getDate("tarikh_permohonan")));
		    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"-":rs.getString("nama_suburusan"));
		    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
		    	  h.put("keterangan", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
		    	  if(rs.getString("no_fail") == null){
			    		h.put("no_fail","Belum Diluluskan");
			    	}else{
			    		h.put("no_fail",rs.getString("no_fail"));
			    	}
		    	  list.addElement(h);
		    	  bil++;
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
		
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
		      String rujukan_surat = (String)data.get("rujukan_kementerian");
		     
		      
		      String id_agensi = (String)data.get("agensi");  
		      String id_negeri = (String)data.get("negeri");
		      String id_daerah = (String)data.get("daerah");
		      
		      //table pfdfail
		      String suburusan = (String)data.get("suburusan");
		      String projek_negeri = (String)data.get("projek_negeri");
		      String kementerian = (String)data.get("kementerian");
		      
		      SimpleDateFormat abc =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		      
		      String TP = "to_date('" + tarikh_permohonan + "','dd/MM/yyyy')";
		      String TK = "to_date('" + tarikh_kehendaki + "','dd/MM/yyyy')";
		     
		      int id_suburusan = Integer.parseInt(suburusan);
		      int id_projek_negeri = Integer.parseInt(projek_negeri);
		      int id_kementerian = Integer.parseInt(kementerian);
		      
		      
		      int agensi = Integer.parseInt(id_agensi);
		      int negeri = Integer.parseInt(id_negeri);
		      int daerah = Integer.parseInt(id_daerah);
		      
		      //status "DAFTAR"
		      int status = 113;
		      
		      db = new Db();
		      
		      //generate no permohonan "JKPTG/PPT/kod_suburusan/this_year/000001
		      long idPermohonan = DB.getNextID("TBLPPTPERMOHONAN_SEQ");
		      long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
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
		      rPH.add("poskod", poskod);
		      rPH.add("no_rujukan_surat", rujukan_surat);
		      rPH.add("tarikh_permohonan", rPH.unquote(TP));
		      rPH.add("tarikh_kehendaki", rPH.unquote(TK));
		      rPH.add("id_agensi", agensi);
		      rPH.add("id_negeri", negeri);
		      rPH.add("id_daerah", daerah);
		      rPH.add("id_status", status);
		      sql = rPH.getSQLInsert("tblpptpermohonan");
		      stmtPH.executeUpdate(sql);
		      
		      
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close add
		
		public static void deleteMaklumatTanah(int id) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_hakmilik", id);
		      sql = r.getSQLDelete("tblppthakmilik");
		      stmt.executeUpdate(sql);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }//close delete

		//elise
		public static void updateMaklumatPermohonan(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		   
		    try
		    {		    	
		      String idFail = (String)data.get("id_fail");
		      String idPermohonan = (String)data.get("id_permohonan");
		      
			  String norujukanptg = (String)data.get("editNoRujukanPtg");
			  String norujukanptd = (String)data.get("editNoRujukanPtd");
			  String norujukanupt = (String)data.get("editNoRujukanUpt");
			  
			  String id_user = (String)data.get("id_user");
			  
			  db = new Db();
			  
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_fail", idFail);
			  r.update("id_permohonan", idPermohonan);
			  r.add("no_rujukan_ptg", norujukanptg);
			  r.add("no_rujukan_ptd", norujukanptd);
			  r.add("no_rujukan_upt", norujukanupt);
			  r.add("id_kemaskini",id_user);
			  r.add("tarikh_kemaskini",r.unquote("sysdate"));
			  sql = r.getSQLUpdate("Tblpptpermohonan");
			  stmt.executeUpdate(sql);

		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }//close updateMaklumatPermohonan
		
		
		public static void update(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		   
		    try
		    {
		    	
		      int id = (Integer)data.get("id_permohonan");
		      int idFail = (Integer)data.get("id_fail");
		      String tarikh_pohon = (String)data.get("tarikh_pohon");
		      
		      
			  String suburusan = (String)data.get("suburusan");
			  String daerah = (String)data.get("daerah");
			  String projeknegeri = (String)data.get("projeknegeri");
			  String negeri = (String)data.get("negeri");
			  String agensi = (String)data.get("agensi");
			  String _kementerian = (String)data.get("kementerian");
			  
			  String poskod = (String)data.get("poskod");
			  String tujuan = (String)data.get("tujuan");
			  String rujukan_kementerian = (String)data.get("rujukan_kementerian");
			  String tarikh_hendak = (String)data.get("tarikh_hendak");
			  String alamat1 = (String)data.get("alamat1");
			  String alamat2 = (String)data.get("alamat2");
			  String alamat3 = (String)data.get("alamat3");
			  
			  String TP = "to_date('" + tarikh_pohon + "','dd/MM/yyyy')";
			  String TH = "to_date('" + tarikh_hendak + "','dd/MM/yyyy')";
			  
			  int idSuburusan = Integer.parseInt(suburusan);
			  int idDaerah = Integer.parseInt(daerah);
			  int idProjekNegeri = Integer.parseInt(projeknegeri);
			  int idNegeri = Integer.parseInt(negeri);
			  int idAgensi = Integer.parseInt(agensi);
			  int idKementerian = Integer.parseInt(_kementerian);
			  
			  db = new Db();
			  
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_permohonan", id);
			  r.add("tarikh_permohonan", r.unquote(TP));
			  r.add("tarikh_kehendaki", r.unquote(TH));
			  r.add("poskod", poskod);
			  r.add("tujuan", tujuan);
			  r.add("no_rujukan_surat", rujukan_kementerian);
			  r.add("alamat1", alamat1);
			  r.add("alamat2", alamat2);
			  r.add("alamat3", alamat3);
			  r.add("id_negeri",idNegeri);
			  r.add("id_agensi",idAgensi);
			  r.add("id_daerah",idDaerah);
			  
		      sql = r.getSQLUpdate("tblpptpermohonan");
		      stmt.executeUpdate(sql);
		      
		      //----------------------------------------
		      Statement stmtF = db.getStatement();
		      SQLRenderer rF = new SQLRenderer();
		      rF.update("id_fail",idFail);
		      rF.add("id_suburusan", idSuburusan);
		      rF.add("id_negeri", idProjekNegeri);
		      rF.add("id_kementerian", idKementerian);
		      sql = rF.getSQLUpdate("tblpfdfail");
		      stmtF.executeUpdate(sql);
		      
		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }//close update
		
		public void setListPohon(int id) throws Exception {
			Db db = null;
			listPohon.clear();
			String sql = "";
			
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			
			r.add("p.id_permohonan");
			r.add("p.id_status");
			r.add("p.no_permohonan");
			r.add("f.no_fail");
			r.add("f.id_fail");
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
			r.add("a.nama_agensi");
			r.add("a.id_agensi");
			r.add("p.no_rujukan_surat");
			r.add("p.flag_peruntukan");
			r.add("p.flag_segera");
			r.add("p.alamat1");
			r.add("p.alamat2");
			r.add("p.alamat3");
			r.add("us.nama_suburusan");
			r.add("us.id_suburusan");
			r.add("p.poskod");
			
			r.add("f.id_fail",r.unquote("p.id_fail"));
			r.add("k.id_kementerian",r.unquote("f.id_kementerian"));
			//r.add("n.id_negeri",r.unquote("f.id_negeri"));
			r.add("n.id_negeri",r.unquote("p.id_negeri"));
			r.add("s.id_status",r.unquote("p.id_status"));
			r.add("d.id_daerah",r.unquote("p.id_daerah"));
			r.add("a.id_agensi",r.unquote("p.id_agensi"));
			r.add("us.id_suburusan",r.unquote("f.id_suburusan"));
			
			r.add("p.id_Permohonan",id);
			
			sql = r.getSQLSelect("Tblrujsuburusan us, Tblpfdfail f, Tblrujagensi a, Tblrujdaerah d, Tblrujnegeri n, Tblrujkementerian k, Tblrujstatus s, Tblpptpermohonan p");
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan", rs.getString("id_Permohonan")==null?"nodata":rs.getString("id_Permohonan"));
				h.put("id_status", rs.getString("id_status")==null?"-":rs.getString("id_status"));
				h.put("idNegeri", rs.getString("id_negeri")==null?"-":rs.getString("id_negeri"));
				h.put("idFail", rs.getString("id_fail")==null?"-":rs.getString("id_fail"));
				h.put("idDaerah", rs.getString("id_daerah")==null?"-":rs.getString("id_daerah"));
				h.put("idSuburusan", rs.getString("id_suburusan")==null?"-":rs.getString("id_suburusan"));
				h.put("idAgensi", rs.getString("id_agensi")==null?"-":rs.getString("id_agensi"));
				h.put("idKementerian", rs.getString("id_kementerian")==null?"-":rs.getString("id_kementerian"));
				h.put("noPermohonan", rs.getString("no_permohonan")==null?"-":rs.getString("no_permohonan"));
				h.put("no_fail", rs.getString("no_fail")==null?"-":rs.getString("no_fail"));
				h.put("namaNegeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
				h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"-":Format.format(rs.getDate("tarikh_permohonan")));
				h.put("tujuan", rs.getString("tujuan")==null?"nodata":rs.getString("tujuan"));
				h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
				h.put("status", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
				h.put("daerah", rs.getString("nama_daerah")==null?"-":rs.getString("nama_daerah"));
				h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"-":Format.format(rs.getDate("tarikh_kehendaki")));
				h.put("nama_agensi", rs.getString("nama_agensi")==null?"-":rs.getString("nama_agensi"));
				h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"-":rs.getString("no_rujukan_surat"));
				h.put("flag_peruntukan", rs.getString("flag_peruntukan")==null?"-":rs.getString("flag_peruntukan"));
				h.put("flag_segera", rs.getString("flag_segera")==null?"-":rs.getString("flag_segera"));
				h.put("alamat1", rs.getString("alamat1")==null?"-":rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2")==null?"-":rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3")==null?"-":rs.getString("alamat3"));
				h.put("poskod", rs.getString("poskod")==null?"-":rs.getString("poskod"));
				h.put("nama_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
				h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"-":rs.getString("nama_suburusan"));
				
				listPohon.addElement(h);
			}
			}
			finally {
				if(db != null) db.close();
			}
			
		}//close setlistpohon
		
		public void setListPohon2(int id) throws Exception {
			Db db = null;
			listPohon2.clear();
			String sql = "";
			
			try{
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
				h.put("idPermohonan", rs.getString("id_Permohonan")==null?"nodata":rs.getString("id_Permohonan"));
				h.put("id_fail", rs.getString("id_fail")==null?"nodata":rs.getString("id_fail"));
				h.put("projek_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
				h.put("idProjekNegeri", rs.getString("id_negeri")==null?"-":rs.getString("id_negeri"));
		
				listPohon2.addElement(h);
			}
			}
			finally {
				if(db != null) db.close();
			}
			
		}//close list pohon2
		
		public static void add_maklumat_tanah(Hashtable data) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    try
		    {
		      
		      String permohonan = (String)data.get("id_permohonan");
		      String negeri = (String)data.get("negeri");
		      String jenisHakMilik = (String)data.get("jenisHakMilik");
		      String daerah = (String)data.get("daerah");
		      String mukim = (String)data.get("mukim");
		      String luas = (String)data.get("luas");
		      String luas_diambil = (String)data.get("luas_diambil");
		      
		      String no_hakmilik = (String)data.get("no_hakmilik");
		      String no_lot = (String)data.get("no_lot");
		      String luas_lot = (String)data.get("luas_lot");
		      String no_pt = (String)data.get("no_pt");
		      String anggaran_luas = (String)data.get("anggaran_luas");
		      
		      int id = Integer.parseInt(permohonan);
		      int id_negeri = Integer.parseInt(negeri);
		      int id_jenishakmilik = Integer.parseInt(jenisHakMilik);
		      int id_daerah = Integer.parseInt(daerah);
		      int id_mukim = Integer.parseInt(mukim);
		      int id_luas = Integer.parseInt(luas);
		      int id_luas_diambil = Integer.parseInt(luas_diambil);
		      int luas_ambil = Integer.parseInt(anggaran_luas);
		      
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_permohonan", id);
		      r.add("id_negeri", id_negeri); 	
		      r.add("id_jenishakmilik", id_jenishakmilik);
		      r.add("id_daerah", id_daerah);
		      r.add("id_mukim", id_mukim);
		      r.add("id_unitluaslot", id_luas);
		      r.add("id_unitluasambil", id_luas_diambil);
		      
		      r.add("luas_ambil", luas_ambil);
		      r.add("no_hakmilik", no_hakmilik);
		      r.add("no_lot", no_lot);
		      r.add("luas_lot", luas_lot);
		      r.add("no_pt", no_pt);
		     
		
		      sql = r.getSQLInsert("tblppthakmilik");
		      stmt.executeUpdate(sql);
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close add_maklumat_tanah
		
		public void setListMaklumatTuanTanah(int id_permohonan, int id_fail) throws Exception{
			Db db = null;
			listMaklumatTuanTanah.clear();
			String sql = "";
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("p.id_permohonan");
				r.add("ls.keterangan");
				r.add("ls2.keterangan");
				r.add("m.id_hakmilik");
				r.add("m.id_negeri");
				r.add("n.nama_negeri");
				r.add("m.id_jenishakmilik");
				r.add("m.id_daerah");
				r.add("m.id_mukim");
				r.add("mk.nama_mukim");
				r.add("m.id_unitluaslot");
				r.add("m.id_unitluasambil");
				r.add("m.luas_ambil");
				r.add("m.no_hakmilik");
				r.add("m.no_lot");
				r.add("m.luas_lot");
				r.add("m.no_pt");
				
			
				r.add("m.id_Permohonan",r.unquote("p.id_Permohonan(+)"));
				r.add("m.id_negeri",r.unquote("n.id_negeri"));
				r.add("ls.id_luas",r.unquote("m.id_unitluaslot"));
				r.add("ls2.id_luas",r.unquote("m.id_unitluasambil"));
				r.add("m.id_mukim",r.unquote("mk.id_mukim"));
				//r.add("m.id_luas",r.unquote("ls.id_luas"));
				
				r.add("p.id_Permohonan",id_permohonan);
				r.add("p.id_fail",id_fail);
				
				sql = r.getSQLSelect("Tblpptpermohonan p, Tblrujluas ls, Tblrujluas ls2, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m ");
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"-":rs.getString("id_Permohonan"));
					h.put("unitluaslot", rs.getString(2)==null?"-":rs.getString(2));
					h.put("unitluasambil", rs.getString(3)==null?"-":rs.getString(3));
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"-":rs.getString("id_hakmilik"));
					h.put("id_negeri", rs.getString("id_negeri")==null?"-":rs.getString("id_negeri"));
					h.put("nama_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"-":rs.getString("id_jenishakmilik"));
					h.put("id_daerah", rs.getString("id_daerah")==null?"-":rs.getString("id_daerah"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"-":rs.getString("id_mukim"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"-":rs.getString("nama_mukim"));
					h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"-":rs.getString("id_unitluaslot"));
					h.put("id_unitluasambil", rs.getString("id_unitluasambil")==null?"-":rs.getString("id_unitluasambil"));
					h.put("luas_ambil", rs.getString("luas_ambil")==null?"-":rs.getString("luas_ambil"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
					h.put("no_lot", rs.getString("no_lot")==null?"-":rs.getString("no_lot"));
					h.put("luas_lot", rs.getString("luas_lot")==null?"-":rs.getString("luas_lot"));
					h.put("no_pt", rs.getString("no_pt")==null?"-":rs.getString("no_pt"));
					listMaklumatTanah.addElement(h);
					bil++;	
				}
				//return list;
			}finally {
				if(db != null) db.close();
			}
			}//close setDataListKertas
		
				
		//hantar (status change to "5")
		public static void hantar(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		   
		    try
		    {
		      int idPermohonan = (Integer)data.get("id_permohonan");
		      int newStatus = 5;
			  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_permohonan", idPermohonan);
			  r.add("id_status", newStatus);
			  
			  sql = r.getSQLUpdate("tblpptpermohonan");
		      stmt.executeUpdate(sql);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }//close hantar
		
		public static void semakanTambah(String idsemakan, String idpermohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
		      int idPermohonan = Integer.parseInt(idpermohonan);
		      String idSemakan = idsemakan;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_Semakanhantar", idSemakanhantar);
		      r.add("id_permohonan", idPermohonan);
		      r.add("id_semakansenarai", idSemakan);
		      sql = r.getSQLInsert("Tblsemakanhantar");
		      stmt.executeUpdate(sql);
		      
		    } finally {
		      if (db != null) db.close();
		    }
		  }
		
		 public static Vector getSenaraiSemakan(int idpermohonan)throws Exception {
			    Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("i.id_semakansenarai");
			      r.add("i.id_semakanhantar");
			
			      r.add("i.id_permohonan",idpermohonan);
			      sql = r.getSQLSelect("tblsemakanhantar i");
			      ResultSet rs = stmt.executeQuery(sql);
			      Vector list = new Vector();
			      
			      Hashtable h = null;

			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  h.put("IdSemakansenarai",rs.getString("id_semakansenarai"));
			    	  h.put("IdSemakanhantari",rs.getString("id_semakanhantar"));
			    	  list.addElement(h);
			      }
			      return list;
			    } finally {
			      if (db != null) db.close();
			    }
			  }
		
		 public void semakanHapus(String idpermohonan,int idsemakansenarai) throws Exception {
			    Db db = null;
			    int idPermohonan = Integer.parseInt(idpermohonan);
			    String sql = "";
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      r.add("id_permohonan", idPermohonan);
			      r.add("id_semakansenarai", idsemakansenarai);
			      sql = r.getSQLDelete("tblsemakanhantar");
			      stmt.executeUpdate(sql);
			      
			    }finally	{
			      if (db != null) db.close();
		    	}
		  }
		 //elise
		 
		public void setlistPermohonan(String id_fail, String id_permohonan) throws Exception {
			
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    Db db = null;
		    listPermohonan.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("p.id_permohonan");
		      r.add("p.no_permohonan");
		      r.add("p.id_fail");
		      r.add("f.no_fail");
		      r.add("d.keterangan"); //keterangan bandar
		      r.add("s.keterangan"); //keterangan id_status
		      r.add("f.id_suburusan");
		      r.add("p.tarikh_permohonan");
		      r.add("p.id_status");
		      r.add("f.id_kementerian");
		      r.add("p.id_agensi");
		      r.add("p.flag_peruntukan");
		      r.add("p.flag_segera");
		      r.add("f.id_negeri");
		      r.add("p.id_daerah"); 
		      r.add("p.tujuan");
		      r.add("p.no_rujukan_surat");
		      r.add("p.tarikh_kehendaki");
		      r.add("p.alamat1");
		      r.add("p.alamat2");
		      r.add("p.alamat3");
		      r.add("p.poskod");
		      r.add("p.id_negeri");
		      r.add("p.id_mukim");	     
		      r.add("k.nama_kementerian");
		      r.add("b.nama_daerah");
		      r.add("p.no_rujukan_ptd");
		      r.add("p.no_rujukan_ptg");
		      r.add("p.no_rujukan_upt");
		      r.add("su.nama_suburusan");	      
		      r.add("a.nama_agensi");
		      r.add("c.nama_negeri");
		      r.add("e.id_senaraisemak");
		      r.add("e.semak1");
		      r.add("e.semak2");
		      r.add("e.semak3");
		      r.add("e.semak4");
		      r.add("e.semak5");
		      r.add("e.semak6");
		      r.add("e.semak7"); 	      
		      
		      r.add("f.id_kementerian",r.unquote("k.id_kementerian"));
		      r.add("f.id_fail",r.unquote("p.id_fail"));
		      r.add("b.id_daerah",r.unquote("p.id_daerah"));
		      r.add("f.id_suburusan",r.unquote("su.id_suburusan"));
		      r.add("p.id_status",r.unquote("s.id_status"));
		      r.add("p.id_agensi",r.unquote("a.id_agensi"));
		      r.add("p.id_negeri",r.unquote("c.id_negeri"));
		      r.add("p.id_mukim",r.unquote("d.id_bandar"));
		      r.add("p.id_permohonan",r.unquote("e.id_permohonan"));
	      
		      r.add("p.id_fail", id_fail, "=");
		      r.add("p.id_permohonan", id_permohonan, "=");

		      sql = r.getSQLSelect("Tblpfdfail f, Tblpptpermohonan p, Tblrujkementerian k, Tblrujagensi a, Tblrujnegeri c, Tblrujdaerah b, Tblrujsuburusan su, Tblrujstatus s, Tblrujbandar d, Tblpptsenaraisemak e ");
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      //Vector v = new Vector();
		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		        h.put("id_permohonan",rs.getLong("id_permohonan"));
		    	h.put("no_permohonan",rs.getString("no_permohonan"));
		    	h.put("no_fail",rs.getString("no_fail"));
		    	h.put("nama_bandar", rs.getString(5)==null?"":rs.getString(5));
		    	h.put("nama_status", rs.getString(6)==null?"":rs.getString(6));
		    	h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
		    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
		    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"":rs.getString("no_rujukan_ptd"));
		    	h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
		    	h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
		    	h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
		    	h.put("nama_agensi", rs.getString("nama_agensi")==null?"":rs.getString("nama_agensi"));	    
		    	h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));  
		    	if(rs.getString("id_suburusan") == null){
		    		h.put("id_suburusan","");
		    	}else{
		    		h.put("id_suburusan",rs.getString("id_suburusan"));
		    	}
		    	h.put("tarikh_permohonan",rs.getDate("tarikh_permohonan")==null?"":rs.getDate("tarikh_permohonan"));
		    	h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
		    	if(rs.getString("id_kementerian") == null){
		    		h.put("id_kementerian","");
		    	}else{
		    		h.put("id_kementerian",rs.getString("id_kementerian"));
		    	}
		    	if(rs.getString("id_agensi") == null){
		    		h.put("id_agensi","");
		    	}else{
		    		h.put("id_agensi",rs.getString("id_agensi"));
		    	}
		    	h.put("flag_peruntukan", rs.getString("flag_peruntukan")==null?"":rs.getString("flag_peruntukan"));
		    	h.put("flag_segera", rs.getString("flag_segera")==null?"":rs.getString("flag_segera"));
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
		    	h.put("tujuan", rs.getString("tujuan")==null?"":rs.getString("tujuan"));
		    	h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"":rs.getString("no_rujukan_surat"));	
		    	h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"":Format.format(rs.getDate("tarikh_kehendaki")));
		    	h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    	h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));	    	
		    	h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));	    
		    	h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));	    
		    	h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
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
		    	h.put("id_senaraisemak", rs.getString("id_senaraisemak")==null?"":rs.getString("id_senaraisemak"));
		    	  h.put("semak1", rs.getString("semak1")==null?"0":rs.getString("semak1"));
		    	  h.put("semak2", rs.getString("semak2")==null?"0":rs.getString("semak2"));
		    	  h.put("semak3", rs.getString("semak3")==null?"0":rs.getString("semak3"));
		    	  h.put("semak4", rs.getString("semak4")==null?"0":rs.getString("semak4"));
		    	  h.put("semak5", rs.getString("semak5")==null?"0":rs.getString("semak5"));
		    	  h.put("semak6", rs.getString("semak6")==null?"0":rs.getString("semak6"));
		    	  h.put("semak7", rs.getString("semak7")==null?"0":rs.getString("semak7"));
		    	  listPermohonan.addElement(h);
		    	bil++;
		      }
		      //return listPermohonan;
		    }
		    finally {
		      if (db != null) db.close();
		    }		
			
			
			
			/*Db db = null;
			listPermohonan.clear();
			String sql = "";
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			
			r.add("p.id_permohonan");
			r.add("p.id_status");
			r.add("p.no_permohonan");
			r.add("f.no_fail");
			r.add("f.id_fail");
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
			r.add("a.nama_agensi");
			r.add("a.id_agensi");
			r.add("p.no_rujukan_surat");
			r.add("p.flag_peruntukan");
			r.add("p.flag_segera");
			r.add("p.alamat1");
			r.add("p.alamat2");
			r.add("p.alamat3");
			r.add("us.nama_suburusan");
			r.add("us.id_suburusan");
			r.add("p.poskod");
			r.add("p.no_rujukan_ptg");	
			r.add("p.no_rujukan_ptd");
			r.add("p.no_rujukan_upt");
			
			r.add("f.id_fail",r.unquote("p.id_fail"));
			r.add("k.id_kementerian",r.unquote("f.id_kementerian"));
			//r.add("n.id_negeri",r.unquote("f.id_negeri"));
			r.add("n.id_negeri",r.unquote("p.id_negeri"));
			r.add("s.id_status",r.unquote("p.id_status"));
			r.add("d.id_daerah",r.unquote("p.id_daerah"));
			r.add("a.id_agensi",r.unquote("p.id_agensi"));
			r.add("us.id_suburusan",r.unquote("f.id_suburusan"));
			
			r.add("p.id_fail",id_fail);
			r.add("p.id_permohonan",id_permohonan);
			
			sql = r.getSQLSelect("Tblrujsuburusan us, Tblpfdfail f, Tblrujagensi a, Tblrujdaerah d, Tblrujnegeri n, Tblrujkementerian k, Tblrujstatus s, Tblpptpermohonan p");
			
			ResultSet rs = stmt.executeQuery(sql);
		
			Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("id_permohonan", rs.getString("id_permohonan")==null?"nodata":rs.getString("id_permohonan"));
				h.put("id_status", rs.getString("id_status")==null?"-":rs.getString("id_status"));
				h.put("idNegeri", rs.getString("id_negeri")==null?"-":rs.getString("id_negeri"));
				h.put("idFail", rs.getString("id_fail")==null?"-":rs.getString("id_fail"));
				h.put("idDaerah", rs.getString("id_daerah")==null?"-":rs.getString("id_daerah"));
				h.put("idSuburusan", rs.getString("id_suburusan")==null?"-":rs.getString("id_suburusan"));
				h.put("idAgensi", rs.getString("id_agensi")==null?"-":rs.getString("id_agensi"));
				h.put("idKementerian", rs.getString("id_kementerian")==null?"-":rs.getString("id_kementerian"));
				h.put("noPermohonan", rs.getString("no_permohonan")==null?"-":rs.getString("no_permohonan"));
				h.put("no_fail", rs.getString("no_fail")==null?"-":rs.getString("no_fail"));
				h.put("namaNegeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
				h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"-":Format.format(rs.getDate("tarikh_permohonan")));
				h.put("tujuan", rs.getString("tujuan")==null?"nodata":rs.getString("tujuan"));
				h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
				h.put("status", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
				h.put("daerah", rs.getString("nama_daerah")==null?"-":rs.getString("nama_daerah"));
				h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"-":Format.format(rs.getDate("tarikh_kehendaki")));
				h.put("nama_agensi", rs.getString("nama_agensi")==null?"-":rs.getString("nama_agensi"));
				h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"-":rs.getString("no_rujukan_surat"));
				h.put("flag_peruntukan", rs.getString("flag_peruntukan")==null?"-":rs.getString("flag_peruntukan"));
				h.put("flag_segera", rs.getString("flag_segera")==null?"-":rs.getString("flag_segera"));
				h.put("alamat1", rs.getString("alamat1")==null?"-":rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2")==null?"-":rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3")==null?"-":rs.getString("alamat3"));
				h.put("poskod", rs.getString("poskod")==null?"-":rs.getString("poskod"));
				h.put("nama_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
				h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"-":rs.getString("nama_suburusan"));
				h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"-":rs.getString("no_rujukan_ptg"));
				h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"-":rs.getString("no_rujukan_ptd"));
				h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"-":rs.getString("no_rujukan_upt"));
				
			
				listPermohonan.addElement(h);
			}
			}
			finally {
				if(db != null) db.close();
			}*/			
		}
		public static void add_malumattuan_tanah(Hashtable h) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      //r.add("id_permohonan", id_permohonan);
		      //r.add("id_fail", id_fail);
		      //r.add("id_negeri", id_negeri); 	
		      //r.add("id_jenishakmilik", id_jenishakmilik);
		      //r.add("id_daerah", id_daerah);
		      //r.add("id_mukim", id_mukim);
		      //r.add("id_unitluaslot", id_luas);
		      //r.add("id_unitluasambil", id_luas_diambil);
		      
		      //r.add("luas_ambil", luas_ambil);
		      //r.add("no_hakmilik", no_hakmilik);
		      //r.add("no_lot", no_lot);
		      //r.add("luas_lot", luas_lot);
		      //r.add("no_pt", no_pt);		     
		
		      sql = r.getSQLInsert("tblppthakmilik");
		      //stmt.executeUpdate(sql);
		      
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally		
		}
		
		public static void add_hak_milik(Hashtable data) throws Exception {
			Db db = null;
			String sql = "";
			String sql1 = "";
			String sql2 = "";
			String sql3 = "";
			
				try
				{			
					long id_hakmilik = DB.getNextID("TBLPPTHAKMILIK_SEQ");
					long id_hakmilikpb = DB.getNextID("TBLPPTHAKMILIKPB_SEQ");
					long id_pihakberkepentingan = DB.getNextID("TBLPPTPIHAKBERKEPENTINGAN_SEQ");

					String id_user = (String)data.get("id_user");
					
					String jenis_hakmilik = (String)data.get("jenis_hakmilik");
					String no_hakmilik = (String)data.get("no_hakmilik");
					String no_lot = (String)data.get("no_lot");
					//String no_pt = (String)data.get("no_pt");
					String negeri = (String)data.get("id_negeri");
					String daerah = (String)data.get("id_daerah");
					String bandar = (String)data.get("id_mukim");
					String luas_lot = (String)data.get("luas_lot");	
					String lot = (String)data.get("lot");
					String unit_luaslot = (String)data.get("id_unitluaslot");
					String anggaran_kws = (String)data.get("luas_ambil");	
					String id_permohonan = (String)data.get("id_permohonan");
					String id_fail = (String)data.get("id_fail");
					String id_status = "16" ;				
			        		
					int idP = Integer.parseInt(id_permohonan);
					int idHK = Integer.parseInt(jenis_hakmilik);
					int idNeg = Integer.parseInt(negeri);
					int idDae = Integer.parseInt(daerah);
					int idMuk = Integer.parseInt(bandar);
					int idUnitLuas = Integer.parseInt(unit_luaslot);
					int idLot = Integer.parseInt(lot);
					
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.add("id_hakmilik",id_hakmilik);
					r.add("id_permohonan",idP);
					r.add("id_jenishakmilik",idHK);
					r.add("no_hakmilik",no_hakmilik);
					r.add("no_lot",no_lot);
					r.add("id_lot",idLot);
					//r.add("no_pt",no_pt);
					r.add("id_negeri",idNeg);
					r.add("id_daerah",idDae);
					r.add("id_mukim",idMuk);
					r.add("luas_lot",luas_lot);
					r.add("id_unitluaslot",idUnitLuas);
					r.add("luas_ambil",anggaran_kws);
					r.add("id_masuk",id_user);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					sql = r.getSQLInsert("tblppthakmilik");
				
					stmt.executeUpdate(sql);
					
					
					db = new Db();
					Statement stmt1 = db.getStatement();
					SQLRenderer r1 = new SQLRenderer();
					r1.add("id_hakmilikpb",id_hakmilikpb);
					r1.add("id_pihakberkepentingan",id_pihakberkepentingan);
					r1.add("id_hakmilik",id_hakmilik);
					r1.add("id_masuk",id_user);
					r1.add("tarikh_masuk",r1.unquote("sysdate"));
					sql1 = r1.getSQLInsert("tblppthakmilikpb");					
					stmt1.executeUpdate(sql1);
					
					
				}finally {
					if(db != null) db.close();
				}
				
		}
		
		public static void add_tuantanah(Hashtable data)  throws Exception {
			Db db = null;
			String sql = "";
			String sql1 = "";
			String sql2 = "";
			
				try
				{			
					//long id_hakmilik = DB.getNextID("TBLPPTHAKMILIK_SEQ");
					long id_hakmilikpb = DB.getNextID("TBLPPTHAKMILIKPB_SEQ");
					long id_pihakberkepentingan = DB.getNextID("TBLPPTPIHAKBERKEPENTINGAN_SEQ");
					
					String id_user = (String)data.get("id_user");
					
					String id_permohonan = (String)data.get("id_permohonan");
					String id_hakmilik = (String)data.get("id_hakmilik");
					String nama_pb = (String)data.get("nama_pb");
					String id_jenispb = (String)data.get("id_jenispb");
					String id_jenisnopb = (String)data.get("id_jenisnopb");
					String no_pb = (String)data.get("no_pb");
					String syer_atas = (String)data.get("syer_atas");
					String syer_bawah = (String)data.get("syer_bawah");					
					String id_bangsa = (String)data.get("id_bangsa");
					String id_warganegara = (String)data.get("id_warganegara");
					String idnegeri = (String)data.get("id_negeri");
					String alamat1PB = (String)data.get("alamat1PB");
					String alamat2PB = (String)data.get("alamat2PB");
					String alamat3PB = (String)data.get("alamat3PB");
					String poskodPB = (String)data.get("poskodPB");
					
					int negeri=0;

				    if(idnegeri!="")
				    {negeri = Integer.parseInt(idnegeri);}

				    //add item
				    
				    db = new Db();
				    
					Statement stmtA = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					  r.add("id_pihakberkepentingan", id_pihakberkepentingan);
					  r.add("nama_pb", nama_pb);
					  r.add("id_jenispb", id_jenispb);
					  r.add("id_jenisnopb", id_jenisnopb);
					  r.add("no_pb", no_pb);
					  r.add("syer_atas", syer_atas);
					  r.add("alamat1", alamat1PB);
					  r.add("alamat2", alamat2PB);
					  r.add("alamat3", alamat3PB);
					  r.add("syer_bawah", syer_bawah);
					  r.add("id_bangsa", id_bangsa);
					  r.add("id_warganegara", id_warganegara);
					  r.add("id_hakmilik", id_hakmilik);
					  r.add("poskod", poskodPB);
					  r.add("id_negeri", negeri);  
					  r.add("id_masuk",id_user);
					  r.add("tarikh_masuk",r.unquote("sysdate"));
					  
					  sql2 = r.getSQLInsert("tblpptpihakberkepentingan");
					  stmtA.executeUpdate(sql2);
					
					 //add id
				    
					Statement stmt = db.getStatement();
					SQLRenderer r1 = new SQLRenderer();
					r1.add("id_hakmilikpb",id_hakmilikpb);
					r1.add("id_pihakberkepentingan",id_pihakberkepentingan);
					r1.add("id_hakmilik",id_hakmilik);
					r1.add("id_masuk",id_user);
					r1.add("tarikh_masuk",r1.unquote("sysdate"));
					sql1 = r1.getSQLInsert("tblppthakmilikpb");
					
					stmt.executeUpdate(sql1);
				
				}finally {
					if(db != null) db.close();
				}
		}
		public static void edit_status(Hashtable data) throws Exception {
			Db db = null;
			String sql3 = "";
			
				try
				{			
					String id_permohonan = (String)data.get("id_permohonan");
					int id_status = 16 ;		
					
					int idP = Integer.parseInt(id_permohonan);
					
					String id_user = (String)data.get("id_user");
					
					//**UNTUK UPDATE ID_STATUS = 16 [MAKLUMAT HAKMILIK]
					  db = new Db();					  
					  Statement stmt3 = db.getStatement();
					  SQLRenderer r3 = new SQLRenderer();
					
					  r3.update("id_permohonan", idP);
					  r3.add("id_status", id_status);
					  r3.add("id_kemaskini",id_user);
					  r3.add("tarikh_kemaskini",r3.unquote("sysdate"));
					  sql3 = r3.getSQLUpdate("tblpptpermohonan");
					  stmt3.executeUpdate(sql3);
					  
									
				}finally {
					if(db != null) db.close();
				}			
		}
		
		public void setMaklumatTanah(int id) throws Exception {
			Db db = null;
			maklumatTanah.clear();
			String sql = "";
			int one = 1;
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			
			r.add("hk.id_hakmilik");
			r.add("ls.id_luas");
			r.add("ls2.id_luas");
			r.add("p.id_permohonan");
			r.add("jhk.id_jenishakmilik");
			r.add("n.id_negeri");
			r.add("d.id_daerah");
			r.add("mk.id_mukim");
			r.add("hk.luas_ambil");
			r.add("hk.no_hakmilik");
			r.add("hk.no_lot");
			r.add("hk.luas_lot");
			r.add("hk.no_pt");
			r.add("n.nama_negeri");
			r.add("d.nama_daerah");
			r.add("ls.keterangan");
			r.add("mk.nama_mukim");
			r.add("jhk.keterangan");
			
			

			r.add("n.id_negeri",r.unquote("hk.id_negeri"));
			r.add("d.id_daerah",r.unquote("hk.id_daerah"));
			r.add("ls.id_luas",r.unquote("hk.id_unitluaslot"));
			r.add("ls2.id_luas",r.unquote("hk.id_unitluasambil"));
			r.add("mk.id_mukim",r.unquote("hk.id_mukim"));
			r.add("jhk.id_jenishakmilik",r.unquote("hk.id_jenishakmilik"));
			
			
			r.add("p.id_permohonan",r.unquote("hk.id_permohonan"));
			
			
			r.add("hk.id_hakmilik",id);
			
			sql = r.getSQLSelect("Tblppthakmilik hk, Tblrujjenishakmilik jhk, Tblrujmukim mk, Tblrujluas ls, Tblrujluas ls2, Tblrujdaerah d, Tblrujnegeri n, Tblpptpermohonan p");
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("id_permohonan", rs.getString("id_Permohonan")==null?"nodata":rs.getString("id_Permohonan"));
				h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"-":rs.getString("id_hakmilik"));
				h.put("id_luasLot", rs.getString(2)==null?"-":rs.getString(2));
				h.put("id_luasAmbil", rs.getString(3)==null?"-":rs.getString(3));
				h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"-":rs.getString("id_jenishakmilik"));
				h.put("id_negeri", rs.getString("id_negeri")==null?"-":rs.getString("id_negeri"));
				h.put("id_daerah", rs.getString("id_daerah")==null?"-":rs.getString("id_daerah"));
				h.put("id_mukim", rs.getString("id_mukim")==null?"-":rs.getString("id_mukim"));
				h.put("luas_ambil", rs.getString("luas_ambil")==null?"-":rs.getString("luas_ambil"));
				h.put("luas_lot", rs.getString("luas_lot")==null?"-":rs.getString("luas_lot"));
				h.put("no_pt", rs.getString("no_pt")==null?"-":rs.getString("no_pt"));
				h.put("no_lot", rs.getString("no_lot")==null?"-":rs.getString("no_lot"));
				h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
				
				maklumatTanah.addElement(h);
			}
			}
			finally {
				if(db != null) db.close();
			}
			
		}//close setlistpohon
		public void setMaklumatTanah2(int id) throws Exception {
			Db db = null;
			maklumatTanah.clear();
			String sql = "";
			int one = 1;
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			
			r.add("hk.id_hakmilik");
			r.add("ls.id_luas");
			r.add("ls2.id_luas");
			r.add("p.id_permohonan");
			r.add("jhk.id_jenishakmilik");
			r.add("n.id_negeri");
			r.add("d.id_daerah");
			r.add("mk.id_mukim");
			r.add("hk.luas_ambil");
			r.add("hk.no_hakmilik");
			r.add("hk.no_lot");
			r.add("hk.luas_lot");
			r.add("hk.no_pt");
			r.add("n.nama_negeri");
			r.add("d.nama_daerah");
			r.add("ls.keterangan");
			r.add("mk.nama_mukim");
			r.add("jhk.keterangan");
			
			

			r.add("n.id_negeri",r.unquote("hk.id_negeri"));
			r.add("d.id_daerah",r.unquote("hk.id_daerah"));
			r.add("ls.id_luas",r.unquote("hk.id_unitluaslot"));
			r.add("ls2.id_luas",r.unquote("hk.id_unitluasambil"));
			r.add("mk.id_mukim",r.unquote("hk.id_mukim"));
			r.add("jhk.id_jenishakmilik",r.unquote("hk.id_jenishakmilik"));
			
			
			r.add("p.id_permohonan",r.unquote("hk.id_permohonan"));
			
			
			r.add("hk.id_hakmilik",id);
			
			sql = r.getSQLSelect("Tblppthakmilik hk, Tblrujjenishakmilik jhk, Tblrujmukim mk, Tblrujluas ls, Tblrujluas ls2, Tblrujdaerah d, Tblrujnegeri n, Tblpptpermohonan p");
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("id_permohonan", rs.getString("id_Permohonan")==null?"nodata":rs.getString("id_Permohonan"));
				h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"-":rs.getString("id_hakmilik"));
				h.put("id_luasLot", rs.getString(2)==null?"-":rs.getString(2));
				h.put("id_luasAmbil", rs.getString(3)==null?"-":rs.getString(3));
				h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"-":rs.getString("id_jenishakmilik"));
				h.put("id_negeri", rs.getString("id_negeri")==null?"-":rs.getString("id_negeri"));
				h.put("id_daerah", rs.getString("id_daerah")==null?"-":rs.getString("id_daerah"));
				h.put("id_mukim", rs.getString("id_mukim")==null?"-":rs.getString("id_mukim"));
				h.put("luas_ambil", rs.getString("luas_ambil")==null?"-":rs.getString("luas_ambil"));
				h.put("luas_lot", rs.getString("luas_lot")==null?"-":rs.getString("luas_lot"));
				h.put("no_pt", rs.getString("no_pt")==null?"-":rs.getString("no_pt"));
				h.put("no_lot", rs.getString("no_lot")==null?"-":rs.getString("no_lot"));
				h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
				
				maklumatTanah.addElement(h);
			}
			}
			finally {
				if(db != null) db.close();
			}
			
		}//close setlistpohon
		
		public void setListMaklumatTanah(int id) throws Exception{
			Db db = null;
			listMaklumatTanah.clear();
			String sql = "";
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("p.id_permohonan");
				r.add("ls.keterangan");
				r.add("ls2.keterangan");
				r.add("m.id_hakmilik");
				r.add("m.id_negeri");
				r.add("n.nama_negeri");
				r.add("m.id_jenishakmilik");
				r.add("m.id_daerah");
				r.add("m.id_mukim");
				r.add("mk.nama_mukim");
				r.add("m.id_unitluaslot");
				r.add("m.id_unitluasambil");
				r.add("m.luas_ambil");
				r.add("m.no_hakmilik");
				r.add("m.no_lot");
				r.add("m.luas_lot");
				r.add("m.no_pt");
				
			
				r.add("m.id_Permohonan",r.unquote("p.id_Permohonan(+)"));
				r.add("m.id_negeri",r.unquote("n.id_negeri"));
				r.add("ls.id_luas",r.unquote("m.id_unitluaslot"));
				r.add("ls2.id_luas",r.unquote("m.id_unitluasambil"));
				r.add("m.id_mukim",r.unquote("mk.id_mukim"));
				//r.add("m.id_luas",r.unquote("ls.id_luas"));
				
				r.add("p.id_Permohonan",id);
				
				
				sql = r.getSQLSelect("Tblpptpermohonan p, Tblrujluas ls, Tblrujluas ls2, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m ");
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"-":rs.getString("id_Permohonan"));
					h.put("unitluaslot", rs.getString(2)==null?"-":rs.getString(2));
					h.put("unitluasambil", rs.getString(3)==null?"-":rs.getString(3));
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"-":rs.getString("id_hakmilik"));
					h.put("id_negeri", rs.getString("id_negeri")==null?"-":rs.getString("id_negeri"));
					h.put("nama_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"-":rs.getString("id_jenishakmilik"));
					h.put("id_daerah", rs.getString("id_daerah")==null?"-":rs.getString("id_daerah"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"-":rs.getString("id_mukim"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"-":rs.getString("nama_mukim"));
					h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"-":rs.getString("id_unitluaslot"));
					h.put("id_unitluasambil", rs.getString("id_unitluasambil")==null?"-":rs.getString("id_unitluasambil"));
					h.put("luas_ambil", rs.getString("luas_ambil")==null?"-":rs.getString("luas_ambil"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
					h.put("no_lot", rs.getString("no_lot")==null?"-":rs.getString("no_lot"));
					h.put("luas_lot", rs.getString("luas_lot")==null?"-":rs.getString("luas_lot"));
					h.put("no_pt", rs.getString("no_pt")==null?"-":rs.getString("no_pt"));
					listMaklumatTanah.addElement(h);
					bil++;	
				}
				//return list;
			}finally {
				if(db != null) db.close();
			}
			}//close setDataListKertas
		
		public static void updateMaklumatTanah(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		   
		    try
		    {		    	
		      //int id_permohonan = (Integer)data.get("id_permohonan");
		      int id_hakmilik = (Integer)data.get("id_hakmilik");
		      
		      String id_user = (String)data.get("id_user");
		      
			  //String _id_existNegeri = (String)data.get("id_existNegeri");
			  String _id_existJenisHakmilik = (String)data.get("id_existJenisHakmilik");
			  //String _id_existDaerah = (String)data.get("id_existDaerah");
			  String _id_existMukim = (String)data.get("id_existMukim");
			  String _id_UnitLuas = (String)data.get("id_UnitLuas");
			  String lot = (String)data.get("lot");
			  
			  String edit_luas_lot = (String)data.get("edit_luas_lot");			  
			  String edit_no_hakmilik = (String)data.get("edit_no_hakmilik");  
			  String edit_no_lot = (String)data.get("edit_no_lot");
			  String edit_anggaran_luas = (String)data.get("edit_anggaran_luas");
			  //String edit_no_pt = (String)data.get("edit_no_pt");
			  
			  //int id_existNegeri = Integer.parseInt(_id_existNegeri);
			  int id_existJenisHakmilik = Integer.parseInt(_id_existJenisHakmilik);
			  //int id_existDaerah = Integer.parseInt(_id_existDaerah);
			  int id_existMukim = Integer.parseInt(_id_existMukim);
			  int id_UnitLuas = Integer.parseInt(_id_UnitLuas);
			  int idLot = Integer.parseInt(lot);
		  
			  db = new Db();
			  
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_hakmilik", id_hakmilik);
			  //r.add("id_negeri", id_existNegeri);
			  r.add("id_jenishakmilik", id_existJenisHakmilik);
			  //r.add("id_daerah", id_existDaerah);
			  r.add("id_mukim", id_existMukim);
			  r.add("id_unitluaslot", id_UnitLuas);
			  r.add("id_lot", idLot);

			  r.add("no_hakmilik", edit_no_hakmilik);
			  r.add("no_lot", edit_no_lot);
			  r.add("luas_lot", edit_luas_lot);
			  //r.add("no_pt", edit_no_pt);
			  r.add("luas_ambil", edit_anggaran_luas);
			  r.add("id_kemaskini",id_user);
			  r.add("tarikh_kemaskini",r.unquote("sysdate"));
			  sql = r.getSQLUpdate("tblppthakmilik");
		      stmt.executeUpdate(sql);
		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }//close updateMaklumatTanah
	
			public static void updateMaklumatPB(Hashtable data) throws Exception {
			    Db db = null;
			    String sql = "";
			   
			    try
			    {		    	
			      int id_permohonan = (Integer)data.get("id_permohonan");
			      int id_pihakberkepentingan = (Integer)data.get("id_pihakberkepentingan");	
			      
			      String id_user = (String)data.get("id_user");
			      
				  String edit_nama_pb = (String)data.get("edit_nama_pb");
			      String id_existNegeri = (String)data.get("id_existNegeri");
				  String id_jenisnopb = (String)data.get("id_jenisnopb");
				  String edit_id_existJenisPB = (String)data.get("id_existJenisPB");
				  String no_pb = (String)data.get("no_pb");
				  String syer_atas = (String)data.get("syer_atas");
				  String syer_bawah = (String)data.get("syer_bawah");			  
				  String id_existBangsa = (String)data.get("id_existBangsa");  
				  String id_existWarganegara = (String)data.get("id_existWarganegara");
				  
				  String alamat1PB = (String)data.get("alamat1");  
				  String alamat2PB = (String)data.get("alamat2");
				  String alamat3PB = (String)data.get("alamat3");
				  String poskodPB = (String)data.get("poskod");
				  String idNegeri = (String)data.get("id_negeri");
				  
				  db = new Db();
				  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_pihakberkepentingan", id_pihakberkepentingan);				
				  r.add("id_jenispb", edit_id_existJenisPB);
				  r.add("id_jenisnopb", id_jenisnopb);
				  r.add("nama_pb", edit_nama_pb);
				  r.add("no_pb", no_pb);
				  r.add("syer_atas", syer_atas);
				  r.add("syer_bawah", syer_bawah);				  
				  r.add("id_bangsa", id_existBangsa);
				  r.add("id_warganegara", id_existWarganegara);
				  r.add("alamat1", alamat1PB);	
				  r.add("alamat2", alamat2PB);	
				  r.add("alamat3", alamat3PB);	
				  r.add("poskod", poskodPB);	
				  r.add("id_negeri", idNegeri);	
				  r.add("id_kemaskini",id_user);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  sql = r.getSQLUpdate("tblpptpihakberkepentingan");				  
				  stmt.executeUpdate(sql);
			    }
			    finally {
			      if (db != null) db.close();
			    }
			  }//close updateMaklumatTanah
		 
}//close class
