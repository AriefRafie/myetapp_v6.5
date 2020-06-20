package ekptg.model.ppt;
/*
 * @author 
 * NORZAILY BINTI JASMI
 */
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class SementaraHakmilik {
	static Logger myLogger = Logger.getLogger(SementaraHakmilik.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static  Vector listcarian = null;
	private  Vector maklumatTanah = new Vector();
	
	public static  Vector getListCarian(){
		return listcarian;
	}
	public Vector getMaklumatTanah(){
		return maklumatTanah;
	}
	@SuppressWarnings("unchecked")
	public static Vector getListPermohonan(String userIdNegeri)throws Exception {
		 
	    Db db = null;
	    String sql = "";	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	     
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, p.flag_semak ";
	    		sql +=", p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt, p.FLAG_STATUS_ONLINE ";
	    		sql +=", p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	    		sql +=" FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +=" AND p.id_status = s.id_status ";
	    		sql +=" AND f.id_suburusan = '53' ";
	    		sql +=" AND p.id_status IN (127,11,16,138)";	
	    		
	    		// ADDED BY ELLY 14 JUN 2010
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			sql += " AND f.id_negeri ='"+userIdNegeri+"'";
	    		}	    		
	    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	    		
	    		myLogger.info("DEFAULT :: "+sql);
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
	    			h.put("FLAG_STATUS_ONLINE", rs.getString("FLAG_STATUS_ONLINE")== null?"":rs.getString("FLAG_STATUS_ONLINE"));
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
	    			
			    	if(rs.getString("flag_semak") != null && rs.getString("flag_semak") != ""){
	    				
	    				if(rs.getString("flag_semak").equals("1")){
	    					h.put("status_fail","<b>TUNGGU PENGESAHAN</b>");
	    				}else if(rs.getString("flag_semak").equals("2")){
	    					h.put("status_fail","<b>TELAH DISAHKAN</b>");
	    				}else{
	    					h.put("status_fail","PENDAFTARAN");
	    				}
	    				
	    			}else{
	    				h.put("status_fail","PENDAFTARAN");
	    			}
	    			
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
	public static void setListCarianSementara(String carianNofail, String carianTarikh, String status,String userIdNegeri)throws Exception {
		
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
	      
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, "; 
	    		sql +=" su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, p.flag_semak, p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt, ";
	    		sql +=" p.FLAG_STATUS_ONLINE ";
			    sql +=" FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +=" AND p.id_status = s.id_status ";
	    		sql +=" AND f.id_suburusan = '53' ";
	    		sql +="AND p.id_status IN (127,11,16,138)";	
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
	    		myLogger.info("SQL setListCarianSementara :: "+sql);
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;
	    		int bil = 1;

	    		while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("bil", bil);
			    	h.put("FLAG_STATUS_ONLINE", rs.getString("FLAG_STATUS_ONLINE")== null?"":rs.getString("FLAG_STATUS_ONLINE"));
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
			    	
			    	if(rs.getString("flag_semak") != null && rs.getString("flag_semak") != ""){
	    				
	    				if(rs.getString("flag_semak").equals("1")){
	    					h.put("status_fail","<b>TUNGGU PENGESAHAN</b>");
	    				}else if(rs.getString("flag_semak").equals("2")){
	    					h.put("status_fail","<b>TELAH DISAHKAN</b>");
	    				}else{
	    					h.put("status_fail","PENDAFTARAN");
	    				}
	    				
	    			}else{
	    				h.put("status_fail","PENDAFTARAN");
	    			}

			    	if(rs.getString("no_fail") == null){
			    		h.put("no_fail","BELUM DISAHKAN");
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

	public void setMaklumatTanah(String idHakmilik) throws Exception {
		Db db = null;
		maklumatTanah.clear();
		String sql = "";
	
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql =  " SELECT DISTINCT hk.id_hakmilik, ls.id_luas as id_unitluaslot, lam.id_luas as id_unitluasambil, p.id_permohonan, jhk.id_jenishakmilik, n.id_negeri, "; 
			sql += " hk.id_lot, d.id_daerah, mk.id_mukim, hk.luas_ambil, hk.no_hakmilik, hk.no_lot, ";
			sql += " hk.luas_lot, hk.no_pt, n.nama_negeri, d.nama_daerah, ls.keterangan, mk.nama_mukim, jhk.keterangan as jenis_hakmilik, hk.catatan, hk.seksyen, ";  
			sql += " hk.tarikh_daftar, hk.tarikh_luput, hk.tempoh_luput, ";
			sql += " hk.lokasi,hk.syarat_nyata,hk.syarat_khas,hk.sekatan_hak,hk.sekatan_kepentingan,hk.no_syit,hk.id_kategoritanah, ";
			sql += " mk.nama_mukim, d.nama_daerah, n.nama_negeri, kt.keterangan as kategori_tanah, hk.id_daerahpenggawa, ";
			sql += " hk.status_borangl, hk.tarikh_terima_hm, ";
			sql += " hk.flag_jenis_rizab, hk.nama_lain_rizab, hk.no_warta_rizab, hk.tarikh_warta_rizab, hk.id_pegawai, ";
			sql += " hk.id_unitluaslot_convert, hk.id_unitluasambil_convert, hk.nama_luas_asal, hk.nama_luas_ambil, q.tempoh_Pendudukan,q.unit_Tempoh,q.tarikh_mula,q.tarikh_akhir";
			sql += " FROM Tblppthakmilik hk, Tblrujjenishakmilik jhk, Tblrujmukim mk, Tblrujluas ls, Tblrujdaerah d, "; 
			sql += " Tblrujnegeri n, Tblpptpermohonan p, Tblrujkategori kt, Tblrujluas lam , tblpptborangq q ";
			sql += " WHERE n.id_negeri(+) = hk.id_negeri "; 
			sql += " AND d.id_daerah(+) = hk.id_daerah "; 
			sql += " AND ls.id_luas(+) = hk.id_unitluaslot ";  
			sql += " AND lam.id_luas(+) = hk.id_unitluasambil ";  
			sql += " AND mk.id_mukim(+) = hk.id_mukim "; 
			sql += " AND kt.id_kategori(+) = hk.id_kategoritanah "; 
			sql += " AND jhk.id_jenishakmilik(+) = hk.id_jenishakmilik ";  
			sql += " AND p.id_permohonan = hk.id_permohonan " ;
			//sql += " AND p.id_permohonan = q.id_permohonan ";  
			sql += " AND HK.ID_HAKMILIK = q.ID_HAKMILIK(+) " ;
			sql += " AND hk.id_hakmilik = '"+idHakmilik+"'";
			myLogger.info("setMaklumatTanah >> "+sql);
			ResultSet rs = stmt.executeQuery(sql);
	
			Hashtable h;
		
			while(rs.next()) {
			h = new Hashtable();
			h.put("id_daerahpenggawa", rs.getString("id_daerahpenggawa")==null?"":rs.getString("id_daerahpenggawa"));
			h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
			h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
			h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
			h.put("id_luasLot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
			h.put("id_unitluasambil", rs.getString("id_unitluasambil")==null?"":rs.getString("id_unitluasambil"));
			h.put("id_lot", rs.getString("id_lot")==null?"":rs.getString("id_lot"));
			h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
			h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
			h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
			h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
			h.put("status_borangl", rs.getString("status_borangl")==null?"":rs.getString("status_borangl"));
			h.put("tarikh_terima_hm", rs.getDate("tarikh_terima_hm")==null?"":Format.format(rs.getDate("tarikh_terima_hm")));
			
			h.put("flag_jenis_rizab", rs.getString("flag_jenis_rizab")==null?"":rs.getString("flag_jenis_rizab"));
			h.put("nama_lain_rizab", rs.getString("nama_lain_rizab")==null?"":rs.getString("nama_lain_rizab"));
			h.put("tarikh_warta_rizab", rs.getDate("tarikh_warta_rizab")==null?"":Format.format(rs.getDate("tarikh_warta_rizab")));
			h.put("no_warta_rizab", rs.getString("no_warta_rizab")==null?"":rs.getString("no_warta_rizab"));
			
			h.put("id_unitluaslot_convert", rs.getString("id_unitluaslot_convert")==null?"":rs.getString("id_unitluaslot_convert"));
			h.put("id_unitluasambil_convert", rs.getString("id_unitluasambil_convert")==null?"":rs.getString("id_unitluasambil_convert"));
			h.put("nama_luas_asal", rs.getString("nama_luas_asal")==null?"":rs.getString("nama_luas_asal"));
			h.put("nama_luas_ambil", rs.getString("nama_luas_ambil")==null?"":rs.getString("nama_luas_ambil"));
			
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
			
			if(rs.getString("id_kategoritanah") != null && rs.getString("id_kategoritanah") != ""){
				
				if(rs.getString("id_kategoritanah").equals("2")){
					h.put("unitByKategori", "Hektar");
				}else{
					h.put("unitByKategori", "Meter Persegi");
				}			
			}else{
				h.put("unitByKategori", "");
			}
			
			h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
			h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
			h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
			h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
			h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));			
			h.put("tarikh_daftar", rs.getDate("tarikh_daftar")==null?"":Format.format(rs.getDate("tarikh_daftar")));
			h.put("tarikh_luput", rs.getDate("tarikh_luput")==null?"":Format.format(rs.getDate("tarikh_luput")));
			h.put("tempoh_luput", rs.getString("tempoh_luput")==null?"":rs.getString("tempoh_luput"));			
			h.put("lokasi", rs.getString("lokasi")==null?"":rs.getString("lokasi"));
			h.put("syarat_nyata", rs.getString("syarat_nyata")==null?"":rs.getString("syarat_nyata"));
			h.put("syarat_khas", rs.getString("syarat_khas")==null?"":rs.getString("syarat_khas"));			
			h.put("sekatan_hak", rs.getString("sekatan_hak")==null?"":rs.getString("sekatan_hak"));
			h.put("sekatan_kepentingan", rs.getString("sekatan_kepentingan")==null?"":rs.getString("sekatan_kepentingan"));
			h.put("no_syit", rs.getString("no_syit")==null?"":rs.getString("no_syit"));			
			h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));
			
			h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
			h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
			h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
			h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"":rs.getString("jenis_hakmilik"));
			h.put("kategori_tanah", rs.getString("kategori_tanah")==null?"":rs.getString("kategori_tanah"));
			
			h.put("tempoh_Pendudukan", rs.getString("tempoh_Pendudukan")==null?"":rs.getString("tempoh_Pendudukan"));
			h.put("unit_Tempoh", rs.getString("unit_Tempoh")==null?"":rs.getString("unit_Tempoh"));
			h.put("tarikh_mula", rs.getDate("tarikh_mula")==null?"":Format.format(rs.getDate("tarikh_mula")));
			h.put("tarikh_akhir", rs.getDate("tarikh_akhir")==null?"":Format.format(rs.getDate("tarikh_akhir")));
			
			maklumatTanah.addElement(h);
		}
		}
		finally {
			if(db != null) db.close();
		}
		
	}//close setlistpohon
	
	
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
	    		sql +=", p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '53' ";
	    		sql +="AND p.id_status IN (127,16)";
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
	public static Vector getListPermohonanHakmilik(String userIdNegeri)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +="AND p.id_status = s.id_status ";
		    		sql +="AND f.id_suburusan = '53' ";
		    		sql +="AND p.id_status IN (127,16)";
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
		    	
	}//close getListPermohonanHakmilik
	
}
