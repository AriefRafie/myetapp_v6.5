package ekptg.model.ppt;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.EkptgCache;


public class FrmSementaraSiasatanData extends EkptgCache implements
		Serializable {

	static Logger myLogger = Logger.getLogger(FrmSementaraSiasatanData.class);
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
	public static final String SEQ_TABLE = "TBLRUJSEQFAIL";

	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String cd = dateFormat.format(date);

	
	Vector list_kementerian = null;

	public Vector list_kementerian() throws Exception {
		list_kementerian = new Vector();
		Vector key = list_kementerian;
		Element cachedObject = myCache.get(key);
		myLogger.info("CACHED KEMENTERIAN :" + myCache.get(key));
		if (cachedObject != null) {
			myLogger.info("CACHED L1");
			return (Vector) cachedObject.getObjectValue();
		} else {
			myLogger.info("CACHED L2");
			Db db = null;
			list_kementerian.clear();
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT ID_KEMENTERIAN, KOD_KEMENTERIAN, NAMA_KEMENTERIAN FROM TBLRUJKEMENTERIAN ORDER BY KOD_KEMENTERIAN ASC ";
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("ID_KEMENTERIAN",
							rs.getString("ID_KEMENTERIAN") == null ? "" : rs
									.getString("ID_KEMENTERIAN"));
					h
							.put("KOD_KEMENTERIAN", rs
									.getString("KOD_KEMENTERIAN") == null ? ""
									: rs.getString("KOD_KEMENTERIAN")
											.toUpperCase());
					h.put("NAMA_KEMENTERIAN",
							rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
									.getString("NAMA_KEMENTERIAN")
									.toUpperCase());
					list_kementerian.addElement(h);
				}

				myCache.put(new Element(key, list_kementerian));
				return list_kementerian;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	Vector list_urusan = null;

	public Vector list_urusan() throws Exception {
		list_urusan = new Vector();
		Db db = null;
		list_urusan.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_SUBURUSAN,KOD_SUBURUSAN,NAMA_SUBURUSAN FROM TBLRUJSUBURUSAN WHERE ID_SEKSYEN = 1";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("KOD_SUBURUSAN",
						rs.getString("KOD_SUBURUSAN") == null ? "" : rs
								.getString("KOD_SUBURUSAN").toUpperCase());
				h.put("NAMA_SUBURUSAN",
						rs.getString("NAMA_SUBURUSAN") == null ? "" : rs
								.getString("NAMA_SUBURUSAN").toUpperCase());
				list_urusan.addElement(h);
			}
			return list_urusan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	Vector list_pejabat = null;

	public Vector list_pejabat() throws Exception {
		list_pejabat = new Vector();
		Db db = null;
		list_pejabat.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PEJABATJKPTG,NAMA_PEJABAT,"+
					" CASE "+
					" WHEN ID_JENISPEJABAT = '22' THEN 'UPT' "+
					" WHEN ID_JENISPEJABAT = '21' THEN 'SPT '  "+  
					" END AS JENIS_PEJABAT "+
					" FROM TBLRUJPEJABATJKPTG "+
					" WHERE ID_JENISPEJABAT IN ('22','21') "+
					" AND ID_SEKSYEN = 1";
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_PEJABATJKPTG", rs.getString("ID_PEJABATJKPTG") == null ? ""
						: rs.getString("ID_PEJABATJKPTG"));
				h.put("NAMA_PEJABAT",
						rs.getString("NAMA_PEJABAT") == null ? "" : rs
								.getString("NAMA_PEJABAT").toUpperCase());
				h.put("JENIS_PEJABAT",
						rs.getString("JENIS_PEJABAT") == null ? "" : rs
								.getString("JENIS_PEJABAT").toUpperCase());
				list_pejabat.addElement(h);
			}
			return list_pejabat;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector list_status = null;

	public Vector list_status() throws Exception {
		list_status = new Vector();
		Db db = null;
		list_status.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_STATUS,KOD_STATUS,KETERANGAN FROM TBLRUJSTATUS WHERE ID_SEKSYEN = 1 ORDER BY KOD_STATUS ASC";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS"));
				h.put("KOD_STATUS", rs.getString("KOD_STATUS") == null ? ""
						: rs.getString("KOD_STATUS").toUpperCase());
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());
				list_status.addElement(h);
			}
			return list_status;
		} finally {
			if (db != null)
				db.close();
		}
	}
	

	Vector senarai_penarikan_carian = null;

	public Vector senarai_penarikan_carian(String no_fail,
			String no_jkptg_negeri, String id_kementerian, String id_urusan,
			String id_status, String jenis_permohon,String role,String user_negeri) throws Exception {
		senarai_penarikan_carian = new Vector();
		Db db = null;
		senarai_penarikan_carian.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT P.ID_PERMOHONAN,P.TARIKH_PERMOHONAN,"
					+ " F.NO_FAIL, P.NO_RUJUKAN_UPT, P.NO_RUJUKAN_PTG, P.NO_RUJUKAN_PTD, "
					+ " K.NAMA_KEMENTERIAN,D.NAMA_DAERAH,S.KETERANGAN,U.NAMA_SUBURUSAN  FROM TBLPPTPERMOHONAN P, "
					+ " TBLRUJSUBURUSAN U,TBLPFDFAIL F,TBLRUJKEMENTERIAN K,TBLRUJDAERAH D,"
					+ " TBLRUJSTATUS S "
					+ " WHERE  P.ID_FAIL = F.ID_FAIL(+) "
					+ " AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) "
					+ " AND P.ID_DAERAH = D.ID_DAERAH(+) "
					+ " AND F.ID_SUBURUSAN = '53' "
					+ " AND P.FLAG_JENISPERMOHONAN = '"
					+ jenis_permohon
					+ "' "					
					+ " AND F.ID_SUBURUSAN = U.ID_SUBURUSAN(+) "

					+" AND S.ID_STATUS IN (38,48,62) "
					
					
					+ " AND P.ID_STATUS = S.ID_STATUS " +
							"";
			    if(!user_negeri.equals("16"))
			    {
				sql += "AND  F.ID_NEGERI = '"+user_negeri+"' ";
			    }
			    

			// kena filter by status (sudah diwartakan)

			    if (no_fail != "") {
					if (!no_fail.trim().equals("")) {
						sql += " AND (UPPER(F.NO_FAIL) LIKE '%" + no_fail.toUpperCase().trim() + "%' OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%" + no_fail.toUpperCase().trim() + "%' OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%" + no_fail.toUpperCase().trim() + "%')";
					}
				}
			if (no_jkptg_negeri != "") {
				if (!no_jkptg_negeri.trim().equals("")) {
					sql = sql + " AND UPPER(P.NO_RUJUKAN_UPT) LIKE '%"
							+ no_jkptg_negeri.toUpperCase().trim() + "%'";
				}
			}
			if (id_urusan != "") {
				if (!id_urusan.trim().equals("")) {
					sql = sql + " AND UPPER(F.ID_SUBURUSAN) LIKE '"
							+ id_urusan.toUpperCase() + "'";
				}
			}
			if (id_kementerian != "") {
				if (!id_kementerian.trim().equals("")) {
					sql = sql + " AND UPPER(F.ID_KEMENTERIAN) LIKE '"
							+ id_kementerian.toUpperCase() + "'";
				}
			}
			if (id_status != "") {
				if (!id_status.trim().equals("")) {
					sql = sql + " AND UPPER(P.ID_STATUS) LIKE '"
							+ id_status.toUpperCase() + "'";
				}
			}

			sql += " ORDER BY P.TARIKH_KEMASKINI DESC";

			myLogger.info("SQL SEMENTARA DEFAULT :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG")== null?"":rs.getString("NO_RUJUKAN_PTG"));
		    	h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD")== null?"":rs.getString("NO_RUJUKAN_PTD"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
		    	h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_PERMOHONAN")));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "default"
						: rs.getString("NO_FAIL").toUpperCase());
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT").toUpperCase());
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());
				h.put("URUSAN", rs.getString("NAMA_SUBURUSAN") == null ? ""
						: rs.getString("NAMA_SUBURUSAN").toUpperCase());
				senarai_penarikan_carian.addElement(h);
			}
			return senarai_penarikan_carian;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_siasatan_penarikan = null;

	public Vector list_siasatan_penarikan(String idpermohonan) throws Exception {
		list_siasatan_penarikan = new Vector();
		list_siasatan_penarikan.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT S.ID_PEGAWAI_SIASATAN," +
					//"HM.NO_LOT," +
			
			/*
			" CASE "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL THEN HM.NO_LOT "+
			" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NULL THEN JL.KETERANGAN || HM.NO_PT "+
			" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || HM.NO_PT  "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL THEN HM.NO_LOT || CHR(32) || CHR(40) || JL.KETERANGAN  || CHR(32) || HM.NO_PT || CHR(41) "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "+
			*/
			
			
			" CASE "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL THEN HM.NO_LOT "+
			" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || HM.NO_PT  "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL THEN JL.KETERANGAN  || HM.NO_PT || CHR(32) || CHR(40) || HM.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "+
			
			
					"HM.NO_PT,PG.USER_NAME AS NAMA_PEGAWAI,S.ULASAN_SIASATAN,PB.ID_PERMOHONAN,S.ID_HAKMILIK,S.BIL_SIASATAN,S.ID_SIASATAN,S.NO_KES,S.NO_SIASATAN,S.STATUS_SIASATAN,S.TARIKH_SIASATAN,S.MASA_SIASATAN, "
					+ " S.TEMPAT,S.ALAMAT1,S.ALAMAT2,S.ALAMAT3,S.POSKOD,S.ID_NEGERI,S.ALASAN_TANGGUH,S.NILAIAN_JPPH,S.ID_UNITLUAS,S.BANTAHAN_TUANTNH,S.BANTAHAN_AGENSI, "
					+ " S.BANTAHAN_LAIN,S.TEMPOH_MILIK_TANAH,S.CARA_MILIK,S.HARGA_BELI,S.JENIS_BANGUNAN,S.JENIS_TANAMAN,S.FLAG_PECAH_SEMPADAN,S.FLAG_TUKAR_SYARAT, "
					+ " S.TARIKH_PECAH_SEMPADAN,S.TARIKH_TUKAR_SYARAT,S.STATUS_SEMASA,S.BEBANAN,S.KETERANGAN_TUAN_TANAH,S.KETERANGAN_AGENSI,S.KETERANGAN_JURUNILAI, "
					+ " S.TUNTUTAN_TUANTNH,S.TUNTUTAN_PB_BEBANAN,S.TUNTUTAN_PB_TDKDAFTAR,S.TUNTUTAN_PB_LAIN,S.ID_BORANGE,S.PERINTAH,S.TARIKH_AKHIR_TAMPAL, "
					+ " S.ID_PEGAWAI_SIASATAN,S.STATUS_TUNTUTAN,S.ID_BANDAR,S.JENIS_WAKTU_SIASATAN "
					+ " FROM TBLPPTSIASATAN S,TBLPPTHAKMILIK HM,"
					+ "TBLPPTPERMOHONAN PB,USERS PG,TBLRUJLOT JL  "
					+ " WHERE S.ID_PERMOHONAN = PB.ID_PERMOHONAN "
				//	+ " AND HM.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
				//	+" AND PH.ID_HAKMILIK = HM.ID_HAKMILIK " 
				//	+" AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
					+ " AND S.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_LOT = JL.ID_LOT(+)"
					+ " AND S.ID_PEGAWAI_SIASATAN = PG.USER_ID(+)"
					+" AND PB.ID_PERMOHONAN = '"+idpermohonan+"'"
					+" ORDER BY NO_LOT ASC,BIL_SIASATAN ASC ";

			myLogger.info("list_siasatan_penarikan" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);

			int bil = 0;
			String temp = "";
			while (rs.next()) {
				bil = bil + 1;
				temp = bil + "a";

				Hashtable h = new Hashtable();
				h.put("BIL", bil);
				h.put("BILTEMP", temp);
				h.put("BILDUM", bil + "dum");
				h.put("ID_PEGAWAI_SIASATAN", rs
						.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
						.getString("ID_PEGAWAI_SIASATAN"));
				h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));

				h.put("ID_PENARIKANBALIK",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
						: rs.getString("ID_SIASATAN"));
				h.put("NO_KES", rs.getString("NO_KES") == null ? "" : rs
						.getString("NO_KES"));
				h.put("NO_SIASATAN", rs.getString("NO_SIASATAN") == null ? ""
						: rs.getString("NO_SIASATAN"));
				h.put("STATUS_SIASATAN",
						rs.getString("STATUS_SIASATAN") == null ? "" : rs
								.getString("STATUS_SIASATAN"));
				h.put("MASA_SIASATAN",
						rs.getString("MASA_SIASATAN") == null ? "" : rs
								.getString("MASA_SIASATAN"));
				h.put("TARIKH_SIASATAN",
						rs.getString("TARIKH_SIASATAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_SIASATAN")));
				h.put("TEMPAT", rs.getString("TEMPAT") == null ? "" : rs
						.getString("TEMPAT"));
				h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1"));
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2"));
				h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
						.getString("POSKOD"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI"));
				h.put("ALASAN_TANGGUH",
						rs.getString("ALASAN_TANGGUH") == null ? "" : rs
								.getString("ALASAN_TANGGUH"));
				h.put("ID_UNITLUAS", rs.getString("ID_UNITLUAS") == null ? ""
						: rs.getString("ID_UNITLUAS"));
				h.put("BANTAHAN_TUANTNH",
						rs.getString("BANTAHAN_TUANTNH") == null ? "" : rs
								.getString("BANTAHAN_TUANTNH"));
				h.put("BANTAHAN_AGENSI",
						rs.getString("BANTAHAN_AGENSI") == null ? "" : rs
								.getString("BANTAHAN_AGENSI"));
				h.put("NILAIAN_JPPH", rs.getString("NILAIAN_JPPH") == null ? ""
						: Format.format(rs.getDouble("NILAIAN_JPPH")));
				h.put("BANTAHAN_LAIN",
						rs.getString("BANTAHAN_LAIN") == null ? "" : rs
								.getString("BANTAHAN_LAIN"));
				h.put("TEMPOH_MILIK_TANAH",
						rs.getString("TEMPOH_MILIK_TANAH") == null ? "" : rs
								.getString("TEMPOH_MILIK_TANAH"));
				h.put("CARA_MILIK", rs.getString("CARA_MILIK") == null ? ""
						: rs.getString("CARA_MILIK"));
				h.put("JENIS_BANGUNAN",
						rs.getString("JENIS_BANGUNAN") == null ? "" : rs
								.getString("JENIS_BANGUNAN"));
				h.put("JENIS_TANAMAN",
						rs.getString("JENIS_TANAMAN") == null ? "" : rs
								.getString("JENIS_TANAMAN"));
				h.put("FLAG_PECAH_SEMPADAN", rs
						.getString("FLAG_PECAH_SEMPADAN") == null ? "" : rs
						.getString("FLAG_PECAH_SEMPADAN"));
				h.put("FLAG_TUKAR_SYARAT",
						rs.getString("FLAG_TUKAR_SYARAT") == null ? "" : rs
								.getString("FLAG_TUKAR_SYARAT"));
				h.put("HARGA_BELI", rs.getString("HARGA_BELI") == null ? ""
						: rs.getDouble("HARGA_BELI"));
				h.put("STATUS_SEMASA",
						rs.getString("STATUS_SEMASA") == null ? "" : rs
								.getString("STATUS_SEMASA"));
				h.put("BEBANAN", rs.getString("BEBANAN") == null ? "" : rs
						.getString("BEBANAN"));
				h.put("KETERANGAN_TUAN_TANAH", rs
						.getString("KETERANGAN_TUAN_TANAH") == null ? "" : rs
						.getString("KETERANGAN_TUAN_TANAH"));
				h.put("KETERANGAN_AGENSI",
						rs.getString("KETERANGAN_AGENSI") == null ? "" : rs
								.getString("KETERANGAN_AGENSI"));
				h.put("KETERANGAN_JURUNILAI", rs
						.getString("KETERANGAN_JURUNILAI") == null ? "" : rs
						.getString("KETERANGAN_JURUNILAI"));
				h.put("TARIKH_PECAH_SEMPADAN", rs
						.getString("TARIKH_PECAH_SEMPADAN") == null ? ""
						: Format.format(rs.getDate("TARIKH_PECAH_SEMPADAN")));
				h.put("TARIKH_TUKAR_SYARAT", rs
						.getString("TARIKH_TUKAR_SYARAT") == null ? "" : Format
						.format(rs.getDate("TARIKH_TUKAR_SYARAT")));
				h.put("TARIKH_AKHIR_TAMPAL", rs
						.getString("TARIKH_AKHIR_TAMPAL") == null ? "" : Format
						.format(rs.getDate("TARIKH_AKHIR_TAMPAL")));
				h.put("TUNTUTAN_TUANTNH",
						rs.getString("TUNTUTAN_TUANTNH") == null ? "" : rs
								.getString("TUNTUTAN_TUANTNH"));
				h.put("TUNTUTAN_PB_BEBANAN", rs
						.getString("TUNTUTAN_PB_BEBANAN") == null ? "" : rs
						.getString("TUNTUTAN_PB_BEBANAN"));
				h.put("TUNTUTAN_PB_TDKDAFTAR", rs
						.getString("TUNTUTAN_PB_TDKDAFTAR") == null ? "" : rs
						.getString("TUNTUTAN_PB_TDKDAFTAR"));
				h.put("TUNTUTAN_PB_LAIN",
						rs.getString("TUNTUTAN_PB_LAIN") == null ? "" : rs
								.getString("TUNTUTAN_PB_LAIN"));
				h.put("ID_BORANGE", rs.getString("ID_BORANGE") == null ? ""
						: rs.getString("ID_BORANGE"));
				h.put("PERINTAH", rs.getString("PERINTAH") == null ? "" : rs
						.getString("PERINTAH"));
				h.put("ID_PEGAWAI_SIASATAN", rs
						.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
						.getString("ID_PEGAWAI_SIASATAN"));
				h.put("STATUS_TUNTUTAN",
						rs.getString("STATUS_TUNTUTAN") == null ? "" : rs
								.getString("STATUS_TUNTUTAN"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
						.getString("ID_BANDAR"));
				h.put("JENIS_WAKTU_SIASATAN", rs
						.getString("JENIS_WAKTU_SIASATAN") == null ? "" : rs
						.getString("JENIS_WAKTU_SIASATAN"));
				h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN") == null ? "1"
						: rs.getString("BIL_SIASATAN"));
				h.put("ULASAN_SIASATAN",
						rs.getString("ULASAN_SIASATAN") == null ? "" : rs
								.getString("ULASAN_SIASATAN"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("NO_PT", rs.getString("NO_PT") == null ? "" : rs
						.getString("NO_PT"));

				list_siasatan_penarikan.addElement(h);
			}
			return list_siasatan_penarikan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector header = null;

	public Vector content_header(String id_permohonan) throws Exception {
		header = new Vector();
		Db db = null;
		header.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT SF.ID_SUBURUSANSTATUSFAIL,SF.AKTIF,SF.ID_SUBURUSANSTATUS,P.ID_PERMOHONAN, F.ID_NEGERI, P.ID_NEGERI AS ID_NEGERI_PROJECT, P.NO_PERMOHONAN, "
					+ "P.ID_FAIL,  F.NO_FAIL, F.ID_SUBURUSAN, P.TARIKH_PERMOHONAN, P.ID_STATUS,  F.ID_KEMENTERIAN, "
					+ "P.ID_AGENSI, P.FLAG_PERUNTUKAN, P.FLAG_SEGERA,  P.ID_DAERAH,  Initcap(P.TUJUAN) AS TUJUAN, P.NO_RUJUKAN_SURAT, "
					+ "P.TARIKH_KEHENDAKI,  P.ALAMAT1, P.ALAMAT2, P.ALAMAT3, P.POSKOD, P.ID_MUKIM, "
					+ "Initcap(K.NAMA_KEMENTERIAN) as NAMA_KEMENTERIAN, Initcap(B.NAMA_DAERAH) as NAMA_DAERAH, P.NO_RUJUKAN_PTD, P.NO_RUJUKAN_PTG, "
					+ "P.NO_RUJUKAN_UPT, SU.NAMA_SUBURUSAN, S.KETERANGAN, C.ID_NEGERI,C.NAMA_NEGERI,P.TARIKH_TERIMA,AG.NAMA_AGENSI "
					+ "FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLRUJKEMENTERIAN K,TBLRUJNEGERI C,  "
					+ "TBLRUJDAERAH B,TBLRUJSUBURUSAN SU,TBLRUJSUBURUSANSTATUSFAIL SF,TBLRUJSTATUS S, TBLRUJAGENSI AG  "
					+ "WHERE "
					+ "P.ID_PERMOHONAN = '"
					+ id_permohonan
					+ "' "
					+ "AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) "
					+ "AND P.ID_FAIL = F.ID_FAIL(+) "
					+ "AND SF.ID_FAIL(+) = F.ID_FAIL "
					+ "AND P.ID_DAERAH = B.ID_DAERAH(+) "
					+ "AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN(+) "
					+ "AND P.ID_STATUS = S.ID_STATUS "
					+ "AND F.ID_NEGERI = C.ID_NEGERI(+) "
					+ "AND P.ID_AGENSI = AG.ID_AGENSI(+)";

			myLogger.info("SQL setHeader :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				//SF.ID_SUBURUSANSTATUSFAIL,SF.AKTIV,SF.ID_SUBURUSANSTATUS,
				h.put("ID_SUBURUSANSTATUSFAIL",
						rs.getString("ID_SUBURUSANSTATUSFAIL") == null ? "" : rs
								.getString("ID_SUBURUSANSTATUSFAIL"));
				h.put("AKTIF",
						rs.getString("AKTIF") == null ? "" : rs
								.getString("AKTIF"));
				
				h.put("ID_SUBURUSANSTATUS",
						rs.getString("ID_SUBURUSANSTATUS") == null ? "" : rs
								.getString("ID_SUBURUSANSTATUS"));
				
				h.put("ID_NEGERI",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				
				h.put("id_permohonan",
						rs.getString("id_permohonan") == null ? "" : rs
								.getString("id_permohonan"));
				
				
				h.put("id_permohonan",
						rs.getString("id_permohonan") == null ? "" : rs
								.getString("id_permohonan"));
				h.put("no_permohonan",
						rs.getString("no_permohonan") == null ? "-" : rs
								.getString("no_permohonan").toUpperCase());
				h.put("id_fail", rs.getString("id_fail") == null ? "" : rs
						.getString("id_fail").toUpperCase());
				h.put("id_status", rs.getString("id_status"));
				h.put("no_fail", rs.getString("no_fail") == null ? "-" : rs
						.getString("no_fail").toUpperCase());
				h.put("nama_kementerian",
						rs.getString("nama_kementerian") == null ? "-" : rs
								.getString("nama_kementerian").toUpperCase());
				h.put("nama_kementerian1",
						rs.getString("nama_kementerian") == null ? "-" : rs
								.getString("nama_kementerian"));
				h.put("nama_daerah", rs.getString("nama_daerah") == null ? "-"
						: rs.getString("nama_daerah").toUpperCase());
				h.put("nama_daerah1", rs.getString("nama_daerah") == null ? "-"
						: rs.getString("nama_daerah"));
				h.put("no_rujukan_ptd",
						rs.getString("no_rujukan_ptd") == null ? "-" : rs
								.getString("no_rujukan_ptd").toUpperCase());
				h.put("no_rujukan_ptg",
						rs.getString("no_rujukan_ptg") == null ? "-" : rs
								.getString("no_rujukan_ptg").toUpperCase());
				h.put("no_rujukan_upt",
						rs.getString("no_rujukan_upt") == null ? "-" : rs
								.getString("no_rujukan_upt").toUpperCase());
				h.put("nama_suburusan",
						rs.getString("nama_suburusan") == null ? "-" : rs
								.getString("nama_suburusan").toUpperCase());
				h.put("keterangan", rs.getString("keterangan") == null ? "-"
						: rs.getString("keterangan").toUpperCase());
				h.put("nama_agensi", rs.getString("nama_agensi") == null ? "-"
						: rs.getString("nama_agensi").toUpperCase());
				h.put("projek_negeri",
						rs.getString("nama_negeri") == null ? "-" : rs
								.getString("nama_negeri").toUpperCase());
				if (rs.getString("id_suburusan") == null) {
					h.put("id_suburusan", "");
				} else {
					h.put("id_suburusan", rs.getString("id_suburusan"));
				}
				h
						.put("tarikh_permohonan",
								rs.getString("tarikh_permohonan") == null ? "-"
										: Format.format(rs
												.getDate("tarikh_permohonan")));
				h.put("tarikh_terima",
						rs.getString("tarikh_terima") == null ? "-" : Format
								.format(rs.getDate("tarikh_terima")));
				h.put("id_status", rs.getString("id_status") == null ? "" : rs
						.getString("id_status"));
				if (rs.getString("id_kementerian") == null) {
					h.put("id_kementerian", "");
				} else {
					h.put("id_kementerian", rs.getString("id_kementerian"));
				}
				if (rs.getString("id_agensi") == null) {
					h.put("id_agensi", "");
				} else {
					h.put("id_agensi", rs.getString("id_agensi"));
				}
				h.put("flag_peruntukan",
						rs.getString("flag_peruntukan") == null ? "" : rs
								.getString("flag_peruntukan"));
				h.put("flag_segera", rs.getString("flag_segera") == null ? ""
						: rs.getString("flag_segera"));
				if (rs.getString("ID_NEGERI_PROJECT") == null) {
					h.put("id_negeri_projek", "");
				} else {
					h
							.put("id_negeri_projek", rs
									.getString("ID_NEGERI_PROJECT"));
				}
				if (rs.getString("id_daerah") == null) {
					h.put("id_daerah", "");
				} else {
					h.put("id_daerah", rs.getString("id_daerah"));
				}
				h.put("tujuan", rs.getString("tujuan") == null ? "-" : rs
						.getString("tujuan").toUpperCase());
				h.put("tujuan_mmk", rs.getString("tujuan") == null ? "-" : rs
						.getString("tujuan"));
				h.put("no_rujukan_surat",
						rs.getString("no_rujukan_surat") == null ? "-" : rs
								.getString("no_rujukan_surat").toUpperCase());
				h.put("tarikh_kehendaki",
						rs.getString("tarikh_kehendaki") == null ? "-" : Format
								.format(rs.getDate("tarikh_kehendaki")));
				h.put("alamat1", rs.getString("alamat1") == null ? "" : rs
						.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2") == null ? "" : rs
						.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3") == null ? "" : rs
						.getString("alamat3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				if (rs.getString("id_negeri") == null) {
					h.put("id_negeri", "");
				} else {
					h.put("id_negeri", rs.getString("id_negeri"));
				}
				if (rs.getString("id_mukim") == null) {
					h.put("id_mukim", "");
				} else {
					h.put("id_mukim", rs.getString("id_mukim"));
				}
				header.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return header;
	}

	
	Vector list_negeri = null;

	public Vector getListnegeri() throws Exception {
		list_negeri = new Vector();
		list_negeri.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT ID_NEGERI,KOD_MAMPU,NAMA_NEGERI,KOD_NEGERI FROM TBLRUJNEGERI ORDER BY KOD_MAMPU ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_NEGERI", rs.getString("ID_NEGERI"));

				if (rs.getString("NAMA_NEGERI") == null) {
					h.put("NAMA_NEGERI", "");
				} else {
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI")
							.toUpperCase());
				}
				if (rs.getString("KOD_NEGERI") == null) {
					h.put("KOD_NEGERI", "");
				} else {
					h
							.put("KOD_NEGERI", rs.getString("KOD_MAMPU")
									.toUpperCase());
				}

				list_negeri.addElement(h);
			}
			return list_negeri;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_jenispb = null;

	public Vector list_jenispb() throws Exception {
		list_jenispb = new Vector();
		list_jenispb.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_JENISPB");
			r.add("KOD_JENIS_PB");
			r.add("KETERANGAN");
			r.add("JENIS_DAFTAR_PB");

			// sql = r.getSQLSelect("Tblrujnegeri", "KOD_NEGERI");
			sql = "SELECT ID_JENISPB,KOD_JENIS_PB,KETERANGAN,JENIS_DAFTAR_PB FROM TBLRUJJENISPB ORDER BY KOD_JENIS_PB ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_JENISPB", rs.getInt("ID_JENISPB"));

				if (rs.getString("KETERANGAN") == null) {
					h.put("KETERANGAN", "");
				} else {
					h.put("KETERANGAN", rs.getString("KETERANGAN")
							);
				}
				if (rs.getString("KOD_JENIS_PB") == null) {
					h.put("KOD_JENIS_PB", "");
				} else {
					h.put("KOD_JENIS_PB", rs.getString("KOD_JENIS_PB")
							);
				}
				if (rs.getString("JENIS_DAFTAR_PB") == null) {
					h.put("JENIS_DAFTAR_PB", "");
				} else {
					h.put("JENIS_DAFTAR_PB", rs.getString("JENIS_DAFTAR_PB")
							);
				}

				list_jenispb.addElement(h);
			}
			return list_jenispb;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_bank = null;

	public Vector list_bank() throws Exception {
		list_bank = new Vector();
		list_bank.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_BANK");
			r.add("KOD_BANK");
			r.add("NAMA_BANK");

			sql = "SELECT ID_BANK,KOD_BANK,NAMA_BANK FROM TBLRUJBANK ORDER BY NAMA_BANK ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_BANK", rs.getInt("ID_BANK"));

				if (rs.getString("NAMA_BANK") == null) {
					h.put("NAMA_BANK", "");
				} else {
					h.put("NAMA_BANK", rs.getString("NAMA_BANK"));
				}
				if (rs.getString("KOD_BANK") == null) {
					h.put("KOD_BANK", "");
				} else {
					h.put("KOD_BANK", rs.getString("KOD_BANK"));
				}

				list_bank.addElement(h);
			}
			return list_bank;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_jenisnopb = null;

	public Vector list_jenisnopb() throws Exception {
		list_jenisnopb = new Vector();
		list_jenisnopb.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_JENISNOPB");
			r.add("KOD_JENIS_NOPB");
			r.add("KETERANGAN");

			sql = "SELECT ID_JENISNOPB,KOD_JENIS_NOPB,KETERANGAN FROM TBLRUJJENISNOPB ORDER BY KOD_JENIS_NOPB ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_JENISNOPB", rs.getInt("ID_JENISNOPB"));

				if (rs.getString("KETERANGAN") == null) {
					h.put("KETERANGAN", "");
				} else {
					h.put("KETERANGAN", rs.getString("KETERANGAN")
							);
				}
				if (rs.getString("KOD_JENIS_NOPB") == null) {
					h.put("KOD_JENIS_NOPB", "");
				} else {
					h.put("KOD_JENIS_NOPB", rs.getString("KOD_JENIS_NOPB")
							);
				}

				list_jenisnopb.addElement(h);
			}
			return list_jenisnopb;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_pegawai = null;
/*
	public Vector list_pegawai(String id_seksyen,String id_negeri,String role) throws Exception {
		list_pegawai = new Vector();
		list_pegawai.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT ID_PEGAWAI,NAMA_PEGAWAI,JAWATAN FROM TBLRUJPEGAWAI "
					+ "WHERE ID_SEKSYEN = '" + id_seksyen + "' ";
					if(!role.equals("(PPT)Pengarah"))
				    {
					sql += "AND  ID_NEGERI = '"+id_negeri+"' ";
				    }
					sql += "ORDER BY NAMA_PEGAWAI ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_PEGAWAI", rs.getInt("ID_PEGAWAI"));

				if (rs.getString("NAMA_PEGAWAI") == null) {
					h.put("NAMA_PEGAWAI", "");
				} else {
					h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI")
							);
				}
				if (rs.getString("JAWATAN") == null) {
					h.put("JAWATAN", "");
				} else {
					h.put("JAWATAN", rs.getString("JAWATAN"));
				}

				list_pegawai.addElement(h);
			}
			return list_pegawai;
		} finally {
			if (db != null)
				db.close();
		}
	}*/
	
	public Vector list_pegawai(String negeriuser,String id_seksyen,String id_negeri,String role) throws Exception {
		list_pegawai = new Vector();
		list_pegawai.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
/*
			sql = "SELECT ID_PEGAWAI,NAMA_PEGAWAI,JAWATAN,ID_JAWATAN FROM TBLRUJPEGAWAI "
					+ "WHERE ID_SEKSYEN = '" + id_seksyen + "' ";
			       if(!negeriuser.equals("16"))
				    {
					sql += "AND  ID_NEGERI = '"+id_negeri+"' ";
				    }
					sql += "ORDER BY NAMA_PEGAWAI ASC";
					
					*/
			myLogger.info("masuk sql");
			
			sql = "SELECT U.USER_ID,U.USER_NAME,J.KETERANGAN,J.ID_JAWATAN,UI.ID_NEGERI " +
					"FROM USERS U,USERS_INTERNAL UI,TBLRUJJAWATAN J " +
					"WHERE U.USER_ID = UI.USER_ID AND UI.ID_SEKSYEN = '1'" +
					"AND UI.ID_JAWATAN = J.ID_JAWATAN(+)";
			
			 if(!negeriuser.equals("16"))
			    {
				sql += "AND  UI.ID_NEGERI = '"+id_negeri+"' ";
			    }
			 
			 sql += "ORDER BY U.USER_NAME ASC";
			 
			 myLogger.info("SQL:"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_PEGAWAI", rs.getString("USER_ID"));

				if (rs.getString("USER_NAME") == null) {
					h.put("NAMA_PEGAWAI", "");
				} else {
					h.put("NAMA_PEGAWAI", rs.getString("USER_NAME").toUpperCase()
							);
				}
				if (rs.getString("KETERANGAN") == null) {
					h.put("JAWATAN", "");
				} else {
					h.put("JAWATAN", rs.getString("KETERANGAN"));
				}
				
				if (rs.getString("ID_JAWATAN") == null) {
					h.put("ID_JAWATAN", "");
				} else {
					h.put("ID_JAWATAN", rs.getString("ID_JAWATAN"));
				}

				list_pegawai.addElement(h);
			}
			return list_pegawai;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	

	Vector list_bandar = null;

	public Vector getListBandarByNegeri(String idnegeri) throws Exception {
		list_bandar = new Vector();
		list_bandar.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ID_BANDAR, KOD_BANDAR, KETERANGAN, ID_NEGERI FROM TBLRUJBANDAR WHERE ID_NEGERI = '"
					+ idnegeri + "' ORDER BY KOD_BANDAR ASC";

			myLogger.info("FIND BANDAR :" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_BANDAR", rs.getInt("ID_BANDAR"));

				if (rs.getString("KETERANGAN") == null) {
					h.put("NAMA_BANDAR", "");
				} else {
					h.put("NAMA_BANDAR", rs.getString("KETERANGAN")
							);
				}
				if (rs.getString("KOD_BANDAR") == null) {
					h.put("KOD_BANDAR", "");
				} else {
					h.put("KOD_BANDAR", rs.getString("KOD_BANDAR")
							);
				}

				list_bandar.addElement(h);
			}
			return list_bandar;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector senarai_hakmilik_batal_penarikan = null;

	public Vector senarai_hakmilik_batal_penarikan(String id_permohonan)
			throws Exception {
		senarai_hakmilik_batal_penarikan = new Vector();
		Db db = null;
		senarai_hakmilik_batal_penarikan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT " +
				//	"PH.FLAG_WARTA,PH.ID_PENARIKANHAKMILIK,PH.LUAS_LOT_TARIK," +
					"H.LUAS_AMBIL," +
				//	"PH.LUAS_AMBIL AS LUAS_AMBIL_ASAL," +
					"H.LUAS_LOT_TARIK,H.ID_HAKMILIK," +
					"PB.ID_PERMOHONAN,H.NO_PT," +
				//	"H.NO_LOT, "
					
				/*	
					
					" CASE "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
					" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
					" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN H.NO_LOT || CHR(32) || CHR(40) || JL.KETERANGAN  || CHR(32) || H.NO_PT || CHR(41) "+
					" ELSE "+
					" '' "+
					" END AS NO_LOT, "*/
					
					" CASE "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
					" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
					" ELSE "+
					" '' "+
					" END AS NO_LOT, "
					
					+ " JH.KETERANGAN AS JENIS_HAKMILIK, "
					+ " JH.KOD_JENIS_HAKMILIK,H.NO_HAKMILIK,M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH, "
					+ " N.NAMA_NEGERI,D.NAMA_DAERAH,H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT,H.NAMA_LUAS_ASAL,H.NAMA_LUAS_AMBIL  "
					+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M,TBLPPTPERMOHONAN PB," 
						//	",TBLPPTPENARIKANHAKMILIK PH,"
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D,TBLRUJLOT JL  "
					+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_MUKIM = M.ID_MUKIM "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) " +
							" AND H.ID_LOT = JL.ID_LOT(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "
					+ "AND PB.ID_PERMOHONAN = H.ID_PERMOHONAN  "
					//+ " AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
					+ " AND PB.ID_PERMOHONAN = '"
					+ id_permohonan
					+ "' "
					+ " ORDER BY H.NO_PT ASC ";
			myLogger.info("SENARAI SUDAH PENARIKAN :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
			/*	
				h.put("FLAG_WARTA", rs.getString("FLAG_WARTA") == null ? ""
						: rs.getString("FLAG_WARTA"));
				h.put("ID_PENARIKANHAKMILIK", rs
						.getString("ID_PENARIKANHAKMILIK") == null ? "" : rs
						.getString("ID_PENARIKANHAKMILIK"));*/
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH"));
				
				
				if (rs.getString("ID_UNITLUASLOT_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");				
					}					
					if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");					
					}
				} else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));			
					}
							
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " MP");
						
					}					
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " HEKTAR");
						
					}

				} else {
					
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getDouble("LUAS_AMBIL"));
					
					}
		
		
				h.put("LUAS_AMBIL_EDIT",
						rs.getString("LUAS_AMBIL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL"));
		
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				
				
				
				h.put("ID_UNITLUASLOT_CONVERT", rs.getString("ID_UNITLUASLOT_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASLOT_CONVERT"));
				
				h.put("ID_UNITLUASAMBIL_CONVERT", rs.getString("ID_UNITLUASAMBIL_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASAMBIL_CONVERT"));
				
				h.put("NAMA_LUAS_ASAL", rs.getString("NAMA_LUAS_ASAL") == null ? ""
						: rs.getString("NAMA_LUAS_ASAL"));
				
				h.put("NAMA_LUAS_AMBIL", rs.getString("NAMA_LUAS_AMBIL") == null ? ""
						: rs.getString("NAMA_LUAS_AMBIL"));
				
				senarai_hakmilik_batal_penarikan.addElement(h);
			}
			return senarai_hakmilik_batal_penarikan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector senarai_pihak_penting = null;	
	public Vector senarai_pihak_penting(String id_permohonan)throws Exception{
		senarai_pihak_penting = new Vector();
		Db db = null;
		senarai_pihak_penting.clear();
		String sql = "";
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT PB1.NAMA_PB,B1.ID_JENISPB,H1.ID_HAKMILIK " +
					"FROM TBLPPTPIHAKBERKEPENTINGAN PB1,TBLPPTHAKMILIKPB B1,TBLPPTHAKMILIK H1 "+
			" WHERE B1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "+
			" AND B1.ID_HAKMILIK = H1.ID_HAKMILIK "+
		//	" AND NVL(H1.FLAG_PEMBATALAN,' ') <> 'Y' "+
			" AND H1.ID_PERMOHONAN = '"+id_permohonan+"' "+
			" ORDER BY NAMA_PB ASC ";
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL",bil);				
				h.put("ID_HAKMILIK",rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("NAMA_PB",rs.getString("NAMA_PB") == null ? "" : rs.getString("NAMA_PB"));
				senarai_pihak_penting.addElement(h);				
			}
			return senarai_pihak_penting;
		}finally{
			if (db != null)
				db.close();
		}
	}
	
	 Vector tempat_tampal = null;
		public Vector tempat_tampal(String id_permohonan) throws Exception {
			tempat_tampal = new Vector();
			tempat_tampal.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = " SELECT INITCAP(TEMPAT) AS TEMPAT,TARIKH_TAMPAL,JENIS_TEMPAT_TAMPAL FROM TBLPPTNOTISAWAM  WHERE ID_PERMOHONAN = '"+id_permohonan+"'";
	            myLogger.info("tempat tampal :"+sql);
				ResultSet rs = stmt.executeQuery(sql);

	            	
				while (rs.next()) {
					Hashtable h = new Hashtable();	
					
					if (rs.getString("TEMPAT") == null) {
						h.put("TEMPAT", "");
					} else {
						h.put("TEMPAT", rs.getString("TEMPAT"));
					}
					
					if (rs.getString("JENIS_TEMPAT_TAMPAL") == null) {
						h.put("JENIS_TEMPAT_TAMPAL", "");
					} else {
						h.put("JENIS_TEMPAT_TAMPAL", rs.getString("JENIS_TEMPAT_TAMPAL"));
					}
					
					h.put("TARIKH_TAMPAL", rs
							.getString("TARIKH_TAMPAL") == null ? "" : Format
							.format(rs.getDate("TARIKH_TAMPAL")));
					
					
					
					
					tempat_tampal.addElement(h);
							
				}
				
				return tempat_tampal;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		
		Vector list_siasatan = null;

		public Vector list_siasatan(String idpermohonan, String id_hakmilik)
				throws Exception {
			list_siasatan = new Vector();
			list_siasatan.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				sql = "SELECT HM.NO_PT," +
						//"HM.NO_LOT," +
/*
				" CASE "+
				" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL THEN HM.NO_LOT "+
				" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NULL THEN JL.KETERANGAN || HM.NO_PT "+
				" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || HM.NO_PT  "+
				" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL THEN HM.NO_LOT || CHR(32) || CHR(40) || JL.KETERANGAN  || CHR(32) || HM.NO_PT || CHR(41) "+
				" ELSE "+
				" '' "+
				" END AS NO_LOT, "+*/
				
				" CASE "+
				" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL THEN HM.NO_LOT "+
				" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || HM.NO_PT  "+
				" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL THEN JL.KETERANGAN  || HM.NO_PT || CHR(32) || CHR(40) || HM.NO_LOT  || CHR(41)  "+
				" ELSE "+
				" '' "+
				" END AS NO_LOT, "+

						"S.ID_PEGAWAI_SIASATAN,PG.USER_NAME AS NAMA_PEGAWAI,S.ULASAN_SIASATAN,PB.ID_PERMOHONAN,S.ID_HAKMILIK,S.BIL_SIASATAN,S.ID_SIASATAN,S.NO_KES,S.NO_SIASATAN,S.STATUS_SIASATAN,S.TARIKH_SIASATAN,S.MASA_SIASATAN, "
						+ " S.TEMPAT,S.ALAMAT1,S.ALAMAT2,S.ALAMAT3,S.POSKOD,S.ID_NEGERI,S.ALASAN_TANGGUH,S.NILAIAN_JPPH,S.ID_UNITLUAS,S.BANTAHAN_TUANTNH,S.BANTAHAN_AGENSI, "
						+ " S.BANTAHAN_LAIN,S.TEMPOH_MILIK_TANAH,S.CARA_MILIK,S.HARGA_BELI,S.JENIS_BANGUNAN,S.JENIS_TANAMAN,S.FLAG_PECAH_SEMPADAN,S.FLAG_TUKAR_SYARAT, "
						+ " S.TARIKH_PECAH_SEMPADAN,S.TARIKH_TUKAR_SYARAT,S.STATUS_SEMASA,S.BEBANAN,S.KETERANGAN_TUAN_TANAH,S.KETERANGAN_AGENSI,S.KETERANGAN_JURUNILAI, "
						+ " S.TUNTUTAN_TUANTNH,S.TUNTUTAN_PB_BEBANAN,S.TUNTUTAN_PB_TDKDAFTAR,S.TUNTUTAN_PB_LAIN,S.ID_BORANGE,S.PERINTAH,S.TARIKH_AKHIR_TAMPAL, "
						+ " S.ID_PEGAWAI_SIASATAN,S.STATUS_TUNTUTAN,S.ID_BANDAR,S.JENIS_WAKTU_SIASATAN "
						+ " FROM TBLPPTSIASATAN S,TBLPPTHAKMILIK HM,"
						+ "TBLPPTPERMOHONAN PB,TBLRUJLOT JL,USERS PG "
						+ " WHERE S.ID_PERMOHONAN = PB.ID_PERMOHONAN "
					//	+ " AND HM.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
					//	+ " AND PH.ID_HAKMILIK = HM.ID_HAKMILIK  "
					//	+ " AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "					
						+ " AND S.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_LOT = JL.ID_LOT(+)"
						+ " AND S.ID_PEGAWAI_SIASATAN = PG.USER_ID(+)"
						+ " AND PB.ID_PERMOHONAN = '"
						+ idpermohonan
						+ "'"
						+ " AND S.ID_HAKMILIK = '" + id_hakmilik + "'" + "";
				sql += " ORDER BY S.NO_KES ASC,S.BIL_SIASATAN ASC";
				myLogger.info("LIST SIASATAN :" + sql);
				ResultSet rs = stmt.executeQuery(sql);

				int bil = 0;
				String temp = "";
				while (rs.next()) {
					bil = bil + 1;
					temp = bil + "a";

					Hashtable h = new Hashtable();
					h.put("BIL", bil);
					h.put("BILTEMP", temp);
					h.put("BILDUM", bil + "dum");
					h.put("ID_PEGAWAI_SIASATAN", rs
							.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
							.getString("ID_PEGAWAI_SIASATAN"));
					h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI") == null ? ""
							: rs.getString("NAMA_PEGAWAI"));

					h.put("ID_PENARIKANBALIK",
							rs.getString("ID_PERMOHONAN") == null ? "" : rs
									.getString("ID_PERMOHONAN"));
					h.put("NO_LOT",
							rs.getString("NO_LOT") == null ? "" : rs
									.getString("NO_LOT"));
					h.put("NO_PT",
							rs.getString("NO_PT") == null ? "" : rs
									.getString("NO_PT"));
					h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
							: rs.getString("ID_HAKMILIK"));
					h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
							: rs.getString("ID_SIASATAN"));
					h.put("NO_KES", rs.getString("NO_KES") == null ? "" : rs
							.getString("NO_KES"));
					h.put("NO_SIASATAN", rs.getString("NO_SIASATAN") == null ? ""
							: rs.getString("NO_SIASATAN"));
					h.put("STATUS_SIASATAN",
							rs.getString("STATUS_SIASATAN") == null ? "" : rs
									.getString("STATUS_SIASATAN"));
					h.put("MASA_SIASATAN",
							rs.getString("MASA_SIASATAN") == null ? "" : rs
									.getString("MASA_SIASATAN"));
					h.put("TARIKH_SIASATAN",
							rs.getString("TARIKH_SIASATAN") == null ? "" : Format
									.format(rs.getDate("TARIKH_SIASATAN")));
					h.put("TEMPAT", rs.getString("TEMPAT") == null ? "" : rs
							.getString("TEMPAT"));
					h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
							.getString("ALAMAT1"));
					h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
							.getString("ALAMAT2"));
					h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
							.getString("ALAMAT3"));
					h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
							.getString("POSKOD"));
					h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
							.getString("ID_NEGERI"));
					h.put("ALASAN_TANGGUH",
							rs.getString("ALASAN_TANGGUH") == null ? "" : rs
									.getString("ALASAN_TANGGUH"));
					h.put("ID_UNITLUAS", rs.getString("ID_UNITLUAS") == null ? ""
							: rs.getString("ID_UNITLUAS"));
					h.put("BANTAHAN_TUANTNH",
							rs.getString("BANTAHAN_TUANTNH") == null ? "" : rs
									.getString("BANTAHAN_TUANTNH"));
					h.put("BANTAHAN_AGENSI",
							rs.getString("BANTAHAN_AGENSI") == null ? "" : rs
									.getString("BANTAHAN_AGENSI"));
					h.put("NILAIAN_JPPH", rs.getString("NILAIAN_JPPH") == null ? ""
							: Format.format(rs.getDouble("NILAIAN_JPPH")));
					h.put("BANTAHAN_LAIN",
							rs.getString("BANTAHAN_LAIN") == null ? "" : rs
									.getString("BANTAHAN_LAIN"));
					h.put("TEMPOH_MILIK_TANAH",
							rs.getString("TEMPOH_MILIK_TANAH") == null ? "" : rs
									.getString("TEMPOH_MILIK_TANAH"));
					h.put("CARA_MILIK", rs.getString("CARA_MILIK") == null ? ""
							: rs.getString("CARA_MILIK"));
					h.put("JENIS_BANGUNAN",
							rs.getString("JENIS_BANGUNAN") == null ? "" : rs
									.getString("JENIS_BANGUNAN"));
					h.put("JENIS_TANAMAN",
							rs.getString("JENIS_TANAMAN") == null ? "" : rs
									.getString("JENIS_TANAMAN"));
					h.put("FLAG_PECAH_SEMPADAN", rs
							.getString("FLAG_PECAH_SEMPADAN") == null ? "" : rs
							.getString("FLAG_PECAH_SEMPADAN"));
					h.put("FLAG_TUKAR_SYARAT",
							rs.getString("FLAG_TUKAR_SYARAT") == null ? "" : rs
									.getString("FLAG_TUKAR_SYARAT"));
					h.put("HARGA_BELI", rs.getString("HARGA_BELI") == null ? ""
							: rs.getDouble("HARGA_BELI"));
					h.put("STATUS_SEMASA",
							rs.getString("STATUS_SEMASA") == null ? "" : rs
									.getString("STATUS_SEMASA"));
					h.put("BEBANAN", rs.getString("BEBANAN") == null ? "" : rs
							.getString("BEBANAN"));
					h.put("KETERANGAN_TUAN_TANAH", rs
							.getString("KETERANGAN_TUAN_TANAH") == null ? "" : rs
							.getString("KETERANGAN_TUAN_TANAH"));
					h.put("KETERANGAN_AGENSI",
							rs.getString("KETERANGAN_AGENSI") == null ? "" : rs
									.getString("KETERANGAN_AGENSI"));
					h.put("KETERANGAN_JURUNILAI", rs
							.getString("KETERANGAN_JURUNILAI") == null ? "" : rs
							.getString("KETERANGAN_JURUNILAI"));
					h.put("TARIKH_PECAH_SEMPADAN", rs
							.getString("TARIKH_PECAH_SEMPADAN") == null ? ""
							: Format.format(rs.getDate("TARIKH_PECAH_SEMPADAN")));
					h.put("TARIKH_TUKAR_SYARAT", rs
							.getString("TARIKH_TUKAR_SYARAT") == null ? "" : Format
							.format(rs.getDate("TARIKH_TUKAR_SYARAT")));
					h.put("TARIKH_AKHIR_TAMPAL", rs
							.getString("TARIKH_AKHIR_TAMPAL") == null ? "" : Format
							.format(rs.getDate("TARIKH_AKHIR_TAMPAL")));
					h.put("TUNTUTAN_TUANTNH",
							rs.getString("TUNTUTAN_TUANTNH") == null ? "" : rs
									.getString("TUNTUTAN_TUANTNH"));
					h.put("TUNTUTAN_PB_BEBANAN", rs
							.getString("TUNTUTAN_PB_BEBANAN") == null ? "" : rs
							.getString("TUNTUTAN_PB_BEBANAN"));
					h.put("TUNTUTAN_PB_TDKDAFTAR", rs
							.getString("TUNTUTAN_PB_TDKDAFTAR") == null ? "" : rs
							.getString("TUNTUTAN_PB_TDKDAFTAR"));
					h.put("TUNTUTAN_PB_LAIN",
							rs.getString("TUNTUTAN_PB_LAIN") == null ? "" : rs
									.getString("TUNTUTAN_PB_LAIN"));
					h.put("ID_BORANGE", rs.getString("ID_BORANGE") == null ? ""
							: rs.getString("ID_BORANGE"));
					h.put("PERINTAH", rs.getString("PERINTAH") == null ? "" : rs
							.getString("PERINTAH"));
					h.put("ID_PEGAWAI_SIASATAN", rs
							.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
							.getString("ID_PEGAWAI_SIASATAN"));
					h.put("STATUS_TUNTUTAN",
							rs.getString("STATUS_TUNTUTAN") == null ? "" : rs
									.getString("STATUS_TUNTUTAN"));
					h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
							.getString("ID_BANDAR"));
					h.put("JENIS_WAKTU_SIASATAN", rs
							.getString("JENIS_WAKTU_SIASATAN") == null ? "" : rs
							.getString("JENIS_WAKTU_SIASATAN"));
					h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN") == null ? "1"
							: rs.getString("BIL_SIASATAN"));
					h.put("ULASAN_SIASATAN",
							rs.getString("ULASAN_SIASATAN") == null ? "" : rs
									.getString("ULASAN_SIASATAN"));

					list_siasatan.addElement(h);
				}
				return list_siasatan;
			} finally {
				if (db != null)
					db.close();
			}
		}

		
		
		Vector list_bandar_all = null;

		public Vector list_bandar_all() throws Exception {
			list_bandar_all = new Vector();
			list_bandar_all.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				sql = "SELECT ID_BANDAR, KOD_BANDAR, KETERANGAN, ID_NEGERI FROM TBLRUJBANDAR";

				myLogger.info("FIND BANDAR :" + sql);
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("ID_BANDAR", rs.getInt("ID_BANDAR"));

					if (rs.getString("KETERANGAN") == null) {
						h.put("NAMA_BANDAR", "");
					} else {
						h.put("NAMA_BANDAR", rs.getString("KETERANGAN")
								);
					}
					if (rs.getString("KOD_BANDAR") == null) {
						h.put("KOD_BANDAR", "");
					} else {
						h.put("KOD_BANDAR", rs.getString("KOD_BANDAR")
								);
					}

					list_bandar_all.addElement(h);
				}
				return list_bandar_all;
			} finally {
				if (db != null)
					db.close();
			}
		}

		
		Vector maklumat_penyediaan = null;

		public Vector maklumat_penyediaan(String id_permohonan) throws Exception {
			maklumat_penyediaan = new Vector();
			Db db = null;
			maklumat_penyediaan.clear();
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT M.FLAG_MMK,M.NAMA_TUAN_TANAH,M.KEPUTUSAN,M.TAJUK,M.ULASAN_PENGARAH,M.SEBAB_PENARIKAN,M.ID_MMK,M.ID_PENARIKANBALIK,M.JENIS_MMK,M.ULASAN,M.NO_RUJMMK,M.FLAG_SEMAK,"
						+ " M.ID_SEMAK,M.TARIKH_SEMAK,M.FLAG_BORANGI,M.ID_PERMOHONAN, M.TUJUAN,M.LATARBELAKANG,"
						+ " M.ASAS_PERTIMBANGAN,M.KESIMPULAN,M.SYOR,M.KEDUDUKAN_LAPORAN_TNH,M.PENGESAHAN_PERUNTUKAN,"
						+ " M.PANDANGAN,M.IMPLIKASI, M.ULASAN_JABTEKNIKAL,M.PERIHAL_TANAH,M.PERIHAL_POHON,M.ANGGARAN_KOS,"
						+ " M.PERAKUAN_PENTADBIR_TNH,M.NILAI_ATAS_TANAH,M.TARIKH_MMK,M.STATUS_SEMAKAN "
						+ " FROM TBLPPTMMK M,TBLPPTPERMOHONAN P  "
						+ " WHERE M.ID_PERMOHONAN = P.ID_PERMOHONAN"
						+ " AND P.ID_PERMOHONAN = '" + id_permohonan + "'";

				myLogger.info("PENYEDIAAN:" + sql);

				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;

				while (rs.next()) {

					h = new Hashtable();
					h.put("ID_MMK", rs.getString("ID_MMK") == null ? "" : rs
							.getString("ID_MMK"));
					h.put("FLAG_MMK",
							rs.getString("FLAG_MMK") == null ? "" : rs
									.getString("FLAG_MMK"));
					h.put("ID_PEMBATALAN",
							rs.getString("ID_PERMOHONAN") == null ? "" : rs
									.getString("ID_PERMOHONAN"));
					h.put("JENIS_MMK", rs.getString("JENIS_MMK") == null ? "" : rs
							.getString("JENIS_MMK"));
					h.put("ULASAN", rs.getString("ULASAN") == null ? "" : rs
							.getString("ULASAN"));
					h.put("NO_RUJMMK", rs.getString("NO_RUJMMK") == null ? "" : rs
							.getString("NO_RUJMMK"));
					h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? ""
							: rs.getString("FLAG_SEMAK"));
					h.put("FLAG_BORANGI", rs.getString("FLAG_BORANGI") == null ? ""
							: rs.getString("FLAG_BORANGI"));
					h.put("ID_PERMOHONAN",
							rs.getString("ID_PERMOHONAN") == null ? "" : rs
									.getString("ID_PERMOHONAN"));
					h.put("TUJUAN", rs.getString("TUJUAN") == null ? "" : rs
							.getString("TUJUAN"));
					h.put("LATARBELAKANG",
							rs.getString("LATARBELAKANG") == null ? "" : rs
									.getString("LATARBELAKANG"));
					h.put("ASAS_PERTIMBANGAN",
							rs.getString("ASAS_PERTIMBANGAN") == null ? "" : rs
									.getString("ASAS_PERTIMBANGAN"));
					h.put("KESIMPULAN", rs.getString("KESIMPULAN") == null ? ""
							: rs.getString("KESIMPULAN"));
					h.put("SYOR", rs.getString("SYOR") == null ? "" : rs
							.getString("SYOR"));
					h.put("KEDUDUKAN_LAPORAN_TNH", rs
							.getString("KEDUDUKAN_LAPORAN_TNH") == null ? "" : rs
							.getString("KEDUDUKAN_LAPORAN_TNH"));
					h.put("PENGESAHAN_PERUNTUKAN", rs
							.getString("PENGESAHAN_PERUNTUKAN") == null ? "" : rs
							.getString("PENGESAHAN_PERUNTUKAN"));
					h.put("PANDANGAN", rs.getString("PANDANGAN") == null ? "" : rs
							.getString("PANDANGAN"));
					h.put("IMPLIKASI", rs.getString("IMPLIKASI") == null ? "" : rs
							.getString("IMPLIKASI"));
					h.put("ULASAN_JABTEKNIKAL",
							rs.getString("ULASAN_JABTEKNIKAL") == null ? "" : rs
									.getString("ULASAN_JABTEKNIKAL"));
					h.put("PERIHAL_TANAH",
							rs.getString("PERIHAL_TANAH") == null ? "" : rs
									.getString("PERIHAL_TANAH"));
					h.put("PERIHAL_POHON",
							rs.getString("PERIHAL_POHON") == null ? "" : rs
									.getString("PERIHAL_POHON"));
					h.put("ANGGARAN_KOS", rs.getString("ANGGARAN_KOS") == null ? ""
							: rs.getString("ANGGARAN_KOS"));
					h.put("PERAKUAN_PENTADBIR_TNH", rs
							.getString("PERAKUAN_PENTADBIR_TNH") == null ? "" : rs
							.getString("PERAKUAN_PENTADBIR_TNH"));
					h.put("NILAI_ATAS_TANAH",
							rs.getString("NILAI_ATAS_TANAH") == null ? "" : rs
									.getString("NILAI_ATAS_TANAH"));
					h.put("SEBAB_PENARIKAN",
							rs.getString("SEBAB_PENARIKAN") == null ? "" : rs
									.getString("SEBAB_PENARIKAN"));
					h.put("STATUS_SEMAKAN",
							rs.getString("STATUS_SEMAKAN") == null ? "" : rs
									.getString("STATUS_SEMAKAN"));
					h.put("TARIKH_SEMAK", rs.getString("TARIKH_SEMAK") == null ? ""
							: Format.format(rs.getDate("TARIKH_SEMAK")));
					h.put("TARIKH_MMK", rs.getString("TARIKH_MMK") == null ? ""
							: Format.format(rs.getDate("TARIKH_MMK")));
				/*	h.put("SEBAB_PENARIKANBALIK", rs
							.getString("SEBAB_PENARIKANBALIK") == null ? "" : rs
							.getString("SEBAB_PENARIKANBALIK"));*/
					h.put("ULASAN_PENGARAH", rs
							.getString("ULASAN_PENGARAH") == null ? "" : rs
							.getString("ULASAN_PENGARAH"));
					h.put("TAJUK", rs
							.getString("TAJUK") == null ? "" : rs
							.getString("TAJUK"));
					h.put("NAMA_TUAN_TANAH", rs
							.getString("NAMA_TUAN_TANAH") == null ? "" : rs
							.getString("NAMA_TUAN_TANAH"));
					h.put("KEPUTUSAN", rs
							.getString("KEPUTUSAN") == null ? "" : rs
							.getString("KEPUTUSAN"));
					
					maklumat_penyediaan.addElement(h);
				}
				return maklumat_penyediaan;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		Vector maklumat_keputusan = null;

		public Vector maklumat_keputusan(String id_permohonan) throws Exception {
			maklumat_keputusan = new Vector();
			Db db = null;
			maklumat_keputusan.clear();
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT MK.ID_MMKKEPUTUSAN,MK.TARIKH_TERIMA,MK.TARIKH_KEPUTUSAN,"
						+ "MK.TARIKH_HANTAR,MK.STATUS_KEPUTUSAN,MK.ULASAN,M.NO_RUJMMK,M.TARIKH_MMK "
						+ "FROM TBLPPTMMK M,"
						+ "TBLPPTPERMOHONAN P, TBLPPTMMKKEPUTUSAN MK "
						+ "WHERE M.ID_PERMOHONAN = P.ID_PERMOHONAN AND M.ID_MMK = MK.ID_MMK "
						+ " AND P.ID_PERMOHONAN = '" + id_permohonan + "'";

				myLogger.info("KEPUTUSAN MMK:" + sql);

				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;

				while (rs.next()) {

					h = new Hashtable();
					h.put("ID_MMKKEPUTUSAN",
							rs.getString("ID_MMKKEPUTUSAN") == null ? "" : rs
									.getString("ID_MMKKEPUTUSAN"));
					h.put("ULASAN", rs.getString("ULASAN") == null ? "" : rs
							.getString("ULASAN"));
					h.put("NO_RUJMMK", rs.getString("NO_RUJMMK") == null ? "" : rs
							.getString("NO_RUJMMK"));
					h.put("TARIKH_MMK", rs.getString("TARIKH_MMK") == null ? ""
							: Format.format(rs.getDate("TARIKH_MMK")));
					h.put("STATUS_KEPUTUSAN",
							rs.getString("STATUS_KEPUTUSAN") == null ? "" : rs
									.getString("STATUS_KEPUTUSAN"));
					h.put("TARIKH_TERIMA",
							rs.getString("TARIKH_TERIMA") == null ? "" : Format
									.format(rs.getDate("TARIKH_TERIMA")));
					h.put("TARIKH_KEPUTUSAN",
							rs.getString("TARIKH_KEPUTUSAN") == null ? "" : Format
									.format(rs.getDate("TARIKH_KEPUTUSAN")));
					h.put("TARIKH_HANTAR",
							rs.getString("TARIKH_HANTAR") == null ? "" : Format
									.format(rs.getDate("TARIKH_HANTAR")));
					maklumat_keputusan.addElement(h);
				}
				return maklumat_keputusan;
			} finally {
				if (db != null)
					db.close();
			}
		}

		Vector maklumat_hakmilik_mmk_pb_siasatan = null;
		public Vector maklumat_hakmilik_mmk_pb_siasatan(String id_siasatan,String id_permohonan) throws Exception {
			maklumat_hakmilik_mmk_pb_siasatan = new Vector();
			maklumat_hakmilik_mmk_pb_siasatan.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = " SELECT " +			
				"DISTINCT " +
						"initcap(PPB.NAMA_PB) AS NAMA_PB FROM " +
						"TBLPPTPERMOHONAN PB," +
						"TBLPPTPENARIKANHAKMILIK PH," +
						"TBLPPTHAKMILIK H,TBLPPTPIHAKBERKEPENTINGAN PPB,TBLPPTHAKMILIKPB HPB,TBLPPTSIASATAN S "
							+"    WHERE PB.ID_PERMOHONAN = H.ID_PERMOHONAN "
							//+"    AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
							+"    AND S.ID_HAKMILIK = H.ID_HAKMILIK "
							+"    AND H.ID_HAKMILIK = HPB.ID_HAKMILIK "
							+"    AND HPB.ID_JENISPB NOT IN (40,41,42) "
							+"    AND HPB.ID_PIHAKBERKEPENTINGAN = PPB.ID_PIHAKBERKEPENTINGAN "
							//+"    AND PH.FLAG_WARTA = 'Y' "
							+"   AND PB.ID_PERMOHONAN = '"+id_permohonan+"'"
							+"   AND S.ID_SIASATAN = '"+id_siasatan+"'";

				ResultSet rs = stmt.executeQuery(sql);

	            	
				while (rs.next()) {
					Hashtable h = new Hashtable();	
					
					if (rs.getString("NAMA_PB") == null) {
						h.put("NAMA_PB", "");
					} else {
						h.put("NAMA_PB", rs.getString("NAMA_PB"));
					}
					
					maklumat_hakmilik_mmk_pb_siasatan.addElement(h);
							
				}
				
				return maklumat_hakmilik_mmk_pb_siasatan;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		

		Vector maklumat_hakmilik_mmk_pemilik_siasatan = null;
		public Vector maklumat_hakmilik_mmk_pemilik_siasatan(String id_siasatan,String id_permohonan) throws Exception {
			maklumat_hakmilik_mmk_pemilik_siasatan = new Vector();
			maklumat_hakmilik_mmk_pemilik_siasatan.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = " SELECT " +			
				"DISTINCT " +
						"UPPER(PPB.NAMA_PB) AS NAMA_PB FROM TBLPPTPERMOHONAN PB," +
						//"TBLPPTPENARIKANHAKMILIK PH," +
						"TBLPPTHAKMILIK H,TBLPPTPIHAKBERKEPENTINGAN PPB,TBLPPTHAKMILIKPB HPB,TBLPPTSIASATAN S "
							+"    WHERE PB.ID_PERMOHONAN = H.ID_PERMOHONAN "
							//+"    AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
							+"    AND S.ID_HAKMILIK = H.ID_HAKMILIK "
							+"    AND H.ID_HAKMILIK = HPB.ID_HAKMILIK "
							+"    AND HPB.ID_JENISPB = '1' "
							+"    AND HPB.ID_PIHAKBERKEPENTINGAN = PPB.ID_PIHAKBERKEPENTINGAN "
							//+"    AND PH.FLAG_WARTA = 'Y' "
							+"   AND PB.ID_PERMOHONAN = '"+id_permohonan+"'"
							+"   AND S.ID_SIASATAN = '"+id_siasatan+"'";

				ResultSet rs = stmt.executeQuery(sql);

	            	
				while (rs.next()) {
					Hashtable h = new Hashtable();	
					
					if (rs.getString("NAMA_PB") == null) {
						h.put("NAMA_PB", "");
					} else {
						h.put("NAMA_PB", rs.getString("NAMA_PB"));
					}
					
					maklumat_hakmilik_mmk_pemilik_siasatan.addElement(h);
							
				}
				
				return maklumat_hakmilik_mmk_pemilik_siasatan;
			} finally {
				if (db != null)
					db.close();
			}
		}
		

		Vector senarai_hakmilik_batal_penarikan_maklumat = null;

		public Vector senarai_hakmilik_batal_penarikan_maklumat(String id_permohonan,String id_hakmilik)
				throws Exception {
			senarai_hakmilik_batal_penarikan_maklumat = new Vector();
			Db db = null;
			senarai_hakmilik_batal_penarikan_maklumat.clear();
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT DISTINCT " +
					//	"PH.ID_PENARIKANHAKMILIK,PH.LUAS_LOT_TARIK," +
						"H.LUAS_AMBIL," +
					//	"PH.LUAS_AMBIL AS LUAS_AMBIL_ASAL," +
						"H.LUAS_LOT_TARIK,H.ID_HAKMILIK,PB.ID_PERMOHONAN,H.NO_PT," +
				//		"H.NO_LOT, "
						
						
						/*
						" CASE "+
						" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
						" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
						" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
						" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN H.NO_LOT || CHR(32) || CHR(40) || JL.KETERANGAN  || CHR(32) || H.NO_PT || CHR(41) "+
						" ELSE "+
						" '' "+
						" END AS NO_LOT, "
						*/
						
						" CASE "+
						" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
						" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
						" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
						" ELSE "+
						" '' "+
						" END AS NO_LOT, "					
						+ " H.NO_HAKMILIK,JH.KETERANGAN AS JENIS_HAKMILIK, "
						+ " JH.KOD_JENIS_HAKMILIK," +
								"" +
								"" +
								"" +
								"" +
								"H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT,M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH, "
						+ " N.NAMA_NEGERI,D.NAMA_DAERAH  "
						+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M," +
								"TBLPPTPERMOHONAN PB," +
								//"TBLPPTPENARIKANHAKMILIK PH,"
						 " TBLRUJNEGERI N,TBLRUJDAERAH D,TBLRUJLOT JL  "
						+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
						+ " AND H.ID_MUKIM = M.ID_MUKIM "
						+ " AND H.ID_NEGERI = N.ID_NEGERI(+) "
						+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "
						+ " AND PB.ID_PERMOHONAN = H.ID_PERMOHONAN AND H.ID_LOT = JL.ID_LOT(+) "
						+ " AND H.ID_HAKMILIK = '"+id_hakmilik+"' "					
					//	+ " AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
						+ " AND PB.ID_PERMOHONAN = '"
						+ id_permohonan
						+ "' "
						+ " ORDER BY H.NO_PT ASC ";
				myLogger.info("SENARAI SUDAH PENARIKAN X :" + sql);

				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 0;
				while (rs.next()) {
					bil = bil + 1;
					h = new Hashtable();
					h.put("BIL", bil);
			/*		h.put("ID_PENARIKANHAKMILIK", rs
							.getString("ID_PENARIKANHAKMILIK") == null ? "" : rs
							.getString("ID_PENARIKANHAKMILIK"));*/
					h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
							: rs.getString("ID_HAKMILIK"));
					h.put("ID_PEMBATALAN",
							rs.getString("ID_PERMOHONAN") == null ? "" : rs
									.getString("ID_PERMOHONAN"));
					h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
							.getString("NO_LOT"));
					h.put("JENIS_HAKMILIK",
							rs.getString("JENIS_HAKMILIK") == null ? "" : rs
									.getString("JENIS_HAKMILIK"));
					h.put("KOD_JENIS_HAKMILIK",
							rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
									.getString("KOD_JENIS_HAKMILIK"));
					h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
							: rs.getString("NO_HAKMILIK"));
					h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
							: rs.getString("NAMA_MUKIM"));
					h.put("ID_KATEGORITANAH",
							rs.getString("ID_KATEGORITANAH") == null ? "" : rs
									.getString("ID_KATEGORITANAH"));
					
					
				
					if (rs.getString("ID_UNITLUASLOT_CONVERT")!=null) {					
						if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
							h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
									: rs.getDouble("LUAS_LOT") + " MP");				
						}					
						if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
							h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
									: rs.getDouble("LUAS_LOT") + " HEKTAR");					
						}
					} else {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT"));			
						}
								
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
						if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
							h.put("LUAS_AMBIL",
									rs.getString("LUAS_AMBIL") == null ? "" : rs
											.getDouble("LUAS_AMBIL")
											+ " MP");
							
						}					
						if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
							h.put("LUAS_AMBIL",
									rs.getString("LUAS_AMBIL") == null ? "" : rs
											.getDouble("LUAS_AMBIL")
											+ " HEKTAR");
							
						}

					} else {
						
						h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getDouble("LUAS_AMBIL"));
						
						}
					
					
			
					h.put("LUAS_AMBIL_EDIT",
							rs.getString("LUAS_AMBIL") == null ? "0" : rs
									.getDouble("LUAS_AMBIL"));
				
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
							: rs.getString("NAMA_NEGERI"));
					h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
							: rs.getString("NAMA_DAERAH"));
					senarai_hakmilik_batal_penarikan_maklumat.addElement(h);
				}
				return senarai_hakmilik_batal_penarikan_maklumat;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		
		Vector tarikh_akhir_tampal = null;
		public Vector tarikh_akhir_tampal(String id_permohonan) throws Exception {
		tarikh_akhir_tampal = new Vector();
		tarikh_akhir_tampal.clear();
		Db db = null;        			
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
	    	sql = "SELECT MAX(TARIKH_TAMPAL) AS TARIKH_TAMPAL FROM TBLPPTNOTISAWAM A, TBLPPTPERMOHONAN P WHERE P.ID_PERMOHONAN = '"+id_permohonan+"' "+
	    		  " AND A.ID_PERMOHONAN = P.ID_PERMOHONAN";
	    	myLogger.info("SQL TARIKH TAMPAL :"+sql);
	    	ResultSet rs = stmt.executeQuery(sql);    	
			
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("TARIKH_TAMPAL", rs.getString("TARIKH_TAMPAL") == null ? "" : Format.format(rs.getDate("TARIKH_TAMPAL")));			
				tarikh_akhir_tampal.addElement(h);
			}
			return tarikh_akhir_tampal;
			//myLogger.info("TARIKH TAMPAL :"+tarikh_akhir_tampal);
			
		}
		finally {
			if (db != null)
				db.close();
		}
		
		}
		
		
		Vector maklumat_siasatan = null;

		public Vector maklumat_siasatan(String id_siasatan) throws Exception {
			maklumat_siasatan = new Vector();
			maklumat_siasatan.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				sql = "SELECT S.TARIKH_PERINTAH,S.NILAI_SEUNIT,S.UNIT_TANAH,PB.ID_PERMOHONAN,S.ULASAN_PERINTAH,S.KEROSAKAN_TANAH,S.KOS_DITANGGUNG,S.ID_PEGAWAI_SIASATAN,PG.USER_NAME AS NAMA_PEGAWAI,S.ULASAN_SIASATAN,PB.ID_PERMOHONAN,S.ID_HAKMILIK,S.BIL_SIASATAN,S.ID_SIASATAN,S.NO_KES,S.NO_SIASATAN,S.STATUS_SIASATAN,S.TARIKH_SIASATAN,S.MASA_SIASATAN, "
						+ " S.TEMPAT,S.ALAMAT1,S.ALAMAT2,S.ALAMAT3,S.POSKOD,S.ID_NEGERI,S.ALASAN_TANGGUH,S.NILAIAN_JPPH,S.ID_UNITLUAS,S.BANTAHAN_TUANTNH,S.BANTAHAN_AGENSI, "
						+ " S.BANTAHAN_LAIN,S.TEMPOH_MILIK_TANAH,S.CARA_MILIK,S.HARGA_BELI,S.JENIS_BANGUNAN,S.JENIS_TANAMAN,S.FLAG_PECAH_SEMPADAN,S.FLAG_TUKAR_SYARAT, "
						+ " S.TARIKH_PECAH_SEMPADAN,S.TARIKH_TUKAR_SYARAT,S.STATUS_SEMASA,S.BEBANAN,S.KETERANGAN_TUAN_TANAH,S.KETERANGAN_AGENSI,S.KETERANGAN_JURUNILAI, "
						+ " S.TUNTUTAN_TUANTNH,S.TUNTUTAN_PB_BEBANAN,S.TUNTUTAN_PB_TDKDAFTAR,S.TUNTUTAN_PB_LAIN,S.ID_BORANGE,S.PERINTAH,S.TARIKH_AKHIR_TAMPAL, "
						+ " S.ID_PEGAWAI_SIASATAN,S.STATUS_TUNTUTAN,S.ID_BANDAR,S.JENIS_WAKTU_SIASATAN, "
						+ " T.ID_TANAH,T.HARGA_SEUNIT_SO,T.UNIT_HARGA_SO,T.BAYAR_PENJEJASAN,T.BAYAR_PECAHPISAH,T.BAYAR_NAIK_NILAISO,T.HARGA_PASARAN_SO,"
						+ " T.HARGA_SEUNIT_JPPH,T.UNIT_HARGA,T.HARGA_PASARAN,T.AMAUN_PENJEJASAN_JPPH,T.AMAUN_PECAHPISAH_JPPH,T.NAIK_NILAI_JPPH,"
						
						+" CASE "+
						" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL THEN HM.NO_LOT "+
						" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || HM.NO_PT  "+
						" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL THEN JL.KETERANGAN  || HM.NO_PT || CHR(32) || CHR(40) || HM.NO_LOT  || CHR(41)  "+
						" ELSE "+
						" '' "+
						" END AS NO_LOT, "					
						+ " HM.NO_HAKMILIK,JH.KETERANGAN AS JENIS_HAKMILIK, "
						+ " JH.KOD_JENIS_HAKMILIK" +
						
						" FROM TBLPPTSIASATAN S,TBLPPTHAKMILIK HM,"						
						+ " TBLRUJJENISHAKMILIK JH,TBLRUJLOT JL," // baru tambah						
						+ "TBLPPTPERMOHONAN PB,USERS PG,TBLPPTTANAH T" 
						+ " WHERE S.ID_PERMOHONAN = PB.ID_PERMOHONAN "
						+ " AND HM.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND HM.ID_LOT = JL.ID_LOT(+)" // baru tambah
						+ " AND HM.ID_PERMOHONAN = PB.ID_PERMOHONAN "
						+ " AND S.ID_HAKMILIK = HM.ID_HAKMILIK"
						+ " AND S.ID_PEGAWAI_SIASATAN = PG.USER_ID(+)"
						+ " AND HM.ID_HAKMILIK = T.ID_HAKMILIK(+) "
						+ " AND S.ID_SIASATAN = '" + id_siasatan + "'" + "";

				myLogger.info("MAKLUMAT SIASATAN :" + sql.toUpperCase());

				ResultSet rs = stmt.executeQuery(sql);

				int bil = 0;
				while (rs.next()) {
					bil = bil + 1;

					Hashtable h = new Hashtable();
					h.put("BIL", bil);
					
					h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
							.getString("NO_LOT"));
					h.put("JENIS_HAKMILIK",
							rs.getString("JENIS_HAKMILIK") == null ? "" : rs
									.getString("JENIS_HAKMILIK"));
					h.put("KOD_JENIS_HAKMILIK",
							rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
									.getString("KOD_JENIS_HAKMILIK"));
					h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
							: rs.getString("NO_HAKMILIK"));
					
					
					h.put("TARIKH_PERINTAH",
							rs.getString("TARIKH_PERINTAH") == null ? "" : Format
									.format(rs.getDate("TARIKH_PERINTAH")));
					
					h.put("NILAI_SEUNIT",rs.getString("NILAI_SEUNIT") == null ? ""
							: rs.getDouble("NILAI_SEUNIT"));				
					
					
					h.put("UNIT_TANAH",
							rs.getString("UNIT_TANAH") == null ? "" : rs
									.getString("UNIT_TANAH"));
					
					

					h.put("ID_PEGAWAI_SIASATAN", rs
							.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
							.getString("ID_PEGAWAI_SIASATAN"));
					h.put("ULASAN_PERINTAH", rs.getString("ULASAN_PERINTAH") == null ? ""
							: rs.getString("ULASAN_PERINTAH"));
				
					h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI") == null ? ""
							: rs.getString("NAMA_PEGAWAI"));
					h.put("KETERANGAN_TUAN_TANAH", rs
							.getString("KETERANGAN_TUAN_TANAH") == null ? "" : rs
							.getString("KETERANGAN_TUAN_TANAH"));
					h.put("ID_PENARIKANBALIK",
							rs.getString("ID_PERMOHONAN") == null ? "" : rs
									.getString("ID_PERMOHONAN"));
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? ""
							: rs.getString("ID_PERMOHONAN"));
					h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
							: rs.getString("ID_HAKMILIK"));
					h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
							: rs.getString("ID_SIASATAN"));
					h.put("NO_KES", rs.getString("NO_KES") == null ? "" : rs
							.getString("NO_KES"));
					h.put("NO_SIASATAN", rs.getString("NO_SIASATAN") == null ? ""
							: rs.getString("NO_SIASATAN"));
					h.put("STATUS_SIASATAN",
							rs.getString("STATUS_SIASATAN") == null ? "" : rs
									.getString("STATUS_SIASATAN"));
					h.put("MASA_SIASATAN",
							rs.getString("MASA_SIASATAN") == null ? "" : rs
									.getString("MASA_SIASATAN"));
					h.put("TARIKH_SIASATAN",
							rs.getString("TARIKH_SIASATAN") == null ? "" : Format
									.format(rs.getDate("TARIKH_SIASATAN")));
					h.put("TEMPAT", rs.getString("TEMPAT") == null ? "" : rs
							.getString("TEMPAT"));
					h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
							.getString("ALAMAT1"));
					h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
							.getString("ALAMAT2"));
					h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
							.getString("ALAMAT3"));
					h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
							.getString("POSKOD"));
					h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
							.getString("ID_NEGERI"));
					h.put("ALASAN_TANGGUH",
							rs.getString("ALASAN_TANGGUH") == null ? "" : rs
									.getString("ALASAN_TANGGUH"));
					h.put("ID_UNITLUAS", rs.getString("ID_UNITLUAS") == null ? ""
							: rs.getString("ID_UNITLUAS"));
					h.put("BANTAHAN_TUANTNH",
							rs.getString("BANTAHAN_TUANTNH") == null ? "" : rs
									.getString("BANTAHAN_TUANTNH"));
					h.put("BANTAHAN_AGENSI",
							rs.getString("BANTAHAN_AGENSI") == null ? "" : rs
									.getString("BANTAHAN_AGENSI"));
					h.put("NILAIAN_JPPH", rs.getString("NILAIAN_JPPH") == null ? ""
							: rs.getDouble("NILAIAN_JPPH"));
					h.put("BANTAHAN_LAIN",
							rs.getString("BANTAHAN_LAIN") == null ? "" : rs
									.getString("BANTAHAN_LAIN"));
					h.put("TEMPOH_MILIK_TANAH",
							rs.getString("TEMPOH_MILIK_TANAH") == null ? "" : rs
									.getString("TEMPOH_MILIK_TANAH"));
					h.put("CARA_MILIK", rs.getString("CARA_MILIK") == null ? ""
							: rs.getString("CARA_MILIK"));
					h.put("JENIS_BANGUNAN",
							rs.getString("JENIS_BANGUNAN") == null ? "" : rs
									.getString("JENIS_BANGUNAN"));
					h.put("JENIS_TANAMAN",
							rs.getString("JENIS_TANAMAN") == null ? "" : rs
									.getString("JENIS_TANAMAN"));
					h.put("FLAG_PECAH_SEMPADAN", rs
							.getString("FLAG_PECAH_SEMPADAN") == null ? "" : rs
							.getString("FLAG_PECAH_SEMPADAN"));
					h.put("FLAG_TUKAR_SYARAT",
							rs.getString("FLAG_TUKAR_SYARAT") == null ? "" : rs
									.getString("FLAG_TUKAR_SYARAT"));
					h.put("HARGA_BELI", rs.getString("HARGA_BELI") == null ? ""
							: rs.getDouble("HARGA_BELI"));
					h.put("STATUS_SEMASA",
							rs.getString("STATUS_SEMASA") == null ? "" : rs
									.getString("STATUS_SEMASA"));
					h.put("BEBANAN", rs.getString("BEBANAN") == null ? "" : rs
							.getString("BEBANAN"));
					h.put("KETERANGAN_AGENSI",
							rs.getString("KETERANGAN_AGENSI") == null ? "" : rs
									.getString("KETERANGAN_AGENSI"));
					h.put("KETERANGAN_JURUNILAI", rs
							.getString("KETERANGAN_JURUNILAI") == null ? "" : rs
							.getString("KETERANGAN_JURUNILAI"));
					h.put("TARIKH_PECAH_SEMPADAN", rs
							.getString("TARIKH_PECAH_SEMPADAN") == null ? ""
							: Format.format(rs.getDate("TARIKH_PECAH_SEMPADAN")));
					h.put("TARIKH_TUKAR_SYARAT", rs
							.getString("TARIKH_TUKAR_SYARAT") == null ? "" : Format
							.format(rs.getDate("TARIKH_TUKAR_SYARAT")));
					h.put("TARIKH_AKHIR_TAMPAL", rs
							.getString("TARIKH_AKHIR_TAMPAL") == null ? "" : Format
							.format(rs.getDate("TARIKH_AKHIR_TAMPAL")));
					h.put("TUNTUTAN_TUANTNH",
							rs.getString("TUNTUTAN_TUANTNH") == null ? "" : rs
									.getString("TUNTUTAN_TUANTNH"));
					h.put("TUNTUTAN_PB_BEBANAN", rs
							.getString("TUNTUTAN_PB_BEBANAN") == null ? "" : rs
							.getString("TUNTUTAN_PB_BEBANAN"));
					h.put("TUNTUTAN_PB_TDKDAFTAR", rs
							.getString("TUNTUTAN_PB_TDKDAFTAR") == null ? "" : rs
							.getString("TUNTUTAN_PB_TDKDAFTAR"));
					h.put("TUNTUTAN_PB_LAIN",
							rs.getString("TUNTUTAN_PB_LAIN") == null ? "" : rs
									.getString("TUNTUTAN_PB_LAIN"));
					h.put("ID_BORANGE", rs.getString("ID_BORANGE") == null ? ""
							: rs.getString("ID_BORANGE"));
					h.put("PERINTAH", rs.getString("PERINTAH") == null ? "" : rs
							.getString("PERINTAH"));
					h.put("ID_PEGAWAI_SIASATAN", rs.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
							.getString("ID_PEGAWAI_SIASATAN"));
					h.put("STATUS_TUNTUTAN",
							rs.getString("STATUS_TUNTUTAN") == null ? "" : rs
									.getString("STATUS_TUNTUTAN"));
					h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
							.getString("ID_BANDAR"));
					h.put("JENIS_WAKTU_SIASATAN", rs
							.getString("JENIS_WAKTU_SIASATAN") == null ? "" : rs
							.getString("JENIS_WAKTU_SIASATAN"));
					h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN") == null ? "1": rs.getString("BIL_SIASATAN"));
					h.put("ULASAN_SIASATAN",
							rs.getString("ULASAN_SIASATAN") == null ? "" : rs
									.getString("ULASAN_SIASATAN"));

					h.put("HARGA_SEUNIT_SO",
							rs.getString("HARGA_SEUNIT_SO") == null ? "" : rs
									.getDouble("HARGA_SEUNIT_SO"));
					h.put("UNIT_HARGA_SO",
							rs.getString("UNIT_HARGA_SO") == null ? "" : rs
									.getString("UNIT_HARGA_SO"));
					h.put("HARGA_PASARAN_SO",
							rs.getString("HARGA_PASARAN_SO") == null ? "" : rs
									.getDouble("HARGA_PASARAN_SO"));
					h.put("BAYAR_PENJEJASAN",
							rs.getString("BAYAR_PENJEJASAN") == null ? "" : rs
									.getDouble("BAYAR_PENJEJASAN"));
					h.put("BAYAR_PECAHPISAH",
							rs.getString("BAYAR_PECAHPISAH") == null ? "" : rs
									.getDouble("BAYAR_PECAHPISAH"));
					h.put("BAYAR_NAIK_NILAISO",
							rs.getString("BAYAR_NAIK_NILAISO") == null ? "" : rs
									.getDouble("BAYAR_NAIK_NILAISO"));
					h.put("HARGA_SEUNIT_JPPH",
							rs.getString("HARGA_SEUNIT_JPPH") == null ? "" : rs
									.getDouble("HARGA_SEUNIT_JPPH"));
					h.put("UNIT_HARGA", rs.getString("UNIT_HARGA") == null ? ""
							: rs.getString("UNIT_HARGA"));
					h.put("HARGA_PASARAN",
							rs.getString("HARGA_PASARAN") == null ? "" : rs
									.getDouble("HARGA_PASARAN"));
					h.put("AMAUN_PENJEJASAN_JPPH", rs
							.getString("AMAUN_PENJEJASAN_JPPH") == null ? "" : rs
							.getDouble("AMAUN_PENJEJASAN_JPPH"));
					h.put("AMAUN_PECAHPISAH_JPPH", rs
							.getString("AMAUN_PECAHPISAH_JPPH") == null ? "" : rs
							.getDouble("AMAUN_PECAHPISAH_JPPH"));
					h.put("NAIK_NILAI_JPPH",
							rs.getString("NAIK_NILAI_JPPH") == null ? "" : rs
									.getDouble("NAIK_NILAI_JPPH"));
					h.put("ID_TANAH", rs.getString("ID_TANAH") == null ? "" : rs
							.getString("ID_TANAH"));
					
					h.put("KEROSAKAN_TANAH", rs.getString("KEROSAKAN_TANAH") == null ? "" : rs
							.getString("KEROSAKAN_TANAH"));
					h.put("KOS_DITANGGUNG", rs.getString("KOS_DITANGGUNG") == null ? "" : rs
							.getString("KOS_DITANGGUNG"));
					
					maklumat_siasatan.addElement(h);

				}
				return maklumat_siasatan;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		public void addMaklumatSiasatan(Hashtable data) throws Exception {
			Db db = null;
			String sql = "";

			String txtNoKes = (String) data.get("txtNoKes");
			String txtNoSiasatan = (String) data.get("txtNoSiasatan");
			String txtTempatSiasatan = (String) data.get("txtTempatSiasatan");
			String txtPoskod = (String) data.get("txtPoskod");
			String txtAlamat1 = (String) data.get("txtAlamat1");
			String txtAlamat2 = (String) data.get("txtAlamat2");
			String txtAlamat3 = (String) data.get("txtAlamat3");
			String socNegeriSiasatan = (String) data.get("socNegeriSiasatan");
			String socBandarSiasatan = (String) data.get("socBandarSiasatan");
			String socStatusSiasatan = (String) data.get("socStatusSiasatan");
			String txtMasaSiasatan = (String) data.get("txtMasaSiasatan");
			String txtAlasan = (String) data.get("txtAlasan");
			String jeniswaktu = (String) data.get("jeniswaktu");
			String txtTkhAkhirNotis = (String) data.get("txtTkhAkhirNotis");
			String txdTarikhSiasatan = (String) data.get("txdTarikhSiasatan");
			String id_Masuk = (String) data.get("id_Masuk");
			String id_pembatalan = (String) data.get("id_pembatalan");
			String id_hakmilik = (String) data.get("id_hakmilik");
			String bil_siasatan = (String) data.get("bil_siasatan");
			
        if(!txtNoKes.equals("") || !txtNoSiasatan.equals("") || !txtTempatSiasatan.equals("") ||
		!txtPoskod.equals("") || !txtAlamat1.equals("") || !txtAlamat2.equals("") || !txtAlamat3.equals("")
		|| !socNegeriSiasatan.equals("") || !socBandarSiasatan.equals("") || !socStatusSiasatan.equals("") 
		|| !txtMasaSiasatan.equals("") || !jeniswaktu.equals("") || !txdTarikhSiasatan.equals("") || !bil_siasatan.equals(""))
        {
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.add("NO_KES", txtNoKes);
				r.add("BIL_SIASATAN", bil_siasatan);
				r.add("NO_SIASATAN", txtNoSiasatan);
				r.add("TEMPAT", txtTempatSiasatan);
				r.add("POSKOD", txtPoskod);
				r.add("ALAMAT1", txtAlamat1);
				r.add("ALAMAT2", txtAlamat2);
				r.add("ALAMAT3", txtAlamat3);
				r.add("ID_NEGERI", socNegeriSiasatan);
				r.add("ID_BANDAR", socBandarSiasatan);
				r.add("STATUS_SIASATAN", socStatusSiasatan);
				r.add("MASA_SIASATAN", txtMasaSiasatan);
				r.add("ALASAN_TANGGUH", txtAlasan);
				r.add("JENIS_WAKTU_SIASATAN", jeniswaktu);

				if (txtTkhAkhirNotis != "") {
					r.add("TARIKH_AKHIR_TAMPAL", r.unquote("to_date('"
							+ txtTkhAkhirNotis + "','dd/MM/yyyy')"));
				} else {
					r.add("TARIKH_AKHIR_TAMPAL", "");
				}

				if (txdTarikhSiasatan != "") {
					r.add("TARIKH_SIASATAN", r.unquote("to_date('"
							+ txdTarikhSiasatan + "','dd/MM/yyyy')"));
				} else {
					r.add("TARIKH_SIASATAN", "");
				}

				r.add("ID_PERMOHONAN", id_pembatalan);
				r.add("ID_HAKMILIK", id_hakmilik);
				r.add("ID_MASUK", id_Masuk);
				r.add("ID_KEMASKINI", id_Masuk);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLInsert("tblpptsiasatan");
				myLogger.info("SQL INSERT SIASATAN :" + sql);
				stmt.executeUpdate(sql);

			} finally {
				if (db != null)
					db.close();
			}
        }

		}

		public void updateMaklumatSiasatan(Hashtable data) throws Exception {
			Db db = null;
			String sql = "";

			String id_siasatan = (String) data.get("id_siasatan");
			String txtNoKes = (String) data.get("txtNoKes");
			String txtNoSiasatan = (String) data.get("txtNoSiasatan");
			String txtTempatSiasatan = (String) data.get("txtTempatSiasatan");
			String txtPoskod = (String) data.get("txtPoskod");
			String txtAlamat1 = (String) data.get("txtAlamat1");
			String txtAlamat2 = (String) data.get("txtAlamat2");
			String txtAlamat3 = (String) data.get("txtAlamat3");
			String socNegeriSiasatan = (String) data.get("socNegeriSiasatan");
			String socBandarSiasatan = (String) data.get("socBandarSiasatan");
			String socStatusSiasatan = (String) data.get("socStatusSiasatan");
			String txtMasaSiasatan = (String) data.get("txtMasaSiasatan");
			String txtAlasan = (String) data.get("txtAlasan");
			String jeniswaktu = (String) data.get("jeniswaktu");
			String txtTkhAkhirNotis = (String) data.get("txtTkhAkhirNotis");
			String txdTarikhSiasatan = (String) data.get("txdTarikhSiasatan");
			String id_Masuk = (String) data.get("id_Masuk");
			String id_pembatalan = (String) data.get("id_pembatalan");
			String id_hakmilik = (String) data.get("id_hakmilik");
			String bil_siasatan = (String) data.get("bil_siasatan");

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.update("ID_SIASATAN", id_siasatan);
				r.add("BIL_SIASATAN", bil_siasatan);
				r.add("NO_KES", txtNoKes);
				r.add("NO_SIASATAN", txtNoSiasatan);
				r.add("TEMPAT", txtTempatSiasatan);
				r.add("POSKOD", txtPoskod);
				r.add("ALAMAT1", txtAlamat1);
				r.add("ALAMAT2", txtAlamat2);
				r.add("ALAMAT3", txtAlamat3);
				r.add("ID_NEGERI", socNegeriSiasatan);
				r.add("ID_BANDAR", socBandarSiasatan);
				r.add("STATUS_SIASATAN", socStatusSiasatan);
				r.add("MASA_SIASATAN", txtMasaSiasatan);
				r.add("ALASAN_TANGGUH", txtAlasan);
				r.add("JENIS_WAKTU_SIASATAN", jeniswaktu);

				if (txtTkhAkhirNotis != "") {
					r.add("TARIKH_AKHIR_TAMPAL", r.unquote("to_date('"
							+ txtTkhAkhirNotis + "','dd/MM/yyyy')"));
				} else {
					r.add("TARIKH_AKHIR_TAMPAL", "");
				}

				if (txdTarikhSiasatan != "") {
					r.add("TARIKH_SIASATAN", r.unquote("to_date('"
							+ txdTarikhSiasatan + "','dd/MM/yyyy')"));
				} else {
					r.add("TARIKH_SIASATAN", "");
				}

				r.add("ID_PERMOHONAN", id_pembatalan);
				r.add("ID_HAKMILIK", id_hakmilik);

				r.add("ID_KEMASKINI", id_Masuk);

				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblpptsiasatan");
				myLogger.info("SQL UPDATE SIASATAN :" + sql);
				stmt.executeUpdate(sql);

			} finally {
				if (db != null)
					db.close();
			}

		}
		
		public void delete_subsiasatan(String id_siasatan) throws Exception {
			Db db = null;

			String sql1 = "";

			try {
				db = new Db();
				Statement stmt1 = db.getStatement();
				sql1 = " DELETE FROM TBLPPTSUBSIASATAN WHERE ID_SIASATAN = "
						+ id_siasatan;
				myLogger.info("SQL DELETE TBLPPTPIHAKBERKEPENTINGAN BY PB:"
						+ sql1);
				stmt1.executeUpdate(sql1);

			} finally {
				if (db != null)
					db.close();
			}

		}
		public void deleteSiasatan(String id_siasatan) throws Exception {
			Db db = null;
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = " DELETE FROM TBLPPTSIASATAN WHERE ID_SIASATAN = "
						+ id_siasatan;
				myLogger.info("SQL DELETE SIASATAN :" + sql);
				stmt.executeUpdate(sql);

			} finally {
				if (db != null)
					db.close();
			}

		}
		

		Vector maklumat_kehadiran = null;

		public Vector maklumat_kehadiran(String id_hakmilikpb) throws Exception {
			maklumat_kehadiran = new Vector();
			maklumat_kehadiran.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = " SELECT HMPB.NAMA_BANK AS NAMA_BANK_TXT,HMPB.NO_HANDPHONE,HMPB.NO_TEL_RUMAH,HM.ID_HAKMILIK,S.ID_SIASATAN,PBALIK.ID_PERMOHONAN,"
						+
						// "H.FLAG_HADIR," +
						"HMPB.ID_PIHAKBERKEPENTINGAN,HMPB.ID_HAKMILIKPB,HMPB.KETERANGAN_JENIS_PB,PB.NAMA_PB,"
						+ "PB.NO_PB,PB.NAMA_KP,HMPB.ID_JENISPB,JPB.KETERANGAN AS JENISPB, "
						+ " HMPB.ALAMAT1,HMPB.ALAMAT2,HMPB.ALAMAT3,HMPB.POSKOD,HMPB.ID_NEGERI,HMPB.ID_BANDAR,N.NAMA_NEGERI, "
						+ " B.KETERANGAN AS NAMA_BANDAR,PB.ID_JENISNOPB,JNOPB.KETERANGAN AS JENISNOPB,HMPB.NO_AKAUN,HMPB.ID_BANK,RB.NAMA_BANK "
						+ " FROM TBLPPTHAKMILIK HM," +
								"TBLPPTPERMOHONAN PBALIK," +
								"TBLPPTSIASATAN S,TBLPPTHAKMILIKPB HMPB, "
						+
						// " TBLPPTKEHADIRAN H," +
						"TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISPB JPB,TBLRUJNEGERI N,TBLRUJBANDAR B, "
						+ " TBLRUJJENISNOPB JNOPB,TBLRUJBANK RB" +
					//			",TBLPPTPENARIKANHAKMILIK PH  "
						 " WHERE " +
					//			" PH.ID_HAKMILIK = HM.ID_HAKMILIK "
						"  HM.ID_PERMOHONAN= S.ID_PERMOHONAN " +

						//		"HM.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
								
						" AND S.ID_PERMOHONAN = PBALIK.ID_PERMOHONAN "
						+
						// " AND H.ID_HAKMILIKPB(+) = HMPB.ID_HAKMILIKPB "+
						// " AND H.ID_SIASATAN(+) = S.ID_SIASATAN "+
						" AND  S.ID_HAKMILIK = HM.ID_HAKMILIK "
						+ " AND HMPB.ID_HAKMILIK = HM.ID_HAKMILIK"
						+ " AND HMPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
						+ " AND HMPB.ID_JENISPB = JPB.ID_JENISPB(+) "
						+ " AND HMPB.ID_NEGERI = N.ID_NEGERI(+) "
						+ " AND HMPB.ID_BANDAR = B.ID_BANDAR(+) "
						+ " AND PB.ID_JENISNOPB = JNOPB.ID_JENISNOPB(+) "
						+ " AND HMPB.ID_BANK = RB.ID_BANK(+) "
						+ " AND HMPB.ID_HAKMILIKPB = '"
						+ id_hakmilikpb
						+ "' "
						+ " ORDER BY NAMA_PB ASC" + "";

				myLogger.info("MAKLUMAT KEHADIRAN PB:" + sql);

				ResultSet rs = stmt.executeQuery(sql);

				int bil = 0;
				String temp = "";
				while (rs.next()) {
					bil = bil + 1;

					Hashtable h = new Hashtable();
					h.put("BIL", bil);
					temp = bil + "k";
					h.put("BILTEMP", temp);
					h.put("KETERANGAN_JENIS_PB", rs.getString("KETERANGAN_JENIS_PB") == null ? ""
							: rs.getString("KETERANGAN_JENIS_PB"));
					h.put("NO_HANDPHONE", rs.getString("NO_HANDPHONE") == null ? ""
							: rs.getString("NO_HANDPHONE"));
					h.put("NO_TEL_RUMAH", rs.getString("NO_TEL_RUMAH") == null ? ""
							: rs.getString("NO_TEL_RUMAH"));
					h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
							: rs.getString("ID_HAKMILIK"));
					h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
							: rs.getString("ID_SIASATAN"));
					h.put("ID_PENARIKANBALIK",
							rs.getString("ID_PERMOHONAN") == null ? "" : rs
									.getString("ID_PERMOHONAN"));
					// h.put("FLAG_HADIR",rs.getString("FLAG_HADIR") == null ? "" :
					// rs.getString("FLAG_HADIR"));
					h.put("ID_PIHAKBERKEPENTINGAN", rs
							.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
							.getString("ID_PIHAKBERKEPENTINGAN"));
					h.put("ID_HAKMILIKPB",
							rs.getString("ID_HAKMILIKPB") == null ? "" : rs
									.getString("ID_HAKMILIKPB"));
					h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
							.getString("NAMA_PB"));
					h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
							.getString("NO_PB"));
					h.put("NAMA_KP", rs.getString("NAMA_KP") == null ? "" : rs
							.getString("NAMA_KP"));
					h.put("ID_JENISPB", rs.getString("ID_JENISPB") == null ? ""
							: rs.getString("ID_JENISPB"));
					// h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" :
					// rs.getString("KETERANGAN"));
					h.put("JENISPB", rs.getString("JENISPB") == null ? "" : rs
							.getString("JENISPB"));
					h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
							.getString("ALAMAT1"));
					h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
							.getString("ALAMAT2"));
					h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
							.getString("ALAMAT3"));
					h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
							.getString("POSKOD"));
					h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
							.getString("ID_NEGERI"));
					h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
							.getString("ID_BANDAR"));
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
							: rs.getString("NAMA_NEGERI"));
					h.put("NAMA_BANDAR", rs.getString("NAMA_BANDAR") == null ? ""
							: rs.getString("NAMA_BANDAR"));
					h.put("ID_JENISNOPB", rs.getString("ID_JENISNOPB") == null ? ""
							: rs.getString("ID_JENISNOPB"));
					h.put("JENISNOPB", rs.getString("JENISNOPB") == null ? "" : rs
							.getString("JENISNOPB"));
					h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs
							.getString("NO_AKAUN"));
					h.put("ID_BANK", rs.getString("ID_BANK") == null ? "" : rs
							.getString("ID_BANK"));
					h.put("NAMA_BANK", rs.getString("NAMA_BANK") == null ? "" : rs
							.getString("NAMA_BANK"));
					
					h.put("NAMA_BANK_TXT", rs.getString("NAMA_BANK_TXT") == null ? "" : rs
							.getString("NAMA_BANK_TXT"));

					
					
					maklumat_kehadiran.addElement(h);
				}
				return maklumat_kehadiran;
			} finally {
				if (db != null)
					db.close();
			}
		}
		public void deleteKehadiranPB(String id_kehadiran) throws Exception {
			Db db = null;

			String sql1 = "";

			try {
				db = new Db();
				Statement stmt1 = db.getStatement();
				sql1 = " DELETE FROM TBLPPTKEHADIRAN WHERE ID_KEHADIRAN = "
						+ id_kehadiran;
				myLogger.info("SQL DELETE TBLPPTKEHADIRAN BY PB:"
						+ sql1);
				stmt1.executeUpdate(sql1);

			} finally {
				if (db != null)
					db.close();
			}

		}

		
		public void updateKehadiran(String id_hakmilikpb, String id_siasatan,
				String id_Masuk) throws Exception {
			Db db = null;
			String sql = "";
			String sql1 = "";

			try {
				db = new Db();

				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("ID_HAKMILIKPB", id_hakmilikpb);
				r.add("ID_SIASATAN", id_siasatan);
				r.add("FLAG_HADIR", 1);
				r.add("ID_KEMASKINI", id_Masuk);
				r.add("ID_MASUK", id_Masuk);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLInsert("tblpptkehadiran");
				myLogger.info("SQL INSERT KEHADIRAN :" + sql);
				stmt.executeUpdate(sql);

			} finally {
				if (db != null)
					db.close();
			}

		}
		public void updatePBKehadiran(String id_pb, String nama_pb, String no_pb,
				String jenis_nopb, String nama_kp, String id_Masuk)
				throws Exception {
			Db db = null;
			String sql = "";
			String sql1 = "";
			try {
				db = new Db();

				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.update("ID_PIHAKBERKEPENTINGAN", id_pb);
				r.add("NAMA_PB", nama_pb);
				r.add("ID_JENISNOPB", jenis_nopb);
				r.add("NAMA_KP", nama_kp);
				r.add("NO_PB", no_pb);
				r.add("ID_KEMASKINI", id_Masuk);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblpptpihakberkepentingan");
				myLogger.info("SQL UPDATE PB KEHADIRAN :" + sql);
				stmt.executeUpdate(sql);
			} finally {
				if (db != null)
					db.close();
			}

		}
		public void updatePBKehadiranDetails(String id_hakmilikpb,
				String id_jenispb, String no_akaun, String id_bank, String alamat1,
				String alamat2, String alamat3, String poskod, String id_negeri,
				String id_bandar, String id_Masuk, String no_hp, String no_tel,String nama_bank,String txtKeteranganPB)
				throws Exception {
			Db db = null;
			String sql = "";
			String sql1 = "";
			try {
				db = new Db();

				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.update("ID_HAKMILIKPB", id_hakmilikpb);
				r.add("KETERANGAN_JENIS_PB", txtKeteranganPB);
				r.add("NO_AKAUN", no_akaun);
				r.add("ID_JENISPB", id_jenispb);
				r.add("ID_BANK", id_bank);
				r.add("NAMA_BANK", nama_bank);
				r.add("ALAMAT1", alamat1);
				r.add("ALAMAT2", alamat2);
				r.add("ALAMAT3", alamat3);
				r.add("POSKOD", poskod);
				r.add("NO_HANDPHONE", no_hp);
				r.add("NO_TEL_RUMAH", no_tel);
				r.add("ID_BANDAR", id_bandar);
				r.add("ID_NEGERI", id_negeri);
				r.add("ID_KEMASKINI", id_Masuk);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblppthakmilikpb");
				myLogger.info("SQL UPDATE PB DETAILS KEHADIRAN :"
						+ sql);
				stmt.executeUpdate(sql);
			} finally {
				if (db != null)
					db.close();
			}

		}
		public void addPBKehadiran(String id_pb1, String nama_pb, String no_pb,
				String jenis_nopb, String nama_kp, String id_Masuk,
				String id_hakmilikpb1, String id_jenispb, String no_akaun,
				String id_bank, String alamat1, String alamat2, String alamat3,
				String poskod, String id_negeri, String id_bandar,
				String id_Masuki, String no_hp, String no_tel, String id_siasatan,
				String id_hakmilik, String txtPBhadir,String namaBank,String txtKeteranganPB) throws Exception {
			long id_pb = DB.getNextID("TBLPPTPIHAKBERKEPENTINGAN_SEQ");
			long id_hakmilikpb = DB.getNextID("TBLPPTHAKMILIKPB_SEQ");
			Db db = null;
			String sql = "";
			String sql1 = "";
			String sql2 = "";
			try {
				db = new Db();

				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("ID_PIHAKBERKEPENTINGAN", id_pb);
				r.add("NAMA_PB", nama_pb);
				r.add("ID_JENISNOPB", jenis_nopb);
				r.add("NAMA_KP", nama_kp);
				r.add("NO_PB", no_pb);
				r.add("ID_KEMASKINI", id_Masuk);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLInsert("tblpptpihakberkepentingan");
				myLogger.info("SQL UPDATE PB KEHADIRAN :" + sql);
				stmt.executeUpdate(sql);

				Statement stmt1 = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				r1.add("ID_HAKMILIKPB", id_hakmilikpb);
				r1.add("KETERANGAN_JENIS_PB", txtKeteranganPB);
				r1.add("ID_PIHAKBERKEPENTINGAN", id_pb);
				r1.add("ID_HAKMILIK", id_hakmilik);
				// r1.add("NO_AKAUN", no_akaun);
				r1.add("NO_AKAUN", no_akaun);
				r1.add("ID_JENISPB", id_jenispb);
				r1.add("ID_BANK", id_bank);
				r1.add("NAMA_BANK", namaBank);
				r1.add("ALAMAT1", alamat1);
				r1.add("ALAMAT2", alamat2);
				r1.add("ALAMAT3", alamat3);
				r1.add("POSKOD", poskod);
				r1.add("NO_HANDPHONE", no_hp);
				r1.add("NO_TEL_RUMAH", no_tel);
				r1.add("ID_BANDAR", id_bandar);
				r1.add("ID_NEGERI", id_negeri);
				r1.add("ID_KEMASKINI", id_Masuk);
				r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
				sql1 = r1.getSQLInsert("tblppthakmilikpb");
				myLogger.info("SQL UPDATE PB DETAILS KEHADIRAN :"
						+ sql);
				stmt1.executeUpdate(sql1);

				if (txtPBhadir.equals("1")) {
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("ID_HAKMILIKPB", id_hakmilikpb);
					r2.add("ID_SIASATAN", id_siasatan);
					r2.add("FLAG_HADIR", 1);
					r2.add("ID_KEMASKINI", id_Masuk);
					r2.add("ID_MASUK", id_Masuk);
					r2.add("TARIKH_MASUK", r2.unquote("sysdate"));
					r2.add("TARIKH_KEMASKINI", r2.unquote("sysdate"));
					sql2 = r2.getSQLInsert("tblpptkehadiran");
					myLogger.info("SQL INSERT KEHADIRAN :" + sql);
					stmt2.executeUpdate(sql2);
				}

			} finally {
				if (db != null)
					db.close();
			}

		}
		public void deleteMaklumatPB(String id_pb) throws Exception {
			Db db = null;

			String sql1 = "";

			try {
				db = new Db();
				Statement stmt1 = db.getStatement();
				sql1 = " DELETE FROM TBLPPTPIHAKBERKEPENTINGAN WHERE ID_PIHAKBERKEPENTINGAN = "
						+ id_pb;
				myLogger.info("SQL DELETE TBLPPTPIHAKBERKEPENTINGAN BY PB:"
						+ sql1);
				stmt1.executeUpdate(sql1);

			} finally {
				if (db != null)
					db.close();
			}

		}

		public void deleteHakmilikPB(String id_hakmilikpb) throws Exception {
			Db db = null;

			String sql1 = "";

			try {
				db = new Db();
				Statement stmt1 = db.getStatement();
				sql1 = " DELETE FROM TBLPPTHAKMILIKPB WHERE ID_HAKMILIKPB = "
						+ id_hakmilikpb;
				myLogger.info("SQL DELETE TBLPPTHAKMILIKPB BY PB:"
						+ sql1);
				stmt1.executeUpdate(sql1);

			} finally {
				if (db != null)
					db.close();
			}

		}
	    

		public void deleteKehadiran(String id_siasatan) throws Exception {
			Db db = null;

			String sql1 = "";

			try {
				db = new Db();
				Statement stmt1 = db.getStatement();
				sql1 = " DELETE FROM TBLPPTKEHADIRAN WHERE ID_SIASATAN = "
						+ id_siasatan;
				myLogger.info("SQL DELETE TBLPPTKEHADIRAN :" + sql1);
				stmt1.executeUpdate(sql1);

			} finally {
				if (db != null)
					db.close();
			}

		}

		
		
		
		Vector list_kehadiran = null;

		public Vector list_kehadiran(String id_siasatan) throws Exception {
			list_kehadiran = new Vector();
			list_kehadiran.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = " SELECT HMPB.NO_HANDPHONE,HMPB.NO_TEL_RUMAH,HM.ID_HAKMILIK,S.ID_SIASATAN,PBALIK.ID_PERMOHONAN,"
						+
						// "H.FLAG_HADIR," +
						"HMPB.ID_PIHAKBERKEPENTINGAN,HMPB.ID_HAKMILIKPB,PB.NAMA_PB,"
						+ "PB.NO_PB,PB.NAMA_KP,HMPB.ID_JENISPB,JPB.KETERANGAN AS JENISPB, "
						+ " HMPB.ALAMAT1,HMPB.ALAMAT2,HMPB.ALAMAT3,HMPB.POSKOD,HMPB.ID_NEGERI,HMPB.ID_BANDAR,N.NAMA_NEGERI, "
						+ " B.KETERANGAN AS NAMA_BANDAR,PB.ID_JENISNOPB,JNOPB.KETERANGAN AS JENISNOPB,HMPB.NO_AKAUN,HMPB.ID_BANK,RB.NAMA_BANK "
						+ " FROM TBLPPTHAKMILIK HM,TBLPPTPERMOHONAN PBALIK,TBLPPTSIASATAN S,TBLPPTHAKMILIKPB HMPB, "
						+
						
						"TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISPB JPB,TBLRUJNEGERI N,TBLRUJBANDAR B, "
						+ " TBLRUJJENISNOPB JNOPB,TBLRUJBANK RB  "
						+ " WHERE " 
						
					//	+" PH.ID_HAKMILIK = HM.ID_HAKMILIK "
						+"  HM.ID_PERMOHONAN = PBALIK.ID_PERMOHONAN "
						+" AND S.ID_PERMOHONAN = PBALIK.ID_PERMOHONAN "
						+
					
						" AND  S.ID_HAKMILIK = HM.ID_HAKMILIK "
						+ " AND HMPB.ID_HAKMILIK = HM.ID_HAKMILIK"
						+ " AND HMPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
						+ " AND HMPB.ID_JENISPB = JPB.ID_JENISPB(+) "
						+ " AND HMPB.ID_NEGERI = N.ID_NEGERI(+) "
						+ " AND HMPB.ID_BANDAR = B.ID_BANDAR(+) "
						+ " AND PB.ID_JENISNOPB = JNOPB.ID_JENISNOPB(+) "
						+ " AND HMPB.ID_BANK = RB.ID_BANK(+) "
						+ " AND NVL(HMPB.ID_JENISPB,0) NOT IN ('40','41','42','27') "
						+

						" AND S.ID_SIASATAN = '"
						+ id_siasatan
						+ "' "
						+ " ORDER BY NAMA_PB ASC" + "";

				ResultSet rs = stmt.executeQuery(sql);

				int bil = 0;
				String temp = "";
				while (rs.next()) {
					bil = bil + 1;

					Hashtable h = new Hashtable();
					h.put("BIL", bil);
					temp = bil + "k";
					h.put("BILTEMP", temp);
					h.put("NO_HANDPHONE", rs.getString("NO_HANDPHONE") == null ? ""
							: rs.getString("NO_HANDPHONE"));
					h.put("NO_TEL_RUMAH", rs.getString("NO_TEL_RUMAH") == null ? ""
							: rs.getString("NO_TEL_RUMAH"));
					h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
							: rs.getString("ID_HAKMILIK"));
					h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
							: rs.getString("ID_SIASATAN"));
					h.put("ID_PENARIKANBALIK",
							rs.getString("ID_PERMOHONAN") == null ? "" : rs
									.getString("ID_PERMOHONAN"));
					// h.put("FLAG_HADIR",rs.getString("FLAG_HADIR") == null ? "" :
					// rs.getString("FLAG_HADIR"));
					h.put("ID_PIHAKBERKEPENTINGAN", rs
							.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
							.getString("ID_PIHAKBERKEPENTINGAN"));
					h.put("ID_HAKMILIKPB",
							rs.getString("ID_HAKMILIKPB") == null ? "" : rs
									.getString("ID_HAKMILIKPB"));
					h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
							.getString("NAMA_PB"));
					h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
							.getString("NO_PB"));
					h.put("NAMA_KP", rs.getString("NAMA_KP") == null ? "" : rs
							.getString("NAMA_KP"));
					h.put("ID_JENISPB", rs.getString("ID_JENISPB") == null ? ""
							: rs.getString("ID_JENISPB"));
					// h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" :
					// rs.getString("KETERANGAN"));
					h.put("JENISPB", rs.getString("JENISPB") == null ? "" : rs
							.getString("JENISPB"));
					h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
							.getString("ALAMAT1"));
					h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
							.getString("ALAMAT2"));
					h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
							.getString("ALAMAT3"));
					h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
							.getString("POSKOD"));
					h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
							.getString("ID_NEGERI"));
					h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
							.getString("ID_BANDAR"));
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
							: rs.getString("NAMA_NEGERI"));
					h.put("NAMA_BANDAR", rs.getString("NAMA_BANDAR") == null ? ""
							: rs.getString("NAMA_BANDAR"));
					h.put("ID_JENISNOPB", rs.getString("ID_JENISNOPB") == null ? ""
							: rs.getString("ID_JENISNOPB"));
					h.put("JENISNOPB", rs.getString("JENISNOPB") == null ? "" : rs
							.getString("JENISNOPB"));
					h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs
							.getString("NO_AKAUN"));
					h.put("ID_BANK", rs.getString("ID_BANK") == null ? "" : rs
							.getString("ID_BANK"));
					h.put("NAMA_BANK", rs.getString("NAMA_BANK") == null ? "" : rs
							.getString("NAMA_BANK"));

					list_kehadiran.addElement(h);
				}
				return list_kehadiran;
			} finally {
				if (db != null)
					db.close();
			}
		}

		Vector list_kehadiran_th = null;

		public Vector list_kehadiran_th(String id_siasatan) throws Exception {
			list_kehadiran_th = new Vector();
			list_kehadiran_th.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = " SELECT HMPB.NO_HANDPHONE,HMPB.NO_TEL_RUMAH,HM.ID_HAKMILIK,S.ID_SIASATAN,PBALIK.ID_PERMOHONAN,"
						+
						// "H.FLAG_HADIR," +
						"HMPB.ID_PIHAKBERKEPENTINGAN,HMPB.ID_HAKMILIKPB,PB.NAMA_PB,"
						+ "PB.NO_PB,PB.NAMA_KP,HMPB.ID_JENISPB,JPB.KETERANGAN AS JENISPB, "
						+ " HMPB.ALAMAT1,HMPB.ALAMAT2,HMPB.ALAMAT3,HMPB.POSKOD,HMPB.ID_NEGERI,HMPB.ID_BANDAR,N.NAMA_NEGERI, "
						+ " B.KETERANGAN AS NAMA_BANDAR,PB.ID_JENISNOPB,JNOPB.KETERANGAN AS JENISNOPB,HMPB.NO_AKAUN,HMPB.ID_BANK,RB.NAMA_BANK "
						+ " FROM TBLPPTHAKMILIK HM," +
								"TBLPPTPERMOHONAN PBALIK," +
								"TBLPPTSIASATAN S,TBLPPTHAKMILIKPB HMPB, "
						+
						
						"TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISPB JPB,TBLRUJNEGERI N,TBLRUJBANDAR B, "
						+ " TBLRUJJENISNOPB JNOPB,TBLRUJBANK RB" 
					//			",TBLPPTPENARIKANHAKMILIK PH "
						+ " WHERE " 
					//	+" PH.ID_HAKMILIK = HM.ID_HAKMILIK "
						+"  HM.ID_PERMOHONAN= PBALIK.ID_PERMOHONAN "
						
						+ " AND S.ID_PERMOHONAN = PBALIK.ID_PERMOHONAN "
						+
						
						" AND  S.ID_HAKMILIK = HM.ID_HAKMILIK "
						+ " AND HMPB.ID_HAKMILIK = HM.ID_HAKMILIK"
						+ " AND HMPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
						+ " AND HMPB.ID_JENISPB = JPB.ID_JENISPB(+) "
						+ " AND HMPB.ID_NEGERI = N.ID_NEGERI(+) "
						+ " AND HMPB.ID_BANDAR = B.ID_BANDAR(+) "
						+ " AND PB.ID_JENISNOPB = JNOPB.ID_JENISNOPB(+) "
						+ " AND HMPB.ID_BANK = RB.ID_BANK(+) "
						+ " AND (HMPB.ID_JENISPB = '40' OR HMPB.ID_JENISPB = '41' OR HMPB.ID_JENISPB = '42' OR HMPB.ID_JENISPB = '27') "
						+ " AND S.ID_SIASATAN = '"
						+ id_siasatan
						+ "' "
						+ " ORDER BY NAMA_PB ASC" + "";

				ResultSet rs = stmt.executeQuery(sql);

				int bil = 0;
				String temp = "";
				while (rs.next()) {
					bil = bil + 1;

					Hashtable h = new Hashtable();
					h.put("BIL", bil);
					temp = bil + "k";
					h.put("BILTEMP", temp);
					h.put("NO_HANDPHONE", rs.getString("NO_HANDPHONE") == null ? ""
							: rs.getString("NO_HANDPHONE"));
					h.put("NO_TEL_RUMAH", rs.getString("NO_TEL_RUMAH") == null ? ""
							: rs.getString("NO_TEL_RUMAH"));
					h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
							: rs.getString("ID_HAKMILIK"));
					h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
							: rs.getString("ID_SIASATAN"));
					h.put("ID_PENARIKANBALIK",
							rs.getString("ID_PERMOHONAN") == null ? "" : rs
									.getString("ID_PERMOHONAN"));
					// h.put("FLAG_HADIR",rs.getString("FLAG_HADIR") == null ? "" :
					// rs.getString("FLAG_HADIR"));
					h.put("ID_PIHAKBERKEPENTINGAN", rs
							.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
							.getString("ID_PIHAKBERKEPENTINGAN"));
					h.put("ID_HAKMILIKPB",
							rs.getString("ID_HAKMILIKPB") == null ? "" : rs
									.getString("ID_HAKMILIKPB"));
					h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
							.getString("NAMA_PB"));
					h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
							.getString("NO_PB"));
					h.put("NAMA_KP", rs.getString("NAMA_KP") == null ? "" : rs
							.getString("NAMA_KP"));
					h.put("ID_JENISPB", rs.getString("ID_JENISPB") == null ? ""
							: rs.getString("ID_JENISPB"));
					// h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" :
					// rs.getString("KETERANGAN"));
					h.put("JENISPB", rs.getString("JENISPB") == null ? "" : rs
							.getString("JENISPB"));
					h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
							.getString("ALAMAT1"));
					h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
							.getString("ALAMAT2"));
					h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
							.getString("ALAMAT3"));
					h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
							.getString("POSKOD"));
					h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
							.getString("ID_NEGERI"));
					h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
							.getString("ID_BANDAR"));
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
							: rs.getString("NAMA_NEGERI"));
					h.put("NAMA_BANDAR", rs.getString("NAMA_BANDAR") == null ? ""
							: rs.getString("NAMA_BANDAR"));
					h.put("ID_JENISNOPB", rs.getString("ID_JENISNOPB") == null ? ""
							: rs.getString("ID_JENISNOPB"));
					h.put("JENISNOPB", rs.getString("JENISNOPB") == null ? "" : rs
							.getString("JENISNOPB"));
					h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs
							.getString("NO_AKAUN"));
					h.put("ID_BANK", rs.getString("ID_BANK") == null ? "" : rs
							.getString("ID_BANK"));
					h.put("NAMA_BANK", rs.getString("NAMA_BANK") == null ? "" : rs
							.getString("NAMA_BANK"));

					list_kehadiran_th.addElement(h);
				}
				return list_kehadiran_th;
			} finally {
				if (db != null)
					db.close();
			}
		}

		
		Vector list_check_kehadiran = null;

		public Vector list_check_kehadiran(String id_siasatan) throws Exception {
			list_check_kehadiran = new Vector();
			list_check_kehadiran.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = "SELECT H.ID_KEHADIRAN,H.ID_HAKMILIKPB,H.FLAG_HADIR,S.ID_SIASATAN " +
						"FROM TBLPPTKEHADIRAN H,TBLPPTSIASATAN S "
						+ "WHERE H.ID_SIASATAN = S.ID_SIASATAN "
						+ "AND S.ID_SIASATAN = '" + id_siasatan + "' " + "";
				myLogger.info("CHECK KEHADIRAN"+sql);
				ResultSet rs = stmt.executeQuery(sql);

				int bil = 0;

				while (rs.next()) {
					bil = bil + 1;

					Hashtable h = new Hashtable();
					h.put("BIL", bil);
					h.put("ID_KEHADIRAN", rs.getString("ID_KEHADIRAN") == null ? ""
							: rs.getString("ID_KEHADIRAN"));
					h.put("ID_HAKMILIKPB",
							rs.getString("ID_HAKMILIKPB") == null ? "" : rs
									.getString("ID_HAKMILIKPB"));
					h.put("FLAG_HADIR", rs.getString("FLAG_HADIR") == null ? ""
							: rs.getString("FLAG_HADIR"));

					list_check_kehadiran.addElement(h);
				}
				return list_check_kehadiran;
			} finally {
				if (db != null)
					db.close();
			}
		}

		
		public void updateTuanTanah(Hashtable data) throws Exception {
			Db db = null;
			String sql = "";
			String txdMilikTanah = (String) data.get("txdMilikTanah");
			String txtCaraMilikTanah = (String) data.get("txtCaraMilikTanah");
			String txtHargaTanah = (String) data.get("txtHargaTanah");
			String txtBebananTanah = (String) data.get("txtBebananTanah");
			String txtKeteranganTuanTanah = (String) data
					.get("txtKeteranganTuanTanah");
			String txtJenisTanaman = (String) data.get("txtJenisTanaman");
			String txtJenisBangunan = (String) data.get("txtJenisBangunan");
			String sorPecahSempadan = (String) data.get("sorPecahSempadan");
			String txdPecahSempadan = (String) data.get("txdPecahSempadan");
			String sorTukarSyarat = (String) data.get("sorTukarSyarat");
			String txdTukarSyarat = (String) data.get("txdTukarSyarat");
			String id_siasatan = (String) data.get("id_siasatan");
			String id_Masuk = (String) data.get("id_Masuk");

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.update("ID_SIASATAN", id_siasatan);
				r.add("TEMPOH_MILIK_TANAH", txdMilikTanah);
				r.add("CARA_MILIK", txtCaraMilikTanah);
				r.add("HARGA_BELI", txtHargaTanah);
				r.add("BEBANAN", txtBebananTanah);
				r.add("KETERANGAN_TUAN_TANAH", txtKeteranganTuanTanah);
				r.add("JENIS_TANAMAN", txtJenisTanaman);
				r.add("JENIS_BANGUNAN", txtJenisBangunan);
				r.add("FLAG_PECAH_SEMPADAN", sorPecahSempadan);
				r.add("FLAG_TUKAR_SYARAT", sorTukarSyarat);

				if (txdPecahSempadan != "") {
					r.add("TARIKH_PECAH_SEMPADAN", r.unquote("to_date('"
							+ txdPecahSempadan + "','dd/MM/yyyy')"));
				} else {
					r.add("TARIKH_PECAH_SEMPADAN", "");
				}

				if (txdTukarSyarat != "") {
					r.add("TARIKH_TUKAR_SYARAT", r.unquote("to_date('"
							+ txdTukarSyarat + "','dd/MM/yyyy')"));
				} else {
					r.add("TARIKH_TUKAR_SYARAT", "");
				}

				r.add("ID_KEMASKINI", id_Masuk);

				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblpptsiasatan");
				stmt.executeUpdate(sql);
				myLogger.info("SQL UPDATE SIASATAN TUAN TANAH :"
						+ sql);

			} finally {
				if (db != null)
					db.close();
			}

		}
		public void updateAgensi(Hashtable data) throws Exception {
			Db db = null;
			String sql = "";
			String txtKeteranganAgensi = (String) data.get("txtKeteranganAgensi");
			String txtKeteranganJurunilai = (String) data
					.get("txtKeteranganJurunilai");

			String id_siasatan = (String) data.get("id_siasatan");
			String id_Masuk = (String) data.get("id_Masuk");

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.update("ID_SIASATAN", id_siasatan);
				r.add("KETERANGAN_AGENSI", txtKeteranganAgensi);
				r.add("KETERANGAN_JURUNILAI", txtKeteranganJurunilai);
				r.add("ID_KEMASKINI", id_Masuk);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblpptsiasatan");
				stmt.executeUpdate(sql);
				myLogger.info("SQL UPDATE SIASATAN AGENSI :" + sql);

			} finally {
				if (db != null)
					db.close();
			}

		}
		
		public void updateTuntutan(Hashtable data) throws Exception {
			Db db = null;
			String sql = "";
			String txtTuntutanTuanTanah = (String) data.get("txtTuntutanTuanTanah");
			String txtLainTuntutan = (String) data.get("txtLainTuntutan");
			String txtPBDaftar = (String) data.get("txtPBDaftar");
			String txtPBXDaftar = (String) data.get("txtPBXDaftar");
			String id_siasatan = (String) data.get("id_siasatan");
			String id_Masuk = (String) data.get("id_Masuk");

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.update("ID_SIASATAN", id_siasatan);
				r.add("TUNTUTAN_TUANTNH", txtTuntutanTuanTanah);
				r.add("TUNTUTAN_PB_LAIN", txtLainTuntutan);
				r.add("TUNTUTAN_PB_BEBANAN", txtPBDaftar);
				r.add("TUNTUTAN_PB_TDKDAFTAR", txtPBXDaftar);
				r.add("ID_KEMASKINI", id_Masuk);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblpptsiasatan");
				stmt.executeUpdate(sql);
				myLogger.info("SQL UPDATE SIASATAN TUNTUTAN :" + sql);

			} finally {
				if (db != null)
					db.close();
			}

		}
		
		public void updateBantahan(Hashtable data) throws Exception {
			Db db = null;
			String sql = "";
			String txtBantahanLain = (String) data.get("txtBantahanLain");
			String txtBantahanAgensi = (String) data.get("txtBantahanAgensi");
			String txtBantahanTuanTanah = (String) data.get("txtBantahanTuanTanah");
			String id_siasatan = (String) data.get("id_siasatan");
			String id_Masuk = (String) data.get("id_Masuk");

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.update("ID_SIASATAN", id_siasatan);
				r.add("BANTAHAN_LAIN", txtBantahanLain);
				r.add("BANTAHAN_AGENSI", txtBantahanAgensi);
				r.add("BANTAHAN_TUANTNH", txtBantahanTuanTanah);
				r.add("ID_KEMASKINI", id_Masuk);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblpptsiasatan");
				stmt.executeUpdate(sql);
				myLogger.info("SQL UPDATE SIASATAN TUNTUTAN :" + sql);

			} finally {
				if (db != null)
					db.close();
			}

		}


		
		
		public void addNilaian(Hashtable data) throws Exception {

			Db db = null;
			String sql = "";
			String sql1 = "";
			String txtHargaSeunitSO = (String) data.get("txtHargaSeunitSO");
			String txtUnitHargaSO = (String) data.get("txtUnitHargaSO");
			String txtHargaPasaranSO = (String) data.get("txtHargaPasaranSO");
			String txtPenjejasanSO = (String) data.get("txtPenjejasanSO");
			String txtPecahPisahSO = (String) data.get("txtPecahPisahSO");
			String txtNaikNilaiSO = (String) data.get("txtNaikNilaiSO");
			String txtHargaSeunitJPPH = (String) data.get("txtHargaSeunitJPPH");
			String txtUnitHargaJPPH = (String) data.get("txtUnitHargaJPPH");
			String txtHargaPasaranJPPH = (String) data.get("txtHargaPasaranJPPH");
			String txtPenjejasanJPPH = (String) data.get("txtPenjejasanJPPH");
			String txtPecahPisahJPPH = (String) data.get("txtPecahPisahJPPH");
			String txtNaikNilaiJPPH = (String) data.get("txtNaikNilaiJPPH");
			String id_siasatan = (String) data.get("id_siasatan");
			String id_Masuk = (String) data.get("id_Masuk");
			String id_hakmilik = (String) data.get("id_hakmilik");
			
			String id_award = (String) data.get("id_award");
			String txtFee = (String) data.get("txtFee");
			String txtBangunan = (String) data.get("txtBangunan");
			String txtTanah = (String) data.get("txtTanah");
			String txtPenjejasan = (String) data.get("txtPenjejasan");
			String txtGantiRugi = (String) data.get("txtGantiRugi");
			String txtNaik = (String) data.get("txtNaik");
			String txtLainPampasan = (String) data.get("txtLainPampasan");
			String txtJumlah = (String) data.get("txtJumlah");
			String txtPecahpisah = (String) data.get("txtPecahpisah");
			String id_hakmilikpb = (String) data.get("id_hakmilikpb");
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("ID_HAKMILIK", id_hakmilik);
				r.add("HARGA_SEUNIT_SO", txtHargaSeunitSO);
				r.add("UNIT_HARGA_SO", txtUnitHargaSO);
				r.add("HARGA_PASARAN_SO", txtHargaPasaranSO);
				r.add("BAYAR_PENJEJASAN", txtPenjejasanSO);
				r.add("BAYAR_PECAHPISAH", txtPecahPisahSO);
				r.add("BAYAR_NAIK_NILAISO", txtNaikNilaiSO);
				r.add("HARGA_SEUNIT_JPPH", txtHargaSeunitJPPH);
				r.add("UNIT_HARGA", txtUnitHargaJPPH);
				r.add("HARGA_PASARAN", txtHargaPasaranJPPH);
				r.add("AMAUN_PENJEJASAN_JPPH", txtPenjejasanJPPH);
				r.add("AMAUN_PECAHPISAH_JPPH", txtPecahPisahJPPH);
				r.add("NAIK_NILAI_JPPH", txtNaikNilaiJPPH);
				r.add("ID_KEMASKINI", id_Masuk);
				r.add("ID_MASUK", id_Masuk);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("tblppttanah");
				stmt.executeUpdate(sql);
				
				/*
				if(id_award.equals("")){
				Statement stmt1 = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				r1.add("BAYAR_FEE", txtFee);
				r1.add("BAYAR_BANGUNAN", txtBangunan);
				r1.add("BAYAR_TANAH", txtTanah);
				r1.add("BAYAR_PENJEJASAN", txtPenjejasan);
				r1.add("NILAI_RUGI", txtGantiRugi);
				r1.add("NAIK_NILAI", txtNaik );
				r1.add("BAYAR_PAMPASAN", txtJumlah);
				r1.add("BAYAR_PECAHPISAH", txtPecahpisah);
				r1.add("BAYAR_LAIN2", txtLainPampasan);
				r1.add("ID_HAKMILIKPB", id_hakmilikpb);			
				r1.add("ID_KEMASKINI", id_Masuk);
				r1.add("ID_MASUK", id_Masuk);
				r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
				r1.add("TARIKH_MASUK", r1.unquote("sysdate"));
				sql1 = r1.getSQLInsert("tblpptaward");
				stmt1.executeUpdate(sql1);
				}
				else
				{
					Statement stmt1 = db.getStatement();
					SQLRenderer r1 = new SQLRenderer();
					r1.update("ID_AWARD", id_award);
					r1.add("BAYAR_FEE", txtFee);
					r1.add("BAYAR_BANGUNAN", txtBangunan);
					r1.add("BAYAR_TANAH", txtTanah);
					r1.add("BAYAR_PENJEJASAN", txtPenjejasan);
					r1.add("NILAI_RUGI", txtGantiRugi);
					r1.add("NAIK_NILAI", txtNaik );
					r1.add("BAYAR_PAMPASAN", txtJumlah);
					r1.add("BAYAR_PECAHPISAH", txtPecahpisah);
					r1.add("BAYAR_LAIN2", txtLainPampasan);
					r1.add("ID_KEMASKINI", id_Masuk);				
					r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));				
					sql1 = r1.getSQLUpdate("tblpptaward");
					stmt1.executeUpdate(sql1);	
				}*/
				
				myLogger.info("SQL INSERT SIASATAN NILAIN :" + sql);

			} finally {
				if (db != null)
					db.close();
			}

		}

		public void updateNilaian(Hashtable data) throws Exception {

			Db db = null;
			String sql = "";
			String sql1 = "";
			String txtHargaSeunitSO = (String) data.get("txtHargaSeunitSO");
			String txtUnitHargaSO = (String) data.get("txtUnitHargaSO");
			String txtHargaPasaranSO = (String) data.get("txtHargaPasaranSO");
			String txtPenjejasanSO = (String) data.get("txtPenjejasanSO");
			String txtPecahPisahSO = (String) data.get("txtPecahPisahSO");
			String txtNaikNilaiSO = (String) data.get("txtNaikNilaiSO");
			String txtHargaSeunitJPPH = (String) data.get("txtHargaSeunitJPPH");
			String txtUnitHargaJPPH = (String) data.get("txtUnitHargaJPPH");
			String txtHargaPasaranJPPH = (String) data.get("txtHargaPasaranJPPH");
			String txtPenjejasanJPPH = (String) data.get("txtPenjejasanJPPH");
			String txtPecahPisahJPPH = (String) data.get("txtPecahPisahJPPH");
			String txtNaikNilaiJPPH = (String) data.get("txtNaikNilaiJPPH");
			String id_siasatan = (String) data.get("id_siasatan");
			String id_Masuk = (String) data.get("id_Masuk");
			String id_tanah = (String) data.get("id_tanah");
			
			String id_award = (String) data.get("id_award");
			String txtFee = (String) data.get("txtFee");
			String txtBangunan = (String) data.get("txtBangunan");
			String txtTanah = (String) data.get("txtTanah");
			String txtPenjejasan = (String) data.get("txtPenjejasan");
			String txtGantiRugi = (String) data.get("txtGantiRugi");
			String txtNaik = (String) data.get("txtNaik");
			String txtLainPampasan = (String) data.get("txtLainPampasan");
			String txtJumlah = (String) data.get("txtJumlah");
			String txtPecahpisah = (String) data.get("txtPecahpisah");
			String id_hakmilikpb = (String) data.get("id_hakmilikpb");
			

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.update("ID_TANAH", id_tanah);
				r.add("HARGA_SEUNIT_SO", txtHargaSeunitSO);
				r.add("UNIT_HARGA_SO", txtUnitHargaSO);
				r.add("HARGA_PASARAN_SO", txtHargaPasaranSO);
				r.add("BAYAR_PENJEJASAN", txtPenjejasanSO);
				r.add("BAYAR_PECAHPISAH", txtPecahPisahSO);
				r.add("BAYAR_NAIK_NILAISO", txtNaikNilaiSO);

				r.add("HARGA_SEUNIT_JPPH", txtHargaSeunitJPPH);
				r.add("UNIT_HARGA", txtUnitHargaJPPH);
				r.add("HARGA_PASARAN", txtHargaPasaranJPPH);
				r.add("AMAUN_PENJEJASAN_JPPH", txtPenjejasanJPPH);
				r.add("AMAUN_PECAHPISAH_JPPH", txtPecahPisahJPPH);
				r.add("NAIK_NILAI_JPPH", txtNaikNilaiJPPH);
				r.add("ID_KEMASKINI", id_Masuk);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblppttanah");
				
				
				/*
				
				if(id_award.equals("")){
				Statement stmt1 = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				r1.add("BAYAR_FEE", txtFee);
				r1.add("BAYAR_BANGUNAN", txtBangunan);
				r1.add("BAYAR_TANAH", txtTanah);
				r1.add("BAYAR_PENJEJASAN", txtPenjejasan);
				r1.add("NILAI_RUGI", txtGantiRugi);
				r1.add("NAIK_NILAI", txtNaik );
				r1.add("BAYAR_PAMPASAN", txtJumlah);
				r1.add("BAYAR_PECAHPISAH", txtPecahpisah);
				r1.add("BAYAR_LAIN2", txtLainPampasan);
				r1.add("ID_HAKMILIKPB", id_hakmilikpb);
				r1.add("ID_KEMASKINI", id_Masuk);
				r1.add("ID_MASUK", id_Masuk);
				r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
				r1.add("TARIKH_MASUK", r1.unquote("sysdate"));
				sql1 = r1.getSQLInsert("tblpptaward");
				stmt1.executeUpdate(sql1);
				}
				else
				{
					Statement stmt1 = db.getStatement();
					SQLRenderer r1 = new SQLRenderer();
					r1.update("ID_AWARD", id_award);
					r1.add("BAYAR_FEE", txtFee);
					r1.add("BAYAR_BANGUNAN", txtBangunan);
					r1.add("BAYAR_TANAH", txtTanah);
					r1.add("BAYAR_PENJEJASAN", txtPenjejasan);
					r1.add("NILAI_RUGI", txtGantiRugi);
					r1.add("NAIK_NILAI", txtNaik );
					r1.add("BAYAR_PAMPASAN", txtJumlah);
					r1.add("BAYAR_PECAHPISAH", txtPecahpisah);
					r1.add("BAYAR_LAIN2", txtLainPampasan);
					r1.add("ID_KEMASKINI", id_Masuk);				
					r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));				
					sql1 = r1.getSQLUpdate("tblpptaward");
					myLogger.info("UPDATE PAMAPASAN:" + sql1);
					stmt1.executeUpdate(sql1);	
				}
*/
				myLogger.info("UPDATE NILAIAN:" + sql + "NILAI ::"
						+ txtNaikNilaiSO);
				stmt.executeUpdate(sql);

			} finally {
				if (db != null)
					db.close();
			}

		}
		public void deleteTanahNilaian(String id_tanah) throws Exception {
			Db db = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				String sql = "DELETE FROM TBLPPTTANAH WHERE ID_TANAH = " + id_tanah;
				stmt.executeUpdate(sql);
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		public void updateStatusSiasatan(Hashtable data) throws Exception {

			Db db = null;
			String sql = "";
			String socPegawaiSiasatan = (String) data.get("socPegawaiSiasatan");
			String socStatusSiasatan = (String) data.get("socStatusSiasatan");
			String txtUlasanSiasatan = (String) data.get("txtUlasanSiasatan");
			String id_siasatan = (String) data.get("id_siasatan");
			String id_Masuk = (String) data.get("id_Masuk");

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.update("ID_SIASATAN", id_siasatan);

				r.add("ID_PEGAWAI_SIASATAN", socPegawaiSiasatan);
				r.add("STATUS_SIASATAN", socStatusSiasatan);
				r.add("ULASAN_SIASATAN", txtUlasanSiasatan);

				r.add("ID_KEMASKINI", id_Masuk);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblpptsiasatan");
				stmt.executeUpdate(sql);
				myLogger
						.info("SQL UPDATE SIASATAN keputusan :" + sql);

			} finally {
				if (db != null)
					db.close();
			}

		}
		
		public void simpan_subsiasatan(String txtUlasanTangguh,String id_siasatan,String column,String user_id) throws Exception {
			Db db = null;
			String sql = "";

		    if(!txtUlasanTangguh.equals(""))
		    {
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.add("KETERANGAN_SUBSIASATAN", txtUlasanTangguh);
				r.add("FLAG_JENIS_SIASATAN", column);
				r.add("ID_SIASATAN", id_siasatan);
				r.add("ID_MASUK", user_id);
				r.add("ID_KEMASKINI", user_id);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLPPTSUBSIASATAN");
				myLogger.info("SQL INSERT TBLPPTSUBSIASATAN :" + sql);
				stmt.executeUpdate(sql);

			} finally {
				if (db != null)
					db.close();
			}
		    }

		}
		
		public void simpan_subAward(String txtUlasanKerosakan,String txtUlasanKerosakanAward,long id_award,String column,String user_id) throws Exception {
			Db db = null;
			String sql = "";

		    if(txtUlasanKerosakan.equals("") && txtUlasanKerosakanAward.equals(""))
		    {
		    	
		    }
		    else
		    {
		    	
		   
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.add("KETERANGAN_SUBAWARD", txtUlasanKerosakan);
				r.add("JUMLAH_SUBAWARD", txtUlasanKerosakanAward);
				r.add("FLAG_JENIS_AWARD", column);
				r.add("ID_AWARD", id_award);
				r.add("ID_MASUK", user_id);
				r.add("ID_KEMASKINI", user_id);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLPPTSUBAWARD");
				myLogger.info("SQL INSERT TBLPPTSUBAWARD :" + sql);
				stmt.executeUpdate(sql);

			} finally {
				if (db != null)
					db.close();
			}
		    }
		}


		Vector maklumat_siasatanX = null;

		public Vector maklumat_siasatanX(String id_permohonan,int bilangan) throws Exception {
			maklumat_siasatanX = new Vector();
			maklumat_siasatanX.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				sql = "SELECT S.ULASAN_PERINTAH,S.KEROSAKAN_TANAH,S.KOS_DITANGGUNG,S.ID_PEGAWAI_SIASATAN,PG.USER_NAME AS NAMA_PEGAWAI,S.ULASAN_SIASATAN,PB.ID_PERMOHONAN,S.ID_HAKMILIK,S.BIL_SIASATAN,S.ID_SIASATAN,S.NO_KES,S.NO_SIASATAN,S.STATUS_SIASATAN,S.TARIKH_SIASATAN,S.MASA_SIASATAN, "
						+ " S.TEMPAT,S.ALAMAT1,S.ALAMAT2,S.ALAMAT3,S.POSKOD,S.ID_NEGERI,S.ALASAN_TANGGUH,S.NILAIAN_JPPH,S.ID_UNITLUAS,S.BANTAHAN_TUANTNH,S.BANTAHAN_AGENSI, "
						+ " S.BANTAHAN_LAIN,S.TEMPOH_MILIK_TANAH,S.CARA_MILIK,S.HARGA_BELI,S.JENIS_BANGUNAN,S.JENIS_TANAMAN,S.FLAG_PECAH_SEMPADAN,S.FLAG_TUKAR_SYARAT, "
						+ " S.TARIKH_PECAH_SEMPADAN,S.TARIKH_TUKAR_SYARAT,S.STATUS_SEMASA,S.BEBANAN,S.KETERANGAN_TUAN_TANAH,S.KETERANGAN_AGENSI,S.KETERANGAN_JURUNILAI, "
						+ " S.TUNTUTAN_TUANTNH,S.TUNTUTAN_PB_BEBANAN,S.TUNTUTAN_PB_TDKDAFTAR,S.TUNTUTAN_PB_LAIN,S.ID_BORANGE,S.PERINTAH,S.TARIKH_AKHIR_TAMPAL, "
						+ " S.ID_PEGAWAI_SIASATAN,S.STATUS_TUNTUTAN,S.ID_BANDAR,S.JENIS_WAKTU_SIASATAN, "
						+ " T.ID_TANAH,T.HARGA_SEUNIT_SO,T.UNIT_HARGA_SO,T.BAYAR_PENJEJASAN,T.BAYAR_PECAHPISAH,T.BAYAR_NAIK_NILAISO,T.HARGA_PASARAN_SO,"
						+ " T.HARGA_SEUNIT_JPPH,T.UNIT_HARGA,T.HARGA_PASARAN,T.AMAUN_PENJEJASAN_JPPH,T.AMAUN_PECAHPISAH_JPPH,T.NAIK_NILAI_JPPH"
						+ " FROM TBLPPTSIASATAN S,TBLPPTHAKMILIK HM,"
						+ "TBLPPTPERMOHONAN PB,USERS PG,TBLPPTTANAH T" +
						//		",TBLPPTPENARIKANHAKMILIK PH "
					    " WHERE S.ID_PERMOHONAN = PB.ID_PERMOHONAN "
					//	+ " AND HM.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
						+" AND PB.ID_PERMOHONAN = HM.ID_PERMOHONAN "
						//+" AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
						+ " AND S.ID_HAKMILIK = HM.ID_HAKMILIK"
						+ " AND S.ID_PEGAWAI_SIASATAN = PG.USER_ID(+)"
						+ " AND HM.ID_HAKMILIK = T.ID_HAKMILIK(+) "
						+ " AND S.BIL_SIASATAN = "+bilangan+" "
						+ " AND PB.ID_PERMOHONAN = '" + id_permohonan + "'" + "";

				myLogger.info("MAKLUMAT SIASATAN :" + sql);

				ResultSet rs = stmt.executeQuery(sql);

				int bil = 0;
				while (rs.next()) {
					bil = bil + 1;

					Hashtable h = new Hashtable();
					h.put("BIL", bil);

					h.put("ID_PEGAWAI_SIASATAN", rs
							.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
							.getString("ID_PEGAWAI_SIASATAN"));
					h.put("ULASAN_PERINTAH", rs.getString("ULASAN_PERINTAH") == null ? ""
							: rs.getString("ULASAN_PERINTAH"));
					h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI") == null ? ""
							: rs.getString("NAMA_PEGAWAI"));
					h.put("KETERANGAN_TUAN_TANAH", rs
							.getString("KETERANGAN_TUAN_TANAH") == null ? "" : rs
							.getString("KETERANGAN_TUAN_TANAH"));
					h.put("ID_PENARIKANBALIK",
							rs.getString("ID_PERMOHONAN") == null ? "" : rs
									.getString("ID_PERMOHONAN"));
					h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
							: rs.getString("ID_HAKMILIK"));
					h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
							: rs.getString("ID_SIASATAN"));
					h.put("NO_KES", rs.getString("NO_KES") == null ? "" : rs
							.getString("NO_KES"));
					h.put("NO_SIASATAN", rs.getString("NO_SIASATAN") == null ? ""
							: rs.getString("NO_SIASATAN"));
					h.put("STATUS_SIASATAN",
							rs.getString("STATUS_SIASATAN") == null ? "" : rs
									.getString("STATUS_SIASATAN"));
					h.put("MASA_SIASATAN",
							rs.getString("MASA_SIASATAN") == null ? "" : rs
									.getString("MASA_SIASATAN"));
					h.put("TARIKH_SIASATAN",
							rs.getString("TARIKH_SIASATAN") == null ? "" : Format
									.format(rs.getDate("TARIKH_SIASATAN")));
					h.put("TEMPAT", rs.getString("TEMPAT") == null ? "" : rs
							.getString("TEMPAT"));
					h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
							.getString("ALAMAT1"));
					h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
							.getString("ALAMAT2"));
					h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
							.getString("ALAMAT3"));
					h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
							.getString("POSKOD"));
					h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
							.getString("ID_NEGERI"));
					h.put("ALASAN_TANGGUH",
							rs.getString("ALASAN_TANGGUH") == null ? "" : rs
									.getString("ALASAN_TANGGUH"));
					h.put("ID_UNITLUAS", rs.getString("ID_UNITLUAS") == null ? ""
							: rs.getString("ID_UNITLUAS"));
					h.put("BANTAHAN_TUANTNH",
							rs.getString("BANTAHAN_TUANTNH") == null ? "" : rs
									.getString("BANTAHAN_TUANTNH"));
					h.put("BANTAHAN_AGENSI",
							rs.getString("BANTAHAN_AGENSI") == null ? "" : rs
									.getString("BANTAHAN_AGENSI"));
					h.put("NILAIAN_JPPH", rs.getString("NILAIAN_JPPH") == null ? ""
							: Format.format(rs.getDouble("NILAIAN_JPPH")));
					h.put("BANTAHAN_LAIN",
							rs.getString("BANTAHAN_LAIN") == null ? "" : rs
									.getString("BANTAHAN_LAIN"));
					h.put("TEMPOH_MILIK_TANAH",
							rs.getString("TEMPOH_MILIK_TANAH") == null ? "" : rs
									.getString("TEMPOH_MILIK_TANAH"));
					h.put("CARA_MILIK", rs.getString("CARA_MILIK") == null ? ""
							: rs.getString("CARA_MILIK"));
					h.put("JENIS_BANGUNAN",
							rs.getString("JENIS_BANGUNAN") == null ? "" : rs
									.getString("JENIS_BANGUNAN"));
					h.put("JENIS_TANAMAN",
							rs.getString("JENIS_TANAMAN") == null ? "" : rs
									.getString("JENIS_TANAMAN"));
					h.put("FLAG_PECAH_SEMPADAN", rs
							.getString("FLAG_PECAH_SEMPADAN") == null ? "" : rs
							.getString("FLAG_PECAH_SEMPADAN"));
					h.put("FLAG_TUKAR_SYARAT",
							rs.getString("FLAG_TUKAR_SYARAT") == null ? "" : rs
									.getString("FLAG_TUKAR_SYARAT"));
					h.put("HARGA_BELI", rs.getString("HARGA_BELI") == null ? ""
							: rs.getDouble("HARGA_BELI"));
					h.put("STATUS_SEMASA",
							rs.getString("STATUS_SEMASA") == null ? "" : rs
									.getString("STATUS_SEMASA"));
					h.put("BEBANAN", rs.getString("BEBANAN") == null ? "" : rs
							.getString("BEBANAN"));
					h.put("KETERANGAN_AGENSI",
							rs.getString("KETERANGAN_AGENSI") == null ? "" : rs
									.getString("KETERANGAN_AGENSI"));
					h.put("KETERANGAN_JURUNILAI", rs
							.getString("KETERANGAN_JURUNILAI") == null ? "" : rs
							.getString("KETERANGAN_JURUNILAI"));
					h.put("TARIKH_PECAH_SEMPADAN", rs
							.getString("TARIKH_PECAH_SEMPADAN") == null ? ""
							: Format.format(rs.getDate("TARIKH_PECAH_SEMPADAN")));
					h.put("TARIKH_TUKAR_SYARAT", rs
							.getString("TARIKH_TUKAR_SYARAT") == null ? "" : Format
							.format(rs.getDate("TARIKH_TUKAR_SYARAT")));
					h.put("TARIKH_AKHIR_TAMPAL", rs
							.getString("TARIKH_AKHIR_TAMPAL") == null ? "" : Format
							.format(rs.getDate("TARIKH_AKHIR_TAMPAL")));
					h.put("TUNTUTAN_TUANTNH",
							rs.getString("TUNTUTAN_TUANTNH") == null ? "" : rs
									.getString("TUNTUTAN_TUANTNH"));
					h.put("TUNTUTAN_PB_BEBANAN", rs
							.getString("TUNTUTAN_PB_BEBANAN") == null ? "" : rs
							.getString("TUNTUTAN_PB_BEBANAN"));
					h.put("TUNTUTAN_PB_TDKDAFTAR", rs
							.getString("TUNTUTAN_PB_TDKDAFTAR") == null ? "" : rs
							.getString("TUNTUTAN_PB_TDKDAFTAR"));
					h.put("TUNTUTAN_PB_LAIN",
							rs.getString("TUNTUTAN_PB_LAIN") == null ? "" : rs
									.getString("TUNTUTAN_PB_LAIN"));
					h.put("ID_BORANGE", rs.getString("ID_BORANGE") == null ? ""
							: rs.getString("ID_BORANGE"));
					h.put("PERINTAH", rs.getString("PERINTAH") == null ? "" : rs
							.getString("PERINTAH"));
					h.put("ID_PEGAWAI_SIASATAN", rs
							.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
							.getString("ID_PEGAWAI_SIASATAN"));
					h.put("STATUS_TUNTUTAN",
							rs.getString("STATUS_TUNTUTAN") == null ? "" : rs
									.getString("STATUS_TUNTUTAN"));
					h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
							.getString("ID_BANDAR"));
					h.put("JENIS_WAKTU_SIASATAN", rs
							.getString("JENIS_WAKTU_SIASATAN") == null ? "" : rs
							.getString("JENIS_WAKTU_SIASATAN"));
					h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN") == null ? "1"
							: rs.getString("BIL_SIASATAN"));
					h.put("ULASAN_SIASATAN",
							rs.getString("ULASAN_SIASATAN") == null ? "" : rs
									.getString("ULASAN_SIASATAN"));

					h.put("HARGA_SEUNIT_SO",
							rs.getString("HARGA_SEUNIT_SO") == null ? "" : rs
									.getDouble("HARGA_SEUNIT_SO"));
					h.put("UNIT_HARGA_SO",
							rs.getString("UNIT_HARGA_SO") == null ? "" : rs
									.getString("UNIT_HARGA_SO"));
					h.put("HARGA_PASARAN_SO",
							rs.getString("HARGA_PASARAN_SO") == null ? "" : rs
									.getDouble("HARGA_PASARAN_SO"));
					h.put("BAYAR_PENJEJASAN",
							rs.getString("BAYAR_PENJEJASAN") == null ? "" : rs
									.getDouble("BAYAR_PENJEJASAN"));
					h.put("BAYAR_PECAHPISAH",
							rs.getString("BAYAR_PECAHPISAH") == null ? "" : rs
									.getDouble("BAYAR_PECAHPISAH"));
					h.put("BAYAR_NAIK_NILAISO",
							rs.getString("BAYAR_NAIK_NILAISO") == null ? "" : rs
									.getDouble("BAYAR_NAIK_NILAISO"));
					h.put("HARGA_SEUNIT_JPPH",
							rs.getString("HARGA_SEUNIT_JPPH") == null ? "" : rs
									.getDouble("HARGA_SEUNIT_JPPH"));
					h.put("UNIT_HARGA", rs.getString("UNIT_HARGA") == null ? ""
							: rs.getString("UNIT_HARGA"));
					h.put("HARGA_PASARAN",
							rs.getString("HARGA_PASARAN") == null ? "" : rs
									.getDouble("HARGA_PASARAN"));
					h.put("AMAUN_PENJEJASAN_JPPH", rs
							.getString("AMAUN_PENJEJASAN_JPPH") == null ? "" : rs
							.getDouble("AMAUN_PENJEJASAN_JPPH"));
					h.put("AMAUN_PECAHPISAH_JPPH", rs
							.getString("AMAUN_PECAHPISAH_JPPH") == null ? "" : rs
							.getDouble("AMAUN_PECAHPISAH_JPPH"));
					h.put("NAIK_NILAI_JPPH",
							rs.getString("NAIK_NILAI_JPPH") == null ? "" : rs
									.getDouble("NAIK_NILAI_JPPH"));
					h.put("ID_TANAH", rs.getString("ID_TANAH") == null ? "" : rs
							.getString("ID_TANAH"));
					
					h.put("KEROSAKAN_TANAH", rs.getString("KEROSAKAN_TANAH") == null ? "" : rs
							.getString("KEROSAKAN_TANAH"));
					h.put("KOS_DITANGGUNG", rs.getString("KOS_DITANGGUNG") == null ? "" : rs
							.getString("KOS_DITANGGUNG"));
					
					maklumat_siasatanX.addElement(h);

				}
				return maklumat_siasatanX;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		
		Vector senarai_pihak_penting_pampasan_perintah = null;

		public Vector senarai_pihak_penting_pampasan_perintah(String id_permohonan,String id_siasatan,String CariPB)
				/*
				String namapb, String nopb, String kplama, String nolot,
				String socMukim)
				*/ throws Exception {
			senarai_pihak_penting_pampasan_perintah = new Vector();
			Db db = null;
			senarai_pihak_penting_pampasan_perintah.clear();
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "SELECT AW.STATUS_PENERIMA,AW.PERINTAH,AW.ULASAN_TIDAK_HADIR,H.ID_HAKMILIK,AW.ID_AWARD,PB.NAMA_PB,PB.ID_PIHAKBERKEPENTINGAN,"
						+ "PB.NO_PB," +
					
						" CASE "+
						" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
						" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
						" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
						" ELSE "+
						" '' "+
						" END AS NO_LOT, "+
						
						" BPB.ID_BAHAGIANPB," +
						"JPB.KETERANGAN AS JENIS_PB,BPB.KETERANGAN_SYER ,"+
						
								"M.NAMA_MUKIM,AW.BAYAR_PAMPASAN,"
						+ "BPB.SYER_ATAS,BPB.SYER_BAWAH,HPB.ID_HAKMILIKPB "
						+ "FROM TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTAWARD AW,TBLPPTSIASATAN S,"
						+ "TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLRUJMUKIM M,TBLPPTBAHAGIANPB BPB," +
								"TBLPPTPERMOHONAN PH," +
								"TBLRUJLOT JL,  "+
								"TBLRUJJENISPB JPB  "
						+ "WHERE HPB.ID_HAKMILIKPB = AW.ID_HAKMILIKPB(+)"
						+ " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK "
						+ " AND NVL(HPB.ID_JENISPB,0) NOT IN ('40','41','42') " +
						"AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
						+" AND HPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+)"
						+ "AND H.ID_MUKIM = M.ID_MUKIM(+) "
						+" AND PH.ID_PERMOHONAN = H.ID_PERMOHONAN "
						+" AND HPB.ID_JENISPB = JPB.ID_JENISPB(+) "
						+" AND H.ID_HAKMILIK = S.ID_HAKMILIK AND H.ID_LOT = JL.ID_LOT(+) "
						+" AND PH.ID_PERMOHONAN = S.ID_PERMOHONAN "
						+" AND S.ID_SIASATAN = '" + id_siasatan + "' "
						+" AND PH.ID_PERMOHONAN = '" + id_permohonan + "'  ";
	/*
				if (namapb != "") {
					sql += "AND  PB.NAMA_PB LIKE '%" + namapb + "%'  ";
				}

				if (nopb != "") {
					sql += "AND  PB.NO_PB LIKE '%" + nopb + "%'  ";
				}

				if (kplama != "") {
					sql += "AND  PB.NO_KP_LAMA LIKE '%" + kplama + "%'  ";
				}

				if (nolot != "") {
					sql += "AND  H.NO_LOT LIKE '%" + nolot + "%'  ";
				}

				if (socMukim != "") {
					sql += "AND  H.ID_MUKIM = '" + socMukim + "'  ";
				}
	*/
				
				if(!CariPB.equals("") && !CariPB.equals(null))
				{
					
					if (!CariPB.trim().equals("")) {
						sql = sql + " AND (UPPER(PB.NO_PB) LIKE '%"
								+ CariPB.toUpperCase().trim() + "%'";					
						
						sql = sql + " OR UPPER(PB.NAMA_PB) LIKE '%"
						+ CariPB.toUpperCase().trim() + "%')";
						
						
					}
					
				}
				sql += "ORDER BY NO_LOT ASC,ID_BAHAGIANPB ASC,NAMA_PB ASC  ";
				myLogger.info("SENARAI PB PAMPASAN PERINTAH SEMENTARA :"
						+ sql);

				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 0;
				while (rs.next()) {
					bil = bil + 1;
					h = new Hashtable();
					h.put("BIL", bil);
					h.put("KETERANGAN_JENIS_PB", rs.getString("KETERANGAN_SYER") == null ? ""
							: rs.getString("KETERANGAN_SYER"));					
					h.put("JENIS_PB", rs.getString("JENIS_PB") == null ? ""
							: rs.getString("JENIS_PB"));
					h.put("ID_BAHAGIANPB",
							rs.getString("ID_BAHAGIANPB") == null ? "" : rs
									.getString("ID_BAHAGIANPB"));
					
					h.put("STATUS_PENERIMA", rs.getString("STATUS_PENERIMA") == null ? ""
							: rs.getString("STATUS_PENERIMA"));
					
					h.put("PERINTAH", rs.getString("PERINTAH") == null ? ""
							: rs.getString("PERINTAH"));
					h.put("ULASAN_TIDAK_HADIR", rs.getString("ULASAN_TIDAK_HADIR") == null ? ""
							: rs.getString("ULASAN_TIDAK_HADIR"));
					
					h.put("ID_AWARD", rs.getString("ID_AWARD") == null ? ""
							: rs.getString("ID_AWARD"));
					h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
							: rs.getString("ID_HAKMILIK"));
					h.put("ID_HAKMILIKPB",
							rs.getString("ID_HAKMILIKPB") == null ? "" : rs
									.getString("ID_HAKMILIKPB"));
					h.put("ID_PIHAKBERKEPENTINGAN", rs
							.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
							.getString("ID_PIHAKBERKEPENTINGAN"));
					h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
							.getString("NAMA_PB"));
					h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
							.getString("NO_PB"));
					h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
							.getString("NO_LOT"));
					h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
							: rs.getString("NAMA_MUKIM"));
					h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? "" : rs
							.getString("SYER_ATAS"));
					h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
							: rs.getString("SYER_BAWAH"));
					h.put("BAYAR_PAMPASAN",
							rs.getString("BAYAR_PAMPASAN") == null ? "" : rs
									.getDouble("BAYAR_PAMPASAN"));
					senarai_pihak_penting_pampasan_perintah.addElement(h);
				}
				return senarai_pihak_penting_pampasan_perintah;
			} finally {
				if (db != null)
					db.close();
			}
		}

		Vector list_subsiasatan = null;	
		public Vector list_subsiasatan(String id_siasatan,String jenis_siasatan) throws Exception {
			list_subsiasatan = new Vector();
			list_subsiasatan.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = "SELECT ID_SUBSIASATAN,KETERANGAN_SUBSIASATAN FROM TBLPPTSUBSIASATAN WHERE ID_SIASATAN = '"+id_siasatan+"' AND FLAG_JENIS_SIASATAN = '"+jenis_siasatan+"' ORDER BY ID_SUBSIASATAN ASC";
				myLogger.info("SQL WARTA :"+sql);
				ResultSet rs = stmt.executeQuery(sql);

	            	
				while (rs.next()) {
					Hashtable h = new Hashtable();	
					if (rs.getString("ID_SUBSIASATAN") == null) {
						h.put("ID_SUBSIASATAN", "");
					} else {
						h.put("ID_SUBSIASATAN", rs.getString("ID_SUBSIASATAN"));
					}
					
					if (rs.getString("KETERANGAN_SUBSIASATAN") == null) {
						h.put("KETERANGAN_SUBSIASATAN", "");
					} else {
						h.put("KETERANGAN_SUBSIASATAN", rs.getString("KETERANGAN_SUBSIASATAN"));
					}
					
					list_subsiasatan.addElement(h);
					}				
				
				
				return list_subsiasatan;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		
		Vector senarai_perintah_award = null;
		
		public Vector senarai_perintah_award(String id_permohonan,String id_siasatan,String id_award)
			 throws Exception {
			senarai_perintah_award = new Vector();
		Db db = null;
		senarai_perintah_award.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT AW.PERINTAH,AW.ULASAN_TIDAK_HADIR,SAW.KETERANGAN_SUBAWARD,AW.STATUS_PENERIMA,SAW.JUMLAH_SUBAWARD,SAW.FLAG_JENIS_AWARD,H.ID_HAKMILIK,AW.ID_AWARD,PB.NAMA_PB,PB.ID_PIHAKBERKEPENTINGAN,"
					+ "PB.NO_PB," +
					" CASE "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
					" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
					" ELSE "+
					" '' "+
					" END AS NO_LOT, "+					
							"M.NAMA_MUKIM,AW.BAYAR_PAMPASAN,"
					+ "BPB.SYER_ATAS,BPB.SYER_BAWAH,HPB.ID_HAKMILIKPB "
					+ "FROM TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTAWARD AW,TBLPPTSIASATAN S,"
					+ "TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLRUJLOT JL,TBLPPTBAHAGIANPB BPB,TBLRUJMUKIM M,TBLPPTPERMOHONAN PH,TBLPPTSUBAWARD SAW  "
					+ "WHERE HPB.ID_HAKMILIKPB = AW.ID_HAKMILIKPB(+)"
					+ " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND NVL(HPB.ID_JENISPB,0) NOT IN ('40','41','42') " 
					+" AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
					+ "AND H.ID_MUKIM = M.ID_MUKIM(+) "
					+" AND PH.ID_PERMOHONAN = H.ID_PERMOHONAN "
					+" AND HPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+)"
					+" AND H.ID_HAKMILIK = S.ID_HAKMILIK "
					+" AND PH.ID_PERMOHONAN = S.ID_PERMOHONAN AND H.ID_LOT = JL.ID_LOT(+) "
					+" AND AW.ID_AWARD = SAW.ID_AWARD "
					+" AND AW.ID_AWARD = '" + id_award + "' "
					+" AND S.ID_SIASATAN = '" + id_siasatan + "' "
					+" AND PH.ID_PERMOHONAN= '" + id_permohonan + "'  ";

			sql += "ORDER BY SAW.ID_SUBAWARD ASC ";
			myLogger.info("SENARAI PERINTAH award :"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("PERINTAH", rs.getString("PERINTAH") == null ? ""
						: rs.getString("PERINTAH"));
				h.put("ULASAN_TIDAK_HADIR", rs.getString("ULASAN_TIDAK_HADIR") == null ? ""
						: rs.getString("ULASAN_TIDAK_HADIR"));
				h.put("STATUS_PENERIMA", rs.getString("STATUS_PENERIMA") == null ? ""
						: rs.getString("STATUS_PENERIMA"));
				
				h.put("ID_AWARD", rs.getString("ID_AWARD") == null ? ""
						: rs.getString("ID_AWARD"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_HAKMILIKPB",
						rs.getString("ID_HAKMILIKPB") == null ? "" : rs
								.getString("ID_HAKMILIKPB"));
				h.put("ID_PIHAKBERKEPENTINGAN", rs
						.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
						.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? "" : rs
						.getString("SYER_ATAS"));
				h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
						: rs.getString("SYER_BAWAH"));
				
				
				
				h.put("BAYAR_PAMPASAN",
						rs.getString("BAYAR_PAMPASAN") == null ? "" : rs
								.getDouble("BAYAR_PAMPASAN"));
				
				h.put("JUMLAH_SUBAWARD",
						rs.getString("JUMLAH_SUBAWARD") == null ? "" : rs
								.getDouble("JUMLAH_SUBAWARD"));
				

				h.put("KETERANGAN_SUBAWARD",
						rs.getString("KETERANGAN_SUBAWARD") == null ? "" : rs
								.getString("KETERANGAN_SUBAWARD"));
				
				h.put("FLAG_JENIS_AWARD",
						rs.getString("FLAG_JENIS_AWARD") == null ? "" : rs
								.getString("FLAG_JENIS_AWARD"));
				
				
				senarai_perintah_award.addElement(h);
			}
			return senarai_perintah_award;
		} finally {
			if (db != null)
				db.close();
		}
		}
		
	/*	public Hashtable maklumat_pb(String id_hakmilikpb)
		 throws Exception {	
	Db db = null;
	String sql = "";
	try {
		db = new Db();
		Statement stmt = db.getStatement();
		sql = " SELECT PB.NAMA_PB,PB.NO_PB,NOPB.KETERANGAN, "+
		
	
		
		
		" CASE "+
		" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
		" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
		" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
		" ELSE "+
		" '' "+
		" END AS NO_LOT "+
		
		
		" FROM TBLPPTHAKMILIKPB HPB,TBLRUJLOT JL,TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISNOPB NOPB,TBLPPTHAKMILIK H "+
		" WHERE HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "+
		" AND PB.ID_JENISNOPB = NOPB.ID_JENISNOPB(+) "+
		" AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_LOT = JL.ID_LOT(+) "+
	
		" AND HPB.ID_HAKMILIKPB = '"+id_hakmilikpb+"' ";

		sql += "ORDER BY NO_LOT ASC,NAMA_PB ASC ";
		myLogger.info("SENARAI PB award :"
				+ sql);

		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		h = new Hashtable();
		int bil = 0;
		while (rs.next()) {
		h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? ""
					: rs.getString("NAMA_PB"));
		
		h.put("NO_PB", rs.getString("NO_PB") == null ? ""
				: rs.getString("NO_PB"));
		
		h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
				: rs.getString("KETERANGAN"));
		
		h.put("NO_LOT", rs.getString("NO_LOT") == null ? ""
				: rs.getString("NO_LOT"));
		}
		return h;
	} finally {
		if (db != null)
			db.close();
	}
	}

	*/
	
	
	   public Hashtable maklumat_pb(String id_hakmilikpb)
	throws Exception {	
	Db db = null;
	String sql = "";
	try {
		db = new Db();
		Statement stmt = db.getStatement();
		sql = " SELECT PB.NAMA_PB,PB.NO_PB,NOPB.KETERANGAN, "+

		" CASE "+
		" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
		" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
		" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
		" ELSE "+
		" '' "+
		" END AS NO_LOT,BPB.ID_BAHAGIANPB,BPB.SYER_ATAS,BPB.SYER_BAWAH "+

		" FROM TBLPPTHAKMILIKPB HPB,TBLPPTBAHAGIANPB BPB,TBLRUJLOT JL,TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISNOPB NOPB,TBLPPTHAKMILIK H "+
		" WHERE HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "+
		" AND PB.ID_JENISNOPB = NOPB.ID_JENISNOPB(+) "+		
		" AND HPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+) "+
		" AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_LOT = JL.ID_LOT(+) "+
	
		" AND HPB.ID_HAKMILIKPB = '"+id_hakmilikpb+"' ";

		sql += "ORDER BY NO_LOT ASC,NAMA_PB ASC ";
		myLogger.info("SENARAI PB award :"
				+ sql);

		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		h = new Hashtable();
		int bil = 0;
		while (rs.next()) {
			
			
		h.put("ID_BAHAGIANPB", rs.getString("ID_BAHAGIANPB") == null ? ""
					: rs.getString("ID_BAHAGIANPB"));
		h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? ""
				: rs.getString("SYER_ATAS"));
		h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
				: rs.getString("SYER_BAWAH"));		
		h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? ""
					: rs.getString("NAMA_PB"));
		
		h.put("NO_PB", rs.getString("NO_PB") == null ? ""
				: rs.getString("NO_PB"));
		
		h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
				: rs.getString("KETERANGAN"));
		
		h.put("NO_LOT", rs.getString("NO_LOT") == null ? ""
				: rs.getString("NO_LOT"));
		}
		return h;
	} finally {
		if (db != null)
			db.close();
	}
	}


		
		public void updateAwardPerintah(String status,String catatan_tdkhadir,String catatan_perintah,String id_award,String id_hakmilikpb,String jumlahaward,String id_Masuk, 
				String idPermohonan, String idHakmilik, String idSiasatan) throws Exception {

			Db db = null;
			String sql = "";
			String sql1 = "";
			String sql2 = "";
			

			try {
				db = new Db();
				
				if(id_award.equals("")){
					
				// TBLPPTAWARD	
				Statement stmt1 = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				
				r1.add("BAYAR_PAMPASAN", jumlahaward);			
				r1.add("ID_HAKMILIKPB", id_hakmilikpb);
				r1.add("ID_SIASATAN", idSiasatan);
				
				r1.add("ULASAN_TIDAK_HADIR", catatan_tdkhadir);
				r1.add("PERINTAH", catatan_perintah);
				r1.add("STATUS_PENERIMA", status);
				
				r1.add("ID_KEMASKINI", id_Masuk);
				r1.add("ID_MASUK", id_Masuk);
				r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
				r1.add("TARIKH_MASUK", r1.unquote("sysdate"));
				sql1 = r1.getSQLInsert("tblpptaward");
				stmt1.executeUpdate(sql1);
				}
				else
				{
					// TBLPPTAWARD
					Statement stmt1 = db.getStatement();
					SQLRenderer r1 = new SQLRenderer();
					r1.update("ID_AWARD", id_award);			
					r1.add("BAYAR_PAMPASAN", jumlahaward);	
					r1.add("ID_SIASATAN", idSiasatan);
					r1.add("ID_KEMASKINI", id_Masuk);	
					
					r1.add("ULASAN_TIDAK_HADIR", catatan_tdkhadir);
					r1.add("PERINTAH", catatan_perintah);
					r1.add("STATUS_PENERIMA", status);
					
					r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));				
					sql1 = r1.getSQLUpdate("tblpptaward");
					myLogger.info("UPDATE PAMPASAN:" + sql1);
					stmt1.executeUpdate(sql1);	
					
					// TBLPPTBORANGQ	
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					
					r2.update("id_permohonan", idPermohonan);
					r2.update("id_hakmilik", idHakmilik);
					r2.add("TAWARAN_PAMPASAN", jumlahaward);
					r2.add("ID_KEMASKINI", id_Masuk);
					r2.add("TARIKH_KEMASKINI", r2.unquote("sysdate"));
					sql2 = r2.getSQLUpdate("tblpptborangq");
					stmt2.executeUpdate(sql2);
				}

				myLogger.info("INSERT PAMPASAN PERINTAH :" + sql);
				myLogger.info("UPDATE PAMPASAN PERINTAH :" + sql1);				
				myLogger.info("UPDATE PAMPASAN BORANG Q :" + sql2);

			} finally {
				if (db != null)
					db.close();
			}

		}
		
		
		public void addAwardPerintah(String status,String id_siasatan,long id_award,String id_hakmilikpb,String jumlahaward,String id_Masuk,String catatan_tdkhadir,String catatan_perintah) throws Exception {

			Db db = null;
			String sql = "";
			String sql1 = "";
			
			

			try {
				db = new Db();
				
				
				Statement stmt1 = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				
				r1.add("ID_AWARD", id_award);
				
				
				r1.add("ULASAN_TIDAK_HADIR", catatan_tdkhadir);
				r1.add("PERINTAH", catatan_perintah);
				r1.add("STATUS_PENERIMA", status);
				
				r1.add("BAYAR_PAMPASAN", jumlahaward);			
				r1.add("ID_HAKMILIKPB", id_hakmilikpb);
				r1.add("ID_SIASATAN", id_siasatan);
				r1.add("ID_KEMASKINI", id_Masuk);
				r1.add("ID_MASUK", id_Masuk);
				r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
				r1.add("TARIKH_MASUK", r1.unquote("sysdate"));
				sql1 = r1.getSQLInsert("tblpptaward");
				stmt1.executeUpdate(sql1);
				

				myLogger.info("UPDATE PAMPASAN PERINTAH:" + sql);
				

			} finally {
				if (db != null)
					db.close();
			}

		}
		
		
		
		public void delete_subaward(String id_award) throws Exception {
			Db db = null;

			String sql1 = "";

			try {
				db = new Db();
				Statement stmt1 = db.getStatement();
				sql1 = " DELETE FROM TBLPPTSUBAWARD WHERE ID_AWARD = "
						+ id_award;
				myLogger.info("SQL DELETE TBLPPTSUBAWARD BY PB:"
						+ sql1);
				stmt1.executeUpdate(sql1);

			} finally {
				if (db != null)
					db.close();
			}

		}
		public void updateKeputusan(Hashtable data) throws Exception {

			Db db = null;
			String sql = "";
			//String socPegawaiSiasatan = (String) data.get("socPegawaiSiasatan");
			String socStatusSiasatan = (String) data.get("socStatusSiasatan");
			String txtUlasanPerintah = (String) data.get("txtUlasanPerintah");
			String id_siasatan = (String) data.get("id_siasatan");
			String id_Masuk = (String) data.get("id_Masuk");
			
			String tarikh_perintah = (String) data.get("tarikh_perintah");
			String socUnit = (String) data.get("socUnit");
			String nilai_seunit = (String) data.get("nilai_seunit");

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.update("ID_SIASATAN", id_siasatan);

				//r.add("ID_PEGAWAI_SIASATAN", socPegawaiSiasatan);
			//	r.add("STATUS_SIASATAN", socStatusSiasatan);
				r.add("ULASAN_PERINTAH", txtUlasanPerintah);
				
				
				
				if (tarikh_perintah != "") {
					r.add("TARIKH_PERINTAH", r.unquote("to_date('"
							+ tarikh_perintah + "','dd/MM/yyyy')"));
				} else {
					r.add("TARIKH_PERINTAH", "");
				}
				
				
				r.add("UNIT_TANAH", socUnit);
				r.add("NILAI_SEUNIT", nilai_seunit);
				

				r.add("ID_KEMASKINI", id_Masuk);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblpptsiasatan");
				stmt.executeUpdate(sql);
				myLogger
						.info("SQL UPDATE SIASATAN keputusan :" + sql);

			} finally {
				if (db != null)
					db.close();
			}

		}
		
		
		Vector maklumat_siasatan_history = null;
		public Vector maklumat_siasatan_history(String id_permohonan,String id_hakmilik,int bil_siasatan) throws Exception {
			maklumat_siasatan_history = new Vector();
			maklumat_siasatan_history.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				sql = "SELECT S.ID_PERMOHONAN,S.ULASAN_PERINTAH,S.KEROSAKAN_TANAH,S.KOS_DITANGGUNG,S.ID_PEGAWAI_SIASATAN,PG.USER_NAME AS NAMA_PEGAWAI,S.ULASAN_SIASATAN,PB.ID_PERMOHONAN,S.ID_HAKMILIK,S.BIL_SIASATAN,S.ID_SIASATAN,S.NO_KES,S.NO_SIASATAN,S.STATUS_SIASATAN,S.TARIKH_SIASATAN,S.MASA_SIASATAN, "
					+ " S.TEMPAT,S.ALAMAT1,S.ALAMAT2,S.ALAMAT3,S.POSKOD,S.ID_NEGERI,S.ALASAN_TANGGUH,S.NILAIAN_JPPH,S.ID_UNITLUAS,S.BANTAHAN_TUANTNH,S.BANTAHAN_AGENSI, "
					+ " S.BANTAHAN_LAIN,S.TEMPOH_MILIK_TANAH,S.CARA_MILIK,S.HARGA_BELI,S.JENIS_BANGUNAN,S.JENIS_TANAMAN,S.FLAG_PECAH_SEMPADAN,S.FLAG_TUKAR_SYARAT, "
					+ " S.TARIKH_PECAH_SEMPADAN,S.TARIKH_TUKAR_SYARAT,S.STATUS_SEMASA,S.BEBANAN,S.KETERANGAN_TUAN_TANAH,S.KETERANGAN_AGENSI,S.KETERANGAN_JURUNILAI, "
					+ " S.TUNTUTAN_TUANTNH,S.TUNTUTAN_PB_BEBANAN,S.TUNTUTAN_PB_TDKDAFTAR,S.TUNTUTAN_PB_LAIN,S.ID_BORANGE,S.PERINTAH,S.TARIKH_AKHIR_TAMPAL, "
					+ " S.ID_PEGAWAI_SIASATAN,S.STATUS_TUNTUTAN,S.ID_BANDAR,S.JENIS_WAKTU_SIASATAN, "
					+ " T.ID_TANAH,T.HARGA_SEUNIT_SO,T.UNIT_HARGA_SO,T.BAYAR_PENJEJASAN,T.BAYAR_PECAHPISAH,T.BAYAR_NAIK_NILAISO,T.HARGA_PASARAN_SO,"
					+ " T.HARGA_SEUNIT_JPPH,T.UNIT_HARGA,T.HARGA_PASARAN,T.AMAUN_PENJEJASAN_JPPH,T.AMAUN_PECAHPISAH_JPPH,T.NAIK_NILAI_JPPH"
					+ " FROM TBLPPTSIASATAN S,TBLPPTHAKMILIK HM,"
					+ "TBLPPTPERMOHONAN PB,USERS PG,TBLPPTTANAH T" +
					//		",TBLPPTPENARIKANHAKMILIK PH "
					 " WHERE S.ID_PERMOHONAN = PB.ID_PERMOHONAN "
				//	+ " AND HM.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
				//	+" AND PH.ID_HAKMILIK = HM.ID_HAKMILIK "
					+" AND HM.ID_PERMOHONAN = PB.ID_PERMOHONAN "
					+ " AND S.ID_HAKMILIK = HM.ID_HAKMILIK"
					+ " AND S.ID_PEGAWAI_SIASATAN = PG.USER_ID(+)"
					+ " AND HM.ID_HAKMILIK = T.ID_HAKMILIK(+) "
						+ " AND S.ID_PERMOHONAN = '" + id_permohonan + "'" 
						+ " AND S.ID_HAKMILIK = '" + id_hakmilik + "'" 
						+ " AND S.BIL_SIASATAN = '" + bil_siasatan + "'" 
						+ "";

				myLogger.info("MAKLUMAT SIASATAN HISTORY :" + sql);

				ResultSet rs = stmt.executeQuery(sql);

				int bil = 0;
				while (rs.next()) {
					bil = bil + 1;

					Hashtable h = new Hashtable();
					h.put("BIL", bil);

					h.put("ID_PEGAWAI_SIASATAN", rs
							.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
							.getString("ID_PEGAWAI_SIASATAN"));
					h.put("ULASAN_PERINTAH", rs.getString("ULASAN_PERINTAH") == null ? ""
							: rs.getString("ULASAN_PERINTAH"));
					h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI") == null ? ""
							: rs.getString("NAMA_PEGAWAI"));
					h.put("KETERANGAN_TUAN_TANAH", rs
							.getString("KETERANGAN_TUAN_TANAH") == null ? "" : rs
							.getString("KETERANGAN_TUAN_TANAH"));
					h.put("ID_PERMOHONAN",
							rs.getString("ID_PERMOHONAN") == null ? "" : rs
									.getString("ID_PERMOHONAN"));
				h.put("ID_PENARIKANBALIK",
							rs.getString("ID_PERMOHONAN") == null ? "" : rs
									.getString("ID_PERMOHONAN"));
					h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
							: rs.getString("ID_HAKMILIK"));
					h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
							: rs.getString("ID_SIASATAN"));
					h.put("NO_KES", rs.getString("NO_KES") == null ? "" : rs
							.getString("NO_KES"));
					h.put("NO_SIASATAN", rs.getString("NO_SIASATAN") == null ? ""
							: rs.getString("NO_SIASATAN"));
					h.put("STATUS_SIASATAN",
							rs.getString("STATUS_SIASATAN") == null ? "" : rs
									.getString("STATUS_SIASATAN"));
					h.put("MASA_SIASATAN",
							rs.getString("MASA_SIASATAN") == null ? "" : rs
									.getString("MASA_SIASATAN"));
					h.put("TARIKH_SIASATAN",
							rs.getString("TARIKH_SIASATAN") == null ? "" : Format
									.format(rs.getDate("TARIKH_SIASATAN")));
					h.put("TEMPAT", rs.getString("TEMPAT") == null ? "" : rs
							.getString("TEMPAT"));
					h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
							.getString("ALAMAT1"));
					h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
							.getString("ALAMAT2"));
					h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
							.getString("ALAMAT3"));
					h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
							.getString("POSKOD"));
					h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
							.getString("ID_NEGERI"));
					h.put("ALASAN_TANGGUH",
							rs.getString("ALASAN_TANGGUH") == null ? "" : rs
									.getString("ALASAN_TANGGUH"));
					h.put("ID_UNITLUAS", rs.getString("ID_UNITLUAS") == null ? ""
							: rs.getString("ID_UNITLUAS"));
					h.put("BANTAHAN_TUANTNH",
							rs.getString("BANTAHAN_TUANTNH") == null ? "" : rs
									.getString("BANTAHAN_TUANTNH"));
					h.put("BANTAHAN_AGENSI",
							rs.getString("BANTAHAN_AGENSI") == null ? "" : rs
									.getString("BANTAHAN_AGENSI"));
					h.put("NILAIAN_JPPH", rs.getString("NILAIAN_JPPH") == null ? ""
							: Format.format(rs.getDouble("NILAIAN_JPPH")));
					h.put("BANTAHAN_LAIN",
							rs.getString("BANTAHAN_LAIN") == null ? "" : rs
									.getString("BANTAHAN_LAIN"));
					h.put("TEMPOH_MILIK_TANAH",
							rs.getString("TEMPOH_MILIK_TANAH") == null ? "" : rs
									.getString("TEMPOH_MILIK_TANAH"));
					h.put("CARA_MILIK", rs.getString("CARA_MILIK") == null ? ""
							: rs.getString("CARA_MILIK"));
					h.put("JENIS_BANGUNAN",
							rs.getString("JENIS_BANGUNAN") == null ? "" : rs
									.getString("JENIS_BANGUNAN"));
					h.put("JENIS_TANAMAN",
							rs.getString("JENIS_TANAMAN") == null ? "" : rs
									.getString("JENIS_TANAMAN"));
					h.put("FLAG_PECAH_SEMPADAN", rs
							.getString("FLAG_PECAH_SEMPADAN") == null ? "" : rs
							.getString("FLAG_PECAH_SEMPADAN"));
					
					h.put("FLAG_TUKAR_SYARAT",
							rs.getString("FLAG_TUKAR_SYARAT") == null ? "" : rs
									.getString("FLAG_TUKAR_SYARAT"));
					
					
					h.put("HARGA_BELI", rs.getString("HARGA_BELI") == null ? ""
							: rs.getDouble("HARGA_BELI"));
					h.put("STATUS_SEMASA",
							rs.getString("STATUS_SEMASA") == null ? "" : rs
									.getString("STATUS_SEMASA"));
					h.put("BEBANAN", rs.getString("BEBANAN") == null ? "" : rs
							.getString("BEBANAN"));
					h.put("KETERANGAN_AGENSI",
							rs.getString("KETERANGAN_AGENSI") == null ? "" : rs
									.getString("KETERANGAN_AGENSI"));
					h.put("KETERANGAN_JURUNILAI", rs
							.getString("KETERANGAN_JURUNILAI") == null ? "" : rs
							.getString("KETERANGAN_JURUNILAI"));
					h.put("TARIKH_PECAH_SEMPADAN", rs
							.getString("TARIKH_PECAH_SEMPADAN") == null ? ""
							: Format.format(rs.getDate("TARIKH_PECAH_SEMPADAN")));
					h.put("TARIKH_TUKAR_SYARAT", rs
							.getString("TARIKH_TUKAR_SYARAT") == null ? "" : Format
							.format(rs.getDate("TARIKH_TUKAR_SYARAT")));
					h.put("TARIKH_AKHIR_TAMPAL", rs
							.getString("TARIKH_AKHIR_TAMPAL") == null ? "" : Format
							.format(rs.getDate("TARIKH_AKHIR_TAMPAL")));
					h.put("TUNTUTAN_TUANTNH",
							rs.getString("TUNTUTAN_TUANTNH") == null ? "" : rs
									.getString("TUNTUTAN_TUANTNH"));
					h.put("TUNTUTAN_PB_BEBANAN", rs
							.getString("TUNTUTAN_PB_BEBANAN") == null ? "" : rs
							.getString("TUNTUTAN_PB_BEBANAN"));
					h.put("TUNTUTAN_PB_TDKDAFTAR", rs
							.getString("TUNTUTAN_PB_TDKDAFTAR") == null ? "" : rs
							.getString("TUNTUTAN_PB_TDKDAFTAR"));
					h.put("TUNTUTAN_PB_LAIN",
							rs.getString("TUNTUTAN_PB_LAIN") == null ? "" : rs
									.getString("TUNTUTAN_PB_LAIN"));
					h.put("ID_BORANGE", rs.getString("ID_BORANGE") == null ? ""
							: rs.getString("ID_BORANGE"));
					h.put("PERINTAH", rs.getString("PERINTAH") == null ? "" : rs
							.getString("PERINTAH"));
					h.put("ID_PEGAWAI_SIASATAN", rs
							.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
							.getString("ID_PEGAWAI_SIASATAN"));
					h.put("STATUS_TUNTUTAN",
							rs.getString("STATUS_TUNTUTAN") == null ? "" : rs
									.getString("STATUS_TUNTUTAN"));
					h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
							.getString("ID_BANDAR"));
					h.put("JENIS_WAKTU_SIASATAN", rs
							.getString("JENIS_WAKTU_SIASATAN") == null ? "" : rs
							.getString("JENIS_WAKTU_SIASATAN"));
					h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN") == null ? "1"
							: rs.getString("BIL_SIASATAN"));
					h.put("ULASAN_SIASATAN",
							rs.getString("ULASAN_SIASATAN") == null ? "" : rs
									.getString("ULASAN_SIASATAN"));

					h.put("HARGA_SEUNIT_SO",
							rs.getString("HARGA_SEUNIT_SO") == null ? "" : rs
									.getDouble("HARGA_SEUNIT_SO"));
					h.put("UNIT_HARGA_SO",
							rs.getString("UNIT_HARGA_SO") == null ? "" : rs
									.getString("UNIT_HARGA_SO"));
					h.put("HARGA_PASARAN_SO",
							rs.getString("HARGA_PASARAN_SO") == null ? "" : rs
									.getDouble("HARGA_PASARAN_SO"));
					h.put("BAYAR_PENJEJASAN",
							rs.getString("BAYAR_PENJEJASAN") == null ? "" : rs
									.getDouble("BAYAR_PENJEJASAN"));
					h.put("BAYAR_PECAHPISAH",
							rs.getString("BAYAR_PECAHPISAH") == null ? "" : rs
									.getDouble("BAYAR_PECAHPISAH"));
					h.put("BAYAR_NAIK_NILAISO",
							rs.getString("BAYAR_NAIK_NILAISO") == null ? "" : rs
									.getDouble("BAYAR_NAIK_NILAISO"));
					h.put("HARGA_SEUNIT_JPPH",
							rs.getString("HARGA_SEUNIT_JPPH") == null ? "" : rs
									.getDouble("HARGA_SEUNIT_JPPH"));
					h.put("UNIT_HARGA", rs.getString("UNIT_HARGA") == null ? ""
							: rs.getString("UNIT_HARGA"));
					h.put("HARGA_PASARAN",
							rs.getString("HARGA_PASARAN") == null ? "" : rs
									.getDouble("HARGA_PASARAN"));
					h.put("AMAUN_PENJEJASAN_JPPH", rs
							.getString("AMAUN_PENJEJASAN_JPPH") == null ? "" : rs
							.getDouble("AMAUN_PENJEJASAN_JPPH"));
					h.put("AMAUN_PECAHPISAH_JPPH", rs
							.getString("AMAUN_PECAHPISAH_JPPH") == null ? "" : rs
							.getDouble("AMAUN_PECAHPISAH_JPPH"));
					h.put("NAIK_NILAI_JPPH",
							rs.getString("NAIK_NILAI_JPPH") == null ? "" : rs
									.getDouble("NAIK_NILAI_JPPH"));
					h.put("ID_TANAH", rs.getString("ID_TANAH") == null ? "" : rs
							.getString("ID_TANAH"));
					
					h.put("KEROSAKAN_TANAH", rs.getString("KEROSAKAN_TANAH") == null ? "" : rs
							.getString("KEROSAKAN_TANAH"));
					h.put("KOS_DITANGGUNG", rs.getString("KOS_DITANGGUNG") == null ? "" : rs
							.getString("KOS_DITANGGUNG"));
					
					maklumat_siasatan_history.addElement(h);

				}
				return maklumat_siasatan_history;
			} finally {
				if (db != null)
					db.close();
			}
		}

		Vector list_PB = null;
		public Vector list_PB(String id_hakmilik,String CariPB) throws Exception {
			list_PB = new Vector();
			list_PB.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = " SELECT BPB.ID_BAHAGIANPB,BPB.SYER_ATAS,BPB.SYER_BAWAH,HMPB.FLAG_BORANGC,HMPB.FLAG_BORANGE,HMPB.FLAG_BORANGG,HMPB.FLAG_BORANGK,HMPB.KETERANGAN_JENIS_PB,HMPB.CATATAN,HMPB.NO_HANDPHONE,HMPB.NO_TEL_RUMAH,HM.ID_HAKMILIK," +
					//	"S.ID_SIASATAN," +
						"PBALIK.ID_PERMOHONAN,"
						+
						// "H.FLAG_HADIR," +
						"HMPB.ID_PIHAKBERKEPENTINGAN,HMPB.ID_HAKMILIKPB,PB.NAMA_PB,"
						+ "PB.NO_PB,PB.NAMA_KP,HMPB.ID_JENISPB,JPB.KETERANGAN AS JENISPB, "
						+ " HMPB.ALAMAT1,HMPB.ALAMAT2,HMPB.ALAMAT3,HMPB.POSKOD,HMPB.ID_NEGERI,HMPB.ID_BANDAR,N.NAMA_NEGERI, "
						+ " B.KETERANGAN AS NAMA_BANDAR,PB.ID_JENISNOPB,JNOPB.KETERANGAN AS JENISNOPB,HMPB.NO_AKAUN,HMPB.ID_BANK,RB.NAMA_BANK "
						+ " FROM TBLPPTHAKMILIK HM,TBLPPTPERMOHONAN PBALIK," +
							//	"TBLPPTSIASATAN S," +
								"TBLPPTHAKMILIKPB HMPB, "
						+
						
						"TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISPB JPB,TBLRUJNEGERI N,TBLRUJBANDAR B, "
						+ " TBLRUJJENISNOPB JNOPB,TBLRUJBANK RB,TBLPPTBAHAGIANPB BPB  "
						+ " WHERE " 
						
					//	+" PH.ID_HAKMILIK = HM.ID_HAKMILIK "
						+" HMPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+) AND  HM.ID_PERMOHONAN = PBALIK.ID_PERMOHONAN "
						
					
					
					//	" AND  S.ID_HAKMILIK(+) = HM.ID_HAKMILIK "
						+ " AND HMPB.ID_HAKMILIK = HM.ID_HAKMILIK"
						+ " AND HMPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
						+ " AND HMPB.ID_JENISPB = JPB.ID_JENISPB(+) "
						+ " AND HMPB.ID_NEGERI = N.ID_NEGERI(+) "
						+ " AND HMPB.ID_BANDAR = B.ID_BANDAR(+) "
						+ " AND PB.ID_JENISNOPB = JNOPB.ID_JENISNOPB(+) "
						+ " AND HMPB.ID_BANK = RB.ID_BANK(+) "
						+ " AND NVL(HMPB.ID_JENISPB,0) NOT IN ('40','41','42','27') "
						+

						" AND HM.ID_HAKMILIK = '"
						+ id_hakmilik
						+ "' ";
						
						if(!CariPB.equals("") && !CariPB.equals(null))
						{
							
							if (!CariPB.trim().equals("")) {
								sql = sql + " AND (UPPER(PB.NO_PB) LIKE '%"
										+ CariPB.toUpperCase().trim() + "%'";					
								
								sql = sql + " OR UPPER(PB.NAMA_PB) LIKE '%"
								+ CariPB.toUpperCase().trim() + "%')";
								
								
							}
							
						}
						
						
						
					sql += " ORDER BY ID_BAHAGIANPB,NAMA_PB ASC" + "";
					
					
					myLogger.info("LIST PB BARU :"+sql.toUpperCase());

				ResultSet rs = stmt.executeQuery(sql);

				int bil = 0;
				String temp = "";
				while (rs.next()) {
					bil = bil + 1;

					Hashtable h = new Hashtable();
					h.put("BIL", bil);
					temp = bil + "k";
					h.put("BILTEMP", temp);
					
					h.put("ID_BAHAGIANPB", rs.getString("ID_BAHAGIANPB") == null ? ""
							: rs.getString("ID_BAHAGIANPB"));					
					h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? ""
							: rs.getString("SYER_ATAS"));
					h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
							: rs.getString("SYER_BAWAH"));
					
					h.put("FLAG_BORANGC", rs.getString("FLAG_BORANGC") == null ? ""
							: rs.getString("FLAG_BORANGC"));
					h.put("FLAG_BORANGE", rs.getString("FLAG_BORANGE") == null ? ""
							: rs.getString("FLAG_BORANGE"));
					h.put("FLAG_BORANGG", rs.getString("FLAG_BORANGG") == null ? ""
							: rs.getString("FLAG_BORANGG"));
					h.put("FLAG_BORANGK", rs.getString("FLAG_BORANGK") == null ? ""
							: rs.getString("FLAG_BORANGK"));
					
					h.put("CATATAN", rs.getString("CATATAN") == null ? ""
							: rs.getString("CATATAN"));
					h.put("KETERANGAN_JENIS_PB", rs.getString("KETERANGAN_JENIS_PB") == null ? ""
							: rs.getString("KETERANGAN_JENIS_PB"));
					h.put("NO_HANDPHONE", rs.getString("NO_HANDPHONE") == null ? ""
							: rs.getString("NO_HANDPHONE"));
					h.put("NO_TEL_RUMAH", rs.getString("NO_TEL_RUMAH") == null ? ""
							: rs.getString("NO_TEL_RUMAH"));
					h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
							: rs.getString("ID_HAKMILIK"));
			//		h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
				//			: rs.getString("ID_SIASATAN"));
					h.put("ID_PENARIKANBALIK",
							rs.getString("ID_PERMOHONAN") == null ? "" : rs
									.getString("ID_PERMOHONAN"));
					// h.put("FLAG_HADIR",rs.getString("FLAG_HADIR") == null ? "" :
					// rs.getString("FLAG_HADIR"));
					h.put("ID_PIHAKBERKEPENTINGAN", rs
							.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
							.getString("ID_PIHAKBERKEPENTINGAN"));
					h.put("ID_HAKMILIKPB",
							rs.getString("ID_HAKMILIKPB") == null ? "" : rs
									.getString("ID_HAKMILIKPB"));
					h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
							.getString("NAMA_PB").toUpperCase());
					h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
							.getString("NO_PB").toUpperCase());
					h.put("NAMA_KP", rs.getString("NAMA_KP") == null ? "" : rs
							.getString("NAMA_KP").toUpperCase());
					h.put("ID_JENISPB", rs.getString("ID_JENISPB") == null ? ""
							: rs.getString("ID_JENISPB"));
					// h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" :
					// rs.getString("KETERANGAN"));
					h.put("JENISPB", rs.getString("JENISPB") == null ? "" : rs
							.getString("JENISPB").toUpperCase());
					h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
							.getString("ALAMAT1").toUpperCase());
					h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
							.getString("ALAMAT2").toUpperCase());
					h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
							.getString("ALAMAT3").toUpperCase());
					h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
							.getString("POSKOD"));
					h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
							.getString("ID_NEGERI"));
					h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
							.getString("ID_BANDAR"));
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
							: rs.getString("NAMA_NEGERI").toUpperCase());
					h.put("NAMA_BANDAR", rs.getString("NAMA_BANDAR") == null ? ""
							: rs.getString("NAMA_BANDAR").toUpperCase());
					h.put("ID_JENISNOPB", rs.getString("ID_JENISNOPB") == null ? ""
							: rs.getString("ID_JENISNOPB"));
					h.put("JENISNOPB", rs.getString("JENISNOPB") == null ? "" : rs
							.getString("JENISNOPB").toUpperCase());
					h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs
							.getString("NO_AKAUN").toUpperCase());
					h.put("ID_BANK", rs.getString("ID_BANK") == null ? "" : rs
							.getString("ID_BANK"));
					h.put("NAMA_BANK", rs.getString("NAMA_BANK") == null ? "" : rs
							.getString("NAMA_BANK").toUpperCase());

					list_PB.addElement(h);
				}
				return list_PB;
			} finally {
				if (db != null)
					db.close();
			}
		}

		Vector senarai_pihak_penting_HAKMILIK = null;	
		public Vector senarai_pihak_penting_bahagian(String id_hakmilik)throws Exception{
			senarai_pihak_penting_HAKMILIK = new Vector();
			Db db = null;
			senarai_pihak_penting_HAKMILIK.clear();
			String sql = "";
			
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "SELECT BH.SYER_ATAS,BH.SYER_BAWAH,B1.ID_HAKMILIKPB,PB1.NAMA_PB,B1.ID_JENISPB,B1.ID_BAHAGIANPB,H1.ID_HAKMILIK " +
						"FROM TBLPPTPIHAKBERKEPENTINGAN PB1,TBLPPTHAKMILIKPB B1,TBLPPTHAKMILIK H1," +
						"TBLPPTBAHAGIANPB BH "+
				" WHERE B1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "+
				" AND B1.ID_HAKMILIK = H1.ID_HAKMILIK AND B1.ID_BAHAGIANPB = BH.ID_BAHAGIANPB "+
				" AND H1.ID_HAKMILIK = '"+id_hakmilik+"' "+
				" ORDER BY NAMA_PB ASC ";
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 0;
				while (rs.next()) {
					bil = bil + 1;
					h = new Hashtable();
					h.put("BIL",bil);
					h.put("SYER_ATAS",rs.getString("SYER_ATAS") == null ? "" : rs.getString("SYER_ATAS"));
					h.put("SYER_BAWAH",rs.getString("SYER_BAWAH") == null ? "" : rs.getString("SYER_BAWAH"));				
					h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB") == null ? "" : rs.getString("ID_HAKMILIKPB"));
					h.put("ID_BAHAGIANPB",rs.getString("ID_BAHAGIANPB") == null ? "" : rs.getString("ID_BAHAGIANPB"));
					h.put("ID_HAKMILIK",rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
					h.put("NAMA_PB",rs.getString("NAMA_PB") == null ? "" : rs.getString("NAMA_PB").toUpperCase());
					senarai_pihak_penting_HAKMILIK.addElement(h);				
				}
				return senarai_pihak_penting_HAKMILIK;
			}finally{
				if (db != null)
					db.close();
			}
		}
		

		public void delete_siasatanaward(String id_award) throws Exception {
			Db db = null;

			String sql1 = "";
			String sql2 = "";

			try {
				db = new Db();
				Statement stmt1 = db.getStatement();
				sql1 = " DELETE FROM TBLPPTSUBAWARD WHERE ID_AWARD = "
						+ id_award;
				myLogger.info("SQL DELETE TBLPPTSUBAWARD BY PB:"
						+ sql1);
				stmt1.executeUpdate(sql1);
				
				
				
				Statement stmt2 = db.getStatement();
				sql2 = " DELETE FROM TBLPPTAWARD WHERE ID_AWARD = "
						+ id_award;
				
				stmt2.executeUpdate(sql2);

			} finally {
				if (db != null)
					db.close();
			}

		}
		
}
