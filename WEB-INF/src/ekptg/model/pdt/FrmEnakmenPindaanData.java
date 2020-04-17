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

public class FrmEnakmenPindaanData {
	private static Logger myLogger = Logger.getLogger(FrmEnakmenPindaanData.class);
	private static Vector list = null;
	
	public Vector getListing(String noEnakmen,
			String namaEnakmen, String tarikhKuatkuasa) throws Exception {
		Db db = null;
				
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			list = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ENAKMENPINDA,A.NO_ENAKMEN_ASAL," +
					"A.NO_ENAKMEN_PINDAAN,A.NAMA_ENAKMEN_PINDAAN,A.TARIKH_KUATKUASA"
					+ " FROM TBLPDTENAKMENPINDA A WHERE A.ID_ENAKMENPINDA IS NOT NULL";

			// NO ENAKMEN
			if (noEnakmen != null) {
				if (!noEnakmen.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_ENAKMEN_PINDAAN) LIKE '%' ||'"
							+ noEnakmen.toUpperCase() + "'|| '%'";
				}
			}
			// NAMA ENAKMEN
			if (namaEnakmen != null) {
				if (!namaEnakmen.trim().equals("")) {
					sql = sql + " AND UPPER(A.NAMA_ENAKMEN_PINDAAN) LIKE '%' ||'"
							+ namaEnakmen.toUpperCase() + "'|| '%'";
				}
			}
			// TARIKH KUATKUASA
			if (tarikhKuatkuasa != null) {
				if (!tarikhKuatkuasa.trim().equals("")) {
					sql = sql
							+ " AND UPPER(A.TARIKH_KUATKUASA) LIKE '%' ||'"
							+ sdf.format(sdf.parse(tarikhKuatkuasa))
									.toUpperCase() + "'|| '%'";
				}
			}

