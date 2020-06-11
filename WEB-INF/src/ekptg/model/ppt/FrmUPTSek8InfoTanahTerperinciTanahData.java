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

public class FrmUPTSek8InfoTanahTerperinciTanahData {
	static Logger myLogger = Logger.getLogger(FrmUPTSek8InfoTanahTerperinciTanahData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector listcarian = null;
	private static Vector maklumatTanahTerperinci = null;
	private static Vector maklumatTanah = null; //PENAMBAHAN YATI
	private static Vector checkTableTanah = null;
	private static Vector listDokumen = null;
	private static Vector previousMaklumatTanahTerperinci = null;
	
	public static Vector getListCarian(){
		return listcarian;
	}
	
	public Vector getMaklumatTanahTerperinci(){
		return maklumatTanahTerperinci;
	}
	public Vector getMaklumatTanah(){ //PENAMBAHAN YATI
		return maklumatTanah;
	}
	
	public Vector getCheckTbltanah(){
		return checkTableTanah;
	}
	
	public Vector getListDokumen(){
		return listDokumen;
	}
	
	public Vector getPreviousMaklumatTanahTerperinci(){
		return previousMaklumatTanahTerperinci;
	}
	
	
	
	public static String sqlListPermohonan(String userIdNegeri)
	{

		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k,Tblppttandakawasan hx ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status and hx.id_permohonan(+) = p.id_permohonan ";
		sql +="AND f.id_suburusan = '52' ";
		sql +="AND (p.id_permohonan in ";
		//sql += " (" +
		/*		" select distinct px.id_permohonan from Tblpptpermohonan px ";
		sql += " where px.id_permohonan = p.id_permohonan ";
		sql += " AND (px.id_permohonan in (select distinct hx.id_permohonan from Tblppttandakawasan hx "; 
		sql += " where hx.id_permohonan = p.id_permohonan) ";
		sql += " OR " +
				"px.id_permohonan in " +*/
				sql += " (SELECT DISTINCT HM.ID_PERMOHONAN FROM TBLPPTTANAH TN, TBLPPTHAKMILIK HM " +
				" WHERE TN.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_PERMOHONAN = p.id_permohonan) OR hx.id_permohonan IS NOT NULL) ";
				//")) ";
		//sql +="AND p.id_status IN (22,26,31,43,132,133,134,147) ";
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
		    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +="AND p.id_status = s.id_status ";
		    		sql +="AND f.id_suburusan = '52' ";
		    		sql +="AND p.id_status IN (22,26,31,43,132,133,134,147) ";
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		    		*/
		    		sql = sqlListPermohonan(userIdNegeri);
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
	public static void setListCarian(String carianNofail, String carianTarikh, String status,String userIdNegeri)throws Exception {
		
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
	    		sql +="AND p.id_status IN (22,26,31,43,132,133,134,147) ";
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
	
	//penambahan yati
	@SuppressWarnings("unchecked")
	public void setMaklumatTanah(String idHakmilik) throws Exception {
		
		maklumatTanah = new Vector();
		
		Db db = null;
		maklumatTanah.clear();
		String sql = "";
	
		try{
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT DISTINCT b.id_hakmilik, c.id_permohonan, b.luas_ambil, b.luas_lot," +
						"b.id_unitluaslot_convert, b.id_unitluasambil_convert," +
						"b.no_hakmilik, b.id_jenishakmilik, b.no_syit," +
						"b.id_kategoritanah " +
						"FROM  tblppthakmilik b, tblpptpermohonan c " +
						"WHERE c.id_permohonan = b.id_permohonan " +
						"AND b.id_hakmilik = b.id_hakmilik " +
						"AND b.id_hakmilik = '"+idHakmilik+"'";
		
				ResultSet rs = stmt.executeQuery(sql);
				
				myLogger.info("SQL LIST MAKLUMAT HAKMILIK :"+sql);
				
				Hashtable h;
				
				while(rs.next()) {
					h = new Hashtable();
					
								
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));	
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));							
					h.put("no_syit", rs.getString("no_syit")==null?"":rs.getString("no_syit"));
					h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));	
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));	
					if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
						
						double luasAmbil = rs.getDouble("luas_ambil");
						String LA = Utils.formatLuasHM(luasAmbil);
						h.put("luas_ambil",LA);
								
					}else{
						h.put("luas_ambil","");
					}
					
					if(rs.getString("luas_lot") != null && rs.getString("luas_lot") != ""){
						
						double LL = rs.getDouble("luas_lot");
						String luasLot = Utils.formatLuasHM(LL);
						h.put("luas_lot",luasLot);
								
					}else{
						h.put("luas_lot","");
					}
					
					h.put("id_unitluaslot_convert", rs.getString("id_unitluaslot_convert")==null?"":rs.getString("id_unitluaslot_convert"));
					h.put("id_unitluasambil_convert", rs.getString("id_unitluasambil_convert")==null?"":rs.getString("id_unitluasambil_convert"));

					
					maklumatTanah.addElement(h);
				}
			
			}
			finally {
				if(db != null) db.close();
			}
		
	}//close 
	//penambahan yati
	@SuppressWarnings("unchecked")
	public void setMaklumatTanahTerperinci(String idHakmilik) throws Exception { //PPT-42
		
		maklumatTanahTerperinci = new Vector();
		
		Db db = null;
		maklumatTanahTerperinci.clear();
		String sql = "";
	
		try{
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT DISTINCT a.struktur_bangunan, a.struktur_bangunan_so, a.id_tanah,b.id_hakmilik,c.id_permohonan, ";
				sql += " b.luas_ambil, b.luas_lot, b.id_unitluaslot_convert, b.id_unitluasambil_convert, ";
				sql += " b.no_hakmilik,b.id_jenishakmilik,b.no_syit,b.id_kategoritanah,a.tarikh_akhir_lawat,a.tarikh_mula_lawat,a.tarikh_ulasan,a.flag_diusaha, ";
				sql += " a.rupabumi,a.flag_lembah,a.flag_lurah,a.flag_paya,a.flag_rendah,a.flag_rata,a.flag_landai,a.flag_bukit, ";
				sql += " a.flag_rezab_melayu,a.nama_pbt,a.flag_pbt,a.sempadan_timur,a.sempadan_selatan,a.sempadan_utara,a.kemudahan_awam, ";
				sql += " a.tanaman,a.halangan,a.keadaan_tanah,a.flag_semak,a.flag_belukar,a.flag_hutan,a.flag_terbiar, ";
				sql += " a.flag_lapang,a.harga_pasaran,a.harga_seunit_jpph,a.unit_harga,a.bayar_naik_nilaiso,a.bayar_pecahpisah, ";
				sql += " a.bayar_penjejasan,a.harga_pasaran_so,a.harga_seunit_so,a.unit_harga_so,a.sempadan_barat, ";
				sql += " a.jarak_bandar,a.id_pelapor,a.ulasan_pegawai,a.harga_seunit_akhir,a.unit_harga_akhir,a.naik_nilai_jpph, ";
				sql += " a.amaun_pecahpisah_jpph,a.amaun_penjejasan_jpph,a.id_unitluasterkini,a.id_unitluasambil, ";
				sql += " a.lokasi_tanah,a.nama_pelapor, b.id_pegawai, a.status_tanah, a.pendahuluan, ";
				sql += " a.jalan_utama, a.jalan_masuk, a.perumahan, a.industri, a.nama_tempat, a.flag_saliran, d.user_name, a.pecah_pisah, ";
				//PPT-42
				sql += " a.unit_harga_ns,a.harga_seunit_ns,a.harga_pasaran_ns,a.bayar_penjejasan_ns,a.bayar_pecah_ns,a.bayar_naik_nilai_ns ";
				sql += " FROM TBLPPTTANAH a, TBLPPTHAKMILIK b, TBLPPTPERMOHONAN c, USERS d";
				sql += " WHERE b.id_permohonan = c.id_permohonan ";
				sql += " AND a.id_pelapor = d.user_id(+)";
				sql += " AND a.id_hakmilik(+) = b.id_hakmilik ";
				sql += " AND b.id_hakmilik = '"+idHakmilik+"'";
		
				ResultSet rs = stmt.executeQuery(sql);
				
				myLogger.info("SQL LIST MAKLUMAT TANAH TERPERINCI "+sql);
				
				Hashtable h;
				
				while(rs.next()) {
					h = new Hashtable();
					
					if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
						
						double luasAmbil = rs.getDouble("luas_ambil");
						String LA = Utils.formatLuasHM(luasAmbil);
						h.put("luas_ambil",LA);
								
					}else{
						h.put("luas_ambil","");
					}
					
					if(rs.getString("luas_lot") != null && rs.getString("luas_lot") != ""){
						
						double LL = rs.getDouble("luas_lot");
						String luasLot = Utils.formatLuasHM(LL);
						h.put("luas_lot",luasLot);
								
					}else{
						h.put("luas_lot","");
					}
					
					h.put("id_unitluaslot_convert", rs.getString("id_unitluaslot_convert")==null?"":rs.getString("id_unitluaslot_convert"));
					h.put("id_unitluasambil_convert", rs.getString("id_unitluasambil_convert")==null?"":rs.getString("id_unitluasambil_convert"));
					
					h.put("pendahuluan", rs.getString("pendahuluan")==null?"":rs.getString("pendahuluan"));
					h.put("id_tanah", rs.getString("id_tanah")==null?"":rs.getString("id_tanah"));
					h.put("status_tanah", rs.getString("status_tanah")==null?"":rs.getString("status_tanah"));
					h.put("pecah_pisah", rs.getString("pecah_pisah")==null?"":rs.getString("pecah_pisah"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
					h.put("flag_saliran", rs.getString("flag_saliran")==null?"":rs.getString("flag_saliran"));
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
					h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));			
					h.put("jalan_utama", rs.getString("jalan_utama")==null?"":rs.getString("jalan_utama"));
					h.put("jalan_masuk", rs.getString("jalan_masuk")==null?"":rs.getString("jalan_masuk"));
					h.put("perumahan", rs.getString("perumahan")==null?"":rs.getString("perumahan"));
					h.put("industri", rs.getString("industri")==null?"":rs.getString("industri"));
					h.put("nama_tempat", rs.getString("nama_tempat")==null?"":rs.getString("nama_tempat"));			
					h.put("no_syit", rs.getString("no_syit")==null?"":rs.getString("no_syit"));
					h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));					
					h.put("tarikh_akhir_lawat", rs.getDate("tarikh_akhir_lawat")==null?"":Format.format(rs.getDate("tarikh_akhir_lawat")));
					h.put("tarikh_mula_lawat", rs.getDate("tarikh_mula_lawat")==null?"":Format.format(rs.getDate("tarikh_mula_lawat")));
					h.put("tarikh_ulasan", rs.getDate("tarikh_ulasan")==null?"":Format.format(rs.getDate("tarikh_ulasan")));					
					h.put("rupabumi", rs.getString("rupabumi")==null?"":rs.getString("rupabumi"));
					h.put("nama_pbt", rs.getString("nama_pbt")==null?"":rs.getString("nama_pbt"));
					h.put("sempadan_timur", rs.getString("sempadan_timur")==null?"":rs.getString("sempadan_timur"));
					h.put("sempadan_selatan", rs.getString("sempadan_selatan")==null?"":rs.getString("sempadan_selatan"));
					h.put("sempadan_utara", rs.getString("sempadan_utara")==null?"":rs.getString("sempadan_utara"));
					h.put("kemudahan_awam", rs.getString("kemudahan_awam")==null?"":rs.getString("kemudahan_awam"));				
					h.put("tanaman", rs.getString("tanaman")==null?"":rs.getString("tanaman"));
					h.put("halangan", rs.getString("halangan")==null?"":rs.getString("halangan"));
					h.put("keadaan_tanah", rs.getString("keadaan_tanah")==null?"":rs.getString("keadaan_tanah"));					
					h.put("unit_harga", rs.getString("unit_harga")==null?"":rs.getString("unit_harga"));								
					h.put("unit_harga_so", rs.getString("unit_harga_so")==null?"":rs.getString("unit_harga_so"));
					h.put("sempadan_barat", rs.getString("sempadan_barat")==null?"":rs.getString("sempadan_barat"));
					h.put("jarak_bandar", rs.getString("jarak_bandar")==null?"":rs.getString("jarak_bandar"));					
					h.put("ulasan_pegawai", rs.getString("ulasan_pegawai")==null?"":rs.getString("ulasan_pegawai"));					
					h.put("unit_harga_akhir", rs.getString("unit_harga_akhir")==null?"":rs.getString("unit_harga_akhir"));					
					h.put("id_unitluasterkini", rs.getString("id_unitluasterkini")==null?"":rs.getString("id_unitluasterkini"));
					h.put("id_unitluasambil", rs.getString("id_unitluasambil")==null?"":rs.getString("id_unitluasambil"));
					h.put("lokasi_tanah", rs.getString("lokasi_tanah")==null?"":rs.getString("lokasi_tanah"));
					
					//double
					h.put("struktur_bangunan_so", rs.getString("struktur_bangunan_so")==null?"":rs.getDouble("struktur_bangunan_so"));
					h.put("struktur_bangunan", rs.getString("struktur_bangunan")==null?"":rs.getDouble("struktur_bangunan"));
					h.put("naik_nilai_jpph", rs.getString("naik_nilai_jpph")==null?"":rs.getDouble("naik_nilai_jpph"));
					h.put("amaun_pecahpisah_jpph", rs.getString("amaun_pecahpisah_jpph")==null?"":rs.getDouble("amaun_pecahpisah_jpph"));
					h.put("amaun_penjejasan_jpph", rs.getString("amaun_penjejasan_jpph")==null?"":rs.getDouble("amaun_penjejasan_jpph"));
					h.put("harga_seunit_akhir", rs.getString("harga_seunit_akhir")==null?"":rs.getDouble("harga_seunit_akhir"));
					h.put("bayar_naik_nilaiso", rs.getString("bayar_naik_nilaiso")==null?"":rs.getDouble("bayar_naik_nilaiso"));
					h.put("bayar_pecahpisah", rs.getString("bayar_pecahpisah")==null?"":rs.getDouble("bayar_pecahpisah"));
					h.put("bayar_penjejasan", rs.getString("bayar_penjejasan")==null?"":rs.getDouble("bayar_penjejasan"));
					h.put("harga_pasaran_so", rs.getString("harga_pasaran_so")==null?"":rs.getDouble("harga_pasaran_so"));
					h.put("harga_seunit_so", rs.getString("harga_seunit_so")==null?"":rs.getDouble("harga_seunit_so"));	
					h.put("harga_pasaran", rs.getString("harga_pasaran")==null?"":rs.getDouble("harga_pasaran"));
					h.put("harga_seunit_jpph", rs.getString("harga_seunit_jpph")==null?"":rs.getDouble("harga_seunit_jpph"));
					
					//PPT-42
					h.put("unit_harga_ns", rs.getString("unit_harga_ns")==null?"":rs.getString("unit_harga_ns"));
					h.put("harga_seunit_ns", rs.getString("harga_seunit_ns")==null?"":rs.getDouble("harga_seunit_ns"));	
					h.put("harga_pasaran_ns", rs.getString("harga_pasaran_ns")==null?"":rs.getDouble("harga_pasaran_ns"));
					h.put("bayar_penjejasan_ns", rs.getString("bayar_penjejasan_ns")==null?"":rs.getDouble("bayar_penjejasan_ns"));
					h.put("bayar_pecah_ns", rs.getString("bayar_pecah_ns")==null?"":rs.getDouble("bayar_pecah_ns"));
					h.put("bayar_naik_nilai_ns", rs.getString("bayar_naik_nilai_ns")==null?"":rs.getDouble("bayar_naik_nilai_ns"));
					
					
					//flag checkbox
					h.put("flag_diusaha", rs.getString("flag_diusaha")==null?"0":rs.getString("flag_diusaha"));
					h.put("flag_lembah", rs.getString("flag_lembah")==null?"0":rs.getString("flag_lembah"));
					h.put("flag_lurah", rs.getString("flag_lurah")==null?"0":rs.getString("flag_lurah"));
					h.put("flag_paya", rs.getString("flag_paya")==null?"0":rs.getString("flag_paya"));
					h.put("flag_rendah", rs.getString("flag_rendah")==null?"0":rs.getString("flag_rendah"));
					h.put("flag_rata", rs.getString("flag_rata")==null?"0":rs.getString("flag_rata"));
					
					h.put("flag_landai", rs.getString("flag_landai")==null?"0":rs.getString("flag_landai"));
					h.put("flag_bukit", rs.getString("flag_bukit")==null?"0":rs.getString("flag_bukit"));
					h.put("flag_rezab_melayu", rs.getString("flag_rezab_melayu")==null?"0":rs.getString("flag_rezab_melayu"));
					h.put("flag_pbt", rs.getString("flag_pbt")==null?"0":rs.getString("flag_pbt"));
					h.put("flag_semak", rs.getString("flag_semak")==null?"0":rs.getString("flag_semak"));
					h.put("flag_belukar", rs.getString("flag_belukar")==null?"0":rs.getString("flag_belukar"));
					h.put("flag_hutan", rs.getString("flag_hutan")==null?"0":rs.getString("flag_hutan"));
					h.put("flag_terbiar", rs.getString("flag_terbiar")==null?"0":rs.getString("flag_terbiar"));
					h.put("flag_lapang", rs.getString("flag_lapang")==null?"0":rs.getString("flag_lapang"));
					
					h.put("nama_pelapor", rs.getString("nama_pelapor")==null?"":rs.getString("nama_pelapor"));
					
					h.put("id_pelapor", rs.getString("id_pelapor")==null?"":rs.getString("id_pelapor"));
					h.put("user_name", rs.getString("user_name")==null?"":rs.getString("user_name"));
					
					maklumatTanahTerperinci.addElement(h);
					System.out.println("input "+maklumatTanahTerperinci);
					//System.out.println("input : "+h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik")));
				}
			
			}
			finally {
				if(db != null) db.close();
			}
		
	}//close 
	
	@SuppressWarnings("unchecked")
	public void setCheckTbltanah(String idHakmilik) throws Exception {
		
		checkTableTanah = new Vector();
		
		Db db = null;
		checkTableTanah.clear();
		String sql = "";
	
		try{
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql = "SELECT DISTINCT id_tanah FROM TBLPPTTANAH WHERE id_hakmilik = '"+idHakmilik+"'";
		
				myLogger.info("CHECK SIZE TABLE :"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				
				Hashtable h;
				
				while(rs.next()) {
					h = new Hashtable();
					h.put("id_tanah", rs.getString("id_tanah")==null?"":rs.getString("id_tanah"));
					checkTableTanah.addElement(h);
				}
			
			}
			finally {
				if(db != null) db.close();
			}
		
	}//close checktable
	
	
	@SuppressWarnings("unchecked")
	public static void updateStatus(Hashtable data) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		//status tanah terperinci
	    		String status = "46";
	    		
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
	public static void simpanTanahTerperinci(Hashtable data, long id_tanah) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    	
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		
	    		//insert tblppttanah
	    		String txtPendahuluan = (String)data.get("txtPendahuluan");
	    		String txtJenisPisah = (String)data.get("txtJenisPisah");
	    		String txtStatusTanah = (String)data.get("txtStatusTanah");
	    		String txdTarikhUlasan = (String)data.get("txdTarikhUlasan");
	    		String txdTarikhLawatMula = (String)data.get("txdTarikhLawatMula");
	    		String txdTarikhLawatAkhir = (String)data.get("txdTarikhLawatAkhir");
	    		String txtJalanUtama = (String)data.get("txtJalanUtama");
	    		String txtJalanMasuk = (String)data.get("txtJalanMasuk");
	    		String txtNamaTempat = (String)data.get("txtNamaTempat");
	    		String txtPerumahan = (String)data.get("txtPerumahan");
	    		String txtIndustri = (String)data.get("txtIndustri");
	    		String sorSaliran = (String)data.get("sorSaliran");
	    		//String socUnitLuasLot = (String)data.get("socUnitLuasLot");
	    		//String socUnitLuasAmbil = (String)data.get("socUnitLuasAmbil");
	    		//String txtLuasLot = (String)data.get("txtLuasLot");
	    		//String txtLuasAmbil = (String)data.get("txtLuasAmbil");
	    		String txtLokasi = (String)data.get("txtLokasi");
	    		String txtJarak = (String)data.get("txtJarak");	    		
	    		//String idPelapor = (String)data.get("idPelapor");
	    		//String txtPelapor = (String)data.get("txtPelapor");	 
	    		String txtUlasanPegawai = (String)data.get("txtUlasanPegawai");
	    		String txtHargaBorangG = (String)data.get("txtHargaBorangG");
	    		String socUnitHargaG = (String)data.get("socUnitHargaG");
	    		String txtKenaikanJP = (String)data.get("txtKenaikanJP");
	    		String txtPecahJP = (String)data.get("txtPecahJP");
	    		String txtPenjejasanJP = (String)data.get("txtPenjejasanJP");
	    		String txtHargaPasaranJP = (String)data.get("txtHargaPasaranJP");
	    		String txtHargaSeunitJP = (String)data.get("txtHargaSeunitJP");
	    		String socUnitHargaJP = (String)data.get("socUnitHargaJP");
	    		String txtKenaikanSO = (String)data.get("txtKenaikanSO");
	    		String txtPecahSO = (String)data.get("txtPecahSO");
	    		String txtPenjejasanSO = (String)data.get("txtPenjejasanSO");
	    		String txtHargaPasaranSO = (String)data.get("txtHargaPasaranSO");
	    		String txtHargaSeunitSO = (String)data.get("txtHargaSeunitSO");
	    		String socUnitHargaSO = (String)data.get("socUnitHargaSO");	    		
	    		String txtSempadanBarat = (String)data.get("txtSempadanBarat");
	    		String txtSempadanTimur = (String)data.get("txtSempadanTimur");
	    		String txtSempadanSelatan = (String)data.get("txtSempadanSelatan");
	    		String txtSempadanUtara = (String)data.get("txtSempadanUtara");
	    		String txtKemudahan = (String)data.get("txtKemudahan");
	    		String txtTanaman = (String)data.get("txtTanaman");
	    		String txtHalangan = (String)data.get("txtHalangan");
	    		String txtPerihalKeadaan = (String)data.get("txtPerihalKeadaan");
	    		String flagSemak = (String)data.get("flagSemak");
	    		String flagBelukar = (String)data.get("flagBelukar");
	    		String flagHutan = (String)data.get("flagHutan");
	    		String flagTerbiar = (String)data.get("flagTerbiar");
	    		String flagLapang = (String)data.get("flagLapang");	    		
	    		String flagUsaha = (String)data.get("flagUsaha");
	    		String txtPerihalRupabumi = (String)data.get("txtPerihalRupabumi");
	    		String flagLembah = (String)data.get("flagLembah");
	    		String flagLurah = (String)data.get("flagLurah");
	    		String flagBerpaya = (String)data.get("flagBerpaya");
	    		String flagRendah = (String)data.get("flagRendah");	    		
	    		String flagRata = (String)data.get("flagRata");
	    		String flagLandai = (String)data.get("flagLandai");
	    		String flagBukit = (String)data.get("flagBukit");
	    		String sorRizab = (String)data.get("sorRizab");
	    		String txtNamaPBT = (String)data.get("txtNamaPBT");
	    		String sorPBT = (String)data.get("sorPBT");
	    		
	    		//PPT-42
	    		String socUnitHargaNS = (String)data.get("socUnitHargaNS");
	    		String txtHargaSeunitNS = (String)data.get("txtHargaSeunitNS");
	    		String txtHargaPasaranNS = (String)data.get("txtHargaPasaranNS");
	    		String txtPenjejasanNS = (String)data.get("txtPenjejasanNS");
	    		String txtPecahNS = (String)data.get("txtPecahNS");
	    		String txtKenaikanNS = (String)data.get("txtKenaikanNS");
	    		
	    		String txtStrukturBangunanSO = (String)data.get("txtStrukturBangunanSO");
	    		String txtStrukturBangunan = (String)data.get("txtStrukturBangunan");

	    		String socPegawai = (String)data.get("socPegawai");
	    		
	    		
	    		String TU = "to_date('" + txdTarikhUlasan + "','dd/MM/yyyy')";
	    		String TLM = "to_date('" + txdTarikhLawatMula + "','dd/MM/yyyy')";
	    		String TLA = "to_date('" + txdTarikhLawatAkhir + "','dd/MM/yyyy')";
	    		
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_tanah",id_tanah);
	    		r.add("id_pelapor",socPegawai);
	    		r.add("id_hakmilik", id_hakmilik);	   
	    		r.add("pecah_pisah",txtJenisPisah);
	    		r.add("lokasi_tanah",txtLokasi);
	    		r.add("pendahuluan",txtPendahuluan);  
	    		r.add("flag_saliran",sorSaliran);
	    		r.add("status_tanah",txtStatusTanah);	    		
	    		r.add("jalan_utama",txtJalanUtama);
	    		r.add("jalan_masuk",txtJalanMasuk);
	    		r.add("nama_tempat",txtNamaTempat);
	    		r.add("perumahan",txtPerumahan);
	    		r.add("industri",txtIndustri);
	    		//r.add("luas_ambil",txtLuasAmbil);
	    		//r.add("luas_terkini",txtLuasLot);
	    		//r.add("id_unitluasambil",socUnitLuasAmbil);
	    		//r.add("id_unitluasterkini",socUnitLuasLot);
	    		r.add("amaun_penjejasan_jpph",txtPenjejasanJP);
	    		r.add("amaun_pecahpisah_jpph",txtPecahJP);
	    		r.add("naik_nilai_jpph",txtKenaikanJP);
	    		r.add("unit_harga_akhir",socUnitHargaG);
	    		r.add("harga_seunit_akhir",txtHargaBorangG);
	    		r.add("ulasan_pegawai",txtUlasanPegawai);
	    		//r.add("nama_pelapor",txtPelapor);	    		
	    		r.add("jarak_bandar",txtJarak);
	    		r.add("sempadan_barat",txtSempadanBarat);
	    		r.add("unit_harga_so",socUnitHargaSO);
	    		r.add("harga_seunit_so",txtHargaSeunitSO);
	    		r.add("harga_pasaran_so",txtHargaPasaranSO);
	    		r.add("bayar_penjejasan",txtPenjejasanSO);
	    		r.add("bayar_pecahpisah",txtPecahSO);
	    		r.add("bayar_naik_nilaiso",txtKenaikanSO);
	    		r.add("unit_harga",socUnitHargaJP);
	    		r.add("harga_seunit_jpph",txtHargaSeunitJP);
	    		r.add("harga_pasaran",txtHargaPasaranJP);
	    		r.add("flag_lapang",flagLapang);
	    		r.add("flag_terbiar",flagTerbiar);
	    		r.add("flag_hutan",flagHutan);
	    		r.add("flag_belukar",flagBelukar);
	    		r.add("flag_semak",flagSemak);
	    		r.add("keadaan_tanah",txtPerihalKeadaan);
	    		r.add("halangan",txtHalangan);
	    		r.add("tanaman",txtTanaman);
	    		r.add("kemudahan_awam",txtKemudahan);
	    		r.add("sempadan_utara",txtSempadanUtara);
	    		r.add("sempadan_selatan",txtSempadanSelatan);
	    		r.add("sempadan_timur",txtSempadanTimur);
	    		r.add("flag_pbt",sorPBT);
	    		r.add("nama_pbt",txtNamaPBT);
	    		r.add("flag_rezab_melayu",sorRizab);
	    		r.add("flag_bukit",flagBukit);
	    		r.add("flag_landai",flagLandai);
	    		r.add("flag_rata",flagRata);
	    		r.add("flag_rendah",flagRendah);
	    		r.add("flag_paya",flagBerpaya);
	    		r.add("flag_lurah",flagLurah);
	    		r.add("flag_lembah",flagLembah);
	    		r.add("rupabumi",txtPerihalRupabumi);
	    		r.add("flag_diusaha",flagUsaha);	
	    		r.add("tarikh_ulasan",r.unquote(TU));
	    		r.add("tarikh_mula_lawat",r.unquote(TLM));
	    		r.add("tarikh_akhir_lawat",r.unquote(TLA));	    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    	
	    		//PPT-42
	    		r.add("unit_harga_ns",socUnitHargaNS);
	    		r.add("harga_seunit_ns",txtHargaSeunitNS);
	    		r.add("harga_pasaran_ns",txtHargaPasaranNS);
	    		r.add("bayar_penjejasan_ns",txtPenjejasanNS);
	    		r.add("bayar_pecahpisah_ns",txtPecahNS);
	    		r.add("bayar_naik_nilai_ns",txtKenaikanNS);
	    		
	    		if(txtStrukturBangunanSO!=null)
	    		{
	    		r.add("struktur_bangunan_so",txtStrukturBangunanSO);
	    		}
	    		if(txtStrukturBangunan!=null)
	    		{
	    		r.add("struktur_bangunan",txtStrukturBangunan);
	    		}
	    		
	    		sql = r.getSQLInsert("tblppttanah");
	    		
	    		myLogger.info("simpanTanahTerperinci :"+sql);
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanTanahTerperinci
	
	@SuppressWarnings("unchecked")
	public static void updateHMtanah(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		
	    		String socKategoriTanah = (String)data.get("socKategoriTanah");
	    		String txtNoSyit = (String)data.get("txtNoSyit");
	    		String txtNoHakmilik = (String)data.get("txtNoHakmilik");
	    		String socJenisHakmilik = (String)data.get("socJenisHakmilik");
	    		
	    		String txtLuasLotAsal = (String)data.get("txtLuasLotAsal");
	    		String sorDropdownUnitAsal = (String)data.get("sorDropdownUnitAsal");
	    		String txtLuasLotAmbil = (String)data.get("txtLuasLotAmbil");
	    		String sorDropdownUnitAmbil = (String)data.get("sorDropdownUnitAmbil");
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_hakmilik", id_hakmilik);	    		
	    		r.add("no_syit",txtNoSyit);
	    		r.add("no_hakmilik",txtNoHakmilik);
	    		r.add("id_kategoritanah",socKategoriTanah);	  
	    		r.add("id_jenishakmilik",socJenisHakmilik);	
	    		
	    		r.add("luas_lot",txtLuasLotAsal);
	    		r.add("id_unitluaslot_convert",sorDropdownUnitAsal);
	    		r.add("luas_ambil",txtLuasLotAmbil);	  
	    		r.add("id_unitluasambil_convert",sorDropdownUnitAmbil);	
	    		
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblppthakmilik");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateHMtanah
	
	//PPT-42
	@SuppressWarnings("unchecked")
	public static void updateTanahTerperinci(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    	
	    		String id_tanah = (String)data.get("id_tanah");
	    		
	    		//insert tblppttanah
	    		String txtPendahuluan = (String)data.get("txtPendahuluan");
	    		String txtJenisPisah = (String)data.get("txtJenisPisah");
	    		String txtStatusTanah = (String)data.get("txtStatusTanah");
	    		String txdTarikhUlasan = (String)data.get("txdTarikhUlasan");
	    		String txdTarikhLawatMula = (String)data.get("txdTarikhLawatMula");
	    		String txdTarikhLawatAkhir = (String)data.get("txdTarikhLawatAkhir");	
	    		String txtJalanUtama = (String)data.get("txtJalanUtama");
	    		String txtJalanMasuk = (String)data.get("txtJalanMasuk");
	    		String txtNamaTempat = (String)data.get("txtNamaTempat");
	    		String txtPerumahan = (String)data.get("txtPerumahan");
	    		String txtIndustri = (String)data.get("txtIndustri");
	    		String sorSaliran = (String)data.get("sorSaliran");
	    		//String socUnitLuasLot = (String)data.get("socUnitLuasLot");
	    		//String socUnitLuasAmbil = (String)data.get("socUnitLuasAmbil");
	    		//String txtLuasLot = (String)data.get("txtLuasLot");
	    		//String txtLuasAmbil = (String)data.get("txtLuasAmbil");
	    		String txtLokasi = (String)data.get("txtLokasi");
	    		String txtJarak = (String)data.get("txtJarak");	    		
	    		//String idPelapor = (String)data.get("idPelapor");
	    		//String txtPelapor = (String)data.get("txtPelapor");
	    		String txtUlasanPegawai = (String)data.get("txtUlasanPegawai");
	    		String txtHargaBorangG = (String)data.get("txtHargaBorangG");
	    		String socUnitHargaG = (String)data.get("socUnitHargaG");
	    		String txtKenaikanJP = (String)data.get("txtKenaikanJP");
	    		String txtPecahJP = (String)data.get("txtPecahJP");
	    		String txtPenjejasanJP = (String)data.get("txtPenjejasanJP");
	    		String txtHargaPasaranJP = (String)data.get("txtHargaPasaranJP");
	    		String txtHargaSeunitJP = (String)data.get("txtHargaSeunitJP");
	    		String socUnitHargaJP = (String)data.get("socUnitHargaJP");
	    		String txtKenaikanSO = (String)data.get("txtKenaikanSO");
	    		String txtPecahSO = (String)data.get("txtPecahSO");
	    		String txtPenjejasanSO = (String)data.get("txtPenjejasanSO");
	    		String txtHargaPasaranSO = (String)data.get("txtHargaPasaranSO");
	    		String txtHargaSeunitSO = (String)data.get("txtHargaSeunitSO");
	    		String socUnitHargaSO = (String)data.get("socUnitHargaSO");
	    		String txtSempadanBarat = (String)data.get("txtSempadanBarat");
	    		String txtSempadanTimur = (String)data.get("txtSempadanTimur");
	    		String txtSempadanSelatan = (String)data.get("txtSempadanSelatan");
	    		String txtSempadanUtara = (String)data.get("txtSempadanUtara");
	    		String txtKemudahan = (String)data.get("txtKemudahan");
	    		String txtTanaman = (String)data.get("txtTanaman");
	    		String txtHalangan = (String)data.get("txtHalangan");
	    		String txtPerihalKeadaan = (String)data.get("txtPerihalKeadaan");
	    		String flagSemak = (String)data.get("flagSemak");
	    		String flagBelukar = (String)data.get("flagBelukar");
	    		String flagHutan = (String)data.get("flagHutan");
	    		String flagTerbiar = (String)data.get("flagTerbiar");
	    		String flagLapang = (String)data.get("flagLapang");	    		
	    		String flagUsaha = (String)data.get("flagUsaha");
	    		String txtPerihalRupabumi = (String)data.get("txtPerihalRupabumi");
	    		String flagLembah = (String)data.get("flagLembah");
	    		String flagLurah = (String)data.get("flagLurah");
	    		String flagBerpaya = (String)data.get("flagBerpaya");
	    		String flagRendah = (String)data.get("flagRendah");	    		
	    		String flagRata = (String)data.get("flagRata");
	    		String flagLandai = (String)data.get("flagLandai");
	    		String flagBukit = (String)data.get("flagBukit");
	    		String sorRizab = (String)data.get("sorRizab");
	    		String txtNamaPBT = (String)data.get("txtNamaPBT");
	    		String sorPBT = (String)data.get("sorPBT");
	    		
	    		//PPT-42
	    		String socUnitHargaNS = (String)data.get("socUnitHargaNS");
	    		String txtHargaSeunitNS = (String)data.get("txtHargaSeunitNS");
	    		String txtHargaPasaranNS = (String)data.get("txtHargaPasaranNS");
	    		String txtPenjejasanNS = (String)data.get("txtPenjejasanNS");
	    		String txtPecahNS = (String)data.get("txtPecahNS");
	    		String txtKenaikanNS = (String)data.get("txtKenaikanNS");
	    		
	    		String txtStrukturBangunanSO = (String)data.get("txtStrukturBangunanSO");
	    		String txtStrukturBangunan = (String)data.get("txtStrukturBangunan");
	    		
	    		String socPegawai = (String)data.get("socPegawai");
	    		
	    		String TU = "to_date('" + txdTarikhUlasan + "','dd/MM/yyyy')";
	    		String TLM = "to_date('" + txdTarikhLawatMula + "','dd/MM/yyyy')";
	    		String TLA = "to_date('" + txdTarikhLawatAkhir + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_tanah", id_tanah);	   
	    		r.add("id_pelapor",socPegawai);
	    		r.add("pecah_pisah",txtJenisPisah);
	    		r.add("lokasi_tanah",txtLokasi);
	    		r.add("pendahuluan",txtPendahuluan);    		
	    		r.add("status_tanah",txtStatusTanah);
	    		r.add("flag_saliran",sorSaliran);
	    		r.add("jalan_utama",txtJalanUtama);
	    		r.add("jalan_masuk",txtJalanMasuk);
	    		r.add("nama_tempat",txtNamaTempat);
	    		r.add("perumahan",txtPerumahan);
	    		r.add("industri",txtIndustri);
	    		//r.add("luas_ambil",txtLuasAmbil);
	    		//r.add("luas_terkini",txtLuasLot);
	    		//r.add("id_unitluasambil",socUnitLuasAmbil);
	    		//r.add("id_unitluasterkini",socUnitLuasLot);
	    		r.add("amaun_penjejasan_jpph",txtPenjejasanJP);
	    		r.add("amaun_pecahpisah_jpph",txtPecahJP);
	    		r.add("naik_nilai_jpph",txtKenaikanJP);
	    		r.add("unit_harga_akhir",socUnitHargaG);
	    		r.add("harga_seunit_akhir",txtHargaBorangG);
	    		r.add("ulasan_pegawai",txtUlasanPegawai);
	    		//r.add("nama_pelapor",txtPelapor);
	    		r.add("jarak_bandar",txtJarak);
	    		r.add("sempadan_barat",txtSempadanBarat);
	    		r.add("unit_harga_so",socUnitHargaSO);
	    		r.add("harga_seunit_so",txtHargaSeunitSO);
	    		r.add("harga_pasaran_so",txtHargaPasaranSO);
	    		r.add("bayar_penjejasan",txtPenjejasanSO);
	    		r.add("bayar_pecahpisah",txtPecahSO);
	    		r.add("bayar_naik_nilaiso",txtKenaikanSO);
	    		r.add("unit_harga",socUnitHargaJP);
	    		r.add("harga_seunit_jpph",txtHargaSeunitJP);
	    		r.add("harga_pasaran",txtHargaPasaranJP);
	    		r.add("flag_lapang",flagLapang);
	    		r.add("flag_terbiar",flagTerbiar);
	    		r.add("flag_hutan",flagHutan);
	    		r.add("flag_belukar",flagBelukar);
	    		r.add("flag_semak",flagSemak);
	    		r.add("keadaan_tanah",txtPerihalKeadaan);
	    		r.add("halangan",txtHalangan);
	    		r.add("tanaman",txtTanaman);
	    		r.add("kemudahan_awam",txtKemudahan);
	    		r.add("sempadan_utara",txtSempadanUtara);
	    		r.add("sempadan_selatan",txtSempadanSelatan);
	    		r.add("sempadan_timur",txtSempadanTimur);
	    		r.add("flag_pbt",sorPBT);
	    		r.add("nama_pbt",txtNamaPBT);
	    		r.add("flag_rezab_melayu",sorRizab);
	    		r.add("flag_bukit",flagBukit);
	    		r.add("flag_landai",flagLandai);
	    		r.add("flag_rata",flagRata);
	    		r.add("flag_rendah",flagRendah);
	    		r.add("flag_paya",flagBerpaya);
	    		r.add("flag_lurah",flagLurah);
	    		r.add("flag_lembah",flagLembah);
	    		r.add("rupabumi",txtPerihalRupabumi);
	    		r.add("flag_diusaha",flagUsaha);	
	    		r.add("tarikh_ulasan",r.unquote(TU));
	    		r.add("tarikh_mula_lawat",r.unquote(TLM));
	    		r.add("tarikh_akhir_lawat",r.unquote(TLA));	    		
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    	
	    		
	    		//PPT-42
	    		r.add("unit_harga_ns",socUnitHargaNS);
	    		r.add("harga_seunit_ns",txtHargaSeunitNS);
	    		r.add("harga_pasaran_ns",txtHargaPasaranNS);
	    		r.add("bayar_penjejasan_ns",txtPenjejasanNS);
	    		r.add("bayar_pecahpisah_ns",txtPecahNS);
	    		r.add("bayar_naik_nilai_ns",txtKenaikanNS);
	    		
	    		r.add("struktur_bangunan_so",txtStrukturBangunanSO);
	    		r.add("struktur_bangunan",txtStrukturBangunan);	
	    		
	    		sql = r.getSQLUpdate("tblppttanah");
	    		
	    		System.out.println("CHECK ::: "+sql);
	    		
	    		stmt.executeUpdate(sql);
	    		
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateTanahTerperinci
	
	@SuppressWarnings("unchecked")
	public void setListDokumen(String id_tanah) throws Exception {
		
		listDokumen = new Vector();
		
		Db db = null;
		listDokumen.clear();
		String sql = "";
	
		try{
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql = "SELECT DISTINCT A.ID_TANAH, A.ID_DOKUMEN, A.NAMA_FAIL, A.JENIS_MIME, A.TAJUK, A.KETERANGAN " +
					  " FROM TBLPPTDOKUMEN A WHERE A.ID_TANAH = '"+id_tanah+"'";
		
				ResultSet rs = stmt.executeQuery(sql);
				
				Hashtable h;
				int bil = 1;
				
				while(rs.next()) {
					h = new Hashtable();
					h.put("bil",bil);
					h.put("id_tanah", rs.getString("ID_TANAH")== null?"":rs.getString("ID_TANAH"));
			    	h.put("id_dokumen", rs.getString("ID_DOKUMEN")== null?"":rs.getString("ID_DOKUMEN"));
			    	h.put("nama_fail", rs.getString("NAMA_FAIL")== null?"":rs.getString("NAMA_FAIL"));
			    	h.put("jenis_mime",rs.getString("JENIS_MIME")== null?"":rs.getString("JENIS_MIME"));
			    	h.put("tajuk",rs.getString("TAJUK")== null?"":rs.getString("TAJUK"));
			    	h.put("keterangan",rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
					listDokumen.addElement(h);
					bil++;
				}
			
			}
			finally {
				if(db != null) db.close();
			}
		
	}//close setListDokumen
	
	
	public static void hapusDokumen(String id_dokumen) throws Exception {
	   
		Db db = null;
	    String sql = "";
	   
	    try{
	    	
	    	 	db = new Db();
	    	 	Statement stmt = db.getStatement();	
	    		
	    		sql = "DELETE FROM tblpptdokumen where id_dokumen = '"+id_dokumen+"'";
	    		stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }//close hapus
	
	@SuppressWarnings("unchecked")
	public void setPreviousMaklumatTanahTerperinci(String idpermohonan) throws Exception {
		
		previousMaklumatTanahTerperinci = new Vector();
		
		Db db = null;
		previousMaklumatTanahTerperinci.clear();
		String sql = "";
	
		try{
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT DISTINCT a.struktur_bangunan_so, a.struktur_bangunan,a.id_tanah,b.id_hakmilik,c.id_permohonan, ";
				sql += " b.luas_ambil, b.luas_lot, b.id_unitluaslot_convert, b.id_unitluasambil_convert, ";
				sql += " b.no_hakmilik,b.id_jenishakmilik,b.no_syit,b.id_kategoritanah,a.tarikh_akhir_lawat,a.tarikh_mula_lawat,a.tarikh_ulasan,a.flag_diusaha, ";
				sql += " a.rupabumi,a.flag_lembah,a.flag_lurah,a.flag_paya,a.flag_rendah,a.flag_rata,a.flag_landai,a.flag_bukit, ";
				sql += " a.flag_rezab_melayu,a.nama_pbt,a.flag_pbt,a.sempadan_timur,a.sempadan_selatan,a.sempadan_utara,a.kemudahan_awam, ";
				sql += " a.tanaman,a.halangan,a.keadaan_tanah,a.flag_semak,a.flag_belukar,a.flag_hutan,a.flag_terbiar, ";
				sql += " a.flag_lapang,a.harga_pasaran,a.harga_seunit_jpph,a.unit_harga,a.bayar_naik_nilaiso,a.bayar_pecahpisah, ";
				sql += " a.bayar_penjejasan,a.harga_pasaran_so,a.harga_seunit_so,a.unit_harga_so,a.sempadan_barat, ";
				sql += " a.jarak_bandar,a.id_pelapor,a.ulasan_pegawai,a.harga_seunit_akhir,a.unit_harga_akhir,a.naik_nilai_jpph, ";
				sql += " a.amaun_pecahpisah_jpph,a.amaun_penjejasan_jpph,a.id_unitluasterkini,a.id_unitluasambil, ";
				sql += " a.lokasi_tanah,a.nama_pelapor, b.id_pegawai, a.status_tanah, a.pendahuluan, ";
				sql += " a.jalan_utama, a.jalan_masuk, a.perumahan, a.industri, a.nama_tempat, a.flag_saliran, d.user_name, a.pecah_pisah";
				sql += " FROM TBLPPTTANAH a, TBLPPTHAKMILIK b, TBLPPTPERMOHONAN c, USERS d";
				sql += " WHERE b.id_permohonan = c.id_permohonan ";
				sql += " AND a.id_pelapor = d.user_id(+)";
				sql += " AND a.id_hakmilik(+) = b.id_hakmilik ";
				sql += " AND c.id_permohonan = '"+idpermohonan+"' ";
				sql += " AND a.id_tanah = (select max(a1.id_tanah)from tblppttanah a1, tblppthakmilik b1, tblpptpermohonan c1 ";
				sql += " where a1.id_hakmilik = b1.id_hakmilik ";
				sql += " and c1.id_permohonan = b1.id_permohonan ";
				sql += " and c1.id_permohonan = '"+idpermohonan+"') "; 
		
				ResultSet rs = stmt.executeQuery(sql);
				
				Hashtable h;
				
				while(rs.next()) {
					h = new Hashtable();		
					h.put("pendahuluan", rs.getString("pendahuluan")==null?"":rs.getString("pendahuluan"));
					h.put("id_tanah", rs.getString("id_tanah")==null?"":rs.getString("id_tanah"));
					h.put("status_tanah", rs.getString("status_tanah")==null?"":rs.getString("status_tanah"));
					h.put("pecah_pisah", rs.getString("pecah_pisah")==null?"":rs.getString("pecah_pisah"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
					h.put("flag_saliran", rs.getString("flag_saliran")==null?"":rs.getString("flag_saliran"));
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
					h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));			
					h.put("jalan_utama", rs.getString("jalan_utama")==null?"":rs.getString("jalan_utama"));
					h.put("jalan_masuk", rs.getString("jalan_masuk")==null?"":rs.getString("jalan_masuk"));
					h.put("perumahan", rs.getString("perumahan")==null?"":rs.getString("perumahan"));
					h.put("industri", rs.getString("industri")==null?"":rs.getString("industri"));
					h.put("nama_tempat", rs.getString("nama_tempat")==null?"":rs.getString("nama_tempat"));			
					h.put("no_syit", rs.getString("no_syit")==null?"":rs.getString("no_syit"));
					h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));					
					h.put("tarikh_akhir_lawat", rs.getDate("tarikh_akhir_lawat")==null?"":Format.format(rs.getDate("tarikh_akhir_lawat")));
					h.put("tarikh_mula_lawat", rs.getDate("tarikh_mula_lawat")==null?"":Format.format(rs.getDate("tarikh_mula_lawat")));
					h.put("tarikh_ulasan", rs.getDate("tarikh_ulasan")==null?"":Format.format(rs.getDate("tarikh_ulasan")));					
					h.put("rupabumi", rs.getString("rupabumi")==null?"":rs.getString("rupabumi"));
					h.put("nama_pbt", rs.getString("nama_pbt")==null?"":rs.getString("nama_pbt"));
					h.put("sempadan_timur", rs.getString("sempadan_timur")==null?"":rs.getString("sempadan_timur"));
					h.put("sempadan_selatan", rs.getString("sempadan_selatan")==null?"":rs.getString("sempadan_selatan"));
					h.put("sempadan_utara", rs.getString("sempadan_utara")==null?"":rs.getString("sempadan_utara"));
					h.put("kemudahan_awam", rs.getString("kemudahan_awam")==null?"":rs.getString("kemudahan_awam"));				
					h.put("tanaman", rs.getString("tanaman")==null?"":rs.getString("tanaman"));
					h.put("halangan", rs.getString("halangan")==null?"":rs.getString("halangan"));
					h.put("keadaan_tanah", rs.getString("keadaan_tanah")==null?"":rs.getString("keadaan_tanah"));					
					h.put("unit_harga", rs.getString("unit_harga")==null?"":rs.getString("unit_harga"));								
					h.put("unit_harga_so", rs.getString("unit_harga_so")==null?"":rs.getString("unit_harga_so"));
					h.put("sempadan_barat", rs.getString("sempadan_barat")==null?"":rs.getString("sempadan_barat"));
					h.put("jarak_bandar", rs.getString("jarak_bandar")==null?"":rs.getString("jarak_bandar"));					
					h.put("ulasan_pegawai", rs.getString("ulasan_pegawai")==null?"":rs.getString("ulasan_pegawai"));					
					h.put("unit_harga_akhir", rs.getString("unit_harga_akhir")==null?"":rs.getString("unit_harga_akhir"));					
					h.put("id_unitluasterkini", rs.getString("id_unitluasterkini")==null?"":rs.getString("id_unitluasterkini"));
					h.put("id_unitluasambil", rs.getString("id_unitluasambil")==null?"":rs.getString("id_unitluasambil"));
					h.put("lokasi_tanah", rs.getString("lokasi_tanah")==null?"":rs.getString("lokasi_tanah"));
					
					//double
					h.put("struktur_bangunan_so", rs.getString("struktur_bangunan_so")==null?0:rs.getDouble("struktur_bangunan_so"));
					h.put("struktur_bangunan", rs.getString("struktur_bangunan")==null?0:rs.getDouble("struktur_bangunan"));
					h.put("naik_nilai_jpph", rs.getString("naik_nilai_jpph")==null?0:rs.getDouble("naik_nilai_jpph"));
					h.put("amaun_pecahpisah_jpph", rs.getString("amaun_pecahpisah_jpph")==null?0:rs.getDouble("amaun_pecahpisah_jpph"));
					h.put("amaun_penjejasan_jpph", rs.getString("amaun_penjejasan_jpph")==null?0:rs.getDouble("amaun_penjejasan_jpph"));
					//h.put("harga_seunit_akhir", rs.getString("harga_seunit_akhir")==null?"":rs.getDouble("harga_seunit_akhir"));
					h.put("bayar_naik_nilaiso", rs.getString("bayar_naik_nilaiso")==null?0:rs.getDouble("bayar_naik_nilaiso"));
					h.put("bayar_pecahpisah", rs.getString("bayar_pecahpisah")==null?0:rs.getDouble("bayar_pecahpisah"));
					h.put("bayar_penjejasan", rs.getString("bayar_penjejasan")==null?0:rs.getDouble("bayar_penjejasan"));
					//h.put("harga_pasaran_so", rs.getString("harga_pasaran_so")==null?"":rs.getDouble("harga_pasaran_so"));
					h.put("harga_seunit_so", rs.getString("harga_seunit_so")==null?0:rs.getDouble("harga_seunit_so"));	
					//h.put("harga_pasaran", rs.getString("harga_pasaran")==null?"":rs.getDouble("harga_pasaran"));
					h.put("harga_seunit_jpph", rs.getString("harga_seunit_jpph")==null?0:rs.getDouble("harga_seunit_jpph"));
					
					//flag checkbox
					h.put("flag_diusaha", rs.getString("flag_diusaha")==null?"0":rs.getString("flag_diusaha"));
					h.put("flag_lembah", rs.getString("flag_lembah")==null?"0":rs.getString("flag_lembah"));
					h.put("flag_lurah", rs.getString("flag_lurah")==null?"0":rs.getString("flag_lurah"));
					h.put("flag_paya", rs.getString("flag_paya")==null?"0":rs.getString("flag_paya"));
					h.put("flag_rendah", rs.getString("flag_rendah")==null?"0":rs.getString("flag_rendah"));
					h.put("flag_rata", rs.getString("flag_rata")==null?"0":rs.getString("flag_rata"));
					
					h.put("flag_landai", rs.getString("flag_landai")==null?"0":rs.getString("flag_landai"));
					h.put("flag_bukit", rs.getString("flag_bukit")==null?"0":rs.getString("flag_bukit"));
					h.put("flag_rezab_melayu", rs.getString("flag_rezab_melayu")==null?"0":rs.getString("flag_rezab_melayu"));
					h.put("flag_pbt", rs.getString("flag_pbt")==null?"0":rs.getString("flag_pbt"));
					h.put("flag_semak", rs.getString("flag_semak")==null?"0":rs.getString("flag_semak"));
					h.put("flag_belukar", rs.getString("flag_belukar")==null?"0":rs.getString("flag_belukar"));
					h.put("flag_hutan", rs.getString("flag_hutan")==null?"0":rs.getString("flag_hutan"));
					h.put("flag_terbiar", rs.getString("flag_terbiar")==null?"0":rs.getString("flag_terbiar"));
					h.put("flag_lapang", rs.getString("flag_lapang")==null?"0":rs.getString("flag_lapang"));
					
					h.put("nama_pelapor", rs.getString("nama_pelapor")==null?"":rs.getString("nama_pelapor"));
					
					h.put("id_pelapor", rs.getString("id_pelapor")==null?"":rs.getString("id_pelapor"));
					h.put("user_name", rs.getString("user_name")==null?"":rs.getString("user_name"));
					
					previousMaklumatTanahTerperinci.addElement(h);
				}
			
			}
			finally {
				if(db != null) db.close();
			}
		
	}//close 
	
}//close class
