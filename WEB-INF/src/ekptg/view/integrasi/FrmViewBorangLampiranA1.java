package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ekptg.helpers.Paging;
import ekptg.model.integrasi.FrmModelBorangLampiranA1;

@SuppressWarnings({ "serial", "unused" })
public class FrmViewBorangLampiranA1 extends AjaxBasedModule {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	String Carian_NoFail = "";
	String Carian_NoPermohonan = "";
	String Carian_NoLot = "";
	String Carian_NoSyit = "";
	
	String ID_PERMOHONAN = "";
	String STATUS_PROSES = "";
	
	String MP_NOPERMOHONAN = "";
	String MP_NOFAIL = "";
	String MP_NEGERI = "";
	String MP_DAERAH = "";
	String MP_TAJUKPERMOHONAN = "";
	
	String JPBD_NORUJUKANJPBD = "";
	String JPBD_NOWARTA = "";
	String JPBD_DALAMKAWASANPBPT = "";
	String JPBD_DALAMKAWASANPBPT0 = "";
	String JPBD_DALAMKAWASANPBPT1 = "";
	String JPBD_ADAPELANSTRUKTUR = "";
	String JPBD_ADAPELANTEMPATAN = "";
	String JPBD_TARIKHLULUSPELANSTRUKTUR = "";
	String JPBD_TARIKHLULUSPELANTEMPATAN = "";
	String JPBD_KEGUNAANTANAH = "";
	String JPBD_POTENSIPEMBANGUNAN = "";
	String JPBD_NAMAPBT = "";
	String JPBD_STATUSKELULUSAN = "";
	String JPBD_PERMOHONANMEMAJUKANTANAH = "";
	String JPBD_PERMOHONANMEMAJUKANTANAH0 = "";
	String JPBD_PERMOHONANMEMAJUKANTANAH1 = "";
	String JPBD_TUJUANPERMOHONAN = "";
	String JPBD_TARIKHLULUSTOLAK = "";
	String JPBD_TARIKHLUPUTKELULUSAN = "";
	String JPBD_CATATANLAIN = "";
	String JPBD_NAMAPEGAWAIJPBD = "";
	String JPBD_JAWATANPEGAWAIJPBD = "";
	String JPBD_TANDATANGANBAGIPIHAK = "";
	String JPBD_NAMAPEGAWAIASAL = "";
	String JPBD_JAWATANPEGAWAIASAL = "";
	String JPBD_TARIKHPERMOHONAN = "";
	String JPBD_JABATAN = "";
	
	String LAMPIRAN_IDLAMPIRAN = "";
	
	String userName = "", userRole = "", userID = "";
	
	Boolean haveINTData = false;
	
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		String vm = "";

        HttpSession session = this.request.getSession();
        
        FrmModelBorangLampiranA1 modelBorang = new FrmModelBorangLampiranA1();
        
        context.remove("haveINTData");
        context.remove("saveBorangLampiranA1");
        context.remove("sendBorangLampiranA1");
        context.remove("deleteBorangLampiranA1");
        
        String action = getParam("action2");
        
        String command = getParam("command");
        
        String READONLY_JPBD = " readonly=\"readonly\" ", DISABLE_JPBD = " disabled=\"disabled\" ";
        String READONLY_JKPTG = " readonly=\"readonly\" ", DISABLE_JKPTG = " disabled=\"disabled\" ";
        
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        userID = (String) session.getAttribute("_ekptg_user_id");
        
        Boolean isJPBDUser = isJPBDUser(userRole);
        Boolean Page_Carian = false;
        Boolean saveBorangLampiranA1 = false, sendBorangLampiranA1 = false, deleteBorangLampiranA1 = false, verifyBorangLampiranA1 = false;
        Boolean isPegawaiJPBD = false;
        
        isPegawaiJPBD = modelBorang.isPegawaiJPBD(userID);
                
        ID_PERMOHONAN = getParam("ID_PERMOHONAN");
        
        Carian_NoFail = getParam("Carian_NoFail");
        Carian_NoPermohonan = getParam("Carian_NoPermohonan");
        Carian_NoLot = getParam("Carian_NoLot");
        Carian_NoSyit = getParam("Carian_NoSyit");

