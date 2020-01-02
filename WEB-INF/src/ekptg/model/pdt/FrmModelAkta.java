package ekptg.model.pdt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmModelAkta {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static Logger myLogger = Logger.getLogger(FrmModelAkta.class);
	@SuppressWarnings("unchecked")
	public Vector aktaCarian(String NoAkta, String NamaAkta, String TarikhKuatkuasa, String ID_Seksyen) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector list = new Vector();
		Vector v = new Vector();
		
		Hashtable h = null;
		
		try {
			String SQL_SEARCH = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			if (!"".equalsIgnoreCase(NoAkta)) {
				SQL_SEARCH += " AND UPPER(M.NO_AKTA) LIKE '%" + NoAkta.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(NamaAkta)) {
				SQL_SEARCH += " AND UPPER(M.NAMA_AKTA) LIKE '%" + NamaAkta.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(TarikhKuatkuasa)) {
				SQL_SEARCH += " AND TO_CHAR(M.TARIKH_KUATKUASA, 'dd/MM/yyyy') = '" + TarikhKuatkuasa + "' ";
			}
			if (!"".equalsIgnoreCase(ID_Seksyen)) {
				SQL_SEARCH += " AND M.ID_SEKSYEN = '" + ID_Seksyen + "' ";
			}
			
			int iCount = 1;
			String RS_IDAkta = "", RS_NoAkta = "", RS_NamaAkta = "", RS_TarikhKuatkuasa = "", RS_NamaSeksyen = "";
			sql = "SELECT M.ID_AKTA, M.NO_AKTA, M.NAMA_AKTA, M.TARIKH_KUATKUASA, C.NAMA_SEKSYEN " +
				"FROM TBLPDTAKTA M, TBLRUJSEKSYEN C " +
				"WHERE M.ID_SEKSYEN = C.ID_SEKSYEN(+) " +
				SQL_SEARCH;
			sql += " ORDER BY M.TARIKH_MASUK";
			myLogger.info("test :: "+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RS_IDAkta = rs.getString(1) == null ? "" : rs.getString(1);
				RS_NoAkta = rs.getString(2) == null ? "" : rs.getString(2);
				RS_NamaAkta = rs.getString(3) == null ? "" : rs.getString(3);
				RS_TarikhKuatkuasa = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				RS_NamaSeksyen = rs.getString(5) == null ? "" : rs.getString(5);
				h = new Hashtable();
				h.put("No", iCount);
				h.put("IDAkta", RS_IDAkta);
				h.put("NoAkta", RS_NoAkta);
				h.put("NamaAkta", RS_NamaAkta);
				h.put("TarikhKuatkuasa", RS_TarikhKuatkuasa);
				h.put("NamaSeksyen", RS_NamaSeksyen);
				v.addElement(h);
				iCount++;
			}
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector aktaCarian(String NoAkta, String NamaAkta
			, String TarikhKuatkuasa, String ID_Seksyen, String tag) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			String SQL_SEARCH = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			if (!"".equalsIgnoreCase(NoAkta)) {
				SQL_SEARCH += " AND UPPER(M.NO_AKTA) LIKE '%" + NoAkta.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(NamaAkta)) {
				SQL_SEARCH += " AND UPPER(M.NAMA_AKTA) LIKE '%" + NamaAkta.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(TarikhKuatkuasa)) {
				SQL_SEARCH += " AND TO_CHAR(M.TARIKH_KUATKUASA, 'dd/MM/yyyy') = '" + TarikhKuatkuasa + "' ";
			}
			//SILA PILIH = -1
			if (!"".equalsIgnoreCase(ID_Seksyen) && ID_Seksyen.length() == -1) {
				SQL_SEARCH += " AND M.ID_SEKSYEN = '" + ID_Seksyen + "' ";
			}
			if (!"".equalsIgnoreCase(tag)) {
				SQL_SEARCH += " AND M.ID_AKTA IN ( " +
					" SELECT ID_DOKUMEN FROM TBLRUJTAGDOKUMEN " +
					" WHERE SUMBER ='AKTA' " +
					" AND TAG_DOKUMEN LIKE '%" + tag + "%' )";

			}
			
			int iCount = 1;
			String RS_IDAkta = "", RS_NoAkta = "", RS_NamaAkta = "", RS_TarikhKuatkuasa = "", RS_NamaSeksyen = "";
			sql = "SELECT M.ID_AKTA, M.NO_AKTA, M.NAMA_AKTA, M.TARIKH_KUATKUASA, C.NAMA_SEKSYEN " +
				//"FROM TBLPDTAKTA M, TBLRUJSEKSYEN C "+
				" FROM TBLPDTAKTA M, TBLRUJSEKSYEN C "+
				" WHERE M.ID_SEKSYEN = C.ID_SEKSYEN(+) "+
	         	SQL_SEARCH;
			sql += " ORDER BY M.ID_AKTA";
			myLogger.info("ACTA CARIAN test :: "+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RS_IDAkta = rs.getString(1) == null ? "" : rs.getString(1);
				RS_NoAkta = rs.getString(2) == null ? "" : rs.getString(2);
				RS_NamaAkta = rs.getString(3) == null ? "" : rs.getString(3);
				RS_TarikhKuatkuasa = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				RS_NamaSeksyen = rs.getString(5) == null ? "" : rs.getString(5);
				h = new Hashtable();
				h.put("No", iCount);
				h.put("IDAkta", RS_IDAkta);
				h.put("NoAkta", RS_NoAkta);
				h.put("NamaAkta", RS_NamaAkta);
				h.put("TarikhKuatkuasa", RS_TarikhKuatkuasa);
				h.put("NamaSeksyen", RS_NamaSeksyen);
				v.addElement(h);
				iCount++;
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
		
	}

	
	
	
	@SuppressWarnings("unchecked")
	public Vector aktaPaparFail(String IDAkta) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(IDAkta)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				
				String RS_NoAkta = "", RS_NamaAkta = "", RS_NoFail = "";
				sql = "SELECT M.NO_AKTA, M.NAMA_AKTA, F.NO_FAIL " +
					"FROM TBLPDTAKTA M, TBLRUJSEKSYEN C, TBLPFDFAIL F " +
					"WHERE M.ID_SEKSYEN = C.ID_SEKSYEN(+) AND M.ID_FAIL = F.ID_FAIL " +
					"AND M.ID_AKTA = " + IDAkta;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_NoAkta = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NamaAkta = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NoFail = rs.getString(3) == null ? "" : rs.getString(3);
					h = new Hashtable();
					h.put("Akta_IDAkta", IDAkta);
					h.put("Akta_NoAkta", RS_NoAkta);
					h.put("Akta_NamaAkta", RS_NamaAkta);
					h.put("Akta_NoFail", RS_NoFail);
					v.addElement(h);
				}				
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}	
	@SuppressWarnings("unchecked")
	public Vector aktaPapar(String IDAkta) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(IDAkta)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				
				String RS_Doc = "",RS_NoAkta = "", RS_NamaAkta = "", RS_TarikhKuatkuasa = "", RS_TarikhMansuh = "", RS_NoWarta = "", RS_TarikhWarta = "", RS_TarafKeselamatan = "", RS_IDSeksyen = "", RS_NoFail = "", RS_Catatan = "", RS_TarikhDaftar = "";
				sql = "SELECT M.NO_AKTA, M.NAMA_AKTA, M.TARIKH_KUATKUASA, M.TARIKH_MANSUH, M.NO_WARTA, M.TARIKH_WARTA, M.ID_TARAFKESELAMATAN, C.ID_SEKSYEN,  " +
					"M.NO_FAIL, M.CATATAN, M.TARIKH_DAFTAR " +
					",NVL((SELECT TAG_DOKUMEN FROM TBLRUJTAGDOKUMEN WHERE ID_DOKUMEN=M.ID_AKTA AND SUMBER ='AKTA' ),'') TAG_DOKUMEN " +
					",NVL((SELECT ID_RUJTAG FROM TBLRUJTAGDOKUMEN WHERE ID_DOKUMEN=M.ID_AKTA AND SUMBER ='AKTA' ),'0') ID_DOKUMEN, " +
					" M.JENIS_MIME " +
					"FROM TBLPDTAKTA M, TBLRUJSEKSYEN C, TBLPFDFAIL F " +
					"WHERE M.ID_SEKSYEN = C.ID_SEKSYEN(+) AND M.ID_FAIL = F.ID_FAIL(+) " +
					"AND M.ID_AKTA = " + IDAkta;
				myLogger.info(" aktaPapar ::::::::: "+sql);
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_NoAkta = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NamaAkta = rs.getString(2) == null ? "" : rs.getString(2);
					RS_TarikhKuatkuasa = rs.getDate(3) == null ? "" : sdf.format(rs.getDate(3));
					RS_TarikhMansuh = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
					RS_NoWarta = rs.getString(5) == null ? "" : rs.getString(5);
					RS_TarikhWarta = rs.getDate(6) == null ? "" : sdf.format(rs.getDate(6));
					RS_TarafKeselamatan = rs.getString(7) == null ? "" : rs.getString(7);
					RS_IDSeksyen = rs.getString(8) == null ? "" : rs.getString(8);
					RS_NoFail = rs.getString(9) == null ? "" : rs.getString(9);
					RS_Catatan = rs.getString(10) == null ? "" : rs.getString(10);
					RS_TarikhDaftar = rs.getDate(11) == null ? "" : sdf.format(rs.getDate(11));
					
					RS_Doc = rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME");
					
					h = new Hashtable();
					h.put("Akta_IDAkta", IDAkta);
					h.put("Akta_NoAkta", RS_NoAkta);
					h.put("Akta_NamaAkta", RS_NamaAkta);
					h.put("Akta_TarikhKuatkuasa", RS_TarikhKuatkuasa);
					h.put("Akta_TarikhMansuh", RS_TarikhMansuh);
					h.put("Akta_NoWarta", RS_NoWarta);
					h.put("Akta_TarikhWarta", RS_TarikhWarta);
					h.put("Akta_TarafKeselamatan", RS_TarafKeselamatan);
					h.put("Akta_IDSeksyen", RS_IDSeksyen);
					h.put("Akta_NoFail", RS_NoFail);
					h.put("Akta_Catatan", RS_Catatan);
					h.put("Akta_TarikhDaftar", RS_TarikhDaftar);					
					h.put("tagging", Utils.isNull(rs.getString(12)));
					h.put("idTagging", Utils.isNull(rs.getString(13)));
					h.put("Akta_Doc", RS_Doc);
					v.addElement(h);
				}				
			}
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
		
	}
	
	/*----------------------TAMBAH MID--------------------*/
	private static Vector list = new Vector();

	public Vector listDocTanahRizabMelayu(String tajukDokumen) throws Exception{
		 Db db = null;
		 list.clear();
		 String sql = "";	 
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_DOKUMEN,A.TAJUK_DOKUMEN,A.NO_DOKUMEN,A.TARIKH_DOKUMEN,B.KOD_SEKSYEN, A.JENIS_DOKUMEN" +
		      		" FROM TBLPDTDOKUMEN A, TBLRUJSEKSYEN B" +
		      		" WHERE A.ID_SEKSYEN = B.ID_SEKSYEN ";
		      		if(!tajukDokumen.equals(""))
		      		{
		      			sql += " AND A.TAJUK_DOKUMEN like '%"+tajukDokumen+"%'";
		      		}
		      		sql += " AND A.FLAG_DOKUMEN = 'T'";
		     
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
	
	 public static Vector getData(){
		 
		  return list;
	 }
	 
	
	
	// TAMBAH Akta BARU
	public static String aktaAdd(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			long idAkta = DB.getNextID("TBLPDTAKTA_SEQ");
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_AKTA", idAkta);
			r.add("NO_AKTA", data.get("noAkta"));
			r.add("NAMA_AKTA", data.get("namaAkta"));
			
			if (data.get("tarikhKuatkuasa") != null) {
				r.add("TARIKH_KUATKUASA", r.unquote("to_date('"+ data.get("tarikhKuatkuasa")+ "','dd/MM/yyyy')"));
			}
			
			if (data.get("tarikhMansuh") != null) {
				r.add("TARIKH_MANSUH", r.unquote("to_date('"+ data.get("tarikhMansuh")+ "','dd/MM/yyyy')"));
			}
			r.add("NO_WARTA", data.get("noWarta"));
			
			if (data.get("tarikhWarta") != null) {
				r.add("TARIKH_WARTA", r.unquote("to_date('"+ data.get("tarikhWarta")+ "','dd/MM/yyyy')"));
			}
			r.add("ID_TARAFKESELAMATAN", data.get("idKeselamatan"));
			r.add("ID_SEKSYEN", data.get("idSeksyen"));
			//r.add("ID_FAIL", data.get("idFail"));
			r.add("NO_FAIL", data.get("NoFail"));
			r.add("CATATAN", data.get("catatan"));
			if (data.get("tarikhDaftar") != null) {
				r.add("TARIKH_DAFTAR", r.unquote("to_date('"+ data.get("tarikhDaftar")+ "','dd/MM/yyyy')"));
			}
			r.add("ID_MASUK", data.get("idMasuk"));
			if (data.get("tarikhMasuk") != null) {
				r.add("TARIKH_MASUK", r.unquote("to_date('"+ data.get("tarikhMasuk")+ "','dd/MM/yyyy')"));
			}
			sql = r.getSQLInsert("TBLPDTAKTA");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
			
			String tagDokumen = (String)data.get("tagDokumen");
			if(!tagDokumen.equals("")){
				long idTagDokumen = DB.getNextID("TBLRUJTAGDOKUMEN_SEQ");
				db = new Db();
				stmt = db.getStatement();
			    r = new SQLRenderer();			      
			    r.add("ID_RUJTAG",idTagDokumen);
			    r.add("ID_DOKUMEN",idAkta);
			    r.add("TAG_DOKUMEN",tagDokumen);
				r.add("SUMBER","AKTA");
			    r.add("TARIKH_MASUK",r.unquote("sysdate")); 
			    r.add("ID_MASUK",data.get("idMasuk"));
			    sql = r.getSQLInsert("TBLRUJTAGDOKUMEN");  
			    stmt.executeUpdate(sql);

			}

			//uploadFiles();
			return String.valueOf(idAkta);
			
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
	}


	public static void aktaUpdate(Hashtable data) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_Akta", data.get("idAkta"));
			r.add("NO_Akta", data.get("noAkta"));
			r.add("NAMA_Akta", data.get("namaAkta"));
			r.add("TARIKH_KUATKUASA", r.unquote("to_date('"+data.get("tarikhKuatkusa")+"','dd/MM/yyyy')"));
			r.add("TARIKH_DAFTAR",r.unquote("to_date('"+data.get("tarikhDaftar")+"','dd/MM/yyyy')"));
			r.add("TARIKH_MANSUH", r.unquote("to_date('"+data.get("tarikhMansuh")+"','dd/MM/yyyy')"));
			r.add("ID_FAIL", data.get("idFail"));
			r.add("NO_FAIL", data.get("noFail"));
			r.add("ID_SEKSYEN", data.get("idSeksyen"));
			r.add("ID_KEMASKINI", data.get("idKemaskini"));
			r.add("TARIKH_KEMASKINI", data.get("tarikhKemaskini"));
			r.add("CATATAN", data.get("catatan"));
			r.add("NO_WARTA", data.get("noWarta"));
			r.add("TARIKH_WARTA", r.unquote("to_date('"+data.get("tarikhWarta")+"','dd/MM/yyyy')"));
			r.add("ID_TARAFKESELAMATAN", data.get("idKeselamatan"));
			sql = r.getSQLUpdate("TBLPDTAkta");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null)
				db.close();
		}
	}
	
	public static void aktaDelete(String idAkta) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_AKTA",idAkta);
			sql = r.getSQLDelete("TBLPDTAkta");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null)
				db.close();
		}

	}
	
	//////////////////////////
	@SuppressWarnings("unchecked")
	public Vector penggalList(String ID_AKTA) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(ID_AKTA)) {
				int iCount = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
	
				String RS_IDPenggal = "", RS_NoAkta = "", RS_NamaAkta = "", RS_NoPenggal = "", RS_TajukPenggal = "";
				sql = "SELECT M.ID_AKTAPENGGAL, C.NO_AKTA, C.NAMA_AKTA, M.NO_PENGGAL, M.TAJUK_PENGGAL " + 
					"FROM TBLPDTAKTAPENGGAL M, TBLPDTAKTA C " + 
					"WHERE M.ID_AKTA = C.ID_AKTA AND M.ID_AKTA = '" + ID_AKTA + "' ORDER BY M.NO_PENGGAL, M.TAJUK_PENGGAL";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDPenggal = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NoAkta = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NamaAkta = rs.getString(3) == null ? "" : rs.getString(3);
					RS_NoPenggal = rs.getString(4) == null ? "" : rs.getString(4);
					RS_TajukPenggal = rs.getString(5) == null ? "" : rs.getString(5);
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDPenggal", RS_IDPenggal);
					h.put("NoAkta", RS_NoAkta);
					h.put("NamaAkta", RS_NamaAkta);
					h.put("NoPenggal", RS_NoPenggal);
					h.put("TajukPenggal", RS_TajukPenggal);
					v.addElement(h);
					iCount++;
				}
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Vector penggalPapar(String IDPenggal) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = null;
		Hashtable h = null;
		//myLogger.debug("idPenggal:"+IDPenggal);
		try {
			v = new Vector();
			if (!"".equalsIgnoreCase(IDPenggal)) {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				
				String RS_NoPenggal = "", RS_TajukPenggal = "";
				sql = "SELECT M.NO_PENGGAL, M.TAJUK_PENGGAL " +
					"FROM TBLPDTAKTAPENGGAL M, TBLPDTAKTA C " +
					"WHERE M.ID_AKTA = C.ID_AKTA " +
					"AND M.ID_AKTAPENGGAL = " + IDPenggal;
				myLogger.info("penggalPapar : "+sql);
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_NoPenggal = rs.getString(1) == null ? "" : rs.getString(1);
					RS_TajukPenggal = rs.getString(2) == null ? "" : rs.getString(2);
					h = new Hashtable();
					h.put("Penggal_IDPenggal", IDPenggal);
					h.put("Penggal_NoPenggal", RS_NoPenggal);
					h.put("Penggal_TajukPenggal", RS_TajukPenggal);
					v.addElement(h);
				}				
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}

	// TAMBAH PENGGAL BARU
	public static void penggalAdd(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			String noPenggal = (String) data.get("noPenggal");
			String tajukPenggal = (String) data.get("tajukPenggal");

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("NO_PENGGAL", noPenggal);
			r.add("TAJUK_PENGGAL", tajukPenggal);
			r.add("ID_AKTA", data.get("idAkta"));
			r.add("ID_MASUK", data.get("idMasuk"));
			r.add("TARIKH_MASUK",r.unquote("sysdate"));

			sql = r.getSQLInsert("TBLPDTAKTAPENGGAL");

			stmt.executeUpdate(sql);
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null)
				db.close();
		}

	}
	
	//Update PENGGAL
	public static void penggalUpdate(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			String noPenggal = (String) data.get("noPenggal");
			String tajukPenggal = (String) data.get("tajukPenggal");

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("NO_PENGGAL", noPenggal);
			r.add("TAJUK_PENGGAL", tajukPenggal);
			r.add("ID_KEMASKINI", data.get("idMasuk"));
			r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			r.update("ID_AKTAPENGGAL", data.get("idAktaPenggal"));
			
			sql = r.getSQLUpdate("TBLPDTAKTAPENGGAL");
			myLogger.debug(sql);
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
	
	public static void penggalDelete(String idPenggal) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TABLE 2
			
			
			//sql = "DELETE FROM TBLPDTAKTABAHAGIAN WHERE ID_AKTAPENGGAL = ''";
			r.add("ID_AKTAPENGGAL",idPenggal);
			sql = r.getSQLDelete("TBLPDTAKTABAB");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
			
			//sql = "DELETE FROM TBLPDTAKTABAHAGIAN WHERE ID_AKTAPENGGAL = ''";
			r.add("ID_AKTAPENGGAL",idPenggal);
			sql = r.getSQLDelete("TBLPDTAKTABAHAGIAN");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
			
			//TABLE LAST
			r.clear();
			r.add("ID_AKTAPENGGAL",idPenggal);
			sql = r.getSQLDelete("TBLPDTAKTAPENGGAL");
			myLogger.debug(sql);
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
	
	@SuppressWarnings("unchecked")
	public Vector bahagianList(String ID_AKTA) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(ID_AKTA)) {
				int iCount = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
	
				String RS_IDBahagian = "", RS_NoAkta = "", RS_NamaAkta = "", RS_NoPenggal = "", RS_NoBahagian = "", RS_TajukBahagian = "";
				sql = "SELECT M.ID_AKTABAHAGIAN, A.NO_AKTA, A.NAMA_AKTA, " +
						"NVL(P.NO_PENGGAL,'-') NO_PENGGAL, M.NO_BAHAGIAN, M.TAJUK_BAHAGIAN " + 
					"FROM TBLPDTAKTABAHAGIAN M, TBLPDTAKTA A, TBLPDTAKTAPENGGAL P " + 
					"WHERE M.ID_AKTA = A.ID_AKTA AND M.ID_AKTAPENGGAL = P.ID_AKTAPENGGAL(+) " + 
					"AND M.ID_AKTA = " + ID_AKTA + " ORDER BY M.NO_BAHAGIAN, M.TAJUK_BAHAGIAN";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDBahagian = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NoAkta = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NamaAkta = rs.getString(3) == null ? "" : rs.getString(3);
					RS_NoPenggal = rs.getString(4) == null ? "" : rs.getString(4);
					RS_NoBahagian = rs.getString(5) == null ? "" : rs.getString(5);
					RS_TajukBahagian = rs.getString(6) == null ? "" : rs.getString(6);
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDBahagian", RS_IDBahagian);
					h.put("NoAkta", RS_NoAkta);
					h.put("NamaAkta", RS_NamaAkta);
					h.put("NoPenggal", RS_NoPenggal);
					h.put("NoBahagian", RS_NoBahagian);
					h.put("TajukBahagian", RS_TajukBahagian);
					v.addElement(h);
					iCount++;
				}
			}
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Vector bahagianPapar(String IDBahagian) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(IDBahagian)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				
				String RS_IDPenggal = "", RS_NoBahagian = "", RS_TajukBahagian = "";
				sql = "SELECT M.ID_AKTAPENGGAL, M.NO_BAHAGIAN, M.TAJUK_BAHAGIAN " +
					"FROM TBLPDTAKTABAHAGIAN M, TBLPDTAKTAPENGGAL C " +
					"WHERE M.ID_AKTAPENGGAL = C.ID_AKTAPENGGAL(+) " +
					"AND M.ID_AKTABAHAGIAN = " + IDBahagian;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_IDPenggal = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NoBahagian = rs.getString(2) == null ? "" : rs.getString(2);
					RS_TajukBahagian = rs.getString(3) == null ? "" : rs.getString(3);
					h = new Hashtable();
					h.put("Penggal_IDPenggal", RS_IDPenggal);
					h.put("Bahagian_NoBahagian", RS_NoBahagian);
					h.put("Bahagian_TajukBahagian", RS_TajukBahagian);
					v.addElement(h);
				}				
			}
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	public Boolean bahagianSave(String mode,String IDBahagian, String IDAkta, String IDPenggal, 
			String NoBahagian, String TajukBahagian) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = null;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_AKTA", IDAkta);
			r.add("ID_AKTAPENGGAL", IDPenggal);
			r.add("NO_BAHAGIAN", NoBahagian);
			r.add("TAJUK_BAHAGIAN", TajukBahagian);
			if ("update".equals(mode)) {
				r.update("ID_AKTABAHAGIAN", IDBahagian);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLPDTAKTABAHAGIAN");
			} else {
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPDTAKTABAHAGIAN");
			}
			myLogger.debug(sql);
			r.clear();
			returnValue = stmt.execute(sql);
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) db.close();			
		}
		
		return returnValue;
	}
	
	public Boolean bahagianUpdate(String IDBahagian, String IDAkta, String IDPenggal, String NoBahagian, String TajukBahagian) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = new Db();
		
		try {
			Boolean haveData = false;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			if (!"".equalsIgnoreCase(IDBahagian)) {
				sql = "SELECT ID_AKTABAHAGIAN FROM TBLPDTAKTABAHAGIAN WHERE " +
						"ID_AKTABAHAGIAN = " + IDBahagian;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveData = true;
				}
				rs.close();
			}
			SQLRenderer r = new SQLRenderer();
			if (haveData) {
				r.update("ID_AKTABAHAGIAN", IDBahagian);
			}
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ID_AKTA", IDAkta);
			r.add("ID_AKTAPENGGAL", IDPenggal);
			r.add("NO_BAHAGIAN", NoBahagian);
			r.add("TAJUK_BAHAGIAN", TajukBahagian);
			if (haveData) {
				sql = r.getSQLUpdate("TBLPDTAKTABAHAGIAN");
			} else {
				long ID_DB = DB.getNextID("TBLPDTAKTABAHAGIAN_SEQ");
				r.add("ID_AKTABAHAGIAN", ID_DB);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPDTAKTABAHAGIAN");
			}
			r.clear();
			returnValue = stmt.execute(sql);
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();			
		}
		
		return returnValue;
	}

	public static void bahagianDelete(String idBahagian) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_AKTABAHAGIAN",idBahagian);
			sql = r.getSQLDelete("TBLPDTAKTABAB");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
			
			r.add("ID_AKTABAHAGIAN",idBahagian);
			sql = r.getSQLDelete("TBLPDTAKTABAHAGIAN");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null)
				db.close();
		}

	}
	
	@SuppressWarnings("unchecked")
	public Vector babList(String ID_AKTA) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(ID_AKTA)) {
				int iCount = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
	
				String RS_IDBab = "", RS_NoAkta = "", RS_NamaAkta = "", RS_NoPenggal = "", RS_NoBahagian = "", RS_NoBab = "", RS_TajukBab = "";
				sql = "SELECT M.ID_AKTABAB, A.NO_AKTA, A.NAMA_AKTA, P.NO_PENGGAL, B.NO_BAHAGIAN, M.NO_BAB, M.TAJUK_BAB " + 
					"FROM TBLPDTAKTA A,TBLPDTAKTABAB M, TBLPDTAKTAPENGGAL P, TBLPDTAKTABAHAGIAN B " + 
					"WHERE A.ID_AKTA=M.ID_AKTA (+) AND M.ID_AKTAPENGGAL = P.ID_AKTAPENGGAL(+) " +
					"AND M.ID_AKTABAHAGIAN = B.ID_AKTABAHAGIAN(+) " + 
					"AND M.ID_AKTA = " + ID_AKTA + " ORDER BY M.NO_BAB, M.TAJUK_BAB";
				//myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDBab = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NoAkta = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NamaAkta = rs.getString(3) == null ? "" : rs.getString(3);
					RS_NoPenggal = rs.getString(4) == null ? "" : rs.getString(4);
					RS_NoBahagian = rs.getString(5) == null ? "" : rs.getString(5);
					RS_NoBab = rs.getString(6) == null ? "" : rs.getString(6);
					RS_TajukBab = rs.getString(7) == null ? "" : rs.getString(7);
					h = new Hashtable();
					h.put("No", iCount);
					h.put("idAktaBab",RS_IDBab);
					h.put("IDBab", RS_IDBab);
					h.put("NoAkta", RS_NoAkta);
					h.put("NamaAkta", RS_NamaAkta);
					h.put("NoPenggal", RS_NoPenggal);
					h.put("NoBahagian", RS_NoBahagian);
					h.put("NoBab", RS_NoBab);
					h.put("TajukBab", RS_TajukBab);
					v.addElement(h);
					iCount++;
				}
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Vector babPapar(String IDBab) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(IDBab)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				
				String RS_IDPenggal = "", RS_IDBahagian = "", RS_NoBab = "", RS_TajukBab = "";
				sql = "SELECT M.ID_AKTAPENGGAL, M.ID_AKTABAHAGIAN, M.NO_BAB, M.TAJUK_BAB " +
					"FROM TBLPDTAKTABAB M, TBLPDTAKTABAHAGIAN B, TBLPDTAKTAPENGGAL P " +
					"WHERE M.ID_AKTAPENGGAL = P.ID_AKTAPENGGAL(+) AND " +
					"M.ID_AKTABAHAGIAN = B.ID_AKTABAHAGIAN (+) " +
					"AND M.ID_AKTABAB = " + IDBab;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_IDPenggal = rs.getString(1) == null ? "" : rs.getString(1);
					RS_IDBahagian = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NoBab = rs.getString(3) == null ? "" : rs.getString(3);
					RS_TajukBab = rs.getString(4) == null ? "" : rs.getString(4);
					h = new Hashtable();
					h.put("Penggal_IDPenggal", RS_IDPenggal);
					h.put("Bahagian_IDBahagian", RS_IDBahagian);
					h.put("Bab_NoBab", RS_NoBab);
					h.put("Bab_TajukBab", RS_TajukBab);
					v.addElement(h);
				}				
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	public Boolean babSave(String mode,String IDBab, String IDAkta, String IDPenggal, String IDBahagian, String NoBab, String TajukBab) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = null;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_AKTA", IDAkta);
			r.add("ID_AKTAPENGGAL", IDPenggal);
			r.add("ID_AKTABAHAGIAN", IDBahagian);
			r.add("NO_BAB", NoBab);
			r.add("TAJUK_BAB", TajukBab);
			if ("update".equals(mode)) {
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.update("ID_AKTABAB", IDBab);
				sql = r.getSQLUpdate("TBLPDTAKTABAB");
			} else {
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPDTAKTABAB");
			}
			myLogger.debug("BAB:"+sql);
			r.clear();
			returnValue = stmt.execute(sql);
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}
	
	public static void babDelete(String idBab) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_AKTABAB",idBab);
			sql = r.getSQLDelete("TBLPDTAKTABAB");
			myLogger.debug(sql);
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

	@SuppressWarnings("unchecked")
	public Vector seksyenList(String ID_AKTA) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(ID_AKTA)) {
				int iCount = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
	
				String RS_IDSeksyen = "", RS_NoPenggal = "", RS_NoBahagian = "", RS_NoBab = "", RS_Seksyen = "";
				sql = "SELECT M.ID_AKTASEKSYEN, P.NO_PENGGAL, B.NO_BAHAGIAN, C.NO_BAB, " +
						"M.SEKSYEN,M.PROVISO,M.NO_SEKSYEN,M.TAJUK_SEKSYEN " + 
					"FROM TBLPDTAKTA A,TBLPDTAKTASEKSYEN M, TBLPDTAKTAPENGGAL P, " +
					"TBLPDTAKTABAHAGIAN B, TBLPDTAKTABAB C " + 
					"WHERE A.ID_AKTA = M.ID_AKTA AND M.ID_AKTAPENGGAL = P.ID_AKTAPENGGAL (+) AND " +
					"M.ID_AKTABAHAGIAN = B.ID_AKTABAHAGIAN (+) AND M.ID_AKTABAB = C.ID_AKTABAB (+)" + 
					"AND M.ID_AKTA = " + ID_AKTA + " ORDER BY M.NO_SEKSYEN, M.ID_AKTASEKSYEN";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDSeksyen = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NoPenggal = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NoBahagian = rs.getString(3) == null ? "" : rs.getString(3);
					RS_NoBab = rs.getString(4) == null ? "" : rs.getString(4);
					RS_Seksyen = rs.getString(5) == null ? "" : rs.getString(5);
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDSeksyen", RS_IDSeksyen);
					h.put("NoPenggal", RS_NoPenggal);
					h.put("NoBahagian", RS_NoBahagian);
					h.put("NoBab", RS_NoBab);
					h.put("Seksyen", RS_Seksyen);
					h.put("Proviso",Utils.isNull(rs.getString("PROVISO")));
					h.put("NoSeksyen",Utils.isNull(rs.getString("No_Seksyen")));
					h.put("TajukSeksyen",Utils.isNull(rs.getString("Tajuk_Seksyen")));
					v.addElement(h);
					iCount++;
				}
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Vector seksyenPapar(String IDSeksyen) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(IDSeksyen)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				
				String RS_IDPenggal = "", RS_IDBahagian = "", RS_IDBab = "", RS_Seksyen = "", RS_Proviso = "";
				sql = "SELECT M.ID_AKTAPENGGAL, M.ID_AKTABAHAGIAN, M.ID_AKTABAB, " +
						"M.SEKSYEN, M.PROVISO,M.NO_SEKSYEN,M.TAJUK_SEKSYEN," +
						"A.ID_AKTA " +
					"FROM TBLPDTAKTASEKSYEN M,TBLPDTAKTABAB A, TBLPDTAKTABAHAGIAN B, TBLPDTAKTAPENGGAL P " +
					"WHERE M.ID_AKTABAB = A.ID_AKTABAB(+) " +
					"AND M.ID_AKTAPENGGAL = P.ID_AKTAPENGGAL (+)" +
					"AND M.ID_AKTABAHAGIAN = B.ID_AKTABAHAGIAN (+) " +
					"AND M.ID_AKTASEKSYEN = '" + IDSeksyen +"'";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_IDPenggal = rs.getString(1) == null ? "" : rs.getString(1);
					RS_IDBahagian = rs.getString(2) == null ? "" : rs.getString(2);
					RS_IDBab = rs.getString(3) == null ? "" : rs.getString(3);
					RS_Seksyen = rs.getString(4) == null ? "" : rs.getString(4);
					RS_Proviso = rs.getString(5) == null ? "" : rs.getString(5);
					h = new Hashtable();
					h.put("id_akta",Utils.isNull(rs.getString("id_akta")));
					h.put("Penggal_IDPenggal", RS_IDPenggal);
					h.put("Bahagian_IDBahagian", RS_IDBahagian);
					h.put("Bab_IDBab", RS_IDBab);
					h.put("Seksyen_Seksyen", RS_Seksyen);
					h.put("Seksyen_Proviso", RS_Proviso);
					h.put("Seksyen_NoSeksyen", Utils.isNull(rs.getString("NO_SEKSYEN")));
					h.put("Seksyen_TajukSeksyen", Utils.isNull(rs.getString("TAJUK_SEKSYEN")));
					v.addElement(h);
				}				
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	public Boolean seksyenSave(String mode,String IDSeksyen, String IDAkta, String IDPenggal, 
			String IDBahagian, String IDBab, 
			String Seksyen, String Proviso,
			String NoSeksyen,String TajukSeksyen,String user_id) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = null;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_AKTA", IDAkta);
			r.add("ID_AKTAPENGGAL", IDPenggal);
			r.add("ID_AKTABAHAGIAN", IDBahagian);
			r.add("ID_AKTABAB", IDBab);
			r.add("SEKSYEN", Seksyen);
			r.add("PROVISO", Proviso);
			r.add("No_Seksyen", NoSeksyen);
			r.add("Tajuk_Seksyen", TajukSeksyen);
			if ("update".equals(mode)) {
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI",user_id);
				r.update("ID_AKTASEKSYEN", IDSeksyen);
				sql = r.getSQLUpdate("TBLPDTAKTASEKSYEN");
				//myLogger.debug("sql: ", sql);
			} else {
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_MASUK",user_id);
				sql = r.getSQLInsert("TBLPDTAKTASEKSYEN");
			}
			r.clear();
			returnValue = stmt.execute(sql);
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}

	public static void seksyenDelete(String idSeksyen) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_AKTASEKSYEN",idSeksyen);
			sql = r.getSQLDelete("TBLPDTAKTASEKSYEN");
			myLogger.debug(sql);
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
	
	//////////
	@SuppressWarnings("unchecked")
	public Vector subSeksyenList(String ID_AKTA) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		try {
			if (!"".equalsIgnoreCase(ID_AKTA)) {
				int iCount = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
	
				sql = "SELECT M.ID_AKTASUBSEKSYEN, P.NO_PENGGAL, B.NO_BAHAGIAN, "+
					"C.NO_BAB,C.TAJUK_BAB,  "+
					"S.NO_SEKSYEN,S.PROVISO AS SEKSYEN_PROVISO, "+ 
					"M.SUB_SEKSYEN,M.PROVISO AS SUBPROVISO,  "+
					"M.NOSUB_SEKSYEN,M.TAJUKSUB_SEKSYEN "+
					"FROM TBLPDTAKTA A,TBLPDTAKTASUBSEKSYEN M, TBLPDTAKTAPENGGAL P, TBLPDTAKTABAHAGIAN B, TBLPDTAKTABAB C, TBLPDTAKTASEKSYEN S " + 
					"WHERE A.ID_AKTA = M.ID_AKTA AND " +
					"M.ID_AKTAPENGGAL = P.ID_AKTAPENGGAL (+) AND " +
					"M.ID_AKTABAHAGIAN = B.ID_AKTABAHAGIAN (+) AND " +
					"M.ID_AKTABAB = C.ID_AKTABAB (+) " +
					"AND M.ID_AKTASEKSYEN = S.ID_AKTASEKSYEN " + 
					"AND M.ID_AKTA = " + ID_AKTA + " ORDER BY S.SEKSYEN,M.NOSUB_SEKSYEN, " +
							"M.ID_AKTASUBSEKSYEN";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
//					RS_IDSubSeksyen = rs.getString(1) == null ? "" : rs.getString(1);
//					RS_NoPenggal = rs.getString(2) == null ? "" : rs.getString(2);
//					RS_NoBahagian = rs.getString(3) == null ? "" : rs.getString(3);
//					RS_NoBab = rs.getString(4) == null ? "" : rs.getString(4);
//					RS_Seksyen = rs.getString(5) == null ? "" : rs.getString(5);
//					RS_SubSeksyen = rs.getString(6) == null ? "" : rs.getString(6);
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDSubSeksyen",Utils.isNull(rs.getString("ID_AKTASUBSEKSYEN")));
					h.put("NoPenggal", Utils.isNull(rs.getString("NO_PENGGAL")));
					h.put("NoBahagian", Utils.isNull(rs.getString("NO_BAHAGIAN")));
					h.put("NoBab", Utils.isNull(rs.getString("NO_BAB")));
					h.put("TajukBab", Utils.isNull(rs.getString("TAJUK_BAB")));
					h.put("Seksyen", Utils.isNull(rs.getString("NO_SEKSYEN")));
					h.put("SeksyenProviso", Utils.isNull(rs.getString("SEKSYEN_PROVISO")));
					h.put("SubSeksyen", Utils.isNull(rs.getString("SUB_SEKSYEN")));
					h.put("SubSeksyenProviso", Utils.isNull(rs.getString("SUBPROVISO")));
					h.put("NoSubSeksyen", Utils.isNull(rs.getString("NOSUB_SEKSYEN")));
					h.put("TajukSubSeksyen", Utils.isNull(rs.getString("TAJUKSUB_SEKSYEN")));
					v.addElement(h);
					iCount++;
				}
			}
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Vector subSeksyenPapar(String IDSubSeksyen) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(IDSubSeksyen)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				
				String RS_IDPenggal = "", RS_IDBahagian = "", RS_IDBab = "", RS_Seksyen = "", RS_SubSeksyen = "", RS_Proviso = "";
				sql = "SELECT M.ID_AKTAPENGGAL, M.ID_AKTABAHAGIAN, M.ID_AKTABAB, S.ID_AKTASEKSYEN, " +
						"M.SUB_SEKSYEN, M.PROVISO,M.NOSUB_SEKSYEN,M.TAJUKSUB_SEKSYEN " +
					"FROM TBLPDTAKTASUBSEKSYEN M, TBLPDTAKTABAHAGIAN B, TBLPDTAKTAPENGGAL P, TBLPDTAKTABAB A, TBLPDTAKTASEKSYEN S " +
					"WHERE M.ID_AKTAPENGGAL = P.ID_AKTAPENGGAL (+) " +
					"AND M.ID_AKTABAHAGIAN = B.ID_AKTABAHAGIAN (+) " +
					"AND M.ID_AKTABAB = A.ID_AKTABAB (+) " +
					"AND M.ID_AKTASEKSYEN = S.ID_AKTASEKSYEN (+) " +
					"AND M.ID_AKTASUBSEKSYEN = " + IDSubSeksyen;
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_IDPenggal = rs.getString(1) == null ? "" : rs.getString(1);
					RS_IDBahagian = rs.getString(2) == null ? "" : rs.getString(2);
					RS_IDBab = rs.getString(3) == null ? "" : rs.getString(3);
					RS_Seksyen = rs.getString(4) == null ? "" : rs.getString(4);
					RS_SubSeksyen = rs.getString(5) == null ? "" : rs.getString(5);
					RS_Proviso = rs.getString(6) == null ? "" : rs.getString(6);
					h = new Hashtable();
					h.put("Penggal_IDPenggal", RS_IDPenggal);
					h.put("Bahagian_IDBahagian", RS_IDBahagian);
					h.put("Bab_IDBab", RS_IDBab);
					h.put("Seksyen_IDSeksyen", RS_Seksyen);
					h.put("SubSeksyen_SubSeksyen", RS_SubSeksyen);
					h.put("SubSeksyen_Proviso", RS_Proviso);
					h.put("SubSeksyen_NoSubSeksyen", Utils.isNull(rs.getString("NOSUB_SEKSYEN")));
					h.put("SubSeksyen_TajukSubSeksyen", Utils.isNull(rs.getString("TAJUKSUB_SEKSYEN")));
					v.addElement(h);
				}				
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	public Boolean subSeksyenSave(String mode,String IDSubSeksyen, String IDAkta, String IDPenggal, 
			String IDBahagian, String IDBab, String IDSeksyen, 
			String SubSeksyen, String Proviso,
			String NoSubSeksyen,String TajukSubSeksyen,String user_id) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = null;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			r.add("ID_AKTA", IDAkta);
			r.add("ID_AKTAPENGGAL", IDPenggal);
			r.add("ID_AKTABAHAGIAN", IDBahagian);
			r.add("ID_AKTABAB", IDBab);
			r.add("ID_AKTASEKSYEN", IDSeksyen);
			r.add("SUB_SEKSYEN", SubSeksyen);
			r.add("PROVISO", Proviso);
			r.add("NOSUB_SEKSYEN", NoSubSeksyen);
			r.add("TAJUKSUB_SEKSYEN", TajukSubSeksyen);
			
			if ("update".equals(mode)) {
				r.update("ID_AKTASUBSEKSYEN", IDSubSeksyen);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI",user_id);
				sql = r.getSQLUpdate("TBLPDTAKTASUBSEKSYEN");
			} else {
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_MASUK",user_id);
				sql = r.getSQLInsert("TBLPDTAKTASUBSEKSYEN");
			}
			myLogger.debug(sql);
			r.clear();
			returnValue = stmt.execute(sql);
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}
	
	public static void subSeksyenDelete(String idSubSeksyen) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_AKTASUBSEKSYEN",idSubSeksyen);
			sql = r.getSQLDelete("TBLPDTAKTASUBSEKSYEN");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null)
				db.close();
		}

	}
	
	/////////
	public Boolean paraSave(String mode,String IDPara, String IDAkta, String IDPenggal, String IDBahagian, String IDBab, String IDSeksyen, String IDSubSeksyen, String Para, String Jadual) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = null;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			r.add("ID_AKTA", IDAkta);
			r.add("ID_AKTAPENGGAL", IDPenggal);
			r.add("ID_AKTABAHAGIAN", IDBahagian);
			r.add("ID_AKTABAB", IDBab);
			r.add("ID_AKTASEKSYEN", IDSeksyen);
			r.add("ID_AKTASUBSEKSYEN", IDSubSeksyen);
			r.add("PARA", Para);
			r.add("JADUAL", Jadual);
			
			if ("update".equals(mode)) {
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.update("ID_AKTAPARA", IDPara);
				sql = r.getSQLUpdate("TBLPDTAKTAPARA");
			} else {
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPDTAKTAPARA");
			}
			r.clear();
			myLogger.debug(sql);
			returnValue = stmt.execute(sql);
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public Vector paraList(String ID_AKTA) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(ID_AKTA)) {
				int iCount = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
	
				String RS_IDPara = "", RS_NoAkta = "", RS_NoPenggal = "", RS_NoBahagian = "", RS_NoBab = "", RS_Seksyen = "", RS_SubSeksyen = "", RS_Para = "";
				sql = "SELECT M.ID_AKTAPARA, A.NO_AKTA, P.NO_PENGGAL, B.NO_BAHAGIAN, C.NO_BAB, SUBSTR(S.TAJUK_SEKSYEN, 1, 50), SUBSTR(SS.TAJUKSUB_SEKSYEN, 1, 50), SUBSTR(M.PARA, 1, 50) " + 
					"FROM TBLPDTAKTAPARA M, TBLPDTAKTA A, TBLPDTAKTAPENGGAL P, TBLPDTAKTABAHAGIAN B, TBLPDTAKTABAB C, TBLPDTAKTASEKSYEN S, TBLPDTAKTASUBSEKSYEN SS " + 
					"WHERE A.ID_AKTA=M.ID_AKTA(+) " +
					"AND M.ID_AKTAPENGGAL = P.ID_AKTAPENGGAL(+) AND " +
					"M.ID_AKTABAHAGIAN = B.ID_AKTABAHAGIAN(+) AND " +
					"M.ID_AKTABAB = C.ID_AKTABAB(+) AND " +
					"M.ID_AKTASEKSYEN = S.ID_AKTASEKSYEN(+) AND " +
					"M.ID_AKTASUBSEKSYEN = SS.ID_AKTASUBSEKSYEN(+) " + 
					"AND M.ID_AKTA = " + ID_AKTA + " ORDER BY M.PARA, M.ID_AKTAPARA";
				myLogger.debug("PARALIST : "+sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDPara = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NoAkta = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NoPenggal = rs.getString(3) == null ? "" : rs.getString(3);
					RS_NoBahagian = rs.getString(4) == null ? "" : rs.getString(4);
					RS_NoBab = rs.getString(5) == null ? "" : rs.getString(5);
					RS_Seksyen = rs.getString(6) == null ? "" : rs.getString(6);
					RS_SubSeksyen = rs.getString(7) == null ? "" : rs.getString(7);
					RS_Para = rs.getString(8) == null ? "" : rs.getString(8);
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDPara", RS_IDPara);
					h.put("NoAkta", RS_NoAkta);
					h.put("NoPenggal", RS_NoPenggal);
					h.put("NoBahagian", RS_NoBahagian);
					h.put("NoBab", RS_NoBab);
					h.put("Seksyen", RS_Seksyen);
					h.put("SubSeksyen", RS_SubSeksyen);
					h.put("Para", RS_Para);
					v.addElement(h);
					iCount++;
				}
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Vector paraPapar(String IDPara) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(IDPara)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				
				String RS_IDPenggal = "", RS_IDBahagian = "", RS_IDBab = "", RS_Seksyen = "", RS_SubSeksyen = "", RS_Para = "", RS_Jadual = "";
				sql = "SELECT M.ID_AKTAPENGGAL, M.ID_AKTABAHAGIAN, M.ID_AKTABAB, S.ID_AKTASEKSYEN, SS.ID_AKTASUBSEKSYEN, M.PARA, M.JADUAL " +
					"FROM TBLPDTAKTAPARA M, TBLPDTAKTABAHAGIAN B, TBLPDTAKTAPENGGAL P, TBLPDTAKTABAB A, TBLPDTAKTASEKSYEN S, TBLPDTAKTASUBSEKSYEN SS " +
					"WHERE M.ID_AKTAPENGGAL = P.ID_AKTAPENGGAL (+) AND " +
					"M.ID_AKTABAHAGIAN = B.ID_AKTABAHAGIAN (+) AND " +
					"M.ID_AKTABAB = A.ID_AKTABAB (+) AND " +
					"M.ID_AKTASEKSYEN = S.ID_AKTASEKSYEN (+) AND " +
					"M.ID_AKTASUBSEKSYEN = SS.ID_AKTASUBSEKSYEN (+)" +
					"AND M.ID_AKTAPARA = '" + IDPara +"'";
				myLogger.debug("paraPapar : "+sql);
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_IDPenggal = rs.getString(1) == null ? "" : rs.getString(1);
					RS_IDBahagian = rs.getString(2) == null ? "" : rs.getString(2);
					RS_IDBab = rs.getString(3) == null ? "" : rs.getString(3);
					RS_Seksyen = rs.getString(4) == null ? "" : rs.getString(4);
					RS_SubSeksyen = rs.getString(5) == null ? "" : rs.getString(5);
					RS_Para = rs.getString(6) == null ? "" : rs.getString(6);
					RS_Jadual = rs.getString(7) == null ? "" : rs.getString(7);
					h = new Hashtable();
					h.put("Penggal_IDPenggal", RS_IDPenggal);
					h.put("Bahagian_IDBahagian", RS_IDBahagian);
					h.put("Bab_IDBab", RS_IDBab);
					h.put("Seksyen_IDSeksyen", RS_Seksyen);
					h.put("SubSeksyen_IDSubSeksyen", RS_SubSeksyen);
					h.put("Para_Para", RS_Para);
					h.put("Para_Jadual", RS_Jadual);
					v.addElement(h);
				}				
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	public static void paraDelete(String idPara) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_AKTAPARA",idPara);
			sql = r.getSQLDelete("TBLPDTAKTAPARA");
			myLogger.debug(sql);
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
	/////////
	public Boolean subParaSave(String mode,String IDSubPara, String IDAkta, String IDPenggal, String IDBahagian, String IDBab, String IDSeksyen, String IDSubSeksyen, String IDPara, String SubPara, String Jadual) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = null;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			SQLRenderer r = new SQLRenderer();

			r.add("ID_AKTA", IDAkta);
			r.add("ID_AKTAPENGGAL", IDPenggal);
			r.add("ID_AKTABAHAGIAN", IDBahagian);
			r.add("ID_AKTABAB", IDBab);
			r.add("ID_AKTASEKSYEN", IDSeksyen);
			r.add("ID_AKTASUBSEKSYEN", IDSubSeksyen);
			r.add("ID_AKTAPARA", IDPara);
			r.add("SUB_PARA", SubPara);
			r.add("JADUAL", Jadual);
			
			if ("update".equals(mode)) {
				r.update("ID_AKTASUBPARA", IDSubPara);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLPDTAKTASUBPARA");
			} else {
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPDTAKTASUBPARA");
			}
			r.clear();
			returnValue = stmt.execute(sql);
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public Vector subParaList(String ID_AKTA) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(ID_AKTA)) {
				int iCount = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
	
				String RS_IDSubPara = "", RS_NoAkta = "", RS_NoPenggal = "", RS_NoBahagian = "", RS_NoBab = "", RS_Seksyen = "", RS_SubSeksyen = "", RS_Para = "", RS_SubPara = "";
				sql = "SELECT M.ID_AKTASUBPARA, A.NO_AKTA, P.NO_PENGGAL, B.NO_BAHAGIAN, C.NO_BAB, SUBSTR(S.SEKSYEN, 1, 50), SUBSTR(SS.SUB_SEKSYEN, 1, 50), SUBSTR(PR.PARA, 1, 50), SUBSTR(M.SUB_PARA, 1, 50) " + 
					"FROM TBLPDTAKTASUBPARA M, TBLPDTAKTA A, TBLPDTAKTAPENGGAL P, TBLPDTAKTABAHAGIAN B, " +
					"TBLPDTAKTABAB C, TBLPDTAKTASEKSYEN S, TBLPDTAKTASUBSEKSYEN SS, TBLPDTAKTAPARA PR " + 
					"WHERE M.ID_AKTA = A.ID_AKTA (+) AND M.ID_AKTAPENGGAL = P.ID_AKTAPENGGAL (+) AND " +
					"M.ID_AKTABAHAGIAN = B.ID_AKTABAHAGIAN (+) AND M.ID_AKTABAB = C.ID_AKTABAB (+) AND " +
					"M.ID_AKTASEKSYEN = S.ID_AKTASEKSYEN (+) AND " +
					"M.ID_AKTASUBSEKSYEN = SS.ID_AKTASUBSEKSYEN (+) AND M.ID_AKTAPARA = PR.ID_AKTAPARA(+) " + 
					"AND M.ID_AKTA = '" + ID_AKTA + "' ORDER BY M.SUB_PARA, M.ID_AKTASUBPARA";
				//myLogger.debug("subParaList:"+sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDSubPara = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NoAkta = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NoPenggal = rs.getString(3) == null ? "" : rs.getString(3);
					RS_NoBahagian = rs.getString(4) == null ? "" : rs.getString(4);
					RS_NoBab = rs.getString(5) == null ? "" : rs.getString(5);
					RS_Seksyen = rs.getString(6) == null ? "" : rs.getString(6);
					RS_SubSeksyen = rs.getString(7) == null ? "" : rs.getString(7);
					RS_Para = rs.getString(8) == null ? "" : rs.getString(8);
					RS_SubPara = rs.getString(9) == null ? "" : rs.getString(9);
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDSubPara", RS_IDSubPara);
					h.put("NoAkta", RS_NoAkta);
					h.put("NoPenggal", RS_NoPenggal);
					h.put("NoBahagian", RS_NoBahagian);
					h.put("NoBab", RS_NoBab);
					h.put("Seksyen", RS_Seksyen);
					h.put("SubSeksyen", RS_SubSeksyen);
					h.put("Para", RS_Para);
					h.put("SubPara", RS_SubPara);
					v.addElement(h);
					iCount++;
				}
			}
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Vector subParaPapar(String IDSubPara) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(IDSubPara)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				
				String RS_IDPenggal = "", RS_IDBahagian = "", RS_IDBab = "", RS_Seksyen = "", RS_SubSeksyen = "", RS_Para = "", RS_SubPara = "", RS_Jadual = "";
				sql = "SELECT M.ID_AKTAPENGGAL, M.ID_AKTABAHAGIAN, M.ID_AKTABAB, S.ID_AKTASEKSYEN, SS.ID_AKTASUBSEKSYEN, PR.ID_AKTAPARA, M.SUB_PARA, M.JADUAL " +
					"FROM TBLPDTAKTASUBPARA M, TBLPDTAKTABAHAGIAN B, TBLPDTAKTAPENGGAL P, TBLPDTAKTABAB A, TBLPDTAKTASEKSYEN S, TBLPDTAKTASUBSEKSYEN SS, TBLPDTAKTAPARA PR " +
					"WHERE M.ID_AKTAPENGGAL = P.ID_AKTAPENGGAL AND M.ID_AKTABAHAGIAN = B.ID_AKTABAHAGIAN AND M.ID_AKTABAB = A.ID_AKTABAB AND M.ID_AKTASEKSYEN = S.ID_AKTASEKSYEN AND M.ID_AKTASUBSEKSYEN = SS.ID_AKTASUBSEKSYEN AND M.ID_AKTAPARA = PR.ID_AKTAPARA " +
					"AND M.ID_AKTASUBPARA = " + IDSubPara;
				myLogger.info(" subParaPapar : "+sql);
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_IDPenggal = rs.getString(1) == null ? "" : rs.getString(1);
					RS_IDBahagian = rs.getString(2) == null ? "" : rs.getString(2);
					RS_IDBab = rs.getString(3) == null ? "" : rs.getString(3);
					RS_Seksyen = rs.getString(4) == null ? "" : rs.getString(4);
					RS_SubSeksyen = rs.getString(5) == null ? "" : rs.getString(5);
					RS_Para = rs.getString(6) == null ? "" : rs.getString(6);
					RS_SubPara = rs.getString(7) == null ? "" : rs.getString(7);
					RS_Jadual = rs.getString(8) == null ? "" : rs.getString(8);
					h = new Hashtable();
					h.put("Penggal_IDPenggal", RS_IDPenggal);
					h.put("Bahagian_IDBahagian", RS_IDBahagian);
					h.put("Bab_IDBab", RS_IDBab);
					h.put("Seksyen_IDSeksyen", RS_Seksyen);
					h.put("SubSeksyen_IDSubSeksyen", RS_SubSeksyen);
					h.put("Para_IDPara", RS_Para);
					h.put("SubPara_SubPara", RS_SubPara);
					h.put("SubPara_Jadual", RS_Jadual);
					v.addElement(h);
				}				
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	public static void subParaDelete(String idSubPara) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_AKTASUBPARA",idSubPara);
			sql = r.getSQLDelete("TBLPDTAKTASUBPARA");
			myLogger.debug(sql);
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
	public String getSOCPenggal(String SOC_NAME) throws Exception {
		return getSOCPenggal(SOC_NAME, "", "", "", "");
	}
	public String getSOCPenggal(String SOC_NAME, String SELECTED_PENGGAL) throws Exception {
		return getSOCPenggal(SOC_NAME, SELECTED_PENGGAL, "", "", "");
	}
	public String getSOCPenggal(String SOC_NAME, String SELECTED_PENGGAL, String ID_AKTA) throws Exception {
		return getSOCPenggal(SOC_NAME, SELECTED_PENGGAL, ID_AKTA, "", "");
	}
	public String getSOCPenggal(String SOC_NAME, String SELECTED_PENGGAL, String ID_AKTA, String DISABLED) throws Exception {
		return getSOCPenggal(SOC_NAME, SELECTED_PENGGAL, ID_AKTA, DISABLED, "");
	}
	public String getSOCPenggal(String SOC_NAME, String SELECTED_PENGGAL, String ID_AKTA, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		myLogger.info("DISABLED === "+ DISABLED);
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(ID_AKTA)) {
				SQL_SEARCH += "WHERE ID_AKTA = " + ID_AKTA + " ";
			}
			returnValue += "<select name='" + SOC_NAME + "'";
			if (DISABLED != null)
				returnValue += " " + DISABLED;
			if (ONCHANGE != null)
				returnValue += " " + ONCHANGE;
			returnValue += " > ";
