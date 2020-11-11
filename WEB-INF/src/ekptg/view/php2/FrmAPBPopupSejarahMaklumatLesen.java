package ekptg.view.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ekptg.model.php2.FrmAPBPopupSejarahMaklumatLesenData;
import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

public class FrmAPBPopupSejarahMaklumatLesen extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(FrmAPBPopupSejarahMaklumatLesen.class);
	FrmAPBPopupSejarahMaklumatLesenData logic = new FrmAPBPopupSejarahMaklumatLesenData();
	
	public String doTemplate2() throws Exception {
		
		Vector<Hashtable<String,String>> listSyarikat = null;
		HttpSession session = this.request.getSession();
		    
		String action = getParam("action");
		String actionPopup = getParam("actionPopup");
		String paparDetail = getParam("paparDetail");
		
		String idFail = getParam("idFail");
	    String noLesen = getParam("txtCarianNoLesen");
	    String noFail = getParam("txtCarianNoFail");
	    String noFailBaru = "";
	    String idPermohonan=logic.getIdPermohonan(idFail);
	    
		this.context.put("paparDetail",paparDetail);

		//VECTOR
        Vector beanMaklumatPermohonan = null;
        Vector beanMaklumatSuratKelulusanLesenKepadaPemohon = null;

    	if ("doPaparMaklumatDetail".equals(actionPopup)){
    		
    		beanMaklumatPermohonan = new Vector();
            logic.setMaklumatPermohonan(idFail);
            beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
    		this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
    		
    		beanMaklumatSuratKelulusanLesenKepadaPemohon = new Vector();
    		
            logic.setMaklumatSuratKelulusanLesenKepadaPemohon(idPermohonan);
            beanMaklumatSuratKelulusanLesenKepadaPemohon = logic.getBeanMaklumatSuratKelulusanLesenKepadaPemohon();
    		this.context.put("BeanMaklumatSuratKelulusanLesenKepadaPemohon", beanMaklumatSuratKelulusanLesenKepadaPemohon);	
    		this.context.put("paparDetail",paparDetail);
    		this.context.put("mode", "view");
    	}
   
		
	    String vm = "app/php2/frmPopupSejarahMaklumatLesen.jsp";
	    context.put("SenaraiFailSyarikat","");

	    noFail = getNoFailByIdFail(idFail);
	    
	    if(noFail.contains("JLD")) {
	    	noFailBaru = getNoFailBaru(noFail.trim());
	    } else {
	    	noFailBaru = noFail.trim();
	    }
	   
	    listSyarikat = new Vector<Hashtable<String,String>>();
	    listSyarikat = logic.getCarianFailSyarikat(noFailBaru,noLesen);
	    
		this.context.put("SenaraiFailSyarikat", listSyarikat);
		context.put("totalRecords",listSyarikat.size());
		
		setupPage(session,action,listSyarikat);
		return vm;
	}
	
	private String getNoFailByIdFail(String idFail) 
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toString();

			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private String getNoFailBaru(String noFail) 
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT SUBSTR('"+noFail+"',0,(INSTR('"+noFail+"','JLD')-2)) AS NO_FAIL_BARU "
				+ "FROM TBLPFDFAIL WHERE ID_URUSAN = '9' AND ID_SUBURUSAN = '57' "
				+ "AND NO_FAIL = '" + noFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("no fail baru : " +sql);

			if (rs.next()) {
				return rs.getString("NO_FAIL_BARU") == null ? "" : rs.getString("NO_FAIL_BARU").toString();

			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
}
