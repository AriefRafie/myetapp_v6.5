	package ekptg.view.php2.online;

	import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmPLPJabatanTeknikalData;
import ekptg.model.php2.FrmPYWHeaderData;
import ekptg.model.php2.FrmPYWJabatanTeknikalData;
import ekptg.model.php2.FrmPYWMaklumatPermohonanData;
import ekptg.model.php2.online.FrmPYWOnlineKJPSenaraiFailData;

	public class FrmPYWOnlineMaklumat extends AjaxBasedModule {
		
		private static final long serialVersionUID = 1L;
		
		FrmPYWHeaderData logicHeader = new FrmPYWHeaderData();
		FrmPYWMaklumatPermohonanData logic = new FrmPYWMaklumatPermohonanData();
		FrmPYWJabatanTeknikalData logicKJP = new FrmPYWJabatanTeknikalData();
		FrmPLPJabatanTeknikalData logicData = new FrmPLPJabatanTeknikalData();
		FrmPYWOnlineKJPSenaraiFailData logicDataKJP = new FrmPYWOnlineKJPSenaraiFailData();
		
		String userId = null;
		String userRole = null;
		String idNegeriUser = null;
		
		@Override
		public String doTemplate2() throws Exception {

			HttpSession session = this.request.getSession();

			Boolean postDB = false;
			String doPost = (String) session.getAttribute("doPost");
			if (doPost.equals("true")) {
				postDB = true;
			}
		    
		    //GET DEFAULT PARAM
		    String submit = getParam("command");  
		    String vm = ""; 
	        String mode = getParam("mode");
	        if (mode.isEmpty()){
	        	mode = "view";
	        }
	        
	        userId = (String)session.getAttribute("_ekptg_user_id");
			//userRole = (String)session.getAttribute("myrole");
			//idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");
	        
	        String selectedTabUpper = (String) getParam("selectedTabUpper");
			if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
				selectedTabUpper = "0";
			}
			String hitButton = getParam("hitButton");
			String flagPopup = getParam("flagPopup");
			String modePopup = getParam("modePopup");
			String flagReKeyin = "";
	        
			//GET ID PARAM
	        String idFail = getParam("idFail");
	        String idPermohonan = getParam("idPermohonan");
	        String idSuburusan = getParam("idSuburusan");
	        String idPemohon = getParam("idPemohon");
	        String idStatus = getParam("idStatus");
	        String idHakmilikAgensi = getParam("idHakmilikAgensi");
	        String idHakmilik = getParam("idHakmilik");
	        String idPermohonanSewa = getParam("idPermohonanSewa");
	        String idNegeriPemohon = getParam("idNegeriPemohon");
	        String flagBorangK = getParam("flagBorangK");
	        String idBorangK = getParam("flagBorangK");
	        String idKementerianTanah = getParam("idKementerian");
	        String idAgensiTanah = getParam("idAgensi");
	        String idPPTBorangK = getParam("idPPTBorangK");
	        String idHakmilikUrusan = getParam("idHakmilikUrusan");
	        String idPHPBorangK = getParam("idPHPBorangK");
	        String idUlasanTeknikal = getParam("idUlasanTeknikal");
	        String idJenisTanah = getParam("idJenisTanah");
	        String flagStatus = getParam("flagStatus");
	        String aktif = getParam("aktif");
	        String flagFrom = getParam("flagFrom");
	        
	        //GET DROPDOWN PARAM
	        String idLuasKegunaan = getParam("socLuasKegunaan");
			if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
				idLuasKegunaan = "99999";
			}
			String idLuas = getParam("socLuas");
			if (idLuas == null || idLuas.trim().length() == 0){
				idLuas = "99999";
			}
	        String idKategoriPemohon = getParam("socKategoriPemohon");
			if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0) {
				idKategoriPemohon = "99999";
			}
			String idNegeri = getParam("socNegeri");
			if (idNegeri == null || idNegeri.trim().length() == 0) {
				idNegeri = "99999";
			}
			String idBandar = getParam("socBandar");
			if (idBandar == null || idBandar.trim().length() == 0) {
				idBandar = "99999";
			}
			String idKementerianKJP = getParam("idKementerian");
			if (idKementerianKJP == null || idKementerianKJP.trim().length() == 0){
				idKementerianKJP = "99999";
			}
			String idAgensiKJP = getParam("idAgensi");
			if (idAgensiKJP == null || idAgensiKJP.trim().length() == 0){
				idAgensiKJP = "99999";
			}
			String idKementerian = getParam("idKementerian");
			if (idKementerian == null || idKementerian.trim().length() == 0){
				idKementerian = "99999";
			}
			String idAgensi = getParam("idAgensi");
			if (idAgensi == null || idAgensi.trim().length() == 0){
				idAgensi = "99999";
			}
			
			//VECTOR
			Vector list = null;
	        Vector beanHeader = null;
	        Vector beanMaklumatTanah = null;
	        Vector beanMaklumatBorangK = null;
	        Vector beanMaklumatSewa = null;
	        Vector beanMaklumatPemohon = null;
	        Vector senaraiPengarah = null;
	        Vector senaraiTanahBerkaitan = null;
	        Vector beanMaklumatKJP = null;
			Vector senaraiKJP = null;
			Vector listKJP = null;
	        
	        String step = getParam("step");
	        
	        vm = "app/php2/online/frmPYWMaklumat.jsp";

			if(hitButton.equals("refresh"))
	        	hitButton = null;			
	        
	        //SUBMIT TO DB
