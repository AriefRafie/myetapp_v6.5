package ekptg.faraid;

import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.faraid.EkptgEngine.Simati;

public class FrmAkta1958 extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(FrmAkta1958.class);
	private static boolean debugmode = true;
	
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String submit = getParam("command"); //First level - from AjaxBasedModule
		String id_permohonan = getParam("id_permohonan");
		String id_simati = getParam("id_simati");
		String vm = "";
		
		String action = getParam("action");
		
		if (id_permohonan == null || "".equals(id_permohonan)) { //Tiada id_permohonan,assuming from searching.
			vm = "app/ppk/frmAkta1958Carian.jsp";
			
			if ("doCarian".equals(submit) || 
				"doChanges".equals(submit)) {
				String txtNoFail = getParam("txtNoFail");
				//myLogger.info(txtNoFail);
				Vector v = this.getFailList(txtNoFail, user_id);
				setupPage(session,action,v);
				//this.context.put("lists", v);
			} else {
				//this.context.put("lists","");
				setupPage(session,action,null);
			}
			
		} else {
		
		vm = "app/ppk/frmAkta1958.jsp";
		//this.context.put("wait","true");
		//myLogger.info("submit:"+submit);
		if ("".equals(submit)) {
			this.context.put("wait","true");
		} else {
			this.context.put("wait","false");
			long start = System.currentTimeMillis(); 
			
			
			EkptgEngine ekptgEngine = new EkptgEngine();
			Simati sm = ekptgEngine.new Simati();
			Vector hasilFaraid = new Vector();
			Akta1958 a = new Akta1958();
			hasilFaraid = a.doAllAkta1958Processing(ekptgEngine,sm,id_simati,id_permohonan);
			
			// Everything is been done here..
			//hasilFaraid = ekptgEngine.doAllAkta1958Processing(id_simati,id_permohonan,ekptgEngine,sm);
			
			//Simati Information
		 	this.context.put("namaSiMati", sm.getNamaSimati());
		 	this.context.put("noKp", sm.getNoKp());
		 	this.context.put("tarikhMati",sm.getTarikhMati());
		 	this.context.put("idSimati",sm.getId_simati());
		 	this.context.put("idPermohonanSimati",sm.getId_permohonansimati());
		 	this.context.put("lapisan",sm.getLapisan());
		 	//-------------
		 	
		 	this.context.put("Senarai",hasilFaraid);
		 	
			this.context.put("idPermohonan",id_permohonan);
		    this.context.put("action",action);
		    
		    long elapsedTimeMillis = System.currentTimeMillis() - start; 
		    float elapsedTimeSecond = elapsedTimeMillis/1000f; 
		    this.context.put("timetaken",elapsedTimeSecond);
		    this.context.put("detailFaraidCalculation",ekptgEngine.getHTMLBuilder());
			}
		}
		return vm;	
		
	}
	
	public Vector getFailList(String no_fail,String user_id) throws Exception {
		Db db = null;
		Vector v = null;
		String sql = "";
		try {
			db = new Db();
			v = new Vector();
			sql = "select D.ID_SIMATI,D.ID_PERMOHONANSIMATI,A.NO_FAIL,B.ID_PERMOHONAN,E.NAMA_SIMATI,C.NAMA_PEMOHON "+
					"from TBLPFDFAIL A,Tblppkpermohonan B,Tblppkpemohon C,  "+
					"Tblppkpermohonansimati D,Tblppksimati E "+
					"WHERE A.ID_FAIL = B.ID_FAIL "+
					"AND NVL(A.ID_STATUS,0) NOT IN ('999') "+
					"AND B.id_daerahmhn IN ( "+
					"select distinct u.id_daerahurus from  TBLRUJPEJABATURUSAN u, users_internal ur  "+
					"where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+user_id+"' "+
					") "+
					"AND UPPER(A.NO_FAIL) LIKE '%"+no_fail.toUpperCase()+"%' "+
					"AND B.ID_PEMOHON = C.ID_PEMOHON "+ 
					"AND B.ID_PERMOHONAN = D.ID_PERMOHONAN "+
					"AND D.ID_SIMATI = E.ID_SIMATI "+
					"order by B.TARIKH_KEMASKINI";
			
			ResultSet rs = db.getStatement().executeQuery(sql);
			if (debugmode)myLogger.info(sql);
			//ekptgEngine.setHTMLBuilder(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("NO_FAIL",rs.getString("NO_FAIL"));
				h.put("NAMA_SIMATI",rs.getString("NAMA_SIMATI"));
				h.put("NAMA_PEMOHON",rs.getString("NAMA_PEMOHON"));
				h.put("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN"));
				h.put("ID_PERMOHONANSIMATI",rs.getString("ID_PERMOHONANSIMATI"));
				h.put("ID_SIMATI",rs.getString("ID_SIMATI"));
				v.add(h);
			}
			
		} catch (Exception e) {
			throw new Exception("Error in getting Fail List");
		} finally {
			if (db != null) db.close();
		}
		return v;
	}

}
