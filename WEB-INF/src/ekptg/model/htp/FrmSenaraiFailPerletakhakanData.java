package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmSenaraiFailPerletakhakanData {

	private static Vector list = new Vector();

	public static void setList(String idUrusan, String carian, String noFail,
			Long idNegeri) throws Exception {
		Db db = null;
		list.clear();
		String sql = "";
		String Like = "";
		if (idNegeri == 20)
			idNegeri = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT distinct f.id_Fail, f.no_Fail, f.tajuk_Fail, s.keterangan, n.nama_Negeri, n.kod_Mampu ";
			sql += "FROM Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n ";
			sql += "WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri ";
			sql += "AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
			sql += "AND sf.aktif = '1' AND f.id_Urusan = " + idUrusan
					+ " AND f.tajuk_Fail LIKE '%" + carian + "%' ";
			sql += "AND f.no_Fail LIKE '%" + noFail + "%' ";
			if (idNegeri != null)
				sql += "AND f.id_Negeri = " + idNegeri;
			sql += " ORDER BY n.kod_Mampu";
			/*
			 * SQLRenderer r = new SQLRenderer();
			 * 
			 * r.add("p.id_Fail"); r.add("f.no_Fail"); r.add("f.tajuk_Fail");
			 * r.add("n.nama_Negeri"); r.add("s.keterangan");
			 * 
			 * r.add("p.id_Fail",r.unquote("f.id_Fail"));
			 * r.add("p.id_Permohonan",r.unquote("sf.id_Permohonan"));
			 * r.add("sf.id_Suburusanstatus"
			 * ,r.unquote("us.id_Suburusanstatus"));
			 * r.add("us.id_Status",r.unquote("s.id_Status"));
			 * r.add("f.id_Negeri",r.unquote("n.id_Negeri"));
			 * 
			 * r.add("f.id_Urusan","108"); r.add("sf.aktif","1");
			 * 
			 * sql =r.getSQLSelect(
			 * "Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s, Tblrujnegeri n"
			 * );
			 */
			ResultSet rs = stmt.executeQuery(sql);
			

			// Vector list = new Vector();
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id", rs.getString("id_Fail"));
				h.put("no", rs.getString("no_Fail"));
				h.put("tajuk", rs.getString("tajuk_Fail"));
				h.put("negeri", rs.getString("nama_Negeri"));
				h.put("keterangan", rs.getString("keterangan"));
				h.put("kodMampu", rs.getString("kod_Mampu"));
				list.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getList() {
		System.out.println(list.size());
		return list;
	}

}
