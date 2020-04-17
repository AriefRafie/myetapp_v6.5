package ekptg.view.ppk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.ppk.FrmAksesMaklumatPPKData;
import ekptg.model.ppk.FrmHeaderPpk;

public class FrmAksesMaklumatPPK extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmAksesMaklumatPPK.class);
	FrmAksesMaklumatPPKData logic = new FrmAksesMaklumatPPKData();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	String user_negeri_login = "";
	String userId = "";
	String role = "";
	HttpSession session = null;
	String action = "";
	List list = null;

	public String doTemplate2() throws Exception {

		logic = new FrmAksesMaklumatPPKData();
		//myLogger.info(".:FrmSenaraiPermohonanSeksyen8:.");
		
		HttpSession session = this.request.getSession();
		String userId = (String)session.getAttribute("_ekptg_user_id");
		
		String vm = "app/ppk/perintah/frmCarian.jsp";
		String flagAdvSearch = getParam("flagAdvSearch");
		String action = getParam("action"); // Second Level
		
		//list = new Vector();
		//list.clear();
		//String command = getParam("command");
		context.put("ScrollX", getParam("ScrollX"));
		context.put("ScrollY", getParam("ScrollY"));
		context.put("view_skrin", "maklumatppk");
		context.put("open_ppkcarian", "no");
		
		this.context.put("myIdSimati", "");
		this.context.put("txtNoFail", "");
		this.context.put("SenaraiFail", "");
		
		//myLogger.info("command CARIAN MAKLUMAT PPK ::" + command);
		
		//if (command.equals("carian")) {
			
			//System.out.println("################# carian");	
			list = Collections.synchronizedList(new ArrayList());
			
		
			String noFail = getParam("txtNoFail");
			String tarikhMohon = getParam("tarikhMohon");
			String namaPemohon = getParam("txtPemohon");
			String kpPemohon = getParam("txtIcPemohon");
			String jenisKpPemohon = getParam("socJenisKpPemohon");
			if (jenisKpPemohon == null || jenisKpPemohon.trim().length() == 0){
				jenisKpPemohon = "0";
			}
			String namaSimati = getParam("txtSimati");
			String kpSimati = getParam("txtIcSimati");
			String jenisKp = getParam("socJenisKp");
			if (jenisKp == null || jenisKp.trim().length() == 0){
				jenisKp = "0";
			}
			String status = getParam("socStatus");
			if (status == null || status.trim().length() == 0){
				status = "0";
			}
			String noHakmilik = getParam("txtNoHakmilik");
			String noSijilMati = getParam("txtNoSijil");
			String tarikhBicara = getParam("tarikhBicara");
			String noLot = getParam("txtNoLot");
			
			String open_dashboard = "no";
			String CAPAIAN_FAIL_UNIT_LUAR = "";
			
				
			if (flagAdvSearch.equals("")){
				namaPemohon = "";
				namaSimati = "";
				noHakmilik = "";
				noSijilMati = "";
				tarikhBicara = "";
				noLot = "";

			
			this.context.put("JumlahFail",logic.totalFail(session));
			

			list = logic.carianFail(noFail, namaPemohon, namaSimati, jenisKp, kpSimati, session, tarikhMohon, status, 
					jenisKpPemohon, kpPemohon, noHakmilik, noSijilMati,tarikhBicara,noLot);
			
			
			this.context.put("SenaraiFail", list);
			
			this.context.put("selectJenisKpPemohon",HTML.SelectPPKJenisKp("socJenisKpPemohon", Long.parseLong(jenisKpPemohon), "", "style=\"width:100px\""));
			this.context.put("selectJenisKp",HTML.SelectPPKJenisKp("socJenisKp", Long.parseLong(jenisKp), "", "style=\"width:100px\""));
			this.context.put("selectStatus",HTML.SelectStatusSek8("socStatus", Long.parseLong(status), "", "style=\"width:320px\""));
			
			this.context.put("txtNoFail", noFail.trim());
			this.context.put("tarikhMohon", tarikhMohon);
			this.context.put("txtPemohon", namaPemohon.trim());
			this.context.put("txtIcPemohon", kpPemohon);
			this.context.put("txtSimati", namaSimati.trim());
			this.context.put("txtIcSimati", kpSimati);
			this.context.put("txtNoHakmilik", noHakmilik.trim());
			this.context.put("txtNoSijil", noSijilMati.trim());
			this.context.put("tarikhBicara", tarikhBicara);
			this.context.put("txtNoLot", noLot.trim());
			
			this.context.put("flagAdvSearch", flagAdvSearch);
			this.context.put("CAPAIAN_FAIL_UNIT_LUAR", CAPAIAN_FAIL_UNIT_LUAR);
			
			this.context.put("totalFailPindahMasuk",logic.totalFailPindahMasuk(userId));
			context.put("view_skrin", "maklumatppk");
			context.put("open_ppkcarian", "yes");
			
			
			
			setupPage(session,action,list);
			
			}
			
			this.context.put("open_dashboard", open_dashboard);
			
			
			
			vm = "app/ppk/perintah/frmCarian.jsp";
			
	/*	}else{
			System.out.println("################# default");
			//this.context.put("SenaraiFail", list);
			list = logic.carianFail("", "", "", "", "", session, "", "", 
					"", "", "", "","","");
			setupPage(session,action,list);
			this.context.put("selectJenisKpPemohon",HTML.SelectPPKJenisKp("socJenisKpPemohon", Long.parseLong("0"), "", "style=\"width:100px\""));
			this.context.put("selectJenisKp",HTML.SelectPPKJenisKp("socJenisKp", Long.parseLong("0"), "", "style=\"width:100px\""));
			this.context.put("selectStatus",HTML.SelectStatusSek8("socStatus", Long.parseLong("0"), "", "style=\"width:320px\""));
			
			this.context.put("txtNoFail","");
			this.context.put("tarikhMohon", "");
			this.context.put("txtPemohon", "");
			this.context.put("txtIcPemohon", "");
			this.context.put("txtSimati", "");
			this.context.put("txtIcSimati", "");
			this.context.put("txtNoHakmilik", "");
			this.context.put("txtNoSijil", "");
			this.context.put("tarikhBicara", "");
			this.context.put("txtNoLot", "");
			
			this.context.put("flagAdvSearch", "");
			//this.context.put("CAPAIAN_FAIL_UNIT_LUAR", "");
			this.context.put("JumlahFail","");
			
			this.context.put("totalFailPindahMasuk",logic.totalFailPindahMasuk(userId));
			context.put("view_skrin", "maklumatppk");
			context.put("open_ppkcarian", "yes");
			vm = "app/ppk/perintah/frmCarian.jsp";
			
		}
		*/
		
		
		return vm;
	
	}
	
	
	
		
	

}
