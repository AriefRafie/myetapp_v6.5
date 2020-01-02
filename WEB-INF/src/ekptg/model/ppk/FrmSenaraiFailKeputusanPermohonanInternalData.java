/**
 * 
 */
package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

//import org.apache.xalan.xsltc.runtime.Hashtable;

/**
 * @author razman
 * 
 */
public class FrmSenaraiFailKeputusanPermohonanInternalData {

	private static Vector listKeputusan = new Vector();
	private static Vector list17 = new Vector();

	private static Vector listB = new Vector();
	private static Vector listC = new Vector();
	private static Vector listBrgC = new Vector();

	private static Vector listKeputusan17 = new Vector();
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");

	public static void setListKeputusan(String userid) throws Exception {
		Db db = null;
		listKeputusan.clear();

		String sql = "SELECT MS.ID_PERMOHONANSIMATI, MS.ID_SIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, "
				+ " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL,(SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
				+ " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI "
				+ " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
				+ " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB"
				+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
				+ userid
				+ "' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
				
				sql += " ) "
				+ " AND P.ID_STATUS = S.ID_STATUS "
				+ " AND P.ID_FAIL = F.ID_FAIL(+) "
				+ " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) "
				+ " AND P.ID_DAERAHMHN = D.ID_DAERAH "
				+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN "
				+ " AND M.ID_SIMATI = MS.ID_SIMATI  "
				// +" AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN "
				+ " AND PP.ID_PEMOHON = P.ID_PEMOHON "
				+ " AND (P.ID_STATUS=14 OR P.ID_STATUS=1 OR P.ID_STATUS=50 OR P.ID_STATUS=53 OR P.ID_STATUS=63 OR P.ID_STATUS=151 OR P.ID_STATUS=152 ) "
				+ " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN"
				+ " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS"
				+ " AND STA.AKTIF = '1'"
				+ " AND P.ID_STATUS <> '999' "
				+ " AND P.SEKSYEN = '8' "
				+ " ORDER BY STA.ID_SUBURUSANSTATUSFAIL DESC";

		// +" AND F.FLAG_JENIS_FAIL = 1"

		// System.out.println("KEPUTUSAN PERMOHONAN SQL"+sql.toUpperCase());
		System.out.println("KEPUTUSAN PERMOHONAN SQL : "+sql.toUpperCase());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("f.id_Fail");
			r.add("f.no_Fail");
			r.add("a.id_Permohonan");
			r.add("a.tarikh_Mohon");
			r.add("a.tarikh_Masuk");
			r.add("f.tarikh_daftar_fail");
			r.add("s.keterangan");
			r.add("p.id_Simati");
			r.add("p.nama_Simati");
			r.add("a.id_Daerahmhn");
			// r.add("a.no_Permohonan_Online");
			r.add("pm.nama_Pemohon");
			r.add("pm.no_Kp_Baru");
			r.add("a.id_Status");
			r.add("s.keterangan");

			r.add("kp.keputusan_Permohonan");

			r.add("a.id_Status", r.unquote("s.id_Status(+)"));
			r.add("pm.id_Permohonan", r.unquote("a.id_Permohonan(+)"));

			r.add("a.id_Fail", r.unquote("f.id_Fail(+)"));
			r.add("a.id_Permohonan", r.unquote("ms.id_Permohonan"));
			r.add("p.id_Simati", r.unquote("ms.id_Simati"));
			r.add("s.id_status", r.unquote("kp.keputusan_Permohonan(+)"));

			// r.add("kp.id_Permohonan",r.unquote("a.id_Permohonan(+)"));

			r.add("a.id_Status", 14);
			// r.add("a.flag_Jenis_Permohonan",0);

			// String sql =
			// r.getSQLSelect("Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s, Tblppksimati p, Tblppkpemohon pm, Tblppkpermohonansimati ms, Tblppkkeputusanpermohonan kp");
			// sql = sql + " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";

			// System.out.print("SQL XXXX"+sql);
			// ResultSet rs = stmt.executeQuery(sql);

			// sql =
			// r.getSQLSelect("Tblppkpermohonan p, Tblpfdfail f, Tblppkkeputusanpermohonan kp, Tblrujstatus s");
			// sql = sql + " and p.id_status=14 order by id_permohonan desc";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));

				h.put("id_Permohonansimati",
						rs.getString("id_Permohonansimati") == null ? "" : rs
								.getString("id_Permohonansimati"));

				h.put("id_Fail",
						rs.getString("id_Fail") == null ? "" : rs
								.getString("id_Fail"));
				h.put("no_Fail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("id_Status",
						rs.getString("id_Status") == null ? "" : rs
								.getString("id_Status"));
				// h.put("id_KeputusanPermohonan",
				// rs.getString("id_KeputusanPermohonan")==null?"":rs.getString("id_KeputusanPermohonan"));
				h.put("statuskeputusan",
						rs.getString("keputusan_Permohonan") == null ? "" : rs
								.getString("keputusan_Permohonan"));
				// h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				// h.put("tarikhMasuk",rs.getString("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
				// h.put("tarikhDaftar",rs.getString("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));

				// h.put("statuskeputusan",rs.getString(11)==null?"":rs.getString(11));
				h.put("namasimati", rs.getString("nama_simati") == null ? ""
						: rs.getString("nama_simati"));
				h.put("idSimati", rs.getString("id_Simati") == null ? ""
						: rs.getString("id_Simati"));
				listKeputusan.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void setListKeputusan17(String userid) throws Exception {
		Db db = null;
		listKeputusan17.clear();

		String sql = "SELECT MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, "
				+ " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL,(SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
				+ " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI "
				+ " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
				+ " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB"
				+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
				+ userid
				+ "' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
				
				sql += " ) "
				+ " AND P.ID_STATUS = S.ID_STATUS "
				+ " AND P.ID_FAIL = F.ID_FAIL(+) "
				+ " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) "
				+ " AND P.ID_DAERAHMHN = D.ID_DAERAH "
				+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN "
				+ " AND M.ID_SIMATI = MS.ID_SIMATI  "
				+ " AND P.ID_PEMOHON= PP.ID_PEMOHON "
				+ " AND (P.ID_STATUS=14 OR P.ID_STATUS=1 OR P.ID_STATUS=50 OR P.ID_STATUS=53 OR P.ID_STATUS=63 OR P.ID_STATUS=151 OR P.ID_STATUS=152 ) "
				+ " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN"
				+ " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS"
				+ " AND STA.AKTIF = '1'"
				+ " AND P.ID_STATUS <> '999' "
				+ " AND P.SEKSYEN = '17' "
				+ " ORDER BY STA.ID_SUBURUSANSTATUSFAIL DESC";

		// +" AND F.FLAG_JENIS_FAIL = 1"

		// System.out.println("KEPUTUSAN PERMOHONAN SQL"+sql.toUpperCase());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("f.id_Fail");
			r.add("f.no_Fail");
			r.add("a.id_Permohonan");
			r.add("a.tarikh_Mohon");
			r.add("a.tarikh_Masuk");
			r.add("f.tarikh_daftar_fail");
			r.add("s.keterangan");
			r.add("p.id_Simati");
			r.add("p.nama_Simati");
			r.add("a.id_Daerahmhn");
			// r.add("a.no_Permohonan_Online");
			r.add("pm.nama_Pemohon");
			r.add("pm.no_Kp_Baru");
			r.add("a.id_Status");
			r.add("s.keterangan");

			r.add("kp.keputusan_Permohonan");

			r.add("a.id_Status", r.unquote("s.id_Status(+)"));
			r.add("pm.id_Permohonan", r.unquote("a.id_Permohonan(+)"));

			r.add("a.id_Fail", r.unquote("f.id_Fail(+)"));
			r.add("a.id_Permohonan", r.unquote("ms.id_Permohonan"));
			r.add("p.id_Simati", r.unquote("ms.id_Simati"));
			r.add("s.id_status", r.unquote("kp.keputusan_Permohonan(+)"));

			// r.add("kp.id_Permohonan",r.unquote("a.id_Permohonan(+)"));

			r.add("a.id_Status", 14);
			// r.add("a.flag_Jenis_Permohonan",0);

			// String sql =
			// r.getSQLSelect("Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s, Tblppksimati p, Tblppkpemohon pm, Tblppkpermohonansimati ms, Tblppkkeputusanpermohonan kp");
			// sql = sql + " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";

			// System.out.print("SQL XXXX"+sql);
			// ResultSet rs = stmt.executeQuery(sql);

			// sql =
			// r.getSQLSelect("Tblppkpermohonan p, Tblpfdfail f, Tblppkkeputusanpermohonan kp, Tblrujstatus s");
			// sql = sql + " and p.id_status=14 order by id_permohonan desc";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));

				h.put("id_Permohonansimati",
						rs.getString("id_Permohonansimati") == null ? "" : rs
								.getString("id_Permohonansimati"));

				h.put("id_Fail",
						rs.getString("id_Fail") == null ? "" : rs
								.getString("id_Fail"));
				h.put("no_Fail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("id_Status",
						rs.getString("id_Status") == null ? "" : rs
								.getString("id_Status"));
				// h.put("id_KeputusanPermohonan",
				// rs.getString("id_KeputusanPermohonan")==null?"":rs.getString("id_KeputusanPermohonan"));
				h.put("statuskeputusan",
						rs.getString("keputusan_Permohonan") == null ? "" : rs
								.getString("keputusan_Permohonan"));
				// h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				// h.put("tarikhMasuk",rs.getString("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
				// h.put("tarikhDaftar",rs.getString("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));

				// h.put("statuskeputusan",rs.getString(11)==null?"":rs.getString(11));
				h.put("namasimati", rs.getString("nama_simati") == null ? ""
						: rs.getString("nama_simati"));
				listKeputusan17.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void setIntMahkamah(String id_fail) throws Exception {
		Db db = null;
		listB.clear();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String sql = "SELECT TO_CHAR(M.TARIKHJANABRGB,'DD/MM/YYYY') AS T "
					+ " FROM TBLINTMTPERMOHONAN M,TBLPFDFAIL F,TBLPPKPERMOHONAN P "
					+ " WHERE M.PETISYENNO = F.NO_FAIL AND F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = '"
					+ id_fail + "'  ";

			// System.out.println("SQL::::::::::::::::::::::::" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("TARIKH_B",
						rs.getString("T") == null ? "" : rs.getString("T"));

				listB.addElement(h);

			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void setIntMahkamah_BrgC(String id_fail) throws Exception {
		Db db = null;
		listC.clear();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String sql = "SELECT TO_CHAR(K.TARIKHJANABORANGC,'DD/MM/YYYY') AS T, K.BORANGCEXTRACTKOD AS SCODE, K.KEPUTUSANBORANGC AS RESULT "
					+ " FROM TBLINTMTKEPUTUSAN K,TBLINTMTPERMOHONAN M,TBLPFDFAIL F,TBLPPKPERMOHONAN P "
					+ " WHERE K.IDKADBIRU = M.IDKADBIRU AND M.PETISYENNO = F.NO_FAIL AND F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = '"
					+ id_fail + "'  ";

			// System.out.println("SQL::::::::::::::::::::::::" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("TARIKH_C",
						rs.getString("T") == null ? "" : rs.getString("T"));
				h.put("SECURITY_C",
						rs.getString("SCODE") == null ? "" : rs
								.getString("SCODE"));
				h.put("RESULT_C",
						rs.getString("RESULT") == null ? "" : rs
								.getString("RESULT"));

				listC.addElement(h);

			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getIntMahkamah() {
		return listB;
	}

	public static Vector getIntMahkamah_BrgC() {
		return listC;
	}

	public static Vector getListKeputusan() {
		return listKeputusan;
	}

	public static Vector getListKeputusan17() {
		return listKeputusan17;
	}

	private static Vector listD = new Vector();
	private static Vector list = new Vector();

	public static Vector getData() {
		return listD;
	}

	public static void setData(String id, String uid) throws Exception {
		Db db = null;
		listD.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT P.ID_PEJAWAL, P.NO_FAIL_AWAL, P.NAMA_PEMOHON_AWAL,F.ID_FAIL, F.NO_FAIL, D.ID_DAERAH, P.ID_PERMOHONAN, P.TARIKH_MOHON,  S.NO_KP_BARU,"
					+ " S.NO_KP_LAMA, S.JENIS_KP, S.NO_KP_LAIN, S.ID_SIMATI, S.NAMA_SIMATI,  S.TARIKH_MATI,"
					+ " PM.ID_PEMOHON, PM.NAMA_PEMOHON, PM.NO_KP_BARU, PM.NO_KP_LAMA, PM.JENIS_KP, PM.NO_KP_LAIN, "
					+ " PM.ALAMAT_1, PM.ALAMAT_2, PM.ALAMAT_3, PM.POSKOD, PM.BANDAR, N.ID_NEGERI, N.NAMA_NEGERI, D.NAMA_DAERAH, "
					+ " P.SEKSYEN, ST.KETERANGAN, P.ID_STATUS, U.ID_PEJABATJKPTG,  U.NAMA_PEJABAT, P.JUMLAH_HTA_TARIKHMOHON, "
					+ " P.JUMLAH_HTA_TARIKHMATI, P.JUMLAH_HA_TARIKHMOHON,  P.JUMLAH_HA_TARIKHMATI, P.JUMLAH_HARTA_TARIKHMOHON,"
					+ " P.JUMLAH_HARTA_TARIKHMATI,  PK.TARIKH_HANTAR_BORANGB, PK.TARIKH_TERIMA_BORANGC, PK.TARIKH_HANTAR_NILAIAN, "
					+ " PK.TARIKH_TERIMA_NILAIAN, PK.FLAG_SALINAN_ARAHAN, PK.JENIS_BORANGC, PK.KEPUTUSAN_PERMOHONAN, PK.CATATAN, SUB.ID_SUBURUSANSTATUS, "
					+ " SUB.ID_SUBURUSANSTATUSFAIL, SUB.AKTIF, RP.ID_JENISPEJABAT, PK.ID_NEGERIMAHKAMAH, PK.ID_DAERAH_MAHKAMAH, SM.ID_PERMOHONANSIMATI,   "
					+ " P.ID_NEGERITERTINGGI, P.ID_DAERAHTERTINGGI, P.ID_DAERAHMHN, PM.ID_TARAFKPTG AS ID_TARAF_MOHON,"
					
					+" PK.FLAG_SEBABPINDAHMAHKAMAH, " //razman add
					
					+ " UX.KETERANGAN AS TARAF_PEMOHON,DM.NAMA_DAERAH AS D_P, "
					+ " KV.ID_PERMOHONAN AS PERMOHONAN_KV,KV.ALAMAT1,KV.ALAMAT2,KV.ALAMAT3,KV.POSKOD AS POSKOD_KV,"
					+ " KV.NAMA_FIRMA,KV.ID_BANDAR,KV.ID_NEGERI AS NEGERI_KV,KV.NAMA_KAVEAT, KV.NO_KAVEAT,KV.NO_TEL,KV.TARIKH_KAVEAT,F.FLAG_JENIS_FAIL,U.ID_NEGERI AS ID_NEGERIPEJABAT "
					+ " FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI MS, "
					+ " TBLRUJNEGERI N, TBLRUJDAERAH D, TBLPPKSIMATI S, TBLPPKPEMOHON PM, "
					+ " TBLRUJSTATUS ST, TBLRUJPEJABATJKPTG U,TBLRUJSUBURUSANSTATUSFAIL SUB,TBLPPKKAVEATPEGUAM KV, "
					+ " TBLPPKKEPUTUSANPERMOHONAN PK, USERS_INTERNAL UR, TBLRUJPEJABAT RP, TBLPPKPERMOHONANSIMATI SM,TBLPPKRUJTARAFKPTG UX, TBLRUJDAERAH DM "
					+ " WHERE F.ID_NEGERI = N.ID_NEGERI(+)"
					+ " AND P.ID_PEJAWAL = RP.ID_PEJABAT(+)"
					+ " AND P.ID_DAERAHMHN = D.ID_DAERAH(+)"
					+ " AND P.ID_FAIL = F.ID_FAIL  "
					// +" AND PM.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND P.ID_PEMOHON = PM.ID_PEMOHON "
					+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN "
					+ " AND S.ID_SIMATI = MS.ID_SIMATI  "
					+ " AND ST.ID_STATUS = P.ID_STATUS"
					+ " AND UR.ID_PEJABATJKPTG = U.ID_PEJABATJKPTG"
					+ " AND U.ID_DAERAH = DM.ID_DAERAH(+)"
					+ " AND UR.USER_ID = '"
					+ uid
					+ "'"
					+ " AND P.ID_PERMOHONAN = PK.ID_PERMOHONAN(+)"
					+ " AND P.ID_PERMOHONAN = SUB.ID_PERMOHONAN"
					+ " AND P.ID_PERMOHONAN = '"
					+ id
					+ "'"
					// + " AND SUB.AKTIF = 1"
					+ " AND SUB.ID_PERMOHONAN  = SM.ID_PERMOHONAN"
					+ " AND PM.ID_TARAFKPTG = UX.ID_TARAFKPTG(+)"
					+ " AND P.ID_PERMOHONAN = KV.ID_PERMOHONAN(+)" + "";

			 System.out.println("SYstem KEPUTUSAN::"+sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			String jp = "";
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail",
						rs.getString("id_Fail") == null ? "" : rs
								.getString("id_Fail"));
				h.put("noFail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				h.put("idDaerah",
						rs.getString("id_Daerah") == null ? "" : rs
								.getString("id_Daerah"));
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("tarikhMohon", rs.getString("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("noKpLama",
						rs.getString("no_Kp_Lama") == null ? "" : rs
								.getString("no_Kp_Lama"));
				h.put("jenisKp",
						rs.getString("jenis_Kp") == null ? "" : rs
								.getString("jenis_Kp"));
				h.put("noKpLain",
						rs.getString("no_Kp_Lain") == null ? "" : rs
								.getString("no_Kp_Lain"));
				h.put("idSimati",
						rs.getString("id_Simati") == null ? "" : rs
								.getString("id_Simati"));
				h.put("namaSimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("tarikhMati", rs.getString("tarikh_Mati") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mati")));
				h.put("idPemohon",
						rs.getString("id_Pemohon") == null ? "" : rs
								.getString("id_Pemohon"));
				h.put("namaPemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("noKpBaruPemohon1", rs.getString(15) == null ? "" : rs
						.getString(15).substring(0, 6));
				h.put("noKpBaruPemohon2", rs.getString(15) == null ? "" : rs
						.getString(15).substring(6, 8));
				h.put("noKpBaruPemohon3", rs.getString(15) == null ? "" : rs
						.getString(15).substring(8, 12));
				h.put("noKpLamaPemohon",
						rs.getString(16) == null ? "" : rs.getString(16));
				h.put("jenisKpPemohon",
						rs.getString(17) == null ? "" : rs.getString(17));
				h.put("noKpLainPemohon",
						rs.getString(18) == null ? "" : rs.getString(18));
				h.put("alamat1",
						rs.getString("alamat_1") == null ? "" : rs
								.getString("alamat_1"));
				h.put("alamat2",
						rs.getString("alamat_2") == null ? "" : rs
								.getString("alamat_2"));
				h.put("alamat3",
						rs.getString("alamat_3") == null ? "" : rs
								.getString("alamat_3"));
				h.put("poskod",
						rs.getString("poskod") == null ? "" : rs
								.getString("poskod"));
				h.put("bandar",
						rs.getString("bandar") == null ? "" : rs
								.getString("bandar"));
				h.put("idnegeri",
						rs.getString("id_Negeri") == null ? "" : rs
								.getString("id_Negeri"));
				h.put("namanegeri", rs.getString("nama_Negeri") == null ? ""
						: rs.getString("nama_Negeri"));
				h.put("namadaerah", rs.getString("nama_Daerah") == null ? ""
						: rs.getString("nama_Daerah"));
				h.put("seksyen",
						rs.getString("seksyen") == null ? "" : rs
								.getString("seksyen"));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_Status",
						rs.getString("id_Status") == null ? "" : rs
								.getString("id_Status"));
				h.put("namaPejabat", rs.getString("nama_pejabat") == null ? ""
						: rs.getString("nama_pejabat"));
				h.put("salinan_arahan", rs.getString("flag_salinan_arahan") == null ? ""
						: rs.getString("flag_salinan_arahan"));

				if (rs.getString("ID_NEGERIPEJABAT").equals("7")) {
					h.put("namaPejabat",
							rs.getString("nama_pejabat") == null ? "" : rs
									.getString("nama_pejabat"));
				} else {
					h.put("namaPejabat",
							rs.getString("nama_pejabat") + ","
									+ rs.getString("D_P") == null ? "" : rs
									.getString("nama_pejabat")
									+ ","
									+ rs.getString("D_P"));
				}

				h.put("jumHtaTarikhMohon",
						rs.getDouble("jumlah_hta_tarikhmohon"));
				h.put("jumHtaTarikhMati", rs.getDouble("jumlah_hta_tarikhmati"));
				h.put("jumHaTarikhMohon", rs.getDouble("jumlah_ha_tarikhmohon"));
				h.put("jumHaTarikhMati", rs.getDouble("jumlah_ha_tarikhmati"));
				h.put("jumHartaTarikhMohon",
						rs.getDouble("jumlah_harta_tarikhmohon"));
				h.put("jumHartaTarikhMati",
						rs.getDouble("jumlah_harta_tarikhmati"));
				h.put("tarikhborangB",
						rs.getString("tarikh_hantar_borangB") == null ? ""
								: sdf.format(rs
										.getDate("tarikh_hantar_borangB")));
				h.put("tarikhborangC",
						rs.getString("tarikh_terima_borangC") == null ? ""
								: sdf.format(rs
										.getDate("tarikh_terima_borangC")));
				h.put("tarikhhantarnilaian",
						rs.getString("tarikh_hantar_nilaian") == null ? ""
								: sdf.format(rs
										.getDate("tarikh_hantar_nilaian")));
				h.put("tarikhterimanilaian",
						rs.getString("tarikh_terima_nilaian") == null ? ""
								: sdf.format(rs
										.getDate("tarikh_terima_nilaian")));
				h.put("jenisborangC",
						rs.getString("jenis_borangC") == null ? "" : rs
								.getString("jenis_borangC"));
				h.put("keputusanpermohonan",
						rs.getString("keputusan_permohonan") == null ? "" : rs
								.getString("keputusan_permohonan"));
				h.put("catatan",
						rs.getString("catatan") == null ? "" : rs
								.getString("catatan"));

				if (rs.getString("PERMOHONAN_KV") != null) {
					jp = "99";
				} else {
					jp = rs.getString("id_Jenispejabat") == null ? "" : rs
							.getString("id_Jenispejabat");
				}

				h.put("jenis_pej", jp);

				h.put("nofailawal", rs.getString("no_Fail_Awal") == null ? ""
						: rs.getString("no_Fail_Awal"));
				h.put("pemohonawal",
						rs.getString("nama_Pemohon_Awal") == null ? "" : rs
								.getString("nama_Pemohon_Awal"));
				h.put("pejabatawal", rs.getString("id_Pejawal") == null ? ""
						: rs.getString("id_Pejawal"));

				h.put("daerahmhn", rs.getString("id_Daerahmhn") == null ? ""
						: rs.getString("id_Daerahmhn"));

				h.put("negerimahkamah",
						rs.getString("ID_NEGERIMAHKAMAH") == null ? "" : rs
								.getString("ID_NEGERIMAHKAMAH"));
				h.put("daerahmahkamah",
						rs.getString("ID_DAERAH_MAHKAMAH") == null ? "" : rs
								.getString("ID_DAERAH_MAHKAMAH"));
				
				h.put("tujuanPindah",
						rs.getString("FLAG_SEBABPINDAHMAHKAMAH") == null ? "" : rs
								.getString("FLAG_SEBABPINDAHMAHKAMAH")); // razman add

				// PK.ID_NEGERIMAHKAMAH, PK.ID_DAERAH_MAHKAMAH "

				h.put("id_Suburusanstatus",
						rs.getString("id_Suburusanstatus") == null ? "" : rs
								.getString("id_Suburusanstatus"));
				h.put("id_Suburusanstatusfail",
						rs.getString("id_Suburusanstatusfail") == null ? ""
								: rs.getString("id_Suburusanstatusfail"));
				h.put("id_Permohonansimati",
						rs.getString("id_Permohonansimati") == null ? "" : rs
								.getString("id_Permohonansimati"));
				h.put("negeritertinggi",
						rs.getString("id_Negeritertinggi") == null ? "" : rs
								.getString("id_Negeritertinggi"));
				h.put("daerahtertinggi",
						rs.getString("id_Daerahtertinggi") == null ? "" : rs
								.getString("id_Daerahtertinggi"));
				h.put("id_taraf_mohon",
						rs.getString("id_Taraf_Mohon") == null ? "" : rs
								.getString("id_Taraf_Mohon"));
				h.put("taraf_pemohon",
						rs.getString("taraf_Pemohon") == null ? "" : rs
								.getString("taraf_Pemohon"));

				h.put("id_permohonankv",
						rs.getString("PERMOHONAN_KV") == null ? "" : rs
								.getString("PERMOHONAN_KV"));
				h.put("txtNamaFirma", rs.getString("NAMA_FIRMA") == null ? ""
						: rs.getString("NAMA_FIRMA"));
				h.put("txtNamaKaveat", rs.getString("NAMA_KAVEAT") == null ? ""
						: rs.getString("NAMA_KAVEAT"));
				h.put("txtNoKaveat", rs.getString("NO_KAVEAT") == null ? ""
						: rs.getString("NO_KAVEAT"));
				h.put("txtAlamat1Peguam", rs.getString("ALAMAT1") == null ? ""
						: rs.getString("ALAMAT1"));
				h.put("txtAlamat2Peguam", rs.getString("ALAMAT2") == null ? ""
						: rs.getString("ALAMAT2"));
				h.put("txtAlamat3Peguam", rs.getString("ALAMAT3") == null ? ""
						: rs.getString("ALAMAT3"));
				h.put("txtPoskodPeguam", rs.getString("POSKOD_KV") == null ? ""
						: rs.getString("POSKOD_KV"));
				h.put("socNegeriPeguam", rs.getString("NEGERI_KV") == null ? ""
						: rs.getString("NEGERI_KV"));
				h.put("txtBandarPeguam", rs.getString("ID_BANDAR") == null ? ""
						: rs.getString("ID_BANDAR"));
				h.put("txtNomborTelefonPeguam",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));
				h.put("txtTarikhKaveat",
						rs.getString("TARIKH_KAVEAT") == null ? "" : sdf
								.format(rs.getDate("TARIKH_KAVEAT")));

