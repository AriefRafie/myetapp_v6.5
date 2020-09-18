package ekptg.view.htp.pajakan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblhtpjemaahmenteri;
import ekptg.model.entities.Tblrujsuburusanstatus;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmFailLainKemaskiniData;
import ekptg.model.htp.FrmPajakanBayaranPajakanCukaiTanahData;
import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.FrmPajakanMemorandumJemaahMenteriData;
import ekptg.model.htp.FrmPajakanPendaftaranData;
import ekptg.model.htp.FrmPajakanPerjanjianPajakanData;
import ekptg.model.htp.FrmPajakanSenaraiFailData;
import ekptg.model.htp.FrmPenswastaan2SenaraiFailData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HTPStatusBean;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.HTPeringatanBean;
import ekptg.model.htp.IHTPStatus;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.IHTPeringatan;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pajakan;
import ekptg.model.htp.entity.PajakanUlasan;
import ekptg.model.htp.entity.Pemohon;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.online.IOnline;
import ekptg.model.htp.online.OnlineBean;
import ekptg.model.htp.pajakan.IPajakanFungsi;
import ekptg.model.htp.pajakan.IPajakanMJM;
import ekptg.model.htp.pajakan.IPajakanUtamaBayaran;
import ekptg.model.htp.pajakan.PajakanMJMBean;
import ekptg.model.htp.pajakan.PajakanUtamaBayaranBean;
import ekptg.model.htp.pajakan.PajakanUtamaPerjanjian;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;
import ekptg.model.htp.utiliti.HTPSusulanBean;
import ekptg.model.htp.utiliti.IHTPSusulan;
import ekptg.model.htp.utiliti.fail.HTPFailBean;
import ekptg.model.htp.utiliti.fail.IHTPFail;
import ekptg.model.integrasi.FrmJPPHModelNilaianPajakan;
import ekptg.model.utils.IUserPegawai;
import ekptg.model.utils.UserKJPBean;
import ekptg.view.htp.utiliti.HtpEmailServices;

/**
 *
 */
public class FrmPajakanSenaraiFailView extends AjaxBasedModule {

	private final String PATH="app/htp/pajakan/fail/";
	private final String PATHPER="app/htp/pajakan/perjanjian/";
	private final String PATHBAY="app/htp/pajakan/bayaran/";
	private final String PATHTAM="app/htp/pajakan/penamatan/";
	private final String PATHPANTAU="app/htp/pajakan/pemantauan/";

