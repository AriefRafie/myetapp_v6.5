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

public class FrmHakmilikRizabPengesahanBean implements ITanahCarian {
	
	private HakmilikInterface iHakmilik = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.FrmHakmilikRizabPengesahanBean.class);
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
			,String idNegeri, String idDaerah, String idMukim
			,String noFail, String idJenisHakmilik, String noHakmilik,String noWarta
			,String idLot,String noLot
			,String idAgensi, String idKementerian
			,String idStatus)throws Exception {
	    listCarianHakmilikDanRizab = new Vector();
	    try {    	
	    	db = new Db();
	    	Statement stmt = db.getStatement(); 
	    	sql = "" +
	    		" SELECT DISTINCT A.ID_HAKMILIK,C.NO_FAIL "+
	        	" ,CASE "+
	            " WHEN E.KOD_JENIS_HAKMILIK = '00' THEN '' "+
	            " ELSE E.KOD_JENIS_HAKMILIK "+
	            " END AS JENIS_HAKMILIK, UPPER(A.NO_HAKMILIK) NO_HAKMILIK, A.NO_WARTA "+
	            " ,H.KETERANGAN AS JENIS_LOT, A.NO_LOT, D.NAMA_URUSAN "+
				 " ,NVL(( SELECT KETERANGAN FROM TBLHTPRUJSTATUSAH WHERE STATUS_SAH = A.STATUS_SAH "+
				 " ),'TIADA MAKLUMAT') STATUS_HAKMILIK "+
	             " ,A.STATUS_RIZAB, A.TARIKH_TERIMA" +
	             " ,A.KEGUNAAN_TANAH "+
	             " ,TPHT.CEROBOH,HIMEJ.TPI " +
	             "FROM TBLHTPHAKMILIK A, "+
	             "TBLPERMOHONAN B, "+
	             "TBLPFDFAIL C, "+
	             "TBLRUJURUSAN D, "+
	             "TBLRUJJENISHAKMILIK E, "+
	             "TBLRUJLOT F, "+
	             "TBLRUJLOT H, "+
	             "TBLRUJKEMENTERIAN RK," +
	             "TBLRUJKEMENTERIANMAPPING RKME "+
	             " ,( "+
	             " 		    SELECT TPHA.ID_HAKMILIK "+ 
	             " 		    ,CASE "+
	             " 		        WHEN count(TPHA.ID_HAKMILIK) > 0 "+ 
	             " 		            THEN 'DICEROBOH' "+
	             "  		        ELSE '' "+
	             " 		    END CEROBOH "+        
	             " 		        FROM "+
	             " 		        TBLPFDFAIL F,TBLPERMOHONAN P,TBLPHPHAKMILIKPERMOHONAN HPHP,TBLHTPHAKMILIKAGENSI TPHA "+ 
	             " 		        WHERE P.ID_FAIL=F.ID_FAIL "+ 
	             " 		        AND P.ID_PERMOHONAN=HPHP.ID_PERMOHONAN "+ 
	             " 		        AND HPHP.ID_HAKMILIKAGENSI = TPHA.ID_HAKMILIKAGENSI "+ 
	             " 		        AND F.ID_URUSAN = 8 "+ 
	             " 		        AND F.ID_SUBURUSAN = 56 "+ 
	             " 		        GROUP BY TPHA.ID_HAKMILIK "+ 
	             " 		) TPHT "+
	             " ,( "+ 
	             " 		    SELECT TPH.ID_HAKMILIK "+ 
	             " 		       ,CASE "+ 
	             " 		            WHEN COUNT(TPH.ID_HAKMILIK) > 0 "+  
	             " 		                THEN 'IMEJ' "+ 
	             " 		            ELSE '' "+ 
	             " 		        END TPI "+          
	             " 		    FROM "+ 
	             " 		    TBLHTPHAKMILIK TPH,TBLHTPGAMBAR TPG "+ 
	             " 		    WHERE TPH.ID_HAKMILIK = TPG.ID_HAKMILIK "+ 
	             " 		    GROUP BY TPH.ID_HAKMILIK "+  
	             " 		) HIMEJ "+ 
	            "WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
	             "AND B.ID_FAIL = C.ID_FAIL "+
	             "AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
	             "AND A.ID_LOT = F.ID_LOT "+
	             "AND C.ID_URUSAN = D.ID_URUSAN "+
	             "AND A.ID_LOT = H.ID_LOT "+
	             "AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
	             "AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN " +
	             " AND A.ID_HAKMILIK = TPHT.ID_HAKMILIK(+) " +
	             " AND A.ID_HAKMILIK = HIMEJ.ID_HAKMILIK(+) "+
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
//						sql = sql + " AND (A.STATUS_SAH IN (" +
//								"		SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
//								"))";	
						if (idStatus.trim().equals("1")) {
							//AKTIF
							sql = sql + " AND (A.STATUS_SAH IN (" +
							"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
							"))";						
							
						} else if (idStatus.trim().equals("2")) {
							sql = sql + " AND (A.STATUS_SAH IN (" +
							"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
							"))";						
						
						}
					}
			}		
			sql = sql + " AND A.STATUS_SAH IN ('E')";						
			sql = sql +" ORDER BY C.NO_FAIL DESC ";

			
			
			sql = getSQLPengambilan("");
			myLog.debug("getCarianSenaraiHakmilikRizabAktifCARIAN ->sql= "+sql);
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
	    	  //h.put("statusRizab", rs.getString("STATUS_RIZAB")== null? "":rs.getString("STATUS_RIZAB"));
	    	  //h.put("tarikhTerima",rs.getDate("TARIKH_TERIMA")== null? "":sdf.format(rs.getDate("TARIKH_TERIMA")));
	    	  
	    	  if(rs.getString("NO_HAKMILIK")!= null){
	    		  h.put("jenisTanah","M");
	    	  }else{
	    		  h.put("jenisTanah","R");
	    	  }
	    	  if(rs.getString("NO_HAKMILIK")!= null && rs.getString("NO_WARTA")!= null){
	    		  h.put("jenisTanah","X");
	    	  }
	    	  //h.put("ceroboh", Utils.isNull(rs.getString("CEROBOH")));
	    	  //h.put("isImej", Utils.isNull(rs.getString("TPI")));
	    	  listCarianHakmilikDanRizab.addElement(h);
	    	  bil++;
	    	  count++;
	    	  
	      }
	    } finally {
	      if (db != null) db.close();
	      
	    }		
	    return listCarianHakmilikDanRizab;
	    
	}
	
	//PAPAR CARIAN HAKMILIK DAN RIZAB
	@Override
	public Vector getCarianSenaraiHakmilikRizab(String idJenisTanah
			,String idNegeri, String idDaerah, String idMukim
			,String noFail, String idJenisHakmilik, String noHakmilik
			,String noWarta
			,String idLot,String noLot
			,String idAgensi, String idKementerian
			,String idStatus,String kegunaan)throws Exception {
	    listCarianHakmilikDanRizab = new Vector();
	    try {    	
	    	db = new Db();
	    	Statement stmt = db.getStatement(); 
	    	sql = "" +
	    		" SELECT DISTINCT A.ID_HAKMILIK,C.NO_FAIL "+
	        	" ,CASE "+
	            " WHEN E.KOD_JENIS_HAKMILIK = '00' THEN '' "+
	            " ELSE E.KOD_JENIS_HAKMILIK "+
	            " END AS JENIS_HAKMILIK, UPPER(A.NO_HAKMILIK) NO_HAKMILIK, A.NO_WARTA "+
	            " ,H.KETERANGAN AS JENIS_LOT, A.NO_LOT, D.NAMA_URUSAN "+
				 " ,NVL(( SELECT KETERANGAN FROM TBLHTPRUJSTATUSAH WHERE STATUS_SAH = A.STATUS_SAH "+
				 " ),'TIADA MAKLUMAT') STATUS_HAKMILIK "+
	             " ,A.STATUS_RIZAB, A.TARIKH_TERIMA" +
	             " ,A.KEGUNAAN_TANAH "+
	             " ,TPHT.CEROBOH,HIMEJ.TPI " +
	             "FROM TBLHTPHAKMILIK A, "+
	             "TBLPERMOHONAN B, "+
	             "TBLPFDFAIL C, "+
	             "TBLRUJURUSAN D, "+
	             "TBLRUJJENISHAKMILIK E, "+
	             "TBLRUJLOT F, "+
	             "TBLRUJLOT H, "+
	             "TBLRUJKEMENTERIAN RK," +
	             "TBLRUJKEMENTERIANMAPPING RKME "+
	             " ,( "+
	             " 		    SELECT TPHA.ID_HAKMILIK "+ 
	             " 		    ,CASE "+
	             " 		        WHEN count(TPHA.ID_HAKMILIK) > 0 "+ 
	             " 		            THEN 'DICEROBOH' "+
	             "  		        ELSE '' "+
	             " 		    END CEROBOH "+        
	             " 		        FROM "+
	             " 		        TBLPFDFAIL F,TBLPERMOHONAN P,TBLPHPHAKMILIKPERMOHONAN HPHP,TBLHTPHAKMILIKAGENSI TPHA "+ 
	             " 		        WHERE P.ID_FAIL=F.ID_FAIL "+ 
	             " 		        AND P.ID_PERMOHONAN=HPHP.ID_PERMOHONAN "+ 
	             " 		        AND HPHP.ID_HAKMILIKAGENSI = TPHA.ID_HAKMILIKAGENSI "+ 
	             " 		        AND F.ID_URUSAN = 8 "+ 
	             " 		        AND F.ID_SUBURUSAN = 56 "+ 
	             " 		        GROUP BY TPHA.ID_HAKMILIK "+ 
	             " 		) TPHT "+
	             " ,( "+ 
	             " 		    SELECT TPH.ID_HAKMILIK "+ 
	             " 		       ,CASE "+ 
	             " 		            WHEN COUNT(TPH.ID_HAKMILIK) > 0 "+  
	             " 		                THEN 'IMEJ' "+ 
	             " 		            ELSE '' "+ 
	             " 		        END TPI "+          
	             " 		    FROM "+ 
	             " 		    TBLHTPHAKMILIK TPH,TBLHTPGAMBAR TPG "+ 
	             " 		    WHERE TPH.ID_HAKMILIK = TPG.ID_HAKMILIK "+ 
	             " 		    GROUP BY TPH.ID_HAKMILIK "+  
	             " 		) HIMEJ "+ 
	            "WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
	             "AND B.ID_FAIL = C.ID_FAIL "+
	             "AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
	             "AND A.ID_LOT = F.ID_LOT "+
	             "AND C.ID_URUSAN = D.ID_URUSAN "+
	             "AND A.ID_LOT = H.ID_LOT "+
	             "AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
	             "AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN " +
	             " AND A.ID_HAKMILIK = TPHT.ID_HAKMILIK(+) " +
	             " AND A.ID_HAKMILIK = HIMEJ.ID_HAKMILIK(+) "+
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
//						sql = sql + " AND (A.STATUS_SAH IN (" +
//								"		SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
//								"))";	
						if (idStatus.trim().equals("1")) {
							//AKTIF
							sql = sql + " AND (A.STATUS_SAH IN (" +
							"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
							"))";						
							
						} else if (idStatus.trim().equals("2")) {
							sql = sql + " AND (A.STATUS_SAH IN (" +
							"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
							"))";						
						
						}
					}
			}		
			sql = sql + " AND A.STATUS_SAH IN ('E')";						
			sql = sql +" ORDER BY C.NO_FAIL DESC ";			
			sql = getSQLPengambilan("");
			//myLog.debug("getCarianSenaraiHakmilikRizabAktifCARIAN ->sql= "+sql);
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
	    	  //h.put("statusRizab", rs.getString("STATUS_RIZAB")== null? "":rs.getString("STATUS_RIZAB"));
	    	  //h.put("tarikhTerima",rs.getDate("TARIKH_TERIMA")== null? "":sdf.format(rs.getDate("TARIKH_TERIMA")));	    	  
	    	  if(rs.getString("NO_HAKMILIK")!= null){
	    		  h.put("jenisTanah","M");
	    	  }else{
	    		  h.put("jenisTanah","R");
	    	  }
	    	  if(rs.getString("NO_HAKMILIK")!= null && rs.getString("NO_WARTA")!= null){
	    		  h.put("jenisTanah","X");
	    	  }
	    	  //h.put("ceroboh", Utils.isNull(rs.getString("CEROBOH")));
	    	  //h.put("isImej", Utils.isNull(rs.getString("TPI")));
	    	  listCarianHakmilikDanRizab.addElement(h);
	    	  bil++;
	    	  count++;    	  
	      }
	      	
	    } finally {
	      if (db != null) db.close();
	      
	    }		
	    return listCarianHakmilikDanRizab;
	    
	}
	
	//PAPAR CARIAN HAKMILIK DAN RIZAB
	@Override
	public Hashtable getCarianHakmilikRizab(String idHakmilik)throws Exception {
	    listCarianHakmilikDanRizab = new Vector();
		Hashtable h = null;
	    try {    	
	    	db = new Db();
	    	Statement stmt = db.getStatement(); 
	    	sql = "SELECT DISTINCT A.ID_HAKMILIK,C.NO_FAIL, "+
	        	"CASE "+
	            "WHEN E.KOD_JENIS_HAKMILIK = '00' THEN '' "+
	            "ELSE E.KOD_JENIS_HAKMILIK "+
	            "END AS JENIS_HAKMILIK, UPPER(A.NO_HAKMILIK) NO_HAKMILIK, A.NO_WARTA, "+
	            "H.KETERANGAN AS JENIS_LOT, A.NO_LOT, D.NAMA_URUSAN "+
//	            ",CASE "+
//	            "WHEN A.STATUS_SAH = 'H' "+
//	                "THEN 'HAKMILIK ASAL TIADA' "+
//	             "WHEN A.STATUS_SAH = 'D' "+
//	                "THEN 'DAFTAR' "+
//	             "WHEN A.STATUS_SAH = 'P' "+
//	                "THEN 'BATAL (PELEPASAN)' "+
//	             "WHEN A.STATUS_SAH = 'T' "+
//	                "THEN 'TUKAR GUNA' "+ 
//	             "WHEN A.STATUS_SAH = 'S' "+
//	                "THEN 'BATAL(SAMBUNGAN)' "+
//	             "WHEN A.STATUS_SAH = 'B' "+
//	                "THEN 'BATAL' "+
//	             "WHEN A.STATUS_SAH = 'M' "+
//		         	"THEN 'BATAL (PINDAHMILIK)' "+
//		         "WHEN A.STATUS_SAH = 'Q' "+
//				   	"THEN 'BATAL (PERMOHONAN)' "+		         	
//				 "WHEN A.STATUS_SAH = 'L' "+
//				 	"THEN 'BATAL (PERLETAKHAKAN)' "+		         	
//				 "ELSE 'TIADA' "+
//	             "END AS STATUS_HAKMILIK "+
				 " ,NVL(( SELECT KETERANGAN FROM TBLHTPRUJSTATUSAH WHERE STATUS_SAH = A.STATUS_SAH "+
				 " ),'TIADA MAKLUMAT') STATUS_HAKMILIK "+
	             " ,A.STATUS_RIZAB, A.TARIKH_TERIMA," +
	             "A.KEGUNAAN_TANAH "+
	             "FROM TBLHTPHAKMILIK A, "+
	             "TBLPERMOHONAN B, "+
	             "TBLPFDFAIL C, "+
	             "TBLRUJURUSAN D, "+
	             "TBLRUJJENISHAKMILIK E, "+
	             "TBLRUJLOT F, "+
	             "TBLRUJLOT H, "+
	             "TBLRUJKEMENTERIAN RK," +
	             "TBLRUJKEMENTERIANMAPPING RKME "+
	             "WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
	             "AND B.ID_FAIL = C.ID_FAIL "+
	             "AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
	             "AND A.ID_LOT = F.ID_LOT "+
	             "AND C.ID_URUSAN = D.ID_URUSAN "+
	             "AND A.ID_LOT = H.ID_LOT "+
	             "AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
	             "AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN ";
	          

			if (idHakmilik != null) {
				if (!idHakmilik.trim().equals("")) {
					sql = sql + " AND A.ID_HAKMILIK = '" + idHakmilik + "'";
				}
			} 

			sql = sql +" ORDER BY C.NO_FAIL DESC ";
			myLog.debug("getCarianSenaraiHakmilikRizabAktifCARIAN ->sql= "+sql);
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
	    	  //listCarianHakmilikDanRizab.addElement(h);
	    	  bil++;
	    	  count++;
	    	  
	      }
	    } finally {
	      if (db != null) db.close();
	      
	    }		
	    return h;
	    
	}
	
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
	            "F.KETERANGAN AS JENIS_LOT, A.NO_LOT, D.NAMA_URUSAN "+
