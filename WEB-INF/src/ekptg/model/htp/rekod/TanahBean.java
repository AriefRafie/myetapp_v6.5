package ekptg.model.htp.rekod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class TanahBean implements ITanah{

	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.TanahBean.class);
	private HakMilik hakmilik = null;
 	private IHtp iHTP = null;
	private PfdFail pfdFail = null;
 	private Permohonan permohonan = null;
 	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public String getLuas(String jenisLuas,String luas
		,String luas2,String luas3,String luas4
		,String luas5,String luas6){
		String luasAsal = ""; 
		if(jenisLuas.equals("1")){
			luasAsal = luas+"KM";
		}else if(jenisLuas.equals("2")){
			luasAsal = luas+"H";
		 }else if(jenisLuas.equals("3")){
			luasAsal = luas+"M";
		 }else if(jenisLuas.equals("4")){
			 luasAsal = luas2+"E"+luas3+"R"+luas4+"P";
		 }else if(jenisLuas.equals("5")){
			luasAsal = luas+"K";
		 }else if(jenisLuas.equals("7")){
			 luasAsal = luas5+"E"+luas6+"D";
		 }else if(jenisLuas.equals("8")){
			 luasAsal = luas2+"R"+luas3+"J"+luas4+"K";
		 }
		return luasAsal;
		
	}

	public String getSQLHakmilik(String idHakmilik){
		String sql = "";
		sql = "SELECT F.NO_FAIL "+
		" ,TPH.ID_HAKMILIK " +
		" ,NVL(RL.KETERANGAN,'0') KOD_LOT "+
		" ,TPH.ID_LOT,TPH.NO_LOT "+
		" ,TPH.ID_NEGERI,TPH.ID_DAERAH,TPH.ID_MUKIM "+
		" ,RN.NAMA_NEGERI, RD.NAMA_DAERAH, RM.NAMA_MUKIM "+
		" ,TPH.LOKASI "+
		" ,TPH.CATATAN ,TPH.KEGUNAAN_TANAH "+
		" ,TPH.ID_KATEGORI,TPH.SYARAT,TPH.SEKATAN "+
		" ,TPH.NO_PELAN,TPH.NO_SYIT,TPH.NO_PU "+
		" ,TPH.NO_FAIL_PTG,TPH.NO_FAIL_PTD,TPH.NO_FAIL_KJP "+
		" ,TPH.ID_LUAS,NVL(TPH.LUAS,0) LUAS,TPH.ID_LUAS_BERSAMAAN,NVL(TPH.LUAS_BERSAMAAN,0) LUAS_BERSAMAAN "+
		" ,TO_CHAR(TPH.TARIKH_TERIMA,'DD/MM/YYYY') TARIKH_TERIMA "+
		" ,TPH.NO_WARTA,TO_CHAR(TPH.TARIKH_WARTA,'DD/MM/YYYY') TARIKH_WARTA "+
		" ,TPH.ID_JENISHAKMILIK,UPPER(TPH.NO_HAKMILIK) NO_HAKMILIK "+
		" ,TPH.NO_BANGUNAN,TPH.NO_TINGKAT,TPH.NO_PETAK "+
		" ,RJH.KETERANGAN,RJH.KOD_JENIS_HAKMILIK KOD_KETERANGAN "+
		" ,TO_CHAR(TPH.TARIKH_DAFTAR,'DD/MM/YYYY') TARIKH_DAFTAR "+
		" ,TPH.HAKMILIK_ASAL "+
		" ,TPH.TARAF_HAKMILIK,TPH.TEMPOH "+
		" ,TO_CHAR(TPH.TARIKH_LUPUT,'DD/MM/YYYY') TARIKH_LUPUT "+
		" ,NVL(TPH.CUKAI,0) CUKAI "+
		" ,TPH.ID_RIZAB,TPH.STATUS_RIZAB,TPH.NO_RIZAB,TPH.KAWASAN_RIZAB, TPH.TARIKH_RIZAB "+
		" ,TPH.TARIKH_KEMASKINI "+
		" ,(SELECT USER_NAME FROM USERS WHERE USER_ID=TPH.ID_KEMASKINI) PEGAWAI_AKHIR "+
		" ,CASE "+
		"	WHEN P.TUJUAN IS NULL THEN F.TAJUK_FAIL "+
		" 	WHEN P.TUJUAN IS NOT NULL THEN P.TUJUAN "+
		" 	ELSE '' "+
		" END TUJUAN "+
		" ,RU.NAMA_URUSAN "+
		" ,RK.NAMA_KEMENTERIAN,RA.NAMA_AGENSI "+
		" ,NVL(TPH.ID_KEMENTERIAN,0) ID_KEMENTERIAN,TPH.ID_AGENSI " +
		" ,P.ID_PERMOHONAN, F.ID_FAIL "+
//		" -- "+
		" FROM "+
//		" -- "+
		" TBLHTPHAKMILIK TPH "+
		" ,TBLRUJDAERAH RD "+
		" ,TBLRUJMUKIM RM "+
		" ,TBLRUJNEGERI RN "+
		" ,TBLPERMOHONAN P "+
		" ,TBLPFDFAIL F "+
		" ,TBLHTPHAKMILIKAGENSI TPHA,TBLRUJKEMENTERIANMAPPING RKME "+
//		" --,TBLRUJAGENSIMAPPING RAME "+
		" ,TBLRUJKEMENTERIAN RK,TBLRUJAGENSI RA " +
		" ,TBLRUJLOT RL " +
		" ,TBLRUJJENISHAKMILIK RJH " +
		" ,TBLRUJURUSAN RU "+
//		" -- "+
		" WHERE P.ID_PERMOHONAN = TPH.ID_PERMOHONAN "+
		" AND P.ID_FAIL = F.ID_FAIL "+
		" AND TPH.ID_MUKIM = RM.ID_MUKIM "+
		" AND RM.ID_DAERAH = RD.ID_DAERAH "+
		" AND RD.ID_NEGERI = RN.ID_NEGERI "+
//		" -- "+
		" AND TPH.ID_HAKMILIK = TPHA.ID_HAKMILIK AND TPHA.STATUS = 'Y' "+
		" AND TPHA.ID_KEMENTERIAN = RKME.ID_KEMENTERIANLAMA AND RKME.STATUS = 'A' "+
		" AND TPH.ID_KEMENTERIAN = RK.ID_KEMENTERIAN "+
		" AND TPHA.ID_AGENSI = RA.ID_AGENSI "+
		" AND TPH.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK "+
		" AND TPH.ID_LOT = RL.ID_LOT " +
		" AND F.ID_URUSAN = RU.ID_URUSAN "+
		" AND TPH.ID_HAKMILIK ='"+ idHakmilik +"'" +
		"";
		return sql;  
		
	}
	
	public String getSQLHakmilikPermohonan(String idPermohonan){
		String sql = "";
		sql = "SELECT F.NO_FAIL "+
		" ,TPH.ID_HAKMILIK "+
		" ,TPH.NO_FAIL_PTG,TPH.NO_FAIL_PTD,TPH.NO_FAIL_KJP "+
		" ,UPPER(TPH.NO_HAKMILIK) NO_HAKMILIK,TPH.NO_WARTA "+
		" ,TPH.NO_BANGUNAN,TPH.NO_TINGKAT,TPH.NO_PETAK "+
		" ,TPH.ID_LOT,TPH.NO_LOT "+
		" ,TPH.HAKMILIK_ASAL,TPH.CATATAN "+
		" ,TPH.KEGUNAAN_TANAH "+
		" ,TPH.ID_RIZAB "+
		" ,TPH.ID_NEGERI,TPH.ID_DAERAH,TPH.ID_MUKIM,TPH.ID_JENISHAKMILIK "+
		" ,RN.NAMA_NEGERI, RD.NAMA_DAERAH, RM.NAMA_MUKIM "+
		" ,TO_CHAR(TPH.TARIKH_TERIMA,'DD/MM/YYYY') TARIKH_TERIMA "+
		" ,TO_CHAR(TPH.TARIKH_DAFTAR,'DD/MM/YYYY') TARIKH_DAFTAR "+
		" ,TPH.TARAF_HAKMILIK,TPH.TEMPOH "+
		" ,TO_CHAR(TPH.TARIKH_LUPUT,'DD/MM/YYYY') TARIKH_LUPUT "+
		" ,NVL(TPH.CUKAI,0) CUKAI "+
		" ,TPH.LOKASI  "+
		" ,TPH.ID_LUAS,NVL(TPH.LUAS,0) LUAS,TPH.ID_LUAS_BERSAMAAN,NVL(TPH.LUAS_BERSAMAAN,0) LUAS_BERSAMAAN  "+
		" ,TPH.STATUS_RIZAB,TPH.NO_RIZAB "+
		" ,TO_CHAR(TPH.TARIKH_WARTA,'DD/MM/YYYY') TARIKH_WARTA,TPH.KAWASAN_RIZAB "+
		" ,TPH.ID_KATEGORI,TPH.SYARAT,TPH.SEKATAN "+
		" ,TPH.NO_PELAN,TPH.NO_SYIT,TPH.NO_PU "+
		" ,TPH.TARIKH_KEMASKINI "+
		" ,(SELECT USER_NAME FROM USERS WHERE USER_ID=TPH.ID_KEMASKINI) PEGAWAI_AKHIR "+
//		" ,P.TUJUAN "+
		" ,CASE "+
		" WHEN P.TUJUAN IS NULL THEN F.TAJUK_FAIL "+
		" WHEN P.TUJUAN IS NOT NULL THEN P.TUJUAN "+
		" ELSE '' "+
		" END TUJUAN "+
		" ,(    SELECT RJHI.KETERANGAN FROM TBLRUJJENISHAKMILIK RJHI  "+
		"     WHERE RJHI.ID_JENISHAKMILIK = TPH.ID_JENISHAKMILIK "+
		" ) KETERANGAN "+
		" ,(     SELECT RUI.NAMA_URUSAN  "+
		"       FROM TBLRUJURUSAN RUI,TBLPFDFAIL FI "+
		"     WHERE FI.ID_URUSAN = RUI.ID_URUSAN "+
		"    AND FI.ID_FAIL = F.ID_FAIL "+
		" ) NAMA_URUSAN "+
		" ,RK.NAMA_KEMENTERIAN,RA.NAMA_AGENSI "+
		" ,NVL(RK.ID_KEMENTERIAN,0) ID_KEMENTERIAN,RA.ID_AGENSI "+
//		" -- "+
		" FROM "+
//		" -- "+
		" TBLHTPHAKMILIK TPH "+
		" ,TBLRUJDAERAH RD "+
		" ,TBLRUJMUKIM RM "+
		" ,TBLRUJNEGERI RN "+
		" ,TBLPERMOHONAN P "+
		" ,TBLPFDFAIL F "+
		" ,TBLHTPHAKMILIKAGENSI TPHA,TBLRUJKEMENTERIANMAPPING RKME "+
//		" --,TBLRUJAGENSIMAPPING RAME "+
		" ,TBLRUJKEMENTERIAN RK,TBLRUJAGENSI RA "+
//		" -- "+
		" WHERE P.ID_PERMOHONAN = TPH.ID_PERMOHONAN "+
		" AND P.ID_FAIL = F.ID_FAIL "+
		" AND TPH.ID_MUKIM = RM.ID_MUKIM "+
		" AND RM.ID_DAERAH = RD.ID_DAERAH "+
		" AND RD.ID_NEGERI = RN.ID_NEGERI "+
//		" -- "+
		" AND TPH.ID_HAKMILIK = TPHA.ID_HAKMILIK AND TPHA.STATUS = 'Y' "+
		" AND TPHA.ID_KEMENTERIAN = RKME.ID_KEMENTERIANLAMA AND RKME.STATUS = 'A' "+
		" AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN "+
		" AND TPHA.ID_AGENSI = RA.ID_AGENSI "+
//		" --AND TPHA.ID_AGENSI = RAME.ID_AGENSILAMA AND RAME.STATUS = 'A' "+
//		" --AND RAME.ID_AGENSIBARU = RA.ID_AGENSI "+
//		" AND TPH.ID_HAKMILIK ='"+ idHakmilik +"'";
		" AND TPH.ID_PERMOHONAN = '"+idPermohonan+"' " +
		" AND ROWNUM<=1 ORDER BY TPH.TARIKH_MASUK";
		return sql;
		
	}

	
	/** PAPAR MAKLUMAT FAIL (MASTER) BY ID
	* Dibuat oleh rosli (06/04/2012), tambah field ID_KEMENTERIAN
	* Diambil fungsi dari ekptg.model.htp.FrmRekodPendaftaranHakmilikRizabData 
	*/
	public Vector<Hashtable<String,String>> getPaparMaklumatFailById(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";
		Vector<Hashtable<String,String>> listMaklumatFail = null;
		try {
			db = new Db();
			listMaklumatFail = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();			   					
		    sql = getSQLHakmilik(idHakmilik);
			myLog.info("getPaparMaklumatFailById:sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idHakmilik", rs.getString("ID_HAKMILIK")==null ? "" :rs.getString("ID_HAKMILIK"));
				h.put("namaKementerian", rs.getString("NAMA_KEMENTERIAN")==null ? "" :rs.getString("NAMA_KEMENTERIAN"));
				h.put("noFailSeksyen", rs.getString("NO_FAIL")==null ? "" :rs.getString("NO_FAIL"));
				h.put("noFailPtg", rs.getString("NO_FAIL_PTG")==null ? "TIADA" :rs.getString("NO_FAIL_PTG"));
				h.put("noFailPtd", rs.getString("NO_FAIL_PTD")==null ? "TIADA" :rs.getString("NO_FAIL_PTD"));
				h.put("tajukFail", rs.getString("TUJUAN")==null ? "" :rs.getString("TUJUAN"));
				h.put("namaNegeri", rs.getString("NAMA_NEGERI")==null ? "" :rs.getString("NAMA_NEGERI"));
				h.put("namaDaerah", rs.getString("NAMA_DAERAH")==null ? "" :rs.getString("NAMA_DAERAH"));
				h.put("jenisHakmilik", rs.getString("KETERANGAN")==null ? "" :rs.getString("KETERANGAN").toUpperCase());
				h.put("kodJenisHakmilik", rs.getString("KOD_KETERANGAN")==null ? "" :rs.getString("KOD_KETERANGAN").toUpperCase());
				h.put("namaMukim", rs.getString("NAMA_MUKIM")==null ? "" :rs.getString("NAMA_MUKIM"));	
				h.put("namaAgensi", rs.getString("NAMA_AGENSI")==null ? "" :rs.getString("NAMA_AGENSI"));
				h.put("noFailKjp", rs.getString("NO_FAIL_KJP")==null ? "TIADA":rs.getString("NO_FAIL_KJP"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK")==null ? "" :rs.getString("NO_HAKMILIK"));
				h.put("noBangunan", rs.getString("NO_BANGUNAN")==null ? "TIADA" :rs.getString("NO_BANGUNAN"));
				h.put("noTingkat", rs.getString("NO_TINGKAT")==null ? "TIADA" :rs.getString("NO_TINGKAT"));
				h.put("noPetak", rs.getString("NO_PETAK")==null ? "TIADA" :rs.getString("NO_PETAK"));
				h.put("kodLot", rs.getString("KOD_LOT")==null ? "" :rs.getString("KOD_LOT"));
				h.put("noLot", rs.getString("NO_LOT")==null ? "" :rs.getString("NO_LOT"));
				h.put("noWarta", rs.getString("NO_WARTA")==null ? "" :rs.getString("NO_WARTA"));
				h.put("caraPerolehan", rs.getString("NAMA_URUSAN")==null ? "" :rs.getString("NAMA_URUSAN"));
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN")==null ? "" :rs.getString("ID_KEMENTERIAN"));
				h.put("hakmilikAsal", rs.getString("HAKMILIK_ASAL")==null ? "":rs.getString("HAKMILIK_ASAL"));
				h.put("catatan", rs.getString("CATATAN")==null ? "":rs.getString("CATATAN"));
				h.put("pegawaiAkhir",Utils.isNull(rs.getString("PEGAWAI_AKHIR")));
				h.put("kegunaanTanah",Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
				h.put("idJenisRizab",Utils.isNull(rs.getString("ID_RIZAB")));
		    	  if(rs.getString("NO_HAKMILIK")!= null){
		    		  h.put("jenisTanah","M");
		    	  }else{
		    		  h.put("jenisTanah","R");
		    	  }
		    	  if(rs.getString("NO_HAKMILIK")!= null && rs.getString("NO_WARTA")!= null){
		    		  h.put("jenisTanah","X");
		    	  }

		    	//fix bug.21112014. syaz
		    	h.put("tarikh_rizab", rs.getDate("TARIKH_RIZAB")==null?"":sdf.format(rs.getDate("TARIKH_RIZAB")));  	    	  
		    	h.put("idPermohonan", rs.getString("ID_PERMOHONAN"));  	    	  
				listMaklumatFail.addElement(h);

			}
			
		} finally {
			if (db != null)
			db.close();
			
		}
		return listMaklumatFail;
		
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
	
	@Override
	public Vector<Hashtable<String,String>> getSenaraiHakmilikSambungan(String noHakmilikAsal
		,String idNegeri,String idDaerah,String idMukim) throws Exception {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Vector<Hashtable<String,String>> listSenaraiFail = new Vector<Hashtable<String,String>>();
				Statement stmt = db.getStatement();			
				sql = "SELECT TPH.ID_HAKMILIK,RJH.KOD_JENIS_HAKMILIK, TPH.NO_HAKMILIK, "+
					  "	(SELECT ID_HAKMILIK " +
					  " FROM TBLHTPHAKMILIK " +
					  "	WHERE LTRIM(NO_HAKMILIK,0) LIKE '%"+noHakmilikAsal+"%'" +
					  "	  AND ID_NEGERI =  TPH.ID_NEGERI "+ 
					  "	  AND ID_DAERAH =  TPH.ID_DAERAH "+     
					  "	  AND ID_MUKIM = TPH.ID_MUKIM "+ 
					  "	  AND ID_JENISHAKMILIK = TPH.ID_JENISHAKMILIK "+
					  " ) AS ID_HAKMILIK_ASAL "+
					  " ,NVL(( SELECT KETERANGAN FROM TBLHTPRUJSTATUSAH WHERE STATUS_SAH = TPH.STATUS_SAH "+
					  " ),'TIADA MAKLUMAT') STATUS_SAH "+
					  "	FROM TBLHTPHAKMILIK TPH,TBLRUJJENISHAKMILIK RJH"+
					  " WHERE " +
					  "	TPH.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK " +
					  " AND TPH.ID_NEGERI= '"+idNegeri+"'"+
					  " AND TPH.ID_DAERAH= '"+idDaerah+"'"+			  
					  " AND TPH.ID_MUKIM= '"+idMukim+"'"+
					  " AND TPH.HAKMILIK_ASAL LIKE '%"+noHakmilikAsal+"%' ";					
				myLog.info("getSenaraiHakmilikSambungan :sql=" +sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable<String,String> h;
			    int bil = 1;
			    //int count = 0;
				
			    while (rs.next()) {
					h = new Hashtable<String,String> ();
					h.put("bil", bil+".");
					h.put("idHakmilikAsal", rs.getString("ID_HAKMILIK_ASAL")==null ? "" :rs.getString("ID_HAKMILIK_ASAL"));
					h.put("idHakmilikBaru", rs.getString("ID_HAKMILIK")==null ? "" :rs.getString("ID_HAKMILIK"));
					h.put("kodJenisBaru", rs.getString("KOD_JENIS_HAKMILIK")==null ? "" :rs.getString("KOD_JENIS_HAKMILIK"));
					h.put("noHakmilikBaru", rs.getString("NO_HAKMILIK")==null ? "" :rs.getString("NO_HAKMILIK"));			
					h.put("statusSah", rs.getString("STATUS_SAH")==null ? "" :rs.getString("STATUS_SAH"));			
					listSenaraiFail.addElement(h);
			    	bil++;
			    	//count++;
				
			    } 		
			    /*
				if(count==0){
					stmt = db.getStatement();			
					sql = "SELECT TPH.ID_HAKMILIK,RJH.KOD_JENIS_HAKMILIK, TPH.NO_HAKMILIK, "+
						  "	(SELECT ID_HAKMILIK " +
						  "	FROM TBLHTPHAKMILIK " +
						  "	WHERE LTRIM(NO_HAKMILIK,0) LIKE '%"+noHakmilikAsal+"%'" +
						  "	  AND ID_NEGERI =  TPH.ID_NEGERI "+ 
						  "	  AND ID_DAERAH =  TPH.ID_DAERAH "+     
						 // "	  --AND ID_MUKIM = TPH.ID_MUKIM "+ 
						  "	  AND ID_JENISHAKMILIK = TPH.ID_JENISHAKMILIK "+
						  " ) AS ID_HAKMILIK_ASAL "+
						  " ,NVL(( SELECT KETERANGAN FROM TBLHTPRUJSTATUSAH WHERE STATUS_SAH = TPH.STATUS_SAH "+
						  " ),'TIADA MAKLUMAT') STATUS_SAH "+
						  "	FROM TBLHTPHAKMILIK TPH,TBLRUJJENISHAKMILIK RJH "+
						  " WHERE " +
						  "	TPH.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK " +
						  " AND TPH.ID_NEGERI= '"+idNegeri+"'"+
						  " AND TPH.ID_DAERAH= '"+idDaerah+"'"+			  
						  //" AND TPH.ID_MUKIM= '"+idMukim+"'"+
						  " AND TPH.HAKMILIK_ASAL LIKE '%"+noHakmilikAsal+"%' ";					
					myLog.info("getSenaraiHakmilikSambungan (Gagal): sql" +sql);
					rs = stmt.executeQuery(sql);
				    while (rs.next()) {
						h = new Hashtable();
						h.put("bil", bil+".");
						h.put("idHakmilikAsal", rs.getString("ID_HAKMILIK_ASAL")==null ? "" :rs.getString("ID_HAKMILIK_ASAL"));
						h.put("idHakmilikBaru", rs.getString("ID_HAKMILIK")==null ? "" :rs.getString("ID_HAKMILIK"));
						h.put("kodJenisBaru", rs.getString("KOD_JENIS_HAKMILIK")==null ? "" :rs.getString("KOD_JENIS_HAKMILIK"));
						h.put("noHakmilikBaru", rs.getString("NO_HAKMILIK")==null ? "" :rs.getString("NO_HAKMILIK"));			
						h.put("statusSah", rs.getString("STATUS_SAH")==null ? "" :rs.getString("STATUS_SAH"));			
						listSenaraiFail.addElement(h);
				    	bil++;
				    	count++;
					
				    } 		
				
				}*/
				return listSenaraiFail;
			
			} finally {
				if (db != null)
				db.close();
			}
			
		}	
	@Override
	public void kemaskiniHakmilikTambahSambungan(Hashtable data) throws Exception {
		Connection conn = null;
	    Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(); 
		String currentDate = sdf.format(date);
	    String sql = "";
	    try{
			  db = new Db();
			  conn = db.getConnection();
			  conn.setAutoCommit(false);
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
		      
			  tambahHakmilikBaruById(data,conn);
			  
			  //KEMASKINI TBLHTPHAKMILIK			  
	    	  r.update("ID_HAKMILIK", data.get("idHakmilik"));
	    	  r.add("ID_NEGERI", data.get("socNegeriHR"));
	    	  r.add("ID_DAERAH", data.get("socDaerahHR"));
	    	  r.add("ID_MUKIM", data.get("socMukimHR"));
	    	  r.add("ID_JENISHAKMILIK", data.get("socJenisHakmilikHR"));
	    	  r.add("NO_HAKMILIK", data.get("txtNoHakmilik"));
	    	  //r.add("ID_LOT", data.get("socLotHR"));
	    	  //r.add("NO_LOT", data.get("txtNoLot"));
	    	  //convert date before add
			  //String tarikhTerima = (String)(data.get("txdTarikhTerima"));
			  //String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
			  //r.add("TARIKH_TERIMA",r.unquote(txdTarikhTerima));  	
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
	    	  r.add("LUAS", data.get("txtLuasLama"));
	    	  
	    	  r.add("TARAF_HAKMILIK", data.get("socTaraf"));
	    	  r.add("ID_KATEGORI", data.get("socKategori"));
	    	  r.add("NO_PELAN", data.get("txtNoPelan"));
	    	  r.add("TEMPOH", data.get("txtTempoh"));
	    	  r.add("NO_FAIL_JOPA", data.get("txtNoFailJopa"));	    	  
	    	  //r.add("HAKMILIK_ASAL", data.get("txtHakmilikAsal"));
	    	  //r.add("CUKAI_TERKINI", data.get("txtCukaiTerkini"));
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
	    	  //2010/11/04
	    	  //r.add("NO_WARTA", data.get("txtNoWarta"));
	    	  r.add("NO_RIZAB", data.get("txtNoWarta"));
	    	  r.add("SEKATAN", data.get("txtSekatan"));	  
	    	  r.add("SYARAT", data.get("txtSyarat"));	  
	    	  r.add("HAKMILIK_BERIKUT", getKodJenisHakmilik(String.valueOf(data.get("socJenisHakmilikBaru")))+ data.get("txtHakmilikBerikut"));
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
			
			  sql = r.getSQLUpdate("TBLHTPHAKMILIK");
			  myLog.info("sql update TBLHTPHAKMILIK:"+sql);
		      stmt.executeUpdate(sql);
		      		      
		      conn.commit();

	    	}catch(Exception e){
	    		conn.rollback();
	    		e.printStackTrace();
	    		
	    	}finally {
		      if (db != null) db.close();
		      if (conn != null) conn.close();
		      
		    }	
	    	
    }
	private void tambahHakmilikBaruById(Hashtable data,Connection conn) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(); 
		String currentDate = sdf.format(date);
		String idHakmilikAsal = String.valueOf(data.get("idHakmilik"));
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			long idHakmilikBaru = DB.getNextID(db,"TBLHTPHAKMILIK_SEQ"); 
			
			//GET DATA LAMA FROM TBLHTPHAKMILIK
			sql = "SELECT A.ID_PERMOHONAN, A.PEGANGAN_HAKMILIK, A.ID_SUBKATEGORI " +
				" ,A.NO_HAKMILIK,LTRIM(A.NO_HAKMILIK,0) NO_HAKMILIKX0,A.ID_LOT " +
		      	" ,A.ID_LUAS_BERSAMAAN,A.LUAS,A.ID_NEGERI,NVL(A.STATUS_RIZAB,'T') STATUS_RIZAB " +
		      	" ,A.ID_DAERAH, A.ID_MUKIM, A.ID_JENISHAKMILIK, A.ID_LOT, A.ID_LUAS " +
		      	" ,A.ID_KATEGORI" +
		      	" ,A.ID_AGENSI, A.ID_KEMENTERIAN,A.KEGUNAAN_TANAH, A.CATATAN " +
		      	" ,( SELECT RJH.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH " +
		      	" WHERE RJH.ID_JENISHAKMILIK = A.ID_JENISHAKMILIK) KOD_JENIS_HAKMILIK " + //modified on 2010/08/16
		      	" FROM TBLHTPHAKMILIK A " +
		      	" WHERE  " +
		      	" A.ID_HAKMILIK = '"+idHakmilikAsal +"'";
			myLog.info("get data lama "+sql);
			ResultSet rs = stmt.executeQuery(sql);			
			if (rs.next()) {
				//CREATE REKOD BARU 			    
			    r.add("ID_HAKMILIK",idHakmilikBaru);
			    r.add("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN"));
			    //r.add("PEGANGAN_HAKMILIK",FrmUtilData.getKodNegeri(rs.getString("ID_NEGERI"))+FrmUtilData.getKodDaerah(rs.getString("ID_DAERAH"))+FrmUtilData.getKodMukim(rs.getString("ID_MUKIM"))+data.get("txtKodSocJenisHakmilikBaru")+String.format("%08d",Integer.parseInt(String.valueOf(data.get("txtHakmilikBerikut")))));
			    r.add("PEGANGAN_HAKMILIK",FrmUtilData.getKodNegeri(rs.getString("ID_NEGERI"))+FrmUtilData.getKodDaerah(rs.getString("ID_DAERAH"))+FrmUtilData.getKodMukim(rs.getString("ID_MUKIM"))+data.get("txtKodSocJenisHakmilikBaru")+data.get("txtHakmilikBerikut"));
			    r.add("ID_SUBKATEGORI",rs.getString("ID_SUBKATEGORI"));
			    r.add("ID_NEGERI",rs.getString("ID_NEGERI"));
			    r.add("ID_DAERAH",rs.getString("ID_DAERAH"));
			    r.add("ID_MUKIM",rs.getString("ID_MUKIM"));
			    r.add("ID_JENISHAKMILIK",(String)data.get("socJenisHakmilikBaru"));
			    // By rosli add format 00000000,diguna semula pada 07/06/2010
			    r.add("NO_HAKMILIK", data.get("txtHakmilikBerikut"));
			    // tidak boleh format, kerana ada data yang dimasukkan adalah abjad 
			    //r.add("NO_HAKMILIK", String.format("%08d",Integer.parseInt(String.valueOf(data.get("txtHakmilikBerikut")))));
			    r.add("NO_LOT",(String)data.get("txtNoLot"));
			    //convert date before add
				String tarikhTerima = (String)(data.get("txdTarikhTerima"));
				String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
				r.add("TARIKH_TERIMA",r.unquote(txdTarikhTerima));  	
				//convert date before add
				String tarikhDaftar = (String)data.get("txdTarikhDaftar");
				String txdTarikhDaftar = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";	  			  			  
			    r.add("TARIKH_DAFTAR", r.unquote(txdTarikhDaftar));
			    r.add("CUKAI", data.get("txtCukaiTahun"));
			    r.add("LOKASI", data.get("txtLokasi"));
			   
			    r.add("TARAF_HAKMILIK", data.get("socTaraf")); 
			    // 28/02/2011
			    //r.add("ID_LOT", rs.getString("ID_LOT"));
			    r.add("ID_LOT", data.get("socLotHR"));
			    r.add("ID_KATEGORI", rs.getString("ID_KATEGORI"));
			    r.add("NO_PELAN", data.get("txtNoPelan"));
			    
			    r.add("ID_LUAS_BERSAMAAN",2);
			    r.add("LUAS_BERSAMAAN", data.get("txtLuas"));
		    	  
			    r.add("ID_LUAS",data.get("socLuas"));
			    r.add("LUAS", data.get("txtLuasLama"));
			    
			    r.add("TEMPOH", data.get("txtTempoh"));
			    r.add("NO_FAIL_JOPA", data.get("txtNoFailJopa"));	    	  
			    r.add("CUKAI_TERKINI", data.get("txtCukaiTerkini"));
			    //convert date before add
				String tarikhLuput = (String)data.get("txdTarikhLuput");
				String txdTarikhLuput = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";	  			  			 
			    r.add("TARIKH_LUPUT", r.unquote(txdTarikhLuput));
			    r.add("NO_PU", data.get("txtNoPu"));
			    //convert date before add
				String tarikhWarta = (String)data.get("txdTarikhWarta");
				String txdTarikhWarta = "to_date('" + tarikhWarta + "','dd/MM/yyyy')";		    	  
			    r.add("TARIKH_WARTA", r.unquote(txdTarikhWarta));
			    r.add("KAWASAN_RIZAB", data.get("txtKawasanRizab"));
			    r.add("NO_SYIT", data.get("txtNoSyit"));
			    //2010/11/04
			    //r.add("NO_WARTA", data.get("txtNoWarta"));
			    r.add("NO_RIZAB", data.get("txtNoWarta"));
			    r.add("SEKATAN", data.get("txtSekatan"));	  
			    r.add("SYARAT", data.get("txtSyarat"));	  
			    r.add("HAKMILIK_ASAL", rs.getString("KOD_JENIS_HAKMILIK")+rs.getString("NO_HAKMILIKX0"));
			    //r.add("STATUS_SAH", "");
			    // by Rosli 03/05/2010
			    // r.add("STATUS_SAH", data.get("socStatus"));
			    // Azam change on 20/05/2010
			    //Default to D instead of ambil dari form, 
			    //sebab dalam form ialah S (Batal Sambungan
			    //Need to check if got any problems
			    r.add("STATUS_SAH","D");
			    //convert date before add
				String tarikhKemaskini = currentDate;
				String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
			    r.add("TARIKH_MASUK", r.unquote(txdTarikhKemaskini));
			    r.add("ID_MASUK",data.get("idKemaskini"));
			    r.add("ID_RIZAB", data.get("socJenisRizab"));    
			    r.add("STATUS_RIZAB", Utils.isNull(rs.getString("STATUS_RIZAB")));
			    // Tambahan field untuk simpan by Rosli 16/08/2010
			    //A.ID_AGENSI, A.ID_KEMENTERIAN,A.KEGUNAAN_TANAH, A.CATATAN 
			    r.add("ID_AGENSI", Utils.isNull(rs.getString("ID_AGENSI")));
			    r.add("ID_KEMENTERIAN", Utils.isNull(rs.getString("ID_KEMENTERIAN")));
			    r.add("KEGUNAAN_TANAH", Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
			    r.add("CATATAN", Utils.isNull(rs.getString("CATATAN")));
			    sql = r.getSQLInsert("TBLHTPHAKMILIK");
			    myLog.info("addHakmilikBaruById:sql insert baru::"+sql);
				stmt.executeUpdate(sql);
			}
			//GET DATA LAMA FROM TBLHTPHAKMILIKAGENSI
			sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_AGENSI, TBLHTPHAKMILIKAGENSI.ID_KEMENTERIAN " +
				" FROM TBLHTPHAKMILIKAGENSI "+
		      	" WHERE TBLHTPHAKMILIKAGENSI.ID_HAKMILIK = '"+idHakmilikAsal +"'";
			//
		    myLog.info("sql="+sql);
			ResultSet rs2 = stmt.executeQuery(sql);
			//KALO ADA ROW BRU INSERT
			if (rs2.next()){
				//CREATE REKOD BARU 
				r = new SQLRenderer();
			    long idHakmilikAgensi = DB.getNextID("TBLHTPHAKMILIKAGENSI_SEQ"); 
			    r.add("ID_HAKMILIKAGENSI",idHakmilikAgensi);
			    r.add("ID_HAKMILIK",idHakmilikBaru);
			    r.add("ID_MASUK",data.get("idKemaskini"));
			    r.add("ID_AGENSI",rs2.getString("ID_AGENSI"));
			    r.add("ID_KEMENTERIAN",rs2.getString("ID_KEMENTERIAN"));
			    r.add("TARIKH_MASUK",r.unquote("SYSDATE"));
			    sql = r.getSQLInsert("TBLHTPHAKMILIKAGENSI");
			    myLog.info("insert hakmilikagensi :"+sql);
			    stmt.executeUpdate(sql);
			}
			//rs2.next();		    
		    
			//GET DATA LAMA FROM TBLHTPHAKMILIKCUKAI
			sql = "SELECT NVL(TPC.CUKAI,0) CUKAI,NVL(TPC.CUKAI_TERKINI,0) CUKAI_TERKINI, " +
					"NVL(TPC.KOD_BAYARAN_CUKAI,'P') KOD_BAYARAN_CUKAI " +
					"FROM TBLHTPHAKMILIKCUKAI TPC "+
		      		"WHERE TPC.ID_HAKMILIK = ' "+idHakmilikAsal +"'";
			ResultSet rs3 = stmt.executeQuery(sql);
			//KALO ADA ROW BARU INSERT
			if(rs3.next()){
				//CREATE REKOD BARU 
				sql = "INSERT INTO TBLHTPHAKMILIKCUKAI (ID_HAKMILIK, ID_MASUK, " +
						"CUKAI_TERKINI, CUKAI, KOD_BAYARAN_CUKAI, STATUS, TARIKH_MASUK)  " +
						"VALUES ('"+idHakmilikBaru+"',"+data.get("idKemaskini")+", " +
								"'"+data.get("txtCukaiTerkini")+"', " +
								"'"+rs3.getString("CUKAI")+"', " +
								"'"+rs3.getString("KOD_BAYARAN_CUKAI")+"', 'S',  SYSDATE )";
				
				/*r = new SQLRenderer();
			    long idHakmilikCukai = DB.getNextID("TBLHTPHAKMILIKCUKAI_SEQ"); 
			    r.add("ID_HAKMILIKCUKAI",idHakmilikCukai);
			    r.add("ID_HAKMILIK",idHakmilikBaru);
			    r.add("ID_MASUK",data.get("idKemaskini"));
			    r.add("CUKAI_TERKINI",rs3.getString("CUKAI_TERKINI"));
			    r.add("CUKAI",rs3.getString("CUKAI"));
			    r.add("KOD_BAYARAN_CUKAI",rs3.getString("KOD_BAYARAN_CUKAI"));
			    r.add("STATUS","S");
			    r.add("TARIKH_MASUK",r.unquote("SYSDATE"));
			    sql = r.getSQLInsert("TBLHTPHAKMILIKCUKAI");
			    */
			    myLog.info("insert hakmilikcukai :"+sql);
			    stmt.executeUpdate(sql);
			}
		
			conn.commit();
			
		}catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
		
	}		
	public boolean isHakmilik(String idNegeri, String idDaerah,String idMukim
		,String idJenisHakmilik, String noHakmilik) throws Exception {
		myLog.info("isHakmilikWarta:"+idNegeri+","+ idDaerah+","+ idMukim+","+idJenisHakmilik+","+ noHakmilik);
	
		Connection conn = null;
			Db db = null;
			boolean returnValue = false;
			SQLRenderer r = null;
	 	    try {
				String sql = null;
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				r = new SQLRenderer();
				r.add("TPH.ID_HAKMILIK");
				//r.add("TPH.ID_HAKMILIK",r.unquote("TPA.ID_HAKMILIK"));
				r.add("TPH.ID_NEGERI",idNegeri);
				r.add("TPH.ID_DAERAH",idDaerah);
				r.add("TPH.ID_MUKIM",idMukim);
				r.add("ID_JENISHAKMILIK",idJenisHakmilik);
				r.add("LTRIM(TPH.NO_HAKMILIK,0)",noHakmilik);
				sql = r.getSQLSelect("TBLHTPHAKMILIK TPH " +
					"");
				myLog.info("isHakmilikWarta:sql="+sql);
				rs = stmt.executeQuery(sql);
				while(rs.next()){
					returnValue = true;				
				}
				
			}catch(Exception e){
				throw new Exception(getIHTP().getErrorHTML(e.toString()));
				
		 	} finally {
		 		if (db != null) db.close();

		 	}
			return returnValue;
		
		}

	public boolean isWarta(String idNegeri, String idDaerah,String idMukim
		,String noWarta
		, String idLot, String noLot) throws Exception {
//		myLog.info("isHakmilikWarta:"+idNegeri+","+ idDaerah+","+ idMukim+","+noWarta+","+ idLot+","+noLot);
		Connection conn = null;
		Db db = null;
		boolean returnValue = false;
		SQLRenderer r = null;
 	    try {
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();
			r.add("TPH.ID_HAKMILIK");
			//r.add("TPH.ID_HAKMILIK",r.unquote("TPA.ID_HAKMILIK"));
			r.add("TPH.ID_NEGERI",idNegeri);
			r.add("TPH.ID_DAERAH",idDaerah);
			r.add("TPH.ID_MUKIM",idMukim);
			r.add("LTRIM(TPH.NO_WARTA,0)",noWarta);
			//r.add("TPH.ID_LOT",idLot);
			r.add("LTRIM(TPH.NO_LOT,0)",noLot);	
			sql = r.getSQLSelect("TBLHTPHAKMILIK TPH " +
				"");
			myLog.info("isHakmilikWarta:sql="+sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				returnValue = true;				
			}
			
		}catch(Exception e){
			throw new Exception(getIHTP().getErrorHTML(e.toString()));
			
	 	} finally {
	 		if (db != null) db.close();

	 	}
		return returnValue;
	
	}
	@Override
	public Hashtable<String,String> getHakmilikUrusan(String idHakmilik,boolean isPB) {
			Db db = null;
			//HakMilik hakmilik = new HakMilik();
			Hashtable<String,String> h = null;
			try{
				db = new Db();
				FrmRekodUtilData frmRekodUtilData = FrmRekodUtilData.getInstance();
				Statement stmt = db.getStatement();
				String sql ="SELECT " +
						" RN.NAMA_NEGERI, RD.NAMA_DAERAH, RM.NAMA_MUKIM " +					
						" ,NVL(LOT.KETERANGAN,'0') KOD_LOT,MT.NO_LOT" +
						" ,RJH.KOD_JENIS_HAKMILIK,MT.NO_HAKMILIK" +
						" ,MT.NO_BANGUNAN,MT.NO_TINGKAT,MT.NO_PETAK" +
						" ,MT.NO_WARTA" +
						"";
					if(isPB){
						sql +=",PB.NAMA ";
					}
					sql +="" +
						//" ,MT.ID_LUAS,MT.PEGANGAN_HAKMILIK,MT.ID_SUBKATEGORI,MT.ID_AGENSI,M.CUKAI_TERKINI " +
						//" ,B.NO_RUJUKAN_KJP,B.NO_RUJUKAN_PTG,B.NO_RUJUKAN_PTD,F.NO_FAIL,F.ID_FAIL,F.ID_KEMENTERIAN " +
						" FROM " +
							getTableTanahTemp();
					if(isPB){
						sql +=",TBLHTPPIHAKBERKEPENTINGAN PB";
					}
											
					sql +=" WHERE" +
							getTableTanahKondisi();
					if(isPB){
						sql +=" AND MT.ID_HAKMILIKURUSAN = PB.ID_HAKMILIKURUSAN ";
					}
					sql +=" AND MT.ID_HAKMILIKURUSAN ="+idHakmilik +
						"";
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()){
					h = new Hashtable<String,String>();			
					h.put("idHakmilik", idHakmilik);
					h.put("namaNegeri", rs.getString("NAMA_NEGERI")==null ? "" :rs.getString("NAMA_NEGERI"));
					h.put("namaDaerah", rs.getString("NAMA_DAERAH")==null ? "" :rs.getString("NAMA_DAERAH"));
					h.put("namaMukim", rs.getString("NAMA_MUKIM")==null ? "" :rs.getString("NAMA_MUKIM"));	
					h.put("kodLot", rs.getString("KOD_LOT")==null ? "" :rs.getString("KOD_LOT"));
					h.put("noLot", rs.getString("NO_LOT")==null ? "" :rs.getString("NO_LOT"));
					h.put("kodJenisHakmilik", rs.getString("KOD_JENIS_HAKMILIK")==null ? "" :rs.getString("KOD_JENIS_HAKMILIK").toUpperCase());
					h.put("noHakmilik", rs.getString("NO_HAKMILIK")==null ? "" :rs.getString("NO_HAKMILIK"));			
					h.put("noBangunan", rs.getString("NO_BANGUNAN")==null ? "TIADA" :rs.getString("NO_BANGUNAN"));
					h.put("noTingkat", rs.getString("NO_TINGKAT")==null ? "TIADA" :rs.getString("NO_TINGKAT"));
					h.put("noPetak", rs.getString("NO_PETAK")==null ? "TIADA" :rs.getString("NO_PETAK"));
					h.put("noWarta", rs.getString("NO_WARTA")==null ? "" :rs.getString("NO_WARTA"));
					if(rs.getString("NO_HAKMILIK")!= null){
						h.put("jenisTanah","M");
					}else{
			    		  h.put("jenisTanah","R");
					}
					if(rs.getString("NO_HAKMILIK")!= null && rs.getString("NO_WARTA")!= null){
						h.put("jenisTanah","X");
					}
					if(isPB)
						h.put("nama", rs.getString("NAMA")==null ? "" :rs.getString("NAMA"));

//					h.put("noFailSeksyen", rs.getString("NO_FAIL")==null ? "" :rs.getString("NO_FAIL"));
//					h.put("noFailPtg", rs.getString("NO_FAIL_PTG")==null ? "TIADA" :rs.getString("NO_FAIL_PTG"));
//					h.put("noFailPtd", rs.getString("NO_FAIL_PTD")==null ? "TIADA" :rs.getString("NO_FAIL_PTD"));
//					h.put("tajukFail", rs.getString("TUJUAN")==null ? "" :rs.getString("TUJUAN"));
//					h.put("namaAgensi", rs.getString("NAMA_AGENSI")==null ? "" :rs.getString("NAMA_AGENSI"));
//					h.put("noFailKjp", rs.getString("NO_FAIL_KJP")==null ? "TIADA":rs.getString("NO_FAIL_KJP"));
//					h.put("caraPerolehan", rs.getString("NAMA_URUSAN")==null ? "" :rs.getString("NAMA_URUSAN"));
//					h.put("idKementerian", rs.getString("ID_KEMENTERIAN")==null ? "" :rs.getString("ID_KEMENTERIAN"));
//					h.put("catatan", rs.getString("CATATAN")==null ? "":rs.getString("CATATAN"));
//					h.put("pegawaiAkhir",Utils.isNull(rs.getString("PEGAWAI_AKHIR")));
//					h.put("kegunaanTanah",Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
//					h.put("idJenisRizab",Utils.isNull(rs.getString("ID_RIZAB")));
//			    	h.put("tarikh_rizab", rs.getDate("TARIKH_RIZAB")==null?"":Format.format(rs.getDate("TARIKH_RIZAB")));  
			    					
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				db.close();
			}
			return h;
			
		}
	
	public String getTableTanahTemp(){
		return "TBLHTPHAKMILIKURUSAN MT" +
				",TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM" +
				",TBLRUJLOT LOT,TBLRUJJENISHAKMILIK RJH"+
				" ";
	}
	public String getTableTanahKondisi(){
		return " MT.ID_NEGERI=RN.ID_NEGERI " +
				" AND MT.ID_DAERAH = RD.ID_DAERAH " +
				" AND MT.ID_MUKIM  = RM.ID_MUKIM " +
				" AND MT.ID_LOT = LOT.ID_LOT " +
				" AND MT.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK " +
				"";
	}	
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
	
}
