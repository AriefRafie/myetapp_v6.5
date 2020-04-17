package ekptg.model.htp.rekod;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class FrmHakmilikRizabPembangunanBean implements IHakmilikRizab {
	
	private HakmilikInterface iHakmilik = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.FrmHakmilikRizabPembangunanBean.class);
	private static Vector listCarianHakmilikDanRizab = null;
	private HakMilik hakmilik = null;
 	private IHtp iHTP = null;  
 	private PfdFail pfdFail = null;
 	private Permohonan permohonan = null;
	private String sql = "";
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private	Db db = null;
	
	//PAPAR CARIAN HAKMILIK DAN RIZAB
	@Override
	public Vector getCarianSenaraiHakmilikRizab(String idJenisTanah
			,String idNegeri, String idDaerah,String idMukim
			,String noFail,String idJenisHakmilik,String noHakmilikWarta
			,String idLot,String noLot, String idAgensi, String idKementerian
			,String idStatus)throws Exception {
	    Db db = null;
	    listCarianHakmilikDanRizab = new Vector();
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();  
	    	sql = "SELECT DISTINCT A.ID_HAKMILIK,C.NO_FAIL, "+
	    		"CASE "+
	            "WHEN E.KOD_JENIS_HAKMILIK = '00' THEN '' "+
	            "ELSE E.KOD_JENIS_HAKMILIK "+
	            "END AS JENIS_HAKMILIK, A.NO_HAKMILIK, A.NO_WARTA, "+
	            "F.KETERANGAN AS JENIS_LOT, A.NO_LOT, D.NAMA_URUSAN, "+
	            "CASE "+
	            "WHEN A.STATUS_SAH = 'H' "+
	                "THEN 'HAKMILIK ASAL TIADA' "+
	             "WHEN A.STATUS_SAH = 'D' "+
	                "THEN 'DAFTAR' "+
	             "WHEN A.STATUS_SAH = 'P' "+
	                "THEN 'BATAL (PELEPASAN)' "+
	             "WHEN A.STATUS_SAH = 'T' "+
	                "THEN 'TUKAR GUNA' "+ 
	             "WHEN A.STATUS_SAH = 'S' "+
	                "THEN 'BATAL(SAMBUNGAN)' "+
	             "WHEN A.STATUS_SAH = 'B' "+
	                "THEN 'BATAL' "+
	             "WHEN A.STATUS_SAH = 'M' "+
		         	"THEN 'BATAL (PINDAHMILIK)' "+
		         "WHEN A.STATUS_SAH = 'Q' "+
			     	"THEN 'BATAL (PERMOHONAN)' "+
				 "WHEN A.STATUS_SAH = 'L' "+
				 	"THEN 'BATAL (PERLETAKHAKAN)' "+		         	
			     "ELSE 'TIADA' "+
	             "END AS STATUS_HAKMILIK, "+
	             "A.STATUS_RIZAB, A.TARIKH_TERIMA,A.KEGUNAAN_TANAH "+
	             "FROM TBLHTPHAKMILIK A, "+
	             "TBLPERMOHONAN B, "+
	             "TBLPFDFAIL C, "+
	             "TBLRUJURUSAN D, "+
	             "TBLRUJJENISHAKMILIK E, "+
	             "TBLRUJLOT F, "+
	             "TBLRUJKEMENTERIAN RK," +
	             "TBLRUJKEMENTERIANMAPPING RKME "+
	             "WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
	             "AND B.ID_FAIL = C.ID_FAIL "+
	             "AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
	             "AND A.ID_LOT = F.ID_LOT "+
	             "AND C.ID_URUSAN = D.ID_URUSAN "+
	             "AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
	             "AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN "+
	             "";
	          
			//idJenisTanah
			if (idJenisTanah != null) {
				if (!idJenisTanah.trim().equals("")) {
					if (Integer.parseInt(idJenisTanah) == 1){
						sql = sql + " AND NVL(A.NO_HAKMILIK,' ') <> ' ' ";
					} else if (Integer.parseInt(idJenisTanah) == 2){
						sql = sql + " AND NVL(A.NO_WARTA,' ') <> ' ' ";
					}
				}
			}
	           
			//id negeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")) {
					if (!idNegeri.trim().equals("99999")) {
						sql = sql + " AND A.ID_NEGERI = '" + idNegeri + "'";
					}
				}
			}
			//id daerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("")) {
					if (!idDaerah.trim().equals("99999")) {
						sql = sql + " AND A.ID_DAERAH = '" + idDaerah + "'";
					}
				}
			}
			//id mukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("")) {
					if (!idMukim.trim().equals("99999")) {
						sql = sql + " AND A.ID_MUKIM = '" + idMukim + "'";
					}
				}
			}	      
			//no fail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(C.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase() + "'|| '%'";
				}
			}
			//id jenishakmilik
			if (idJenisHakmilik != null) {
				if (!idJenisHakmilik.trim().equals("")) {
					if (!idJenisHakmilik.trim().equals("99999") && !idJenisHakmilik.trim().equals("00")) {
						sql = sql + " AND A.ID_JENISHAKMILIK = '" + idJenisHakmilik + "'";
					}
				}
			} 
			//no hakmilik
			if (noHakmilikWarta != null) {
				if (!noHakmilikWarta.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_HAKMILIK) LIKE '%' ||'" + noHakmilikWarta.toUpperCase() + "'|| '%'";
				}
			}       
