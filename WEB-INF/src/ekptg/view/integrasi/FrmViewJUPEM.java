package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.Paging;
import ekptg.intergration.XEkptgEmailSender;
import ekptg.intergration.FileUploadProperty;
import ekptg.model.integrasi.FrmModelJUPEM;
import ekptg.model.integrasi.FrmModelUtilitiesIntegration;

public class FrmViewJUPEM extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
	String NAMA_FAIL = "";
    
    String userID = "", userName = "", userRole = "";
	
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		String vm = "app/integrasi/frmJUPEM.jsp";

        HttpSession session = this.request.getSession();
        
        FrmModelJUPEM logic = new FrmModelJUPEM();
        
        // action
        String action = getParam("action");
        String action2 = getParam("action2");
        String mode = getParam("mode");
        
        Boolean doUpload = false, noData = false;
	    
        userID = (String) session.getAttribute("_ekptg_user_id");
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        
        //----------------------------
        // maklumat hakmilik
        String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
        String ID_HAKMILIK = getParam("ID_HAKMILIK");
        
        if ("".equalsIgnoreCase(ID_PERMOHONAN) || "".equalsIgnoreCase(ID_HAKMILIK)) {
        	noData = true;
        }
        
        Vector vHM = new Vector();
        Hashtable hHM = new Hashtable();
        hHM.put("ID_PERMOHONAN", ID_PERMOHONAN);
        hHM.put("ID_HAKMILIK", ID_HAKMILIK);
        vHM.add(hHM);
        vHM = logic.getMaklumatHakmilik(vHM);
    	String DAERAH = "", MUKIM = "", JENIS_HAKMILIK = "", NO_HAKMILIK = "", NO_LOTPT = "", TARIKH_DAFTAR_BORANGK = "", LUAS_DIAMBIL = "", TARIKH_SURAT_PTG = "", NO_SYIT = "";
        if (!vHM.isEmpty()) {
        	hHM = (Hashtable) vHM.get(0);
        	if (!hHM.isEmpty()) {
	        	DAERAH = (String) hHM.get("DAERAH");
	        	MUKIM = (String) hHM.get("MUKIM");
	        	JENIS_HAKMILIK = (String) hHM.get("JENIS_HAKMILIK");
	        	NO_HAKMILIK = (String) hHM.get("NO_HAKMILIK");
	        	NO_LOTPT = (String) hHM.get("NO_LOTPT");
	        	TARIKH_DAFTAR_BORANGK = (String) hHM.get("TARIKH_DAFTAR_BORANGK");
	        	LUAS_DIAMBIL = (String) hHM.get("LUAS_DIAMBIL");
	        	TARIKH_SURAT_PTG = (String) hHM.get("TARIKH_SURAT_PTG");
	        	NO_SYIT = (String) hHM.get("NO_SYIT");
        	}
        }
        context.put("DAERAH", DAERAH);
        context.put("MUKIM", MUKIM);
        context.put("JENIS_HAKMILIK", JENIS_HAKMILIK);
        context.put("NO_HAKMILIK", NO_HAKMILIK);
        context.put("NO_LOTPT", NO_LOTPT);
        context.put("TARIKH_DAFTAR_BORANGK", TARIKH_DAFTAR_BORANGK);
        context.put("LUAS_DIAMBIL", LUAS_DIAMBIL);
        context.put("TARIKH_SURAT_PTG", TARIKH_SURAT_PTG);
        context.put("NO_SYIT", NO_SYIT);
        //----------------------------
        
        //----------------------------
        // PUB
        Vector vPUB = new Vector();
        Hashtable hPUB = new Hashtable();;        
        
        Boolean viewPUBField = false, savePUBData = false;
        String DATA_PUB_01 = "", DATA_PUB_02 = "", DATA_PUB_03 = "", DATA_PUB_04 = "", DATA_PUB_05 = "", DATA_PUB_06 = "", DATA_PUB_07 = "", DATA_PUB_08 = "", DATA_PUB_09 = "", DATA_PUB_10 = "";
		String DATA_PUB_11 = "", DATA_PUB_12 = "", DATA_PUB_13 = "", DATA_PUB_14 = "", DATA_PUB_15 = "", DATA_PUB_16 = "", DATA_PUB_17 = "", DATA_PUB_18 = "", DATA_PUB_19 = "", DATA_PUB_20 = "";
		String DATA_PUB_21 = "", DATA_PUB_22 = "", DATA_PUB_23 = "", DATA_PUB_24 = "", DATA_PUB_25 = "", DATA_PUB_26 = "";
        String ID_JUPEMPUB = getParam("ID_JUPEMPUB");
		DATA_PUB_01 = getParam("DATA_PUB_01");
		DATA_PUB_02 = getParam("DATA_PUB_02");
		DATA_PUB_03 = getParam("DATA_PUB_03");
		DATA_PUB_04 = getParam("DATA_PUB_04");
		DATA_PUB_05 = getParam("DATA_PUB_05");
		DATA_PUB_06 = getParam("DATA_PUB_06");
		DATA_PUB_07 = getParam("DATA_PUB_07");
		DATA_PUB_08 = getParam("DATA_PUB_08");
		DATA_PUB_09 = getParam("DATA_PUB_09");
		DATA_PUB_10 = getParam("DATA_PUB_10");
		DATA_PUB_11 = getParam("DATA_PUB_11");
		DATA_PUB_12 = getParam("DATA_PUB_12");
		DATA_PUB_13 = getParam("DATA_PUB_13");
		DATA_PUB_14 = getParam("DATA_PUB_14");
		DATA_PUB_15 = getParam("DATA_PUB_15");
		DATA_PUB_16 = getParam("DATA_PUB_16");
		DATA_PUB_17 = getParam("DATA_PUB_17");
		DATA_PUB_18 = getParam("DATA_PUB_18");
		DATA_PUB_19 = getParam("DATA_PUB_19");
		DATA_PUB_20 = getParam("DATA_PUB_20");
		DATA_PUB_21 = getParam("DATA_PUB_21");
		DATA_PUB_22 = getParam("DATA_PUB_22");
		DATA_PUB_23 = getParam("DATA_PUB_23");
		DATA_PUB_24 = getParam("DATA_PUB_24");
		DATA_PUB_25 = getParam("DATA_PUB_25");
		DATA_PUB_26 = getParam("DATA_PUB_26");
		
		if (!DATA_PUB_06.startsWith("PT")) {
			DATA_PUB_06 = "PT" + DATA_PUB_06;
		}
        if ("addRowPUB".equalsIgnoreCase(mode)) {
        	viewPUBField = true;
        } else if ("savePUBData".equalsIgnoreCase(mode)) {
        	hPUB = new Hashtable();
            hPUB.put("ID_PERMOHONAN", ID_PERMOHONAN);
            hPUB.put("ID_HAKMILIK", ID_HAKMILIK);
            hPUB.put("ID_USER", userID);
            hPUB.put("ID_JUPEMPUB", ID_JUPEMPUB);
            hPUB.put("DATA_PUB_01", DATA_PUB_01);
            hPUB.put("DATA_PUB_02", DATA_PUB_02);
            hPUB.put("DATA_PUB_03", DATA_PUB_03);
            hPUB.put("DATA_PUB_04", DATA_PUB_04);
            hPUB.put("DATA_PUB_05", DATA_PUB_05);
            hPUB.put("DATA_PUB_06", DATA_PUB_06);
            hPUB.put("DATA_PUB_07", DATA_PUB_07);
            hPUB.put("DATA_PUB_08", DATA_PUB_08);
            hPUB.put("DATA_PUB_09", DATA_PUB_09);
            hPUB.put("DATA_PUB_10", DATA_PUB_10);
            hPUB.put("DATA_PUB_11", DATA_PUB_11);
            hPUB.put("DATA_PUB_12", DATA_PUB_12);
            hPUB.put("DATA_PUB_13", DATA_PUB_13);
            hPUB.put("DATA_PUB_14", DATA_PUB_14);
            hPUB.put("DATA_PUB_15", DATA_PUB_15);
            hPUB.put("DATA_PUB_16", DATA_PUB_16);
            hPUB.put("DATA_PUB_17", DATA_PUB_17);
            hPUB.put("DATA_PUB_18", DATA_PUB_18);
            hPUB.put("DATA_PUB_19", DATA_PUB_19);
            hPUB.put("DATA_PUB_20", DATA_PUB_20);
            hPUB.put("DATA_PUB_21", DATA_PUB_21);
            hPUB.put("DATA_PUB_22", DATA_PUB_22);
            hPUB.put("DATA_PUB_23", DATA_PUB_23);
            hPUB.put("DATA_PUB_24", DATA_PUB_24);
            hPUB.put("DATA_PUB_25", DATA_PUB_25);
            hPUB.put("DATA_PUB_26", DATA_PUB_26);
            vPUB.add(hPUB);
            logic.saveMainData(vPUB);
        	savePUBData = logic.savePUBData(vPUB);
        } else if ("DATA_PUB_01".equalsIgnoreCase(mode) || "DATA_PUB_02".equalsIgnoreCase(mode)) {
        	viewPUBField = true;	
        } else if ("getPUBData".equalsIgnoreCase(mode)) {
        	hPUB = new Hashtable();
            hPUB.put("ID_JUPEMPUB", ID_JUPEMPUB);
            vPUB.add(hPUB);
            vPUB = logic.getPUBData(vPUB);
            if (!vPUB.isEmpty()) {
            	hPUB = (Hashtable) vPUB.get(0);
            	if (!hPUB.isEmpty()) {
                	viewPUBField = true;
            		DATA_PUB_01 = (String) hPUB.get("DATA_PUB_01");
            		DATA_PUB_02 = (String) hPUB.get("DATA_PUB_02");
            		DATA_PUB_03 = (String) hPUB.get("DATA_PUB_03");
            		DATA_PUB_04 = (String) hPUB.get("DATA_PUB_04");
            		DATA_PUB_05 = (String) hPUB.get("DATA_PUB_05");
            		DATA_PUB_06 = (String) hPUB.get("DATA_PUB_06");
            		DATA_PUB_07 = (String) hPUB.get("DATA_PUB_07");
            		DATA_PUB_08 = (String) hPUB.get("DATA_PUB_08");
            		DATA_PUB_09 = (String) hPUB.get("DATA_PUB_09");
            		DATA_PUB_10 = (String) hPUB.get("DATA_PUB_10");
            		DATA_PUB_11 = (String) hPUB.get("DATA_PUB_11");
            		DATA_PUB_12 = (String) hPUB.get("DATA_PUB_12");
            		DATA_PUB_13 = (String) hPUB.get("DATA_PUB_13");
            		DATA_PUB_14 = (String) hPUB.get("DATA_PUB_14");
            		DATA_PUB_15 = (String) hPUB.get("DATA_PUB_15");
            		DATA_PUB_16 = (String) hPUB.get("DATA_PUB_16");
            		DATA_PUB_17 = (String) hPUB.get("DATA_PUB_17");
            		DATA_PUB_18 = (String) hPUB.get("DATA_PUB_18");
            		DATA_PUB_19 = (String) hPUB.get("DATA_PUB_19");
            		DATA_PUB_20 = (String) hPUB.get("DATA_PUB_20");
            		DATA_PUB_21 = (String) hPUB.get("DATA_PUB_21");
            		DATA_PUB_22 = (String) hPUB.get("DATA_PUB_22");
            		DATA_PUB_23 = (String) hPUB.get("DATA_PUB_23");
            		DATA_PUB_24 = (String) hPUB.get("DATA_PUB_24");
            		DATA_PUB_25 = (String) hPUB.get("DATA_PUB_25");
            		DATA_PUB_26 = (String) hPUB.get("DATA_PUB_26");            		
            	}
            }
        }
        context.put("viewPUBField", viewPUBField);
        context.put("savePUBData", savePUBData);
        
        vPUB.clear();
        hPUB.clear();
        hPUB.put("ID_PERMOHONAN", ID_PERMOHONAN);
        hPUB.put("ID_HAKMILIK", ID_HAKMILIK);
        vPUB.add(hPUB);
        vPUB = logic.getListPUB(vPUB);
        context.put("ListDataPUB", vPUB);
        
        if (viewPUBField) {
			context.put("DATA_PUB_01", DATA_PUB_01);
			context.put("DATA_PUB_02", DATA_PUB_02);
			context.put("DATA_PUB_03", DATA_PUB_03);
			context.put("DATA_PUB_04", DATA_PUB_04);
			context.put("DATA_PUB_05", DATA_PUB_05);
			context.put("DATA_PUB_06", DATA_PUB_06);
			context.put("DATA_PUB_07", DATA_PUB_07);
			context.put("DATA_PUB_08", DATA_PUB_08);
			context.put("DATA_PUB_09", DATA_PUB_09);
			context.put("DATA_PUB_10", DATA_PUB_10);
			context.put("DATA_PUB_11", DATA_PUB_11);
			context.put("DATA_PUB_12", DATA_PUB_12);
			context.put("DATA_PUB_13", DATA_PUB_13);
			context.put("DATA_PUB_14", DATA_PUB_14);
			context.put("DATA_PUB_15", DATA_PUB_15);
			context.put("DATA_PUB_16", DATA_PUB_16);
			context.put("DATA_PUB_17", DATA_PUB_17);
			context.put("DATA_PUB_18", DATA_PUB_18);
			context.put("DATA_PUB_19", DATA_PUB_19);
			context.put("DATA_PUB_20", DATA_PUB_20);
			context.put("DATA_PUB_21", DATA_PUB_21);
			context.put("DATA_PUB_22", DATA_PUB_22);
			context.put("DATA_PUB_23", DATA_PUB_23);
			context.put("DATA_PUB_24", DATA_PUB_24);
			context.put("DATA_PUB_25", DATA_PUB_25);
			context.put("DATA_PUB_26", DATA_PUB_26);
        }
        
        String SOC_NEGERI_PUB = "", SOC_DAERAH_PUB = "", SOC_MUKIM_PUB = "", SOC_MARKDESC_FROM = "", SOC_MARKDESC_TO = "", SOC_CLASS = "", SOC_LINECODE = "", SOC_LINETYPE = "";
        SOC_NEGERI_PUB = logic.getSOCNegeri("DATA_PUB_01", DATA_PUB_01, false, "onchange=\"javascript:doChangeSOC('DATA_PUB_01');\"");
        SOC_DAERAH_PUB = logic.getSOCDaerah("DATA_PUB_02", DATA_PUB_02, DATA_PUB_01, false, "onchange=\"javascript:doChangeSOC('DATA_PUB_02');\"");
        SOC_MUKIM_PUB = logic.getSOCMukim("DATA_PUB_03", DATA_PUB_03, DATA_PUB_02, DATA_PUB_01);
        SOC_MARKDESC_FROM = logic.getSOCMarkDesc("DATA_PUB_11", DATA_PUB_11);
        SOC_MARKDESC_TO = logic.getSOCMarkDesc("DATA_PUB_18", DATA_PUB_18);
        SOC_CLASS = logic.getSOCClass("DATA_PUB_24", DATA_PUB_24);
        SOC_LINECODE = logic.getSOCLineCode("DATA_PUB_25", DATA_PUB_25);
        SOC_LINETYPE = logic.getSOCLineType("DATA_PUB_26", DATA_PUB_26);
        
        context.put("SOC_NEGERI_PUB", SOC_NEGERI_PUB);
        context.put("SOC_DAERAH_PUB", SOC_DAERAH_PUB);
        context.put("SOC_MUKIM_PUB", SOC_MUKIM_PUB);
        context.put("SOC_MARKDESC_FROM", SOC_MARKDESC_FROM);
        context.put("SOC_MARKDESC_TO", SOC_MARKDESC_TO);
        context.put("SOC_CLASS", SOC_CLASS);
        context.put("SOC_LINECODE", SOC_LINECODE);
        context.put("SOC_LINETYPE", SOC_LINETYPE);
        
        context.put("ID_JUPEMPUB", ID_JUPEMPUB);
        //----------------------------
        
        //----------------------------
        // PUD
        Vector vPUD = new Vector();
        Hashtable hPUD = new Hashtable();        
        
        Boolean viewPUDField = false, savePUDData = false;
        String DATA_PUD_01 = "", DATA_PUD_02 = "", DATA_PUD_03 = "", DATA_PUD_04 = "", DATA_PUD_05 = "", DATA_PUD_06 = "", DATA_PUD_07 = "", DATA_PUD_08 = "", DATA_PUD_09 = "", DATA_PUD_10 = "";
		String DATA_PUD_11 = "", DATA_PUD_12 = "", DATA_PUD_13 = "", DATA_PUD_14 = "";
        String ID_JUPEMPUD = getParam("ID_JUPEMPUD");
		DATA_PUD_01 = getParam("DATA_PUD_01");
		DATA_PUD_02 = getParam("DATA_PUD_02");
		DATA_PUD_03 = getParam("DATA_PUD_03");
		DATA_PUD_04 = getParam("DATA_PUD_04");
		DATA_PUD_05 = getParam("DATA_PUD_05");
		DATA_PUD_06 = getParam("DATA_PUD_06");
		DATA_PUD_07 = getParam("DATA_PUD_07");
		DATA_PUD_08 = getParam("DATA_PUD_08");
		DATA_PUD_09 = getParam("DATA_PUD_09");
		DATA_PUD_10 = getParam("DATA_PUD_10");
		DATA_PUD_11 = getParam("DATA_PUD_11");
		DATA_PUD_12 = getParam("DATA_PUD_12");
		DATA_PUD_13 = getParam("DATA_PUD_13");
		DATA_PUD_14 = getParam("DATA_PUD_14");
		
        if ("addRowPUD".equalsIgnoreCase(mode)) {
        	viewPUDField = true;
        } else if ("savePUDData".equalsIgnoreCase(mode)) {
        	hPUD = new Hashtable();
            hPUD.put("ID_PERMOHONAN", ID_PERMOHONAN);
            hPUD.put("ID_HAKMILIK", ID_HAKMILIK);
            hPUD.put("ID_USER", userID);
            hPUD.put("ID_JUPEMPUD", ID_JUPEMPUD);
            hPUD.put("DATA_PUD_01", DATA_PUD_01);
            hPUD.put("DATA_PUD_02", DATA_PUD_02);
            hPUD.put("DATA_PUD_03", DATA_PUD_03);
            hPUD.put("DATA_PUD_04", DATA_PUD_04);
            hPUD.put("DATA_PUD_05", DATA_PUD_05);
            hPUD.put("DATA_PUD_06", DATA_PUD_06);
            hPUD.put("DATA_PUD_07", DATA_PUD_07);
            hPUD.put("DATA_PUD_08", DATA_PUD_08);
            hPUD.put("DATA_PUD_09", DATA_PUD_09);
            hPUD.put("DATA_PUD_10", DATA_PUD_10);
            hPUD.put("DATA_PUD_11", DATA_PUD_11);
            hPUD.put("DATA_PUD_12", DATA_PUD_12);
            hPUD.put("DATA_PUD_13", DATA_PUD_13);
            hPUD.put("DATA_PUD_14", DATA_PUD_14);
            vPUD.add(hPUD);
            logic.saveMainData(vPUD);
        	savePUDData = logic.savePUDData(vPUD);
        } else if ("DATA_PUD_01".equalsIgnoreCase(mode) || "DATA_PUD_02".equalsIgnoreCase(mode)) {
        	viewPUDField = true;	
        } else if ("getPUDData".equalsIgnoreCase(mode)) {
        	hPUD = new Hashtable();
            hPUD.put("ID_JUPEMPUD", ID_JUPEMPUD);
            vPUD.add(hPUD);
            vPUD = logic.getPUDData(vPUD);
            if (!vPUD.isEmpty()) {
            	hPUD = (Hashtable) vPUD.get(0);
            	if (!hPUD.isEmpty()) {
                	viewPUDField = true;
            		DATA_PUD_01 = (String) hPUD.get("DATA_PUD_01");
            		DATA_PUD_02 = (String) hPUD.get("DATA_PUD_02");
            		DATA_PUD_03 = (String) hPUD.get("DATA_PUD_03");
            		DATA_PUD_04 = (String) hPUD.get("DATA_PUD_04");
            		DATA_PUD_05 = (String) hPUD.get("DATA_PUD_05");
            		DATA_PUD_06 = (String) hPUD.get("DATA_PUD_06");
            		DATA_PUD_07 = (String) hPUD.get("DATA_PUD_07");
            		DATA_PUD_08 = (String) hPUD.get("DATA_PUD_08");
            		DATA_PUD_09 = (String) hPUD.get("DATA_PUD_09");
            		DATA_PUD_10 = (String) hPUD.get("DATA_PUD_10");
            		DATA_PUD_11 = (String) hPUD.get("DATA_PUD_11");
            		DATA_PUD_12 = (String) hPUD.get("DATA_PUD_12");
            		DATA_PUD_13 = (String) hPUD.get("DATA_PUD_13");
            		DATA_PUD_14 = (String) hPUD.get("DATA_PUD_14");            		
            	}
            }
        }
        context.put("viewPUDField", viewPUDField);
        context.put("savePUDData", savePUDData);
        
        vPUD.clear();
        hPUD.clear();
        hPUD.put("ID_PERMOHONAN", ID_PERMOHONAN);
        hPUD.put("ID_HAKMILIK", ID_HAKMILIK);
        vPUD.add(hPUD);
        vPUD = logic.getListPUD(vPUD);
        context.put("ListDataPUD", vPUD);
        
        if (viewPUDField) {
			context.put("DATA_PUD_01", DATA_PUD_01);
			context.put("DATA_PUD_02", DATA_PUD_02);
			context.put("DATA_PUD_03", DATA_PUD_03);
			context.put("DATA_PUD_04", DATA_PUD_04);
			context.put("DATA_PUD_05", DATA_PUD_05);
			context.put("DATA_PUD_06", DATA_PUD_06);
			context.put("DATA_PUD_07", DATA_PUD_07);
			context.put("DATA_PUD_08", DATA_PUD_08);
			context.put("DATA_PUD_09", DATA_PUD_09);
			context.put("DATA_PUD_10", DATA_PUD_10);
			context.put("DATA_PUD_11", DATA_PUD_11);
			context.put("DATA_PUD_12", DATA_PUD_12);
			context.put("DATA_PUD_13", DATA_PUD_13);
			context.put("DATA_PUD_14", DATA_PUD_14);
        }
         
        String SOC_NEGERI_PUD = "", SOC_DAERAH_PUD = "", SOC_MUKIM_PUD = "", SOC_LANDTITLE = "", SOC_LANDUSE = "";
        SOC_NEGERI_PUD = logic.getSOCNegeri("DATA_PUD_01", DATA_PUD_01, false, "onchange=\"javascript:doChangeSOC('DATA_PUD_01');\"");
        SOC_DAERAH_PUD = logic.getSOCDaerah("DATA_PUD_02", DATA_PUD_02, DATA_PUD_01, false, "onchange=\"javascript:doChangeSOC('DATA_PUD_02');\"");
        SOC_MUKIM_PUD = logic.getSOCMukim("DATA_PUD_03", DATA_PUD_03, DATA_PUD_02, DATA_PUD_01);
        SOC_LANDTITLE = logic.getSOCLandTitle("DATA_PUD_11", DATA_PUD_11);
        SOC_LANDUSE = logic.getSOCLandUse("DATA_PUD_12", DATA_PUD_12);
        
        context.put("SOC_NEGERI_PUD", SOC_NEGERI_PUD);
        context.put("SOC_DAERAH_PUD", SOC_DAERAH_PUD);
        context.put("SOC_MUKIM_PUD", SOC_MUKIM_PUD);
        context.put("SOC_LANDTITLE", SOC_LANDTITLE);
        context.put("SOC_LANDUSE", SOC_LANDUSE);
        
        context.put("ID_JUPEMPUD", ID_JUPEMPUD);
       	//----------------------------
        
        //----------------------------
        // PUL
        Vector vPUL = new Vector();
        Hashtable hPUL = new Hashtable();        
        
        Boolean viewPULField = false, savePULData = false;
        String DATA_PUL_01 = "", DATA_PUL_02 = "", DATA_PUL_03 = "", DATA_PUL_04 = "", DATA_PUL_05 = "", DATA_PUL_06 = "", DATA_PUL_07 = "", DATA_PUL_08 = "", DATA_PUL_09 = "", DATA_PUL_10 = "";
		String DATA_PUL_11 = "", DATA_PUL_12 = "", DATA_PUL_13 = "";
        String ID_JUPEMPUL = getParam("ID_JUPEMPUL");
		DATA_PUL_01 = getParam("DATA_PUL_01");
		DATA_PUL_02 = getParam("DATA_PUL_02");
		DATA_PUL_03 = getParam("DATA_PUL_03");
		DATA_PUL_04 = getParam("DATA_PUL_04");
		DATA_PUL_05 = getParam("DATA_PUL_05");
		DATA_PUL_06 = getParam("DATA_PUL_06");
		DATA_PUL_07 = getParam("DATA_PUL_07");
		DATA_PUL_08 = getParam("DATA_PUL_08");
		DATA_PUL_09 = getParam("DATA_PUL_09");
		DATA_PUL_10 = getParam("DATA_PUL_10");
		DATA_PUL_11 = getParam("DATA_PUL_11");
		DATA_PUL_12 = getParam("DATA_PUL_12");
		DATA_PUL_13 = getParam("DATA_PUL_13");
		
        if ("addRowPUL".equalsIgnoreCase(mode)) {
        	viewPULField = true;
        } else if ("savePULData".equalsIgnoreCase(mode)) {
        	hPUL = new Hashtable();
            hPUL.put("ID_PERMOHONAN", ID_PERMOHONAN);
            hPUL.put("ID_HAKMILIK", ID_HAKMILIK);
            hPUL.put("ID_USER", userID);
            hPUL.put("ID_JUPEMPUL", ID_JUPEMPUL);
            hPUL.put("DATA_PUL_01", DATA_PUL_01);
            hPUL.put("DATA_PUL_02", DATA_PUL_02);
            hPUL.put("DATA_PUL_03", DATA_PUL_03);
            hPUL.put("DATA_PUL_04", DATA_PUL_04);
            hPUL.put("DATA_PUL_05", DATA_PUL_05);
            hPUL.put("DATA_PUL_06", DATA_PUL_06);
            hPUL.put("DATA_PUL_07", DATA_PUL_07);
            hPUL.put("DATA_PUL_08", DATA_PUL_08);
            hPUL.put("DATA_PUL_09", DATA_PUL_09);
            hPUL.put("DATA_PUL_10", DATA_PUL_10);
            hPUL.put("DATA_PUL_11", DATA_PUL_11);
            hPUL.put("DATA_PUL_12", DATA_PUL_12);
            hPUL.put("DATA_PUL_13", DATA_PUL_13);
            vPUL.add(hPUL);
            logic.saveMainData(vPUL);
        	savePULData = logic.savePULData(vPUL);
        } else if ("DATA_PUL_01".equalsIgnoreCase(mode) || "DATA_PUL_02".equalsIgnoreCase(mode)) {
        	viewPULField = true;	
        } else if ("getPULData".equalsIgnoreCase(mode)) {
        	hPUL = new Hashtable();
            hPUL.put("ID_JUPEMPUL", ID_JUPEMPUL);
            vPUL.add(hPUL);
            vPUL = logic.getPULData(vPUL);
            if (!vPUL.isEmpty()) {
            	hPUL = (Hashtable) vPUL.get(0);
            	if (!hPUL.isEmpty()) {
                	viewPULField = true;
            		DATA_PUL_01 = (String) hPUL.get("DATA_PUL_01");
            		DATA_PUL_02 = (String) hPUL.get("DATA_PUL_02");
            		DATA_PUL_03 = (String) hPUL.get("DATA_PUL_03");
            		DATA_PUL_04 = (String) hPUL.get("DATA_PUL_04");
            		DATA_PUL_05 = (String) hPUL.get("DATA_PUL_05");
            		DATA_PUL_06 = (String) hPUL.get("DATA_PUL_06");
            		DATA_PUL_07 = (String) hPUL.get("DATA_PUL_07");
            		DATA_PUL_08 = (String) hPUL.get("DATA_PUL_08");
            		DATA_PUL_09 = (String) hPUL.get("DATA_PUL_09");
            		DATA_PUL_10 = (String) hPUL.get("DATA_PUL_10");
            		DATA_PUL_11 = (String) hPUL.get("DATA_PUL_11");
            		DATA_PUL_12 = (String) hPUL.get("DATA_PUL_12");
            		DATA_PUL_13 = (String) hPUL.get("DATA_PUL_13");
            	}
            }
        }
        context.put("viewPULField", viewPULField);
        context.put("savePULData", savePULData);
        
        vPUL.clear();
        hPUL.clear();
        hPUL.put("ID_PERMOHONAN", ID_PERMOHONAN);
        hPUL.put("ID_HAKMILIK", ID_HAKMILIK);
        vPUL.add(hPUL);
        vPUL = logic.getListPUL(vPUL);
        context.put("ListDataPUL", vPUL);
        
		if (viewPULField) {
			context.put("DATA_PUL_01", DATA_PUL_01);
			context.put("DATA_PUL_02", DATA_PUL_02);
			context.put("DATA_PUL_03", DATA_PUL_03);
			context.put("DATA_PUL_04", DATA_PUL_04);
			context.put("DATA_PUL_05", DATA_PUL_05);
			context.put("DATA_PUL_06", DATA_PUL_06);
			context.put("DATA_PUL_07", DATA_PUL_07);
			context.put("DATA_PUL_08", DATA_PUL_08);
			context.put("DATA_PUL_09", DATA_PUL_09);
			context.put("DATA_PUL_10", DATA_PUL_10);
			context.put("DATA_PUL_11", DATA_PUL_11);
			context.put("DATA_PUL_12", DATA_PUL_12);
			context.put("DATA_PUL_13", DATA_PUL_13);
		}
        
        String SOC_NEGERI_PUL = "", SOC_DAERAH_PUL = "", SOC_MUKIM_PUL = ""; 
        SOC_NEGERI_PUL = logic.getSOCNegeri("DATA_PUL_01", DATA_PUL_01, false, "onchange=\"javascript:doChangeSOC('DATA_PUL_01');\"");
        SOC_DAERAH_PUL = logic.getSOCDaerah("DATA_PUL_02", DATA_PUL_02, DATA_PUL_01, false, "onchange=\"javascript:doChangeSOC('DATA_PUL_02');\"");
        SOC_MUKIM_PUL = logic.getSOCMukim("DATA_PUL_03", DATA_PUL_03, DATA_PUL_02, DATA_PUL_01);
        
        context.put("SOC_NEGERI_PUL", SOC_NEGERI_PUL);
        context.put("SOC_DAERAH_PUL", SOC_DAERAH_PUL);
        context.put("SOC_MUKIM_PUL", SOC_MUKIM_PUL);
        
        context.put("ID_JUPEMPUL", ID_JUPEMPUL);
       	//----------------------------
        
        if ("emailJUPEM".equalsIgnoreCase(mode)) {
            Vector vEmail = new Vector();
            Hashtable hEmail = new Hashtable();
            String FILENAME_PUB = "", FILENAME_PUD = "", FILENAME_PUL = "", rVal = "";
            hEmail.put("ID_PERMOHONAN", ID_PERMOHONAN);
            hEmail.put("ID_HAKMILIK", ID_HAKMILIK);
            hEmail.put("GEN_TYPE", "PUB");
            vEmail.add(hEmail);
            FILENAME_PUB = logic.generateFileName(vEmail);
            rVal = logic.generateText(vEmail);
            logic.WriteFile(FILENAME_PUB + ".pub", userID, rVal);

            vEmail.clear();
            hEmail.clear();
            hEmail.put("ID_PERMOHONAN", ID_PERMOHONAN);
            hEmail.put("ID_HAKMILIK", ID_HAKMILIK);
            hEmail.put("GEN_TYPE", "PUD");
            vEmail.add(hEmail);
            FILENAME_PUD = logic.generateFileName(vEmail);
            rVal = logic.generateText(vEmail);
            logic.WriteFile(FILENAME_PUD + ".pud", userID, rVal);

            vEmail.clear();
            hEmail.clear();
            hEmail.put("ID_PERMOHONAN", ID_PERMOHONAN);
            hEmail.put("ID_HAKMILIK", ID_HAKMILIK);
            hEmail.put("GEN_TYPE", "PUL");
            vEmail.add(hEmail);
            FILENAME_PUL = logic.generateFileName(vEmail);
            rVal = logic.generateText(vEmail);
            logic.WriteFile(FILENAME_PUL + ".pul", userID, rVal);
            
            String EMAIL_ADDR = getParam("EMAIL_ADDR");
            String FolderName = FileUploadProperty.getInstance().getIntegrasiJUPEMFolder();
    		if (!FolderName.endsWith("/")) {
    			FolderName += "/";
    		}
    		FolderName += userID;
    		if (!FolderName.endsWith("/")) {
    			FolderName += "/";
    		}
    		
    		String ALAMAT_DARI = "", ADA_CC = "", ALAMAT_CC_1 = "", ALAMAT_CC_2 = "", TAJUK = "", KANDUNGAN = "";
    		
    		Vector vEmailContent = FrmModelUtilitiesIntegration.getEmailContent("JUPEM");
    		if (!vEmailContent.isEmpty()) {
        		Hashtable hEmailContent = new Hashtable();
        		hEmailContent = (Hashtable) vEmailContent.get(0);
        		ALAMAT_DARI = (String) hEmailContent.get("ALAMAT_DARI");
        		ADA_CC = (String) hEmailContent.get("ADA_CC");
        		ALAMAT_CC_1 = (String) hEmailContent.get("ALAMAT_CC_1");
        		ALAMAT_CC_2 = (String) hEmailContent.get("ALAMAT_CC_2");
        		TAJUK = (String) hEmailContent.get("TAJUK");
        		KANDUNGAN = (String) hEmailContent.get("KANDUNGAN");
    		}
    		    		
            XEkptgEmailSender email = XEkptgEmailSender.getInstance();
    		email.FROM = ALAMAT_DARI;
    		email.SUBJECT = TAJUK;
    		email.MESSAGE = KANDUNGAN;
    		email.MULTIPLE_RECIEPIENT = new String[1];
    		email.MULTIPLE_RECIEPIENT[0] = EMAIL_ADDR;
    		if ("1".equalsIgnoreCase(ADA_CC)) {
    			if (!"".equalsIgnoreCase(ALAMAT_CC_1) && !"".equalsIgnoreCase(ALAMAT_CC_2)) {
    				email.TO_CC = new String[2];
    				email.TO_CC[0] = ALAMAT_CC_1;
    				email.TO_CC[1] = ALAMAT_CC_2;
    			} else if (!"".equalsIgnoreCase(ALAMAT_CC_1)) {
    				email.TO_CC = new String[1];
    				email.TO_CC[0] = ALAMAT_CC_1;
    			} else if (!"".equalsIgnoreCase(ALAMAT_CC_2)) {
    				email.TO_CC = new String[1];
    				email.TO_CC[0] = ALAMAT_CC_2;
    			}
    		}
    		email.ATTACHMENT = new String[3];
    		email.ATTACHMENT[0] = FolderName + FILENAME_PUB + ".pub";
    		email.ATTACHMENT[1] = FolderName + FILENAME_PUD + ".pud";
    		email.ATTACHMENT[2] = FolderName + FILENAME_PUL + ".pul";
    		email.sendEmail();
    		
    		context.put("emailSent", "true");
    		
    		logic.DeleteFiles(userID);
        }
        
        context.put("ID_PERMOHONAN", ID_PERMOHONAN);
        context.put("ID_HAKMILIK", ID_HAKMILIK);
        context.put("noData", noData);
        
