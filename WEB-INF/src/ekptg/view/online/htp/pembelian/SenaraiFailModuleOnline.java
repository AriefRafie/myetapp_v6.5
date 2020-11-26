package ekptg.view.online.htp.pembelian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.action.AjaxModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.engine.EmailSender;
import ekptg.engine.EmailProperty;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.entities.UserKementerian;
import ekptg.model.htp.FrmGadaianSemakan1Data;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailTerimaPohonData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HTPEmelSemakBean;
import ekptg.model.htp.HTPPermohonanBean;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHTPEmel;
import ekptg.model.htp.IHTPPermohonan;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.IPenggunaKementerian;
import ekptg.model.htp.IUtilHTML;
import ekptg.model.htp.PenggunaKementerianBean;
import ekptg.model.htp.PihakBerkepentingan;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.UtilHTMLBean;
import ekptg.model.htp.entity.Bangunan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pemohon;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.online.IOnline;
import ekptg.model.htp.online.OnlineBean;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.IPembelianOnline;
import ekptg.model.htp.pembelian.IPemilik;
import ekptg.model.htp.pembelian.PembelianBean;
import ekptg.model.htp.pembelian.PembelianOnlineBean;
import ekptg.model.htp.pembelian.PemilikBean;
import ekptg.model.htp.utiliti.HTPEmelPermohonanBean;
import ekptg.model.htp.utiliti.HTPEmelSemakanBean;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.utils.IUserPegawai;
import ekptg.model.utils.UserBean;
import ekptg.model.utils.emel.EmailConfig;
import ekptg.model.utils.emel.IEmel;


public class SenaraiFailModuleOnline extends AjaxModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6717995094916712274L;
	private static final String PATH="app/htp/pembelian/fail/online/";
	static Logger myLog = Logger.getLogger(ekptg.view.online.htp.pembelian.SenaraiFailModuleOnline.class);
	private static final Log log = LogFactory.getLog(SenaraiFailModuleOnline.class);
	private Bangunan bangunan = null;
	private final String IDJENISTANAH = "4"; //TANAH MILIK - TM
	private final String URUSAN_TANAH = "01";
	private final String URUSAN_BANGUNAN = "03";
	private final String ID_URUSANTANAH = "24";
	private final String ID_URUSANTANAH1 = "25"; 	
	private ekptg.model.htp.IHTPEmel iHTPEmel = null;
	private ekptg.model.utils.emel.IEmel emelSemak = null;
	private FrmSenaraiFailTerimaPohonData senaraiFail = null;
	private HakmilikUrusan urusan = null;
	private HtpPermohonan htpPermohonan = null;
	private IHTPPermohonan iHTPPermohonan = null;
	private IHtp iHTP = null;  
	private IOnline iOnline = null;
	private IPembelian iPembelian = null;
	private IPembelianOnline iPembelianOnline = null;
	private IPenggunaKementerian iPengguna = null;
	private IPemilik iPemilik = null;
	private IUserPegawai iUser = null;
	private IUtilHTML iUtil = null;

	private Pemohon pemohon;
	private Permohonan permohonan = null;
	private PfdFail fail = null;
	private PihakBerkepentingan pemilik = null; 	
	private String vm = PATH+"index_.jsp";
	private String userID = null;
	private String readonly = "disabled class = \"disabled\"";
	private String idNegeri ="";
	private String socAgensi = "";
	private String socKementerian = "";
	private String socNegeri = "";
	private String socStatus = "";
	private String socSubUrusan = "";
	private String idAgensi = "";
 	private String idStatus = "";
