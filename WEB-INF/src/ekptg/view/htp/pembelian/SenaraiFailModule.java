package ekptg.view.htp.pembelian;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.action.AjaxModule;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.entities.Tblrujsuburusanstatus;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailTerimaPohonData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHTPStatus;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.PihakBerkepentingan;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.entity.Bangunan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pemohon;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.IPemilik;
import ekptg.model.htp.pembelian.PembelianBean;
import ekptg.model.htp.pembelian.PemilikBean;
import ekptg.model.htp.rekod.FrmRekodUtilData;
import ekptg.model.htp.utiliti.HTPEmelKelulusanBean;
import ekptg.model.htp.utiliti.HTPEmelSemakanBean;
import ekptg.model.utils.IUtilHTMLPilihan;
import ekptg.model.utils.emel.EmailConfig;
import ekptg.model.utils.emel.IEmel;
import ekptg.model.utils.rujukan.UtilHTMLPilihanSeksyenUPI;
import ekptg.model.htp.HTPStatusBean;

public class SenaraiFailModule extends AjaxModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3966088089658329931L;
	private static final String PATH="app/htp/pembelian/fail/";
	static Logger myLog = Logger.getLogger(ekptg.view.htp.pembelian.SenaraiFailModule.class);
	private IEmel emelSemak = null;
	private IEmel emelulus = null;
	private String vm = PATH+"index.jsp";
	private String userID = null;
 	private IHtp iHTP = null;  
 	private IUtilHTMLPilihan iPilihan = null;
 	private IPembelian iPembelian = null;
	private IPemilik iPemilik = null;
	private IHTPStatus iStatus = null;
	
	private String readonly = " disabled class = \"disabled\" ";
	private String idNegeri ="";
	private String subModul ="Perakuan Pembelian";
	private final String IDJENISTANAH = "4"; //TANAH MILIK - TM
	private final String URUSAN_TANAH = "01";
	private final String URUSAN_BANGUNAN = "03";
	private final String ID_URUSAN = "2";
	private final String ID_URUSANTANAH = "24";
	private final String ID_URUSANTANAH1 = "25";
	
	private PfdFail fail = null;
	private Permohonan permohonan = null;
	private HtpPermohonan htpPermohonan = null;
	private HakmilikUrusan urusan = null;
	private PihakBerkepentingan pemilik = null;
	private Pemohon pemohon;
	private Bangunan bangunan = null;
	private FrmRekodUtilData frmRekodUtilData = null;
	private FrmSenaraiFailTerimaPohonData senaraiFail = null;

	@Override
	public String doAction() throws Exception {
		HttpSession ses = request.getSession();
		userID = (String)ses.getAttribute("_ekptg_user_id");
		String command = getParam("command");
		String action = getParam("action");
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
		String idHakmilikUrusan =  getParam("idHakmilikUrusan");
		
		boolean isTanah = false;

		context.put("URUSAN_BANGUNAN",URUSAN_BANGUNAN);
		context.put("URUSAN_TANAH",ID_URUSANTANAH);
		context.put("URUSAN_TANAH1",ID_URUSANTANAH1);
		
		frmRekodUtilData = FrmRekodUtilData.getInstance();
		context.remove("senaraihakmilikrizab");
		
		myLog.info("command : "+command);
		String portal_role = String.valueOf(ses.getAttribute("myrole")).substring(5,String.valueOf(ses.getAttribute("myrole")).length());
		context.put("portalRole", portal_role);
		myLog.info("portal_role = "+portal_role);
		String langkah = "0";

		if(command.equals("tambahFail")){
			getSenaraiSemakFail();
			String mode="";
			context.put("mode", mode);
			context.put("pageMode", "edit");
			context.put("socNegeri",HTML.SelectNegeri("socNegeri"));
			context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan(ID_URUSAN,null,"socSuburusan", null,""));
//			context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("2","23,25", "socSuburusan", null,""));
			context.put("statusTanah", UtilHTML.SelectJenisTanah("socStatusTanah", Long.parseLong(IDJENISTANAH), "",IDJENISTANAH));
			context.put("socTarafKeselamatan", HTML.SelectTarafKeselamatan("socTarafKeselamatan", null, "") );
			context.remove("htpPermohonan");
			getKementerianDetail("", "edit");
			context.put("inputstyleread", null );
			vm= PATH+"file.jsp";
			myLog.info("tambahFail ::"+vm);
			
			context.put("page","1");
		}
		
		
		else if(command.equals("doChangeKementerian")){
			getSenaraiSemakFail();
			getValues();
			String idNegeri = getParam("socNegeri");
			String idKementerian = getParam("socKementerian");
			String socKementerian = HTML.SelectKementerian("socKementerian", (idKementerian == "") ? null : Long.parseLong(idKementerian),"", " onChange=\"doChangeKementerian()\" ");
			String socAgensi = HTML.SelectAgensiByKementerian("socAgensi",idKementerian,Long.parseLong("1"),"");
			String socNegeri = HTML.SelectNegeri("socNegeri",(idNegeri == "") ? null : Long.parseLong(idNegeri),"");
		    context.put("selectKementerian",socKementerian);
			context.put("selectAgensi",socAgensi);
			context.put("socNegeri",socNegeri);
			context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("2",null,"socSuburusan", null,""));
			//context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("2","23,25", "socSuburusan", null,""));
			//context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", htpPermohonan.getIdJenisTanah(), ""));
			context.put("statusTanah", UtilHTML.SelectJenisTanah("socStatusTanah", htpPermohonan.getIdJenisTanah(), "",IDJENISTANAH));
			context.put("socTarafKeselamatan", HTML.SelectTarafKeselamatan("socTarafKeselamatan", fail.getIdTarafKeselamatan(), "") );
			//context.put("idPermohonan", permohonan.getIdPermohonan());
			context.put("tajuk", permohonan.getTujuan());
			context.put("noFailKJP", htpPermohonan.getNoRujukanKJP());
			context.put("noFailLain", htpPermohonan.getNoRujukanLain());
			String mode="";
			context.put("inputstyleread", null );
			context.put("mode", mode);
			context.put("pageMode", "edit");
			vm= PATH+"file.jsp";
			myLog.info("doChangeKementerian ::"+vm);
			
		}else if(command.equalsIgnoreCase("simpan")){
			getValues();
			viewDetail2();
			getSenaraiSemakFail();
			htpPermohonan = getIPembelian().simpanPermohonan(htpPermohonan);
			AuditTrail.logActivity("1", "3", this, ses, "INS", "FAIL PERMOHONAN ["+htpPermohonan.getPermohonan().getPfdFail().getNoFail()+"] DITAMBAH ");
			
			simpanSenaraiSemakFail(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			context.put("htpPermohonan", htpPermohonan); 
			context.put("pageMode", "update");
			context.put("inputstyleread", readonly );
			vm= PATH+"file.jsp";
			myLog.info("Simpan ::"+vm);
			
		}else if(command.equalsIgnoreCase("update")){
			getValues();
			htpPermohonan = getIPembelian().findPermohonan(String.valueOf(permohonan.getIdPermohonan()),String.valueOf(htpPermohonan.getIdHtpPermohonan()));
			
			viewDetail2();
			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", "kemas");
			context.put("inputstyleread", "");
			context.put("inputstylereadnofail",readonly);
			getSenaraiSemakFail();
			vm= PATH+"file.jsp";
			myLog.info("Update ::"+vm);
		}else if(command.equalsIgnoreCase("detail")){
			getValues();
			Vector senaraiHakmilikRizab = frmRekodUtilData.getHakmilikRizabMengikutPermohonan(idPermohonan);
			//myLog.info("senaraiHakmilikRizab:"+senaraiHakmilikRizab.size());
			//myLog.info("senaraiHakmilikRizab:"+senaraiHakmilikRizab.isEmpty());
			context.put("senaraihakmilikrizab",senaraiHakmilikRizab);
		
			htpPermohonan = getIPembelian().findPermohonan(idPermohonan,idHtpPermohonan);
			viewDetail2();
			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", "update");
			context.put("inputstyleread", readonly);
			context.put("page","1");
			getSenaraiSemakFail();
			
			vm= PATH+"file.jsp";
			//myLog.info("detail ::"+vm);
		}else if(command.equalsIgnoreCase("simpanupdate")){
			getValues();
			viewDetail2();
			htpPermohonan = getIPembelian().kemaskiniPermohonan(htpPermohonan,String.valueOf(permohonan.getIdPermohonan()),String.valueOf(htpPermohonan.getIdHtpPermohonan()));
			simpanSenaraiSemakFail(getParam("txtidPermohonan"));
			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", "update");
			context.put("inputstyleread", readonly);
			getSenaraiSemakFail();
			vm= PATH+"file.jsp";
			myLog.info("simpanUpdate ::"+vm);
		}
		else if(command.equals("searchFail")){
			String noFail = getParam("noFail");
			String carian = getParam("namaFail");
			idNegeri = getParam("socNegeri") == "" ? "0" : getParam("socNegeri");
			
			Vector<?> v = getIPembelian().findFail(carian, noFail, idNegeri);
			context.put("lists", v);
			context.put("socNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri)));
			setupPage(ses,action, v);
			context.put("carianNoFail", noFail);
			context.put("carian", carian);
			vm = PATH+"index.jsp";
			myLog.info("searchFail ::"+vm);
		}
		else if(command.equals("maklumatTanah")){
			getValues();
			getPermohonanInfo();
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			context.put("mt", m);
			context.put("selectedTab", 0);
			vm = PATH+"tanah.jsp";
			myLog.info("MaklumatTanah ::"+vm);
			context.put("page","2");
		}
		else if(command.equals("tambahTanah")){
			tambahTanahDetail();
			context.put("button", "simpan");
			context.remove("urusan");
			vm = PATH+"tanahInfo.jsp";
			myLog.info("tambahTanah ::"+vm);
			
		}
		else if(command.equalsIgnoreCase("doChangeDaerah")){			
			getValuesMaklumatTanah();
			tambahTanahDetail();
			context.put("button", "simpan");
			vm = PATH+"tanahInfo.jsp";
			myLog.info("doChangeDaerah ::"+vm);
		}
		else if(command.equalsIgnoreCase("simpanMaklumatTanah")){
			getValuesMaklumatTanah();
			tambahTanahDetail();
			urusan = iPembelian.simpanHakmilik(urusan);
			getValues();
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(String.valueOf(urusan.getPermohonan().getIdPermohonan()));
			context.put("urusan", urusan);
			context.put("mt", m);
			context.put("selectedTab", 0);
			vm = PATH+"tanah.jsp";
			myLog.info("simpanMaklumatTanah ::"+vm);
		}
		else if(command.equalsIgnoreCase("detailTanah")){		

			getValuesMaklumatTanah();			
			urusan = getIPembelian().findByHakmilikUrusanId(idHakmilikUrusan);
			tambahTanahDetail2();
			context.put("urusan", urusan);
			context.put("button", "kemaskini");
			vm = PATH+"tanahInfo.jsp";
			myLog.info("detailTanah ::"+vm);
		}
		else if(command.equalsIgnoreCase("kemaskiniMaklumatTanah")){
			getValuesMaklumatTanah();
			tambahTanahDetail2();
			urusan = getIPembelian().updateHakmilik(urusan);		
			context.put("urusan", urusan);

			vm = PATH+"tanahInfo.jsp";
			myLog.info("kemaskiniMaklumatTanah ::"+vm);
			
		}else if(command.equals("deleteMaklumatTanah")){
			getIPembelian().deleteHakmilik(idHakmilikUrusan);
			getValues();
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(idPermohonan);
			context.put("mt", m);
			context.put("selectedTab", 0);
			vm = PATH+"tanah.jsp";
			myLog.info("deleteMaklumatTanah ::"+vm);
		}
		else if(command.equals("maklumatBangunan")){
			getPermohonanInfo();
			Vector buildings = getIPembelian().findBangunanByPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			context.put("buildings", buildings);
			context.put("selectedTab", 1);
			vm = PATH+"tanah.jsp";
		}
		else if(command.equals("tambahBangunan")){
			getPermohonanInfo();
			Vector<HakmilikUrusan> hmu = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			context.put("hmu", hmu);
			context.put("selectedTab", 1);
			context.remove("bangunan");
			vm = PATH+"bangunanInfo.jsp";
			
		}	
		else if(command.equals("simpanBangunan")){
			getPermohonanInfo();
			getBangunanValues();
			bangunan = getIPembelian().simpanBangunan(bangunan);
			Vector buildings = getIPembelian().findBangunanByPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			
			context.put("buildings", buildings);
			context.put("bangunan", bangunan);
			context.put("selectedTab", 1);
			vm = PATH+"tanah.jsp";
		}
		else if(command.equals("updateBangunan")){
			getPermohonanInfo();
			getBangunanValues();
			bangunan = getIPembelian().updateBangunan(bangunan);
			Vector buildings = getIPembelian().findBangunanByPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			
			context.put("buildings", buildings);
			context.put("bangunan", bangunan);
			context.put("selectedTab", 1);
			vm = PATH+"tanah.jsp";
		}
		else if(command.equals("paparBangunan")){
			getPermohonanInfo();
			Vector<HakmilikUrusan> hmu = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			context.put("hmu", hmu);
			bangunan = getIPembelian().findBangunan(getParam("idBangunan"));
			context.put("bangunan",bangunan);
			context.put("bangunanMode","update");
			vm = PATH+"bangunanInfo.jsp";
		}
		else if(command.equals("deleteBangunan")){
			getPermohonanInfo();
			getIPembelian().deleteBangunan(getParam("idBangunan"));
			Vector buildings = getIPembelian().findBangunanByPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			context.put("buildings", buildings);
			context.put("bangunan", bangunan);
			context.put("selectedTab", 1);
			vm = PATH+"tanah.jsp";
		}
		else if(command.equals("viewPemilik")){
			System.out.println("===viewPemilik===" +idPermohonan);
			getPermohonanInfo();
			getValues();
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			getPermohonanInfo();
			context.put("pp", p);
			context.put("mode", 0);
			context.put("mt", m);
			context.put("selectedTab", 2);
			myLog.info(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan());
			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
				context.put("selectedTab", 1);
			}
			
			vm = PATH+"tanah.jsp";
		}else if(command.equals("tambahPemilik")){
			myLog.info("===tambahPemilik===" +idPermohonan);
			getPermohonanInfo();
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			getValuesPemilik();
			//getValuesMaklumatTanah();
			//tambahTanahDetail3();
			context.remove("pemilik");
			context.put("selectJenisNoPB", HTML.SelectJenisNoPb("selectJenisNoPB"));
			context.put("socNegeri",HTML.SelectNegeri("socNegeri"," onChange=\"doChangeDaerah2()\" "));
			context.put("socDaerah", HTML.SelectDaerah("socDaerah"));
						
			//context.put("socDaerah", "");
			context.put("buttonMode", 1);
			context.put("select", 0);
			context.put("mode", 1);
			context.put("mt", m);
			context.put("pp", p);
			context.put("bil", 100);
			context.put("selectedTab", 2);
			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
				context.put("selectedTab", 1);
			}
			vm = PATH+"tanah.jsp";
		}else if(command.equals("doChangeDaerah2")){
			System.out.println("===doChangeDaerah2===" +idPermohonan);
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			getValuesPemilik();
			//getValuesMaklumatTanah();
			tambahTanahDetail3();
			context.put("buttonMode", 1);
			context.put("mode", 1);
			context.put("select", pemilik.getDdownHakmilik());
			context.put("mt", m);
			context.put("pp", p);
			context.put("selectedTab", 2);
			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
				context.put("selectedTab", 1);
			}
			vm = PATH+"tanah.jsp";
		}else if(command.equalsIgnoreCase("simpanPemilik")){
			System.out.println("==simpanPemilik==");
			getValuesPemilik();
			tambahTanahDetail3();
			pemilik = getIPemilik().savePemilik(pemilik);
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			context.put("buttonMode", 1);
			context.put("mode", 0);
			context.put("pemilik", pemilik);
			context.put("mt", m);
			context.put("pp", p);
			context.put("selectedTab", 2);
			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
				context.put("selectedTab", 1);
			}
			vm = PATH+"tanah.jsp";
		}else if(command.equalsIgnoreCase("detailPemilik")){
			System.out.println("==detailPemilik==");
			getValuesPemilik();
			tambahTanahDetail3();
			pemilik = getIPemilik().findPemilik(pemilik.getIdpihakberkepentingan());
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			context.put("socNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(pemilik.getIdNegeri())));
			context.put("socDaerah", HTML.SelectDaerahByNegeri(pemilik.getIdNegeri(), "socDaerah", Long.parseLong(pemilik.getIdDaerah()), ""));
			context.put("selectJenisNoPB", HTML.SelectJenisNoPb("selectJenisNoPB", Long.parseLong(pemilik.getJenisPB()), ""));
			context.put("buttonMode", 2);
			context.put("mode", 1);
			context.put("pemilik", pemilik);
			context.put("mt", m);
			context.put("pp", p);
			context.put("selectedTab", 2);
			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
				context.put("selectedTab", 1);
			}
			vm = PATH+"tanah.jsp";
		}
		else if(command.equalsIgnoreCase("updatePemilik")){
			System.out.println("==updatePemilik==");
			getValuesPemilik();
			tambahTanahDetail3();
			pemilik = getIPemilik().updatePemilik(pemilik);
			//pemilik = getIPemilik().updatePemilik(pemilik);
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			context.put("socNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(pemilik.getIdNegeri())));
			context.put("socDaerah", HTML.SelectDaerahByNegeri(pemilik.getIdNegeri(), "socDaerah", Long.parseLong(pemilik.getIdDaerah()), ""));
			context.put("selectJenisNoPB", HTML.SelectJenisNoPb("selectJenisNoPB", Long.parseLong(pemilik.getJenisPB()), ""));
			context.put("buttonMode", 2);
			context.put("mode", 0);
			context.put("pemilik", pemilik);
			context.put("mt", m);
			context.put("pp", p);
			context.put("selectedTab", 2);
			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
				context.put("selectedTab", 1);
			}
			vm = PATH+"tanah.jsp";
		}else if(command.equalsIgnoreCase("deletePemilik")){
			System.out.println("==deletePemilik==");
			getValuesPemilik();
			tambahTanahDetail3();
			getIPemilik().deletePemilik(pemilik.getIdpihakberkepentingan());
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			context.put("mode", 0);
			context.put("pemilik", pemilik);
			context.put("mt", m);
			context.put("pp", p);
			context.put("selectedTab", 2);
			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
				context.put("selectedTab", 1);
			}
			vm = PATH+"tanah.jsp";
		
		}else if(command.equalsIgnoreCase("simpanpengesahan")){
				myLog.info("==simpanpengesahan==");
				getPermohonanInfo();
				getPenjualDetails();
				String penjualMode = "edit";
				String mode =" readonly";
				String enable =" disabled";
				pemohon = getIPembelian().findByPermohonan(String.valueOf(permohonan.getIdPermohonan()));
				if(pemohon == null){
					penjualMode = "new";
					mode ="";
					enable ="";
					context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP"," onChange=\"doChangePenjualNegeri()\" "));
					context.put("selectDaerahP", HTML.SelectDaerah("selectDaerahP"));
					context.put("pemohon", null);
				}else{
					context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP",Long.parseLong(pemohon.getIdNegeri()) ,"disabled", " onChange=\"doChangePenjualNegeri()\" "));
					context.put("selectDaerahP", HTML.SelectDaerahByNegeri(pemohon.getIdNegeri(), "selectDaerahP", Long.parseLong(pemohon.getIdDaerah()), "disabled"));
				}
				Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
				context.put("pemiliks", p);
				context.put("enable", enable);
				context.put("mode", mode);
				context.put("penjualMode", penjualMode);
				context.put("selectedTab", 3);
				if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
						ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
					context.put("selectedTab", 2);
				}
				context.put("pemohon", pemohon);
				
				isTanah = true;
				EmailConfig ec = new EmailConfig();
				String userMail = "roslizakariasip@gmail.com";
				String tajuk = ec.tajukSemakanInt+subModul;
				//String emelSubjek = ec.tajukSemakan+"Perakuan Pembelian";
				String kandungan = getEmelSemak().setKandungan(htpPermohonan.getPermohonan().getPfdFail().getTajukFail()
						,htpPermohonan.getPermohonan().getPfdFail().getNamaKementerian());

				idPermohonan = String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan());
				String idFail = String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdFail());
				String IDSUBURUSAN = String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan());
				
				if(portal_role.equals("HQPengguna")){
		  			langkah = "1";
	    			kemaskiniSimpanStatusSelesai(idFail,idPermohonan,IDSUBURUSAN,"4");
	    			ec.sendByRole(userMail, portal_role, null, tajuk, kandungan);
	    					
	    		}else if(portal_role.equals("HQPegawai")){
		  			langkah = "4";
	    			kemaskiniSimpanStatusSelesai(idFail,idPermohonan,IDSUBURUSAN,"5");
	    			ec.sendByRole(userMail, portal_role, null, tajuk, kandungan);
	    			
	    		}else if(portal_role.equals("HQPengarah")){
		  			langkah = "5";
	    			kemaskiniSimpanStatusSelesai(idFail,idPermohonan,IDSUBURUSAN,"6");
	    			tajuk = ec.tajukKelulusan+subModul;
//	    			email.MESSAGE = "<html><table><tr><td>Nama Projek</td><td>:</td><td>"+nama_projek+"</td></tr>" +
//	    					"<tr><td>Tarikh Permohonan</td><td>:</td><td>"+tarikh_permohonan+"</td></tr>" +
//	    					"<tr><td>Nama Kementerian</td><td>:</td><td>"+nama_kementerian+"</td></tr>" +
//	    					"<tr><td>Telah berjaya disahkan</td></tr>" +
//	    					"<tr><td>&nbsp;</td><td>&nbsp;</td><td><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em></td></tr>" +
//	    					"</table></html>" ;
	    			ec.sendByRole(userMail, portal_role, null, tajuk, kandungan);
	    			
	    			kandungan = getEmeLulus().setEmailSign(htpPermohonan.getPermohonan().getTarikhTerima()
	    					, htpPermohonan.getPermohonan().getPfdFail().getTajukFail()
	    					, htpPermohonan.getPermohonan().getPfdFail().getNamaKementerian());
	    			//"4" Pengarah (KJP)
	    			ec.sendByRoleKJP(getEmelSemak().checkEmail(userID)
							, "4"
							, String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdKementerian())
							, tajuk, kandungan);
		
	    		}
				myLog.info("langkah="+langkah);
				
				String statuSemasa = "0";
				Tblrujsuburusanstatus hashStatus = getStatus().getStatusPermohonanAktif(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdFail())
													, String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));

				if(hashStatus != null)
					statuSemasa = String.valueOf(hashStatus.getLangkah());
				