//			//no warta
//			if (noWarta != null) {
//				if (!noWarta.trim().equals("")) {
//					sql = sql + " AND UPPER(A.NO_WARTA) LIKE '%' ||'" + noWarta.toUpperCase() + "'|| '%'";
//				}
//			}   
			//id lot
			if (idLot != null) {
				if (!idLot.trim().equals("")) {
					if (!idLot.trim().equals("99999")) {
						sql = sql + " AND A.ID_LOT = '" + idLot + "'";
					}
				}
			} 
			//no lot
			if (noLot != null) {
				if (!noLot.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_LOT) LIKE '%' ||'" + noLot.toUpperCase() + "'|| '%'";
				}
			} 
			//id Kementerian
			if (idKementerian != null) {
				if (!idKementerian.trim().equals("")) {
					if (!idKementerian.trim().equals("99999")) {
						//20/09/2010
						sql = sql + " AND A.ID_KEMENTERIAN = '" + idKementerian + "'";
					}
				}
			}
			//no agensi
			if (idAgensi != null) {
				if (!idAgensi.trim().equals("")) {
					if (!idAgensi.trim().equals("99999")) {
						sql = sql + " AND A.ID_AGENSI = '" + idAgensi + "'";
					}
				}
			} 
			//idStatus
			if (idStatus != null) {
				if (!idStatus.trim().equals("")) {
					if (idStatus.trim().equals("1")) {
						//AKTIF
						sql = sql + " AND (A.STATUS_SAH IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
						"))";						
						
					} else if (idStatus.trim().equals("2")) {
						sql = sql + " AND (A.STATUS_SAH IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
						"))";						
					
					}else{
						//AKTIF
						sql = sql + " AND (A.STATUS_SAH IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
						"))";						
					
					}
					
				}
				
			}
			
			sql = sql +" ORDER BY C.NO_FAIL DESC ";
			myLog.debug("getCarianSenaraiHakmilikRizabById:CARIAN::sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);	    
			Hashtable h;
	      	int bil = 1;
	      	int count = 0;

	      	while (rs.next()) {
	      		h = new Hashtable();
	      		h.put("bil", bil+".");
	      		h.put("idHakmilik",rs.getString("ID_HAKMILIK"));
	      		h.put("noFail",rs.getString("NO_FAIL"));
	      		h.put("kodJenis", rs.getString("JENIS_HAKMILIK")== null?"":rs.getString("JENIS_HAKMILIK"));
	      		h.put("noHakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
	      		h.put("noWarta", rs.getString("NO_WARTA")== null?"":rs.getString("NO_WARTA"));
	      		h.put("kodLot", rs.getString("JENIS_LOT")== null?"":rs.getString("JENIS_LOT"));
	      		h.put("noLot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
	      		h.put("namaUrusan", rs.getString("NAMA_URUSAN")== null?"":rs.getString("NAMA_URUSAN"));
	      		h.put("statusSah", rs.getString("STATUS_HAKMILIK")== null?"":rs.getString("STATUS_HAKMILIK"));
	      		h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH")== null?"":rs.getString("KEGUNAAN_TANAH").toUpperCase());
	      		h.put("statusRizab", rs.getString("STATUS_RIZAB")== null? "":rs.getString("STATUS_RIZAB"));
	      		h.put("tarikhTerima",rs.getDate("TARIKH_TERIMA")== null? "":sdf.format(rs.getDate("TARIKH_TERIMA")));
	    	  
	      		if(rs.getString("NO_HAKMILIK")!= null){
	      			h.put("jenisTanah","M");
	      		}else{
	      			h.put("jenisTanah","R");
	      		}
	      		if(rs.getString("NO_HAKMILIK")!= null && rs.getString("NO_WARTA")!= null){
	      			h.put("jenisTanah","X");
	      		}
	      		listCarianHakmilikDanRizab.addElement(h);
	      		bil++;
	      		count++;
	    	  
	      	}
	      	//azam remark - no need to add empty hashtable

	    } finally {
	      if (db != null) db.close();
	    
	    }		
	    return listCarianHakmilikDanRizab;
	    
	}	
	
	@Override
	public Vector getCarianSenaraiHakmilikRizab(String idJenisTanah
			,String idNegeri, String idDaerah,String idMukim
			,String noFail,String idJenisHakmilik,String noHakmilik, String noWarta
			,String idLot,String noLot, String idAgensi, String idKementerian
			,String idStatus)throws Exception {
	    Db db = null;
	    listCarianHakmilikDanRizab = new Vector();
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();  
	    	sql = "SELECT DISTINCT A.ID_HAKMILIK,C.NO_FAIL, "+
	    		"CASE "+
	            "WHEN E.KOD_JENIS_HAKMILIK = '00' THEN '' "+
	            "ELSE E.KOD_JENIS_HAKMILIK "+
	            "END AS JENIS_HAKMILIK, A.NO_HAKMILIK, A.NO_WARTA, "+
	            "F.KETERANGAN AS JENIS_LOT, A.NO_LOT, D.NAMA_URUSAN, "+
	            "CASE "+
	            "WHEN A.STATUS_SAH = 'H' "+
	                "THEN 'HAKMILIK ASAL TIADA' "+
	             "WHEN A.STATUS_SAH = 'D' "+
	                "THEN 'DAFTAR' "+
	             "WHEN A.STATUS_SAH = 'P' "+
	                "THEN 'BATAL (PELEPASAN)' "+
	             "WHEN A.STATUS_SAH = 'T' "+
	                "THEN 'TUKAR GUNA' "+ 
	             "WHEN A.STATUS_SAH = 'S' "+
	                "THEN 'BATAL(SAMBUNGAN)' "+
	             "WHEN A.STATUS_SAH = 'B' "+
	                "THEN 'BATAL' "+
	             "WHEN A.STATUS_SAH = 'M' "+
		         	"THEN 'BATAL (PINDAHMILIK)' "+
		         "WHEN A.STATUS_SAH = 'Q' "+
			     	"THEN 'BATAL (PERMOHONAN)' "+
				 "WHEN A.STATUS_SAH = 'L' "+
				 	"THEN 'BATAL (PERLETAKHAKAN)' "+		         	
			     "ELSE 'TIADA' "+
	             "END AS STATUS_HAKMILIK, "+
	             "A.STATUS_RIZAB, A.TARIKH_TERIMA,A.KEGUNAAN_TANAH "+
	             "FROM TBLHTPHAKMILIK A, "+
	             "TBLPERMOHONAN B, "+
	             "TBLPFDFAIL C, "+
	             "TBLRUJURUSAN D, "+
	             "TBLRUJJENISHAKMILIK E, "+
	             "TBLRUJLOT F, "+
	             "TBLRUJKEMENTERIAN RK," +
	             "TBLRUJKEMENTERIANMAPPING RKME "+
	             "WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
	             "AND B.ID_FAIL = C.ID_FAIL "+
	             "AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
	             "AND A.ID_LOT = F.ID_LOT "+
	             "AND C.ID_URUSAN = D.ID_URUSAN "+
	             "AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
	             "AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN AND RKME.STATUS='A' " +
	             "AND A.ID_HAKMILIK NOT IN " +
	             "	(SELECT TPHP.ID_HAKMILIK FROM" +
	             "	TBLHTPHAKMILIKPERIHAL TPHP " +
	             "	WHERE  " +
	             "	TPHP.LUAS_BERSAMAAN < A.LUAS_BERSAMAAN " +
	             "	 "+
	             "	 " +
	             "	) "+
	             "";
	          
			//idJenisTanah
			if (idJenisTanah != null) {
				if (!idJenisTanah.trim().equals("")) {
					if (Integer.parseInt(idJenisTanah) == 1){
						sql = sql + " AND NVL(A.NO_HAKMILIK,' ') <> ' ' ";
					} else if (Integer.parseInt(idJenisTanah) == 2){
						sql = sql + " AND NVL(A.NO_WARTA,' ') <> ' ' ";
					}
				}
			}
	           
			//id negeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")) {
					if (!idNegeri.trim().equals("99999")) {
						sql = sql + " AND A.ID_NEGERI = '" + idNegeri + "'";
					}
				}
			}
			//id daerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("")) {
					if (!idDaerah.trim().equals("99999")) {
						sql = sql + " AND A.ID_DAERAH = '" + idDaerah + "'";
					}
				}
			}
			//id mukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("")) {
					if (!idMukim.trim().equals("99999")) {
						sql = sql + " AND A.ID_MUKIM = '" + idMukim + "'";
					}
				}
			}	      
			//no fail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(C.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase() + "'|| '%'";
				}
			}
			//id jenishakmilik
			if (idJenisHakmilik != null) {
				if (!idJenisHakmilik.trim().equals("")) {
					if (!idJenisHakmilik.trim().equals("99999") && !idJenisHakmilik.trim().equals("00")) {
						sql = sql + " AND A.ID_JENISHAKMILIK = '" + idJenisHakmilik + "'";
					}
				}
			} 
			//no hakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_HAKMILIK) LIKE '%' ||'" + noHakmilik.toUpperCase() + "'|| '%'";
				}
			}       
