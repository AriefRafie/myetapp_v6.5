package ekptg.view.pdt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.model.pdt.FrmDaftarAktaPindaanData;

public class PendaftaranAktaPindaan extends AjaxBasedModule{
	private static Logger myLog = Logger.getLogger(ekptg.view.pdt.PendaftaranAktaPindaan.class);
	FrmDaftarAktaPindaanData aktapinda = new FrmDaftarAktaPindaanData();
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String action = getParam("command");
		String hitbutton = getParam("hitbutton");
		String checked = getParam("checked");
		this.context.put("checked",checked);
		String idFail = getParam("socNoFail");
		String tabId = getParam("tabId");
		
		this.context.put("txtNoAkta" , "");
		this.context.put("txtNamaAkta" , "");
		this.context.put("txtNoAktaPindaan", "");
		this.context.put("txtNoAktaPindaan", "");
		this.context.put("txtNamaAktaPindaan1", "");
		this.context.put("txtTarikhKuatkuasa1", "");
		this.context.put("tag_dokumen", "");
		
		String RO_General = "";
		
		String noAkta = "";
		String namaAkta = "";
		String noAktaPindaan = "";
		String namaAktaPindaan = "";
		String tarikhKuatkuasa = "";
		String noWarta = "";
		String tarikhWarta = "";
		String catatan = "";
		
		String Cari_noAkta = "";
		String Cari_namaAkta = "";
		String Cari_noAktaPindaan = "";
		String Cari_NamaAktaPindaan = "";
		String Cari_tarikhKuatkuasa = "";
		String Cari_TarikhKuatkuasa = "";
		String Cari_tag_Dokumen = "";
		
		

		
		Vector list = new Vector();
		list.clear();
		HttpSession session = this.request.getSession();
		String current_role = (String) session.getAttribute("myrole");
		this.context.put("current_role", current_role);
		
