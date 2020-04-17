package ekptg.view.ppk;

import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

public class FrmPertanyaan extends AjaxBasedModule{
	
	static Logger myLogger = Logger.getLogger(FrmPertanyaan.class);
	private static boolean debugmode = true;
	
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String submit = getParam("command"); //First level - from AjaxBasedModule
		String id_permohonan = getParam("id_permohonan");
		String id_simati = getParam("id_simati");
		String vm = "";
		
		String action = getParam("action");
		

		//myLogger.info("id_permohonan:"+id_permohonan);
		//myLogger.info("command:"+submit);
		
		if (id_permohonan == null || "".equals(id_permohonan)) { //Tiada id_permohonan,assuming from searching.
			vm = "app/ppk/frmPertanyaan.jsp";
			
			if ("doCarian".equals(submit)) {
				String txtNoFail = getParam("txtNoFail");
				//myLogger.info(txtNoFail);
				Vector v = this.getFailList(txtNoFail, user_id);
				this.context.put("lists", v);
			}
			
		} else {
		
		vm = "app/ppk/frmFaraid.jsp";
		//this.context.put("wait","true");
		//myLogger.info("submit:"+submit);
		if ("".equals(submit)) {
			this.context.put("wait","true");
		} else {

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
					" ";
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_id+"  ";
					sql += " ) "+
					"AND UPPER(A.NO_FAIL) LIKE '"+no_fail.toUpperCase()+"%' "+
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
	
	public static String getSQLWhere(Hashtable h) {
		 String fieldname="",value="",output = "";
		 int x=0;
		 for (Enumeration e = h.keys(); e.hasMoreElements();) {
			 fieldname = (String)e.nextElement();
			 value = (String)h.get(fieldname);
			 if (value != null || value != "") {
				 x++;
				 output = output + " UPPER("+fieldname+") LIKE '%"+value.toUpperCase()+"%'" + (x<h.size()? " OR ":"");
			 }
		  }
		 return output;
	}

}
