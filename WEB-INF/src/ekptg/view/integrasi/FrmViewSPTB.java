package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.integrasi.FrmModelSPTB;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
import ekptg.model.ppt.FrmPermohonanUPTData;

@SuppressWarnings({ "serial", "unused" })
public class FrmViewSPTB extends AjaxBasedModule {

	static Logger myLogger = Logger.getLogger(FrmViewSPTB.class);	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	String Carian_NoFail = "";
	String Carian_NoPermohonan = "";
	String Carian_Nohakmilik = "";
	String Carian_Nolot = "";
	String seksyen = "";
	
	String ID_PERMOHONAN = "";
	String ID_PEMOHON = "";
	String ID_FAIL = "";
	
	String ID_HAKMILIK = "";
	String ID_SEKSYEN = "";
	
	String MP_NAMAPEMOHON = "";
	String MP_TARIKHPERMOHONAN = "";
	String MP_NOKPPEMOHON = "";
	String MP_ALAMATPEMOHON = "";
	String MP_ALAMAT1PEMOHON = "";
	String MP_ALAMAT2PEMOHON = "";
	String MP_ALAMAT3PEMOHON = "";
	String MP_STATUSKEBANGKRAPAN = "";
	String MP_STATUSKEBANGKRAPAN0 = "";
	String MP_STATUSKEBANGKRAPAN1 = "";
	String MP_CATATAN = "";
	String MP_SELESAI = "";
	
	
	String MP_ID_PERMOHONAN = "";
	String MP_ID_FAIL = "";
	String MP_NO_FAIL = "";
	String MP_NO_PERMOHONAN = "";
	String MP_ID_HAKMILIK = "";
	String MP_NO_HAKMILIK = "";
	String MP_NO_LOT = "";
	String MP_KOD_JENIS_HAKMILIK = "";
	String MP_NAMA_NEGERI = "";
	String MP_NAMA_DAERAH = "";
	String MP_NAMA_MUKIM = "";
	String MP_KATEGORI_TANAH = "";
	String MP_NAMA_LUAS_ASAL = "";
	String MP_HARI = "";
	String MP_BULAN = "";
	String MP_TAHUN = "";
	String MP_JENIS_RIZAB = "";
	String MP_NO_SYIT = "";
	String MP_ID_SEKSYEN = "";
	String MP_STATUS_SPTB = "";	
	String MP_NAMA_PEMILIK = "";
		
	Boolean isSPTBUser = false, haveINTData = false;
    String userName = "",_portal_role = "", userRole = "";
    
    
    Vector listHTAid = null;
    Vector dataMaklumatTanah = null;
    
    
   // _portal_role = (String) session.getAttribute("_portal_role");
	
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
        HttpSession session = this.request.getSession();
        
        FrmModelSPTB modelSPTB = new FrmModelSPTB();
        FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
        FrmPrmhnnSek8InternalData logic = new FrmPrmhnnSek8InternalData();
        
		String vm = "";
        String action = getParam("action2");  
        
        myLogger.info("ACTION SPTB"+action);
        
        Boolean Page_Carian = false;
    	Boolean saveSPTB = false, sendSPTB = false, sendJKPTG = false, deleteSPTB = false;
                
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        isSPTBUser = isSPTBUser(userRole);
        
        ID_PERMOHONAN = getParam("ID_PERMOHONAN");
        ID_PEMOHON = getParam("ID_PEMOHON");
        
        ID_FAIL = getParam("ID_FAIL");
        ID_HAKMILIK = getParam("ID_HAKMILIK");
        ID_SEKSYEN = getParam("ID_SEKSYEN");
        
    	MP_NAMAPEMOHON = getParam("MP_NAMAPEMOHON");
    	MP_TARIKHPERMOHONAN = getParam("MP_TARIKHPERMOHONAN");
    	MP_NOKPPEMOHON = getParam("MP_NOKPPEMOHON");
    	MP_ALAMATPEMOHON = getParam("MP_ALAMATPEMOHON");        
    	MP_ALAMAT1PEMOHON = getParam("MP_ALAMAT1PEMOHON");        
    	MP_ALAMAT2PEMOHON = getParam("MP_ALAMAT2PEMOHON");        
    	MP_ALAMAT3PEMOHON = getParam("MP_ALAMAT3PEMOHON");
    	MP_STATUSKEBANGKRAPAN = getParam("MP_STATUSKEBANGKRAPAN");
    	MP_CATATAN = getParam("MP_CATATAN");
    	MP_SELESAI = getParam("MP_SELESAI");

