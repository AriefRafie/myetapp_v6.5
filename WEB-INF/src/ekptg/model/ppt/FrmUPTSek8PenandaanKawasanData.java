package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmUPTSek8PenandaanKawasanData {
	
	static Logger myLogger = Logger.getLogger(FrmUPTSek8PenandaanKawasanData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector listcarian = null;
	private static Vector dataKawasanPenandaan = null;
	private  Vector listMaklumatTanah = null;
	
	
	public static Vector getListCarian(){
		return listcarian;
	}
	public Vector getDataKawasanPenandaan(){
		return dataKawasanPenandaan;
	}
	public Vector getListMaklumatTanah(){
		return listMaklumatTanah;
	}
	
	
	public static String sqlListPermohonan(String userIdNegeri)
	{

		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +=" p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +=" FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +=" AND p.id_status = s.id_status ";
		sql +=" AND f.id_suburusan = '52' ";
		//sql +=" AND p.id_status IN (38,58,76,74)";
		
		sql += " AND (p.id_permohonan in (select distinct hx.id_permohonan from Tblppttandakawasan hx "; 
		sql += " where hx.id_permohonan = p.id_permohonan) ";
		sql += " OR p.id_permohonan in (select distinct wx.id_permohonan from Tblpptwarta wx ";
		sql += " where wx.id_permohonan = p.id_permohonan)) ";
		
		
		//sql +=" AND p.flag_segera = '1'";
		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
			if(userIdNegeri.equals("14")){
				sql += "AND f.id_negeri in (14,15,16) ";
			}else{
				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
			}		
		}
		
		
	
	return sql;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static Vector getListPermohonan(String userIdNegeri)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		/*
		    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		    		sql +=" p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		    		sql +=" FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +=" AND p.id_status = s.id_status ";
		    		sql +=" AND f.id_suburusan = '52' ";
		    		sql +=" AND p.id_status IN (38,58,76,74)";
		    		//sql +=" AND p.flag_segera = '1'";
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		    		*/
		    		sql = sqlListPermohonan(userIdNegeri);
		    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);
		    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
		    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
				    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
		    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
		    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
		    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
		    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
		    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
		    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
		    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
		    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
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
		    	
	}//close getListPermohonan
	
	@SuppressWarnings("unchecked")
	public static void setListCarian(String carianNofail, String carianTarikh, String status, String userIdNegeri)throws Exception {
		
		listcarian = new Vector();
		
	    Db db = null;
	    listcarian.clear();
	    String sql = "";
	    
	    String cariTarikh = "";
	    if(carianTarikh!=""){
	    	cariTarikh = "to_date('" + carianTarikh + "','dd/MM/yyyy')";
	    }	    
	    
	    String nofail = carianNofail.trim();
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		/*
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, p.no_rujukan_surat, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
	    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '52' ";
	    		sql +=" AND p.id_status IN (38,58,76,74)";
	    		//sql +=" AND p.flag_segera = '1'";
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
	    		*/
	    		
	    		sql = sqlListPermohonan(userIdNegeri);
	    		
	    		//default flag
				boolean setLimit = true;  
				
	    		//NO FAIL
				if (carianNofail != "" && carianNofail != null) {
					if (!nofail.equals("")) {
						setLimit = false;
						//sql = sql + " AND UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%'";
						sql += " AND (UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_ptg) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_upt) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_ptd) LIKE '%" + nofail.toUpperCase() + "%')";
					}
				}//close carian by nofail
		
				//TARIKH
				if (carianTarikh != "" && carianTarikh != null) {
					if (!cariTarikh.equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(p.tarikh_permohonan) = "+cariTarikh;
					}
				}//close carian by tarikh
	
	    		//STATUS
				if (status != "" && status != null) {
					setLimit = false;
					sql = sql + " AND UPPER(s.id_status) = '"+status+"'";
				}//close carian by status

				if(setLimit){	
					sql += " AND ROWNUM <= 10 ";				
				}	
				
	    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	      
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;
	    		int bil = 1;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
	    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
			    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
	    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
	    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
	    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
	    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
	    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
	    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
	    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
	    			if(rs.getString("no_fail") == null){
	    				h.put("no_fail","Belum Diluluskan");
	    			}else{
	    				h.put("no_fail",rs.getString("no_fail"));
	    			}
	    			listcarian.addElement(h);
	    			bil++;
	    			
	      }//close while
	      
	    }// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close carian
	
	
	@SuppressWarnings("unchecked")
	public static void simpanPenandaan(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	
	    		//String txtStatusTanda = (String)data.get("txtStatusTanda");
	    		
	    		String socStatusTanda = (String)data.get("socStatusTanda");
	    		String socStatusLaksana = (String)data.get("socStatusLaksana");
	    		String txtNoRujAgensi = (String)data.get("txtNoRujAgensi");
	    		String txtNamaPegawai = (String)data.get("txtNamaPegawai");
	    		String txdTarikhTandaDari = (String)data.get("txdTarikhTandaDari");
	    		String txdTarikhTandaHingga = (String)data.get("txdTarikhTandaHingga");
	    		String txdTarikhLawat = (String)data.get("txdTarikhLawat");
	    		String txdTarikhLulus = (String)data.get("txdTarikhLulus");
	    		String txtAlamat1 = (String)data.get("txtAlamat1");	    	
	    		String txtAlamat2 = (String)data.get("txtAlamat2");	 
	    		String txtAlamat3 = (String)data.get("txtAlamat3");	    	
	    		String txtPoskod = (String)data.get("txtPoskod");	    	
	    		String socNegeri = (String)data.get("socNegeri");	 
	    		String socBandar = (String)data.get("socBandar");
	    		
	    		
	    		String TLL = "to_date('" + txdTarikhLulus + "','dd/MM/yyyy')";
	    		String TD = "to_date('" + txdTarikhTandaDari + "','dd/MM/yyyy')";
	    		String TH = "to_date('" + txdTarikhTandaHingga + "','dd/MM/yyyy')";
	    		String TL = "to_date('" + txdTarikhLawat + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_permohonan", id_permohonan);
	    		//r.add("status_tanda", txtStatusTanda);
	    		r.add("flag_tanda", socStatusTanda);
	    		r.add("cara_laksana", socStatusLaksana);
	    		r.add("tarikh_mula",r.unquote(TD));
	    		r.add("tarikh_akhir",r.unquote(TH));
	    		r.add("tarikh_lawat",r.unquote(TL));   
	    		r.add("tarikh_lulus",r.unquote(TLL));    
	    		r.add("no_rujagensi", txtNoRujAgensi);
	    		r.add("nama_pegawai", txtNamaPegawai);
	    		r.add("alamat1_juruukur", txtAlamat1);
	    		r.add("alamat2_juruukur", txtAlamat2);
	    		r.add("alamat3_juruukur", txtAlamat3);	  
	    		r.add("poskod", txtPoskod);
	    		r.add("id_negeri", socNegeri);
	    		r.add("id_bandar", socBandar);	
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("tblppttandakawasan");
	    		myLogger.info("SQL INSERT tblppttandakawasan :: "+sql);
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanPenandaan
	
	@SuppressWarnings("unchecked")
	public static void updatePenandaan(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_tandakawasan = (String)data.get("id_tandakawasan");
	    		
	    		//String txtStatusTanda = (String)data.get("txtStatusTanda");
	    		String socStatusTanda = (String)data.get("socStatusTanda");
	    		String socStatusLaksana = (String)data.get("socStatusLaksana");
	    		String txtNoRujAgensi = (String)data.get("txtNoRujAgensi");
	    		String txtNamaPegawai = (String)data.get("txtNamaPegawai");
	    		String txdTarikhTandaDari = (String)data.get("txdTarikhTandaDari");
	    		String txdTarikhTandaHingga = (String)data.get("txdTarikhTandaHingga");
	    		String txdTarikhLawat = (String)data.get("txdTarikhLawat");
	    		String txdTarikhLulus = (String)data.get("txdTarikhLulus");
	    		String txtAlamat1 = (String)data.get("txtAlamat1");	    	
	    		String txtAlamat2 = (String)data.get("txtAlamat2");	 
	    		String txtAlamat3 = (String)data.get("txtAlamat3");
	    		String txtPoskod = (String)data.get("txtPoskod");	    	
	    		String socNegeri = (String)data.get("socNegeri");	 
	    		String socBandar = (String)data.get("socBandar");
	    		
	    		
	    		String TLL = "to_date('" + txdTarikhLulus + "','dd/MM/yyyy')";
	    		String TD = "to_date('" + txdTarikhTandaDari + "','dd/MM/yyyy')";
	    		String TH = "to_date('" + txdTarikhTandaHingga + "','dd/MM/yyyy')";
	    		String TL = "to_date('" + txdTarikhLawat + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_tandakawasan", id_tandakawasan);
	    		//r.add("status_tanda", txtStatusTanda);
	    		r.add("flag_tanda", socStatusTanda);
	    		r.add("cara_laksana", socStatusLaksana);
	    		r.add("tarikh_mula",r.unquote(TD));
	    		r.add("tarikh_akhir",r.unquote(TH));
	    		r.add("tarikh_lawat",r.unquote(TL));    
	    		r.add("tarikh_lulus",r.unquote(TLL));  
	    		r.add("no_rujagensi", txtNoRujAgensi);
	    		r.add("nama_pegawai", txtNamaPegawai);
	    		r.add("alamat1_juruukur", txtAlamat1);
	    		r.add("alamat2_juruukur", txtAlamat2);
	    		r.add("alamat3_juruukur", txtAlamat3);	 
	    		r.add("poskod", txtPoskod);
	    		r.add("id_negeri", socNegeri);
	    		r.add("id_bandar", socBandar);	
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblppttandakawasan");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updatePenandaan
	
	
	@SuppressWarnings("unchecked")
	public void setDataKawasanPenandaan(String idpermohonan) throws Exception{
		
		dataKawasanPenandaan = new Vector();
		
		Db db = null;
		dataKawasanPenandaan.clear();
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT id_tandakawasan,tarikh_mula,tarikh_akhir,nama_pegawai,alamat1_juruukur,tarikh_lulus, "; 
				sql += " alamat2_juruukur,alamat3_juruukur,no_rujagensi,tarikh_lawat,cara_laksana,flag_tanda,poskod,id_negeri,id_bandar ";
				sql += " FROM tblppttandakawasan ";
				sql += " WHERE id_permohonan = '"+idpermohonan+"'";
				myLogger.info("SQL :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;

				while (rs.next()){
					h = new Hashtable();
					h.put("id_tandakawasan", rs.getString("id_tandakawasan")==null?"":rs.getString("id_tandakawasan"));
					h.put("cara_laksana", rs.getString("cara_laksana")==null?"":rs.getString("cara_laksana"));
					//h.put("status_tanda", rs.getString("status_tanda")==null?"":rs.getString("status_tanda"));
					h.put("flag_tanda", rs.getString("flag_tanda")==null?"":rs.getString("flag_tanda"));
					h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
					h.put("alamat1_juruukur", rs.getString("alamat1_juruukur")==null?"":rs.getString("alamat1_juruukur"));			
					h.put("alamat2_juruukur", rs.getString("alamat2_juruukur")==null?"":rs.getString("alamat2_juruukur"));
					h.put("alamat3_juruukur", rs.getString("alamat3_juruukur")==null?"":rs.getString("alamat3_juruukur"));
					h.put("no_rujagensi", rs.getString("no_rujagensi")==null?"":rs.getString("no_rujagensi"));
					h.put("tarikh_mula", rs.getDate("tarikh_mula")==null?"":Format.format(rs.getDate("tarikh_mula")));
					h.put("tarikh_akhir", rs.getDate("tarikh_akhir")==null?"":Format.format(rs.getDate("tarikh_akhir")));
					h.put("tarikh_lawat", rs.getDate("tarikh_lawat")==null?"":Format.format(rs.getDate("tarikh_lawat")));
					h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));		
					h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));	
					h.put("id_bandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));	
					h.put("tarikh_lulus", rs.getDate("tarikh_lulus")==null?"":Format.format(rs.getDate("tarikh_lulus")));
					dataKawasanPenandaan.addElement(h);
				
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}//close setDataKawasanPenandaan
	
	
	@SuppressWarnings("unchecked")
	public static void updateStatus(Hashtable data) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		//status tanda kawasan
	    		String status = "46";
	    		//status 46
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", id_permohonan);	   
	    		r.add("id_status",status);  
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close updateStatus
	
	@SuppressWarnings("unchecked")
	public static void updateStatusPenandaan(Hashtable data) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		//status tanda kawasan
	    		String status = "38";
	    		//status 46
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", id_permohonan);	   
	    		r.add("id_status",status);  
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close updateStatusPenandaan
	
	@SuppressWarnings("unchecked")
	public void setListMaklumatTanah(String idPermohonan,String lot,String idpegawai) throws Exception{
			
		 listMaklumatTanah = new Vector();
			
			Db db = null;
			listMaklumatTanah.clear();
			String sql = "";
			String noLOT = lot.trim();
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql = "SELECT DISTINCT m.id_hakmilik, mk.nama_mukim, m.luas_ambil,";
					sql += " m.no_hakmilik, e.nama_pegawai, m.no_subjaket, m.id_pegawai, m.id_unitluasambil_convert,";
					sql += " CASE ";
					sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot "; 
					sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
					sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";       
					sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
					sql += " ELSE '' ";
					sql += " END AS NO_LOTPT ";  
					sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
					sql += " Tblrujdaerah d, Tblrujpegawai e, Tblpptborange g, Tblpptnotisawam h";
					sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
					sql += " AND m.id_negeri = n.id_negeri "; 
					sql += " AND g.id_hakmilik = m.id_hakmilik";
					sql += " AND h.id_hakmilik = m.id_hakmilik";
					sql += " AND p.id_fail = f.id_fail "; 
					sql += " AND p.id_daerah = d.id_daerah(+)";
					sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
					sql += " AND m.id_pegawai = e.id_pegawai(+)";
					sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
					sql += " AND m.id_lot = lt.id_lot(+) ";
					sql += " AND m.id_mukim = mk.id_mukim(+) ";  
					sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
					sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
					sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
					
					//NO LOT
					if (lot != "" && lot != null) {
						if (!noLOT.equals("")) {
							sql += " AND (UPPER(m.no_lot) LIKE '%" + noLOT.toUpperCase() + "%' OR  UPPER(m.no_pt) LIKE '%" + noLOT.toUpperCase() + "%' OR UPPER(lt.keterangan) LIKE '%" + noLOT.toUpperCase() + "%')";
						}
					}//close if nolot
					
					//ID PEGAWAI
					if (idpegawai != "" && idpegawai != null) {
							sql += " AND m.id_pegawai = '"+idpegawai+"'";
					}//close if idpegawai
					
					
					sql += " ORDER BY NO_LOTPT asc, mk.nama_mukim asc";
					
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
					int bil = 1;
					
					while (rs.next()){
						h = new Hashtable();
						h.put("bil", bil);
						h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
						h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
						h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
						h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
						h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
						
						if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
							
							double luasAmbil = rs.getDouble("luas_ambil");
							String LA = Utils.formatLuasHM(luasAmbil);
							h.put("luas_ambil",LA);
									
						}else{
							h.put("luas_ambil","0");
						}
						
						if(rs.getString("id_unitluasambil_convert") != null){
							
							if(rs.getString("id_unitluasambil_convert").equals("1")){
								h.put("unitByKategori", "Hektar");
							}else{
								h.put("unitByKategori", "Meter Persegi");
							}			
						}else{
							h.put("unitByKategori", "");
						}
			
						listMaklumatTanah.addElement(h);
						bil++;	
						
					}
				//return list;
			}finally {
				if(db != null) db.close();
			}
		}//close setListMaklumatTanah
	
	//penambahan function-yati
	public static Vector listEmel = null;
			
	public static Vector getListEmel() {
	return listEmel;
	}
			
	//get detail user
	@SuppressWarnings("unchecked")
	public static Vector setListEmel(String id_permohonan) throws Exception {
				
		Vector listEmel = new Vector();
					
			Db db = null;
			String sql = "";
					
			try {
			db = new Db();
			Statement stmt = db.getStatement();
						
			sql =  "SELECT  PF.ID_KEMENTERIAN, TP.ID_PERMOHONAN, TP.ID_FAIL, TK.EMEL " +
					"FROM TBLPPTPERMOHONAN TP, TBLPFDFAIL PF, TBLRUJKEMENTERIAN TK " +
					"WHERE PF.ID_KEMENTERIAN = TK.ID_KEMENTERIAN " +
					"AND PF.ID_FAIL = TP.ID_FAIL " +
					"AND TP.ID_PERMOHONAN = '"+id_permohonan+"' ";
				//myLogger.info("GET EMEL : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;
			while (rs.next()) {
				h = new Hashtable();
				h.put("id_permohonan", rs.getString("ID_PERMOHONAN")== null?"":rs.getString("ID_PERMOHONAN"));
				h.put("id_kementerian", rs.getString("ID_KEMENTERIAN")== null?"":rs.getString("ID_KEMENTERIAN"));
				h.put("id_fail", rs.getString("ID_FAIL")== null?"":rs.getString("ID_FAIL"));
				h.put("emel", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
							
				listEmel.addElement(h);
								
				}
			return listEmel;
				}catch (Exception re) {
			throw re;
			} finally {
			if (db != null)
				db.close();
				}
				}//close setEmel
	
}//close class