		RO_General = "readonly=\"readonly\"";
		
		
		if (tabId.equals("")) {
			tabId = "0";
		}
		vm = "app/pdt/frmDaftarAktaPindaan.jsp";
		noAkta = getParam("txtNoAkta");
		namaAkta = getParam("txtNamaAkta");
		noAktaPindaan = getParam("txtNoAktaPindaan");
		namaAktaPindaan = getParam("txtNamaAktaPindaan");
		tarikhKuatkuasa = getParam("txtTarikhKuatkuasa");
		
		
		String idAkta = getParam("idAkta");
		myLog.debug("real action:" + action);
		if (action.equals("paparsenaraipindaan")) {
			vm = "app/pdt/frmSenaraiAktaPindaan.jsp";
			aktapinda.setPaparAktaById(idAkta);
			Hashtable hPapar = (Hashtable) aktapinda.getPaparAkta().get(0);
			this.context.put("idAkta", hPapar.get("idAkta").toString());
			this.context.put("noAkta", hPapar.get("noAkta").toString());
			this.context.put("namaAkta", hPapar.get("namaAkta").toString());	
			this.context.put("seksyen", hPapar.get("idseksyen").toString());
			this.context.put("noFail", hPapar.get("noFail").toString());
			list = aktapinda.getSenaraiAktaPindaan(idAkta);
			setupPage(session,action,list);	
			
		}else if (action.equals("paparsenaraipindaan_detail")) {
			action = "doUpdate";
			//this.context.put("readOnly","");
			vm = "app/pdt/frmKemaskiniAktaPindaan.jsp";
			String idAktaPindaan = getParam("idAktaPindaan");
			Hashtable hPapar = (Hashtable) aktapinda.getAktaPindaanDetail(idAktaPindaan);
			setAktaPindaDetail(hPapar);
//			this.context.put("txtNoAktaPindaan", hPapar.get("noAktaPindaan").toString());
//			this.context.put("txtNamaAktaPindaan", hPapar.get("namaAktaPindaan").toString());
//			this.context.put("txtTarikhKuatkuasa", hPapar.get("tarikh_kuatkuasa_pindaan").toString());
//			this.context.put("txtNoWarta", hPapar.get("no_warta"));
//			this.context.put("txtTarikhWarta", hPapar.get("tarikh_warta"));			
			this.context.put("idAktaPindaan", idAktaPindaan);
			
		} else if (action.equals("tambahAktaPindaan")) {
			resetAktaPindaDetail();
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.context.put("txtTarikhDaftar", sdf.format(now));
			action = "update";
			//this.context.put("readOnly","");
			RO_General = "";
			vm = "app/pdt/frmKemaskiniAktaPindaan.jsp";
			
		}else if("papar".equals(action)){
			// papar akta asal by id
			vm = "app/pdt/frmKemaskiniAktaPindaan.jsp";
			setHeader(idAkta);
		
			//CHECK IF AKTA PINDAAN SUDAH WUJUD
			//
			//this.context.put("action", "update");
			
			if (aktapinda.isAktaPindaanExist(idAkta)) {
				action = "doUpdate";
				//GET DATA
				Hashtable hAktaPindaan = aktapinda.getAktaPindaan(idAkta);
				this.context.put("txtNoAktaPindaan", hAktaPindaan.get("noAktaPindaan"));
				this.context.put("txtNamaAktaPindaan", hAktaPindaan.get("namaAktaPindaan"));
				
			} else {
				action = "update";
			}
			
		}
		// simpan data baru
		else if (action.equals("simpan")) {			
			String user_id = (String) session.getAttribute("_ekptg_user_id");
			
			// add new Akta pindaan
			//if (tabId.equals("0")) {
				myLog.debug("ID AKTA:"+getParam("idAkta"));
				Hashtable hAddAktaPindaan = new Hashtable();
				
				hAddAktaPindaan.put("noAktaAsal",getParam("noAktaAsal"));
				hAddAktaPindaan.put("idAkta",getParam("idAkta"));
				hAddAktaPindaan.put("idAktaPindaan",getParam("idAktaPindaan"));
				hAddAktaPindaan.put("noAktaPindaan", getParam("txtNoAktaPindaan"));
				hAddAktaPindaan.put("namaAktaPindaan", getParam("txtNamaAktaPindaan"));
				hAddAktaPindaan.put("noWartaPindaan", getParam("txtNoWarta") == null|| getParam("txtNoWarta") == "" ? "" : Integer.parseInt(getParam("txtNoWarta")));
				hAddAktaPindaan.put("tarikhKuatkuasaPindaan",getParam("txtTarikhKuatkuasa"));
				hAddAktaPindaan.put("tarikhWartaPindaan", getParam("txtTarikhWarta"));
				hAddAktaPindaan.put("tarikhPerkenaanDiraja", getParam("txtTarikhPerkenaanDiraja"));
				hAddAktaPindaan.put("tarikhPenyiaranDlmWarta", getParam("txtTarikhPenyiaranDlmWarta"));
				
				
//				if(checked.equals("sulit"))	
//					hAddAktaPindaan.put("idKeselamatan","3");
//				else
//					hAddAktaPindaan.put("idKeselamatan","1");
				
				hAddAktaPindaan.put("idFail", getParam("socNoFail") == null|| getParam("socNoFail") == "" ? "" : Integer.parseInt(getParam("socNoFail")));
				hAddAktaPindaan.put("noMemorandumMenteri", getParam("txtNoMemorandumMenteri"));
				hAddAktaPindaan.put("kptsnJemaahMenteri", getParam("txtKptsnJemaahMenteri"));
				hAddAktaPindaan.put("kptsnMajlisTanah", getParam("txtKptsnMajlisTanah"));
				hAddAktaPindaan.put("perkenaanDiraja", getParam("txtTarikhPerkenaanDiraja"));
				hAddAktaPindaan.put("catatan", getParam("txtCatatan"));
				hAddAktaPindaan.put("tarikhDaftarPindaan", getParam("txtTarikhDaftar"));
				hAddAktaPindaan.put("idMasuk",user_id);
				hAddAktaPindaan.put("tarikhMasuk", getParam("txtTarikhDaftar"));
				hAddAktaPindaan.put("tagDokumen", getParam("tag_dokumen"));
				hAddAktaPindaan.put("idTagDokumen", getParam("id_tagdokumen"));
				
				String mode = getParam("mode");
				String idAktaPindaanTemp = "";
				if ("doUpdate".equals(mode)) {
					myLog.debug("1;;;;;;;;;;;;;;;;;;;;;;;;;");
					aktapinda.addAktaPindaan(hAddAktaPindaan,mode);
					UploadFile((String)hAddAktaPindaan.get("idAktaPindaan"));
					idAktaPindaanTemp = (String)hAddAktaPindaan.get("idAktaPindaan");
					
					
					
				} else {
					myLog.debug("2");
					String idAktaPindaan = aktapinda.addAktaPindaan(hAddAktaPindaan,"simpan");
					UploadFile(idAktaPindaan);
					idAktaPindaanTemp = idAktaPindaan;

				}
				setHeader(idAkta);
				Hashtable hPapar = (Hashtable) aktapinda.getAktaPindaanDetail(idAktaPindaanTemp);
				//setAktaPindaDetail(hAddAktaPindaan);
				setAktaPindaDetail(hPapar);
				action = "view";
				RO_General = "readonly=\"readonly\"";
				
				myLog.debug("ACTIONN : "+action);
				vm = "app/pdt/frmKemaskiniAktaPindaan.jsp";
				
				
				// papar akta yang telah dipinda

			//}
		} else if ("kemaskini".equals(action)) {
			
			myLog.debug("ACTION kemaskini : "+action);
			action = "doUpdate";
			//this.context.put("readOnly","");
			
			vm = "app/pdt/frmKemaskiniAktaPindaan.jsp";
			
			
			
		} else if ("delete".equals(action)) {
			vm = "app/pdt/frmSenaraiAktaPindaan.jsp";
			String idAktaPindaan = getParam("idAktaPindaan");
			noAkta = getParam("noAkta");
			namaAkta = getParam("namaAkta");
			String noFail = getParam("noFail");
			aktapinda.Delete(idAktaPindaan);
			list = aktapinda.getSenaraiAktaPindaan(idAkta);
			Hashtable hPapar = (Hashtable) aktapinda.getPaparAkta().get(0);
			this.context.put("idAkta", hPapar.get("idAkta").toString());
			this.context.put("noAkta", hPapar.get("noAkta").toString());
			this.context.put("namaAkta", hPapar.get("namaAkta").toString());	
			this.context.put("noFail", hPapar.get("noFail").toString());
			setupPage(session,action,list);	
			
		} else if ("deleteAktaPindaanByIdAkta".equals(action)) {
			vm = "app/pdt/frmDaftarAktaPindaan.jsp";
			idAkta = getParam("idAkta");
			aktapinda.DeleteAktaPindaanByIdAkta(idAkta);
			
			noAktaPindaan = getParam("txtNoAktaPindaan");
			namaAktaPindaan = getParam("txtNamaAktaPindaan1");
			tarikhKuatkuasa = getParam("txtTarikhKuatkuasa1");
			aktapinda.setCarianPaparAktaPinda(noAkta,namaAkta, tarikhKuatkuasa,noAktaPindaan, namaAktaPindaan);
			list = aktapinda.getCarianPaparAkta();
			setupPage(session,action,list);	
		
		} else {
			// papar semua akta asal
			
			System.out.println("9");
			action = request.getParameter("action");
			noAktaPindaan = getParam("txtNoAktaPindaan");
			namaAktaPindaan = getParam("txtNamaAktaPindaan1");
			tarikhKuatkuasa = getParam("txtTarikhKuatkuasa1");
			String tag = getParam("tag_dokumen");
			
			//FrmDaftarAktaPindaanData.setCarianPaparAktaPinda(noAkta,namaAkta, tarikhKuatkuasa,namaAktaPindaan);
			//list = FrmDaftarAktaPindaanData.getCarianPaparAkta();
			list = aktapinda.getCarianPaparAktaPinda(noAkta,namaAkta, tarikhKuatkuasa,noAktaPindaan,namaAktaPindaan,tag);
			/*this.context.put("txtNoAkta", noAkta);
			this.context.put("txtNamaAkta", namaAkta);
			this.context.put("txtNamaAktaPindaan1", namaAktaPindaan);
			this.context.put("txtTarikhKuatkuasa1", tarikhKuatkuasa);
			context.put("tag_Dokumen",tag);*/
			
			
					
			this.context.put("readOnly","");
			setupPage(session,action,list);	
			
		}
		this.context.put("tabId", tabId);
		this.context.put("action", action);
		myLog.debug("action --"+action);
		//myLogger.debug(vm);
		myLog.debug("2;;;;;;;;;;;;;;;;;;;;;;;;;");
		return vm;
		
	}


	public void setContext(Hashtable h) {
		String key="";
		Object value="";
		Iterator it = h.keySet().iterator();
        while (it.hasNext()) {
            key =  (String)it.next();
            value = h.get(key);
            myLog.debug(key+"=>"+value);
            this.context.put(key,value);
       }
	}
	
	
	
	////////
	public void UploadFile(String idAkta) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		    	FileItem item = (FileItem)itr.next();
			    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			    	saveBlob(idAkta,item);
			    }
		    }
		}
	}
	
	public void saveBlob(String idAktaPinda,FileItem item) throws Exception {
		 Db db = null;
		 try {
			 db = new Db();
			 Connection con = db.getConnection();
			 con.setAutoCommit(false);
			 PreparedStatement ps = con.prepareStatement("UPDATE tblpdtaktapinda " +
			 		"SET Content=?,jenis_mime=? WHERE id_aktapinda=?");
			 ps.setBinaryStream(1,item.getInputStream(),(int)item.getSize());
			 ps.setString(2,item.getName());
			 ps.setString(3,idAktaPinda);
			 ps.executeUpdate();
			 con.commit();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
			      if (db != null) db.close();
		 }
	}
	
	public void setHeader(String idAkta) throws Exception {
		aktapinda.setPaparAktaById(idAkta);
		Hashtable hPapar = (Hashtable) aktapinda.getPaparAkta().get(0);
		this.context.put("idAkta", hPapar.get("idAkta").toString());
		this.context.put("noAkta", hPapar.get("noAkta").toString());
		this.context.put("namaAkta", hPapar.get("namaAkta").toString());	
		this.context.put("seksyen", hPapar.get("idseksyen").toString());
		this.context.put("noFail", hPapar.get("noFail").toString());
		
	}
	
	public void resetAktaPindaDetail() throws Exception {
		this.context.put("txtNoAktaPindaan","");
		this.context.put("txtNamaAktaPindaan","");
		this.context.put("txtTarikhKuatkuasa", "");
		this.context.put("txtNoWarta","");
		this.context.put("txtTarikhWarta","");
		this.context.put("txtTarikhDaftar","");
		this.context.put("txtTarikhPerkenaanDiraja","");
		this.context.put("txtTarikhPenyiaranDlmWarta", "");
		this.context.put("txtNoMemorandumMenteri","");
		this.context.put("txtKptsnJemaahMenteri","");
		this.context.put("txtKptsnMajlisTanah", "");
		this.context.put("txtCatatan","");
		
	}
	
	public void setAktaPindaDetail(Hashtable hPapar) throws Exception {
		this.context.put("idAktaPindaan",(String)hPapar.get("idAktaPindaan"));
		this.context.put("txtNoAktaPindaan", (String)hPapar.get("noAktaPindaan"));
		this.context.put("txtNamaAktaPindaan", (String)hPapar.get("namaAktaPindaan"));
		this.context.put("txtTarikhKuatkuasa", hPapar.get("tarikhKuatkuasaPindaan"));
		this.context.put("txtNoWarta", hPapar.get("noWartaPindaan"));
		this.context.put("txtTarikhWarta", hPapar.get("tarikhWartaPindaan"));
		this.context.put("txtTarikhDaftar", hPapar.get("tarikhDaftarPindaan"));
		this.context.put("txtTarikhPerkenaanDiraja", hPapar.get("tarikhPerkenaanDiraja"));
		this.context.put("txtTarikhPenyiaranDlmWarta", hPapar.get("tarikhPenyiaranDlmWarta"));
		this.context.put("txtNoMemorandumMenteri", hPapar.get("noMemorandumMenteri"));
		this.context.put("txtKptsnJemaahMenteri", hPapar.get("kptsnJemaahMenteri"));
		this.context.put("txtKptsnMajlisTanah", hPapar.get("kptsnMajlisTanah"));
		this.context.put("txtCatatan", hPapar.get("catatan"));
		this.context.put("tag_Dokumen", hPapar.get("tagging"));
		this.context.put("id_tagdokumen", hPapar.get("idTagging"));
		
	}
	
}
