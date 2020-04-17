package ekptg.model.pdt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmDokBerkaitanTanahRizabMelayuData {
	private static Logger myLogger = Logger.getLogger(FrmDokBerkaitanTanahRizabMelayuData.class);
	private static Vector list = new Vector();
	public static void  setCarianDokumen(String noRujukanDokumen,String tajukDokumen, String tarikhDokumen, String seksyen) throws Exception{
		 Db db = null;
		 list.clear();
		 String sql = "";
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_DOKUMEN,A.TAJUK_DOKUMEN,A.NO_DOKUMEN,A.TARIKH_DOKUMEN,B.KOD_SEKSYEN, A.JENIS_DOKUMEN" +
		      		" FROM TBLPDTDOKUMEN A, TBLRUJSEKSYEN B" +
		      		" WHERE A.ID_SEKSYEN = B.ID_SEKSYEN";
		      
		      if (noRujukanDokumen != null) {
					if (!noRujukanDokumen.trim().equals("")) {
						sql = sql + " AND UPPER(A.JENIS_DOKUMEN) LIKE '%' ||'" + noRujukanDokumen.toUpperCase() + "'|| '%'";
					}
				}
		      
		      if (noRujukanDokumen != null) {
					if (!noRujukanDokumen.trim().equals("")) {
						sql = sql + " AND UPPER(A.NO_DOKUMEN) LIKE '%' ||'" + noRujukanDokumen.toUpperCase() + "'|| '%'";
					}
				}
		      
		      //TAJUK DOKUMEN
		      if (tajukDokumen != null) {
					if (!tajukDokumen.trim().equals("")) {
						sql = sql + " AND UPPER(A.TAJUK_DOKUMEN) LIKE '%' ||'" + tajukDokumen.toUpperCase() + "'|| '%'";
					}
				}
		      
		      //TARIKH DOKUMEN
		      SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
		      if (tarikhDokumen != null) {
					if (!tarikhDokumen.toString().trim().equals("")) {			 
						sql = sql + " AND A.TARIKH_DOKUMEN = '" + sdf1.format(sdf.parse(tarikhDokumen)).toUpperCase() +"'";
					}
				}
		      
		      //SEKSYEN
		      if (seksyen != null) {
					if (!seksyen.trim().equals("")) {
						if (!seksyen.trim().equals("0")) {
							sql = sql + " AND A.ID_SEKSYEN = '" + seksyen + "'";
						}
					}
				}
		      
		      sql = sql + " ORDER BY A.ID_DOKUMEN";
		      
		      ResultSet rs = stmt.executeQuery(sql);
			    
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("idDokumen",rs.getString("ID_DOKUMEN"));
		    	  h.put("jenisDokumen",rs.getString("JENIS_DOKUMEN")== null?"":rs.getString("JENIS_DOKUMEN"));
		    	  h.put("noRujukanDokumen",rs.getString("NO_DOKUMEN")== null?"":rs.getString("NO_DOKUMEN"));
		    	  h.put("tajukDokumen",rs.getString("TAJUK_DOKUMEN")== null?"":rs.getString("TAJUK_DOKUMEN"));
		    	  h.put("tarikhDokumen",rs.getDate("TARIKH_DOKUMEN")== null?"":sdf.format(rs.getDate("TARIKH_DOKUMEN")));
		    	  h.put("kodSeksyen", rs.getString("KOD_SEKSYEN")== null?"":rs.getString("KOD_SEKSYEN"));
		    	  list.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "");
		    	  h.put("idDokumen",0);
		    	  h.put("noRujukanDokumen","Tiada rekod.");
		    	  h.put("tajukDokumen","");
		    	  h.put("tarikhDokumen","");
		    	  h.put("kodSeksyen","");
		    	  list.addElement(h);
		      }
		 }catch (Exception re) {
			 myLogger.error("Error: ", re);
			 throw re;
			 }

		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public static Vector getCarianDokumen(String noRujukanDokumen, String tajukDokumen
			, String tarikhDokumen, String seksyen, String tag) throws Exception{
		 Db db = null;
		 list.clear();
		 String sql = "";	 
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_DOKUMEN,A.TAJUK_DOKUMEN,A.NO_DOKUMEN,A.TARIKH_DOKUMEN,B.KOD_SEKSYEN, A.JENIS_DOKUMEN" +
		      		" FROM TBLPDTDOKUMEN A, TBLRUJSEKSYEN B" +
		      		" WHERE A.ID_SEKSYEN = B.ID_SEKSYEN "
		      		+ " AND A.FLAG_DOKUMEN = 'T'";
		      
		      if (noRujukanDokumen != null) {
					if (!noRujukanDokumen.trim().equals("")) {
						sql = sql + " AND UPPER(A.JENIS_DOKUMEN) LIKE '%' ||'" + noRujukanDokumen.toUpperCase() + "'|| '%'";
					}
				}
		      
		    /*  if (noRujukanDokumen != null) {
					if (!noRujukanDokumen.trim().equals("")) {
						sql = sql + " AND UPPER(A.NO_DOKUMEN) LIKE '%' ||'" + noRujukanDokumen.toUpperCase() + "'|| '%'";
					}
				}*/
		      
		      //TAJUK DOKUMEN
		      if (tajukDokumen != null) {
					if (!tajukDokumen.trim().equals("")) {
						sql = sql + " AND UPPER(A.TAJUK_DOKUMEN) LIKE '%' ||'" + tajukDokumen.toUpperCase() + "'|| '%'";
					}
				}
		      
		      //TARIKH DOKUMEN
		      SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
		      if (tarikhDokumen != null) {
					if (!tarikhDokumen.toString().trim().equals("")) {			 
						sql = sql + " AND A.TARIKH_DOKUMEN = '" + sdf1.format(sdf.parse(tarikhDokumen)).toUpperCase() +"'";
					}
				}
		      
		      //SEKSYEN
		      if (seksyen != null) {
					if (!seksyen.trim().equals("")) {
						if (!seksyen.trim().equals("0")) {
							sql = sql + " AND A.ID_SEKSYEN = '" + seksyen + "'";
						}
					}
				}
			
		      if (!"".equalsIgnoreCase(tag)) {
		    	  sql += " AND A.ID_DOKUMEN IN ( " +
		    	  	" SELECT TAGI.ID_DOKUMEN FROM TBLRUJTAGDOKUMEN TAGI,TBLPDTDOKUMEN DTTI  " +
					" WHERE DTTI.ID_DOKUMEN = TAGI.ID_DOKUMEN AND TAGI.SUMBER='DOKUMEN' " +
					" AND TAGI.TAG_DOKUMEN LIKE '%" + tag + "%' )";
		      }
		      
		      sql = sql + " ORDER BY A.ID_DOKUMEN";		      
		      ResultSet rs = stmt.executeQuery(sql);
			    
		      Hashtable h;
		      int bil = 1;
		      int count = 0;	      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("idDokumen",rs.getString("ID_DOKUMEN"));
		    	  h.put("jenisDokumen",rs.getString("JENIS_DOKUMEN")== null?"":rs.getString("JENIS_DOKUMEN"));
		    	  h.put("noRujukanDokumen",rs.getString("NO_DOKUMEN")== null?"":rs.getString("NO_DOKUMEN"));
		    	  h.put("tajukDokumen",rs.getString("TAJUK_DOKUMEN")== null?"":rs.getString("TAJUK_DOKUMEN"));
		    	  h.put("tarikhDokumen",rs.getDate("TARIKH_DOKUMEN")== null?"":sdf.format(rs.getDate("TARIKH_DOKUMEN")));
		    	  h.put("kodSeksyen", rs.getString("KOD_SEKSYEN")== null?"":rs.getString("KOD_SEKSYEN"));
		    	  list.addElement(h);
		    	  bil++;
		    	  count++;
		    	  
		      }
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h = null;
		    	 /* h.put("bil", "");
		    	  h.put("idDokumen",0);
		    	  h.put("noRujukanDokumen","Tiada rekod.");
		    	  h.put("tajukDokumen","");
		    	  h.put("tarikhDokumen","");
		    	  h.put("kodSeksyen","");
		    	  list.addElement(h);*/
		      
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

	public static Vector getList(){		 
		return list;
	}

	public static void Delete(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
				
			r.add("id_Dokumen",idDokumen);
			sql = r.getSQLDelete("TBLPDTRUJLAMPIRAN");
			stmt.executeUpdate(sql);
				
			r.clear();
				
			r.add("id_Dokumen",idDokumen);
			sql = r.getSQLDelete("TBLPDTDOKUMEN");
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



}
