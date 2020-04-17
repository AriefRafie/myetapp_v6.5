package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPermohonanData;
import ekptg.model.ppt.FrmHakmilikSementaraNotisIndividuData;

public class SementaraNotisIndividu extends AjaxBasedModule{

	@Override
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String submit = getParam("command");
        String action = getParam("action");
        String mode = getParam("mode");
        context.put("mode",mode);
        String tarikhmohon = "";
    	String nofail = "";    
    	String cStatus = "0";
    	String id_fail = getParam("id_fail");
		context.put("idFail", id_fail);
		String id_permohonan = getParam("id_permohonan");
		context.put("idPermohonan",id_permohonan);
		String id_buktipenyampaian = getParam("id_buktipenyampaian");
		context.put("idBuktiPenyampaian", id_buktipenyampaian);
		String pb = getParam("socNamaPB");
		String jenisDok = getParam("socJenisDokumen");
		HttpSession session = this.request.getSession();
		String doPost = (String) session.getAttribute("doPost");
		
		
    	FrmHakmilikSementaraNotisIndividuData notisIndividu = new FrmHakmilikSementaraNotisIndividuData();
    	FrmHakmilikSementaraMaklumatPermohonanData prmhnnMaster = new FrmHakmilikSementaraMaklumatPermohonanData();
    	
    	Vector list = null;
    	Vector listPBPilihan = null;
    	Vector listAgensi = null;
    	Vector listPenyampaianNotis = null;
    	Vector paparPenerimaNotis = null;
    	Vector dataPb = null;
    	

    	if ("newNotisIndividu".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraPenyampaianNotis.jsp";
    		context.put("mode", "new");
    		context.put("readonly", "");
    		context.put("disabled","");
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			
	
			notisIndividu.listPB(id_permohonan);		
			list = notisIndividu.getList();
		    context.put("listPB", list);
		    context.put("size_listPB",list.size());
		    
		    notisIndividu.listPenyampaianNotis(id_permohonan);
		    listPenyampaianNotis = notisIndividu.getListPenyampaianNotis();
		    context.put("listPenyampaianNotis",listPenyampaianNotis);
	
			context.put("namaPenghantarNotis","");
			context.put("SelectNamaPB", HTML.SelectNamaPB(id_permohonan, "socNamaPB",null,null,"onChange=\"doChangePB();\""));
			context.put("namaPenerima","");
			context.put("statusSerahan","");
    		context.put("jenisSerahan","");
			context.put("nokp","");
			context.put("catatan","");
			context.put("tarikhHantar","");
			   		
    	}
    	else if ("simpanPenyampaiNotis".equals(action)){
    	
    		addPenyampaiNotis(session);
    		vm = "app/ppt/frmHakmilikSementaraPenyampaianNotis.jsp";
    		context.put("mode", "new");
    		context.put("readonly", "");
    		context.put("disabled","");
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			
			notisIndividu.listPB(id_permohonan);		
			list = notisIndividu.getList();
		    context.put("listPB", list);
		    context.put("size_listPB",list.size());
		    
		    notisIndividu.listPenyampaianNotis(id_permohonan);
		    listPenyampaianNotis = notisIndividu.getListPenyampaianNotis();
		    context.put("listPenyampaianNotis",listPenyampaianNotis);
			
			context.put("namaPenghantarNotis","");
			context.put("SelectNamaPB", HTML.SelectNamaPB(id_permohonan, "socNamaPB",null,null,"onChange=\"doChangePB();\""));
			context.put("namaPenerima","");
			context.put("statusSerahan","");
    		context.put("jenisSerahan","");
			context.put("nokp","");
			context.put("catatan","");
			context.put("tarikhHantar","");
    	
    	}
    	else if ("viewPenerimaNotis".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraPenyampaianNotis.jsp";
    		context.put("mode", "view");
    		context.put("readonly", "readonly");
    		context.put("disabled","disabled");
    		
    		notisIndividu.paparPenerimaNotis(id_buktipenyampaian);
    		paparPenerimaNotis = notisIndividu.getList();
    		
    		Hashtable h = (Hashtable)paparPenerimaNotis.get(0);
    		
    		context.put("id_buktipenyampaian",h.get("id_buktipenyampaian"));
    		context.put("namaPenghantarNotis",h.get("nama_penghantar"));
    		context.put("SelectNamaPB", HTML.SelectNamaPB(id_permohonan, "socNamaPB",Utils.parseLong(h.get("id_pihakberkepentingan").toString()),"class=disabled disabled ",null));
			context.put("namaPenerima",h.get("nama_penerima"));
    		context.put("statusSerahan",h.get("flag_serah"));
    		context.put("jenisSerahan",h.get("jenis_serahan"));
    		context.put("tarikhHantar",h.get("tarikh_hantar"));
    		context.put("nokp",h.get("no_kp_penerima"));
    		context.put("catatan",h.get("catatan"));
    		
    		notisIndividu.listPBPilihan(h.get("id_buktipenyampaian").toString());		
			list = notisIndividu.getListPBPilihan();
		    context.put("listPB", list);
		    context.put("size_listPB",list.size());
		    
		    notisIndividu.listPenyampaianNotis(id_permohonan);
		    listPenyampaianNotis = notisIndividu.getListPenyampaianNotis();
		    context.put("listPenyampaianNotis",listPenyampaianNotis);
    	}
    	
