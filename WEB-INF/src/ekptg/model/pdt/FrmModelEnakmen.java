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

public class FrmModelEnakmen {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static Logger myLogger = Logger.getLogger(FrmModelEnakmen.class);
	@SuppressWarnings("unchecked")
	public Vector enakmenCarian(String NoEnakmen, String NamaEnakmen, String TarikhKuatkuasa, String ID_Seksyen) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			String SQL_SEARCH = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			if (!"".equalsIgnoreCase(NoEnakmen)) {
				SQL_SEARCH += "AND UPPER(M.NO_ENAKMEN) LIKE '%" + NoEnakmen.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(NamaEnakmen)) {
				SQL_SEARCH += "AND UPPER(M.NAMA_ENAKMEN) LIKE '%" + NamaEnakmen.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(TarikhKuatkuasa)) {
				SQL_SEARCH += "AND TO_CHAR(M.TARIKH_KUATKUASA, 'dd/MM/yyyy') = '" + TarikhKuatkuasa + "' ";
			}
			if (!"".equalsIgnoreCase(ID_Seksyen)) {
				SQL_SEARCH += "AND M.ID_SEKSYEN = '" + ID_Seksyen + "' ";
			}

			int iCount = 1;
			String RS_IDEnakmen = "", RS_NoEnakmen = "", RS_NamaEnakmen = "", RS_TarikhKuatkuasa = "", RS_NamaSeksyen = "";
			sql = "SELECT M.ID_ENAKMEN, M.NO_ENAKMEN, M.NAMA_ENAKMEN," +
					" M.TARIKH_KUATKUASA, C.NAMA_SEKSYEN " +
				"FROM TBLPDTENAKMEN M, TBLRUJSEKSYEN C " +
				"WHERE M.ID_SEKSYEN = C.ID_SEKSYEN(+) " +
				SQL_SEARCH;
			myLogger.info("test :: "+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RS_IDEnakmen = rs.getString(1) == null ? "" : rs.getString(1);
				RS_NoEnakmen = rs.getString(2) == null ? "" : rs.getString(2);
				RS_NamaEnakmen = rs.getString(3) == null ? "" : rs.getString(3);
				RS_TarikhKuatkuasa = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				RS_NamaSeksyen = rs.getString(5) == null ? "" : rs.getString(5);
				h = new Hashtable();
				h.put("No", iCount);
				h.put("IDEnakmen", RS_IDEnakmen);
				h.put("NoEnakmen", RS_NoEnakmen);
				h.put("NamaEnakmen", RS_NamaEnakmen);
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
	public Vector enakmenCarian(String NoEnakmen, String NamaEnakmen
			, String TarikhKuatkuasa, String ID_Seksyen, String tag) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			String SQL_SEARCH = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			if (!"".equalsIgnoreCase(NoEnakmen)) {
				SQL_SEARCH += "AND UPPER(M.NO_ENAKMEN) LIKE '%" + NoEnakmen.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(NamaEnakmen)) {
				SQL_SEARCH += "AND UPPER(M.NAMA_ENAKMEN) LIKE '%" + NamaEnakmen.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(TarikhKuatkuasa)) {
				SQL_SEARCH += "AND TO_CHAR(M.TARIKH_KUATKUASA, 'dd/MM/yyyy') = '" + TarikhKuatkuasa + "' ";
			}
			if (!"".equalsIgnoreCase(ID_Seksyen)) {
				SQL_SEARCH += "AND M.ID_SEKSYEN = '" + ID_Seksyen + "' ";
			}
			if (!"".equalsIgnoreCase(tag)) {
				SQL_SEARCH += " AND M.ID_ENAKMEN = ( " +
						" SELECT ID_DOKUMEN FROM TBLRUJTAGDOKUMEN " +
						" WHERE SUMBER ='ENAKMEN' " +
						" AND TAG_DOKUMEN LIKE '%" + tag + "%' )";
			}

			int iCount = 1;
			String RS_IDEnakmen = "", RS_NoEnakmen = "", RS_NamaEnakmen = "", RS_TarikhKuatkuasa = "", RS_NamaSeksyen = "";
			sql = "SELECT M.ID_ENAKMEN, M.NO_ENAKMEN, M.NAMA_ENAKMEN," +
					" M.TARIKH_KUATKUASA, C.NAMA_SEKSYEN " +
				"FROM TBLPDTENAKMEN M, TBLRUJSEKSYEN C " +
				"WHERE M.ID_SEKSYEN = C.ID_SEKSYEN(+) " +
				SQL_SEARCH;
			myLogger.info("test :: "+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RS_IDEnakmen = rs.getString(1) == null ? "" : rs.getString(1);
				RS_NoEnakmen = rs.getString(2) == null ? "" : rs.getString(2);
				RS_NamaEnakmen = rs.getString(3) == null ? "" : rs.getString(3);
				RS_TarikhKuatkuasa = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				RS_NamaSeksyen = rs.getString(5) == null ? "" : rs.getString(5);
				h = new Hashtable();
				h.put("No", iCount);
				h.put("IDEnakmen", RS_IDEnakmen);
				h.put("NoEnakmen", RS_NoEnakmen);
				h.put("NamaEnakmen", RS_NamaEnakmen);
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
	public Vector enakmenPaparFail(String IDEnakmen) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			if (!"".equalsIgnoreCase(IDEnakmen)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				String RS_NoEnakmen = "", RS_NamaEnakmen = "", RS_NoFail = "";
				sql = "SELECT M.NO_ENAKMEN, M.NAMA_ENAKMEN, F.NO_FAIL " +
					"FROM TBLPDTENAKMEN M, TBLRUJSEKSYEN C, TBLPFDFAIL F " +
					"WHERE M.ID_SEKSYEN = C.ID_SEKSYEN(+) AND M.ID_FAIL = F.ID_FAIL " +
					"AND M.ID_ENAKMEN = " + IDEnakmen;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_NoEnakmen = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NamaEnakmen = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NoFail = rs.getString(3) == null ? "" : rs.getString(3);
					h = new Hashtable();
					h.put("Enakmen_IDEnakmen", IDEnakmen);
					h.put("Enakmen_NoEnakmen", RS_NoEnakmen);
					h.put("Enakmen_NamaEnakmen", RS_NamaEnakmen);
					h.put("Enakmen_NoFail", RS_NoFail);
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
	public Vector enakmenPapar(String IDEnakmen) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			if (!"".equalsIgnoreCase(IDEnakmen)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				String RS_NoEnakmen = "", RS_NamaEnakmen = "", RS_TarikhKuatkuasa = "", RS_TarikhMansuh = "", RS_NoWarta = "", RS_TarikhWarta = "", RS_TarafKeselamatan = "", RS_IDSeksyen = "", RS_NoFail = "", RS_Catatan = "", RS_TarikhDaftar = "";
				sql = "SELECT M.NO_ENAKMEN, M.NAMA_ENAKMEN, M.TARIKH_KUATKUASA, M.TARIKH_MANSUH, M.NO_WARTA, M.TARIKH_WARTA, M.ID_TARAFKESELAMATAN, C.ID_SEKSYEN, " +
					" M.NO_FAIL, M.CATATAN, M.TARIKH_DAFTAR " +
					" ,NVL((SELECT TAG_DOKUMEN FROM TBLRUJTAGDOKUMEN WHERE ID_DOKUMEN=M.ID_ENAKMEN AND SUMBER ='ENAKMEN' ),'') TAG_DOKUMEN " +
					" ,NVL((SELECT ID_RUJTAG FROM TBLRUJTAGDOKUMEN WHERE ID_DOKUMEN=M.ID_ENAKMEN AND SUMBER ='ENAKMEN'),'0') ID_DOKUMEN " +
					" FROM TBLPDTENAKMEN M, TBLRUJSEKSYEN C, TBLPFDFAIL F " +
					" WHERE M.ID_SEKSYEN = C.ID_SEKSYEN(+) AND M.ID_FAIL = F.ID_FAIL(+) " +
					" AND M.ID_ENAKMEN = " + IDEnakmen;
				//myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_NoEnakmen = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NamaEnakmen = rs.getString(2) == null ? "" : rs.getString(2);
					RS_TarikhKuatkuasa = rs.getDate(3) == null ? "" : sdf.format(rs.getDate(3));
					RS_TarikhMansuh = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
					RS_NoWarta = rs.getString(5) == null ? "" : rs.getString(5);
					RS_TarikhWarta = rs.getDate(6) == null ? "" : sdf.format(rs.getDate(6));
					RS_TarafKeselamatan = rs.getString(7) == null ? "" : rs.getString(7);
					RS_IDSeksyen = rs.getString(8) == null ? "" : rs.getString(8);
					RS_NoFail = rs.getString(9) == null ? "" : rs.getString(9);
					RS_Catatan = rs.getString(10) == null ? "" : rs.getString(10);
					RS_TarikhDaftar = rs.getDate(11) == null ? "" : sdf.format(rs.getDate(11));
					h = new Hashtable();
					h.put("Enakmen_IDEnakmen", IDEnakmen);
					h.put("Enakmen_NoEnakmen", RS_NoEnakmen);
					h.put("Enakmen_NamaEnakmen", RS_NamaEnakmen);
					h.put("Enakmen_TarikhKuatkuasa", RS_TarikhKuatkuasa);
					h.put("Enakmen_TarikhMansuh", RS_TarikhMansuh);
					h.put("Enakmen_NoWarta", RS_NoWarta);
					h.put("Enakmen_TarikhWarta", RS_TarikhWarta);
					h.put("Enakmen_TarafKeselamatan", RS_TarafKeselamatan);
					h.put("Enakmen_IDSeksyen", RS_IDSeksyen);
					h.put("Enakmen_NoFail", RS_NoFail);
					h.put("Enakmen_Catatan", RS_Catatan);
					h.put("Enakmen_TarikhDaftar", RS_TarikhDaftar);
					h.put("tagging", Utils.isNull(rs.getString(12)));
					h.put("idTagging", Utils.isNull(rs.getString(13)));
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

	/////////

	// TAMBAH Enakmen BARU
	public static String enakmenAdd(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try {
			long idEnakmen = DB.getNextID("TBLPDTENAKMEN_SEQ");
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_ENAKMEN", idEnakmen);
			r.add("NO_ENAKMEN", data.get("noEnakmen"));
			r.add("NAMA_ENAKMEN", data.get("namaEnakmen"));

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
			sql = r.getSQLInsert("TBLPDTENAKMEN");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
			
			String tagDokumen = (String)data.get("tagDokumen");
			String idTagDokumen = (String)data.get("idTagDokumen");
			if(!tagDokumen.equals("")){
				db = new Db();
				stmt = db.getStatement();
			    r = new SQLRenderer();			      
				if(!idTagDokumen.equals("")){
					r.update("ID_RUJTAG", data.get("idTagDokumen"));
					r.add("ID_KEMASKINI", data.get("idMasuk"));
					r.add("TAG_DOKUMEN",tagDokumen);
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql = r.getSQLUpdate("TBLRUJTAGDOKUMEN");								
				
				}else{
					long idTagDokumenBaru = DB.getNextID("TBLRUJTAGDOKUMEN_SEQ");
					r.add("ID_RUJTAG",idTagDokumenBaru);
					r.add("ID_DOKUMEN",idEnakmen);
					r.add("TAG_DOKUMEN",tagDokumen);
					r.add("SUMBER","ENAKMEN");
					r.add("TARIKH_MASUK",r.unquote("sysdate")); 
					r.add("ID_MASUK",data.get("idMasuk"));
					sql = r.getSQLInsert("TBLRUJTAGDOKUMEN");  
				
				}
			    stmt.executeUpdate(sql);

			}

			return String.valueOf(idEnakmen);

		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null)
				db.close();
		}
	}


	public static void enakmenUpdate(Hashtable data) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_Enakmen", data.get("idEnakmen"));
			r.add("NO_Enakmen", data.get("noEnakmen"));
			r.add("NAMA_Enakmen", data.get("namaEnakmen"));
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
			sql = r.getSQLUpdate("TBLPDTEnakmen");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
			
			String tagDokumen = (String)data.get("tagDokumen");
			String idTagDokumen = (String)data.get("idTagDokumen");
			myLogger.debug("idTagDokumen="+idTagDokumen);
			if(!tagDokumen.equals("")){
				db = new Db();
				stmt = db.getStatement();
			    r = new SQLRenderer();			      
				if(!idTagDokumen.equals("0")){
					r.update("ID_RUJTAG", data.get("idTagDokumen"));
					r.add("ID_KEMASKINI", data.get("idMasuk"));
					r.add("TAG_DOKUMEN",tagDokumen);
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql = r.getSQLUpdate("TBLRUJTAGDOKUMEN");								
				
				}else{
					long idTagDokumenBaru = DB.getNextID("TBLRUJTAGDOKUMEN_SEQ");
					r.add("ID_RUJTAG",idTagDokumenBaru);
					r.add("ID_DOKUMEN",data.get("idEnakmen"));
					r.add("TAG_DOKUMEN",tagDokumen);
					r.add("SUMBER","ENAKMEN");
					r.add("TARIKH_MASUK",r.unquote("sysdate")); 
					r.add("ID_MASUK",data.get("idMasuk"));
					sql = r.getSQLInsert("TBLRUJTAGDOKUMEN");  
				
				}
				myLogger.debug(sql);
			    stmt.executeUpdate(sql);

			}
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)
				db.close();
		}
	}

