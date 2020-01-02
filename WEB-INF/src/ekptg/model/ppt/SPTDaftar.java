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
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class SPTDaftar {
	static Logger myLogger = Logger.getLogger(SPTDaftar.class);
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
	
	@SuppressWarnings("unchecked")
	public static Vector getListPemohon()throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk ";
		    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +="AND p.id_status = s.id_status ";
		    		sql +="AND f.id_suburusan = '51' ";
		    		sql +="AND p.id_status != 113 ";
		    		//sql +="AND p.id_status = ANY(5,127,128)";
		    		sql +="ORDER by p.tarikh_permohonan desc, p.no_permohonan desc ";

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
		    	  
		    		}//close while
		    		return list;
		    	}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListPemohon
	
	@SuppressWarnings("unchecked")
	public static void setList(String carianPermohonan, String carianTarikh, String cStatus)throws Exception {
		
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
	      
	    		sql = "SELECT distinct p.id_permohonan, p.no_permohonan, p.no_rujukan_surat, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '51' ";
	    		sql +="AND p.id_status != 113 ";
	    		//sql +="AND p.id_status = ANY(5,127,128)";
	     
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
	
//				//SUBURUSAN
//				if (cSuburusan != "" && cSuburusan != null) {
//					if (!cSuburusan.equals("")) {
//						sql = sql + " AND UPPER(su.id_suburusan) = '"+cSuburusan+"'";
//					}
//				}//close suburusan

	    		//STATUS
				if (cStatus != "" && cStatus != null) {
					if (!cStatus.equals("")) {
						sql = sql + " AND UPPER(s.id_status) = '"+cStatus+"'";
					}
				}//close status

	    		sql +=" ORDER by p.tarikh_permohonan desc, p.no_permohonan desc ";
	      
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
	    			
	      }//close while
	      //return list;
	    }// 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	  }//close setlist
	
	@SuppressWarnings("unchecked")
	public static void addSPT(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try
	    {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	
	    	//user login id
	    	String id_user = (String)data.get("id_user");
	    	
	    	//Table pptpermohonan
	    	String tarikh_kehendaki = (String)data.get("tarikh_kehendaki");
	    	String tarikh_surat = (String)data.get("tarikh_surat");	       
	    	String flag_peruntukan = (String)data.get("flag_peruntukan");
	    	String flag_segera = (String)data.get("flag_segera");
	    	String tujuan = (String)data.get("tujuan");
	    	String alamat1 = (String)data.get("alamat1");
	    	String alamat2 = (String)data.get("alamat2");
	    	String alamat3 = (String)data.get("alamat3");
	    	String poskod = (String)data.get("poskod");
	    	String id_bandar = (String)data.get("bandar");
	    	String rujukan_surat = (String)data.get("rujukan_kementerian");	      
	    	String id_agensi = (String)data.get("agensi");  
	    	String id_negeri = (String)data.get("negeri");
	    	String id_daerah = (String)data.get("daerah");
	      
	    	//table pfdfail
	    	String id_suburusan = (String)data.get("suburusan");
	    	String id_projek_negeri = (String)data.get("projek_negeri");
	    	String id_kementerian = (String)data.get("kementerian");

	    	//convert string to date
	    	String TK = "to_date('" + tarikh_kehendaki + "','dd/MM/yyyy')";
	    	String TS = "to_date('" + tarikh_surat + "','dd/MM/yyyy')";
	      
	    	
	    	//status "PERMOHONAN HQ"
	    	int status = 5;
	    	
	    	//status "PERMOHONAN KAUNTER"
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

	    	SQLRenderer rUrus = new SQLRenderer();				 
	    	rUrus.add("id_Suburusan");
	    	rUrus.add("kod_Suburusan");				      
	    	rUrus.add("id_Suburusan",id_suburusan);				      
	    	sql = rUrus.getSQLSelect("Tblrujsuburusan");
	    	ResultSet rsUrus = stmt.executeQuery(sql);
	    	while (rsUrus.next()) {
	    		kodSubUrusan = rsUrus.getString("kod_Suburusan");
	    	}
	      
	    	//generate no permohonan
	    	String noPermohonan = kodJabatan+"/"+module+"/"+kodSubUrusan+"/"+tahun+"-"+String.format("%06d",File.getSeqNo(seq_number1, seq_number2, seq_number3, seq_number4));
	      
	    
	    	SQLRenderer rF = new SQLRenderer();
	    	rF.add("id_fail",idFail);
	    	rF.add("id_suburusan", id_suburusan);
	    	rF.add("id_negeri", id_projek_negeri);
	    	rF.add("id_kementerian", id_kementerian);
	    	rF.add("tarikh_masuk",rF.unquote("sysdate"));
			rF.add("id_masuk",id_user);
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
	     	rPH.add("id_mukim", id_bandar);
	     	rPH.add("poskod", poskod);
	     	rPH.add("no_rujukan_surat", rujukan_surat);
	     	rPH.add("tarikh_permohonan", rPH.unquote("sysdate"));
	     	rPH.add("tarikh_kehendaki", rPH.unquote(TK));
	     	rPH.add("tarikh_surat", rPH.unquote(TS));
	     	rPH.add("id_negeri", id_negeri);
	     	rPH.add("id_daerah", id_daerah);
	     	rPH.add("id_status", status);
	     	rPH.add("id_agensi", id_agensi);
	     	rPH.add("flag_jenispermohonan", flag_jenisserahan);
	     	rPH.add("tarikh_masuk",rPH.unquote("sysdate"));
			rPH.add("id_masuk",id_user);
	     	sql = rPH.getSQLInsert("tblpptpermohonan");
	     	stmt.executeUpdate(sql);
	  
	     	SQLRenderer rS = new SQLRenderer();
	     	rS.add("id_senaraisemak",idSenaraiSemak);
	     	rS.add("id_permohonan", idPermohonan);
	    	rS.add("tarikh_masuk",rS.unquote("sysdate"));
			rS.add("id_masuk",id_user);
	     	sql = rS.getSQLInsert("tblpptsenaraisemak");
	     	stmt.executeUpdate(sql);
	    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close add
	
	
	public static void deleteMaklumatTanah(int id) throws Exception {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	    	
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
	   
	    try
	    {
	    
	    	 db = new Db();
	    	 
	    	 String id_hakmilik = (String)data.get("id_hakmilik");
	      
	    	 String catatan = (String)data.get("catatan");
	    	 
	    	 String id_user = (String)data.get("id_user");
	    	 
	    	 String negeri = (String)data.get("editNegeri");
	    	// String jenishakmilik = (String)data.get("editJenisHakmilik");
	    	 String daerah = (String)data.get("editDaerah");
	    	 String mukim = (String)data.get("editMukim");
	    	// String luas = (String)data.get("editLuas");
	    	// String lot = (String)data.get("editLot");
	    	 //String luasambil = (String)data.get("editLuasAmbil");
	
		  
	    	// String edit_no_hakmilik = (String)data.get("edit_no_hakmilik");  
	    	// String edit_no_lot = (String)data.get("edit_no_lot");
	    	// String edit_luas_lot = (String)data.get("edit_luas_lot");
	    	 //String edit_no_pt = (String)data.get("edit_no_pt");
	    	// String edit_anggaran_luas = (String)data.get("edit_anggaran_luas");
		  
	    	// int idNegeri = Integer.parseInt(negeri);
	    	// int idJenisHakMilik = Integer.parseInt(jenishakmilik);
	    	// int idDaerah = Integer.parseInt(daerah);
	    	// int idMukim = Integer.parseInt(mukim);
	    	// int idLuas = Integer.parseInt(luas);
	    	 //int idLot = Integer.parseInt(lot);
	    	 //int idLuasAmbil = Integer.parseInt(luasambil);
		  
	    	 //int luasAmbil = Integer.parseInt(edit_anggaran_luas);
		  
	    	 
	    	 Statement stmt = db.getStatement();
	    	 SQLRenderer r = new SQLRenderer();
	    	 r.update("id_hakmilik", id_hakmilik);
	    	 r.add("id_negeri", negeri);
	    	// r.add("id_jenishakmilik", jenishakmilik);
	    	 r.add("id_daerah", daerah);
	    	 r.add("id_mukim", mukim);
	    	// r.add("id_unitluaslot", luas);
	    	//r.add("id_lot", lot);		  
	    	// r.add("no_hakmilik", edit_no_hakmilik);
	    	// r.add("no_lot", edit_no_lot);
	    	// r.add("luas_lot", edit_luas_lot);
	    	 		//r.add("no_pt", edit_no_pt);
	    	// r.add("luas_ambil", edit_anggaran_luas);
	    	 r.add("catatan",catatan);
	    	 r.add("tarikh_kemaskini",r.unquote("sysdate"));
		     r.add("id_kemaskini",id_user);
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
	    	 	
	    		String id_permohonan = (String)data.get("id_permohonan"); 
	    		String id_fail = (String)data.get("id_fail"); 
	    		String id_check = (String)data.get("id_senaraisemak"); 
	      
	    		String id_urusan = (String)data.get("id_urusan"); 
	    		int idUrusan = 0;
	    		if(id_urusan!=""){
	    			idUrusan = Integer.parseInt(id_urusan);
	    		}
	      
	    		//checkbox seksyen 8
	    		String semak_1 = (String)data.get("semak1"); 
	    		String semak_2 = (String)data.get("semak2"); 
	    		String semak_3 = (String)data.get("semak3"); 
	    		String semak_4 = (String)data.get("semak4"); 
	    		String semak_5 = (String)data.get("semak5"); 
	    		String semak_6 = (String)data.get("semak6"); 
	    		String semak_7 = (String)data.get("semak7"); 
	      
	    		//checkbox seksyen 4
	    		String semak_10 = (String)data.get("semak10"); 
	    		String semak_20 = (String)data.get("semak20");
	     
	    		String id_daerah = (String)data.get("daerah");
	    		String id_projekNegeri = (String)data.get("projeknegeri");
	    		String id_negeri = (String)data.get("negeri");
	    		String id_bandar = (String)data.get("bandar");
	    		String id_agensi = (String)data.get("agensi");
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
	    		r.update("id_permohonan", id_permohonan);
	    		r.add("tarikh_kehendaki", r.unquote(TH));
	    		r.add("tarikh_surat", r.unquote(TS));
	    		r.add("poskod", poskod);
	    		r.add("tujuan", tujuan);
	    		r.add("no_rujukan_surat", rujukan_kementerian);
	    		r.add("alamat1", alamat1);
	    		r.add("alamat2", alamat2);
	    		r.add("alamat3", alamat3);
	    		r.add("id_negeri",id_negeri);
	    		r.add("id_agensi", id_agensi);
	    		r.add("id_daerah",id_daerah);
	    		r.add("id_mukim",id_bandar);
	    		r.add("flag_peruntukan", flagPeruntukan);
	    		r.add("flag_segera", flagSegera);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	   	      	r.add("id_kemaskini",id_user);
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);

	    		//projek negeri
	    		SQLRenderer rF = new SQLRenderer();
	    		rF.update("id_fail",id_fail);
	    		rF.add("id_negeri", id_projekNegeri);
	    		rF.add("id_kementerian", id_kementerian);
	    		rF.add("tarikh_kemaskini",rF.unquote("sysdate"));
	   	      	rF.add("id_kemaskini",id_user);
	    		sql = rF.getSQLUpdate("tblpfdfail");
	    		stmt.executeUpdate(sql);
	      
	    		//checkbutton seksyen 8
	    		if(idUrusan==52)
	    		{
	    			SQLRenderer rCK = new SQLRenderer();
	    			rCK.update("i.id_senaraisemak",id_check);
	    			rCK.add("i.id_permohonan", id_permohonan);
	    			rCK.add("i.semak1", semak_1);
	    			rCK.add("i.semak2", semak_2);
	    			rCK.add("i.semak3", semak_3);
	    			rCK.add("i.semak4", semak_4);
	    			rCK.add("i.semak5", semak_5);
	    			rCK.add("i.semak6", semak_6);
	    			rCK.add("i.semak7", semak_7);
	    			rCK.add("tarikh_kemaskini",rCK.unquote("sysdate"));
	    		    rCK.add("id_kemaskini",id_user);
	    			sql = rCK.getSQLUpdate("tblpptsenaraisemak i");
	    			stmt.executeUpdate(sql);
	    		}
	      
	    		//checkbutton seksyen 4
	    		if(idUrusan==51)
	    		{
	    			SQLRenderer rCK4 = new SQLRenderer();
	    			rCK4.update("j.id_senaraisemak",id_check);
	    			rCK4.add("j.id_permohonan", id_permohonan);
	    			rCK4.add("j.semak1", semak_10);
	    			rCK4.add("j.semak2", semak_20);
	    			rCK4.add("tarikh_kemaskini",rCK4.unquote("sysdate"));
	    		    rCK4.add("id_kemaskini",id_user);
	    			sql = rCK4.getSQLUpdate("tblpptsenaraisemak j");
	    			stmt.executeUpdate(sql);
	    		}
	      
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }//close update
	
	
	@SuppressWarnings("unchecked")
	public void setListPohon(String id) throws Exception {
		Db db = null;
		listPohon.clear();
		String sql = "";
		
		
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
		
				r.add("p.id_permohonan");
				r.add("p.id_status");
				r.add("p.flag_jenispermohonan");
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
		
				r.add("p.id_Permohonan",id);
		
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
	
	@SuppressWarnings("unchecked")
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
		
	}//close setlistpohon
	
	@SuppressWarnings("unchecked")
	public void setListPohon2(String id) throws Exception {
		
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
	
	
	
	public static void add_maklumat_tanah(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    try
	    {
	      
	    	db = new Db();
	    	
	    	String catatan = (String)data.get("catatan");
	    	
	    	String id_user = (String)data.get("id_user");
	    	
	    	String id_permohonan = (String)data.get("id_permohonan");
	    	String id_negeri = (String)data.get("negeri");
	    	String id_jenishakmilik = (String)data.get("jenisHakMilik");
	    	String id_daerah = (String)data.get("daerah");
	    	String id_mukim = (String)data.get("mukim");
	    	String id_luas = (String)data.get("luas");
	    	String id_lot = (String)data.get("lot");
	    	//String luas_diambil = (String)data.get("luas_diambil");
	      
	    	String no_hakmilik = (String)data.get("no_hakmilik");
	    	String no_lot = (String)data.get("no_lot");
	    	String luas_lot = (String)data.get("luas_lot");
	    	//String no_pt = (String)data.get("no_pt");
	    	String luas_ambil = (String)data.get("anggaran_luas");	      
	    	// int id_luas_diambil = Integer.parseInt(luas_diambil);

	    	
	    	Statement stmt = db.getStatement();
	    	SQLRenderer r = new SQLRenderer();
	    	r.add("id_permohonan", id_permohonan);
	    	r.add("id_negeri", id_negeri); 	
	    	r.add("id_jenishakmilik", id_jenishakmilik);
	    	r.add("id_daerah", id_daerah);
	    	r.add("id_mukim", id_mukim);
	    	r.add("id_unitluaslot", id_luas);
	    	r.add("id_lot", id_lot);
	    	//r.add("id_unitluasambil", id_luas_diambil);    	
	    	r.add("luas_ambil", luas_ambil);
	    	r.add("no_hakmilik", no_hakmilik);
	    	r.add("no_lot", no_lot);
	    	r.add("luas_lot", luas_lot);
	    	r.add("tarikh_masuk",r.unquote("sysdate"));
	    	r.add("id_masuk",id_user);
	    	r.add("catatan",catatan);
	    	//r.add("no_pt", no_pt);
	    	sql = r.getSQLInsert("tblppthakmilik");
	    	stmt.executeUpdate(sql);
	    	
	    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close add_maklumat_tanah
	
	public void setListMaklumatTanah(String id) throws Exception{
		
		Db db = null;
		listMaklumatTanah.clear();
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = " SELECT DISTINCT m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
				sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
				sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.no_pt "; 
				sql += " FROM Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m "; 
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
				sql += " AND m.id_negeri = n.id_negeri "; 
				sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
				sql += " AND m.id_lot = lt.id_lot(+) "; 
				sql += " AND m.id_mukim = mk.id_mukim(+) ";  
				sql += " AND p.id_Permohonan = '"+id+"'";
				
				ResultSet rs = stmt.executeQuery(sql);
		
				Hashtable h;
				int bil = 1;
			
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
					h.put("unitluaslot", rs.getString("unit1")==null?"-":rs.getString("unit1"));
					//h.put("unitluasambil", rs.getString(3)==null?"":rs.getString(3));
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
					h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"-":rs.getString("nama_mukim"));
					h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
					//h.put("id_unitluasambil", rs.getString("id_unitluasambil")==null?"":rs.getString("id_unitluasambil"));
					h.put("luas_ambil", rs.getString("luas_ambil")==null?"":rs.getString("luas_ambil"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
					h.put("no_lot", rs.getString("no_lot")==null?"-":rs.getString("no_lot"));
					h.put("luas_lot", rs.getString("luas_lot")==null?"":rs.getString("luas_lot"));
					h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
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
			
			sql = "SELECT DISTINCT hk.id_hakmilik, ls.id_luas, p.id_permohonan, jhk.id_jenishakmilik, n.id_negeri, "; 
			sql += " hk.id_lot, d.id_daerah, mk.id_mukim, hk.luas_ambil, hk.no_hakmilik, hk.no_lot, ";
			sql += " hk.luas_lot, hk.no_pt, n.nama_negeri, d.nama_daerah, ls.keterangan, mk.nama_mukim, jhk.keterangan, catatan ";  
			sql += " FROM Tblppthakmilik hk, Tblrujjenishakmilik jhk, Tblrujmukim mk, Tblrujluas ls, Tblrujdaerah d, "; 
			sql += " Tblrujnegeri n, Tblpptpermohonan p ";
			sql += " WHERE n.id_negeri = hk.id_negeri "; 
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
			h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
			h.put("id_luasLot", rs.getString(2)==null?"":rs.getString(2));
			h.put("id_lot", rs.getString("id_lot")==null?"":rs.getString("id_lot"));
			//h.put("id_luasAmbil", rs.getString(3)==null?"":rs.getString(3));
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
	
	//sahkan (status change to "127")
	public static void sahkan(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	   
	    try
	    {
	    	
	      String idPermohonan = (String)data.get("id_permohonan");
	      String newStatus = "127";
	      String id_user = (String)data.get("id_user");
	      
		  db = new Db();
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  r.update("id_permohonan", idPermohonan);
		  r.add("id_status", newStatus);		
		  r.add("tarikh_kemaskini",r.unquote("sysdate"));
	      r.add("id_kemaskini",id_user);
		  sql = r.getSQLUpdate("tblpptpermohonan");
	      stmt.executeUpdate(sql);
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
		     
		      db = new Db();
		    	
		      String id_user = (String)data.get("id_user");
		      
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
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_permohonan", id_permohonan);
		      r.add("id_status", id_status);
		      r.add("tarikh_kemaskini",r.unquote("sysdate"));
		      r.add("id_kemaskini",id_user);
		      sql = r.getSQLUpdate("Tblpptpermohonan");
		      stmt.executeUpdate(sql);
		      
		      //masukkan nofail(generated) ke pfdfail
		      Statement stmtF = db.getStatement();
			  SQLRenderer rF = new SQLRenderer();
			  rF.update("id_fail", id_fail);
			  rF.add("no_Fail", noFail);
			  rF.add("tarikh_kemaskini",rF.unquote("sysdate"));
		      rF.add("id_kemaskini",id_user);
		      sql = rF.getSQLUpdate("Tblpfdfail");
		      stmtF.executeUpdate(sql);
		      		
		    }
		    finally {
		      if (db != null) db.close();
		    }
	}//close luluskan

	
	 public static Vector getSenaraiSemakan(String idpermohonan)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = " SELECT DISTINCT ID_SENARAISEMAK,SEMAK1,SEMAK2,SEMAK3,SEMAK4,SEMAK5,SEMAK6,SEMAK7" +
		      		" FROM TBLPPTSENARAISEMAK WHERE ID_PERMOHONAN = '"+idpermohonan+"'";

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id_senaraisemak", rs.getString("ID_SENARAISEMAK")==null?"":rs.getString("ID_SENARAISEMAK"));
		    	  h.put("semak1", rs.getString("SEMAK1")==null?"0":rs.getString("SEMAK1"));
		    	  h.put("semak2", rs.getString("SEMAK2")==null?"0":rs.getString("SEMAK2"));
		    	  h.put("semak3", rs.getString("SEMAK3")==null?"0":rs.getString("SEMAK3"));
		    	  h.put("semak4", rs.getString("SEMAK4")==null?"0":rs.getString("SEMAK4"));
		    	  h.put("semak5", rs.getString("SEMAK5")==null?"0":rs.getString("SEMAK5"));
		    	  h.put("semak6", rs.getString("SEMAK6")==null?"0":rs.getString("SEMAK6"));
		    	  h.put("semak7", rs.getString("SEMAK7")==null?"0":rs.getString("SEMAK7"));
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
		      sql +="WHERE k.id_negeri = n.id_negeri(+) ";
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
	 
	//--Get alamat Kementerian--//
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
	 public static void  setListDokumen(String id)throws Exception {
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
		      
		      r.add("p.id_permohonan",id);
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
	 
	 
	 
	 
	 /*
	  * 
	  * 
	  * SEKSYEN 8
	  * 
	  * 
	  */
	 
	 
	 
	@SuppressWarnings("unchecked")
	public static Vector getListPemohonSeksyen8()throws Exception {
			 
		Db db = null;
		String sql = "";
			    
		try{
			    	
			  	db = new Db();
			    Statement stmt = db.getStatement();
			     
			    sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk ";
			    sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
			    sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
			    sql +="AND p.id_status = s.id_status ";
			    sql +="AND f.id_suburusan = '52' ";
			    sql +="ORDER by p.tarikh_permohonan desc, p.no_permohonan desc ";
			     
			    ResultSet rs = stmt.executeQuery(sql);
			    Vector list = new Vector();
			      
			    Hashtable h;
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
			    	  
			    }//close while
			    	return list;
		}//close try 
		finally{
			if (db != null) db.close();
		}//close finally
			    	
	}//close getListPemohonSeksyen 8
	
	
	//Carian
	private static Vector listCarianSek8 = new Vector();
	public static  Vector getListCarianSek8(){
		return listCarianSek8;
	}
	
	@SuppressWarnings("unchecked")
	public static void setListCarianSek8(String carianPermohonan, String carianTarikh, String cStatus)throws Exception {
		
	    Db db = null;
	    listCarianSek8.clear();
	    String sql = "";
	    
	    String cariTarikh = "";
	    if(carianTarikh!=""){
	    	cariTarikh = "to_date('" + carianTarikh + "','dd/MM/yyyy')";
	    }	    
	    
	    String noPermohonan = carianPermohonan.trim();
	    
	    try{
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT distinct p.id_permohonan, p.no_permohonan, p.no_rujukan_surat, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '52' ";
	     
	    		//NO PERMOHONAN
				if (carianPermohonan != "" && carianPermohonan != null) {
					if (!noPermohonan.equals("")) {
						sql = sql + " AND UPPER(p.no_permohonan) LIKE '%" + noPermohonan.toUpperCase() + "%'";
					}
				}
		
				//TARIKH
				if (carianTarikh != "" && carianTarikh != null) {
					if (!cariTarikh.equals("")) {
						sql = sql + " AND UPPER(p.tarikh_permohonan) = "+cariTarikh;
					}
				}
	
//				//SUBURUSAN
//				if (cSuburusan != "" && cSuburusan != null) {
//					if (!cSuburusan.equals("")) {
//						sql = sql + " AND UPPER(su.id_suburusan) = '"+cSuburusan+"'";
//					}
//				}

	    		//STATUS
				if (cStatus != "" && cStatus != null) {
					if (!cStatus.equals("")) {
						sql = sql + " AND UPPER(s.id_status) = '"+cStatus+"'";
					}
				}
	    		sql +=" ORDER by p.tarikh_permohonan desc, p.no_permohonan desc ";

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
	    			listCarianSek8.addElement(h);
	    			bil++;
	    			
	      }//close while

	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	    
	  }//close setlist
	 
	 
}//close class