    	else if ("kemaskini".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraPenyampaianNotis.jsp";
    		context.put("mode", "update");
    		context.put("readonly", "");
    		context.put("disabled","");
    		
    		notisIndividu.paparPenerimaNotis(id_buktipenyampaian);
    		paparPenerimaNotis = notisIndividu.getList();
    		
    		Hashtable h = (Hashtable)paparPenerimaNotis.get(0);
    		
    		context.put("id_buktipenyampaian",h.get("id_buktipenyampaian"));
    		context.put("namaPenghantarNotis",h.get("nama_penghantar"));
    		context.put("SelectNamaPB", HTML.SelectNamaPB(id_permohonan, "socNamaPB",Utils.parseLong(h.get("id_pihakberkepentingan").toString()),null,null));
			context.put("namaPenerima",h.get("nama_penerima"));
    		context.put("statusSerahan",h.get("flag_serah"));
    		context.put("jenisSerahan",h.get("jenis_serahan"));
    		context.put("tarikhHantar",h.get("tarikh_hantar"));
    		context.put("nokp",h.get("no_kp_penerima"));
    		context.put("catatan",h.get("catatan"));
    		
    		notisIndividu.listPBPilihan(h.get("id_buktipenyampaian").toString());		
			list = notisIndividu.getListPBPilihan();
		    context.put("listPB", list);
		    context.put("size_listPB",list.size());
		    
		    notisIndividu.listPenyampaianNotis(id_permohonan);
		    listPenyampaianNotis = notisIndividu.getListPenyampaianNotis();
		    context.put("listPenyampaianNotis",listPenyampaianNotis);
    	}
    	
