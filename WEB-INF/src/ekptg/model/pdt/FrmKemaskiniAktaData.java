package ekptg.model.pdt;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmKemaskiniAktaData {
	private static Logger myLogger = Logger.getLogger(FrmKemaskiniAktaData.class);

	// UPDATE ENAKMEN
	public void updateAkta(Hashtable data) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_Akta", data.get("idAkta"));
			r.add("NO_Akta", data.get("noAkta"));
			r.add("NAMA_Akta", data.get("namaAkta"));
			r.add("TARIKH_KUATKUASA", data.get("tarikhKuatkusa"));
			r.add("TARIKH_DAFTAR", data.get("tarikhDaftar"));
			r.add("TARIKH_MANSUH", data.get("tarikhMansuh"));
			r.add("ID_FAIL", data.get("idFail"));
			r.add("ID_SEKSYEN", data.get("idSeksyen"));
			r.add("ID_KEMASKINI", data.get("idKemaskini"));
			r.add("TARIKH_KEMASKINI", data.get("tarikhKemaskini"));
			r.add("CATATAN", data.get("catatan"));
			r.add("NO_WARTA", data.get("noWarta"));
			r.add("TARIKH_WARTA", data.get("tarikhWarta"));
			r.add("ID_TARAFKESELAMATAN", data.get("idKeselamatan"));

			sql = r.getSQLUpdate("TBLPDTAkta");
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

	// UPDATE PENGGAL
	public void updatePenggal(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_AktaPENGGAL", data.get("idPenggal"));
			r.add("NO_Akta", data.get("noAkta"));
			r.add("NAMA_Akta", data.get("namaAkta"));
			r.add("NO_PENGGAL", data.get("noPenggal"));
			r.add("TAJUK_PENGGAL", data.get("tajukPenggal"));
			r.add("ID_KEMASKINI", data.get("idKemaskini"));
			r.add("TARIKH_KEMASKINI", data.get("tarikhKemaskini"));

			sql = r.getSQLUpdate("TBLPDTAktaPENGGAL");
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

	// UPDATE BAHAGIAN
	public void updateBahagian(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_AktaBAHAGIAN", data.get("idBahagian"));
			r.add("TAJUK_BAHAGIAN", data.get("tajukBahagian"));
			r.add("ID_AktaPENGGAL", data.get("idPenggal"));
			r.add("ID_MASUK", data.get("idMasuk"));
			r.add("ID_Akta", data.get("idAkta"));
			r.add("NO_BAHAGIAN", data.get("noBahagian"));
			r.add("ID_KEMASKINI", data.get("idKemaskini"));
			r.add("TARIKH_KEMASKINI", data.get("tarikhKemaskini"));

			sql = r.getSQLUpdate("TBLPDTAktaBAHAGIAN");
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

	// UPDATE BAB
	public void updateBab(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_AktaBAB", data.get("idBab"));
			r.add("ID_Akta", data.get("idAkta"));
			r.add("ID_AktaPENGGAL", data.get("idAktaPenggal"));
			r.add("ID_AktaBAHAGIAN", data.get("idAktaBahagian"));
			r.add("ID_KEMASKINI", data.get("idKemaskini"));
			r.add("TARIKH_KEMASKINI", data.get("tarikhKemaskini"));
			r.add("TAJUK_BAB", data.get("tajukBab"));
			r.add("NO_BAB", data.get("noBab"));

			sql = r.getSQLUpdate("TBLPDTAktaBAB");
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

	// UPDATE SEKSYEN
	public void updateSeksyen(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_AktaSEKSYEN", data.get("idSeksyen"));
			r.add("SEKSYEN", data.get("seksyen"));
			r.add("ID_Akta", data.get("idAkta"));
			r.add("ID_AktaPENGGAL", data.get("idAktaPenggal"));
			r.add("ID_AktaBAHAGIAN", data.get("idAktaBahagian"));
			r.add("ID_AktaBAB", data.get("idAktaBab"));
			r.add("ID_KEMASKINI", data.get("idKemaskini"));
			r.add("TARIKH_KEMASKINI", data.get("tarikhKemaskini"));

			sql = r.getSQLUpdate("TBLPDTAktaSEKSYEN");
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

	// UPDATE SUBSEKSYEN
	public void updateSubSeksyen(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_AktaSUBSEKSYEN", data.get("idSubSeksyen"));
			r.add("ID_Akta", data.get("idAkta"));
			r.add("ID_AktaPENGGAL", data.get("idAktaPenggal"));
			r.add("ID_AktaBAHAGIAN", data.get("idAktaBahagian"));
			r.add("ID_AktaBAB", data.get("idAktaBab"));
			r.add("ID_AktaSEKSYEN", data.get("idAktaSeksyen"));
			r.add("SUB_SEKSYEN", data.get("subSeksyen"));
			r.add("ID_KEMASKINI", data.get("idKemaskini"));
			r.add("TARIKH_KEMASKINI", data.get("tarikhKemaskini"));

			sql = r.getSQLUpdate("TBLPDTAktaSUBSEKSYEN");
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

	// UPDATE PARA
	public void updatePara(Hashtable data) throws Exception {
		Db dbPara = new Db();
		String sqlPara = "";

		try {
			Statement stmtPara = dbPara.getStatement();
			SQLRenderer rPara = new SQLRenderer();

			rPara.update("ID_AktaPARA", data.get("idPara"));
			rPara.add("ID_Akta", data.get("idAkta"));
			rPara.add("ID_AktaPENGGAL", data.get("idAktaPenggal"));
			rPara.add("ID_AktaBAHAGIAN", data.get("idAktaBahagian"));
			rPara.add("ID_AktaBAB", data.get("idAktaBab"));
			rPara.add("ID_AktaSEKSYEN", data.get("idAktaSeksyen"));
			rPara.add("ID_AktaSUBSEKSYEN", data.get("idAktaSubSeksyen"));
			rPara.add("PARA", data.get("para"));
			rPara.add("ID_KEMASKINI", data.get("idKemaskini"));
			rPara.add("TARIKH_KEMASKINI", data.get("tarikhKemaskini"));

			sqlPara = rPara.getSQLUpdate("TBLPDTAktaPARA");
			stmtPara.executeUpdate(sqlPara);

		} catch (Exception re) {
			 myLogger.error("Error: ", re);
			 throw re;
			 }
		 finally {
			if (dbPara != null)
				dbPara.close();
		}
		
		Db dbJadual = new Db();
		String sqlJadual = "";
		try {
			Statement stmtJadual = dbJadual.getStatement();
			SQLRenderer rJadual = new SQLRenderer();
			rJadual.update("ID_JADUAL", data.get("idJadual"));
			rJadual.add("NO_JADUAL", data.get("jadual"));
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
	// UPDATE SUBPARA
	public void updateSubPara(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_AktaSUBPARA", data.get("idSubPara"));
			r.add("ID_Akta", data.get("idAkta"));
			r.add("ID_AktaPENGGAL", data.get("idAktaPenggal"));
			r.add("ID_AktaBAHAGIAN", data.get("idAktaBahagian"));
			r.add("ID_AktaBAB", data.get("idAktaBab"));
			r.add("ID_AktaSEKSYEN", data.get("idAktaSeksyen"));
			r.add("ID_AktaPARA", data.get("idAktaPara"));
			r.add("ID_JADUAL", data.get("idJadual"));
			r.add("SUB_PARA", data.get("subPara"));
			r.add("ID_KEMASKINI", data.get("idKemaskini"));
			r.add("TARIKH_KEMASKINI", data.get("tarikhKemaskini"));

			sql = r.getSQLUpdate("TBLPDTAktaSUBPARA");
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
	// UPDATE JADUALPARA
	public void updateJadualPara(Hashtable data) throws Exception {
		Db dbJadualPara = new Db();
		String sqlJadualPara = "";
		Db dbJadual = new Db();
		String sqlJadual = "";
		try {
			Statement stmtJadualPara = dbJadualPara.getStatement();
			SQLRenderer rJadualPara = new SQLRenderer();

			rJadualPara.update("ID_JADUALPARA",  data.get("idJadualPara"));
			rJadualPara.add("ID_Akta", data.get("idAkta"));
			rJadualPara.add("ID_JADUAL",data.get("idJadual"));
			rJadualPara.add("PARA", data.get("para"));
			rJadualPara.add("ID_KEMASKINI", data.get("idKemaskini"));
			rJadualPara.add("TARIKH_KEMASKINI", data.get("tarikhKemaskini"));
			
			sqlJadualPara = rJadualPara.getSQLUpdate("TBLPDTJADUALPARA");
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
	// UPDATE SUBJADUALPARA
	public void updateJadualSubPara(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_JADUALSUBPARA", data.get("idJadualSubPara"));
			r.add("ID_Akta", data.get("idAkta"));
			r.add("ID_JADUALPARA", data.get("idJadualPara"));
			r.add("ID_JADUAL", data.get("idJadual"));
			r.add("SUB_PARA", data.get("subPara"));
			r.add("ID_KEMASKINI", data.get("idKemaskini"));
			r.add("TARIKH_KEMASKINI", data.get("tarikhKemaskini"));

			sql = r.getSQLUpdate("TBLPDTJADUALSUBPARA");
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
	// UPDATE JADUALSUBSUBPARA
	public void updateJadualSubSubPara(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_JADUALSUBSUBPARA", data.get("idJadualSubSubPara"));
			r.add("ID_Akta", data.get("idAkta"));
			r.add("ID_JADUAL", data.get("idJadual"));
			r.add("ID_JADUALPARA", data.get("idJadualPara"));
			r.add("ID_JADUALSUBPARA", data.get("idJadualSubPara"));
			r.add("SUB_SUBPARA", data.get("subSubPara"));
			r.add("ID_KEMASKINI", data.get("idKemaskini"));
			r.add("TARIKH_KEMASKINI", data.get("tarikhKemaskini"));

			sql = r.getSQLUpdate("TBLPDTJADUALSUBSUBPARA");
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
}
