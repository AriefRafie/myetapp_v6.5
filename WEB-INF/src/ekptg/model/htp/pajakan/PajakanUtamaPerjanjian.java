package ekptg.model.htp.pajakan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.model.htp.FrmPajakanMemorandumJemaahMenteriData;

public class PajakanUtamaPerjanjian implements IPajakanFungsi {
	
	private Db db = null;
	private Connection conn = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.pajakan.PajakanUtamaPerjanjian.class);
	private String sql = "";
	private Vector<Hashtable <String,String>> senarai = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Vector<Hashtable <String,String>> getSenarai(String idPermohonan,String jenis) throws Exception {
		try {
			senarai = new Vector<Hashtable <String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_DERAFPERJANJIAN, A.TARIKH_HANTAR_DERAF, A.TARIKH_TERIMA, A.TARIKH_HANTARPTP, A.TARIKH_TERIMAPTP, A.ULASAN_SEKSYEN "+
				" FROM TBLHTPDERAFPERJANJIAN A WHERE " +
				" A.JENIS_DOKUMEN = '"+jenis+"' " +
				" AND A.ID_PERMOHONAN = '" + idPermohonan + "'";		
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable <String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable <String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idDraf", rs.getString("ID_DERAFPERJANJIAN") == null ? "" : rs.getString("ID_DERAFPERJANJIAN"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR_DERAF") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_DERAF")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhHantarPKP", rs.getDate("TARIKH_HANTARPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTARPTP")));
				h.put("tarikhTerimaPKP", rs.getDate("TARIKH_TERIMAPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMAPTP")));
				h.put("ulasan", rs.getString("ULASAN_SEKSYEN") == null ? "" : rs.getString("ULASAN_SEKSYEN"));
				senarai.addElement(h);
				bil++;
				
			}

		} finally {
			if (db != null)
				db.close();
		}
		return senarai;
	}
	@Override
	public Vector<Hashtable<String,String>> getMaklumat(String idDraf) throws Exception {
		FrmPajakanMemorandumJemaahMenteriData logicmjm = new FrmPajakanMemorandumJemaahMenteriData();
		return logicmjm.getMaklumatDraf(idDraf);
	}
	@Override
	public void kemaskini(String idDraf, Hashtable<String,String> hash) throws Exception {
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPDERAFPERJANJIAN
			r.update("ID_DERAFPERJANJIAN", idDraf);
					
			if (!"".equals(hash.get("tarikhHantar"))){
				r.add("TARIKH_HANTAR_DERAF", r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhHantarPKP"))){
				r.add("TARIKH_HANTARPTP", r.unquote("to_date('" + hash.get("tarikhHantarPKP") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerimaPKP"))){
				r.add("TARIKH_TERIMAPTP", r.unquote("to_date('" + hash.get("tarikhTerimaPKP") + "','dd/MM/yyyy')"));
			}
			r.add("ULASAN_SEKSYEN", hash.get("ulasan"));	
			
			r.add("ID_KEMASKINI", hash.get("userId"));
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLHTPDERAFPERJANJIAN");
			myLog.info("kemaskini:sql="+sql);
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

	@Override
	public String simpan(String idPermohonan, Hashtable<String,String> hash) throws Exception {
		String idDerafPerjanjian = "-1";
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();				
			//TBLHTPDERAFPERJANJIAN
			idDerafPerjanjian = String.valueOf(DB.getNextID("TBLHTPDERAFPERJANJIAN_SEQ"));
			r.add("ID_DERAFPERJANJIAN", idDerafPerjanjian);
			r.add("ID_PERMOHONAN", idPermohonan);		
			if (!"".equals(hash.get("tarikhHantar"))){
				r.add("TARIKH_HANTAR_DERAF", r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhHantarPKP"))){
				r.add("TARIKH_HANTARPTP", r.unquote("to_date('" + hash.get("tarikhHantarPKP") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerimaPKP"))){
				r.add("TARIKH_TERIMAPTP", r.unquote("to_date('" + hash.get("tarikhTerimaPKP") + "','dd/MM/yyyy')"));
			}
			r.add("ULASAN_SEKSYEN", hash.get("ulasan"));
			//r.add("JENIS_DOKUMEN","P");	M-memo,P-Perjanjian
			r.add("JENIS_DOKUMEN",hash.get("jenis"));	
			
			r.add("ID_MASUK", hash.get("userId"));
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPDERAFPERJANJIAN");
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
		return idDerafPerjanjian;
				
	}
	

	
}