//	        if (postDB) {
	        	if ("doSimpanKemaskiniMaklumatSewa".equals(hitButton)) {
					logic.updatePermohonanSewa(idFail, idPermohonan,
							getParam("tarikhTerima"), getParam("tarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtNoFailNegeri"),
							getParam("txtPerkara"), idPermohonanSewa,
							getParam("txtTujuan"), getParam("socTempohSewa"), idLuasKegunaan,
							idLuas, getParam("txtLuasMohon1"),
							getParam("txtLuasMohon2"), getParam("txtLuasMohon3"),
							getParam("txtLuasBersamaan"), getParam("txtBakiLuas"),
							session);
				}
	        	if ("doSimpanKemaskiniMaklumatPemohon".equals(hitButton)) {
					logic.updatePemohon(idPemohon, idKategoriPemohon,
							getParam("txtNama"), getParam("txtNamaPegawai"), getParam("txtNoPendaftaran"),
							getParam("txtAlamat1"), getParam("txtAlamat2"),
							getParam("txtAlamat3"), getParam("txtPoskod"),
							idBandar, idNegeri, getParam("txtEmel"),
							getParam("txtNoTel"), getParam("txtNoFaks"),
							getParam("txtModalBenar"), getParam("txtModalJelas"), session);
				}
	        	if ("doSeterusnya".equals(hitButton)){
	        		logic.updateStatus(idFail, idPermohonan, idSuburusan,null, session);
	        	}
	        	if ("doBatalPermohonan".equals(hitButton)){
	    			logic.doBatalPermohonan(idFail, idPermohonan, idSuburusan, getParam("tarikhBatal"), getParam("txtSebab"), session);
	    			step = "";
	    		}
	        	if ("doHapus".equals(hitButton)){
	        		logic.doHapus(idPermohonan, getParam("idHakmilikPermohonan"), session);
	    		}
	        	if ("simpanMaklumatKJP".equals(hitButton)){
	        		idUlasanTeknikal = logicKJP.simpanMaklumatKJP(idPermohonan, idKementerianKJP, idAgensiKJP, getParam("txtTarikhHantar"), 
	        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
	    		}
	        	if ("simpanMaklumatUlanganKJP".equals(hitButton)){
	        		idUlasanTeknikal = logicKJP.simpanMaklumatUlanganKJP(idUlasanTeknikal, idPermohonan, idKementerianKJP, idAgensiKJP, getParam("txtTarikhHantar"), 
	        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
	    		}
	        	if ("simpanKemaskiniMaklumatKJP".equals(hitButton)){
	        		logicKJP.simpanKemaskiniMaklumatKJP(idUlasanTeknikal, idKementerianKJP, idAgensiKJP, getParam("txtTarikhHantar"), 
	        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
	        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"), getParam("socKeputusan"), session);
	    		}
//	        }    			

	        //HEADER
	        beanHeader = new Vector();
	        logicHeader.setMaklumatPermohonan(idFail, getParam("initiateFlagBuka"), session);
	        beanHeader = logicHeader.getBeanMaklumatPermohonan();
			this.context.put("BeanHeader", beanHeader);
			if (logicHeader.getBeanMaklumatPermohonan().size() != 0){
				Hashtable hashPermohonan = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
				idFail = (String) hashPermohonan.get("idFail");
				idPermohonan = (String) hashPermohonan.get("idPermohonan");
				idPemohon = (String) hashPermohonan.get("idPemohon");
				idStatus = (String) hashPermohonan.get("idStatus");
				idSuburusan = (String) hashPermohonan.get("idSuburusan");
			}
			
			String flagMT = logic.getFlagMT(idFail, userId);
			this.context.put("flagMT", flagMT);
			
			//MAKLUMAT HAKMILIK
			String statusRizab = "";
			logicHeader.setMaklumatHakmilik(logicHeader.getIdHakmilikPermohonanByIdFail(idFail));
			if (logicHeader.getBeanMaklumatHakmilik().size() != 0){
				Hashtable hashHakmilik = (Hashtable) logicHeader.getBeanMaklumatHakmilik().get(0);
				idHakmilik = logicHeader.getIdHakmilikByIdHakmilikPermohonan((String) hashHakmilik.get("idHakmilikPermohonan"));
				if ("".equals((String) hashHakmilik.get("noHakmilik"))){
					statusRizab = "RIZAB";
				} else {
					statusRizab = "MILIK";
				}
				flagBorangK = (String) hashHakmilik.get("flagBorangK");
				
			}
			this.context.put("flagBorangK", flagBorangK);
			
			if ("Y".equals(flagBorangK)){
				beanMaklumatBorangK = new Vector();
				beanMaklumatBorangK = logicHeader.getBeanMaklumatHakmilik();
				this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
			} else {
				beanMaklumatTanah = new Vector();
				beanMaklumatTanah = logicHeader.getBeanMaklumatHakmilik();
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			}	
			
			this.context.put("flagPopup", "");
			this.context.put("modePopup", "");
			
	        //VIEW MODE
	        if ("view".equals(mode)){

	        	this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				this.context.put("disabled", "disabled");
	        			
				//MAKLUMAT SEWA
				beanMaklumatSewa = new Vector();
				logic.setMaklumatSewa(idPermohonan);
				beanMaklumatSewa = logic.getBeanMaklumatSewa();
				this.context.put("BeanMaklumatSewa", beanMaklumatSewa);
				if (beanMaklumatSewa.size() != 0){
	    			Hashtable hashMaklumatSewa = (Hashtable) logic.getBeanMaklumatSewa().get(0);
	    			idPermohonanSewa = (String)(hashMaklumatSewa.get("idPermohonanSewa"));
	        		if (hashMaklumatSewa.get("flagGuna") != null && hashMaklumatSewa.get("flagGuna").toString().trim().length() != 0){
	        			idLuasKegunaan = (String) hashMaklumatSewa.get("flagGuna");
	        		} else {
	        			idLuasKegunaan = "99999";
	        		}
	        		if (hashMaklumatSewa.get("idLuasMohon") != null && hashMaklumatSewa.get("idLuasMohon").toString().trim().length() != 0){
	        			idLuas = (String) hashMaklumatSewa.get("idLuasMohon");
	        		} else {
	        			idLuas = "99999";
	        		}
	    		}
				
				this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));
	    		
				// MAKLUMAT PEMOHON
				logic.setMaklumatPemohon(idPemohon);
				beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
				this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);			
				if (logic.getBeanMaklumatPemohon().size() != 0){
					Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
					idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
					idNegeri = (String) hashPemohon.get("idNegeri");
					idBandar = (String) hashPemohon.get("idBandar");
				}

				this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
				this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
	  		
				//SENARAI PENGARAH
				senaraiPengarah = new Vector();
				logic.setSenaraiPengarah(idPemohon);
				senaraiPengarah = logic.getListPengarah();
				this.context.put("SenaraiPengarah", senaraiPengarah);	
				
				//SENARAI TANAH BERKAITAN
				senaraiTanahBerkaitan = new Vector();
				logic.setSenaraiTanahBerkaitan(idPermohonan);
				senaraiTanahBerkaitan = logic.getListTanahBerkaitan();
				this.context.put("SenaraiTanahBerkaitan", senaraiTanahBerkaitan);

	        } else if ("update".equals(mode)){ //UPDATE MODE
	        	
	        	this.context.put("readonly", "");
	    		this.context.put("inputTextClass", "");
	    		this.context.put("disabled", "");
	        	
	    		//MAKLUMAT PENYEWAAN
	        	beanMaklumatSewa = new Vector();
	    		logic.setMaklumatSewa(idPermohonan);
	    		Hashtable hashMaklumatSewaDB = (Hashtable) logic.getBeanMaklumatSewa().get(0);
				Hashtable hashMaklumatSewa = new Hashtable();
				hashMaklumatSewa.put("tarikhTerima", getParam("tarikhTerima"));
				hashMaklumatSewa.put("tarikhSurat", getParam("tarikhSurat"));
				hashMaklumatSewa.put("noRujukanSurat", getParam("txtNoRujukanSurat"));
				hashMaklumatSewa.put("perkara", getParam("txtPerkara"));
				hashMaklumatSewa.put("tujuan", getParam("txtTujuan"));
				hashMaklumatSewa.put("catatan", getParam("txtCatatan"));
				hashMaklumatSewa.put("luasAsal", hashMaklumatSewaDB.get("luasAsal"));
				hashMaklumatSewa.put("keteranganLuasAsal", hashMaklumatSewaDB.get("keteranganLuasAsal"));			
				if ("doChangeLuas".equals(submit)){
					hashMaklumatSewa.put("luas1", "");
					hashMaklumatSewa.put("luas2", "");
					hashMaklumatSewa.put("luas3", "");
					hashMaklumatSewa.put("luasBersamaan", "");
					hashMaklumatSewa.put("luasBaki", "");
				} else {
					hashMaklumatSewa.put("luas1", getParam("txtLuasMohon1"));
					hashMaklumatSewa.put("luas2", getParam("txtLuasMohon2"));
					hashMaklumatSewa.put("luas3", getParam("txtLuasMohon3"));
					if ("1".equals(idLuasKegunaan)){
						hashMaklumatSewa.put("luasBersamaan",  hashMaklumatSewaDB.get("luasAsal"));		
						hashMaklumatSewa.put("luasBaki", Utils.formatLuas(0D));
					} else {
						hashMaklumatSewa.put("luasBersamaan", getParam("txtLuasBersamaan"));			
						hashMaklumatSewa.put("luasBaki", getParam("txtBakiLuas"));		
					}
				}
				hashMaklumatSewa.put("flagTempohSewa", getParam("socTempohSewa"));
				beanMaklumatSewa.addElement(hashMaklumatSewa);
	           	this.context.put("BeanMaklumatSewa", beanMaklumatSewa);
	        
	    		this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", " onChange=\"doChangeLuasKegunaan()\" style=\"width:auto\""));        	       	

	        	//MAKLUMAT PEMOHON
	    		beanMaklumatPemohon = new Vector();
				Hashtable hashPemohon = new Hashtable();
				hashPemohon.put("nama", getParam("txtNama") == null ? "": getParam("txtNama"));
				hashPemohon.put("namaPegawai", getParam("txtNamaPegawai") == null ? "": getParam("txtNamaPegawai"));
				hashPemohon.put("noPendaftaran",getParam("txtNoPendaftaran") == null ? "": getParam("txtNoPendaftaran"));
				hashPemohon.put("pekerjaan",getParam("txtPekerjaan") == null ? "": getParam("txtPekerjaan"));
				hashPemohon.put("alamat1", getParam("txtAlamat1") == null ? "": getParam("txtAlamat1"));
				hashPemohon.put("alamat2", getParam("txtAlamat2") == null ? "": getParam("txtAlamat2"));
				hashPemohon.put("alamat3", getParam("txtAlamat3") == null ? "": getParam("txtAlamat3"));
				hashPemohon.put("poskod", getParam("txtPoskod") == null ? "": getParam("txtPoskod"));
				hashPemohon.put("emel", getParam("txtEmel") == null ? "": getParam("txtEmel"));
				hashPemohon.put("noTel", getParam("txtNoTel") == null ? "": getParam("txtNoTel"));
				hashPemohon.put("noFaks", getParam("txtNoFaks") == null ? "": getParam("txtNoFaks"));
				hashPemohon.put("modalJelas", getParam("txtModalJelas") == null ? "": getParam("txtModalJelas"));
				hashPemohon.put("modalBenar", getParam("txtModalBenar") == null ? "": getParam("txtModalBenar"));
				beanMaklumatPemohon.addElement(hashPemohon);
				this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
				
				this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon",Long.parseLong(idKategoriPemohon), ""," onChange=\"doChangeKategori();\""));
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
				this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));			
	        }
	        
	        if ("batalPermohonan".equals(step)){
	        	vm = "app/php2/frmBatalPermohonan.jsp";
	        }
	        
	        //OPEN POPUP DETAIL MAKLUMAT KJP
			if ("openKJP".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				beanMaklumatKJP = new Vector();    			
		    			Hashtable hashMaklumatKJP = new Hashtable();
						hashMaklumatKJP.put("kementerian", logicData.getKementerianByIdKementerian(idKementerianKJP));
		    			hashMaklumatKJP.put("agensi", logicData.getAgensiByIdAgensi(idAgensiKJP));
		    			hashMaklumatKJP.put("tarikhHantar", "");
		    			hashMaklumatKJP.put("jangkamasa", "");
		    			hashMaklumatKJP.put("tarikhJangkaTerima", "");

		    			beanMaklumatKJP.addElement(hashMaklumatKJP);
						this.context.put("BeanMaklumatKJP", beanMaklumatKJP);
						
						/*idKementerianKJP = idKementerianTanah;
						idAgensiKJP = idAgensiTanah;*/
						
	    			} else {
	    				beanMaklumatKJP = new Vector();    			
		    			Hashtable hashMaklumatKJP = new Hashtable();
						hashMaklumatKJP.put("kementerian", logicData.getKementerianByIdKementerian(idKementerianKJP));
		    			hashMaklumatKJP.put("agensi", logicData.getAgensiByIdAgensi(idAgensiKJP));
		    			hashMaklumatKJP.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatKJP.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatKJP.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatKJP.addElement(hashMaklumatKJP);
						this.context.put("BeanMaklumatKJP", beanMaklumatKJP);
	    			}
	    			
	    			/*this.context.put("selectKementerianKJP", HTML.SelectKementerian("socKementerianKJP", Utils.parseLong(idKementerianKJP), "", " onChange=\"doChangeKementerian();\""));
					this.context.put("selectAgensiKJP", HTML.SelectAgensiByKementerian("socAgensiKJP", idKementerianKJP, Utils.parseLong(idAgensiKJP), "", ""));*/
					
				}  else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
	    			
	    			beanMaklumatKJP = new Vector();
	    			logicKJP.setMaklumatKJP(idUlasanTeknikal, logicDataKJP.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
					beanMaklumatKJP = logicKJP.getBeanMaklumatKJP();
					this.context.put("BeanMaklumatKJP",beanMaklumatKJP);
					
					if (beanMaklumatKJP.size() != 0){
						Hashtable hashMaklumatKJP = (Hashtable) logicKJP.getBeanMaklumatKJP().get(0);
						flagStatus = (String) hashMaklumatKJP.get("flagStatus");
						aktif = (String) hashMaklumatKJP.get("aktif");
						idKementerianKJP = (String) hashMaklumatKJP.get("idKementerianKJP");
						idAgensiKJP = (String) hashMaklumatKJP.get("idAgensiKJP");
						System.out.println("**** beanMaklumatKJP VIEW 2 --- "+beanMaklumatKJP);
					}
					
					/*this.context.put("selectKementerianKJP", HTML.SelectKementerian("socKementerianKJP", Utils.parseLong(idKementerianKJP), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensiKJP", HTML.SelectAgensiByKementerian("socAgensiKJP", idKementerianKJP, Utils.parseLong(idAgensiKJP), "disabled", " class=\"disabled\""));*/
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatKJP = new Vector();    			
	    			Hashtable hashMaklumatKJP = new Hashtable();
					hashMaklumatKJP.put("kementerian", logicData.getKementerianByIdKementerian(idKementerianKJP));
	    			hashMaklumatKJP.put("agensi", logicData.getAgensiByIdAgensi(idAgensiKJP));
	    			hashMaklumatKJP.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatKJP.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatKJP.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
	    			hashMaklumatKJP.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatKJP.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatKJP.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatKJP.put("ulasan", getParam("txtUlasan"));
	    			hashMaklumatKJP.put("flagKeputusan", getParam("socKeputusan"));
	    			beanMaklumatKJP.addElement(hashMaklumatKJP);
					this.context.put("BeanMaklumatKJP", beanMaklumatKJP);
					
					/*if ("1".equals(flagStatus)){
						this.context.put("selectKementerianKJP", HTML.SelectKementerian("socKementerianKJP", Utils.parseLong(idKementerianKJP), "", " onChange=\"doChangeKementerian();\""));
						this.context.put("selectAgensiKJP", HTML.SelectAgensiByKementerian("socAgensiKJP", idKementerianKJP, Utils.parseLong(idAgensiKJP), "", ""));
						
					} else {
						this.context.put("selectKementerianKJP", HTML.SelectKementerian("socKementerianKJP", Utils.parseLong(idKementerianKJP), "disabled", " class=\"disabled\""));
						this.context.put("selectAgensiKJP", HTML.SelectAgensiByKementerian("socAgensiKJP", idKementerianKJP, Utils.parseLong(idAgensiKJP), "disabled", " class=\"disabled\""));
					}*/	
				}
			}
			
			//DOKUMEN KJP
			listKJP = new Vector();
			logicKJP.setSenaraiKJP(idPermohonan);
			listKJP = logicKJP.getListKJP();
			this.context.put("listKJP", listKJP);	
			System.out.println("*** listKJP ---"+listKJP);
	        
	        //SET DEFAULT PARAM
			this.context.put("mode", mode);
			this.context.put("flagPopup", flagPopup);
			this.context.put("modePopup", modePopup);
			this.context.put("selectedTabUpper", selectedTabUpper);
			this.context.put("flagFrom", flagFrom);
			this.context.put("flagStatus", flagStatus);
			
	        //SET ID PARAM
			this.context.put("idFail", idFail);
	        this.context.put("idPermohonan", idPermohonan);
	        this.context.put("idSuburusan", idSuburusan);
	        this.context.put("idPermohonanSewa", idPermohonanSewa);
	        this.context.put("idPemohon", idPemohon);
	        this.context.put("idStatus", idStatus);
	        this.context.put("idHakmilik", idHakmilik);
	        this.context.put("idHakmilikAgensi", idHakmilikAgensi);
	        this.context.put("idLuasKegunaan", idLuasKegunaan);
	        this.context.put("idLuas", idLuas);
	        this.context.put("idKategoriPemohon", idKategoriPemohon);
	        
	        this.context.put("idNegeriPemohon", idNegeriPemohon);   
	        this.context.put("statusRizab",statusRizab);  
	        this.context.put("flagBorangK",flagBorangK);
	        this.context.put("idBorangK",idBorangK);
	        
	        this.context.put("idPPTBorangK", idPPTBorangK);
	        this.context.put("idPHPBorangK", idPHPBorangK);
	        this.context.put("idHakmilikUrusan", idHakmilikUrusan);
	        
	        this.context.put("idJenisTanah", idJenisTanah);
	        this.context.put("idUlasanTeknikal", idUlasanTeknikal);
	        this.context.put("idKementerianTanah", idKementerianTanah);
	        this.context.put("idAgensiTanah", idAgensiTanah);
	        
	        this.context.put("flagStatus", flagStatus);
	        this.context.put("aktif", aktif);
			
			this.context.put("idKementerian", idKementerian);
	        this.context.put("idAgensi", idAgensi);
	       
	        this.context.put("step",step);
	        
	        /*if (!"".equals(getParam("flagFrom"))){
	        	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
	        }*/
			
			hitButton = null;
			this.context.put("hitButton",hitButton);
			System.out.println("vm "+vm);
	        
			return vm;
		}
	}
