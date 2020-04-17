package ekptg.model.ppt;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.EkptgCache;
import ekptg.helpers.Utils;

public class BantahanAgensiDaftarOnline extends EkptgCache implements Serializable  {
	 static Logger myLogger = Logger.getLogger(BantahanAgensiDaftarOnline.class);
	 private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");	 
	 
	 private Vector listMaklumatTanah = null;
	 private Vector listMaklumatTanahBantahan = null;
	 public Vector getMaklumatBantahan = null;
	 @SuppressWarnings("unchecked")
	 private static  Vector listCarian = new Vector();
	 public Vector listPemohonAP = null;
	 public Vector listPBonline = null;
	 public Vector listMaklumatAgensiBantahan = null;
	 public Vector listAPonlineInternal = null;
	 public Vector listMaklumatAgensiBantahanOnlineInternal = null;
	 public Vector listMaklumatTanahByStatus = null;
	 
	 public Vector getListMaklumatTanah(){
			return listMaklumatTanahByStatus;
	}
	 
	 public Vector getListMaklumatTanahBantahan(){
			return listMaklumatTanahBantahan;
	}
	 
	 public Vector getMaklumatAgensi_DgnBantahan(){
		 return listMaklumatAgensiBantahan;
	 }
	 
	 @SuppressWarnings("unchecked")
	 public static  Vector getListCarian(){
			return listCarian;
	 }
	 
	 public Vector getMaklumatAgensi_DgnBantahanOnlineInternal(){
		 return listMaklumatAgensiBantahanOnlineInternal;
	 }
	 
	 
	 
	public Hashtable getHakmilikPortal(String id_hakmilik) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();		      
	      sql =  " SELECT C.NO_HAKMILIK,D.NAMA_KEMENTERIAN,D.ID_KEMENTERIAN,C.JUMLAH_AWARD,E.ID_AGENSI,E.NAMA_AGENSI,E.ALAMAT1,E.ALAMAT2,E.ALAMAT3,E.POSKOD,E.ID_NEGERI, ";	
	      sql += " CASE WHEN C.NO_LOT IS NOT NULL AND C.NO_PT IS NULL THEN C.NO_LOT ";
	      sql += " WHEN C.NO_LOT IS NULL AND C.NO_PT IS NULL THEN LT.KETERANGAN || C.NO_PT ";
	      sql += " WHEN C.NO_LOT IS NULL AND C.NO_PT IS NULL THEN LT.KETERANGAN || C.NO_PT ";	
	      sql += " WHEN C.NO_LOT IS NULL AND C.NO_PT IS NOT NULL THEN LT.KETERANGAN || C.NO_PT  ";
	      sql += " WHEN C.NO_LOT IS NOT NULL AND C.NO_PT IS NOT NULL THEN LT.KETERANGAN || C.NO_PT || CHR(32) || CHR(40) || C.NO_LOT || CHR(41) ";
	      sql += " ELSE '' ";	
	      sql += " END AS NO_LOTPT ";
	      sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, ";	     
	      sql += " TBLRUJLOT LT,TBLRUJKEMENTERIAN D,TBLRUJAGENSI E ";
	      sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
	      sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
	      sql += " AND C.ID_LOT = LT.ID_LOT(+) ";
	      sql += " AND A.ID_KEMENTERIAN = D.ID_KEMENTERIAN(+) AND B.ID_AGENSI = E.ID_AGENSI(+) ";
	      sql += " AND C.ID_HAKMILIK = '"+id_hakmilik+"' ";
     
//	      myLogger.info("TEST 123 :: "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h = new Hashtable();
	      if (rs.next()) {
		    	h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
		    	h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN")==null?"":rs.getString("NAMA_KEMENTERIAN"));
		    	h.put("id_kementerian", rs.getString("ID_KEMENTERIAN")==null?"":rs.getString("ID_KEMENTERIAN"));
		    	//h.put("jumlah_award", rs.getString("JUMLAH_AWARD")==null?"":Double.parseDouble(rs.getString("JUMLAH_AWARD")));   
		    	h.put("jumlah_award",Utils.isNull(rs.getString("JUMLAH_AWARD")));   		    	
		    	h.put("id_agensi", rs.getString("ID_AGENSI")==null?"":rs.getString("ID_AGENSI"));
		    	h.put("nama_agensi", rs.getString("NAMA_AGENSI")==null?"":rs.getString("NAMA_AGENSI"));		    	
		    	h.put("alamat1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
		    	h.put("alamat2", rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
		    	h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
		    	h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
		    	h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
		    	h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
		    	h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
	      }
	      return h;
	    }
	    finally {
	      if (db != null) db.close();
	    }	
	}

//	public void setListMaklumatTanah(String id_permohonan,String usid,String lot) throws Exception{
//		
//		 listMaklumatTanah = new Vector();
//			
//			Db db = null;
//			listMaklumatTanah.clear();
//			String sql = "";
//			String noLOT = lot.trim();
//			
//			try {
//					db = new Db();
//					Statement stmt = db.getStatement();
//					
//					sql =  " SELECT C.ID_FAIL,D.ID_PERMOHONAN,E.ID_HAKMILIK,E.NO_HAKMILIK,G.ID_HAKMILIKPB,H.NAMA_MUKIM,E.LUAS_AMBIL,E.ID_UNITLUASLOT_CONVERT,I.KETERANGAN, ";
//					sql += " CASE ";
//					sql += " WHEN E.NO_LOT IS NOT NULL AND E.NO_PT IS NULL THEN E.NO_LOT ";
//					sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NULL THEN LT.KETERANGAN || E.NO_PT "; 
//					sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NULL THEN LT.KETERANGAN || E.NO_PT  ";
//					sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NOT NULL THEN LT.KETERANGAN || E.NO_PT  ";       
//					sql += " WHEN E.NO_LOT IS NOT NULL AND E.NO_PT IS NOT NULL THEN LT.KETERANGAN || E.NO_PT || CHR(32) || CHR(40) || E.NO_LOT || CHR(41) ";
//					sql += " ELSE '' ";
//					sql += " END AS NO_LOTPT ";
//					sql += " FROM USERS A,USERS_ONLINE B,TBLPFDFAIL C, "; 
//					sql += " TBLPPTPERMOHONAN D,TBLPPTHAKMILIK E,TBLPPTPIHAKBERKEPENTINGAN F, ";
//					sql += " TBLPPTHAKMILIKPB G,TBLRUJKEMENTERIAN H,TBLRUJSUBURUSAN SU, ";       
//					sql += " TBLRUJSTATUS ST,TBLRUJLOT LT,TBLRUJLUAS I,TBLRUJMUKIM H ";
//					sql += " WHERE A.USER_ID = B.USER_ID AND C.ID_SUBURUSAN = SU.ID_SUBURUSAN ";
//					sql += " AND C.ID_FAIL = D.ID_FAIL AND D.ID_PERMOHONAN = E.ID_PERMOHONAN "; 
//					sql += " AND E.ID_HAKMILIK = G.ID_HAKMILIK AND F.ID_PIHAKBERKEPENTINGAN = G.ID_PIHAKBERKEPENTINGAN ";
//					sql += " AND C.ID_KEMENTERIAN = H.ID_KEMENTERIAN AND B.NO_KP_BARU = F.NO_PB(+) AND D.ID_STATUS = ST.ID_STATUS ";       
//					sql += " AND NVL (E.FLAG_PENARIKAN_KESELURUHAN, ' ') <> 'Y' ";
//					sql += " AND NVL (E.FLAG_PEMBATALAN_KESELURUHAN, ' ') <> 'Y' ";					
//					sql += " AND E.ID_LOT = LT.ID_LOT(+) AND I.ID_LUAS(+) = E.ID_UNITLUASAMBIL   "; 
//					sql += " AND H.ID_MUKIM(+) = E.ID_MUKIM AND su.id_suburusan = '52' ";
//					sql += " AND A.USER_ID = '"+ usid +"' ";	
//					sql += " AND D.ID_PERMOHONAN = '"+ id_permohonan +"' ";	
//	
//					//NO LOT
//					if (lot != "" && lot != null) {
//						if (!noLOT.equals("")) {
//							sql += " AND (UPPER(E.NO_LOT) LIKE '%" + noLOT.toUpperCase() + "%' OR  UPPER(E.NO_PT) LIKE '%" + noLOT.toUpperCase() + "%' OR UPPER(LT.KETERANGAN) LIKE '%" + noLOT.toUpperCase() + "%')";
//						}
//					}//close if nolot										
//					
//					sql += " ORDER BY NO_LOTPT ASC, H.NAMA_MUKIM asc";
//					myLogger.info("SQL HAKMILIK ONLINE  :: "+sql);
//					ResultSet rs = stmt.executeQuery(sql);	
//					Hashtable h;
//					int bil = 1;
//					
//					while (rs.next()){
//						h = new Hashtable();
//						h.put("bil", bil);
//						h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
//						h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
//						h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
//						h.put("nama_mukim", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));						
//						h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
//						if(rs.getString("LUAS_AMBIL") != null && rs.getString("LUAS_AMBIL") != ""){
//							
//							double luasAmbil = rs.getDouble("LUAS_AMBIL");
//							String LA = Utils.formatLuasHM(luasAmbil);
//							h.put("luas_ambil",LA);
//									
//						}else{
//							h.put("luas_ambil","0");
//						}
//						
//						if(rs.getString("ID_UNITLUASLOT_CONVERT") != null){
//							
//							if(rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")){
//								h.put("unitByKategori", "Hektar");
//							}else{
//								h.put("unitByKategori", "Meter Persegi");
//							}			
//						}else{
//							h.put("unitByKategori", "");
//						}
//			
//						listMaklumatTanah.addElement(h);
//						bil++;	
//						
//					}
//				//return list;
//			}finally {
//				if(db != null) db.close();
//			}
//		}//close setListMaklumatTanah
	
	
	@SuppressWarnings("unchecked")
	public void setListMaklumatTanah(String id_permohonan,String usid,String lot) throws Exception{
	
	 listMaklumatTanah = new Vector();
		
		Db db = null;
		listMaklumatTanah.clear();
		String sql = "";
		String noLOT = lot.trim();
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql =  " SELECT DISTINCT p.no_rujukan_ptg, p.id_status, f.no_fail, m.catatan, ";
				sql += " p.id_permohonan, ls.keterangan AS unit1, ";
				sql += " lt.keterangan AS unit2, m.id_hakmilik, m.id_negeri, ";
				sql += " n.nama_negeri, "; 
				sql += " (SELECT COUNT (a.id_hakmilikpb) ";
				sql += " FROM tblppthakmilikpb a,tblpptpihakberkepentingan b ";       
				sql += " WHERE a.id_pihakberkepentingan = b.id_pihakberkepentingan ";
				sql += " AND a.id_hakmilik(+) = m.id_hakmilik ";
				sql += " AND a.id_jenispb NOT IN (40, 41, 42)) AS totalpb, ";
				sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, "; 
				sql += " m.id_unitluaslot, m.luas_ambil, m.no_hakmilik, m.no_lot, ";
				sql += " m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, ";       
				sql += " m.tempoh_luput, jh.kod_jenis_hakmilik, m.lokasi, ";
				sql += " m.syarat_nyata, m.syarat_khas, m.sekatan_hak, ";
				sql += " m.sekatan_kepentingan, m.no_syit, "; 
				sql += " jh.keterangan AS jenis_hakmilik, m.id_kategoritanah, ";
				sql += " m.flag_ambil_segera, d.nama_daerah, m.flag_borangl, ";       
				sql += " m.status_borangl, m.tarikh_terima_hm, ";
				sql += " u.user_name AS nama_pegawai, jh.status_hakmilik, ";					
				sql += " m.flag_jenis_rizab, m.nama_lain_rizab, m.no_warta_rizab, "; 
				sql += " m.tarikh_warta_rizab, m.no_subjaket, m.id_pegawai, ";
				sql += " m.id_unitluasambil_convert, m.id_unitluaslot_convert, ";	
				sql += " CASE ";												
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt "; 
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt ";
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL ";       
				sql += " THEN lt.keterangan || m.no_pt || CHR (32) || CHR (40)|| m.no_lot || CHR (41) ";
				sql += " ELSE '' ";					
				sql += " END AS no_lotpt, bts.keterangan as status_bantahan "; 
				sql += " FROM tblpfdfail f,tblpptpermohonan p, ";
				sql += " tblrujlot lt,tblrujluas ls, ";	
				sql += " tblrujmukim mk,tblrujnegeri n, ";												
				sql += " tblppthakmilik m,tblrujjenishakmilik jh, ";				
				sql += " tblrujdaerah d,users u, tblpptbantahan bt, tblrujstatus bts ";
				sql += " WHERE m.id_permohonan = p.id_permohonan(+) ";	
				sql += " AND m.id_negeri = n.id_negeri ";												
				sql += " AND p.id_fail = f.id_fail ";				
				sql += " AND m.id_daerah = d.id_daerah(+) ";	
				sql += " AND ls.id_luas(+) = m.id_unitluaslot ";												
				sql += " AND m.id_pegawai = u.user_id(+) ";				
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+) ";
				sql += " AND m.id_lot = lt.id_lot(+) ";	
				sql += " AND m.id_mukim = mk.id_mukim(+) ";												
				sql += " AND NVL (m.flag_pembatalan_keseluruhan, 0) <> 'Y' ";
				sql += " AND NVL (m.flag_penarikan_keseluruhan, 0) <> 'Y' ";	
				sql += " AND bt.id_hakmilik(+) = m.id_hakmilik ";
				sql += " and bt.status_bantahan_ap = bts.id_status(+) ";
				sql += " AND p.id_permohonan = '"+id_permohonan+"' ";
                
//				myLogger.info("SQL HAKMILIK ONLINE  :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("status_bantahan", rs.getString("status_bantahan")==null?"BELUM DIDAFTAR":rs.getString("status_bantahan"));	
					h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));					
					h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
					h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