    	else if ("simpanKemaskini".equals(action)){
    		
    		KemaskiniPenerimaNotis(session);
    		vm = "app/ppt/frmHakmilikSementaraPenyampaianNotis.jsp";
    		context.put("mode", "view");
    		context.put("readonly", "readonly");
    		context.put("disabled","disabled");
    		
    		notisIndividu.paparPenerimaNotis(id_buktipenyampaian);
    		paparPenerimaNotis = notisIndividu.getList();
    		
    		Hashtable h = (Hashtable)paparPenerimaNotis.get(0);
    		
    		context.put("id_buktipenyampaian",h.get("id_buktipenyampaian"));
    		context.put("namaPenghantarNotis",h.get("nama_penghantar"));
    		context.put("SelectNamaPB", HTML.SelectNamaPB(id_permohonan, "socNamaPB",Utils.parseLong(h.get("id_pihakberkepentingan").toString()),"class=disabled disabled ",null));
			context.put("namaPenerima",h.get("nama_penerima"));
    		context.put("statusSerahan",h.get("flag_serah"));
    		context.put("jenisSerahan",h.get("jenis_serahan"));
    		context.put("tarikhHantar",h.get("tarikh_hantar"));
    		context.put("nokp",h.get("no_kp_penerima"));
    		context.put("catatan",h.get("catatan"));
    		
    		notisIndividu.listPBPilihan(h.get("id_buktipenyampaian").toString());		
			list = notisIndividu.getListPBPilihan();
		    context.put("listPB", list);
		    context.put("size_listPB",list.size());
		    
		    notisIndividu.listPenyampaianNotis(id_permohonan);
		    listPenyampaianNotis = notisIndividu.getListPenyampaianNotis();
		    context.put("listPenyampaianNotis",listPenyampaianNotis);
    		
    	}
    	else if ("hapusMaklumat".equals(action)){
    		
    		deleteMaklumat(session);
    		vm = "app/ppt/frmHakmilikSementaraPenyampaianNotis.jsp";
    		context.put("mode", "new");
    		context.put("readonly", "");
    		context.put("disabled","");
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			
	
			notisIndividu.listPB(id_permohonan);		
			list = notisIndividu.getList();
		    context.put("listPB", list);
		    context.put("size_listPB",list.size());
		    
		    notisIndividu.listPenyampaianNotis(id_permohonan);
		    listPenyampaianNotis = notisIndividu.getListPenyampaianNotis();
		    context.put("listPenyampaianNotis",listPenyampaianNotis);
	
			context.put("namaPenghantarNotis","");
			context.put("SelectNamaPB", HTML.SelectNamaPB(id_permohonan, "socNamaPB",null,null,"onChange=\"doChangePB();\""));
			context.put("namaPenerima","");
			context.put("statusSerahan","");
    		context.put("jenisSerahan","");
			context.put("nokp","");
			context.put("catatan","");
			context.put("tarikhHantar","");
    		
    		
    	}
    	else if ("doChangePB".equals(submit)){
    		vm = "app/ppt/frmHakmilikSementaraPenyampaianNotis.jsp";
    		context.put("mode", "new");
    		context.put("readonly", "");
    		context.put("disabled","");
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			
			context.put("namaPenghantarNotis",getParam("txtNamaPenghantarNotis"));
			context.put("SelectNamaPB", HTML.SelectNamaPB(id_permohonan, "socNamaPB",Utils.parseLong(pb),null,"onChange=\"doChangePB();\""));
			context.put("namaPenerima",getParam("txtNamaWakil"));
			context.put("statusSerahan",getParam("socStatusSerahan"));
    		context.put("jenisSerahan",getParam("socJenisSerahan"));
			context.put("catatan",getParam("txtCatatan"));
			context.put("tarikhHantar",getParam("txdTarikhSerahan"));
			
			notisIndividu.setDataPB(pb);
			dataPb = notisIndividu.getDataPb();
			Hashtable hP = (Hashtable)dataPb.get(0);
			context.put("nokp",hP.get("NO_PB"));
			
			notisIndividu.listPB(id_permohonan);		
			list = notisIndividu.getList();
		    context.put("listPB", list);
		    context.put("size_listPB",list.size());
		    
		    notisIndividu.listPenyampaianNotis(id_permohonan);
		    listPenyampaianNotis = notisIndividu.getListPenyampaianNotis();
		    context.put("listPenyampaianNotis",listPenyampaianNotis);
    		
    		
    	}
    	else if ("doChangePBUpdate".equals(submit)){
    		vm = "app/ppt/frmHakmilikSementaraPenyampaianNotis.jsp";
    		context.put("mode", "update");
    		context.put("readonly", "");
    		context.put("disabled","");
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			
			
			
			notisIndividu.paparPenerimaNotis(id_buktipenyampaian);
    		paparPenerimaNotis = notisIndividu.getList();
    		
    		Hashtable hN = (Hashtable)paparPenerimaNotis.get(0);
    		
    		context.put("id_buktipenyampaian",hN .get("id_buktipenyampaian"));
    		context.put("namaPenghantarNotis",hN .get("nama_penghantar"));
    		context.put("SelectNamaPB", HTML.SelectNamaPB(id_permohonan, "socNamaPB",Utils.parseLong(hN.get("id_pihakberkepentingan").toString()),null,"onChange=\"doChangePBUpdate();\""));
			context.put("namaPenerima",hN.get("nama_penerima"));
    		context.put("statusSerahan",hN .get("flag_serah"));
    		context.put("jenisSerahan",hN .get("jenis_serahan"));
    		context.put("tarikhHantar",hN .get("tarikh_hantar"));
    		context.put("catatan",hN .get("catatan"));
			
			notisIndividu.setDataPB(pb);
			dataPb = notisIndividu.getDataPb();
			Hashtable hP = (Hashtable)dataPb.get(0);
			context.put("nokp",hP.get("NO_PB"));
			
			notisIndividu.listPBPilihan(hN.get("id_buktipenyampaian").toString());		
			list = notisIndividu.getListPBPilihan();
		    context.put("listPB", list);
		    context.put("size_listPB",list.size());
		    
		    notisIndividu.listPenyampaianNotis(id_permohonan);
		    listPenyampaianNotis = notisIndividu.getListPenyampaianNotis();
		    context.put("listPenyampaianNotis",listPenyampaianNotis);
    		
    	}
    	else{
    		 	vm = "app/ppt/frmHakmilikSementaraSenaraiPenyampaianNotis.jsp";
    			
    			if (!"".equals(getParam("txdTarikh")));
    				tarikhmohon = getParam("txdTarikh");
    		
    			if (!"".equals(getParam("txtNoFail")));
    				nofail = getParam("txtNoFail");
    			
    			if(!"".equals(getParam("socStatus")));
    				cStatus = getParam("socStatus");
    			
    			notisIndividu.setCarianFail(nofail, tarikhmohon, cStatus);		
    			list = notisIndividu.getList();
    								
    		
    		    context.put("PermohonanList", list);
    		    context.put("list_size", list.size());
    		    context.put("CarianFail", nofail);  
    		    context.put("tarikhPermohonan", tarikhmohon);
    		    context.put("SelectStatus", HTML.SelectStatusPPT("socStatus",Utils.parseLong(cStatus),"style=width:130px"));
    		    setupPage(session,action,list);
    	}
       
		
		
		
		return vm;
	}
	