//				myLog.info("langkah1 ="+htpPermohonan.getPermohonan().getPfdFail().getIdFail());
//				myLog.info("langkah2="+String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
				//
				myLog.info("statuSemasa="+statuSemasa+"|portal_role="+portal_role);
				//this.context.put("idFail", idFail);
				this.context.put("statuSemasa", statuSemasa);

						
				vm = PATH+"tanah.jsp";
		
		}else if(command.equalsIgnoreCase("viewPenjual")){
			myLog.info("==viewPenjual==");
			getPermohonanInfo();
			getPenjualDetails();
			String penjualMode = "edit";
			String mode =" readonly";
			String enable =" disabled";
			pemohon = getIPembelian().findByPermohonan(String.valueOf(permohonan.getIdPermohonan()));
			if(pemohon == null){
				penjualMode = "new";
				mode ="";
				enable ="";
				context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP"," onChange=\"doChangePenjualNegeri()\" "));
				context.put("selectDaerahP", HTML.SelectDaerah("selectDaerahP"));
				context.put("pemohon", null);
			}else{
				context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP",Long.parseLong(pemohon.getIdNegeri()) ,"disabled", " onChange=\"doChangePenjualNegeri()\" "));
				context.put("selectDaerahP", HTML.SelectDaerahByNegeri(pemohon.getIdNegeri(), "selectDaerahP", Long.parseLong(pemohon.getIdDaerah()), "disabled"));
			}
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			context.put("pemiliks", p);
			context.put("enable", enable);
			context.put("mode", mode);
			context.put("penjualMode", penjualMode);
			context.put("selectedTab", 3);
			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
				context.put("selectedTab", 2);
			}
			context.put("pemohon", pemohon);
			
			isTanah = true;
			if(portal_role.equals("HQPengguna")){
	  			langkah = "1";
    		}else if(portal_role.equals("HQPegawai")){
	  			langkah = "4";
    		}else if(portal_role.equals("HQPengarah")){
	  			langkah = "5";	    		
    		}