	private final String JENISTANAH = "2,4";	//TR-TANAH RIZAB,TM-TANAH MILIK
	private static final long serialVersionUID = 1L;
	FrmPajakanHeaderData logicHeader = new FrmPajakanHeaderData();
	FrmPajakanSenaraiFailData logic = new FrmPajakanSenaraiFailData();
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.pajakan.FrmPajakanSenaraiFailView.class);
	private PfdFail fail = null;
	//private Permohonan permohonan = null;
	private HakmilikUrusan urusan = null;
	private HtpBean iHtp = null;
	private HtpPermohonan htpPermohonan = null;
 	private IHTPFail iHTPFail = null;
	private IPembelian iPembelian = null;
 	// mjm
	private IPajakanMJM iPMJM = null;
	private IOnline iOnline = null;
	private IHTPStatus iStatus = null;
	//perjanjian
	private IPajakanFungsi iPFungsi = null;
	//tindakan
	private IHTPSusulan iSusulan = null;

	//19/08/2010
	String flagAdvSearch = "";
	//Pendaftaran
	FrmPajakanPendaftaranData logicp = new FrmPajakanPendaftaranData();
	FrmPenswastaan2SenaraiFailData swastaData = new FrmPenswastaan2SenaraiFailData();

	private IHTPeringatan iHTPP = null;
    //MJM
	FrmPajakanMemorandumJemaahMenteriData logicmjm = new FrmPajakanMemorandumJemaahMenteriData();
    private Pemohon pemohon = null;
    String tarikhSemasa = "";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String idPermohonan ="";
    String idFail_ = "";
    String idSubUrusan = "";
    private String userID = "";
    private String iDeraf = "-1";
    //String idPermohonan_ = "";
    //Perjanjian
	FrmPajakanPerjanjianPajakanData logicper = new FrmPajakanPerjanjianPajakanData();
	private IPajakanUtamaBayaran iPajakanBayaran = null;
	//Bayaran
	FrmPajakanBayaranPajakanCukaiTanahData logicBayaran = new FrmPajakanBayaranPajakanCukaiTanahData();
	//PENAMATAN
	private Tblrujsuburusanstatusfail subUrusanStatusFail = null;
	String idSusulan = "";

	String idJawatan = "";
	private IUserPegawai iUser = null;

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    //GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
        String actionPajakan = getParam("actionPajakan");
        //String submit = getParam("command");
        String mode = getParam("mode");
        String hitButton = getParam("hitButton");
        String page_ = "0";
        //MJM
        String selectedTabLower = getParam("selectedTabLower");
        myLog.info("doPost="+doPost+",action="+action+",actionpajakan="+actionPajakan+",mode="+mode+",hitButton="+hitButton);
        userID = String.valueOf(session.getAttribute("_ekptg_user_id"));
        String portal_role = (String)session.getAttribute("myrole");
        myLog.info("portal_role ros >>>>> "+portal_role);

        //GET ID PARAM
        String idFail = getParam("idFail");
        //25/01/2017
        //String idPermohonan = getParam("idPermohonan");
        idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String subUrusan = getParam("subUrusan");
        myLog.info("idFail="+idFail+",subUrusan:"+subUrusan);
        //String idHakmilikUrusan = getParam("idHakmilikUrusan");

        //VECTOR
        Vector<Hashtable<String, String>> beanHeader = null;
        Vector<Hashtable<String, String>> list = null;
        Vector<Hashtable<String, String>> beanMaklumatPermohonan = null;
        Vector<Hashtable<String, String>> senaraiHakmilik = null;

        String noHakmilikC = getParam("txthakmilik");
        String noLotC = getParam("txtlot");

        //GET DROPDOWN PARAM
        String idNegeri = getParam("socNegeri");
		if (idNegeri.equals("-1") || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idKementerian = getParam("socKementerian");
		//if (idKementerian == null || idKementerian.trim().length() == 0){
		if (idKementerian.equals("-1") || idKementerian.trim().length() == 0){
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		//if (idAgensi == null || idAgensi.trim().length() == 0){
		if (idAgensi.equals("-1") || idAgensi.trim().length() == 0 ){
			idAgensi = "99999";
		}
		String idSuburusan = getParam("socSuburusan");
		//if (idSuburusan == null || idSuburusan.trim().length() == 0){
		if (idSuburusan.equals("-1") || idSuburusan.trim().length() == 0){
			idSuburusan = "99999";
		}
		String idStatusTanah = getParam("socStatusTanah");
		if (idStatusTanah == null || idStatusTanah.trim().length() == 0){
			idStatusTanah = "99999";
		}
		String idJenisFail = getParam("socJenisFail");
		if (idJenisFail == null || idJenisFail.trim().length() == 0){
			idJenisFail = "99999";
		}
		myLog.info("actionPajakan:0::"+actionPajakan);
		//18/08/2010
		flagAdvSearch = getParam("flagAdvSearch");
        // Pendaftaran Pajakan 24/01/2017
		String selectedTab = getParam("selectedTab");
        if(selectedTab.equals("") || selectedTab.equals(null)){
        	selectedTab = "0";
        }

        /* 2017/03/27
        String idFailSession = "";
        if (session.getAttribute("idFail") != null){
        	idFailSession = String.valueOf(session.getAttribute("idFail"));
        	if(!getParam("idFail").equals("")){
        		idFailSession = getParam("idFail");
        	}
    		htpPermohonan = getIHTP().findPermohonan(idFailSession,idPermohonan,"");
    		if(htpPermohonan == null)
				throw new Exception(getIHTP().getErrorHTML("[HTP PAJAKAN] SILA KEMASKINI FAIL TERLEBIH DAHULU"));

        	idPermohonan = String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan());

        }
        idFail = getParam("idFail").equals("")?idFailSession:getParam("idFail");
 		session.setAttribute("idFail", idFail);
 		*/
 		// END Pendaftaran
        //PENDAFTARAN
        String idHakmilikUrusan = getParam("idHakmilikUrusan");
		//for email
		boolean sendEmail = true;
		HtpEmailServices htpEmailService = new HtpEmailServices();
		String emUrusan = "Pajakan";
		String emEmailto = "rosli@si-protech.com.my";
		String emMaincontent = "hantarPengesahanPermohonanPajakan";
		String emNofail = "";
		String emTitle = "";
		String emTarikhMohon = "";
		String emAgensi = "";

        // MJM
        iDeraf = getParam("idDraf");
 		String idUlasanKJP = getParam("idUlasanKJP");
        String idUlasanNilai = getParam("idUlasanNilai");
        String idUlasanTeknikal = getParam("idUlasanTeknikal");
        String idUlasanSPHP = getParam("idUlasanSPHP");
		idSubUrusan = subUrusan;
        myLog.info("idSubUrusan:"+idSubUrusan);
        idFail_ = idFail;
        if(selectedTabLower.equals("") || selectedTabLower.equals(null))
	        selectedTabLower = "0";

        //Perjanjian
		String idPajakan = getParam("idPajakan");
		//String idDerafPerjanjian = getParam("idDraf");
		//Bayaran
		String idBayaran = getParam("idBayaran");
		//Tindakan
		idSusulan = getParam("idsusulan");

		String userId = (String) session.getAttribute("_ekptg_user_id");
		String userJawatan = "";

		Hashtable hUser = getIUser().getPengguna(userId);
		userJawatan = String.valueOf(hUser.get("idJawatan"));
		idJawatan = userJawatan;
		myLog.info("idJawatan >>> "+idJawatan);
		context.put("idjawatan", idJawatan);
		myLog.info("hitButton ros >>>> "+hitButton);
		myLog.info("actionPajakan ros >>>> "+actionPajakan);
		String statusSemasa = "";

		if(portal_role.contains("HQPengguna")){
			statusSemasa = "penyedia";
		}else if(portal_role.contains("HQPegawai")){
			statusSemasa = "penyemak";
		}else if(portal_role.contains("HQPengarah")){
			statusSemasa = "pelulus";
		}
		myLog.info("statusSemasa >>> "+statusSemasa);
		this.context.put("statusSemasa", statusSemasa);

		//HITBUTTON
		if (postDB){
			//myLog.info("HITBUTTON="+hitButton);
          	/** Pendaftaran*/
    		if (hitButton.equals("simpan")){
         		idFail = logic.simpan(idNegeri, idKementerian, idAgensi, idSuburusan, idStatusTanah, idJenisFail, getParam("txtNoFailKJP"),
         					getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"),getParam("tarikhSuratPemohon"), session);
         		session.setAttribute("idFail", idFail);
	        	Hashtable<String,String> hFail = getIHTPFail().getMaklumatPermohonan(idFail);
				AuditTrail.logActivity("1", "3", this, session, "INS", "FAIL PERMOHONAN ["+hFail.get("noFail")+"] DITAMBAH ");

    		}else if (hitButton.equals("simpanbyswasta")){
         		idFail = logic.simpan(idPermohonan,idNegeri, idKementerian, idAgensi, idSuburusan, idStatusTanah, idJenisFail, getParam("txtNoFailKJP"),
         					getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"),getParam("tarikhSuratPemohon"), session);
         		session.setAttribute("idFail", idFail);
	        	Hashtable<String,String> hFail = getIHTPFail().getMaklumatPermohonan(idFail);
				AuditTrail.logActivity("1", "3", this, session, "INS", "FAIL PERMOHONAN ["+hFail.get("noFail")+"] DITAMBAH ");

    		}else if (hitButton.equals("seterusnyadaftar")){
         		logicp.seterusnya(idFail, idPermohonan, subUrusan, session);
        	}else if (hitButton.equals("deletehakmilik")){
             	logicp.deleteHakmilik(idHakmilikUrusan);
        	}else if (hitButton.equals("saveUpdateFail")){
        		saveUpdateFail(idFail,subUrusan,session);
        	}else if (hitButton.equals("hantarpenyemakdaftar")){
    			//swastaData.saveHantarPengesahan(idFail, session,"H"); //hantar
        		logicp.hantarPenyemak(idFail, idPermohonan, subUrusan, session);

    			//email
    			if(sendEmail){
    				//System.out.println("send mail");
    				htpEmailService.setEmail(emUrusan,emEmailto,emMaincontent,emNofail,emTitle,emTarikhMohon,emAgensi);
    			}

        	}else if (hitButton.equals("hantarpengesahandaftar")){
    			//swastaData.saveHantarPengesahan(idFail, session,"H"); //hantar
        		logicp.hantarPengesahan(idFail, idPermohonan, subUrusan, session);

    			//email
    			if(sendEmail){
    				//System.out.println("send mail");
    				htpEmailService.setEmail(emUrusan,emEmailto,emMaincontent,emNofail,emTitle,emTarikhMohon,emAgensi);
    			}

        	}else if (hitButton.equals("sahkanpermohonandaftar")){
        		logicp.sahkan(idFail, idPermohonan, subUrusan, session);
    			//swastaData.saveHantarPengesahan(idFail, session,"S"); //sahkan

    		/** MJM */
        	}else if (hitButton.equals("savePemohon")){
    			savePemohon(idPermohonan, session);

        	}else if (hitButton.equals("saveMemo")){
        		saveMemo(idPermohonan, session);
        		//myLog.info(idFail_);
        		uploadFiles(session,idFail_);

        	}else if (hitButton.equals("hapusmemo")){
    			logicmjm.hapusMJM(idPermohonan);
        		Tblhtpjemaahmenteri mjm = (Tblhtpjemaahmenteri)getIPMJM().getMaklumatMemorandumJemaahMenteri(idPermohonan);
    			AuditTrail.logActivity(idStatusTanah, "3", this, session, "DEL", "MEMORANDUM   [NO. MEMO:"+mjm.getNoMemorandum()+"] DIHAPUSKAN ");

        	}else if (hitButton.equals("hapuslampiran")){
				String idDokumen = getParam("iDokumen");
				String idLampiran = getParam("idLampiran");
			    getIOnline().hapusLampiran(idDokumen,idLampiran);

        	}else if (hitButton.equals("saveUlasanKJP")){
        		saveUlasanKJP(idPermohonan, session);

        	}else if (hitButton.equals("saveUpdateKJP")){
        		saveUpdateUlasanKJP(idUlasanKJP, session);

        	}else if (hitButton.equals("hapusKJP")){
    			logicmjm.hapusKJP(idUlasanKJP);

        	}else if (hitButton.equals("saveJPPH")){
    			saveJPPH(idPermohonan, session);

        	}else if (hitButton.equals("saveUpdateJPPH")){
    			saveUpdateJPPH(idUlasanTeknikal, idUlasanNilai, session);

        	}else if (hitButton.equals("hapusJPPH")){
    			logicmjm.hapusJPPH(idUlasanTeknikal, idUlasanNilai);

        	}else if (hitButton.equals("saveUlasanSPHP")){
    			saveSPHP(idPermohonan, session);

        	}else if (hitButton.equals("saveUpdateSPHP")){
    			saveUpdateSPHP(idUlasanSPHP, session);

        	}else if (hitButton.equals("hapusSPHP")){
    			logicmjm.hapusSPHP(idUlasanSPHP);

        	}else if (hitButton.equals("saveDraf")){
//    			myLog.debug("** save draft **");
    			//saveDraf(idPermohonan, session);
    			//downloadDraf(idPermohonan , session);
    			//06/09/2010
    			iDeraf = simpanDraf(idPermohonan, session);
    			//myLog.debug("** save draft **idDerafPerjanjian="+idDerafPerjanjian);
    			simpanLampiran(iDeraf);

    		}else if (hitButton.equals("downloadDraf")){
    			downloadDraf(idPermohonan , session);

    		}else if (hitButton.equals("saveUpdateDraf")){
    			saveUpdateDraf(iDeraf, session);
    			simpanLampiran(iDeraf);

    		}else if (hitButton.equals("hapusDraf")){
    			logicmjm.hapusDraf(iDeraf);

    		}else if (hitButton.equals("seterusnyamjm")){
    			logicmjm.seterusnya(idFail, idPermohonan, subUrusan, session);

    		//Perjanjian
    		}else if (hitButton.equals("saveMOA")){
    			saveMOA(idPermohonan, session);
    		}else if (hitButton.equals("save15A")){
    			save15A(idPermohonan, session);
    		}else if (hitButton.equals("saveJM")){
				saveJM(idPermohonan, session);
    		}else if (hitButton.equals("savedrafper")){
    			//saveDraf(idPermohonan, session);
    			simpanPerjanjian(idPermohonan, "simpan");
    		}else if (hitButton.equals("saveupdatedrafper")){
    			//saveUpdateDrafPerjanjian(idDraf, session);
    			simpanPerjanjian(iDeraf, "kemaskini");
    		}else if (hitButton.equals("hapusdrafper")){
    			//idDraf = getParam("idDraf");
    			logicper.hapusDraf(iDeraf);
    		}else if (hitButton.equals("savePajakan")){
    			savePajakan(idPermohonan, session);
    		}else if (hitButton.equals("saveUpdatePajakan")){
    			saveUpdatePajakan(idPajakan, session);
    		}else if (hitButton.equals("hapusPajakan")){
    			logicper.hapusPajakan(idPajakan);
    		}else if (hitButton.equals("seterusnyaperjanjian")){
    			logicper.seterusnya(idFail, idPermohonan, subUrusan, session);
            //BAYARAN
    		}else if (hitButton.equals("saveBayaran")){
        		//saveBayaran(idPermohonan, session);
        		simpanKemaskiniBayaran(idPermohonan, "-1");
        	}else if (hitButton.equals("hapusBayaran")){
        		logicBayaran.hapusBayaran(idBayaran);
        	}else if (hitButton.equals("saveUpdateBayaran")){
        		//saveUpdateBayaran(idBayaran, session);
        		simpanKemaskiniBayaran(idPermohonan,idBayaran);
        	//PENAMATAN
        	}else if (hitButton.equals("selesaisimpan")){
				String langkah = getParam("sockategori");
        		myLog.info("masuk sini langkah " +langkah);

        		kemaskiniSimpanStatusSelesai(idFail,idPermohonan,subUrusan,langkah);
        		getStatus().kemaskiniStatusPermohonan(idPermohonan,subUrusan,langkah,userID);

           	}else if (hitButton.equals("selesaikemaskinisimpan")){
    	    	kemaskiniSimpanStatusSelesai(idFail,idPermohonan);

           	}else if (hitButton.equals("tindakansimpan")){
           		setTindakanValues(idPermohonan,"-1",hitButton);

        	}else if (hitButton.equals("tindakankemaskini")){
        		setTindakanValues(idPermohonan,getParam("idsusulan"),hitButton);

        	}else if (hitButton.equals("tindakanhapus")){
        		setTindakanValues(idPermohonan,getParam("idsusulan"),hitButton);

        	}

		}

		//HEADER
        beanHeader = new Vector<Hashtable<String, String>>();
        logicHeader.setMaklumatPermohonan(idFail);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);

		if (beanHeader.size() != 0){
			Hashtable<String, String> hashHeader = (Hashtable<String, String>) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = hashHeader.get("idFail").toString();
			idPermohonan = hashHeader.get("idPermohonan").toString();
			idStatus = hashHeader.get("idStatus").toString();
			subUrusan = hashHeader.get("subUrusan").toString();
	        myLog.info("idFail="+idFail+",idPermohonan 2="+idPermohonan);
	        myLog.info("idStatus="+idStatus);

	        /*Semakan jika telah disahkan pengarah atau belum (bagi fail baru didaftar selepas penambahbaikan). syaz. 01122014 */
			String flagMohonFail = hashHeader.get("flagMohonFail").toString();
			String strval = "display";
			if((flagMohonFail!="") && !flagMohonFail.equalsIgnoreCase("S") ){
				strval = "hide";
			}
			this.context.put("valFlagMohonFail", strval);

		}

		if (actionPajakan.equals("papar")){
        	vm = PATH+"frmDaftar.jsp";
			//GO TO PAPAR PAJAKAN
			logicHeader.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = new Vector<Hashtable<String, String>>();

    		beanMaklumatPermohonan = logicHeader.getBeanMaklumatPermohonan();
			//Hashtable<String, String> hashHeader = (Hashtable<String, String>) logicHeader.getBeanMaklumatPermohonan().get(0);
			/*hashPermohonan.put("noFail", "");
			hashPermohonan.put("noFailKJP", getParam("txtNoFailKJP") == null ? "" : getParam("txtNoFailKJP"));
			hashPermohonan.put("tarikhSuratKJP", getParam("tarikhSuratKJP") == null ? "" : getParam("tarikhSuratKJP"));
			hashPermohonan.put("noFailLain", getParam("txtNoFailLain") == null ? "" : getParam("txtNoFailLain"));
			hashPermohonan.put("tarikhAgihan", getParam("tarikhAgihan") == null ? "" : getParam("tarikhAgihan"));
			hashPermohonan.put("tajuk", getParam("txtTajuk") == null ? "" : getParam("txtTajuk"));
			hashPermohonan.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon") == null ? "" : getParam("tarikhSuratPemohon"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
	*/
			this.context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri", Long.parseLong(idNegeri), " disabled class=disabled", ""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled class=disabled", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), " disabled class=disabled", ""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(idSuburusan), " disabled class=disabled", ""));
			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), " disabled class=disabled", ""));
			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), " disabled class=disabled", ""));

        	this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
        	MaklumatPermohonanView("view");
        	this.context.put("mode", mode);

		} else if (actionPajakan.equals("daftarbaru")){  //ACTION PAJAKAN
			myLog.info("daftarBaru");
			//if ("daftarBaru".equals(submit)){
        	//GO TO DAFTAR BARU PAJAKAN
        	//vm = PATH+"frmPajakanPenerimaanPermohonanDaftar.jsp";
        	vm = PATH+"frmDaftar.jsp";

        	mode = "new";
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");

        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector<Hashtable<String, String>>();
			Hashtable<String, String> hashPermohonan = new Hashtable<String, String>();
			hashPermohonan.put("noFail", "");
			hashPermohonan.put("noFailKJP", getParam("txtNoFailKJP") == null ? "" : getParam("txtNoFailKJP"));
			hashPermohonan.put("tarikhSuratKJP", getParam("tarikhSuratKJP") == null ? "" : getParam("tarikhSuratKJP"));
			hashPermohonan.put("noFailLain", getParam("txtNoFailLain") == null ? "" : getParam("txtNoFailLain"));
			hashPermohonan.put("tarikhAgihan", getParam("tarikhAgihan") == null ? "" : getParam("tarikhAgihan"));
			hashPermohonan.put("tajuk", getParam("txtTajuk") == null ? "" : getParam("txtTajuk"));
			hashPermohonan.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon") == null ? "" : getParam("tarikhSuratPemohon"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

//			this.context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(idNegeri), "", ""));
//			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
//			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), "", ""));
//			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(idSuburusan), "", ""));
//			this.context.put("selectStatusTanah",UtilHTML.SelectJenisTanah("socStatusTanah",Long.parseLong(idStatusTanah),"",JENISTANAH));
//			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), "", ""));
        	setContext(idNegeri,idKementerian,idAgensi,idSuburusan,idStatusTanah,idJenisFail);

        }else if (actionPajakan.equals("daftarBaruSwasta")){
		//if ("daftarBaru".equals(submit)){
        	//GO TO DAFTAR BARU PAJAKAN
        	vm = PATH+"frmPajakanPenerimaanPermohonanDaftarSwasta.jsp";

        	mode = "new";
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");

        	//MAKLUMAT PERMOHONAN
        	// idHakmilik (parameter dari skrin)
			urusan = getIPembelian().findByHakmilikUrusanId(getParam("idHakmilik"));
			//idPermohonan = urusan.getPermohonan().getNoPermohonan();
			htpPermohonan = getIPembelian().findPermohonan(urusan.getPermohonan().getNoPermohonan(), "");
			fail = htpPermohonan.getPermohonan().getPfdFail();

        	beanMaklumatPermohonan = new Vector<Hashtable<String, String>>();
			Hashtable<String, String> hashPermohonan = new Hashtable<String, String>();
			hashPermohonan.put("noFail", "");
			hashPermohonan.put("noFailKJP", getParam("txtNoFailKJP") == null ? "" : getParam("txtNoFailKJP"));
			hashPermohonan.put("tarikhSuratKJP", getParam("tarikhSuratKJP") == null ? "" : getParam("tarikhSuratKJP"));
			hashPermohonan.put("noFailLain", getParam("txtNoFailLain") == null ? "" : getParam("txtNoFailLain"));
			hashPermohonan.put("tarikhAgihan", getParam("tarikhAgihan") == null ? "" : getParam("tarikhAgihan"));
			hashPermohonan.put("tajuk", getParam("txtTajuk") == null ? "" : getParam("txtTajuk"));
			hashPermohonan.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon") == null ? "" : getParam("tarikhSuratPemohon"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

//			this.context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(urusan.getIdNegeri()), "", ""));
//			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",fail.getIdKementerian(), "", " onChange=\"doChangeKementerian();\""));
//			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,htpPermohonan.getIdAgensi(), "", ""));
//			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(String.valueOf("18")), " disabled", ""));
//			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), "", ""));
//			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), "", ""));
        	setContext(idNegeri,idKementerian,idAgensi,idSuburusan,idStatusTanah,idJenisFail);

  		// Pendaftaran 24/01/2017
        } else if (actionPajakan.equals("papardaftar")||actionPajakan.equals("paparpermohonan")){
			vm = PATH+"frmDaftarHakmilik.jsp";
			if(selectedTab.equals("0")){
				logicHeader.setMaklumatPermohonan(idFail);
				beanMaklumatPermohonan = new Vector<Hashtable<String, String>>();
	    		beanMaklumatPermohonan = logicHeader.getBeanMaklumatPermohonan();
	        	this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
	       		MaklumatPermohonanView(mode);
	       		this.context.remove("senaraiFailLain");
		       	list = FrmFailLainKemaskiniData.getDataFailLain(idFail);
		       	this.context.put("senaraiFailLain",list);

			}else if(selectedTab.equals("1")){
				senaraiHakmilik = new Vector<Hashtable<String, String>>();
	    		logicp.setListHakmilik(idPermohonan);
	    		senaraiHakmilik = logicp.getSenaraiHakmilik();
	   			this.context.put("SenaraiHakmilik", senaraiHakmilik);

			}
			SimpleDateFormat sdfTahun = new SimpleDateFormat("yyyy");
		    myLog.info("papardaftar:idPermohonan="+idPermohonan);
		    Vector<HtpPermohonan> peringatanBayaran = getIHTPP().getPeringatanJenisBayaranPer(idPermohonan, "3",sdfTahun.format(new Date()),"21");
		    this.context.put("bayarPajakan", peringatanBayaran);
		    page_ = "2";
		// PERMOHON 06/09/2020
        } else if(actionPajakan.equalsIgnoreCase("paparpemohon")){
	        	vm ="app/htp/pajakan/pemohon/index.jsp";
	        	myLog.info("paparpemohon...selectedTab="+selectedTab);
	        	myLog.info("paparpemohon...selectedTabLower="+selectedTabLower);

	    		myLog.info("idJawatan paparpemohon >>> "+idJawatan);
	    		this.context.put("idjawatan", idJawatan);

        		Vector<Hashtable<String, String>> vec = logicmjm.getMaklumatPemohonPajakan(idPermohonan);
            	PemohonPajakanView(mode,vec);
		        page_ = "3";
        // ULASAN 06/09/2020
        }else if(actionPajakan.equalsIgnoreCase("paparulasan")){
        	vm ="app/htp/pajakan/ulasan/index.jsp";
        	myLog.info("paparulasan...selectedTab="+selectedTab);
        	myLog.info("paparulasan...selectedTabLower="+selectedTabLower);
    		if (selectedTabLower.equals("0")){
        		//ulasan KJP
    			Vector<Hashtable<String, String>> senaraiUlasanKJP = new Vector<Hashtable<String, String>>();
    			logicmjm.setListUlasanKJP(idPermohonan);
    			senaraiUlasanKJP = logicmjm.getSenaraiUlasanKJP();
        		this.context.put("SenaraiUlasanKJP", senaraiUlasanKJP);
        		UlasanKJPView(mode, idUlasanKJP);

    		}else if(selectedTabLower.equals("1")){
        		//ulasan JPPH
    			Vector<Hashtable<String, String>> senaraiUlasanJPPH = new Vector<Hashtable<String, String>>();
        		logicmjm.setListUlasanJPPH(idPermohonan);
        		senaraiUlasanJPPH = logicmjm.getSenaraiUlasanJPPH();
        		this.context.put("SenaraiUlasanJPPH", senaraiUlasanJPPH);
        		UlasanJPPHView(mode, idUlasanTeknikal);
        		this.context.put("SenaraiUlasanJPPHOnline", FrmJPPHModelNilaianPajakan.listUlasanOnline(idPermohonan));

    		}else{
    			//ulasan SPHP
    			Vector<Hashtable<String, String>> senaraiUlasanSPHP = new Vector<Hashtable<String, String>>();
        		logicmjm.setListUlasanSPHP(idPermohonan);
        		senaraiUlasanSPHP = logicmjm.getSenaraiUlasanSPHP();
        		this.context.put("SenaraiUlasanSPHP", senaraiUlasanSPHP);
        		UlasanSPHPView(mode, idUlasanSPHP);

    		}
	        page_ = "4";

        }else if(actionPajakan.equalsIgnoreCase("paparmjm")){
        	vm ="app/htp/pajakan/mjm/index.jsp";
        	myLog.info("paparmjm...selectedTab="+selectedTab);
        	myLog.info("paparmjm...selectedTabLower="+selectedTabLower);

			myLog.info(action+",MJM:mode="+mode);
			this.context.remove("BeanMJM");
	    	tarikhSemasa = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
			Tblhtpjemaahmenteri mjm = (Tblhtpjemaahmenteri)getIPMJM().getMaklumatMemorandumJemaahMenteri(idPermohonan);
			MemorandumJemaahMenteriView(mode,mjm);
			this.context.put("num_files",1);
			if (getParamAsInteger("jumlahlampiran")>1) {
				int j = getParamAsInteger("jumlahlampiran");
				this.context.put("num_files", j);

			}// end getParamAsInteger("jumlahlampiran")
	        page_ = "5";

        }else if(actionPajakan.equalsIgnoreCase("paparderaf")){
        	vm ="app/htp/pajakan/deraf/index.jsp";
        	myLog.info("paparderaf...selectedTab="+selectedTab);
        	myLog.info("paparderaf...selectedTabLower="+selectedTabLower);


    		//draf memorandum jemaah menteri
    		Vector<Hashtable<String, String>> senaraiDraf = new Vector<Hashtable<String, String>>();
    		logicmjm.setListDraf(idPermohonan);
    		senaraiDraf = logicmjm.getSenaraiDraf();
    		this.context.put("SenaraiDraf", senaraiDraf);

    		DrafView(mode, iDeraf);

	        page_ = "6";

        }else if (actionPajakan.equalsIgnoreCase("paparperjanjian")) {
        	vm ="app/htp/pajakan/perjanjian/index.jsp";
        	//vm =PATHPER+"index.jsp";
			myLog.info("paparperjanjian="+actionPajakan+",mode="+mode);
			Vector<Hashtable <String,String>> senaraiDraf = new Vector<Hashtable <String,String>>();
        	//logicper.setListDraf(idPermohonan);
        	//senaraiDraf = logicper.getSenaraiDraf();
			senaraiDraf = getIPFungsi().getSenarai(idPermohonan,"P");
        	this.context.put("SenaraiDraf", senaraiDraf);
        	drafViewPerjanjian(mode, iDeraf);

			page_ = "7";

        }else if (actionPajakan.equalsIgnoreCase("paparpajakan")) {
        	vm ="app/htp/pajakan/pajakan/index.jsp";
			myLog.info("paparpajakan="+actionPajakan+",mode="+mode);
			if (selectedTab.equals("0")){
	        	Vector<Hashtable <String,String>> senaraiPajakan = new Vector<Hashtable <String,String>>();
	        	logicper.setListPajakan(idPermohonan);
	        	senaraiPajakan = logicper.getSenaraiPajakan();
	        	this.context.put("SenaraiPajakan", senaraiPajakan);
	        	PajakanView(mode,idPajakan,idPermohonan);
	    		this.context.put("idPajakan", idPajakan);

	        }  else if (selectedTab.equals("1")){
	        	Borang15AView(mode);
	        	/*logicper.setMaklumatTabKelulusanJemaahMenteri(idPermohonan);
				JemaahMenteriView(mode);*/

	        }
			page_ = "8";

        }else if(actionPajakan.equalsIgnoreCase("BayaranPajakan")){
            vm  = PATHBAY+"index.jsp";
            logicBayaran.setListMaklumatBayaran(idFail);
        	BayaranView(mode, idBayaran);
			page_ = "9";

        }else if(actionPajakan.equalsIgnoreCase("penamatan")){
            vm  = PATHTAM+"index.jsp";
    	    //if(mode.equalsIgnoreCase("selesaiview")){
        	if(mode.equalsIgnoreCase("view")){
    	    	myLog.info("selesaiview:idPermohonan="+idPermohonan);
    	    	statusView(idFail,idPermohonan,mode);

//    	    }else if(mode.equalsIgnoreCase("selesaisimpan")){
//     	    	beanHeader = new Vector();
//    	        logicHeader.setMaklumatPermohonan(idFail);
//    	        beanHeader = logicHeader.getBeanMaklumatPermohonan();
//    			this.context.put("BeanHeader", beanHeader);
//    	    	statusView(idPermohonan);

    	    //}else if(mode.equalsIgnoreCase("selesaikemaskini")){
    	    }else if(mode.equalsIgnoreCase("kemaskini")){
    	    	myLog.info("selesaikemaskini");
    	    	statusView(idFail,idPermohonan,mode);
    	    	//this.context.put("modeDisplay", "");
    			//this.context.put("classDis", "");

//    	    }else if(mode.equalsIgnoreCase("selesaikemaskinisimpan")){
//    	    	myLog.info("selesaikemaskinisimpan");
//    	    	//kemaskiniSimpanStatusSelesai(idFail,idPermohonan);
//    	    	statusView(idPermohonan);
//
    	    }
			page_ = "10";

        }else if(actionPajakan.equalsIgnoreCase("pemantauan")){
            vm  = PATHPANTAU+"index.jsp";
			//String sumber = "PAJAKAN_TINDAKAN";

        	getTindakan(mode);
			page_ = "11";

        } else if(actionPajakan.equals("carian")){
        	myLog.info("carian");
        	vm = PATH+"index.jsp";
          	idNegeri = getParam("socnegericarian");
    		if (idNegeri.equals("-1") || idNegeri.trim().length() == 0){
    			idNegeri = "99999";
    		}
        	idKementerian = getParam("sockementeriancarian");
    		if (idKementerian.equals("-1") || idKementerian.trim().length() == 0){
    			idKementerian = "99999";
    		}
        	idAgensi = getParam("socagensicarian");
    		if (idAgensi.equals("-1") || idAgensi.trim().length() == 0 ){
    			idAgensi = "99999";
    		}
         	idSuburusan = getParam("socsuburusancarian");
    		if (idSuburusan.equals("-1") || idSuburusan.trim().length() == 0){
    			idSuburusan = "99999";
    		}
       		idStatus = getParam("socstatuscarian");
    		if (idStatus.equals("") || idStatus.trim().length() == 0){
    			idStatus = "99999";
    		}

			this.context.put("socNegeri",HTML.SelectNegeri("socnegericarian", Long.parseLong(idNegeri), "", "  onChange=\"doChangeKementerian();\""));
			this.context.put("socKementerian",HTML.SelectKementerian("sockementeriancarian",Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
			this.context.put("socAgensi",HTML.SelectAgensiByKementerian("socagensicarian", idKementerian ,Long.parseLong(idAgensi), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
			this.context.put("selectSuburusan",UtilHTML.selectSuburusanLaporan("3", "socsuburusancarian" ,idSuburusan, "", ""));
			this.context.put("selectStatus",UtilHTML.selectStatusByModule("socstatuscarian", Long.parseLong(idStatus), "", "3"));
			if(flagAdvSearch.equals("Y")){
				list = getIHTP().carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txdtarikhdaftarfail"), getParam("txdTarikhTerima")
					, getParam("txtNamaPemohon")
					, idNegeri
					, idKementerian, idAgensi
					, "",noHakmilikC, "",noLotC
					, idSuburusan
					, idStatus);
				flagAdvSearch = "Y";
				this.context.put("tarikhDaftarFail", getParam("txdtarikhdaftarfail"));

			}else{ // default click button [Carian]
				list = getIHTP().carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txdtarikhdaftarfail"), getParam("txdTarikhTerima")
						, getParam("txtNamaPemohon")
						, idNegeri
						, idKementerian, idAgensi
						, "", "", "",""
						, idSuburusan,idStatus);
		    }
			this.context.put("SenaraiFail", list);
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
			setupPage(session,action,list);


        } else {
        	myLog.info("default page");
        	myLog.info("idKementerian="+idKementerian);
        	myLog.info("idAgensi="+idAgensi);
        	myLog.info("idSuburusan="+idSuburusan);
        	//GO TO LIST FAIL PAJAKAN
			list = new Vector<Hashtable<String, String>>();
        	vm = PATH+"index.jsp";

           	idNegeri = getParam("socnegericarian");
    		if (idNegeri.equals("-1") || idNegeri.trim().length() == 0){
    			idNegeri = "99999";
    		}
        	idKementerian = getParam("sockementeriancarian");
    		if (idKementerian.equals("-1") || idKementerian.trim().length() == 0){
    			idKementerian = "99999";
    		}
        	idAgensi = getParam("socagensicarian");
    		if (idAgensi.equals("-1") || idAgensi.trim().length() == 0 ){
    			idAgensi = "99999";
    		}
         	idSuburusan = getParam("socsuburusancarian");
    		if (idSuburusan.equals("-1") || idSuburusan.trim().length() == 0){
    			idSuburusan = "99999";
    		}
    		idStatus = getParam("socstatuscarian");
    		if (idStatus.equals("") || idStatus.trim().length() == 0){
    			idStatus = "99999";
    		}
			this.context.put("socNegeri",HTML.SelectNegeri("socnegericarian", Long.parseLong(idNegeri), "", " onChange=\"doChangeKementerian();\""));
			this.context.put("socKementerian",HTML.SelectKementerian("sockementeriancarian",Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
			this.context.put("socAgensi",HTML.SelectAgensiByKementerian("socagensicarian", idKementerian ,Long.parseLong(idAgensi), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
			this.context.put("selectSuburusan",UtilHTML.selectSuburusanLaporan("3", "socsuburusancarian" ,idSuburusan, "", ""));
			this.context.put("selectStatus",UtilHTML.selectStatusByModule("socstatuscarian", Long.parseLong(idStatus), "", "3"));
			if(flagAdvSearch.equals("Y")){
				list = getIHTP().carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txdtarikhdaftarfail"), getParam("txdTarikhTerima")
					, getParam("txtNamaPemohon")
					, idNegeri
					, idKementerian, idAgensi
					, "", noHakmilikC, "",noLotC
					, idSuburusan,idStatus);

			}else{ /**Apabila pilih Negeri, Kementerian atau Agensi*/
				myLog.info("default XY");
//				list = getIHTP().carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txdTarikhTerima")
//					, getParam("txtNamaPemohon")
//					, idNegeri
//					, idKementerian, idAgensi);
				list = getIHTPFail().getSenaraiFail(userID,getParam("txtNoFail"), getParam("txtTajukFail")
					,idKementerian, idAgensi
					,"-1","-1","-1"
					,"3","",""
					,"-1","-1",false);

			}
			if (mode.indexOf("change") != -1) {
				myLog.info("change");
				list = getIHTP().carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txdTarikhTerima")
						, getParam("txtNamaPemohon")
						, idNegeri
						, idKementerian, idAgensi);
		    	flagAdvSearch = "Y";

		    }
			//actionPajakan = "carian";
			this.context.put("SenaraiFail", list);
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
			setupPage(session,action,list);

        }

        //SET DEFAULT PARAM
		//Carian
		this.context.put("txtTajukFailCarian", getParam("txtTajukFail"));
		this.context.put("noHakmilik", noHakmilikC);
		this.context.put("noLot", noLotC);
		this.context.put("actionPajakan", actionPajakan);

		//SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idStatus", idStatus);
		this.context.put("subUrusan", subUrusan);
	    // 19/08/2010
    	this.context.put("flagAdvSearch",flagAdvSearch);
		//log.info(vm);
    	//Pendaftaran
    	this.context.put("mode", mode);
		this.context.put("selectedTab", selectedTab);
		//MJM
        this.context.put("selectedTabLower", selectedTabLower);
        this.context.put("idUlasanTeknikal", idUlasanTeknikal);
        this.context.put("idUlasanNilai", idUlasanNilai);
        this.context.put("idDraf", iDeraf);
        this.context.put("idUlasanKJP", idUlasanKJP);
        this.context.put("idUlasanSPHP", idUlasanSPHP);
        //Bayaran
        this.context.put("idBayaran", idBayaran);
		//Tindakan
        this.context.put("idSusulan", idSusulan);
		this.context.put("page", page_);
		getPermohonanInfo();
		if(getParam("idPermohonan").equals("")){
			getPermohonanInfo(idPermohonan);
		}
		this.context.put("modul", this.className);

		return vm;

	}

	public void getTindakan(String mode) throws Exception{
    	Vector<Hashtable <String,String>> senaraiTindakan = null;
		Hashtable<String, String> hashData = null;
    	String tarikhTindakan = "";
    	String catatan = "";
    	String readOnly = "";
    	String classDis = "";
		try{

			if (mode.equalsIgnoreCase("baharu")){
				this.context.put("readOnly", "");
		       	this.context.put("classDis", "");

			} else if (mode.equalsIgnoreCase("papar")){
				hashData = new Hashtable<String, String>();
				hashData = getISusulan().getMaklumat(idPermohonan, idSusulan);
				tarikhTindakan = hashData.get("tarikh");
				catatan = hashData.get("keterangan");

				readOnly = "readOnly";
				classDis = "disabled";

			}else if(mode.equalsIgnoreCase("kemaskini")){
	   			this.context.put("readOnly", "");
	   			this.context.put("classDis", "");
				hashData = new Hashtable<String, String>();
				hashData = getISusulan().getMaklumat(idPermohonan, idSusulan);
				tarikhTindakan = hashData.get("tarikh");
				catatan = hashData.get("keterangan");

			}else{
				senaraiTindakan = new Vector<Hashtable <String,String>>();
			    senaraiTindakan = getISusulan().getMaklumat(idPermohonan);
			    context.put("senaraiTindakan",senaraiTindakan);

			}
			context.put("tarikhTindakan", tarikhTindakan);
			context.put("catatan", catatan);
   			this.context.put("readOnly", readOnly);
   			this.context.put("classDis", classDis);

		} catch(Exception e){
			e.printStackTrace();
		}

	}

	private void setContext(String idNegeri,String idKementerian,String idAgensi,String idSuburusan,String idStatusTanah,String idJenisFail)
		throws Exception {
		//this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", ""));
		this.context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(idNegeri), "", ""));
		this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
		this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), "", ""));
		this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(idSuburusan), "", ""));
		//this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), "", ""));
		this.context.put("selectStatusTanah",UtilHTML.SelectJenisTanah("socStatusTanah",Long.parseLong(idStatusTanah),"",JENISTANAH));
		this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), "", ""));

	}
	//Fungsi2 terlibat dengan skrin senarai fail
	// set values untuk carian
	/*private void getCarianValues(){
		idNegeri = getParam("socnegericarian");
		if (idNegeri.equals("-1") || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
    	idKementerian = getParam("sockementeriancarian");
		if (idKementerian.equals("-1") || idKementerian.trim().length() == 0){
			idKementerian = "99999";
		}
    	idAgensi = getParam("socagensicarian");
		if (idAgensi.equals("-1") || idAgensi.trim().length() == 0 ){
			idAgensi = "99999";
		}
     	idSuburusan = getParam("socsuburusancarian");
		if (idSuburusan.equals("-1") || idSuburusan.trim().length() == 0){
			idSuburusan = "99999";
		}

		String nama = getParam("txtNama");
		String noRujukan = getParam("txtnorujukan");
		String alamat1 = getParam("txtAlamat1");
		String alamat2 = getParam("txtAlamat2");
		String alamat3 = getParam("txtAlamat3");
		String poskod = getParam("txtPoskod");
		String idNegeri = getParam("socNegeri");
		String idBandar = getParam("socDaerah");
		String tel = getParam("txtNoTelefon");
		String fax = getParam("txtNoFax");
		String emel = getParam("txtemail");
		//permohonan = new Permohonan();
		//permohonan.setIdPermohonan(idPermohonan);
		pemohon = new Pemohon();
		//pemohon.setIdPemohon(idPenjual);
		//pemohon.setFlagPemilik(flag);
		pemohon.setNama(nama);//pemohon.getNama()
		pemohon.setNoPemohon(noRujukan);
		pemohon.setAlamat1(alamat1);
		pemohon.setAlamat2(alamat2);
		pemohon.setAlamat3(alamat3);
		//pemohon.setPermohonan(permohonan);
		pemohon.setPoskod(poskod);
		pemohon.setIdNegeri(idNegeri);
		pemohon.setIdDaerah(idBandar);
		pemohon.setTel(tel);
		pemohon.setFax(fax);
		pemohon.setEmel(emel);
		context.put("pemohon", pemohon);

	}*/
	// Pendaftaran
	  private void saveUpdateFail(String idFail,String idSubUrusan,HttpSession session) throws Exception {
	    Hashtable<String,String> hash = new Hashtable<String,String>();
	    hash.put("noFailKJP", getParam("txtNoFailKJP"));
		hash.put("tarikhSuratKJP", getParam("tarikhSuratKJP"));
		hash.put("noFailLain", getParam("txtNoFailLain"));
		hash.put("tarikhAgihan", getParam("tarikhAgihan"));
		hash.put("tajuk", getParam("txtTajuk"));
		hash.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon"));
		hash.put("userId", userID);
		logicp.saveUpdateFail(idFail, idSubUrusan, hash, session);

	}

	private void getPermohonanInfo()throws Exception{
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
		htpPermohonan = getIHTP().findPermohonan(idPermohonan,idHtpPermohonan);
		context.put("htpPermohonan", htpPermohonan);
	}

	private void getPermohonanInfo(String idPermohonan )throws Exception{
		String idHtpPermohonan = getParam("idHtpPermohonan");
		htpPermohonan = getIHTP().findPermohonan(idPermohonan,idHtpPermohonan);
		context.put("htpPermohonan", htpPermohonan);
	}

	public void MaklumatPermohonanView(String mode) throws Exception{
		try {
			if (mode.equalsIgnoreCase("view")){
				this.context.put("readOnly", "readOnly");
	        	this.context.put("classDis", "disabled");
	        	this.context.put("inputTextClass", "disabled");

	        //mode = update
	        }else if(mode.equalsIgnoreCase("update")){
	        	this.context.put("readOnly", "");
	        	this.context.put("classDis", "");
	    	}

		} catch (Exception e){
			e.printStackTrace();
	    }

	}
	//MJM
	private void saveMemo(String idPermohonan, HttpSession session) throws Exception {
	    Hashtable<String,String> hash = new Hashtable<String,String>();
		hash.put("tarikhTHPTP", getParam("txdTHPTP"));
		hash.put("tarikhTTPTP", getParam("txdTTPTP"));
		hash.put("tarikhTHTUP", getParam("txdTHTUP"));
		hash.put("tarikhTMJM", getParam("txdTMJM"));
		hash.put("tarikhTTK", getParam("txdTTK"));
		hash.put("noMemo", getParam("txtNoMemorandum"));
		hash.put("keputusan", getParam("txtKeputusan"));
		hash.put("tindakanLanjut", getParam("txtKeterangan"));
		hash.put("tarikhHantarPemohon", getParam("txdTMKPemohon"));
		hash.put("idFail", idFail_);
		hash.put("idSubUrusan", idSubUrusan);
		if(logicmjm.isMaklumatMemorandumJemaahMenteri(idPermohonan))
			 AuditTrail.logActivity("1", "3", this, session, "UPD", "MEMORANDUM  [NO. MEMO:"+getParam("txtNoMemorandum")+"] DIKEMASKINI ");
		else
			AuditTrail.logActivity("1", "3", this, session, "INS", "MEMORANDUM   [NO. MEMO:"+getParam("txtNoMemorandum")+"] DITAMBAH ");

		logicmjm.updateMemo(idPermohonan, hash, session);

		kemaskiniStatusPermohonan(idPermohonan,idSubUrusan,getParam("txtKeputusan"));

	}

	private void kemaskiniStatusPermohonan(String keputusan,String idPermohonan,String idSubUrusan)
		throws Exception {
		String langkah = "";
		if(keputusan.equals("S")|| keputusan.equals("L")){
			langkah = "7";
		}else if(keputusan.equals("TS") || keputusan.equals("TL")){
			langkah = "6";
		}
		if(!langkah.equals(""))
			getStatus().kemaskiniStatusPermohonan(idPermohonan,idSubUrusan,langkah,userID);

	}
	//UPLOAD FILE MJM
	private void uploadFiles(HttpSession session,String idFail) throws Exception {
		myLog.info("uploadFiles");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
//			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//			Enumeration<?> allparam = request.getParameterNames();
//			String name="";
//			String value_="";
//			for (; allparam.hasMoreElements(); ) {
//				// Get the name of the request parameter
//				name = (String)allparam.nextElement();
//				// Get the value of the request parameter
//				value_ = request.getParameter(name);
//
//			}
		List<?> items = upload.parseRequest(request);
		Iterator<?> itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem)itr.next();
			if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
				String idDokumen = simpanDokumen(item.getName(),idFail);
				saveData(idDokumen,item);

			}
		}

	}

		private String simpanDokumen(String namaFail,String idFail) throws Exception {
			Hashtable<String,String> h = new Hashtable<String,String>();
			h.put("flag_Dokumen","1");
			h.put("tajuk_Dokumen",namaFail);
			h.put("namaPengirim", "-");
			h.put("id_Fail",idFail);
			h.put("id_Masuk",userID);
			return getIOnline().addMasuk(h);

		}

		private void saveData(String idDokumen,FileItem item) throws Exception {
			Db db = null;
			try {
				db = new Db();
				long id_Lampiran = DB.getNextID("TBLPFDRUJLAMPIRAN_SEQ");
				Connection con = db.getConnection();
				con.setAutoCommit(false);
				PreparedStatement ps = con.prepareStatement("" +
						"insert into TBLPFDRUJLAMPIRAN " +
			        	"(id_lampiran,id_dokumen,nama_fail,jenis_mime,content,id_masuk,tarikh_masuk,id_kemaskini,tarikh_kemaskini) " +
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

		private void saveUlasanKJP(String idPermohonan, HttpSession session) throws Exception {
			 Hashtable<String,String> hash = new Hashtable<String,String>();
			 hash.put("noRujukanKJP", getParam("txtNoRujukanKJP"));
			 hash.put("tarikhHantar", getParam("txdTarikhHantarKJP"));
			 hash.put("tarikhTerima", getParam("txdTarikhTerimaKJP"));
			 hash.put("ulasan", getParam("txtUlasanKJP"));
			 hash.put("status", getParam("socKeputusan"));
			 hash.put("idSubUrusan", idSubUrusan);
			 hash.put("idFail", idFail_);
			 logicmjm.saveUlasanKJP(idPermohonan, hash, session);
			 kemaskiniStatusPermohonan(idPermohonan,idSubUrusan,getParam("socKeputusan"));

		}

		private void saveUpdateUlasanKJP(String idUlasanKJP, HttpSession session) throws Exception {
			Hashtable<String,String> hash = new Hashtable<String,String>();
			hash.put("noRujukanKJP", getParam("txtNoRujukanKJP"));
			hash.put("tarikhHantar", getParam("txdTarikhHantarKJP"));
			hash.put("tarikhTerima", getParam("txdTarikhTerimaKJP"));
			hash.put("ulasan", getParam("txtUlasanKJP"));
			hash.put("status", getParam("socKeputusan"));
			hash.put("idSubUrusan", idSubUrusan);
			hash.put("idFail", idFail_);
			hash.put("idPermohonan", idPermohonan);	//idPermohonan_
			logicmjm.updateUlasanKJP(idUlasanKJP, hash, session);
			kemaskiniStatusPermohonan(idPermohonan,idSubUrusan,getParam("socKeputusan"));//idPermohonan_

		}

		private void saveJPPH(String idPermohonan, HttpSession session) throws Exception {
			Hashtable<String,String> hash = new Hashtable<String,String>();
			hash.put("noRujukan", getParam("txtNoRujJPPH"));
			hash.put("tarikhHantar", getParam("txdTarikhHantarJPPH"));
			hash.put("tarikhTerima", getParam("txdTarikhTerimaJPPH"));
			hash.put("tahunNilaian", getParam("txtTahunNilaian"));
			hash.put("nilaian", getParam("txtNilaiTanah"));
			hash.put("syor", getParam("txtSyorBayaran"));
			hash.put("ulasan", getParam("txtKeteranganJPPH"));
			logicmjm.saveJPPH(idPermohonan, hash, session);

		}

		private void saveUpdateJPPH(String idUlasanTeknikal, String idUlasanNilai, HttpSession session) throws Exception {
			Hashtable<String,String> hash = new Hashtable<String,String>();
			hash.put("noRujukan", getParam("txtNoRujJPPH"));
			hash.put("tarikhHantar", getParam("txdTarikhHantarJPPH"));
			hash.put("tarikhTerima", getParam("txdTarikhTerimaJPPH"));
			hash.put("tahunNilaian", getParam("txtTahunNilaian"));
			hash.put("nilaian", getParam("txtNilaiTanah"));
			hash.put("syor", getParam("txtSyorBayaran"));
			hash.put("ulasan", getParam("txtKeteranganJPPH"));
			logicmjm.saveUpdateJPPH(idUlasanTeknikal, idUlasanNilai, hash, session);

		}

		private void saveSPHP(String idPermohonan, HttpSession session) throws Exception {
			Hashtable<String,String> hash = new Hashtable<String,String>();
			hash.put("noRujukan", getParam("txtNoRujukanSPHP"));
			hash.put("tarikhHantar", getParam("txdTarikhHantarSPHP"));
			hash.put("tarikhTerima", getParam("txdTarikhTerimaSPHP"));
			hash.put("ulasan", getParam("txtUlasanSPHP"));
			hash.put("status", getParam("socKeputusan"));
			logicmjm.saveSPHP(idPermohonan, hash, session);

		}

		private void saveUpdateSPHP(String idUlasanSPHP, HttpSession session) throws Exception {
			Hashtable<String,String> hash = new Hashtable<String,String>();
			hash.put("noRujukan", getParam("txtNoRujukanSPHP"));
			hash.put("tarikhHantar", getParam("txdTarikhHantarSPHP"));
			hash.put("tarikhTerima", getParam("txdTarikhTerimaSPHP"));
			hash.put("ulasan", getParam("txtUlasanSPHP"));
			hash.put("status", getParam("socKeputusan"));
			logicmjm.saveUpdateSPHP(idUlasanSPHP, hash, session);

		}

		private String simpanDraf(String idPermohonan, HttpSession session) throws Exception {
			idPermohonan = getParam("idPermohonan");
	    	String tarikhHantarDraf = getParam("txdTarikhHantarDraf");
	    	String tarikhTerimaDraf = getParam("txdTarikhTerimaDraf");
		   	String keteranganDraf = getParam("txtKeteranganDraf");
		   	Hashtable<String,String> hash = new Hashtable<String,String>();
			hash.put("tarikhHantar", tarikhHantarDraf);
			hash.put("tarikhTerima", tarikhTerimaDraf);
			hash.put("ulasan", keteranganDraf);
			hash.put("id_permohonan", idPermohonan);
			return logicmjm.simpanDraf(idPermohonan, hash, session);

		}

		private void saveUpdateDraf(String idDraf, HttpSession session) throws Exception {
			Hashtable<String,String> hash = new Hashtable<String,String>();
	    	hash.put("tarikhHantar", getParam("txdTarikhHantarDraf"));
	    	hash.put("tarikhTerima", getParam("txdTarikhTerimaDraf"));
			hash.put("ulasan", getParam("txtKeteranganDraf"));
			hash.put("userId", userID);
			logicmjm.saveUpdateDraf(idDraf, hash);

		}

		private void downloadDraf(String idPermohonan,HttpSession session) throws Exception {
			//System.out.println("=======Download file==========" +idPermohonan);
			uploadFilesNew();

		}

		private void uploadFilesNew() throws Exception {
			DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    List<?> items = upload.parseRequest(request);
		    Iterator<?> itr = items.iterator();
		    while (itr.hasNext()) {
		    	FileItem item = (FileItem)itr.next();
		    	//log.info("ContentType :" + item.getContentType());
		    	if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			    	  saveData(item);
			      }
		    }

		}

		private void saveData(FileItem item) throws Exception {
			HttpSession session = request.getSession();
	  		Db db = null;
	  		Date TT = sdf.parse(getParam("txdTarikhTerimaDraf"));
	  		Date TH = sdf.parse(getParam("txdTarikhTerimaDraf"));
	  		java.sql.Date TH2 = new java.sql.Date(TH.getTime());
		    java.sql.Date TT2 = new java.sql.Date(TT.getTime());

		    try {
		    	long id_derafperjanjian = DB.getNextID("TBLHTPDERAFPERJANJIAN_SEQ");
		    	db = new Db();

		    	Connection con = db.getConnection();
		    	con.setAutoCommit(false);
			        	//SQLRenderer r = new SQLRenderer();
		    	PreparedStatement ps = con.prepareStatement("" +
		    			"insert into TBLHTPDERAFPERJANJIAN " +
			    		"(id_derafperjanjian,id_permohonan,nama_fail,mimetype,content_derafperjanjian,tarikh_hantarptp,tarikh_terimaptp,ulasan_seksyen,id_masuk,id_kemaskini,jenis_dokumen) " +
		    			"values(?,?,?,?,?,?,?,?,?,?,?)");
		    	ps.setLong(1, id_derafperjanjian);
		    	ps.setNString(2, getParam("idPermohonan"));
	        	ps.setString(3,item.getName());
	        	ps.setString(4,item.getContentType());
		    	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
		    	ps.setDate(6, TH2);
		    	ps.setDate(7, TT2);
		    	ps.setString(8, getParam("txtKeteranganDraf"));
		    	ps.setString(9,(String) session.getAttribute("_ekptg_user_id"));
		    	ps.setString(10,(String) session.getAttribute("_ekptg_user_id"));
		    	ps.setString(11, "M");
		    	ps.executeUpdate();
		    	con.commit();

		    } finally {
		    	if (db != null) db.close();
		    }

		}

	public void PemohonPajakanView(String mode) throws Exception{

    	String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}
		Vector<Hashtable<String, String>> beanMaklumatPemohon = null;

    	try {

    		if (mode.equalsIgnoreCase("view")){

    			beanMaklumatPemohon = new Vector<Hashtable<String, String>>();
        		beanMaklumatPemohon = logicmjm.getPemohonPajakan();
        		this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
        		if(beanMaklumatPemohon.size()!=0){
	        		Hashtable<String, String> hashPermohonDB = (Hashtable<String, String>) beanMaklumatPemohon.get(0);
	    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong((String)hashPermohonDB.get("idNegeri")), " class='disabled' "));
	        		//this.context.put("selectDaerah", HTML.SelectDaerahByNegeri((String)hashPermohonDB.get("idNegeri"), "socDaerah", Long.parseLong((String)hashPermohonDB.get("idDaerah")), " disabled='disabled' class='disabled' "));
					this.context.put("selectDaerah",HTML.SelectBandarByNegeri(String.valueOf(hashPermohonDB.get("idNegeri")), "socDaerah",Long.parseLong(String.valueOf(hashPermohonDB.get("idBandar")))," class='disabled'"));

	        		this.context.put("readOnly", "readOnly");
	        		this.context.put("classDis", "disabled");

        		}
    		}
    		//mode = update
    		else if(mode.equalsIgnoreCase("update")){

    			Hashtable<String, String> hashPermohon;
    			beanMaklumatPemohon = new Vector<Hashtable<String, String>>();
    			//MAKLUMAT PEMOHON
    			hashPermohon = new Hashtable<String, String>();
    			hashPermohon.put("nama", getParam("txtNama") == null ? "" : getParam("txtNama"));
    			hashPermohon.put("alamat1", getParam("txtAlamat1") == null ? "" : getParam("txtAlamat1"));
    			hashPermohon.put("alamat2", getParam("txtAlamat2") == null ? "" : getParam("txtAlamat2"));
    			hashPermohon.put("alamat3", getParam("txtAlamat3") == null ? "" : getParam("txtAlamat3"));
    			hashPermohon.put("poskod", getParam("txtPoskod") == null ? "" : getParam("txtPoskod"));
    			hashPermohon.put("tel", getParam("txtNoTelefon") == null ? "" : getParam("txtNoTelefon"));
    			hashPermohon.put("fax", getParam("txtNoFax") == null ? "" : getParam("txtNoFax"));
    			hashPermohon.put("noPemohon", getParam("txtnorujukan") == null ? "" : getParam("txtnorujukan"));
    			hashPermohon.put("emel", getParam("txtemail") == null ? "" : getParam("txtemail"));
    			beanMaklumatPemohon.addElement(hashPermohon);
    			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
//    			myLog.info("N="+getParam("socNegeri"));
//    			myLog.info("D="+getParam("socDaerah"));
    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", "onchange='doChangeNegeri()'"));
        		//this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Long.parseLong(idDaerah), ""));
				this.context.put("selectDaerah",HTML.SelectBandarByNegeri(idNegeri, "socDaerah",Long.parseLong(idDaerah),""));

    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}

    	} catch (Exception e){
    		e.printStackTrace();
    	}

    }
	public void PemohonPajakanView(String mode,Vector<Hashtable<String, String>> beanMaklumatPemohon) throws Exception{
    	String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}
    	try {
    		if (mode.equalsIgnoreCase("view")){
    			//beanMaklumatPemohon = new Vector();
        		//beanMaklumatPemohon = logic.getPemohonPajakan();
        		this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
        		if(beanMaklumatPemohon.size()!=0){
	        		Hashtable<String, String> hashPermohonDB = (Hashtable<String, String>) beanMaklumatPemohon.get(0);
	    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong((String)hashPermohonDB.get("idNegeri")), " class='disabled' "));
	        		//this.context.put("selectDaerah", HTML.SelectDaerahByNegeri((String)hashPermohonDB.get("idNegeri"), "socDaerah", Long.parseLong((String)hashPermohonDB.get("idDaerah")), " disabled='disabled' class='disabled' "));
					this.context.put("selectDaerah",HTML.SelectBandarByNegeri(String.valueOf(hashPermohonDB.get("idNegeri")), "socDaerah",Long.parseLong(String.valueOf(hashPermohonDB.get("idBandar")))," class='disabled'"));
	        		this.context.put("readOnly", "readOnly");
	        		this.context.put("classDis", "disabled");

        		}else{
        			getPemohonValues();
        			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), " onchange='doChangeNegeri()' "));
					this.context.put("selectDaerah",HTML.SelectBandarByNegeri(String.valueOf(idNegeri), "socDaerah",Long.parseLong(idDaerah)," "));

        		}

    		}else if(mode.equalsIgnoreCase("update")){	//mode = update
    			Hashtable<String, String> hashPermohon;
    			beanMaklumatPemohon = new Vector<Hashtable<String, String>>();
    			//MAKLUMAT PEMOHON
    			hashPermohon = new Hashtable<String, String>();
    			hashPermohon.put("nama", getParam("txtNama") == null ? "" : getParam("txtNama"));
    			hashPermohon.put("alamat1", getParam("txtAlamat1") == null ? "" : getParam("txtAlamat1"));
    			hashPermohon.put("alamat2", getParam("txtAlamat2") == null ? "" : getParam("txtAlamat2"));
    			hashPermohon.put("alamat3", getParam("txtAlamat3") == null ? "" : getParam("txtAlamat3"));
    			hashPermohon.put("poskod", getParam("txtPoskod") == null ? "" : getParam("txtPoskod"));
    			hashPermohon.put("tel", getParam("txtNoTelefon") == null ? "" : getParam("txtNoTelefon"));
    			hashPermohon.put("fax", getParam("txtNoFax") == null ? "" : getParam("txtNoFax"));
    			hashPermohon.put("noPemohon", getParam("txtnorujukan") == null ? "" : getParam("txtnorujukan"));
    			hashPermohon.put("emel", getParam("txtemail") == null ? "" : getParam("txtemail"));
    			beanMaklumatPemohon.addElement(hashPermohon);
    			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
//    			myLog.info("N="+getParam("socNegeri"));
//    			myLog.info("D="+getParam("socDaerah"));
    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", "onchange='doChangeNegeri()'"));
        		//this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Long.parseLong(idDaerah), ""));
				this.context.put("selectDaerah",HTML.SelectBandarByNegeri(idNegeri, "socDaerah",Long.parseLong(idDaerah),""));
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");

    		}

    	} catch (Exception e){
    		e.printStackTrace();

    	}

    }

	 public void MemorandumJemaahMenteriView(String mode,Tblhtpjemaahmenteri beanMJM) throws Exception{
	        //public void MemorandumJemaahMenteriView(String mode,Vector beanMJM) throws Exception{
		 try{
	    		String tarikhHantarPTP = "";
	    		String tarikhTerimaPTP ="";
	    		String tarikhHantarKSU ="";
	    		String tarikhMesyuaratMJM = "";
	    		String tarikhTerimaKeputusan = "";
	    		String keputusan = "";
	    		String noMemorandum = "-";
	    		String tindakanLanjut = "-";
	    		String tarikhMaklumanKeputusan = "";
	    		//Vector senaraiLampiran = null;
	    		if (mode.equalsIgnoreCase("view")){
//	    			myLog.info("beanMJM="+beanMJM);
//	    			myLog.info("No. Memo"+beanMJM.getNoMemorandum());
	    			//myLog.info(beanMJM);
	        		//this.context.put("BeanMJM", beanMJM);
	    			//tarikhHantarPTP = tarikhSemasa;
	    			tarikhMesyuaratMJM = tarikhSemasa;
	    			tarikhTerimaKeputusan = tarikhSemasa;
	    			tarikhSemasa = "";
	         		this.context.put("readOnly", "");
	        		this.context.put("classDis", "");
	        		this.context.put("Style2", "");
	        		if(beanMJM.getNoMemorandum()!=null){
	        			tarikhHantarPTP = beanMJM.getTarikhHantarDasarFormat();
	        			tarikhTerimaPTP = beanMJM.getTarikhTerimaFormat();
	        			tarikhHantarKSU = beanMJM.getTarikhHantarKsuFormat();
	    				//myLog.info(beanMJM.getTarikhMsyrtJemaahFormat());
	        			tarikhMesyuaratMJM = beanMJM.getTarikhMesyuaratJemaahFormat();
	    				//myLog.info(beanMJM.getTarikhMsyrtJemaahFormat());
	    				tarikhTerimaKeputusan = beanMJM.getTarikhKeputusanFormat();
	    				keputusan = beanMJM.getStatusKeputusan();
	    				noMemorandum = beanMJM.getNoMemorandum();
	    				tindakanLanjut = beanMJM.getTindakanLanjut();
	    				tarikhMaklumanKeputusan = beanMJM.getTarikhHantarPemohonFormat();
	    				this.context.put("readOnly", "readOnly");
	            		this.context.put("classDis", "disabled");
		        		this.context.put("Style2", "none");

	    			}
	    			Vector<Hashtable<String, String>> dokumens = getIOnline().getLampiranByPermohonan(idPermohonan);
	    			context.put("senaraidokumen", dokumens);


	    		}else if(mode.equalsIgnoreCase("update")){	//mode = update
	           		if(beanMJM.getNoMemorandum()!=null){
	         			tarikhHantarPTP = beanMJM.getTarikhHantarDasarFormat();
	        			tarikhTerimaPTP = beanMJM.getTarikhTerimaFormat();
	        			tarikhHantarKSU = beanMJM.getTarikhHantarKsuFormat();
	        			tarikhMesyuaratMJM = beanMJM.getTarikhMesyuaratJemaahFormat();
	    				tarikhTerimaKeputusan = beanMJM.getTarikhKeputusanFormat();
	    				keputusan = beanMJM.getStatusKeputusan();
	    				noMemorandum = beanMJM.getNoMemorandum();
	    				tindakanLanjut = beanMJM.getTindakanLanjut();
	    				tarikhMaklumanKeputusan = beanMJM.getTarikhHantarPemohonFormat();

	           		}
	    			Vector<Hashtable<String, String>> dokumens = getIOnline().getLampiranByPermohonan(idPermohonan);
	    			context.put("senaraidokumen", dokumens);
	    			this.context.put("readOnly", "");
	        		this.context.put("classDis", "");
	        		this.context.put("Style2", "");

	    		}

	    		//this.context.put("tarikhSemasa", tarikhSemasa);
	    		this.context.put("BeanMJM", beanMJM);
	    		this.context.put("tarikhHantarPTP", tarikhHantarPTP);
	    		this.context.put("tarikhTerimaPTP", tarikhTerimaPTP);
	    		this.context.put("tarikhHantarKSU", tarikhHantarKSU);
	    		this.context.put("tarikhMesyuaratMJM", tarikhMesyuaratMJM);
	    		this.context.put("tarikhTerimaKeputusan", tarikhTerimaKeputusan);
	    		this.context.put("keputusan", keputusan);
	    		this.context.put("noMemorandum", noMemorandum);
	    		this.context.put("tindakanLanjut", tindakanLanjut);
	    		this.context.put("tarikhMaklumanKeputusan",tarikhMaklumanKeputusan);

	    	} catch(Exception e){
	    		e.printStackTrace();

	    	}

	    }
	private void getPemohonValues(){
		String nama = getParam("txtNama");
		String noRujukan = getParam("txtnorujukan");
		String alamat1 = getParam("txtAlamat1");
		String alamat2 = getParam("txtAlamat2");
		String alamat3 = getParam("txtAlamat3");
		String poskod = getParam("txtPoskod");
		String idNegeri = getParam("socNegeri");
		String idBandar = getParam("socDaerah");
		String tel = getParam("txtNoTelefon");
		String fax = getParam("txtNoFax");
		String emel = getParam("txtemail");
		//permohonan = new Permohonan();
		//permohonan.setIdPermohonan(idPermohonan);
		pemohon = new Pemohon();
		//pemohon.setIdPemohon(idPenjual);
		//pemohon.setFlagPemilik(flag);
		pemohon.setNama(nama);//pemohon.getNama()
		pemohon.setNoPemohon(noRujukan);
		pemohon.setAlamat1(alamat1);
		pemohon.setAlamat2(alamat2);
		pemohon.setAlamat3(alamat3);
		//pemohon.setPermohonan(permohonan);
		pemohon.setPoskod(poskod);
		pemohon.setIdNegeri(idNegeri);
		pemohon.setIdDaerah(idBandar);
		pemohon.setTel(tel);
		pemohon.setFax(fax);
		pemohon.setEmel(emel);
		context.put("pemohon", pemohon);

	}
	/** MJM */
	   private void savePemohon(String idPermohonan, HttpSession session) throws Exception {
	    	Hashtable<String,String> hash = new Hashtable<String,String>();
			hash.put("nama", getParam("txtNama"));
			hash.put("alamat1", getParam("txtAlamat1"));
			hash.put("alamat2", getParam("txtAlamat2"));
			hash.put("alamat3", getParam("txtAlamat3"));
			hash.put("poskod", getParam("txtPoskod"));
			hash.put("idDaerah", getParam("socDaerah"));
			hash.put("idNegeri", getParam("socNegeri"));
			hash.put("noTel", getParam("txtNoTelefon"));
			hash.put("noFax", getParam("txtNoFax"));
			//Kemaskini pada 24/12/2010-mrosli
			hash.put("idBandar", getParam("socDaerah"));
			hash.put("noRujukan", getParam("txtnorujukan"));
			hash.put("email", getParam("txtemail"));
			logicmjm.updatePemohon(idPermohonan, hash, session);

		}

    public void UlasanKJPView(String mode, String idUlasanKJP) throws Exception{
    	PajakanUlasan ulasan = null;
    	try{
    		//Vector beanUlasanKJP = null;
       		if (mode.equalsIgnoreCase("newKJP")){
       			Date today = new Date();
//      			beanUlasanKJP = new Vector();
//        		Hashtable hashUlasanKJP = new Hashtable();
//        		hashUlasanKJP.put("noRujukan", getParam("txtNoRujukanKJP") == null ? "" : getParam("txtNoRujukanKJP"));
//        		myLog.info("newKJP:Tarikh Hantar="+sdf.format(today));
//        		hashUlasanKJP.put("tarikhHantar", getParam("txdTarikhHantarKJP") == null ? String.valueOf(sdf.format(today)) : getParam("txdTarikhHantarKJP"));
//        		hashUlasanKJP.put("tarikhTerima", getParam("txdTarikhTerimaKJP") == null ? "" : getParam("txdTarikhTerimaKJP"));
//        		hashUlasanKJP.put("ulasanKJP", getParam("txtUlasanKJP") == null ? "" : getParam("txtUlasanKJP"));
//        		beanUlasanKJP.addElement(hashUlasanKJP);
//    			this.context.put("BeanUlasanKJP", beanUlasanKJP);
    			ulasan = new PajakanUlasan();
    			ulasan.setNo(getParam("txtNoRujukanKJP") == null ? "" : getParam("txtNoRujukanKJP"));
    			ulasan.setTarikhHantarTxt(getParam("txdTarikhHantarKJP").equals("")? sdf.format(today) : getParam("txdTarikhHantarKJP"));
    			ulasan.setTarikhTerima(getParam("txdTarikhTerimaKJP") == null ? "" : getParam("txdTarikhTerimaKJP"));
    			ulasan.setKeputusan(getParam("socKeputusan") == null ? "" : getParam("socKeputusan"));
    			ulasan.setUlasan(getParam("txtUlasanKJP") == null ? "" : getParam("txtUlasanKJP"));
    			this.context.put("readOnly", "");
            	this.context.put("classDis", "");

       		} else if (mode.equalsIgnoreCase("viewKJP")){
//       			beanUlasanKJP = new Vector();
//        		logic.setMaklumatUlasanKJP(idUlasanKJP);
//        		beanUlasanKJP = logic.getUlasanKJP();
//            	this.context.put("BeanUlasanKJP", beanUlasanKJP);
       			ulasan = getIPMJM().getMaklumatUlasan(idUlasanKJP);
            	this.context.put("readOnly", "readOnly");
            	this.context.put("classDis", "disabled");

            //mode = update
        	}else if(mode.equalsIgnoreCase("updateKJP")){
       			ulasan = getIPMJM().getMaklumatUlasan(idUlasanKJP);
       			this.context.put("readOnly", "");
            	this.context.put("classDis", "");

        	}
    		this.context.put("ulasan", ulasan);


    	} catch(Exception e){
    		e.printStackTrace();
    	}

    }

    public void UlasanJPPHView(String mode, String idUlasanTeknikal) throws Exception{
    	try{
    		Vector<Hashtable<String, String>> beanUlasanJPPH = null;
    		if (mode.equalsIgnoreCase("newJPPH")){
    			beanUlasanJPPH = new Vector<Hashtable<String, String>>();
    			Hashtable<String, String> hashUlasanJPPH = new Hashtable<String, String>();
    			hashUlasanJPPH.put("noRujukan", getParam("txtNoRujJPPH") == null ? "" : getParam("txtNoRujJPPH"));
    			hashUlasanJPPH.put("tarikhHantar", getParam("txdTarikhHantarJPPH") == null||getParam("txdTarikhHantarJPPH").equals("") ?sdf.format(new Date()) : getParam("txdTarikhHantarJPPH"));
    			hashUlasanJPPH.put("tarikhTerima", getParam("txdTarikhTerimaJPPH") == null ? "" : getParam("txdTarikhTerimaJPPH"));
    			hashUlasanJPPH.put("tahunNilaian", getParam("txtTahunNilaian") == null ? "" : getParam("txtTahunNilaian"));
    			hashUlasanJPPH.put("nilaian", getParam("txtNilaiTanah") == null||getParam("txtNilaiTanah").equals("")  ? "0.00" : getParam("txtNilaiTanah"));
    			hashUlasanJPPH.put("syor", getParam("txtSyorBayaran") == null ? "" : getParam("txtSyorBayaran"));
    			hashUlasanJPPH.put("ulasan", getParam("txtKeteranganJPPH") == null ? "" : getParam("txtKeteranganJPPH"));
    			beanUlasanJPPH.addElement(hashUlasanJPPH);
				this.context.put("BeanUlasanJPPH", beanUlasanJPPH);
				this.context.put("readOnly", "");
        		this.context.put("classDis", "");

    		} else if (mode.equalsIgnoreCase("viewJPPH")){
    			beanUlasanJPPH = new Vector<Hashtable<String, String>>();
    			logicmjm.setMaklumatUlasanJPPH(idUlasanTeknikal);
    			beanUlasanJPPH = logicmjm.getBeanMaklumatUlasanJPPH();
        		this.context.put("BeanUlasanJPPH", beanUlasanJPPH);
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");

    		}
    		//mode = update
    		else if(mode.equalsIgnoreCase("updateJPPH")){

    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}
    	} catch(Exception e){
    		e.printStackTrace();
    	}

    }

    public void UlasanSPHPView(String mode, String idUlasanSPHP) throws Exception{
    	try{
    		Vector<Hashtable<String, String>> beanUlasanSPHP = null;
    		if (mode.equalsIgnoreCase("newSPHP")){
    			beanUlasanSPHP = new Vector<Hashtable<String, String>>();
    			Hashtable<String, String> hashUlasanSPHP = new Hashtable<String, String>();
    			hashUlasanSPHP.put("noRujukan", getParam("txtNoRujukanSPHP") == null ? "" : getParam("txtNoRujukanSPHP"));
    			hashUlasanSPHP.put("tarikhHantar", getParam("txdTarikhHantarSPHP") == null ||getParam("txdTarikhHantarSPHP").equals("") ?sdf.format(new Date()) : getParam("txdTarikhHantarSPHP"));
    			hashUlasanSPHP.put("tarikhTerima", getParam("txdTarikhTerimaSPHP") == null ? "" : getParam("txdTarikhTerimaSPHP"));
    			hashUlasanSPHP.put("ulasan", getParam("txtUlasanSPHP") == null ? "" : getParam("txtUlasanSPHP"));
    			hashUlasanSPHP.put("status", getParam("socKeputusan") == null ? "" : getParam("socKeputusan"));
    			beanUlasanSPHP.addElement(hashUlasanSPHP);
				this.context.put("BeanUlasanSPHP", beanUlasanSPHP);
				this.context.put("readOnly", "");
        		this.context.put("classDis", "");

    		} else if (mode.equalsIgnoreCase("viewSPHP")){
    			beanUlasanSPHP = new Vector<Hashtable<String, String>>();
    			logicmjm.setMaklumatUlasanSPHP(idUlasanSPHP);
    			beanUlasanSPHP = logicmjm.getBeanMaklumatUlasanSPHP();
        		this.context.put("BeanUlasanSPHP", beanUlasanSPHP);
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");

    		//mode = update
    		}else if(mode.equalsIgnoreCase("updateSPHP")){
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");

    		}
    	} catch(Exception e){
    		e.printStackTrace();
    	}

    }
	public void DrafView(String mode, String idDraf) throws Exception{
		myLog.info("DrafView:idDraf="+idDraf);
		try{
			Vector<Hashtable<String, String>> beanDraf = null;
			if (mode.equalsIgnoreCase("newDraf")){
				beanDraf = new Vector<Hashtable<String, String>>();
				Hashtable<String, String> hashDraf = new Hashtable<String, String>();
				hashDraf.put("idpermohonan", getParam("txtidPermohonan") == null ? "" : getParam("txtidPermohonan"));
				hashDraf.put("tarikhHantar", getParam("txdTarikhHantarDraf") == null ? "" : getParam("txdTarikhHantarDraf"));
				hashDraf.put("tarikhTerima", getParam("txdTarikhTerimaDraf") == null ? "" : getParam("txdTarikhTerimaDraf"));
				hashDraf.put("ulasan", getParam("txtKeteranganDraf") == null ? "" : getParam("txtKeteranganDraf"));
				beanDraf.addElement(hashDraf);
				this.context.put("BeanDraf", beanDraf);
				this.context.put("readOnly", "");
				this.context.put("classDis", "");

			} else if (mode.equalsIgnoreCase("viewDraf")){
				beanDraf = new Vector<Hashtable<String, String>>();
				//logicmjm.setMaklumatDraf(idDraf);
				//beanDraf = logicmjm.getBeanMaklumatDraf();
				beanDraf = logicmjm.getMaklumatDraf(idDraf);
				this.context.put("BeanDraf", beanDraf);
				this.context.put("readOnly", "readOnly");
				this.context.put("classDis", "disabled");

			}else if(mode.equalsIgnoreCase("updateDraf")){//mode = update
				this.context.put("readOnly", "");
    			this.context.put("classDis", "");

			}

		} catch(Exception e){
			e.printStackTrace();
		}

	}

	   public void MemorandumJemaahMenteriView(String mode) throws Exception{
	    	try{
	    		Vector<Hashtable<String, String>> beanMJM = null;
	    		if (mode.equalsIgnoreCase("view")){
	    			beanMJM = new Vector<Hashtable<String, String>>();
	    			beanMJM = logicmjm.getMemoJemaahMenteri();
//	    			myLog.info(beanMJM);
	        		this.context.put("BeanMJM", beanMJM);
	        		this.context.put("readOnly", "readOnly");
	        		this.context.put("classDis", "disabled");

	    		}else if(mode.equalsIgnoreCase("update")){	//mode = update
	    			this.context.put("readOnly", "");
	        		this.context.put("classDis", "");

	    		}
	    	} catch(Exception e){
	    		e.printStackTrace();

	    	}

	    }
		public void simpanLampiran(String idPerjanjian) throws Exception {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
		    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
			    List<?> items = upload.parseRequest(request);
			    Iterator<?> itr = items.iterator();
			    while (itr.hasNext()) {
			    	FileItem item = (FileItem)itr.next();
				    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
//				    	myLog.info("simpanLampiran:ready");
				    	simpanLampiranDraf(idPerjanjian,item);
//				    	myLog.info("simpanLampiran:success");
				    }
			    }
			}
		}

		private void simpanLampiranDraf(String idPerjanjian,FileItem item) throws Exception {
			Db db = null;
			PreparedStatement ps = null;
			myLog.info("simpanLampiranDraf:idPerjanjian="+idPerjanjian);
			try {
	        	db = new Db();
	        	Connection con = db.getConnection();
				//Statement stmt = db.getStatement();
	        	con.setAutoCommit(false);
				if(logicmjm.getLampiran(idPerjanjian)){
//					myLog.info("simpanLampiranDraf:UPDATE");
		        	ps = con.prepareStatement("UPDATE TBLHTPDERAFPERJANJIANATTACH " +
		        			" (SET NAMA_FAIL = ?" +
		        			" ,MIMETYPE= ?" +
		        			" ,CONTENT_DERAFPERJANJIAN= ?" +
		        			" ,ID_KEMASKINI= ?" +
		        			" ,TARIKH_KEMASKINI=sysdate" +
		        			" WHERE ID_DERAFPERJANJIAN = ?) " +
		        			"");

		        	ps.setString(1,item.getName());
		        	ps.setString(2,item.getContentType());
		        	ps.setBinaryStream(3,item.getInputStream(),(int)item.getSize());
		        	ps.setString(4, userID);
		        	ps.setString(5, idPerjanjian);

				}else{
//					myLog.info("simpanLampiranDraf:INSERT");
					long id_derafperjanjian = DB.getNextID("TBLHTPDERAFPERATTACH_SEQ");
					ps = con.prepareStatement("" +
							"INSERT INTO TBLHTPDERAFPERJANJIANATTACH " +
		        			"(ID_DERAFPERJANJIANATTACH,ID_DERAFPERJANJIAN,NAMA_FAIL,MIMETYPE,CONTENT_DERAFPERJANJIAN,ID_MASUK,ID_KEMASKINI,TARIKH_MASUK,TARIKH_KEMASKINI) " +
		        			" VALUES (?,?,?,?,?,?,?,sysdate,sysdate)");
		        	ps.setLong(1, id_derafperjanjian);
		        	ps.setString(2, idPerjanjian);
		        	ps.setString(3,item.getName());
		        	ps.setString(4,item.getContentType());
		        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
		        	ps.setString(6,userID);
		        	ps.setString(7,userID);

				}

	        	ps.executeUpdate();
	            con.commit();

			 }catch (Exception e){
	        	e.getStackTrace();
		    } finally {
			      if (db != null) db.close();

		    }

	  }
	/**PERJANJIAN*/
	public void drafViewPerjanjian(String mode, String idDraf) throws Exception{
	    try{
	    	Vector<Hashtable<String,String>> beanDraf = null;
	    	if (mode.equalsIgnoreCase("newDraf")){
	    		beanDraf = new Vector<Hashtable<String,String>>();
	    		Hashtable<String,String> hashDraf = new Hashtable<String,String>();
	    		hashDraf.put("tarikhHantar", getParam("txdTarikhHantarDraf") == null ? "" : getParam("txdTarikhHantarDraf"));
	   			hashDraf.put("tarikhTerima", getParam("txdTarikhTerimaDraf") == null ? "" : getParam("txdTarikhTerimaDraf"));
	   			hashDraf.put("tarikhHantarPKP", getParam("txdTarikhHantarPKP") == null ? "" : getParam("txdTarikhHantarPKP"));
	   			hashDraf.put("tarikhTerimaPKP", getParam("txdTarikhTerimaPKP") == null ? "" : getParam("txdTarikhTerimaPKP"));
	   			hashDraf.put("ulasan", getParam("txtKeteranganDraf") == null ? "" : getParam("txtKeteranganDraf"));

    			beanDraf.addElement(hashDraf);
				this.context.put("BeanDraf", beanDraf);

				this.context.put("readOnly", "");
	        	this.context.put("classDis", "");

	    	} else if (mode.equalsIgnoreCase("viewDraf")){
	    		beanDraf = new Vector<Hashtable<String,String>>();
	    		//logicper.setMaklumatDraf(idDraf);
	    		//beanDraf = logicper.getBeanMaklumatDraf();
	    		beanDraf = getIPFungsi().getMaklumat(idDraf);
	    		//idDraf = beanDraf.get(0).get("idDraf");
	   			this.context.put("BeanDraf", beanDraf);

	        	this.context.put("readOnly", "readOnly");
	        	this.context.put("classDis", "disabled");

	    		//mode = update
	    	}else if(mode.equalsIgnoreCase("updateDraf")){
	    		this.context.put("readOnly", "");
	        	this.context.put("classDis", "");

	    	}
	   	} catch(Exception e){
	   		e.printStackTrace();
	   	}

    }

	public void PajakanView(String mode, String idPajakan,String idPermohonan) throws Exception{
		String idCaraBayar = getParam("socCaraBayar");
		if (idCaraBayar == null || idCaraBayar.trim().length() == 0){
			idCaraBayar = "99999";
		}
		String idTanah = getParam("socTanah");
		if (idTanah == null || idTanah.trim().length() == 0){
			idTanah = "99999";
		}

		try{
	    	Vector<Hashtable<String,String>> beanPajakan = null;
	    	Pajakan pajakan = getIPajakanBayaran().getMaklumatCukai(idPermohonan);
	   		myLog.info("cukai="+pajakan.getKadarCukaiString());
			this.context.put("pajakanTemp",pajakan);

	    	if (mode.equalsIgnoreCase("newPajakan")){
	    		myLog.info("mode=newPajakan");
	    		beanPajakan = new Vector<Hashtable<String,String>>();
	    		Hashtable<String,String> hashPajakan = new Hashtable<String,String>();
	    		hashPajakan.put("tarikhTandatangan", getParam("txdTarikhTandatangan") == null ? "" : getParam("txdTarikhTandatangan"));
	   			hashPajakan.put("tarikhMula", getParam("txdTarikhMulaPajakan") == null ? "" : getParam("txdTarikhMulaPajakan"));
	   			hashPajakan.put("tarikhTamat", getParam("txdTarikhTamatPajakan") == null ? "" : getParam("txdTarikhTamatPajakan"));
	   			hashPajakan.put("tempoh", getParam("txtTempoh") == null ? "" : getParam("txtTempoh"));
	   			hashPajakan.put("kadar", getParam("txtKadarCukai") == null ? "" : getParam("txtKadarCukai"));
    			hashPajakan.put("kadarPajakan", getParam("txtKadarPajakan") == null ? "" : getParam("txtKadarPajakan"));
	    		this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong(idCaraBayar), "", ""));
	    		this.context.put("selectTanah", HTML.selectTanah(idPermohonan,"socTanah", Long.parseLong(idTanah), "", ""));
    			//2018/03/07
	    		hashPajakan.put("status", getParam("socaktif") == null ? "Y" : getParam("socaktif"));
    			hashPajakan.put("denda", getParam("txtdenda") == null ? "8" : getParam("txtdenda"));
    			hashPajakan.put("catatan", getParam("catatan") == null ? "" : getParam("catatan"));

	    		beanPajakan.addElement(hashPajakan);
				this.context.put("BeanPajakan", beanPajakan);

				//PENAMBAHBAIKAN FASA3. 27112014. SYAZ. POPUP/NOTIFIKASI PERINGATAN SEMULA / LUPUT
				showNewEditDropdown("new",null);

				this.context.put("readOnly", "");
	        	this.context.put("classDis", "");

	    	} else if (mode.equalsIgnoreCase("viewPajakan")){
	    		myLog.info("viewPajakan");
	    		beanPajakan = new Vector<Hashtable <String,String>>();
	    		logicper.setMaklumatPajakan(idPajakan);
	    		beanPajakan = logicper.getBeanMaklumatPajakan();
	    		this.context.put("BeanPajakan", beanPajakan);

	    		Hashtable<String,String> hashMaklumatPajakanDB = (Hashtable<String,String>) beanPajakan.get(0);
	    		System.out.println("hashMaklumatPajakanDB.get(idTanah) >>>> "+hashMaklumatPajakanDB.get("idTanah"));
	    		//this.context.put("catatan_", hashMaklumatPajakanDB.get("catatan"));
	    		this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong((String) hashMaklumatPajakanDB.get("idCaraBayar")), "disabled='disabled' class='disabled'"));
	    		this.context.put("selectTanah", HTML.selectTanah(idPermohonan,"socTanah", (String) hashMaklumatPajakanDB.get("idTanah"), "disabled='disabled' class='disabled'"));

	    		showNewEditDropdown("view",Integer.parseInt(String.valueOf(hashMaklumatPajakanDB.get("notifikasiPeringatan"))));
	        	this.context.put("readOnly", "readOnly");
	        	this.context.put("classDis", "disabled");

	    		//mode = update
	    	}else if(mode.equalsIgnoreCase("updatePajakan")){
	    		myLog.info("updatePajakan:idpajakan="+idPajakan);
	    		beanPajakan = new Vector<Hashtable <String,String>>();
	    		logicper.setMaklumatPajakan(idPajakan);
	    		beanPajakan = logicper.getBeanMaklumatPajakan();
	    		this.context.put("BeanPajakan", beanPajakan);
	    		Hashtable <String,String> hashMaklumatPajakanDB = (Hashtable <String,String>) beanPajakan.get(0);

	    		showNewEditDropdown("edit",Integer.parseInt(String.valueOf(hashMaklumatPajakanDB.get("notifikasiPeringatan"))));

	    			//this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong(idCaraBayar), "", ""));
	    		this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong((String) hashMaklumatPajakanDB.get("idCaraBayar")), null));
	    		this.context.put("selectTanah", HTML.selectTanah(idPermohonan,"socTanah", Long.parseLong(idTanah), null,null));

	    		this.context.put("readOnly", "");
	        	this.context.put("classDis", "");

	    	}

	    } catch(Exception e){
	    	e.printStackTrace();
	    }

	}

	private void showNewEditDropdown(String strMode,Integer value) throws Exception {
	    if(strMode.equalsIgnoreCase("new") || strMode.equalsIgnoreCase("edit")){
	    	strMode = "";
	    }else{
	    	strMode = "disabled class='disabled'";
	    }
	    this.context.put("selectNumberRange", HTML.selectNumberRange("socNotifikasiPeringatan", value, strMode, "onchange='javascript:doCheckTarikhTamat()' style='width:15% !important'", 1, 99));

	}

	public void MOAView(String mode) throws Exception{
    	try{
    		Vector<Hashtable <String,String>> beanMaklumatMOA = null;

    		if (mode.equalsIgnoreCase("view")){

    			beanMaklumatMOA = new Vector<Hashtable <String,String>>();
    			beanMaklumatMOA = logicper.getBeanMaklumatMOA();
        		this.context.put("BeanMaklumatMOA", beanMaklumatMOA);

        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");

    		//mode = update
    		}else if(mode.equalsIgnoreCase("update")){
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    }

	public void Borang15AView(String mode) throws Exception{
		Hashtable <String,String> beanMaklumat15A = null;
		try{
    		//Vector<Hashtable <String,String>> beanMaklumat15A = null;
			//logicper.setMaklumat15A(idPermohonan);
    		beanMaklumat15A = logicper.getMaklumat15A(idPermohonan);
    		myLog.info("beanMaklumat15A="+beanMaklumat15A);

    		if (mode.equalsIgnoreCase("view")){
    			//beanMaklumat15A = new Vector<Hashtable <String,String>>();
    			//beanMaklumat15A = logicper.getBeanMaklumat15A();
        		//this.context.put("BeanMaklumat15A", beanMaklumat15A);

        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");

    		//mode = update
    		}else if(mode.equalsIgnoreCase("update")){
				Vector<Hashtable<String,String>> senaraiDraf = getIPFungsi().getSenarai(idPermohonan,"P");
				Hashtable<String,String> hDraf = (Hashtable<String,String>)senaraiDraf.get(0);

				logicper.setListPajakan(idPermohonan);
				Vector<Hashtable<String,String>> senaraiPajakan = logicper.getSenaraiPajakan();
				Hashtable<String,String> hPajakan = (Hashtable<String,String>)senaraiPajakan.get(0);

				if(beanMaklumat15A.get("tarikhTerima").equals(""))
					beanMaklumat15A.put("tarikhTerima", hDraf.get("tarikhTerima"));
				if(beanMaklumat15A.get("tarikhTandatangan").equals(""))
					beanMaklumat15A.put("tarikhTandatangan", hPajakan.get("tarikhTandatangan"));

    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}
        	this.context.put("beanMaklumat15A", beanMaklumat15A);

    	} catch(Exception e){
    		e.printStackTrace();
    	}
    }

	public void JemaahMenteriView(String mode) throws Exception{
    	try{
    		Vector<Hashtable <String,String>> beanMaklumatJM = null;

    		if (mode.equalsIgnoreCase("view")){

    			beanMaklumatJM = new Vector<Hashtable <String,String>>();
    			beanMaklumatJM = logicper.getBeanMaklumatJemaahMenteri();
        		this.context.put("BeanMaklumatJM", beanMaklumatJM);

        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");

    		//mode = update
    		}else if(mode.equalsIgnoreCase("update")){
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    }
	private void saveMOA(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable <String,String> hash = new Hashtable <String,String>();
		hash.put("tarikhTerima", getParam("txdTarikhTerimaMOA"));
		hash.put("tarikhTandatangan", getParam("txdTarikhTandatanganPTP"));
		hash.put("tarikhKembali", getParam("txdTarikhKembaliMOA"));
		hash.put("tarikhDaftar", getParam("txdTarikhDaftarMOA"));
		hash.put("tarikhBayar", getParam("txdTarikhBayaranMOA"));
		hash.put("noPerjanjian", getParam("txtNoPerjanjianMOA"));
		hash.put("tarikhHantarPengarah", getParam("txdTarikhHantarPengarah"));
		logicper.updateMOA(idPermohonan, hash, session);

	}

	private void save15A(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable <String,String> hash = new Hashtable <String,String>();
		hash.put("tarikhTerima", getParam("txdTarikhTerimaPemohon"));
		hash.put("tarikhTandatangan", getParam("txdTarikhTandatanganPTP15A"));
		hash.put("tarikhHantar", getParam("txdTarikhHantarPemohon"));
		hash.put("tarikhDaftar", getParam("txdTarikhDaftarPajakan"));
		hash.put("tarikhTerimaHakmilik", getParam("txdtarikhTerimaHakmilik"));
		hash.put("tarikhKemaskini", getParam("txdTarikhKemaskiniHakmilik"));
		hash.put("flagNotifikasi", getParam("flagNotifikasi"));
		logicper.update15A(idPermohonan, hash, session);

	}

	private void saveJM(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable <String,String> hash = new Hashtable <String,String>();
		hash.put("tarikhPerbadanan", getParam("txdTarikhPerbadanan"));
		hash.put("tarikhMesyuarat", getParam("txdtarikhMesyuarat"));
		hash.put("noMemo", getParam("txtNoMemorandum"));
		logicper.updateJM(idPermohonan, hash, session);

	}

	//private void saveDraf(String idPermohonan, HttpSession session) throws Exception {
	private void simpanPerjanjian(String idRujukan, String op) throws Exception {
    	Hashtable <String,String> hash = new Hashtable <String,String>();
    	hash.put("tarikhHantar", getParam("txdTarikhHantarDraf"));
		hash.put("tarikhTerima", getParam("txdTarikhTerimaDraf"));
		hash.put("tarikhHantarPKP", getParam("txdTarikhHantarPKP"));
		hash.put("tarikhTerimaPKP", getParam("txdTarikhTerimaPKP"));
		hash.put("ulasan", getParam("txtKeteranganDraf"));
		hash.put("jenis", "P");
		hash.put("userId", userID);
		//logicper.saveDraf(idPermohonan,hash);
		if(op.equals("simpan"))
			iDeraf = getIPFungsi().simpan(idRujukan, hash);
		else if(op.equals("kemaskini"))
			getIPFungsi().kemaskini(idRujukan, hash);

	}
//    private void saveUpdateDrafPerjanjian(String idDraf, HttpSession session) throws Exception {
//    	Hashtable <String,String> hash = new Hashtable <String,String>();
//    	hash.put("tarikhHantar", getParam("txdTarikhHantarDraf"));
//		hash.put("tarikhTerima", getParam("txdTarikhTerimaDraf"));
//		hash.put("tarikhHantarPKP", getParam("txdTarikhHantarPKP"));
//		hash.put("tarikhTerimaPKP", getParam("txdTarikhTerimaPKP"));
//		hash.put("ulasan", getParam("txtKeteranganDraf"));
//		logicper.saveUpdateDraf(idDraf, hash, session);
//
//	}

    private void savePajakan(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable <String,String> hash = new Hashtable <String,String>();
    	hash.put("tarikhTandatangan", getParam("txdTarikhTandatangan"));
		hash.put("tarikhMula", getParam("txdTarikhMulaPajakan"));
		hash.put("tarikhTamat", getParam("txdTarikhTamatPajakan"));
		hash.put("tempoh", getParam("txtTempoh"));
		hash.put("socNotifikasiPeringatan", getParam("socNotifikasiPeringatan"));
		String kcukai = getParam("txtKadarCukai") == "" ? "0.00" : getParam("txtKadarCukai");
		hash.put("kadar", String.valueOf(Utils.parseDouble(Utils.RemoveComma(kcukai))));
		hash.put("kadarPajakan", getParam("txtKadarPajakan"));
		hash.put("idCaraBayar", getParam("socCaraBayar"));
		hash.put("katCukai", getParam("socKategoriCukai"));
		//2017/05/09
		hash.put("tarikhPatut", getParam("txdtarikhpatut"));
		hash.put("catatan", getParam("txtcatatan").trim());
		hash.put("denda", getParam("txtdenda") == null ? "8" : getParam("txtdenda"));
		hash.put("status", "Y");
		hash.put("id_tanah", getParam("socTanah"));

		logicper.savePajakan(idPermohonan, hash, session);

	}

    private void saveUpdatePajakan(String idPajakan, HttpSession session) throws Exception {
    	Hashtable <String,String> hash = new Hashtable <String,String>();
    	hash.put("tarikhTandatangan", getParam("txdTarikhTandatangan"));
		hash.put("tarikhMula", getParam("txdTarikhMulaPajakan"));
		hash.put("tarikhTamat", getParam("txdTarikhTamatPajakan"));
		hash.put("tempoh", getParam("txtTempoh"));
		hash.put("socNotifikasiPeringatan", getParam("socNotifikasiPeringatan"));

		String kcukai = getParam("txtKadarCukai") == "" ? "0.00" : getParam("txtKadarCukai");

		hash.put("kadar", String.valueOf(Utils.parseDouble(Utils.RemoveComma(kcukai))));
		hash.put("kadarPajakan", getParam("txtKadarPajakan"));
		hash.put("idCaraBayar", getParam("socCaraBayar"));
		hash.put("katCukai", getParam("socKategoriCukai"));
		//2017/05/09
		hash.put("tarikhPatut", getParam("txdtarikhpatut"));
		hash.put("catatan", getParam("txtcatatan").trim());
		hash.put("denda", getParam("txtdenda") == null ? "8" : getParam("txtdenda"));
		hash.put("status", getParam("socaktif"));
		hash.put("id_tanah", getParam("socTanah"));
		logicper.saveUpdatePajakan(idPajakan, hash, session);

	}
    /**Bayaran*/
    public void BayaranView(String mode, String idBayaran) throws Exception{
    	String tujuan = getParam("socTujuan");
		if (tujuan == null || tujuan.trim().length() == 0){
			tujuan = "99999";
		}
		String caraBayar = getParam("socCaraBayar");
		if (caraBayar == null || caraBayar.trim().length() == 0){
			caraBayar = "99999";
		}

		 try{
	    	Vector<Hashtable <String,String>> beanBayaran = null;
	    	Vector<Hashtable <String,String>> v = logicBayaran.getListMaklumatBayaran();
	    	Hashtable <String,String> hashBayar = null;
	    	this.context.put("BayaranList", v);

	    	if (mode.equalsIgnoreCase("newBayaran")){
	    		beanBayaran = new Vector<Hashtable <String,String>>();
	    		Hashtable <String,String> hashBayaran = new Hashtable <String,String>();
	    		hashBayaran.put("namaBank", getParam("txtNamaBank") == null ? "" : getParam("txtNamaBank"));
	    		hashBayaran.put("NoBayaran", getParam("txtNoBayaran") == null ? "" : getParam("txtNoBayaran"));
	    		hashBayaran.put("tarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
	    		hashBayaran.put("jumlahBayaran", getParam("txtJumlahBayaran") == null ? "" : getParam("txtJumlahBayaran"));
	    		hashBayaran.put("NoResit", getParam("txtNoResit") == null ? "" : getParam("txtNoResit"));
	    		hashBayaran.put("tarikhResit", getParam("txdtarikhResit") == null ? "" : getParam("txdtarikhResit"));
	    		hashBayaran.put("tarikhHantarResit", getParam("txdtarikhHantarResit") == null ? "" : getParam("txdtarikhHantarResit"));
	    		hashBayaran.put("tarikhCek", getParam("txdTarikhCek") == null ? "" : getParam("txdTarikhCek"));

	    		beanBayaran.addElement(hashBayaran);
				this.context.put("BeanBayaranList", beanBayaran);

				this.context.put("selectTujuan", FrmPajakanBayaranPajakanCukaiTanahData.SelectTujuanBayar("socTujuan", Long.parseLong(tujuan), "" , ""));
				this.context.put("selectCaraBayar", logicBayaran.selectCaraBayaranPajakan("socCaraBayar", Long.parseLong(caraBayar), "", ""));

				this.context.put("readOnly", "");
	       		this.context.put("classDis", "");

	   		} else if (mode.equalsIgnoreCase("viewBayaran")){
	    		beanBayaran = new Vector<Hashtable <String,String>>();
	    		hashBayar = new Hashtable <String,String>();
	    		logicBayaran.setMaklumatBayaran(idBayaran);
	   			beanBayaran = logicBayaran.getBeanMaklumatBayaran();
	   			hashBayar = (Hashtable <String,String>)beanBayaran.get(0);
	   			myLog.info(hashBayar);
	   			String disabled = "class='disabled' disabled='disabled'";

	   			this.context.put("BeanBayaranList", beanBayaran);

        		this.context.put("readOnly", "readOnly");
	        	this.context.put("classDis", "disabled");

	        	this.context.put("selectTujuan", FrmPajakanBayaranPajakanCukaiTanahData.SelectTujuanBayar("socTujuan", Long.parseLong((String)hashBayar.get("tujuan")), disabled , ""));
				this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong((String)hashBayar.get("caraBayar")), disabled, ""));

	    		//mode = update
	    	}else if(mode.equalsIgnoreCase("updateBayaran")){
	    		this.context.put("selectTujuan", FrmPajakanBayaranPajakanCukaiTanahData.SelectTujuanBayar("socTujuan", Long.parseLong(tujuan), "" , ""));
				this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong(caraBayar), "", ""));

    			this.context.put("readOnly", "");
    			this.context.put("classDis", "");

	    	}

	    } catch(Exception e){
	   		e.printStackTrace();
	    }

    }
    public void BayaranView1(String mode, String idBayaran) throws Exception{
		 String tujuan = getParam("socTujuan");
		if (tujuan == null || tujuan.trim().length() == 0){
			tujuan = "99999";
		}
		String caraBayar = getParam("socCaraBayar");
		if (caraBayar == null || caraBayar.trim().length() == 0){
			caraBayar = "99999";
		}

		 try{
	    	Vector<Hashtable <String,String>> beanBayaran = null;
	    	Vector<Hashtable <String,String>> v = logicBayaran.getListMaklumatBayaran();
	    	Hashtable <String,String> hashBayar = null;
	    	this.context.put("BayaranList", v);

	    	if (mode.equalsIgnoreCase("baharu")){
	    		beanBayaran = new Vector<Hashtable <String,String>>();
	    		Hashtable <String,String> hashBayaran = new Hashtable <String,String>();
	    		hashBayaran.put("namaBank", getParam("txtNamaBank") == null ? "" : getParam("txtNamaBank"));
	    		hashBayaran.put("NoBayaran", getParam("txtNoBayaran") == null ? "" : getParam("txtNoBayaran"));
	    		hashBayaran.put("tarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
	    		hashBayaran.put("jumlahBayaran", getParam("txtJumlahBayaran") == null ? "" : getParam("txtJumlahBayaran"));
	    		hashBayaran.put("NoResit", getParam("txtNoResit") == null ? "" : getParam("txtNoResit"));
	    		hashBayaran.put("tarikhResit", getParam("txdtarikhResit") == null ? "" : getParam("txdtarikhResit"));
	    		hashBayaran.put("tarikhHantarResit", getParam("txdtarikhHantarResit") == null ? "" : getParam("txdtarikhHantarResit"));
	    		hashBayaran.put("tarikhCek", getParam("txdTarikhCek") == null ? "" : getParam("txdTarikhCek"));

	    		beanBayaran.addElement(hashBayaran);
				this.context.put("BeanBayaranList", beanBayaran);

				this.context.put("selectTujuan", FrmPajakanBayaranPajakanCukaiTanahData.SelectTujuanBayar("socTujuan", Long.parseLong(tujuan), "" , ""));
				this.context.put("selectCaraBayar", logicBayaran.selectCaraBayaranPajakan("socCaraBayar", Long.parseLong(caraBayar), "", ""));

				this.context.put("readOnly", "");
	       		this.context.put("classDis", "");

	   		} else if (mode.equalsIgnoreCase("viewBayaran")){
	    		beanBayaran = new Vector<Hashtable <String,String>>();
	    		hashBayar = new Hashtable <String,String>();
	    		logicBayaran.setMaklumatBayaran(idBayaran);
	   			beanBayaran = logicBayaran.getBeanMaklumatBayaran();
	   			hashBayar = (Hashtable <String,String>)beanBayaran.get(0);
	   			String disabled = "class='disabled' disabled='disabled'";

	   			this.context.put("BeanBayaranList", beanBayaran);

       		this.context.put("readOnly", "readOnly");
	        	this.context.put("classDis", "disabled");

	        	this.context.put("selectTujuan", FrmPajakanBayaranPajakanCukaiTanahData.SelectTujuanBayar("socTujuan", Long.parseLong((String)hashBayar.get("tujuan")), disabled , ""));
				this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong((String)hashBayar.get("caraBayar")), disabled, ""));

	    		//mode = update
	    	}else if(mode.equalsIgnoreCase("updateBayaran")){
	    		this.context.put("selectTujuan", FrmPajakanBayaranPajakanCukaiTanahData.SelectTujuanBayar("socTujuan", Long.parseLong(tujuan), "" , ""));
				this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong(caraBayar), "", ""));

   			this.context.put("readOnly", "");
   			this.context.put("classDis", "");

	    	}

	    } catch(Exception e){
	   		e.printStackTrace();
	    }

   }

