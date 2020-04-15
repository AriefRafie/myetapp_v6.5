/**
 * 
 */
package integrasi.rest.etanah.wpkl;

import integrasi.rest.etanah.wpkl.entities.Hakmilik;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

/**
 * @author mohd faizal
 *
 */
public class FrmPopupSemakanHakmilikEtanahView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		String idPengguna = (String)session.getAttribute("_portal_username");
		
		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "app/integrasi/etanah/wpkl/frmSemakanMaklumatHakmilikEtanah.jsp";
		String actionPopup = getParam("actionPopup");
		String hitButt = getParam("hitButt");
		String submit = getParam("command");
		
		String flagMsg = "";
		String outputMsg = "";
		
		String idHakmilik = getParam("idHakmilik").trim();
		this.context.put("idHakmilik", idHakmilik);
		String flagModul = getParam("flagModul").trim();
		this.context.put("flagModul", flagModul);
		
		Hakmilik hakmilik = null;
		
		if ("PPT".equals(flagModul)) {
			hakmilik = RESTInvoker.getMaklumatHakmilik(idPengguna, getUPIIdHakmilikPPT(idHakmilik), "");
		}
		
		if (hakmilik == null) {
			flagMsg = "T";
			outputMsg = "HAKMILIK TIDAK WUJUD DI SISTEM E-TANAH";
		} else {
			if ("BATAL".equalsIgnoreCase(hakmilik.getStatusHakmilik())) {
				flagMsg = "T";
				outputMsg = "STATUS HAKMILIK ADALAH BATAL DI SISTEM E-TANAH";
			}
		}
		this.context.put("hakmilik", hakmilik);
		
        this.context.put("flagMsg", flagMsg);
		this.context.put("outputMsg", outputMsg);		
		
		return vm;
	}
	
	private String getUPIIdHakmilikPPT(String idPPTHakmilik) {
		String sql = "";
		String idHakmilik = "";
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT TBLRUJNEGERI.KOD_NEGERI || TBLRUJDAERAH.KOD_DAERAH || TBLRUJMUKIM.KOD_MUKIM ||"
					+ " TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK || TRIM(TO_CHAR(TBLPPTHAKMILIK.NO_HAKMILIK,'00000000')) AS ID_HAKMILIK"
					+ " FROM TBLPPTHAKMILIK, TBLRUJNEGERI, TBLRUJMUKIM, TBLRUJDAERAH, TBLRUJJENISHAKMILIK"
					+ " WHERE TBLPPTHAKMILIK.ID_NEGERI = TBLRUJNEGERI.ID_NEGERI(+) AND TBLPPTHAKMILIK.ID_MUKIM = TBLRUJMUKIM.ID_MUKIM(+) AND TBLPPTHAKMILIK.ID_DAERAH = TBLRUJDAERAH.ID_DAERAH(+)"
					+ " AND TBLPPTHAKMILIK.ID_JENISHAKMILIK = TBLRUJJENISHAKMILIK.ID_JENISHAKMILIK(+) AND TBLPPTHAKMILIK.ID_HAKMILIK = '" + idPPTHakmilik + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				idHakmilik = rs.getString("ID_HAKMILIK");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return idHakmilik;
	}
}
