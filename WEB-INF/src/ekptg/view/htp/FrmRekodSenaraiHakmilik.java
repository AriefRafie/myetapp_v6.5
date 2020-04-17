package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmRekodPendaftaranHakmilikRizabData;

public class FrmRekodSenaraiHakmilik extends AjaxBasedModule{
		private static final long serialVersionUID = 1L;
		private static Logger log = Logger.getLogger(ekptg.view.htp.FrmRekodSenaraiHakmilik.class);
		
		@Override
		public String doTemplate2() throws Exception {
			
			//Get all parameters from query String
			String name="";
			String value="";
			Enumeration allparam = request.getParameterNames();
			for (; allparam.hasMoreElements(); ) {
		        // Get the name of the request parameter
		        name = (String)allparam.nextElement();
		        // Get the value of the request parameter
		        value = request.getParameter(name);
		        log.debug(name +"="+value);
			}

			String vm = "";
			String submit = getParam("command");//1st level
			log.info("submit :" +submit);
			
			String action = getParam("action");//2nd		
			this.context.put("action",action);
			log.info("action :" +action);
			
			String idRizab = getParam("socRizab");
			if (idRizab == null || idRizab.trim().length() == 0){
				idRizab = "99999";
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
			String idLuas = getParam("socLuas");
			if (idLuas == null || idLuas.trim().length() == 0){
				idLuas = "99999";
			}		
			String idKategori = getParam("socKategori");
			if (idKategori == null || idKategori.trim().length() == 0){
				idKategori = "99999";
			}		
			String noHakmilik = getParam("txtNoHakmilik");
			Vector list =null;
			HttpSession session = this.request.getSession();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			//VIEW SENARAI HAKMILIK DAN RIZAB
			if ("".equals(submit))		
			{
				vm = "app/htp/frmRekodSenaraiHakmilikRizab.jsp";
				list = view_modeSenaraiFail(session);
				this.context.put("SenaraiHakmilik", list);
						
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
				this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
				this.context.put("txtNoHakmilik", "");
				setupPage(session,action,list);

			}
			//VIEW CARIAN HAKMILIK DAN RIZAB BY idNegeri,idDaerah,idMukim,noHakmilik
			if("carianHakmilikRizab".equals(submit)){
				
				vm = "app/htp/frmRekodSenaraiHakmilikRizab.jsp";
				
				// if carian = null
				if(idNegeri.equals("99999") && idDaerah.equals("99999") && idMukim.equals("99999") && noHakmilik.isEmpty()){
					
					list = view_modeSenaraiFail(session);
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
					this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
					this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
					this.context.put("txtNoHakmilik", "");
					this.context.put("SenaraiHakmilik", list);
					
				}
				else
				{
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
					this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
					this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
					
					list = view_modeCarianFailHakmilikDanRizab(session,idNegeri,idDaerah,idMukim,noHakmilik);
					this.context.put("txtNoHakmilik", noHakmilik);
					this.context.put("idNegeriL",idNegeri);
					this.context.put("idDaerahL",idDaerah);
					this.context.put("idMukimL",idMukim);
					this.context.put("noHakmilikL",noHakmilik);
					this.context.put("SenaraiHakmilik", list);
				}
				setupPage(session,action,list);
			}
			else
			//VIEW HAKMILIK BY ID
			if ("paparDetailHakmilik".equals(submit))
			{
				vm = "app/htp/frmRekodPendaftaranHakmilik.jsp";
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");
			
				// VIEW MAKLUMAT FAIL (MASTER) BY ID
				view_modeMaklumatFail(session);

				// VIEW MAKLUMAT HAKMILIK BY ID
				view_modeHakmilikRizab(session,submit);
		    }
			//VIEW RIZAB BY ID
			else
			if ("paparDetailRizab".equals(submit))
			{
				vm = "app/htp/frmRekodPendaftaranRizab.jsp";
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");
				
				// VIEW MAKLUMAT FAIL (MASTER) BY ID
				view_modeMaklumatFail(session);
				
				// VIEW MAKLUMAT RIZAB BY ID
				view_modeHakmilikRizab(session,submit);
		    }
			else
			//KEMASKINI DETAIL MAKLUMAT HAKMILIK
		    if ("kemaskiniDetailHakmilik".equals(submit))	
		    {
		    	vm = "app/htp/frmRekodPendaftaranHakmilik.jsp";
				this.context.put("readOnly", "");
				this.context.put("disabled", "");	
				this.context.put("mode", "update");
				
				// view Maklumat Fail by ID
				view_modeMaklumatFail(session);

				// view Maklumat Hakmilik by ID
				view_modeHakmilikRizab(session,submit);
		    }
		    else
			//KEMASKINI DETAIL MAKLUMAT RIZAB
		    if ("kemaskiniDetailRizab".equals(submit))	
		    {
		    	vm = "app/htp/frmRekodPendaftaranRizab.jsp";
				this.context.put("readOnly", "");
				this.context.put("disabled", "");	
				this.context.put("mode", "update");
				view_modeHakmilikRizab(session,submit);
		    }
		    else
			//UPDATE DETAIL MAKLUMAT HAKMILIK
		    if ("updateDetailHakmilik".equals(submit))	
		    {
		    	vm = "app/htp/frmRekodPendaftaranHakmilik.jsp";
				this.context.put("readOnly", "");
				this.context.put("disabled", "");
				Hashtable hHakmilikUpdate = new Hashtable();
				hHakmilikUpdate.put("idHakmilik", getParam("idHakmilik"));
				hHakmilikUpdate.put("txdTarikhTerima", getParam("txdTarikhTerima"));
				hHakmilikUpdate.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));
				hHakmilikUpdate.put("txtCukaiTahun", getParam("txtCukaiTahun"));
				hHakmilikUpdate.put("txtLokasi", getParam("txtLokasi"));			
				hHakmilikUpdate.put("socLuas", getParam("socLuas"));
				hHakmilikUpdate.put("socKategori", getParam("socKategori"));			
				hHakmilikUpdate.put("txtNoPelan", getParam("txtNoPelan"));
				hHakmilikUpdate.put("txtTempoh", getParam("txtTempoh"));			
				hHakmilikUpdate.put("txtSyarat", getParam("txtSyarat"));
				hHakmilikUpdate.put("txtNoFailJopa", getParam("txtNoFailJopa"));			
				hHakmilikUpdate.put("txtHakmilikAsal", getParam("txtHakmilikAsal"));
				hHakmilikUpdate.put("txtCukaiTerkini", getParam("txtCukaiTerkini"));
				hHakmilikUpdate.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));
				hHakmilikUpdate.put("txtTarafHakmilik", getParam("txtTarafHakmilik"));
				hHakmilikUpdate.put("txdTarikhLuput", getParam("txdTarikhLuput"));			
				hHakmilikUpdate.put("txtNoPu", getParam("txtNoPu"));
				hHakmilikUpdate.put("txdTarikhWarta", getParam("txdTarikhWarta"));
				hHakmilikUpdate.put("txtKawasanRizab", getParam("txtKawasanRizab"));
				hHakmilikUpdate.put("txtNoSyit", getParam("txtNoSyit"));
				hHakmilikUpdate.put("txtNoWarta", getParam("txtNoWarta"));
				hHakmilikUpdate.put("txtSekatan", getParam("txtSekatan"));
				hHakmilikUpdate.put("txtKawasanRizab", getParam("txtKawasanRizab"));
				hHakmilikUpdate.put("txtHakmilikBerikut", getParam("txtHakmilikBerikut"));
				hHakmilikUpdate.put("socStatus", getParam("socStatus"));
				hHakmilikUpdate.put("txdTarikhKemaskini", getParam("txdTarikhKemaskini"));			
				hHakmilikUpdate.put("socRizab", getParam("socRizab"));	
				FrmRekodPendaftaranHakmilikRizabData.updateHakmilikById(hHakmilikUpdate);
				
				//PAPAR SEMULA HAKMILIK YANG DIUPDATE
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");	
				this.context.put("mode", "view");
				view_modeHakmilikRizab(session,submit);
		    }
		    else
			//UPDATE DETAIL RIZAB
		    if ("updateDetailRizab".equals(submit))	
		    {
		    	vm = "app/htp/frmRekodPendaftaranRizab.jsp";
				this.context.put("readOnly", "");
				this.context.put("disabled", "");
				Hashtable hRizabUpdate = new Hashtable();
				hRizabUpdate.put("idHakmilik", getParam("idHakmilik"));
				hRizabUpdate.put("txdTarikhTerima", getParam("txdTarikhTerima"));
				hRizabUpdate.put("socLuas", getParam("socLuas"));
				hRizabUpdate.put("txtNoPelan", getParam("txtNoPelan"));
				hRizabUpdate.put("txtNoPu", getParam("txtNoPu"));
				hRizabUpdate.put("txtLokasi", getParam("txtLokasi"));	
				hRizabUpdate.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));
				hRizabUpdate.put("txtNoFailJopa", getParam("txtNoFailJopa"));	
				hRizabUpdate.put("socStatus", getParam("socStatus"));
				hRizabUpdate.put("txdTarikhKemaskini", getParam("txdTarikhKemaskini"));			
				FrmRekodPendaftaranHakmilikRizabData.XupdateRizabById(hRizabUpdate);
				
				//PAPAR SEMULA RIZAB YANG DIUPDATE
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");	
				this.context.put("mode", "view");
				view_modeHakmilikRizab(session,submit);
		    }	
		    else 
		    if(("doChanges".equals(submit)) || ("record_listing".equals(submit))){	
		    	vm = "app/htp/frmRekodSenaraiHakmilikRizab.jsp";
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
				this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
				this.context.put("txtNoHakmilik", "");
				list = view_modeSenaraiFail(session);
				this.context.put("SenaraiHakmilik", list);
		    	setupPage(session,action,list);
		    }
		    else
			if ("doChangeState".equals(action)) {
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("txtNoHakmilik", "");
				//err.FrmRekodPendaftaranHakmilikRizabData.ge
				this.context.put("SenaraiHakmilik", list);
			}
			else
			if ("doChangeDaerah".equals(action)) {
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim),"",""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("txtNoHakmilik", "");
			}

			return vm;
		}
		// VIEW SENARAI FAIL HAKMILIK DAN RIZAB
		private Vector view_modeSenaraiFail(HttpSession session) throws Exception {
			return FrmRekodPendaftaranHakmilikRizabData.getPaparSenaraiHakmilikRizab();
		}
		// VIEW MAKLUMAT FAIL BY ID (MASTER)
		private void view_modeMaklumatFail(HttpSession session) throws Exception {
			String idHakmilik = getParam("idHakmilik");	
			Vector list =null;

			list = FrmRekodPendaftaranHakmilikRizabData.getPaparMaklumatFailById(idHakmilik);
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
		}
	    // VIEW MAKLUMAT DETAIL HAKMILIK DAN RIZAB BY ID 
		private void view_modeHakmilikRizab(HttpSession session,String submit) throws Exception {
			//int id = Integer.parseInt(getParam("idHakmilik"));
			Vector list =null;
			list = FrmRekodPendaftaranHakmilikRizabData.getPaparHakmilikRizabById(getParam("idHakmilik"));
			Hashtable hHakmilik = (Hashtable) list.get(0);

			this.context.put("idHakmilik", (String)hHakmilik.get("idHakmilik"));
			this.context.put("txdTarikhTerima", (String)hHakmilik.get("tarikhTerima"));
			this.context.put("txdTarikhDaftar", (String)hHakmilik.get("tarikhDaftar"));
			this.context.put("txtCukaiTahun", (String)hHakmilik.get("cukai"));
			this.context.put("txtLokasi", (String)hHakmilik.get("lokasi"));
			if(submit.equals("kemaskiniDetailHakmilik")||submit.equals("kemaskiniDetailRizab")){
				this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), " style='width:200px;'"));
				this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), " style='width:200px;'"));
				this.context.put("selectRizab", HTML.SelectRizab("socRizab",Utils.parseLong((String)hHakmilik.get("idRizab")), " style='width:200px;'"));
			}
			else
				if(submit.equals("updateDetailHakmilik")|| submit.equals("updateDetailRizab")||submit.equals("paparDetailHakmilik")||submit.equals("paparDetailRizab")){
				  this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), "disabled"," style=\"width:200px\" class=\"disabled\""));
				  this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), "disabled"," style=\"width:200px\" class=\"disabled\""));
				  this.context.put("selectRizab", HTML.SelectRizab("socRizab",Utils.parseLong((String)hHakmilik.get("idRizab")), "disabled", " style=\"width:200px\" class=\"disabled\""));
				}	
			this.context.put("txtNoPelan", (String)hHakmilik.get("noPelan"));
			this.context.put("txtTempoh", (String)hHakmilik.get("tempoh"));
			this.context.put("txtSyarat", (String)hHakmilik.get("syarat"));
			this.context.put("txtHakmilikAsal", (String)hHakmilik.get("hakmilikAsal"));
			this.context.put("txtNoFailJopa", (String)hHakmilik.get("noFailJopa"));
			this.context.put("txtTarafHakmilik", (String)hHakmilik.get("tarafHakmilik"));
			this.context.put("txdTarikhLuput", (String)hHakmilik.get("tarikhLuput"));
			this.context.put("txtCukaiTerkini", (String)hHakmilik.get("cukaiTerkini"));
			this.context.put("txtKegunaanTanah", (String)hHakmilik.get("kegunaanTanah"));
			this.context.put("txtNoPu", (String)hHakmilik.get("noPu"));
			this.context.put("txdTarikhWarta", (String)hHakmilik.get("tarikhWarta"));
			this.context.put("txtKawasanRizab", (String)hHakmilik.get("kawasanRizab"));
			this.context.put("txtNoSyit", (String)hHakmilik.get("noSyit"));
			this.context.put("txtNoWarta", (String)hHakmilik.get("noWarta"));
			this.context.put("txtSekatan", (String)hHakmilik.get("sekatan"));
			this.context.put("txtHakmilikBerikut", (String)hHakmilik.get("hakmilikBerikut"));
			this.context.put("socStatus", (String)hHakmilik.get("socStatus"));
			this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
			this.context.put("txdTarikhKemaskini", (String)hHakmilik.get("tarikhKemaskini"));				
		}
		// VIEW CARIAN FAIL HAKMILIK DAN RIZAB	
		private Vector view_modeCarianFailHakmilikDanRizab(HttpSession session,String idNegeri,String idDaerah,String idMukim,String noHakmilik) throws Exception {
			return FrmRekodPendaftaranHakmilikRizabData.getCarianSenaraiHakmilikRizabById(idNegeri,idDaerah,idMukim,noHakmilik,"","","","","","","","","");
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

