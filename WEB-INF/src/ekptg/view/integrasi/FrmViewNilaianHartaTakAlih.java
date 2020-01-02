package ekptg.view.integrasi;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.integrasi.FrmModelNilaianHartaTakAlih;

@SuppressWarnings({ "serial", "unused" })
public class FrmViewNilaianHartaTakAlih extends AjaxBasedModule {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	String Carian_NoFail = "";
	String Carian_NoPermohonan = "";
	String Carian_NamaSiMati = "";
	String Carian_NoKPSiMati = "";
	
	// base variables for nilaian
	String ID_PERMOHONAN = "";
	String ID_HTA = "";
	
	// base variables for maklumat permohonan fieldset
	String MP_NOFAIL = "";
	String MP_NOPERMOHONAN = "";
	String MP_NEGERI = "";
	String MP_DAERAH = "";
	String MP_UNIT = "";
	String MP_TARIKHMOHON = "";
	String MP_NAMAPEMOHON = "";
	String MP_NOKPPEMOHON = "";
	String MP_ALAMAT1PEMOHON = "";
	String MP_ALAMAT2PEMOHON = "";
	String MP_ALAMAT3PEMOHON = "";
	String MP_ALAMATPEMOHON = "";
	String MP_NOTELPEMOHON = "";
	String MP_NAMASIMATI = "";
	String MP_NOKPSIMATI = "";
	String MP_TARIKHMATI = "";

	// base variables for nilaian
	String PERLU_NILAIAN_TARIKH_MATI = "";
	String ID_NEGERI = "";
	String ID_DAERAH = "";
	String ID_MUKIM = "";
	String SEKSYEN = "";
	String ID_JENISHM = "";
	String ID_JENISPEMBANGUNAN = "";
	String NO_HAKMILIK = "";
	String NO_PTLOT = "";
	String ID_JENISPTLOT = "";
	String NO_BANGUNAN = "";
	String NO_TINGKAT = "";
	String NO_PETAK = "";
	String NO_RSS = "";
	String BA_SIMATI = "";
	String BB_SIMATI = "";
	String ID_JENISPEGANGAN = "";
	String TARIKH_LUPUT_PAJAKAN = "";
	String TEMPOH_PAJAKAN = "";
	String ID_KATEGORITANAH = "";
	String SYARAT_NYATA = "";
	String SEKATAN_KEPENTINGAN = "";
	String LUAS_TANAH = "";
	String ID_JENISLUAS = "";
	String LUAS_PETAK = "";
	String ALAMAT1_HARTA = "";
	String ALAMAT2_HARTA = "";
	String ALAMAT3_HARTA = "";
	String ID_JENISHARTA = "";
	String CATATAN = "";
	String NAMA_PEGAWAI_JKPTG = "";
	String NO_TEL_PEGAWAI_JKPTG = "";
	String CAWANGAN_PEGAWAI_JKPTG = "";
	String NAMA_PEMILIK = "";
	String TARIKH_PERJANJIAN = "";
	String NAMA_PEMAJU = "";
	String EMAIL_ADDR_PEGAWAI_JPPH = "";
	String EMAIL_SEND_JPPH = "";
	
	String JPPH_NILAI_TARIKH_MOHON = "";
	String JPPH_NILAI_TARIKH_MATI = "";
	String JPPH_NAMA_PEGAWAI = "";
	String JPPH_CAWANGAN_JPPH = "";
	String JPPH_CATATAN = "";
	String JPPH_TARIKH_LAWAT_PERIKSA = "";

	String SOC_NEGERI = "", SOC_DAERAH = "", SOC_MUKIM = "", SOC_PEGANGAN = "";