	public static void enakmenDelete(String idEnakmen) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_Enakmen",idEnakmen);
			sql = r.getSQLDelete("TBLPDTEnakmen");
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
	public Vector penggalList(String ID_ENAKMEN) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			if (!"".equalsIgnoreCase(ID_ENAKMEN)) {
				int iCount = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				String RS_IDPenggal = "", RS_NoEnakmen = "", RS_NamaEnakmen = "", RS_NoPenggal = "", RS_TajukPenggal = "", RS_Enakmen_NoFail = "";
				sql = "SELECT M.ID_ENAKMENPENGGAL, C.NO_ENAKMEN, C.NAMA_ENAKMEN, M.NO_PENGGAL, M.TAJUK_PENGGAL, C.NO_FAIL " +
					"FROM TBLPDTENAKMENPENGGAL M, TBLPDTENAKMEN C " +
					"WHERE M.ID_ENAKMEN = C.ID_ENAKMEN AND M.ID_ENAKMEN = '" + ID_ENAKMEN + "' ORDER BY M.NO_PENGGAL, M.TAJUK_PENGGAL";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDPenggal = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NoEnakmen = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NamaEnakmen = rs.getString(3) == null ? "" : rs.getString(3);
					RS_NoPenggal = rs.getString(4) == null ? "" : rs.getString(4);
					RS_TajukPenggal = rs.getString(5) == null ? "" : rs.getString(5);
					RS_Enakmen_NoFail = rs.getString(6) == null ? "" : rs.getString(6);
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDPenggal", RS_IDPenggal);
					h.put("NoEnakmen", RS_NoEnakmen);
					h.put("NamaEnakmen", RS_NamaEnakmen);
					h.put("NoPenggal", RS_NoPenggal);
					h.put("TajukPenggal", RS_TajukPenggal);
					h.put("Enakmen_NoFail", RS_Enakmen_NoFail);
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

				String RS_NoPenggal = "", RS_TajukPenggal = "",RS_NoEnakmen = "", RS_NamaEnakmen = "", RS_Enakmen_NoFail = "";
				sql = "SELECT M.NO_PENGGAL, M.TAJUK_PENGGAL, C.NO_ENAKMEN, C.NAMA_ENAKMEN, C.NO_FAIL " +
					"FROM TBLPDTENAKMENPENGGAL M, TBLPDTENAKMEN C " +
					"WHERE M.ID_ENAKMEN = C.ID_ENAKMEN " +
					"AND M.ID_ENAKMENPENGGAL = " + IDPenggal;
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_NoPenggal = rs.getString(1) == null ? "" : rs.getString(1);
					RS_TajukPenggal = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NoEnakmen = rs.getString(3) == null ? "" : rs.getString(3);
					RS_NamaEnakmen = rs.getString(4) == null ? "" : rs.getString(4);
					RS_Enakmen_NoFail = rs.getString(5) == null ? "" : rs.getString(5);
					h = new Hashtable();
					h.put("Penggal_IDPenggal", IDPenggal);
					h.put("Penggal_NoPenggal", RS_NoPenggal);
					h.put("Penggal_TajukPenggal", RS_TajukPenggal);
					h.put("Enakmen_NamaEnakmen", RS_NamaEnakmen);
					h.put("Enakmen_NoEnakmen", RS_NoEnakmen);
					h.put("Enakmen_NoFail", RS_Enakmen_NoFail);
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
			r.add("ID_ENAKMEN", data.get("idEnakmen"));
			r.add("ID_MASUK", data.get("idMasuk"));
			r.add("TARIKH_MASUK",r.unquote("sysdate"));

			sql = r.getSQLInsert("TBLPDTENAKMENPENGGAL");

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
			r.update("ID_ENAKMENPENGGAL", data.get("idEnakmenPenggal"));

			sql = r.getSQLUpdate("TBLPDTENAKMENPENGGAL");
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


			//sql = "DELETE FROM TBLPDTENAKMENBAHAGIAN WHERE ID_ENAKMENPENGGAL = ''";
			r.add("ID_ENAKMENPENGGAL",idPenggal);
			sql = r.getSQLDelete("TBLPDTENAKMENBAB");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);