    	Vector vData = new Vector();
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();
    	
        if ("searchSPTB".equalsIgnoreCase(action)) {
        	Page_Carian = true;
        } else if ("viewSPTB".equalsIgnoreCase(action)) {
        	if (!"".equalsIgnoreCase(ID_HAKMILIK) && !"".equalsIgnoreCase(ID_FAIL) && !"".equalsIgnoreCase(ID_SEKSYEN)) {
            	vData = modelSPTB.viewSPTBMaklumatHakmilik(ID_FAIL,ID_HAKMILIK,ID_SEKSYEN);
            
            	
            	if(ID_SEKSYEN.equals("1"))
            	{
            	dataHakmilik(ID_SEKSYEN,"disabled");
            	modelUPT.setMaklumatTanah(ID_HAKMILIK);
        		dataMaklumatTanah = modelUPT.getMaklumatTanah();
            	myLogger.info("vData:"+vData);
            	}
            	if(ID_SEKSYEN.equals("2"))
            	{            	
            	modelSPTB.setDataHTAbyIdHtaam(ID_HAKMILIK);
				listHTAid = modelSPTB.getDataHTAbyIdHtaam();
            	}
            	
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePageMP(h);
    			}
    		} else {
    			Page_Carian = true;
    		}
        } else if ("saveSPTB".equalsIgnoreCase(action)) {
        	if (!"".equalsIgnoreCase(ID_HAKMILIK) && !"".equalsIgnoreCase(ID_FAIL) && !"".equalsIgnoreCase(ID_SEKSYEN)) {
                h = new Hashtable();
				h.put("MP_STATUSKEBANGKRAPAN", MP_STATUSKEBANGKRAPAN);
				h.put("MP_CATATAN", MP_CATATAN);
				h.put("MP_SELESAI", MP_SELESAI);
				h.put("ID_HAKMILIK", ID_HAKMILIK);
				h.put("ID_FAIL", ID_FAIL);
				h.put("ID_SEKSYEN", ID_SEKSYEN);
				saveSPTB = modelSPTB.saveSPTB(ID_HAKMILIK,ID_FAIL,ID_SEKSYEN,isSPTBUser, h);
				if(ID_SEKSYEN.equals("1"))
            	{
            	dataHakmilik(ID_SEKSYEN,"disabled");
            	modelUPT.setMaklumatTanah(ID_HAKMILIK);
        		dataMaklumatTanah = modelUPT.getMaklumatTanah();
            	myLogger.info("vData:"+vData);
            	}
				if(ID_SEKSYEN.equals("2"))
            	{            	
				modelSPTB.setDataHTAbyIdHtaam(ID_HAKMILIK);
				listHTAid = modelSPTB.getDataHTAbyIdHtaam();
            	}
				vData = modelSPTB.viewSPTBMaklumatHakmilik(ID_FAIL,ID_HAKMILIK,ID_SEKSYEN);
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePageMP(h);
    				
    				myLogger.info("MP_SELESAI:::::::"+getParam("MP_SELESAI"));
    				
    				if(getParam("MP_SELESAI").equals("1"))
    				{
    				sendJKPTG = true;
    				saveSPTB = false;
    				}
    				
    			}
    		} else {
    			Page_Carian = true;
    		}
        } else if ("sendSPTB".equalsIgnoreCase(action)) {
        	if (!"".equalsIgnoreCase(ID_HAKMILIK) && !"".equalsIgnoreCase(ID_FAIL) && !"".equalsIgnoreCase(ID_SEKSYEN)) {
        		
        		h = new Hashtable();
 				h.put("MP_STATUSKEBANGKRAPAN", MP_STATUSKEBANGKRAPAN);
 				h.put("MP_CATATAN", MP_CATATAN);
 				h.put("MP_SELESAI", MP_SELESAI);
 				h.put("ID_HAKMILIK", ID_HAKMILIK);
 				h.put("ID_FAIL", ID_FAIL);
 				h.put("ID_SEKSYEN", ID_SEKSYEN);
 				saveSPTB = modelSPTB.saveSPTB(ID_HAKMILIK,ID_FAIL,ID_SEKSYEN,isSPTBUser, h);
        		
        		
        		
        		sendSPTB = modelSPTB.sendSPTB(ID_FAIL,ID_HAKMILIK,ID_SEKSYEN);
        	
        		vData = modelSPTB.viewSPTBMaklumatHakmilik(ID_FAIL,ID_HAKMILIK,ID_SEKSYEN);
        		if(ID_SEKSYEN.equals("1"))
            	{
            	dataHakmilik(ID_SEKSYEN,"disabled");
            	modelUPT.setMaklumatTanah(ID_HAKMILIK);
        		dataMaklumatTanah = modelUPT.getMaklumatTanah();
            	myLogger.info("vData:"+vData);
            	}
        		if(ID_SEKSYEN.equals("2"))
            	{            	
        		modelSPTB.setDataHTAbyIdHtaam(ID_HAKMILIK);
				listHTAid = modelSPTB.getDataHTAbyIdHtaam();
            	}
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePageMP(h);
    			}
    		} else {
    			Page_Carian = true;
    		}
        } 
        
      
        else if ("deleteSPTB".equalsIgnoreCase(action)) {
        	Page_Carian = true;

        	if (!"".equalsIgnoreCase(ID_HAKMILIK) && !"".equalsIgnoreCase(ID_FAIL) && !"".equalsIgnoreCase(ID_SEKSYEN)) {
            	
        		deleteSPTB = modelSPTB.deleteSPTB(ID_HAKMILIK,ID_FAIL,ID_SEKSYEN);
        		if (!deleteSPTB) {
    				Page_Carian = false;
        		}
        	}
        } else {
    		Page_Carian = true;
        }
        
        if (Page_Carian) {
        	vm = "app/integrasi/frmSPTBCarian.jsp";

        	Carian_NoFail = getParam("Carian_NoFail");
            Carian_NoPermohonan = getParam("Carian_NoPermohonan");
            Carian_Nohakmilik = getParam("Carian_Nohakmilik");
            Carian_Nolot = getParam("Carian_Nolot");
            seksyen = getParam("seksyen");

            context.put("seksyen", seksyen);
            context.put("Carian_NoFail", Carian_NoFail);
        	context.put("Carian_NoPermohonan", Carian_NoPermohonan);
        	context.put("Carian_Nohakmilik", Carian_Nohakmilik);
        	context.put("Carian_Nolot", Carian_Nolot);
        	
        	context.put("deleteSPTB", deleteSPTB);
        	context.remove("ListPermohonan");
        	
        	if ("searchSPTB".equalsIgnoreCase(action)) {
        		//vList = modelSPTB.searchSPTB(Carian_NoFail, Carian_NoPermohonan, Carian_NamaPemohon, Carian_NoKPPemohon);
        		vList = modelSPTB.searchSPTBHAKMILIK(Carian_NoFail, Carian_NoPermohonan, Carian_Nohakmilik, Carian_Nolot,seksyen,(String) session.getAttribute("_ekptg_user_id"));
            	
        		myLogger.info("vList:"+vList);
        		
        		
        		setupPage(session, action, vList);
        	}
        } else {
        	vm = "app/integrasi/frmSPTB.jsp";
        	
        	
        	context.put("MP_ID_PERMOHONAN", MP_ID_PERMOHONAN);
        	context.put("MP_ID_FAIL", MP_ID_FAIL);
        	context.put("MP_NO_FAIL", MP_NO_FAIL);
        	context.put("MP_NO_PERMOHONAN", MP_NO_PERMOHONAN);
        	context.put("MP_ID_HAKMILIK", MP_ID_HAKMILIK);
        	context.put("MP_NO_HAKMILIK", MP_NO_HAKMILIK);
        	context.put("MP_NO_LOT", MP_NO_LOT);
        	context.put("MP_KOD_JENIS_HAKMILIK", MP_KOD_JENIS_HAKMILIK);
        	context.put("MP_NAMA_NEGERI", MP_NAMA_NEGERI);
        	context.put("MP_NAMA_DAERAH", MP_NAMA_DAERAH);
        	context.put("MP_NAMA_MUKIM", MP_NAMA_MUKIM);
        	context.put("MP_KATEGORI_TANAH", MP_KATEGORI_TANAH);
        	context.put("MP_NAMA_LUAS_ASAL", MP_NAMA_LUAS_ASAL);
        	context.put("MP_HARI", MP_HARI);
        	context.put("MP_BULAN", MP_BULAN);
        	context.put("MP_TAHUN", MP_TAHUN);
        	context.put("MP_JENIS_RIZAB", MP_JENIS_RIZAB);
        	context.put("MP_NO_SYIT", MP_NO_SYIT);
        	context.put("MP_ID_SEKSYEN", MP_ID_SEKSYEN);
        	context.put("MP_STATUS_SPTB", MP_STATUS_SPTB);
        	context.put("MP_NAMA_PEMILIK", MP_NAMA_PEMILIK);
        	
	    	context.put("MP_NAMAPEMOHON", MP_NAMAPEMOHON);
	        context.put("MP_TARIKHPERMOHONAN", MP_TARIKHPERMOHONAN);
	    	context.put("MP_NOKPPEMOHON", MP_NOKPPEMOHON);
	    	context.put("MP_ALAMATPEMOHON", MP_ALAMATPEMOHON);
	    	context.put("MP_ALAMAT1PEMOHON", MP_ALAMAT1PEMOHON);
	    	context.put("MP_ALAMAT2PEMOHON", MP_ALAMAT2PEMOHON);
	    	context.put("MP_ALAMAT3PEMOHON", MP_ALAMAT3PEMOHON);
	    	context.put("MP_ALAMATPEMOHON", MP_ALAMATPEMOHON);
	    	context.put("MP_STATUSKEBANGKRAPAN", MP_STATUSKEBANGKRAPAN);
	    	context.put("MP_STATUSKEBANGKRAPAN0", MP_STATUSKEBANGKRAPAN0);
	    	context.put("MP_STATUSKEBANGKRAPAN1", MP_STATUSKEBANGKRAPAN1);
	    	context.put("MP_CATATAN", MP_CATATAN);
	    	context.put("MP_SELESAI", MP_SELESAI);
	        context.put("ID_PERMOHONAN", ID_PERMOHONAN);
	        context.put("ID_PEMOHON", ID_PEMOHON);
	        context.put("ID_FAIL", ID_FAIL);
	        context.put("ID_HAKMILIK", ID_HAKMILIK);
	        context.put("ID_SEKSYEN", ID_SEKSYEN);
	        context.put("haveINTData", haveINTData);
	        context.put("saveSPTB", saveSPTB);
	        context.put("sendSPTB", sendSPTB);
	        context.put("sendJKPTG", sendJKPTG);
	        
	        context.put("dataMaklumatTanah", dataMaklumatTanah);
	        context.put("listHTAid", listHTAid);
	        
	        
	        
        }
        if (isSPTBUser) {
        	context.put("READONLY_SPTB", "");	
        	context.put("DISABLE_SPTB", "");
        	context.put("READONLY_JKPTG", " readonly=\"readonly\" ");	
        	context.put("DISABLE_JKPTG", " readonly=\"readonly\" ");
        } else {
        	context.put("READONLY_SPTB", " readonly=\"readonly\" ");
        	context.put("DISABLE_SPTB", " disabled=\"disabled\" ");
        	context.put("READONLY_JKPTG", "");
        	context.put("DISABLE_JKPTG", "");
        }
        context.put("isSPTBUser", isSPTBUser);
        _portal_role = (String) session.getAttribute("_portal_role");
        context.put("_portal_role", _portal_role);
        context.put("action2", action);
		return vm;
	}
	
	/*
	@SuppressWarnings("unchecked")
	private void populatePageMP(Hashtable h) throws Exception {
		if (!h.isEmpty()) {
			ID_PERMOHONAN = (String) h.get("MP_IDPERMOHONAN");
			MP_NAMAPEMOHON = (String) h.get("MP_NAMAPEMOHON");
			MP_TARIKHPERMOHONAN = (String) h.get("MP_TARIKHPERMOHONAN");
			MP_NOKPPEMOHON = (String) h.get("MP_NOKPPEMOHON");
			MP_ALAMATPEMOHON = (String) h.get("MP_ALAMATPEMOHON");
			MP_ALAMAT1PEMOHON = (String) h.get("MP_ALAMAT1PEMOHON");
			MP_ALAMAT2PEMOHON = (String) h.get("MP_ALAMAT2PEMOHON");
			MP_ALAMAT3PEMOHON = (String) h.get("MP_ALAMAT3PEMOHON");
			MP_STATUSKEBANGKRAPAN = (String) h.get("MP_STATUSKEBANGKRAPAN");
			MP_CATATAN = (String) h.get("MP_CATATAN");
			MP_SELESAI = (String) h.get("MP_SELESAI");
			haveINTData = (Boolean) h.get("haveINTData");
			if ("1".equalsIgnoreCase(MP_STATUSKEBANGKRAPAN)) {
				MP_STATUSKEBANGKRAPAN0 = "";
				MP_STATUSKEBANGKRAPAN1 = " checked=\"checked\" ";
			} else {
				MP_STATUSKEBANGKRAPAN0 = " checked=\"checked\" ";
				MP_STATUSKEBANGKRAPAN1 = "";
			}
			if ("1".equalsIgnoreCase(MP_SELESAI)) {
				MP_SELESAI = " checked=\"checked\" ";
			} else {
				MP_SELESAI = "";
			}
		}
	}
	*/
	
	
	@SuppressWarnings("unchecked")
	private void populatePageMP(Hashtable h) throws Exception {
		
		if (!h.isEmpty()) {
			MP_ID_PERMOHONAN = (String) h.get("MP_IDPERMOHONAN");
			MP_NO_FAIL = (String) h.get("MP_NO_FAIL");
			MP_NO_PERMOHONAN = (String) h.get("MP_NO_PERMOHONAN");
			MP_ID_HAKMILIK = (String) h.get("MP_ID_HAKMILIK");
			MP_NO_HAKMILIK = (String) h.get("MP_NO_HAKMILIK");
			MP_NO_LOT = (String) h.get("MP_NO_LOT");
			MP_KOD_JENIS_HAKMILIK = (String) h.get("MP_KOD_JENIS_HAKMILIK");
			MP_NAMA_NEGERI = (String) h.get("MP_NAMA_NEGERI");
			MP_NAMA_DAERAH = (String) h.get("MP_NAMA_DAERAH");
			MP_ID_FAIL = (String) h.get("MP_ID_FAIL");
			MP_NAMA_MUKIM = (String) h.get("MP_NAMA_MUKIM");
			MP_KATEGORI_TANAH = (String) h.get("MP_KATEGORI_TANAH");
			MP_NAMA_LUAS_ASAL = (String) h.get("MP_NAMA_LUAS_ASAL");
			MP_HARI = (String) h.get("MP_HARI");
			MP_BULAN = (String) h.get("MP_BULAN");
			MP_TAHUN = (String) h.get("MP_TAHUN");
			MP_JENIS_RIZAB = (String) h.get("MP_JENIS_RIZAB");
			MP_NO_SYIT = (String) h.get("MP_NO_SYIT");
			MP_ID_SEKSYEN = (String) h.get("MP_ID_SEKSYEN");
			MP_STATUS_SPTB = (String) h.get("MP_STATUS_SPTB");
			MP_CATATAN = (String) h.get("MP_CATATAN");
			MP_SELESAI = (String) h.get("MP_SELESAI");
			MP_NAMA_PEMILIK = (String) h.get("MP_NAMA_PEMILIK");
			
			
			
			haveINTData = (Boolean) h.get("haveINTData");
			if ("1".equalsIgnoreCase(MP_STATUS_SPTB)) {
				MP_STATUSKEBANGKRAPAN0 = "";
				MP_STATUSKEBANGKRAPAN1 = " checked=\"checked\" ";
			} else {
				MP_STATUSKEBANGKRAPAN0 = " checked=\"checked\" ";
				MP_STATUSKEBANGKRAPAN1 = "";
			}
			if ("1".equalsIgnoreCase(MP_SELESAI)) {
				
				MP_SELESAI = " checked=\"checked\" ";
			} else {
				MP_SELESAI = "";
			}
		}
	}
	
	private Boolean isSPTBUser(String userRole) throws Exception {
		Boolean returnValue = false;
		returnValue = "sptb".equalsIgnoreCase(userRole);
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public void setupPage(HttpSession session, String action, Vector list) {
		try {
			this.context.put("totalRecords",list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10 : getParamAsInteger("itemsPerPage");
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
			this.context.put("ListPermohonan",paging.getPage(page));
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
	
	@SuppressWarnings("unchecked")
	private void dataHakmilik(String idHakmilik,String disability) throws Exception{
	    
		Vector dataMaklumatTanah = new Vector();
		dataMaklumatTanah.clear();
		FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
		//data hakmilik
		modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);
		
		String id_negeriprojek = "";
		String id_daerah = "";
		String id_mukim = "";
		String id_jenishakmilik = "";
		String id_luaslot = "";
		String id_lot = "";
		String id_kategoritanah = "";
		String id_daerahpenggawa = "";
		String id_unitluasambil = "";
		if(dataMaklumatTanah.size()!=0){
			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
			id_mukim = h.get("id_mukim").toString();
			id_jenishakmilik = h.get("id_jenishakmilik").toString();
			id_luaslot = h.get("id_luasLot").toString();
			id_lot = h.get("id_lot").toString();
			id_kategoritanah = h.get("id_kategoritanah").toString();
			id_daerah = h.get("id_daerah").toString();
			id_negeriprojek = h.get("id_negeri").toString();
			id_daerahpenggawa = h.get("id_daerahpenggawa").toString();
			id_unitluasambil = h.get("id_unitluasambil").toString();			
		}
		
		
		//validation jajahan
		if(id_negeriprojek.equals("3")){
			context.put("showJajahan","yes");
		}else{
			context.put("showJajahan","no");
		}
		
		String mode = "";
		if(disability.equals("enabled")){
			mode = "";
		}else{
			mode = "disabled class=disabled";
		}
		
		//untuk kelantan shj
		context.put("SelectDaerahPenggawa",HTML.SelectDaerahPenggawa("socDaerahPenggawa",Utils.parseLong(id_daerahpenggawa),null," "+mode+" style=width:274px"));
		
		//dropdown
		if(id_negeriprojek.equals("10")){
			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"id=socJenisHakmilik "+mode+" style=width:auto onchange=doOnchangeUpdate()"));   	
		}else{
			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"id=socJenisHakmilik "+mode+" style=width:auto onchange=doOnchangeUpdate()"));   	
		}
		
		context.put("selectKategoriTanah",HTML.SelectKategoriTanah("socKategoriTanah",Utils.parseLong(id_kategoritanah),"id=socKategoriTanah "+mode+" style=width:auto",null));
		context.put("selectKodLot", HTML.SelectUnitPT("socKodLot",Utils.parseLong(id_lot),"style=width:auto "+mode+" "));
		
		//dropdown unit luas
		context.put("selectUnitLuasLot",HTML.SelectLuas("socUnitLuasLot",Utils.parseLong(id_luaslot),"style=width:250px "+mode+" id=socUnitLuasLot onchange=onchangeUnitLuasAsalUpdate()"));
		context.put("selectUnitLuasAmbil",HTML.SelectLuas("socUnitLuasAmbil",Utils.parseLong(id_unitluasambil),"style=width:250px "+mode+" id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbilUpdate()"));
		
		//dropdown by
		if(id_daerah!=""){
			context.put("selectMukim",HTML.SelectMukimNoKodByDaerah(id_daerah,"socMukim",Utils.parseLong(id_mukim),"style=width:auto "+mode+""));
		}else{
			context.put("selectMukim",HTML.SelectMukimNoKod("socMukim",Utils.parseLong(id_mukim),"style=width:auto "+mode+""));
		}
		
	}//close dataHakmilik
	
}