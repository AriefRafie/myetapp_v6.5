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

public class FrmDaftarEnakmenData {
	private static Logger myLogger = Logger.getLogger(FrmDaftarEnakmenData.class);
	private static Vector listEnakmenAll = null;
	private static Vector listCarianPaparEnakmen = null;
	
	
	// CARIAN ENAKMEN DAN PAPAR SEMUA ENAKMEN
	public Vector getCarianPaparEnakmen(String noEnakmen,
			String namaEnakmen, String tarikhKuatkuasa) throws Exception {
		Db db = null;
				
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			listCarianPaparEnakmen = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ENAKMEN,A.NO_ENAKMEN,A.NAMA_ENAKMEN,A.TARIKH_KUATKUASA"
					+ " FROM TBLPDTENAKMEN A WHERE A.ID_ENAKMEN IS NOT NULL";

			// NO ENAKMEN
			if (noEnakmen != null) {
				if (!noEnakmen.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_ENAKMEN) LIKE '%' ||'"
							+ noEnakmen.toUpperCase() + "'|| '%'";
				}
			}
			// NAMA ENAKMEN
			if (namaEnakmen != null) {
				if (!namaEnakmen.trim().equals("")) {
					sql = sql + " AND UPPER(A.NAMA_ENAKMEN) LIKE '%' ||'"
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

			sql = sql + " ORDER BY A.ID_ENAKMEN";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("id_enakmen", rs.getString("ID_ENAKMEN"));
				h.put("no_enakmen", rs.getString("NO_ENAKMEN") == null ? ""
						: rs.getString("NO_ENAKMEN"));
				h.put("nama_enakmen", rs.getString("NAMA_ENAKMEN") == null ? ""
						: rs.getString("NAMA_ENAKMEN"));
				h.put("tarikh_kuatkuasa",
						rs.getDate("TARIKH_KUATKUASA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_KUATKUASA")));

				listCarianPaparEnakmen.addElement(h);
				no++;
				count++;

			}


		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
		return listCarianPaparEnakmen;
	}

	// TAMBAH ENAKMEN BARU
	public String addEnakmen(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		String output = "";
		try {
			long idEnakmen = DB.getNextID("TBLPDTENAKMEN_SEQ");
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_ENAKMEN", idEnakmen);
			r.add("NO_ENAKMEN", data.get("noEnakmen"));
			r.add("NAMA_ENAKMEN", data.get("namaEnakmen"));
			r.add("TARIKH_KUATKUASA", r.unquote("to_date('"+ data.get("tarikhKuatkuasa")+ "','dd/MM/yyyy')"));
			r.add("TARIKH_MANSUH", r.unquote("to_date('"+ data.get("tarikhMansuh")+ "','dd/MM/yyyy')"));
			r.add("TARIKH_WARTA", r.unquote("to_date('"+ data.get("tarikhWarta")+ "','dd/MM/yyyy')"));
			r.add("TARIKH_DAFTAR", r.unquote("to_date('"+ data.get("tarikhDaftar")+ "','dd/MM/yyyy')"));

			r.add("NO_WARTA", data.get("noWarta"));
			r.add("ID_TARAFKESELAMATAN", data.get("idKeselamatan"));
			r.add("ID_SEKSYEN", data.get("idSeksyen"));
			r.add("NO_FAIL", data.get("noFail"));
			r.add("CATATAN", data.get("catatan"));
			r.add("ID_MASUK", data.get("idMasuk"));
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPDTENAKMEN");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
			output = String.valueOf(idEnakmen);
		}catch (Exception re) {
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
			
			r.add("a.id_Enakmen");
			r.add("a.no_Enakmen");
			r.add("a.nama_Enakmen");
			r.add("a.tarikh_Kuatkuasa");
			r.add("a.tarikh_Mansuh");
			r.add("a.no_Warta");
			r.add("a.tarikh_Warta");
			r.add("a.id_Fail");
			r.add("a.no_Fail");
			r.add("a.id_Seksyen");
			r.add("a.catatan");
			r.add("a.tarikh_Daftar");
			r.add("a.id_Tarafkeselamatan");

			r.add("a.id_Enakmen", id);

			//sql = r.getSQLSelect("Tblpdtenakmen a,Tblrujseksyen b, Tblpfdfail c");
			sql = r.getSQLSelect("Tblpdtenakmen a");
			myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				h=new Hashtable();
				h.put("idEnakmen", Utils.isNull(rs.getString("id_Enakmen")));
				h.put("noEnakmen", Utils.isNull(rs.getString("no_Enakmen")));
				h.put("namaEnakmen", Utils.isNull(rs.getString("nama_Enakmen")));
				h.put("tarikhKuatkuasa",rs.getDate("tarikh_Kuatkuasa")==null?"":sdf.format(rs.getDate("tarikh_Kuatkuasa")) );
				h.put("tarikhMansuh",rs.getDate("tarikh_Mansuh")==null?"":sdf.format(rs.getDate("tarikh_Mansuh")) );
				h.put("noWarta", Utils.isNull(rs.getString("no_Warta")));
				h.put("tarikhWarta",rs.getDate("tarikh_Warta")==null?"":sdf.format(rs.getDate("tarikh_Warta")) );
				h.put("sorTerbuka", Utils.isNull(rs.getString("id_Tarafkeselamatan")));
				h.put("idseksyen", Utils.isNull(rs.getString("id_Seksyen")));
				h.put("idFail", Utils.isNull(rs.getString("id_Fail")));
				h.put("noFail", Utils.isNull(rs.getString("no_Fail")));
				h.put("catatan", Utils.isNull(rs.getString("catatan")));
				h.put("tarikhDaftar",rs.getDate("tarikh_Daftar")==null?"":sdf.format(rs.getDate("tarikh_Daftar")) );
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
	
	public String formatDate(String f,SimpleDateFormat sdf) {
		if (f == null) {
			return "";
		} else {
			return sdf.format(f);
		}
	}
	
}
