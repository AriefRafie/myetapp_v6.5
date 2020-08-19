package ekptg.model.ppt;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;

public class BantahanAgensiDaftar extends EkptgCache implements Serializable {
	 static Logger myLogger = Logger.getLogger(BantahanAgensiDaftar.class);
	 private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");	
	 public Vector getSenaraiNoLot = null;
	 public Vector getNoSiasatanAP = null;
	 public Vector getTarikhPentingAP = null;
	 public Vector listCarianAP = null;
	 public Vector getMaklumatBantahanAP = null;
	 public Vector getMaklumatPampasanAP = null;
	 public Vector getMaklumatDepositAP = null;
	 public Vector getHakmilikAP = null;
	 public Vector listDokumen_bantahan = null;
	 public Vector view_details_dokumen = null;
	 public Vector getNoWarta = null;
	 public Vector getAlamatMahkamah = null;
	 

		public Vector setCarianNoHakmilik(String id_permohonan,String carianNoHakmilik,String carianNoLot) throws Exception{
			
			Db db = null;
			String sql = "";
			carianNoHakmilik = carianNoHakmilik.trim();	
			carianNoLot = carianNoLot.trim();
			try {
					listCarianAP = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql =  " SELECT  B.ID_HAKMILIK,A.ID_PERMOHONAN,B.NO_LOT,D.STATUS_BANTAHAN,E.KETERANGAN,B.NO_HAKMILIK ";
					sql += " FROM TBLPPTPERMOHONAN A,TBLPPTHAKMILIK B, ";
					sql += " TBLPPTHAKMILIKPB C,TBLPPTBANTAHAN D, ";
					sql += " TBLRUJSTATUS E ";
					sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN ";
					sql += " AND B.ID_HAKMILIK = C.ID_HAKMILIK ";
					sql += " AND C.ID_HAKMILIKPB = D.ID_HAKMILIKPB(+) ";
					sql += " AND D.STATUS_BANTAHAN = E.ID_STATUS(+) "; 
					sql += " AND A.ID_PERMOHONAN = '"+ id_permohonan +"' ";
					
					//NAMA PB
					if (carianNoHakmilik != "" && carianNoHakmilik != null) {
						if (!carianNoHakmilik.equals("")) {
							sql += " AND UPPER(B.NO_HAKMILIK) LIKE '%" + carianNoHakmilik.toUpperCase() + "%'";
						}
					}
					
					//NO LOT
					if (carianNoLot != "" && carianNoLot != null) {
						if (!carianNoLot.equals("")) {
							sql += " AND UPPER(B.NO_LOT) LIKE '%" + carianNoLot.toUpperCase() + "%'";
						}
					}				
					
					sql += " ORDER BY B.NO_LOT ASC";						
				myLogger.info("SQL CARIAN AGENSI PEMOHON :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
					int bil = 1;
					while (rs.next()){
						h = new Hashtable();
				    	h.put("bil", bil);
				    	h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
				    	h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				    	h.put("no_lot", rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));	    	
				    	h.put("status_bantahan", rs.getString("STATUS_BANTAHAN")==null?"":rs.getString("STATUS_BANTAHAN"));
				    	h.put("keteranganStatusBantahan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				    	h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));			    	
				    	listCarianAP.addElement(h);
						bil++;	
					}
					return listCarianAP;
					
			}finally {
				if(db != null) db.close();
			}
		}	
		
		public int getSenaraiNoLot_count(String id_permohonan)throws Exception {		 							
			Db db = null;
			String sql = "";
			try{
					//getSenaraiNoLot = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();								
					sql  = " SELECT count(*) as total ";   
					sql += " FROM TBLPPTPERMOHONAN A,TBLPPTHAKMILIK B, ";
					sql += " TBLPPTBANTAHAN D,TBLRUJSTATUS E,Tblrujlot lt ";
					sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN ";
					sql += " AND D.STATUS_BANTAHAN_AP = e.ID_STATUS(+) ";
					sql += " AND B.ID_HAKMILIK = D.ID_HAKMILIK(+) ";
					sql += " AND D.ID_HAKMILIKPB IS NULL ";	
					sql += " AND B.id_lot = lt.id_lot(+) ";
					sql += " AND A.ID_PERMOHONAN = '"+ id_permohonan +"' ";
					ResultSet rs = stmt.executeQuery(sql);
					myLogger.info("SQL getSenaraiNoLot :: "+sql);
					Hashtable h;
					int total = 0;
			     while (rs.next()) {
			    	 total = rs.getInt("total");
			      	}  
			     return total;
				}
					finally{
						if(db != null)db.close();
					}	
				
	}		 
	 
		public Vector getSenaraiNoLot(String id_permohonan)throws Exception {		 							
			Db db = null;
			String sql = "";
			try{
					getSenaraiNoLot = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();								
					sql  = " SELECT DISTINCT B.ID_HAKMILIK, A.ID_PERMOHONAN, B.NO_LOT, D.STATUS_BANTAHAN_AP, ";
					sql += " E.KETERANGAN, B.NO_HAKMILIK, ";
					sql += " CASE ";
					sql += " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NULL THEN B.NO_LOT ";
					sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NULL THEN lt.keterangan || B.NO_PT ";
					sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NULL THEN lt.keterangan || B.NO_PT ";
					sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NOT NULL THEN lt.keterangan || B.NO_PT ";
					sql += " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NOT NULL THEN lt.keterangan || B.NO_PT || CHR(32) || CHR(40) || B.NO_LOT || CHR(41) ";
					sql += " ELSE ''  ";
					sql += " END AS NO_LOTPT, D.FLAG_ONLINE ";   
					sql += " FROM TBLPPTPERMOHONAN A,TBLPPTHAKMILIK B, ";
					sql += " TBLPPTBANTAHAN D,TBLRUJSTATUS E,Tblrujlot lt ";
					sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN ";
					sql += " AND D.STATUS_BANTAHAN_AP = e.ID_STATUS(+) ";
					sql += " AND B.ID_HAKMILIK = D.ID_HAKMILIK(+) ";
					sql += " AND D.ID_HAKMILIKPB IS NULL ";	
					sql += " AND B.id_lot = lt.id_lot(+) ";
					sql += " AND A.ID_PERMOHONAN = '"+ id_permohonan +"' ";
					ResultSet rs = stmt.executeQuery(sql);
					myLogger.info("SQL getSenaraiNoLot :: "+sql);
					Hashtable h;
					int bil = 1;
			     while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("bil", bil);
			    	h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
			    	h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
			    	h.put("no_lot", rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));	    	
			    	h.put("status_bantahan_ap", rs.getString("STATUS_BANTAHAN_AP")==null?"":rs.getString("STATUS_BANTAHAN_AP"));
			    	h.put("keteranganStatusBantahan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
			    	h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
			    	h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
			    	h.put("flag_online", rs.getString("FLAG_ONLINE")==null?"":rs.getString("FLAG_ONLINE"));
			    	getSenaraiNoLot.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
				return getSenaraiNoLot;
	}		 
	 
		public static Hashtable getAddAgensi(String id_permohonan,String id_hakmilik) throws Exception { 
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();		      
		      sql  =  " SELECT A.ID_KEMENTERIAN,D.NAMA_KEMENTERIAN,D.ALAMAT1,D.ALAMAT2,D.ALAMAT3,D.POSKOD,D.ID_NEGERI,E.NAMA_NEGERI ";					     
		      sql +=  " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";	
		      sql +=  " TBLPPTHAKMILIK C,TBLRUJKEMENTERIAN D, ";
		      sql +=  " TBLRUJNEGERI E ";
		      sql +=  " WHERE A.ID_FAIL = B.ID_FAIL ";
		      sql +=  " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
		      sql +=  " AND D.ID_KEMENTERIAN = A.ID_KEMENTERIAN ";
		      sql +=  " AND E.ID_NEGERI = D.ID_NEGERI ";
		      sql +=  " AND B.ID_PERMOHONAN = '"+id_permohonan+"' AND C.ID_HAKMILIK = '"+id_hakmilik+"' ";	      
//		      myLogger.info("SQL getAddAgensi :: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
			    	h.put("id_kementerian", rs.getString("ID_KEMENTERIAN")==null?"":rs.getString("ID_KEMENTERIAN"));
			    	h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN")==null?"":rs.getString("NAMA_KEMENTERIAN"));
			    	h.put("alamat1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
			    	h.put("alamat2", rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
			    	h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
			    	h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
			    	h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
			    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		      }
		      return h;
		    }
		    finally {
		      if (db != null) db.close();
		    }	
		}
		
		 public Vector getNoSiasatanAP(String id_permohonan,String id_hakmilik)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			 try{
				 	getNoSiasatanAP = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					sql =  " SELECT MAX(ID_SIASATAN) AS ID_SIASATAN FROM TBLPPTSIASATAN  "; 
					sql += " WHERE ID_PERMOHONAN = '"+id_permohonan+"' AND ID_HAKMILIK = '"+id_hakmilik+"' ";

//					myLogger.info("getNoSiasatanAP :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
			     while (rs.next()) {
			    	h = new Hashtable();		    	 	    	
			    	h.put("id_siasatan", rs.getString("ID_SIASATAN")==null?"":rs.getString("ID_SIASATAN"));
			    	getNoSiasatanAP.addElement(h);		    	
			     	}      
				}
					finally{
						if(db != null)db.close();
					}	
				return getNoSiasatanAP;					
		 }	
		 
		 public Vector getTarikhPentingAP(String id_permohonan,String id_hakmilik,String id_siasatan)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			 try{
				 	getTarikhPentingAP = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					sql =  " SELECT DISTINCT F.TARIKH_SEDIA_AWARD, G.TARIKH_BORANGH "; 
					sql += " FROM TBLPPTPERMOHONAN A,TBLPPTHAKMILIK B, ";
					sql += " TBLPPTSIASATAN E,TBLPPTAWARD F, ";
					sql += " TBLPPTBORANGH H,TBLPPTBORANGG G ";
					sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN ";
					sql += " AND B.ID_HAKMILIK = E.ID_HAKMILIK ";
					sql += " AND E.ID_SIASATAN = F.ID_SIASATAN ";
					sql += " AND E.ID_SIASATAN = G.ID_SIASATAN ";
					sql += " AND F.TARIKH_SEDIA_AWARD = (SELECT MAX(AW.TARIKH_SEDIA_AWARD) FROM TBLPPTAWARD AW WHERE AW.ID_SIASATAN = '"+id_siasatan+"') ";
					sql += " AND A.ID_PERMOHONAN = E.ID_PERMOHONAN ";
					sql += " AND H.ID_BORANGG = G.ID_BORANGG ";
					sql += " AND A.ID_PERMOHONAN = '"+id_permohonan+"' AND B.ID_HAKMILIK = '"+id_hakmilik+"'  ";
					sql += " AND E.ID_SIASATAN = '"+id_siasatan+"' ";
					myLogger.info("getTarikhPentingAP :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;	
					
			     while (rs.next()) {
			    	h = new Hashtable();		    	 
			    	h.put("tarikh_sedia_award", rs.getString("TARIKH_SEDIA_AWARD")==null?"":sdf.format(rs.getDate("TARIKH_SEDIA_AWARD")));		    	
			    	h.put("tarikh_borangh", rs.getString("TARIKH_BORANGH")==null?"":sdf.format(rs.getDate("TARIKH_BORANGH")));
			    	getTarikhPentingAP.addElement(h);		    	
			     	}      
				}
					finally{
						if(db != null)db.close();
					}	
				return getTarikhPentingAP;					
		 }	
 
	public Vector<Hashtable<String,String>> getMaklumatBantahanAP(String id_permohonan, String id_hakmilik, String id_siasatan, String id_warta) 
		throws Exception {		 							
		Db db = null;
		String sql = "";
		
		try{
			getMaklumatBantahanAP = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();
//			SQLRenderer r = new SQLRenderer();				
			sql = "SELECT A.NAMA_AGENSI,"+
					" B.ID_HAKMILIK, B.ID_BANTAHAN, B.MAKLUMAT_BANTAHAN_TAMAT_TEMPOH, " +
					" B.FLAG_SEMAKAN_ONLINE, B.NO_BANTAHAN, B.JENIS_PEMBANTAH, B.TARIKH_TERIMA, B.TARIKH_BORANGN, " +
					" B.ID_PIHAKBERKEPENTINGAN, B.STATUS_BANTAHAN_AP, B.ALASAN, B.KEPENTINGANKEATAS,  " +
					" B.AMAUN_TUNTUTAN, B.FLAG_ONLINE, B.FLAG_PAMPASAN, B.FLAG_PENERIMA_PAMPASAN, B.FLAG_SYARAT, B.FLAG_UKUR_LUAS, B.TARIKH_TERIMA_AWARD," +
					" B.ID_AGENSI,B.ID_KEMENTERIAN, NVL(B.ID_NEGERI,0) ID_NEGERI, B.FLAG_BAHAGIAN_PAMPASAN, "+
					" HM.NO_LOT, HM.NO_PT, HM.NO_HAKMILIK, HM.FLAG_BANTAHAN," +
					" K.NAMA_KEMENTERIAN,K.ALAMAT1, K.ALAMAT2, K.ALAMAT3, K.POSKOD, " +
					" N.NAMA_NEGERI,"+
					" S.ID_SIASATAN, S.NO_SIASATAN,  "  +
					" ST.KETERANGAN AS DESC_STATUS_BANTAHAN_AP," +
					" W.ID_WARTA " +
					" ,A.NAMA_AGENSI"+
					" FROM TBLPPTPERMOHONAN P, TBLPPTHAKMILIK HM, TBLPPTBANTAHAN B, TBLRUJKEMENTERIAN K, TBLRUJNEGERI N, " +
					"TBLRUJAGENSI A, TBLRUJSTATUS ST, TBLPPTSIASATAN S," +
					"TBLPPTAWARD AW, TBLPPTWARTA W " +
					"	WHERE P.ID_PERMOHONAN=HM.ID_PERMOHONAN " +
					"	AND HM.ID_HAKMILIK= B.ID_HAKMILIK " +
					"	AND B.ID_KEMENTERIAN = K.ID_KEMENTERIAN " +
					"	AND B.STATUS_BANTAHAN_AP = ST.ID_STATUS " +
					"	AND B.ID_NEGERI = N.ID_NEGERI(+)" +
					"	AND B.ID_AGENSI = A.ID_AGENSI(+)" +
					"	AND HM.ID_HAKMILIK = S.ID_HAKMILIK(+) " +
					"	AND S.ID_SIASATAN = AW.ID_SIASATAN(+) " +
					"	AND W.ID_WARTA = '"+id_warta+"' " +
					"	AND P.ID_PERMOHONAN = '"+id_permohonan+"' " +
					"	AND B.ID_HAKMILIK = '"+id_hakmilik+"' " +
					"";
										           
			myLogger.info("getMaklumatBantahanAP:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
						
			Hashtable<String,String> h;			    
			while (rs.next()) {
				h = new Hashtable<String,String>();
				    	//Bantahan MT
				    	h.put("nama", rs.getString("NAMA_AGENSI")==null?rs.getString("NAMA_KEMENTERIAN"):rs.getString("NAMA_AGENSI"));
				    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				    	h.put("noPB", "");
				    	h.put("idJenisNoPB", "");
				    	h.put("idRujukanPB", rs.getString("ID_AGENSI"));
				    	h.put("umur", "0");

				    	h.put("maklumat_bantahan_tamat_tempoh", rs.getString("MAKLUMAT_BANTAHAN_TAMAT_TEMPOH")==null?"":rs.getString("MAKLUMAT_BANTAHAN_TAMAT_TEMPOH"));
				    	h.put("FLAG_SEMAKAN_ONLINE", rs.getString("FLAG_SEMAKAN_ONLINE")==null?"":rs.getString("FLAG_SEMAKAN_ONLINE"));
				    	h.put("id_siasatan", rs.getString("ID_SIASATAN")==null?"":rs.getString("ID_SIASATAN"));
				    	h.put("id_bantahan", rs.getString("ID_BANTAHAN")==null?"":rs.getString("ID_BANTAHAN"));
				    	h.put("no_bantahan", rs.getString("NO_BANTAHAN")==null?"":rs.getString("NO_BANTAHAN"));		
				    	h.put("jenis_pembantah", rs.getString("JENIS_PEMBANTAH")==null?"":rs.getString("JENIS_PEMBANTAH"));
				    	h.put("tarikh_terima", rs.getString("TARIKH_TERIMA")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA")));
				    	h.put("tarikh_borangn", rs.getString("TARIKH_BORANGN")==null?"":sdf.format(rs.getDate("TARIKH_BORANGN")));
				    	//h.put("tarikh_borangh", rs.getString("TARIKH_BORANGH")==null?"":sdf.format(rs.getDate("TARIKH_BORANGH")));
				    	h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
				    	h.put("status_bantahan_ap", rs.getString("STATUS_BANTAHAN_AP")==null?"":rs.getString("STATUS_BANTAHAN_AP"));
				    	h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
				    	h.put("alasan", rs.getString("ALASAN")==null?"":rs.getString("ALASAN"));
				    	h.put("kepentingankeatas", rs.getString("KEPENTINGANKEATAS")==null?"":rs.getString("KEPENTINGANKEATAS"));
				    	h.put("id_kementerian", rs.getString("ID_KEMENTERIAN")==null?"":rs.getString("ID_KEMENTERIAN"));
				    	h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN")==null?"":rs.getString("NAMA_KEMENTERIAN"));
				    	h.put("alamat1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
				    	h.put("alamat2", rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
				    	h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
				    	h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
				    	h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				    	h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
				    	h.put("no_pt", rs.getString("NO_PT")==null?"":rs.getString("NO_PT"));
				    	h.put("no_lot", rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
				    	h.put("flag_syarat", rs.getString("FLAG_SYARAT")==null?"":rs.getString("FLAG_SYARAT"));
				    	h.put("flag_bantahan", rs.getString("FLAG_BANTAHAN")==null?"":rs.getString("FLAG_BANTAHAN"));
				    	h.put("flag_penerima_pampasan", rs.getString("FLAG_PENERIMA_PAMPASAN")==null?"":rs.getString("FLAG_PENERIMA_PAMPASAN"));
				    	h.put("flag_bahagian_pampasan", rs.getString("FLAG_BAHAGIAN_PAMPASAN")==null?"":rs.getString("FLAG_BAHAGIAN_PAMPASAN"));
				    	h.put("flag_ukur_luas", rs.getString("FLAG_UKUR_LUAS")==null?"":rs.getString("FLAG_UKUR_LUAS"));
				    	h.put("flag_pampasan", rs.getString("FLAG_PAMPASAN")==null?"":rs.getString("FLAG_PAMPASAN"));			    	
				    	h.put("tarikh_terima_award", rs.getString("TARIKH_TERIMA_AWARD")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA_AWARD")));
				    	h.put("no_siasatan", rs.getString("NO_SIASATAN")==null?"":rs.getString("NO_SIASATAN"));
				    	h.put("amaunTuntutanF", rs.getString("AMAUN_TUNTUTAN")==null?"":lebah.util.Util.formatDecimal(rs.getDouble("AMAUN_TUNTUTAN")));
				    	h.put("txtAmaunPampasanF", rs.getString("AMAUN_TUNTUTAN")==null?"":lebah.util.Util.formatDecimal(rs.getDouble("AMAUN_TUNTUTAN")));
				    	h.put("txtAmaunTuntutanF", rs.getString("AMAUN_TUNTUTAN")==null?"":lebah.util.Util.formatDecimal(rs.getDouble("AMAUN_TUNTUTAN")));
				    	
				    	h.put("amaun_tuntutan", rs.getString("AMAUN_TUNTUTAN")==null?"":rs.getString("AMAUN_TUNTUTAN"));
				    	h.put("txtAmaunPampasan", rs.getString("AMAUN_TUNTUTAN")==null?"":rs.getString("AMAUN_TUNTUTAN"));
				    	h.put("txtAmaunTuntutan", rs.getString("AMAUN_TUNTUTAN")==null?"":rs.getString("AMAUN_TUNTUTAN"));
//				    	h.put("amaun_tuntutan", rs.getString("AMAUN_TUNTUTAN")==null?"":Double.parseDouble(rs.getString("AMAUN_TUNTUTAN")));
//				    	h.put("txtAmaunPampasan", rs.getString("AMAUN_TUNTUTAN")==null?"":Double.parseDouble(rs.getString("AMAUN_TUNTUTAN")));
//				    	h.put("txtAmaunTuntutan", rs.getString("AMAUN_TUNTUTAN")==null?"":Double.parseDouble(rs.getString("AMAUN_TUNTUTAN")));
				    	h.put("id_warta", rs.getString("ID_WARTA")==null?"":rs.getString("ID_WARTA"));
				    	h.put("desc_status_bantahan_ap", rs.getString("DESC_STATUS_BANTAHAN_AP")==null?"":rs.getString("DESC_STATUS_BANTAHAN_AP"));
				    	h.put("nama_agensi", rs.getString("NAMA_AGENSI")==null?"":rs.getString("NAMA_AGENSI"));
				    	h.put("flag_online", rs.getString("FLAG_ONLINE")==null?"":rs.getString("FLAG_ONLINE"));				    	
				    	getMaklumatBantahanAP.addElement(h);		    	
				   
			}      
			
		}finally{
			if(db != null)db.close();
		}	
		return getMaklumatBantahanAP;
		
	}		 

			public Vector getMaklumatPampasanAP(String id_hakmilik) throws Exception {		 							
				 Db db = null;
				 String sql = "";
				try{
						getMaklumatPampasanAP = new Vector();
						db = new Db();
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();				
						sql =  " SELECT (A.JUMLAH_AWARD) AS BAYAR_PAMPASAN "; 
						sql += " FROM TBLPPTHAKMILIK A ";
						sql += " WHERE A.ID_HAKMILIK = '"+id_hakmilik+"' ";
						ResultSet rs = stmt.executeQuery(sql);
						myLogger.info("getMaklumatPampasanAP :: "+sql);
						Hashtable h;			    
				     while (rs.next()) {
				    	h = new Hashtable();
//				    	h.put("amaun_bayaran", rs.getString("BAYAR_PAMPASAN")==null?"0":Double.parseDouble(rs.getString("BAYAR_PAMPASAN")));
				    	
						if(rs.getString("BAYAR_PAMPASAN") == null){
				    		h.put("amaun_bayaran","0");
				    	}else{
				    		h.put("amaun_bayaran", rs.getString("BAYAR_PAMPASAN")==null?"":Double.parseDouble(rs.getString("BAYAR_PAMPASAN")));
				    	}
				    	
				    	getMaklumatPampasanAP.addElement(h);
				      	}      
					}
						finally{
							if(db != null)db.close();
						}	
					return getMaklumatPampasanAP;
		}				
			
			public Vector getMaklumatDepositAP(String id_hakmilik) throws Exception {		 							
				 Db db = null;
				 String sql = "";
				 try{
					 	getMaklumatDepositAP = new Vector();
						db = new Db();
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();				
						sql =  " SELECT A.TARIKH_TERIMA_RESIT,A.TARIKH_RESIT,A.NO_RESIT,A.AMAUN_DEPOSIT,A.CARA_BAYAR,A.NO_RUJUKAN_BAYARAN, "; 
						sql += " A.TARIKH_SURAT_BAYARDEPOSIT,A.TARIKH_AKHIR_BAYARDEPOSIT,B.ID_BANK,B.NO_AKAUN,A.FLAG_TERIMADEPOSIT,B.NAMA_PENGHANTAR,B.NAMA_BANK ";
						sql += " FROM TBLPPTBANTAHAN A, TBLPPTBORANGO B ";
						sql += " WHERE A.ID_BANTAHAN=B.ID_BANTAHAN AND A.ID_HAKMILIK = '"+ id_hakmilik +"' ";												
//						myLogger.info("SQL getMaklumatDeposit :: "+sql);
						ResultSet rs = stmt.executeQuery(sql);					
						Hashtable h;			    
				     while (rs.next()) {
				    	h = new Hashtable();
				    	h.put("tarikh_terima_resit", rs.getString("TARIKH_TERIMA_RESIT")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA_RESIT")));
				    	h.put("tarikh_resit", rs.getString("TARIKH_RESIT")==null?"":sdf.format(rs.getDate("TARIKH_RESIT")));
				    	h.put("no_resit", rs.getString("NO_RESIT")==null?"":rs.getString("NO_RESIT"));
				    	h.put("amaun_deposit", rs.getString("AMAUN_DEPOSIT")==null?"":Double.parseDouble(rs.getString("AMAUN_DEPOSIT")));
				    	h.put("cara_bayar", rs.getString("CARA_BAYAR")==null?"":rs.getString("CARA_BAYAR"));
				    	h.put("no_rujukan_bayaran", rs.getString("NO_RUJUKAN_BAYARAN")==null?"":rs.getString("NO_RUJUKAN_BAYARAN"));
				    	h.put("tarikh_surat_bayardeposit", rs.getString("TARIKH_SURAT_BAYARDEPOSIT")==null?"":sdf.format(rs.getDate("TARIKH_SURAT_BAYARDEPOSIT")));
				    	h.put("tarikh_akhir_bayardeposit", rs.getString("TARIKH_AKHIR_BAYARDEPOSIT")==null?"":sdf.format(rs.getDate("TARIKH_AKHIR_BAYARDEPOSIT")));			    				    	
				    	h.put("id_bank", rs.getString("ID_BANK")==null?"":rs.getString("ID_BANK"));
				    	h.put("no_akaun", rs.getString("NO_AKAUN")==null?"":rs.getString("NO_AKAUN"));
				    	h.put("flag_terimadeposit", rs.getString("FLAG_TERIMADEPOSIT")==null?"":rs.getString("FLAG_TERIMADEPOSIT"));
				    	h.put("nama_penghantar", rs.getString("NAMA_PENGHANTAR")==null?"":rs.getString("NAMA_PENGHANTAR"));
				    	h.put("nama_bank", rs.getString("NAMA_BANK")==null?"":rs.getString("NAMA_BANK"));
				    	getMaklumatDepositAP.addElement(h);
				      	}      
					}
						finally{
							if(db != null)db.close();
						}	
					return getMaklumatDepositAP;
		}
			
			public static Hashtable getIdBorangOAP(String id_hakmilik,String id_bantahan) throws Exception { 
			    Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();		      
			      sql =  " SELECT O.ID_BORANGO FROM TBLPPTBANTAHAN A, TBLPPTBORANGO O WHERE A.ID_BANTAHAN = O.ID_BANTAHAN AND A.ID_HAKMILIK = '"+ id_hakmilik +"' AND O.ID_BANTAHAN = '"+ id_bantahan +"' ";					     
		      myLogger.info("SQL getIdBorangOAP :: "+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      Hashtable h = new Hashtable();
			      if (rs.next()) {
				    	h.put("id_borango", rs.getString("ID_BORANGO")==null?"":rs.getString("ID_BORANGO"));
			      }
			      return h;
			    }
			    finally {
			      if (db != null) db.close();
			    }	
			}			
			
			 public static Hashtable getCheckingIdMahkamahAP(String id_hakmilik,String id_bantahan) throws Exception {	
				    Db db = null;
				    String sql = "";
				    try {
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();		      
				      sql =  " SELECT O.ID_MAHKAMAH FROM TBLPPTBANTAHAN A, TBLPPTBORANGO O WHERE A.ID_BANTAHAN = O.ID_BANTAHAN AND A.ID_HAKMILIK = '"+ id_hakmilik +"' AND O.ID_BANTAHAN = '"+ id_bantahan +"' ";					     
//				      myLogger.info("getCheckingIdMahkamahAP :: "+sql);
				      ResultSet rs = stmt.executeQuery(sql);
				      Hashtable h = new Hashtable();
				      if (rs.next()) {
					    	h.put("id_mahkamah", rs.getString("ID_MAHKAMAH")==null?"":rs.getString("ID_MAHKAMAH"));
				      }
				      return h;
				    }
				    finally {
				      if (db != null) db.close();
				    }
			}
			
				public static Hashtable getIdAwardAP(String id_hakmilik) throws Exception { 
				    Db db = null;
				    String sql = "";
				    try {
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();		      
				      sql =  " SELECT MAX(D.ID_AWARD) AS ID_AWARD FROM TBLPPTTERIMABAYARAN A, ";
				      sql += " TBLPPTBAYARAN B,TBLPPTHAKMILIKPB C,TBLPPTAWARD D ";
				      sql += " WHERE A.ID_TERIMABAYARAN = B.ID_TERIMABAYARAN ";
				      sql += " AND B.ID_HAKMILIKPB = C.ID_HAKMILIKPB ";
				      sql += " AND C.ID_HAKMILIKPB = D.ID_HAKMILIKPB ";
				      sql += " AND A.ID_HAKMILIK = '"+id_hakmilik+"' ";
//				      myLogger.info("SQL getIdAwardAP :: "+sql);
				      ResultSet rs = stmt.executeQuery(sql);
				      Hashtable h = new Hashtable();
				      if (rs.next()) {
					    	h.put("id_award", rs.getString("ID_AWARD")==null?"":rs.getString("ID_AWARD"));
				      }
				      return h;
				    }
				    finally {
				      if (db != null) db.close();
				    }	
				}	
				
				public static Hashtable getKeteranganPampasanAP(String id_award) throws Exception { 
				    Db db = null;
				    String sql = "";
				    try {
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();		
				      
				      sql =  " SELECT KETERANGAN FROM TBLPPTTAMBAHAWARD ";	
				      sql += " WHERE ID_AWARD = '"+id_award+"' ";

				      myLogger.info("SQL getKeteranganPampasanAP :: "+sql);
				      ResultSet rs = stmt.executeQuery(sql);  
				      
				      Hashtable h = new Hashtable();
				      if (rs.next()) {
					    	h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				      }
				      return h;
				    }
				    finally {
				      if (db != null) db.close();
				    }	
				}
				
				 public Vector getHakmilikAP(String id_hakmilik)throws Exception {		 		 
					 Db db = null;
					 String sql = "";
					try{
							getHakmilikAP = new Vector();
							db = new Db();
							Statement stmt = db.getStatement();
							SQLRenderer r = new SQLRenderer();				
							sql =  " SELECT DISTINCT A.ID_HAKMILIK, A.NO_LOT, A.ID_UNITLUASLOT, B.KETERANGAN AS UNITLOT, A.NO_PT, "; 
							sql += " A.ID_UNITLUASPT, E.KETERANGAN AS UNITPT ";
							sql += " FROM TBLPPTHAKMILIK A,TBLRUJLUAS B,TBLRUJLUAS E ";
							sql += " WHERE A.ID_UNITLUASLOT = B.ID_LUAS(+) AND A.ID_UNITLUASPT = E.ID_LUAS(+) ";
							sql += " AND A.ID_HAKMILIK = '"+id_hakmilik+"' ";
//							myLogger.info("SQL getHakmilikAP :: "+sql);
							ResultSet rs = stmt.executeQuery(sql);
							Hashtable h;			    
					     while (rs.next()) {
					    	h = new Hashtable();		    	 
					        h.put("id_hakmilik", rs.getString("ID_HAKMILIK") == null ?"":rs.getString("ID_HAKMILIK"));
					    	h.put("no_lot", rs.getString("NO_LOT") == null ?"":rs.getString("NO_LOT"));
					    	h.put("id_unitluaslot", rs.getString("ID_UNITLUASLOT") == null?"":rs.getString("ID_UNITLUASLOT"));
					    	h.put("unitlot", rs.getString(4) == null ?"":rs.getString(4));
					    	h.put("no_pt", rs.getString("NO_PT") == null ?"":rs.getString("NO_PT"));
					    	h.put("id_unitluaspt", rs.getString("ID_UNITLUASPT") == null ?"":rs.getString("ID_UNITLUASPT"));
					    	h.put("unitpt", rs.getString(7) == null ?"":rs.getString(7));		    	
//					    	h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB") == null ?"":rs.getString("ID_HAKMILIKPB"));
					    	getHakmilikAP.addElement(h);
					      	}      
						}
							finally{
								if(db != null)db.close();
							}	
						return getHakmilikAP;					
			 }	
				 
					public Vector senarai_dokumen_bantahan_(String id_bantahan) throws Exception {				
					    Db db = null;
					    String sql = "";			  	    
					    try {
					      listDokumen_bantahan = new Vector();		
					      db = new Db();
					      Statement stmt = db.getStatement();
					      sql = "SELECT A.ID_BANTAHAN,A.ID_DOKUMEN, A.NAMA_FAIL, A.JENIS_MIME, A.TAJUK, A.KETERANGAN,"+ 
								" A.CONTENT  FROM TBLPPTDOKUMEN A,TBLPPTBANTAHAN P WHERE A.ID_BANTAHAN = '"+id_bantahan+"' "+
								" AND A.ID_BANTAHAN = P.ID_BANTAHAN";
					      
//					      myLogger.info("SQL DOKUMEN :"+sql.toUpperCase());
					      ResultSet rs = stmt.executeQuery(sql);
					     
					      Hashtable h;
					      int bil = 0;
					    
					      while (rs.next()) {
					    	
					    	  bil = bil + 1;
					    	  h = new Hashtable();			    	 
					    	  h.put("BIL", bil);
					    	  h.put("ID_BANTAHAN",rs.getString("ID_BANTAHAN")== null?"":rs.getString("ID_BANTAHAN"));
					    	  h.put("ID_DOKUMEN", rs.getString("ID_DOKUMEN")== null?"":rs.getString("ID_DOKUMEN"));
					    	  h.put("NAMA_FAIL", rs.getString("NAMA_FAIL")== null?"":rs.getString("NAMA_FAIL"));
					    	  h.put("JENIS_MIME",rs.getString("JENIS_MIME")== null?"":rs.getString("JENIS_MIME"));
					    	  h.put("TAJUK",rs.getString("TAJUK")== null?"":rs.getString("TAJUK"));
					    	  h.put("KETERANGAN",rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));          
					    	  listDokumen_bantahan.addElement(h);
					      }		      
					      return listDokumen_bantahan;
					      
					    } finally {
					      if (db != null) db.close();
					    }	
					}	
					
					public Vector view_details_dokumen(String id_dokumen) throws Exception {
						 Db db = null;
						 String sql = "";
						 try
						 {
							 view_details_dokumen = new Vector();
							 db = new Db();
							 Statement stmt = db.getStatement();
							 sql = " SELECT ID_DOKUMEN,TAJUK,KETERANGAN,NAMA_FAIL,CONTENT,JENIS_MIME,NO_RUJUKAN,ID_BANTAHAN FROM TBLPPTDOKUMEN "+ 
							 	   " WHERE ID_DOKUMEN = '"+ id_dokumen +"' ";		
							 myLogger.info("view_details_dokumen ::"+sql);
							 ResultSet rs = stmt.executeQuery(sql);
						     
						      Hashtable h;
						      int bil = 0;
						    
						      while (rs.next()) {
						    	  bil = bil + 1;
						    	  h = new Hashtable();			    	 
						    	  h.put("BIL", bil);
						    	  h.put("ID_BANTAHAN",rs.getString("ID_BANTAHAN")== null?"":rs.getString("ID_BANTAHAN"));
						    	  h.put("ID_DOKUMEN", rs.getString("ID_DOKUMEN")== null?"":rs.getString("ID_DOKUMEN"));
						    	  h.put("NAMA_FAIL", rs.getString("NAMA_FAIL")== null?"":rs.getString("NAMA_FAIL"));
						    	  h.put("JENIS_MIME",rs.getString("JENIS_MIME")== null?"":rs.getString("JENIS_MIME"));
						    	  h.put("TAJUK",rs.getString("TAJUK")== null?"":rs.getString("TAJUK"));
						    	  h.put("KETERANGAN",rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
						          
						    	  view_details_dokumen.addElement(h);		    	 	    	
								}
						 	}
								finally{
									if(db != null)db.close();
								}	
							return view_details_dokumen;			
					}

					 public Vector getNoWarta(String id_permohonan)throws Exception {		 		 
						 Db db = null;
						 String sql = "";
						 try{
							 	getNoWarta = new Vector();
								db = new Db();
								Statement stmt = db.getStatement();
								SQLRenderer r = new SQLRenderer();				
								sql =  " SELECT ID_WARTA,NO_WARTA FROM TBLPPTWARTA A WHERE ID_PERMOHONAN = '"+id_permohonan+"' "; 
								sql += " AND ID_WARTA = (SELECT MAX(ID_WARTA) FROM TBLPPTWARTA WHERE ID_PERMOHONAN = A.ID_PERMOHONAN) ";
//								myLogger.info("getNoWarta :: "+sql);
								ResultSet rs = stmt.executeQuery(sql);
								Hashtable h;	
						     while (rs.next()) {
						    	h = new Hashtable();		    	 
						    	h.put("id_warta", rs.getString("ID_WARTA")==null?"":rs.getString("ID_WARTA"));		    	
						    	h.put("no_warta", rs.getString("NO_WARTA")==null?"":rs.getString("NO_WARTA"));
						    	getNoWarta.addElement(h);		    	
						     	}      
							}
								finally{
									if(db != null)db.close();
								}	
							return getNoWarta;					
					 }	 				

						public Vector getAlamatMahkamah(String idNegeriMhn,String id_pejabat) throws Exception {
							String key = "getAlamatMahkamah"+id_pejabat;
							Element cachedObject = myCache.get(key);
							if (cachedObject != null) {
							  return (Vector)cachedObject.getObjectValue();
							} else {		 							
								 Db db = null;
								 String sql = "";
								 try{
									 	getAlamatMahkamah = new Vector();
										db = new Db();
										Statement stmt = db.getStatement();
										SQLRenderer r = new SQLRenderer();				
										sql =  " SELECT PJ.ID_PEJABAT, PJ.KOD_PEJABAT, PJ.NAMA_PEJABAT, PJ.ALAMAT1,PJ.ALAMAT2, PJ.ALAMAT3, PJ.POSKOD, PJ.NO_TEL, PJ.NO_FAX,DA.NAMA_DAERAH,DA.ID_DAERAH,PJ.ID_BANDAR,PJ.ID_NEGERI "; 
										sql += " FROM TBLRUJPEJABAT PJ, TBLRUJDAERAH da ";
										sql += " WHERE PJ.ID_JENISPEJABAT = 8 AND PJ.ID_DAERAH = da.ID_DAERAH ";
										sql += " AND PJ.ID_NEGERI = '"+ idNegeriMhn +"' and PJ.ID_PEJABAT = '"+ id_pejabat +"' ";															
//										myLogger.info("SQL getAlamatMahkamah :: "+sql);
										ResultSet rs = stmt.executeQuery(sql);					
										Hashtable h;			    
								     while (rs.next()) {
								    	h = new Hashtable();
								    	h.put("id_pejabat", rs.getString("ID_PEJABAT")==null?"":rs.getString("ID_PEJABAT"));
								    	h.put("kod_pejabat", rs.getString("KOD_PEJABAT")==null?"":rs.getString("KOD_PEJABAT"));
								    	h.put("nama_pejabat", rs.getString("NAMA_PEJABAT")==null?"":rs.getString("NAMA_PEJABAT"));
								    	h.put("alamat1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
								    	h.put("alamat2", rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
								    	h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
								    	h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
								    	h.put("no_tel", rs.getString("NO_TEL")==null?"":rs.getString("NO_TEL"));
								    	h.put("no_fax", rs.getString("NO_FAX")==null?"":rs.getString("NO_FAX"));
								    	h.put("nama_daerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
								    	h.put("id_daerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
								    	h.put("id_bandar", rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
								    	h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
								    	getAlamatMahkamah.addElement(h);
								      	}      
									}
										finally{
											if(db != null)db.close();
										}	
									myCache.put(new Element(key, getAlamatMahkamah));
									return getAlamatMahkamah;
							}
						}

				 
					 
}
