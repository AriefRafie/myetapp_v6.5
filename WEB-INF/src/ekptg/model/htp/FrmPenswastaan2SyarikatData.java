/**
 * 
 */
package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.model.htp.penswastaan.IPenswastaan;
import ekptg.model.htp.penswastaan.PenswastaanBean;

/**
 * 
 *
 */
public class FrmPenswastaan2SyarikatData {
	
	private Vector beanMaklumatSyarikat = null;
	private Vector senaraiPengarah = null;
	private Vector beanMaklumatPengarah = null;
	private IPenswastaan iP= null;

	public String getIdPemajuByIdPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_PEMAJU FROM TBLHTPPEMAJU WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_PEMAJU").toString();
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatSyarikat(String idPemaju) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatSyarikat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLHTPPEMAJU WHERE ID_PEMAJU = '" + idPemaju + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPemaju", rs.getString("ID_PEMAJU") == null ? "" : rs.getString("ID_PEMAJU"));
				h.put("noRujukan", rs.getString("NO_RUJUKAN_PEMAJU") == null ? "" : rs.getString("NO_RUJUKAN_PEMAJU"));
				h.put("nama", rs.getString("NAMA_PEMAJU") == null ? "" : rs.getString("NAMA_PEMAJU"));
				h.put("alamat1", rs.getString("ALAMAT_PEMAJU1") == null ? "" : rs.getString("ALAMAT_PEMAJU1"));
				h.put("alamat2", rs.getString("ALAMAT_PEMAJU2") == null ? "" : rs.getString("ALAMAT_PEMAJU2"));
				h.put("alamat3", rs.getString("ALAMAT_PEMAJU3") == null ? "" : rs.getString("ALAMAT_PEMAJU3"));
				h.put("poskod", rs.getString("POSKOD_PEMAJU") == null ? "" : rs.getString("POSKOD_PEMAJU"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "99999" : rs.getString("ID_NEGERI"));
				h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "99999" : rs.getString("ID_DAERAH"));
				h.put("noTel", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
				beanMaklumatSyarikat.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void simpanUpdateSyarikat(String idPemaju, Hashtable hash, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPPEMAJU
			if(getISwasta().isMaklumatPemaju(String.valueOf(hash.get("idPermohonan")))){
				r.update("ID_PEMAJU", idPemaju);					
				r.add("NAMA_PEMAJU", hash.get("nama"));
				r.add("NO_RUJUKAN_PEMAJU", hash.get("noRujukan"));	
				r.add("ALAMAT_PEMAJU1", hash.get("alamat1"));	
				r.add("ALAMAT_PEMAJU2", hash.get("alamat2"));	
				r.add("ALAMAT_PEMAJU3", hash.get("alamat3"));	
				r.add("POSKOD_PEMAJU", hash.get("poskod"));	
				r.add("ID_NEGERI", hash.get("idNegeri"));	
				r.add("ID_DAERAH", hash.get("idDaerah"));	
				r.add("NO_TEL", hash.get("noTel"));	
				r.add("NO_FAX", hash.get("noFax"));	
				
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLHTPPEMAJU");

			}else{
				long idPemaju_ = DB.getNextID(db, "TBLHTPPEMAJU_SEQ");
				r.add("ID_PEMAJU", idPemaju_);
				r.add("ID_PERMOHONAN", String.valueOf(hash.get("idPermohonan")));
				r.add("NAMA_PEMAJU", hash.get("nama"));
				r.add("NO_RUJUKAN_PEMAJU", hash.get("noRujukan"));	
				r.add("ALAMAT_PEMAJU1", hash.get("alamat1"));	
				r.add("ALAMAT_PEMAJU2", hash.get("alamat2"));	
				r.add("ALAMAT_PEMAJU3", hash.get("alamat3"));	
				r.add("POSKOD_PEMAJU", hash.get("poskod"));	
				r.add("ID_NEGERI", hash.get("idNegeri"));	
				r.add("ID_DAERAH", hash.get("idDaerah"));	
				r.add("NO_TEL", hash.get("noTel"));	
				r.add("NO_FAX", hash.get("noFax"));	
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLHTPPEMAJU");
			}

			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setListPengarah(String idPemaju) throws Exception {
		Db db = null;
		String sql = "";

		try {
			senaraiPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLHTPPENGARAH WHERE ID_PEMAJU = '" + idPemaju + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPengarah", rs.getString("ID_PENGARAH") == null ? "" : rs.getString("ID_PENGARAH"));
				h.put("namaPengarah", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("noPengenalan", rs.getString("NO_OPB") == null ? "" : rs.getString("NO_OPB"));
				h.put("idWarganegara", rs.getString("ID_WARGANEGARA") == null ? "" : rs.getString("ID_WARGANEGARA"));
				senaraiPengarah.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPengarah(String idPengarah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLHTPPENGARAH WHERE ID_PENGARAH = '" + idPengarah + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPengarah", rs.getString("ID_PENGARAH") == null ? "" : rs.getString("ID_PENGARAH"));
				h.put("namaPengarah", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("noPengenalan", rs.getString("NO_OPB") == null ? "" : rs.getString("NO_OPB"));
				h.put("idWarganegara", rs.getString("ID_WARGANEGARA") == null ? "" : rs.getString("ID_WARGANEGARA"));
				beanMaklumatPengarah.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void savePengarah(String idPemaju, String namaPengarah, String noPengenalan, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPPENGARAH
			long idPengarah = DB.getNextID("TBLHTPPENGARAH_SEQ");
			r.add("ID_PENGARAH", idPengarah);
			r.add("ID_PEMAJU", idPemaju);	
			r.add("NAMA", namaPengarah);
			r.add("NO_OPB", noPengenalan);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPPENGARAH");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}
	
	public void saveUpdatePengarah(String idPengarah, String namaPengarah, String noPengenalan, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPPENGARAH
			r.update("ID_PENGARAH", idPengarah);
			r.add("NAMA", namaPengarah);
			r.add("NO_OPB", noPengenalan);

			sql = r.getSQLUpdate("TBLHTPPENGARAH");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}
	
	public void hapusPengarah(String idPengarah) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	

			//TBLHTPPENGARAH
			r.add("ID_PENGARAH", idPengarah);

			sql = r.getSQLDelete("TBLHTPPENGARAH");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}	
	}

	public Vector getBeanMaklumatSyarikat() {
		return beanMaklumatSyarikat;
	}

	public void setBeanMaklumatSyarikat(Vector beanMaklumatSyarikat) {
		this.beanMaklumatSyarikat = beanMaklumatSyarikat;
	}

	public Vector getSenaraiPengarah() {
		return senaraiPengarah;
	}

	public void setSenaraiPengarah(Vector senaraiPengarah) {
		this.senaraiPengarah = senaraiPengarah;
	}

	public Vector getBeanMaklumatPengarah() {
		return beanMaklumatPengarah;
	}

	public void setBeanMaklumatPengarah(Vector beanMaklumatPengarah) {
		this.beanMaklumatPengarah = beanMaklumatPengarah;
	}
	
	private IPenswastaan getISwasta(){
		if (iP==null){
			iP = new PenswastaanBean();
		}
		return iP;
	} 

}
