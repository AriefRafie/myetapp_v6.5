package ekptg.model.htp.rekod;

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
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;

public class FrmRekodUtilData {
	private IHtp iHTP = null;  
	private static Logger mylog = Logger.getLogger(ekptg.model.htp.rekod.FrmRekodUtilData.class);	
	//private static Vector listHakmilik = null;
	//private static Vector listMaklumatFail = null;
	//private static Vector listSenaraiFail = null;		
	private ITanah iHakmilik = null;
	private static Vector<Hashtable<String, String>> listCarianHakmilikDanRizab = null;
	//private static Connection conn = null;
	private static FrmRekodUtilData instance = null;

	public static FrmRekodUtilData getInstance(){
	    if (instance == null) instance = new FrmRekodUtilData();
	    return instance;
	}
	//PAPAR CARIAN HAKMILIK DAN RIZAB
	public boolean isHTPHakmilik(String idPermohonan)throws Exception {
	    Db db = null;
	    String sql = "";
	    boolean returnValue = false;
	    try {   	
	      db = new Db();
	      Statement stmt = db.getStatement();
    
	      sql = "SELECT ID_HAKMILIK FROM TBLHTPHAKMILIK "+
	             "WHERE ID_PERMOHONAN = '"+idPermohonan+"'";
	      //mylog.info("isHTPHakmilik: "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      //Azam add if (rs.next())
	      if (rs.next()) {
	    	  returnValue = true;
	      }
		}catch(Exception e1){
	    	throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] RALAT PILIH MAKLUMAT TANAH , "+e1.toString()));
	    } finally {
	      if (db != null) db.close();
	    }		
	    //mylog.debug("isHTPHakmilik:"+returnValue);
	    return returnValue;
	}
	
	//PAPAR CARIAN HAKMILIK DAN RIZAB
	public Vector<Hashtable<String, String>> getCarianSenaraiHakmilikRizabById(String idPermohonan,String idJenisTanah,String idNegeri, String idDaerah,String idMukim,String noFail,String idJenisHakmilik,String noHakmilik,String noWarta,String idLot,String noLot, String idAgensi, String idKementerian, String idStatus)throws Exception {
	    Db db = null;
	    listCarianHakmilikDanRizab = new Vector<Hashtable<String, String>>();
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {	    	
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	sql = "SELECT DISTINCT A.ID_HAKMILIK,C.NO_FAIL, "+
	            "CASE "+
	            "WHEN E.KOD_JENIS_HAKMILIK = '00' THEN '' "+
	            "ELSE E.KOD_JENIS_HAKMILIK "+
	            "END AS JENIS_HAKMILIK, A.NO_HAKMILIK, A.NO_WARTA, "+
	            "H.KETERANGAN AS JENIS_LOT, A.NO_LOT, D.NAMA_URUSAN "+
				",( SELECT SSI.KETERANGAN FROM TBLHTPRUJSTATUSAH SSI "+
				" WHERE SSI.STATUS_SAH = A.STATUS_SAH "+
			 	" ) STATUS_HAKMILIK "+
	             " ,A.NO_BANGUNAN,A.NO_TINGKAT,A.NO_PETAK "+
	             " ,A.STATUS_RIZAB, A.TARIKH_TERIMA," +
	             "A.KEGUNAAN_TANAH ," +
	             "B.ID_PERMOHONAN, A.ID_KEMENTERIAN,K.NAMA_KEMENTERIAN," +
	             "NVL ( (SELECT   RAI.nama_agensi " +
                  " FROM   TBLRUJAGENSI RAI " +
                  " WHERE   RAI.ID_AGENSI = A.ID_AGENSI), " +
		          " 'TIADA MAKLUMAT AGENSI')" +
		          " NAMA_AGENSI," +
		          " NVL ( (SELECT   RAI.ID_AGENSI " +
		          "FROM   TBLRUJAGENSI RAI " +
		          "WHERE   RAI.ID_AGENSI =A.ID_AGENSI), '0') " +
		          "ID_AGENSI, " +
		          "NVL (A.ID_DAERAH, '0') ID_DAERAH, " +
		          "NVL (PP.NO_RUJUKAN_KJP, '') NO_RUJUKAN_KJP, " +
		          //"NVL (PP.NO_RUJUKAN_LAIN, '') NO_RUJUKAN_LAIN, " +
		          "NVL (PP.NO_RUJUKAN_PTD, '') NO_RUJUKAN_PTD, " +
		          "NVL (PP.NO_RUJUKAN_PTG, '') NO_RUJUKAN_PTG " +
	             " "+
	             "FROM TBLHTPHAKMILIK A, "+
	             "TBLPERMOHONAN B, "+
	             "TBLPFDFAIL C, "+
	             "TBLRUJURUSAN D, "+
	             "TBLRUJJENISHAKMILIK E, "+
	             "TBLRUJLOT F, "+
	             "TBLRUJLOT H, "+
	             "TBLHTPHAKMILIKPERIHAL I, "+
	             "TBLHTPHAKMILIKAGENSI J, TBLRUJKEMENTERIAN K, TBLHTPPERMOHONAN PP "+
	             "WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
	             "AND B.ID_FAIL = C.ID_FAIL "+
	             "AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
	             "AND A.ID_LOT = F.ID_LOT "+
	             "AND C.ID_URUSAN = D.ID_URUSAN "+
	             "AND A.ID_LOT = H.ID_LOT "+
	             "AND A.ID_HAKMILIK = J.ID_HAKMILIK(+) "+
	             "AND A.ID_HAKMILIK = I.ID_HAKMILIK(+) "+
	             "AND B.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
	             "AND A.ID_KEMENTERIAN = K.ID_KEMENTERIAN "+
	             "AND A.ID_PERMOHONAN = '"+idPermohonan+"' " +
	             "";
	          
			//idJenisTanah
			if (idJenisTanah != null) {
				if (!idJenisTanah.trim().equals("")) {
					if (Integer.parseInt(idJenisTanah) == 1){
						//sql = sql + " AND A.NO_HAKMILIK IS NOT NULL AND A.STATUS_RIZAB IS NOT NULL ";
						sql = sql + " AND A.STATUS_RIZAB IS NOT NULL ";
					} else if (Integer.parseInt(idJenisTanah) == 2){
						//sql = sql + " AND A.NO_HAKMILIK IS NULL AND A.STATUS_RIZAB IS NULL ";
						sql = sql + " AND A.STATUS_RIZAB IS NULL ";
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
						sql = sql + " AND J.ID_KEMENTERIAN = '" + idKementerian + "'";
					}
				}
			}
			//no agensi
			if (idAgensi != null) {
				if (!idAgensi.trim().equals("")) {
					if (!idAgensi.trim().equals("99999")) {
						sql = sql + " AND J.ID_AGENSI = '" + idAgensi + "'";
					}
				}
			} 
			//idStatus
			if (idStatus != null) {
				if (!idStatus.trim().equals("")) {
					if (idStatus.trim().equals("1")) {
						//AKTIF
						//sql = sql + " AND (A.STATUS_SAH IS NULL OR A.STATUS_SAH IN ('T','H','D'))";
						/* 17/08/2011 */
						sql = sql + " AND (A.STATUS_SAH NOT IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
						"))";	
					} else if (idStatus.trim().equals("2")) {
						//BATAL
						//sql = sql + " AND A.STATUS_SAH IN ('B','M',P','S')";
						/* 17/08/2011 */
						sql = sql + " AND (A.STATUS_SAH IN (" +
						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
						"))";						
					}
					//fir edit to get all record without batal
					else{
						//AKTIF
						sql = sql + " AND (A.STATUS_SAH IS NULL OR A.STATUS_SAH IN ('T','H','D'))";
					}

					
				}
			}
			
			sql = sql +" ORDER BY A.TARIKH_TERIMA DESC ";
			mylog.info("getCarianSenaraiHakmilikRizabById: -------------------------------------------"+sql);
			ResultSet rs = stmt.executeQuery(sql);
	    
			Hashtable<String, String> h = null;
	      	int bil = 1;
	      	int count = 0;
	      	while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  h.put("bil", bil+".");
	    	  h.put("idHakmilik",rs.getString("ID_HAKMILIK"));
	    	  h.put("noFail",rs.getString("NO_FAIL"));
	    	  h.put("kodJenis", rs.getString("JENIS_HAKMILIK")== null?"":rs.getString("JENIS_HAKMILIK"));
	    	  h.put("noHakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
	    	  h.put("noStrata", rs.getString("NO_BANGUNAN")== null?"":"["+rs.getString("NO_BANGUNAN")+","+rs.getString("NO_TINGKAT")+","+rs.getString("NO_PETAK")+"]");
	    	  h.put("noWarta", rs.getString("NO_WARTA")== null?"":rs.getString("NO_WARTA"));
	    	  h.put("kodLot", rs.getString("JENIS_LOT")== null?"":rs.getString("JENIS_LOT"));
	    	  h.put("noLot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
	    	  h.put("namaUrusan", rs.getString("NAMA_URUSAN")== null?"":rs.getString("NAMA_URUSAN"));
	    	  h.put("statusSah", rs.getString("STATUS_HAKMILIK")== null?"":rs.getString("STATUS_HAKMILIK"));
	    	  h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH")== null?"":rs.getString("KEGUNAAN_TANAH").toUpperCase());
	    	  h.put("statusRizab", rs.getString("STATUS_RIZAB")== null? "":rs.getString("STATUS_RIZAB"));
	    	  h.put("tarikhTerima",rs.getDate("TARIKH_TERIMA")== null? "":sdf.format(rs.getDate("TARIKH_TERIMA")));
	    	  h.put("idPermohonan",rs.getString("ID_PERMOHONAN")== null? "":rs.getString("ID_PERMOHONAN"));
	    	  h.put("idKementerian", rs.getString("ID_KEMENTERIAN")==null ? "0" :rs.getString("ID_KEMENTERIAN"));
    		  h.put("kementerian",Utils.isNull(rs.getString("NAMA_KEMENTERIAN")));
    		  h.put("agensi", rs.getString("NAMA_AGENSI")==null ? "" :rs.getString("NAMA_AGENSI"));
	    	  h.put("idAgensi", rs.getString("ID_AGENSI")==null ? "0" :rs.getString("ID_AGENSI"));
	    	  h.put("idDaerah", rs.getString("ID_DAERAH")==null ? "0" :rs.getString("ID_DAERAH"));  		 
	    	  h.put("noFailKJP", rs.getString("NO_RUJUKAN_KJP")==null ? "" :rs.getString("NO_RUJUKAN_KJP"));
	    	  h.put("noFailPTD", Utils.isNull(rs.getString("NO_RUJUKAN_PTD")));
	    	  h.put("noFailPTG", Utils.isNull(rs.getString("NO_RUJUKAN_PTG")));
	    	  
	    	  if(rs.getString("NO_HAKMILIK")!= null){
	    		  h.put("jenisTanah","M");
	    	  }else{
	    		  h.put("jenisTanah","R");
	    	  }
	    	  listCarianHakmilikDanRizab.addElement(h);
	    	  bil++;
	    	  count++;
	    	  
	    	 
	      }
	      if (count == 0){
	    	  h = new Hashtable<String, String>();
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
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }		
	    return listCarianHakmilikDanRizab;
	}		

	/* Created by : Mohamad Rosli 2010/01/31
	   * Tujuan	  : Data dari tblhtphakmilikurusan
	   * Pra syarat : Static untuk urusan Permohonan-1,Rizab-10,Pembelian-2,Perletakhakan-5
	   */
	  public Vector<Hashtable<String, String>> getHakmilikUrusanMengikutUrusan(String idUrusan)throws Exception {
		  Db db = null;
		  String sql = "";
	      Hashtable<String, String> h = null;
	      Vector<Hashtable<String, String>> list = null;
		  try {
			  db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		 
		      r.add("P.ID_PERMOHONAN");
		      r.add("RN.ID_NEGERI");	      
		      r.add("RN.NAMA_NEGERI");
		      r.add("F.NO_FAIL");
		      r.add("F.TAJUK_FAIL");
		      r.add("PP.NO_RUJUKAN_PTG");
		      r.add("PP.NO_RUJUKAN_PTD");
		      r.add("F.ID_URUSAN");
		      r.add("F.ID_FAIL");

		      r.add("RN.ID_NEGERI",r.unquote("F.ID_NEGERI"));
		      r.add("P.ID_FAIL",r.unquote("F.ID_FAIL"));
		      r.add("P.ID_PERMOHONAN",r.unquote("PP.ID_PERMOHONAN"));
		      
		      sql = r.getSQLSelect("TBLPFDFAIL F,TBLPERMOHONAN P,TBLHTPPERMOHONAN PP " +
		      		",TBLRUJNEGERI RN ");
		      sql +=" AND F.ID_URUSAN IN ("+idUrusan+") AND ROWNUM <= 50 ";
		      sql +=" ORDER BY F.TARIKH_MASUK";
		      //mylog.info("getHakmilikUrusanMengikutSubUrusan("+idUrusan+") ::"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      list = new Vector<Hashtable<String, String>>();
		      while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  h.put("IdNegeri", rs.getString("ID_NEGERI")==null ? "0" :rs.getString("ID_NEGERI"));
		    	  h.put("labelNegeri", rs.getString("Id_Negeri")+" - "+rs.getString("NAMA_NEGERI"));
		    	  h.put("id", rs.getString("ID_FAIL"));
		    	  h.put("no", Utils.isNull(rs.getString("NO_FAIL")));
		    	  h.put("negeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
		    	  h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
		    	  //h.put("tujuan", Utils.isNull(rs.getString("TUJUAN")));
		    	  //h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
		     	  h.put("ptg", Utils.isNull(rs.getString("NO_RUJUKAN_PTG")));
		    	  h.put("ptd", Utils.isNull(rs.getString("NO_RUJUKAN_PTD")));
		    	  h.put("flagTanah", Utils.isNull(rs.getString("ID_URUSAN")));
		    	  h.put("idpermohonan", rs.getString("ID_PERMOHONAN")==null ? "0" :rs.getString("ID_PERMOHONAN"));
		    	  list.addElement(h);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }	
	  
		/* Created by : Mohamad Rosli 2020/04/01
	   * Tujuan	  : Data dari tblhtphakmilikurusan
	   * Pra syarat : Static untuk urusan Permohonan-1,Rizab-10,Pembelian-2,Perletakhakan-5
	   * Tambah parameter idNegeri
	   */
	public Vector<Hashtable<String, String>> getHakmilikUrusanMengikutUrusan(String idUrusan,String idNegeri) 
		throws Exception {
		Db db = null;
		String sql = "";
		Hashtable<String, String> h = null;
		Vector<Hashtable<String, String>> list = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		 
			r.add("P.ID_PERMOHONAN");
		    r.add("RN.ID_NEGERI");	      
		    r.add("RN.NAMA_NEGERI");
		    r.add("F.NO_FAIL");
		    r.add("F.TAJUK_FAIL");
		    r.add("PP.NO_RUJUKAN_PTG");
		    r.add("PP.NO_RUJUKAN_PTD");
		    r.add("F.ID_URUSAN");
		    r.add("F.ID_FAIL");

		    r.add("RN.ID_NEGERI",r.unquote("F.ID_NEGERI"));
		    r.add("P.ID_FAIL",r.unquote("F.ID_FAIL"));
		    r.add("P.ID_PERMOHONAN",r.unquote("PP.ID_PERMOHONAN"));
		    r.add("F.ID_NEGERI",r.unquote(idNegeri));
		      
		    sql = r.getSQLSelect("TBLPFDFAIL F,TBLPERMOHONAN P,TBLHTPPERMOHONAN PP " +",TBLRUJNEGERI RN ");
		    sql +=" AND F.ID_URUSAN IN ("+idUrusan+") AND ROWNUM <= 50 ";
		    sql +=" ORDER BY F.TARIKH_MASUK";
		    //mylog.info("getHakmilikUrusanMengikutSubUrusan("+idUrusan+") ::"+sql);
		    ResultSet rs = stmt.executeQuery(sql);
		    list = new Vector<Hashtable<String, String>>();
		    while (rs.next()) {
		    	h = new Hashtable<String, String>();
				h.put("IdNegeri",rs.getString("ID_NEGERI") == null ? "0" : rs.getString("ID_NEGERI"));
				h.put("labelNegeri",rs.getString("Id_Negeri") + " - "+ rs.getString("NAMA_NEGERI"));
				h.put("id", rs.getString("ID_FAIL"));
				h.put("no", Utils.isNull(rs.getString("NO_FAIL")));
				h.put("negeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
				h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
				//h.put("tujuan", Utils.isNull(rs.getString("TUJUAN")));
				// h.put("keterangan",
				// Utils.isNull(rs.getString("KETERANGAN")));
				h.put("ptg", Utils.isNull(rs.getString("NO_RUJUKAN_PTG")));
				h.put("ptd", Utils.isNull(rs.getString("NO_RUJUKAN_PTD")));
				h.put("flagTanah", Utils.isNull(rs.getString("ID_URUSAN")));
				h.put("idpermohonan",rs.getString("ID_PERMOHONAN") == null ? "0" : rs.getString("ID_PERMOHONAN"));
				list.addElement(h);
				
			}
			return list;
			
		} finally {
			if (db != null)
				db.close();
		}
		
	}

	public Vector<Hashtable<String, String>> senaraiFailMengikutCarian(String idUser
		,String nofail,String tajukfail
		,String id_kementerian,String id_agensi
		,String id_negeri,String id_daerah,String id_mukim,String id_urusan)throws Exception {
		Db db = null;
		String sql = "";
	    Vector<Hashtable<String, String>> list = null;
	    boolean isSearch = false;
		    
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	sql = " SELECT f.id_masuk,p.id_Fail, f.no_Fail, f.tajuk_Fail " +
		      		" ,(select nama_negeri from tblrujnegeri where id_negeri = f.id_negeri) negeri "+
		      		" ,s.keterangan, p.tujuan,PP.NO_RUJUKAN_PTD,F.ID_URUSAN,PP.NO_RUJUKAN_PTG" +
		      		" ,P.ID_PERMOHONAN "+
		      		" FROM TBLPERMOHONAN P, TBLHTPPERMOHONAN PP "+
		      		" ,Tblpfdfail F,Tblrujsuburusanstatusfail SF,Tblhtphakmilikurusan THMU,Tblrujsuburusanstatus US,Tblrujstatus S "+
		      		" WHERE P.id_Fail = F.id_Fail  " +
		      		" AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
		      		" AND P.ID_PERMOHONAN = SF.ID_PERMOHONAN  "+
		      		" AND P.ID_PERMOHONAN = thmu.ID_PERMOHONAN(+)  "+
		      		" AND SF.id_Suburusanstatus = US.id_Suburusanstatus  "+
		      		" AND SF.ID_FAIL = F.ID_FAIL " +
		      		" AND ( F.ID_STATUS <> '999' OR F.ID_STATUS is null) "+
		      		" AND US.id_Status = S.id_Status  " +
		      		" AND SF.aktif = '1'  " +
		      		" AND F.id_urusan in (1,2,5,10) ";
		      
//		      if (idUser != null) {
//		    	  sql = sql + "AND f.id_masuk='"+idUser+"'";
//		      }
		      
		      if (nofail != null) {
		    	  sql = sql + "AND lower(f.no_Fail) like '%"+nofail.toLowerCase()+"%' ";
		    	  isSearch = true;
		      }
		      
		      if (id_urusan != null && !"-1".equals(id_urusan)) {
		    	  sql = sql + " AND f.id_urusan in ("+id_urusan+") ";
		    	  isSearch = true;
		      }
		      
		      if (tajukfail != null) {
		    	  sql = sql + "AND lower(NVL(f.tajuk_Fail,' ')) like '%"+tajukfail.toLowerCase()+"%' ";
		    	  isSearch = true;
		      }
		      
		      if (id_kementerian != null && !"-1".equals(id_kementerian)) {
		    	  sql = sql + "AND f.id_kementerian = '"+id_kementerian+"' ";
		    	  isSearch = true;
		      }
		      
		      if (id_agensi != null && !"-1".equals(id_agensi)) {
		    	  sql = sql + "AND pp.id_agensi = '"+id_agensi+"' ";
		    	  isSearch = true;
		      }
		      
		      if (id_negeri != null && !"-1".equals(id_negeri)) {
		    	  sql = sql + "AND f.id_negeri = '"+id_negeri+"' ";
		    	  isSearch = true;
		      }
		      //DAERAH DLM HTP PERMOHONAN
		      if (id_daerah != null && !"-1".equals(id_daerah)) {
		    	  sql = sql + "AND pp.id_daerah = '"+id_daerah+"' ";
		    	  isSearch = true;
		      }
		      
		      if (id_mukim != null && !"-1".equals(id_mukim)) {
		    	  sql = sql + "AND thmu.id_mukim = '"+id_mukim+"' ";
		    	  isSearch = true;
		      }
		      
		      if (!isSearch) {
		    	  sql = sql + "AND ROWNUM <= 50 ";
		      }
		      
		  sql = sql + "ORDER BY  f.tarikh_kemaskini ";
		      //mylog.info("senaraiFailMengikutCarian() ::"+sql);
		  ResultSet rs = stmt.executeQuery(sql);
		  list = new Vector<Hashtable<String, String>>();
		  Hashtable<String, String> h;

		  while (rs.next()) {
			  h = new Hashtable<String, String>();
			  h.put("id", rs.getString("ID_FAIL"));
			  h.put("no", Utils.isNull(rs.getString("NO_FAIL")));
			  h.put("negeri", Utils.isNull(rs.getString("NEGERI")));
			  h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
			  h.put("tujuan", Utils.isNull(rs.getString("TUJUAN")));
			  h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
		   	  h.put("ptg", Utils.isNull(rs.getString("NO_RUJUKAN_PTG")));
		   	  h.put("ptd", Utils.isNull(rs.getString("NO_RUJUKAN_PTD")));
		      h.put("flagTanah", Utils.isNull(rs.getString("ID_URUSAN")));
		   	  h.put("idpermohonan", rs.getString("ID_PERMOHONAN")==null ? "0" :rs.getString("ID_PERMOHONAN"));
		   	  list.addElement(h);
		    	  
		  }
		  return list;
		    
	    } finally {
	    	if (db != null) db.close();
	    }
		  
	}
	
		 @SuppressWarnings("unchecked")
		 public Hashtable<String, Comparable> getPermohonanInfoV1(String idpermohonan)throws Exception {
			 Db db = null;
			 String sql = "";
			 try {
				 db = new Db();
				 Statement stmt = db.getStatement();
				 @SuppressWarnings("unused")
				 SQLRenderer r = new SQLRenderer();
				 sql= " select n.nama_negeri,k.nama_kementerian,a.nama_agensi,s.nama_suburusan," +
				      " f.no_fail,to_char(f.tarikh_daftar_fail,'dd/mm/yyyy') tarikh_daftar_fail,h.no_rujukan_kjp,h.no_rujukan_lain," +
				      " to_char(p.tarikh_surat,'dd/mm/yyyy') tarikh_surat,to_char(p.tarikh_terima,'dd/mm/yyyy') tarikh_terima,p.tujuan,p.id_permohonan,f.id_fail," +
				      " k.id_kementerian,a.id_agensi,n.id_negeri,H.NO_RUJUKAN_PTD,H.NO_RUJUKAN_PTG,F.TAJUK_FAIL " +
				      " ,H.ID_DAERAH" +
				      " FROM " +
				      " tblrujnegeri n,Tblpfdfail f,tblrujkementerian k,tblpermohonan p,"+
				      " tblhtppermohonan h,tblrujagensi a,tblrujsuburusan s where "+
				      " f.ID_NEGERI=n.ID_NEGERI and f.id_kementerian=k.id_kementerian "+
				      " and p.ID_FAIL=f.ID_FAIL and p.id_permohonan=h.ID_PERMOHONAN  "+
				      " and h.ID_AGENSI=a.id_agensi and f.ID_SUBURUSAN=s.ID_SUBURUSAN  "+
				      " and p.id_permohonan = '"+idpermohonan+"'";
				 mylog.info("getPermohonanInfoV1:sql="+sql);
				      ResultSet rs = stmt.executeQuery(sql);
				      Hashtable h = new Hashtable();

				      while (rs.next()) {
			    		  //h.put("idnegeri", Utils.isNull(rs.getString("id_negeri")));
			    		  if(rs.getString("ID_NEGERI")==null){
				    		  h.put("idnegeri","0");
				    	  }else{
				    		  h.put("idnegeri", rs.getString("ID_NEGERI"));	}
				    	  h.put("negeri", Utils.isNull(rs.getString("nama_negeri"))); 
				    	  h.put("idkementerian", Utils.isNull(rs.getString("id_kementerian")));
				    	  h.put("kementerian", Utils.isNull(rs.getString("nama_kementerian")));
			    		  h.put("idagensi", Utils.isNull(rs.getString("id_agensi")));
			    		  h.put("agensi", Utils.isNull(rs.getString("nama_agensi")));
			    		  h.put("suburusan", Utils.isNull(rs.getString("nama_suburusan")));
			    		  h.put("fail", Utils.isNull(rs.getString("no_fail")));
				    	  if(rs.getString("tarikh_daftar_fail")==null){
				    		  h.put("tdaftar",new Date());
				    	  }else{
				    		  h.put("tdaftar", rs.getString("tarikh_daftar_fail"));	}
			    		  h.put("rujukankjp", Utils.isNull(rs.getString("no_rujukan_kjp")));
			    		  h.put("rujukanlain", Utils.isNull(rs.getString("no_rujukan_lain")));
				    	  if(rs.getString("tarikh_surat")==null){
				    		  h.put("tsurat",new Date());
				    	  }else{
				    		  h.put("tsurat", rs.getString("tarikh_surat"));	}
				    	  if(rs.getString("tarikh_terima")==null){
				    		  h.put("rtterima",new Date());
				    	  }else{
				    		  h.put("rtterima", rs.getString("tarikh_terima"));	}
			    		  h.put("tujuan", Utils.isNull(rs.getString("tujuan")));
				    	  h.put("idpermohonan", rs.getString("ID_PERMOHONAN")==null ? "0" :rs.getString("ID_PERMOHONAN"));
				    	  h.put("idfail", rs.getString("ID_FAIL")==null ? "0" :rs.getString("ID_FAIL"));
			    		  h.put("noFailPTD", Utils.isNull(rs.getString("NO_RUJUKAN_PTD")));
			    		  h.put("noFailPTG", Utils.isNull(rs.getString("NO_RUJUKAN_PTG")));
				    	  h.put("idDaerah", rs.getString("ID_DAERAH")==null ? "0" :rs.getString("ID_DAERAH"));
			    		  h.put("tajukFail", Utils.isNull(rs.getString("TAJUK_FAIL")));


				      }
				      return h;
				    } finally {
				      if (db != null) db.close();
				    }
		 }

		  /* Created by : Mohamad Rosli 2010/05/1
		   * Tujuan	  : Data dari tblhtphakmilikurusan
		   * Pra syarat : Data tblhtphakmilikurusan dimasukkan terlebih dahulu
		   */
		  public Hashtable<String, String> getHakmilikUrusanV1(String idPermohonan,String idHakmilikUrusan)throws Exception {
			  SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
			  Db db = null;
			  String sql = "";
		      Hashtable<String, String> h = null;
			  try {
				  db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			 
			      r.add("distinct( u.Id_Hakmilikurusan)");
			      r.add("u.Id_Permohonan");
			      r.add("u.Id_Negeri");	      
			      r.add("RN.NAMA_NEGERI");
			      r.add("u.Id_Daerah");
			      r.add("RD.NAMA_DAERAH");
			      r.add("u.Id_Mukim");
			      r.add("RM.NAMA_MUKIM");
			      r.add("u.Id_Jenishakmilik");
			      r.add("h.Keterangan as HakKeterangan");
			      r.add("H.KOD_JENIS_HAKMILIK");
			      r.add("u.No_Hakmilik");
			      r.add("u.No_Lot");
			      r.add("u.Id_Lot");
			      r.add("lot.Keterangan as LotKeterangan");
			      r.add("u.Tarikh_Mula");
			      r.add("u.Tarikh_Luput");
			      r.add("u.Luas");
			      r.add("u.Id_Luas");
			      r.add("luas.Keterangan as LuasKeterangan");
			      r.add("u.No_Pelan");
			      r.add("u.Id_Jenisrizab");
			      r.add("u.Id_Kategori");
				  //2010/02/25
			      r.add("RK.nama_Kementerian");
			      r.add("RK.ID_KEMENTERIAN");
			      r.add("F.no_fail");
			      r.add("F.TAJUK_FAIL");
			      r.add("RA.NAMA_AGENSI");
			      r.add("RA.ID_AGENSI");
			      r.add("PP.NO_RUJUKAN_KJP");
			      r.add("PP.NO_RUJUKAN_LAIN");
			      r.add("PP.NO_RUJUKAN_PTD");
			      r.add("PP.NO_RUJUKAN_PTG");
		      
			      if(idPermohonan != "0"){	r.add("u.Id_Permohonan", r.unquote(idPermohonan));	}	
			  	  if(idHakmilikUrusan != "0"){	r.add(" u.Id_Hakmilikurusan",r.unquote(idHakmilikUrusan));	}

			  	  r.add("u.Id_Jenishakmilik",r.unquote("h.Id_Jenishakmilik"));
			      r.add("u.Id_Lot",r.unquote("lot.Id_Lot"));
			      r.add("u.Id_Luas",r.unquote("luas.Id_Luas"));
			      r.add("RN.ID_NEGERI",r.unquote("U.ID_NEGERI"));
			      r.add("RD.ID_DAERAH",r.unquote("U.ID_DAERAH"));
			      r.add("RM.ID_MUKIM",r.unquote("U.ID_MUKIM")); 
			      r.add("P.ID_FAIL",r.unquote("F.ID_FAIL")); 
			      r.add("PP.ID_PERMOHONAN",r.unquote("P.ID_PERMOHONAN")); 
			      r.add("F.ID_KEMENTERIAN",r.unquote("RK.ID_KEMENTERIAN"));
			      r.add("PP.ID_AGENSI",r.unquote("RA.ID_AGENSI"));
			      
			      sql = r.getSQLSelect("" +
			      		" TBLPFDFAIL F,TBLPERMOHONAN P,TBLHTPPERMOHONAN PP,TBLHTPHAKMILIKURUSAN U" +
			      		", TBLRUJJENISHAKMILIK H, TBLRUJLOT LOT,TBLRUJLUAS LUAS, TBLRUJJENISTANAH J " +
			      		" ,TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM,TBLRUJKEMENTERIAN RK, TBLRUJAGENSI RA" );
			      mylog.info("getHakmilikUrusan ::"+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      //Azam change to if (rs.next
			      //while (rs.next()) {
			      if (rs.next()) {
			    	  h = new Hashtable<String, String>();
			    	  	    	  
			    	  h.put("IdHakmilikurusan",rs.getString("Id_Hakmilikurusan"));
			    	  if(rs.getString("Id_Negeri") == null){
			    		  h.put("IdNegeri","");
			    	  }else{
			    		  h.put("IdNegeri",rs.getString("Id_Negeri"));
			    	  }
			    	  h.put("labelNegeri", rs.getString("Id_Negeri")+" - "+rs.getString("NAMA_NEGERI"));
			    	  h.put("namaNegeri", rs.getString("Id_Negeri")+" - "+rs.getString("NAMA_NEGERI"));
			    	  
			    	  if(rs.getString("Id_Daerah") == null){
			    		  h.put("IdDaerah","");
			    	  }else{
			    		  h.put("IdDaerah",rs.getString("Id_Daerah"));
			    	  }
			    	  h.put("labelDaerah", rs.getString("Id_Daerah")+" - "+rs.getString("NAMA_DAERAH"));
			    	  h.put("namaDaerah", rs.getString("Id_Daerah")+" - "+rs.getString("NAMA_DAERAH"));
			    	  h.put("idMukim", rs.getString("ID_MUKIM")==null ? "0" :rs.getString("ID_MUKIM"));
			    	  h.put("labelMukim", rs.getString("Id_Mukim")+" - "+rs.getString("NAMA_MUKIM"));
			    	  h.put("namaMukim", rs.getString("Id_Mukim")+" - "+rs.getString("NAMA_MUKIM"));
			    	  h.put("idJenisHakmilik", rs.getString("ID_JENISHAKMILIK")==null ? "0" :rs.getString("ID_JENISHAKMILIK"));
			    	  h.put("labelNohakmilik", rs.getString("KOD_JENIS_HAKMILIK")+" "+rs.getString("No_Hakmilik"));
			    	  if(rs.getString("HakKeterangan") == null){
			    		  h.put("HakKeterangan","");
			    	  }else{
			    		  h.put("HakKeterangan",rs.getString("HakKeterangan"));
			    	  }
			    	  h.put("jenisHakmilik", rs.getString("HAKKETERANGAN")+" "+rs.getString("HAKKETERANGAN"));
			    	  if(rs.getString("No_Hakmilik") == null){
			    		  h.put("NoHakmilik","");
			    	  }else{
			    		  h.put("NoHakmilik",rs.getString("No_Hakmilik"));
			    	  }
			    	  h.put("idLot", rs.getString("ID_LOT")==null ? "0" :rs.getString("ID_LOT"));
		    		  h.put("noLot",Utils.isNull(rs.getString("No_Lot")));
			    	  h.put("labelNolot", rs.getString("LotKeterangan")+" "+rs.getString("No_Lot"));
			    	  if(rs.getString("LotKeterangan") == null){
			    		  h.put("LotKeterangan","");
			    	  }else{
			    		  h.put("LotKeterangan",rs.getString("LotKeterangan"));
			    	  }
			    	  if(rs.getString("Tarikh_Mula") == null){
			    		  h.put("TarikhMula","");
			    	  }else{
			    		  h.put("TarikhMula",Format.format(rs.getDate("Tarikh_Mula")));
			    	  }
			    	  if(rs.getString("Tarikh_Luput") == null){
			    		  h.put("TarikhLuput","");
			    	  }else{
			    		  h.put("TarikhLuput",Format.format(rs.getDate("Tarikh_Luput")));
			    	  }
			    	  h.put("idLuas", rs.getString("ID_LUAS")==null ? "0" :rs.getString("ID_LUAS"));
		    		  h.put("luas",Utils.isNull(rs.getString("LUAS")));
		    		  h.put("luasKeterangan",Utils.isNull(rs.getString("LuasKeterangan")));
			    	  if(rs.getString("No_Pelan") == null){
			    		  h.put("NoPelan","");
			    	  }else{
			    		  h.put("NoPelan",rs.getString("No_Pelan"));
			    	  }
			    	  if(rs.getString("Id_Jenisrizab") == null){
			    		  h.put("IdRizab","");
			    	  }else{
			    		  h.put("IdRizab",rs.getString("Id_Jenisrizab"));
			    	  }
			    	  if(rs.getString("Id_Kategori") == null){
			    		  h.put("IdKategori","");
			    	  }else{
			    		  h.put("IdKategori",rs.getString("Id_Kategori"));
			    	  }
			    	  if(rs.getString("nama_Kementerian") == null){
			    		  h.put("namaKementerian","");
			    	  }else{
			    		  h.put("namaKementerian",rs.getString("nama_Kementerian"));
			    	  }	
			    	  h.put("noFailSeksyen", rs.getString("no_fail")==null ? "" :rs.getString("no_fail"));
			    	  h.put("tajukFail", rs.getString("TAJUK_FAIL")==null ? "" :rs.getString("TAJUK_FAIL"));
			    	  h.put("namaAgensi", rs.getString("NAMA_AGENSI")==null ? "" :rs.getString("NAMA_AGENSI"));
			    	  h.put("noFailKjp", rs.getString("NO_RUJUKAN_KJP")==null ? "" :rs.getString("NO_RUJUKAN_KJP"));
			    	  h.put("noFailPTG", rs.getString("NO_RUJUKAN_PTG")==null ? "" :rs.getString("NO_RUJUKAN_PTG"));
			    	  h.put("noFailPTD", rs.getString("NO_RUJUKAN_PTD")==null ? "" :rs.getString("NO_RUJUKAN_PTD"));
			    	  h.put("idKementerian", rs.getString("ID_KEMENTERIAN")==null ? "0" :rs.getString("ID_KEMENTERIAN"));
			    	  h.put("idAgensi", rs.getString("ID_AGENSI")==null ? "0" :rs.getString("ID_AGENSI"));
			    	  h.put("idPermohonan", rs.getString("ID_PERMOHONAN")==null ? "0" :rs.getString("ID_PERMOHONAN"));
			    	  
			      }
			      return h;
			    } finally {
			      if (db != null) db.close();
			    }
			  }
		  
		  public static Hashtable<String, String> getRizabUrusan(String idPermohonan,String idHakmilikUrusan)throws Exception {
			  SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
			  Db db = null;
			  String sql = "";
		      Hashtable<String, String> h = null;
			  try {
				  db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			 
			      r.add("DISTINCT( U.ID_HAKMILIKURUSAN)");
			      r.add("U.NO_WARTA");
			      r.add("U.ID_PERMOHONAN");
			      r.add("RD.ID_DAERAH");
			      r.add("RD.NAMA_DAERAH");
			      r.add("RM.ID_MUKIM");
			      r.add("RM.NAMA_MUKIM");
			      r.add("U.NO_LOT");
			      r.add("U.ID_LOT");
			      r.add("LOT.KETERANGAN AS LOTKETERANGAN");
			      r.add("U.TARIKH_MULA");
			      r.add("U.TARIKH_LUPUT");
			      r.add("U.LUAS");
			      r.add("U.ID_LUAS");
			      r.add("LUAS.KETERANGAN AS LUASKETERANGAN");
			      r.add("U.NO_PELAN");
			      r.add("PP.ID_DAERAH AS ID_DAERAHPERMOHONAN");
		      
			      if(idPermohonan != null){	r.add("U.ID_PERMOHONAN", idPermohonan);	}	
			  	  if(idHakmilikUrusan != null){	r.add(" U.ID_HAKMILIKURUSAN",idHakmilikUrusan);	}

			      r.add("U.ID_PERMOHONAN",r.unquote("P.ID_PERMOHONAN")); 
			      r.add("P.ID_PERMOHONAN",r.unquote("PP.ID_PERMOHONAN")); 
			      r.add("U.ID_MUKIM",r.unquote("RM.ID_MUKIM")); 
			      r.add("RM.ID_DAERAH",r.unquote("RD.ID_DAERAH"));
//			      r.add("RD.ID_NEGERI",r.unquote("RN.ID_NEGERI"));
			      r.add("U.ID_LOT",r.unquote("LOT.ID_LOT"));
			      r.add("U.ID_LUAS",r.unquote("LUAS.ID_LUAS"));
			      r.add("P.ID_FAIL",r.unquote("F.ID_FAIL")); 
			      
			      sql = r.getSQLSelect("" +
			      		"TBLPFDFAIL F,TBLPERMOHONAN P,TBLHTPPERMOHONAN PP,TBLHTPHAKMILIKURUSAN U" +
			      		", TBLRUJLOT LOT,TBLRUJLUAS LUAS " +
			      		", TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM " +
			    		"");
			      //,Tblrujkementerian RK, TBLRUJAGENSI RA" );
			      //sql +=" AND U.NO_WARTA IS NOT NULL ";
			      //mylog.info("getRizabUrusan ::sql="+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      int count = 0;
			      int bil = 1;
			      while (rs.next()) {
			    	  h = new Hashtable<String, String>();			    	  	    	  
			    	  h.put("bil",String.valueOf(bil));
			    	  h.put("IdHakmilikurusan",rs.getString("ID_HAKMILIKURUSAN"));
			    	  if(rs.getString("ID_MUKIM") == null){
			    		  h.put("IdMukim","0");
			    		  h.put("idMukim","0");
			    		  
			    	  }else{
			    		  h.put("IdMukim",rs.getString("ID_MUKIM"));
			    		  h.put("idMukim",rs.getString("ID_MUKIM"));
			    		  
			    	  }
			    	  h.put("labelMukim", rs.getString("ID_MUKIM")+" - "+rs.getString("NAMA_MUKIM"));
			    	  h.put("namaMukim", rs.getString("ID_MUKIM")+" - "+rs.getString("NAMA_MUKIM"));
			    	  if(rs.getString("NO_LOT") == null){
			    		  h.put("NoLot","");
			    	  }else{
			    		  h.put("NoLot",rs.getString("NO_LOT"));
			    	  }
			    	  h.put("labelNolot", rs.getString("LOTKETERANGAN")+" "+rs.getString("NO_LOT"));
			    	  if(rs.getString("Id_Lot") == null){
			    		  h.put("IdLot","0");
			    	  }else{
			    		  h.put("IdLot",rs.getString("Id_Lot"));
			    	  }
			    	  if(rs.getString("LotKeterangan") == null){
			    		  h.put("LotKeterangan","");
			    	  }else{
			    		  h.put("LotKeterangan",rs.getString("LOTKETERANGAN"));
			    	  }
			    	  if(rs.getString("Tarikh_Mula") == null){
			    		  h.put("TarikhMula","");
			    	  }else{
			    		  h.put("TarikhMula",Format.format(rs.getDate("Tarikh_Mula")));
			    	  }
			    	  if(rs.getString("Tarikh_Luput") == null){
			    		  h.put("TarikhLuput","");
			    	  }else{
			    		  h.put("TarikhLuput",Format.format(rs.getDate("Tarikh_Luput")));
			    	  }
			    	  if(rs.getString("Luas") == null){
			    		  h.put("Luas","");
			    	  }else{
			    		  h.put("Luas",rs.getString("Luas"));
			    	  }
			    	  //h.put("IdLuas",rs.getString("Id_Luas"));
			    	  if(rs.getString("ID_LUAS") == null){
			    		  h.put("IdLuas","0");
			    	  }else{
			    		  h.put("IdLuas",rs.getString("ID_LUAS"));
			    	  }
			    	  if(rs.getString("LUASKETERANGAN") == null){
			    		  h.put("LuasKeterangan","");
			    	  }else{
			    		  h.put("LuasKeterangan",rs.getString("LUASKETERANGAN"));
			    	  }
			    	  if(rs.getString("NO_PELAN") == null){
			    		  h.put("NoPelan","");
			    	  }else{
			    		  h.put("NoPelan",rs.getString("NO_PELAN"));
			    	  }
			    	  if(rs.getString("ID_DAERAHPERMOHONAN") == null){
			    		  h.put("IdDaerahPermohonan","0");
			    		  h.put("idDaerahPermohonan","0");
			    	  }else{
			    		  h.put("IdDaerahPermohonan",rs.getString("ID_DAERAHPERMOHONAN"));
			    		  h.put("idDaerahPermohonan",rs.getString("ID_DAERAHPERMOHONAN"));
			    	  }
			    	  if(rs.getString("Id_Daerah") == null){
			    		  h.put("IdDaerah","0");
			    		  h.put("idDaerah","0");
			    	  }else{
			    		  h.put("IdDaerah",rs.getString("ID_DAERAH"));
			    		  h.put("idDaerah",rs.getString("ID_DAERAH"));
			    	  }
			    	  bil++;
			    	  count++;
			    	  
			      }
			      if (count == 0){
			    	  h = new Hashtable<String, String>();
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
			    	  h.put("IdHakmilikurusan","");
		    		  h.put("IdMukim","0");
			    	  h.put("labelMukim", "");
			    	  h.put("namaMukim", "");
			    	  h.put("labelNolot","");
			    	  h.put("IdLot","1");
		    		  h.put("LotKeterangan","");
		    		  h.put("Luas","");
			    	  h.put("IdLuas","0");
		    		  h.put("LuasKeterangan","");
		    		  h.put("NoPelan","");
		    		  h.put("NoPelan","");
		    		  h.put("IdDaerahPermohonan","0");
		    		  h.put("IdDaerah","0");
				      mylog.info("getRizabUrusan ::count=0");
		      }

			    } finally {
			      if (db != null) db.close();
			    }
			      return h;
			  }

		// INSER TBLHTPHAKMILIK
		public String insertHTPRizab(Db db,Hashtable<String,String> data) throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date(); 
			String currentDate = sdf.format(date);
			String sql = "";
			String idHakmilik = "";

			try{
				idHakmilik = String.valueOf(DB.getNextID(db,"TBLHTPHAKMILIK_SEQ"));
				Statement stmt = db.getStatement();
					  SQLRenderer r = new SQLRenderer();
			    	  r.add("ID_HAKMILIK", r.unquote(idHakmilik));
			    	  r.add("ID_PERMOHONAN", data.get("idPermohonan"));
			    	  r.add("ID_NEGERI", data.get("socNegeriHR"));
			    	  r.add("ID_DAERAH", data.get("socDaerahHR"));
			    	  r.add("ID_MUKIM", data.get("socMukimHR"));
			    	  r.add("NO_WARTA", data.get("txtNoWarta"));
			    	  r.add("NO_FAIL", data.get("txtNoFail"));
			    	  r.add("NO_FAIL_KJP", data.get("txtFailKJP"));
			    	  r.add("NO_FAIL_PTG", data.get("txtFailPTG"));
			    	  r.add("NO_FAIL_PTD", data.get("txtFailPTD"));			    	  

			    	  //convert date before add
					  String tarikhTerima = (String)(data.get("txdTarikhTerima"));
					  String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
					  r.add("TARIKH_TERIMA",r.unquote(txdTarikhTerima));  	
					  //convert date before add
					  String tarikhDaftar = (String)data.get("txdTarikhWarta");
					  String txdTarikhDaftar = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";	  			  			  
			    	  r.add("TARIKH_WARTA", r.unquote(txdTarikhDaftar));
			    	  r.add("LOKASI", data.get("txtLokasi"));
			    	  r.add("ID_LUAS", data.get("socLuas"));
			    	  //r.add("LUAS", data.get("txtLuas"));
			    	  //24/09/2010
			    	  r.add("LUAS", data.get("txtLuasLama"));
			    	  r.add("ID_LUAS_BERSAMAAN", 2);
			    	  r.add("LUAS_BERSAMAAN", data.get("txtLuasBersamaan"));
			    	  r.add("NO_SYIT", data.get("txtNoSyit"));
			    	  r.add("NO_PELAN", data.get("txtNoPelan"));
			    	  r.add("NO_PU", data.get("txtNoPu"));
			    	  r.add("STATUS_SAH", data.get("socStatus"));
				      r.add("ID_KEMENTERIAN", data.get("idKementerian"));
			    	  r.add("ID_AGENSI", data.get("idAgensi"));
			    	  //kena ubah di controller dulu
			    	  r.add("ID_LOT", data.get("socLot"));
			    	  //azam change to txtNoLotR
			    	  //r.add("NO_LOT", data.get("txtNoLot"));
			    	  r.add("NO_LOT", data.get("txtNoLotR"));
			    	  
			    	  r.add("ID_JENISHAKMILIK", 0); //0=TIADA MAKLUMAT
			    	  //comment on 07/06/2010
			    	  //String ph = ""+data.get("socNegeriHR")+data.get("socDaerahHR")+data.get("socMukimHR")+data.get("txtNoWarta");
			    	  String ph = getKodNegeri(String.valueOf(data.get("socNegeriHR")));
			    	  ph += getKodDaerah(String.valueOf(data.get("socDaerahHR")));
			    	  ph += getKodMukim(String.valueOf(data.get("socMukimHR")));
			    	  ph += data.get("txtNoWarta");						    
			    	  r.add("PEGANGAN_HAKMILIK", ph);			    	  
			    	  r.add("CUKAI", r.unquote(String.valueOf("0.00")));
			    	  //r.add("TARAF_HAKMILIK", data.get("socTaraf")); P=Pajakan,F=Pegangan Bebas
			    	  //r.add("TEMPOH", data.get("txtTempoh"));
			    	  r.add("ID_KATEGORI", 1); //Kategori 1=TIADA MAKLUMAT	
			    	  r.add("ID_SUBKATEGORI",109); //Sub Kategori 109=TIADA MAKLUMAT					    	  
			    	  r.add("CUKAI_TERKINI", r.unquote(String.valueOf("0.00")));
			    	  //convert date before add
					  String tarikhKemaskini = currentDate;
					  String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
			    	  r.add("TARIKH_KEMASKINI", r.unquote(txdTarikhKemaskini));
			    	  r.add("ID_RIZAB", 0);//0=TIADA MAKLUMAT    
				      r.add("STATUS_RIZAB", "T");
			    	  r.add("TARIKH_MASUK", r.unquote(txdTarikhKemaskini));
					  r.add("ID_MASUK", r.unquote(String.valueOf(data.get("idMasuk"))));	
					  
					  r.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
					  r.add("CATATAN", data.get("txtCatatan"));
					  
					  sql = r.getSQLInsert("TBLHTPHAKMILIK");
					  mylog.info("sql insert TBLHTPHAKMILIK (TANAH RIZAB) :"+sql);
				      stmt.executeUpdate(sql);
				    } catch (Exception e) {
				    	throw new Exception(e);
				    }
//				    finally {
//				      if (db != null) db.close();
//				    }	
//			
//				    Db db2 = null;
//				    String sql2 = "";
				return idHakmilik;
		   
		}

			/* Created by : Mohamad Rosli 2010/05/06
			   * Tujuan	  : Data dari tblhtphakmilik
			   * Pra syarat : Static untuk urusan Permohonan-1,Rizab-10,Pembelian-2,Perletakhakan-5
			   */
			
			//PAPAR CARIAN HAKMILIK DAN RIZAB
			public Vector<Hashtable<String, String>> getHakmilikRizabMengikutPermohonan(String idPermohonan)throws Exception {
			    Db db = null;
			    Vector listHakmilikRizab = new Vector<Hashtable<String, String>>();
			    String sql = "";
			    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();	    
			      sql = "" +
			      	"SELECT RK.ID_KEMENTERIAN,RK.NAMA_KEMENTERIAN,RA.ID_AGENSI,RA.NAMA_AGENSI "+
			      	" ,TPH.NO_FAIL_PTD,TPH.NO_FAIL_PTG,TPH.NO_FAIL_KJP," +
			      	" CASE" +
			      	"	WHEN TPH.NO_FAIL IS NULL THEN F.NO_FAIL " +
			      	"   WHEN TPH.NO_FAIL IS NOT NULL THEN TPH.NO_FAIL " +
			      	"   ELSE 'TIADA MAKLUMAT' " +
			      	" END NO_FAIL " +
			      	" ,F.TAJUK_FAIL "+
			      	" ,RU.NAMA_URUSAN,TPH.NO_WARTA,TPH.TARIKH_WARTA,RL.ID_LOT,TPH.NO_LOT,RL.KETERANGAN LOTKETERANGAN   "+
			      	" ,TPH.TARIKH_TERIMA" +
			      	" ,TPH.KEGUNAAN_TANAH" +
			      	" ,TPH.NO_PELAN,TPH.NO_SYIT,TPH.NO_PU "+
			      	" ,LUAS.ID_LUAS,TPH.LUAS,TPH.ID_LUAS_BERSAMAAN,TPH.LUAS_BERSAMAAN "+
			      	" ,RM.ID_MUKIM,RM.NAMA_MUKIM,RD.ID_DAERAH,RD.NAMA_DAERAH,RN.ID_NEGERI,RN.NAMA_NEGERI "+
			      	" ,TPH.ID_HAKMILIK,TPH.ID_PERMOHONAN,TPH.LOKASI " +
					",( SELECT SSI.KETERANGAN FROM TBLHTPRUJSTATUSAH SSI "+
					" 	WHERE SSI.STATUS_SAH = TPH.STATUS_SAH "+
					" ) STATUS_SAH "+
			      	" , TO_CHAR(TPH.TARIKH_KEMASKINI,'dd/mm/yyyy') TARIKH_KEMASKINI" +
			      	" ,(SELECT USER_NAME FROM USERS WHERE USER_ID = TPH.ID_KEMASKINI) PEGAWAI_AKHIR" +
			      	" ,NVL(TPH.CATATAN,'') CATATAN" +
			      	" ,TPH.NO_HAKMILIK " +
			      	" ,(SELECT RJH.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH " +
			      	" 	WHERE RJH.ID_JENISHAKMILIK = TPH.ID_JENISHAKMILIK" +
			      	" ) JENIS_HAKMILIK "+
		            " ,TPH.NO_BANGUNAN,TPH.NO_TINGKAT,TPH.NO_PETAK " +
		            " ,TPH.ID_JENISRIZAB "+
			      	" FROM TBLHTPHAKMILIK TPH" +
			      	//",TBLHTPHAKMILIKAGENSI TPHA" +
			      	" ,TBLRUJAGENSI RA,TBLRUJKEMENTERIAN RK,TBLPERMOHONAN P "+
			      	" ,TBLPFDFAIL F,TBLRUJURUSAN RU,TBLRUJLOT RL" +
			      	//", TBLHTPHAKMILIKPERIHAL TPP" +
			      	" ,TBLRUJLUAS LUAS "+
			      	" ,TBLRUJMUKIM RM,TBLRUJDAERAH RD,TBLRUJNEGERI RN "+
					" WHERE tph.id_agensi = ra.id_agensi(+) "+
					" AND ra.id_kementerian = rk.id_kementerian(+) "+
					" AND tph.id_permohonan = p.id_permohonan "+
					" AND p.id_fail = f.id_fail "+
					" AND f.id_urusan = ru.id_urusan(+) "+
					" AND tph.id_lot = rl.id_lot(+) "+
					" AND tph.id_luas = luas.id_luas(+) "+
					" AND tph.id_mukim = rm.id_mukim(+) "+
					" AND rm.id_daerah = rd.id_daerah(+) "+
					" AND rd.id_negeri = rn.id_negeri(+) "+
					" AND tph.id_permohonan = '"+idPermohonan+"'";
			      	
			      	
			      	/*" WHERE " +
			      	//" TPH.ID_HAKMILIK=TPHA.ID_HAKMILIK AND "+
			      	//" TPHA.ID_AGENSI=RA.ID_AGENSI "+
			      	" TPH.ID_AGENSI=RA.ID_AGENSI "+
			      	" AND RA.ID_KEMENTERIAN=RK.ID_KEMENTERIAN "+
			      	//" AND TPHA.STATUS='Y' "+
			      	" AND TPH.ID_PERMOHONAN = P.ID_PERMOHONAN "+
			      	" AND P.ID_FAIL = F.ID_FAIL "+
			      	" AND F.ID_URUSAN=RU.ID_URUSAN "+
			      	" AND TPH.ID_LOT=RL.ID_LOT "+
			      	//" AND TPH.ID_HAKMILIK=TPP.ID_HAKMILIK(+) "+
			      	" AND TPH.ID_LUAS=LUAS.ID_LUAS "+
			      	" AND TPH.ID_MUKIM=RM.ID_MUKIM "+
			      	" AND RM.ID_DAERAH=RD.ID_DAERAH "+
			      	" AND RD.ID_NEGERI=RN.ID_NEGERI "+
			      	" AND TPH.ID_PERMOHONAN = '"+idPermohonan+"'"; */
			      	// " AND TPH.ID_PERMOHONAN='1610421'  "+
//					sql = sql +" ORDER BY C.NO_FAIL DESC ";
			      // Komen pada 11/07/2013
//			      	mylog.info("getHakmilikRizabMengikutPermohonan: "+sql);
//					sql = sql + " AND (TPH.STATUS_SAH IN (" +
//					"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
//					"))";
			      
			      System.out.println("sql : "+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      Hashtable<String, String> h = null;
			      int bil = 1;
			      int count = 0;
			      while (rs.next()) {
			    	  h = new Hashtable<String, String>();
			    	  h.put("bil", bil+".");
			    	  h.put("idKementerian", rs.getString("ID_KEMENTERIAN")==null ? "0" :rs.getString("ID_KEMENTERIAN"));
		    		  h.put("kementerian",Utils.isNull(rs.getString("NAMA_KEMENTERIAN")));
			    	  h.put("idAgensi", rs.getString("ID_AGENSI")==null ? "0" :rs.getString("ID_AGENSI"));
			    	  h.put("agensi", rs.getString("NAMA_AGENSI")==null ? "" :rs.getString("NAMA_AGENSI"));
			    	  h.put("noFailPTD", Utils.isNull(rs.getString("NO_FAIL_PTD")));
		    		  h.put("noFailPTG", Utils.isNull(rs.getString("NO_FAIL_PTG")));
			    	  h.put("noFailKJP", rs.getString("NO_FAIL_KJP")==null ? "" :rs.getString("NO_FAIL_KJP"));
		    		  h.put("noFail",Utils.isNull(rs.getString("NO_FAIL")));
			    	  h.put("tajukFail", rs.getString("TAJUK_FAIL")==null ? "" :rs.getString("TAJUK_FAIL"));
			    	  h.put("namaUrusan", rs.getString("NAMA_URUSAN")== null?"":rs.getString("NAMA_URUSAN"));
			    	  h.put("noWarta", rs.getString("NO_WARTA")== null?"":rs.getString("NO_WARTA"));
			    	  h.put("tarikhWarta",rs.getDate("TARIKH_WARTA")== null? "":sdf.format(rs.getDate("TARIKH_WARTA")));
			    	  h.put("tarikhTerima",rs.getDate("TARIKH_TERIMA")== null? "":sdf.format(rs.getDate("TARIKH_TERIMA")));
			    	  //h.put("kodLot", rs.getString("JENIS_LOT")== null?"":rs.getString("JENIS_LOT"));
			    	  h.put("idLot", rs.getString("ID_LOT")==null ? "0" :rs.getString("ID_LOT"));
			    	  h.put("noLot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
			    	  h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH")== null?"":rs.getString("KEGUNAAN_TANAH").toUpperCase());
			    	  h.put("statusSah", rs.getString("STATUS_SAH")== null?"":rs.getString("STATUS_SAH"));
		    		  h.put("lokasi",Utils.isNull(rs.getString("LOKASI")));

			    	  h.put("idHakmilik",Utils.isNull(rs.getString("ID_HAKMILIK")));
			    	  //h.put("kodJenis", rs.getString("JENIS_HAKMILIK")== null?"":rs.getString("JENIS_HAKMILIK"));
			    	  h.put("noHakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
			    	  //h.put("statusRizab", rs.getString("STATUS_RIZAB")== null? "":rs.getString("STATUS_RIZAB"));
			    	  h.put("idPermohonan",rs.getString("ID_PERMOHONAN")== null? "":rs.getString("ID_PERMOHONAN"));
		    		  
			    	  h.put("idMukim", rs.getString("ID_MUKIM")==null ? "0" :rs.getString("ID_MUKIM"));
			    	  h.put("namaMukim", rs.getString("Id_Mukim")+" - "+rs.getString("NAMA_MUKIM"));
			    	  h.put("labelMukim", rs.getString("Id_Mukim")+" - "+rs.getString("NAMA_MUKIM"));
			    	  if(rs.getString("Id_Daerah") == null){
			    		  h.put("idDaerah","0");
			    	  }else{
			    		  h.put("idDaerah",rs.getString("Id_Daerah"));
			    	  }
			    	  h.put("labelDaerah", rs.getString("Id_Daerah")+" - "+rs.getString("NAMA_DAERAH"));
			    	  h.put("namaDaerah", rs.getString("Id_Daerah")+" - "+rs.getString("NAMA_DAERAH"));
			    	  if(rs.getString("Id_Negeri") == null){
			    		  h.put("idNegeri","0");
			    	  }else{
			    		  h.put("idNegeri",rs.getString("Id_Negeri"));
			    	  }
			    	  h.put("labelNegeri", rs.getString("Id_Negeri")+" - "+rs.getString("NAMA_NEGERI"));
			    	  h.put("namaNegeri", rs.getString("Id_Negeri")+" - "+rs.getString("NAMA_NEGERI"));
		    		  h.put("noLot",Utils.isNull(rs.getString("No_Lot")));
			    	  h.put("labelNolot", rs.getString("LotKeterangan")+" "+rs.getString("No_Lot"));
		    		  h.put("LotKeterangan",Utils.isNull(rs.getString("LotKeterangan")));
		    		  h.put("kodLot",Utils.isNull(rs.getString("LotKeterangan")));
			       	  h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN")==null ? "0" :rs.getString("ID_LUAS_BERSAMAAN"));
		    		  h.put("luas",Utils.isNull(rs.getString("LUAS")));
		    		  h.put("luasBersamaan",Utils.isNull(rs.getString("LUAS_BERSAMAAN")));
			       	  h.put("idLuasLama", rs.getString("ID_LUAS")==null ? "0" :rs.getString("ID_LUAS"));
		    		  h.put("luasLama",Utils.isNull(rs.getString("LUAS")));
		    		  //h.put("luasKeterangan",Utils.isNull(rs.getString("LuasKeterangan")));
		    		  h.put("noPelan",Utils.isNull(rs.getString("NO_PELAN")));
		    		  h.put("noSyit",Utils.isNull(rs.getString("NO_SYIT")));
		    		  h.put("noPu",Utils.isNull(rs.getString("NO_PU")));
		    		  h.put("pegawaiAkhir",Utils.isNull(rs.getString("PEGAWAI_AKHIR")));
		    		  h.put("tarikhKemaskini",Utils.isNull(rs.getString("TARIKH_KEMASKINI")));
		    		  h.put("catatan",Utils.isNull(rs.getString("CATATAN")));
		
		    		  // 28/05/2013
			    	  h.put("kodJenis", rs.getString("JENIS_HAKMILIK")== null?"":rs.getString("JENIS_HAKMILIK"));
			    	  h.put("noStrata", rs.getString("NO_BANGUNAN")== null?"":"["+rs.getString("NO_BANGUNAN")+","+rs.getString("NO_TINGKAT")+","+rs.getString("NO_PETAK")+"]");
			       	  h.put("idJenisRizab", rs.getString("ID_JENISRIZAB")==null ? "0" :rs.getString("ID_JENISRIZAB"));

		    		  listHakmilikRizab.addElement(h);
			    	  bil++;
			    	  count++;
			    	  
			      }
				}catch(Exception e){
					e.printStackTrace();
					//throw new Exception("MASALAH CAPAIAN DATA, SILA SEMAK METHOD getHakmilikRizabMengikutPermohonan(ekptg.model.htp.rekod.FrmRekodUtilData)"+e.getMessage());
					throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] MASALAH CAPAIAN DATA, SILA SEMAK METHOD getHakmilikRizabMengikutPermohonan(ekptg.model.htp.rekod.FrmRekodUtilData) "+e.getMessage()));
			    } finally {
			      if (db != null) db.close();
			    }		
			    return listHakmilikRizab;
			}		
	  
			//Get Detail HAKMILIK
			public Hashtable<String, String> getDetailHakmilik(String idHakmilik)throws Exception {
				SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
				Db db = null;
				String sql = "";
			    Hashtable<String, String> h = null;
				try {
					db = new Db();
				    Statement stmt = db.getStatement();
//				    sql = "SELECT A.ID_HAKMILIK,A.NO_FAIL_PTG,A.NO_FAIL_PTD " +
//				    	" ,A.ID_NEGERI,A.ID_DAERAH,A.ID_MUKIM,A.ID_JENISHAKMILIK"+
//						" ,A.NO_HAKMILIK,A.NO_BANGUNAN,A.NO_TINGKAT,A.NO_PETAK"+
//						" ,A.ID_LOT,A.NO_LOT" +
//						" ,TO_CHAR(A.TARIKH_TERIMA,'DD/MM/YYYY') TARIKH_TERIMA" +
//						" ,TO_CHAR(A.TARIKH_DAFTAR,'DD/MM/YYYY') TARIKH_DAFTAR" +
//						" ,A.TARAF_HAKMILIK,A.TEMPOH" +
//						" ,TO_CHAR(A.TARIKH_LUPUT,'DD/MM/YYYY') TARIKH_LUPUT,NVL(A.CUKAI,0) CUKAI"+
//						" ,A.LOKASI" +
//						" ,A.KEGUNAAN_TANAH" +
//						" ,A.ID_LUAS,NVL(A.LUAS,0) LUAS,A.ID_LUAS_BERSAMAAN,NVL(A.LUAS_BERSAMAAN,0) LUAS_BERSAMAAN "+
//						" ,A.STATUS_RIZAB,A.NO_RIZAB NO_WARTA" +
//						" ,TO_CHAR(A.TARIKH_WARTA,'DD/MM/YYYY') TARIKH_WARTA"+
//						" ,A.ID_RIZAB,A.KAWASAN_RIZAB,A.ID_KATEGORI"+
//						" ,A.SYARAT,A.SEKATAN,A.NO_PELAN,A.NO_SYIT,A.NO_PU "+
//						" ,A.ID_KEMENTERIAN,RK.NAMA_KEMENTERIAN,A.ID_AGENSI " +
//						" ,A.CATATAN " +
//						" ,A.TARIKH_KEMASKINI "+
//						" FROM TBLHTPHAKMILIK A,TBLRUJKEMENTERIAN RK " +
//						" WHERE " +
//						" A.ID_KEMENTERIAN=RK.ID_KEMENTERIAN AND " +
//						" A.ID_HAKMILIK = '"+idHakmilik+"'";
				    sql = getIHakmilik().getSQLHakmilik(idHakmilik);
				    mylog.info("getDetailHakmilik:sql siniiiiiiiiiiiiiiiiiiiiiiiiii="+sql);
				    ResultSet rs = stmt.executeQuery(sql);
				    //Azam change to if (rs.next
				    //while (rs.next()) {
				    if (rs.next()) {
				    	  h = new Hashtable<String, String>();
				    	  h.put("no_fail_ptg", Utils.isNull(rs.getString("no_fail_ptg")));
				    	  h.put("no_fail_ptd", Utils.isNull(rs.getString("no_fail_ptd")));
				    	  h.put("id_negeri", Utils.isNull(rs.getString("id_negeri")));
				    	  h.put("id_daerah", Utils.isNull(rs.getString("id_daerah")));
				    	  h.put("id_mukim", Utils.isNull(rs.getString("id_mukim")));
				    	  h.put("idJenisHakmilik", Utils.isNull(rs.getString("id_jenishakmilik")));
				    	  h.put("no_hakmilik", Utils.isNull(rs.getString("no_hakmilik")));
				    	  h.put("no_bangunan", Utils.isNull(rs.getString("no_bangunan")));
				    	  h.put("no_tingkat", Utils.isNull(rs.getString("no_tingkat")));
				    	  h.put("no_petak", Utils.isNull(rs.getString("no_petak")));
				    	  h.put("idLot", Utils.isNull(rs.getString("id_lot")));
				    	  h.put("noLot", Utils.isNull(rs.getString("no_lot")));
				    	  h.put("tarikh_terima", Utils.isNull(rs.getString("tarikh_terima")));
				    	  h.put("tarikh_daftar", Utils.isNull(rs.getString("tarikh_daftar")));
				    	  h.put("taraf_hakmilik", Utils.isNull(rs.getString("taraf_hakmilik")));
				    	  h.put("tempoh", Utils.isNull(rs.getString("tempoh")));
				    	  h.put("tarikh_luput", Utils.isNull(rs.getString("tarikh_luput")));
				    	  h.put("cukai", Utils.isNull(rs.getString("cukai")));
				    	  //h.put("cukai", Utils.isNull(String.valueOf(rs.getDouble("cukai"))));
				    	  h.put("lokasi", Utils.isNull(rs.getString("lokasi")));
				    	  h.put("kegunaan_tanah", Utils.isNull(rs.getString("kegunaan_tanah")));
				    	  
				       	  h.put("idLuas", Utils.isNull(rs.getString("ID_LUAS")));
				       	  h.put("luas_lama", Utils.isNull(rs.getString("LUAS")));
				       	  
				       	  h.put("luas", Utils.isNull(rs.getString("LUAS_BERSAMAAN")));			       	  
				       	  
				       	  h.put("status_rizab", Utils.isNull(rs.getString("status_rizab")));
				       	  //h.put("no_warta", Utils.isNull(rs.getString("no_warta")));
				       	  h.put("no_warta", Utils.isNull(rs.getString("no_rizab")));
				       	  h.put("tarikh_warta", Utils.isNull(rs.getString("tarikh_warta")));
				       	  h.put("id_rizab", Utils.isNull(rs.getString("id_rizab")));
				       	  h.put("kawasan_rizab", Utils.isNull(rs.getString("kawasan_rizab")));
				      	
				       	  h.put("id_kategori", Utils.isNull(rs.getString("id_kategori")));
				       	  h.put("syarat", Utils.isNull(rs.getString("syarat")));
				       	  h.put("sekatan", Utils.isNull(rs.getString("sekatan")));
				       	  h.put("no_pelan", Utils.isNull(rs.getString("no_pelan")));
				       	  h.put("no_syit", Utils.isNull(rs.getString("no_syit")));
				       	  h.put("no_pu", Utils.isNull(rs.getString("no_pu")));
				       	  h.put("idkementerian", Utils.isNull(rs.getString("id_kementerian")));
				       	  h.put("namakementerian", Utils.isNull(rs.getString("nama_kementerian")));
				       	  h.put("idagensi", Utils.isNull(rs.getString("id_agensi")));	      						      	  
				       	  h.put("catatan", Utils.isNull(rs.getString("CATATAN")));	      						      	  

				      }
				      return h;
				    } finally {
				      if (db != null) db.close();
				    }
				  }

			  public Hashtable<String, String> getDetailHakmilikByIdPermohonan(String idPermohonan)throws Exception {
				  SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
				  Db db = null;
				  String sql = "";
			      Hashtable<String, String> h = null;
				  try {
					  db = new Db();
				      Statement stmt = db.getStatement();
//				      sql = "SELECT A.ID_HAKMILIK,A.NO_FAIL_PTG,A.NO_FAIL_PTD " +
//				      		" ,A.ID_NEGERI,A.ID_DAERAH,A.ID_MUKIM,A.ID_JENISHAKMILIK "+
//							" ,A.NO_HAKMILIK,A.NO_BANGUNAN,A.NO_TINGKAT,A.NO_PETAK "+
//							" ,A.ID_LOT,A.NO_LOT " +
//							" ,TO_CHAR(A.TARIKH_TERIMA,'DD/MM/YYYY') TARIKH_TERIMA " +
//							" ,TO_CHAR(A.TARIKH_DAFTAR,'DD/MM/YYYY') TARIKH_DAFTAR " +
//							" ,A.TARAF_HAKMILIK,A.TEMPOH" +
//							" ,TO_CHAR(A.TARIKH_LUPUT,'DD/MM/YYYY') TARIKH_LUPUT,A.CUKAI "+
//							" ,A.LOKASI,A.KEGUNAAN_TANAH " +
//							" ,A.ID_LUAS,NVL(A.LUAS,0) LUAS,A.ID_LUAS_BERSAMAAN,NVL(A.LUAS_BERSAMAAN,0) LUAS_BERSAMAAN "+
//							" ,A.STATUS_RIZAB,A.NO_RIZAB NO_WARTA " +
//							" ,TO_CHAR(A.TARIKH_WARTA,'DD/MM/YYYY') TARIKH_WARTA "+
//							" ,A.ID_RIZAB,A.KAWASAN_RIZAB,A.ID_KATEGORI "+
//							" ,A.SYARAT,A.SEKATAN,A.NO_PELAN,A.NO_SYIT,A.NO_PU " +
//							" ,A.ID_KEMENTERIAN,A.ID_AGENSI " +
//							" ,A.CATATAN " +
//							" ,A.TARIKH_KEMASKINI "+
//							" FROM TBLHTPHAKMILIK A  "+
//							" WHERE A.ID_PERMOHONAN = '"+idPermohonan+"' " +
//							" AND ROWNUM<=1 ORDER BY A.TARIKH_MASUK";
				      sql = getIHakmilik().getSQLHakmilikPermohonan(idPermohonan);
				      mylog.info(sql);
				      ResultSet rs = stmt.executeQuery(sql);
				      //Azam change to if (rs.next
				      //while (rs.next()) {
				      if (rs.next()) {
				    	  h = new Hashtable<String, String>();
				    	  h.put("no_fail_ptg", Utils.isNull(rs.getString("no_fail_ptg")));
				    	  h.put("no_fail_ptd", Utils.isNull(rs.getString("no_fail_ptd")));
				    	  h.put("id_negeri", Utils.isNull(rs.getString("id_negeri")));
				    	  h.put("id_daerah", Utils.isNull(rs.getString("id_daerah")));
				    	  h.put("id_mukim", Utils.isNull(rs.getString("id_mukim")));
				    	  h.put("idJenisHakmilik", Utils.isNull(rs.getString("id_jenishakmilik")));
				    	  h.put("no_hakmilik", Utils.isNull(rs.getString("no_hakmilik")));
				    	  h.put("no_bangunan", Utils.isNull(rs.getString("no_bangunan")));
				    	  h.put("no_tingkat", Utils.isNull(rs.getString("no_tingkat")));
				    	  h.put("no_petak", Utils.isNull(rs.getString("no_petak")));
				    	  h.put("idLot", Utils.isNull(rs.getString("id_lot")));
				    	  h.put("noLot", Utils.isNull(rs.getString("no_lot")));
				    	  h.put("tarikh_terima", Utils.isNull(rs.getString("tarikh_terima")));
				    	  h.put("tarikh_daftar", Utils.isNull(rs.getString("tarikh_daftar")));
				    	  h.put("taraf_hakmilik", Utils.isNull(rs.getString("taraf_hakmilik")));
				    	  h.put("tempoh", Utils.isNull(rs.getString("tempoh")));
				    	  h.put("tarikh_luput", Utils.isNull(rs.getString("tarikh_luput")));
				    	  h.put("cukai", Utils.isNull(rs.getString("cukai")));
				    	  h.put("lokasi", Utils.isNull(rs.getString("lokasi")));
				    	  h.put("kegunaan_tanah", Utils.isNull(rs.getString("kegunaan_tanah")));
				    	  
				       	  h.put("idLuas", Utils.isNull(rs.getString("ID_LUAS")));
				       	  h.put("luas_lama", Utils.isNull(rs.getString("LUAS")));
				       	  
				       	  h.put("luas", Utils.isNull(rs.getString("LUAS_BERSAMAAN")));			       	  
		
				       	  
				       	  h.put("status_rizab", Utils.isNull(rs.getString("status_rizab")));
				       	  h.put("no_warta", Utils.isNull(rs.getString("no_warta")));
				       	  h.put("tarikh_warta", Utils.isNull(rs.getString("tarikh_warta")));
				       	  h.put("id_rizab", Utils.isNull(rs.getString("id_rizab")));
				       	  h.put("kawasan_rizab", Utils.isNull(rs.getString("kawasan_rizab")));
				      	
				       	  h.put("id_kategori", Utils.isNull(rs.getString("id_kategori")));
				       	  h.put("syarat", Utils.isNull(rs.getString("syarat")));
				       	  h.put("sekatan", Utils.isNull(rs.getString("sekatan")));
				       	  h.put("no_pelan", Utils.isNull(rs.getString("no_pelan")));
				       	  h.put("no_syit", Utils.isNull(rs.getString("no_syit")));
				       	  h.put("no_pu", Utils.isNull(rs.getString("no_pu")));
				       	  h.put("idkementerian", Utils.isNull(rs.getString("id_kementerian")));
				       	  h.put("idagensi", Utils.isNull(rs.getString("id_agensi")));
				       	  h.put("catatan", Utils.isNull(rs.getString("CATATAN")));
				      						      	  

				      }
				      return h;
				    } finally {
				      if (db != null) db.close();
				    }
				  }

		// KEMASKINI RIZAB MENGIKUT ID
		public void kemaskiniRizabMengikutId(Hashtable data) throws Exception {
			Db dbHakmilik = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date(); 
			String currentDate = sdf.format(date);
			String sql = "";
			
			try{
				dbHakmilik = new Db();
				Statement stmtHakmilik = dbHakmilik.getStatement();
				SQLRenderer rHakmilik = new SQLRenderer();
				rHakmilik.update("ID_HAKMILIK", data.get("idHakmilik"));
				rHakmilik.add("NO_FAIL_PTD", data.get("txtFailPTD"));	  
				rHakmilik.add("NO_FAIL_PTG", data.get("txtFailPTG"));	  
				rHakmilik.add("ID_NEGERI", data.get("socNegeriHR"));
				rHakmilik.add("ID_DAERAH", data.get("socDaerahHR"));
				rHakmilik.add("ID_MUKIM", data.get("socMukimHR"));
				rHakmilik.add("NO_WARTA", data.get("txtNoWarta"));
				rHakmilik.add("ID_LOT",data.get("socLot"));
				rHakmilik.add("NO_LOT", data.get("txtNoLotR"));
				// Commnet on 07/06/2010
				//String ph = ""+data.get("socNegeriHR")+data.get("socDaerahHR")+data.get("socMukimHR")+data.get("txtNoWarta");
				String ph = getKodNegeri(String.valueOf(data.get("socNegeriHR")));
				ph += getKodDaerah(String.valueOf(data.get("socDaerahHR")));
				ph += getKodMukim(String.valueOf(data.get("socMukimHR")));
				//comment on 07/06/2010
				ph += data.get("txtNoWarta");						    
				rHakmilik.add("PEGANGAN_HAKMILIK", ph);			    	  
				  
				//convert date before add
				String tarikhTerima = data.get("txdTarikhTerima").toString();
				String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
				rHakmilik.add("TARIKH_TERIMA",rHakmilik.unquote(txdTarikhTerima));  						  
				String tarikhWarta = data.get("txdTarikhWarta").toString();
				String txdTarikhWarta = "to_date('" + tarikhWarta + "','dd/MM/yyyy')";	    	  
				rHakmilik.add("TARIKH_WARTA",rHakmilik.unquote(txdTarikhWarta));  	
				rHakmilik.add("LOKASI", data.get("txtLokasi"));
				//txtKegunaanTanah 
				rHakmilik.add("ID_LUAS",data.get("socLuas"));
				rHakmilik.add("LUAS", data.get("txtLuasLama"));
				rHakmilik.add("ID_LUAS_BERSAMAAN",2);
				rHakmilik.add("LUAS_BERSAMAAN", data.get("txtLuasBersamaan"));
				rHakmilik.add("NO_PELAN", data.get("txtNoPelan"));
				rHakmilik.add("NO_SYIT", data.get("txtNoSyit"));
				rHakmilik.add("NO_PU", data.get("txtNoPu"));
				rHakmilik.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
				//rHakmilik.add("NO_FAIL_JOPA", data.get("txtNoFailJopa"));	  
				//rHakmilik.add("STATUS_SAH",data.get("socStatus"));
				//convert date before add
				String tarikhKemaskini = currentDate;
				String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
				rHakmilik.add("TARIKH_KEMASKINI", rHakmilik.unquote("sysdate"));			  
				rHakmilik.add("ID_KEMASKINI", rHakmilik.unquote(String.valueOf(data.get("idKemaskini"))));			  
				rHakmilik.add("CATATAN", data.get("txtCatatan"));
		  
				sql = rHakmilik.getSQLUpdate("TBLHTPHAKMILIK");
				//mylog.info("getSQLUpdate(\"TBLHTPHAKMILIK\"):"+sql);
				stmtHakmilik.executeUpdate(sql);
				
			}	catch(Exception e){
				e.printStackTrace();
				throw new Exception("MASALAH KEMASKINI DATA, SILA SEMAK METHOD kemaskiniRizabMengikutId(ekptg.model.htp.rekod.FrmRekodUtilData)");

			}finally {
				if (dbHakmilik != null) dbHakmilik.close();
			}
	  
		}
				
				public String getKodJenisHakmilik(String idJenisHakmilik) throws Exception {
					String returnValue = "00";
					Db db = null;
					String sql = "";
					try {
						db = new Db();
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						r.add("kod_Jenis_Hakmilik");
						r.add("id_Jenishakmilik",idJenisHakmilik);
						sql = r.getSQLSelect("Tblrujjenishakmilik");
						ResultSet rs = stmt.executeQuery(sql);
						while (rs.next()) {
							returnValue = rs.getString("kod_Jenis_Hakmilik");
						}
					} finally {
						if (db != null)	db.close();
					}
					return returnValue;
				}
				
				public String getKodNegeri(String idNegeri) throws Exception {
					String returnValue = "00";
					Db db = null;
					String sql = "";
					try {
							db = new Db();
							Statement stmt = db.getStatement();
							SQLRenderer r = new SQLRenderer();
							r.add("KOD_NEGERI");
							r.add("ID_NEGERI",idNegeri);
							sql = r.getSQLSelect("TBLRUJNEGERI");

							ResultSet rs = stmt.executeQuery(sql);
							while (rs.next()) {
									returnValue = rs.getString("KOD_NEGERI");
							}
						} finally {
							if (db != null)	db.close();
					}
					return returnValue;
				}

				public String getKodDaerah(String idDaerah) throws Exception {
					String returnValue = "00";
					Db db = null;
					String sql = "";
					try {
							db = new Db();
							Statement stmt = db.getStatement();
							SQLRenderer r = new SQLRenderer();
							r.add("KOD_DAERAH");
							r.add("ID_DAERAH",idDaerah);
							sql = r.getSQLSelect("TBLRUJDAERAH");

							ResultSet rs = stmt.executeQuery(sql);
							while (rs.next()) {
									returnValue = rs.getString("KOD_DAERAH");
							}
						} finally {
							if (db != null)	db.close();
					}
					return returnValue;
				}	
				
				public String getKodMukim(String idMukim) throws Exception {
					String returnValue = "00";
					Db db = null;
					String sql = "";
					try {
							db = new Db();
							Statement stmt = db.getStatement();
							SQLRenderer r = new SQLRenderer();
							r.add("KOD_MUKIM");
							r.add("ID_MUKIM",idMukim);
							sql = r.getSQLSelect("TBLRUJMUKIM");
							ResultSet rs = stmt.executeQuery(sql);
							while (rs.next()) {
									returnValue = rs.getString("KOD_MUKIM");
							}
						} finally {
							if (db != null)	db.close();
					}
					return returnValue;
				}
				
		public static Vector<Hashtable<String, Comparable>> getLaporanMengikutUrusan
			(String suburusan, String level, String jlaporan,String template) throws Exception {
			String key = "DBgetLaporan";
			//Element cachedObject = myCache.get(key);
			//if (cachedObject != null) {
			//	return (Vector)cachedObject.getObjectValue();
			//} else {
						
				  Db db = null;
				    String sql = "";
				    try {
				      db = new Db();
				      SQLRenderer r = new SQLRenderer();
				      r.add("rjd.keterangan");
				      r.add("rdu.module_id");
				      r.add("rdu.peringkat");
				      r.add("rdu.template");
				      r.add("rdu.id_suburusan");
				      r.add("RU.NAMA_URUSAN");
				      r.add("RDU.ATURAN");
					  r.add("rjd.ID_JENISDOKUMEN",r.unquote("rdu.ID_JENISDOKUMEN"));
					  r.add("rdu.ID_SUBURUSAN",r.unquote("rsu.ID_SUBURUSAN"));
					  r.add("RSU.ID_URUSAN",r.unquote("RU.ID_URUSAN"));
					  r.add("rdu.DOKUMEN",r.unquote("'"+jlaporan+"'"));
					  r.add("LOWER(rdu.template)","%"+template+"%","like");
					  //r.add("rdu.template","'HTPRekodRingkasanRizabKementerian','HTPRekodRingkasanMilikKementerian')"," not in(");
					  String sqlAdd = "";
					  if(level!=null){
						  if(level.equals("unit")){
							  sqlAdd = " AND ( SUBSTR(rdu.peringkat,0,4)='"+level+"' OR SUBSTR(rdu.peringkat,0,6)='daerah' )";
						  }else if(level.equals("daerah"))
							  r.add("SUBSTR(rdu.peringkat,0,6)",level);
						  else if(level.equals("negeri"))
							  r.add("SUBSTR(rdu.peringkat,0,6)",level);

					  }		           
				      sql = r.getSQLSelect("tblrujdokumenurl rdu,tblrujjenisdokumen rjd,tblrujurusan ru,tblrujsuburusan rsu");
				      if(!suburusan.equals("TIADA")){
				    		  sql += " AND RDU.ID_SUBURUSAN in ("+suburusan+")"; 
				      }
				      sql += " AND rdu.template NOT IN ('HTPRekodRingkasanRizabKementerian','HTPRekodRingkasanMilikKementerian') ";
				      sql += sqlAdd + " ORDER BY RU.NAMA_URUSAN,RDU.ATURAN";
				      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
				      Statement stmt = db.getStatement();
				      mylog.info("getLaporanMengikutUrusan("+suburusan+","+level+"):sql::"+sql);
				      ResultSet rs = stmt.executeQuery(sql);
				      Hashtable<String, Comparable> h;
				      while (rs.next()) {
				    	  h = new Hashtable<String, Comparable>();
				    	  h.put("idmodule",rs.getString("module_id")); 
				    	  h.put("keterangan",rs.getString("keterangan"));
				    	  h.put("peringkat",rs.getString("peringkat"));
				    	  h.put("template",rs.getString("template"));
				    	  h.put("idsuburusan",rs.getString("id_suburusan"));
				    	  h.put("namaurusan",rs.getString("NAMA_URUSAN"));
				    	  v.addElement(h);
				      }
				      return v;
				    } finally {
				      if (db != null) db.close();
			}
				//	}
		}		
				
		private ITanah getIHakmilik(){
			if (iHakmilik==null){
				iHakmilik=new TanahBean();
			}
			return iHakmilik;
		}	
		
		private IHtp getIHTP(){
			if(iHTP== null)
				iHTP = new HtpBean();
			return iHTP;
		}		  
		
		
}
