package ekptg.view.integrasi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.integrasi.FrmAPBModel;

public class FrmViewAPB extends AjaxBasedModule {
    
	private static final long serialVersionUID = 1L;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	Boolean isDebugMode = true;
	
	//String ID_PERMOHONAN = "";
	String ID_ULASANTEKNIKAL = "";

	// variables utk carian
	String CARIAN_NOFAIL = "", CARIAN_TARIKHSURAT = "", CARIAN_NAMAPEMOHON = "", CARIAN_NOKPPEMOHON = "", CARIAN_NEGERI = "";
	String CARIAN_DAERAH = "", CARIAN_MUKIM = "", CARIAN_AGENSI = "";
	
    // variables utk fail
	String MP_NOFAIL = "", MP_URUSAN = "", MP_TARIKHTERIMA = "", MP_TARIKHSURAT = "", MP_PERKARA = "", MP_NAMAPEMOHON = "";
	String MP_ALAMAT1PEMOHON = "", MP_ALAMAT2PEMOHON = "", MP_ALAMAT3PEMOHON = "", MP_POSKOD = "", MP_NEGERI = "", MP_NOTELEFON = "", MP_NOFAKS = "", MP_ALAMAT = "";
	String KAW_LUARPERAIRANNEGERI = "", KAW_NEGERI = "", KAW_LOKASI = "", KAW_LUASDIPOHON = "", LAINLAIN_MAKLUMATTAMBAHAN = "";
	String MB_ULASAN = "", MB_NAMAPEMOHONTINDIH1 = "", MB_NAMAPEMOHONTINDIH2 = "", MB_NAMAPEMOHONTINDIH3 = "", MB_TARIKHMOHONTINDIH = "", MB_CATATAN = "";
	String MB_NAMAPENGULAS = "", MB_NOTELPENGULAS = "", MB_KOORDINAT = "", MB_HASIMAGE = "";

	Date carianTarikhSurat = date, tarikhMohonTindih = date;
	long idNegeriTapak = 0, idAgensi = 0;
	
	String userID = "", userName = "", userRole = "";
	
