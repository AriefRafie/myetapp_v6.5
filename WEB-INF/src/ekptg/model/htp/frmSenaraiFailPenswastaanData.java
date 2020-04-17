package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class frmSenaraiFailPenswastaanData {

	static Logger mylog = Logger
			.getLogger(ekptg.model.htp.frmSenaraiFailPenswastaanData.class);

	public static void addSimpanPemaju(Hashtable data) throws Exception {
		// Masukan data dalam table - Bukti Penyampaian
		Db db = null;
		String sql = "";
		String id = "";

		try {
			long id_pemaju = DB.getNextID("TBLHTPPEMAJU_SEQ");
			String idPermohonan = (String) data.get("id_permohonan");
			String jenisPB = (String) data.get("jenisPB");
			String nama = (String) data.get("nama");
			String notel = (String) data.get("notel");
			String nofax = (String) data.get("nofax");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String selectNegeri = (String) data.get("selectNegeri");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_pemaju", id_pemaju);
			r.add("id_permohonan", idPermohonan);
			r.add("ID_RUJJENISOPB", jenisPB);
			r.add("NAMA_PEMAJU", nama);
			r.add("NO_TEL", notel);
			r.add("NO_fax", nofax);
			r.add("ALAMAT_PEMAJU1", alamat1);
			r.add("ALAMAT_PEMAJU2", alamat2);
			r.add("ALAMAT_PEMAJU3", alamat3);
			r.add("POSKOD_PEMAJU", poskod);
			// r.add("id_negeri", selectNegeri);
			r.add("ID_DAERAH", "1");
			r.add("ID_NEGERI", "1");

			sql = r.getSQLInsert("TBLHTPPEMAJU");
			stmt.executeUpdate(sql);

			id = "" + id_pemaju;
			// System.out.println(id);

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void updateSimpanPemaju(Hashtable data) throws Exception {
		// Masukan
		Db db = null;
		String sql = "";
		String id = "";

		try {

			String idPermohonan = (String) data.get("id_permohonan");
			String jenisPB = (String) data.get("jenisPB");
			String nama = (String) data.get("nama");
			String notel = (String) data.get("notel");
			String nofax = (String) data.get("nofax");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String selectNegeri = (String) data.get("selectNegeri");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			// where
			r.update("id_permohonan", idPermohonan);
			
			// set field
			r.add("ID_RUJJENISOPB", jenisPB);
			r.add("NAMA_PEMAJU", nama);
			r.add("NO_TEL", notel);
			r.add("NO_fax", nofax);
			r.add("ALAMAT_PEMAJU1", alamat1);
			r.add("ALAMAT_PEMAJU2", alamat2);
			r.add("ALAMAT_PEMAJU3", alamat3);
			r.add("POSKOD_PEMAJU", poskod);
			// r.add("id_negeri", selectNegeri);
			r.add("ID_DAERAH", "1");
			r.add("ID_NEGERI", "1");

			sql = r.getSQLUpdate("TBLHTPPEMAJU");
			
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public static Hashtable getPermohonanInfo(String idpermohonan)
			throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " select trm.nama_mukim, trd.id_daerah, trd.kod_daerah, trd.NAMA_DAERAH, n.nama_negeri,k.nama_kementerian,a.nama_agensi, "
					+ " s.nama_suburusan,f.no_fail,to_char(f.tarikh_daftar_fail,'dd/mm/yyyy') tarikh_daftar_fail ,h.no_rujukan_kjp, "
					+ " h.no_rujukan_lain,to_char(p.tarikh_surat,'dd/mm/yyyy') tarikh_surat,to_char(p.tarikh_terima,'dd/mm/yyyy') tarikh_terima,p.tujuan,"
					+ " p.id_permohonan,f.id_fail,k.id_kementerian,a.id_agensi, "
					+ " n.id_negeri FROM tblrujnegeri n, Tblpfdfail f, tblrujkementerian k,tblpermohonan p, tblrujdaerah trd, tblrujmukim trm,"
					+ " tblhtppermohonan h, tblrujagensi a,tblrujsuburusan s where  trm.id_daerah=trd.id_daerah and trd.id_negeri=n.ID_NEGERI and "
					+ " f.ID_NEGERI=n.ID_NEGERI and f.id_kementerian=k.id_kementerian and "
					+ " p.ID_FAIL=f.ID_FAIL and p.id_permohonan=h.ID_PERMOHONAN and "
					+ " h.ID_AGENSI=a.id_agensi and f.ID_SUBURUSAN=s.ID_SUBURUSAN and "
					+ " p.id_permohonan= '" + idpermohonan + "'";
			System.out.println("peje : " + sql);
			mylog.info("getPermohonanInfo(" + idpermohonan + ") : sql::" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = new Hashtable();

			while (rs.next()) {
				if (rs.getString("nama_negeri") == null) {
					h.put("negeri", "");
				} else {
					h.put("negeri", rs.getString("nama_negeri"));
				}
				if (rs.getString("nama_kementerian") == null) {
					h.put("kementerian", "");
				} else {
					h.put("kementerian", rs.getString("nama_kementerian"));
				}
				if (rs.getString("nama_agensi") == null) {
					h.put("agensi", "");
				} else {
					h.put("agensi", rs.getString("nama_agensi"));
				}
				if (rs.getString("nama_suburusan") == null) {
					h.put("suburusan", "");
				} else {
					h.put("suburusan", rs.getString("nama_suburusan"));
				}
				if (rs.getString("no_fail") == null) {
					h.put("fail", "");
				} else {
					h.put("fail", rs.getString("no_fail"));
				}
				if (rs.getString("tarikh_daftar_fail") == null) {
					h.put("tdaftar", new Date());
				} else {
					h.put("tdaftar", rs.getString("tarikh_daftar_fail"));
				}
				if (rs.getString("no_rujukan_kjp") == null) {
					h.put("rujukankjp", "");
				} else {
					h.put("rujukankjp", rs.getString("no_rujukan_kjp"));
				}
				if (rs.getString("no_rujukan_lain") == null) {
					h.put("rujukanlain", "");
				} else {
					h.put("rujukanlain", rs.getString("no_rujukan_lain"));
				}
				if (rs.getString("tarikh_surat") == null) {
					h.put("tsurat", new Date());
				} else {
					h.put("tsurat", rs.getString("tarikh_surat"));
				}
				if (rs.getString("tarikh_terima") == null) {
					h.put("rtterima", new Date());
				} else {
					h.put("rtterima", rs.getString("tarikh_terima"));
				}
				if (rs.getString("tujuan") == null) {
					h.put("tujuan", "");
				} else {
					h.put("tujuan", rs.getString("tujuan"));
				}
				if (rs.getString("id_permohonan") == null) {
					h.put("idpermohonan", "");
				} else {
					h.put("idpermohonan", rs.getString("id_permohonan"));
				}
				if (rs.getString("id_fail") == null) {
					h.put("idfail", "");
				} else {
					h.put("idfail", rs.getString("id_fail"));
				}
				h.put("idagensi", rs.getString("id_agensi"));
				h.put("idkementerian", rs.getString("id_kementerian"));
				h.put("idnegeri", rs.getString("id_negeri"));
				h.put("iddaerah", rs.getString("id_daerah"));
				// h.put("namamukim", rs.getString("nama_mukim"));
				// list.addElement(h);
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getMaklumatSyarikat(String id_permohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			Vector maklumatSyarikat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT id_pemaju, id_permohonan,"
					+ " poskod_pemaju, alamat_pemaju3,"
					+ " alamat_pemaju2,alamat_pemaju1,"
					+ " nama_pemaju, id_rujjenisopb,"
					+ " id_negeri, no_tel, no_fax"
					+ " FROM tblhtppemaju where id_permohonan = '"
					+ id_permohonan + "'";
			;

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h = new Hashtable();
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPemaju", rs.getString("id_pemaju") == null ? "" : rs
						.getString("id_pemaju"));
				h.put("id_permohonan",
						rs.getString("id_permohonan") == null ? "" : rs
								.getString("id_permohonan").toUpperCase());
				h.put("poskod", rs.getString("poskod_pemaju") == null ? "" : rs
						.getString("poskod_pemaju"));
				h.put("alamat3", rs.getString("alamat_pemaju3") == null ? ""
						: rs.getString("alamat_pemaju3"));
				h.put("alamat2", rs.getString("alamat_pemaju2") == null ? ""
						: rs.getString("alamat_pemaju2"));
				h.put("alamat1", rs.getString("alamat_pemaju1") == null ? ""
						: rs.getString("alamat_pemaju1").toUpperCase());
				h.put("nama", rs.getString("nama_pemaju") == null ? "" : rs
						.getString("nama_pemaju").toUpperCase());
				h.put("jenisPB", rs.getString("id_rujjenisopb") == null ? ""
						: rs.getString("id_rujjenisopb").toUpperCase());
				h.put("selectNegeri", rs.getString("id_negeri") == null ? ""
						: rs.getString("id_negeri").toUpperCase());
				h.put("notel", rs.getString("no_tel") == null ? "" : rs
						.getString("no_tel").toUpperCase());
				h.put("nofax", rs.getString("no_fax") == null ? "" : rs
						.getString("no_fax").toUpperCase());
				maklumatSyarikat.addElement(h);
				bil++;
			}
			return maklumatSyarikat;

		} finally {
			if (db != null)
				db.close();
		}
	}

}
