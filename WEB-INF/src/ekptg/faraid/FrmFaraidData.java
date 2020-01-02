package ekptg.faraid;


	
	import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

	public class FrmFaraidData {
		
		private Vector beanMaklumatSimati = new Vector();

		public void setMaklumatSimati(String idPermohonan) throws Exception {
			Db db = null;
			beanMaklumatSimati.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.add("A.NAMA_SIMATI");
				r.add("A.NO_KP_BARU");
				r.add("A.NO_KP_LAMA");
				r.add("A.TARIKH_MATI");
				r.add("B.ID_PERMOHONAN");
				r.add("A.ID_SIMATI");
				r.add("D.NO_FAIL");
				
				r.add("A.ID_SIMATI",r.unquote("B.ID_SIMATI"));
				r.add("B.ID_PERMOHONAN", r.unquote("C.ID_PERMOHONAN"));
				r.add("C.ID_FAIL",r.unquote("D.ID_FAIL"));
				
				r.add("C.ID_PERMOHONAN",idPermohonan);

				sql = r.getSQLSelect("TBLPPKSIMATI A, TBLPPKPERMOHONANSIMATI B, TBLPPKPERMOHONAN C, TBLPFDFAIL D");
				ResultSet rs = stmt.executeQuery(sql);

				Hashtable h;
				int bil = 1;
				Integer count = 0;

				while (rs.next()) {
					h = new Hashtable();
					
					h.put("idPermohonan", rs.getString("ID_PERMOHONAN"));
					h.put("idSimati", rs.getString("ID_SIMATI"));
					h.put("namaSimati",rs.getString("NAMA_SIMATI"));
					h.put("noKPBaru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
					h.put("noKPLama", rs.getString("NO_KP_LAMA")==null?"":rs.getString("NO_KP_LAMA"));
					h.put("tarikhMati",rs.getDate("TARIKH_MATI")==null?"":sdf.format(rs.getDate("TARIKH_MATI")));
					h.put("noFail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					
					
					beanMaklumatSimati.addElement(h);
					bil++;
					count++;
				}
				
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		public Vector getBeanMaklumatSimati() {
			return beanMaklumatSimati;
		}

		public void setBeanMaklumatSimati(Vector beanMaklumatSimati) {
			this.beanMaklumatSimati = beanMaklumatSimati;
		}
}