//			myLog.info("langkah="+langkah);
//			myLog.info("langkah1 ="+htpPermohonan.getPermohonan().getPfdFail().getIdFail());
//			myLog.info("langkah2="+String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			String statuSemasa = "0";
			Tblrujsuburusanstatus hashStatus = getStatus().getStatusPermohonanAktif(
					String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdFail()), String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));

			//			Hashtable<String,String> hashStatus = getStatus().getInfoStatusPermohonanFail(
//					String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdFail()), String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()),langkah);
			if(hashStatus != null)
				statuSemasa = String.valueOf(hashStatus.getLangkah());
				
			//myLog.info(isTanah+"|statuSemasa="+langkah+"|portal_role="+portal_role);
			myLog.info(isTanah+"|statuSemasa="+statuSemasa);
			//this.context.put("idFail", idFail);
			this.context.put("statuSemasa", statuSemasa);
					
			vm = PATH+"tanah.jsp";
			
		}else if(command.equalsIgnoreCase("doChangePenjualNegeri")){
			System.out.println("==doChangePenjualNegeri==");
			getPermohonanInfo();
			getPenjualDetails();
			getdoChange();
			String mode ="";
			String enable =" disabled";
			pemohon = getIPembelian().findByPermohonan(getParam("txtidPermohonan"));
			if(pemohon == null){
				context.put("penjualMode", "new");
			}else{
				context.put("penjualMode", "update");
			}

			vm = PATH+"tanah.jsp";	
		}
		else if(command.equalsIgnoreCase("simpanPenjual")){
			System.out.println("==simpanPenjual==");
			getPermohonanInfo();
			getPenjualDetails();
			getdoChange();
			pemohon = getIPembelian().simpanPenjual(pemohon);
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			context.put("pemiliks", p);
			context.put("mode", "readonly");
			context.put("penjualMode", "");
			context.put("enable", " disabled");
			context.put("pemohon", pemohon);
			vm = PATH+"tanah.jsp";
		}
		else if(command.equalsIgnoreCase("kemaskiniPenjual")){
			System.out.println("==kemaskiniPenjual==");
			getPermohonanInfo();
			getdoChange();
			String penjualMode = "update";
			String mode =" ";
			pemohon = getIPembelian().findByPermohonan(getParam("txtidPermohonan"));
			if(pemohon == null){
				penjualMode = "new";
				mode ="";
			}
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			context.put("pemiliks", p);
			context.put("mode", mode);
			context.put("penjualMode", penjualMode);
			context.put("enable", " ");
			context.put("selectedTab", 3);
			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
				context.put("selectedTab", 2);
			}
			vm = PATH+"tanah.jsp";
		}
		else if(command.equalsIgnoreCase("updatePenjual")){
			System.out.println("==updatePenjual==");
			getPermohonanInfo();
			getPenjualDetails();
			pemohon = getIPembelian().updatePenjual(pemohon);
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP",Long.parseLong(pemohon.getIdNegeri()) ,"disabled", " onChange=\"doChangePenjualNegeri()\" "));
			context.put("selectDaerahP", HTML.SelectDaerahByNegeri(pemohon.getIdNegeri(), "selectDaerahP", Long.parseLong(pemohon.getIdDaerah()), "disabled"));
			context.put("pemiliks", p);
			context.put("mode", "readonly");
			context.put("penjualMode", "update");
			context.put("enable", " disabled");
			context.put("pemohon", pemohon);
			context.put("selectedTab", 3);
			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
				context.put("selectedTab", 2);
			}
			vm = PATH+"tanah.jsp";
		}
		else if(command.equalsIgnoreCase("assignPenjual")){
			getPermohonanInfo();
			getPenjualDetails();
			String flag = pemohon.getFlagPemilik();
			if(!flag.equalsIgnoreCase("")){
				pemohon.setFlagPemilik("Y");
				pemilik = getIPemilik().findPemilik(flag);
				pemohon.setNama(pemilik.getNama());
				pemohon.setAlamat1(pemilik.getAlamat1());
				pemohon.setAlamat2(pemilik.getAlamat2());
				pemohon.setAlamat3(pemilik.getAlamat3());
				pemohon.setPoskod(pemilik.getPoskod());
				pemohon.setTel(pemilik.getTel());
				pemohon.setFax(pemilik.getFax());
				pemohon.setNoPemohon(pemilik.getNoRujukan());
				pemohon.setIdNegeri(pemilik.getIdNegeri());
				pemohon.setIdDaerah(pemilik.getIdDaerah());
				context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP",Long.parseLong(pemohon.getIdNegeri()) ,"disabled", " onChange=\"doChangePenjualNegeri()\" "));
				context.put("selectDaerahP", HTML.SelectDaerahByNegeri(pemohon.getIdNegeri(), "selectDaerahP", Long.parseLong(pemohon.getIdDaerah()), "disabled"));
			}
			String penjualMode ="";
			String mode = "";
			String enable ="";
		
			if( getIPembelian().findByPermohonan(String.valueOf(permohonan.getIdPermohonan()))== null){
				penjualMode = "new";
				mode ="";
				enable ="";
				context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP"," onChange=\"doChangePenjualNegeri()\" "));
				context.put("selectDaerahP", HTML.SelectDaerah("selectDaerahP"));
				
			}
			else{
				mode=" readonly";
				penjualMode = "";
				enable =" disabled";
			}
			context.put("pemohon", pemohon);
			context.put("mode", mode);
			context.put("penjualMode", penjualMode);
			context.put("pemohon", pemohon);
			context.put("enable", enable);
			context.put("selectedTab", 3);
			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
				context.put("selectedTab", 2);
			}
			vm = PATH+"tanah.jsp";
		
		}else if(command.equalsIgnoreCase("perakuanPembelian")){
			getPermohonanInfo();
			context.put("page","3");
			pemohon = getIPembelian().findByPermohonan(getParam("txtidPermohonan"));
			if(pemohon == null){
				context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP"," onChange=\"doChangePenjualNegeri()\" "));
				context.put("selectDaerahP", HTML.SelectDaerah("selectDaerahP"));
	
				Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
				context.put("pemiliks", p);
				context.put("mode", " ");
				context.put("penjualMode", "new");
				context.put("enable", " ");
				context.put("selectedTab", 3);
				if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
						ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
					context.put("selectedTab", 2);
				}
				context.put("page","2");
				context.put("pemohon", null);
				vm = PATH+"tanah.jsp";
			
			}else{
				
				getPermohonanInfo();
				getSemakanPerakuanPembelian();
				vm = PATH+"perakuanPembelian.jsp";
				context.put("page","3");
				context.put("semakMode", "");
				context.put("mode", " disabled");
			
			}
			
		}else if(command.equalsIgnoreCase("kemaskiniPerakuan")){
			
			getSemakanPerakuanPembelian();
			vm = PATH+"perakuanPembelian.jsp";
			context.put("page","3");
			context.put("semakMode", "update");
			context.put("mode", " ");
		}
		else if(command.equalsIgnoreCase("simpanPerakuan")){
			getPermohonanInfo();
			simpanSenaraiSemakAkuanPembelian(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			getSemakanPerakuanPembelian();
			vm = PATH+"perakuanPembelian.jsp";
			context.put("page","3");
			context.put("semakMode", "");
			context.put("mode", " disabled");
		}
		else{
			String noFail = getParam("noFail");
			String carian = getParam("namaFail");
			idNegeri = getParam("socNegeri") == "" ? "0" : getParam("socNegeri");
			
			Vector<?> v = getIPembelian().findFail(carian, noFail, idNegeri);
			context.put("lists", v);
			context.put("socNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri)));
			setupPage(ses,action, v);
			
			//context.put("socNegeri",HTML.SelectNegeri("socNegeri"));
			vm = PATH+"index.jsp";
		}
		
		context.put("isTanah", isTanah);

				
		return vm;
		
	}
	
	private void getSenaraiSemakan(){
		
	}
	private void getKementerianDetail(String idKementerian,String mode)throws Exception{
		String disabled ="disabled";
		if(mode=="edit"){
			disabled ="";
		}
		
		String socKementerian = HTML.SelectKementerian("socKementerian", (idKementerian == "") ? null : Long.parseLong(idKementerian),disabled, " onChange=\"doChangeKementerian()\" ");
	    String socAgensi = HTML.SelectAgensiByKementerian("socAgensi","",Long.parseLong("1"),"");
	    context.put("selectKementerian",socKementerian);
		context.put("selectAgensi",socAgensi);
	
		
	}
	
	private void getListItem()throws Exception{
		context.put("selectKementerian",HTML.SelectKementerian("socKementerian"));
		context.put("selectAgensi",HTML.SelectAgensi("socAgensi"));
		//HTML.SelectStatus(selectName, selectedValue, disability)
	}
	private void getValues(){
		String idNegeri = getParam("socNegeri");
		String idKementerian = getParam("socKementerian");
		String idSubUrusan = getParam("socSuburusan");
		String idAgensi = getParam("socAgensi");			
		String tajuk = getParam("txtTajuk");		
		String failKJP = getParam("txtNoFailKJP");
		String tarikhSuratKJP = getParam("txdTarikhSurKJP");
		String noFailLain = getParam("txtNoFailLain");
		String tarikhPermohonan = getParam("txdTarikhPermohonan");
		String idJenisTanah = getParam("socStatusTanah");
		String idKeselamatan = getParam("socTarafKeselamatan");
		String noFail = getParam("txtNoFailSek");
		String idPermohonan = getParam("txtidPermohonan");
		String idHtpPermohonan = getParam("txtidHtpPermohonan");
		fail = new PfdFail();
		permohonan = new Permohonan();
		htpPermohonan = new HtpPermohonan();
		
		
		fail.setIdNegeri(idNegeri);
		fail.setIdKementerian(idKementerian);
		fail.setIdSubUrusan(idSubUrusan);
		fail.setIdTarafKeselamatan(idKeselamatan);
		fail.setNoFail(noFail);
		//senaraiFail = new FrmSenaraiFailTerimaPohonData();
		//senaraiFail.getKementerianByMampu(Integer.parseInt(idKementerian));
		
		htpPermohonan.setIdHtpPermohonan(idHtpPermohonan);
		htpPermohonan.setIdAgensi(idAgensi);
		permohonan.setTujuan(tajuk);
		htpPermohonan.setNoRujukanKJP(failKJP);
		permohonan.setTarikhSurat(tarikhSuratKJP);
		htpPermohonan.setNoRujukanLain(noFailLain);
		permohonan.setTarikhTerima(tarikhPermohonan);
		htpPermohonan.setIdJenisTanah(idJenisTanah);
		permohonan.setIdPermohonan(idPermohonan);
		
		
		permohonan.setPfdFail(fail);
		htpPermohonan.setPermohonan(permohonan);

		context.put("permohonan", htpPermohonan);
	}
	private void getValuesMaklumatTanah(){
		
		String idPermohonan = getParam("txtidPermohonan");
		String idNegeri = getParam("socNegeri");
		String idDaerah = getParam("socDaerah");
		String idMukim = getParam("socMukim");
		String idHakmilik = getParam("socJenisHakmilik");
		String idKodLot = getParam("socLot");
		String noLot = getParam("txtNoLot");
		String idRizab = getParam("socRizab");
		String tarikhMula = getParam("txdTarikhTerima");
		String tarikhLuput = getParam("txdTarikhLuput");
		String luasTanah = getParam("txtLuas");		
		String noPelanAkui = getParam("txtNoPelan");
		String noHakMilik = getParam("txtNoHakmilik");
		String unitLuas = getParam("socLuas");
		String kategoriTanah = getParam("socKategori");
		String idSubkategoriTanah = getParam("");
		String peganganHakmilik = getParam("");
		String noBangunan = getParam("noBangunan");
		String noTingkat = getParam("noTingkat");
		String noPetak = getParam("noPetak");	
		String idHakmilikUrusan = getParam("txtIdHakmilikUrusan");
		String statusTanah = getParam("socTaraf");
		String tempohBaki = getParam("txtTempoh");
		String tempohBakiTanah = getParam("txtTempohbaki");
		
		String ddownHakmilik = getParam("ddownHakmilik");
		String NamaPemaju = getParam("txtNamaPemaju");
		String NoRuj = getParam("txtNoRuj");
		String Alamat1 = getParam("txtAlamat1");
		String Alamat2 = getParam("txtAlamat2");
		String Alamat3 = getParam("txtAlamat3");
		String Poskod = getParam("txtPoskod");
		String NoTelefon = getParam("txtNoTelefon");
		String NoFax = getParam("txtNoFax");

		urusan = new HakmilikUrusan();
		permohonan = new Permohonan();
		
		//urusan.setDdownHakmilik(ddownHakmilik);
		urusan.setIdhakmilikurusan(idHakmilikUrusan);
		urusan.setIdNegeri(idNegeri);
		urusan.setIdDaerah(idDaerah);
		urusan.setIdMukim(idMukim);
		urusan.setIdSeksyen(getParam("socSeksyen"));
		urusan.setIdHakmilik(idHakmilik);
		urusan.setIdLot(idKodLot);
		urusan.setNolot(noLot);
		urusan.setIdJenisRizab(idRizab);
		urusan.setTarikhMula(tarikhMula);
		urusan.setTarikhMula(tarikhMula);
		urusan.setTarikhLuput(tarikhLuput);
		urusan.setLuas(luasTanah);
		urusan.setNoPlan(noPelanAkui);
		urusan.setNohakmilik(noHakMilik);
		urusan.setIdLuas(unitLuas);
		urusan.setIdKategoriTanah(kategoriTanah);
		urusan.setIdSubKetegoriTanah(idSubkategoriTanah);
		urusan.setNoBangunan(noBangunan);
		urusan.setNoTingkat(noTingkat);
		urusan.setNoPetak(noPetak);
		urusan.setPeganganHakmilik(peganganHakmilik);
		urusan.setNoBangunan(noBangunan);
		urusan.setNoTingkat(noTingkat);
		urusan.setNoPetak(noPetak);
		urusan.setStatusTanah(statusTanah);
		urusan.setTempohTanah(tempohBaki);
		urusan.setTempohBakiTanah(tempohBakiTanah);

		permohonan.setIdPermohonan(idPermohonan);
		
		urusan.setPermohonan(permohonan);
		context.put("urusan", urusan);
		
	}
	
private void getValuesPemilik(){
		String idNegeri = getParam("socNegeri");
		String idDaerah = getParam("socDaerah");
		String selectJenisNoPB = getParam("selectJenisNoPB");
		String idHakmilikUrusan = getParam("txtIdHakmilikUrusan");
		String ddownHakmilik = getParam("ddownHakmilik");
		String namaPemaju = getParam("txtNamaPemaju");
		String noRuj = getParam("txtNoRuj");
		String alamat1 = getParam("txtAlamat1");
		String alamat2 = getParam("txtAlamat2");
		String alamat3 = getParam("txtAlamat3");
		String poskod = getParam("txtPoskod");
		String noTelefon = getParam("txtNoTelefon");
		String noFax = getParam("txtNoFax");
		String Idpihakberkepentingan =getParam("Idpihakberkepentingan");
	
		pemilik = new PihakBerkepentingan();
		
		pemilik.setIdpihakberkepentingan(Idpihakberkepentingan);
		pemilik.setIdHakmilikUrusan(ddownHakmilik);
		pemilik.setJenisPB(selectJenisNoPB);
		pemilik.setDdownHakmilik(ddownHakmilik);
		pemilik.setIdNegeri(idNegeri);
		pemilik.setIdDaerah(idDaerah);
		pemilik.setIdHakmilikurusanPB(idHakmilikUrusan);
		pemilik.setNama(namaPemaju);
		pemilik.setAlamat1(alamat1);
		pemilik.setAlamat2(alamat2);
		pemilik.setAlamat3(alamat3);
		pemilik.setNoRujukan(noRuj);
		pemilik.setPoskod(poskod);
		pemilik.setTel(noTelefon);
		pemilik.setFax(noFax);

		context.put("pemilik", pemilik);
		
	}

	private void viewDetail2() throws  Exception{	
		String socNegeri = HTML.SelectNegeri("socNegeri",htpPermohonan.getPermohonan().getPfdFail().getIdNegeri(),readonly);
		String socKementerian = HTML.SelectKementerian("socKementerian", htpPermohonan.getPermohonan().getPfdFail().getIdKementerian(),readonly);
		String socAgensi = HTML.SelectAgensiByKementerian("socAgensi",String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdKementerian()),htpPermohonan.getIdAgensi(),readonly);
	    context.put("selectKementerian",socKementerian);
		context.put("selectAgensi",socAgensi);
		context.put("socNegeri",socNegeri);
		//2020/09/11 paparan boleh pilih
//		context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("2",null,"socSuburusan", null,""));
		context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan("2", "socSuburusan",htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),readonly));
		context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", htpPermohonan.getIdJenisTanah(),readonly));
		context.put("socTarafKeselamatan", HTML.SelectTarafKeselamatan("socTarafKeselamatan", htpPermohonan.getPermohonan().getPfdFail().getIdTarafKeselamatan(),readonly) );
	}

	private void tambahTanahDetail() throws  Exception{
		String idPermohonan = getParam("txtidPermohonan")==""?"0":getParam("txtidPermohonan");
		String idHtpPermohonan = getParam("txtidHtpPermohonan")==""?"0":getParam("txtidHtpPermohonan");
		String socNegeri = getParam("socNegeri")==""?"0":getParam("socNegeri");
		String socDaerah = getParam("socDaerah") ==""?"0":getParam("socDaerah");
		String idMukim = getParam("socMukim") ==""?"0":getParam("socMukim");
		Long idMukimLong = Long.parseLong(idMukim);
		
		String socLot = getParam("socLot") ==""?"0":getParam("socLot");
		htpPermohonan = getIPembelian().findPermohonan(idPermohonan, idHtpPermohonan);
		context.put("socNegeri",HTML.SelectNegeri("socNegeri",htpPermohonan.getPermohonan().getPfdFail().getIdNegeri(),"disabled"));
		context.put("socDaerah", HTML.SelectDaerahByNegeri(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdNegeri()), "socDaerah",Long.parseLong(socDaerah),null," onChange=\"doChangeDaerah()\" "));
		context.put("socMukim",HTML.SelectMukimByDaerah(
				socDaerah, "socMukim",idMukimLong,"","onChange=\"pilihMukim()\" "));
