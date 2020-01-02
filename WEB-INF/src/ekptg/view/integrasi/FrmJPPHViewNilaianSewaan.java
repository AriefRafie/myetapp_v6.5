package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.integrasi.FrmJPPHModelNilaianSewaan;
import ekptg.model.integrasi.FrmModelNilaianHartaTakAlih;

@SuppressWarnings({ "serial", "unused" })
public class FrmJPPHViewNilaianSewaan extends AjaxBasedModule {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	String ID_PERMOHONAN = "";
	String ID_HM = "";
	
	String MP_NOFAIL = "";
	String MP_NEGERI = "";
	String MP_DAERAH = "";
	String MP_UNITJKPTG = "";
	String MP_NAMAPEGAWAIJKPTG = "";
	String MP_NOTELJKPTG = "";
	String MP_CAWANGANJKPTG = "";
	
	String MH_NEGERI = "";
	String MH_DAERAH = "";
	String MH_MUKIM = "";
	String MH_SEKSYEN = "";
	String MH_JENISHAKMILIK = "";
	String MH_NOHAKMILIK = "";
	String MH_NOPTLOT = "";
	String MH_JENISPTLOT = "";
	String MH_JENISPEGANGAN = "";
	String MH_TARIKHLUPUTPAJAKAN = "";
	String MH_TEMPOHPAJAKAN = "";
	String MH_ADAPELANTAPAK = "";
	String MH_KATEGORITANAH = "";
	String MH_SYARATNYATA = "";
	String MH_SEKATANKEPENTINGAN = "";
	String MH_LUASASAL = "";
	String MH_JENISLUASASAL = "";
	String MH_LUASDISEWA = "";
	String MH_JENISLUASDISEWA = "";
	String MH_STATUSPEMILIKAN = "";
	String MH_TUJUANSEWAAN = "";
	String MH_ALAMAT1HARTA = "";
	String MH_ALAMAT2HARTA = "";
	String MH_ALAMAT3HARTA = "";
	String MH_ALAMAT4HARTA = "";
	String MH_NOLOTBERKAITAN = "";
	String MH_CATATAN = "";
		
	String JPPH_TARIKHNILAIAN = "";
	String JPPH_KADARBULAN = "";
	String JPPH_KADARBULAN3TAHUN = "";
	String JPPH_KADARTAHUNAN = "";
	String JPPH_NAMAPEGAWAI = "";
	String JPPH_CAWANGAN = "";
	String JPPH_CATATAN = "";
	
	String EMAIL_ADDR1 = "", EMAIL_ADDR2 = "", EMAIL_ADDR3 = "";
	String EMAIL_SEND = "";
	
	String SOC_MHNEGERI = "", SOC_MHDAERAH = "", SOC_MHMUKIM = "", SOC_MHJENISHAKMILIK = "", SOC_MHJENISPTLOT = "", SOC_MHJENISPEGANGAN = "";
	String SOC_MHKATEGORITANAH = "", SOC_MHSYARATNYATA = "", SOC_MHSEKATANKEPENTINGAN = "", SOC_MHJENISLUASASAL = "", SOC_MHJENISLUASDISEWA = "";
    
	Boolean isJPPHUser = false;
	String userName = "";
	String userRole = "";
	String userID = "";
	