// 	private String idSubUrusan = "";
 	private String idSuburusan = "";
 	private String IDSUBURUSANS = "23,25";

	private Tblrujsuburusanstatusfail subUrusanStatusFail = null;
	private UserKementerian uk = null;	

	String actionI = "";
	String id_kementerian = "";
	String RO_General = "";
	String jawatan = "";
	String idJawatan = "";

	@Override
	public String doAction() throws Exception {
	try{	
		HttpSession ses = request.getSession();
		userID = (String)ses.getAttribute("_ekptg_user_id");
		String command = getParam("command");
		String action = getParam("action");
		actionI = action;
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
		String idHakmilikUrusan =  getParam("idHakmilikUrusan");
		String idNegeriPemohon = "";
    	String namaPemohon = getParam("namaPemohon");
		context.put("URUSAN_BANGUNAN",URUSAN_BANGUNAN);
		context.put("URUSAN_TANAH",ID_URUSANTANAH);
		context.put("URUSAN_TANAH1",ID_URUSANTANAH1);
		
		String dateToday = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
		context.put("dateToday", dateToday);
		
		if (id_kementerian == null || id_kementerian.trim().length() == 0){
			uk = getIPengguna().getKementerian(userID);
			if(uk == null){
				//throw new Exception("[ONLINE-HTP REKOD] KEMENTERIAN TIDAK DIJUMPAI");
				//return vm;
				getIHTP().getErrorHTML("[ONLINE-HTP PEMBELIAN] KEMENTERIAN TIDAK DIJUMPAI");
	
			}
			id_kementerian = String.valueOf(uk.getAgensi().getKementerian().getIdKementerian());
			//System.out.println("+++++"+id_kementerian);
		}
		Hashtable<String,String> hUser = getIUser().getPengguna(userID);
		jawatan = String.valueOf(hUser.get("jawatan"));
		idJawatan = String.valueOf(hUser.get("idjawatan"));
		context.put("idjawatan", idJawatan);

		myLog.info("JAWATAN="+jawatan);
		myLog.info("JAWATAN="+hUser.get("idjawatan"));
//		if(command.equals("tambahFail")){
//			myLog.info("tambahFail ::"+vm);
//			getSenaraiSemakFail();
//			String mode="";
//			context.put("mode", mode);
//			context.put("pageMode", "edit");
//			context.put("socNegeri",HTML.SelectNegeri("socNegeri"));
//			context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("2","23,25", "socSuburusan", null,""));
//			context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", null, ""));
//			context.put("socTarafKeselamatan", HTML.SelectTarafKeselamatan("socTarafKeselamatan", null, "") );
//			context.remove("htpPermohonan");
//			getKementerianDetail("", "edit");
//			context.put("inputstyleread", null );
//			//vm= PATH+"file.jsp";
//			vm= PATH+"fileOnline.jsp";
//			
//			context.put("page","1");
//		}
		if(command.equals("tambahFail")){	//online
			
			getSenaraiSemakFail();
			String mode="";
			context.put("mode", mode);
			context.put("pageMode", "edit");
			context.put("socNegeri",HTML.SelectNegeri("socNegeri"));
			context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("2","23,25", "socSuburusan", null,""));
			//context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", null, ""));
			context.put("statusTanah", UtilHTML.SelectJenisTanah("socStatusTanah", Long.parseLong(IDJENISTANAH), "",IDJENISTANAH));
			context.put("socTarafKeselamatan", HTML.SelectTarafKeselamatan("socTarafKeselamatan", null, "") );
			context.remove("htpPermohonan");
			getKementerianDetailOnline(id_kementerian, "edit");
			context.put("inputstyleread", null );
			
			myLog.info("tambahFail :: " +vm);			
			context.put("page","1");
			
			vm= PATH+"fileOnline.jsp";
			
//		}else if(command.equals("doChangeKementerian")){
//			myLog.info("doChangeKementerian ::"+vm);
//			getSenaraiSemakFail();
//			getValues();
//			String idNegeri = getParam("socNegeri");
//			String idKementerian = getParam("socKementerian");
//			String socKementerian = HTML.SelectKementerian("socKementerian", (idKementerian == "") ? null : Long.parseLong(idKementerian),"", " onChange=\"doChangeKementerian()\" ");
//			String socAgensi = HTML.SelectAgensiByKementerian("socAgensi",idKementerian,Long.parseLong("1"),"");
//			String socNegeri = HTML.SelectNegeri("socNegeri",(idNegeri == "") ? null : Long.parseLong(idNegeri),"");
//		    context.put("selectKementerian",socKementerian);
//			context.put("selectAgensi",socAgensi);
//			context.put("socNegeri",socNegeri);
//			//context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan("2", "socSuburusan",fail.getIdSubUrusan(),""));
//			context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("2","23,25", "socSuburusan", null,""));
//			context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", htpPermohonan.getIdJenisTanah(), ""));
//			context.put("socTarafKeselamatan", HTML.SelectTarafKeselamatan("socTarafKeselamatan", fail.getIdTarafKeselamatan(), "") );
//			//context.put("idPermohonan", permohonan.getIdPermohonan());
//			context.put("tajuk", permohonan.getTujuan());
//			context.put("noFailKJP", htpPermohonan.getNoRujukanKJP());
//			context.put("noFailLain", htpPermohonan.getNoRujukanLain());
//			String mode="";
//			context.put("inputstyleread", null );
//			context.put("mode", mode);
//			context.put("pageMode", "edit");
//			vm= PATH+"file.jsp";
			
		}else if(command.equalsIgnoreCase("Xsimpan")){
			myLog.info("simpan ::"+vm);
			getValues();
			viewDetail2();
			getSenaraiSemakFail();
			htpPermohonan = getIPembelian().simpanPermohonan(htpPermohonan);
			
			simpanSenaraiSemakFail(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", "update");
			context.put("inputstyleread", readonly );
			vm= PATH+"file.jsp";

		}else if(command.equalsIgnoreCase("simpan")){
			myLog.info("====Simpan====");
			getValues();
			viewDetail2();
			getSenaraiSemakFail();
			htpPermohonan = getIPembelian().simpanPermohonanOnline(htpPermohonan);
			
			simpanSenaraiSemakFail(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", "update");
			context.put("inputstyleread", readonly );
			vm= PATH+"fileOnline.jsp";
						
		}else if(command.equalsIgnoreCase("update")){
			getValues();
			myLog.info("update ::"+vm);
			htpPermohonan = getIPembelian().findPermohonan(String.valueOf(permohonan.getIdPermohonan()),String.valueOf(htpPermohonan.getIdHtpPermohonan()));
			
			viewDetail2();
			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", "kemas");
			context.put("inputstyleread", "");
			getSenaraiSemakFail();
//			this.context.put("num_files",1);
//			RO_General = "readonly=\"readonly\"";
//			if ("changeLampiran".equals(actionI)) {
//				RO_General = "";
//				String x = getParam("X");
//				actionI = x;
//				int j = getParamAsInteger("jumlahlampiran");
//				this.context.put("num_files", j);
//				
//			}
			//vm= PATH+"file.jsp";
			vm= PATH+"fileOnline.jsp";
			
		}else if(command.equalsIgnoreCase("detail")){	
			getValues();
			htpPermohonan = getIPembelian().findPermohonan(idPermohonan,idHtpPermohonan);
			viewDetail2();
			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", "update");
			context.put("inputstyleread", readonly);
			context.put("page","1");
			getSenaraiSemakFail();		
			vm= PATH+"file2.jsp";
			myLog.info("detail ::"+vm);
			
		}else if(command.equalsIgnoreCase("detailonline")){			
			getValues();
			htpPermohonan = getIPembelian().findPermohonan(idPermohonan,idHtpPermohonan);
			viewDetail2();
			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", "update");
			context.put("inputstyleread", readonly);
			context.put("page","1");
			getSenaraiSemakFail();
			
//			this.context.put("num_files",1);
//			RO_General = "readonly=\"readonly\"";
//			if ("changeLampiran".equals(actionI)) {
//				RO_General = "";
//				String x = getParam("X");
//				actionI = x;
//				int j = getParamAsInteger("jumlahlampiran");
//				this.context.put("num_files", j);
//				
//			}
			vm= PATH+"fileOnline.jsp";
			myLog.info("===detailonline===="+vm);	
			
		}else if(command.equalsIgnoreCase("simpanupdate")){
				
			getValues();
			viewDetail2();
			htpPermohonan = getIPembelianOnline().kemaskiniPermohonan(htpPermohonan,String.valueOf(permohonan.getIdPermohonan()),String.valueOf(htpPermohonan.getIdHtpPermohonan()));
			simpanSenaraiSemakFail(getParam("txtidPermohonan"));
			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", "update");
			context.put("inputstyleread", readonly);
			getSenaraiSemakFail();
			//vm= PATH+"file.jsp";
			vm= PATH+"fileOnline.jsp";
			myLog.info("simpanupdate ::"+vm);
		
		}else if(command.equals("maklumatanahonline")){
			getValues();
			getPermohonanInfo();
			myLog.info("Read Here");
			
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			context.put("mt", m);
			context.put("selectedTab", 0);
			vm = PATH+"tanahOnline.jsp";
			myLog.info("===Maklumat Tanah Online===" +vm+" "+idPermohonan);
			context.put("page","2");
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
		}else if(command.equals("maklumatTanah")){
			getValues();
			getPermohonanInfo();
			
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			context.put("mt", m);
			context.put("selectedTab", 0);
			
			vm = PATH+"tanah2.jsp";
			myLog.info("maklumatTanah ::"+vm);	
			context.put("page","2");
		}
		else if(command.equals("tambahTanah")){
			jawatan = String.valueOf(hUser.get("jawatan"));
			idJawatan = String.valueOf(hUser.get("idjawatan"));
			context.put("idjawatan", idJawatan);
			myLog.info("tambahTanah ::"+vm);	
			tambahTanahDetail();
			context.put("button", "simpan");
			context.put("urusan", urusan);
			vm = PATH+"tanahInfoOnline.jsp";
			
		}else if(command.equalsIgnoreCase("doChangeDaerah")){
			myLog.info("doChangeDaerah ::"+vm);
			getValuesMaklumatTanah();
			tambahTanahDetail();
			context.put("button", "simpan");
			
			vm = PATH+"tanahInfoOnline.jsp";
			
		}
		else if(command.equalsIgnoreCase("simpanMaklumatTanah")){
			myLog.info("simpanMaklumatTanah ::"+vm);
			getValuesMaklumatTanah();
			tambahTanahDetail();
			urusan = iPembelian.simpanHakmilikOnline(urusan);
			getValues();
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(String.valueOf(urusan.getPermohonan().getIdPermohonan()));
			context.put("urusan", urusan);
			context.put("mt", m);
			context.put("selectedTab", 0);
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
			vm = PATH+"tanahOnline.jsp";
		
		}else if(command.equalsIgnoreCase("detailTanah")){			
			getValuesMaklumatTanah();			
			urusan = getIPembelian().findByHakmilikUrusanId(idHakmilikUrusan);
			tambahTanahDetail2();
			context.put("urusan", urusan);
			context.put("button", "kemaskini");
			vm = PATH+"tanahInfo2.jsp";
			myLog.info("detailTanah ::"+vm);
			
		}else if(command.equalsIgnoreCase("detailtanahonline")){				
			getValuesMaklumatTanah();			
			urusan = getIPembelian().findByHakmilikUrusanId(idHakmilikUrusan);
			tambahTanahDetail2();
			context.put("urusan", urusan);
			context.put("button", "kemaskini");
			
			vm = PATH+"tanahInfoOnline.jsp";
			myLog.info("detailTanah Online::"+vm);	
			
		}else if(command.equalsIgnoreCase("kemaskiniMaklumatTanah")){	
			myLog.info("kemaskiniMaklumatTanah1::"+vm);
			getValuesMaklumatTanah();
			myLog.info("kemaskiniMaklumatTanah2::"+vm);
			tambahTanahDetail2();
			myLog.info("kemaskiniMaklumatTanah3::"+vm);
			urusan = getIPembelian().updateHakmilik(urusan);	
			myLog.info("kemaskiniMaklumatTanah4::"+vm);
			context.put("urusan", urusan);
			myLog.info("kemaskiniMaklumatTanah5::"+vm);
			vm = PATH+"tanahInfoOnline.jsp";
			myLog.info("kemaskiniMaklumatTanah online ::"+vm);
			context.put("buttonMode", 2);
			context.put("mode", 0);
			context.put("pemilik", pemilik);
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
			//context.put("mt", m);
			//context.put("pp", p);
			
		}else if(command.equals("deleteMaklumatTanah")){
			myLog.info("deleteMaklumatTanah ::"+vm);
			getIPembelian().deleteHakmilik(idHakmilikUrusan);
			getValues();
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(idPermohonan);
			context.put("mt", m);
			context.put("selectedTab", 0);
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
			vm = PATH+"tanahOnline.jsp";
		
		}
		else if(command.equals("maklumatBangunan")){
			getPermohonanInfo();
			Vector buildings = getIPembelian().findBangunanByPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			context.put("buildings", buildings);
			context.put("selectedTab", 1);
			vm = PATH+"tanah2.jsp";
			myLog.info("maklumatBangunan ::"+vm);
		}
		else if(command.equals("tambahBangunan")){
			myLog.info("tambahBangunan ::"+vm);
			getPermohonanInfo();
			Vector<HakmilikUrusan> hmu = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			context.put("hmu", hmu);
			context.put("selectedTab", 1);
			context.remove("bangunan");
			vm = PATH+"bangunanInfo.jsp";
			
		}	
		else if(command.equals("simpanBangunan")){
			myLog.info("simpanBangunan ::"+vm);
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
			myLog.info("updateBangunan ::"+vm);
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
			myLog.info("paparBangunan ::"+vm);
			getPermohonanInfo();
			Vector<HakmilikUrusan> hmu = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			context.put("hmu", hmu);
			bangunan = getIPembelian().findBangunan(getParam("idBangunan"));
			context.put("bangunan",bangunan);
			context.put("bangunanMode","update");
			vm = PATH+"bangunanInfo.jsp";
		}
		else if(command.equals("deleteBangunan")){
			myLog.info("deleteBangunan ::"+vm);
			getPermohonanInfo();
			getIPembelian().deleteBangunan(getParam("idBangunan"));
			Vector buildings = getIPembelian().findBangunanByPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			context.put("buildings", buildings);
			context.put("bangunan", bangunan);
			context.put("selectedTab", 1);
			vm = PATH+"tanah.jsp";
			
		}else if(command.equals("viewPemilik")){
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
			
			vm = PATH+"tanah2.jsp";
			myLog.info("viewPemilik ::"+vm);
			
		}else if(command.equals("viewpemilikonline")){
			getPermohonanInfo();
			getValues();
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			getPermohonanInfo();
			context.put("pp", p);
			context.put("mode", 0);
			context.put("mt", m);
			//context.put("selectedTab", 2);
			context.put("selectedTab", 1);
			myLog.info(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan());
//			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
//					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
//				context.put("selectedTab", 1);
//			}			
			vm = PATH+"tanahOnline.jsp";
			myLog.info("viewPemilikOnline ::"+vm);
			
		}else if(command.equals("tambahPemilik")){
			myLog.info("tambahPemilik ::"+vm);
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
			//context.put("selectedTab", 2);
			context.put("selectedTab", 1);
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
//			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
//					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
//				context.put("selectedTab", 1);
//			}
			vm = PATH+"tanahOnline.jsp";
			
		}else if(command.equals("doChangeDaerah2")){
			myLog.info("doChangeDaerah2 ::"+vm);
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
			//context.put("selectedTab", 2);
			context.put("selectedTab", 1);
//			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
//					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
//				context.put("selectedTab", 1);
//			}
			vm = PATH+"tanahOnline.jsp";
			
		}else if(command.equalsIgnoreCase("simpanPemilik")){
			myLog.info("simpanPemilik ::"+vm);
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
			//context.put("selectedTab", 2);
			context.put("selectedTab", 1);
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
//			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
//					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
//				context.put("selectedTab", 1);
//			}
			vm = PATH+"tanahOnline.jsp";
			
		}else if(command.equalsIgnoreCase("detailPemilik")){
			
			getValuesPemilik();
			tambahTanahDetail3();
			pemilik = getIPemilik().findPemilik(pemilik.getIdpihakberkepentingan());
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			context.put("socNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(pemilik.getIdNegeri()),"disabled"));
			context.put("socDaerah", HTML.SelectDaerahByNegeri(pemilik.getIdNegeri(), "socDaerah", Long.parseLong(pemilik.getIdDaerah()), "disabled"));
			context.put("selectJenisNoPB", HTML.SelectJenisNoPb("selectJenisNoPB", Long.parseLong(pemilik.getJenisPB()), "disabled"));
			context.put("buttonMode", 0);
			context.put("mode", 1);
			context.put("pemilik", pemilik);
			context.put("mt", m);
			context.put("pp", p);
			context.put("selectedTab", 2);
			context.put("inputstyleread", readonly);
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
				context.put("selectedTab", 1);
			}
			vm = PATH+"tanahOnline.jsp";
			myLog.info("detailPemilik ::"+vm);
			
		}else if(command.equalsIgnoreCase("detailpemilikonline")){			
			getValuesPemilik();
			tambahTanahDetail3();
			pemilik = getIPemilik().findPemilik(pemilik.getIdpihakberkepentingan());
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			context.put("socNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(pemilik.getIdNegeri()),""));
			context.put("socDaerah", HTML.SelectDaerahByNegeri(pemilik.getIdNegeri(), "socDaerah", Long.parseLong(pemilik.getIdDaerah()), ""));
			context.put("selectJenisNoPB", HTML.SelectJenisNoPb("selectJenisNoPB", Long.parseLong(pemilik.getJenisPB()), ""));
			context.put("buttonMode", 2);
			context.put("mode", 1);
			context.put("pemilik", pemilik);
			context.put("mt", m);
			context.put("pp", p);
			//context.put("selectedTab", 2);
			context.put("selectedTab", 1);
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
//			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
//					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
//				context.put("selectedTab", 1);
//			}
			vm = PATH+"tanahOnline.jsp";
			myLog.info("detailPemilik Online ::");
		
		}else if(command.equalsIgnoreCase("updatePemilik")){
			myLog.info("updatePemilik ::"+vm);
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
			//context.put("selectedTab", 2);
			context.put("selectedTab", 1);
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
//			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
//					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
//				context.put("selectedTab", 1);
//			}
			vm = PATH+"tanahOnline.jsp";
			
		}else if(command.equalsIgnoreCase("deletePemilik")){
			myLog.info("deletePemilik ::"+vm);
			getValuesPemilik();
			tambahTanahDetail3();
			getIPemilik().deletePemilik(pemilik.getIdpihakberkepentingan());
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(getParam("txtidPermohonan"));
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			context.put("mode", 0);
			context.put("pemilik", pemilik);
			context.put("mt", m);
			context.put("pp", p);
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
			//context.put("selectedTab", 2);
			context.put("selectedTab", 1);
//			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
//					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
//				context.put("selectedTab", 1);
//			}
			vm = PATH+"tanahOnline.jsp";
			
		}else if(command.equalsIgnoreCase("viewPenjual")){		
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
				context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP",null,"disabled"," onChange=\"doChangePenjualNegeri()\" "));
				context.put("selectDaerahP", HTML.SelectDaerah("selectDaerahP",null,"disabled"));
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
			this.context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			this.context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			this.context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));

			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
				context.put("selectedTab", 2);
			}
			context.put("pemohon", pemohon);
			vm = PATH+"tanah2.jsp";
			myLog.info("viewPenjual ::"+vm);
			
		}else if(command.equalsIgnoreCase("viewpenjualonline")){			
			getPermohonanInfo();
			getPenjualDetails();
			String penjualMode = "edit";
			String mode =" readonly";
			String enable =" disabled";
			pemohon = getIPembelian().findByPermohonan(String.valueOf(permohonan.getIdPermohonan()));
			Vector<HakmilikUrusan> m = getIPembelian().getHakmilikList(String.valueOf(permohonan.getIdPermohonan()));
			context.put("mtPenjual", m);
			if(pemohon == null){
				penjualMode = "new";
				mode ="";
				enable ="";
				context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP",null,enable," onChange=\"doChangePenjualNegeri()\" "));
				context.put("selectDaerahP", HTML.SelectDaerah("selectDaerahP",null,enable));
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
//			context.put("selectedTab", 3);
			context.put("selectedTab", 2);
