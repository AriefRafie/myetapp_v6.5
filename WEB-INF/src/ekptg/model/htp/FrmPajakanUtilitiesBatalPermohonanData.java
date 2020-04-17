/**
 * 
 */
package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;


/**
 * @author Firzan.Fir
 *
 */
public class FrmPajakanUtilitiesBatalPermohonanData {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FrmPajakanUtilitiesBatalPermohonanData.class);
	private Db db = null;
	private Connection conn = null;
	private Vector senaraiFail = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	public void carianFail(String noFail, String tarikhTerima) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C"
				+ " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND A.ID_SEKSYEN = '3' AND A.ID_URUSAN = '3' AND A.ID_SUBURUSAN IN (7, 17, 18)";
				
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			
			//tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
				}
			}
						
			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			
			log.info("sql utilities : " + sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void BatalPermohonan(String[] cbBatal, HttpSession session)throws Exception{
		String sql = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			for(int i=0; i < cbBatal.length; i++){
				String idFail = cbBatal[i];
				log.info("idFail utk batal : " + idFail);
				
				
				
				
				//TBLPERMOHONAN
				SQLRenderer r = new SQLRenderer();
				r.update("ID_FAIL", idFail);
				r.add("ID_STATUS", "63");
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				
			}
			
			
			
			//conn.commit();
			
		}catch(Exception e){
			conn.rollback();
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
			if(conn != null)
				conn.close();
		}
	}
	
	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}

}