//		context.put("socMukim",HTML.SelectMukimByDaerah(socDaerah, "socMukim"));
		context.put("socJenisHakmilik" , HTML.SelectJenisHakmilik("socJenisHakmilik"));
		context.put("socLot", HTML.SelectLot("socLot", Long.parseLong(socLot), ""));
		context.put("socRizab", HTML.SelectRizab("socRizab"));
		context.put("socLuas", HTML.SelectLuas("socLuas"));
		context.put("socKategori", HTML.SelectKategori("socKategori"));
		context.put("socseksyen", getPilihan().Pilihan("socSeksyen", "",idMukim));

		context.put("htpPermohonan", htpPermohonan);
	}
	private void tambahTanahDetail2() throws  Exception{
		String idPermohonan = getParam("txtidPermohonan")==""?"0":getParam("txtidPermohonan");
		String idHtpPermohonan = getParam("txtidHtpPermohonan")==""?"0":getParam("txtidHtpPermohonan");
		String socNegeri = getParam("socNegeri")==""?"0":getParam("socNegeri");
		String socDaerah = getParam("socDaerah") ==""?"0":getParam("socDaerah");
		String idHakmilikUrusan = getParam("idHakmilikUrusan") ==""?"0":getParam("idHakmilikUrusan");
		htpPermohonan = getIPembelian().findPermohonan(idPermohonan, idHtpPermohonan);
		//urusan = getIPembelian().findByHakmilikUrusanId(idHakmilikUrusan);
		context.put("socNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(urusan.getIdNegeri()),"disabled"));
		//context.put("socDaerah", HTML.SelectDaerahby("socDaerah", Long.parseLong(urusan.getIdDaerah()), ""));
		context.put("socDaerah", HTML.SelectDaerahByNegeri(urusan.getIdNegeri(), "socDaerah",Long.parseLong(urusan.getIdDaerah()),null," onChange=\"doChangeDaerah()\" "));
		context.put("socMukim",HTML.SelectMukimByDaerah(urusan.getIdDaerah(), "socMukim", Long.parseLong(urusan.getIdMukim()), ""));
		context.put("socJenisHakmilik" , HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(urusan.getIdHakmilik()), ""));
		context.put("socLot", HTML.SelectLot("socLot", Long.parseLong(urusan.getIdLot()), ""));
		context.put("socRizab", HTML.SelectRizab("socRizab", Long.parseLong(urusan.getIdJenisRizab()), ""));
		context.put("socLuas", HTML.SelectLuas("socLuas", Long.parseLong(urusan.getIdLuas()), ""));
		context.put("socKategori", HTML.SelectKategori("socKategori", Long.parseLong(urusan.getIdKategoriTanah()), ""));

		context.put("htpPermohonan", htpPermohonan);
	}
	private void tambahTanahDetail3() throws  Exception{
		String idPermohonan = getParam("txtidPermohonan")==""?"0":getParam("txtidPermohonan");
		String idHtpPermohonan = getParam("txtidHtpPermohonan")==""?"0":getParam("txtidHtpPermohonan");
		String socNegeri = getParam("socNegeri")==""?"0":getParam("socNegeri");
		String socDaerah = getParam("socDaerah") ==""?"0":getParam("socDaerah");
		String idHakmilikUrusan = getParam("idHakmilikUrusan") ==""?"0":getParam("idHakmilikUrusan");
		String dd = getParam("ddownHakmilik")==""?"0":getParam("ddownHakmilik");
		Long selectJenisNoPB = Long.parseLong(getParam("selectJenisNoPB")==""?"0":getParam("selectJenisNoPB"));
		
		htpPermohonan = getIPembelian().findPermohonan(idPermohonan, idHtpPermohonan);

		context.put("socNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(socNegeri),""," onChange=\"doChangeDaerah2()\" "));
		context.put("socDaerah", HTML.SelectDaerahByNegeri(socNegeri, "socDaerah"));
		context.put("selectJenisNoPB", HTML.SelectJenisNoPb("selectJenisNoPB", selectJenisNoPB, ""));
		context.put("htpPermohonan", htpPermohonan);
	}
	
	private IPembelian getIPembelian(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	} 
	
	private IPemilik getIPemilik(){
		if(iPemilik==null){
			iPemilik = new PemilikBean();
		}
		return iPemilik;
		
	}
	private void getPermohonanInfo()throws Exception{
		String idPermohonan = getParam("txtidPermohonan");
		String idHtpPermohonan = getParam("txtidHtpPermohonan");
		htpPermohonan = getIPembelian().findPermohonan(idPermohonan,idHtpPermohonan);
		context.put("htpPermohonan", htpPermohonan);
	}
	
	private void getPenjualDetails(){
		String idPenjual = getParam("idPenjual");
		String idPermohonan = getParam("txtidPermohonan");
		String flag = getParam("PenjualSama");
		String nama = getParam("txtNamaPenjual");
		String ic =getParam("txtKodPenjual");
		String alamat1 = getParam("txtAlamat1");
		String alamat2 = getParam("txtAlamat2");
		String alamat3 = getParam("txtAlamat3");
		String poskod = getParam("txtPoskod");
		String idNegeri = getParam("selectNegeriP");
		String idDaerah = getParam("selectDaerahP");
		String tel = getParam("txtNoTelefon");
		String fax = getParam("txtNoFax");
		String noPA = getParam("txtNoPA");
		
		permohonan = new Permohonan();
		permohonan.setIdPermohonan(idPermohonan);
		pemohon = new Pemohon();
		pemohon.setIdPemohon(idPenjual);
		pemohon.setFlagPemilik(flag);
		pemohon.setNama(nama);
		pemohon.setNoPemohon(ic);
		pemohon.setAlamat1(alamat1);
		pemohon.setAlamat2(alamat2);
		pemohon.setAlamat3(alamat3);
		pemohon.setPermohonan(permohonan);
		pemohon.setPoskod(poskod);
		pemohon.setIdNegeri(idNegeri);
		pemohon.setIdDaerah(idDaerah);
		pemohon.setTel(tel);
		pemohon.setFax(fax);
		pemohon.setNoPA(noPA);
		
		context.put("pemohon", pemohon);
	}
	
	private void getBangunanValues(){
		String idBangunan = getParam("idBangunan");
		String idHakmilikUrusan = getParam("idHakmilikUrusan");
		String noPetak= getParam("noPetak");
		String noBangunan = getParam("noBangunan");
		String noTingkat = getParam("noTingkat");
		
		bangunan = new Bangunan();
		urusan = new HakmilikUrusan();
		urusan.setIdhakmilikurusan(idHakmilikUrusan);
		bangunan.setIdBangunan(idBangunan);
		bangunan.setHakmilikUrusan(urusan);
		bangunan.setNoPetak(noPetak);
		bangunan.setNoBangunan(noBangunan);
		bangunan.setNoTingkat(noTingkat);
		
		context.put("bangunan", bangunan);
	}
	
	private void getSenaraiSemakFail()throws Exception{
		context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPembelianSemakNew"));
		context.put("semakclass", new FrmSemakan());
	}
	private void getSemakanPerakuanPembelian()throws Exception{
		context.put("semakclass", new FrmSemakan());
		Vector semakList = FrmSemakan.getSenaraiSemakan("frmBelianAkuan");
		context.put("perakuanPembelian", semakList);
	}
	private void getdoChange() throws Exception {
		String selectNegeriP = getParam("selectNegeriP");
		String selectDaerahP = getParam("selectDaerahP")==""?"0":getParam("selectDaerahP");
		context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP", Long.parseLong(selectNegeriP),""," onChange=\"doChangePenjualNegeri()\" "));
		context.put("selectDaerahP", HTML.SelectDaerahByNegeri(selectNegeriP, "selectDaerahP",Long.parseLong(selectDaerahP),""));
		
	}
	private void simpanSenaraiSemakFail(String idPermohonan)throws Exception{
		String[] cbsemaks = this.request.getParameterValues("cbsemaks");
		FrmSemakan frmSemak = new FrmSemakan();
		frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
		if (cbsemaks != null) {
			for (int i = 0; i < cbsemaks.length; i++) {
				frmSemak = new FrmSemakan();
				frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
			}
		}
		
		
	}
	
	private void simpanSenaraiSemakAkuanPembelian(String idPermohonan)throws Exception{
		String[] cbsemaks = this.request.getParameterValues("akuans");
		FrmSemakan frmSemak = new FrmSemakan();
		frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
		if (cbsemaks != null) {
			for (int i = 0; i < cbsemaks.length; i++) {
				frmSemak = new FrmSemakan();
				frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
			}
		}
		
		
	}
	
