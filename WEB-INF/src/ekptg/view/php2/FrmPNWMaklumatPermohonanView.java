/**
 * 
 */
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
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.php2.FrmPNWHeaderData;
import ekptg.model.php2.FrmPNWMaklumatPermohonanData;
import ekptg.model.php2.FrmPNWPopupSenaraiTanahData;
import ekptg.model.php2.utiliti.LampiranBean;
import ekptg.model.utils.lampiran.ILampiran;


public class FrmPNWMaklumatPermohonanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPNWHeaderData logicHeader = new FrmPNWHeaderData();
	FrmPNWMaklumatPermohonanData logic = new FrmPNWMaklumatPermohonanData();
	FrmPNWPopupSenaraiTanahData logicTanah = new FrmPNWPopupSenaraiTanahData();
	FrmSemakan semak = null;
	private ILampiran iLampiran = null;

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
	    if (doPost.equals("true") || getParam("doPost").equals("tru")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String submit = getParam("command");  
	    String vm = ""; 
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

		//GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idPemohon = getParam("idPemohon");
        String idStatus = getParam("idStatus");
        String idPermohonanPelepasan = getParam("idPermohonanPelepasan");
        String idHakmilik = getParam("idHakmilik");
        String idDokumen = getParam("idDokumen");
        String idUlasanTeknikal = getParam("idUlasanTeknikal");
        String idHakmilikAgensi = getParam("idHakmilikAgensi");
        String idHakmilikSementara = getParam("idHakmilikSementara");
        String idHakmilikPelan = getParam("idHakmilikPelan");
        String idHakmilikPermohonan = getParam("idHakmilikPermohonan");
        String idHakmilikPegangan = getParam("idHakmilikPegangan");
        
        String flagBorangK = getParam("flagBorangK");
        String peganganHakmilik = getParam("peganganHakmilik");
        String flagHakmilik = getParam("flagHakmilik");
        
        //GET DROPDOWN PARAM
        String idLuasKegunaan = getParam("socLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
			idLuasKegunaan = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
        
        //VECTOR
        Vector beanHeader = null;
        Vector beanMaklumatTanah = null;
        Vector beanMaklumatBorangK = null;
        Vector beanMaklumatPenawaran = null;
        Vector senaraiTanahBerkaitan = null;
        Vector beanMaklumatPelan = null;
        Vector senaraiPelan = null;
        Vector senaraiSemak = null;
        
		this.context.put("completed", false);
		this.context.put("limitExceed", false);
		
        String step = getParam("step");
        
        vm = "app/php2/frmPNWMaklumatPermohonan.jsp";  
        
        //SUBMIT TO NEXT PROCESS
        if (postDB) {
    		if ("simpanDaftarHakmilikBaru".equals(hitButton)) {
    			//logicTanah.simpan(idPermohonan, idHakmilikAgensi, idHakmilikSementara, session);
    			logic.simpanDaftarHakmilikBaru(idPermohonan, idHakmilikAgensi, idHakmilikSementara, session);
    			hitButton = "refresh";
    		}
        	if ("doSimpanKemaskiniMaklumatPenawaran".equals(hitButton)) {
				logic.updatePermohonanPenawaran(idFail, idPermohonan,
						getParam("tarikhTerima"), getParam("tarikhSurat"),
						getParam("txtPerkara"), idPermohonanPelepasan,
						idLuasKegunaan, idLuas, getParam("txtLuasMohon1"),
						getParam("txtLuasMohon2"), getParam("txtLuasMohon3"),
						getParam("txtLuasBersamaan"), getParam("txtBakiLuas"), idHakmilikPermohonan,
						session);
			}
        	if ("doHapus".equals(hitButton)){
        		logic.doHapus(idPermohonan, getParam("idHakmilikPermohonan"), session);
    		}
        	if ("doSeterusnya".equals(hitButton)){
    			logic.updateStatus(idFail, idPermohonan, session);
    		} 
        	if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
        	if ("doSimpanKemaskiniSenaraiSemak".equals(hitButton)) {
        		String semaks [] = this.request.getParameterValues("idsSenaraiSemak");
    			logic.updateSenaraiSemak(idPermohonan,semaks,session);
        	}
        	if ("simpanDokumen".equals(hitButton)) {
				uploadFiles(idFail,idPermohonan, session);
			}
        	if ("simpanKemaskiniDokumen".equals(hitButton)) {
				logic.simpanKemaskiniDokumen(idDokumen, getParam("txtNamaPelan"), getParam("txtCatatanPelan"), session);
			}
        	if ("hapusDokumen".equals(hitButton)) {
				logic.hapusDokumen(idDokumen, session);
			}
        	//maklumat tanah
        	if("doSimpanMaklumatTanah".equals(hitButton)){
				beanMaklumatTanah = new Vector();
				logicTanah.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
				beanMaklumatTanah = logicTanah.getBeanMaklumatTanah();
				
				idHakmilik = logicHeader.getIdHakmilikByIdFail(idFail);
				
				logic.saveMaklumatTanah(idHakmilikAgensi, idPermohonan, idHakmilik, beanMaklumatTanah, session);
        	}
        	if ("doHapusFail".equals(hitButton)){
    			logic.doHapusFail(idFail, idPermohonan, getParam("tarikhHapus"), getParam("txtSebab"), session);
    			step = "";
    			vm = "app/php2/frmPLPSenaraiFail.jsp";
    		}
    	}
        
        this.context.put("javascriptLampiran", getDocPHP().javascriptUpload("", "paparLampiran", "idDokumen",session, "phptkr"));
        
        //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		if (logicHeader.getBeanMaklumatPermohonan().size() != 0){
			Hashtable hashPermohonan = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashPermohonan.get("idFail");
			idPermohonan = (String) hashPermohonan.get("idPermohonan");
			idPemohon = (String) hashPermohonan.get("idPemohon");
			idStatus = (String) hashPermohonan.get("idStatus");
		}
		
		//MAKLUMAT HAKMILIK
		String statusRizab = "";
		logicHeader.setMaklumatHakmilik(logicHeader.getIdHakmilikPermohonanByIdFail(idFail));
		if (logicHeader.getBeanMaklumatHakmilik().size() != 0){
			Hashtable hashHakmilik = (Hashtable) logicHeader.getBeanMaklumatHakmilik().get(0);
			idHakmilik = logicHeader.getIdHakmilikByIdHakmilikPermohonan((String) hashHakmilik.get("idHakmilikPermohonan"));
			String idHTPHakmilik = logicHeader.getIdHTPHakmilikByIdHakmilikPermohonan((String) hashHakmilik.get("idHakmilikPermohonan"));
			this.context.put("idHTPHakmilik", idHTPHakmilik);
			
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
       	
		//SENARAI TANAH BERKAITAN
		senaraiTanahBerkaitan = new Vector();
		logic.setSenaraiTanahBerkaitan(idPermohonan);
		senaraiTanahBerkaitan = logic.getListTanahBerkaitan();
		this.context.put("SenaraiTanahBerkaitan", senaraiTanahBerkaitan);
		
		//SENARAI SEMAK
		if("5".equals(selectedTabUpper)){
			/*senaraiSemak = logic.getSenaraiSemak(idPermohonan);
			this.context.put("SenaraiSemak", senaraiSemak);*/

			semak = new FrmSemakan();
			senaraiSemak = semak.getSenaraiSemakanAttach2("phptukar",idPermohonan);
			this.context.put("SenaraiSemak", senaraiSemak);

		}
		
        //VIEW MODE
        if ("view".equals(mode)){
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
			
			if ("4".equals(selectedTabUpper)){
				//OPEN POPUP DOKUMEN
	        	if ("openPopupDokumen".equals(flagPopup)){
	        		
	        		if ("new".equals(modePopup)){
	        			
	        			this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		    			
		    			beanMaklumatPelan = new Vector();    			
		    			Hashtable hashMaklumatPelan = new Hashtable();
		    			hashMaklumatPelan.put("namaPelan", "");
		    			hashMaklumatPelan.put("catatanPelan", "");
		    			beanMaklumatPelan.addElement(hashMaklumatPelan);
						this.context.put("BeanMaklumatPelan", beanMaklumatPelan);
		    			
	        		} else if ("update".equals(modePopup)){
	        			
	        			this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		    			
		    			//MAKLUMAT DOKUMEN
						beanMaklumatPelan = new Vector();
						logic.setMaklumatPelan(idDokumen);
						beanMaklumatPelan = logic.getBeanMaklumatPelan();
						this.context.put("BeanMaklumatPelan", beanMaklumatPelan);
		    			
	        		} else if ("view".equals(modePopup)){
	        			
	        			this.context.put("readonlyPopup", "readonly");
		    			this.context.put("inputTextClassPopup", "disabled");
		    			
		    			//MAKLUMAT DOKUMEN
						beanMaklumatPelan = new Vector();
						logic.setMaklumatPelan(idDokumen);
						beanMaklumatPelan = logic.getBeanMaklumatPelan();
						this.context.put("BeanMaklumatPelan", beanMaklumatPelan);
	        		}
	        	} 
	        	
	        	//SENARAI DOKUMEN
				senaraiPelan = new Vector();
				logic.setSenaraiPelan(idPermohonan);
				senaraiPelan = logic.getListPelan();
				this.context.put("SenaraiPelan", senaraiPelan);
				
			} else if("2".equals(selectedTabUpper)) {
	        	if ("openPopupMaklumatPermohonan".equals(flagPopup)){
	        		
        			//MAKLUMAT PENAWARAN
        			beanMaklumatPenawaran = new Vector();
        			logic.setMaklumatPenawaranByIdHakmilik(idHakmilikPermohonan);
        			beanMaklumatPenawaran = logic.getBeanMaklumatPenawaran();
        			
        			if (beanMaklumatPenawaran.size() == 0){
        				if(flagHakmilik.equals("U")){
            				beanMaklumatPenawaran = new Vector();
            				logic.setMaklumatPenawaran(idPermohonan);
                			beanMaklumatPenawaran = logic.getBeanMaklumatPenawaran();
        				}
        			}
        			
        			if (beanMaklumatPenawaran.size() > 0){
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
            		} else {
		    			Hashtable hashMaklumatPenawaran = new Hashtable();
		    			hashMaklumatPenawaran.put("tarikhTerima", "");
		    			hashMaklumatPenawaran.put("tarikhSurat", "");
		    			hashMaklumatPenawaran.put("perkara", "");
		    			hashMaklumatPenawaran.put("perkara", "");
		    			hashMaklumatPenawaran.put("luasAsal", "");
		    			hashMaklumatPenawaran.put("luas1", "");
		    			hashMaklumatPenawaran.put("luas2", "");
		    			hashMaklumatPenawaran.put("luas3", "");
		    			hashMaklumatPenawaran.put("luasBersamaan", "");
		    			hashMaklumatPenawaran.put("luasBaki", "");
		    			hashMaklumatPenawaran.put("keteranganLuasAsal", "");
		    			beanMaklumatPenawaran.addElement(hashMaklumatPenawaran);
		    			idLuasKegunaan = "99999";
            			idLuas = "99999";
            			idPermohonanPelepasan = "";
            		}
        			
	        		if ("update".equals(modePopup)){
	            		this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", " onChange=\"doChangeLuasKegunaan()\" style=\"width:auto\""));
	        		}else if ("view".equals(modePopup)){
	        			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));	
	        		}
	        	}
				Vector senaraiTanahSemua = new Vector();
				senaraiTanahSemua = logic.setSenaraiTanahSemua(idPermohonan, senaraiTanahSemua);
				this.context.put("SenaraiTanahSemua", senaraiTanahSemua);
				
    			this.context.put("BeanMaklumatPenawaran", beanMaklumatPenawaran);
			}
        } else if ("update".equals(mode)){ //UPDATE MODE
        	this.context.put("readonly", "");
    		this.context.put("inputTextClass", "");
    		this.context.put("disabled", "");
    		
        	//MAKLUMAT PENAWARAN
        	beanMaklumatPenawaran = new Vector();
    		logic.setMaklumatPenawaran(idPermohonan);
    		Hashtable hashMaklumatPenawaranDB = (Hashtable) logic.getBeanMaklumatPenawaran().get(0);
			Hashtable hashMaklumatPenawaran = new Hashtable();
			hashMaklumatPenawaran.put("tarikhTerima", getParam("tarikhTerima"));
			hashMaklumatPenawaran.put("tarikhSurat", getParam("tarikhSurat"));
			hashMaklumatPenawaran.put("perkara", getParam("txtPerkara"));
			hashMaklumatPenawaran.put("luasAsal", Utils.RemoveSymbol((String)hashMaklumatPenawaranDB.get("luasAsal")));
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
        
        if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
        
        if ("hapusFail".equals(step)){
        	vm = "app/php2/frmHapusFail.jsp";
        }
        
        //SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("selectedTabUpper", selectedTabUpper);
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idPemohon", idPemohon);
        this.context.put("idStatus", idStatus);
        this.context.put("idUlasanTeknikal", idUlasanTeknikal);
        this.context.put("idPermohonanPelepasan", idPermohonanPelepasan);
		this.context.put("idLuasKegunaan", idLuasKegunaan);
		this.context.put("idLuas", idLuas);
		this.context.put("idHakmilik", idHakmilik);		
		this.context.put("idHakmilikPelan", idHakmilikPelan);		
		this.context.put("idDokumen", idDokumen);
		this.context.put("flagBorangK",flagBorangK);
		this.context.put("statusRizab",statusRizab);
		this.context.put("idHakmilikAgensi", idHakmilikAgensi);	
		this.context.put("idHakmilikSementara", idHakmilikSementara);
		this.context.put("idHakmilikPermohonan", idHakmilikPermohonan);	
		this.context.put("idHakmilikPegangan", idHakmilikPegangan);
        
		this.context.put("step",step);
		this.context.put("peganganHakmilik",peganganHakmilik);
		this.context.put("flagHakmilik",flagHakmilik);
		
		return vm;
	}
	
	// UPLOAD FILE
	private void uploadFiles(String idFail, String idPermohonan , HttpSession session) throws Exception {
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
					if(item.getSize()<500000000L)
						saveData(item ,idFail, idPermohonan , session);
					else
						this.context.put("limitExceed", true);						
				}
			}
		}
	}
	
	private void saveData(FileItem item,String idFail, String idPermohonan, HttpSession session) throws Exception {

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
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,FLAG_DOKUMEN,ID_PERMOHONAN) "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, getParam("namaPelan"));
			ps.setString(3, getParam("catatanPelan"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, "P");
			ps.setString(9, idPermohonan);
			ps.executeUpdate();

			con.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "INS",
					"FAIL PENAWARAN [" + idDokumen
							+ "] DIDAFTARKAN");
			
		} finally {
			if (db != null)
				db.close();
		}
		this.context.put("completed", true);
	}	

	private String carianId(String idFail) throws Exception  {
		return logic.carianIdPermohonan(idFail);
	}
	
	private ILampiran getDocPHP(){
		if(iLampiran == null){
			iLampiran = new LampiranBean();
		}
		return iLampiran;

	}
}
