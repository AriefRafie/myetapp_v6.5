package ekptg.helpers;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import ekptg.engine.StateLookup;
import ekptg.model.entities.Tblpdtagendamesyuarat;
import ekptg.model.entities.Tblpdtaktabab;
import ekptg.model.entities.Tblpdtaktabahagian;
import ekptg.model.entities.Tblpdtaktapenggal;
import ekptg.model.entities.Tblpdtaktaseksyen;
import ekptg.model.entities.Tblpdtenakmenbab;
import ekptg.model.entities.Tblpdtenakmenbahagian;
import ekptg.model.entities.Tblpdtenakmenseksyen;
import ekptg.model.entities.Tblpdtminitmesyuarat;
import ekptg.model.entities.Tblpdtrujdokumenpekeliling;
import ekptg.model.entities.Tblpdtrujperkarapekeliling;
import ekptg.model.entities.Tblpfdfail;
import ekptg.model.entities.Tblpfdrujfaharasat;
import ekptg.model.entities.Tblpfdrujlokasifail;
import ekptg.model.entities.Tblpfdrujlokasimesyuarat;
import ekptg.model.entities.Tblphprujdokumen;
import ekptg.model.entities.Tblphprujjenistujuan;
import ekptg.model.entities.Tblphprujtujuankaitan;
import ekptg.model.entities.Tblppkpeguam;
import ekptg.model.entities.Tblppkrujjenisperintah;
import ekptg.model.entities.Tblppkrujunit;
import ekptg.model.entities.Tblppthakmilik;
import ekptg.model.entities.Tblpptjabatanteknikal;
import ekptg.model.entities.Tblpptpihakberkepentingan;
import ekptg.model.entities.Tblrujagensi;
import ekptg.model.entities.Tblrujbandar;
import ekptg.model.entities.Tblrujbangsa;
import ekptg.model.entities.Tblrujbank;
import ekptg.model.entities.Tblrujbebanan;
import ekptg.model.entities.Tblrujbulan;
import ekptg.model.entities.Tblrujcarabayar;
import ekptg.model.entities.Tblrujdaerah;
import ekptg.model.entities.Tblrujdaerahpenggawa;
import ekptg.model.entities.Tblrujjawatan;
import ekptg.model.entities.Tblrujjenisaduan;
import ekptg.model.entities.Tblrujjenisbayaran;
import ekptg.model.entities.Tblrujjenisdokumen;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujjenisnopb;
import ekptg.model.entities.Tblrujjenispb;
import ekptg.model.entities.Tblrujjenisrizab;
import ekptg.model.entities.Tblrujjenistanah;
import ekptg.model.entities.Tblrujkategori;
import ekptg.model.entities.Tblrujkategoripemohon;
import ekptg.model.entities.Tblrujkementerian;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.entities.Tblrujluas;
import ekptg.model.entities.Tblrujmukim;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujpegawai;
import ekptg.model.entities.Tblrujpejabat;
import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.entities.Tblrujpfdtarafkeselamatan;
import ekptg.model.entities.Tblrujseksyen;
import ekptg.model.entities.Tblrujstatus;
import ekptg.model.entities.Tblrujsubkategori;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.entities.Tblrujulasanringkasan;
import ekptg.model.entities.Tblrujunit;
import ekptg.model.entities.Tblrujurusan;
import ekptg.model.entities.Tblrujwarganegara;
import ekptg.model.entities.Users;

