package ekptg.model.integrasi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import etapp.entity.integrasi.MaklumatCukai;
//import ekptg.model.htp.HtpBean;

public class FrmLHDNBean implements IIntegrasi{
	
 	private IHtp iHTP = null;  
	private final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
	private Hashtable h = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.integrasi.FrmLHDNBean.class);
	private Vector senaraiFailSemakanPerintahHQ = new Vector();
	String sql = "";
	Db db = null;
	Connection conn = null;

	// TAMBAH MAKLUMAT  
	@Override
	public String simpan(Hashtable data) throws Exception {
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			long id = DB.getNextID("TBLINTLHDNCUKAI_SEQ"); 
		    r.add("ID_LHDNCUKAI",id);
		    r.add("ID_SIMATI",data.get("idSimati"));
		    r.add("CUKAI",data.get("cukai"));
		    r.add("TUNGGAKAN",data.get("tunggakan"));
		    r.add("JUMLAH",data.get("jumlah"));
		    r.add("BAKI",data.get("baki"));
		    
		    r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_MASUK", r.unquote(String.valueOf(data.get("idMasuk"))));	
	    	r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", r.unquote(String.valueOf(data.get("idMasuk"))));  

		    sql = r.getSQLInsert("TBLINTLHDNCUKAI");
		    myLog.info("sql insert baru :sql="+sql);		    	    
			stmt.executeUpdate(sql);
			conn.commit();
		    return String.valueOf(id);
		    
		}catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception(getIHTP().getErrorHTML("Rollback error : " + e.getMessage()));
	    	}
	    	throw new Exception(getIHTP().getErrorHTML("Ralat : Masalah penyimpanan data " + ex.getMessage()));
	    	
	    } finally {
			if (db != null)
				db.close();
		}


	}
	
	// TAMBAH MAKLUMAT  
	@Override
	public String kemaskini(Hashtable data) throws Exception {
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		    r.update("ID_LHDNCUKAI",data.get("idMaklumatCukai"));
		    r.add("CUKAI",data.get("cukai"));
		    r.add("TUNGGAKAN",data.get("tunggakan"));
		    r.add("JUMLAH",data.get("jumlah"));
		    r.add("BAKI",data.get("baki"));
	    	r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", r.unquote(String.valueOf(data.get("idMasuk"))));  
		    sql = r.getSQLUpdate("TBLINTLHDNCUKAI");
			stmt.executeUpdate(sql);
			conn.commit();
		    return String.valueOf(data.get("idMaklumatCukai"));
		    
		}catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception(getIHTP().getErrorHTML("Rollback error : " + e.getMessage()));
	    	}
	    	throw new Exception(getIHTP().getErrorHTML("Ralat : Masalah penyimpanan data " + ex.getMessage()));
	    	
	    } finally {
			if (db != null)
				db.close();
		}

	}
	
	/** 
	 * Paparan Tindakan 
	 */
	@Override
	public Vector getSenarai() throws Exception {
		Db db = null;
		String sql = "";
		Vector listMaklumatFail = null;
		try {
			db = new Db();
			listMaklumatFail = new Vector();
			Statement stmt = db.getStatement();				
		    //sql = getSQL(idHakmilik)+ " ORDER BY A.TARIKH_BINAAN DESC";
			//myLog.info("Vector getMaklumat:sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil+".");
				h.put("idHakmilikPerihal", rs.getString("ID_HAKMILIKPERIHAL"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK"));
				h.put("jenisBangunan", rs.getString("JENIS_BINAAN")==null ? "" :rs.getString("JENIS_BINAAN"));
				h.put("noRujukanJKR", rs.getString("NO_RUJUKAN_JKR")==null ? "" :rs.getString("NO_RUJUKAN_JKR"));
				h.put("tarikhBina", rs.getString("TARIKH_BINAAN")==null ? "" :rs.getString("TARIKH_BINAAN"));
				h.put("hargaBina", rs.getString("HARGA_BINAAN")==null ? "" :rs.getString("HARGA_BINAAN"));
				h.put("unitLuas", rs.getString("KETERANGAN")==null ? "" :rs.getString("KETERANGAN"));
				h.put("luasB", rs.getString("LUAS_BANGUNAN")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("LUAS_BANGUNAN")));
				h.put("luasJ", rs.getString("LUAS_JALAN")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("LUAS_JALAN")));
				h.put("luasP", rs.getString("LUAS_PADANG")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("LUAS_PADANG")));
				h.put("luasPR", rs.getString("LUAS_PARKING")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("LUAS_PARKING")));
				h.put("luasL", rs.getString("LUAS_LAIN")==null ? "0.00000" :ekptg.helpers.Utils.formatLuas(rs.getDouble("LUAS_LAIN")));	
				h.put("catatan", rs.getString("CATATAN")==null ? "" :rs.getString("CATATAN"));

				h.put("idSusulanStatus", Utils.isNull(rs.getString("ID_SUSULANSTATUSFAIL")));
				h.put("idStatusFail", Utils.isNull(rs.getString("ID_SUBURUSANSTATUSFAIL")));
				//h.put("idSusulan", Utils.isNull(rs.getString("ID_SUSULAN")));
				//h.put("tarikh", Utils.isNull(rs.getString("TARIKH")));
				//h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
				h.put("status", Utils.isNull(rs.getString("STATUS")));
				h.put("keteranganBinaan", Utils.isNull(rs.getString("KETERANGAN_BINAAN")));
				listMaklumatFail.addElement(h);
				bil++;
			}
		}catch (SQLException ex) { 
	    	throw new Exception(getIHTP().getErrorHTML("Ralat : Masalah select data " + ex.getMessage()));
			
		} finally {
			if (db != null)
			db.close();
			
		}
		return listMaklumatFail;
	}
	
	/** Dibuat pada 22/10/2012 Oleh Mohamad Rosli
	// Bertujuan mendapatkan maklumat cukai
	**/
	//@Override
	public MaklumatCukai getMaklumat(String id) throws Exception {
		MaklumatCukai mc = null; 
		try {
			db = new Db();
			Statement stmt = db.getStatement();				
		    sql = getSQL(id)+
		    "";
			myLog.info("getMaklumat:sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			while (rs.next()) {
//				h = new Hashtable();
//				h.put("bil", bil);
//				h.put("cukai", rs.getDouble("CUKAI"));
//				h.put("tunggakan", rs.getDouble("TUNGGAKAN"));
//				h.put("jumlah", rs.getDouble("JUMLAH"));
//				h.put("baki", rs.getDouble("BAKI"));
				mc = new MaklumatCukai();
				//mc.setBil(bil);
				mc.setCukai(rs.getDouble("CUKAI"));
				mc.setTunggakan(rs.getDouble("TUNGGAKAN"));
				mc.setJumlah(rs.getDouble("JUMLAH"));
				mc.setBaki(rs.getDouble("BAKI"));
				bil++;
				
			}
			
		} finally {
			if (db != null)
			db.close();
			
		}
		//return h;
		return mc;
	}
	
	// TAMBAH MAKLUMAT  
	public String simpanSusulanStatusFail(Hashtable data) throws Exception {
		try {
				db = new Db();
				conn = db.getConnection();
		    	conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
			    long id = DB.getNextID("TBLHTPSUSULANSTATUSFAIL_SEQ"); 
			    r.add("ID_SUSULANSTATUSFAIL",id);
			    r.add("ID_SUSULAN",data.get("idSusulan"));
			    r.add("ID_SUBURUSANSTATUSFAIL",data.get("idStatusFail"));
			    r.add("SUMBER",data.get("sumber"));
			    r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("ID_MASUK", r.unquote(String.valueOf(data.get("idMasuk"))));	
		    	r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				r.add("ID_KEMASKINI", r.unquote(String.valueOf(data.get("idMasuk"))));  
			    sql = r.getSQLInsert("TBLHTPSUSULANSTATUSFAIL");
			    myLog.info("sql insert baru :sql="+sql);			    	    
				stmt.executeUpdate(sql);
				conn.commit();
			    return String.valueOf(id);
			    
			}catch (SQLException ex) { 
		    	try {
		    		conn.rollback();
		    	} catch (SQLException e) {
		    		throw new Exception(getIHTP().getErrorHTML("Rollback error : " + e.getMessage()));
		    	}
		    	throw new Exception(getIHTP().getErrorHTML("Ralat : Masalah penyimpanan data " + ex.getMessage()));

		    	
		    } finally {
				if (db != null)
					db.close();
			}

	}
			
	public String getSQL(String id){
		sql = "" +
		" SELECT ILHDN.CUKAI, ILHDN.TUNGGAKAN, ILHDN.JUMLAH, ILHDN.BAKI "+
		" FROM TBLINTLHDNCUKAI ILHDN "+
		" WHERE  "+
		" ILHDN.ID_LHDNCUKAI = '"+id +"'"+
    	"";
		return sql;
		
	}
	
	public String getSQL(String idHakmilik,String langkah){
		sql = "" +
		" SELECT A.ID_HAKMILIKPERIHAL, A.ID_HAKMILIK, A.LUAS_BANGUNAN, A.LUAS_JALAN, A.LUAS_PADANG, "+
		" A.LUAS_PARKING, A.LUAS_LAIN, A.LUAS_BELUMDIBANGUNKAN, B.LUAS, A.JENIS_BINAAN, A.NO_RUJUKAN_JKR, " +
		" TO_CHAR(A.TARIKH_BINAAN,'dd/mm/yyyy') TARIKH_BINAAN " +
		" ,A.HARGA_BINAAN, A.CATATAN ,C.KETERANGAN, A.LUAS_HEKTAR "+			
    	" , ( " +
    	" 	    SELECT RSI.KETERANGAN " +
    	" 	    FROM TBLRUJSTATUS RSI, TBLRUJSUBURUSANSTATUS RSUS,TBLHTPRUJSUBURUSANSTATUSFAIL RSUSF,TBLHTPSUSULANSTATUSFAIL SSF " +
    	" 	    WHERE RSI.ID_STATUS = RSUS.ID_STATUS " +
    	"		AND RSUS.ID_SUBURUSANSTATUS = RSUSF.ID_SUBURUSANSTATUS " +
    	" 	    AND RSUSF.ID_SUBURUSANSTATUSFAIL = SSF.ID_SUBURUSANSTATUSFAIL " +
    	" 	    AND RSUSF.AKTIF='1' " +
    	" 	    AND RSUSF.ID_PERMOHONAN = A.ID_HAKMILIKPERIHAL " +
    	" 	) STATUS " +
    	" , ( " +
    	" 	    SELECT RSI.KETERANGAN " +
    	" 	    FROM TBLRUJSTATUS RSI, TBLRUJSUBURUSANSTATUS RSUS,TBLHTPRUJSUBURUSANSTATUSFAIL RSUSF,TBLHTPSUSULANSTATUSFAIL SSF " +
    	" 	    WHERE RSI.ID_STATUS = RSUS.ID_STATUS " +
    	"		AND RSUS.ID_SUBURUSANSTATUS = RSUSF.ID_SUBURUSANSTATUS " +
    	" 	    AND RSUSF.ID_SUBURUSANSTATUSFAIL = SSF.ID_SUBURUSANSTATUSFAIL " +
    	" 	    AND RSUSF.AKTIF='1' " +
    	" 	    AND RSUSF.ID_PERMOHONAN = A.ID_HAKMILIKPERIHAL " +
    	" 	   AND RSUS.LANGKAH ='"+langkah+"'" +     
    	" 	) ISPAUTAN " +
    	" ,(SELECT SSF.ID_SUSULANSTATUSFAIL "+
    	" 	    FROM TBLHTPRUJSUBURUSANSTATUSFAIL RSUSF,TBLHTPSUSULANSTATUSFAIL SSF "+
    	" 	    WHERE RSUSF.ID_SUBURUSANSTATUSFAIL = SSF.ID_SUBURUSANSTATUSFAIL "+
    	" 	    AND RSUSF.AKTIF='1' "+
    	" 	    AND RSUSF.ID_PERMOHONAN = A.ID_HAKMILIKPERIHAL " +
    	" ) ID_SUSULANSTATUSFAIL " +
    	" ,(SELECT RSUSF.ID_SUBURUSANSTATUSFAIL "+
    	" 	    FROM TBLHTPRUJSUBURUSANSTATUSFAIL RSUSF,TBLHTPSUSULANSTATUSFAIL SSF "+
    	" 	    WHERE RSUSF.ID_SUBURUSANSTATUSFAIL = SSF.ID_SUBURUSANSTATUSFAIL "+
    	" 	    AND RSUSF.AKTIF='1' "+
    	" 	    AND RSUSF.ID_PERMOHONAN = A.ID_HAKMILIKPERIHAL " +
    	" ) ID_SUBURUSANSTATUSFAIL " +
    	"  ,(SELECT KETERANGAN FROM TBLHTPRUJENISBINAAN " +
    	"  WHERE (STATUS_SAH =A.JENIS_BINAAN OR TO_CHAR(ID_JENISBINAAN) =A.JENIS_BINAAN) ) KETERANGAN_BINAAN" +		
		" FROM TBLHTPHAKMILIKPERIHAL A, TBLHTPHAKMILIK B, TBLRUJLUAS C "+
		" WHERE A.ID_HAKMILIK = B.ID_HAKMILIK "+
		" AND C.ID_LUAS(+) = A.ID_LUAS_BERSAMAAN "+
		" AND A.ID_HAKMILIK = '"+idHakmilik +"'"+
    	"";
		return sql;
	}
	
	public void hapus(String id) throws Exception {
		Db db = null;
		String sql = "";
		Vector senarai = null;
		Hashtable h = null;
		SQLRenderer r = new SQLRenderer();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//senarai = getUlasanStatus(id);
			for (int iii = 0; iii < senarai.size(); iii++) { 
  	    	  	h = (Hashtable)senarai.get(iii);
  	    	  	r = new SQLRenderer();
  	    	  	r.add("ID_SUBURUSANSTATUSFAIL",h.get("id"));
  				sql = r.getSQLDelete("TBLRUJSUBURUSANSTATUSFAIL");
   	    	  	myLog.info("TBLRUJSUBURUSANSTATUSFAIL:"+sql);
 				stmt.executeUpdate(sql);
    	  	
			}
	    	r = new SQLRenderer();
			r.add("ID_SUSULAN",id);
			sql = r.getSQLDelete("TBLHTPSUSULANSTATUSFAIL");
			myLog.info("TBLHTPSUSULANSTATUSFAIL:"+sql);
			stmt.executeUpdate(sql);

	    	r = new SQLRenderer();
			r.add("ID_SUSULAN",id);
			sql = r.getSQLDelete("TBLHTPSUSULAN");
			myLog.info("TBLHTPSUSULAN:"+sql);
			stmt.executeUpdate(sql);
	  
		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public Vector carianPerintahHQ(String role,String noFail, String namaPemohon, String namaSimati
			, String jenisKp, String noKp, HttpSession session,	boolean setLimit) throws Exception {	
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		senaraiFailSemakanPerintahHQ.clear();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL" +
			" , H.ID_PERMOHONANSIMATI" +
			" , B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK" +
			" , C.ID_SIMATI,C.NAMA_SIMATI, D.ID_PEMOHON" +
			" , D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN " +
			" , G.KETERANGAN" +
			" ,(SELECT RIS.KETERANGAN " + 
			" FROM TBLINTRUJSTATUS RIS, TBLINTRUJSTATUSMODUL RISM,TBLINTRUJSTATUSMODULSEMASA RISMS, TBLINTLHDNCUKAI ILH " + 
			" WHERE  RISM.ID_STATUS = RIS.ID_STATUS " + 
			" AND RISM.ID_STATUSMODUL = RISMS.ID_STATUSMODUL AND RISMS.AKTIF = '1' " +
			" AND RISMS.ID_SUMBER = ILH.ID_LHDNCUKAI " +
			" AND ILH.ID_SIMATI = C.ID_SIMATI" +
			") KETERANGAN_CUKAI " +
			", G.ID_STATUS " +
			" ,(SELECT ILH.ID_LHDNCUKAI " +
			" FROM TBLINTLHDNCUKAI ILH " +
			" WHERE  ILH.ID_SIMATI=C.ID_SIMATI " +
			" ) ID_LHDNCUKAI" +
			" FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSTATUS G, " +
			" TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J " +
			" WHERE A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI " +
			" AND D.ID_PEMOHON = B.ID_PEMOHON "+
//			" AND B.ID_STATUS = '21' " + //Selesai
			" AND B.ID_STATUS = '8' " + //Pendaftaran
			" AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH  " +
			" AND B.SEKSYEN = 8 AND B.FLAG_JENIS_PERMOHONAN = 1 " +
			" AND B.ID_STATUS = G.ID_STATUS AND B.ID_STATUS != 999";		
				
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
				
			}
			
			//namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
				
			}
			
			//namaSimati
			if (namaSimati != null) {
				if (!namaSimati.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(C.NAMA_SIMATI) LIKE '%' ||'"
							+ namaSimati.trim().toUpperCase() + "'|| '%'";
				}
			
			}
			
			//jenisKp & noKp
			if (jenisKp != null) {
					if (!jenisKp.trim().equals("")) {
							if (noKp != null) {
									if (!noKp.trim().equals("")) {
											if (jenisKp.equals("1")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_BARU) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
											else if (jenisKp.equals("2")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_LAMA) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
											else if (jenisKp.equals("3")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_LAIN) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
									}
							}
					}
				
								
			}
						
			//sql = sql + " ORDER BY B.ID_PERMOHONAN";
			
			if (setLimit == true) {
				sql = sql + " AND ROWNUM <= 50 ";
			}
			sql = sql + " AND C.ID_SIMATI NOT IN (SELECT ID_SIMATI FROM TBLINTLHDNCUKAI )" ;
			myLog.info("SENARAI PERINTAH SEK8_17 HQ"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				h.put("namaSimati", rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("idStatusLHDN", rs.getString("ID_LHDNCUKAI") == null ? "0" : rs.getString("ID_LHDNCUKAI"));				
				senaraiFailSemakanPerintahHQ.addElement(h);
				bil++;
				count++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		return senaraiFailSemakanPerintahHQ;
	
	}	
	
	public Vector carianPerintahHQProses(String role,String noFail, String namaPemohon, String namaSimati
			, String jenisKp, String noKp, HttpSession session) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		senaraiFailSemakanPerintahHQ.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL" +
					" , H.ID_PERMOHONANSIMATI" +
					" , B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK" +
					" , C.ID_SIMATI,C.NAMA_SIMATI, D.ID_PEMOHON" +
					" , D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN " +
					//" , G.KETERANGAN" +
					" ,(SELECT RIS.KETERANGAN " + 
					" FROM TBLINTRUJSTATUS RIS, TBLINTRUJSTATUSMODUL RISM,TBLINTRUJSTATUSMODULSEMASA RISMS, TBLINTLHDNCUKAI ILH " + 
					" WHERE  RISM.ID_STATUS = RIS.ID_STATUS " + 
					" AND RISM.ID_STATUSMODUL = RISMS.ID_STATUSMODUL AND RISMS.AKTIF = '1' " +
					" AND RISMS.ID_SUMBER = ILH.ID_LHDNCUKAI " +
					" AND ILH.ID_SIMATI = C.ID_SIMATI" +
					") KETERANGAN " +
					", G.ID_STATUS " +
					" ,(SELECT ILH.ID_LHDNCUKAI " +
					" FROM TBLINTLHDNCUKAI ILH " +
					" WHERE  ILH.ID_SIMATI=C.ID_SIMATI " +
					" ) ID_LHDNCUKAI" +
//					" ,(SELECT TO_CHAR(ILH.TARIKH_KEMASKINI,'dd/mm/yyyy') " +
					" ,(SELECT ILH.TARIKH_MASUK " +
					" FROM TBLINTLHDNCUKAI ILH " +
					" WHERE  ILH.ID_SIMATI=C.ID_SIMATI " +
					" ) TARIKH_SEMAKAN" +
					" FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSTATUS G, " +
					" TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J " +
					" WHERE A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI " +
					" AND D.ID_PEMOHON = B.ID_PEMOHON "+
//					" AND B.ID_STATUS = '21' " + //Selesai
//					" AND B.ID_STATUS = '8' " + //Pendaftaran
					" AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH  " +
					" AND B.SEKSYEN = 8 AND B.FLAG_JENIS_PERMOHONAN = 1 " +
					" AND B.ID_STATUS = G.ID_STATUS AND B.ID_STATUS != 999";
			
			boolean setLimit = true;
				
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
				
			}
			
			//namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
				
			}
			
			//namaSimati
			if (namaSimati != null) {
				if (!namaSimati.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(C.NAMA_SIMATI) LIKE '%' ||'"
							+ namaSimati.trim().toUpperCase() + "'|| '%'";
				}
			
			}
			
			//jenisKp & noKp
			if (jenisKp != null) {
					if (!jenisKp.trim().equals("")) {
							if (noKp != null) {
									if (!noKp.trim().equals("")) {
											if (jenisKp.equals("1")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_BARU) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
											else if (jenisKp.equals("2")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_LAMA) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
											else if (jenisKp.equals("3")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_LAIN) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
									}
							}
					}
				
								
			}
						
			//sql = sql + " ORDER BY B.ID_PERMOHONAN";
			
			if (setLimit == true) {
				sql = sql + " AND ROWNUM <= 50 ";
			}
			sql = sql + " AND C.ID_SIMATI IN (" +
			"SELECT LHDNC.ID_SIMATI " +
			" FROM TBLINTLHDNCUKAI LHDNC,TBLINTRUJSTATUSMODUL IRSM,TBLINTRUJSTATUSMODULSEMASA IRSMS " +
			" WHERE " +
			" IRSMS.ID_SUMBER = LHDNC.ID_LHDNCUKAI " +
			" AND IRSMS.ID_STATUSMODUL = IRSM.ID_STATUSMODUL " +
			" AND IRSM.LANGKAH = 2 " +
			" AND IRSMS.AKTIF = '1' " +
			")" ;
			myLog.info("SENARAI PROSES"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				h.put("namaSimati", rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("idStatusLHDN", rs.getString("ID_LHDNCUKAI") == null ? "0" : rs.getString("ID_LHDNCUKAI"));				
				h.put("tarikhSemakan", rs.getString("TARIKH_SEMAKAN") == null ? "" : sdf.format(rs.getDate("TARIKH_SEMAKAN")));
				senaraiFailSemakanPerintahHQ.addElement(h);
				bil++;
				count++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		return senaraiFailSemakanPerintahHQ;
	}	

	public Vector carianPerintahHQSemak(String role,String noFail, String namaPemohon, String namaSimati
			, String jenisKp, String noKp, HttpSession session) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		senaraiFailSemakanPerintahHQ.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL" +
					" , H.ID_PERMOHONANSIMATI" +
					" , B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK" +
					" , C.ID_SIMATI,C.NAMA_SIMATI, D.ID_PEMOHON" +
					" , D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN " +
					//" , G.KETERANGAN" +
					" ,(SELECT RIS.KETERANGAN " + 
					" FROM TBLINTRUJSTATUS RIS, TBLINTRUJSTATUSMODUL RISM,TBLINTRUJSTATUSMODULSEMASA RISMS, TBLINTLHDNCUKAI ILH " + 
					" WHERE  RISM.ID_STATUS = RIS.ID_STATUS " + 
					" AND RISM.ID_STATUSMODUL = RISMS.ID_STATUSMODUL AND RISMS.AKTIF = '1' " +
					" AND RISMS.ID_SUMBER = ILH.ID_LHDNCUKAI " +
					" AND ILH.ID_SIMATI = C.ID_SIMATI" +
					") KETERANGAN " +
					", G.ID_STATUS " +
					" ,(SELECT ILH.ID_LHDNCUKAI " +
					" FROM TBLINTLHDNCUKAI ILH " +
					" WHERE  ILH.ID_SIMATI=C.ID_SIMATI " +
					" ) ID_LHDNCUKAI" +
//					" ,(SELECT TO_CHAR(ILH.TARIKH_KEMASKINI,'dd/mm/yyyy') " +
					" ,(SELECT ILH.TARIKH_MASUK " +
					" FROM TBLINTLHDNCUKAI ILH " +
					" WHERE  ILH.ID_SIMATI=C.ID_SIMATI " +
					" ) TARIKH_SEMAKAN" +
					" FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSTATUS G, " +
					" TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J " +
					" WHERE A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI " +
					" AND D.ID_PEMOHON = B.ID_PEMOHON "+
//					" AND B.ID_STATUS = '21' " + //Selesai
					" AND B.ID_STATUS = '8' " + //Pendaftaran
					" AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH  " +
					" AND B.SEKSYEN = 8 AND B.FLAG_JENIS_PERMOHONAN = 1 " +
					" AND B.ID_STATUS = G.ID_STATUS AND B.ID_STATUS != 999";
			
			boolean setLimit = true;
				
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
				
			}
			
			//namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
				
			}
			
			//namaSimati
			if (namaSimati != null) {
				if (!namaSimati.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(C.NAMA_SIMATI) LIKE '%' ||'"
							+ namaSimati.trim().toUpperCase() + "'|| '%'";
				}
			
			}
			
			//jenisKp & noKp
			if (jenisKp != null) {
					if (!jenisKp.trim().equals("")) {
							if (noKp != null) {
									if (!noKp.trim().equals("")) {
											if (jenisKp.equals("1")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_BARU) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
											else if (jenisKp.equals("2")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_LAMA) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
											else if (jenisKp.equals("3")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_LAIN) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
									}
							}
					}
				
								
			}
						
			//sql = sql + " ORDER BY B.ID_PERMOHONAN";
			
			if (setLimit == true) {
				sql = sql + " AND ROWNUM <= 50 ";
			}
			sql = sql + " AND C.ID_SIMATI IN ( " +
					"SELECT LHDNC.ID_SIMATI " +
					" FROM TBLINTLHDNCUKAI LHDNC,TBLINTRUJSTATUSMODUL IRSM,TBLINTRUJSTATUSMODULSEMASA IRSMS " +
					" WHERE " +
					" IRSMS.ID_SUMBER = LHDNC.ID_LHDNCUKAI " +
					" AND IRSMS.ID_STATUSMODUL = IRSM.ID_STATUSMODUL " +
					" AND IRSM.LANGKAH = 3 " +
					" AND IRSMS.AKTIF = '1'" +
					")" ;
			myLog.info("SENARAI SEMAKAN"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				h.put("namaSimati", rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("idStatusLHDN", rs.getString("ID_LHDNCUKAI") == null ? "0" : rs.getString("ID_LHDNCUKAI"));				
				h.put("tarikhSemakan", rs.getString("TARIKH_SEMAKAN") == null ? "" : sdf.format(rs.getDate("TARIKH_SEMAKAN")));
				senaraiFailSemakanPerintahHQ.addElement(h);
				bil++;
				count++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		return senaraiFailSemakanPerintahHQ;
	}	
	
	public Vector carianPerintah(String role,String noFail, String namaPemohon, String namaSimati
			, String jenisKp, String noKp, HttpSession session) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		senaraiFailSemakanPerintahHQ.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL" +
					" , H.ID_PERMOHONANSIMATI" +
					" , B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK" +
					" , C.ID_SIMATI,C.NAMA_SIMATI, D.ID_PEMOHON" +
					" , D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN " +
					" , G.KETERANGAN" +
					" ,(SELECT RIS.KETERANGAN " + 
					" FROM TBLINTRUJSTATUS RIS, TBLINTRUJSTATUSMODUL RISM,TBLINTRUJSTATUSMODULSEMASA RISMS, TBLINTLHDNCUKAI ILH " + 
					" WHERE  RISM.ID_STATUS = RIS.ID_STATUS " + 
					" AND RISM.ID_STATUSMODUL = RISMS.ID_STATUSMODUL AND RISMS.AKTIF = '1' " +
					" AND RISMS.ID_SUMBER = ILH.ID_LHDNCUKAI " +
					" AND ILH.ID_SIMATI = C.ID_SIMATI" +
					") KETERANGAN_CUKAI " +
					", G.ID_STATUS " +
					" ,(SELECT ILH.ID_LHDNCUKAI " +
					" FROM TBLINTLHDNCUKAI ILH " +
					" WHERE  ILH.ID_SIMATI=C.ID_SIMATI " +
					" ) ID_LHDNCUKAI" +
					" FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSTATUS G, " +
					" TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J " +
					" WHERE A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI " +
					" AND D.ID_PEMOHON = B.ID_PEMOHON "+
//					" AND B.ID_STATUS = '21' " + //Selesai
					" AND B.ID_STATUS = '8' " + //Pendaftaran
					" AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH  " +
					" AND B.SEKSYEN = 8 AND B.FLAG_JENIS_PERMOHONAN = 1 " +
					" AND B.ID_STATUS = G.ID_STATUS AND B.ID_STATUS != 999";
			
			boolean setLimit = true;
				
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
				
			}
			
			//namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
				
			}
			
			//namaSimati
			if (namaSimati != null) {
				if (!namaSimati.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(C.NAMA_SIMATI) LIKE '%' ||'"
							+ namaSimati.trim().toUpperCase() + "'|| '%'";
				}
			
			}
			
			//jenisKp & noKp
			if (jenisKp != null) {
					if (!jenisKp.trim().equals("")) {
							if (noKp != null) {
									if (!noKp.trim().equals("")) {
											if (jenisKp.equals("1")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_BARU) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
											else if (jenisKp.equals("2")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_LAMA) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
											else if (jenisKp.equals("3")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_LAIN) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
									}
							}
					}
				
								
			}
						
			//sql = sql + " ORDER BY B.ID_PERMOHONAN";
			
			if (setLimit == true) {
				sql = sql + " AND ROWNUM <= 50 ";
			}
			//sql = sql + " AND C.ID_SIMATI IN (SELECT ID_SIMATI FROM TBLINTLHDNCUKAI )" ;
			myLog.info("SENARAI PERINTAH SEK8 "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN_CUKAI") == null ? rs.getString("KETERANGAN") : rs.getString("KETERANGAN_CUKAI"));
				h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				h.put("namaSimati", rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("idStatusLHDN", rs.getString("ID_LHDNCUKAI") == null ? "0" : rs.getString("ID_LHDNCUKAI"));				
				senaraiFailSemakanPerintahHQ.addElement(h);
				bil++;
				count++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		return senaraiFailSemakanPerintahHQ;
	}
	
	public Vector carianFail(String role,String noFail, String namaPemohon
			, String namaSimati, String jenisKp, String noKp
			, HttpSession session) throws Exception {		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		senaraiFailSemakanPerintahHQ.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL" +
			" , H.ID_PERMOHONANSIMATI" +
			" , B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK" +
			" , C.ID_SIMATI,C.NAMA_SIMATI, D.ID_PEMOHON" +
			" , D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN " +
			" , CASE " +
			"		WHEN B.ID_STATUS != 21 THEN 'DALAM PROSES'" +
			"		WHEN B.ID_STATUS = 21 THEN G.KETERANGAN " +
			"		ELSE 'TIADA MAKLUMAT' " +
			" END KETERANGAN "+
			", G.ID_STATUS " +
			" FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSTATUS G, " +
			" TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J " +
			" WHERE A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI " +
			" AND D.ID_PEMOHON = B.ID_PEMOHON "+
			" AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH  " +
//			" AND B.SEKSYEN = 8 " +
			" AND B.FLAG_JENIS_PERMOHONAN = 1 " +
			" AND B.ID_STATUS = G.ID_STATUS AND B.ID_STATUS != 999";
			
			boolean setLimit = true;
				
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
				
			}
			
			//namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
				
			}
			
			//namaSimati
			if (namaSimati != null) {
				if (!namaSimati.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(C.NAMA_SIMATI) LIKE '%' ||'"
							+ namaSimati.trim().toUpperCase() + "'|| '%'";
				}
			
			}
			
			//jenisKp & noKp
			if (jenisKp != null) {
					if (!jenisKp.trim().equals("")) {
							if (noKp != null) {
									if (!noKp.trim().equals("")) {
											if (jenisKp.equals("1")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_BARU) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
											else if (jenisKp.equals("2")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_LAMA) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
											else if (jenisKp.equals("3")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_LAIN) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
									}
							}
					}
				
								
			}
						
			//sql = sql + " ORDER BY B.ID_PERMOHONAN";
			
			if (setLimit == true) {
				sql = sql + " AND ROWNUM <= 50 ";
			}
			//sql = sql + " AND C.ID_SIMATI IN (SELECT ID_SIMATI FROM TBLINTLHDNCUKAI )" ;
			myLog.info("SENARAI PERINTAH SEK8 "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				//h.put("keterangan",rs.getString("KETERANGAN_CUKAI") == null ? rs.getString("KETERANGAN") : rs.getString("KETERANGAN_CUKAI"));
				h.put("keterangan",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				h.put("namaSimati", rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				//h.put("idStatusLHDN", rs.getString("ID_LHDNCUKAI") == null ? "0" : rs.getString("ID_LHDNCUKAI"));				
				senaraiFailSemakanPerintahHQ.addElement(h);
				bil++;
				count++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		return senaraiFailSemakanPerintahHQ;
	}	
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}	
	
	
}
