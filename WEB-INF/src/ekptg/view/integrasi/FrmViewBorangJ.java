package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.integrasi.FrmModelBorangJ;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8KptsanBicaraData;

@SuppressWarnings({ "serial", "unused" })
public class FrmViewBorangJ extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmModelBorangJ.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	FrmPrmhnnSek8KptsanBicaraData logic4 = new FrmPrmhnnSek8KptsanBicaraData();
	
	String Carian_NoFail = "";
	String Carian_NoPermohonan = "";
	String Carian_NamaSiMati = "";
	String Carian_NoKPSiMati = "";
	
	String ID_PERMOHONAN = "";
	
	String MP_NOFAIL = "";
	String MP_NOPERMOHONAN = "";
	String MP_NEGERI = "";
	String MP_DAERAH = "";
	String MP_UNIT = "";
	String MP_TARIKHMOHON = "";
	String MP_NAMAPEMOHON = "";
	String MP_NOKPPEMOHON = "";
	String MP_ALAMATPEMOHON = "";
	String MP_NOTELPEMOHON = "";
	String MP_NAMASIMATI = "";
	String MP_NOKPSIMATI = "";
	String MP_TARIKHMATI = "";
	String MP_FAKTAGUAMAN = "";
	String MP_PENDAPAT = "";
	
	String PERMOHONAN_TARIKHRUJUKAN = "";
	String PERMOHONAN_NEGERIMAHKAMAH = "";
	String PERMOHONAN_NAMAMAHKAMAH = "";
	String PERMOHONAN_ALAMATMAHKAMAH1 = "";
	String PERMOHONAN_ALAMATMAHKAMAH2 = "";
	String PERMOHONAN_ALAMATMAHKAMAH3 = "";
	String PERMOHONAN_POSKODMAHKAMAH = "";
	String PERMOHONAN_NOTELEFONMAHKAMAH = "";
	String PERMOHONAN_NOFAKSMAHKAMAH = "";
	String PERMOHONAN_PEGAWAIPENGENDALI = "";
	
	String KEPUTUSAN_TARIKHTERIMABORANGJ = "";
	String KEPUTUSAN_TARIKHHANTARKEPUTUSAN = "";
	String KEPUTUSAN_KEPUTUSAN = "";
	String KEPUTUSAN_CATATAN = "";
	
	String SIJILFARAID_NAMAMAHKAMAH = "";
	String SIJILFARAID_DAERAHMAHKAMAH = "";
	String SIJILFARAID_NEGERIMAHKAMAH = "";
	String SIJILFARAID_NOKES = "";
	String SIJILFARAID_NAMAHAKIM = "";
	String SIJILFARAID_HARTAPUSAKASIMATI = "";
	String SIJILFARAID_TARIKHMATI = "";
	String SIJILFARAID_NAMASEKSYEN = "";
	String SIJILFARAID_NAMAORDINAN = "";
	String SIJILFARAID_KEPUTUSANFARAID = "";
	String SIJILFARAID_TARIKHSIJILFARAID = "";
	
	String SEBAB_TANGGUH = "";
	String KEPUTUSAN_MAHKAMAH = "";
	String ID_PERMOHONANSIMATI = "";
	String ID_PERBICARAAN = "";
	String ID_PERINTAH = "";
	String ID_BORANGJ = "";
	
	
	
	
	
	Vector ListWaris = new Vector();
	
	Boolean haveINTData = false, deleteBorangJ = false;
    
    String USER_NAME = "", USER_ID = "";
	
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		String vm = "";
		ListWaris.clear();
        HttpSession session = this.request.getSession();
        
        FrmModelBorangJ modelBorangJ = new FrmModelBorangJ();
        
        // action
        String action2 = getParam("action2");
        myLogger.info("action2 : "+action2);
        
        // mode
        String mode = getParam("mode");

        // command: used by changeSOC
        String command = getParam("command");
        
        // selectedTab
        String selectedTab = getParam("selectedTab");
        if ("".equalsIgnoreCase(selectedTab)) {
        	selectedTab = "0";
        }
        if (!"0".equalsIgnoreCase(selectedTab) && !"1".equalsIgnoreCase(selectedTab) && !"2".equalsIgnoreCase(selectedTab)) {
        	selectedTab = "0";
        }
        
        Boolean sendJKSM = false;
        
        USER_NAME = (String) session.getAttribute("_portal_username");
        USER_ID = (String) session.getAttribute("_ekptg_user_id");
        
        Boolean Page_Carian = false;
        
        ID_PERMOHONAN = getParam("ID_PERMOHONAN");
        
        Carian_NoFail = getParam("Carian_NoFail");
        Carian_NoPermohonan = getParam("Carian_NoPermohonan");
        Carian_NamaSiMati = getParam("Carian_NamaSiMati");
        Carian_NoKPSiMati = getParam("Carian_NoKPSiMati");
        context.put("Carian_NoFail", Carian_NoFail);
        context.put("Carian_NoPermohonan", Carian_NoPermohonan);
        context.put("Carian_NamaSiMati", Carian_NamaSiMati);
        context.put("Carian_NoKPSiMati", Carian_NoKPSiMati);
        this.context.put("ListWaris","");
        
        
    	Vector vData = new Vector();
    	//Vector vList = new Vector();
    	List vList = null;
    	Hashtable h = new Hashtable();
    	
        if ("searchBorangJ".equalsIgnoreCase(action2)) {
        	vm = "app/integrasi/frmBorangJCarian.jsp";
        	context.remove("deleteBorangJ");
        	context.remove("ListPermohonan");
        	vList = modelBorangJ.searchBorangJ(Carian_NoFail, Carian_NoPermohonan, Carian_NamaSiMati, Carian_NoKPSiMati);
        	context.put("ListPermohonan", vList);
        	setupPage(session, action2, vList);
    		context.put("pagingTitle", "title");
    		Page_Carian = true;
        } else if ("viewBorangJ".equalsIgnoreCase(action2)) {
    		// initial VM value
        	headerppk_baru(session, ID_PERMOHONAN, "Y", "", "T");
        	vm = "app/integrasi/frmBorangJCarian.jsp";
    		
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			vData = modelBorangJ.viewBorangJ(ID_PERMOHONAN, USER_NAME);
    			if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmBorangJ.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    			
    			
    			//* GET MAKLUMAT WARIS
    			logic4.setMaklumatWaris(ID_BORANGJ,ID_PERBICARAAN,ID_PERMOHONANSIMATI);
    			ListWaris = logic4.getMaklumatWaris();
    			if ( ListWaris.size() != 0 ){
    				this.context.put("ListWaris",ListWaris);
    			}else {
    				this.context.put("ListWaris","");
    			}
    			
    			vData = modelBorangJ.getMaklumatMahkamah(ID_PERMOHONAN);
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				PERMOHONAN_ALAMATMAHKAMAH1 = (String) h.get("PERMOHONAN_ALAMATMAHKAMAH1");
    				PERMOHONAN_ALAMATMAHKAMAH2 = (String) h.get("PERMOHONAN_ALAMATMAHKAMAH2");
    				PERMOHONAN_ALAMATMAHKAMAH3 = (String) h.get("PERMOHONAN_ALAMATMAHKAMAH3");
    				PERMOHONAN_POSKODMAHKAMAH = (String) h.get("PERMOHONAN_POSKODMAHKAMAH");
    				PERMOHONAN_NOTELEFONMAHKAMAH = (String) h.get("PERMOHONAN_NOTELEFONMAHKAMAH");
    				PERMOHONAN_NOFAKSMAHKAMAH = (String) h.get("PERMOHONAN_NOFAKSMAHKAMAH");
    			}
    		}
        } else if ("sendBorangJ".equalsIgnoreCase(action2)) {
        	vm = "app/integrasi/frmBorangJCarian.jsp";
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				vm = "app/integrasi/frmBorangJ.jsp";

    			h = new Hashtable();
				h.put("ID_PERMOHONAN", ID_PERMOHONAN);
				sendJKSM = modelBorangJ.saveBorangJ(ID_PERMOHONAN, (String)session.getAttribute("_ekptg_user_id"), h);
    			vData = modelBorangJ.viewBorangJ(ID_PERMOHONAN, (String)session.getAttribute("_ekptg_user_id"));
    			if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmBorangJ.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    		}
        } else if ("deleteBorangJ".equalsIgnoreCase(action2)) {
        	vm = "app/integrasi/frmBorangJCarian.jsp";

        	if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
        		deleteBorangJ = modelBorangJ.deleteBorangJ(ID_PERMOHONAN);
        		if (!deleteBorangJ) {
    				vm = "app/integrasi/frmBorangJ.jsp";
        		}
        	}
        } else {
        	vm = "app/integrasi/frmBorangJCarian.jsp";
        	//vList = modelBorangJ.searchBorangJ("", "", "");
        	context.remove("deleteBorangJ");
        	context.remove("ListPermohonan");
        	if(!Carian_NoFail.equals("") || !Carian_NoPermohonan.equals("") || !Carian_NamaSiMati.equals("") || !Carian_NoKPSiMati.equals(""))
        	vList = modelBorangJ.searchBorangJ(Carian_NoFail, Carian_NoPermohonan, Carian_NamaSiMati, Carian_NoKPSiMati);
        	context.put("ListPermohonan", vList);
    		setupPage(session, action2, vList);
    		context.put("pagingTitle", "title");
    		Page_Carian = true;
        }
        
        if (Page_Carian) {
        	context.put("ListPermohonan", vList);
        } else {
        	context.put("selectedTab", selectedTab);
        	context.put("sendJKSM", sendJKSM);
        	
	        context.put("MP_NOFAIL", MP_NOFAIL);
	        context.put("MP_NOPERMOHONAN", MP_NOPERMOHONAN);
	    	context.put("MP_NEGERI", MP_NEGERI);
	    	context.put("MP_DAERAH", MP_DAERAH);
	    	context.put("MP_UNIT", MP_UNIT);
	    	context.put("MP_TARIKHMOHON", MP_TARIKHMOHON);
	    	context.put("MP_NAMAPEMOHON", MP_NAMAPEMOHON);
	    	context.put("MP_NOKPPEMOHON", MP_NOKPPEMOHON);
	    	context.put("MP_ALAMATPEMOHON", MP_ALAMATPEMOHON);
	    	context.put("MP_NOTELPEMOHON", MP_NOTELPEMOHON);
	    	context.put("MP_NAMASIMATI", MP_NAMASIMATI);
	    	context.put("MP_TARIKHMATI", MP_TARIKHMATI);
	    	context.put("MP_NOKPSIMATI", MP_NOKPSIMATI);
	    	context.put("MP_FAKTAGUAMAN", MP_FAKTAGUAMAN);
	    	context.put("MP_PENDAPAT", MP_PENDAPAT);

	    	context.put("PERMOHONAN_TARIKHRUJUKAN", PERMOHONAN_TARIKHRUJUKAN);
	    	context.put("PERMOHONAN_NEGERIMAHKAMAH", PERMOHONAN_NEGERIMAHKAMAH);
	    	context.put("PERMOHONAN_NAMAMAHKAMAH", PERMOHONAN_NAMAMAHKAMAH);
	    	context.put("PERMOHONAN_ALAMATMAHKAMAH1", PERMOHONAN_ALAMATMAHKAMAH1);
	    	context.put("PERMOHONAN_ALAMATMAHKAMAH2", PERMOHONAN_ALAMATMAHKAMAH2);
	    	context.put("PERMOHONAN_ALAMATMAHKAMAH3", PERMOHONAN_ALAMATMAHKAMAH3);
	    	context.put("PERMOHONAN_POSKODMAHKAMAH", PERMOHONAN_POSKODMAHKAMAH);
	    	context.put("PERMOHONAN_NOTELEFONMAHKAMAH", PERMOHONAN_NOTELEFONMAHKAMAH);
	    	context.put("PERMOHONAN_NOFAKSMAHKAMAH", PERMOHONAN_NOFAKSMAHKAMAH);
	    	context.put("PERMOHONAN_PEGAWAIPENGENDALI", PERMOHONAN_PEGAWAIPENGENDALI);
	    	
	    	context.put("KEPUTUSAN_TARIKHTERIMABORANGJ", KEPUTUSAN_TARIKHTERIMABORANGJ);
	    	context.put("KEPUTUSAN_TARIKHHANTARKEPUTUSAN", KEPUTUSAN_TARIKHHANTARKEPUTUSAN);
	    	context.put("KEPUTUSAN_KEPUTUSAN", KEPUTUSAN_KEPUTUSAN);
	    	context.put("KEPUTUSAN_CATATAN", KEPUTUSAN_CATATAN);
	    	
	    	context.put("SIJILFARAID_NAMAMAHKAMAH", SIJILFARAID_NAMAMAHKAMAH);
	    	context.put("SIJILFARAID_DAERAHMAHKAMAH", SIJILFARAID_DAERAHMAHKAMAH);
	    	context.put("SIJILFARAID_NEGERIMAHKAMAH", SIJILFARAID_NEGERIMAHKAMAH);
	    	context.put("SIJILFARAID_NOKES", SIJILFARAID_NOKES);
	    	context.put("SIJILFARAID_NAMAHAKIM", SIJILFARAID_NAMAHAKIM);
	    	context.put("SIJILFARAID_HARTAPUSAKASIMATI", SIJILFARAID_HARTAPUSAKASIMATI);
	    	context.put("SIJILFARAID_TARIKHMATI", SIJILFARAID_TARIKHMATI);
	    	context.put("SIJILFARAID_NAMASEKSYEN", SIJILFARAID_NAMASEKSYEN);
	    	context.put("SIJILFARAID_NAMAORDINAN", SIJILFARAID_NAMAORDINAN);
	    	context.put("SIJILFARAID_KEPUTUSANFARAID", SIJILFARAID_KEPUTUSANFARAID);
	    	context.put("SIJILFARAID_TARIKHSIJILFARAID", SIJILFARAID_TARIKHSIJILFARAID);
	    	
	    	context.put("SEBAB_TANGGUH", SEBAB_TANGGUH);
	    	context.put("KEPUTUSAN_MAHKAMAH", KEPUTUSAN_MAHKAMAH);
	    	
	    	context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
	    	context.put("ID_PERBICARAAN", ID_PERBICARAAN);
	    	context.put("ID_PERINTAH", ID_PERINTAH);
	    	context.put("ID_BORANGJ", ID_BORANGJ);
	    	
	    	

	    	context.put("haveINTData", haveINTData);
        }
        context.put("deleteBorangJ", deleteBorangJ);
        context.put("ID_PERMOHONAN", ID_PERMOHONAN);
        context.put("mode", mode);
        context.put("action2", action2);
		return vm;
	}
	
	@SuppressWarnings("unchecked")
	private void populatePage(Hashtable h) throws Exception {
		if (!h.isEmpty()) {
			FrmModelBorangJ modelBorang = new FrmModelBorangJ();
			MP_NOFAIL = (String) h.get("MP_NOFAIL");
			MP_NOPERMOHONAN = (String) h.get("MP_NOPERMOHONAN");
			MP_NEGERI = (String) h.get("MP_NEGERI");
			MP_DAERAH = (String) h.get("MP_DAERAH");
			//MP_UNIT = (String) h.get("MP_UNIT");
			MP_UNIT = modelBorang.getUnitUser(USER_ID);
			MP_TARIKHMOHON = (String) h.get("MP_TARIKHMOHON");
			MP_NAMAPEMOHON = (String) h.get("MP_NAMAPEMOHON");
			MP_NOKPPEMOHON = (String) h.get("MP_NOKPPEMOHON");
			MP_ALAMATPEMOHON = (String) h.get("MP_ALAMATPEMOHON");
			MP_NOTELPEMOHON = (String) h.get("MP_NOTELPEMOHON");
			MP_NAMASIMATI = (String) h.get("MP_NAMASIMATI");
			MP_TARIKHMATI = (String) h.get("MP_TARIKHMATI");
			MP_NOKPSIMATI = (String) h.get("MP_NOKPSIMATI");
			MP_FAKTAGUAMAN = (String) h.get("MP_FAKTAGUAMAN");
			MP_PENDAPAT = (String) h.get("MP_PENDAPAT");
			
			PERMOHONAN_TARIKHRUJUKAN = (String) h.get("PERMOHONAN_TARIKHRUJUKAN");
			PERMOHONAN_NEGERIMAHKAMAH = (String) h.get("PERMOHONAN_NEGERIMAHKAMAH");
			PERMOHONAN_NAMAMAHKAMAH = (String) h.get("PERMOHONAN_NAMAMAHKAMAH");
			PERMOHONAN_ALAMATMAHKAMAH1 = (String) h.get("PERMOHONAN_ALAMATMAHKAMAH1");
			PERMOHONAN_ALAMATMAHKAMAH2 = (String) h.get("PERMOHONAN_ALAMATMAHKAMAH2");
			PERMOHONAN_ALAMATMAHKAMAH3 = (String) h.get("PERMOHONAN_ALAMATMAHKAMAH3");
			PERMOHONAN_POSKODMAHKAMAH = (String) h.get("PERMOHONAN_POSKODMAHKAMAH");
			PERMOHONAN_NOTELEFONMAHKAMAH = (String) h.get("PERMOHONAN_NOTELEFONMAHKAMAH");
			PERMOHONAN_NOFAKSMAHKAMAH = (String) h.get("PERMOHONAN_NOFAKSMAHKAMAH");
			PERMOHONAN_PEGAWAIPENGENDALI = (String) h.get("PERMOHONAN_PEGAWAIPENGENDALI");
			
			KEPUTUSAN_TARIKHTERIMABORANGJ = (String) h.get("KEPUTUSAN_TARIKHTERIMABORANGJ");
			KEPUTUSAN_TARIKHHANTARKEPUTUSAN = (String) h.get("KEPUTUSAN_TARIKHHANTARKEPUTUSAN");
			KEPUTUSAN_KEPUTUSAN = (String) h.get("KEPUTUSAN_KEPUTUSAN");
			KEPUTUSAN_CATATAN = (String) h.get("KEPUTUSAN_CATATAN");
			
			SIJILFARAID_NAMAMAHKAMAH = (String) h.get("SIJILFARAID_NAMAMAHKAMAH");
			SIJILFARAID_DAERAHMAHKAMAH = (String) h.get("SIJILFARAID_DAERAHMAHKAMAH");
			SIJILFARAID_NEGERIMAHKAMAH = (String) h.get("SIJILFARAID_NEGERIMAHKAMAH");
			SIJILFARAID_NOKES = (String) h.get("SIJILFARAID_NOKES");
			SIJILFARAID_NAMAHAKIM = (String) h.get("SIJILFARAID_NAMAHAKIM");
			SIJILFARAID_HARTAPUSAKASIMATI = (String) h.get("SIJILFARAID_HARTAPUSAKASIMATI");
			SIJILFARAID_TARIKHMATI = (String) h.get("SIJILFARAID_TARIKHMATI");
			SIJILFARAID_NAMASEKSYEN = (String) h.get("SIJILFARAID_NAMASEKSYEN");
			SIJILFARAID_NAMAORDINAN = (String) h.get("SIJILFARAID_NAMAORDINAN");
			SIJILFARAID_KEPUTUSANFARAID = (String) h.get("SIJILFARAID_KEPUTUSANFARAID");
			SIJILFARAID_TARIKHSIJILFARAID = (String) h.get("SIJILFARAID_TARIKHSIJILFARAID");
			
			SEBAB_TANGGUH = (String) h.get("SEBAB_TANGGUH");
			
			
			KEPUTUSAN_MAHKAMAH = (String) h.get("KEPUTUSAN_MAHKAMAH");
			
			
			ID_PERMOHONANSIMATI = (String) h.get("ID_PERMOHONANSIMATI");
			ID_PERBICARAAN = (String) h.get("ID_PERBICARAAN");
			ID_PERINTAH = (String) h.get("ID_PERINTAH");
			ID_BORANGJ = (String) h.get("ID_BORANGJ");
			
			
			
			
			haveINTData = (Boolean) h.get("haveINTData");
		}
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
			this.context.put("SenaraiFail",paging.getPage(page));
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
	
	private void headerppk_baru(HttpSession session, String id_permohonan,
			String flag_permohonan, String id_fail, String flag_fail)
			throws Exception {
		// hashtable maklumat header
		this.context.put("headerppk", mainheader.getHeaderData(session,
				id_permohonan, flag_permohonan, id_fail, flag_fail));
		
		this.context.put("getPemohonData", mainheader.getPemohonData(session,id_permohonan, null));
		
		Vector list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan, flag_permohonan,
				id_fail, flag_fail);
		this.context.put("list_sub_header", list_sub);
		this.context.put("flag_jenis_vm", "utiliti_ajax");
	}
}