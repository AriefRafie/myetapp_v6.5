package ekptg.model.php2.utiliti;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import org.apache.log4j.Logger;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import ekptg.model.entities.Tblpfdfail;
import ekptg.model.entities.Tblphprujjenistujuan;
import ekptg.model.entities.Tblphprujtujuankaitan;
import ekptg.model.entities.Tblrujsubsuburusan;


public class PHPUtilData {
	
	static Logger myLogger = Logger.getLogger(ekptg.model.php2.utiliti.PHPUtilData.class);
	
	public static Vector<Tblrujsubsuburusan> getSubsuburusanBySubUrusan (String idSuburusan) throws Exception {
		Vector<Tblrujsubsuburusan> v = null;
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_subsuburusan");
			r.add("kod_subsuburusan");
			r.add("nama_subsuburusan");
			r.add("id_suburusan", Integer.parseInt(idSuburusan));
			v = new Vector<Tblrujsubsuburusan>();
			sql = r.getSQLSelect("Tblrujsubsuburusan");
			myLogger.info("Tblrujsubsuburusan :" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			Tblrujsubsuburusan t = null;
			while (rs.next()) {
				t = new Tblrujsubsuburusan();
				t.setIdSubsuburusan(rs.getLong("id_subsuburusan"));
				t.setKodSubsuburusan(rs.getString("kod_subsuburusan"));
				t.setNamaSubsuburusan(rs.getString("nama_subsuburusan"));
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
		
		myLogger.info("Tblpfdfail :" +sql);
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_fail");
			r.add("no_fail");
			r.add("id_masuk", userId);
			v = new Vector<Tblpfdfail>();
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
	
	public static Vector<Tblphprujjenistujuan> getJenistujuanAPB() throws Exception { //yati tambah 24/7/2020
		Db db = null;
		String sql = "Select id_JenisTujuan, upper(kod_JenisTujuan) as kod_JenisTujuan, upper(keterangan) as keterangan from tblphprujjenistujuan order by id_JenisTujuan asc";
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
	 
}