	Boolean isJPPHUser = false, haveINTData = false, permohonanSelesai = false, displayNilaianTarikhMati = false;
	String userName = "", userRole = "", userID = "";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String doTemplate2() throws Exception{
		
		String vm = "";

        HttpSession session = this.request.getSession();
        
        FrmModelNilaianHartaTakAlih modelHTA = new FrmModelNilaianHartaTakAlih();
        
        String action2 = getParam("action2");
        String action = getParam("action");        
        String command = getParam("command2");
        String mode = getParam("mode");
        
        String JENIS_HTA = getParam("JENIS_HTA");
        if ("".equalsIgnoreCase(JENIS_HTA)) {
        	JENIS_HTA = "0";
        }
        if (!"0".equalsIgnoreCase(JENIS_HTA) && !"1".equalsIgnoreCase(JENIS_HTA)) {
        	JENIS_HTA = "0";
        }
        
        String READONLY_JPPH = "", READONLY_JKPTG = "", READONLY_PAJAKAN = "";
        String DISABLE_JKPTG = "", DISABLE_JPPH = "", DISABLE_PAJAKAN = "";
                
        Boolean saveNilaianHTA = false, sendNilaianHTA = false, deleteNilaianHTA = false, returnNilaianHTA = false;
        
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        userID = (String) session.getAttribute("_ekptg_user_id");
        isJPPHUser = isJPPHUser(userRole);
        
        Boolean Page_Carian = false, ReadOnly = false, NoData = false;
        
        ID_PERMOHONAN = getParam("ID_PERMOHONAN");
        ID_HTA = getParam("ID_HTA");

        permohonanSelesai = modelHTA.isPermohonanSelesai(ID_PERMOHONAN, ID_HTA, JENIS_HTA);

    	Vector vData = new Vector();
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();
        
        if ("searchNilaianHTA".equalsIgnoreCase(action2) || "".equalsIgnoreCase(action2) || "emptyNilaianHTA".equalsIgnoreCase(action2)) {
        	// cater for page carian
            Carian_NoFail = getParam("Carian_NoFail");
            Carian_NoPermohonan = getParam("Carian_NoPermohonan");
            Carian_NamaSiMati = getParam("Carian_NamaSiMati");
            Carian_NoKPSiMati = getParam("Carian_NoKPSiMati");

            vm = "app/integrasi/frmJPPHNilaianHTACarian.jsp";
            if ("searchNilaianHTA".equalsIgnoreCase(action2)) {
            	vList = modelHTA.searchHTA(Carian_NoFail, Carian_NoPermohonan, Carian_NamaSiMati, Carian_NoKPSiMati);
            	context.put("ListPermohonan", vList);
        		setupPage(session, action, vList);
        		context.put("pagingTitle", "title");
            }
    		Page_Carian = true;
        } else {
        	if ("viewNilaianHTA".equalsIgnoreCase(action2.trim())) {
        		vm = "app/integrasi/frmJPPHNilaianHTA.jsp";
        		
        		// get maklumat permohonan
        		vData = modelHTA.viewHTAMaklumatPermohonan(ID_PERMOHONAN, ID_HTA, JENIS_HTA);
        		if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmJPPHNilaianHTA.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePageMP(h);
    			}
        		// get maklumat HTA
    			vData = modelHTA.viewNilaianHTA(ID_PERMOHONAN, ID_HTA, JENIS_HTA);
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePageHTA(h);
    			}
    			
    			// JPPH users cannot edit JKPTG fields and vice versa
    			if (isJPPHUser) {
    				modelHTA.changeStatusPermohonanForJPPH(ID_PERMOHONAN, ID_HTA, JENIS_HTA);
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
        	} else if ("saveNilaianHTA".equalsIgnoreCase(action2.trim())) {
        		vm = "app/integrasi/frmJPPHNilaianHTA.jsp";
        		
        		// get field values
        		getValueFromPage();
        		
        		// save and send data to JPPH
    			h = new Hashtable();
    			h.put("UNIT", FrmModelNilaianHartaTakAlih.getUnitUser(userID));
    			h.put("PERLU_NILAIAN_TARIKH_MATI", PERLU_NILAIAN_TARIKH_MATI);
    			h.put("ID_NEGERI", ID_NEGERI);
    			h.put("ID_DAERAH", ID_DAERAH);
    			h.put("ID_MUKIM", ID_MUKIM);
    			h.put("SEKSYEN", SEKSYEN);
    			h.put("JENIS_HAKMILIK", ID_JENISHM);
    			h.put("NO_HAKMILIK", NO_HAKMILIK);
    			h.put("NO_PTLOT", NO_PTLOT);
    			h.put("JENIS_PTLOT", ID_JENISPTLOT);
    			h.put("NO_BANGUNAN", NO_BANGUNAN);
    			h.put("NO_TINGKAT", NO_TINGKAT);
    			h.put("NO_PETAK", NO_PETAK);
    			h.put("NO_RSS", NO_RSS);
    			h.put("BA_SIMATI", BA_SIMATI);
    			h.put("BB_SIMATI", BB_SIMATI);
    			h.put("JENIS_PEGANGAN", ID_JENISPEGANGAN);
    			h.put("TARIKH_LUPUT_PAJAKAN", TARIKH_LUPUT_PAJAKAN);
    			h.put("TEMPOH_PAJAKAN", TEMPOH_PAJAKAN);
    			h.put("KATEGORI_TANAH", ID_KATEGORITANAH);
    			h.put("SYARAT_NYATA", SYARAT_NYATA);
    			h.put("SEKATAN_KEPENTINGAN", SEKATAN_KEPENTINGAN);
    			h.put("LUAS_TANAH", LUAS_TANAH);
    			h.put("JENIS_LUAS_TANAH", ID_JENISLUAS);
    			h.put("LUAS_PETAK", LUAS_PETAK);
    			h.put("ALAMAT1_HARTA", ALAMAT1_HARTA);
    			h.put("ALAMAT2_HARTA", ALAMAT2_HARTA);
    			h.put("ALAMAT3_HARTA", ALAMAT3_HARTA);
    			h.put("JENIS_HARTA_DINILAI", ID_JENISHARTA);
    			h.put("CATATAN", CATATAN);
    			h.put("NAMA_PEMILIK", NAMA_PEMILIK);
    			h.put("TARIKH_PERJANJIAN", TARIKH_PERJANJIAN);
    			h.put("NAMA_PEMAJU", NAMA_PEMAJU);
    			h.put("NAMA_PEGAWAI_JKPTG", NAMA_PEGAWAI_JKPTG);
    			h.put("NO_TEL_PEGAWAI_JKPTG", NO_TEL_PEGAWAI_JKPTG);
    			h.put("CAWANGAN_PEGAWAI_JKPTG", CAWANGAN_PEGAWAI_JKPTG);
    			h.put("EMAIL_ADDR_PEGAWAI_JPPH", EMAIL_ADDR_PEGAWAI_JPPH);
    			h.put("EMAIL_SEND_JPPH", EMAIL_SEND_JPPH);
    			h.put("JPPH_NILAI_TARIKH_MOHON", JPPH_NILAI_TARIKH_MOHON);
    			h.put("JPPH_NILAI_TARIKH_MATI", JPPH_NILAI_TARIKH_MATI);
    			h.put("JPPH_NAMA_PEGAWAI", JPPH_NAMA_PEGAWAI);
    			h.put("JPPH_CAWANGAN_JPPH", JPPH_CAWANGAN_JPPH);
    			h.put("JPPH_CATATAN", JPPH_CATATAN);
    			h.put("JPPH_TARIKH_LAWAT_PERIKSA", JPPH_TARIKH_LAWAT_PERIKSA);
				if (!"".equalsIgnoreCase(ID_HTA)) {
					saveNilaianHTA = modelHTA.saveNilaianHTA(ID_PERMOHONAN, ID_HTA, JENIS_HTA, userID, isJPPHUser, h);
				}
				
        		// get maklumat permohonan
        		vData = modelHTA.viewHTAMaklumatPermohonan(ID_PERMOHONAN, ID_HTA, JENIS_HTA);
        		if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmJPPHNilaianHTA.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePageMP(h);
    			}
        		// get maklumat HTA
    			vData = modelHTA.viewNilaianHTA(ID_PERMOHONAN, ID_HTA, JENIS_HTA);
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePageHTA(h);
    			}
    			