//					h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
					h.put("nama_mukim", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));						
					h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
					if(rs.getString("LUAS_AMBIL") != null && rs.getString("LUAS_AMBIL") != ""){
						
						double luasAmbil = rs.getDouble("LUAS_AMBIL");
						String LA = Utils.formatLuasHM(luasAmbil);
						h.put("luas_ambil",LA);
								
					}else{
						h.put("luas_ambil","0");
					}
					
					if(rs.getString("ID_UNITLUASLOT_CONVERT") != null){
						
						if(rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")){
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
	
	public void setMaklumatAgensi_DgnBantahan(String id_hakmilik) throws Exception {
		listMaklumatAgensiBantahan = new Vector();
			
			Db db = null;
			listMaklumatAgensiBantahan.clear();
			String sql = "";
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql =  " SELECT A.ID_FAIL,C.ID_HAKMILIK,C.NO_HAKMILIK,D.ID_BANTAHAN ";
					sql += " FROM TBLPFDFAIL A, ";
					sql += " TBLPPTPERMOHONAN B, ";
					sql += " TBLPPTHAKMILIK C, "; 
					sql += " TBLPPTBANTAHAN D ";
					sql += " WHERE A.ID_FAIL = B.ID_FAIL ";       
					sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
					sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK ";	
					sql += " AND NVL(C.FLAG_BANTAHAN,'0') = 1 ";
					sql += " AND C.ID_HAKMILIK = '"+id_hakmilik+"' ";	
					
					myLogger.info("SQL AGENSI BANTAHAN ONLINE  :: "+sql);
					
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
					
					while (rs.next()){
						h = new Hashtable();
						h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
						h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
						h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
						h.put("id_bantahan", rs.getString("ID_BANTAHAN")==null?"":rs.getString("ID_BANTAHAN"));
						listMaklumatAgensiBantahan.addElement(h);						
					}
				//return list;
			}finally {
				if(db != null) db.close();
			}
	}
	
	public Vector getMaklumatBantahan(String id_permohonan,String id_bantahan) throws Exception {		 							
		 Db db = null;
		 String sql = "";
		try{
				getMaklumatBantahan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT f.flag_semakan_online, f.tarikh_mohon, f.tarikh_borangn,f.status_bantahan, f.alasan, f.kepentingankeatas, c.flag_bantahan, "; 
				sql += " f.flag_penerima_pampasan, f.flag_bahagian_pampasan, f.flag_ukur_luas, ";
				sql += " F.FLAG_PAMPASAN,F.AMAUN_TUNTUTAN,F.FLAG_ONLINE,F.STATUS_BANTAHAN_AP ";
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, ";
				sql += " TBLPPTBANTAHAN F ";	
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIK = F.ID_HAKMILIK ";	
				sql += " AND B.ID_PERMOHONAN = '"+id_permohonan+"' ";
				sql += " AND F.ID_BANTAHAN = '"+id_bantahan+"' ";
				
				myLogger.info("SQL GETMAKLUMATBANTAHAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);

				Hashtable h;			    
		     while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("flag_semakan_online", rs.getString("flag_semakan_online")==null?"":rs.getString("flag_semakan_online"));
		    	h.put("status_bantahan_ap", rs.getString("STATUS_BANTAHAN_AP")==null?"":rs.getString("STATUS_BANTAHAN_AP"));
		    	h.put("tarikh_mohon", rs.getString("TARIKH_MOHON")==null?"-":sdf.format(rs.getDate("TARIKH_MOHON")));
		    	h.put("tarikh_borangn", rs.getString("TARIKH_BORANGN")==null?"-":sdf.format(rs.getDate("TARIKH_BORANGN")));
		    	h.put("status_bantahan", rs.getString("STATUS_BANTAHAN")==null?"":rs.getString("STATUS_BANTAHAN"));
		    	h.put("alasan", rs.getString("ALASAN")==null?"":rs.getString("ALASAN"));
		    	h.put("kepentingankeatas", rs.getString("KEPENTINGANKEATAS")==null?"":rs.getString("KEPENTINGANKEATAS"));
		    	h.put("flag_bantahan", rs.getString("FLAG_BANTAHAN")==null?"":rs.getString("FLAG_BANTAHAN"));
		    	h.put("flag_penerima_pampasan", rs.getString("FLAG_PENERIMA_PAMPASAN")==null?"":rs.getString("FLAG_PENERIMA_PAMPASAN"));
		    	h.put("flag_bahagian_pampasan", rs.getString("FLAG_BAHAGIAN_PAMPASAN")==null?"":rs.getString("FLAG_BAHAGIAN_PAMPASAN"));
		    	h.put("flag_ukur_luas", rs.getString("FLAG_UKUR_LUAS")==null?"":rs.getString("FLAG_UKUR_LUAS"));
		    	h.put("flag_pampasan", rs.getString("FLAG_PAMPASAN")==null?"":rs.getString("FLAG_PAMPASAN"));			    	
		    	h.put("amaun_tuntutan", rs.getString("AMAUN_TUNTUTAN")==null?"":Double.parseDouble(rs.getString("AMAUN_TUNTUTAN")));
		    	h.put("flag_online", rs.getString("FLAG_ONLINE")==null?"":rs.getString("FLAG_ONLINE"));
		    	getMaklumatBantahan.addElement(h);
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getMaklumatBantahan;
}	
	
	
	
	// TRANSACTION ADD/EDIT/DELETE
	
	
	public String daftarBantahanPortal(String txdMohon, String txdBrgN,
			String txtNoLot, String txtNoHakmilik, String txtNama,
			String txtAlamat1, String txtAlamat2, String txtAlamat3,
			String txtPoskod, String txtKptgnAtasTnh, String txtAlasanBantahan,
			String usid, String jenis_pembantah, String ukuran_luas,
			String amaun_pampasan,String id_kementerian, String id_agensi, String terima_pampasan,
			String umpuk_pampasan, String id_hakmilik, String txtAmaunTuntutan, String id_permohonan) throws Exception {
			
		 	Db db = null;	 	
		 	Connection conn = null;
		    String sql = "";
		    String output = "";
		    Date now = new Date();
		    
	    	int id_stat = 80;		// ID_STATUS = 80 [BANTAHAN TAWARAN]
	    	String tahun = sdf.format(now);
	    	    
		    String no_bantahan = tahun+"-"+String.format("%06d",getSeqNoBantahan(id_stat));	
		    
		    try
		    {				    	  
		    	  long id_bantahan = DB.getNextID("TBLPPTBANTAHAN_SEQ");
		    	  String TM = "to_date('" + txdMohon + "','dd/MM/yyyy')";
		    	  String TBN = "to_date('" + txdBrgN + "','dd/MM/yyyy')";	

			      db = new Db();
			      //SET AUTOCOMMIT TO FALSE
		          conn = db.getConnection();
		          conn.setAutoCommit(false);
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
		    	
			      //TBLPPTBANTAHAN
			      r = new SQLRenderer();
			      r.add("id_bantahan",id_bantahan);
			      r.add("no_bantahan",no_bantahan);		      
			      r.add("jenis_pembantah",1);				// JENIS PEMBANTAH : 1 [PIHAK BERKEPENTINGAN] & 2 [AGENSI PEMOHON]
			      r.add("tarikh_mohon",r.unquote(TM) );		      
			      r.add("tarikh_borangn",r.unquote(TBN) );
			      r.add("alasan",txtAlasanBantahan);
			      r.add("kepentingankeatas",txtKptgnAtasTnh);
			      r.add("flag_penerima_pampasan",ukuran_luas);
			      r.add("flag_bahagian_pampasan",amaun_pampasan);
			      r.add("id_agensi",id_agensi);
			      r.add("id_kementerian",id_kementerian);
			      r.add("id_hakmilik",id_hakmilik);
			      r.add("flag_ukur_luas",terima_pampasan);
			      r.add("flag_pampasan",umpuk_pampasan);
			      r.add("status_bantahan_ap",199);				// 199 = DALAM PROSES - AGENSI
			      r.add("amaun_tuntutan",txtAmaunTuntutan);
			      r.add("flag_online",1);		// 1: FLAG PERMOHONAN ONLINE (PENDAFTARAN BANTAHAN)
			      r.add("id_masuk",usid);
			      r.add("tarikh_masuk",r.unquote("sysdate"));
			      r.add("id_kemaskini",usid);
			      r.add("tarikh_kemaskini",r.unquote("sysdate"));			      
			      sql = r.getSQLInsert("Tblpptbantahan");
//			      myLogger.info("INSERT PORTAL(AP) ::"+sql);
			      stmt.executeUpdate(sql);
			      
			      r.clear();
			      
			      //TBLPPTHAKMILIK
			      r.update("id_hakmilik", id_hakmilik);
			      
				  r.add("flag_bantahan",1); 						// 1 = YA
				  r.add("id_kemaskini",usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  sql = r.getSQLUpdate("Tblppthakmilik");
//				  myLogger.info("UPDATE ::-> "+sql);	
				  stmt.executeUpdate(sql);	
				  
			      r.clear();
			      
			      //TBLRUJSUBURUSANSTATUSBANTAHAN		      
				  r.add("id_permohonan",id_permohonan); 
				  r.add("id_hakmilik",id_hakmilik);
				  r.add("id_bantahan",id_bantahan);
				  r.add("id_suburusanstatus",16102106);			// ID_SUBURUSANSTATUS DALAM PROSES - AGENSI = 199
				  r.add("aktif",1);
				  r.add("id_masuk",usid);
				  r.add("tarikh_masuk",r.unquote("sysdate"));
				  r.add("id_kemaskini",usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));			  
			      sql = r.getSQLInsert("Tblrujsuburusanstatusbantahan");
//			      myLogger.info("INSERT TBLRUJSUBURUSANSTATUSBANTAHAN ::"+sql);
			      stmt.executeUpdate(sql);
				  
			      
			      output = ""+id_bantahan;
			      
			      
			      conn.commit();		      		
		    } catch (SQLException se) { 
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
		    } finally {
		      if (db != null) db.close();
		    }			
		    
		    return output;
		}	
	 

	public void updateStatusDalamProses(String id_permohonan, String usid) throws Exception {
	    Db db = null;
	    String sql = "";	    
	    try
	    {			  
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_permohonan", id_permohonan);		    
		    r.add("id_status", 181);	// 181=DALAM PROSES
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblpptpermohonan");	
//		    myLogger.info("EDIT IDSTATUS TBLPPTPERMOHONAN = "+sql);
		    stmt.executeUpdate(sql);		    
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}				
	}
	
	public void updateFlagOnline(String id_bantahan, String usid, String id_hakmilik) throws Exception {
	    Db db = null;
	    String sql = "";
//	    String sql2 = "";
	    try
	    {			  
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_bantahan", id_bantahan);		    
		    r.add("flag_online", 2);	// 2 - FLAG HANTAR KE INTERNAL 
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
						
		    sql = r.getSQLUpdate("Tblpptbantahan");	
		    myLogger.info("EDIT FLAG ONLINE = "+sql);
		    stmt.executeUpdate(sql);
		    
		    r.clear();
		    
		    r.update("id_hakmilik", id_hakmilik);
		    r.add("flag_bantahan", 1);
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblppthakmilik");	
		    
		    stmt.executeUpdate(sql);	
		    
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}				
	}	

	
	public void simpanEditBantahan(String usid, String id_bantahan,String txdMohon, String txdBrgN, String txtKptgnAtasTnh,String ukuran_luas, 
			String amaun_pampasan, String terima_pampasan,String umpuk_pampasan, 
			String txtAmaunTuntutan,String txtAlasanBantahan,String id_status_bantahan) throws Exception {

	 	Db db = null;	 	
	 	Connection conn = null;
	    String sql = "";
	    Date now = new Date();
	    try
	    {		
//	    	  if (isNoResitExist(txtNoResit)) {
//	    		  throw new Exception("Rekod No. Resit "+ txtNoResit +" Telah Wujud.");
//	    	  }			    	  
	    	 
	    	  String TM = "to_date('" + txdMohon + "','dd/MM/yyyy')";
	    	  String TBN = "to_date('" + txdBrgN + "','dd/MM/yyyy')";

		      db = new Db();
		      //SET AUTOCOMMIT TO FALSE
	          conn = db.getConnection();
	          conn.setAutoCommit(false);
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
	    	
		      //TBLPPTBANTAHAN
		      r = new SQLRenderer();
		      r.update("id_bantahan",id_bantahan);		      
		      r.add("tarikh_mohon",r.unquote(TM) );		      
		      r.add("tarikh_borangn",r.unquote(TBN) );
		      r.add("kepentingankeatas",txtKptgnAtasTnh);		      
		      r.add("flag_penerima_pampasan",ukuran_luas);		      
		      r.add("flag_bahagian_pampasan",amaun_pampasan);		      
		      r.add("flag_ukur_luas",terima_pampasan);		      
		      r.add("flag_pampasan",umpuk_pampasan);
		      r.add("amaun_tuntutan",txtAmaunTuntutan);
		      r.add("alasan",txtAlasanBantahan);
		      r.add("id_kemaskini",usid);
		      r.add("tarikh_kemaskini",r.unquote("sysdate"));
		      sql = r.getSQLUpdate("Tblpptbantahan");
//		      myLogger.info("UPDATE ::"+sql);
		      stmt.executeUpdate(sql);		      		      	      		      
		      
		      if(id_status_bantahan.equals("1610250")){
		    	  
		    	  r.clear();
			      
				  r.update("id_bantahan", id_bantahan);		    
				  r.add("flag_online", 1);	// TUKAR KE ONLINE BALIK
				  r.add("status_bantahan_ap","199"); //Status DALAM PROSES
				  r.add("id_kemaskini",usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  sql = r.getSQLUpdate("Tblpptbantahan");	
				  stmt.executeUpdate(sql);	  
				  
		      }

			  conn.commit();	
		      
	    } catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Kemaskini Maklumat Bantahan:"+se.getMessage());
	    } finally {
	      if (db != null) db.close();
	    }		
		
	}	
	
	
	@SuppressWarnings("unchecked")
	public Vector setCarianFail(String carianStatus,String carianJenisHM,String carianNegeri,String carianNoFail,String carianTarikhMohon,
			String carianUrusan,String carianBilMohon,String carianNamaPB,String carianNoPB,String carianNoHM,String carianNoLot,
			String carianTujuan,String userId,String role)throws Exception {
	    Db db = null;
	    String sql = "";
	    listCarian.clear();
	    
	    if(carianTarikhMohon!=""){
	    	carianTarikhMohon = "to_date('" + carianTarikhMohon + "','dd/MM/yyyy')";
	    }	    
	    
	    try {
	      
	      db = new Db();
	      Statement stmt = db.getStatement();  	    
	      /*
	      sql = " SELECT DISTINCT us.user_id,p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, ";
	      sql +=" f.no_fail,p.tarikh_permohonan,su.nama_suburusan, ";
	      sql +=" k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
	      sql +=" p.flag_semak, p.no_rujukan_ptg, p.no_rujukan_ptd, ";
	      sql +=" f.id_suburusan, p.tarikh_kemaskini, p.no_rujukan_upt ";
	      sql +=" FROM tblpptpermohonan p,tblpfdfail f,tblrujsuburusan su,tblrujstatus s,tblrujkementerian k, ";
	      sql +=" tblppthakmilik l,users_kementerian uk,users us ";
	      sql +=" WHERE f.id_fail = p.id_fail AND uk.user_id = us.user_id ";
	      sql +=" and f.id_kementerian = uk.id_kementerian AND f.id_suburusan = su.id_suburusan ";
	      sql +=" AND f.id_kementerian = k.id_kementerian ";	
	      sql +=" and us.user_id = '"+userId+"' ";
	      sql +=" AND su.id_suburusan = '52' ";
	      
	      sql +=" AND ( ";
	      sql +=" SELECT DISTINCT P1.ID_PERMOHONAN FROM TBLPPTBORANGH H,TBLPPTPERMOHONAN P1,TBLPPTHAKMILIK HM,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB ";	
	      sql +=" WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN ";
	      sql +=" AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HM.ID_PERMOHONAN = P1.ID_PERMOHONAN ";
	      sql +=" AND H.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB AND P1.ID_PERMOHONAN = p.id_permohonan ";
	      sql +=" ) > 0  ";
	      
	      sql +=" AND p.id_status = s.id_status ";
	      sql +=" AND p.id_permohonan = l.id_permohonan ";
	      sql +=" AND NVL (l.flag_penarikan_keseluruhan, ' ') <> 'Y' ";
	      sql +=" AND NVL (l.flag_pembatalan_keseluruhan, ' ') <> 'Y' ";				      
	     
	      if(role.equals("online_kjpagensi")){
  			sql +="AND uk.id_agensi = p.id_agensi ";
  		  }
	      */
	      
	      sql = " SELECT DISTINCT us.user_id, p.id_status, p.id_permohonan, p.no_permohonan, "+
  				" f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, "+
  				" k.nama_kementerian, s.keterangan, p.tarikh_masuk, "+
  				" p.flag_semak, p.no_rujukan_ptg, p.no_rujukan_ptd, "+
  				" f.id_suburusan, p.tarikh_kemaskini, p.no_rujukan_upt "+
  				" FROM tblpptpermohonan p, "+
  				" tblpfdfail f, "+
  				" tblrujsuburusan su, "+
  				" tblrujstatus s, "+
  				" tblrujkementerian k, "+
  				" tblppthakmilik l, "+
  				" users_kementerian uk, "+
  				" tblppthakmilikpb hpb,tblpptborangh h, tblpptpihakberkepentingan pb, "+
  				" users us "+
  				" WHERE  f.id_fail = p.id_fail "+
  				" and l.id_hakmilik = hpb.id_hakmilik AND h.id_hakmilikpb = hpb.id_hakmilikpb and pb.id_pihakberkepentingan = hpb.id_pihakberkepentingan "+
  				" AND uk.user_id = us.user_id "+
  				" AND f.id_kementerian = uk.id_kementerian "+
  				" AND f.id_suburusan = su.id_suburusan "+
  				" AND f.id_kementerian = k.id_kementerian "+
  				" AND us.user_id = '"+userId+"' "+
  				" AND su.id_suburusan = '52' "+
  				" AND p.id_status = s.id_status "+
  				" AND p.id_permohonan = l.id_permohonan "+
  				" AND NVL (l.flag_penarikan_keseluruhan, ' ') <> 'Y' "+
  				" AND NVL (l.flag_pembatalan_keseluruhan, ' ') <> 'Y' ";
  		
		      if(role.equals("online_kjpagensi")){
		    	  sql +="AND uk.id_agensi = p.id_agensi ";
	    		}
	      
	    //NO FAIL
			if (!carianNoFail.equals("")) {
					sql += " AND (UPPER(f.no_fail) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
						" OR UPPER(p.no_rujukan_ptg) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
						" OR UPPER(p.no_permohonan_online) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
						" OR UPPER(p.no_rujukan_upt) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
						" OR UPPER(p.no_rujukan_ptd) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%'))";
			}//close if pemohon 
	
			if (!carianBilMohon.equals("")) {
				sql += " AND UPPER(p.no_permohonan) LIKE UPPER('%" + carianBilMohon.toUpperCase() + "%') ";
			}
			
			//TARIKH
			if (!carianTarikhMohon.equals("")) {
				sql = sql + " AND UPPER(p.tarikh_permohonan) = "+carianTarikhMohon;
			}//close tarikh  

  		//STATUS
			if (!carianStatus.equals("")) {
				sql = sql + " AND UPPER(s.id_status) = '"+carianStatus+"' ";
			}//close status
			
			//Urusan
			if (!carianUrusan.equals("")) {
				sql = sql + " AND UPPER(f.id_suburusan) = '"+carianUrusan+"'";
			}//close Urusan
			
			//Negeri Projek
			if (!carianNegeri.equals("")) {
				sql = sql + " AND UPPER(f.id_negeri) = '"+carianNegeri+"'";
			}//close Urusan
			
			//Nama Projek
			if (!carianTujuan.equals("")) {
				sql = sql + " AND UPPER(p.tujuan) LIKE UPPER('%"+carianTujuan+"%')";
			}//close Urusan
			
			//No hakmilik
	    	if (!carianNoHM.equals("")) {
	    		sql += " AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK " +
	    			   " WHERE UPPER(NO_HAKMILIK) LIKE UPPER('%"+carianNoHM+"%'))";
		    }
	    	
	    	//Nama PB
	    	if (!carianNamaPB.equals("")) {
				sql += " AND P.ID_PERMOHONAN IN (SELECT H.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB " +
		               " WHERE H.ID_HAKMILIK = HPB.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN" +
		               " AND UPPER(PB.NAMA_PB)  LIKE UPPER('%' ||'"+carianNamaPB.trim()+"'|| '%') ) ";								
			}
	    	
	    	//No PB
			if (!carianNoPB.equals("")) {
				sql += " AND P.ID_PERMOHONAN IN (SELECT H.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB "
		            +  " WHERE H.ID_HAKMILIK = HPB.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN" +
		               " AND UPPER(PB.NO_PB) LIKE UPPER('%"+carianNoPB+"%')";									
			}
	    	
			
			//no_lot/pt
	    	if (!carianNoLot.equals("")) {
			    sql += " AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK " +
			    	   " WHERE UPPER(NO_LOT) LIKE UPPER('%' ||'"+carianNoLot.trim()+"'|| '%') " +
			    	   " OR UPPER(NO_PT) LIKE UPPER('%' ||'"+carianNoLot.trim()+"'|| '%'))";					   
			}
			
	    	//Id jenishakmilik
	    	if (!carianJenisHM.equals("")) {
			    sql += " AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_JENISHAKMILIK = '"+carianJenisHM+"')";
	    	}
	    	
	      
			//sorting
			sql +=" ORDER BY p.id_status DESC, p.tarikh_kemaskini DESC ";

			myLogger.info("SQL CARIAN :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
  
			Hashtable h;
			int bil = 1;
			while (rs.next()) {					
    			h = new Hashtable();
    			h.put("bil", bil);		    			
    			h.put("id_suburusan", rs.getString("id_suburusan")== null?"":rs.getString("id_suburusan"));
    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));		    			
    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
    			h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));		    			
    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));		    			
    			if(rs.getString("id_suburusan") != null && rs.getString("id_suburusan") != ""){		    				
    				if(rs.getString("id_suburusan").equals("51")){
    					h.put("suburusan","SEKSYEN 4");
    				}else if(rs.getString("id_suburusan").equals("52")){
    					h.put("suburusan","SEKSYEN 8");
    				}else if(rs.getString("id_suburusan").equals("53")){
    					h.put("suburusan","PENGAMBILAN SEMENTARA");
    				}else{
    					h.put("suburusan","");
    				}		    					
    			}else{
    				h.put("suburusan","");
    			}		    			
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
    			listCarian.addElement(h);
    			bil++;
			}
			return listCarian;
	} finally {
		if (db != null) db.close();
	}

}
	// CLOSE	
	
	///************** CREATE SEQUENCE TEMP 
	
	 public static synchronized int getSeqNoBantahan(int id_jenisaduan) throws DbException {
			return getSeqNoAduan(id_jenisaduan);
		}	 

	 public static synchronized int getSeqNoAduan(int id_jenisaduan) throws DbException  {

			Db db = null;
			Connection conn = null;
			//File f = null;
			StringBuffer sb = new StringBuffer();
			int seqno=0;
			try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
		//	f = new File();
			boolean found = false;
			
			sb.append("SELECT NO_TURUTAN FROM TBLRUJSEQADUAN WHERE ");
			sb.append("ID_JENISADUAN = " +id_jenisaduan);
			
			
			ResultSet rs = db.getStatement().executeQuery(sb.toString()); 
			
			if (rs.next()) found = true;
			if (found) {
			//	f.increaseSeqAduan(id_jenisaduan); 
				increaseSeqAduan(id_jenisaduan); 
			} else {
			//	f.addNewAduan(id_jenisaduan);	
				addNewAduan(id_jenisaduan);	
			}
			ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
	        if ( rs2.next() ) {
	      	  
	      		  seqno = rs2.getInt("NO_TURUTAN");
	      	  
	        }
			conn.commit();
			
			} catch (Exception ex) {
			try {
			conn.rollback(); } catch (SQLException localSQLException1) {
			}
			throw new DbException(ex.getMessage() + ": " + sb.toString());
			} 
			finally {
			if (db != null) db.close();
			}
			
			return seqno;
			}
	 
	 public static void increaseSeqAduan(int id_jenisaduan) throws DbException  {

			Db db = null;
		
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE TBLRUJSEQADUAN  SET "); 
			sb.append("no_turutan = no_turutan + 1 ");
			sb.append(" WHERE ");
			sb.append("id_jenisaduan = '"+id_jenisaduan+"'");
			
			try {
				db = new Db();
				try{
				db.getStatement().executeUpdate(sb.toString());
				} catch (SQLException x) {x.printStackTrace();}
			}catch (Exception ex) {
				throw new DbException(ex.getMessage() + ": " + sb.toString());
			}
			finally {
			if (db != null) db.close();
			}
		}
	 public static void addNewAduan(int id_jenisaduan) throws DbException {

			Db db = null;
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO TBLRUJSEQADUAN (ID_JENISADUAN,NO_TURUTAN)");
			sb.append(" VALUES (");
			sb.append("'"+id_jenisaduan+"'");
			sb.append(",1)"); //initial value
			
			try {
			db = new Db();
			db.getStatement().executeUpdate(sb.toString());
			}catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
			}
			finally {
			if (db != null) db.close();
			}
		}
	 
		////***** END CREATE SEQUENCE TEMP	
	 

	 
	 // ONLINE AGENSI PEMOHON 
	 
		// GET LIST PEMOHON AP
		
	 public Vector getListPemohonAP(String userIdNegeri)throws Exception {		 
		    Db db = null;
		    String sql = "";		    
		    try{
		      listPemohonAP = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();		     
		      
		      sql = " SELECT DISTINCT p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail,f.no_fail, p.tarikh_permohonan, su.nama_suburusan, ";
		      sql +=" k.nama_kementerian, s.keterangan, p.no_rujukan_upt,p.tarikh_kemaskini ";
		      sql +=" FROM tblpptpermohonan p,tblpfdfail f,tblrujsuburusan su,tblrujstatus s,tblrujkementerian k,tblppthakmilik l ";
		      sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan ";
		      sql +=" AND f.id_kementerian = k.id_kementerian AND su.id_suburusan = '52' ";
		      sql +=" AND ( ";
		      sql +=" SELECT DISTINCT P1.ID_PERMOHONAN FROM TBLPPTBORANGH H,TBLPPTPERMOHONAN P1,TBLPPTHAKMILIK HM,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB ";	
		      sql +=" WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN ";
		      sql +=" AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HM.ID_PERMOHONAN = P1.ID_PERMOHONAN ";
		      sql +=" AND H.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB AND P1.ID_PERMOHONAN = p.id_permohonan ";
		      sql +=" ) > 0  ";
		      sql +=" AND p.id_status = s.id_status AND p.id_permohonan = l.id_permohonan ";
		      sql +=" AND NVL (l.flag_penarikan_keseluruhan, ' ') <> 'Y'  ";
		      sql +=" AND NVL (l.flag_pembatalan_keseluruhan, ' ') <> 'Y' ";
		      sql +=" AND P.ID_STATUS NOT IN (181,183,184,185,186,187,220,1610248) ";
		      
	    		// ADDED BY ELLY 14 JUNE 2010
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    		}
		      
		      sql +=" ORDER BY P.TARIKH_KEMASKINI DESC ";			      		      
//		      myLogger.info("SQL DEFAULT PB :: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);		      		      
		      Hashtable h = null;
		      int bil = 1;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("id_fail", rs.getString("id_fail"));
		    	  h.put("id_status", rs.getString("id_status"));
		    	  h.put("id_permohonan", rs.getString("id_permohonan"));
		    	  h.put("no_permohonan", rs.getString("no_permohonan"));
		    	  h.put("tarikh_permohonan", rs.getString("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));
		    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));		    	  
		    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
		    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
		    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
		    	  if(rs.getString("no_fail") == null){
			    		h.put("no_fail","Belum Diluluskan");
			    	}else{
			    		h.put("no_fail",rs.getString("no_fail"));
			    	}
		    	  h.put("tarikh_kemaskini", rs.getString("tarikh_kemaskini")==null?"":sdf.format(rs.getDate("tarikh_kemaskini")));		    	  
		    	  listPemohonAP.addElement(h);
		    	  bil++;		    	  
		      }
				return listPemohonAP;
		    }
		    finally{
		      if (db != null) db.close();
		    }
	 }		
	 
	 
		public static Vector getListPemohon(String userId,String role)throws Exception {
			 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
//		    		sql = " SELECT DISTINCT u.user_id,p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, ";
//		    		sql +=" f.no_fail, p.tarikh_permohonan, su.nama_suburusan, ";
//		    		sql +=" k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
//		    		sql +=" p.flag_semak, p.no_rujukan_ptg, p.no_rujukan_ptd, ";
//		    		sql +=" f.id_suburusan, p.tarikh_kemaskini, p.no_rujukan_upt ";
//		    		sql +=" FROM tblpptpermohonan p,tblpfdfail f, ";
//		    		sql +=" tblrujsuburusan su,tblrujstatus s, ";
//		    		sql +=" tblrujkementerian k,users u,users_kementerian uk,tblppthakmilik l ";
//		    		sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan ";
//		    		sql +=" AND f.id_kementerian = k.id_kementerian AND u.user_id = uk.user_id(+) ";
//		    		sql +=" AND UK.ID_KEMENTERIAN = F.ID_KEMENTERIAN ";
//		    		sql +=" AND p.id_status = s.id_status AND f.id_suburusan = '52' ";
//		    		sql +=" AND (SELECT DISTINCT p1.id_permohonan ";
//		    		sql +=" FROM tblpptborangh h,tblpptpermohonan p1,tblppthakmilik hm ";
//		    		sql +=" WHERE hm.id_permohonan = p1.id_permohonan  ";
//		    		sql +=" AND p1.id_permohonan = p.id_permohonan) > 0 ";
//		    		sql +=" AND NVL (p.flag_jenispermohonan, 0) = '1' ";
//		    		sql +=" AND NVL (l.flag_penarikan_keseluruhan, ' ') <> 'Y' ";
//		    		sql +=" AND NVL (l.flag_pembatalan_keseluruhan, ' ') <> 'Y'";
//		    		sql +=" AND p.id_status NOT IN (181, 183, 184, 185, 186, 187, 220, 1610248) ";
//		    		sql +=" AND u.user_id = '"+userId+"' ";		    		
//		    		sql +=" ORDER BY p.tarikh_masuk DESC, p.tarikh_kemaskini DESC ";	
		    		
//				      sql = " SELECT DISTINCT us.user_id,p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, ";
//				      sql +=" f.no_fail,p.tarikh_permohonan,su.nama_suburusan, ";
//				      sql +=" k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
//				      sql +=" p.flag_semak, p.no_rujukan_ptg, p.no_rujukan_ptd, ";
//				      sql +=" f.id_suburusan, p.tarikh_kemaskini, p.no_rujukan_upt ";
//				      sql +=" FROM tblpptpermohonan p,tblpfdfail f,tblrujsuburusan su,tblrujstatus s,tblrujkementerian k, ";
//				      sql +=" tblppthakmilik l,users_kementerian uk,users us ";
//				      sql +=" WHERE f.id_fail = p.id_fail AND uk.user_id = us.user_id ";
//				      sql +=" and f.id_kementerian = uk.id_kementerian AND f.id_suburusan = su.id_suburusan ";
//				      sql +=" AND f.id_kementerian = k.id_kementerian ";	
//				      sql +=" and us.user_id = '"+userId+"' ";
//				      sql +=" AND su.id_suburusan = '52' ";
				      
				  /*    sql +=" AND (SELECT DISTINCT p1.id_permohonan ";
				      sql +=" FROM tblpptborangh h,tblpptpermohonan p1,tblppthakmilik hm ";
				      sql +=" WHERE hm.id_permohonan = p1.id_permohonan ";
				      sql +=" AND p1.id_permohonan = p.id_permohonan) > 0 ";*/
				      
//				      sql +=" AND ( ";
//				      sql +=" SELECT DISTINCT P1.ID_PERMOHONAN FROM TBLPPTBORANGH H,TBLPPTPERMOHONAN P1,TBLPPTHAKMILIK HM,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB ";	
//				      sql +=" WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN ";
//				      sql +=" AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HM.ID_PERMOHONAN = P1.ID_PERMOHONAN ";
//				      sql +=" AND H.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB AND P1.ID_PERMOHONAN = p.id_permohonan ";
//				      sql +=" ) > 0  ";
//				      
//				      sql +=" AND p.id_status = s.id_status ";
//				      sql +=" AND p.id_permohonan = l.id_permohonan ";
//				      sql +=" AND NVL (l.flag_penarikan_keseluruhan, ' ') <> 'Y' ";
//				      sql +=" AND NVL (l.flag_pembatalan_keseluruhan, ' ') <> 'Y' ";

		    		sql = " SELECT DISTINCT us.user_id, p.id_status, p.id_permohonan, p.no_permohonan, "+
		    				" f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, "+
		    				" k.nama_kementerian, s.keterangan, p.tarikh_masuk, "+
		    				" p.flag_semak, p.no_rujukan_ptg, p.no_rujukan_ptd, "+
		    				" f.id_suburusan, p.tarikh_kemaskini, p.no_rujukan_upt "+
		    				" FROM tblpptpermohonan p, "+
		    				" tblpfdfail f, "+
		    				" tblrujsuburusan su, "+
		    				" tblrujstatus s, "+
		    				" tblrujkementerian k, "+
		    				" tblppthakmilik l, "+
		    				" users_kementerian uk, "+
		    				" tblppthakmilikpb hpb,tblpptborangh h, tblpptpihakberkepentingan pb, "+
		    				" users us "+
		    				" WHERE  f.id_fail = p.id_fail "+
		    				" and l.id_hakmilik = hpb.id_hakmilik AND h.id_hakmilikpb = hpb.id_hakmilikpb and pb.id_pihakberkepentingan = hpb.id_pihakberkepentingan "+
		    				" AND uk.user_id = us.user_id "+
		    				" AND f.id_kementerian = uk.id_kementerian "+
		    				" AND f.id_suburusan = su.id_suburusan "+
		    				" AND f.id_kementerian = k.id_kementerian "+
		    				" AND us.user_id = '"+userId+"' "+
		    				" AND su.id_suburusan = '52' "+
		    				" AND p.id_status = s.id_status "+
		    				" AND p.id_permohonan = l.id_permohonan "+
		    				" AND NVL (l.flag_penarikan_keseluruhan, ' ') <> 'Y' "+
		    				" AND NVL (l.flag_pembatalan_keseluruhan, ' ') <> 'Y' ";
		    		
				      if(role.equals("online_kjpagensi")){
				    	  sql +="AND uk.id_agensi = p.id_agensi ";
			    		}
				      
				      //sql +=" AND p.id_status NOT IN (181, 183, 184, 185, 186, 187, 220, 1610248) ";
				      //sql +=" AND f.id_negeri = '10' ";
				      
				      sql +=" ORDER BY p.id_status DESC, p.tarikh_kemaskini DESC ";
		    		
		    		myLogger.info("SQL AP ONLINE :: "+sql);
		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);		    			
		    			h.put("id_suburusan", rs.getString("id_suburusan")== null?"":rs.getString("id_suburusan"));
		    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
		    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
		    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
		    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
		    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
		    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
		    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));		    			
		    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
		    			h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
		    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));		    			
		    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));		    			
		    			if(rs.getString("id_suburusan") != null && rs.getString("id_suburusan") != ""){		    				
		    				if(rs.getString("id_suburusan").equals("51")){
		    					h.put("suburusan","SEKSYEN 4");
		    				}else if(rs.getString("id_suburusan").equals("52")){
		    					h.put("suburusan","SEKSYEN 8");
		    				}else if(rs.getString("id_suburusan").equals("53")){
		    					h.put("suburusan","PENGAMBILAN SEMENTARA");
		    				}else{
		    					h.put("suburusan","");
		    				}		    					
		    			}else{
		    				h.put("suburusan","");
		    			}		    			
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
		    			list.addElement(h);
		    			bil++;
		    	  
		    		}//close while
		    		return list;
		    	}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListPemohon
	
 public Vector getListPemohonPBOnlihe()throws Exception {		 
	    Db db = null;
	    String sql = "";		    
	    try{
	      listPBonline = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();		     
	      
	      sql = " SELECT B.ID_STATUS, B.ID_PERMOHONAN, B.NO_PERMOHONAN, A.ID_FAIL, B.NO_RUJUKAN_PTD, ";		      
	      sql +=" B.NO_RUJUKAN_PTG,A.NO_FAIL, B.TARIKH_PERMOHONAN, SU.NAMA_SUBURUSAN, ";
	      sql +=" K.NAMA_KEMENTERIAN, S.KETERANGAN, B.NO_RUJUKAN_UPT,B.TARIKH_KEMASKINI, ";
	      sql +=" D.NAMA_PB,D.NO_PB "; 
	      sql +=" FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
	      sql +=" TBLPPTHAKMILIK C,TBLPPTPIHAKBERKEPENTINGAN D, ";
	      sql +=" TBLPPTHAKMILIKPB E,TBLPPTBANTAHAN F, ";
	      sql +=" TBLRUJSUBURUSAN su,TBLRUJSTATUS s,TBLRUJKEMENTERIAN k ";	
	      sql +=" WHERE A.ID_FAIL = B.ID_FAIL ";
	      sql +=" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
	      sql +=" AND C.ID_HAKMILIK = E.ID_HAKMILIK ";
	      sql +=" AND D.ID_PIHAKBERKEPENTINGAN = E.ID_PIHAKBERKEPENTINGAN ";
	      sql +=" AND E.ID_HAKMILIKPB = F.ID_HAKMILIKPB ";
	      sql +=" AND A.ID_SUBURUSAN = su.ID_SUBURUSAN ";
	      sql +=" AND A.ID_KEMENTERIAN = k.ID_KEMENTERIAN ";
	      sql +=" AND B.ID_STATUS = s.ID_STATUS ";
	      sql +=" AND NVL (C.FLAG_PENARIKAN_KESELURUHAN, ' ') <> 'Y' ";
	      sql +=" AND NVL (C.FLAG_PEMBATALAN_KESELURUHAN, ' ') <> 'Y' ";
	      sql +=" AND SU.ID_SUBURUSAN = '52' ";
	      sql +=" AND F.FLAG_ONLINE = '2' ";
	      
//	      myLogger.info("SQL LIST INTERNAL PB ONLINE :: "+sql);
	      ResultSet rs = stmt.executeQuery(sql);		      		      
	      Hashtable h = null;
	      int bil = 1;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
	    	  h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
	    	  h.put("no_permohonan", rs.getString("NO_PERMOHONAN")==null?"":rs.getString("NO_PERMOHONAN"));
	    	  h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
	    	  h.put("no_rujukan_ptg", rs.getString("NO_RUJUKAN_PTG")==null?"":rs.getString("NO_RUJUKAN_PTG"));
	    	  h.put("no_rujukan_ptd", rs.getString("NO_RUJUKAN_PTD")==null?"":rs.getString("NO_RUJUKAN_PTD"));		    	  
	    	  h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));	    	  
	    	  h.put("tarikh_permohonan", rs.getString("TARIKH_PERMOHONAN")==null?"":sdf.format(rs.getDate("TARIKH_PERMOHONAN")));
	    	  h.put("nama_suburusan", rs.getString("NAMA_SUBURUSAN")==null?"":rs.getString("NAMA_SUBURUSAN"));
	    	  h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN")==null?"":rs.getString("NAMA_KEMENTERIAN"));
	    	  h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
	    	  h.put("no_rujukan_upt", rs.getString("NO_RUJUKAN_UPT")==null?"":rs.getString("NO_RUJUKAN_UPT"));
	    	  h.put("tarikh_kemaskini", rs.getString("TARIKH_KEMASKINI")==null?"":sdf.format(rs.getDate("TARIKH_KEMASKINI")));
	    	  h.put("nama_pb", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
	    	  h.put("no_pb", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
	    	  listPBonline.addElement(h);
	    	  bil++;		    	  
	      }
			return listPBonline;
	    }
	    finally{
	      if (db != null) db.close();
	    }
 }

		
	public Vector getListPemohonAPOnliheInternal(String txtNoFail, String carianTarikh)throws Exception {		 
	    Db db = null;
	    String sql = "";		    
	    String nofail = "";
	    
	    String cariTarikh = "";
	    if(carianTarikh!=""){
	    	cariTarikh = "to_date('" + carianTarikh + "','dd/MM/yyyy')";
	    }	 
	    
	    try{
	      listAPonlineInternal = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();		     
	      nofail = txtNoFail.trim();  
	      
	      sql = " SELECT A.ID_FAIL,A.NO_FAIL,B.ID_PERMOHONAN,C.ID_HAKMILIK, D.ID_BANTAHAN, D.TARIKH_MOHON, ";		      
	      sql +=" E.NAMA_KEMENTERIAN,F.NAMA_AGENSI,B.ID_STATUS,B.NO_RUJUKAN_PTD,B.NO_RUJUKAN_PTG,B.NO_RUJUKAN_UPT, ";
	      sql +=" CASE ";
	      sql +=" WHEN C.no_lot IS NOT NULL AND C.no_pt IS NULL "; 
	      sql +=" THEN C.no_lot ";
	      sql +=" WHEN C.no_lot IS NULL AND C.no_pt IS NULL ";
	      sql +=" THEN LT.keterangan || C.no_pt ";
	      sql +=" WHEN C.no_lot IS NULL AND C.no_pt IS NULL ";	
	      sql +=" THEN LT.keterangan || C.no_pt ";
	      sql +=" WHEN C.no_lot IS NULL AND C.no_pt IS NOT NULL ";
	      sql +=" THEN LT.keterangan || C.no_pt ";
	      sql +=" WHEN C.no_lot IS NOT NULL AND C.no_pt IS NOT NULL ";
	      sql +=" THEN LT.keterangan || C.no_pt || CHR (32) || CHR (40) || C.no_lot || CHR (41) ";
	      sql +=" ELSE '' ";
	      sql +=" END AS no_lotpt ";
	      sql +=" FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
	      sql +=" TBLPPTHAKMILIK C,TBLPPTBANTAHAN D, ";
	      sql +=" TBLRUJLOT LT,TBLRUJKEMENTERIAN E, ";
	      sql +=" TBLRUJAGENSI F,TBLRUJSUBURUSAN SU ";
	      sql +=" WHERE A.ID_FAIL = B.ID_FAIL ";
	      sql +=" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
	      sql +=" AND C.ID_HAKMILIK = D.ID_HAKMILIK ";
	      sql +=" AND C.ID_LOT = LT.ID_LOT(+) ";
	      sql +=" AND A.ID_KEMENTERIAN = E.ID_KEMENTERIAN ";
	      sql +=" AND B.ID_AGENSI = F.ID_AGENSI ";
	      sql +=" AND A.ID_SUBURUSAN = SU.ID_SUBURUSAN ";
	      sql +=" AND NVL (C.FLAG_PEMBATALAN_KESELURUHAN, 0) <> 'Y' ";	      	      
	      sql +=" AND NVL (C.FLAG_PENARIKAN_KESELURUHAN, 0) <> 'Y' ";
	      sql +=" AND SU.ID_SUBURUSAN = '52' ";
	      sql +=" AND D.FLAG_ONLINE IN ('1','2','3') ";	      
	      
	    //NO FAIL
			if (txtNoFail != "" && txtNoFail != null) {
				if (!nofail.equals("")) {
					sql += " AND (UPPER(a.no_fail) LIKE '%" + nofail.toUpperCase() + "%' " +
						" OR UPPER(b.no_rujukan_ptg) LIKE '%" + nofail.toUpperCase() + "%' " +
						" OR UPPER(b.no_rujukan_upt) LIKE '%" + nofail.toUpperCase() + "%' " +
						" OR UPPER(b.no_rujukan_ptd) LIKE '%" + nofail.toUpperCase() + "%')";
				}
			}//close carian by nofail
			
			//TARIKH
			if (carianTarikh != "" && carianTarikh != null) {
				if (!cariTarikh.equals("")) {
					sql = sql + " AND UPPER(b.tarikh_permohonan) = "+cariTarikh;
				}
			}//close carian by tarikh
			
	      myLogger.info("SQL LIST INTERNAL AP ONLINE :: "+sql);
	      ResultSet rs = stmt.executeQuery(sql);		      		      
	      Hashtable h = null;
	      int bil = 1;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
	    	  h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
	    	  h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
	    	  h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
	    	  h.put("id_bantahan", rs.getString("ID_BANTAHAN")==null?"":rs.getString("ID_BANTAHAN"));
	    	  h.put("tarikh_mohon", rs.getString("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));		    	  
	    	  h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN")==null?"":rs.getString("NAMA_KEMENTERIAN"));	    	      	  
	    	  h.put("nama_agensi", rs.getString("NAMA_AGENSI")==null?"":rs.getString("NAMA_AGENSI"));
	    	  h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
	    	  h.put("no_rujukan_ptd", rs.getString("NO_RUJUKAN_PTD")==null?"":rs.getString("NO_RUJUKAN_PTD"));
	    	  h.put("no_rujukan_ptg", rs.getString("NO_RUJUKAN_PTG")==null?"":rs.getString("NO_RUJUKAN_PTG"));
	    	  h.put("no_rujukan_upt", rs.getString("NO_RUJUKAN_UPT")==null?"":rs.getString("NO_RUJUKAN_UPT"));
	    	  h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
	    	  listAPonlineInternal.addElement(h);
	    	  bil++;		    	  
	      }
			return listAPonlineInternal;
	    }
	    finally{
	      if (db != null) db.close();
	    }
	}


	
	public void setMaklumatAgensi_DgnBantahanOnlineInternal(String id_bantahan) throws Exception {
		listMaklumatAgensiBantahanOnlineInternal = new Vector();
			
			Db db = null;
			listMaklumatAgensiBantahanOnlineInternal.clear();
			String sql = "";
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql =  " SELECT A.ID_FAIL,C.ID_HAKMILIK,C.NO_HAKMILIK,D.ID_BANTAHAN ";
					sql += " FROM TBLPFDFAIL A, ";
					sql += " TBLPPTPERMOHONAN B, ";
					sql += " TBLPPTHAKMILIK C, "; 
					sql += " TBLPPTBANTAHAN D ";
					sql += " WHERE A.ID_FAIL = B.ID_FAIL ";       
					sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
					sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK ";									
					sql += " AND D.ID_BANTAHAN = '"+id_bantahan+"' ";	
					
//					myLogger.info("SQL AGENSI BANTAHAN ONLINE  :: "+sql);
					
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
					
					while (rs.next()){
						h = new Hashtable();
						h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
						h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
						h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
						h.put("id_bantahan", rs.getString("ID_BANTAHAN")==null?"":rs.getString("ID_BANTAHAN"));
						listMaklumatAgensiBantahanOnlineInternal.addElement(h);						
					}
				//return list;
			}finally {
				if(db != null) db.close();
			}
	}

	
	public void pengesahan_internal(String id_bantahan, String usid) throws Exception {
	    Db db = null;
	    String sql = "";	    
	    try
	    {			  
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_bantahan", id_bantahan);		    
		    r.add("flag_online", 3);	// 3 - FLAG PENGESAHAN DI INTERNAL
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblpptbantahan");	
//		    myLogger.info("EDIT FLAG ONLINE = "+sql);
		    stmt.executeUpdate(sql);		    
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}				
	}
	
	
	public void setListMaklumatTanahByStatus(String idPermohonan,String lot,String idpegawai) throws Exception{
		
		listMaklumatTanahByStatus = new Vector();
		
		Db db = null;
		listMaklumatTanahByStatus.clear();
		String sql = "";
		String noLOT = lot.trim();
		String nama2Mukim = "";
		String listLOT = "";
		String listLOTHM = "";
		double totalSize = 0;
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT COUNT(distinct mk.nama_mukim) as totalMukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rx = stmt.executeQuery(sql);				
				int totalMukim = 0;

				while (rx.next()){					
					totalMukim = rx.getInt("totalMukim");														
				}
		
				sql = "SELECT DISTINCT nama_mukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rz = stmt.executeQuery(sql);	
				
				String namaMukim = "";
				int bilMukim = 0;
				
				while (rz.next()){					
						
					if(totalMukim!=0){
						if(bilMukim==0){
							
							namaMukim += namaMukim = rz.getString("nama_mukim");	
						
						}else{
							
							if(totalMukim - bilMukim == 1){
								namaMukim += " dan "+rz.getString("nama_mukim");
							}else{
								namaMukim += ", "+rz.getString("nama_mukim");	
							}	
						}
						bilMukim++;	
					
					}else{
						namaMukim = "";
					}
					
					if(totalMukim == bilMukim){
						nama2Mukim = namaMukim;
					}else{
						nama2Mukim = "";
					}
				}
				
				
				//////////////get lot////////////////////
				sql = "SELECT COUNT(m.id_hakmilik) as totalLOT FROM Tblpptpermohonan p, Tblppthakmilik m, Tblrujlot lt ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
				sql += " AND m.id_lot = lt.id_lot(+)"; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rl = stmt.executeQuery(sql);				
				int totalLOT = 0;

				while (rl.next()){					
					totalLOT = rl.getInt("totalLOT");														
				}
				
				sql = "SELECT CASE ";
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";      
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT, m.no_hakmilik, jh.kod_jenis_hakmilik  FROM Tblpptpermohonan p, Tblrujlot lt, Tblppthakmilik m, Tblrujjenishakmilik jh ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
				sql += " AND m.id_lot = lt.id_lot(+) "; 
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rl2 = stmt.executeQuery(sql);	
				
				String LotPT = "";
				String LotPTHM = "";
				int bilLOT = 0;
				
				while (rl2.next()){					
					
					if(totalLOT!=0){
						if(bilLOT==0){
							
							LotPTHM += LotPTHM = (rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
							LotPT += LotPT = rl2.getString("NO_LOTPT");
							
						}else{
							
							if(totalLOT - bilLOT == 1){
								LotPTHM += " dan "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));
								LotPT += " dan "+rl2.getString("NO_LOTPT");
							}else{
								LotPTHM += ", "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
								LotPT += ", "+rl2.getString("NO_LOTPT");
							}	
						}
						bilLOT++;	
					
					}else{
						LotPT = "";
						LotPTHM = "";
					}
					
					if(totalLOT == bilLOT){
						listLOT = LotPT;
						listLOTHM = LotPTHM;
					}else{
						listLOT = "";
						listLOTHM = "";
					}
				}
				
				sql = "SELECT DISTINCT p.no_rujukan_ptg, p.id_status, f.no_fail, m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
				
				sql += " (select count(a.id_hakmilikpb) from Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
				sql += " where a.id_pihakberkepentingan = b.id_pihakberkepentingan and a.id_hakmilik(+) = m.id_hakmilik and a.id_jenispb not in (40,41,42))as totalPB, ";
				
				sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
				sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, jh.kod_jenis_hakmilik, ";
				sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, ";
				sql += " m.flag_ambil_segera,d.nama_daerah,m.flag_borangl, m.status_borangl, m.tarikh_terima_hm, u.user_name as nama_pegawai, jh.status_hakmilik, ";
				sql += " m.flag_jenis_rizab, m.nama_lain_rizab, m.no_warta_rizab, m.tarikh_warta_rizab, m.no_subjaket, m.id_pegawai, m.id_unitluasambil_convert, m.id_unitluaslot_convert, ";
				sql += " CASE ";
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot "; 
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";       
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT, ";
				sql += " M.FLAG_BANTAHAN, bts.keterangan as status_bantahan ";
				sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
				sql += " Tblrujdaerah d, Users u, tblpptbantahan bt,tblrujstatus bts ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
				sql += " AND m.id_negeri = n.id_negeri "; 
				sql += " AND p.id_fail = f.id_fail "; 
				sql += " AND m.id_daerah = d.id_daerah(+)";
				sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
				sql += " AND m.id_pegawai = u.user_id(+)";
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND m.id_lot = lt.id_lot(+) ";
				sql += " AND m.id_mukim = mk.id_mukim(+) ";  
				sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
				sql += " AND bt.id_hakmilik(+) = m.id_hakmilik ";
				sql += " AND bt.status_bantahan_ap = bts.id_status(+) ";
				sql += " AND (select count(*) from tblpptborangh bh, tblppthakmilikpb hpb where bh.id_hakmilikpb = hpb.id_hakmilikpb" +
					   " and hpb.id_hakmilik = m.id_hakmilik ) > 0";
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
				
				
				sql += " ORDER BY m.no_subjaket asc, m.no_lot asc, mk.nama_mukim asc";
				myLogger.info("LIST HAKMILIK AP PORTAL :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("listLOT", listLOT.toUpperCase());
					h.put("listLOTHM", listLOTHM.toUpperCase());
					h.put("nama2Mukim", nama2Mukim.toUpperCase());
					h.put("bil", bil);
					h.put("totalPB", rs.getString("totalPB")==null?"":rs.getString("totalPB"));
					h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
					h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
					h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
					h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
					h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
					h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
					h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
					h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
					h.put("unitluaslot", rs.getString("unit1")==null?"":rs.getString("unit1"));			
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
					h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
					h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
					h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
					h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
					h.put("status_borangl", rs.getString("status_borangl")==null?"":rs.getString("status_borangl"));
					h.put("tarikh_terima_hm", rs.getDate("tarikh_terima_hm")==null?"":sdf.format(rs.getDate("tarikh_terima_hm")));
					h.put("status_hakmilik", rs.getString("status_hakmilik")==null?"":rs.getString("status_hakmilik"));
					
					h.put("flag_jenis_rizab", rs.getString("flag_jenis_rizab")==null?"":rs.getString("flag_jenis_rizab"));
					h.put("nama_lain_rizab", rs.getString("nama_lain_rizab")==null?"":rs.getString("nama_lain_rizab"));
					h.put("tarikh_warta_rizab", rs.getDate("tarikh_warta_rizab")==null?"":sdf.format(rs.getDate("tarikh_warta_rizab")));
					h.put("no_warta_rizab", rs.getString("no_warta_rizab")==null?"":rs.getString("no_warta_rizab"));
					
					if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
						
						double luasAmbil = rs.getDouble("luas_ambil");
						String LA = Utils.formatLuasHM(luasAmbil);
						h.put("luas_ambil",LA);
								
					}else{
						h.put("luas_ambil","0");
					}
					
					
					//validation for PU
					if(rs.getString("luas_ambil") != null && rs.getString("luas_lot") != null){
						
						double luasAmbil = 0;
						if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("2")){
							luasAmbil = rs.getDouble("luas_ambil"); //meter persegi
						}else{
							luasAmbil = rs.getDouble("luas_ambil") * 10000; //hektar
						}
						
						double luasLot = 0;
						if(rs.getString("id_unitluaslot_convert") != null && rs.getString("id_unitluaslot_convert").equals("2")){
							luasLot = rs.getDouble("luas_lot"); //meter persegi
						}else{
							luasLot = rs.getDouble("luas_lot") * 10000; //hektar
						}						
						
						double baki = luasLot - luasAmbil;
						
						if(baki>0){
							h.put("flagPU","yes");
						}else{
							h.put("flagPU","no");
						}
				
					}else{
						h.put("flagPU","no");
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

					
					if(rs.getString("id_unitluasambil_convert") != null){	
						
						if(rs.getString("id_unitluasambil_convert").equals("1")){	
							
							if(rs.getString("luas_ambil") != null){
								double luasAmbil = rs.getDouble("luas_ambil");
								String LAH = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAH);
							}else{
								h.put("nilaiTanah","0");
							}
							
						}else{						
							if(rs.getString("luas_ambil") != null){
								double luasAmbil = rs.getDouble("luas_ambil") / 10000;
								String LAM = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAM);
							}else{
								h.put("nilaiTanah","0");
							}						
						}	
						
					}else{
						h.put("nilaiTanah","0");
					}
					
					
					//get total luas ambil in hectar
					if(rs.getString("luas_ambil") != null){	
						if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("1")){
							totalSize += rs.getDouble("luas_ambil");
						}else{
							totalSize += (rs.getDouble("luas_ambil") / 10000);
						}
					}
					// ADDED BY ELLY
					h.put("luas_keseluruhan", totalSize);					
					
					h.put("status_bantahan", rs.getString("status_bantahan")==null?"BELUM DIDAFTAR":rs.getString("status_bantahan"));
					
					h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
					h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
					h.put("luas_lot", rs.getString("luas_lot")==null?"":rs.getDouble("luas_lot"));
					h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
					h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
					
					h.put("tarikh_daftar", rs.getDate("tarikh_daftar")==null?"":sdf.format(rs.getDate("tarikh_daftar")));
					h.put("tarikh_luput", rs.getDate("tarikh_luput")==null?"":sdf.format(rs.getDate("tarikh_luput")));
					h.put("tempoh_luput", rs.getString("tempoh_luput")==null?"":rs.getString("tempoh_luput"));
					
					h.put("lokasi", rs.getString("lokasi")==null?"":rs.getString("lokasi"));
					h.put("syarat_nyata", rs.getString("syarat_nyata")==null?"":rs.getString("syarat_nyata"));
					h.put("syarat_khas", rs.getString("syarat_khas")==null?"":rs.getString("syarat_khas"));
					
					h.put("sekatan_hak", rs.getString("sekatan_hak")==null?"":rs.getString("sekatan_hak"));
					h.put("sekatan_kepentingan", rs.getString("sekatan_kepentingan")==null?"":rs.getString("sekatan_kepentingan"));
					h.put("no_syit", rs.getString("no_syit")==null?"":rs.getString("no_syit"));
					h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"":rs.getString("jenis_hakmilik"));
					h.put("flag_borangl", rs.getString("flag_borangl")==null?"":rs.getString("flag_borangl"));
					h.put("flag_bantahan", rs.getString("FLAG_BANTAHAN")==null?"":rs.getString("FLAG_BANTAHAN"));
					
					listMaklumatTanahByStatus.addElement(h);
					bil++;	
				}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}//close setDataListKertas	
	
	
	
}