//			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
//					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
//				context.put("selectedTab", 2);
//			}
			context.put("pemohon", pemohon);
			vm = PATH+"tanahOnline.jsp";
			myLog.info("viewPenjualOnline ::"+vm);
			
		}else if(command.equalsIgnoreCase("doChangePenjualNegeri")){
			myLog.info("doChangePenjualNegeri ::"+vm);
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

			vm = PATH+"tanahOnline.jsp";	
		
		}else if(command.equalsIgnoreCase("simpanPenjual")){
			myLog.info("simpanPenjual ::"+vm);
			getPermohonanInfo();
			getPenjualDetails();
			getdoChange();
			pemohon = getIPembelian().simpanPenjual(pemohon);
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP",Long.parseLong(pemohon.getIdNegeri()),"disabled"," onChange=\"doChangePenjualNegeri()\" "));
			context.put("selectDaerahP", HTML.SelectDaerah("selectDaerahP",Long.parseLong(pemohon.getIdDaerah()),"disabled"));

			context.put("pemiliks", p);
			context.put("mode", "readonly");
			context.put("penjualMode", "");
			context.put("enable", " disabled");
			context.put("pemohon", pemohon);
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
			vm = PATH+"tanahOnline.jsp";
		
		}else if(command.equalsIgnoreCase("kemaskiniPenjual")){
			myLog.info("kemaskiniPenjual ::"+vm);
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
//			context.put("selectedTab", 3);
			context.put("selectedTab", 2);
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
//			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
//					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
//				context.put("selectedTab", 2);
//			}
			vm = PATH+"tanahOnline.jsp";
		
		}else if(command.equalsIgnoreCase("updatePenjual")){
			myLog.info("updatePenjual ::"+vm);
			getPermohonanInfo();
			getPenjualDetails();
			pemohon = getIPembelian().updatePenjual(pemohon);
			Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
			context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP",Long.parseLong(pemohon.getIdNegeri()) ,"disabled", " onChange=\"doChangePenjualNegeri()\" "));
			context.put("selectDaerahP", HTML.SelectDaerahByNegeri(pemohon.getIdNegeri(), "selectDaerahP", Long.parseLong(pemohon.getIdDaerah()), "disabled"));
			context.put("pemiliks", p);
			context.put("mode", "readonly");
			context.put("penjualMode", "Xupdate");
			context.put("enable", " disabled");
			context.put("pemohon", pemohon);