			//sql = "DELETE FROM TBLPDTENAKMENBAHAGIAN WHERE ID_ENAKMENPENGGAL = ''";
			r.add("ID_ENAKMENPENGGAL",idPenggal);
			sql = r.getSQLDelete("TBLPDTENAKMENBAHAGIAN");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);

			//TABLE LAST
			r.clear();
			r.add("ID_ENAKMENPENGGAL",idPenggal);
			sql = r.getSQLDelete("TBLPDTENAKMENPENGGAL");
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
	public Vector bahagianList(String ID_ENAKMEN) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			if (!"".equalsIgnoreCase(ID_ENAKMEN)) {
				int iCount = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				String RS_IDBahagian = "", RS_NoEnakmen = "", RS_NamaEnakmen = "", RS_NoPenggal = "", RS_NoBahagian = "", RS_TajukBahagian = "";
				sql = "SELECT M.ID_ENAKMENBAHAGIAN, A.NO_ENAKMEN, A.NAMA_ENAKMEN, " +
						"NVL(P.NO_PENGGAL,'-') NO_PENGGAL, M.NO_BAHAGIAN, M.TAJUK_BAHAGIAN " +
					"FROM TBLPDTENAKMENBAHAGIAN M, TBLPDTENAKMEN A, TBLPDTENAKMENPENGGAL P " +
					"WHERE M.ID_ENAKMEN = A.ID_ENAKMEN AND M.ID_ENAKMENPENGGAL = P.ID_ENAKMENPENGGAL(+) " +
					"AND M.ID_ENAKMEN = " + ID_ENAKMEN + " ORDER BY M.NO_BAHAGIAN, M.TAJUK_BAHAGIAN";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDBahagian = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NoEnakmen = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NamaEnakmen = rs.getString(3) == null ? "" : rs.getString(3);
					RS_NoPenggal = rs.getString(4) == null ? "" : rs.getString(4);
					RS_NoBahagian = rs.getString(5) == null ? "" : rs.getString(5);
					RS_TajukBahagian = rs.getString(6) == null ? "" : rs.getString(6);
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDBahagian", RS_IDBahagian);
					h.put("NoEnakmen", RS_NoEnakmen);
					h.put("NamaEnakmen", RS_NamaEnakmen);
					h.put("NoPenggal", RS_NoPenggal);
					h.put("NoBahagian", RS_NoBahagian);
					h.put("TajukBahagian", RS_TajukBahagian);
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
	public Vector bahagianPapar(String IDBahagian) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			if (!"".equalsIgnoreCase(IDBahagian)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				String RS_IDPenggal = "", RS_NoBahagian = "", RS_TajukBahagian = "", RS_TajukPenggal = "",RS_NoEnakmen = "", RS_NamaEnakmen = "", RS_Enakmen_NoFail = "";
				sql = "SELECT M.ID_ENAKMENPENGGAL, M.NO_BAHAGIAN, M.TAJUK_BAHAGIAN, C.TAJUK_PENGGAL, D.NO_ENAKMEN, D.NAMA_ENAKMEN, D.NO_FAIL " +
					"FROM TBLPDTENAKMENBAHAGIAN M, TBLPDTENAKMENPENGGAL C, " +
					"TBLPDTENAKMEN D " +
					"WHERE D.ID_ENAKMEN = C.ID_ENAKMEN " +
					"AND M.ID_ENAKMENPENGGAL = C.ID_ENAKMENPENGGAL(+) " +
					"AND M.ID_ENAKMENBAHAGIAN = " + IDBahagian;
				myLogger.debug("papar bahagian " + sql);
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_IDPenggal = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NoBahagian = rs.getString(2) == null ? "" : rs.getString(2);
					RS_TajukBahagian = rs.getString(3) == null ? "" : rs.getString(3);
					RS_TajukPenggal = rs.getString(4) == null ? "" : rs.getString(4);
					RS_NoEnakmen = rs.getString(5) == null ? "" : rs.getString(5);
					RS_NamaEnakmen = rs.getString(6) == null ? "" : rs.getString(6);
					RS_Enakmen_NoFail = rs.getString(7) == null ? "" : rs.getString(7);
					h = new Hashtable();
					h.put("Penggal_IDPenggal", RS_IDPenggal);
					h.put("Bahagian_NoBahagian", RS_NoBahagian);
					h.put("Bahagian_TajukBahagian", RS_TajukBahagian);
					h.put("Bahagian_Penggal", RS_TajukPenggal);
					h.put("Enakmen_NamaEnakmen", RS_NamaEnakmen);
					h.put("Enakmen_NoEnakmen", RS_NoEnakmen);
					h.put("Enakmen_NoFail", RS_Enakmen_NoFail);
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

	public Boolean bahagianSave(String mode,String IDBahagian, String IDEnakmen, String IDPenggal,
			String NoBahagian, String TajukBahagian) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();

			r.add("ID_ENAKMEN", IDEnakmen);
			r.add("ID_ENAKMENPENGGAL", IDPenggal);
			r.add("NO_BAHAGIAN", NoBahagian);
			r.add("TAJUK_BAHAGIAN", TajukBahagian);
			if ("update".equals(mode)) {
				r.update("ID_ENAKMENBAHAGIAN", IDBahagian);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLPDTENAKMENBAHAGIAN");
			} else {
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPDTENAKMENBAHAGIAN");
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

	public Boolean bahagianUpdate(String IDBahagian, String IDEnakmen, String IDPenggal, String NoBahagian, String TajukBahagian) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = new Db();

		try {
			Boolean haveData = false;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			if (!"".equalsIgnoreCase(IDBahagian)) {
				sql = "SELECT ID_ENAKMENBAHAGIAN FROM TBLPDTENAKMENBAHAGIAN WHERE " +
						"ID_ENAKMENBAHAGIAN = " + IDBahagian;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveData = true;
				}
				rs.close();
			}
			SQLRenderer r = new SQLRenderer();
			if (haveData) {
				r.update("ID_ENAKMENBAHAGIAN", IDBahagian);
			}
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ID_ENAKMEN", IDEnakmen);
			r.add("ID_ENAKMENPENGGAL", IDPenggal);
			r.add("NO_BAHAGIAN", NoBahagian);
			r.add("TAJUK_BAHAGIAN", TajukBahagian);
			if (haveData) {
				sql = r.getSQLUpdate("TBLPDTENAKMENBAHAGIAN");
			} else {
				long ID_DB = DB.getNextID("TBLPDTENAKMENBAHAGIAN_SEQ");
				r.add("ID_ENAKMENBAHAGIAN", ID_DB);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPDTENAKMENBAHAGIAN");
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

			r.add("ID_ENAKMENBAHAGIAN",idBahagian);
			sql = r.getSQLDelete("TBLPDTENAKMENBAB");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);

			r.add("ID_ENAKMENBAHAGIAN",idBahagian);
			sql = r.getSQLDelete("TBLPDTENAKMENBAHAGIAN");
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
	public Vector babList(String ID_ENAKMEN) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			if (!"".equalsIgnoreCase(ID_ENAKMEN)) {
				int iCount = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				String RS_IDBab = "", RS_NoEnakmen = "", RS_NamaEnakmen = "", RS_NoPenggal = "", RS_NoBahagian = "", RS_NoBab = "", RS_TajukBab = "";
				sql = "SELECT M.ID_ENAKMENBAB, A.NO_ENAKMEN, A.NAMA_ENAKMEN, P.NO_PENGGAL, B.NO_BAHAGIAN, M.NO_BAB, M.TAJUK_BAB " +
					"FROM TBLPDTENAKMEN A,TBLPDTENAKMENBAB M, TBLPDTENAKMENPENGGAL P, TBLPDTENAKMENBAHAGIAN B " +
					"WHERE A.ID_ENAKMEN=M.ID_ENAKMEN (+) AND M.ID_ENAKMENPENGGAL = P.ID_ENAKMENPENGGAL(+) " +
					"AND M.ID_ENAKMENBAHAGIAN = B.ID_ENAKMENBAHAGIAN(+) " +
					"AND M.ID_ENAKMEN = " + ID_ENAKMEN + " ORDER BY M.NO_BAB, M.TAJUK_BAB";
				//myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDBab = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NoEnakmen = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NamaEnakmen = rs.getString(3) == null ? "" : rs.getString(3);
					RS_NoPenggal = rs.getString(4) == null ? "" : rs.getString(4);
					RS_NoBahagian = rs.getString(5) == null ? "" : rs.getString(5);
					RS_NoBab = rs.getString(6) == null ? "" : rs.getString(6);
					RS_TajukBab = rs.getString(7) == null ? "" : rs.getString(7);
					h = new Hashtable();
					h.put("No", iCount);
					h.put("idEnakmenBab",RS_IDBab);
					h.put("IDBab", RS_IDBab);
					h.put("NoEnakmen", RS_NoEnakmen);
					h.put("NamaEnakmen", RS_NamaEnakmen);
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

				String RS_IDBab = "", RS_IDPenggal = "", RS_IDBahagian = "", RS_NoBab = "", RS_TajukBab = "", RS_NoEnakmen = "", RS_NamaEnakmen = "", RS_Enakmen_NoFail = "";
				sql = "SELECT M.ID_ENAKMENPENGGAL, M.ID_ENAKMENBAHAGIAN, M.NO_BAB, M.TAJUK_BAB, D.NO_ENAKMEN, D.NAMA_ENAKMEN, D.NO_FAIL, M.ID_ENAKMENBAB " +
					"FROM TBLPDTENAKMENBAB M, TBLPDTENAKMENBAHAGIAN B, TBLPDTENAKMENPENGGAL P, TBLPDTENAKMEN D " +
					"WHERE D.ID_ENAKMEN = P.ID_ENAKMEN AND " +
					"M.ID_ENAKMENPENGGAL = P.ID_ENAKMENPENGGAL(+) AND " +
					"M.ID_ENAKMENBAHAGIAN = B.ID_ENAKMENBAHAGIAN (+) " +
					"AND M.ID_ENAKMENBAB = " + IDBab;
				/*myLogger.debug(":::::::::::::::::::::::::::::;;;"+sql);		*/	
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_IDPenggal = rs.getString(1) == null ? "" : rs.getString(1);
					RS_IDBahagian = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NoBab = rs.getString(3) == null ? "" : rs.getString(3);
					RS_TajukBab = rs.getString(4) == null ? "" : rs.getString(4);
					RS_NoEnakmen = rs.getString(5) == null ? "" : rs.getString(5);
					RS_NamaEnakmen = rs.getString(6) == null ? "" : rs.getString(6);
					RS_Enakmen_NoFail = rs.getString(7) == null ? "" : rs.getString(7);
					RS_IDBab = rs.getString(8) == null ? "" : rs.getString(8);
					h = new Hashtable();
					h.put("Penggal_IDPenggal", RS_IDPenggal);
					h.put("Bahagian_IDBahagian", RS_IDBahagian);
					h.put("Bab_NoBab", RS_NoBab);
					h.put("Bab_TajukBab", RS_TajukBab);
					h.put("Enakmen_NamaEnakmen", RS_NamaEnakmen);
					h.put("Enakmen_NoEnakmen", RS_NoEnakmen);
					h.put("Enakmen_NoFail", RS_Enakmen_NoFail);
					h.put("Bab_IDBab", RS_IDBab);
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

	public Boolean babSave(String mode,String IDBab, String IDEnakmen, String IDPenggal, String IDBahagian, String NoBab, String TajukBab) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = null;
		
		myLogger.info("IDBab dlm sql - "+IDBab);

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			SQLRenderer r = new SQLRenderer();

			r.add("ID_ENAKMEN", IDEnakmen);
			r.add("ID_ENAKMENPENGGAL", IDPenggal);
			r.add("ID_ENAKMENBAHAGIAN", IDBahagian);
			r.add("NO_BAB", NoBab);
			r.add("TAJUK_BAB", TajukBab);
			if ("update".equals(mode)) {
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.update("ID_ENAKMENBAB", IDBab);
				sql = r.getSQLUpdate("TBLPDTENAKMENBAB");
			} else {
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPDTENAKMENBAB");
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
			r.add("ID_ENAKMENBAB",idBab);
			sql = r.getSQLDelete("TBLPDTENAKMENBAB");
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
	public Vector seksyenList(String ID_ENAKMEN) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			if (!"".equalsIgnoreCase(ID_ENAKMEN)) {
				int iCount = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				String RS_IDSeksyen = "", RS_NoPenggal = "", RS_NoBahagian = "", RS_NoBab = "", RS_Seksyen = "";
				sql = "SELECT M.ID_ENAKMENSEKSYEN, P.NO_PENGGAL, B.NO_BAHAGIAN, C.NO_BAB, " +
						"M.SEKSYEN,M.PROVISO,M.NO_SEKSYEN,M.TAJUK_SEKSYEN " +
					"FROM TBLPDTENAKMEN A,TBLPDTENAKMENSEKSYEN M, TBLPDTENAKMENPENGGAL P, " +
					"TBLPDTENAKMENBAHAGIAN B, TBLPDTENAKMENBAB C " +
					"WHERE A.ID_ENAKMEN = M.ID_ENAKMEN AND M.ID_ENAKMENPENGGAL = P.ID_ENAKMENPENGGAL (+) AND " +
					"M.ID_ENAKMENBAHAGIAN = B.ID_ENAKMENBAHAGIAN (+) AND M.ID_ENAKMENBAB = C.ID_ENAKMENBAB (+)" +
					"AND M.ID_ENAKMEN = " + ID_ENAKMEN + " ORDER BY M.NO_SEKSYEN, M.ID_ENAKMENSEKSYEN";
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

				String RS_IDPenggal = "", RS_IDBahagian = "", RS_IDBab = "", RS_Seksyen = "", RS_Proviso = "", RS_NoEnakmen = "", RS_NamaEnakmen = "", RS_Enakmen_NoFail = "";
				sql = "SELECT M.ID_ENAKMENPENGGAL, M.ID_ENAKMENBAHAGIAN, M.ID_ENAKMENBAB, " +
						"M.SEKSYEN, M.PROVISO,M.NO_SEKSYEN,M.TAJUK_SEKSYEN," +
						"A.ID_ENAKMEN, D.NO_ENAKMEN, D.NAMA_ENAKMEN, D.NO_FAIL " +
					"FROM TBLPDTENAKMENSEKSYEN M,TBLPDTENAKMENBAB A, TBLPDTENAKMENBAHAGIAN B, TBLPDTENAKMENPENGGAL P, TBLPDTENAKMEN D " +
					"WHERE D.ID_ENAKMEN = M.ID_ENAKMEN (+) " +
					"AND M.ID_ENAKMENBAB = A.ID_ENAKMENBAB(+) " +
					"AND M.ID_ENAKMENPENGGAL = P.ID_ENAKMENPENGGAL (+)" +
					"AND M.ID_ENAKMENBAHAGIAN = B.ID_ENAKMENBAHAGIAN (+) " +
					"AND M.ID_ENAKMENSEKSYEN = '" + IDSeksyen +"'";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_IDPenggal = rs.getString(1) == null ? "" : rs.getString(1);
					RS_IDBahagian = rs.getString(2) == null ? "" : rs.getString(2);
					RS_IDBab = rs.getString(3) == null ? "" : rs.getString(3);
					RS_Seksyen = rs.getString(4) == null ? "" : rs.getString(4);
					RS_Proviso = rs.getString(5) == null ? "" : rs.getString(5);
					RS_NoEnakmen = rs.getString(6) == null ? "" : rs.getString(6);
					RS_NamaEnakmen = rs.getString(7) == null ? "" : rs.getString(7);
					RS_Enakmen_NoFail = rs.getString(8) == null ? "" : rs.getString(8);
					h = new Hashtable();
					h.put("id_enakmen",Utils.isNull(rs.getString("id_enakmen")));
					h.put("Penggal_IDPenggal", RS_IDPenggal);
					h.put("Bahagian_IDBahagian", RS_IDBahagian);
					h.put("Bab_IDBab", RS_IDBab);
					h.put("Seksyen_Seksyen", RS_Seksyen);
					h.put("Seksyen_Proviso", RS_Proviso);
					h.put("Seksyen_NoSeksyen", Utils.isNull(rs.getString("NO_SEKSYEN")));
					h.put("Seksyen_TajukSeksyen", Utils.isNull(rs.getString("TAJUK_SEKSYEN")));
					h.put("Enakmen_NamaEnakmen", RS_NamaEnakmen);
					h.put("Enakmen_NoEnakmen", RS_NoEnakmen);
					h.put("Enakmen_NoFail", RS_Enakmen_NoFail);
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

	public Boolean seksyenSave(String mode,String IDSeksyen, String IDEnakmen, String IDPenggal,
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

			r.add("ID_ENAKMEN", IDEnakmen);
			r.add("ID_ENAKMENPENGGAL", IDPenggal);
			r.add("ID_ENAKMENBAHAGIAN", IDBahagian);
			r.add("ID_ENAKMENBAB", IDBab);
			r.add("SEKSYEN", Seksyen);
			r.add("PROVISO", Proviso);
			r.add("No_Seksyen", NoSeksyen);
			r.add("Tajuk_Seksyen", TajukSeksyen);
			if ("update".equals(mode)) {
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI",user_id);
				r.update("ID_ENAKMENSEKSYEN", IDSeksyen);
				sql = r.getSQLUpdate("TBLPDTENAKMENSEKSYEN");
			} else {
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_MASUK",user_id);
				sql = r.getSQLInsert("TBLPDTENAKMENSEKSYEN");
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
			r.add("ID_ENAKMENSEKSYEN",idSeksyen);
			sql = r.getSQLDelete("TBLPDTENAKMENSEKSYEN");
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
	public Vector subSeksyenList(String ID_ENAKMEN) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		try {
			if (!"".equalsIgnoreCase(ID_ENAKMEN)) {
				int iCount = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				sql = "SELECT M.ID_ENAKMENSUBSEKSYEN, P.NO_PENGGAL, B.NO_BAHAGIAN, "+
					"C.NO_BAB,C.TAJUK_BAB,  "+
					"S.NO_SEKSYEN,S.PROVISO AS SEKSYEN_PROVISO, "+
					"M.SUB_SEKSYEN,M.PROVISO AS SUBPROVISO,  "+
					"M.NOSUB_SEKSYEN,M.TAJUKSUB_SEKSYEN "+
					"FROM TBLPDTENAKMEN A,TBLPDTENAKMENSUBSEKSYEN M, TBLPDTENAKMENPENGGAL P, TBLPDTENAKMENBAHAGIAN B, TBLPDTENAKMENBAB C, TBLPDTENAKMENSEKSYEN S " +
					"WHERE A.ID_ENAKMEN = M.ID_ENAKMEN AND " +
					"M.ID_ENAKMENPENGGAL = P.ID_ENAKMENPENGGAL (+) AND " +
					"M.ID_ENAKMENBAHAGIAN = B.ID_ENAKMENBAHAGIAN (+) AND " +
					"M.ID_ENAKMENBAB = C.ID_ENAKMENBAB (+) " +
					"AND M.ID_ENAKMENSEKSYEN = S.ID_ENAKMENSEKSYEN " +
					"AND M.ID_ENAKMEN = " + ID_ENAKMEN + " ORDER BY S.SEKSYEN,M.NOSUB_SEKSYEN, " +
							"M.ID_ENAKMENSUBSEKSYEN";
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
					h.put("IDSubSeksyen",Utils.isNull(rs.getString("ID_ENAKMENSUBSEKSYEN")));
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
				sql = "SELECT M.ID_ENAKMENPENGGAL, M.ID_ENAKMENBAHAGIAN, M.ID_ENAKMENBAB, S.ID_ENAKMENSEKSYEN, " +
						"M.SUB_SEKSYEN, M.PROVISO,M.NOSUB_SEKSYEN,M.TAJUKSUB_SEKSYEN " +
					"FROM TBLPDTENAKMENSUBSEKSYEN M, TBLPDTENAKMENBAHAGIAN B, TBLPDTENAKMENPENGGAL P, TBLPDTENAKMENBAB A, TBLPDTENAKMENSEKSYEN S " +
					"WHERE M.ID_ENAKMENPENGGAL = P.ID_ENAKMENPENGGAL (+) " +
					"AND M.ID_ENAKMENBAHAGIAN = B.ID_ENAKMENBAHAGIAN (+) " +
					"AND M.ID_ENAKMENBAB = A.ID_ENAKMENBAB (+) " +
					"AND M.ID_ENAKMENSEKSYEN = S.ID_ENAKMENSEKSYEN (+) " +
					"AND M.ID_ENAKMENSUBSEKSYEN = " + IDSubSeksyen;
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

	public Boolean subSeksyenSave(String mode,String IDSubSeksyen, String IDEnakmen, String IDPenggal,
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
			r.add("ID_ENAKMEN", IDEnakmen);
			r.add("ID_ENAKMENPENGGAL", IDPenggal);
			r.add("ID_ENAKMENBAHAGIAN", IDBahagian);
			r.add("ID_ENAKMENBAB", IDBab);
			r.add("ID_ENAKMENSEKSYEN", IDSeksyen);
			r.add("SUB_SEKSYEN", SubSeksyen);
			r.add("PROVISO", Proviso);
			r.add("NOSUB_SEKSYEN", NoSubSeksyen);
			r.add("TAJUKSUB_SEKSYEN", TajukSubSeksyen);

			if ("update".equals(mode)) {
				r.update("ID_ENAKMENSUBSEKSYEN", IDSubSeksyen);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI",user_id);
				sql = r.getSQLUpdate("TBLPDTENAKMENSUBSEKSYEN");
			} else {
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_MASUK",user_id);
				sql = r.getSQLInsert("TBLPDTENAKMENSUBSEKSYEN");
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
			r.add("ID_ENAKMENSUBSEKSYEN",idSubSeksyen);
			sql = r.getSQLDelete("TBLPDTENAKMENSUBSEKSYEN");
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
	public Boolean paraSave(String mode,String IDPara, String IDEnakmen, String IDPenggal, String IDBahagian, String IDBab, String IDSeksyen, String IDSubSeksyen, String Para, String Jadual) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			r.add("ID_ENAKMEN", IDEnakmen);
			r.add("ID_ENAKMENPENGGAL", IDPenggal);
			r.add("ID_ENAKMENBAHAGIAN", IDBahagian);
			r.add("ID_ENAKMENBAB", IDBab);
			r.add("ID_ENAKMENSEKSYEN", IDSeksyen);
			r.add("ID_ENAKMENSUBSEKSYEN", IDSubSeksyen);
			r.add("PARA", Para);
			//r.add("JADUAL", Jadual);

			if ("update".equals(mode)) {
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.update("ID_ENAKMENPARA", IDPara);
				sql = r.getSQLUpdate("TBLPDTENAKMENPARA");
			} else {
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPDTENAKMENPARA");
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
	public Vector paraList(String ID_ENAKMEN) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			if (!"".equalsIgnoreCase(ID_ENAKMEN)) {
				int iCount = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				String RS_IDPara = "", RS_NoEnakmen = "", RS_NoPenggal = "", RS_NoBahagian = "", RS_NoBab = "", RS_Seksyen = "", RS_SubSeksyen = "", RS_Para = "";
				sql = "SELECT M.ID_ENAKMENPARA, A.NO_ENAKMEN, P.NO_PENGGAL, B.NO_BAHAGIAN, C.NO_BAB, SUBSTR(S.SEKSYEN, 1, 50), SUBSTR(SS.SUB_SEKSYEN, 1, 50), SUBSTR(M.PARA, 1, 50) " +
					"FROM TBLPDTENAKMENPARA M, TBLPDTENAKMEN A, TBLPDTENAKMENPENGGAL P, TBLPDTENAKMENBAHAGIAN B, TBLPDTENAKMENBAB C, TBLPDTENAKMENSEKSYEN S, TBLPDTENAKMENSUBSEKSYEN SS " +
					"WHERE A.ID_ENAKMEN=M.ID_ENAKMEN(+) " +
					"AND M.ID_ENAKMENPENGGAL = P.ID_ENAKMENPENGGAL(+) AND " +
					"M.ID_ENAKMENBAHAGIAN = B.ID_ENAKMENBAHAGIAN(+) AND " +
					"M.ID_ENAKMENBAB = C.ID_ENAKMENBAB(+) AND " +
					"M.ID_ENAKMENSEKSYEN = S.ID_ENAKMENSEKSYEN(+) AND " +
					"M.ID_ENAKMENSUBSEKSYEN = SS.ID_ENAKMENSUBSEKSYEN(+) " +
					"AND M.ID_ENAKMEN = " + ID_ENAKMEN + " ORDER BY M.PARA, M.ID_ENAKMENPARA";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDPara = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NoEnakmen = rs.getString(2) == null ? "" : rs.getString(2);
					RS_NoPenggal = rs.getString(3) == null ? "" : rs.getString(3);
					RS_NoBahagian = rs.getString(4) == null ? "" : rs.getString(4);
					RS_NoBab = rs.getString(5) == null ? "" : rs.getString(5);
					RS_Seksyen = rs.getString(6) == null ? "" : rs.getString(6);
					RS_SubSeksyen = rs.getString(7) == null ? "" : rs.getString(7);
					RS_Para = rs.getString(8) == null ? "" : rs.getString(8);
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDPara", RS_IDPara);
					h.put("NoEnakmen", RS_NoEnakmen);
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
	public Vector paraPapar(String IDPara) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			if (!"".equalsIgnoreCase(IDPara)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				String RS_IDPara = "", RS_IDPenggal = "", RS_IDBahagian = "", RS_IDBab = "", RS_Seksyen = "", RS_SubSeksyen = "", RS_Para = "", RS_Jadual = "", RS_NoEnakmen = "", RS_NamaEnakmen = "", RS_Enakmen_NoFail = "";
				sql = "SELECT M.ID_ENAKMENPENGGAL, M.ID_ENAKMENBAHAGIAN, M.ID_ENAKMENBAB, S.ID_ENAKMENSEKSYEN, SS.ID_ENAKMENSUBSEKSYEN, M.PARA, M.ID_JADUAL, C.NO_ENAKMEN, C.NAMA_ENAKMEN, C.NO_FAIL, M.ID_ENAKMENPARA " +
					"FROM TBLPDTENAKMENPARA M, TBLPDTENAKMENBAHAGIAN B, TBLPDTENAKMENPENGGAL P, TBLPDTENAKMENBAB A, TBLPDTENAKMENSEKSYEN S, TBLPDTENAKMENSUBSEKSYEN SS, TBLPDTENAKMEN C " +
					
					"WHERE C.ID_ENAKMEN = M.ID_ENAKMEN (+) AND " +
					"M.ID_ENAKMENPENGGAL = P.ID_ENAKMENPENGGAL (+) AND " +
					"M.ID_ENAKMENBAHAGIAN = B.ID_ENAKMENBAHAGIAN (+) AND " +
					"M.ID_ENAKMENBAB = A.ID_ENAKMENBAB (+) AND " +
					"M.ID_ENAKMENSEKSYEN = S.ID_ENAKMENSEKSYEN (+) AND " +
					"M.ID_ENAKMENSUBSEKSYEN = SS.ID_ENAKMENSUBSEKSYEN (+)" +
					"AND M.ID_ENAKMENPARA = '" + IDPara +"'";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					RS_IDPenggal = rs.getString(1) == null ? "" : rs.getString(1);
					RS_IDBahagian = rs.getString(2) == null ? "" : rs.getString(2);
					RS_IDBab = rs.getString(3) == null ? "" : rs.getString(3);
					RS_Seksyen = rs.getString(4) == null ? "" : rs.getString(4);
					RS_SubSeksyen = rs.getString(5) == null ? "" : rs.getString(5);
					RS_Para = rs.getString(6) == null ? "" : rs.getString(6);
					RS_Jadual = rs.getString(7) == null ? "" : rs.getString(7);
					RS_NoEnakmen = rs.getString(8) == null ? "" : rs.getString(8);
					RS_NamaEnakmen = rs.getString(9) == null ? "" : rs.getString(9);
					RS_Enakmen_NoFail = rs.getString(10) == null ? "" : rs.getString(10);
					RS_IDPara = rs.getString(11) == null ? "" : rs.getString(11);
					h = new Hashtable();
					h.put("Penggal_IDPenggal", RS_IDPenggal);
					h.put("Bahagian_IDBahagian", RS_IDBahagian);
					h.put("Bab_IDBab", RS_IDBab);
					h.put("Seksyen_IDSeksyen", RS_Seksyen);
					h.put("SubSeksyen_IDSubSeksyen", RS_SubSeksyen);
					h.put("Para_Para", RS_Para);
					h.put("Para_Jadual", RS_Jadual);
					h.put("Enakmen_NamaEnakmen", RS_NamaEnakmen);
					h.put("Enakmen_NoEnakmen", RS_NoEnakmen);
					h.put("Enakmen_NoFail", RS_Enakmen_NoFail);
					h.put("Para_IDPara", RS_IDPara);
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
			r.add("ID_ENAKMENPARA",idPara);
			sql = r.getSQLDelete("TBLPDTENAKMENPARA");
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
//	"update",SubPara_IDSubPara,Seksyen_IDSeksyen, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, SOC_Para, SubPara_SubPara, SubPara_Jadual
	public Boolean subParaSave(String mode,String IDSubPara,  String IDEnakmen, String IDPenggal, String IDBahagian, String IDBab, String IDSeksyen, String IDSubSeksyen, String IDPara, String SubPara, String Jadual) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			SQLRenderer r = new SQLRenderer();
			
			myLogger.info("SubPara_SubPara :: "+SubPara);

			r.add("ID_ENAKMEN", IDEnakmen);
			r.add("ID_ENAKMENPENGGAL", IDPenggal);
			r.add("ID_ENAKMENBAHAGIAN", IDBahagian);
			r.add("ID_ENAKMENBAB", IDBab);
			r.add("ID_ENAKMENSEKSYEN", IDSeksyen);
			r.add("ID_ENAKMENSUBSEKSYEN", IDSubSeksyen);
			r.add("ID_ENAKMENPARA", IDPara);
			r.add("SUB_PARA", SubPara);
			r.add("ID_JADUAL", Jadual);

			if ("update".equals(mode)) {
				myLogger.info("SubPara_SubPara update mode :: "+SubPara);
				r.update("ID_ENAKMENSUBPARA", IDSubPara);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLPDTENAKMENSUBPARA");
			} else {
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPDTENAKMENSUBPARA");
			}
			r.clear();
			myLogger.info("SubPara_SubPara sql :: "+sql);
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
	public Vector subParaList(String ID_ENAKMEN) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			if (!"".equalsIgnoreCase(ID_ENAKMEN)) {
				int iCount = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				String RS_IDSubPara = "", RS_NoEnakmen = "", RS_NoPenggal = "", RS_NoBahagian = "", RS_NoBab = "", RS_Seksyen = "", RS_SubSeksyen = "", RS_Para = "", RS_SubPara = "";
				sql = "SELECT M.ID_ENAKMENSUBPARA, A.NO_ENAKMEN, P.NO_PENGGAL, B.NO_BAHAGIAN, C.NO_BAB, SUBSTR(S.SEKSYEN, 1, 50), SUBSTR(SS.SUB_SEKSYEN, 1, 50), SUBSTR(PR.PARA, 1, 50), SUBSTR(M.SUB_PARA, 1, 50) " +
					"FROM TBLPDTENAKMENSUBPARA M, TBLPDTENAKMEN A, TBLPDTENAKMENPENGGAL P, TBLPDTENAKMENBAHAGIAN B, " +
					"TBLPDTENAKMENBAB C, TBLPDTENAKMENSEKSYEN S, TBLPDTENAKMENSUBSEKSYEN SS, TBLPDTENAKMENPARA PR " +
					"WHERE M.ID_ENAKMEN = A.ID_ENAKMEN (+) AND M.ID_ENAKMENPENGGAL = P.ID_ENAKMENPENGGAL (+) AND " +
					"M.ID_ENAKMENBAHAGIAN = B.ID_ENAKMENBAHAGIAN (+) AND M.ID_ENAKMENBAB = C.ID_ENAKMENBAB (+) AND " +
					"M.ID_ENAKMENSEKSYEN = S.ID_ENAKMENSEKSYEN (+) AND " +
					"M.ID_ENAKMENSUBSEKSYEN = SS.ID_ENAKMENSUBSEKSYEN (+) AND M.ID_ENAKMENPARA = PR.ID_ENAKMENPARA(+) " +
					"AND M.ID_ENAKMEN = '" + ID_ENAKMEN + "' ORDER BY M.SUB_PARA, M.ID_ENAKMENSUBPARA";
				//myLogger.debug("subParaList:"+sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDSubPara = rs.getString(1) == null ? "" : rs.getString(1);
					RS_NoEnakmen = rs.getString(2) == null ? "" : rs.getString(2);
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
					h.put("NoEnakmen", RS_NoEnakmen);
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
	public Vector subParaPapar(String IDSubPara) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			if (!"".equalsIgnoreCase(IDSubPara)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				String RS_IDEnakmen="", RS_IDSubPara = "", RS_IDPenggal = "", RS_IDBahagian = "", RS_IDBab = "", RS_Seksyen = "", RS_SubSeksyen = "", RS_Para = "", RS_SubPara = "", RS_Jadual = "",RS_NoEnakmen = "", RS_NamaEnakmen = "", RS_Enakmen_NoFail = "";
				sql = "SELECT M.ID_ENAKMENPENGGAL, M.ID_ENAKMENBAHAGIAN, M.ID_ENAKMENBAB, S.ID_ENAKMENSEKSYEN, SS.ID_ENAKMENSUBSEKSYEN, PR.ID_ENAKMENPARA, M.SUB_PARA, M.ID_JADUAL, C.NO_ENAKMEN, C.NAMA_ENAKMEN, C.NO_FAIL, M.ID_ENAKMENSUBPARA, M.ID_ENAKMEN " +
					" FROM TBLPDTENAKMENSUBPARA M, TBLPDTENAKMENBAHAGIAN B, TBLPDTENAKMENPENGGAL P, TBLPDTENAKMENBAB A, TBLPDTENAKMENSEKSYEN S, TBLPDTENAKMENSUBSEKSYEN SS, TBLPDTENAKMENPARA PR, TBLPDTENAKMEN C " +
					"WHERE M.ID_ENAKMEN = C.ID_ENAKMEN AND M.ID_ENAKMENPENGGAL = P.ID_ENAKMENPENGGAL AND M.ID_ENAKMENBAHAGIAN = B.ID_ENAKMENBAHAGIAN AND M.ID_ENAKMENBAB = A.ID_ENAKMENBAB AND M.ID_ENAKMENSEKSYEN = S.ID_ENAKMENSEKSYEN AND M.ID_ENAKMENSUBSEKSYEN = SS.ID_ENAKMENSUBSEKSYEN AND M.ID_ENAKMENPARA = PR.ID_ENAKMENPARA " +
					"AND M.ID_ENAKMENSUBPARA = " + IDSubPara;
				System.out.println(sql);
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
					RS_NoEnakmen = rs.getString(9) == null ? "" : rs.getString(9);
					RS_NamaEnakmen = rs.getString(10) == null ? "" : rs.getString(10);
					RS_Enakmen_NoFail = rs.getString(11) == null ? "" : rs.getString(11);
					RS_IDSubPara = rs.getString(12) == null ? "" : rs.getString(12);
					RS_IDEnakmen = rs.getString(13) == null ? "" : rs.getString(13);
					h = new Hashtable();
					h.put("Penggal_IDPenggal", RS_IDPenggal);
					h.put("Bahagian_IDBahagian", RS_IDBahagian);
					h.put("Bab_IDBab", RS_IDBab);
					h.put("Seksyen_IDSeksyen", RS_Seksyen);
					h.put("SubSeksyen_IDSubSeksyen", RS_SubSeksyen);
					h.put("Para_IDPara", RS_Para);
					h.put("SubPara_SubPara", RS_SubPara);
					h.put("SubPara_Jadual", RS_Jadual);
					h.put("Enakmen_NamaEnakmen", RS_NamaEnakmen);
					h.put("Enakmen_NoEnakmen", RS_NoEnakmen);
					h.put("Enakmen_NoFail", RS_Enakmen_NoFail);
					h.put("SubPara_IDSubPara", RS_IDSubPara);
					h.put("Enakmen_IDEnakmen", RS_IDEnakmen);
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
			r.add("ID_ENAKMENSUBPARA",idSubPara);
			sql = r.getSQLDelete("TBLPDTENAKMENSUBPARA");
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
	public String getSOCPenggal(String SOC_NAME, String SELECTED_PENGGAL, String ID_ENAKMEN) throws Exception {
		return getSOCPenggal(SOC_NAME, SELECTED_PENGGAL, ID_ENAKMEN, "", "");
	}
	public String getSOCPenggal(String SOC_NAME, String SELECTED_PENGGAL, String ID_ENAKMEN, String DISABLED) throws Exception {
		return getSOCPenggal(SOC_NAME, SELECTED_PENGGAL, ID_ENAKMEN, DISABLED, "");
	}
	public String getSOCPenggal(String SOC_NAME, String SELECTED_PENGGAL, String ID_ENAKMEN, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";

		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(ID_ENAKMEN)) {
				SQL_SEARCH += "WHERE ID_ENAKMEN = " + ID_ENAKMEN + " ";
			}

			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_ENAKMENPENGGAL = "", NO_PENGGAL = "", TAJUK_PENGGAL = "";
			sql = "SELECT ID_ENAKMENPENGGAL, NO_PENGGAL, SUBSTR(TAJUK_PENGGAL, 1, 20) FROM TBLPDTENAKMENPENGGAL " +
				SQL_SEARCH + "ORDER BY NO_PENGGAL, TAJUK_PENGGAL";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_ENAKMENPENGGAL = rs.getString(1) == null ? "" : rs.getString(1);
				NO_PENGGAL = rs.getString(2) == null ? "" : rs.getString(2);
				TAJUK_PENGGAL = rs.getString(3) == null ? "" : rs.getString(3);
				if (!"".equalsIgnoreCase(ID_ENAKMENPENGGAL)) {
					if (SELECTED_PENGGAL.equalsIgnoreCase(ID_ENAKMENPENGGAL)) {
						returnValue += "  <option value=\"" + ID_ENAKMENPENGGAL + "\" selected=\"selected\">" + NO_PENGGAL + " - " + TAJUK_PENGGAL + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_ENAKMENPENGGAL + "\">" + NO_PENGGAL + " - " + TAJUK_PENGGAL + "</option>\r\n";
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
			String ID_Enakmen, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";

		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(ID_Enakmen)) {
				SQL_SEARCH = "WHERE ID_ENAKMEN = '" + ID_Enakmen + "' ";
			}

			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_ENAKMENBAHAGIAN = "", NO_BAHAGIAN = "", TAJUK_BAHAGIAN = "";
			sql = "SELECT ID_ENAKMENBAHAGIAN, NO_BAHAGIAN, SUBSTR(TAJUK_BAHAGIAN, 1, 20) " +
					"FROM TBLPDTENAKMENBAHAGIAN " +
				SQL_SEARCH + "ORDER BY NO_BAHAGIAN, TAJUK_BAHAGIAN";
			myLogger.debug("SELECTED_BAHAGIAN:"+SELECTED_BAHAGIAN);
			myLogger.debug("soc bahagiain:"+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_ENAKMENBAHAGIAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_BAHAGIAN = rs.getString(2) == null ? "" : rs.getString(2);
				TAJUK_BAHAGIAN = rs.getString(3) == null ? "" : rs.getString(3);
				if (!"".equalsIgnoreCase(ID_ENAKMENBAHAGIAN)) {
					if (SELECTED_BAHAGIAN.equalsIgnoreCase(ID_ENAKMENBAHAGIAN)) {
						returnValue += "  <option value=\"" + ID_ENAKMENBAHAGIAN + "\" selected=\"selected\">" + NO_BAHAGIAN + " - " + TAJUK_BAHAGIAN + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_ENAKMENBAHAGIAN + "\">" + NO_BAHAGIAN + " - " + TAJUK_BAHAGIAN + "</option>\r\n";
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
	public String getSOCBab(String SOC_NAME, String SELECTED_BAB, String ID_ENAKMEN, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_ENAKMENBAB = "", NO_BAB = "", TAJUK_BAB = "";
			sql = "SELECT ID_ENAKMENBAB, NO_BAB, SUBSTR(TAJUK_BAB, 1, 20) FROM TBLPDTENAKMENBAB " +
				  "WHERE ID_ENAKMEN='"+ID_ENAKMEN+"' ORDER BY NO_BAB, TAJUK_BAB";
			myLogger.debug("SOC BAB:"+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_ENAKMENBAB = rs.getString(1) == null ? "" : rs.getString(1);
				NO_BAB = rs.getString(2) == null ? "" : rs.getString(2);
				TAJUK_BAB = rs.getString(3) == null ? "" : rs.getString(3);
				if (!"".equalsIgnoreCase(ID_ENAKMENBAB)) {
					if (SELECTED_BAB.equalsIgnoreCase(ID_ENAKMENBAB)) {
						returnValue += "  <option value=\"" + ID_ENAKMENBAB + "\" selected=\"selected\">" + NO_BAB + " - " + TAJUK_BAB + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_ENAKMENBAB + "\">" + NO_BAB + " - " + TAJUK_BAB + "</option>\r\n";
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
	public String getSOCSeksyen(String SOC_NAME, String SELECTED_SEKSYEN, String ID_ENAKMEN,
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
				SQL_SEARCH = "WHERE ID_ENAKMEN = '" + ID_ENAKMEN + "' ";
			//}

			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_ENAKMENSEKSYEN = "", SEKSYEN = "";
			sql = "SELECT ID_ENAKMENSEKSYEN, SUBSTR(NO_SEKSYEN, 1, 20) FROM TBLPDTENAKMENSEKSYEN " +
				SQL_SEARCH + "ORDER BY NO_SEKSYEN, ID_ENAKMENSEKSYEN";
			myLogger.debug("sql for dropdown seksyen : "+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_ENAKMENSEKSYEN = rs.getString(1) == null ? "" : rs.getString(1);
				SEKSYEN = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_ENAKMENSEKSYEN)) {
					if (SELECTED_SEKSYEN.equalsIgnoreCase(ID_ENAKMENSEKSYEN)) {
						returnValue += "  <option value=\"" + ID_ENAKMENSEKSYEN + "\" selected=\"selected\">" + SEKSYEN + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_ENAKMENSEKSYEN + "\">" + SEKSYEN + "</option>\r\n";
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
				SQL_SEARCH = "WHERE ID_ENAKMENSEKSYEN = '" + ID_SEKSYEN + "' ";
			//}

			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_ENAKMENSUBSEKSYEN = "", SUBSEKSYEN = "";
			sql = "SELECT ID_ENAKMENSUBSEKSYEN, SUBSTR(NOSUB_SEKSYEN, 1, 20) FROM TBLPDTENAKMENSUBSEKSYEN " +
				SQL_SEARCH + "ORDER BY SUB_SEKSYEN, ID_ENAKMENSUBSEKSYEN";
			//myLogger.debug(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_ENAKMENSUBSEKSYEN = rs.getString(1) == null ? "" : rs.getString(1);
				SUBSEKSYEN = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_ENAKMENSUBSEKSYEN)) {
					if (SELECTED_SUBSEKSYEN.equalsIgnoreCase(ID_ENAKMENSUBSEKSYEN)) {
						returnValue += "  <option value=\"" + ID_ENAKMENSUBSEKSYEN + "\" selected=\"selected\">" + SUBSEKSYEN + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_ENAKMENSUBSEKSYEN + "\">" + SUBSEKSYEN + "</option>\r\n";
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
	public String getSOCPara(String SOC_NAME, String SELECTED_PARA, String ID_ENAKMEN, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";

		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
				SQL_SEARCH = "WHERE ID_ENAKMEN = '" + ID_ENAKMEN + "' ";

			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_ENAKMENPARA = "", PARA = "";
			sql = "SELECT ID_ENAKMENPARA, SUBSTR(PARA, 1, 20) FROM TBLPDTENAKMENPARA " +
				SQL_SEARCH + "ORDER BY PARA, ID_ENAKMENPARA";
			myLogger.debug(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_ENAKMENPARA = rs.getString(1) == null ? "" : rs.getString(1);
				PARA = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_ENAKMENPARA)) {
					if (SELECTED_PARA.equalsIgnoreCase(ID_ENAKMENPARA)) {
						returnValue += "  <option value=\"" + ID_ENAKMENPARA + "\" selected=\"selected\">" + PARA + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_ENAKMENPARA + "\">" + PARA + "</option>\r\n";
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
	public String JadualParaSave(String mode,String IDJadual,String IDEnakmen,
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

			r.add("ID_ENAKMEN", IDEnakmen);

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


	public Vector JadualList(String ID_ENAKMEN) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = new Vector();
		Hashtable h = null;

		try {
			if (!"".equalsIgnoreCase(ID_ENAKMEN)) {
				int iCount = 1;
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				String RS_IDJadual="",RS_NamaJadual = "",RS_NoEnakmen;
				sql = "select a.id_jadual,a.nama_jadual,b.no_enakmen," +
						//"a.tajuk,substr(a.catatan,0,40)||'...' as catatan "+
						"a.tajuk,a.catatan "+
					  "FROM tblpdtjadual a,tblpdtenakmen b "+
					  "where a.id_enakmen=b.id_enakmen " +
					  "AND a.ID_ENAKMEN = '" + ID_ENAKMEN + "' ORDER BY a.TARIKH_MASUK";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDJadual = Utils.isNull(rs.getString(1));
					RS_NamaJadual = Utils.isNull(rs.getString(2));
					RS_NoEnakmen = Utils.isNull(rs.getString(3));
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDJadual", RS_IDJadual);
					h.put("NamaJadual", RS_NamaJadual);
					h.put("NoEnakmen", RS_NoEnakmen);
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
		}catch (Exception re) {
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


	public String getSOCJadual(String SOC_NAME, String SELECTED_JADUAL, String ID_ENAKMEN,
			String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";

		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
				SQL_SEARCH = "WHERE ID_ENAKMEN = '" + ID_ENAKMEN + "' ";

			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_ENAKMENPARA = "", PARA = "";
			sql = "SELECT ID_JADUAL, SUBSTR(NAMA_JADUAL, 1, 20) FROM TBLPDTJADUAL " +
				SQL_SEARCH + "ORDER BY ID_JADUAL";
			myLogger.debug(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_ENAKMENPARA = rs.getString(1) == null ? "" : rs.getString(1);
				PARA = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_ENAKMENPARA)) {
					//if (SELECTED_JADUAL.equalsIgnoreCase(ID_ENAKMENPARA)) {
					if (ID_ENAKMENPARA.equalsIgnoreCase(SELECTED_JADUAL)) {
						returnValue += "  <option value=\"" + ID_ENAKMENPARA + "\" selected=\"selected\">" + PARA + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_ENAKMENPARA + "\">" + PARA + "</option>\r\n";
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
		} catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		     finally {
			if (db != null)	db.close();
		}
		return String.valueOf(idJadualLampiran);
		//return returnValue;
	}


	public Vector JadualLampiranList(String ID_ENAKMEN) throws Exception {
		String sql = "";
		Db db = null;
		Vector v = new Vector();
		Hashtable h = null;

		try {
			if (!"".equalsIgnoreCase(ID_ENAKMEN)) {
				int iCount = 1;
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				String RS_IDJadualLampiran="",RS_NamaJadualLampiran = "",RS_NoEnakmen;
				sql = "select b.id_jaduallampiran,a.nama_jadual,c.no_enakmen," +
						//"b.nama_lampiran," +
						"b.tajuk,b.catatan from " +
						"tblpdtjadual a,tblpdtjaduallampiran b,tblpdtenakmen c " +
						"where a.id_jadual = b.id_jadual "+
						"AND a.id_enakmen = c.id_enakmen "+
						"AND a.ID_ENAKMEN = '" + ID_ENAKMEN + "' ORDER BY a.TARIKH_MASUK,a.nama_jadual asc";
				myLogger.debug(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					RS_IDJadualLampiran = Utils.isNull(rs.getString("id_jaduallampiran"));
					RS_NamaJadualLampiran = Utils.isNull(rs.getString("nama_jadual"));
					RS_NoEnakmen = Utils.isNull(rs.getString("no_enakmen"));
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDJadualLampiran", RS_IDJadualLampiran);
					h.put("NamaJadualLampiran", RS_NamaJadualLampiran);
					h.put("NoEnakmen", RS_NoEnakmen);
					//h.put("NamaLampiran",Utils.isNull(rs.getString(4)));
					h.put("Tajuk",Utils.isNull(rs.getString("tajuk")));
					h.put("Catatan",Utils.isNull(rs.getString("catatan")));
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

		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
			 throw re;
			 }
		      finally {
			if (db != null)
				db.close();
		}
	}


}