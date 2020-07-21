package ekptg.model.htp.utiliti;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.htp.rekod.ITanahCarian;

public class LaporanTanahBean implements ITanahCarian {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.utiliti.LaporanTanahBean.class);
	private static Vector<Hashtable<String,String>> senaraiTanah = null;
	private String sql = "";
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private	Db db = null;
	
	//PAPAR CARIAN HAKMILIK DAN RIZAB, 13 Parameter
	@Override 
	public Vector<Hashtable<String,String>> getCarianSenaraiHakmilikRizab(String idJenisTanah
			,String idNegeri, String idDaerah, String idMukim
			,String noFail, String idJenisHakmilik, String noHakmilik
			,String noWarta
			,String idLot,String noLot
			,String idAgensi, String idKementerian
			,String idStatus)throws Exception {
		senaraiTanah = new Vector<Hashtable<String,String>>();
	    try {    	
	    	db = new Db();
	    	Statement stmt = db.getStatement(); 
	    	sql = "" +
	    		" SELECT DISTINCT A.ID_HAKMILIK,C.NO_FAIL "+
	        	" ,CASE "+
	            " 	WHEN E.KOD_JENIS_HAKMILIK = '00' THEN '' "+
	            " 	ELSE E.KOD_JENIS_HAKMILIK "+
	            " END AS JENIS_HAKMILIK, UPPER(A.NO_HAKMILIK) NO_HAKMILIK, A.NO_WARTA "+
	            " ,H.KETERANGAN AS JENIS_LOT, A.NO_LOT, NVL(D.NAMA_URUSAN,'TIADA') NAMA_URUSAN "+
				" ,NVL(( " +
				//"SELECT KETERANGAN FROM TBLHTPRUJSTATUSAH WHERE STATUS_SAH = A.STATUS_SAH "+
				"  	SAH.KETERANGAN	"+
				" ),'TIADA MAKLUMAT') STATUS_HAKMILIK "+
	            " ,A.STATUS_RIZAB, A.TARIKH_TERIMA" +
	            " ,A.KEGUNAAN_TANAH "+
	            " ,TPHT.CEROBOH,HIMEJ.TPI " +
	            " FROM TBLHTPHAKMILIK A, "+
	            " TBLPERMOHONAN B, "+
	            " TBLPFDFAIL C, "+
	            " TBLRUJURUSAN D, "+
	            " TBLRUJJENISHAKMILIK E, "+
	            " TBLRUJLOT F, "+
	            " TBLRUJLOT H, "+
	            " TBLRUJKEMENTERIAN RK," +
	            " TBLRUJKEMENTERIANMAPPING RKME "+
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
	            " 		) HIMEJ " +
	            " , TBLHTPRUJSTATUSAH SAH "+ 
				//"SELECT KETERANGAN FROM TBLHTPRUJSTATUSAH WHERE STATUS_SAH = A.STATUS_SAH "+

	            " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
	            " AND B.ID_FAIL = C.ID_FAIL "+
	            " AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
	            " AND A.ID_LOT = F.ID_LOT "+
	            " AND C.ID_URUSAN = D.ID_URUSAN(+) "+ //2017/06/08, tambah +
	            " AND A.ID_LOT = H.ID_LOT "+
	            " AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
	            " AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN " +
	            " AND A.ID_HAKMILIK = TPHT.ID_HAKMILIK(+) " +
	            " AND A.ID_HAKMILIK = HIMEJ.ID_HAKMILIK(+) "+
	            " AND A.STATUS_SAH = SAH.STATUS_SAH(+) "+
				//"SELECT KETERANGAN FROM TBLHTPRUJSTATUSAH WHERE STATUS_SAH = A.STATUS_SAH "+
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
			sql = sql +" ORDER BY C.NO_FAIL DESC ";
			System.out.println("getCarianSenaraiHakmilikRizabAktifCARIAN ->sql= "+sql);
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
	    	  h.put("ceroboh", Utils.isNull(rs.getString("CEROBOH")));
	    	  h.put("isImej", Utils.isNull(rs.getString("TPI")));
	    	  senaraiTanah.addElement(h);
	    	  bil++;
	    	  //count++;
	    	  
	      }
	    } finally {
	      if (db != null) db.close();
	      
	    }		
	    return senaraiTanah;
	    
	}
	/**
	 * 
	 * @param idJenisTanah
	 * @param idNegeri
	 * @param idDaerah
	 * @param idMukim
	 * @param noFail
	 * @param idJenisHakmilik
	 * @param noHakmilik
	 * @param noWarta
	 * @param idLot
	 * @param noLot
	 * @param idAgensi
	 * @param idKementerian
	 * @param idStatus
	 * @param kegunaan
	 * @return
	 * @throws Exception
	 * 14 Parameter
	 */
	@Override
	public Vector<Hashtable<String,String>> getCarianSenaraiHakmilikRizab(String idJenisTanah
			,String idNegeri, String idDaerah, String idMukim
			,String noFail
			,String idJenisHakmilik, String noHakmilik
			,String noWarta
			,String idLot, String noLot
			,String idAgensi, String idKementerian
			,String idStatus, String kegunaan) throws Exception {
		senaraiTanah = new Vector<Hashtable<String,String>>();
	    try {    	
	    	db = new Db();
	    	//idHakmilik| jenisTanah| statusSah| noFail| kodJenis| noHakmilik| noWarta| kodLot| noLot|
	    	// kegunaanTanah| ceroboh| isImej
	    	Statement stmt = db.getStatement(); 
	    	sql = "" +
	    		" SELECT DISTINCT A.ID_HAKMILIK "+
	        	" ,CASE "+
	            " 	WHEN RJH.KOD_JENIS_HAKMILIK = '00' THEN '' "+
	            " 	ELSE RJH.KOD_JENIS_HAKMILIK "+
	            " END AS JENIS_HAKMILIK, UPPER(A.NO_HAKMILIK) NO_HAKMILIK, A.NO_WARTA "+
	            " ,LOT.KETERANGAN AS JENIS_LOT, A.NO_LOT "+
				" ,NVL(SAH.KETERANGAN,'TIADA MAKLUMAT') STATUS_HAKMILIK "+
	            " ,A.STATUS_RIZAB, A.TARIKH_TERIMA" +
	            " ,A.KEGUNAAN_TANAH "+
	            " ,P.NO_FAIL, P.NAMA_URUSAN "+
	            " ,TPHT.CEROBOH,HIMEJ.TPI " +
	            " ,NVL(GIS.UPI,'N') GIS_HANTAR "+  
	            " ,NVL(GIS.LATITUDE,'N') GIS_CHARTING "+  
	            " ,GETGISTATUSREKOD(RJH.STATUS_HAKMILIK,A.NO_WARTA) GIS_STATUS " +
	            " FROM TBLHTPHAKMILIK A "+
	            ",TBLRUJNEGERI RN "+
	            ",TBLRUJDAERAH RD "+
	            ",TBLRUJMUKIM RM "+
	            ",TBLRUJJENISHAKMILIK RJH "+
	            ",TBLRUJLOT LOT "+
	            ",TBLRUJKEMENTERIAN RK " +
	            ",TBLRUJKEMENTERIANMAPPING RKME "+
	            ", TBLHTPRUJSTATUSAH SAH "+ 
	            ", TBLINTGIS GIS "+ 
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
	            " ,(  SELECT B.ID_PERMOHONAN,C.NO_FAIL,D.NAMA_URUSAN "+ 
	            "         FROM TBLPERMOHONAN B,TBLPFDFAIL C,TBLRUJURUSAN D "+
	            "         WHERE B.ID_FAIL = C.ID_FAIL "+  
	            "             AND C.ID_URUSAN = D.ID_URUSAN "+
	            "             AND C.ID_URUSAN IN (1,10,2,5,4) "+
	            "          GROUP BY B.ID_PERMOHONAN,C.NO_FAIL,D.NAMA_URUSAN "+
	            "     ) P "+
	            "     ,(   SELECT  "+
	            "             MT.ID_HAKMILIK "+
	            //"             --,MT.NO_LOT,NVL(MT.NO_HAKMILIK,'TIADA') "+
	            "             ,GETUPI(RN.KOD_NEGERI,RD.KOD_DAERAH_UPI,RM.KOD_MUKIM_UPI,'000',RJH.STATUS_HAKMILIK,MT.NO_LOT,MT.NO_HAKMILIK,RJH.KOD_JENIS_HAKMILIK ) UPI "+
	            "         FROM TBLHTPHAKMILIK MT "+
	            "             ,TBLRUJJENISHAKMILIK RJH "+
	            "             , TBLRUJNEGERI RN "+
	            "             ,TBLRUJDAERAH RD "+
	            "             ,TBLRUJMUKIM RM "+
	            "         WHERE MT.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK "+
	            "             AND MT.ID_NEGERI = RN.ID_NEGERI "+
	            "             AND MT.ID_DAERAH = RD.ID_DAERAH "+
	            "             AND MT.ID_MUKIM = RM.ID_MUKIM "+
	           // "             --AND MT.ID_HAKMILIK IN (1610100914) "+
	            "     ) UP "+
	            " WHERE A.ID_PERMOHONAN = P.ID_PERMOHONAN "+
	            " AND A.ID_NEGERI = RN.ID_NEGERI "+
	            " AND A.ID_DAERAH = RD.ID_DAERAH "+
	            " AND A.ID_MUKIM = RM.ID_MUKIM "+
	            " AND A.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK "+
	            " AND A.ID_LOT = LOT.ID_LOT "+
	            " AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
	            " AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN " +
	            " AND A.STATUS_SAH = SAH.STATUS_SAH(+) "+
	      	    " AND A.ID_HAKMILIK = UP.ID_HAKMILIK "+
	      	    " AND A.ID_HAKMILIK = TPHT.ID_HAKMILIK(+) " +
	            " AND A.ID_HAKMILIK = HIMEJ.ID_HAKMILIK(+) "+
	            " AND UP.UPI = GIS.UPI(+) "+
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
					sql = sql + " AND UPPER(P.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase() + "'|| '%'";
				}
			}
			//id jenishakmilik
			//myLog.info("idJenisHakmilik="+idJenisHakmilik);
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
					if (!idKementerian.trim().equals("99999")&& !idKementerian.trim().equals("-1")) {
						sql = sql + " AND A.ID_KEMENTERIAN = '" + idKementerian + "'";
					}
				}
			}
			//no agensi
			if (idAgensi != null) {
				if (!idAgensi.trim().equals("")) {
					if (!idAgensi.trim().equals("99999")&& !idAgensi.trim().equals("-1")) {
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
			if (!kegunaan.equals("")) {
				sql += " AND A.KEGUNAAN_TANAH LIKE '%"+kegunaan+"%'";
			}
			sql = sql +" ORDER BY P.NO_FAIL DESC ";
			myLog.debug("getCarianSenaraiHakmilikRizab:sql= "+sql);
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
	    	  h.put("ceroboh", Utils.isNull(rs.getString("CEROBOH")));
	    	  h.put("isImej", Utils.isNull(rs.getString("TPI")));
	    	  h.put("gisStatus", rs.getString("GIS_STATUS"));
	    	  h.put("gisHantar", rs.getString("GIS_HANTAR"));
	    	  h.put("gisCharting", rs.getString("GIS_CHARTING"));
	    	  senaraiTanah.addElement(h);
	    	  bil++;

	      	}
	      	
	    } finally {
	      if (db != null) db.close();
	      
	    }		
	    return senaraiTanah;
	    
	}	
	
	//PAPAR CARIAN HAKMILIK DAN RIZAB
	@Override
	public Hashtable<String,String> getCarianHakmilikRizab(String idHakmilik)throws Exception {
		senaraiTanah = new Vector<Hashtable<String,String>>();
		Hashtable<String,String> h = null;
	    try {    	
	    	db = new Db();
	    	Statement stmt = db.getStatement(); 
	    	sql = "SELECT DISTINCT A.ID_HAKMILIK,C.NO_FAIL, "+
	        	"CASE "+
	            "	WHEN E.KOD_JENIS_HAKMILIK = '00' THEN '' "+
	            "	ELSE E.KOD_JENIS_HAKMILIK "+
	            "END AS JENIS_HAKMILIK, UPPER(A.NO_HAKMILIK) NO_HAKMILIK, A.NO_WARTA, "+
	            "H.KETERANGAN AS JENIS_LOT, A.NO_LOT, D.NAMA_URUSAN "+
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
			//myLog.debug("getCarianSenaraiHakmilikRizabAktifCARIAN ->sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);	    
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
	    	  //listCarianHakmilikDanRizab.addElement(h);
	    	  bil++;
	    	  //count++;
	    	  
	      }
	    } finally {
	      if (db != null) db.close();
	      
	    }		
	    return h;
	    
	}
	
	//PAPAR CARIAN HAKMILIK DAN RIZAB, 12 Parameter
	@Override
	public Vector<Hashtable<String,String>> getCarianSenaraiHakmilikRizab(String idJenisTanah
			,String idNegeri, String idDaerah,String idMukim
			,String noFail
			,String idJenisHakmilik,String noHakmilikWarta
			,String idLot,String noLot
			,String idAgensi, String idKementerian
			,String idStatus)throws Exception {
	    Db db = null;
	    senaraiTanah = new Vector<Hashtable<String,String>>();
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();  
	    	sql = "SELECT DISTINCT A.ID_HAKMILIK,C.NO_FAIL, "+
	    		"CASE "+
	            "	WHEN E.KOD_JENIS_HAKMILIK = '00' THEN '' "+
	            "	ELSE E.KOD_JENIS_HAKMILIK "+
	            "END AS JENIS_HAKMILIK, A.NO_HAKMILIK, A.NO_WARTA, "+
	            "F.KETERANGAN AS JENIS_LOT, A.NO_LOT, D.NAMA_URUSAN "+
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
	      		senaraiTanah.addElement(h);
	      		bil++;
	      		//count++;
	    	  
	      	}
	      	//azam remark - no need to add empty hashtable

	    } finally {
	      if (db != null) db.close();
	    
	    }		
	    return senaraiTanah;
	    
	}	
	
//	private IHtp getIHTP(){
//		if(iHTP== null)
//			iHTP = new HtpBean();
//		return iHTP;
//	}
	
	
}
