package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmRekodPendaftaranHakmilikSementaraData;


public class FrmRekodHakmilikSementara extends AjaxBasedModule{
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FrmRekodHakmilikSementara.class);
	@Override
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String submit = getParam("command");//1st level
		log.info("submit :" +submit);
		
		String action = getParam("action");//2nd		
		this.context.put("action",action);
		log.info("action :" +action);
		
		String firstAction = getParam("firstAction");
		this.context.put("firstAction",firstAction);
		log.info("firstAction :" +firstAction);
		
		String nextAction = getParam("nextAction");	
		this.context.put("nextAction",nextAction);
		log.info("nextAction :" +nextAction);

		String msg ="Hakmilik "+getParam("txtHakmilikAsal")+" telah dibatalkan. No Hakmilik Sambungan "+getParam("txtHakmilikBerikut")+" telah berjaya didaftarkan";
		this.context.put("msg", msg);
		
        String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
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
		String idJenisHakmilikBaru = getParam("socJenisHakmilikBaru");
		if (idJenisHakmilikBaru == null || idJenisHakmilikBaru.trim().length() == 0){
			idJenisHakmilikBaru = "99999";
		}
		String idJenisHakmilikAsal = getParam("socJenisHakmilikAsal");
		if (idJenisHakmilikAsal == null || idJenisHakmilikAsal.trim().length() == 0){
			idJenisHakmilikAsal = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}
		String idLot = getParam("socJenisLot");
		if (idLot == null || idLot.trim().length() == 0){
			idLot = "99999";
		}
		String noFail = getParam("txtNoFail");
		String noHakmilik = getParam("txtNoHakmilikC");
		String noWarta = getParam("txtNoWarta");
		String noLot = getParam("txtNoLotC");
		
		Vector list =null;
		Vector listSambungan = null;
		HttpSession session = this.request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
		
		String noHakmilikAsal = getParam("txtNoHakmilik");
		this.context.put("modePopup", "");
		
		//VIEW SENARAI HAKMILIK DAN RIZAB
		if ("".equals(firstAction))		
		{
			vm = "app/htp/frmRekodSenaraiHakmilikSementara.jsp";
			//UNTUK HOLD VALUE SUPAYA DOCHANGE NEGERI/DAERAH TAK UBAH TERUS
			if("".equals(action)){
				list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
			}
			this.context.put("SenaraiTanah", list);
	    	this.context.put("idJenisTanah", idJenisTanah);	
	    	if ("1".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "selected");
				this.context.put("selected2", "");
	    	} 
	    	else 
	    	if ("2".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "selected");
	    	} 
	    	else {
	    		this.context.put("selected", "selected");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
	    	}
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
		    this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
			this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
			this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
			this.context.put("txtNoFail", "");
			this.context.put("txtNoHakmilikC", "");
			this.context.put("txtNoWarta", "");
			this.context.put("txtNoLotC", "");
			setupPage(session,action,list);
		}
		else
		//VIEW CARIAN HAKMILIK DAN RIZAB BY idNegeri,idDaerah,idMukim,noHakmilik
		if("carianHakmilikRizab".equals(firstAction)){
				
			vm = "app/htp/frmRekodSenaraiHakmilikSementara.jsp";
				
		    	this.context.put("idJenisTanah", idJenisTanah);	
		    	if ("1".equals(idJenisTanah)) {
					this.context.put("selected", "");
					this.context.put("selected1", "selected");
					this.context.put("selected2", "");
		    	} 
		    	else 
		    	if ("2".equals(idJenisTanah)) {
					this.context.put("selected", "");
					this.context.put("selected1", "");
					this.context.put("selected2", "selected");
		    	} 
		    	else {
		    		this.context.put("selected", "selected");
					this.context.put("selected1", "");
					this.context.put("selected2", "");
		    	}
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
				this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));	
				list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
				this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
				this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
		    	this.context.put("txtNoFail", noFail);
		    	this.context.put("txtNoHakmilikC", noHakmilik);
		    	this.context.put("txtNoWarta", noWarta);
		    	this.context.put("txtNoLotC", noLot);

			setupPage(session,action,list);
			if ("doChangeState".equals(nextAction)) {
		    	this.context.put("idJenisTanah", idJenisTanah);	
		    	if ("1".equals(idJenisTanah)) {
					this.context.put("selected", "");
					this.context.put("selected1", "selected");
					this.context.put("selected2", "");
		    	} 
		    	else 
		    	if ("2".equals(idJenisTanah)) {
					this.context.put("selected", "");
					this.context.put("selected1", "");
					this.context.put("selected2", "selected");
		    	} 
		    	else {
		    		this.context.put("selected", "selected");
					this.context.put("selected1", "");
					this.context.put("selected2", "");
		    	}
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
		    	this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
		    	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
		    	this.context.put("txtNoFail", "");
		    	this.context.put("txtNoHakmilikC", "");
				this.context.put("txtNoWarta", "");
				this.context.put("txtNoLotC", "");		
				list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
				this.context.put("SenaraiHakmilik", list);
			}
			else
			if ("doChangeDaerah".equals(nextAction)) {
		    	this.context.put("idJenisTanah", idJenisTanah);	
		    	if ("1".equals(idJenisTanah)) {
					this.context.put("selected", "");
					this.context.put("selected1", "selected");
					this.context.put("selected2", "");
		    	} 
		    	else 
		    	if ("2".equals(idJenisTanah)) {
					this.context.put("selected", "");
					this.context.put("selected1", "");
					this.context.put("selected2", "selected");
		    	} 
		    	else {
		    		this.context.put("selected", "selected");
					this.context.put("selected1", "");
					this.context.put("selected2", "");
		    	}
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim),"",""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
		    	this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
		    	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
		    	this.context.put("txtNoFail", "");
		    	this.context.put("txtNoHakmilikC", "");
				this.context.put("txtNoWarta", "");
				this.context.put("txtNoLotC", "");
				list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
				this.context.put("SenaraiHakmilik", list);
			}	
		}
		else
		//VIEW HAKMILIK BY ID
		if ("paparDetailHakmilik".equals(firstAction))
		{
			//vm = "app/htp/newDesign2.jsp";	
			vm = "app/htp/frmRekodPendaftaranHakmilikSementara.jsp";
			this.context.put("readOnly", "readonly");
			this.context.put("disabled", "disabled");
			this.context.put("mode", "view");
			
			// VIEW MAKLUMAT FAIL (MASTER) BY ID
			view_modeMaklumatFail(session);

			// VIEW MAKLUMAT HAKMILIK BY ID
			view_modeHakmilikSementara(session);
					
		 }	
		else
		//KEMASKINI DETAIL MAKLUMAT HAKMILIK
	    if ("kemaskiniDetailHakmilik".equals(firstAction))	
	    {
	    	//vm = "app/htp/newDesign2.jsp";	
	    	vm = "app/htp/frmRekodPendaftaranHakmilikSementara.jsp";
			this.context.put("readOnly", "");
			this.context.put("disabled", "");
			this.context.put("mode", "update");

			// view Maklumat Fail by ID
			view_modeMaklumatFail(session);

			// view Maklumat Hakmilik by ID
			view_modeHakmilikSementara(session);
			
			
			//LIST HAKMILIK SAMBUNGAN
			listSambungan = view_modeSenaraiHakmilikSambungan(session,noHakmilikAsal);
			this.context.put("listSambungan",listSambungan);
			
			this.context.put("selectJenisHakmilikBaru", HTML.SelectJenisHakmilik("socJenisHakmilikBaru",Utils.parseLong(idJenisHakmilikBaru), " style=\"width:200px\""));
			this.context.put("txtHakmilikBerikut","");
			
	    }
		//UPDATE DETAIL MAKLUMAT HAKMILIK
		if("updateDetailHakmilik".equals(firstAction)){
			
			vm = "app/htp/frmRekodPendaftaranHakmilikSementara.jsp";
			//vm = "app/htp/newDesign2.jsp";
			//UPDATE FAIL LAMA KEPADA STATUS BATAL
			Hashtable hHakmilikUpdate = new Hashtable();
			hHakmilikUpdate.put("idHakmilik", getParam("idHakmilik"));
			hHakmilikUpdate.put("txtKemAgenTerkini", getParam("txtKemAgenTerkini"));
			hHakmilikUpdate.put("socStatus", getParam("socStatus"));
			hHakmilikUpdate.put("socJenisHakmilikBaru", getParam("socJenisHakmilikBaru"));
			hHakmilikUpdate.put("txtHakmilikBerikut", getParam("txtHakmilikBerikut"));
			hHakmilikUpdate.put("idKemaskini",session.getAttribute("_ekptg_user_id").toString());

			FrmRekodPendaftaranHakmilikSementaraData.updateHakmilikById(hHakmilikUpdate);

			//PAPAR SEMULA HAKMILIK YANG DIUPDATE
			this.context.put("readOnly", "readonly");
			this.context.put("disabled", "disabled");	
			//this.context.put("mode", "view");
			view_modeHakmilikRizab(session,submit);
			
			//LIST HAKMILIK SAMBUNGAN
			listSambungan = view_modeSenaraiHakmilikSambungan(session,noHakmilikAsal);
			this.context.put("listSambungan",listSambungan);

		}

		else
		//ADD HAMILIK BARU
		if("addHakmilikBaru".equals(firstAction)){
			vm = "app/htp/frmRekodPendaftaranHakmilikSementara.jsp";
			this.context.put("modePopup", "openSambungan");
			this.context.put("socStatus","S");
			this.context.put("readOnly", "readonly");
			this.context.put("disabled", "disabled");
			this.context.put("readOnly2", "");
			this.context.put("disabled2", "");
			this.context.put("mode","simpanBaru");
			this.context.put("selectJenisHakmilikBaru", HTML.SelectJenisHakmilik("socJenisHakmilikBaru",Utils.parseLong(idJenisHakmilikBaru), " style=\"width:200px\""));
			this.context.put("txtHakmilikBerikut","");
			view_modeHakmilikRizab(session,submit);
			
			//LIST HAKMILIK SAMBUNGAN
			listSambungan = view_modeSenaraiHakmilikSambungan(session,noHakmilikAsal);
			this.context.put("listSambungan",listSambungan);
		}
		//DAFTAR HAMILIK BARU
		if("daftarHakmilik".equals(firstAction)){
			 vm = "app/htp/frmRekodPendaftaranHakmilikSementara.jsp";
			this.context.put("sambung","sambung");	
			this.context.put("txtHakmilikBerikut","");
			
			//CREATE FAIL BARU BASE ON DATA LAME 
			Hashtable hHakmilikBaru = new Hashtable();
			hHakmilikBaru.put("idHakmilik", getParam("idHakmilik"));
			hHakmilikBaru.put("socNegeriHR", getParam("socNegeriHR"));
			hHakmilikBaru.put("socDaerahHR", getParam("socDaerahHR"));
			hHakmilikBaru.put("socMukimHR", getParam("socMukimHR"));
			hHakmilikBaru.put("socJenisHakmilikHR", getParam("socJenisHakmilikHR"));
			hHakmilikBaru.put("txtNoHakmilik", getParam("txtNoHakmilik"));	
			hHakmilikBaru.put("socLotHR", getParam("socLotHR"));
			hHakmilikBaru.put("txtNoLot", getParam("txtNoLot"));
			hHakmilikBaru.put("txdTarikhTerima", getParam("txdTarikhTerima"));
			hHakmilikBaru.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));
			hHakmilikBaru.put("txtNoBangunan", getParam("txtNoBangunan"));
			hHakmilikBaru.put("txtNoTingkat", getParam("txtNoTingkat"));
			hHakmilikBaru.put("txtNoPetak", getParam("txtNoPetak"));
			hHakmilikBaru.put("txtCukaiTahun", getParam("txtCukaiTahun"));
			hHakmilikBaru.put("txtLokasi", getParam("txtLokasi"));	
			hHakmilikBaru.put("socLuas", getParam("socLuas"));
			hHakmilikBaru.put("txtLuas", getParam("txtLuas"));
			hHakmilikBaru.put("socTaraf", getParam("socTaraf"));
			hHakmilikBaru.put("socKategori", getParam("socKategori"));			
			hHakmilikBaru.put("txtNoPelan", getParam("txtNoPelan"));
			hHakmilikBaru.put("txtTempoh", getParam("txtTempoh"));			
			hHakmilikBaru.put("txtSyarat", getParam("txtSyarat"));
			hHakmilikBaru.put("txtNoFailJopa", getParam("txtNoFailJopa"));			
			hHakmilikBaru.put("txtHakmilikAsal", getParam("txtHakmilikAsal"));
			hHakmilikBaru.put("txtCukaiTerkini", getParam("txtCukaiTerkini"));
			hHakmilikBaru.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));
			hHakmilikBaru.put("txtTarafHakmilik", getParam("txtTarafHakmilik"));
			hHakmilikBaru.put("txdTarikhLuput", getParam("txdTarikhLuput"));			
			hHakmilikBaru.put("txtNoPu", getParam("txtNoPu"));
			hHakmilikBaru.put("txdTarikhWarta", getParam("txdTarikhWarta"));
			hHakmilikBaru.put("txtKawasanRizab", getParam("txtKawasanRizab"));
			hHakmilikBaru.put("txtNoSyit", getParam("txtNoSyit"));
			hHakmilikBaru.put("txtNoWarta", getParam("txtNoWarta"));
			hHakmilikBaru.put("txtSekatan", getParam("txtSekatan"));
			hHakmilikBaru.put("txtSyarat", getParam("txtSyarat"));
			hHakmilikBaru.put("txtKawasanRizab", getParam("txtKawasanRizab"));
			hHakmilikBaru.put("txtHakmilikBerikut", getParam("txtHakmilikBerikut"));
			hHakmilikBaru.put("socStatus", getParam("socStatus"));
			hHakmilikBaru.put("txdTarikhKemaskini", getParam("txdTarikhKemaskini"));			
			hHakmilikBaru.put("socJenisRizab", getParam("socJenisRizab"));	
			hHakmilikBaru.put("socRizab", getParam("socRizab"));	
			hHakmilikBaru.put("socRizab", getParam("socRizab"));	
			hHakmilikBaru.put("idKemaskini",session.getAttribute("_ekptg_user_id").toString());
			
			
			//CREATE FAIL BARU BASE ON NEW INPUT
			hHakmilikBaru.put("socJenisHakmilikBaru", getParam("socJenisHakmilikBaru"));
			hHakmilikBaru.put("txtHakmilikBerikut", getParam("txtHakmilikBerikut"));
			FrmRekodPendaftaranHakmilikSementaraData.addHakmilikBaruById(hHakmilikBaru);
			
			//VIEW HAKMILIK ASAL
			view_modeHakmilikRizab(session,submit);
			
			//LIST HAKMILIK SAMBUNGAN
			listSambungan = view_modeSenaraiHakmilikSambungan(session,noHakmilikAsal);
			this.context.put("listSambungan",listSambungan);
			
			this.context.put("txtHakmilikBerikut", "");
			
			
		}
		//DELETE REKOD IMAGE
		if("deleteHakmilikBaru".equals(firstAction)){	  
			 vm = "app/htp/frmRekodPendaftaranHakmilikSementara.jsp";
			FrmRekodPendaftaranHakmilikSementaraData.hapusHakmilikBaruById(getParam("idHakmilikBaru"));
			
			//VIEW HAKMILIK ASAL
			view_modeHakmilikRizab(session,submit);
			
			//LIST HAKMILIK SAMBUNGAN
			this.context.put("socStatus", "S");
			listSambungan = view_modeSenaraiHakmilikSambungan(session,noHakmilikAsal);
			this.context.put("listSambungan",listSambungan);


		}
		
		log.info(vm);
		return vm;
	}
	// VIEW CARIAN FAIL HAKMILIK DAN RIZAB	
	private Vector view_modeCarianFailHakmilikDanRizab(HttpSession session,String idJenisTanah,String idNegeri,String idDaerah,String idMukim,String noFail,String idJenisHakmilik,String noHakmilik,String noWarta,String idLot,String noLot, String idAgensi) throws Exception {
		return FrmRekodPendaftaranHakmilikSementaraData.getCarianSenaraiHakmilikSementaraById(idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);		
	}
	// view_modeSenaraiHakmilikSambungan
	private Vector view_modeSenaraiHakmilikSambungan(HttpSession session,String noHakmilikAsal) throws Exception {
		return FrmRekodPendaftaranHakmilikSementaraData.getSenaraiHakmilikSambungan(noHakmilikAsal);	
		
	}