//			//no warta
			if (noWarta != null) {
				if (!noWarta.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_WARTA) LIKE '%' ||'" + noWarta.toUpperCase() + "'|| '%'";
				}
			}   
			//id lot
			if (idLot != null) {
				if (!idLot.trim().equals("")) {
					if (!idLot.trim().equals("99999")) {
						sql = sql + " AND A.ID_LOT = '" + idLot + "'";
					}
				}
			} 
			//no lot
			if (noLot != null) {
				if (!noLot.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_LOT) LIKE '%' ||'" + noLot.toUpperCase() + "'|| '%'";
				}
			} 
			//id Kementerian
			if (idKementerian != null) {
				if (!idKementerian.trim().equals("")) {
					if (!idKementerian.trim().equals("99999")) {
						//20/09/2010
						sql = sql + " AND A.ID_KEMENTERIAN = '" + idKementerian + "'";
					}
				}
			}
			//no agensi
			if (idAgensi != null) {
				if (!idAgensi.trim().equals("")) {
					if (!idAgensi.trim().equals("99999")) {
						sql = sql + " AND A.ID_AGENSI = '" + idAgensi + "'";
					}
				}
			} 
			//idStatus
			if (idStatus != null) {
				if (!idStatus.trim().equals("")) {
					if (idStatus.trim().equals("1")) {
						//AKTIF
						sql = sql + " AND (A.STATUS_SAH IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
						"))";						
						
					} else if (idStatus.trim().equals("2")) {
						sql = sql + " AND (A.STATUS_SAH IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
						"))";						
					
					}else{
						//AKTIF
						sql = sql + " AND (A.STATUS_SAH IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
						"))";						
					
					}
					
				}
				
			}
			
			sql = sql +" ORDER BY C.NO_FAIL DESC ";
			myLog.debug("getCarianSenaraiTanahDiceroboh:CARIAN::sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);	    
			Hashtable h;
	      	int bil = 1;
	      	int count = 0;

	      	while (rs.next()) {
	      		h = new Hashtable();
	      		h.put("bil", bil+".");
	      		h.put("idHakmilik",rs.getString("ID_HAKMILIK"));
	      		h.put("noFail",rs.getString("NO_FAIL"));
	      		h.put("kodJenis", rs.getString("JENIS_HAKMILIK")== null?"":rs.getString("JENIS_HAKMILIK"));
	      		h.put("noHakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
	      		h.put("noWarta", rs.getString("NO_WARTA")== null?"":rs.getString("NO_WARTA"));
	      		h.put("kodLot", rs.getString("JENIS_LOT")== null?"":rs.getString("JENIS_LOT"));
	      		h.put("noLot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
	      		h.put("namaUrusan", rs.getString("NAMA_URUSAN")== null?"":rs.getString("NAMA_URUSAN"));
	      		h.put("statusSah", rs.getString("STATUS_HAKMILIK")== null?"":rs.getString("STATUS_HAKMILIK"));
	      		h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH")== null?"":rs.getString("KEGUNAAN_TANAH").toUpperCase());
	      		h.put("statusRizab", rs.getString("STATUS_RIZAB")== null? "":rs.getString("STATUS_RIZAB"));
	      		h.put("tarikhTerima",rs.getDate("TARIKH_TERIMA")== null? "":sdf.format(rs.getDate("TARIKH_TERIMA")));
	    	  
	      		if(rs.getString("NO_HAKMILIK")!= null){
	      			h.put("jenisTanah","M");
	      		}else{
	      			h.put("jenisTanah","R");
	      		}
	      		if(rs.getString("NO_HAKMILIK")!= null && rs.getString("NO_WARTA")!= null){
	      			h.put("jenisTanah","X");
	      		}
	      		listCarianHakmilikDanRizab.addElement(h);
	      		bil++;
	      		count++;
	    	  
	      	}

	    } finally {
	      if (db != null) db.close();
	    
	    }		
	    return listCarianHakmilikDanRizab;
	    
	}	

	@Override
	public Vector getCarianSenaraiHakmilikRizab(String idJenisTanah
			,String idNegeri, String idDaerah,String idMukim
			,String noFail,String idJenisHakmilik,String noHakmilik
			,String noWarta
			,String idLot,String noLot
			,String idAgensi, String idKementerian
			,String idStatus, String kegunaan)throws Exception {
	    Db db = null;
	    listCarianHakmilikDanRizab = new Vector();
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();  
	    	sql = "SELECT DISTINCT A.ID_HAKMILIK,C.NO_FAIL, "+
	    		"CASE "+
	            "WHEN E.KOD_JENIS_HAKMILIK = '00' THEN '' "+
	            "ELSE E.KOD_JENIS_HAKMILIK "+
	            "END AS JENIS_HAKMILIK, A.NO_HAKMILIK, A.NO_WARTA, "+
	            "F.KETERANGAN AS JENIS_LOT, A.NO_LOT, D.NAMA_URUSAN, "+
	            "CASE "+
	            "WHEN A.STATUS_SAH = 'H' "+
	                "THEN 'HAKMILIK ASAL TIADA' "+
	             "WHEN A.STATUS_SAH = 'D' "+
	                "THEN 'DAFTAR' "+
	             "WHEN A.STATUS_SAH = 'P' "+
	                "THEN 'BATAL (PELEPASAN)' "+
	             "WHEN A.STATUS_SAH = 'T' "+
	                "THEN 'TUKAR GUNA' "+ 
	             "WHEN A.STATUS_SAH = 'S' "+
	                "THEN 'BATAL(SAMBUNGAN)' "+
	             "WHEN A.STATUS_SAH = 'B' "+
	                "THEN 'BATAL' "+
	             "WHEN A.STATUS_SAH = 'M' "+
		         	"THEN 'BATAL (PINDAHMILIK)' "+
		         "WHEN A.STATUS_SAH = 'Q' "+
			     	"THEN 'BATAL (PERMOHONAN)' "+
				 "WHEN A.STATUS_SAH = 'L' "+
				 	"THEN 'BATAL (PERLETAKHAKAN)' "+		         	
			     "ELSE 'TIADA' "+
	             "END AS STATUS_HAKMILIK, "+
	             "A.STATUS_RIZAB, A.TARIKH_TERIMA,A.KEGUNAAN_TANAH "+
	             "FROM TBLHTPHAKMILIK A, "+
	             "TBLPERMOHONAN B, "+
	             "TBLPFDFAIL C, "+
	             "TBLRUJURUSAN D, "+
	             "TBLRUJJENISHAKMILIK E, "+
	             "TBLRUJLOT F, "+
	             "TBLRUJKEMENTERIAN RK," +
	             "TBLRUJKEMENTERIANMAPPING RKME "+
	             "WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
	             "AND B.ID_FAIL = C.ID_FAIL "+
	             "AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
	             "AND A.ID_LOT = F.ID_LOT "+
	             "AND C.ID_URUSAN = D.ID_URUSAN "+
	             "AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
	             "AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN AND RKME.STATUS='A' " +
	             "AND A.ID_HAKMILIK NOT IN " +
	             "	(SELECT TPHP.ID_HAKMILIK FROM" +
	             "	TBLHTPHAKMILIKPERIHAL TPHP " +
	             "	WHERE  " +
	             "	TPHP.LUAS_BERSAMAAN < A.LUAS_BERSAMAAN " +
	             "	 "+
	             "	 " +
	             "	) "+
	             "";	          
			//idJenisTanah
			if (idJenisTanah != null) {
				if (!idJenisTanah.trim().equals("")) {
					if (Integer.parseInt(idJenisTanah) == 1){
						sql = sql + " AND NVL(A.NO_HAKMILIK,' ') <> ' ' ";
					} else if (Integer.parseInt(idJenisTanah) == 2){
						sql = sql + " AND NVL(A.NO_WARTA,' ') <> ' ' ";
					}
				}
			}           
			//id negeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")) {
					if (!idNegeri.trim().equals("99999")) {
						sql = sql + " AND A.ID_NEGERI = '" + idNegeri + "'";
					}
				}
			}
			//id daerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("")) {
					if (!idDaerah.trim().equals("99999")) {
						sql = sql + " AND A.ID_DAERAH = '" + idDaerah + "'";
					}
				}
			}
			//id mukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("")) {
					if (!idMukim.trim().equals("99999")) {
						sql = sql + " AND A.ID_MUKIM = '" + idMukim + "'";
					}
				}
			}	      
			//no fail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(C.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase() + "'|| '%'";
				}
			}
			//id jenishakmilik
			if (idJenisHakmilik != null) {
				if (!idJenisHakmilik.trim().equals("")) {
					if (!idJenisHakmilik.trim().equals("99999") && !idJenisHakmilik.trim().equals("00")) {
						sql = sql + " AND A.ID_JENISHAKMILIK = '" + idJenisHakmilik + "'";
					}
				}
			} 
			//no hakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_HAKMILIK) LIKE '%' ||'" + noHakmilik.toUpperCase() + "'|| '%'";
				}
			}       