    	JPBD_NORUJUKANJPBD = getParam("JPBD_NORUJUKANJPBD");
    	JPBD_NOWARTA = getParam("JPBD_NOWARTA");
    	JPBD_DALAMKAWASANPBPT = getParam("JPBD_DALAMKAWASANPBPT");
    	JPBD_ADAPELANSTRUKTUR = getParam("JPBD_ADAPELANSTRUKTUR");
    	JPBD_ADAPELANTEMPATAN = getParam("JPBD_ADAPELANTEMPATAN");
    	JPBD_TARIKHLULUSPELANSTRUKTUR = getParam("JPBD_TARIKHLULUSPELANSTRUKTUR");
    	JPBD_TARIKHLULUSPELANTEMPATAN = getParam("JPBD_TARIKHLULUSPELANSTRUKTUR");
    	JPBD_KEGUNAANTANAH = getParam("JPBD_KEGUNAANTANAH");
    	JPBD_POTENSIPEMBANGUNAN = getParam("JPBD_POTENSIPEMBANGUNAN");
    	JPBD_NAMAPBT = getParam("JPBD_NAMAPBT");
    	JPBD_STATUSKELULUSAN = getParam("JPBD_STATUSKELULUSAN");
    	JPBD_PERMOHONANMEMAJUKANTANAH = getParam("JPBD_PERMOHONANMEMAJUKANTANAH");
    	JPBD_TUJUANPERMOHONAN = getParam("JPBD_TUJUANPERMOHONAN");
    	JPBD_TARIKHLULUSTOLAK = getParam("JPBD_TARIKHLULUSTOLAK");
    	JPBD_TARIKHLUPUTKELULUSAN = getParam("JPBD_TARIKHLUPUTKELULUSAN");
    	JPBD_CATATANLAIN = getParam("JPBD_CATATANLAIN");
    	JPBD_NAMAPEGAWAIJPBD = getParam("JPBD_NAMAPEGAWAIJPBD");
    	JPBD_JAWATANPEGAWAIJPBD = getParam("JPBD_JAWATANPEGAWAIJPBD");
    	JPBD_TANDATANGANBAGIPIHAK = getParam("JPBD_TANDATANGANBAGIPIHAK");
    	JPBD_NAMAPEGAWAIASAL = getParam("JPBD_NAMAPEGAWAIASAL");
    	JPBD_JAWATANPEGAWAIASAL = getParam("JPBD_JAWATANPEGAWAIASAL");
    	JPBD_TARIKHPERMOHONAN = getParam("JPBD_TARIKHPERMOHONAN");
    	JPBD_JABATAN = getParam("JPBD_JABATAN");
    	
    	LAMPIRAN_IDLAMPIRAN = getParam("LAMPIRAN_IDLAMPIRAN");
    	
    	Vector vData = new Vector();
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();
    	
    	Boolean doUpload = false, doDeleteFile = false, deleteFile = false;
    	
