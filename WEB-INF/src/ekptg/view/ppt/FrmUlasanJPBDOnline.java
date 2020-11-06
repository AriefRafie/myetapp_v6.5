package ekptg.view.ppt;

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
import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.integrasi.FrmModelBorangLampiranA1;
import ekptg.model.integrasi.FrmModelMyInfoBorangLampiranA1;
import ekptg.model.ppt.FrmUlasanJPBDOnlineData;
import ekptg.model.ppt.PPTHeader;
import ekptg.model.utils.IUserPegawai;
import ekptg.model.utils.UserKJPBean;
import ekptg.model.utils.emel.EmailConfig;
import ekptg.model.utils.emel.IEmel;
import ekptg.view.htp.online.jrp.HTPEmelJRPBean;

@SuppressWarnings({ "serial", "unused" })
public class FrmUlasanJPBDOnline extends AjaxBasedModule {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLogger = Logger.getLogger(FrmUlasanJPBDOnline.class);
	
	private IHtp iHTP = null;
	private ekptg.model.utils.emel.IEmel emelSemak = null;
	private IUserPegawai iUser = null;
	
	//VECTOR
	Vector listDetailKJP = null;
	
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
	String ID_NEGERI = "";
	
	String LAMPIRAN_IDLAMPIRAN = "";
	
	String idKementerian = "";
	String idAgensi = "";
	
	String userName = "", userRole = "", userID = "";
	
