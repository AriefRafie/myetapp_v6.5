package ekptg.faraid;

import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.faraid.EkptgEngine.Simati;
import ekptg.faraid.EkptgEngine.Waris;
import ekptg.helpers.Utils;

public class FrmFaraid extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(FrmFaraid.class);
	private static boolean debugmode = true;
	
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String submit = getParam("command"); //First level - from AjaxBasedModule
		String id_permohonan = getParam("id_permohonan");
		String id_simati = getParam("id_simati");
		String id_permohonansimati = getParam("id_permohonansimati");
		String vm = "";
		
		String action = getParam("action");
		

		//myLogger.info("id_permohonan:"+id_permohonan);
		//myLogger.info("command:"+submit);
		
		if (id_permohonan == null || "".equals(id_permohonan)) { //Tiada id_permohonan,assuming from searching.
			vm = "app/ppk/frmFaraidCarian.jsp";
			
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
		
		//vm = "app/ppk/frmFaraid.jsp";
		vm = "app/ppk/frmFaraidDev.jsp";
		//this.context.put("wait","true");
		//myLogger.info("submit:"+submit);
		myLogger.info("xxxx");
		if ("".equals(submit)) {
			this.context.put("wait","true");
		} else {
			this.context.put("wait","false");
			long start = System.currentTimeMillis(); 
			
			
			EkptgEngine ekptgEngine = new EkptgEngine();
			Simati sm = ekptgEngine.new Simati();
			
			Vector hasilFaraid = new Vector();
			
			// Everything is been done here..
			hasilFaraid = ekptgEngine.doAllFaraidProcessing(id_simati,id_permohonan,id_permohonansimati,ekptgEngine,sm);
			
			//New  Add by Hadi - March 2017
			Vector<Waris> warisList = new Vector<Waris>();
			warisList = ekptgEngine.getWarisList(id_simati, id_permohonan, ekptgEngine);
			Waris objInfo;
			Waris newObj;
			myLogger.info("Waris Count:" + warisList.size());
			
			//Update Faraid Result Info to All available Waris
			for(int x=0;x<warisList.size();x++) {
				objInfo = (Waris)warisList.get(x);
				Waris waris = this.getResultForWaris(objInfo.getID(), objInfo, hasilFaraid);
				warisList.set(x, waris);
				newObj = (Waris)warisList.get(x);
				myLogger.info("All Waris:" + waris.getID()+ ' ' + waris.getNama());
			}
			this.context.put("allWaris", warisList);
			// End of Add by Hadi
			
			//Simati Informattion
		 	this.context.put("namaSiMati", sm.getNamaSimati());
		 	this.context.put("noKp", sm.getNoKp());
		 	this.context.put("tarikhMati",sm.getTarikhMati());
		 	this.context.put("idSimati",sm.getId_simati());
		 	this.context.put("idPermohonanSimati",sm.getId_permohonansimati());
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
	
	// add by Hadi on March 2017 to feed object to waris diagram
	public Waris getResultForWaris(String warisId, Waris waris, Vector faraidList) {
		Waris objInfo;
		//myLogger.info("Waris Count:" + faraidList.size());
		
		for(int x=0;x<faraidList.size();x++) {
			objInfo = (Waris)faraidList.get(x);
			myLogger.info("test:" + objInfo.getID()+ ' ' + objInfo.getNama());
			//myLogger.info("test2:" + waris.getNama());
			if (waris.getNama().toUpperCase().equals("BAITULMAL")) {
				if (objInfo.getNama().toUpperCase().equals("BAITULMAL")) {
				 waris.setBahagian(objInfo.getBahagian());
				 waris.setBahagianAtas(objInfo.getBahagianAtas());
				 waris.setBahagianBawah(objInfo.getBahagianBawah());
				 waris.setHubungan(objInfo.getHubungan());
				 myLogger.info("Update Faraid Baitulmal:" +  waris.getID()+ ' ' + waris.getBahagian());
				 return waris;
				}
			} else {
				 if (warisId.equals(objInfo.getID())) {
					 waris.setBahagian(objInfo.getBahagian());
					 waris.setBahagianAtas(objInfo.getBahagianAtas());
					 waris.setBahagianBawah(objInfo.getBahagianBawah());
					 waris.setHubungan(objInfo.getHubungan());
					 myLogger.info("Update Faraid:" +  waris.getID()+ ' ' + waris.getBahagian());
					 return waris;
				 }
			
			}
			
		}
		return waris;
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
				h.put("NO_FAIL",Utils.isNull(rs.getString("NO_FAIL")));
				h.put("NAMA_SIMATI",Utils.isNull(rs.getString("NAMA_SIMATI")));
				h.put("NAMA_PEMOHON",Utils.isNull(rs.getString("NAMA_PEMOHON")));
				h.put("ID_PERMOHONAN",Utils.isNull(rs.getString("ID_PERMOHONAN")));
				h.put("ID_PERMOHONANSIMATI",Utils.isNull(rs.getString("ID_PERMOHONANSIMATI")));
				h.put("ID_SIMATI",Utils.isNull(rs.getString("ID_SIMATI")));
				v.add(h);
			}
			
		} catch (Exception e) {
			throw new Exception("Error in getting Fail List:"+e.getMessage());
		} finally {
			if (db != null) db.close();
		}
		return v;
	}

}