        if ("searchBorangLampiranA1".equalsIgnoreCase(action)) {
        	vm = "app/integrasi/frmBorangLampiranA1Carian.jsp";
        	vList = modelBorang.searchPermohonan(Carian_NoFail, Carian_NoPermohonan, Carian_NoLot, Carian_NoSyit);
        	setupPage(session, action, vList);
        	context.put("ListPermohonan", vList);
        	Page_Carian = true;
        } else if ("viewBorangLampiranA1".equalsIgnoreCase(action)) {
    		// initial VM value
        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
    		
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			vData = modelBorang.viewMaklumatPermohonan(ID_PERMOHONAN);
    			if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    		}
        } else if ("sendBorangLampiranA1".equalsIgnoreCase(action)) {
    		// initial VM value
        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
    		
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			h = new Hashtable();
				h.put("JPBD_NORUJUKANJPBD", JPBD_NORUJUKANJPBD);
				h.put("JPBD_NOWARTA", JPBD_NOWARTA);
				h.put("JPBD_DALAMKAWASANPBPT", JPBD_DALAMKAWASANPBPT);
				h.put("JPBD_ADAPELANSTRUKTUR", JPBD_ADAPELANSTRUKTUR);
				h.put("JPBD_ADAPELANTEMPATAN", JPBD_ADAPELANTEMPATAN);
				h.put("JPBD_TARIKHLULUSPELANSTRUKTUR", JPBD_TARIKHLULUSPELANSTRUKTUR);
				h.put("JPBD_TARIKHLULUSPELANTEMPATAN", JPBD_TARIKHLULUSPELANTEMPATAN);
				h.put("JPBD_KEGUNAANTANAH", JPBD_KEGUNAANTANAH);
				h.put("JPBD_POTENSIPEMBANGUNAN", JPBD_POTENSIPEMBANGUNAN);
				h.put("JPBD_NAMAPBT", JPBD_NAMAPBT);
				h.put("JPBD_STATUSKELULUSAN", JPBD_STATUSKELULUSAN);
				h.put("JPBD_PERMOHONANMEMAJUKANTANAH", JPBD_PERMOHONANMEMAJUKANTANAH);
				h.put("JPBD_TUJUANPERMOHONAN", JPBD_TUJUANPERMOHONAN);
				h.put("JPBD_TARIKHLULUSTOLAK", JPBD_TARIKHLULUSTOLAK);
				h.put("JPBD_TARIKHLUPUTKELULUSAN", JPBD_TARIKHLUPUTKELULUSAN);
				h.put("JPBD_CATATANLAIN", JPBD_CATATANLAIN);
				h.put("JPBD_NAMAPEGAWAIJPBD", JPBD_NAMAPEGAWAIJPBD);
				h.put("JPBD_JAWATANPEGAWAIJPBD", JPBD_JAWATANPEGAWAIJPBD);
				h.put("JPBD_TANDATANGANBAGIPIHAK", JPBD_TANDATANGANBAGIPIHAK);
				h.put("JPBD_NAMAPEGAWAIASAL", JPBD_NAMAPEGAWAIASAL);
				h.put("JPBD_JAWATANPEGAWAIASAL", JPBD_JAWATANPEGAWAIASAL);
				h.put("JPBD_TARIKHPERMOHONAN", JPBD_TARIKHPERMOHONAN);
				h.put("JPBD_JABATAN", JPBD_JABATAN);
				saveBorangLampiranA1 = modelBorang.saveBorangLampiranA1(ID_PERMOHONAN, userID, isJPBDUser, h);
				sendBorangLampiranA1 = modelBorang.sendBorangLampiranA1(ID_PERMOHONAN, isJPBDUser);
    			vData = modelBorang.viewMaklumatPermohonan(ID_PERMOHONAN);
    			if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    		}
        } else if ("saveBorangLampiranA1".equalsIgnoreCase(action)) {
    		// initial VM value
        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
    		
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			h = new Hashtable();
				h.put("JPBD_NORUJUKANJPBD", JPBD_NORUJUKANJPBD);
				h.put("JPBD_NOWARTA", JPBD_NOWARTA);
				h.put("JPBD_DALAMKAWASANPBPT", JPBD_DALAMKAWASANPBPT);
				h.put("JPBD_ADAPELANSTRUKTUR", JPBD_ADAPELANSTRUKTUR);
				h.put("JPBD_ADAPELANTEMPATAN", JPBD_ADAPELANTEMPATAN);
				h.put("JPBD_TARIKHLULUSPELANSTRUKTUR", JPBD_TARIKHLULUSPELANSTRUKTUR);
				h.put("JPBD_TARIKHLULUSPELANTEMPATAN", JPBD_TARIKHLULUSPELANTEMPATAN);
				h.put("JPBD_KEGUNAANTANAH", JPBD_KEGUNAANTANAH);
				h.put("JPBD_POTENSIPEMBANGUNAN", JPBD_POTENSIPEMBANGUNAN);
				h.put("JPBD_NAMAPBT", JPBD_NAMAPBT);
				h.put("JPBD_STATUSKELULUSAN", JPBD_STATUSKELULUSAN);
				h.put("JPBD_PERMOHONANMEMAJUKANTANAH", JPBD_PERMOHONANMEMAJUKANTANAH);
				h.put("JPBD_TUJUANPERMOHONAN", JPBD_TUJUANPERMOHONAN);
				h.put("JPBD_TARIKHLULUSTOLAK", JPBD_TARIKHLULUSTOLAK);
				h.put("JPBD_TARIKHLUPUTKELULUSAN", JPBD_TARIKHLUPUTKELULUSAN);
				h.put("JPBD_CATATANLAIN", JPBD_CATATANLAIN);
				h.put("JPBD_NAMAPEGAWAIJPBD", JPBD_NAMAPEGAWAIJPBD);
				h.put("JPBD_JAWATANPEGAWAIJPBD", JPBD_JAWATANPEGAWAIJPBD);
				h.put("JPBD_TANDATANGANBAGIPIHAK", JPBD_TANDATANGANBAGIPIHAK);
				h.put("JPBD_NAMAPEGAWAIASAL", JPBD_NAMAPEGAWAIASAL);
				h.put("JPBD_JAWATANPEGAWAIASAL", JPBD_JAWATANPEGAWAIASAL);
				h.put("JPBD_TARIKHPERMOHONAN", JPBD_TARIKHPERMOHONAN);
				h.put("JPBD_JABATAN", JPBD_JABATAN);
				saveBorangLampiranA1 = modelBorang.saveBorangLampiranA1(ID_PERMOHONAN, userID, isJPBDUser, h);
    			vData = modelBorang.viewMaklumatPermohonan(ID_PERMOHONAN);
    			if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    		}
        } else if ("verifyBorangLampiranA1".equalsIgnoreCase(action)) {
    		// initial VM value
        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
    		
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			h = new Hashtable();
				h.put("JPBD_NORUJUKANJPBD", JPBD_NORUJUKANJPBD);
				h.put("JPBD_NOWARTA", JPBD_NOWARTA);
				h.put("JPBD_DALAMKAWASANPBPT", JPBD_DALAMKAWASANPBPT);
				h.put("JPBD_ADAPELANSTRUKTUR", JPBD_ADAPELANSTRUKTUR);
				h.put("JPBD_ADAPELANTEMPATAN", JPBD_ADAPELANTEMPATAN);
				h.put("JPBD_TARIKHLULUSPELANSTRUKTUR", JPBD_TARIKHLULUSPELANSTRUKTUR);
				h.put("JPBD_TARIKHLULUSPELANTEMPATAN", JPBD_TARIKHLULUSPELANTEMPATAN);
				h.put("JPBD_KEGUNAANTANAH", JPBD_KEGUNAANTANAH);
				h.put("JPBD_POTENSIPEMBANGUNAN", JPBD_POTENSIPEMBANGUNAN);
				h.put("JPBD_NAMAPBT", JPBD_NAMAPBT);
				h.put("JPBD_STATUSKELULUSAN", JPBD_STATUSKELULUSAN);
				h.put("JPBD_PERMOHONANMEMAJUKANTANAH", JPBD_PERMOHONANMEMAJUKANTANAH);
				h.put("JPBD_TUJUANPERMOHONAN", JPBD_TUJUANPERMOHONAN);
				h.put("JPBD_TARIKHLULUSTOLAK", JPBD_TARIKHLULUSTOLAK);
				h.put("JPBD_TARIKHLUPUTKELULUSAN", JPBD_TARIKHLUPUTKELULUSAN);
				h.put("JPBD_CATATANLAIN", JPBD_CATATANLAIN);
				h.put("JPBD_NAMAPEGAWAIJPBD", JPBD_NAMAPEGAWAIJPBD);
				h.put("JPBD_JAWATANPEGAWAIJPBD", JPBD_JAWATANPEGAWAIJPBD);
				h.put("JPBD_TANDATANGANBAGIPIHAK", JPBD_TANDATANGANBAGIPIHAK);
				h.put("JPBD_NAMAPEGAWAIASAL", JPBD_NAMAPEGAWAIASAL);
				h.put("JPBD_JAWATANPEGAWAIASAL", JPBD_JAWATANPEGAWAIASAL);
				h.put("JPBD_TARIKHPERMOHONAN", JPBD_TARIKHPERMOHONAN);
				h.put("JPBD_JABATAN", JPBD_JABATAN);
				saveBorangLampiranA1 = modelBorang.saveBorangLampiranA1(ID_PERMOHONAN, userID, isJPBDUser, h);
				verifyBorangLampiranA1 = modelBorang.verifyBorangLampiranA1(ID_PERMOHONAN);
    			vData = modelBorang.viewMaklumatPermohonan(ID_PERMOHONAN);
    			if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    		}
        } else if ("deleteBorangLampiranA1".equalsIgnoreCase(action)) {
    		// initial VM value
        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
    		
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				deleteBorangLampiranA1 = modelBorang.deleteBorangLampiranA1(ID_PERMOHONAN);
				if (deleteBorangLampiranA1) {
					vm = "app/integrasi/frmBorangLampiranA1Carian.jsp";
				}
    		}
        } else if ("printSuratIringanJKPTG".equalsIgnoreCase(action)) {
        	vm = "app/integrasi/frmBorangLampiranA1SuratIringanKeJKPTG.jsp";

        	if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			vData = modelBorang.viewMaklumatPermohonan(ID_PERMOHONAN);
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    		}
        } else if ("doUpload".equalsIgnoreCase(action)) {
        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
        	
        	if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
	        	DiskFileItemFactory factory = new DiskFileItemFactory();
			    ServletFileUpload upload = new ServletFileUpload(factory);
			    String objName = "", MIME_Type = "";
	
			    List items = upload.parseRequest(request);
			    Iterator itr = items.iterator();
			    while (itr.hasNext()) {
			    	FileItem objItem = (FileItem)itr.next();
			    	if ((!(objItem.isFormField())) && (objItem.getName() != null) && (!("".equals(objItem.getName())))) {
			    		doUpload = modelBorang.doUpload(objItem, ID_PERMOHONAN);
			    	}
			    }
			    vData = modelBorang.viewMaklumatPermohonan(ID_PERMOHONAN);
    			if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
        	}
        } else if ("deleteFile".equalsIgnoreCase(action)) {
        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
        	
        	if (!"".equalsIgnoreCase(LAMPIRAN_IDLAMPIRAN)) {
        		doDeleteFile = true;
        		deleteFile = modelBorang.deleteFile(LAMPIRAN_IDLAMPIRAN);
        	}
		    vData = modelBorang.viewMaklumatPermohonan(ID_PERMOHONAN);
			if (!vData.isEmpty()) {
	        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
				h = (Hashtable) vData.get(0);
				populatePage(h);
			}
        } else {
        	vm = "app/integrasi/frmBorangLampiranA1Carian.jsp";
    		setupPage(session, action, vList);
    		context.put("pagingTitle", "title");
    		Page_Carian = true;
        }
        
    	if (isJPBDUser) {
    		READONLY_JPBD = "";
    		DISABLE_JPBD = "";
    	} else {
    		READONLY_JKPTG = "";
    		DISABLE_JKPTG = "";
    	}
    	context.put("READONLY_JPBD", READONLY_JPBD);
    	context.put("DISABLE_JPBD", DISABLE_JPBD);
    	context.put("READONLY_JKPTG", READONLY_JKPTG);
    	context.put("DISABLE_JKPTG", DISABLE_JKPTG);
    	context.put("isJPBDUser", isJPBDUser);

        if (Page_Carian) {
        	context.put("ListPermohonan", vList);
        } else {
        	vList = modelBorang.ListLampiran(ID_PERMOHONAN);
        	context.put("ListLampiran", vList);
        	
        	STATUS_PROSES = modelBorang.getStatusPermohonan(ID_PERMOHONAN);
        	
        	if ("0".equalsIgnoreCase(JPBD_DALAMKAWASANPBPT) || "".equalsIgnoreCase(JPBD_DALAMKAWASANPBPT)) {
        		JPBD_DALAMKAWASANPBPT0 = " checked=\"checked\" ";
        		JPBD_DALAMKAWASANPBPT1 = "";
        	} else {
        		JPBD_DALAMKAWASANPBPT0 = "";
        		JPBD_DALAMKAWASANPBPT1 = " checked=\"checked\" ";
        	}
        	if ("1".equalsIgnoreCase(JPBD_ADAPELANSTRUKTUR)) {
        		JPBD_ADAPELANSTRUKTUR = "checked=\"checked\" ";
        	}
        	if ("1".equalsIgnoreCase(JPBD_ADAPELANTEMPATAN)) {
        		JPBD_ADAPELANTEMPATAN = " checked=\"checked\" ";
        	}
        	if ("0".equalsIgnoreCase(JPBD_PERMOHONANMEMAJUKANTANAH) || "".equalsIgnoreCase(JPBD_PERMOHONANMEMAJUKANTANAH)) {
        		JPBD_PERMOHONANMEMAJUKANTANAH0 = " checked=\"checked\" ";
        		JPBD_PERMOHONANMEMAJUKANTANAH1 = "";
        	} else {
        		JPBD_PERMOHONANMEMAJUKANTANAH0 = "";
        		JPBD_PERMOHONANMEMAJUKANTANAH1 = " checked=\"checked\" ";
        	}
        	if ("1".equalsIgnoreCase(JPBD_TANDATANGANBAGIPIHAK)) {
        		JPBD_TANDATANGANBAGIPIHAK = " checked=\"checked\" ";
        	}
        	
        	context.put("haveINTData", haveINTData);
        	context.put("doDeleteFile", doDeleteFile);
        	context.put("deleteFile", deleteFile);
        	context.put("deleteBorangLampiranA1", deleteBorangLampiranA1);
        	context.put("saveBorangLampiranA1", saveBorangLampiranA1);
        	context.put("sendBorangLampiranA1", sendBorangLampiranA1);
        	context.put("verifyBorangLampiranA1", verifyBorangLampiranA1);
        	context.put("isPegawaiJPBD", isPegawaiJPBD);
        	context.put("STATUS_PROSES", STATUS_PROSES);

        	context.put("MP_NOPERMOHONAN", MP_NOPERMOHONAN);
	        context.put("MP_NOFAIL", MP_NOFAIL);
	    	context.put("MP_NEGERI", MP_NEGERI);
	    	context.put("MP_DAERAH", MP_DAERAH);
	    	context.put("MP_TAJUKPERMOHONAN", MP_TAJUKPERMOHONAN);

	    	context.put("JPBD_NORUJUKANJPBD", JPBD_NORUJUKANJPBD);
	    	context.put("JPBD_NOWARTA", JPBD_NOWARTA);
	    	context.put("JPBD_DALAMKAWASANPBPT", JPBD_DALAMKAWASANPBPT);
	    	context.put("JPBD_DALAMKAWASANPBPT0", JPBD_DALAMKAWASANPBPT0);
	    	context.put("JPBD_DALAMKAWASANPBPT1", JPBD_DALAMKAWASANPBPT1);
	    	context.put("JPBD_ADAPELANSTRUKTUR", JPBD_ADAPELANSTRUKTUR);
	    	context.put("JPBD_ADAPELANTEMPATAN", JPBD_ADAPELANTEMPATAN);
	    	context.put("JPBD_TARIKHLULUSPELANSTRUKTUR", JPBD_TARIKHLULUSPELANSTRUKTUR);
	    	context.put("JPBD_TARIKHLULUSPELANTEMPATAN", JPBD_TARIKHLULUSPELANTEMPATAN);
	    	context.put("JPBD_KEGUNAANTANAH", JPBD_KEGUNAANTANAH);
	    	context.put("JPBD_POTENSIPEMBANGUNAN", JPBD_POTENSIPEMBANGUNAN);
	    	context.put("JPBD_NAMAPBT", JPBD_NAMAPBT);
	    	context.put("JPBD_STATUSKELULUSAN", JPBD_STATUSKELULUSAN);
	    	context.put("JPBD_PERMOHONANMEMAJUKANTANAH", JPBD_PERMOHONANMEMAJUKANTANAH);
	    	context.put("JPBD_PERMOHONANMEMAJUKANTANAH0", JPBD_PERMOHONANMEMAJUKANTANAH0);
	    	context.put("JPBD_PERMOHONANMEMAJUKANTANAH1", JPBD_PERMOHONANMEMAJUKANTANAH1);
	    	context.put("JPBD_TUJUANPERMOHONAN", JPBD_TUJUANPERMOHONAN);
	    	context.put("JPBD_TARIKHLULUSTOLAK", JPBD_TARIKHLULUSTOLAK);
	    	context.put("JPBD_TARIKHLUPUTKELULUSAN", JPBD_TARIKHLUPUTKELULUSAN);
	    	context.put("JPBD_CATATANLAIN", JPBD_CATATANLAIN);
	    	context.put("JPBD_NAMAPEGAWAIJPBD", JPBD_NAMAPEGAWAIJPBD);
	    	context.put("JPBD_JAWATANPEGAWAIJPBD", JPBD_JAWATANPEGAWAIJPBD);
	    	context.put("JPBD_TANDATANGANBAGIPIHAK", JPBD_TANDATANGANBAGIPIHAK);
	    	context.put("JPBD_NAMAPEGAWAIASAL", JPBD_NAMAPEGAWAIASAL);
	    	context.put("JPBD_JAWATANPEGAWAIASAL", JPBD_JAWATANPEGAWAIASAL);
	    	context.put("JPBD_TARIKHPERMOHONAN", JPBD_TARIKHPERMOHONAN);
	    	context.put("JPBD_JABATAN", JPBD_JABATAN);
        }
        context.put("ID_PERMOHONAN", ID_PERMOHONAN);
        context.put("action2", action);

        return vm;
	}
	
	@SuppressWarnings("unchecked")
	private void populatePage(Hashtable h) throws Exception {
		if (!h.isEmpty()) {
			MP_NOPERMOHONAN = (String) h.get("MP_NOPERMOHONAN");
			MP_NOFAIL = (String) h.get("MP_NOFAIL");
			MP_NEGERI = (String) h.get("MP_NEGERI");
			MP_DAERAH = (String) h.get("MP_DAERAH");
			MP_TAJUKPERMOHONAN = (String) h.get("MP_TAJUKPERMOHONAN");
			JPBD_NORUJUKANJPBD = (String) h.get("JPBD_NORUJUKANJPBD");
			JPBD_NOWARTA = (String) h.get("JPBD_NOWARTA");
			JPBD_DALAMKAWASANPBPT = (String) h.get("JPBD_DALAMKAWASANPBPT");
			JPBD_ADAPELANSTRUKTUR = (String) h.get("JPBD_ADAPELANSTRUKTUR");
			JPBD_ADAPELANTEMPATAN = (String) h.get("JPBD_ADAPELANTEMPATAN");
			JPBD_TARIKHLULUSPELANSTRUKTUR = (String) h.get("JPBD_TARIKHLULUSPELANSTRUKTUR");
			JPBD_TARIKHLULUSPELANTEMPATAN = (String) h.get("JPBD_TARIKHLULUSPELANTEMPATAN");
			JPBD_KEGUNAANTANAH = (String) h.get("JPBD_KEGUNAANTANAH");
			JPBD_POTENSIPEMBANGUNAN = (String) h.get("JPBD_POTENSIPEMBANGUNAN");
			JPBD_NAMAPBT = (String) h.get("JPBD_NAMAPBT");
			JPBD_STATUSKELULUSAN = (String) h.get("JPBD_STATUSKELULUSAN");
			JPBD_PERMOHONANMEMAJUKANTANAH = (String) h.get("JPBD_PERMOHONANMEMAJUKANTANAH");
			JPBD_TUJUANPERMOHONAN = (String) h.get("JPBD_TUJUANPERMOHONAN");
			JPBD_TARIKHLULUSTOLAK = (String) h.get("JPBD_TARIKHLULUSTOLAK");
			JPBD_TARIKHLUPUTKELULUSAN = (String) h.get("JPBD_TARIKHLUPUTKELULUSAN");
			JPBD_CATATANLAIN = (String) h.get("JPBD_CATATANLAIN");
			JPBD_NAMAPEGAWAIJPBD = (String) h.get("JPBD_NAMAPEGAWAIJPBD");
			JPBD_JAWATANPEGAWAIJPBD = (String) h.get("JPBD_JAWATANPEGAWAIJPBD");
			JPBD_TANDATANGANBAGIPIHAK = (String) h.get("JPBD_TANDATANGANBAGIPIHAK");
			JPBD_NAMAPEGAWAIASAL = (String) h.get("JPBD_NAMAPEGAWAIASAL");
			JPBD_JAWATANPEGAWAIASAL = (String) h.get("JPBD_JAWATANPEGAWAIASAL");
			JPBD_TARIKHPERMOHONAN = (String) h.get("JPBD_TARIKHPERMOHONAN");
			JPBD_JABATAN = (String) h.get("JPBD_JABATAN");
			if ("true".equalsIgnoreCase((String) h.get("haveINTData"))) {
				haveINTData = true;
			}
		}
	}
	
	private Boolean isJPBDUser(String USER_ROLE) throws Exception {
		Boolean returnValue = false;
		if ("jpbd".equalsIgnoreCase(USER_ROLE)) {
			returnValue = true;
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public void setupPage(HttpSession session, String action, Vector list) {
		try {
		
		this.context.put("totalRecords", list.size());
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
	    	
	    Paging paging = new Paging(session, list, itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("ListPermohonan", paging.getPage(page));
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