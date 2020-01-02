package ekptg.model.pdt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmDaftarAktaData {

	private static Logger myLogger = Logger.getLogger(FrmDaftarAktaData.class);
	
	private static Vector listAktaAll = new Vector();
	private static Vector listCarianPaparAkta = new Vector();
	private static Vector listPenggalAll = new Vector();
	private static Vector listPenggalPaparById = new Vector();
	private static Vector listBahagianAll = new Vector();
	private static Vector listBahagianPaparById = new Vector();
	private static Vector listBabAll = new Vector();
	private static Vector listBabPaparById = new Vector();
	private static Vector listSeksyenAll = new Vector();
	private static Vector listSeksyenPaparById = new Vector();
	private static Vector listSubSeksyenAll = new Vector();
	private static Vector listSubSeksyenPaparById = new Vector();
	private static Vector listParaAll = new Vector();
	private static Vector listParaPaparById = new Vector();
	private static Vector listSubParaAll = new Vector();
	private static Vector listSubParaPaparById = new Vector();
	private static Vector listJadualParaAll = new Vector();
	private static Vector listJadualParaPaparById = new Vector();
	private static Vector listJadualSubParaAll = new Vector();
	private static Vector listJadualSubParaPaparById = new Vector();
	private static Vector listJadualSubSubParaAll = new Vector();
	private static Vector listJadualSubSubParaPaparById = new Vector();
	
	// CARIAN Akta DAN PAPAR SEMUA Akta
	public void setCarianPaparAkta(String noAkta,
			String namaAkta, String tarikhKuatkuasa) throws Exception {
		Db db = null;
		listCarianPaparAkta.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AKTA,A.NO_AKTA,A.NAMA_AKTA,A.TARIKH_KUATKUASA"
					+ " FROM TBLPDTAKTA A WHERE A.ID_AKTA IS NOT NULL";

			// NO AKTA
			if (noAkta != null) {
				if (!noAkta.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_AKTA) LIKE '%' ||'"
							+ noAkta.toUpperCase() + "'|| '%'";
				}
			}
			// NAMA Akta
			if (namaAkta != null) {
				if (!namaAkta.trim().equals("")) {
					sql = sql + " AND UPPER(A.NAMA_AKTA) LIKE '%' ||'"
							+ namaAkta.toUpperCase() + "'|| '%'";
				}
			}
			// TARIKH KUATKUASA
			if (tarikhKuatkuasa != null) {
				if (!tarikhKuatkuasa.trim().equals("")) {
					sql = sql
							+ " AND UPPER(A.TARIKH_KUATKUASA) LIKE '%' ||'"
							+ sdf.format(sdf.parse(tarikhKuatkuasa))
									.toUpperCase() + "'|| '%'";
				}
			}

			sql = sql + " ORDER BY A.ID_AKTA";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("id_Akta", rs.getString("ID_AKTA"));
				h.put("no_Akta", rs.getString("NO_AKTA") == null ? ""
						: rs.getString("NO_AKTA"));
				h.put("nama_Akta", rs.getString("NAMA_AKTA") == null ? ""
						: rs.getString("NAMA_AKTA"));
				h.put("tarikh_kuatkuasa",
						rs.getDate("TARIKH_KUATKUASA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_KUATKUASA")));

				listCarianPaparAkta.addElement(h);
				no++;
				count++;

			}

			if (count == 0) {
				h = new Hashtable();
				h.put("no", "");
				h.put("id_Akta", "");
				h.put("no_Akta", "Tiada rekod.");
				h.put("nama_Akta", "");
				h.put("tarikh_kuatkuasa", "");

				listCarianPaparAkta.addElement(h);
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}

	// TAMBAH Akta BARU
	public void addAkta(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			long idAkta = DB.getNextID("TBLPDTAKTA_SEQ");
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_AKTA", idAkta);
			r.add("NO_AKTA", data.get("noAkta"));
			r.add("NAMA_AKTA", data.get("namaAkta"));
			
			if (data.get("tarikhKuatkuasa") != null) {
				r.add("TARIKH_KUATKUASA", r.unquote("to_date('"+ data.get("tarikhKuatkuasa")+ "','dd/MM/yyyy')"));
			}
			
			if (data.get("tarikhMansuh") != null) {
				r.add("TARIKH_MANSUH", r.unquote("to_date('"+ data.get("tarikhMansuh")+ "','dd/MM/yyyy')"));
			}
			r.add("NO_WARTA", data.get("noWarta"));
			
			if (data.get("tarikhWarta") != null) {
				r.add("TARIKH_WARTA", r.unquote("to_date('"+ data.get("tarikhWarta")+ "','dd/MM/yyyy')"));
			}
			r.add("ID_TARAFKESELAMATAN", data.get("idKeselamatan"));
			r.add("ID_SEKSYEN", data.get("idSeksyen"));
			r.add("ID_FAIL", data.get("idFail"));
			r.add("CATATAN", data.get("catatan"));
			if (data.get("tarikhDaftar") != null) {
				r.add("TARIKH_DAFTAR", r.unquote("to_date('"+ data.get("tarikhDaftar")+ "','dd/MM/yyyy')"));
			}
			r.add("ID_MASUK", data.get("idMasuk"));
			if (data.get("tarikhMasuk") != null) {
				r.add("TARIKH_MASUK", r.unquote("to_date('"+ data.get("tarikhMasuk")+ "','dd/MM/yyyy')"));
			}
			sql = r.getSQLInsert("TBLPDTAKTA");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
			
			//uploadFiles();
			
			
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}

	// TAMBAH PENGGAL BARU
	public void addPenggal(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			long idPenggal = DB.getNextID("TBLPDTAKTAPENGGAL_SEQ");
			Integer noPenggal = (Integer) data.get("noPenggal");
			String tajukPenggal = (String) data.get("tajukPenggal");

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_AKTAPENGGAL", idPenggal);
			r.add("NO_PENGGAL", noPenggal);
			r.add("TAJUK_PENGGAL", tajukPenggal);
			r.add("ID_AKTA", data.get("idAkta"));
			r.add("ID_MASUK", data.get("idMasuk"));
			r.add("TARIKH_MASUK", data.get("tarikhMasuk"));

			sql = r.getSQLInsert("TBLPDTAKTAPENGGAL");

			stmt.executeUpdate(sql);
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}

	// TAMBAH BAHAGIAN BARU
	public void addBahagian(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			long idBahagian = DB.getNextID("TBLPDTAKTABAHAGIAN_SEQ");

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_AKTABAHAGIAN", idBahagian);
			r.add("TAJUK_BAHAGIAN", data.get("tajukBahagian"));
			r.add("ID_AKTAPENGGAL", data.get("idAktaPenggal"));
			r.add("ID_MASUK", data.get("idMasuk"));
			r.add("ID_AKTA", data.get("idAkta"));
			r.add("NO_BAHAGIAN", data.get("noBahagian"));
			r.add("TARIKH_MASUK", data.get("tarikhMasuk"));

			sql = r.getSQLInsert("TBLPDTAKTABAHAGIAN");
			stmt.executeUpdate(sql);
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}

	// TAMBAH BAB BARU
	public void addBab(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			long idBab = DB.getNextID("TBLPDTAKTABAB_SEQ");

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_AKTA", data.get("idAkta"));
			r.add("ID_AKTABAB", idBab);
			r.add("ID_AKTAPENGGAL", data.get("idAktaPenggal"));
			r.add("ID_AKTABAHAGIAN", data.get("idAktaBahagian"));
			r.add("ID_MASUK", data.get("idMasuk"));
			r.add("TARIKH_MASUK", data.get("tarikhMasuk"));
			r.add("TAJUK_BAB", data.get("tajukBab"));
			r.add("NO_BAB", data.get("noBab"));

			sql = r.getSQLInsert("TBLPDTAKTABAB");

			stmt.executeUpdate(sql);
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}

	// TAMBAH SEKSYEN BARU
	public void addSeksyen(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			long idSeksyen = DB.getNextID("TBLPDTAKTASEKSYEN_SEQ");

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_AKTASEKSYEN", idSeksyen);
			r.add("SEKSYEN", data.get("seksyen"));
			r.add("ID_AKTA", data.get("idAkta"));
			r.add("ID_AKTAPENGGAL", data.get("idAktaPenggal"));
			r.add("ID_AKTABAHAGIAN", data.get("idAktaBahagian"));
			r.add("ID_AKTABAB", data.get("idAktaBab"));
			r.add("ID_MASUK", data.get("idMasuk"));
			r.add("TARIKH_MASUK", data.get("tarikhMasuk"));
			sql = r.getSQLInsert("TBLPDTAKTASEKSYEN");

			stmt.executeUpdate(sql);
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}

	// TAMBAH SUBSEKSYEN BARU
	public void addSubSeksyen(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			long idsSubSeksyen = DB.getNextID("TBLPDTAKTASUBSEKSYEN_SEQ");

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_AKTASUBSEKSYEN", idsSubSeksyen);
			r.add("SUB_SEKSYEN", data.get("subSeksyen"));
			r.add("ID_AKTA", data.get("idAkta"));
			r.add("ID_AKTAPENGGAL", data.get("idAktaPenggal"));
			r.add("ID_AKTABAHAGIAN", data.get("idAktaBahagian"));
			r.add("ID_AKTABAB", data.get("idAktaBab"));
			r.add("ID_AKTASEKSYEN", data.get("idAktaSeksyen"));
			r.add("ID_MASUK", data.get("idMasuk"));
			r.add("TARIKH_MASUK", data.get("tarikhMasuk"));
			sql = r.getSQLInsert("TBLPDTAKTASUBSEKSYEN");

			stmt.executeUpdate(sql);
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}

	// TAMBAH PARA DAN JADUAL BARU
	public void addPara(Hashtable data) throws Exception {
		Db dbPara = new Db();
		String sqlPara = "";
		Db dbJadual = new Db();
		String sqlJadual = "";
		long idJadual = DB.getNextID("TBLPDTJADUAL_SEQ");
		long idPara = DB.getNextID("TBLPDTAKTAPARA_SEQ");
			
		try {
			Statement stmtJadual = dbJadual.getStatement();
			SQLRenderer rJadual = new SQLRenderer();

			rJadual.add("ID_JADUAL", idJadual);
			rJadual.add("ID_AKTA", data.get("idAkta"));
			rJadual.add("NO_JADUAL", data.get("jadual"));
			rJadual.add("ID_MASUK", data.get("idMasuk"));
			rJadual.add("TARIKH_MASUK", data.get("tarikhMasuk"));
			sqlJadual = rJadual.getSQLInsert("TBLPDTJADUAL");

			stmtJadual.executeUpdate(sqlJadual);
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (dbJadual != null)
				dbJadual.close();
		}
		try {
			Statement stmtPara = dbPara.getStatement();
			SQLRenderer rPara = new SQLRenderer();

			rPara.add("ID_AKTAPARA", idPara);
			rPara.add("ID_AKTA", data.get("idAkta"));
			rPara.add("ID_AKTAPENGGAL", data.get("idAktaPenggal"));
			rPara.add("ID_AKTABAHAGIAN", data.get("idAktaBahagian"));
			rPara.add("ID_AKTABAB", data.get("idAktaBab"));
			rPara.add("ID_AKTASEKSYEN", data.get("idAktaSeksyen"));
			rPara.add("ID_AKTASUBSEKSYEN", data.get("idAktaSubSeksyen"));
			rPara.add("PARA", data.get("para"));
			rPara.add("ID_JADUAL",idJadual);
			rPara.add("ID_MASUK", data.get("idMasuk"));
			rPara.add("TARIKH_MASUK", data.get("tarikhMasuk"));
			sqlPara = rPara.getSQLInsert("TBLPDTAKTAPARA");

			stmtPara.executeUpdate(sqlPara);
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (dbPara != null)
				dbPara.close();
		}
	}
	// TAMBAH SUBPARA BARU
	public void addSubPara(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			long idSubPara = DB.getNextID("TBLPDTAKTASUBPARA_SEQ");

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_AKTASUBPARA", idSubPara);
			r.add("ID_AKTA", data.get("idAkta"));
			r.add("ID_AKTAPENGGAL", data.get("idAktaPenggal"));
			r.add("ID_AKTABAHAGIAN", data.get("idAktaBahagian"));
			r.add("ID_AKTABAB", data.get("idAktaBab"));
			r.add("ID_AKTASEKSYEN", data.get("idAktaSeksyen"));
			r.add("ID_AKTASUBSEKSYEN", data.get("idAktaSubSeksyen"));
			r.add("ID_AKTAPARA", data.get("idAktaPara"));
			r.add("ID_JADUAL", data.get("idJadual"));
			r.add("SUB_PARA", data.get("subPara"));
			r.add("ID_MASUK", data.get("idMasuk"));
			r.add("TARIKH_MASUK", data.get("tarikhMasuk"));
			sql = r.getSQLInsert("TBLPDTAKTASUBPARA");

			stmt.executeUpdate(sql);
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}
	// TAMBAH JADUALPARA BARU
	public void addJadualPara(Hashtable data) throws Exception {
		Db dbJadualPara = new Db();
		String sqlJadualPara = "";
		Db dbJadual = new Db();
		String sqlJadual = "";
		long idJadualPara = DB.getNextID("TBLPDTJADUALPARA_SEQ");
		try {
			Statement stmtJadualPara = dbJadualPara.getStatement();
			SQLRenderer rJadualPara = new SQLRenderer();

			rJadualPara.add("ID_JADUALPARA", idJadualPara);
			rJadualPara.add("ID_AKTA", data.get("idAkta"));
			rJadualPara.add("ID_JADUAL",data.get("idJadual"));
			rJadualPara.add("PARA", data.get("para"));
			rJadualPara.add("ID_MASUK", data.get("idMasuk"));
			rJadualPara.add("TARIKH_MASUK", data.get("tarikhMasuk"));
			
			sqlJadualPara = rJadualPara.getSQLInsert("TBLPDTJADUALPARA");
			stmtJadualPara.executeUpdate(sqlJadualPara);
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (dbJadualPara != null)
				dbJadualPara.close();
		}
		try {
			Statement stmtJadual = dbJadual.getStatement();
			SQLRenderer rJadual = new SQLRenderer();

			rJadual.update("ID_JADUAL", data.get("idJadual"));
			rJadual.add("NAMA_JADUAL", data.get("namaJadual"));
			rJadual.add("ID_KEMASKINI", data.get("idKemaskini"));
			rJadual.add("TARIKH_KEMASKINI", data.get("tarikhKemaskini"));
			
			sqlJadual = rJadual.getSQLUpdate("TBLPDTJADUAL");
			stmtJadual.executeUpdate(sqlJadual);
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (dbJadual != null)
				dbJadual.close();
		}
		
	}
	// TAMBAH JADUALSUBPARA BARU
	public void addJadualSubPara(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			long idJadualSubPara = DB.getNextID("TBLPDTJADUALSUBPARA_SEQ");

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_JADUALSUBPARA", idJadualSubPara);
			r.add("ID_AKTA", data.get("idAkta"));
			r.add("ID_JADUAL", data.get("idJadual"));
			r.add("ID_JADUALPARA", data.get("idJadualPara"));
			r.add("SUB_PARA", data.get("subPara"));
			r.add("ID_MASUK", data.get("idMasuk"));
			r.add("TARIKH_MASUK", data.get("tarikhMasuk"));
			sql = r.getSQLInsert("TBLPDTJADUALSUBPARA");

			stmt.executeUpdate(sql);
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}
	// TAMBAH JADUALSUBSUBPARA BARU
	public void addJadualSubSubPara(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			long idJadualSubSubPara = DB.getNextID("TBLPDTJADUALSUBSUBPARA_SEQ");

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_JADUALSUBSUBPARA", idJadualSubSubPara);
			r.add("ID_AKTA", data.get("idAkta"));
			r.add("ID_JADUAL", data.get("idJadual"));
			r.add("ID_JADUALPARA", data.get("idJadualPara"));
			r.add("ID_JADUALSUBPARA", data.get("idJadualSubPara"));
			r.add("SUB_SUBPARA", data.get("subSubPara"));
			r.add("ID_MASUK", data.get("idMasuk"));
			r.add("TARIKH_MASUK", data.get("tarikhMasuk"));
			sql = r.getSQLInsert("TBLPDTJADUALSUBSUBPARA");
			stmt.executeUpdate(sql);
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}
	// PAPAR SENARAI Akta MENGIKUT ID
	public void setPaparAktaById(int id) throws Exception {

		Db db = null;
		listAktaAll.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
//			SQLRenderer r = new SQLRenderer();
//
//			r.add("a.id_Akta");
//			r.add("a.no_Akta");
//			r.add("a.nama_Akta");
//			r.add("a.tarikh_Kuatkuasa");
//			r.add("a.tarikh_Mansuh");
//			r.add("a.no_Warta");
//			r.add("a.tarikh_Warta");
//			r.add("a.id_Fail");
//			r.add("a.id_Seksyen");
//			r.add("a.catatan");
//			r.add("a.tarikh_Daftar");
//			r.add("a.id_Tarafkeselamatan");
//			r.add("a.id_Seksyen", r.unquote("b.id_Seksyen"));
//			r.add("a.id_Fail", r.unquote("c.id_Fail"));
//			r.add("a.id_Akta", id);
//
//			sql = r.getSQLSelect("Tblpdtakta a,Tblrujseksyen b, Tblpfdfail c");
			
			
			sql = "SELECT a.id_Akta, a.no_Akta, a.nama_Akta, a.tarikh_Kuatkuasa, a.tarikh_Mansuh,"+ 
				"a.no_Warta, a.tarikh_Warta, a.id_Fail, a.id_Seksyen, a.catatan, a.tarikh_Daftar, "+ 
				"a.id_Tarafkeselamatan  "+ 
				"FROM Tblpdtakta a,Tblrujseksyen b, Tblpfdfail c "+ 
				"WHERE a.id_Seksyen = b.id_Seksyen(+)  " +
				"AND a.id_Fail = c.id_Fail  AND a.id_Akta = '"+id+"' ";
			
			myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idAkta", Utils.isNull(rs.getString("id_Akta")));
				h.put("noAkta", Utils.isNull(rs.getString("no_Akta")));
				h.put("namaAkta", Utils.isNull(rs.getString("nama_Akta")));
				h.put("tarikhKuatkuasa", rs.getString("tarikh_Kuatkuasa")==null?"":sdf.format(rs.getDate("tarikh_Kuatkuasa")));
				h.put("tarikhMansuh", rs.getString("tarikh_Mansuh")==null?"":sdf.format(rs.getDate("tarikh_Mansuh")));
				h.put("noWarta", Utils.isNull(rs.getString("no_Warta")));
				h.put("tarikhWarta", rs.getString("tarikh_Warta")==null?"":sdf.format(rs.getDate("tarikh_Warta")));
				h.put("sorTerbuka", Utils.isNull(rs.getString("id_Tarafkeselamatan")));
				h.put("idseksyen", Utils.isNull(rs.getString("id_Seksyen")));
				h.put("idFail", Utils.isNull(rs.getString("id_Fail")));
				h.put("catatan", Utils.isNull(rs.getString("catatan")));
				h.put("tarikhDaftar", rs.getString("tarikh_Daftar")==null?"":sdf.format(rs.getDate("tarikh_Daftar")));
				listAktaAll.addElement(h);

			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}

	// PAPAR SENARAI PENGGAL MENGIKUT ID
	public void setPaparPenggalById(int id) throws Exception {

		Db db = null;
		listPenggalPaparById.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("a.id_Akta");
			r.add("a.no_Akta");
			r.add("a.nama_Akta");
			r.add("b.id_Akta");
			r.add("b.no_Penggal");
			r.add("b.id_AktaPenggal");
			r.add("b.tajuk_Penggal");
			r.add("b.id_Akta", r.unquote("a.id_Akta"));
			r.add("b.id_AktaPenggal", id);

			sql = r.getSQLSelect("Tblpdtakta a,Tblpdtaktapenggal b");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idAkta", rs.getString("id_Akta"));
				h.put("noAkta", rs.getString("no_Akta"));
				h.put("namaAkta", rs.getString("nama_Akta"));
				h.put("idPenggal", rs.getString("id_AktaPenggal"));
				h.put("noPenggal", rs.getString("no_Penggal"));

				h.put("tajukPenggal", rs.getString("tajuk_Penggal"));
				listPenggalPaparById.addElement(h);

			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}

	// PAPAR SENARAI BAHAGIAN MENGIKUT ID
	public void setPaparBahagianById(int id) throws Exception {

		Db db = null;
		listBahagianPaparById.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("a.id_Akta");
			r.add("a.no_Akta");
			r.add("a.nama_Akta");
			r.add("b.id_Akta");
			r.add("b.no_Penggal");
			r.add("b.id_AktaPenggal");
			r.add("b.tajuk_Penggal");
			r.add("c.id_Akta");
			r.add("c.id_AktaBahagian");
			r.add("c.no_Bahagian");
			r.add("c.tajuk_Bahagian");
			r.add("c.id_AktaBahagian", id);
			r.add("c.id_AktaPenggal", r.unquote("b.id_AktaPenggal"));
			r.add("c.id_Akta", r.unquote("a.id_Akta"));

			sql = r
					.getSQLSelect("Tblpdtakta a,Tblpdtaktapenggal b, Tblpdtaktabahagian c");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idAkta", rs.getString("id_Akta"));
				h.put("noAkta", rs.getString("no_Akta"));
				h.put("namaAkta", rs.getString("nama_Akta"));
				h.put("idPenggal", rs.getString("id_AktaPenggal"));
				h.put("noPenggal", rs.getString("no_Penggal"));
				h.put("noBahagian", rs.getString("no_Bahagian"));
				h.put("idBahagian", rs.getString("id_AktaBahagian"));
				h.put("tajukBahagian", rs.getString("tajuk_Bahagian"));
				h.put("tajukPenggal", rs.getString("tajuk_Penggal"));
				listBahagianPaparById.addElement(h);

			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}

	// PAPAR SENARAI BAB MENGIKUT ID
	public void setPaparBabById(int id) throws Exception {

		Db db = null;
		listBabPaparById.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("a.id_Akta");
			r.add("a.no_Akta");
			r.add("a.nama_Akta");
			r.add("b.id_Akta");
			r.add("b.no_Penggal");
			r.add("b.id_AktaPenggal");
			r.add("b.tajuk_Penggal");
			r.add("d.id_Akta");
			r.add("c.id_AktaBahagian");
			r.add("d.no_Bab");
			r.add("d.id_AktaBab");
			r.add("d.tajuk_Bab");
			r.add("d.id_AktaBab", id);
			r.add("c.id_AktaPenggal", r.unquote("b.id_AktaPenggal"));
			r.add("c.id_AktaBahagian", r.unquote("d.id_AktaBahagian"));
			r.add("d.id_Akta", r.unquote("a.id_Akta"));

			sql = r
					.getSQLSelect("Tblpdtakta a,Tblpdtaktapenggal b, Tblpdtaktabahagian c,Tblpdtaktabab d ");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idAkta", rs.getString("id_Akta"));
				h.put("idBab", rs.getString("id_AktaBab"));
				h.put("noAkta", rs.getString("no_Akta"));
				h.put("namaAkta", rs.getString("nama_Akta"));
				h.put("idPenggal", rs.getString("id_AktaPenggal"));
				h.put("idBahagian", rs.getString("id_AktaBahagian"));
				h.put("noBab", rs.getString("no_Bab"));
				h.put("tajukBab", rs.getString("tajuk_Bab"));
				listBabPaparById.addElement(h);

			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}

	// PAPAR SENARAI SEKSYEN MENGIKUT ID
	public void setPaparSeksyenById(int id) throws Exception {

		Db db = null;
		listSeksyenPaparById.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("a.id_Akta");
			r.add("a.no_Akta");
			r.add("a.nama_Akta");
			r.add("b.id_Akta");
			r.add("b.no_Penggal");
			r.add("b.id_AktaPenggal");
			r.add("b.tajuk_Penggal");
			r.add("d.id_Akta");
			r.add("c.id_AktaBahagian");
			r.add("d.no_Bab");
			r.add("d.id_AktaBab");
			r.add("d.tajuk_Bab");
			r.add("e.seksyen");
			r.add("e.id_Akta");
			r.add("e.id_AktaPenggal");
			r.add("e.id_AktaBahagian");
			r.add("e.id_AktaBab");
			r.add("e.id_AktaSeksyen");
			r.add("e.id_AktaSeksyen", id);
			r.add("e.id_AktaBab", r.unquote("d.id_AktaBab"));
			r.add("e.id_AktaBahagian", r.unquote("c.id_AktaBahagian"));
			r.add("e.id_AktaPenggal", r.unquote("b.id_AktaPenggal"));
			r.add("e.id_Akta", r.unquote("a.id_Akta"));

			sql = r
					.getSQLSelect("Tblpdtakta a,Tblpdtaktapenggal b, Tblpdtaktabahagian c,Tblpdtaktabab d,Tblpdtaktaseksyen e ");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idAkta", rs.getString("id_Akta"));
				h.put("noAkta", rs.getString("no_Akta"));
				h.put("namaAkta", rs.getString("nama_Akta"));
				h.put("idPenggal", rs.getString("id_AktaPenggal"));
				h.put("idBahagian", rs.getString("id_AktaBahagian"));
				h.put("idBab", rs.getString("id_AktaBab"));
				h.put("idSeksyen", rs.getString("id_AktaSeksyen"));
				h.put("seksyen", rs.getString("seksyen"));
				listSeksyenPaparById.addElement(h);

			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}

	// PAPAR SENARAI SUBSEKSYEN MENGIKUT ID
	public void setPaparSubSeksyenById(int id) throws Exception {

		Db db = null;
		listSubSeksyenPaparById.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("a.id_Akta");
			r.add("a.no_Akta");
			r.add("a.nama_Akta");
			r.add("b.id_Akta");
			r.add("b.no_Penggal");
			r.add("b.id_AktaPenggal");
			r.add("b.tajuk_Penggal");
			r.add("d.id_Akta");
			r.add("c.id_AktaBahagian");
			r.add("d.no_Bab");
			r.add("d.id_AktaBab");
			r.add("d.tajuk_Bab");
			r.add("e.seksyen");
			r.add("e.id_AktaSeksyen");
			r.add("f.id_Akta");
			r.add("f.id_AktaPenggal");
			r.add("f.id_AktaBahagian");
			r.add("f.id_AktaBab");
			r.add("f.id_AktaSeksyen");
			r.add("f.id_AktaSubSeksyen");
			r.add("f.sub_seksyen");
			r.add("f.id_AktaSubSeksyen", id);
			r.add("f.id_Akta", r.unquote("a.id_Akta"));
			r.add("f.id_AktaPenggal", r.unquote("b.id_AktaPenggal"));
			r.add("f.id_AktaBahagian", r.unquote("c.id_AktaBahagian"));
			r.add("f.id_AktaBab", r.unquote("d.id_AktaBab"));
			r.add("f.id_AktaSeksyen", r.unquote("e.id_AktaSeksyen"));

			sql = r
					.getSQLSelect("Tblpdtakta a,Tblpdtaktapenggal b, Tblpdtaktabahagian c,Tblpdtaktabab d,Tblpdtaktaseksyen e,Tblpdtaktasubseksyen f ");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idAkta", rs.getString("id_Akta"));
				h.put("noAkta", rs.getString("no_Akta"));
				h.put("namaAkta", rs.getString("nama_Akta"));
				h.put("idPenggal", rs.getString("id_AktaPenggal"));
				h.put("idBahagian", rs.getString("id_AktaBahagian"));
				h.put("idBab", rs.getString("id_AktaBab"));
				h.put("idSeksyen", rs.getString("id_AktaSeksyen"));
				h.put("idSubSeksyen", rs.getString("id_AktaSubSeksyen"));
				h.put("subSeksyen", rs.getString("sub_seksyen"));
				listSubSeksyenPaparById.addElement(h);

			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}
	// PAPAR SENARAI PARA MENGIKUT ID
	public void setPaparParaById(int id) throws Exception {

		Db db = null;
		listParaPaparById.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("a.id_Akta");
			r.add("a.no_Akta");
			r.add("a.nama_Akta");
			r.add("b.id_Akta");
			r.add("b.no_Penggal");
			r.add("b.id_AktaPenggal");
			r.add("b.tajuk_Penggal");
			r.add("d.id_Akta");
			r.add("c.id_AktaBahagian");
			r.add("d.no_Bab");
			r.add("d.id_AktaBab");
			r.add("d.tajuk_Bab");
			r.add("e.seksyen");
			r.add("e.id_AktaSeksyen");
			r.add("f.id_AktaSubSeksyen");
			r.add("f.sub_seksyen");
			r.add("h.no_jadual");
			r.add("h.id_Akta");
			r.add("g.id_Akta");
			r.add("g.id_AktaPenggal");
			r.add("g.id_AktaBahagian");
			r.add("g.id_AktaBab");
			r.add("g.id_AktaSeksyen");
			r.add("g.id_AktaSubSeksyen");
			r.add("g.para");
			r.add("g.id_Jadual");
			r.add("g.id_AktaPara");
			r.add("g.id_AktaPara", id);
			r.add("g.id_Akta", r.unquote("a.id_Akta"));
			r.add("g.id_AktaPenggal", r.unquote("b.id_AktaPenggal"));
			r.add("g.id_AktaBahagian", r.unquote("c.id_AktaBahagian"));
			r.add("g.id_AktaBab", r.unquote("d.id_AktaBab"));
			r.add("g.id_AktaSeksyen", r.unquote("e.id_AktaSeksyen"));
			r.add("g.id_AktaSubSeksyen", r.unquote("f.id_AktaSubSeksyen"));
			r.add("h.id_Akta",r.unquote("g.id_Akta"));
			r.add("h.id_jadual",r.unquote("g.id_jadual"));
			
			sql = r
					.getSQLSelect("Tblpdtakta a,Tblpdtaktapenggal b, Tblpdtaktabahagian c,Tblpdtaktabab d,Tblpdtaktaseksyen e,Tblpdtaktasubseksyen f,Tblpdtaktapara g,Tblpdtjadual h");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idAkta", rs.getString("id_Akta"));
				h.put("noAkta", rs.getString("no_Akta"));
				h.put("namaAkta", rs.getString("nama_Akta"));
				h.put("idPenggal", rs.getString("id_AktaPenggal"));
				h.put("idBahagian", rs.getString("id_AktaBahagian"));
				h.put("idBab", rs.getString("id_AktaBab"));
				h.put("idSeksyen", rs.getString("id_AktaSeksyen"));
				h.put("idSubSeksyen", rs.getString("id_AktaSubSeksyen"));
				h.put("idPara", rs.getString("id_AktaPara"));
				h.put("idJadual", rs.getString("id_Jadual"));
				h.put("para", rs.getString("para"));
				h.put("jadual", rs.getString("no_jadual"));
				listParaPaparById.addElement(h);

			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}
	// PAPAR SENARAI SUBPARA MENGIKUT ID
	public void setPaparSubParaById(int id) throws Exception {

		Db db = null;
		listSubParaPaparById.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("a.id_Akta");
			r.add("a.no_Akta");
			r.add("a.nama_Akta");
			r.add("b.id_AktaPenggal");
			r.add("b.tajuk_Penggal");
			r.add("d.id_AktaBab");
			r.add("d.tajuk_Bab");
			r.add("e.seksyen");
			r.add("e.id_AktaSeksyen");
			r.add("f.id_AktaSubSeksyen");
			r.add("f.sub_seksyen");
			r.add("h.id_jadual");
			r.add("h.no_jadual");
			r.add("g.para");
			r.add("g.id_AktaPara");
			r.add("i.id_Akta");
			r.add("i.id_AktaPenggal");
			r.add("i.id_AktaBahagian");
			r.add("i.id_AktaBab");
			r.add("i.id_AktaSeksyen");
			r.add("i.id_AktaSubSeksyen");
			r.add("i.id_Jadual");
			r.add("i.id_AktaPara");
			r.add("i.id_AktaSubPara");
			r.add("i.sub_Para");
			r.add("i.id_AktaSubPara", id);
			r.add("i.id_Akta", r.unquote("a.id_Akta"));
			r.add("i.id_AktaPenggal", r.unquote("b.id_AktaPenggal"));
			r.add("i.id_AktaBahagian", r.unquote("c.id_AktaBahagian"));
			r.add("i.id_AktaBab", r.unquote("d.id_AktaBab"));
			r.add("i.id_AktaSeksyen", r.unquote("e.id_AktaSeksyen"));
			r.add("i.id_AktaSubSeksyen", r.unquote("f.id_AktaSubSeksyen"));
			r.add("i.id_AktaPara",r.unquote("g.id_AktaPara"));
			r.add("i.id_jadual",r.unquote("h.id_jadual"));
			
			sql = r
					.getSQLSelect("Tblpdtakta a,Tblpdtaktapenggal b, Tblpdtaktabahagian c,Tblpdtaktabab d,Tblpdtaktaseksyen e,Tblpdtaktasubseksyen f,Tblpdtaktapara g,Tblpdtjadual h, Tblpdtaktasubpara i");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();

				h.put("idAkta", rs.getString("id_Akta"));
				h.put("noAkta", rs.getString("no_Akta"));
				h.put("namaAkta", rs.getString("nama_Akta"));
				h.put("idPenggal", rs.getString("id_AktaPenggal"));
				h.put("idBahagian", rs.getString("id_AktaBahagian"));
				h.put("idBab", rs.getString("id_AktaBab"));
				h.put("idSeksyen", rs.getString("id_AktaSeksyen"));
				h.put("idSubSeksyen", rs.getString("id_AktaSubSeksyen"));
				h.put("idPara", rs.getString("id_AktaPara"));
				h.put("idJadual", rs.getString("id_Jadual"));
				h.put("idSubPara", rs.getString("id_AktaSubPara"));
				h.put("subPara", rs.getString("sub_para"));
				listSubParaPaparById.addElement(h);

			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}
	// PAPAR SENARAI JADUALPARA MENGIKUT ID
	public void setPaparJadualParaById(int id) throws Exception {

		Db db = null;
		listJadualParaPaparById.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("a.id_Akta");
			r.add("a.no_Akta");
			r.add("a.nama_Akta");
			r.add("h.id_Jadual");
			r.add("h.no_Jadual");
			r.add("h.nama_Jadual");
			r.add("j.id_Jadual");
			r.add("j.para");
			r.add("j.id_Akta");
			r.add("j.id_JadualPara");
			r.add("j.id_JadualPara", id);
			r.add("a.id_Akta", r.unquote("j.id_Akta"));
			r.add("a.id_Akta", r.unquote("h.id_Akta"));
			r.add("h.id_Jadual", r.unquote("j.id_Jadual"));

			sql = r
					.getSQLSelect("Tblpdtakta a,Tblpdtjadual h, Tblpdtjadualpara j");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();

				h.put("idAkta", rs.getString("id_Akta"));
				h.put("idJadualPara", rs.getString("id_JadualPara"));
				h.put("noAkta", rs.getString("no_Akta"));
				h.put("namaAkta", rs.getString("nama_Akta"));
				h.put("idJadual", rs.getString("id_Jadual"));
				h.put("namaJadual", rs.getString("nama_Jadual"));
				h.put("para", rs.getString("para"));
				listJadualParaPaparById.addElement(h);

			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}
	// PAPAR SENARAI JADUALSUBPARA MENGIKUT ID
	public void setPaparJadualSubParaById(int id) throws Exception {

		Db db = null;
		listJadualSubParaPaparById.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("a.id_Akta");
			r.add("a.no_Akta");
			r.add("a.nama_Akta");
			r.add("h.id_Jadual");
			r.add("h.no_Jadual");
			r.add("j.id_JadualPara");
			r.add("j.para");
			r.add("j.id_Akta");
			r.add("k.id_JadualPara");
			r.add("k.sub_Para");
			r.add("k.id_JadualSubpara");
			r.add("k.id_JadualSubpara", id);
			r.add("a.id_Akta", r.unquote("k.id_Akta"));
			r.add("h.id_Jadual", r.unquote("k.id_Jadual"));
			r.add("j.id_JadualPara", r.unquote("k.id_JadualPara"));

			sql = r
					.getSQLSelect("Tblpdtakta a, Tblpdtjadual h,Tblpdtjadualpara j,Tblpdtjadualsubpara k");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();

				h.put("idAkta", rs.getString("id_Akta"));
				h.put("idJadualSubPara", rs.getString("id_JadualSubpara"));
				h.put("noAkta", rs.getString("no_Akta"));
				h.put("namaAkta", rs.getString("nama_Akta"));
				h.put("idJadual", rs.getString("id_Jadual"));
				h.put("idJadualPara", rs.getString("id_JadualPara"));
				h.put("noJadual", rs.getString("no_Jadual"));
				h.put("subPara", rs.getString("sub_Para"));
				listJadualSubParaPaparById.addElement(h);

			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}
	// PAPAR SENARAI JADUALSUBSUBPARA MENGIKUT ID
	public void setPaparJadualSubSubParaById(int id) throws Exception {

		Db db = null;
		listJadualSubSubParaPaparById.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("a.id_Akta");
			r.add("a.no_Akta");
			r.add("a.nama_Akta");
			r.add("h.id_Jadual");
			r.add("h.no_Jadual");
			r.add("j.id_JadualPara");
			r.add("j.para");
			r.add("j.id_Akta");
			r.add("k.id_JadualPara");
			r.add("k.sub_Para");
			r.add("k.id_JadualSubpara");
			r.add("l.id_JadualSubsubpara");
			r.add("l.sub_Subpara");
			r.add("l.id_JadualSubsubpara", id);
			r.add("a.id_Akta", r.unquote("l.id_Akta"));
			r.add("h.id_Jadual", r.unquote("l.id_Jadual"));
			r.add("j.id_JadualPara", r.unquote("l.id_JadualPara"));
			r.add("k.id_JadualSubpara", r.unquote("l.id_JadualSubpara"));

			sql = r
					.getSQLSelect("Tblpdtakta a, Tblpdtjadual h,Tblpdtjadualpara j,Tblpdtjadualsubpara k,Tblpdtjadualsubsubpara l");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();

				h.put("idAkta", rs.getString("id_Akta"));
				h.put("idJadualSubSubPara", rs.getString("id_JadualSubSubPara"));
				h.put("noAkta", rs.getString("no_Akta"));
				h.put("namaAkta", rs.getString("nama_Akta"));
				h.put("idJadual", rs.getString("id_Jadual"));
				h.put("idJadualPara", rs.getString("id_JadualPara"));
				h.put("idJadualSubPara", rs.getString("id_JadualSubpara"));
				h.put("noJadual", rs.getString("no_Jadual"));
				h.put("subPara", rs.getString("sub_Para"));
				h.put("subSubPara", rs.getString("sub_SubPara"));
				listJadualSubSubParaPaparById.addElement(h);

			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}
	// PAPAR SENARAI SEMUA PENGGAL
	public void setSenaraiPenggal(int id) throws Exception {
		Db db = null;
		listPenggalAll.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AKTA,A.NO_AKTA,A.NAMA_AKTA, B.ID_AKTA, B.TAJUK_PENGGAL,B.NO_PENGGAL, B.ID_AKTAPENGGAL"
					+ " FROM TBLPDTAKTA A, TBLPDTAKTAPENGGAL B WHERE A.ID_AKTA = B.ID_AKTA AND A.ID_AKTA = "
					+ id;

			sql = sql + " ORDER BY B.ID_AKTAPENGGAL";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("idAkta", rs.getString("ID_AKTA"));
				h.put("idPenggal", rs.getString("ID_AKTAPENGGAL"));
				h.put("noPenggal", rs.getString("NO_PENGGAL") == null ? "" : rs
						.getString("NO_PENGGAL"));
				h.put("tajukPenggal",
						rs.getString("TAJUK_PENGGAL") == null ? "" : rs
								.getString("TAJUK_PENGGAL"));
				listPenggalAll.addElement(h);
				no++;
				count++;

			}
			if (count == 0) {
				h = new Hashtable();
				h.put("no", "");
				h.put("idPenggal", "");
				h.put("noPenggal", "Tiada rekod.");
				h.put("tajukPenggal", "");
				listPenggalAll.addElement(h);
			}

		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}

	// PAPAR SENARAI SEMUA BAHAGIAN
	public void setSenaraiBahagian(int id) throws Exception {
		Db db = null;
		listBahagianAll.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AKTA,B.ID_AKTAPENGGAL,B.NO_BAHAGIAN, B.TAJUK_BAHAGIAN,C.TAJUK_PENGGAL,C.ID_AKTAPENGGAL,B.ID_AKTABAHAGIAN"
					+ " FROM TBLPDTAKTA A, TBLPDTAKTABAHAGIAN B,TBLPDTAKTAPENGGAL C WHERE B.ID_AKTAPENGGAL = C.ID_AKTAPENGGAL AND A.ID_AKTA = B.ID_AKTA AND B.ID_AKTA = "
					+ id;

			sql = sql + " ORDER BY B.ID_AKTABAHAGIAN";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("idAkta", rs.getString("ID_AKTA"));
				h.put("idBahagian", rs.getString("ID_AKTABAHAGIAN"));
				h.put("tajukBahagian",
						rs.getString("TAJUK_BAHAGIAN") == null ? "" : rs
								.getString("TAJUK_BAHAGIAN"));
				h.put("noBahagian", rs.getString("NO_BAHAGIAN") == null ? ""
						: rs.getString("NO_BAHAGIAN"));
				h.put("tajukPenggal",
						rs.getString("TAJUK_PENGGAL") == null ? "" : rs
								.getString("TAJUK_PENGGAL"));
				h.put("idPenggal",
						rs.getString("ID_AKTAPENGGAL") == null ? "" : rs
								.getString("ID_AKTAPENGGAL"));
				listBahagianAll.addElement(h);
				no++;
				count++;

			}
			if (count == 0) {
				h = new Hashtable();
				h.put("no", "");
				h.put("idAkta", "");
				h.put("tajukPenggal", "Tiada rekod.");
				h.put("noBahagian", "");
				h.put("tajukBahagian", "");
				listBahagianAll.addElement(h);
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}

	// PAPAR SEMUA BAB
	public void setSenaraiBab(int id) throws Exception {
		Db db = null;
		listBabAll.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AKTA,B.TAJUK_PENGGAL,B.ID_AKTAPENGGAL,C.ID_AKTABAHAGIAN,C.TAJUK_BAHAGIAN,D.ID_AKTABAHAGIAN,D.TAJUK_BAB,D.NO_BAB,D.ID_AKTABAB,D.ID_AKTAPENGGAL"
					+ " FROM TBLPDTAKTA A, TBLPDTAKTAPENGGAL B , TBLPDTAKTABAHAGIAN C, TBLPDTAKTABAB D"
					+ " WHERE A.ID_AKTA = D.ID_AKTA AND C.ID_AKTABAHAGIAN = D.ID_AKTABAHAGIAN AND B.ID_AKTAPENGGAL = D.ID_AKTAPENGGAL AND A.ID_AKTA = "
					+ id;

			sql = sql + " ORDER BY D.ID_AKTABAB";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("idAkta", rs.getString("ID_AKTA"));
				h.put("idBab", rs.getString("ID_AKTABAB") == null ? "" : rs
						.getString("ID_AKTABAB"));
				h.put("tajukPenggal",
						rs.getString("TAJUK_PENGGAL") == null ? "" : rs
								.getString("TAJUK_PENGGAL"));
				h.put("noBab", rs.getString("NO_BAB") == null ? "" : rs
						.getString("NO_BAB"));
				h.put("tajukBab", rs.getString("TAJUK_BAB") == null ? "" : rs
						.getString("TAJUK_BAB"));
				h.put("tajukBahagian",
						rs.getString("TAJUK_BAHAGIAN") == null ? "" : rs
								.getString("TAJUK_BAHAGIAN"));
				listBabAll.addElement(h);
				no++;
				count++;

			}
			if (count == 0) {
				h = new Hashtable();
				h.put("no", "");
				h.put("idAkta", "");
				h.put("noPenggal", " ");
				h.put("tajukPenggal", "Tiada rekod.");
				h.put("noBab", "");
				h.put("tajukBab", "");
				h.put("tajukBahagian", "");
				listBabAll.addElement(h);
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}

	// PAPAR SEMUA SEKSYEN
	public void setSenaraiSeksyen(int id) throws Exception {
		Db db = null;
		listSeksyenAll.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AKTA,B.TAJUK_PENGGAL,B.ID_AKTAPENGGAL,C.ID_AKTABAB,C.TAJUK_BAB,D.TAJUK_BAHAGIAN, D.ID_AKTABAHAGIAN,"
					+ " E.SEKSYEN,E.ID_AKTAPENGGAL,E.ID_AKTABAHAGIAN, E.ID_AKTA,E.ID_AKTABAB,E.ID_AKTASEKSYEN "
					+ " FROM TBLPDTAKTA A, TBLPDTAKTAPENGGAL B , TBLPDTAKTABAB C, TBLPDTAKTABAHAGIAN D, TBLPDTAKTASEKSYEN E "
					+ " WHERE A.ID_AKTA = E.ID_AKTA AND B.ID_AKTAPENGGAL = E.ID_AKTAPENGGAL AND D.ID_AKTABAHAGIAN = E.ID_AKTABAHAGIAN "
					+ " AND E.ID_AKTABAB=C.ID_AKTABAB AND A.ID_AKTA = "
					+ id;

			sql = sql + " ORDER BY E.ID_AKTASEKSYEN";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("idAkta", rs.getString("ID_AKTA"));
				h.put("idSeksyen",
						rs.getString("ID_AKTASEKSYEN") == null ? "" : rs
								.getString("ID_AKTASEKSYEN"));
				h.put("tajukPenggal",
						rs.getString("TAJUK_PENGGAL") == null ? "" : rs
								.getString("TAJUK_PENGGAL"));
				h.put("tajukBahagian",
						rs.getString("TAJUK_BAHAGIAN") == null ? "" : rs
								.getString("TAJUK_BAHAGIAN"));
				h.put("tajukBab", rs.getString("TAJUK_BAB") == null ? "" : rs
						.getString("TAJUK_BAB"));
				h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs
						.getString("SEKSYEN"));
				listSeksyenAll.addElement(h);
				no++;
				count++;

			}
			if (count == 0) {
				h = new Hashtable();
				h.put("no", "");
				h.put("idAkta", "");
				h.put("noPenggal", " ");
				h.put("tajukPenggal", "Tiada rekod.");
				h.put("tajukBab", "");
				h.put("tajukBahagian", "");
				h.put("seksyen", "");
				listSeksyenAll.addElement(h);
			}

		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}

	// PAPAR SEMUA SUB SEKSYEN
	public void setSenaraiSubSeksyen(int id) throws Exception {
		Db db = null;
		listSubSeksyenAll.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AKTA,B.TAJUK_PENGGAL,B.ID_AKTAPENGGAL,C.ID_AKTABAB,C.TAJUK_BAB,D.TAJUK_BAHAGIAN, D.ID_AKTABAHAGIAN,"
					+ " E.SEKSYEN,E.ID_AKTASEKSYEN,F.ID_AKTAPENGGAL,F.ID_AKTABAHAGIAN, F.ID_AKTA,F.ID_AKTABAB,F.ID_AKTASUBSEKSYEN,F.ID_AKTASEKSYEN,F.SUB_SEKSYEN"
					+ " FROM TBLPDTAKTA A, TBLPDTAKTAPENGGAL B , TBLPDTAKTABAB C, TBLPDTAKTABAHAGIAN D, TBLPDTAKTASEKSYEN E, TBLPDTAKTASUBSEKSYEN F "
					+ " WHERE A.ID_AKTA = F.ID_AKTA AND B.ID_AKTAPENGGAL = F.ID_AKTAPENGGAL AND D.ID_AKTABAHAGIAN = F.ID_AKTABAHAGIAN "
					+ " AND F.ID_AKTABAB=C.ID_AKTABAB AND F.ID_AKTASEKSYEN=E.ID_AKTASEKSYEN AND A.ID_AKTA = "
					+ id;

			sql = sql + " ORDER BY F.ID_AKTASUBSEKSYEN";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("idAkta", rs.getString("ID_AKTA"));
				h.put("idSubSeksyen",
						rs.getString("ID_AKTASUBSEKSYEN") == null ? "" : rs
								.getString("ID_AKTASUBSEKSYEN"));
				h.put("tajukPenggal",
						rs.getString("TAJUK_PENGGAL") == null ? "" : rs
								.getString("TAJUK_PENGGAL"));
				h.put("tajukBahagian",
						rs.getString("TAJUK_BAHAGIAN") == null ? "" : rs
								.getString("TAJUK_BAHAGIAN"));
				h.put("tajukBab", rs.getString("TAJUK_BAB") == null ? "" : rs
						.getString("TAJUK_BAB"));
				h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs
						.getString("SEKSYEN"));
				h.put("subSeksyen", rs.getString("SUB_SEKSYEN") == null ? ""
						: rs.getString("SUB_SEKSYEN"));
				listSubSeksyenAll.addElement(h);
				no++;
				count++;

			}
			if (count == 0) {
				h = new Hashtable();
				h.put("no", "");
				h.put("idAkta", "");
				h.put("noPenggal", " ");
				h.put("tajukPenggal", "Tiada rekod.");
				h.put("tajukBab", "");
				h.put("tajukBahagian", "");
				h.put("seksyen", "");
				h.put("subSeksyen", "");
				listSubSeksyenAll.addElement(h);
			}

		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}

	// PAPAR SEMUA PARA
	public void setSenaraiPara(int id) throws Exception {
		Db db = null;
		listParaAll.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AKTA,B.TAJUK_PENGGAL,B.ID_AKTAPENGGAL,C.ID_AKTABAB,C.TAJUK_BAB,D.TAJUK_BAHAGIAN, D.ID_AKTABAHAGIAN,"
					+ " E.SEKSYEN,E.ID_AKTASEKSYEN,F.ID_AKTASUBSEKSYEN,F.SUB_SEKSYEN,G.ID_AKTAPENGGAL,G.ID_AKTABAHAGIAN,"
					+ " G.ID_AKTA,G.ID_AKTABAB,G.ID_AKTASUBSEKSYEN,G.ID_AKTASEKSYEN,G.ID_AKTAPARA,G.PARA,G.ID_JADUAL,H.ID_JADUAL, H.NO_JADUAL, H.ID_AKTA"
					+ " FROM TBLPDTAKTA A, TBLPDTAKTAPENGGAL B , TBLPDTAKTABAB C, TBLPDTAKTABAHAGIAN D, TBLPDTAKTASEKSYEN E, TBLPDTAKTASUBSEKSYEN F, TBLPDTAKTAPARA G, TBLPDTJADUAL H"
					+ " WHERE A.ID_AKTA = G.ID_AKTA AND B.ID_AKTAPENGGAL = G.ID_AKTAPENGGAL AND D.ID_AKTABAHAGIAN = G.ID_AKTABAHAGIAN AND H.ID_JADUAL=G.ID_JADUAL"
					+ " AND H.ID_AKTA=G.ID_AKTA AND C.ID_AKTABAB = G.ID_AKTABAB AND E.ID_AKTASEKSYEN=G.ID_AKTASEKSYEN AND F.ID_AKTASUBSEKSYEN = G.ID_AKTASUBSEKSYEN AND A.ID_AKTA = "
					+ id;

			sql = sql + " ORDER BY G.ID_AKTAPARA";
			myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("idAkta", rs.getString("ID_AKTA"));
				h.put("idPara", rs.getString("ID_AKTAPARA") == null ? ""
						: rs.getString("ID_AKTAPARA"));
				h.put("idJadual", rs.getString("ID_JADUAL") == null ? ""
						: rs.getString("ID_JADUAL"));
				h.put("tajukPenggal",
						rs.getString("TAJUK_PENGGAL") == null ? "" : rs
								.getString("TAJUK_PENGGAL"));
				h.put("tajukBahagian",
						rs.getString("TAJUK_BAHAGIAN") == null ? "" : rs
								.getString("TAJUK_BAHAGIAN"));
				h.put("tajukBab", rs.getString("TAJUK_BAB") == null ? "" : rs
						.getString("TAJUK_BAB"));
				h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs
						.getString("SEKSYEN"));
				h.put("subSeksyen", rs.getString("SUB_SEKSYEN") == null ? ""
						: rs.getString("SUB_SEKSYEN"));
				h.put("para", rs.getString("PARA") == null ? "" : rs
						.getString("PARA"));
				h.put("jadual", rs.getString("NO_JADUAL") == null ? "" : rs
						.getString("NO_JADUAL"));
				listParaAll.addElement(h);
				no++;
				count++;

			}
			if (count == 0) {
				h = new Hashtable();
				h.put("no", "");
				h.put("idAkta", "");
				h.put("noPenggal", " ");
				h.put("tajukPenggal", "Tiada rekod.");
				h.put("tajukBab", "");
				h.put("tajukBahagian", "");
				h.put("seksyen", "");
				h.put("subSeksyen", "");
				h.put("para", "");
				h.put("jadual", "");
				listParaAll.addElement(h);
			}

		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}
	// PAPAR SEMUA SUBPARA
	public void setSenaraiSubPara(int id) throws Exception {
		Db db = null;
		listSubParaAll.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AKTA,B.TAJUK_PENGGAL,B.ID_AKTAPENGGAL,C.ID_AKTABAB,C.TAJUK_BAB,D.TAJUK_BAHAGIAN, D.ID_AKTABAHAGIAN,"
					+ " E.SEKSYEN,E.ID_AKTASEKSYEN,F.ID_AKTASUBSEKSYEN, F.SUB_SEKSYEN,G.PARA,G.ID_AKTAPARA,H.ID_AKTA,H.ID_JADUAL, H.NO_JADUAL,I.SUB_PARA,I.ID_AKTASUBPARA," 
					+ " I.ID_AKTA,I.ID_AKTAPENGGAL,I.ID_AKTABAHAGIAN,I.ID_AKTABAB,I.ID_AKTASEKSYEN,I.ID_AKTASUBSEKSYEN,I.ID_AKTAPARA,I.ID_JADUAL"
					+ " FROM TBLPDTAKTA A, TBLPDTAKTAPENGGAL B , TBLPDTAKTABAB C, TBLPDTAKTABAHAGIAN D, TBLPDTAKTASEKSYEN E, TBLPDTAKTASUBSEKSYEN F, TBLPDTAKTAPARA G, TBLPDTJADUAL H, TBLPDTAKTASUBPARA I"
					+ " WHERE A.ID_AKTA = I.ID_AKTA AND B.ID_AKTAPENGGAL = I.ID_AKTAPENGGAL AND C.ID_AKTABAB = I.ID_AKTABAB AND D.ID_AKTABAHAGIAN = I.ID_AKTABAHAGIAN"
					+ " AND E.ID_AKTASEKSYEN = I.ID_AKTASEKSYEN AND F.ID_AKTASUBSEKSYEN = I.ID_AKTASUBSEKSYEN AND G.ID_AKTAPARA = I.ID_AKTAPARA AND H.ID_JADUAL = I.ID_JADUAL"
					+ " AND A.ID_AKTA ="+ id;

			sql = sql + " ORDER BY I.ID_AKTASUBPARA";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("idAkta", rs.getString("ID_AKTA"));
				h.put("idPara", rs.getString("ID_AKTAPARA") == null ? ""
						: rs.getString("ID_AKTAPARA"));
				h.put("idJadual", rs.getString("ID_JADUAL") == null ? ""
						: rs.getString("ID_JADUAL"));
				h.put("idSubPara", rs.getString("ID_AKTASUBPARA") == null ? ""
						: rs.getString("ID_AKTASUBPARA"));
				h.put("tajukPenggal",
						rs.getString("TAJUK_PENGGAL") == null ? "" : rs
								.getString("TAJUK_PENGGAL"));
				h.put("tajukBahagian",
						rs.getString("TAJUK_BAHAGIAN") == null ? "" : rs
								.getString("TAJUK_BAHAGIAN"));
				h.put("tajukBab", rs.getString("TAJUK_BAB") == null ? "" : rs
						.getString("TAJUK_BAB"));
				h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs
						.getString("SEKSYEN"));
				h.put("subSeksyen", rs.getString("SUB_SEKSYEN") == null ? ""
						: rs.getString("SUB_SEKSYEN"));
				h.put("para", rs.getString("PARA") == null ? "" : rs
						.getString("PARA"));
				h.put("subPara", rs.getString("SUB_PARA") == null ? "" : rs
						.getString("SUB_PARA"));
				h.put("jadual", rs.getString("NO_JADUAL") == null ? "" : rs
						.getString("NO_JADUAL"));
				listSubParaAll.addElement(h);
				no++;
				count++;

			}
			if (count == 0) {
				h = new Hashtable();
				h.put("no", "");
				h.put("idAkta", "");
				h.put("noPenggal", " ");
				h.put("tajukPenggal", "Tiada rekod.");
				h.put("tajukBab", "");
				h.put("tajukBahagian", "");
				h.put("seksyen", "");
				h.put("subSeksyen", "");
				h.put("para", "");
				h.put("subPara", "");
				h.put("jadual", "");
				listSubParaAll.addElement(h);
			}

		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}
	// PAPAR SENARAI SEMUA JADUALPARA
	public void setSenaraiJadualPara(int id) throws Exception {
		Db db = null;
		listJadualParaAll.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AKTA,A.NO_AKTA,A.NAMA_AKTA, H.ID_JADUAL,H.NO_JADUAL,H.NAMA_JADUAL,J.ID_AKTA, J.ID_JADUAL, J.ID_JADUALPARA, J.PARA"
					+ " FROM TBLPDTAKTA A,TBLPDTJADUAL H, TBLPDTJADUALPARA J " +
							"WHERE A.ID_AKTA = J.ID_AKTA AND " +
							"H.ID_JADUAL = J.ID_JADUAL AND A.ID_AKTA = "
					+ id;

			sql = sql + " ORDER BY J.ID_JADUALPARA";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("idAkta", rs.getString("ID_AKTA"));
				h.put("idJadual", rs.getString("ID_JADUAL"));
				h.put("idJadualPara", rs.getString("ID_JADUALPARA"));
				h.put("noJadual",rs.getString("NO_JADUAL"));
				h.put("namaJadual", rs.getString("NAMA_JADUAL") == null ? "" : rs
						.getString("NAMA_JADUAL"));
				h.put("para",
						rs.getString("PARA") == null ? "" : rs
								.getString("PARA"));
				listJadualParaAll.addElement(h);
				no++;
				count++;

			}
			if (count == 0) {
				h = new Hashtable();
				h.put("no", "");
				h.put("idJadualPara", "");
				h.put("noJadual", "Tiada rekod.");
				h.put("namaJadual", "");
				h.put("para", "");
				listJadualParaAll.addElement(h);
			}

		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}
	// PAPAR SENARAI SEMUA JADUALSUBPARA
	public void setSenaraiJadualSubPara(int id) throws Exception {
		Db db = null;
		listJadualSubParaAll.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AKTA,A.NO_AKTA,A.NAMA_AKTA, H.ID_JADUAL,H.NO_JADUAL,J.ID_AKTA, J.ID_JADUALPARA, K.ID_JADUALSUBPARA,J.PARA,K.ID_JADUAL,K.ID_JADUALPARA,K.SUB_PARA "
					+ " FROM TBLPDTAKTA A,TBLPDTJADUAL H, TBLPDTJADUALPARA J, TBLPDTJADUALSUBPARA K "
					+ " WHERE A.ID_AKTA = K.ID_AKTA AND H.ID_JADUAL = K.ID_JADUAL AND J.ID_JADUALPARA=K.ID_JADUALPARA AND A.ID_AKTA = "
					+ id;

			sql = sql + " ORDER BY K.ID_JADUALSUBPARA";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("idAkta", rs.getString("ID_AKTA"));
				h.put("idJadualSubPara", rs.getString("ID_JADUALSUBPARA"));
				h.put("noJadual",rs.getString("NO_JADUAL"));
				h.put("subPara", rs.getString("SUB_PARA") == null ? "" : rs.getString("SUB_PARA"));
				h.put("para",rs.getString("PARA") == null ? "" : rs.getString("PARA"));
				listJadualSubParaAll.addElement(h);
				no++;
				count++;

			}
			if (count == 0) {
				h = new Hashtable();
				h.put("no", "");
				h.put("idJadualSubPara", "");
				h.put("noJadual", "Tiada rekod.");
				h.put("para", "");
				h.put("subPara", "");
				listJadualSubParaAll.addElement(h);
			}

		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}
	// PAPAR SENARAI SEMUA JADUALSUBSUBPARA
	public void setSenaraiJadualSubSubPara(int id) throws Exception {
		Db db = null;
		listJadualSubSubParaAll.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AKTA,A.NO_AKTA,A.NAMA_AKTA, H.ID_JADUAL,H.NO_JADUAL, J.ID_JADUALPARA,J.PARA, K.ID_JADUALSUBPARA,K.SUB_PARA,L.ID_AKTA,L.ID_JADUAL,L.ID_JADUALPARA,L.ID_JADUALSUBPARA,L.ID_JADUALSUBSUBPARA,L.SUB_SUBPARA "
					+ " FROM TBLPDTAKTA A,TBLPDTJADUAL H, TBLPDTJADUALPARA J, TBLPDTJADUALSUBPARA K, TBLPDTJADUALSUBSUBPARA L "
					+ " WHERE A.ID_AKTA = L.ID_AKTA AND H.ID_JADUAL = L.ID_JADUAL AND J.ID_JADUALPARA = L.ID_JADUALPARA AND K.ID_JADUALSUBPARA = L.ID_JADUALSUBPARA AND A.ID_AKTA = "
					+ id;

			sql = sql + " ORDER BY L.ID_JADUALSUBSUBPARA";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("idAkta", rs.getString("ID_AKTA"));
				h.put("idJadualSubSubPara", rs.getString("ID_JADUALSUBSUBPARA"));
				h.put("idJadual", rs.getString("ID_JADUAL"));
				h.put("idJadualSubPara", rs.getString("ID_JADUALSUBPARA"));
				h.put("noJadual",rs.getString("NO_JADUAL"));
				h.put("para",rs.getString("PARA"));
				h.put("subPara",rs.getString("SUB_PARA"));
				h.put("subSubPara", rs.getString("SUB_SUBPARA") == null ? "" : rs
						.getString("SUB_SUBPARA"));
				listJadualSubSubParaAll.addElement(h);
				no++;
				count++;

			}
			if (count == 0) {
				h = new Hashtable();
				h.put("no", "");
				h.put("idJadualSubSubPara", "");
				h.put("noJadual", "Tiada rekod.");
				h.put("para", "");
				h.put("subPara", "");
				h.put("subSubPara", "");
				listJadualSubSubParaAll.addElement(h);
			}

		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}
	
	// RETURN LIST SEMUA Akta
	public Vector getPaparAkta() {

		return listAktaAll;
	}
	// RETURN LIST DAN CARIAN Akta
	public Vector getCarianPaparAkta() {

		return listCarianPaparAkta;
	}
	// RETURN LIST SEMUA PENGGAL
	public Vector getPaparPenggal() {

		return listPenggalAll;
	}
	// RETURN PENGGAL BY ID
	public Vector getPaparPenggalById() {

		return listPenggalPaparById;
	}
	// RETURN LIST SEMUA BAHAGIAN
	public Vector getPaparBahagian() {

		return listBahagianAll;
	}
	// RETURN BAHAGIAN BY ID
	public Vector getPaparBahagianById() {

		return listBahagianPaparById;
	}
	// RETURN LIST SEMUA BAB
	public Vector getPaparBab() {

		return listBabAll;
	}
	// RETURN BAB BY ID
	public Vector getPaparBabById() {

		return listBabPaparById;
	}
	// RETURN LIST SEMUA SEKSYEN
	public Vector getPaparSeksyen() {

		return listSeksyenAll;
	}
	// RETURN SEKSYEN BY ID
	public Vector getPaparSeksyenById() {

		return listSeksyenPaparById;
	}
	// RETURN LIST SEMUA SUBSEKSYEN
	public Vector getPaparSubSeksyen() {

		return listSubSeksyenAll;
	}
	// RETURN SUBSEKSYEN BY ID
	public Vector getPaparSubSeksyenById() {

		return listSubSeksyenPaparById;
	}
	// RETURN LIST SEMUA PARA
	public Vector getPaparPara() {

		return listParaAll;
	}
	// RETURN PARA BY ID
	public Vector getPaparParaById() {

		return listParaPaparById;
	}
	// RETURN LIST SEMUA SUBPARA
	public Vector getPaparSubPara() {

		return listSubParaAll;
	}
	// RETURN SUBPARA BY ID
	public Vector getPaparSubParaById() {

		return listSubParaPaparById;
	}
	// RETURN LIST SEMUA JADUALPARA
	public Vector getPaparJadualPara() {

		return listJadualParaAll;
	}
	// RETURN JADUALPARA BY ID
	public Vector getPaparJadualParaById() {

		return listJadualParaPaparById;
	}
	// RETURN LIST SEMUA JADUALSUBPARA
	public Vector getPaparJadualSubPara() {

		return listJadualSubParaAll;
	}
	// RETURN JADUALSUBSUBPARA BY ID
	public Vector getPaparJadualSubParaById() {

		return listJadualSubParaPaparById;
	}
	// RETURN LIST SEMUA JADUALSUBSUBPARA
	public Vector getPaparJadualSubSubPara() {

		return listJadualSubSubParaAll;
	}
	// RETURN JADUALSUBSUBPARA BY ID
	public Vector getPaparJadualSubSubParaById() {

		return listJadualSubSubParaPaparById;
	}
	
	////
	


}