	@SuppressWarnings("rawtypes")
	public String doTemplate2() throws Exception{
		
		String vm = "";

        HttpSession session = this.request.getSession();
        userID = (String) session.getAttribute("_ekptg_user_id");
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
                
        // action
        String action  = getParam("action");
        String action2 = getParam("action2");
        String mode    = getParam("mode");
        
//        Boolean doUpload = false;

        FrmAPBModel model = new FrmAPBModel();
        
//        Boolean isAPBUser = false;
//        isAPBUser = model.isAPBUser(userID);

        getDataFromJSP();
        
        long idUlasanTeknikal = 0, idPermohonan = 0;
        if (!"".equalsIgnoreCase(ID_ULASANTEKNIKAL.trim())) {
        	idUlasanTeknikal = Long.parseLong(ID_ULASANTEKNIKAL);
        }
        idPermohonan = model.getIdPermohonanFromIdUlasanTeknikal(idUlasanTeknikal);
        if ("searchAPB".equalsIgnoreCase(action2.trim())) {
        	// search record
        	vm = "app/integrasi/frmAPBCarian.jsp";
        	        	
        	Vector vList = new Vector();
        	model.noFail = CARIAN_NOFAIL;
        	model.tarikhSurat = carianTarikhSurat;
        	model.namaPemohon = CARIAN_NAMAPEMOHON;
        	model.idNegeri = idNegeriTapak;
        	model.idAgensi = idAgensi;
        	vList = model.searchMaklumat();
        	context.put("ListPermohonan", vList);
        	setupPage(session, action, vList);
    		context.put("pagingTitle", "title");
    	} else if ("viewAPB".equalsIgnoreCase(action2.trim())) {
        	// view record
    		if (idUlasanTeknikal > 0) {
            	vm = "app/integrasi/frmAPB.jsp";
    			model.idUlasanTeknikal = idUlasanTeknikal;
    			Vector vData = model.viewMaklumat();
    			populateData(vData);
//    			vData = model.getListUploadedFile(ID_ULASANTEKNIKAL);
//    			context.put("ListUpload", vData);
    			vData = model.getListKoordinat(idPermohonan);
    			context.put("ListKoordinat", vData);
    		} else {
    			vm = "app/integrasi/frmAPBCarian.jsp";
    		}
        } else if ("editAPB".equalsIgnoreCase(action2.trim())) {
        	// edit record
    		if (idUlasanTeknikal > 0) {
            	vm = "app/integrasi/frmAPB.jsp";
    			model.idUlasanTeknikal = idUlasanTeknikal;
    			Vector vData = model.viewMaklumat();
    			populateData(vData);
//    			vData = model.getListUploadedFile(ID_ULASANTEKNIKAL);
//    			context.put("ListUpload", vData);
    			vData = model.getListKoordinat(idPermohonan);
    			context.put("ListKoordinat", vData);
    			if ("changeSOC".equalsIgnoreCase(mode.trim())) {
    				getDataFromJSP();
    			}
    		} else {
    			vm = "app/integrasi/frmAPBCarian.jsp";
    		}
        } else if ("saveAPB".equalsIgnoreCase(action2.trim())) {
        	// save record
    		if (idUlasanTeknikal > 0) {
            	vm = "app/integrasi/frmAPB.jsp";
    			model.idUlasanTeknikal = idUlasanTeknikal;
        		model.agensiUlasanTeknikal = MB_ULASAN;
        		model.agensiNamaPemohonBertindih1 = MB_NAMAPEMOHONTINDIH1;
        		model.agensiNamaPemohonBertindih2 = MB_NAMAPEMOHONTINDIH2;
        		model.agensiNamaPemohonBertindih3 = MB_NAMAPEMOHONTINDIH3;
        		model.agensiKoordinat = MB_KOORDINAT;
        		model.agensiTarikhMohonBertindih = tarikhMohonTindih;
        		model.agensiCatatan = MB_CATATAN;
        		model.agensiNamaPengulas = MB_NAMAPENGULAS;
        		model.agensiNoTelefonPengulas = MB_NOTELPENGULAS;
    			model.saveMaklumat();
    			Vector vData = model.viewMaklumat();
    			populateData(vData);
//    			vData = model.getListUploadedFile(ID_ULASANTEKNIKAL);
//    			context.put("ListUpload", vData);
    			vData = model.getListKoordinat(idPermohonan);
    			context.put("ListKoordinat", vData);
    		} else {
    			vm = "app/integrasi/frmAPBCarian.jsp";
    		}
        } else if ("sendAPB".equalsIgnoreCase(action2.trim())) {
        	// send record
    		if (idUlasanTeknikal > 0) {
            	vm = "app/integrasi/frmAPB.jsp";
    			model.idUlasanTeknikal = idUlasanTeknikal;
        		model.agensiUlasanTeknikal = MB_ULASAN;
        		model.agensiNamaPemohonBertindih1 = MB_NAMAPEMOHONTINDIH1;
        		model.agensiNamaPemohonBertindih2 = MB_NAMAPEMOHONTINDIH2;
        		model.agensiNamaPemohonBertindih3 = MB_NAMAPEMOHONTINDIH3;
        		model.agensiKoordinat = MB_KOORDINAT;
        		model.agensiTarikhMohonBertindih = tarikhMohonTindih;
        		model.agensiCatatan = MB_CATATAN;
        		model.agensiNamaPengulas = MB_NAMAPENGULAS;
        		model.agensiNoTelefonPengulas = MB_NOTELPENGULAS;
    			model.saveMaklumat();
    			model.sendMaklumat();
    			Vector vData = model.viewMaklumat();
    			populateData(vData);
//    			vData = model.getListUploadedFile(ID_ULASANTEKNIKAL);
//    			context.put("ListUpload", vData);
    			vData = model.getListKoordinat(idPermohonan);
    			context.put("ListKoordinat", vData);
    		} else {
    			vm = "app/integrasi/frmAPBCarian.jsp";
    		}
        } else if ("deleteAPB".equalsIgnoreCase(action2.trim())) {
        	// delete record
        	vm = "app/integrasi/frmAPBCarian.jsp";
        } else if ("returnAPB".equalsIgnoreCase(action2.trim())) {
        	// return record
        	vm = "app/integrasi/frmAPBCarian.jsp";
        } else if ("uploadFileAPB".equalsIgnoreCase(action2.trim())) {
        	// file upload
        	if (idUlasanTeknikal > 0) {
	        	vm = "app/integrasi/frmAPB.jsp";
	        	DiskFileItemFactory factory = new DiskFileItemFactory();
			    ServletFileUpload upload = new ServletFileUpload(factory);
	
			    List items = upload.parseRequest(request);
			    Iterator itr = items.iterator();
			    while (itr.hasNext()) {
			    	FileItem objItem = (FileItem)itr.next();
			    	if ((!(objItem.isFormField())) && (objItem.getName() != null) && (!("".equals(objItem.getName())))) {
			    		model.uploadFileAPB(objItem, userID, Long.toString(idUlasanTeknikal));
			    	}
			    }
			    model.idUlasanTeknikal = idUlasanTeknikal;
				Vector vData = model.viewMaklumat();
				populateData(vData);
				vData = model.getListKoordinat(idPermohonan);
				context.put("ListKoordinat", vData);
        	}
        } else if ("deleteImage".equalsIgnoreCase(action2.trim())) {
        	// delete uploaded file
        	if (idUlasanTeknikal > 0) {
	        	vm = "app/integrasi/frmAPB.jsp";
			    model.idUlasanTeknikal = idUlasanTeknikal;
			    model.deleteImage();
				Vector vData = model.viewMaklumat();
				populateData(vData);
				vData = model.getListKoordinat(idPermohonan);
				context.put("ListKoordinat", vData);
        	}
        } else {
        	// display search page
        	vm = "app/integrasi/frmAPBCarian.jsp";
        }
        
    	context.put("selCarianNegeri", HTML.SelectNegeri("CARIAN_NEGERI", idNegeriTapak == 0 ? null : idNegeriTapak, "", " onchange=\"doChangeSOC()\" "));

        context.put("MP_NOFAIL", MP_NOFAIL);
		context.put("MP_URUSAN", MP_URUSAN); 
		context.put("MP_TARIKHTERIMA", MP_TARIKHTERIMA); 
		context.put("MP_TARIKHSURAT", MP_TARIKHSURAT); 
		context.put("MP_PERKARA", MP_PERKARA); 
		context.put("MP_NAMAPEMOHON", MP_NAMAPEMOHON); 
		context.put("MP_ALAMAT1PEMOHON", MP_ALAMAT1PEMOHON); 
		context.put("MP_ALAMAT2PEMOHON", MP_ALAMAT2PEMOHON); 
		context.put("MP_ALAMAT3PEMOHON", MP_ALAMAT3PEMOHON);
		context.put("MP_ALAMAT", MP_ALAMAT);
		context.put("MP_NOTELEFON", MP_NOTELEFON); 
		context.put("MP_NOFAKS", MP_NOFAKS);
		context.put("KAW_LUARPERAIRANNEGERI", "1".equalsIgnoreCase(KAW_LUARPERAIRANNEGERI.trim()) ? "YA" : "TIDAK");
		context.put("KAW_NEGERI", KAW_NEGERI);
		context.put("KAW_LOKASI", KAW_LOKASI);
		context.put("KAW_LUASDIPOHON", KAW_LUASDIPOHON);
		context.put("LAINLAIN_MAKLUMATTAMBAHAN", LAINLAIN_MAKLUMATTAMBAHAN);
		context.put("MB_ULASAN", MB_ULASAN); 
		context.put("MB_NAMAPEMOHONTINDIH1", MB_NAMAPEMOHONTINDIH1);
		context.put("MB_NAMAPEMOHONTINDIH2", MB_NAMAPEMOHONTINDIH2);
		context.put("MB_NAMAPEMOHONTINDIH3", MB_NAMAPEMOHONTINDIH3);
		context.put("MB_TARIKHMOHONTINDIH", MB_TARIKHMOHONTINDIH); 
		context.put("MB_KOORDINAT", MB_KOORDINAT);
		context.put("MB_CATATAN", MB_CATATAN);
		context.put("MB_NAMAPENGULAS", MB_NAMAPENGULAS);
		context.put("MB_NOTELPENGULAS", MB_NOTELPENGULAS);

		context.put("hasImage", model.hasImage(ID_ULASANTEKNIKAL));
		context.put("imageName", model.imageName(ID_ULASANTEKNIKAL));
        context.put("ID_ULASANTEKNIKAL", ID_ULASANTEKNIKAL);
        context.put("ID_PERMOHONAN", idPermohonan);
        context.put("isDebugMode", isDebugMode);
        context.put("action2", action2);
        context.put("action", action);
        context.put("mode", mode);
		return vm;
	}
	