//    private void saveBayaran(String idPermohonan, HttpSession session) throws Exception {
//    	Hashtable <String,String> hashBayaran = new Hashtable <String,String>();
//    	hashBayaran.put("namaBank", getParam("txtNamaBank") == null ? "" : getParam("txtNamaBank"));
//		hashBayaran.put("NoBayaran", getParam("txtNoBayaran") == null ? "" : getParam("txtNoBayaran"));
//		hashBayaran.put("tarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
//		hashBayaran.put("jumlahBayaran", getParam("txtJumlahBayaran") == null ? "" : getParam("txtJumlahBayaran"));
//		hashBayaran.put("NoResit", getParam("txtNoResit") == null ? "" : getParam("txtNoResit"));
//		hashBayaran.put("tarikhResit", getParam("txdtarikhResit") == null ? "" : getParam("txdtarikhResit"));
//		hashBayaran.put("tarikhHantarResit", getParam("txdtarikhHantarResit") == null ? "" : getParam("txdtarikhHantarResit"));
//		hashBayaran.put("tujuan", getParam("socTujuan"));
//		hashBayaran.put("caraBayar", getParam("socCaraBayar"));
//		hashBayaran.put("tarikhCek", getParam("txdTarikhCek") == null ? "" : getParam("txdTarikhCek"));
//		//myLog.info("hash bayaran : " + hashBayaran);
//		logicBayaran.saveBayaran(idPermohonan, hashBayaran, session);
//
//	}