			sql = sql + " ORDER BY A.ID_ENAKMENPINDA";
			myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("id_enakmen", rs.getString("ID_ENAKMENPINDA"));
				h.put("no_enakmen",Utils.isNull(rs.getString("NO_ENAKMEN_PINDAAN")));
				h.put("nama_enakmen",Utils.isNull(rs.getString("NAMA_ENAKMEN_PINDAAN")));
				h.put("no_enakmenAsal",Utils.isNull(rs.getString("NO_ENAKMEN_ASAL")));
				h.put("tarikh_kuatkuasa",
						rs.getDate("TARIKH_KUATKUASA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_KUATKUASA")));

				list.addElement(h);
				no++;
				count++;

			}


		}catch (Exception re) {
			 myLogger.error("Error: ", re);
			 throw re;
			 }
		  finally {
			if (db != null)
				db.close();
		}
		return list;
	}
	
	public Vector getListing(String txtNoEnakmenAsal,String noEnakmen,String namaEnakmen,String tarikhKuatkuasa,String tag) throws Exception {
		Db db = null;
				
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			list = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ENAKMENPINDA,A.NO_ENAKMEN_ASAL," +
					"A.NO_ENAKMEN_PINDAAN,A.NAMA_ENAKMEN_PINDAAN,A.TARIKH_KUATKUASA"
					+ " FROM TBLPDTENAKMENPINDA A WHERE A.ID_ENAKMENPINDA IS NOT NULL";

////			//NO ENAKMEN ASAL
					if (txtNoEnakmenAsal != null) {
						if (!txtNoEnakmenAsal.trim().equals("")) {
							sql = sql + " AND UPPER(A.NO_ENAKMEN_ASAL) LIKE '%' ||'"
										+ txtNoEnakmenAsal.toUpperCase() + "'|| '%'";
							}
					}
			// NO ENAKMEN
			if (noEnakmen != null) {
				if (!noEnakmen.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_ENAKMEN_PINDAAN) LIKE '%' ||'"
							+ noEnakmen.toUpperCase() + "'|| '%'";
				}
			}
			// NAMA ENAKMEN
			if (namaEnakmen != null) {
				if (!namaEnakmen.trim().equals("")) {
					sql = sql + " AND UPPER(A.NAMA_ENAKMEN_PINDAAN) LIKE '%' ||'"
							+ namaEnakmen.toUpperCase() + "'|| '%'";
				}
			}
			// TARIKH KUATKUASA
			if (tarikhKuatkuasa != null) {
				if (!tarikhKuatkuasa.trim().equals("")) {
					sql = sql
							+ " AND UPPER(A.TARIKH_KUATKUASA) LIKE '%' ||'"
							+ sdf.format(sdf.parse(tarikhKuatkuasa))
									.toUpperCase() + "'|| '%'";
				}
			}
			if (!"".equalsIgnoreCase(tag)) {
				sql += " AND A.ID_ENAKMENPINDA IN ( " +
						" SELECT TAGI.ID_DOKUMEN FROM TBLRUJTAGDOKUMEN TAGI,TBLPDTENAKMENPINDA DTI  " +
						" WHERE DTI.ID_ENAKMENPINDA = TAGI.ID_DOKUMEN AND TAGI.SUMBER='ENAKMENP' " +
						" AND UPPER(TAGI.TAG_DOKUMEN) LIKE '%" + tag + "%' )";
			}

			sql = sql + " ORDER BY A.ID_ENAKMENPINDA";
			myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("id_enakmen", rs.getString("ID_ENAKMENPINDA"));
				h.put("txtNoEnakmenAsal", rs.getString("NO_ENAKMEN_ASAL"));
				h.put("no_enakmen",Utils.isNull(rs.getString("NO_ENAKMEN_PINDAAN")));
				h.put("nama_enakmen",Utils.isNull(rs.getString("NAMA_ENAKMEN_PINDAAN")));
				h.put("no_enakmenAsal",Utils.isNull(rs.getString("NO_ENAKMEN_ASAL")));
				h.put("tarikh_kuatkuasa",
						rs.getDate("TARIKH_KUATKUASA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_KUATKUASA")));

				list.addElement(h);
				no++;
				count++;

			}


		}catch (Exception re) {
			 myLogger.error("Error: ", re);
			 throw re;
			 }
		  finally {
			if (db != null)
				db.close();
		}
		return list;
	}

	// TAMBAH ENAKMEN BARU
	public String addEnakmen(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		String output = "";
		try {
			long idEnakmen = DB.getNextID("TBLPDTENAKMENPINDA_SEQ");
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_ENAKMENPINDA", idEnakmen);
			r.add("NO_ENAKMEN_ASAL", data.get("noEnakmenAsal"));
			r.add("NO_ENAKMEN_PINDAAN", data.get("noEnakmen"));
			r.add("NAMA_ENAKMEN_PINDAAN", data.get("namaEnakmen"));
			r.add("TARIKH_KUATKUASA", r.unquote("to_date('"+ data.get("tarikhKuatkuasa")+ "','dd/MM/yyyy')"));
			r.add("TARIKH_MANSUH", r.unquote("to_date('"+ data.get("tarikhMansuh")+ "','dd/MM/yyyy')"));
			r.add("TARIKH_WARTA", r.unquote("to_date('"+ data.get("tarikhWarta")+ "','dd/MM/yyyy')"));
			r.add("TARIKH_DAFTAR", r.unquote("to_date('"+ data.get("tarikhDaftar")+ "','dd/MM/yyyy')"));

			r.add("NO_WARTA", data.get("noWarta"));
			r.add("ID_TARAFKESELAMATAN", data.get("idKeselamatan"));
			//r.add("ID_SEKSYEN", data.get("idSeksyen"));
			r.add("NO_FAIL", data.get("noFail"));
			r.add("CATATAN", data.get("catatan"));
			r.add("ID_MASUK", data.get("idMasuk"));
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPDTENAKMENPINDA");
			myLogger.info("insert TBLPDTENAKMENPINDA :"+sql);
			stmt.executeUpdate(sql);
			output = String.valueOf(idEnakmen);
					
			String tagDokumen = (String)data.get("tagDokumen");
			String idTagDokumen = (String)data.get("idTagDokumen");
			if(!tagDokumen.equals("")){
				db = new Db();
				stmt = db.getStatement();
			    r = new SQLRenderer();			      
			    sql = new String();
			    if(!idTagDokumen.equals("0")){
					r.update("ID_RUJTAG", data.get("idTagDokumen"));
					r.add("ID_KEMASKINI", data.get("idMasuk"));
					r.add("TAG_DOKUMEN",tagDokumen);
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql = r.getSQLUpdate("TBLRUJTAGDOKUMEN");
					myLogger.info("update TBLRUJTAGDOKUMEN : "+sql);							
				
				}else{
					long idTagDokumenBaru = DB.getNextID("TBLRUJTAGDOKUMEN_SEQ");
					r.add("ID_RUJTAG",idTagDokumenBaru);
					r.add("ID_DOKUMEN",idEnakmen);
					r.add("TAG_DOKUMEN",tagDokumen);
					r.add("SUMBER","ENAKMENP");
					r.add("TARIKH_MASUK",r.unquote("sysdate")); 
					r.add("ID_MASUK",data.get("idMasuk"));
					sql = r.getSQLInsert("TBLRUJTAGDOKUMEN"); 
					myLogger.info(" insert TBLRUJTAGDOKUMEN :  "+sql);
				
				}
				
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
		return output;
	}
	// PAPAR SENARAI ENAKMEN MENGIKUT ID
	public Hashtable getPaparEnakmenById(String id) throws Exception {

		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Hashtable h = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			r.add("a.id_Enakmenpinda");
			r.add("a.no_Enakmen_asal");
			r.add("a.no_Enakmen_pindaan");
			r.add("a.nama_Enakmen_pindaan");
			r.add("a.tarikh_Kuatkuasa");
			r.add("a.tarikh_Mansuh");
			r.add("a.no_Warta");
			r.add("a.tarikh_Warta");
			r.add("a.id_Fail");
			r.add("a.no_Fail");
			//r.add("a.id_Seksyen");
			r.add("a.catatan");
			r.add("a.tarikh_Daftar");
			r.add("NVL((SELECT TAG_DOKUMEN FROM TBLRUJTAGDOKUMEN WHERE ID_DOKUMEN=A.ID_ENAKMENPINDA AND SUMBER='ENAKMENP' ),'') TAG_DOKUMEN");
			r.add("NVL((SELECT ID_RUJTAG FROM TBLRUJTAGDOKUMEN WHERE ID_DOKUMEN=A.ID_ENAKMENPINDA AND SUMBER='ENAKMENP' ),'0') ID_DOKUMEN");
			r.add("a.id_Enakmenpinda", id);

			//sql = r.getSQLSelect("Tblpdtenakmen a,Tblrujseksyen b, Tblpfdfail c");
			 * *
			 */
			//sql = r.getSQLSelect("Tblpdtenakmenpinda a");
			
			sql = " SELECT A.JENIS_MIME AS DOC,A.ID_ENAKMENPINDA, A.NO_ENAKMEN_ASAL, A.NO_ENAKMEN_PINDAAN, "+
					" A.NAMA_ENAKMEN_PINDAAN, A.TARIKH_KUATKUASA, A.TARIKH_MANSUH, "+
					"  A.NO_WARTA, A.TARIKH_WARTA, A.ID_FAIL, A.NO_FAIL, A.CATATAN, "+
					"   A.TARIKH_DAFTAR, B.ID_DOKUMEN, B.TAG_DOKUMEN "+
					" FROM TBLPDTENAKMENPINDA A, TBLRUJTAGDOKUMEN B "+
					" WHERE A.ID_ENAKMENPINDA = '"+id+"' AND A.ID_ENAKMENPINDA = B.ID_DOKUMEN(+)  ";
			
			myLogger.info("Tblpdtenakmenpinda : "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				h=new Hashtable();
				h.put("idEnakmenPinda", Utils.isNull(rs.getString("id_Enakmenpinda")));
				h.put("noEnakmenAsal", Utils.isNull(rs.getString("no_Enakmen_asal")));
				h.put("noEnakmenPinda", Utils.isNull(rs.getString("no_Enakmen_Pindaan")));
				h.put("namaEnakmenPinda", Utils.isNull(rs.getString("nama_Enakmen_pindaan")));
				h.put("tarikhKuatkuasa",rs.getDate("tarikh_Kuatkuasa")==null?"":sdf.format(rs.getDate("tarikh_Kuatkuasa")) );
				h.put("tarikhMansuh",rs.getDate("tarikh_Mansuh")==null?"":sdf.format(rs.getDate("tarikh_Mansuh")) );
				h.put("noWarta", Utils.isNull(rs.getString("no_Warta")));
				h.put("tarikhWarta",rs.getDate("tarikh_Warta")==null?"":sdf.format(rs.getDate("tarikh_Warta")) );
				//h.put("sorTerbuka", Utils.isNull(rs.getString("id_Tarafkeselamatan")));
				//h.put("idseksyen", Utils.isNull(rs.getString("id_Seksyen")));
				//h.put("idFail", Utils.isNull(rs.getString("id_Fail")));
				h.put("noFail", Utils.isNull(rs.getString("no_Fail")));
				h.put("catatan", Utils.isNull(rs.getString("catatan")));
				h.put("tarikhDaftar",rs.getDate("tarikh_Daftar")==null?"":sdf.format(rs.getDate("tarikh_Daftar")) );
				h.put("tagging",  Utils.isNull(rs.getString("TAG_DOKUMEN")));
				h.put("idTagging",  Utils.isNull(rs.getString("ID_DOKUMEN")));
				h.put("DOC",  Utils.isNull(rs.getString("DOC")));
			
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
	
	public void updateEnakmenPinda(Hashtable data) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_ENAKMENPINDA", data.get("idEnakmenPinda"));
			r.add("NO_ENAKMEN_ASAL", data.get("noEnakmenAsal"));
			r.add("NO_ENAKMEN_PINDAAN", data.get("noEnakmenPinda"));
			r.add("NAMA_ENAKMEN_PINDAAN", data.get("namaEnakmenPinda"));
			//r.add("TARIKH_KUATKUASA", data.get("tarikhKuatkusa"));
			r.add("TARIKH_KUATKUASA", r.unquote("to_date('"+ data.get("tarikhKuatkusa")+ "','dd/MM/yyyy')"));
			r.add("TARIKH_DAFTAR",  r.unquote("to_date('"+ data.get("tarikhDaftar")+ "','dd/MM/yyyy')"));
			r.add("TARIKH_MANSUH",  r.unquote("to_date('"+ data.get("tarikhMansuh")+ "','dd/MM/yyyy')"));
			r.add("NO_FAIL", data.get("noFail"));
			r.add("ID_KEMASKINI", data.get("idKemaskini"));
			r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			r.add("CATATAN", data.get("catatan"));
			r.add("NO_WARTA", data.get("noWarta"));
			r.add("TARIKH_WARTA",  r.unquote("to_date('"+ data.get("tarikhWarta")+ "','dd/MM/yyyy')"));

			sql = r.getSQLUpdate("TBLPDTENAKMENPINDA");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
			
			
			String tagDokumen = (String)data.get("tagDokumen");
			String idTagDokumen = (String)data.get("idTagDokumen");
			if(!tagDokumen.equals("")){
				db = new Db();
				stmt = db.getStatement();
			    r = new SQLRenderer();			      
				if(!idTagDokumen.equals("0")){
					r.update("ID_RUJTAG", data.get("idTagDokumen"));
					r.add("ID_KEMASKINI", data.get("idKemaskini"));
					r.add("TAG_DOKUMEN",tagDokumen);
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql = r.getSQLUpdate("TBLRUJTAGDOKUMEN");								
				
				}else{
					long idTagDokumenBaru = DB.getNextID("TBLRUJTAGDOKUMEN_SEQ");
					r.add("ID_RUJTAG",idTagDokumenBaru);
					r.add("ID_DOKUMEN",data.get("idEnakmenPinda"));
					r.add("TAG_DOKUMEN",tagDokumen);
					r.add("TARIKH_MASUK",r.unquote("sysdate")); 
					r.add("ID_MASUK",data.get("idKemaskini"));
					sql = r.getSQLInsert("TBLRUJTAGDOKUMEN");  
				
				}
				myLogger.info(sql);
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
	
	public String formatDate(String f,SimpleDateFormat sdf) {
		if (f == null) {
			return "";
		} else {
			return sdf.format(f);
		}
	}
	
	public void Delete(String idEnakmenPinda) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_ENAKMENPINDA",idEnakmenPinda);
			sql = r.getSQLDelete("TBLPDTENAKMENPINDA");
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
	
}
