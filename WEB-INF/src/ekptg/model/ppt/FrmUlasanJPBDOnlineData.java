package ekptg.model.ppt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.view.ppt.FrmUlasanJPBDOnline;

public class FrmUlasanJPBDOnlineData {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLogger = Logger.getLogger(FrmUlasanJPBDOnlineData.class);
	
	@SuppressWarnings("unchecked")
	public Vector searchPermohonan(String NO_FAIL, String NO_PERMOHONAN, String NO_LOT, String NO_SYIT) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				SQL_SEARCH = " AND UPPER(FAIL.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NO_PERMOHONAN)) {
				SQL_SEARCH = " AND UPPER(M.NO_PERMOHONAN) LIKE '%" + NO_PERMOHONAN.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NO_LOT)) {
				SQL_SEARCH = " AND (UPPER(HM.NO_LOT) LIKE '%" + NO_LOT.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NO_SYIT)) {
				SQL_SEARCH = " AND UPPER(HM.NO_SYIT) LIKE '%" + NO_SYIT.toUpperCase() + "%'";
			}
			sql = "SELECT M.ID_PERMOHONAN, FAIL.NO_FAIL, M.NO_PERMOHONAN, HM.NO_LOT, HM.NO_SYIT, MUKIM.NAMA_MUKIM " +
				"FROM TBLPPTPERMOHONAN M, TBLPPTHAKMILIK HM, TBLPFDFAIL FAIL, TBLRUJMUKIM MUKIM " +
				"WHERE M.ID_PERMOHONAN = HM.ID_PERMOHONAN AND M.ID_FAIL = FAIL.ID_FAIL AND M.ID_MUKIM = MUKIM.ID_MUKIM(+)" +
				SQL_SEARCH + " ORDER BY FAIL.NO_FAIL, M.NO_PERMOHONAN, HM.NO_LOT, HM.NO_SYIT, MUKIM.NAMA_MUKIM";
			int i = 1;
			String ID_PERMOHONAN = "", NAMA_MUKIM = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				NO_LOT = rs.getString(4) == null ? "" : rs.getString(4);
				NO_SYIT = rs.getString(5) == null ? "" : rs.getString(5);
				NAMA_MUKIM = rs.getString(6) == null ? "" : rs.getString(6);

				h = new Hashtable();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NoLot", NO_LOT);
				h.put("NoSyit", NO_SYIT);
				h.put("Mukim", NAMA_MUKIM);
				v.add(h);
				i++;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector ListLampiran(String ID_PERMOHONAN) throws Exception  {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				Hashtable h = null;
				
				int iCount = 0;
				String ID_DOKUMEN = "", NAMA_FAIL = "", JENIS_MIME = "", TARIKH_MUATNAIK = "";
				
				sql = "SELECT ID_DOKUMEN, CONTENT_NAME, CONTENT_MIME, TARIKH_KEMASKINI FROM TBLINTDOKUMEN " +
					"WHERE UPPER(NAMA_PROSES) = 'JPBD' AND ID_PROSES = " + ID_PERMOHONAN;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					iCount = iCount + 1;
					ID_DOKUMEN = rs.getString(1) == null ? "" : rs.getString(1);
					NAMA_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
					JENIS_MIME = rs.getString(3) == null ? "" : rs.getString(3);
					TARIKH_MUATNAIK = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
					
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDDokumen", ID_DOKUMEN);
					h.put("NamaFail", NAMA_FAIL);
					h.put("JenisMIME", JENIS_MIME);
					h.put("TarikhMuatNaik", TARIKH_MUATNAIK);
					v.add(h);
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}