//	// VIEW SENARAI FAIL HAKMILIK DAN RIZAB
//	private Vector view_modeSenaraiFail(HttpSession session) throws Exception {
//		return FrmRekodPendaftaranHakmilikSementaraData.getPaparSenaraiHakmilikSementara();
//	}
	// VIEW MAKLUMAT FAIL BY ID (MASTER)
	private void view_modeMaklumatFail(HttpSession session) throws Exception {
		String idPermohonan = getParam("idHakmilik");	
		Vector list =null;
		list = FrmRekodPendaftaranHakmilikSementaraData.getPaparMaklumatFailById(idPermohonan);
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
		this.context.put("txtNoLot", (String)hMaklumatFail.get("noLot"));	
		this.context.put("caraPerolehan", (String)hMaklumatFail.get("caraPerolehan"));
	}
    // VIEW MAKLUMAT DETAIL HAKMILIK DAN RIZAB BY ID 
	private void view_modeHakmilikSementara(HttpSession session) throws Exception {
		String idHakmilik = getParam("idHakmilik");
		Vector list =null;
		list = FrmRekodPendaftaranHakmilikSementaraData.getPaparHakmilikSementaraById(idHakmilik);
		Hashtable hHakmilik = (Hashtable) list.get(0);

		this.context.put("idHakmilik", (String)hHakmilik.get("idHakmilik"));
		this.context.put("txdTarikhTerima", (String)hHakmilik.get("tarikhTerima"));
		this.context.put("txdTarikhDaftar", (String)hHakmilik.get("tarikhDaftar"));
		this.context.put("txtCukaiTahun", (String)hHakmilik.get("cukai"));
		this.context.put("txtLokasi", (String)hHakmilik.get("lokasi"));
        this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong((String)hHakmilik.get("idNegeriHR")),"disabled", " style=\"width:200px\" onChange=\"doChangeStateHR();\" class=\"disabled\""));
	    this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri((String)hHakmilik.get("idNegeriHR"),"socDaerahHR", Utils.parseLong((String)hHakmilik.get("idDaerahHR")),"disabled"," style=\"width:200px\" onChange=\"doChangeDaerahHR();\" class=\"disabled\""));
	    this.context.put("selectMukimHR", HTML.SelectMukimByDaerah((String)hHakmilik.get("idDaerahHR"),"socMukimHR", Utils.parseLong((String)hHakmilik.get("idMukimHR")),"disabled"," style=\"width:200px\" class=\"disabled\""));
	    this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong((String)hHakmilik.get("idJenisHakmilikHR")),"disabled", " style=\"width:200px\"  class=\"disabled\""));
	    this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong((String)hHakmilik.get("idLot")),"disabled", " style=\"width:200px\" class=\"disabled\""));
		//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), "disabled"," style=\"width:200px\" class=\"disabled\""));
	    this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
	    this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), "disabled"," style=\"width:200px\" class=\"disabled\""));
		this.context.put("selectRizab", HTML.SelectRizab("socRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), "disabled", " style=\"width:200px\" class=\"disabled\""));
		this.context.put("kodJenis", (String)hHakmilik.get("kodJenis"));
		this.context.put("noHakmilik", (String)hHakmilik.get("noHakmilik"));
		this.context.put("txtCukaiTerkini",(String)hHakmilik.get("cukaiTerkini"));
		this.context.put("txtNoBangunan",(String)hHakmilik.get("noBangunan"));
		this.context.put("txtNoTingkat",(String)hHakmilik.get("noTingkat"));
		this.context.put("txtNoPetak",(String)hHakmilik.get("noPetak"));
		this.context.put("txtNoPelan", (String)hHakmilik.get("noPelan"));
		this.context.put("txtTempoh", (String)hHakmilik.get("tempoh"));
		this.context.put("txtSyarat", (String)hHakmilik.get("syarat"));
		this.context.put("txtHakmilikAsal", (String)hHakmilik.get("hakmilikAsal"));
		this.context.put("txtNoFailJopa", (String)hHakmilik.get("noFailJopa"));
		this.context.put("txtTarafHakmilik", (String)hHakmilik.get("tarafHakmilik"));
		this.context.put("txdTarikhLuput", (String)hHakmilik.get("tarikhLuput"));
		this.context.put("txtCukaiTerkini", (String)hHakmilik.get("cukaiTerkini"));
		this.context.put("txtKegunaanTanah", (String)hHakmilik.get("kegunaanTanah"));
		this.context.put("txtLuas", (String)hHakmilik.get("luas"));
		this.context.put("txtNoPu", (String)hHakmilik.get("noPu"));
		this.context.put("txdTarikhWarta",(String)hHakmilik.get("tarikhWarta"));
		this.context.put("txtNoWarta",(String)hHakmilik.get("noWarta"));
		this.context.put("txtNoRizab",(String)hHakmilik.get("noRizab"));
		this.context.put("txdTarikhRizab",(String)hHakmilik.get("tarikhRizab"));
		this.context.put("txtKawasanRizab",(String)hHakmilik.get("kawasanRizab"));
		this.context.put("txtNoSyit",(String)hHakmilik.get("noSyit"));
		this.context.put("txtSekatan",(String)hHakmilik.get("sekatan"));
		this.context.put("txtHakmilikBerikut",(String)hHakmilik.get("hakmilikBerikut"));
		this.context.put("socStatus", getParam("socStatus") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatus"));
		this.context.put("txtKemAgenTerkini", getParam("txtKemAgenTerkini") == "" ? (String)hHakmilik.get("catatan"):getParam("txtKemAgenTerkini"));
		this.context.put("socTaraf",(String)hHakmilik.get("socTaraf"));
		this.context.put("socRizab", (String)hHakmilik.get("socRizab"));
		this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
		this.context.put("txdTarikhKemaskini", (String)hHakmilik.get("tarikhKemaskini"));
		this.context.put("txtPegawaiAkhir", (String)hHakmilik.get("userName"));
		this.context.put("socLuas",(String)hHakmilik.get("idLuasLama"));
		this.context.put("txtLuasLama",(String)hHakmilik.get("luasLama"));
		this.context.put("txtLuas",(String)hHakmilik.get("luasConvert"));		
		this.context.put("txtNoHakmilikAsal",(String)hHakmilik.get("hakmilikAsal"));		
	}
    // VIEW MAKLUMAT DETAIL HAKMILIK DAN RIZAB BY ID 
	private void view_modeHakmilikRizab(HttpSession session,String submit) throws Exception {
		String idHakmilik = getParam("idHakmilik");
		log.info("idHakmilik :"+id);
		Vector list =null;
		list = FrmRekodPendaftaranHakmilikSementaraData.getPaparHakmilikSementaraById(idHakmilik);
		Hashtable hHakmilik = (Hashtable) list.get(0);

		this.context.put("idHakmilik", (String)hHakmilik.get("idHakmilik"));
		this.context.put("txdTarikhTerima",getParam("txdTarikhTerima") == "" ? (String)hHakmilik.get("tarikhTerima"):getParam("txdTarikhTerima"));
		this.context.put("txdTarikhDaftar",getParam("txdTarikhDaftar") == "" ?  (String)hHakmilik.get("tarikhDaftar"):getParam("txdTarikhDaftar"));
		this.context.put("txtCukaiTahun",getParam("txtCukaiTahun") == "" ? (String)hHakmilik.get("cukai"):getParam("txtCukaiTahun"));
		this.context.put("txtLokasi",getParam("txtLokasi") == "" ? (String)hHakmilik.get("lokasi"):getParam("txtLokasi"));
		if(submit.equals("kemaskiniDetailHakmilik")||submit.equals("kemaskiniDetailRizab")){
			//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), " style='width:200px;'"));
		    this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), " style='width:200px;'" ));
			this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), " style='width:200px;'"));
			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), " style='width:200px;'"));
		}
		else
			if(submit.equals("updateDetailHakmilik")|| submit.equals("updateDetailRizab")||submit.equals("paparDetailHakmilik")||submit.equals("paparDetailRizab")){
				//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), "disabled"," style=\"width:200px\" class=\"disabled\""));
				this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
				this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), "disabled"," style=\"width:200px\" class=\"disabled\""));
				this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), "disabled", " style=\"width:200px\" class=\"disabled\""));
			}	
		this.context.put("txtNoPelan",getParam("txtNoPelan") == "" ? (String)hHakmilik.get("noPelan"):getParam("txtNoPelan"));
		this.context.put("txtTempoh",getParam("txtTempoh") == "" ? (String)hHakmilik.get("tempoh"):getParam("txtTempoh"));
		this.context.put("txtSyarat",getParam("txtSyarat") == "" ? (String)hHakmilik.get("syarat"):getParam("txtSyarat"));
		this.context.put("txtHakmilikAsal",getParam("txtHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtHakmilikAsal"));
		this.context.put("txtNoFailJopa",getParam("txtNoFailJopa") == "" ? (String)hHakmilik.get("noFailJopa"):getParam("txtNoFailJopa"));
		this.context.put("txtTarafHakmilik",getParam("txtTarafHakmilik") == "" ? (String)hHakmilik.get("tarafHakmilik"):getParam("txtTarafHakmilik"));
		this.context.put("txdTarikhLuput",getParam("txdTarikhLuput") == "" ? (String)hHakmilik.get("tarikhLuput"):getParam("txdTarikhLuput"));
		this.context.put("txtCukaiTerkini",getParam("txtCukaiTerkini") == "" ? (String)hHakmilik.get("cukaiTerkini"):getParam("txtCukaiTerkini"));
		this.context.put("txtKegunaanTanah",getParam("txtKegunaanTanah") == "" ? (String)hHakmilik.get("kegunaanTanah"):getParam("txtKegunaanTanah"));
		this.context.put("txtLuas",getParam("txtLuas") == "" ? (String)hHakmilik.get("luas"):getParam("txtLuas"));
		this.context.put("txtNoPu",getParam("txtNoPu") == "" ? (String)hHakmilik.get("noPu"):getParam("txtNoPu"));
		this.context.put("txdTarikhWarta",getParam("txdTarikhWarta") == "" ? (String)hHakmilik.get("tarikhWarta"):getParam("txdTarikhWarta"));
		this.context.put("txtKawasanRizab",getParam("txtKawasanRizab") == "" ? (String)hHakmilik.get("kawasanRizab"):getParam("txtKawasanRizab"));
		this.context.put("txtNoSyit",getParam("txtNoSyit") == "" ? (String)hHakmilik.get("noSyit"):getParam("txtNoSyit"));
		this.context.put("txtNoWarta",getParam("txtNoWarta") == "" ? (String)hHakmilik.get("noWarta"):getParam("txtNoWarta"));
		this.context.put("txtSekatan",getParam("txtSekatan") == "" ? (String)hHakmilik.get("sekatan"):getParam("txtSekatan"));
		this.context.put("txtHakmilikAsal",getParam("txtHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtHakmilikAsal"));
		this.context.put("txtHakmilikBerikut",getParam("txtHakmilikBerikut") == "" ? (String)hHakmilik.get("hakmilikBerikut"):getParam("txtHakmilikBerikut"));
		this.context.put("socStatus",getParam("socStatus") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatus"));
		this.context.put("socTaraf", getParam("socTaraf") == "" ? (String)hHakmilik.get("socTaraf"):getParam("socTaraf"));
		this.context.put("socRizab", getParam("socRizab") == "" ? (String)hHakmilik.get("socRizab"):getParam("socRizab"));
		this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
		this.context.put("txdTarikhKemaskini", (String)hHakmilik.get("tarikhKemaskini"));	
		this.context.put("txtNoHakmilikAsal",getParam("txtNoHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtNoHakmilikAsal"));
		this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini") == "" ? (String)hHakmilik.get("catatan"):getParam("txtKemAgenTerkini"));
		
	}	
	// SETUP PAGING
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
}
