package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.integrasi.FrmModelNilaianSewaan;

@SuppressWarnings({ "serial", "unused" })
public class FrmViewNilaianSewaan extends AjaxBasedModule {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	String Carian_NoFail = "";
	String Carian_NamaSiMati = "";
	String Carian_NoKPSiMati = "";
	
	String ID_PERMOHONAN = "";
	String ID_SIMATI = "";
	String ID_PEMOHON = "";
	String ID_HTAAH = "";
	String ID_HTATH = "";
	
	String MP_IDFAIL = "";
	String MP_NOFAIL = "";
	String MP_NOPERMOHONAN = "";
	String MP_IDNEGERI = "";
	String MP_IDDAERAH = "";
	String MP_IDUNIT = "";
	String MP_NEGERI = "";
	String MP_DAERAH = "";
	String MP_UNIT = "";
	String MP_IDSIMATI = "";
	String MP_NAMASIMATI = "";
	String MP_TARIKHMATI = "";
	String MP_NOKPSIMATI = "";
	String MP_IDPEMOHON = "";
	String MP_NAMAPEMOHON = "";
	String MP_NOKPPEMOHON = "";
	String MP_ALAMAT1PEMOHON = "";
	String MP_ALAMAT2PEMOHON = "";
	String MP_ALAMAT3PEMOHON = "";
	String MP_ALAMATPEMOHON = "";
	String MP_NOTELPEMOHON = "";
	
	String HTAAH_NEGERI = "";
	String HTAAH_DAERAH = "";
	String HTAAH_MUKIM = "";
	String HTAAH_SEKSYEN = "";
	String HTAAH_JENISHAKMILIK = "";
	String HTAAH_NOHAKMILIK = "";
	String HTAAH_JENISPTLOT = "";
	String HTAAH_NOPTLOT = "";
	String HTAAH_NOBANGUNAN = "";
	String HTAAH_JENISNOBANGUNAN = "";
	String HTAAH_BASIMATI = "";
	String HTAAH_BBSIMATI = "";
	String HTAAH_JENISPEGANGAN = "";
	String HTAAH_JENISPEGANGAN1 = "";
	String HTAAH_JENISPEGANGAN2 = "";
	String HTAAH_TARIKHLUPUTPAJAKAN = "";
	String HTAAH_TEMPOHPAJAKAN = "";
	String HTAAH_KATEGORITANAH = "";
	String HTAAH_SYARATNYATA = "";
	String HTAAH_SEKATANKEPENTINGAN = "";
	String HTAAH_LUASTANAH = "";
	String HTAAH_JENISLUASTANAH = "";
	String HTAAH_LUASPETAK = "";
	String HTAAH_ALAMAT1HARTA = "";
	String HTAAH_ALAMAT2HARTA = "";
	String HTAAH_ALAMAT3HARTA = "";
	String HTAAH_JENISHARTADINILAI = "";
	String HTAAH_CATATAN = "";
	String HTAAH_NAMAPEGAWAIJKPTG = "";
	String HTAAH_NILAITARIKHMOHONTANAH = "";
	String HTAAH_NILAITARIKHMOHONBANGUNAN = "";
	String HTAAH_NILAITARIKHMATITANAH = "";
	String HTAAH_NILAITARIKHMATIBANGUNAN = "";
	String HTAAH_NAMAPEGAWAI = "";
	String HTAAH_CAWANGANJPPH = "";
	String HTAAH_JPPHCATATAN = "";
    
	String HTATH_NEGERI = "";
	String HTATH_DAERAH = "";
	String HTATH_MUKIM = "";
	String HTATH_SEKSYEN = "";
	String HTATH_JENISPTLOT = "";
	String HTATH_NOPTLOT = "";
	String HTATH_NOBANGUNAN = "";
	String HTATH_JENISNOBANGUNAN = "";
	String HTATH_BASIMATI = "";
	String HTATH_BBSIMATI = "";
	String HTATH_JENISPEGANGAN = "";
	String HTATH_TARIKHLUPUTPAJAKAN = "";
	String HTATH_TEMPOHPAJAKAN = "";
	String HTATH_KATEGORITANAH = "";
	String HTATH_SYARATNYATA = "";
	String HTATH_SEKATANKEPENTINGAN = "";
	String HTATH_ALAMAT1HARTA = "";
	String HTATH_ALAMAT2HARTA = "";
	String HTATH_ALAMAT3HARTA = "";
	String HTATH_NAMAPEMILIK = "";
	String HTATH_TARIKHPERJANJIANJUALBELI = "";
	String HTATH_NAMAPEMAJU = "";
	String HTATH_LUASPETAK = "";
	String HTATH_CATATAN = "";
	String HTATH_NAMAPEGAWAIJKPTG = "";
	String HTATH_NILAITARIKHMOHONTANAH = "";
	String HTATH_NILAITARIKHMOHONBANGUNAN = "";
	String HTATH_NILAITARIKHMATITANAH = "";
	String HTATH_NILAITARIKHMATIBANGUNAN = "";
	String HTATH_NAMAPEGAWAI = "";
	String HTATH_CAWANGANJPPH = "";
	String HTATH_JPPHCATATAN = "";
	
	String SOC_AHNEGERI = "", SOC_AHDAERAH = "", SOC_AHMUKIM = "";
	String SOC_THNEGERI = "", SOC_THDAERAH = "", SOC_THMUKIM = "";

	String userName = "";
	
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		String vm = "";

        HttpSession session = this.request.getSession();
        
        FrmModelNilaianSewaan modelSEWA = new FrmModelNilaianSewaan();
        
        // action
        String action = getParam("action2");
        
        // mode
        String mode = getParam("mode");

        // command: used by changeSOC
        String command = getParam("command");
        
        // selectedTab
        String selectedTab = getParam("selectedTab");
        if ("".equalsIgnoreCase(selectedTab)) {
        	selectedTab = "0";
        }
        if (!"0".equalsIgnoreCase(selectedTab) && !"1".equalsIgnoreCase(selectedTab)) {
        	selectedTab = "0";
        }
        
        String DISABLE_JPPH = " readonly=\"readonly\" ", DISABLE_JKPTG = " readonly=\"readonly\" ", DISABLE_PAJAKAN = " readonly=\"readonly\" ";
        
        Boolean saveNilaianHTA = false, deleteNilaianHTA = false;
        
        userName = (String) session.getAttribute("_portal_username");
        
        Boolean Page_Carian = false;
        
