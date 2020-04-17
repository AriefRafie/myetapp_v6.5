package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmSek4LaporanAwalTanahData {
	static Logger myLogger = Logger.getLogger(FrmSek4LaporanAwalTanahData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static  Vector listcarian = null;
	private static  Vector dataLaporanTanah = null;
	
	public static  Vector getListCarian(){
		return listcarian;
	}

	public static  Vector getDataLaporanTanah(){
		return dataLaporanTanah;
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getListPermohonan(String userIdNegeri)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		    		sql += "p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +="AND p.id_status = s.id_status ";
		    		sql +="AND f.id_suburusan = '51' ";
		    		sql +="AND p.id_status IN (147,148)";
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		    		sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";

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
	      
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, p.no_rujukan_surat, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
	    		sql += "p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '51' ";
	    		sql +="AND p.id_status IN (147,148)";
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
	    		
	    		//default flag
				boolean setLimit = true;  
				
	    		//NO FAIL
				if (carianNofail != "" && carianNofail != null) {
					if (!nofail.equals("")) {
						setLimit = false;
						//sql = sql + " AND UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%'";
						sql += " AND (UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_ptg) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_ptg) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_upt) LIKE '%" + nofail.toUpperCase() + "%')";
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
	public static void simpanLaporan(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		String txtNoPeta = (String)data.get("txtNoPeta");
	    		String txtLaporanTanah = (String)data.get("txtLaporanTanah");

	    		String txtNoSyit = (String)data.get("txtNoSyit");	 
	    		String txtBilKeseluruhan = (String)data.get("txtBilKeseluruhan");
	    		String socKategoriTanah = (String)data.get("socKategoriTanah");
	    		String txtLuasKeseluruhan = (String)data.get("txtLuasKeseluruhan");
	    		String txdTarikhMula = (String)data.get("txdTarikhMula");
	    		String txdTarikhTamat = (String)data.get("txdTarikhTamat");
	    		String txdTarikhLawat = (String)data.get("txdTarikhLawat");
	    		
	    		String txtKawasanMajlis = (String)data.get("txtKawasanMajlis");
	    		String txtSimpanMelayu = (String)data.get("txtSimpanMelayu");
	    		String txtLainlain = (String)data.get("txtLainlain");
	    		String txtUlasan = (String)data.get("txtUlasan");
	    		String txtKemudahan = (String)data.get("txtKemudahan");	    		
	    		String txtRupabumi = (String)data.get("txtRupabumi");
	    		String txtPerihal = (String)data.get("txtPerihal");
	    		String txtLokasi = (String)data.get("txtLokasi");
	    		String txtPendahuluan = (String)data.get("txtPendahuluan");
	    		
	    		String id_unitluas = (String)data.get("sorDropdownUnitAsal");
	    		
	    		String TM = "to_date('" + txdTarikhMula + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTamat + "','dd/MM/yyyy')";
	    		String TL = "to_date('" + txdTarikhLawat + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_permohonan", id_permohonan);
	    		r.add("perihal_syit",txtNoSyit);    	
	    		r.add("id_unitluas", id_unitluas);
	    		r.add("laporan_tanah",txtLaporanTanah);    
	    		r.add("no_peta_kadaster",txtNoPeta);   
	    		r.add("id_jenis_tanah",socKategoriTanah);    
	    		r.add("pendahuluan",txtPendahuluan);	
	    		r.add("bil_hakmilik",txtBilKeseluruhan);    		
	    		r.add("luas_terlibat",txtLuasKeseluruhan);	
	    		r.add("tarikh_mula_chart",r.unquote(TM));
	    		r.add("tarikh_akhir_chart",r.unquote(TT));
	    		r.add("tarikh_lawatan",r.unquote(TL));	    		
	    		r.add("perihal_kawasan_majlis",txtKawasanMajlis);
				r.add("perihal_kawasan_simpan",txtSimpanMelayu);
	    		r.add("perihal_kawasan_lain2",txtLainlain);
	    		r.add("ulasan_syor",txtUlasan);
	    		r.add("kemudahan_awam",txtKemudahan);
	    		r.add("keadaan_rupabumi",txtRupabumi);
	    		r.add("perihal_bangunan",txtPerihal);	    		
	    		r.add("lokasi_tanah",txtLokasi);	    			    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("tblppttanahumum");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanLaporan
	
	
	@SuppressWarnings("unchecked")
	public static void updateStatus(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		String statusLaporanTanah = "147";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", id_permohonan);
	    		r.add("id_status",statusLaporanTanah);
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
	public static void setDataLaporanTanah(String idpermohonan)throws Exception {
		
		dataLaporanTanah = new Vector();
		
	    Db db = null;
	    dataLaporanTanah.clear();
	    String sql = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT DISTINCT id_tanahumum,id_permohonan,perihal_syit,id_jenis_tanah,bil_hakmilik,luas_terlibat,id_unitluas, ";
	    		sql += " tarikh_mula_chart,tarikh_akhir_chart,tarikh_lawatan,perihal_kawasan_majlis,perihal_kawasan_simpan,perihal_kawasan_lain2, ";
	    		sql += " ulasan_syor,kemudahan_awam,keadaan_rupabumi,perihal_bangunan,lokasi_tanah,no_peta_kadaster,laporan_tanah,pendahuluan,id_unitluas "; 
	    		sql += " FROM tblppttanahumum ";
	    		sql += " WHERE id_permohonan = '"+idpermohonan+"'";
	    		
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			h.put("id_unitluas", rs.getString("id_unitluas")== null?"":rs.getString("id_unitluas"));
	    			h.put("pendahuluan", rs.getString("pendahuluan")== null?"":rs.getString("pendahuluan"));
	    			h.put("id_tanahumum", rs.getString("id_tanahumum")== null?"":rs.getString("id_tanahumum"));
	    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
	    			h.put("no_peta_kadaster", rs.getString("no_peta_kadaster")== null?"":rs.getString("no_peta_kadaster"));
	    			h.put("laporan_tanah", rs.getString("laporan_tanah")== null?"":rs.getString("laporan_tanah"));
	    			h.put("tarikh_mula_chart", rs.getDate("tarikh_mula_chart")==null?"":Format.format(rs.getDate("tarikh_mula_chart")));
	    			h.put("tarikh_akhir_chart", rs.getDate("tarikh_akhir_chart")==null?"":Format.format(rs.getDate("tarikh_akhir_chart")));
	    			h.put("tarikh_lawatan", rs.getDate("tarikh_lawatan")==null?"":Format.format(rs.getDate("tarikh_lawatan")));	   	    				    			
	    			h.put("perihal_syit", rs.getString("perihal_syit")== null?"":rs.getString("perihal_syit"));
	    			h.put("id_jenis_tanah", rs.getString("id_jenis_tanah")== null?"":rs.getString("id_jenis_tanah"));
	    			h.put("bil_hakmilik", rs.getString("bil_hakmilik")== null?"":rs.getString("bil_hakmilik"));
	    			h.put("luas_terlibat", rs.getString("luas_terlibat")== null?"":rs.getString("luas_terlibat"));
	    			h.put("id_unitluas", rs.getString("id_unitluas")== null?"":rs.getString("id_unitluas"));
	    			h.put("perihal_kawasan_majlis", rs.getString("perihal_kawasan_majlis")== null?"":rs.getString("perihal_kawasan_majlis"));
	    			h.put("perihal_kawasan_simpan", rs.getString("perihal_kawasan_simpan")== null?"":rs.getString("perihal_kawasan_simpan"));
	    			h.put("perihal_kawasan_lain2", rs.getString("perihal_kawasan_lain2")== null?"":rs.getString("perihal_kawasan_lain2"));    			
	    			h.put("ulasan_syor", rs.getString("ulasan_syor")== null?"":rs.getString("ulasan_syor"));
	    			h.put("kemudahan_awam", rs.getString("kemudahan_awam")== null?"":rs.getString("kemudahan_awam"));
	    			h.put("keadaan_rupabumi", rs.getString("keadaan_rupabumi")== null?"":rs.getString("keadaan_rupabumi"));
	    			h.put("perihal_bangunan", rs.getString("perihal_bangunan")== null?"":rs.getString("perihal_bangunan"));
	    			h.put("lokasi_tanah", rs.getString("lokasi_tanah")== null?"":rs.getString("lokasi_tanah"));	    		
	    			
	    			dataLaporanTanah.addElement(h);
	    		
	      }//close while
	      
	    }// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close setDataLaporanTanah
	
	@SuppressWarnings("unchecked")
	public static void updateLaporan(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_tanahumum = (String)data.get("id_tanahumum");
	    		
	    		String txtNoPeta = (String)data.get("txtNoPeta");	
	    		String txtLaporanTanah = (String)data.get("txtLaporanTanah");	

	    		String txtNoSyit = (String)data.get("txtNoSyit");	 
	    		String txtBilKeseluruhan = (String)data.get("txtBilKeseluruhan");
	    		String socKategoriTanah = (String)data.get("socKategoriTanah");
	    		String txtLuasKeseluruhan = (String)data.get("txtLuasKeseluruhan");
	    		String txdTarikhMula = (String)data.get("txdTarikhMula");
	    		String txdTarikhTamat = (String)data.get("txdTarikhTamat");
	    		String txdTarikhLawat = (String)data.get("txdTarikhLawat");
	    		
	    		String txtKawasanMajlis = (String)data.get("txtKawasanMajlis");
	    		String txtSimpanMelayu = (String)data.get("txtSimpanMelayu");
	    		String txtLainlain = (String)data.get("txtLainlain");
	    		String txtUlasan = (String)data.get("txtUlasan");
	    		String txtKemudahan = (String)data.get("txtKemudahan");	    		
	    		String txtRupabumi = (String)data.get("txtRupabumi");
	    		String txtPerihal = (String)data.get("txtPerihal");
	    		String txtLokasi = (String)data.get("txtLokasi");
	    		String txtPendahuluan = (String)data.get("txtPendahuluan");
	    		
	    		String id_unitluas = (String)data.get("sorDropdownUnitAsal");
	    		
	    		String TM = "to_date('" + txdTarikhMula + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTamat + "','dd/MM/yyyy')";
	    		String TL = "to_date('" + txdTarikhLawat + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_tanahumum", id_tanahumum);
	    		r.add("perihal_syit",txtNoSyit);    	
	    		r.add("laporan_tanah",txtLaporanTanah);    
	    		r.add("id_unitluas", id_unitluas);
	    		r.add("no_peta_kadaster",txtNoPeta);    
	    		r.add("id_jenis_tanah",socKategoriTanah);    	
	    		r.add("bil_hakmilik",txtBilKeseluruhan);    
	    		r.add("pendahuluan",txtPendahuluan);	
	    		r.add("luas_terlibat",txtLuasKeseluruhan);
	    		r.add("tarikh_mula_chart",r.unquote(TM));
	    		r.add("tarikh_akhir_chart",r.unquote(TT));
	    		r.add("tarikh_lawatan",r.unquote(TL));	    		
	    		r.add("perihal_kawasan_majlis",txtKawasanMajlis);
				r.add("perihal_kawasan_simpan",txtSimpanMelayu);
	    		r.add("perihal_kawasan_lain2",txtLainlain);
	    		r.add("ulasan_syor",txtUlasan);
	    		r.add("kemudahan_awam",txtKemudahan);
	    		r.add("keadaan_rupabumi",txtRupabumi);
	    		r.add("perihal_bangunan",txtPerihal);	    		
	    		r.add("lokasi_tanah",txtLokasi);	    
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblppttanahumum");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateLaporan
	
}//close class
