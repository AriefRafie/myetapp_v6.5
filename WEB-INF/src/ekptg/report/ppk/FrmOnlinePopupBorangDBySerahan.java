/**
 * 
 */
package ekptg.report.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmPrmhnnSek8Notis;
import ekptg.model.ppk.FrmRynSek8SemakPenerimaan;
/**
 * 
 *
 */
public class FrmOnlinePopupBorangDBySerahan extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	FrmRynSek8SemakPenerimaan model = new FrmRynSek8SemakPenerimaan();
	FrmPrmhnnSek8Notis model2 = new FrmPrmhnnSek8Notis();
	static Logger myLog = Logger.getLogger(ekptg.report.ppk.FrmOnlinePopupBorangDBySerahan.class);
	
	@Override
	public String doTemplate2() throws Exception {
		
		//System.out.println(".:FrmPopupPilihPegawaiReportView:.");
		HttpSession session = request.getSession();
		
		String vm = "";
		String id_perbicaraan = getParam("id_perbicaraan");
		this.context.put("id_perbicaraan", id_perbicaraan);
		//myLog.info("getParam(\"noFail\")="+getParam("noFail"));	
		String noFail = getParam("noFail");
		String idPegawai = getParam("socPegawai");
		if (idPegawai == null || idPegawai.trim().length() == 0){
			idPegawai = "0";
		}

		String idpejabatjkptg = getParam("idpejabatjkptg");


		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "0";
		}
		String id_Daerah = getParam("socDaerahUrus");
		if (id_Daerah == null || id_Daerah.trim().length() == 0){
			id_Daerah = "0";
		}
		
		Vector list = new Vector();
		list.clear();
		
		Vector listbyUser = new Vector();
		listbyUser.clear();
		
		String idPermohonan = "";
		String idNegeri = "";
		String idfail = "";		
		
		logic.setMaklumatPermohonan(noFail);
		list = logic.getBeanMaklumatPermohonan();
		if (list.size() != 0){
			Hashtable h = (Hashtable) list.get(0);
			idPermohonan = h.get("idPermohonan").toString();
			idNegeri = h.get("idNegeri").toString();
		}
		
		if(idNegeri.equals(""))
		{
			logic.setMaklumatPermohonanbyUser((String)session.getAttribute("_ekptg_user_id"));
			listbyUser = logic.getBeanMaklumatPermohonanbyUser();
			if (listbyUser.size() != 0){
				Hashtable h = (Hashtable) listbyUser.get(0);
				idNegeri = h.get("idNegeri").toString();
			}
		}
		
		
		idfail = logic.getIdFail(noFail);
		this.context.put("idfail", idfail);
		
		vm = "app/ppk/frmPopupCetakBorangDBySerahan.jsp";
		this.context.put("noFail", noFail);		
	
		return vm;
		
	}
	
	
}
