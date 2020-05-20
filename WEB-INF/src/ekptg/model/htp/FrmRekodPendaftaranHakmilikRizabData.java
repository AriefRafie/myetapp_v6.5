package ekptg.model.htp;

import java.sql.Connection;
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
import ekptg.helpers.Utils;
import ekptg.model.entities.Daerah;
import ekptg.model.entities.Mukim;
import ekptg.model.entities.Negeri;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HakmilikCukai;

public class FrmRekodPendaftaranHakmilikRizabData {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.FrmRekodPendaftaranHakmilikRizabData.class);	
	private static Vector<Hashtable<String,String>> listHakmilik = null;
	private static Vector<Hashtable<String,String>> listMaklumatFail = null;
	private static Vector<Hashtable<String,String>> listSenaraiFail = null;		
	private static Vector<Hashtable<String,String>> listCarianHakmilikDanRizab = null;
	private static Connection conn = null;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	//PAPAR CARIAN HAKMILIK DAN RIZAB
	public static Vector<Hashtable<String,String>> getCarianSenaraiHakmilikRizabById(String idJenisTanah,String idNegeri, String idDaerah,String idMukim,String noFail,String idJenisHakmilik,String noHakmilik,String noWarta,String idLot,String noLot, String idAgensi, String idKementerian, String idStatus)throws Exception {
	    Db db = null;
	    listCarianHakmilikDanRizab = new Vector<Hashtable<String,String>>();
	    String sql = "";
	    try {
	    	//log.info("idStatus : " + idStatus);
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
//				 Kemaskini 2010/11/03 hapus "TBLRUJLOT H" ada 2 rujukan
//	             "TBLRUJLOT H, "+
//	             By rosli 24/05/2010 kegunaan tanah dari htphakmilik
//	             "TBLHTPHAKMILIKPERIHAL I, "+
	             //20/09/2010
//	             "TBLHTPHAKMILIKAGENSI J "+
	             "TBLRUJKEMENTERIAN RK," +
	             "TBLRUJKEMENTERIANMAPPING RKME "+
	             "WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
	             "AND B.ID_FAIL = C.ID_FAIL "+
	             "AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
	             "AND A.ID_LOT = F.ID_LOT "+
	             //Azam remark no warta
	             //"AND A.NO_WARTA IS NOT NULL "+
	             "AND C.ID_URUSAN = D.ID_URUSAN "+
	             //"AND A.ID_LOT = H.ID_LOT "+
	             //20/09/2010
	             //"AND A.ID_HAKMILIK = J.ID_HAKMILIK(+) "+
	             "AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
	             "AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN "+
	             // Kemaskini pada 03/11/2010, maklumat tidak dipapar sekiranya  "RKME.STATUS='A' (TBLRUJKEMENTERIANMAPPING)"
	             //"AND RKME.STATUS = 'A' "+
	             "";
//	             "AND A.ID_HAKMILIK = I.ID_HAKMILIK(+) ";
	            // "AND (A.STATUS_SAH NOT IN ('P', 'T', 'S') OR A.STATUS_SAH IS NULL) ";
	          
			//idJenisTanah
			if (idJenisTanah != null) {
				if (!idJenisTanah.trim().equals("")) {
					if (Integer.parseInt(idJenisTanah) == 1){
						//sql = sql + " AND A.NO_HAKMILIK IS NOT NULL AND A.STATUS_RIZAB IS NOT NULL ";
						//sql = sql + " AND A.STATUS_RIZAB IS NOT NULL ";
						sql = sql + " AND NVL(A.NO_HAKMILIK,' ') <> ' ' ";
					} else if (Integer.parseInt(idJenisTanah) == 2){
						//sql = sql + " AND A.NO_HAKMILIK IS NULL AND A.STATUS_RIZAB IS NULL ";
						//sql = sql + " AND A.STATUS_RIZAB IS NULL ";
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
			//no warta
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
						//sql = sql + " AND J.ID_KEMENTERIAN = '" + idKementerian + "'";
						sql = sql + " AND A.ID_KEMENTERIAN = '" + idKementerian + "'";
					}
				}
			}
			//no agensi
			if (idAgensi != null) {
				if (!idAgensi.trim().equals("")) {
					if (!idAgensi.trim().equals("99999")) {
						//20/09/2010
						//sql = sql + " AND J.ID_AGENSI = '" + idAgensi + "'";
						sql = sql + " AND A.ID_AGENSI = '" + idAgensi + "'";
					}
				}
			} 
			//idStatus
			if (idStatus != null) {
				if (!idStatus.trim().equals("")) {
					if (idStatus.trim().equals("1")) {
						//AKTIF
						//sql = sql + " AND (A.STATUS_SAH IS NULL OR A.STATUS_SAH IN ('T','H','D'))";
						// 20/09/2010
						//sql = sql + " AND (A.STATUS_SAH NOT IN ('P','Q','S','B','M'))";
						/* 17/08/2011 */
						sql = sql + " AND (A.STATUS_SAH IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
						"))";						

						
					} else if (idStatus.trim().equals("2")) {
						//BATAL [P - BATAL (PELEPASAN),Q - BATAL (PERMOHONAN)]
						//BATAL [S - BATAL (SAMBUNGAN),B - BATAL,M - BATAL (PINDAHMILIK)]
						//Azam add M						
						//sql = sql + " AND A.STATUS_SAH IN ('P','Q','S','B','M')";
						/* 17/08/2011 */
						sql = sql + " AND (A.STATUS_SAH IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
						"))";						
					}
					//fir edit to get all record without batal
					else{
						//AKTIF
						//log.info("ALL:idStatus::"+idStatus);
						//sql = sql + " AND (A.STATUS_SAH IS NULL OR A.STATUS_SAH IN ('T','H','D'))";
					}

					
				}
			}
			
			sql = sql +" ORDER BY C.NO_FAIL DESC ";
			myLog.debug("getCarianSenaraiHakmilikRizabById:CARIAN::sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);	    
			Hashtable<String,String> h;
	      	int bil = 1;
	      	//int count = 0;

	      	while (rs.next()) {
	      		h = new Hashtable<String,String>();
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
	      		//count++;
	    	  
	      	}
	      	//azam remark - no need to add empty hashtable

	    } finally {
	      if (db != null) db.close();
	    
	    }		
	    return listCarianHakmilikDanRizab;
	    
	}	
	
	// RETURN SENARI FAIL
	public static Vector<Hashtable<String,String>> getPaparSenaraiHakmilikRizab() throws Exception {
		return listCarianHakmilikDanRizab;
		
	}
	
	// PAPAR MAKLUMAT FAIL (MASTER) BY ID
	// Modified by rosli (21/04/2010), tambah field Id_KEMENTERIAN 
	public static Vector<Hashtable<String,String>> getPaparMaklumatFailById(String id) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			listMaklumatFail = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();			
			sql = "SELECT TPH.ID_HAKMILIK,RK.NAMA_KEMENTERIAN,RA.NAMA_AGENSI, "+
		      "RN.NAMA_NEGERI, RD.NAMA_DAERAH, RM.NAMA_MUKIM, "+
		      "TPH.NO_FAIL_PTG,TPH.NO_FAIL_PTD, F.NO_FAIL,TPH.NO_FAIL_KJP, "+
		      "P.TUJUAN, RJH.KETERANGAN, "+
		      "UPPER(TPH.NO_HAKMILIK) NO_HAKMILIK, TPH.NO_LOT, TPH.NO_WARTA, "+
		      "RU.NAMA_URUSAN "+
		      ",NVL(TPH.ID_KEMENTERIAN,0) ID_KEMENTERIAN" +
		      ",TPH.HAKMILIK_ASAL,TPH.CATATAN" +
		      " ,(SELECT USER_NAME FROM USERS WHERE USER_ID=TPH.ID_KEMASKINI) PEGAWAI_AKHIR "+
		      ",TPH.KEGUNAAN_TANAH,TPH.ID_RIZAB  " +
		      "FROM TBLHTPHAKMILIK TPH, "+
		      "TBLRUJKEMENTERIAN RK, "+
		      "TBLRUJDAERAH RD, "+
		      "TBLRUJMUKIM RM, "+
		      "TBLRUJJENISHAKMILIK RJH, "+
		      "TBLRUJAGENSI RA, "+
		      "TBLRUJNEGERI RN, "+
		      "TBLPERMOHONAN P, "+
		      "TBLPFDFAIL F, "+
		      "TBLRUJURUSAN RU "+
		      "WHERE F.ID_FAIL = P.ID_FAIL "+
		      "AND P.ID_PERMOHONAN = TPH.ID_PERMOHONAN "+
		      "AND RU.ID_URUSAN = F.ID_URUSAN "+
		      "AND TPH.ID_AGENSI = RA.ID_AGENSI(+) "+
		      "AND RA.ID_KEMENTERIAN = RK.ID_KEMENTERIAN(+) "+
		      "AND TPH.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK(+) "+
		  	  "AND TPH.ID_MUKIM = RM.ID_MUKIM(+) "+
		  	  "AND RM.ID_DAERAH = RD.ID_DAERAH(+) "+ 
		      "AND RD.ID_NEGERI = RN.ID_NEGERI(+) "+
		  	  "AND TPH.ID_HAKMILIK ='"+id+"'";			   					
			myLog.info("getPaparMaklumatFailById:sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idHakmilik", rs.getString("ID_HAKMILIK")==null ? "" :rs.getString("ID_HAKMILIK"));
				h.put("namaKementerian", rs.getString("NAMA_KEMENTERIAN")==null ? "" :rs.getString("NAMA_KEMENTERIAN"));
				h.put("noFailSeksyen", rs.getString("NO_FAIL")==null ? "" :rs.getString("NO_FAIL"));
				h.put("noFailPtg", rs.getString("NO_FAIL_PTG")==null ? "" :rs.getString("NO_FAIL_PTG"));
				h.put("noFailPtd", rs.getString("NO_FAIL_PTD")==null ? "" :rs.getString("NO_FAIL_PTD"));
				h.put("tajukFail", rs.getString("TUJUAN")==null ? "" :rs.getString("TUJUAN"));
				h.put("namaNegeri", rs.getString("NAMA_NEGERI")==null ? "" :rs.getString("NAMA_NEGERI"));
				h.put("namaDaerah", rs.getString("NAMA_DAERAH")==null ? "" :rs.getString("NAMA_DAERAH"));
				h.put("jenisHakmilik", rs.getString("KETERANGAN")==null ? "" :rs.getString("KETERANGAN").toUpperCase());
				h.put("namaMukim", rs.getString("NAMA_MUKIM")==null ? "" :rs.getString("NAMA_MUKIM"));	
				h.put("namaAgensi", rs.getString("NAMA_AGENSI")==null ? "" :rs.getString("NAMA_AGENSI"));
				h.put("noFailKjp", rs.getString("NO_FAIL_KJP")==null ? "":rs.getString("NO_FAIL_KJP"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK")==null ? "" :rs.getString("NO_HAKMILIK"));
				h.put("noLot", rs.getString("NO_LOT")==null ? "" :rs.getString("NO_LOT"));
				h.put("noWarta", rs.getString("NO_WARTA")==null ? "" :rs.getString("NO_WARTA"));
				h.put("caraPerolehan", rs.getString("NAMA_URUSAN")==null ? "" :rs.getString("NAMA_URUSAN"));
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN")==null ? "" :rs.getString("ID_KEMENTERIAN"));
				h.put("hakmilikAsal", rs.getString("HAKMILIK_ASAL")==null ? "":rs.getString("HAKMILIK_ASAL"));
				h.put("catatan", rs.getString("CATATAN")==null ? "":rs.getString("CATATAN"));
				h.put("pegawaiAkhir",Utils.isNull(rs.getString("PEGAWAI_AKHIR")));
				h.put("kegunaanTanah",Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
				h.put("idJenisRizab",Utils.isNull(rs.getString("ID_RIZAB")));
				listMaklumatFail.addElement(h);

			}
			
		} finally {
			if (db != null)
			db.close();
			
		}
		return listMaklumatFail;
	}
	
	// PAPAR HAKMILIK DAN RIZAB BY ID
	public static Vector<Hashtable<String,String>> getPaparHakmilikRizabById(String id) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			listHakmilik = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();			
			sql = "SELECT A.ID_HAKMILIK, A.TARIKH_TERIMA, A.TARIKH_DAFTAR, UPPER(A.NO_HAKMILIK) NO_HAKMILIK,A.NO_LOT, "+
		       	  "A.ID_NEGERI,(SELECT NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI=A.ID_NEGERI) NAMA_NEGERI" +
		       	  ",A.ID_DAERAH, (SELECT NAMA_DAERAH FROM TBLRUJDAERAH WHERE ID_DAERAH=A.ID_DAERAH) NAMA_DAERAH" +
		       	  ",A.ID_MUKIM, (SELECT NAMA_MUKIM FROM TBLRUJMUKIM WHERE ID_MUKIM=A.ID_MUKIM) NAMA_MUKIM" +
		       	  ",A.ID_JENISHAKMILIK, NVL((SELECT KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK=A.ID_JENISHAKMILIK),'') KOD_JENISHAKMILIK" +
		       	  ", NVL((SELECT KETERANGAN FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK=A.ID_JENISHAKMILIK),'') NAMA_JENISHAKMILIK" +
		       	  ", A.CUKAI, "+
		       	  "A.LOKASI,A.ID_LUAS,A.LUAS,A.ID_LUAS_BERSAMAAN,A.LUAS_BERSAMAAN LUASBERSAMAAN" +
		       	  ",A.NO_PELAN, A.ID_LOT,NVL((SELECT KETERANGAN FROM TBLRUJLOT WHERE ID_LOT=A.ID_LOT),'') NAMA_LOT" +
		       	  ", A.ID_RIZAB,NVL(RR.KETERANGAN,'') JENIS_RIZAB " +
		       	  ", A.ID_KATEGORI,NVL((SELECT KETERANGAN FROM TBLRUJKATEGORI WHERE ID_KATEGORI=A.ID_KATEGORI AND ROWNUM <= 1),'') KATEGORI, "+
		       	  "A.TEMPOH, A.SYARAT, A.HAKMILIK_ASAL, A.NO_FAIL_JOPA, A.TARAF_HAKMILIK, "+
		       	  "A.TARIKH_LUPUT, A.CUKAI_TERKINI, A.KEGUNAAN_TANAH, A.NO_PU, "+
		       	  "A.TARIKH_WARTA, A.KAWASAN_RIZAB, A.NO_SYIT, A.NO_RIZAB NO_WARTA, A.SEKATAN, "+	
		       	  "A.HAKMILIK_BERIKUT, A.STATUS_SAH, A.TARIKH_KEMASKINI, A.STATUS_RIZAB, "+
		       	  "A.NO_RIZAB,A.TARIKH_RIZAB,A.NO_BANGUNAN,A.NO_TINGKAT,A.NO_PETAK,A.ID_KEMASKINI, "+
		       	  "S.USER_NAME, A.HAKMILIK_ASAL, A.CATATAN,B.ID_HAKMILIKCUKAI "+
		            ",CASE "+
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
		             "END AS STATUS_TANAH " +
		             ",NVL( (SELECT RJH.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH "+
		             " WHERE RJH.ID_JENISHAKMILIK=A.ID_JENISHAKMILIK),'00') KOD_JENIS_HAKMILIK "+
		             //"FROM TBLHTPHAKMILIK A, TBLHTPHAKMILIKPERIHAL B,USERS S "+
				  "FROM TBLHTPHAKMILIK A, TBLHTPHAKMILIKCUKAI B,USERS S,TBLRUJJENISRIZAB RR "+
				  "WHERE A.ID_HAKMILIK = '"+id +"' " +
				  "AND A.ID_KEMASKINI = S.USER_ID(+) " +
				  "AND B.STATUS='S' "+
				  "AND A.ID_HAKMILIK = B.ID_HAKMILIK(+) " +
				  "AND RR.ID_JENISRIZAB(+) =A.ID_RIZAB";			
			myLog.info("getPaparHakmilikRizabById:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idHakmilik", rs.getString("id_Hakmilik"));
				h.put("idNegeriHR", rs.getString("ID_NEGERI")==null ? "" :rs.getString("ID_NEGERI"));
				h.put("namaNegeriHR", Utils.isNull(rs.getString("NAMA_NEGERI")));
				h.put("idDaerahHR", rs.getString("ID_DAERAH")==null ? "":rs.getString("ID_DAERAH"));
				h.put("namaDaerahHR", Utils.isNull(rs.getString("NAMA_DAERAH")));
				h.put("idMukimHR", rs.getString("ID_MUKIM")==null ? "":rs.getString("ID_MUKIM"));
				h.put("namaMukimHR", Utils.isNull(rs.getString("NAMA_MUKIM")));
				h.put("idLot", rs.getString("ID_LOT")==null ? "":rs.getString("ID_LOT"));
				h.put("noLot", rs.getString("NO_LOT")==null ? "":rs.getString("NO_LOT"));
				h.put("namaLot", Utils.isNull(rs.getString("NAMA_LOT")));
				h.put("idJenisHakmilikHR", rs.getString("ID_JENISHAKMILIK")==null ? "":rs.getString("ID_JENISHAKMILIK"));	
				h.put("kodJenisHakmilikHR", Utils.isNull(rs.getString("KOD_JENISHAKMILIK"))+" - "+Utils.isNull(rs.getString("NAMA_JENISHAKMILIK")));
				h.put("tarikhTerima", rs.getString("TARIKH_TERIMA")==null ? "	" :sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhDaftar", rs.getString("TARIKH_DAFTAR")==null ? "" :sdf.format(rs.getDate("TARIKH_DAFTAR")));
				h.put("cukai", rs.getString("CUKAI")==null ? "" :rs.getString("CUKAI"));
				h.put("lokasi", rs.getString("LOKASI")==null ? "":rs.getString("LOKASI"));
				//LUAS LAMA
				h.put("idLuasLama", rs.getString("ID_LUAS")==null ? "0":rs.getString("ID_LUAS"));
				h.put("luasLama", rs.getString("LUAS")==null ? "":rs.getString("LUAS"));
				//LUAS CONVERT
				//h.put("luasConvert", rs.getString("LUASBERSAMAAN")==null ? new Double("0.00000"):rs.getDouble("LUASBERSAMAAN"));
				h.put("luasConvert", String.valueOf(rs.getString("LUASBERSAMAAN")==null ?0.00:rs.getDouble("LUASBERSAMAAN")));
				h.put("noPelan", rs.getString("NO_PELAN")==null ? "":rs.getString("NO_PELAN"));
				h.put("idJenisRizab", rs.getString("ID_RIZAB")==null ? "0":rs.getString("ID_RIZAB"));
				h.put("namaJenisRizab", Utils.isNull(rs.getString("JENIS_RIZAB")));
				h.put("idKategori", rs.getString("id_Kategori")==null ? "" :rs.getString("ID_KATEGORI"));
				h.put("namaKategori", Utils.isNull(rs.getString("KATEGORI")));
				h.put("tempoh", rs.getString("TEMPOH")==null ? "" :rs.getString("TEMPOH"));
				h.put("syarat", rs.getString("SYARAT")==null ? "":rs.getString("SYARAT"));
				h.put("noBangunan", rs.getString("NO_BANGUNAN")==null ? "":rs.getString("NO_BANGUNAN"));
				h.put("noTingkat", rs.getString("NO_TINGKAT")==null ? "":rs.getString("NO_TINGKAT"));
				h.put("noPetak", rs.getString("NO_PETAK")==null ? "":rs.getString("NO_PETAK"));
				h.put("socTaraf", rs.getString("TARAF_HAKMILIK")==null ? "":rs.getString("TARAF_HAKMILIK"));
				h.put("tarikhLuput", rs.getString("TARIKH_LUPUT")==null ? "":sdf.format(rs.getDate("TARIKH_LUPUT")));
				h.put("cukaiTerkini", rs.getString("CUKAI_TERKINI")==null ? "":rs.getString("CUKAI_TERKINI"));
				h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH")==null ? "":rs.getString("KEGUNAAN_TANAH"));				
				h.put("noPu", rs.getString("NO_PU")==null ? "":rs.getString("NO_PU"));
				h.put("tarikhWarta", rs.getString("TARIKH_WARTA")==null ? "":sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("noWarta", rs.getString("NO_WARTA")==null? "":rs.getString("NO_WARTA"));
				h.put("tarikhRizab", rs.getString("TARIKH_RIZAB")==null ? "":sdf.format(rs.getDate("TARIKH_RIZAB")));
				h.put("noRizab", rs.getString("NO_RIZAB")==null? "":rs.getString("NO_RIZAB"));
				h.put("kawasanRizab", rs.getString("KAWASAN_RIZAB")==null ? "":rs.getString("KAWASAN_RIZAB"));
				h.put("noSyit", rs.getString("NO_SYIT")==null ? "":rs.getString("NO_SYIT"));
				h.put("sekatan", rs.getString("SEKATAN")==null ? "":rs.getString("SEKATAN"));
				h.put("socStatus", rs.getString("STATUS_SAH")==null ? "":rs.getString("STATUS_SAH"));
				h.put("socStatusTanah", rs.getString("STATUS_TANAH")==null ? "":rs.getString("STATUS_TANAH"));
				h.put("tarikhKemaskini", rs.getString("TARIKH_KEMASKINI")==null ? "":sdf.format(rs.getDate("TARIKH_KEMASKINI")));
				h.put("socRizab", rs.getString("STATUS_RIZAB")==null ? "":rs.getString("STATUS_RIZAB"));
		    	/*  if(rs.getString("NO_HAKMILIK")!= null){
		    		  h.put("socRizab","M");
		    	  }else{
		    		  h.put("socRizab","R");
		    	  }
		    	  if(rs.getString("NO_HAKMILIK")!= null && rs.getString("NO_WARTA")!= null){
		    		  h.put("socRizab","X");
		    	  }*/
				h.put("userName", rs.getString("USER_NAME")==null ? "":rs.getString("USER_NAME"));
				h.put("noHakmilikAsal", rs.getString("HAKMILIK_ASAL")==null ? "":rs.getString("HAKMILIK_ASAL"));
				h.put("catatan", rs.getString("CATATAN")==null ? "":rs.getString("CATATAN"));
				h.put("idHakmilikCukai", rs.getString("ID_HAKMILIKCUKAI")==null ? "0":rs.getString("ID_HAKMILIKCUKAI"));
				h.put("kodJenisHakmilik", rs.getString("KOD_JENIS_HAKMILIK")==null ? "00":rs.getString("KOD_JENIS_HAKMILIK"));						
				//BUG FIX!. syaz. add no_hakmilik. 11/11/2014
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK")==null ? "":rs.getString("NO_HAKMILIK"));
				listHakmilik.addElement(h);
				
			}
			
		} finally {
			if (db != null)
			db.close();
			
		}
		return listHakmilik;
		
	}
	// PAPAR HAKMILIK DAN RIZAB BY ID
	public HakMilik getHakmilikRizab(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";
		HakMilik hn = null;
		try {
			db = new Db();
			//hn = new HakMilik();
			Statement stmt = db.getStatement();			
				sql = "SELECT A.ID_HAKMILIK, A.TARIKH_TERIMA, A.TARIKH_DAFTAR, UPPER(A.NO_HAKMILIK) NO_HAKMILIK,A.NO_LOT, "+
			       	  "A.ID_NEGERI,(SELECT NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI=A.ID_NEGERI) NAMA_NEGERI" +
			       	  ",A.ID_DAERAH, (SELECT NAMA_DAERAH FROM TBLRUJDAERAH WHERE ID_DAERAH=A.ID_DAERAH) NAMA_DAERAH" +
			       	  ",A.ID_MUKIM, (SELECT NAMA_MUKIM FROM TBLRUJMUKIM WHERE ID_MUKIM=A.ID_MUKIM) NAMA_MUKIM" +
			       	  ",A.ID_JENISHAKMILIK, NVL((SELECT KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK=A.ID_JENISHAKMILIK),'') KOD_JENISHAKMILIK" +
			       	  ", NVL((SELECT KETERANGAN FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK=A.ID_JENISHAKMILIK),'') NAMA_JENISHAKMILIK" +
			       	  ", A.CUKAI, "+
			       	  "A.LOKASI,A.ID_LUAS,A.LUAS,A.ID_LUAS_BERSAMAAN,A.LUAS_BERSAMAAN LUASBERSAMAAN" +
			       	  ",A.NO_PELAN, A.ID_LOT,NVL((SELECT KETERANGAN FROM TBLRUJLOT WHERE ID_LOT=A.ID_LOT),'') NAMA_LOT" +
			       	  ", A.ID_RIZAB,NVL(RR.KETERANGAN,'') JENIS_RIZAB " +
			       	  ", A.ID_KATEGORI,NVL((SELECT KETERANGAN FROM TBLRUJKATEGORI WHERE ID_KATEGORI=A.ID_KATEGORI AND ROWNUM <= 1),'') KATEGORI, "+
			       	  "A.TEMPOH, A.SYARAT, A.HAKMILIK_ASAL, A.NO_FAIL_JOPA, A.TARAF_HAKMILIK, "+
			       	  "A.TARIKH_LUPUT, A.CUKAI_TERKINI, A.KEGUNAAN_TANAH, A.NO_PU, "+
			       	  "A.TARIKH_WARTA, A.KAWASAN_RIZAB, A.NO_SYIT, A.NO_RIZAB NO_WARTA, A.SEKATAN, "+	
			       	  "A.HAKMILIK_BERIKUT, A.STATUS_SAH, A.TARIKH_KEMASKINI, A.STATUS_RIZAB, "+
			       	  "A.NO_RIZAB,A.TARIKH_RIZAB,A.NO_BANGUNAN,A.NO_TINGKAT,A.NO_PETAK,A.ID_KEMASKINI, "+
			       	  "S.USER_NAME, A.HAKMILIK_ASAL, A.CATATAN,B.ID_HAKMILIKCUKAI "+
			            ",CASE "+
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
			             "END AS STATUS_TANAH " +
			             ",NVL( (SELECT RJH.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH "+
			             " WHERE RJH.ID_JENISHAKMILIK=A.ID_JENISHAKMILIK),'00') KOD_JENIS_HAKMILIK "+
			             //"FROM TBLHTPHAKMILIK A, TBLHTPHAKMILIKPERIHAL B,USERS S "+
					  "FROM TBLHTPHAKMILIK A, TBLHTPHAKMILIKCUKAI B,USERS S,TBLRUJJENISRIZAB RR "+
					  "WHERE A.ID_HAKMILIK = '"+idHakmilik +"' " +
					  "AND A.ID_KEMASKINI = S.USER_ID(+) " +
					  "AND B.STATUS='S' "+
					  "AND A.ID_HAKMILIK = B.ID_HAKMILIK(+) " +
					  "AND RR.ID_JENISRIZAB(+) =A.ID_RIZAB";			
			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("getHakmilikRizab:sql="+sql);
			while (rs.next()) {
				hn = new HakMilik();
				hn.setIdHakmilik(rs.getLong("ID_HAKMILIK"));
				Negeri rn = new Negeri();
				rn.setIdNegeri(rs.getLong("ID_NEGERI"));
				rn.setNamaNegeri(rs.getString("NAMA_NEGERI"));				
				hn.setNegeri(rn);
				
				Daerah rd = new Daerah();
				rd.setIdDaerah(rs.getLong("ID_DAERAH"));
				rd.setNamaDaerah(rs.getString("NAMA_DAERAH"));
				hn.setDaerah(rd);
				
				Mukim rm = new Mukim();
				rm.setIdMukim(rs.getLong("ID_MUKIM"));
				rm.setNamaMukim(rs.getString("NAMA_MUKIM"));
				hn.setMukim(rm);
				
				//h.put("idLot", rs.getString("ID_LOT")==null ? "":rs.getString("ID_LOT"));
				Tblrujlot rl = new Tblrujlot();
				rl.setIdLot(rs.getLong("ID_LOT"));
				rl.setKeterangan(rs.getString("NAMA_LOT"));
				hn.setRujLot(rl);
				
				Tblrujjenishakmilik rjh = new Tblrujjenishakmilik();
				rjh.setIdJenishakmilik(rs.getLong("ID_JENISHAKMILIK"));
				rjh.setKodJenisHakmilik(rs.getString("KOD_JENISHAKMILIK"));
				hn.setRujJenisHakmilik(rjh);
				
				hn.setTarikhTerima(rs.getDate("TARIKH_TERIMA"));
				hn.setTarikhDaftar(rs.getDate("TARIKH_DAFTAR"));
				HakmilikCukai hc = new HakmilikCukai();
				hc.setCukai(rs.getDouble("CUKAI"));
				hc.setCukaiTerkini(rs.getDouble("CUKAI_TERKINI"));
				hn.setHakmilikCukai(hc);
				
				hn.setLokasi(rs.getString("LOKASI"));
				hn.setIdLuas(rs.getLong("ID_LUAS"));
				hn.setLuasString(rs.getString("LUAS"));
				hn.setLuasBersamaan(rs.getDouble("LUASBERSAMAAN"));
				hn.setNoPelan(rs.getString("NO_PELAN"));
				//h.put("idJenisRizab", rs.getString("ID_RIZAB")==null ? "0":rs.getString("ID_RIZAB"));
				//h.put("namaJenisRizab", Utils.isNull(rs.getString("JENIS_RIZAB")));				
				//h.put("idKategori", rs.getString("id_Kategori")==null ? "" :rs.getString("ID_KATEGORI"));
				//h.put("namaKategori", Utils.isNull(rs.getString("KATEGORI")));				
				hn.setTempoh(rs.getString("TEMPOH"));
				hn.setSyarat(rs.getString("SYARAT"));
				//h.put("noBangunan", rs.getString("NO_BANGUNAN")==null ? "":rs.getString("NO_BANGUNAN"));
				//h.put("noTingkat", rs.getString("NO_TINGKAT")==null ? "":rs.getString("NO_TINGKAT"));
				//h.put("noPetak", rs.getString("NO_PETAK")==null ? "":rs.getString("NO_PETAK"));
				//h.put("socTaraf", rs.getString("TARAF_HAKMILIK")==null ? "":rs.getString("TARAF_HAKMILIK"));
				//h.put("tarikhLuput", rs.getString("TARIKH_LUPUT")==null ? "":sdf.format(rs.getDate("TARIKH_LUPUT")));
				hn.setKegunaan(rs.getString("KEGUNAAN_TANAH"));
				hn.setNoPU(rs.getString("NO_PU"));	
				hn.setTarikhWarta(rs.getDate("TARIKH_WARTA"));
				hn.setNoWarta(rs.getString("NO_WARTA"));
				
				//hn.setIdNegeri(rs.getLong("ID_NEGERI"));		
//					h.put("tarikhRizab", rs.getString("TARIKH_RIZAB")==null ? "":sdf.format(rs.getDate("TARIKH_RIZAB")));
//					h.put("noRizab", rs.getString("NO_RIZAB")==null? "":rs.getString("NO_RIZAB"));
//					h.put("kawasanRizab", rs.getString("KAWASAN_RIZAB")==null ? "":rs.getString("KAWASAN_RIZAB"));
//					h.put("noSyit", rs.getString("NO_SYIT")==null ? "":rs.getString("NO_SYIT"));
//					h.put("sekatan", rs.getString("SEKATAN")==null ? "":rs.getString("SEKATAN"));
//					h.put("socStatus", rs.getString("STATUS_SAH")==null ? "":rs.getString("STATUS_SAH"));
//					h.put("socStatusTanah", rs.getString("STATUS_TANAH")==null ? "":rs.getString("STATUS_TANAH"));
//					h.put("tarikhKemaskini", rs.getString("TARIKH_KEMASKINI")==null ? "":sdf.format(rs.getDate("TARIKH_KEMASKINI")));
//					h.put("socRizab", rs.getString("STATUS_RIZAB")==null ? "":rs.getString("STATUS_RIZAB"));
//					h.put("userName", rs.getString("USER_NAME")==null ? "":rs.getString("USER_NAME"));
//					h.put("noHakmilikAsal", rs.getString("HAKMILIK_ASAL")==null ? "":rs.getString("HAKMILIK_ASAL"));
//					h.put("catatan", rs.getString("CATATAN")==null ? "":rs.getString("CATATAN"));
//					h.put("idHakmilikCukai", rs.getString("ID_HAKMILIKCUKAI")==null ? "0":rs.getString("ID_HAKMILIKCUKAI"));
//					h.put("kodJenisHakmilik", rs.getString("KOD_JENIS_HAKMILIK")==null ? "00":rs.getString("KOD_JENIS_HAKMILIK"));		
//				
				hn.setNoHakmilik(rs.getString("NO_HAKMILIK"));
										
			}
				
		} finally {
			if (db != null)
				db.close();
				
		}
		return hn;
			
		
	}
	// PAPAR HAKMILIK DAN RIZAB BY ID
	public static Vector<Hashtable<String,String>> getPaparRizabById(String id) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			listHakmilik = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();		
			sql = "SELECT A.ID_HAKMILIK, A.TARIKH_TERIMA, A.TARIKH_DAFTAR, UPPER(A.NO_HAKMILIK) NO_HAKMILIK,A.NO_LOT, "+
		       	  "A.ID_NEGERI,(SELECT NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI=A.ID_NEGERI) NAMA_NEGERI" +
		       	  ",A.ID_DAERAH, (SELECT NAMA_DAERAH FROM TBLRUJDAERAH WHERE ID_DAERAH=A.ID_DAERAH) NAMA_DAERAH" +
		       	  ",A.ID_MUKIM, (SELECT NAMA_MUKIM FROM TBLRUJMUKIM WHERE ID_MUKIM=A.ID_MUKIM) NAMA_MUKIM" +
		       	  ",A.ID_JENISHAKMILIK, NVL((SELECT KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK=A.ID_JENISHAKMILIK),'') KOD_JENISHAKMILIK" +
		       	  ", NVL((SELECT KETERANGAN FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK=A.ID_JENISHAKMILIK),'') NAMA_JENISHAKMILIK" +
		       	  ", A.CUKAI, "+
		       	  "A.LOKASI,A.ID_LUAS,A.LUAS,A.ID_LUAS_BERSAMAAN,A.LUAS_BERSAMAAN LUASBERSAMAAN" +
		       	  ",A.NO_PELAN, A.ID_LOT,NVL((SELECT KETERANGAN FROM TBLRUJLOT WHERE ID_LOT=A.ID_LOT),'') NAMA_LOT" +
		       	  ", A.ID_RIZAB,NVL(RR.KETERANGAN,'') JENIS_RIZAB " +
		       	  ", A.ID_KATEGORI,NVL((SELECT KETERANGAN FROM TBLRUJKATEGORI WHERE ID_KATEGORI=A.ID_KATEGORI AND ROWNUM <= 1),'') KATEGORI, "+
		       	  "A.TEMPOH, A.SYARAT, A.HAKMILIK_ASAL, A.NO_FAIL_JOPA, A.TARAF_HAKMILIK, "+
		       	  "A.TARIKH_LUPUT, A.CUKAI_TERKINI, A.KEGUNAAN_TANAH, A.NO_PU, "+
		       	  "A.TARIKH_WARTA, A.KAWASAN_RIZAB, A.NO_SYIT, A.NO_WARTA, A.SEKATAN, "+	
		       	  "A.HAKMILIK_BERIKUT, A.STATUS_SAH, A.TARIKH_KEMASKINI, A.STATUS_RIZAB, "+
		       	  "A.NO_RIZAB,A.TARIKH_RIZAB,A.NO_BANGUNAN,A.NO_TINGKAT,A.NO_PETAK,A.ID_KEMASKINI, "+
		       	  "S.USER_NAME, A.HAKMILIK_ASAL, A.CATATAN "+
		            ",CASE "+
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
		             "END AS STATUS_TANAH " +
		             ",NVL( (SELECT RJH.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH "+
		             " WHERE RJH.ID_JENISHAKMILIK=A.ID_JENISHAKMILIK),'00') KOD_JENIS_HAKMILIK "+
				  "FROM TBLHTPHAKMILIK A, USERS S,TBLRUJJENISRIZAB RR "+
				  "WHERE A.ID_HAKMILIK = '"+id +"' " +
				  "AND A.ID_KEMASKINI = S.USER_ID(+) " +
				  "AND RR.ID_JENISRIZAB(+) = A.ID_RIZAB";			
			ResultSet rs = stmt.executeQuery(sql);
			//log.info("getPaparHakmilikRizabById:sql="+sql);
			Hashtable<String,String> h;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idHakmilik", rs.getString("id_Hakmilik"));
				h.put("idNegeriHR", rs.getString("ID_NEGERI")==null ? "" :rs.getString("ID_NEGERI"));
				h.put("namaNegeriHR", Utils.isNull(rs.getString("NAMA_NEGERI")));
				h.put("idDaerahHR", rs.getString("ID_DAERAH")==null ? "":rs.getString("ID_DAERAH"));
				h.put("namaDaerahHR", Utils.isNull(rs.getString("NAMA_DAERAH")));
				h.put("idMukimHR", rs.getString("ID_MUKIM")==null ? "":rs.getString("ID_MUKIM"));
				h.put("namaMukimHR", Utils.isNull(rs.getString("NAMA_MUKIM")));
				h.put("idLot", rs.getString("ID_LOT")==null ? "":rs.getString("ID_LOT"));
				h.put("noLot", rs.getString("NO_LOT")==null ? "":rs.getString("NO_LOT"));
				h.put("namaLot", Utils.isNull(rs.getString("NAMA_LOT")));
				h.put("idJenisHakmilikHR", rs.getString("ID_JENISHAKMILIK")==null ? "":rs.getString("ID_JENISHAKMILIK"));	
				h.put("kodJenisHakmilikHR", Utils.isNull(rs.getString("KOD_JENISHAKMILIK"))+" - "+Utils.isNull(rs.getString("NAMA_JENISHAKMILIK")));
				h.put("tarikhTerima", rs.getString("TARIKH_TERIMA")==null ? "	" :sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhDaftar", rs.getString("TARIKH_DAFTAR")==null ? "" :sdf.format(rs.getDate("TARIKH_DAFTAR")));
				h.put("cukai", rs.getString("CUKAI")==null ? "" :rs.getString("CUKAI"));
				h.put("lokasi", rs.getString("LOKASI")==null ? "":rs.getString("LOKASI"));
				//LUAS LAMA
				h.put("idLuasLama", rs.getString("ID_LUAS")==null ? "0":rs.getString("ID_LUAS"));
				h.put("luasLama", rs.getString("LUAS")==null ? "":rs.getString("LUAS"));
				//LUAS CONVERT
				//h.put("luasConvert", rs.getString("LUASBERSAMAAN")==null ? new Double("0.00000"):rs.getDouble("LUASBERSAMAAN"));
				h.put("luasConvert", String.valueOf(rs.getString("LUASBERSAMAAN")==null ?0.00:rs.getDouble("LUASBERSAMAAN")));
				h.put("luasConvertF", rs.getString("LUASBERSAMAAN")==null ?"0.00":Utils.formatLuas(rs.getDouble("LUASBERSAMAAN")));
				h.put("noPelan", rs.getString("NO_PELAN")==null ? "":rs.getString("NO_PELAN"));
				h.put("idJenisRizab", rs.getString("ID_RIZAB")==null ? "0":rs.getString("ID_RIZAB"));
				h.put("namaJenisRizab", Utils.isNull(rs.getString("JENIS_RIZAB")));
				h.put("idKategori", rs.getString("id_Kategori")==null ? "" :rs.getString("ID_KATEGORI"));
				h.put("namaKategori", Utils.isNull(rs.getString("KATEGORI")));
				h.put("tempoh", rs.getString("TEMPOH")==null ? "" :rs.getString("TEMPOH"));
				h.put("syarat", rs.getString("SYARAT")==null ? "":rs.getString("SYARAT"));
				h.put("noBangunan", rs.getString("NO_BANGUNAN")==null ? "":rs.getString("NO_BANGUNAN"));
				h.put("noTingkat", rs.getString("NO_TINGKAT")==null ? "":rs.getString("NO_TINGKAT"));
				h.put("noPetak", rs.getString("NO_PETAK")==null ? "":rs.getString("NO_PETAK"));
				h.put("socTaraf", rs.getString("TARAF_HAKMILIK")==null ? "":rs.getString("TARAF_HAKMILIK"));
				h.put("tarikhLuput", rs.getString("TARIKH_LUPUT")==null ? "":sdf.format(rs.getDate("TARIKH_LUPUT")));
				h.put("cukaiTerkini", rs.getString("CUKAI_TERKINI")==null ? "":rs.getString("CUKAI_TERKINI"));
				h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH")==null ? "":rs.getString("KEGUNAAN_TANAH"));				
				h.put("noPu", rs.getString("NO_PU")==null ? "":rs.getString("NO_PU"));
				h.put("tarikhWarta", rs.getString("TARIKH_WARTA")==null ? "":sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("noWarta", rs.getString("NO_WARTA")==null? "":rs.getString("NO_WARTA"));
				h.put("tarikhRizab", rs.getString("TARIKH_RIZAB")==null ? "":sdf.format(rs.getDate("TARIKH_RIZAB")));
				h.put("noRizab", rs.getString("NO_RIZAB")==null? "":rs.getString("NO_RIZAB"));
				h.put("kawasanRizab", rs.getString("KAWASAN_RIZAB")==null ? "":rs.getString("KAWASAN_RIZAB"));
				h.put("noSyit", rs.getString("NO_SYIT")==null ? "":rs.getString("NO_SYIT"));
				h.put("sekatan", rs.getString("SEKATAN")==null ? "":rs.getString("SEKATAN"));
				h.put("socStatus", rs.getString("STATUS_SAH")==null ? "":rs.getString("STATUS_SAH"));
				h.put("socStatusTanah", rs.getString("STATUS_TANAH")==null ? "":rs.getString("STATUS_TANAH"));
				h.put("tarikhKemaskini", rs.getString("TARIKH_KEMASKINI")==null ? "":sdf.format(rs.getDate("TARIKH_KEMASKINI")));
				//h.put("socRizab", rs.getString("STATUS_RIZAB")==null ? "":rs.getString("STATUS_RIZAB"));
		    	  if(rs.getString("NO_HAKMILIK")!= null){
		    		  h.put("socRizab","M");
		    	  }else{
		    		  h.put("socRizab","R");
		    	  }
		    	  if(rs.getString("NO_HAKMILIK")!= null && rs.getString("NO_WARTA")!= null){
		    		  h.put("socRizab","X");
		    	  }
				h.put("userName", rs.getString("USER_NAME")==null ? "":rs.getString("USER_NAME"));
				h.put("noHakmilikAsal", rs.getString("HAKMILIK_ASAL")==null ? "":rs.getString("HAKMILIK_ASAL"));
				h.put("catatan", rs.getString("CATATAN")==null ? "":rs.getString("CATATAN"));
				h.put("idHakmilikCukai", "00");
				h.put("kodJenisHakmilik", rs.getString("KOD_JENIS_HAKMILIK")==null ? "00":rs.getString("KOD_JENIS_HAKMILIK"));				
				listHakmilik.addElement(h);
				
			}
			
		} finally {
			if (db != null)
			db.close();
			
		}
		return listHakmilik;
		
	}
	/**
	 * firzan edit to add setautocommit = false so that if any error, it wont save it
	 *
	 */
	// KEMASKINI HAKMILIK BY ID
	public static void updateHakmilikById(Hashtable<String,String> data) throws Exception {
	    Db db = null;
		Date date = new Date(); 
		String currentDate = sdf.format(date);
	    String sql = "";
	    try{
			  db = new Db();
			  conn = db.getConnection();
			  conn.setAutoCommit(false);
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  
			  //UPDATE TBLHTPHAKMILIK			  
	    	  r.update("ID_HAKMILIK", data.get("idHakmilik"));
	    	  
	    	  r.add("ID_NEGERI", data.get("socNegeriHR"));
	    	  r.add("ID_DAERAH", data.get("socDaerahHR"));
	    	  r.add("ID_MUKIM", data.get("socMukimHR"));
	    	  r.add("ID_JENISHAKMILIK", data.get("socJenisHakmilikHR"));
	    	  r.add("NO_HAKMILIK", data.get("txtNoHakmilik"));
	    	  r.add("ID_LOT", data.get("socLotHR"));
	    	  r.add("NO_LOT", data.get("txtNoLot"));
	    	  //convert date before add
			  String tarikhTerima = (String)(data.get("txdTarikhTerima"));
			  String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	
			  
			  r.add("TARIKH_TERIMA",r.unquote(txdTarikhTerima));  	
			  //convert date before add
			  String tarikhDaftar = (String)data.get("txdTarikhDaftar");
			  String txdTarikhDaftar = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";	  			  			  
	    	  r.add("TARIKH_DAFTAR", r.unquote(txdTarikhDaftar));
	    	  r.add("NO_BANGUNAN", data.get("txtNoBangunan"));
	    	  r.add("NO_TINGKAT", data.get("txtNoTingkat"));
	    	  r.add("NO_PETAK", data.get("txtNoPetak"));
	    	  r.add("CUKAI", data.get("txtCukaiTahun"));
	    	  r.add("LOKASI", data.get("txtLokasi"));
	    	  
	    	  r.add("ID_LUAS_BERSAMAAN",2);
	    	  r.add("LUAS_BERSAMAAN", data.get("txtLuas"));
	    	  
	    	  r.add("ID_LUAS",data.get("socLuas"));
	    	  System.out.println("luas lama "+data.get("txtLuasLama"));
	    	  r.add("LUAS", data.get("txtLuasLama"));
	    	  
	    	  r.add("TARAF_HAKMILIK", data.get("socTaraf"));
	    	  r.add("ID_KATEGORI", data.get("socKategori"));
	    	  r.add("NO_PELAN", data.get("txtNoPelan"));
	    	  r.add("TEMPOH", data.get("txtTempoh"));
	    	  r.add("NO_FAIL_JOPA", data.get("txtNoFailJopa"));	    	  
	    	  //r.add("HAKMILIK_ASAL", data.get("txtHakmilikAsal"));
	    	  r.add("CUKAI_TERKINI", data.get("txtCukaiTerkini"));
	    	  //r.add("CUKAI",data.get("txtCukaiSemasa"));
	    	  //convert date before add
	    	  if(data.get("txdTarikhLuput")!=""){
	    		  String tarikhLuput = (String)data.get("txdTarikhLuput");
	    		  String txdTarikhLuput = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";	
	    		  r.add("TARIKH_LUPUT", r.unquote(txdTarikhLuput));
	    	  }
	    	  r.add("NO_PU", data.get("txtNoPu"));
	    	  //convert date before add
			  String tarikhWarta = (String)data.get("txdTarikhWarta");
			  String txdTarikhWarta = "to_date('" + tarikhWarta + "','dd/MM/yyyy')";		    	  
	    	  //r.add("TARIKH_WARTA", r.unquote(txdTarikhWarta));
	    	  r.add("KAWASAN_RIZAB", data.get("txtKawasanRizab"));
	    	  r.add("NO_SYIT", data.get("txtNoSyit"));
	    	  //r.add("NO_WARTA", data.get("txtNoWarta"));
	    	  myLog.info("NO_RIZAB:"+data.get("txtNoWarta"));
	    	  r.add("NO_RIZAB", data.get("txtNoWarta"));
	    	  //LOT1616
	    	  r.add("SEKATAN", data.get("txtSekatan"));	  
	    	  r.add("SYARAT", data.get("txtSyarat"));	  
	    	  r.add("HAKMILIK_BERIKUT", data.get("txtHakmilikBerikut"));
	    	  r.add("STATUS_SAH", data.get("socStatus"));
	    	  //convert date before add
			  String tarikhKemaskini = currentDate;
			  String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
	    	  r.add("TARIKH_KEMASKINI", r.unquote(txdTarikhKemaskini));
	    	  r.add("ID_RIZAB", data.get("socJenisRizab"));   
	    	  r.add("CATATAN", data.get("catatan"));   
	    	  r.add("STATUS_RIZAB",data.get("socRizab"));
	    	  r.add("ID_KEMASKINI",data.get("idKemaskini"));
	    	  r.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
			
	    	  //FIX BUG. 21112014. SYAZ
	    	  String tarikhRizab = (String)(data.get("txdTarikhRizab"));
			  String txdTarikhRizab = "to_date('" + tarikhRizab + "','dd/MM/yyyy')";
	    	  r.add("TARIKH_RIZAB", r.unquote(txdTarikhRizab));
	    	  
			  sql = r.getSQLUpdate("TBLHTPHAKMILIK");
			  myLog.info("sql update TBLHTPHAKMILIK:"+sql);
		      stmt.executeUpdate(sql);
		      
		      //TBLHTPHAKMILIKPERIHAL
//		      r = new SQLRenderer();
//		      r.update("ID_HAKMILIK", data.get("idHakmilik"));
//		      r.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
//		      	//r.add("ID_KEMASKINI", data.get("idKemaskini"));
//		      	//r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//		      sql = r.getSQLUpdate("TBLHTPHAKMILIKPERIHAL");
//		      stmt.executeUpdate(sql);
		      
		      //TBLHTPHAKMILIKCUKAI
		      r = new SQLRenderer();
	    	  String sqlCukai = "";
		      if(data.get("txtCukaiTahun").equals(data.get("txtCukaiTerkini"))){
		    	  r.update("ID_HAKMILIKCUKAI",r.unquote(String.valueOf(data.get("idHakmilikCukai")))); 
		    	  r.add("CUKAI_TERKINI",data.get("txtCukaiTerkini"));
		    	  r.add("CUKAI",data.get("txtCukaiTahun"));
		    	  r.add("ID_KEMASKINI",data.get("idKemaskini"));
		    	  r.add("TARIKH_KEMASKINI",r.unquote("SYSDATE"));
		    	  sqlCukai = r.getSQLUpdate("TBLHTPHAKMILIKCUKAI");
		    	  //log.info("1. sql update TBLHTPHAKMILIKCUKAI:"+sql);
		    	  stmt.executeUpdate(sqlCukai);
		      
		      }else{
		    	  r.update("ID_HAKMILIKCUKAI",r.unquote(String.valueOf(data.get("idHakmilikCukai")))); 
		    	  r.add("STATUS","N");
		    	  r.add("ID_KEMASKINI",r.unquote(String.valueOf(data.get("idKemaskini"))));
		    	  r.add("TARIKH_KEMASKINI",r.unquote("SYSDATE"));
		    	  sqlCukai = r.getSQLUpdate("TBLHTPHAKMILIKCUKAI");
		    	  //log.info("1. sql update TBLHTPHAKMILIKCUKAI:"+sql);
		    	  stmt.executeUpdate(sqlCukai);
		    	  
			      r = new SQLRenderer();
			      long idHakmilikCukai = DB.getNextID("TBLHTPHAKMILIKCUKAI_SEQ"); 
			      r.add("ID_HAKMILIKCUKAI",idHakmilikCukai); 
			      r.add("ID_HAKMILIK",data.get("idHakmilik")); 
				  r.add("KOD_BAYARAN_CUKAI","P");
				  r.add("CUKAI",data.get("txtCukaiSemasa"));
				  r.add("CUKAI_TERKINI",data.get("txtCukaiTerkini"));
				  r.add("STATUS","S");
				  r.add("ID_MASUK",data.get("idKemaskini"));
				  r.add("TARIKH_MASUK",r.unquote("SYSDATE"));
				  r.add("ID_KEMASKINI",data.get("idKemaskini"));
				  r.add("TARIKH_KEMASKINI",r.unquote("SYSDATE"));
						  
				  sqlCukai = r.getSQLInsert("TBLHTPHAKMILIKCUKAI");
				  //log.info("2. sql INSERT TBLHTPHAKMILIKCUKAI:sqlCukai="+sqlCukai);
				  stmt.executeUpdate(sqlCukai);
					
		      }	      
		      conn.commit();

	    }catch(Exception e){
	    	conn.rollback();
	    	e.printStackTrace();
	    
	    }finally {
	    	if (db != null) db.close();
		    if (conn != null) conn.close();
	    
	    }	
	    
    }
	/**
	 * @param data
	 * @throws Exception
	 * Diguna dalam 
	 * 	FrmRekodPendaftaranTanah (X Aktif)
	 *  FrmRekodPendaftaranTanahAgensi
	 * 	FrmRekodTanah
	 */
	// KEMASKINI RIZAB BY ID
	public static void XupdateRizabById(Hashtable data) throws Exception {
	    Db dbHakmilik = null;
		Date date = new Date(); 
		String currentDate = sdf.format(date);
	    String sql = "";
	    try {
	    	  dbHakmilik = new Db();
			  Statement stmtHakmilik = dbHakmilik.getStatement();
			  SQLRenderer rHakmilik = new SQLRenderer();
			  rHakmilik.update("ID_HAKMILIK", data.get("idHakmilik"));
	    	  //convert date before add
			  String tarikhTerima = data.get("txdTarikhTerima").toString();
			  String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
			  rHakmilik.add("TARIKH_TERIMA",rHakmilik.unquote(txdTarikhTerima));  	
			  String tarikhWarta= data.get("txdTarikhWarta").toString();
			  String txdTarikhWarta = "to_date('" + tarikhWarta + "','dd/MM/yyyy')";	 		  
			  rHakmilik.add("TARIKH_WARTA",rHakmilik.unquote(txdTarikhWarta));  	
			  rHakmilik.add("ID_LOT", "1");
			  rHakmilik.add("NO_WARTA", data.get("txtNoWarta"));
			  rHakmilik.add("NO_LOT", data.get("txtNoLotR"));
			  rHakmilik.add("ID_LUAS",data.get("socLuas"));
			  rHakmilik.add("LUAS",  data.get("txtLuasLama"));
			  
			  rHakmilik.add("ID_LUAS_BERSAMAAN","2");
			  rHakmilik.add("LUAS_BERSAMAAN",data.get("txtLuas"));
			  
			  rHakmilik.add("NO_PELAN", data.get("txtNoPelan"));
			  rHakmilik.add("NO_PU", data.get("txtNoPu"));
			  rHakmilik.add("NO_SYIT", data.get("txtNoSyit"));
			  rHakmilik.add("LOKASI", data.get("txtLokasi"));
			  rHakmilik.add("NO_FAIL_JOPA", data.get("txtNoFailJopa"));	 
			  if(!data.get("socStatus").equals("")){
				  rHakmilik.add("STATUS_SAH",data.get("socStatus"));
			  }
			  rHakmilik.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
			  rHakmilik.add("CATATAN", data.get("catatan"));   
			  rHakmilik.add("ID_JENISRIZAB", data.get("socjenisrizab"));   
			  
	    	  //convert date before add
			  String tarikhKemaskini = currentDate;
			  String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
			  rHakmilik.add("TARIKH_KEMASKINI", rHakmilik.unquote(txdTarikhKemaskini));;			  
			  rHakmilik.add("ID_KEMASKINI", data.get("idMasuk"));;			  

			  sql = rHakmilik.getSQLUpdate("TBLHTPHAKMILIK");
			  stmtHakmilik.executeUpdate(sql);
		 
	    }finally {
		    if (dbHakmilik != null) dbHakmilik.close();
		
	    }
		Db dbHakmilikPerihal = new Db();
		String sqlHakmilikPerihal = "";
		try{
		     Statement stmtHakmilikPerihal = dbHakmilikPerihal.getStatement();
			 SQLRenderer rHakmilikPerihal = new SQLRenderer();
			 rHakmilikPerihal.update("ID_HAKMILIK", data.get("idHakmilik"));
			 rHakmilikPerihal.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
			 sqlHakmilikPerihal = rHakmilikPerihal.getSQLUpdate("TBLHTPHAKMILIKPERIHAL");
			 stmtHakmilikPerihal.executeUpdate(sqlHakmilikPerihal);

		}finally {
			if (dbHakmilikPerihal != null)dbHakmilikPerihal.close();
		
		}		  
	}
	
	// PAPAR SENARAI FAIL HAKMILIK SEMENTARA
	public static Vector<Hashtable<String,String>> getSenaraiHakmilikSambungan(String noHakmilikAsal) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			listSenaraiFail = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();			
			sql = "SELECT TBLHTPHAKMILIK.ID_HAKMILIK,TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK, TBLHTPHAKMILIK.NO_HAKMILIK, "+
				  "(SELECT ID_HAKMILIK FROM TBLHTPHAKMILIK WHERE NO_HAKMILIK LIKE '%20000003%') AS ID_HAKMILIK_ASAL "+
				  "FROM TBLHTPHAKMILIK,TBLRUJJENISHAKMILIK "+
				  "WHERE TBLHTPHAKMILIK.ID_JENISHAKMILIK = TBLRUJJENISHAKMILIK.ID_JENISHAKMILIK "+
				  "AND TBLHTPHAKMILIK.HAKMILIK_ASAL LIKE '%"+noHakmilikAsal+"%' ";					
			//myLog.info("gaga :" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
		    int bil = 1;
		    int count = 0;
			
		    while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", bil+".");
				h.put("idHakmilikAsal", rs.getString("ID_HAKMILIK_ASAL")==null ? "" :rs.getString("ID_HAKMILIK_ASAL"));
				h.put("idHakmilikBaru", rs.getString("ID_HAKMILIK")==null ? "" :rs.getString("ID_HAKMILIK"));
				h.put("kodJenisBaru", rs.getString("KOD_JENIS_HAKMILIK")==null ? "" :rs.getString("KOD_JENIS_HAKMILIK"));
				h.put("noHakmilikBaru", rs.getString("NO_HAKMILIK")==null ? "" :rs.getString("NO_HAKMILIK"));			
				listSenaraiFail.addElement(h);
		    	bil++;
		    	count++;
			
		    } 		
			if(count==0){
				h = new Hashtable<String,String>();
				h.put("bil","");
				h.put("idHakmilikAsal","");
				h.put("idHakmilikBaru","");
				h.put("kodJenisBaru", "Tiada Rekod");
				h.put("noHakmilikBaru","");			
				listSenaraiFail.addElement(h);
			
			}
			return listSenaraiFail;
		
		} finally {
			if (db != null)
			db.close();
		}
		
	}
	
	//PAPAR CARIAN HAKMILIK DAN RIZAB
	public static Vector<Hashtable<String,String>> getCarianSenaraiHakmilikRizabAktif(String idJenisTanah,String idNegeri, String idDaerah,String idMukim,String noFail,String idJenisHakmilik,String noHakmilik,String noWarta,String idLot,String noLot, String idAgensi, String idKementerian, String idStatus)throws Exception {
		Db db = null;
	    listCarianHakmilikDanRizab = new Vector<Hashtable<String,String>>();
	    String sql = "";
	    try {
	    	
	    	db = new Db();
	    	Statement stmt = db.getStatement(); 
	    	sql = "SELECT DISTINCT A.ID_HAKMILIK,C.NO_FAIL, "+
	        	"CASE "+
	            "WHEN E.KOD_JENIS_HAKMILIK = '00' THEN '' "+
	            "ELSE E.KOD_JENIS_HAKMILIK "+
	            "END AS JENIS_HAKMILIK, UPPER(A.NO_HAKMILIK) NO_HAKMILIK, A.NO_WARTA, "+
	            "H.KETERANGAN AS JENIS_LOT, A.NO_LOT, D.NAMA_URUSAN, "+
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
	             "A.STATUS_RIZAB, A.TARIKH_TERIMA," +
	             //"I.KEGUNAAN_TANAH "+
	             //Kegunaan tanah ambil dari tblhtphakmilik
	             "A.KEGUNAAN_TANAH "+
	             "FROM TBLHTPHAKMILIK A, "+
	             "TBLPERMOHONAN B, "+
	             "TBLPFDFAIL C, "+
	             "TBLRUJURUSAN D, "+
	             "TBLRUJJENISHAKMILIK E, "+
	             "TBLRUJLOT F, "+
	             "TBLRUJLOT H, "+
	             //"TBLHTPHAKMILIKPERIHAL I, "+
	             //"TBLHTPHAKMILIKAGENSI J "+
	             "TBLRUJKEMENTERIAN RK," +
	             "TBLRUJKEMENTERIANMAPPING RKME "+
	             "WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
	             "AND B.ID_FAIL = C.ID_FAIL "+
	             "AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
	             "AND A.ID_LOT = F.ID_LOT "+
	             //Azam remark no warta
	             //"AND A.NO_WARTA IS NOT NULL "+
	             "AND C.ID_URUSAN = D.ID_URUSAN "+
	             "AND A.ID_LOT = H.ID_LOT "+
	             "AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
	             "AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN ";
	             //"AND A.ID_HAKMILIK = J.ID_HAKMILIK(+) "+
	             //"AND A.ID_HAKMILIK = I.ID_HAKMILIK(+) ";
	            // "AND (A.STATUS_SAH NOT IN ('P', 'T', 'S') OR A.STATUS_SAH IS NULL) ";
	          
	    	//idJenisTanah
			if (idJenisTanah != null) {
				if (!idJenisTanah.trim().equals("")) {
					if (Integer.parseInt(idJenisTanah) == 1){
						//sql = sql + " AND A.NO_HAKMILIK IS NOT NULL AND A.STATUS_RIZAB IS NOT NULL ";
						//sql = sql + " AND A.STATUS_RIZAB IS NOT NULL ";
						sql = sql + " AND NVL(A.NO_HAKMILIK,' ') <> ' ' ";
					} else if (Integer.parseInt(idJenisTanah) == 2){
						//sql = sql + " AND A.NO_HAKMILIK IS NULL AND A.STATUS_RIZAB IS NULL ";
						//sql = sql + " AND A.STATUS_RIZAB IS NULL ";
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
			//no warta
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
						//AKTIF
						//sql = sql + " AND (A.STATUS_SAH IS NULL OR A.STATUS_SAH IN ('T','H','D'))";
						// 20/09/2010
						//sql = sql + " AND (A.STATUS_SAH NOT IN ('B','P','Q','S','M','L'))";
						/* 17/08/2011 */
						sql = sql + " AND (A.STATUS_SAH NOT IN (" +
								"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
								"))";						
					}
			}
			
			sql = sql +" ORDER BY C.NO_FAIL DESC ";
			//log.debug("CARIAN -> "+sql);
			ResultSet rs = stmt.executeQuery(sql);	    
			Hashtable<String,String> h;
	      	int bil = 1;
	      	
	      	while (rs.next()) {
	    	  h = new Hashtable<String,String>();
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
	    	  
	      }
	      //azam remark - no need to add empty hashtable
	      /*
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil","");
	    	  h.put("idHakmilik","");
	    	  h.put("noFail","Tiada Rekod.");
	    	  h.put("kodJenis", "");
	    	  h.put("noHakmilik", "");
	    	  h.put("noWarta", "");
	    	  h.put("kodLot", "");
	    	  h.put("noLot", "");
	    	  h.put("namaUrusan", "");
	    	  h.put("statusSah", "");
	    	  h.put("kegunaanTanah", "");
	    	  h.put("statusRizab", "");
	    	  h.put("tarikhTerima","");
	    	  
	    	  listCarianHakmilikDanRizab.addElement(h);
	      }
	      */
	      //return list;
	    } finally {
	      if (db != null) db.close();
	      
	    }		
	    return listCarianHakmilikDanRizab;
	    
	}		

}