        ID_PERMOHONAN = getParam("ID_PERMOHONAN");
        if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
	        ID_SIMATI = modelSEWA.getIDSiMati(ID_PERMOHONAN);
	        ID_PEMOHON = modelSEWA.getIDPemohon(ID_PERMOHONAN);
        }
        ID_HTAAH = getParam("ID_HTAAH");
        if ("".equalsIgnoreCase(ID_HTAAH) && !"".equalsIgnoreCase(ID_SIMATI)) {
        	ID_HTAAH = modelSEWA.getFirstIDHTAAH(ID_SIMATI);
        }
        ID_HTATH = getParam("ID_HTATH");
        if ("".equalsIgnoreCase(ID_HTATH) && !"".equalsIgnoreCase(ID_SIMATI)) {
        	ID_HTATH = modelSEWA.getFirstIDHTATH(ID_SIMATI);
        }
        
        Carian_NoFail = getParam("Carian_NoFail");
        Carian_NamaSiMati = getParam("Carian_NamaSiMati");
        Carian_NoKPSiMati = getParam("Carian_NoKPSiMati");
        
        MP_IDFAIL = getParam("MP_IDFAIL");
        MP_NOFAIL = getParam("MP_NOFAIL");
        MP_NOPERMOHONAN = getParam("MP_NOPERMOHONAN");
    	MP_IDNEGERI = getParam("MP_IDNEGERI");
    	MP_IDDAERAH = getParam("MP_IDDAERAH");
    	MP_IDUNIT = getParam("MP_IDUNIT");
    	MP_NEGERI = getParam("MP_NEGERI");
    	MP_DAERAH = getParam("MP_DAERAH");
    	MP_UNIT = getParam("MP_UNIT");
    	MP_IDSIMATI = getParam("MP_IDSIMATI");
    	MP_NAMASIMATI = getParam("MP_NAMASIMATI");
    	MP_TARIKHMATI = getParam("MP_TARIKHMATI");
    	MP_NOKPSIMATI = getParam("MP_NOKPSIMATI");
    	MP_IDPEMOHON = getParam("MP_IDPEMOHON");
    	MP_NAMAPEMOHON = getParam("MP_NAMAPEMOHON");
    	MP_NOKPPEMOHON = getParam("MP_NOKPPEMOHON");
    	MP_ALAMATPEMOHON = getParam("MP_ALAMATPEMOHON");     
    	MP_NOTELPEMOHON = getParam("MP_NOTELPEMOHON");

    	HTAAH_NEGERI = getParam("HTAAH_NEGERI");
    	HTAAH_DAERAH = getParam("HTAAH_DAERAH");
    	HTAAH_MUKIM = getParam("HTAAH_MUKIM");
    	HTAAH_SEKSYEN = getParam("HTAAH_SEKSYEN");
    	HTAAH_JENISHAKMILIK = getParam("HTAAH_JENISHAKMILIK");
    	HTAAH_NOHAKMILIK = getParam("HTAAH_NOHAKMILIK");
    	HTAAH_JENISPTLOT = getParam("HTAAH_JENISPTLOT");
    	HTAAH_NOPTLOT = getParam("HTAAH_NOPTLOT");
    	HTAAH_NOBANGUNAN = getParam("HTAAH_NOBANGUNAN");
    	HTAAH_BASIMATI = getParam("HTAAH_BASIMATI");
    	HTAAH_BBSIMATI = getParam("HTAAH_BBSIMATI");
    	HTAAH_JENISPEGANGAN = getParam("HTAAH_JENISPEGANGAN");
    	HTAAH_TARIKHLUPUTPAJAKAN = getParam("HTAAH_TARIKHLUPUTPAJAKAN");
    	HTAAH_TEMPOHPAJAKAN = getParam("HTAAH_TEMPOHPAJAKAN");
    	HTAAH_KATEGORITANAH = getParam("HTAAH_KATEGORITANAH");
    	HTAAH_SYARATNYATA = getParam("HTAAH_SYARATNYATA");
    	HTAAH_SEKATANKEPENTINGAN = getParam("HTAAH_SEKATANKEPENTINGAN");
    	HTAAH_LUASTANAH = getParam("HTAAH_LUASTANAH");
    	HTAAH_JENISLUASTANAH = getParam("HTAAH_JENISLUASTANAH");
    	HTAAH_LUASPETAK = getParam("HTAAH_LUASPETAK");
    	HTAAH_ALAMAT1HARTA = getParam("HTAAH_ALAMAT1HARTA");
    	HTAAH_ALAMAT2HARTA = getParam("HTAAH_ALAMAT2HARTA");
    	HTAAH_ALAMAT3HARTA = getParam("HTAAH_ALAMAT3HARTA");
    	HTAAH_JENISHARTADINILAI = getParam("HTAAH_JENISHARTADINILAI");
    	HTAAH_CATATAN = getParam("HTAAH_CATATAN");
    	HTAAH_NAMAPEGAWAIJKPTG = getParam("HTAAH_NAMAPEGAWAIJKPTG");
    	HTAAH_NILAITARIKHMOHONTANAH = getParam("HTAAH_NILAITARIKHMOHONTANAH");
    	HTAAH_NILAITARIKHMOHONBANGUNAN = getParam("HTAAH_NILAITARIKHMOHONBANGUNAN");
    	HTAAH_NILAITARIKHMATITANAH = getParam("HTAAH_NILAITARIKHMATITANAH");
    	HTAAH_NILAITARIKHMATIBANGUNAN = getParam("HTAAH_NILAITARIKHMATIBANGUNAN");
    	HTAAH_NAMAPEGAWAI = getParam("HTAAH_NAMAPEGAWAI");
    	HTAAH_CAWANGANJPPH = getParam("HTAAH_CAWANGANJPPH");
    	HTAAH_JPPHCATATAN = getParam("HTAAH_JPPHCATATAN");

    	HTATH_NEGERI = getParam("HTATH_NEGERI");
    	HTATH_DAERAH = getParam("HTATH_DAERAH");
    	HTATH_MUKIM = getParam("HTATH_MUKIM");
    	HTATH_SEKSYEN = getParam("HTATH_SEKSYEN");
    	HTATH_JENISPTLOT = getParam("HTATH_JENISPTLOT");
    	HTATH_NOPTLOT = getParam("HTATH_NOPTLOT");
    	HTATH_NOBANGUNAN = getParam("HTATH_NOBANGUNAN");
    	HTATH_BASIMATI = getParam("HTATH_BASIMATI");
    	HTATH_BBSIMATI = getParam("HTATH_BBSIMATI");
    	HTATH_JENISPEGANGAN = getParam("HTATH_JENISPEGANGAN");
    	HTATH_TARIKHLUPUTPAJAKAN = getParam("HTATH_TARIKHLUPUTPAJAKAN");
    	HTATH_TEMPOHPAJAKAN = getParam("HTATH_TEMPOHPAJAKAN");
    	HTATH_KATEGORITANAH = getParam("HTATH_KATEGORITANAH");
    	HTATH_SYARATNYATA = getParam("HTATH_SYARATNYATA");
    	HTATH_SEKATANKEPENTINGAN = getParam("HTATH_SEKATANKEPENTINGAN");
    	HTATH_ALAMAT1HARTA = getParam("HTATH_ALAMAT1HARTA");
    	HTATH_ALAMAT2HARTA = getParam("HTATH_ALAMAT2HARTA");
    	HTATH_ALAMAT3HARTA = getParam("HTATH_ALAMAT3HARTA");
    	HTATH_NAMAPEMILIK = getParam("HTATH_NAMAPEMILIK");
    	HTATH_TARIKHPERJANJIANJUALBELI = getParam("HTATH_TARIKHPERJANJIANJUALBELI");
    	HTATH_NAMAPEMAJU = getParam("HTATH_NAMAPEMAJU");
    	HTATH_LUASPETAK = getParam("HTATH_LUASPETAK");
    	HTATH_CATATAN = getParam("HTATH_CATATAN");
    	HTATH_NAMAPEGAWAIJKPTG = getParam("HTATH_NAMAPEGAWAIJKPTG");
    	HTATH_NILAITARIKHMOHONTANAH = getParam("HTATH_NILAITARIKHMOHONTANAH");
    	HTATH_NILAITARIKHMOHONBANGUNAN = getParam("HTATH_NILAITARIKHMOHONBANGUNAN");
    	HTATH_NILAITARIKHMATITANAH = getParam("HTATH_NILAITARIKHMATITANAH");
    	HTATH_NILAITARIKHMATIBANGUNAN = getParam("HTATH_NILAITARIKHMATIBANGUNAN");
    	HTATH_NAMAPEGAWAI = getParam("HTATH_NAMAPEGAWAI");
    	HTATH_CAWANGANJPPH = getParam("HTATH_CAWANGANJPPH");
    	HTATH_JPPHCATATAN = getParam("HTATH_JPPHCATATAN");
    	
    	SOC_AHNEGERI = getParam("HTAAH_NEGERI");
    	SOC_AHDAERAH = getParam("HTAAH_DAERAH");
    	SOC_THNEGERI = getParam("HTATH_NEGERI");
    	SOC_THDAERAH = getParam("HTATH_DAERAH");
    	
    	Vector vData = new Vector();
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();
    	
        if ("searchNilaianHTA".equalsIgnoreCase(action)) {
        	vm = "app/integrasi/frmNilaianHTACarian.jsp";
        	vList = modelSEWA.searchHTA(Carian_NoFail, Carian_NamaSiMati, Carian_NoKPSiMati);
        	context.put("ListPermohonan", vList);
        	Page_Carian = true;
        } else if ("viewNilaianHTA".equalsIgnoreCase(action)) {
    		// initial VM value
        	vm = "app/integrasi/frmNilaianHTACarian.jsp";
    		
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			vData = modelSEWA.viewHTAMaklumatPermohonan(ID_PERMOHONAN);
    			if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmNilaianHTA.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    			
    			if ("0".equalsIgnoreCase(selectedTab)) {
	    			vList = modelSEWA.viewListNilaianHTAAH(ID_SIMATI);
	    			context.put("ListHTAAH", vList);
	    			
	    			if ("".equalsIgnoreCase(command)) {
		    			vData = modelSEWA.viewNilaianHTAAH(ID_HTAAH);
		    			if (!vData.isEmpty()) {
		    				h = (Hashtable) vData.get(0);
		    				populatePageHTAAH(h);
		    			}
	    			}
    			} else {
    				vList = modelSEWA.viewListNilaianHTATH(ID_SIMATI);
    				context.put("ListHTATH", vList);
    				
    				if ("".equalsIgnoreCase(command)) {
		    			vData = modelSEWA.viewNilaianHTATH(ID_HTATH);
		    			if (!vData.isEmpty()) {
		    				h = (Hashtable) vData.get(0);
		    				populatePageHTATH(h);
		    			}
	    			}
    			}
    		}
        } else if ("saveNilaianHTA".equalsIgnoreCase(action)) {
    		// initial VM value
        	vm = "app/integrasi/frmNilaianHTACarian.jsp";
    		
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			vData = modelSEWA.viewHTAMaklumatPermohonan(ID_PERMOHONAN);
    			if (!vData.isEmpty()) {
    	        	vm = "app/integrasi/frmNilaianHTA.jsp";
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    			h = new Hashtable();
    			h.put("MP_NOPERMOHONAN", MP_NOPERMOHONAN);
    			h.put("MP_IDFAIL", MP_IDFAIL);
    			h.put("MP_NOFAIL", MP_NOFAIL);
    			h.put("MP_IDNEGERI", MP_IDNEGERI);
				h.put("MP_IDDAERAH", MP_IDDAERAH);
				h.put("MP_IDUNIT", MP_IDUNIT);
				h.put("MP_IDSIMATI", MP_IDSIMATI);
				h.put("MP_NAMASIMATI", MP_NAMASIMATI);
				h.put("MP_NOKPSIMATI", MP_NOKPSIMATI);
				h.put("MP_TARIKHMATI", MP_TARIKHMATI);
				h.put("MP_IDPEMOHON", MP_IDPEMOHON);
				h.put("MP_NAMAPEMOHON", MP_NAMAPEMOHON);
				h.put("MP_NOKPPEMOHON", MP_NOKPPEMOHON);
				h.put("MP_ALAMAT1PEMOHON", MP_ALAMAT1PEMOHON);
				h.put("MP_ALAMAT2PEMOHON", MP_ALAMAT2PEMOHON);
				h.put("MP_ALAMAT3PEMOHON", MP_ALAMAT3PEMOHON);
				if ("0".equalsIgnoreCase(selectedTab)) {
					h.put("ID_HTAAH", ID_HTAAH);
					h.put("HTAAH_NEGERI", HTAAH_NEGERI);
					h.put("HTAAH_DAERAH", HTAAH_DAERAH);
					h.put("HTAAH_MUKIM", HTAAH_MUKIM);
					h.put("HTAAH_SEKSYEN", HTAAH_SEKSYEN);
					h.put("HTAAH_JENISHAKMILIK", HTAAH_JENISHAKMILIK);
					h.put("HTAAH_NOHAKMILIK", HTAAH_NOHAKMILIK);
					h.put("HTAAH_NOPTLOT", HTAAH_NOPTLOT);
					h.put("HTAAH_JENISPTLOT", HTAAH_JENISPTLOT);
					h.put("HTAAH_NOBANGUNAN", HTAAH_NOBANGUNAN);
					h.put("HTAAH_JENISNOBANGUNAN", HTAAH_JENISNOBANGUNAN);
					h.put("HTAAH_BASIMATI", HTAAH_BASIMATI);
					h.put("HTAAH_BBSIMATI", HTAAH_BBSIMATI);
					h.put("HTAAH_JENISPEGANGAN", HTAAH_JENISPEGANGAN);
					h.put("HTAAH_TARIKHLUPUTPAJAKAN", HTAAH_TARIKHLUPUTPAJAKAN);
					h.put("HTAAH_TEMPOHPAJAKAN", HTAAH_TEMPOHPAJAKAN);
					h.put("HTAAH_KATEGORITANAH", HTAAH_KATEGORITANAH);
					h.put("HTAAH_SYARATNYATA", HTAAH_SYARATNYATA);
					h.put("HTAAH_SEKATANKEPENTINGAN", HTAAH_SEKATANKEPENTINGAN);
					h.put("HTAAH_LUASTANAH", HTAAH_LUASTANAH);
					h.put("HTAAH_JENISLUASTANAH", HTAAH_JENISLUASTANAH);
					h.put("HTAAH_LUASPETAK", HTAAH_LUASPETAK);
					h.put("HTAAH_ALAMAT1HARTA", HTAAH_ALAMAT1HARTA);
					h.put("HTAAH_ALAMAT2HARTA", HTAAH_ALAMAT2HARTA);
					h.put("HTAAH_ALAMAT3HARTA", HTAAH_ALAMAT3HARTA);
					h.put("HTAAH_JENISHARTADINILAI", HTAAH_JENISHARTADINILAI);
					h.put("HTAAH_CATATAN", HTAAH_CATATAN);
					h.put("HTAAH_NILAITARIKHMOHONTANAH", HTAAH_NILAITARIKHMOHONTANAH);
					h.put("HTAAH_NILAITARIKHMOHONBANGUNAN", HTAAH_NILAITARIKHMOHONBANGUNAN);
					h.put("HTAAH_NILAITARIKHMATITANAH", HTAAH_NILAITARIKHMATITANAH);
					h.put("HTAAH_NILAITARIKHMATIBANGUNAN", HTAAH_NILAITARIKHMATIBANGUNAN);
					h.put("HTAAH_NAMAPEGAWAI", HTAAH_NAMAPEGAWAI);
					h.put("HTAAH_CAWANGANJPPH", HTAAH_CAWANGANJPPH);
					h.put("HTAAH_JPPHCATATAN", HTAAH_JPPHCATATAN);
				} else {
					h.put("ID_HTATH", ID_HTATH);
					h.put("HTATH_NEGERI", HTATH_NEGERI);
					h.put("HTATH_DAERAH", HTATH_DAERAH);
					h.put("HTATH_MUKIM", HTATH_MUKIM);
					h.put("HTATH_SEKSYEN", HTATH_SEKSYEN);
					h.put("HTATH_NOPTLOT", HTATH_NOPTLOT);
					h.put("HTATH_JENISPTLOT", HTATH_JENISPTLOT);
					h.put("HTATH_NOBANGUNAN", HTATH_NOBANGUNAN);
					h.put("HTATH_JENISNOBANGUNAN", HTATH_JENISNOBANGUNAN);
					h.put("HTATH_BASIMATI", HTATH_BASIMATI);
					h.put("HTATH_BBSIMATI", HTATH_BBSIMATI);
					h.put("HTATH_JENISPEGANGAN", HTATH_JENISPEGANGAN);
					h.put("HTATH_TARIKHLUPUTPAJAKAN", HTATH_TARIKHLUPUTPAJAKAN);
					h.put("HTATH_TEMPOHPAJAKAN", HTATH_TEMPOHPAJAKAN);
					h.put("HTATH_KATEGORITANAH", HTATH_KATEGORITANAH);
					h.put("HTATH_SYARATNYATA", HTATH_SYARATNYATA);
					h.put("HTATH_SEKATANKEPENTINGAN", HTATH_SEKATANKEPENTINGAN);
					h.put("HTATH_ALAMAT1HARTA", HTATH_ALAMAT1HARTA);
					h.put("HTATH_ALAMAT2HARTA", HTATH_ALAMAT2HARTA);
					h.put("HTATH_ALAMAT3HARTA", HTATH_ALAMAT3HARTA);
					h.put("HTATH_NAMAPEMILIK", HTATH_NAMAPEMILIK);
					h.put("HTATH_TARIKHPERJANJIANJUALBELI", HTATH_TARIKHPERJANJIANJUALBELI);
					h.put("HTATH_NAMAPEMAJU", HTATH_NAMAPEMAJU);
					h.put("HTATH_LUASPETAK", HTATH_LUASPETAK);
					h.put("HTATH_CATATAN", HTATH_CATATAN);
					h.put("HTATH_NILAITARIKHMOHONTANAH", HTATH_NILAITARIKHMOHONTANAH);
					h.put("HTATH_NILAITARIKHMOHONBANGUNAN", HTATH_NILAITARIKHMOHONBANGUNAN);
					h.put("HTATH_NILAITARIKHMATITANAH", HTATH_NILAITARIKHMATITANAH);
					h.put("HTATH_NILAITARIKHMATIBANGUNAN", HTATH_NILAITARIKHMATIBANGUNAN);
					h.put("HTATH_NAMAPEGAWAI", HTATH_NAMAPEGAWAI);
					h.put("HTATH_CAWANGANJPPH", HTATH_CAWANGANJPPH);
					h.put("HTATH_JPPHCATATAN", HTATH_JPPHCATATAN);
				}
				if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
					saveNilaianHTA = modelSEWA.saveNilaianHTA(ID_PERMOHONAN, selectedTab, h);
				}
				if ("0".equalsIgnoreCase(selectedTab)) {
	    			vList = modelSEWA.viewListNilaianHTAAH(ID_SIMATI);
	    			context.put("ListHTAAH", vList);
	    			
	    			if ("".equalsIgnoreCase(command)) {
		    			vData = modelSEWA.viewNilaianHTAAH(ID_HTAAH);
		    			if (!vData.isEmpty()) {
		    				h = (Hashtable) vData.get(0);
		    				populatePageHTAAH(h);
		    			}
	    			}					
				} else {
					vList = modelSEWA.viewListNilaianHTATH(ID_SIMATI);
	    			context.put("ListHTATH", vList);
	    			
	    			if ("".equalsIgnoreCase(command)) {
		    			vData = modelSEWA.viewNilaianHTATH(ID_HTATH);
		    			if (!vData.isEmpty()) {
		    				h = (Hashtable) vData.get(0);
		    				populatePageHTATH(h);
		    			}
	    			}			
				}
    		}
        } else if ("deleteNilaianHTA".equalsIgnoreCase(action)) {
    		// initial VM value
        	vm = "app/integrasi/frmNilaianHTACarian.jsp";
    		
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			if ("0".equalsIgnoreCase(selectedTab) && !"".equalsIgnoreCase(ID_HTAAH)) {
    				deleteNilaianHTA = modelSEWA.deleteNilaianHTA(ID_PERMOHONAN, selectedTab, ID_HTAAH);
    			} else if ("1".equalsIgnoreCase(selectedTab) && !"".equalsIgnoreCase(ID_HTATH)){
    				deleteNilaianHTA = modelSEWA.deleteNilaianHTA(ID_PERMOHONAN, selectedTab, ID_HTATH);
    			}
    		}
        } else {
        	vm = "app/integrasi/frmNilaianHTACarian.jsp";
    		setupPage(session, action, vList);
    		context.put("pagingTitle", "title");
    		Page_Carian = true;
        }
        if ("changeSOCPajakan".equalsIgnoreCase(command)) {
        	HTAAH_JENISPEGANGAN = getParam("HTAAH_JENISPEGANGAN");
        	if ("2".equalsIgnoreCase(HTAAH_JENISPEGANGAN)) {
        		DISABLE_PAJAKAN = "";
        	}
        }
        
        if (Page_Carian) {
        	context.put("ListPermohonan", vList);
        } else {
        	if ("2".equalsIgnoreCase(HTAAH_JENISPEGANGAN) || "2".equalsIgnoreCase(HTATH_JENISPEGANGAN)) {
        		DISABLE_PAJAKAN = "";
        	}
        	DISABLE_JKPTG = "";
        	DISABLE_JPPH = "";
        	context.put("selectedTab", selectedTab);
        	context.put("saveNilaianHTA", saveNilaianHTA);
	        context.put("MP_NOFAIL", MP_NOFAIL);
	        context.put("MP_NOPERMOHONAN", MP_NOPERMOHONAN);
	    	context.put("MP_NEGERI", MP_NEGERI);
	    	context.put("MP_DAERAH", MP_DAERAH);
	    	context.put("MP_UNIT", MP_UNIT);
	    	context.put("MP_NAMASIMATI", MP_NAMASIMATI);
	    	context.put("MP_TARIKHMATI", MP_TARIKHMATI);
	    	context.put("MP_NOKPSIMATI", MP_NOKPSIMATI);
	    	context.put("MP_NAMAPEMOHON", MP_NAMAPEMOHON);
	    	context.put("MP_NOKPPEMOHON", MP_NOKPPEMOHON);
	    	context.put("MP_ALAMATPEMOHON", MP_ALAMATPEMOHON);
	    	context.put("MP_NOTELPEMOHON", MP_NOTELPEMOHON);

	    	context.put("selectAHNegeri", HTML.SelectNegeri("HTAAH_NEGERI", Utils.parseLong(SOC_AHNEGERI), DISABLE_JKPTG, "onchange=\"doChangeSOC('changeSOCAHNegeri');\""));
	    	context.put("selectAHDaerah", HTML.SelectDaerah("HTAAH_DAERAH", Utils.parseLong(SOC_AHDAERAH), DISABLE_JKPTG, "onchange=\"doChangeSOC('changeSOCAHDaerah');\""));
	    	context.put("selectAHMukim", HTML.SelectMukim("HTAAH_MUKIM", Utils.parseLong(SOC_AHMUKIM), DISABLE_JKPTG));
	    	context.put("selectAHSeksyen", HTML.SelectSeksyen("HTAAH_SEKSYEN", Utils.parseLong(HTAAH_SEKSYEN), DISABLE_JKPTG));
	    	context.put("selectAHJenisHakmilik", HTML.SelectJenisHakmilik("HTAAH_JENISHAKMILIK", Utils.parseLong(HTAAH_JENISHAKMILIK), DISABLE_JKPTG));
	    	context.put("HTAAH_NOHAKMILIK", HTAAH_NOHAKMILIK);
	    	context.put("HTAAH_NOPTLOT", HTAAH_NOPTLOT);
	    	context.put("selectAHJenisPTLot", modelSEWA.SelectJenisPTLot("HTAAH_JENISPTLOT", Utils.parseLong(HTAAH_JENISPTLOT), DISABLE_JKPTG));
	    	context.put("HTAAH_NOBANGUNAN", HTAAH_NOBANGUNAN);
	    	context.put("selectAHJenisNoBangunan", modelSEWA.SelectJenisNoBangunan("HTAAH_JENISNOBANGUNAN", Utils.parseLong(HTAAH_JENISNOBANGUNAN), DISABLE_JKPTG));
	    	context.put("HTAAH_BASIMATI", HTAAH_BASIMATI);
	    	context.put("HTAAH_BBSIMATI", HTAAH_BBSIMATI);
	    	context.put("selectAHJenisPegangan", modelSEWA.SelectJenisPegangan("HTAAH_JENISPEGANGAN", Utils.parseLong(HTAAH_JENISPEGANGAN), DISABLE_JKPTG, "onchange=\"toggleJenisPegangan();\""));
	    	context.put("HTAAH_TARIKHLUPUTPAJAKAN", HTAAH_TARIKHLUPUTPAJAKAN);
	    	context.put("HTAAH_TEMPOHPAJAKAN", HTAAH_TEMPOHPAJAKAN);
	    	context.put("selectAHKategoriTanah", modelSEWA.SelectKategoriTanah("HTAAH_KATEGORITANAH", Utils.parseLong(HTAAH_KATEGORITANAH), DISABLE_JKPTG));
	    	context.put("HTAAH_SYARATNYATA", HTAAH_SYARATNYATA);
	    	context.put("HTAAH_SEKATANKEPENTINGAN", HTAAH_SEKATANKEPENTINGAN);
	    	context.put("HTAAH_LUASTANAH", HTAAH_LUASTANAH);
	    	context.put("selectAHJenisLuasTanah", modelSEWA.SelectJenisLuasTanah("HTAAH_JENISLUASTANAH", Utils.parseLong(HTAAH_JENISLUASTANAH), HTAAH_JENISLUASTANAH));
	    	context.put("HTAAH_LUASPETAK", HTAAH_LUASPETAK);
	    	context.put("HTAAH_ALAMAT1HARTA", HTAAH_ALAMAT1HARTA);
	    	context.put("HTAAH_ALAMAT2HARTA", HTAAH_ALAMAT2HARTA);
	    	context.put("HTAAH_ALAMAT3HARTA", HTAAH_ALAMAT3HARTA);
	    	context.put("selectAHJenisHartaDinilai", modelSEWA.SelectJenisHartaDinilai("HTAAH_JENISHARTADINILAI", Utils.parseLong(HTAAH_JENISHARTADINILAI), DISABLE_JKPTG));
	    	context.put("HTAAH_CATATAN", HTAAH_CATATAN);
	    	context.put("HTAAH_NAMAPEGAWAIJKPTG", HTAAH_NAMAPEGAWAIJKPTG);
	    	context.put("HTAAH_NILAITARIKHMOHONTANAH", HTAAH_NILAITARIKHMOHONTANAH);
	    	context.put("HTAAH_NILAITARIKHMOHONBANGUNAN", HTAAH_NILAITARIKHMOHONBANGUNAN);
	    	context.put("HTAAH_NILAITARIKHMATITANAH", HTAAH_NILAITARIKHMATITANAH);
	    	context.put("HTAAH_NILAITARIKHMATIBANGUNAN", HTAAH_NILAITARIKHMATIBANGUNAN);
	    	context.put("HTAAH_NAMAPEGAWAI", HTAAH_NAMAPEGAWAI);
	    	context.put("HTAAH_CAWANGANJPPH", HTAAH_CAWANGANJPPH);
	    	context.put("HTAAH_JPPHCATATAN", HTAAH_JPPHCATATAN);
	    	
	    	context.put("selectTHNegeri", HTML.SelectNegeri("HTATH_NEGERI", Utils.parseLong(HTATH_NEGERI), DISABLE_JKPTG, "onchange=\"doChangeSOC('changeSOCTHNegeri');\""));
	    	context.put("selectTHDaerah", HTML.SelectDaerah("HTATH_DAERAH", Utils.parseLong(HTATH_DAERAH), DISABLE_JKPTG, "onchange=\"doChangeSOC('changeSOCTHDaerah');\""));
	    	context.put("selectTHMukim", HTML.SelectMukim("HTATH_MUKIM", Utils.parseLong(HTATH_MUKIM), DISABLE_JKPTG));
	    	context.put("selectTHSeksyen", HTML.SelectSeksyen("HTATH_SEKSYEN", Utils.parseLong(HTATH_SEKSYEN), DISABLE_JKPTG));
	    	context.put("HTATH_NOPTLOT", HTATH_NOPTLOT);
	    	context.put("selectTHJenisPTLot", modelSEWA.SelectJenisPTLot("HTATH_JENISPTLOT", Utils.parseLong(HTATH_JENISPTLOT), DISABLE_JKPTG));
	    	context.put("HTATH_NOBANGUNAN", HTATH_NOBANGUNAN);
	    	context.put("selectTHJenisNoBangunan", modelSEWA.SelectJenisNoBangunan("HTATH_JENISNOBANGUNAN", Utils.parseLong(HTATH_JENISNOBANGUNAN), DISABLE_JKPTG));
	    	context.put("HTATH_BASIMATI", HTATH_BASIMATI);
	    	context.put("HTATH_BBSIMATI", HTATH_BBSIMATI);
	    	context.put("selectTHJenisPegangan", modelSEWA.SelectJenisPegangan("HTATH_JENISPEGANGAN", Utils.parseLong(HTATH_JENISPEGANGAN), DISABLE_JKPTG, "onchange=\"toggleJenisPegangan();\""));
	    	context.put("HTATH_TARIKHLUPUTPAJAKAN", HTATH_TARIKHLUPUTPAJAKAN);
	    	context.put("HTATH_TEMPOHPAJAKAN", HTATH_TEMPOHPAJAKAN);
	    	context.put("selectTHKategoriTanah", modelSEWA.SelectKategoriTanah("HTATH_KATEGORITANAH", Utils.parseLong(HTATH_KATEGORITANAH), DISABLE_JKPTG));
	    	context.put("HTATH_SYARATNYATA", HTATH_SYARATNYATA);
	    	context.put("HTATH_SEKATANKEPENTINGAN", HTATH_SEKATANKEPENTINGAN);
	    	context.put("HTATH_ALAMAT1HARTA", HTATH_ALAMAT1HARTA);
	    	context.put("HTATH_ALAMAT2HARTA", HTATH_ALAMAT2HARTA);
	    	context.put("HTATH_ALAMAT3HARTA", HTATH_ALAMAT3HARTA);
	    	context.put("HTATH_NAMAPEMILIK", HTATH_NAMAPEMILIK);
	    	context.put("HTATH_TARIKHPERJANJIANJUALBELI", HTATH_TARIKHPERJANJIANJUALBELI);
	    	context.put("HTATH_NAMAPEMAJU", HTATH_NAMAPEMAJU);
	    	context.put("HTATH_LUASPETAK", HTATH_LUASPETAK);
	    	context.put("HTATH_CATATAN", HTATH_CATATAN);
	    	context.put("HTATH_NAMAPEGAWAIJKPTG", HTATH_NAMAPEGAWAIJKPTG);
	    	context.put("HTATH_NILAITARIKHMOHONTANAH", HTATH_NILAITARIKHMOHONTANAH);
	    	context.put("HTATH_NILAITARIKHMOHONBANGUNAN", HTATH_NILAITARIKHMOHONBANGUNAN);
	    	context.put("HTATH_NILAITARIKHMATITANAH", HTATH_NILAITARIKHMATITANAH);
	    	context.put("HTATH_NILAITARIKHMATIBANGUNAN", HTATH_NILAITARIKHMATIBANGUNAN);
	    	context.put("HTATH_NAMAPEGAWAI", HTATH_NAMAPEGAWAI);
	    	context.put("HTATH_CAWANGANJPPH", HTATH_CAWANGANJPPH);
	    	context.put("HTATH_JPPHCATATAN", HTATH_JPPHCATATAN);
	    	
	    	context.put("DISABLE_JKPTG", DISABLE_JKPTG);
	    	context.put("DISABLE_JPPH", DISABLE_JPPH);
	    	context.put("DISABLE_PAJAKAN", DISABLE_PAJAKAN);
        }
        if ("changeSOCAHNegeri".equalsIgnoreCase(command)) {
        	context.put("selectAHNegeri", HTML.SelectNegeri("HTAAH_NEGERI", Utils.parseLong(SOC_AHNEGERI), DISABLE_JKPTG, "onchange=\"doChangeSOC('changeSOCAHNegeri');\""));
        	context.put("selectAHDaerah", HTML.SelectDaerahByNegeri(SOC_AHNEGERI, "HTAAH_DAERAH", Utils.parseLong(SOC_AHDAERAH), DISABLE_JKPTG, "onchange=\"doChangeSOC('changeSOCAHDaerah');\""));
        	context.put("selectAHMukim", HTML.SelectMukimByDaerah(SOC_AHDAERAH, "HTAAH_MUKIM", Utils.parseLong(SOC_AHMUKIM), DISABLE_JKPTG));
        } else if ("changeSOCAHDaerah".equalsIgnoreCase(command)) {
        	context.put("selectAHDaerah", HTML.SelectDaerahByNegeri(SOC_AHNEGERI, "HTAAH_DAERAH", Utils.parseLong(SOC_AHDAERAH), DISABLE_JKPTG, "onchange=\"doChangeSOC('changeSOCAHDaerah');\""));
        	context.put("selectAHMukim", HTML.SelectMukimByDaerah(SOC_AHDAERAH, "HTAAH_MUKIM", Utils.parseLong(SOC_AHMUKIM), DISABLE_JKPTG));
        }
        if ("changeSOCTHNegeri".equalsIgnoreCase(command)) {
        	context.put("selectTHNegeri", HTML.SelectNegeri("HTATH_NEGERI", Utils.parseLong(SOC_THNEGERI), DISABLE_JKPTG, "onchange=\"doChangeSOC('changeSOCTHNegeri');\""));
        	context.put("selectTHDaerah", HTML.SelectDaerahByNegeri(SOC_THNEGERI, "HTATH_DAERAH", Utils.parseLong(SOC_THDAERAH), DISABLE_JKPTG, "onchange=\"doChangeSOC('changeSOCTHDaerah');\""));
        	context.put("selectTHMukim", HTML.SelectMukimByDaerah(SOC_THDAERAH, "HTATH_MUKIM", Utils.parseLong(SOC_THMUKIM), DISABLE_JKPTG));
        } else if ("changeSOCTHDaerah".equalsIgnoreCase(command)) {
        	context.put("selectTHDaerah", HTML.SelectDaerahByNegeri(SOC_THNEGERI, "HTATH_DAERAH", Utils.parseLong(SOC_THDAERAH), DISABLE_JKPTG, "onchange=\"doChangeSOC('changeSOCTHDaerah');\""));
        	context.put("selectTHMukim", HTML.SelectMukimByDaerah(SOC_THDAERAH, "HTATH_MUKIM", Utils.parseLong(SOC_THMUKIM), DISABLE_JKPTG));
        }
        context.put("ID_PERMOHONAN", ID_PERMOHONAN);
        context.put("ID_SIMATI", ID_SIMATI);
        context.put("ID_PEMOHON", ID_PEMOHON);
        context.put("ID_HTAAH", ID_HTAAH);
        context.put("ID_HTATH", ID_HTATH);
        context.put("mode", mode);
        context.put("action2", action);
		return vm;
	}
	
	private void populatePage(Hashtable h) throws Exception {
		if (!h.isEmpty()) {
			MP_IDFAIL = (String) h.get("MP_IDFAIL");
			MP_NOFAIL = (String) h.get("MP_NOFAIL");
			MP_NOPERMOHONAN = (String) h.get("MP_NOPERMOHONAN");
			MP_IDNEGERI = (String) h.get("MP_IDNEGERI");
			MP_IDDAERAH = (String) h.get("MP_IDDAERAH");
			MP_IDUNIT = (String) h.get("MP_IDUNIT");
			MP_NEGERI = (String) h.get("MP_NEGERI");
			MP_DAERAH = (String) h.get("MP_DAERAH");
			MP_UNIT = (String) h.get("MP_UNIT");
			MP_IDSIMATI = (String) h.get("MP_IDSIMATI");
			MP_NAMASIMATI = (String) h.get("MP_NAMASIMATI");
			MP_TARIKHMATI = (String) h.get("MP_TARIKHMATI");
			MP_NOKPSIMATI = (String) h.get("MP_NOKPSIMATI");
			MP_IDPEMOHON = (String) h.get("MP_IDPEMOHON");
			MP_NAMAPEMOHON = (String) h.get("MP_NAMAPEMOHON");
			MP_NOKPPEMOHON = (String) h.get("MP_NOKPPEMOHON");
			MP_ALAMAT1PEMOHON = (String) h.get("MP_ALAMAT1PEMOHON");
			MP_ALAMAT2PEMOHON = (String) h.get("MP_ALAMAT2PEMOHON");
			MP_ALAMAT3PEMOHON = (String) h.get("MP_ALAMAT3PEMOHON");
			MP_ALAMATPEMOHON = (String) h.get("MP_ALAMATPEMOHON");
			MP_NOTELPEMOHON = (String) h.get("MP_NOTELPEMOHON");
		}
	}
	
	private void populatePageHTAAH(Hashtable h) throws Exception {
		if (!h.isEmpty()) {
			HTAAH_NEGERI = (String) h.get("HTAAH_NEGERI");
			HTAAH_DAERAH = (String) h.get("HTAAH_DAERAH");
			HTAAH_MUKIM = (String) h.get("HTAAH_MUKIM");
			HTAAH_SEKSYEN = (String) h.get("HTAAH_SEKSYEN");
			HTAAH_JENISHAKMILIK = (String) h.get("HTAAH_JENISHAKMILIK");
			HTAAH_NOHAKMILIK = (String) h.get("HTAAH_NOHAKMILIK");
			HTAAH_NOPTLOT = (String) h.get("HTAAH_NOPTLOT");
			HTAAH_JENISPTLOT = (String) h.get("HTAAH_JENISPTLOT");
			HTAAH_NOBANGUNAN = (String) h.get("HTAAH_NOBANGUNAN");
			HTAAH_JENISNOBANGUNAN = (String) h.get("HTAAH_JENISNOBANGUNAN");
			HTAAH_BASIMATI = (String) h.get("HTAAH_BASIMATI");
			HTAAH_BBSIMATI = (String) h.get("HTAAH_BBSIMATI");
			HTAAH_JENISPEGANGAN = (String) h.get("HTAAH_JENISPEGANGAN");
			HTAAH_TARIKHLUPUTPAJAKAN = (String) h.get("HTAAH_TARIKHLUPUTPAJAKAN");
			HTAAH_TEMPOHPAJAKAN = (String) h.get("HTAAH_TEMPOHPAJAKAN");
			HTAAH_KATEGORITANAH = (String) h.get("HTAAH_KATEGORITANAH");
			HTAAH_SYARATNYATA = (String) h.get("HTAAH_SYARATNYATA");
			HTAAH_SEKATANKEPENTINGAN = (String) h.get("HTAAH_SEKATANKEPENTINGAN");
			HTAAH_LUASTANAH = (String) h.get("HTAAH_LUASTANAH");
			HTAAH_JENISLUASTANAH = (String) h.get("HTAAH_JENISLUASTANAH");
			HTAAH_LUASPETAK = (String) h.get("HTAAH_LUASPETAK");
			HTAAH_ALAMAT1HARTA = (String) h.get("HTAAH_ALAMAT1HARTA");
			HTAAH_ALAMAT2HARTA = (String) h.get("HTAAH_ALAMAT2HARTA");
			HTAAH_ALAMAT3HARTA = (String) h.get("HTAAH_ALAMAT3HARTA");
			HTAAH_JENISHARTADINILAI = (String) h.get("HTAAH_JENISHARTADINILAI");
			HTAAH_CATATAN = (String) h.get("HTAAH_CATATAN");
	    	HTAAH_NAMAPEGAWAIJKPTG = (String) h.get("HTAAH_NAMAPEGAWAIJKPTG");
			HTAAH_NILAITARIKHMOHONTANAH = (String) h.get("HTAAH_NILAITARIKHMOHONTANAH");
			HTAAH_NILAITARIKHMOHONBANGUNAN = (String) h.get("HTAAH_NILAITARIKHMOHONBANGUNAN");
			HTAAH_NILAITARIKHMATITANAH = (String) h.get("HTAAH_NILAITARIKHMATITANAH");
			HTAAH_NILAITARIKHMATIBANGUNAN = (String) h.get("HTAAH_NILAITARIKHMATIBANGUNAN");
			HTAAH_NAMAPEGAWAI = (String) h.get("HTAAH_NAMAPEGAWAI");
			HTAAH_CAWANGANJPPH = (String) h.get("HTAAH_CAWANGANJPPH");
			HTAAH_JPPHCATATAN = (String) h.get("HTAAH_JPPHCATATAN");	
			SOC_AHNEGERI = (String) h.get("HTAAH_NEGERI");
			SOC_AHDAERAH = (String) h.get("HTAAH_DAERAH");
			SOC_AHMUKIM = (String) h.get("HTAAH_MUKIM");
		}
	}
	
	private void populatePageHTATH(Hashtable h) throws Exception {
		if (!h.isEmpty()) {
			HTATH_NEGERI = (String) h.get("HTATH_NEGERI");
			HTATH_DAERAH = (String) h.get("HTATH_DAERAH");
			HTATH_MUKIM = (String) h.get("HTATH_MUKIM");
			HTATH_SEKSYEN = (String) h.get("HTATH_SEKSYEN");
			HTATH_JENISPTLOT = (String) h.get("HTATH_JENISPTLOT");
			HTATH_NOPTLOT = (String) h.get("HTATH_NOPTLOT");
			HTATH_NOBANGUNAN = (String) h.get("HTATH_NOBANGUNAN");
			HTATH_JENISNOBANGUNAN = (String) h.get("HTATH_JENISNOBANGUNAN");
			HTATH_BASIMATI = (String) h.get("HTATH_BASIMATI");
			HTATH_BBSIMATI = (String) h.get("HTATH_BBSIMATI");
			HTATH_JENISPEGANGAN = (String) h.get("HTATH_JENISPEGANGAN");
			HTATH_TARIKHLUPUTPAJAKAN = (String) h.get("HTATH_TARIKHLUPUTPAJAKAN");
			HTATH_TEMPOHPAJAKAN = (String) h.get("HTATH_TEMPOHPAJAKAN");
			HTATH_KATEGORITANAH = (String) h.get("HTATH_KATEGORITANAH");
			HTATH_SYARATNYATA = (String) h.get("HTATH_SYARATNYATA");
			HTATH_SEKATANKEPENTINGAN = (String) h.get("HTATH_SEKATANKEPENTINGAN");
			HTATH_ALAMAT1HARTA = (String) h.get("HTATH_ALAMAT1HARTA");
			HTATH_ALAMAT2HARTA = (String) h.get("HTATH_ALAMAT2HARTA");
			HTATH_ALAMAT3HARTA = (String) h.get("HTATH_ALAMAT3HARTA");
			HTATH_NAMAPEMILIK = (String) h.get("HTATH_NAMAPEMILIK");
			HTATH_TARIKHPERJANJIANJUALBELI = (String) h.get("HTATH_TARIKHPERJANJIANJUALBELI");
			HTATH_NAMAPEMAJU = (String) h.get("HTATH_NAMAPEMAJU");
			HTATH_LUASPETAK = (String) h.get("HTATH_LUASPETAK");
			HTATH_CATATAN = (String) h.get("HTATH_CATATAN");
	    	HTATH_NAMAPEGAWAIJKPTG = (String) h.get("HTATH_NAMAPEGAWAIJKPTG");
			HTATH_NILAITARIKHMOHONTANAH = (String) h.get("HTATH_NILAITARIKHMOHONTANAH");
			HTATH_NILAITARIKHMOHONBANGUNAN = (String) h.get("HTATH_NILAITARIKHMOHONBANGUNAN");
			HTATH_NILAITARIKHMATITANAH = (String) h.get("HTATH_NILAITARIKHMATITANAH");
			HTATH_NILAITARIKHMATIBANGUNAN = (String) h.get("HTATH_NILAITARIKHMATIBANGUNAN");
			HTATH_NAMAPEGAWAI = (String) h.get("HTATH_NAMAPEGAWAI");
			HTATH_CAWANGANJPPH = (String) h.get("HTATH_CAWANGANJPPH");
			HTATH_JPPHCATATAN = (String) h.get("HTATH_JPPHCATATAN");
			SOC_THNEGERI = (String) h.get("HTAAH_NEGERI");
			SOC_THDAERAH = (String) h.get("HTAAH_DAERAH");
			SOC_THMUKIM = (String) h.get("HTAAH_MUKIM");
		}
	}

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