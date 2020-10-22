package ekptg.view.ppk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.util.DateUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.ppk.FrmBorangPSek17OnlineData;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8Data;
import ekptg.model.ppk.FrmPrmhnnSek8Data;
import ekptg.model.ppk.FrmPrmhnnSek8SecaraOnlineData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiHTATHData;
import ekptg.model.ppk.FrmPrmhnnStatusPengunaOnlineData;
import ekptg.model.ppk.FrmTukaranStatus;
import ekptg.model.ppk.online.IStatusPermohonan;
import ekptg.model.ppk.online.StatusPermohonanFacade;
import ekptg.model.ppk.util.LampiranBean;

public class FrmPrmhnnBantahanOnline extends AjaxBasedModule {
	static Logger myLogger = Logger
			.getLogger(FrmPrmhnnBantahanOnline.class);

	FrmPrmhnnSek8SecaraOnlineData logiconline = new FrmPrmhnnSek8SecaraOnlineData();
	FrmBorangPSek17OnlineData logic = new FrmBorangPSek17OnlineData();
	FrmPrmhnnSek8Data logic3 = new FrmPrmhnnSek8Data();
	FrmTukaranStatus model = new FrmTukaranStatus();

	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String usid = "";
		usid = (String) session.getAttribute("_ekptg_user_id");
		String vm = "app/ppk/frmPrmhnnBantahanOnline.jsp";
		// String submit = getParam("hitButt");
		String mode = getParam("mode");
		String submit = getParam("command"); // First Level - default by AjaxBasedModule Call
		String action = getParam("action"); // Second Level

		// System.out.println("SUBMIT :: "+submit);
		// System.out.println("MODE :: "+mode);

		String disability1 = "";
		String disability2 = "";
		String readability1 = "";
		String readability2 = "";
		this.context.put("Util", new lebah.util.Util()); 
		Vector senaraiFail = new Vector();
		Vector senaraiBantah = new Vector();
		
		String USER_LOGIN_SYSTEM = (String)session.getAttribute("_portal_login");

		this.context.put("skrin_deraf", "");
		this.context.put("txtNoFailSub","");

		// this.context.put("skrin_online_popup","");
		myLogger.info("submit="+submit);
		
		if ("cariMainSub".equals(submit)){
			
    		String txtNoFailSub = getParam("txtNoFailSub"); 
    		String txtIcPemohon = getParam("txtIcPemohon");
    		String txtNamaSimati = getParam("txtNamaSimati"); 
    		String txtNamaPemohon = getParam("txtNamaPemohon");
    		context.put("txtNoFailSub", txtNoFailSub.trim());
    		context.put("txtIcPemohon",txtIcPemohon.trim());
    		context.put("txtNamaSimati",txtNamaSimati.trim());
    		context.put("txtNamaPemohon",txtNamaPemohon.trim());
    		context.put("list_fail",model.search_bantahan(txtNoFailSub.trim(),usid,txtIcPemohon,txtNamaSimati,txtNamaPemohon));
    		context.put("view_list_fail","yes"); 
    		
  
			vm = "app/ppk/frmPrmhnnBantahanOnline.jsp";
    
    	}

		if ("check_kp".equals(submit)) {
			String NoKPBaru = getParam("txtNoKPBaru1a")
					+ getParam("txtNoKPBaru2a") + getParam("txtNoKPBaru3a");
			String NoKPLama = getParam("txtNoKPLamaa");
			String NoKPLain = getParam("txtNoKPLaina");

			myLogger.info("test 1111");
			// check samada data pemohon wujud atau tidak
			int getResultChkId = logiconline.checkPemohon(NoKPBaru, NoKPLama,
					NoKPLain);
			myLogger.info("getResultChkId :: " + getResultChkId);
			if (getResultChkId == 0) {
				String idAlert = "1";
				this.context.put("IdAlert", idAlert);
			} else {
//				senaraiFail = FrmPrmhnnStatusPengunaOnlineData
//						.getSenaraiPermohonanBantahan("", (String) session
//								.getAttribute("_ekptg_user_id"),
//								(String) session.getAttribute("_portal_role"),
//								getParam("kppemohon"), getParam("kpsimati"), USER_LOGIN_SYSTEM, 
//								"no");
				senaraiBantah = FrmPrmhnnStatusPengunaOnlineData.getSenaraiBantahan("", (String) session
						.getAttribute("_ekptg_user_id"),
						(String) session.getAttribute("_portal_role"),
						getParam("kppemohon"), getParam("kpsimati"), USER_LOGIN_SYSTEM, 
						"no");
				

				// this.context.put("senaraitugasan", senaraiFail);
				this.context.put("senaraibantahan", senaraiBantah);
				myLogger.info("lalu sini A");
				vm = "app/ppk/frmPrmhnnBantahanOnline.jsp";
			}
		} 
		else if ("doChanges".equals(submit)) {
			
//			senaraiFail = FrmPrmhnnStatusPengunaOnlineData
//					.getSenaraiPermohonanBantahan("", (String) session
//							.getAttribute("_ekptg_user_id"),
//							(String) session.getAttribute("_portal_role"),
//							getParam("kppemohon"), getParam("kpsimati"), USER_LOGIN_SYSTEM, 
//							"no");
			senaraiBantah = FrmPrmhnnStatusPengunaOnlineData.getSenaraiBantahan("", (String) session
					.getAttribute("_ekptg_user_id"),
					(String) session.getAttribute("_portal_role"),
					getParam("kppemohon"), getParam("kpsimati"), USER_LOGIN_SYSTEM, 
					"no");

			this.context.put("senaraibantahan", senaraiBantah);
			vm = "app/ppk/frmPrmhnnBantahanOnline.jsp";
			myLogger.info("lalu sini B");
			setupPage(session, action, senaraiBantah);
		}
		