//        String IDFail = getParam("IDFail");
//        Boolean NoFileSpecified = false, FileProcessSuccess = false, FileProcessFail = false, FileDeleteSuccess = false, FileDeleteFail = false;
//
//    	Vector vList = new Vector();
//    	
//        if ("uploadFile".equalsIgnoreCase(action2)) {
//        	DiskFileItemFactory factory = new DiskFileItemFactory();
//		    ServletFileUpload upload = new ServletFileUpload(factory);
//
//		    List items = upload.parseRequest(request);
//		    Iterator itr = items.iterator();
//		    while (itr.hasNext()) {
//		    	FileItem objItem = (FileItem)itr.next();
//		    	if ((!(objItem.isFormField())) && (objItem.getName() != null) && (!("".equals(objItem.getName())))) {
//		    		doUpload = logic.doUpload(objItem, userID);
//		    	}
//		    }
//		} else if ("deleteFile".equalsIgnoreCase(action2)) {
//			if ("".equalsIgnoreCase(IDFail)) {
//				NoFileSpecified = true;
//			} else {
//				if (logic.deleteFile(IDFail)) {
//					FileDeleteSuccess = true;
//				} else {
//					FileDeleteFail = true;
//				}
//			}
//		}
//        
//        // get list of files
//        vList = logic.getListUploadedFile();
//        context.put("ListUploaded", vList);
//        setupPage(session, action, vList);
//        
//        context.put("IDFail", IDFail);
//        context.put("UploadStatus", doUpload);
//        context.put("NoFileSpecified", NoFileSpecified);
//        context.put("FileProcessSuccess", FileProcessSuccess);
//        context.put("FileProcessFail", FileProcessFail);
//        context.put("FileDeleteSuccess", FileDeleteSuccess);
//        context.put("FileDeleteFail", FileDeleteFail);
//        
//    	vm = "app/integrasi/frmEKadaster.jsp";
//
//        context.put("action2", action2);
//        context.put("action", action);
		return vm;
	}
	
	@SuppressWarnings("unchecked")
	public void setupPage(HttpSession session, String action, Vector list) {
		try {
			this.context.put("totalRecords",list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10 : getParamAsInteger("itemsPerPage");
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
			this.context.put("ListUploaded",paging.getPage(page));
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