	private void getDataFromJSP() throws ParseException {
		ID_ULASANTEKNIKAL = getParam("ID_ULASANTEKNIKAL");
		MB_ULASAN = getParam("MB_ULASAN");
		MB_NAMAPEMOHONTINDIH1 = getParam("MB_NAMAPEMOHONTINDIH1");
		MB_NAMAPEMOHONTINDIH2 = getParam("MB_NAMAPEMOHONTINDIH2");
		MB_NAMAPEMOHONTINDIH3 = getParam("MB_NAMAPEMOHONTINDIH3");
		MB_TARIKHMOHONTINDIH = getParam("MB_TARIKHMOHONTINDIH");
		MB_KOORDINAT = getParam("MB_KOORDINAT");
		MB_CATATAN = getParam("MB_CATATAN");
		MB_NAMAPENGULAS = getParam("MB_NAMAPENGULAS");
		MB_NOTELPENGULAS = getParam("MB_NOTELPENGULAS");		
    	CARIAN_NOFAIL = getParam("CARIAN_NOFAIL");
    	CARIAN_TARIKHSURAT = getParam("CARIAN_TARIKHSURAT");
    	CARIAN_NAMAPEMOHON = getParam("CARIAN_NAMAPEMOHON");
    	CARIAN_NEGERI = getParam("CARIAN_NEGERI");
    	CARIAN_AGENSI = getParam("CARIAN_AGENSI");
    	if (!"".equalsIgnoreCase(CARIAN_TARIKHSURAT.trim())) {
    		carianTarikhSurat = (Date) sdf.parse(CARIAN_TARIKHSURAT);
    	} else {
    		carianTarikhSurat = null;
    	}
    	if (!"".equalsIgnoreCase(CARIAN_NEGERI.trim())) {
    		idNegeriTapak = Long.valueOf(CARIAN_NEGERI);
    	} else {
    		idNegeriTapak = (long) 0;
    	}
    	if (!"".equalsIgnoreCase(MB_TARIKHMOHONTINDIH.trim())) {
    		tarikhMohonTindih = (Date) sdf.parse(MB_TARIKHMOHONTINDIH);
    	} else {
    		tarikhMohonTindih = null;
    	}
	}
	
