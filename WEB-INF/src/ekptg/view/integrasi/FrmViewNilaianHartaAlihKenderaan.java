package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.integrasi.FrmModelNilaianHartaAlihKenderaan;
import ekptg.model.integrasi.FrmModelNilaianHartaTakAlih;

@SuppressWarnings({ "serial", "unused" })
public class FrmViewNilaianHartaAlihKenderaan extends AjaxBasedModule {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	String Carian_NoFail = "";
	String Carian_NoKenderaan = "";
	String Carian_NegeriKenderaanBerada = "";
	String Carian_NamaSiMati = "";
	String Carian_NoKPSiMati = "";
	
	String ID_PERMOHONAN = "";
	String ID_HA = "";
	
	String MP_NOFAIL = "";
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
	
	String ALAMAT1_LOKASI = "";
	String ALAMAT2_LOKASI = "";
	String ALAMAT3_LOKASI = "";
	String ID_NEGERILOKASI = "";
	String ID_BANDARLOKASI = "";
	String NO_PENDAFTARAN = "";
	String JENIS_BADAN = "";
	String JENIS_BUATAN = "";
	String NAMA_MODEL = "";
	String KEUPAYAAN_ENJIN = "";
	String TAHUN_DIBUAT = "";
	String NO_ENJIN = "";
	String NO_CASIS = "";
	String JENIS_BAHANBAKAR = "";
	String ALAMAT1_HARTA = "";
	String ALAMAT2_HARTA = "";
	String ALAMAT3_HARTA = "";
	String CATATAN = "";
	String NAMA_PEGAWAI_JKPTG = "";
	String NO_TEL_PEGAWAI_JKPTG = "";
	String CAWANGAN_PEGAWAI_JKPTG = "";
	String EMAIL_ADDR_PEGAWAI_JPPH = "";
	String EMAIL_SEND_JPPH = "";
	String JPPH_NILAI_TARIKH_MOHON = "";
	String JPPH_NAMA_PEGAWAI = "";
	String JPPH_CAWANGAN_JPPH = "";
	String JPPH_CATATAN = "";
	String JPPH_TARIKH_LAWAT_PERIKSA = "";
	
	String SOC_NEGERILOKASI = "", SOC_BANDARLOKASI = "", SOC_JENISBADAN = "", SOC_BUATAN = "", SOC_BAHANBAKAR = "";
	
	String userName = "", userRole = "", userID = "";
	