				h.put("jenispermohonan",
						rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs
								.getString("FLAG_JENIS_FAIL"));

				listD.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void setCarianFail(String noFail, String namaPemohon,
			String namaSimati, String icSimati, String JenisIc, String userid)
			throws Exception {
		Db db = null;
		list.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String chkDataFail = noFail.trim();
			String chkDataPemohon = namaPemohon.trim();
			String chkDataSimati = namaSimati.trim();
			String chkDataIcSimati = icSimati.trim();
			String chkDataJenisKp = JenisIc;

			sql = "SELECT MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, "
					+ " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL,(SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
					+ " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI ,M.ID_SIMATI "
					+ " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
					+ " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB"
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ userid
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
					
					sql += " ) "
					+ " AND P.ID_STATUS = S.ID_STATUS "
					+ " AND P.ID_FAIL = F.ID_FAIL(+) "
					+ " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) "
					+ " AND P.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN "
					+ " AND M.ID_SIMATI = MS.ID_SIMATI  "
					+ " AND P.ID_PEMOHON = PP.ID_PEMOHON "
					// +" AND (P.ID_STATUS=14 OR P.ID_STATUS=1 OR P.ID_STATUS=50 OR P.ID_STATUS=53 OR P.ID_STATUS=63 OR P.ID_STATUS=56 OR P.ID_STATUS=151 OR P.ID_STATUS=152 ) "
					+ " AND (P.ID_STATUS=14 OR P.ID_STATUS=1 OR P.ID_STATUS=50 OR P.ID_STATUS=53 OR P.ID_STATUS=63 OR P.ID_STATUS=151 OR P.ID_STATUS=152 ) "
					+ " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+ " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS"
					+ " AND STA.AKTIF = '1'"
					+ " AND P.SEKSYEN = '8'"
					+ " AND P.ID_STATUS <> '999' ";

			/*
			 * sql =
			 * "SELECT f.id_Fail, m.id_Simati, f.no_Fail, p.id_Permohonan, p.tarikh_Mohon, kp.id_Keputusanpermohonan,"
			 * +
			 * "kp.keputusan_Permohonan, p.id_Status, s.keterangan, p.tarikh_Masuk, f.tarikh_daftar_fail,"
			 * +
			 * "(select ss.keterangan from Tblrujstatus ss where ss.id_status = kp.keputusan_Permohonan) as status_keputusan,m.nama_simati,f.flag_jenis_fail "
			 * +
			 * "FROM Tblppkpermohonan p, Tblpfdfail f, Tblppkkeputusanpermohonan kp, Tblrujstatus s, Tblppkpemohon pp, Tblppksimati m, Tblppkpermohonansimati ms WHERE "
			 * +
			 * "p.id_Status = s.id_Status AND p.id_Status <> '999'  AND p.id_Fail = f.id_Fail(+) and f.flag_jenis_fail = 1 and p.id_permohonan = kp.id_permohonan(+) AND p.id_Permohonan = ms.id_Permohonan AND m.id_Simati = ms.id_Simati  AND "
			 * +
			 * "p.id_Permohonan = pp.id_Permohonan and (P.ID_STATUS=14 OR P.ID_STATUS=1 OR P.ID_STATUS=50 OR P.ID_STATUS=53 OR P.ID_STATUS=63 OR P.ID_STATUS=151 OR P.ID_STATUS=152 ) "
			 * ;
			 */

			if (noFail != "") {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%"
							+ chkDataFail.toUpperCase() + "%'";
				}
			}
			// NAMA PEMOHON
			if (namaPemohon != "") {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(PP.NAMA_PEMOHON) LIKE '%"
							+ chkDataPemohon.toUpperCase() + "%'";
				}
			}
			// NAMA SIMATI
			if (namaSimati != "") {
				if (!namaSimati.trim().equals("")) {
					sql = sql + " AND UPPER(M.NAMA_SIMATI) LIKE '%"
							+ chkDataSimati.toUpperCase() + "%'";
				}
			}
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
			}
			// sql = sql + " ORDER BY F.ID_FAIL DESC";
			sql = sql + " ORDER BY STA.ID_SUBURUSANSTATUSFAIL DESC";

			// //System.out.println("SQLLLL CARI:"+sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail",
						rs.getString("id_Fail") == null ? "" : rs
								.getString("id_Fail"));
				h.put("no_Fail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				h.put("tarikh_Mohon", rs.getString("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getString("tarikh_Masuk") == null ? ""
						: sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar",
						rs.getDate("tarikh_daftar_fail") == null ? "" : sdf
								.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_simati",
						rs.getString("id_Simati") == null ? "" : rs
								.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_simati") == null ? ""
						: rs.getString("nama_simati"));
				h.put("statuskeputusan",
						rs.getString(12) == null ? "" : rs.getString(12));
				list.addElement(h);
				bil++;
				count++;
			}
			if (count == 0) {
				h = new Hashtable();
				h.put("bil", "");
				h.put("id_Permohonan", "");
				h.put("id_Fail", "");
				h.put("no_Fail", "");
				h.put("tarikh_Mohon", "");
				h.put("tarikhMasuk", "");
				h.put("keterangan", "");
				h.put("id_simati", "");
				h.put("namasimati", "");
				h.put("statuskeputusan", "");
				list.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getList() {
		return list;
	}

	public static void setCarianFail17(String noFail, String namaPemohon,
			String namaSimati, String icSimati, String JenisIc, String userid)
			throws Exception {
		Db db = null;
		list17.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String chkDataFail = noFail.trim();
			String chkDataPemohon = namaPemohon.trim();
			String chkDataSimati = namaSimati.trim();
			String chkDataIcSimati = icSimati.trim();
			String chkDataJenisKp = JenisIc;

			sql = "SELECT MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, "
					+ " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL,(SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
					+ " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI ,M.ID_SIMATI "
					+ " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
					+ " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB"
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ userid
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
					
					sql += " ) "
					+ " AND P.ID_STATUS = S.ID_STATUS "
					+ " AND P.ID_FAIL = F.ID_FAIL(+) "
					+ " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) "
					+ " AND P.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN "
					+ " AND M.ID_SIMATI = MS.ID_SIMATI  "
					+ " AND P.ID_PEMOHON = PP.ID_PEMOHON "
					// +" AND (P.ID_STATUS=14 OR P.ID_STATUS=1 OR P.ID_STATUS=50 OR P.ID_STATUS=53 OR P.ID_STATUS=63 OR P.ID_STATUS=56 OR P.ID_STATUS=151 OR P.ID_STATUS=152 ) "
					+ " AND (P.ID_STATUS=14 OR P.ID_STATUS=1 OR P.ID_STATUS=50 OR P.ID_STATUS=53 OR P.ID_STATUS=63 OR P.ID_STATUS=151 OR P.ID_STATUS=152 ) "
					+ " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+ " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS"
					+ " AND STA.AKTIF = '1'"
					+ " AND P.SEKSYEN = '17'"
					+ " AND P.ID_STATUS <> '999' ";

			/*
			 * sql =
			 * "SELECT f.id_Fail, m.id_Simati, f.no_Fail, p.id_Permohonan, p.tarikh_Mohon, kp.id_Keputusanpermohonan,"
			 * +
			 * "kp.keputusan_Permohonan, p.id_Status, s.keterangan, p.tarikh_Masuk, f.tarikh_daftar_fail,"
			 * +
			 * "(select ss.keterangan from Tblrujstatus ss where ss.id_status = kp.keputusan_Permohonan) as status_keputusan,m.nama_simati,f.flag_jenis_fail "
			 * +
			 * "FROM Tblppkpermohonan p, Tblpfdfail f, Tblppkkeputusanpermohonan kp, Tblrujstatus s, Tblppkpemohon pp, Tblppksimati m, Tblppkpermohonansimati ms WHERE "
			 * +
			 * "p.id_Status = s.id_Status AND p.id_Status <> '999'  AND p.id_Fail = f.id_Fail(+) and f.flag_jenis_fail = 1 and p.id_permohonan = kp.id_permohonan(+) AND p.id_Permohonan = ms.id_Permohonan AND m.id_Simati = ms.id_Simati  AND "
			 * +
			 * "p.id_Permohonan = pp.id_Permohonan and (P.ID_STATUS=14 OR P.ID_STATUS=1 OR P.ID_STATUS=50 OR P.ID_STATUS=53 OR P.ID_STATUS=63 OR P.ID_STATUS=151 OR P.ID_STATUS=152 ) "
			 * ;
			 */

			if (noFail != "") {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%"
							+ chkDataFail.toUpperCase() + "%'";
				}
			}
			// NAMA PEMOHON
			if (namaPemohon != "") {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(PP.NAMA_PEMOHON) LIKE '%"
							+ chkDataPemohon.toUpperCase() + "%'";
				}
			}
			// NAMA SIMATI
			if (namaSimati != "") {
				if (!namaSimati.trim().equals("")) {
					sql = sql + " AND UPPER(M.NAMA_SIMATI) LIKE '%"
							+ chkDataSimati.toUpperCase() + "%'";
				}
			}
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
			}
			// sql = sql + " ORDER BY F.ID_FAIL DESC";
			sql = sql + " ORDER BY STA.ID_SUBURUSANSTATUSFAIL DESC";

			System.out.println("SQLLLL CARI:" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail",
						rs.getString("id_Fail") == null ? "" : rs
								.getString("id_Fail"));
				h.put("no_Fail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				h.put("tarikh_Mohon", rs.getString("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getString("tarikh_Masuk") == null ? ""
						: sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar",
						rs.getDate("tarikh_daftar_fail") == null ? "" : sdf
								.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_simati",
						rs.getString("id_Simati") == null ? "" : rs
								.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_simati") == null ? ""
						: rs.getString("nama_simati"));
				h.put("statuskeputusan",
						rs.getString(12) == null ? "" : rs.getString(12));
				list17.addElement(h);
				bil++;
				count++;
			}
			if (count == 0) {
				h = new Hashtable();
				h.put("bil", "");
				h.put("id_Permohonan", "");
				h.put("id_Fail", "");
				h.put("no_Fail", "");
				h.put("tarikh_Mohon", "");
				h.put("tarikhMasuk", "");
				h.put("keterangan", "");
				h.put("id_simati", "");
				h.put("namasimati", "");
				h.put("statuskeputusan", "");
				list17.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getList17() {
		return list17;
	}

	private static Vector listNegeri = new Vector();

	public static void setListNegeri() throws Exception {
		Db db = null;
		listNegeri.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("n.id_negeri");
			r.add("n.nama_negeri");

			r.add("p.id_negeri", r.unquote("n.id_negeri"));

			sql = r.getSQLSelect("tblrujpejabat p, tblrujnegeri n");
			sql = sql
					+ " and p.id_jenispejabat = 8 group by n.id_negeri, n.nama_negeri";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idNegeri",
						rs.getString("id_negeri") == null ? "" : rs
								.getString("id_negeri"));
				h.put("namaNegeri", rs.getString("nama_negeri") == null ? ""
						: rs.getString("nama_negeri"));
				listNegeri.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getListNegeri() {
		return listNegeri;
	}

	private static Vector listDaerahall = new Vector();

	public static void setListDaerahall() throws Exception {
		Db db = null;
		listDaerahall.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("d.id_daerah");
			r.add("d.nama_daerah");

			r.add("p.id_negeri", r.unquote("n.id_negeri"));
			r.add("p.id_daerah", r.unquote("d.id_daerah"));

			sql = r.getSQLSelect("tblrujpejabat p, tblrujnegeri n, tblrujdaerah d");
			sql = sql
					+ " and p.id_jenispejabat = 8 group by d.id_daerah, d.nama_daerah";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idDaerah",
						rs.getString("id_daerah") == null ? "" : rs
								.getString("id_daerah"));
				h.put("namaDaerah", rs.getString("nama_daerah") == null ? ""
						: rs.getString("nama_daerah"));
				listDaerahall.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getListDaerahall() {
		return listDaerahall;
	}

	private static Vector listDaerah = new Vector();

	public static void setListDaerah(String idnegeri) throws Exception {
		Db db = null;
		listDaerah.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("d.id_daerah");
			r.add("d.nama_daerah");

			r.add("p.id_negeri", r.unquote("n.id_negeri"));
			r.add("p.id_daerah", r.unquote("d.id_daerah"));

			sql = r.getSQLSelect("tblrujpejabat p, tblrujnegeri n, tblrujdaerah d");
			sql = sql + " and p.id_jenispejabat = 8 and p.id_negeri = "
					+ idnegeri + " group by d.id_daerah, d.nama_daerah";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idDaerah",
						rs.getString("id_daerah") == null ? "" : rs
								.getString("id_daerah"));
				h.put("namaDaerah", rs.getString("nama_daerah") == null ? ""
						: rs.getString("nama_daerah"));
				listDaerah.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getListDaerah() {
		return listDaerah;
	}

	private static Vector list_status_nilai = new Vector();

	public static void setlist_status_nilai(String idpermohonan,
			String suburusan) throws Exception {
		Db db = null;
		list_status_nilai.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ID_SUBURUSANSTATUSFAIL,ID_PERMOHONAN,ID_SUBURUSANSTATUS,TARIKH_MASUK "
					+ "FROM TBLRUJSUBURUSANSTATUSFAIL "
					+ "WHERE ID_SUBURUSANSTATUS = '"
					+ suburusan
					+ "' "
					+ "AND ID_PERMOHONAN = '"
					+ idpermohonan
					+ "' "
					+ "AND TARIKH_MASUK = (SELECT MAX(TARIKH_MASUK) FROM TBLRUJSUBURUSANSTATUSFAIL "
					+ "WHERE ID_SUBURUSANSTATUS = '"
					+ suburusan
					+ "' AND ID_PERMOHONAN = '" + idpermohonan + "')";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_SUBURUSANSTATUSFAIL",
						rs.getString("ID_SUBURUSANSTATUSFAIL") == null ? ""
								: rs.getString("ID_SUBURUSANSTATUSFAIL"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_SUBURUSANSTATUS",
						rs.getString("ID_SUBURUSANSTATUS") == null ? "" : rs
								.getString("ID_SUBURUSANSTATUS"));
				h.put("TARIKH_MASUK", rs.getString("TARIKH_MASUK") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MASUK")));

				list_status_nilai.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getlist_status_nilai() {
		return list_status_nilai;
	}

	private static Vector list_status_tunggu = new Vector();

	public static void setlist_status_tunggu(String idpermohonan,
			String suburusan) throws Exception {
		Db db = null;
		list_status_tunggu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ID_SUBURUSANSTATUSFAIL,ID_PERMOHONAN,ID_SUBURUSANSTATUS,TARIKH_MASUK "
					+ "FROM TBLRUJSUBURUSANSTATUSFAIL "
					+ "WHERE ID_SUBURUSANSTATUS = '"
					+ suburusan
					+ "' "
					+ "AND ID_PERMOHONAN = '"
					+ idpermohonan
					+ "' "
					+ "AND TARIKH_MASUK = (SELECT MAX(TARIKH_MASUK) FROM TBLRUJSUBURUSANSTATUSFAIL "
					+ "WHERE ID_SUBURUSANSTATUS = '"
					+ suburusan
					+ "' AND ID_PERMOHONAN = '" + idpermohonan + "')";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_SUBURUSANSTATUSFAIL",
						rs.getString("ID_SUBURUSANSTATUSFAIL") == null ? ""
								: rs.getString("ID_SUBURUSANSTATUSFAIL"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_SUBURUSANSTATUS",
						rs.getString("ID_SUBURUSANSTATUS") == null ? "" : rs
								.getString("ID_SUBURUSANSTATUS"));
				h.put("TARIKH_MASUK", rs.getString("TARIKH_MASUK") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MASUK")));

				list_status_tunggu.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getlist_status_tunggu() {
		return list_status_tunggu;
	}

}