	@SuppressWarnings({ "rawtypes" })
	public String doTemplate2() throws Exception{
		
		String vm = "";

        HttpSession session = this.request.getSession();
        
        FrmJPPHModelNilaianSewaan modelSewa = new FrmJPPHModelNilaianSewaan();
        
        // action
        String action2 = getParam("action2");
        String action = action2;
        
        String pageFlag = "";
        
        // command: used by changeSOC
        String command = getParam("command");
        
        String READONLY_FOR_JKPTG = "true", READONLY_FOR_JPPH = "true", READONLY_FOR_JENISPEGANGAN = "true";
        String DISABLE_FOR_JKPTG = " class=\"disabled\" disabled=\"disabled\" ", DISABLE_FOR_JPPH = " class=\"disabled\" disabled=\"disabled\" ";
        
        Boolean saveMaklumat = false, sendMaklumat = false, cancelMaklumat = false, returnMaklumat = false, hasMaklumat = false, isCompleted = false;
        
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        userID = (String) session.getAttribute("_ekptg_user_id");
        isJPPHUser = isJPPHUser(userRole);
        
        Boolean Page_Carian = false;
        
        ID_PERMOHONAN = getParam("ID_PERMOHONAN");
        ID_HM = getParam("ID_HM");
        
        MP_UNITJKPTG = FrmModelNilaianHartaTakAlih.getUnitUser(userID);
        
        isCompleted = modelSewa.isCompleted(ID_PERMOHONAN, ID_HM);
        getAllParamVariables();
    	
    	Vector vData = new Vector();
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();
    	
        if ("search".equalsIgnoreCase(action2)) {
            modelSewa.setNoFail(getParam("Cari_NoFail"));
            modelSewa.setIdNegeri(getParam("Cari_Negeri"));
            modelSewa.setIdDaerah(getParam("Cari_Daerah"));
            modelSewa.setIdMukim(getParam("Cari_Mukim"));
            modelSewa.setNoHakmilik(getParam("Cari_NoHakmilik"));
            modelSewa.setNoPTLot(getParam("Cari_NoPTLot"));
            
        	vList = modelSewa.searchMaklumat();
    		Page_Carian = true;
        } else if ("edit".equalsIgnoreCase(action2)) {
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			modelSewa.setIdPermohonan(ID_PERMOHONAN);
    			vData = modelSewa.viewMaklumat();
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    			pageFlag = "2";
    		} else {
    			Page_Carian = true;
    		}
        } else if ("view".equalsIgnoreCase(action2)) {
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			modelSewa.setIdPermohonan(ID_PERMOHONAN);
    			vData = modelSewa.viewMaklumat();
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    			pageFlag = "1";
    		} else {
    			Page_Carian = true;
    		}
        } else if ("save".equalsIgnoreCase(action2)) {
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			modelSewa.setIdPermohonan(ID_PERMOHONAN);
    			modelSewa.setIdHM(ID_HM);
    			modelSewa.setUnitJKPTG(MP_UNITJKPTG);
    			modelSewa.setNamaPegawaiJKPTG(MP_NAMAPEGAWAIJKPTG);
    			modelSewa.setNoTelPegawaiJKPTG(MP_NOTELJKPTG);
    			modelSewa.setCawanganJKPTG(MP_CAWANGANJKPTG);
    			modelSewa.setIdNegeri(MH_NEGERI);
    			modelSewa.setIdDaerah(MH_DAERAH);
    			modelSewa.setIdMukim(MH_MUKIM);
    			modelSewa.setSeksyen(MH_SEKSYEN);
    			modelSewa.setJenisHakmilik(MH_JENISHAKMILIK);
    			modelSewa.setNoHakmilik(MH_NOHAKMILIK);
    			modelSewa.setNoPTLot(MH_NOPTLOT);
    			modelSewa.setJenisPTLot(MH_JENISPTLOT);
    			modelSewa.setJenisPegangan(MH_JENISPEGANGAN);
    			modelSewa.setTarikhLuputPajakan(MH_TARIKHLUPUTPAJAKAN);
    			modelSewa.setTempohPajakan(MH_TEMPOHPAJAKAN);
    			modelSewa.setAdaPelanTapak(MH_ADAPELANTAPAK);
    			modelSewa.setKategoriTanah(MH_KATEGORITANAH);
    			modelSewa.setSyaratNyata(MH_SYARATNYATA);
    			modelSewa.setSekatanKepentingan(MH_SEKATANKEPENTINGAN);
    			modelSewa.setLuasAsal(MH_LUASASAL);
    			modelSewa.setJenisLuasAsal(MH_JENISLUASASAL);
    			modelSewa.setLuasDisewa(MH_LUASDISEWA);
    			modelSewa.setJenisLuasDisewa(MH_JENISLUASDISEWA);
    			modelSewa.setStatusPemilikan(MH_STATUSPEMILIKAN);
    			modelSewa.setTujuanSewaan(MH_TUJUANSEWAAN);
    			modelSewa.setAlamatHarta1(MH_ALAMAT1HARTA);
    			modelSewa.setAlamatHarta2(MH_ALAMAT2HARTA);
    			modelSewa.setAlamatHarta3(MH_ALAMAT3HARTA);
    			modelSewa.setAlamatHarta4(MH_ALAMAT4HARTA);
    			modelSewa.setNoLotBerkaitan(MH_NOLOTBERKAITAN);
    			modelSewa.setCatatan(MH_CATATAN);
    			modelSewa.setJpphTarikhNilaian(JPPH_TARIKHNILAIAN);
    			modelSewa.setJpphKadarSewaBulanan(JPPH_KADARBULAN);
    			modelSewa.setJpphKadarSewaBulanan3Tahun(JPPH_KADARBULAN3TAHUN);
    			modelSewa.setJpphKadarSewaTahunan(JPPH_KADARTAHUNAN);
    			modelSewa.setJpphCatatan(JPPH_CATATAN);
    			modelSewa.setJpphNamaPegawai(JPPH_NAMAPEGAWAI);
    			modelSewa.setJpphCawangan(JPPH_CAWANGAN);
    			modelSewa.setEmailAddr1(EMAIL_ADDR1);
    			modelSewa.setEmailAddr2(EMAIL_ADDR2);
    			modelSewa.setEmailAddr3(EMAIL_ADDR3);
    			modelSewa.setEmailSend(EMAIL_SEND);

				saveMaklumat = modelSewa.saveMaklumat(userID, isJPPHUser);
    			vData = modelSewa.viewMaklumat();
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    			pageFlag = "3";
    		} else {
    			Page_Carian = true;
    		}
        } else if ("send".equalsIgnoreCase(action2)) {
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			modelSewa.setIdPermohonan(ID_PERMOHONAN);
    			modelSewa.setIdHM(ID_HM);
    			modelSewa.setUnitJKPTG(MP_UNITJKPTG);
    			modelSewa.setNamaPegawaiJKPTG(MP_NAMAPEGAWAIJKPTG);
    			modelSewa.setNoTelPegawaiJKPTG(MP_NOTELJKPTG);
    			modelSewa.setCawanganJKPTG(MP_CAWANGANJKPTG);
    			modelSewa.setIdNegeri(MH_NEGERI);
    			modelSewa.setIdDaerah(MH_DAERAH);
    			modelSewa.setIdMukim(MH_MUKIM);
    			modelSewa.setSeksyen(MH_SEKSYEN);
    			modelSewa.setJenisHakmilik(MH_JENISHAKMILIK);
    			modelSewa.setNoHakmilik(MH_NOHAKMILIK);
    			modelSewa.setNoPTLot(MH_NOPTLOT);
    			modelSewa.setJenisPTLot(MH_JENISPTLOT);
    			modelSewa.setJenisPegangan(MH_JENISPEGANGAN);
    			modelSewa.setTarikhLuputPajakan(MH_TARIKHLUPUTPAJAKAN);
    			modelSewa.setTempohPajakan(MH_TEMPOHPAJAKAN);
    			modelSewa.setAdaPelanTapak(MH_ADAPELANTAPAK);
    			modelSewa.setKategoriTanah(MH_KATEGORITANAH);
    			modelSewa.setSyaratNyata(MH_SYARATNYATA);
    			modelSewa.setSekatanKepentingan(MH_SEKATANKEPENTINGAN);
    			modelSewa.setLuasAsal(MH_LUASASAL);
    			modelSewa.setJenisLuasAsal(MH_JENISLUASASAL);
    			modelSewa.setLuasDisewa(MH_LUASDISEWA);
    			modelSewa.setJenisLuasDisewa(MH_JENISLUASDISEWA);
    			modelSewa.setStatusPemilikan(MH_STATUSPEMILIKAN);
    			modelSewa.setTujuanSewaan(MH_TUJUANSEWAAN);
    			modelSewa.setAlamatHarta1(MH_ALAMAT1HARTA);
    			modelSewa.setAlamatHarta2(MH_ALAMAT2HARTA);
    			modelSewa.setAlamatHarta3(MH_ALAMAT3HARTA);
    			modelSewa.setAlamatHarta4(MH_ALAMAT4HARTA);
    			modelSewa.setNoLotBerkaitan(MH_NOLOTBERKAITAN);
    			modelSewa.setCatatan(MH_CATATAN);
    			modelSewa.setJpphTarikhNilaian(JPPH_TARIKHNILAIAN);
    			modelSewa.setJpphKadarSewaBulanan(JPPH_KADARBULAN);
    			modelSewa.setJpphKadarSewaBulanan3Tahun(JPPH_KADARBULAN3TAHUN);
    			modelSewa.setJpphKadarSewaTahunan(JPPH_KADARTAHUNAN);
    			modelSewa.setJpphCatatan(JPPH_CATATAN);
    			modelSewa.setJpphNamaPegawai(JPPH_NAMAPEGAWAI);
    			modelSewa.setJpphCawangan(JPPH_CAWANGAN);
    			modelSewa.setEmailAddr1(EMAIL_ADDR1);
    			modelSewa.setEmailAddr2(EMAIL_ADDR2);
    			modelSewa.setEmailAddr3(EMAIL_ADDR3);
    			modelSewa.setEmailSend(EMAIL_SEND);

				saveMaklumat = modelSewa.saveMaklumat(userID, isJPPHUser);
				sendMaklumat = modelSewa.sendMaklumat(userID, isJPPHUser);
				
    			modelSewa.setIdPermohonan(ID_PERMOHONAN);
    			vData = modelSewa.viewMaklumat();
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    			pageFlag = "3";
    		} else {
    			Page_Carian = true;
    		}        
        } else if ("return".equalsIgnoreCase(action2)) {
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			modelSewa.setIdPermohonan(ID_PERMOHONAN);
    			modelSewa.setIdHM(ID_HM);
    			returnMaklumat = modelSewa.returnMaklumat(userID);
    		} else {
    			Page_Carian = true;
    		}        	
        } else if ("cancel".equalsIgnoreCase(action2)) {
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			modelSewa.setIdPermohonan(ID_PERMOHONAN);
    			modelSewa.setIdHM(ID_HM);
    			cancelMaklumat = modelSewa.cancelMaklumat();
    		} else {
    			Page_Carian = true;
    		}
        } else {
    		Page_Carian = true;
        }
        
        if (Page_Carian) {
        	vm = "app/integrasi/frmJPPHCarianNilaianSewaan.jsp";
        	
        	if (!isJPPHUser) {
        		DISABLE_FOR_JPPH = "";
        		READONLY_FOR_JPPH = "";
        	}
        	SOC_MHNEGERI = getParam("Cari_Negeri");
        	SOC_MHDAERAH = getParam("Cari_Daerah");
        	SOC_MHMUKIM = getParam("Cari_Mukim");

	    	context.put("selectNegeri", HTML.SelectNegeri("Cari_Negeri", Utils.parseLong(SOC_MHNEGERI), DISABLE_FOR_JPPH, "onchange=\"doChange();\""));
	    	context.put("selectDaerah", HTML.SelectDaerahByNegeri(SOC_MHNEGERI, "Cari_Daerah", Utils.parseLong(SOC_MHDAERAH), DISABLE_FOR_JPPH, "onchange=\"doChange();\""));
	    	context.put("selectMukim", HTML.SelectMukimByDaerah(SOC_MHDAERAH, "Cari_Mukim", Utils.parseLong(SOC_MHMUKIM), DISABLE_FOR_JPPH));

        	context.put("SenaraiFail", vList);
	    	setupPage(session, action, vList);
    		context.put("pagingTitle", "title");
        } else {
        	vm = "app/integrasi/frmJPPHNilaianSewaan.jsp";
        	if (isJPPHUser) {
            	READONLY_FOR_JKPTG = "";
            	DISABLE_FOR_JKPTG = "";
        	} else {
        		if ("edit".equalsIgnoreCase(action2)) {
	            	READONLY_FOR_JPPH = "";
	            	DISABLE_FOR_JPPH = "";
	            	if (!"1".equalsIgnoreCase(SOC_MHJENISPEGANGAN.trim())) {
	                	READONLY_FOR_JENISPEGANGAN = "";
	            	}
        		}
        	}
        	if (!"".equalsIgnoreCase(command.trim())) {
        		// if command is not empty by means of SOC refresh, get all values from screen
        		getAllParamVariables();      		
        	}
        	// checkbox section
        	if ("1".equalsIgnoreCase(MH_ADAPELANTAPAK.trim())) {
        		MH_ADAPELANTAPAK = "checked=\"checked\"";
        	} else {
        		MH_ADAPELANTAPAK = "";
        	}
        	
        	// data exists in TBLINTJPPHSEWAAN
        	hasMaklumat = modelSewa.hasMaklumat(ID_PERMOHONAN, ID_HM);
        	
        	context.put("saveMaklumat", saveMaklumat);
        	context.put("sendMaklumat", sendMaklumat);
        	context.put("cancelMaklumat", cancelMaklumat);
        	context.put("returnMaklumat", returnMaklumat);
        	context.put("hasMaklumat", hasMaklumat);
        	context.put("isCompleted", isCompleted);
	        context.put("MP_NOFAIL", MP_NOFAIL);
	    	context.put("MP_NEGERI", MP_NEGERI);
	    	context.put("MP_DAERAH", MP_DAERAH);
	    	context.put("MP_UNITJKPTG", MP_UNITJKPTG);
	    	context.put("MP_NAMAPEGAWAIJKPTG", MP_NAMAPEGAWAIJKPTG);
	    	context.put("MP_NOTELJKPTG", MP_NOTELJKPTG);
	    	context.put("MP_CAWANGANJKPTG", MP_CAWANGANJKPTG);

	    	context.put("MH_NEGERI", HTML.SelectNegeri("MH_NEGERI", Utils.parseLong(SOC_MHNEGERI), DISABLE_FOR_JPPH, "onchange=\"doChangeSOC('changeSOCNegeri');\""));
	    	context.put("MH_DAERAH", HTML.SelectDaerahByNegeri(SOC_MHNEGERI, "MH_DAERAH", Utils.parseLong(SOC_MHDAERAH), DISABLE_FOR_JPPH));
	    	context.put("MH_MUKIM", HTML.SelectMukimByDaerah(SOC_MHDAERAH, "MH_MUKIM", Utils.parseLong(SOC_MHMUKIM), DISABLE_FOR_JPPH));
	    	context.put("MH_SEKSYEN", MH_SEKSYEN);
	    	context.put("MH_JENISHAKMILIK", HTML.SelectJenisHakmilik("MH_JENISHAKMILIK", Utils.parseLong(SOC_MHJENISHAKMILIK), DISABLE_FOR_JPPH));
	    	context.put("MH_NOHAKMILIK", MH_NOHAKMILIK);
	    	context.put("MH_NOPTLOT", MH_NOPTLOT);
	    	context.put("MH_JENISPTLOT", modelSewa.SelectJenisPTLot("MH_JENISPTLOT", Utils.parseLong(SOC_MHJENISPTLOT), DISABLE_FOR_JPPH));
	    	context.put("MH_JENISPEGANGAN", modelSewa.SelectJenisPegangan("MH_JENISPEGANGAN", Utils.parseLong(SOC_MHJENISPEGANGAN), DISABLE_FOR_JPPH, "onchange=\"doChangeSOC('changeSOCJenisPegangan');\""));
	    	context.put("MH_TARIKHLUPUTPAJAKAN", MH_TARIKHLUPUTPAJAKAN);
	    	context.put("MH_TEMPOHPAJAKAN", MH_TEMPOHPAJAKAN);
	    	context.put("MH_ADAPELANTAPAK", MH_ADAPELANTAPAK);
	    	context.put("MH_KATEGORITANAH", modelSewa.SelectKategoriTanah("MH_KATEGORITANAH", Utils.parseLong(SOC_MHKATEGORITANAH), DISABLE_FOR_JPPH));
	    	context.put("MH_SYARATNYATA", modelSewa.SelectSyaratNyata("MH_SYARATNYATA", Utils.parseLong(SOC_MHSYARATNYATA), DISABLE_FOR_JPPH));
	    	context.put("MH_SEKATANKEPENTINGAN", modelSewa.SelectSekatanKepentingan("MH_SEKATANKEPENTINGAN", Utils.parseLong(SOC_MHSEKATANKEPENTINGAN), DISABLE_FOR_JPPH));
	    	context.put("MH_LUASASAL", MH_LUASASAL);
	    	context.put("MH_JENISLUASASAL", modelSewa.SelectJenisLuasTanah("MH_JENISLUASASAL", Utils.parseLong(SOC_MHJENISLUASASAL), DISABLE_FOR_JPPH));
	    	context.put("MH_LUASDISEWA", MH_LUASDISEWA);
	    	context.put("MH_JENISLUASDISEWA", modelSewa.SelectJenisLuasTanah("MH_JENISLUASDISEWA", Utils.parseLong(SOC_MHJENISLUASDISEWA), DISABLE_FOR_JPPH));
	    	context.put("MH_STATUSPEMILIKAN", MH_STATUSPEMILIKAN);
	    	context.put("MH_TUJUANSEWAAN", MH_TUJUANSEWAAN);
	    	context.put("MH_ALAMAT1HARTA", MH_ALAMAT1HARTA);
	    	context.put("MH_ALAMAT2HARTA", MH_ALAMAT2HARTA);
	    	context.put("MH_ALAMAT3HARTA", MH_ALAMAT3HARTA);
	    	context.put("MH_ALAMAT4HARTA", MH_ALAMAT4HARTA);
	    	context.put("MH_NOLOTBERKAITAN", MH_NOLOTBERKAITAN);
	    	context.put("MH_CATATAN", MH_CATATAN);
	    	context.put("JPPH_TARIKHNILAIAN", JPPH_TARIKHNILAIAN);
	    	context.put("JPPH_KADARBULAN", JPPH_KADARBULAN);
	    	context.put("JPPH_KADARBULAN3TAHUN", JPPH_KADARBULAN3TAHUN);
	    	context.put("JPPH_KADARTAHUNAN", JPPH_KADARTAHUNAN);
	    	context.put("JPPH_NAMAPEGAWAI", JPPH_NAMAPEGAWAI);
	    	context.put("JPPH_CAWANGAN", JPPH_CAWANGAN);
	    	context.put("JPPH_CATATAN", JPPH_CATATAN);
	    	context.put("EMAIL_ADDR1", EMAIL_ADDR1);
	    	context.put("EMAIL_ADDR2", EMAIL_ADDR2);
	    	context.put("EMAIL_ADDR3", EMAIL_ADDR3);
	    	context.put("EMAIL_SEND", EMAIL_SEND);

        	if ("changeSOCNegeri".equalsIgnoreCase(command)) {
            	context.put("MH_NEGERI", HTML.SelectNegeri("MH_NEGERI", Utils.parseLong(SOC_MHNEGERI), DISABLE_FOR_JPPH, "onchange=\"doChangeSOC('changeSOCNegeri');\""));
            	context.put("MH_DAERAH", HTML.SelectDaerahByNegeri(SOC_MHNEGERI, "MH_DAERAH", (long) 0, DISABLE_FOR_JPPH, "onchange=\"doChangeSOC('changeSOCDaerah');\""));
            	context.put("MH_MUKIM", HTML.SelectMukim("MH_MUKIM", (long) 0, DISABLE_FOR_JPPH));
            } else if ("changeSOCDaerah".equalsIgnoreCase(command)) {
            	context.put("MH_DAERAH", HTML.SelectDaerahByNegeri(SOC_MHNEGERI, "MH_DAERAH", Utils.parseLong(SOC_MHDAERAH), DISABLE_FOR_JPPH, "onchange=\"doChangeSOC('changeSOCAHDaerah');\""));
            	context.put("MH_MUKIM", HTML.SelectMukimByDaerah(SOC_MHDAERAH, "MH_MUKIM", (long) 0, DISABLE_FOR_JPPH));
            }
        }

        context.put("READONLY_FOR_JENISPEGANGAN", READONLY_FOR_JENISPEGANGAN);
    	context.put("READONLY_FOR_JPPH", READONLY_FOR_JPPH);
    	context.put("READONLY_FOR_JKPTG", READONLY_FOR_JKPTG);
    	context.put("DISABLE_FOR_JPPH", DISABLE_FOR_JPPH);
    	context.put("DISABLE_FOR_JKPTG", DISABLE_FOR_JKPTG);
        context.put("ID_PERMOHONAN", ID_PERMOHONAN);
        context.put("ID_HM", ID_HM);
        context.put("isJPPHUser", isJPPHUser);
        context.put("action2", action);
        context.put("pageFlag", pageFlag);
		return vm;
	}
	
	private void getAllParamVariables() throws Exception {
		MP_NAMAPEGAWAIJKPTG = getParam("MP_NAMAPEGAWAIJKPTG");
		MP_NOTELJKPTG = getParam("MP_NOTELJKPTG");
		MP_CAWANGANJKPTG = getParam("MP_CAWANGANJKPTG");
		MH_NEGERI = getParam("MH_NEGERI");
		MH_DAERAH = getParam("MH_DAERAH");
		MH_MUKIM = getParam("MH_MUKIM");
		MH_SEKSYEN = getParam("MH_SEKSYEN");
		MH_JENISHAKMILIK = getParam("MH_JENISHAKMILIK");
		MH_NOHAKMILIK = getParam("MH_NOHAKMILIK");
		MH_NOPTLOT = getParam("MH_NOPTLOT");
		MH_JENISPTLOT = getParam("MH_JENISPTLOT");
		MH_JENISPEGANGAN = getParam("MH_JENISPEGANGAN");
		MH_TARIKHLUPUTPAJAKAN = getParam("MH_TARIKHLUPUTPAJAKAN");
		MH_TEMPOHPAJAKAN = getParam("MH_TEMPOHPAJAKAN");
		MH_ADAPELANTAPAK = getParam("MH_ADAPELANTAPAK");
		MH_KATEGORITANAH = getParam("MH_KATEGORITANAH");
		MH_SYARATNYATA = getParam("MH_SYARATNYATA");
		MH_SEKATANKEPENTINGAN = getParam("MH_SEKATANKEPENTINGAN");
		MH_LUASASAL = getParam("MH_LUASASAL");
		MH_JENISLUASASAL = getParam("MH_JENISLUASASAL");
		MH_LUASDISEWA = getParam("MH_LUASDISEWA");
		MH_JENISLUASDISEWA = getParam("MH_JENISLUASDISEWA");
		MH_STATUSPEMILIKAN = getParam("MH_STATUSPEMILIKAN");
		MH_TUJUANSEWAAN = getParam("MH_TUJUANSEWAAN");
		MH_ALAMAT1HARTA = getParam("MH_ALAMAT1HARTA");
		MH_ALAMAT2HARTA = getParam("MH_ALAMAT2HARTA");
		MH_ALAMAT3HARTA = getParam("MH_ALAMAT3HARTA");
		MH_ALAMAT4HARTA = getParam("MH_ALAMAT4HARTA");
		MH_NOLOTBERKAITAN = getParam("MH_NOLOTBERKAITAN");
		MH_CATATAN = getParam("MH_CATATAN");
		JPPH_TARIKHNILAIAN = getParam("JPPH_TARIKHNILAIAN");
		JPPH_KADARBULAN = getParam("JPPH_KADARBULAN");
		JPPH_KADARBULAN3TAHUN = getParam("JPPH_KADARBULAN3TAHUN");
		JPPH_KADARTAHUNAN = getParam("JPPH_KADARTAHUNAN");
		JPPH_NAMAPEGAWAI = getParam("JPPH_NAMAPEGAWAI");
		JPPH_CAWANGAN = getParam("JPPH_CAWANGAN");
		JPPH_CATATAN = getParam("JPPH_CATATAN");
		EMAIL_ADDR1 = getParam("EMAIL_ADDR1");
		EMAIL_ADDR2 = getParam("EMAIL_ADDR2");
		EMAIL_ADDR3 = getParam("EMAIL_ADDR3");
		EMAIL_SEND = getParam("EMAIL_SEND");
		
		SOC_MHNEGERI = getParam("MH_NEGERI");
    	SOC_MHDAERAH = getParam("MH_DAERAH");
    	SOC_MHMUKIM = getParam("MH_MUKIM");
    	SOC_MHJENISHAKMILIK = getParam("MH_JENISHAKMILIK");
    	SOC_MHJENISPTLOT = getParam("MH_JENISPTLOT");
    	SOC_MHJENISPEGANGAN = getParam("MH_JENISPEGANGAN");
    	SOC_MHKATEGORITANAH = getParam("MH_KATEGORITANAH");
    	SOC_MHSYARATNYATA = getParam("MH_SYARATNYATA");
    	SOC_MHSEKATANKEPENTINGAN = getParam("MH_SEKATANKEPENTINGAN");
    	SOC_MHJENISLUASASAL = getParam("MH_JENISLUASASAL");
    	SOC_MHJENISLUASDISEWA = getParam("MH_JENISLUASDISEWA");     
    }
	
	@SuppressWarnings("rawtypes")
	private void populatePage(Hashtable h) throws Exception {
		if (!h.isEmpty()) {
			ID_HM = (String) h.get("ID_HM");
			MP_NOFAIL = (String) h.get("MP_NOFAIL");
			MP_NEGERI = (String) h.get("MP_NEGERI");
			MP_DAERAH = (String) h.get("MP_DAERAH");
			MP_UNITJKPTG = (String) h.get("MP_UNITJKPTG");
			MP_NAMAPEGAWAIJKPTG = (String) h.get("MP_NAMAPEGAWAIJKPTG");
			MP_NOTELJKPTG = (String) h.get("MP_NOTELJKPTG");
			MP_CAWANGANJKPTG = (String) h.get("MP_CAWANGANJKPTG");
			MH_NEGERI = (String) h.get("MH_NEGERI");
			MH_DAERAH = (String) h.get("MH_DAERAH");
			MH_MUKIM = (String) h.get("MH_MUKIM");
			MH_SEKSYEN = (String) h.get("MH_SEKSYEN");
			MH_JENISHAKMILIK = (String) h.get("MH_JENISHAKMILIK");
			MH_NOHAKMILIK = (String) h.get("MH_NOHAKMILIK");
			MH_NOPTLOT = (String) h.get("MH_NOPTLOT");
			if (MH_NOPTLOT.toUpperCase().startsWith("LOT")) {
				MH_JENISPTLOT = "1";				
			} else {
				MH_JENISPTLOT = "2";
			}
			MH_JENISPEGANGAN = (String) h.get("MH_JENISPEGANGAN");
			MH_TARIKHLUPUTPAJAKAN = (String) h.get("MH_TARIKHLUPUTPAJAKAN");
			MH_TEMPOHPAJAKAN = (String) h.get("MH_TEMPOHPAJAKAN");
			MH_ADAPELANTAPAK = (String) h.get("MH_ADAPELANTAPAK");
			MH_KATEGORITANAH = (String) h.get("MH_KATEGORITANAH");
			MH_SYARATNYATA = (String) h.get("MH_SYARATNYATA");
			MH_SEKATANKEPENTINGAN = (String) h.get("MH_SEKATANKEPENTINGAN");
			MH_LUASASAL = (String) h.get("MH_LUASASAL");
			MH_JENISLUASASAL = (String) h.get("MH_JENISLUASASAL");
			MH_LUASDISEWA = (String) h.get("MH_LUASDISEWA");
			MH_JENISLUASDISEWA = (String) h.get("MH_JENISLUASDISEWA");
			MH_STATUSPEMILIKAN = (String) h.get("MH_STATUSPEMILIKAN");
			MH_TUJUANSEWAAN = (String) h.get("MH_TUJUANSEWAAN");
			MH_ALAMAT1HARTA = (String) h.get("MH_ALAMAT1HARTA");
			MH_ALAMAT2HARTA = (String) h.get("MH_ALAMAT2HARTA");
			MH_ALAMAT3HARTA = (String) h.get("MH_ALAMAT3HARTA");
			MH_ALAMAT4HARTA = (String) h.get("MH_ALAMAT4HARTA");
			MH_NOLOTBERKAITAN = (String) h.get("MH_NOLOTBERKAITAN");
			MH_CATATAN = (String) h.get("MH_CATATAN");
			JPPH_TARIKHNILAIAN = (String) h.get("JPPH_TARIKHNILAIAN");
			JPPH_KADARBULAN = (String) h.get("JPPH_KADARBULAN");
			JPPH_KADARBULAN3TAHUN = (String) h.get("JPPH_KADARBULAN3TAHUN");
			JPPH_KADARTAHUNAN = (String) h.get("JPPH_KADARTAHUNAN");
			JPPH_NAMAPEGAWAI = (String) h.get("JPPH_NAMAPEGAWAI");
			JPPH_CAWANGAN = (String) h.get("JPPH_CAWANGAN");
			JPPH_CATATAN = (String) h.get("JPPH_CATATAN");
			EMAIL_ADDR1 = (String) h.get("EMAIL_ADDR1");
			EMAIL_ADDR2 = (String) h.get("EMAIL_ADDR2");
			EMAIL_ADDR3 = (String) h.get("EMAIL_ADDR3");
			EMAIL_SEND = (String) h.get("EMAIL_SEND");

			if (isJPPHUser) {
				if ("".equalsIgnoreCase(JPPH_NAMAPEGAWAI.trim())) {
					JPPH_NAMAPEGAWAI = userName.toUpperCase();
				}
				if ("".equalsIgnoreCase(JPPH_CAWANGAN.trim())) {
					JPPH_CAWANGAN = FrmModelNilaianHartaTakAlih.getCawanganUserJPPH(userID).toUpperCase();
				}
			} else {
				if ("".equalsIgnoreCase(MP_UNITJKPTG.trim())) {
					MP_UNITJKPTG = FrmModelNilaianHartaTakAlih.getUnitUser(userID);
				}
				if ("".equalsIgnoreCase(MP_NAMAPEGAWAIJKPTG.trim())) {
					MP_NAMAPEGAWAIJKPTG = userName.toUpperCase().trim();
				}
				if ("".equalsIgnoreCase(MP_NOTELJKPTG.trim())) {
					MP_NOTELJKPTG = FrmModelNilaianHartaTakAlih.getNoTelefonUser(userID);					
				}
				if ("".equalsIgnoreCase(MP_CAWANGANJKPTG.trim())) {
					MP_CAWANGANJKPTG = FrmModelNilaianHartaTakAlih.getCawanganUserJPPH(userID);
				}
			}
			if ("1".equalsIgnoreCase(EMAIL_SEND.trim())) {
				EMAIL_SEND = "checked=\"checked\"";
			}

	    	SOC_MHNEGERI = MH_NEGERI;
	    	SOC_MHDAERAH = MH_DAERAH;
	    	SOC_MHMUKIM = MH_MUKIM;
	    	SOC_MHJENISHAKMILIK = MH_JENISHAKMILIK;
	    	SOC_MHJENISPTLOT = MH_JENISPTLOT;
	    	SOC_MHJENISPEGANGAN = MH_JENISPEGANGAN;
	    	SOC_MHKATEGORITANAH = MH_KATEGORITANAH;
	    	SOC_MHSYARATNYATA = MH_SYARATNYATA;
	    	SOC_MHSEKATANKEPENTINGAN = MH_SEKATANKEPENTINGAN;
	    	SOC_MHJENISLUASASAL = MH_JENISLUASASAL;
	    	SOC_MHJENISLUASDISEWA = MH_JENISLUASDISEWA;
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
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
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
}