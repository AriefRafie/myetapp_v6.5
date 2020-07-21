package ekptg.view.php2.online;

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
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.php2.online.FrmPNWOnlineSenaraiFailData;

public class FrmPNWOnlineSenaraiFailView extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(FrmPNWOnlineSenaraiFailView.class);
	FrmPNWOnlineSenaraiFailData logic = new FrmPNWOnlineSenaraiFailData();

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    String userId = (String) session.getAttribute("_ekptg_user_id");
	    	    
	    // DROP DOWN PENDAFTARAN
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = ""; 
        String actionOnline = getParam("actionOnline");
        String submit = getParam("command");   
        
        //GET ID PARAM
        String idFailSession = "";
        if (session.getAttribute("idFail") != null){
        	idFailSession = (String) session.getAttribute("idFail");
        }
        String idFail = getParam("idFail");
        String idStatus = getParam("idStatus");
        String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String userRole = "";
		String userJawatan = "";
		String layerKJP = "";
		String idNegeriPemohon = ""; 
		String idAgensi = ""; 
		String idKementerian = "";
		String idPermohonan = getParam("idPermohonan");
		String idPermohonanPelepasan = getParam("idPermohonanPelepasan");
		String idTanahGanti = getParam("idTanahGanti");
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idPemohon = getParam("idPemohon");
		String idDokumen = getParam("idDokumen");
		
		//VECTOR
        Vector list = null;
        Vector beanMaklumatPermohonan = null;
        Vector beanMaklumatTanah = null;
        Vector listDetailKJP = null;
        Vector beanHeader = null;
        Vector beanMaklumatPenawaran = null;
        Vector beanMaklumatKeputusan = null;
        Vector senaraiAgensi = null;
        Vector beanMaklumatImejan = null;
        Vector senaraiImejan = null;
        
		// GET DROPDOWN PARAM
        String idLuasKegunaan = getParam("socLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
			idLuasKegunaan = "99999";
		}
		String idJenisProjek = getParam("socJenisProjek");
		if (idJenisProjek == null || idJenisProjek.trim().length() == 0){
			idJenisProjek = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
		
		this.context.put("errorPeganganHakmilik", "");

		userRole = logic.getUserRole(userId);
		userJawatan = logic.getUserJawatan(userId);
		
		if ("24".equals(userJawatan)){
			layerKJP = "1";
		} else if ("9".equals(userJawatan)){
			layerKJP = "2";
		} else if ("4".equals(userJawatan)){
			layerKJP = "3";
		} else {
			layerKJP = "1";
		}
		
		this.context.put("userRole", userRole);
		this.context.put("userJawatan", userJawatan);
		this.context.put("layerKJP", layerKJP);
			
		listDetailKJP = logic.getIdNegeriKJPByUserId(userId);
		
		if(!listDetailKJP.isEmpty() && listDetailKJP.size()> 0) {
    		Hashtable hashRayuanDB = (Hashtable) listDetailKJP.get(0);
    		idNegeriPemohon = hashRayuanDB.get("idNegeri").toString();
    		idKementerian= hashRayuanDB.get("idKementerian").toString();
    		idAgensi= hashRayuanDB.get("idAgensi").toString();
    		
		}
		
		this.context.put("idNegeriPemohon", idNegeriPemohon);
		this.context.put("idKementerian", idKementerian);
		this.context.put("idAgensi", idAgensi);	
		this.context.put("onload", "");
		this.context.put("completed", false);
		
		myLog.info("hitButton="+hitButton+",actionOnline="+actionOnline);
		//HITBUTTON
		if (postDB) {
			if (hitButton.equals("doDaftarBaru")){
           		idFail = logic.daftarBaru(userRole,idKementerian,idAgensi, getParam("idHakmilikAgensi"), session);
        	}else if (hitButton.equals("doSimpanKemaskiniMaklumatTnh")){
        		logic.updateTanah(idPermohonan,idHakmilikAgensi,session);	
            }else if (hitButton.equals("doSimpanKemaskiniMaklumatPelepasan")){
        		logic.updatePermohonanPenawaran(idPermohonanPelepasan,
						idLuasKegunaan, idLuas, getParam("txtLuasMohon1"),
						getParam("txtLuasMohon2"), getParam("txtLuasMohon3"),
						getParam("txtLuasBersamaan"), getParam("txtBakiLuas"),
						session);
        	}else if (hitButton.equals("doHantarSemakan")){				
				if (logic.checkMaklumatPenawaranLengkap(idPermohonan)){
    				this.context.put("onload", " \"alert('Masih terdapat maklumat penawaran yang belum lengkap.')\"");	
				} else {
					logic.updatePermohonanSemakan(idPermohonan, idKementerian, session);
				}				
			}else if (hitButton.equals("doHantarKelulusan")){			
				if (logic.checkMaklumatPenawaranLengkap(idPermohonan)){
    				this.context.put("onload", " \"alert('Masih terdapat maklumat penawaran yang belum lengkap.')\"");	
				} else {
					logic.updatePermohonanKelulusan(idPermohonan, idKementerian, session);
				}				
			}else if (hitButton.equals("doHantarEmel")){			
				if (logic.checkMaklumatPenawaranLengkap(idPermohonan)){
    				this.context.put("onload", " \"alert('Masih terdapat maklumat penawaran yang belum lengkap.')\"");	
				} else {
					logic.updatePermohonanEmel(idFail,idPermohonan,session);
				}		
				
			}else if (hitButton.equals("doHapus")){
				logic.hapusPermohonan(idFail);
			}else if (hitButton.equals("simpanDokumen")) {
				uploadFiles(idPermohonan, session);
			}else if (hitButton.equals("simpanKemaskiniDokumen")) {
				logic.simpanKemaskiniDokumen(idDokumen, getParam("txtNamaImej"), getParam("txtCatatanImej"), session);
			}else if (hitButton.equals("hapusDokumen")) {
				logic.hapusDokumen(idDokumen);
			}
			
		}
	
		
		if ("papar".equals(actionOnline)) {
			// GO TO VIEW PENAWARAN
		    vm = "app/php2/online/frmPNWDaftarManual.jsp";
		    
		   	mode="view";
			this.context.put("mode", "view");
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");

			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(logic.getIdHakmilikAgensi(idFail));
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);

		} else if ("daftarBaru".equals(actionOnline)) {
			// GO TO DAFTAR BARU PENAWARAN
			vm = "app/php2/online/frmPNWDaftarManual.jsp";
		
			mode = "new";
			this.context.put("mode", "new");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");

			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noPermohonan", "");
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			//MAKLUMAT HAKMILIK
			if ("doChangePeganganHakmilik".equals(submit)) {
				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(idKementerian, idAgensi, getParam("txtPeganganHakmilik"));
				if (idHakmilikAgensi.isEmpty()) {
					this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
				}
			}
			
			beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(idHakmilikAgensi);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);

		} else if ("seterusnya".equals(actionOnline)){        	
        	// GO TO MAKLUMAT PERMOHONAN  
			vm = "app/php2/online/frmPNWMaklumatPermohonan.jsp";
			
        	//HEADER
            beanHeader = new Vector();
            logic.setMaklumatHeader(idFail);
            beanHeader = logic.getBeanMaklumatHeader();
    		this.context.put("BeanHeader", beanHeader);
    		
    		if (beanHeader.size() != 0){
    			Hashtable hashHeader = (Hashtable) logic.getBeanMaklumatHeader().get(0);
    			idFail = (String) hashHeader.get("idFail");
    			idPermohonan = (String) hashHeader.get("idPermohonan");
    			idStatus = (String) hashHeader.get("idStatus");
    			idHakmilikAgensi = (String) hashHeader.get("idHakmilikAgensi");	
    			idNegeriPemohon = (String) hashHeader.get("idNegeriPemohon");
    		}
    	
        	// MODE VIEW
    		if("view".equals(mode)){
    	
    			this.context.put("readonly", "readonly");
    			this.context.put("inputTextClass", "disabled");
    			this.context.put("disabled", "disabled");
    			
    			if ("0".equals(selectedTabUpper)){
	    			//MAKLUMAT TANAH
	    			beanMaklumatTanah = new Vector();
	    			logic.setMaklumatTanah(idHakmilikAgensi);
	    			beanMaklumatTanah = logic.getBeanMaklumatTanah();
	    			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
    			} else if ("1".equals(selectedTabUpper)){
	    			
    				//MAKLUMAT PENAWARAN
	    			beanMaklumatPenawaran = new Vector();
	    			logic.setMaklumatPenawaran(idPermohonan);
	    			beanMaklumatPenawaran = logic.getBeanMaklumatPenawaran();
	    			this.context.put("BeanMaklumatPenawaran", beanMaklumatPenawaran);
	    			if (beanMaklumatPenawaran.size() != 0){
	        			Hashtable hashMaklumatPenawaran = (Hashtable) logic.getBeanMaklumatPenawaran().get(0);
	        			idPermohonanPelepasan = (String)(hashMaklumatPenawaran.get("idPermohonanPelepasan"));
	            		if (hashMaklumatPenawaran.get("flagGuna") != null && hashMaklumatPenawaran.get("flagGuna").toString().trim().length() != 0){
	            			idLuasKegunaan = (String) hashMaklumatPenawaran.get("flagGuna");
	            		} else {
	            			idLuasKegunaan = "99999";
	            		}
	            		if (hashMaklumatPenawaran.get("idLuasMohon") != null && hashMaklumatPenawaran.get("idLuasMohon").toString().trim().length() != 0){
	            			idLuas = (String) hashMaklumatPenawaran.get("idLuasMohon");
	            		} else {
	            			idLuas = "99999";
	            		}
	        		}
	       			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));	
    			
    			} else if ("2".equals(selectedTabUpper)){
    				
    				//MAKLUMAT KEPUTUSAN
    				beanMaklumatKeputusan = new Vector();
    				logic.setMaklumatKeputusan(idPermohonan);
    				beanMaklumatKeputusan = logic.getBeanMaklumatKeputusan();
    		    	this.context.put("BeanMaklumatKeputusan", beanMaklumatKeputusan);
    		    	
    		    	this.context.put("txtJumlahLuas", logic.getTotalLuas(idPermohonan));
    				
    				//SENARAI AGENSI
    				senaraiAgensi = new Vector();
    				logic.setSenaraiAgensi(idPermohonan);
    				senaraiAgensi = logic.getListAgensi();
    				this.context.put("SenaraiAgensi", senaraiAgensi);
    			}
    		}
    		
    		// MODE UPDATE
    		else if("update".equals(mode)){
    			
    			this.context.put("readonly", "");
        		this.context.put("inputTextClass", "");
        		this.context.put("disabled", "");        		
        		
        		if ("0".equals(selectedTabUpper)){
	        		
        			//MAKLUMAT TANAH
	        		beanMaklumatTanah = new Vector();
	    			logic.setMaklumatTanah(idHakmilikAgensi);
	    			beanMaklumatTanah = logic.getBeanMaklumatTanah();
	    			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
        		 
	        		if ("doChangePeganganHakmilik1".equals(submit)){
	    				beanMaklumatTanah = new Vector();
	    				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(idKementerian, idAgensi, getParam("txtPeganganHakmilik1"));
	    				logic.setMaklumatTanah(idHakmilikAgensi);
	    				beanMaklumatTanah = logic.getBeanMaklumatTanah();
	    				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
	    				this.context.put("idHakmilikAgensi", idHakmilikAgensi);
	    				this.context.put("idKementerian", getParam("idKementerian"));
	    				this.context.put("kodKementerian", getParam("kodKementerian"));

	    				this.context.put("idNegeriTanah", getParam("idNegeriTanah"));
	    				this.context.put("kodNegeriTanah", getParam("kodNegeriTanah"));
	    				if (idHakmilikAgensi.isEmpty()){
	    					this.context.put("errorPeganganHakmilik", "Hakmilik tidak wujud.");
	    				}
	    			} else if ("doChangeMaklumatTanah".equals(submit)){
	    				beanMaklumatTanah = new Vector();
	    				idHakmilikAgensi = getParam("idHakmilikAgensiPopup");
	        			logic.setMaklumatTanah(idHakmilikAgensi);
	        			beanMaklumatTanah = logic.getBeanMaklumatTanah();
	        			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
	    			}
        		
        		} else if ("1".equals(selectedTabUpper)){	        		
        			//MAKLUMAT PENAWARAN
	            	beanMaklumatPenawaran = new Vector();
	        		logic.setMaklumatPenawaran(idPermohonan);
	        		Hashtable hashMaklumatPenawaranDB = (Hashtable) logic.getBeanMaklumatPenawaran().get(0);
	        		
	        		if ("99999".equals(idLuasKegunaan)){
	        			idLuasKegunaan = (String)hashMaklumatPenawaranDB.get("flagGuna");
	        			if(!"".equals(idLuasKegunaan)){
	        				idLuasKegunaan = (String)hashMaklumatPenawaranDB.get("flagGuna");
	        			}
	        			else {
	        				idLuasKegunaan = "99999";
	        			}
	        			
	        		}
	        		if ("99999".equals(idLuas)){
	        			idLuas = (String)hashMaklumatPenawaranDB.get("idLuasMohon");
	        			if(!"".equals(idLuasKegunaan)){
	        				idLuas= (String)hashMaklumatPenawaranDB.get("idLuasMohon");
	        			}
	        			else {
	        				idLuas = "99999";
	        			}        			        	
	        		}        		
	        		
	    			Hashtable hashMaklumatPenawaran = new Hashtable();
	    			hashMaklumatPenawaran.put("tarikhTerima", getParam("tarikhTerima"));
	    			hashMaklumatPenawaran.put("tarikhSurat", getParam("tarikhSurat"));
	    			hashMaklumatPenawaran.put("perkara", getParam("txtPerkara"));
	    			hashMaklumatPenawaran.put("luasAsal", hashMaklumatPenawaranDB.get("luasAsal"));
	    			hashMaklumatPenawaran.put("keteranganLuasAsal", hashMaklumatPenawaranDB.get("keteranganLuasAsal"));			
	    			if ("doChangeLuas".equals(submit)){
	    				hashMaklumatPenawaran.put("luas1", "");
	    				hashMaklumatPenawaran.put("luas2", "");
	    				hashMaklumatPenawaran.put("luas3", "");
	    				hashMaklumatPenawaran.put("luasBersamaan", "");
	    				hashMaklumatPenawaran.put("luasBaki", "");
	    			} else {
	    				hashMaklumatPenawaran.put("luas1", getParam("txtLuasMohon1"));
	    				hashMaklumatPenawaran.put("luas2", getParam("txtLuasMohon2"));
	    				hashMaklumatPenawaran.put("luas3", getParam("txtLuasMohon3"));
	    				if ("1".equals(idLuasKegunaan)){
	    					hashMaklumatPenawaran.put("luasBersamaan",  hashMaklumatPenawaranDB.get("luasAsal"));		
	    					hashMaklumatPenawaran.put("luasBaki", Utils.formatLuas(0D));
	    				} else {
	    					hashMaklumatPenawaran.put("luasBersamaan", getParam("txtLuasBersamaan"));			
	    					hashMaklumatPenawaran.put("luasBaki", getParam("txtBakiLuas"));		
	    				}
	    			}
	    			beanMaklumatPenawaran.addElement(hashMaklumatPenawaran);
	               	this.context.put("BeanMaklumatPenawaran", beanMaklumatPenawaran);
	            	
	        		this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", " onChange=\"doChangeLuasKegunaan()\" style=\"width:auto\""));
	    		
        		} 
    		}    		
   			
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
			logic.setSenaraiImejan(idPermohonan);
			senaraiImejan = logic.getListImejan();
			this.context.put("SenaraiImejan", senaraiImejan);
				
        }
		else{	
			
		
		String jenisHakmilik = getParam("socJenisHakmilik");
		if (jenisHakmilik == null || jenisHakmilik.trim().length() == 0){
			jenisHakmilik = "99999";
		}
		String jenisLot = getParam("socJenisLot");
		if (jenisLot == null || jenisLot.trim().length() == 0){
			jenisLot = "99999";
		}
		String idNegeriC = getParam("socNegeriC");
		if (idNegeriC == null || idNegeriC.trim().length() == 0){
			idNegeriC = "99999";
		}
		String idDaerahC = getParam("socDaerahC");
		if (idDaerahC == null || idDaerahC.trim().length() == 0){
			idDaerahC = "99999";
		}
		String idMukimC = getParam("socMukimC");
		if (idMukimC == null || idMukimC.trim().length() == 0){
			idMukimC = "99999";
		}
		
		String idStatusC = getParam("socStatusC");
		if (idStatusC == null || idStatusC.trim().length() == 0){
			idStatusC = "99999";
		} 
		String flagDetail = getParam("flagDetail");
		
    	//GO TO LIST FAIL PELEPASAN        	
    	vm = "app/php2/online/frmPNWSenaraiFail.jsp";
    	
    	logic.carianFail(userId,userRole,getParam("txtNoFail"),getParam("txtNoPermohonan"),getParam("txdTarikhTerima"),idNegeriC,idDaerahC,idMukimC,
    			jenisHakmilik,getParam("txtNoHakmilik"),getParam("txtNoWarta"),jenisLot,getParam("txtNoLot"),getParam("txtNoPegangan"),idStatusC);
		list = new Vector();
		list = logic.getSenaraiFail();
		this.context.put("SenaraiFail", list);
		
		this.context.put("txtNoFail", getParam("txtNoFail"));
		this.context.put("txtNoPermohonan", getParam("txtNoPermohonan"));
		this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));		
		
		this.context.put("txtNoPegangan", getParam("txtNoPegangan"));
    	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(jenisHakmilik), ""));
    	this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
    	this.context.put("txtNoWarta", getParam("txtNoWarta"));
		this.context.put("selectLot", HTML.SelectLot("socJenisLot",Long.parseLong(jenisLot), ""));
		this.context.put("txtNoLot", getParam("txtNoLot"));
		this.context.put("selectNegeri",HTML.SelectNegeri("socNegeriC", Long.parseLong(idNegeriC), "", " onChange=\"doChangeNegeri();\""));
		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Long.parseLong(idDaerahC), "", " onChange=\"doChangeDaerah();\""));
		this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Long.parseLong(idMukimC), ""));
		
		this.context.put("flagDetail", flagDetail);
		setupPage(session, action, list);
    }
    
    //SET DEFAULT PARAM
	this.context.put("actionOnline", actionOnline);
	this.context.put("mode", mode);
	this.context.put("flagPopup", flagPopup);
	this.context.put("modePopup", modePopup);
	this.context.put("selectedTabUpper", selectedTabUpper);
	
	//SET DEFAULT ID PARAM
	this.context.put("idFail", idFail);
	this.context.put("idStatus", idStatus);
	this.context.put("idPermohonan", idPermohonan);
    this.context.put("idPermohonanPelepasan", idPermohonanPelepasan);
    this.context.put("idTanahGanti", idTanahGanti);
    this.context.put("idLuasKegunaan", idLuasKegunaan);
    this.context.put("idLuas", idLuas);
    this.context.put("idNegeriPemohon", idNegeriPemohon);  
    this.context.put("idHakmilikAgensi", idHakmilikAgensi);
    this.context.put("idPemohon", idPemohon);
    this.context.put("idDokumen", idDokumen);
    
	return vm;
}

	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10: getParamAsInteger("itemsPerPage");
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

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("SenaraiFail", paging.getPage(page));
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
	
	// UPLOAD FILE
	private void uploadFiles(String idPermohonan, HttpSession session) throws Exception {
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
					saveData(item, idPermohonan, session);
				}
			}
		}
	}


	private void saveData(FileItem item, String idPermohonan, HttpSession session) throws Exception {

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
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,ID_PERMOHONAN,FLAG_DOKUMEN) "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, getParam("namaImej"));
			ps.setString(3, getParam("catatanImej"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idPermohonan);
			ps.setString(9, "L");
			ps.executeUpdate();

			con.commit();
			
		} finally {
			if (db != null)
				db.close();
		}
		this.context.put("completed", true);
	}
}

