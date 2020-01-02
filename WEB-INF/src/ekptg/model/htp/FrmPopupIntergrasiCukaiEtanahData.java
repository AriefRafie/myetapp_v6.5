/**
 * 
 */
package ekptg.model.htp;

import integrasi.ws.etanah.melaka_ns.htp.EtanahHTPManager;

/**
 * @author mohd faizal
 *
 */
public class FrmPopupIntergrasiCukaiEtanahData {

	public void getMaklumatCukai(String idHakmilik, String tahun) throws Exception {
		EtanahHTPManager.getMaklumatCukai(idHakmilik, tahun, idHakmilik.substring(0, 2));
//		Db db = null;
//		String sql = "";
//
//		try {
//			
//			db = new Db();
//			Statement stmt = db.getStatement();
//
//			sql = "SELECT HM.PEGANGAN_HAKMILIK, NEGERI.KOD_NEGERI FROM TBLHTPHAKMILIK HM, TBLRUJNEGERI NEGERI WHERE HM.ID_NEGERI = NEGERI.ID_NEGERI AND HM.STATUS_SAH = 'D'";
//			
//			ResultSet rs = stmt.executeQuery(sql);
//
//			while (rs.next()) {
//				EtanahHTPManager.getMaklumatCukai(rs.getString("PEGANGAN_HAKMILIK"), tahun, rs.getString("KOD_NEGERI"));
//			}
//
//		} finally {
//			if (db != null)
//				db.close();
//		}		
	}
	
	public void getMaklumatBayaran(String idHakmilik, String tahun) throws Exception {
		EtanahHTPManager.getMaklumatBayaran(idHakmilik, tahun, idHakmilik.substring(0, 2));
//		Db db = null;
//		String sql = "";
//
//		try {
//			
//			db = new Db();
//			Statement stmt = db.getStatement();
//
//			sql = "SELECT ID_HAKMILIK FROM INT_HTPMAKLUMATCUKAI WHERE TAHUN = '" + tahun + "'";
//			
//			ResultSet rs = stmt.executeQuery(sql);
//
//			while (rs.next()) {
//				EtanahHTPManager.getMaklumatCukai(rs.getString("ID_HAKMILIK"), tahun, rs.getString("ID_HAKMILIK").substring(0, 2));
//			}
//
//		} finally {
//			if (db != null)
//				db.close();
//		}		
	}

}