		// syafiqah add
		else if("skrinBantahNow".equals(submit)) {
			
			String idPermohonan = getParam("idPermohonan");
			String idFail = getParam("idFail");
			String noFail = getParam("noFail");
			String nama_simati = getParam("nama_simati");
			String ic_simati = getParam("icSimati");
			String id_bicara = getParam("idBicara");
			
			LampiranBean lBean = new LampiranBean();
			
			senaraiBantah = FrmPrmhnnStatusPengunaOnlineData.getSenaraiBantahan("", (String) session
					.getAttribute("_ekptg_user_id"),
					(String) session.getAttribute("_portal_role"),
					getParam("kppemohon"), getParam("kpsimati"), USER_LOGIN_SYSTEM, 
					"no");
			
			// maklumat pemohon
			Vector<Hashtable<String,String>> vec = logic3.setMaklumatPemohon(usid);
			Hashtable<String,String> hash = vec.get(0);
			
			// MAKLUMAT 
			this.context.put("pemohon", hash);
			this.context.put("nowpast", "now");
			myLogger.info("masuk skrin bantah NOW");
			
			if("getSimati".equals(mode)) {
				myLogger.info("lalu sini a");
				// Simati
				this.context.put("idfail", getParam("id_fail"));
				//this.context.put("nofail", getParam("nama_fail"));
				this.context.put("idPermohonan", getParam("id_permohonan"));
				this.context.put("id_bicara", getParam("id_bicara"));
				this.context.put("nofail", getParam("nama_fail"));
				this.context.put("nama_simati", getParam("nama_simati"));
				this.context.put("ic_simati", getParam("ic_simati"));
				this.context.put("supportDoc", lBean.getLampiranSimatiPapar(getParam("id_permohonan"), "99204"));
			} else {
				myLogger.info("lalu sini b");
				this.context.put("idfail", idFail);
				this.context.put("idPermohonan", idPermohonan);
				this.context.put("id_bicara", id_bicara);
				this.context.put("nofail", noFail);
				this.context.put("nama_simati", nama_simati);
				this.context.put("ic_simati", ic_simati);
				this.context.put("supportDoc", lBean.getLampiranSimatiPapar(idPermohonan, "99204"));
			}
			
			
			vm = "app/ppk/frmSkrinBantahanOnline.jsp";
			setupPage(session, action, senaraiBantah);
			
			this.context.put("show_simpan_button", "yes");
			this.context.put("show_confirm_button", "");
			
			this.context.put("senaraibantahan", senaraiBantah);
			
			// Disclaimer
			this.context.put("nameD", getParam("txtNama"));
			this.context.put("no_kpD", getParam("txtNoKPLamaPemohon"));
			
			// Pembantah
			this.context.put("nama", getParam("txtNama"));
			this.context.put("ic_no", getParam("txtNoKPLamaPemohon"));
			this.context.put("alamat1", getParam("txtAlamat1"));
			this.context.put("alamat2", getParam("txtAlamat2"));
			this.context.put("alamat3", getParam("txtAlamat3"));
			this.context.put("poskod", getParam("txtPoskod"));
			this.context.put("bandar", getParam("txtBandar"));
			this.context.put("negeri", getParam("txtNegeri"));
			this.context.put("no_tel", getParam("txtNoTel"));
			this.context.put("emel", getParam("txtEmel"));
			this.context.put("catatan", getParam("txtCatatan"));
			//this.context.put("supportDoc", lBean.getLampiranSimatiPapar(idPermohonan, "99204"));
		}
		else if("skrinBantahPast".equals(submit)) {
			String idFail = getParam("idFail");
			String noFail = getParam("nofail");
			String ic_simati = getParam("icSimati");
			
			senaraiBantah = FrmPrmhnnStatusPengunaOnlineData.getSenaraiBantahan("", (String) session
					.getAttribute("_ekptg_user_id"),
					(String) session.getAttribute("_portal_role"),
					getParam("kppemohon"), getParam("kpsimati"), USER_LOGIN_SYSTEM, 
					"no");
			
			// maklumat pemohon
			Vector<Hashtable<String,String>> vec = logic3.setMaklumatPemohon(usid);
			Hashtable<String,String> hash = vec.get(0);
			this.context.put("pemohon", hash);
			
			this.context.put("nowpast", "past");
			myLogger.info("masuk skrin bantah PAST");
			
			vm = "app/ppk/frmSkrinBantahanOnline.jsp";
			setupPage(session, action, senaraiBantah);
			
			this.context.put("show_simpan_button", "");
			this.context.put("show_confirm_button", "yes");
			this.context.put("dah_simpan", "");
			this.context.put("idfail", idFail);
			this.context.put("nofail", noFail);
			this.context.put("ic_simati", ic_simati);
			this.context.put("senaraibantahan", senaraiBantah);
		}
		else if("paparDcl".equals(submit)) {
			String name = getParam("name");
			String no_kp = getParam("no_kp");
			
			vm = "app/ppk/frmSkrinBantahanOnline.jsp";
			setupPage(session, action, senaraiBantah);
			
			this.context.put("name", name);
			this.context.put("no_kp", no_kp);
			this.context.put("show_simpan_button", "yes");
			this.context.put("show_confirm_button", "");
			// this.context.put("show_kembali_button", "yes");
			this.context.put("dah_simpan", "yes");
		}
		else if("hantarBantahan".equals(submit)) {
			myLogger.info("Step 1 SYAFIQAH");
			
			String xxxxx = getParam("docSokongan");
			addBantahan(session);
			this.context.put("view_list_fail",""); 
		}
		
