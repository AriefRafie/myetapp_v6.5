/**
 * 
 */
package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

public class FrmPilihPegawai extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ekptg.view.htp.FrmPilihPegawai.class);
	
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = request.getSession();		
		String vm = "";
		vm = "app/ppk/frmPilihPegawai.jsp";

		String report = getParam("report");
		String flagReport = getParam("flagReport");
		String noFail = getParam("noFail");
		String idhta = getParam("idhta");
		String id_perbicaraan = getParam("idperbicaraan");
		String bilDokumen = getParam("bilDokumen");
		String id_kolateralmst = getParam("id_kolateralmst");
		String idPegawai = getParam("socPegawai");
		String id_Pegawai = getParam("idpegawai");
		String idnegerirayuan = getParam("idnegeri");
		String idobminor = getParam("idobminor");
		String idsimati = getParam("idsimati");
		String idpermohonansimati = getParam("idpermohonansimati");
		String idpejabatjkptg = getParam("idpejabatjkptg");
		
		if(idnegerirayuan != "" && idnegerirayuan != "0"){
			context.put("selectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(idnegerirayuan),"socMahkamah",null,null,"style=width:305 onchange=getdetailmahkamah()"));
    	}else{
    		context.put("selectMahkamah",HTML.SelectMahkamah("socMahkamah",null,null,"style=width:305 onchange=getdetailmahkamah()"));
        }
		
		String submit = getParam("command");
    	if ("getdetailmahkamah".equals(submit)){
     	}else if ("simpanPilihanOb".equals(submit)){
     	}//close simpanCheckOB
    	
		if (idPegawai == null || idPegawai.trim().length() == 0){
			idPegawai = "0";
		}
		if (id_Pegawai == null || id_Pegawai.trim().length() == 0){
			id_Pegawai = "0";
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "0";
		}
		
		Vector list = new Vector();
		list.clear();
		
		String idPermohonan = "";
		String idNegeri = "";
		String idfail = "";
		
		//logic.setMaklumatPermohonan(noFail);
		//list = logic.getBeanMaklumatPermohonan();
		if (list.size() != 0){
			Hashtable h = (Hashtable) list.get(0);
			idPermohonan = h.get("idPermohonan").toString();
			idNegeri = h.get("idNegeri").toString();
		}
		
		Vector listAllOB = new Vector();
		listAllOB.clear();
		
		if(idpermohonansimati!=""){
			int idPSimati = Integer.parseInt(idpermohonansimati);
			int idSimati = 0;
			if(idsimati!=""){
				idSimati = Integer.parseInt(idsimati);	
			}
			//get list ob
			//logic.setListPilihanOb(idPSimati,id_perbicaraan,idSimati);
			//listAllOB = logic.getListPilihanOb();
		}
		
		//idfail = logic.getIdFail(noFail);
		this.context.put("idfail", idfail);
		
		
		this.context.put("idpejabatjkptg", idpejabatjkptg);
		this.context.put("listAllOB", listAllOB);
		this.context.put("listAllOB_size", listAllOB.size());
		this.context.put("report", report);
		this.context.put("flagReport", flagReport);
		this.context.put("id_perbicaraan", id_perbicaraan);
		this.context.put("noFail", noFail);
		this.context.put("idhta", idhta);
		this.context.put("bilDokumen", bilDokumen);
		this.context.put("id_kolateralmst", id_kolateralmst);
		this.context.put("selectNamaPegawai",HTML.SelectPegawaiLaporan(idNegeri, "socPegawai", Long.parseLong(idPegawai), "", ""));
		this.context.put("selectedNamaPegawai",HTML.SelectPegawaiLaporan(idNegeri, "socPegawai", Long.parseLong(id_Pegawai), "", ""));
		this.context.put("idobminor", idobminor);
		this.context.put("idsimati", idsimati);
		this.context.put("id_Pegawai", id_Pegawai);
		
		return vm;
	}
}