public void setupPage(HttpSession session,String action,Vector list) {
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
		this.context.put("lists",paging.getPage(page));
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

	//Skrin Tindakan
		private String kemaskiniSimpanStatusSelesai(String idFail
			,String idPermohonan
			,String idSubUrusan
			,String langkah) throws Exception {
			try {				
				
				Tblrujsuburusanstatusfail subUrusanStatusFail = new Tblrujsuburusanstatusfail();
				subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
				subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
				subUrusanStatusFail.setAktif("0");
			
				Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
				long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,idSubUrusan,"=");
				subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
				subUrusanStatusFailN.setAktif("1");
				subUrusanStatusFailN.setUrl("-");
				subUrusanStatusFailN.setIdMasuk(Long.parseLong(userID));
				return String.valueOf(getStatus().kemaskiniSimpanStatusAktif(subUrusanStatusFail, subUrusanStatusFailN));
					
			} catch (Exception e) {
				throw new Exception(getIHTP().getErrorHTML("Ralat FrmPerakuanPembelian, kemaskiniSimpanStatusSelesai:"+e.getMessage()));
			}
		
		}	
		
		private IHtp getIHTP(){
			if(iHTP== null)
				iHTP = new HtpBean();
			return iHTP;
		}	

	private IHTPStatus getStatus(){
		if(iStatus==null){
			iStatus = new HTPStatusBean();
		}
		return iStatus;
	
	}
	private IUtilHTMLPilihan getPilihan(){
		if(iPilihan==null){
			iPilihan = new UtilHTMLPilihanSeksyenUPI();
		}
		return iPilihan;
	
	}
	
	private IEmel getEmelSemak(){
		if(emelSemak == null)
			emelSemak = new HTPEmelSemakanBean();
		return emelSemak;
	}
	private IEmel getEmeLulus(){
		if(emelulus == null)
			emelulus = new HTPEmelKelulusanBean();
		return emelulus;
	}
}