public class DB extends EkptgCache implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 5809670899489908145L;
	static Logger myLogger = Logger.getLogger(ekptg.helpers.DB.class);

	//
	public synchronized static long getNextID(Db db, String seqName) throws Exception {
		String statecode = StateLookup.getInstance().getTitle("StateCode");
		String sql = "select " + statecode + " || to_char(sysdate,'YY') || " + seqName + ".NEXTVAL  FROM DUAL ";
		try {
			Statement stmt = db.getStatement();
			myLogger.info("TBLPPKPERMOHONANSIMATI_PK :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getLong(1);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	//
	public synchronized static long getNextID(String seqName) throws Exception {
		Db db = null;
		// original
		// String sql = "select " + seqName + ".NEXTVAL FROM DUAL ";

		// Get State code from dbconnection.properties
		String statecode = StateLookup.getInstance().getTitle("StateCode");
		String sql = "select " + statecode + " || to_char(sysdate,'YY') || " + seqName + ".NEXTVAL  FROM DUAL ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getLong(1);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public synchronized static long getNextID_DB(String seqName,Db db) throws Exception {
		//Db db = null;
		// original
		// String sql = "select " + seqName + ".NEXTVAL FROM DUAL ";

		// Get State code from dbconnection.properties
		String statecode = StateLookup.getInstance().getTitle("StateCode");
		String sql = "select " + statecode + " || to_char(sysdate,'YY') || " + seqName + ".NEXTVAL  FROM DUAL ";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getLong(1);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			//if (db != null)
			//	db.close();
		}

	}

	public static Vector<Tblrujnegeri> getNegeriExcludePelbagaiNegeri() throws Exception {
		String key = "DBgetNegeriExcludePelbagaiNegeri";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujnegeri>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "Select id_Negeri,kod_Negeri,nama_Negeri from tblrujnegeri where kod_Negeri not in ('99','00')";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
				Tblrujnegeri s = null;
				while (rs.next()) {
					s = new Tblrujnegeri();
					s.setIdNegeri(rs.getLong(1));
					s.setKodNegeri(rs.getString(2));
					s.setNamaNegeri(rs.getString(3));
					v.addElement(s);

				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}

	}

	public static Vector<Tblrujnegeri> getNegeri() throws Exception {
		String key = "DBgetNegeri";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujnegeri>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "Select id_Negeri,kod_Negeri,nama_Negeri from tblrujnegeri";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
				Tblrujnegeri s = null;
				while (rs.next()) {
					s = new Tblrujnegeri();
					s.setIdNegeri(rs.getLong(1));
					s.setKodNegeri(rs.getString(2));
					s.setNamaNegeri(rs.getString(3));
					v.addElement(s);

				}
				myLogger.info("SQL LIST NEGERI :"+sql);
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujnegeri> getNegeriPerairan() throws Exception {
		String key = "DBgetNegeri";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujnegeri>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "Select id_Negeri,kod_Negeri,nama_Negeri from tblrujnegeri where kod_negeri not in (14,16,99,00)";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
				Tblrujnegeri s = null;
				while (rs.next()) {
					s = new Tblrujnegeri();
					s.setIdNegeri(rs.getLong(1));
					s.setKodNegeri(rs.getString(2));
					s.setNamaNegeri(rs.getString(3));
					v.addElement(s);

				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujnegeri> getNegeri(String id_Negeri) throws Exception {
		String key = "DB.getNegeri" + id_Negeri;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujnegeri>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = " ";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_Negeri");
				r.add("kod_Negeri");
				r.add("nama_Negeri");

				if (id_Negeri != null)
					r.add("id_Negeri", id_Negeri);

				sql = r.getSQLSelect("tblrujnegeri");
				ResultSet rs = stmt.executeQuery(sql);

				Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
				Tblrujnegeri s = null;
				while (rs.next()) {
					s = new Tblrujnegeri();
					s.setIdNegeri(rs.getLong("id_Negeri"));
					s.setKodNegeri(rs.getString("kod_Negeri"));
					s.setNamaNegeri(rs.getString("nama_Negeri"));
					v.addElement(s);

				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}

	}

	public static Vector<Tblrujdaerah> getDaerah(String id_Daerah) throws Exception {
		String key = "DB.getDaerah" + id_Daerah;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujdaerah>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = " ";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_Daerah");
				r.add("kod_Daerah");
				r.add("nama_Daerah");

				if (id_Daerah != null)
					r.add("id_Daerah", id_Daerah);

				sql = r.getSQLSelect("tblrujdaerah");
				ResultSet rs = stmt.executeQuery(sql);

				Vector<Tblrujdaerah> v = new Vector<Tblrujdaerah>();
				Tblrujdaerah s = null;
				while (rs.next()) {
					s = new Tblrujdaerah();
					s.setIdDaerah(rs.getLong("id_Daerah"));
					s.setKodDaerah(rs.getString("kod_Daerah"));
					s.setNamaDaerah(rs.getString("nama_Daerah"));
					v.addElement(s);

				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujbandar> getBandar(String id_Bandar) throws Exception {
		String key = "DB.getBandar" + id_Bandar;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujbandar>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = " ";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_Bandar");
				r.add("kod_Bandar");
				r.add("keterangan");

				if (id_Bandar != null)
					r.add("id_Bandar", id_Bandar);

				sql = r.getSQLSelect("tblrujbandar");
				ResultSet rs = stmt.executeQuery(sql);

				Vector<Tblrujbandar> v = new Vector<Tblrujbandar>();
				Tblrujbandar s = null;
				while (rs.next()) {
					s = new Tblrujbandar();
					s.setIdBandar(rs.getLong("id_Bandar"));
					s.setKodBandar(rs.getString("kod_Bandar"));
					s.setKeterangan(rs.getString("keterangan"));
					v.addElement(s);

				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}

	}

	// * GET NEGERI FILTER BY ADA MAHKAMAH - ELLY 130909
	public static Vector<Tblrujnegeri> getNegeriByMahkamah() throws Exception {
		String key = "DB.getNegeriByMahkamah";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujnegeri>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "SELECT n.id_negeri, n.kod_negeri, n.nama_negeri FROM tblrujpejabat p, tblrujnegeri n WHERE p.id_negeri = n.id_negeri AND p.id_jenispejabat = 8 GROUP BY n.id_negeri,n.kod_negeri,n.nama_negeri ORDER BY id_negeri ASC ";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
				Tblrujnegeri s = null;
				while (rs.next()) {
					s = new Tblrujnegeri();
					s.setIdNegeri(rs.getLong(1));
					s.setKodNegeri(rs.getString(2));
					s.setNamaNegeri(rs.getString(3));
					v.addElement(s);

				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}

	}

	public static Vector<Tblrujnegeri> getNegeriByMahkamah(String id_Negeri) throws Exception {
		String key = "DB.getNegeriByMahkamah" + id_Negeri;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujnegeri>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = " ";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_Negeri");
				r.add("kod_Negeri");
				r.add("nama_Negeri");

				if (id_Negeri != null)
					r.add("id_Negeri", id_Negeri);

				sql = r.getSQLSelect("tblrujnegeri");
				ResultSet rs = stmt.executeQuery(sql);

				Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
				Tblrujnegeri s = null;
				while (rs.next()) {
					s = new Tblrujnegeri();
					s.setIdNegeri(rs.getLong("id_Negeri"));
					s.setKodNegeri(rs.getString("kod_Negeri"));
					s.setNamaNegeri(rs.getString("nama_Negeri"));
					v.addElement(s);

				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}
	// * CLOSE

	// * GET NEGERI FILTER BY ADA MAHKAMAH - ELLY 230909
	public static Vector<Tblrujnegeri> getNegeriByMahkamahSyariah() throws Exception {
		String key = "DB.getNegeriByMahkamahSyariah";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujnegeri>) cachedObject.getObjectValue();
		} else {
			Db db = null;

			String sql = "SELECT n.id_negeri, n.kod_negeri, n.nama_negeri FROM tblrujpejabat p, tblrujnegeri n WHERE p.id_negeri = n.id_negeri AND p.id_jenispejabat = 41 GROUP BY n.id_negeri,n.kod_negeri,n.nama_negeri ORDER BY id_negeri ASC ";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
				Tblrujnegeri s = null;
				while (rs.next()) {
					s = new Tblrujnegeri();
					s.setIdNegeri(rs.getLong(1));
					s.setKodNegeri(rs.getString(2));
					s.setNamaNegeri(rs.getString(3));
					v.addElement(s);

				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}

	}

	public static Vector<Tblrujnegeri> getNegeriByMahkamahSyariah(String id_Negeri) throws Exception {
		String key = "DB.getNegeriByMahkamahSyariah" + id_Negeri;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujnegeri>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_Negeri");
				r.add("kod_Negeri");
				r.add("nama_Negeri");

				if (id_Negeri != null)
					r.add("id_Negeri", id_Negeri);

				sql = r.getSQLSelect("tblrujnegeri");
				ResultSet rs = stmt.executeQuery(sql);

				Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
				Tblrujnegeri s = null;
				while (rs.next()) {
					s = new Tblrujnegeri();
					s.setIdNegeri(rs.getLong("id_Negeri"));
					s.setKodNegeri(rs.getString("kod_Negeri"));
					s.setNamaNegeri(rs.getString("nama_Negeri"));
					v.addElement(s);

				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}

	}
	// * CLOSE

	public static Vector<Tblrujkementerian> getKementerian() throws Exception {
		String key = "DB.getKementerian";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujkementerian>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "select id_kementerian,kod_kementerian,nama_kementerian from tblrujkementerian order by kod_kementerian";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujkementerian> list = new Vector<Tblrujkementerian>();
				Tblrujkementerian f = null;
				while (rs.next()) {
					f = new Tblrujkementerian();
					f.setIdNegeri(rs.getLong(1));
					f.setIdKementerian(rs.getLong(1));
					f.setKodKementerian(rs.getString(2));
					f.setNamaKementerian(rs.getString(3));
					list.addElement(f);
				}
				return list;

			} finally {
				if (db != null)
					db.close();
			}
		}

	}
	/*
	 * @author : firzan 21012010
	 */
	public static Vector<Tblrujkementerian> getKementerian(String idkementerian) throws Exception {
		String key = "DB.getKementerian" + idkementerian;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujkementerian>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "select id_kementerian,kod_kementerian,nama_kementerian from tblrujkementerian " +
					"where id_kementerian = "+ idkementerian + " order by kod_kementerian";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujkementerian> list = new Vector<Tblrujkementerian>();
				Tblrujkementerian f = null;
				while (rs.next()) {
					f = new Tblrujkementerian();
					f.setIdKementerian(rs.getLong(1));
					f.setKodKementerian(rs.getString(2));
					f.setNamaKementerian(rs.getString(3));
					list.addElement(f);
				}
				myCache.put(new Element(key, list));
				return list;

			} finally {
				if (db != null)
					db.close();
			}
		}

	}

	/*
	 * fir edit add id kementerian
	 */
	public static Vector<Tblrujagensi> getAgensi() throws Exception {
		String key = "DB.getAgensi";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujagensi>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "select id_agensi,kod_agensi,nama_agensi, id_kementerian from tblrujagensi";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujagensi> list = new Vector<Tblrujagensi>();
				Tblrujagensi f = null;
				while (rs.next()) {
					f = new Tblrujagensi();
					f.setIdAgensi(rs.getLong(1));
					f.setKodAgensi(rs.getString(2));
					f.setNamaAgensi(rs.getString(3));
					f.setIdKementerian(rs.getLong("id_kementerian"));
					list.addElement(f);
				}
				myCache.put(new Element(key, list));
				return list;

			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	/*
	 * @author : firzan 21012010
	 */
	public static Vector<Tblrujagensi> getAgensi(String idAgensi) throws Exception {
		String key = "DB.getAgensi" + idAgensi;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujagensi>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "select id_agensi,kod_agensi,nama_agensi,id_kementerian from tblrujagensi " +
					"where id_agensi = "+ idAgensi;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujagensi> list = new Vector<Tblrujagensi>();
				Tblrujagensi f = null;
				while (rs.next()) {
					f = new Tblrujagensi();
					f.setIdAgensi(rs.getLong(1));
					f.setKodAgensi(rs.getString(2));
					f.setNamaAgensi(rs.getString(3));
					f.setIdKementerian(rs.getLong("ID_KEMENTERIAN"));
					list.addElement(f);
				}
				myCache.put(new Element(key, list));
				return list;
			} finally {
				if (db != null)
					db.close();
			}
		}

	}

	public static Vector<Tblrujdaerah> getDaerah() throws Exception {
		String key = "DB.getDaerah";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujdaerah>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "Select id_Daerah,kod_Daerah,nama_Daerah from tblrujdaerah where kod_daerah <> '0'";
			Vector<Tblrujdaerah> list = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				list = new Vector<Tblrujdaerah>();
				Tblrujdaerah f = null;
				while (rs.next()) {
					f = new Tblrujdaerah();
					f.setIdDaerah(rs.getLong(1));
					f.setKodDaerah(rs.getString(2));
					f.setNamaDaerah(rs.getString(3));
					list.addElement(f);
				}
				myCache.put(new Element(key, list));
				return list;
			} finally {
				if (db != null)
					db.close();
			}
		}

	}

	public static Vector<Tblrujdaerah> getDaerahByNegeri(String idnegeri) throws Exception {
		String key = "DB.getDaerahByNegeri" + idnegeri;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujdaerah>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			Vector<Tblrujdaerah> list = null;
			String sql = "";
			if (idnegeri.equals("13")) {
				sql = "Select id_Daerah,kod_Daerah,nama_Daerah from tblrujdaerah" + " where id_negeri='" + idnegeri + "'"
						+ "and kod_Daerah not in (10,13,14,15) ORDER BY lpad(kod_Daerah,10)";

			}else {
				sql = "Select id_Daerah,kod_Daerah,nama_Daerah from tblrujdaerah" + " where id_negeri='" + idnegeri + "'"
						+ " ORDER BY lpad(kod_Daerah,10)";
			}
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				//myLogger.debug(":::::::::::::;;;test pajakan"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				list = new Vector<Tblrujdaerah>();
				Tblrujdaerah f = null;
				while (rs.next()) {
					f = new Tblrujdaerah();
					f.setIdDaerah(rs.getLong(1));
					f.setKodDaerah(rs.getString(2));
					f.setNamaDaerah(rs.getString(3));
					list.addElement(f);
				}
				myCache.put(new Element(key, list));
				return list;
			} finally {
				if (db != null)
					db.close();
			}
		}

	}

	public static Vector<Tblrujdaerah> getDaerahByNegeri_KPP(String idnegeri, String idPegawai, boolean kpp_hq) throws Exception {
		String key = "DB.getDaerahByNegeri" + idnegeri;
		Element cachedObject = null;//myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujdaerah>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			Vector<Tblrujdaerah> list = null;
			String sql = "";

			if (idnegeri.equals("13")) {
				sql = "Select id_Daerah,kod_Daerah,nama_Daerah from tblrujdaerah" + " where id_negeri='" + idnegeri + "'"
						+ "and kod_Daerah not in (10,13,14,15) ORDER BY lpad(kod_Daerah,10)";
				}
			else if ((idnegeri.equals("16")) && (kpp_hq==true)) {
				sql = "Select id_Daerah,kod_Daerah,nama_Daerah , id_negeri from tblrujdaerah where id_negeri='" + idnegeri + "'" +
						" union " +
						" Select id_Daerah,kod_Daerah,nama_Daerah, id_negeri from tblrujdaerah where id_Daerah in " +
						" (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '" + idPegawai + "')" +
						" order by id_negeri, nama_daerah";
			}else {
				sql = "Select id_Daerah,kod_Daerah,nama_Daerah from tblrujdaerah" + " where id_negeri='" + idnegeri + "'"
						+ " ORDER BY lpad(kod_Daerah,10)";
			}
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				ResultSet rs = stmt.executeQuery(sql);

				list = new Vector<Tblrujdaerah>();
				Tblrujdaerah f = null;
				while (rs.next()) {
					f = new Tblrujdaerah();
					f.setIdDaerah(rs.getLong(1));
					f.setKodDaerah(rs.getString(2));
					f.setNamaDaerah(rs.getString(3));
					list.addElement(f);
				}
				myCache.put(new Element(key, list));
				return list;
			} finally {
				if (db != null)
					db.close();
			}
		}

	}

	public static Vector<Tblrujurusan> getUrusan() throws Exception {
		String key = "DB.getUrusan";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujurusan>) cachedObject.getObjectValue();
		} else {
			Vector<Tblrujurusan> v = null;
			Db db = null;
			String sql = "SELECT id_urusan,kod_urusan,nama_urusan FROM tblrujurusan ORDER BY "+
					" nama_urusan ASC"+
//					"lpad(kod_urusan,10)"+
					"";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujurusan>();
				Tblrujurusan u = null;
				while (rs.next()) {
					u = new Tblrujurusan();
					u.setIdUrusan(rs.getLong("id_urusan"));
					u.setKodUrusan(rs.getString("kod_urusan"));
					u.setNamaUrusan(rs.getString("nama_urusan"));
					v.addElement(u);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}

	}

	public static Vector<Tblrujurusan> getUrusanPHPPenyewaan() throws Exception {
		String key = "DB.getUrusanPHPPenyewaan";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujurusan>) cachedObject.getObjectValue();
		} else {
			Vector<Tblrujurusan> v = null;
			Db db = null;
			String sql = "Select id_urusan,kod_urusan,nama_urusan from "
					+ " tblrujurusan where id_urusan in (7,12,13) order by lpad(kod_urusan,10)";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujurusan>();
				Tblrujurusan u = null;
				while (rs.next()) {
					u = new Tblrujurusan();
					u.setIdUrusan(rs.getLong("id_urusan"));
					u.setKodUrusan(rs.getString("kod_urusan"));
					u.setNamaUrusan(rs.getString("nama_urusan"));
					v.addElement(u);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujsuburusan> getUrusan(String idUrusan) throws Exception {
		String key = "DB.getUrusan" + idUrusan;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujsuburusan>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = " ";
			Vector<Tblrujsuburusan> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_urusan");
				r.add("kod_urusan");
				r.add("nama_urusan");

				if (idUrusan != null)
					r.add("id_Urusan", idUrusan);

				sql = r.getSQLSelect("tblrujurusan");
				ResultSet rs = stmt.executeQuery(sql);

				v = new Vector<Tblrujsuburusan>();
				Tblrujsuburusan s = null;
				while (rs.next()) {
					s = new Tblrujsuburusan();
					s.setIdSuburusan(rs.getLong("id_urusan"));
					s.setKodSuburusan(rs.getString("kod_urusan"));
					s.setNamaSuburusan(rs.getString("nama_urusan"));

					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujsuburusan> getSubUrusan() throws Exception {
		String key = "DB.getSubUrusan";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujsuburusan>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "Select id_Suburusan,kod_Suburusan,nama_Suburusan,id_Urusan"
					+ " from tblrujsuburusan order by	kod_Suburusan ";
			Vector<Tblrujsuburusan> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujsuburusan>();
				Tblrujsuburusan s = null;
				while (rs.next()) {
					s = new Tblrujsuburusan();
					s.setIdSuburusan(rs.getLong("id_Suburusan"));
					s.setKodSuburusan(rs.getString("kod_Suburusan"));
					s.setNamaSuburusan(rs.getString("nama_Suburusan"));
					s.setIdUrusan(rs.getLong("id_Urusan"));
					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Hashtable<String, Comparable>> getSubSubUrusan() throws Exception {
		String key = "DB.getSubSubUrusan";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Hashtable<String, Comparable>>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "Select a.id_SubSuburusan,a.kod_SubSuburusan,a.nama_SubSuburusan,b.kod_Suburusan"
					+ " from tblrujsubsuburusan a, tblrujsuburusan b where a.id_suburusan = b.id_suburusan order by b.kod_Suburusan, a.kod_SubSuburusan ";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
				Hashtable<String, Comparable> h;
				while (rs.next()) {
					h = new Hashtable<String, Comparable>();
					h.put("id_SubSuburusan", rs.getLong("id_SubSuburusan"));
					h.put("kod_SubSuburusan", rs.getString("kod_SubSuburusan"));
					h.put("nama_SubSuburusan", rs.getString("nama_SubSuburusan"));
					h.put("kod_Suburusan", rs.getString("kod_Suburusan"));

					v.addElement(h);
				}
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Hashtable<String, Comparable>> getSubSubSubUrusan() throws Exception {
		String key = "DB.getSubSubSubUrusan";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Hashtable<String, Comparable>>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "Select a.id_SubSubSuburusan,a.kod_SubSubSuburusan,a.nama_SubSubSuburusan"
					+ " from tblrujsubsubsuburusan a, tblrujsubsuburusan b where a.id_subsuburusan = b.id_subsuburusan order by a.kod_SubSubSuburusan ";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
				Hashtable<String, Comparable> h;
				while (rs.next()) {
					h = new Hashtable<String, Comparable>();
					h.put("id_SubSubSuburusan", rs.getLong("id_SubSubSuburusan"));
					h.put("kod_SubSubSuburusan", rs.getString("kod_SubSubSuburusan"));
					h.put("nama_SubSubSuburusan", rs.getString("nama_SubSubSuburusan"));

					v.addElement(h);
				}
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	/**
	 *  Altered 210609 by Mansur
	 *  Kemaskini pada 2017/03/25, buang kod yang dikomen
	 * @param idUrusan
	 * @return
	 * @throws Exception
	 */
	public static Vector<Tblrujsuburusan> getSubUrusanByUrusan(String idUrusan) throws Exception {
		String key = "DB.getSubUrusanByUrusan" + idUrusan;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujsuburusan>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = " ";
			Vector<Tblrujsuburusan> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_suburusan");
				r.add("kod_suburusan");
				r.add("nama_suburusan");
				//r.add("flag_aktif","1");
				r.add("id_urusan", Integer.parseInt(idUrusan));
				v = new Vector<Tblrujsuburusan>();
				sql = r.getSQLSelect("tblrujsuburusan", "nama_suburusan ASC");
//				sql = r.getSQLSelect("Tblrujsuburusan", "lpad(kod_Suburusan,10)");
//				System.out.println(" sql TEST SUBURUSAN EMPTY : "+sql);
				ResultSet rs = stmt.executeQuery(sql);

				Tblrujsuburusan s = null;
				while (rs.next()) {
					s = new Tblrujsuburusan();
					s.setIdSuburusan(rs.getLong("id_suburusan"));
					s.setKodSuburusan(rs.getString("kod_suburusan"));
					s.setNamaSuburusan(rs.getString("nama_suburusan"));
					v.addElement(s);

				}
				myCache.put(new Element(key, v));
				return v;

			} finally {
				if (db != null)
					db.close();
			}
		}

	}

	// end altered 210609 by mansur

	@SuppressWarnings("unchecked")
	public static Vector<Tblrujstatus> getStatusByIdSeksyen(String idSeksyen) throws Exception {
		String key = "DB.getIdStatus";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujstatus>) cachedObject.getObjectValue();

		} else {
			Db db = null;
			String sql = "";

			sql = "SELECT ID_SEKSYEN, ID_STATUS, KOD_STATUS, KETERANGAN AS NAMA_STATUS "
				+ "FROM TBLRUJSTATUS WHERE ID_SEKSYEN = 4 "
				+ "AND ID_STATUS IN (1610198, 1610201, 1610206, 1610214) ORDER BY KOD_STATUS";

			Vector<Tblrujstatus> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujstatus>();
				Tblrujstatus s = null;
				while (rs.next()) {
					s = new Tblrujstatus();
					s.setIdSeksyen(rs.getLong("ID_SEKSYEN"));
					s.setIdStatus(rs.getLong("ID_STATUS"));
					s.setKodStatus(rs.getString("KOD_STATUS"));
					s.setKeterangan(rs.getString("NAMA_STATUS"));
					v.addElement(s);
				}

				myCache.put(new Element(key, v));
				return v;

			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	// dat 17072010
	public static Vector<Tblrujsuburusan> getSubUrusanPelepasan(String idUrusan) throws Exception {
		String key = "DB.getSubUrusan";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujsuburusan>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "select id_Suburusan,kod_Suburusan,nama_Suburusan,id_Urusan"
					+ " from tblrujsuburusan where id_Urusan = '6'" + " order by	kod_Suburusan ";

			Vector<Tblrujsuburusan> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujsuburusan>();
				Tblrujsuburusan s = null;
				while (rs.next()) {
					s = new Tblrujsuburusan();
					s.setIdSuburusan(rs.getLong("id_Suburusan"));
					s.setKodSuburusan(rs.getString("kod_Suburusan"));
					s.setNamaSuburusan(rs.getString("nama_Suburusan"));
					s.setIdUrusan(rs.getLong("id_Urusan"));
					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujsuburusan> getSubUrusanPelepasanOnlineKJP(String idUrusan) throws Exception {
		String key = "DB.getSubUrusan";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujsuburusan>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "Select id_Suburusan,kod_Suburusan,nama_Suburusan,id_Urusan"
					+ " from tblrujsuburusan where id_Urusan = '6'" + " and id_Suburusan in (32,33) order by	kod_Suburusan ";

			Vector<Tblrujsuburusan> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujsuburusan>();
				Tblrujsuburusan s = null;
				while (rs.next()) {
					s = new Tblrujsuburusan();
					s.setIdSuburusan(rs.getLong("id_Suburusan"));
					s.setKodSuburusan(rs.getString("kod_Suburusan"));
					s.setNamaSuburusan(rs.getString("nama_Suburusan"));
					s.setIdUrusan(rs.getLong("id_Urusan"));
					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujmukim> getMukim() throws Exception {
		String key = "DB.getMukim";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujmukim>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "";
			Vector<Tblrujmukim> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "Select id_Mukim,kod_Mukim,nama_Mukim from Tblrujmukim where kod_Mukim <> '00' order by kod_Mukim";

				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujmukim>();
				Tblrujmukim m = null;
				while (rs.next()) {
					m = new Tblrujmukim();
					m.setIdMukim(rs.getLong("id_Mukim"));
					m.setKodMukim(rs.getString("kod_Mukim"));
					m.setNamaMukim(rs.getString("nama_Mukim"));
					v.addElement(m);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	// added by hilda 1/7/2010
	public static Vector<Hashtable<String, Comparable>> getStatusPelepasan() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT ID_STATUS,KOD_STATUS,KETERANGAN FROM"
				+ " TBLRUJSTATUS WHERE ID_SEKSYEN = 4"
				+ " AND ID_STATUS IN (1610198,1610199,1610200,1610201,1610202,1610203,1610204,1610205,1610206,1610207,1610208,1610212,1614197,999)"
				+ " ORDER BY" + " CASE ID_STATUS" + " WHEN 1610198 THEN 1" + " WHEN 1610199 THEN 2" + " WHEN 1610200 THEN 3"
				+ " WHEN 1610201 THEN 4" + " WHEN 1610202 THEN 5" + " WHEN 1610203 THEN 6" + " WHEN 1610204 THEN 7"
				+ " WHEN 1610205 THEN 8" + " WHEN 1610206 THEN 9" + " WHEN 1614197 THEN 10" + " WHEN 1610207 THEN 11"
				+ " WHEN 1610208 THEN 12" + " WHEN 1610212 THEN 13" + " WHEN 999 THEN 14" + " END";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_STATUS"));
				h.put("kod", rs.getString("KOD_STATUS") == null ? "" : rs.getString("KOD_STATUS").toUpperCase());
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// added by hilda 1/7/2010
	public static Vector<Hashtable<String, Comparable>> getStatusPenyewaan() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT ID_STATUS,KOD_STATUS,KETERANGAN FROM"
				+ " TBLRUJSTATUS WHERE ID_SEKSYEN = 4"
				+ " AND ID_STATUS IN (1610198,1610199,1610200,1610213,1610201,1610206,1610214,1610195,1610208,1610212,1610221,1610222)"
				+ " ORDER BY" + " CASE ID_STATUS" + " WHEN 1610198 THEN 1" + " WHEN 1610199 THEN 2" + " WHEN 1610200 THEN 3"
				+ " WHEN 1610213 THEN 4" + " WHEN 1610201 THEN 5" + " WHEN 1610206 THEN 6" + " WHEN 1610214 THEN 7"
				+ " WHEN 1610195 THEN 8" + " WHEN 1610221 THEN 9" + " WHEN 1610222 THEN 10" + " WHEN 1610208 THEN 11"
				+ " WHEN 1610212 THEN 12" + " END";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_STATUS"));
				h.put("kod", rs.getString("KOD_STATUS") == null ? "" : rs.getString("KOD_STATUS").toUpperCase());
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// added by dat 6/7/2010
	public static Vector<Hashtable<String, Comparable>> getStatusTukarguna() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT ID_STATUS,KOD_STATUS,KETERANGAN FROM" + " TBLRUJSTATUS WHERE ID_SEKSYEN = 4"
				+ " AND ID_STATUS IN (1610198,1610199,1610200,1610201,1610206,1610207,1610208,1610212,1610191,1610218,999)" + " ORDER BY"
				+ " CASE ID_STATUS" + " WHEN 1610198 THEN 1" + " WHEN 1610199 THEN 2" + " WHEN 1610200 THEN 3"
				+ " WHEN 1610201 THEN 4" + " WHEN 1610206 THEN 5" + " WHEN 1610191 THEN 6" + " WHEN 1610207 THEN 7"
				+ " WHEN 1610208 THEN 8" + " WHEN 1610212 THEN 9" + " WHEN 1610218 THEN 10" +  " WHEN 999 THEN 11" +" END";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_STATUS"));
				h.put("kod", rs.getString("KOD_STATUS") == null ? "" : rs.getString("KOD_STATUS").toUpperCase());
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getStatusPenawaran() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT ID_STATUS, KOD_STATUS, KETERANGAN" + " FROM TBLRUJSTATUS WHERE ID_SEKSYEN = 4"
				+ " AND ID_STATUS IN (1610198,1610199,1610200,1610210,1610201,1610206,1610207,1610208,1610212,1610218)" + " ORDER BY"
				+ " CASE ID_STATUS" + " WHEN 1610198 THEN 1" + " WHEN 1610199 THEN 2" + " WHEN 1610200 THEN 3"
				+ " WHEN 1610210 THEN 4" + " WHEN 1610201 THEN 5" + " WHEN 1610206 THEN 6" + " WHEN 1610207 THEN 7"
				+ " WHEN 1610208 THEN 8" + " WHEN 1610212 THEN 9 " + " WHEN 1610218 THEN 10" + " END";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_STATUS"));
				h.put("kod", rs.getString("KOD_STATUS") == null ? "" : rs.getString("KOD_STATUS").toUpperCase());
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getStatusPenguatkuasaan() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT ID_STATUS, KOD_STATUS, KETERANGAN" + " FROM TBLRUJSTATUS WHERE ID_SEKSYEN = 4"
				+ " AND ID_STATUS IN (1610198,1610200,1610199,1610201,1610216,1610217,1610218,1610212)" + " ORDER BY"
				+ " CASE ID_STATUS" + " WHEN 1610198 THEN 1" + " WHEN 1610200 THEN 2" + " WHEN 1610199 THEN 3"
				+ " WHEN 1610201 THEN 4" + " WHEN 1610216 THEN 5" + " WHEN 1610217 THEN 6" + " WHEN 1610218 THEN 7"
				+ " WHEN 1610212 THEN 8" + " END";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_STATUS"));
				h.put("kod", rs.getString("KOD_STATUS") == null ? "" : rs.getString("KOD_STATUS").toUpperCase());
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenishakmilik> getJenisHakmilik() throws Exception {
		String key = "DB.getJenisHakmilik";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujjenishakmilik>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "";
			Vector<Tblrujjenishakmilik> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql += " SELECT ID_JENISHAKMILIK, KOD_JENIS_HAKMILIK, KETERANGAN "
						+ " FROM TBLRUJJENISHAKMILIK "
						+ " WHERE ID_JENISHAKMILIK NOT IN ('333')"
						+ " ORDER BY LPAD (REPLACE (kod_jenis_hakmilik, '00', 'A'), 100)";
				
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("Tblrujjenishakmilik====="+sql);
				v = new Vector<Tblrujjenishakmilik>();
				Tblrujjenishakmilik j = null;
				while (rs.next()) {
					j = new Tblrujjenishakmilik();
					j.setIdJenishakmilik(rs.getLong("id_Jenishakmilik"));
					j.setKodJenisHakmilik(rs.getString("kod_Jenis_Hakmilik"));
					if (rs.getString("Keterangan") == null) {
						j.setKeterangan("");
					} else {
						j.setKeterangan(rs.getString("Keterangan"));
					}
					v.addElement(j);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	// *** Jenis HakMilik Selangor
	public static Vector<Tblrujjenishakmilik> getJenisHakmilikSelangor() throws Exception {
		String key = "DB.getJenisHakmilikSelangor";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujjenishakmilik>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "";
			Vector<Tblrujjenishakmilik> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql += "SELECT id_Jenishakmilik, kod_Jenis_Hakmilik, Keterangan  " + " FROM Tblrujjenishakmilik "
						+ " WHERE ID_JENISHAKMILIK IN (0,16,6,4,5,17,15,11,1,3,2,99,135,333) "
						+ " AND ID_JENISHAKMILIK NOT IN ('333') "
						+ " ORDER BY lpad(replace(kod_Jenis_Hakmilik,'00','A'),100)";

				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujjenishakmilik>();
				Tblrujjenishakmilik j = null;
				while (rs.next()) {
					j = new Tblrujjenishakmilik();
					j.setIdJenishakmilik(rs.getLong("id_Jenishakmilik"));
					j.setKodJenisHakmilik(rs.getString("kod_Jenis_Hakmilik"));
					if (rs.getString("Keterangan") == null) {
						j.setKeterangan("");
					} else {
						j.setKeterangan(rs.getString("Keterangan"));
					}
					v.addElement(j);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujlot> getLot() throws Exception {
		String key = "DB.getLot";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujlot>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "";
			Vector<Tblrujlot> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				// AZAM REMOVED FILTER <> 1...WHY IT'S FILTERED??
				sql = "SELECT ID_LOT, KOD_LOT, KETERANGAN FROM " + "TBLRUJLOT " +
				// "WHERE ID_LOT <> '1' " +
						"ORDER BY LPAD(KOD_LOT,10)";
				ResultSet rs = stmt.executeQuery(sql);

				v = new Vector<Tblrujlot>();
				Tblrujlot l = null;
				while (rs.next()) {
					l = new Tblrujlot();
					l.setIdLot(rs.getLong("ID_LOT"));
					l.setKodLot(rs.getString("KOD_LOT"));
					l.setKeterangan(rs.getString("KETERANGAN"));
					v.addElement(l);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujlot> getLotTanah() throws Exception {
		String key = "DB.getLot";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujlot>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "";
			Vector<Tblrujlot> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				// AZAM REMOVED FILTER <> 1...WHY IT'S FILTERED??
				sql = "SELECT DISTINCT KOD_LOT FROM V_MAKLUMATTNH_DAN_LAPORANTNH " ;
				ResultSet rs = stmt.executeQuery(sql);

				v = new Vector<Tblrujlot>();
				Tblrujlot l = null;
				while (rs.next()) {
					l = new Tblrujlot();
					l.setKodLot(rs.getString("KOD_LOT"));
					v.addElement(l);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujlot> getLot(String id_Lot) throws Exception {
		String key = "DB.getLot" + id_Lot;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujlot>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = " ";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_Lot");
				r.add("kod_Lot");
				r.add("keterangan");

				if (id_Lot != null)
					r.add("id_Lot", id_Lot);

				sql = r.getSQLSelect("tblrujlot");
				ResultSet rs = stmt.executeQuery(sql);

				Vector<Tblrujlot> v = new Vector<Tblrujlot>();
				Tblrujlot s = null;
				while (rs.next()) {
					s = new Tblrujlot();
					s.setIdLot(rs.getLong("id_Lot"));
					s.setKodLot(rs.getString("kod_Lot"));
					s.setKeterangan(rs.getString("keterangan"));

					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujmukim> getMukim(String id_Mukim) throws Exception {
		String key = "DB.getMukim" + id_Mukim;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujmukim>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = " ";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_Mukim");
				r.add("kod_Mukim");
				r.add("nama_Mukim");

				if (id_Mukim != null)
					r.add("id_Mukim", id_Mukim);

				sql = r.getSQLSelect("tblrujmukim");
				ResultSet rs = stmt.executeQuery(sql);

				Vector<Tblrujmukim> v = new Vector<Tblrujmukim>();
				Tblrujmukim s = null;
				while (rs.next()) {
					s = new Tblrujmukim();
					s.setIdMukim(rs.getLong("id_Mukim"));
					s.setKodMukim(rs.getString("kod_Mukim"));
					s.setNamaMukim(rs.getString("nama_Mukim"));

					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujjenishakmilik> getJenishakmilik(String id_Jenishakmilik) throws Exception {
		String key = "DB.getJenishakmilik" + id_Jenishakmilik;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujjenishakmilik>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = " ";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_Jenishakmilik");
				r.add("kod_Jenis_Hakmilik");
				r.add("keterangan");

				if (id_Jenishakmilik != null)
					r.add("id_Jenishakmilik", id_Jenishakmilik);

				sql = r.getSQLSelect("tblrujjenishakmilik");
				ResultSet rs = stmt.executeQuery(sql);

				Vector<Tblrujjenishakmilik> v = new Vector<Tblrujjenishakmilik>();
				Tblrujjenishakmilik s = null;
				while (rs.next()) {
					s = new Tblrujjenishakmilik();
					s.setIdJenishakmilik(rs.getLong("id_Jenishakmilik"));
					s.setKodJenisHakmilik(rs.getString("kod_Jenis_Hakmilik"));
					s.setKeterangan(rs.getString("keterangan"));

					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujluas> getLuas() throws Exception {
		String key = "DB.getLuas";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujluas>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "SELECT id_Luas, kod_Luas, Keterangan FROM Tblrujluas where id_luas NOT IN (6,9) ORDER BY kod_Luas";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujluas> v = new Vector<Tblrujluas>();
				Tblrujluas l = null;
				while (rs.next()) {
					l = new Tblrujluas();
					l.setIdLuas(rs.getLong("id_Luas"));
					l.setKodLuas(rs.getString("kod_Luas"));
					l.setKeterangan(rs.getString("Keterangan"));
					v.addElement(l);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujjenisrizab> getRizab() throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Jenisrizab");
			r.add("kod_Rizab");
			r.add("Keterangan");
			sql = r.getSQLSelect("Tblrujjenisrizab", "kod_Rizab");
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenisrizab> v = new Vector<Tblrujjenisrizab>();
			Tblrujjenisrizab j = null;
			while (rs.next()) {
				j = new Tblrujjenisrizab();
				j.setIdRizab(rs.getLong("id_Jenisrizab"));
				j.setKodRizab(rs.getString("kod_Rizab"));
				j.setKeterangan(rs.getString("Keterangan"));
				v.addElement(j);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujseksyen> getSeksyen() throws Exception {
		/* Azam change order by id_seksyen on May,13 */
		Db db = null;
		String sql = "Select id_seksyen,kod_Seksyen,nama_Seksyen" + " from tblrujseksyen order by id_seksyen ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujseksyen> v = new Vector<Tblrujseksyen>();
			Tblrujseksyen s = null;
			while (rs.next()) {
				s = new Tblrujseksyen();
				s.setIdSeksyen(rs.getLong("id_Seksyen"));
				s.setKodSeksyen(rs.getString("kod_Seksyen"));
				s.setNamaSeksyen(rs.getString("nama_Seksyen"));
				// s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujseksyen> getSeksyen(String idSeksyen) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Seksyen");
			r.add("kod_Seksyen");
			r.add("nama_Seksyen");
			// r.add("id_Urusan");

			if (idSeksyen != null)
				r.add("id_Seksyen", idSeksyen);

			sql = r.getSQLSelect("tblrujseksyen");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujseksyen> v = new Vector<Tblrujseksyen>();
			Tblrujseksyen s = null;
			while (rs.next()) {
				s = new Tblrujseksyen();
				s.setIdSeksyen(rs.getLong("id_Seksyen"));
				s.setKodSeksyen(rs.getString("kod_Seksyen"));
				s.setNamaSeksyen(rs.getString("nama_Seksyen"));
				// s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujkategori> getKategori() throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Kategori");
			r.add("kod_Kategori");
			r.add("Keterangan");
			sql = r.getSQLSelect("Tblrujkategori", "kod_Kategori");
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujkategori> v = new Vector<Tblrujkategori>();
			Tblrujkategori k = null;
			while (rs.next()) {
				k = new Tblrujkategori();
				k.setIdKategori(rs.getLong("id_Kategori"));
				k.setKodKategori(rs.getString("kod_Kategori"));
				k.setKeterangan(rs.getString("Keterangan"));
				v.addElement(k);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujpfdtarafkeselamatan> getTarafKeselamatan() throws Exception {
		Db db = null;
		String sql = "Select id_Tarafkeselamatan,kod_Taraf_Keselamatan,taraf_Keselamatan"
				+ " from tblpfdrujtarafkeselamatan order by	kod_Taraf_Keselamatan";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpfdtarafkeselamatan> v = new Vector<Tblrujpfdtarafkeselamatan>();
			Tblrujpfdtarafkeselamatan s = null;
			while (rs.next()) {
				s = new Tblrujpfdtarafkeselamatan();
				s.setIdTarafKeselamatan(rs.getLong("id_Tarafkeselamatan"));
				s.setKodTarafKeselamatan(rs.getString("kod_Taraf_Keselamatan") == null ? "" : rs
						.getString("kod_Taraf_Keselamatan"));
				s.setTarafKeselamatan(rs.getString("taraf_Keselamatan"));
				// s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujpfdtarafkeselamatan> getTarafKeselamatan(String id_Tarafkeselamatan) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Tarafkeselamatan");
			r.add("kod_Taraf_Keselamatan");
			r.add("taraf_Keselamatan");
			// r.add("id_Urusan");

			if (id_Tarafkeselamatan != null)
				r.add("id_Tarafkeselamatan", id_Tarafkeselamatan);

			sql = r.getSQLSelect("tblrujseksyen");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujpfdtarafkeselamatan> v = new Vector<Tblrujpfdtarafkeselamatan>();
			Tblrujpfdtarafkeselamatan s = null;
			while (rs.next()) {
				s = new Tblrujpfdtarafkeselamatan();
				s.setIdTarafKeselamatan(rs.getLong("id_Tarafkeselamatan"));
				s.setKodTarafKeselamatan(rs.getString("kod_Taraf_Keselamatan"));
				s.setTarafKeselamatan(rs.getString("taraf_Keselamatan"));
				// s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusFail() throws Exception {
		Db db = null;
		String sql = "Select id_Status,keterangan" + " from tblrujstatus where id_Seksyen = 6";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				// s.setTarafKeselamatan(rs.getString("taraf_Keselamatan"));
				// s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatus() throws Exception {
		Db db = null;
		String sql = "Select id_Status,keterangan" + " from tblrujstatus";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				// s.setTarafKeselamatan(rs.getString("taraf_Keselamatan"));
				// s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusFail(String id_Status) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Status");
			r.add("keterangan");
			// r.add("taraf_Keselamatan");
			// r.add("id_Urusan");

			if (id_Status != null)
				r.add("id_Status", id_Status);

			sql = r.getSQLSelect("tblrujstatus");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				// s.setTarafKeselamatan(rs.getString("taraf_Keselamatan"));
				// s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}


	public static Vector<Tblrujstatus> getStatusFailGadaian() throws Exception {
		Db db = null;
		String sql = "select distinct s.id_Status, s.keterangan from tblpfdfail f1, tblrujstatus s "+
					 "where f1.id_Urusan = 108 "+
					 "and (f1.id_status is null or f1.id_status <> '999')"+
					 "and f1.id_status = s.id_status ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}


	public static Vector<Tblrujstatus> getStatusFailHapus() throws Exception {
		Db db = null;
		String sql = "Select distinct s.id_Status, s.keterangan from tblpfdfail f1, tblrujstatus s "+
					 "where f1.id_Urusan in (1,10,5,4,3,2,108,309) "+
					 "and (f1.id_status = '999')"+
					 "and f1.id_status = s.id_status ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}


	public static Vector<Tblrujstatus> getStatusFailPerletakhakan() throws Exception {
		Db db = null;
		String sql = "Select distinct s.id_Status, s.keterangan from tblpfdfail f1, tblrujstatus s "+
					 "where f1.id_Urusan = 5 "+
					 "and (f1.id_status is null or f1.id_status <> '999')"+
					 "and f1.id_status = s.id_status ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusFailPenswastaan() throws Exception {
		Db db = null;
		String sql = "Select distinct s.id_Status, s.keterangan from tblpfdfail f1, tblrujstatus s "+
					 "where f1.id_Urusan = 4 "+
					 "and (f1.id_status is null or f1.id_status <> '999')"+
					 "and f1.id_status = s.id_status ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusFailPajakanKecil() throws Exception {
		Db db = null;
		String sql = "Select distinct s.id_Status, s.keterangan from tblpfdfail f1, tblrujstatus s "+
					 "where f1.id_Urusan = 309 "+
					 "and (f1.id_status is null or f1.id_status <> '999')"+
					 "and f1.id_status = s.id_status ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusFailPembelian() throws Exception {
		Db db = null;
		String sql = "Select distinct s.id_Status, s.keterangan from tblpfdfail f1, tblrujstatus s "+
					 "where f1.id_Urusan = 2 "+
					 "and (f1.id_status is null or f1.id_status <> '999')"+
					 "and f1.id_status = s.id_status ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}


	public static Vector<Tblrujstatus> getStatusFailPajakan() throws Exception {
		Db db = null;
		String sql = "Select distinct s.id_Status, s.keterangan from tblpfdfail f1, tblrujstatus s "+
					 "where f1.id_Urusan = 3 "+
					 "and (f1.id_status is null or f1.id_status <> '999')"+
					 "and f1.id_status = s.id_status ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}


	public static Vector<Tblrujstatus> getStatusFailPerizapan() throws Exception {
		Db db = null;
		String sql = "Select distinct s.id_Status, s.keterangan from tblpfdfail f1, tblrujstatus s "+
					 "where f1.id_Urusan = 10 "+
					 "and (f1.id_status is null or f1.id_status <> '999')"+
					 "and f1.id_status = s.id_status ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}


	public static Vector<Tblrujstatus> getStatusFailPemberimilikan() throws Exception {
		Db db = null;
		String sql = "Select distinct s.id_Status, s.keterangan from tblpfdfail f1, tblrujstatus s "+
					 "where f1.id_Urusan = 1 "+
					 "and (f1.id_status is null or f1.id_status <> '999')"+
					 "and f1.id_status = s.id_status ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusFailKeseluruhan() throws Exception {
		Db db = null;
		String sql = "Select distinct s.id_Status, s.keterangan from tblpfdfail f1, tblrujstatus s "+
					 "where f1.id_Urusan in (1,10,5,4,3,2,108,309) "+
					 "and (f1.id_status is null or f1.id_status <> '999')"+
					 "and f1.id_status = s.id_status ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}



	public static Vector<Tblpfdrujlokasifail> getLokasiFail() throws Exception {
		Db db = null;
		String sql = "Select id_Lokasifail,lokasi_Fail" + " from tblpfdrujlokasifail " + " order by id_Lokasifail asc ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpfdrujlokasifail> v = new Vector<Tblpfdrujlokasifail>();
			Tblpfdrujlokasifail s = null;
			while (rs.next()) {
				s = new Tblpfdrujlokasifail();
				s.setIdLokasifail(rs.getLong("id_Lokasifail"));
				s.setLokasiFail(rs.getString("lokasi_Fail"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpfdrujlokasifail> getLokasiFailNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "Select id_Lokasifail,lokasi_Fail" + " from tblpfdrujlokasifail where id_negeri = '" + idNegeri
				+ "' order by id_Lokasifail asc ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpfdrujlokasifail> v = new Vector<Tblpfdrujlokasifail>();
			Tblpfdrujlokasifail s = null;
			while (rs.next()) {
				s = new Tblpfdrujlokasifail();
				s.setIdLokasifail(rs.getLong("id_Lokasifail"));
				s.setLokasiFail(rs.getString("lokasi_Fail"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpfdrujlokasifail> getLokasiFail(String id_Lokasifail) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Lokasifail");
			r.add("lokasi_Fail");

			if (id_Lokasifail != null)
				r.add("id_Lokasifail", id_Lokasifail);

			sql = r.getSQLSelect("tblpfdrujlokasifail");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblpfdrujlokasifail> v = new Vector<Tblpfdrujlokasifail>();
			Tblpfdrujlokasifail s = null;
			while (rs.next()) {
				s = new Tblpfdrujlokasifail();
				s.setIdLokasifail(rs.getLong("id_Lokasifail"));
				s.setLokasiFail(rs.getString("lokasi_Fail"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpfdrujfaharasat> getFaharasat() throws Exception {
		Db db = null;
		String sql = "Select id_Faharasat,faharasat" + " from tblpfdrujfaharasat ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpfdrujfaharasat> v = new Vector<Tblpfdrujfaharasat>();
			Tblpfdrujfaharasat s = null;
			while (rs.next()) {
				s = new Tblpfdrujfaharasat();
				s.setIdFaharasat(rs.getLong("id_Faharasat"));
				s.setFaharasat(rs.getString("faharasat"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpfdrujfaharasat> getFaharasat(String id_Faharasat) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Faharasat");
			r.add("faharasat");

			if (id_Faharasat != null)
				r.add("id_Faharasat", id_Faharasat);

			sql = r.getSQLSelect("tblpfdrujfaharasat");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblpfdrujfaharasat> v = new Vector<Tblpfdrujfaharasat>();
			Tblpfdrujfaharasat s = null;
			while (rs.next()) {
				s = new Tblpfdrujfaharasat();
				s.setIdFaharasat(rs.getLong("id_Faharasat"));
				s.setFaharasat(rs.getString("faharasat"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenisdokumen> getJenisDokumen() throws Exception {
		Db db = null;
		String sql = "Select id_Jenisdokumen,kod_Jenis_Dokumen,keterangan" + " from tblrujjenisdokumen ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenisdokumen> v = new Vector<Tblrujjenisdokumen>();
			Tblrujjenisdokumen s = null;
			while (rs.next()) {
				s = new Tblrujjenisdokumen();
				s.setIdJenisdokumen(rs.getLong("id_Jenisdokumen"));
				s.setKodJenisDokumen(rs.getString("kod_Jenis_Dokumen"));
				s.setKeterangan(rs.getString("keterangan"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenisdokumen> getJenisDokumenSKPP() throws Exception {
		Db db = null;
		String sql = "Select id_Jenisdokumen,kod_Jenis_Dokumen,keterangan" + " from tblrujjenisdokumen where id_seksyen = 18 ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenisdokumen> v = new Vector<Tblrujjenisdokumen>();
			Tblrujjenisdokumen s = null;
			while (rs.next()) {
				s = new Tblrujjenisdokumen();
				s.setIdJenisdokumen(rs.getLong("id_Jenisdokumen"));
				s.setKodJenisDokumen(rs.getString("kod_Jenis_Dokumen"));
				s.setKeterangan(rs.getString("keterangan"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenisdokumen> getJenisDokumen(String id_Jenisdokumen) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Jenisdokumen");
			r.add("kod_Jenis_Dokumen");
			r.add("keterangan");

			if (id_Jenisdokumen != null)
				r.add("id_Jenisdokumen", id_Jenisdokumen);

			sql = r.getSQLSelect("tblrujjenisdokumen");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujjenisdokumen> v = new Vector<Tblrujjenisdokumen>();
			Tblrujjenisdokumen s = null;
			while (rs.next()) {
				s = new Tblrujjenisdokumen();
				s.setIdJenisdokumen(rs.getLong("id_Jenisdokumen"));
				s.setKodJenisDokumen(rs.getString("kod_Jenis_Dokumen"));
				s.setKeterangan("keterangan");

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Tblrujpejabatjkptg getPejabatJKPTG(String idSeksyen, String idNegeri, String idDaerah) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_pejabatjkptg");
			r.add("nama_pejabat");
			r.add("alamat1");
			r.add("alamat2");
			r.add("alamat3");
			r.add("poskod");
			r.add("id_negeri");
			r.add("id_daerah");
			r.add("no_tel");
			r.add("no_fax");
			r.add("id_seksyen", idSeksyen);
			r.add("id_negeri", idNegeri);
			r.add("id_daerah", idDaerah);

			sql = r.getSQLSelect("tblrujpejabatjkptg");
			myLogger.info("getPejabatJKPTG:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Tblrujpejabatjkptg s = null;
			while (rs.next()) {
				s = new Tblrujpejabatjkptg();
				s.setIdPejabatjkptg(rs.getLong("id_pejabatjkptg"));
				s.setNamaPejabat(rs.getString("nama_pejabat"));
				s.setAlamat1(rs.getString("alamat1"));
				if (rs.getString("alamat2") != null) {
					s.setAlamat2(rs.getString("alamat2"));
				} else {
					s.setAlamat2("");
				}
				if (rs.getString("alamat3") != null) {
					s.setAlamat3(rs.getString("alamat3"));
				} else {
					s.setAlamat3("");
				}
				s.setPoskod(rs.getString("poskod"));
				s.setIdNegeri(rs.getLong("id_negeri"));
				s.setIdNegeri(rs.getLong("id_daerah"));

				if (rs.getString("no_tel") != null) {
					s.setNoTel(rs.getString("no_tel"));
				} else {
					s.setNoTel("");
				}
				if (rs.getString("no_fax") != null) {
					s.setNoFax(rs.getString("no_fax"));
				} else {
					s.setNoFax("");
				}

			}
			return s;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujpegawai> getPegawai() throws Exception {
		Db db = null;
		String sql = "SELECT DISTINCT ID_PEGAWAI,NAMA_PEGAWAI FROM TBLRUJPEGAWAI ORDER BY NAMA_PEGAWAI ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpegawai> v = new Vector<Tblrujpegawai>();
			Tblrujpegawai s = null;
			while (rs.next()) {
				s = new Tblrujpegawai();
				s.setIdPegawai(rs.getLong("ID_PEGAWAI"));
				s.setNamaPegawai(rs.getString("NAMA_PEGAWAI"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, String>> getNamaPejabat(String noFail) throws Exception {
		Db db = null;
		Vector<Hashtable<String, String>> v = null;
		String sql = "";
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT ID_PEJABAT , NAMA_PEJABAT FROM TBLRUJPEJABAT PEJ, TBLPHPHAKMILIK HKMILIK, TBLPHPHAKMILIKPERMOHONAN HKMPER, TBLPERMOHONAN MOHON, TBLPFDFAIL FAIL "
					+ "WHERE PEJ.ID_NEGERI = HKMILIK.ID_NEGERI "
					+ "AND PEJ.ID_JENISPEJABAT = 25 "
					+ "AND HKMILIK.ID_HAKMILIKPERMOHONAN  = HKMPER.ID_HAKMILIKPERMOHONAN "
					+ "AND HKMPER.ID_PERMOHONAN = MOHON.ID_PERMOHONAN "
					+ "AND MOHON.ID_FAIL = FAIL.ID_FAIL "
					+ "AND FAIL.NO_FAIL = '" + noFail + "' ";
			ResultSet rs = stmt.executeQuery(sql);
			v = new Vector<Hashtable<String, String>>();
			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEJABAT", rs.getString("ID_PEJABAT") == null ? "":rs.getString("ID_PEJABAT"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "":rs.getString("NAMA_PEJABAT"));
				v.addElement(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	public static Vector<Hashtable<String, String>> getNamaPejabatByIdJenisPejabat(String idJenisPejabat, String idFail, String idPejabat) throws Exception {
		Db db = null;
		Vector<Hashtable<String, String>> v = null;
		String sql = "";
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT PEJ.NAMA_PEJABAT, HKMILIK.ID_NEGERI, PEJ.POSKOD, NEGERI.NAMA_NEGERI, DAERAH.NAMA_DAERAH, PEJ.ID_PEJABAT , "
				  + " PEJ.ALAMAT1 , PEJ.ALAMAT2 , PEJ.ALAMAT3 "
				  + "FROM TBLRUJPEJABAT PEJ, TBLPHPHAKMILIK HKMILIK, TBLPHPHAKMILIKPERMOHONAN HKMPER, TBLPERMOHONAN MOHON, TBLPFDFAIL FAIL, "
				  + "TBLRUJJENISPEJABAT JENISPEJABAT, TBLRUJNEGERI NEGERI, TBLRUJDAERAH DAERAH "
				  + "WHERE PEJ.ID_NEGERI = HKMILIK.ID_NEGERI "
				  + "AND HKMILIK.ID_HAKMILIKPERMOHONAN  = HKMPER.ID_HAKMILIKPERMOHONAN "
				  + "AND HKMPER.ID_PERMOHONAN = MOHON.ID_PERMOHONAN "
				  + "AND MOHON.ID_FAIL = FAIL.ID_FAIL "
				  + "AND PEJ.ID_JENISPEJABAT = JENISPEJABAT.ID_JENISPEJABAT "
				  + "AND NEGERI.ID_NEGERI = PEJ.ID_NEGERI "
				  + "AND DAERAH.ID_DAERAH = PEJ.ID_DAERAH ";

			if(idJenisPejabat!=null && idJenisPejabat.length()>0){
				sql = sql+" AND PEJ.ID_JENISPEJABAT="+idJenisPejabat;
			}
			if(idFail!=null && idFail.length()>0){
				sql = sql+" AND FAIL.ID_FAIL="+idFail;
			}
			if(idPejabat!=null && idPejabat.length()>0){
				sql = sql+" AND PEJ.ID_PEJABAT="+idPejabat;
			}

			ResultSet rs = stmt.executeQuery(sql);

			v = new Vector<Hashtable<String, String>>();
			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? "":rs.getString("NAMA_PEJABAT"));
				h.put("idPejabat", rs.getString("ID_PEJABAT") == null ? "":rs.getString("ID_PEJABAT"));
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "":rs.getString("ALAMAT1"));
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "":rs.getString("ALAMAT2"));
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "":rs.getString("ALAMAT3"));
				h.put("poskod", rs.getString("POSKOD") == null ? "":rs.getString("POSKOD"));
				h.put("namaDaerah", rs.getString("NAMA_DAERAH") == null ? "":rs.getString("NAMA_DAERAH"));
				h.put("namaNegeri", rs.getString("NAMA_NEGERI") == null ? "":rs.getString("NAMA_NEGERI"));

				v.addElement(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}

	public static Vector<Users> getPegawaiJKPTG(String idnegeri) throws Exception {
		Db db = null;
		String sql = " SELECT DISTINCT A.USER_ID, A.ID_JAWATAN,B.KOD_JAWATAN,B.KETERANGAN,C.USER_NAME "
				+ " FROM USERS_INTERNAL A, TBLRUJJAWATAN B, USERS C "
				+ " WHERE A.ID_JAWATAN = B.ID_JAWATAN AND C.USER_ID = A.USER_ID AND A.ID_NEGERI = " + idnegeri + " "
				+ " ORDER BY ID_JAWATAN ";

		myLogger.info("GETPEGAWAI :: " + sql);
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Users> v = new Vector<Users>();
			Users s = null;
			while (rs.next()) {
				s = new Users();
				s.setUserId(rs.getLong("user_id"));
				s.setUserName(rs.getString("user_name"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujpegawai> getPegawai(String id_Pegawai) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Pegawai");
			r.add("nama_Pegawai");

			if (id_Pegawai != null)
				r.add("id_Pegawai", id_Pegawai);

			sql = r.getSQLSelect("tblrujpegawai");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujpegawai> v = new Vector<Tblrujpegawai>();
			Tblrujpegawai s = null;
			while (rs.next()) {
				s = new Tblrujpegawai();
				s.setIdPegawai(rs.getLong("id_Pegawai"));
				s.setNamaPegawai(rs.getString("nama_Pegawai"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpfdrujlokasimesyuarat> getLokasiMesyuarat() throws Exception {
		Db db = null;
		String sql = "Select id_Lokasi,upper(lokasi) as lokasi" + " from tblpfdrujlokasimesyuarat ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpfdrujlokasimesyuarat> v = new Vector<Tblpfdrujlokasimesyuarat>();
			Tblpfdrujlokasimesyuarat s = null;
			while (rs.next()) {
				s = new Tblpfdrujlokasimesyuarat();
				s.setIdLokasi(rs.getLong("id_Lokasi"));
				s.setLokasi(rs.getString("lokasi"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpfdrujlokasimesyuarat> getLokasiMesyuaratNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "Select id_Lokasi,upper(lokasi) as lokasi" + " from tblpfdrujlokasimesyuarat where id_negeri = '" + idNegeri
				+ "' ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpfdrujlokasimesyuarat> v = new Vector<Tblpfdrujlokasimesyuarat>();
			Tblpfdrujlokasimesyuarat s = null;
			while (rs.next()) {
				s = new Tblpfdrujlokasimesyuarat();
				s.setIdLokasi(rs.getLong("id_Lokasi"));
				s.setLokasi(rs.getString("lokasi"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpfdrujlokasimesyuarat> getLokasi(String id_Lokasi) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Lokasi");
			r.add("lokasi");

			if (id_Lokasi != null)
				r.add("id_Lokasi", id_Lokasi);

			sql = r.getSQLSelect("tblpfdrujlokasimesyuarat");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblpfdrujlokasimesyuarat> v = new Vector<Tblpfdrujlokasimesyuarat>();
			Tblpfdrujlokasimesyuarat s = null;
			while (rs.next()) {
				s = new Tblpfdrujlokasimesyuarat();
				s.setIdLokasi(rs.getLong("id_Lokasi"));
				s.setLokasi(rs.getString("lokasi"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpfdfail> getFail() throws Exception {
		Db db = null;
		String sql = "Select id_Fail,no_Fail" + " from tblpfdfail ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpfdfail> v = new Vector<Tblpfdfail>();
			Tblpfdfail s = null;
			while (rs.next()) {
				s = new Tblpfdfail();
				s.setIdFail(rs.getLong("id_Fail"));
				s.setNoFail(rs.getString("no_Fail"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpfdfail> getFail(String idSeksyen) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Fail");
			r.add("no_Fail");
			r.add("id_Seksyen");

			if (idSeksyen != null)
				r.add("id_Seksyen", idSeksyen);

			sql = r.getSQLSelect("tblpfdfail");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblpfdfail> v = new Vector<Tblpfdfail>();
			Tblpfdfail s = null;
			while (rs.next()) {
				s = new Tblpfdfail();
				s.setIdFail(rs.getLong("id_Fail"));
				s.setNoFail(rs.getString("no_Fail"));
				s.setIdSeksyen(rs.getLong("id_Seksyen"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpdtagendamesyuarat> getAgenda() throws Exception {
		Db db = null;
		String sql = "Select id_Agendamesyuarat,agenda_Mesyuarat" + " from tblpfdagendamesyuarat";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpdtagendamesyuarat> v = new Vector<Tblpdtagendamesyuarat>();
			Tblpdtagendamesyuarat s = null;
			while (rs.next()) {
				s = new Tblpdtagendamesyuarat();
				s.setIdAgendamesyuarat(rs.getLong("id_Agendamesyuarat"));
				s.setAgendaMesyuarat(rs.getString("agenda_Mesyuarat"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpdtagendamesyuarat> getAgenda(String id_Mesyuarat) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Agendamesyuarat");
			r.add("agenda_Mesyuarat");

			if (id_Mesyuarat != null)
				r.add("id_Mesyuarat", id_Mesyuarat);

			sql = r.getSQLSelect("tblpfdagendamesyuarat");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblpdtagendamesyuarat> v = new Vector<Tblpdtagendamesyuarat>();
			Tblpdtagendamesyuarat s = null;
			while (rs.next()) {
				s = new Tblpdtagendamesyuarat();
				s.setIdAgendamesyuarat(rs.getLong("id_Agendamesyuarat"));
				s.setAgendaMesyuarat(rs.getString("agenda_Mesyuarat"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpdtminitmesyuarat> getPerkaraMinit() throws Exception {
		Db db = null;
		String sql = "Select id_Minitmesyuarat,tajuk_Minit" + " from tblpfdminitmesyuarat";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpdtminitmesyuarat> v = new Vector<Tblpdtminitmesyuarat>();
			Tblpdtminitmesyuarat s = null;
			while (rs.next()) {
				s = new Tblpdtminitmesyuarat();
				s.setIdMinitmesyuarat(rs.getLong("id_Minitmesyuarat"));
				s.setTajukMinit(rs.getString("tajuk_Minit"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpdtminitmesyuarat> getPerkaraMinit(String idAgenda) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Minitmesyuarat");
			r.add("tajuk_Minit");

			if (idAgenda != null)
				r.add("id_Agendamesyuarat", idAgenda);

			sql = r.getSQLSelect("tblpfdminitmesyuarat");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblpdtminitmesyuarat> v = new Vector<Tblpdtminitmesyuarat>();
			Tblpdtminitmesyuarat s = null;
			while (rs.next()) {
				s = new Tblpdtminitmesyuarat();
				s.setIdMinitmesyuarat(rs.getLong("id_Minitmesyuarat"));
				s.setTajukMinit(rs.getString("tajuk_Minit"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenisnopb> getRujJenisNoPB() throws Exception {
		Db db = null;
		String sql = "Select id_jenisnopb, kod_jenis_nopb, keterangan from "
				+ " tblrujjenisnopb where id_jenisnopb <> '9' order by lpad(id_jenisnopb,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenisnopb> v = new Vector<Tblrujjenisnopb>();
			Tblrujjenisnopb u = null;
			while (rs.next()) {
				u = new Tblrujjenisnopb();
				u.setIdJenisnopb(rs.getLong("id_jenisnopb"));
				u.setKodJenisNopb(rs.getString("kod_jenis_nopb"));
				u.setKeterangan(rs.getString("keterangan"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenisnopb> getRujJenisNoPBIndividu() throws Exception {
		Db db = null;
		String sql = "Select id_jenisnopb, kod_jenis_nopb, keterangan from "
				+ " tblrujjenisnopb where id_jenisnopb in (1,3,4,5,6,11) order by lpad(id_jenisnopb,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenisnopb> v = new Vector<Tblrujjenisnopb>();
			Tblrujjenisnopb u = null;
			while (rs.next()) {
				u = new Tblrujjenisnopb();
				u.setIdJenisnopb(rs.getLong("id_jenisnopb"));
				u.setKodJenisNopb(rs.getString("kod_jenis_nopb"));
				u.setKeterangan(rs.getString("keterangan"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenisnopb> getRujJenisNoPBIndividuLain() throws Exception {
		Db db = null;
		String sql = "Select id_jenisnopb, kod_jenis_nopb, keterangan from "
				+ " tblrujjenisnopb where id_jenisnopb in(4,5,6,7) order by lpad(id_jenisnopb,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenisnopb> v = new Vector<Tblrujjenisnopb>();
			Tblrujjenisnopb u = null;
			while (rs.next()) {
				u = new Tblrujjenisnopb();
				u.setIdJenisnopb(rs.getLong("id_jenisnopb"));
				u.setKodJenisNopb(rs.getString("kod_jenis_nopb"));
				u.setKeterangan(rs.getString("keterangan"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenisnopb> getRujJenisNoPBIndividuSyarikat() throws Exception {
		Db db = null;
		String sql = "Select id_jenisnopb, kod_jenis_nopb, keterangan from "
				+ " tblrujjenisnopb where id_jenisnopb in (1,2,3,4,5,6,11) order by lpad(id_jenisnopb,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenisnopb> v = new Vector<Tblrujjenisnopb>();
			Tblrujjenisnopb u = null;
			while (rs.next()) {
				u = new Tblrujjenisnopb();
				u.setIdJenisnopb(rs.getLong("id_jenisnopb"));
				u.setKodJenisNopb(rs.getString("kod_jenis_nopb"));
				u.setKeterangan(rs.getString("keterangan"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenisnopb> getRujJenisNoPBSyarikat() throws Exception {
		Db db = null;
		String sql = "Select id_jenisnopb, kod_jenis_nopb, keterangan from "
				+ " tblrujjenisnopb where id_jenisnopb in (2) order by lpad(id_jenisnopb,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenisnopb> v = new Vector<Tblrujjenisnopb>();
			Tblrujjenisnopb u = null;
			while (rs.next()) {
				u = new Tblrujjenisnopb();
				u.setIdJenisnopb(rs.getLong("id_jenisnopb"));
				u.setKodJenisNopb(rs.getString("kod_jenis_nopb"));
				u.setKeterangan(rs.getString("keterangan"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenisnopb> getRujJenisNoPB(String idJenisNoPB) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_jenisnopb");
			r.add("kod_jenis_nopb");
			r.add("keterangan");

			if (idJenisNoPB != null)
				r.add("id_jenisnopb", idJenisNoPB);

			sql = r.getSQLSelect("tblrujjenisnopb");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujjenisnopb> v = new Vector<Tblrujjenisnopb>();
			Tblrujjenisnopb s = null;
			while (rs.next()) {
				s = new Tblrujjenisnopb();
				s.setIdJenisnopb(rs.getLong("id_jenisnopb"));
				s.setKodJenisNopb(rs.getString("kod_jenis_nopb"));
				s.setKeterangan(rs.getString("keterangan"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	/**
	 * added by peje to cater pemohon pelepasan for syarikat, persatuan and
	 * pertubuhan
	 *
	 * @return
	 * @throws Exception
	 */
	public static Vector<Tblrujkategoripemohon> getKategoriPemohonPHPPLP() throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_Kategoripemohon, kod_Kategoripemohon, keterangan from tblrujkategoripemohon"
				+ " where id_Kategoripemohon in (2,6,9)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujkategoripemohon> v = new Vector<Tblrujkategoripemohon>();
			Tblrujkategoripemohon s = null;
			while (rs.next()) {
				s = new Tblrujkategoripemohon();
				s.setIdKategoripemohon(rs.getLong(1));
				s.setKodKategoripemohon(rs.getString(2));
				s.setKeterangan(rs.getString(3));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujkategoripemohon> getKategoriPemohonIndividuAndSyarikat() throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_Kategoripemohon, kod_Kategoripemohon, keterangan from tblrujkategoripemohon"
				+ " where id_Kategoripemohon in (1,2)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujkategoripemohon> v = new Vector<Tblrujkategoripemohon>();
			Tblrujkategoripemohon s = null;
			while (rs.next()) {
				s = new Tblrujkategoripemohon();
				s.setIdKategoripemohon(rs.getLong(1));
				s.setKodKategoripemohon(rs.getString(2));
				s.setKeterangan(rs.getString(3));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujkategoripemohon> getKategoriPemohonOrganisasiAndSyarikat() throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_Kategoripemohon, kod_Kategoripemohon, keterangan from tblrujkategoripemohon"
				+ " where id_Kategoripemohon in (10,2,9) order by keterangan desc";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujkategoripemohon> v = new Vector<Tblrujkategoripemohon>();
			Tblrujkategoripemohon s = null;
			while (rs.next()) {
				s = new Tblrujkategoripemohon();
				s.setIdKategoripemohon(rs.getLong(1));
				s.setKodKategoripemohon(rs.getString(2));
				s.setKeterangan(rs.getString(3));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujkategoripemohon> getKategoriIndividu() throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_Kategoripemohon, kod_Kategoripemohon, keterangan from tblrujkategoripemohon"
				+ " where id_Kategoripemohon = 1";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujkategoripemohon> v = new Vector<Tblrujkategoripemohon>();
			Tblrujkategoripemohon s = null;
			while (rs.next()) {
				s = new Tblrujkategoripemohon();
				s.setIdKategoripemohon(rs.getLong(1));
				s.setKodKategoripemohon(rs.getString(2));
				s.setKeterangan(rs.getString(3));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujkategoripemohon> getKategoriIndividuBukanIndividu() throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_Kategoripemohon, kod_Kategoripemohon, keterangan from tblrujkategoripemohon"
				+ " where id_Kategoripemohon in (1,11)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujkategoripemohon> v = new Vector<Tblrujkategoripemohon>();
			Tblrujkategoripemohon s = null;
			while (rs.next()) {
				s = new Tblrujkategoripemohon();
				s.setIdKategoripemohon(rs.getLong(1));
				s.setKodKategoripemohon(rs.getString(2));
				s.setKeterangan(rs.getString(3));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujkategoripemohon> getKategoriPenawaran() throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_Kategoripemohon, kod_Kategoripemohon, keterangan from tblrujkategoripemohon"
				+ " where id_Kategoripemohon in (3,8)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujkategoripemohon> v = new Vector<Tblrujkategoripemohon>();
			Tblrujkategoripemohon s = null;
			while (rs.next()) {
				s = new Tblrujkategoripemohon();
				s.setIdKategoripemohon(rs.getLong(1));
				s.setKodKategoripemohon(rs.getString(2));
				s.setKeterangan(rs.getString(3));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujkategoripemohon> getKategoriPemohonPenguatkuasa() throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_Kategoripemohon, kod_Kategoripemohon, keterangan from tblrujkategoripemohon "
				+ " where id_Kategoripemohon in (1,2,3,4,5,6,7,8,9) order by id_Kategoripemohon asc";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujkategoripemohon> v = new Vector<Tblrujkategoripemohon>();
			Tblrujkategoripemohon s = null;
			while (rs.next()) {
				s = new Tblrujkategoripemohon();
				s.setIdKategoripemohon(rs.getLong(1));
				s.setKodKategoripemohon(rs.getString(2));
				s.setKeterangan(rs.getString(3));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujpejabatjkptg> getUnitJKPTGByIdNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "select id_pejabatjkptg, nama_pejabat, alamat1, alamat2, alamat3,"
				+ " poskod, id_negeri, no_tel, no_fax from tblrujpejabatjkptg where id_seksyen = 2 and id_negeri = " + idNegeri
				+ " order by nama_pejabat";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabatjkptg> v = new Vector<Tblrujpejabatjkptg>();
			Tblrujpejabatjkptg s = null;
			while (rs.next()) {
				s = new Tblrujpejabatjkptg();
				s.setIdPejabatjkptg(rs.getLong(1));
				s.setNamaPejabat(rs.getString(2));
				s.setAlamat1(rs.getString(3));
				s.setAlamat2(rs.getString(4));
				s.setAlamat3(rs.getString(5));
				s.setPoskod(rs.getString(6));
				s.setIdNegeri(rs.getLong(7));
				s.setNoTel(rs.getString(8));
				s.setNoFax(rs.getString(9));

				v.addElement(s);

			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujpejabatjkptg> getUnitJKPTGByIdNegeriUnit(String idNegeri) throws Exception {
		Db db = null;
		String sql = "select distinct id_pejabatjkptg, nama_pejabat, alamat1, alamat2, alamat3,"
				+ " poskod, id_negeri, no_tel, no_fax from tblrujpejabatjkptg where id_seksyen <> 17 and id_jenispejabat not in (21,24) and id_negeri = "
				+ idNegeri + " order by nama_pejabat";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabatjkptg> v = new Vector<Tblrujpejabatjkptg>();
			Tblrujpejabatjkptg s = null;
			while (rs.next()) {
				s = new Tblrujpejabatjkptg();
				s.setIdPejabatjkptg(rs.getLong(1));
				s.setNamaPejabat(rs.getString(2));
				s.setAlamat1(rs.getString(3));
				s.setAlamat2(rs.getString(4));
				s.setAlamat3(rs.getString(5));
				s.setPoskod(rs.getString(6));
				s.setIdNegeri(rs.getLong(7));
				s.setNoTel(rs.getString(8));
				s.setNoFax(rs.getString(9));

				v.addElement(s);

			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujpejabat> getPejabat() throws Exception {
		Db db = null;
		String sql = "Select id_pejabat,nama_pejabat from tblrujpejabat";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabat> v = new Vector<Tblrujpejabat>();
			Tblrujpejabat s = null;
			while (rs.next()) {
				s = new Tblrujpejabat();
				s.setIdPejabat(rs.getLong(1));
				s.setNamaPejabat(rs.getString(2));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujpejabatjkptg> getPejabatJKPTG() throws Exception {
		Db db = null;
		String sql = "Select id_pejabatjkptg,nama_pejabat from tblrujpejabatjkptg";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabatjkptg> v = new Vector<Tblrujpejabatjkptg>();
			Tblrujpejabatjkptg s = null;
			while (rs.next()) {
				s = new Tblrujpejabatjkptg();
				s.setIdPejabatjkptg(rs.getLong(1));
				s.setNamaPejabat(rs.getString(2));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujpejabatjkptg> getUnitJKPTG() throws Exception {
		Db db = null;
		String sql = "Select id_pejabatjkptg,nama_pejabat,alamat1 from tblrujpejabatjkptg";
		sql += " where id_seksyen = 2 ";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabatjkptg> v = new Vector<Tblrujpejabatjkptg>();
			Tblrujpejabatjkptg s = null;
			while (rs.next()) {
				s = new Tblrujpejabatjkptg();
				s.setIdPejabatjkptg(rs.getLong(1));
				s.setNamaPejabat(Utils.isNull(rs.getString(2)));
				s.setAlamat1(Utils.isNull(rs.getString(3)));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// ADDED BY HIDAYAH
	public static Vector<Tblrujpejabatjkptg> getUnitJKPTGByIdPjbt() throws Exception {

		Db db = null;

		String sql = "Select id_pejabatjkptg,nama_pejabat,alamat1 from tblrujpejabatjkptg";
		sql += " where id_seksyen = 2";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabatjkptg> v = new Vector<Tblrujpejabatjkptg>();
			Tblrujpejabatjkptg s = null;
			while (rs.next()) {
				s = new Tblrujpejabatjkptg();
				s.setIdPejabatjkptg(rs.getLong(1));
				s.setNamaPejabat(Utils.isNull(rs.getString(2)));
				s.setAlamat1(Utils.isNull(rs.getString(3)));

				v.addElement(s);
			}

			return v;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public static Vector<Tblpdtrujdokumenpekeliling> getKategoriPekeliling(String user_role) throws Exception {
		Db db = null;

		String sql = "";

		if (user_role == "adminhtp" || user_role == "HTP" || user_role == "htpgadaian" || user_role == "HTP Gadaian"
				|| user_role == "htppembelian") {

			sql = "Select id_dokumenpekeliling,kod_jenis_dokumenpekeliling, jenis_dokumen_pekeliling from tblpdtrujdokumenpekeliling where kod_jenis_dokumenpekeliling in ('02','04') ";
		} else if (user_role == "adminskpp") {
			sql = "Select id_dokumenpekeliling,kod_jenis_dokumenpekeliling, jenis_dokumen_pekeliling from tblpdtrujdokumenpekeliling where kod_jenis_dokumenpekeliling in ('01','03','04') ";
		} else {
			// AZAM CHANGE - remove filter 04
			sql = "Select id_dokumenpekeliling,kod_jenis_dokumenpekeliling, jenis_dokumen_pekeliling "
					+ "from tblpdtrujdokumenpekeliling order by kod_jenis_dokumenpekeliling";
			// "where kod_jenis_dokumenpekeliling in ('04') ";
		}

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpdtrujdokumenpekeliling> v = new Vector<Tblpdtrujdokumenpekeliling>();
			Tblpdtrujdokumenpekeliling s = null;
			while (rs.next()) {
				s = new Tblpdtrujdokumenpekeliling();
				s.setIdDokumenpekeliling(rs.getLong(1));
				s.setKodJenisDokumenpekeliling(rs.getString(2));
				s.setJenisDokumenPekeliling(rs.getString(3));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpdtrujdokumenpekeliling> getKategoriPekeliling() throws Exception {
		Db db = null;

		String sql = "Select id_dokumenpekeliling,kod_jenis_dokumenpekeliling, jenis_dokumen_pekeliling from tblpdtrujdokumenpekeliling";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpdtrujdokumenpekeliling> v = new Vector<Tblpdtrujdokumenpekeliling>();
			Tblpdtrujdokumenpekeliling s = null;
			while (rs.next()) {
				s = new Tblpdtrujdokumenpekeliling();
				s.setIdDokumenpekeliling(rs.getLong(1));
				s.setKodJenisDokumenpekeliling(rs.getString(2));
				s.setJenisDokumenPekeliling(rs.getString(3));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpdtrujperkarapekeliling> getPerkaraPekeliling() throws Exception {
		Db db = null;
		String sql = "Select id_perkarapekeliling,kod_perkara_pekeliling, perkara_pekeliling from tblpdtrujperkarapekeliling order by id_perkarapekeliling asc";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpdtrujperkarapekeliling> v = new Vector<Tblpdtrujperkarapekeliling>();
			Tblpdtrujperkarapekeliling s = null;
			while (rs.next()) {
				s = new Tblpdtrujperkarapekeliling();
				s.setIdPerkarapekeliling(rs.getLong(1));
				s.setKodPerkaraPekeliling(rs.getString(2));
				s.setPerkaraPekeliling(rs.getString(3));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// by rosli on 30/05/2009
	public static Vector<Tblrujagensi> getAgensiByKementerian(String idKementerian) throws Exception {
		Db db = null;
		String sql = "Select id_Agensi,kod_Agensi,nama_Agensi from " + "tblrujagensi where id_kementerian= '" + idKementerian
				+ "' and kod_agensi not in ('09','10','11') order by lpad(kod_Agensi,10)"; // KOD
																							// AGENSI
																							// LAUT
																							// BERUBAH
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujagensi> list = new Vector<Tblrujagensi>();
			Tblrujagensi f = null;
			while (rs.next()) {
				f = new Tblrujagensi();
				f.setIdAgensi(rs.getLong(1));
				f.setKodAgensi(rs.getString(2));
				f.setNamaAgensi(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujbandar> getBandar() throws Exception {
		Db db = null;
		String sql = "Select id_Bandar,kod_Bandar,keterangan from tblrujbandar order by keterangan";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujbandar> list = new Vector<Tblrujbandar>();
			Tblrujbandar f = null;
			while (rs.next()) {
				f = new Tblrujbandar();
				f.setIdBandar(rs.getLong(1));
				f.setKodBandar(rs.getString(2));
				f.setKeterangan(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujbandar> getBandarByNegeri(String idnegeri) throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_Bandar,kod_Bandar,keterangan from tblrujbandar";
		sql += " where id_negeri= '" + idnegeri + "'";
		sql += " ORDER BY lpad(id_Bandar,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujbandar> list = new Vector<Tblrujbandar>();
			Tblrujbandar f = null;
			while (rs.next()) {
				f = new Tblrujbandar();
				f.setIdBandar(rs.getLong(1));
				f.setKodBandar(rs.getString(2));
				f.setKeterangan(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	/**
	 * //----------DB.java added by elly to cater status/penerangan no fail for
	 * UPT(PPT)
	 *
	 * @return
	 * @throws Exception
	 */

	public static Vector<Tblrujstatus> getStatusUPT() throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_status, kod_status, keterangan, id_seksyen from tblrujstatus where id_seksyen = 1 order by keterangan asc ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_status"));
				s.setKeterangan(rs.getString("keterangan"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusUPT(String id_status) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_status");
			r.add("keterangan");

			if (id_status != null)
				r.add("id_status", id_status);

			sql = r.getSQLSelect("Tblrujstatus");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_status"));
				s.setKeterangan(rs.getString("keterangan"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	/**
	 * //----------DB.java added by elly to cater status/penerangan no fail for
	 * PPTHakmilikSek4
	 *
	 * @return
	 * @throws Exception
	 */

	public static Vector<Tblrujstatus> getStatusHakmilik() throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_status, kod_status, keterangan, id_seksyen from tblrujstatus where id_status IN (11,16) ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_status"));
				s.setKeterangan(rs.getString("keterangan"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusHakmilik(String id_status) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_status");
			r.add("keterangan");

			if (id_status != null)
				r.add("id_status", id_status);

			sql = r.getSQLSelect("Tblrujstatus");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_status"));
				s.setKeterangan(rs.getString("keterangan"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	/**
	 * //----------DB.java added by elly to cater status/penerangan no fail for
	 * LaporanAwalTanahSenarai
	 */

	public static Vector<Tblrujstatus> getStatusLaporanAwalTanah() throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_status, kod_status, keterangan, id_seksyen from tblrujstatus where id_status IN (149,147) ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_status"));
				s.setKeterangan(rs.getString("keterangan"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusLaporanAwalTanah(String id_status) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_status");
			r.add("keterangan");

			if (id_status != null)
				r.add("id_status", id_status);

			sql = r.getSQLSelect("Tblrujstatus");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_status"));
				s.setKeterangan(rs.getString("keterangan"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujsuburusan> getSubUrusanUPT() throws Exception {
		Db db = null;
		String sql = "Select id_Suburusan,kod_Suburusan,nama_Suburusan,id_Urusan from tblrujsuburusan where id_urusan = '17' ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujsuburusan> v = new Vector<Tblrujsuburusan>();
			Tblrujsuburusan s = null;
			while (rs.next()) {
				s = new Tblrujsuburusan();
				s.setIdSuburusan(rs.getLong("id_Suburusan"));
				s.setKodSuburusan(rs.getString("kod_Suburusan"));
				s.setNamaSuburusan(rs.getString("nama_Suburusan"));
				s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujsuburusan> getSubUrusanUPT(String idUrusan) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Suburusan");
			r.add("kod_Suburusan");
			r.add("nama_Suburusan");
			r.add("id_Urusan");

			if (idUrusan != null)
				r.add("id_Urusan", idUrusan);

			sql = r.getSQLSelect("tblrujsuburusan");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujsuburusan> v = new Vector<Tblrujsuburusan>();
			Tblrujsuburusan s = null;
			while (rs.next()) {
				s = new Tblrujsuburusan();
				s.setIdSuburusan(rs.getLong("id_Suburusan"));
				s.setKodSuburusan(rs.getString("kod_Suburusan"));
				s.setNamaSuburusan(rs.getString("nama_Suburusan"));
				s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// --->> added by elly
	public static Vector<Tblrujwarganegara> getWarganegara() throws Exception {
		Db db = null;
		String sql = "Select id_Warganegara,kod_Warga,keterangan from tblrujwarganegara order by keterangan asc";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujwarganegara> list = new Vector<Tblrujwarganegara>();
			Tblrujwarganegara f = null;
			while (rs.next()) {
				f = new Tblrujwarganegara();
				f.setIdWarganegara(rs.getLong(1));
				f.setKodWarga(rs.getString(2));
				f.setKeterangan(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujwarganegara> getWarganegara(String IdWarganegara) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_warganegara");
			r.add("kod_warga");
			r.add("keterangan");

			if (IdWarganegara != null)
				r.add("id_warganegara", IdWarganegara);

			sql = r.getSQLSelect("tblrujwarganegara");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujwarganegara> v = new Vector<Tblrujwarganegara>();
			Tblrujwarganegara s = null;
			while (rs.next()) {
				s = new Tblrujwarganegara();
				s.setIdWarganegara(rs.getLong("id_warganegara"));
				s.setKodWarga(rs.getString("kod_warga"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// **added by elly
	public static Vector<Tblrujbangsa> getBangsa() throws Exception {
		Db db = null;
		String sql = "Select id_bangsa,kod_bangsa,keterangan from tblrujbangsa where id_bangsa in (1,2,3,7) order by id_bangsa";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujbangsa> list = new Vector<Tblrujbangsa>();
			Tblrujbangsa f = null;
			while (rs.next()) {
				f = new Tblrujbangsa();
				f.setIdBangsa(rs.getLong(1));
				f.setKodBangsa(rs.getString(2));
				f.setKeterangan(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujbangsa> getBangsa(String IdBangsa) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_bangsa");
			r.add("kod_bangsa");
			r.add("keterangan");

			if (IdBangsa != null)
				r.add("id_bangsa", IdBangsa);

			sql = r.getSQLSelect("tblrujbangsa");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujbangsa> v = new Vector<Tblrujbangsa>();
			Tblrujbangsa s = null;
			while (rs.next()) {
				s = new Tblrujbangsa();
				s.setIdBangsa(rs.getLong("id_bangsa"));
				s.setKodBangsa(rs.getString("kod_bangsa"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// **added by elly for No PB dlm Hakmilik UPT
	public static Vector<Tblrujjenispb> getRujKodJenisPB() throws Exception {
		Db db = null;
		String sql = "Select id_jenispb, kod_jenis_pb, keterangan from " + " tblrujjenispb where id_jenispb not in (40,41,42) "
				+ " order by lpad(id_jenispb,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenispb> v = new Vector<Tblrujjenispb>();
			Tblrujjenispb u = null;
			while (rs.next()) {
				u = new Tblrujjenispb();
				u.setIdJenispb(rs.getLong("id_jenispb"));
				u.setKodJenisPb(rs.getString("kod_jenis_pb"));
				u.setKeterangan(rs.getString("keterangan"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenispb> getRujKodJenisPB(String idJenisPB) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_jenispb");
			r.add("kod_jenis_pb");
			r.add("keterangan");

			if (idJenisPB != null)
				r.add("id_jenispb", idJenisPB);

			sql = r.getSQLSelect("tblrujjenispb");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujjenispb> v = new Vector<Tblrujjenispb>();
			Tblrujjenispb s = null;
			while (rs.next()) {
				s = new Tblrujjenispb();
				s.setIdJenispb(rs.getLong("id_jenispb"));
				s.setKodJenisPb(rs.getString("kod_jenis_pb"));
				s.setKeterangan(rs.getString("keterangan"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblphprujdokumen> getPHPRujDokumen() throws Exception {
		Db db = null;
		String sql = "Select id_dokumen,kod_dokumen, keterangan from tblphprujdokumen where id_dokumen in (1,4)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblphprujdokumen> v = new Vector<Tblphprujdokumen>();
			Tblphprujdokumen s = null;
			while (rs.next()) {
				s = new Tblphprujdokumen();
				s.setIdDokumen(rs.getLong(1));
				s.setKodDokumen(rs.getString(2));
				s.setKeterangan(rs.getString(3));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblphprujdokumen> getPHPRujDokumenNotisPeringatan() throws Exception {
		Db db = null;
		String sql = "Select id_dokumen,kod_dokumen, keterangan from tblphprujdokumen " + " where id_dokumen in (6,9)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblphprujdokumen> v = new Vector<Tblphprujdokumen>();
			Tblphprujdokumen s = null;
			while (rs.next()) {
				s = new Tblphprujdokumen();
				s.setIdDokumen(rs.getLong(1));
				s.setKodDokumen(rs.getString(2));
				s.setKeterangan(rs.getString(3));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblphprujdokumen> getPHPRujDokumenPanggilMesyuarat() throws Exception {
		Db db = null;
		String sql = "Select id_dokumen,kod_dokumen, keterangan from tblphprujdokumen " + " where id_dokumen in (13,14)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblphprujdokumen> v = new Vector<Tblphprujdokumen>();
			Tblphprujdokumen s = null;
			while (rs.next()) {
				s = new Tblphprujdokumen();
				s.setIdDokumen(rs.getLong(1));
				s.setKodDokumen(rs.getString(2));
				s.setKeterangan(rs.getString(3));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}


	public static Vector<Tblphprujjenistujuan> getPHPRujJenisTujuan() throws Exception {
		Db db = null;
		String sql = "Select id_jenistujuan, upper(kod_jenistujuan) as kod_jenistujuan, upper(keterangan) as keterangan from tblphprujjenistujuan order by id_jenistujuan asc";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblphprujjenistujuan> v = new Vector<Tblphprujjenistujuan>();
			Tblphprujjenistujuan s = null;
			while (rs.next()) {
				s = new Tblphprujjenistujuan();
				s.setIdJenistujuan(rs.getLong(1));
				s.setKodJenistujuan(rs.getString(2));
				s.setKeterangan(rs.getString(3));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblphprujtujuankaitan> getPHPRujTujuanKaitan() throws Exception {
		Db db = null;
		String sql = "Select id_Tujuankaitan, upper(kod_Tujuankaitan) as kod_Tujuankaitan, upper(keterangan) as keterangan from tblphprujtujuankaitan order by id_Tujuankaitan asc";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblphprujtujuankaitan> v = new Vector<Tblphprujtujuankaitan>();
			Tblphprujtujuankaitan s = null;
			while (rs.next()) {
				s = new Tblphprujtujuankaitan();
				s.setIdTujuankaitan(rs.getLong(1));
				s.setKodTujuankaitan(rs.getString(2));
				s.setKeterangan(rs.getString(3));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjawatan> getJawatan() throws Exception {
		Db db = null;
		String sql = "Select ID_JAWATAN, KOD_JAWATAN, KETERANGAN from tblrujjawatan order by lpad(KOD_JAWATAN, 10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjawatan> v = new Vector<Tblrujjawatan>();
			Tblrujjawatan j = null;
			while (rs.next()) {
				j = new Tblrujjawatan();
				j.setIdJawatan(rs.getLong(1));
				j.setKodJawatan(rs.getString(2));
				j.setKeterangan(rs.getString(3));

				v.addElement(j);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjawatan> getJawatanByIdJawatan(String id_Jawatan) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_JAWATAN");
			r.add("KOD_JAWATAN");
			r.add("KETERANGAN");

			if (id_Jawatan != null)
				r.add("ID_JAWATAN", id_Jawatan);

			sql = r.getSQLSelect("tblrujjawatan");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujjawatan> v = new Vector<Tblrujjawatan>();
			Tblrujjawatan s = null;
			while (rs.next()) {
				s = new Tblrujjawatan();
				s.setIdJawatan(rs.getLong("ID_JAWATAN"));
				s.setKodJawatan(rs.getString("KOD_JAWATAN"));
				s.setKeterangan("KETERANGAN");

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujmukim> getMukimByDaerah(String iddaerah) throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_Mukim,kod_Mukim,nama_Mukim from tblrujmukim" + " where id_daerah='" + iddaerah
				+ "' ORDER BY lpad(kod_Mukim,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujmukim> list = new Vector<Tblrujmukim>();
			Tblrujmukim f = null;
			while (rs.next()) {
				f = new Tblrujmukim();
				f.setIdMukim(rs.getLong(1));
				f.setKodMukim(rs.getString(2));
				f.setNamaMukim(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}// close get mukim by daerah

	public static Vector<Tblrujmukim> getMukimByNegeri(String idnegeri) throws Exception {
		Db db = null;
		String sql = "";
		// sql = "Select id_Mukim,kod_Mukim,nama_Mukim from tblrujmukim" +
		// " where id_negeri= '"+idnegeri+"' ORDER BY lpad(id_Mukim,10)";
		sql = "Select RM.id_Mukim,RM.kod_Mukim,RM.nama_Mukim,RD.ID_DAERAH " + " from tblrujmukim RM,TBLRUJDAERAH RD "
				+ " WHERE RD.ID_DAERAH=RM.ID_DAERAH " + " AND RM.KOD_MUKIM <> '00' " + " AND RD.id_negeri=" + idnegeri + "  "
				+ " ORDER BY RD.KOD_DAERAH,RM.ID_Mukim";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujmukim> list = new Vector<Tblrujmukim>();
			Tblrujmukim f = null;
			while (rs.next()) {
				f = new Tblrujmukim();
				f.setIdMukim(rs.getLong(1));
				f.setKodMukim(rs.getString(2));
				f.setNamaMukim(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}// close get mukim by negeri

	public static Tblrujsuburusan getSubUrusan(String idSuburusan) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Suburusan");
			r.add("kod_Suburusan");
			r.add("nama_Suburusan");
			r.add("id_Urusan");

			if (idSuburusan != null)
				r.add("id_Suburusan", idSuburusan);

			sql = r.getSQLSelect("tblrujsuburusan");
			ResultSet rs = stmt.executeQuery(sql);
			// Vector v = new Vector();
			Tblrujsuburusan s = null;
			while (rs.next()) {
				s = new Tblrujsuburusan();
				s.setIdSuburusan(rs.getLong("id_Suburusan"));
				s.setKodSuburusan(rs.getString("kod_Suburusan"));
				s.setNamaSuburusan(rs.getString("nama_Suburusan"));
				s.setIdUrusan(rs.getLong("id_Urusan"));
				// v.addElement(s);
			}
			return s;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// **getJenisTanah by Elly
	public static Vector<Tblrujjenistanah> getJenisTanah() throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_JenisTanah");
			r.add("kod_Jenis_Tanah");
			r.add("Keterangan");
			sql = r.getSQLSelect("Tblrujjenistanah", "kod_Jenis_Tanah");
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenistanah> v = new Vector<Tblrujjenistanah>();
			Tblrujjenistanah l = null;
			while (rs.next()) {
				l = new Tblrujjenistanah();
				l.setIdJenistanah(rs.getLong("id_JenisTanah"));
				l.setKodJenistanah(rs.getString("kod_Jenis_Tanah"));
				l.setKeterangan(rs.getString("Keterangan"));
				v.addElement(l);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	/**
	 * //----------DB.java added by elly to cater for Maklumat Jabatan Teknikal
	 */

	public static Vector<Tblrujstatus> getStatusJabatanTeknikal() throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_status, kod_status, keterangan, id_seksyen from tblrujstatus where id_status IN (22,147) ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_status"));
				s.setKeterangan(rs.getString("keterangan"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusJabatanTeknikal(String id_status) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_status");
			r.add("keterangan");

			if (id_status != null)
				r.add("id_status", id_status);

			sql = r.getSQLSelect("Tblrujstatus");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_status"));
				s.setKeterangan(rs.getString("keterangan"));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// ** getPegawaiPengendali from Tblppkrujunit
	// ** by elly (080709)
	public static Vector<Tblppkrujunit> getPegawaiPengendali() throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Unitpsk");
			r.add("kod");
			r.add("Nama_Pegawai");
			r.add("status_peg", "1");
			sql = r.getSQLSelect("Tblppkrujunit", "kod");
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblppkrujunit> v = new Vector<Tblppkrujunit>();
			Tblppkrujunit l = null;
			while (rs.next()) {
				l = new Tblppkrujunit();
				l.setidunitpk(rs.getLong("id_Unitpsk"));
				l.setkod(rs.getString("kod"));
				l.setnamapegawai(rs.getString("Nama_Pegawai"));
				v.addElement(l);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// ** getMahkamah from Tblrujpejabat
	// ** by elly (120709)

	public static Vector<Tblrujpejabat> getMahkamah() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT id_pejabat,id_jenispejabat,nama_pejabat,kod_pejabat,id_negeri FROM Tblrujpejabat WHERE id_jenispejabat = 08 ORDER BY id_pejabat";
		myLogger.info("getMahkamah >> " + sql);
		// Db db = null;
		// String sql = "";
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabat> v = new Vector<Tblrujpejabat>();
			Tblrujpejabat s = null;

			while (rs.next()) {
				s = new Tblrujpejabat();
				s.setIdPejabat(rs.getLong("id_pejabat"));
				s.setJenisPejabat(rs.getString("id_jenispejabat"));
				s.setNamaPejabat(rs.getString("nama_pejabat"));
				s.setKodPejabat(rs.getString("kod_pejabat"));
				s.setIdNegeri(rs.getLong("id_negeri"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// ** getTempatBicara from Tblppkrujunit
	// ** by elly (120709)

	public static Vector<Tblppkrujunit> getTempatBicara() throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Unitpsk");
			r.add("kod");
			r.add("Nama_Pejabat");
			r.add("keterangan_unit_psk");
			sql = r.getSQLSelect("Tblppkrujunit", "kod");
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblppkrujunit> v = new Vector<Tblppkrujunit>();
			Tblppkrujunit l = null;
			while (rs.next()) {
				l = new Tblppkrujunit();
				l.setidunitpk(rs.getLong("id_Unitpsk"));
				l.setkod(rs.getString("kod"));
				l.setnamapejabat(rs.getString("Nama_Pejabat"));
				l.setketeranganunitpsk(rs.getString("keterangan_unit_psk"));
				v.addElement(l);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenisaduan> getJenisAduan() throws Exception {

		Db db = null;
		String sql = "Select ID_JENISADUAN, KOD_JENIS_ADUAN, JENIS_ADUAN from TBLRUJJENISADUAN order by ID_JENISADUAN asc";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenisaduan> v = new Vector<Tblrujjenisaduan>();
			Tblrujjenisaduan j = null;
			while (rs.next()) {
				j = new Tblrujjenisaduan();
				j.setIdJenisaduan(rs.getLong(1));
				j.setKodJenisAduan(rs.getString(2));
				j.setJenisAduan(rs.getString(3));

				v.addElement(j);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujulasanringkasan> getUlasanRingkasan() throws Exception {
		Db db = null;
		String sql = "Select id_ulasanringkasan,kod_ulasanringkasan,keterangan from "
				+ " tblrujulasanringkasan order by lpad(kod_ulasanringkasan,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujulasanringkasan> v = new Vector<Tblrujulasanringkasan>();
			Tblrujulasanringkasan u = null;
			while (rs.next()) {
				u = new Tblrujulasanringkasan();
				u.setIdUlasanringkasan(rs.getLong("id_ulasanringkasan"));
				u.setKodUlasanringkasan(rs.getString("kod_ulasanringkasan"));
				u.setKeterangan(rs.getString("keterangan"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpdtaktapenggal> getPenggal(int idAkta) throws Exception {
		Db db = null;
		String sql = "Select id_aktapenggal,no_penggal,tajuk_penggal from " + " tblpdtaktapenggal where id_akta = " + idAkta
				+ "order by id_aktapenggal";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpdtaktapenggal> v = new Vector<Tblpdtaktapenggal>();
			Tblpdtaktapenggal u = null;
			while (rs.next()) {
				u = new Tblpdtaktapenggal();
				u.setIdAktapenggal(rs.getLong("id_aktapenggal"));
				u.setNoPenggal(rs.getString("no_penggal"));
				u.setTajukPenggal(rs.getString("tajuk_penggal"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getPenggalEnakmen(int idEnakmen) throws Exception {
		Db db = null;
		String sql = "Select id_enakmenpenggal,no_penggal,tajuk_penggal from " + " tblpdtenakmenpenggal where id_enakmen = "
				+ idEnakmen + " order by id_enakmenpenggal";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();

			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id_enakmenpenggal", rs.getLong("id_enakmenpenggal"));
				h.put("tajuk_penggal", rs.getString("tajuk_penggal"));
				;
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getSubSeksyenEnakmen(int idEnakmen) throws Exception {
		Db db = null;
		String sql = "Select id_enakmensubseksyen,sub_seksyen from " + " tblpdtenakmensubseksyen where id_enakmen = " + idEnakmen
				+ " order by id_enakmensubseksyen";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();

			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id_enakmensubseksyen", rs.getLong("id_enakmensubseksyen"));
				h.put("sub_seksyen", rs.getString("sub_seksyen"));
				;
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getParaEnakmen(int idEnakmen) throws Exception {
		Db db = null;
		String sql = "Select id_enakmenpara,para from " + " tblpdtenakmenpara where id_enakmen = " + idEnakmen
				+ " order by id_enakmenpara";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();

			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id_enakmenpara", rs.getLong("id_enakmenpara"));
				h.put("para", rs.getString("para"));
				;
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getJadualEnakmen(int idEnakmen) throws Exception {
		Db db = null;
		String sql = "Select id_jadual,no_jadual from " + " tblpdtjadual where id_enakmen = " + idEnakmen + " order by id_jadual";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();

			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id_jadual", rs.getLong("id_jadual"));
				h.put("no_jadual", rs.getString("no_jadual"));
				;
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getParaJadualParaEnakmen(int idEnakmen) throws Exception {
		Db db = null;
		String sql = "Select id_jadualpara,para from " + " tblpdtjadualpara where id_enakmen = " + idEnakmen
				+ " order by id_jadualpara";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();

			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id_jadualpara", rs.getLong("id_jadualpara"));
				h.put("para", rs.getString("para"));
				;
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getSubParaJadualParaEnakmen(int idEnakmen) throws Exception {
		Db db = null;
		String sql = "Select id_jadualsubpara,sub_para from " + " tblpdtjadualsubpara where id_enakmen = " + idEnakmen
				+ " order by id_jadualsubpara";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();

			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id_jadualsubpara", rs.getLong("id_jadualsubpara"));
				h.put("sub_para", rs.getString("sub_para"));
				;
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusAduan() throws Exception {
		Db db = null;
		String sql = "Select id_Status,keterangan" + " from tblrujstatus where id_Seksyen = 14";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				// s.setTarafKeselamatan(rs.getString("taraf_Keselamatan"));
				// s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenisbayaran> getJenisBayaran() throws Exception {
		Db db = null;
		String sql = "Select id_Jenisbayaran,kod_Jenis_Bayaran,keterangan" + " from tblrujjenisbayaran";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenisbayaran> v = new Vector<Tblrujjenisbayaran>();
			Tblrujjenisbayaran s = null;
			while (rs.next()) {
				s = new Tblrujjenisbayaran();
				s.setIdJenisbayaran(rs.getLong("id_Jenisbayaran"));
				s.setKodJenisBayar(rs.getString("kod_Jenis_Bayaran"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenisbayaran> getJenisBayaranFiAPB() throws Exception {
		Db db = null;
		String sql = "Select id_Jenisbayaran,kod_Jenis_Bayaran,keterangan from tblrujjenisbayaran where id_Jenisbayaran IN (11,19)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenisbayaran> v = new Vector<Tblrujjenisbayaran>();
			Tblrujjenisbayaran s = null;
			while (rs.next()) {
				s = new Tblrujjenisbayaran();
				s.setIdJenisbayaran(rs.getLong("id_Jenisbayaran"));
				s.setKodJenisBayar(rs.getString("kod_Jenis_Bayaran"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujbank> getBank() throws Exception {
		Db db = null;
		String sql = "Select id_Bank,kod_Bank,nama_Bank" + " from tblrujbank";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujbank> v = new Vector<Tblrujbank>();
			Tblrujbank s = null;
			while (rs.next()) {
				s = new Tblrujbank();
				s.setIdBank(rs.getLong("id_Bank"));
				s.setKodBank(rs.getString("kod_Bank"));
				s.setNamaBank(rs.getString("nama_Bank"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblppkrujjenisperintah> getJenisPerintahSek8() throws Exception {
		Db db = null;
		String sql = "Select id_jenisperintah,kod,keterangan from "
				+ " tblppkrujjenisperintah where id_jenisperintah in (1,2,3,7) order by lpad(kod,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblppkrujjenisperintah> v = new Vector<Tblppkrujjenisperintah>();
			Tblppkrujjenisperintah u = null;
			while (rs.next()) {
				u = new Tblppkrujjenisperintah();
				u.setIdJenisperintah(rs.getLong("id_jenisperintah"));
				u.setKod(rs.getString("kod"));
				u.setKeterangan(rs.getString("keterangan"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Object>> getMahkamahByNegeri(Long idnegeri) throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select pj.id_pejabat,pj.kod_pejabat,pj.nama_pejabat,pj.alamat1,pj.alamat2,pj.alamat3,pj.poskod,pj.no_tel,pj.no_fax, da.nama_daerah from tblrujpejabat pj, tblrujdaerah da "
				+ " where pj.id_jenispejabat = 8 and pj.id_daerah = da.id_daerah and pj.id_negeri="
				+ idnegeri
				+ " ORDER BY lpad(id_pejabat,10)";
//		myLogger.info("ALAMAT MAHKAMAH :: " + sql);
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Object>> v = new Vector<Hashtable<String, Object>>();
			Hashtable<String, Object> h;
			while (rs.next()) {
				h = new Hashtable<String, Object>();
				h.put("id", rs.getLong("id_pejabat"));
				h.put("kod_pejabat", rs.getString("kod_pejabat") == null ? "" : rs.getString("kod_pejabat"));
				h.put("namaPejabat", rs.getString("nama_pejabat") == null ? "" : rs.getString("nama_pejabat"));
				h.put("namaDaerah", rs.getString("nama_daerah") == null ? "" : rs.getString("nama_daerah"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// ** MAHKAMAH SYARIAH BY NEGERI [230909]
	public static Vector<Hashtable<String, Comparable>> getMahkamahSyariahByNegeri(Long idnegeri) throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select pj.id_pejabat,pj.kod_pejabat,pj.nama_pejabat,pj.alamat1,pj.alamat2,pj.alamat3,pj.poskod,pj.no_tel,pj.no_fax, da.nama_daerah from tblrujpejabat pj, tblrujdaerah da "
				+ " where pj.id_jenispejabat = 41 and pj.id_daerah = da.id_daerah and pj.id_negeri="
				+ idnegeri
				+ " ORDER BY lpad(id_pejabat,10)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("id_pejabat"));
				h.put("namaPejabat", rs.getString("nama_pejabat") == null ? "" : rs.getString("nama_pejabat"));
				h.put("namaDaerah", rs.getString("nama_daerah") == null ? "" : rs.getString("nama_daerah"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// CLOSE

	public static Vector<Tblppkrujunit> getTempatBicaraByNegeri(String idnegeri) throws Exception {
		Db db = null;
		String sql = "";
		sql = "select nama_pejabat,alamat1,alamat2,alamat3,poskod, no_tel, id_pejabatjkptg from tblRujPejabatJKPTG  where id_pejabatjkptg in ";
		sql += "(select distinct a.id_pejabatjkptg  from TBLRUJPEJABATURUSAN a, tblrujpejabat b, tblrujpejabatjkptg c, ";
		sql += "tblrujdaerah d, tblrujdaerah e where a.id_jenispejabat<>3 ";
		sql += "and a.id_seksyen = 2 and a.id_daerahurus = b.id_daerah and b.id_jenispejabat =2 and c.id_jenispejabat = 22 ";
		sql += "and a.id_pejabatjkptg = c.id_pejabatjkptg and a.id_daerahurus = d.id_daerah and a.id_daerah = e.id_daerah ";
		sql += "and a.id_negeriurus = " + idnegeri + " )";
		sql += " and id_seksyen = 2 ";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblppkrujunit> list = new Vector<Tblppkrujunit>();
			Tblppkrujunit f = null;
			while (rs.next()) {
				f = new Tblppkrujunit();
				f.setnamapejabat(rs.getString(1));
				f.setalamat1(rs.getString(2));
				f.setalamat2(rs.getString(3));
				f.setalamat3(rs.getString(4));
				f.setposkod(rs.getString(5));
				f.setnotel(rs.getString(6));
				f.setidJkptg(rs.getLong(7));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujpejabatjkptg> getTempatBicaraByPejabatJKPTG(String id_jkptg) throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select distinct id_pejabatjkptg,kod_jkptg,nama_pejabat from tblrujpejabatjkptg";
		sql += " where id_pejabatjkptg = '" + id_jkptg + "'";
		sql += " ORDER BY lpad(id_pejabatjkptg,10)";
		//kemaskini pada 2017/10/26
		/*sql = "SELECT "+
			"RP.ID_PEJABAT, RP.KOD_PEJABAT, RP.NAMA_PEJABAT, RP.ID_JENISPEJABAT "+
			"FROM "+
			"TBLRUJPEJABAT RP "+
			",TBLRUJPEJABATURUSAN RPU "+
			"WHERE "+
			"RP.ID_NEGERI =  RPU.ID_NEGERIURUS  AND RP.ID_DAERAH  = RPU.ID_DAERAHURUS "+
			"AND RP.ID_JENISPEJABAT='2' "+
			"AND RPU.ID_SEKSYEN = '2' "+
			"AND RPU.ID_JENISPEJABAT IN ('21','22','24') "+
			"AND RPU.ID_PEJABATJKPTG= '" + id_jkptg + "' "+
//			--24
//			--32
//			--38
//			--44
//			--47
//			--82
			"ORDER BY LPAD(RP.ID_PEJABAT,1) " +
			"";*/
		myLogger.info("sql======"+sql);
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabatjkptg> v = new Vector<Tblrujpejabatjkptg>();
			Tblrujpejabatjkptg s = null;

			while (rs.next()) {
				s = new Tblrujpejabatjkptg();
				s.setIdPejabatjkptg(rs.getLong(1));
				s.setKodJkptg(rs.getString(2));
				s.setNamaPejabat(rs.getString(3));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public static Vector<Tblrujpejabat> getPejabatTanahByJKPTG(String id_jkptg) throws Exception {
		// Azam change to cater for Pn hasiah problem.
		//
		Db db = null;
		String sql = "";

		sql = "select distinct c.id_pejabat, c.kod_pejabat, c.nama_pejabat, c.id_jenispejabat "
				+ " from tblrujpejabatjkptg a, tblrujpejabaturusan b,  tblrujpejabat c "
				+ " where c.ID_NEGERI =  b.ID_NEGERIURUS " + " and c.id_daerah   = b.id_daerahurus "
				+ " and b.id_negeri = a.id_negeri " + " and b.id_daerah = a.id_daerah " + " and b.id_seksyen = '2' "
				+ " and b.id_jenispejabat  = a.id_jenispejabat " + " and b.id_jenispejabat IN ('21','22','24') "
				+ " and c.id_jenispejabat = '2' " + " and b.id_pejabatjkptg = '" + id_jkptg + "'"
				+ " ORDER BY lpad(id_pejabat,10)";

		try {
			db = new Db();

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabat> v = new Vector<Tblrujpejabat>();
			Tblrujpejabat s = null;

			while (rs.next()) {
				s = new Tblrujpejabat();
				s.setIdPejabat(rs.getLong(1));
				s.setKodPejabat(rs.getString("kod_pejabat"));
				s.setNamaPejabat(rs.getString(3));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblppkrujunit> getPegawaiPengendaliByJKPTG(String id_jkptg) throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_unitpsk,kod,nama_pegawai from tblppkrujunit" + " where id_jkptg= '" + id_jkptg + "'"
				+ " and status_peg = '1'" + " ORDER BY lpad(id_unitpsk,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblppkrujunit> list = new Vector<Tblppkrujunit>();
			Tblppkrujunit f = null;
			while (rs.next()) {
				f = new Tblppkrujunit();
				f.setidunitpk(rs.getLong(1));
				f.setkod(rs.getString(2));
				f.setnamapegawai(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblppkrujunit> getPegawaiPengendaliByNegeri(String id_negeri) throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_unitpsk,kod,nama_pegawai,id_negeri from tblppkrujunit" + " WHERE id_negeri = '" + id_negeri + "'"
				+ " AND status_peg = '1' " + " ORDER BY lpad(id_unitpsk,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblppkrujunit> list = new Vector<Tblppkrujunit>();
			Tblppkrujunit f = null;
			while (rs.next()) {
				f = new Tblppkrujunit();
				f.setidunitpk(rs.getLong(1));
				f.setkod(rs.getString(2));
				f.setnamapegawai(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblppkpeguam> getFirmaTerdahuluByPemohon(String id_pemohon, String id_permohonan) throws Exception {
		Db db = null;
		String sql = "";
		String id_peguam = "";

		// sql = "SELECT pg.id_peguam, p.id_perayu, pg.nama_firma ";
		// sql +=
		// "FROM Tblppkperayu p, Tblppkpeguamperayu pp, Tblppkpeguam pg, Tblppkpeguampemohon pgp ";
		// sql += "WHERE pp.id_perayu = p.id_perayu ";
		// sql += "AND pp.id_peguam = pg.id_peguam ";
		// sql += "AND pgp.id_peguam = pg.id_peguam ";
		// sql += "AND pgp.id_pemohon = "+id_pemohon ;
		// sql += " ORDER BY pg.nama_firma asc ";

		sql = " SELECT pg.id_peguam, p.id_perayu, pg.nama_firma ";
		sql += " FROM Tblppkperayu p, tblppkrayuan pr, tblppkpermohonan a, Tblppkpeguamperayu pp, ";
		sql += " Tblppkpeguam pg, Tblppkpeguampemohon pgp ";
		sql += " WHERE pp.id_perayu = p.id_perayu ";
		sql += " AND p.id_rayuan = pr.id_rayuan ";
		sql += " AND pr.id_permohonan = a.id_permohonan ";
		sql += " AND pp.id_peguam = pg.id_peguam ";
		sql += " AND pgp.id_peguam = pg.id_peguam ";
		sql += " AND pgp.id_pemohon = '" + id_pemohon + "'";
		sql += " AND a.id_permohonan = '" + id_permohonan + "'";
		sql += " ORDER BY pg.nama_firma asc ";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()) {

				if (i == 0) {
					id_peguam += "ALL(" + rs.getString("id_peguam");
				} else {
					id_peguam += "," + rs.getString("id_peguam");
				}
				i++;

			}
			if (id_peguam != "") {
				id_peguam += ")";
			}

			Statement stmtN = db.getStatement();
			SQLRenderer rN = new SQLRenderer();
			sql = "select p.id_peguam,p.nama_firma ";
			sql += "from tblppkpeguampemohon pp,tblppkpeguam p ";
			sql += "where pp.id_peguam = p.id_peguam ";
			sql += "and id_pemohon = '" + id_pemohon + "'";
			sql += " and p.nama_firma is not null ";
			if (id_peguam != "") {
				sql += " and p.id_peguam != " + id_peguam;
			}
			sql += " ORDER BY p.nama_firma asc ";

			ResultSet rx = stmtN.executeQuery(sql);
			Vector<Tblppkpeguam> list = new Vector<Tblppkpeguam>();
			Tblppkpeguam f = null;
			while (rx.next()) {
				f = new Tblppkpeguam();
				f.setIdPeguam(rx.getLong(1));

				if (rx.getString(2) == null) {
					f.setNamaFirma("");
				} else {
					f.setNamaFirma(rx.getString(2));
				}

				list.addElement(f);

			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpdtaktabahagian> getBahagian(int idAkta) throws Exception {
		Db db = null;
		String sql = "Select id_aktabahagian,no_bahagian,tajuk_bahagian from " + " tblpdtaktabahagian where id_akta = " + idAkta
				+ "order by id_aktabahagian";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpdtaktabahagian> v = new Vector<Tblpdtaktabahagian>();
			Tblpdtaktabahagian u = null;
			while (rs.next()) {
				u = new Tblpdtaktabahagian();
				u.setIdAktabahagian(rs.getLong("id_aktabahagian"));
				u.setNoBahagian(rs.getString("no_bahagian"));
				u.setTajukBahagian(rs.getString("tajuk_bahagian"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpdtenakmenbahagian> getBahagianEnakmen(int idEnakmen) throws Exception {
		Db db = null;
		String sql = "Select id_enakmenbahagian,no_bahagian,tajuk_bahagian from " + " tblpdtenakmenbahagian where id_enakmen = "
				+ idEnakmen + "order by id_enakmenbahagian";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpdtenakmenbahagian> v = new Vector<Tblpdtenakmenbahagian>();
			Tblpdtenakmenbahagian u = null;
			while (rs.next()) {
				u = new Tblpdtenakmenbahagian();
				u.setIdEnakmenbahagian(rs.getLong("id_enakmenbahagian"));
				u.setNoBahagian(rs.getString("no_bahagian"));
				u.setTajukBahagian(rs.getString("tajuk_bahagian"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpdtenakmenbab> getBabEnakmen(int idEnakmen) throws Exception {
		Db db = null;
		String sql = "Select id_enakmenbab,no_bab,tajuk_bab from " + " tblpdtenakmenbab where id_enakmen = " + idEnakmen
				+ "order by id_enakmenbab";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpdtenakmenbab> v = new Vector<Tblpdtenakmenbab>();
			Tblpdtenakmenbab u = null;
			while (rs.next()) {
				u = new Tblpdtenakmenbab();
				u.setIdEnakmenbab(rs.getLong("id_enakmenbab"));
				u.setNoBab(rs.getString("no_bab"));
				u.setTajukBab(rs.getString("tajuk_bab"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpdtenakmenseksyen> getSeksyenEnakmen(int idEnakmen) throws Exception {
		Db db = null;
		String sql = "Select id_enakmenseksyen,seksyen from " + " tblpdtenakmenseksyen where id_enakmen = " + idEnakmen
				+ "order by id_enakmenseksyen";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpdtenakmenseksyen> v = new Vector<Tblpdtenakmenseksyen>();
			Tblpdtenakmenseksyen u = null;
			while (rs.next()) {
				u = new Tblpdtenakmenseksyen();
				u.setIdEnakmenseksyen(rs.getLong("id_enakmenseksyen"));
				u.setSeksyen(rs.getString("seksyen"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpdtaktabab> getBab(int idAkta) throws Exception {
		Db db = null;
		String sql = "Select id_aktabab,no_bab,tajuk_bab from " + " tblpdtaktabab where id_akta = " + idAkta
				+ "order by id_aktabab";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpdtaktabab> v = new Vector<Tblpdtaktabab>();
			Tblpdtaktabab u = null;
			while (rs.next()) {
				u = new Tblpdtaktabab();
				u.setIdAktabab(rs.getLong("id_aktabab"));
				u.setNoBab(rs.getString("no_bab"));
				u.setTajukBab(rs.getString("tajuk_bab"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpdtaktaseksyen> getSeksyenAkta(int idAkta) throws Exception {
		Db db = null;
		String sql = "Select id_aktaseksyen,seksyen from " + " tblpdtaktaseksyen where id_akta = " + idAkta
				+ "order by id_aktaseksyen";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpdtaktaseksyen> v = new Vector<Tblpdtaktaseksyen>();
			Tblpdtaktaseksyen u = null;
			while (rs.next()) {
				u = new Tblpdtaktaseksyen();
				u.setIdAktaseksyen(rs.getLong("id_aktaseksyen"));
				u.setSeksyen(rs.getString("seksyen"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getSubSeksyenAkta(int idAkta) throws Exception {
		Db db = null;
		String sql = "Select id_aktasubseksyen,sub_seksyen from " + " tblpdtaktasubseksyen where id_akta = " + idAkta
				+ "order by id_aktasubseksyen";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id_aktasubseksyen", rs.getLong("ID_AKTASUBSEKSYEN"));
				h.put("sub_seksyen", rs.getString("SUB_SEKSYEN"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getPara(int idAkta) throws Exception {
		Db db = null;
		String sql = "Select id_aktapara,para from " + " tblpdtaktapara where id_akta = " + idAkta + "order by id_aktapara";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id_aktapara", rs.getLong("ID_AKTAPARA"));
				h.put("para", rs.getString("PARA"));
				;
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getJadualAkta(int idAkta) throws Exception {
		Db db = null;
		String sql = "Select id_jadual,no_jadual from " + " tblpdtjadual where id_akta = " + idAkta + "order by id_jadual";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();

			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id_jadual", rs.getLong("id_jadual"));
				h.put("no_jadual", rs.getString("no_jadual"));
				;
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getJadualPara(int idAkta) throws Exception {
		Db db = null;
		String sql = "Select id_jadualpara,para from " + " tblpdtjadualpara where id_akta = " + idAkta + "order by id_jadualpara";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id_jadualpara", rs.getLong("ID_JADUALPARA"));
				h.put("para", rs.getString("PARA"));
				;
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getJadualSubPara(int idAkta) throws Exception {
		Db db = null;
		String sql = "Select id_jadualsubpara,sub_para from " + " tblpdtjadualsubpara where id_akta = " + idAkta
				+ "order by id_jadualsubpara";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id_jadualsubpara", rs.getLong("ID_JADUALSUBPARA"));
				h.put("sub_para", rs.getString("SUB_PARA"));
				;
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblppkrujunit> getSenaraiPegawaiLaporan(String idNegeri) throws Exception {
		Db db = null;
		// String sql =
		// "SELECT ID_UNITPSK, KOD, NAMA_PEGAWAI FROM TBLPPKRUJUNIT WHERE"
		// + " ID_NEGERI = '" + idNegeri +
		// "' AND STATUS_PEG = '1' ORDER BY LPAD(KOD,100)";

		//YG NI ORI
		//String sql = "SELECT ID_UNITPSK, KOD, NAMA_PEGAWAI,STATUS_PEG" + ",case " + " when STATUS_PEG=1 then '(Aktif)' "
		//		+ " else '(Tidak Aktif)' " + " end AS CATATAN " + "FROM TBLPPKRUJUNIT WHERE ID_NEGERI = '" + idNegeri
		//		+ "' ORDER BY LPAD(KOD,100)";

		String sql = "SELECT ID_UNITPSK, KOD, NAMA_PEGAWAI,STATUS_PEG" + ",case " + " when STATUS_PEG=1 then '(Aktif)' "
				+ " else '(Tidak Aktif)' " + " end AS CATATAN " + "FROM TBLPPKRUJUNIT "
				+ "ORDER BY LPAD(KOD,100)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			System.out.println("sql = " +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblppkrujunit> list = new Vector<Tblppkrujunit>();
			Tblppkrujunit f = null;
			while (rs.next()) {
				f = new Tblppkrujunit();
				f.setidunitpk(rs.getLong(1));
				f.setkod(rs.getString(2));
				f.setnamapegawai(rs.getString(3));
				f.setstatuspeg(rs.getString(4));
				f.setcatatan(rs.getString(5));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getSenaraiStatusPPKSek8() throws Exception {
		String key = "DB.getSenaraiStatusPPKSek8";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Hashtable<String, Comparable>>) cachedObject.getObjectValue();
		} else {
			// azam change on 08.09.09: order by kod_status
			Db db = null;
			String sql = "SELECT ID_STATUS, KOD_STATUS, KETERANGAN FROM TBLRUJSTATUS WHERE ID_SEKSYEN = 2 AND"
					+ " ID_STATUS IN (8,19,14,18,21,25,30,41,44,47,50,53,56,61,64,70,169,150,151,152,153,154,155,163,164,165,170) ORDER BY KOD_STATUS ASC";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
				Hashtable<String, Comparable> h;
				while (rs.next()) {
					h = new Hashtable<String, Comparable>();
					h.put("id", rs.getLong("ID_STATUS"));
					h.put("kod", rs.getString("KOD_STATUS") == null ? "" : rs.getString("KOD_STATUS"));
					h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
					v.addElement(h);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Hashtable<String, Comparable>> getDaerahForSuratIringanTerengganu() throws Exception {
		String key = "DB.getDaerahForSuratIringanTerengganu";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Hashtable<String, Comparable>>) cachedObject.getObjectValue();
		} else {

			Db db = null;
			String sql = "SELECT A.ID_PEJABATJKPTG, B.ID_DAERAH, B.KOD_DAERAH, B.NAMA_DAERAH"
					+ " FROM TBLRUJPEJABATJKPTG A, TBLRUJDAERAH B WHERE A.ID_SEKSYEN = 2 AND A.ID_NEGERI = 11"
					+ " AND A.ID_JENISPEJABAT = 22 AND A.ID_DAERAH = B.ID_DAERAH ORDER BY NAMA_DAERAH ASC";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
				Hashtable<String, Comparable> h;
				while (rs.next()) {
					h = new Hashtable<String, Comparable>();
					h.put("id", rs.getLong("ID_PEJABATJKPTG"));
					h.put("kod", rs.getString("KOD_DAERAH") == null ? "" : rs.getString("KOD_DAERAH"));
					h.put("namaDaerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
					v.addElement(h);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Hashtable<String, Comparable>> getUnitPPKByNegeri(String idNegeri) throws Exception {
		String key = "DB.getUnitPPKByNegeri";
		// Element cachedObject = myCache.get(key);
		// if (cachedObject != null) {
		// return (Vector)cachedObject.getObjectValue();
		// } else {
		//
		Db db = null;
		String sql = "SELECT A.ID_PEJABATJKPTG, B.ID_DAERAH, B.KOD_DAERAH, B.NAMA_DAERAH, A.NAMA_PEJABAT, A.KOD_JKPTG, A.ALAMAT1"
				+ " FROM TBLRUJPEJABATJKPTG A, TBLRUJDAERAH B WHERE A.ID_SEKSYEN = 2 AND A.ID_NEGERI = '" + idNegeri + "'"
				+ " AND A.ID_JENISPEJABAT = 22 AND A.ID_DAERAH = B.ID_DAERAH ORDER BY ALAMAT1 ASC";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_PEJABATJKPTG"));
				h.put("kod", rs.getString("KOD_JKPTG") == null ? "" : rs.getString("KOD_JKPTG"));
				h.put("keterangan", Utils.RemoveSymbol(rs.getString("NAMA_PEJABAT").toUpperCase()));
				v.addElement(h);
			}
			// myCache.put(new Element(key, v));
			return v;
		} finally {
			if (db != null)
				db.close();
			// }
		}
	}

	public static Vector<Hashtable<String, Integer>> getTahun() throws Exception {
		String key = "DB.getTahun";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Hashtable<String, Integer>>) cachedObject.getObjectValue();
		} else {
			Date date = new Date();
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			Integer year = cal.get(Calendar.YEAR);

			Vector<Hashtable<String, Integer>> v = new Vector<Hashtable<String, Integer>>();
			Hashtable<String, Integer> h;
			for (int y = 0; y < 40; y++) {
				h = new Hashtable<String, Integer>();
				h.put("id", year);
				h.put("tahun", year);
				v.addElement(h);
				year -= 1;
			}
			myCache.put(new Element(key, v));
			return v;
		}
	}

	public static Vector<Hashtable<String, Integer>> getTahunH() throws Exception {
		String key = "DB.getTahun";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Hashtable<String, Integer>>) cachedObject.getObjectValue();
		} else {
			Date date = new Date();
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			Integer year = cal.get(Calendar.YEAR);

			Vector<Hashtable<String, Integer>> v = new Vector<Hashtable<String, Integer>>();
			Hashtable<String, Integer> h;
			for (int y = 0; y < 40; y++) {
				h = new Hashtable<String, Integer>();
				h.put("id", year);
				h.put("tahun", year);
				v.addElement(h);
				year -= 1;
			}
			myCache.put(new Element(key, v));
			return v;
		}
	}


	public static Vector<Hashtable<String, Comparable>> getHTAByIdSimati(String idSimati) throws Exception {
		Db db = null;
		String sql = "SELECT ID_HTA,NO_HAKMILIK FROM TBLPPKHTA WHERE ID_SIMATI = " + idSimati;

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_HTA"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujpejabat> getPejabatTanah() throws Exception {
		String key = "DB.getTahun";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujpejabat>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "select distinct id_pejabat, nama_pejabat from tblrujpejabat where id_jenispejabat = 2";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujpejabat> v = new Vector<Tblrujpejabat>();
				Tblrujpejabat s = null;
				while (rs.next()) {
					s = new Tblrujpejabat();
					s.setIdPejabat(rs.getLong(1));
					s.setNamaPejabat(rs.getString(2));

					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Hashtable<String, Comparable>> getDaerahByUnitPPK(String idPejabatJKPTG) throws Exception {
		Db db = null;
		String sql = " SELECT DISTINCT U.ID_DAERAHURUS, D.KOD_DAERAH, D.NAMA_DAERAH ";
		sql += "	FROM TBLRUJPEJABATURUSAN U, TBLRUJPEJABATJKPTG KP, TBLRUJDAERAH D ";
		sql += "	WHERE U.ID_PEJABATJKPTG = KP.ID_PEJABATJKPTG ";
		sql += "	AND U.ID_DAERAHURUS = D.ID_DAERAH ";
		sql += "	AND KP.ID_PEJABATJKPTG = '" + idPejabatJKPTG + "'";
		sql += "	AND U.ID_JENISPEJABAT = '22' ";
		sql += " ORDER BY D.NAMA_DAERAH";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			myLogger.info("getDaerahByUnitPPK = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_DAERAHURUS"));
				h.put("kod", rs.getString("KOD_DAERAH") == null ? "" : rs.getString("KOD_DAERAH"));
				h.put("keterangan", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujpejabat> getPejabatPenasihatUndangUndangByNegeri(String idnegeri) throws Exception {

		Db db = null;
		String sql = "";

		sql = "SELECT DISTINCT id_pejabat, nama_pejabat, id_jenispejabat" + " FROM tblrujpejabat" + " WHERE id_jenispejabat = 81"
				+ " AND id_seksyen = 2" + " AND id_negeri = '" + idnegeri + "'" + " ORDER BY lpad(id_pejabat,10)";

		try {
			db = new Db();

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabat> v = new Vector<Tblrujpejabat>();
			Tblrujpejabat s = null;

			while (rs.next()) {
				s = new Tblrujpejabat();
				s.setIdPejabat(rs.getLong(1));
				s.setNamaPejabat(rs.getString(2));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujpejabat> getPejabatPenasihatUndangUndang() throws Exception {
		Db db = null;
		String sql = "select distinct id_pejabat, nama_pejabat from tblrujpejabat where id_jenispejabat = '81' ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabat> v = new Vector<Tblrujpejabat>();
			Tblrujpejabat s = null;
			while (rs.next()) {
				s = new Tblrujpejabat();
				s.setIdPejabat(rs.getLong(1));
				s.setNamaPejabat(rs.getString(2));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjenispb> getJenisPB() throws Exception {
		Db db = null;
		String sql = " SELECT ID_JENISPB,KOD_JENIS_PB,KETERANGAN FROM TBLRUJJENISPB ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenispb> v = new Vector<Tblrujjenispb>();
			Tblrujjenispb s = null;
			while (rs.next()) {
				s = new Tblrujjenispb();
				s.setIdJenispb(rs.getLong(1));
				s.setKodJenisPb(rs.getString(2));
				s.setKeterangan(rs.getString(3).toUpperCase());

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusPPT() throws Exception {
		Db db = null;
		String sql = " SELECT ID_STATUS, KOD_STATUS, KETERANGAN FROM TBLRUJSTATUS WHERE ID_STATUS IN (11,127)";
		sql += " ORDER BY LPAD(KOD_STATUS,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("ID_STATUS"));
				s.setKodStatus(rs.getString("KOD_STATUS"));
				s.setKeterangan(rs.getString("KETERANGAN"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// * HAKMILIK (PPT) - ELLY 13012010
	public static Vector<Tblppthakmilik> getHakmilik(String id_hakmilik, String id_pihakberkepentingan) throws Exception {
		String key = "DB.getHakmilik" + id_hakmilik + id_pihakberkepentingan;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblppthakmilik>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = " SELECT DISTINCT A.ID_HAKMILIK, A.NO_HAKMILIK FROM TBLPPTHAKMILIK a, TBLPPTHAKMILIKPB B, TBLPPTPIHAKBERKEPENTINGAN C WHERE A.ID_HAKMILIK = B.ID_HAKMILIK AND B.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN AND A.ID_HAKMILIK = '"
					+ id_hakmilik + "' AND C.ID_PIHAKBERKEPENTINGAN = '" + id_pihakberkepentingan + "' ";
			myLogger.info("getHakmilik BY PB :: " + sql);
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblppthakmilik> v = new Vector<Tblppthakmilik>();
				Tblppthakmilik s = null;
				while (rs.next()) {
					s = new Tblppthakmilik();
					s.setIdHakmilik(rs.getLong(1));
					s.setNoHakmilik(rs.getString(2));
					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	// * GET NOLOT BY HAKMILIK (PPT) - ELLY 13012010
	public static Vector<Tblppthakmilik> getNoLotByHakmilik(String id_hakmilik) throws Exception {

		String key = "DB.getHakmilik" + id_hakmilik;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblppthakmilik>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "SELECT DISTINCT A.ID_HAKMILIK, A.NO_LOT FROM TBLPPTHAKMILIK A WHERE A.ID_HAKMILIK =  " + id_hakmilik
					+ " ";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblppthakmilik> v = new Vector<Tblppthakmilik>();
				Tblppthakmilik s = null;
				while (rs.next()) {
					s = new Tblppthakmilik();
					s.setIdHakmilik(rs.getLong(1));
					s.setNoLot(rs.getString(2));
					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	// * GET PIHAK BANTAH BY HAKMILIK (PPT) - ELLY 13012010
	public static Vector<Hashtable<String, String>> getPihakBantahByHakmilik(String id_hakmilik) throws Exception {
		Db db = null;
		String sql = "SELECT A.ID_HAKMILIK, A.ID_PIHAKBERKEPENTINGAN,A.ID_JENISPB,B.KETERANGAN,B.KOD_JENIS_PB,A.ID_JENISNOPB,A.NAMA_PB FROM TBLPPTPIHAKBERKEPENTINGAN A,TBLRUJJENISPB B,TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D WHERE A.ID_JENISPB=B.ID_JENISPB AND C.ID_HAKMILIK = D.ID_HAKMILIK AND A.ID_PIHAKBERKEPENTINGAN = D.ID_PIHAKBERKEPENTINGAN AND C.ID_HAKMILIK = "
				+ id_hakmilik + " ";
		// myLogger.info("getPihakBantahByHakmilik :: "+sql);
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();
			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("id_hakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("id_pihakberkepentingan",
						rs.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("id_jenispb", rs.getString("ID_JENISPB") == null ? "" : rs.getString("ID_JENISPB"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("kod_jenispb", rs.getString("KOD_JENIS_PB") == null ? "" : rs.getString("KOD_JENIS_PB"));
				h.put("nama_pb", rs.getString("NAMA_PB") == null ? "" : rs.getString("NAMA_PB"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// * GET JENIS PIHAK BANTAH BY ID PIHAKBERKEPENTINGAN (PPT) - ELLY 13012010
	public static Vector<Hashtable<String, String>> getPihakBantahByIdPihakBerkepentingan(String id_pihakberkepentingan) throws Exception {
		Db db = null;
		// String sql =
		// " SELECT C.ID_HAKMILIK, A.ID_PIHAKBERKEPENTINGAN,A.ID_JENISPB,B.KETERANGAN,B.KOD_JENIS_PB,A.ID_JENISNOPB,A.NAMA_PB FROM TBLPPTPIHAKBERKEPENTINGAN A,TBLRUJJENISPB B,TBLPPTHAKMILIKPB C WHERE A.ID_JENISPB=B.ID_JENISPB AND C.ID_PIHAKBERKEPENTINGAN = A.ID_PIHAKBERKEPENTINGAN AND A.ID_PIHAKBERKEPENTINGAN = '"+
		// id_pihakberkepentingan +"' ";
		String sql = " SELECT C.ID_HAKMILIK, A.ID_PIHAKBERKEPENTINGAN,C.ID_JENISPB,B.KETERANGAN,B.KOD_JENIS_PB,A.ID_JENISNOPB,A.NAMA_PB FROM TBLPPTPIHAKBERKEPENTINGAN A,TBLRUJJENISPB B,TBLPPTHAKMILIKPB C WHERE C.ID_JENISPB=B.ID_JENISPB(+) AND C.ID_PIHAKBERKEPENTINGAN = A.ID_PIHAKBERKEPENTINGAN AND A.ID_PIHAKBERKEPENTINGAN = '"
				+ id_pihakberkepentingan + "' ";

		myLogger.info("Jenis PihakBerkepentingan :: " + sql);
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();
			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("id_hakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("id_pihakberkepentingan",
						rs.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("id_jenispb", rs.getString("ID_JENISPB") == null ? "" : rs.getString("ID_JENISPB"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("kod_jenispb", rs.getString("KOD_JENIS_PB") == null ? "" : rs.getString("KOD_JENIS_PB"));
				h.put("nama_pb", rs.getString("NAMA_PB") == null ? "" : rs.getString("NAMA_PB"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// * GET PIHAK BANTAH BY ID PIHAKBERKEPENTINGAN (PPT) - ELLY 10022010
	public static Vector<Hashtable<String, String>> getNamaPembantahByIdPihakBerkepentingan(String id_hakmilikpb) throws Exception {
		Db db = null;
		// String sql =
		// " SELECT C.ID_HAKMILIK,A.ID_PIHAKBERKEPENTINGAN,A.ID_JENISPB,B.KETERANGAN,B.KOD_JENIS_PB,A.ID_JENISNOPB,A.NAMA_PB FROM TBLPPTPIHAKBERKEPENTINGAN A,TBLRUJJENISPB B,TBLPPTHAKMILIKPB C WHERE A.ID_JENISPB=B.ID_JENISPB(+) AND C.ID_PIHAKBERKEPENTINGAN=A.ID_PIHAKBERKEPENTINGAN AND A.ID_PIHAKBERKEPENTINGAN = '"+
		// id_pihakberkepentingan +"' ";
		String sql = " SELECT C.ID_HAKMILIK,A.ID_PIHAKBERKEPENTINGAN,C.ID_JENISPB,B.KETERANGAN,B.KOD_JENIS_PB,A.ID_JENISNOPB,A.NAMA_PB,C.ID_HAKMILIKPB FROM TBLPPTPIHAKBERKEPENTINGAN A,TBLRUJJENISPB B,TBLPPTHAKMILIKPB C WHERE A.ID_JENISPB=B.ID_JENISPB(+) AND C.ID_PIHAKBERKEPENTINGAN=A.ID_PIHAKBERKEPENTINGAN AND C.ID_HAKMILIKPB = '"
				+ id_hakmilikpb + "' ";
		myLogger.info("NAMA PEMBANTAH BY IDPB :: " + sql);
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();
			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("id_hakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("id_pihakberkepentingan",
						rs.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("id_jenispb", rs.getString("ID_JENISPB") == null ? "" : rs.getString("ID_JENISPB"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("kod_jenispb", rs.getString("KOD_JENIS_PB") == null ? "" : rs.getString("KOD_JENIS_PB"));
				h.put("nama_pb", rs.getString("NAMA_PB") == null ? "" : rs.getString("NAMA_PB"));
				h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB") == null ? "" : rs.getString("ID_HAKMILIKPB"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// GET CARA BAYAR PAMPASAN BANTAHAN (PPT) - ELLY 19012010
	public static Vector<Tblrujcarabayar> getCaraBayar() throws Exception {
		Db db = null;
		String sql = "Select id_carabayar,kod_carabayar,keterangan"
				+ " from tblrujcarabayar where id_carabayar in (1,2,5,6,9) order by id_carabayar ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujcarabayar> v = new Vector<Tblrujcarabayar>();
			Tblrujcarabayar s = null;
			while (rs.next()) {
				s = new Tblrujcarabayar();
				s.setIdCarabayar(rs.getLong("id_carabayar"));
				s.setKodCaraBayar(rs.getString("kod_carabayar"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// GET JENIS BANK BANTAHAN (PPT) - ELLY 19012010
	public static Vector<Tblrujbank> getJenisBank() throws Exception {
		Db db = null;
		String sql = "Select id_bank,kod_bank,nama_bank" + " from tblrujbank  where flag_aktif = 'Y' order by kod_bank";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujbank> v = new Vector<Tblrujbank>();
			Tblrujbank s = null;
			while (rs.next()) {
				s = new Tblrujbank();
				s.setIdBank(rs.getLong("id_bank"));
				s.setKodBank(rs.getString("kod_bank"));
				s.setNamaBank(rs.getString("nama_bank"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujkategori> getKategoriTanah() throws Exception {
		String key = "DB.getKategoriTanah";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujkategori>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_kategori");
				r.add("kod_kategori");
				r.add("Keterangan");
				sql = r.getSQLSelect("Tblrujkategori", "kod_kategori");
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujkategori> v = new Vector<Tblrujkategori>();
				Tblrujkategori l = null;
				while (rs.next()) {
					l = new Tblrujkategori();
					l.setIdKategori(rs.getLong(1));
					l.setKodKategori(rs.getString(2));
					l.setKeterangan(rs.getString(3));
					v.addElement(l);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujsubkategori> getSubKategoriTanah(String idKategoriTanah) throws Exception {
		String key = "DB.getSubKategoriTanah" + idKategoriTanah;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujsubkategori>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_subkategori");
				r.add("kod_subkategori");
				r.add("Keterangan");
				r.add("id_kategori", idKategoriTanah);
				sql = r.getSQLSelect("Tblrujsubkategori", "kod_subkategori");
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujsubkategori> v = new Vector<Tblrujsubkategori>();
				Tblrujsubkategori l = null;
				while (rs.next()) {
					l = new Tblrujsubkategori();
					l.setIdSubkategori(rs.getLong(1));
					l.setKodSubkategori(rs.getString(2));
					l.setKeterangan(rs.getString(3));
					v.addElement(l);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	// 24012009 Jenis Bebanan -Syah
	public static Vector<Tblrujbebanan> getJenisBebanan() throws Exception {
		String key = "DB.getJenisBebanan";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujbebanan>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_bebanan");
				r.add("kod_bebanan");
				r.add("Keterangan");
				sql = r.getSQLSelect("Tblrujbebanan", "kod_bebanan");
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujbebanan> v = new Vector<Tblrujbebanan>();
				Tblrujbebanan l = null;
				while (rs.next()) {
					l = new Tblrujbebanan();
					l.setIdRujbebanan(rs.getLong(1));
					l.setKodBebanan(rs.getString(2));
					l.setKeterangan(rs.getString(3));
					v.addElement(l);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	// 26012009 Jabatan Teknikal -Syah
	public static Vector<Tblpptjabatanteknikal> getJabatanTeknikal() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT id_jabatanteknikal, kod_jabatan_teknikal, nama_jabatan FROM Tblpptjabatanteknikal"
				+ " ORDER BY lpad(id_jabatanteknikal,10)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpptjabatanteknikal> v = new Vector<Tblpptjabatanteknikal>();
			Tblpptjabatanteknikal s = null;
			while (rs.next()) {
				s = new Tblpptjabatanteknikal();
				s.setIdJabatanteknikal(rs.getLong(1));
				s.setKodJabatanTeknikal(rs.getString(2));
				s.setNamaJabatan(rs.getString(3));

				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// 02022010 get PTG by negeri -Syah
	public static Vector<Tblrujpejabat> getPejabatPTGbyNegeri(String idnegeri) throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_pejabat,kod_pejabat,nama_pejabat,id_seksyen,id_jenispejabat from tblrujpejabat ";
		sql += " where id_seksyen = '1'";
		sql += " and id_jenispejabat = '1'";
		sql += " and id_negeri='" + idnegeri + "'";
		sql += " ORDER BY lpad(id_pejabat,10)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabat> list = new Vector<Tblrujpejabat>();
			Tblrujpejabat f = null;
			while (rs.next()) {
				f = new Tblrujpejabat();
				f.setIdPejabat(rs.getLong(1));
				f.setKodPejabat(rs.getString(2));
				f.setNamaPejabat(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}// close getPejabatPTGbyNegeri

	// 02022010 get PTG -Syah
	public static Vector<Tblrujpejabat> getPejabatPTG() throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_pejabat,kod_pejabat,nama_pejabat,id_seksyen,id_jenispejabat from tblrujpejabat ";
		sql += " where id_seksyen = '1'";
		sql += " and id_jenispejabat = '1'";
		sql += " ORDER BY lpad(id_pejabat,10)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabat> list = new Vector<Tblrujpejabat>();
			Tblrujpejabat f = null;
			while (rs.next()) {
				f = new Tblrujpejabat();
				f.setIdPejabat(rs.getLong(1));
				f.setKodPejabat(rs.getString(2));
				f.setNamaPejabat(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}// close getPejabatPTG

	// 02022010 get PTG by negeri -Syah
	public static Vector<Tblrujpejabat> getPejabatPTDbyNegeri(String idnegeri) throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_pejabat,kod_pejabat,nama_pejabat,id_seksyen,id_jenispejabat from tblrujpejabat ";
		sql += " where id_seksyen = '1'";
		sql += " and id_jenispejabat = '2'";
		sql += " and id_negeri='" + idnegeri + "'";
		sql += " ORDER BY lpad(kod_pejabat,10)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabat> list = new Vector<Tblrujpejabat>();
			Tblrujpejabat f = null;
			while (rs.next()) {
				f = new Tblrujpejabat();
				f.setIdPejabat(rs.getLong(1));
				f.setKodPejabat(rs.getString(2));
				f.setNamaPejabat(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}// close getPejabatPTDbyNegeri

	// 02022010 get PTD -Syah
	public static Vector<Tblrujpejabat> getPejabatPTD() throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_pejabat,kod_pejabat,nama_pejabat,id_seksyen,id_jenispejabat from tblrujpejabat ";
		sql += " where id_seksyen = '1'";
		sql += " and id_jenispejabat = '2'";
		sql += " ORDER BY lpad(kod_pejabat,10)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabat> list = new Vector<Tblrujpejabat>();
			Tblrujpejabat f = null;
			while (rs.next()) {
				f = new Tblrujpejabat();
				f.setIdPejabat(rs.getLong(1));
				f.setKodPejabat(rs.getString(2));
				f.setNamaPejabat(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}// close getPejabatPTG

	// ADDED BY HIDAYAH 08022010
	public static Vector<Tblpptpihakberkepentingan> getNamaPB(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT D.ID_PIHAKBERKEPENTINGAN,D.NAMA_PB" +

		" FROM TBLPPTPERMOHONAN A," + " TBLPPTHAKMILIK B," + " TBLPPTHAKMILIKPB C," + " TBLPPTPIHAKBERKEPENTINGAN D,"
				+ " TBLRUJJENISPB E" +

				" WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN" + " AND B.ID_HAKMILIK = C.ID_HAKMILIK"
				+ " AND E.ID_JENISPB = D.ID_JENISPB" + " AND E.KOD_JENIS_PB NOT IN ('WJ','PG','PS')"
				+ " AND D.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN" + " AND B.ID_PERMOHONAN = '" + idPermohonan + "'";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpptpihakberkepentingan> list = new Vector<Tblpptpihakberkepentingan>();
			Tblpptpihakberkepentingan f = null;
			while (rs.next()) {
				f = new Tblpptpihakberkepentingan();
				f.setIdPihakberkepentingan(rs.getLong(1));
				f.setNamaPb(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}

	/**
	 * added by peje to cater pemohon pelepasan for PTG, PTD and JKPTG
	 *
	 * @return
	 * @throws Exception
	 */
	public static Vector<Hashtable<String, Comparable>> getKategoriPemohonPTDPTGJKPTG() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT ID_KATEGORIPEMOHON, KOD_KATEGORIPEMOHON, KETERANGAN FROM TBLRUJKATEGORIPEMOHON"
				+ " WHERE ID_KATEGORIPEMOHON IN (4,5,8,9)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_KATEGORIPEMOHON"));
				h.put("kod", rs.getString("KOD_KATEGORIPEMOHON") == null ? "" : rs.getString("KOD_KATEGORIPEMOHON").toUpperCase());
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getPejabatKJP() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT ID_JENISPEJABAT,KOD_JENIS_PEJABAT, KETERANGAN FROM TBLRUJJENISPEJABAT"
				+ " WHERE ID_JENISPEJABAT IN (1,2,3,4,5,10,25)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_JENISPEJABAT"));
				h.put("kod", rs.getString("KOD_JENIS_PEJABAT") == null ? "" : rs.getString("KOD_JENIS_PEJABAT").toUpperCase());
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getPejabatJKPTGByIdNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "SELECT ID_PEJABATJKPTG, KOD_JKPTG, NAMA_PEJABAT FROM TBLRUJPEJABATJKPTG WHERE ID_SEKSYEN = '01' AND ID_NEGERI = '"
				+ idNegeri + "' ORDER BY KOD_JKPTG ASC";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_PEJABATJKPTG"));
				h.put("kod", rs.getString("KOD_JKPTG") == null ? "" : rs.getString("KOD_JKPTG").toUpperCase());
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getPejabatKPTGByIdNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "SELECT ID_PEJABATJKPTG, KOD_JKPTG, NAMA_PEJABAT FROM TBLRUJPEJABATJKPTG WHERE ID_SEKSYEN = '2'"
				+ " AND ID_JENISPEJABAT = '24' AND ID_NEGERI = '" + idNegeri + "' ORDER BY KOD_JKPTG ASC";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_PEJABATJKPTG"));
				h.put("kod", rs.getString("KOD_JKPTG") == null ? "" : rs.getString("KOD_JKPTG").toUpperCase());
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getPejabatKPTGByIdNegeriIdSeksyen(String idNegeri, String idSeksyen) throws Exception {
		Db db = null;
		String sql = "SELECT ID_PEJABATJKPTG, KOD_JKPTG, NAMA_PEJABAT FROM TBLRUJPEJABATJKPTG WHERE ID_SEKSYEN = '" + idSeksyen +"'"
				+ " AND ID_JENISPEJABAT = '24' AND ID_NEGERI = '" + idNegeri + "' ORDER BY KOD_JKPTG ASC";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_PEJABATJKPTG"));
				h.put("kod", rs.getString("KOD_JKPTG") == null ? "" : rs.getString("KOD_JKPTG").toUpperCase());
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getPejabatByIdNegeriAndJenisPejabat(String idNegeri, String jenisPejabat) throws Exception {
		Db db = null;
		String sql = "SELECT ID_PEJABAT, KOD_PEJABAT, NAMA_PEJABAT FROM TBLRUJPEJABAT WHERE ID_SEKSYEN = '01' AND ID_JENISPEJABAT = '"
				+ jenisPejabat + "' AND ID_NEGERI = '" + idNegeri + "' ORDER BY KOD_PEJABAT ASC";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_PEJABAT"));
				h.put("kod", rs.getString("KOD_PEJABAT") == null ? "" : rs.getString("KOD_PEJABAT").toUpperCase());
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getKategoriPemohonPelepasan() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT ID_KATEGORIPEMOHON, KOD_KATEGORIPEMOHON, KETERANGAN FROM TBLRUJKATEGORIPEMOHON"
				+ " WHERE ID_KATEGORIPEMOHON IN (4,5,8) ORDER BY KOD_KATEGORIPEMOHON ASC";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_KATEGORIPEMOHON"));
				h.put("kod", rs.getString("KOD_KATEGORIPEMOHON") == null ? "" : rs.getString("KOD_KATEGORIPEMOHON").toUpperCase());
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getKategoriPemohonPenawaran() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT ID_KATEGORIPEMOHON, KOD_KATEGORIPEMOHON, KETERANGAN FROM TBLRUJKATEGORIPEMOHON"
				+ " WHERE ID_KATEGORIPEMOHON IN (3) ORDER BY KOD_KATEGORIPEMOHON ASC";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_KATEGORIPEMOHON"));
				h.put("kod", rs.getString("KOD_KATEGORIPEMOHON") == null ? "" : rs.getString("KOD_KATEGORIPEMOHON").toUpperCase());
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getKategoriPemohonPNW() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT ID_KATEGORIPEMOHON, KOD_KATEGORIPEMOHON, KETERANGAN FROM TBLRUJKATEGORIPEMOHON"
				+ " WHERE ID_KATEGORIPEMOHON IN (8) ORDER BY KOD_KATEGORIPEMOHON ASC";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_KATEGORIPEMOHON"));
				h.put("kod", rs.getString("KOD_KATEGORIPEMOHON") == null ? "" : rs.getString("KOD_KATEGORIPEMOHON").toUpperCase());
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// author : hilda
	// date : 05/08/2010
	// PBT - Pihak Berkuasa Tempatan
	public static Vector<Hashtable<String, Comparable>> getPejabatPBT() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT ID_PEJABAT, KOD_PEJABAT, NAMA_PEJABAT FROM TBLRUJPEJABAT "
				+ " WHERE ID_JENISPEJABAT = 25 AND ID_SEKSYEN = 4 ";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_PEJABAT"));
				h.put("kod", rs.getString("KOD_PEJABAT") == null ? "" : rs.getString("KOD_PEJABAT").toUpperCase());
				h.put("keterangan", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// author : hilda
	// date : 05/08/2010
	// PBT - Pihak Berkuasa Tempatan by negeri
	public static Vector<Tblrujpejabat> getPejabatPBTByNegeri(String idnegeri) throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_pejabat,kod_pejabat,nama_pejabat from tblrujpejabat";
		sql += " where id_negeri= '" + idnegeri + "'";
		sql += " and id_jenispejabat = 25 ";
		sql += " and id_seksyen = 4 ";
		sql += " ORDER BY lpad(id_pejabat,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujpejabat> list = new Vector<Tblrujpejabat>();
			Tblrujpejabat f = null;
			while (rs.next()) {
				f = new Tblrujpejabat();
				f.setIdPejabat(rs.getLong(1));
				f.setKodPejabat(rs.getString(2));
				f.setNamaPejabat(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// ADDED BY HIDAYAH 11022010
	public static Vector<Tblppthakmilik> getHakmilikSementara(String id_permohonan) throws Exception {
		String key = "DB.getHakmilik" + id_permohonan;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblppthakmilik>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = " SELECT DISTINCT A.ID_HAKMILIK, A.NO_HAKMILIK FROM TBLPPTHAKMILIK A WHERE A.ID_PERMOHONAN = "
					+ id_permohonan + " ";
			// String sql =
			// " SELECT DISTINCT A.ID_HAKMILIK, A.NO_HAKMILIK FROM TBLPPTHAKMILIK a, TBLPPTHAKMILIKPB B, TBLPPTPIHAKBERKEPENTINGAN C WHERE A.ID_HAKMILIK = B.ID_HAKMILIK AND B.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN AND A.ID_HAKMILIK = '"+
			// id_hakmilik +"' AND C.ID_PIHAKBERKEPENTINGAN = '"+
			// id_pihakberkepentingan +"' ";
			myLogger.info("getHakmilik 111 :: " + sql);
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblppthakmilik> v = new Vector<Tblppthakmilik>();
				Tblppthakmilik s = null;
				while (rs.next()) {
					s = new Tblppthakmilik();
					s.setIdHakmilik(rs.getLong(1));
					s.setNoHakmilik(rs.getString(2));
					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujkategoripemohon> getKategoriPengaduABP() throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select id_Kategoripemohon, kod_Kategoripemohon, keterangan from tblrujkategoripemohon"
				+ " where id_Kategoripemohon in (1,2,3,9)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujkategoripemohon> v = new Vector<Tblrujkategoripemohon>();
			Tblrujkategoripemohon s = null;
			while (rs.next()) {
				s = new Tblrujkategoripemohon();
				s.setIdKategoripemohon(rs.getLong(1));
				s.setKodKategoripemohon(rs.getString(2));
				s.setKeterangan(rs.getString(3));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// ADDED BY HIDAYAH 17022010

	public static Vector<Hashtable<String, Comparable>> getPB(String idHakmilik) throws Exception {
		String key = "DB.getPB" + idHakmilik;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Hashtable<String, Comparable>>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = " SELECT B.ID_HAKMILIKPB,A.NAMA_PB" +

			" FROM TBLPPTPIHAKBERKEPENTINGAN A," + " TBLPPTHAKMILIKPB B," + " TBLPPTHAKMILIK C,"
					+
					// " TBLPPTKEHADIRAN D"+
					" TBLRUJJENISPB E" +

					" WHERE A.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN" + " AND C.ID_HAKMILIK = B.ID_HAKMILIK"
					+ " AND E.ID_JENISPB = A.ID_JENISPB" + " AND E.KOD_JENIS_PB NOT IN ('WJ','PG','PS')" +
					// " AND B.ID_HAKMILIKPB = D.ID_HAKMILIKPB(+)" +
					// " AND B.ID_HAKMILIKPB NOT IN (D.ID_HAKMILIKPB)"+
					" AND B.ID_HAKMILIK = '" + idHakmilik + "'";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
				Hashtable<String, Comparable> h;
				while (rs.next()) {
					h = new Hashtable<String, Comparable>();
					h.put("ID_HAKMILIKPB", rs.getLong("ID_HAKMILIKPB"));
					h.put("NAMA_PB", rs.getString("NAMA_PB"));
					v.addElement(h);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblpptpihakberkepentingan> getPBbyHakmilik(String idhakmilik) throws Exception {
		Db db = null;

		String sql = "SELECT DISTINCT a.id_pihakberkepentingan, b.nama_pb, a.id_hakmilik, a.id_jenispb ";
		sql += " FROM Tblppthakmilik h, Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
		sql += " WHERE a.id_pihakberkepentingan = b.id_pihakberkepentingan(+) ";
		sql += " AND a.id_hakmilik = h.id_hakmilik";
		sql += " AND b.nama_pb is not null ";
		sql += " AND a.id_hakmilik = '" + idhakmilik + "'";
		sql += " AND a.id_jenispb not in (40,41,42)";
		sql += " ORDER BY b.nama_pb";
		myLogger.info("SQL :: " + sql);
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpptpihakberkepentingan> list = new Vector<Tblpptpihakberkepentingan>();
			Tblpptpihakberkepentingan f = null;
			while (rs.next()) {
				f = new Tblpptpihakberkepentingan();
				f.setIdPihakberkepentingan(rs.getLong(1));
				f.setNamaPb(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public static Vector<Tblpptpihakberkepentingan> getPBbyPermohonan(String idpermohonan) throws Exception {
		Db db = null;

		String sql = "SELECT DISTINCT d.id_pihakberkepentingan, d.nama_pb ";
		sql += " FROM tblpptpermohonan a, tblppthakmilik b, tblppthakmilikpb c, tblpptpihakberkepentingan d ";
		sql += " WHERE b.id_permohonan = a.id_permohonan ";
		sql += " AND c.id_hakmilik = b.id_hakmilik ";
		sql += " AND c.id_pihakberkepentingan = d.id_pihakberkepentingan ";
		sql += " AND c.id_jenispb not in (40,41,42)";
		sql += " AND b.id_permohonan = '" + idpermohonan + "'";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpptpihakberkepentingan> list = new Vector<Tblpptpihakberkepentingan>();
			Tblpptpihakberkepentingan f = null;
			while (rs.next()) {
				f = new Tblpptpihakberkepentingan();
				f.setIdPihakberkepentingan(rs.getLong(1));
				f.setNamaPb(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblppthakmilik> getHakmilikByPermohonan(String idpermohonan) throws Exception {
		Db db = null;

		String sql = "SELECT DISTINCT id_hakmilik,no_hakmilik " + " FROM tblppthakmilik where id_permohonan = '" + idpermohonan
				+ "'" + " ORDER BY lpad(no_hakmilik,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblppthakmilik> list = new Vector<Tblppthakmilik>();
			Tblppthakmilik f = null;
			while (rs.next()) {
				f = new Tblppthakmilik();
				f.setIdHakmilik(rs.getLong(1));
				f.setNoHakmilik(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujnegeri> getNegeriMampu1() throws Exception {
		String key = "DBgetNegeriMampu";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null)
			return ((Vector<Tblrujnegeri>) cachedObject.getObjectValue());

		Db db = null;
		String sql = "Select id_Negeri,kod_Mampu,nama_Negeri from tblrujnegeri order by kod_mampu ";
		try {
			Vector localVector1;
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
			Tblrujnegeri s = null;
			while (rs.next()) {
				s = new Tblrujnegeri();
				s.setIdNegeri(Long.valueOf(rs.getLong(1)));
				s.setKodMampu(rs.getString(2));
				s.setNamaNegeri(rs.getString(3));

				v.addElement(s);
			}
			myCache.put(new Element(key, v));
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblppthakmilik> getHakmilikByPermohonanWithSiasatan(String idpermohonan) throws Exception {
		Db db = null;

		String sql = "SELECT DISTINCT a.id_hakmilik, a.no_hakmilik "
				+ " FROM tblppthakmilik a, tblpptsiasatan b where b.id_hakmilik(+) = a.id_hakmilik " + " and a.id_permohonan = '"
				+ idpermohonan + "'" + " and b.id_siasatan is not null " + " ORDER BY lpad(a.no_hakmilik,10)";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblppthakmilik> list = new Vector<Tblppthakmilik>();
			Tblppthakmilik f = null;
			while (rs.next()) {
				f = new Tblppthakmilik();
				f.setIdHakmilik(rs.getLong(1));
				f.setNoHakmilik(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujnegeri> getNegeriMampu() throws Exception {
		String key = "DBgetNegeriMampu";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujnegeri>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "Select id_Negeri,kod_Mampu,nama_Negeri from tblrujnegeri" + " order by kod_mampu ";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
				Tblrujnegeri s = null;
				while (rs.next()) {
					s = new Tblrujnegeri();
					s.setIdNegeri(rs.getLong(1));
					s.setKodMampu(rs.getString(2));
					s.setNamaNegeri(rs.getString(3));

					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	// public static Vector getTujuanBayar() throws Exception {
	// String key = "DB.getTujuanBayar";
	// Element cachedObject = myCache.get(key);
	// if (cachedObject != null) {
	// return (Vector)cachedObject.getObjectValue();
	// } else {
	// Db db = null;
	// String sql =
	// "SELECT A.ID_JENISBAYARAN, A.KOD_JENIS_BAYARAN, A.KETERANGAN ";
	// sql += "FROM TBLRUJJENISBAYARAN A ";
	// sql += "WHERE A.ID_SEKSYEN = 3 ";
	// try {
	// db = new Db();
	// Statement stmt = db.getStatement();
	// ResultSet rs = stmt.executeQuery(sql);
	// Vector v = new Vector();
	// Hashtable h;
	// while (rs.next()) {
	// h = new Hashtable();
	// h.put("idJenisBayar",rs.getLong("ID_JENISBAYARAN"));
	// h.put("kodJenisBayar",rs.getLong("KOD_JENIS_BAYARAN"));
	// h.put("keterangan",rs.getString("KETERANGAN"));
	// v.addElement(h);
	// }
	// myCache.put(new Element(key, v));
	// return v;
	// } finally {
	// if (db != null)
	// db.close();
	// }
	// }
	// }

	// 18032010 tblrujdaerahpenggawa -Syah
	public static Vector<Tblrujdaerahpenggawa> getDaerahPenggawa() throws Exception {
		String key = "DB.getDaerahPenggawa";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujdaerahpenggawa>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "Select id_daerahpenggawa,kod_daerahpenggawa,nama_daerahpenggawa from tblrujdaerahpenggawa where kod_daerahpenggawa <> '0'";
			Vector<Tblrujdaerahpenggawa> list = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				list = new Vector<Tblrujdaerahpenggawa>();
				Tblrujdaerahpenggawa f = null;
				while (rs.next()) {
					f = new Tblrujdaerahpenggawa();
					f.setIdDaerahPenggawa(rs.getLong(1));
					f.setKodDaerahPenggawa(rs.getString(2));
					f.setNamaDaerahPenggawa(rs.getString(3));
					list.addElement(f);
				}
				myCache.put(new Element(key, list));
				return list;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	// select pegawai ppt by negeri 22/03/2010
	public static Vector<Users> getSenaraiPegawaiPPTByNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = " SELECT DISTINCT U.USER_ID AS ID_PEGAWAI, U.USER_NAME AS NAMA_PEGAWAI FROM USERS U, USERS_INTERNAL UI ";
		sql += " WHERE U.USER_ID = UI.USER_ID ";
		sql += " AND UI.ID_SEKSYEN = '1' ";

		if (idNegeri.equals("15") || idNegeri.equals("16")) {
			sql += " AND UI.ID_NEGERI = '14'";
		} else {
			sql += " AND UI.ID_NEGERI = '" + idNegeri + "'";
		}

		sql += " ORDER BY LPAD(U.USER_NAME,10) ";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Users> list = new Vector<Users>();
			Users f = null;
			while (rs.next()) {
				f = new Users();
				f.setUserId(rs.getLong(1));
				f.setUserName(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// select pegawai ppt 22/03/2010
	public static Vector<Users> getSenaraiPegawaiPPT() throws Exception {
		Db db = null;
		String sql = " SELECT DISTINCT U.USER_ID AS ID_PEGAWAI, U.USER_NAME AS NAMA_PEGAWAI FROM USERS U, USERS_INTERNAL UI ";
		sql += " WHERE U.USER_ID = UI.USER_ID ";
		sql += " AND UI.ID_SEKSYEN = '1' ";
		sql += " ORDER BY LPAD(U.USER_NAME,10) ";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Users> list = new Vector<Users>();
			Users f = null;
			while (rs.next()) {
				f = new Users();
				f.setUserId(rs.getLong(1));
				f.setUserName(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// ADDED BY HIDAYAH 04/03/2010
	public static Vector<Tblrujjenispb> getRujKodJenisPB2() throws Exception {
		Db db = null;
		String sql = "Select id_jenispb, kod_jenis_pb, keterangan from " + " tblrujjenispb where id_jenispb in (40,41,42) "
				+ " order by lpad(id_jenispb,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjenispb> v = new Vector<Tblrujjenispb>();
			Tblrujjenispb u = null;
			while (rs.next()) {
				u = new Tblrujjenispb();
				u.setIdJenispb(rs.getLong("id_jenispb"));
				u.setKodJenisPb(rs.getString("kod_jenis_pb"));
				u.setKeterangan(rs.getString("keterangan"));
				v.addElement(u);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// 26032010 get PB Pampasan by hakmilik -Syah
	public static Vector<Tblpptpihakberkepentingan> getPBPampasanByHakmilik(String idhakmilik, String id_terimabayaran, String id_pihakberkepentingan)
			throws Exception {

		Db db = null;
		String sql = "";
		String idpb = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT E.ID_PIHAKBERKEPENTINGAN ";
			sql += " FROM TBLPPTHAKMILIK A, TBLPPTHAKMILIKPB B, TBLPPTTERIMABAYARAN C, TBLPPTBAYARAN D, TBLPPTPIHAKBERKEPENTINGAN E, ";
			sql += " TBLPPTAWARD F ";
			sql += " WHERE B.ID_HAKMILIK = A.ID_HAKMILIK ";
			sql += " AND C.ID_HAKMILIK = A.ID_HAKMILIK ";
			sql += " AND B.ID_PIHAKBERKEPENTINGAN = E.ID_PIHAKBERKEPENTINGAN";
			sql += " AND D.ID_TERIMABAYARAN = C.ID_TERIMABAYARAN ";
			sql += " AND D.ID_HAKMILIKPB = B.ID_HAKMILIKPB ";
			sql += " AND F.ID_HAKMILIKPB = B.ID_HAKMILIKPB";
			sql += " AND C.ID_TERIMABAYARAN = '" + id_terimabayaran + "'";
			if (id_pihakberkepentingan != "") {
				sql += " AND F.ID_PIHAKBERKEPENTINGAN <> '" + id_pihakberkepentingan + "'";
			}

			// sql = "SELECT DISTINCT F.ID_PIHAKBERKEPENTINGAN ";
			// sql +=
			// " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, TBLPPTSIASATAN C, TBLPPTAWARD D, TBLPPTHAKMILIKPB E, TBLPPTPIHAKBERKEPENTINGAN F ";
			// sql += " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN ";
			// sql += " AND E.ID_HAKMILIK = B.ID_HAKMILIK ";
			// sql +=
			// " AND E.ID_PIHAKBERKEPENTINGAN = F.ID_PIHAKBERKEPENTINGAN ";
			// sql += " AND C.ID_HAKMILIK(+) = B.ID_HAKMILIK ";
			// sql += " AND D.ID_SIASATAN = C.ID_SIASATAN ";
			// sql += " AND D.ID_HAKMILIKPB(+) = E.ID_HAKMILIKPB ";
			// sql += " AND B.ID_HAKMILIK = '"+idhakmilik+"'";

			ResultSet rx = stmt.executeQuery(sql);
			int i = 0;
			while (rx.next()) {

				if (i == 0) {
					idpb += "ALL(" + rx.getString("ID_PIHAKBERKEPENTINGAN");
				} else {
					idpb += "," + rx.getString("ID_PIHAKBERKEPENTINGAN");
				}
				i++;

			}// close while

			if (idpb != "") {
				idpb += ")";
			}

			sql = "SELECT DISTINCT a.id_pihakberkepentingan, b.nama_pb, a.id_hakmilik ";
			sql += " FROM Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
			sql += " WHERE a.id_pihakberkepentingan = b.id_pihakberkepentingan(+) ";
			sql += " AND b.nama_pb is not null ";
			sql += " AND a.id_hakmilik = '" + idhakmilik + "'";
			if (idpb != "") {
				sql += " AND a.id_pihakberkepentingan <> " + idpb;
			}
			sql += " ORDER BY b.nama_pb";

			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpptpihakberkepentingan> list = new Vector<Tblpptpihakberkepentingan>();
			Tblpptpihakberkepentingan f = null;
			while (rs.next()) {
				f = new Tblpptpihakberkepentingan();
				f.setIdPihakberkepentingan(rs.getLong(1));
				f.setNamaPb(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// * HAKMILIK BY AP (PPT) - ELLY 04042010
	public static Vector<Tblppthakmilik> getHakmilikByAP(String id_hakmilik) throws Exception {
		String key = "DB.getHakmilikByAP" + id_hakmilik;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblppthakmilik>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = " SELECT DISTINCT A.ID_HAKMILIK, A.NO_HAKMILIK FROM TBLPPTHAKMILIK A WHERE A.ID_HAKMILIK = '"
					+ id_hakmilik + "' ";
			// myLogger.info("getHakmilik BY AP :: "+sql);
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblppthakmilik> v = new Vector<Tblppthakmilik>();
				Tblppthakmilik s = null;
				while (rs.next()) {
					s = new Tblppthakmilik();
					s.setIdHakmilik(rs.getLong(1));
					s.setNoHakmilik(rs.getString(2));
					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	// Pegawai setiap hakmilik by permohonan /syah /06042010
	public static Vector<Users> getPegawaiHMbyPermohonan(String idpermohonan, String urusan) throws Exception {
		Db db = null;
		String sql = "";

		if (urusan.equals("sementara") || urusan.equals("seksyen4")) {

			sql = " SELECT DISTINCT C.USER_ID AS ID_PEGAWAI, C.USER_NAME AS NAMA_PEGAWAI ";
			sql += " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, USERS C, TBLPPTAGIHANHM AGHM ";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN ";
			sql += " AND B.ID_HAKMILIK = AGHM.ID_HAKMILIK(+) ";
			sql += " AND (B.ID_PEGAWAI IS NOT NULL AND B.ID_PEGAWAI = C.USER_ID ";
			sql += " OR B.ID_PEGAWAI IS NULL AND AGHM.ID_HAKMILIK IS NOT NULL AND AGHM.USER_ID = C.USER_ID AND AGHM.BARIS = '2') ";
			sql += " AND A.ID_PERMOHONAN = '" + idpermohonan + "' ";
			sql += " ORDER BY C.USER_NAME ";

		} else {

			sql = " SELECT DISTINCT C.USER_ID AS ID_PEGAWAI, C.USER_NAME AS NAMA_PEGAWAI ";
			sql += " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, USERS C, TBLPPTAGIHANHM AGHM ";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN ";
			sql += " AND B.ID_HAKMILIK = AGHM.ID_HAKMILIK  ";
			sql += "  AND A.ID_PERMOHONAN = '" + idpermohonan + "' ";
			sql += "  AND AGHM.USER_ID = C.USER_ID ";
			sql += "  AND AGHM.BARIS = '2' ";
			sql += " ORDER BY C.USER_NAME  ";

		}

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			myLogger.info("SQL : "+sql);
			Vector<Users> list = new Vector<Users>();
			Users f = null;
			while (rs.next()) {
				f = new Users();
				f.setUserId(rs.getLong(1));
				f.setUserName(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}// close get Pegawai HM by Permohonan

	public static Vector<Tblrujlot> getUnitPT() throws Exception {
		String key = "DB.getUnitPT";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujlot>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "";
			Vector<Tblrujlot> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT ID_LOT, KOD_LOT, KETERANGAN FROM " + " TBLRUJLOT " + " WHERE ID_LOT <> '1' "
						+ " ORDER BY LPAD(KOD_LOT,10)";

				ResultSet rs = stmt.executeQuery(sql);

				v = new Vector<Tblrujlot>();
				Tblrujlot l = null;
				while (rs.next()) {
					l = new Tblrujlot();
					l.setIdLot(rs.getLong("ID_LOT"));
					l.setKodLot(rs.getString("KOD_LOT"));
					l.setKeterangan(rs.getString("KETERANGAN"));
					v.addElement(l);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Hashtable<String, String>> getBahagianPBbyHakmilik(String id_hakmilik) throws Exception {
		Db db = null;

		String sql = "SELECT B.ID_BAHAGIANPB,C.NAMA_PB, A.ID_HAKMILIKPB ";
		sql += " FROM TBLPPTHAKMILIKPB A, TBLPPTBAHAGIANPB B, TBLPPTPIHAKBERKEPENTINGAN C ";
		sql += " WHERE A.ID_BAHAGIANPB = B.ID_BAHAGIANPB ";
		sql += " AND A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN ";
		sql += "  AND A.ID_HAKMILIKPB = (SELECT MIN(ID_HAKMILIKPB) FROM TBLPPTHAKMILIKPB WHERE TBLPPTHAKMILIKPB.ID_BAHAGIANPB = B.ID_BAHAGIANPB) ";
		sql += " AND A.ID_HAKMILIK = '" + id_hakmilik + "'";

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_BAHAGIANPB", rs.getString("ID_BAHAGIANPB") == null ? "" : rs.getString("ID_BAHAGIANPB"));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs.getString("NAMA_PB"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, String>> getPejabatJPPH() throws Exception {
		Db db = null;

		String sql = "SELECT A.ID_PEJABAT, A.KOD_PEJABAT, A.NAMA_PEJABAT, B.NAMA_DAERAH ";
		sql += " FROM TBLRUJPEJABAT A, TBLRUJDAERAH B ";
		sql += " WHERE A.ID_JENISPEJABAT = '3' ";
		sql += " AND A.ID_DAERAH = B.ID_DAERAH(+) ";
		sql += " AND A.ID_SEKSYEN = '1' ";

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEJABAT", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
				h.put("KOD_PEJABAT", rs.getString("KOD_PEJABAT") == null ? "" : rs.getString("KOD_PEJABAT"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, String>> getPejabatJPPHByNegeri(String id_negeri) throws Exception {
		Db db = null;

		String sql = "SELECT A.ID_PEJABAT, A.KOD_PEJABAT, A.NAMA_PEJABAT, B.NAMA_DAERAH, C.NAMA_NEGERI ";
		sql += " FROM TBLRUJPEJABAT A, TBLRUJDAERAH B, TBLRUJNEGERI C ";
		sql += " WHERE A.ID_JENISPEJABAT = '3' ";
		sql += " AND A.ID_DAERAH = B.ID_DAERAH(+) ";
		sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) ";
		sql += " AND A.ID_SEKSYEN = '1' ";

		// untuk wilayah labuan dan putrajaya under KL
		if (id_negeri.equals("15") || id_negeri.equals("16")) {
			sql += " AND A.ID_NEGERI = '14'";
		} else {
			sql += " AND A.ID_NEGERI = '" + id_negeri + "'";
		}

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEJABAT", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
				h.put("KOD_PEJABAT", rs.getString("KOD_PEJABAT") == null ? "" : rs.getString("KOD_PEJABAT"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// ---------------------------
	// k-mie, 19/05/2010
	// guna utk PFD (HTML.java - SelectStatusTindakan)
	public static Vector<Hashtable<String, Comparable>> getStatusTindakan() throws Exception {
		Db db = null;
		String sql = "Select id_statustindakan,status_tindakan" + " from tblpfdrujstatustindakan ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> s = null;
			while (rs.next()) {
				s = new Hashtable<String, Comparable>();
				s.put("id_statustindakan", rs.getLong("id_statustindakan"));
				s.put("status_tindakan", rs.getString("status_tindakan") == null ? "" : rs.getString("status_tindakan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// ---------------------------

	// BY ELLY (PHP) 24062010
	public static Vector<Tblrujbulan> getBulan() throws Exception {
		String key = "DBgetBulan";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujbulan>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = " Select id_bulan,kod_bulan,nama_bulan from tblrujbulan ";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujbulan> v = new Vector<Tblrujbulan>();
				Tblrujbulan s = null;
				while (rs.next()) {
					s = new Tblrujbulan();
					s.setIdBulan(rs.getLong(1));
					s.setKodBulan(rs.getString(2));
					s.setNamaBulan(rs.getString(3));

					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	// add by Rizuan - register file -------
	public static Vector<Tblrujstatus> getStatusFailA() throws Exception {
		Db db = null;
		String sql = "Select id_Status,keterangan" + " from tblrujstatus where id_Seksyen = 6 and id_Status = 7";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				// s.setTarafKeselamatan(rs.getString("taraf_Keselamatan"));
				// s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusFailA(String id_Status) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Status");
			r.add("keterangan");
			// r.add("taraf_Keselamatan");
			// r.add("id_Status","7");

			if (id_Status != null)
				r.add("id_Status", id_Status);

			sql = r.getSQLSelect("tblrujstatus");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				// s.setTarafKeselamatan(rs.getString("taraf_Keselamatan"));
				// s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusFailB() throws Exception {
		Db db = null;
		String sql = "Select id_Status,keterangan"
				+ " from tblrujstatus where id_Seksyen = 6 and id_Status NOT IN (125,126,129,130,131)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				// s.setTarafKeselamatan(rs.getString("taraf_Keselamatan"));
				// s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujstatus> getStatusFailB(String id_Status) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Status");
			r.add("keterangan");
			// r.add("taraf_Keselamatan");
			// r.add("id_Status","7");

			if (id_Status != null)
				r.add("id_Status", id_Status);

			sql = r.getSQLSelect("tblrujstatus");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				// s.setTarafKeselamatan(rs.getString("taraf_Keselamatan"));
				// s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujurusan> getUrusan1() throws Exception {
		String key = "DB.getUrusan";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujurusan>) cachedObject.getObjectValue();
		} else {
			Vector<Tblrujurusan> v = null;
			Db db = null;
			String sql = "Select id_urusan,kod_urusan,nama_urusan from " + " tblrujurusan order by nama_urusan asc";
			// + " tblrujurusan order by lpad(kod_urusan,10)";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujurusan>();
				Tblrujurusan u = null;
				while (rs.next()) {
					u = new Tblrujurusan();
					u.setIdUrusan(rs.getLong("id_urusan"));
					u.setKodUrusan(rs.getString("kod_urusan"));
					u.setNamaUrusan(rs.getString("nama_urusan"));
					v.addElement(u);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujurusan> getUrusanByIdSeksyen(String idSeksyenB) throws Exception {
		String key = "DB.getUrusanBySeksyen" + idSeksyenB;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujurusan>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			Vector<Tblrujurusan> list = null;
			String sql = "";
			// sql = "Select id_urusan,kod_urusan,nama_urusan from tblrujurusan"
			// + " where id_seksyen=" + idSeksyenB
			// + " ORDER BY lpad(kod_Daerah,10)";

			sql = "select distinct UR.ID_URUSAN, UR.KOD_URUSAN, UR.NAMA_URUSAN"
					+ " from tblrujsuburusan su, tblrujurusan ur, tblrujseksyen se"
					+ " where su.ID_SEKSYEN = se.ID_SEKSYEN and SU.ID_URUSAN = UR.ID_URUSAN" + " and su.ID_SEKSYEN ="
					+ idSeksyenB + " ORDER BY nama_urusan asc";

			// select distinct UR.NAMA_URUSAN, UR.ID_URUSAN
			// from tblrujsuburusan su, tblrujurusan ur, tblrujseksyen se
			// where su.ID_SEKSYEN = se.ID_SEKSYEN and SU.ID_URUSAN =
			// UR.ID_URUSAN
			// and su.ID_SEKSYEN = 3;

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				list = new Vector<Tblrujurusan>();
				Tblrujurusan f = null;
				while (rs.next()) {
					f = new Tblrujurusan();
					f.setIdUrusan(rs.getLong(1));
					f.setKodUrusan(rs.getString(2));
					f.setNamaUrusan(rs.getString(3));
					list.addElement(f);
				}
				myCache.put(new Element(key, list));
				return list;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujdaerah> getDaerah1() throws Exception {
		String key = "DB.getDaerah";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujdaerah>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "Select id_Daerah,kod_Daerah,nama_Daerah from tblrujdaerah";
			Vector<Tblrujdaerah> list = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				list = new Vector<Tblrujdaerah>();
				Tblrujdaerah f = null;
				while (rs.next()) {
					f = new Tblrujdaerah();
					f.setIdDaerah(rs.getLong(1));
					f.setKodDaerah(rs.getString(2));
					f.setNamaDaerah(rs.getString(3));
					list.addElement(f);
				}
				myCache.put(new Element(key, list));
				return list;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujdaerah> getDaerahByIdDaerah(String socDaerahB) throws Exception {
		String key = "DB.getDaerahByIdDaerah" + socDaerahB;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujdaerah>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			Vector<Tblrujdaerah> list = null;
			String sql = "";
			sql = "Select id_Daerah,kod_Daerah,nama_Daerah from tblrujdaerah" + " where id_daerah=" + socDaerahB
					+ " ORDER BY lpad(kod_Daerah,10)";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				list = new Vector<Tblrujdaerah>();
				Tblrujdaerah f = null;
				while (rs.next()) {
					f = new Tblrujdaerah();
					f.setIdDaerah(rs.getLong(1));
					f.setKodDaerah(rs.getString(2));
					f.setNamaDaerah(rs.getString(3));
					list.addElement(f);
				}
				myCache.put(new Element(key, list));
				return list;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujsuburusan> getSuburusanByIdSuburusan(String socSuburusanB) throws Exception {
		String key = "DB.getSuburusanByIdSuburusan" + socSuburusanB;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujsuburusan>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			Vector<Tblrujsuburusan> list = null;
			String sql = "";
			sql = "Select id_suburusan,kod_suburusan,nama_suburusan from tblrujsuburusan" + " where id_suburusan="
					+ socSuburusanB + " ORDER BY lpad(kod_suburusan,10)";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				list = new Vector<Tblrujsuburusan>();
				Tblrujsuburusan f = null;
				while (rs.next()) {
					f = new Tblrujsuburusan();
					f.setIdSuburusan(rs.getLong(1));
					f.setKodSuburusan(rs.getString(2));
					f.setNamaSuburusan(rs.getString(3));
					list.addElement(f);
				}
				myCache.put(new Element(key, list));
				return list;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujseksyen> getSeksyenPAR(String idSeksyen) throws Exception {
		Db db = null;
		String sql = " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Seksyen");
			r.add("kod_Seksyen");
			r.add("nama_Seksyen");
			// r.add("id_Urusan");

			if (idSeksyen != null)
				r.add("id_Seksyen", idSeksyen);

			sql = r.getSQLSelect("tblrujseksyen");
			ResultSet rs = stmt.executeQuery(sql);

			Vector<Tblrujseksyen> v = new Vector<Tblrujseksyen>();
			Tblrujseksyen s = null;
			while (rs.next()) {
				s = new Tblrujseksyen();
				s.setIdSeksyen(rs.getLong("id_Seksyen"));
				s.setKodSeksyen(rs.getString("kod_Seksyen"));
				s.setNamaSeksyen(rs.getString("nama_Seksyen"));
				// s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getPARByIdSeksyen(String idSeksyen) throws Exception {
		Db db = null;
		Vector<Hashtable<String, Comparable>> v = null;
		String sql = "";
		try {

			db = new Db();
			Statement stmt = db.getStatement();

			if (idSeksyen.equalsIgnoreCase("8") || idSeksyen.equalsIgnoreCase("9")) {
				sql = "select user_id,user_name from users where user_id in (select user_id from users_internal where id_seksyen ='"
						+ idSeksyen + "' and id_jawatan = 21)";
			} else {
				sql = "select user_id,user_name from users where user_id in (select user_id from users_internal where id_seksyen ='"
						+ idSeksyen + "' and id_jawatan = 28)";
			}

			ResultSet rs = stmt.executeQuery(sql);
			v = new Vector<Hashtable<String, Comparable>>();

			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("ID_PEGAWAI", rs.getLong("user_id"));
				h.put("NAMA_PEGAWAI", rs.getString("user_name") == null ? "" : rs.getString("user_name").toUpperCase());
				v.addElement(h);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}

	public static Vector<Hashtable<String, String>> getPARById(String socPAR) {
		Db db = null;
		Vector<Hashtable<String, String>> v = null;
		String sql = "";
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			sql = "select user_id,user_name from users where user_id ='" + socPAR + "'";
			ResultSet rs = stmt.executeQuery(sql);
			v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEGAWAI", rs.getString("user_id") == null ? "" : rs.getString("user_id"));
				h.put("NAMA_PEGAWAI", rs.getString("user_name") == null ? "" : rs.getString("user_name").toUpperCase());
				v.addElement(h);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}

	public static Vector<Hashtable> getSubSubUrusanByIdSubUrusan(String selectSubUrusanB) throws Exception {

		Db db = null;
		Vector<Hashtable> v = null;
		String sql = "";
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			sql = "select C.ID_SUBSUBURUSAN, C.KOD_SUBSUBURUSAN, C.NAMA_SUBSUBURUSAN from tblrujurusan a, tblrujsuburusan b, tblrujsubsuburusan c "
					+ "where A.ID_URUSAN = B.ID_URUSAN "
					+ "and B.ID_SUBURUSAN = C.ID_SUBURUSAN "
					+ "and B.ID_SUBURUSAN = '"
					+ selectSubUrusanB + "'" + " ORDER BY C.KOD_SUBSUBURUSAN asc";
			ResultSet rs = stmt.executeQuery(sql);
			v = new Vector<Hashtable>();

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_SUBSUBURUSAN", rs.getString("ID_SUBSUBURUSAN") == null ? "" : rs.getLong("ID_SUBSUBURUSAN"));
				h.put("KOD_SUBSUBURUSAN", rs.getString("KOD_SUBSUBURUSAN") == null ? "" : rs.getString("KOD_SUBSUBURUSAN")
						.toUpperCase());
				h.put("NAMA_SUBSUBURUSAN", rs.getString("NAMA_SUBSUBURUSAN") == null ? "" : rs.getString("NAMA_SUBSUBURUSAN")
						.toUpperCase());
				v.addElement(h);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return v;

	}

	public static Vector<Hashtable> getSubSubSubUrusanByIdSubSubUrusan(String selectSubSubUrusanC) throws Exception {

		Db db = null;
		Vector<Hashtable> v = null;
		String sql = "";
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			sql = "select C.ID_SUBSUBSUBURUSAN, C.KOD_SUBSUBSUBURUSAN, C.NAMA_SUBSUBSUBURUSAN from tblrujsubsuburusan b, tblrujsubsubsuburusan c "
					+ "where "
					+ "B.ID_SUBSUBURUSAN = C.ID_SUBSUBURUSAN "
					+ "and B.ID_SUBSUBURUSAN = '"
					+ selectSubSubUrusanC
					+ "'" + " ORDER BY C.KOD_SUBSUBSUBURUSAN asc";
			ResultSet rs = stmt.executeQuery(sql);
			v = new Vector<Hashtable>();

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_SUBSUBSUBURUSAN", rs.getString("ID_SUBSUBSUBURUSAN") == null ? "" : rs.getLong("ID_SUBSUBSUBURUSAN"));
				h.put("KOD_SUBSUBSUBURUSAN",
						rs.getString("KOD_SUBSUBSUBURUSAN") == null ? "" : rs.getString("KOD_SUBSUBSUBURUSAN").toUpperCase());
				h.put("NAMA_SUBSUBSUBURUSAN",
						rs.getString("NAMA_SUBSUBSUBURUSAN") == null ? "" : rs.getString("NAMA_SUBSUBSUBURUSAN").toUpperCase());
				v.addElement(h);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return v;

	}

	public static Vector<Tblrujurusan> getUrusanSeksyen() throws Exception {
		String key = "DB.getUrusanSeksyen";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujurusan>) cachedObject.getObjectValue();
		} else {
			Vector<Tblrujurusan> v = null;
			Db db = null;
			String sql = "Select id_urusan,kod_urusan,nama_urusan from "
					+ " tblrujurusan where id_negeri is null order by nama_urusan asc";
			// + " tblrujurusan order by lpad(kod_urusan,10)";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujurusan>();
				Tblrujurusan u = null;
				while (rs.next()) {
					u = new Tblrujurusan();
					u.setIdUrusan(rs.getLong("id_urusan"));
					u.setKodUrusan(rs.getString("kod_urusan"));
					u.setNamaUrusan(rs.getString("nama_urusan"));
					v.addElement(u);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujurusan> getUrusanArkib() throws Exception {
		String key = "DB.getUrusanSeksyen";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujurusan>) cachedObject.getObjectValue();
		} else {
			Vector<Tblrujurusan> v = null;
			Db db = null;
			String sql = "Select distinct a.id_urusan,a.kod_urusan,a.nama_urusan from tblrujurusan a, tblrujsuburusan b, tblrujsubsuburusan c where kod_urusan in ('100','200','300','400','500')"
					+ " and a.id_urusan = b.id_urusan and b.id_suburusan = c.id_suburusan order by lpad(kod_urusan,10)";
			// +
			// " and nama_urusan in ('PENTADBIRAN','PENGURUSAN TANAH DAN BANGUNAN','PENGURUSAN ASET','PENGURUSAN KEWANGAN','PENGURUSAN SUMBER MANUSIA') and id_negeri is null order by kod_urusan asc";
			// + " tblrujurusan order by lpad(kod_urusan,10)";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujurusan>();
				Tblrujurusan u = null;
				while (rs.next()) {
					u = new Tblrujurusan();
					u.setIdUrusan(rs.getLong("id_urusan"));
					u.setKodUrusan(rs.getString("kod_urusan"));
					u.setNamaUrusan(rs.getString("nama_urusan"));
					v.addElement(u);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujurusan> getUrusanNegeri(String id_negeri) throws Exception {
		String key = "DB.getUrusanNegeri";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujurusan>) cachedObject.getObjectValue();
		} else {
			Vector<Tblrujurusan> v = null;
			Db db = null;
			String sql = "Select id_urusan,kod_urusan,nama_urusan from ";

			if (id_negeri.equals("1") || id_negeri.equals("3") || id_negeri.equals("4") || id_negeri.equals("5")
					|| id_negeri.equals("6") || id_negeri.equals("8") || id_negeri.equals("10") || id_negeri.equals("14")) {
				sql = sql + " tblrujurusan where id_negeri = '" + id_negeri + "' order by nama_urusan asc";

			} else {
				sql = sql + " tblrujurusan where id_negeri is null order by nama_urusan asc";
			}
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujurusan>();
				Tblrujurusan u = null;
				while (rs.next()) {
					u = new Tblrujurusan();
					u.setIdUrusan(rs.getLong("id_urusan"));
					u.setKodUrusan(rs.getString("kod_urusan"));
					u.setNamaUrusan(rs.getString("nama_urusan"));
					v.addElement(u);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujunit> getUnitByNegeri(String id_Negeri) throws Exception {
		String key = "DB.getUnitByNegeri" + id_Negeri;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujunit>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			Vector<Tblrujunit> list = null;
			String sql = "";
			sql = "Select id_unit,nama_unit from tblrujunit" + " where id_negeri='" + id_Negeri + "'" + " ORDER BY id_unit desc";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				list = new Vector<Tblrujunit>();
				Tblrujunit f = null;
				while (rs.next()) {
					f = new Tblrujunit();
					f.setIdUnit(rs.getLong(1));
					f.setNamaUnit(rs.getString(2));
					list.addElement(f);
				}
				myCache.put(new Element(key, list));
				return list;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Hashtable<String, String>> getPegawaiUnitPelepasan() throws Exception {
		Db db = null;
		//BY AIN 09052017
		String sql = " SELECT DISTINCT A.USER_NAME, B.USER_ID, B.ID_JAWATAN, B.ID_NEGERI"
				+ " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C"
				+ " WHERE A.USER_ID = B.USER_ID"
				+ " AND B.ID_JAWATAN = C.ID_JAWATAN(+)"
				+ " AND A.USER_ROLE IN ('(PHP)UserPelepasan')"
				+ " AND B.FLAG_AKTIF = '1'"
				+ " AND B.ID_SEKSYEN = '4'"
				+ " AND B.ID_NEGERI = '16'"
				+ " UNION"
				+ " SELECT DISTINCT A.USER_NAME, B.USER_ID, B.ID_JAWATAN, B.ID_NEGERI"
				+ " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C, USER_ROLE D"
				+ " WHERE A.USER_ID = B.USER_ID"
				+ " AND B.ID_JAWATAN = C.ID_JAWATAN(+)"
				+ " AND A.USER_LOGIN = D.USER_ID"
				+ " AND D.ROLE_ID IN ('(PHP)UserPelepasan')"
				+ " AND B.FLAG_AKTIF = '1'"
				+ " AND B.ID_SEKSYEN = '4'"
				+ " AND B.ID_NEGERI = '16'"
				+ " ORDER BY USER_NAME ASC";

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEGAWAI", rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("NAMA_PEGAWAI", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, String>> getPegawaiUnitHasil() throws Exception {
		Db db = null;
		//BY AIN 09052017
		String sql = " SELECT DISTINCT A.USER_NAME, B.USER_ID, B.ID_JAWATAN, B.ID_NEGERI"
				+ " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C"
				+ " WHERE A.USER_ID = B.USER_ID"
				+ " AND B.ID_JAWATAN = C.ID_JAWATAN(+)"
				+ " AND A.USER_ROLE IN ('(PHP)UserHasil')"
				+ " AND B.FLAG_AKTIF = '1'"
				+ " AND B.ID_SEKSYEN = '4'"
				+ " AND B.ID_PEJABATJKPTG = '116'"
				+ " UNION"
				+ " SELECT DISTINCT A.USER_NAME, B.USER_ID, B.ID_JAWATAN, B.ID_NEGERI"
				+ " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C, USER_ROLE D"
				+ " WHERE A.USER_ID = B.USER_ID"
				+ " AND B.ID_JAWATAN = C.ID_JAWATAN(+)"
				+ " AND A.USER_LOGIN = D.USER_ID"
				+ " AND D.ROLE_ID IN ('(PHP)UserHasil')"
				+ " AND B.FLAG_AKTIF = '1'"
				+ " AND B.ID_SEKSYEN = '4'"
				+ " AND B.ID_PEJABATJKPTG = '116'"
				+ " ORDER BY USER_NAME ASC";

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEGAWAI", rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("NAMA_PEGAWAI", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, String>> getPegawaiUnitPenguatkuasaan() throws Exception {
		Db db = null;
		//BY AIN 25072017
		String sql = " SELECT DISTINCT A.USER_NAME, B.USER_ID, B.ID_JAWATAN, B.ID_NEGERI"
				+ " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C"
				+ " WHERE A.USER_ID = B.USER_ID"
				+ " AND B.ID_JAWATAN = C.ID_JAWATAN(+)"
				+ " AND A.USER_ROLE IN ('(PHP)UserPenguatkuasaan')"
				+ " AND B.FLAG_AKTIF = '1'"
				+ " AND B.ID_SEKSYEN = '4'"
				+ " AND B.ID_PEJABATJKPTG = '116'"
				+ " UNION"
				+ " SELECT DISTINCT A.USER_NAME, B.USER_ID, B.ID_JAWATAN, B.ID_NEGERI"
				+ " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C, USER_ROLE D"
				+ " WHERE A.USER_ID = B.USER_ID"
				+ " AND B.ID_JAWATAN = C.ID_JAWATAN(+)"
				+ " AND A.USER_LOGIN = D.USER_ID"
				+ " AND D.ROLE_ID IN ('(PHP)UserPenguatkuasaan')"
				+ " AND B.FLAG_AKTIF = '1'"
				+ " AND B.ID_SEKSYEN = '4'"
				+ " AND B.ID_PEJABATJKPTG = '116'"
				+ " ORDER BY USER_NAME ASC";

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEGAWAI", rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("NAMA_PEGAWAI", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, String>> getPegawaiUnitAPB() throws Exception {
		Db db = null;
		//BY AIN 25072017
		String sql = " SELECT DISTINCT A.USER_NAME, B.USER_ID, B.ID_JAWATAN, B.ID_NEGERI"
				+ " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C"
				+ " WHERE A.USER_ID = B.USER_ID"
				+ " AND B.ID_JAWATAN = C.ID_JAWATAN(+)"
				+ " AND A.USER_ROLE IN ('(PHP)UserAPB')"
				+ " AND B.FLAG_AKTIF = '1'"
				+ " AND B.ID_SEKSYEN = '4'"
				+ " UNION"
				+ " SELECT DISTINCT A.USER_NAME, B.USER_ID, B.ID_JAWATAN, B.ID_NEGERI"
				+ " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C, USER_ROLE D"
				+ " WHERE A.USER_ID = B.USER_ID"
				+ " AND B.ID_JAWATAN = C.ID_JAWATAN(+)"
				+ " AND A.USER_LOGIN = D.USER_ID"
				+ " AND D.ROLE_ID IN ('(PHP)UserAPB')"
				+ " AND B.FLAG_AKTIF = '1'"
				+ " AND B.ID_SEKSYEN = '4'"
				+ " ORDER BY USER_NAME ASC";

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEGAWAI", rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("NAMA_PEGAWAI", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, String>> getPegawaiUnitPenyewaanByNegeri(String idNegeri) throws Exception {
		Db db = null;
		//BY AIN 09052017
		String sql = " SELECT DISTINCT A.USER_NAME, B.USER_ID, B.ID_JAWATAN, B.ID_NEGERI"
				+ " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C"
				+ " WHERE A.USER_ID = B.USER_ID"
			    + " AND B.ID_JAWATAN = C.ID_JAWATAN(+)"
			    + " AND A.USER_ROLE IN ('(PHP)PYWPengarahHQ', '(PHP)PYWPenolongPengarahHQ',"
				+ " '(PHP)PYWPenolongPegawaiTanahHQ', '(PHP)PYWPengarahNegeri',"
				+ " '(PHP)PYWPenolongPengarahNegeri', '(PHP)PYWPenolongPegawaiTanahNegeri')"
			    + " AND B.FLAG_AKTIF = '1'"
			    + " AND B.ID_NEGERI = '" + idNegeri + "'"
			    + " AND B.ID_SEKSYEN = '4'"
			    + " AND C.ID_JAWATAN IN (4, 5, 9, 161738)"
			    + " UNION"
			    + " SELECT DISTINCT A.USER_NAME, B.USER_ID, B.ID_JAWATAN, B.ID_NEGERI"
			    + " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C, USER_ROLE D"
			    + " WHERE A.USER_ID = B.USER_ID"
			    + " AND B.ID_JAWATAN = C.ID_JAWATAN(+)"
			    + " AND A.USER_LOGIN = D.USER_ID"
			    + " AND D.ROLE_ID IN ('(PHP)PYWPengarahHQ', '(PHP)PYWPenolongPengarahHQ',"
				+ " '(PHP)PYWPenolongPegawaiTanahHQ', '(PHP)PYWPengarahNegeri',"
				+ " '(PHP)PYWPenolongPengarahNegeri', '(PHP)PYWPenolongPegawaiTanahNegeri')"
			    + " AND B.FLAG_AKTIF = '1'"
			    + " AND B.ID_NEGERI = '" + idNegeri + "'"
			    + " AND B.ID_SEKSYEN = '4'"
			    + " AND C.ID_JAWATAN IN (4, 5, 9, 161738)"
			    + " ORDER BY USER_NAME ASC";

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEGAWAI", rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("NAMA_PEGAWAI", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// GET CARA BAYAR HASIL (PHP) - PEJE
	public static Vector<Hashtable<String, String>> getCaraBayaran() throws Exception {
		Db db = null;
		String sql = "Select id_carabayar,kod_carabayar,keterangan"
				+ " from tblrujcarabayar where id_carabayar in (1,3,4,5,9,11) order by id_carabayar ";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_CARABAYAR", rs.getString("ID_CARABAYAR") == null ? "" : rs.getString("ID_CARABAYAR"));
				h.put("KOD_CARABAYAR", rs.getString("KOD_CARABAYAR") == null ? "" : rs.getString("KOD_CARABAYAR"));
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpptpihakberkepentingan> getPBbyHakmilikWithIdAward(String idhakmilik) throws Exception {

		Db db = null;

		String sql = "SELECT DISTINCT a.id_pihakberkepentingan, b.nama_pb, a.id_hakmilik, a.id_jenispb ";
		sql += " FROM Tblppthakmilik h, Tblppthakmilikpb a, Tblpptpihakberkepentingan b, Tblpptaward c ";
		sql += " WHERE a.id_pihakberkepentingan = b.id_pihakberkepentingan(+) ";
		sql += " AND a.id_hakmilik = h.id_hakmilik";
		sql += " AND c.id_hakmilikpb = a.id_hakmilikpb ";
		sql += " AND b.nama_pb is not null ";
		sql += " AND a.id_hakmilik = '" + idhakmilik + "'";
		sql += " AND a.id_jenispb not in (40,41,42)";
		sql += " ORDER BY b.nama_pb";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpptpihakberkepentingan> list = new Vector<Tblpptpihakberkepentingan>();
			Tblpptpihakberkepentingan f = null;
			while (rs.next()) {
				f = new Tblpptpihakberkepentingan();
				f.setIdPihakberkepentingan(rs.getLong(1));
				f.setNamaPb(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}// close getPBbyHakmilikWithIdAward

	public static Vector<Tblpptpihakberkepentingan> getPBbyHakmilikWithPenerimaanBorangH(String idhakmilik) throws Exception {

		Db db = null;

		String sql = "SELECT DISTINCT a.id_pihakberkepentingan, b.nama_pb, a.id_hakmilik, a.id_jenispb ";
		sql += " FROM Tblppthakmilik h, Tblppthakmilikpb a, Tblpptpihakberkepentingan b, Tblpptaward c ";
		sql += " WHERE a.id_pihakberkepentingan = b.id_pihakberkepentingan(+) ";
		sql += " AND a.id_hakmilik = h.id_hakmilik";
		sql += " AND c.id_hakmilikpb = a.id_hakmilikpb ";
		sql += " AND b.nama_pb is not null ";
		sql += " AND (select count(*)from tblpptborangh bh, tblpptborangg bg where bh.id_hakmilikpb = a.id_hakmilikpb and bh.id_borangg = bg.id_borangg) > '0' ";
		sql += " AND a.id_hakmilik = '" + idhakmilik + "'";
		sql += " AND a.id_jenispb not in (40,41,42)";
		sql += " ORDER BY b.nama_pb";


		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpptpihakberkepentingan> list = new Vector<Tblpptpihakberkepentingan>();
			Tblpptpihakberkepentingan f = null;
			while (rs.next()) {
				f = new Tblpptpihakberkepentingan();
				f.setIdPihakberkepentingan(rs.getLong(1));
				f.setNamaPb(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}// close getPBbyHakmilikWithPenerimaanBorangH

	public static Vector<Hashtable<String, Comparable>> getPengarahBySeksyen(String idSeksyen) throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT USERS.USER_ID, UPPER(USERS.USER_NAME) AS NAMA FROM USERS, USERS_INTERNAL WHERE USERS.USER_ID = USERS_INTERNAL.USER_ID AND USERS_INTERNAL.ID_JAWATAN in (2,4) AND USERS_INTERNAL.ID_SEKSYEN = '"
				+ idSeksyen + "'";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("USER_ID"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// added by hilda 10/08/2010
	public static Vector<Hashtable<String, Comparable>> getLuasAPB() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT ID_LUAS,KOD_LUAS,KETERANGAN from " + "TBLRUJLUAS WHERE " + "ID_LUAS IN (1,2)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_LUAS"));
				h.put("kod", rs.getString("KOD_LUAS") == null ? "" : rs.getString("KOD_LUAS").toUpperCase());
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// 20092010 Jenis Bayaran FPX -Syah
	public static Vector<Tblrujjenisbayaran> getJenisBayaranFPX() throws Exception {
		String key = "DB.getJenisBayaranFPX";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujjenisbayaran>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "SELECT ID_JENISBAYARAN, KOD_JENIS_BAYARAN, KETERANGAN ";
			sql += "FROM TBLRUJJENISBAYARAN WHERE ID_JENISBAYARAN IN(9,10,14,19,21) ";
			sql += "ORDER BY KOD_JENIS_BAYARAN ";
			Vector<Tblrujjenisbayaran> list = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				list = new Vector<Tblrujjenisbayaran>();
				Tblrujjenisbayaran f = null;
				while (rs.next()) {
					f = new Tblrujjenisbayaran();
					f.setIdJenisbayaran(rs.getLong(1));
					f.setKodJenisBayar(rs.getString(2));
					f.setKeterangan(rs.getString(3));
					list.addElement(f);
				}
				myCache.put(new Element(key, list));
				return list;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	// 23092010 Pilihan sub urusan PPK -Syah
	public static Vector<Tblrujsuburusan> getSuburusanPPK() throws Exception {
		String key = "DBgetSuburusanPPK";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujsuburusan>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "SELECT DISTINCT ID_SUBURUSAN, KOD_SUBURUSAN, NAMA_SUBURUSAN, ID_URUSAN ";
			sql += "FROM TBLRUJSUBURUSAN ";
			sql += "WHERE ID_SUBURUSAN IN (59,60) ";
			sql += "ORDER BY KOD_SUBURUSAN ";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujsuburusan> v = new Vector<Tblrujsuburusan>();
				Tblrujsuburusan s = null;
				while (rs.next()) {
					s = new Tblrujsuburusan();
					s.setIdSuburusan(rs.getLong(1));
					s.setKodSuburusan(rs.getString(2));
					s.setNamaSuburusan(rs.getString(3));
					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	// 23092010 Pilihan unit dengan nama daerah PPK -Syah
	public static Vector<Hashtable> getUnitJKPTGWithDaerah() throws Exception {

		Db db = null;

		String sql = "SELECT DISTINCT A.ID_PEJABATJKPTG, A.NAMA_PEJABAT, B.NAMA_DAERAH ";
		sql += "FROM TBLRUJPEJABATJKPTG A, TBLRUJDAERAH B ";
		sql += "WHERE A.ID_DAERAH = B.ID_DAERAH(+) ";
		sql += "AND A.ID_SEKSYEN = '2' ";
		sql += "AND A.ID_JENISPEJABAT = '22' ";
		sql += "ORDER BY A.NAMA_PEJABAT,B.NAMA_DAERAH ";

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable> v = new Vector<Hashtable>();

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_PEJABATJKPTG", rs.getLong("ID_PEJABATJKPTG") == 0 ? "" : rs.getLong("ID_PEJABATJKPTG"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}// close

	// 23092010 Pilihan unit dengan nama daerah by negeri PPK -Syah
	public static Vector<Hashtable> getUnitJKPTGWithDaerahByNegeri(String id_negeri) throws Exception {

		Db db = null;

		String sql = "SELECT DISTINCT A.ID_PEJABATJKPTG, A.NAMA_PEJABAT, B.NAMA_DAERAH ";
		sql += "FROM TBLRUJPEJABATJKPTG A, TBLRUJDAERAH B ";
		sql += "WHERE A.ID_DAERAH = B.ID_DAERAH(+) ";
		sql += "AND A.ID_SEKSYEN = '2' ";
		sql += "AND A.ID_JENISPEJABAT = '22' ";
		sql += "AND A.ID_NEGERI = '" + id_negeri + "' ";
		sql += "ORDER BY A.NAMA_PEJABAT,B.NAMA_DAERAH ";

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable> v = new Vector<Hashtable>();

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_PEJABATJKPTG", rs.getLong("ID_PEJABATJKPTG") == 0 ? "" : rs.getLong("ID_PEJABATJKPTG"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}// close

	public static Vector<Hashtable<String, Comparable>> getPejabatJPPHPelepasan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT TBLRUJPEJABAT.ID_PEJABAT, TBLRUJPEJABAT.KOD_PEJABAT, TBLRUJPEJABAT.NAMA_PEJABAT"
				+ " FROM TBLPHPHAKMILIKPERMOHONAN, TBLPHPHAKMILIK, TBLRUJPEJABATURUSAN, TBLRUJPEJABAT"
				+ " WHERE TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKPERMOHONAN = TBLPHPHAKMILIK.ID_HAKMILIKPERMOHONAN"
				+ " AND TBLRUJPEJABATURUSAN.ID_JENISPEJABAT = '3'"
				+ " AND TBLPHPHAKMILIK.ID_NEGERI = TBLRUJPEJABATURUSAN.ID_NEGERIURUS"
				+ " AND TBLPHPHAKMILIK.ID_DAERAH = TBLRUJPEJABATURUSAN.ID_DAERAHURUS"
				+ " AND TBLRUJPEJABATURUSAN.ID_PEJABATJKPTG = TBLRUJPEJABAT.ID_PEJABAT"
				+ " AND TBLPHPHAKMILIKPERMOHONAN.ID_PERMOHONAN = '" + idPermohonan + "'"

				+ " UNION"

				+ " SELECT TBLRUJPEJABAT.ID_PEJABAT, TBLRUJPEJABAT.KOD_PEJABAT, TBLRUJPEJABAT.NAMA_PEJABAT"
				+ " FROM TBLPHPTANAHGANTIPLPSN, TBLRUJPEJABATURUSAN, TBLRUJPEJABAT"
				+ " WHERE TBLRUJPEJABATURUSAN.ID_JENISPEJABAT = '3'"
				+ " AND TBLPHPTANAHGANTIPLPSN.ID_NEGERI = TBLRUJPEJABATURUSAN.ID_NEGERIURUS"
				+ " AND TBLPHPTANAHGANTIPLPSN.ID_DAERAH = TBLRUJPEJABATURUSAN.ID_DAERAHURUS"
				+ " AND TBLRUJPEJABATURUSAN.ID_PEJABATJKPTG = TBLRUJPEJABAT.ID_PEJABAT"
				+ " AND TBLPHPTANAHGANTIPLPSN.ID_PERMOHONAN = '" + idPermohonan + "'";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_PEJABAT"));
				h.put("kod", rs.getString("KOD_PEJABAT") == null ? "" : rs.getString("KOD_PEJABAT").toUpperCase());
				h.put("keterangan", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// ADDED BY DAYAH 10/01/2011

	public static Vector<Tblppkrujunit> getPegawaiPengendaliByDaerah(String id_jkptg, String id_daerah) throws Exception {
		Db db = null;
		String sql = "";
		sql = "Select a.id_unitpsk,a.kod,a.nama_pegawai from tblppkrujunit a, tblrujpejabaturusan b" + " where a.id_jkptg= '"
				+ id_jkptg + "'" + " and b.id_pejabatjkptg = a.id_jkptg" + " and b.id_daerahurus ='" + id_daerah + "'"
				+ " and a.status_peg = '1'" + " ORDER BY lpad(id_unitpsk,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblppkrujunit> list = new Vector<Tblppkrujunit>();
			Tblppkrujunit f = null;
			while (rs.next()) {
				f = new Tblppkrujunit();
				f.setidunitpk(rs.getLong(1));
				f.setkod(rs.getString(2));
				f.setnamapegawai(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujdaerah> getDaerahByNegeriKOD(String idnegeri, String flag) throws Exception {

		Db db = null;
		Vector<Tblrujdaerah> list = null;
		String sql = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DAERAH,NAMA_DAERAH, ";
			if (flag.equals("1")) {
				sql += "CASE ";
				sql += "WHEN KOD_DAERAH_PTG IS NOT NULL THEN KOD_DAERAH_PTG ";
				sql += "ELSE KOD_DAERAH ";
				sql += "END ";
			} else {
				sql += "KOD_DAERAH ";
			}

			sql += "AS KOD_DAERAH_PILIHAN ";
			sql += "FROM TBLRUJDAERAH ";
			sql += "WHERE ID_NEGERI = '" + idnegeri + "' ";
			sql += "ORDER BY lpad(KOD_DAERAH_PILIHAN,10) ";

			ResultSet rs = stmt.executeQuery(sql);
			list = new Vector<Tblrujdaerah>();
			Tblrujdaerah f = null;
			while (rs.next()) {
				f = new Tblrujdaerah();
				f.setIdDaerah(rs.getLong(1));
				f.setKodDaerah(rs.getString("KOD_DAERAH_PILIHAN"));
				f.setNamaDaerah(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public static Vector<Tblrujdaerah> getDaerahKOD(String flag) throws Exception {

		Db db = null;
		String sql = "";
		Vector<Tblrujdaerah> list = null;

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DAERAH,NAMA_DAERAH,ID_NEGERI, ";
			if (flag.equals("1")) {
				sql += "CASE ";
				sql += "WHEN KOD_DAERAH_PTG IS NOT NULL THEN KOD_DAERAH_PTG ";
				sql += "ELSE KOD_DAERAH ";
				sql += "END ";
			} else {
				sql += "KOD_DAERAH ";
			}

			sql += "AS KOD_DAERAH_PILIHAN ";
			sql += "FROM TBLRUJDAERAH ";
			sql += "WHERE KOD_DAERAH <> '0' ";
			sql += "ORDER BY lpad(ID_NEGERI,10), lpad(KOD_DAERAH_PILIHAN,10) ";

			ResultSet rs = stmt.executeQuery(sql);
			list = new Vector<Tblrujdaerah>();
			Tblrujdaerah f = null;
			while (rs.next()) {
				f = new Tblrujdaerah();
				f.setIdDaerah(rs.getLong(1));
				f.setKodDaerah(rs.getString("KOD_DAERAH_PILIHAN"));
				f.setNamaDaerah(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public static Vector<Tblrujdaerah> getDaerahLaporanByIdPejabatJKPTG(String id_pejabatjkptg) throws Exception {

		Db db = null;

		String sql = " SELECT D.ID_DAERAH,D.KOD_DAERAH,D.NAMA_DAERAH";
		sql += " FROM TBLRUJPEJABATJKPTG P,TBLRUJDAERAH D";
		sql += " WHERE P.ID_SEKSYEN = '1' AND P.ID_NEGERI = D.ID_NEGERI AND  P.ID_JENISPEJABAT IN ('22','21')";

		if (!id_pejabatjkptg.equals("16")) {
			sql += " AND P.ID_PEJABATJKPTG = '" + id_pejabatjkptg + "'";
		}

		sql += " AND D.KOD_DAERAH <> '0' ORDER BY P.ID_NEGERI,D.KOD_DAERAH ASC";
myLogger.info("SQL LISTxxx -- :"+sql);
		Vector<Tblrujdaerah> list = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			list = new Vector<Tblrujdaerah>();
			Tblrujdaerah f = null;
			while (rs.next()) {
				f = new Tblrujdaerah();
				f.setIdDaerah(rs.getLong(1));
				f.setKodDaerah(rs.getString(2));
				f.setNamaDaerah(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}

	// add 11/11/2010
	public static Vector<Hashtable<String, Comparable>> getPejabatJKPTGLaporan() throws Exception {
		Db db = null;

			//String sql = " SELECT ID_PEJABATJKPTG,NAMA_PEJABAT,";
			//sql += " CASE";
			//sql += " WHEN ID_JENISPEJABAT = '22' THEN 'UPT'";
			//sql += " WHEN ID_JENISPEJABAT = '21' THEN 'IBU PEJABAT'";
			//sql += " END AS JENIS_PEJABAT";
			//sql += " FROM TBLRUJPEJABATJKPTG";
			//sql += " WHERE ID_JENISPEJABAT IN ('22','21')";
			//sql += " AND ID_SEKSYEN = '1'";
			String sql = "SELECT A.ID_PEJABATJKPTG , A.ID_NEGERI, N.NAMA_NEGERI" +
					" FROM TBLRUJPEJABATJKPTG A, TBLRUJNEGERI N" +
					" WHERE A.ID_NEGERI = N.ID_NEGERI " +
					" AND A.ID_SEKSYEN ='1' " +
					" AND A.ID_JENISPEJABAT IN ('22','21')";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
				Hashtable<String, Comparable> h;
				while (rs.next()) {
					h = new Hashtable<String, Comparable>();
					h.put("id_pejabatjkptg", rs.getLong("ID_PEJABATJKPTG"));
					h.put("id_negeri", rs.getLong("ID_NEGERI"));
					h.put("nama_negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
					//h.put("nama_pejabat", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
					//h.put("jenis_pejabat", rs.getString("JENIS_PEJABAT") == null ? "" : rs.getString("JENIS_PEJABAT").toUpperCase());
					v.addElement(h);
				}
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}


	public static Vector<Tblrujunit> getUnit() throws Exception {
		/* Azam change order by id_seksyen on May,13 */
		Db db = null;
		String sql = "Select id_unit,nama_unit" + " from tblrujunit order by id_unit ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujunit> v = new Vector<Tblrujunit>();
			Tblrujunit s = null;
			while (rs.next()) {
				s = new Tblrujunit();
				s.setIdUnit(rs.getLong("id_unit"));
				// s.setKodSeksyen(rs.getString("kod_Seksyen"));
				s.setNamaUnit(rs.getString("nama_unit"));
				// s.setIdUrusan(rs.getLong("id_Urusan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// [PPT] Untuk KL sahaja 13042011
	public static Vector<Tblrujnegeri> getNegeriMampuKL() throws Exception {
		String key = "DBgetNegeriMampuKL";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujnegeri>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "Select id_Negeri,kod_Mampu,nama_Negeri ";
			sql += " from tblrujnegeri ";
			sql += " where id_negeri in (14,15,16) ";
			sql += " order by kod_mampu ";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
				Tblrujnegeri s = null;
				while (rs.next()) {
					s = new Tblrujnegeri();
					s.setIdNegeri(rs.getLong(1));
					s.setKodMampu(rs.getString(2));
					s.setNamaNegeri(rs.getString(3));

					v.addElement(s);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	// select pengarah dan bekas pengarah 14/09/2011 --YATI EDIT 29/10/2018 UTK TAMBAH COND ROLE
	public static Vector<Users> getPengarahDanBekasPengarah(String idNegeri) throws Exception {
		Db db = null;
		String sql = " SELECT DISTINCT U.USER_ID AS ID_PEGAWAI, U.USER_NAME AS NAMA_PEGAWAI FROM USERS U, USERS_INTERNAL UI ";
		sql += " WHERE U.USER_ID = UI.USER_ID ";
		sql += " AND UI.ID_SEKSYEN = '1' ";
		sql += " AND (u.user_role = '(PPT)PengarahUnit'  or ui.id_jawatan IN (4, 29) ) ";


		if (!idNegeri.equals("")) {
			if (idNegeri.equals("15") || idNegeri.equals("16")) {
				sql += " AND UI.ID_NEGERI = '14'";
			} else {
				sql += " AND UI.ID_NEGERI = '" + idNegeri + "'";
			}
		}

		sql += " ORDER BY LPAD(U.USER_NAME,10) ";
		myLogger.info("getPengarahDanBekasPengarah :" + sql);
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Users> list = new Vector<Users>();
			Users f = null;
			while (rs.next()) {
				f = new Users();
				f.setUserId(rs.getLong(1));
				f.setUserName(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// select penolong pengarah 27022012
	public static Vector<Users> getPenolongPengarahPPT(String idNegeri) throws Exception {
		Db db = null;
		String sql = " SELECT DISTINCT U.USER_ID AS ID_PEGAWAI, U.USER_NAME AS NAMA_PEGAWAI FROM USERS U, USERS_INTERNAL UI ";
		sql += " WHERE U.USER_ID = UI.USER_ID ";
		sql += " AND UI.ID_SEKSYEN = '1' ";
		sql += " AND UI.ID_JAWATAN IN (9) ";

		if (!idNegeri.equals("")) {
			if (idNegeri.equals("15") || idNegeri.equals("16")) {
				sql += " AND UI.ID_NEGERI = '14'";
			} else {
				sql += " AND UI.ID_NEGERI = '" + idNegeri + "'";
			}
		}

		sql += " ORDER BY LPAD(U.USER_NAME,10) ";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Users> list = new Vector<Users>();
			Users f = null;
			while (rs.next()) {
				f = new Users();
				f.setUserId(rs.getLong(1));
				f.setUserName(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, String>> getPejabatARBByNegeri(String id_negeri) throws Exception {
		Db db = null;

		String sql = "SELECT A.ID_PEJABAT, A.KOD_PEJABAT, A.NAMA_PEJABAT, B.NAMA_DAERAH, C.NAMA_NEGERI ";
		sql += " FROM TBLRUJPEJABAT A, TBLRUJDAERAH B, TBLRUJNEGERI C ";
		sql += " WHERE A.ID_JENISPEJABAT = '9' ";
		sql += " AND A.ID_DAERAH = B.ID_DAERAH(+) ";
		sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) ";

		// untuk wilayah labuan dan putrajaya under KL
		if (id_negeri.equals("15") || id_negeri.equals("16")) {
			sql += " AND A.ID_NEGERI = '14'";
		} else {
			sql += " AND A.ID_NEGERI = '" + id_negeri + "'";
		}

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEJABAT", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
				h.put("KOD_PEJABAT", rs.getString("KOD_PEJABAT") == null ? "" : rs.getString("KOD_PEJABAT"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());

				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, String>> getPejabatARB() throws Exception {
		Db db = null;

		String sql = "SELECT A.ID_PEJABAT, A.KOD_PEJABAT, A.NAMA_PEJABAT, B.NAMA_DAERAH ";
		sql += " FROM TBLRUJPEJABAT A, TBLRUJDAERAH B ";
		sql += " WHERE A.ID_JENISPEJABAT = '9' ";
		sql += " AND A.ID_DAERAH = B.ID_DAERAH(+) ";

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEJABAT", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
				h.put("KOD_PEJABAT", rs.getString("KOD_PEJABAT") == null ? "" : rs.getString("KOD_PEJABAT"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// ADD BY PEJE
	public static Vector<Hashtable<String, String>> getKategoriMonitoring() throws Exception {
		Db db = null;

		String sql = "SELECT ID_KATEGORI, KOD_KATEGORI, KETERANGAN FROM TBLMONRUJKATEGORI";

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_KATEGORI", rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
				h.put("KOD_KATEGORI", rs.getString("KOD_KATEGORI") == null ? "" : rs.getString("KOD_KATEGORI"));
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujjawatan> getJawatanKJP() throws Exception {
		Db db = null;
		String sql = "Select ID_JAWATAN, KOD_JAWATAN " + " ,case " + " when id_jawatan=4 then 'PELULUS' "
				+ " when id_jawatan=9 then 'PENYEMAK' " + " when id_jawatan=24 then 'PENYEDIA' " + " end KETERANGAN"
				+ " from tblrujjawatan where id_jawatan in (4,9,24) order by lpad(KOD_JAWATAN,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujjawatan> v = new Vector<Tblrujjawatan>();
			Tblrujjawatan j = null;
			while (rs.next()) {
				j = new Tblrujjawatan();
				j.setIdJawatan(rs.getLong(1));
				j.setKodJawatan(rs.getString(2));
				j.setKeterangan(rs.getString(3));

				v.addElement(j);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblpptpihakberkepentingan> getPBbyHakmilikUnder18(String idhakmilik) throws Exception {
		Db db = null;

		String sql = "SELECT DISTINCT a.id_pihakberkepentingan, b.nama_pb, a.id_hakmilik, a.id_jenispb ";
		sql += " FROM Tblppthakmilik h, Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
		sql += " WHERE a.id_pihakberkepentingan = b.id_pihakberkepentingan(+) ";
		sql += " AND a.id_hakmilik = h.id_hakmilik";
		sql += " AND b.nama_pb is not null ";
		sql += " AND a.id_hakmilik = '" + idhakmilik + "'";
		sql += " AND a.id_jenispb not in (40,41,42)";
		sql += " AND (b.umur is not null AND b.umur >= 18 OR b.umur is null) ";
		sql += " ORDER BY b.nama_pb";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblpptpihakberkepentingan> list = new Vector<Tblpptpihakberkepentingan>();
			Tblpptpihakberkepentingan f = null;
			while (rs.next()) {
				f = new Tblpptpihakberkepentingan();
				f.setIdPihakberkepentingan(rs.getLong(1));
				f.setNamaPb(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}

	// select pegawai ppt by negeri 22/03/2010
	public static Vector<Users> getSenaraiPPengarahPPTByNegeri(String idNegeri) throws Exception {
		Db db = null;
		/*String sql = " SELECT DISTINCT U.USER_ID AS ID_PEGAWAI, U.USER_NAME AS NAMA_PEGAWAI FROM USERS U, USERS_INTERNAL UI ";
		sql += " WHERE U.USER_ID = UI.USER_ID ";
		sql += " AND UI.ID_SEKSYEN = '1' "; */
		// sql += " AND UI.ID_JAWATAN IN (7,9) ";

		String sql = " SELECT DISTINCT U.USER_ID AS ID_PEGAWAI,  (UPPER(U.USER_NAME) || ' (' || "+
				" (CASE WHEN UI.FLAG_AKTIF = 1 THEN 'AKTIF' "+
				" WHEN UI.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' "+
				" ELSE 'AKTIF' END) || ' - ' || U.USER_LOGIN || ') ' "+
				" ) AS NAMA_PEGAWAI ,UI.FLAG_AKTIF,U.USER_NAME  "+
				" FROM USERS U, USERS_INTERNAL UI  WHERE U.USER_ID = UI.USER_ID  " +
				" AND UI.ID_SEKSYEN IN (1)  ";

		if (idNegeri.equals("15") || idNegeri.equals("16")) {
			sql += " AND UI.ID_NEGERI = '14'";
		} else {
			sql += " AND UI.ID_NEGERI = '" + idNegeri + "'";
		}

		sql += " ORDER BY (CASE WHEN UI.FLAG_AKTIF = 1 THEN 'AKTIF' "+
				" WHEN UI.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' "+
				" ELSE 'AKTIF' END), U.USER_NAME  ";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Users> list = new Vector<Users>();
			Users f = null;
			while (rs.next()) {
				f = new Users();
				f.setUserId(rs.getLong(1));
				f.setUserName(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// select pegawai ppt by negeri 22/03/2010
	public static Vector<Users> getSenaraiPegawaiPPTByNegeriExceptPPdanPgh(String idNegeri) throws Exception {
		Db db = null;

		/*
		String sql = " SELECT DISTINCT U.USER_ID AS ID_PEGAWAI, " +
				" ('(' || U.USER_LOGIN || ') ' || U.USER_NAME) AS NAMA_PEGAWAI " +
				" FROM USERS U, USERS_INTERNAL UI ";
		sql += " WHERE U.USER_ID = UI.USER_ID ";
		sql += " AND UI.ID_SEKSYEN NOT IN (2,0)";
		//sql += " AND ID_JAWATAN NOT IN (1,2,5,7,4) ";
		//sql += " AND UI.ID_JAWATAN IN (9,33,161738) ";
		sql += " AND UI.ID_JAWATAN IN (9,33,161738,4,13) ";

		if (idNegeri.equals("15") || idNegeri.equals("16")) {
			sql += " AND UI.ID_NEGERI = '14'";
		} else {
			sql += " AND UI.ID_NEGERI = '" + idNegeri + "'";
		}
		//sql += " AND (UI.FLAG_AKTIF = '1' ";
		sql += " ORDER BY LPAD(U.USER_NAME,10) ";
		*/

		//RAZMAN BUAT BARU
		String sql = " SELECT DISTINCT U.USER_ID AS ID_PEGAWAI,  (UPPER(U.USER_NAME) || ' (' || "+
				" (CASE WHEN UI.FLAG_AKTIF = 1 THEN 'AKTIF' "+
				" WHEN UI.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' "+
				" ELSE 'AKTIF' END) || ' - ' || U.USER_LOGIN || ') ' "+
				" ) AS NAMA_PEGAWAI ,UI.FLAG_AKTIF,U.USER_NAME  "+
				" FROM USERS U, USERS_INTERNAL UI  WHERE U.USER_ID = UI.USER_ID  " +
				" AND UI.ID_SEKSYEN NOT IN (2,0)  "+
				" AND UI.ID_JAWATAN IN (9,33,161738,4,13,5)   ";

				if (idNegeri.equals("15") || idNegeri.equals("16")) {
					sql += " AND UI.ID_NEGERI = '14'";
				} else {
					sql += " AND UI.ID_NEGERI = '" + idNegeri + "'";
				}

				sql += " ORDER BY (CASE WHEN UI.FLAG_AKTIF = 1 THEN 'AKTIF' "+
				" WHEN UI.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' "+
				" ELSE 'AKTIF' END), U.USER_NAME  ";

		myLogger.info("LIST PENTADBIR"+sql);
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Users> list = new Vector<Users>();
			Users f = null;
			while (rs.next()) {
				f = new Users();
				f.setUserId(rs.getLong(1));
				f.setUserName(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, String>> getPYWPenolongPegawaiTanahHQ() throws Exception {
		Db db = null;
		//BY AIN 09052017
		String sql = " SELECT DISTINCT A.USER_NAME, B.USER_ID, B.ID_JAWATAN, B.ID_NEGERI"
				+ " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C"
				+ " WHERE A.USER_ID = B.USER_ID"
				+ " AND B.ID_JAWATAN = C.ID_JAWATAN(+)"
				+ " AND A.USER_ROLE IN ('(PHP)PYWPenolongPegawaiTanahHQ')"
				+ " AND B.FLAG_AKTIF = '1'"
				+ " AND B.ID_NEGERI = '16'"
				+ " AND B.ID_SEKSYEN = '4'"
				+ " UNION"
				+ " SELECT DISTINCT A.USER_NAME, B.USER_ID, B.ID_JAWATAN, B.ID_NEGERI"
				+ " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C, USER_ROLE D"
				+ " WHERE A.USER_ID = B.USER_ID"
				+ " AND B.ID_JAWATAN = C.ID_JAWATAN(+)"
				+ " AND A.USER_LOGIN = D.USER_ID"
				+ " AND D.ROLE_ID IN ('(PHP)PYWPenolongPegawaiTanahHQ')"
				+ " AND B.FLAG_AKTIF = '1'"
				+ " AND B.ID_NEGERI = '16'"
				+ " AND B.ID_SEKSYEN = '4'"
				+ " ORDER BY USER_NAME ASC";

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("keluarlaaaa "+sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEGAWAI", rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("NAMA_PEGAWAI", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Hashtable<String, Comparable>> getStatusAPB() throws Exception {
		Db db = null;
		String sql = "";
		sql = "SELECT B.ID_STATUS, B.KOD_STATUS, B.KETERANGAN"
				+ " FROM TBLRUJSUBURUSANSTATUS A, TBLRUJSTATUS B"
				+ " WHERE A.ID_STATUS = B.ID_STATUS"
				+ " AND A.ID_SUBURUSAN = 57"
				+ " ORDER BY A.LANGKAH ASC";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id", rs.getLong("ID_STATUS"));
				h.put("kod", rs.getString("KOD_STATUS") == null ? "" : rs.getString("KOD_STATUS").toUpperCase());
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Users> getSenaraiPenolongDanPengarahHTP() throws Exception {
		Db db = null;

		String sql = " SELECT DISTINCT U.USER_ID AS ID_PEGAWAI, U.USER_NAME AS NAMA_PEGAWAI, UI.ID_NEGERI, UI.ID_JAWATAN "
				+ " FROM USERS U, USERS_INTERNAL UI " + " WHERE U.USER_ID = UI.USER_ID " + " AND UI.ID_SEKSYEN = '3' "
				+ " AND UI.ID_NEGERI = '16' --HQ " + " AND UI.ID_JAWATAN IN ('4','9') " + " ORDER BY LPAD(U.USER_NAME,10) ";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Users> list = new Vector<Users>();
			Users f = null;
			while (rs.next()) {
				f = new Users();
				f.setUserId(rs.getLong(1));
				f.setUserName(rs.getString(2));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector<Tblrujluas> getLuasKjpRekod() throws Exception {
		String key = "DB.getLuasKjpRekod";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujluas>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "SELECT id_Luas, kod_Luas, Keterangan FROM Tblrujluas where id_luas IN (2,3,5,6) ORDER BY kod_Luas";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblrujluas> v = new Vector<Tblrujluas>();
				Tblrujluas l = null;
				while (rs.next()) {
					l = new Tblrujluas();
					l.setIdLuas(rs.getLong("id_Luas"));
					l.setKodLuas(rs.getString("kod_Luas"));
					l.setKeterangan(rs.getString("Keterangan"));
					v.addElement(l);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujurusan> getUrusanKJPOnline() throws Exception {
		String key = "DB.getUrusanKJPOnline";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujurusan>) cachedObject.getObjectValue();
		} else {
			Vector<Tblrujurusan> v = null;
			Db db = null;
			String sql = "Select id_urusan,kod_urusan,nama_urusan from "
					+ " tblrujurusan where id_urusan in (6,7,9) order by id_urusan";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujurusan>();
				Tblrujurusan u = null;
				while (rs.next()) {
					u = new Tblrujurusan();
					u.setIdUrusan(rs.getLong("id_urusan"));
					u.setKodUrusan(rs.getString("kod_urusan"));
					u.setNamaUrusan(rs.getString("nama_urusan"));
					v.addElement(u);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	public static Vector<Tblrujsuburusan> getSuburusanKJPOnline(Long parentValue) throws Exception {
		String key = "DB.getSuburusanKJPOnline"+parentValue;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujsuburusan>) cachedObject.getObjectValue();
		} else {
			Vector<Tblrujsuburusan> v = null;
			Db db = null;
			String sql = "Select id_suburusan, kod_suburusan, nama_suburusan from tblrujsuburusan where id_urusan = "+parentValue;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujsuburusan>();
				Tblrujsuburusan u = null;
				while (rs.next()) {
					u = new Tblrujsuburusan();
					u.setIdSuburusan(rs.getLong("id_suburusan"));
					u.setKodSuburusan(rs.getString("kod_suburusan"));
					u.setNamaSuburusan(rs.getString("nama_suburusan"));
					v.addElement(u);
				}
				myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}
	public static String getUrlAgensi(String kodIntegrasi) throws Exception {
		String url = "";

		Db db = null;
		String sql = "";
		db = new Db();
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		sql = "SELECT URL FROM TBLINTURLAGENSI WHERE KOD_INTEGRASI = '" + kodIntegrasi + "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			url = rs.getString("url");
		}

		return url;
	}
	public static String getUrlAgensiHartaAlih(String kodHa) throws Exception {
		String url = "";

		Db db = null;
		String sql = "";
		db = new Db();
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		sql = "SELECT INT_URL FROM TBLPPKRUJJENISHA WHERE KOD = " + kodHa;
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			url = rs.getString("int_url");
		}

		return url;
	}


	/*public static Vector<Hashtable<String, String>> getSenaraiPegawaiPTG(String idNegeri) throws Exception {
		Db db = null;


		String sql = "SELECT ID_PEJABAT, KOD_PEJABAT, NAMA_PEJABAT FROM TBLRUJPEJABAT WHERE ID_JENISPEJABAT = '01' "
			        + " AND ID_NEGERI = '" + idNegeri + "' ORDER BY KOD_PEJABAT ASC";


//		String sql = "SELECT PEJ.NAMA_PEJABAT AS PTG, PEJ.ID_PEJABAT AS ID_PTG " +
//				"FROM TBLRUJPEJABAT PEJ, TBLPHPHAKMILIK HAKMILIK, TBLPHPHAKMILIKPERMOHONAN HAKMILIKPERMOHONAN, TBLPERMOHONAN PERMOHONAN, TBLPFDFAIL FAIL " +
//				"WHERE PEJ.ID_NEGERI = HAKMILIK.ID_NEGERI AND HAKMILIK.ID_HAKMILIKPERMOHONAN = HAKMILIKPERMOHONAN.ID_HAKMILIKPERMOHONAN " +
//				"AND HAKMILIKPERMOHONAN.ID_PERMOHONAN = PERMOHONAN.ID_PERMOHONAN AND PERMOHONAN.ID_FAIL = FAIL.ID_FAIL " +
//				"AND PEJ.ID_JENISPEJABAT = 01 AND FAIL.NO_FAIL = '"+noFail+"'";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEJABAT", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}*/

	/*public static Vector<Hashtable<String, String>> getSenaraiPegawaiPTD(String idNegeri) throws Exception {
		Db db = null;

		String sql = "SELECT ID_PEJABAT, KOD_PEJABAT, NAMA_PEJABAT FROM TBLRUJPEJABAT WHERE ID_JENISPEJABAT = '02'"
		        + " AND ID_NEGERI = '" + idNegeri + "' ORDER BY KOD_PEJABAT ASC";


//		String sql = "SELECT PEJ.NAMA_PEJABAT AS PTD, PEJ.ID_PEJABAT AS ID_PTD " +
//				"FROM TBLRUJPEJABAT PEJ, TBLPHPHAKMILIK HAKMILIK, TBLPHPHAKMILIKPERMOHONAN HAKMILIKPERMOHONAN, TBLPERMOHONAN PERMOHONAN, TBLPFDFAIL FAIL " +
//				"WHERE PEJ.ID_NEGERI = HAKMILIK.ID_NEGERI AND HAKMILIK.ID_HAKMILIKPERMOHONAN = HAKMILIKPERMOHONAN.ID_HAKMILIKPERMOHONAN " +
//				"AND HAKMILIKPERMOHONAN.ID_PERMOHONAN = PERMOHONAN.ID_PERMOHONAN AND PERMOHONAN.ID_FAIL = FAIL.ID_FAIL " +
//				"AND PEJ.ID_JENISPEJABAT = 02 AND FAIL.NO_FAIL = '"+noFail+"'";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEJABAT", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}*/

	/*public static Vector<Hashtable<String, String>> getSenaraiPegawaiPBT(String noFail) throws Exception {
		Db db = null;

		String sql = "SELECT PEJ.NAMA_PEJABAT AS PBT, PEJ.ID_PEJABAT AS ID_PBT " +
				"FROM TBLRUJPEJABAT PEJ, TBLPHPHAKMILIK HAKMILIK, TBLPHPHAKMILIKPERMOHONAN HAKMILIKPERMOHONAN, TBLPERMOHONAN PERMOHONAN, TBLPFDFAIL FAIL " +
				"WHERE PEJ.ID_NEGERI = HAKMILIK.ID_NEGERI AND HAKMILIK.ID_HAKMILIKPERMOHONAN = HAKMILIKPERMOHONAN.ID_HAKMILIKPERMOHONAN " +
				"AND HAKMILIKPERMOHONAN.ID_PERMOHONAN = PERMOHONAN.ID_PERMOHONAN AND PERMOHONAN.ID_FAIL = FAIL.ID_FAIL " +
				"AND PEJ.ID_JENISPEJABAT = 25 AND FAIL.NO_FAIL = '"+noFail+"'";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PBT", rs.getString("ID_PBT") == null ? "" : rs.getString("ID_PBT"));
				h.put("PBT", rs.getString("PBT") == null ? "" : rs.getString("PBT").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}*/

	/*public static Vector<Hashtable<String, String>> getSenaraiPegawaiJKPTG(String idNegeri) throws Exception {
		Db db = null;
		String sql = "SELECT ID_PEJABATJKPTG, KOD_JKPTG, NAMA_PEJABAT FROM TBLRUJPEJABATJKPTG " +
				"WHERE ID_SEKSYEN = '2'" + " AND ID_JENISPEJABAT = '24' " +
				"AND ID_NEGERI = '" + idNegeri + "' ORDER BY KOD_JKPTG ASC";

//		String sql = "SELECT ID_PEJABATJKPTG, F.NAMA_PEJABAT, F.ALAMAT1, F.ALAMAT2, F.ALAMAT3, F.POSKOD, F.ID_NEGERI " +
//				"FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANPELEPASAN C, TBLPHPHAKMILIKPERMOHONAN D, TBLPHPHAKMILIK E, TBLRUJPEJABATJKPTG F " +
//				"WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_PERMOHONAN = D.ID_PERMOHONAN AND D.ID_HAKMILIKPERMOHONAN = E.ID_HAKMILIKPERMOHONAN " +
//				"AND E.ID_NEGERI = F.ID_NEGERI AND F.ID_JENISPEJABAT = 24 AND F.ID_SEKSYEN = 2 AND A.NO_FAIL = '"+noFail+"'";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEJABATJKPTG", rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}*/

	/*public static Vector<Hashtable<String, String>> getSenaraiPegawaiKJP(String noFail) throws Exception {
		Db db = null;
		String sql = "SELECT B.ID_AGENSI, B.NAMA_AGENSI FROM TBLRUJKEMENTERIAN A, TBLRUJAGENSI B WHERE A.ID_KEMENTERIAN = B.ID_KEMENTERIAN";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();

			Hashtable<String, String> h;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_AGENSI", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
				h.put("NAMA_AGENSI", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}*/

	public static Vector<Hashtable<String, String>> getSenaraiPegawaiLainlain(String noFail) throws Exception {
		Db db = null;
		String sql = "SELECT ID_PEJABAT, NAMA_PEJABAT FROM TBLRUJPEJABAT WHERE KOD_PEJABAT IN ('TNB','SYB')";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();
			Hashtable<String, String> h;

			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("ID_PEJABAT", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				v.addElement(h);

			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	//sort negeri sarawak ikut list yg diminta
	public static Vector<Tblrujdaerah> getSortDaerahByNegeri(String idnegeri) throws Exception {
		String key = "DB.getDaerahByNegeri" + idnegeri;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector<Tblrujdaerah>) cachedObject.getObjectValue();
		} else {
			Db db = null;
			Vector<Tblrujdaerah> list = null;
			String sql = "";
			if (idnegeri.equals("13")) {
				sql = " SELECT ID_DAERAH, KOD_DAERAH, NAMA_DAERAH "+
						" FROM ( "+
						" SELECT ID_DAERAH, KOD_DAERAH, NAMA_DAERAH, ID_NEGERI, " +
						"(CASE WHEN ID_DAERAH = 112 THEN 1 "+
						" WHEN ID_DAERAH = 119 THEN 2 "+
						" WHEN ID_DAERAH = 1610157 THEN 3 "+
						" WHEN ID_DAERAH = 113 THEN 4 "+
						" WHEN ID_DAERAH = 1610153 THEN 5 "+
						" WHEN ID_DAERAH = 117 THEN 6 "+
						" WHEN ID_DAERAH = 114 THEN 7 "+
						" WHEN ID_DAERAH = 118 THEN 8 "+
						" WHEN ID_DAERAH = 1610152 THEN 9 "+
						" WHEN ID_DAERAH = 120 THEN 10 "+
						" WHEN ID_DAERAH = 115 THEN 11 "+
						" WHEN ID_DAERAH = 116 THEN 12 ELSE 0 END) AS TURUTAN_SARAWAK "+
						" FROM TBLRUJDAERAH "+
						" WHERE KOD_DAERAH NOT IN ('10','13','14','15','0')) "+
						" WHERE ID_NEGERI = '13' "+
						" ORDER BY TURUTAN_SARAWAK, KOD_DAERAH";
				} else {
					sql = "Select id_Daerah,kod_Daerah,nama_Daerah from tblrujdaerah" + " where id_negeri='" + idnegeri + "'"
							+ " ORDER BY lpad(kod_Daerah,10)";
				}
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				//myLogger.debug(":::::::::::::;;;test pajakan"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				list = new Vector<Tblrujdaerah>();
				Tblrujdaerah f = null;
				while (rs.next()) {
					f = new Tblrujdaerah();
					f.setIdDaerah(rs.getLong(1));
					f.setKodDaerah(rs.getString(2));
					f.setNamaDaerah(rs.getString(3));
					list.addElement(f);
				}
				myCache.put(new Element(key, list));
				return list;
			} finally {
				if (db != null)
					db.close();
			}
		}

	}

	//ADDED BY YATI 7/2/2018
	public static Vector<Tblrujdaerah> getDaerahLaporanByIdPejabatJKPTGPPT(String id_pejabatjkptg) throws Exception {

		Db db = null;

		String sql = " SELECT D.ID_DAERAH,D.KOD_DAERAH,D.NAMA_DAERAH";
		sql += " FROM TBLRUJPEJABATJKPTG P,TBLRUJDAERAH D";
		sql += " WHERE P.ID_SEKSYEN = '1' AND P.ID_NEGERI = D.ID_NEGERI AND  P.ID_JENISPEJABAT IN ('22','21')";

		if (!id_pejabatjkptg.equals("16")) {
			sql += " AND P.ID_NEGERI = '" + id_pejabatjkptg+ "'";
		}

		sql += " AND D.KOD_DAERAH <> '0' ORDER BY P.ID_NEGERI,D.KOD_DAERAH ASC";

		myLogger.info("SQL LIST ZZZZ : "+sql);
		Vector<Tblrujdaerah> list = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			list = new Vector<Tblrujdaerah>();
			Tblrujdaerah f = null;
			while (rs.next()) {
				f = new Tblrujdaerah();
				f.setIdDaerah(rs.getLong(1));
				f.setKodDaerah(rs.getString(2));
				f.setNamaDaerah(rs.getString(3));
				list.addElement(f);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}


	}



}// close class