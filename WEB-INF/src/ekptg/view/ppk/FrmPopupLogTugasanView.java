//arief add for Status / Rekod pergerakan fail 6/4/2020

package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

public class FrmPopupLogTugasanView extends AjaxBasedModule {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public String doTemplate2() throws Exception {
		String idFail = getParam("idFail");
		context.put("tarikhPermohonan", getTarikhPermohonan(idFail));
		Hashtable tugasanSemasa = getTugasanSemasa(idFail);				
		context.put("tugasanSemasa", tugasanSemasa);
		Vector listLogTugasan = getSenaraiLogTugasan(idFail);
		this.context.put("listLogTugasan", listLogTugasan);
		return "app/ppk/frmPopupLogTugasan.jsp";
	}
	
	private String getTarikhPermohonan (String idFail) {
		String sql = "";
		String tarikhPermohonan = null;
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT TARIKH_MOHON FROM TBLPPKPERMOHONAN WHERE ID_FAIL = '" + idFail + "'";			
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				tarikhPermohonan = (rs.getDate("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		return tarikhPermohonan;
	}
	
	private Hashtable getTugasanSemasa(String idFail) {
		String sql = "";
		Hashtable tugasanSemasa = null;
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT PEGAWAI_SEBELUM.USER_NAME AS PEGAWAI_SEBELUM, PEGAWAI_TUGASAN.USER_NAME AS PEGAWAI_TUGASAN, TBLPPKLOGTUGASAN.ROLE,"
					+ " TBLPPKLOGTUGASAN.TARIKH_DITUGASKAN, TBLPPKLOGTUGASAN.CATATAN"
					+ " FROM TBLPPKLOGTUGASAN, USERS PEGAWAI_SEBELUM, USERS PEGAWAI_TUGASAN"
					+ " WHERE TBLPPKLOGTUGASAN.ID_PEGAWAI_SEBELUM = PEGAWAI_SEBELUM.USER_ID(+)"
					+ " AND TBLPPKLOGTUGASAN.ID_PEGAWAI = PEGAWAI_TUGASAN.USER_ID (+)"
					+ " AND TBLPPKLOGTUGASAN.FLAG_AKTIF = 'Y' AND TBLPPKLOGTUGASAN.ID_FAIL = '" + idFail + "'";			
			sql = sql + " ORDER BY TBLPPKLOGTUGASAN.TARIKH_DITUGASKAN DESC";			
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {	
				tugasanSemasa = new Hashtable();
				tugasanSemasa.put("tugasanDari", rs.getString("PEGAWAI_SEBELUM") == null ? "" : rs.getString("PEGAWAI_SEBELUM"));
				tugasanSemasa.put("pegawaiTindakan", rs.getString("PEGAWAI_TUGASAN") == null ? "" : rs.getString("PEGAWAI_TUGASAN"));
				tugasanSemasa.put("rolePegawaiTindakan", rs.getString("ROLE") == null ? "" : rs.getString("ROLE"));
				tugasanSemasa.put("tarikhTugasan", rs.getDate("TARIKH_DITUGASKAN") == null ? "" : sdf.format(rs.getDate("TARIKH_DITUGASKAN")));
				tugasanSemasa.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
			}	
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		return tugasanSemasa;
	}
	
	private Vector getSenaraiLogTugasan(String idFail) {
		String sql = "";
		Vector listLogTugasan = null;
		Hashtable h;
		Db db = null;
		try {
			listLogTugasan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT PEGAWAI_SEBELUM.USER_NAME AS PEGAWAI_SEBELUM, PEGAWAI_TUGASAN.USER_NAME AS PEGAWAI_TUGASAN, TBLPHPLOGTUGASAN.ROLE,"
					+ " TBLPPKLOGTUGASAN.TARIKH_DITUGASKAN"
					+ " FROM TBLPPKLOGTUGASAN, USERS PEGAWAI_SEBELUM, USERS PEGAWAI_TUGASAN"
					+ " WHERE TBLPPKLOGTUGASAN.ID_PEGAWAI_SEBELUM = PEGAWAI_SEBELUM.USER_ID(+)"
					+ " AND TBLPPKLOGTUGASAN.ID_PEGAWAI = PEGAWAI_TUGASAN.USER_ID (+)"
					+ " AND TBLPPKLOGTUGASAN.FLAG_AKTIF = 'T' AND TBLPPKLOGTUGASAN.ID_FAIL = '" + idFail + "'";			
			sql = sql + " ORDER BY TBLPPKLOGTUGASAN.TARIKH_DITUGASKAN DESC";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				h = new Hashtable();
				h.put("tugasanDari", rs.getString("PEGAWAI_SEBELUM") == null ? "" : rs.getString("PEGAWAI_SEBELUM"));
				h.put("pegawaiTindakan", rs.getString("PEGAWAI_TUGASAN") == null ? "" : rs.getString("PEGAWAI_TUGASAN"));
				h.put("rolePegawaiTindakan", rs.getString("ROLE") == null ? "" : rs.getString("ROLE"));
				h.put("tarikhTugasan", rs.getDate("TARIKH_DITUGASKAN") == null ? "" : sdf.format(rs.getDate("TARIKH_DITUGASKAN")));
				listLogTugasan.addElement(h);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		return listLogTugasan;
	}
}
