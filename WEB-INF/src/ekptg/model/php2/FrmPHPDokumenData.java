package ekptg.model.php2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;

public class FrmPHPDokumenData {
	
	public Vector getBeanMaklumatImejan() {
		return beanMaklumatImejan;
	}

	public void setBeanMaklumatImejan(Vector beanMaklumatImejan) {
		this.beanMaklumatImejan = beanMaklumatImejan;
	}

	private Vector beanMaklumatImejan = null;
	private static final Log log = LogFactory.getLog(FrmPHPDokumenData.class);
	
	public void hapusDokumen(String idDokumen, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// TBLPHPDOKUMEN
			SQLRenderer r = new SQLRenderer();
			r.add("ID_DOKUMEN", idDokumen);

			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610210", "4", null, session, "DEL",
					"FAIL PENAWARAN [" + idDokumen
							+ "] DIHAPUSKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateDokumen(String idDokumen, FileItem item, String namaImej, String catatanImej, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");

		try {			
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn
					.prepareStatement("UPDATE TBLPHPDOKUMEN SET NAMA_DOKUMEN=?, CATATAN=?, CONTENT=?, JENIS_MIME=?, NAMA_FAIL=?, ID_KEMASKINI=?, TARIKH_KEMASKINI=SYSDATE WHERE ID_DOKUMEN=?");

			ps.setString(1, namaImej);
			ps.setString(2, catatanImej);
			ps.setBinaryStream(3, item.getInputStream(), (int) item.getSize());
			ps.setString(4, item.getContentType());
			ps.setString(5, item.getName());
			ps.setString(6, userId);
			ps.setString(7, idDokumen);

			 ps.executeUpdate();
			 conn.commit();
			 
			 AuditTrail.logActivity("1610210", "4", null, session, "UPD",
						"FAIL PENAWARAN [" + idDokumen
								+ "] DIKEMASKINI");
			 
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}	

	public void setMaklumatImej(String column, String data) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatImejan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN WHERE "+column+" = '"+data+"'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaImej", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanImej",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				beanMaklumatImejan.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void saveData(FileItem item, String idPermohonan, String namaImej, String catatanImej, String flagDokumen, String column, String data, HttpSession session) throws Exception {

		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		try {
			db = new Db();

			// TBLPHPDOKUMEN
			long idDokumen = DB.getNextID("TBLPHPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO TBLPHPDOKUMEN "
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,FLAG_DOKUMEN,ID_PERMOHONAN,"+column+") "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, namaImej);
			ps.setString(3, catatanImej);
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, flagDokumen);
			ps.setString(9, idPermohonan);
			ps.setString(10, data);
			ps.executeUpdate();

			con.commit();
			
			AuditTrail.logActivity("1610210", "4", null, session, "INS",
					"FAIL PENAWARAN [" + idDokumen
							+ "] DIDAFTARKAN");
			
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}

	}

}
