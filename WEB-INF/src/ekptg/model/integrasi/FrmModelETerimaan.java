package ekptg.model.integrasi;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.fileupload.FileItem;

import ekptg.helpers.DB;

public class FrmModelETerimaan {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public String getNamaUserByID(String ID_USER) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_USER)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				String sql = "";
				sql = "SELECT USER_NAME FROM USERS WHERE USER_ID = " + ID_USER;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	public String getMIMEType(String FileName) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			
			r.add("CONTENT_MIME");
			r.add("CONTENT_NAME", FileName);
			sql = r.getSQLSelect("TBLINTETERIMAAN");
			r.clear();
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				returnValue = rs.getString(1) == null ? "" : rs.getString(1);
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	public String getUploadID(String FileName) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_ETERIMAAN");
			r.add("CONTENT_NAME", FileName);
			sql = r.getSQLSelect("TBLINTETERIMAAN");
			r.clear();
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				returnValue = rs.getString(1) == null ? "" : rs.getString(1);
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getListUploadedFile() throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Hashtable h = null;
			SQLRenderer r = new SQLRenderer();
			int i = 1;
			String IDFail = "", NamaFail = "", NamaPegawai = "", TarikhMuatNaik = "", Status = "", MIME_Type = "";
			
			r.add("ID_ETERIMAAN");
			r.add("CONTENT_NAME");
			r.add("USER_ID");
			r.add("TARIKH_KEMASKINI");
			r.add("STATUS_PROSES");
			r.add("CONTENT_MIME");
			r.add("STATUS_PROSES", "SELESAI", "!=");
			sql = r.getSQLSelect("TBLINTETERIMAAN", "TARIKH_KEMASKINI DESC");
			r.clear();
			
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				IDFail = rs.getString(1) == null ? "" : rs.getString(1);
				NamaFail = rs.getString(2) == null ? "" : rs.getString(2);
				NamaPegawai = rs.getString(3) == null ? "" : rs.getString(3);
				TarikhMuatNaik = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				Status = rs.getString(5) == null ? "" : rs.getString(5);
				MIME_Type = rs.getString(6) == null ? "" : rs.getString(6);
				
				NamaPegawai = getNamaUserByID(NamaPegawai);
					
				h = new Hashtable();
				h.put("No", i);
				h.put("IDFail", IDFail);
				h.put("NamaFail", NamaFail);
				h.put("NamaPegawai", NamaPegawai);
				h.put("TarikhMuatNaik", TarikhMuatNaik);
				h.put("Status", Status);
				h.put("MIME_Type", MIME_Type);
				
				v.add(h);
				i++;
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}
	
	public Boolean doUpload(FileItem objItem, String userID) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			java.util.Date today = new java.util.Date();
			java.sql.Date date = new java.sql.Date(today.getTime());
			long ID_DB = DB.getNextID("TBLINTETERIMAAN_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	PreparedStatement ps = con.prepareStatement("INSERT INTO TBLINTETERIMAAN " +
        			"(ID_ETERIMAAN, CONTENT, CONTENT_NAME, CONTENT_MIME, USER_ID, STATUS_PROSES, TARIKH_KEMASKINI) " +
        			"VALUES (?, ?, ?, ?, ?, ?, ?)");
        	ps.setLong(1, ID_DB);
        	//ps.setString(2, "2010-07-30");
        	ps.setBinaryStream(2, objItem.getInputStream(), (int)objItem.getSize());
        	ps.setString(3, objItem.getName());
        	ps.setString(4, objItem.getContentType());
        	ps.setString(5, userID);
        	ps.setString(6, "BARU");
			ps.setDate(7, date);
        	ps.executeUpdate();
            con.commit();
            
            returnValue = true;
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean processFile(String IDFail) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(IDFail)) {
		        Connection con = db.getConnection();
		        PreparedStatement ps = con.prepareStatement("SELECT CONTENT, CONTENT_MIME FROM TBLINTETERIMAAN WHERE ID_ETERIMAAN = ?");
		        ps.setString(1, IDFail);
		        ResultSet rs = ps.executeQuery();
		        if (rs.next()) {
		        	Blob b = rs.getBlob("CONTENT");
			        String s = new String(b.getBytes(1l, (int) b.length()));
			        //System.out.println(s);
			        ps.execute("UPDATE TBLINTETERIMAAN SET STATUS_PROSES = 'SELESAI' WHERE ID_ETERIMAAN = " + IDFail);
			        returnValue = true;
		        }
			}
		} finally {
			if (db != null) db.close();
		}
        return returnValue;
	}
	
	public Boolean deleteFile(String IDFail) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(IDFail)) {
				Statement stmt = db.getStatement();
				String sql = "";
				sql = "DELETE FROM TBLINTETERIMAAN WHERE ID_ETERIMAAN = " + IDFail;
				stmt.execute(sql);
				returnValue = true;
			}
		} finally {
			if (db != null) db.close();
		}
        return returnValue;
	}
}