	@SuppressWarnings("rawtypes")
	private void populateData(Vector v) {
		try {
			if (!v.isEmpty()) {
				MP_ALAMAT = "";
				Hashtable h = (Hashtable) v.get(0);
				ID_ULASANTEKNIKAL = (String) h.get("ID_ULASANTEKNIKAL");
				MP_NOFAIL = (String) h.get("MP_NOFAIL");
				MP_URUSAN = (String) h.get("MP_URUSAN");
				MP_TARIKHTERIMA = (String) h.get("MP_TARIKHTERIMA");
				MP_TARIKHSURAT = (String) h.get("MP_TARIKHSURAT");
				MP_PERKARA = (String) h.get("MP_PERKARA");
				MP_NAMAPEMOHON = (String) h.get("MP_NAMAPEMOHON");
				MP_ALAMAT1PEMOHON = (String) h.get("MP_ALAMAT1PEMOHON");
				MP_ALAMAT2PEMOHON = (String) h.get("MP_ALAMAT2PEMOHON");
				MP_ALAMAT3PEMOHON = (String) h.get("MP_ALAMAT3PEMOHON");
				if (!"".equalsIgnoreCase(MP_ALAMAT1PEMOHON.trim())) {
					MP_ALAMAT += MP_ALAMAT1PEMOHON + ",<br />";
				}
				if (!"".equalsIgnoreCase(MP_ALAMAT2PEMOHON.trim())) {
					MP_ALAMAT += MP_ALAMAT2PEMOHON + ",<br />";
				}
				if (!"".equalsIgnoreCase(MP_ALAMAT3PEMOHON.trim())) {
					MP_ALAMAT += MP_ALAMAT3PEMOHON + ",<br />";
				}
				MP_ALAMAT = MP_ALAMAT.substring(0, MP_ALAMAT.length() - 7);
				MP_POSKOD = (String) h.get("MP_POSKOD");
				MP_NEGERI = (String) h.get("MP_NEGERI");
				MP_NOTELEFON = (String) h.get("MP_NOTELEFON");
				MP_NOFAKS = (String) h.get("MP_NOFAKS");
				KAW_LUARPERAIRANNEGERI = (String) h.get("KAW_LUARPERAIRANNEGERI");
				KAW_NEGERI = (String) h.get("KAW_NEGERI");
				KAW_LOKASI = (String) h.get("KAW_LOKASI");
				KAW_LUASDIPOHON = (String) h.get("KAW_LUASDIPOHON");
				LAINLAIN_MAKLUMATTAMBAHAN = (String) h.get("LAINLAIN_MAKLUMATTAMBAHAN");
				MB_ULASAN = (String) h.get("MB_ULASAN");
				MB_NAMAPEMOHONTINDIH1 = (String) h.get("MB_NAMAPEMOHONTINDIH1");
				MB_NAMAPEMOHONTINDIH2 = (String) h.get("MB_NAMAPEMOHONTINDIH2");
				MB_NAMAPEMOHONTINDIH3 = (String) h.get("MB_NAMAPEMOHONTINDIH3");
				MB_KOORDINAT = (String) h.get("MB_KOORDINAT");
				MB_TARIKHMOHONTINDIH = (String) h.get("MB_TARIKHMOHONTINDIH");
				MB_CATATAN = (String) h.get("MB_CATATAN");
				MB_NAMAPENGULAS = (String) h.get("MB_NAMAPENGULAS");
				MB_NOTELPENGULAS = (String) h.get("MB_NOTELPENGULAS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
	}
	
	@SuppressWarnings("rawtypes")
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
			this.context.put("ListUploaded",paging.getPage(page));
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