	Boolean haveINTData = false;
	
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{	
		String vm = "";

        HttpSession session = this.request.getSession();
        FrmModelMyInfoBorangLampiranA1 modelBorang = new FrmModelMyInfoBorangLampiranA1();
        FrmUlasanJPBDOnlineData logic = new FrmUlasanJPBDOnlineData();
        
        
        vm = "app/ppt/frmSenaraiUlasanJPBDOnline.jsp";
      
        
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        userID = (String) session.getAttribute("_ekptg_user_id");
        
        Boolean isJPBDUser = isJPBDUser(userRole);
        Boolean isPegawaiJPBD = logic.isPegawaiJPBD(userID);
        
        String command = getParam("command");
        
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();
    	Vector vData = new Vector();
        
        
        context.remove("haveINTData");
        context.remove("saveBorangLampiranA1");
        context.remove("sendBorangLampiranA1");
        context.remove("deleteBorangLampiranA1");
        
        String action = getParam("action2");
        
        
        String READONLY_JPBD = " readonly=\"readonly\" ", DISABLE_JPBD = " disabled=\"disabled\" ";
        String READONLY_JKPTG = " readonly=\"readonly\" ", DISABLE_JKPTG = " disabled=\"disabled\" ";
        
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        userID = (String) session.getAttribute("_ekptg_user_id");
        
       // Boolean isJPBDUser = isJPBDUser(userRole);
        Boolean Page_Carian = false;
        Boolean saveBorangLampiranA1 = false, sendBorangLampiranA1 = false, deleteBorangLampiranA1 = false, verifyBorangLampiranA1 = false;
       // Boolean isPegawaiJPBD = false;
        
        isPegawaiJPBD = logic.isPegawaiJPBD(userID);
                
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
    	
    	listDetailKJP = logic.getIdNegeriKJPByUserId(userID);
		if (!listDetailKJP.isEmpty() && listDetailKJP.size() > 0) {
			Hashtable hashRayuanDB = (Hashtable) listDetailKJP.get(0);
			ID_NEGERI = hashRayuanDB.get("ID_NEGERI").toString();
			idKementerian = hashRayuanDB.get("idKementerian").toString();
			idAgensi = hashRayuanDB.get("idAgensi").toString();
			//myLog.info("JAWATAN="+userJawatan);
			myLogger.info("IDKEMENTERIAN="+hashRayuanDB.get("idKementerian").toString());

		}
		
		this.context.put("ID_NEGERI", ID_NEGERI);
		this.context.put("idKementerian", idKementerian);
		this.context.put("idAgensi", idAgensi);
		myLogger.info("ID_NEGERI======="+ID_NEGERI);
    	
    	Boolean doUpload = false, doDeleteFile = false, deleteFile = false;
    	
    	if ("viewFullPermohonan".equalsIgnoreCase(command)) {
    		String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			vList = modelBorang.viewFullPermohonan(ID_PERMOHONAN);
    			context.put("selectedFullPermohonan", ID_PERMOHONAN);
        		context.put("viewFullPermohonan", "true");
    			context.put("ListFullPermohonan", vList);
    		}
    	}
    	else if ("searchBorangLampiranA1".equalsIgnoreCase(action)) {
        	vm = "app/ppt/frmSenaraiUlasanJPBDOnline.jsp";
        	vList = logic.searchPermohonan(Carian_NoFail, Carian_NoPermohonan, Carian_NoLot, Carian_NoSyit);
        	setupPage(session, action, vList);
        	context.put("ListPermohonan", vList);
        	Page_Carian = true;
        } else if ("viewBorangLampiranA1".equalsIgnoreCase(action)) {
        	myLogger.info("baca viewBorangLampiranA1===1 "+ID_PERMOHONAN);
    		// initial VM value
        	vm = "app/ppt/frmMaklumatUlasanJPBDOnline.jsp";
    		
        	if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			vData = logic.viewMaklumatPermohonan(ID_PERMOHONAN);
    			if (!vData.isEmpty()) {
    	        	vm = "app/ppt/frmMaklumatUlasanJPBDOnline.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    		}
        } else if ("sendBorangLampiranA1".equalsIgnoreCase(action)) {
    		// initial VM value
        	vm = "app/ppt/frmMaklumatUlasanJPBDOnline.jsp";
        	myLogger.info("baca sendBorangLampiranA1");
    		
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
				saveBorangLampiranA1 = logic.saveBorangLampiranA1(ID_PERMOHONAN, userID, isJPBDUser, h);
				sendBorangLampiranA1 = logic.sendBorangLampiranA1(ID_PERMOHONAN, isJPBDUser);
    			vData = logic.viewMaklumatPermohonan(ID_PERMOHONAN);
    			if (!vData.isEmpty()) {
    	        	vm = "app/ppt/frmMaklumatUlasanJPBDOnline.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    		}
    		Hashtable hUser = getIUser().getPengguna(userID);
			//idKementerian =  String.valueOf(hUser.get("idKementerian"));
			Hashtable hashHeader = (Hashtable) logic.viewMaklumatPermohonan(ID_PERMOHONAN).get(0);
			Hashtable hashRayuanDB = (Hashtable) listDetailKJP.get(0);
			//namaPemohon = hashRayuanDB.get("namaPemohon").toString();
    		
    		EmailConfig ec = new EmailConfig();

			
			String emelSubjek = ec.tajukSemakanInt + "Penerima Tawaran";
			String kandungan = "";
			
			//kandungan = getEmelSemak().setEmailSign(String.valueOf(hashHeader.get("noFail")));
				
			kandungan = getEmelSemak().setEmailSign(String.valueOf(hashHeader.get("noFail")),
					String.valueOf(hashHeader.get("tajukFail")), String.valueOf(hashRayuanDB.get("namaPemohon")));

			if (!getEmelSemak().checkEmail(userID).equals(""))
				getIHTP().getErrorHTML("[ONLINE-PHP PELEPASAN] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");
			
			ec.sendByRole(getEmelSemak().checkEmail(userID), "(PPT)PenolongPengarahUnit",
					String.valueOf(String.valueOf(hashRayuanDB.get("ID_NEGERI"))), emelSubjek, kandungan);
			
			
        } else if ("saveUlasanJPBD".equalsIgnoreCase(action)) {
    		// initial VM value
        	myLogger.info("baca saveUlasanJPBD===1"+ID_PERMOHONAN);
        	vm = "app/ppt/frmMaklumatUlasanJPBDOnline.jsp";
    		
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
				saveBorangLampiranA1 = logic.saveBorangLampiranA1(ID_PERMOHONAN, userID, isJPBDUser, h);
				myLogger.info("ULASAN JPBD userID==="+userID+"===idJPBDUser==="+isJPBDUser);
    			vData = logic.viewMaklumatPermohonan(ID_PERMOHONAN);
    			if (!vData.isEmpty()) {
    	        	vm = "app/ppt/frmMaklumatUlasanJPBDOnline.jsp";
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
				saveBorangLampiranA1 = logic.saveBorangLampiranA1(ID_PERMOHONAN, userID, isJPBDUser, h);
				verifyBorangLampiranA1 = logic.verifyBorangLampiranA1(ID_PERMOHONAN);
    			vData = logic.viewMaklumatPermohonan(ID_PERMOHONAN);
    			if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    		}
        } else if ("deleteBorangLampiranA1".equalsIgnoreCase(action)) {
    		// initial VM value
        	vm = "app/ppt/frmMaklumatUlasanJPBDOnline.jsp";
    		
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				deleteBorangLampiranA1 = logic.deleteBorangLampiranA1(ID_PERMOHONAN);
				if (deleteBorangLampiranA1) {
					vm = "app/ppt/frmSenaraiUlasanJPBDOnline.jsp";
				}
    		}
        } else if ("printSuratIringanJKPTG".equalsIgnoreCase(action)) {
        	vm = "app/integrasi/frmBorangLampiranA1SuratIringanKeJKPTG.jsp";

        	if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			vData = logic.viewMaklumatPermohonan(ID_PERMOHONAN);
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
			    		doUpload = logic.doUpload(objItem, ID_PERMOHONAN);
			    	}
			    }
			    vData = logic.viewMaklumatPermohonan(ID_PERMOHONAN);
    			if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmBorangLampiranA1.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
        	}
        } else if ("deleteFile".equalsIgnoreCase(action)) {
        	vm = "app/ppt/frmMaklumatUlasanJPBDOnline.jsp";
        	
        	if (!"".equalsIgnoreCase(LAMPIRAN_IDLAMPIRAN)) {
        		doDeleteFile = true;
        		deleteFile = logic.deleteFile(LAMPIRAN_IDLAMPIRAN);
        	}
		    vData = logic.viewMaklumatPermohonan(ID_PERMOHONAN);
			if (!vData.isEmpty()) {
	        	vm = "app/ppt/frmMaklumatUlasanJPBDOnline.jsp";
				h = (Hashtable) vData.get(0);
				populatePage(h);
			}
        } else {
        	vm = "app/ppt/frmSenaraiUlasanJPBDOnline.jsp";
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
        	vList = logic.ListLampiran(ID_PERMOHONAN);
        	context.put("ListLampiran", vList);
        	
        	STATUS_PROSES = logic.getStatusPermohonan(ID_PERMOHONAN);
        	
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
	    	context.put("ID_PERMOHONAN", ID_PERMOHONAN);
	    	context.put("ID_NEGERI", ID_NEGERI);
        }
        
        vList = modelBorang.searchBorangLampiranA1(false);
        context.put("ListBorangLampiranA1", vList);
        vList = modelBorang.searchBorangLampiranA1(true);
        context.put("ListBorangLampiranA1Selesai", vList);
        vList = modelBorang.searchBorangLampiranA1TungguPengesahan();
        context.put("ListBorangLampiranA1Pengesahan", vList);
	
        context.put("isJPBDUser", isJPBDUser);
        context.put("isPegawaiJPBD", isPegawaiJPBD);
        
        context.put("ID_PERMOHONAN", ID_PERMOHONAN);
        context.put("action2", action);
        
        myLogger.info("vm====="+vm);

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
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	private IEmel getEmelSemak(){
		if(emelSemak == null)
			emelSemak = new HTPEmelJRPBean();
		return emelSemak;
	}
	private IUserPegawai getIUser(){
		if(iUser==null){
			iUser = new UserKJPBean();
		}
		return iUser;
			
	}
}