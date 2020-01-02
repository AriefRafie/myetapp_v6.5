package ekptg.model.pdt;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmMesyuaratData {

	static Logger myLogger = Logger.getLogger(FrmMesyuaratData.class);
	
	public Long[] saveMesyuarat(String paramTajuk, String paramTahun,
			String paramBilMesyuarat, HttpSession session) throws Exception {
		
		Long ID_TBLPDTRUJMESYUARAT = null, ID_TBLPDTMESYUARAT = null;
		Db db = new Db();
		Db db1 = new Db();
		try {
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();	
		      String sql = "";  
		      
		      ID_TBLPDTRUJMESYUARAT = DB.getNextID("TBLPDTRUJMESYUARAT_SEQ");
		      r.add("ID_TBLPDTRUJMESYUARAT",ID_TBLPDTRUJMESYUARAT);
		      r.add("TAJUK_MESYUARAT",paramTajuk);
		      r.add("ID_MASUK",session.getAttribute("_ekptg_user_id"));
		      r.add("TARIKH_MASUK",r.unquote("sysdate"));
		      sql = r.getSQLInsert("TBLPDTRUJMESYUARAT");			      				     
		      stmt.executeUpdate(sql);
		      
		      
		      Statement stmt1 = db1.getStatement();
		      SQLRenderer r1 = new SQLRenderer();	
		      String sql1 = "";  
		      
		      ID_TBLPDTMESYUARAT = DB.getNextID("TBLPDTMESYUARAT_SEQ");
		      r1.add("ID_TBLPDTMESYUARAT",ID_TBLPDTMESYUARAT);
		      r1.add("ID_TBLPDTRUJMESYUARAT",ID_TBLPDTRUJMESYUARAT);
		      r1.add("TAHUN",paramTahun);
		      r1.add("BIL_MESYUARAT",paramBilMesyuarat);
		      r1.add("ID_MASUK",session.getAttribute("_ekptg_user_id"));
		      r1.add("TARIKH_MASUK",r1.unquote("sysdate"));
		      sql1 = r1.getSQLInsert("TBLPDTMESYUARAT");			      				     
		      stmt1.executeUpdate(sql1);
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}finally{
			db.close();
			db1.close();
		}
		
		Long[] ids = {ID_TBLPDTRUJMESYUARAT,ID_TBLPDTMESYUARAT};
		return ids;
	}
	
	
	public Object paramStatus(String param) throws Exception{

		StringBuffer sb = new StringBuffer("");
		try {
			
			Vector list = new Vector();
			list = findDistictTajukMesyuarat();
			sb.append("<select name='paramTajuk' id='paramTajuk' ");
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			for (Object object : list) {
				
				Hashtable data = (Hashtable)object;
				
				if(param.equals(data.get("tajukMesyuarat"))){
					sb.append("<option selected value='"+data.get("tajukMesyuarat")+"'>"+data.get("tajukMesyuarat")+"</option>\n");
				}else{
					sb.append("<option value='"+data.get("tajukMesyuarat")+"'>"+data.get("tajukMesyuarat")+"</option>\n");
				}
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}


	private Vector findDistictTajukMesyuarat() throws Exception {
		 Db db = null;
		 Vector list = new Vector();
		 String sql = "";	 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT DISTINCT TAJUK_MESYUARAT FROM TBLPDTRUJMESYUARAT ORDER BY TAJUK_MESYUARAT ";
		      ResultSet rs = stmt.executeQuery(sql);
			    
		      Hashtable h;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("tajukMesyuarat",rs.getString("TAJUK_MESYUARAT")== null ? "" : rs.getString("TAJUK_MESYUARAT"));
		    	  list.addElement(h);
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


	public Vector findMesyuarat(String paramTajuk, String paramTahun,
			String paramBilMesyuarat, String paramTajukDokumen) throws Exception {
		 Db db = null;
		 Vector list = new Vector();
		 String sql = "";	 
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_TBLPDTMESYUARAT, B.ID_TBLPDTRUJMESYUARAT, A.BIL_MESYUARAT,A.TAHUN,B.TAJUK_MESYUARAT, C.NAMA_FAIL " +
			      		" FROM TBLPDTMESYUARAT A, TBLPDTRUJMESYUARAT B, TBLPDTRUJLAMPIRAN C "
			      		+ " WHERE A.ID_TBLPDTRUJMESYUARAT = B.ID_TBLPDTRUJMESYUARAT "
			      		+ " AND A.ID_TBLPDTMESYUARAT = C.ID_TBLPDTMESYUARAT (+)";
		      
		      if (paramTajuk != null && !paramTajuk.trim().equals("")) {
						sql = sql + " AND UPPER(B.TAJUK_MESYUARAT) LIKE '%' ||'" + paramTajuk.toUpperCase() + "'|| '%'";
				}
		      
		      if (paramTahun != null && !paramTahun.trim().equals("")) {
						sql = sql + " AND UPPER(A.TAHUN) LIKE '%' ||'" + paramTahun.toUpperCase() + "'|| '%'";
				}
		      
		      if (paramBilMesyuarat != null && !paramBilMesyuarat.trim().equals("")) {
						sql = sql + " AND UPPER(A.BIL_MESYUARAT) LIKE '%' ||'" + paramBilMesyuarat.toUpperCase() + "'|| '%'";
				}
		      
		      if (paramTajukDokumen != null && !paramTajukDokumen.trim().equals("")) {
					sql = sql + " AND UPPER(C.NAMA_FAIL) LIKE '%' ||'" + paramTajukDokumen.toUpperCase() + "'|| '%'";
		      }
		      
		      
		      sql = sql + " ORDER BY A.TAHUN DESC , B.TAJUK_MESYUARAT DESC ";
		      myLogger.info("-------- SQL MESYUARAT :"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
			    
		      Hashtable h;
		      int bil = 1;
		      int count = 0;	      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  
		    	  myLogger.info(bil+"_ID_TBLPDTMESYUARAT :"+rs.getString("ID_TBLPDTMESYUARAT"));
		    	  myLogger.info(bil+"_ID_TBLPDTRUJMESYUARAT :"+rs.getString("ID_TBLPDTRUJMESYUARAT"));
		    	  h.put("idMesyuarat",rs.getString("ID_TBLPDTMESYUARAT") == null ? "" : rs.getString("ID_TBLPDTMESYUARAT"));
		    	  h.put("idRujMesyuarat",rs.getString("ID_TBLPDTRUJMESYUARAT")== null ? "" : rs.getString("ID_TBLPDTRUJMESYUARAT"));
		    	  h.put("tajukMesyuarat",rs.getString("TAJUK_MESYUARAT")== null ? "" : rs.getString("TAJUK_MESYUARAT"));
		    	  h.put("tahun",rs.getString("TAHUN")== null?"":rs.getString("TAHUN"));
		    	  h.put("bilMesyuarat",rs.getString("BIL_MESYUARAT")== null?"":rs.getString("BIL_MESYUARAT"));
		    	  h.put("tajukDokumen",rs.getString("NAMA_FAIL")== null?"":rs.getString("NAMA_FAIL"));
		    	  list.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		 }catch (Exception re) {
		    	myLogger.error("Error: ", re);
				 throw re;
				 }
			      finally {
		      if (db != null) db.close();
		    }
		 return list;
	}


	public void saveLampiran(String txtNamaDokumen, String paramJenis,
			String idTblPdtMesyuarat, FileItem item, HttpSession session) throws Exception {
		Db db1 = new Db();
		try {
			Hashtable h = new Hashtable();
			h.put("itemInputStream",item.getInputStream());
			h.put("size",item.getSize());
			h.put("fileName",item.getName());
			java.util.Date today = new java.util.Date();
			Connection con = db1.getConnection();
	    	con.setAutoCommit(false);
	    	PreparedStatement ps;
	    	ps = con.prepareStatement("INSERT INTO TBLPDTRUJLAMPIRAN (ID_LAMPIRAN,ID_TBLPDTMESYUARAT,ID_JENISDOKUMEN, CONTENT, NAMA_FAIL, ID_MASUK, TARIKH_MASUK)"
	    			+ " VALUES(?,?,?,?,?,?,?)");
	    	ps.setLong(1, DB.getNextID("TBLPDTRUJLAMPIRAN_SEQ"));
	    	ps.setLong(2, Long.parseLong(idTblPdtMesyuarat));
	    	ps.setLong(3, Long.parseLong(paramJenis));
	    	ps.setBinaryStream(4, (InputStream) h.get("itemInputStream"),Integer.valueOf(h.get("size").toString()));
	    	ps.setString(5, txtNamaDokumen);
	    	ps.setString(6, session.getAttribute("_ekptg_user_id").toString());
	    	ps.setDate(7, new java.sql.Date(today.getTime()));
	    	ps.executeUpdate();
	        con.commit();
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}finally{
			db1.close();
		}
	}


	public Vector findRujLampiran(String idTblPdtMesyuarat) {
		 Db db = null;
		 Vector list = new Vector();
		 String sql = "";	 
		 try {
			 db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "SELECT A.ID_LAMPIRAN,A.NAMA_FAIL,B.KETERANGAN " +
			      		" FROM TBLPDTRUJLAMPIRAN A, TBLRUJJENISDOKUMEN B "
			      		+ " WHERE A.ID_JENISDOKUMEN = B.ID_JENISDOKUMEN "
			      		+ " AND A.ID_TBLPDTMESYUARAT ="+idTblPdtMesyuarat;
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h;
		      int bil = 1;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("idLampiran",rs.getString("ID_LAMPIRAN")== null?"":rs.getString("ID_LAMPIRAN"));
		    	  h.put("namaFail",rs.getString("NAMA_FAIL")== null?"":rs.getString("NAMA_FAIL"));
		    	  h.put("jenisDokumen",rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
		    	  list.addElement(h);
		    	  bil++;
		      }
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}finally{
			db.close();
		}
		return list;
	}


	public Hashtable findMesyuaratBy(String idTblPdtMesyuarat) { Db db = null;
	 String sql = "";	 
	 Hashtable h = new Hashtable();
	 try {
		 db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "SELECT A.BIL_MESYUARAT,A.TAHUN,B.TAJUK_MESYUARAT " +
		      		" FROM TBLPDTMESYUARAT A, TBLPDTRUJMESYUARAT B "
		      		+ " WHERE A.ID_TBLPDTRUJMESYUARAT = B.ID_TBLPDTRUJMESYUARAT "
		      		+ " AND A.ID_TBLPDTMESYUARAT ="+idTblPdtMesyuarat;
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bilMesyuarat",rs.getString("BIL_MESYUARAT")== null?"":rs.getString("BIL_MESYUARAT"));
	    	  h.put("tahun",rs.getString("TAHUN")== null?"":rs.getString("TAHUN"));
	    	  h.put("tajukMesyuarat",rs.getString("TAJUK_MESYUARAT")== null?"":rs.getString("TAJUK_MESYUARAT"));
	      }
	} catch (Exception e) {
		myLogger.info(e.getMessage());
	}finally{
		db.close();
	}
	return h;
	}


	public void deleteMesy(String idTblPdtMesyuarat, String idRujMesyuarat) throws Exception {
		Db db = new Db();
		Db db1 = new Db();
		Db db2 = new Db();
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_TBLPDTMESYUARAT",idTblPdtMesyuarat);
			sql = r.getSQLDelete("TBLPDTMESYUARAT");
			
			Statement stmt1 = db1.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.add("ID_TBLPDTRUJMESYUARAT",idRujMesyuarat);
			sql1 = r1.getSQLDelete("TBLPDTRUJMESYUARAT");
			
			Statement stmt2 = db2.getStatement();
			SQLRenderer r2 = new SQLRenderer();
			r2.add("ID_TBLPDTMESYUARAT",idTblPdtMesyuarat);
			sql2 = r2.getSQLDelete("TBLPDTRUJLAMPIRAN");
			
			stmt2.executeUpdate(sql2);
			stmt.executeUpdate(sql);
			stmt1.executeUpdate(sql1);
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
				db1.close();
				db2.close();
		}
		
	}


	public void deleteLamp(String idLampiran)  throws Exception {
		Db db = new Db();
		String sql = "";
		try {
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		r.add("ID_LAMPIRAN",idLampiran);
		sql = r.getSQLDelete("TBLPDTRUJLAMPIRAN");
		stmt.executeUpdate(sql);
	} catch (Exception re) {
    	myLogger.error("Error: ", re);
		 throw re;
		 }
	     finally {
		if (db != null)
			db.close();
	}}


	public void kemaskiniMesyuarat(String paramTajuk, String paramTahun,
			String paramBilMesyuarat, HttpSession session,
			String idTblPdtMesyuarat, String idRujMesyuarat)throws Exception {
		Db db = new Db();
		Db db1 = new Db();
		try {
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();	
		      String sql = "";  
		      
		      
		      r.add("TAJUK_MESYUARAT",paramTajuk);
		      r.add("ID_KEMASKINI",session.getAttribute("_ekptg_user_id"));
		      r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
		      r.update("ID_TBLPDTRUJMESYUARAT",idTblPdtMesyuarat);
		      sql = r.getSQLUpdate("TBLPDTRUJMESYUARAT");			      				     
		      stmt.executeUpdate(sql);
		      
		      
		      Statement stmt1 = db1.getStatement();
		      SQLRenderer r1 = new SQLRenderer();	
		      String sql1 = "";  
		      
		      
		      r1.add("TAHUN",paramTahun);
		      r1.add("BIL_MESYUARAT",paramBilMesyuarat);
		      r1.add("ID_KEMASKINI",session.getAttribute("_ekptg_user_id"));
		      r1.add("TARIKH_KEMASKINI",r1.unquote("sysdate"));
		      r1.update("ID_TBLPDTMESYUARAT",idRujMesyuarat);
		      sql1 = r1.getSQLUpdate("TBLPDTMESYUARAT");			      				     
		      stmt1.executeUpdate(sql1);
		} catch (Exception e) {
			myLogger.info(e.getMessage());
		}finally{
			db.close();
			db1.close();
		}
	}
}
