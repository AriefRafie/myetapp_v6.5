package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class BantahanPampasanAgensi {
	 static Logger myLogger = Logger.getLogger(BantahanPampasanAgensi.class);
	 private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");	
	 
		private static Vector listcarian = null;
		private static Vector listMaklumatTanahWithSiasatan = null;
		
		public static Vector getListCarian(String userIdNegeri){
			return listcarian;
		}
		
		public Vector getListMaklumatTanahWithSiasatan(){
			return listMaklumatTanahWithSiasatan;
		}
		
		public static Vector getListPermohonan(String userIdNegeri)throws Exception {
			 
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
		    		sql +="AND p.id_status IN (184,186,187,199,200,201,202,203,204,205)";
		    		
		    		// ADDED BY ELLY 14 JUNE 2010
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    		}
		    		
		    		sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
		    		myLogger.info("DEFAUL :: "+sql);
		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);
		    			
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
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '52' ";
	    		sql +="AND p.id_status IN (184,186,187,199,200,201,202,203,204,205)";
	    		
	    		// ADDED BY ELLY 14 JUNE 2010
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    		}
	     
	    		//NO FAIL
				if (carianNofail != "" && carianNofail != null) {
					if (!nofail.equals("")) {
						sql = sql + " AND UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%'";
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
		 
	public void setListMaklumatTanahWithSiasatan(String id_permohonan,String lot) throws Exception{
		
		listMaklumatTanahWithSiasatan = new Vector();
		
		Db db = null;
		listMaklumatTanahWithSiasatan.clear();
		String sql = "";
		String noLOT = lot.trim();
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql =  " SELECT DISTINCT m.catatan, p.id_permohonan, ls.keterangan AS unit1,lt.keterangan AS unit2, m.id_hakmilik, m.id_negeri, ";
				sql += " n.nama_negeri, m.id_jenishakmilik, m.id_daerah, m.id_mukim,mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, m.no_hakmilik, ";
				sql += " m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar,m.tarikh_luput, m.tempoh_luput, m.lokasi, m.syarat_nyata, ";
				sql += " m.syarat_khas, m.sekatan_hak, m.sekatan_kepentingan,m.no_syit, jh.keterangan AS jenis_hakmilik, ";
				sql += " m.id_kategoritanah, m.flag_ambil_segera, pg.nama_pegawai, b.status_bantahan_ap, st.keterangan, pb.flag_bayar_bantahan, ss.id_siasatan ";
				sql += " FROM tblpptpermohonan p,tblrujlot lt,tblrujluas ls,tblrujmukim mk,tblrujnegeri n,tblppthakmilik m, "; 
				sql += " tblrujjenishakmilik jh,tblpptsiasatan ss,tblrujpegawai pg, tblpptbantahan b, tblrujstatus st, Tblppthakmilikpb pb ";  
				sql += " WHERE m.id_permohonan = p.id_permohonan(+) AND m.id_negeri = n.id_negeri "; 
				sql += " AND ls.id_luas(+) = m.id_unitluaslot AND m.id_jenishakmilik = jh.id_jenishakmilik(+) ";  
				sql += " AND ss.id_hakmilik(+) = m.id_hakmilik AND m.id_lot = lt.id_lot(+) ";
				sql += " AND m.id_mukim = mk.id_mukim(+) AND m.id_pegawai = pg.id_pegawai(+) AND M.ID_HAKMILIK = B.ID_HAKMILIK ";
				sql += " AND ss.id_siasatan IS NOT NULL AND B.STATUS_BANTAHAN_AP IS NOT NULL ";
				sql += " AND SS.ID_SIASATAN = (SELECT MAX(ID_SIASATAN) FROM TBLPPTSIASATAN WHERE ID_PERMOHONAN = P.ID_PERMOHONAN AND ID_PERMOHONAN = '"+id_permohonan+"') ";
				sql += " AND b.status_bantahan_ap = st.id_status AND b.status_bantahan_ap IN (201,203,204) ";
				sql += " AND m.id_hakmilik = pb.id_hakmilik AND p.id_Permohonan = '"+id_permohonan+"' ";
				
				//NO LOT
				if (lot != "" && lot != null) {
					if (!noLOT.equals("")) {
						sql += " AND UPPER(m.no_lot) LIKE '%" + noLOT.toUpperCase() + "%'";
					}
				}//close if nolot
				
				sql += " ORDER BY m.no_lot asc, mk.nama_mukim asc";
				
				myLogger.info("SQL senarai HAKmilik :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
					h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
					h.put("unitluaslot", rs.getString("unit1")==null?"-":rs.getString("unit1"));			
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
					h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"-":rs.getString("nama_mukim"));
					h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
					h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
					
					if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
						
						double luasAmbil = rs.getDouble("luas_ambil");
						String LA = Utils.formatLuasHM(luasAmbil);
						h.put("luas_ambil",LA);
								
					}else{
						h.put("luas_ambil","0");
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

					if(rs.getString("id_kategoritanah") != null && rs.getString("id_kategoritanah") != ""){						
						if(rs.getString("id_kategoritanah").equals("2")){	
							
							if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
								double luasAmbil = rs.getDouble("luas_ambil") * 10000;
								String LAH = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAH);
							}else{
								h.put("nilaiTanah","0");
							}
							
						}else{						
							if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
								double luasAmbil = rs.getDouble("luas_ambil");
								String LAM = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAM);
							}else{
								h.put("nilaiTanah","0");
							}						
						}			
					}else{
						h.put("nilaiTanah","0");
					}
					
					
					h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"-":rs.getString("id_kategoritanah"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
					h.put("no_lot", rs.getString("no_lot")==null?"-":rs.getString("no_lot"));
					h.put("luas_lot", rs.getString("luas_lot")==null?"":rs.getDouble("luas_lot"));
					h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
					h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
					
					h.put("tarikh_daftar", rs.getDate("tarikh_daftar")==null?"":Format.format(rs.getDate("tarikh_daftar")));
					h.put("tarikh_luput", rs.getDate("tarikh_luput")==null?"":Format.format(rs.getDate("tarikh_luput")));
					h.put("tempoh_luput", rs.getString("tempoh_luput")==null?"":rs.getString("tempoh_luput"));
					
					h.put("lokasi", rs.getString("lokasi")==null?"":rs.getString("lokasi"));
					h.put("syarat_nyata", rs.getString("syarat_nyata")==null?"":rs.getString("syarat_nyata"));
					h.put("syarat_khas", rs.getString("syarat_khas")==null?"":rs.getString("syarat_khas"));
					
					h.put("sekatan_hak", rs.getString("sekatan_hak")==null?"":rs.getString("sekatan_hak"));
					h.put("sekatan_kepentingan", rs.getString("sekatan_kepentingan")==null?"":rs.getString("sekatan_kepentingan"));
					h.put("no_syit", rs.getString("no_syit")==null?"":rs.getString("no_syit"));
					h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"":rs.getString("jenis_hakmilik"));
					h.put("status_bantahan_ap", rs.getString("status_bantahan_ap")==null?"":rs.getString("status_bantahan_ap"));
					h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
					h.put("flag_bayar_bantahan", rs.getString("flag_bayar_bantahan")==null?"":rs.getString("flag_bayar_bantahan"));
					h.put("id_siasatan", rs.getString("id_siasatan")==null?"":rs.getString("id_siasatan"));
										
					listMaklumatTanahWithSiasatan.addElement(h);
					bil++;	
				}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}//close setListMaklumatTanahWithSiasatan	 
}
