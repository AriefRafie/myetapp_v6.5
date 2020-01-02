/**
 * 
 */
package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

/**
 * @author mohd faizal
 *
 */
public class FrmIntergrasiSenaraiMaklumatCukaiEtanahData {
	
	private Vector senaraiMaklumatCukai = null;	
	private Vector beanMaklumatCukai = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void carianMaklumatCukai(String idHakmilik, String tahun) throws Exception {

		Db db = null;
		String sql = "";

		try {
			senaraiMaklumatCukai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT *"
					
				+ " FROM INT_HTPMAKLUMATCUKAI MC"
				
				+ " WHERE MC.ID_HAKMILIK IS NOT NULL";
			
			//idHakmilik
			if (idHakmilik != null) {
				if (!idHakmilik.trim().equals("")) {
					sql = sql + " AND MC.ID_HAKMILIK = '" + idHakmilik.trim().toUpperCase() + "'";
				}
			}
			
			//tahun
			if (tahun != null) {
				if (!tahun.trim().equals("")) {
					sql = sql + " AND MC.TAHUN = '" + tahun.trim().toUpperCase() + "'";
				}
			}
						
			sql = sql + " ORDER BY MC.ID_HAKMILIK ASC";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("tahun", rs.getString("TAHUN") == null ? "" : rs.getString("TAHUN"));
				
				h.put("cukaiKenaBayar", rs.getString("CUKAI_KENA_BAYAR") == null ? "" : rs.getString("CUKAI_KENA_BAYAR"));
				h.put("cukaiPerluBayar", rs.getString("CUKAI_PERLU_BAYAR") == null ? "" : rs.getString("CUKAI_PERLU_BAYAR"));
				h.put("cukaiDibayar", rs.getString("CUKAI_DIBAYAR") == null ? "" : rs.getString("CUKAI_DIBAYAR"));	
				h.put("cukaiParit", rs.getString("CUKAI_PARIT") == null ? "" : rs.getString("CUKAI_PARIT"));
				h.put("cukaiTaliAir", rs.getString("CUKAI_TALI_AIR") == null ? "" : rs.getString("CUKAI_TALI_AIR"));		
				h.put("denda", rs.getString("DENDA") == null ? "" : rs.getString("DENDA"));
				h.put("pengecualian", rs.getString("PENGECUALIAN") == null ? "" : rs.getString("PENGECUALIAN"));
				h.put("pengurangan", rs.getString("PENGURANGAN") == null ? "" : rs.getString("PENGURANGAN"));
				h.put("bayaranLain", rs.getString("BAYARAN_LAIN") == null ? "" : rs.getString("BAYARAN_LAIN"));
				h.put("tunggakan", rs.getString("TUNGGAKAN") == null ? "" : rs.getString("TUNGGAKAN"));					
				
				senaraiMaklumatCukai.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}		
	}
	
	public Vector getSenaraiMaklumatCukai() {
		return senaraiMaklumatCukai;
	}

	public void setSenaraiMaklumatCukai(Vector senaraiMaklumatCukai) {
		this.senaraiMaklumatCukai = senaraiMaklumatCukai;
	}

	public Vector getBeanMaklumatCukai() {
		return beanMaklumatCukai;
	}

	public void setBeanMaklumatCukai(Vector beanMaklumatCukai) {
		this.beanMaklumatCukai = beanMaklumatCukai;
	}

}