//			context.put("selectedTab", 3);
			context.put("selectedTab", 2);
//			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
//					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
//				context.put("selectedTab", 2);
//			}
			vm = PATH+"tanahOnline.jsp";
		
		}else if(command.equalsIgnoreCase("hapuspenjual")){
				myLog.info("hapuspenjual ::"+vm);
				getPermohonanInfo();
				getPenjualDetails();
				getIPembelian().hapusPenjual(pemohon);
//				Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
//				context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP",Long.parseLong(pemohon.getIdNegeri()) ,"disabled", " onChange=\"doChangePenjualNegeri()\" "));
//				context.put("selectDaerahP", HTML.SelectDaerahByNegeri(pemohon.getIdNegeri(), "selectDaerahP", Long.parseLong(pemohon.getIdDaerah()), "disabled"));
//				context.put("pemiliks", p);
//				context.put("mode", "readonly");
//				context.put("penjualMode", "update");
//				context.put("enable", " disabled");
//				context.put("pemohon", pemohon);
//				context.put("selectedTab", 3);
//				if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
//						ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
//					context.put("selectedTab", 2);
//				}
				
				String penjualMode = "new";
				//String mode =" readonly";
				//String enable =" disabled";
				pemohon = getIPembelian().findByPermohonan(String.valueOf(permohonan.getIdPermohonan()));
				if(pemohon == null){
					myLog.info("pemohon == null ::"+vm);
					penjualMode = "new";
					//mode ="";
					//enable ="";
					context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP",null,""," onChange=\"doChangePenjualNegeri()\" "));
					context.put("selectDaerahP", HTML.SelectDaerah("selectDaerahP",null,""));
					context.put("pemohon", null);
					
				}else{
					myLog.info("pemohonon == else ::"+vm);
					context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP",Long.parseLong(pemohon.getIdNegeri()) ,"disabled", " onChange=\"doChangePenjualNegeri()\" "));
					context.put("selectDaerahP", HTML.SelectDaerahByNegeri(pemohon.getIdNegeri(), "selectDaerahP", Long.parseLong(pemohon.getIdDaerah()), "disabled"));
				
				}
				Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(getParam("txtidPermohonan"));
				context.put("pemiliks", p);
				//ontext.put("enable", enable);
				//context.put("mode", mode);
				context.put("penjualMode", penjualMode);
//				context.put("selectedTab", 3);
				context.put("selectedTab", 2);
//				if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
//						ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
//					context.put("selectedTab", 2);
//				}
				context.put("pemohon", pemohon);
				context.put("saiz_listTanah", getHakmilikList(idPermohonan));
				context.put("saiz_listPB", getHakmilikPB(idPermohonan));
				context.put("saiz_listPenjual", getListPenjual(idPermohonan));
				context.put("saiz_listLampiran", getListLampiran(idPermohonan));
				vm = PATH+"tanahOnline.jsp";
				
		}else if(command.equalsIgnoreCase("assignPenjual")){
			myLog.info("assignPenjual ::");
			getPermohonanInfo();
			getPenjualDetails();
			context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP"," onChange=\"doChangePenjualNegeri()\" "));
			context.put("selectDaerahP", HTML.SelectDaerah("selectDaerahP"));
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
				pemohon.setEmel(pemilik.getEmel());
				pemohon.setNoPemohon(pemilik.getNoRujukan());
				pemohon.setIdNegeri(pemilik.getIdNegeri());
				pemohon.setIdDaerah(pemilik.getIdDaerah());
				//context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP",Long.parseLong(pemilik.getIdNegeri()) ,"disabled", " onChange=\"doChangePenjualNegeri()\" "));
				//context.put("selectDaerahP", HTML.SelectDaerahByNegeri(pemohon.getIdNegeri(), "selectDaerahP", Long.parseLong(pemohon.getIdDaerah()), "disabled"));
				context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP",Long.parseLong(pemilik.getIdNegeri()) ,"", " onChange=\"doChangePenjualNegeri()\" "));
				context.put("selectDaerahP", HTML.SelectDaerahByNegeri(pemohon.getIdNegeri(), "selectDaerahP", Long.parseLong(pemohon.getIdDaerah()), ""));
				
			}
			String penjualMode ="";
			String mode = "";
			String enable ="";
		
			if( getIPembelian().findByPermohonan(String.valueOf(permohonan.getIdPermohonan()))== null){
				penjualMode = "new";
				mode ="";
				enable ="";
				myLog.info("new");
			}else{
				mode=" readonly";
				penjualMode = "";
				enable =" disabled";
			
			}
			context.put("pemohon", pemohon);
			context.put("mode", mode);
			context.put("penjualMode", penjualMode);
			context.put("pemohon", pemohon);
			context.put("enable", enable);