//			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_AKTAPENGGAL = "", NO_PENGGAL = "", TAJUK_PENGGAL = "";
			sql = "SELECT ID_AKTAPENGGAL, NO_PENGGAL, SUBSTR(TAJUK_PENGGAL, 1, 20) FROM TBLPDTAKTAPENGGAL " +
				SQL_SEARCH + "ORDER BY NO_PENGGAL, TAJUK_PENGGAL";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_AKTAPENGGAL = rs.getString(1) == null ? "" : rs.getString(1);
				NO_PENGGAL = rs.getString(2) == null ? "" : rs.getString(2);
				TAJUK_PENGGAL = rs.getString(3) == null ? "" : rs.getString(3);
				if (!"".equalsIgnoreCase(ID_AKTAPENGGAL)) {
					if (SELECTED_PENGGAL.equalsIgnoreCase(ID_AKTAPENGGAL)) {
						returnValue += "  <option value=\"" + ID_AKTAPENGGAL + "\" selected=\"selected\">" + NO_PENGGAL + " - " + TAJUK_PENGGAL + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_AKTAPENGGAL + "\">" + NO_PENGGAL + " - " + TAJUK_PENGGAL + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
			myLogger.info("returnValue: "+ returnValue);
			
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String getSOCBahagian(String SOC_NAME) throws Exception {
		return getSOCBahagian(SOC_NAME, "", "", "", "");
	}
	public String getSOCBahagian(String SOC_NAME, String SELECTED_BAHAGIAN) throws Exception {
		return getSOCBahagian(SOC_NAME, SELECTED_BAHAGIAN, "", "", "");
	}
	public String getSOCBahagian(String SOC_NAME, String SELECTED_BAHAGIAN, String ID_PENGGAL) throws Exception {
		return getSOCBahagian(SOC_NAME, SELECTED_BAHAGIAN, ID_PENGGAL, "", "");
	}
	public String getSOCBahagian(String SOC_NAME, String SELECTED_BAHAGIAN, String ID_PENGGAL, String DISABLED) throws Exception {
		return getSOCBahagian(SOC_NAME, SELECTED_BAHAGIAN, ID_PENGGAL, DISABLED, "");
	}
	public String getSOCBahagian(String SOC_NAME, String SELECTED_BAHAGIAN, 
			String ID_Akta, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(ID_Akta)) {
				SQL_SEARCH = "WHERE ID_AKTA = '" + ID_Akta + "' ";
			}
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_AKTABAHAGIAN = "", NO_BAHAGIAN = "", TAJUK_BAHAGIAN = "";
			sql = "SELECT ID_AKTABAHAGIAN, NO_BAHAGIAN, SUBSTR(TAJUK_BAHAGIAN, 1, 20) " +
					"FROM TBLPDTAKTABAHAGIAN " +
				SQL_SEARCH + "ORDER BY NO_BAHAGIAN, TAJUK_BAHAGIAN";
			myLogger.debug("SELECTED_BAHAGIAN:"+SELECTED_BAHAGIAN);
			myLogger.debug("soc bahagiain:"+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_AKTABAHAGIAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_BAHAGIAN = rs.getString(2) == null ? "" : rs.getString(2);
				TAJUK_BAHAGIAN = rs.getString(3) == null ? "" : rs.getString(3);
				if (!"".equalsIgnoreCase(ID_AKTABAHAGIAN)) {
					if (SELECTED_BAHAGIAN.equalsIgnoreCase(ID_AKTABAHAGIAN)) {
						returnValue += "  <option value=\"" + ID_AKTABAHAGIAN + "\" selected=\"selected\">" + NO_BAHAGIAN + " - " + TAJUK_BAHAGIAN + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_AKTABAHAGIAN + "\">" + NO_BAHAGIAN + " - " + TAJUK_BAHAGIAN + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String getSOCBab(String SOC_NAME) throws Exception {
		return getSOCBab(SOC_NAME, "", "", "", "");
	}
	public String getSOCBab(String SOC_NAME, String SELECTED_BAB) throws Exception {
		return getSOCBab(SOC_NAME, SELECTED_BAB, "", "", "");
	}
	public String getSOCBab(String SOC_NAME, String SELECTED_BAB, String ID_BAHAGIAN) throws Exception {
		return getSOCBab(SOC_NAME, SELECTED_BAB, ID_BAHAGIAN, "", "");
	}
	public String getSOCBab(String SOC_NAME, String SELECTED_BAB, String ID_BAHAGIAN, String DISABLED) throws Exception {
		return getSOCBab(SOC_NAME, SELECTED_BAB, ID_BAHAGIAN, DISABLED, "");
	}
	public String getSOCBab(String SOC_NAME, String SELECTED_BAB, String ID_AKTA, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_AKTABAB = "", NO_BAB = "", TAJUK_BAB = "";
			sql = "SELECT ID_AKTABAB, NO_BAB, SUBSTR(TAJUK_BAB, 1, 20) FROM TBLPDTAKTABAB " +
				  "WHERE ID_AKTA='"+ID_AKTA+"' ORDER BY NO_BAB, TAJUK_BAB";
			myLogger.debug("SOC BAB:"+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_AKTABAB = rs.getString(1) == null ? "" : rs.getString(1);
				NO_BAB = rs.getString(2) == null ? "" : rs.getString(2);
				TAJUK_BAB = rs.getString(3) == null ? "" : rs.getString(3);
				if (!"".equalsIgnoreCase(ID_AKTABAB)) {
					if (SELECTED_BAB.equalsIgnoreCase(ID_AKTABAB)) {
						returnValue += "  <option value=\"" + ID_AKTABAB + "\" selected=\"selected\">" + NO_BAB + " - " + TAJUK_BAB + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_AKTABAB + "\">" + NO_BAB + " - " + TAJUK_BAB + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String getSOCSeksyen(String SOC_NAME) throws Exception {
		return getSOCSeksyen(SOC_NAME, "", "", "", "");
	}
	public String getSOCSeksyen(String SOC_NAME, String SELECTED_SEKSYEN) throws Exception {
		return getSOCSeksyen(SOC_NAME, SELECTED_SEKSYEN, "", "", "");
	}
	public String getSOCSeksyen(String SOC_NAME, String SELECTED_SEKSYEN, String ID_BAB) throws Exception {
		return getSOCSeksyen(SOC_NAME, SELECTED_SEKSYEN, ID_BAB, "", "");
	}
	public String getSOCSeksyen(String SOC_NAME, String SELECTED_SEKSYEN, String ID_BAB, String DISABLED) throws Exception {
		return getSOCSeksyen(SOC_NAME, SELECTED_SEKSYEN, ID_BAB, DISABLED, "");
	}
	public String getSOCSeksyen(String SOC_NAME, String SELECTED_SEKSYEN, String ID_AKTA, 
			String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			//if (!"".equalsIgnoreCase(ID_BAB)) {
				SQL_SEARCH = "WHERE ID_AKTA = '" + ID_AKTA + "' ";
			//}
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_AKTASEKSYEN = "", SEKSYEN = "";
			sql = "SELECT ID_AKTASEKSYEN, SUBSTR(TAJUK_SEKSYEN, 1, 20) FROM TBLPDTAKTASEKSYEN " +
				SQL_SEARCH + "ORDER BY NO_SEKSYEN, ID_AKTASEKSYEN";
			//myLogger.debug(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_AKTASEKSYEN = rs.getString(1) == null ? "" : rs.getString(1);
				SEKSYEN = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_AKTASEKSYEN)) {
					if (SELECTED_SEKSYEN.equalsIgnoreCase(ID_AKTASEKSYEN)) {
						returnValue += "  <option value=\"" + ID_AKTASEKSYEN + "\" selected=\"selected\">" + SEKSYEN + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_AKTASEKSYEN + "\">" + SEKSYEN + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String getSOCSubSeksyen(String SOC_NAME) throws Exception {
		return getSOCSubSeksyen(SOC_NAME, "", "", "", "");
	}
	public String getSOCSubSeksyen(String SOC_NAME, String SELECTED_SUBSEKSYEN) throws Exception {
		return getSOCSubSeksyen(SOC_NAME, SELECTED_SUBSEKSYEN, "", "", "");
	}
	public String getSOCSubSeksyen(String SOC_NAME, String SELECTED_SUBSEKSYEN, String ID_SEKSYEN) throws Exception {
		return getSOCSubSeksyen(SOC_NAME, SELECTED_SUBSEKSYEN, ID_SEKSYEN, "", "");
	}
	public String getSOCSubSeksyen(String SOC_NAME, String SELECTED_SUBSEKSYEN, String ID_SEKSYEN, String DISABLED) throws Exception {
		return getSOCSubSeksyen(SOC_NAME, SELECTED_SUBSEKSYEN, ID_SEKSYEN, DISABLED, "");
	}
	public String getSOCSubSeksyen(String SOC_NAME, String SELECTED_SUBSEKSYEN, String ID_SEKSYEN, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			//if (!"".equalsIgnoreCase(ID_SEKSYEN)) {
				SQL_SEARCH = "WHERE ID_AKTASEKSYEN = '" + ID_SEKSYEN + "' ";
			//}
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_AKTASUBSEKSYEN = "", SUBSEKSYEN = "";
			sql = "SELECT ID_AKTASUBSEKSYEN, SUBSTR(TAJUKSUB_SEKSYEN, 1, 20) FROM TBLPDTAKTASUBSEKSYEN " +
				SQL_SEARCH + "ORDER BY SUB_SEKSYEN, ID_AKTASUBSEKSYEN";
			//myLogger.debug(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_AKTASUBSEKSYEN = rs.getString(1) == null ? "" : rs.getString(1);
				SUBSEKSYEN = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_AKTASUBSEKSYEN)) {
					if (SELECTED_SUBSEKSYEN.equalsIgnoreCase(ID_AKTASUBSEKSYEN)) {
						returnValue += "  <option value=\"" + ID_AKTASUBSEKSYEN + "\" selected=\"selected\">" + SUBSEKSYEN + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_AKTASUBSEKSYEN + "\">" + SUBSEKSYEN + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String getSOCPara(String SOC_NAME) throws Exception {
		return getSOCPara(SOC_NAME, "", "", "", "");
	}
	public String getSOCPara(String SOC_NAME, String SELECTED_PARA) throws Exception {
		return getSOCPara(SOC_NAME, SELECTED_PARA, "", "", "");
	}
	public String getSOCPara(String SOC_NAME, String SELECTED_PARA, String ID_SUBSEKSYEN) throws Exception {
		return getSOCPara(SOC_NAME, SELECTED_PARA, ID_SUBSEKSYEN, "", "");
	}
	public String getSOCPara(String SOC_NAME, String SELECTED_PARA, String ID_SUBSEKSYEN, String DISABLED) throws Exception {
		return getSOCPara(SOC_NAME, SELECTED_PARA, ID_SUBSEKSYEN, DISABLED, "");
	}
	public String getSOCPara(String SOC_NAME, String SELECTED_PARA, String ID_AKTA, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
				SQL_SEARCH = "WHERE ID_AKTA = '" + ID_AKTA + "' ";
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_AKTAPARA = "", PARA = "";
			sql = "SELECT ID_AKTAPARA, SUBSTR(PARA, 1, 20) FROM TBLPDTAKTAPARA " +
				SQL_SEARCH + "ORDER BY PARA, ID_AKTAPARA";
			myLogger.debug(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_AKTAPARA = rs.getString(1) == null ? "" : rs.getString(1);
				PARA = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_AKTAPARA)) {
					if (SELECTED_PARA.equalsIgnoreCase(ID_AKTAPARA)) {
						returnValue += "  <option value=\"" + ID_AKTAPARA + "\" selected=\"selected\">" + PARA + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_AKTAPARA + "\">" + PARA + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	/////// JADUAL
	public String JadualParaSave(String mode,String IDJadual,String IDAkta,
			String NamaJadual,String TajukJadual,String CatatanJadual,String user_id) throws Exception {
		
		Boolean returnValue = false;
		String sql = "";
		Db db = null;
		long idJadual = 0;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			SQLRenderer r = new SQLRenderer();

			r.add("NAMA_JADUAL", NamaJadual);
			r.add("TAJUK",TajukJadual);
			r.add("CATATAN",CatatanJadual);
			
			r.add("ID_AKTA", IDAkta);
			
			if ("update".equals(mode)) {
				r.update("ID_JADUAL", IDJadual);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI",user_id);
				sql = r.getSQLUpdate("TBLPDTJADUAL");
			} else {
				idJadual = DB.getNextID("TBLPDTJADUAL_SEQ");
				r.add("ID_JADUAL",idJadual);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_MASUK",user_id);
				sql = r.getSQLInsert("TBLPDTJADUAL");
			}
			myLogger.debug("SQL Jadual:"+sql);
			r.clear();
			returnValue = stmt.execute(sql);
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)	db.close();
		}
		return String.valueOf(idJadual);
		//return returnValue;
	}
	
	
	public Vector JadualList(String ID_AKTA) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(ID_AKTA)) {
				int iCount = 1;
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
	
				String RS_IDJadual="",RS_NamaJadual = "",RS_NoAkta;
				sql = "select a.id_jadual,a.nama_jadual,b.no_akta," +
						//"a.tajuk,substr(a.catatan,0,40)||'...' as catatan "+
						"a.tajuk,a.catatan "+
					  "FROM tblpdtjadual a,tblpdtakta b "+
					  "where a.id_akta=b.id_akta " +
					  "AND a.ID_AKTA = '" + ID_AKTA + "' ORDER BY a.TARIKH_MASUK";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDJadual = Utils.isNull(rs.getString(1));
					RS_NamaJadual = Utils.isNull(rs.getString(2));
					RS_NoAkta = Utils.isNull(rs.getString(3));
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDJadual", RS_IDJadual);
					h.put("NamaJadual", RS_NamaJadual);
					h.put("NoAkta", RS_NoAkta);
					h.put("TajukJadual", Utils.isNull(rs.getString("tajuk")));
					h.put("CatatanJadual", Utils.isNull(rs.getString("catatan")));
					
					v.addElement(h);
					iCount++;
				}
			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	public void jadualDelete(String idJadual) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_JADUAL",idJadual);
			sql = r.getSQLDelete("TBLPDTJADUAL");
			myLogger.debug(sql);
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
	
	
	public Hashtable JadualPapar(String idJadual) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable h = null;
		try {
			sql = "Select Id_Jadual,Nama_Jadual,Tajuk,Catatan " +
					"FROM TBLPDTJADUAL WHERE id_jadual='"+idJadual+"'";
			myLogger.debug(sql);
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;			
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				h = new Hashtable();
				h.put("IDJadual",Utils.isNull(rs.getString("Id_Jadual")));
				h.put("NamaJadual",Utils.isNull(rs.getString("Nama_Jadual")));
				h.put("TajukJadual",Utils.isNull(rs.getString("Tajuk")));
				h.put("CatatanJadual",Utils.isNull(rs.getString("Catatan")));
			}
			
		} catch (Exception e) {
			
		}
		finally {if (db != null) db.close();}
		return h;
	}

	
	public String getSOCJadual(String SOC_NAME, String SELECTED_JADUAL, String ID_AKTA,
			String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
				SQL_SEARCH = "WHERE ID_AKTA = '" + ID_AKTA + "' ";
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_AKTAPARA = "", PARA = "";
			sql = "SELECT ID_JADUAL, SUBSTR(NAMA_JADUAL, 1, 20) FROM TBLPDTJADUAL " +
				SQL_SEARCH + "ORDER BY ID_JADUAL";
			myLogger.debug(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_AKTAPARA = rs.getString(1) == null ? "" : rs.getString(1);
				PARA = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_AKTAPARA)) {
					//if (SELECTED_JADUAL.equalsIgnoreCase(ID_AKTAPARA)) {
					if (ID_AKTAPARA.equalsIgnoreCase(SELECTED_JADUAL)) {	
						returnValue += "  <option value=\"" + ID_AKTAPARA + "\" selected=\"selected\">" + PARA + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_AKTAPARA + "\">" + PARA + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}	
	
/////////////////////////////////
/////// JADUAL
	public String JadualLampiranSave(String mode,String IDJadualLampiran,String IDJadual,
			String Tajuk,String Catatan,String user_id) throws Exception {
		
		Boolean returnValue = false;
		String sql = "";
		Db db = null;
		long idJadualLampiran = 0;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			SQLRenderer r = new SQLRenderer();
			r.add("TAJUK", Tajuk);
			r.add("CATATAN", Catatan);
			r.add("ID_Jadual", IDJadual);
			
			if ("update".equals(mode)) {
				r.update("ID_JADUALLAMPIRAN", IDJadualLampiran);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI",user_id);
				sql = r.getSQLUpdate("TBLPDTJADUALLAMPIRAN");
			} else {
				idJadualLampiran = DB.getNextID("TBLPDTJADUALLAMPIRAN_SEQ");
				r.add("ID_JADUALLAMPIRAN",idJadualLampiran);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_MASUK",user_id);
				sql = r.getSQLInsert("TBLPDTJADUALLAMPIRAN");
			}
			myLogger.debug("SQL JadualLampiran:"+sql);
			r.clear();
			returnValue = stmt.execute(sql);
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null)	db.close();
		}
		return String.valueOf(idJadualLampiran);
		//return returnValue;
	}
	
	
	public Vector JadualLampiranList(String ID_AKTA) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
			if (!"".equalsIgnoreCase(ID_AKTA)) {
				int iCount = 1;
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
	
				String RS_IDJadualLampiran="",RS_NamaJadualLampiran = "",RS_NoAkta;
				sql = "select b.id_jaduallampiran,a.nama_jadual,c.no_akta," +
						//"b.nama_lampiran," +
						"b.tajuk,b.catatan from " +
						"tblpdtjadual a,tblpdtjaduallampiran b,tblpdtakta c " +
						"where a.id_jadual = b.id_jadual "+
						"AND a.id_akta = c.id_akta "+
						"AND a.ID_AKTA = '" + ID_AKTA + "' ORDER BY a.TARIKH_MASUK,a.nama_jadual asc";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDJadualLampiran = Utils.isNull(rs.getString("id_jaduallampiran"));
					RS_NamaJadualLampiran = Utils.isNull(rs.getString("nama_jadual"));
					RS_NoAkta = Utils.isNull(rs.getString("no_akta"));
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDJadualLampiran", RS_IDJadualLampiran);
					h.put("NamaJadualLampiran", RS_NamaJadualLampiran);
					h.put("NoAkta", RS_NoAkta);
					//h.put("NamaLampiran",Utils.isNull(rs.getString(4)));
					h.put("Tajuk",Utils.isNull(rs.getString("tajuk")));
					h.put("Catatan",Utils.isNull(rs.getString("catatan")));
					v.addElement(h);
					iCount++;
				}
			}
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	public void jadualLampiranDelete(String idJadualLampiran) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_JADUALLAMPIRAN",idJadualLampiran);
			sql = r.getSQLDelete("TBLPDTJADUALLAMPIRAN");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
			
			r.clear();
			r.add("ID_JADUALLAMPIRAN",idJadualLampiran);
			sql = r.getSQLDelete("tblpdtjaduallampiran_fail");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
			/// DELETE LAMPIRAN FAIL
			
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Hashtable JadualLampiranPapar(String IDJadualLampiran) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable h = null;
		try {
			sql = "Select Id_JadualLampiran,ID_Jadual," +
					//"substr(Catatan,0,20) as Catatan,Tajuk FROM TBLPDTJADUALLAMPIRAN " +
					"Catatan,Tajuk FROM TBLPDTJADUALLAMPIRAN " +
					"WHERE id_jaduallampiran='"+IDJadualLampiran+"' ORDER BY TARIKH_MASUK";
			myLogger.debug(sql);
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;			
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				h = new Hashtable();
				h.put("IDJadual",Utils.isNull(rs.getString("Id_Jadual")));
				h.put("IDJadualLampiran",Utils.isNull(rs.getString("Id_JadualLampiran")));
				h.put("Catatan",Utils.isNull(rs.getString("Catatan")));
				h.put("Tajuk",Utils.isNull(rs.getString("Tajuk")));
			}
			
		} catch (Exception e) {
			
		}
		finally {if (db != null) db.close();}
		return h;
	}

	
	public Vector JadualLampiranFailList(String IDJadualLampiran) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = new Vector();
		Hashtable h = null;
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
					sql = "select id_jaduallampiran_fail,nama_lampiran from " +
							"tblpdtjaduallampiran_fail where " +
						  "id_jaduallampiran = '"+IDJadualLampiran+"' ORDER BY TARIKH_MASUK";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					h = new Hashtable();
					h.put("IDJadualLampiran", IDJadualLampiran);
					h.put("IDJadualLampiranFail",Utils.isNull(rs.getString("id_jaduallampiran_fail")));
					h.put("NamaLampiran", Utils.isNull(rs.getString("nama_lampiran")));
					v.addElement(h);
				}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	public void jadualLampiranFailDelete(String idJadualLampiranFail) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_JADUALLAMPIRAN_FAIL",idJadualLampiranFail);
			sql = r.getSQLDelete("tblpdtjaduallampiran_fail");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
			/// DELETE LAMPIRAN FAIL
			
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