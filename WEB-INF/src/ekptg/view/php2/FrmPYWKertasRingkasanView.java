package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmPYWHeaderData;
import ekptg.model.php2.FrmPYWKertasRingkasanData;


public class FrmPYWKertasRingkasanView extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	FrmPYWHeaderData logicHeader = new FrmPYWHeaderData();
	FrmPYWKertasRingkasanData logic = new FrmPYWKertasRingkasanData();
	
	String userId = null;
	String userRole = null;
	String idNegeriUser = null;
	
	//@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  (String)session.getAttribute("doPost");
		if (doPost.equals("true")) {
		    postDB = true;
		}
		
		userId = (String)session.getAttribute("_ekptg_user_id");
		userRole = (String)session.getAttribute("myrole");
		idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");
	    
		//GET DEFAULT PARAM 
		String vm = "";
		String mode = getParam("mode");
		if (mode.isEmpty()){
	      	mode = "view";
	    }
		String hitButton = getParam("hitButton");
       
		//GET ID PARAM
	    String idFail = getParam("idFail");
	    String idPermohonan = getParam("idPermohonan");
	    String idStatus = getParam("idStatus");	    
	    String idKertasKerja = getParam("idKertasKerja");
	    String idSuburusan = getParam("idSuburusan");
	    String flagPermohonanDari = getParam("flagPermohonanDari");
	    String idLaporanTanah = getParam("idLaporanTanah");
             
	    //VECTOR
		Vector beanHeader= null;
		Vector beanKertasRingkasan = null;
		
		String idPegawai = getParam("socPegawai");
		if (idPegawai == null || idPegawai.trim().length() == 0) {
			idPegawai = "99999";
		}
		
		String step = getParam("step");
		
		vm = "app/php2/frmPYWKertasRingkasan.jsp"; 
		
		this.context.put("onload", "");
		
		//SEND TO MODEL
		if (postDB) {
			if ("doSeterusnya".equals(hitButton)){
	    		logic.updateStatus(idFail, idPermohonan, idSuburusan, session);
			}
			if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, idSuburusan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
			if ("simpanKemaskiniRingkasan".equals(hitButton)){
				logic.simpanKemaskiniRingkasan(idKertasKerja, getParam("txtUlasanKJP"), getParam("txtUlasanJPPH"), getParam("txtUlasanJKPTGN"),
						getParam("txtUlasanKemKewangan"), getParam("txtUlasanKemWP"), getParam("txtUlasanPTG"), getParam("txtUlasanDBKL"), getParam("txtUlasanBPH"),
						getParam("socPajakan"), getParam("socPenswastaan"), getParam("txtNamaPegawai"), getParam("txtTarikhRujukan"), session);
			}
			
			if ("gotoSemakanPPNegeri".equals(hitButton)){
	    		logic.gotoSemakanPPNegeri(idFail, idNegeriUser, session);
	    		session.removeAttribute("ID_FAIL");
				session.setAttribute("MSG", "FAIL TELAH DIHANTAR UNTUK SEMAKAN PENOLONG PENGARAH");
	    		this.context.put("onload", "gotoSenaraiFail();");
			}
			if ("gotoSemakanPNegeri".equals(hitButton)){
	    		logic.gotoSemakanPNegeri(idFail, idNegeriUser, session);
	    		session.removeAttribute("ID_FAIL");
				session.setAttribute("MSG", "FAIL TELAH DIHANTAR UNTUK SEMAKAN PENGARAH");
	    		this.context.put("onload", "gotoSenaraiFail();");
			}
			if ("gotoHantarHQ".equals(hitButton)){
	    		logic.gotoHantarHQ(idFail, idNegeriUser, idPermohonan, session);
	    		session.removeAttribute("ID_FAIL");
				session.setAttribute("MSG", "FAIL TELAH DIHANTAR KE IBUPEJABAT");
	    		this.context.put("onload", "gotoSenaraiFail();");
			}
			if ("gotoHantarTugasanPP".equals(hitButton)){
	    		logic.gotoHantarTugasanPP(idFail, idNegeriUser, session);
	    		session.removeAttribute("ID_FAIL");
				session.setAttribute("MSG", "FAIL TELAH DITUGASKAN KEPADA PENOLONG PENGARAH");
	    		this.context.put("onload", "gotoSenaraiFail();");
			}
			
			if ("doSimpanAgihanTugas".equals(hitButton)){
	    		logic.doSimpanAgihanTugas(idFail, idPegawai, getParam("txtCatatan"), idNegeriUser, session);
	    		session.removeAttribute("ID_FAIL");
				session.setAttribute("MSG", "FAIL TELAH DITUGASKAN KEPADA PEGAWAI");
	    		this.context.put("onload", "gotoSenaraiFail();");
			}
			if ("gotoSemakanPPHQ".equals(hitButton)){
	    		logic.gotoSemakanPPHQ(idFail, idNegeriUser, session);
	    		session.removeAttribute("ID_FAIL");
				session.setAttribute("MSG", "FAIL TELAH DIHANTAR UNTUK SEMAKAN PENOLONG PENGARAH");
	    		this.context.put("onload", "gotoSenaraiFail();");
			}
			if ("doMintaMaklumatTambahan".equals(hitButton)){
	    		logic.doMintaMaklumatTambahan(idFail, getParam("txtCatatan"), idNegeriUser, session);
	    		session.removeAttribute("ID_FAIL");
				session.setAttribute("MSG", "FAIL TELAH DIHANTAR KE JKPTG NEGERI UNTUK TINDAKAN SELANJUTNYA");
	    		this.context.put("onload", "gotoSenaraiFail();");
			}
			if ("doPindaanMaklumat".equals(hitButton)){
	    		logic.doPindaanMaklumat(idFail, getParam("txtCatatan"), idNegeriUser, session);
	    		session.removeAttribute("ID_FAIL");
				session.setAttribute("MSG", "FAIL TELAH DIKEMBALIKAN UNTUK TINDAKAN SELANJUTNYA");
	    		this.context.put("onload", "gotoSenaraiFail();");
			}
			if ("gotoKembaliFail".equals(hitButton)){
	    		logic.gotoKembaliFail(idFail, idNegeriUser, session);
	    		session.removeAttribute("ID_FAIL");
				session.setAttribute("MSG", "FAIL TELAH DIKEMBALIKAN KE IBUPEJABAT");
	    		this.context.put("onload", "gotoSenaraiFail();");
			}
	    }
		
		//HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, getParam("initiateFlagBuka"), session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashHeader.get("idFail");
			idPermohonan = (String) hashHeader.get("idPermohonan");
			idStatus = (String) hashHeader.get("idStatus");
			idSuburusan = (String) hashHeader.get("idSuburusan");
			flagPermohonanDari = (String) hashHeader.get("flagPermohonanDari");
		}
		
		String flagMT = logic.getFlagMT(idFail, userId);
		this.context.put("flagMT", flagMT);
		
		String flagPindaan = logic.getFlagPindaan(idFail, userId);
		this.context.put("flagPindaan", flagPindaan);
		
		idLaporanTanah = logic.getIdLaporanTanah(idPermohonan);
		this.context.put("idLaporanTanah", idLaporanTanah);
		
		// MODE VIEW
		if("view".equals(mode)){
				
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
			
			//KERTAS Ringkasan
			beanKertasRingkasan = new Vector();
			logic.setMaklumatKertasRingkasan(idPermohonan);
			beanKertasRingkasan = logic.getBeanKertasRingkasan();
			this.context.put("BeanKertasRingkasan", beanKertasRingkasan);
			
		}
		// MODE VIEW
		if("update".equals(mode)){
				
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			this.context.put("disabled", "");
			
			//KERTAS Ringkasan
			beanKertasRingkasan = new Vector();
			logic.setMaklumatKertasRingkasan(idPermohonan);
			beanKertasRingkasan = logic.getBeanKertasRingkasan();
			this.context.put("BeanKertasRingkasan", beanKertasRingkasan);
		}
		
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        
		} else if ("gotoHantarTugasanPPT".equals(step)){
			
			this.context.put("selectPegawai", HTML.SelectPYWPenolongPegawaiTanahHQ("socPegawai", Long.parseLong(idPegawai), "", ""));
			
			vm = "app/php2/frmPYWAgihanTugas.jsp";
        
		} else if ("gotoMaklumatTambahan".equals(step)){
			
			vm = "app/php2/frmMintaMaklumatTambahan.jsp";
		
		} else if ("gotoPindaanMaklumat".equals(step)){
			
			vm = "app/php2/frmPindaanMaklumat.jsp";
        }
		
	  //SET DEFAULT PARAM
	  this.context.put("mode", mode);
				
	  //SET ID PARAM
	  this.context.put("idFail", idFail);
      this.context.put("idPermohonan", idPermohonan);
	  this.context.put("idStatus", idStatus);
	  this.context.put("idKertasKerja", idKertasKerja);
	  this.context.put("idSuburusan", idSuburusan);
	  this.context.put("flagPermohonanDari", flagPermohonanDari);

	  this.context.put("step",step);
	  
	  //SET VALUE USER
	  this.context.put("userId", userId);
	  this.context.put("userRole", userRole);
	  this.context.put("idNegeriUser", idNegeriUser);
	  
	  if (!"".equals(getParam("flagFrom"))){
      	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
      }
	  
	  return vm;
	}	
}

