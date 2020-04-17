/**
 * 
 */
package ekptg.report.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmPrmhnnSek8Notis;
import ekptg.model.ppk.FrmRynSek8SemakPenerimaan;
/**
 * 
 *
 */
public class FrmPopupPilihPegawaiReportView extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	FrmRynSek8SemakPenerimaan model = new FrmRynSek8SemakPenerimaan();
	FrmPrmhnnSek8Notis model2 = new FrmPrmhnnSek8Notis();
	static Logger myLog = Logger.getLogger(ekptg.report.ppk.FrmPopupPilihPegawaiReportView.class);
	
	@Override
	public String doTemplate2() throws Exception {
		
		//System.out.println(".:FrmPopupPilihPegawaiReportView:.");
		HttpSession session = request.getSession();
		
		String vm = "";
		String report = getParam("report");
		String flagReport = getParam("flagReport");
		//myLog.info("getParam(\"noFail\")="+getParam("noFail"));	
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
		String noKP = getParam("noKP");
				
		if(idnegerirayuan != "" && idnegerirayuan != "0"){
			context.put("selectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(idnegerirayuan),"socMahkamah",null,null,"style=width:305 onchange=getdetailmahkamah()"));
    	}else{
    		context.put("selectMahkamah",HTML.SelectMahkamah("socMahkamah",null,null,"style=width:305 onchange=getdetailmahkamah()"));
        }
		
		String submit = getParam("command");
    	if ("getdetailmahkamah".equals(submit)){
    		
    		String idmahkamah = getParam("socMahkamah");
    		
    		
    		if(idnegerirayuan != "" && idnegerirayuan != "0"){
    			if(idmahkamah != ""){
    				context.put("selectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(idnegerirayuan),"socMahkamah",Utils.parseLong(idmahkamah),null,"style=width:305 onchange=getdetailmahkamah()"));
    			}else{
    				context.put("selectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(idnegerirayuan),"socMahkamah",null,null,"style=width:305 onchange=getdetailmahkamah()"));
        		}
    		}else{
        		if(idmahkamah != ""){
    				context.put("selectMahkamah",HTML.SelectMahkamah("socMahkamah",Utils.parseLong(idmahkamah),null,"style=width:305 onchange=getdetailmahkamah()"));
    			}else{
    				context.put("selectMahkamah",HTML.SelectMahkamah("socMahkamah",null,null,"style=width:305 onchange=getdetailmahkamah()"));
        		}
    		}
    		
    		String daerah = "";
			String negeri = "";
    		String iddaerah = "";
			
    		if(idmahkamah!=""){
    			Vector detailMahkamah = model.getDetailMahkamah(idmahkamah);
        		
        		if(detailMahkamah.size()!=0){
        		Hashtable onc = (Hashtable) detailMahkamah.get(0);
        			
        			daerah = onc.get("nama_daerah").toString();
        			negeri = onc.get("nama_negeri").toString();
        			iddaerah = onc.get("id_daerah").toString();
        		
        		}//close if(detailMahkamah.size()!=0)
        	}
    				context.put("daerahrayuan", iddaerah);
    				context.put("negerirayuan", negeri);
    	}
    	else if ("simpanPilihanOb".equals(submit)){
    		
    		Hashtable h = new Hashtable();
    		
    		id_perbicaraan = getParam("idperbicaraan");
    		
    		h.put("id_user", session.getAttribute("_ekptg_user_id"));
    		h.put("id_perbicaraan",id_perbicaraan);
    		
    		//delect checkbox
    		logic.deletePilihanOb(id_perbicaraan);
    		
    		String[] cblistob = request.getParameterValues("cblistob");
  	 	  	for (int i = 0; i < cblistob.length; i++) { 
  	 	  		logic.simpanPilihanOb(h,cblistob[i]);
        	}
        	
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
		
		Vector listAllOB = new Vector();
		listAllOB.clear();
		
		if(idpermohonansimati!=""){
			//int idPSimati = Integer.parseInt(idpermohonansimati);
			String idPSimati = getParam("idpermohonansimati");
			String idSimati = "0";
			if(idsimati.trim().length() > 0){
				idSimati = idsimati;	
			}
			//get list ob
			logic.setListPilihanOb(idPSimati,id_perbicaraan,idSimati);
			listAllOB = logic.getListPilihanOb();
		}
		
		idfail = logic.getIdFail(noFail);
		this.context.put("idfail", idfail);
		
		vm = "app/ppk/frmPopupCetakLaporan.jsp";
		
		boolean checkJawatanKPP_HQ = false;
		checkJawatanKPP_HQ = logic.getJawatanKPP_HQ(session,(String)session.getAttribute("_ekptg_user_id"));
		
		
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
		//this.context.put("selectNamaPegawai",HTML.SelectPegawaiLaporan(idNegeri, "socPegawai", Long.parseLong(idPegawai), "", "")); aishahlatip ubah
		this.context.put("selectNamaPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeri, "socPegawai", Long.parseLong(idPegawai), "", ""));
		this.context.put("selectNamaPegawai_KPP",HTML.SelectPegawaiPengendaliByNegeri_KPP(idNegeri, "socPegawai", Long.parseLong(idPegawai), "", "", checkJawatanKPP_HQ));//aishahlatip add
		this.context.put("selectDaerah",HTML.SelectDaerahForSuratIringanTerengganu("socDaerah", Long.parseLong(idDaerah), "", ""));
		//this.context.put("selectedNamaPegawai",HTML.SelectPegawaiLaporan(idNegeri, "socPegawai", Long.parseLong(id_Pegawai), "", ""));aishahlatip ubah
		this.context.put("selectedNamaPegawai",HTML.SelectPegawaiPengendaliByNegeri(idNegeri, "socPegawai", Long.parseLong(id_Pegawai), "", ""));
		
		this.context.put("selectDaerahUrus",HTML.SelectDaerahByNegeri(idNegeri, "socDaerahUrus",Long.parseLong(id_Daerah), ""));
		this.context.put("selectDaerahUrus_KPP",HTML.SelectDaerahByNegeri_KPP(idNegeri, "socDaerahUrus",Long.parseLong(id_Daerah), "", (String)session.getAttribute("_ekptg_user_id"), checkJawatanKPP_HQ ));//aishahlatip add

		this.context.put("idobminor", idobminor);
		this.context.put("idsimati", idsimati);
		this.context.put("id_Pegawai", id_Pegawai);
		this.context.put("noKP",noKP);	
		
		return vm;
		
	}
	
	
}
