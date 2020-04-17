/**
 * 
 */
package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmPrmhnnSek8Data {
	private Vector list = new Vector();
	private Vector listSimati = new Vector();
	private Vector listPemohon = new Vector();
	private Vector listPeguam = new Vector();
	private Vector listKeputusan = new Vector();
	private Vector listWaris = new Vector();
	private Vector listHta = new Vector();
	private Vector listHtath = new Vector();
	private Vector listHa = new Vector();
	private Vector listRujJenisHa = new Vector();
	private Vector listPenting = new Vector();
	private Vector listPentingbyIDOB = new Vector();
	private Vector listSaksi = new Vector();
	private Vector listPenghutang = new Vector();
	private Vector listPemiutang = new Vector();
	private Vector listWarisParent = new Vector();
	private Vector listWarisLapisan = new Vector();
	private Vector listWarisUpdate = new Vector();
	private Vector listDaerah = new Vector();
	private Vector listLuas = new Vector();
	private Vector listHTA = new Vector();
	private Vector listHTAX = new Vector();
	private Vector listHTAbyIdHtaam = new Vector();
	private Vector listHTAXbyIdHtaam = new Vector();
	private Vector listWarisOB = new Vector();
	private Vector listWarisLapisanIdMati = new Vector();
	private Vector listWarisLapisanIdMatiDelete = new Vector();
	private Vector listCheckPeguam = new Vector();
	private Vector listPenghutangbyIDOB = new Vector();

	private static SimpleDateFormat formatter = new SimpleDateFormat(
			"dd/MM/yyyy");

	public Vector getData() {

		return list;

	}

	public Vector getCheckPeguam() {
		return listCheckPeguam;
	}

	public Vector getDataHTAX() {
		return listHTAX;
	}

	public Vector getDataHTAXbyIdHtaam() {
		return listHTAXbyIdHtaam;
	}

	public Vector getDataHTAbyIdHtaam() {
		return listHTAbyIdHtaam;
	}

	public Vector getDataHTA() {
		return listHTA;
	}

	public Vector getDataWarisOB() {
		return listWarisOB;
	}

	public Vector getDataSimati() {
		return listSimati;
	}

	public Vector getDataWarisLapisanIdMati() {
		return listWarisLapisanIdMati;
	}

	public Vector getDataWarisLapisanIdMatiDelete() {
		return listWarisLapisanIdMatiDelete;
	}

	public Vector getDataPemohon() {
		return listPemohon;
	}

	public Vector getDataPeguam() {
		return listPeguam;
	}

	public Vector getDataPenting() {
		return listPenting;
	}

	public Vector getDataPentingbyIDOB() {
		return listPentingbyIDOB;
	}

	public Vector getDataSaksi() {
		return listSaksi;
	}

	public Vector getDataPenghutang() {
		return listPenghutang;
	}

	public Vector getDataPenghutangbyIDOB() {
		return listPenghutangbyIDOB;
	}

	public Vector getDataPemiutang() {
		return listPemiutang;
	}

	public Vector getDataWaris() {
		return listWaris;
	}

	public Vector getDataWarisParent() {
		return listWarisParent;
	}

	public Vector getDataWarisLapisan() {
		return listWarisLapisan;
	}

	public Vector getDataWarisUpdate() {
		return listWarisUpdate;
	}

	public Vector getDataHta() {
		return listHta;
	}

	public Vector getDataHtath() {
		return listHtath;
	}

	public Vector getDataHa() {
		return listHa;
	}

	public void setCheckPeguam(String id) throws Exception {
		Db db = null;
		listCheckPeguam.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("s.id_Permohonan");
			r.add("s.id_Semakansenarai");
			r.add("s.id_Semakanhantar");

			r.add("s.id_Permohonan", id);
			r.add("s.id_Semakansenarai", 10);

			sql = r.getSQLSelect("Tblsemakanhantar s");
			System.out.println("CHECKLIST PEGUAM:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idSemakanhantar",
						rs.getString("id_Semakanhantar") == null ? "" : rs
								.getString("id_Semakanhantar"));
				listCheckPeguam.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataSimati(String id) throws Exception {
		Db db = null;
		listSimati.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("p.id_Permohonan");
			r.add("s.id_Simati");
			r.add("s.no_Kp_Baru");
			r.add("s.no_Kp_Lama");
			r.add("s.jenis_Kp");
			r.add("s.no_Kp_Lain");
			r.add("s.nama_Simati");
			r.add("s.tarikh_Mati");
			r.add("s.nama_Lain");
			r.add("s.jantina");
			r.add("s.jenis_Warga");
			r.add("s.jenis_Agama");
			r.add("s.umur");
			r.add("s.id_Buktimati");
			r.add("s.tempat_Mati");
			r.add("s.no_Sijil_Mati");
			r.add("s.waktu_Kematian");
			r.add("s.jenis_Waktu_Mati");
			r.add("s.id_Buktimati");
			r.add("s.sebab_Mati");
			r.add("s.alamat_1");
			r.add("s.alamat_2");
			r.add("s.alamat_3");
			r.add("s.poskod");
			r.add("s.bandar");
			r.add("s.id_Negeri");
			r.add("s.catatan");
			r.add("po.id_Pemohon");
			r.add("M1.id_permohonansimati");

			r.add("p.id_Permohonan", r.unquote("po.id_Permohonan"));
			r.add("po.id_Permohonan", r.unquote("m1.id_Permohonan"));
			r.add("m1.id_simati", r.unquote("s.id_simati"));

			r.add("p.seksyen", 8);
			r.add("p.id_Permohonan", id);

			sql = r
					.getSQLSelect("Tblppksimati s, Tblppkpermohonan p, Tblppkpemohon po, Tblppkpermohonansimati m1");

			System.out.println("setDataSimati-->>" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("noKpBaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("noKpLama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jenisKp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("noKpLain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("namaSimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("namaLain", rs.getString("nama_Lain") == null ? "" : rs
						.getString("nama_Lain"));
				h.put("idPemohon", rs.getString("id_Pemohon") == null ? "" : rs
						.getString("id_Pemohon"));
				h.put("masamati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				Calendar cal = new GregorianCalendar();

				String dateStart = rs.getString("tarikh_Mati");
				DateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
				// DateFormat ds = new SimpleDateFormat("E-MMM-dd hh:mm:ss");
				Date start_parsed = ds.parse(dateStart);

				Calendar newStart = Calendar.getInstance();
				newStart.setTime(start_parsed);

				int s_day = newStart.get(Calendar.DATE);
				int s_month = newStart.get(Calendar.MONTH);
				int s_year = newStart.get(Calendar.YEAR);

				String st = "";

				if (s_month < 10) {
					if (s_day < 10) {

						st = "0" + (s_day) + "/" + 0 + (s_month + 1) + "/"
								+ s_year;
						// context.put("stl",""+0+(s_day)+"-"+0+(s_month+1)+"-"+s_year);
					} else {

						st = s_day + "/" + 0 + (s_month + 1) + "/" + s_year;
						// context.put("stl",s_day+"-"+0+(s_month+1)+"-"+s_year);
					}
				} else {
					if (s_day < 10) {

						st = "0" + (s_day) + "/" + 0 + (s_month + 1) + "/"
								+ s_year;

						// context.put("stl",""+0+(s_day)+"-"+0+(s_month+1)+"-"+s_year);
					} else {

						st = s_day + "/" + (s_month + 1) + "/" + s_year;

						// context.put("stl",s_day+"-"+(s_month+1)+"-"+s_year);
					}
				}
				h.put("tarikhMati", st);
				h.put("tarikhMatidb", rs.getString("tarikh_Mati") == null ? ""
						: rs.getString("tarikh_Mati"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("jenisWarga", rs.getString("jenis_Warga") == null ? ""
						: rs.getString("jenis_Warga"));
				h.put("jenisAgama", rs.getString("jenis_Agama") == null ? ""
						: rs.getString("jenis_Agama"));
				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));
				h.put("idBuktimati", rs.getString("id_Buktimati") == null ? ""
						: rs.getString("id_Buktimati"));
				h.put("tempatMati", rs.getString("tempat_Mati") == null ? ""
						: rs.getString("tempat_Mati"));
				h.put("noSijilMati", rs.getString("no_Sijil_Mati") == null ? ""
						: rs.getString("no_Sijil_Mati"));
				h.put("masaMati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));
				h.put("jeniswaktumati",
						rs.getString("jenis_Waktu_Mati") == null ? "" : rs
								.getString("jenis_Waktu_Mati"));
				h.put("sebabMati", rs.getString("sebab_Mati") == null ? "" : rs
						.getString("sebab_Mati"));
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
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				// h.put("buktikematian",
				// rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				// h.put("idbuktikematian",
				// rs.getString("id_Buktimati")==null?"":rs.getString("id_Buktimati"));
				// h.put("namanegeri",
				// rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
				// h.put("kodbuktikematian",
				// rs.getString("kod")==null?"":rs.getString("kod"));
				// h.put("kod_Negeri",
				// rs.getString("kod_Negeri")==null?"":rs.getString("kod_Negeri"));
				System.out.println(h);
				listSimati.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataHta(String id) throws Exception {
		Db db = null;
		listHta.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("hta.id_Hta");
			r.add("hta.id_Negeri");
			r.add("hta.id_Daerah");
			r.add("hta.id_Mukim");
			r.add("hta.no_Hakmilik");
			r.add("hta.no_Pt");
			r.add("hta.ba_Simati");
			r.add("hta.bb_Simati");
			r.add("n.nama_Negeri");
			r.add("d.nama_Daerah");
			r.add("m.nama_Mukim");
			r.add("sm.id_Simati");
			r.add("sm.id_Permohonan");
			r.add("p.id_Permohonan");

			r.add("hta.id_Negeri", r.unquote("n.id_Negeri(+)"));
			r.add("hta.id_Daerah", r.unquote("d.id_Daerah(+)"));
			r.add("hta.id_Mukim", r.unquote("m.id_Mukim(+)"));
			r.add("hta.id_Simati", r.unquote("sm.id_Simati(+)"));
			r.add("sm.id_Permohonan", r.unquote("p.id_Permohonan(+)"));

			r.add("p.id_Permohonan", id);

			sql = r
					.getSQLSelect("Tblppkpermohonan p, Tblppkhta hta, Tblrujnegeri n, Tblrujdaerah d, Tblrujmukim m, Tblppksimati sm");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Hta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("id_Negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("id_Simati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("id_Daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("id_Mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("no_Hakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("no_Pt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("ba_Simati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bb_Simati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("nama_Negeri", rs.getString("nama_Negeri") == null ? ""
						: rs.getString("nama_Negeri"));
				h.put("nama_Daerah", rs.getString("nama_Daerah") == null ? ""
						: rs.getString("nama_Daerah"));
				h.put("nama_Mukim", rs.getString("nama_Mukim") == null ? ""
						: rs.getString("nama_Mukim"));
				listHta.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setDataHtath(String id) throws Exception {
		Db db = null;
		listHtath.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("hta.id_Hta");
			r.add("hta.id_Negeri");
			r.add("hta.id_Daerah");
			r.add("hta.id_Mukim");
			r.add("hta.no_Perjanjian");
			r.add("hta.no_Pt");
			r.add("hta.ba_Simati");
			r.add("hta.bb_Simati");
			r.add("n.nama_Negeri");
			r.add("d.nama_Daerah");
			r.add("m.nama_Mukim");
			r.add("sm.id_Simati");
			r.add("sm.id_Permohonan");
			r.add("p.id_Permohonan");

			r.add("hta.id_Negeri", r.unquote("n.id_Negeri(+)"));
			r.add("hta.id_Daerah", r.unquote("d.id_Daerah(+)"));
			r.add("hta.id_Mukim", r.unquote("m.id_Mukim(+)"));
			r.add("hta.id_Simati", r.unquote("sm.id_Simati(+)"));
			r.add("sm.id_Permohonan", r.unquote("p.id_Permohonan(+)"));

			r.add("p.id_Permohonan", id);

			sql = r
					.getSQLSelect("Tblppkpermohonan p, Tblppkhta hta, Tblrujnegeri n, Tblrujdaerah d, Tblrujmukim m, Tblppksimati sm");
			ResultSet rs = stmt.executeQuery(sql);
			// Vector list = new Vector(;
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Hta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("id_Negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("id_Simati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("id_Daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("id_Mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("no_Perjanjian",
						rs.getString("no_Perjanjian") == null ? "" : rs
								.getString("no_Perjanjian"));
				h.put("no_Pt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("ba_Simati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bb_Simati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("nama_Negeri", rs.getString("nama_Negeri") == null ? ""
						: rs.getString("nama_Negeri"));
				h.put("nama_Daerah", rs.getString("nama_Daerah") == null ? ""
						: rs.getString("nama_Daerah"));
				h.put("nama_Mukim", rs.getString("nama_Mukim") == null ? ""
						: rs.getString("nama_Mukim"));

				listHtath.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setDataHa(String id) throws Exception {
		Db db = null;
		listHa.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ha.id_Ha");
			r.add("ha.id_Simati");
			r.add("ha.id_Jenisha");
			r.add("ha.no_Daftar");
			r.add("sm.id_Simati");
			r.add("sm.id_Permohonan");
			r.add("p.id_Permohonan");

			r.add("ha.id_Simati", r.unquote("sm.id_Simati(+)"));
			r.add("sm.id_Permohonan", r.unquote("p.id_Permohonan(+)"));

			r.add("p.id_Permohonan", id);

			sql = r
					.getSQLSelect("Tblppkpermohonan p, Tblppkha ha, Tblppksimati sm");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Ha", rs.getString("id_Ha") == null ? "" : rs
						.getString("id_Ha"));
				h.put("id_Jenisha", rs.getString("id_Jenisha") == null ? ""
						: rs.getString("id_Jenisha"));
				h.put("id_Simati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("no_Daftar", rs.getString("no_Daftar") == null ? "" : rs
						.getString("no_Daftar"));
				listHa.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void updatesimati(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			String id_Simati = (String) data.get("id_Simati");
			String nama_Simati = (String) data.get("nama_Simati");
			String nama_Lain = (String) data.get("nama_Lain");
			String no_Kp_Baru = (String) data.get("no_Kp_Baru");
			String no_Kp_Lama = (String) data.get("no_Kp_Lama");

			String jenis_Kp = (String) data.get("jenis_Kp");
			String n0_Kp_Lain = (String) data.get("n0_Kp_Lain");

			int umur = (Integer) data.get("umur");

			String jantina = (String) data.get("jantina");
			String no_Sijil_Mati = (String) data.get("no_Sijil_Mati");
			String tempat_Mati = (String) data.get("tempat_Mati");
			String alamat_1 = (String) data.get("alamat_1");
			String alamat_2 = (String) data.get("alamat_2");
			String alamat_3 = (String) data.get("alamat_3");
			String bandar = (String) data.get("bandar");
			String poskod = (String) data.get("poskod");

			/*
			 * String tarikh_Mati = (String)data.get("tarikh_Mati");
			 * SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 * 
			 * Date newtarikh_Mati = sdf.parse(tarikh_Mati);
			 */
			String tarikh_Mati_ = (String) data.get("tarikh_Mati");
			String tarikh_Mati = "to_date('" + tarikh_Mati_ + "','dd/MM/yyyy')";
			String waktu_Kematian = (String) data.get("waktu_Kematian");
			String jenis_Waktu_Mati = (String) data.get("jenis_Waktu_Mati");
			String sebab_Mati = (String) data.get("sebab_Mati");
			String catatan = (String) data.get("catatan");
			int id_Negeri = (Integer) data.get("id_Negeri");
			String id_Buktimati = (String) data.get("id_Buktimati");
			String jenis_Agama = (String) data.get("jenis_Agama");
			String jenis_Warga = (String) data.get("jenis_Warga");
			String tarikh_Kkini = (String) data.get("tarikh_Kkini");
			String id_Permohonan = (String) data.get("id_Permohonan");
			String id_Masuk = (String) data.get("id_Masuk");
			String tarikh_Masuk = (String) data.get("tarikh_Masuk");
			String id_Kemaskini = (String) data.get("id_Kemaskini");
			String tarikh_Kemaskini = (String) data.get("tarikh_Kemaskini");
			String id_Db = (String) data.get("id_Db");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_simati", id_Simati);

			r.add("nama_Simati", nama_Simati);
			r.add("nama_Lain", nama_Lain);
			r.add("no_Kp_Baru", no_Kp_Baru);
			r.add("no_Kp_Lama", no_Kp_Lama);
			r.add("jenis_Kp", jenis_Kp);

			r.add("n0_Kp_Lain", n0_Kp_Lain);

			r.add("jantina", jantina);
			r.add("no_Sijil_Mati", no_Sijil_Mati);
			r.add("tempat_Mati", tempat_Mati);
			r.add("alamat_1", alamat_1);
			r.add("alamat_2", alamat_2);
			r.add("alamat_3", alamat_3);
			r.add("bandar", bandar);
			r.add("poskod", poskod);

			if (umur != 0) {
				r.add("umur", umur);
			}
			if (umur == 0) {
				r.add("umur", "");
			}
			r.add("tarikh_Mati", r.unquote(tarikh_Mati));
			r.add("waktu_Kematian", jenis_Waktu_Mati);

			r.add("sebab_Mati", sebab_Mati);
			r.add("catatan", catatan);
			if (umur != 0) {
				r.add("id_Negeri", id_Negeri);
			}
			if (umur == 0) {
				r.add("id_Negeri", "");
			}

			r.add("id_Buktimati", id_Buktimati);
			r.add("jenis_Agama", jenis_Agama);
			r.add("jenis_Warga", jenis_Warga);
			r.add("tarikh_Kkini", tarikh_Kkini);
			r.add("id_Permohonan", id_Permohonan);
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", tarikh_Masuk);
			r.add("id_Kemaskini", id_Kemaskini);
			r.add("tarikh_Kemaskini", tarikh_Kemaskini);

			r.add("id_Db", id_Db);

			sql = r.getSQLUpdate("tblppksimati");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void updatepemohon(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			String id_Pemohon = (String) data.get("idPemohon");
			String id_Permohonan = (String) data.get("idPermohonan");

			String nama_Pemohon = (String) data.get("namaPemohon");

			String no_Kp_Baru = (String) data.get("noKpBaru");
			System.out.println("UMURRR" + (Integer) data.get("umur"));

			String jenis_Kp = (String) data.get("jenisKp");
			String no_Kp_Lain = (String) data.get("noKpLain");

			int umur = (Integer) data.get("umur");

			String jantina = (String) data.get("jantina");

			String taraf = (String) data.get("taraf");
			String saudara = (String) data.get("saudara");
			String jenisWarga = (String) data.get("jenisWarga");
			String jenisAgama = (String) data.get("jenisAgama");

			String alamat_1 = (String) data.get("alamat1");
			String alamat_2 = (String) data.get("alamat2");
			String alamat_3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String poskod = (String) data.get("poskod");

			int id_Negeri = (Integer) data.get("idnegeri");

			String catatan = (String) data.get("catatan");

			String nofaks = (String) data.get("nofaks");
			String notelefon = (String) data.get("notelefon");
			String notelefonbimbit = (String) data.get("notelefonbimbit");
			String emel = (String) data.get("emel");

			String status_Peguam = (String) data.get("status_Peguam");
			String status_Pemohon = (String) data.get("status_Pemohon");

			String no_Kp_Lama = (String) data.get("no_Kp_Lama");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_Pemohon", id_Pemohon);

			r.add("nama_Pemohon", nama_Pemohon);

			r.add("no_Kp_Baru", no_Kp_Baru);

			r.add("jenis_Kp", jenis_Kp);

			r.add("no_Kp_Lain", no_Kp_Lain);

			if (umur != 0) {
				r.add("umur", umur);
			}

			if (umur == 0) {
				r.add("umur", "");
			}

			r.add("jantina", jantina);
			r.add("jenis_Warga", jenisWarga);
			r.add("jenis_Agama", jenisAgama);
			r.add("alamat_1", alamat_1);
			r.add("alamat_2", alamat_2);
			r.add("alamat_3", alamat_3);
			r.add("bandar", bandar);
			r.add("poskod", poskod);

			r.add("no_Hp", notelefonbimbit);
			r.add("no_Tel", notelefon);
			r.add("emel", emel);
			r.add("no_Fax", nofaks);
			r.add("catatan", catatan);

			// r.add("id_Negeri",id_Negeri);
			// r.add("id_Tarafkptg",taraf);
			r.add("id_Tarafkptg", 1);
			r.add("id_Saudara", saudara);

			if (id_Negeri != 0) {
				r.add("id_Negeri", id_Negeri);
			}

			r.add("id_Permohonan", id_Permohonan);

			r.add("status_Peguam", status_Peguam);
			r.add("status_Pemohon", status_Pemohon);

			r.add("no_Kp_Lama", no_Kp_Lama);

			sql = r.getSQLUpdate("tblppkpemohon");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void updatepeguam(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			String id_Peguam = (String) data.get("idPeguam");
			String nama_Firma = ((String) data.get("firma")).toUpperCase();
			String alamat_1 = ((String) data.get("alamat1")).toUpperCase();
			String alamat_2 = ((String) data.get("alamat2")).toUpperCase();
			String alamat_3 = ((String) data.get("alamat3")).toUpperCase();
			String bandar = ((String) data.get("bandar")).toUpperCase();
			String poskod = (String) data.get("poskod");
			int id_Negeri = (Integer) data.get("idnegeri");
			int id_bandar = (Integer) data.get("idbandar");
			String no_Rujukan_Firma = (String) data.get("rujfirma");
			String no_Tel = (String) data.get("noTel");
			String no_Fax = (String) data.get("noFax");
			String emel = (String) data.get("emel");
			String nama_Peguam = (String) data.get("");
			String no_Syarikat = (String) data.get("");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_Peguam", id_Peguam);
			r.add("nama_Firma", nama_Firma);
			r.add("alamat1", alamat_1);
			r.add("alamat2", alamat_2);
			r.add("alamat3", alamat_3);
			r.add("bandar", bandar);
			r.add("poskod", poskod);
			r.add("id_Negeri", id_Negeri);
			r.add("id_bandar", id_bandar);
			r.add("no_Rujukan_Firma", no_Rujukan_Firma);
			r.add("no_Tel", no_Tel);
			r.add("no_Fax", no_Fax);
			r.add("emel", emel);
			sql = r.getSQLUpdate("tblppkpeguam");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void tambahpeguam(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			String id_Pemohon = (String) data.get("idPemohon");

			String nama_Firma = (String) data.get("firma");
			String alamat_1 = (String) data.get("alamat1");
			String alamat_2 = (String) data.get("alamat2");
			String alamat_3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String poskod = (String) data.get("poskod");
			int id_Negeri = (Integer) data.get("idnegeri");
			String no_Rujukan_Firma = (String) data.get("rujfirma");
			String no_Tel = (String) data.get("noTel");
			String no_Fax = (String) data.get("noFax");
			String emel = (String) data.get("emel");
			String nama_Peguam = (String) data.get("");
			String no_Syarikat = (String) data.get("");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_Pemohon", id_Pemohon);
			r.add("nama_Firma", nama_Firma);
			r.add("alamat1", alamat_1);
			r.add("alamat2", alamat_2);
			r.add("alamat3", alamat_3);
			r.add("bandar", bandar);
			r.add("poskod", poskod);
			r.add("id_Negeri", id_Negeri);
			r.add("no_Rujukan_Firma", no_Rujukan_Firma);
			r.add("no_Tel", no_Tel);
			r.add("no_Fax", no_Fax);
			r.add("emel", emel);

			sql = r.getSQLInsert("tblppkpeguam");
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void updatePenghutang(Hashtable data) throws Exception {

		Db db = null;
		String sql = "";
		try {
			String idsimati = (String) data.get("idSimati");
			String idob = (String) data.get("idob");
			String namaob = (String) data.get("namaOB");
			String jenishutang = (String) data.get("jenishutang");
			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = (String) data.get("nokppenting");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			int id_bandar = (Integer) data.get("id_bandar");
			int negeri = (Integer) data.get("negeri");
			double nilaihutang = (Double) data.get("nilaihutang");
			String butiranhutang = (String) data.get("butiranhutang");
			String noakaun = (String) data.get("noakaun");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_Penghutang", idob);
			r.add("no_Akaun", noakaun);
			r.add("butiran_Hutang", butiranhutang);
			r.add("jumlah_Hutang", nilaihutang);
			r.add("id_Simati", idsimati);
			r.add("nama_Penghutang", namaob);
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			if (jenishutang == "2") {
				r.add("no_Kp_Baru", nokpbaru);
			} else if (jenishutang == "1") {
				r.add("no_Kp_Baru", "");
			}
			r.add("no_Kp_Lama", nokppenting);
			r.add("jenis_Kp", jenisKp);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("id_bandar", id_bandar);
			r.add("poskod", poskod);
			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			sql = r.getSQLUpdate("tblppkpenghutang");
			System.out.println("sql masuk--->>" + sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}

	}

	public static void updatePenting(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			String idsimati = (String) data.get("idSimati");
			String idob = (String) data.get("idobx");
			String userid = (String) data.get("iduser");
			String namaob = ((String) data.get("namaOB")).toUpperCase();
			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = ((String) data.get("nokppenting"))
					.toUpperCase();
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = ((String) data.get("nokplain")).toUpperCase();
			String statusOB = (String) data.get("statusOB");
			int taraf = (Integer) data.get("taraf");
			String jantina = (String) data.get("jantina");
			String agama = (String) data.get("agama");
			String warga = (String) data.get("warga");
			int umur = (Integer) data.get("umur");
			String noberanak = (String) data.get("noberanak");
			String alamat1 = ((String) data.get("alamat1")).toUpperCase();
			String alamat2 = ((String) data.get("alamat2")).toUpperCase();
			String alamat3 = ((String) data.get("alamat3")).toUpperCase();
			String poskod = (String) data.get("poskod");
			String bandar = (String) data.get("bandar");
			int negeri = (Integer) data.get("negeri");
			double nilaihutang = (Double) data.get("nilaihutang");
			String butiranhutang = (String) data.get("butiranhutang");
			String noakaun = ((String) data.get("noakaun")).toUpperCase();
			String catatan = ((String) data.get("catatan")).toUpperCase();
			String dob = (String) data.get("dob");
			String notel = (String) data.get("notel");
			String dobpenting = "to_date('" + dob + "','dd/MM/yyyy')";
			String notepon = (String) data.get("notepon");
			String nofaks = (String) data.get("nofaks");
			String idpermohonansimati = (String) data.get("idpermohonansimati");
			int id_bandar = (Integer) data.get("id_bandar");
			int idbandar = (Integer) data.get("idbandar");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_Ob", idob);
			r.add("no_Akaun", noakaun);
			r.add("butiran_Hutang", butiranhutang);
			r.add("nilai_Hutang", nilaihutang);
			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob);
			r.add("tarikh_Lahir", r.unquote(dobpenting));
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokppenting);
			r.add("jenis_Kp", jenisKp);
			r.add("status_Ob", statusOB);
			if (taraf != 0) {
				r.add("id_Tarafkptg", taraf);
			}
			r.add("jantina", jantina);
			r.add("jenis_Agama", agama);
			r.add("jenis_Warga", warga);
			if (umur != 0) {
				r.add("umur", umur);
			}
			r.add("no_Surat_Beranak", noberanak);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("bandar", bandar);
			r.add("poskod", poskod);
			r.add("no_hp", nofaks);
			if (negeri != 0) {
				r.add("id_Negeri", negeri);
				r.add("id_Negerisurat", negeri);
			}
			if (id_bandar != 0) {
				r.add("id_Bandar", id_bandar);
				r.add("id_bandarsurat", id_bandar);
			} else if (id_bandar == 0 && idbandar != 0) {
				r.add("id_Bandar", idbandar);
				r.add("id_bandarsurat", idbandar);
			}
			r.add("alamat1_surat", alamat1);
			r.add("alamat2_surat", alamat2);
			r.add("alamat3_surat", alamat3);
			r.add("poskod_surat", poskod);
			r.add("catatan", catatan);
			r.add("no_Tel", notel);
			r.add("id_kemaskini", userid);
			r.add("id_permohonansimati", idpermohonansimati);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppkob");
			System.out.println("update ob--->>" + sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void addPenting(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			// String ids = (String)data.get("id");
			// int id=Integer.parseInt(ids);
			String idsimati = (String) data.get("idSimati");
			String jenisPb = (String) data.get("jenisPB");
			String idpemohon = (String) data.get("idPemohon");
			String idpermohonansimati = (String) data.get("idpermohonansimati");
			String namaob = ((String) data.get("namaOB")).toUpperCase();
			String userid = (String) data.get("iduser");
			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = ((String) data.get("nokppenting"))
					.toUpperCase();
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");
			String statusOB = (String) data.get("statusOB");
			int taraf = (Integer) data.get("taraf");
			String jantina = (String) data.get("jantina");
			String agama = (String) data.get("agama");
			String warga = (String) data.get("warga");
			int umur = (Integer) data.get("umur");
			String noberanak = (String) data.get("noberanak");
			String alamat1 = ((String) data.get("alamat1")).toUpperCase();
			String alamat2 = ((String) data.get("alamat2")).toUpperCase();
			String alamat3 = ((String) data.get("alamat3")).toUpperCase();
			String poskod = (String) data.get("poskod");
			String bandar = (String) data.get("bandar");
			int negeri = (Integer) data.get("negeri");
			int id_bandar = (Integer) data.get("id_bandar");
			String catatan = ((String) data.get("catatan")).toUpperCase();
			String notepon = (String) data.get("notepon");
			String nofaks = (String) data.get("nofaks");
			String dob = (String) data.get("dob");
			String dobpenting = "to_date('" + dob + "','dd/MM/yyyy')";

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob);
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokppenting);
			r.add("jenis_Kp", jenisKp);
			r.add("status_Ob", statusOB);
			if (taraf != 0) {
				r.add("id_Tarafkptg", taraf);
			}
			r.add("jantina", jantina);
			r.add("jenis_Agama", agama);
			r.add("jenis_Warga", warga);
			r.add("no_Surat_Beranak", noberanak);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("poskod", poskod);
			r.add("alamat1_surat", alamat1);
			r.add("alamat2_surat", alamat2);
			r.add("alamat3_surat", alamat3);
			r.add("poskod_surat", poskod);
			r.add("no_tel", notepon);
			r.add("no_hp", nofaks);
			r.add("id_Negeri", negeri);
			r.add("id_Negerisurat", negeri);
			r.add("id_bandar", id_bandar);
			r.add("id_bandarsurat", id_bandar);
			r.add("catatan", catatan);
			r.add("id_jenispb", jenisPb);
			r.add("id_masuk", userid);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("id_pemohon", idpemohon);
			r.add("id_permohonansimati", idpermohonansimati);
			sql = r.getSQLInsert("tblppkob");
			System.out.println("xxxxxxxxxx" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void addSaksi(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			// String ids = (String)data.get("id");
			// int id=Integer.parseInt(ids);
			String idsimati = (String) data.get("idSimati");
			String namaob = (String) data.get("namaOB");

			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = (String) data.get("nokppenting");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");

			String jantina = (String) data.get("jantina");

			String warga = (String) data.get("warga");

			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String bandar = (String) data.get("bandar");
			String notel = (String) data.get("notel");
			int negeri = (Integer) data.get("negeri");
			String catatan = (String) data.get("catatan");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob);

			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokppenting);
			r.add("jenis_Kp", jenisKp);

			r.add("jantina", jantina);

			r.add("jenis_Warga", warga);

			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("no_Tel", notel);

			r.add("bandar", bandar);
			r.add("poskod", poskod);

			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			r.add("catatan", catatan);
			int tarsaksi = 14;
			r.add("id_Tarafkptg", tarsaksi);

			sql = r.getSQLInsert("tblppkob");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void addPemiutang(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			// String ids = (String)data.get("id");
			// int id=Integer.parseInt(ids);
			String idsimati = (String) data.get("idSimati");
			String userid = (String) data.get("iduser");
			String namaob = (String) data.get("namaOB");
			double nilaihutang = (Double) data.get("nilaihutang");
			String butiranhutang = (String) data.get("butiranhutang");
			String noakaun = (String) data.get("noakaun");

			String jenispemiutang = (String) data.get("jenispemiutang");
			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = (String) data.get("nokppenting");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");
			String idpermohonansimati = (String) data.get("idpermohonansimati");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			int id_bandar = (Integer) data.get("id_bandar");
			String poskod = (String) data.get("poskod");
			int negeri = (Integer) data.get("negeri");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob);
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokppenting);
			r.add("jenis_Kp", jenisKp);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("poskod", poskod);
			if (id_bandar != 0) {
				r.add("id_bandar", id_bandar);
			}
			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			r.add("alamat1_surat", alamat1);
			r.add("alamat2_surat", alamat2);
			r.add("alamat3_surat", alamat3);
			r.add("poskod_surat", poskod);
			if (negeri != 0) {
				r.add("id_Negerisurat", negeri);
			}
			if (id_bandar != 0) {
				r.add("id_bandarsurat", id_bandar);
			}
			int tarsaksi = 2;
			r.add("id_Tarafkptg", tarsaksi);

			r.add("no_Akaun", noakaun);
			r.add("butiran_Hutang", butiranhutang);
			r.add("nilai_Hutang", nilaihutang);
			r.add("jenis_pemiutang", jenispemiutang);
			r.add("id_permohonansimati", idpermohonansimati);
			r.add("id_masuk", userid);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblppkob");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void addPenghutang(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			String idsimati = (String) data.get("idSimati");
			String namaob = (String) data.get("namaOB");
			String jenishutang = (String) data.get("jenishutang");

			double nilaihutang = (Double) data.get("nilaihutang");
			String butiranhutang = (String) data.get("butiranhutang");
			String noakaun = (String) data.get("noakaun");

			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = (String) data.get("nokppenting");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");

			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String bandar = (String) data.get("bandar");
			// String notel= (String)data.get("notel");
			int negeri = (Integer) data.get("negeri");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Simati", idsimati);
			r.add("nama_Penghutang", namaob);

			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokppenting);
			r.add("jenis_Kp", jenisKp);
			r.add("jenis_Penghutang", jenishutang);

			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);

			r.add("bandar", bandar);
			r.add("poskod", poskod);

			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}

			r.add("no_Akaun", noakaun);
			r.add("butiran_Hutang", butiranhutang);
			if (nilaihutang != 0) {
				r.add("jumlah_Hutang", nilaihutang);
			}

			sql = r.getSQLInsert("tblppkpenghutang");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void updateSaksi(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			String idsimati = (String) data.get("idSimati");
			String idob = (String) data.get("idsaksi");
			String namaob = (String) data.get("namaOB");
			String notel = (String) data.get("notel");

			String nokpbaru = (String) data.get("nokpbaru");
			String nokpsaksi = (String) data.get("nokpsaksi");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");

			String jantina = (String) data.get("jantina");
			String agama = (String) data.get("agama");
			String warga = (String) data.get("warga");

			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String bandar = (String) data.get("bandar");
			int negeri = (Integer) data.get("negeri");
			String catatan = (String) data.get("catatan");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_Ob", idob);

			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob);
			r.add("no_Tel", notel);

			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokpsaksi);
			r.add("jenis_Kp", jenisKp);

			r.add("jantina", jantina);
			r.add("jenis_Agama", agama);
			r.add("jenis_Warga", warga);

			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);

			r.add("bandar", bandar);
			r.add("poskod", poskod);

			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			r.add("catatan", catatan);

			sql = r.getSQLUpdate("tblppkob");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	/*
	 * public static void addPenghutang(Hashtable data) throws Exception { Db db
	 * = null; String sql = ""; try { //String ids = (String)data.get("id"); //
	 * int idpg=Integer.parseInt("idpg"); int
	 * idsimati=(Integer)data.get("idSimati"); String namapg=
	 * (String)data.get("namapg");
	 * 
	 * 
	 * String nokpbaru= (String)data.get("nokpbaru"); String nokppenting=
	 * (String)data.get("nokppenting"); String jenisKp=
	 * (String)data.get("jenisKp"); String nokplain=
	 * (String)data.get("nokplain"); String jenishutang=
	 * (String)data.get("jenishutang");
	 * 
	 * double jumlah=(Double)data.get("jumlah"); String noakaun=
	 * (String)data.get("noakaun");
	 * 
	 * String alamat1= (String)data.get("alamat1"); String alamat2=
	 * (String)data.get("alamat2"); String alamat3= (String)data.get("alamat3");
	 * String poskod= (String)data.get("poskod"); String bandar=
	 * (String)data.get("bandar"); int negeri=(Integer)data.get("negeri");
	 * String catatan= (String)data.get("catatan");
	 * 
	 * 
	 * 
	 * db = new Db(); Statement stmt = db.getStatement(); SQLRenderer r = new
	 * SQLRenderer(); r.add("id_Simati", idsimati); r.add("nama_Penghutang",
	 * namapg);
	 * 
	 * r.add("no_Kp_Baru", nokpbaru); r.add("no_Kp_Lain", nokplain);
	 * r.add("no_Kp_Lama", nokppenting); r.add("jenis_Kp", jenisKp);
	 * r.add("jenis_Penghutang", jenishutang); if(jumlah!=0) {
	 * r.add("jumlah_Hutang",jumlah); } r.add("no_Akaun", noakaun);
	 * 
	 * r.add("alamat_1", alamat1); r.add("alamat_2", alamat2); r.add("alamat_3",
	 * alamat3);
	 * 
	 * r.add("bandar", bandar); r.add("poskod", poskod);
	 * 
	 * if(negeri!=0) { r.add("id_Negeri", negeri); } r.add("butiran_Hutang",
	 * catatan);
	 * 
	 * 
	 * 
	 * sql = r.getSQLInsert("tblppkpenghutang"); stmt.executeUpdate(sql); }
	 * finally { if (db != null) db.close(); } }
	 */

	public static Vector getListPenting() throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id");
			r.add("name");
			r.add("description");

			sql = r.getSQLSelect("Tblppktest");
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id"));
				h.put("description", rs.getString("description"));

				if (rs.getString("name") == null) {
					h.put("name", "");
				} else {
					h.put("name", rs.getString("name"));
				}
				/*
				 * if(rs.getString("description") == null){ h.put("description",
				 * ""); }else{ h.put("description",
				 * rs.getString("description")); }
				 */
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void updateNilaiHartaBaruHta(String id, String NilaiMohon,
			String NilaiMati, String idSimati, String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			// int idHta = Integer.parseInt(id);
			// int IdPermohonan = Integer.parseInt(idPermohonan);
			// int idsimati = Integer.parseInt(idSimati);
			double nilaiHtaTarikhMohon = Double.parseDouble(NilaiMohon);
			double nilaiHtaTarikhMati = Double.parseDouble(NilaiMati);

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HTA", id);
			r.update("ID_SIMATI", idSimati);
			r.add("NILAI_HTA_TARIKHMOHON", nilaiHtaTarikhMohon);
			r.add("NILAI_HTA_TARIKHMATI", nilaiHtaTarikhMati);
			sql = r.getSQLUpdate("tblppkhta");
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void updateNilaiHartaBaruHa(String id, String NilaiMohon,
			String NilaiMati, String idSimati, String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";
		String sql2 = "";
		try {
			// int idHa = Integer.parseInt(id);
			// int IdPermohonan = Integer.parseInt(idPermohonan);
			// int idsimati = Integer.parseInt(idSimati);

			double nilaiHtaTarikhMohon = Double.parseDouble(NilaiMohon);
			double nilaiHtaTarikhMati = Double.parseDouble(NilaiMati);

			db = new Db();
			Statement stmtA = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_HA", id);
			r1.update("ID_SIMATI", idSimati);
			r1.add("NILAI_HA_TARIKHMOHON", nilaiHtaTarikhMohon);
			r1.add("NILAI_HA_TARIKHMATI", nilaiHtaTarikhMati);
			sql = r1.getSQLUpdate("tblppkha");
			stmtA.executeUpdate(sql);

			/*
			 * db = new Db(); Statement stmtB = db.getStatement(); SQLRenderer
			 * r2 = new SQLRenderer(); r2.update("ID_PERMOHONAN",idPermohonan);
			 * r2.add("JUMLAH_HTA_TARIKHMOHON", JumlahHtaTarikhMohon);
			 * r2.add("JUMLAH_HTA_TARIKHMATI", JumlahHtaTarikhMati);
			 * r2.add("JUMLAH_HA_TARIKHMOHON", JumlahHaTarikhMohon);
			 * r2.add("JUMLAH_HA_TARIKHMATI", JumlahHaTarikhMati);
			 * r2.add("JUMLAH_HARTA_TARIKHMOHON", JumlahBesarTarikhMohon);
			 * r2.add("JUMLAH_HARTA_TARIKHMATI", JumlahBesarTarikhMati); sql2 =
			 * r2.getSQLUpdate("tblppkpermohonan");
			 * System.out.println("sql2"+sql2); stmtB.executeUpdate(sql2);
			 */

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void updateWaris(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			String idwaris = (String) data.get("idwaris");
			String idsimati = (String) data.get("idSimati");
			String namaob = ((String) data.get("namaOB")).toUpperCase();
			String nokpbaru = (String) data.get("nokpbaru");
			String nokpwaris = (String) data.get("nokpwaris");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = ((String) data.get("nokplain")).toUpperCase();
			String nokplama = (String) data.get("nokplama");
			String jantina = (String) data.get("jantina");
			String agama = (String) data.get("agama");
			String warga = (String) data.get("warga");
			String alamat1 = ((String) data.get("alamat1")).toUpperCase();
			String alamat2 = ((String) data.get("alamat2")).toUpperCase();
			String alamat3 = ((String) data.get("alamat3")).toUpperCase();
			String poskod = (String) data.get("poskod");
			String bandar = ((String) data.get("bandar")).toUpperCase();
			String negeri = (String) data.get("negeri");
			int saudara = (Integer) data.get("saudara");
			String catatan = ((String) data.get("catatan")).toUpperCase();
			String notel = (String) data.get("notel");
			String nohp = (String) data.get("hp");
			int statusWaris = (Integer) data.get("statusWaris");
			String waktumatiwaris = (String) data.get("waktumatiwaris");
			String checkmati = (String) data.get("checkmati");
			String tarikhmati = (String) data.get("tarikhmati");
			String tarikhlahir = (String) data.get("tarikhlahir");
			String tarikhmati_format = "to_date('" + tarikhmati
					+ "','dd/MM/yyyy')";
			String tarikhlahir_format = "to_date('" + tarikhlahir
					+ "','dd/MM/yyyy')";
			int umur = (Integer) data.get("umur");
			// String tarikhlahir_format = "to_date('" + tarikhlahir +
			// "','dd/MM/yyyy')";
			String noberanak = ((String) data.get("noberanak")).toUpperCase();
			String chcAlamat = (String) data.get("chcAlamat");

			String alamatSurat1Pemohon = (String) data
					.get("alamatSurat1Pemohon");
			String alamatSurat2Pemohon = (String) data
					.get("alamatSurat2Pemohon");
			String alamatSurat3Pemohon = (String) data
					.get("alamatSurat3Pemohon");
			String poskodSuratPemohon = (String) data.get("poskodSuratPemohon");
			String bandarSuratPemohon = (String) data.get("bandarSuratPemohon");
			String negeriSurat = (String) data.get("negerisurat");
			int id_bandar = (Integer) data.get("id_bandar");
			int id_bandarsurat = (Integer) data.get("id_bandarsurat");
			String idPermohonan = (String) data.get("idPermohonan");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_Ob", idwaris);
			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob);
			r.add("no_Tel", notel);
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokpwaris);
			r.add("jenis_Kp", jenisKp);
			r.add("jantina", jantina);
			r.add("jenis_Agama", agama);
			r.add("jenis_Warga", warga);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("bandar", bandar);
			r.add("poskod", poskod);
			if (!negeri.equals("0")) {
				r.add("id_Negeri", Integer.parseInt(negeri));
			} else {
				r.add("id_Negeri", 0);
			}
			if (id_bandar != 0) {
				r.add("id_bandar", id_bandar);
			} else {
				r.add("id_bandar", 0);
			}
			r.add("catatan", catatan);
			r.add("no_Hp", nohp);
			if (saudara != 0) {
				r.add("id_Saudara", saudara);
			}
			r.add("status_Ob", statusWaris);
			r.add("status_Hidup", checkmati);
			r.add("waktu_Kematian", waktumatiwaris);
			if (tarikhmati != "") {
				r.add("tarikh_Mati", r.unquote(tarikhmati_format));
			}
			if (tarikhlahir != "") {
				r.add("tarikh_Lahir", r.unquote(tarikhlahir_format));
			}

			if (umur != 0) {
				r.add("umur", umur);
			}
			if (chcAlamat.equals("1")) {
				r.add("ALAMAT1_SURAT", alamat1);
				r.add("ALAMAT2_SURAT", alamat2);
				r.add("ALAMAT3_SURAT", alamat3);
				r.add("ID_BANDARSURAT", id_bandar);
				r.add("POSKOD_SURAT", poskod);
				r.add("ID_NEGERISURAT", Integer.parseInt(negeri));
			} else if (chcAlamat.equals("0")) {
				r.add("ALAMAT1_SURAT", alamatSurat1Pemohon);
				r.add("ALAMAT2_SURAT", alamatSurat2Pemohon);
				r.add("ALAMAT3_SURAT", alamatSurat3Pemohon);
				r.add("ID_BANDARSURAT", id_bandarsurat);
				r.add("POSKOD_SURAT", poskodSuratPemohon);
				r.add("ID_NEGERISURAT", Integer.parseInt(negeriSurat));
			}
			r.add("no_Surat_beranak", noberanak);
			sql = r.getSQLUpdate("tblppkob");
			System.out.println("update waris-->>" + sql);
			stmt.executeUpdate(sql);

			/*
			 * Statement stm = db.getStatement(); SQLRenderer r1 = new
			 * SQLRenderer(); r1.update("id_permohonan",idPermohonan);
			 * r1.add("NO_KP_BARU",nokpbaru); r1.add("NO_KP_LAMA",nokpwaris);
			 * r1.add("JENIS_KP",jenisKp); r1.add("NO_KP_LAIN",nokplain);
			 * r1.add("NAMA_PEMOHON",namaob); r1.add("UMUR",umur);
			 * r1.add("JANTINA",jantina); r1.add("JENIS_AGAMA",agama);
			 * r1.add("ALAMAT_1", alamat1); r1.add("ALAMAT_2", alamat2);
			 * r1.add("ALAMAT_3", alamat3); r1.add("BANDAR", bandar);
			 * r1.add("POSKOD", poskod); r1.add("NO_HP",notel);
			 * r1.add("NO_TEL",nohp); r1.add("ID_SAUDARA", saudara);
			 * if(!negeri.equals("0")) { r1.add("ID_NEGERI",
			 * Integer.parseInt(negeri)); }else{ r1.add("ID_NEGERI", 0); } if
			 * (chcAlamat.equals("1")){ r1.add("ALAMAT1_SURAT", alamat1);
			 * r1.add("ALAMAT2_SURAT", alamat2); r1.add("ALAMAT3_SURAT",
			 * alamat3); r1.add("BANDAR_SURAT", bandar); r1.add("POSKOD_SURAT",
			 * poskod); r1.add("ID_NEGERISURAT", negeri); }else if
			 * (chcAlamat.equals("0")){ r1.add("ALAMAT1_SURAT",
			 * alamatSurat1Pemohon); r1.add("ALAMAT2_SURAT",
			 * alamatSurat2Pemohon); r1.add("ALAMAT3_SURAT",
			 * alamatSurat3Pemohon); r1.add("BANDAR_SURAT", bandarSuratPemohon);
			 * r1.add("POSKOD_SURAT", poskodSuratPemohon);
			 * r1.add("ID_NEGERISURAT", negeriSurat); } String sql1 =
			 * r1.getSQLUpdate("tblppkpemohon");
			 * System.out.println("tblppkpemohon--->>"+sql1);
			 * System.out.println("chcAlamat --->>"+chcAlamat);
			 * stm.executeUpdate(sql1);
			 */
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void addWaris(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			String idsimati = (String) data.get("idSimati");
			String namaob = (String) data.get("namaOB");
			String nokpbaru = (String) data.get("nokpbaru");
			String nokpwaris = (String) data.get("nokpwaris");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");
			String jantina = (String) data.get("jantina");
			String agama = (String) data.get("agama");
			String warga = (String) data.get("warga");
			String idpermohonansimati = (String) data.get("idpermohonansimati");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			// String bandar= (String)data.get("bandar");
			int id_bandar = (Integer) data.get("id_bandar");
			int negeri = (Integer) data.get("negeri");

			String chkAddWaris = (String) data.get("chkAddWaris");
			String alamat1Surat = (String) data.get("alamat1Surat");
			String alamat2Surat = (String) data.get("alamat2Surat");
			String alamat3Surat = (String) data.get("alamat3Surat");
			String poskodSurat = (String) data.get("poskodSurat");
			// String bandarSurat= (String)data.get("bandarSurat");
			int id_bandarsurat = (Integer) data.get("id_bandarsurat");
			int negeriSurat = (Integer) data.get("negerisurat");

			int saudara = (Integer) data.get("saudara");
			String catatan = (String) data.get("catatan");
			String notel = (String) data.get("notel");
			String nohp = (String) data.get("hp");
			int statusWaris = (Integer) data.get("statusWaris");
			String waktumatiwaris = (String) data.get("waktumatiwaris");
			String checkmati = (String) data.get("checkmati");
			int umur = (Integer) data.get("umur");
			String noberanak = (String) data.get("noberanak");
			String tarikhmati = (String) data.get("tarikhmati");
			String tarikhlahir = (String) data.get("tarikhlahir");
			String tarikhmati_format = "to_date('" + tarikhmati
					+ "','dd/MM/yyyy')";
			String tarikhlahir_format = "to_date('" + tarikhlahir
					+ "','dd/MM/yyyy')";

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob);
			r.add("no_Tel", notel);
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokpwaris);
			r.add("jenis_Kp", jenisKp);
			r.add("jantina", jantina);
			r.add("jenis_Agama", agama);
			r.add("jenis_Warga", warga);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("id_bandar", id_bandar);
			r.add("poskod", poskod);

			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			r.add("catatan", catatan);
			r.add("no_Hp", nohp);

			if (saudara != 0) {
				r.add("id_Saudara", saudara);
			}
			r.add("status_Ob", statusWaris);
			r.add("status_Hidup", checkmati);
			r.add("waktu_Kematian", waktumatiwaris);
			r.add("id_Tarafkptg", 1);
			r.add("no_Surat_beranak", noberanak);

			if (tarikhmati != "") {
				r.add("tarikh_Mati", r.unquote(tarikhmati_format));
			}
			if (tarikhlahir != "") {
				r.add("tarikh_Lahir", r.unquote(tarikhlahir_format));
			}

			if (umur != 0) {
				r.add("umur", umur);
			}
			if (chkAddWaris.equals("0")) {
				r.add("alamat1_surat", alamat1);
				r.add("alamat2_surat", alamat2);
				r.add("alamat3_surat", alamat3);
				r.add("poskod_surat", poskod);
				r.add("id_bandarsurat", id_bandar);
				r.add("id_negerisurat", negeri);
			} else {
				r.add("alamat1_surat", alamat1Surat);
				r.add("alamat2_surat", alamat2Surat);
				r.add("alamat3_surat", alamat3Surat);
				r.add("poskod_surat", poskodSurat);
				r.add("id_bandarsurat", id_bandarsurat);
				r.add("id_negerisurat", negeriSurat);

			}
			r.add("lapis", 1);
			r.add("id_permohonansimati", idpermohonansimati);
			sql = r.getSQLInsert("tblppkob");
			System.out.println("addWaris-->>" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void addWarisBerikut(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";
		try {
			// String ids = (String)data.get("id");
			// int id=Integer.parseInt(ids);
			long idOB_gabung = (Long) data.get("idOB_gabung");
			String idsimati = (String) data.get("idSimati");
			String namaob = (String) data.get("namaOB");
			String nokpbaru = (String) data.get("nokpbaru");
			String nokpwaris = (String) data.get("nokpwaris");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");
			String jantina = (String) data.get("jantina");
			String agama = (String) data.get("agama");
			String warga = (String) data.get("warga");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String bandar = (String) data.get("bandar");
			int negeri = (Integer) data.get("negeri");
			int saudara = (Integer) data.get("saudara");
			String idpermohonansimati = (String) data.get("idpermohonansimati");
			String catatan = (String) data.get("catatan");
			String notel = (String) data.get("notel");
			String nohp = (String) data.get("hp");
			int statusWaris = (Integer) data.get("statusWaris");
			String waktumatiwaris = (String) data.get("waktumatiwaris");
			String checkmati = (String) data.get("checkmati");
			int umur = (Integer) data.get("umur");
			int lapis = (Integer) data.get("lapis");
			String noberanak = (String) data.get("noberanak");
			String tarikhmati = (String) data.get("tarikhmati");
			String tarikhlahir = (String) data.get("tarikhlahir");
			String tarikhmati_format = "to_date('" + tarikhmati
					+ "','dd/MM/yyyy')";
			String tarikhlahir_format = "to_date('" + tarikhlahir
					+ "','dd/MM/yyyy')";
			String idparent = (String) data.get("idparent");

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_Ob", idOB_gabung);
			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob);
			r.add("no_Tel", notel);
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokpwaris);
			r.add("jenis_Kp", jenisKp);
			r.add("jantina", jantina);
			r.add("jenis_Agama", agama);
			r.add("jenis_Warga", warga);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("bandar", bandar);
			r.add("poskod", poskod);

			if (negeri != 0) {
				r.add("id_Negeri", negeri);

			}
			r.add("catatan", catatan);

			if (lapis != 0) {
				r.add("lapis", lapis + 1);
			}

			r.add("no_Hp", nohp);

			if (saudara != 0) {
				r.add("id_Saudara", saudara);
			}

			r.add("status_Ob", statusWaris);
			r.add("status_Hidup", checkmati);
			r.add("waktu_Kematian", waktumatiwaris);
			r.add("id_Tarafkptg", 1);

			r.add("no_Surat_beranak", noberanak);
			r.add("id_permohonansimati", idpermohonansimati);

			if (tarikhmati != "") {
				r.add("tarikh_Mati", r.unquote(tarikhmati_format));
			}
			if (tarikhlahir != "") {
				r.add("tarikh_Lahir", r.unquote(tarikhlahir_format));
			}
			if (umur != 0) {
				r.add("umur", umur);
			}
			sql = r.getSQLInsert("tblppkob");
			stmt.executeUpdate(sql);

			db = new Db();
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();

			r1.add("id_Ob", idOB_gabung);
			r1.add("id_Parentob", idparent);
			r1.add("id_Saudara", saudara);
			sql1 = r1.getSQLInsert("tblppkhubunganob");
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();

		}
	}

	public void setDataPemohon(String id) throws Exception {
		Db db = null;
		listPemohon.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("p.id_Permohonan");
			r.add("pm.id_Pemohon");
			r.add("pm.no_Kp_Baru");
			r.add("pm.no_Kp_Lama");
			r.add("pm.jenis_Kp");
			r.add("pm.no_Kp_Lain");
			r.add("pm.nama_Pemohon");
			r.add("pm.jantina");
			r.add("pm.jenis_Warga");
			r.add("pm.jenis_Agama");
			r.add("pm.id_Tarafkptg");
			r.add("pm.id_Saudara");
			r.add("pm.umur");
			r.add("pm.alamat_1");
			r.add("pm.alamat_2");
			r.add("pm.alamat_3");
			r.add("pm.poskod");
			r.add("pm.bandar");
			r.add("pm.id_Negeri");
			r.add("pm.no_Hp");
			r.add("pm.no_Tel");
			r.add("pm.emel");
			r.add("pm.no_Fax");
			r.add("pm.catatan");
			// r.add("n.nama_Negeri");
			// r.add("tf.keterangan");
			r.add("pm.id_Saudara");
			// r.add("sa.keterangan");
			// r.add("tf.kod");

			r.add("pm.id_Permohonan", r.unquote("p.id_Permohonan"));
			// r.add("pm.id_Negeri",r.unquote("n.id_Negeri"));
			// r.add("pm.id_Tarafkptg",r.unquote("tf.id_Tarafkptg"));
			// r.add("pm.id_Saudara",r.unquote("sa.id_Saudara"));

			r.add("p.id_Permohonan", id);

			// Tblrujnegeri n, Tblppkrujtarafkptg tf, Tblppkrujsaudara sa

			sql = r.getSQLSelect("Tblppkpemohon pm, Tblppkpermohonan p");

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("idPemohon", rs.getString("id_Pemohon") == null ? "" : rs
						.getString("id_Pemohon"));
				h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("noKpLama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jenisKp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("noKpLain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("namaPemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("idTarafkptg", rs.getString("id_Tarafkptg") == null ? ""
						: rs.getString("id_Tarafkptg"));

				// h.put("kodTarafkptg",
				// rs.getString(29)==null?"":rs.getString(29));

				h.put("idSaudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("jenisWarga", rs.getString("jenis_Warga") == null ? ""
						: rs.getString("jenis_Warga"));
				h.put("jenisAgama", rs.getString("jenis_Agama") == null ? ""
						: rs.getString("jenis_Agama"));
				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));
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
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noHp", rs.getString("no_Hp") == null ? "" : rs
						.getString("no_Hp"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("emel", rs.getString("emel") == null ? "" : rs
						.getString("emel"));
				h.put("noFax", rs.getString("no_Fax") == null ? "" : rs
						.getString("no_Fax"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				// h.put("namanegeri",
				// rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
				// h.put("keterangan",
				// rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("idsaudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				// h.put("saudara_keterangan",
				// rs.getString(28)==null?"":rs.getString(28));
				// h.put("kodtaraf",
				// rs.getString(29)==null?"":rs.getString(29));

				listPemohon.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataPeguam(int idpeguam) throws Exception {
		Db db = null;
		listPeguam.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("pm.id_Pemohon");
			r.add("p.id_Pemohon");
			r.add("pg.id_Peguam");
			r.add("pg.nama_Peguam");
			r.add("pg.alamat1");
			r.add("pg.alamat2");
			r.add("pg.alamat3");
			r.add("pg.bandar");
			r.add("pg.poskod");
			r.add("pg.id_Negeri");
			r.add("pg.no_Tel");
			r.add("pg.no_Fax");
			r.add("pg.no_Syarikat");
			r.add("pg.no_Rujukan_Firma");
			r.add("pg.nama_Firma");
			r.add("pg.emel");

			r.add("pm.id_Pemohon", r.unquote("p.id_Pemohon"));
			r.add("p.id_peguam", r.unquote("pg.id_peguam"));

			r.add("pg.id_peguam", idpeguam);

			sql = r
					.getSQLSelect("Tblppkpeguam pg, Tblppkpemohon pm, Tblppkpeguampemohon p");
			System.out.println("PEGUAM SIZEE" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			// Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idPemohon", rs.getString("id_Pemohon") == null ? "" : rs
						.getString("id_Pemohon"));
				h.put("idPeguam", rs.getString("id_Peguam") == null ? "" : rs
						.getString("id_Peguam"));
				h.put("namaPeguam", rs.getString("nama_Peguam") == null ? ""
						: rs.getString("nama_Peguam"));
				h.put("noRujukanFirma",
						rs.getString("no_Rujukan_Firma") == null ? "" : rs
								.getString("no_Rujukan_Firma"));
				h.put("alamat1", rs.getString("alamat1") == null ? "" : rs
						.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2") == null ? "" : rs
						.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3") == null ? "" : rs
						.getString("alamat3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("noFax", rs.getString("no_Fax") == null ? "" : rs
						.getString("no_Fax"));
				h.put("noSyarikat", rs.getString("no_Syarikat") == null ? ""
						: rs.getString("no_Syarikat"));
				h.put("namaFirma", rs.getString("nama_Firma") == null ? "" : rs
						.getString("nama_Firma"));
				h.put("emel", rs.getString("emel") == null ? "" : rs
						.getString("emel"));
				listPeguam.addElement(h);
				// count++;
			}
			/*
			 * if (count == 0){ h = new Hashtable(); h.put("idPemohon", "");
			 * h.put("idPeguam", ""); h.put("namaPeguam", "");
			 * h.put("noRujukanFirma", ""); h.put("alamat1", "");
			 * h.put("alamat2", ""); h.put("alamat3", ""); h.put("bandar", "");
			 * h.put("poskod", ""); h.put("idnegeri", ""); h.put("noTel", "");
			 * h.put("noFax", ""); h.put("noSyarikat", ""); h.put("namaFirma",
			 * ""); h.put("emel", ""); listPeguam.addElement(h); }
			 */
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataWarisUpdate(String id) throws Exception {
		Db db = null;
		listWarisUpdate.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");

			r.add("ob.no_Tel");

			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");
			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.waktu_Kematian");
			r.add("ob.lapis");

			r.add("ob.alamat1_surat");
			r.add("ob.alamat2_surat");
			r.add("ob.alamat3_surat");
			r.add("ob.bandar_surat");
			r.add("ob.poskod_surat");
			r.add("ob.id_negerisurat");
			r.add("ob.id_bandar");
			r.add("ob.id_bandarsurat");

			r.add("ob.id_Simati", r.unquote("m1.id_Simati"));
			r.add("p.id_Permohonan", r.unquote("m1.id_Permohonan"));
			r.add("m1.id_simati", r.unquote("m.id_simati"));

			r.add("ob.id_Ob", id);
			r.add("ob.id_Tarafkptg", 1);

			sql = r
					.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati m1");
			sql = sql + " and p.seksyen = 8 and rownum < 2";
			System.out.println("setDataWarisUpdate-->>" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-"
						: formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs
						.getString("no_Hp"));
				h.put("lapis", rs.getString("lapis") == null ? "" : rs
						.getString("lapis"));
				h.put("alamat1Surat",
						rs.getString("alamat1_surat") == null ? "" : rs
								.getString("alamat1_surat"));
				h.put("alamat2Surat",
						rs.getString("alamat2_surat") == null ? "" : rs
								.getString("alamat2_surat"));
				h.put("alamat3Surat",
						rs.getString("alamat3_surat") == null ? "" : rs
								.getString("alamat3_surat"));
				h.put("bandarsurat", rs.getString("bandar_surat") == null ? ""
						: rs.getString("bandar_surat"));
				h.put("poskodsurat", rs.getString("poskod_surat") == null ? ""
						: rs.getString("poskod_surat"));
				h.put("idnegerisurat",
						rs.getString("id_negerisurat") == null ? "" : rs
								.getString("id_negerisurat"));
				h.put("idbandarsurat",
						rs.getString("id_bandarsurat") == null ? "" : rs
								.getString("id_bandarsurat"));
				h.put("idbandar", rs.getString("id_bandar") == null ? "" : rs
						.getString("id_bandar"));
				listWarisUpdate.addElement(h);

			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataPenting(String id) throws Exception {
		Db db = null;
		listPenting.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			r.add("ob.catatan");

			r.add("ob.butiran_Hutang");
			r.add("ob.nilai_Hutang");
			r.add("ob.no_Akaun");
			r.add("ob.jenis_pemiutang");
			r.add("ob.id_bandar");
			r
					.add("ob.id_permohonansimati", r
							.unquote("m1.id_permohonansimati"));
			r.add("m1.id_Simati", r.unquote("m.id_Simati(+)"));
			r.add("m1.id_Permohonan", r.unquote("p.id_Permohonan(+)"));

			r.add("p.id_Permohonan", id);
			r.add("p.seksyen", 8);

			sql = r
					.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati M1");
			System.out.println("data pemiutang-->>>>" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));
				h.put("dob", rs.getString("tarikh_Lahir") == null ? "" : rs
						.getString("tarikh_Lahir"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));
				h.put("butiranhutang",
						rs.getString("butiran_Hutang") == null ? "" : rs
								.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("nilai_Hutang") == null ? ""
						: rs.getDouble("nilai_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs
						.getString("no_Akaun"));
				h.put("pemiutang", rs.getString("jenis_pemiutang") == null ? ""
						: rs.getString("jenis_pemiutang"));
				h.put("idbandar", rs.getString("id_bandar") == null ? "" : rs
						.getString("id_bandar"));
				listPenting.addElement(h);
				count++;
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataPenghutang(String id) throws Exception {
		Db db = null;
		listPenghutang.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Penghutang");
			r.add("ob.id_Simati");
			r.add("ob.id_Penghutang");
			r.add("ob.nama_Penghutang");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.id_Negeri");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			r.add("ob.jenis_Penghutang");
			r.add("ob.butiran_Hutang");
			r.add("ob.jumlah_Hutang");
			r.add("ob.no_Akaun");
			r.add("ob.id_Simati", r.unquote("m.id_Simati"));
			r.add("ob.id_Simati", id);
			sql = r.getSQLSelect("Tblppkpenghutang ob, Tblppksimati m");
			System.out.println("listpenting ---->>>" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Penghutang") == null ? "" : rs
						.getString("id_Penghutang"));
				h.put("nama_Ob", rs.getString("nama_Penghutang") == null ? ""
						: rs.getString("nama_Penghutang"));
				h.put("jenishutang",
						rs.getString("jenis_Penghutang") == null ? "" : rs
								.getString("jenis_Penghutang"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));
				h.put("butiranhutang",
						rs.getString("butiran_Hutang") == null ? "" : rs
								.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("jumlah_Hutang") == null ? ""
						: rs.getDouble("jumlah_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs
						.getString("no_Akaun"));
				listPenghutang.addElement(h);
				count++;
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataPenghutangbyIDOB(String id, String idpermohonansimati)
			throws Exception {
		Db db = null;
		listPenghutangbyIDOB.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Penghutang");
			r.add("ob.id_Simati");
			r.add("ob.id_Penghutang");
			r.add("ob.nama_Penghutang");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.id_Negeri");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			r.add("ob.jenis_Penghutang");
			r.add("ob.butiran_Hutang");
			r.add("ob.jumlah_Hutang");
			r.add("ob.no_Akaun");
			r.add("ob.id_bandar");

			r.add("ob.id_Simati", r.unquote("m.id_Simati"));
			r.add("ob.id_Penghutang", id);
			// r.add("ob.id_permohonansimati",idpermohonansimati);

			sql = r.getSQLSelect("Tblppkpenghutang ob, Tblppksimati m");

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Penghutang") == null ? "" : rs
						.getString("id_Penghutang"));
				h.put("nama_Ob", rs.getString("nama_Penghutang") == null ? ""
						: rs.getString("nama_Penghutang"));
				h.put("jenishutang",
						rs.getString("jenis_Penghutang") == null ? "" : rs
								.getString("jenis_Penghutang"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));
				h.put("butiranhutang",
						rs.getString("butiran_Hutang") == null ? "" : rs
								.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("jumlah_Hutang") == null ? ""
						: rs.getDouble("jumlah_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs
						.getString("no_Akaun"));
				h.put("idbandar", rs.getString("id_bandar") == null ? "" : rs
						.getString("id_bandar"));
				listPenghutangbyIDOB.addElement(h);
				count++;
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataPentingbyIDOB(String id) throws Exception {
		Db db = null;
		listPentingbyIDOB.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			r.add("ob.catatan");
			r.add("ob.butiran_Hutang");
			r.add("ob.nilai_Hutang");
			r.add("ob.no_Akaun");
			r.add("ob.no_tel");
			r.add("ob.no_hp");
			r.add("n.nama_negeri");
			r.add("ob.jenis_pemiutang");
			r.add("ob.id_bandar");

			r
					.add("ob.id_permohonansimati", r
							.unquote("m1.id_permohonansimati"));
			r.add("m1.id_Permohonan", r.unquote("p.id_Permohonan(+)"));
			r.add("m1.id_Permohonan", r.unquote("m.id_simati(+)"));
			r.add("ob.id_Negeri", r.unquote("n.id_negeri(+)"));

			r.add("ob.id_Ob", id);

			sql = r
					.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati M1, Tblrujnegeri n");
			sql = sql
					+ " and p.seksyen = 8 group by ob.id_Simati, ob.id_Ob, ob.nama_Ob, ob.no_Kp_Baru, ob.no_Kp_Lama, ob.jenis_Kp, ob.no_Kp_Lain, "
					+ "ob.no_Surat_Beranak, ob.tarikh_Lahir, ob.jantina, ob.umur, ob.alamat_1, ob.alamat_2, ob.alamat_3, ob.bandar, "
					+ "ob.poskod, ob.no_Tel, ob.status_Hidup, ob.status_Ob, ob.id_Tarafkptg, ob.id_Negeri, ob.id_Saudara, ob.jenis_Agama, "
					+ "ob.jenis_Warga, ob.catatan, ob.butiran_Hutang, ob.nilai_Hutang, ob.no_Akaun, ob.no_tel, ob.no_hp, n.nama_negeri, "
					+ "ob.jenis_pemiutang, ob.id_bandar";

			System.out.println("data-->>>" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));
				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));
				h.put("butiranhutang",
						rs.getString("butiran_Hutang") == null ? "" : rs
								.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("nilai_Hutang") == null ? ""
						: rs.getDouble("nilai_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs
						.getString("no_Akaun"));
				h.put("negeri", rs.getString("nama_negeri") == null ? "" : rs
						.getString("nama_negeri"));
				h.put("notel", rs.getString("no_tel") == null ? "" : rs
						.getString("no_tel"));
				h.put("nohp", rs.getString("no_hp") == null ? "" : rs
						.getString("no_hp"));
				h.put("pemiutang", rs.getString("jenis_pemiutang") == null ? ""
						: rs.getString("jenis_pemiutang"));
				h.put("idbandar", rs.getString("id_bandar") == null ? "" : rs
						.getString("id_bandar"));
				listPentingbyIDOB.addElement(h);

			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataWarisParent(String id) throws Exception {
		Db db = null;
		listWarisParent.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");

			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");
			r.add("ob.lapis");

			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.waktu_Kematian");

			r.add("ob.id_Simati", r.unquote("m1.id_simati"));
			r.add("p.id_Permohonan", r.unquote("m1.id_Permohonan"));
			r.add("m1.id_simati", r.unquote("m.id_simati"));

			r.add("ob.id_Ob", id);

			int k = 1;
			r.add("ob.id_Tarafkptg", k);

			// r.add("ob.id_simati",simati);

			sql = r
					.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati m1");
			sql = sql
					+ " group by ob.id_Simati, ob.id_Ob, ob.nama_Ob, ob.no_Kp_Baru, ob.no_Kp_Lama, ob.jenis_Kp, ob.no_Kp_Lain, "
					+ "ob.no_Surat_Beranak, ob.tarikh_Lahir, ob.jantina, ob.umur, ob.alamat_1, ob.alamat_2, ob.alamat_3, "
					+ "ob.bandar, ob.poskod, ob.no_Tel, ob.status_Hidup, ob.status_Ob, ob.id_Tarafkptg, ob.id_Negeri, "
					+ "ob.id_Saudara, ob.jenis_Agama, ob.jenis_Warga, ob.catatan, ob.lapis, ob.tarikh_Mati, ob.no_Hp, "
					+ "ob.waktu_Kematian";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));

				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));

				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-"
						: formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs
						.getString("no_Hp"));
				h.put("lapis", rs.getString("lapis") == null ? "" : rs
						.getString("lapis"));
				listWarisParent.addElement(h);

			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataWaris(String id) throws Exception {
		Db db = null;
		listWaris.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");

			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");
			r.add("ob.lapis");
			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.waktu_Kematian");
			r.add("ob.id_bandar");
			r.add("ob.id_bandarsurat");
			r.add("ob.id_negerisurat");

			r.add("ob.id_Simati", r.unquote("m1.id_Simati"));
			r.add("p.id_Permohonan", r.unquote("m1.id_Permohonan"));
			r.add("m1.id_simati", r.unquote("m.id_simati"));

			r.add("p.id_Permohonan", id);

			int k = 1;
			r.add("ob.id_Tarafkptg", k);
			r.add("ob.lapis", k);

			// r.add("ob.id_simati",simati);

			sql = r
					.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati m1");
			sql = sql + " ORDER BY ob.lapis";
			System.out.println("setDataWaris XX-->>" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));

				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));

				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-"
						: formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs
						.getString("no_Hp"));
				h.put("lapis", rs.getString("lapis") == null ? "" : rs
						.getString("lapis"));
				h.put("idbandar", rs.getString("id_bandar") == null ? "" : rs
						.getString("id_bandar"));
				h.put("idbandarsurat",
						rs.getString("id_bandarsurat") == null ? "" : rs
								.getString("id_bandarsurat"));
				h.put("idnegerisurat",
						rs.getString("id_negerisurat") == null ? "" : rs
								.getString("id_negerisurat"));
				listWaris.addElement(h);

			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataWarisLapisan(String idwaris) throws Exception {
		Db db = null;
		listWarisLapisan.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");

			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");

			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.waktu_Kematian");
			r.add("hu.id_Parentob");
			r.add("ob.lapis");
			r.add("hu.id_hubunganob");
			r.add("hu.id_Ob", r.unquote("ob.id_Ob"));
			r.add("hu.id_Parentob", idwaris);

			int k = 1;
			r.add("ob.id_Tarafkptg", k);

			sql = r.getSQLSelect("Tblppkob ob, Tblppkhubunganob hu");

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));

				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));

				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-"
						: formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs
						.getString("no_Hp"));

				h.put("idparent", rs.getString("id_Parentob") == null ? "" : rs
						.getString("id_Parentob"));

				h.put("lapis", rs.getString("lapis") == null ? "" : rs
						.getString("lapis"));
				listWarisLapisan.addElement(h);

			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataSaksi(String simati) throws Exception {
		Db db = null;
		listSaksi.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");

			r.add("ob.id_Simati", r.unquote("m.id_Simati"));

			r.add("ob.id_simati", simati);

			int k = 14;
			r.add("ob.id_Tarafkptg", k);

			sql = r.getSQLSelect("Tblppkob ob, Tblppksimati m");

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));
				h.put("dob", rs.getString("tarikh_Lahir") == null ? "" : rs
						.getString("tarikh_Lahir"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));
				listSaksi.addElement(h);

			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public static void updatePemiutang(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			String idpm = (String) data.get("idpm");
			String idsimati = (String) data.get("idSimati");
			String namapg = (String) data.get("namapg");

			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = (String) data.get("nokppenting");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");

			double jumlah = (Double) data.get("jumlah");
			String noakaun = (String) data.get("noakaun");

			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String bandar = (String) data.get("bandar");
			int negeri = (Integer) data.get("negeri");
			String catatan = (String) data.get("catatan");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_Ob", idpm);

			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namapg);

			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokppenting);
			r.add("jenis_Kp", jenisKp);

			if (jumlah != 0) {
				r.add("nilai_Hutang", jumlah);
			}
			r.add("no_Akaun", noakaun);

			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);

			r.add("bandar", bandar);
			r.add("poskod", poskod);

			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			r.add("butiran_Hutang", catatan);

			sql = r.getSQLUpdate("tblppkob");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	/*
	 * public void setDataPenghutang(int simati) throws Exception { Db db =
	 * null; listPenghutang.clear(); String sql = "";
	 * 
	 * 
	 * try{ db = new Db(); Statement stmt = db.getStatement(); SQLRenderer r =
	 * new SQLRenderer(); r.add("ob.id_Simati"); r.add("ob.id_Penghutang");
	 * r.add("ob.nama_Penghutang"); r.add("ob.no_Kp_Baru");
	 * r.add("ob.no_Kp_Lama"); r.add("ob.jenis_Kp"); r.add("ob.no_Kp_Lain");
	 * r.add("ob.no_Akaun"); r.add("ob.jumlah_Hutang");
	 * r.add("ob.jenis_Penghutang"); r.add("ob.alamat_1"); r.add("ob.alamat_2");
	 * r.add("ob.alamat_3"); r.add("ob.bandar"); r.add("ob.poskod");
	 * r.add("ob.id_Negeri"); r.add("ob.butiran_Hutang");
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * r.add("ob.id_Simati",r.unquote("m.id_Simati"));
	 * 
	 * r.add("ob.id_Simati",simati);
	 * 
	 * sql = r.getSQLSelect("Tblppkpenghutang ob, Tblppksimati m");
	 * 
	 * ResultSet rs = stmt.executeQuery(sql); Hashtable h; Integer count = 0;
	 * 
	 * while(rs.next()) {
	 * 
	 * 
	 * h = new Hashtable();
	 * 
	 * 
	 * 
	 * 
	 * h.put("idSimati",
	 * rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
	 * h.put("idpg",
	 * rs.getString("id_Penghutang")==null?"":rs.getString("id_Penghutang"));
	 * h.put("nama_Pg",
	 * rs.getString("nama_Penghutang")==null?"":rs.getString("nama_Penghutang"
	 * )); h.put("jumlah",
	 * rs.getString("jumlah_Hutang")==null?"":rs.getString("jumlah_Hutang"));
	 * h.put("jenishutang",
	 * rs.getString("jenis_Penghutang")==null?"":rs.getString
	 * ("jenis_Penghutang")); h.put("noakaun",
	 * rs.getString("no_Akaun")==null?"":rs.getString("no_Akaun"));
	 * h.put("nokpbaru",
	 * rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
	 * h.put("nokpbaru1",
	 * rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"
	 * ).substring(0,6)); h.put("nokpbaru2",
	 * rs.getString("no_Kp_Baru")==null?"":
	 * rs.getString("no_Kp_Baru").substring(6,8)); h.put("nokpbaru3",
	 * rs.getString
	 * ("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
	 * h.put("nokplama",
	 * rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
	 * h.put("jeniskp",
	 * rs.getString("jenis_Kp")==null?"":rs.getString("jenis_Kp"));
	 * h.put("nokplain",
	 * rs.getString("no_Kp_Lain")==null?"":rs.getString("no_Kp_Lain"));
	 * h.put("idnegeri",
	 * rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
	 * h.put("alamat1",
	 * rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
	 * h.put("alamat2",
	 * rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
	 * h.put("alamat3",
	 * rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
	 * h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
	 * h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
	 * h.put("catatan",
	 * rs.getString("butiran_Hutang")==null?"":rs.getString("butiran_Hutang"));
	 * listPenghutang.addElement(h);
	 * 
	 * 
	 * } } finally { if(db != null) db.close(); }
	 * 
	 * }
	 */
	public void setDataPemiutang(String simati) throws Exception {
		Db db = null;
		listPemiutang.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Akaun");
			r.add("ob.nilai_Hutang");

			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.id_Negeri");
			r.add("ob.butiran_Hutang");

			r.add("ob.id_Simati", r.unquote("m.id_Simati"));

			r.add("ob.id_Simati", simati);

			int h = 2;
			r.add("ob.id_Tarafkptg", h);

			sql = r.getSQLSelect("Tblppkob ob, Tblppksimati m");

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h1;
			Integer count = 0;

			while (rs.next()) {

				h1 = new Hashtable();

				h1.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h1.put("idpm", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h1.put("nama_Pg", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h1.put("jumlah", rs.getString("nilai_Hutang") == null ? "" : rs
						.getString("nilai_Hutang"));
				h1.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs
						.getString("no_Akaun"));
				h1.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h1.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? ""
						: rs.getString("no_Kp_Baru").substring(0, 6));
				h1.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? ""
						: rs.getString("no_Kp_Baru").substring(6, 8));
				h1.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? ""
						: rs.getString("no_Kp_Baru").substring(8, 12));
				h1.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h1.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h1.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h1.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h1.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h1.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h1.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h1.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h1.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h1.put("catatan", rs.getString("butiran_Hutang") == null ? ""
						: rs.getString("butiran_Hutang"));
				listPemiutang.addElement(h1);

			}
		} finally {
			if (db != null)
				db.close();
		}

	}

	public static void deleteWarisHubungan(String uid) throws Exception {
		Db db = null;
		// int id= uid;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Ob", uid);
			sql = r.getSQLDelete("tblppkhubunganob");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void deleteWaris(String uid) throws Exception {
		Db db = null;
		// int id= uid;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Ob", uid);
			sql = r.getSQLDelete("tblppkob");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void deletePenting(String uid) throws Exception {
		Db db = null;
		// int id= uid;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Ob", uid);
			sql = r.getSQLDelete("tblppkob");
			System.out.println("sql--delete-->>>" + sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void deletePenghutang(String uid) throws Exception {
		Db db = null;
		// int id= uid;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Penghutang", uid);
			sql = r.getSQLDelete("tblppkpenghutang");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void addHtaamX(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			// String noHakmilik=(String)data.get("noHakmilik");
			String idsimati = (String) data.get("idSimati");
			String nopt = (String) data.get("nopt");
			double nilai_Hta_memohon = (Double) data.get("nilai_Hta_memohon");
			double nilai_Hta_mati = (Double) data.get("nilai_Hta_mati");
			int kategori = (Integer) data.get("kategori");
			int jenishakmilik = (Integer) data.get("jenishakmilik");
			String pemilikan = (String) data.get("pemilikan");
			int negeri = (Integer) data.get("negeri");
			int daerah = (Integer) data.get("daerah");
			int mukim = (Integer) data.get("mukim");
			String luashmp = (String) data.get("luashmp");
			String luasasal = (String) data.get("luasasal");
			String nopajakan = (String) data.get("nopajakan");
			String jenistanah = (String) data.get("jenistanah");
			int basimati = (Integer) data.get("basimati");
			int bbsimati = (Integer) data.get("bbsimati");
			String tanggungan = (String) data.get("tanggungan");
			int jenisluas = (Integer) data.get("jenisluas");
			String catatan = ((String) data.get("catatan")).toUpperCase();
			String noperserahan = (String) data.get("noperserahan");

			String namapemaju = (String) data.get("namapemaju");
			String alamatpemaju1 = (String) data.get("alamatpemaju1");
			String alamatpemaju2 = (String) data.get("alamatpemaju2");
			String alamatpemaju3 = (String) data.get("alamatpemaju3");
			String poskodpemaju = (String) data.get("poskodpemaju");
			// String bandarpemaju=(String)data.get("bandarpemaju");
			int negeripemaju = (Integer) data.get("id_negeripemaju");
			String alamathta1 = ((String) data.get("alamathta1")).toUpperCase();
			String alamathta2 = ((String) data.get("alamathta2")).toUpperCase();
			String alamathta3 = ((String) data.get("alamathta3")).toUpperCase();
			String poskodhta = (String) data.get("poskodhta");
			String bandarhta = ((String) data.get("bandarhta")).toUpperCase();
			String noperjanjian = (String) data.get("noperjanjian");
			String tarikhperja = (String) data.get("tarikhperjanjian");
			String tarikhperjanjian = "to_date('" + tarikhperja
					+ "','dd/MM/yyyy')";
			String namarancangan = ((String) data.get("namarancangan"))
					.toUpperCase();
			String noroh = (String) data.get("noroh");
			String nolot = (String) data.get("nolot");
			String nocagaran = (String) data.get("nocagaran");
			String flag = (String) data.get("flag");
			String idpermohonansimati = (String) data.get("idpermohonansimati");
			String jeniskepentingan = (String) data.get("jeniskepentingan");
			int id_bandarhta = (Integer) data.get("id_bandarhta");
			int id_bandarpemaju = (Integer) data.get("id_bandarpemaju");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Simati", idsimati);
			r.add("no_Pt", nopt);
			if (nilai_Hta_memohon != 0.0) {
				r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			}
			if (nilai_Hta_mati != 0.0) {
				r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			}
			if (kategori != 0) {
				r.add("id_Kategori", kategori);
			}
			if (jenishakmilik != 0) {
				r.add("id_Jenishm", jenishakmilik);
			}
			r.add("status_Pemilikan", pemilikan);
			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			if (daerah != 0) {
				r.add("id_Daerah", daerah);
			}
			if (mukim != 0) {
				r.add("id_Mukim", mukim);
			}
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			if (basimati != 0) {
				r.add("ba_Simati", basimati);
			}
			if (bbsimati != 0) {
				r.add("bb_Simati", bbsimati);
			}
			r.add("tanggungan", tanggungan);
			if (jenisluas != 0) {
				r.add("id_luas", jenisluas);
			}
			r.add("catatan", catatan);
			r.add("no_Perserahan", noperserahan);
			String jenishta = "T";
			r.add("jenis_Hta", jenishta);
			r.add("nama_Pemaju", namapemaju);
			r.add("alamat_Pemaju1", alamatpemaju1);
			r.add("alamat_Pemaju2", alamatpemaju2);
			r.add("alamat_Pemaju3", alamatpemaju3);
			r.add("poskod_Pemaju", poskodpemaju);
			if (id_bandarpemaju != 0) {
				r.add("id_bandarpemaju", id_bandarpemaju);
			}
			if (negeripemaju != 0) {
				r.add("id_Negeripemaju", negeripemaju);
			}
			r.add("alamat_Hta1", alamathta1);
			r.add("alamat_Hta2", alamathta2);
			r.add("alamat_Hta3", alamathta3);
			r.add("poskod_Hta", poskodhta);
			if (id_bandarhta != 0) {
				r.add("id_bandarhta", id_bandarhta);
			}
			// r.add("bandar_Hta", bandarhta);
			r.add("no_Perjanjian", noperjanjian);
			r.add("tarikh_Perjanjian", r.unquote(tarikhperjanjian));
			r.add("nama_Rancangan", namarancangan);
			r.add("no_Roh", noroh);
			r.add("no_Lot_Id", nolot);
			r.add("no_Cagaran", nocagaran);
			r.add("flag_Kategori_Hta", flag);
			r.add("jenis_kepentingan", jeniskepentingan);
			r.add("flag_pa", jenishta);
			r.add("flag_pt", jenishta);
			r.add("flag_selesai", jenishta);
			r.add("id_permohonansimati", Integer.parseInt(idpermohonansimati));
			sql = r.getSQLInsert("tblppkhta");
			System.out.println("sssssssssss---->>>" + sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void updateHtaamX(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			String idhta = (String) data.get("idhta");
			String idsimati = (String) data.get("idSimati");
			String nopt = (String) data.get("nopt");
			double nilai_Hta_memohon = (Double) data.get("nilai_Hta_memohon");
			double nilai_Hta_mati = (Double) data.get("nilai_Hta_mati");
			int kategori = (Integer) data.get("kategori");
			int jenishakmilik = (Integer) data.get("jenishakmilik");
			String pemilikan = (String) data.get("pemilikan");
			int negeri = (Integer) data.get("negeri");
			int daerah = (Integer) data.get("daerah");
			int mukim = (Integer) data.get("mukim");
			String luashmp = (String) data.get("luashmp");
			String luasasal = (String) data.get("luasasal");
			String nopajakan = (String) data.get("nopajakan");
			String jenistanah = (String) data.get("jenistanah");
			int basimati = (Integer) data.get("basimati");
			int bbsimati = (Integer) data.get("bbsimati");
			String tanggungan = (String) data.get("tanggungan");
			int jenisluas = (Integer) data.get("jenisluas");
			String catatan = (String) data.get("catatan");
			String noperserahan = (String) data.get("noperserahan");

			String namapemaju = (String) data.get("namapemaju");
			String alamatpemaju1 = (String) data.get("alamatpemaju1");
			String alamatpemaju2 = (String) data.get("alamatpemaju2");
			String alamatpemaju3 = (String) data.get("alamatpemaju3");
			String poskodpemaju = (String) data.get("poskodpemaju");
			String bandarpemaju = (String) data.get("bandarpemaju");
			int negeripemaju = (Integer) data.get("id_negeripemaju");
			String alamathta1 = (String) data.get("alamathta1");
			String alamathta2 = (String) data.get("alamathta2");
			String alamathta3 = (String) data.get("alamathta3");
			String poskodhta = (String) data.get("poskodhta");
			String bandarhta = (String) data.get("bandarhta");
			String noperjanjian = (String) data.get("noperjanjian");
			String tarikhperja = (String) data.get("tarikhperjanjian");
			String tarikhperjanjian = "to_date('" + tarikhperja
					+ "','dd/MM/yyyy')";
			String namarancangan = (String) data.get("namarancangan");
			String noroh = (String) data.get("noroh");
			String nolot = (String) data.get("nolot");
			String nocagaran = (String) data.get("nocagaran");
			String flag = (String) data.get("flag");
			String jeniskepentingan = (String) data.get("jeniskepentingan");
			int id_bandarhta = (Integer) data.get("id_bandarhta");
			int id_bandarpemaju = (Integer) data.get("id_bandarpemaju");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_Hta", idhta);
			r.add("id_Simati", idsimati);
			r.add("no_Pt", nopt);
			if (nilai_Hta_memohon != 0.0) {
				r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			}
			if (nilai_Hta_mati != 0.0) {
				r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			}
			if (kategori != 0) {
				r.add("id_Kategori", kategori);
			}
			if (jenishakmilik != 0) {
				r.add("id_Jenishm", jenishakmilik);
			}
			r.add("status_Pemilikan", pemilikan);
			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			if (daerah != 0) {
				r.add("id_Daerah", daerah);
			}
			if (mukim != 0) {
				r.add("id_Mukim", mukim);
			}
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			if (basimati != 0) {
				r.add("ba_Simati", basimati);
			}
			if (bbsimati != 0) {
				r.add("bb_Simati", bbsimati);
			}
			r.add("tanggungan", tanggungan);
			if (jenisluas != 0) {
				r.add("id_luas", jenisluas);
			}
			r.add("catatan", catatan);
			r.add("no_Perserahan", noperserahan);
			String jenishta = "T";
			r.add("jenis_Hta", jenishta);
			r.add("nama_Pemaju", namapemaju);
			r.add("alamat_Pemaju1", alamatpemaju1);
			r.add("alamat_Pemaju2", alamatpemaju2);
			r.add("alamat_Pemaju3", alamatpemaju3);
			r.add("poskod_Pemaju", poskodpemaju);
			if (id_bandarpemaju != 0) {
				r.add("id_bandarpemaju", id_bandarpemaju);
			}
			if (negeripemaju != 0) {
				r.add("id_Negeripemaju", negeripemaju);
			}
			r.add("alamat_Hta1", alamathta1);
			r.add("alamat_Hta2", alamathta2);
			r.add("alamat_Hta3", alamathta3);
			r.add("poskod_Hta", poskodhta);
			if (id_bandarhta != 0) {
				r.add("id_bandarhta", id_bandarhta);
			}
			r.add("no_Perjanjian", noperjanjian);
			r.add("tarikh_Perjanjian", r.unquote(tarikhperjanjian));
			r.add("nama_Rancangan", namarancangan);
			r.add("no_Roh", noroh);
			r.add("no_Lot_Id", nolot);
			r.add("no_Cagaran", nocagaran);
			r.add("jenis_kepentingan", jeniskepentingan);
			sql = r.getSQLUpdate("tblppkhta");
			System.out.println("SQL htath---->>" + sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void addHtaam(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			String noHakmilik = (String) data.get("noHakmilik");
			String idsimati = (String) data.get("idSimati");
			String nopt = (String) data.get("nopt");
			double nilai_Hta_memohon = (Double) data.get("nilai_Hta_memohon");
			double nilai_Hta_mati = (Double) data.get("nilai_Hta_mati");
			int kategori = (Integer) data.get("kategori");
			int jenishakmilik = (Integer) data.get("jenishakmilik");
			String pemilikan = (String) data.get("pemilikan");
			int negeri = (Integer) data.get("negeri");
			int daerah = (Integer) data.get("daerah");
			int mukim = (Integer) data.get("mukim");
			String luashmp = (String) data.get("luashmp");
			String luasasal = (String) data.get("luasasal");
			String nopajakan = (String) data.get("nopajakan");
			String jenistanah = (String) data.get("jenistanah");
			int basimati = (Integer) data.get("basimati");
			int bbsimati = (Integer) data.get("bbsimati");
			String tanggungan = (String) data.get("tanggungan");
			int jenisluas = (Integer) data.get("jenisluas");
			String catatan = (String) data.get("catatan");
			String noperserahan = (String) data.get("noperserahan");
			
			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			String sekatan = (String) data.get("sekatan");
			String syaratNyata = (String) data.get("syaratNyata");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_Simati", idsimati);
			r.add("no_Hakmilik", noHakmilik);
			r.add("no_Pt", nopt);

			if (nilai_Hta_mati != 0.0) {
				r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			}

			if (nilai_Hta_memohon != 0.0) {
				r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			}

			if (kategori != 0) {
				r.add("id_Kategori", kategori);
			}

			if (jenishakmilik != 0) {
				r.add("id_Jenishm", jenishakmilik);
			}

			r.add("status_Pemilikan", pemilikan);

			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			if (daerah != 0) {
				r.add("id_Daerah", daerah);
			}
			if (mukim != 0) {
				r.add("id_Mukim", mukim);
			}
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			if (basimati != 0) {
				r.add("ba_Simati", basimati);
			}
			if (bbsimati != 0) {
				r.add("bb_Simati", bbsimati);
			}

			r.add("tanggungan", tanggungan);

			if (jenisluas != 0) {
				r.add("id_luas", jenisluas);
			}

			r.add("catatan", catatan);
			r.add("no_Perserahan", noperserahan);
			String jenishta = "Y";
			r.add("jenis_Hta", jenishta);
			
			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			r.add("SEKATAN", sekatan);
			r.add("SYARAT_NYATA", syaratNyata);

			sql = r.getSQLInsert("tblppkhta");
			System.out.println("SQL>>" + sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void updateHtaam(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			String noHakmilik = (String) data.get("noHakmilik");
			String idsimati = (String) data.get("idSimati");
			String nopt = (String) data.get("nopt");
			double nilai_Hta_memohon = (Double) data.get("nilai_Hta_memohon");
			double nilai_Hta_mati = (Double) data.get("nilai_Hta_mati");
			int kategori = (Integer) data.get("kategori");
			int jenishakmilik = (Integer) data.get("jenishakmilik");
			String pemilikan = (String) data.get("pemilikan");
			int negeri = (Integer) data.get("negeri");
			int daerah = (Integer) data.get("daerah");
			int mukim = (Integer) data.get("mukim");
			String luashmp = (String) data.get("luashmp");
			String luasasal = (String) data.get("luasasal");
			String nopajakan = (String) data.get("nopajakan");
			String jenistanah = (String) data.get("jenistanah");
			int basimati = (Integer) data.get("basimati");
			int bbsimati = (Integer) data.get("bbsimati");
			String tanggungan = (String) data.get("tanggungan");
			int jenisluas = (Integer) data.get("jenisluas");
			int idhta = (Integer) data.get("idhta");
			String catatan = (String) data.get("catatan");
			String noperserahan = (String) data.get("noperserahan");
			
			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			String sekatan = (String) data.get("sekatan");
			String syaratNyata = (String) data.get("syaratNyata");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_Hta", idhta);

			r.add("id_Simati", idsimati);
			r.add("no_Hakmilik", noHakmilik);
			r.add("no_Pt", nopt);

			if (nilai_Hta_mati != 0.0) {
				r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			}

			if (nilai_Hta_memohon != 0.0) {
				r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			}

			if (kategori != 0) {
				r.add("id_Kategori", kategori);
			}

			if (jenishakmilik != 0) {
				r.add("id_Jenishm", jenishakmilik);
			}

			r.add("status_Pemilikan", pemilikan);

			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			if (daerah != 0) {
				r.add("id_Daerah", daerah);
			}
			if (mukim != 0) {
				r.add("id_Mukim", mukim);
			}
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			if (basimati != 0) {
				r.add("ba_Simati", basimati);
			}
			if (bbsimati != 0) {
				r.add("bb_Simati", bbsimati);
			}

			r.add("tanggungan", tanggungan);

			if (jenisluas != 0) {
				r.add("id_luas", jenisluas);
			}

			r.add("catatan", catatan);
			r.add("no_Perserahan", noperserahan);
			String jenishta = "Y";
			r.add("jenis_Hta", jenishta);
			
			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			r.add("SEKATAN", sekatan);
			r.add("SYARAT_NYATA", syaratNyata);

			sql = r.getSQLUpdate("tblppkhta");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void deleteHtaam(String uid) throws Exception {
		Db db = null;
		// int id= uid;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Hta", uid);
			sql = r.getSQLDelete("tblppkhta");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setDataWarisLapisanIdMati(String idmati) throws Exception {
		Db db = null;
		listWarisLapisanIdMati.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");

			r.add("ob.alamat1_Surat");
			r.add("ob.alamat2_Surat");
			r.add("ob.alamat3_Surat");
			r.add("ob.bandar_Surat");
			r.add("ob.poskod_Surat");
			r.add("ob.id_NegeriSurat");

			r.add("ob.no_Tel");

			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");

			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.waktu_Kematian");
			r.add("hu.id_Parentob");
			r.add("ob.lapis");

			r.add("hu.id_hubunganob");

			r.add("hu.id_Ob", r.unquote("ob.id_Ob"));
			r.add("ob.id_Simati", idmati);

			int k = 1;
			r.add("ob.id_Tarafkptg", k);
			sql = r
					.getSQLSelect("Tblppkob ob, Tblppkhubunganob hu",
							"ob.lapis");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));

				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));

				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-"
						: formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs
						.getString("no_Hp"));

				h.put("idparent", rs.getString("id_Parentob") == null ? "" : rs
						.getString("id_Parentob"));

				h.put("lapis", rs.getString("lapis") == null ? "" : rs
						.getString("lapis"));

				h.put("alamat1Surat",
						rs.getString("alamat1_Surat") == null ? "" : rs
								.getString("alamat1_Surat"));
				h.put("alamat2Surat",
						rs.getString("alamat2_Surat") == null ? "" : rs
								.getString("alamat2_Surat"));
				h.put("alamat3Surat",
						rs.getString("alamat3_Surat") == null ? "" : rs
								.getString("alamat3_Surat"));
				h.put("bandar_Surat", rs.getString("bandar_Surat") == null ? ""
						: rs.getString("bandar_Surat"));
				h.put("idnegeriSurat",
						rs.getString("id_NegeriSurat") == null ? "" : rs
								.getString("id_NegeriSurat"));
				h.put("poskodSurat", rs.getString("poskod_Surat") == null ? ""
						: rs.getString("poskod_Surat"));

				System.out.println(h);
				listWarisLapisanIdMati.addElement(h);

			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataWarisLapisanIdMatiDelete(String idwaris)
			throws Exception {
		Db db = null;
		listWarisLapisanIdMatiDelete.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");

			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");

			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.waktu_Kematian");
			r.add("hu.id_Parentob");
			r.add("ob.lapis");

			r.add("hu.id_hubunganob");

			r.add("hu.id_Ob", r.unquote("ob.id_Ob"));

			r.add("hu.id_Parentob", idwaris);

			int k = 1;
			r.add("ob.id_Tarafkptg", k);
			sql = r
					.getSQLSelect("Tblppkob ob, Tblppkhubunganob hu",
							"ob.lapis");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));

				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));

				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-"
						: formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs
						.getString("no_Hp"));

				h.put("idparent", rs.getString("id_Parentob") == null ? "" : rs
						.getString("id_Parentob"));

				h.put("lapis", rs.getString("lapis") == null ? "" : rs
						.getString("lapis"));

				System.out.println(h);
				listWarisLapisanIdMatiDelete.addElement(h);

			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataWarisOB() throws Exception {
		Db db = null;
		listWarisOB.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.id_bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");

			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");
			r.add("ob.lapis");
			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.waktu_Kematian");

			sql = r.getSQLSelect("Tblppkob ob");
			// sql = sql+"ORDER BY ob.lapis";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("WARIS LISTTTT:::" + sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));
				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));
				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("id_bandar") == null ? "" : rs
						.getString("id_bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? ""
						: formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs
						.getString("no_Hp"));
				h.put("lapis", rs.getString("lapis") == null ? "" : rs
						.getString("lapis"));

				System.out.println(h);
				listWarisOB.addElement(h);

			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataHTA(String idsimati) throws Exception {
		Db db = null;
		listHTA.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("h.id_Hta");
			r.add("h.no_Hakmilik");
			r.add("h.id_Simati");
			r.add("h.no_Pt");
			r.add("h.nilai_Hta_Tarikhmohon");
			r.add("h.nilai_Hta_Tarikhmati");
			r.add("h.id_Kategori");
			r.add("h.id_Jenishm");
			r.add("h.id_Jenispb");
			r.add("h.id_Negeri");
			r.add("h.id_Daerah");
			r.add("h.id_Luas");
			r.add("h.id_Mukim");
			r.add("h.luas_Hmp");
			r.add("h.luas");
			r.add("h.no_Cagaran");
			r.add("h.no_Pajakan");
			r.add("h.jenis_Tnh");
			r.add("h.ba_Simati");
			r.add("h.bb_Simati");
			r.add("h.jenis_Hta");
			r.add("h.tanggungan");
			r.add("h.no_Perserahan");
			r.add("h.catatan");
			r.add("h.status_Pemilikan");

			String status = "Y";

			r.add("h.id_simati", r.unquote("s.id_simati"));
			r.add("h.id_simati", idsimati);
			r.add("h.jenis_Hta", status);
			sql = r.getSQLSelect("Tblppkhta h, Tblppksimati s");

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs
						.getString("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getString("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan",
						rs.getString("no_Perserahan") == null ? "" : rs
								.getString("no_Perserahan"));
				listHTA.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataHTAbyIdHtaam(String idhtaam) throws Exception {
		Db db = null;
		listHTAbyIdHtaam.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("h.id_Hta");
			r.add("h.no_Hakmilik");
			r.add("h.id_Simati");
			r.add("h.no_Pt");
			r.add("h.nilai_Hta_Tarikhmohon");
			r.add("h.nilai_Hta_Tarikhmati");
			r.add("h.id_Kategori");
			r.add("h.id_Jenishm");
			r.add("h.id_Jenispb");
			r.add("h.id_Negeri");
			r.add("h.id_Daerah");
			r.add("h.id_Luas");
			r.add("h.id_Mukim");
			r.add("h.luas_Hmp");
			r.add("h.luas");
			r.add("h.no_Cagaran");
			r.add("h.no_Pajakan");
			r.add("h.jenis_Tnh");
			r.add("h.ba_Simati");
			r.add("h.bb_Simati");
			r.add("h.jenis_Hta");
			r.add("h.tanggungan");
			r.add("h.no_Perserahan");
			r.add("h.catatan");
			r.add("h.status_Pemilikan");

			String status = "Y";

			r.add("h.id_simati", r.unquote("s.id_simati"));
			r.add("h.jenis_Hta", status);
			r.add("h.id_Hta", idhtaam);
			sql = r.getSQLSelect("Tblppkhta h, Tblppksimati s");

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs
						.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan",
						rs.getString("status_Pemilikan") == null ? "" : rs
								.getString("status_Pemilikan"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan",
						rs.getString("no_Perserahan") == null ? "" : rs
								.getString("no_Perserahan"));

				System.out.println(h);
				listHTAbyIdHtaam.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataHTAX(String idsimati) throws Exception {
		Db db = null;
		listHTAX.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("h.id_Hta");
			r.add("h.no_Hakmilik");
			r.add("h.id_Simati");
			r.add("h.no_Pt");
			r.add("h.nilai_Hta_Tarikhmohon");
			r.add("h.nilai_Hta_Tarikhmati");
			r.add("h.id_Kategori");
			r.add("h.id_Jenishm");
			r.add("h.id_Jenispb");
			r.add("h.id_Negeri");
			r.add("h.id_Daerah");
			r.add("h.id_Luas");
			r.add("h.id_Mukim");
			r.add("h.luas_Hmp");
			r.add("h.luas");
			r.add("h.no_Cagaran");
			r.add("h.no_Pajakan");
			r.add("h.jenis_Tnh");
			r.add("h.ba_Simati");
			r.add("h.bb_Simati");
			r.add("h.jenis_Hta");
			r.add("h.tanggungan");
			r.add("h.no_Perserahan");
			r.add("h.catatan");
			r.add("h.status_Pemilikan");
			r.add("h.no_Perjanjian");
			r.add("h.no_Roh");

			String status = "T";

			r.add("h.id_simati", r.unquote("s.id_simati"));
			r.add("h.jenis_Hta", status);
			sql = r.getSQLSelect("Tblppkhta h, Tblppksimati s");

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs
						.getString("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getString("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan",
						rs.getString("no_Perserahan") == null ? "" : rs
								.getString("no_Perserahan"));
				h.put("noperjanjian",
						rs.getString("no_Perjanjian") == null ? "" : rs
								.getString("no_Perjanjian"));

				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs
						.getString("no_Roh"));

				System.out.println(h);
				listHTAX.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataHTAXbyIdHtaam(String idhtaam) throws Exception {
		Db db = null;
		listHTAXbyIdHtaam.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("h.id_Hta");
			r.add("h.no_Hakmilik");
			r.add("h.id_Simati");
			r.add("h.no_Pt");
			r.add("h.nilai_Hta_Tarikhmohon");
			r.add("h.nilai_Hta_Tarikhmati");
			r.add("h.id_Kategori");
			r.add("h.id_Jenishm");
			r.add("h.id_Jenispb");
			r.add("h.id_Negeri");
			r.add("h.id_Daerah");
			r.add("h.id_Luas");
			r.add("h.id_Mukim");
			r.add("h.luas_Hmp");
			r.add("h.luas");
			r.add("h.no_Cagaran");
			r.add("h.no_Pajakan");
			r.add("h.jenis_Tnh");
			r.add("h.ba_Simati");
			r.add("h.bb_Simati");
			r.add("h.jenis_Hta");
			r.add("h.tanggungan");
			r.add("h.no_Perserahan");
			r.add("h.catatan");
			r.add("h.status_Pemilikan");
			r.add("h.nama_Pemaju");
			r.add("h.alamat_Pemaju1");
			r.add("h.alamat_Pemaju2");
			r.add("h.alamat_Pemaju3");
			r.add("h.poskod_Pemaju");
			r.add("h.bandar_Pemaju");
			r.add("h.id_Negeripemaju");
			r.add("h.alamat_Hta1");
			r.add("h.alamat_Hta2");
			r.add("h.alamat_Hta3");
			r.add("h.poskod_Hta");
			r.add("h.bandar_Hta");
			r.add("h.no_Perjanjian");
			r.add("h.tarikh_Perjanjian");
			r.add("h.nama_Rancangan");
			r.add("h.no_Roh");
			r.add("h.no_Lot_Id");
			r.add("h.flag_Kategori_Hta");
			r.add("h.id_bandarhta");
			r.add("h.id_bandarpemaju");
			r.add("h.jenis_kepentingan");

			String status = "T";

			r.add("h.id_simati", r.unquote("s.id_simati"));
			r.add("h.jenis_Hta", status);
			r.add("h.id_Hta", idhtaam);
			sql = r.getSQLSelect("Tblppkhta h, Tblppksimati s");

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs
						.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan",
						rs.getString("status_Pemilikan") == null ? "" : rs
								.getString("status_Pemilikan"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan",
						rs.getString("no_Perserahan") == null ? "" : rs
								.getString("no_Perserahan"));

				h.put("flag", rs.getString("flag_Kategori_Hta") == null ? ""
						: rs.getString("flag_Kategori_Hta"));
				h.put("namapemaju", rs.getString("nama_Pemaju") == null ? ""
						: rs.getString("nama_Pemaju"));
				h.put("alamatpemaju1",
						rs.getString("alamat_Pemaju1") == null ? "" : rs
								.getString("alamat_Pemaju1"));
				h.put("alamatpemaju2",
						rs.getString("alamat_Pemaju2") == null ? "" : rs
								.getString("alamat_Pemaju2"));
				h.put("alamatpemaju3",
						rs.getString("alamat_Pemaju3") == null ? "" : rs
								.getString("alamat_Pemaju3"));
				h.put("poskodpemaju",
						rs.getString("poskod_Pemaju") == null ? "" : rs
								.getString("poskod_Pemaju"));
				h.put("bandarpemaju",
						rs.getString("bandar_Pemaju") == null ? "" : rs
								.getString("bandar_Pemaju"));
				h.put("negeripemaju",
						rs.getString("id_Negeripemaju") == null ? "" : rs
								.getString("id_Negeripemaju"));
				h.put("alamathta1", rs.getString("alamat_Hta1") == null ? ""
						: rs.getString("alamat_Hta1"));
				h.put("alamathta2", rs.getString("alamat_Hta2") == null ? ""
						: rs.getString("alamat_Hta2"));
				h.put("alamathta3", rs.getString("alamat_Hta3") == null ? ""
						: rs.getString("alamat_Hta3"));
				h.put("poskodhta", rs.getString("poskod_Hta") == null ? "" : rs
						.getString("poskod_Hta"));
				h.put("bandarhta", rs.getString("bandar_Hta") == null ? "" : rs
						.getString("bandar_Hta"));
				h.put("noperjanjian",
						rs.getString("no_Perjanjian") == null ? "" : rs
								.getString("no_Perjanjian"));
				h.put("tarikhperjanjian",
						rs.getString("tarikh_Perjanjian") == null ? ""
								: formatter.format(rs
										.getDate("tarikh_Perjanjian")));
				h.put("namarancangan",
						rs.getString("nama_Rancangan") == null ? "" : rs
								.getString("nama_Rancangan"));
				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs
						.getString("no_Roh"));
				h.put("nolot", rs.getString("no_Lot_Id") == null ? "" : rs
						.getString("no_Lot_Id"));
				h.put("idbandarhta", rs.getString("id_bandarhta") == null ? ""
						: rs.getString("id_bandarhta"));
				h.put("idbandarpemaju",
						rs.getString("id_bandarpemaju") == null ? "" : rs
								.getString("id_bandarpemaju"));
				h.put("jeniskepentingan",
						rs.getString("jenis_kepentingan") == null ? "" : rs
								.getString("jenis_kepentingan"));
				listHTAXbyIdHtaam.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}

	}

	private static Vector checkWaris = null;

	public static Vector getCheckWaris() {
		return checkWaris;
	}

	@SuppressWarnings("unchecked")
	public static void setCheckWaris(String nokpbaru, String nokplama,
			String nokplain, String idpermohonan) throws Exception {

		checkWaris = new Vector();

		Db db = null;
		checkWaris.clear();
		String sql = "";
		String kpBaru = nokpbaru.trim();
		String kpLama = nokplama.trim();
		String kpLain = nokplain.trim();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT COUNT(A.ID_OB)AS cntWaris FROM TBLPPKOB A, TBLPPKPERMOHONANSIMATI B "
					+ "WHERE A.ID_PERMOHONANSIMATI=B.ID_PERMOHONANSIMATI ";

			// KP BARU
			if (!kpBaru.equals("") && !kpLama.equals("") && !kpLain.equals("")) {
				sql += "AND UPPER(A.NO_KP_BARU) LIKE '%" + kpBaru.toUpperCase()
						+ "%' " + "OR UPPER(a.NO_KP_LAMA) LIKE '%"
						+ kpLama.toUpperCase() + "%' "
						+ "OR UPPER(a.NO_KP_LAIN) LIKE '%"
						+ kpLain.toUpperCase() + "%' ";
			}

			sql += "AND B.ID_PERMOHONAN = '" + idpermohonan + "' ";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {

				h = new Hashtable();
				h.put("cntWaris", rs.getString("cntWaris") == null ? "" : rs
						.getString("cntWaris"));
				checkWaris.addElement(h);
			}

		} catch (SQLException se) {
			throw new Exception("Ralat : " + se.getMessage());
		} finally {

			if (db != null)
				db.close();
		}

	}// close setDataPegawai

}