//			//no warta
			if (noWarta != null) {
				if (!noWarta.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_WARTA) LIKE '%' ||'" + noWarta.toUpperCase() + "'|| '%'";
				}
			}   
			//id lot
			if (idLot != null) {
				if (!idLot.trim().equals("")) {
					if (!idLot.trim().equals("99999")) {
						sql = sql + " AND A.ID_LOT = '" + idLot + "'";
					}
				}
			} 
			//no lot
			if (noLot != null) {
				if (!noLot.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_LOT) LIKE '%' ||'" + noLot.toUpperCase() + "'|| '%'";
				}
			} 
			//id Kementerian
			if (idKementerian != null) {
				if (!idKementerian.trim().equals("")) {
					if (!idKementerian.trim().equals("99999")) {
						//20/09/2010
						sql = sql + " AND A.ID_KEMENTERIAN = '" + idKementerian + "'";
					}
				}
			}
			//no agensi
			if (idAgensi != null) {
				if (!idAgensi.trim().equals("")) {
					if (!idAgensi.trim().equals("99999")) {
						sql = sql + " AND A.ID_AGENSI = '" + idAgensi + "'";
					}
				}
			} 
			//idStatus
			if (idStatus != null) {
				if (!idStatus.trim().equals("")) {
					if (idStatus.trim().equals("1")) {
						//AKTIF
						sql = sql + " AND (A.STATUS_SAH IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
						"))";						
						
					} else if (idStatus.trim().equals("2")) {
						sql = sql + " AND (A.STATUS_SAH IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
						"))";						
					
					}else{
						//AKTIF
						sql = sql + " AND (A.STATUS_SAH IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
						"))";						
					
					}
					
				}
				
			}
			
			sql = sql +" ORDER BY C.NO_FAIL DESC ";
			myLog.debug("getCarianSenaraiTanahDiceroboh:CARIAN::sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);	    
			Hashtable h;
	      	int bil = 1;
	      	int count = 0;
	      	while (rs.next()) {
	      		h = new Hashtable();
	      		h.put("bil", bil+".");
	      		h.put("idHakmilik",rs.getString("ID_HAKMILIK"));
	      		h.put("noFail",rs.getString("NO_FAIL"));
	      		h.put("kodJenis", rs.getString("JENIS_HAKMILIK")== null?"":rs.getString("JENIS_HAKMILIK"));
	      		h.put("noHakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
	      		h.put("noWarta", rs.getString("NO_WARTA")== null?"":rs.getString("NO_WARTA"));
	      		h.put("kodLot", rs.getString("JENIS_LOT")== null?"":rs.getString("JENIS_LOT"));
	      		h.put("noLot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
	      		h.put("namaUrusan", rs.getString("NAMA_URUSAN")== null?"":rs.getString("NAMA_URUSAN"));
	      		h.put("statusSah", rs.getString("STATUS_HAKMILIK")== null?"":rs.getString("STATUS_HAKMILIK"));
	      		h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH")== null?"":rs.getString("KEGUNAAN_TANAH").toUpperCase());
	      		h.put("statusRizab", rs.getString("STATUS_RIZAB")== null? "":rs.getString("STATUS_RIZAB"));
	      		h.put("tarikhTerima",rs.getDate("TARIKH_TERIMA")== null? "":sdf.format(rs.getDate("TARIKH_TERIMA")));
	    	  
	      		if(rs.getString("NO_HAKMILIK")!= null){
	      			h.put("jenisTanah","M");
	      		}else{
	      			h.put("jenisTanah","R");
	      		}
	      		if(rs.getString("NO_HAKMILIK")!= null && rs.getString("NO_WARTA")!= null){
	      			h.put("jenisTanah","X");
	      		}
	      		listCarianHakmilikDanRizab.addElement(h);
	      		bil++;
	      		count++;
	    	  
	      	}

	    } finally {
	      if (db != null) db.close();
	    
	    }		
	    return listCarianHakmilikDanRizab;
	    
	}
	
	@Override
	public Hashtable getCarianHakmilikRizab(String idHakmilik)throws Exception {
	    Db db = null;
	    //listCarianHakmilikDanRizab = new Vector();
		Hashtable h = null;
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();  
	    	sql = "SELECT DISTINCT A.ID_HAKMILIK,C.NO_FAIL, "+
	    		"CASE "+
	            "WHEN E.KOD_JENIS_HAKMILIK = '00' THEN '' "+
	            "ELSE E.KOD_JENIS_HAKMILIK "+
	            "END AS JENIS_HAKMILIK, A.NO_HAKMILIK, A.NO_WARTA, "+
	            "F.KETERANGAN AS JENIS_LOT, A.NO_LOT, D.NAMA_URUSAN, "+
	            "CASE "+
	            "WHEN A.STATUS_SAH = 'H' "+
	                "THEN 'HAKMILIK ASAL TIADA' "+
	             "WHEN A.STATUS_SAH = 'D' "+
	                "THEN 'DAFTAR' "+
	             "WHEN A.STATUS_SAH = 'P' "+
	                "THEN 'BATAL (PELEPASAN)' "+
	             "WHEN A.STATUS_SAH = 'T' "+
	                "THEN 'TUKAR GUNA' "+ 
	             "WHEN A.STATUS_SAH = 'S' "+
	                "THEN 'BATAL(SAMBUNGAN)' "+
	             "WHEN A.STATUS_SAH = 'B' "+
	                "THEN 'BATAL' "+
	             "WHEN A.STATUS_SAH = 'M' "+
		         	"THEN 'BATAL (PINDAHMILIK)' "+
		         "WHEN A.STATUS_SAH = 'Q' "+
			     	"THEN 'BATAL (PERMOHONAN)' "+
				 "WHEN A.STATUS_SAH = 'L' "+
				 	"THEN 'BATAL (PERLETAKHAKAN)' "+		         	
			     "ELSE 'TIADA' "+
	             "END AS STATUS_HAKMILIK, "+
	             "A.STATUS_RIZAB, A.TARIKH_TERIMA,A.KEGUNAAN_TANAH "+
	             "FROM TBLHTPHAKMILIK A, "+
	             "TBLPERMOHONAN B, "+
	             "TBLPFDFAIL C, "+
	             "TBLRUJURUSAN D, "+
	             "TBLRUJJENISHAKMILIK E, "+
	             "TBLRUJLOT F, "+
	             "TBLRUJKEMENTERIAN RK," +
	             "TBLRUJKEMENTERIANMAPPING RKME "+
	             "WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
	             "AND B.ID_FAIL = C.ID_FAIL "+
	             "AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
	             "AND A.ID_LOT = F.ID_LOT "+
	             "AND C.ID_URUSAN = D.ID_URUSAN "+
	             "AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
	             "AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN AND RKME.STATUS='A' " +
	             "AND A.ID_HAKMILIK NOT IN " +
	             "	(SELECT TPHP.ID_HAKMILIK FROM" +
	             "	TBLHTPHAKMILIKPERIHAL TPHP " +
	             "	WHERE  " +
	             "	TPHP.LUAS_BERSAMAAN < A.LUAS_BERSAMAAN " +
	             "	 "+
	             "	 " +
	             "	) "+
	             "";
	          
			//idHakmilik
			if (idHakmilik != null) {
				if (!idHakmilik.trim().equals("")) {
					sql = sql + " AND A.ID_HAKMILIK = '" + idHakmilik + "'";
				}
			}
			sql = sql +" ORDER BY C.NO_FAIL DESC ";
			myLog.debug("getCarianSenaraiTanahDiceroboh:CARIAN::sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);	    
	      	int bil = 1;
	      	int count = 0;

	      	while (rs.next()) {
	      		h = new Hashtable();
	      		h.put("bil", bil+".");
	      		h.put("idHakmilik",rs.getString("ID_HAKMILIK"));
	      		h.put("noFail",rs.getString("NO_FAIL"));
	      		h.put("kodJenis", rs.getString("JENIS_HAKMILIK")== null?"":rs.getString("JENIS_HAKMILIK"));
	      		h.put("noHakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
	      		h.put("noWarta", rs.getString("NO_WARTA")== null?"":rs.getString("NO_WARTA"));
	      		h.put("kodLot", rs.getString("JENIS_LOT")== null?"":rs.getString("JENIS_LOT"));
	      		h.put("noLot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
	      		h.put("namaUrusan", rs.getString("NAMA_URUSAN")== null?"":rs.getString("NAMA_URUSAN"));
	      		h.put("statusSah", rs.getString("STATUS_HAKMILIK")== null?"":rs.getString("STATUS_HAKMILIK"));
	      		h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH")== null?"":rs.getString("KEGUNAAN_TANAH").toUpperCase());
	      		h.put("statusRizab", rs.getString("STATUS_RIZAB")== null? "":rs.getString("STATUS_RIZAB"));
	      		h.put("tarikhTerima",rs.getDate("TARIKH_TERIMA")== null? "":sdf.format(rs.getDate("TARIKH_TERIMA")));
	    	  
	      		if(rs.getString("NO_HAKMILIK")!= null){
	      			h.put("jenisTanah","M");
	      		}else{
	      			h.put("jenisTanah","R");
	      		}
	      		if(rs.getString("NO_HAKMILIK")!= null && rs.getString("NO_WARTA")!= null){
	      			h.put("jenisTanah","X");
	      		}
	      		listCarianHakmilikDanRizab.addElement(h);
	      		bil++;
	      		count++;
	    	  
	      	}

	    } finally {
	      if (db != null) db.close();
	    
	    }		
	    return h;
	    
	}	

	

	  
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
	
	
}
