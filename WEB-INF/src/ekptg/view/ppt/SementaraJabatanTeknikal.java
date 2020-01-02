package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPermohonanData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiJabatanTeknikalData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiSuratJabatanTeknikalData;
import ekptg.model.ppt.FrmHakmilikSementaraSuratJabatanTeknikalData;

public class SementaraJabatanTeknikal extends AjaxBasedModule{

	@Override
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String submit = getParam("command");
		String action = getParam("action");
		String tarikhmohon = "";
    	String nofail = "";    
    	String noJKPTG = "";
    	String cStatus = "0";
    	String checkedTidak = "";
    	String checkedYa = "";
    	String flag_Terima = getParam("sorTerimaSuratJT");
    	String id_fail = getParam("id_fail");
		context.put("id_fail", getParam("id_fail"));
		String id_permohonan = getParam("id_permohonan");
		context.put("id_permohonan", getParam("id_permohonan"));
		String idUlasanTeknikal = getParam("idUlasanTeknikal");
		context.put("idUlasanTeknikal",idUlasanTeknikal);
		String jabatan = getParam("socJabatan");
    	HttpSession session = this.request.getSession();
    	
    	FrmHakmilikSementaraSenaraiJabatanTeknikalData listJbtnTeknikal = new FrmHakmilikSementaraSenaraiJabatanTeknikalData();
		FrmHakmilikSementaraMaklumatPermohonanData prmhnnMaster = new FrmHakmilikSementaraMaklumatPermohonanData();
		FrmHakmilikSementaraSuratJabatanTeknikalData srtJbtnTeknikal = new FrmHakmilikSementaraSuratJabatanTeknikalData();
		FrmHakmilikSementaraSenaraiSuratJabatanTeknikalData listSuratJbtnTeknikal = new FrmHakmilikSementaraSenaraiSuratJabatanTeknikalData();
		
		
		Vector list = null;
    	Vector listAgensi = null;
    	Vector alamatKementerian = null;
    	Vector listSuratJabatan = null;
    	Vector maklumatSuratJbtn = null;
    	
    	
		if("viewSenaraiJbtnTeknikal".equals(action)){
			
			
			vm = "app/ppt/frmHakmilikSementaraSenaraiSuratJabatanTeknikal.jsp";

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
			
			listSuratJbtnTeknikal.setListSuratJabatan(id_permohonan);
			listSuratJabatan = listSuratJbtnTeknikal.getListSuratJabatan();
			context.put("PermohonanList",listSuratJabatan);
			
		}
		else if ("tambahSuratJbtn".equals(action)){
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatSuratJabatanTeknikal.jsp";
			
			context.put("mode","newSuratJbtn");
			context.put("readonly","");
			context.put("disabled","");
			
			context.put("SelectJabatan",HTML.SelectJabatanTeknikal("socJabatan",null,"style=width:auto"));
			context.put("BIL_SURAT","");
    		context.put("NO_RUJ_SURAT", "");
    		context.put("TARIKH_SURAT", "");
    		context.put("TARIKH_HANTAR", "");
    		context.put("TEMPOH","");
    		context.put("PERIHAL","");
    		context.put("BIL_SURATJT","");
    		context.put("NO_RUJ_SURATJT","");
    		context.put("TARIKH_SURATJT","");
    		context.put("TARIKH_TERIMAJT","");
    		context.put("KEPUTUSANJT","");
    		context.put("ULASANJT","");

			
			if (getParam("sorTerimaSuratJT").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("sorTerimaSuratJT").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			context.put("flag_Terima",getParam("sorTerimaSuratJT"));
			
			
			
			
		}
		else if ("simpanSuratJbtn".equals(action)){
			
			addSuratJabatan(session);
			edit_Status_Jabatan(session);
			
			vm = "app/ppt/frmHakmilikSementaraSenaraiSuratJabatanTeknikal.jsp";
			
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
			
			listSuratJbtnTeknikal.setListSuratJabatan(id_permohonan);
			listSuratJabatan = listSuratJbtnTeknikal.getListSuratJabatan();
			context.put("PermohonanList",listSuratJabatan);
			
			
		}
		else if ("viewSuratJbtn".equals(action)){
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatSuratJabatanTeknikal.jsp";
			context.put("mode","paparSuratJbtn");
			context.put("readonly","readonly");
			context.put("disabled","disabled");
			
			
			
			srtJbtnTeknikal.setMaklumatSuratJabatan(idUlasanTeknikal);
			maklumatSuratJbtn = srtJbtnTeknikal.getMaklumatSuratJabatan();
			Hashtable h = (Hashtable)maklumatSuratJbtn.get(0);
			
			context.put("SelectJabatan",HTML.SelectJabatanTeknikal("socJabatan",Utils.parseLong(h.get("ID_JABATANTEKNIKAL").toString()),"style=width:auto class=disabled disabled"));
			context.put("BIL_SURAT",h.get("BIL_SURAT").toString());
    		context.put("NO_RUJ_SURAT", h.get("NO_RUJ_SURAT").toString());
    		context.put("TARIKH_SURAT", h.get("TARIKH_SURAT").toString());
    		context.put("TARIKH_HANTAR", h.get("TARIKH_HANTAR").toString());
    		context.put("TEMPOH",h.get("TEMPOH").toString());
    		context.put("PERIHAL",h.get("PERIHAL").toString());
    		context.put("flag_Terima",h.get("FLAG_TERIMA").toString());
    		context.put("BIL_SURATJT",h.get("BIL_SURATJT").toString());
    		context.put("NO_RUJ_SURATJT",h.get("NO_RUJ_SURATJT").toString());
    		context.put("TARIKH_SURATJT",h.get("TARIKH_SURATJT").toString());
    		context.put("TARIKH_TERIMAJT",h.get("TARIKH_TERIMAJT").toString());
    		context.put("KEPUTUSANJT",h.get("KEPUTUSANJT").toString());
    		context.put("ULASANJT",h.get("ULASANJT").toString());

			
			if (getParam("flag_Terima").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("flag_Terima").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
	
		}
		else if ("kemaskiniSuratJbtn".equals(action)){
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatSuratJabatanTeknikal.jsp";
			context.put("mode","kemaskiniSuratJbtn");
			context.put("readonly","");
			context.put("disabled","");
			
			
			srtJbtnTeknikal.setMaklumatSuratJabatan(idUlasanTeknikal);
			maklumatSuratJbtn = srtJbtnTeknikal.getMaklumatSuratJabatan();
			Hashtable h = (Hashtable)maklumatSuratJbtn.get(0);
			
			context.put("SelectJabatan",HTML.SelectJabatanTeknikal("socJabatan",Utils.parseLong(h.get("ID_JABATANTEKNIKAL").toString()),"style=width:auto"));
			context.put("BIL_SURAT",h.get("BIL_SURAT").toString());
    		context.put("NO_RUJ_SURAT", h.get("NO_RUJ_SURAT").toString());
    		context.put("TARIKH_SURAT", h.get("TARIKH_SURAT").toString());
    		context.put("TARIKH_HANTAR", h.get("TARIKH_HANTAR").toString());
    		context.put("TEMPOH",h.get("TEMPOH").toString());
    		context.put("PERIHAL",h.get("PERIHAL").toString());
    		context.put("flag_Terima",h.get("FLAG_TERIMA").toString());
    		context.put("BIL_SURATJT",h.get("BIL_SURATJT").toString());
    		context.put("NO_RUJ_SURATJT",h.get("NO_RUJ_SURATJT").toString());
    		context.put("TARIKH_SURATJT",h.get("TARIKH_SURATJT").toString());
    		context.put("TARIKH_TERIMAJT",h.get("TARIKH_TERIMAJT").toString());
    		context.put("KEPUTUSANJT",h.get("KEPUTUSANJT").toString());
    		context.put("ULASANJT",h.get("ULASANJT").toString());

			
			if (getParam("flag_Terima").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("flag_Terima").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			
		}
		else if ("updateSuratJbtn".equals(action)){
			
			updateSuratJbtn(session);
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatSuratJabatanTeknikal.jsp";
			context.put("mode","paparSuratJbtn");
			context.put("readonly","readonly");
			context.put("disabled","disabled");
			
			
			
			srtJbtnTeknikal.setMaklumatSuratJabatan(idUlasanTeknikal);
			maklumatSuratJbtn = srtJbtnTeknikal.getMaklumatSuratJabatan();
			Hashtable h = (Hashtable)maklumatSuratJbtn.get(0);
			
			context.put("SelectJabatan",HTML.SelectJabatanTeknikal("socJabatan",Utils.parseLong(h.get("ID_JABATANTEKNIKAL").toString()),"style=width:auto class=disabled disabled"));
			context.put("BIL_SURAT",h.get("BIL_SURAT").toString());
    		context.put("NO_RUJ_SURAT", h.get("NO_RUJ_SURAT").toString());
    		context.put("TARIKH_SURAT", h.get("TARIKH_SURAT").toString());
    		context.put("TARIKH_HANTAR", h.get("TARIKH_HANTAR").toString());
    		context.put("TEMPOH",h.get("TEMPOH").toString());
    		context.put("PERIHAL",h.get("PERIHAL").toString());
    		context.put("flag_Terima",h.get("FLAG_TERIMA").toString());
    		context.put("BIL_SURATJT",h.get("BIL_SURATJT").toString());
    		context.put("NO_RUJ_SURATJT",h.get("NO_RUJ_SURATJT").toString());
    		context.put("TARIKH_SURATJT",h.get("TARIKH_SURATJT").toString());
    		context.put("TARIKH_TERIMAJT",h.get("TARIKH_TERIMAJT").toString());
    		context.put("KEPUTUSANJT",h.get("KEPUTUSANJT").toString());
    		context.put("ULASANJT",h.get("ULASANJT").toString());

			
			if (getParam("flag_Terima").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("flag_Terima").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			
		}
		else if ("terimaSuratJbtn".equals(action)){
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatSuratJabatanTeknikal.jsp";
			
			context.put("mode","newSuratJbtn");
			context.put("readonly","");
			context.put("disabled","");
			
    		
			context.put("SelectJabatan",HTML.SelectJabatanTeknikal("socJabatan",Utils.parseLong(jabatan),"style=width:auto"));
    		context.put("BIL_SURAT",getParam("txtBilSurat"));
			context.put("NO_RUJ_SURAT", getParam("txtNoRujSurat"));
    		context.put("TARIKH_SURAT", getParam("txdTarikhSurat"));
    		context.put("TARIKH_HANTAR", getParam("txdTarikhHantar"));
    		context.put("TEMPOH",getParam("txtTempoh"));
    		context.put("PERIHAL",getParam("txtPerihal"));
			
			if (getParam("sorTerimaSuratJT").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("sorTerimaSuratJT").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			context.put("flag_Terima",flag_Terima);
			
		}
		else if ("tidakTerimaSuratJbtn".equals(action)){
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatSuratJabatanTeknikal.jsp";
			
			context.put("mode","newSuratJbtn");
			context.put("readonly","");
			context.put("disabled","");
			
    		
			context.put("SelectJabatan",HTML.SelectJabatanTeknikal("socJabatan",Utils.parseLong(jabatan),"style=width:auto"));
    		context.put("BIL_SURAT",getParam("txtBilSurat"));
			context.put("NO_RUJ_SURAT", getParam("txtNoRujSurat"));
    		context.put("TARIKH_SURAT", getParam("txdTarikhSurat"));
    		context.put("TARIKH_HANTAR", getParam("txdTarikhHantar"));
    		context.put("TEMPOH",getParam("txtTempoh"));
    		context.put("PERIHAL",getParam("txtPerihal"));

    		
			
			if (getParam("sorTerimaSuratJT").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("sorTerimaSuratJT").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			context.put("flag_Terima",flag_Terima);
			
		}
		else if ("terimaSuratJbtnUpdate".equals(action)){
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatSuratJabatanTeknikal.jsp";
			
			context.put("mode","kemaskiniSuratJbtn");
			context.put("readonly","");
			context.put("disabled","");
			
    		
			context.put("SelectJabatan",HTML.SelectJabatanTeknikal("socJabatan",Utils.parseLong(jabatan),"style=width:auto"));
    		context.put("BIL_SURAT",getParam("txtBilSurat"));
			context.put("NO_RUJ_SURAT", getParam("txtNoRujSurat"));
    		context.put("TARIKH_SURAT", getParam("txdTarikhSurat"));
    		context.put("TARIKH_HANTAR", getParam("txdTarikhHantar"));
    		context.put("TEMPOH",getParam("txtTempoh"));
    		context.put("PERIHAL",getParam("txtPerihal"));
			
			if (getParam("sorTerimaSuratJT").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("sorTerimaSuratJT").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			context.put("flag_Terima",flag_Terima);
			
		}
		else if ("tidakTerimaSuratJbtnUpdate".equals(action)){
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatSuratJabatanTeknikal.jsp";
			
			context.put("mode","kemaskiniSuratJbtn");
			context.put("readonly","");
			context.put("disabled","");
			
    		
			context.put("SelectJabatan",HTML.SelectJabatanTeknikal("socJabatan",Utils.parseLong(jabatan),"style=width:auto"));
    		context.put("BIL_SURAT",getParam("txtBilSurat"));
			context.put("NO_RUJ_SURAT", getParam("txtNoRujSurat"));
    		context.put("TARIKH_SURAT", getParam("txdTarikhSurat"));
    		context.put("TARIKH_HANTAR", getParam("txdTarikhHantar"));
    		context.put("TEMPOH",getParam("txtTempoh"));
    		context.put("PERIHAL",getParam("txtPerihal"));

    		
			
			if (getParam("sorTerimaSuratJT").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("sorTerimaSuratJT").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			context.put("flag_Terima",flag_Terima);
			
		}
		else if ("hapusSuratJbtn".equals(action)){
			
			deleteSuratJabatan(session);
			
			vm = "app/ppt/frmHakmilikSementaraSenaraiSuratJabatanTeknikal.jsp";
			
			
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
			
			listSuratJbtnTeknikal.setListSuratJabatan(id_permohonan);
			listSuratJabatan = listSuratJbtnTeknikal.getListSuratJabatan();
			context.put("PermohonanList",listSuratJabatan);
			
			
		}
		else{
			
			vm = "app/ppt/frmHakmilikSementaraSenaraiJabatanTeknikal.jsp";
			
			if (!"".equals(getParam("txdTarikh")));
			tarikhmohon = getParam("txdTarikh");
		
			if (!"".equals(getParam("txtNoFail")));
				nofail = getParam("txtNoFail");
			
			if(!"".equals(getParam("socStatus")))
				cStatus = getParam("socStatus");
			
			listJbtnTeknikal.setCarianFail(nofail, tarikhmohon, cStatus);		
			list = listJbtnTeknikal.getList();
								
		
		    context.put("PermohonanList", list);
		    context.put("list_size", list.size());
		    context.put("CarianFail", nofail);  
		    context.put("tarikhPermohonan", tarikhmohon);
		    context.put("SelectStatus", HTML.SelectStatusPPT("socStatus",Utils.parseLong(cStatus),"style=width:130px"));
		    setupPage(session,action,list);
			
		}
		
		
		
		return vm;
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
	
	private void addSuratJabatan(HttpSession session) throws Exception{
	    
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String flagTerima = getParam("sorTerimaSuratJT");

	    	h.put("id_permohonan", getParam("id_permohonan"));
	    	h.put("jabatan", getParam("socJabatan"));
	    	h.put("bilSurat",getParam("txtBilSurat"));
	    	h.put("noRujSurat", getParam("txtNoRujSurat"));
	    	h.put("tarikhSurat", getParam("txdTarikhSurat"));
	    	h.put("tarikhHantar", getParam("txdTarikhHantar"));
	    	h.put("tempoh", getParam("txtTempoh"));
	    	h.put("perihal", getParam("txtPerihal"));
	   
	    	h.put("flagTerima", getParam("sorTerimaSuratJT"));
	    	
	    	if (flagTerima.equals("1")){
	    		h.put("bilSuratJT", getParam("txtBilSuratTerima"));
	    		h.put("noRujSuratJT", getParam("txtNoRujSurat"));
	    		h.put("txdTkhSurat", getParam("txdTkhSurat"));
	    		h.put("tarikhTerimaJT", getParam("txdTkhTerima"));
	    		h.put("keputusanJT", getParam("socKeputusanAdd"));
	    		h.put("ulasanJT",getParam("txtUlasan"));
	    	}
	    	h.put("id_Masuk",user_id);
	    
	    	FrmHakmilikSementaraSuratJabatanTeknikalData.addSuratJabatan(h);
	    
	   }//close add_maklumat_tanah
	
		private void updateSuratJbtn(HttpSession session) throws Exception{
	
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String flagTerima = getParam("sorTerimaSuratJT");
		String idUlasanTeknikal = getParam("idUlasanTeknikal");

	    	h.put("id_ulasanteknikal",idUlasanTeknikal);
	    	h.put("jabatan", getParam("socJabatan"));
	    	h.put("bilSurat",getParam("txtBilSurat"));
	    	h.put("noRujSurat", getParam("txtNoRujSurat"));
	    	h.put("tarikhSurat", getParam("txdTarikhSurat"));
	    	h.put("tarikhHantar", getParam("txdTarikhHantar"));
	    	h.put("tempoh", getParam("txtTempoh"));
	    	h.put("perihal", getParam("txtPerihal"));
	    	
	    	
	    	h.put("flagTerima", getParam("sorTerimaSuratJT"));
	    	
	    	if (flagTerima.equals("1")){
	    		h.put("bilSuratJT", getParam("txtBilSuratTerima"));
	    		h.put("noRujSuratJT", getParam("txtNoRujSurat"));
	    		h.put("txdTkhSurat", getParam("txdTkhSurat"));
	    		h.put("tarikhTerimaJT", getParam("txdTkhTerima"));
	    		h.put("keputusanJT", getParam("socKeputusanAdd"));
	    		h.put("ulasanJT",getParam("txtUlasan"));
	    	}
	    	h.put("id_Kemaskini",user_id);
	    
	    	FrmHakmilikSementaraSuratJabatanTeknikalData.updateSuratJabatan(h);
	    
	   }//close add_maklumat_tanah
 
	private void edit_Status_Jabatan(HttpSession session) throws Exception {
		String id_permohonan = getParam("id_permohonan");
	    
	    Hashtable h = null;
	    h = new Hashtable();

	    h.put("id_permohonan", id_permohonan);

	    FrmHakmilikSementaraSuratJabatanTeknikalData.edit_status(h);
	}
	
	private void deleteSuratJabatan(HttpSession session) throws Exception{
	    String id = getParam("idUlasanTeknikal");	    
	    FrmHakmilikSementaraSuratJabatanTeknikalData.deleteJabatanTeknikal(id);
	  }
}
