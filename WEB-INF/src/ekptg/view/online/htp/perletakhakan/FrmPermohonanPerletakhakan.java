package ekptg.view.online.htp.perletakhakan;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.entities.UserKementerian;
import ekptg.model.htp.FrmHakmilikPerletakhakanData;
import ekptg.model.htp.FrmPerletakhakanMaklumatData;
import ekptg.model.htp.FrmPerletakhakanPendaftaranData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.IPenggunaKementerian;
import ekptg.model.htp.PenggunaKementerianBean;

public class FrmPermohonanPerletakhakan extends AjaxBasedModule {

	private final String IDURUSAN = "5";
	private final String PATH="app/htp/perletakhakan/";
	private FrmPerletakhakanPendaftaranData logic = new FrmPerletakhakanPendaftaranData(); 
	private FrmHakmilikPerletakhakanData logic2 = new FrmHakmilikPerletakhakanData();//data tuk view
	private FrmHakmilikPerletakhakanData perletakhakanData = new FrmHakmilikPerletakhakanData();
	private IHtp iHTP = null;  
	private IPenggunaKementerian iPengguna = null;
	private static final long serialVersionUID = -8487877676979529353L;
	private static Logger myLog = Logger.getLogger(ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan.class);
    private String isCarian = "tidak";
	private String userId = "";
	private UserKementerian uk = null;

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();		
		Boolean postDB = false;
		String doPost = session.getAttribute("doPost").toString();
		if (doPost.equals("true")) {
			postDB = true;
		}
		userId = (String)session.getAttribute("_ekptg_user_id");

		String vm="";
		
		String submit = getParam("command"); //untuk doAjaxCall
		String action = getParam("action"); //untuk setup paging je
		String mode  = getParam("mode");
		String hitButton  = getParam("hitButton");
		
		String idFail = getParam("idFail");		
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
		String idSuburusanStatusFail = getParam("idSuburusanStatusFail");
		String actionPerletakhakan = getParam("actionPerletakhakan");

		String id_kementerian = "";
		String idUser = "";
		idUser = (String)session.getAttribute("_ekptg_user_id");

		String idHakmilikurusan = getParam("idHakmilikurusan");
		String idPihakberkepentingan = getParam("idPihakberkepentingan");
		String idBebanan = getParam("idBebanan");
		
        //ONLINE
        if (id_kementerian == null || id_kementerian.trim().length() == 0){
			uk = getIPengguna().getKementerian(idUser);
			if(uk == null)
				throw new Exception("[ONLINE-HTP REKOD] KEMENTERIAN TIDAK DIJUMPAI");
			
			id_kementerian = String.valueOf(uk.getAgensi().getKementerian().getIdKementerian());
			System.out.println("idKementerian ==" +id_kementerian);
		}
		// CARIAN
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
		
		String idkementerianC = getParam("socKementerianC");
		if (idkementerianC == null || idkementerianC.trim().length() == 0){
			idkementerianC = "99999";
			uk = getIPengguna().getKementerian(userId);
			if(uk == null)
				throw new Exception("[ONLINE-HTP REKOD] KEMENTERIAN TIDAK DIJUMPAI");
			
			idkementerianC = String.valueOf(uk.getAgensi().getKementerian().getIdKementerian());
		}

		String idAgensiC = getParam("socAgensiC");
		if (idAgensiC == null || idAgensiC.trim().length() == 0){
			idAgensiC = "99999";
		}
				
