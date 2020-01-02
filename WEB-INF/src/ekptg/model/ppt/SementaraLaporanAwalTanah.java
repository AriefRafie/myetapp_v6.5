package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class SementaraLaporanAwalTanah {
	static Logger myLogger = Logger.getLogger(SementaraLaporanAwalTanah.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static  Vector listcarian = null;
	private static  Vector dataLaporanTanah = null;
	
	public static  Vector getListCarian(String userIdNegeri){
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
	     
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk ";
	    		sql +=", p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '53' ";
	    		sql +="AND p.id_status IN (147,148)";
	    		
	    		// ADDED BY ELLY 14 JUNE 2010
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
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
	      
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, p.no_rujukan_surat, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk ";
	    		sql +=",p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '53' ";
	    		sql +="AND p.id_status IN (147,148)";
	    		
	    		// ADDED BY ELLY 14 JUNE 2010
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    		}		    		
	     
	    		//NO FAIL
				if (carianNofail != "" && carianNofail != null) {
					if (!nofail.equals("")) {
						//setLimit = false;
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
						sql = sql + " AND UPPER(p.tarikh_permohonan) = "+cariTarikh;
					}
				}//close carian by tarikh
	
	    		//STATUS
				if (status != "" && status != null) {
						sql = sql + " AND UPPER(s.id_status) = '"+status+"'";
				}//close carian by status
	
	    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	    		myLogger.info("SQL carian :: "+sql);
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
	

	public static void simpanLaporan(Hashtable data) throws Exception
	  {		
	    Db db = null;
	    String sql = "";
	    
	    try{	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		String id_pegawai = (String)data.get("id_pegawai");
	    		String txdTarikhMula = (String)data.get("txdTarikhMula");
	    		String txdTarikhTamat = (String)data.get("txdTarikhTamat");
	    		String txdTarikhLawat = (String)data.get("txdTarikhLawat");
	    		String txtBilKeseluruhan = (String)data.get("txtBilKeseluruhan");
	    		String txtLuasKeseluruhan = (String)data.get("txtLuasKeseluruhan");
//	    		String unitLuas = (String)data.get("unitLuas");
	    		String id_unitluas = (String)data.get("sorDropdownUnitAsal");
	    		String txtKosTanah = (String)data.get("txtKosTanah");
	    		String txtKosBangunan = (String)data.get("txtKosBangunan");
	    		String txtPerihal = (String)data.get("txtPerihal");	    
	    		
	    		String sbcLuarKwsnSmpnMelayu = (String)data.get("sbcLuarKwsnSmpnMelayu");
	    		String sbcDlmKwsnSmpnMelayu = (String)data.get("sbcDlmKwsnSmpnMelayu");
	    		String sbcLuarKwsnMajlisDaerah = (String)data.get("sbcLuarKwsnMajlisDaerah");
	    		String sbcDlmKwsnMajlisDaerah = (String)data.get("sbcDlmKwsnMajlisDaerah");
	    		String txtLokasi = (String)data.get("txtLokasi");
	    		String sorJenisTanah = (String)data.get("sorJenisTanah");	    		
	    		
	    		String txtRupabumiSeluruhLot = (String)data.get("txtRupabumiSeluruhLot");	    		
	    		String txtRupabumiKwsTerlibat = (String)data.get("txtRupabumiKwsTerlibat");
	    		
	    		String txtKeadaanLotSeluruh = (String)data.get("txtKeadaanLotSeluruh");	    		
	    		String txtKeadaanLotTanaman = (String)data.get("txtKeadaanLotTanaman");	    		
	    		
	    		String txtKeadaanLotJnsTanaman = (String)data.get("txtKeadaanLotJnsTanaman");	    		
	    		String txtKeadaanLotKwsTerlibat = (String)data.get("txtKeadaanLotKwsTerlibat");	    		
	    		
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		String txdTarikhLapor = (String)data.get("txdTarikhLapor");
	    			    			    	
	    		String TM = "to_date('" + txdTarikhMula + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTamat + "','dd/MM/yyyy')";
	    		String TL = "to_date('" + txdTarikhLawat + "','dd/MM/yyyy')";
	    		String TLapor = "to_date('" + txdTarikhLapor + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_permohonan", id_permohonan);
	    		r.add("id_pegawai", id_pegawai);
	    		r.add("bil_hakmilik",txtBilKeseluruhan);    		
	    		r.add("luas_terlibat",txtLuasKeseluruhan);
	    		r.add("id_unitluas",id_unitluas);
	    		r.add("harga_anggar",txtKosTanah);
	    		r.add("harga_anggar_bangunan",txtKosBangunan);
	    		r.add("perihal_bangunan",txtPerihal);	    
	    		r.add("flag_jenis_tanah",sorJenisTanah);
	    		r.add("rupabumi_seluruh_lot",txtRupabumiSeluruhLot );
	    		r.add("rupabumi_kwsn_terlibat", txtRupabumiKwsTerlibat);
	    		r.add("lot_seluruh_lot", txtKeadaanLotSeluruh);
	    		r.add("lot_keadaan_tanaman", txtKeadaanLotTanaman);
	    		r.add("lot_jenis_tanaman", txtKeadaanLotJnsTanaman);
	    		r.add("lot_kwsn_terlibat", txtKeadaanLotKwsTerlibat);
	    		r.add("lokasi_tanah",txtLokasi);	    		
	    		r.add("id_hakmilik",id_hakmilik);
	    		r.add("flag_luar_simpanan",sbcLuarKwsnSmpnMelayu);
	    		r.add("flag_dlm_simpanan",sbcDlmKwsnSmpnMelayu);
	    		r.add("flag_luar_majlis",sbcLuarKwsnMajlisDaerah);
	    		r.add("flag_dlm_majlis",sbcDlmKwsnMajlisDaerah);
	    		r.add("tarikh_mula_chart",r.unquote(TM));
	    		r.add("tarikh_akhir_chart",r.unquote(TT));
	    		r.add("tarikh_lawatan",r.unquote(TL));
	    		r.add("tarikh_lapor",r.unquote(TLapor));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("tblppttanahumum");
	    		myLogger.info("SQL INSERT LAPORAN TNH :: "+sql);
	    		stmt.executeUpdate(sql);

	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanLaporan

	public static void updateLaporan(Hashtable data) throws Exception
	  {		
	    Db db = null;
	    String sql = "";
	    
	    try{	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_tanahumum = (String)data.get("id_tanahumum");
	    		
	    		String txdTarikhMula = (String)data.get("txdTarikhMula");
	    		String txdTarikhTamat = (String)data.get("txdTarikhTamat");
	    		String txdTarikhLawat = (String)data.get("txdTarikhLawat");
	    		String txtBilKeseluruhan = (String)data.get("txtBilKeseluruhan");
	    		String txtLuasKeseluruhan = (String)data.get("txtLuasKeseluruhan");
//	    		String unitLuas = (String)data.get("unitLuas");
	    		String id_unitluas = (String)data.get("sorDropdownUnitAsal");
	    		String txtKosTanah = (String)data.get("txtKosTanah");
	    		String txtKosBangunan = (String)data.get("txtKosBangunan");
	    		String txtPerihal = (String)data.get("txtPerihal");	
	    			    		
	    		String sbcLuarKwsnSmpnMelayu = (String)data.get("sbcLuarKwsnSmpnMelayu");
	    		String sbcDlmKwsnSmpnMelayu = (String)data.get("sbcDlmKwsnSmpnMelayu");
	    		String sbcLuarKwsnMajlisDaerah = (String)data.get("sbcLuarKwsnMajlisDaerah");
	    		String sbcDlmKwsnMajlisDaerah = (String)data.get("sbcDlmKwsnMajlisDaerah");
	    		String txtLokasi = (String)data.get("txtLokasi");
	    		String sorJenisTanah = (String)data.get("sorJenisTanah");	    		
	    		
	    		String txtRupabumiSeluruhLot = (String)data.get("txtRupabumiSeluruhLot");	    		
	    		String txtRupabumiKwsTerlibat = (String)data.get("txtRupabumiKwsTerlibat");
	    		
	    		String txtKeadaanLotSeluruh = (String)data.get("txtKeadaanLotSeluruh");	    		
	    		String txtKeadaanLotTanaman = (String)data.get("txtKeadaanLotTanaman");	    		
	    		
	    		String txtKeadaanLotJnsTanaman = (String)data.get("txtKeadaanLotJnsTanaman");	    		
	    		String txtKeadaanLotKwsTerlibat = (String)data.get("txtKeadaanLotKwsTerlibat");	  	    		
	    		
	    		String txdTarikhLapor = (String)data.get("txdTarikhLapor");
	    		String TM = "to_date('" + txdTarikhMula + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTamat + "','dd/MM/yyyy')";
	    		String TL = "to_date('" + txdTarikhLawat + "','dd/MM/yyyy')";
	    		String TLapor = "to_date('" + txdTarikhLapor + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		
	    		r.update("id_tanahumum",id_tanahumum);
	    		
//	    		r.add("bil_hakmilik",txtBilKeseluruhan);    		
//	    		r.add("luas_terlibat",txtLuasKeseluruhan);
//	    		r.add("id_unitluas",unitLuas);
//	    		r.add("harga_anggar",txtKosTanah);
//	    		r.add("harga_anggar_bangunan",txtKosBangunan);
//	    		r.add("perihal_bangunan",txtPerihal);	 
//	    		r.add("flag_jenis_tanah",sorJenisTanah);
//	    		r.add("rupabumi_seluruh_lot",txtRupabumiSeluruhLot );
//	    		r.add("rupabumi_kwsn_terlibat",txtRupabumiKwsTerlibat);
//	    		r.add("lot_seluruh_lot",txtKeadaanLotSeluruh);
//	    		r.add("lot_keadaan_tanaman",txtKeadaanLotTanaman);
//	    		r.add("lot_jenis_tanaman",txtKeadaanLotJnsTanaman);
//	    		r.add("lot_kwsn_terlibat",txtKeadaanLotKwsTerlibat);	    		
//	    		r.add("lokasi_tanah",txtLokasi);	    		
//	    		r.add("flag_luar_simpanan",sbcLuarKwsnSmpnMelayu);
//	    		r.add("flag_dlm_simpanan",sbcDlmKwsnSmpnMelayu);
//	    		r.add("flag_luar_majlis",sbcLuarKwsnMajlisDaerah);
//	    		r.add("flag_dlm_majlis",sbcDlmKwsnMajlisDaerah);	
//	    		r.add("rupabumi_kwsn_terlibat",txtRupabumiKwsTerlibat);	    		
//	    		r.add("tarikh_mula_chart",r.unquote(TM));
//	    		r.add("tarikh_akhir_chart",r.unquote(TT));
//	    		r.add("tarikh_lawatan",r.unquote(TL));
//	    		r.add("tarikh_lapor",r.unquote(TLapor));
	    		
//	    		r.add("no_peta_kadaster", txtNoPeta);
	    		r.add("bil_hakmilik",txtBilKeseluruhan);    		
	    		r.add("luas_terlibat",txtLuasKeseluruhan);
	    		r.add("id_unitluas",id_unitluas);
	    		r.add("harga_anggar",txtKosTanah);
	    		r.add("harga_anggar_bangunan",txtKosBangunan);
	    		r.add("perihal_bangunan",txtPerihal);	    
	    		r.add("flag_jenis_tanah",sorJenisTanah);
	    		r.add("rupabumi_seluruh_lot",txtRupabumiSeluruhLot );
	    		r.add("rupabumi_kwsn_terlibat", txtRupabumiKwsTerlibat);
	    		r.add("lot_seluruh_lot", txtKeadaanLotSeluruh);
	    		r.add("lot_keadaan_tanaman", txtKeadaanLotTanaman);
	    		r.add("lot_jenis_tanaman", txtKeadaanLotJnsTanaman);
	    		r.add("lot_kwsn_terlibat", txtKeadaanLotKwsTerlibat);
//	    		r.add("perihal_tm_sendiri",txtTanahSendiri);
//	    		r.add("perihal_tr_negeri",txtTanahNegeri);
//	    		r.add("perihal_tmtr_sekutuan",txtTanahPersekutuan);
//	    		r.add("keadaan_rupabumi",txtKeadaanTanah);
	    		r.add("lokasi_tanah",txtLokasi);	    		
//	    		r.add("perihal_kawasan_simpan",txtTanahMelayu);
//	    		r.add("perihal_tnh_kerajaan",txtTanahKerajaan);
//	    		r.add("ulasan_syor",txtSyor);
//	    		r.add("id_hakmilik",id_hakmilik);
	    		r.add("flag_luar_simpanan",sbcLuarKwsnSmpnMelayu);
	    		r.add("flag_dlm_simpanan",sbcDlmKwsnSmpnMelayu);
	    		r.add("flag_luar_majlis",sbcLuarKwsnMajlisDaerah);
	    		r.add("flag_dlm_majlis",sbcDlmKwsnMajlisDaerah);
	    		r.add("tarikh_mula_chart",r.unquote(TM));
	    		r.add("tarikh_akhir_chart",r.unquote(TT));
	    		r.add("tarikh_lawatan",r.unquote(TL));
	    		r.add("tarikh_lapor",r.unquote(TLapor));	    		
	    		
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblppttanahumum");
	    		myLogger.info("SQL UPDATE LAPORAN TNH :: "+sql);
	    		stmt.executeUpdate(sql);
	    		  	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateLaporan

	public static void setDataLaporanTanah(String idpermohonan,String idpegawai)throws Exception {
		
		dataLaporanTanah = new Vector();
		
	    Db db = null;
	    dataLaporanTanah.clear();
	    String sql = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT DISTINCT id_tanahumum,id_permohonan,id_pegawai,tarikh_mula_chart,tarikh_akhir_chart,tarikh_lawatan,lokasi_tanah,keadaan_rupabumi, ";
	    		sql += " perihal_bangunan,ulasan_syor,perihal_tm_sendiri,perihal_tnh_kerajaan,bil_hakmilik,id_unitluas,luas_terlibat, ";
	    		sql += " harga_anggar,harga_anggar_bangunan,perihal_tmtr_sekutuan,perihal_tr_negeri,perihal_kawasan_simpan,no_peta_kadaster,tarikh_lapor, ";
	    		sql += " flag_luar_simpanan,flag_dlm_simpanan,flag_luar_majlis,flag_dlm_majlis,rupabumi_kwsn_terlibat, "; 
	    		sql += " flag_jenis_tanah,rupabumi_seluruh_lot,rupabumi_kwsn_terlibat,lot_seluruh_lot,lot_keadaan_tanaman,lot_jenis_tanaman,lot_kwsn_terlibat ";
	    		sql += " FROM tblppttanahumum ";
	    		sql += " WHERE id_permohonan = '"+idpermohonan+"'";
	    		sql += " AND id_pegawai = '"+idpegawai+"'";
	    		myLogger.info("setDataLaporanTanah :: "+sql);
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			h.put("id_tanahumum", rs.getString("id_tanahumum")== null?"":rs.getString("id_tanahumum"));
	    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
	    			h.put("no_peta_kadaster", rs.getString("no_peta_kadaster")== null?"":rs.getString("no_peta_kadaster"));
	    			h.put("lokasi_tanah", rs.getString("lokasi_tanah")== null?"":rs.getString("lokasi_tanah"));
	    			h.put("keadaan_rupabumi", rs.getString("keadaan_rupabumi")== null?"":rs.getString("keadaan_rupabumi"));	    			
	    			h.put("tarikh_mula_chart", rs.getDate("tarikh_mula_chart")==null?"":Format.format(rs.getDate("tarikh_mula_chart")));
	    			h.put("tarikh_akhir_chart", rs.getDate("tarikh_akhir_chart")==null?"":Format.format(rs.getDate("tarikh_akhir_chart")));
	    			h.put("tarikh_lawatan", rs.getDate("tarikh_lawatan")==null?"":Format.format(rs.getDate("tarikh_lawatan")));	    			
	    			h.put("perihal_bangunan", rs.getString("perihal_bangunan")== null?"":rs.getString("perihal_bangunan"));
	    			h.put("ulasan_syor", rs.getString("ulasan_syor")== null?"":rs.getString("ulasan_syor"));
	    			h.put("perihal_tm_sendiri", rs.getString("perihal_tm_sendiri")== null?"":rs.getString("perihal_tm_sendiri"));
	    			h.put("perihal_tmtr_sekutuan", rs.getString("perihal_tmtr_sekutuan")== null?"":rs.getString("perihal_tmtr_sekutuan"));
	    			h.put("perihal_tr_negeri", rs.getString("perihal_tr_negeri")== null?"":rs.getString("perihal_tr_negeri"));
	    			h.put("perihal_kawasan_melayu", rs.getString("perihal_kawasan_simpan")== null?"":rs.getString("perihal_kawasan_simpan"));	    			
	    			h.put("luas_terlibat", rs.getString("luas_terlibat")== null?"":rs.getString("luas_terlibat"));   			
	    			h.put("perihal_tnh_kerajaan", rs.getString("perihal_tnh_kerajaan")== null?"":rs.getString("perihal_tnh_kerajaan"));
	    			h.put("bil_hakmilik", rs.getString("bil_hakmilik")== null?"":rs.getString("bil_hakmilik"));
	    			h.put("id_unitluas", rs.getString("id_unitluas")== null?"":rs.getString("id_unitluas"));
	    			h.put("harga_anggar_bangunan", rs.getString("harga_anggar_bangunan")== null?"":rs.getString("harga_anggar_bangunan"));	
	    			h.put("harga_anggar", rs.getString("harga_anggar")== null?"":rs.getString("harga_anggar"));
	    			h.put("tarikh_lapor", rs.getDate("tarikh_lapor")==null?"":Format.format(rs.getDate("tarikh_lapor")));
	    			h.put("flag_luar_simpanan", rs.getString("flag_luar_simpanan")== null?"":rs.getString("flag_luar_simpanan"));
	    			h.put("flag_dlm_simpanan", rs.getString("flag_dlm_simpanan")== null?"":rs.getString("flag_dlm_simpanan"));
	    			h.put("flag_luar_majlis", rs.getString("flag_luar_majlis")== null?"":rs.getString("flag_luar_majlis"));
	    			h.put("flag_dlm_majlis", rs.getString("flag_dlm_majlis")== null?"":rs.getString("flag_dlm_majlis"));
	    			h.put("rupabumi_kwsn_terlibat", rs.getString("rupabumi_kwsn_terlibat")== null?"":rs.getString("rupabumi_kwsn_terlibat"));
	    			h.put("flag_jenis_tanah", rs.getString("flag_jenis_tanah")== null?"":rs.getString("flag_jenis_tanah"));	    			
	    			h.put("rupabumi_seluruh_lot", rs.getString("rupabumi_seluruh_lot")== null?"":rs.getString("rupabumi_seluruh_lot"));	    			
	    			h.put("rupabumi_kwsn_terlibat", rs.getString("rupabumi_kwsn_terlibat")== null?"":rs.getString("rupabumi_kwsn_terlibat"));	    			
	    			h.put("lot_seluruh_lot", rs.getString("lot_seluruh_lot")== null?"":rs.getString("lot_seluruh_lot"));	    			
	    			h.put("lot_keadaan_tanaman", rs.getString("lot_keadaan_tanaman")== null?"":rs.getString("lot_keadaan_tanaman"));	    			
	    			h.put("lot_jenis_tanaman", rs.getString("lot_jenis_tanaman")== null?"":rs.getString("lot_jenis_tanaman"));	    			
	    			h.put("lot_kwsn_terlibat", rs.getString("lot_kwsn_terlibat")== null?"":rs.getString("lot_kwsn_terlibat"));	    				    			

	    			//String
	    			h.put("str_harga_anggar_bangunan", rs.getString("harga_anggar_bangunan")== null?"":rs.getString("harga_anggar_bangunan"));	
	    			h.put("str_harga_anggar", rs.getString("harga_anggar")== null?"":rs.getString("harga_anggar"));
	    			
	    			dataLaporanTanah.addElement(h);
	    		
	      }//close while
	      
	    }// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close setDataLaporanTanah
	
	
}