		// syafiqah add ends
		
		
		System.out.println("vm FrmPrmhnnBantahanOnline >>> "+vm);
		
		/*String login = (String) session.getAttribute("_portal_login");
		Hashtable get_userID = null;
		get_userID = (Hashtable) get_id(login);
		String IDpemohon = (String) get_userID.get("NO_KP_BARU");*/
		
		//Vector icPemohon = logiconline.getICpemohon((String) session.getAttribute("_ekptg_user_id"));

//		senaraiFail = FrmPrmhnnStatusPengunaOnlineData.getSenaraiPermohonanBantahan("",
//				(String) session.getAttribute("_ekptg_user_id"),
//				(String) session.getAttribute("_portal_role"),
//				getParam("kppemohon"), getParam("kpsimati"), USER_LOGIN_SYSTEM, "no");
		
		senaraiBantah = FrmPrmhnnStatusPengunaOnlineData.getSenaraiBantahan("", (String) session
				.getAttribute("_ekptg_user_id"),
				(String) session.getAttribute("_portal_role"),
				getParam("kppemohon"), getParam("kpsimati"), USER_LOGIN_SYSTEM, 
				"no");
		
		System.out.println("USER_LOGIN_SYSTEM >>> "+USER_LOGIN_SYSTEM);
		
		this.context.put("senaraibantahan", senaraiBantah);
		setupPage(session, action, senaraiBantah);
		// this.context.put("view_list_fail",""); 

		context.put("IDpemohon", USER_LOGIN_SYSTEM);
		this.context.put("kppemohon", getParam("kppemohon"));
		this.context.put("kpsimati", getParam("kpsimati"));

		Template template = this.engine.getTemplate(vm);
		myLogger.debug("vm:" + vm);
		System.out.println("vm :" + vm);
		return vm;

	}

	private Hashtable get_id(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	

	public void setupPage(HttpSession session, String action, Vector senaraiBantah) {
		try {

			this.context.put("totalRecords", senaraiBantah.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null
					|| this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10
						: getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage");
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

			Paging paging = new Paging(session, senaraiBantah, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("senaraitugasan", paging.getPage(page));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
	}
	
	//syafiqah add
	private void addBantahan(HttpSession session) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		
		String idP = getParam("id_pemohon");
		String idFail = getParam("id_fail");
		String noFail = getParam("nama_fail");
		String namaP = getParam("txtNama");
		String myidP = getParam("txtNoKPLamaPemohon");
		String alamat1 = getParam("txtAlamat1");
		String alamat2 = getParam("txtAlamat2");
		String alamat3 = getParam("txtAlamat3");
		String poskod = getParam("txtPoskod");
		String bandar = getParam("txtBandar");
		String negeri = getParam("txtNegeri");
		String noTel = getParam("txtNoTel");
		String emel = getParam("txtEmel");
		String catatan = getParam("txtCatatan");
		String id_perbicaraan = getParam("id_bicara");
		
		myLogger.info("Step 2 SYAFIQAH");
		
		Hashtable h = null;
		h = new Hashtable();
		
		h.put("id_pembantah", idP);
		h.put("nama_pembantah", namaP);
		h.put("alamat1", alamat1);
		h.put("alamat2", alamat2);
		h.put("alamat3", alamat3);
		h.put("poskod", poskod);
		h.put("bandar", bandar);
		h.put("negeri", negeri);
		h.put("noTel", noTel);
		h.put("emel", emel);
		h.put("sebab", catatan);
		h.put("id_fail", idFail);
		h.put("no_fail", noFail);
		h.put("no_kp_baru", myidP);
		h.put("tarikh_hantar", currentDate);
		h.put("id_perbicaraan", id_perbicaraan);
		
		logiconline.insertPermohonanBantah(h);
		
	}
	
	//syafiqah add end

	public int parseInt(String x) {
		if (x == null || "".equals(x)) {
			return -1;
		} else
			return Integer.parseInt(x);
	}

	// end of seksyen 17
}
