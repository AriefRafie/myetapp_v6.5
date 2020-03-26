package ekptg.view.php2.online;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmPYWHeaderData;
import ekptg.model.php2.online.FrmPYWOnlineSenaraiFailData;
import ekptg.model.php2.utiliti.PHPUtilHTML;
import ekptg.view.ppt.FrmBantahanSenaraiOnline;

public class FrmPYWOnlineSenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmBantahanSenaraiOnline.class);

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		FrmPYWOnlineSenaraiFailData logic = new FrmPYWOnlineSenaraiFailData();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		String id_user = (String) session.getAttribute("_ekptg_user_id");
		String vm = "";
		
		String command = getParam("command");
		
		String flag_penyewaan = getParam("flag_penyewaan");
		this.context.put("flag_penyewaan", flag_penyewaan);
		
		String nModul = getParam("namamodul");
		this.context.put("nModul", nModul);
		
		String nTab = getParam("namatab");
		this.context.put("nTab", nTab);
		
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP
											// PAGING SHJ
		String submit = getParam("command");
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idFail = getParam("idFail");
		String actionPenyewaan = getParam("actionPenyewaan");
		String idStatus = getParam("idStatus");
		String hitButton = getParam("hitButton");
		String idPermohonan = getParam("idPermohonan");
		String idHakmilikSementara = getParam("idHakmilikSementara");
		String idPHPBorangK = getParam("idPHPBorangK");
		String idHakmilikUrusan = getParam("idHakmilikUrusan");
		String idPPTBorangK = getParam("idPPTBorangK");

		
		String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String idPermohonanSewa = getParam("idPermohonanSewa");
        		
		myLogger.info("actionPenyewaan="+actionPenyewaan+",submit="+submit+",mode="+mode);
		Vector list = null;
		Vector beanMaklumatPermohonan = null;
		Vector beanMaklumatTanah = null;
		Vector beanHeader = null;
		Vector beanMaklumatSewa = null;
		Vector beanMaklumatBorangK = null;
		
		String idUrusan = getParam("socUrusan");
		if (idUrusan == null || idUrusan.trim().length() == 0) {
			idUrusan = "99999";
		}
		String idSuburusan = getParam("socSuburusan");
		if (idSuburusan == null || idSuburusan.trim().length() == 0) {
			idSuburusan = "99999";
		}
		String idLuasKegunaan = getParam("socLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
			idLuasKegunaan = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
		String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
		}
		/*String idJenisTujuan = getParam("socJenisTujuan");
		if (idJenisTujuan == null || idJenisTujuan.trim().length() == 0) {
			idJenisTujuan = "99999";
		}*/

		this.context.put("errorPeganganHakmilik", "");
		this.context.put("onload", "");
		
		//DATE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
				
		//SAVE TO DB
		if (postDB) {
			if ("doDaftarBaru".equals(hitButton)) {
				idFail = logic.daftarBaru(idUrusan, idSuburusan, idHakmilikAgensi, getParam("txtperkara"), 
				getParam("txtNoRujukanSurat"), getParam("txttarikhSurat"), idJenisTanah, idPHPBorangK, idPPTBorangK,
				getParam("idKementerianTanah"), getParam("idNegeriTanah"), idHakmilikUrusan, getParam("tarikhTerima"),
				session);
			}
			if ("doSimpanKemaskiniMaklumatTnh".equals(hitButton)){
        		logic.updateTanah(idPermohonan,idHakmilikAgensi,session);	
            }
			if ("doSimpanKemaskiniMaklumatPenyewaan".equals(hitButton)){
        		logic.updatePermohonanSewa(idPermohonanSewa,
						getParam("txtTujuan"), getParam("socTempohSewa"), idLuasKegunaan,
						idLuas, getParam("txtLuasMohon1"),
						getParam("txtLuasMohon2"), getParam("txtLuasMohon3"),
						getParam("txtLuasBersamaan"), getParam("txtBakiLuas"),
						session);
        	}
			if ("doHantarEmel".equals(hitButton)){
				
				if (logic.checkMaklumatPywLengkap(idPermohonan)){
    				this.context.put("onload", " \"alert('Masih terdapat maklumat penyewaan yang belum lengkap.')\"");	
				} else {
					logic.updatePermohonanEmel(idFail,idPermohonan,session);
				}				
			}
			if ("doHapus".equals(hitButton)){
				logic.hapusPermohonan(idFail);
			}
		}
		
		//ajax command
		if ("showStatusPermohonanSewa".equals(submit)){			
			String id_fail = getParam("id_fail");
			this.context.put("id_fail", id_fail);
			
			String namaPemohon = getParam("namaPemohon");
			this.context.put("namaPemohon", namaPemohon);
			
			vm = "app/php2/online/frmStatusPermohonanSewa.jsp";
		}
		else if ("paparMaklumatPenyewaan".equals(actionPenyewaan)){
			
			// GO TO MAKLUMAT PERMOHONAN  
			vm = "app/php2/online/frmPYWMaklumatPermohonan.jsp";
			
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
    		}
    		
    		// MODE VIEW
    		if("view".equals(mode)){
    			
    			this.context.put("readonly", "readonly");
    			this.context.put("inputTextClass", "disabled");
    			this.context.put("disabled", "disabled");
    			
    			//MAKLUMAT TANAH
    			beanMaklumatTanah = new Vector();
    			logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
    			beanMaklumatTanah = logic.getBeanMaklumatTanah();
    			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
    			
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
        		
    			/*FrmPYWHeaderData header = new FrmPYWHeaderData();
    			Vector<Hashtable<String,String>> vec = header.setMaklumatPermohonan("1613133103");
    			//this.context.put("pemohon", vec.get(0));
    			this.context.put("pemohon", vec);*/

    		
    		}
    		
    		// MODE UPDATE
    		else if("update".equals(mode)){
    			
    			this.context.put("readonly", "");
        		this.context.put("inputTextClass", "");
        		this.context.put("disabled", "");        		
        		
        		if ("doChangeJenisTanah".equals(submit)){
    				idHakmilikAgensi = "";
    				idPHPBorangK = "";
    				idPPTBorangK = "";
    				idHakmilikUrusan = "";
    			}
        		
        		//MAKLUMAT TANAH
        		beanMaklumatTanah = new Vector();
    			logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
    			beanMaklumatTanah = logic.getBeanMaklumatTanah();
    			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
    			
        		if ("doChangePeganganHakmilik1".equals(submit)){
    				beanMaklumatTanah = new Vector();
    				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik1"));
    				logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
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
        			logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
        			beanMaklumatTanah = logic.getBeanMaklumatTanah();
        			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
    			}
        		
        		//MAKLUMAT PENYEWAAN
            	beanMaklumatSewa = new Vector();
        		logic.setMaklumatSewa(idPermohonan);
        		Hashtable hashMaklumatSewaDB = (Hashtable) logic.getBeanMaklumatSewa().get(0);
    			Hashtable hashMaklumatSewa = new Hashtable();
    			hashMaklumatSewa.put("tarikhTerima", getParam("tarikhTerima"));
    			hashMaklumatSewa.put("tarikhSurat", getParam("tarikhSurat"));
    			hashMaklumatSewa.put("perkara", getParam("txtPerkara"));
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
    		}
			
    	  //PAPAR MAKLUMAT
		} else if ("papar".equals(actionPenyewaan)){
			
			vm = "app/php2/online/frmPYWDaftarOnline.jsp";
			
			mode = "view";
			this.context.put("mode", "view");
        	this.context.put("readonly", "disabled");
        	this.context.put("inputTextClass", "disabled");
        	
        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
	
			if (logic.getBeanMaklumatPermohonan().size() != 0){
				Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				idUrusan = (String) hashPermohonan.get("idUrusan");
				idSuburusan = (String) hashPermohonan.get("idSuburusan");
				idPermohonan= (String) hashPermohonan.get("idPermohonan");
			}
			this.context.put("selectUrusan",HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), "disabled", " class=\"disabled\""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan(idUrusan, "socSuburusan", Long.parseLong(idSuburusan), "disabled", " class=\"disabled\""));

			//MAKLUMAT TANAH
			/*beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(logic.getIdHakmilikAgensi(idFail));
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);*/
			String flagBorangK = "";
			logic.setMaklumatHakmilik(logic.getIdHakmilikPermohonanByIdFail(idFail));
			if (logic.getBeanMaklumatHakmilik().size() != 0){
				Hashtable hashHakmilik = (Hashtable) logic.getBeanMaklumatHakmilik().get(0);
				flagBorangK = (String) hashHakmilik.get("flagBorangK");
			}
			
			if ("Y".equals(flagBorangK)){
				beanMaklumatBorangK = new Vector();
				beanMaklumatBorangK = logic.getBeanMaklumatHakmilik();
				this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
				this.context.put("idJenisTanah", "3");
			} else {
				beanMaklumatTanah = new Vector();
				beanMaklumatTanah = logic.getBeanMaklumatHakmilik();
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
				this.context.put("idJenisTanah", "1");
				this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));
			}		
			
		//DAFTAR PERMOHONAN BARU
		} else if ("daftarBaru".equals(actionPenyewaan)) {
			
			vm = "app/php2/online/frmPYWDaftarOnline.jsp";
			
			mode = "new";
			this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PEMOHON
        	FrmPYWHeaderData header = new FrmPYWHeaderData();
			Vector<Hashtable<String,String>> vec = header.setMaklumatPermohonan(id_user);
			this.context.put("pemohon", vec.get(0));
        	
        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			//hashPermohonan.put("noFail", "");
			hashPermohonan.put("noPermohonan", "");
			hashPermohonan.put("noRujukanSurat",getParam("txtNoRujukanSurat") == null ? "": getParam("txtNoRujukanSurat"));
			hashPermohonan.put("perkara", getParam("txtperkara") == null ? "": getParam("txtperkara"));
			hashPermohonan.put("tarikhSurat", getParam("txttarikhSurat") == null ? "" : getParam("txttarikhSurat"));
			hashPermohonan.put("tarikhTerima",getParam("tarikhTerima") == null || "".equals(getParam("tarikhTerima"))? sdf.format(currentDate) : getParam("tarikhTerima"));

			
			beanMaklumatPermohonan.addElement(hashPermohonan);
			
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);			
			this.context.put("selectUrusan", HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), ""," onChange=\"doChangeUrusan();\""));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(idUrusan,"socSuburusan", Long.parseLong(idSuburusan), ""," onChange=\"doChangeSuburusan();\""));
				
			//MAKLUMAT HAKMILIK
			if ("doChangePeganganHakmilik".equals(submit)) {
				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik").trim());
				if (idHakmilikAgensi.isEmpty()) {
					this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
				}
			}
			
			beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			
			//MAKLUMAT BORANG K
			if ("doChangePeganganHakmilikBorangK".equals(submit)) {
				idPHPBorangK = logic.getIdPHPBorangKByPeganganHakmilik(getParam("txtPeganganHakmilik"));
				if (idPHPBorangK.isEmpty()) {
					idHakmilikUrusan = logic.getIdHakmilikUrusanByPeganganHakmilik(getParam("txtPeganganHakmilik"));
					if (idHakmilikUrusan.isEmpty()) {
						this.context.put("errorPeganganHakmilik","Maklumat Borang K tidak wujud.");
					}					
				}
			}			
			
			beanMaklumatBorangK = new Vector();
			logic.setMaklumatBorangK(idPPTBorangK, idHakmilikUrusan, idPHPBorangK);
			beanMaklumatBorangK = logic.getBeanMaklumatBorangK();
			this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
			
			if ("1".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "selected");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("selected4", "");
				this.context.put("idJenisTanah", idJenisTanah);
        	}else if ("2".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "selected");
				this.context.put("selected3", "");
				this.context.put("selected4", "");
				this.context.put("idJenisTanah", idJenisTanah);
        	}else if ("3".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "selected");
				this.context.put("selected4", "");
				this.context.put("idJenisTanah", idJenisTanah);
        	}else if ("4".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("selected4", "selected");
				this.context.put("idJenisTanah", idJenisTanah);
        	}else{
    			this.context.put("selected", "selected");
    			this.context.put("selected1", "");
    			this.context.put("selected2", "");
    			this.context.put("selected3", "");
    			this.context.put("selected4", "");
    			this.context.put("idJenisTanah", 0);
            }

		} else if ("cari".equals(command)) {
			
			String findNoFail = getParam("findNoFail");
			String findNoHakmilik = getParam("findNoHakmilik");
			String findNoLot = getParam("findNoLot");
			
			Vector status_PermohonanSewa = logic.statusPermohonanSewa(findNoFail, findNoHakmilik, findNoLot, id_user);
			this.context.put("status_PermohonanSewa", status_PermohonanSewa);
			setupPage(session, action, status_PermohonanSewa);
			
			context.put("findNoFail", getParam("findNoFail"));	
			context.put("findNoHakmilik", getParam("findNoHakmilik"));
			context.put("findNoLot", getParam("findNoLot"));

			// screen
			vm = "app/php2/online/frmPYWSenaraiFailOnline.jsp";

		}// close cari
		
		else {
			
			Vector status_PermohonanSewa = logic.statusPermohonanSewa(null, null, null, id_user);
			this.context.put("status_PermohonanSewa", status_PermohonanSewa);
			setupPage(session, action, status_PermohonanSewa);
			
			context.remove("findNoFail");	
			context.remove("findNamaProjek");
			context.remove("findNoSiasatan");
			
			vm = "app/php2/online/frmPYWSenaraiFailOnline.jsp";
		}
		
//		System.out.println("vm FrmPYWOnlineSenaraiFailView : "+vm);

		// SET DEFAULT PARAM
		this.context.put("actionPenyewaan", actionPenyewaan);
		this.context.put("mode", mode);
		this.context.put("selectedTabUpper", selectedTabUpper);

		// SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idHakmilikAgensi", idHakmilikAgensi);
		this.context.put("idLuasKegunaan", idLuasKegunaan);
	    this.context.put("idLuas", idLuas);
	    this.context.put("idPermohonanSewa", idPermohonanSewa);
		this.context.put("idPPTBorangK", idPPTBorangK);
		this.context.put("idHakmilikUrusan", idHakmilikUrusan);
		this.context.put("idPHPBorangK", idPHPBorangK);


		return vm;
	}

	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
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

}