    			// once data is saved, no changes on JKPTG side can be done. JPPH can still edit the data
    			mode = "";
    			action2 = "";
    			command = "";
				READONLY_JKPTG = "true";
				DISABLE_JKPTG = "true";
				READONLY_JPPH = "false";
				DISABLE_JPPH = "false";
        	} else if ("sendNilaianHTA".equalsIgnoreCase(action2.trim())) {
        		vm = "app/integrasi/frmJPPHNilaianHTA.jsp";
        		
        		// get field values
        		getValueFromPage();
        		
        		// save and send data to JPPH
    			h = new Hashtable();
    			h.put("UNIT", FrmModelNilaianHartaTakAlih.getUnitUser(userID));
    			h.put("PERLU_NILAIAN_TARIKH_MATI", PERLU_NILAIAN_TARIKH_MATI);
    			h.put("ID_NEGERI", ID_NEGERI);
    			h.put("ID_DAERAH", ID_DAERAH);
    			h.put("ID_MUKIM", ID_MUKIM);
    			h.put("SEKSYEN", SEKSYEN);
    			h.put("JENIS_HAKMILIK", ID_JENISHM);
    			h.put("NO_HAKMILIK", NO_HAKMILIK);
    			h.put("NO_PTLOT", NO_PTLOT);
    			h.put("JENIS_PTLOT", ID_JENISPTLOT);
    			h.put("NO_BANGUNAN", NO_BANGUNAN);
    			h.put("NO_TINGKAT", NO_TINGKAT);
    			h.put("NO_PETAK", NO_PETAK);
    			h.put("NO_RSS", NO_RSS);
    			h.put("BA_SIMATI", BA_SIMATI);
    			h.put("BB_SIMATI", BB_SIMATI);
    			h.put("JENIS_PEGANGAN", ID_JENISPEGANGAN);
    			h.put("TARIKH_LUPUT_PAJAKAN", TARIKH_LUPUT_PAJAKAN);
    			h.put("TEMPOH_PAJAKAN", TEMPOH_PAJAKAN);
    			h.put("KATEGORI_TANAH", ID_KATEGORITANAH);
    			h.put("SYARAT_NYATA", SYARAT_NYATA);
    			h.put("SEKATAN_KEPENTINGAN", SEKATAN_KEPENTINGAN);
    			h.put("LUAS_TANAH", LUAS_TANAH);
    			h.put("JENIS_LUAS_TANAH", ID_JENISLUAS);
    			h.put("LUAS_PETAK", LUAS_PETAK);
    			h.put("ALAMAT1_HARTA", ALAMAT1_HARTA);
    			h.put("ALAMAT2_HARTA", ALAMAT2_HARTA);
    			h.put("ALAMAT3_HARTA", ALAMAT3_HARTA);
    			h.put("JENIS_HARTA_DINILAI", ID_JENISHARTA);
    			h.put("CATATAN", CATATAN);
    			h.put("NAMA_PEMILIK", NAMA_PEMILIK);
    			h.put("TARIKH_PERJANJIAN", TARIKH_PERJANJIAN);
    			h.put("NAMA_PEMAJU", NAMA_PEMAJU);
    			h.put("NAMA_PEGAWAI_JKPTG", NAMA_PEGAWAI_JKPTG);
    			h.put("NO_TEL_PEGAWAI_JKPTG", NO_TEL_PEGAWAI_JKPTG);
    			h.put("CAWANGAN_PEGAWAI_JKPTG", CAWANGAN_PEGAWAI_JKPTG);
    			h.put("EMAIL_ADDR_PEGAWAI_JPPH", EMAIL_ADDR_PEGAWAI_JPPH);
    			h.put("EMAIL_SEND_JPPH", EMAIL_SEND_JPPH);
    			h.put("JPPH_NILAI_TARIKH_MOHON", JPPH_NILAI_TARIKH_MOHON);
    			h.put("JPPH_NILAI_TARIKH_MATI", JPPH_NILAI_TARIKH_MATI);
    			h.put("JPPH_NAMA_PEGAWAI", JPPH_NAMA_PEGAWAI);
    			h.put("JPPH_CAWANGAN_JPPH", JPPH_CAWANGAN_JPPH);
    			h.put("JPPH_CATATAN", JPPH_CATATAN);
    			h.put("JPPH_TARIKH_LAWAT_PERIKSA", JPPH_TARIKH_LAWAT_PERIKSA);
				if (!"".equalsIgnoreCase(ID_HTA)) {
					saveNilaianHTA = modelHTA.saveNilaianHTA(ID_PERMOHONAN, ID_HTA, JENIS_HTA, userID, isJPPHUser, h);
					//2017/08/28
					//sendNilaianHTA = modelHTA.sendNilaianHTA(ID_PERMOHONAN, ID_HTA, JENIS_HTA, userID, isJPPHUser,h);
				}
				
        		// get maklumat permohonan
        		vData = modelHTA.viewHTAMaklumatPermohonan(ID_PERMOHONAN, ID_HTA, JENIS_HTA);
        		if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmJPPHNilaianHTA.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePageMP(h);
    			}
        		// get maklumat HTA
    			vData = modelHTA.viewNilaianHTA(ID_PERMOHONAN, ID_HTA, JENIS_HTA);
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePageHTA(h);
    			}
    			
    			// once data is sent, no changes to fields can be done
    			mode = "";
    			action2 = "";
    			command = "";
				READONLY_JKPTG = "true";
				DISABLE_JKPTG = "true";
				READONLY_JPPH = "true";
				DISABLE_JPPH = "true";
        	} else if ("returnNilaianHTA".equalsIgnoreCase(action2.trim())) {
        		vm = "app/integrasi/frmJPPHNilaianHTACarian.jsp";
        		
            	if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HTA)) {
            		returnNilaianHTA = modelHTA.returnNilaianHTA(ID_PERMOHONAN, ID_HTA, JENIS_HTA);
            		if (returnNilaianHTA) {
            			Page_Carian = true;
            		}
            	}
        		
        	} else if ("deleteNilaianHTA".equalsIgnoreCase(action2.trim())) {
        		vm = "app/integrasi/frmJPPHNilaianHTACarian.jsp";
        		
            	if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HTA)) {
            		deleteNilaianHTA = modelHTA.deleteNilaianHTA(ID_PERMOHONAN, ID_HTA, JENIS_HTA);
            		if (deleteNilaianHTA) {
            			Page_Carian = true;
            		}
            	}        		
        	} else {
        		// redirect to page carian as new search
        		vm = "app/integrasi/frmJPPHNilaianHTACarian.jsp";
        		Page_Carian = true;
        	}
        }
    	
    	if (Page_Carian) {
        	context.put("ListPermohonan", vList);
    	} else {
        	if ("".equalsIgnoreCase(command.trim())) {
        		SOC_NEGERI = ID_NEGERI;
        		SOC_DAERAH = ID_DAERAH;
        		SOC_MUKIM = ID_MUKIM;
        		SOC_PEGANGAN = ID_JENISPEGANGAN;
        	} else {
        		getValueFromPage();
        	}
        	if (isJPPHUser) {
        		if ("".equalsIgnoreCase(JPPH_CAWANGAN_JPPH)) {
        			JPPH_CAWANGAN_JPPH = FrmModelNilaianHartaTakAlih.getNamaPejabatJPPH(userName);
        		}
        	} else {
            	MP_UNIT = FrmModelNilaianHartaTakAlih.getUnitUser(userID);
        	}
        	
        	String SOC_DISABLE_JKPTG = "";
        	if ("true".equalsIgnoreCase(DISABLE_JKPTG)) {
        		SOC_DISABLE_JKPTG = " disabled=\"disabled\" class=\"disabled\" ";
        	}
        	
        	if ("1".equalsIgnoreCase(PERLU_NILAIAN_TARIKH_MATI)) {
        		PERLU_NILAIAN_TARIKH_MATI = " checked=\"checked\" ";
        	}
        	if ("1".equalsIgnoreCase(EMAIL_SEND_JPPH)) {
        		EMAIL_SEND_JPPH = " checked=\"checked\" ";
        	}
        	
        	if ("1".equalsIgnoreCase(ID_JENISPEGANGAN.trim())) {
        		DISABLE_PAJAKAN = "true";
        		READONLY_PAJAKAN = "true";
        	}
        	
        	// changes to put comma in number
        	DecimalFormat df = new DecimalFormat();
        	DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        	dfs.setGroupingSeparator('.');
        	df.setDecimalFormatSymbols(dfs);

        	if ("".equalsIgnoreCase(JPPH_NILAI_TARIKH_MOHON.trim())) {
        		JPPH_NILAI_TARIKH_MOHON = "0";
        	}
        	if ("".equalsIgnoreCase(JPPH_NILAI_TARIKH_MATI.trim())) {
        		JPPH_NILAI_TARIKH_MATI = "0";
        	}
        	//JPPH_NILAI_TARIKH_MOHON = df.format(JPPH_NILAI_TARIKH_MOHON);
        	//JPPH_NILAI_TARIKH_MATI = df.format(JPPH_NILAI_TARIKH_MATI);

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

	    	context.put("PERLU_NILAIAN_TARIKH_MATI", PERLU_NILAIAN_TARIKH_MATI);
	    	context.put("selectNegeri", HTML.SelectNegeri("ID_NEGERI", Utils.parseLong(SOC_NEGERI), SOC_DISABLE_JKPTG, " onchange=\"doChangeSOC('changeSOCNegeri');\""));
	    	context.put("selectDaerah", HTML.SelectDaerah("ID_DAERAH", Utils.parseLong(SOC_DAERAH), SOC_DISABLE_JKPTG, " onchange=\"doChangeSOC('changeSOCDaerah');\""));
	    	context.put("selectMukim", HTML.SelectMukim("ID_MUKIM", Utils.parseLong(SOC_MUKIM), SOC_DISABLE_JKPTG));
	    	context.put("SEKSYEN", SEKSYEN);
	    	context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("ID_JENISHM", Utils.parseLong(ID_JENISHM), SOC_DISABLE_JKPTG));
	    	context.put("NO_HAKMILIK", NO_HAKMILIK);
	    	context.put("selectJenisPembangunan", modelHTA.SelectJenisPembangunan("ID_JENISPEMBANGUNAN", Utils.parseLong(ID_JENISPEMBANGUNAN), SOC_DISABLE_JKPTG));
	    	context.put("NO_PTLOT", NO_PTLOT);
	    	context.put("selectJenisPTLot", modelHTA.SelectJenisPTLot("ID_JENISPTLOT", Utils.parseLong(ID_JENISPTLOT), SOC_DISABLE_JKPTG));
	    	context.put("NO_BANGUNAN", NO_BANGUNAN);
	    	context.put("NO_TINGKAT", NO_TINGKAT);
	    	context.put("NO_PETAK", NO_PETAK);
	    	context.put("NO_RSS", NO_RSS);
	    	context.put("BA_SIMATI", BA_SIMATI);
	    	context.put("BB_SIMATI", BB_SIMATI);
	    	context.put("selectJenisPegangan", modelHTA.SelectJenisPegangan("ID_JENISPEGANGAN", Utils.parseLong(ID_JENISPEGANGAN), SOC_DISABLE_JKPTG, "onchange=\"toggleJenisPegangan();\""));
	    	context.put("selectJenisPegangan", modelHTA.SelectJenisPegangan("ID_JENISPEGANGAN", Utils.parseLong(ID_JENISPEGANGAN), SOC_DISABLE_JKPTG, "onchange=\"toggleJenisPegangan();\""));
	    	context.put("TARIKH_LUPUT_PJKN", TARIKH_LUPUT_PAJAKAN);
	    	context.put("TEMPOH_PJKN", TEMPOH_PAJAKAN);
	    	context.put("selectKategoriTanah", modelHTA.SelectKategoriTanah("ID_KATEGORITANAH", Utils.parseLong(ID_KATEGORITANAH), SOC_DISABLE_JKPTG));
	    	context.put("SYARAT_NYATA", SYARAT_NYATA);
	    	context.put("SEKATAN_KEPENTINGAN", SEKATAN_KEPENTINGAN);
	    	context.put("LUAS_TANAH", LUAS_TANAH);
	    	context.put("selectJenisLuasTanah", modelHTA.SelectJenisLuasTanah("ID_JENISLUAS", Utils.parseLong(ID_JENISLUAS), SOC_DISABLE_JKPTG));
	    	context.put("LUAS_PETAK", LUAS_PETAK);
	    	context.put("ALAMAT1_HARTA", ALAMAT1_HARTA);
	    	context.put("ALAMAT2_HARTA", ALAMAT2_HARTA);
	    	context.put("ALAMAT3_HARTA", ALAMAT3_HARTA);
	    	context.put("selectJenisHartaDinilai", modelHTA.SelectJenisHartaDinilai("ID_JENISHARTA", Utils.parseLong(ID_JENISHARTA), SOC_DISABLE_JKPTG));
	    	context.put("CATATAN", CATATAN);
	    	context.put("NAMA_PEGAWAI_JKPTG", NAMA_PEGAWAI_JKPTG);
	    	context.put("NO_TEL_PEGAWAI_JKPTG", NO_TEL_PEGAWAI_JKPTG);
	    	context.put("CAWANGAN_PEGAWAI_JKPTG", CAWANGAN_PEGAWAI_JKPTG);
	    	context.put("NAMA_PEMILIK", NAMA_PEMILIK);
	    	context.put("TARIKH_JUAL_BELI", TARIKH_PERJANJIAN);
	    	context.put("NAMA_PEMAJU", NAMA_PEMAJU);
	    	//context.put("selectEmailPegawai", modelHTA.SelectPenggunaJPPHEmail("ID_EMAILPEGAWAI", 0, SOC_NEGERI, SOC_NEGERI, SOC_NEGERI, "onchange=\"toggleJenisPegangan();\""));
	    	context.put("EMAIL_ADDR_PEGAWAI_JPPH", EMAIL_ADDR_PEGAWAI_JPPH);
	    	context.put("EMAIL_SEND_JPPH", EMAIL_SEND_JPPH);
	    	context.put("JPPH_NILAI_TARIKH_MOHON", JPPH_NILAI_TARIKH_MOHON);
	    	context.put("JPPH_NILAI_TARIKH_MATI", JPPH_NILAI_TARIKH_MATI);
	    	context.put("JPPH_NAMA_PEGAWAI", JPPH_NAMA_PEGAWAI);
	    	context.put("JPPH_CAWANGAN_JPPH", JPPH_CAWANGAN_JPPH);
	    	context.put("JPPH_CATATAN", JPPH_CATATAN);
	    	context.put("JPPH_TARIKH_LAWAT_PERIKSA", JPPH_TARIKH_LAWAT_PERIKSA);
    	}
    	//---------------------------------------------------------------

    	// ref email pn kamarina 17 nov 2011
    	// nilaian tarikh mati tak diperlukan bagi kematian selepas 1 nov 1991
    	context.put("displayNilaianTarikhMati", displayNilaianTarikhMati);
    	
    	context.put("READONLY_JKPTG", READONLY_JKPTG);
    	context.put("DISABLE_JKPTG", DISABLE_JKPTG);
    	context.put("READONLY_JPPH", READONLY_JPPH);
    	context.put("DISABLE_JPPH", DISABLE_JPPH);
    	context.put("READONLY_PAJAKAN", READONLY_PAJAKAN);
    	context.put("DISABLE_PAJAKAN", DISABLE_PAJAKAN);
        context.put("ID_PERMOHONAN", ID_PERMOHONAN);
        context.put("ID_HTA", ID_HTA);
    	context.put("isJPPHUser", isJPPHUser);
    	context.put("saveNilaianHTA", saveNilaianHTA);
    	context.put("sendNilaianHTA", sendNilaianHTA);
    	context.put("deleteNilaianHTA", deleteNilaianHTA);
    	context.put("returnNilaianHTA", returnNilaianHTA);
    	context.put("haveINTData", haveINTData);
    	context.put("permohonanSelesai", permohonanSelesai);
        context.put("action2", action2);
        context.put("mode", mode);
    	context.put("selectedTab", JENIS_HTA);
    	
		return vm;
	}
	
	private void getValueFromPage() throws Exception {
		// run before saving to get latest values from page
		try {
			PERLU_NILAIAN_TARIKH_MATI = getParam("PERLU_NILAIAN_TARIKH_MATI");
			ID_NEGERI = getParam("ID_NEGERI");
			ID_DAERAH = getParam("ID_DAERAH");
			ID_MUKIM = getParam("ID_MUKIM");
			SEKSYEN = getParam("SEKSYEN");
			ID_JENISHM = getParam("ID_JENISHM");
			NO_HAKMILIK = getParam("NO_HAKMILIK");
			NO_PTLOT = getParam("NO_PTLOT");
			ID_JENISPTLOT = getParam("ID_JENISPTLOT");
			NO_BANGUNAN = getParam("NO_BANGUNAN");
			NO_TINGKAT = getParam("NO_TINGKAT");
			NO_PETAK = getParam("NO_PETAK");
			NO_RSS = getParam("NO_RSS");
			BA_SIMATI = getParam("BA_SIMATI");
			BB_SIMATI = getParam("BB_SIMATI");
			ID_JENISPEGANGAN = getParam("ID_JENISPEGANGAN");
			TARIKH_LUPUT_PAJAKAN = getParam("TARIKH_LUPUT_PAJAKAN");
			TEMPOH_PAJAKAN = getParam("TEMPOH_PAJAKAN");
			ID_KATEGORITANAH = getParam("ID_KATEGORITANAH");
			SYARAT_NYATA = getParam("SYARAT_NYATA");
			SEKATAN_KEPENTINGAN = getParam("SEKATAN_KEPENTINGAN");
			LUAS_TANAH = getParam("LUAS_TANAH");
			ID_JENISLUAS = getParam("ID_JENISLUAS");
			LUAS_PETAK = getParam("LUAS_PETAK");
	    	ALAMAT1_HARTA = getParam("ALAMAT1_HARTA");
	    	ALAMAT2_HARTA = getParam("ALAMAT2_HARTA");
	    	ALAMAT3_HARTA = getParam("ALAMAT3_HARTA");
	    	ID_JENISHARTA = getParam("ID_JENISHARTA");
	    	CATATAN = getParam("CATATAN");
	    	NAMA_PEMILIK = getParam("NAMA_PEMILIK");
	    	TARIKH_PERJANJIAN = getParam("TARIKH_PERJANJIAN");
	    	NAMA_PEMAJU = getParam("NAMA_PEMAJU");
	    	NAMA_PEGAWAI_JKPTG = getParam("NAMA_PEGAWAI_JKPTG");
	    	NO_TEL_PEGAWAI_JKPTG = getParam("NO_TEL_PEGAWAI_JKPTG");
	    	CAWANGAN_PEGAWAI_JKPTG = getParam("CAWANGAN_PEGAWAI_JKPTG");

	    	EMAIL_ADDR_PEGAWAI_JPPH = getParam("EMAIL_ADDR_PEGAWAI_JPPH");
	    	EMAIL_SEND_JPPH = getParam("EMAIL_SEND_JPPH");

	    	JPPH_NILAI_TARIKH_MOHON = getParam("JPPH_NILAI_TARIKH_MOHON");
	    	JPPH_NILAI_TARIKH_MATI = getParam("JPPH_NILAI_TARIKH_MATI");
	    	JPPH_NAMA_PEGAWAI = getParam("JPPH_NAMA_PEGAWAI");
	    	JPPH_CAWANGAN_JPPH = getParam("JPPH_CAWANGAN_JPPH");
	    	JPPH_CATATAN = getParam("JPPH_CATATAN");
	    	JPPH_TARIKH_LAWAT_PERIKSA = getParam("JPPH_TARIKH_LAWAT_PERIKSA");
	    	
	    	SOC_NEGERI = getParam("ID_NEGERI");
	    	SOC_DAERAH = getParam("ID_DAERAH");
	    	SOC_MUKIM = getParam("ID_MUKIM");
	    	SOC_PEGANGAN = getParam("ID_JENISPEGANGAN");
		} catch (Exception e){
			
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	private void populatePageMP(Hashtable h) throws Exception {
		if (!h.isEmpty()) {
			FrmModelNilaianHartaTakAlih modelHTA = new FrmModelNilaianHartaTakAlih();
			
			String TEMP_UNIT = (String) h.get("MP_UNIT");
			
			MP_NOFAIL = (String) h.get("MP_NOFAIL");
			MP_NOPERMOHONAN = (String) h.get("MP_NOPERMOHONAN");
			MP_NEGERI = (String) h.get("MP_NEGERI");
			MP_DAERAH = (String) h.get("MP_DAERAH");
			if ("".equalsIgnoreCase(TEMP_UNIT)) {
				MP_UNIT = FrmModelNilaianHartaTakAlih.getUnitUser(userID);
			} else {
				MP_UNIT = (String) h.get("MP_UNIT");
			}
			MP_TARIKHMOHON = (String) h.get("MP_TARIKHMOHON");
			MP_NAMAPEMOHON = (String) h.get("MP_NAMAPEMOHON");
			MP_NOKPPEMOHON = (String) h.get("MP_NOKPPEMOHON");
			MP_ALAMATPEMOHON = (String) h.get("MP_ALAMATPEMOHON");
			MP_NOTELPEMOHON = (String) h.get("MP_NOTELPEMOHON");
			if ("".equalsIgnoreCase(MP_NOTELPEMOHON.trim())) {
				MP_NOTELPEMOHON = "0";
			}
			MP_NAMASIMATI = (String) h.get("MP_NAMASIMATI");
			MP_TARIKHMATI = (String) h.get("MP_TARIKHMATI");
			MP_NOKPSIMATI = (String) h.get("MP_NOKPSIMATI");
			displayNilaianTarikhMati = (Boolean) h.get("COND_DISPNILAIANTARIKHMATI");
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	private void populatePageHTA(Hashtable h) throws Exception {
		if (!h.isEmpty()) {
			FrmModelNilaianHartaTakAlih modelHTA = new FrmModelNilaianHartaTakAlih();
			
			PERLU_NILAIAN_TARIKH_MATI = (String) h.get("PERLU_NILAIAN_TARIKH_MATI");
			ID_NEGERI = (String) h.get("ID_NEGERI");
			ID_DAERAH = (String) h.get("ID_DAERAH");
			ID_MUKIM = (String) h.get("ID_MUKIM");
			SEKSYEN = (String) h.get("SEKSYEN");
			ID_JENISHM = (String) h.get("JENIS_HAKMILIK");
			NO_HAKMILIK = (String) h.get("NO_HAKMILIK");
			ID_JENISPEMBANGUNAN = (String) h.get("JENIS_PEMBANGUNAN");
			NO_PTLOT = (String) h.get("NO_PTLOT");
			ID_JENISPTLOT = (String) h.get("JENIS_PTLOT");
			NO_BANGUNAN = (String) h.get("NO_BANGUNAN");
			NO_TINGKAT = (String) h.get("NO_TINGKAT");
			NO_PETAK = (String) h.get("NO_PETAK");
			NO_RSS = (String) h.get("NO_RSS");
			BA_SIMATI = (String) h.get("BA_SIMATI");
			BB_SIMATI = (String) h.get("BB_SIMATI");
			ID_JENISPEGANGAN = (String) h.get("JENIS_PEGANGAN");
			TARIKH_LUPUT_PAJAKAN = (String) h.get("TARIKH_LUPUT_PAJAKAN");
			TEMPOH_PAJAKAN = (String) h.get("TEMPOH_PAJAKAN");
			ID_KATEGORITANAH = (String) h.get("KATEGORI_TANAH");
			SYARAT_NYATA = (String) h.get("SYARAT_NYATA");
			SEKATAN_KEPENTINGAN = (String) h.get("SEKATAN_KEPENTINGAN");
			LUAS_TANAH = (String) h.get("LUAS_TANAH");
			ID_JENISLUAS = (String) h.get("JENIS_LUAS_TANAH");
			LUAS_PETAK = (String) h.get("LUAS_PETAK");
			ALAMAT1_HARTA = (String) h.get("ALAMAT1_HARTA");
			ALAMAT2_HARTA = (String) h.get("ALAMAT2_HARTA");
			ALAMAT3_HARTA = (String) h.get("ALAMAT3_HARTA");
			ID_JENISHARTA = (String) h.get("JENIS_HARTA_DINILAI");
			CATATAN = (String) h.get("CATATAN");
			NAMA_PEMILIK = (String) h.get("NAMA_PEMILIK");
			TARIKH_PERJANJIAN = (String) h.get("TARIKH_PERJANJIAN");
			NAMA_PEMAJU = (String) h.get("NAMA_PEMAJU");
	    	NAMA_PEGAWAI_JKPTG = (String) h.get("NAMA_PEGAWAI_JKPTG");
	    	NO_TEL_PEGAWAI_JKPTG = (String) h.get("NO_TEL_PEGAWAI_JKPTG");
	    	CAWANGAN_PEGAWAI_JKPTG = (String) h.get("CAWANGAN_PEGAWAI_JKPTG");
	    	EMAIL_ADDR_PEGAWAI_JPPH = (String) h.get("EMAIL_ADDR_PEGAWAI_JPPH");
			EMAIL_SEND_JPPH = (String) h.get("EMAIL_SEND_JPPH");
			JPPH_NILAI_TARIKH_MOHON = (String) h.get("JPPH_NILAI_TARIKH_MOHON");
			JPPH_NILAI_TARIKH_MATI = (String) h.get("JPPH_NILAI_TARIKH_MATI");
			JPPH_NAMA_PEGAWAI = (String) h.get("JPPH_NAMA_PEGAWAI");
			JPPH_CAWANGAN_JPPH = (String) h.get("JPPH_CAWANGAN_JPPH");
			JPPH_CATATAN = (String) h.get("JPPH_CATATAN");
			JPPH_TARIKH_LAWAT_PERIKSA = (String) h.get("JPPH_TARIKH_LAWAT_PERIKSA");
			NO_RSS = (String) h.get("NO_RSS");
			
			if (isJPPHUser) {
				if ("".equalsIgnoreCase(JPPH_NAMA_PEGAWAI.trim())) {
					JPPH_NAMA_PEGAWAI = userName;
				}
				if ("".equalsIgnoreCase(JPPH_CAWANGAN_JPPH.trim())) {
					JPPH_CAWANGAN_JPPH = FrmModelNilaianHartaTakAlih.getCawanganUserJPPH(userID);
				}
			} else {
//				if ("".equalsIgnoreCase(EMAIL_ADDR_PEGAWAI_JPPH.trim())) {
//					EMAIL_ADDR_PEGAWAI_JPPH = "";
//				}
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
			if (NO_PTLOT.startsWith("LOT")) {
				ID_JENISPTLOT = "2";
			} else {
				ID_JENISPTLOT = "1";
			}
			if (!"2".equalsIgnoreCase(ID_JENISPEGANGAN)) {
				ID_JENISPEGANGAN = "1";
			}
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