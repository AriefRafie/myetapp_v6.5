package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.integrasi.FrmJPPHModelNilaianPajakan;
import ekptg.model.integrasi.FrmModelNilaianHartaTakAlih;

@SuppressWarnings({ "serial", "unused" })
public class FrmJPPHViewNilaianPajakan extends AjaxBasedModule {

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
	String MH_NOPAJAKAN = "";
	String MH_NOPERSERAHAN = "";
	String MH_JENISPEGANGAN = "";
	String MH_TARIKHLUPUTPAJAKAN = "";
	String MH_TEMPOHPAJAKAN = "";
	String MH_ADAPELANTAPAK = "";
	String MH_KATEGORITANAH = "";
	String MH_SYARATNYATA = "";
	String MH_SEKATANKEPENTINGAN = "";
	String MH_LUASASAL = "";
	String MH_JENISLUASASAL = "";
	String MH_LUASDIPAJAK = "";
	String MH_JENISLUASDIPAJAK = "";
	String MH_STATUSPEMILIKAN = "";
	String MH_TUJUANPAJAKAN = "";
	String MH_NILAIPAJAK = "";
	String MH_PAJAK99 = "";
	String MH_PAJAK60 = "";
	String MH_PAJAK30 = "";
	String MH_PAJAKLAINLAIN = "";
	String MH_PAJAKTAHUNAN = "";
	String MH_NILAIANDIPERLUKAN = "";
	String MH_TEMPOHNILAIAN = "";
	String MH_PAJAKLAINLAINTAHUN = "";
	String MH_CATATAN = "";
		
	String JPPH_TARIKHNILAIAN = "";
	String JPPH_NILAIPAJAK99 = "";
	String JPPH_NILAIPAJAK60 = "";
	String JPPH_NILAIPAJAK30 = "";
	String JPPH_NILAIPAJAKLAINLAIN = "";
	String JPPH_NILAIPAJAKTAHUNAN = "";
	String JPPH_NAMAPEGAWAI = "";
	String JPPH_CAWANGAN = "";
	String JPPH_CATATAN = "";
	
	String EMAIL_ADDR1 = "";
	String EMAIL_ADDR2 = "";
	String EMAIL_ADDR3 = "";
	String EMAIL_SEND = "";
	
	String SOC_MHNEGERI = "", SOC_MHDAERAH = "", SOC_MHMUKIM = "", SOC_MHJENISHAKMILIK = "", SOC_MHJENISPTLOT = "", SOC_MHJENISPEGANGAN = "";
	String SOC_MHKATEGORITANAH = "", SOC_MHSYARATNYATA = "", SOC_MHSEKATANKEPENTINGAN = "", SOC_MHJENISLUASASAL = "", SOC_MHJENISLUASDIPAJAK = "";
    
	Boolean isJPPHUser = false;
	String userName = "";
	String userRole = "";
	String userID = "";
	