		//PENDAFTAAN
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}

		String idkementerian = getParam("socKementerian");
		if (idkementerian == null || idkementerian.trim().length() == 0){
			//idkementerian = "99999";
			idkementerian =idkementerianC;
		}

		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}
		String idSuburusan = getParam("socSuburusan");
		if (idSuburusan == null || idSuburusan.trim().length() == 0){
			idSuburusan = "99999";
		}
		
		/*String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}		
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}*/
		String idNegeriPemilik = getParam("socNegeriPemilik");
		if (idNegeriPemilik == null || idNegeriPemilik.trim().length() == 0){
			idNegeriPemilik = "99999";
		}		
		String idDaerahPemilik = getParam("socDaerahPemilik");
		if (idDaerahPemilik == null || idDaerahPemilik.trim().length() == 0){
			idDaerahPemilik = "99999";
		}
		String idNegeriBebanan = getParam("socNegeriBebanan");
		if (idNegeriBebanan == null || idNegeriBebanan.trim().length() == 0){
			idNegeriBebanan = "99999";
		}		
		String idDaerahBebanan = getParam("socDaerahBebanan");
		if (idDaerahBebanan == null || idDaerahBebanan.trim().length() == 0){
			idDaerahBebanan = "99999";
		}
		String idMukim = getParam("socMukim");
		if (idMukim == null || idMukim.trim().length() == 0){
			idMukim = "99999";
		}		
		String idJenisHakmilik = getParam("socJenisHakmilik");
		if (idJenisHakmilik == null || idJenisHakmilik.trim().length() == 0){
			idJenisHakmilik = "99999";
		}		
		String idKategori = getParam("socKategori");
		if (idKategori == null || idKategori.trim().length() == 0){
			idKategori = "99999";
		}		
		String idSubkategori = getParam("socSubkategori");
		if (idSubkategori == null || idSubkategori.trim().length() == 0){
			idSubkategori = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}		
		String idLot = getParam("socLot");
		if (idLot == null || idLot.trim().length() == 0){
			idLot = "99999";
		}
		String idJenisBebanan = getParam("socJenisBebanan");
		if (idJenisBebanan == null || idJenisBebanan.trim().length() == 0){
			idJenisBebanan = "99999";
		}
		String idJenisPB = getParam("socJenisPB");
		if (idJenisPB == null || idJenisPB.trim().length() == 0){
			idJenisPB = "99999";
		}
		
		Vector<?> list = null;
		Vector beanHeader = null;
		Vector listHakmilik = null;
		Vector beanHakmilik = null;
		myLog.info("submit:"+submit+",action="+action+",mode="+mode);
		myLog.info("actionPerletakhakan="+actionPerletakhakan);
			
		if(postDB){
			if("simpanHakmilik".equals(hitButton)){
				idHakmilikurusan = logic2.saveHakmilik(idPermohonan,getParam("txtLokasi"),
                 		getParam("txtCukaiSemasa"),
    					getParam("txtSyaratNyata"),
    					getParam("txtNoHakmilik"),
    					getParam("txtNoStrata"),
    					getParam("txtNoPelan"),
    					getParam("Luas"),
    					getParam("txtNoSyit"),
    					getParam("txtSekatan"),
    					getParam("txtLot"),
    					getParam("txtLuas1"),
    					idNegeri,  
    					idLuas,
    					idDaerah,
    					idLot,
    					idKategori,
    					idMukim,
    					idJenisHakmilik,
    				    session);
			}if("simpanUpdateHakmilik".equals(hitButton)){
				logic2.updateHakmilik(idHakmilikurusan,getParam("txtLokasi"),
                 		getParam("txtCukaiSemasa"),
    					getParam("txtSyaratNyata"),
    					getParam("txtNoHakmilik"),
    					getParam("txtNoStrata"),
    					getParam("txtNoPelan"),
    					getParam("Luas"),
    					getParam("txtNoSyit"),
    					getParam("txtSekatan"),
    					getParam("txtLot"),
    					getParam("txtLuas1"),
    					idNegeri,  
    					idLuas,
    					idDaerah,
    					idLot,
    					idKategori,
    					idMukim,
    					idJenisHakmilik,
    				    session);
			}
		}
		//SENARAI PENDAFTARAN PERLETAHAKAN
		if("".equals(actionPerletakhakan)){
			
			vm = PATH+"frmPerletakhakanSenaraiFailOnline.jsp";
	   	   
			logic.getSenaraiFailOnline(getParam("txtNoFail"), getParam("txtTajukFail"), idkementerianC, idAgensiC, idNegeriC, idDaerahC, idMukimC,(String)session.getAttribute("_ekptg_user_id"));

			isCarian = getParam("txtcarian");
			myLog.info("isCarian::"+isCarian);
			if(isCarian.equals("ya")){

				logic.getSenaraiFailOnline(getParam("txtNoFail"), getParam("txtTajukFail"), idkementerianC, idAgensiC, idNegeriC, idDaerahC, idMukimC,null);
				isCarian = "ya";
				
			}
			list = logic.getListFailPerletakhakan();
			this.context.put("SenaraiFail", list);
			 
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtTajukFail", getParam("txtTajukFail"));
			 
			this.context.put("selectNegeriC", HTML.SelectNegeri("socNegeriC",Utils.parseLong(idNegeriC), " onChange=\"doChangeNegeriCarian();\" style=\"width:200px\""));
			this.context.put("selectDaerahC", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Utils.parseLong(idDaerahC), "", " onChange=\"doChangeDaerahCarian();\" "));
			this.context.put("selectMukimC", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Utils.parseLong(idMukimC), "", ""));

			//this.context.put("selectKementerianC", HTML.SelectKementerian("socKementerianC", Utils.parseLong(idkementerianC), " onChange=\"doChangeKementerian();\" "));
			//midified by rosli 20100804
			this.context.put("selectKementerianC", HTML.SelectKementerian("socKementerianC", Utils.parseLong(idkementerianC)," disabled=\"disabled\"", " onChange=\"doChangeKementerianCarian();\" "));
			this.context.put("selectAgensiC", HTML.SelectAgensiByKementerian("socAgensiC", idkementerianC, Utils.parseLong(idAgensiC), "  "));
			    
