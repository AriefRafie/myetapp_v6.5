/**
 * 
 */
package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmPYWPopupSenaraiTanahBerkaitanData;

/**
 * 
 *
 */
public class FrmPYWPopupSenaraiTanahBerkaitanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPYWPopupSenaraiTanahBerkaitanData logic = new FrmPYWPopupSenaraiTanahBerkaitanData();

	String idNegeriUser = null;
	
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");		

		//GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
	    String actionPopup = getParam("actionPopup");
	    String submit = getParam("command");	    
	    String hitButton = getParam("hitButton");
	    
	    String idPermohonan = getParam("idPermohonan");
	    String idHakmilikAgensi = getParam("idHakmilikAgensi");
	    String idHakmilikSementara = getParam("idHakmilikSementara");
	    
	    //VECTOR
        Vector list = null;
        Vector beanMaklumatHakmilik = null;
        Vector beanMaklumatTanah = null;
	    
	    //GET DROPDOWN PARAM
        String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
		}
		String jenisHakmilik = getParam("socJenisHakmilik");
		if (jenisHakmilik == null || jenisHakmilik.trim().length() == 0){
			jenisHakmilik = "99999";
		}
		String jenisLot = getParam("socJenisLot");
		if (jenisLot == null || jenisLot.trim().length() == 0){
			jenisLot = "99999";
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
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0){
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}
		String idKategori = getParam("socKategori");
		if (idKategori == null || idKategori.trim().length() == 0){
			idKategori = "99999";
		}
		String idSubKategori = getParam("socSubKategori");
		if (idSubKategori == null || idSubKategori.trim().length() == 0){
			idSubKategori = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
		
		//FILTER BY NEGERI
		if (idNegeriUser != null && idNegeriUser.length() > 0){
			if (!"16".equals(idNegeriUser)){
				idNegeri = idNegeriUser;
			}
		}
		
		this.context.put("close_window", "");
		
		//SEND TO MODEL
		if ("doSimpanHakMilik".equals(hitButton)) {
			idHakmilikSementara = logic.saveHakmilikSementara(jenisHakmilik, getParam("txtNoHakmilik"), jenisLot, getParam("txtNoLot"),
					getParam("txtLuasBersamaan"), idNegeri, idDaerah, idMukim,
					idKategori, idSubKategori, idKementerian, idAgensi,  
					getParam("txtSyarat"), getParam("txtSekatan"),getParam("txtKegunaanTanah"), session);
			hitButton = "";
		}
		
		//HITBUTTON		
		if ("simpan".equals(hitButton)) {
			logic.simpan(idPermohonan, idHakmilikAgensi, idHakmilikSementara, session);
			this.context.put("close_window", "yes");
		}
	    
		if ("daftar".equals(actionPopup)){
	    	
	    	//GO TO DAFTAR MAKLUMAT HAK MILIK SEMENTARA
        	vm = "app/php2/frmPYWPopupDaftarHakmilik.jsp";
        	
        	this.context.put("readonlyPopup", "");
			this.context.put("inputTextClassPopup", "");
			this.context.put("disabledPopup", "");
			
			beanMaklumatHakmilik = new Vector();
			Hashtable hashHakmilik = new Hashtable();
			hashHakmilik.put("peganganHakmilik", getParam("txtPeganganHakmilik"));
			hashHakmilik.put("noHakmilik", getParam("txtNoHakmilik"));
			hashHakmilik.put("noLot", getParam("txtNoLot"));
			if ("doChangeLuas".equals(submit)){
				hashHakmilik.put("luas1", "");
				hashHakmilik.put("luas2", "");
				hashHakmilik.put("luas3", "");
				hashHakmilik.put("luasBersamaan", "");
			} else {
				hashHakmilik.put("luas1", getParam("txtLuas1"));
				hashHakmilik.put("luas2", getParam("txtLuas2"));
				hashHakmilik.put("luas3", getParam("txtLuas3"));
				hashHakmilik.put("luasBersamaan", getParam("txtLuasBersamaan"));
			}
			hashHakmilik.put("syarat", getParam("txtSyarat"));
			hashHakmilik.put("sekatan", getParam("txtSekatan"));
			hashHakmilik.put("kegunaanTanah", getParam("txtKegunaanTanah"));			
			hashHakmilik.put("tarikhBorangK", getParam("tarikhBorangK"));
			hashHakmilik.put("catatan", getParam("txtCatatan"));
			hashHakmilik.put("noPerserahan", getParam("txtNoPerserahan"));
			hashHakmilik.put("tarikhCatatan", getParam("tarikhCatatan"));
			hashHakmilik.put("tarikhTerima", getParam("tarikhTerima"));
			
			beanMaklumatHakmilik.addElement(hashHakmilik);
			this.context.put("BeanMaklumatHakmilik", beanMaklumatHakmilik);
        	
        	if ("doChangeNegeri".equals(submit)){
        		idDaerah = "99999";
        		idMukim = "99999";
        	}
        	if ("doChangeDaerah".equals(submit)){
        		idMukim = "99999";
        	}
        	
        	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(jenisHakmilik), ""));
			this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot",Long.parseLong(jenisLot), ""));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Long.parseLong(idDaerah), "", " onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Long.parseLong(idMukim), ""));
			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), ""));
			
			this.context.put("selectKategori", HTML.SelectKategoriTanah("socKategori", Long.parseLong(idKategori), "", " onChange=\"doChangeKategori();\""));
			this.context.put("selectSubKategori", HTML.SelectSubKategoriTanah(idKategori, "socSubKategori", Long.parseLong(idSubKategori), "", ""));
	    	
	    } else if ("papar".equals(actionPopup)){
	    	
	    	//GO TO DETAIL MAKLUMAT TANAH        	
        	vm = "app/php2/frmPYWPopupMaklumatTanahBerkaitan.jsp";
        	
        	beanMaklumatTanah = new Vector();
			
			logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
	    	
	    } else {
	    	
	    	//GO TO LIST TANAH        	
        	vm = "app/php2/frmPYWPopupSenaraiTanahBerkaitan.jsp";  
        	
        	if ("1".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "selected");
				this.context.put("selected2", "");
				this.context.put("idJenisTanah", idJenisTanah);
			} else if ("2".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "selected");
				this.context.put("idJenisTanah", idJenisTanah);
			} else {
				this.context.put("selected", "selected");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("idJenisTanah", "0");
			}

			if ("doChangeNegeri".equals(submit)) {
				idDaerah = "99999";
				idMukim = "99999";
			}
			if ("doChangeDaerah".equals(submit)) {
				idMukim = "99999";
			}

			this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik(
					"socJenisHakmilik", Long.parseLong(jenisHakmilik), ""));
			this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot",
					Long.parseLong(jenisLot), ""));			
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
					Long.parseLong(idNegeri), "",
					" onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(
					idNegeri, "socDaerah", Long.parseLong(idDaerah), "",
					" onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,
					"socMukim", Long.parseLong(idMukim), ""));
			this.context.put("selectKementerian", HTML.SelectKementerian(
					"socKementerian", Long.parseLong(idKementerian), "",
					" onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi", HTML.SelectAgensiByKementerian(
					"socAgensi", idKementerian, Long.parseLong(idAgensi), ""));

			this.context.put("txtPeganganHakmilik",
					getParam("txtPeganganHakmilik"));
			this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
			this.context.put("txtNoLot", getParam("txtNoLot"));
			this.context.put("txtNoWarta", getParam("txtNoWarta"));
			this.context.put("tarikhWarta", getParam("tarikhWarta"));

			list = new Vector();
			logic.carianTanah(idJenisTanah, getParam("txtPeganganHakmilik"),
					jenisHakmilik, getParam("txtNoHakmilik"), jenisLot,
					getParam("txtNoLot"), getParam("txtNoWarta"),
					getParam("tarikhWarta"), idNegeri, idDaerah, idMukim,
					idKementerian, idAgensi, idPermohonan);
			list = logic.getSenaraiTanah();
			this.context.put("SenaraiTanah", list);
        	
        	setupPage(session,action,list);
	    }
	    
		this.context.put("actionPopup", actionPopup);
		this.context.put("idLuas", idLuas);
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
				this.context.put("SenaraiTanah",paging.getPage(page));
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