	@SuppressWarnings({ "rawtypes" })
	public String doTemplate2() throws Exception{
		String vm = "";

        HttpSession session = this.request.getSession();
        
        FrmJPPHModelNilaianPajakan modelPajak = new FrmJPPHModelNilaianPajakan();
        
        // action
        String action2 = getParam("action2");
        String action = action2;
        
        // command: used by changeSOC
        String command = getParam("command");
        
        String READONLY_FOR_JKPTG = "true", READONLY_FOR_JPPH = "true", READONLY_FOR_JENISPEGANGAN = "true";
        String DISABLE_FOR_JKPTG = " disabled=\"disabled\" ", DISABLE_FOR_JPPH = " disabled=\"disabled\" ";
        
        Boolean saveMaklumat = false, sendMaklumat = false, cancelMaklumat = false, returnMaklumat = false, hasMaklumat = false, isCompleted = false;
        
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        userID = (String) session.getAttribute("_ekptg_user_id");
        isJPPHUser = isJPPHUser(userRole);
        
        Boolean Page_Carian = false;
        
        ID_PERMOHONAN = getParam("ID_PERMOHONAN");
        ID_HM = getParam("ID_HM");
        
        MP_UNITJKPTG = FrmModelNilaianHartaTakAlih.getUnitUser(userID);
        
        isCompleted = modelPajak.isCompleted(ID_PERMOHONAN, ID_HM);
        getAllParamVariables();
    	
    	Vector vData = new Vector();
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();
    	
    	if ("".equalsIgnoreCase(action2.trim())) {
    		action2 = "view";
    	}
//    	ID_PERMOHONAN = "1610114067";
//    	ID_HM = "161090966";
    	
        if ("search".equalsIgnoreCase(action2)) {
            modelPajak.setNoFail(getParam("Cari_NoFail"));
            modelPajak.setIdNegeri(getParam("Cari_Negeri"));
            modelPajak.setIdDaerah(getParam("Cari_Daerah"));
            modelPajak.setIdMukim(getParam("Cari_Mukim"));
            modelPajak.setNoHakmilik(getParam("Cari_NoHakmilik"));
            modelPajak.setNoPTLot(getParam("Cari_NoPTLot"));
            
        	vList = modelPajak.searchMaklumat();
    		Page_Carian = true;
        } else if ("edit".equalsIgnoreCase(action2)) {
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			modelPajak.setIdPermohonan(ID_PERMOHONAN);
    			vData = modelPajak.viewMaklumat();
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    		} else {
    			Page_Carian = true;
    		}
        } else if ("view".equalsIgnoreCase(action2)) {
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			modelPajak.setIdPermohonan(ID_PERMOHONAN);
    			vData = modelPajak.viewMaklumat();
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    		} else {
    			Page_Carian = true;
    		}
        } else if ("save".equalsIgnoreCase(action2)) {
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			modelPajak.setIdPermohonan(ID_PERMOHONAN);
    			modelPajak.setIdHM(ID_HM);
    			modelPajak.setUnitJKPTG(MP_UNITJKPTG);
    			modelPajak.setNamaPegawaiJKPTG(MP_NAMAPEGAWAIJKPTG);
    			modelPajak.setNoTelPegawaiJKPTG(MP_NOTELJKPTG);
    			modelPajak.setCawanganJKPTG(MP_CAWANGANJKPTG);
    			modelPajak.setIdNegeri(MH_NEGERI);
    			modelPajak.setIdDaerah(MH_DAERAH);
    			modelPajak.setIdMukim(MH_MUKIM);
    			modelPajak.setSeksyen(MH_SEKSYEN);
    			modelPajak.setJenisHakmilik(MH_JENISHAKMILIK);
    			modelPajak.setNoHakmilik(MH_NOHAKMILIK);
    			modelPajak.setNoPTLot(MH_NOPTLOT);
    			modelPajak.setJenisPTLot(MH_JENISPTLOT);
    			modelPajak.setNoPajakan(MH_NOPAJAKAN);
    			modelPajak.setNoPerserahan(MH_NOPERSERAHAN);
    			modelPajak.setJenisPegangan(MH_JENISPEGANGAN);
    			modelPajak.setTarikhLuputPajakan(MH_TARIKHLUPUTPAJAKAN);
    			modelPajak.setTempohPajakan(MH_TEMPOHPAJAKAN);
    			modelPajak.setAdaPelanTapak(MH_ADAPELANTAPAK);
    			modelPajak.setKategoriTanah(MH_KATEGORITANAH);
    			modelPajak.setSyaratNyata(MH_SYARATNYATA);
    			modelPajak.setSekatanKepentingan(MH_SEKATANKEPENTINGAN);
    			modelPajak.setLuasAsal(MH_LUASASAL);
    			modelPajak.setJenisLuasAsal(MH_JENISLUASASAL);
    			modelPajak.setLuasDipajak(MH_LUASDIPAJAK);
    			modelPajak.setJenisLuasDipajak(MH_JENISLUASDIPAJAK);
    			modelPajak.setStatusPemilikan(MH_STATUSPEMILIKAN);
    			modelPajak.setTujuanPajakan(MH_TUJUANPAJAKAN);
    			modelPajak.setJenisNilaianPajakan(getParam("MH_NILAIPAJAK2"));
    			modelPajak.setNilaianDiperlukan(MH_NILAIANDIPERLUKAN);
    			modelPajak.setTempohNilaian(MH_TEMPOHNILAIAN);
    			modelPajak.setCatatan(MH_CATATAN);
    			modelPajak.setJpphTarikhNilaian(JPPH_TARIKHNILAIAN);
    			modelPajak.setJpphNilaiPajak99(JPPH_NILAIPAJAK99);
    			modelPajak.setJpphNilaiPajak60(JPPH_NILAIPAJAK60);
    			modelPajak.setJpphNilaiPajak30(JPPH_NILAIPAJAK30);
    			modelPajak.setJpphNilaiPajakLainLain(JPPH_NILAIPAJAKLAINLAIN);
    			modelPajak.setJpphNilaiPajakTahunan(JPPH_NILAIPAJAKTAHUNAN);
    			modelPajak.setJpphCatatan(JPPH_CATATAN);
    			modelPajak.setJpphNamaPegawai(JPPH_NAMAPEGAWAI);
    			modelPajak.setJpphCawangan(JPPH_CAWANGAN);
    			modelPajak.setEmailAddr1(EMAIL_ADDR1);
    			modelPajak.setEmailAddr2(EMAIL_ADDR2);
    			modelPajak.setEmailAddr3(EMAIL_ADDR3);
    			modelPajak.setEmailSend(EMAIL_SEND);

				saveMaklumat = modelPajak.saveMaklumat(userID, isJPPHUser);
    			vData = modelPajak.viewMaklumat();
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    		} else {
    			Page_Carian = true;
    		}
        } else if ("send".equalsIgnoreCase(action2)) {
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			modelPajak.setIdPermohonan(ID_PERMOHONAN);
    			modelPajak.setIdHM(ID_HM);
    			modelPajak.setUnitJKPTG(MP_UNITJKPTG);
    			modelPajak.setNamaPegawaiJKPTG(MP_NAMAPEGAWAIJKPTG);
    			modelPajak.setNoTelPegawaiJKPTG(MP_NOTELJKPTG);
    			modelPajak.setCawanganJKPTG(MP_CAWANGANJKPTG);
    			modelPajak.setIdNegeri(MH_NEGERI);
    			modelPajak.setIdDaerah(MH_DAERAH);
    			modelPajak.setIdMukim(MH_MUKIM);
    			modelPajak.setSeksyen(MH_SEKSYEN);
    			modelPajak.setJenisHakmilik(MH_JENISHAKMILIK);
    			modelPajak.setNoHakmilik(MH_NOHAKMILIK);
    			modelPajak.setNoPTLot(MH_NOPTLOT);
    			modelPajak.setJenisPTLot(MH_JENISPTLOT);
    			modelPajak.setNoPajakan(MH_NOPAJAKAN);
    			modelPajak.setNoPerserahan(MH_NOPERSERAHAN);
    			modelPajak.setJenisPegangan(MH_JENISPEGANGAN);
    			modelPajak.setTarikhLuputPajakan(MH_TARIKHLUPUTPAJAKAN);
    			modelPajak.setTempohPajakan(MH_TEMPOHPAJAKAN);
    			modelPajak.setAdaPelanTapak(MH_ADAPELANTAPAK);
    			modelPajak.setKategoriTanah(MH_KATEGORITANAH);
    			modelPajak.setSyaratNyata(MH_SYARATNYATA);
    			modelPajak.setSekatanKepentingan(MH_SEKATANKEPENTINGAN);
    			modelPajak.setLuasAsal(MH_LUASASAL);
    			modelPajak.setJenisLuasAsal(MH_JENISLUASASAL);
    			modelPajak.setLuasDipajak(MH_LUASDIPAJAK);
    			modelPajak.setJenisLuasDipajak(MH_JENISLUASDIPAJAK);
    			modelPajak.setStatusPemilikan(MH_STATUSPEMILIKAN);
    			modelPajak.setTujuanPajakan(MH_TUJUANPAJAKAN);
    			modelPajak.setJenisNilaianPajakan(getParam("MH_NILAIPAJAK2"));
    			modelPajak.setNilaianDiperlukan(MH_NILAIANDIPERLUKAN);
    			modelPajak.setTempohNilaian(MH_TEMPOHNILAIAN);
    			modelPajak.setCatatan(MH_CATATAN);
    			modelPajak.setJpphTarikhNilaian(JPPH_TARIKHNILAIAN);
    			modelPajak.setJpphNilaiPajak99(JPPH_NILAIPAJAK99);
    			modelPajak.setJpphNilaiPajak60(JPPH_NILAIPAJAK60);
    			modelPajak.setJpphNilaiPajak30(JPPH_NILAIPAJAK30);
    			modelPajak.setJpphNilaiPajakLainLain(JPPH_NILAIPAJAKLAINLAIN);
    			modelPajak.setJpphNilaiPajakTahunan(JPPH_NILAIPAJAKTAHUNAN);
    			modelPajak.setJpphCatatan(JPPH_CATATAN);
    			modelPajak.setJpphNamaPegawai(JPPH_NAMAPEGAWAI);
    			modelPajak.setJpphCawangan(JPPH_CAWANGAN);
    			modelPajak.setEmailAddr1(EMAIL_ADDR1);
    			modelPajak.setEmailAddr2(EMAIL_ADDR2);
    			modelPajak.setEmailAddr3(EMAIL_ADDR3);
    			modelPajak.setEmailSend(EMAIL_SEND);
    			
				saveMaklumat = modelPajak.saveMaklumat(userID, isJPPHUser);
				sendMaklumat = modelPajak.sendMaklumat(userID, isJPPHUser);
				
    			vData = modelPajak.viewMaklumat();
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    		} else {
    			Page_Carian = true;
    		}        
        } else if ("return".equalsIgnoreCase(action2)) {
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			modelPajak.setIdPermohonan(ID_PERMOHONAN);
    			modelPajak.setIdHM(ID_HM);
    			modelPajak.setJpphCatatan(JPPH_CATATAN);
    			returnMaklumat = modelPajak.returnMaklumat(userID);
    		} else {
    			Page_Carian = true;
    		}        	
        } else if ("cancel".equalsIgnoreCase(action2)) {
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {

    		} else {
    			Page_Carian = true;
    		}
        } else {
    		Page_Carian = true;
        }
        
        if (Page_Carian) {
        	vm = "app/integrasi/frmJPPHCarianNilaianPajakan.jsp";
        	
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
        	vm = "app/integrasi/frmJPPHNilaianPajakan.jsp";
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
				if ("MH_PAJAK99".equalsIgnoreCase(command.trim())) {
					MH_PAJAK99 = "checked=\"checked\"";
					MH_NILAIANDIPERLUKAN = "1";
				} else if ("MH_PAJAK60".equalsIgnoreCase(command.trim())) {
					MH_PAJAK60 = "checked=\"checked\"";
					MH_NILAIANDIPERLUKAN = "2";
				} else if ("MH_PAJAK30".equalsIgnoreCase(command.trim())) {
					MH_PAJAK30 = "checked=\"checked\"";
					MH_NILAIANDIPERLUKAN = "3";
				} else if ("MH_PAJAKLAINLAIN".equalsIgnoreCase(command.trim())) {
					MH_PAJAKLAINLAIN = "checked=\"checked\"";
					MH_NILAIANDIPERLUKAN = "4";
				} else if ("MH_PAJAKTAHUNAN".equalsIgnoreCase(command.trim())) {
					MH_PAJAKTAHUNAN = "checked=\"checked\"";
					MH_NILAIANDIPERLUKAN = "5";
				} else {
					MH_NILAIPAJAK = getParam("MH_NILAIPAJAK2");
					if ("MH_PAJAK99".equalsIgnoreCase(MH_NILAIPAJAK.trim())) {
						MH_PAJAK99 = "checked=\"checked\"";
						MH_NILAIANDIPERLUKAN = "1";
					} else if ("MH_PAJAK60".equalsIgnoreCase(MH_NILAIPAJAK.trim())) {
						MH_PAJAK60 = "checked=\"checked\"";
						MH_NILAIANDIPERLUKAN = "2";
					} else if ("MH_PAJAK30".equalsIgnoreCase(MH_NILAIPAJAK.trim())) {
						MH_PAJAK30 = "checked=\"checked\"";
						MH_NILAIANDIPERLUKAN = "3";
					} else if ("MH_PAJAKLAINLAIN".equalsIgnoreCase(MH_NILAIPAJAK.trim())) {
						MH_PAJAKLAINLAIN = "checked=\"checked\"";
						MH_NILAIANDIPERLUKAN = "4";
					} else if ("MH_PAJAKTAHUNAN".equalsIgnoreCase(MH_NILAIPAJAK.trim())) {
						MH_PAJAKTAHUNAN = "checked=\"checked\"";
						MH_NILAIANDIPERLUKAN = "5";
					}
				}
        	}
        	
        	if ("".equalsIgnoreCase(MH_NILAIPAJAK.trim())) {
        		MH_NILAIPAJAK = "MH_PAJAK99";
        		MH_PAJAK99 = "checked=\"checked\"";
        		MH_NILAIANDIPERLUKAN = "1";
        	}
        	
        	// checkbox section
        	if ("1".equalsIgnoreCase(MH_ADAPELANTAPAK.trim())) {
        		MH_ADAPELANTAPAK = "checked=\"checked\"";
        	} else {
        		MH_ADAPELANTAPAK = "";
        	}
        	if ("1".equalsIgnoreCase(EMAIL_SEND.trim())) {
        		EMAIL_SEND = "checked=\"checked\"";
        	} else {
        		EMAIL_SEND = "";
        	}

        	// data exists in TBLINTJPPHPAJAKAN
        	hasMaklumat = modelPajak.hasMaklumat();
        	
        	context.put("saveMaklumat", saveMaklumat);
        	context.put("sendMaklumat", sendMaklumat);
        	context.put("cancelMaklumat", cancelMaklumat);
        	context.put("returnMaklumat", returnMaklumat);
        	context.put("hasMaklumat", hasMaklumat);
        	
	        context.put("MP_NOFAIL", MP_NOFAIL);
	    	context.put("MP_NEGERI", MP_NEGERI);
	    	context.put("MP_DAERAH", MP_DAERAH);
	    	context.put("MP_UNITJKPTG", MP_UNITJKPTG);
	    	context.put("MP_NAMAPEGAWAIJKPTG", MP_NAMAPEGAWAIJKPTG);
	    	context.put("MP_NOTELJKPTG", MP_NOTELJKPTG);
	    	context.put("MP_CAWANGANJKPTG", MP_CAWANGANJKPTG);

	    	context.put("MH_NEGERI", HTML.SelectNegeri("MH_NEGERI", Utils.parseLong(SOC_MHNEGERI), DISABLE_FOR_JPPH, "onchange=\"doChange('changeSOCNegeri', 'true');\""));
	    	context.put("MH_DAERAH", HTML.SelectDaerahByNegeri(SOC_MHNEGERI, "MH_DAERAH", Utils.parseLong(SOC_MHDAERAH), DISABLE_FOR_JPPH));
	    	context.put("MH_MUKIM", HTML.SelectMukimByDaerah(SOC_MHDAERAH, "MH_MUKIM", Utils.parseLong(SOC_MHMUKIM), DISABLE_FOR_JPPH));
	    	context.put("MH_SEKSYEN", MH_SEKSYEN);
	    	context.put("MH_JENISHAKMILIK", HTML.SelectJenisHakmilik("MH_JENISHAKMILIK", Utils.parseLong(SOC_MHJENISHAKMILIK), DISABLE_FOR_JPPH));
	    	context.put("MH_NOHAKMILIK", MH_NOHAKMILIK);
	    	context.put("MH_NOPTLOT", MH_NOPTLOT);
	    	context.put("MH_JENISPTLOT", modelPajak.SelectJenisPTLot("MH_JENISPTLOT", Utils.parseLong(SOC_MHJENISPTLOT), DISABLE_FOR_JPPH));
	    	context.put("MH_NOPAJAKAN", MH_NOPAJAKAN);
	    	context.put("MH_NOPERSERAHAN", MH_NOPERSERAHAN);
	    	context.put("MH_JENISPEGANGAN", modelPajak.SelectJenisPegangan("MH_JENISPEGANGAN", Utils.parseLong(SOC_MHJENISPEGANGAN), DISABLE_FOR_JPPH, "onchange=\"doChange('changeSOCJenisPegangan', 'true');\""));
	    	context.put("MH_TARIKHLUPUTPAJAKAN", MH_TARIKHLUPUTPAJAKAN);
	    	context.put("MH_TEMPOHPAJAKAN", MH_TEMPOHPAJAKAN);
	    	context.put("MH_ADAPELANTAPAK", MH_ADAPELANTAPAK);
	    	context.put("MH_KATEGORITANAH", modelPajak.SelectKategoriTanah("MH_KATEGORITANAH", Utils.parseLong(SOC_MHKATEGORITANAH), DISABLE_FOR_JPPH));
	    	context.put("MH_SYARATNYATA", modelPajak.SelectSyaratNyata("MH_SYARATNYATA", Utils.parseLong(SOC_MHSYARATNYATA), DISABLE_FOR_JPPH));
	    	context.put("MH_SEKATANKEPENTINGAN", modelPajak.SelectSekatanKepentingan("MH_SEKATANKEPENTINGAN", Utils.parseLong(SOC_MHSEKATANKEPENTINGAN), DISABLE_FOR_JPPH));
	    	context.put("MH_LUASASAL", MH_LUASASAL);
	    	context.put("MH_JENISLUASASAL", modelPajak.SelectJenisLuasTanah("MH_JENISLUASASAL", Utils.parseLong(SOC_MHJENISLUASASAL), DISABLE_FOR_JPPH));
	    	context.put("MH_LUASDIPAJAK", MH_LUASDIPAJAK);
	    	context.put("MH_JENISLUASDIPAJAK", modelPajak.SelectJenisLuasTanah("MH_JENISLUASDIPAJAK", Utils.parseLong(SOC_MHJENISLUASDIPAJAK), DISABLE_FOR_JPPH));
	    	context.put("MH_STATUSPEMILIKAN", MH_STATUSPEMILIKAN);
	    	context.put("MH_TUJUANPAJAKAN", MH_TUJUANPAJAKAN);
	    	context.put("MH_NILAIPAJAK", MH_NILAIPAJAK);
	        context.put("MH_NILAIPAJAK2", getParam("MH_NILAIPAJAK2"));
	        
	        context.put("MH_PAJAK99", MH_PAJAK99);
	        context.put("MH_PAJAK60", MH_PAJAK60);
	        context.put("MH_PAJAK30", MH_PAJAK30);
	        context.put("MH_PAJAKLAINLAIN", MH_PAJAKLAINLAIN);
	        context.put("MH_PAJAKTAHUNAN", MH_PAJAKTAHUNAN);
	        context.put("MH_PAJAKLAINLAINTAHUN", MH_PAJAKLAINLAINTAHUN);
	        
	    	context.put("MH_CATATAN", MH_CATATAN);
	    	context.put("JPPH_TARIKHNILAIAN", JPPH_TARIKHNILAIAN);
	    	context.put("JPPH_NILAIPAJAK99", JPPH_NILAIPAJAK99);
	    	context.put("JPPH_NILAIPAJAK60", JPPH_NILAIPAJAK60);
	    	context.put("JPPH_NILAIPAJAK30", JPPH_NILAIPAJAK30);
	    	context.put("JPPH_NILAIPAJAKLAINLAIN", JPPH_NILAIPAJAKLAINLAIN);
	    	context.put("JPPH_NILAIPAJAKTAHUNAN", JPPH_NILAIPAJAKTAHUNAN);
	    	context.put("JPPH_NAMAPEGAWAI", JPPH_NAMAPEGAWAI);
	    	context.put("JPPH_CAWANGAN", JPPH_CAWANGAN);
	    	context.put("JPPH_CATATAN", JPPH_CATATAN);
	    	context.put("EMAIL_ADDR1", EMAIL_ADDR1);
	    	context.put("EMAIL_ADDR2", EMAIL_ADDR2);
	    	context.put("EMAIL_ADDR3", EMAIL_ADDR3);
	    	context.put("EMAIL_SEND", EMAIL_SEND);
	    	
	    	//*****************
	    	// htp page
	    	String idFail = modelPajak.getIdFail(ID_PERMOHONAN);
	    	context.put("idPermohonan", ID_PERMOHONAN);
	    	context.put("idFail", idFail);
	    	//*****************

        	if ("changeSOCNegeri".equalsIgnoreCase(command)) {
            	context.put("MH_NEGERI", HTML.SelectNegeri("MH_NEGERI", Utils.parseLong(SOC_MHNEGERI), DISABLE_FOR_JPPH, "onchange=\"doChange('changeSOCNegeri', 'true');\""));
            	context.put("MH_DAERAH", HTML.SelectDaerahByNegeri(SOC_MHNEGERI, "MH_DAERAH", (long) 0, DISABLE_FOR_JPPH, "onchange=\"doChange('changeSOCDaerah', 'true');\""));
            	context.put("MH_MUKIM", HTML.SelectMukim("MH_MUKIM", (long) 0, DISABLE_FOR_JPPH));
            } else if ("changeSOCDaerah".equalsIgnoreCase(command)) {
            	context.put("MH_DAERAH", HTML.SelectDaerahByNegeri(SOC_MHNEGERI, "MH_DAERAH", Utils.parseLong(SOC_MHDAERAH), DISABLE_FOR_JPPH, "onchange=\"doChange('changeSOCAHDaerah', 'true');\""));
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
        context.put("command", command);
        context.put("action2", action);
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
		MH_NOPERSERAHAN = getParam("MH_NOPERSERAHAN");
		MH_NOPAJAKAN = getParam("MH_NOPAJAKAN");
		MH_JENISPEGANGAN = getParam("MH_JENISPEGANGAN");
		MH_TARIKHLUPUTPAJAKAN = getParam("MH_TARIKHLUPUTPAJAKAN");
		MH_TEMPOHPAJAKAN = getParam("MH_TEMPOHPAJAKAN");
		MH_ADAPELANTAPAK = getParam("MH_ADAPELANTAPAK");
		MH_KATEGORITANAH = getParam("MH_KATEGORITANAH");
		MH_SYARATNYATA = getParam("MH_SYARATNYATA");
		MH_SEKATANKEPENTINGAN = getParam("MH_SEKATANKEPENTINGAN");
		MH_LUASASAL = getParam("MH_LUASASAL");
		MH_JENISLUASASAL = getParam("MH_JENISLUASASAL");
		MH_LUASDIPAJAK = getParam("MH_LUASDIPAJAK");
		MH_JENISLUASDIPAJAK = getParam("MH_JENISLUASDIPAJAK");
		MH_STATUSPEMILIKAN = getParam("MH_STATUSPEMILIKAN");
		MH_TUJUANPAJAKAN = getParam("MH_TUJUANPAJAKAN");
		MH_NILAIPAJAK = getParam("MH_NILAIPAJAK");
		MH_NILAIANDIPERLUKAN = getParam("MH_NILAIANDIPERLUKAN");
		MH_TEMPOHNILAIAN = getParam("MH_TEMPOHNILAIAN");
		MH_CATATAN = getParam("MH_CATATAN");
		JPPH_TARIKHNILAIAN = getParam("JPPH_TARIKHNILAIAN");
		JPPH_NILAIPAJAK99 = getParam("JPPH_NILAIPAJAK99");
		JPPH_NILAIPAJAK60 = getParam("JPPH_NILAIPAJAK60");
		JPPH_NILAIPAJAK30 = getParam("JPPH_NILAIPAJAK30");
		JPPH_NILAIPAJAKLAINLAIN = getParam("JPPH_NILAIPAJAKLAINLAIN");
		JPPH_NILAIPAJAKTAHUNAN = getParam("JPPH_NILAIPAJAKTAHUNAN");
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
    	SOC_MHJENISLUASDIPAJAK = getParam("MH_JENISLUASDIPAJAK");     
    }
	
	@SuppressWarnings("rawtypes")
	private void populatePage(Hashtable h) throws Exception {
		if (!h.isEmpty()) {
			ID_HM = (String) h.get("ID_HM");
			MP_NOFAIL = (String) h.get("MP_NOFAIL");
			MP_NEGERI = (String) h.get("MP_NEGERI");
			MP_DAERAH = (String) h.get("MP_DAERAH");
			MP_UNITJKPTG = (String) h.get("MP_UNITJKPTG");
			if (!isJPPHUser && "".equalsIgnoreCase(MP_UNITJKPTG.trim())) {
				MP_UNITJKPTG = FrmModelNilaianHartaTakAlih.getUnitUser(userID);
			}
			MP_NAMAPEGAWAIJKPTG = (String) h.get("MP_NAMAPEGAWAIJKPTG");
			if (!isJPPHUser && "".equalsIgnoreCase(MP_NAMAPEGAWAIJKPTG.trim())) {
				MP_NAMAPEGAWAIJKPTG = userName.toUpperCase().trim();
			}
			MP_NOTELJKPTG = (String) h.get("MP_NOTELJKPTG");
			if (!isJPPHUser && "".equalsIgnoreCase(MP_NOTELJKPTG)) {
				MP_NOTELJKPTG = FrmModelNilaianHartaTakAlih.getNoTelefonUser(userID);
			}
			MP_CAWANGANJKPTG = (String) h.get("MP_CAWANGANJKPTG");
			if (!isJPPHUser && "".equalsIgnoreCase(MP_CAWANGANJKPTG.trim())) {
				MP_CAWANGANJKPTG = FrmModelNilaianHartaTakAlih.getCawanganUserJPPH(userID);
			}
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
			MH_NOPAJAKAN = (String) h.get("MH_NOPAJAKAN");
			MH_NOPERSERAHAN = (String) h.get("MH_NOPERSERAHAN");
			MH_JENISPEGANGAN = (String) h.get("MH_JENISPEGANGAN");
			MH_TARIKHLUPUTPAJAKAN = (String) h.get("MH_TARIKHLUPUTPAJAKAN");
			MH_TEMPOHPAJAKAN = (String) h.get("MH_TEMPOHPAJAKAN");
			MH_ADAPELANTAPAK = (String) h.get("MH_ADAPELANTAPAK");
			MH_KATEGORITANAH = (String) h.get("MH_KATEGORITANAH");
			MH_SYARATNYATA = (String) h.get("MH_SYARATNYATA");
			MH_SEKATANKEPENTINGAN = (String) h.get("MH_SEKATANKEPENTINGAN");
			MH_LUASASAL = (String) h.get("MH_LUASASAL");
			MH_JENISLUASASAL = (String) h.get("MH_JENISLUASASAL");
			MH_LUASDIPAJAK = (String) h.get("MH_LUASDIPAJAK");
			MH_JENISLUASDIPAJAK = (String) h.get("MH_JENISLUASDIPAJAK");
			MH_STATUSPEMILIKAN = (String) h.get("MH_STATUSPEMILIKAN");
			MH_TUJUANPAJAKAN = (String) h.get("MH_TUJUANPAJAKAN");
			MH_NILAIANDIPERLUKAN = (String) h.get("MH_NILAIANDIPERLUKAN");
			MH_TEMPOHNILAIAN = (String) h.get("MH_TEMPOHNILAIAN");
			MH_CATATAN = (String) h.get("MH_CATATAN");
			JPPH_TARIKHNILAIAN = (String) h.get("JPPH_TARIKHNILAIAN");
			JPPH_NILAIPAJAK99 = (String) h.get("JPPH_NILAIPAJAK99");
			JPPH_NILAIPAJAK60 = (String) h.get("JPPH_NILAIPAJAK60");
			JPPH_NILAIPAJAK30 = (String) h.get("JPPH_NILAIPAJAK30");
			JPPH_NILAIPAJAKLAINLAIN = (String) h.get("JPPH_NILAIPAJAKLAINLAIN");
			JPPH_NILAIPAJAKTAHUNAN = (String) h.get("JPPH_NILAIPAJAKTAHUNAN");
			JPPH_NAMAPEGAWAI = (String) h.get("JPPH_NAMAPEGAWAI");
			JPPH_CAWANGAN = (String) h.get("JPPH_CAWANGAN");
			JPPH_CATATAN = (String) h.get("JPPH_CATATAN");
			EMAIL_ADDR1 = (String) h.get("EMAIL_ADDR1");
			EMAIL_ADDR2 = (String) h.get("EMAIL_ADDR2");
			EMAIL_ADDR3 = (String) h.get("EMAIL_ADDR3");
			EMAIL_SEND = (String) h.get("EMAIL_SEND");
			
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
	    	SOC_MHJENISLUASDIPAJAK = MH_JENISLUASDIPAJAK;
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