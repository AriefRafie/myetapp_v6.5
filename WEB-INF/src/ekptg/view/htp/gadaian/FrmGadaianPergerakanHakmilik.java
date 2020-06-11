package ekptg.view.htp.gadaian;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmRekodPergerakanHakmilikData;
import ekptg.model.htp.IHTPHakmilikUrusan;
import ekptg.model.htp.gadaian.HTPHakmilikUrusanGadaianBean;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;
import ekptg.model.htp.rekod.HakmilikPergerakanGadaianBean;
import ekptg.model.htp.rekod.ITanah;
import ekptg.model.htp.rekod.ITanahDaftar;
import ekptg.model.htp.rekod.TanahBean;

public class FrmGadaianPergerakanHakmilik extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 478357183096758258L;
	private final String PATH="app/htp/gadaian/pergerakan/";
	private ITanah iHakmilik = null;
 	private IHTPHakmilikUrusan iHakmilikUrusan = null;  
 	private ITanahDaftar iHakmilikGadaian = null;  
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.gadaian.FrmGadaianPergerakanHakmilik.class);

	public String doTemplate2() throws Exception {

		String vm = "";		
		String submit = getParam("command");//1st level
		String action = getParam("action");//2nd		
		this.context.put("action",action);
		
		Vector listPergerakan =null;
		HttpSession session = this.request.getSession();
		
		String idHakmilik = getParam("idHakmilik");
		this.context.put("idHakmilik",idHakmilik);
		
        String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
		}
		String idLot = getParam("socJenisLot");
		if (idLot == null || idLot.trim().length() == 0){
			idLot = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}
		String idMukim = getParam("socMukim");
		if (idMukim == null || idMukim.trim().length() == 0){
			idMukim = "99999";
		}				
		String idJenisHakmilik = getParam("socJenisHakmilik");
		if (idJenisHakmilik == null || idJenisHakmilik.trim().length() == 0){
			idJenisHakmilik = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}
		String noFail = getParam("txtNoFail");
		String noHakmilik = getParam("txtNoHakmilik");
		String noWarta = getParam("txtNoWarta");
		String noLot = getParam("txtNoLot");
		String idPergerakan = getParam("idPergerakan");
		
		String firstAction = getParam("firstAction");
		this.context.put("firstAction",firstAction);
		String nextAction = getParam("nextAction");	
		this.context.put("nextAction",nextAction);
		myLog.info("submit=" +submit+",action=" +action+",firstAction=" +firstAction+",nextAction=" +nextAction);
		
		//VIEW SENARAI HAKMILIK DAN RIZAB
		if (firstAction.equals("")){
			vm = PATH+"index.jsp";			
			//listPergerakan = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
			listPergerakan = getIHakmilikUrusan().getCarianSenaraiHakmilik(idJenisTanah
							, idNegeri, idDaerah, idMukim
							, noFail
							, idJenisHakmilik, noHakmilik
							, noWarta
							, idLot, noLot
							, idAgensi);
			this.context.put("SenaraiHakmilik", listPergerakan);
					
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
			this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
			this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
			//this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
			this.context.put("txtNoFail", "");
			this.context.put("txtNoHakmilik", "");
			this.context.put("txtNoWarta", "");
			this.context.put("txtNoLot", "");
			setupPage(session,action,listPergerakan);
		
		//VIEW CARIAN HAKMILIK DAN RIZAB BY idNegeri,idDaerah,idMukim,noHakmilik
		}else if("carianHakmilikRizab".equals(firstAction)){				
			vm = PATH+"index.jsp";
				
			// if carian = null
			if(idNegeri.equals("99999") && idDaerah.equals("99999") && idMukim.equals("99999") && noHakmilik.isEmpty()){					
				//listPergerakan = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
				listPergerakan = getIHakmilikUrusan().getCarianSenaraiHakmilik(idJenisTanah
						, idNegeri, idDaerah, idMukim
						, noFail
						, idJenisHakmilik, noHakmilik
						, noWarta
						, idLot, noLot
						, idAgensi);

				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
				this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
				this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
				//this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
				this.context.put("txtNoFail", "");
				this.context.put("txtNoHakmilik", "");
				this.context.put("txtNoLot", "");
				this.context.put("SenaraiHakmilik", listPergerakan);
				
			}else{
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
				this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
				this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
				//this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
				//listPergerakan = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
				listPergerakan = getIHakmilikUrusan().getCarianSenaraiHakmilik(idJenisTanah
						, idNegeri, idDaerah, idMukim
						, noFail
						, idJenisHakmilik, noHakmilik
						, noWarta
						, idLot, noLot
						, idAgensi);

				this.context.put("txtNoFail",noFail);
				this.context.put("txtNoHakmilik", noHakmilik);
		    	this.context.put("txtNoWarta", noWarta);
		    	this.context.put("txtNoLot", noLot);
				this.context.put("SenaraiHakmilik", listPergerakan);
			}

			setupPage(session,action,listPergerakan);
			if ("doChangeState".equals(nextAction)) {
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				//this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim),"",""));
				//this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
				this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
				this.context.put("txtNoFail", "");
				this.context.put("txtNoHakmilik", "");
				this.context.put("txtNoWarta", "");
				this.context.put("txtNoLot", "");
			
			}else if ("doChangeDaerah".equals(nextAction)) {
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
					//this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
					this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
					this.context.put("txtNoFail", "");
					this.context.put("txtNoHakmilik", "");
					this.context.put("txtNoWarta", "");
					this.context.put("txtNoLot", "");
			}

		//VIEW SENARAI PERGERAKAN HAKMILIK BY ID
		}else if ("paparDetailPergerakanHakmilik".equals(firstAction)){
			vm = PATH+"frmRekodPergerakanHakmilik.jsp";
			this.context.put("readonly", "readonly=\"readonly\"");
			this.context.put("disabled", "disabled");
			this.context.put("mode", "view");		
			// VIEW MAKLUMAT FAIL
			viewMaklumatFailMengikutHakmilik(idHakmilik);
			
			// VIEW MAKLUMAT PERGERAKAN HAKMILIK BY ID
			listPergerakan = view_modeSenaraiPergerakanFail();
			this.context.put("SenaraiPergerakan", listPergerakan);
			setupPagePergerakanHakmilikDetail(session,action,listPergerakan);		
				
		// view page tambah pergerakan
		}else if(firstAction.equals("tambahPergerakanHakmilik")){
	    	vm = PATH+"frmRekodPergerakanTambah.jsp";
			this.context.put("readonly", "");
			this.context.put("disabled", "");
			this.context.put("mode", "new");
					
			this.context.put("txtKeterangan","");
			this.context.put("txtKepada","");
			this.context.put("txdTarikhSerah","");
			this.context.put("txtSalinan","");
			this.context.put("socStatusS","");
			this.context.put("txdTarikhKembali","");
			this.context.put("txtCatatan","");
			this.context.put("sorDokumen","");
			this.context.put("failRujukanPer","");
			this.context.put("txTajukPer","");
			
		// add new rekod to tblhtppergerakan
	    }else if("tambahPergerakanHakmilikDetail".equals(firstAction)){	
	    	vm = PATH+"frmRekodPergerakanTambah.jsp";
			Hashtable<String,String> hPergerakanAdd = new Hashtable<String,String>();
			hPergerakanAdd.put("idHakmilik", getParam("idHakmilik"));
			hPergerakanAdd.put("keterangan", getParam("txtKeterangan"));
			hPergerakanAdd.put("kepada", getParam("txtKepada"));
			hPergerakanAdd.put("tarikhSerah", getParam("txdTarikhSerah"));
			hPergerakanAdd.put("socStatusS", getParam("socStatusS"));
			hPergerakanAdd.put("bilSalinan", getParam("txtSalinan"));
			hPergerakanAdd.put("txdTarikhKembali", getParam("txdTarikhKembali"));
			hPergerakanAdd.put("txtCatatan", getParam("txtCatatan"));		
			hPergerakanAdd.put("sorDokumen", getParam("sorDokumen"));
			hPergerakanAdd.put("idKemaskini",session.getAttribute("_ekptg_user_id").toString());
			hPergerakanAdd.put("failRujukanPer", getParam("txtfailrujukanper"));
			hPergerakanAdd.put("txTajukPer", getParam("txtajukper"));
			//String idPergerakanBaru = FrmRekodPergerakanHakmilikData.addPergerakan(hPergerakanAdd);
			String idPergerakanBaru = getIPergerakan().simpan(hPergerakanAdd);
			// view balik lepas insert
			this.context.put("readonly", "readonly=\"readonly\"");
			this.context.put("disabled", "disabled");
			this.context.put("mode", "view");
			view_modePergerakanByIdPergerakan(session,idPergerakanBaru);

		// kemaskini pergerakan hakmilik
	    }else if(firstAction.equals("kemaskiniPergerakanHakmilikDetail")){			
	    	vm = PATH+"frmRekodPergerakanTambah.jsp";
			this.context.put("readonly", "");
			this.context.put("disabled", "");
			this.context.put("mode", "update");
			view_modePergerakanByIdPergerakan(session,idPergerakan);
		
		// update pergerakan hakmilik
	    }else if("updatePergerakanHakmilikDetail".equals(firstAction)){	
	    	vm = PATH+"frmRekodPergerakanTambah.jsp";
			
			viewMaklumatFailMengikutHakmilik(idHakmilik);
			Hashtable<String,String> hPergerakanUpdate = new Hashtable<String,String>();
			hPergerakanUpdate.put("idPergerakan", getParam("idPergerakan"));
			hPergerakanUpdate.put("idHakmilik", getParam("idHakmilik"));
			hPergerakanUpdate.put("keterangan", getParam("txtKeterangan"));
			hPergerakanUpdate.put("kepada", getParam("txtKepada"));
			hPergerakanUpdate.put("tarikhSerah", getParam("txdTarikhSerah"));
			myLog.info("getParam(\"socStatus\")="+getParam("socStatusS"));
			hPergerakanUpdate.put("socStatus", getParam("socStatusS"));
			hPergerakanUpdate.put("bilSalinan", getParam("txtSalinan"));
			hPergerakanUpdate.put("txdTarikhKembali", getParam("txdTarikhKembali"));
			hPergerakanUpdate.put("txtCatatan", getParam("txtCatatan"));
			hPergerakanUpdate.put("sorDokumen", getParam("sorDokumen"));
			hPergerakanUpdate.put("idKemaskini",session.getAttribute("_ekptg_user_id").toString());
			myLog.info(getParam("txtfailrujukanper"));
			hPergerakanUpdate.put("failRujukanPer", getParam("txtfailrujukanper"));
			hPergerakanUpdate.put("txTajukPer", getParam("txtajukper"));
			getIPergerakan().kemaskini(hPergerakanUpdate);
			
			// view balik lepas insert
			this.context.put("readonly", "readonly=\"readonly\"");
			this.context.put("disabled", "disabled");
			this.context.put("mode", "view");
			view_modePergerakanByIdPergerakan(session,idPergerakan);
			
		// view pergerakan hakmilik by idpergerakan
		}else if(firstAction.equals("paparPergerakanHakmilikDetail")){		
			vm = PATH+"frmRekodPergerakanTambah.jsp";
			myLog.info("paparPergerakanHakmilikDetail:idpergerakan = "+idPergerakan);
			view_modePergerakanByIdPergerakan(session,idPergerakan);
		
		}else if(firstAction.equals("paparPergerakanHakmilik")){		
			vm = PATH+"frmRekodPergerakanTambah.jsp";
			this.context.put("readonly", "readonly=\"readonly\"");
			this.context.put("disabled", "disabled");
			this.context.put("mode", "view");
			paparPergerakanByIdPergerakan(session,idPergerakan);
		
		}
		if((submit.equals("doChanges")) || (submit.equals("record_listing"))){	
		    vm = PATH+"index.jsp";
			myLog.info("submit = "+submit);
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
			this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
			this.context.put("txtNoHakmilik", "");
		    //listPergerakan = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
			listPergerakan = getIHakmilikUrusan().getCarianSenaraiHakmilik(idJenisTanah
					, idNegeri, idDaerah, idMukim
					, noFail
					, idJenisHakmilik, noHakmilik
					, noWarta
					, idLot, noLot
					, idAgensi);
			this.context.put("SenaraiHakmilik", listPergerakan);
			setupPage(session,action,listPergerakan);
		
		}else if (submit.equals("hapus")){
			vm = PATH+"frmRekodPergerakanHakmilik.jsp";
			this.context.put("readonly", "readonly=\"readonly\"");
			this.context.put("disabled", "disabled");
			this.context.put("mode", "view");
			getIPergerakan().hapus(idPergerakan);
			// VIEW MAKLUMAT FAIL
			viewMaklumatFailMengikutHakmilik(idHakmilik);
			// VIEW MAKLUMAT PERGERAKAN HAKMILIK BY ID
			listPergerakan = view_modeSenaraiPergerakanFail();
			this.context.put("SenaraiPergerakan", listPergerakan);
			setupPagePergerakanHakmilikDetail(session,action,listPergerakan);		
			
		}
		return vm;
		
	}
	// VIEW SENARAI FAIL PERGERAKAN BY ID_HAKMILIK
	private Vector<Hashtable<String,String>> view_modeSenaraiPergerakanFail() throws Exception {
		return  getIPergerakan().getSenaraiMaklumat(getParam("idHakmilik"));
	}
	private void view_modePergerakanByIdPergerakan(HttpSession session,String idPergerakan) throws Exception {
		//Vector list =null;
		//list = FrmRekodPergerakanHakmilikData.getMaklumatPergerakanById(idPergerakan);
		Hashtable<String,String> hPergerakanById = getIPergerakan().getMaklumat(idPergerakan);
		this.context.put("idPergerakan",getParam("idPergerakan") == "" ? (String)hPergerakanById.get("idPergerakan"):getParam("idPergerakan"));
		this.context.put("txtKeterangan",getParam("txtKeterangan") == "" ? (String)hPergerakanById.get("keterangan"):getParam("txtKeterangan"));
		this.context.put("txtKepada", getParam("txtKepada") == "" ? (String)hPergerakanById.get("kepada"):getParam("txtKepada"));
		this.context.put("txdTarikhSerah", getParam("txdTarikhSerah")=="" ? (String)hPergerakanById.get("tarikhSerahan"): getParam("txdTarikhSerah"));
		this.context.put("txtSalinan",getParam("txtSalinan") =="" ? (String)hPergerakanById.get("bilSalinan"):getParam("txtSalinan"));
		this.context.put("socStatusS",getParam("socStatusS")== "" ? (String)hPergerakanById.get("socStatusS"):getParam("socStatusS"));
		this.context.put("txdTarikhKembali",getParam("txdTarikhKembali") =="" ? (String)hPergerakanById.get("tarikhKembali"):getParam("txdTarikhKembali"));
		this.context.put("txtCatatan",getParam("txtCatatan")== "" ? (String)hPergerakanById.get("catatan"):getParam("txtCatatan"));
		this.context.put("sorDokumen",getParam("sorDokumen")== "" ? (String)hPergerakanById.get("sorDokumen"):getParam("sorDokumen"));
		this.context.put("failRujukanPer",getParam("txtfailrujukanper")== "" ? (String)hPergerakanById.get("failRujukanPer"):getParam("txtfailrujukanper"));
		this.context.put("txTajukPer",getParam("txtajukper")== "" ? (String)hPergerakanById.get("txTajukPer"):getParam("txtajukper"));
		
	}
	
	private void paparPergerakanByIdPergerakan(HttpSession session,String idPergerakan) throws Exception {
//		Vector list =null;
//		list = FrmRekodPergerakanHakmilikData.getMaklumatPergerakanById(idPergerakan);
//		Hashtable hPergerakanById = (Hashtable) list.get(0);
		Hashtable<String,String> hPergerakanById = getIPergerakan().getMaklumat(idPergerakan);

		this.context.put("idPergerakan",(String)hPergerakanById.get("idPergerakan"));
		this.context.put("txtKeterangan",(String)hPergerakanById.get("keterangan"));
		this.context.put("txtKepada",(String)hPergerakanById.get("kepada"));
		this.context.put("txdTarikhSerah",(String)hPergerakanById.get("tarikhSerahan"));
		this.context.put("txtSalinan",(String)hPergerakanById.get("bilSalinan"));
		this.context.put("socStatusS",(String)hPergerakanById.get("socStatusS"));
		this.context.put("txdTarikhKembali",(String)hPergerakanById.get("tarikhKembali"));
		this.context.put("txtCatatan",(String)hPergerakanById.get("catatan"));
		this.context.put("sorDokumen",(String)hPergerakanById.get("sorDokumen"));
		this.context.put("failRujukanPer",(String)hPergerakanById.get("failRujukanPer"));
		this.context.put("txTajukPer",(String)hPergerakanById.get("txTajukPer"));

	}
	// VIEW CARIAN FAIL HAKMILIK DAN RIZAB	

	private Vector view_modeCarianFailHakmilikDanRizab(HttpSession session,String idJenisTanah,String idNegeri,String idDaerah,String idMukim,String noFail,String idJenisHakmilik,String noHakmilik,String noWarta,String idLot,String noLot, String idAgensi) throws Exception {
		return FrmRekodPergerakanHakmilikData.getCarianSenaraiHakmilikRizabById(idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);		
	}
	// VIEW MAKLUMAT FAIL BY ID (MASTER)
	private void Xview_modeMaklumatFail(HttpSession session) throws Exception {
		//int id = Integer.parseInt(getParam("idHakmilik"));
		Vector list =null;
		list = FrmRekodPergerakanHakmilikData.getPaparMaklumatFailById(String.valueOf(getParam("idHakmilik")));
		Hashtable hMaklumatFail = (Hashtable) list.get(0);
		
		this.context.put("txtFailPTG",(String)hMaklumatFail.get("noFailPtg"));
		this.context.put("txtTajuk",(String)hMaklumatFail.get("tajukFail"));
		this.context.put("txtNamaKementerian", (String)hMaklumatFail.get("namaKementerian"));
		this.context.put("txtNoFailSeksyen", (String)hMaklumatFail.get("noFailSeksyen"));
		this.context.put("txtNamaNegeri", (String)hMaklumatFail.get("namaNegeri"));
		this.context.put("txtNamaDaerah", (String)hMaklumatFail.get("namaDaerah"));
		this.context.put("txtNamaMukim", (String)hMaklumatFail.get("namaMukim"));
		this.context.put("txtNamaAgensi", (String)hMaklumatFail.get("namaAgensi"));
		this.context.put("txtJenisHakmilik", (String)hMaklumatFail.get("jenisHakmilik"));
		this.context.put("txtFailKJP", (String)hMaklumatFail.get("noFailKjp"));
		this.context.put("txtNoWarta", (String)hMaklumatFail.get("noWarta"));
		this.context.put("txtNoHakmilik",(String) hMaklumatFail.get("noHakmilik"));
		this.context.put("selectLot", (String)hMaklumatFail.get("jenisLot"));	
		this.context.put("txtNoLot", (String)hMaklumatFail.get("noLot"));	
		this.context.put("caraPerolehan", (String)hMaklumatFail.get("caraPerolehan"));	
	}
	// SETUP PAGING FOR LIST SENARAI PERGERAKAN HAKMILIK
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
		this.context.put("SenaraiHakmilik",paging.getPage(page));
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
	// SETUP PAGING FOR LIST SENARAI PERGERAKAN HAKMILIK	
	public void setupPagePergerakanHakmilikDetail(HttpSession session,String action,Vector list) {
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
		this.context.put("SenaraiHakmilik",paging.getPage(page));
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
	
	// PAPARAN HEADER, Dibuat pada 2017/10/06
	private String viewMaklumatFailMengikutHakmilik(String idHakmilik) throws Exception {
		myLog.info("idHakmilik:"+idHakmilik);
//		Vector list =null;
//		list = getIHakmilik().getPaparMaklumatFailById(idHakmilik);
		Hashtable<String,String> hMaklumatFail = getIHakmilik().getHakmilikUrusan(idHakmilik,true);
		//HakMilik hm = getIHakmilik().getHakmilikUrusan(idHakmilik,true);
		this.context.put("txtNamaNegeri", (String)hMaklumatFail.get("namaNegeri"));
		this.context.put("txtNamaDaerah", (String)hMaklumatFail.get("namaDaerah"));
		this.context.put("txtNamaMukim", (String)hMaklumatFail.get("namaMukim"));
		this.context.put("selectLot", (String)hMaklumatFail.get("kodLot"));	
		this.context.put("txtNoLot", (String)hMaklumatFail.get("noLot"));	
		this.context.put("txtJenisHakmilik", (String)hMaklumatFail.get("kodJenisHakmilik"));
		this.context.put("txtNoHakmilik", (String)hMaklumatFail.get("noHakmilik"));
		this.context.put("txtNoBangunan", (String)hMaklumatFail.get("noBangunan"));
		this.context.put("txtNoTingkat", (String)hMaklumatFail.get("noTingkat"));
		this.context.put("txtNoPetak", (String)hMaklumatFail.get("noPetak"));
		this.context.put("txtNoWarta", (String)hMaklumatFail.get("noWarta"));

		return idHakmilik;
		
	}
	
	private ITanah getIHakmilik(){
		if (iHakmilik == null){
			iHakmilik = new TanahBean();
		}
		return iHakmilik;
	}
	
	private IHTPHakmilikUrusan getIHakmilikUrusan(){
		if(iHakmilikUrusan== null)
			iHakmilikUrusan = new HTPHakmilikUrusanGadaianBean();
		return iHakmilikUrusan;
	}
	
	private ITanahDaftar getIPergerakan(){
		if(iHakmilikGadaian== null)
			iHakmilikGadaian = new HakmilikPergerakanGadaianBean();
		return iHakmilikGadaian;
	}
	
	
}

