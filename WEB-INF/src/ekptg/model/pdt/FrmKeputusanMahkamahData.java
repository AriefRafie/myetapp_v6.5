package ekptg.model.pdt;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmKeputusanMahkamahData {
	 private static Logger myLogger = Logger.getLogger(FrmKeputusanMahkamahData.class);
	 
	 public List listTableRujukanSkop(HttpSession session, String tableRujukan, String id_filter)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listTableRujukanSkop = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				if(tableRujukan.equals("TBLRUJSKOP"))
				{
					sql = " SELECT ID_SKOP AS ID,  UPPER(NAMA_SKOP) AS KETERANGAN FROM TBLRUJSKOP ORDER BY ID_SKOP";
				}
				
				
				
				myLogger.info(" V3: SQL listTableRujukanSkop ("+tableRujukan+") :"+ sql);
				rs = stmt.executeQuery(sql);
				listTableRujukanSkop = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					bil++;
					h.put("BIL",bil);
					h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
					//h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
					h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
					
					listTableRujukanSkop.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return listTableRujukanSkop;

		}
	 
	public Vector findTblPdtDokumenBy(String txtSkop, String txtNamaKes,
			String txtCitation) throws DbException, SQLException {
		 Db db = null;
		 Vector list = new Vector();
		 String sql = "";	 
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.SKOP,A.NAMA_KES,A.RUJ_MAHKAMAH,A.ID_DOKUMEN,A.TAHUN, B.ID_LAMPIRAN, B.CONTENT, C.ID_SKOP, C.NAMA_SKOP" +
			      		" FROM TBLPDTDOKUMEN A, TBLPDTRUJLAMPIRAN B, TBLRUJSKOP C WHERE "
			      		+ " A.ID_DOKUMEN = B.ID_DOKUMEN (+) "
			      		+ " AND A.FLAG_DOKUMEN = 'M' "
			      		+ " AND A.SKOP = C.ID_SKOP ";
		      
		      if (txtSkop != null && !txtSkop.trim().equals("")) {
						sql = sql + " AND UPPER(A.SKOP) =  " + txtSkop;
				}
		      
		      if (txtNamaKes != null && !txtNamaKes.trim().equals("")) {
						sql = sql + " AND UPPER(A.NAMA_KES) LIKE '%' ||'" + txtNamaKes.toUpperCase() + "'|| '%'";
				}
		      
		      if (txtCitation != null && !txtCitation.trim().equals("")) {
						sql = sql + " AND UPPER(A.RUJ_MAHKAMAH) LIKE '%' ||'" + txtCitation.toUpperCase() + "'|| '%'";
				}
		      
		      
		      
		      sql = sql + " ORDER BY A.ID_DOKUMEN";		      
		      ResultSet rs = stmt.executeQuery(sql);
		      System.out.println("KEPUTUSAN MAHKAMAH:: " +sql);
			    
		      Hashtable h;
		      int bil = 1;
		      int count = 0;	      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("idDokumen",rs.getString("ID_DOKUMEN"));
		    	  h.put("content",rs.getBlob("CONTENT")== null? false : true );
		    	  h.put("idLampiran",rs.getString("ID_LAMPIRAN")== null?"":rs.getString("ID_LAMPIRAN"));
		    	  h.put("skop",rs.getString("SKOP")== null?"":rs.getString("SKOP"));
		    	  h.put("namaKes",rs.getString("NAMA_KES")== null?"":rs.getString("NAMA_KES"));
		    	  h.put("namaSkop",rs.getString("NAMA_SKOP")== null?"":rs.getString("NAMA_SKOP"));
		    	  h.put("rujMahkamah",rs.getString("RUJ_MAHKAMAH")== null?"":rs.getString("RUJ_MAHKAMAH"));
		    	  h.put("tahun",rs.getString("TAHUN")== null?"":rs.getString("TAHUN"));
		    	//penambahan untuk keluarkan tahun
		    	  list.addElement(h);
		    	  bil++;
		    	  count++;
		    	  
		    	  
		    	  
		      }
		 } catch (Exception re) {
			 
		    	myLogger.error("Error: ", re);
				 throw re;
				 }
			     finally {
		      if (db != null) db.close();
		    }
		 return list;
	}

	public void save(String txtSkop, String txtNamaKes, String txtCitation, String tahun, HttpSession session, FileItem item) throws Exception {
		  Db db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();	
	      String sql = "";  
	      Long IDDOKUMEN = DB.getNextID("TBLPDTDOKUMEN_SEQ");
	      r.add("ID_DOKUMEN",IDDOKUMEN);
	      r.add("SKOP",txtSkop);
	      r.add("NAMA_KES",txtNamaKes);
	      r.add("RUJ_MAHKAMAH", txtCitation);
	      r.add("TAHUN", tahun);
	      r.add("FLAG_DOKUMEN", "M");
	      r.add("ID_MASUK",session.getAttribute("_ekptg_user_id"));
	      r.add("TARIKH_MASUK",r.unquote("sysdate"));
	      sql = r.getSQLInsert("TBLPDTDOKUMEN");			      				     
	      stmt.executeUpdate(sql);
	      System.out.println("tambah baru" +sql);
	      
	      Hashtable h = new Hashtable();
			h.put("itemInputStream",item.getInputStream());
			h.put("size",item.getSize());
			h.put("fileName",item.getName());
			Db db1 = new Db();
			java.util.Date today = new java.util.Date();
			Connection con = db1.getConnection();
	    	con.setAutoCommit(false);
	    	PreparedStatement ps;
	    	ps = con.prepareStatement("INSERT INTO TBLPDTRUJLAMPIRAN (ID_LAMPIRAN,ID_DOKUMEN, CONTENT, NAMA_FAIL, ID_MASUK, TARIKH_MASUK)"
	    			+ " VALUES(?,?,?,?,?,?)");
	    	ps.setLong(1, DB.getNextID("TBLPDTRUJLAMPIRAN_SEQ"));
	    	ps.setLong(2, IDDOKUMEN);
	    	ps.setBinaryStream(3, (InputStream) h.get("itemInputStream"),Integer.valueOf(h.get("size").toString()));
	    	ps.setString(4, h.get("fileName").toString());
	    	ps.setString(5, session.getAttribute("_ekptg_user_id").toString());
	    	ps.setDate(6, new java.sql.Date(today.getTime()));
	    	ps.executeUpdate();
	        con.commit();
	        
	        System.out.println(" SAVE KEPUTUSAN MAHKAMAH:: " +sql);
	}

	public Hashtable findTblPdtDokumenById(String idDokumen) throws SQLException, DbException {
		 Db db = null;
		 String sql = "";	
		 ResultSet rs;
		 Hashtable h = new Hashtable();
		 try {
			 //PENAMBAHAN A.TAHUN DI SELECT STATEMENT
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "SELECT A.SKOP,A.NAMA_KES,A.RUJ_MAHKAMAH,A.ID_DOKUMEN, A.TAHUN, B.ID_LAMPIRAN, B.CONTENT  " +
			      		" FROM TBLPDTDOKUMEN A, TBLPDTRUJLAMPIRAN B "
			      		+ " WHERE A.FLAG_DOKUMEN = 'M' "
			      		+ " AND A.ID_DOKUMEN = B.ID_DOKUMEN (+) "
			      		+ " AND A.ID_DOKUMEN = "+idDokumen+" ORDER BY A.ID_DOKUMEN ";
		      rs = stmt.executeQuery(sql);
		      while (rs.next()) {
		    	  h.put("idDokumen",rs.getString("ID_DOKUMEN"));
		    	  h.put("idLampiran",rs.getString("ID_LAMPIRAN")== null?"":rs.getString("ID_LAMPIRAN"));
		    	  h.put("content",rs.getBlob("CONTENT")== null ? false : true);
		    	  h.put("skop",rs.getString("SKOP")== null?"":rs.getString("SKOP"));
		    	  h.put("namaKes",rs.getString("NAMA_KES")== null?"":rs.getString("NAMA_KES"));
		    	  h.put("rujMahkamah",rs.getString("RUJ_MAHKAMAH")== null?"":rs.getString("RUJ_MAHKAMAH"));
		    	  h.put("tahun",rs.getString("TAHUN")== null?"":rs.getString("TAHUN"));
		    	  //penambahan untuk keluarkan tahun
		      }
		 } catch (Exception re) {
		    	myLogger.error("Error: ", re);
				 throw re;
				 }
			     finally {
		      if (db != null) db.close();
		    }
		 return h;
	}

	public void saveEdit(String idDokumen, String txtSkop, String txtNamaKes,
			String txtCitation, String tahun, HttpSession session, FileItem item, String idLampiran) throws Exception {
		  Db db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();	
	      String sql = "";  
	      
	      r.add("SKOP",txtSkop);
	      r.add("NAMA_KES",txtNamaKes);
	      r.add("RUJ_MAHKAMAH", txtCitation);
	      r.add("TAHUN", tahun);
	      r.add("FLAG_DOKUMEN", "M");
	      r.add("ID_KEMASKINI",session.getAttribute("_ekptg_user_id"));
	      r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
	      r.update("ID_DOKUMEN", idDokumen);
	      sql = r.getSQLUpdate("TBLPDTDOKUMEN");			      				     
	      stmt.executeUpdate(sql);
	      
	      Hashtable h = new Hashtable();
			h.put("itemInputStream",item.getInputStream());
			h.put("size",item.getSize());
			h.put("fileName",item.getName());
	      
	      if(idLampiran.equals("")){
				Db db1 = new Db();
				java.util.Date today = new java.util.Date();
				Connection con = db1.getConnection();
		    	con.setAutoCommit(false);
		    	PreparedStatement ps;
	    		ps = con.prepareStatement("INSERT INTO TBLPDTRUJLAMPIRAN (ID_LAMPIRAN,ID_DOKUMEN, CONTENT, NAMA_FAIL, ID_MASUK, TARIKH_MASUK)"
		    			+ " VALUES(?,?,?,?,?,?)");
		    	ps.setLong(1, DB.getNextID("TBLPDTRUJLAMPIRAN_SEQ"));
		    	ps.setLong(2, Long.parseLong(idDokumen));
		    	ps.setBinaryStream(3, (InputStream) h.get("itemInputStream"),Integer.valueOf(h.get("size").toString()));
		    	ps.setString(4, h.get("fileName").toString());
		    	ps.setString(5, session.getAttribute("_ekptg_user_id").toString());
		    	ps.setDate(6, new java.sql.Date(today.getTime()));
		    	ps.executeUpdate();
		        con.commit();
	      }else{
	    	  Db db1 = new Db();
				java.util.Date today = new java.util.Date();
				Connection con = db1.getConnection();
		    	con.setAutoCommit(false);
		    	PreparedStatement ps;
	    		ps = con.prepareStatement("UPDATE TBLPDTRUJLAMPIRAN SET ID_DOKUMEN = ?, CONTENT = ?, NAMA_FAIL = ?, ID_KEMASKINI = ?, TARIKH_KEMASKINI = ? WHERE ID_LAMPIRAN = ?");
		    	ps.setLong(1, Long.parseLong(idDokumen));
		    	ps.setBinaryStream(2, (InputStream) h.get("itemInputStream"),Integer.valueOf(h.get("size").toString()));
		    	ps.setString(3, h.get("fileName").toString());
		    	ps.setString(4, session.getAttribute("_ekptg_user_id").toString());
		    	ps.setDate(5, new java.sql.Date(today.getTime()));
		    	ps.setLong(6, Long.parseLong(idLampiran));
		    	ps.executeUpdate();
		        con.commit();
	      }
	      System.out.println(" SAVE EDIT KEPUTUSAN MAHKAMAH:: " +sql);
	     
	       
	}

	public void delete(String idDokumen) throws DbException, SQLException {
		Db db = new Db();
		String sql = "";
		String sql1 = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Dokumen",idDokumen);
			sql = r.getSQLDelete("TBLPDTDOKUMEN");
			
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.add("id_Dokumen",idDokumen);
			sql1 = r1.getSQLDelete("TBLPDTRUJLAMPIRAN");
			stmt1.executeUpdate(sql1);
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

	public void save(String txtSkop, String txtNamaKes, String txtCitation, String tahun, 
			HttpSession session) throws Exception {
		 Db db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();	
	      String sql = "";  
	      Long IDDOKUMEN = DB.getNextID("TBLPDTDOKUMEN_SEQ");
	      r.add("ID_DOKUMEN",IDDOKUMEN);
	      r.add("SKOP",txtSkop);
	      r.add("NAMA_KES",txtNamaKes);
	      r.add("RUJ_MAHKAMAH", txtCitation);
	      r.add("TAHUN", tahun);
	      r.add("FLAG_DOKUMEN", "M");
	      r.add("ID_MASUK",session.getAttribute("_ekptg_user_id"));
	      r.add("TARIKH_MASUK",r.unquote("sysdate"));
	      sql = r.getSQLInsert("TBLPDTDOKUMEN");	
	      myLogger.info(" sql TBLPDTDOKUMEN : "+sql);
	      stmt.executeUpdate(sql);
	      
			Db db1 = new Db();
			java.util.Date today = new java.util.Date();
			Connection con = db1.getConnection();
	    	con.setAutoCommit(false);
	    	PreparedStatement ps;
	    	ps = con.prepareStatement("INSERT INTO TBLPDTRUJLAMPIRAN (ID_LAMPIRAN,ID_DOKUMEN, ID_MASUK, TARIKH_MASUK)"
	    			+ " VALUES(?,?,?,?)");
	    	ps.setLong(1, DB.getNextID("TBLPDTRUJLAMPIRAN_SEQ"));
	    	ps.setLong(2, IDDOKUMEN);
	    	ps.setString(3, session.getAttribute("_ekptg_user_id").toString());
	    	ps.setDate(4, new java.sql.Date(today.getTime()));
	    	ps.executeUpdate();
	        con.commit();
		
	}

	public void saveEdit(String idDokumen, String txtSkop, String txtNamaKes,
			String txtCitation, String tahun, HttpSession session, String idLampiran) throws SQLException, Exception {
		Db db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();	
	      String sql = "";  
	      
	      r.add("SKOP",txtSkop);
	      r.add("NAMA_KES",txtNamaKes);
	      r.add("RUJ_MAHKAMAH", txtCitation);
	      r.add("TAHUN", tahun);
	      r.add("FLAG_DOKUMEN", "M");
	      r.add("ID_KEMASKINI",session.getAttribute("_ekptg_user_id"));
	      r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
	      r.update("ID_DOKUMEN", idDokumen);
	      sql = r.getSQLUpdate("TBLPDTDOKUMEN");			      				     
	      stmt.executeUpdate(sql);
	      
	      if(idLampiran.equals("")){
	    	  Db db1 = new Db();
				java.util.Date today = new java.util.Date();
				Connection con = db1.getConnection();
		    	con.setAutoCommit(false);
		    	PreparedStatement ps;
	    		ps = con.prepareStatement("INSERT INTO TBLPDTRUJLAMPIRAN (ID_LAMPIRAN, ID_DOKUMEN, ID_MASUK, TARIKH_MASUK)"
		    			+ " VALUES(?,?,?,?)");
		    	ps.setLong(1, DB.getNextID("TBLPDTRUJLAMPIRAN_SEQ"));
		    	ps.setLong(2, Long.parseLong(idDokumen));
		    	ps.setString(3, session.getAttribute("_ekptg_user_id").toString());
		    	ps.setDate(4, new java.sql.Date(today.getTime()));
		    	ps.executeUpdate();
		        con.commit();
	      }else{
	    	  Db db1 = new Db();
				java.util.Date today = new java.util.Date();
				Connection con = db1.getConnection();
		    	con.setAutoCommit(false);
		    	PreparedStatement ps;
	    		ps = con.prepareStatement("UPDATE TBLPDTRUJLAMPIRAN SET ID_DOKUMEN = ?, ID_MASUK = ?, TARIKH_MASUK = ? WHERE ID_LAMPIRAN = ?");
		    	ps.setLong(1, Long.parseLong(idDokumen));
		    	ps.setString(2, session.getAttribute("_ekptg_user_id").toString());
		    	ps.setDate(3, new java.sql.Date(today.getTime()));
		    	ps.setLong(4, Long.parseLong(idLampiran));
		    	ps.executeUpdate();
		        con.commit();
	      }
				
		
	}
}
