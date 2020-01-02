package ekptg.model.ppt;

import java.sql.Blob;
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
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */


public class APDaftar {
	
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
		private static  Vector list = new Vector();
		private static  Vector listDokumen = new Vector();
		private  Vector listPohon = new Vector();
		private  Vector listAgensi = new Vector();
		private  Vector listPohon2 = new Vector();
		private  Vector listMaklumatTanah = new Vector();
		private  Vector maklumatTanah = new Vector();
		
		
		public static  Vector getList(){
			return list;
		}
		public Vector getListPohon(){
			return listPohon;
		}
		public Vector getListDokumen(){
			return listDokumen;
		}
		public Vector getListAgensi(){
			return listAgensi;
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
		
		 
		 public static Vector getListPemohon(String usid)throws Exception {
			    Db db = null;
			    String sql = "";
			    try {
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			     
			    		//default list
			    		sql = "SELECT DISTINCT p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, "; 
			    		sql += " su.nama_suburusan, k.nama_kementerian, s.keterangan, p.id_masuk ";
			    		sql += " FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Users u "; 
			    		sql += " WHERE f.id_fail = p.id_fail ";
			    		sql += " AND f.id_suburusan = su.id_suburusan ";
			    		sql += " AND f.id_kementerian = k.id_kementerian "; 
			    		sql += " AND p.id_status = s.id_status "; 
						sql += " AND p.id_masuk = u.user_id ";
						sql += " AND su.id_suburusan in (51,52,53) ";
						sql += " AND u.user_id = '"+usid+"'";
						sql += " ORDER by p.tarikh_permohonan desc, p.no_permohonan desc "; 
			     
						ResultSet rs = stmt.executeQuery(sql);
						Vector list = new Vector();
			      
						Hashtable h = null;
						int bil = 1;

						while (rs.next()) {
							h = new Hashtable();
							h.put("bil", bil);
							h.put("id_fail", rs.getString("id_fail"));
							h.put("id_status", rs.getString("id_status"));
							h.put("id_permohonan", rs.getString("id_permohonan"));
							h.put("no_permohonan", rs.getString("no_permohonan"));
							h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
							h.put("nama_suburusan", rs.getString("nama_suburusan"));
							h.put("nama_kementerian", rs.getString("nama_kementerian"));
							h.put("status", rs.getString("keterangan"));
							if(rs.getString("no_fail") == null){
								h.put("no_fail","Belum Diluluskan");
							}else{
								h.put("no_fail",rs.getString("no_fail"));
							}
							list.addElement(h);
							bil++;
			    	  
						}
						return list;
			    	} finally {
			      if (db != null) db.close();
			    }
			    	
		}//close list pemohon
		
		public static void setList(String usid, String carianPermohonan, String carianTarikh, String cSuburusan, String cStatus)throws Exception {
		    Db db = null;
		    list.clear();
		    String sql = "";
		    
		    String cariTarikh = "";
		    if(carianTarikh!=""){
		    	cariTarikh = "to_date('" + carianTarikh + "','dd/MM/yyyy')";
		    }	    
		    String noPermohonan = carianPermohonan.trim();

		    try {
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		      
		      	//syarat
		      	sql = "SELECT DISTINCT p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, "; 
	    		sql += " su.nama_suburusan, k.nama_kementerian, s.keterangan, p.id_masuk ";
	    		sql += " FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Users u "; 
	    		sql += " WHERE f.id_fail = p.id_fail ";
	    		sql += " AND f.id_suburusan = su.id_suburusan ";
	    		sql += " AND f.id_kementerian = k.id_kementerian "; 
	    		sql += " AND p.id_status = s.id_status "; 
				sql += " AND p.id_masuk = u.user_id ";
				sql += " AND su.id_suburusan in (51,52,53) ";
				sql += " AND u.user_id = '"+usid+"'";
		     
		      //carian
				//NO PERMOHONAN
				if (carianPermohonan != "" && carianPermohonan != null) {
					if (!noPermohonan.equals("")) {
						sql = sql + " AND UPPER(p.no_permohonan) LIKE '%" + noPermohonan.toUpperCase() + "%'";
					}
				}//close if pemohon
		
				//TARIKH
				if (carianTarikh != "" && carianTarikh != null) {
					if (!cariTarikh.equals("")) {
						sql = sql + " AND UPPER(p.tarikh_permohonan) = "+cariTarikh;
					}
				}//close tarikh
	
				//SUBURUSAN
				if (cSuburusan != "" && cSuburusan != null) {
					if (!cSuburusan.equals("")) {
						sql = sql + " AND UPPER(su.id_suburusan) = '"+cSuburusan+"'";
					}
				}//close suburusan

	    		//STATUS
				if (cStatus != "" && cStatus != null) {
					if (!cStatus.equals("")) {
						sql = sql + " AND UPPER(s.id_status) = '"+cStatus+"'";
					}
				}//close status
		      
		      //sorting
		      sql +="ORDER by p.tarikh_permohonan desc, p.no_permohonan desc ";
		    
		    
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("id_fail", rs.getString("id_fail"));
		    	  h.put("id_permohonan", rs.getString("id_permohonan"));
		    	  h.put("no_permohonan", rs.getString("no_permohonan"));
		    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    	  h.put("nama_suburusan", rs.getString("nama_suburusan"));
		    	  h.put("nama_kementerian", rs.getString("nama_kementerian"));
		    	  h.put("status", rs.getString("keterangan"));
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
		
	public static void add(Hashtable data) throws Exception{
			
		    Db db = null;
		    String sql = "";
		    
		    try
		    {
		    	
		    	 	db = new Db();
		    	 	Statement stmt = db.getStatement();
		    	 	
		    	 	//user login id
			    	String id_user = (String)data.get("id_user");
			    	
		    	 	//Table pptpermohonan
		    	 	//String tarikh_permohonan = (String)data.get("tarikh_permohonan");
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
		      
		    	 	//String TP = "to_date('" + tarikh_permohonan + "','dd/MM/yyyy')";
		    	 	String TK = "to_date('" + tarikh_kehendaki + "','dd/MM/yyyy')";
		    	 	String TS = "to_date('" + tarikh_surat + "','dd/MM/yyyy')";
		      
		    	 	int id_suburusan = Integer.parseInt(suburusan);

		    	 	//status "DAFTAR"
		    	 	int status = 113;
		    	 	//status "PERMOHONAN ONLINE"
		    	 	int flag_jenisserahan = 1;
		      
		      
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
		    	
		      
		    	 	SQLRenderer rUrus = new SQLRenderer();				 
		    	 	rUrus.add("id_Suburusan");
		    	 	rUrus.add("kod_Suburusan");				      
		    	 	rUrus.add("id_Suburusan",id_suburusan);				      
		    	 	sql = rUrus.getSQLSelect("Tblrujsuburusan");
		    	 	ResultSet rsUrus = stmt.executeQuery(sql);
		    	 	while (rsUrus.next()) {
		    	 		kodSubUrusan = rsUrus.getString("kod_Suburusan");
		    	 	}
		      
		    	 	//no permohonan
		    	 	String noPermohonan = kodJabatan+"/"+module+"/"+kodSubUrusan+"/"+tahun+"-"+String.format("%06d",File.getSeqNo(seq_number1, seq_number2, seq_number3, seq_number4));
		    	      
		    	 	
		    	 	SQLRenderer rF = new SQLRenderer();
		    	 	rF.add("id_fail",idFail);
		    	 	rF.add("id_suburusan", id_suburusan);
		    	 	rF.add("id_negeri", projek_negeri);
		    	 	rF.add("id_kementerian", kementerian);
		    	 	rF.add("id_masuk",id_user);
		    	 	rF.add("tarikh_masuk",rF.unquote("sysdate"));
		    	 	sql = rF.getSQLInsert("tblpfdfail");
		    	 	stmt.executeUpdate(sql);
		   
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
		    	 	rPH.add("tarikh_permohonan", rPH.unquote("sysdate"));
		    	 	rPH.add("tarikh_kehendaki", rPH.unquote(TK));
		    	 	rPH.add("tarikh_surat", rPH.unquote(TS));
		    	 	rPH.add("id_agensi", id_agensi);
		    	 	rPH.add("id_negeri", id_negeri);
		    	 	rPH.add("id_daerah", id_daerah);
		    	 	rPH.add("id_status", status);
		    	 	rPH.add("flag_jenispermohonan", flag_jenisserahan);
		    	 	rPH.add("id_masuk",id_user);
		    	 	rPH.add("tarikh_masuk",rPH.unquote("sysdate"));
		    	 	sql = rPH.getSQLInsert("tblpptpermohonan");
		    	 	stmt.executeUpdate(sql);
	
		    	 	SQLRenderer rS = new SQLRenderer();
		    	 	rS.add("id_senaraisemak",idSenaraiSemak);
		    	 	rS.add("id_permohonan", idPermohonan);
		    	 	rS.add("id_masuk",id_user);
		    	 	rS.add("tarikh_masuk",rS.unquote("sysdate"));
		    	 	sql = rS.getSQLInsert("tblpptsenaraisemak");
		    	 	stmt.executeUpdate(sql);
		  
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
	}//close add
		
		public static void deleteMaklumatTanah(String id) throws Exception {
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
		
		
	@SuppressWarnings("unchecked")
	public static void updateMaklumatTanah(Hashtable data) throws Exception {
		   
		Db db = null;
		String sql = "";
		   
		try{
			
			  db = new Db();
			
		      String idHakmilik = (String)data.get("id_hakmilik");
			  String idnegeri = (String)data.get("editNegeri");
			  String iddaerah = (String)data.get("editDaerah");
			  String idmukim = (String)data.get("editMukim");
			  String id_user = (String)data.get("id_user");
			  String id_suburusan = (String)data.get("id_suburusan");
  
			  String catatan = "";
			  String idjenishakmilik = "";
			  String idluas = "";
			  String idlot = "";
			  String edit_no_hakmilik = "";
			  String edit_no_lot = "";
			  String edit_luas_lot = "";
			  String edit_anggaran_luas = "";
			  
			  if(id_suburusan.equals("51")){
	    			//seksyen 4
	    			catatan = (String)data.get("catatan");
			  }else{
				  	//seksyen8
				  	idjenishakmilik = (String)data.get("editJenisHakmilik");
				  	idluas = (String)data.get("editLuas");
				  	idlot = (String)data.get("editLot"); 
				  	edit_no_hakmilik = (String)data.get("edit_no_hakmilik");  
				  	edit_no_lot = (String)data.get("edit_no_lot");
				 	edit_luas_lot = (String)data.get("edit_luas_lot");
				 	edit_anggaran_luas = (String)data.get("edit_anggaran_luas");
			  }
 
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_hakmilik", idHakmilik);
			  r.add("id_negeri", idnegeri);
			  r.add("id_jenishakmilik", idjenishakmilik);
			  r.add("id_daerah", iddaerah);
			  r.add("id_mukim", idmukim);
			  r.add("id_unitluaslot", idluas);
			  r.add("id_lot", idlot);		  
			  r.add("no_hakmilik", edit_no_hakmilik);
			  r.add("no_lot", edit_no_lot);
			  r.add("luas_lot", edit_luas_lot);
			  r.add("luas_ambil", edit_anggaran_luas);
			  r.add("catatan",catatan);
			  r.add("id_kemaskini",id_user);
			  r.add("tarikh_kemaskini",r.unquote("sysdate"));
			  sql = r.getSQLUpdate("tblppthakmilik");
		      stmt.executeUpdate(sql);
		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close updateMaklumatTanah
		
		
	@SuppressWarnings("unchecked")
	public static void update(Hashtable data) throws Exception {
		    
		Db db = null;
		String sql = "";
		   
		try{
		      
		    	db = new Db();
				Statement stmt = db.getStatement();
		    	
		    	String id_user = (String)data.get("id_user");
		    	
		    	String idpermohonan = (String)data.get("id_permohonan");
		    	String idFail = (String)data.get("id_fail");

		    	String daerah = (String)data.get("daerah");
		    	String projeknegeri = (String)data.get("projeknegeri");
		    	String negeri = (String)data.get("negeri");
		    	String bandar = (String)data.get("bandar");
		    	String agensi = (String)data.get("agensi");
		    	String id_kementerian = (String)data.get("kementerian");
			  
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
			  
		    	String TH = "to_date('" + tarikh_hendak + "','dd/MM/yyyy')";
		    	String TS = "to_date('" + tarikh_surat + "','dd/MM/yyyy')";

		    	
		    	SQLRenderer r = new SQLRenderer();
		    	r.update("id_permohonan", idpermohonan);
		    	r.add("tarikh_kehendaki", r.unquote(TH));
		    	r.add("tarikh_surat", r.unquote(TS));
		    	r.add("poskod", poskod);
		    	r.add("tujuan", tujuan);
		    	r.add("no_rujukan_surat", rujukan_kementerian);
		    	r.add("alamat1", alamat1);
		    	r.add("alamat2", alamat2);
		    	r.add("alamat3", alamat3);
		    	r.add("id_negeri",negeri);
		    	r.add("id_agensi",agensi);
		    	r.add("id_daerah",daerah);
		    	r.add("id_mukim",bandar);
		    	r.add("flag_peruntukan", flagPeruntukan);
		    	r.add("flag_segera", flagSegera);	
		    	r.add("id_kemaskini",id_user);
		    	r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    	sql = r.getSQLUpdate("tblpptpermohonan");
		    	stmt.executeUpdate(sql);
		      
		    	//update projek negeri
		    	SQLRenderer rF = new SQLRenderer();
		    	rF.update("id_fail",idFail);
		    	rF.add("id_negeri", projeknegeri);
		    	rF.add("id_kementerian", id_kementerian);
		    	rF.add("id_kemaskini",id_user);
		    	rF.add("tarikh_kemaskini",rF.unquote("sysdate"));
		    	sql = rF.getSQLUpdate("tblpfdfail");
		    	stmt.executeUpdate(sql);
	
		  }
		  finally {
		     if (db != null) db.close();
		  }
	}//close update
		
		public void setListPohon(String idpermohonan) throws Exception {
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
			r.add("p.tarikh_surat");
			//r.add("a.nama_agensi");
			r.add("p.id_agensi");
			r.add("p.no_rujukan_surat");
			r.add("p.flag_peruntukan");
			r.add("p.flag_jenispermohonan");
			r.add("p.flag_segera");
			r.add("p.id_mukim");
			r.add("p.alamat1");
			r.add("p.alamat2");
			r.add("p.alamat3");
			r.add("us.nama_suburusan");
			r.add("us.id_suburusan");
			r.add("p.poskod");
			r.add("smk.id_senaraisemak");
			
			r.add("f.id_fail",r.unquote("p.id_fail"));
			r.add("k.id_kementerian",r.unquote("f.id_kementerian"));
			r.add("n.id_negeri",r.unquote("p.id_negeri"));
			r.add("s.id_status",r.unquote("p.id_status"));
			r.add("d.id_daerah",r.unquote("p.id_daerah"));
			//r.add("a.id_agensi",r.unquote("p.id_agensi"));
			r.add("us.id_suburusan",r.unquote("f.id_suburusan"));
			r.add("p.id_permohonan",r.unquote("smk.id_permohonan"));
			
			r.add("p.id_Permohonan",idpermohonan);
			
			sql = r.getSQLSelect("Tblrujsuburusan us, Tblpptsenaraisemak smk, Tblpfdfail f, Tblrujdaerah d, Tblrujnegeri n, Tblrujkementerian k, Tblrujstatus s, Tblpptpermohonan p");

			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
				h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
				h.put("id_senaraisemak", rs.getString("id_senaraisemak")==null?"":rs.getString("id_senaraisemak"));
				h.put("idNegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
				h.put("idFail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
				h.put("idDaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
				h.put("idSuburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
				h.put("idAgensi", rs.getString("id_agensi")==null?"0":rs.getString("id_agensi"));
				h.put("idKementerian", rs.getString("id_kementerian")==null?"":rs.getString("id_kementerian"));
				h.put("noPermohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));
				h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
				h.put("namaNegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
				h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
				h.put("tujuan", rs.getString("tujuan")==null?"":rs.getString("tujuan"));
				h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
				h.put("status", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
				h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"":Format.format(rs.getDate("tarikh_kehendaki")));
				h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
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
		
		public void setListAgensi(int id) throws Exception {
			Db db = null;
			listAgensi.clear();
			String sql = "";
			
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			
			r.add("p.id_permohonan");
			r.add("a.nama_agensi");
			r.add("a.id_agensi");
			
			r.add("a.id_agensi",r.unquote("p.id_agensi"));
			
			r.add("p.id_Permohonan",id);
			
			sql = r.getSQLSelect("Tblpptpermohonan p, tblrujagensi a");
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("idAgensi", rs.getString("id_agensi")==null?"":rs.getString("id_agensi"));
				h.put("nama_agensi", rs.getString("nama_agensi"));
				
				listAgensi.addElement(h);
			}
			}
			finally {
				if(db != null) db.close();
			}
			
		}//close setlistagensi
		
		public void setListPohon2(String idpermohonan) throws Exception {
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
			
		
			r.add("p.id_Permohonan",idpermohonan);
			
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
		
		 public static void  addDokumen(Hashtable data)throws Exception {
			 	Db db = null;
			    String sql = "";
			    Blob blob = null;
			    
			   try
			    {	 
			    	  long idLampiran = DB.getNextID("TBLPFDRUJLAMPIRAN_SEQ");
			    	  String idDokumen = (String)data.get("id_Dokumen");
			    	  String namaFail = (String)data.get("nama_Fail");
				      String jenisMime = (String)data.get("jenis_Mime");
				      String content = (String)data.get("content");
				      	       
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
					  
					  r.add("id_Lampiran",idLampiran);
				      r.add("id_Dokumen",idDokumen);
				      r.add("nama_Fail", namaFail);
				      r.add("jenis_Mime", jenisMime);
				      r.add("content",content);
	
				      
				      sql = r.getSQLInsert("tblpfdrujlampiran");  
				      stmt.executeUpdate(sql);
	   
			    }finally {
				      if (db != null) db.close();
			    }

		 }
		
		public static void add_maklumat_tanah(Hashtable data) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    	
		    		String idsuburusan = (String)data.get("id_suburusan");
		    		String idpermohonan = (String)data.get("id_permohonan");
		    		String idnegeri = (String)data.get("negeri");
		    		String iddaerah = (String)data.get("daerah");
		    		String idmukim = (String)data.get("mukim");
		    		
		    		String id_user = (String)data.get("id_user");
		    		
		    		//seksyen 4
		    		String catatan = "";
		    		
		    		//seksyen 8
		    		String idjenisHakMilik = "";
		    		String idluas = "";
		    		String idlot = "";
		    		String no_hakmilik = "";
		    		String no_lot = "";
		    		String luas_lot = "";
		    		String anggaran_luas = "";
		    		
		    		if(idsuburusan.equals("51")){
		    			//seksyen 4
		    			catatan = (String)data.get("catatan");
		    		}else{
		    			//seksyen 8 n sementara
		    			idjenisHakMilik = (String)data.get("jenisHakMilik");		    	
			    		idluas = (String)data.get("luas");
			    		idlot = (String)data.get("lot");		      
			    		no_hakmilik = (String)data.get("no_hakmilik");
			    		no_lot = (String)data.get("no_lot");
			    		luas_lot = (String)data.get("luas_lot");
			    		anggaran_luas = (String)data.get("anggaran_luas");
		    		}
		    		

		    		Statement stmt = db.getStatement();
		    		SQLRenderer r = new SQLRenderer();
		    		r.add("id_permohonan", idpermohonan);
		    		r.add("id_negeri", idnegeri); 	
		    		r.add("id_jenishakmilik", idjenisHakMilik);
		    		r.add("id_daerah", iddaerah);
		    		r.add("id_mukim", idmukim);
		    		r.add("id_unitluaslot", idluas);
		    		r.add("id_lot", idlot);
		    		r.add("catatan", catatan);
		    		r.add("luas_ambil", anggaran_luas);
		    		r.add("no_hakmilik", no_hakmilik);
		    		r.add("no_lot", no_lot);
		    		r.add("luas_lot", luas_lot);
		    		r.add("tarikh_masuk",r.unquote("sysdate"));
		    		r.add("id_masuk",id_user);
		    		sql = r.getSQLInsert("tblppthakmilik");
		    		stmt.executeUpdate(sql);
		      
		      
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close add_maklumat_tanah
		
		public void setListMaklumatTanah(String idpermohonan) throws Exception{
			Db db = null;
			listMaklumatTanah.clear();
			String sql = "";
			
			try {
				
					db = new Db();
					Statement stmt = db.getStatement();
				
					sql = "SELECT DISTINCT p.id_permohonan, lt.keterangan as kodlot, ls.keterangan as unitluaslot, m.id_hakmilik, m.id_negeri, n.nama_negeri, m.id_jenishakmilik, ";
					sql += " m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, m.no_hakmilik, m.no_lot, m.id_lot, m.luas_lot, m.catatan ";  
					sql += " FROM Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m "; 
					sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) "; 
					sql += " AND m.id_negeri = n.id_negeri "; 
					sql += " AND ls.id_luas(+) = m.id_unitluaslot ";  
					sql += " AND m.id_lot = lt.id_lot(+) "; 
					sql += " AND m.id_mukim = mk.id_mukim(+) "; 
					sql += " AND p.id_Permohonan = '"+idpermohonan+"'";
				
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;
					int bil = 1;
				
					while (rs.next()){
						h = new Hashtable();
						h.put("bil", bil);
						h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
						h.put("unitluaslot", rs.getString("unitluaslot")==null?"":rs.getString("unitluaslot"));
						h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
						h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
						h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
						h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
						h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
						h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
						h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
						h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
						h.put("luas_ambil", rs.getString("luas_ambil")==null?"":rs.getString("luas_ambil"));
						h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
						h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
						h.put("luas_lot", rs.getString("luas_lot")==null?"":rs.getString("luas_lot"));
						h.put("kod_lot", rs.getString("kodlot")==null?"":rs.getString("kodlot"));
						h.put("id_lot", rs.getString("id_lot")==null?"":rs.getString("id_lot"));
						h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
						listMaklumatTanah.addElement(h);
						bil++;	
				}
				//return list;
			}finally {
				if(db != null) db.close();
			}
			}//close setDataListKertas
		
		
		public void setMaklumatTanah(String idHakmilik) throws Exception {
			Db db = null;
			maklumatTanah.clear();
			String sql = "";
			int one = 1;
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT hk.id_hakmilik, hk.id_lot, ls.id_luas as id_luaslot, p.id_permohonan, jhk.id_jenishakmilik, n.id_negeri, ";
				sql += " d.id_daerah, mk.id_mukim, hk.luas_ambil, hk.no_hakmilik, hk.no_lot, hk.luas_lot, hk.no_pt, n.nama_negeri, "; 
				sql += " d.nama_daerah, ls.keterangan, mk.nama_mukim, jhk.keterangan, hk.catatan "; 
				sql += " FROM Tblppthakmilik hk, Tblrujjenishakmilik jhk, Tblrujmukim mk, Tblrujluas ls, "; 
				sql += " Tblrujdaerah d, Tblrujnegeri n, Tblpptpermohonan p ";
				sql += " WHERE n.id_negeri(+) = hk.id_negeri "; 
				sql += " AND d.id_daerah(+) = hk.id_daerah "; 
				sql += " AND ls.id_luas(+) = hk.id_unitluaslot "; 
				sql += " AND mk.id_mukim(+) = hk.id_mukim "; 
				sql += " AND jhk.id_jenishakmilik(+) = hk.id_jenishakmilik "; 
				sql += " AND p.id_permohonan = hk.id_permohonan "; 
				sql += " AND hk.id_hakmilik = '"+idHakmilik+"'";
		
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
			
			while(rs.next()) {
				h = new Hashtable();
				h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("id_lot", rs.getString("id_lot")==null?"":rs.getString("id_lot"));
				h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
				h.put("id_luasLot", rs.getString("id_luaslot")==null?"":rs.getString("id_luaslot"));
				h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
				h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
				h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
				h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
				h.put("luas_ambil", rs.getString("luas_ambil")==null?"":rs.getString("luas_ambil"));
				h.put("luas_lot", rs.getString("luas_lot")==null?"":rs.getString("luas_lot"));
				h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
				h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
				h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				
				maklumatTanah.addElement(h);
			}
			}
			finally {
				if(db != null) db.close();
			}
			
		}//close setlistpohon
		
		//hantar (status change to "5")
		public static void hantar(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		   
		    try
		    {
		    	 String id_user = (String)data.get("id_user");
		    	 String idPermohonan = (String)data.get("id_permohonan");
		    	 int newStatus = 5;
			  
		    	 db = new Db();
		    	 Statement stmt = db.getStatement();
		    	 SQLRenderer r = new SQLRenderer();
		    	 r.update("id_permohonan", idPermohonan);
		    	 r.add("id_status", newStatus);
		    	 r.add("id_kemaskini",id_user);
		    	 r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    	 sql = r.getSQLUpdate("tblpptpermohonan");
		    	 stmt.executeUpdate(sql);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }//close hantar
		
		public static void semakanTambah(String idsemakan, String idpermohonan, String idSenaraiSemak) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      //long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
		      int id_SenaraiSemak = Integer.parseInt(idSenaraiSemak);
		      int idPermohonan = Integer.parseInt(idpermohonan);
		      String idSemakan = idsemakan;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.update("id_senaraisemak", id_SenaraiSemak);
		      r.add("id_permohonan", idPermohonan);
		      r.add("id_semakansenarai", idSemakan);
		      sql = r.getSQLUpdate("Tblpptsenaraisemak");
		      stmt.executeUpdate(sql);
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	
		
		 public static Vector getSenaraiSemakan(String idpermohonan)throws Exception {
			    Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      r.add("i.id_senaraisemak");	 
			      r.add("i.semak1");
			      r.add("i.semak2");
			      r.add("i.semak3");
			      r.add("i.semak4");
			      r.add("i.semak5");
			      r.add("i.semak6");
			      r.add("i.semak7");
			
			      r.add("i.id_permohonan",idpermohonan);
			      
			      sql = r.getSQLSelect("tblpptsenaraisemak i");
			      ResultSet rs = stmt.executeQuery(sql);
			      Vector list = new Vector();
			      
			      Hashtable h = null;

			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  h.put("id_senaraisemak",rs.getString("id_senaraisemak"));
			    	  h.put("semak1", rs.getString("semak1")==null?"0":rs.getString("semak1"));
			    	  h.put("semak2", rs.getString("semak2")==null?"0":rs.getString("semak2"));
			    	  h.put("semak3", rs.getString("semak3")==null?"0":rs.getString("semak3"));
			    	  h.put("semak4", rs.getString("semak4")==null?"0":rs.getString("semak4"));
			    	  h.put("semak5", rs.getString("semak5")==null?"0":rs.getString("semak5"));
			    	  h.put("semak6", rs.getString("semak6")==null?"0":rs.getString("semak6"));
			    	  h.put("semak7", rs.getString("semak7")==null?"0":rs.getString("semak7"));
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
			      sql +="AND k.id_kementerian = '"+idK+"' ";

			      ResultSet rs = stmt.executeQuery(sql);
			      Vector list = new Vector();
			      
			      Hashtable h = null;
			     
			      while (rs.next()) {
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
		 
		//--Get alamat agensi--//
		 public static Vector getAlamatAgensi(int idA)throws Exception {
			    Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			     
			      sql = "SELECT a.alamat1, a.alamat2, a.alamat3, a.poskod, n.id_negeri ";
			      sql +="FROM Tblrujagensi a, Tblrujnegeri n ";
			      sql +="WHERE a.id_negeri = n.id_negeri ";
			      sql +="AND a.id_agensi = "+idA+" ";

			      ResultSet rs = stmt.executeQuery(sql);
			      Vector list = new Vector();
			      
			      Hashtable h = null;
			     
			      while (rs.next()) {
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
			  }//find alamat Agensi
		 
		 //view list dokumen by id
		 public static void  setListDokumen(String idpermohonan)throws Exception {
			    Db db = null;
			    listDokumen.clear();
			    String sql = "";
			    
			    try {
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
			      
			      r.add("p.id_permohonan",idpermohonan);
			      r.add("p.id_permohonan",r.unquote("a.id_permohonan"));
			
			      sql = r.getSQLSelect("Tblpptdokumen a, tblpptpermohonan p");
			     
			      ResultSet rs = stmt.executeQuery(sql);
			     
			      Hashtable h;
			      int bil = 1;
			    
			      while (rs.next()) {
			    	  
			    	  h = new Hashtable();
			    	 
			    	  h.put("bil", bil);
			    	  h.put("id_permohonan",rs.getInt("id_permohonan"));
			    	  h.put("id_Dokumen", rs.getString("id_Dokumen")== null?"":rs.getString("id_Dokumen"));
			    	  h.put("nama_Fail", rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
			    	  h.put("jenis_Mime",rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));
			    	  h.put("tajuk",rs.getString("tajuk")== null?"":rs.getString("tajuk"));
			    	  h.put("keterangan",rs.getString("keterangan")== null?"":rs.getString("keterangan"));
			          
			    	  listDokumen.addElement(h);
			    	  bil++;	    	
			      }			    
			      //return list;
			    } finally {
			      if (db != null) db.close();
			    }
			}
		 
}//close class
