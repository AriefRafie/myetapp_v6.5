
package ekptg.model.ppk;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.pm.entity.ActivityEvent;
import lebah.pm.entity.UserActivityEvent;
import lebah.template.DbPersistence;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.engine.GetAttachment;
import ekptg.helpers.DB;
//hide attachment
//import ekptg.intergration.EkptgEmailSender;
import ekptg.report.ppk.FrmPopupPilihPegawaiReportData;

/*import lebah.pm.entity.ActivityEvent;
 import lebah.pm.entity.UserActivityEvent;
 import lebah.portal.action.Command;
 import lebah.template.DbPersistence;*/

public class FrmPrmhnnSek8Notis {

	static Logger myLogger = Logger.getLogger(FrmPrmhnnSek8Notis.class);

	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");

	// seksyen 8 (ada campur utk seksyen 17)
	private static Vector list = new Vector();
	private static Vector listOB = new Vector();
	private static Vector listPra = new Vector();
	private static Vector listSemuaOB = new Vector();
	private static Vector listOB18 = new Vector();
	private static Vector listDefault = new Vector();
	private static Vector listSemak = new Vector();
	private static Vector listSemakWithData = new Vector();
	private static Vector listBicara = new Vector();
	private static Vector listPenjaga = new Vector();
	private static Vector penjaga1 = new Vector();
	private static Vector penjaga2 = new Vector();
	private static Vector penjaga3 = new Vector();
	private static Vector penjaga4 = new Vector();
	private static Vector MaklumatPenjaga = new Vector();
	private static Vector listDataPenjaga = new Vector();
	private static Vector listPenerimaNotis = new Vector();
	private static Vector listSerahanNotis = new Vector();
	private static Vector listDataOBNotis = new Vector();
	private static Vector selectedOB = new Vector();
	private static Vector selectedDropdown = new Vector();
	private static Vector selectedDropdownWithPenjaga = new Vector();
	private static Vector listOBatas18 = new Vector();
	private static Vector listOBatas18Semua = new Vector();
	private static Vector listKPPenjaga = new Vector();
	private static Vector penghantarNotisByJkptg = new Vector();
	private static Vector penghantarNotis = new Vector();
	private static Vector listOBsemak = new Vector();

	// -- 23122009
	private static Vector selectedOB17 = new Vector();

	// -- 08122009
	private static Vector checkCetakAkuanSumpah = new Vector();
	
	//18052017
	private static Vector validPegPengendali = new Vector();

	// seksyen 17
	private static Vector listSek17 = new Vector();
	private static Vector listDefaultSek17 = new Vector();

	/** ADD BY PEJE - SENARAI PEMIUTANG **/
	private static Vector listPemiutang = new Vector();
	
	
	//aishahlatip
	private static Vector listCountOB = new Vector();
	private static Vector listCountPenerimaNotis = new Vector();
	private static Vector listCanSendOB = new Vector();
	private static Vector listOBhidden = new Vector();
	


	/** ADD BY PEJE - SENARAI PEMIUTANG **/
	public static Vector getListPemiutang() {
		return listPemiutang;
	}
	
	/** ADD BY PEJE - ID_PERBICARAAN **/
	private static String idPerbicaraan = "";
	
	public static String getIdPerbicaraan() {
		return idPerbicaraan;
	}

	// seksyen 8 (ada campur utk seksyen 17)
	public static Vector getListCarian() {
		return list;
	}

	public static Vector getListOBatas18() {
		return listOBatas18;
	}

	public static Vector getListOBatas18Semua() {
		return listOBatas18Semua;
	}

	public static Vector getDataPenjaga() {
		return listDataPenjaga;
	}

	public static Vector getMaklumatPenjaga() {
		return MaklumatPenjaga;
	}

	public static Vector getListPenjaga() {
		return listPenjaga;
	}

	public static Vector getPenjaga1() {
		return penjaga1;
	}

	public static Vector getPenjaga2() {
		return penjaga2;
	}

	public static Vector getPenjaga3() {
		return penjaga3;
	}

	public static Vector getPenjaga4() {
		return penjaga4;
	}

	public static Vector getListDefault() {
		return listDefault;
	}

	public static Vector getListSemak() {
		return listSemak;
	}

	public static Vector getListOB() {
		return listOB;
	}

	public static Vector getSelectedOB() {
		return selectedOB;
	}

	public static Vector getSelectedDropdown() {
		return selectedDropdown;
	}

	public static Vector getListPenerimaNotis() {
		return listPenerimaNotis;
	}
	
	public static Vector getListSerahanNotis() {
		return listSerahanNotis;
	}

	public static Vector getDataOBNotis() {
		return listDataOBNotis;
	}

	public static Vector getListOB18() {
		return listOB18;
	}

	public static Vector getListSemakWithData() {
		return listSemakWithData;
	}

	public static Vector getListBicara() {
		return listBicara;
	}

	public static Vector getSelectedDropdownWithPenjaga() {
		return selectedDropdownWithPenjaga;
	}

	public static Vector getListSemuaOB() {
		return listSemuaOB;
	}
	
	public static Vector getListPraPerbicaraan() {
		return listPra;
	}

	public static Vector getPenghantarNotisByJkptg() {
		return penghantarNotisByJkptg;
	}

	public static Vector getPenghantarNotis() {
		return penghantarNotis;
	}

	public static Vector getListOBsemak() {
		return listOBsemak;
	}

	// -- 08122009
	public static Vector getCheckCetakAkuanSumpah() {
		return checkCetakAkuanSumpah;
	}
	
	// -- 18052017
		public static Vector getValidPegawaiPengendali() {
			return validPegPengendali;
		}

	// -- 23122009
	public static Vector getSelectedOB17() {
		return selectedOB17;
	}

	// seksyen 17
	public static Vector getListCarianSek17() {
		return listSek17;
	}

	public static Vector getListDefaultSek17() {
		return listDefaultSek17;
	}
	
	//aishahlatip
	public static Vector getCountSemuaOB() {
		return listCountOB;
	}
	
	//aishahlatip
	public static Vector getCountPenerimaNotis() {
		return listCountPenerimaNotis;
	}
		
	//aishahlatip
	public static Vector getListCanSendOB() {
		return listCanSendOB;
	}
	//aishahlatip
		public static Vector getListOBhidden() {
			return listOBhidden;
		}
	

		
		public static Vector idSimatitoSijilMati(String idsimati)
				throws Exception {
			myLogger.info("FrmPrmhnnSek8Notis.java");
			Db db = null;

			list.clear();
			String sql = "";

			try {

				db = new Db();
				Statement stmt = db.getStatement();
				sql = "SELECT NO_SIJIL_MATI FROM TBLPPKSIMATI WHERE ID_SIMATI="+idsimati;
				myLogger.info("sql idSimatitoSijilMati = "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Vector v = new Vector();
				Hashtable h;

				while (rs.next()) {

					h = new Hashtable();
					h.put("NO_SIJIL_MATI",
							rs.getString("NO_SIJIL_MATI") == null ? "" : rs
									.getString("NO_SIJIL_MATI"));
					v.addElement(h);
				}
				return v;
			} finally {
				if (db != null)
					db.close();
		}
		}
				
	// List permohonan Seksyen 8
	public static void setListDefault(String usid) throws Exception {

		Db db = null;
		listDefault.clear();
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();

			// SYARAT
			sql = "SELECT MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, "
					+ " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, " 
					
					//razman comment, query mcm ini adalah diharamkan sama sekali
					//+"(SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS  WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN, "
					
					+" SS.KETERANGAN AS STATUS_KEPUTUSAN, "
					
					
					+" M.NAMA_SIMATI "
					+ " FROM "
					
					+ " TBLRUJSTATUS SS "
					
					+" ,TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
					+ " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB "
					+ " WHERE "
					
					//RAZMAN ADD
					+ " SS.ID_STATUS(+) = KP.KEPUTUSAN_PERMOHONAN AND "
					
					+" D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ usid
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
					
					sql += " ) "
					+ " AND P.ID_STATUS = S.ID_STATUS "
					+ " AND P.ID_FAIL = F.ID_FAIL(+) "
					+ " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) "
					+ " AND P.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN "
					+ " AND M.ID_SIMATI = MS.ID_SIMATI "
					+ " AND P.ID_PEMOHON = PP.ID_PEMOHON "
					//+ " AND (P.ID_STATUS=151 OR P.ID_STATUS=53 OR P.ID_STATUS=18 OR P.ID_STATUS=44 OR P.ID_STATUS=173 OR P.ID_STATUS=175 OR P.ID_STATUS=177 OR P.ID_STATUS=166) "
					+ " AND P.ID_STATUS IN (151,53,18,44,173,175,177,166) "
					+ " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
					+ " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
					+ " AND STA.AKTIF = '1' "
					+ " AND P.ID_STATUS <> '999' "
					+ " AND P.SEKSYEN = '8' "
					+ " AND P.FLAG_JENIS_PERMOHONAN = '1' "
					//+ " AND SS.KETERANGAN LIKE 'PERMOHONAN DITERUSKAN%' "
					// +
					// " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)"
					+ " AND F.NO_FAIL IS NOT NULL ORDER BY STA.ID_SUBURUSANSTATUSFAIL desc, p.tarikh_kemaskini desc  ";
					
					System.out.println(" LIST NOTIS SEK 8 : "+sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;

			while (rs.next()) {

				h = new Hashtable();

				h.put("bil", bil);
				h.put("id_Permohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("id_status", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS"));
				h.put("id_Fail", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL"));
				h.put("no_Fail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL"));
				h.put("tarikhmohon", rs.getDate("TARIKH_MOHON") == null ? ""
						: Format.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getDate("TARIKH_MASUK") == null ? ""
						: Format.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar",
						rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : Format
								.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));
				h.put("id_simati", rs.getString("ID_SIMATI") == null ? "" : rs
						.getString("ID_SIMATI"));
				h.put("namasimati", rs.getString("NAMA_SIMATI") == null ? ""
						: rs.getString("NAMA_SIMATI"));

				listDefault.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}

	}// close default

	public static void setListDefaultFailLengkap(String usid) throws Exception {

		Db db = null;
		listDefault.clear();
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();

			// SYARAT
			sql = "SELECT MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, "
					+ " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
					+ " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI "
					+ " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
					+ " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ usid
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
					
					sql += " ) "
					+ " AND P.ID_STATUS = S.ID_STATUS "
					+ " AND P.ID_FAIL = F.ID_FAIL(+) "
					+ " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) "
					+ " AND P.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN "
					+ " AND M.ID_SIMATI = MS.ID_SIMATI "
					+ " AND P.ID_PEMOHON = PP.ID_PEMOHON "
					//+ " AND (P.ID_STATUS=151 OR P.ID_STATUS=53 OR P.ID_STATUS=18 OR P.ID_STATUS=44 OR P.ID_STATUS=173 OR P.ID_STATUS=175 OR P.ID_STATUS=177 OR P.ID_STATUS=166) "
					+ " AND P.ID_STATUS IN (151,53,18,44,173,175,177,166) "
					+ " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
					+ " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
					+ " AND STA.AKTIF = '1' "
					+ " AND P.ID_STATUS <> '999' "
					+ " AND P.SEKSYEN = '8' "
					+ " AND P.FLAG_JENIS_PERMOHONAN = '1' "
					//+ " AND SS.KETERANGAN LIKE 'PERMOHONAN DITERUSKAN%' "
					// +
					// " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)"
					+ " AND F.NO_FAIL IS NOT NULL ORDER BY STA.ID_SUBURUSANSTATUSFAIL desc, p.tarikh_kemaskini desc  ";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;

			while (rs.next()) {

				h = new Hashtable();

				h.put("bil", bil);
				h.put("id_Permohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("id_status", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS"));
				h.put("id_Fail", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL"));
				h.put("no_Fail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL"));
				h.put("tarikhmohon", rs.getDate("TARIKH_MOHON") == null ? ""
						: Format.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getDate("TARIKH_MASUK") == null ? ""
						: Format.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar",
						rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : Format
								.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));
				h.put("id_simati", rs.getString("ID_SIMATI") == null ? "" : rs
						.getString("ID_SIMATI"));
				h.put("namasimati", rs.getString("NAMA_SIMATI") == null ? ""
						: rs.getString("NAMA_SIMATI"));

				listDefault.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}

	}// close default
	
	
	// List carian Seksyen 8
	public static void setCarianFail(String noFail, String namaPemohon,
			String namaSimati, String icSimati, String JenisIc, String usid, String statusFail)
			throws Exception {

		Db db = null;

		list.clear();
		String sql = "";

		try {

			db = new Db();

			String chkDataFail = noFail.trim();
			String chkDataPemohon = namaPemohon.trim();
			String chkDataSimati = namaSimati.trim();
			String chkDataIcSimati = icSimati.trim();
			String chkDataJenisKp = JenisIc;
		    String chkstatusFail = statusFail;

			Statement stmt = db.getStatement();

			// SYARAT
			sql = "SELECT DISTINCT PP.NAMA_PEMOHON, MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, "
					+ " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
					+ " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI "
					+ " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
					+ " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ usid
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
					
					sql +=" ) "
					+ " AND P.ID_STATUS = S.ID_STATUS "
					+ " AND P.ID_FAIL = F.ID_FAIL(+) "
					+ " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) "
					+ " AND P.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN "
					+ " AND M.ID_SIMATI = MS.ID_SIMATI "
					+ " AND P.ID_PEMOHON = PP.ID_PEMOHON ";
					//+ " AND (P.ID_STATUS=151 OR P.ID_STATUS=53 OR P.ID_STATUS=18 OR P.ID_STATUS=44 OR P.ID_STATUS=173 OR P.ID_STATUS=175 OR P.ID_STATUS=177 OR P.ID_STATUS=166) "
					if (statusFail.equals("3")) {
						sql = sql + "AND P.ID_STATUS IN (151,53) ";
					}
					else
					{
						sql = sql + " AND P.ID_STATUS IN (151,53,18,44,173,175,177,166) ";
					}
					sql = sql + " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
					+ " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
					+ " AND STA.AKTIF = '1' "
					+ " AND P.ID_STATUS <> '999' "
					+ " AND P.SEKSYEN = '8' "
					+ " AND P.FLAG_JENIS_PERMOHONAN = '1' ";
			// + " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)";

			// NO FAIL
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%"
							+ chkDataFail.toUpperCase() + "%'";
				}
			}// close if nofail

			// NAMA PEMOHON
			if (namaPemohon != "") {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(PP.NAMA_PEMOHON) LIKE '%"
							+ chkDataPemohon.toUpperCase() + "%'";
				}
			}// close if pemohon

			// NAMA SIMATI
			if (namaSimati != "") {
				if (!namaSimati.trim().equals("")) {
					sql = sql + " AND UPPER(M.NAMA_SIMATI) LIKE '%"
							+ chkDataSimati.toUpperCase() + "%'";
				}
			}// close if nama simati

			// IC SIMATI
			if (icSimati != "") {
				if (!icSimati.trim().equals("")) {
					if (chkDataJenisKp.equals("1")) {
						sql = sql + " AND UPPER(M.NO_KP_BARU) LIKE '%"
								+ chkDataIcSimati.toUpperCase() + "%'";
					} else if (chkDataJenisKp.equals("2")) {
						sql = sql + " AND UPPER(M.NO_KP_LAMA) LIKE '%"
								+ chkDataIcSimati.toUpperCase() + "%'";
					} else if (chkDataJenisKp.equals("3")) {
						sql = sql + " AND UPPER(M.NO_KP_LAIN) LIKE '%"
								+ chkDataIcSimati.toUpperCase() + "%'";
					}
				}

			}// close if ic simati

			
			//statusFail
			if (statusFail != "") {
				myLogger.info("statusFailsadad ==" + statusFail);
				//myLogger.info("[chkstatusFail ==" + chkstatusFail);
				if (!statusFail.equals("")) {
					if (statusFail.equals("1") ){
						sql = sql + " AND KP.KEPUTUSAN_PERMOHONAN = '152'" ;
					}
					else if (statusFail.equals("2")){
						sql = sql + " AND KP.KEPUTUSAN_PERMOHONAN LIKE '53'";
					}
					else if (statusFail.equals("3") ){
						sql = sql + " AND KP.KEPUTUSAN_PERMOHONAN in ('151','53') " ;
					}
				}
				
			}//close if status Fail 
			
			// sorting
			// sql +=
			// " AND F.NO_FAIL IS NOT NULL ORDER BY P.TARIKH_KEMASKINI desc, STA.ID_SUBURUSANSTATUSFAIL DESC ";
			//sql += " AND F.NO_FAIL IS NOT NULL ORDER BY STA.ID_SUBURUSANSTATUSFAIL desc, p.tarikh_kemaskini desc  ";
			sql += " AND F.NO_FAIL IS NOT NULL ORDER BY f.id_fail desc, p.tarikh_kemaskini desc  ";
			
			System.out.println("SQL setCarianFail :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;

			while (rs.next()) {

				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("id_status", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS"));
				h.put("id_Fail", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL"));
				h.put("no_Fail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL"));
				h.put("tarikhmohon", rs.getString("TARIKH_MOHON") == null ? ""
						: Format.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK") == null ? ""
						: Format.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar",
						rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : Format
								.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));
				h.put("namasimati", rs.getString("NAMA_SIMATI") == null ? ""
						: rs.getString("NAMA_SIMATI"));
				h.put("id_simati", rs.getString("ID_SIMATI") == null ? "" : rs
						.getString("ID_SIMATI"));
				h.put("keputusan_permohonan", rs.getString("KEPUTUSAN_PERMOHONAN") == null ? "" : rs
						.getString("KEPUTUSAN_PERMOHONAN"));
				list.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}

	}// close carian

	// Data pemohon :: SEKSYEN 8 & SEKSYEN 17
	public static void setListSemak(String id_permohonan, String usid)
		throws Exception {
		Db db = null;

		listSemak.clear();
		String sql = "";

		try {
			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT distinct pm.id_negeri, n.id_Negeri, n.nama_Negeri,f.id_Fail, f.no_Fail, d.id_Daerah, p.id_Permohonan, ";
			sql += "p.tarikh_Mohon, s.no_Kp_Baru, s.no_Kp_Lama, s.jenis_Kp, s.no_Kp_Lain, s.id_Simati, ";
			sql += "s.nama_Simati, s.tarikh_Mati, pm.id_Pemohon, pm.nama_Pemohon, pm.no_kp_baru, ";
			sql += "pm.no_kP_lama, pm.jenis_Kp, pm.no_Kp_lain, pm.alamat_1, pm.alamat_2, pm.alamat_3, ";
			sql += "pm.poskod, pm.bandar, d.nama_Daerah, p.seksyen, st.keterangan, ";
			sql += "p.id_Status, mosi.id_Permohonansimati, s.umur, s.jantina, ur.id_negeri as id_negeri_jajahan, ";
			sql += "pm.umur, pm.jantina" +
					",u.id_pejabatjkptg,u.nama_pejabat, u.id_negeri as id_negeripejabat" +
					", p.no_subjaket" +
					//", dx.nama_daerah AS D_P" +
					", p.tarikh_rayuan ";
			sql += "FROM Tblpfdfail f,Tblppkpermohonan p, Tblrujnegeri n, Tblrujdaerah d, Tblppksimati s, ";
			sql += "Tblppkpemohon pm, Tblrujstatus st, tblrujsuburusanstatusfail sstf" +
					//", tblrujdaerah dx" +
					", tblrujsuburusanstatus sst ";
			sql += "" +
					//"tblrujpejabatjkptg u" +
					", Tblppkpermohonansimati mosi, Users_Internal ur ";
			sql += ",(SELECT RP.ID_PEJABATJKPTG,RP.NAMA_PEJABAT,RPU.ID_NEGERI,RPU.ID_DAERAHURUS ID_DAERAH FROM "+
					" TBLRUJPEJABATJKPTG RP, TBLRUJPEJABATURUSAN RPU "+
					" WHERE RP.ID_PEJABATJKPTG = RPU.ID_PEJABATJKPTG "+
					" AND RPU.ID_JENISPEJABAT = 22 "+
					" AND RP.ID_SEKSYEN = 2 "+
					//" AND RPU.ID_DAERAHURUS = 5 "+
					") U ";
			sql += "WHERE f.id_Negeri = n.id_Negeri(+) ";
			sql += "AND sstf.id_permohonan = p.id_permohonan ";
			sql += "AND sstf.id_suburusanstatus = sst.id_suburusanstatus ";
			sql += "AND sst.id_status = st.id_status ";
			sql += "AND p.id_Daerahmhn = d.id_Daerah(+) ";
			sql += "AND ur.user_id = '" + usid + "'";
			//sql += " And ur.ID_PEJABATJKPTG = u.ID_PEJABATJKPTG ";
			sql += " AND p.id_Fail = f.id_Fail ";
			sql += "AND p.ID_PEMOHON = pm.ID_PEMOHON ";
			sql += "AND s.id_Simati = mosi.id_Simati ";
			sql += "AND p.id_Permohonan = mosi.id_Permohonan ";
			sql += "AND st.id_Status = p.id_Status ";
			sql += "AND d.id_daerah = p.id_daerahmhn ";
			//sql += "AND u.id_daerah = dx.id_daerah(+) ";
			sql += "AND p.id_daerahmhn = u.id_daerah ";
			// sql += "AND sstf.aktif = '1' ";

			sql += " AND p.id_Permohonan = '" + id_permohonan + "'";
			System.out.println("sql*********** = " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("id_permohonansimati", rs
						.getString("id_Permohonansimati") == null ? "" : rs
						.getString("id_Permohonansimati"));
				// h.put("id_suburusanstatusfail",
				// rs.getString("id_suburusanstatusfail"));
				h.put("idFail", rs.getString("id_Fail") == null ? "" : rs
						.getString("id_Fail"));
				h.put("noFail", rs.getString("no_Fail") == null ? "" : rs
						.getString("no_Fail"));
				h.put("idDaerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("tarikhMohon", rs.getDate("tarikh_Mohon") == null ? ""
						: Format.format(rs.getDate("tarikh_Mohon")));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("namaSimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("tarikhMati", rs.getDate("tarikh_Mati") == null ? ""
						: Format.format(rs.getDate("tarikh_Mati")));
				h.put("idPemohon", rs.getString("id_Pemohon") == null ? "" : rs
						.getString("id_Pemohon"));
				h.put("namaPemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("idnegeri", rs.getString(16) == null ? "" : rs
						.getString(16));
				h.put("namanegeri", rs.getString("nama_Negeri") == null ? ""
						: rs.getString("nama_Negeri"));
				h.put("namadaerah", rs.getString("nama_Daerah") == null ? ""
						: rs.getString("nama_Daerah"));
				h.put("seksyen", rs.getString("seksyen") == null ? "" : rs
						.getString("seksyen"));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_Status", rs.getString("id_Status") == null ? "" : rs
						.getString("id_Status"));

				if (rs.getString("id_negeripejabat").equals("7")) {
					h.put("namaPejabat",
							rs.getString("nama_pejabat") == null ? "" : rs
									.getString("nama_pejabat"));
				} else {
//					h.put("namaPejabat", rs.getString("nama_pejabat") + ","
//							+ rs.getString("D_P") == null ? "" : rs
//							.getString("nama_pejabat")
//							+ "," + rs.getString("D_P"));
					h.put("namaPejabat"
							, rs.getString("nama_pejabat") + ","+ rs.getString("nama_Daerah") == null ? "" : rs.getString("nama_pejabat")+ "," + rs.getString("nama_Daerah"));
				}

				h.put("id_pejabatjkptg",
						rs.getString("id_pejabatjkptg") == null ? "" : rs
								.getString("id_pejabatjkptg"));
				h.put("tarikh_rayuan", rs.getDate("tarikh_rayuan") == null ? ""
						: Format.format(rs.getDate("tarikh_rayuan")));
				if (rs.getString(3) == null || rs.getString(3) == "") {
					h.put("pmNama_negeri", "");
				} else {
					h.put("pmNama_negeri", rs.getString(3));
				}

				h.put("pmidnegeri",
						rs.getString("id_negeri_jajahan") == null ? "" : rs
								.getString("id_negeri_jajahan"));

				listSemak.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}// close semak
	
	
	
	

	// Data Senarai Bicara
	public static void setListBicara(String idkp) throws Exception {

		Db db = null;

		listBicara.clear();
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();

			sql = "SELECT distinct b.id_perbicaraan, b.tarikh_bicara, b.masa_bicara, "
					+ " b.tempat_bicara, b.bil_bicara, b.alamat_bicara1, b.alamat_bicara2, "
					+ " b.alamat_bicara3, b.bandar, b.poskod, pt.keputusan_mahkamah, b.tarikh_notis, "
					+ " pt.flag_tangguh, pt.sebab_tangguh "
					+ " FROM Tblppkperbicaraan b, Tblppkperintah pt, Tblppkkeputusanpermohonan kp, Tblppkpermohonan p "
					+ " WHERE pt.id_perbicaraan = b.id_perbicaraan "
					+ " AND b.id_keputusanpermohonan = kp.id_keputusanpermohonan "
					+ " AND kp.id_permohonan = p.id_permohonan "
					+ " AND b.id_keputusanpermohonan = '"
					+ idkp
					+ "'"
					+ " AND pt.flag_tangguh is not null "
					+ " ORDER BY bil_bicara ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_perbicaraan",
						rs.getString("id_perbicaraan") == null ? "" : rs
								.getString("id_perbicaraan"));
				h.put("masa_bicara", rs.getString("masa_bicara") == null ? ""
						: rs.getString("masa_bicara"));
				h.put("tempat_bicara",
						rs.getString("tempat_bicara") == null ? "" : rs
								.getString("tempat_bicara"));
				h.put("bil_bicara", rs.getString("bil_bicara") == null ? ""
						: rs.getString("bil_bicara"));
				h.put("alamat_bicara1",
						rs.getString("alamat_bicara1") == null ? "" : rs
								.getString("alamat_bicara1"));
				h.put("alamat_bicara2",
						rs.getString("alamat_bicara2") == null ? "" : rs
								.getString("alamat_bicara2"));
				h.put("alamat_bicara3",
						rs.getString("alamat_bicara3") == null ? "" : rs
								.getString("alamat_bicara3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("tarikh_bicara", rs.getDate("tarikh_bicara") == null ? ""
						: Format.format(rs.getDate("tarikh_bicara")));
				h.put("tarikh_notis", rs.getDate("tarikh_notis") == null ? ""
						: Format.format(rs.getDate("tarikh_notis")));
				h.put("sebab_tangguh",
						rs.getString("sebab_tangguh") == null ? "-" : rs
								.getString("sebab_tangguh"));

				if (rs.getString("flag_tangguh") != null
						&& rs.getString("flag_tangguh") != "") {

					String flagT = rs.getString("flag_tangguh");
					int flag = Integer.parseInt(flagT);
					if (flag == 1) {
						h.put("flag_tangguh", "Pemohon / Waris Tidak Hadir ");
					} else if (flag == 2) {
						h.put("flag_tangguh", "Senarai Waris Tidak Lengkap");
					} else if (flag == 3) {
						h.put("flag_tangguh",
								"Menunggu Keputusan Rujukan Mahkamah Syariah ");
					} else if (flag == 4) {
						h.put("flag_tangguh", "Bukti Kematian Tidak Lengkap");
					} else if (flag == 5) {
						h
								.put("flag_tangguh",
										"Menunggu Keputusan Rujukan Kepada Ruler of The State atau Mahkamah Tinggi");
					} else if (flag == 6) {
						h.put("flag_tangguh", "Pertelingkahan Kolateral");
					} else if (flag == 7) {
						h.put("flag_tangguh", "Menunggu Sijil Faraid");
					} else if (flag == 8) {
						h.put("flag_tangguh",
								"Menunggu Surat Akaun Persetujuan");
					} else if (flag == 9) {
						h.put("flag_tangguh", "Sebab-sebab Lain");
					} else if (flag == 99) {
						h
								.put("flag_tangguh",
										"Perbicaraan Semula(rayuan dibenarkan oleh mahkamah)");
					}
				} else {
					h.put("flag_tangguh", "-");
				}

				if (rs.getString("keputusan_mahkamah") == null
						|| rs.getString("keputusan_mahkamah") == "") {
					h.put("keputusan_mahkamah", "-");
				} else {
					h.put("keputusan_mahkamah", rs
							.getString("keputusan_mahkamah"));
				}

				listBicara.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}// close bicara

	// Get alamat tempat bicara (pejabat kptg)
	public static Vector getAlamatTempatBicara(String idBicara)
			throws Exception {

		Db db = null;

		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();

			sql = "SELECT k.alamat1, k.alamat2, k.alamat3, k.poskod, k.id_negeri ";
			sql += "FROM Tblrujpejabatjkptg k ";
			sql += "WHERE k.id_pejabatjkptg = '" + idBicara + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("alamat1", rs.getString("alamat1") == null ? "" : rs
						.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2") == null ? "" : rs
						.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3") == null ? "" : rs
						.getString("alamat3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("id_negeri", rs.getString("id_negeri") == null ? "" : rs
						.getString("id_negeri"));

				list.addElement(h);

			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}// Get alamat tempat bicara
	
	//code wp 12/05/2017
	public boolean getTarikhValidation(String eventDate) throws Exception {

		Db db = null;

		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();

			sql = "select e.event_date, e.event_text from ";
			sql += "calendar_event e ";
			sql += "where e.event_id='Y' ";
			sql += "AND to_char(e.event_date,'dd/mm/yyyy')= '" + eventDate + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();

			Hashtable h;
			boolean flag = false;

			while (rs.next()) {
				flag = true;
				// h = new Hashtable();
				//
				// h.put("eventdate",rs.getString("event_date") == null ? "" :
				// rs.getString("event_date"));
				// h.put("event_text",rs.getString("event_text") == null ? "" :
				// rs.getString("event_text"));
				//
				// list.addElement(h);
				// return true;

			}
			return flag;
		} catch (Exception er) {
			myLogger.error(er);
			return false;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	

	// Get alamat tempat bicara (pejabat tanah)
	public static Vector getAlamatPejabatTanah(String idBicara)
			throws Exception {

		Db db = null;

		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();

			sql = "SELECT k.alamat1, k.alamat2, k.alamat3, k.poskod, k.id_negeri ";
			sql += "FROM Tblrujpejabat k ";
			sql += "WHERE k.id_pejabat = '" + idBicara + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("alamat1", rs.getString("alamat1") == null ? "" : rs
						.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2") == null ? "" : rs
						.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3") == null ? "" : rs
						.getString("alamat3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("id_negeri", rs.getString("id_negeri") == null ? "" : rs
						.getString("id_negeri"));

				list.addElement(h);

			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}// Get alamat tempat bicara

	// Get idkeputusanpermohonan :: SEKSYEN 8 & SEKSYEN 17 ::
	public static Vector getKeputusanPermohonan(String id_permohonan)
			throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();

			sql = "SELECT k.id_keputusanpermohonan, k.keputusan_permohonan ";
			sql += "FROM Tblppkkeputusanpermohonan k ";
			sql += "WHERE k.id_permohonan = '" + id_permohonan + "'";
			System.out.println("getKeputusanPermohonan :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();

			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_keputusanpermohonan", rs
						.getString("id_keputusanpermohonan") == null ? "" : rs
						.getString("id_keputusanpermohonan"));
				h.put("keputusan_permohonan", rs
						.getString("keputusan_permohonan") == null ? "" : rs
						.getString("keputusan_permohonan"));

				list.addElement(h);

			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}// Get idkeputusanpermohonan

	
	public static Date parseDateTime(String dateTxt) {
		if (dateTxt != null && !"".equals(dateTxt)) {
			try {
				return new SimpleDateFormat("dd/MM/yyyy hh:mm a")
						.parse(dateTxt);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}

	public static Date parseDate(String dateTxt) {
		if (dateTxt != null && !"".equals(dateTxt)) {
			try {
				return new SimpleDateFormat("dd/MM/yyyy").parse(dateTxt);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}

	public static void updateCalendarNotis(String event_id, String event_text,
			String tarikh_bicara, String user_id, String id_perbicaraan)
			throws Exception {

		Connection conn = null;
		Db db = null;
		String sql = "";
		String sql5 = "";
		String sql6 = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			SQLRenderer calendar_event = new SQLRenderer();
			String TB = "to_date('" + tarikh_bicara + "','dd/MM/yyyy')";
			SQLRenderer r = new SQLRenderer();
			calendar_event.update("ID_PERBICARAAN", id_perbicaraan);
			calendar_event.add("EVENT_ID", event_id);
			calendar_event.add("EVENT_TEXT", event_text);
			calendar_event.add("EVENT_DATE", r.unquote(TB));
			calendar_event.add("VIEW_SCOPE", "private");
			calendar_event.add("USER_ID", user_id);
			sql = calendar_event.getSQLUpdate("CALENDAR_EVENT");
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ se.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

	}

	public static void addCalendarNotis(HttpSession session, String event_id,
			String event_text, String tarikh_bicara, String user_id,
			String id_perbicaraan) throws Exception {

		Connection conn = null;
		Db db = null;
		String sql = "";
		String sql5 = "";
		String sql6 = "";

		try {

			db = new Db();

			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			SQLRenderer calendar_event = new SQLRenderer();
			String TB = "to_date('" + tarikh_bicara + "','dd/MM/yyyy')";
			SQLRenderer r = new SQLRenderer();
			calendar_event.add("EVENT_ID", event_id);
			calendar_event.add("EVENT_TEXT", event_text);
			calendar_event.add("EVENT_DATE", r.unquote(TB));
			calendar_event.add("VIEW_SCOPE", "private");
			calendar_event.add("USER_ID", user_id);
			calendar_event.add("ID_PERBICARAAN", id_perbicaraan);
			sql = calendar_event.getSQLInsert("CALENDAR_EVENT");
			myLogger.info("ADD CALENDER :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ se.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

	}

	// Simpan notis
	public static String addNotis(HttpSession session, Hashtable data)
			throws Exception {

		Connection conn = null;
		Db db = null;
		String sql = "";
		String sql5 = "";
		String sql6 = ""; 

		long id_perbicaraan = DB.getNextID("TBLPPKPERBICARAAN_SEQ");
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// generate other table id
			long id_notisobmst = DB.getNextID("TBLPPKNOTISOBMST_SEQ");
			
			idPerbicaraan = String.valueOf(id_perbicaraan);

			String id_permohonan = (String) data.get("id_permohonan");
			String id_fail = (String) data.get("id_fail");
			String id_suburusanstatusfail = (String) data
					.get("id_suburusanstatusfail");
			String id_keputusanpermohonan = (String) data
					.get("id_keputusanpermohonan");
			String id_ppkrujunit = (String) data.get("pegawai");
			String id_pejabatjkptg = (String) data.get("tempat_bicara");

			String jenispejabat = (String) data.get("jenispejabat");

			String negeri = (String) data.get("negeri");
			String id_masuk = (String) data.get("id_masuk");

			String tarikh_bicara = (String) data.get("tarikh_bicara");
			String masa_bicara = (String) data.get("masa_bicara");
			String alamat1 = (String) data.get("alamat_bicara1");
			String alamat2 = (String) data.get("alamat_bicara2");
			String alamat3 = (String) data.get("alamat_bicara3");
			String poskod = (String) data.get("poskod");
			String tarikh_notis = (String) data.get("tarikh_notis");

			// -- 09122009
			String socJenisWaktu = (String) data.get("socJenisWaktu");

			String nama_pegawai = "";
			String nama_pejabat = "";
			String id_pejabat = "";
			String tablePejabat = "";

			String firstNotis = "1";

			String TB = "to_date('" + tarikh_bicara + "','dd/MM/yyyy')";
			String TN = "to_date('" + tarikh_notis + "','dd/MM/yyyy')";

			// get nama pegawai
			SQLRenderer rMT = new SQLRenderer();
			rMT.add("id_unitpsk");
			rMT.add("nama_pegawai");
			rMT.add("id_unitpsk", id_ppkrujunit);
			sql = rMT.getSQLSelect("Tblppkrujunit");

			ResultSet rsMT = stmt.executeQuery(sql);
			while (rsMT.next()) {
				nama_pegawai = rsMT.getString("nama_pegawai");
			}

			String id_bandar = "";

			if (!jenispejabat.equals("0")) {

				// get pejabat
				if (jenispejabat.equals("22")) {
					id_pejabat = "id_pejabatjkptg";
					tablePejabat = "Tblrujpejabatjkptg";
				} else if (jenispejabat.equals("2")) {
					id_pejabat = "id_pejabat";
					tablePejabat = "Tblrujpejabat";
				} else {
					id_pejabat = "id_pejabatjkptg";
					tablePejabat = "Tblrujpejabatjkptg";
				}

				SQLRenderer rMP = new SQLRenderer();
				rMP.add("nama_pejabat");
				rMP.add("id_bandar");
				rMP.add(id_pejabat, id_pejabatjkptg);
				sql = rMP.getSQLSelect(tablePejabat);

				ResultSet rsP = stmt.executeQuery(sql);
				while (rsP.next()) {
					nama_pejabat = rsP.getString("nama_pejabat");
					id_bandar = rsP.getString("id_bandar");
				}
			} else {
				nama_pejabat = id_pejabatjkptg;
			}

			String keterangan = "";

			if (id_bandar != "") {
				SQLRenderer rb = new SQLRenderer();
				rb.add("keterangan");
				rb.add("id_bandar", id_bandar);
				sql = rb.getSQLSelect("Tblrujbandar");

				ResultSet rsPb = stmt.executeQuery(sql);
				while (rsPb.next()) {
					keterangan = rsPb.getString("keterangan");
				}
			}

			// add data (notis perbicaraan) with bil = 1
			SQLRenderer r = new SQLRenderer();
			r.add("id_perbicaraan", id_perbicaraan);
			r.add("id_keputusanpermohonan", id_keputusanpermohonan);
			r.add("id_unitpsk", id_ppkrujunit);
			r.add("bil_bicara", firstNotis);
			r.add("masa_bicara", masa_bicara);
			r.add("alamat_bicara1", alamat1);
			r.add("alamat_bicara2", alamat2);
			r.add("alamat_bicara3", alamat3);
			r.add("poskod", poskod);
			r.add("peg_pengendali", nama_pegawai);
			r.add("tempat_bicara", nama_pejabat);
			r.add("tarikh_bicara", r.unquote(TB));
			r.add("tarikh_notis", r.unquote(TN));
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("id_negeribicara", negeri);
			r.add("id_masuk", id_masuk);

			// -- 09122009
			r.add("jenis_masa_bicara", socJenisWaktu);

			if (keterangan != "") {
				r.add("bandar", keterangan);
			} else {
				r.add("bandar", "");
			}

			if (!jenispejabat.equals("0")) {
				r.add("id_pejabat", id_pejabatjkptg);
			} else {
				r.add("id_pejabat", "0");
			}

			r.add("id_jenispejabat", jenispejabat);
			sql = r.getSQLInsert("Tblppkperbicaraan");
			stmt.executeUpdate(sql);
			
			//aishahlatip tutup sebab penambahan baru untuk pilihan penyampai laporan 01082017
			// create table notisobmst
			SQLRenderer rMST = new SQLRenderer();
			rMST.add("id_notisobmst", id_notisobmst);
			rMST.add("id_masuk", id_masuk);
			rMST.add("tarikh_masuk", rMST.unquote("sysdate"));
			sql = rMST.getSQLInsert("Tblppknotisobmst");
			stmt.executeUpdate(sql);

			// create child table
			SQLRenderer r1 = new SQLRenderer();
			r1.add("id_perbicaraan", id_perbicaraan);
			r1.add("id_notisobmst", id_notisobmst);
			r1.add("flag_jenis_notis", 0);
			r1.add("id_masuk", id_masuk);
			r1.add("tarikh_masuk", r1.unquote("sysdate"));
			sql = r1.getSQLInsert("Tblppknotisperbicaraan");
			stmt.executeUpdate(sql);

			// tukar status permohonan diteruskan => notis perbicaraan
			int status_notisperbicaraan = 18;

			/*
			 * SQLRenderer rP = new SQLRenderer();
			 * rP.update("id_permohonan",id_permohonan); rP.add("id_status",
			 * status_notisperbicaraan); rP.add("id_kemaskini",id_masuk);
			 * rP.add("tarikh_kemaskini", rP.unquote("sysdate")); sql =
			 * rP.getSQLUpdate("Tblppkpermohonan"); stmt.executeUpdate(sql);
			 */

			// update n add tblrujsuburusanstatus
			/*
			 * SQLRenderer r6 = new SQLRenderer();
			 * r6.update("id_Suburusanstatusfail",id_suburusanstatusfail);
			 * r6.add("AKTIF",0); r6.add("ID_KEMASKINI",id_masuk);
			 * r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate")); sql6 =
			 * r6.getSQLUpdate("tblrujsuburusanstatusfail");
			 * stmt.executeUpdate(sql6);
			 * 
			 * 
			 * SQLRenderer r5 = new SQLRenderer();
			 * r5.add("ID_SUBURUSANSTATUSFAIL"
			 * ,DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			 * r5.add("ID_PERMOHONAN",id_permohonan); r5.add("ID_FAIL",id_fail);
			 * r5.add("ID_SUBURUSANSTATUS",354); r5.add("AKTIF",1);
			 * r5.add("ID_MASUK",id_masuk); r5.add("id_kemaskini",id_masuk);
			 * r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
			 * r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate")); sql5 =
			 * r5.getSQLInsert("tblrujsuburusanstatusfail");
			 * stmt.executeUpdate(sql5);
			 */

			conn.commit();

			Statement stmtfail = db.getStatement();
			SQLRenderer rfail = new SQLRenderer();
			String sql_fail = "SELECT F.NO_FAIL, S.NAMA_SIMATI  FROM  TBLPPKPERBICARAAN B, " +
					" TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERMOHONAN P,TBLPFDFAIL F , TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI M " +
					" WHERE B.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN " +
					" AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN " +
					" AND P.ID_FAIL = F.ID_FAIL " +
					" AND S.ID_SIMATI = M.ID_SIMATI " +
					" AND M.ID_PERMOHONAN = KP.ID_PERMOHONAN " +
					" AND B.ID_PERBICARAAN = '"+ id_perbicaraan + "'";
			ResultSet rsfail = stmtfail.executeQuery(sql_fail);
			String no_fail = "";
			String nama_simati= "";
			while (rsfail.next()) {
				
				if (rsfail.getString("NO_FAIL") != null) {
					no_fail = rsfail.getString("NO_FAIL");
				}
				
				if (rsfail.getString("NAMA_SIMATI") != null) {
					nama_simati = rsfail.getString("NAMA_SIMATI");
				}
			}

			/*
			 * r.add("bil_bicara", firstNotis); r.add("masa_bicara",
			 * masa_bicara); r.add("alamat_bicara1", alamat1);
			 * r.add("alamat_bicara2", alamat2); r.add("alamat_bicara3",
			 * alamat3); r.add("poskod", poskod); r.add("peg_pengendali",
			 * nama_pegawai); r.add("tempat_bicara", nama_pejabat);
			 */
			
			
			/** START ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE **/			
			String event_id = "PERBICARAAN PUSAKA KECIL BAGI FAIL " + no_fail;
			String event_text = "PERBICARAAN PUSAKA KECIL BAGI FAIL " + no_fail;
			String event_location = "";
			if (!"".equals(nama_pejabat))
				event_location = event_location + nama_pejabat;
			if (!"".equals(alamat1))
				event_location = event_location + ", " + alamat1;
			if (!"".equals(alamat2))
				event_location = event_location + ", " + alamat2;
			if (!"".equals(alamat3))
				event_location = event_location + ", " + alamat3;
			if (!"".equals(poskod))
				event_location = event_location + ", " + poskod;
			if (!"".equals(keterangan))
				event_location = event_location + " " + keterangan;			
			
			
			//########################## start insert calendar #############################
			String userLoginPegawai = "";
			Statement stmtPegawai = db.getStatement();
			//System.out.println("id_ppkrujunit========"+id_ppkrujunit);
			String sqlPegawai = "SELECT * FROM TBLPPKRUJUNIT, USERS WHERE UPPER(TBLPPKRUJUNIT.NAMA_PEGAWAI) = UPPER(USERS.USER_NAME) AND"
				+ " USERS.USER_TYPE = 'internal' AND TBLPPKRUJUNIT.ID_UNITPSK = '" + id_ppkrujunit + "'";
		
			//aishah edit on 5/5/2017
		/*	String sqlPegawai = "SELECT * FROM TBLPPKRUJUNIT, USERS WHERE TBLPPKRUJUNIT.USER_ID = USERS.USER_ID AND"
					+ " USERS.USER_TYPE = 'internal' AND TBLPPKRUJUNIT.ID_UNITPSK = '" + id_ppkrujunit + "'";*/
			System.out.println("sqlPegawai2========"+sqlPegawai);
			ResultSet rsPegawai = stmtPegawai.executeQuery(sqlPegawai);		
			if (rsPegawai.next()) {
				if (rsPegawai.getString("USER_LOGIN") != null) {
				userLoginPegawai = rsPegawai.getString("USER_LOGIN");
				
				//aishah edit 26072017
				System.out.println("userLoginPegawai========"+userLoginPegawai);
				myLogger.info("save event  >>>>>> userLoginPegawai : "+userLoginPegawai);
				myLogger.info("save event  >>>>>> id_perbicaraan : "+id_perbicaraan);
				myLogger.info("save event  >>>>>> event_text : "+event_text);
				myLogger.info("save event  >>>>>> event_location : "+event_location);
				myLogger.info("save event  >>>>>> tarikh_bicara : "+tarikh_bicara);
				myLogger.info("save event  >>>>>> masa_bicara : "+masa_bicara);
				myLogger.info("save event  >>>>>> socJenisWaktu : "+socJenisWaktu);
				
				

				
				
				
				
				saveActivityEvent(userLoginPegawai, id_perbicaraan, event_text, event_location, tarikh_bicara, masa_bicara, socJenisWaktu);
				
				}
			}
			
			/** END ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE **/

//			 addCalendarNotis(session, event_id, event_text, tarikh_bicara,id_ppkrujunit, id_perbicaraan + "");
			// saveActivityEvent(session,"09:00 AM","09:30 AM",event_text,nama_pejabat,
			// remark,
			// tarikh_bicara,user_login);
			//########################## end insert calendar #############################
			// :::SUB
			// ID_STATUS : status_notisperbicaraan
			// ID_SUBURUSAN : 354
			FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
			logic_A.kemaskiniSubUrusanStatusFail(session, id_permohonan,
					id_masuk, status_notisperbicaraan + "", "354", id_fail);
			

			 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			 Date date = new Date();  
			 myLogger.info("sysdate ==== "+formatter.format(date));  
			 if ((tarikh_bicara.compareTo(formatter.format(date)) > 0 ) || (tarikh_bicara.compareTo(formatter.format(date)) == 0 ) ){
				 myLogger.info("send email  >>>>>> tarikh_bicara : ");
				 hantarEmelTTPegawai(session,Long.toString(id_perbicaraan),no_fail,nama_simati);
				 
				//###############add send email#########################
				//hantarEmel(session,Long.toString(id_perbicaraan),no_fail,nama_simati);
			 }
		

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ se.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
		return idPerbicaraan;

	}// close addNotis
	
	/** ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE 
	 * @param description 
	 * @param locationRemark 
	 * @param eventDateStart_ 
	 * @param masa_bicara 
	 * @param jenisWaktu 
	 * @param idUnitPSK 
	 * @param db 
	 * @throws Exception **/

	private static void saveActivityEvent(String userLoginPegawai, Long idPerbicaraan, String description, String locationRemark, String tarikhMula, String masa_bicara, String jenisWaktu) throws Exception {
		System.out.println("sini lepas xxxxxxxxxxxxxxxx==="+idPerbicaraan);
		DbPersistence db = new DbPersistence();
		System.out.println("idPerbicaraan==="+idPerbicaraan);
		System.out.println("userLoginPegawai==="+userLoginPegawai);
		deleteUserActivityEvent(idPerbicaraan);
		System.out.println("----------------------done delete------------------");
		//find UserActivityEvent
		UserActivityEvent userActivityEvent = (UserActivityEvent) db.get("select u from UserActivityEvent u where u.userLogin = '" + userLoginPegawai + "'");
		
		System.out.println("select u from UserActivityEvent u where u.userLogin = '" + userLoginPegawai + "'");
		
		if ( userActivityEvent == null ) {
			db.begin();
			userActivityEvent = new UserActivityEvent();
			userActivityEvent.setUserLogin(userLoginPegawai);
			db.persist(userActivityEvent);
			db.commit();
		}		
		System.out.println("----------------------1------------------");
		String displayColor = "#FFCCCC";
				
		String jamBicara  = "";
		String minitBicara = "";
		String startTime = "";
		String endTime = "";
		System.out.println("----------------------2------------------");
		if (!"".equals(masa_bicara)){
			
			jamBicara = masa_bicara.substring(0, 2);
			minitBicara = masa_bicara.substring(2, 4);
			System.out.println("----------------------3------------------");
			/*if (Integer.valueOf(minitBicara) < 10){
				System.out.println("----------------------3A------------------");
				minitBicara = "00";
			} else if (Integer.valueOf(minitBicara) >= 10 && Integer.valueOf(minitBicara) < 20){
				System.out.println("----------------------3B------------------");
				minitBicara = "10";
			} else if (Integer.valueOf(minitBicara) >= 20 && Integer.valueOf(minitBicara) < 30){
				System.out.println("----------------------3C------------------");
				minitBicara = "20";
			} else if (Integer.valueOf(minitBicara) >= 30 && Integer.valueOf(minitBicara) < 40){
				System.out.println("----------------------3D------------------");
				minitBicara = "30";
			} else if (Integer.valueOf(minitBicara) >= 40 && Integer.valueOf(minitBicara) < 50){
				System.out.println("----------------------3E------------------");
				minitBicara = "40";
			} else if (Integer.valueOf(minitBicara) >= 50 && Integer.valueOf(minitBicara) < 60){
				System.out.println("----------------------3F------------------");
				minitBicara = "50";
			}*/
			System.out.println("----------------------4------------------");
			int jamTamat = Integer.valueOf(jamBicara);
			jamTamat = jamTamat + 1;
			System.out.println("----------------------5------------------");
			int minitTamat = Integer.valueOf(minitBicara);
			System.out.println("----------------------6------------------");
			startTime = jamBicara + ":" + minitBicara;
			if ("1".equals(jenisWaktu)){
				startTime = startTime + " AM";
				
				if (jamTamat < 12) {
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}						
					endTime = endTime + ":" + minitBicara + " AM";
				} else if (jamTamat == 12) {
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}						
					endTime = endTime + ":" + minitBicara + " PM";
				} else {
					jamTamat = jamTamat - 12;
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}						
					endTime = endTime + ":" + minitBicara + " PM";
				}
					
			} else {
				startTime = startTime + " PM";
				
				if (jamTamat < 12) {
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}						
					endTime = endTime + ":" + minitBicara + " PM";
				} else if (jamTamat < 12) {
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}						
					endTime = endTime + ":" + minitBicara + " PM";
				} else {
					jamTamat = jamTamat - 12;
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}						
					endTime = endTime + ":" + minitBicara + " PM";
				}
			}			
		}		
		System.out.println("----------------------7------------------");
		Date startDateTime = parseDateTime(tarikhMula + " " + startTime);
		System.out.println("----------------------8------------------");
		String eventDateEnd_ = tarikhMula;
		Date endDateTime = parseDateTime(eventDateEnd_ + " " + endTime);	
		System.out.println("----------------------9------------------");
		db.begin();
		ActivityEvent activityEvent = (ActivityEvent) db.get("select a from ActivityEvent a where a.idPerbicaraan = '" + Long.valueOf(idPerbicaraan) + "'");
		System.out.println("----------------------10------------------");
		if ( activityEvent == null ) {
			activityEvent = new ActivityEvent();
			activityEvent.setDescription(description);
			activityEvent.setEventDate(parseDate(tarikhMula));
			activityEvent.setStartDateTime(startDateTime);
			activityEvent.setEndDateTime(endDateTime);
			activityEvent.setLocationRemark(locationRemark);
			activityEvent.setRemark("");
			activityEvent.setDisplayColor(displayColor);
			activityEvent.setIdPerbicaraan(idPerbicaraan);
			activityEvent.setUserActivityEvent(userActivityEvent);
			activityEvent.setCreateDate(new Date());
			db.persist(activityEvent);
			if ( userActivityEvent.getEvents() == null ) userActivityEvent.setEvents(new ArrayList<ActivityEvent>());
			userActivityEvent.getEvents().add(activityEvent);
			
		} else {
			activityEvent.setDescription(description);
			activityEvent.setEventDate(parseDate(tarikhMula));
			activityEvent.setStartDateTime(startDateTime);
			activityEvent.setEndDateTime(endDateTime);
			activityEvent.setLocationRemark(locationRemark);
			activityEvent.setRemark("");
			activityEvent.setDisplayColor(displayColor);
			activityEvent.setIdPerbicaraan(idPerbicaraan);
		}			
		
		try {
			db.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void deleteUserActivityEvent(Long idPerbicaraan) throws Exception {
		
		Db db = null;
		String sql = "";

		try {			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "DELETE FROM TBLACTIVITYEVENT WHERE ID_PERBICARAAN = '" + idPerbicaraan + "'";
			System.out.println("sql deleteUserActivityEvent= "+sql);
			stmt.execute(sql);
			
		} finally {
			if (db != null)
				db.close();
		}		
	}

	// Update notis
	public static void updateNotis(HttpSession session, Hashtable data) throws Exception {
		System.out.println("updateNotis");
		Db db = null;
		String sql = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			String id_perbicaraan = (String) data.get("id_perbicaraan");
			String id_keputusanpermohonan = (String) data
					.get("id_keputusanpermohonan");

			String id_ppkrujunit = (String) data.get("pegawai");
			String id_pejabatjkptg = (String) data.get("tempat_bicara");

			String id_status = (String) data.get("id_status");
			String id_kemaskini = (String) data.get("id_kemaskini");

			String negeri = (String) data.get("negeri");

			String tarikh_bicara = (String) data.get("tarikh_bicara");
			String masa_bicara = (String) data.get("masa_bicara");
			String alamat1 = (String) data.get("alamat_bicara1");
			String alamat2 = (String) data.get("alamat_bicara2");
			String alamat3 = (String) data.get("alamat_bicara3");
			String poskod = (String) data.get("poskod");
			String tarikh_notis = (String) data.get("tarikh_notis");

			String jenispejabat = (String) data.get("jenispejabat");

			// -- 09122009
			String socJenisWaktu = (String) data.get("socJenisWaktu");
			String sebab_pinda_tarikh = (String) data.get("sebab_pinda_tarikh");
			String tarikh_bicara_dahulu = (String) data.get("tarikh_bicara_dahulu");
			
			String nama_pegawai = "";
			String nama_pejabat = "";
			String id_pejabat = "";
			String tablePejabat = "";
			String TBD = "";
			String TB = "to_date('" + tarikh_bicara + "','dd/MM/yyyy')";
			String TN = "to_date('" + tarikh_notis + "','dd/MM/yyyy')";
			if (tarikh_bicara_dahulu != "") {
				TBD = "to_date('" + tarikh_bicara_dahulu + "','dd/MM/yyyy')";
			}
			
			
			// get nama pegawai
			SQLRenderer rMT = new SQLRenderer();
			rMT.add("id_unitpsk");
			rMT.add("nama_pegawai");
			rMT.add("nama_pejabat");
			rMT.add("id_unitpsk", id_ppkrujunit);
			sql = rMT.getSQLSelect("Tblppkrujunit");

			ResultSet rsMT = stmt.executeQuery(sql);
			while (rsMT.next()) {
				nama_pegawai = rsMT.getString("nama_pegawai");
			}

			String id_bandar = "";

			if (!jenispejabat.equals("0")) {

				// get nama pejabat
				if (jenispejabat.equals("22")) {
					id_pejabat = "id_pejabatjkptg";
					tablePejabat = "Tblrujpejabatjkptg";
				} else if (jenispejabat.equals("2")) {
					id_pejabat = "id_pejabat";
					tablePejabat = "Tblrujpejabat";
				} else {
					id_pejabat = "id_pejabatjkptg";
					tablePejabat = "Tblrujpejabatjkptg";
				}

				SQLRenderer rMP = new SQLRenderer();
				rMP.add("nama_pejabat");
				rMP.add("id_bandar");
				rMP.add(id_pejabat, id_pejabatjkptg);
				sql = rMP.getSQLSelect(tablePejabat);

				ResultSet rsP = stmt.executeQuery(sql);
				while (rsP.next()) {
					nama_pejabat = rsP.getString("nama_pejabat");
					id_bandar = rsP.getString("id_bandar");
				}

			} else {
				nama_pejabat = id_pejabatjkptg;
			}

			String keterangan = "";

			if (id_bandar != "") {
				SQLRenderer rb = new SQLRenderer();
				rb.add("keterangan");
				rb.add("id_bandar", id_bandar);
				sql = rb.getSQLSelect("Tblrujbandar");

				ResultSet rsPb = stmt.executeQuery(sql);
				while (rsPb.next()) {
					keterangan = rsPb.getString("keterangan");
				}
			}

			SQLRenderer r = new SQLRenderer();
			r.update("id_perbicaraan", id_perbicaraan);
			r.add("masa_bicara", masa_bicara);
			r.add("alamat_bicara1", alamat1);
			r.add("alamat_bicara2", alamat2);
			r.add("alamat_bicara3", alamat3);
			r.add("id_unitpsk", id_ppkrujunit);
			r.add("poskod", poskod);
			r.add("peg_pengendali", nama_pegawai);
			r.add("tempat_bicara", nama_pejabat);
			r.add("tarikh_bicara", r.unquote(TB));
			r.add("tarikh_notis", r.unquote(TN));
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			r.add("id_negeribicara", negeri);
			r.add("id_kemaskini", id_kemaskini);
			r.add("sebab_pinda_tarikh", sebab_pinda_tarikh);
			if (tarikh_bicara_dahulu != "") {
				r.add("tarikh_bicara_dahulu", r.unquote(TBD));
			}
			
			// -- 09122009
			r.add("jenis_masa_bicara", socJenisWaktu);

			if (keterangan != "") {
				r.add("bandar", keterangan);
			} else {
				r.add("bandar", "");
			}

			if (!jenispejabat.equals("0")) {
				r.add("id_pejabat", id_pejabatjkptg);
			} else {
				r.add("id_pejabat", "0");
			}

			r.add("id_jenispejabat", jenispejabat);
			sql = r.getSQLUpdate("Tblppkperbicaraan");
			System.out.println("sql updateNotis = "+sql);
			stmt.executeUpdate(sql);

			Statement stmtfail = db.getStatement();
			SQLRenderer rfail = new SQLRenderer();
			
			String sql_fail = "SELECT F.NO_FAIL, S.NAMA_SIMATI  FROM  TBLPPKPERBICARAAN B, " +
					" TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERMOHONAN P,TBLPFDFAIL F , TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI M " +
					" WHERE B.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN " +
					" AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN " +
					" AND P.ID_FAIL = F.ID_FAIL " +
					" AND S.ID_SIMATI = M.ID_SIMATI " +
					" AND M.ID_PERMOHONAN = KP.ID_PERMOHONAN " +
					" AND B.ID_PERBICARAAN = '"+ id_perbicaraan + "'";
			ResultSet rsfail = stmtfail.executeQuery(sql_fail);
			String no_fail = "";
			String nama_simati = "";
			while (rsfail.next()) {
				if (rsfail.getString("NO_FAIL") != null) {
					no_fail = rsfail.getString("NO_FAIL");
				}
				
				if (rsfail.getString("NAMA_SIMATI") != null) {
					nama_simati = rsfail.getString("NAMA_SIMATI");
				}
			}

			/** START ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE **/
			
			String event_id = "PERBICARAAN PUSAKA KECIL BAGI FAIL " + no_fail;
			String event_text = "PERBICARAAN PUSAKA KECIL BAGI FAIL " + no_fail;
			String event_location = "";
			if (!"".equals(nama_pejabat))
				event_location = event_location + nama_pejabat;
			if (!"".equals(alamat1))
				event_location = event_location + ", " + alamat1;
			if (!"".equals(alamat2))
				event_location = event_location + ", " + alamat2;
			if (!"".equals(alamat3))
				event_location = event_location + ", " + alamat3;
			if (!"".equals(poskod))
				event_location = event_location + ", " + poskod;
			if (!"".equals(keterangan))
				event_location = event_location + " " + keterangan;			
			
			String userLoginPegawai = "";
			Statement stmtPegawai = db.getStatement();
			
			String sqlPegawai = "SELECT * FROM TBLPPKRUJUNIT, USERS WHERE UPPER(TBLPPKRUJUNIT.NAMA_PEGAWAI) = UPPER(USERS.USER_NAME) AND"
				+ " USERS.USER_TYPE = 'internal' AND TBLPPKRUJUNIT.ID_UNITPSK = '" + id_ppkrujunit + "' order by USER_LOGIN asc";
			
			
			System.out.println("sqlPegawai1=="+sqlPegawai);
			ResultSet rsPegawai = stmtfail.executeQuery(sqlPegawai);		
			if (rsPegawai.next()) {
				if (rsPegawai.getString("USER_LOGIN") != null) {
					userLoginPegawai = rsPegawai.getString("USER_LOGIN");
					System.out.println("userLoginPegawai=="+userLoginPegawai);
					//aishah edit 26072017
					System.out.println("id_perbicaraan=="+id_perbicaraan);
					System.out.println("masa_bicara=="+masa_bicara);
					saveActivityEvent(userLoginPegawai, Long.valueOf(id_perbicaraan), event_text, event_location, tarikh_bicara, masa_bicara, socJenisWaktu);
					/** END ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE **/
				}
			}
			
			
			
			 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			 Date date = new Date();  
			 myLogger.info("sysdate ==== "+formatter.format(date));  
			 if ((tarikh_bicara.compareTo(formatter.format(date)) > 0 ) || (tarikh_bicara.compareTo(formatter.format(date)) == 0 ) ){
				 myLogger.info("send email  >>>>>> tarikh_bicara : ");
				 hantarEmelTTPegawai(session,id_perbicaraan,no_fail,nama_simati);
				 
				//###############add send email#########################
				//hantarEmel(session,Long.toString(id_perbicaraan),no_fail,nama_simati);
			 }
			
		}// close try

		finally {
			if (db != null)
				db.close();
		}// close finally

	}// close updateNotis


	// GET DATA NOTIS :: SEKSYEN 8 & SEKSYEN 17 ::
	public static void setListSemakWithData(String id_keputusanpermohonan)
			throws Exception {

		Db db = null;

		listSemakWithData.clear();
		String sql = "";
		String sql2 = "";
		String id_perbicaraan = "";

		try {

			// get latest notis by bil bicara

			db = new Db();
			Statement stmt = db.getStatement();

			// sql = "SELECT distinct max(b.id_perbicaraan)as id_perbicaraan ";
			// sql +="FROM Tblppkperbicaraan b ";
			// sql +="WHERE b.id_keputusanpermohonan = '" +
			// id_keputusanpermohonan + "'";

			// //Alter by elly on 17 April,to handle case where max id
			// perbicaraan is not really the
			// latest perbicaraan
			sql = " SELECT MAX(id_perbicaraan) as id_perbicaraan FROM (";
			sql += " SELECT distinct a.id_perbicaraan FROM Tblppkperbicaraan a ";
			sql += " WHERE ";
			sql += " tarikh_masuk = (select max(tarikh_masuk) from tblppkperbicaraan b where b.id_keputusanpermohonan=a.id_keputusanpermohonan) ";
			sql += " and a.id_keputusanpermohonan = '" + id_keputusanpermohonan
					+ "'";
			sql += " ) ";
			System.out.println("setListSemakWithDataA :"+sql);
			ResultSet rsMT = stmt.executeQuery(sql);
			while (rsMT.next()) {
				id_perbicaraan = rsMT.getString("id_perbicaraan");
			}

			SQLRenderer r = new SQLRenderer();
			r.add("pb.id_perbicaraan");
			r.add("pb.id_unitpsk");
			r.add("pb.id_keputusanpermohonan");
			r.add("pb.tarikh_notis");
			r.add("pb.tarikh_bicara");
			r.add("pb.masa_bicara");

			r.add("pb.tempat_bicara");
			r.add("pb.bil_bicara");
			r.add("pb.alamat_bicara1");
			r.add("pb.alamat_bicara2");
			r.add("pb.alamat_bicara3");

			r.add("pb.poskod");
			r.add("pb.id_negeribicara");
			r.add("pb.peg_pengendali");
			r.add("pb.tarikh_masuk");

			// -- 09122009
			r.add("pb.jenis_masa_bicara");

			r.add("pb.id_pejabat");
			r.add("pb.id_jenispejabat");
			r.add("pb.SIGNED_TEXT");//aishahlatip add 16/07/2018
			r.add("pb.tarikh_bicara_dahulu");
			r.add("pb.sebab_pinda_tarikh");
			
			r.add("pb.id_keputusanpermohonan", id_keputusanpermohonan);
			r.add("pb.id_perbicaraan", id_perbicaraan);

			sql2 = r.getSQLSelect("Tblppkperbicaraan pb");
			System.out.println("setListSemakWithDataB :"+sql2);
			ResultSet rs = stmt.executeQuery(sql2);
			Hashtable h;

			while (rs.next()) {

				h = new Hashtable();
				h.put("id_perbicaraan",
						rs.getString("id_perbicaraan") == null ? "" : rs
								.getString("id_perbicaraan"));
				h.put("id_unitpsk", rs.getString("id_unitpsk") == null ? ""
						: rs.getString("id_unitpsk"));
				h.put("tarikh_notis", rs.getDate("tarikh_notis") == null ? ""
						: Format.format(rs.getDate("tarikh_notis")));
				h.put("tarikh_bicara", rs.getDate("tarikh_bicara") == null ? ""
						: Format.format(rs.getDate("tarikh_bicara")));

				h.put("masa_bicara", rs.getString("masa_bicara") == null ? ""
						: rs.getString("masa_bicara"));
				h.put("tempat_bicara",
						rs.getString("tempat_bicara") == null ? "" : rs
								.getString("tempat_bicara"));
				h.put("bil_bicara", rs.getString("bil_bicara") == null ? ""
						: rs.getString("bil_bicara"));
				h.put("alamat_bicara1",
						rs.getString("alamat_bicara1") == null ? "" : rs
								.getString("alamat_bicara1"));
				h.put("alamat_bicara2",
						rs.getString("alamat_bicara2") == null ? "" : rs
								.getString("alamat_bicara2"));
				h.put("alamat_bicara3",
						rs.getString("alamat_bicara3") == null ? "" : rs
								.getString("alamat_bicara3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("id_negeribicara",
						rs.getString("id_negeribicara") == null ? "" : rs
								.getString("id_negeribicara"));
				h.put("peg_pengendali",
						rs.getString("peg_pengendali") == null ? "" : rs
								.getString("peg_pengendali"));
				h.put("id_pejabat", rs.getString("id_pejabat") == null ? ""
						: rs.getString("id_pejabat"));
				h.put("id_jenispejabat",
						rs.getString("id_jenispejabat") == null ? "" : rs
								.getString("id_jenispejabat"));

				// -- 09122009
				h.put("jenis_masa_bicara",rs.getString("jenis_masa_bicara") == null ? "" : rs.getString("jenis_masa_bicara"));
				
				//16/7/2018 aishahlatip
				h.put("SIGNED_TEXT",rs.getString("SIGNED_TEXT") == null ? "" : rs.getString("SIGNED_TEXT"));
				h.put("tarikh_bicara_dahulu", rs.getDate("tarikh_bicara_dahulu") == null ? ""
						: Format.format(rs.getDate("tarikh_bicara_dahulu")));
				h.put("sebab_pinda_tarikh",rs.getString("sebab_pinda_tarikh") == null ? "" : rs.getString("sebab_pinda_tarikh"));
				

				listSemakWithData.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}

	}// close semakwithdata

	// LIST OB :: SEKSYEN 8 & SEKSYEN 17 ::
	public static void setListOB(String id_permohonansimati, String id_simati)
			throws Exception {

		Db db = null;
		listOB.clear();
		String sql = "";
		String icYEAR = "";
		String icMONTH = "";
		String icDAY = "";
		String MONTH = "";
		String x = "";
		String year = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ob.id_ob, ob.id_simati, ob.nama_ob, ob.no_kp_baru, ob.no_kp_lama, ";
			sql += "ob.status_hidup, ob.jenis_kp, ob.no_kp_lain, ob.no_surat_beranak, ";
			sql += "ob.tarikh_lahir, ob.jantina, ob.id_tarafkptg, ob.umur, tr.keterangan ";
			sql += "FROM Tblppkob ob1,Tblppkobpermohonan ob, Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
			sql += "WHERE ob1.id_ob = ob.id_ob and ob.id_permohonansimati = '"
					+ id_permohonansimati + "' " + ""
					+ " and ob1.id_permohonansimati = sm.id_permohonansimati ";
			sql += " AND sm.id_permohonan = p.id_permohonan ";
			sql += " AND ob.id_tarafkptg = tr.id_tarafkptg ";
			// sql += "AND ob.status_ob != ALL(3,4) ";
			sql += " AND ob.id_tarafkptg != 14 ";
			sql += " AND p.id_status <> '169' ";
			sql += " AND NVL(ob.status_hidup,0) = 0 ";
			sql += " AND ob.id_simati = '" + id_simati + "'";
			sql += " AND p.no_subjaket <= '"
					+ getNoSubjaketByIdPermohonan(getIdPermohonanByIdPermohonanSimati(id_permohonansimati))
					+ "'";
			// sql += " AND ob.id_permohonansimati <= '" + id_permohonansimati +
			// "'";
			sql += " ORDER BY ob.umur desc,ob.nama_ob asc ";
			myLogger.info("setListOB :" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;

			while (rs.next()) {

				h = new Hashtable();

				if (rs.getString("umur") != null && rs.getString("umur") != "") {

					h.put("age", rs.getInt("umur"));
				} else if (rs.getString("no_kp_baru") != null
						&& rs.getString("no_kp_baru") != "") {

					x = rs.getString("no_kp_baru").substring(0, 2);

					int z = 0;

					if (x != "") {
						z = Integer.parseInt(x);
					}

					icYEAR = rs.getString("no_kp_baru").substring(0, 2);
					icMONTH = rs.getString("no_kp_baru").substring(2, 4);
					icDAY = rs.getString("no_kp_baru").substring(4, 6);

					if (z <= 20) {
						year = "20" + icYEAR;
					} else {
						year = "19" + icYEAR;
					}

					int y = Integer.parseInt(year);
					int m = Integer.parseInt(icMONTH) - 1;
					int d = Integer.parseInt(icDAY);

					Calendar cal = new GregorianCalendar(y, m, d);
					Calendar now = new GregorianCalendar();

					int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
					if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
							|| (cal.get(Calendar.MONTH) == now
									.get(Calendar.MONTH) && cal
									.get(Calendar.DAY_OF_MONTH) > now
									.get(Calendar.DAY_OF_MONTH))) {
						res--;
					}

					int notnull = 0;
					int cantzero = 1;

					if (res < 0) {
						h.put("age", notnull);
					} else if (res == 0) {
						h.put("age", cantzero);
					} else {
						h.put("age", res);
					}

				} else {
					h.put("age", "");
				}

				h.put("bil", bil);
				h.put("no_kp_baru1", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(0, 6));
				h.put("no_kp_baru2", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(6, 8));
				h.put("no_kp_baru3", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(8, 12));
				h.put("id_ob", rs.getString("id_ob") == null ? "" : rs
						.getString("id_ob"));
				h.put("id_simati", rs.getString("id_simati") == null ? "" : rs
						.getString("id_simati"));
				h.put("nama_ob", rs.getString("nama_ob") == null ? "" : rs
						.getString("nama_ob"));
				h.put("no_kp_baru", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru"));
				h.put("no_kp_lama", rs.getString("no_kp_lama") == null ? ""
						: rs.getString("no_kp_lama"));
				h.put("jenis_kp", rs.getString("jenis_kp") == null ? "" : rs
						.getString("jenis_kp"));
				h.put("no_kp_lain", rs.getString("no_kp_lain") == null ? ""
						: rs.getString("no_kp_lain"));
				h.put("no_surat_beranak",
						rs.getString("no_surat_beranak") == null ? "" : rs
								.getString("no_surat_beranak"));
				h.put("tarikh_lahir", rs.getString("tarikh_lahir") == null ? ""
						: rs.getString("tarikh_lahir"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				// h.put("id_tarafkptg", rs.getString("id_tarafkptg"));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));

				listOB.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}// close OB
	
	//
	public static void setTblppkpraperbicaraan(String idPermohonan) throws Exception {
		Db db = null;
		listPra.clear();
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT * FROM TBLPPKPRAPERBICARAAN WHERE ID_PERMOHONAN = "+idPermohonan;
			
			myLogger.info("setTblppkpraperbicaraan :" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;

			while (rs.next()) {

				h = new Hashtable();

				h.put("tarikh_inkuiri", rs.getDate("tarikh_inkuiri") == null ? ""
						: Format.format(rs.getDate("tarikh_inkuiri")));
				h.put("sebab_inkuiri", rs.getString("sebab_inkuiri") == null ? ""
						: rs.getString("sebab_inkuiri"));
				h.put("catatan_sebabinkuiri", rs.getString("catatan_sebabinkuiri") == null ? ""
						: rs.getString("catatan_sebabinkuiri"));
				h.put("catatan_notis", rs.getString("catatan_notis") == null ? "" : rs
						.getString("catatan_notis"));
				h.put("alamat1", rs.getString("alamat_bicara1") == null ? "" : rs
						.getString("alamat_bicara1"));
				h.put("alamat2", rs.getString("alamat_bicara2") == null ? "" : rs
						.getString("alamat_bicara2"));
				h.put("alamat3", rs.getString("alamat_bicara3") == null ? "" : rs
						.getString("alamat_bicara3"));
				h.put("poskod", rs.getString("poskod_bicara") == null ? "" : rs
						.getString("poskod_bicara"));
				h.put("idnegeri", rs.getString("idnegeri_bicara") == null ? "" : rs
						.getString("idnegeri_bicara"));
				h.put("masa_bicara", rs.getString("masa_bicara") == null ? "" : rs
						.getString("masa_bicara"));
				h.put("jenis_masa_bicara", rs.getString("jenis_masa_bicara") == null ? "" : rs
						.getString("jenis_masa_bicara"));
				h.put("keputusan_praperbicaraan", rs.getString("keputusan_praperbicaraan") == null ? "" : rs
						.getString("keputusan_praperbicaraan"));
				h.put("catatan_keputusan", rs.getString("catatan_keputusan") == null ? "" : rs
						.getString("catatan_keputusan"));
				

				listPra.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public static void setTblppkpraperbicaraan2(String idFail) throws Exception {
		Db db = null;
		listPra.clear();
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT * FROM TBLPPKPRAPERBICARAAN WHERE ID_PERMOHONAN in (SELECT ID_PERMOHONAN FROM TBLPPKPERMOHONAN WHERE ID_FAIL = "+idFail+")";
			
			myLogger.info("setTblppkpraperbicaraan :" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;

			while (rs.next()) {

				h = new Hashtable();

				h.put("tarikh_inkuiri", rs.getDate("tarikh_inkuiri") == null ? ""
						: Format.format(rs.getDate("tarikh_inkuiri")));
				h.put("id_praperbicaraan", rs.getInt("id_praperbicaraan"));
				h.put("sebab_inkuiri", rs.getString("sebab_inkuiri") == null ? ""
						: rs.getString("sebab_inkuiri"));
				h.put("catatan_sebabinkuiri", rs.getString("catatan_sebabinkuiri") == null ? ""
						: rs.getString("catatan_sebabinkuiri"));
				h.put("catatan_notis", rs.getString("catatan_notis") == null ? "" : rs
						.getString("catatan_notis"));
				h.put("alamat1", rs.getString("alamat_bicara1") == null ? "" : rs
						.getString("alamat_bicara1"));
				h.put("alamat2", rs.getString("alamat_bicara2") == null ? "" : rs
						.getString("alamat_bicara2"));
				h.put("alamat3", rs.getString("alamat_bicara3") == null ? "" : rs
						.getString("alamat_bicara3"));
				h.put("poskod", rs.getString("poskod_bicara") == null ? "" : rs
						.getString("poskod_bicara"));
				h.put("idnegeri", rs.getString("idnegeri_bicara") == null ? "" : rs
						.getString("idnegeri_bicara"));
				h.put("masa_bicara", rs.getString("masa_bicara") == null ? "" : rs
						.getString("masa_bicara"));
				h.put("jenis_masa_bicara", rs.getString("jenis_masa_bicara") == null ? "" : rs
						.getString("jenis_masa_bicara"));
				

				listPra.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	//INSERT NOTIS PRAPERBICARAAN
	
	public static void addNotisPraPerbicaraan(Hashtable data) throws Exception {

		
		
		Db db = null;

		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			long id_notispraperbicaraan = DB.getNextID("TBLPPKPRAPERBICARAAN_SEQ");

			// id login
			String id_masuk = (String) data.get("id_masuk");
			if (id_masuk == null){
				id_masuk = (String) data.get("id_kemaskini");
			}
			String socJenisWaktu = (String) data.get("socJenisWaktu");
			String txtMasaBicara = (String) data.get("txtMasaBicara");
			String tarikhInkuiri = (String) data.get("txdTarikhInkuiri");
			String sebabInkuiri = (String) data.get("socSebab");
			String txtSebabsebabLain = (String) data.get("txtSebabsebabLain");
			String txtCatatan = (String) data.get("txtCatatan");
			String id_kemaskini = (String) data.get("id_kemaskini");
			String idPermohonan = (String) data.get("idPermohonan");
			String alamat_bicara1 = (String) data.get("alamat_bicara1");
			String alamat_bicara2 = (String) data.get("alamat_bicara2");
			String alamat_bicara3 = (String) data.get("alamat_bicara3");
			String poskod_bicara = (String) data.get("poskod_bicara");
			String negeri_bicara = (String) data.get("negeri_bicara");
			
			String TI = "to_date('" + tarikhInkuiri + "','dd/MM/yyyy')";
			
			// insert data into Tblppkpraperbicaraan
			Statement stmtS = db.getStatement();
			
			
			
			SQLRenderer r = new SQLRenderer();

			r.add("id_notispraperbicaraan", id_notispraperbicaraan);
			r.add("jenis_masa_bicara", socJenisWaktu);
			r.add("masa_bicara", txtMasaBicara);
			r.add("id_praperbicaraan", id_notispraperbicaraan);
			r.add("id_masuk", id_masuk);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("tarikh_inkuiri",  r.unquote(TI));
			r.add("sebab_inkuiri", sebabInkuiri);
			r.add("catatan_notis", txtCatatan);
			r.add("id_permohonan", idPermohonan);
			r.add("ALAMAT_BICARA1", alamat_bicara1);
			r.add("ALAMAT_BICARA2", alamat_bicara2);
			r.add("ALAMAT_BICARA3", alamat_bicara3);
			r.add("POSKOD_BICARA", poskod_bicara);
			r.add("IDNEGERI_BICARA", negeri_bicara);
			
			sql = r.getSQLInsert("Tblppkpraperbicaraan");
			myLogger.info("addNotisPraPerbicaraan : " + sql);
			stmt.executeUpdate(sql);
			
			
			
			
			

		}
		finally {
			if (db != null)
				db.close();
		}

	}
	
	public static void updateNotisPraPerbicaraan(Hashtable data) throws Exception {

		Db db = null;
		String sql = "";
		String keputusan = "";
		String catatan_keputusan = "";
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			String id_masuk = (String) data.get("id_masuk");
			if (id_masuk == null){
				id_masuk = (String) data.get("id_kemaskini");
			}
			String socJenisWaktu = (String) data.get("socJenisWaktu");
			String txtMasaBicara = (String) data.get("txtMasaBicara");
			String id_praperbicaraan = (String) data.get("id_praperbicaraan");
			String tarikhInkuiri = (String) data.get("txdTarikhInkuiri");
			String sebabInkuiri = (String) data.get("socSebab");
			String txtSebabsebabLain = (String) data.get("txtSebabsebabLain");
			String txtCatatan = (String) data.get("txtCatatan");
			String id_kemaskini = (String) data.get("id_kemaskini");
			String idPermohonan = (String) data.get("idPermohonan");
			String alamat_bicara1 = (String) data.get("alamat_bicara1");
			String alamat_bicara2 = (String) data.get("alamat_bicara2");
			String alamat_bicara3 = (String) data.get("alamat_bicara3");
			String poskod_bicara = (String) data.get("poskod_bicara");
			String negeri_bicara = (String) data.get("negeri_bicara");
			keputusan = (String) data.get("socKeputusan");
			catatan_keputusan = (String) data.get("catatan_keputusan");
			
			String TI = "to_date('" + tarikhInkuiri + "','dd/MM/yyyy')";
			
			// insert data into Tblppkpraperbicaraan
			Statement stmtS = db.getStatement();
			
			
			
			SQLRenderer r = new SQLRenderer();

			r.update("id_praperbicaraan", id_praperbicaraan);
			r.add("jenis_masa_bicara", socJenisWaktu);
			r.add("masa_bicara", txtMasaBicara);
			r.add("id_kemaskini", id_masuk);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			r.add("tarikh_inkuiri",  r.unquote(TI));
			r.add("sebab_inkuiri", sebabInkuiri);
			r.add("catatan_notis", txtCatatan);
			r.add("id_permohonan", idPermohonan);
			r.add("ALAMAT_BICARA1", alamat_bicara1);
			r.add("ALAMAT_BICARA2", alamat_bicara2);
			r.add("ALAMAT_BICARA3", alamat_bicara3);
			r.add("POSKOD_BICARA", poskod_bicara);
			r.add("IDNEGERI_BICARA", negeri_bicara);
			r.add("KEPUTUSAN_PRAPERBICARAAN", keputusan);
			r.add("CATATAN_KEPUTUSAN", catatan_keputusan);
			sql = r.getSQLUpdate("Tblppkpraperbicaraan");
			myLogger.info("updateNotisPraPerbicaraan : " + sql);
			stmt.executeUpdate(sql);

		}
		finally {
			if (db != null)
				db.close();
		}

	}

	// LIST OB :: SEKSYEN 8 & SEKSYEN 17 ::
	public static void setListSemuaOB(String id_permohonansimati,
			String id_simati, String seksyen, String idperbicaraan) throws Exception {

		Db db = null;
		listSemuaOB.clear();
		String sql = "";
		String icYEAR = "";
		String icMONTH = "";
		String icDAY = "";
		String MONTH = "";
		String x = "";
		String year = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			if (idperbicaraan == null)
			{
				idperbicaraan = "";
			}
			sql += " SELECT OB.ID_OB, OB.ID_SIMATI, OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, OB.STATUS_HIDUP, OB.JENIS_KP, OB.NO_KP_LAIN, OB.NO_SURAT_BERANAK, " +
					" OB.TARIKH_LAHIR, OB.JANTINA, OB.ID_TARAFKPTG,  " +
					 " OB.UMUR, TR.KETERANGAN, OB.STATUS_OB, OB.EMEL " +
					 " ,CASE WHEN NOTIS_OB.ID_OB IS NOT NULL THEN 'Y' END AS FLAG  " +
					 " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1, TBLPPKPERMOHONANSIMATI SM, TBLPPKRUJTARAFKPTG TR, TBLPPKPERMOHONAN P  " +
					 " ,(SELECT ID_OB FROM TBLPPKNOTISOB WHERE ID_PERBICARAAN = '" + idperbicaraan + "') NOTIS_OB " +
					 " WHERE OB1.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI  " +
					 " AND OB.ID_OB = NOTIS_OB.ID_OB (+) " +
					 " AND OB.ID_OB = OB1.ID_OB AND OB.ID_PERMOHONANSIMATI = '" + id_permohonansimati + "'  " +
					 " AND OB.ID_TARAFKPTG = TR.ID_TARAFKPTG AND SM.ID_PERMOHONAN = P.ID_PERMOHONAN  AND NVL(OB.STATUS_HIDUP,0) = 0   " +
					 " AND P.ID_STATUS <> '169'  AND OB.ID_SIMATI = '" + id_simati + "'   AND OB.ID_TARAFKPTG NOT IN (2,14)  ";
					 
						if (!seksyen.equals("8")) {
							sql += " AND NVL(p.no_subjaket,0) <= '"
									+ getNoSubjaketByIdPermohonan(getIdPermohonanByIdPermohonanSimati(id_permohonansimati))
									+ "'";
						}
					 
					 sql += " UNION   " +
					 " SELECT OB.ID_OB, OB.ID_SIMATI, OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, OB.STATUS_HIDUP, OB.JENIS_KP, OB.NO_KP_LAIN, " +
					 " OB.NO_SURAT_BERANAK, OB.TARIKH_LAHIR, OB.JANTINA, OB.ID_TARAFKPTG,   " +
					 " OB.UMUR, TR.KETERANGAN, OB.STATUS_OB,OB.EMEL   " +
					 " ,CASE WHEN NOTIS_OB.ID_OB IS NOT NULL THEN 'Y' END AS FLAG  " +
					 
					 " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1, TBLPPKPERMOHONANSIMATI SM, TBLPPKRUJTARAFKPTG TR, TBLPPKPERMOHONAN P  " +
					 " ,(SELECT ID_OB FROM TBLPPKNOTISOB WHERE ID_PERBICARAAN = '" + idperbicaraan + "') NOTIS_OB   " +
					 " WHERE OB1.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI    " +
					 " AND OB.ID_OB = NOTIS_OB.ID_OB (+) " +
					 " AND OB.ID_OB = OB1.ID_OB AND OB.ID_PERMOHONANSIMATI = '" + id_permohonansimati + "'   " +
					 " AND OB1.ID_PERMOHONANSIMATI =  '" + id_permohonansimati + "' AND OB.ID_TARAFKPTG = TR.ID_TARAFKPTG AND SM.ID_PERMOHONAN = P.ID_PERMOHONAN    " +
					 " AND NVL(OB.STATUS_HIDUP,0) = 0  AND P.ID_STATUS <> '169'  AND OB.ID_SIMATI = '" + id_simati + "'  AND OB.ID_TARAFKPTG = 14    " +
					 " ORDER BY UMUR DESC, NAMA_OB ASC   ";

			
			myLogger.info("NOTIS LIST OB : " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;

			while (rs.next()) {

				h = new Hashtable();

				if (rs.getString("umur") != null && rs.getString("umur") != "") {

					h.put("age", rs.getInt("umur"));
				} else if (rs.getString("no_kp_baru") != null
						&& rs.getString("no_kp_baru") != "") {

					x = rs.getString("no_kp_baru").substring(0, 2);

					int z = 0;

					if (x != "") {
						z = Integer.parseInt(x);
					}

					icYEAR = rs.getString("no_kp_baru").substring(0, 2);
					icMONTH = rs.getString("no_kp_baru").substring(2, 4);
					icDAY = rs.getString("no_kp_baru").substring(4, 6);

					if (z <= 20) {
						year = "20" + icYEAR;
					} else {
						year = "19" + icYEAR;
					}

					int y = Integer.parseInt(year);
					int m = Integer.parseInt(icMONTH) - 1;
					int d = Integer.parseInt(icDAY);

					Calendar cal = new GregorianCalendar(y, m, d);
					Calendar now = new GregorianCalendar();

					int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
					if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
							|| (cal.get(Calendar.MONTH) == now
									.get(Calendar.MONTH) && cal
									.get(Calendar.DAY_OF_MONTH) > now
									.get(Calendar.DAY_OF_MONTH))) {
						res--;
					}

					int notnull = 0;
					int cantzero = 1;

					if (res < 0) {
						h.put("age", notnull);
					} else if (res == 0) {
						h.put("age", cantzero);
					} else {
						h.put("age", res);
					}

				} else {
					h.put("age", "");
				}

				h.put("bil", bil);
				h.put("no_kp_baru1", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(0, 6));
				h.put("no_kp_baru2", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(6, 8));
				h.put("no_kp_baru3", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(8, 12));
				h.put("id_ob", rs.getString("id_ob") == null ? "" : rs
						.getString("id_ob"));
				h.put("id_simati", rs.getString("id_simati") == null ? "" : rs
						.getString("id_simati"));
				h.put("nama_ob", rs.getString("nama_ob") == null ? "" : rs
						.getString("nama_ob"));
				h.put("no_kp_baru", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru"));
				h.put("no_kp_lama", rs.getString("no_kp_lama") == null ? ""
						: rs.getString("no_kp_lama"));
				h.put("jenis_kp", rs.getString("jenis_kp") == null ? "" : rs
						.getString("jenis_kp"));
				h.put("no_kp_lain", rs.getString("no_kp_lain") == null ? ""
						: rs.getString("no_kp_lain"));
				h.put("no_surat_beranak",
						rs.getString("no_surat_beranak") == null ? "" : rs
								.getString("no_surat_beranak"));
				h.put("tarikh_lahir", rs.getString("tarikh_lahir") == null ? ""
						: rs.getString("tarikh_lahir"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("status_ob", rs.getString("status_ob") == null ? "" : rs
						.getInt("status_ob"));

				if (rs.getString("status_ob") != null
						&& rs.getString("status_ob") != "") {

					String statusOB = rs.getString("status_ob");
					if (statusOB.equals("1")) {
						h.put("keteranganOB", "DEWASA/WARAS");
					} else if (statusOB.equals("2")) {
						h.put("keteranganOB", "BELUM DEWASA");
					} else if (statusOB.equals("3")) {
						h.put("keteranganOB", "HILANG");
					} else if (statusOB.equals("4")) {
						h.put("keteranganOB", "TIDAK SEMPURNA AKAL");
					} else {
						h.put("keteranganOB", "DEWASA/WARAS");
					}
				} else {
					h.put("keteranganOB", "DEWASA/WARAS");
				}
				
				if (idperbicaraan != null && idperbicaraan.length() > 0){
					h.put("flag", rs.getString("flag") == null ? "" : rs.getString("flag"));
				} else {
					h.put("flag", "");
				}
				h.put("emel", rs.getString("emel") == null ? "" : rs.getString("emel"));
				listSemuaOB.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}// close semua OB

	/** ADD BY PEJE - SENARAI PEMIUTANG **/
	public static void setListPemiutang(String id_permohonansimati,
			String id_simati, String seksyen, String idperbicaraan) throws Exception {

		Db db = null;
		listPemiutang.clear();
		String sql = "";
		String icYEAR = "";
		String icMONTH = "";
		String icDAY = "";
		String MONTH = "";
		String x = "";
		String year = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT ob.id_ob, ob.id_simati, ob.nama_ob, ob.no_kp_baru, ob.no_kp_lama, ";
			sql += "ob.status_hidup, ob.jenis_kp, ob.no_kp_lain, ob.no_surat_beranak, ";
			sql += "ob.tarikh_lahir, ob.jantina, ob.id_tarafkptg, ob.umur, tr.keterangan, ob.status_ob ";
			
			/** ADD BY PEJE - TO GET SELECTED FLAG **/
			if (idperbicaraan != null && idperbicaraan.length() > 0){
				sql += ", CASE WHEN ob.id_ob IN (SELECT id_ob from tblppknotisob where id_perbicaraan = '" + idperbicaraan + "') THEN 'Y' END AS flag ";
			}
			
			sql += "FROM Tblppkobpermohonan ob,tblppkob ob1, Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
			sql += "WHERE ob1.id_permohonansimati = sm.id_permohonansimati "
					+ " and ob.id_ob = ob1.id_ob and ob.id_permohonansimati = '"
					+ id_permohonansimati + "'";
			sql += " AND ob.id_tarafkptg = tr.id_tarafkptg ";
			sql += "AND sm.id_permohonan = p.id_permohonan ";
			sql += " AND NVL(ob.status_hidup,0) = 0 ";
			sql += " AND p.id_status <> '169' ";
			sql += " AND ob.id_simati = '" + id_simati + "' ";

			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			sql += " AND ob.id_tarafkptg = 2 ";

			if (!seksyen.equals("8")) {
				sql += " AND NVL(p.no_subjaket,0) <= '"
						+ getNoSubjaketByIdPermohonan(getIdPermohonanByIdPermohonanSimati(id_permohonansimati))
						+ "'";
			}
			sql += " ORDER BY ob.umur desc,ob.nama_ob asc ";

			myLogger.info("NOTIS LIST PEMIUTANG : " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;

			while (rs.next()) {

				h = new Hashtable();

				if (rs.getString("umur") != null && rs.getString("umur") != "") {

					h.put("age", rs.getInt("umur"));
				} else if (rs.getString("no_kp_baru") != null
						&& rs.getString("no_kp_baru") != "") {

					x = rs.getString("no_kp_baru").substring(0, 2);

					int z = 0;

					if (x != "") {
						z = Integer.parseInt(x);
					}

					icYEAR = rs.getString("no_kp_baru").substring(0, 2);
					icMONTH = rs.getString("no_kp_baru").substring(2, 4);
					icDAY = rs.getString("no_kp_baru").substring(4, 6);

					if (z <= 20) {
						year = "20" + icYEAR;
					} else {
						year = "19" + icYEAR;
					}

					int y = Integer.parseInt(year);
					int m = Integer.parseInt(icMONTH) - 1;
					int d = Integer.parseInt(icDAY);

					Calendar cal = new GregorianCalendar(y, m, d);
					Calendar now = new GregorianCalendar();

					int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
					if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
							|| (cal.get(Calendar.MONTH) == now
									.get(Calendar.MONTH) && cal
									.get(Calendar.DAY_OF_MONTH) > now
									.get(Calendar.DAY_OF_MONTH))) {
						res--;
					}

					int notnull = 0;
					int cantzero = 1;

					if (res < 0) {
						h.put("age", notnull);
					} else if (res == 0) {
						h.put("age", cantzero);
					} else {
						h.put("age", res);
					}

				} else {
					h.put("age", "");
				}

				h.put("bil", bil);
				h.put("no_kp_baru1", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(0, 6));
				h.put("no_kp_baru2", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(6, 8));
				h.put("no_kp_baru3", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(8, 12));
				h.put("id_ob", rs.getString("id_ob") == null ? "" : rs
						.getString("id_ob"));
				h.put("id_simati", rs.getString("id_simati") == null ? "" : rs
						.getString("id_simati"));
				h.put("nama_ob", rs.getString("nama_ob") == null ? "" : rs
						.getString("nama_ob"));
				h.put("no_kp_baru", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru"));
				h.put("no_kp_lama", rs.getString("no_kp_lama") == null ? ""
						: rs.getString("no_kp_lama"));
				h.put("jenis_kp", rs.getString("jenis_kp") == null ? "" : rs
						.getString("jenis_kp"));
				h.put("no_kp_lain", rs.getString("no_kp_lain") == null ? ""
						: rs.getString("no_kp_lain"));
				h.put("no_surat_beranak",
						rs.getString("no_surat_beranak") == null ? "" : rs
								.getString("no_surat_beranak"));
				h.put("tarikh_lahir", rs.getString("tarikh_lahir") == null ? ""
						: rs.getString("tarikh_lahir"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("status_ob", rs.getString("status_ob") == null ? "" : rs
						.getInt("status_ob"));

				if (rs.getString("status_ob") != null
						&& rs.getString("status_ob") != "") {

					String statusOB = rs.getString("status_ob");
					if (statusOB.equals("1")) {
						h.put("keteranganOB", "DEWASA/WARAS");
					} else if (statusOB.equals("2")) {
						h.put("keteranganOB", "BELUM DEWASA");
					} else if (statusOB.equals("3")) {
						h.put("keteranganOB", "HILANG");
					} else if (statusOB.equals("4")) {
						h.put("keteranganOB", "TIDAK SEMPURNA AKAL");
					} else {
						h.put("keteranganOB", "DEWASA/WARAS");
					}
				} else {
					h.put("keteranganOB", "DEWASA/WARAS");
				}
				
				if (idperbicaraan != null && idperbicaraan.length() > 0){
					h.put("flag", rs.getString("flag") == null ? "" : rs.getString("flag"));
				} else {
					h.put("flag", "");
				}

				listPemiutang.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	// add penjaga

	public void deletePenjaga(String id_penjaga, String id_ob) throws Exception {
		Db db = null;

		String sql = "";
		String sql1 = "";
		String sql4 = "";
		try {
			db = new Db();
			Statement stmt4 = db.getStatement();
			SQLRenderer r4 = new SQLRenderer();
			r4.add("id_penjaga", id_penjaga);
			sql = r4.getSQLDelete("tblppkpenjaga");
			myLogger.info("DELETE PENJAGA :" + sql);
			stmt4.executeUpdate(sql);

			sql1 = " SELECT ID_PENJAGA FROM TBLPPKPENJAGA WHERE ID_OBMINOR = '"
					+ id_ob
					+ "' "
					+ " ORDER BY TO_DATE(TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY HH:MIpm'),'DD/MM/YYYY HH:MIpm') ASC";
			myLogger.info("SELECT * PENJAGA :" + sql1);
			ResultSet rs = stmt4.executeQuery(sql1);
			Hashtable h;
			Integer order = 0;
			while (rs.next()) {
				order++;
				updateDataPenjagaBIL(order + "", rs.getString("ID_PENJAGA"));
			}
			myLogger.info("order :" + order);

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void addPenjaga(Hashtable data) throws Exception {

		Db db = null;
		String sql = "";
		int bil = 0;

		try {

			db = new Db();

			long id_penjaga = DB.getNextID("TBLPPKPENJAGA_SEQ");

			String vadPenjaga = (String) data.get("vadPenjaga");
			int _vadPenjaga = Integer.parseInt(vadPenjaga);

			String listPenjaga_size = (String) data.get("listPenjaga_size");
			int _listPenjaga_size = Integer.parseInt(listPenjaga_size);

			String id_obminor = (String) data.get("id_obminor");
			String id_ob = (String) data.get("id_ob");
			
			String id_pegawai = (String) data.get("id_pegawai");
			String idNegeri = (String) data.get("socNegeri");

			String Warganegara = (String) data.get("socWarganegara");
			String Agama = (String) data.get("socAgama");
			String Jantina = (String) data.get("socJantina");
			String JenisKp = (String) data.get("socJenisKp");

			String txtNoKPBaru = (String) data.get("txtNoKPBaru");
			String txtNoKPLama = (String) data.get("txtNoKPLama");
			String txtJenisKP = (String) data.get("txtJenisKP");
			String txtNamaPenjaga = (String) data.get("txtNamaPenjaga");
			String txtUmur = (String) data.get("txtUmur");
			String txtalamat1 = (String) data.get("txtalamat1");
			String txtalamat2 = (String) data.get("txtalamat2");
			String txtalamat3 = (String) data.get("txtalamat3");
			String txtbandar = (String) data.get("txtbandar");
			String txtposkod = (String) data.get("txtposkod");
			String txtcatatan = (String) data.get("txtcatatan");
			String txtTarikhPerlantikanPenjaga = (String) data.get("tarikhPerlantikanPenjaga");
			myLogger.info("txtTarikhPerlantikanPenjaga = " + txtTarikhPerlantikanPenjaga);
			// int idOB = 0;
			//		      
			// int idOBMINOR = Integer.parseInt(id_obminor);
			//		      
			// if(id_ob!="0"){
			// idOB = Integer.parseInt(id_ob);
			// }

			String idPeg = id_pegawai;

			if (_listPenjaga_size != 4) {
				bil = _listPenjaga_size + 1;
			}

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_penjaga", id_penjaga);

			if (_vadPenjaga == 0) {
				r.add("id_ob", id_ob);
				r.add("id_obminor", id_obminor);
			} else if (_vadPenjaga == 1) {
				r.add("id_obminor", id_obminor);
			}

			r.add("jenis_warga", Warganegara);
			r.add("jenis_agama", Agama);
			r.add("jantina", Jantina);
			r.add("jenis_kp", JenisKp);
			r.add("no_kp_baru", txtNoKPBaru);
			r.add("no_kp_lama", txtNoKPLama);
			r.add("no_kp_lain", txtJenisKP);
			r.add("nama_penjaga", txtNamaPenjaga);
			r.add("umur", txtUmur);
			r.add("alamat1", txtalamat1);
			r.add("alamat2", txtalamat2);
			r.add("alamat3", txtalamat3);
			r.add("id_bandar", txtbandar);
			r.add("poskod", txtposkod);
			r.add("catatan", txtcatatan);
			r.add("bil_penjaga", bil);
			r.add("tarikh_perlantikanpenjaga",txtTarikhPerlantikanPenjaga);

			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("id_masuk", idPeg);
			r.add("id_negeri", idNegeri);
			sql = r.getSQLInsert("Tblppkpenjaga");
			stmt.executeUpdate(sql);

			sql = " SELECT ID_PENJAGA FROM TBLPPKPENJAGA WHERE ID_OBMINOR = '"
					+ id_obminor
					+ "' "
					+ " ORDER BY TO_DATE(TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY HH:MIpm'),'DD/MM/YYYY HH:MIpm') ASC";
			myLogger.info("SELECT * PENJAGA :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer order = 0;
			while (rs.next()) {
				order++;
				updateDataPenjagaBIL(order + "", rs.getString("ID_PENJAGA"));
			}

		}// close try

		finally {
			if (db != null)
				db.close();
		}// close finally

	}// close add Penjaga

	public static void updateDataPenjagaBIL(String order, String id_penjaga)
			throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			myLogger.info("order :" + order);
			r.clear();
			r.update("ID_PENJAGA", id_penjaga);
			r.add("BIL_PENJAGA", order);
			sql = r.getSQLUpdate("TBLPPKPENJAGA");
			myLogger.info("UPDATE PENJAGA :" + sql);
			stmt.executeUpdate(sql);

		}// close try

		finally {
			if (db != null)
				db.close();
		}// close finally

	}// close add Penjaga

	// update data penjaga
	public static void updateDataPenjaga(Hashtable data) throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();

			String id_penjaga = (String) data.get("id_penjaga");
			String id_pegawai = (String) data.get("id_pegawai");
			String idNegeri = (String) data.get("socNegeri");

			String Warganegara = (String) data.get("socWarganegara");
			String Agama = (String) data.get("socAgama");
			String Jantina = (String) data.get("socJantina");
			String JenisKp = (String) data.get("socJenisKp");

			String txtNoKPBaru = (String) data.get("txtNoKPBaru");
			String txtNoKPLama = (String) data.get("txtNoKPLama");
			String txtJenisKP = (String) data.get("txtJenisKP");
			String txtNamaPenjaga = (String) data.get("txtNamaPenjaga");
			String txtUmur = (String) data.get("txtUmur");
			String txtalamat1 = (String) data.get("txtalamat1");
			String txtalamat2 = (String) data.get("txtalamat2");
			String txtalamat3 = (String) data.get("txtalamat3");
			String txtbandar = (String) data.get("txtbandar");
			String txtposkod = (String) data.get("txtposkod");
			String txtcatatan = (String) data.get("txtcatatan");

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_penjaga", id_penjaga);
			r.add("jenis_warga", Warganegara);
			r.add("jenis_agama", Agama);
			r.add("jantina", Jantina);
			r.add("jenis_kp", JenisKp);
			r.add("no_kp_baru", txtNoKPBaru);
			r.add("no_kp_lama", txtNoKPLama);
			r.add("no_kp_lain", txtJenisKP);
			r.add("nama_penjaga", txtNamaPenjaga);
			r.add("umur", txtUmur);
			r.add("alamat1", txtalamat1);
			r.add("alamat2", txtalamat2);
			r.add("alamat3", txtalamat3);
			r.add("id_bandar", txtbandar);
			r.add("poskod", txtposkod);
			r.add("catatan", txtcatatan);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			r.add("id_kemaskini", id_pegawai);
			r.add("id_negeri", idNegeri);
			sql = r.getSQLUpdate("Tblppkpenjaga");
			stmt.executeUpdate(sql);

		}// close try

		finally {
			if (db != null)
				db.close();
		}// close finally

	}// close update Data Penjaga

	// list penjaga
	public static void setListPenjaga(String id_ob, String id_permohonansimati)
			throws Exception {

		Db db = null;
		listPenjaga.clear();
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("pen.id_penjaga");
			r.add("pen.id_ob");
			r.add("pen.id_obminor");
			r.add("pen.no_kp_baru");
			r.add("pen.no_kp_lama");

			r.add("pen.jenis_kp");
			r.add("pen.no_kp_lain");
			r.add("pen.nama_penjaga");
			r.add("pen.jantina");
			r.add("pen.jenis_warga");

			r.add("pen.jenis_agama");
			r.add("pen.umur");
			r.add("pen.alamat1");
			r.add("pen.alamat2");
			r.add("pen.alamat3");

			r.add("pen.poskod");
			r.add("pen.bandar");
			r.add("pen.id_negeri");
			r.add("pen.catatan");
			r.add("ob.nama_ob");

			r.add("bil_penjaga");

			r.add("pen.id_obminor", r.unquote("ob.id_ob"));

			r.add("pen.id_obminor", id_ob);

			// sql =
			// r.getSQLSelect("Tblppkpenjaga pen, Tblppkob ob","pen.nama_penjaga asc");

			sql = "SELECT PEN.ID_PENJAGA, PEN.ID_OB, PEN.ID_OBMINOR, PEN.NO_KP_BARU, PEN.NO_KP_LAMA, "
					+ " PEN.JENIS_KP, PEN.NO_KP_LAIN, PEN.NAMA_PENJAGA, PEN.JANTINA, PEN.JENIS_WARGA, "
					+ " PEN.JENIS_AGAMA, PEN.UMUR, PEN.ALAMAT1, PEN.ALAMAT2, PEN.ALAMAT3, PEN.POSKOD, "
					+ " PEN.BANDAR, PEN.ID_NEGERI, PEN.CATATAN, OB.NAMA_OB, BIL_PENJAGA  "
					+ " FROM TBLPPKPENJAGA PEN, TBLPPKOB OB1, TBLPPKOBPERMOHONAN OB "
					+ " WHERE PEN.ID_OBMINOR = OB1.ID_OB AND OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"
					+ id_permohonansimati
					+ "' "
					+ " AND PEN.ID_OBMINOR = '"
					+ id_ob + "'  ORDER BY PEN.NAMA_PENJAGA ASC";

			myLogger.info("------setListPenjaga(" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;

			while (rs.next()) {

				h = new Hashtable();
				h.put("bil", bil);
				// h.put("id_ob", rs.getString("id_ob"));
				h.put("id_obminor", rs.getString("id_obminor") == null ? ""
						: rs.getString("id_obminor"));
				h.put("id_penjaga", rs.getString("id_penjaga") == null ? ""
						: rs.getString("id_penjaga"));
				h.put("no_kp_baru", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru"));
				h.put("no_kp_lama", rs.getString("no_kp_lama") == null ? ""
						: rs.getString("no_kp_lama"));
				h.put("jenis_kp", rs.getString("jenis_kp") == null ? "" : rs
						.getString("jenis_kp"));
				h.put("no_kp_lain", rs.getString("no_kp_lain") == null ? ""
						: rs.getString("no_kp_lain"));
				h.put("nama_penjaga", rs.getString("nama_penjaga") == null ? ""
						: rs.getString("nama_penjaga"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("jenis_warga", rs.getString("jenis_warga") == null ? ""
						: rs.getString("jenis_warga"));
				h.put("jenis_agama", rs.getString("jenis_agama") == null ? ""
						: rs.getString("jenis_agama"));
				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));
				h.put("alamat1", rs.getString("alamat1") == null ? "" : rs
						.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2") == null ? "" : rs
						.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3") == null ? "" : rs
						.getString("alamat3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("bil_penjaga", rs.getString("bil_penjaga") == null ? ""
						: rs.getString("bil_penjaga"));
				h.put("nama_ob", rs.getString("nama_ob") == null ? "" : rs
						.getString("nama_ob"));

				if (rs.getString("id_negeri") == null) {
					h.put("id_negeri", "0");
				} else {
					h.put("id_negeri", rs.getString("id_negeri"));
				}

				listPenjaga.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}// close list penjaga

	// data penjaga
	public static void setDataPenjaga(String id_penjaga) throws Exception {

		Db db = null;
		listDataPenjaga.clear();
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("pen.id_penjaga");
			r.add("pen.id_ob");
			r.add("pen.id_obminor");
			r.add("pen.no_kp_baru");
			r.add("pen.no_kp_lama");

			r.add("pen.jenis_kp");
			r.add("pen.no_kp_lain");
			r.add("pen.nama_penjaga");
			r.add("pen.jantina");
			r.add("pen.jenis_warga");

			r.add("pen.jenis_agama");
			r.add("pen.umur");
			r.add("pen.alamat1");
			r.add("pen.alamat2");
			r.add("pen.alamat3");

			r.add("pen.poskod");
			r.add("pen.bandar");
			r.add("pen.id_bandar");
			r.add("pen.id_negeri");
			r.add("pen.catatan");

			r.add("bil_penjaga");
			r.add("tarikh_perlantikanpenjaga");
			r.add("pen.id_penjaga", id_penjaga);
			sql = r.getSQLSelect("Tblppkpenjaga pen");

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {

				h = new Hashtable();
				h.put("id_penjaga", rs.getString("id_penjaga"));

				if (rs.getString("id_ob") == null) {
					h.put("editable", "yes");
				} else {
					h.put("editable", "no");
				}

				h.put("id_obminor", rs.getString("id_obminor") == null ? ""
						: rs.getString("id_obminor"));
				h.put("no_kp_baru1", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(0, 6));
				h.put("no_kp_baru2", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(6, 8));
				h.put("no_kp_baru3", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(8, 12));
				h.put("no_kp_lama", rs.getString("no_kp_lama") == null ? ""
						: rs.getString("no_kp_lama"));
				h.put("jenis_kp", rs.getString("jenis_kp") == null ? "" : rs
						.getString("jenis_kp"));
				h.put("no_kp_lain", rs.getString("no_kp_lain") == null ? ""
						: rs.getString("no_kp_lain"));
				h.put("nama_penjaga", rs.getString("nama_penjaga") == null ? ""
						: rs.getString("nama_penjaga"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("jenis_warga", rs.getString("jenis_warga") == null ? ""
						: rs.getString("jenis_warga"));
				h.put("jenis_agama", rs.getString("jenis_agama") == null ? ""
						: rs.getString("jenis_agama"));
				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));
				h.put("alamat1", rs.getString("alamat1") == null ? "" : rs
						.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2") == null ? "" : rs
						.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3") == null ? "" : rs
						.getString("alamat3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("id_bandar", rs.getString("id_bandar") == null ? "" : rs
						.getString("id_bandar"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("bil_penjaga", rs.getString("bil_penjaga") == null ? ""
						: rs.getString("bil_penjaga"));
				// h.put("id_negeri",
				// rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
				h.put("tarikh_perlantikanpenjaga", rs.getString("bil_penjaga") == null ? ""
						: rs.getString("bil_penjaga"));
				h.put("tarikh_perlantikanpenjaga", rs.getDate("TARIKH_PERLANTIKANPENJAGA") == null ? ""
						: Format.format(rs.getDate("TARIKH_PERLANTIKANPENJAGA")));
				if (rs.getString("id_negeri") == null) {
					h.put("id_negeri", "0");
				} else {
					h.put("id_negeri", rs.getString("id_negeri"));
				}

				listDataPenjaga.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}// close data penjaga

	// List penjaga :: SEKSYEN 8 & 17 ::
	public static void setMaklumatPenjaga(String id_permohonansimati,
			String id_simati) throws Exception {

		Db db = null;
		MaklumatPenjaga.clear();

		String sql = "SELECT distinct a.id_obminor, "
				+ " (SELECT nama_ob FROM tblppkob  WHERE id_simati = '"
				+ id_simati + "' AND id_ob = a.id_obminor ) as nama_ob,";
		sql += " (SELECT id_ob FROM tblppkob WHERE id_simati = '" + id_simati
				+ "' AND id_ob = a.id_obminor ) as waris,";
		sql += " (SELECT nama_penjaga FROM tblppkpenjaga  WHERE bil_penjaga = 1 AND id_obminor = a.id_obminor ) as penjaga1, ";
		sql += " (SELECT id_penjaga FROM tblppkpenjaga  WHERE bil_penjaga = 1 AND id_obminor = a.id_obminor ) as id_penjaga1, ";
		sql += " (SELECT nama_penjaga FROM tblppkpenjaga  WHERE bil_penjaga = 2 AND id_obminor = a.id_obminor) as penjaga2, ";
		sql += " (SELECT id_penjaga FROM tblppkpenjaga  WHERE bil_penjaga = 2 AND id_obminor = a.id_obminor) as id_penjaga2, ";
		sql += " (SELECT nama_penjaga FROM tblppkpenjaga  WHERE bil_penjaga = 3 AND id_obminor = a.id_obminor ) as penjaga3, ";
		sql += " (SELECT id_penjaga FROM tblppkpenjaga  WHERE bil_penjaga = 3 AND id_obminor = a.id_obminor ) as id_penjaga3, ";
		sql += " (SELECT nama_penjaga FROM tblppkpenjaga  WHERE bil_penjaga = 4 AND id_obminor = a.id_obminor ) as penjaga4, ";
		sql += " (SELECT id_penjaga FROM tblppkpenjaga  WHERE bil_penjaga = 4 AND id_obminor = a.id_obminor ) as id_penjaga4";
		sql += " FROM tblppksimati sm,tblppkpenjaga a, tblppkob ob1,tblppkobpermohonan ob, tblppkpermohonan p, "
				+ " tblppkpermohonansimati pst";
		sql += " WHERE ob1.id_ob = ob.id_ob and ob.id_permohonansimati = '"
				+ id_permohonansimati
				+ "' "
				+ " and ob.id_simati = sm.id_simati  AND a.id_obminor = ob1.id_ob ";
		sql += " AND pst.id_permohonan = p.id_permohonan ";
		sql += " AND pst.id_simati = sm.id_simati ";
		// sql += " AND p.id_status <> '169' ";
		sql += " AND ob.id_simati = '" + id_simati + "'";
		// sql += " AND p.no_subjaket <= '" +
		// getNoSubjaketByIdPermohonan(getIdPermohonanByIdPermohonanSimati(id_permohonansimati))+"'";
		// sql += " AND ob.id_permohonansimati <= '"+id_permohonansimati+"' ";
		sql += " GROUP BY a.id_obminor ";
		sql += " order by nama_ob ";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			myLogger.info("sql :" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;

			while (rs.next()) {

				h = new Hashtable();

				h.put("bil", bil);
				h.put("id_ob", rs.getString("waris") == null ? "" : rs
						.getString("waris"));
				h.put("id_obminor", rs.getString("id_obminor") == null ? ""
						: rs.getString("id_obminor"));
				h.put("nama_ob", rs.getString("nama_ob") == null ? "" : rs
						.getString("nama_ob"));
				h.put("penjaga1", rs.getString("penjaga1") == null ? "" : rs
						.getString("penjaga1"));
				h.put("penjaga2", rs.getString("penjaga2") == null ? "" : rs
						.getString("penjaga2"));
				h.put("penjaga3", rs.getString("penjaga3") == null ? "" : rs
						.getString("penjaga3"));
				h.put("penjaga4", rs.getString("penjaga4") == null ? "" : rs
						.getString("penjaga4"));
				h.put("id_penjaga1", rs.getString("id_penjaga1") == null ? ""
						: rs.getString("id_penjaga1"));
				h.put("id_penjaga2", rs.getString("id_penjaga2") == null ? ""
						: rs.getString("id_penjaga2"));
				h.put("id_penjaga3", rs.getString("id_penjaga3") == null ? ""
						: rs.getString("id_penjaga3"));
				h.put("id_penjaga4", rs.getString("id_penjaga4") == null ? ""
						: rs.getString("id_penjaga4"));
				MaklumatPenjaga.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	// update laporan with existing idobmst
	public static void addLaporan(Hashtable data, String idOB) throws Exception {

		Connection conn = null;
		Db db = null;
		String sql = "";
		String sql2 = "";

		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			String id_keputusanpermohonan = (String) data.get("idkp");
			String tarikh_serahan = (String) data.get("tarikh_serahan");
			String jenis_serah = (String) data.get("jenis_serah");
			String jenis_status = (String) data.get("jenis_status");
			String catatan = (String) data.get("catatan");
			
			//code wp
			String tarikhHantar = (String) data.get("tarikhHantar");
			String alamatEmel = (String) data.get("alamatEmel");
			
			String nama_penghantar = (String) data.get("nama_penghantar");
			String id_penghantar = (String) data.get("id_penghantar");

			String daftar_pos = (String) data.get("daftar_pos");
			String nama_penerima = (String) data.get("nama_penerima");
			String no_kp = (String) data.get("noKP");
			String kplama = (String) data.get("kplama");
			String kplain = (String) data.get("kplain");
			String id_pegawai = (String) data.get("id_pegawai");
			String namaLain = (String) data.get("namaLain");//aishahlatip
			
		//	System.out.println("namaLain=="+namaLain);

			String TS = "to_date('" + tarikh_serahan + "','dd/MM/yyyy')";
			String TE = "to_date('" + tarikhHantar + "','dd/MM/yyyy')";//code wp

			// GET ID PERBICARAAN
			String id_perbicaraan = "";
			
		
			
			sql = "SELECT * FROM (" +
					"SELECT JMAX.*, MAX(JMAX.BIL_BICARA) OVER (PARTITION BY JMAX.ID_KEPUTUSANPERMOHONAN) MAX_BILBICARA " +
					" FROM TBLPPKPERBICARAAN JMAX WHERE JMAX.ID_KEPUTUSANPERMOHONAN = "+ id_keputusanpermohonan +
					") WHERE BIL_BICARA = MAX_BILBICARA ";



			ResultSet rsMT = stmt.executeQuery(sql);

			while (rsMT.next()) {
				id_perbicaraan = rsMT.getString("id_perbicaraan");
			}
			System.out.println("id_perbicaraan=="+id_perbicaraan);
			// GET ID_NOTISOBMST BY TBLPPKNOTISPERBICARAAN
			String id_notisobmst = "";

			sql = "SELECT id_notisobmst";
			sql += " FROM Tblppknotisperbicaraan";
			sql += " WHERE NVL(flag_jenis_notis,0) = '0'";
			sql += " AND id_perbicaraan = '" + id_perbicaraan + "'";

			ResultSet rsX = stmt.executeQuery(sql);

			while (rsX.next()) {
				id_notisobmst = rsX.getString("id_notisobmst");
			}
			
		
			// TBLPPKOBMST
			SQLRenderer r = new SQLRenderer();
		
			if(id_notisobmst.equals("")){
				
				long id_notisobmst_new = DB.getNextID("TBLPPKNOTISOBMST_SEQ");
			
				r.add("id_notisobmst", id_notisobmst_new);
				r.add("tarikh_serahan", r.unquote(TS));
				r.add("status_serah", jenis_status);
				r.add("jenis_serah", jenis_serah);
				r.add("catatan", catatan);
				r.add("nama_penghantar_notis", nama_penghantar);
				r.add("id_penghantarnotis", id_penghantar);
				r.add("no_surat_daftar", daftar_pos);
				r.add("id_masuk", id_pegawai);
				r.add("tarikh_masuk", r.unquote("sysdate"));
				r.add("id_kemaskini", id_pegawai);
				r.add("tarikh_kemaskini", r.unquote("sysdate"));
				
				r.add("tarikh_emel", r.unquote(TE));
				r.add("emel", alamatEmel);
				
				r.add("NAMA_PENGHANTAR_LAIN", namaLain);//aishahlatip
	
				sql = r.getSQLInsert("TBLPPKNOTISOBMST");
				stmt.executeUpdate(sql);
			
				
				//new
				// CREATE CHILD TABLE FOR TBLPPKPERBICARAAN N OBMST
				SQLRenderer rNO = new SQLRenderer();

				rNO.add("id_notisobmst", id_notisobmst_new);
				rNO.add("id_perbicaraan", id_perbicaraan);
				rNO.add("flag_jenis_notis", 0);
				rNO.add("id_masuk", id_pegawai);
				rNO.add("tarikh_masuk", rNO.unquote("sysdate"));

			
				sql = rNO.getSQLInsert("TBLPPKNOTISPERBICARAAN");
				stmt.executeUpdate(sql);

				id_notisobmst = Long.toString(id_notisobmst_new);
				
			}else{
				System.out.println("sini update");
				r.update("id_notisobmst", id_notisobmst);
				r.add("tarikh_serahan", r.unquote(TS));
				r.add("status_serah", jenis_status);
				r.add("jenis_serah", jenis_serah);
				r.add("catatan", catatan);
				r.add("nama_penghantar_notis", nama_penghantar);
				r.add("id_penghantarnotis", id_penghantar);
				r.add("no_surat_daftar", daftar_pos);
				r.add("id_masuk", id_pegawai);
				r.add("tarikh_masuk", r.unquote("sysdate"));
				r.add("id_kemaskini", id_pegawai);
				r.add("tarikh_kemaskini", r.unquote("sysdate"));
				
				r.add("tarikh_emel", r.unquote(TE));
				r.add("emel", alamatEmel);
				
				r.add("NAMA_PENGHANTAR_LAIN", namaLain);//aishahlatip
				sql = r.getSQLUpdate("TBLPPKNOTISOBMST");
				stmt.executeUpdate(sql);
			}
	
			conn.commit();
			

			// CREATE CHILD TABLE FOR TBLPPKOBMST (TBLPPKOBDTL)
			// if(!idOB.equals("")){

			SQLRenderer rDTL = new SQLRenderer();
			System.out.println("id_notisobmst=3="+id_notisobmst);
			rDTL.add("id_ob", idOB);
			rDTL.add("id_notisobmst", id_notisobmst);
			rDTL.add("nama_penerima", nama_penerima);
			rDTL.add("no_kp_baru", no_kp);
			rDTL.add("no_kp_lama", kplama);
			rDTL.add("no_kp_lain", kplain);

			rDTL.add("id_masuk", id_pegawai);
			rDTL.add("tarikh_masuk", rDTL.unquote("sysdate"));
			
			sql2 = rDTL.getSQLInsert("TBLPPKNOTISOBDTL");
			stmt.executeUpdate(sql2);
			
			
//comment start
/*			sql = "SELECT max(id_perbicaraan) as id_perbicaraan  ";
			sql += " FROM Tblppkperbicaraan WHERE id_keputusanpermohonan = "
					+ id_keputusanpermohonan;

			ResultSet rsMT = stmt.executeQuery(sql);

			while (rsMT.next()) {
				id_perbicaraan = rsMT.getString("id_perbicaraan");
			}
			
			System.out.println("id_perbicaraan====="+id_perbicaraan);

			// GET ID_NOTISOBMST BY TBLPPKNOTISPERBICARAAN
			String id_notisobmst = "";

			sql = "SELECT id_notisobmst";
			sql += " FROM Tblppknotisperbicaraan";
			sql += " WHERE NVL(flag_jenis_notis,0) = '0'";
			sql += " AND id_perbicaraan = '" + id_perbicaraan + "'";

			ResultSet rsX = stmt.executeQuery(sql);

			while (rsX.next()) {
				id_notisobmst = rsX.getString("id_notisobmst");
			}
			System.out.println("id_notisobmst====="+id_notisobmst);
			// TBLPPKOBMST
			SQLRenderer r = new SQLRenderer();
			r.update("id_notisobmst", id_notisobmst);
			r.add("tarikh_serahan", r.unquote(TS));
			r.add("status_serah", jenis_status);
			r.add("jenis_serah", jenis_serah);
			r.add("catatan", catatan);
			r.add("nama_penghantar_notis", nama_penghantar);
			r.add("id_penghantarnotis", id_penghantar);
			r.add("no_surat_daftar", daftar_pos);
			r.add("id_masuk", id_pegawai);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("id_kemaskini", id_pegawai);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			
			if(TE!=""){
			r.add("tarikh_emel", r.unquote(TE));
			r.add("emel", alamatEmel);
			}
			
	if(id_notisobmst.equals("")){
		System.out.println("sini insert");
		sql = r.getSQLInsert("TBLPPKNOTISOBMST");
	}else{
		System.out.println("sini update");
		sql = r.getSQLUpdate("TBLPPKNOTISOBMST");
	//}
			stmt.executeUpdate(sql);

			// CREATE CHILD TABLE FOR TBLPPKOBMST (TBLPPKOBDTL)
			// if(!idOB.equals("")){
			
			//checking dulu sekiranya id_notisobmst telah wujud didlam TBLPPKNOTISOBDTL
			String idNotisOBDTL = "";
			
			if(!id_notisobmst.equals("")){
				
				idNotisOBDTL = checkDataWujud(id_notisobmst);
				
				if(!idNotisOBDTL.equals("")){//id wujud
				
					SQLRenderer r1 = new SQLRenderer();
					r1.update("id_notisobmst", id_notisobmst);
					r1.add("id_ob", idOB);
					r1.add("nama_penerima", nama_penerima);
					r1.add("no_kp_baru", no_kp);
					r1.add("no_kp_lama", kplama);
					r1.add("no_kp_lain", kplain);
					r1.add("id_kemaskini", id_pegawai);
					r1.add("tarikh_kemaskini", r1.unquote("sysdate"));
					
					sql = r1.getSQLUpdate("TBLPPKNOTISOBDTL");
					stmt.executeUpdate(sql);
				}else{
					
					SQLRenderer rDTL = new SQLRenderer();

					rDTL.add("id_ob", idOB);
					rDTL.add("id_notisobmst", id_notisobmst);
					rDTL.add("nama_penerima", nama_penerima);
					rDTL.add("no_kp_baru", no_kp);
					rDTL.add("no_kp_lama", kplama);
					rDTL.add("no_kp_lain", kplain);
					rDTL.add("id_masuk", id_pegawai);
					rDTL.add("tarikh_masuk", rDTL.unquote("sysdate"));
					
					sql2 = rDTL.getSQLInsert("TBLPPKNOTISOBDTL");
					stmt.executeUpdate(sql2);
					
				}
			}*///comment end

			conn.commit();

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data ");
		} finally {
			if (db != null)
				db.close();
		}
	}

	
	public static void addLaporanOLD(Hashtable data, String idOB) throws Exception {

		Connection conn = null;
		Db db = null;
		String sql = "";
		String sql2 = "";

		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			String id_keputusanpermohonan = (String) data.get("idkp");
			String tarikh_serahan = (String) data.get("tarikh_serahan");
			String jenis_serah = (String) data.get("jenis_serah");
			String jenis_status = (String) data.get("jenis_status");
			String catatan = (String) data.get("catatan");
			
			System.out.println("jenis_serah addlaporan===="+jenis_serah);
			System.out.println("jenis_status addlaporan===="+jenis_status);
			
			//code wp
			String tarikhHantar = (String) data.get("tarikhHantar");
			String alamatEmel = (String) data.get("alamatEmel");
			
			String nama_penghantar = (String) data.get("nama_penghantar");
			String id_penghantar = (String) data.get("id_penghantar");

			String daftar_pos = (String) data.get("daftar_pos");
			String nama_penerima = (String) data.get("nama_penerima");
			String no_kp = (String) data.get("noKP");
			String kplama = (String) data.get("kplama");
			String kplain = (String) data.get("kplain");
			String id_pegawai = (String) data.get("id_pegawai");

			String TS = "to_date('" + tarikh_serahan + "','dd/MM/yyyy')";
			String TE = "to_date('" + tarikhHantar + "','dd/MM/yyyy')";//code wp

			// GET ID PERBICARAAN
			String id_perbicaraan = "";

			/*sql = "SELECT max(id_perbicaraan) as id_perbicaraan  ";
			sql += " FROM Tblppkperbicaraan WHERE id_keputusanpermohonan = "
					+ id_keputusanpermohonan;*/
			
			sql = "SELECT * FROM (" +
					" SELECT JMAX.*, MAX(JMAX.BIL_BICARA) OVER (PARTITION BY JMAX.ID_KEPUTUSANPERMOHONAN) MAX_BILBICARA" +
					" FROM TBLPPKPERBICARAAN JMAX WHERE JMAX.ID_KEPUTUSANPERMOHONAN = "+id_keputusanpermohonan+"" +
					" ) WHERE BIL_BICARA = MAX_BILBICARA ";
			

			ResultSet rsMT = stmt.executeQuery(sql);

			while (rsMT.next()) {
				id_perbicaraan = rsMT.getString("id_perbicaraan");
			}
			
			System.out.println("id_perbicaraan====="+id_perbicaraan);

			// GET ID_NOTISOBMST BY TBLPPKNOTISPERBICARAAN
			String id_notisobmst = "";

			sql = "SELECT id_notisobmst";
			sql += " FROM Tblppknotisperbicaraan";
			sql += " WHERE NVL(flag_jenis_notis,0) = '0'";
			sql += " AND id_perbicaraan = '" + id_perbicaraan + "'";

			ResultSet rsX = stmt.executeQuery(sql);

			while (rsX.next()) {
				id_notisobmst = rsX.getString("id_notisobmst");
			}
			System.out.println("id_notisobmst====="+id_notisobmst);
			// TBLPPKOBMST
			SQLRenderer r = new SQLRenderer();
			r.update("id_notisobmst", id_notisobmst);
			r.add("tarikh_serahan", r.unquote(TS));
			r.add("status_serah", jenis_status);
			r.add("jenis_serah", jenis_serah);
			r.add("catatan", catatan);
			r.add("nama_penghantar_notis", nama_penghantar);
			r.add("id_penghantarnotis", id_penghantar);
			r.add("no_surat_daftar", daftar_pos);
			r.add("id_masuk", id_pegawai);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("id_kemaskini", id_pegawai);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			
			if(TE!=""){
			r.add("tarikh_emel", r.unquote(TE));
			r.add("emel", alamatEmel);
			}
			
	/*if(id_notisobmst.equals("")){
		System.out.println("sini insert");
		sql = r.getSQLInsert("TBLPPKNOTISOBMST");
	}else{
		System.out.println("sini update");*/
		sql = r.getSQLUpdate("TBLPPKNOTISOBMST");
	//}
			stmt.executeUpdate(sql);
			System.out.println("sini lepas execute sql1");
			// CREATE CHILD TABLE FOR TBLPPKOBMST (TBLPPKOBDTL)
			// if(!idOB.equals("")){
			
			//checking dulu sekiranya id_notisobmst telah wujud didlam TBLPPKNOTISOBDTL
			String idNotisOBDTL = "";
			
			if(!id_notisobmst.equals("")){
				
				idNotisOBDTL = checkDataWujud(id_notisobmst);
				
				if(!idNotisOBDTL.equals("")){//id wujud
				
					SQLRenderer r1 = new SQLRenderer();
					r1.update("id_notisobmst", id_notisobmst);
					r1.add("id_ob", idOB);
					r1.add("nama_penerima", nama_penerima);
					r1.add("no_kp_baru", no_kp);
					r1.add("no_kp_lama", kplama);
					r1.add("no_kp_lain", kplain);
					r1.add("id_kemaskini", id_pegawai);
					r1.add("tarikh_kemaskini", r1.unquote("sysdate"));
					
					sql = r1.getSQLUpdate("TBLPPKNOTISOBDTL");
					stmt.executeUpdate(sql);
				}else{
					
					SQLRenderer rDTL = new SQLRenderer();

					rDTL.add("id_ob", idOB);
					rDTL.add("id_notisobmst", id_notisobmst);
					rDTL.add("nama_penerima", nama_penerima);
					rDTL.add("no_kp_baru", no_kp);
					rDTL.add("no_kp_lama", kplama);
					rDTL.add("no_kp_lain", kplain);
					rDTL.add("id_masuk", id_pegawai);
					rDTL.add("tarikh_masuk", rDTL.unquote("sysdate"));
					
					sql2 = rDTL.getSQLInsert("TBLPPKNOTISOBDTL");
					stmt.executeUpdate(sql2);
					
				}
			}

			conn.commit();

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data ");
		} finally {
			if (db != null)
				db.close();
		}
	}

	// add new laporan with new idobmst
	public static void addLaporanTambahan(Hashtable data, String idOB)
			throws Exception {

		Connection conn = null;
		Db db = null;
		String sql = "";

		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			String id_keputusanpermohonan = (String) data.get("idkp");

			String tarikh_serahan = (String) data.get("tarikh_serahan");
			String jenis_serah = (String) data.get("jenis_serah");
			String jenis_status = (String) data.get("jenis_status");
			String catatan = (String) data.get("catatan");

			String nama_penghantar = (String) data.get("nama_penghantar");
			String id_penghantar = (String) data.get("id_penghantar");

			String daftar_pos = (String) data.get("daftar_pos");

			String nama_penerima = (String) data.get("nama_penerima");
			String no_kp = (String) data.get("noKP");
			String kplama = (String) data.get("kplama");
			String kplain = (String) data.get("kplain");

			String id_pegawai = (String) data.get("id_pegawai");

			String TS = "to_date('" + tarikh_serahan + "','dd/MM/yyyy')";
			
			String namaLain = (String) data.get("namaLain");//aishahlatip
			System.out.println("namaLain==="+namaLain);
			// if(!idOB.equals("x") && !idOB.equals("")){

			long id_notisobmst = DB.getNextID("TBLPPKNOTISOBMST_SEQ");

			// TBLPPKOBMST
			SQLRenderer r = new SQLRenderer();

			r.add("id_notisobmst", id_notisobmst);
			r.add("tarikh_serahan", r.unquote(TS));
			r.add("status_serah", jenis_status);
			r.add("jenis_serah", jenis_serah);
			r.add("catatan", catatan);
			r.add("nama_penghantar_notis", nama_penghantar);
			r.add("id_penghantarnotis", id_penghantar);
			r.add("no_surat_daftar", daftar_pos);
			r.add("id_masuk", id_pegawai);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			if(namaLain!=null){
				r.add("NAMA_PENGHANTAR_LAIN", namaLain);
			}

			sql = r.getSQLInsert("TBLPPKNOTISOBMST");
			System.out.println("TBLPPKNOTISOBMST sebelum"+sql);
			stmt.executeUpdate(sql);
			
			System.out.println("TBLPPKNOTISOBMST lepas");

			// CREATE CHILD TABLE FOR TBLPPKOBMST (TBLPPKOBDTL)
			SQLRenderer rDTL = new SQLRenderer();

			rDTL.add("id_ob", idOB);
			rDTL.add("id_notisobmst", id_notisobmst);
			rDTL.add("nama_penerima", nama_penerima);
			rDTL.add("no_kp_baru", no_kp);
			rDTL.add("no_kp_lama", kplama);
			rDTL.add("no_kp_lain", kplain);

			rDTL.add("id_masuk", id_pegawai);
			rDTL.add("tarikh_masuk", rDTL.unquote("sysdate"));

			sql = rDTL.getSQLInsert("TBLPPKNOTISOBDTL");
			System.out.println("TBLPPKNOTISOBDTL sebelum");
			stmt.executeUpdate(sql);
			System.out.println("TBLPPKNOTISOBDTL lepas");

			// GET ID PERBICARAAN
			String id_perbicaraan = "";

			sql = "SELECT max(id_perbicaraan) as id_perbicaraan  ";
			sql += " FROM Tblppkperbicaraan WHERE id_keputusanpermohonan = '"
					+ id_keputusanpermohonan + "'";

			System.out.println("Tblppkperbicaraan sebelum");
			ResultSet rsMT = stmt.executeQuery(sql);
			System.out.println("Tblppkperbicaraan lepas");

			while (rsMT.next()) {
				id_perbicaraan = rsMT.getString("id_perbicaraan");
			}

			// CREATE CHILD TABLE FOR TBLPPKPERBICARAAN N OBMST
			SQLRenderer rNO = new SQLRenderer();

			rNO.add("id_notisobmst", id_notisobmst);
			rNO.add("id_perbicaraan", id_perbicaraan);
			rNO.add("flag_jenis_notis", 0);
			rNO.add("id_masuk", id_pegawai);
			rNO.add("tarikh_masuk", rNO.unquote("sysdate"));

			System.out.println("TBLPPKNOTISPERBICARAAN sebelum");
			sql = rNO.getSQLInsert("TBLPPKNOTISPERBICARAAN");
			System.out.println("TBLPPKNOTISPERBICARAAN lepas");
			stmt.executeUpdate(sql);

			// }

			conn.commit();

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data ");
		} finally {
			if (db != null)
				db.close();
		}

	}// add laporan tambahan

	// LIST PENERIMA NOTIS :: SEKSYEN 8 & SEKSYEN 17 ::
	public static void setListPenerimaNotis(String id_perbicaraan,
			String id_permohonansimati) throws Exception {

		Db db = null;
		listPenerimaNotis.clear();
		String sql = "";
		String idOB = "";

		try {

			db = new Db();

			Statement stmtN = db.getStatement();
			SQLRenderer rN = new SQLRenderer();

			sql = " SELECT n.id_notisobdtl, n.id_notisobmst, pb.id_perbicaraan, npb.id_notisperbicaraan, n.nama_penerima, ob.nama_ob, ob.id_ob,mst.JENIS_SERAH ";
			sql += " FROM Tblppknotisobdtl n, Tblppkob ob1,Tblppkobpermohonan ob,Tblppknotisobmst mst, Tblppknotisperbicaraan npb, Tblppkperbicaraan pb ";
			sql += " WHERE ob1.id_ob = ob.id_ob and n.id_ob = ob1.id_ob "
					+ " and ob.id_permohonansimati = '" + id_permohonansimati
					+ "' ";
			sql += " AND n.id_notisobmst = mst.id_notisobmst ";
			sql += " AND npb.id_notisobmst = mst.id_notisobmst ";
			sql += " AND npb.id_perbicaraan = pb.id_perbicaraan ";
			sql += " AND NVL(npb.flag_jenis_notis,0) = '0' ";
			sql += " AND pb.id_perbicaraan = '" + id_perbicaraan + "'";
			//sql += " AND n.nama_penerima is not null ";
			sql += " ORDER BY ob.nama_ob ";
			
			myLogger.info("sqlsetListPenerimaNotis = "+sql);
			ResultSet rx = stmtN.executeQuery(sql);

			Hashtable h;
			int bil = 1;

			while (rx.next()) {
				h = new Hashtable();
				// idOB = rx.getString("id_ob");
				h.put("bil", bil);
				h.put("id_ob", rx.getString("id_ob") == null ? "" : rx
						.getString("id_ob"));
				h.put("id_notisobdtl",
						rx.getString("id_notisobdtl") == null ? "" : rx
								.getString("id_notisobdtl"));
				h.put("id_notisobmst",
						rx.getString("id_notisobmst") == null ? "" : rx
								.getString("id_notisobmst"));
				h.put("nama_penerima",
						rx.getString("nama_penerima") == null ? "" : rx
								.getString("nama_penerima"));
				h.put("nama_ob", rx.getString("nama_ob") == null ? "" : rx
						.getString("nama_ob"));
				
				
				h.put("jenis_serah", rx.getString("JENIS_SERAH") == null ? "" : rx
						.getString("JENIS_SERAH"));
				
				listPenerimaNotis.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	// LIST PENERIMA NOTIS :: SEKSYEN 8 & SEKSYEN 17 ::
		public static void setListSerahanNotis(String id_perbicaraan,String id_permohonansimati) throws Exception {

			Db db = null;
			listSerahanNotis.clear();
			String sql = "";
			String idOB = "";
			boolean statusHantarPNB = false;
			
			try {

				db = new Db();

				Statement stmtN = db.getStatement();
				SQLRenderer rN = new SQLRenderer();
				
				
				statusHantarPNB = getPNBValidation(id_perbicaraan);
				
			/*	if(statusHantarPNB){

				sql = " SELECT n.id_notisobdtl, n.id_notisobmst, pb.id_perbicaraan, npb.id_notisperbicaraan, n.nama_penerima, ob.nama_ob, ob.id_ob,mst.JENIS_SERAH ";
				sql += " FROM Tblppknotisobdtl n, Tblppkob ob1,Tblppkobpermohonan ob,Tblppknotisobmst mst, Tblppknotisperbicaraan npb, Tblppkperbicaraan pb ";
				sql += " WHERE ob1.id_ob = ob.id_ob and n.id_ob = ob1.id_ob "
						+ " and ob.id_permohonansimati = '" + id_permohonansimati
						+ "' ";
				sql += " AND n.id_notisobmst = mst.id_notisobmst ";
				sql += " AND npb.id_notisobmst = mst.id_notisobmst ";
				sql += " AND npb.id_perbicaraan = pb.id_perbicaraan ";
				sql += " AND NVL(npb.flag_jenis_notis,0) = '0' ";
				sql += " AND pb.id_perbicaraan = '" + id_perbicaraan + "'";
				sql += " AND mst.jenis_serah IS NOT NULL ";
				sql += " ORDER BY ob.nama_ob ";
				
				}else{*/
					
					sql = " SELECT n.id_notisobdtl, n.id_notisobmst, pb.id_perbicaraan, npb.id_notisperbicaraan, n.nama_penerima, ob.nama_ob, ob.id_ob,mst.JENIS_SERAH ";
					sql += " FROM Tblppknotisobdtl_temp n, Tblppkob ob1,Tblppkobpermohonan ob,Tblppknotisobmst_temp mst, Tblppknotisperbicaraan_temp npb, Tblppkperbicaraan pb ";
					sql += " WHERE ob1.id_ob = ob.id_ob and n.id_ob = ob1.id_ob "
							+ " and ob.id_permohonansimati = '" + id_permohonansimati
							+ "' ";
					sql += " AND n.id_notisobmst = mst.id_notisobmst ";
					sql += " AND npb.id_notisobmst = mst.id_notisobmst ";
					sql += " AND npb.id_perbicaraan = pb.id_perbicaraan ";
					sql += " AND NVL(npb.flag_jenis_notis,0) = '0' ";
					sql += " AND pb.id_perbicaraan = '" + id_perbicaraan + "'";
					sql += " AND mst.jenis_serah IS NOT NULL ";
					sql += " ORDER BY ob.nama_ob ";
					
			//	}
				
				myLogger.info("sqlsetListSerahanNotis = "+sql);
				ResultSet rx = stmtN.executeQuery(sql);

				Hashtable h;
				int bil = 1;

				while (rx.next()) {
					h = new Hashtable();

					h.put("bil", bil);
					h.put("id_ob", rx.getString("id_ob") == null ? "" : rx.getString("id_ob"));
					h.put("id_notisobdtl",rx.getString("id_notisobdtl") == null ? "" : rx.getString("id_notisobdtl"));
					h.put("id_notisobmst",rx.getString("id_notisobmst") == null ? "" : rx.getString("id_notisobmst"));
					h.put("nama_penerima",rx.getString("nama_penerima") == null ? "" : rx.getString("nama_penerima"));
					h.put("nama_ob", rx.getString("nama_ob") == null ? "" : rx.getString("nama_ob"));										
					h.put("jenis_serah", rx.getString("JENIS_SERAH") == null ? "" : rx.getString("JENIS_SERAH"));
					
					listSerahanNotis.addElement(h);
					bil++;
				}

			} finally {
				if (db != null)
					db.close();
			}
		}
	
	//aishahlatip 01082017
	// count PENERIMA NOTIS :: SEKSYEN 8 & SEKSYEN 17 ::
		public static void setCountPenerimaNotis(String id_perbicaraan,
				String id_permohonansimati) throws Exception {

			Db db = null;
			listCountPenerimaNotis.clear();
			String sql = "";
			String idOB = "";

			try {

				db = new Db();

				Statement stmtN = db.getStatement();
				SQLRenderer rN = new SQLRenderer();

				sql = " SELECT count(n.id_notisobdtl) as notisobdtl ";
				sql += " FROM Tblppknotisobdtl n, Tblppkob ob1,Tblppkobpermohonan ob,Tblppknotisobmst mst, Tblppknotisperbicaraan npb, Tblppkperbicaraan pb ";
				sql += " WHERE ob1.id_ob = ob.id_ob and n.id_ob = ob1.id_ob "
						+ " and ob.id_permohonansimati = '" + id_permohonansimati + "' ";
				sql += " AND n.id_notisobmst = mst.id_notisobmst ";
				sql += " AND npb.id_notisobmst = mst.id_notisobmst ";
				sql += " AND npb.id_perbicaraan = pb.id_perbicaraan ";
				sql += " AND NVL(npb.flag_jenis_notis,0) = '0' ";
				sql += " AND npb.id_perbicaraan = pb.id_perbicaraan " +
						" AND ob.status_ob = 1  AND mst.JENIS_SERAH is not null ";
				sql += " AND pb.id_perbicaraan = '" + id_perbicaraan + "'";
				sql += " ORDER BY ob.nama_ob ";
				
				myLogger.info("sqlsetListPenerimaNotis = "+sql);
				ResultSet rx = stmtN.executeQuery(sql);

				Hashtable h;
				int bil = 1;
				int countPenerimaNotis = 0;

				while (rx.next()) {
					h = new Hashtable();
					// idOB = rx.getString("id_ob");
					
					countPenerimaNotis = rx.getInt(1);
//					myLogger.info("countPenerimaNotis = "+countPenerimaNotis);
					h.put("bil", bil);
					h.put("countPenerimaNotis", countPenerimaNotis);
					
					listCountPenerimaNotis.addElement(h);
					bil++;
				}

			} finally {
				if (db != null)
					db.close();
			}
		}
	
		//aishahlatip 01082017
		// count OB :: SEKSYEN 8 & SEKSYEN 17 ::
		public static void setCountSemuaOB(String id_permohonansimati,
				String id_simati, String seksyen, String idperbicaraan) throws Exception {

			Db db = null;
			listCountOB.clear();
			String sql = "";

			try {

				db = new Db();

				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql = " SELECT SUM (COUNT_OB) FROM ( " +
					  " SELECT COUNT(ob.id_ob) as COUNT_OB ";
				sql += "FROM Tblppkobpermohonan ob,tblppkob ob1, Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
				sql += "WHERE ob1.id_permohonansimati = sm.id_permohonansimati "
						+ " and ob.id_ob = ob1.id_ob and ob.id_permohonansimati = '"
						+ id_permohonansimati + "'";
				sql += " AND ob.id_tarafkptg = tr.id_tarafkptg ";
				sql += "AND sm.id_permohonan = p.id_permohonan ";
				sql += " AND NVL(ob.status_hidup,0) = 0 ";
				sql += " AND p.id_status <> '169' ";
				sql += " AND ob.id_simati = '" + id_simati + "' ";
				sql += " AND ob.status_ob = 1 "; //hanya OB DEWASA/WARAS dikeluarkan
				sql += " AND ob.id_tarafkptg NOT IN (2,14) "; //KELUARKAN SENARAI PEMIUTANG & SAKSI

				if (!seksyen.equals("8")) {
					sql += " AND NVL(p.no_subjaket,0) <= '"
							+ getNoSubjaketByIdPermohonan(getIdPermohonanByIdPermohonanSimati(id_permohonansimati))
							+ "'";
				}
				
				sql += "UNION ";
				
				/** ADD BY PEJE - UNION SQL GET SAKSI **/
				sql += "SELECT  count(ob.id_ob) as COUNT_OB ";
				sql += "FROM Tblppkobpermohonan ob,tblppkob ob1, Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
				sql += "WHERE ob1.id_permohonansimati = sm.id_permohonansimati "
						+ " and ob.id_ob = ob1.id_ob and ob.id_permohonansimati = '" + id_permohonansimati + "'";
				sql += " and ob1.id_permohonansimati = '" + id_permohonansimati + "'";
				sql += " AND ob.id_tarafkptg = tr.id_tarafkptg ";
				sql += "AND sm.id_permohonan = p.id_permohonan ";
				sql += " AND NVL(ob.status_hidup,0) = 0 ";
				sql += " AND p.id_status <> '169' ";
				sql += " AND ob.id_simati = '" + id_simati + "' ";
				sql += " AND ob.id_tarafkptg = 14 ";
				sql += " AND ob.status_ob = 1 )"; //hanya OB DEWASA/WARAS dikeluarkan
				

				//myLogger.info("COUNT LIST OB : " + sql);
				ResultSet rs = stmt.executeQuery(sql);

				Hashtable h;
				int bil = 1;
				int countAllOB = 0;

				while (rs.next()) {

					h = new Hashtable();

					countAllOB = rs.getInt(1);
					h.put("countAllOB", countAllOB);
					myLogger.info("countAllOB :::: : " + countAllOB);
					
					listCountOB.addElement(h);
					bil++;
				}
			} finally {
				if (db != null)
					db.close();
			}
		}// close semua count OB
		
		
				//aishahlatip 02082017
				// senarai Boleh Hantar ke OB :: SEKSYEN 8 & SEKSYEN 17 ::
				public static void setSemuaOB4HantarNotis(String id_permohonansimati,
						String id_simati, String seksyen, String idperbicaraan) throws Exception {
					
					
				/*	System.out.println("id_permohonansimati=="+id_permohonansimati);
					System.out.println("id_simati=="+id_simati);
					System.out.println("idperbicaraan=="+idperbicaraan);
					System.out.println("seksyen=="+seksyen);*/

					Db db = null;
					listCanSendOB.clear();
					String sql = "";

					try {

						db = new Db();

						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						
						sql = "SELECT ob.id_ob, ob.nama_ob, ob.no_kp_baru, ob.no_kp_lama,ob.no_kp_lain ";
						sql += "FROM Tblppkobpermohonan ob,tblppkob ob1, Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
						sql += "WHERE ob1.id_permohonansimati = sm.id_permohonansimati "
								+ " and ob.id_ob = ob1.id_ob and ob.id_permohonansimati = '"
								+ id_permohonansimati + "'";
						sql += " AND ob.id_tarafkptg = tr.id_tarafkptg ";
						sql += "AND sm.id_permohonan = p.id_permohonan ";
						sql += " AND NVL(ob.status_hidup,0) = 0 ";
						sql += " AND p.id_status <> '169' ";
						sql += " AND ob.id_simati = '" + id_simati + "' ";
						sql += " AND ob.status_ob = 1 "; //hanya OB DEWASA/WARAS dikeluarkan
						/** ADD BY PEJE - KELUARKAN SENARAI PEMIUTANG & SAKSI**/
						sql += " AND ob.id_tarafkptg NOT IN (2,14) ";
					

						if (!seksyen.equals("8")) {
							sql += " AND NVL(p.no_subjaket,0) <= '"
									+ getNoSubjaketByIdPermohonan(getIdPermohonanByIdPermohonanSimati(id_permohonansimati))
									+ "'";
						}
						
						sql += "UNION ";
						sql += "SELECT ob.id_ob,ob.nama_ob ,  ob.no_kp_baru, ob.no_kp_lama,ob.no_kp_lain ";
						sql += "FROM Tblppkobpermohonan ob,tblppkob ob1, Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
						sql += "WHERE ob1.id_permohonansimati = sm.id_permohonansimati "
								+ " and ob.id_ob = ob1.id_ob and ob.id_permohonansimati = '" + id_permohonansimati + "'";
						sql += " and ob1.id_permohonansimati = '" + id_permohonansimati + "'";
						sql += " AND ob.id_tarafkptg = tr.id_tarafkptg ";
						sql += "AND sm.id_permohonan = p.id_permohonan ";
						sql += " AND NVL(ob.status_hidup,0) = 0 ";
						sql += " AND p.id_status <> '169' ";
						sql += " AND ob.id_simati = '" + id_simati + "' ";
						sql += " AND ob.id_tarafkptg = 14 ";
						sql += " AND ob.status_ob = 1 "; //hanya OB DEWASA/WARAS dikeluarkan
						
						
						

						myLogger.info("COUNT LIST OB  x: " + sql);
						ResultSet rs = stmt.executeQuery(sql);

						Hashtable h;
						int bil = 1;
						String id_ob = "";
						String nama_ob = "";
						String no_kp_baru = "";
						String no_kp_lama = "";
						String no_kp_lain = "";

						while (rs.next()) {

							h = new Hashtable();

							h.put("id_ob_can", rs.getString("id_ob") == null ? ""
									: rs.getString("id_ob"));
							
							h.put("nama_ob_can", rs.getString("nama_ob") == null ? ""
									: rs.getString("nama_ob"));
							
							h.put("no_kp_baru", rs.getString("no_kp_baru") == null ? ""
									: rs.getString("no_kp_baru"));
							
							h.put("no_kp_lama", rs.getString("no_kp_lama") == null ? ""
									: rs.getString("no_kp_lama"));
							
							h.put("no_kp_lain", rs.getString("no_kp_lain") == null ? ""
									: rs.getString("no_kp_lain"));
							
							
							//myLogger.info("id_ob :::: : " + id_ob);
							
							listCanSendOB.addElement(h);
							bil++;
						}
					} finally {
						if (db != null)
							db.close();
					}
				}// close semua count OB
				
				
				//aishahlatip 02082017
				// senarai Boleh Hantar ke OB :: SEKSYEN 8 & SEKSYEN 17 ::
				public static void setMaklumatOBhidden(String id_permohonansimati,
						String id_simati, String seksyen, String idperbicaraan, String id_ob_hidden) throws Exception {
					
					
				/*	System.out.println("id_permohonansimati=="+id_permohonansimati);
					System.out.println("id_simati=="+id_simati);
					System.out.println("idperbicaraan=="+idperbicaraan);
					System.out.println("seksyen=="+seksyen);*/

					Db db = null;
					listOBhidden.clear();
					String sql = "";

					try {

						db = new Db();

						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						
						sql = "SELECT ob.id_ob, ob.nama_ob, ob.no_kp_baru, ob.no_kp_lama,ob.no_kp_lain ";
						sql += "FROM Tblppkobpermohonan ob,tblppkob ob1, Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
						sql += "WHERE ob1.id_permohonansimati = sm.id_permohonansimati "
								+ " and ob.id_ob = ob1.id_ob and ob.id_permohonansimati = '"
								+ id_permohonansimati + "'";
						sql += " AND ob.id_tarafkptg = tr.id_tarafkptg ";
						sql += "AND sm.id_permohonan = p.id_permohonan ";
						sql += " AND NVL(ob.status_hidup,0) = 0 ";
						sql += " AND p.id_status <> '169' ";
						sql += " AND ob.id_simati = '" + id_simati + "' ";
						sql += " AND ob.status_ob = 1 "; //hanya OB DEWASA/WARAS dikeluarkan
						/** ADD BY PEJE - KELUARKAN SENARAI PEMIUTANG & SAKSI**/
						sql += " AND ob.id_tarafkptg NOT IN (2,14) ";
						sql += " AND ob.id_ob = '"+id_ob_hidden+"' ";

						if (!seksyen.equals("8")) {
							sql += " AND NVL(p.no_subjaket,0) <= '"
									+ getNoSubjaketByIdPermohonan(getIdPermohonanByIdPermohonanSimati(id_permohonansimati))
									+ "'";
						}
						
						sql += "UNION ";
						sql += "SELECT ob.id_ob,ob.nama_ob ,  ob.no_kp_baru, ob.no_kp_lama,ob.no_kp_lain ";
						sql += "FROM Tblppkobpermohonan ob,tblppkob ob1, Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
						sql += "WHERE ob1.id_permohonansimati = sm.id_permohonansimati "
								+ " and ob.id_ob = ob1.id_ob and ob.id_permohonansimati = '" + id_permohonansimati + "'";
						sql += " and ob1.id_permohonansimati = '" + id_permohonansimati + "'";
						sql += " AND ob.id_tarafkptg = tr.id_tarafkptg ";
						sql += "AND sm.id_permohonan = p.id_permohonan ";
						sql += " AND NVL(ob.status_hidup,0) = 0 ";
						sql += " AND p.id_status <> '169' ";
						sql += " AND ob.id_simati = '" + id_simati + "' ";
						sql += " AND ob.id_tarafkptg = 14 ";
						sql += " AND ob.status_ob = 1 "; //hanya OB DEWASA/WARAS dikeluarkan
						sql += " AND ob.id_ob = '"+id_ob_hidden+"' ";
						
						

						myLogger.info("COUNT LIST OB  x: " + sql);
						ResultSet rs = stmt.executeQuery(sql);

						Hashtable h;
						int bil = 1;
						

						while (rs.next()) {

							h = new Hashtable();

							h.put("id_ob_can", rs.getString("id_ob") == null ? ""
									: rs.getString("id_ob"));
							
							h.put("nama_ob_can", rs.getString("nama_ob") == null ? ""
									: rs.getString("nama_ob"));
							
							System.out.println("rs.getString(nama_ob)==="+rs.getString("nama_ob"));
							
							h.put("no_kp_baru", rs.getString("no_kp_baru") == null ? ""
									: rs.getString("no_kp_baru"));
							
							h.put("no_kp_lama", rs.getString("no_kp_lama") == null ? ""
									: rs.getString("no_kp_lama"));
							
							h.put("no_kp_lain", rs.getString("no_kp_lain") == null ? ""
									: rs.getString("no_kp_lain"));
							
							
							myLogger.info("id_ob :::: : " + rs.getString("id_ob"));
							myLogger.info("nama_ob :::: : " + rs.getString("nama_ob"));
							myLogger.info("no_kp_baru :::: : " + rs.getString("no_kp_baru"));
							
							listOBhidden.addElement(h);
							bil++;
						}
					} finally {
						if (db != null)
							db.close();
					}
				}// close semua count OB
				
				
				

	// LIST PENERIMA NOTIS selected
	public static void setSelectedOB(String id_perbicaraan,
			String id_permohonansimati, String id_simati) throws Exception {

		Db db = null;
		selectedOB.clear();
		String sql = "";
		String idOB = "";
		String icYEAR = "";
		String icMONTH = "";
		String icDAY = "";
		String MONTH = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			SQLRenderer r = new SQLRenderer();

			/*
			 * sql =
			 * "SELECT n.id_notisobdtl, n.id_notisobmst, pb.id_perbicaraan, ";
			 * sql +=
			 * "npb.id_notisperbicaraan, n.nama_penerima, ob.nama_ob, ob.id_ob "
			 * ; sql +=
			 * "FROM Tblppknotisobdtl n, Tblppkob ob,Tblppknotisobmst mst, ";
			 * sql += "Tblppknotisperbicaraan npb, Tblppkperbicaraan pb "; sql
			 * +=
			 * "WHERE n.id_ob = ob.id_ob  AND n.id_notisobmst = mst.id_notisobmst "
			 * ; sql +=
			 * "AND npb.id_notisobmst = mst.id_notisobmst  AND npb.id_perbicaraan = pb.id_perbicaraan "
			 * ; sql += "AND pb.id_perbicaraan = '"+id_perbicaraan+"'" ; sql +=
			 * "AND NVL(ob.status_hidup,0) = 0 "; sql +=
			 * "ORDER BY ob.nama_ob DESC ";
			 */

			sql = "SELECT n.id_notisobdtl, n.id_notisobmst, pb.id_perbicaraan, ";
			sql += "npb.id_notisperbicaraan, n.nama_penerima, ob.nama_ob, ob.id_ob ";
			sql += "FROM Tblppknotisobdtl n, Tblppkob ob1,Tblppkobpermohonan ob,Tblppknotisobmst mst, ";
			sql += "Tblppknotisperbicaraan npb, Tblppkperbicaraan pb ";
			sql += "WHERE ob1.id_ob = ob.id_ob AND ob.id_permohonansimati = '"
					+ id_permohonansimati
					+ "' "
					+ " and  n.id_ob = ob1.id_ob  AND n.id_notisobmst = mst.id_notisobmst ";
			sql += "AND npb.id_notisobmst = mst.id_notisobmst  AND npb.id_perbicaraan = pb.id_perbicaraan ";
			sql += "AND pb.id_perbicaraan = '" + id_perbicaraan + "'";
			sql += "AND NVL(ob.status_hidup,0) = 0 ";
			//sql += "AND n.nama_penerima is not null ";
			
			sql += "ORDER BY ob.nama_ob DESC ";

			myLogger.info("SET SELECTED OB NOTIS :XXXXXXXXXXXXXXXXXXXXXXXXx" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()) {

				if (i == 0) {
					idOB += "ALL(" + rs.getString("id_ob");
				} else {
					idOB += "," + rs.getString("id_ob");
				}
				i++;

			}// close while

			if (idOB != "") {
				idOB += ")";
			}

			// get selected
			SQLRenderer rN = new SQLRenderer();
			sql = " SELECT distinct ob.nama_ob,ob.id_ob,ob.no_kp_baru,ob.umur,ob.no_kp_lama,ob.no_kp_lain,ob.emel ";
			sql += " FROM Tblppkob ob1,Tblppkobpermohonan ob,Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
			sql += " WHERE ob1.id_ob = ob.id_ob and ob.id_permohonansimati = '"
					+ id_permohonansimati + "' "
					+ " and ob1.id_permohonansimati = sm.id_permohonansimati ";
			sql += " AND sm.id_permohonan = p.id_permohonan ";
			sql += " AND p.id_status <> '169' ";
			sql += " AND ob.id_simati = '" + id_simati + "'";
			sql += " AND NVL(p.no_subjaket,0) <= '"
					+ getNoSubjaketByIdPermohonan(getIdPermohonanByIdPermohonanSimati(id_permohonansimati))
					+ "'";
			sql += " AND ob.id_tarafkptg = tr.id_tarafkptg ";
			sql += " AND ob.id_tarafkptg != 14 ";
			sql += " AND NVL(ob.status_hidup,0) = 0 ";
			sql += " AND NVL(ob.status_ob,0) != 4 ";
			sql += " AND NVL(ob.umur,18)  >= 18 ";
			if (idOB != "") {
				sql += " AND ob.id_ob != " + idOB;
			}
			
			/** ADD BY PEJE - TO GET SAKSI ONLY FOR CURRENT PERMOHONAN */
			sql += " UNION";
			
			sql += " SELECT distinct ob.nama_ob,ob.id_ob,ob.no_kp_baru,ob.umur,ob.no_kp_lama,ob.no_kp_lain,ob.emel ";
			sql += " FROM Tblppkob ob1,Tblppkobpermohonan ob,Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
			sql += " WHERE ob1.id_ob = ob.id_ob and ob.id_permohonansimati = '"
					+ id_permohonansimati + "' "
					+ " and ob1.id_permohonansimati = sm.id_permohonansimati ";
			sql += " AND ob1.id_permohonansimati = '" + id_permohonansimati + "' ";
			sql += " AND sm.id_permohonan = p.id_permohonan ";
			sql += " AND p.id_status <> '169' ";
			sql += " AND ob.id_simati = '" + id_simati + "'";
			sql += " AND NVL(p.no_subjaket,0) <= '"
					+ getNoSubjaketByIdPermohonan(getIdPermohonanByIdPermohonanSimati(id_permohonansimati))
					+ "'";
			sql += " AND ob.id_tarafkptg = tr.id_tarafkptg ";
			sql += " AND ob.id_tarafkptg = 14 ";
			sql += " AND NVL(ob.status_hidup,0) = 0 ";
			sql += " AND NVL(ob.status_ob,0) != 4 ";
			sql += " AND NVL(ob.umur,18)  >= 18 ";
			if (idOB != "") {
				sql += " AND ob.id_ob != " + idOB;
			}
			
			sql += " ORDER BY nama_ob asc ";

			ResultSet rx = stmt.executeQuery(sql);
			
			System.out.println("::::::::::sql=========="+sql);
			
			Hashtable h;
			int bil = 1;
			String year = "";
			String x = "";

			while (rx.next()) {
				h = new Hashtable();

				if (rx.getString("umur") != null && rx.getString("umur") != "") {

					h.put("age", rx.getInt("umur"));

				} else if (rx.getString("no_kp_baru") != null
						&& rx.getString("no_kp_baru") != "") {

					x = rx.getString("no_kp_baru").substring(0, 2);

					int z = 0;

					if (x != "") {
						z = Integer.parseInt(x);
					}

					icYEAR = rx.getString("no_kp_baru").substring(0, 2);
					icMONTH = rx.getString("no_kp_baru").substring(2, 4);
					icDAY = rx.getString("no_kp_baru").substring(4, 6);

					if (z <= 20) {
						year = "20" + icYEAR;
					} else {
						year = "19" + icYEAR;
					}

					int y = Integer.parseInt(year);
					int m = Integer.parseInt(icMONTH) - 1;
					int d = Integer.parseInt(icDAY);

					Calendar cal = new GregorianCalendar(y, m, d);
					Calendar now = new GregorianCalendar();

					int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
					if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
							|| (cal.get(Calendar.MONTH) == now
									.get(Calendar.MONTH) && cal
									.get(Calendar.DAY_OF_MONTH) > now
									.get(Calendar.DAY_OF_MONTH))) {
						res--;
					}

					int notnull = 0;
					int cantzero = 1;

					if (res < 0) {
						h.put("age", notnull);
					} else if (res == 0) {
						h.put("age", cantzero);
					} else {
						h.put("age", res);
					}

				} else {
					h.put("age", "");
				}

				h.put("bil", bil);
				h.put("no_kp_baru1", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru").substring(0, 6));
				h.put("no_kp_baru2", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru").substring(6, 8));
				h.put("no_kp_baru3", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru").substring(8, 12));
				h.put("no_kp_lama", rx.getString("no_kp_lama") == null ? ""
						: rx.getString("no_kp_lama"));
				h.put("id_ob", rx.getString("id_ob") == null ? "" : rx
						.getString("id_ob"));
				h.put("no_kp_baru", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru"));
				h.put("nama_ob", rx.getString("nama_ob") == null ? "" : rx
						.getString("nama_ob"));
				h.put("no_kp_lain", rx.getString("no_kp_lain") == null ? ""
						: rx.getString("no_kp_lain"));
				h.put("emel", rx.getString("emel") == null ? "" : rx.getString("emel"));
				selectedOB.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	// DATA OB PENERIMA NOTIS
	public static void setDataOBNotis(String id_obdtl,
			String id_permohonansimati) throws Exception {

		Db db = null;
		listDataOBNotis.clear();
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("n.id_notisobdtl");
			r.add("n.id_notisobmst");
			r.add("n.id_ob");

			r.add("n.nama_penerima");
			r.add("n.no_kp_baru");
			r.add("n.no_kp_lama");
			r.add("n.no_kp_lain");

			r.add("ob.nama_ob");

			r.add("mst.nama_penghantar_notis");
			r.add("mst.tarikh_serahan");
			r.add("mst.catatan");
			r.add("mst.status_serah");
			r.add("mst.jenis_serah");
			r.add("mst.no_surat_daftar");

			r.add("pn.kod_penghantar_notis");
			r.add("mst.id_penghantarnotis");

			r.add("n.id_ob", r.unquote("ob.id_ob"));
			r.add("n.id_notisobmst", r.unquote("mst.id_notisobmst"));
			r.add("mst.id_penghantarnotis", r.unquote("pn.id_penghantarnotis"));

			r.add("n.id_notisobdtl", id_obdtl);

			// sql =
			// r.getSQLSelect("Tblppknotisobdtl n, Tblppkob ob,Tblppknotisobmst mst, Tblppkrujpenghantarnotis pn");

			sql = "SELECT N.ID_NOTISOBDTL, N.ID_NOTISOBMST, N.ID_OB, N.NAMA_PENERIMA, N.NO_KP_BARU, "
					+ " N.NO_KP_LAMA, N.NO_KP_LAIN, OB.NAMA_OB, MST.NAMA_PENGHANTAR_NOTIS, "
					+ " MST.TARIKH_SERAHAN, MST.CATATAN, MST.STATUS_SERAH, MST.JENIS_SERAH, "
					+ " MST.NO_SURAT_DAFTAR, PN.KOD_PENGHANTAR_NOTIS, MST.ID_PENGHANTARNOTIS , MST.NAMA_PENGHANTAR_LAIN , MST.EMEL, MST.TARIKH_EMEL "
					+ " FROM TBLPPKNOTISOBDTL N, TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB,TBLPPKNOTISOBMST MST, "
					+ " TBLPPKRUJPENGHANTARNOTIS PN "
					+ " WHERE OB.ID_PERMOHONANSIMATI = '"
					+ id_permohonansimati
					+ "' AND OB1.ID_OB = OB.ID_OB "
					+ " AND N.ID_OB = OB1.ID_OB  "
					+ " AND N.ID_NOTISOBMST = MST.ID_NOTISOBMST  "
					+ " AND MST.ID_PENGHANTARNOTIS = PN.ID_PENGHANTARNOTIS(+)  "
					+ " AND N.ID_NOTISOBDTL = '" + id_obdtl + "' ";
			myLogger.info("-----------NOTIS setDataOBNotis("
					+ sql.toUpperCase());
			ResultSet rx = stmt.executeQuery(sql);
			Hashtable h;

			while (rx.next()) {

				h = new Hashtable();
				h.put("id_notisobdtl",
						rx.getString("id_notisobdtl") == null ? "" : rx
								.getString("id_notisobdtl"));
				h.put("id_notisobmst",
						rx.getString("id_notisobmst") == null ? "" : rx
								.getString("id_notisobmst"));
				h.put("id_ob", rx.getString("id_ob") == null ? "" : rx
						.getString("id_ob"));
				h.put("kod_penghantar_notis", rx
						.getString("kod_penghantar_notis") == null ? "" : rx
						.getString("kod_penghantar_notis"));
				h.put("id_penghantarnotis",
						rx.getString("id_penghantarnotis") == null ? "" : rx
								.getString("id_penghantarnotis"));
				h.put("nama_penerima",
						rx.getString("nama_penerima") == null ? "" : rx
								.getString("nama_penerima"));
				h.put("no_kp_baru1", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru").substring(0, 6));
				h.put("no_kp_baru2", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru").substring(6, 8));
				h.put("no_kp_baru3", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru").substring(8, 12));
				h.put("no_kp_baru", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru"));
				h.put("nama_ob", rx.getString("nama_ob") == null ? "" : rx
						.getString("nama_ob"));
				h.put("catatan", rx.getString("catatan") == null ? "" : rx
						.getString("catatan"));
				h.put("status_serah", rx.getString("status_serah") == null ? ""
						: rx.getString("status_serah"));
				h.put("jenis_serah", rx.getString("jenis_serah") == null ? ""
						: rx.getString("jenis_serah"));
				h.put("nama_penghantar_notis", rx
						.getString("nama_penghantar_notis") == null ? "" : rx
						.getString("nama_penghantar_notis"));
				h.put("tarikh_serahan",
						rx.getDate("tarikh_serahan") == null ? "" : Format
								.format(rx.getDate("tarikh_serahan")));
				h.put("no_surat_daftar",
						rx.getString("no_surat_daftar") == null ? "" : rx
								.getString("no_surat_daftar"));
				h.put("no_kp_lama", rx.getString("no_kp_lama") == null ? ""
						: rx.getString("no_kp_lama"));
				h.put("no_kp_lain", rx.getString("no_kp_lain") == null ? ""
						: rx.getString("no_kp_lain"));
				
				h.put("nama_penghantar_lain", rx.getString("NAMA_PENGHANTAR_LAIN") == null ? ""
						: rx.getString("NAMA_PENGHANTAR_LAIN"));
				
				h.put("alamatEmel", rx.getString("emel") == null ? "" : rx.getString("emel"));
				h.put("tarikhHantar", rx.getDate("tarikh_emel") == null ? "" : Format.format(rx.getDate("tarikh_emel")));
				
				listDataOBNotis.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}// close data ob notis

	// update laporan notis
	public static void updateLaporan(Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";

		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			String id_obdtl = (String) data.get("idOBDTL");

			String nama_penerima = (String) data.get("nama_penerima");
			String noKP = (String) data.get("noKP");
			String kplama = (String) data.get("kplama");
			String kplain = (String) data.get("kplain");

			String jenis_serah = (String) data.get("jenis_serah");
			String jenis_status = (String) data.get("jenis_status");
			String daftar_pos = (String) data.get("daftar_pos");

			String nama_penghantar = (String) data.get("nama_penghantar");
			String id_penghantar = (String) data.get("id_penghantar");

			String tarikh_serahan = (String) data.get("tarikh_serahan");
			String catatan = (String) data.get("catatan");

			String id_pegawai = (String) data.get("id_pegawai");
			String namaLain = (String) data.get("namaLain");//aishahlatip

			String TS = "to_date('" + tarikh_serahan + "','dd/MM/yyyy')";
			
			

			String id_notisobmst = "";

			// update table obdtl
			SQLRenderer r = new SQLRenderer();
			r.update("id_notisobdtl", id_obdtl);
			r.add("nama_penerima", nama_penerima);
			r.add("no_kp_baru", noKP);
			r.add("no_kp_lama", kplama);
			r.add("no_kp_lain", kplain);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			r.add("id_kemaskini", id_pegawai);
			sql = r.getSQLUpdate("Tblppknotisobdtl");
			stmt.executeUpdate(sql);

			// get id obmst
			SQLRenderer rMT = new SQLRenderer();
			rMT.add("id_notisobdtl");
			rMT.add("id_notisobmst");
			rMT.add("id_notisobdtl", id_obdtl);
			sql = rMT.getSQLSelect("Tblppknotisobdtl");
			ResultSet rsMT = stmt.executeQuery(sql);
			while (rsMT.next()) {
				id_notisobmst = rsMT.getString("id_notisobmst");
			}

			System.out.println("namaLain==="+namaLain);
			// update table mst
			SQLRenderer rMST = new SQLRenderer();
			rMST.update("id_notisobmst", id_notisobmst);
			rMST.add("jenis_serah", jenis_serah);
			rMST.add("status_serah", jenis_status);
			rMST.add("no_surat_daftar", daftar_pos);
			rMST.add("nama_penghantar_notis", nama_penghantar);
			rMST.add("id_penghantarnotis", id_penghantar);
			rMST.add("tarikh_serahan", rMST.unquote(TS));
			rMST.add("catatan", catatan);
			rMST.add("tarikh_kemaskini", r.unquote("sysdate"));
			rMST.add("id_kemaskini", id_pegawai);
			rMST.add("NAMA_PENGHANTAR_LAIN", namaLain);//aishahlatip
			sql = rMST.getSQLUpdate("Tblppknotisobmst");
			
			System.out.println("******sql==="+sql);
			
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ se.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

	}// close update laporan Notis

	// get name by dropdown
	public static Vector getNameByDropdown(String idDropdown) throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT p.id_penjaga, p.nama_penjaga ";
			sql += "FROM Tblppkpenjaga p ";
			sql += "WHERE p.id_penjaga = '" + idDropdown + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();

			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_penjaga", rs.getString("id_penjaga") == null ? ""
						: rs.getString("id_penjaga"));
				h.put("nama_penjaga", rs.getString("nama_penjaga") == null ? ""
						: rs.getString("nama_penjaga"));
				list.addElement(h);

			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}// get name by dropdown

	public static String addNotisTambah(HttpSession session, Hashtable data)
			throws Exception {

		Connection conn = null;
		Db db = null;
		String sql = "";
		String sql5 = "";
		String sql6 = "";

		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// generate other table id
			long id_notisobmst = DB.getNextID("TBLPPKNOTISOBMST_SEQ");
			long id_perbicaraan = DB.getNextID("TBLPPKPERBICARAAN_SEQ");
			idPerbicaraan = String.valueOf(id_perbicaraan);

			String bil = (String) data.get("bil_bicara");
			String id_permohonan = (String) data.get("id_permohonan");
			String id_fail = (String) data.get("id_fail");
			String id_suburusanstatusfail = (String) data
					.get("id_suburusanstatusfail");
			String id_keputusanpermohonan = (String) data
					.get("id_keputusanpermohonan");

			String id_ppkrujunit = (String) data.get("pegawai");
			String id_pejabatjkptg = (String) data.get("tempat_bicara");

			String jenispejabat = (String) data.get("jenispejabat");

			String negeri = (String) data.get("negeri");
			String id_masuk = (String) data.get("id_masuk");

			String tarikh_bicara = (String) data.get("tarikh_bicara");
			String masa_bicara = (String) data.get("masa_bicara");
			String alamat1 = (String) data.get("alamat_bicara1");
			String alamat2 = (String) data.get("alamat_bicara2");
			String alamat3 = (String) data.get("alamat_bicara3");
			String poskod = (String) data.get("poskod");
			String tarikh_notis = (String) data.get("tarikh_notis");

			// -- 09122009
			String socJenisWaktu = (String) data.get("socJenisWaktu");

			String nama_pegawai = "";
			String nama_pejabat = "";
			String id_pejabat = "";
			String tablePejabat = "";

			String TB = "to_date('" + tarikh_bicara + "','dd/MM/yyyy')";
			String TN = "to_date('" + tarikh_notis + "','dd/MM/yyyy')";

			// get nama pegawai n pejabat

			SQLRenderer rMT = new SQLRenderer();
			rMT.add("id_unitpsk");
			rMT.add("nama_pegawai");
			rMT.add("id_unitpsk", id_ppkrujunit);
			sql = rMT.getSQLSelect("Tblppkrujunit");
			ResultSet rsMT = stmt.executeQuery(sql);
			while (rsMT.next()) {
				nama_pegawai = rsMT.getString("nama_pegawai");
			}

			String id_bandar = "";

			if (!jenispejabat.equals("0")) {

				// get pejabat
				if (jenispejabat.equals("22")) {
					id_pejabat = "id_pejabatjkptg";
					tablePejabat = "Tblrujpejabatjkptg";
				} else if (jenispejabat.equals("2")) {
					id_pejabat = "id_pejabat";
					tablePejabat = "Tblrujpejabat";
				} else {
					id_pejabat = "id_pejabatjkptg";
					tablePejabat = "Tblrujpejabatjkptg";
				}

				SQLRenderer rMP = new SQLRenderer();
				rMP.add("nama_pejabat");
				rMP.add("id_bandar");
				rMP.add(id_pejabat, id_pejabatjkptg);
				sql = rMP.getSQLSelect(tablePejabat);

				ResultSet rsP = stmt.executeQuery(sql);
				while (rsP.next()) {
					nama_pejabat = rsP.getString("nama_pejabat");
					id_bandar = rsP.getString("id_bandar");
				}

			} else {
				nama_pejabat = id_pejabatjkptg;
			}

			String keterangan = "";

			if (id_bandar != "") {
				SQLRenderer rb = new SQLRenderer();
				rb.add("keterangan");
				rb.add("id_bandar", id_bandar);
				sql = rb.getSQLSelect("Tblrujbandar");

				ResultSet rsPb = stmt.executeQuery(sql);
				while (rsPb.next()) {
					keterangan = rsPb.getString("keterangan");
				}
			}

			// add data (notis perbicaraan) with bil = 1
			SQLRenderer r = new SQLRenderer();
			r.add("id_perbicaraan", id_perbicaraan);
			r.add("id_keputusanpermohonan", id_keputusanpermohonan);
			r.add("id_unitpsk", id_ppkrujunit);
			r.add("bil_bicara", bil);
			r.add("masa_bicara", masa_bicara);
			r.add("alamat_bicara1", alamat1);
			r.add("alamat_bicara2", alamat2);
			r.add("alamat_bicara3", alamat3);
			r.add("poskod", poskod);
			r.add("peg_pengendali", nama_pegawai);
			r.add("tempat_bicara", nama_pejabat);
			r.add("tarikh_bicara", r.unquote(TB));
			r.add("tarikh_notis", r.unquote(TN));
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("id_negeribicara", negeri);
			r.add("id_masuk", id_masuk);

			// -- 09122009
			r.add("jenis_masa_bicara", socJenisWaktu);

			if (keterangan != "") {
				r.add("bandar", keterangan);
			} else {
				r.add("bandar", "");
			}

			if (!jenispejabat.equals("0")) {
				r.add("id_pejabat", id_pejabatjkptg);
			} else {
				r.add("id_pejabat", "0");
			}

			r.add("id_jenispejabat", jenispejabat);
			sql = r.getSQLInsert("Tblppkperbicaraan");
			stmt.executeUpdate(sql);

			// create table notisobmst
			SQLRenderer rMST = new SQLRenderer();
			rMST.add("id_notisobmst", id_notisobmst);
			rMST.add("id_masuk", id_masuk);
			rMST.add("tarikh_masuk", rMST.unquote("sysdate"));
			sql = rMST.getSQLInsert("Tblppknotisobmst");
			stmt.executeUpdate(sql);

			// create child table
			SQLRenderer r1 = new SQLRenderer();
			r1.add("id_perbicaraan", id_perbicaraan);
			r1.add("id_notisobmst", id_notisobmst);
			r1.add("flag_jenis_notis", 0);
			r1.add("id_masuk", id_masuk);
			r1.add("tarikh_masuk", r1.unquote("sysdate"));
			sql = r1.getSQLInsert("Tblppknotisperbicaraan");
			stmt.executeUpdate(sql);

			// tukar status permohonan diteruskan => notis perbicaraan
			int status_notisperbicaraan = 18;
			/*
			 * SQLRenderer rP = new SQLRenderer();
			 * rP.update("id_permohonan",id_permohonan); rP.add("id_status",
			 * status_notisperbicaraan); rP.add("id_kemaskini",id_masuk);
			 * rP.add("tarikh_kemaskini", rP.unquote("sysdate")); sql =
			 * rP.getSQLUpdate("Tblppkpermohonan"); stmt.executeUpdate(sql);
			 */

			// update n add tblrujsuburusanstatus
			/*
			 * SQLRenderer r6 = new SQLRenderer();
			 * r6.update("id_Suburusanstatusfail",id_suburusanstatusfail);
			 * r6.add("AKTIF",0); r6.add("ID_KEMASKINI",id_masuk);
			 * r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate")); sql6 =
			 * r6.getSQLUpdate("tblrujsuburusanstatusfail");
			 * stmt.executeUpdate(sql6);
			 * 
			 * 
			 * SQLRenderer r5 = new SQLRenderer();
			 * r5.add("ID_SUBURUSANSTATUSFAIL"
			 * ,DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			 * r5.add("ID_PERMOHONAN",id_permohonan); r5.add("ID_FAIL",id_fail);
			 * r5.add("ID_SUBURUSANSTATUS",354); r5.add("AKTIF",1);
			 * r5.add("ID_MASUK",id_masuk); r5.add("ID_KEMASKINI",id_masuk);
			 * r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
			 * r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate")); sql5 =
			 * r5.getSQLInsert("tblrujsuburusanstatusfail");
			 * stmt.executeUpdate(sql5);
			 */

			conn.commit();

			Statement stmtfail = db.getStatement();
			SQLRenderer rfail = new SQLRenderer();
			/*String sql_fail = "SELECT F.NO_FAIL FROM  TBLPPKPERBICARAAN B,"
					+ " TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERMOHONAN P,TBLPFDFAIL F "
					+ " WHERE B.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN "
					+ " AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND P.ID_FAIL = F.ID_FAIL AND B.ID_PERBICARAAN = '"
					+ id_perbicaraan + "'";
			ResultSet rsfail = stmtfail.executeQuery(sql_fail);
			String no_fail = "";
			while (rsfail.next()) {
				if (rsfail.getString("NO_FAIL") != null) {
					no_fail = rsfail.getString("NO_FAIL");
				}
			}*/
			
			String sql_fail = "SELECT F.NO_FAIL, S.NAMA_SIMATI  FROM  TBLPPKPERBICARAAN B, " +
					" TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERMOHONAN P,TBLPFDFAIL F , TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI M " +
					" WHERE B.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN " +
					" AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN " +
					" AND P.ID_FAIL = F.ID_FAIL " +
					" AND S.ID_SIMATI = M.ID_SIMATI " +
					" AND M.ID_PERMOHONAN = KP.ID_PERMOHONAN " +
					" AND B.ID_PERBICARAAN = '"+ id_perbicaraan + "'";
			ResultSet rsfail = stmtfail.executeQuery(sql_fail);
			String no_fail = "";
			String nama_simati= "";
			while (rsfail.next()) {
				
				if (rsfail.getString("NO_FAIL") != null) {
					no_fail = rsfail.getString("NO_FAIL");
				}
				
				if (rsfail.getString("NAMA_SIMATI") != null) {
					nama_simati = rsfail.getString("NAMA_SIMATI");
				}
			}
			
			/** START ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE **/			
			String event_id = "PERBICARAAN PUSAKA KECIL BAGI FAIL " + no_fail;
			String event_text = "PERBICARAAN PUSAKA KECIL BAGI FAIL " + no_fail;
			String event_location = "";
			if (!"".equals(nama_pejabat))
				event_location = event_location + nama_pejabat;
			if (!"".equals(alamat1))
				event_location = event_location + ", " + alamat1;
			if (!"".equals(alamat2))
				event_location = event_location + ", " + alamat2;
			if (!"".equals(alamat3))
				event_location = event_location + ", " + alamat3;
			if (!"".equals(poskod))
				event_location = event_location + ", " + poskod;
			if (!"".equals(keterangan))
				event_location = event_location + " " + keterangan;			
			
			String userLoginPegawai = "";
			Statement stmtPegawai = db.getStatement();
			
			String sqlPegawai = "SELECT * FROM TBLPPKRUJUNIT, USERS WHERE UPPER(TBLPPKRUJUNIT.NAMA_PEGAWAI) = UPPER(USERS.USER_NAME) AND"
				+ " USERS.USER_TYPE = 'internal' AND TBLPPKRUJUNIT.ID_UNITPSK = '" + id_ppkrujunit + "'";
			
			ResultSet rsPegawai = stmtPegawai.executeQuery(sqlPegawai);		
			if (rsPegawai.next()) {
			if (rsPegawai.getString("USER_LOGIN") != null) {
				userLoginPegawai = rsPegawai.getString("USER_LOGIN");
				
				
				saveActivityEvent(userLoginPegawai, id_perbicaraan, event_text, event_location, tarikh_bicara, masa_bicara, socJenisWaktu);
				/** END ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE **/
				
			}else{
				
				//aishah edit on 5/5/2017
				String sqlPegawai2 = "SELECT * FROM TBLPPKRUJUNIT, USERS WHERE TBLPPKRUJUNIT.USER_ID = USERS.USER_ID AND"
				+ " USERS.USER_TYPE = 'internal' AND TBLPPKRUJUNIT.ID_UNITPSK = '" + id_ppkrujunit + "'";
				
				ResultSet rsPegawai2 = stmtPegawai.executeQuery(sqlPegawai2);	
				
				if (rsPegawai2.getString("USER_LOGIN") != null) {
					userLoginPegawai = rsPegawai.getString("USER_LOGIN");
					
					saveActivityEvent(userLoginPegawai, id_perbicaraan, event_text, event_location, tarikh_bicara, masa_bicara, socJenisWaktu);
					/** END ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE **/
				}
				
			}
			}

			

			//aishah add 26072017
			//###############add send email#########################
			 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			 Date date = new Date();  
			 myLogger.info("sysdate ==== "+formatter.format(date));  
			 if ((tarikh_bicara.compareTo(formatter.format(date)) > 0 ) || (tarikh_bicara.compareTo(formatter.format(date)) == 0 ) ){
				 myLogger.info("send email  >>>>>> tarikh_bicara : ");
				 hantarEmelTTPegawai(session,Long.toString(id_perbicaraan),no_fail,nama_simati);
				 
				//###############add send email#########################
				//hantarEmel(session,Long.toString(id_perbicaraan),no_fail,nama_simati);
			 }
			
//			addCalendarNotis(session, event_id, event_text, tarikh_bicara,id_ppkrujunit, id_perbicaraan + "");

			// :::SUB
			// ID_STATUS : status_notisperbicaraan
			// ID_SUBURUSAN : 354
			FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
			logic_A.kemaskiniSubUrusanStatusFail(session, id_permohonan,
					id_masuk, status_notisperbicaraan + "", "354", id_fail);

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ se.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
		return idPerbicaraan;

	}// close addNotis

	public static Vector getIdNotisPerbicaraan(String idp) throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT distinct max(pb.id_notisperbicaraan) as id_notisperbicaraan ";
			sql += "FROM Tblppknotisperbicaraan pb ";
			sql += "WHERE pb.id_perbicaraan = '" + idp + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();

			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_notisperbicaraan", rs
						.getString("id_notisperbicaraan") == null ? "" : rs
						.getString("id_notisperbicaraan"));
				list.addElement(h);

			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}// Get idnotisperbicaraan

	// get idkeputusanpermohonan
	public static Vector getDataOB(String id_ob, String id_permohonansimati)
			throws Exception {

		Db db = null;
		String sql = "";
		String icYEAR = "";
		String icMONTH = "";
		String icDAY = "";
		String MONTH = "";
		String year = "";
		String x = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ob.nama_ob, ob.id_negeri, ob.id_ob, ob.no_kp_baru, ob.no_kp_lama, ob.no_kp_lain, ob.jenis_kp, ";
			sql += "ob.jantina, ob.alamat_1, ob.alamat_2, ob.alamat_3, ob.bandar, ob.poskod, ob.jenis_agama, ob.jenis_warga, ob.umur, ob.id_bandar ";
			sql += "FROM Tblppkob ob1,tblppkobpermohonan ob ";
			sql += "WHERE ob1.id_ob = ob.id_ob and ob.id_permohonansimati = '"
					+ id_permohonansimati + "' " + " and ob.id_ob = '" + id_ob
					+ "'";

			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();

			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();

				if (rs.getString("umur") != null && rs.getString("umur") != "") {

					h.put("age", rs.getInt("umur"));

				} else if (rs.getString("no_kp_baru") != null
						&& rs.getString("no_kp_baru") != "") {

					x = rs.getString("no_kp_baru").substring(0, 2);

					int z = 0;

					if (x != "") {
						z = Integer.parseInt(x);
					}

					icYEAR = rs.getString("no_kp_baru").substring(0, 2);
					icMONTH = rs.getString("no_kp_baru").substring(2, 4);
					icDAY = rs.getString("no_kp_baru").substring(4, 6);

					if (z <= 20) {
						year = "20" + icYEAR;
					} else {
						year = "19" + icYEAR;
					}

					int y = Integer.parseInt(year);
					int m = Integer.parseInt(icMONTH) - 1;
					int d = Integer.parseInt(icDAY);

					Calendar cal = new GregorianCalendar(y, m, d);
					Calendar now = new GregorianCalendar();

					int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
					if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
							|| (cal.get(Calendar.MONTH) == now
									.get(Calendar.MONTH) && cal
									.get(Calendar.DAY_OF_MONTH) > now
									.get(Calendar.DAY_OF_MONTH))) {
						res--;
					}

					int notnull = 0;
					int cantzero = 1;

					if (res < 0) {
						h.put("age", notnull);
					} else if (res == 0) {
						h.put("age", cantzero);
					} else {
						h.put("age", res);
					}

				} else {
					h.put("age", "");
				}

				h.put("nama_ob", rs.getString("nama_ob") == null ? "" : rs
						.getString("nama_ob"));
				h.put("no_kp_baru1", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(0, 6));
				h.put("no_kp_baru2", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(6, 8));
				h.put("no_kp_baru3", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(8, 12));
				h.put("no_kp_baru", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru"));
				h.put("no_kp_lama", rs.getString("no_kp_lama") == null ? ""
						: rs.getString("no_kp_lama"));
				h.put("no_kp_lain", rs.getString("no_kp_lain") == null ? ""
						: rs.getString("no_kp_lain"));
				h.put("jenis_kp", rs.getString("jenis_kp") == null ? "" : rs
						.getString("jenis_kp"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("alamat_1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat_2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat_3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("jenis_agama", rs.getString("jenis_agama") == null ? ""
						: rs.getString("jenis_agama"));
				h.put("jenis_warga", rs.getString("jenis_warga") == null ? ""
						: rs.getString("jenis_warga"));
				h.put("id_negeri", rs.getString("id_negeri") == null ? "" : rs
						.getString("id_negeri"));
				h.put("id_bandar", rs.getString("id_bandar") == null ? "" : rs
						.getString("id_bandar"));

				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}// Get namaOB

	// list selected dropdown
	public static void setSelectedDropdown(String id_permohonansimati,
			String id_OBbwh18, String id_simati, String Seksyen)
			throws Exception {

		Db db = null;
		selectedDropdown.clear();
		String sql = "";
		String idOB = "";
		String icYEAR = "";
		String icMONTH = "";
		String icDAY = "";
		String MONTH = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			/*
			 * sql = "select a.id_ob, b.no_kp_baru, b.status_hidup "; sql +=
			 * "FROM tblppkpenjaga a, tblppkob b "; sql +=
			 * "WHERE a.id_ob = b.id_ob "; sql += "AND b.id_simati = '" +
			 * id_simati + "'"; sql +=
			 * " AND b.id_permohonansimati <= '"+id_permohonansimati+"'" ; sql
			 * += " AND a.id_obminor = '"+id_OBbwh18+"'"; sql +=
			 * " AND NVL(b.status_hidup,0) = 0  ";
			 */

			sql = "select a.id_ob, b.no_kp_baru, b.status_hidup ";
			sql += "FROM tblppkpenjaga a, tblppkob b1,tblppkobpermohonan b ";
			sql += "WHERE a.id_ob = b1.id_ob and b1.id_ob = b.id_ob "
					+ " and b.id_permohonansimati = '" + id_permohonansimati
					+ "'";
			sql += "AND b.id_simati = '" + id_simati + "'";
			sql += " AND b1.id_permohonansimati <= '" + id_permohonansimati
					+ "'";
			sql += " AND a.id_obminor = '" + id_OBbwh18 + "'";
			sql += " AND NVL(b.status_hidup,0) = 0  ";

			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()) {

				if (i == 0) {
					idOB += "ALL(" + rs.getString("id_ob");
				} else {
					idOB += "," + rs.getString("id_ob");
				}
				i++;

			}

			if (idOB != "") {
				idOB += ")";
			}

			// get selected
			Statement stmtN = db.getStatement();
			SQLRenderer rN = new SQLRenderer();

			sql = " SELECT distinct ob.nama_ob,ob.id_ob,ob.no_kp_baru,ob.umur ";
			sql += " FROM Tblppkob ob1,Tblppkobpermohonan ob,"
					+ " Tblppkpermohonansimati sm, Tblppkpermohonan p ";
			sql += " WHERE ob1.id_ob = ob.id_ob and ob.id_permohonansimati = '"
					+ id_permohonansimati + "' "
					+ " and ob1.id_permohonansimati = sm.id_permohonansimati ";
			sql += " AND sm.id_permohonan = p.id_permohonan ";
			sql += " AND p.id_status <> '169' ";
			sql += " AND ob.id_simati = '" + id_simati + "'";
			// sql +=" AND ob.id_permohonansimati <= '"+id_permohonansimati+"'"
			// ;
			if (!Seksyen.equals("8")) {
				sql += " AND NVL(p.no_subjaket,0) <= '"
						+ getNoSubjaketByIdPermohonan(getIdPermohonanByIdPermohonanSimati(id_permohonansimati))
						+ "'";
			}
			sql += " AND NVL(ob.status_hidup,0) = 0  ";
			sql += " AND NVL(ob.status_ob,0) != 4 ";
			sql += " AND ob.id_tarafkptg != ALL(14,2,8) ";
			sql += " AND NVL(ob.umur,18)  >= 18 ";
			if (idOB != "") {
				sql += " AND ob.id_ob != " + idOB;
			}
			sql += " ORDER BY ob.nama_ob asc ";

			ResultSet rx = stmtN.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			String year = "";
			String x = "";

			while (rx.next()) {

				h = new Hashtable();

				if (rx.getString("umur") != null && rx.getString("umur") != "") {

					h.put("age", rx.getInt("umur"));

				} else if (rx.getString("no_kp_baru") != null
						&& rx.getString("no_kp_baru") != "") {

					x = rx.getString("no_kp_baru").substring(0, 2);

					int z = 0;

					if (x != "") {
						z = Integer.parseInt(x);
					}

					icYEAR = rx.getString("no_kp_baru").substring(0, 2);
					icMONTH = rx.getString("no_kp_baru").substring(2, 4);
					icDAY = rx.getString("no_kp_baru").substring(4, 6);

					if (z <= 20) {
						year = "20" + icYEAR;
					} else {
						year = "19" + icYEAR;
					}

					int y = Integer.parseInt(year);
					int m = Integer.parseInt(icMONTH) - 1;
					int d = Integer.parseInt(icDAY);

					Calendar cal = new GregorianCalendar(y, m, d);
					Calendar now = new GregorianCalendar();

					int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
					if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
							|| (cal.get(Calendar.MONTH) == now
									.get(Calendar.MONTH) && cal
									.get(Calendar.DAY_OF_MONTH) > now
									.get(Calendar.DAY_OF_MONTH))) {
						res--;
					}

					int notnull = 0;
					int cantzero = 1;

					if (res < 0) {
						h.put("age", notnull);
					} else if (res == 0) {
						h.put("age", cantzero);
					} else {
						h.put("age", res);
					}

				} else {
					h.put("age", "");
				}

				h.put("bil", bil);
				h.put("no_kp_baru1", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru").substring(0, 6));
				h.put("no_kp_baru2", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru").substring(6, 8));
				h.put("no_kp_baru3", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru").substring(8, 12));
				h.put("id_ob", rx.getString("id_ob") == null ? "" : rx
						.getString("id_ob"));
				h.put("no_kp_baru", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru"));
				h.put("nama_ob", rx.getString("nama_ob") == null ? "" : rx
						.getString("nama_ob"));
				selectedDropdown.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	// get nama&nokp by dropdown
	public static Vector getNameNKPByDropdown(String id_ob,
			String id_permohonansimati) throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();

			sql = "SELECT b.id_ob, b.nama_ob, b.no_kp_baru, b.no_kp_lama, b.no_kp_lain ";
			sql += "FROM Tblppkob b1,tblppkobpermohonan b ";
			sql += "WHERE b.id_ob = b1.id_ob and b.id_permohonansimati = '"
					+ id_permohonansimati + "' " + " and b.id_ob = '" + id_ob
					+ "'";

			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();

			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_ob", rs.getString("id_ob") == null ? "" : rs
						.getString("id_ob"));
				h.put("nama_ob", rs.getString("nama_ob") == null ? "" : rs
						.getString("nama_ob"));
				h.put("no_kp_baru", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru"));
				h.put("no_kp_baru1", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(0, 6));
				h.put("no_kp_baru2", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(6, 8));
				h.put("no_kp_baru3", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(8, 12));
				h.put("no_kp_lama", rs.getString("no_kp_lama") == null ? ""
						: rs.getString("no_kp_lama"));
				h.put("no_kp_lain", rs.getString("no_kp_lain") == null ? ""
						: rs.getString("no_kp_lain"));
				list.addElement(h);

			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}// get nama&nokp by dropdown

	// LIST OB
	public static void setListOBatas18(String id_permohonansimati,
			String id_simati, String Seksyen) throws Exception {

		Db db = null;
		listOBatas18.clear();
		String sql = "";
		String icYEAR = "";
		String icMONTH = "";
		String icDAY = "";
		String MONTH = "";
		String year = "";
		String x = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT ob.id_ob, ob.id_simati, ob.nama_ob, ob.no_kp_baru, ob.no_kp_lama, ";
			sql += "ob.status_hidup, ob.jenis_kp, ob.no_kp_lain, ob.no_surat_beranak, ";
			sql += "ob.tarikh_lahir, ob.jantina, ob.id_tarafkptg, ob.umur, tr.keterangan ";
			sql += "FROM Tblppkob ob1,Tblppkobpermohonan ob, "
					+ " Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
			sql += "WHERE ob.id_permohonansimati = '"
					+ id_permohonansimati
					+ "' "
					+ " and ob1.id_ob = ob.id_ob and ob1.id_permohonansimati = sm.id_permohonansimati ";
			sql += "AND ob.id_tarafkptg = tr.id_tarafkptg ";
			sql += " AND sm.id_permohonan = p.id_permohonan ";
			sql += " AND p.id_status <> '169' ";
			sql += " AND ob.id_simati = '" + id_simati + "'";
			// sql += " AND ob.id_permohonansimati <= '"+id_permohonansimati+"'"
			// ;
			if (!Seksyen.equals("8")) {
				sql += " AND NVL(p.no_subjaket,0) <= '"
						+ getNoSubjaketByIdPermohonan(getIdPermohonanByIdPermohonanSimati(id_permohonansimati))
						+ "'";
			}
			sql += " AND NVL(ob.status_ob,0) != 4 ";
			sql += " AND ob.id_tarafkptg != 14 ";
			sql += " AND NVL(ob.status_hidup,0) = 0 ";
			sql += " AND NVL(ob.umur,18)  >= 18 ";
			sql += " ORDER BY ob.nama_ob asc";
			myLogger.info("setListOBatas18"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				if (rs.getString("umur") != null && rs.getString("umur") != "") {

					h.put("age", rs.getInt("umur"));

				} else if (rs.getString("no_kp_baru") != null
						&& rs.getString("no_kp_baru") != "") {

					x = rs.getString("no_kp_baru").substring(0, 2);

					int z = 0;

					if (x != "") {
						z = Integer.parseInt(x);
					}

					icYEAR = rs.getString("no_kp_baru").substring(0, 2);
					icMONTH = rs.getString("no_kp_baru").substring(2, 4);
					icDAY = rs.getString("no_kp_baru").substring(4, 6);

					if (z <= 20) {
						year = "20" + icYEAR;
					} else {
						year = "19" + icYEAR;
					}

					int y = Integer.parseInt(year);
					int m = Integer.parseInt(icMONTH) - 1;
					int d = Integer.parseInt(icDAY);

					Calendar cal = new GregorianCalendar(y, m, d);
					Calendar now = new GregorianCalendar();

					int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
					if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
							|| (cal.get(Calendar.MONTH) == now
									.get(Calendar.MONTH) && cal
									.get(Calendar.DAY_OF_MONTH) > now
									.get(Calendar.DAY_OF_MONTH))) {
						res--;
					}

					int notnull = 0;
					int cantzero = 1;

					if (res < 0) {
						h.put("age", notnull);
					} else if (res == 0) {
						h.put("age", cantzero);
					} else {
						h.put("age", res);
					}

				} else {
					h.put("age", "");
				}

				h.put("bil", bil);
				h.put("no_kp_baru1", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(0, 6));
				h.put("no_kp_baru2", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(6, 8));
				h.put("no_kp_baru3", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(8, 12));
				h.put("id_ob", rs.getString("id_ob") == null ? "" : rs
						.getString("id_ob"));
				h.put("id_simati", rs.getString("id_simati") == null ? "" : rs
						.getString("id_simati"));
				h.put("nama_ob", rs.getString("nama_ob") == null ? "" : rs
						.getString("nama_ob"));
				h.put("no_kp_baru", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru"));
				h.put("no_kp_lama", rs.getString("no_kp_lama") == null ? ""
						: rs.getString("no_kp_lama"));
				h.put("jenis_kp", rs.getString("jenis_kp") == null ? "" : rs
						.getString("jenis_kp"));
				h.put("no_kp_lain", rs.getString("no_kp_lain") == null ? ""
						: rs.getString("no_kp_lain"));
				h.put("no_surat_beranak",
						rs.getString("no_surat_beranak") == null ? "" : rs
								.getString("no_surat_beranak"));
				h.put("tarikh_lahir", rs.getString("tarikh_lahir") == null ? ""
						: rs.getString("tarikh_lahir"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				// h.put("id_tarafkptg", rs.getString("id_tarafkptg"));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));

				listOBatas18.addElement(h);
				bil++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (db != null)
				db.close();
		}
	}// close OB

	// LIST OB
	public static void setListOBatas18Semua(String id_permohonansimati,
			String id_simati, String Seksyen) throws Exception {

		Db db = null;
		listOBatas18Semua.clear();
		String sql = "";
		String icYEAR = "";
		String icMONTH = "";
		String icDAY = "";
		String MONTH = "";
		String year = "";
		String x = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT ob.id_ob, ob.id_simati, ob.nama_ob, ob.no_kp_baru, ob.no_kp_lama, ";
			sql += "ob.status_hidup, ob.jenis_kp, ob.no_kp_lain, ob.no_surat_beranak, ";
			sql += "ob.tarikh_lahir, ob.jantina, ob.id_tarafkptg, ob.umur, tr.keterangan ";
			sql += "FROM Tblppkob ob1,Tblppkobpermohonan ob, Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
			sql += "WHERE ob1.id_ob = ob.id_ob "
					+ " and ob.id_permohonansimati = '" + id_permohonansimati
					+ "' "
					+ " and ob1.id_permohonansimati = sm.id_permohonansimati ";
			sql += "AND ob.id_tarafkptg = tr.id_tarafkptg ";
			sql += " AND sm.id_permohonan = p.id_permohonan ";
			sql += " AND p.id_status <> '169' ";
			sql += " AND ob.id_simati = '" + id_simati + "'";
			// sql += " AND ob.id_permohonansimati <= '"+id_permohonansimati+"'"
			// ;
			if (!Seksyen.equals("8")) {
				sql += " AND NVL(p.no_subjaket,0) <= '"
						+ getNoSubjaketByIdPermohonan(getIdPermohonanByIdPermohonanSimati(id_permohonansimati))
						+ "'";
			}
			// sql += " AND ob.id_tarafkptg != 14 ";
			sql += " AND NVL(ob.status_hidup,0) = 0 ";
			sql += " AND NVL(ob.status_ob,0) != 4 ";
			sql += " AND NVL(ob.umur,18)  >= 18 ";
			sql += " ORDER BY ob.nama_ob asc";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				if (rs.getString("umur") != null && rs.getString("umur") != "") {

					h.put("age", rs.getInt("umur"));

				} else if (rs.getString("no_kp_baru") != null
						&& rs.getString("no_kp_baru") != "") {

					x = rs.getString("no_kp_baru").substring(0, 2);

					int z = 0;

					if (x != "") {
						z = Integer.parseInt(x);
					}

					icYEAR = rs.getString("no_kp_baru").substring(0, 2);
					icMONTH = rs.getString("no_kp_baru").substring(2, 4);
					icDAY = rs.getString("no_kp_baru").substring(4, 6);

					if (z <= 20) {
						year = "20" + icYEAR;
					} else {
						year = "19" + icYEAR;
					}

					int y = Integer.parseInt(year);
					int m = Integer.parseInt(icMONTH) - 1;
					int d = Integer.parseInt(icDAY);

					Calendar cal = new GregorianCalendar(y, m, d);
					Calendar now = new GregorianCalendar();

					int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
					if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
							|| (cal.get(Calendar.MONTH) == now
									.get(Calendar.MONTH) && cal
									.get(Calendar.DAY_OF_MONTH) > now
									.get(Calendar.DAY_OF_MONTH))) {
						res--;
					}

					int notnull = 0;
					int cantzero = 1;

					if (res < 0) {
						h.put("age", notnull);
					} else if (res == 0) {
						h.put("age", cantzero);
					} else {
						h.put("age", res);
					}

				} else {
					h.put("age", "");
				}

				h.put("bil", bil);
				h.put("no_kp_baru1", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(0, 6));
				h.put("no_kp_baru2", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(6, 8));
				h.put("no_kp_baru3", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(8, 12));
				h.put("id_ob", rs.getString("id_ob") == null ? "" : rs
						.getString("id_ob"));
				h.put("id_simati", rs.getString("id_simati") == null ? "" : rs
						.getString("id_simati"));
				h.put("nama_ob", rs.getString("nama_ob") == null ? "" : rs
						.getString("nama_ob"));
				h.put("no_kp_baru", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru"));
				h.put("no_kp_lama", rs.getString("no_kp_lama") == null ? ""
						: rs.getString("no_kp_lama"));
				h.put("jenis_kp", rs.getString("jenis_kp") == null ? "" : rs
						.getString("jenis_kp"));
				h.put("no_kp_lain", rs.getString("no_kp_lain") == null ? ""
						: rs.getString("no_kp_lain"));
				h.put("no_surat_beranak",
						rs.getString("no_surat_beranak") == null ? "" : rs
								.getString("no_surat_beranak"));
				h.put("tarikh_lahir", rs.getString("tarikh_lahir") == null ? ""
						: rs.getString("tarikh_lahir"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));

				listOBatas18Semua.addElement(h);
				bil++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (db != null)
				db.close();
		}
	}// close OB semua atas 18

	// List penghantar notis :: SEKSYEN 8 & 17 ::
	public static void setPenghantarNotisByJkptg(String id_pejabatjkptg)
		throws Exception {
		Db db = null;
		penghantarNotisByJkptg.clear();
//		2017/12/21 Kemaskini query bagi paparan Penghantar Notis mengikut negeri
//		String sql = "SELECT DISTINCT id_penghantarnotis,kod_penghantar_notis,nama "
//				+ " FROM TBLPPKRUJPENGHANTARNOTIS "
//				+ " WHERE id_pejabatjkptg IN ( '"+ id_pejabatjkptg+ "', 99999)"
//				+ " Order by kod_penghantar_notis ";
		String sql = "SELECT DISTINCT id_penghantarnotis,kod_penghantar_notis,nama " 
				+ " FROM TBLPPKRUJPENGHANTARNOTIS "
				+ " WHERE id_pejabatjkptg IN (  "
				+ " SELECT ID_PEJABATJKPTG FROM TBLRUJPEJABATJKPTG  "
				+ " WHERE ID_SEKSYEN=2 "
				+ " AND ID_JENISPEJABAT=22 "
				+ " AND ID_NEGERI IN ( "
				+ "     SELECT ID_NEGERI FROM TBLRUJPEJABATJKPTG WHERE ID_PEJABATJKPTG='"+ id_pejabatjkptg+ "' "
		        + " ) "
		        + " ) OR id_pejabatjkptg IN 99999 "
		        + " ORDER BY KOD_PENGHANTAR_NOTIS ";
		myLogger.info("penghantar notis :: sql="+sql);
		try {
			db = new Db();
			Statement stmt = db.getStatement();			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("id_penghantarnotis"
						,rs.getString("id_penghantarnotis") == null ? "" : rs.getString("id_penghantarnotis"));
				h.put("kod_penghantar_notis"
						, rs.getString("kod_penghantar_notis") == null ? "" : rs.getString("kod_penghantar_notis"));
				h.put("nama", rs.getString("nama") == null ? "" : rs.getString("nama"));
				penghantarNotisByJkptg.addElement(h);

			}
		} finally {
			if (db != null)
				db.close();
		}
		
	}

	public static void setPenghantarNotis() throws Exception {

		Db db = null;
		penghantarNotis.clear();

		String sql = "SELECT DISTINCT id_penghantarnotis,kod_penghantar_notis,nama "
				+ " FROM TBLPPKRUJPENGHANTARNOTIS Order by kod_penghantar_notis ";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {

				h = new Hashtable();
				h.put("id_penghantarnotis",
						rs.getString("id_penghantarnotis") == null ? "" : rs
								.getString("id_penghantarnotis"));
				h.put("kod_penghantar_notis", rs
						.getString("kod_penghantar_notis") == null ? "" : rs
						.getString("kod_penghantar_notis"));
				h.put("nama", rs.getString("nama") == null ? "" : rs
						.getString("nama"));
				penghantarNotis.addElement(h);

			}
		} finally {

			if (db != null)
				db.close();
		}
	}

	// getDetailPenghantarNotis
	public static Vector getDetailPenghantarNotis(String id_penghantarnotis)
			throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();

			sql = "SELECT id_penghantarnotis, kod_penghantar_notis, nama ";
			sql += " FROM TBLPPKRUJPENGHANTARNOTIS ";
			sql += " WHERE id_penghantarnotis = '" + id_penghantarnotis + "'";
			sql += " Order by kod_penghantar_notis ";

			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();

			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_penghantarnotis",
						rs.getString("id_penghantarnotis") == null ? "" : rs
								.getString("id_penghantarnotis"));
				h.put("kod_penghantar_notis", rs
						.getString("kod_penghantar_notis") == null ? "" : rs
						.getString("kod_penghantar_notis"));
				h.put("nama", rs.getString("nama") == null ? "" : rs
						.getString("nama"));
				list.addElement(h);

			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}// getDetailPenghantarNotis

	public static Vector getSelectedPenghantarNotisByJkptg(String id_jkptg,
			String id_penghantarnotis) throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			
			

			sql = "SELECT id_penghantarnotis, kod_penghantar_notis, nama ";
			sql += " FROM TBLPPKRUJPENGHANTARNOTIS ";
			sql += " WHERE id_penghantarnotis <> '" + id_penghantarnotis + "'";
			//sql += " AND id_pejabatjkptg = '" + id_jkptg + "'";
			sql += " AND id_pejabatjkptg IN ("+id_jkptg+",99999)  " ;
			sql += " Order by kod_penghantar_notis ";

			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();

			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_penghantarnotis",
						rs.getString("id_penghantarnotis") == null ? "" : rs
								.getString("id_penghantarnotis"));
				h.put("kod_penghantar_notis", rs
						.getString("kod_penghantar_notis") == null ? "" : rs
						.getString("kod_penghantar_notis"));
				h.put("nama", rs.getString("nama") == null ? "" : rs
						.getString("nama"));
				list.addElement(h);

			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getSelectedPenghantarNotis(String id_penghantarnotis)
			throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();

			sql = "SELECT id_penghantarnotis, kod_penghantar_notis, nama ";
			sql += " FROM TBLPPKRUJPENGHANTARNOTIS ";
			sql += " WHERE id_penghantarnotis <> '" + id_penghantarnotis + "'";
			sql += " Order by kod_penghantar_notis ";

			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();

			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_penghantarnotis",
						rs.getString("id_penghantarnotis") == null ? "" : rs
								.getString("id_penghantarnotis"));
				h.put("kod_penghantar_notis", rs
						.getString("kod_penghantar_notis") == null ? "" : rs
						.getString("kod_penghantar_notis"));
				h.put("nama", rs.getString("nama") == null ? "" : rs
						.getString("nama"));
				list.addElement(h);

			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// simpan Semakan K2
	public static void addListOb(Hashtable data, String id_ob) throws Exception {

		Db db = null;

		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			long id_notisob = DB.getNextID("TBLPPKNOTISOB_SEQ");

			// id login
			String id_masuk = (String) data.get("id_masuk");
			if (id_masuk == null){
				id_masuk = (String) data.get("id_kemaskini");
			}
			String id_perbicaraan = (String) data.get("id_perbicaraan");
			if (id_perbicaraan == null) {
				id_perbicaraan = idPerbicaraan;
			}

			// flag pilihan ob sek17
			String flag_cetak = "0";
			
			// insert data into Tblppknotisob	
			Statement stmtS = db.getStatement();
			sql = "INSERT INTO Tblppknotisob "
					+ "(id_notisob, id_perbicaraan, id_ob, id_masuk, tarikh_masuk, flag_cetak)  "
					+ "VALUES ('" + id_notisob + "', '" + id_perbicaraan
					+ "', '" + id_ob + "', '" + id_masuk + "', sysdate, '"
					+ flag_cetak + "') ";
			stmtS.executeUpdate(sql);

		}// close try
		finally {
			if (db != null)
				db.close();
		}// close finally

	}// close simpanSemakanK2

	// LIST OB cbsemak
	public static void setListOBsemak(String id_permohonansimati,
			String id_perbicaraan, String id_simati) throws Exception {

		Db db = null;
		listOBsemak.clear();
		String sql = "";
		String icYEAR = "";
		String icMONTH = "";
		String icDAY = "";
		String MONTH = "";
		String year = "";
		String x = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT ob.id_ob, ob.id_simati, ob.nama_ob, ob.no_kp_baru, ob.no_kp_lama, ";
			sql += " ob.status_hidup, ob.jenis_kp, ob.no_kp_lain, ob.no_surat_beranak, ";
			sql += " ob.tarikh_lahir, ob.jantina, ob.id_tarafkptg, ob.umur, tr.keterangan, ob.status_ob,";
			sql += " (select count(*) from tblppknotisob where id_ob=ob.id_ob and NVL(flag_cetak,0) = '0' and id_perbicaraan = '"
					+ id_perbicaraan + "')as flag ";
			sql += " FROM Tblppkob ob1,Tblppkobpermohonan ob, "
					+ " Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
			sql += " WHERE ob.id_ob = ob1.id_ob and ob.id_permohonansimati = '"
					+ id_permohonansimati + "' "
					+ " and ob1.id_permohonansimati = sm.id_permohonansimati ";
			sql += " AND sm.id_permohonan = p.id_permohonan ";
			sql += " AND p.id_status <> '169' ";
			sql += " AND ob.id_tarafkptg = tr.id_tarafkptg ";
			sql += " AND NVL(ob.status_hidup,0) = 0 ";
			// sql +=
			// " AND ob.id_permohonansimati <= '"+id_permohonansimati+"'";
			sql += " AND ob.id_simati = '" + id_simati + "'";
			sql += " AND NVL(p.no_subjaket,0) <= '"
					+ getNoSubjaketByIdPermohonan(getIdPermohonanByIdPermohonanSimati(id_permohonansimati))
					+ "'";
			sql += " ORDER BY ob.umur desc,ob.nama_ob asc ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				if (rs.getString("umur") != null && rs.getString("umur") != "") {

					h.put("age", rs.getInt("umur"));
				} else if (rs.getString("no_kp_baru") != null
						&& rs.getString("no_kp_baru") != "") {

					x = rs.getString("no_kp_baru").substring(0, 2);

					int z = 0;

					if (x != "") {
						z = Integer.parseInt(x);
					}

					icYEAR = rs.getString("no_kp_baru").substring(0, 2);
					icMONTH = rs.getString("no_kp_baru").substring(2, 4);
					icDAY = rs.getString("no_kp_baru").substring(4, 6);

					if (z <= 20) {
						year = "20" + icYEAR;
					} else {
						year = "19" + icYEAR;
					}

					int y = Integer.parseInt(year);
					int m = Integer.parseInt(icMONTH) - 1;
					int d = Integer.parseInt(icDAY);

					Calendar cal = new GregorianCalendar(y, m, d);
					Calendar now = new GregorianCalendar();

					int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
					if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
							|| (cal.get(Calendar.MONTH) == now
									.get(Calendar.MONTH) && cal
									.get(Calendar.DAY_OF_MONTH) > now
									.get(Calendar.DAY_OF_MONTH))) {
						res--;
					}

					int notnull = 0;
					int cantzero = 1;

					if (res < 0) {
						h.put("age", notnull);
					} else if (res == 0) {
						h.put("age", cantzero);
					} else {
						h.put("age", res);
					}

				} else {
					h.put("age", "");
				}

				h.put("bil", bil);
				h.put("no_kp_baru1", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(0, 6));
				h.put("no_kp_baru2", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(6, 8));
				h.put("no_kp_baru3", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru").substring(8, 12));
				h.put("id_simati", rs.getString("id_simati") == null ? "" : rs
						.getString("id_simati"));
				h.put("no_kp_baru", rs.getString("no_kp_baru") == null ? ""
						: rs.getString("no_kp_baru"));
				h.put("no_kp_lama", rs.getString("no_kp_lama") == null ? ""
						: rs.getString("no_kp_lama"));
				h.put("jenis_kp", rs.getString("jenis_kp") == null ? "" : rs
						.getString("jenis_kp"));
				h.put("no_kp_lain", rs.getString("no_kp_lain") == null ? ""
						: rs.getString("no_kp_lain"));
				h.put("no_surat_beranak",
						rs.getString("no_surat_beranak") == null ? "" : rs
								.getString("no_surat_beranak"));
				h.put("tarikh_lahir", rs.getString("tarikh_lahir") == null ? ""
						: rs.getString("tarikh_lahir"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_ob", rs.getString("id_ob") == null ? "" : rs
						.getString("id_ob"));
				h.put("nama_ob", rs.getString("nama_ob") == null ? "" : rs
						.getString("nama_ob"));
				h.put("flag", rs.getString("flag") == null ? "" : rs
						.getString("flag"));
				h.put("status_ob", rs.getString("status_ob") == null ? "" : rs
						.getString("status_ob"));

				if (rs.getString("status_ob") != null
						&& rs.getString("status_ob") != "") {

					String statusOB = rs.getString("status_ob");
					if (statusOB.equals("1")) {
						h.put("keteranganOB", "DEWASA/WARAS");
					} else if (statusOB.equals("2")) {
						h.put("keteranganOB", "BELUM DEWASA");
					} else if (statusOB.equals("3")) {
						h.put("keteranganOB", "HILANG");
					} else if (statusOB.equals("4")) {
						h.put("keteranganOB", "TIDAK SEMPURNA AKAL");
					} else {
						h.put("keteranganOB", "DEWASA/WARAS");
					}
				} else {
					h.put("keteranganOB", "DEWASA/WARAS");
				}

				listOBsemak.addElement(h);
				bil++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (db != null)
				db.close();
		}
	}// close OB cbsemak

	// delete checked ob
	public static void obSemakDelete(String id_perbicaraan) throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "DELETE FROM TBLPPKNOTISOB WHERE id_perbicaraan = '"
					+ id_perbicaraan + "'";
			sql += " AND NVL(flag_cetak,0) = '0' ";
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}// delete checked ob

	// -- 08122009
	// check button cetak akuan sumpah :: SEKSYEN 8 & SEKSYEN 17 ::
	public static void setCheckCetakAkuanSumpah(String id_perbicaraan,
			String id_permohonansimati) throws Exception {

		Db db = null;
		checkCetakAkuanSumpah.clear();
		String sql = "";
		String idOBdtl = "";

		try {

			db = new Db();

			Statement stmtN = db.getStatement();

			sql = " SELECT ob.id_ob, n.id_notisobdtl ";
			sql += " FROM Tblppknotisobdtl n, "
					+ " Tblppkob ob1,Tblppkobpermohonan ob,"
					+ " Tblppknotisobmst mst, Tblppknotisperbicaraan npb, Tblppkperbicaraan pb ";
			sql += " WHERE ob1.id_ob = ob.id_ob and ob.id_permohonansimati = '"
					+ id_permohonansimati + "' " + " and n.id_ob = ob1.id_ob ";
			sql += " AND n.id_notisobmst = mst.id_notisobmst ";
			sql += " AND npb.id_notisobmst = mst.id_notisobmst ";
			sql += " AND npb.id_perbicaraan = pb.id_perbicaraan ";
			sql += " AND NVL(npb.flag_jenis_notis,0) = '0' ";
			sql += " AND pb.id_perbicaraan = '" + id_perbicaraan + "'";

			ResultSet rx = stmtN.executeQuery(sql);
			int i = 0;

			while (rx.next()) {
				if (i == 0) {
					idOBdtl += "IN(" + rx.getString("id_notisobdtl");
				} else {
					idOBdtl += "," + rx.getString("id_notisobdtl");
				}
				i++;

			}// close while

			if (idOBdtl != "") {
				idOBdtl += ")";
			}

			// get selected
			sql = "SELECT DISTINCT a.id_notisperbicaraan, b.id_perbicaraan, c.id_notisobmst, d.id_notisobdtl ";
			sql += " FROM tblppknotisperbicaraan a,tblppkperbicaraan b,tblppknotisobmst c,tblppknotisobdtl d ";
			sql += " WHERE a.id_perbicaraan = b.id_perbicaraan ";
			sql += " AND a.id_notisobmst = c.id_notisobmst ";
			sql += " AND d.id_notisobmst = c.id_notisobmst ";
			sql += " AND NVL(a.flag_jenis_notis,0) = 0 ";
			sql += " AND c.status_serah = 2 ";

			if (idOBdtl != "") {
				sql += " AND d.id_notisobdtl " + idOBdtl;
			} else {
				sql += " AND d.id_notisobdtl = ''";
			}

			ResultSet rs = stmtN.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_notisobdtl",
						rs.getString("id_notisobdtl") == null ? "" : rs
								.getString("id_notisobdtl"));
				checkCetakAkuanSumpah.addElement(h);
			}// close while

		} finally {
			if (db != null)
				db.close();
		}
	}

	// -- 08122009

	// LIST PENERIMA NOTIS selected
	public static void setSelectedOB17(String id_perbicaraan,
			String id_permohonansimati, String id_simati) throws Exception {

		Db db = null;
		selectedOB17.clear();
		String sql = "";
		String idOB = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			SQLRenderer r = new SQLRenderer();

			sql = "SELECT n.id_notisobdtl, n.id_notisobmst, pb.id_perbicaraan, ";
			sql += "npb.id_notisperbicaraan, n.nama_penerima, ob.nama_ob, ob.id_ob, ob1.EMEL ";
			sql += "FROM Tblppknotisobdtl n, "
					+ "Tblppkob ob1,Tblppkobpermohonan ob,"
					+ " Tblppknotisobmst mst, ";
			sql += "Tblppknotisperbicaraan npb, Tblppkperbicaraan pb ";
			sql += "WHERE ob.id_ob = ob1.id_ob and ob.id_permohonansimati = '"
					+ id_permohonansimati
					+ "' "
					+ " and n.id_ob = ob1.id_ob  AND n.id_notisobmst = mst.id_notisobmst ";
			sql += "AND npb.id_notisobmst = mst.id_notisobmst  AND npb.id_perbicaraan = pb.id_perbicaraan ";
			sql += "AND pb.id_perbicaraan = '" + id_perbicaraan + "'";
			sql += "AND NVL(ob.status_hidup,0) = 0 ";
			sql += "ORDER BY ob.nama_ob DESC ";

			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()) {

				if (i == 0) {
					idOB += "ALL(" + rs.getString("id_ob");
				} else {
					idOB += "," + rs.getString("id_ob");
				}
				i++;

			}// close while

			if (idOB != "") {
				idOB += ")";
			}

			// get selected
			SQLRenderer rN = new SQLRenderer();
			sql = " SELECT distinct ob.nama_ob,ob.id_ob,ob.no_kp_baru,ob.umur,ob.no_kp_lama,ob.no_kp_lain, ob1.emel ";
			sql += " FROM Tblppkob ob1,Tblppkobpermohonan ob,Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p, Tblppknotisob nob ";
			sql += " WHERE ob1.id_ob = ob.id_ob "
					+ " and ob.id_permohonansimati = '" + id_permohonansimati
					+ "' "
					+ " and ob1.id_permohonansimati = sm.id_permohonansimati ";
			sql += " AND sm.id_permohonan = p.id_permohonan ";
			sql += " AND nob.id_ob = ob.id_ob ";
			sql += " AND p.id_status <> '169' ";
			sql += " AND ob.id_simati = '" + id_simati + "'";
			// sql +=" AND ob.id_permohonansimati <= '"+id_permohonansimati+"'"
			// ;
			sql += " AND NVL(p.no_subjaket,0) <= '"
					+ getNoSubjaketByIdPermohonan(getIdPermohonanByIdPermohonanSimati(id_permohonansimati))
					+ "'";
			sql += " AND ob.id_tarafkptg = tr.id_tarafkptg ";
			// sql +=" AND ob.id_tarafkptg != 14 ";
			sql += " AND NVL(ob.status_hidup,0) = 0 ";
			sql += " AND NVL(ob.status_ob,0) != 4 ";
			sql += " AND NVL(ob.umur,18)  >= 18 ";
			sql += " AND nob.id_perbicaraan = '" + id_perbicaraan + "' ";
			if (idOB != "") {
				sql += " AND ob.id_ob != " + idOB;
			}
			sql += " ORDER BY ob.nama_ob asc ";

			ResultSet rx = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rx.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("no_kp_baru1", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru").substring(0, 6));
				h.put("no_kp_baru2", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru").substring(6, 8));
				h.put("no_kp_baru3", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru").substring(8, 12));
				h.put("no_kp_lama", rx.getString("no_kp_lama") == null ? ""
						: rx.getString("no_kp_lama"));
				h.put("id_ob", rx.getString("id_ob") == null ? "" : rx
						.getString("id_ob"));
				h.put("no_kp_baru", rx.getString("no_kp_baru") == null ? ""
						: rx.getString("no_kp_baru"));
				h.put("nama_ob", rx.getString("nama_ob") == null ? "" : rx
						.getString("nama_ob"));
				h.put("no_kp_lain", rx.getString("no_kp_lain") == null ? ""
						: rx.getString("no_kp_lain"));
				h.put("emel", rx.getString("emel") == null ? ""
						: rx.getString("emel"));

				selectedOB17.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}

	}// close selected ob utk 17

	// Get idkeputusanpermohonan :: SEKSYEN 8 & SEKSYEN 17 ::
	public static Vector getIdpemohonSek8(String id_simati) throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();

			sql = "select id_pemohon from tblppkpermohonan a, tblppkpermohonansimati b ";
			sql += " where a.id_permohonan = b.id_permohonan ";
			sql += " and b.id_simati = '" + id_simati + "'";
			sql += " and a.seksyen = '8' ";

			ResultSet rs = stmt.executeQuery(sql);

			Vector list = new Vector();

			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_pemohon", rs.getString("id_pemohon") == null ? ""
						: rs.getString("id_pemohon"));
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}// Get idkeputusanpermohonan

	// Get IdSubUrusanStatusFail
	public static Vector getIdSubUrusanStatusFail(String id_permohonan)
			throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT max(id_suburusanstatusfail)as id_suburusanstatusfail";
			sql += " FROM Tblrujsuburusanstatusfail ";
			sql += " WHERE id_permohonan = '" + id_permohonan + "'";
			sql += " AND aktif = '1'";

			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_suburusanstatusfail", rs
						.getString("id_suburusanstatusfail") == null ? "" : rs
						.getString("id_suburusanstatusfail"));
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}// Get IdSubUrusanStatusFail

	/*
	 * * * [SEKSYEN 17] * * *
	 */

	// List permohonan Seksyen 17
	public static void setListDefaultSek17(String usid) throws Exception {

		Db db = null;
		listDefaultSek17.clear();
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();

			// SYARAT
			sql = "SELECT MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, "
					+ " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
					+ " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI "
					+ " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
					+ " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ usid
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
					
					sql += " ) "
					+ " AND P.ID_STATUS = S.ID_STATUS "
					+ " AND P.ID_FAIL = F.ID_FAIL(+) "
					+ " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) "
					+ " AND P.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN "
					+ " AND M.ID_SIMATI = MS.ID_SIMATI "
					+ " AND P.ID_PEMOHON = PP.ID_PEMOHON "
					+ " AND (P.ID_STATUS=151 OR P.ID_STATUS=53 OR P.ID_STATUS=18 OR P.ID_STATUS=44 OR P.ID_STATUS=173 OR P.ID_STATUS=175 OR P.ID_STATUS=177 OR P.ID_STATUS=166) "
					+ " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
					+ " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
					+ " AND STA.AKTIF = '1' "
					+ " AND P.ID_STATUS <> '999' "
					+ " AND P.SEKSYEN = '17' "
					+ " AND P.FLAG_JENIS_PERMOHONAN = '1' "
					// +
					// " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)"
					// +
					// " AND F.NO_FAIL IS NOT NULL ORDER BY P.TARIKH_KEMASKINI desc, STA.ID_SUBURUSANSTATUSFAIL DESC ";
					+ " AND F.NO_FAIL IS NOT NULL ORDER BY STA.ID_SUBURUSANSTATUSFAIL desc, p.tarikh_kemaskini desc  ";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("id_Permohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("id_status", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS"));
				h.put("id_Fail", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL"));
				h.put("no_Fail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL"));
				h.put("tarikhmohon", rs.getDate("TARIKH_MOHON") == null ? ""
						: Format.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getDate("TARIKH_MASUK") == null ? ""
						: Format.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar",
						rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : Format
								.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));
				h.put("namasimati", rs.getString("NAMA_SIMATI") == null ? ""
						: rs.getString("NAMA_SIMATI"));
				h.put("id_simati", rs.getString("ID_SIMATI") == null ? "" : rs
						.getString("ID_SIMATI"));
				listDefaultSek17.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}

	}// close default

	// List carian Seksyen 17
	public static void setCarianFailSek17(String noFail, String namaPemohon,
			String namaSimati, String icSimati, String JenisIc, String usid)
			throws Exception {

		Db db = null;

		listSek17.clear();
		String sql = "";

		try {

			db = new Db();

			String chkDataFail = noFail.trim();
			String chkDataPemohon = namaPemohon.trim();
			String chkDataSimati = namaSimati.trim();
			String chkDataIcSimati = icSimati.trim();
			String chkDataJenisKp = JenisIc;

			Statement stmt = db.getStatement();

			// SYARAT
			sql = "SELECT PP.NAMA_PEMOHON, MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, "
					+ " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
					+ " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI "
					+ " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
					+ " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ usid
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
					
					sql += " ) "
					+ " AND P.ID_STATUS = S.ID_STATUS "
					+ " AND P.ID_FAIL = F.ID_FAIL(+) "
					+ " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) "
					+ " AND P.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN "
					+ " AND M.ID_SIMATI = MS.ID_SIMATI "
					+ " AND P.ID_PEMOHON = PP.ID_PEMOHON "
					+ " AND (P.ID_STATUS=151 OR P.ID_STATUS=53 OR P.ID_STATUS=18 OR P.ID_STATUS=44 OR P.ID_STATUS=173 OR P.ID_STATUS=175 OR P.ID_STATUS=177 OR P.ID_STATUS=166) "
					+ " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
					+ " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
					+ " AND STA.AKTIF = '1' "
					+ " AND P.ID_STATUS <> '999' "
					+ " AND P.SEKSYEN = '17' "
					+ " AND P.FLAG_JENIS_PERMOHONAN = '1' ";
			// + " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)";

			// NO FAIL
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%"
							+ chkDataFail.toUpperCase() + "%'";
				}
			}// close if nofail

			// NAMA PEMOHON
			if (namaPemohon != "") {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(PP.NAMA_PEMOHON) LIKE '%"
							+ chkDataPemohon.toUpperCase() + "%'";
				}
			}// close if pemohon

			// NAMA SIMATI
			if (namaSimati != "") {
				if (!namaSimati.trim().equals("")) {
					sql = sql + " AND UPPER(M.NAMA_SIMATI) LIKE '%"
							+ chkDataSimati.toUpperCase() + "%'";
				}
			}// close if nama simati

			// IC SIMATI
			if (icSimati != "") {
				if (!icSimati.trim().equals("")) {
					if (chkDataJenisKp.equals("1")) {
						sql = sql + " AND UPPER(M.NO_KP_BARU) LIKE '%"
								+ chkDataIcSimati.toUpperCase() + "%'";
					} else if (chkDataJenisKp.equals("2")) {
						sql = sql + " AND UPPER(M.NO_KP_LAMA) LIKE '%"
								+ chkDataIcSimati.toUpperCase() + "%'";
					} else if (chkDataJenisKp.equals("3")) {
						sql = sql + " AND UPPER(M.NO_KP_LAIN) LIKE '%"
								+ chkDataIcSimati.toUpperCase() + "%'";
					}
				}
			}// close if ic simati

			// sorting
			// sql +=
			// " AND F.NO_FAIL IS NOT NULL ORDER BY P.TARIKH_KEMASKINI desc, STA.ID_SUBURUSANSTATUSFAIL DESC ";
			sql += " AND F.NO_FAIL IS NOT NULL ORDER BY STA.ID_SUBURUSANSTATUSFAIL desc, p.tarikh_kemaskini desc  ";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("id_Permohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("id_status", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS"));
				h.put("id_Fail", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL"));
				h.put("no_Fail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL"));
				h.put("tarikhmohon", rs.getString("TARIKH_MOHON") == null ? ""
						: Format.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK") == null ? ""
						: Format.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar",
						rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : Format
								.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));
				h.put("namasimati", rs.getString("NAMA_SIMATI") == null ? ""
						: rs.getString("NAMA_SIMATI"));
				h.put("id_simati", rs.getString("ID_SIMATI") == null ? "" : rs
						.getString("ID_SIMATI"));
				listSek17.addElement(h);
				bil++;

			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}

	}// close carian

	// Simpan notis SEKSYEN 17
	public static String addNotisSek17(HttpSession session, Hashtable data)
			throws Exception {

		Connection conn = null;
		Db db = null;
		String sql = "";
		String sql5 = "";
		String sql6 = "";
		
		long id_perbicaraan = DB.getNextID("TBLPPKPERBICARAAN_SEQ");
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// generate other table id
			long id_notisobmst = DB.getNextID("TBLPPKNOTISOBMST_SEQ");
			// long id_perbicaraan = DB.getNextID("TBLPPKPERBICARAAN_SEQ");
			idPerbicaraan = String.valueOf(id_perbicaraan);

			String id_permohonan = (String) data.get("id_permohonan");
			String id_fail = (String) data.get("id_fail");
			String id_suburusanstatusfail = (String) data
					.get("id_suburusanstatusfail");
			String id_keputusanpermohonan = (String) data
					.get("id_keputusanpermohonan");
			String id_ppkrujunit = (String) data.get("pegawai");
			String id_pejabatjkptg = (String) data.get("tempat_bicara");

			String jenispejabat = (String) data.get("jenispejabat");

			String negeri = (String) data.get("negeri");
			String id_masuk = (String) data.get("id_masuk");

			String tarikh_bicara = (String) data.get("tarikh_bicara");
			String masa_bicara = (String) data.get("masa_bicara");
			String alamat1 = (String) data.get("alamat_bicara1");
			String alamat2 = (String) data.get("alamat_bicara2");
			String alamat3 = (String) data.get("alamat_bicara3");
			String poskod = (String) data.get("poskod");
			String tarikh_notis = (String) data.get("tarikh_notis");

			// -- 09122009
			String socJenisWaktu = (String) data.get("socJenisWaktu");

			String nama_pegawai = "";
			String nama_pejabat = "";
			String id_pejabat = "";
			String tablePejabat = "";

			String firstNotis = "1";

			String TB = "to_date('" + tarikh_bicara + "','dd/MM/yyyy')";
			String TN = "to_date('" + tarikh_notis + "','dd/MM/yyyy')";

			// get nama pegawai
			SQLRenderer rMT = new SQLRenderer();
			rMT.add("id_unitpsk");
			rMT.add("nama_pegawai");
			rMT.add("id_unitpsk", id_ppkrujunit);
			sql = rMT.getSQLSelect("Tblppkrujunit");

			ResultSet rsMT = stmt.executeQuery(sql);
			while (rsMT.next()) {
				nama_pegawai = rsMT.getString("nama_pegawai");
			}

			String id_bandar = "";

			if (!jenispejabat.equals("0")) {

				// get pejabat
				if (jenispejabat.equals("22")) {
					id_pejabat = "id_pejabatjkptg";
					tablePejabat = "Tblrujpejabatjkptg";
				} else if (jenispejabat.equals("2")) {
					id_pejabat = "id_pejabat";
					tablePejabat = "Tblrujpejabat";
				} else {
					id_pejabat = "id_pejabatjkptg";
					tablePejabat = "Tblrujpejabatjkptg";
				}

				SQLRenderer rMP = new SQLRenderer();
				rMP.add("nama_pejabat");
				rMP.add("id_bandar");
				rMP.add(id_pejabat, id_pejabatjkptg);
				sql = rMP.getSQLSelect(tablePejabat);

				ResultSet rsP = stmt.executeQuery(sql);
				while (rsP.next()) {
					nama_pejabat = rsP.getString("nama_pejabat");
					id_bandar = rsP.getString("id_bandar");
				}
			} else {
				nama_pejabat = id_pejabatjkptg;
			}

			String keterangan = "";

			if (id_bandar != "") {
				SQLRenderer rb = new SQLRenderer();
				rb.add("keterangan");
				rb.add("id_bandar", id_bandar);
				sql = rb.getSQLSelect("Tblrujbandar");

				ResultSet rsPb = stmt.executeQuery(sql);
				while (rsPb.next()) {
					keterangan = rsPb.getString("keterangan");
				}
			}

			// add data (notis perbicaraan) with bil = 1
			SQLRenderer r = new SQLRenderer();
			r.add("id_perbicaraan", id_perbicaraan);
			r.add("id_keputusanpermohonan", id_keputusanpermohonan);
			r.add("id_unitpsk", id_ppkrujunit);
			r.add("bil_bicara", firstNotis);
			r.add("masa_bicara", masa_bicara);
			r.add("alamat_bicara1", alamat1);
			r.add("alamat_bicara2", alamat2);
			r.add("alamat_bicara3", alamat3);
			r.add("poskod", poskod);
			r.add("peg_pengendali", nama_pegawai);
			r.add("tempat_bicara", nama_pejabat);
			r.add("tarikh_bicara", r.unquote(TB));
			r.add("tarikh_notis", r.unquote(TN));
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("id_negeribicara", negeri);
			r.add("id_masuk", id_masuk);

			// -- 09122009
			r.add("jenis_masa_bicara", socJenisWaktu);

			if (keterangan != "") {
				r.add("bandar", keterangan);
			} else {
				r.add("bandar", "");
			}

			if (!jenispejabat.equals("0")) {
				r.add("id_pejabat", id_pejabatjkptg);
			} else {
				r.add("id_pejabat", "0");
			}

			r.add("id_jenispejabat", jenispejabat);
			sql = r.getSQLInsert("Tblppkperbicaraan");
			stmt.executeUpdate(sql);

			
			// create table notisobmst
			SQLRenderer rMST = new SQLRenderer();
			rMST.add("id_notisobmst", id_notisobmst);
			rMST.add("id_masuk", id_masuk);
			rMST.add("tarikh_masuk", rMST.unquote("sysdate"));
			sql = rMST.getSQLInsert("Tblppknotisobmst");
			stmt.executeUpdate(sql);

			// create child table
			SQLRenderer r1 = new SQLRenderer();
			r1.add("id_perbicaraan", id_perbicaraan);
			r1.add("id_notisobmst", id_notisobmst);
			r1.add("flag_jenis_notis", 0);
			r1.add("id_masuk", id_masuk);
			r1.add("tarikh_masuk", r1.unquote("sysdate"));
			sql = r1.getSQLInsert("Tblppknotisperbicaraan");
			stmt.executeUpdate(sql);

			// tukar status permohonan diteruskan => notis perbicaraan
			int status_notisperbicaraan = 18;

			/*
			 * SQLRenderer rP = new SQLRenderer();
			 * rP.update("id_permohonan",id_permohonan); rP.add("id_status",
			 * status_notisperbicaraan); rP.add("id_kemaskini",id_masuk);
			 * rP.add("tarikh_kemaskini", rP.unquote("sysdate")); sql =
			 * rP.getSQLUpdate("Tblppkpermohonan"); stmt.executeUpdate(sql);
			 */

			// update n add tblrujsuburusanstatus
			/*
			 * SQLRenderer r6 = new SQLRenderer();
			 * r6.update("id_Suburusanstatusfail",id_suburusanstatusfail);
			 * r6.add("AKTIF",0); r6.add("ID_KEMASKINI",id_masuk);
			 * r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate")); sql6 =
			 * r6.getSQLUpdate("tblrujsuburusanstatusfail");
			 * stmt.executeUpdate(sql6);
			 */

			// 341 = notis perbicaraan
			/*
			 * SQLRenderer r5 = new SQLRenderer();
			 * r5.add("ID_SUBURUSANSTATUSFAIL"
			 * ,DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			 * r5.add("ID_PERMOHONAN",id_permohonan); r5.add("ID_FAIL",id_fail);
			 * r5.add("ID_SUBURUSANSTATUS",341); r5.add("AKTIF",1);
			 * r5.add("ID_MASUK",id_masuk); r5.add("id_kemaskini",id_masuk);
			 * r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
			 * r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate")); sql5 =
			 * r5.getSQLInsert("tblrujsuburusanstatusfail");
			 * stmt.executeUpdate(sql5);
			 */

			conn.commit();
			
			Statement stmtfail = db.getStatement();
			SQLRenderer rfail = new SQLRenderer();
			
			String sql_fail = "SELECT F.NO_FAIL, S.NAMA_SIMATI  FROM  TBLPPKPERBICARAAN B, " +
					" TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERMOHONAN P,TBLPFDFAIL F , TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI M " +
					" WHERE B.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN " +
					" AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN " +
					" AND P.ID_FAIL = F.ID_FAIL " +
					" AND S.ID_SIMATI = M.ID_SIMATI " +
					" AND M.ID_PERMOHONAN = KP.ID_PERMOHONAN " +
					" AND B.ID_PERBICARAAN = '"+ id_perbicaraan + "'";
			ResultSet rsfail = stmtfail.executeQuery(sql_fail);
			String no_fail = "";
			String nama_simati= "";
			while (rsfail.next()) {
				
				if (rsfail.getString("NO_FAIL") != null) {
					no_fail = rsfail.getString("NO_FAIL");
				}
				
				if (rsfail.getString("NAMA_SIMATI") != null) {
					nama_simati = rsfail.getString("NAMA_SIMATI");
				}
			}
			
			/** START ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE **/			
			String event_id = "PERBICARAAN PUSAKA KECIL BAGI FAIL " + no_fail;
			String event_text = "PERBICARAAN PUSAKA KECIL BAGI FAIL " + no_fail;
			String event_location = "";
			if (!"".equals(nama_pejabat))
				event_location = event_location + nama_pejabat;
			if (!"".equals(alamat1))
				event_location = event_location + ", " + alamat1;
			if (!"".equals(alamat2))
				event_location = event_location + ", " + alamat2;
			if (!"".equals(alamat3))
				event_location = event_location + ", " + alamat3;
			if (!"".equals(poskod))
				event_location = event_location + ", " + poskod;
			if (!"".equals(keterangan))
				event_location = event_location + " " + keterangan;			
			
			String userLoginPegawai = "";
			Statement stmtPegawai = db.getStatement();
			
			String sqlPegawai = "SELECT * FROM TBLPPKRUJUNIT, USERS WHERE UPPER(TBLPPKRUJUNIT.NAMA_PEGAWAI) = UPPER(USERS.USER_NAME) AND"
				+ " USERS.USER_TYPE = 'internal' AND TBLPPKRUJUNIT.ID_UNITPSK = '" + id_ppkrujunit + "'";
			
			ResultSet rsPegawai = stmtPegawai.executeQuery(sqlPegawai);		
			if (rsPegawai.next()) {
				if (rsPegawai.getString("USER_LOGIN") != null) {
				userLoginPegawai = rsPegawai.getString("USER_LOGIN");
				}
			}

			saveActivityEvent(userLoginPegawai, Long.valueOf(id_perbicaraan), event_text, event_location, tarikh_bicara, masa_bicara, socJenisWaktu);
			/** END ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE **/

			// :::SUB
			// ID_STATUS : status_notisperbicaraan
			// ID_SUBURUSAN : 341
			FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
			logic_A.kemaskiniSubUrusanStatusFail(session, id_permohonan,
					id_masuk, status_notisperbicaraan + "", "341", id_fail);
			
			
			//###############add send email#########################			
			 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			 Date date = new Date();  
			 myLogger.info("sysdate ==== "+formatter.format(date));  
			 if ((tarikh_bicara.compareTo(formatter.format(date)) > 0 ) || (tarikh_bicara.compareTo(formatter.format(date)) == 0 ) ){
				 myLogger.info("send email  >>>>>> tarikh_bicara : ");
				 hantarEmelTTPegawai(session,Long.toString(id_perbicaraan),no_fail,nama_simati);
				 
				//###############add send email#########################
				//hantarEmel(session,Long.toString(id_perbicaraan),no_fail,nama_simati);
			 }

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ se.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
		
		return idPerbicaraan;

	}// close addNotis

	// simpan notis tambah (tangguh) SEKSYEN 17
	public static void addNotisTambahSek17(HttpSession session, Hashtable data)
			throws Exception {

		Connection conn = null;
		Db db = null;
		String sql = "";
		String sql5 = "";
		String sql6 = "";

		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// generate other table id
			long id_notisobmst = DB.getNextID("TBLPPKNOTISOBMST_SEQ");
			// long id_perbicaraan = DB.getNextID("TBLPPKPERBICARAAN_SEQ");
			String id_perbicaraan = (String) data.get("id_perbicaraan");

			String bil = (String) data.get("bil_bicara");
			String id_permohonan = (String) data.get("id_permohonan");
			String id_fail = (String) data.get("id_fail");
			String id_suburusanstatusfail = (String) data
					.get("id_suburusanstatusfail");
			String id_keputusanpermohonan = (String) data
					.get("id_keputusanpermohonan");

			String id_ppkrujunit = (String) data.get("pegawai");
			String id_pejabatjkptg = (String) data.get("tempat_bicara");

			String jenispejabat = (String) data.get("jenispejabat");

			String negeri = (String) data.get("negeri");
			String id_masuk = (String) data.get("id_masuk");

			String tarikh_bicara = (String) data.get("tarikh_bicara");
			String masa_bicara = (String) data.get("masa_bicara");
			String alamat1 = (String) data.get("alamat_bicara1");
			String alamat2 = (String) data.get("alamat_bicara2");
			String alamat3 = (String) data.get("alamat_bicara3");
			String poskod = (String) data.get("poskod");
			String tarikh_notis = (String) data.get("tarikh_notis");

			// -- 09122009
			String socJenisWaktu = (String) data.get("socJenisWaktu");

			String nama_pegawai = "";
			String nama_pejabat = "";
			String id_pejabat = "";
			String tablePejabat = "";

			String TB = "to_date('" + tarikh_bicara + "','dd/MM/yyyy')";
			String TN = "to_date('" + tarikh_notis + "','dd/MM/yyyy')";

			// get nama pegawai n pejabat

			SQLRenderer rMT = new SQLRenderer();
			rMT.add("id_unitpsk");
			rMT.add("nama_pegawai");
			rMT.add("id_unitpsk", id_ppkrujunit);
			sql = rMT.getSQLSelect("Tblppkrujunit");
			ResultSet rsMT = stmt.executeQuery(sql);
			// kalau expected result hanya satu record, guna if (rsMT.next())
			while (rsMT.next()) {
				nama_pegawai = rsMT.getString("nama_pegawai");
			}

			String id_bandar = "";

			if (!jenispejabat.equals("0")) {

				// get pejabat
				if (jenispejabat.equals("22")) {
					id_pejabat = "id_pejabatjkptg";
					tablePejabat = "Tblrujpejabatjkptg";
				} else if (jenispejabat.equals("2")) {
					id_pejabat = "id_pejabat";
					tablePejabat = "Tblrujpejabat";
				} else {
					id_pejabat = "id_pejabatjkptg";
					tablePejabat = "Tblrujpejabatjkptg";
				}

				SQLRenderer rMP = new SQLRenderer();
				rMP.add("nama_pejabat");
				rMP.add("id_bandar");
				rMP.add(id_pejabat, id_pejabatjkptg);
				sql = rMP.getSQLSelect(tablePejabat);

				ResultSet rsP = stmt.executeQuery(sql);
				while (rsP.next()) {
					nama_pejabat = rsP.getString("nama_pejabat");
					id_bandar = rsP.getString("id_bandar");
				}

			} else {
				nama_pejabat = id_pejabatjkptg;
			}

			String keterangan = "";

			if (id_bandar != "") {
				SQLRenderer rb = new SQLRenderer();
				rb.add("keterangan");
				rb.add("id_bandar", id_bandar);
				sql = rb.getSQLSelect("Tblrujbandar");

				ResultSet rsPb = stmt.executeQuery(sql);
				while (rsPb.next()) {
					keterangan = rsPb.getString("keterangan");
				}
			}

			// add data (notis perbicaraan) with bil = 1
			SQLRenderer r = new SQLRenderer();
			r.add("id_perbicaraan", id_perbicaraan);
			r.add("id_keputusanpermohonan", id_keputusanpermohonan);
			r.add("id_unitpsk", id_ppkrujunit);
			r.add("bil_bicara", bil);
			r.add("masa_bicara", masa_bicara);
			r.add("alamat_bicara1", alamat1);
			r.add("alamat_bicara2", alamat2);
			r.add("alamat_bicara3", alamat3);
			r.add("poskod", poskod);
			r.add("peg_pengendali", nama_pegawai);
			r.add("tempat_bicara", nama_pejabat);
			r.add("tarikh_bicara", r.unquote(TB));
			r.add("tarikh_notis", r.unquote(TN));
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("id_negeribicara", negeri);
			r.add("id_masuk", id_masuk);

			// -- 09122009
			r.add("jenis_masa_bicara", socJenisWaktu);

			if (keterangan != "") {
				r.add("bandar", keterangan);
			} else {
				r.add("bandar", "");
			}

			if (!jenispejabat.equals("0")) {
				r.add("id_pejabat", id_pejabatjkptg);
			} else {
				r.add("id_pejabat", "0");
			}

			r.add("id_jenispejabat", jenispejabat);
			sql = r.getSQLInsert("Tblppkperbicaraan");
			stmt.executeUpdate(sql);

			// create table notisobmst
			SQLRenderer rMST = new SQLRenderer();
			rMST.add("id_notisobmst", id_notisobmst);
			rMST.add("id_masuk", id_masuk);
			rMST.add("tarikh_masuk", rMST.unquote("sysdate"));
			sql = rMST.getSQLInsert("Tblppknotisobmst");
			stmt.executeUpdate(sql);

			// create child table
			SQLRenderer r1 = new SQLRenderer();
			r1.add("id_perbicaraan", id_perbicaraan);
			r1.add("id_notisobmst", id_notisobmst);
			r1.add("flag_jenis_notis", 0);
			r1.add("id_masuk", id_masuk);
			r1.add("tarikh_masuk", r1.unquote("sysdate"));
			sql = r1.getSQLInsert("Tblppknotisperbicaraan");
			stmt.executeUpdate(sql);

			// tukar status permohonan diteruskan => notis perbicaraan
			int status_notisperbicaraan = 18;

			/*
			 * SQLRenderer rP = new SQLRenderer();
			 * rP.update("id_permohonan",id_permohonan); rP.add("id_status",
			 * status_notisperbicaraan); rP.add("id_kemaskini",id_masuk);
			 * rP.add("tarikh_kemaskini", rP.unquote("sysdate")); sql =
			 * rP.getSQLUpdate("Tblppkpermohonan"); stmt.executeUpdate(sql);
			 */

			// update n add tblrujsuburusanstatus
			/*
			 * SQLRenderer r6 = new SQLRenderer();
			 * r6.update("id_Suburusanstatusfail",id_suburusanstatusfail);
			 * r6.add("AKTIF",0); r6.add("ID_KEMASKINI",id_masuk);
			 * r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate")); sql6 =
			 * r6.getSQLUpdate("tblrujsuburusanstatusfail");
			 * stmt.executeUpdate(sql6);
			 */

			// 341 = notis perbicaraan
			/*
			 * SQLRenderer r5 = new SQLRenderer();
			 * r5.add("ID_SUBURUSANSTATUSFAIL"
			 * ,DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			 * r5.add("ID_PERMOHONAN",id_permohonan); r5.add("ID_FAIL",id_fail);
			 * r5.add("ID_SUBURUSANSTATUS",341); r5.add("AKTIF",1);
			 * r5.add("ID_MASUK",id_masuk); r5.add("ID_KEMASKINI",id_masuk);
			 * r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
			 * r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate")); sql5 =
			 * r5.getSQLInsert("tblrujsuburusanstatusfail");
			 * stmt.executeUpdate(sql5);
			 */

			conn.commit();
			
			Statement stmtfail = db.getStatement();
			SQLRenderer rfail = new SQLRenderer();
			String sql_fail = "SELECT F.NO_FAIL FROM  TBLPPKPERBICARAAN B,"
					+ " TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERMOHONAN P,TBLPFDFAIL F "
					+ " WHERE B.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN "
					+ " AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND P.ID_FAIL = F.ID_FAIL AND B.ID_PERBICARAAN = '"
					+ id_perbicaraan + "'";
			ResultSet rsfail = stmtfail.executeQuery(sql_fail);
			String no_fail = "";
			while (rsfail.next()) {
				if (rsfail.getString("NO_FAIL") != null) {
					no_fail = rsfail.getString("NO_FAIL");
				}
			}
			
			/** START ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE **/			
			String event_id = "PERBICARAAN PUSAKA KECIL BAGI FAIL " + no_fail;
			String event_text = "PERBICARAAN PUSAKA KECIL BAGI FAIL " + no_fail;
			String event_location = "";
			if (!"".equals(nama_pejabat))
				event_location = event_location + nama_pejabat;
			if (!"".equals(alamat1))
				event_location = event_location + ", " + alamat1;
			if (!"".equals(alamat2))
				event_location = event_location + ", " + alamat2;
			if (!"".equals(alamat3))
				event_location = event_location + ", " + alamat3;
			if (!"".equals(poskod))
				event_location = event_location + ", " + poskod;
			if (!"".equals(keterangan))
				event_location = event_location + " " + keterangan;			
			
			String userLoginPegawai = "";
			Statement stmtPegawai = db.getStatement();
			
			String sqlPegawai = "SELECT * FROM TBLPPKRUJUNIT, USERS WHERE UPPER(TBLPPKRUJUNIT.NAMA_PEGAWAI) = UPPER(USERS.USER_NAME) AND"
				+ " USERS.USER_TYPE = 'internal' AND TBLPPKRUJUNIT.ID_UNITPSK = '" + id_ppkrujunit + "'";
			
			ResultSet rsPegawai = stmtPegawai.executeQuery(sqlPegawai);		
			if (rsPegawai.next()) {
			if (rsPegawai.getString("USER_LOGIN") != null) {
				userLoginPegawai = rsPegawai.getString("USER_LOGIN");
				}
			}

			saveActivityEvent(userLoginPegawai, Long.valueOf(id_perbicaraan), event_text, event_location, tarikh_bicara, masa_bicara, socJenisWaktu);
			/** END ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE **/

			// :::SUB
			// ID_STATUS : status_notisperbicaraan
			// ID_SUBURUSAN : 341
			FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
			logic_A.kemaskiniSubUrusanStatusFail(session, id_permohonan,
					id_masuk, status_notisperbicaraan + "", "341", id_fail);

		} catch (SQLException se) {
			try {
				//
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data ");
		} finally {
			if (db != null)
				db.close();
		}

	}// close addNotis
	
	
	public static String checkDataWujud(String ID_NOTISOBMST) throws Exception {
		Db db = null;
		String ID_NOTISOBDTL = "";
		listKPPenjaga.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT ID_NOTISOBDTL FROM TBLPPKNOTISOBDTL WHERE ID_NOTISOBMST = '" + ID_NOTISOBMST + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			if (rs.next()) {
				ID_NOTISOBDTL = rs.getString("ID_NOTISOBDTL") == null ? "" : rs.getString("ID_NOTISOBDTL");
			}
		} finally {
			if (db != null)
				db.close();
		}
		return ID_NOTISOBDTL;
	}

	public boolean checkKPPenjaga(String idobminor, String kpbaru,
			String kplama, String kplain) throws Exception {
		Db db = null;
		boolean a = false;
		listKPPenjaga.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT DISTINCT P.ID_OBMINOR, P.NAMA_PENJAGA, P.NO_KP_BARU, ";
			sql += " P.NO_KP_LAMA, P.NO_KP_LAIN  FROM TBLPPKPENJAGA P ";
			sql += " WHERE P.ID_OBMINOR = '" + idobminor + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {

				String kp_baru = rs.getString("NO_KP_BARU") == null ? "" : rs
						.getString("NO_KP_BARU");
				String kp_lama = rs.getString("NO_KP_LAMA") == null ? "" : rs
						.getString("NO_KP_LAMA");
				String kp_lain = rs.getString("NO_KP_LAIN") == null ? "" : rs
						.getString("NO_KP_LAIN");

				if ((kpbaru != "" && kp_baru != "" && kp_baru.equals(kpbaru))) {

					a = true;
				}
				if ((kplama != "" && kp_lama != "" && kp_lama.equals(kplama))) {

					a = true;
				}
				if ((kplain != "" && kp_lain != "" && kp_lain.equals(kplain))) {

					a = true;
				}

			}

			// return a;
		} finally {
			if (db != null)
				db.close();

		}
		return a;
	}

	// Added on 5/4/2010
	public static String getNoSubjaketByIdPermohonan(String idPermohonan)
			throws Exception {

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("NO_SUBJAKET");
			r.add("ID_PERMOHONAN", idPermohonan);
			sql = r.getSQLSelect("TBLPPKPERMOHONAN");
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getString("NO_SUBJAKET");
			} else {
				return "";
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	// Added on 5/4/2010
	public static String getIdPermohonanByIdPermohonanSimati(
			String idPermohonanSimati) throws Exception {

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_permohonan");
			r.add("id_permohonansimati", idPermohonanSimati);
			sql = r.getSQLSelect("TBLPPKPERMOHONANSIMATI");
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getString("id_permohonan");
			} else {
				return "";
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//aishahlatip 8/5/2017
	public static Hashtable viewPerbicaraan(HttpSession session, String id_perbicaraan) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		myLogger.info(" USER_UNIT :" + USER_UNIT);
		myLogger.info(" id_perbicaraan :" + id_perbicaraan);
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
			if(!id_perbicaraan.equals(""))
			{
				
				sql = " SELECT a.*, b.NAMA_NEGERI FROM Tblppkperbicaraan a, TBLRUJNEGERI b " +
					  " WHERE a.ID_NEGERIBICARA = b.ID_NEGERI and a.ID_PERBICARAAN = '" + id_perbicaraan +"' ";
		
				myLogger.info(" maklumat perbicaraan : viewPerbicaraan :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				while (rs.next()) {				

					h.put("ID_PERBICARAAN",rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN"));
					h.put("ID_KEPUTUSANPERMOHONAN",rs.getString("ID_KEPUTUSANPERMOHONAN") == null ? "" : rs.getString("ID_KEPUTUSANPERMOHONAN"));
					h.put("TARIKH_NOTIS", rs.getDate("TARIKH_NOTIS") == null ? ""
							: Format.format(rs.getDate("TARIKH_NOTIS")));
					h.put("TARIKH_BICARA", rs.getDate("TARIKH_BICARA") == null ? ""
							: Format.format(rs.getDate("TARIKH_BICARA")));
					h.put("MASA_BICARA",rs.getString("MASA_BICARA") == null ? "" : rs.getString("MASA_BICARA"));
					h.put("TEMPAT_BICARA",rs.getString("TEMPAT_BICARA") == null ? "" : rs.getString("TEMPAT_BICARA"));
					h.put("BIL_BICARA",rs.getString("BIL_BICARA") == null ? "" : rs.getString("BIL_BICARA"));
					h.put("ALAMAT_BICARA1",rs.getString("ALAMAT_BICARA1") == null ? "" : rs.getString("ALAMAT_BICARA1"));
					h.put("ALAMAT_BICARA2",rs.getString("ALAMAT_BICARA2") == null ? "" : rs.getString("ALAMAT_BICARA2"));
					h.put("ALAMAT_BICARA3",rs.getString("ALAMAT_BICARA3") == null ? "" : rs.getString("ALAMAT_BICARA3"));
					h.put("BANDAR",rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR"));
					h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
					h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
					h.put("PEG_PENGENDALI",rs.getString("PEG_PENGENDALI") == null ? "" : rs.getString("PEG_PENGENDALI"));
					h.put("JENIS_MASA_BICARA",rs.getString("JENIS_MASA_BICARA") == null ? "" : rs.getString("JENIS_MASA_BICARA"));
					
					String id_unit = (rs.getString("ID_UNITPSK") == null ? "" : rs.getString("ID_UNITPSK"));
					
					h.put("id_unit",id_unit == null ? "" : id_unit);
					
					String masaBicara = (rs.getString("JENIS_MASA_BICARA") == null ? "" : rs.getString("JENIS_MASA_BICARA"));
					String jenisMasaBicara = "";
					if(masaBicara.equals("1")){
						jenisMasaBicara = "PAGI";
					}else if(masaBicara.equals("2")){
						jenisMasaBicara = "TENGAHARI";
					}else if(masaBicara.equals("3")){
						jenisMasaBicara = "PETANG";
					}
					
					h.put("jenisMasaBicara",jenisMasaBicara == null ? "" : jenisMasaBicara);
				
				}
			}
			else
			{
				h.put("ID_PERBICARAAN","");
				h.put("ID_KEPUTUSANPERMOHONAN","");
				h.put("TARIKH_NOTIS","");
				h.put("TARIKH_BICARA","");
				h.put("MASA_BICARA","");
				h.put("TEMPAT_BICARA","");
				h.put("BIL_BICARA","");
				h.put("ALAMAT_BICARA1","");
				h.put("ALAMAT_BICARA2","");
				h.put("ALAMAT_BICARA3","");
				h.put("BANDAR","");
				h.put("POSKOD","");
				h.put("NAMA_NEGERI","");
				h.put("PEG_PENGENDALI","");
				h.put("JENIS_MASA_BICARA","");
				h.put("id_unit","");
				h.put("jenisMasaBicara","");
			
			}
			return h;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	public static void hantarEmel(HttpSession session,String id_perbicaraan, String no_fail, String nama_simati) throws Exception {
		
		myLogger.info("MASUK FUNCTION EMEL2");	
		
		EmailProperty pro = EmailProperty.getInstance();
		EmailSender email = EmailSender.getInstance();
		email.FROM = pro.getFrom();
		email.MULTIPLE_RECIEPIENT = new String[1];
		String subject = "";
		
		String content = "";

		myLogger.info("hantarEmel");
		Hashtable viewData = viewPerbicaraan(session,id_perbicaraan);;				
		String ID_PERBICARAAN = (String)viewData.get("ID_PERBICARAAN");
		String ID_KEPUTUSANPERMOHONAN = (String)viewData.get("ID_KEPUTUSANPERMOHONAN");
		String TARIKH_NOTIS = (String)viewData.get("TARIKH_NOTIS");
		String TARIKH_BICARA = (String)viewData.get("TARIKH_BICARA");
		String MASA_BICARA = (String)viewData.get("MASA_BICARA");
		String TEMPAT_BICARA = (String)viewData.get("TEMPAT_BICARA");
		String BIL_BICARA = (String)viewData.get("BIL_BICARA");
		String ALAMAT_BICARA1 = (String)viewData.get("ALAMAT_BICARA1");
		String ALAMAT_BICARA2 = (String)viewData.get("ALAMAT_BICARA2");
		String ALAMAT_BICARA3 = (String)viewData.get("ALAMAT_BICARA3");
		String BANDAR = (String)viewData.get("BANDAR"); 
		String NAMA_NEGERI = (String)viewData.get("NAMA_NEGERI"); 
		String PEG_PENGENDALI = (String)viewData.get("PEG_PENGENDALI"); 
		String JENIS_MASA_BICARA = (String)viewData.get("JENIS_MASA_BICARA"); 
		String id_unit = (String)viewData.get("id_unit"); 
		myLogger.info("id_unit = "+id_unit);
		String jenisMasaBicara = (String)viewData.get("jenisMasaBicara"); 
		
		String tujuan = "";
		
			subject = " MyeTaPP Pembahagian Pusaka : Makluman bagi Notis Perbicaraan No Fail "+no_fail;
			tujuan = " Pemakluman Notis Perbicaraan yang memerlukan tindakan Tuan/Puan. Sila semak permohonan ini di MyeTaPP.";

			
			content = "<html><table>" +
				/*	"<tr><td colspan=3> EMAIL INI DIJANA BERTUJUAN UNTUK PENGUJIAN SISTEM MYETTAP. SILA ABAIKAN EMAIL INI. TERIMA KASIH. </td></tr>" +*/
					"<tr><td>Tujuan</td><td>:</td><td>"+tujuan+"</td></tr>" +
					"<tr><td>No.Fail</td><td>:</td><td>"+no_fail+"</td></tr>" +
					"<tr><td>Nama Si Mati</td><td>:</td><td>"+nama_simati+"</td></tr>" +
					"<tr><td>Bil Bicara</td><td>:</td><td>"+BIL_BICARA+"</td></tr>" +	
					"<tr><td>Tarikh Notis </td><td>:</td><td>"+TARIKH_NOTIS+"</td></tr>" +
					"<tr><td>Tarikh Bicara</td><td>:</td><td>"+TARIKH_BICARA+"</td></tr>" +		
					"<tr><td>Masa Bicara</td><td>:</td><td>"+MASA_BICARA+" "+jenisMasaBicara+"</td></tr>" +		
					"<tr><td>Tempat Bicara</td><td>:</td><td>"+TEMPAT_BICARA+"</td></tr>" +		
					"<tr><td>Alamat Bicara</td><td>:</td><td>"+ALAMAT_BICARA1+"</td></tr>" +		
					"<tr><td>&nbsp;</td><td>:</td><td>"+ALAMAT_BICARA2+"</td></tr>" +		
					"<tr><td>&nbsp;</td><td>:</td><td>"+ALAMAT_BICARA3+"</td></tr>" +		
					"<tr><td>Bandar</td><td>:</td><td>"+BANDAR+"</td></tr>" +		
					"<tr><td>Negeri</td><td>:</td><td>"+NAMA_NEGERI+"</td></tr>" +	
					"<tr><td>Pegawai Pengendali</td><td>:</td><td>"+PEG_PENGENDALI+"</td></tr>" +
					"<tr><td colspan=3>&nbsp;</td></tr>" +
					"<tr><td colspan=3><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em></td></tr>" +
					"</table></html>" ;
			
			
			//email.MULTIPLE_RECIEPIENT[0] = EMEL_PELULUS;
			
			List listEmelPelulus = listEmelPelulus(session, id_unit);
			email.MULTIPLE_RECIEPIENT = new String[listEmelPelulus.size()];
			for(int i = 0; i < listEmelPelulus.size();i++)
			{
				Map m = (Map) listEmelPelulus.get(i);
				String EMEL = (String) m.get("EMEL");
				//int COUNT_EMEL = (int)maklumatTindakan.get("COUNT_EMEL");
				email.MULTIPLE_RECIEPIENT[i] = EMEL;						
			}
		
		
		
		email.SUBJECT = subject;
		email.MESSAGE = content;		
		
		//listPenggunaMengikutRole = listPenggunaMengikutRole(session,"adminekptg",(String) viewPengguna.get("ID_NEGERI"));
		
		//GET EMEL PEGAWAI ADMINEKPTG MENGIKUT NEGERI
		/*
		email.MULTIPLE_RECIEPIENT = new String[listPenggunaMengikutRole.size()];
		for(int i = 0; i < listPenggunaMengikutRole.size();i++)
		   {			   
			   Map m = (Map) listPenggunaMengikutRole.get(i);
			   myLogger.info(" EMEL PENGGUNA :"+(String) m.get("EMEL"));
			   email.MULTIPLE_RECIEPIENT[i] = (String) m.get("EMEL");			  
		 }
		*/
		//email.TO_CC = new String[1];		
		//email.TO_CC[0] = "razman.zainal@gmail.com";
		email.sendEmail();		 
	 }
	
	
public static void hantarEmelTTPegawai(HttpSession session,String id_perbicaraan, String no_fail, String nama_simati) throws Exception {
		
		//System.out.println("MASUK FUNCTION EMEL1");	
		
		EmailProperty pro = EmailProperty.getInstance();
		EmailSender email = EmailSender.getInstance();
		email.FROM = pro.getFrom();
		email.MULTIPLE_RECIEPIENT = new String[1];
		String subject = "";
		
		String content = "";

		/*h.put("ID_PERBICARAAN","");
		h.put("ID_KEPUTUSANPERMOHONAN","");
		h.put("TARIKH_NOTIS","");
		h.put("TARIKH_BICARA","");
		h.put("MASA_BICARA","");
		h.put("TEMPAT_BICARA","");
		h.put("BIL_BICARA","");
		h.put("ALAMAT_BICARA1","");
		h.put("ALAMAT_BICARA2","");
		h.put("ALAMAT_BICARA3","");
		h.put("BANDAR","");
		h.put("POSKOD","");
		h.put("NAMA_NEGERI","");
		h.put("PEG_PENGENDALI","");
		h.put("JENIS_MASA_BICARA","");
		h.put("id_unit","");
		h.put("jenisMasaBicara","");*/
		
		Hashtable viewData = viewPerbicaraan(session,id_perbicaraan);
		//myLogger.info("hantarEmelTTPegawai2");
		String ID_PERBICARAAN = (String)viewData.get("ID_PERBICARAAN");
		String ID_KEPUTUSANPERMOHONAN = (String)viewData.get("ID_KEPUTUSANPERMOHONAN");
		String TARIKH_NOTIS = (String)viewData.get("TARIKH_NOTIS");
		String TARIKH_BICARA = (String)viewData.get("TARIKH_BICARA");
		String MASA_BICARA = (String)viewData.get("MASA_BICARA");
		String TEMPAT_BICARA = (String)viewData.get("TEMPAT_BICARA");
		String BIL_BICARA = (String)viewData.get("BIL_BICARA");
		String ALAMAT_BICARA1 = (String)viewData.get("ALAMAT_BICARA1");
		String ALAMAT_BICARA2 = (String)viewData.get("ALAMAT_BICARA2");
		String ALAMAT_BICARA3 = (String)viewData.get("ALAMAT_BICARA3");
		String BANDAR = (String)viewData.get("BANDAR"); 
		String NAMA_NEGERI = (String)viewData.get("NAMA_NEGERI"); 
		String PEG_PENGENDALI = (String)viewData.get("PEG_PENGENDALI"); 
		String JENIS_MASA_BICARA = (String)viewData.get("JENIS_MASA_BICARA"); 
		String id_unit = (String)viewData.get("id_unit"); 
		String jenisMasaBicara = (String)viewData.get("jenisMasaBicara"); 
		
		String tujuan = "";
		
			subject = " MyeTaPP Pembahagian Pusaka : Tindakan bagi Notis Perbicaraan, memerlukan Kelulusan oleh Pegawai No Fail "+no_fail;
			tujuan = " Pemakluman Notis Perbicaraan yang memerlukan tindakan kelulusan Tuan/Puan. Sila semak permohonan ini di MyeTaPP.";

			
			content = "<html><table>" +
					//"<tr><td colspan=3> EMAIL INI DIJANA BERTUJUAN UNTUK PENGUJIAN SISTEM MYETTAP. SILA ABAIKAN EMAIL INI. TERIMA KASIH. </td></tr>" +
					"<tr><td colspan=3>&nbsp;</td></tr>" +
					"<tr><td colspan=3>Untuk kelulusan,sila login ke MyEtapp :: Modul Pembahagian Pusaka >> Menu Notis Perbicaraan >> Pilih No Fail >> Tab Tindakan Pegawai </td></tr>" +
					"<tr><td colspan=3>&nbsp;</td></tr>" +
					"<tr><td>Tujuan</td><td>:</td><td>"+tujuan+"</td></tr>" +
					"<tr><td>No.Fail</td><td>:</td><td>"+no_fail+"</td></tr>" +
					"<tr><td>Nama Si Mati</td><td>:</td><td>"+nama_simati+"</td></tr>" +
					"<tr><td>Bil Bicara</td><td>:</td><td>"+BIL_BICARA+"</td></tr>" +	
					"<tr><td>Tarikh Notis </td><td>:</td><td>"+TARIKH_NOTIS+"</td></tr>" +
					"<tr><td>Tarikh Bicara</td><td>:</td><td>"+TARIKH_BICARA+"</td></tr>" +		
					"<tr><td>Masa Bicara</td><td>:</td><td>"+MASA_BICARA+" "+jenisMasaBicara+"</td></tr>" +		
					"<tr><td>Tempat Bicara</td><td>:</td><td>"+TEMPAT_BICARA+"</td></tr>" +		
					"<tr><td>Alamat Bicara</td><td>:</td><td>"+ALAMAT_BICARA1+"</td></tr>" +		
					"<tr><td>&nbsp;</td><td>:</td><td>"+ALAMAT_BICARA2+"</td></tr>" +		
					"<tr><td>&nbsp;</td><td>:</td><td>"+ALAMAT_BICARA3+"</td></tr>" +		
					"<tr><td>Bandar</td><td>:</td><td>"+BANDAR+"</td></tr>" +		
					"<tr><td>Negeri</td><td>:</td><td>"+NAMA_NEGERI+"</td></tr>" +	
					"<tr><td>Pegawai Pengendali</td><td>:</td><td>"+PEG_PENGENDALI+"</td></tr>" +
					"<tr><td colspan=3>&nbsp;</td></tr>" +
					"<tr><td colspan=3><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em></td></tr>" +
					"</table></html>" ;
			
			
			//email.MULTIPLE_RECIEPIENT[0] = EMEL_PELULUS;
			
			List listEmelPelulus = listEmelPelulus(session, id_unit);
			email.MULTIPLE_RECIEPIENT = new String[listEmelPelulus.size()];
			for(int i = 0; i < listEmelPelulus.size();i++)
			{
				Map m = (Map) listEmelPelulus.get(i);
				String EMEL = (String) m.get("EMEL");
				//int COUNT_EMEL = (int)maklumatTindakan.get("COUNT_EMEL");
				email.MULTIPLE_RECIEPIENT[i] = EMEL;						
			}
		
		
		
		email.SUBJECT = subject;
		email.MESSAGE = content;		
		
		//listPenggunaMengikutRole = listPenggunaMengikutRole(session,"adminekptg",(String) viewPengguna.get("ID_NEGERI"));
		
		//GET EMEL PEGAWAI ADMINEKPTG MENGIKUT NEGERI
		/*
		email.MULTIPLE_RECIEPIENT = new String[listPenggunaMengikutRole.size()];
		for(int i = 0; i < listPenggunaMengikutRole.size();i++)
		   {			   
			   Map m = (Map) listPenggunaMengikutRole.get(i);
			   myLogger.info(" EMEL PENGGUNA :"+(String) m.get("EMEL"));
			   email.MULTIPLE_RECIEPIENT[i] = (String) m.get("EMEL");			  
		 }
		*/
		//email.TO_CC = new String[1];		
		//email.TO_CC[0] = "razman.zainal@gmail.com";
		email.sendEmail();		 
	 }


public static List listEmelPelulus(HttpSession session, String ID_UNIT)throws Exception {
	Db db = null;
	ResultSet rs = null;
	Statement stmt = null;
	List listEmelPelulus = null;
	String sql = "";
	try {
		db = new Db();
		stmt = db.getStatement();
		
		sql = " SELECT EMEL FROM TBLPPKRUJUNIT WHERE ID_UNITPSK =  "+ID_UNIT;
		
		myLogger.info(" SQL : listEmelPelulus :"+ sql);			
		rs = stmt.executeQuery(sql);
		listEmelPelulus = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));	
			listEmelPelulus.add(h);
		}

	} finally {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (db != null)
			db.close();
	}
	return listEmelPelulus;
}

//aishahlatip15052017 : untuk dapatkan samaada negeri cuti am pada jumaat sabtu atau sabtu ahad
public int getFlagCuti(String idNegeri) throws Exception {

	Db db = null;

	String sql = "";
	int flag = 0;
	
	try {

		db = new Db();

		Statement stmt = db.getStatement();

		sql = "SELECT FLAG_CUTI " +
				" FROM TBLRUJNEGERI WHERE ID_NEGERI = '" + idNegeri + "'";

		ResultSet rs = stmt.executeQuery(sql);
		Vector list = new Vector();

		Hashtable h;
		

		while (rs.next()) {
			flag = rs.getInt(1);

		}
		//return flag;
	} catch (Exception er) {
		myLogger.error(er);

	} finally {
		if (db != null)
			db.close();
	}
	return flag;
}

public static List getListEmailAddress(List listEmailAdd, String finalSqlOB) throws Exception {
	Db db = null;
	Vector result = new Vector();
	String sql = "";
	String addressEmel = "";

	try {

		db = new Db();

		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		sql = "SELECT E.EMEL FROM TBLPPKOBPERMOHONAN E WHERE E.ID_OB IN (" + finalSqlOB + ")";

		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;

		while (rs.next()) {
			addressEmel = rs.getString("EMEL") == null ? "" : rs.getString("EMEL");
			if(!listEmailAdd.contains(addressEmel)){
				listEmailAdd.add(addressEmel);
			}
		}
	} catch (SQLException e) {
		myLogger.error(e);
	} catch (Exception er) {
		myLogger.error(er);
		throw er;
	} finally {
		if (db != null)
			db.close();
	}

	return listEmailAdd;
}

public static Vector getIdFail(String idPermohonan) throws Exception {

	Db db = null;
	Vector result = new Vector();
	String sql = "";

	try {

		db = new Db();

		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		sql = "SELECT P.ID_FAIL FROM TBLPPKPERMOHONAN P WHERE P.ID_PERMOHONAN = '" + idPermohonan + "'";

		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;

		while (rs.next()) {
			h = new Hashtable();
			h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
			result.addElement(h);
		}
	} catch (SQLException e) {
		myLogger.error(e);
	} catch (Exception er) {
		myLogger.error(er);
		throw er;
	} finally {
		if (db != null)
			db.close();
	}
	return result;
}

public static String hantarEmail(String idFail, List listEmailAdd) throws Exception {
	// set mapping field email
	Db db = null;
	try {
		db = new Db();
		Statement stmt = db.getStatement();
		Statement stmtNotis = db.getStatement();
		ResultSet rs = null;
		ResultSet rsNotis = null;
		String sql = "";
		String status = "";
		//EmailProperty email = EmailProperty.getInstance();\
		
		EmailProperty pro = EmailProperty.getInstance();
		EmailSender email = EmailSender.getInstance();
		if (listEmailAdd.size() > 0) {
			String EMAIL_FROM = "", EMAIL_SUBJECT = "", EMAIL_BODY = "";
//			EMAIL_FROM = "admin.ppk@kptg.gov.my";
			//EMAIL_FROM = "appmailer@ilaunch.com.my";
			EMAIL_FROM = pro.getFrom();
			EMAIL_SUBJECT = "NOTIS PETISYEN DAN PENDENGARAN (Seksyen 9)";
			EMAIL_BODY = "";

			if (!"".equalsIgnoreCase(EMAIL_FROM.trim()) && !"".equalsIgnoreCase(EMAIL_SUBJECT.trim())) {

				
				StringBuffer bff = new StringBuffer();

				String NO_FAIL="", NAMA_PEMOHON = "", NO_KP_BARU = "", NO_KP_BARU_LAMA = "", ALAMAT_1 = "", ALAMAT_2 = "",ALAMAT_3 = "", POSKOD = "",NAMA_BANDAR_PEMOHON = "",NAMA_NEGERI_PEMOHON = "",NAMA_SIMATI = "",ALAMAT_SIMATI1 = "",
						ALAMAT_SIMATI2 = "",ALAMAT_SIMATI3 = "",POSKOD_SIMATI = "",NAMA_BANDAR_SIMATI = "",NAMA_NEGERI_SIMATI = "",
						BIL_BICARA = "",TARIKH_BICARA = "",TEMPAT_BICARA = "",ALAMAT_BICARA1 = "",ALAMAT_BICARA2 = "",ALAMAT_BICARA3 = "",NAMA_BANDAR_BICARA = "",NAMA_NEGERI_BICARA = "",
						PEG_PENGENDALI  = "",MASA_BICARA="", JENIS_MASA="";
				
				sql = "SELECT F.NO_FAIL, PE.NAMA_PEMOHON, PE.NO_KP_BARU, PE.NO_KP_BARU_LAMA, PE.ALAMAT_1, PE.ALAMAT_2,PE.ALAMAT_3, PE.POSKOD,Initcap(BANPEMOHON.KETERANGAN) AS NAMA_BANDAR_PEMOHON," +
						"Initcap(NEGPEMOHON.NAMA_NEGERI) AS NAMA_NEGERI_PEMOHON,SIMATI.NAMA_SIMATI,SIMATI.ALAMAT_1 AS ALAMAT_SIMATI1,SIMATI.ALAMAT_2 AS ALAMAT_SIMATI2,SIMATI.ALAMAT_3 AS ALAMAT_SIMATI3," +
						"SIMATI.POSKOD AS POSKOD_SIMATI,Initcap(BANSIMATI.KETERANGAN) AS NAMA_BANDAR_SIMATI,Initcap(NEGSIMATI.NAMA_NEGERI) AS NAMA_NEGERI_SIMATI, BIC.BIL_BICARA,BIC.TARIKH_BICARA," +
						"Initcap(BIC.TEMPAT_BICARA)AS TEMPAT_BICARA, Initcap(BIC.ALAMAT_BICARA1) AS ALAMAT_BICARA1,Initcap(BIC.ALAMAT_BICARA2) AS ALAMAT_BICARA2," +
						"Initcap(BIC.ALAMAT_BICARA3) AS ALAMAT_BICARA3,Initcap(BIC.BANDAR) AS NAMA_BANDAR_BICARA,Initcap(NEGBICARA.NAMA_NEGERI) AS NAMA_NEGERI_BICARA," +
						"Initcap(BIC.PEG_PENGENDALI) AS PEG_PENGENDALI ,BIC.MASA_BICARA,BIC.JENIS_MASA_BICARA FROM TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI SI,TBLPPKSIMATI SIMATI,TBLPPKPEMOHON PE," +
						"TBLPFDFAIL F,TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERBICARAAN BIC,TBLRUJBANDAR BANPEMOHON,TBLRUJBANDAR BANSIMATI,TBLRUJNEGERI NEGPEMOHON," +
						" TBLRUJNEGERI NEGBICARA,TBLRUJNEGERI NEGSIMATI WHERE P.ID_FAIL = F.ID_FAIL AND PE.ID_BANDAR = BANPEMOHON.ID_BANDAR(+) AND SIMATI.ID_BANDAR = BANSIMATI.ID_BANDAR(+) " +
						"AND BIC.ID_NEGERIBICARA = NEGBICARA.ID_NEGERI(+) AND SIMATI.ID_NEGERI = NEGSIMATI.ID_NEGERI(+) AND PE.ID_NEGERI = NEGPEMOHON.ID_NEGERI(+) " +
						"AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) AND KP.ID_KEPUTUSANPERMOHONAN = BIC.ID_KEPUTUSANPERMOHONAN(+) AND P.ID_PEMOHON = PE.ID_PEMOHON(+) AND P.ID_PERMOHONAN = SI.ID_PERMOHONAN(+) " +
						"AND SI.ID_SIMATI = SIMATI.ID_SIMATI(+) AND F.ID_FAIL =" + idFail + " AND BIC.BIL_BICARA = (SELECT MAX(BIC.BIL_BICARA) AS BIL_BICARA FROM TBLPPKPERMOHONAN P " +
						",TBLPFDFAIL F,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN BIC, TBLRUJNEGERI NEG WHERE P.ID_FAIL = F.ID_FAIL AND BIC.ID_NEGERIBICARA = NEG.ID_NEGERI(+) " +
						"AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) AND KP.ID_KEPUTUSANPERMOHONAN = BIC.ID_KEPUTUSANPERMOHONAN(+) " +
						"AND F.ID_FAIL =" + idFail + ")";

				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					NO_FAIL= rs.getString(1) == null ? "" : rs.getString(1);
					NAMA_PEMOHON = rs.getString(2) == null ? "" : rs.getString(2);
					NO_KP_BARU = rs.getString(3) == null ? "" : rs.getString(3);
					NO_KP_BARU_LAMA = rs.getString(4) == null ? "" : rs.getString(4);
					ALAMAT_1 = rs.getString(5) == null ? "" : rs.getString(5);
					ALAMAT_2 = rs.getString(6) == null ? "" : rs.getString(6);
					ALAMAT_3 = rs.getString(7) == null ? "" : rs.getString(7); 
					POSKOD = rs.getString(8) == null ? "" : rs.getString(8);
					NAMA_BANDAR_PEMOHON = rs.getString(9) == null ? "" : rs.getString(9);
					NAMA_NEGERI_PEMOHON = rs.getString(10) == null ? "" : rs.getString(10);
					NAMA_SIMATI = rs.getString(11) == null ? "" : rs.getString(11);
					ALAMAT_SIMATI1 = rs.getString(12) == null ? "" : rs.getString(12);
					ALAMAT_SIMATI2 = rs.getString(13) == null ? "" : rs.getString(13);
					ALAMAT_SIMATI3 = rs.getString(14) == null ? "" : rs.getString(14);
					POSKOD_SIMATI = rs.getString(15) == null ? "" : rs.getString(15);
					NAMA_BANDAR_SIMATI = rs.getString(16) == null ? "" : rs.getString(16);
					NAMA_NEGERI_SIMATI = rs.getString(17) == null ? "" : rs.getString(17);
					BIL_BICARA = rs.getString(18) == null ? "" : rs.getString(18);
					TARIKH_BICARA = rs.getString(19) == null ? "" : rs.getString(19);
					TEMPAT_BICARA = rs.getString(20) == null ? "" : rs.getString(20);
					ALAMAT_BICARA1 = rs.getString(21) == null ? "" : rs.getString(21);
					ALAMAT_BICARA2 = rs.getString(22) == null ? "" : rs.getString(22);
					ALAMAT_BICARA3 = rs.getString(23) == null ? "" : rs.getString(23);
					NAMA_BANDAR_BICARA = rs.getString(24) == null ? "" : rs.getString(24);
					NAMA_NEGERI_BICARA = rs.getString(25) == null ? "" : rs.getString(25);
					PEG_PENGENDALI  = rs.getString(26) == null ? "" : rs.getString(26);
					MASA_BICARA= rs.getString(27) == null ? "" : rs.getString(27);
					JENIS_MASA= rs.getString(28) == null ? "" : rs.getString(28);
				}
				
				// header email
				bff.append("Salam Tuan/Puan,");
				bff.append("<br/>");
				bff.append("<br/>");
				bff.append("Untuk makluman Permohonan Pembahagian Pusaka Kecil No Fail ");
				bff.append(NO_FAIL);
				bff.append(" yang telah dibuat oleh ");
				bff.append(NAMA_PEMOHON);
				bff.append(" , No. KP:");
				bff.append(NO_KP_BARU);
				
				if (NO_KP_BARU_LAMA != null && !"".equals(NO_KP_BARU_LAMA)) {
					bff.append("("+NO_KP_BARU_LAMA+")");
				}
				
				bff.append(" yang beralamat di ");
				bff.append(ALAMAT_1+","+ ALAMAT_2+" "+ ALAMAT_3+" "+ POSKOD+","+ NAMA_BANDAR_PEMOHON+","+NAMA_NEGERI_PEMOHON);
				bff.append(" bagi pembahagian harta pusaka dan barang kepunyaan ");
				bff.append(NAMA_SIMATI);
				bff.append(" si mati, yang dahulunya beralamat di ");
				bff.append(ALAMAT_SIMATI1+" "+ ALAMAT_SIMATI2+" "+ ALAMAT_SIMATI3+" "+ POSKOD_SIMATI+","+ NAMA_BANDAR_SIMATI+" "+NAMA_NEGERI_SIMATI);
				
				bff.append("<br/>");
				bff.append("<br/>");
				
				bff.append("Permohonan ini akan didengar di ");
				bff.append(TEMPAT_BICARA+","+ ALAMAT_BICARA1+" "+ALAMAT_BICARA2+" "+ALAMAT_BICARA3+" "+NAMA_BANDAR_BICARA+" "+NAMA_NEGERI_BICARA);
				bff.append(" pada ");
				
				if (TARIKH_BICARA != null && !"".equals(TARIKH_BICARA)) {
					try {
						SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
						bff.append(format2.format(TARIKH_BICARA));
					} catch (Exception er) {
						myLogger.error(er);
						//throw er;
					}
				}
				
				bff.append(" pada pukul ");
				bff.append(MASA_BICARA);
				
				if (JENIS_MASA != null && !"".equals(JENIS_MASA)) {
					if("1".equals(JENIS_MASA)){
						bff.append(" pagi");		
					}else if("2".equals(JENIS_MASA)){
						bff.append(" tengahari");
					}else if("3".equals(JENIS_MASA)){
						bff.append(" petang");
					}
				}
				
				bff.append("<br/>");
				bff.append("<br/>");
				
				bff.append("Semua orang yang menuntut mempunyai apa-apa kepentingan dalam harta pusaka si mati adalah dikehendaki hadir pada masa dan tempat yang disebutkan di atas.");
				
				bff.append("<br/>");
				bff.append("<br/>");
				
				bff.append("Mana-mana orang yang ingin membantah kepada permohonan ini bolehlah membuat bantahan bertulis kepada yang bertandatangan di bawah sebelum tarikh di atas.");
				
				bff.append("<br/>");
				bff.append("<br/>");
				
				bff.append("Harap maklum.");
				bff.append("<br/>");
				
				bff.append("Sekian, terima kasih.");
				
				bff.append("<br/>");
				bff.append("<br/>");
				
				bff.append("webmaster@etapp.gov.my");
				
				bff.append("<br/>");
				bff.append("<br/>");
				bff.append("<br/>");
				
				bff.append("Nota: E-mel ini dijana dari sistem eTaPP dan tidak memerlukan balasan.");
				
				bff.append("<br/>");
				bff.append("<br/>");
				bff.append("<br/>");
				
				bff.append("---------------------------------------------------------------------------------------");
				
				
				EMAIL_BODY = bff.toString();
				System.out.println(EMAIL_BODY);

				Date date = new Date();
				email.FROM = EMAIL_FROM;
				List emails = new ArrayList<Object>();
				//validate email address
				for (int i = 0; i < listEmailAdd.size(); i++) {
					String address = (String)listEmailAdd.get(i);
					if(isValidEmail(address)){
						emails.add(address);	
					}
				}
				
				boolean flagSendMail = false;
				if(emails.size()>1){
					email.MULTIPLE_RECIEPIENT = new String[emails.size()];
					
					for (int i = 0; i < emails.size(); i++) {
						String address = (String) emails.get(i);
						if(address!=null && address.length()>0)
						email.MULTIPLE_RECIEPIENT[i] = address.trim();
						flagSendMail = true;
					}	
				}else if(emails.size()==1){
					String emaile = (String)emails.get(0);
					System.out.println("("+emaile+")");
					email.RECIEPIENT = emaile;
					flagSendMail = true;
				}

				// email.RECIEPIENT = EMAIL_ADDR;
				email.SUBJECT = EMAIL_SUBJECT;
				email.MESSAGE = EMAIL_BODY;
				if(flagSendMail){
					email.sendEmail();
				}
			}
		}
	} catch (DbException e) {
		myLogger.error(e);
		return "fail";
	} catch (Exception er) {
		myLogger.error(er);
		throw er;
	} finally {
		if (db != null)
			db.close();
	}

	// update tarikh hantar

	return "successEmail";
}


public static boolean isValidEmail(String enteredEmail){
    String EMAIL_REGIX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    Pattern pattern = Pattern.compile(EMAIL_REGIX);
    Matcher matcher = pattern.matcher(enteredEmail);
    return ((!enteredEmail.isEmpty()) && (enteredEmail!=null) && (matcher.matches()));
} 	

//aishahlatip 18052017
//checking sama ada user yang login adalah pegawai pengendali
public static void setValidPegawaiPengendali(String userId,String idPerbicaraan, String NamaPeg, String username) throws Exception {

	Db db = null;
	Hashtable h;
	validPegPengendali.clear();
	String sql = "";
	String sql2 = "";
	String idOBdtl = "";

	try {

		db = new Db();

		Statement stmtN = db.getStatement();

		sql = " SELECT A.USER_ID " +
				" FROM TBLPPKRUJUNIT A, TBLPPKPERBICARAAN B " +
				" WHERE A.ID_UNITPSK = B.ID_UNITPSK " +
				" AND A.USER_ID = '"+userId+"' " +
				" AND B.ID_PERBICARAAN ='"+idPerbicaraan+"'";

		ResultSet rs = stmtN.executeQuery(sql);
		int i = 0;

		if (rs.next()) {
			
			h = new Hashtable();
			h.put("USER_ID_PEG",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
			validPegPengendali.addElement(h);
		}else{
			
			
			
			sql2 = " SELECT A.USER_ID " +
				" FROM TBLPPKRUJUNIT A, TBLPPKPERBICARAAN B " +
				" WHERE A.ID_UNITPSK = B.ID_UNITPSK " +
				" AND A.NAMA_PEGAWAI = '"+username+"' " +
				" AND B.ID_PERBICARAAN ='"+idPerbicaraan+"' ";

			
				rs = stmtN.executeQuery(sql2);
				
				System.out.println("sql2b==="+sql2);
				if (rs.next()) {
					h = new Hashtable();
					h.put("USER_ID_PEG",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
					validPegPengendali.addElement(h);
				}
			
		}

		

	} finally {
		if (db != null)
			db.close();
	}
}

public static boolean getPNBValidation(String idPerbicaraan) throws Exception {

	Db db = null;

	String sql = "";
	String SIGNED_TEXT = "";
	try {

		db = new Db();

		Statement stmt = db.getStatement();

		/*sql = "SELECT A.ID_BORANGPNB " +
				" FROM TBLPPKBORANGPNB A " +
				" WHERE A.ID_PERBICARAAN= '" + idPerbicaraan + "'";*/
		
		sql = "SELECT SIGNED_TEXT FROM TBLPPKPERBICARAAN WHERE ID_PERBICARAAN = '" + idPerbicaraan + "'";

		ResultSet rs = stmt.executeQuery(sql);
		Vector list = new Vector();

		Hashtable h;
		boolean flag = false;

		while (rs.next()) {
			h = new Hashtable();
			//SIGNED_TEXT = rs.getString("SIGNED_TEXT") == null ? "" : rs.getString("SIGNED_TEXT");
			SIGNED_TEXT = rs.getString("SIGNED_TEXT");
			System.out.println("SIGNED_TEXT==="+SIGNED_TEXT);
			if(  SIGNED_TEXT.equals("") || SIGNED_TEXT!=null){
				flag = true;
			}

		}
		return flag;
	} catch (Exception er) {
		myLogger.error(er);
		return false;
	} finally {
		if (db != null)
			db.close();
	}
}
	

public String getNamaPengendaliByNegeri(String idNegeri, String idpsk) throws Exception {

	Db db = null;

	String sql = "";
	String NAMA_PEGAWAI = "";
	try {

		db = new Db();

		Statement stmt = db.getStatement();

		sql = "SELECT ID_UNITPSK, KOD, NAMA_PEGAWAI,STATUS_PEG" + ",case " + " when STATUS_PEG=1 then '(Aktif)' "
				+ " else '(Tidak Aktif)' " + " end AS CATATAN " + "FROM TBLPPKRUJUNIT WHERE ID_NEGERI = '" + idNegeri
				+ "' ORDER BY LPAD(KOD,100)";

		ResultSet rs = stmt.executeQuery(sql);
		Vector list = new Vector();

		Hashtable h;
		

		while (rs.next()) {
			NAMA_PEGAWAI = rs.getString("NAMA_PEGAWAI");

		}
		return NAMA_PEGAWAI;
	} catch (Exception er) {
		myLogger.error(er);
		return NAMA_PEGAWAI;
	} finally {
		if (db != null)
			db.close();
	}
}

public String getNamaPengendali( String idpsk) throws Exception {

	Db db = null;

	String sql = "";
	String NAMA_PEGAWAI = "";
	try {


		db = new Db();

		Statement stmt = db.getStatement();

		sql = "SELECT ID_UNITPSK, KOD, NAMA_PEGAWAI,STATUS_PEG" + ",case " + " when STATUS_PEG=1 then '(Aktif)' "
				+ " else '(Tidak Aktif)' " + " end AS CATATAN " + "FROM TBLPPKRUJUNIT WHERE ID_UNITPSK = '" + idpsk
				+ "' ORDER BY LPAD(KOD,100)";

		ResultSet rs = stmt.executeQuery(sql);
		Vector list = new Vector();

		Hashtable h;
		

		while (rs.next()) {
			NAMA_PEGAWAI = rs.getString("NAMA_PEGAWAI");

		}
		return NAMA_PEGAWAI;
	} catch (Exception er) {
		myLogger.error(er);
		return NAMA_PEGAWAI;
	} finally {
		if (db != null)
			db.close();
	}
}


//hide attachment
public static void addHantarLaporanNotisTemp(Hashtable data, String id_ob, String id_perbicaraan,HttpSession session,ServletContext application,
		HttpServletRequest request,HttpServletResponse response) throws Exception {

//public static void addHantarLaporanNotis(Hashtable data, String id_ob, String id_perbicaraan) throws Exception {

	Db db = null;

	String sql = "";

	try {
		db = new Db();
		Statement stmt = db.getStatement();

		long id_notisobmst = DB.getNextID("TBLPPKNOTISOBMST_SEQ");
		String id_masuk = (String) data.get("id_masuk");
		
		/*System.out.println("id_masuk==="+id_masuk);
		System.out.println("id_perbicaraan==="+id_perbicaraan);
		System.out.println("id_notisobmst==="+id_notisobmst);*/
		
		SQLRenderer r = new SQLRenderer();
		 
		
		
		r.add("id_notisobmst", id_notisobmst);
		r.add("tarikh_serahan", r.unquote("sysdate"));
		r.add("status_serah", data.get("status_serah"));
		r.add("jenis_serah", data.get("jenis_serah"));
		r.add("catatan", "");
		r.add("nama_penghantar_notis", data.get("nama_penghantar_notis"));
		r.add("id_penghantarnotis", data.get("id_penghantarnotis"));
		r.add("id_masuk", id_masuk);
		r.add("tarikh_masuk", r.unquote("sysdate"));
		r.add("id_kemaskini", id_masuk);
		r.add("tarikh_kemaskini", r.unquote("sysdate"));
		
			if(data.get("jenis_serah").equals("3")){
				r.add("tarikh_emel", r.unquote("sysdate"));
				r.add("emel", data.get("alamatEmel"));
			}
		
		r.add("NAMA_PENGHANTAR_LAIN", data.get("NAMA_PENGHANTAR_LAIN"));
		sql = r.getSQLInsert("TBLPPKNOTISOBMST_TEMP");
		
		//System.out.println(":::::::::::::;sql TBLPPKNOTISOBMST ===="+sql);
		stmt.executeUpdate(sql);
		
		/*System.out.println("id_perbicaraan==="+id_perbicaraan);
		System.out.println("id_notisobmst==="+id_notisobmst);
		System.out.println("id_masuk==="+id_masuk);*/

		// create Tblppknotisperbicaraan
		SQLRenderer r1 = new SQLRenderer();
		r1.add("id_perbicaraan", id_perbicaraan);
		r1.add("id_notisobmst", id_notisobmst);
		r1.add("flag_jenis_notis", 0);
		r1.add("id_masuk", id_masuk);
		r1.add("tarikh_masuk", r1.unquote("sysdate"));
		sql = r1.getSQLInsert("Tblppknotisperbicaraan_temp");
		stmt.executeUpdate(sql);
		
		//create TBLPPKNOTISOBDTL
		SQLRenderer rDTL = new SQLRenderer();
		rDTL.add("id_ob", id_ob);
		rDTL.add("id_notisobmst", id_notisobmst);
		rDTL.add("nama_penerima",data.get("nama_ob") );
		rDTL.add("no_kp_baru", data.get("no_kp_baru"));
		rDTL.add("no_kp_lama", data.get("no_kp_lama"));
		rDTL.add("no_kp_lain", data.get("no_kp_lain"));
		rDTL.add("id_masuk", id_masuk);
		rDTL.add("tarikh_masuk", rDTL.unquote("sysdate"));

		//System.out.println("data.get(nama_obs) ==="+data.get("nama_obs") );
		sql = rDTL.getSQLInsert("TBLPPKNOTISOBDTL_TEMP");
		stmt.executeUpdate(sql);
		
		//email
		//akan shoot email selepas tindakn pegawai berjaya
		if(data.get("jenis_serah").equals("3")){
			//hantar emel
			//hantarNotisByEmel((String) data.get("id_fail"),id_ob);
			
			//ada attachment dlm emel
			//hantarNotisByEmel(session,(String) data.get("id_fail"),id_ob,application,request,response);
		}
		

	}// close try
	finally {
		if (db != null)
			db.close();
	}// close finally

}// close simpanSemakanK2

//hide attachment
public static void addHantarLaporanNotis(Hashtable data, String id_ob, String id_perbicaraan,HttpSession session,ServletContext application,
		HttpServletRequest request,HttpServletResponse response) throws Exception {

//public static void addHantarLaporanNotis(Hashtable data, String id_ob, String id_perbicaraan) throws Exception {

	Db db = null;

	String sql = "";

	try {
		db = new Db();
		Statement stmt = db.getStatement();

		long id_notisobmst = DB.getNextID("TBLPPKNOTISOBMST_SEQ");
		String id_masuk = (String) data.get("id_masuk");
		
		System.out.println("id_masuk==="+id_masuk);
		System.out.println("id_perbicaraan==="+id_perbicaraan);
		System.out.println("id_notisobmst==="+id_notisobmst);
		
		SQLRenderer r = new SQLRenderer();
		 
		
		
		r.add("id_notisobmst", id_notisobmst);
		r.add("tarikh_serahan", r.unquote("sysdate"));
		r.add("status_serah", data.get("status_serah"));
		r.add("jenis_serah", data.get("jenis_serah"));
		r.add("catatan", "");
		r.add("nama_penghantar_notis", data.get("nama_penghantar_notis"));
		r.add("id_penghantarnotis", data.get("id_penghantarnotis"));
		r.add("id_masuk", id_masuk);
		r.add("tarikh_masuk", r.unquote("sysdate"));
		r.add("id_kemaskini", id_masuk);
		r.add("tarikh_kemaskini", r.unquote("sysdate"));
		
			if(data.get("jenis_serah").equals("3")){
				r.add("tarikh_emel", r.unquote("sysdate"));
				r.add("emel", data.get("alamatEmel"));
			}
		
		r.add("NAMA_PENGHANTAR_LAIN", data.get("NAMA_PENGHANTAR_LAIN"));
		sql = r.getSQLInsert("TBLPPKNOTISOBMST");
		
		System.out.println(":::::::::::::;sql TBLPPKNOTISOBMST ===="+sql);
		stmt.executeUpdate(sql);
		
		/*System.out.println("id_perbicaraan==="+id_perbicaraan);
		System.out.println("id_notisobmst==="+id_notisobmst);
		System.out.println("id_masuk==="+id_masuk);*/

		// create Tblppknotisperbicaraan
		SQLRenderer r1 = new SQLRenderer();
		r1.add("id_perbicaraan", id_perbicaraan);
		r1.add("id_notisobmst", id_notisobmst);
		r1.add("flag_jenis_notis", 0);
		r1.add("id_masuk", id_masuk);
		r1.add("tarikh_masuk", r1.unquote("sysdate"));
		sql = r1.getSQLInsert("Tblppknotisperbicaraan");
		System.out.println("sql Tblppknotisperbicaraan==="+sql );
		stmt.executeUpdate(sql);
		
		//create TBLPPKNOTISOBDTL
				SQLRenderer rDTL = new SQLRenderer();
				rDTL.add("id_ob", id_ob);
				rDTL.add("id_notisobmst", id_notisobmst);
		/*		rDTL.add("nama_penerima",data.get("nama_obs") );
				rDTL.add("no_kp_baru", data.get("no_kp_baru"));
				rDTL.add("no_kp_lama", data.get("no_kp_lama"));
				rDTL.add("no_kp_lain", data.get("no_kp_lain"));*/
				rDTL.add("id_masuk", id_masuk);
				rDTL.add("tarikh_masuk", rDTL.unquote("sysdate"));

				System.out.println("data.get(nama_obs) ==="+data.get("nama_obs") );
				System.out.println("sql TBLPPKNOTISOBDTL==="+sql );
				sql = rDTL.getSQLInsert("TBLPPKNOTISOBDTL");
				stmt.executeUpdate(sql);
		
		//email
		if(data.get("jenis_serah").equals("3")){
			//hantar emel
			//hantarNotisByEmel((String) data.get("id_fail"),id_ob);
			
			//ada attachment dlm emel
			hantarNotisByEmel(session,(String) data.get("id_fail"),id_ob,application,request,response);
		}
		

	}// close try
	finally {
		if (db != null)
			db.close();
	}// close finally

}// close simpanSemakanK2


//hide attachment
public static void addHantarLaporanNotis_temp(Hashtable data, String id_ob, String id_perbicaraan,HttpSession session,ServletContext application,
		HttpServletRequest request,HttpServletResponse response) throws Exception {

//public static void addHantarLaporanNotis(Hashtable data, String id_ob, String id_perbicaraan) throws Exception {

	Db db = null;

	String sql = "";

	try {
		db = new Db();
		Statement stmt = db.getStatement();

		long id_notisobmst = DB.getNextID("TBLPPKNOTISOBMST_SEQ");
		String id_masuk = (String) data.get("id_masuk");
		
		System.out.println("id_masuk==="+id_masuk);
		System.out.println("id_perbicaraan==="+id_perbicaraan);
		System.out.println("id_notisobmst==="+id_notisobmst);
		
		SQLRenderer r = new SQLRenderer();
		 
		
		
		r.add("id_notisobmst", id_notisobmst);
		r.add("tarikh_serahan", r.unquote("sysdate"));
		r.add("status_serah", data.get("status_serah"));
		r.add("jenis_serah", data.get("jenis_serah"));
		r.add("catatan", "");
		r.add("nama_penghantar_notis", data.get("nama_penghantar_notis"));
		r.add("id_penghantarnotis", data.get("id_penghantarnotis"));
		r.add("id_masuk", id_masuk);
		r.add("tarikh_masuk", r.unquote("sysdate"));
		r.add("id_kemaskini", id_masuk);
		r.add("tarikh_kemaskini", r.unquote("sysdate"));
		
			if(data.get("jenis_serah").equals("3")){
				r.add("tarikh_emel", r.unquote("sysdate"));
				r.add("emel", data.get("alamatEmel"));
			}
		
		r.add("NAMA_PENGHANTAR_LAIN", data.get("NAMA_PENGHANTAR_LAIN"));
		sql = r.getSQLInsert("TBLPPKNOTISOBMST_TEMP");
		
		System.out.println(":::::::::::::;sql TBLPPKNOTISOBMST ===="+sql);
		stmt.executeUpdate(sql);
		
		/*System.out.println("id_perbicaraan==="+id_perbicaraan);
		System.out.println("id_notisobmst==="+id_notisobmst);
		System.out.println("id_masuk==="+id_masuk);*/

		// create Tblppknotisperbicaraan
		SQLRenderer r1 = new SQLRenderer();
		r1.add("id_perbicaraan", id_perbicaraan);
		r1.add("id_notisobmst", id_notisobmst);
		r1.add("flag_jenis_notis", 0);
		r1.add("id_masuk", id_masuk);
		r1.add("tarikh_masuk", r1.unquote("sysdate"));
		sql = r1.getSQLInsert("Tblppknotisperbicaraan_temp");
		System.out.println("sql Tblppknotisperbicaraan==="+sql );
		stmt.executeUpdate(sql);
		
		//create TBLPPKNOTISOBDTL
		SQLRenderer rDTL = new SQLRenderer();
		rDTL.add("id_ob", id_ob);
		rDTL.add("id_notisobmst", id_notisobmst);
		rDTL.add("nama_penerima",data.get("nama_ob") );
		rDTL.add("no_kp_baru", data.get("no_kp_baru"));
		rDTL.add("no_kp_lama", data.get("no_kp_lama"));
		rDTL.add("no_kp_lain", data.get("no_kp_lain"));
		rDTL.add("id_masuk", id_masuk);
		rDTL.add("tarikh_masuk", rDTL.unquote("sysdate"));

		System.out.println("data.get(nama_obs) ==="+data.get("nama_ob") );
		System.out.println("sql TBLPPKNOTISOBDTL==="+sql );
		sql = rDTL.getSQLInsert("TBLPPKNOTISOBDTL_TEMP");
		stmt.executeUpdate(sql);
		
		//email
		if(data.get("jenis_serah").equals("3")){
			//hantar emel
			//hantarNotisByEmel((String) data.get("id_fail"),id_ob);
			
			//ada attachment dlm emel
			//hantarNotisByEmel(session,(String) data.get("id_fail"),id_ob,application,request,response);
		}
		

	}// close try
	finally {
		if (db != null)
			db.close();
	}// close finally

}// close simpanSemakanK2


//aishahlatip
static Vector listidnotisobmst = new Vector();
public static Vector<Hashtable<String,String>> getID_NOTISOBMST(String id_ob, boolean statusHantarPNB) throws Exception {
		Db db = null;
		listidnotisobmst.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			

			sql = " SELECT ID_NOTISOBMST FROM TBLPPKNOTISOBDTL_TEMP WHERE ID_OB = '" + id_ob + "'";

			myLogger.info("listidnotisobmst :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_NOTISOBMST",rs.getString("ID_NOTISOBMST") == null ? "" : rs.getString("ID_NOTISOBMST"));	
				listidnotisobmst.addElement(h);
		
			}
			 return listidnotisobmst;
		} finally {
			if (db != null)
				db.close();
		}
	}


public static void deleteTBLPPKNOTISOBDTL(String ID_NOTISOBMST, String id_perbicaraan) throws Exception {

	Db db = null;
	String sql = "";

	try {

		db = new Db();
		Statement stmt = db.getStatement();
		
		/*if(statusHantarPNB){
			sql = "DELETE FROM TBLPPKNOTISOBDTL WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
		}else{*/
			//sql = "DELETE FROM TBLPPKNOTISOBDTL_TEMP WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
		//}
		
		sql = "DELETE from TBLPPKNOTISOBDTL_TEMP where id_notisobmst in (select id_notisobmst from tblppknotisperbicaraan_temp where id_perbicaraan = "+id_perbicaraan+")";

		stmt.executeUpdate(sql);
		
		System.out.println("deleteTBLPPKNOTISOBDTL "+sql);

	} finally {
		if (db != null)
			db.close();
	}

}// delete TBLPPKNOTISOBDTL

public static void deleteTBLPPKNOTISPERBICARAAN(String ID_NOTISOBMST, String id_perbicaraan) throws Exception {

	Db db = null;
	String sql = "";

	try {

		db = new Db();
		Statement stmt = db.getStatement();

		/*if(statusHantarPNB){
			sql = "DELETE FROM TBLPPKNOTISPERBICARAAN WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
		}else{*/
			//sql = "DELETE FROM TBLPPKNOTISPERBICARAAN_TEMP WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
		//}
			
			sql = "DELETE from TBLPPKNOTISPERBICARAAN_TEMP where id_perbicaraan = "+id_perbicaraan+"";

		stmt.executeUpdate(sql);
		
		System.out.println("deleteTBLPPKNOTISPERBICARAAN "+sql);

	} finally {
		if (db != null)
			db.close();
	}

}// delete TBLPPKNOTISPERBICARAAN

public static void deleteTBLPPKNOTISOBMST(String ID_NOTISOBMST, String id_perbicaraan) throws Exception {

	Db db = null;
	String sql = "";

	try {

		db = new Db();
		Statement stmt = db.getStatement();

		/*if(statusHantarPNB){
			sql = "DELETE FROM TBLPPKNOTISOBMST WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
		}else{*/
			//sql = "DELETE FROM TBLPPKNOTISOBMST_TEMP WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
		//}
		
		sql = "delete from TBLPPKNOTISOBMST_TEMP where id_notisobmst in (select id_notisobmst from tblppknotisperbicaraan_temp where id_perbicaraan = "+id_perbicaraan+")";

		stmt.executeUpdate(sql);
		
		System.out.println("deleteTBLPPKNOTISOBMST "+sql);

	} finally {
		if (db != null)
			db.close();
	}

}// delete TBLPPKNOTISOBMST

//add utk delete real notis perbicaraan

public static void deleteTBLPPKNOTISOBDTL_ORI(String ID_NOTISOBMST, String id_perbicaraan) throws Exception {

	Db db = null;
	String sql = "";

	try {

		db = new Db();
		Statement stmt = db.getStatement();
		
		/*if(statusHantarPNB){
			sql = "DELETE FROM TBLPPKNOTISOBDTL WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
		}else{*/
			//sql = "DELETE FROM TBLPPKNOTISOBDTL_TEMP WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
		//}
		
		sql = "DELETE from TBLPPKNOTISOBDTL where id_notisobmst in (select id_notisobmst from tblppknotisperbicaraan where id_perbicaraan = "+id_perbicaraan+")";

		stmt.executeUpdate(sql);
		
		System.out.println("deleteTBLPPKNOTISOBDTL "+sql);

	} finally {
		if (db != null)
			db.close();
	}

}// delete TBLPPKNOTISOBDTL

public static void deleteTBLPPKNOTISPERBICARAAN_ORI(String ID_NOTISOBMST, String id_perbicaraan) throws Exception {

	Db db = null;
	String sql = "";

	try {

		db = new Db();
		Statement stmt = db.getStatement();

		/*if(statusHantarPNB){
			sql = "DELETE FROM TBLPPKNOTISPERBICARAAN WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
		}else{*/
			//sql = "DELETE FROM TBLPPKNOTISPERBICARAAN_TEMP WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
		//}
			
			sql = "DELETE from TBLPPKNOTISPERBICARAAN where id_perbicaraan = "+id_perbicaraan+"";

		stmt.executeUpdate(sql);
		
		System.out.println("deleteTBLPPKNOTISPERBICARAAN "+sql);

	} finally {
		if (db != null)
			db.close();
	}

}// delete TBLPPKNOTISPERBICARAAN

public static void deleteTBLPPKNOTISOBMST_ORI(String ID_NOTISOBMST, String id_perbicaraan) throws Exception {

	Db db = null;
	String sql = "";

	try {

		db = new Db();
		Statement stmt = db.getStatement();

		/*if(statusHantarPNB){
			sql = "DELETE FROM TBLPPKNOTISOBMST WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
		}else{*/
			//sql = "DELETE FROM TBLPPKNOTISOBMST_TEMP WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
		//}
		
		sql = "DELETE FROM TBLPPKNOTISOBMST WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";

		stmt.executeUpdate(sql);
		
		System.out.println("deleteTBLPPKNOTISOBMST "+sql);

	} finally {
		if (db != null)
			db.close();
	}

}// delete TBLPPKNOTISOBMST

//hide attachment
public static void hantarNotisByEmel(HttpSession session,String idFail, String id_OB, ServletContext application,HttpServletRequest request,HttpServletResponse response) throws Exception {
//public static void hantarNotisByEmel(String idFail, String id_OB) throws Exception {
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	myLogger.info("MASUK FUNCTION EMEL--------------------"+id_OB);
	
	Db db = null;
	try{
	db = new Db();
	Statement stmt = db.getStatement();
	Statement stmtNotis = db.getStatement();
	ResultSet rs = null;
	ResultSet rsNotis = null;
	String sql = "";
	String status = "";
	String seksyen = "";
	
	
	EmailProperty pro = EmailProperty.getInstance();
	EmailSender email = EmailSender.getInstance();
	email.FROM = pro.getFrom();
	email.MULTIPLE_RECIEPIENT = new String[1];
	String subject = "";
	
	String content = "";
	
	
	StringBuffer bff = new StringBuffer();
	String tujuan = "";
	
		
		tujuan = " Pemakluman Notis Perbicaraan yang memerlukan tindakan Tuan/Puan. Sila semak permohonan ini di MyeTaPP.";

		String ID_PERBICARAAN="", ID_FAIL="", NO_FAIL="", NAMA_PEMOHON = "", NO_KP_BARU = "", NO_KP_BARU_LAMA = "", ALAMAT_1 = "", ALAMAT_2 = "",ALAMAT_3 = "", POSKOD = "",NAMA_BANDAR_PEMOHON = "",NAMA_NEGERI_PEMOHON = "",NAMA_SIMATI = "",ALAMAT_SIMATI1 = "",
				ALAMAT_SIMATI2 = "",ALAMAT_SIMATI3 = "",POSKOD_SIMATI = "",NAMA_BANDAR_SIMATI = "",NAMA_NEGERI_SIMATI = "",
				BIL_BICARA = "",TARIKH_BICARA = "",TEMPAT_BICARA = "",ALAMAT_BICARA1 = "",ALAMAT_BICARA2 = "",ALAMAT_BICARA3 = "",NAMA_BANDAR_BICARA = "",NAMA_NEGERI_BICARA = "",
				PEG_PENGENDALI  = "",MASA_BICARA="", JENIS_MASA="", ID_SIMATI = "", ID_PERMOHONANSIMATI="";
		
		String jenisMasa = "";
		
		sql = "SELECT F.NO_FAIL, PE.NAMA_PEMOHON, PE.NO_KP_BARU, PE.NO_KP_BARU_LAMA, PE.ALAMAT_1, PE.ALAMAT_2,PE.ALAMAT_3, PE.POSKOD,Initcap(BANPEMOHON.KETERANGAN) AS NAMA_BANDAR_PEMOHON," +
				"Initcap(NEGPEMOHON.NAMA_NEGERI) AS NAMA_NEGERI_PEMOHON,SIMATI.NAMA_SIMATI,SIMATI.ALAMAT_1 AS ALAMAT_SIMATI1,SIMATI.ALAMAT_2 AS ALAMAT_SIMATI2,SIMATI.ALAMAT_3 AS ALAMAT_SIMATI3," +
				"SIMATI.POSKOD AS POSKOD_SIMATI,Initcap(BANSIMATI.KETERANGAN) AS NAMA_BANDAR_SIMATI,Initcap(NEGSIMATI.NAMA_NEGERI) AS NAMA_NEGERI_SIMATI, BIC.BIL_BICARA,TO_CHAR(BIC.TARIKH_BICARA,'DD/MM/YYYY') AS TARIKH_BICARA," +
				"Initcap(BIC.TEMPAT_BICARA)AS TEMPAT_BICARA, Initcap(BIC.ALAMAT_BICARA1) AS ALAMAT_BICARA1,Initcap(BIC.ALAMAT_BICARA2) AS ALAMAT_BICARA2," +
				"Initcap(BIC.ALAMAT_BICARA3) AS ALAMAT_BICARA3,Initcap(BIC.BANDAR) AS NAMA_BANDAR_BICARA,Initcap(NEGBICARA.NAMA_NEGERI) AS NAMA_NEGERI_BICARA," +
				"Initcap(BIC.PEG_PENGENDALI) AS PEG_PENGENDALI ,BIC.MASA_BICARA,BIC.JENIS_MASA_BICARA,BIC.ID_PERBICARAAN,F.ID_FAIL,P.SEKSYEN ,SIMATI.ID_SIMATI, SI.ID_PERMOHONANSIMATI " +
				" FROM TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI SI,TBLPPKSIMATI SIMATI,TBLPPKPEMOHON PE, " +
				"TBLPFDFAIL F,TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERBICARAAN BIC,TBLRUJBANDAR BANPEMOHON,TBLRUJBANDAR BANSIMATI,TBLRUJNEGERI NEGPEMOHON," +
				" TBLRUJNEGERI NEGBICARA,TBLRUJNEGERI NEGSIMATI WHERE P.ID_FAIL = F.ID_FAIL AND PE.ID_BANDAR = BANPEMOHON.ID_BANDAR(+) AND SIMATI.ID_BANDAR = BANSIMATI.ID_BANDAR(+) " +
				"AND BIC.ID_NEGERIBICARA = NEGBICARA.ID_NEGERI(+) AND SIMATI.ID_NEGERI = NEGSIMATI.ID_NEGERI(+) AND PE.ID_NEGERI = NEGPEMOHON.ID_NEGERI(+) " +
				"AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) AND KP.ID_KEPUTUSANPERMOHONAN = BIC.ID_KEPUTUSANPERMOHONAN(+) AND P.ID_PEMOHON = PE.ID_PEMOHON(+) AND P.ID_PERMOHONAN = SI.ID_PERMOHONAN(+) " +
				"AND SI.ID_SIMATI = SIMATI.ID_SIMATI(+) AND F.ID_FAIL =" + idFail + " AND BIC.BIL_BICARA = (SELECT MAX(BIC.BIL_BICARA) AS BIL_BICARA FROM TBLPPKPERMOHONAN P " +
				",TBLPFDFAIL F,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN BIC, TBLRUJNEGERI NEG WHERE P.ID_FAIL = F.ID_FAIL AND BIC.ID_NEGERIBICARA = NEG.ID_NEGERI(+) " +
				"AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) AND KP.ID_KEPUTUSANPERMOHONAN = BIC.ID_KEPUTUSANPERMOHONAN(+) " +
				"AND F.ID_FAIL =" + idFail + ")";

		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			NO_FAIL= rs.getString(1) == null ? "" : rs.getString(1);
			NAMA_PEMOHON = rs.getString(2) == null ? "" : rs.getString(2);
			NO_KP_BARU = rs.getString(3) == null ? "" : rs.getString(3);
			NO_KP_BARU_LAMA = rs.getString(4) == null ? "" : rs.getString(4);
			ALAMAT_1 = rs.getString(5) == null ? "" : rs.getString(5);
			ALAMAT_2 = rs.getString(6) == null ? "" : rs.getString(6);
			ALAMAT_3 = rs.getString(7) == null ? "" : rs.getString(7); 
			POSKOD = rs.getString(8) == null ? "" : rs.getString(8);
			NAMA_BANDAR_PEMOHON = rs.getString(9) == null ? "" : rs.getString(9);
			NAMA_NEGERI_PEMOHON = rs.getString(10) == null ? "" : rs.getString(10);
			NAMA_SIMATI = rs.getString(11) == null ? "" : rs.getString(11);
			ALAMAT_SIMATI1 = rs.getString(12) == null ? "" : rs.getString(12);
			ALAMAT_SIMATI2 = rs.getString(13) == null ? "" : rs.getString(13);
			ALAMAT_SIMATI3 = rs.getString(14) == null ? "" : rs.getString(14);
			POSKOD_SIMATI = rs.getString(15) == null ? "" : rs.getString(15);
			NAMA_BANDAR_SIMATI = rs.getString(16) == null ? "" : rs.getString(16);
			NAMA_NEGERI_SIMATI = rs.getString(17) == null ? "" : rs.getString(17);
			BIL_BICARA = rs.getString(18) == null ? "" : rs.getString(18);
			TARIKH_BICARA = rs.getString(19) == null ? "" : rs.getString(19);
			TEMPAT_BICARA = rs.getString(20) == null ? "" : rs.getString(20);
			ALAMAT_BICARA1 = rs.getString(21) == null ? "" : rs.getString(21);
			ALAMAT_BICARA2 = rs.getString(22) == null ? "" : rs.getString(22);
			ALAMAT_BICARA3 = rs.getString(23) == null ? "" : rs.getString(23);
			NAMA_BANDAR_BICARA = rs.getString(24) == null ? "" : rs.getString(24);
			NAMA_NEGERI_BICARA = rs.getString(25) == null ? "" : rs.getString(25);
			PEG_PENGENDALI  = rs.getString(26) == null ? "" : rs.getString(26);
			MASA_BICARA= rs.getString(27) == null ? "" : rs.getString(27);
			JENIS_MASA= rs.getString(28) == null ? "" : rs.getString(28);
			
			ID_PERBICARAAN = rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN");
			ID_FAIL = rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL");
			
			jenisMasa = "";
			if (JENIS_MASA != null && !"".equals(JENIS_MASA)) {
				if("1".equals(JENIS_MASA)){
					jenisMasa = "pagi";		
				}else if("2".equals(JENIS_MASA)){
					jenisMasa = "tengahari";
				}else if("3".equals(JENIS_MASA)){
					jenisMasa = "petang";
				}
			}
			
			
			seksyen = rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN");
			ID_SIMATI = rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI");
			ID_PERMOHONANSIMATI = rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI");
		}
		
		
		if(seksyen.equals("8")){
			subject = "NOTIS PETISYEN DAN PENDENGARAN (Seksyen 9)";
		}else{
			subject = "NOTIS PENDENGARAN PERMOHONAN BERIKUTNYA (Seksyen 17)";

		}
		
		// header email
		bff.append("Salam Sejahtera Tuan/Puan,");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Untuk makluman Permohonan Pembahagian Pusaka Kecil ");
		bff.append("telah dibuat oleh ");
		bff.append(NAMA_PEMOHON);
		bff.append(" , No. KP:");
		bff.append(NO_KP_BARU);
		
		if (NO_KP_BARU_LAMA != null && !"".equals(NO_KP_BARU_LAMA)) {
			bff.append("("+NO_KP_BARU_LAMA+")");
		}
		
		bff.append(" yang beralamat di ");
		bff.append(ALAMAT_1+","+ ALAMAT_2+" "+ ALAMAT_3+" "+ POSKOD+","+ NAMA_BANDAR_PEMOHON+","+NAMA_NEGERI_PEMOHON);
		bff.append(" bagi pembahagian harta pusaka dan barang kepunyaan ");
		bff.append(NAMA_SIMATI);
		bff.append(" si mati, ");				
		bff.append("akan didengar mengikut ketetapan berikut: ");
		bff.append("<html><table>" +				
				"<tr><td>No.Fail</td><td>:</td><td>"+NO_FAIL+"</td></tr>" +
				"<tr><td>Nama Si Mati</td><td>:</td><td>"+NAMA_SIMATI+"</td></tr>" +
				"<tr><td>Bil Bicara</td><td>:</td><td>"+BIL_BICARA+"</td></tr>" +	
				"<tr><td>Tarikh Bicara</td><td>:</td><td>"+TARIKH_BICARA+"</td></tr>" +		
				"<tr><td>Masa Bicara</td><td>:</td><td>"+MASA_BICARA+" "+jenisMasa+"</td></tr>" +		
				"<tr><td>Tempat Bicara</td><td>:</td><td>"+TEMPAT_BICARA+"</td></tr>" +		
				"<tr><td>Alamat Tempat Bicara</td><td>:</td><td>"+ALAMAT_BICARA1+"</td></tr>" +		
				"<tr><td>&nbsp;</td><td>:</td><td>"+ALAMAT_BICARA2+"</td></tr>" +		
				"<tr><td>&nbsp;</td><td>:</td><td>"+ALAMAT_BICARA3+"</td></tr>" +		
				"<tr><td>Bandar</td><td>:</td><td>"+NAMA_BANDAR_BICARA+"</td></tr>" +		
				"<tr><td>Negeri</td><td>:</td><td>"+NAMA_NEGERI_BICARA+"</td></tr>" +	
				"<tr><td colspan=3>&nbsp;</td></tr>" +
				"<tr><td colspan=3>Bersama-sama ini dilampirkan notis perbicaraan untuk makluman Tuan/Puan</td></tr>" +
				"<tr><td colspan=3>&nbsp;</td></tr>" +
				"<tr><td colspan=3>&nbsp;</td></tr>" +
				"<tr><td colspan=3><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em></td></tr>" +
				"</table></html>" );	
		
		
		/*bff.append("<br/>");
		bff.append("No Fail : ");
		bff.append(NO_FAIL);
		bff.append(TEMPAT_BICARA+","+ ALAMAT_BICARA1+" "+ALAMAT_BICARA2+" "+ALAMAT_BICARA3+" "+NAMA_BANDAR_BICARA+" "+NAMA_NEGERI_BICARA);
		bff.append(" pada ");*/
		
		

		
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("<br/>");
		
		bff.append("---------------------------------------------------------------------------------------");
		content = bff.toString();
		
		GetAttachment report = new GetAttachment(); //hide email attachment
    	final Map<String, Object> myMap = new HashMap<String,Object>();
    	//hide email attachment
    	myLogger.info("ID_FAIL : "+ID_FAIL+" ID_PERBICARAAN : "+ID_PERBICARAAN);
    	String path ="";
    	String folderName = "ppk";
    	String fileName ="";
    	String namaAttached = "";
    	
    	if(seksyen.equals("8")){
    		fileName = "BorangD_email";
    		namaAttached =  "Borang D";
    	}else{
    		fileName = "BorangS_email";
    		namaAttached =  "Borang S";
    	}
    	
		ResourceBundle rb = ResourceBundle.getBundle("file");
		String appContext = rb.getString("context_name");
		String pathAttach = application.getRealPath(File.separator + "reports" + File.separator).replace(appContext + File.separator, "");
    	
    	myMap.put("idfail", ID_FAIL);
    	myMap.put("flagVersion", "no");
    	myMap.put("idperbicaraan", ID_PERBICARAAN);
    	myMap.put("ReportDir", pathAttach);
    	myMap.put("signedData", "");
    	myMap.put("os", "1");
    	myMap.put("jenisSerahan", "3");
    	myMap.put("idob", id_OB);
    	
    	if(seksyen.equals("17")){
	    	myMap.put("idsimati", ID_SIMATI);
	    	
	    	logic.getPemohonTerdahulu(ID_FAIL);
			Vector pemohon = new Vector(); 
			pemohon = logic.getBeanPemohonTerdahulu();	
			Hashtable h = new Hashtable();
			if(pemohon.size() != 0){
				
				h = (Hashtable)pemohon.get(0);
				myMap.put("namaPemohon",h.get("namaPemohon"));
				myMap.put("alamatPemohon",h.get("alamatPemohon"));
				myMap.put("kpPemohon", h.get("kpPemohon"));
				myMap.put("sebab", "");
				
			}
    	}
    	
    	
    	//parameter utk panggil report, boleh multiple    	
    	//byte[] bytes1 = report.getReportBytes("ppk","KulitFail",request, response, application, myMap);
    	   	
    	byte[] bytes1 = report.getReportBytes("ppk",fileName,request, response, application, myMap);

    	myLogger.info("bytes1 ::::: "+bytes1);
    ////open razman add new feature : attachment in bytes
		email.ATTACHMENT_BYTES = new String[1];
		email.ATTACHMENT_BYTES[0] = new String(bytes1, "ISO-8859-1");;
		email.ATTACHMENT_BYTES_NAME = new String[1]; //kena sama dengan jumlah attachment
		email.ATTACHMENT_BYTES_NAME[0] = namaAttached;//letak nama file bersesuaian
		
		//close razman add new feature : attachment in bytes		
	
		List listEmailAdd = getOBEmailAddressNotis(id_OB,ID_PERMOHONANSIMATI);
		
		if( listEmailAdd.size()> 0){
				email.MULTIPLE_RECIEPIENT = new String[listEmailAdd.size()];
				for(int i = 0; i < listEmailAdd.size();i++)
				{
					Map m = (Map) listEmailAdd.get(i);
					String EMEL = (String) m.get("EMEL");
					myLogger.info("::::::::::::::::::::::::::::: EMAIL ::::::::::::::::::::::::::: "+EMEL);
					//int COUNT_EMEL = (int)maklumatTindakan.get("COUNT_EMEL");
					email.MULTIPLE_RECIEPIENT[i] = EMEL;						
				}
			
			email.SUBJECT = subject;
			email.MESSAGE = content;		
			email.sendEmail();		
		}else{
			myLogger.info("::::::::::::::::::::::::::::: EMAIL NULL ::::::::::::::::::::::::::: ");
		}
	
	} catch (DbException e) {
		myLogger.error(e);
		//return "fail";
	} catch (Exception er) {
		myLogger.error(er);
		throw er;
	} finally {
		if (db != null)
			db.close();
	}
	
 }


//aishahlatip
public static List getOBEmailAddress(String id_ob)throws Exception {
	Db db = null;
	ResultSet rs = null;
	Statement stmt = null;
	List listEmelPelulus = null;
	String sql = "";
	try {
		db = new Db();
		stmt = db.getStatement();
		
		sql = " SELECT E.EMEL FROM TBLPPKOBPERMOHONAN E WHERE E.ID_OB IN (" + id_ob + ")";
		
		myLogger.info(" SQL : emel ob :"+ sql);			
		rs = stmt.executeQuery(sql);
		listEmelPelulus = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));	
			listEmelPelulus.add(h);
		}

	} finally {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (db != null)
			db.close();
	}
	return listEmelPelulus;
}

//aishahlatip
public static List getOBEmailAddressNotis(String id_ob, String id_permohonansimati)throws Exception {
	Db db = null;
	ResultSet rs = null;
	Statement stmt = null;
	List listEmelPelulus = null;
	String sql = "";
	try {
		db = new Db();
		stmt = db.getStatement();
		
		sql = " SELECT E.EMEL FROM TBLPPKOBPERMOHONAN E WHERE E.ID_OB IN (" + id_ob + ") AND E.ID_PERMOHONANSIMATI = "+id_permohonansimati;
		
		myLogger.info(" SQL : emel ob :"+ sql);			
		rs = stmt.executeQuery(sql);
		listEmelPelulus = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));	
			listEmelPelulus.add(h);
		}

	} finally {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (db != null)
			db.close();
	}
	return listEmelPelulus;
}

public String getSignedData(String idPerbicaraan) {
	String signedData = "";
	Db db = null;
	ResultSet rs = null;
	Statement stmt = null;
	try {
		db = new Db();
		stmt = db.getStatement();
		
		String sql = " SELECT SIGNED_TEXT FROM TBLPPKPERBICARAAN WHERE ID_PERBICARAAN = '" + idPerbicaraan + "'";
		rs = stmt.executeQuery(sql);
		System.out.println("data dah signed "+sql);
		if (rs.next()) {
			signedData = rs.getString("SIGNED_TEXT");
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return signedData;
}


//aishah add 06062018

//get semua id_notisobmst
//lembu

static Vector listobmst = new Vector();
public static Vector<Hashtable<String,String>> getID_NOTISPERBICAAAN(String id_perbicaraan) throws Exception {
		Db db = null;
		listobmst.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			

			sql = " SELECT ID_NOTISOBMST FROM TBLPPKNOTISPERBICARAAN WHERE ID_PERBICARAAN = '" + id_perbicaraan + "'";

			//myLogger.info("getID_NOTISPERBICAAAN:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_NOTISOBMST",rs.getString("ID_NOTISOBMST") == null ? "" : rs.getString("ID_NOTISOBMST"));	
				listobmst.addElement(h);
		
			}
			 return listobmst;
		} finally {
			if (db != null)
				db.close();
		}
	}

static Vector listobmsttemp = new Vector();
public static Vector<Hashtable<String,String>> getID_NOTISPERBICAAAN_TEMP(String id_perbicaraan, String serahan) throws Exception {
		Db db = null;
		listobmsttemp.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			

			sql = " SELECT A.ID_NOTISOBMST , B.JENIS_SERAH, C.ID_OB FROM TBLPPKNOTISPERBICARAAN_TEMP A, TBLPPKNOTISOBMST_TEMP B, TBLPPKNOTISOBDTL_TEMP C" +
					" WHERE A.ID_NOTISOBMST = B.ID_NOTISOBMST " +
					" AND B.ID_NOTISOBMST = C.ID_NOTISOBMST ";
					if(serahan.equals("specific")){
						sql = sql + " AND B.JENIS_SERAH in (3,5) " ;
					}
					if(serahan.equals("byhand")){
						sql = sql + " AND B.JENIS_SERAH in (1) " ;
					}
					
		   sql = sql + " AND A.ID_PERBICARAAN = '" + id_perbicaraan + "' order by A.ID_NOTISOBMST asc ";

			ResultSet rs = stmt.executeQuery(sql);
			
			myLogger.info("get sql TBLPPKNOTISOBMST_TEMP === " +sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_NOTISOBMST",rs.getString("ID_NOTISOBMST") == null ? "" : rs.getString("ID_NOTISOBMST"));	
				h.put("JENIS_SERAH",rs.getString("JENIS_SERAH") == null ? "" : rs.getString("JENIS_SERAH"));	
				h.put("ID_OB",rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));	
				listobmsttemp.addElement(h);
				
				
				myLogger.info("ID_NOTISOBMST====" +rs.getString("ID_NOTISOBMST"));
		
			}
			 return listobmsttemp;
		} finally {
			if (db != null)
				db.close();
		}
	}


static Vector listobmstori = new Vector();
public static Vector<Hashtable<String,String>> getID_NOTISPERBICAAAN_ORI(String id_perbicaraan) throws Exception {
		Db db = null;
		listobmstori.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			

			sql = " SELECT ID_NOTISOBMST FROM TBLPPKNOTISPERBICARAAN WHERE ID_PERBICARAAN = '" + id_perbicaraan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_NOTISOBMST",rs.getString("ID_NOTISOBMST") == null ? "" : rs.getString("ID_NOTISOBMST"));	
				listobmstori.addElement(h);
		
			}
			 return listobmstori;
		} finally {
			if (db != null)
				db.close();
		}
	}

//lembu
// insert data from Tblppknotisperbicaraan_temp, tblppknotisobmst_temp, tblppknotisobdtl_temp into Tblppknotisperbicaraan, tblppknotisobmst, tblppknotisobdtl	
public static void insertPenghantaranNotisReal(String id_perbicaraan,String idfail,HttpSession session,ServletContext application,HttpServletRequest request,HttpServletResponse response) throws Exception {

	Db db = null;
	
	String sql = "";
	String sql3 = "";
	String sql4 = "";
	String id_notisobmst = "";
	String id_notisobmsttemp = "";
	String id_notisobmstoritemp = "";
	String JENIS_SERAH = "";
	String ID_OB = "";
	
	Hashtable h;
	try {
		db = new Db();

		Statement stmt = db.getStatement();
		
		
		Vector<Hashtable<String,String>> listnotisobmstAlltemp = getID_NOTISPERBICAAAN_TEMP(id_perbicaraan,"all");
		
		Vector<Hashtable<String,String>> listnotisobmstori = getID_NOTISPERBICAAAN_ORI(id_perbicaraan);
		
		myLogger.info("listnotisobmstAlltemp===="+listnotisobmstAlltemp.size());
		
		if(listnotisobmstAlltemp.size()>0){
			for(int i=0; i < listnotisobmstori.size(); i++)
			{
					Hashtable<String,String> h1 = (Hashtable<String,String>) listnotisobmstori.get(i);
					id_notisobmstoritemp = h1.get("ID_NOTISOBMST").toString();		
					
					//delete table real
					deleteTBLPPKNOTISOBDTL_ORI("",id_perbicaraan);	//delete TBLPPKNOTISOBDTL	
					deleteTBLPPKNOTISPERBICARAAN_ORI("",id_perbicaraan);	//delete TBLPPKNOTISPERBICARAAN	
					deleteTBLPPKNOTISOBMST_ORI(id_notisobmstoritemp,id_perbicaraan);	//delete TBLPPKNOTISOBMST	
				
			
			}
		}
		
		Vector<Hashtable<String,String>> listnotisobmsttemp = getID_NOTISPERBICAAAN_TEMP(id_perbicaraan,"specific");
		//clear rekod HA balik
		if(listnotisobmstAlltemp.size()>0){
			for(int i=0; i < listnotisobmsttemp.size(); i++)
			{
					Hashtable<String,String> h1 = (Hashtable<String,String>) listnotisobmsttemp.get(i);
					id_notisobmsttemp = h1.get("ID_NOTISOBMST").toString();			
					JENIS_SERAH =  h1.get("JENIS_SERAH").toString();	
					ID_OB =  h1.get("ID_OB").toString();	
				    //copy from temp then insert data into Tblppknotisobmst, 	Tblppknotisobdtl, Tblppknotisperbicaraan
					Tblppknotisobmst_insert( id_perbicaraan, id_notisobmsttemp,"emailpnmb");
					
					if(JENIS_SERAH.equals("3")){
						hantarNotisByEmel(session,idfail,ID_OB,application,request,response);
					}
				
			}
		}else{//hanya serahan tangan shj ambil1 row shj
			
			
			Vector<Hashtable<String,String>> listnotisobmsttempSerahanTGN = getID_NOTISPERBICAAAN_TEMP(id_perbicaraan,"byhand");
			if(listnotisobmsttempSerahanTGN.size()>0)
			{
				myLogger.info("listnotisobmsttempSerahanTGN===="+listnotisobmsttempSerahanTGN.size());
				for(int i=0; i < 1; i++)
				{
					myLogger.info("============masuk====");
					Hashtable<String,String> h1 = (Hashtable<String,String>) listnotisobmsttempSerahanTGN.get(i);
					id_notisobmsttemp = h1.get("ID_NOTISOBMST").toString();			
					JENIS_SERAH =  h1.get("JENIS_SERAH").toString();	
					ID_OB =  h1.get("ID_OB").toString();	
					Tblppknotisobmst_insert( id_perbicaraan, id_notisobmsttemp,"byhand");
					
				}
			}
			
		}

	}// close try
	finally {
		if (db != null)
			db.close();
		
		
	}// close finally

}// close 

public static void deleteNOTISPERBICARAAN(String ID_PERBICARAAN) throws Exception {

	Db db = null;
	String sql = "";

	try {

		db = new Db();
		Statement stmt = db.getStatement();
		
		sql = "DELETE FROM TBLPPKNOTISPERBICARAAN WHERE ID_PERBICARAAN = '"+ ID_PERBICARAAN + "'";
		stmt.executeUpdate(sql);
		

	} finally {
		if (db != null)
			db.close();
	}

}


public static void Tblppknotisobmst_insert(String id_perbicaraan,String id_notisobmsttemp, String jenisSerah) throws Exception {
	Db db = null;
	//String userId = session.getAttribute("_ekptg_user_id").toString();
	String sql = "";
	
	String sql3 = "";
	String sql4 = "";
	String id_notisobmst = "";

	try {
		
		db = new Db();
		Statement stmt = db.getStatement();
		Statement stmtA = db.getStatement();
		Statement stmtS = db.getStatement();
		Statement stmtB = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		
		sql = "INSERT INTO TBLPPKNOTISOBMST (ID_NOTISOBMST,  BIL,  TARIKH_SERAHAN,  STATUS_SERAH   , " +
				 " JENIS_SERAH ,  STATUS_AKUAN_SUMPAH,  CATATAN,  NAMA_PENGHANTAR_NOTIS ,  NO_SURAT_DAFTAR     , ID_MASUK   , " +
				 " TARIKH_MASUK  ,  ID_KEMASKINI ,  TARIKH_KEMASKINI,  ID_DB ,  ID_NOTISOBMSTLAMA , ID_PENGHANTARNOTIS  ,TARIKH_EMEL  , " +
				 " EMEL , NAMA_PENGHANTAR_LAIN   )" +
				 " SELECT ID_NOTISOBMST  ,BIL       , TARIKH_SERAHAN    ,  STATUS_SERAH   , JENIS_SERAH , STATUS_AKUAN_SUMPAH   , " +
				 " CATATAN      , NAMA_PENGHANTAR_NOTIS , NO_SURAT_DAFTAR  , ID_MASUK   , TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI , ID_DB                  , " +
				 " ID_NOTISOBMSTLAMA , ID_PENGHANTARNOTIS     , TARIKH_EMEL , EMEL ,  NAMA_PENGHANTAR_LAIN " +
				" FROM TBLPPKNOTISOBMST_TEMP" +
				" WHERE ID_NOTISOBMST = "+id_notisobmsttemp;
		System.out.println("Tblppknotisobmst "+sql);
		stmtA.executeUpdate(sql);
		System.out.println("Tblppknotisobmst "+sql);
		
		
		// insert data into TBLPPKNOTISPERBICARAAN	
		
		sql3 = "INSERT INTO TBLPPKNOTISPERBICARAAN (ID_NOTISPERBICARAAN  ," +
				" ID_PERBICARAAN  , ID_NOTISOBMST , ID_MASUK , TARIKH_MASUK , ID_KEMASKINI , TARIKH_KEMASKINI,  ID_DB , FLAG_JENIS_NOTIS)" +
				" SELECT ID_NOTISPERBICARAAN  , ID_PERBICARAAN  , ID_NOTISOBMST , ID_MASUK , TARIKH_MASUK , ID_KEMASKINI , TARIKH_KEMASKINI , ID_DB , FLAG_JENIS_NOTIS" +
				" FROM TBLPPKNOTISPERBICARAAN_TEMP" +
				" WHERE ID_NOTISOBMST = "+id_notisobmsttemp;
		System.out.println("TBLPPKNOTISPERBICARAAN 1 "+sql3);
		stmtS.executeUpdate(sql3);
		System.out.println("TBLPPKNOTISPERBICARAAN 2 "+sql3);
	
		
		// insert data into TBLPPKNOTISOBDTL	
		
		if(jenisSerah.equals("byhand"))
		{
		sql4 = "INSERT INTO TBLPPKNOTISOBDTL (ID_NOTISOBDTL ,ID_NOTISOBMST ,ID_OB ,NAMA_PENERIMA , NO_KP_BARU  ,ID_MASUK ,TARIKH_MASUK ," +
				"ID_KEMASKINI, TARIKH_KEMASKINI , ID_DB, NO_KP_LAMA, NO_KP_LAIN)" +
				" SELECT ID_NOTISOBDTL ,ID_NOTISOBMST ,ID_OB ,NAMA_PENERIMA , NO_KP_BARU  ,ID_MASUK ,TARIKH_MASUK ," +
				" ID_KEMASKINI, TARIKH_KEMASKINI , ID_DB, NO_KP_LAMA, NO_KP_LAIN" +
				" FROM TBLPPKNOTISOBDTL_TEMP" +
				" WHERE ID_NOTISOBMST = "+id_notisobmsttemp;
			System.out.println("TBLPPKNOTISOBDTL 1 "+sql4);
			stmtB.executeUpdate(sql4);
			System.out.println("TBLPPKNOTISOBDTL 1 "+sql4);
		}
	
		if(jenisSerah.equals("byhand"))
		{
			stmtB.close();
		}
		stmtS.close();
		stmtA.close();
		stmt.close();
		
	} finally {
		
		
		
		if (db != null)
			db.close();
	}
}


}// close class