//			setupPage(session,action,list);

		}
		else
        //TAMBAH PENDAFTARAN PERLETAHAKAN
		if ("tambah".equals(actionPerletakhakan)) {
			myLog.info("tambah " +vm);
			vm = PATH+"frmPerletakhakanMaklumatFailOnline.jsp";
			
			context.put("mode1", "new");
			//this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeNegeri();\" "));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah), "", ""));

		    //this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idkementerian), " onChange=\"doChangeKementerian();\" "));
			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idkementerian)," disabled=\"disabled\"", " onChange=\"doChangeKementerian();\" "));
			this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idkementerian, Utils.parseLong(idAgensi), "  "));

			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("5","socSuburusan", Long.parseLong(idSuburusan),""));
			
			this.context.put("txtTajuk",getParam("txtTajuk"));
			this.context.put("noFail","");			
			this.context.put("txtNoFailKJP", getParam("txtNoFailKJP"));
			if ("".equals(getParam("txdTarikhSurKJP"))){
				this.context.put("txdTarikhSurKJP", lebah.util.Util.getDateTime(new Date(),"dd/MM/yyyy"));
			} else {
				this.context.put("txdTarikhSurKJP",getParam("txdTarikhSurKJP"));
			}			
			this.context.put("txtNoFailLain", getParam("txtNoFailLain"));
			
			this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPerletakhakanSema"));
		    this.context.put("semakclass", new FrmSemakan());		
	   
		} else if ("simpanBaru".equals(actionPerletakhakan)){	//SIMPAN BARU PENDAFTARAN PERLETAHAKAN BY ID_FAIL
			myLog.info("simpanBaru==");
			vm = PATH+"frmPerletakhakanMaklumatFailOnline.jsp";
			idFail = logic.saveOnline(getParam("txtTajuk"),getParam("txtNoFailKJP"),getParam("txdTarikhSurKJP"),getParam("txtNoFailLain"),idkementerian, idAgensi,idNegeri,idDaerah, idSuburusan, session);
		   
			idPermohonan = logic.getIdPermonanByIdFail(idFail);	   
			String[] cbsemaks = this.request.getParameterValues("sbcSemakan");
			FrmSemakan frmSemak = new FrmSemakan();
			frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
			if (cbsemaks != null) {
				for (int i = 0; i < cbsemaks.length; i++) {
					frmSemak = new FrmSemakan();
					frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
				}
			}
		   
		   //VIEW BALIK LEPAS SIMPAN
		   this.context.put("readonly", "readonly");
		   this.context.put("mode1", "view");
		   this.context.put("inputTextClass", "disabled");
		   viewPendaftaranPerletakhakanByIdFail(actionPerletakhakan,idFail);  
	   
		}
	   else
	   // PAPAR PENDAFTARAN PERLETAHAKAN BY ID_FAIL
	   if ("papar".equals(actionPerletakhakan)) {
		   	
			vm = PATH+"frmPerletakhakanMaklumatFailOnline.jsp";
			myLog.info("papar :: "+vm);
        	this.context.put("mode1", "view");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	this.context.put("page3", true);
        	
        	//check hakmilik has been transfer to rekod
        	boolean isTransfered = new FrmPerletakhakanMaklumatData().isFailTransfered(idFail);
        	this.context.put("isTransfered", isTransfered);
        	//VIEW PENDAFTARAN PERLETAHAKAN BY ID FAIL	
        	viewPendaftaranPerletakhakanByIdFail(actionPerletakhakan,idFail);			
		
	   } 
	   else 
	   //KEMASKINI PENDAFTARAN PERLETAHAKAN BY ID_FAIL
	   if("kemaskini".equals(actionPerletakhakan)){
		   myLog.info("kemaskini");
			vm = PATH+"frmPerletakhakanMaklumatFailOnline.jsp";
        	context.put("mode1", "update");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	Vector maklumatPermohonan = null;
       		maklumatPermohonan = new Vector();
       		list = new Vector();
    		logic.setMaklumatPermohonan(idFail);
    		maklumatPermohonan = logic.getMaklumatPermohonan();
    		Hashtable hashPermohonanDB = (Hashtable) maklumatPermohonan.get(0);

    		this.context.put("txtTajuk", getParam("txtTajuk") == null ?  hashPermohonanDB.get("txtTajuk") : getParam("txtTajuk"));
    		this.context.put("noFail", hashPermohonanDB.get("noFail"));
    		this.context.put("txtNoFailKJP", getParam("txtNoFailKJP") == null ?  hashPermohonanDB.get("txtNoFailKJP") : getParam("txtNoFailKJP"));
    		this.context.put("txdTarikhSurKJP", getParam("txdTarikhSurKJP") == null ?  hashPermohonanDB.get("txdTarikhSurKJP") : getParam("txdTarikhSurKJP"));
    		this.context.put("txtNoFailLain", getParam("txtNoFailLain") == null ?  hashPermohonanDB.get("txtNoFailLain") : getParam("txtNoFailLain"));
    		this.context.put("idFail", hashPermohonanDB.get("idFail"));
    		this.context.put("idPermohonan", hashPermohonanDB.get("idPermohonan"));
    		this.context.put("idHtpPermohonan",hashPermohonanDB.get("idHtpPermohonan"));
    		this.context.put("idSuburusanStatusFail",hashPermohonanDB.get("idSuburusanStatusFail"));
    		
    		this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPerletakhakanSema"));
    	    this.context.put("semakclass", new FrmSemakan());
        	
    	    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong((String) hashPermohonanDB.get("id_negeri")), "disabled", " class=\"disabled\""));
    	    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri((String) hashPermohonanDB.get("id_negeri"),"socDaerah", Long.parseLong((String) hashPermohonanDB.get("id_daerah")), "disabled", " class=\"disabled\""));
    		this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(id_kementerian), "disabled", " class=\"disabled\""));
    		this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Long.parseLong((String) hashPermohonanDB.get("id_agensi")), "disabled", " class=\"disabled\""));
    		this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("5","socSuburusan", Long.parseLong((String)hashPermohonanDB.get("idSuburusan")),"disabled", " class=\"disabled\""));
	
		}
	   	else
	   	//UPDATE PENDAFTARAN PERLETAKHAKAN BY ID_FAIL
		if("edit".equals(actionPerletakhakan)){
			myLog.info("edit");
			vm = PATH+"frmPerletakhakanMaklumatFailOnline.jsp";

			if(postDB){
				
			logic.update(getParam("txtTajuk"),
					getParam("txtNoFailKJP"), getParam("txdTarikhSurKJP"),
					getParam("txtNoFailLain"), idPermohonan, idHtpPermohonan, session);
			}
			
			String[] cbsemaks = this.request.getParameterValues("sbcSemakan");
			FrmSemakan frmSemak = new FrmSemakan();
			frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
			if (cbsemaks != null) {
				for (int i = 0; i < cbsemaks.length; i++) {
					frmSemak = new FrmSemakan();
					frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
				}
			}
			   
			//VIEW BALIK LEPAS UPDATE
        	this.context.put("mode1", "view");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
		
        	viewPendaftaranPerletakhakanByIdFail(actionPerletakhakan,idFail); 
		
		}
		else if("sahkan".equals(actionPerletakhakan)){
			myLog.info("sahkan");
//			new FrmPerletakhakanMaklumatData().transferToRecord(idFail);
//			vm = "app/htp/frmPerletakhakanMaklumatFail.jsp";
//        	this.context.put("mode1", "view");
//        	this.context.put("readonly", "readonly");
//        	this.context.put("inputTextClass", "disabled");
//        	boolean isTransfered = new FrmPerletakhakanMaklumatData().isFailTransfered(idFail);
//        	this.context.put("isTransfered", isTransfered);
//        	viewPendaftaranPerletakhakanByIdFail(actionPerletakhakan,idFail);
        	        	
		}else if ("maklumathakmilik".equals(actionPerletakhakan)){
			
			//vm = PATH+"frmPerletakhakanSenaraiHakmilikOnline.jsp";
			vm = PATH+"frmPerletakhakanTabHakmilikOnline.jsp";
			//myLog.info("maklumathakmilik :: "+vm);
			//HEADER
			beanHeader = new Vector();
			Hashtable hashHeader = null;
			perletakhakanData.setMaklumatHeader(idFail);
			beanHeader = perletakhakanData.getBeanHeader();
			if (beanHeader.size() != 0){
				//Hashtable 
				hashHeader = (Hashtable) perletakhakanData.getBeanHeader().get(0);				
				if (idPermohonan.isEmpty()){
					idPermohonan = hashHeader.get("idPermohonan").toString();
				}
			}
			this.context.put("List", beanHeader);
			
			//SENARAI HAKMILIK
			perletakhakanData.setMaklumatSenaraiHakmilik(idPermohonan);
	    	listHakmilik = perletakhakanData.getSenaraiHakmilik();
	    	this.context.put("Listhakmilik", listHakmilik);
	    	
	    	if ("newHakmilik".equals(mode)){
	    		myLog.info("newHakmilik");
	    		this.context.put("readonly", "");
	    		this.context.put("inputTextClass", "");
	    		
	    		
	    		//this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", " onChange=\"doChangeState();\" style=\"width:200px\""));
	    		//this.context.put("selectDaerah", HTML.SelectDaerah("socDaerah"));
    			if (idNegeri == "99999"){
    				idNegeri = String.valueOf(hashHeader.get("id_negeri"));
    			}		
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\" style=\"width:200px\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeState();\" style=\"width:200px\""));

				//this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),""," style=\"width:200px\""));
	    		this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(idJenisHakmilik), "", " style=\"width:200px\""));
	    		this.context.put("selectKategori", HTML.SelectKategori("socKategori", Long.parseLong(idKategori), "", " style=\"width:200px\" "));
	    		this.context.put("selectLuas", HTML.SelectLuas("socLuas_ ", Long.parseLong(idLuas), "", " style=\"width:200px\" onChange=\"doChangeState();\" "));
	    		this.context.put("selectLot", HTML.SelectLot("socLot", Long.parseLong(idLot), "", " style=\"width:200px\""));
	    		
	    		this.context.put("txtLokasi", getParam("txtLokasi"));
	    		this.context.put("txtCukaiSemasa",getParam("txtCukaiSemasa"));
	    		this.context.put("txtSyaratNyata",getParam("txtSyaratNyata"));
	    		this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
	    		this.context.put("txtNoStrata", getParam("txtNoStrata"));
	    		this.context.put("txtNoPelan", getParam("txtNoPelan"));
	    		this.context.put("txtLuasBersamaan", getParam("txtLuasBersamaan"));
	    		this.context.put("txtNoSyit", getParam("txtNoSyit"));
	    		this.context.put("txtSekatan", getParam("txtSekatan"));
	    		this.context.put("txtLot", getParam("txtLot"));
	    		this.context.put("txtLuas", getParam("txtLuas"));
	    		this.context.put("txtLuas1", getParam("txtLuas"));
	    		
	    	} else if ("doChangeState".equals(mode)){
	    		this.context.put("readonly", "");
	    		this.context.put("inputTextClass", "");
	    		//String socNegeri = getParam("socNegeri");
	    		myLog.info("doChangeState====" );	  
	    		String socNegeri = getParam("socNegeri");
	    		String socDaerah = getParam("socDaerah");
	    		if (socDaerah=="")
	    			socDaerah = "00";
	    		String socMukim = getParam("socMukim");
	    		if(socMukim=="")
	    			socMukim="00";
	    		
	    		System.out.println("pppppppppppppp"+socNegeri+"daerah"+socDaerah);
	    		idJenisHakmilik =getParam("socJenisHakmilik") == "" ? "0" : getParam("socJenisHakmilik");
	    		idKategori = getParam("socKategori") == "" ? "0" : getParam("socKategori");	    		
	    		idLuas = (getParam("socLuas") == "" ? "0" : getParam("socLuas"));
	    		if(idLuas == ""){
	    			idLuas = "00";
	    		}
	    		//myLog.info("idLuas="+idLuas);
	    		//myLog.info("getParam(\"socLuas_\")="+getParam("socLuas_"));
	    			
	    		idLot = getParam("socLot") == "" ? "0" : getParam("socLot");
	    		
	    		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(socNegeri), null," onChange=\"doChangeState();\" style=\"width:200px\""));
	    		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(socNegeri,"socDaerah", Long.parseLong(socDaerah),""," onChange=\"doChangeState();\" style=\"width:200px\""));
	    		this.context.put("selectMukim", HTML.SelectMukimByDaerah(socDaerah,"socMukim", Long.parseLong(socMukim),""," style=\"width:200px\""));	
	    		this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(idJenisHakmilik), "", " style=\"width:200px\""));		
	    		this.context.put("selectKategori", HTML.SelectKategori("socKategori", null, "", " style=\"width:200px\" "));
	    		this.context.put("selectLuas", HTML.SelectLuas("socLuas ", Long.parseLong(idLuas), " ", " style=\"width:200px\" onChange=\"doChangeState();\" ") );
	    		this.context.put("selectLot", HTML.SelectLot("socLot", Long.parseLong(idLot), "", " style=\"width:200px\""));
	    		
	    		this.context.put("txtLokasi", getParam("txtLokasi"));
	    		this.context.put("txtCukaiSemasa",getParam("txtCukaiSemasa"));
	    		this.context.put("txtSyaratNyata",getParam("txtSyaratNyata"));
	    		this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
	    		this.context.put("txtNoStrata", getParam("txtNoStrata"));
	    		this.context.put("txtNoPelan", getParam("txtNoPelan"));
	    		this.context.put("txtLuasBersamaan", getParam("txtLuasBersamaan"));
	    		this.context.put("txtNoSyit", getParam("txtNoSyit"));
	    		this.context.put("txtSekatan", getParam("txtSekatan"));
	    		this.context.put("txtLot", getParam("txtLot"));
	    		this.context.put("txtLuas", getParam("txtLuas"));	    		
	    		//detailHakmilik();	    	
	    		
	    	}  else if ("viewHakmilik".equals(mode)){
	    		myLog.info("viewHakmilik");
	    		this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				
	    		beanHakmilik = new Vector();
	    		perletakhakanData.setMaklumatHakmilik(idHakmilikurusan);
	    		beanHakmilik = perletakhakanData.getBeanHakmilik();
	    		
	    		if(beanHakmilik.size()!= 0){
	    			Hashtable hashPermohonanDB = (Hashtable) beanHakmilik.get(0);

	    			this.context.put("txtLokasi", hashPermohonanDB.get("LOKASI"));
	    			this.context.put("txtCukaiSemasa", hashPermohonanDB.get("CUKAI_TERKINI"));
	    			this.context.put("txtSyaratNyata", hashPermohonanDB.get("SYARAT"));
	    			this.context.put("txtNoPelan", hashPermohonanDB.get("NO_PELAN"));
	    			this.context.put("txtNoStrata", "");
	    			this.context.put("txtLuasBersamaan", hashPermohonanDB.get("LUAS_BERSAMAAN"));
	    			this.context.put("txtLuas1", hashPermohonanDB.get("LUAS"));
	    			this.context.put("txtSekatan", hashPermohonanDB.get("SEKATAN"));
	    			this.context.put("txtLot", hashPermohonanDB.get("NO_LOT"));
	    			this.context.put("txtLuas", hashPermohonanDB.get("LUAS"));
	    			this.context.put("txtLuas1", hashPermohonanDB.get("LUAS"));
	    			this.context.put("txtNoSyit", hashPermohonanDB.get("NO_SYIT"));
	    			this.context.put("txtNoHakmilik", hashPermohonanDB.get("NO_HAKMILIK"));
	    			
	    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) hashPermohonanDB.get("ID_NEGERI")),"disabled", " class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri((String) hashPermohonanDB.get("ID_NEGERI"),"socDaerah",Long.parseLong((String) hashPermohonanDB.get("ID_DAERAH")),"disabled", " class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectMukim", HTML.SelectMukimByDaerah((String) hashPermohonanDB.get("ID_DAERAH"),"socMukim",Long.parseLong((String) hashPermohonanDB.get("ID_MUKIM")),"disabled", " class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong((String) hashPermohonanDB.get("ID_JENISHAKMILIK")),"disabled"," class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectKategori", HTML.SelectKategori("socKategori",Long.parseLong((String) hashPermohonanDB.get("ID_KATEGORI")),"disabled"," class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectLuas", HTML.SelectLuas("socLuas",Long.parseLong((String) hashPermohonanDB.get("ID_LUAS")),"disabled"," class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectLot", HTML.SelectLot("socLot",Long.parseLong((String) hashPermohonanDB.get("ID_LOT")),"disabled"," class=\"disabled\" style=\"width:200px\""));
	    		}
	    		
	    	} else if ("updateHakmilik".equals(mode)){
	    		myLog.info("updateHakmilik");
	    		this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				
				beanHakmilik = new Vector();
				perletakhakanData.setMaklumatHakmilik(idHakmilikurusan);
	    		beanHakmilik = perletakhakanData.getBeanHakmilik();
	    		if(beanHakmilik.size()!= 0){
	    			Hashtable hashPermohonanDB = (Hashtable) beanHakmilik.get(0);
	    			
	    			if (idNegeri == "99999"){
	    				idNegeri = (String) hashPermohonanDB.get("ID_NEGERI");
	    			}		
	    			if (idDaerah == "99999"){
	    				idDaerah = (String) hashPermohonanDB.get("ID_DAERAH");
	    			}		
	    			if (idMukim == "99999"){
	    				idMukim = (String) hashPermohonanDB.get("ID_MUKIM");
	    			}		
	    			if (idJenisHakmilik == "99999"){
	    				idJenisHakmilik = (String) hashPermohonanDB.get("ID_JENISHAKMILIK");
	    			}		
	    			if (idKategori == "99999"){
	    				idKategori = (String) hashPermohonanDB.get("ID_KATEGORI");
	    			}		
	    			if (idLuas == "99999"){
	    				idLuas = (String) hashPermohonanDB.get("ID_LUAS");
	    			}		
	    			if (idLot == "99999"){
	    				idLot = (String) hashPermohonanDB.get("ID_LOT");
	    			}
	    		}
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\" style=\"width:200px\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),"",""));
	    		this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(idJenisHakmilik), "", " style=\"width:200px\""));
	    		this.context.put("selectKategori", HTML.SelectKategori("socKategori", Long.parseLong(idKategori), "", " style=\"width:200px\" "));
	    		this.context.put("selectLuas", HTML.SelectLuas("socLuas", Long.parseLong(idLuas), "", " style=\"width:200px\""));
	    		this.context.put("selectLot", HTML.SelectLot("socLot", Long.parseLong(idLot), "", " style=\"width:200px\""));
	    		
	    		this.context.put("txtLokasi", getParam("txtLokasi"));
	    		this.context.put("txtCukaiSemasa",getParam("txtCukaiSemasa"));
	    		this.context.put("txtSyaratNyata",getParam("txtSyaratNyata"));
	    		this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
	    		this.context.put("txtNoStrata", getParam("txtNoStrata"));
	    		this.context.put("txtNoPelan", getParam("txtNoPelan"));
	    		//this.context.put("txtLuasBersamaan", getParam("txtLuasBersamaan"));
	    		this.context.put("txtLuasBersamaan", getParam("Luas"));
	    		this.context.put("txtNoSyit", getParam("txtNoSyit"));
	    		this.context.put("txtSekatan", getParam("txtSekatan"));
	    		this.context.put("txtLot", getParam("txtLot"));
	    		this.context.put("txtLuas", getParam("txtLuas"));
	    		this.context.put("txtLuas1", getParam("txtLuas1"));

	    		
	    	}else if ("hapusHakmilik".equals(mode)){
	    		myLog.info("hapusHakmilik:idHakmilikurusan="+idHakmilikurusan);
	    		this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				getIHTP().hapusHakmilik(idHakmilikurusan);

				beanHakmilik = new Vector();
				perletakhakanData.setMaklumatHakmilik(idHakmilikurusan);
	    		beanHakmilik = perletakhakanData.getBeanHakmilik();
	    		if(beanHakmilik.size()!= 0){
	    			Hashtable hashPermohonanDB = (Hashtable) beanHakmilik.get(0);

	    			this.context.put("txtLokasi", hashPermohonanDB.get("LOKASI"));
	    			this.context.put("txtCukaiSemasa", hashPermohonanDB.get("CUKAI_TERKINI"));
	    			this.context.put("txtSyaratNyata", hashPermohonanDB.get("SYARAT"));
	    			this.context.put("txtNoPelan", hashPermohonanDB.get("NO_PELAN"));
	    			this.context.put("txtNoStrata", "");
	    			this.context.put("txtLuasBersamaan", hashPermohonanDB.get("LUAS_BERSAMAAN"));
	    			this.context.put("txtSekatan", hashPermohonanDB.get("SEKATAN"));
	    			this.context.put("txtLot", hashPermohonanDB.get("NO_LOT"));
	    			this.context.put("txtLuas", hashPermohonanDB.get("LUAS"));
	    			this.context.put("txtNoSyit", hashPermohonanDB.get("NO_SYIT"));
	    			this.context.put("txtNoHakmilik", hashPermohonanDB.get("NO_HAKMILIK"));
	    			
	    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) hashPermohonanDB.get("ID_NEGERI")),"disabled", " class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri((String) hashPermohonanDB.get("ID_NEGERI"),"socDaerah",Long.parseLong((String) hashPermohonanDB.get("ID_DAERAH")),"disabled", " class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectMukim", HTML.SelectMukimByDaerah((String) hashPermohonanDB.get("ID_DAERAH"),"socMukim",Long.parseLong((String) hashPermohonanDB.get("ID_MUKIM")),"disabled", " class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong((String) hashPermohonanDB.get("ID_JENISHAKMILIK")),"disabled"," class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectKategori", HTML.SelectKategori("socKategori",Long.parseLong((String) hashPermohonanDB.get("ID_KATEGORI")),"disabled"," class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectLuas", HTML.SelectLuas("socLuas",Long.parseLong((String) hashPermohonanDB.get("ID_LUAS")),"disabled"," class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectLot", HTML.SelectLot("socLot",Long.parseLong((String) hashPermohonanDB.get("ID_LOT")),"disabled"," class=\"disabled\" style=\"width:200px\""));
	    		}
	    		perletakhakanData.setMaklumatSenaraiHakmilik(idPermohonan);
		    	listHakmilik = perletakhakanData.getSenaraiHakmilik();
		    	this.context.put("Listhakmilik", listHakmilik);
		    	mode = "";
	    	}
		    this.context.put("socLuas", idLuas);
	    	
	    			
		}
		
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idHtpPermohonan", idHtpPermohonan);
		this.context.put("idSuburusanStatusFail", idSuburusanStatusFail);
		this.context.put("kodKementerian", getParam("kodKementerian"));
		this.context.put("kodNegeri", getParam("kodNegeri"));
		this.context.put("iscarian", isCarian);

		//SET DEFAULT PARAM
		this.context.put("actionPerletakhakan", actionPerletakhakan);
		this.context.put("mode", mode);
		
		//SET ID PARAM
		this.context.put("idHakmilikurusan", idHakmilikurusan);
		this.context.put("idPihakberkepentingan", idPihakberkepentingan);
		this.context.put("idBebanan", idBebanan);		
		return vm;
   
	}
	
	private void detailHakmilik()throws Exception {
		
		String socNegeri = getParam("socNegeri" == "" ? "0" : getParam("socNegeri"));
		System.out.println("pppppppppppppp"+socNegeri);
		String socDaerah = getParam("socDaerah" == "" ? "0" : getParam("socDaerah"));
		String socMukim = getParam("socMukim" == "" ? "0" : getParam("socMukim"));
		String idJenisHakmilik =getParam("socJenisHakmilik" == "" ? "0" : getParam("socJenisHakmilik"));
		String idKategori = getParam("socKategori" == "" ? "0" : getParam("socKategori"));
		String idLuas = getParam("socLuas" == "" ? "0" : getParam("socLuas"));
		String idLot = getParam("socLot" == "" ? "0" : getParam("socLot"));
		
		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(socNegeri), null," onChange=\"doChangeState();\" style=\"width:200px\""));
		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(socNegeri,"socDaerah", Long.parseLong(socDaerah),""," onChange=\"doChangeState();\""));
		this.context.put("selectMukim", HTML.SelectMukimByDaerah(socDaerah,"socMukim", Long.parseLong(socMukim),"",""));	
		this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(idJenisHakmilik), "", " style=\"width:200px\""));		
		this.context.put("selectKategori", HTML.SelectKategori("socKategori", Long.parseLong(idKategori), "", " style=\"width:200px\" "));
		this.context.put("selectLuas", HTML.SelectLuas("socLuas", Long.parseLong(idLuas), "", " style=\"width:200px\""));
		this.context.put("selectLot", HTML.SelectLot("socLot", Long.parseLong(idLot), "", " style=\"width:200px\""));
		
		this.context.put("txtLokasi", getParam("txtLokasi"));
		this.context.put("txtCukaiSemasa",getParam("txtCukaiSemasa"));
		this.context.put("txtSyaratNyata",getParam("txtSyaratNyata"));
		this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		this.context.put("txtNoStrata", getParam("txtNoStrata"));
		this.context.put("txtNoPelan", getParam("txtNoPelan"));
		this.context.put("txtLuasBersamaan", getParam("txtLuasBersamaan"));
		this.context.put("txtNoSyit", getParam("txtNoSyit"));
		this.context.put("txtSekatan", getParam("txtSekatan"));
		this.context.put("txtLot", getParam("txtLot"));
		this.context.put("txtLuas", getParam("txtLuas"));
		
	}

	//********************************* SENARAI METHOD *******************************************************************
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
	//VIEW PENDAFTARAN PERLETAHAKAN BY ID FAIL
	private void viewPendaftaranPerletakhakanByIdFail(String actionPerletakhakan,String idFail) throws Exception {
		
		Vector maklumatPermohonan = null;
   		maklumatPermohonan = new Vector();
   		Vector list = null;
   		list = new Vector();
		logic.setMaklumatPermohonan(idFail);
		maklumatPermohonan = logic.getMaklumatPermohonan();
		Hashtable hashPermohonanDB = (Hashtable) maklumatPermohonan.get(0);

		this.context.put("txtTajuk", hashPermohonanDB.get("txtTajuk"));
		this.context.put("noFail", hashPermohonanDB.get("noFail"));
		this.context.put("noP", hashPermohonanDB.get("noP"));
		this.context.put("txtNoFailKJP", hashPermohonanDB.get("txtNoFailKJP"));
		this.context.put("txdTarikhSurKJP", hashPermohonanDB.get("txdTarikhSurKJP"));
		this.context.put("txtNoFailLain", hashPermohonanDB.get("txtNoFailLain"));
		this.context.put("idFail", hashPermohonanDB.get("idFail"));
		this.context.put("idPermohonan", hashPermohonanDB.get("idPermohonan"));
		this.context.put("idHtpPermohonan",hashPermohonanDB.get("idHtpPermohonan"));
		this.context.put("idSuburusanStatusFail",hashPermohonanDB.get("idSuburusanStatusFail"));
		
		this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPerletakhakanSema"));
	    this.context.put("semakclass", new FrmSemakan());
		
	    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong((String) hashPermohonanDB.get("id_negeri")), "disabled", " class=\"disabled\" "));
	    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri((String) hashPermohonanDB.get("id_negeri"), "socDaerah", Long.parseLong((String) hashPermohonanDB.get("id_daerah")), "disabled", " class=\"disabled\""));
		this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong((String) hashPermohonanDB.get("id_kementerian")), "disabled", " class=\"disabled\""));
		this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Long.parseLong((String) hashPermohonanDB.get("id_agensi")), "disabled", " class=\"disabled\""));
		this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("5","socSuburusan", Long.parseLong((String)hashPermohonanDB.get("idSuburusan")),"disabled", " class=\"disabled\""));
	}	

	
	private IPenggunaKementerian getIPengguna(){
		if(iPengguna==null){
			iPengguna = new PenggunaKementerianBean();
		}
		return iPengguna;
		
	}
	  
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}	 
 	
	
}