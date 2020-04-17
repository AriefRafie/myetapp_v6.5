package ekptg.view.php2;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmTKRHeaderData;
import ekptg.model.php2.FrmTKRLawatanTapakData;

public class FrmTKRLawatanTapakView extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;

	FrmTKRHeaderData logicHeader = new FrmTKRHeaderData();
	FrmTKRLawatanTapakData logic = new FrmTKRLawatanTapakData();

	// @Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String submit = getParam("command");
		String hitButton = getParam("hitButton");		
		String mode = getParam("mode");
		if (mode.isEmpty()) {
			mode = "view";
		}
		String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper)) {
			selectedTabUpper = "0";
		}
		String actionTukarguna = getParam("actionTukarguna");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String flagJenisTanah = getParam("flagJenisTanah");
		String flagReKeyin = "";

		// GET ID PARAM
		String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String idPermohonanPelepasan = getParam("idPermohonanPelepasan");
        String idHakmilikPermohonan = getParam("idHakmilikPermohonan");
        String idTanahGanti = getParam("idTanahGanti");
		String idLaporanTanah = getParam("idLaporanTanah");
		String idPegawaiLaporanTanah = getParam("idPegawaiLaporanTanah");
		String idDokumen = getParam("idDokumen");

		// VECTOR
		Vector beanHeader = null;
		Vector senaraiTanahPohon = null;
        Vector senaraiTanahGanti = null;
        Vector beanMaklumatTanah = null;
        Vector beanMaklumatLaporanTanah = null;
        Vector senaraiKehadiran = null;
        Vector beanMaklumatKehadiran = null;
        Vector senaraiImejan = null;
        Vector beanMaklumatImejan = null;
		
		// GET DROPDOWN PARAM
        String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idJawatan = getParam("socJawatan");
		if (idJawatan == null || idJawatan.trim().length() == 0){
			idJawatan = "99999";
		}
		String idNegeriPelapor = getParam("socNegeriPelapor");
		if (idNegeriPelapor == null || idNegeriPelapor.trim().length() == 0){
			idNegeriPelapor = "99999";
		}
		String idJawatanPelapor = getParam("socJawatanPelapor");
		if (idJawatanPelapor == null || idJawatanPelapor.trim().length() == 0){
			idJawatanPelapor = "99999";
		}

		String step = getParam("step");
		
		this.context.put("completed", false);
		
		// SEND TO MODEL
		if (postDB) {
			if ("simpanKemaskiniMaklumatLaporanTanah".equals(hitButton)) {
				logic.simpanKemaskiniMaklumatLaporanTanah(idLaporanTanah,
						getParam("txtTarikhLawatan"), getParam("socFlagLawatan"),
						getParam("txtTujuanLaporan"),
						getParam("txtLaporanAtasTanah"),
						getParam("txtIsuUlasan"), 
						getParam("txtCatatan"), session);
			}
			if ("simpanKemaskiniMaklumatLain".equals(hitButton)) {
				logic.simpanKemaskiniMaklumatLain(idLaporanTanah,
						getParam("txtJalanHubungan"),
						getParam("txtKawasanBerhampiran"),
						getParam("txtJarakDariBandar"), getParam("kemudahanAsasA"),
						getParam("kemudahanAsasL"), getParam("kemudahanAsasT"),
						getParam("txtKemudahanAsas"), getParam("txtKeadaanTanah"),
						getParam("txtKeadaanRupabumi"), getParam("txtUtara"),
						getParam("txtSelatan"), getParam("txtTimur"),
						getParam("txtBarat"), session);
			}
			if ("simpanKehadiran".equals(hitButton)) {
				logic.simpanKehadiran(idLaporanTanah, getParam("txtNamaPegawai"), idNegeri, idJawatan, session);
				flagReKeyin = "Y";
				idNegeri = "99999";
				idJawatan = "99999";
			}
			if ("simpanKemaskiniKehadiran".equals(hitButton)) {
				logic.simpanKemaskiniKehadiran(idPegawaiLaporanTanah, getParam("txtNamaPegawai"), idNegeri, idJawatan, session);
			}
			if ("hapusKehadiran".equals(hitButton)) {
				logic.hapusKehadiran(idPegawaiLaporanTanah, session);
			}
			if ("simpanKemaskiniPelapor".equals(hitButton)) {
				logic.simpanKemaskiniPelapor(idLaporanTanah, getParam("txtNama"), idNegeriPelapor, idJawatanPelapor, session);
			}
			if ("simpanDokumen".equals(hitButton)) {
				uploadFiles(idLaporanTanah, idPermohonan, session);
			}
			if ("simpanKemaskiniDokumen".equals(hitButton)) {
				logic.simpanKemaskiniDokumen(idDokumen, getParam("txtNamaImej"), getParam("txtCatatanImej"), session);
			}
			if ("hapusDokumen".equals(hitButton)) {
				logic.hapusDokumen(idDokumen, session);
			}
			if ("doSeterusnya".equals(hitButton)) {
				logic.updateStatus(idFail, idPermohonan, session);
			}
			if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
		}

		//HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		if (logicHeader.getBeanMaklumatPermohonan().size() != 0){
			Hashtable hashPermohonan = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashPermohonan.get("idFail");
			idPermohonan = (String) hashPermohonan.get("idPermohonan");
			idStatus = (String) hashPermohonan.get("idStatus");
		}
		
		//MAKLUMAT HAKMILIK
		logicHeader.setMaklumatHakmilik(logicHeader.getIdHakmilikPermohonanByIdFail(idFail));
		
		if ("papar".equals(actionTukarguna)) {
			
			vm = "app/php2/frmTKRMaklumatLawatanTapak.jsp";
			
			idLaporanTanah = logic.getIdLaporanTanah(idHakmilikPermohonan, idTanahGanti, flagJenisTanah);
			
			if ("0".equals(selectedTabUpper)){
				if ("P".equals(flagJenisTanah)){
					
					//MAKLUMAT TANAH
					beanMaklumatTanah = new Vector();
					beanMaklumatTanah = logicHeader.getBeanMaklumatHakmilik();
					this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
					
				} else if ("G".equals(flagJenisTanah)){
					
					//MAKLUMAT TANAH
					beanMaklumatTanah = new Vector();
					logicHeader.setMaklumatTanahGanti(idTanahGanti);
					beanMaklumatTanah = logicHeader.getBeanMaklumatTanahGanti();
					this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
				}				
			} else if ("1".equals(selectedTabUpper)){
				
				if ("view".equals(mode)){
					
					this.context.put("readonly", "readonly");
        			this.context.put("inputTextClass", "disabled");
        			this.context.put("disabled", "disabled");
        			
					//MAKLUMAT LAPORAN TANAH
					beanMaklumatLaporanTanah = new Vector();
					logic.setMaklumatLaporanTanah(idLaporanTanah);
					beanMaklumatLaporanTanah = logic.getBeanMaklumatLaporanTanah();
					this.context.put("BeanMaklumatLaporanTanah", beanMaklumatLaporanTanah);
					
				} else if ("update".equals(mode)){
					this.context.put("readonly", "");
        			this.context.put("inputTextClass", "");
        			this.context.put("disabled", "");
        			
        			//MAKLUMAT LAPORAN TANAH
					beanMaklumatLaporanTanah = new Vector();
					logic.setMaklumatLaporanTanah(idLaporanTanah);
					beanMaklumatLaporanTanah = logic.getBeanMaklumatLaporanTanah();
					this.context.put("BeanMaklumatLaporanTanah", beanMaklumatLaporanTanah);
				}				
			} else if ("2".equals(selectedTabUpper)){
				
				if ("view".equals(mode)){
					
					this.context.put("readonly", "readonly");
        			this.context.put("inputTextClass", "disabled");
        			this.context.put("disabled", "disabled");
        			
					//MAKLUMAT LAPORAN TANAH
					beanMaklumatLaporanTanah = new Vector();
					logic.setMaklumatLaporanTanah(idLaporanTanah);
					beanMaklumatLaporanTanah = logic.getBeanMaklumatLaporanTanah();
					this.context.put("BeanMaklumatLaporanTanah", beanMaklumatLaporanTanah);
					
				} else if ("update".equals(mode)){
					this.context.put("readonly", "");
        			this.context.put("inputTextClass", "");
        			this.context.put("disabled", "");
        			
        			//MAKLUMAT LAPORAN TANAH
					beanMaklumatLaporanTanah = new Vector();
					logic.setMaklumatLaporanTanah(idLaporanTanah);
					beanMaklumatLaporanTanah = logic.getBeanMaklumatLaporanTanah();
					this.context.put("BeanMaklumatLaporanTanah", beanMaklumatLaporanTanah);
				}				
			} else if ("3".equals(selectedTabUpper)){
				
				//OPEN POPUP KEHADIRAN
	        	if ("openPopupKehadiran".equals(flagPopup)){
	        		
	        		String flagLawatan = logic.getFlagLawatanByIdLaporanTanah(idLaporanTanah);
	        		String idNegeriTanah = "99999";
	        		
	        		if ("new".equals(modePopup)){
	        			
	        			this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		    			
		    			if ("".equals(flagReKeyin)){
		    				
		    				beanMaklumatKehadiran = new Vector();    			
			    			Hashtable hashMaklumatKehadiran = new Hashtable();
			    			hashMaklumatKehadiran.put("namaPegawai", getParam("txtNama"));
			    			beanMaklumatKehadiran.addElement(hashMaklumatKehadiran);
							this.context.put("BeanMaklumatKehadiran", beanMaklumatKehadiran);
							
		    			} else {
		    				
		    				beanMaklumatKehadiran = new Vector();    			
			    			Hashtable hashMaklumatKehadiran = new Hashtable();
			    			hashMaklumatKehadiran.put("namaPegawai", "");
			    			beanMaklumatKehadiran.addElement(hashMaklumatKehadiran);
							this.context.put("BeanMaklumatKehadiran", beanMaklumatKehadiran);
							
		    			}
		    			if ("1".equals(flagLawatan)){
		    				idNegeri = "16"; //PUTRAJAYA
		    			} else {
		    				
		    				if ("P".equals(flagJenisTanah)){
			        			idNegeriTanah = logic.getIdNegeriTanahPohon(idHakmilikPermohonan);
			        		} else if ("G".equals(flagJenisTanah)){
			        			idNegeriTanah = logic.getIdNegeriTanahGanti(idTanahGanti);
			        		}
		    				
		    				if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
			    				idNegeri = idNegeriTanah;
			    			}
		    			}		    			
						
						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "",""));
						this.context.put("selectJawatan", HTML.SelectJawatan("socJawatan",Long.parseLong(idJawatan), "",""));
		    			
	        		} else if ("update".equals(modePopup)){
	        			
	        			this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		    			
		    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "",""));
						this.context.put("selectJawatan", HTML.SelectJawatan("socJawatan",Long.parseLong(idJawatan), "",""));
		    			
	        		} else if ("view".equals(modePopup)){
	        			
	        			this.context.put("readonlyPopup", "readonly");
		    			this.context.put("inputTextClassPopup", "disabled");
		    			
		    			//MAKLUMAT LAPORAN TANAH
						beanMaklumatKehadiran = new Vector();
						logic.setMaklumatKehadiran(idPegawaiLaporanTanah);
						beanMaklumatKehadiran = logic.getBeanMaklumatKehadiran();
						this.context.put("BeanMaklumatKehadiran", beanMaklumatKehadiran);
						if (beanMaklumatKehadiran.size() != 0){
	    					Hashtable hashMaklumatKehadiran = (Hashtable) logic.getBeanMaklumatKehadiran().get(0);
	    					
	    					idJawatan = (String) hashMaklumatKehadiran.get("idJawatan");
	    					idNegeri = (String) hashMaklumatKehadiran.get("idNegeri");
	    				}
		    			
		    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
						this.context.put("selectJawatan", HTML.SelectJawatan("socJawatan",Long.parseLong(idJawatan), "disabled", " class=\"disabled\""));
	        		}
	        	} 
				
				//SENARAI KEHADIRAN
				senaraiKehadiran = new Vector();
				logic.setSenaraiKehadiran(idLaporanTanah);
				senaraiKehadiran = logic.getListKehadiran();
				this.context.put("SenaraiKehadiran", senaraiKehadiran);
				
			} else if ("4".equals(selectedTabUpper)){
        		
        		if ("view".equals(mode)){
        			
        			this.context.put("readonly", "readonly");
	    			this.context.put("inputTextClass", "disabled");
        			
        			//MAKLUMAT LAPORAN TANAH
					beanMaklumatLaporanTanah = new Vector();
					logic.setMaklumatLaporanTanah(idLaporanTanah);
					beanMaklumatLaporanTanah = logic.getBeanMaklumatLaporanTanah();
					this.context.put("BeanMaklumatLaporanTanah", beanMaklumatLaporanTanah);
					
					if (beanMaklumatLaporanTanah.size() != 0){
    					Hashtable hashMaklumatPelapor = (Hashtable) logic.getBeanMaklumatLaporanTanah().get(0);
    					
    					idJawatanPelapor = (String) hashMaklumatPelapor.get("idJawatan");
    					idNegeriPelapor = (String) hashMaklumatPelapor.get("idNegeri");
    				}
					
					this.context.put("selectNegeriPelapor", HTML.SelectNegeri("socNegeriPelapor",Long.parseLong(idNegeriPelapor), "disabled", " class=\"disabled\""));
					this.context.put("selectJawatanPelapor", HTML.SelectJawatan("socJawatanPelapor",Long.parseLong(idJawatanPelapor), "disabled", " class=\"disabled\""));
        			
        		} else if ("update".equals(mode)){
        			
        			this.context.put("readonly", "");
	    			this.context.put("inputTextClass", "");
	    			
	    			//MAKLUMAT LAPORAN TANAH
					beanMaklumatLaporanTanah = new Vector();
					logic.setMaklumatLaporanTanah(idLaporanTanah);
					beanMaklumatLaporanTanah = logic.getBeanMaklumatLaporanTanah();
					this.context.put("BeanMaklumatLaporanTanah", beanMaklumatLaporanTanah);
					
					String flagLawatan = logic.getFlagLawatanByIdLaporanTanah(idLaporanTanah);
	        		String idNegeriTanah = "99999";	        		
	        		
					if ("1".equals(flagLawatan) && "99999".equals(idNegeri)){
	    				idNegeriPelapor = "16"; //PUTRAJAYA
	    			} else {
	    				
	    				if ("P".equals(flagJenisTanah)){
		        			idNegeriTanah = logic.getIdNegeriTanahPohon(idHakmilikPermohonan);
		        		} else if ("G".equals(flagJenisTanah)){
		        			idNegeriTanah = logic.getIdNegeriTanahGanti(idTanahGanti);
		        		}
	    				
	    				if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
		    				idNegeriPelapor = idNegeriTanah;
		    			}
	    			}
	    			
	    			this.context.put("selectNegeriPelapor", HTML.SelectNegeri("socNegeriPelapor",Long.parseLong(idNegeriPelapor), "",""));
					this.context.put("selectJawatanPelapor", HTML.SelectJawatan("socJawatanPelapor",Long.parseLong(idJawatanPelapor), "",""));
        		}
        	} else if ("5".equals(selectedTabUpper)){
        		
        		//OPEN POPUP DOKUMEN
	        	if ("openPopupDokumen".equals(flagPopup)){
	        		
	        		if ("new".equals(modePopup)){
	        			
	        			this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		    			
		    			beanMaklumatImejan = new Vector();    			
		    			Hashtable hashMaklumatImejan = new Hashtable();
		    			hashMaklumatImejan.put("namaImej", "");
		    			hashMaklumatImejan.put("catatanImej", "");
		    			beanMaklumatImejan.addElement(hashMaklumatImejan);
						this.context.put("BeanMaklumatImejan", beanMaklumatImejan);
		    			
	        		} else if ("update".equals(modePopup)){
	        			
	        			this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		    			
		    			//MAKLUMAT DOKUMEN
						beanMaklumatImejan = new Vector();
						logic.setMaklumatImej(idDokumen);
						beanMaklumatImejan = logic.getBeanMaklumatImejan();
						this.context.put("BeanMaklumatImejan", beanMaklumatImejan);
		    			
	        		} else if ("view".equals(modePopup)){
	        			
	        			this.context.put("readonlyPopup", "readonly");
		    			this.context.put("inputTextClassPopup", "disabled");
		    			
		    			//MAKLUMAT DOKUMEN
						beanMaklumatImejan = new Vector();
						logic.setMaklumatImej(idDokumen);
						beanMaklumatImejan = logic.getBeanMaklumatImejan();
						this.context.put("BeanMaklumatImejan", beanMaklumatImejan);
	        		}
	        	} 
        		
        		//SENARAI IMEJAN
				senaraiImejan = new Vector();
				logic.setSenaraiImejan(idLaporanTanah);
				senaraiImejan = logic.getListImejan();
				this.context.put("SenaraiImejan", senaraiImejan);
        	}
			
		} else {
			
			vm = "app/php2/frmTKRLawatanTapak.jsp";
			
			//TANAH DIPOHON
			senaraiTanahPohon = new Vector();
			logic.setSenaraiTanahPohon(idPermohonan);
			senaraiTanahPohon = logic.getListTanahPohon();
			this.context.put("SenaraiTanahPohon", senaraiTanahPohon);
			
			//TANAH GANTI
			senaraiTanahGanti = new Vector();
			logic.setSenaraiTanahGanti(idPermohonan);
			senaraiTanahGanti = logic.getListTanahGanti();
			this.context.put("SenaraiTanahGanti", senaraiTanahGanti);
		}


		if ("batalPermohonan".equals(step)){
			vm = "app/php2/frmBatalPermohonan.jsp";
		}
		
		// SET DEFAULT PARAM
		this.context.put("actionTukarguna", actionTukarguna);
		this.context.put("mode", mode);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("flagJenisTanah", flagJenisTanah);

		// SET ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idStatus", idStatus);
		this.context.put("idHakmilikPermohonan", idHakmilikPermohonan);
		this.context.put("idTanahGanti", idTanahGanti);
		this.context.put("idLaporanTanah", idLaporanTanah);
		this.context.put("idPegawaiLaporanTanah", idPegawaiLaporanTanah);
		this.context.put("idDokumen", idDokumen);

		this.context.put("step",step);
		
		return vm;
	}

	// UPLOAD FILE
	private void uploadFiles(String idLaporanTanah, String idPermohonan, HttpSession session) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null)
						&& (!("".equals(item.getName())))) {
					saveData(item, idLaporanTanah, idPermohonan, session);
				}
			}
		}
	}

	private void saveData(FileItem item, String idLaporanTanah, String idPermohonan, HttpSession session) throws Exception {

		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		try {
			db = new Db();

			// TBLPHPDOKUMEN
			long idDokumen = DB.getNextID("TBLPHPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO TBLPHPDOKUMEN "
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,ID_LAPORANTANAH,FLAG_DOKUMEN,ID_PERMOHONAN) "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, getParam("namaImej"));
			ps.setString(3, getParam("catatanImej"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idLaporanTanah);
			ps.setString(9, "I");
			ps.setString(10, idPermohonan);
			ps.executeUpdate();

			con.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "INS",
					"FAIL [" + idDokumen
							+ "] DIDAFTARKAN");
			
		} finally {
			if (db != null)
				db.close();
		}
		
		this.context.put("completed", true);
	}
}