//	public String getIDPermohonan(String NO_FAIL) throws Exception {
//		String returnValue = "";
//		Db db = new Db();
//		
//		try {
//			if (!"".equalsIgnoreCase(NO_FAIL)) {
//				String sql = "";
//				sql = "SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE NO_FAIL = '" + NO_FAIL + "'";
//				Statement stmt = db.getStatement();
//				ResultSet rs = null;
//				rs = stmt.executeQuery(sql);
//				if (rs.next()) {
//					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
//				}
//			}
//		} finally {
//			if (db != null)
//				db.close();
//		}
//		return returnValue;
//	}

	public String getStatusPermohonan(String ID_PERMOHONAN) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				String sql = "";
				sql = "SELECT STATUS_PROSES FROM TBLINTJPBD WHERE ID_PERMOHONAN = '" + ID_PERMOHONAN + "'";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	public Vector viewMaklumatPermohonan(String ID_PERMOHONAN) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				String sql = "";
				Boolean haveINTData = false;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				String NO_FAIL = "", NO_PERMOHONAN = "", NAMA_NEGERI = "", NAMA_DAERAH = "", TAJUK_PERMOHONAN = "";
				String NO_RUJUKAN_JPBD = "", NO_WARTA = "", DALAM_KAWASAN_PBPT = "", ADA_PELAN_STRUKTUR = "", ADA_PELAN_TEMPATAN = "";
				String TARIKH_LULUS_PELAN_STRUKTUR = "", TARIKH_LULUS_PELAN_TEMPATAN = "", KEGUNAAN_TANAH = "", POTENSI_PEMBANGUNAN = "", NAMA_PBT = "";
				String STATUS_KELULUSAN = "", PERMOHONAN_MEMAJUKAN_TANAH = "", TUJUAN_PERMOHONAN = "", TARIKH_LULUS_TOLAK = "", TARIKH_LUPUT_KELULUSAN = "";
				String CATATAN_LAIN = "", NAMA_PEGAWAI_JPBD = "", JAWATAN_PEGAWAI_JPBD = "", TANDATANGAN_BAGI_PIHAK = "", NAMA_PEGAWAI_ASAL = "";
				String JAWATAN_PEGAWAI_ASAL = "", TARIKH_PERMOHONAN = "", JABATAN = "";

				sql = "SELECT FAIL.NO_FAIL, PMHN.NO_PERMOHONAN, NG.NAMA_NEGERI, DE.NAMA_DAERAH, PMHN.TUJUAN, M.NO_RUJUKAN_JPBD, M.NO_WARTA, " +
					"M.DALAM_KAWASAN_PBPT, M.ADA_PELAN_STRUKTUR, M.ADA_PELAN_TEMPATAN, M.TARIKH_LULUS_PELAN_STRUKTUR, M.TARIKH_LULUS_PELAN_TEMPATAN, " +
					"M.KEGUNAAN_TANAH, M.POTENSI_PEMBANGUNAN, M.NAMA_PBT, M.STATUS_KELULUSAN, M.PERMOHONAN_MEMAJUKAN_TANAH, M.TUJUAN_PERMOHONAN, " +
					"M.TARIKH_LULUS_TOLAK, M.TARIKH_LUPUT_KELULUSAN, M.CATATAN_LAIN, M.NAMA_PEGAWAI_JPBD, M.JAWATAN_PEGAWAI_JPBD, " +
					"M.TANDATANGAN_BAGI_PIHAK, M.NAMA_PEGAWAI_ASAL, M.JAWATAN_PEGAWAI_ASAL, M.TARIKH_PERMOHONAN, M.JABATAN, PMHN.ID_PERMOHONAN " +
					"FROM TBLINTJPBD M, TBLPPTPERMOHONAN PMHN, TBLPFDFAIL FAIL, TBLRUJNEGERI NG, TBLRUJDAERAH DE " +
					"WHERE M.ID_PERMOHONAN = PMHN.ID_PERMOHONAN AND PMHN.ID_FAIL = FAIL.ID_FAIL AND PMHN.ID_NEGERI = NG.ID_NEGERI(+) AND PMHN.ID_DAERAH = DE.ID_DAERAH(+) " +
					"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN;
				rs = stmt.executeQuery(sql);
				myLogger.info("viewMaklumatPermohonan====="+sql);
				if (rs.next()) {
					haveINTData = true;
					NO_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
					NO_PERMOHONAN = rs.getString(2) == null ? "" : rs.getString(2);
					NAMA_NEGERI = rs.getString(3) == null ? "" : rs.getString(3);
					NAMA_DAERAH = rs.getString(4) == null ? "" : rs.getString(4);
					TAJUK_PERMOHONAN = rs.getString(5) == null ? "" : rs.getString(5);
					NO_RUJUKAN_JPBD = rs.getString(6) == null ? "" : rs.getString(6);
					NO_WARTA = rs.getString(7) == null ? "" : rs.getString(7);
					DALAM_KAWASAN_PBPT = rs.getString(8) == null ? "" : rs.getString(8); 
					ADA_PELAN_STRUKTUR = rs.getString(9) == null ? "" : rs.getString(9); 
					ADA_PELAN_TEMPATAN = rs.getString(10) == null ? "" : rs.getString(10);
					TARIKH_LULUS_PELAN_STRUKTUR = rs.getDate(11) == null ? "" : sdf.format(rs.getDate(11));
					TARIKH_LULUS_PELAN_TEMPATAN = rs.getDate(12) == null ? "" : sdf.format(rs.getDate(12));
					KEGUNAAN_TANAH = rs.getString(13) == null ? "" : rs.getString(13);
					POTENSI_PEMBANGUNAN = rs.getString(14) == null ? "" : rs.getString(14);
					NAMA_PBT = rs.getString(15) == null ? "" : rs.getString(15);
					STATUS_KELULUSAN = rs.getString(16) == null ? "" : rs.getString(16);
					PERMOHONAN_MEMAJUKAN_TANAH = rs.getString(17) == null ? "" : rs.getString(17);
					TUJUAN_PERMOHONAN = rs.getString(18) == null ? "" : rs.getString(18);
					TARIKH_LULUS_TOLAK = rs.getDate(19) == null ? "" : sdf.format(rs.getDate(19));
					TARIKH_LUPUT_KELULUSAN = rs.getDate(20) == null ? "" : sdf.format(rs.getDate(20));
					CATATAN_LAIN = rs.getString(21) == null ? "" : rs.getString(21);
					NAMA_PEGAWAI_JPBD = rs.getString(22) == null ? "" : rs.getString(22);
					JAWATAN_PEGAWAI_JPBD = rs.getString(23) == null ? "" : rs.getString(23);
					TANDATANGAN_BAGI_PIHAK = rs.getString(24) == null ? "" : rs.getString(24);
					NAMA_PEGAWAI_ASAL = rs.getString(25) == null ? "" : rs.getString(25);
					JAWATAN_PEGAWAI_ASAL = rs.getString(26) == null ? "" : rs.getString(26); 
					TARIKH_PERMOHONAN = rs.getDate(27) == null ? "" : sdf.format(rs.getDate(27));
					JABATAN = rs.getString(28) == null ? "" : rs.getString(28);
					ID_PERMOHONAN = rs.getString(29) == null ? "" : rs.getString(29);
				}
				
				if (!haveINTData) {
					sql = "SELECT FAIL.NO_FAIL, PMHN.NO_PERMOHONAN, NG.NAMA_NEGERI, DE.NAMA_DAERAH, PMHN.TUJUAN, PMHN.ID_PERMOHONAN " +
					"'', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '' " +
					"FROM TBLPPTPERMOHONAN PMHN, TBLPFDFAIL FAIL, TBLRUJNEGERI NG, TBLRUJDAERAH DE " +
					"WHERE PMHN.ID_FAIL = FAIL.ID_FAIL AND PMHN.ID_NEGERI = NG.ID_NEGERI(+) AND PMHN.ID_DAERAH = DE.ID_DAERAH(+) " +
					"AND PMHN.ID_PERMOHONAN = " + ID_PERMOHONAN;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						NO_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
						NO_PERMOHONAN = rs.getString(2) == null ? "" : rs.getString(2);
						NAMA_NEGERI = rs.getString(3) == null ? "" : rs.getString(3);
						NAMA_DAERAH = rs.getString(4) == null ? "" : rs.getString(4);
						TAJUK_PERMOHONAN = rs.getString(5) == null ? "" : rs.getString(5);
						NO_RUJUKAN_JPBD = rs.getString(6) == null ? "" : rs.getString(6);
						NO_WARTA = rs.getString(7) == null ? "" : rs.getString(7);
						DALAM_KAWASAN_PBPT = rs.getString(8) == null ? "" : rs.getString(8); 
						ADA_PELAN_STRUKTUR = rs.getString(9) == null ? "" : rs.getString(9); 
						ADA_PELAN_TEMPATAN = rs.getString(10) == null ? "" : rs.getString(10);
						TARIKH_LULUS_PELAN_STRUKTUR = rs.getDate(11) == null ? "" : sdf.format(rs.getDate(11));
						TARIKH_LULUS_PELAN_TEMPATAN = rs.getDate(12) == null ? "" : sdf.format(rs.getDate(12));
						KEGUNAAN_TANAH = rs.getString(13) == null ? "" : rs.getString(13);
						POTENSI_PEMBANGUNAN = rs.getString(14) == null ? "" : rs.getString(14);
						NAMA_PBT = rs.getString(15) == null ? "" : rs.getString(15);
						STATUS_KELULUSAN = rs.getString(16) == null ? "" : rs.getString(16);
						PERMOHONAN_MEMAJUKAN_TANAH = rs.getString(17) == null ? "" : rs.getString(17);
						TUJUAN_PERMOHONAN = rs.getString(18) == null ? "" : rs.getString(18);
						TARIKH_LULUS_TOLAK = rs.getDate(19) == null ? "" : sdf.format(rs.getDate(19));
						TARIKH_LUPUT_KELULUSAN = rs.getDate(20) == null ? "" : sdf.format(rs.getDate(20));
						CATATAN_LAIN = rs.getString(21) == null ? "" : rs.getString(21);
						NAMA_PEGAWAI_JPBD = rs.getString(22) == null ? "" : rs.getString(22);
						JAWATAN_PEGAWAI_JPBD = rs.getString(23) == null ? "" : rs.getString(23);
						TANDATANGAN_BAGI_PIHAK = rs.getString(24) == null ? "" : rs.getString(24);
						NAMA_PEGAWAI_ASAL = rs.getString(25) == null ? "" : rs.getString(25);
						JAWATAN_PEGAWAI_ASAL = rs.getString(26) == null ? "" : rs.getString(26); 
						TARIKH_PERMOHONAN = rs.getDate(27) == null ? "" : sdf.format(rs.getDate(27));
						JABATAN = rs.getString(28) == null ? "" : rs.getString(28);
						ID_PERMOHONAN = rs.getString(29) == null ? "" : rs.getString(29);
					}
				}
				h = new Hashtable();
				h.put("haveINTData", Boolean.toString(haveINTData));
    			h.put("MP_NOFAIL", NO_FAIL);
				h.put("MP_NOPERMOHONAN", NO_PERMOHONAN);
				h.put("MP_NEGERI", NAMA_NEGERI);
				h.put("MP_DAERAH", NAMA_DAERAH);
				h.put("MP_TAJUKPERMOHONAN", TAJUK_PERMOHONAN);
				h.put("JPBD_NORUJUKANJPBD", NO_RUJUKAN_JPBD);
				h.put("JPBD_NOWARTA", NO_WARTA);
				h.put("JPBD_DALAMKAWASANPBPT", DALAM_KAWASAN_PBPT);
				h.put("JPBD_ADAPELANSTRUKTUR", ADA_PELAN_STRUKTUR);
				h.put("JPBD_ADAPELANTEMPATAN", ADA_PELAN_TEMPATAN);
				h.put("JPBD_TARIKHLULUSPELANSTRUKTUR", TARIKH_LULUS_PELAN_STRUKTUR);
				h.put("JPBD_TARIKHLULUSPELANTEMPATAN", TARIKH_LULUS_PELAN_TEMPATAN);
				h.put("JPBD_KEGUNAANTANAH", KEGUNAAN_TANAH);
				h.put("JPBD_POTENSIPEMBANGUNAN", POTENSI_PEMBANGUNAN);
				h.put("JPBD_NAMAPBT", NAMA_PBT);
				h.put("JPBD_STATUSKELULUSAN", STATUS_KELULUSAN);
				h.put("JPBD_PERMOHONANMEMAJUKANTANAH", PERMOHONAN_MEMAJUKAN_TANAH);
				h.put("JPBD_TUJUANPERMOHONAN", TUJUAN_PERMOHONAN);
				h.put("JPBD_TARIKHLULUSTOLAK", TARIKH_LULUS_TOLAK);
				h.put("JPBD_TARIKHLUPUTKELULUSAN", TARIKH_LUPUT_KELULUSAN);
				h.put("JPBD_CATATANLAIN", CATATAN_LAIN);
				h.put("JPBD_NAMAPEGAWAIJPBD", NAMA_PEGAWAI_JPBD);
				h.put("JPBD_JAWATANPEGAWAIJPBD", JAWATAN_PEGAWAI_JPBD);
				h.put("JPBD_TANDATANGANBAGIPIHAK", TANDATANGAN_BAGI_PIHAK);
				h.put("JPBD_NAMAPEGAWAIASAL", NAMA_PEGAWAI_ASAL);
				h.put("JPBD_JAWATANPEGAWAIASAL", JAWATAN_PEGAWAI_ASAL);
				h.put("JPBD_TARIKHPERMOHONAN", TARIKH_PERMOHONAN);
				h.put("JPBD_JABATAN", JABATAN);
				h.put("ID_PERMOHONAN", ID_PERMOHONAN);
				v.add(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	public Vector getIdNegeriKJPByUserId(String userId) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable h;
		Vector listDetailKJP = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.USER_ID, A.USER_NAME, C.ID_NEGERI, B.ID_KEMENTERIAN, B.ID_AGENSI FROM USERS A, USERS_KEMENTERIAN B, TBLRUJAGENSI C, TBLRUJKEMENTERIAN D "
					+ " WHERE A.USER_ID = B.USER_ID AND B.ID_AGENSI = C.ID_AGENSI AND B.ID_KEMENTERIAN = D.ID_KEMENTERIAN AND A.USER_ID = '"
					+ userId + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("listDetailKJP:: "+sql);

			if (rs.next()) {
				h = new Hashtable();
				h.put("userId", rs.getString("USER_ID").toString());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI").toString());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN").toString());
				h.put("idAgensi", rs.getString("ID_AGENSI").toString());
				h.put("namaPemohon", rs.getString("USER_NAME").toString());
				listDetailKJP.addElement(h);

				return listDetailKJP;
			} else {
				return listDetailKJP;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Boolean saveBorangLampiranA1(String ID_PERMOHONAN, String USER_ID, Boolean isJPBDRole, Hashtable h) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !h.isEmpty()) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Boolean haveData = false;
				String sql = "";

				long ID_JPBD = 0;
				String STATUS_PROSES = "";
				String NO_RUJUKAN_JPBD = "", NO_WARTA = "", DALAM_KAWASAN_PBPT = "", ADA_PELAN_STRUKTUR = "", ADA_PELAN_TEMPATAN = "";
				String TARIKH_LULUS_PELAN_STRUKTUR = "", TARIKH_LULUS_PELAN_TEMPATAN = "", KEGUNAAN_TANAH = "", POTENSI_PEMBANGUNAN = "", NAMA_PBT = "";
				String STATUS_KELULUSAN = "", PERMOHONAN_MEMAJUKAN_TANAH = "", TUJUAN_PERMOHONAN = "", TARIKH_LULUS_TOLAK = "", TARIKH_LUPUT_KELULUSAN = "";
				String CATATAN_LAIN = "", NAMA_PEGAWAI_JPBD = "", JAWATAN_PEGAWAI_JPBD = "", TANDATANGAN_BAGI_PIHAK = "", NAMA_PEGAWAI_ASAL = "";
				String JAWATAN_PEGAWAI_ASAL = "", TARIKH_PERMOHONAN = "", JABATAN = "";

				NO_RUJUKAN_JPBD = (String) h.get("JPBD_NORUJUKANJPBD");
				NO_WARTA = (String) h.get("JPBD_NOWARTA");
				DALAM_KAWASAN_PBPT = (String) h.get("JPBD_DALAMKAWASANPBPT");
				ADA_PELAN_STRUKTUR = (String) h.get("JPBD_ADAPELANSTRUKTUR");
				ADA_PELAN_TEMPATAN = (String) h.get("JPBD_ADAPELANTEMPATAN");
				TARIKH_LULUS_PELAN_STRUKTUR = (String) h.get("JPBD_TARIKHLULUSPELANSTRUKTUR");
				TARIKH_LULUS_PELAN_TEMPATAN = (String) h.get("JPBD_TARIKHLULUSPELANTEMPATAN");
				KEGUNAAN_TANAH = (String) h.get("JPBD_KEGUNAANTANAH");
				POTENSI_PEMBANGUNAN = (String) h.get("JPBD_POTENSIPEMBANGUNAN");
				NAMA_PBT = (String) h.get("JPBD_NAMAPBT");
				STATUS_KELULUSAN = (String) h.get("JPBD_STATUSKELULUSAN");
				PERMOHONAN_MEMAJUKAN_TANAH = (String) h.get("JPBD_PERMOHONANMEMAJUKANTANAH");
				TUJUAN_PERMOHONAN = (String) h.get("JPBD_TUJUANPERMOHONAN");
				TARIKH_LULUS_TOLAK = (String) h.get("JPBD_TARIKHLULUSTOLAK");
				TARIKH_LUPUT_KELULUSAN = (String) h.get("JPBD_TARIKHLUPUTKELULUSAN");
				CATATAN_LAIN = (String) h.get("JPBD_CATATANLAIN");
				NAMA_PEGAWAI_JPBD = (String) h.get("JPBD_NAMAPEGAWAIJPBD");
				JAWATAN_PEGAWAI_JPBD = (String) h.get("JPBD_JAWATANPEGAWAIJPBD");
				TANDATANGAN_BAGI_PIHAK = (String) h.get("JPBD_TANDATANGANBAGIPIHAK");
				NAMA_PEGAWAI_ASAL = (String) h.get("JPBD_NAMAPEGAWAIASAL");
				JAWATAN_PEGAWAI_ASAL = (String) h.get("JPBD_JAWATANPEGAWAIASAL");
				TARIKH_PERMOHONAN = (String) h.get("JPBD_TARIKHPERMOHONAN");
				JABATAN = (String) h.get("JPBD_JABATAN");
				
				if (isJPBDRole) {
					STATUS_PROSES = "DALAM PROSES JPBD";
				} else {
					STATUS_PROSES = "BARU";
				}
				
				// check in TBLINTJPBD
				r.clear();
				r.add("ID_PERMOHONAN");
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				sql = r.getSQLSelect("TBLINTJPBD");				
				rs = stmt.executeQuery(sql);
				myLogger.info("saveBorangLampiranA1====1"+sql);
				if (rs.next()) {
					haveData = true;
				}
				
				r.clear();
				if (haveData) {
					r.update("ID_PERMOHONAN", ID_PERMOHONAN);
				} else {
					r.add("ID_MASUK", USER_ID);
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				}
				r.add("STATUS_PROSES", STATUS_PROSES);
				r.add("ID_KEMASKINI", USER_ID);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				r.add("NO_RUJUKAN_JPBD", NO_RUJUKAN_JPBD);
				r.add("NO_WARTA", NO_WARTA);
				r.add("DALAM_KAWASAN_PBPT", DALAM_KAWASAN_PBPT);
				r.add("ADA_PELAN_STRUKTUR", ADA_PELAN_STRUKTUR);
				r.add("ADA_PELAN_TEMPATAN", ADA_PELAN_TEMPATAN);
				r.add("TARIKH_LULUS_PELAN_STRUKTUR", r.unquote("TO_DATE('" + TARIKH_LULUS_PELAN_STRUKTUR + "', 'dd/MM/yyyy')"));
				r.add("TARIKH_LULUS_PELAN_TEMPATAN", r.unquote("TO_DATE('" + TARIKH_LULUS_PELAN_TEMPATAN + "', 'dd/MM/yyyy')"));
				r.add("KEGUNAAN_TANAH", KEGUNAAN_TANAH);
				r.add("POTENSI_PEMBANGUNAN", POTENSI_PEMBANGUNAN);
				r.add("NAMA_PBT", NAMA_PBT);
				r.add("STATUS_KELULUSAN", STATUS_KELULUSAN);
				r.add("PERMOHONAN_MEMAJUKAN_TANAH", PERMOHONAN_MEMAJUKAN_TANAH);
				r.add("TUJUAN_PERMOHONAN", TUJUAN_PERMOHONAN);
				r.add("TARIKH_LULUS_TOLAK", r.unquote("TO_DATE('" + TARIKH_LULUS_TOLAK + "', 'dd/MM/yyyy')"));
				r.add("TARIKH_LUPUT_KELULUSAN", r.unquote("TO_DATE('" + TARIKH_LUPUT_KELULUSAN + "', 'dd/MM/yyyy')"));
				r.add("CATATAN_LAIN", CATATAN_LAIN);
				r.add("NAMA_PEGAWAI_JPBD", NAMA_PEGAWAI_JPBD);
				r.add("JAWATAN_PEGAWAI_JPBD", JAWATAN_PEGAWAI_JPBD);
				r.add("TANDATANGAN_BAGI_PIHAK", TANDATANGAN_BAGI_PIHAK);
				r.add("NAMA_PEGAWAI_ASAL", NAMA_PEGAWAI_ASAL);
				r.add("JAWATAN_PEGAWAI_ASAL", JAWATAN_PEGAWAI_ASAL);
				r.add("TARIKH_PERMOHONAN", r.unquote("TO_DATE('" + TARIKH_PERMOHONAN + "', 'dd/MM/yyyy')"));
				r.add("JABATAN", JABATAN);
				if (haveData) {
					sql = r.getSQLUpdate("TBLINTJPBD");
					myLogger.info("saveBorangLampiranA1====2"+sql);
				} else {
					ID_JPBD = DB.getNextID("TBLINTJPBD_SEQ");
					r.add("ID_JPBD", ID_JPBD);
					sql = r.getSQLInsert("TBLINTJPBD");
					myLogger.info("saveBorangLampiranA1====3"+sql);
				}
				stmt.execute(sql);
				returnValue = true;
			}			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean sendBorangLampiranA1(String ID_PERMOHONAN, Boolean isJPBDRole) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				String sql = "";
				
				r.clear();
				if (isJPBDRole) {
					r.add("STATUS_PROSES", "SELESAI");
				} else {
					r.add("STATUS_PROSES", "BARU");
				}
				r.update("ID_PERMOHONAN", ID_PERMOHONAN);
				sql = r.getSQLUpdate("TBLINTJPBD");				
				stmt.executeQuery(sql);
				returnValue = true;
			}			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean verifyBorangLampiranA1(String ID_PERMOHONAN) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				String sql = "";
				
				r.clear();
				r.add("STATUS_PROSES", "MOHON PENGESAHAN");
				r.update("ID_PERMOHONAN", ID_PERMOHONAN);
				sql = r.getSQLUpdate("TBLINTJPBD");				
				stmt.executeQuery(sql);
				returnValue = true;
			}			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean deleteBorangLampiranA1(String ID_PERMOHONAN) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				Boolean haveData = false;
				String sql = "";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				SQLRenderer r = new SQLRenderer();
				
				// check in TBLINTJPBD
				r.add("ID_PERMOHONAN");
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				sql = r.getSQLSelect("TBLINTJPBD");
				r.clear();
				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveData = true;
				}
				if (haveData) {
					sql = "DELETE FROM TBLINTJPBD WHERE ID_PERMOHONAN = " + ID_PERMOHONAN;
					stmt.execute(sql);
					returnValue = true;
				}
			}
		} finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
	}
	
	public Boolean deleteFile(String ID_DOKUMEN) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			//if (!"".equalsIgnoreCase(ID_DOKUMEN)) {
				Boolean haveData = false;
				String sql = "";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				SQLRenderer r = new SQLRenderer();
				
				// check in TBLINTJPBD
				//r.add("ID_DOKUMEN");
				//r.add("ID_DOKUMEN", ID_DOKUMEN);
				sql = r.getSQLSelect("TBLINTDOKUMEN");
				myLogger.info("baca deleteFile========");
				//r.clear();
				
				//rs = stmt.executeQuery(sql);
				//if (rs.next()) {
					haveData = true;
				//}
				//if (haveData) {
					sql = "DELETE FROM TBLINTDOKUMEN WHERE ID_DOKUMEN = " + ID_DOKUMEN;
					stmt.executeUpdate(sql);
					myLogger.info("baca deleteFile========"+sql);
					returnValue = true;
				//}
			//}
		} finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
	}

	public Boolean doUpload(FileItem objItem, String idPermohonan) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			java.util.Date today = new java.util.Date();
			java.sql.Date date = new java.sql.Date(today.getTime());
			long ID_DB = DB.getNextID("TBLINTDOKUMEN_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	PreparedStatement ps = con.prepareStatement("INSERT INTO TBLINTDOKUMEN " +
        			"(ID_DOKUMEN, TARIKH_SIMPAN, TARIKH_KEMASKINI, ID_PROSES, NAMA_PROSES, CONTENT, CONTENT_NAME, CONTENT_MIME) " +
        			"VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        	ps.setLong(1, ID_DB);
        	ps.setDate(2, date);
        	ps.setDate(3, date);
        	ps.setString(4, idPermohonan);
        	ps.setString(5, "JPBD");
        	ps.setBinaryStream(6, objItem.getInputStream(), (int)objItem.getSize());
        	ps.setString(7, objItem.getName());
        	ps.setString(8, objItem.getContentType());
        	ps.executeUpdate();
            con.commit();
            
            returnValue = true;
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean isPegawaiJPBD(String ID_USER) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			String sql = "";
			ResultSet rs = null;
			Statement stmt = db.getStatement();
			int TEMP = 0;
			
			sql = "SELECT I.ID_JAWATAN " +
				"FROM USERS U, USERS_INTERNAL I " +
				"WHERE U.USER_ID = I.USER_ID " +
				"AND U.USER_ID = " + ID_USER;
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				TEMP = rs.getInt(1);
			}
			if (TEMP < 18) {
	            returnValue = true;
			}			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
}