private void deleteMaklumat(HttpSession session) throws Exception {
		// delete row
		String id_buktipenyampaian= getParam("id_buktipenyampaian");
		
		FrmHakmilikSementaraNotisIndividuData.deleteMaklumat(id_buktipenyampaian);
		
	}

private void KemaskiniPenerimaNotis(HttpSession session) throws Exception {
		// update table bukti penyampaian
		Hashtable h = new Hashtable();
    
		String idPermohonan= getParam("id_permohonan");
		long id_buktipenyampaian = Long.parseLong(getParam("id_buktipenyampaian"));
	
		h.put("idPermohonan",idPermohonan);
		h.put("idBuktiPenyampaian",id_buktipenyampaian);
		h.put("namaPenghantarNotis",getParam("txtNamaPenghantarNotis"));
		h.put("namaPB",getParam("socNamaPB"));
		h.put("namaWakil",getParam("txtNamaWakil"));
		h.put("tarikhTampalNotis",getParam("txdTarikhTampal"));
		h.put("statusSerahan",getParam("socStatusSerahan"));
		h.put("jenisSerahan",getParam("socJenisSerahan"));
		h.put("tarikhSerahan",getParam("txdTarikhSerahan"));
		h.put("noKp",getParam("txtNoKP"));
		h.put("catatan",getParam("txtCatatan"));
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		
		String[] cbsemaks = request.getParameterValues("cbsemaks");
		
		FrmHakmilikSementaraNotisIndividuData.deletePilihanPB(id_buktipenyampaian);
		
		if((cbsemaks!=null)){
			for (int i = 0; i < cbsemaks.length; i++) { 
				FrmHakmilikSementaraNotisIndividuData.simpanPilihanPB(h,cbsemaks[i],id_buktipenyampaian);
			}
		}
		
		FrmHakmilikSementaraNotisIndividuData.updatePenerimaNotis(h,id_buktipenyampaian);

	}

private void addPenyampaiNotis(HttpSession session)  throws Exception {
		// simpan data pada table bukti penyampaian
		Hashtable h = new Hashtable();
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idPermohonan= getParam("id_permohonan");
		
		h.put("idPermohonan",idPermohonan);
		h.put("namaPenghantarNotis",getParam("txtNamaPenghantarNotis"));
		h.put("namaPB",getParam("socNamaPB"));
		h.put("namaWakil",getParam("txtNamaWakil"));
		h.put("tarikhTampalNotis",getParam("txdTarikhTampal"));
		h.put("statusSerahan",getParam("socStatusSerahan"));
		h.put("jenisSerahan",getParam("socJenisSerahan"));
		h.put("tarikhSerahan",getParam("txdTarikhSerahan"));
		h.put("noKp",getParam("txtNoKP"));
		h.put("catatan",getParam("txtCatatan"));
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		
		String[] cbsemaks = request.getParameterValues("cbsemaks");
		long id_buktipenyampaian = DB.getNextID("TBLPPTBUKTIPENYAMPAIAN_SEQ");
		
		if((cbsemaks!=null)){
			for (int i = 0; i < cbsemaks.length; i++) { 
				FrmHakmilikSementaraNotisIndividuData.simpanPilihanPB(h,cbsemaks[i],id_buktipenyampaian);
			}
		}
		
		FrmHakmilikSementaraNotisIndividuData.addPenyampaiNotis(h,id_buktipenyampaian);
		
		
	}

public void setupPage(HttpSession session,String action,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}
	    
	    if ("getNext".equals(action)) {
	    	page++;
	    } else if ("getPrevious".equals(action)) {
	    	page--;
	    } else if ("getPage".equals(action)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }
	    	
	    Paging paging = new Paging(session,list,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("PermohonanList",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}


}