//    private void saveUpdateBayaran(String idBayaran, HttpSession session) throws Exception {
//	 	Hashtable <String,String> hashBayaran = new Hashtable <String,String>();
//    	hashBayaran.put("namaBank", getParam("txtNamaBank") == null ? "" : getParam("txtNamaBank"));
//		hashBayaran.put("NoBayaran", getParam("txtNoBayaran") == null ? "" : getParam("txtNoBayaran"));
//		hashBayaran.put("tarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
//		hashBayaran.put("jumlahBayaran", getParam("txtJumlahBayaran") == null ? "" : getParam("txtJumlahBayaran"));
//		hashBayaran.put("NoResit", getParam("txtNoResit") == null ? "" : getParam("txtNoResit"));
//		hashBayaran.put("tarikhResit", getParam("txdtarikhResit") == null ? "" : getParam("txdtarikhResit"));
//		hashBayaran.put("tarikhHantarResit", getParam("txdtarikhHantarResit") == null ? "" : getParam("txdtarikhHantarResit"));
//		hashBayaran.put("tujuan", getParam("socTujuan") == null ? "" : getParam("socTujuan"));
//		hashBayaran.put("caraBayar", getParam("socCaraBayar") == null ? "" : getParam("socCaraBayar"));
//		hashBayaran.put("tarikhCek", getParam("txdTarikhCek") == null ? "" : getParam("txdTarikhCek"));
//		logicBayaran.saveUpdateBayaran(idBayaran, hashBayaran, session);
//
//	}
    private void simpanKemaskiniBayaran(String idPermohonan,String idBayaran) throws Exception {
	 	Hashtable <String,String> hashBayaran = new Hashtable <String,String>();
    	hashBayaran.put("namaBank", getParam("txtNamaBank") == null ? "" : getParam("txtNamaBank"));
		hashBayaran.put("NoBayaran", getParam("txtNoBayaran") == null ? "" : getParam("txtNoBayaran"));
		hashBayaran.put("tarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
		hashBayaran.put("jumlahBayaran", getParam("txtJumlahBayaran") == null ? "" : getParam("txtJumlahBayaran"));
		hashBayaran.put("NoResit", getParam("txtNoResit") == null ? "" : getParam("txtNoResit"));
		hashBayaran.put("tarikhResit", getParam("txdtarikhResit") == null ? "" : getParam("txdtarikhResit"));
		hashBayaran.put("tarikhHantarResit", getParam("txdtarikhHantarResit") == null ? "" : getParam("txdtarikhHantarResit"));
		hashBayaran.put("tujuan", getParam("socTujuan") == null ? "" : getParam("socTujuan"));
		hashBayaran.put("caraBayar", getParam("socCaraBayar") == null ? "" : getParam("socCaraBayar"));
		hashBayaran.put("tarikhCek", getParam("txdTarikhCek") == null ? "" : getParam("txdTarikhCek"));
		hashBayaran.put("idBayaran", idBayaran);
		hashBayaran.put("userId", userID);
		logicBayaran.simpanKemaskiniBayaran(idPermohonan, hashBayaran);

	}
	public void statusView(String idFail,String idPermohonan,String mode) throws Exception{
		Hashtable<String,String> hInfo = null;
		String modeDisplay = "";
		String classDis = "";
		String pageMode = "";
		try{
			//hInfo = new Hashtable<String,String>();
			Tblrujsuburusanstatusfail susf = getStatus().getStatusFailPermohonanAktif(idFail,idPermohonan);
			Tblrujsuburusanstatus sus = getStatus().getDetails(String.valueOf(susf.getIdSuburusanstatus()));

			if(sus.getLangkah()==98 || sus.getLangkah()==99)
				hInfo = getIHTP().getInfoTamatSelesaiPermohonan(idPermohonan);

			if(mode.equalsIgnoreCase("kemaskini")){
				pageMode = mode;
			}else if(mode.equalsIgnoreCase("view")){

				if(hInfo == null)
					pageMode = "baru";
				else{
					modeDisplay = " disabled='disabled' ";
					classDis = " class='disabled' ";
					pageMode = mode;

				}

			}
			//myLog.info("statusView:"+hInfo+","+mode+","+classDis+","+pageMode);
			context.put("selesaiBean", hInfo);
			context.put("modeDisplay", modeDisplay);
			context.put("classDis", classDis);
			context.put("pagemode", pageMode);

		}catch(Exception e){
			e.printStackTrace();

		}

	}

	private void kemaskiniSimpanStatusSelesai(String idFail,String idPermohonan)throws Exception {
		 try {
			subUrusanStatusFail = new Tblrujsuburusanstatusfail();
			subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
			subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
			subUrusanStatusFail.setUrl(getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
			subUrusanStatusFail.setIdKemaskini(Long.parseLong(userID));
			getIHTP().kemaskiniSimpanStatusPermohonanAktif(subUrusanStatusFail,getParam("txdTarikhTerima"));

		} catch (Exception e) {
			throw new Exception("Ralat kemaskiniSimpanStatusSelesai[2371]:"+e.getMessage());

		}

	}

	private void kemaskiniSimpanStatusSelesai(String idFail,String idPermohonan,String idSubUrusan,String langkah)
		throws Exception {
		myLog.info("kemaskiniSimpanStatusSelesai ::: langkah >>> "+langkah);
		 try {
			subUrusanStatusFail = new Tblrujsuburusanstatusfail();
			subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
			subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
			subUrusanStatusFail.setAktif("0");
			subUrusanStatusFail.setIdKemaskini(Long.parseLong(userID));


			Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
			long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,idSubUrusan,"=");
			subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
			subUrusanStatusFailN.setAktif("1");
			subUrusanStatusFailN.setUrl(getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
			subUrusanStatusFailN.setIdMasuk(Long.parseLong(userID));
			//subUrusanStatusFailN.setTarikhMasuk(new Date(getParam("txdTarikhTerima")));
			//getIHTP().kemaskiniSimpanStatusPermohonanAktif(subUrusanStatusFail, subUrusanStatusFailN,getParam("txdTarikhTerima"));
			getStatus().kemaskiniSimpanStatusAktif(subUrusanStatusFail, subUrusanStatusFailN,getParam("txdTarikhTerima"));

		} catch (Exception e) {
			throw new Exception("Ralat kemaskiniSimpanStatusSelesai[2395]:"+e.getMessage());

		}

	}

	private void setTindakanValues(String idPermohonan,String idSusulanInput, String button) throws Exception{
		Hashtable<String, String> hashData = null;
		String sumber = "PAJAKAN_TINDAKAN";
		String tarikh = getParam("txdtindakan");
		String catatan = getParam("txtcatatan");

		if (button.equals("tindakanhapus")){
			getISusulan().hapus(idSusulanInput);
		}else{
			hashData = new Hashtable<String, String>();
			hashData.put("txdTarikh", tarikh);
			hashData.put("idPermohonan", idPermohonan);
			hashData.put("catatan", catatan);
			hashData.put("idMasuk",userID);
			hashData.put("idSusulan",idSusulanInput);
			hashData.put("sumber",sumber);
			idSusulan = getISusulan().simpanKemaskini(hashData);
		}

	}

	private IPembelian getIPembelian(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	}

	private IHtp getIHTP(){
		if(iHtp == null)
			iHtp = new HtpBean();
		return iHtp;
	}

	private IHTPFail getIHTPFail(){
		if (iHTPFail==null){
			iHTPFail = new HTPFailBean();
		}
		return iHTPFail;
	}

	private IHTPeringatan getIHTPP(){
		if(iHTPP== null)
			iHTPP = new HTPeringatanBean();
		return iHTPP;
	}

	private IPajakanFungsi getIPFungsi(){
		if(iPFungsi== null)
			iPFungsi = new PajakanUtamaPerjanjian();
		return iPFungsi;
	}

	private IPajakanMJM getIPMJM(){
		if(iPMJM== null)
			iPMJM = new PajakanMJMBean();
		return iPMJM;
	}

	private IOnline getIOnline(){
		if(iOnline==null){
			iOnline = new OnlineBean();
		}
		return iOnline;

	}

	private IHTPStatus getStatus(){
		if(iStatus==null){
			iStatus = new HTPStatusBean();
		}
		return iStatus;

	}

	private IHTPSusulan getISusulan(){
		if(iSusulan==null){
			iSusulan = new HTPSusulanBean();
		}
		return iSusulan;

	}

	private IPajakanUtamaBayaran getIPajakanBayaran(){
		if(iPajakanBayaran== null)
			iPajakanBayaran = new PajakanUtamaBayaranBean();

		return iPajakanBayaran;

	}

	private IUserPegawai getIUser(){
		if(iUser==null){
			iUser = new UserKJPBean();
		}
		return iUser;

	}


}