//			context.put("selectedTab", 3);
			context.put("selectedTab", 2);
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
//			if(ID_URUSANTANAH.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan())) ||
//					ID_URUSANTANAH1.equals(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()))	){
//				context.put("selectedTab", 2);
//			}
			//vm = PATH+"tanah.jsp";
			vm = PATH+"tanahOnline.jsp";
		
		}
		else if(command.equalsIgnoreCase("perakuanPembelian")){
			myLog.info("perakuanPembelian ::"+vm);
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
			}
			else{
				
				getPermohonanInfo();
				getSemakanPerakuanPembelian();
				vm = PATH+"perakuanPembelian.jsp";
				context.put("page","3");
				context.put("semakMode", "");
				context.put("mode", " disabled");
			}
		}
		else if(command.equalsIgnoreCase("kemaskiniPerakuan")){
			myLog.info("kemaskiniPerakuan ::"+vm);
			getSemakanPerakuanPembelian();
			vm = PATH+"perakuanPembelian.jsp";
			context.put("page","3");
			context.put("semakMode", "update");
			context.put("mode", " ");
		}
		else if(command.equalsIgnoreCase("simpanPerakuan")){
			myLog.info("simpanPerakuan ::"+vm);
			getPermohonanInfo();
			simpanSenaraiSemakAkuanPembelian(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			getSemakanPerakuanPembelian();
			vm = PATH+"perakuanPembelian.jsp";
			context.put("page","3");
			context.put("semakMode", "");
			context.put("mode", " disabled");
		
		}else if(command.equalsIgnoreCase("rundingan")){
			getPermohonanInfo();
			myLog.info("rundingan ::id_permohonan="+htpPermohonan.getPermohonan().getIdPermohonan());	
			//Vector dokumens = getIOnline().getDataDokumen(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			Vector dokumens = getIOnline().getLampiranByPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			context.put("senaraidokumen", dokumens);
			//Vector lampirans = getIOnline().getLampiranByPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			//context.put("senarailapiran", lampirans);
			getSemakanPerakuanPembelian();

			context.put("selectedTab", 3);
			vm = PATH+"tanahOnline.jsp";
			
		}else if(command.equalsIgnoreCase("viewLampiran")){
			getPermohonanInfo();
			myLog.info("viewLampiran ::id_permohonan="+htpPermohonan.getPermohonan().getIdPermohonan());	
			//Vector dokumens = getIOnline().getDataDokumen(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			Vector dokumens = getIOnline().getLampiranByPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			context.put("senaraidokumen", dokumens);
			//Vector lampirans = getIOnline().getLampiranByPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			//context.put("senarailapiran", lampirans);
			getSemakanPerakuanPembelian();

			context.put("selectedTab", 4);
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
			vm = PATH+"tanahOnline.jsp";
			
		}else if(command.equalsIgnoreCase("tambahlampiran")){
			getPermohonanInfo();
			myLog.info("tambahlampiran ::"+htpPermohonan.getPermohonan().getIdPermohonan());
			myLog.info("tambahlampiran ::id_fail="+htpPermohonan.getPermohonan().getPfdFail().getIdFail());
			String mode="";
			context.put("mode", mode);
			context.put("pageMode", "edit");
//			Vector buildings = getIOnline().getDataDokumen(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
//			context.put("senaraidokumen", buildings);
//			context.put("selectedTab", 3);
			this.context.put("num_files","");
//			RO_General = "readonly=\"readonly\"";
			if ("changeLampiran".equals(actionI)) {
//				RO_General = "";
//				String x = getParam("X");
//				actionI = x;
				int j = getParamAsInteger("jumlahlampiran");
				this.context.put("num_files", j);				
			}
			vm = PATH+"maklumatDokumen.jsp";
			myLog.info("tambahlampiran ::"+vm);
			
		}else if(command.equalsIgnoreCase("simpanlampiran")){
			getPermohonanInfo();
			String mode="";
			context.put("mode", mode);
			context.put("pageMode", "edit");
			this.context.put("num_files","");
//			RO_General = "readonly=\"readonly\"";
			if ("changeLampiran".equals(actionI)) {
//				RO_General = "";
//				String x = getParam("X");
//				actionI = x;
				int j = getParamAsInteger("jumlahlampiran");
				this.context.put("num_files", j);				
			}
			myLog.info("simpanlampiran ::"+htpPermohonan.getPermohonan().getIdPermohonan());
			myLog.info("simpanlampiran:id_fail="+htpPermohonan.getPermohonan().getPfdFail().getIdFail());
			uploadFiles(ses,String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdFail()));
			context.put("selectedTab", 4);
			
			vm = PATH+"maklumatDokumen.jsp";

		}else if(command.equalsIgnoreCase("lampiran")){
			vm = PATH+"maklumatDokumen.jsp";
			getPermohonanInfo();
			myLog.info("lampiran ::"+htpPermohonan.getPermohonan().getIdPermohonan());
			String mode =" readonly";
			String enable =" disabled";
			String nextAction = getParam("nextAction");	
			String idGambar = getParam("idGambar");
			myLog.info("lampiran ::"+nextAction);
			 if("viewDetailImej".equals(nextAction)){
					this.context.put("readOnly", "readOnly");
				    this.context.put("disabled", "disabled");
				    this.context.put("mode","view");					    
				    viewModeLampiran(ses,idGambar);
				    
			 }else if("hapus".equals(nextAction)){
				 	myLog.info("hapus ::"+nextAction);
//					this.context.put("readOnly", "readOnly");
//				    this.context.put("disabled", "disabled");
//				    this.context.put("mode","view");					    
				    //viewModeLampiran(ses,idGambar);
					String idDokumen = getParam("iddokumen");
				    getIOnline().hapusLampiran(idDokumen,idGambar);
					Vector dokumens = getIOnline().getLampiranByPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
					context.put("senaraidokumen", dokumens);
					context.put("selectedTab", 4);
					vm = PATH+"tanahOnline.jsp";
					
			 }else if("kemaskiniDetail".equals(nextAction)){
				 this.context.put("readOnly", "");
				 this.context.put("disabled", "");
				 this.context.put("mode","kemaskini");					    
				 viewModeLampiran(ses,idGambar);
			 
			 }else if("kemaskinisimpan".equals(nextAction)){
				 String idDokumen = getParam("iddokumen");
				 Hashtable data = new Hashtable();				 
				 data.put("idDokumen",idDokumen);
				 data.put("tajuk_Dokumen",getParam("txtRingkas"));
				 data.put("namaPengirim", getParam("txtPerihalImej"));
				 data.put("idKemaskini",String.valueOf(userID));
				 getIOnline().kemaskiniDokumen(data);
				 this.context.put("readOnly", "readOnly");
				 this.context.put("disabled", "disabled");
				 this.context.put("mode","view");					    
				 viewModeLampiran(ses,idGambar);	
				 
			 }
			 	context.put("saiz_listTanah", getHakmilikList(idPermohonan));
				context.put("saiz_listPB", getHakmilikPB(idPermohonan));
				context.put("saiz_listPenjual", getListPenjual(idPermohonan));
				context.put("saiz_listLampiran", getListLampiran(idPermohonan));
		}else if(command.equalsIgnoreCase("viewsemakan")){
			getPermohonanInfo();
			myLog.info("viewsemakan ::id_permohonan="+htpPermohonan.getPermohonan().getIdPermohonan());	
			getSemakanPerakuanPembelian();

			context.put("selectedTab", 4);
			vm = PATH+"tanahOnline.jsp";

		}else if(command.equalsIgnoreCase("pengesahan")){
			getPermohonanInfo();
			myLog.info("viewsemakan ::id_permohonan="+htpPermohonan.getPermohonan().getIdPermohonan());	
			//getSemakanPerakuanPembelian();
			
			Vector listDetailKJP = null;
			getIdNegeriKJPByUserId(userID);
			listDetailKJP = getIdNegeriKJPByUserId(userID);
			Hashtable hashList = (Hashtable) listDetailKJP.get(0);
			idNegeriPemohon = hashList.get("idNegeri").toString();
			namaPemohon = (String) hashList.get("namaPemohon");
			userID = (String) hashList.get("userID");
			
			this.context.put("ListdetailKJP", listDetailKJP);
			this.context.put("namaPemohon", namaPemohon);
			this.context.put("userID", userID);
			myLog.info("namaPemohon:: " + namaPemohon);
			
			String semakMode="";
			String statusSemasa="-4";
			if(getIOnline().isHantar(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),htpPermohonan.getPermohonan().getIdPermohonan()
					,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"-1")){
				semakMode = "xupdate";			
			}else{

				semakMode = "update";
				if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),htpPermohonan.getPermohonan().getIdPermohonan()
						,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"-4")){
					statusSemasa = "-4";	
 

				
				}else if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),htpPermohonan.getPermohonan().getIdPermohonan()
						,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"-3")){
					statusSemasa = "-3";


					
				}else if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),htpPermohonan.getPermohonan().getIdPermohonan()
							,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"-2")){
					statusSemasa = "-2";	

					
				}
			}
			//myLog.info("semakMode="+semakMode);
			context.put("semakMode", semakMode);
			myLog.info("statusSemasa:"+statusSemasa);
			context.put("statussemasa", statusSemasa);
			context.put("buttonSend", "disabled");

			context.put("selectedTab", 4);
			vm = PATH+"perakuanPembelianOnline.jsp";
			
		}else if(command.equalsIgnoreCase("simpanpengesahan")){
			getPermohonanInfo();
			myLog.info("simpanpengesahan ::id_permohonan="+htpPermohonan.getPermohonan().getIdPermohonan());	
			String semakMode="";
			/*
			 * -4 untuk status - Pendaftaran
			 * -3 untuk status - Tindakan Pegawai 
			 * -2 untuk status - Tindakan Pengarah
			 * -1 untuk status - Permohonan Online (Pengesahan)
			 * 1  untuk status - Penerimaan Permohonan
			*/
			String langkah = "-3";
			EmailConfig ec = new EmailConfig();

			//myLog.info("from="+email.FROM);
			String emelSubjek = ec.tajukSemakan+"Perakuan Pembelian";
			String kandungan = "";
			if(idJawatan.equals("20")||idJawatan.equals("24")){
				myLog.info("BACA SINIIIII============");
				
				langkah = "-3";
				
				kandungan = getEmelSemak().setKandungan(htpPermohonan.getPermohonan().getPfdFail().getTajukFail(), String.valueOf(hUser.get("nama")));
    			
				if(!getEmelSemak().checkEmail(userID).equals(""))
					getIHTP().getErrorHTML("[ONLINE-HTP PEMBELIAN] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");

				ec.sendByRoleKJP(getEmelSemak().checkEmail(userID)
						, "9"
						, String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdKementerian())
						, emelSubjek, kandungan);

			}else if (idJawatan.equals("9")){
				langkah = "-2";				
				
				kandungan = getEmelSemak().setKandungan(htpPermohonan.getPermohonan().getPfdFail().getTajukFail(), String.valueOf(hUser.get("nama")));
    			
				if(!getEmelSemak().checkEmail(userID).equals(""))
					getIHTP().getErrorHTML("[ONLINE-HTP PEMBELIAN] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");

				ec.sendByRoleKJP(getEmelSemak().checkEmail(userID)
						, "4"
						, String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdKementerian())
						, emelSubjek, kandungan);
							
			}else if (idJawatan.equals("4")){
				langkah = "-1";
				emelSubjek = ec.tajukHantarPermohonan + "Perakuan Pembelian";
				String userEmelInternal = "shiqaosman@gmail.com";
						
				kandungan = getEmelSemak().setKandungan(htpPermohonan.getPermohonan().getPfdFail().getTajukFail()
							,htpPermohonan.getPermohonan().getPfdFail().getNamaKementerian()
							,htpPermohonan.getPermohonan().getNoPermohonan());
    			
				if(!getEmelSemak().checkEmail(userID).equals(""))
					getIHTP().getErrorHTML("[ONLINE-HTP PEMBELIAN] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");
				//   (HTP)HQPenggunaPembelianPerletakhakan,   (HTP)HQPenggunaPembelian, (HTP)HQPengguna

				//tutup untuk pengujian
				//ec.hantarPermohonan(getEmelSemak().checkEmail(userID), "(HTP)HQPenggunaPembelianPerletakhakan", emelSubjek, kandungan);
				ec.hantarPermohonan(userEmelInternal, userEmelInternal, emelSubjek, kandungan);
								
			}
			Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
			rsusf.setIdPermohonan(htpPermohonan.getPermohonan().getIdPermohonan());
			rsusf.setIdFail(htpPermohonan.getPermohonan().getPfdFail().getIdFail());
			rsusf.setIdSuburusanstatusfail(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan());
			rsusf.setUrl("-");
			simpanPengesahan(rsusf,langkah);
			HtpPermohonan htpPermohonanNew = new HtpPermohonan();
			Permohonan permohonanNew = new Permohonan();
			permohonanNew.setIdMasuk(Long.parseLong(userID));
			htpPermohonanNew.setPermohonan(permohonanNew);			
			getIHTPPermohonan().kemaskiniPermohonanTarikh(htpPermohonanNew
					,String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan())
					,String.valueOf(htpPermohonan.getIdHtpPermohonan()));

			if(getIOnline().isHantar(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),htpPermohonan.getPermohonan().getIdPermohonan()
					,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),langkah)){
				semakMode = "xupdate";			
			}else{
				semakMode = "update";
			}
			myLog.info("selectedTab=======");
			context.put("semakMode", semakMode);
			context.put("selectedTab", 4);
			vm = PATH+"perakuanPembelianOnline.jsp";	
		
		}else if(command.equalsIgnoreCase("simpanpengesahan2")){
			getPermohonanInfo();
			myLog.info("simpanpengesahan ::id_permohonan="+htpPermohonan.getPermohonan().getIdPermohonan());	
			String semakMode="";
			String langkah = "11";
			/*
			 * -4 untuk status - Pendaftaran
			 * -3 untuk status - Tindakan Pegawai 
			 * -2 untuk status - Tindakan Pengarah
			 * -1 untuk status - Permohonan Online (Pengesahan)
			 * 1  untuk status - Penerimaan Permohonan
			 * 11 untuk status - Tindakan Penyedia
			*/
			
			EmailConfig ec = new EmailConfig();

			//myLog.info("from="+email.FROM);
			String emelSubjek = ec.tajukSemakan+"Perakuan Pembelian";
			String kandungan = "";
			if(idJawatan.equals("4")){
				
				langkah = "11";
				
				kandungan = getEmelSemak().setKandungan(htpPermohonan.getPermohonan().getPfdFail().getTajukFail(), String.valueOf(hUser.get("nama")));
    			
				if(!getEmelSemak().checkEmail(userID).equals(""))
					getIHTP().getErrorHTML("[ONLINE-HTP PEMBELIAN] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");

				ec.sendByRoleKJP(getEmelSemak().checkEmail(userID)
						, "9"
						, String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdKementerian())
						, emelSubjek, kandungan);

			}
			Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
			rsusf.setIdPermohonan(htpPermohonan.getPermohonan().getIdPermohonan());
			rsusf.setIdFail(htpPermohonan.getPermohonan().getPfdFail().getIdFail());
			rsusf.setIdSuburusanstatusfail(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan());
			rsusf.setUrl("-");
			simpanPengesahan(rsusf,langkah);
			HtpPermohonan htpPermohonanNew = new HtpPermohonan();
			Permohonan permohonanNew = new Permohonan();
			permohonanNew.setIdMasuk(Long.parseLong(userID));
			htpPermohonanNew.setPermohonan(permohonanNew);			
			getIHTPPermohonan().kemaskiniPermohonanTarikh(htpPermohonanNew
					,String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan())
					,String.valueOf(htpPermohonan.getIdHtpPermohonan()));

			if(getIOnline().isHantar(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),htpPermohonan.getPermohonan().getIdPermohonan()
					,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),langkah)){
				semakMode = "xupdate";			
			}else{
				semakMode = "update";
			}
			myLog.info("selectedTab=======");
			context.put("semakMode", semakMode);
			context.put("selectedTab", 4);
			vm = PATH+"perakuanPembelianOnline.jsp";	
		
		}else if(command.equals("searchFail")){
			myLog.info("searchFail ::"+vm);	
			String noFailOnline = getParam("noFailOnline");
			String noFail = getParam("noFail");
			String namaFail = getParam("namaFail");
			String idStatuss = "";
			idNegeri = getParam("socNegeri") == "" ? "0" : getParam("socNegeri");
			idAgensi = getParam("socAgensi") == "" ? "0" : getParam("socAgensi");
			idSuburusan = getParam("socSubUrusan") == "" ? "0" : getParam("socSubUrusan");
			idStatus = getParam("socStatus") == "" ? "0" : getParam("socStatus");
			myLog.info("idStatus="+idStatus);
			//Vector<?> v = getIPembelian().findFailKJP(carian, noFail, idNegeri,id_kementerian);
			Vector<?> v = getIPembelianOnline().findFailKJP(namaFail, noFail, noFailOnline, idNegeri,id_kementerian,idAgensi,idSuburusan,idStatus);
			context.put("lists", v);
			
			socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri));
			socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian, Utils.parseLong(idAgensi), ""," style=\"width:400\"");
			socSubUrusan = UtilHTML.SelectSuburusanByIdUrusan("2","23,25", "socSubUrusan",Long.parseLong(idSuburusan),"");
			socStatus = getIUtil().SelectStatusPermohonan("socStatus",Long.parseLong(idStatus),"",IDSUBURUSANS);

			this.context.put("carianNoFail", noFail);
			this.context.put("carian", namaFail);
		   	this.context.put("socAgensi", socAgensi);
		   	this.context.put("socKementerian", socKementerian);
			this.context.put("socNegeri", socNegeri);
			this.context.put("socsuburusan", socSubUrusan);
			this.context.put("socstatus", socStatus);
			
			setupPage(ses,action, v);
			vm = PATH+"index_.jsp";
			
		}else if(command.equals("kosong")) {
			String noFailOnline = "";
			String noFail = "";
			String namaFail = "";
			
			socNegeri = HTML.SelectNegeri("socNegeri","");
			socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian,Utils.parseLong("")," style=\"width:400\"");
			socSubUrusan = UtilHTML.SelectSuburusanByIdUrusan("2","23,25", "socSubUrusan", null,"");
			socStatus = getIUtil().SelectStatusPermohonan("socStatus","",null);
			this.context.put("socNegeri", socNegeri);
			this.context.put("socAgensi", socAgensi);
			this.context.put("socSubUrusan", socSubUrusan);
			this.context.put("socStatus", socStatus);
			this.context.put("carianNoFail", "");
			this.context.put("carian", "");
			
			idNegeri = getParam("socNegeri") == "" ? "0" : getParam("socNegeri");
			idAgensi = getParam("socAgensi") == "" ? "0" : getParam("socAgensi");
			idStatus = getParam("socStatus") == "" ? "0" : getParam("socStatus");
			idSuburusan = getParam("socSubUrusan") == "" ? "0" : getParam("socSubUrusan");
			
			Vector<?> v = getIPembelianOnline().findFailKJP(namaFail, noFail, noFailOnline, "",id_kementerian,"","","");
			//this.context.put("lists", v);
			setupPage(ses,action, v);
			
			vm = PATH+"index_.jsp";
		}else{
			String noFailOnline = getParam("noFailOnline");
			String noFail = getParam("noFail");
			String carian = getParam("namaFail");
			idNegeri = getParam("socNegeri") == "" ? "0" : getParam("socNegeri");
			idAgensi = getParam("socAgensi") == "" ? "0" : getParam("socAgensi");
			idStatus = getParam("socStatus") == "" ? "0" : getParam("socStatus");
			
			//Vector<?> v = getIPembelian().findFailKJP(carian, noFail, idNegeri,id_kementerian);
			Vector<?> v = getIPembelianOnline().findFailKJP(carian, noFail, noFailOnline, idNegeri,id_kementerian,idAgensi,idSuburusan,idStatus);
			
			context.put("lists", v);
			socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri));
			//socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian), "disabled class=disabled","onChange=\"doChangeKementerianX()\" style=\"width:400\"");
			socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian, Utils.parseLong(idAgensi), ""," style=\"width:400\"");
			socSubUrusan = UtilHTML.SelectSuburusanByIdUrusan("2","23,25", "socSubUrusan", null,"");
			socStatus = getIUtil().SelectStatusPermohonan("socStatus","",IDSUBURUSANS);
		   	this.context.put("socAgensi", socAgensi);
		   	this.context.put("socKementerian", socKementerian);
			this.context.put("socNegeri", socNegeri);
			this.context.put("socsuburusan", socSubUrusan);
			this.context.put("socstatus", socStatus);
			context.put("saiz_listTanah", getHakmilikList(idPermohonan));
			context.put("saiz_listPB", getHakmilikPB(idPermohonan));
			context.put("saiz_listPenjual", getListPenjual(idPermohonan));
			context.put("saiz_listLampiran", getListLampiran(idPermohonan));
			setupPage(ses,action, v);
			
			vm = PATH+"index_.jsp";
			//myLog.info("findFail ::"+vm);
			
		}	
		System.out.println("::::: vm ::::: "+vm);
		
		}catch(Exception e){
			getIHTP().getErrorHTML(e.toString());
	
		}		
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
	
	private void getKementerianDetailOnline(String idKementerian,String mode)throws Exception{
		String disabled ="disabled";
		if(mode=="edit"){
			disabled ="";
		}
		
		String socKementerian = HTML.SelectKementerian("socKementerian", (idKementerian == "") ? null : Long.parseLong(idKementerian),"disabled class=disabled", " onChange=\"doChangeKementerian()\"  style=\"width:400\"");
	    String socAgensi = HTML.SelectAgensiByKementerian("socAgensi",idKementerian,Long.parseLong("1")," style=\"width:400\"");
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
		permohonan.setIdMasuk(Long.valueOf(userID));
		htpPermohonan.setPermohonan(permohonan);

		context.put("permohonan", htpPermohonan);
		
	}
	
	public Vector getIdNegeriKJPByUserId(String userId) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable h;
		Vector listDetailKJP = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.USER_ID, A.USER_NAME, C.ID_NEGERI, B.ID_KEMENTERIAN, B.ID_AGENSI FROM USERS A, USERS_KEMENTERIAN B, TBLRUJAGENSI C, TBLRUJKEMENTERIAN D "
					+ " WHERE A.USER_ID = B.USER_ID AND B.ID_AGENSI = C.ID_AGENSI AND B.ID_KEMENTERIAN = D.ID_KEMENTERIAN AND A.USER_ID = '"
					+ userId + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				h = new Hashtable();
				h.put("userId", rs.getString("USER_ID").toString());
				h.put("idNegeri", rs.getString("ID_NEGERI").toString());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN").toString());
				h.put("idAgensi", rs.getString("ID_AGENSI").toString());
				h.put("namaPemohon", rs.getString("USER_NAME").toString());
				listDetailKJP.addElement(h);

				return listDetailKJP;
			} else {
				return listDetailKJP;
			}

		} finally {
			if (db != null)
				db.close();
		}
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
		String emel = getParam("txtEmel");

		urusan = new HakmilikUrusan();
		permohonan = new Permohonan();
		
		//urusan.setDdownHakmilik(ddownHakmilik);
		urusan.setIdhakmilikurusan(idHakmilikUrusan);
		urusan.setIdNegeri(idNegeri);
		urusan.setIdDaerah(idDaerah);
		urusan.setIdMukim(idMukim);
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
		String emel = getParam("txtEmel");
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
		pemilik.setEmel(emel);

		context.put("pemilik", pemilik);
		
	}

	private void viewDetail2() throws  Exception{	
		String socNegeri = HTML.SelectNegeri("socNegeri",htpPermohonan.getPermohonan().getPfdFail().getIdNegeri(),readonly);
		String socKementerian = HTML.SelectKementerian("socKementerian", htpPermohonan.getPermohonan().getPfdFail().getIdKementerian(),readonly+" style=\"width:400\" ");
		String socAgensi = HTML.SelectAgensiByKementerian("socAgensi",String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdKementerian()),htpPermohonan.getIdAgensi(),readonly+" style=\"width:400\" ");
	    context.put("selectKementerian",socKementerian);
		context.put("selectAgensi",socAgensi);
		context.put("socNegeri",socNegeri);
		context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan("2", "socSuburusan",htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),readonly));
		context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", htpPermohonan.getIdJenisTanah(), readonly));
		context.put("socTarafKeselamatan", HTML.SelectTarafKeselamatan("socTarafKeselamatan", htpPermohonan.getPermohonan().getPfdFail().getIdTarafKeselamatan(), readonly) );
	
	}

	private void tambahTanahDetail() throws  Exception{
		String idPermohonan = getParam("txtidPermohonan")==""?"0":getParam("txtidPermohonan");
		String idHtpPermohonan = getParam("txtidHtpPermohonan")==""?"0":getParam("txtidHtpPermohonan");
		String socNegeri = getParam("socNegeri")==""?"0":getParam("socNegeri");
		String socDaerah = getParam("socDaerah") ==""?"0":getParam("socDaerah");
		
		String socLot = getParam("socLot") ==""?"0":getParam("socLot");
		htpPermohonan = getIPembelian().findPermohonan(idPermohonan, idHtpPermohonan);
		context.put("socNegeri",HTML.SelectNegeri("socNegeri",htpPermohonan.getPermohonan().getPfdFail().getIdNegeri(),"disabled"));
		context.put("socDaerah", HTML.SelectDaerahByNegeri(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdNegeri()), "socDaerah",Long.parseLong(socDaerah),null," onChange=\"doChangeDaerah()\" "));
		context.put("socMukim",HTML.SelectMukimByDaerah(socDaerah, "socMukim"));
		context.put("socJenisHakmilik" , HTML.SelectJenisHakmilik("socJenisHakmilik"));
		context.put("socLot", HTML.SelectLot("socLot", Long.parseLong(socLot), ""));
		context.put("socRizab", HTML.SelectRizab("socRizab"));
		context.put("socLuas", HTML.SelectLuas("socLuas"));
		context.put("socKategori", HTML.SelectKategori("socKategori"));

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
		context.put("socDaerah", HTML.SelectDaerahByNegeri(urusan.getIdNegeri(), "socDaerah",Long.parseLong(urusan.getIdDaerah()),"disabled"," onChange=\"doChangeDaerah()\" "));
		context.put("socMukim",HTML.SelectMukimByDaerah(urusan.getIdDaerah(), "socMukim", Long.parseLong(urusan.getIdMukim()), "disabled"));
		context.put("socJenisHakmilik" , HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(urusan.getIdHakmilik()), "disabled"));
		context.put("socLot", HTML.SelectLot("socLot", Long.parseLong(urusan.getIdLot()), "disabled"));
		context.put("socRizab", HTML.SelectRizab("socRizab", Long.parseLong(urusan.getIdJenisRizab()),"disabled"));
		context.put("socLuas", HTML.SelectLuas("socLuas", Long.parseLong(urusan.getIdLuas()), "disabled"));
		context.put("socKategori", HTML.SelectKategori("socKategori", Long.parseLong(urusan.getIdKategoriTanah()), "disabled"));
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
	
	public int getHakmilikList(String idPermohonan) throws Exception {
		idPermohonan = getParam("txtidPermohonan")==""?"0":getParam("txtidPermohonan");
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql += " SELECT COUNT(*) AS TOTAL_TANAH ";
			sql += " FROM TBLHTPHAKMILIKURUSAN A, TBLRUJLOT B,TBLRUJLUAS C,TBLRUJJENISHAKMILIK D ";

			sql += " WHERE A.ID_PERMOHONAN = '" + idPermohonan + "' ";
			sql += " AND A.ID_LOT = B.ID_LOT(+) ";
			sql += " AND A.ID_LUAS = C.ID_LUAS ";
			sql += " AND A.ID_JENISHAKMILIK = D.ID_JENISHAKMILIK ";

			myLog.info(" getHakmilikList : " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int total_tanah = 0;
			while (rs.next()) {
				total_tanah = rs.getString("total_tanah") == null ? 0 : rs.getInt("total_tanah");

			}
			return total_tanah;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}// close count hakmilik tanah

	public int getHakmilikPB(String idPermohonan) throws Exception {
		idPermohonan = getParam("txtidPermohonan")==""?"0":getParam("txtidPermohonan");
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql += " SELECT COUNT(*) AS TOTALPB ";
			sql += " FROM TBLHTPPIHAKBERKEPENTINGAN A, TBLHTPHAKMILIKURUSAN B, TBLRUJNEGERI C, TBLRUJDAERAH D ";

			sql += " WHERE B.ID_PERMOHONAN = '" + idPermohonan + "' ";
			sql += " AND A.ID_HAKMILIKURUSAN = B.ID_HAKMILIKURUSAN ";
			sql += " AND A.ID_DAERAH = D.ID_DAERAH(+) ";
			sql += " AND A.ID_NEGERI = C.ID_NEGERI ";

			myLog.info(" getHakmiliKPB : " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int totalPB = 0;
			while (rs.next()) {
				totalPB = rs.getString("totalPB") == null ? 0 : rs.getInt("totalPB");

			}
			return totalPB;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}// close count pihak berkepentingan
	
	public int getListPenjual(String idPermohonan) throws Exception {
		idPermohonan = getParam("txtidPermohonan")==""?"0":getParam("txtidPermohonan");
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql += " SELECT COUNT(*) AS TOTALPENJUAL ";
			sql += " FROM TBLHTPPEMOHON ";

			sql += " WHERE ID_PERMOHONAN = '" + idPermohonan + "' ";

			myLog.info(" getListPenjual : " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int totalPenjual = 0;
			while (rs.next()) {
				totalPenjual = rs.getString("totalPenjual") == null ? 0 : rs.getInt("totalPenjual");

			}
			return totalPenjual;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}// close count penjual
	
	public int getListLampiran(String idPermohonan) throws Exception {
		idPermohonan = getParam("txtidPermohonan")==""?"0":getParam("txtidPermohonan");
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql += " SELECT COUNT(*) AS TOTALLAMPIRAN ";
			sql += " FROM TBLPFDRUJLAMPIRAN A, TBLPFDDOKUMEN FDD, TBLPERMOHONAN P ";

			sql += " WHERE P.ID_PERMOHONAN = '" + idPermohonan + "' "
					+ " AND A.ID_DOKUMEN = FDD.ID_DOKUMEN "
					+ " AND P.ID_FAIL = FDD.ID_FAIL "
					+ " ORDER BY A.ID_LAMPIRAN ASC ";

			myLog.info(" getListLampiran : " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int totalLampiran = 0;
			while (rs.next()) {
				totalLampiran = rs.getString("totalLampiran") == null ? 0 : rs.getInt("totalLampiran");

			}
			return totalLampiran;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}// close count Lampiran

	private IPembelian getIPembelian() {
		if (iPembelian == null) {
			iPembelian = new PembelianBean();
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
		String emel = getParam("txtEmel");
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
		pemohon.setEmel(emel);
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

	private IPenggunaKementerian getIPengguna(){
		if(iPengguna==null){
			iPengguna = new PenggunaKementerianBean();
		}
		return iPengguna;
		
	}

	//UPLOAD FILE
	private void uploadFiles(HttpSession session,String idFail) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	    Enumeration allparam = request.getParameterNames();
		String name="";String value="";
		for (; allparam.hasMoreElements(); ) {
	        // Get the name of the request parameter
	        name = (String)allparam.nextElement();
	        // Get the value of the request parameter
	        value = request.getParameter(name);
	        //System.out.println(name +"="+value);
	        myLog.info(name +"="+value);
		}
	    List items = upload.parseRequest(request);
	    Iterator itr = items.iterator();
	    while (itr.hasNext()) {
	    	FileItem item = (FileItem)itr.next();
		    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	String idDokumen = simpanDokumen(session,idFail);			
		    	saveData(idDokumen,item);
		    }
	    }
	 }

	 private void saveData(String idDokumen,FileItem item) throws Exception {
		 Db db = null;
		 try {
			 db = new Db();			 
			 long id_Lampiran = DB.getNextID("TBLPFDRUJLAMPIRAN_SEQ");
			 Connection con = db.getConnection();
			 con.setAutoCommit(false);
			 PreparedStatement ps = con.prepareStatement("insert into TBLPFDRUJLAMPIRAN " +
	        		"(id_Lampiran,id_Dokumen,nama_Fail,jenis_Mime,content,id_masuk,tarikh_masuk,id_kemaskini,tarikh_kemaskini) " +
	        			"values(?,?,?,?,?,"+userID+",sysdate,"+userID+",sysdate)");
			 ps.setLong(1, id_Lampiran);
			 ps.setString(2, idDokumen);
			 ps.setString(3,item.getName());
			 ps.setString(4,item.getContentType());
			 ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
			 ps.executeUpdate();
			 con.commit();
			 
		 } finally {
			 if (db != null) db.close();
			      
		 }
		 
	 }
	 
	 private String simpanDokumen(HttpSession session,String idFail) throws Exception {			
		 String user_id = (String)session.getAttribute("_ekptg_user_id");
//		 String idLogDokumen = getParam("idLogDokumen");
//		 String no_Rujukan_Dokumen = getParam("no_Rujukan_Dokumen");
//		 String no_Rujukan_DokumenEncode = java.net.URLDecoder.decode(no_Rujukan_Dokumen);		
//		 String bil_Minit_Dokumen = getParam("bil_Minit_Dokumen");
//		 String new_no_Rujukan_Dokumen = no_Rujukan_DokumenEncode+"("+bil_Minit_Dokumen+")";
//		 String minit = getParam("c1");
//		 String laporan = getParam("c2");
//		 String cd = getParam("c3");
//		 String id_PA = getParam("socPA");
		 Hashtable h = new Hashtable();  
		 h.put("flag_Dokumen","1");
//		 h.put("bil_Minit_Dokumen", getParam("bil_Minit_Dokumen"));
//		 h.put("id_Jenisdokumen", getIOnline().getIdDokumenByKod(""));
//		 h.put("no_Rujukan_Dokumen", new_no_Rujukan_Dokumen);
//		 h.put("tarikh_Dokumen_Diterima", getParam("tarikh_Dokumen_Diterima"));
//		 h.put("tarikh_Dokumen", getParam("tarikh_Dokumen"));
//		 h.put("no_Rujukan_Lain", getParam("no_Rujukan_Lain"));
		 
		 h.put("tajuk_Dokumen",getParam("txtRingkas"));
		 h.put("namaPengirim", getParam("txtPerihalImej"));
		 
		 //h.put("id_nama_penerima",getParam("socPegawai") );
		 //h.put("id_PA", id_PA);
		 //h.put("idLogDokumen", idLogDokumen);
		 h.put("id_Fail",idFail);
		 h.put("id_Masuk",user_id);
//		 if ("true".equalsIgnoreCase(minit)){
//		 		h.put("idMinit", "1");
//			}else{
//				h.put("idMinit", "0");
//			}
//			if ("true".equalsIgnoreCase(laporan)){
//				h.put("idLaporan", "1");
//			}else{
//				h.put("idLaporan", "0");
//			}
//			if ("true".equalsIgnoreCase(cd)){
//				h.put("idCD", "1");
//			}else{
//				h.put("idCD", "0");
//			}
		return getIOnline().addMasuk(h);
			    
	 }
	 
	private IOnline getIOnline(){
		if(iOnline==null){
			iOnline = new OnlineBean();
		}
		return iOnline;
			
	}
	
	private void viewModeLampiran(HttpSession session,String idGambar) throws Exception {		
		Vector list =null;
		//list = FrmRekodPembangunanImejData.getMaklumatImejById(idGambar);
		list = getIOnline().getLampiranByDokumen(idGambar);
		Hashtable hImejById = (Hashtable) list.get(0);		
		this.context.put("idLampiran",(String)hImejById.get("idLampiran"));
		this.context.put("idGambar",(String)hImejById.get("idLampiran"));
		this.context.put("idDokumen",(String)hImejById.get("idDokumen"));
		this.context.put("txtRingkas",(String)hImejById.get("keterangan"));
		this.context.put("txtPerihalImej", (String)hImejById.get("perihal"));
		this.context.put("namafail",(String)hImejById.get("namaFail"));
		//this.context.put("txdTarikhKemaskiniImej", (String)hImejById.get("tarikh"));

	}
	
	private void simpanPengesahan(Tblrujsuburusanstatusfail rsusf,String langkah)throws Exception {
		 try {
			subUrusanStatusFail = new Tblrujsuburusanstatusfail();
			subUrusanStatusFail.setIdPermohonan(rsusf.getIdPermohonan());
			subUrusanStatusFail.setIdFail(rsusf.getIdFail());
			subUrusanStatusFail.setAktif("0");
		
			Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
			long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,String.valueOf(rsusf.getIdSuburusanstatusfail()),"=");
			subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
			subUrusanStatusFailN.setAktif("1");
			subUrusanStatusFailN.setUrl(Utils.isNull(rsusf.getUrl()));
			subUrusanStatusFailN.setIdMasuk(Long.parseLong(userID));
			subUrusanStatusFailN.setIdKemaskini(Long.parseLong(userID));
			getIHTP().kemaskiniSimpanStatusPermohonanAktif(subUrusanStatusFail,subUrusanStatusFailN);
			
		} catch (Exception e) {
			//throw new Exception("Ralat FrmGadaian[554]:"+e.getCause());
			getIHTP().getErrorHTML(e.toString());

		}
	}
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
	private IHTPPermohonan getIHTPPermohonan(){
		if(iHTPPermohonan== null)
			iHTPPermohonan = new HTPPermohonanBean();
		return iHTPPermohonan;
	}
	
	private IPembelianOnline getIPembelianOnline(){
		if(iPembelianOnline==null){
			iPembelianOnline = new PembelianOnlineBean();
		}
		return iPembelianOnline;
			
	}
	
	private IUtilHTML getIUtil(){
		if(iUtil==null){
			iUtil = new UtilHTMLBean();
		}
		return iUtil;
			
	}
	
	private IUserPegawai getIUser(){
		if(iUser==null){
			iUser = new UserBean();
		}
		return iUser;
			
	}
	
//	private IHTPEmel getHTPEmel(){
//		if(iHTPEmel == null)
//			iHTPEmel = new HTPEmelPermohonanBean();
//		return iHTPEmel;
//	}

//	private IHTPEmel getHTPEmelSemak(){
//		if(iHTPEmel == null)
//			iHTPEmel = new HTPEmelSemakBean();
//		return iHTPEmel;
//	}	
	
	private IEmel getEmelSemak(){
		if(emelSemak == null)
			emelSemak = new HTPEmelSemakanBean();
		return emelSemak;
	}

	
}


