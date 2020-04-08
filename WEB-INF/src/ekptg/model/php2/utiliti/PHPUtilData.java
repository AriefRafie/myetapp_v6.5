package ekptg.model.php2.utiliti;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import org.apache.log4j.Logger;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import ekptg.model.entities.Tblpfdfail;
import ekptg.model.entities.Tblphprujjenistujuan;


public class PHPUtilData {
	
	static Logger myLogger = Logger.getLogger(ekptg.model.php2.utiliti.PHPUtilData.class);
	
	public static Vector<Tblphprujjenistujuan> getTujuanBySubUrusan (String idSuburusan) throws Exception {
		Vector<Tblphprujjenistujuan> v = null;
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_jenistujuan");
			r.add("kod_jenistujuan");
			r.add("keterangan");
			r.add("id_suburusan", Integer.parseInt(idSuburusan));
			v = new Vector<Tblphprujjenistujuan>();
			sql = r.getSQLSelect("Tblphprujjenistujuan");
			myLogger.info("test :" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			Tblphprujjenistujuan t = null;
			while (rs.next()) {
				t = new Tblphprujjenistujuan();
				t.setIdJenistujuan(rs.getLong("id_jenistujuan"));
				t.setKodJenistujuan(rs.getString("kod_jenistujuan"));
				t.setKeterangan(rs.getString("keterangan"));
				v.addElement(t);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public static Vector<Tblpfdfail> getNoFailByIdPemohon (String userId) throws Exception {
		Vector<Tblpfdfail> v = null;
		Db db = null;
		String sql = " SELECT A.ID_FAIL, A.NO_FAIL, A.ID_MASUK "
				   	+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B "
				   	+ " WHERE A.ID_FAIL = B.ID_FAIL AND A.NO_FAIL IS NOT NULL "
				   	+ " AND A.ID_SEKSYEN = 4 AND A.ID_URUSAN = 7 "
				   	+ " AND A.FLAG_FAIL = 1 AND A.ID_MASUK = '" + userId + "'";
		
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_fail");
			r.add("no_fail");
			r.add("id_masuk", Integer.parseInt(userId));
			v = new Vector<Tblpfdfail>();
			myLogger.info("Tblpfdfail :" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			Tblpfdfail t = null;
			while (rs.next()) {
				t = new Tblpfdfail();
				t.setIdFail(rs.getLong("id_fail"));
				t.setNoFail(rs.getString("no_fail"));
				v.addElement(t);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	 
}
