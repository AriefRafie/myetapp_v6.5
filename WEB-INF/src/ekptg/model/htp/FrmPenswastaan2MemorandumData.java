package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmPenswastaan2MemorandumData {
	
	static Logger mylogger = Logger.getLogger(FrmPenswastaan2MemorandumData.class);
	
	static SimpleDateFormat Formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	private Vector beanMemorandum = null;
	
	
	
	public void simpanUpdateMemorandum(String idJemaahMenteri, Hashtable hash, HttpSession session) throws Exception {
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
			
			
			//TBLHTPJEMAAHMENTERI
			r.update("ID_JEMAAHMENTERI", idJemaahMenteri);					
			r.add("NAMA_PEMAJU", hash.get("nama"));
			r.add("TARIKH_HANTAR_KSU", r.unquote("to_date('" + hash.get("txdUlasanKeKSU") + "','dd/MM/yyyy')"));			
			//r.add("TARIKH_TERIMA_KSU", hash.get("txdTerimaDariKSU"));	
			r.add("TARIKH_TERIMA_KSU", r.unquote("to_date('" + hash.get("txdTerimaDariKSU") + "','dd/MM/yyyy')"));			
			r.add("NO_MEMORANDUM", hash.get("txtNoMemorandum"));	
			//r.add("TARIKH_MSYRT_JEMAAH", hash.get("txdMystJM"));	
			r.add("TARIKH_MSYRT_JEMAAH", r.unquote("to_date('" + hash.get("txdMystJM") + "','dd/MM/yyyy')"));	
			//r.add("TARIKH_KEPUTUSAN", hash.get("txdKeputusan"));	
			r.add("TARIKH_KEPUTUSAN", r.unquote("to_date('" + hash.get("txdKeputusan") + "','dd/MM/yyyy')"));	
			r.add("ULASAN", hash.get("txtUlasan"));	
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ID_DB", r.unquote("99"));

			sql = r.getSQLUpdate("TBLHTPJEMAAHMENTERI");
			stmt.executeUpdate(sql);
			
			mylogger.info("SQL UPDATE JEMAAH_MENTERI :"+sql);
			
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
	
	
	public String getIdJemaahMenteriByIdPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_JEMAAHMENTERI FROM TBLHTPJEMAAHMENTERI WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_JEMAAHMENTERI").toString();
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public void setbeanMemorandum(String idJemaahMenteri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMemorandum = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLHTPJEMAAHMENTERI WHERE ID_JEMAAHMENTERI = '" + idJemaahMenteri + "'";
			
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idJemaahMenteri", rs.getString("ID_JEMAAHMENTERI") == null ? "" : rs.getString("ID_JEMAAHMENTERI"));
				h.put("id_permohonan",rs.getString("id_permohonan") == null ? "" : rs.getString("id_permohonan").toUpperCase());
				h.put("txdTerimaDariKSU", rs.getDate("TARIKH_TERIMA_KSU") == null ? "": Formatter.format(rs.getDate("TARIKH_TERIMA_KSU")));
				h.put("txdUlasanKeKSU", rs.getDate("TARIKH_MASUK") == null ? "": Formatter.format(rs.getDate("TARIKH_MASUK")));
				h.put("txtNoMemorandum", rs.getString("NO_MEMORANDUM") == null ? "": rs.getString("NO_MEMORANDUM"));
				h.put("txdMystJM", rs.getDate("TARIKH_MSYRT_JEMAAH") == null ? "": Formatter.format(rs.getDate("TARIKH_MSYRT_JEMAAH")));
				h.put("txdKeputusan", rs.getDate("TARIKH_KEPUTUSAN") == null ? "": Formatter.format(rs.getDate("TARIKH_KEPUTUSAN")));
				h.put("txtUlasan", rs.getString("ULASAN") == null ? "": rs.getString("ULASAN"));
				beanMemorandum.addElement(h);
				bil++;
			}

		
			mylogger.info("SQL JEMAAH MENTERI :"+sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Vector getBeanMemorandum() {
		return beanMemorandum;
	}

	public void setBeanMemorandum(Vector beanMemorandum) {
		this.beanMemorandum = beanMemorandum;
	}

}
