package ekptg.model.pdt;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmTambahDokumenData {
	static Logger myLogger = Logger.getLogger(FrmTambahDokumenData.class);

	public void save(String txtNamaDokumen,HttpSession session) {
		Db db = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
	    	con.setAutoCommit(false);
	    	PreparedStatement ps;
	    	Long ID_TBLPDTSENARAIDOKUMEN = DB.getNextID("TBLPDTSENARAIDOKUMEN_SEQ");
	    	String userId = (String) session.getAttribute("_ekptg_user_id");
	    	java.util.Date today = new java.util.Date();
	    	ps = con.prepareStatement("insert into TBLPDTSENARAIDOKUMEN " +
					"(ID_TBLPDTSENARAIDOKUMEN ,PERIHAL ,ID_DOKUMENINDUK ,TARIKH_MASUK ,MASUK_OLEH) " +
					"values(?,?,?,?,?)");
    		ps.setLong(1, ID_TBLPDTSENARAIDOKUMEN);
	    	ps.setString(2, txtNamaDokumen);
	    	ps.setString(3, null);
	    	ps.setDate(4,  new java.sql.Date(today.getTime()));
	    	ps.setString(5, userId);
	    	
		
	    	ps.executeUpdate();
	    	con.commit();
			
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}finally{
			if (db != null){
		    	  db.close();
		      }
		}
		
	}

	public Vector findByNamaDok(String txtNamaDokumen) {
		Db db = null;
		String sql = "";
		Vector senaraiDokumenInduk =  new Vector<>();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			if(!txtNamaDokumen.equals("")){
				sql = "SELECT A.ID_TBLPDTSENARAIDOKUMEN, A.PERIHAL"
						+ " FROM TBLPDTSENARAIDOKUMEN A"
						+ " WHERE";
				sql = sql + " UPPER(A.PERIHAL) LIKE '%"+txtNamaDokumen.toUpperCase()+"%' "
						+ " AND A.ID_DOKUMENINDUK IS null";
			}else{
				sql = "SELECT A.ID_TBLPDTSENARAIDOKUMEN, A.PERIHAL"
						+ " FROM TBLPDTSENARAIDOKUMEN A";
			}
			myLogger.info("sql TBLPDTSENARAIDOKUMEN : "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id",
						rs.getString("ID_TBLPDTSENARAIDOKUMEN") == null ? "" : rs
								.getString("ID_TBLPDTSENARAIDOKUMEN"));
				h.put("perihal",
						rs.getString("PERIHAL") == null ? "" : rs
								.getString("PERIHAL"));
				h.put("snriChild",findByIdInduk(rs.getString("ID_TBLPDTSENARAIDOKUMEN")));
				senaraiDokumenInduk.addElement(h);
				bil++;
			}

			
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}finally{
			if (db != null){
		    	  db.close();
		      }
		}
		return senaraiDokumenInduk;
	}

	private Vector findByIdInduk(String idInduk) {
		
		Db db = null;
		String sql = "";
		Vector senaraiDokumenChild =  new Vector<>();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_TBLPDTSENARAIDOKUMEN, A.PERIHAL"
					+ " FROM TBLPDTSENARAIDOKUMEN A"
					+ " WHERE";
			if(!idInduk.equals("")){
				sql = sql + " A.ID_DOKUMENINDUK = '"+idInduk+"' "
						+ "ORDER BY A.PERIHAL";
			}
			myLogger.info("sql TBLPDTSENARAIDOKUMEN : "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id",
						rs.getString("ID_TBLPDTSENARAIDOKUMEN") == null ? "" : rs
								.getString("ID_TBLPDTSENARAIDOKUMEN"));
				h.put("perihal",
						rs.getString("PERIHAL") == null ? "" : rs
								.getString("PERIHAL"));
				h.put("snriChild1",findByIdInduk(rs.getString("ID_TBLPDTSENARAIDOKUMEN")));
				senaraiDokumenChild.addElement(h);
				bil++;
			}

			
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}finally{
			if (db != null){
		    	  db.close();
		      }
		}
		return senaraiDokumenChild;
	}

	public void saveChild(String txtNamaDokumen, HttpSession session, String id) {
		try {
			Db db = new Db();
			Connection con = db.getConnection();
	    	con.setAutoCommit(false);
	    	PreparedStatement ps;
	    	Long ID_TBLPDTSENARAIDOKUMEN = DB.getNextID("TBLPDTSENARAIDOKUMEN_SEQ");
	    	String userId = (String) session.getAttribute("_ekptg_user_id");
	    	java.util.Date today = new java.util.Date();
	    	ps = con.prepareStatement("insert into TBLPDTSENARAIDOKUMEN " +
					"(ID_TBLPDTSENARAIDOKUMEN ,PERIHAL ,ID_DOKUMENINDUK ,TARIKH_MASUK ,MASUK_OLEH) " +
					"values(?,?,?,?,?)");
    		ps.setLong(1, ID_TBLPDTSENARAIDOKUMEN);
	    	ps.setString(2, txtNamaDokumen);
	    	ps.setString(3, id);
	    	ps.setDate(4,  new java.sql.Date(today.getTime()));
	    	ps.setString(5, userId);
	    	
		
	    	ps.executeUpdate();
	    	con.commit();
			
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}
		
	}

	public void delete(String id) throws DbException {
		Db db = new Db();
		Db db1 = new Db();
		Db db2 = new Db();
		Db db3 = new Db();
		Db db4 = new Db();
		if(id != ""){
			try {
				Connection con = db.getConnection();
		    	con.setAutoCommit(false);
				String sql = "";
				Statement stmt = db.getStatement();
				sql = selectSnriDok(id);
				ResultSet rs = stmt.executeQuery(sql);
				
				if(!rs.equals(null)){
					while (rs.next()) {
						myLogger.info("rs : "+rs.getString("ID_TBLPDTSENARAIDOKUMEN"));
						Connection con1 = db1.getConnection();
				    	con1.setAutoCommit(false);
						String sql1 = "";
						Statement stmt1 = db1.getStatement();
						sql1 = selectSnriDok(rs.getString("ID_TBLPDTSENARAIDOKUMEN"));
						ResultSet rs1 = stmt1.executeQuery(sql1);
						
						if(!rs1.equals(null)){
							while (rs1.next()) {
								myLogger.info("rs1 : "+rs1.getString("ID_TBLPDTSENARAIDOKUMEN"));
								Connection con2 = db2.getConnection();
						    	con2.setAutoCommit(false);
								String sql2 = "";
								Statement stmt2 = db2.getStatement();
								sql2 = selectSnriDok(rs1.getString("ID_TBLPDTSENARAIDOKUMEN"));
								ResultSet rs2 = stmt2.executeQuery(sql2);
								
								if(!rs2.equals(null)){
									while (rs2.next()) {
										myLogger.info("rs2 : "+rs2.getString("ID_TBLPDTSENARAIDOKUMEN"));
										Connection con3 = db3.getConnection();
										con3.setAutoCommit(false);
										String sql3 = "";
										Statement stmt3 = db3.getStatement();
										sql3 = selectSnriDok(rs2.getString("ID_TBLPDTSENARAIDOKUMEN"));
										ResultSet rs3 = stmt3.executeQuery(sql3);
										
										if(!rs3.equals(null)){
											while (rs3.next()) {
												myLogger.info("rs3 : "+rs3.getString("ID_TBLPDTSENARAIDOKUMEN"));
												deleteRujLamp(rs3.getString("ID_TBLPDTSENARAIDOKUMEN"), con3);
												deleteSnraiDok(rs3.getString("ID_TBLPDTSENARAIDOKUMEN"), con3);
											}
										}
										deleteRujLamp(rs2.getString("ID_TBLPDTSENARAIDOKUMEN"), con2);
										deleteSnraiDok(rs2.getString("ID_TBLPDTSENARAIDOKUMEN"), con2);
									}
								}
								deleteRujLamp(rs1.getString("ID_TBLPDTSENARAIDOKUMEN"), con1);
								deleteSnraiDok(rs1.getString("ID_TBLPDTSENARAIDOKUMEN"), con1);
							}
						}
						deleteRujLamp(rs.getString("ID_TBLPDTSENARAIDOKUMEN"), con);
						deleteSnraiDok(rs.getString("ID_TBLPDTSENARAIDOKUMEN"), con);
					}
				}
				
				Connection con4 = db4.getConnection();
		    	con4.setAutoCommit(false);
		    	deleteRujLamp(id, con4);
				deleteSnraiDokBy(id, con4);
				
			} catch (Exception e) {
				myLogger.info(e.getMessage());
			}finally{
				if (db != null){
			    	  db.close();
			    	  db1.close();
			    	  db2.close();
			    	  db3.close();
			    	  db4.close();
			      }
			}
		}
	}

	private String selectSnriDokBy(String id) {
		return "SELECT A.ID_TBLPDTSENARAIDOKUMEN, A.ID_DOKUMENINDUK "
				+ " FROM TBLPDTSENARAIDOKUMEN A"
				+ " WHERE A.ID_TBLPDTSENARAIDOKUMEN = "+id;
	}

	private void deleteSnraiDokBy(String id, Connection con) throws SQLException {
		try {
			PreparedStatement ps;
			ps = con.prepareStatement("DELETE FROM TBLPDTSENARAIDOKUMEN WHERE ID_TBLPDTSENARAIDOKUMEN = ?");
			ps.setLong(1, Long.parseLong(id));
			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}
	}


	private void deleteRujLamp(String id, Connection con) throws SQLException {
		try {
			PreparedStatement ps;
			ps = con.prepareStatement("DELETE FROM TBLPDTRUJLAMPIRAN WHERE ID_TBLPDTSENARAIDOKUMEN = ?");
			ps.setLong(1, Long.parseLong(id));
			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}
	}

	private String selectSnriDok(String id) {
		return "SELECT A.ID_TBLPDTSENARAIDOKUMEN, A.ID_DOKUMENINDUK "
				+ " FROM TBLPDTSENARAIDOKUMEN A"
				+ " WHERE A.ID_DOKUMENINDUK = "+id;
	}

	private void deleteSnraiDok(String id, Connection con) throws SQLException {
		try {
			PreparedStatement ps;
			ps = con.prepareStatement("DELETE TBLPDTSENARAIDOKUMEN WHERE ID_TBLPDTSENARAIDOKUMEN = ?");
			ps.setLong(1, Long.parseLong(id));
			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}
	}

	public Hashtable findById(String id) {
		int bil = 1;
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		Hashtable h = new Hashtable();
		try {
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			r.add("ID_TBLPDTSENARAIDOKUMEN");
			r.add("PERIHAL");
			r.add("ID_DOKUMENINDUK");
			r.set("ID_TBLPDTSENARAIDOKUMEN", Long.parseLong(id));
			String sql = r.getSQLSelect("TBLPDTSENARAIDOKUMEN");
			myLogger.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				h.put("bil", bil);
				h.put("perihal",
						rs.getString("PERIHAL") == null ? "" : rs.getString("PERIHAL"));
				h.put("idDokInduk",rs.getString("ID_DOKUMENINDUK") == null ? "" : rs.getString("ID_DOKUMENINDUK"));
				h.put("id",id);
				bil++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			if (db != null){
		    	  db.close();
		      }
		}
		return h;
	}

	public Vector findByIdPdtSnriDkmn(String id) {

		Db db = null;
		String sql = "";
		Vector senaraiRujLampiran =  new Vector<>();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_LAMPIRAN, A.ID_TBLPDTSENARAIDOKUMEN, A.NAMA_FAIL"
					+ " FROM TBLPDTRUJLAMPIRAN A"
					+ " WHERE";
			if(!id.equals("")){
				sql = sql + " A.ID_TBLPDTSENARAIDOKUMEN ="+id;
			}
			myLogger.info("sql TBLPDTSENARAIDOKUMEN : "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id",
						rs.getString("ID_LAMPIRAN") == null ? "" : rs
								.getString("ID_LAMPIRAN"));
				h.put("perihal",
						rs.getString("NAMA_FAIL") == null ? "" : rs
								.getString("NAMA_FAIL"));
				h.put("idTblPdtSnriDok",rs.getString("ID_TBLPDTSENARAIDOKUMEN") == null ? "" : rs
						.getString("ID_TBLPDTSENARAIDOKUMEN"));
				senaraiRujLampiran.addElement(h);
				bil++;
			}

			
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}finally{
			if (db != null){
		    	  db.close();
		      }
		}
		return senaraiRujLampiran;
	
	}

	public void saveRujLamp(FileItem item, String id, String txtNamaDokumen,HttpSession session)  throws Exception  {
		String userId = (String) session.getAttribute("_ekptg_user_id");
		java.util.Date today = new java.util.Date();
		long ID_LAMPIRAN = DB.getNextID("TBLPDTRUJLAMPIRAN_SEQ");
		PreparedStatement ps1;
		Hashtable h = new Hashtable();
		h.put("itemInputStream",item.getInputStream());
		h.put("size",item.getSize());
		Db db = new Db();
		Connection con = db.getConnection();
    	con.setAutoCommit(false);
		ps1 = con.prepareStatement("insert into TBLPDTRUJLAMPIRAN " +
				"(ID_LAMPIRAN,CONTENT,NAMA_FAIL,ID_MASUK,TARIKH_MASUK,ID_TBLPDTSENARAIDOKUMEN) " +
				"values(?,?,?,?,?,?)");
		ps1.setLong(1, ID_LAMPIRAN);
		String size = h.get("size").toString();
    	ps1.setBinaryStream(2, (InputStream) h.get("itemInputStream"),Integer.valueOf(size));
    	ps1.setString(3,txtNamaDokumen);
    	ps1.setString(4, userId);
    	ps1.setDate(5, new java.sql.Date(today.getTime()));
    	ps1.setLong(6,Long.parseLong(id));
    	ps1.executeUpdate();
        con.commit();
        db.close();
	}

	public void deleteTblRujLamp(String id) throws DbException {
		Db db = new Db();
		if(id != ""){
			try {
				Connection con = db.getConnection();
		    	con.setAutoCommit(false);
		    	PreparedStatement ps;
				ps = con.prepareStatement("DELETE TBLPDTRUJLAMPIRAN WHERE ID_LAMPIRAN = ?");
				ps.setLong(1, Long.parseLong(id));
				ps.executeUpdate();
		        con.commit();
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if (db != null){
			    	  db.close();
			      }
			}
		}
		
	}

	public void editChild(String txtNamaDokumen, HttpSession session, String id) {
		String userId = (String) session.getAttribute("_ekptg_user_id");
		try {
			Db db = new Db();
			java.util.Date today = new java.util.Date();
			Connection con = db.getConnection();
	    	con.setAutoCommit(false);
	    	PreparedStatement ps;
	    	ps = con.prepareStatement("UPDATE TBLPDTSENARAIDOKUMEN SET PERIHAL = ?, TARIKH_KEMASKINI = ?, KEMASKINI_OLEH = ?  WHERE ID_TBLPDTSENARAIDOKUMEN = "+id+"");
	    	ps.setString(1, txtNamaDokumen);
	    	ps.setDate(2, new java.sql.Date(today.getTime()));
	    	ps.setString(3, userId);
	    	ps.executeUpdate();
	        con.commit();
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}
		
	}

}
