package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPermohonanData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiTandaKwsnData;

public class SementaraTandaKwsn extends AjaxBasedModule {

	@Override
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String submit = getParam("command");
        String action = getParam("action");
        String tarikhmohon = "";
    	String nofail = "";    
    	String cStatus = "0";
    	String id_fail = getParam("id_fail");
		context.put("idFail", id_fail);
		String id_permohonan = getParam("id_permohonan");
		context.put("idPermohonan",id_permohonan);
		String mode = getParam("mode");
		context.put("mode",mode);
		String id_TandaKawasan = getParam("id_tandakawasan");
    	String negeri = getParam("socNegeri");
    	String bandar = getParam("socBandar");
		
    	FrmHakmilikSementaraSenaraiTandaKwsnData listTanda = new FrmHakmilikSementaraSenaraiTandaKwsnData();
    	FrmHakmilikSementaraMaklumatPermohonanData prmhnnMaster = new FrmHakmilikSementaraMaklumatPermohonanData();
    	
    	Vector listTandaKawasan = null;
    	Vector listCarianFail = null;
    	Vector paparTandaKawasan = null;
    	Vector listAgensi = null;
    	
		
		
    	    	
    	HttpSession session = this.request.getSession();
    	
    	if ("newTanda".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraTandaKwsn.jsp";
    		context.put("mode", "newTanda");
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
			
			context.put("StatusTanda","0");
    		context.put("StatusLaksana","0");
			context.put("TarikhTandaDari","");
    		context.put("TarikhTandaHingga","");
    		context.put("TarikhLawatPeriksa","");
    		context.put("TarikhLulus", "");
    		context.put("rujAgensi","");
    		context.put("namaPegawai","");
    		context.put("Alamat1","");
    		context.put("Alamat2","");
    		context.put("Alamat3","");
    		context.put("Poskod","");
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",null,null,"style=width:200px onChange=\"doChangeidNegeri();\""));
    		context.put("SelectBandar",HTML.SelectBandar("socBandar",null, "style=width:200px"));
    		
    		
			listTanda.listTandaKwsn(id_permohonan);
			listTandaKawasan = listTanda.getListTandaKawasan();				
		    context.put("TandaList", listTandaKawasan);
		    		
    	}
    	
    	else if ("addTanda".equals(action)){
    		
    		
    		addTanda(session);
    		vm = "app/ppt/frmHakmilikSementaraTandaKwsn.jsp";
    		context.put("mode", "newTanda");
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
			
			listTanda.listTandaKwsn(id_permohonan);
			listTandaKawasan = listTanda.getListTandaKawasan();
			context.put("TandaList", listTandaKawasan);
			
				
    	}
    	
    	else if ("viewTandaKwsn".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraTandaKwsn.jsp";
    		context.put("mode", "viewTanda");
    		context.put("readonly", "readonly");
    		context.put("disabled","disabled");
    		
    		    		
    		listTanda.paparTandaKwsn(id_TandaKawasan);
    		paparTandaKawasan = listTanda.getPaparTandaKawasan();
    		
    		Hashtable h = (Hashtable)paparTandaKawasan.get(0);
    		context.put("idTandaKawasan",h.get("id_tandakawasan"));
			context.put("StatusTanda",h.get("statustanda"));
    		context.put("StatusLaksana",h.get("statuslaksana"));
			context.put("TarikhTandaDari",h.get("tarikhmula"));
    		context.put("TarikhTandaHingga",h.get("tarikhakhir"));
    		context.put("TarikhLawatPeriksa",h.get("tarikhlawat"));
    		context.put("TarikhLulus", h.get("tarikhlulus"));
    		context.put("rujAgensi",h.get("norujagensi"));
    		context.put("namaPegawai",h.get("namapegawai"));
    		context.put("Alamat1",h.get("alamat1"));
    		context.put("Alamat2",h.get("alamat2"));
    		context.put("Alamat3",h.get("alamat3"));
    		context.put("Poskod",h.get("poskod"));
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(h.get("idnegeri").toString()),null,"class=disabled disabled class=autoselect onChange=\"doChangeidNegeriUpdate();\""));
    		context.put("SelectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(h.get("idbandar").toString()), "class=disabled disabled class=autoselect"));
    		
    		
    		
    		listTanda.listTandaKwsn(id_permohonan);
			listTandaKawasan = listTanda.getListTandaKawasan();
			context.put("TandaList", listTandaKawasan);
    		
    	}
    	
    	else if ("kemaskiniTanda".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraTandaKwsn.jsp";
    		context.put("mode", "kemaskiniTanda");
    		context.put("readonly", "");
    		context.put("disabled","");
    		    		
    		listTanda.paparTandaKwsn(id_TandaKawasan);
    		paparTandaKawasan = listTanda.getPaparTandaKawasan();
    		Hashtable h = (Hashtable)paparTandaKawasan.get(0);
    		context.put("idTandaKawasan",h.get("id_tandakawasan"));
			context.put("StatusTanda",h.get("statustanda"));
    		context.put("StatusLaksana",h.get("statuslaksana"));
			context.put("TarikhTandaDari",h.get("tarikhmula"));
    		context.put("TarikhTandaHingga",h.get("tarikhakhir"));
    		context.put("TarikhLawatPeriksa",h.get("tarikhlawat"));
    		context.put("TarikhLulus", h.get("tarikhlulus"));
    		context.put("rujAgensi",h.get("norujagensi"));
    		context.put("namaPegawai",h.get("namapegawai"));
    		context.put("Alamat1",h.get("alamat1"));
    		context.put("Alamat2",h.get("alamat2"));
    		context.put("Alamat3",h.get("alamat3"));
    		context.put("Poskod",h.get("poskod"));
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(h.get("idnegeri").toString()),null,"class=autoselect onChange=\"doChangeidNegeriUpdate();\""));
    		context.put("SelectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(h.get("idbandar").toString()), "class=autoselect"));
    		
    		listTanda.listTandaKwsn(id_permohonan);
			listTandaKawasan = listTanda.getListTandaKawasan();
			context.put("TandaList", listTandaKawasan);
    		
    	}
    	
    	else if ("simpanUpdateTanda".equals(action)){
    		
    		updateTanda(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraTandaKwsn.jsp";
    		context.put("mode", "viewTanda");
    		context.put("readonly", "readonly");
    		context.put("disabled","disabled");
    		
    		listTanda.paparTandaKwsn(id_TandaKawasan);
    		paparTandaKawasan = listTanda.getPaparTandaKawasan();
    		Hashtable h = (Hashtable)paparTandaKawasan.get(0);
    		context.put("idTandaKawasan",h.get("id_tandakawasan"));
			context.put("StatusTanda",h.get("statustanda"));
    		context.put("StatusLaksana",h.get("statuslaksana"));
			context.put("TarikhTandaDari",h.get("tarikhmula"));
    		context.put("TarikhTandaHingga",h.get("tarikhakhir"));
    		context.put("TarikhLawatPeriksa",h.get("tarikhlawat"));
    		context.put("TarikhLulus", h.get("tarikhlulus"));
    		context.put("rujAgensi",h.get("norujagensi"));
    		context.put("namaPegawai",h.get("namapegawai"));
    		context.put("Alamat1",h.get("alamat1"));
    		context.put("Alamat2",h.get("alamat2"));
    		context.put("Alamat3",h.get("alamat3"));
    		context.put("Poskod",h.get("poskod"));
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(h.get("idnegeri").toString()),null,"class=disabled disabled class=autoselect onChange=\"doChangeidNegeriUpdate();\""));
    		context.put("SelectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(h.get("idbandar").toString()), "class=disabled disabled class=autoselect"));
    	
    		
    		listTanda.listTandaKwsn(id_permohonan);
			listTandaKawasan = listTanda.getListTandaKawasan();
			context.put("TandaList", listTandaKawasan);
    		
    	}
    	
    	else if ("hapusTanda".equals(action)){
    		
    		deleteTanda(session);
    		vm = "app/ppt/frmHakmilikSementaraTandaKwsn.jsp";
    		context.put("mode", "newNotis");
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
			
			context.put("StatusTanda","0");
    		context.put("StatusLaksana","0");
			context.put("TarikhTandaDari","");
    		context.put("TarikhTandaHingga","");
    		context.put("TarikhLawatPeriksa","");
    		context.put("rujAgensi","");
    		context.put("namaPegawai","");
    		context.put("Alamat1","");
    		context.put("Alamat2","");
    		context.put("Alamat3","");
    		context.put("Poskod","");
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",null,null,"style=width:200px onChange=\"doChangeidNegeri();\""));
    		context.put("SelectBandar",HTML.SelectBandar("socBandar",null, "style=width:200px"));
    		
			
    		listTanda.listTandaKwsn(id_permohonan);
			listTandaKawasan = listTanda.getListTandaKawasan();
			context.put("TandaList", listTandaKawasan);
    		
    	}
    	
    	else if ("doChangeidNegeri".equals(submit)) {
    		vm = "app/ppt/frmHakmilikSementaraTandaKwsn.jsp";
    		context.put("mode", "newTanda");
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
			
			context.put("StatusTanda",getParam("socStatusTanda"));
    		context.put("StatusLaksana",getParam("socStatusLaksana"));
			context.put("TarikhTandaDari",getParam("txdTkhTandaMula"));
    		context.put("TarikhTandaHingga",getParam("txdTkhTandaHingga"));
    		context.put("TarikhLawatPeriksa",getParam("txdTkhLawatPeriksa"));
    		context.put("TarikhLulus", getParam("txdTarikhLulus"));
    		context.put("rujAgensi",getParam("txtNoRujAgensi"));
    		context.put("namaPegawai",getParam("txtNamaPegawai"));
    		context.put("Alamat1",getParam("txtAlamat1Jurukur"));
    		context.put("Alamat2",getParam("txtAlamat2Jurukur"));
    		context.put("Alamat3",getParam("txtAlamat3Jurukur"));
    		context.put("Poskod",getParam("txtPoskod"));
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(negeri),null,"style=width:200px onChange=\"doChangeidNegeri();\""));
    		context.put("SelectBandar",HTML.SelectBandarByNegeri(negeri,"socBandar",null, "style=width:200px"));
    		
    		
			listTanda.listTandaKwsn(id_permohonan);
			listTandaKawasan = listTanda.getListTandaKawasan();				
		    context.put("TandaList", listTandaKawasan);
    		
    		
    	}

    	else if ("doChangeidNegeriUpdate".equals(submit)) {
    		vm = "app/ppt/frmHakmilikSementaraTandaKwsn.jsp";
    		context.put("mode", "kemaskiniTanda");
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
			
    		
    		listTanda.paparTandaKwsn(id_TandaKawasan);
    		paparTandaKawasan = listTanda.getPaparTandaKawasan();
    		
    		Hashtable hT = (Hashtable)paparTandaKawasan.get(0);
    		context.put("idTandaKawasan",hT.get("id_tandakawasan"));
			context.put("StatusTanda",hT.get("statustanda"));
    		context.put("StatusLaksana",hT.get("statuslaksana"));
			context.put("TarikhTandaDari",hT.get("tarikhmula"));
    		context.put("TarikhTandaHingga",hT.get("tarikhakhir"));
    		context.put("TarikhLawatPeriksa",hT.get("tarikhlawat"));
    		context.put("TarikhLulus", hT.get("tarikhlulus"));
    		context.put("rujAgensi",hT.get("norujagensi"));
    		context.put("namaPegawai",hT.get("namapegawai"));
    		context.put("Alamat1",hT.get("alamat1"));
    		context.put("Alamat2",hT.get("alamat2"));
    		context.put("Alamat3",hT.get("alamat3"));
    		context.put("Poskod",hT.get("poskod"));
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(negeri),null,"class=autoselect onChange=\"doChangeidNegeriUpdate();\""));
    		context.put("SelectBandar",HTML.SelectBandarByNegeri(negeri,"socBandar",Utils.parseLong(bandar), "class=autoselect"));
    			
    		
			listTanda.listTandaKwsn(id_permohonan);
			listTandaKawasan = listTanda.getListTandaKawasan();				
		    context.put("TandaList", listTandaKawasan);
    		
    		
    	}
    	else{
    		 	vm = "app/ppt/frmHakmilikSementaraSenaraiTandaKwsn.jsp";
    			
    			if (!"".equals(getParam("txdTarikh")));
    				tarikhmohon = getParam("txdTarikh");
    		
    			if (!"".equals(getParam("txtNoFail")));
    				nofail = getParam("txtNoFail");
    			
    			if(!"".equals(getParam("socStatus")));
    				cStatus = getParam("socStatus");
    			
    			listTanda.setCarianFail(nofail, tarikhmohon, cStatus);		
    			listCarianFail = listTanda.getListCarianFail();
    								
    		
    		    context.put("PermohonanList", listCarianFail);
    		    context.put("CarianFail", nofail);  
    		    context.put("tarikhPermohonan", tarikhmohon);
    		    context.put("SelectStatus", HTML.SelectStatusPPT("socStatus",Utils.parseLong(cStatus),"style=width:130px"));
    		    setupPage(session,action,listCarianFail);
    	}
       
		
		
		return vm;
	}
	
	private void addTanda(HttpSession session) throws Exception {
		// // add data dalam table notis awam (tempat tampal = atas tanah)
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idPermohonan= getParam("id_permohonan");
		
		h.put("idPermohonan",idPermohonan);
		h.put("StatusTanda", getParam("socStatusTanda"));
		h.put("StatusLaksana",getParam("socStatusLaksana"));
		h.put("TarikhTandaDari", getParam("txdTkhTandaMula"));
		h.put("TarikhTandaHingga",getParam("txdTkhTandaHingga"));
		h.put("TarikhLawatPeriksa",getParam("txdTkhLawatPeriksa"));
		h.put("TarikhLulus",getParam("txdTarikhLulus"));
		h.put("rujAgensi",getParam("txtNoRujAgensi"));
		h.put("namaPegawai",getParam("txtNamaPegawai"));
		h.put("Alamat1",getParam("txtAlamat1Jurukur"));
		h.put("Alamat2",getParam("txtAlamat2Jurukur"));
		h.put("Alamat3",getParam("txtAlamat3Jurukur"));
		h.put("Poskod",getParam("txtPoskod"));
		h.put("Negeri",getParam("socNegeri"));
		h.put("Bandar",getParam("socBandar"));
		h.put("idMasuk",user_id);
		
		
		FrmHakmilikSementaraSenaraiTandaKwsnData.addTanda(h);
	}

	private void deleteTanda(HttpSession session)throws Exception {
		// delete row 
		String idTandaKawasan= getParam("id_tandakawasan");
		
		FrmHakmilikSementaraSenaraiTandaKwsnData.deleteTanda(idTandaKawasan);
		
	}
	
	private void updateTanda(HttpSession session) throws Exception {
		// update table tandakawasan jika berlaku kemaskini
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idPermohonan= getParam("id_permohonan");
		String idTandaKawasan= getParam("id_tandakawasan");
		
		h.put("idPermohonan",idPermohonan);
		h.put("idTandaKawasan",idTandaKawasan);
		h.put("StatusTanda", getParam("socStatusTanda"));
		h.put("StatusLaksana",getParam("socStatusLaksana"));
		h.put("TarikhTandaDari", getParam("txdTkhTandaMula"));
		h.put("TarikhTandaHingga",getParam("txdTkhTandaHingga"));
		h.put("TarikhLawatPeriksa",getParam("txdTkhLawatPeriksa"));
		h.put("TarikhLulus",getParam("txdTarikhLulus"));
		h.put("rujAgensi",getParam("txtNoRujAgensi"));
		h.put("namaPegawai",getParam("txtNamaPegawai"));
		h.put("namaPegawai",getParam("txtNamaPegawai"));
		h.put("Alamat1",getParam("txtAlamat1Jurukur"));
		h.put("Alamat2",getParam("txtAlamat2Jurukur"));
		h.put("Alamat3",getParam("txtAlamat3Jurukur"));
		h.put("Poskod",getParam("txtPoskod"));
		h.put("Negeri",getParam("socNegeri"));
		h.put("Bandar",getParam("socBandar"));
		h.put("idKemaskini",user_id);
		
		FrmHakmilikSementaraSenaraiTandaKwsnData.updateTanda(h);
		
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
