package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class FrmUtilData {
	
	//private static Vector list = new Vector();
	//private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	Date now = new Date();
	static Logger mylog = Logger.getLogger(FrmUtilData.class);
	
	@SuppressWarnings("unchecked")
	public static Vector<Hashtable<String, Comparable>> getUnitPPK() throws Exception {
		Db db = null;
		String sql = "SELECT A.ID_PEJABATJKPTG, B.ID_DAERAH, B.KOD_DAERAH, B.NAMA_DAERAH, A.NAMA_PEJABAT, A.KOD_JKPTG, A.ALAMAT1"+
					 " FROM TBLRUJPEJABATJKPTG A, TBLRUJDAERAH B " +
					 " WHERE A.ID_SEKSYEN = 2 AND A.ID_JENISPEJABAT = 22 AND A.ID_DAERAH = B.ID_DAERAH" +
					 " ORDER BY A.ID_NEGERI,B.ID_DAERAH";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
			Hashtable<String, Comparable> h;
			while (rs.next()) {
				h = new Hashtable<String, Comparable>();
				h.put("id",rs.getLong("ID_PEJABATJKPTG"));
				h.put("kod",rs.getString("KOD_JKPTG") == null ? "" : rs.getString("KOD_JKPTG"));
				h.put("keterangan", Utils.RemoveSymbol(rs.getString("NAMA_PEJABAT").toUpperCase()));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

}