	Boolean haveINTData = false, permohonanSelesai = false, isJPPHUser = false;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String doTemplate2() throws Exception{
		
		Boolean sendNilaianHAK = false, saveNilaianHAK = false, deleteNilaianHAK = false, returnNilaianHAK = false;

		String vm = "";

        HttpSession session = this.request.getSession();
        
        FrmModelNilaianHartaAlihKenderaan modelHA = new FrmModelNilaianHartaAlihKenderaan();
        
        // action
        String action2 = getParam("action2");
        String action = getParam("action");
        String mode = getParam("mode");
        
        // command: used by changeSOC
        String command = getParam("command");
        
        String READONLY_JPPH = "", READONLY_JKPTG = "";
        String DISABLE_JPPH = "", DISABLE_JKPTG = "";;
        
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        userID = (String) session.getAttribute("_ekptg_user_id");
        isJPPHUser = isJPPHUser(userRole);
        
        context.remove("saveNilaianHAK");
        context.remove("sendNilaianHAKJKPTG");
        context.remove("deleteNilaianHAK");
        context.remove("returnNilaianHAK");
        context.remove("isJPPHUser");
        context.remove("haveINTData");
        
        Boolean Page_Carian = false;

        ID_PERMOHONAN = getParam("ID_PERMOHONAN");
        ID_HA = getParam("ID_HA");
        permohonanSelesai = modelHA.isPermohonanSelesai(ID_PERMOHONAN, ID_HA);
        
        ALAMAT1_LOKASI = getParam("ALAMAT1_LOKASI");
        ALAMAT2_LOKASI = getParam("ALAMAT2_LOKASI");
        ALAMAT3_LOKASI = getParam("ALAMAT3_LOKASI");
        ID_NEGERILOKASI = getParam("ID_NEGERILOKASI");
        ID_BANDARLOKASI = getParam("ID_BANDARLOKASI");
        
    	NO_PENDAFTARAN = getParam("NO_PENDAFTARAN");
    	JENIS_BADAN = getParam("ID_JENISBADAN");
    	JENIS_BUATAN = getParam("ID_BUATAN");
    	NAMA_MODEL = getParam("NAMA_MODEL");
    	KEUPAYAAN_ENJIN = getParam("KEUPAYAAN_ENJIN");
    	TAHUN_DIBUAT = getParam("TAHUN_DIBUAT");
    	NO_ENJIN = getParam("NO_ENJIN");
    	NO_CASIS = getParam("NO_CASIS");
    	JENIS_BAHANBAKAR = getParam("ID_BAHANBAKAR");
    	CATATAN = getParam("CATATAN");
    	NAMA_PEGAWAI_JKPTG = getParam("NAMA_PEGAWAI_JKPTG");

    	JPPH_NILAI_TARIKH_MOHON = getParam("JPPH_NILAI_TARIKH_MOHON");
    	JPPH_NAMA_PEGAWAI = getParam("JPPH_NAMA_PEGAWAI");
    	JPPH_CAWANGAN_JPPH = getParam("JPPH_CAWANGAN_JPPH");
    	JPPH_CATATAN = getParam("JPPH_CATATAN");
    	JPPH_TARIKH_LAWAT_PERIKSA = getParam("JPPH_TARIKH_LAWAT_PERIKSA");
    	EMAIL_ADDR_PEGAWAI_JPPH = getParam("EMAIL_ADDR_PEGAWAI_JPPH");
    	EMAIL_SEND_JPPH = getParam("EMAIL_SEND_JPPH");
    	
    	SOC_NEGERILOKASI = getParam("ID_NEGERILOKASI");
    	SOC_BANDARLOKASI = getParam("ID_BANDARLOKASI");
    	SOC_JENISBADAN = getParam("ID_JENISBADAN");
    	SOC_BUATAN = getParam("ID_BUATAN");
    	SOC_BAHANBAKAR = getParam("ID_BAHANBAKAR");
    	
    	NO_PENDAFTARAN = NO_PENDAFTARAN.replace(" ", "");
    	NO_ENJIN = NO_ENJIN.replace(" ", "");
    	NO_CASIS = NO_CASIS.replace(" ", "");
    	
    	Vector vData = new Vector();
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();
    	
        if ("searchNilaianHAK".equalsIgnoreCase(action2)) {
        	// cater for page carian
            Carian_NoFail = getParam("Carian_NoFail");
            Carian_NoKenderaan = getParam("Carian_NoKenderaan");
            Carian_NegeriKenderaanBerada = getParam("Carian_NegeriKenderaanBerada");
            Carian_NamaSiMati = getParam("Carian_NamaSiMati");
            Carian_NoKPSiMati = getParam("Carian_NoKPSiMati");

            vm = "app/integrasi/frmJPPHNilaianHAKCarian.jsp";
        	vList = modelHA.searchHAK(Carian_NoFail, Carian_NoKenderaan, Carian_NegeriKenderaanBerada, Carian_NamaSiMati, Carian_NoKPSiMati);
        	context.put("ListPermohonan", vList);
    		setupPage(session, action, vList);
    		context.put("pagingTitle", "title");
    		Page_Carian = true;
        } else {
        	// cater for all other pages
        	if ("viewNilaianHAK".equalsIgnoreCase(action2.trim())) {
        		vm = "app/integrasi/frmJPPHNilaianHAK.jsp";
        		
        		// get maklumat permohonan
        		vData = modelHA.viewHAKMaklumatPermohonan(ID_PERMOHONAN, ID_HA);
        		if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmJPPHNilaianHAK.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
        		// get maklumat HA
    			if (!"".equalsIgnoreCase(ID_HA)) {
	    			vData = modelHA.viewNilaianHAK(ID_HA);
	    			if (!vData.isEmpty()) {
	    				h = (Hashtable) vData.get(0);
	    				populatePageHAK(h);
	    			}
    			}
    			// JPPH users cannot edit JKPTG fields and vice versa
    			if (isJPPHUser) {
    				modelHA.changeStatusPermohonanForJPPH(ID_HA);
					READONLY_JKPTG = "true";
					DISABLE_JKPTG = "true";
    			} else {
    				// JKPTG users can only edit fields if in edit mode
	    			if ("edit".equalsIgnoreCase(mode)) {
						READONLY_JKPTG = "false";
						DISABLE_JKPTG = "false";
					} else {
						READONLY_JKPTG = "true";
						DISABLE_JKPTG = "true";
					}
    			}
        	} else if ("sendNilaianHAK".equalsIgnoreCase(action2.trim())) {
        		vm = "app/integrasi/frmJPPHNilaianHAK.jsp";
        		
        		// get field values
        		getValueFromPage();
        		
        		// save and send data to JPPH
    			h = new Hashtable();
    			h.put("UNIT", modelHA.getUnitUser(userID));
				h.put("JENIS_HA", "1");	// fix 1 for kenderaan (maybe useful in future)
				h.put("ALAMAT1_LOKASI", ALAMAT1_LOKASI);
				h.put("ALAMAT2_LOKASI", ALAMAT2_LOKASI);
				h.put("ALAMAT3_LOKASI", ALAMAT3_LOKASI);
				h.put("ID_NEGERILOKASI", ID_NEGERILOKASI);
				h.put("ID_BANDARLOKASI", ID_BANDARLOKASI);
				h.put("NO_PENDAFTARAN", NO_PENDAFTARAN);
				h.put("JENIS_BADAN", JENIS_BADAN);
				h.put("JENIS_BUATAN", JENIS_BUATAN);
				h.put("NAMA_MODEL", NAMA_MODEL);
				h.put("KEUPAYAAN_ENJIN", KEUPAYAAN_ENJIN);
				h.put("TAHUN_DIBUAT", TAHUN_DIBUAT);
				h.put("NO_ENJIN", NO_ENJIN);
				h.put("NO_CASIS", NO_CASIS);
				h.put("JENIS_BAHANBAKAR", JENIS_BAHANBAKAR);
				h.put("ALAMAT1_HARTA", ALAMAT1_HARTA);
				h.put("ALAMAT2_HARTA", ALAMAT2_HARTA);
				h.put("ALAMAT3_HARTA", ALAMAT3_HARTA);
				h.put("CATATAN", CATATAN);
				h.put("NAMA_PEGAWAI_JKPTG", NAMA_PEGAWAI_JKPTG);
				h.put("NO_TEL_PEGAWAI_JKPTG", NO_TEL_PEGAWAI_JKPTG);
				h.put("CAWANGAN_PEGAWAI_JKPTG", CAWANGAN_PEGAWAI_JKPTG);
				h.put("EMAIL_ADDR_PEGAWAI_JPPH", EMAIL_ADDR_PEGAWAI_JPPH);
				h.put("EMAIL_SEND_JPPH", EMAIL_SEND_JPPH);
				h.put("JPPH_NILAI_TARIKH_MOHON", JPPH_NILAI_TARIKH_MOHON);
				h.put("JPPH_NAMA_PEGAWAI", JPPH_NAMA_PEGAWAI);
				h.put("JPPH_CAWANGAN_JPPH", JPPH_CAWANGAN_JPPH);
				h.put("JPPH_CATATAN", JPPH_CATATAN);
				h.put("JPPH_TARIKH_LAWAT_PERIKSA", JPPH_TARIKH_LAWAT_PERIKSA);
				if (!"".equalsIgnoreCase(ID_HA)) {
					saveNilaianHAK = modelHA.saveNilaianHAK(ID_PERMOHONAN, ID_HA, userID, isJPPHUser, h);
					sendNilaianHAK = modelHA.sendNilaianHAK(ID_PERMOHONAN, ID_HA, userID, isJPPHUser);
				}
				
        		// get maklumat permohonan
        		vData = modelHA.viewHAKMaklumatPermohonan(ID_PERMOHONAN, ID_HA);
        		if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmJPPHNilaianHAK.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
        		// get maklumat HA
    			if (!"".equalsIgnoreCase(ID_HA)) {
	    			vData = modelHA.viewNilaianHAK(ID_HA);
	    			if (!vData.isEmpty()) {
	    				h = (Hashtable) vData.get(0);
	    				populatePageHAK(h);
	    			}
    			}
    			
    			// once data is sent, no changes to fields can be done
    			mode = "";
				READONLY_JKPTG = "true";
				DISABLE_JKPTG = "true";
				READONLY_JPPH = "true";
				DISABLE_JPPH = "true";
        	} else if ("saveNilaianHAK".equalsIgnoreCase(action2.trim())) {
        		vm = "app/integrasi/frmJPPHNilaianHAK.jsp";
        		
        		// get field values
        		getValueFromPage();
        		
        		// save data
    			h = new Hashtable();
    			h.put("UNIT", modelHA.getUnitUser(userID));
				h.put("JENIS_HA", "1");	// fix 1 for kenderaan (maybe useful in future)
				h.put("ALAMAT1_LOKASI", ALAMAT1_LOKASI);
				h.put("ALAMAT2_LOKASI", ALAMAT2_LOKASI);
				h.put("ALAMAT3_LOKASI", ALAMAT3_LOKASI);
				h.put("ID_NEGERILOKASI", ID_NEGERILOKASI);
				h.put("ID_BANDARLOKASI", ID_BANDARLOKASI);
				h.put("NO_PENDAFTARAN", NO_PENDAFTARAN);
				h.put("JENIS_BADAN", JENIS_BADAN);
				h.put("JENIS_BUATAN", JENIS_BUATAN);
				h.put("NAMA_MODEL", NAMA_MODEL);
				h.put("KEUPAYAAN_ENJIN", KEUPAYAAN_ENJIN);
				h.put("TAHUN_DIBUAT", TAHUN_DIBUAT);
				h.put("NO_ENJIN", NO_ENJIN);
				h.put("NO_CASIS", NO_CASIS);
				h.put("JENIS_BAHANBAKAR", JENIS_BAHANBAKAR);
				h.put("ALAMAT1_HARTA", ALAMAT1_HARTA);
				h.put("ALAMAT2_HARTA", ALAMAT2_HARTA);
				h.put("ALAMAT3_HARTA", ALAMAT3_HARTA);
				h.put("CATATAN", CATATAN);
				h.put("NAMA_PEGAWAI_JKPTG", NAMA_PEGAWAI_JKPTG);
				h.put("NO_TEL_PEGAWAI_JKPTG", NO_TEL_PEGAWAI_JKPTG);
				h.put("CAWANGAN_PEGAWAI_JKPTG", CAWANGAN_PEGAWAI_JKPTG);
				h.put("EMAIL_ADDR_PEGAWAI_JPPH", EMAIL_ADDR_PEGAWAI_JPPH);
				h.put("EMAIL_SEND_JPPH", EMAIL_SEND_JPPH);
				h.put("JPPH_NILAI_TARIKH_MOHON", JPPH_NILAI_TARIKH_MOHON);
				h.put("JPPH_NAMA_PEGAWAI", JPPH_NAMA_PEGAWAI);
				h.put("JPPH_CAWANGAN_JPPH", JPPH_CAWANGAN_JPPH);
				h.put("JPPH_CATATAN", JPPH_CATATAN);
				h.put("JPPH_TARIKH_LAWAT_PERIKSA", JPPH_TARIKH_LAWAT_PERIKSA);
				if (!"".equalsIgnoreCase(ID_HA)) {
					saveNilaianHAK = modelHA.saveNilaianHAK(ID_PERMOHONAN, ID_HA, userID, isJPPHUser, h);
				}
				
        		// get maklumat permohonan
        		vData = modelHA.viewHAKMaklumatPermohonan(ID_PERMOHONAN, ID_HA);
        		if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmJPPHNilaianHAK.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
        		// get maklumat HA
    			if (!"".equalsIgnoreCase(ID_HA)) {
	    			vData = modelHA.viewNilaianHAK(ID_HA);
	    			if (!vData.isEmpty()) {
	    				h = (Hashtable) vData.get(0);
	    				populatePageHAK(h);
	    			}
    			}
    			
    			// once data is saved, no changes on JKPTG side can be done. JPPH can still edit the data
    			mode = "";
				READONLY_JKPTG = "true";
				DISABLE_JKPTG = "true";
				READONLY_JPPH = "false";
				DISABLE_JPPH = "false";
    		} else if  ("deleteNilaianHAK".equalsIgnoreCase(action2.trim())) {
        		vm = "app/integrasi/frmJPPHNilaianHAKCarian.jsp";
        		
            	if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HA)) {
            		deleteNilaianHAK = modelHA.deleteNilaianHAK(ID_HA);
            		if (deleteNilaianHAK) {
            			Page_Carian = true;
            		}
            	}
        	} else if  ("returnNilaianHAK".equalsIgnoreCase(action2.trim()) && (isJPPHUser)) {
        		vm = "app/integrasi/frmJPPHNilaianHAKCarian.jsp";
        		
            	if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HA)) {
            		returnNilaianHAK = modelHA.returnNilaianHAK(ID_HA);
            		if (returnNilaianHAK) {
            			Page_Carian = true;
            		}
            	}
        	} else {
        		// redirect to page carian as new search
        		vm = "app/integrasi/frmJPPHNilaianHAKCarian.jsp";
        		Page_Carian = true;
        	}
        } 
        
        if (Page_Carian) {
        	String SOC_DISABLE_JPPH = "";
        	if (isJPPHUser) {
        		SOC_DISABLE_JPPH = " disabled=\"disabled\" class=\"disabled\" ";
        	}
        	context.put("ListPermohonan", vList);
        	context.put("selectNegeriKenderaanBerada", HTML.SelectNegeri("Carian_NegeriKenderaanBerada", (long) 0, SOC_DISABLE_JPPH)) ;
        } else {
        	if ("".equalsIgnoreCase(command.trim())) {
        		SOC_NEGERILOKASI = ID_NEGERILOKASI;
        		SOC_BANDARLOKASI = ID_BANDARLOKASI;
        		SOC_JENISBADAN = JENIS_BADAN;
        		SOC_BUATAN = JENIS_BUATAN;
        		SOC_BAHANBAKAR = JENIS_BAHANBAKAR;
        	} else {
        		getValueFromPage();
        	}
        	if (isJPPHUser) {
        		if ("".equalsIgnoreCase(JPPH_CAWANGAN_JPPH)) {
        			JPPH_CAWANGAN_JPPH = FrmModelNilaianHartaTakAlih.getNamaPejabatJPPH(userName);
        		}
        	} else {
            	MP_UNIT = modelHA.getUnitUser(userID);
        	}
        	
        	String SOC_DISABLE_JKPTG = "";
        	if ("true".equalsIgnoreCase(DISABLE_JKPTG)) {
        		SOC_DISABLE_JKPTG = " disabled=\"disabled\" class=\"disabled\" ";
        	}
        	
	        context.put("MP_NOFAIL", MP_NOFAIL);
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

	    	context.put("ALAMAT1_LOKASI", ALAMAT1_LOKASI);
	    	context.put("ALAMAT2_LOKASI", ALAMAT2_LOKASI);
	    	context.put("ALAMAT3_LOKASI", ALAMAT3_LOKASI);
	    	context.put("selectLokasiKenderaanNegeri", HTML.SelectNegeri("ID_NEGERILOKASI", Utils.parseLong(SOC_NEGERILOKASI), SOC_DISABLE_JKPTG, " onchange=\"doChangeSOC('ID_NEGERILOKASI');\" ")) ;
	    	context.put("selectLokasiKenderaanBandar", HTML.SelectDaerahByNegeri(SOC_NEGERILOKASI, "ID_BANDARLOKASI", Utils.parseLong(SOC_BANDARLOKASI), SOC_DISABLE_JKPTG));
	    	context.put("NO_PENDAFTARAN", NO_PENDAFTARAN);
	    	context.put("selectJenisBadan", modelHA.SelectJenisBadan("JENIS_BADAN", Utils.parseLong(SOC_JENISBADAN), SOC_DISABLE_JKPTG));
	    	context.put("selectBuatan", modelHA.SelectBuatan("JENIS_BUATAN", Utils.parseLong(SOC_BUATAN), SOC_DISABLE_JKPTG));
	    	context.put("NAMA_MODEL", NAMA_MODEL);
	    	context.put("KEUPAYAAN_ENJIN", KEUPAYAAN_ENJIN);
	    	context.put("TAHUN_DIBUAT", TAHUN_DIBUAT);
	    	context.put("NO_ENJIN", NO_ENJIN);
	    	context.put("NO_CASIS", NO_CASIS);
	    	context.put("selectBahanBakar", modelHA.SelectBahanBakar("JENIS_BAHANBAKAR", Utils.parseLong(SOC_BAHANBAKAR), SOC_DISABLE_JKPTG));
	    	context.put("CATATAN", CATATAN);
	    	context.put("NAMA_PEGAWAI_JKPTG", NAMA_PEGAWAI_JKPTG);
	    	context.put("NO_TEL_PEGAWAI_JKPTG", NO_TEL_PEGAWAI_JKPTG);
	    	context.put("CAWANGAN_PEGAWAI_JKPTG", CAWANGAN_PEGAWAI_JKPTG);
	    	context.put("EMAIL_ADDR_PEGAWAI_JPPH", EMAIL_ADDR_PEGAWAI_JPPH);
	    	context.put("EMAIL_SEND_JPPH", EMAIL_SEND_JPPH);
	    	context.put("JPPH_NILAI_TARIKH_MOHON", JPPH_NILAI_TARIKH_MOHON);
	    	context.put("JPPH_NAMA_PEGAWAI", JPPH_NAMA_PEGAWAI);
	    	context.put("JPPH_CAWANGAN_JPPH", JPPH_CAWANGAN_JPPH);
	    	context.put("JPPH_CATATAN", JPPH_CATATAN);
	    	context.put("JPPH_TARIKH_LAWAT_PERIKSA", JPPH_TARIKH_LAWAT_PERIKSA);
        }
    	context.put("saveNilaianHAK", saveNilaianHAK);
    	context.put("sendNilaianHAK", sendNilaianHAK);
        context.put("deleteNilaianHAK", deleteNilaianHAK);
        context.put("returnNilaianHAK", returnNilaianHAK);
        context.put("haveINTData", haveINTData);
        context.put("permohonanSelesai", permohonanSelesai);
    	context.put("isJPPHUser", isJPPHUser);
    	context.put("READONLY_JKPTG", READONLY_JKPTG);
    	context.put("READONLY_JPPH", READONLY_JPPH);
    	context.put("DISABLE_JKPTG", DISABLE_JKPTG);
    	context.put("DISABLE_JPPH", DISABLE_JPPH);
        context.put("ID_PERMOHONAN", ID_PERMOHONAN);
        context.put("ID_HA", ID_HA);
        context.put("action2", action2);
        context.put("mode", mode);
		return vm;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private void populatePage(Hashtable h) throws Exception {
		if (!h.isEmpty()) {
			FrmModelNilaianHartaAlihKenderaan modelHAK = new FrmModelNilaianHartaAlihKenderaan();
			String TEMP_UNIT = (String) h.get("MP_UNIT");
			
			MP_NOFAIL = (String) h.get("MP_NOFAIL");
			MP_NEGERI = (String) h.get("MP_NEGERI");
			MP_DAERAH = (String) h.get("MP_DAERAH");
			if ("".equalsIgnoreCase(TEMP_UNIT)) {
				MP_UNIT = modelHAK.getUnitUser(userID);
			} else {
				MP_UNIT = (String) h.get("MP_UNIT");
			}
			
			if (!isJPPHUser) {
				MP_UNIT = modelHAK.getUnitUser(userID);
			}
			MP_TARIKHMOHON = (String) h.get("MP_TARIKHMOHON");
			MP_NAMAPEMOHON = (String) h.get("MP_NAMAPEMOHON");
			MP_NOKPPEMOHON = (String) h.get("MP_NOKPPEMOHON");
			MP_NOTELPEMOHON = (String) h.get("MP_NOTELPEMOHON");
			MP_ALAMATPEMOHON = (String) h.get("MP_ALAMATPEMOHON");
			MP_NAMASIMATI = (String) h.get("MP_NAMASIMATI");
			MP_NOKPSIMATI = (String) h.get("MP_NOKPSIMATI");
			MP_TARIKHMATI = (String) h.get("MP_TARIKHMATI");
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	private void populatePageHAK(Hashtable h) throws Exception {
		if (!h.isEmpty()) {
			FrmModelNilaianHartaTakAlih modelHTA = new FrmModelNilaianHartaTakAlih();
			
			ALAMAT1_LOKASI = (String) h.get("ALAMAT1_LOKASI");
			ALAMAT2_LOKASI = (String) h.get("ALAMAT2_LOKASI");
			ALAMAT3_LOKASI = (String) h.get("ALAMAT3_LOKASI");
			ID_NEGERILOKASI = (String) h.get("ID_NEGERILOKASI");
			ID_BANDARLOKASI = (String) h.get("ID_BANDARLOKASI");
			
			NO_PENDAFTARAN = (String) h.get("NO_PENDAFTARAN");
			JENIS_BADAN = (String) h.get("JENIS_BADAN");
			JENIS_BUATAN = (String) h.get("JENIS_BUATAN");
			NAMA_MODEL = (String) h.get("NAMA_MODEL");
			KEUPAYAAN_ENJIN = (String) h.get("KEUPAYAAN_ENJIN");
			TAHUN_DIBUAT = (String) h.get("TAHUN_DIBUAT");
			NO_ENJIN = (String) h.get("NO_ENJIN");
			NO_CASIS = (String) h.get("NO_CASIS");
			JENIS_BAHANBAKAR = (String) h.get("JENIS_BAHANBAKAR");
			CATATAN = (String) h.get("CATATAN");
			NAMA_PEGAWAI_JKPTG = (String) h.get("NAMA_PEGAWAI_JKPTG");
			NO_TEL_PEGAWAI_JKPTG = (String) h.get("NO_TEL_PEGAWAI_JKPTG");
			CAWANGAN_PEGAWAI_JKPTG = (String) h.get("CAWANGAN_PEGAWAI_JKPTG");
			EMAIL_ADDR_PEGAWAI_JPPH = (String) h.get("EMAIL_ADDR_PEGAWAI_JPPH");
			EMAIL_SEND_JPPH = (String) h.get("EMAIL_SEND_JPPH");
			JPPH_NILAI_TARIKH_MOHON = (String) h.get("JPPH_NILAI_TARIKH_MOHON");
			JPPH_NAMA_PEGAWAI = (String) h.get("JPPH_NAMA_PEGAWAI");
			JPPH_CAWANGAN_JPPH = (String) h.get("JPPH_CAWANGAN_JPPH");
			JPPH_CATATAN = (String) h.get("JPPH_CATATAN");
			JPPH_TARIKH_LAWAT_PERIKSA = (String) h.get("JPPH_TARIKH_LAWAT_PERIKSA");

			if (isJPPHUser) {
				if ("".equalsIgnoreCase(JPPH_NAMA_PEGAWAI.trim())) {
					JPPH_NAMA_PEGAWAI = userName;
				}
				if ("".equalsIgnoreCase(JPPH_CAWANGAN_JPPH.trim())) {
					JPPH_CAWANGAN_JPPH = FrmModelNilaianHartaTakAlih.getCawanganUserJPPH(userID);
				}
			} else {
				if ("".equalsIgnoreCase(NAMA_PEGAWAI_JKPTG.trim())) {
					NAMA_PEGAWAI_JKPTG = userName;
				}
				if ("".equalsIgnoreCase(NO_TEL_PEGAWAI_JKPTG.trim())) {
					NO_TEL_PEGAWAI_JKPTG = FrmModelNilaianHartaTakAlih.getNoTelefonUser(userID);
				}
				if ("".equalsIgnoreCase(CAWANGAN_PEGAWAI_JKPTG.trim())) {
					CAWANGAN_PEGAWAI_JKPTG = FrmModelNilaianHartaTakAlih.getUnitUser(userID);
				}
			}
			
			String TEMP = (String) h.get("haveINTData");
			if ("true".equalsIgnoreCase(TEMP)) {
				haveINTData = true;				
			}
		}
	}
	
	private void getValueFromPage() throws Exception {
		// run before saving to get latest values from page
		try {
	        ALAMAT1_LOKASI = getParam("ALAMAT1_LOKASI");
	        ALAMAT2_LOKASI = getParam("ALAMAT2_LOKASI");
	        ALAMAT3_LOKASI = getParam("ALAMAT3_LOKASI");
	        ID_NEGERILOKASI = getParam("ID_NEGERILOKASI");
	        ID_BANDARLOKASI = getParam("ID_BANDARLOKASI");
	        
	    	NO_PENDAFTARAN = getParam("NO_PENDAFTARAN");
	    	JENIS_BADAN = getParam("JENIS_BADAN");
	    	JENIS_BUATAN = getParam("JENIS_BUATAN");
	    	NAMA_MODEL = getParam("NAMA_MODEL");
	    	KEUPAYAAN_ENJIN = getParam("KEUPAYAAN_ENJIN");
	    	TAHUN_DIBUAT = getParam("TAHUN_DIBUAT");
	    	NO_ENJIN = getParam("NO_ENJIN");
	    	NO_CASIS = getParam("NO_CASIS");
	    	JENIS_BAHANBAKAR = getParam("JENIS_BAHANBAKAR");
	    	ALAMAT1_HARTA = getParam("ALAMAT1_HARTA");
	    	ALAMAT2_HARTA = getParam("ALAMAT2_HARTA");
	    	ALAMAT3_HARTA = getParam("ALAMAT3_HARTA");
	    	CATATAN = getParam("CATATAN");
	    	NAMA_PEGAWAI_JKPTG = getParam("NAMA_PEGAWAI_JKPTG");
	    	NO_TEL_PEGAWAI_JKPTG = getParam("NO_TEL_PEGAWAI_JKPTG");
	    	CAWANGAN_PEGAWAI_JKPTG = getParam("CAWANGAN_PEGAWAI_JKPTG");

	    	EMAIL_ADDR_PEGAWAI_JPPH = getParam("EMAIL_ADDR_PEGAWAI_JPPH");
	    	EMAIL_SEND_JPPH = getParam("EMAIL_SEND_JPPH");

	    	JPPH_NILAI_TARIKH_MOHON = getParam("JPPH_NILAI_TARIKH_MOHON");
	    	JPPH_NAMA_PEGAWAI = getParam("JPPH_NAMA_PEGAWAI");
	    	JPPH_CAWANGAN_JPPH = getParam("JPPH_CAWANGAN_JPPH");
	    	JPPH_CATATAN = getParam("JPPH_CATATAN");
	    	JPPH_TARIKH_LAWAT_PERIKSA = getParam("JPPH_TARIKH_LAWAT_PERIKSA");
	    	
	    	SOC_NEGERILOKASI = getParam("ID_NEGERILOKASI");
	    	SOC_BANDARLOKASI = getParam("ID_BANDARLOKASI");
	    	SOC_JENISBADAN = getParam("JENIS_BADAN");
	    	SOC_BUATAN = getParam("JENIS_BUATAN");
	    	SOC_BAHANBAKAR = getParam("JENIS_BAHANBAKAR");
		} catch (Exception e){
			
		}
	}

	private Boolean isJPPHUser(String userRole) throws Exception {
		Boolean returnValue = false;
		returnValue = "jpph".equalsIgnoreCase(userRole);
		return returnValue;
	}

	@SuppressWarnings("rawtypes")
	public void setupPage(HttpSession session, String action, Vector list) {
		try {
			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10 : getParamAsInteger("itemsPerPage");
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
			this.context.put("error", e.getMessage());
		}	
	}	
}