//	            ",CASE "+
//	            "WHEN A.STATUS_SAH = 'H' "+
//	                "THEN 'HAKMILIK ASAL TIADA' "+
//	             "WHEN A.STATUS_SAH = 'D' "+
//	                "THEN 'DAFTAR' "+
//	             "WHEN A.STATUS_SAH = 'P' "+
//	                "THEN 'BATAL (PELEPASAN)' "+
//	             "WHEN A.STATUS_SAH = 'T' "+
//	                "THEN 'TUKAR GUNA' "+ 
//	             "WHEN A.STATUS_SAH = 'S' "+
//	                "THEN 'BATAL(SAMBUNGAN)' "+
//	             "WHEN A.STATUS_SAH = 'B' "+
//	                "THEN 'BATAL' "+
//	             "WHEN A.STATUS_SAH = 'M' "+
//		         	"THEN 'BATAL (PINDAHMILIK)' "+
//		         "WHEN A.STATUS_SAH = 'Q' "+
//			     	"THEN 'BATAL (PERMOHONAN)' "+
//				 "WHEN A.STATUS_SAH = 'L' "+
//				 	"THEN 'BATAL (PERLETAKHAKAN)' "+		         	
//			     "ELSE 'TIADA' "+
//	             "END AS STATUS_HAKMILIK, "+
				 " ,NVL(( SELECT KETERANGAN FROM TBLHTPRUJSTATUSAH WHERE STATUS_SAH = A.STATUS_SAH "+
				 " ),'TIADA MAKLUMAT') STATUS_HAKMILIK "+
	             " ,A.STATUS_RIZAB, A.TARIKH_TERIMA,A.KEGUNAAN_TANAH "+
	             
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
	
	public String getSQLPengambilan(String idHakmilik){
		sql = ""+
		"SELECT F.ID_FAIL,F.NO_FAIL,F.ID_KEMENTERIAN,PTP.ID_PERMOHONAN "+
		" ,RN.ID_NEGERI,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.Id_Mukim,RM.NAMA_MUKIM "+
		" ,RJH.Id_Jenishakmilik,RJH.Keterangan as HakKeterangan,RJH.KOD_JENIS_HAKMILIK JENIS_HAKMILIK "+
		" ,PTH.NO_HAKMILIK,PTH.ID_HAKMILIK "+
		" ,Case  "+
		"     WHEN PTH.NO_PT=null then PTH.NO_LOT "+
		"     WHEN PTH.NO_LOT=null then PTH.NO_PT "+
		"     else 'TIADA MAKLUMAT' "+
		" END NO_LOT "+
		" ,PTHA.ID_LOT " +
		" ,( SELECT KETERANGAN FROM TBLRUJLOT WHERE ID_LOT= PTHA.ID_LOT) JENIS_LOT "+
		//" --,lot.Keterangan as LotKeterangan
		" ,PTHA.NAMA_LUAS_ASAL LUAS "+
		//--,PTHA.ID_UNITLUASLOT_CONVERT ID_LUAS
		" ,'PENGAMBILAN' SUMBER,'' NO_WARTA,'TIADA' STATUS_HAKMILIK" +
		" , PTP.TUJUAN KEGUNAAN_TANAH, 'PENGAMBILAN' NAMA_URUSAN " +
		" FROM  "+
		" TBLPPTTEMPHAKMILIK PTH,TBLPPTHAKMILIK PTHA,TBLPPTPERMOHONAN PTP "+
		" ,TBLPFDFAIL F "+
		" ,TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM "+
		" ,TBLRUJJENISHAKMILIK RJH "+
		//--,TBLRUJLOT LOT
		//--,TBLRUJLUAS LUAS
		" WHERE  "+
		" PTH.ID_HAKMILIK_ASAL = PTHA.ID_HAKMILIK "+
		" AND PTP.ID_PERMOHONAN = PTHA.ID_PERMOHONAN "+
		" AND PTP.ID_FAIL = F.ID_FAIL "+
		" AND RN.ID_NEGERI= PTHA.ID_NEGERI  "+
		" AND RD.ID_DAERAH=PTHA.ID_DAERAH "+
		" AND RM.ID_MUKIM=PTHA.ID_MUKIM  "+
		" AND RJH.Id_Jenishakmilik=PTH.Id_Jenishakmilik "+
		"";
		return sql;
		
	}
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
	
}
