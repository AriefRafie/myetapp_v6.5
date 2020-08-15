/*Insert into TBLRUJAGENSI
   (ID_AGENSI, KOD_AGENSI, NAMA_AGENSI, ALAMAT1, ALAMAT2, 
    ALAMAT3, POSKOD, ID_NEGERI, JAWATAN, ID_KEMENTERIAN, 
    ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI)
 Values
   (751, '01', 'SURUHANJAYA KOPERASI MALAYSIA', 'Tingkat 5-7, Blok J,', 'Pusat Bandar Damansara,', 
    'TIADA', '50608', 14, 'TIADA', 25, 
    1, TO_DATE('01/20/0003 10:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, TO_DATE('01/20/0003 10:00:00', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;*/
package ekptg.view.online.htp.gadaian;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.entities.Tblrujagensi;
import ekptg.model.entities.Tblrujkementerian;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmGadaianHakmilikData;
import ekptg.model.htp.FrmGadaianInfoData;
import ekptg.model.htp.FrmGadaianPeguamData;
import ekptg.model.htp.FrmGadaianPenHamilikData;
import ekptg.model.htp.FrmGadaianSemakan1Data;
import ekptg.model.htp.FrmGadaianSemakanData;
import ekptg.model.htp.FrmGadaianSenaraiPermohonanData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailGadaianData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.GadaianSubUrusanAgensi;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHTPHakmilikUrusan;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.gadaian.HTPHakmilikUrusanGadaianBean;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;


public class FrmKJPGadaianA extends AjaxBasedModule {
	
	private final String PATH="app/htp/gadaian/online/";
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmGadaianA.class);
 	private IHTPHakmilikUrusan iHakmilikUrusan = null;  
	private IPembelian iPembelian = null;
	private String result = new String();
	private Date now = new Date();
    private SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
    String mode = "";
    String submit = "";
    String doChange = "";
    private String idHTPGadaian = "";
    private String idPermohonan = "";
    private Long idJenisHakmilik = 0L;
    private String idUser = "";
    private String idNegeri = "0";
    private String idSubUrusan = "0";
    private String idFail = "0";
    FrmGadaianPenHamilikData gadaianData = null;
    FrmGadaianHakmilikData hakmilikData = null;
	private HtpPermohonan htpPermohonan = null;
	UtilHTML utilHTML = new UtilHTML();
	private IHtp iHTP = null;  
	private Tblrujsuburusanstatusfail subUrusanStatusFail = null;
	private FrmGadaianSemakanData gadaiandata = null;
	private FrmGadaianSenaraiPermohonanData permohonandata = null;
	private Vector<Hashtable<String,String>> senarai = new Vector<Hashtable<String,String>>();
	private Vector<Hashtable<String,String>> senaraiPermohonan = new Vector<Hashtable<String,String>>();
	
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String action = "";
    	String vm = "";
    	String disability = "";
    	String readability = "";
    	String style1 = "";
    	String style2 = "";
    	Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
		if(session == null)
			throw new Exception("[KJP GADAIAN] SILA LOGIN SEMULA");

    	list.clear();

		action = getParam("action");
		submit = getParam("command");
		mode = getParam("mode");
		String selectedTab = getParam("tabId");
		doChange = getParam("doChange");
		idHTPGadaian = getParam("idHTPGadaian");
		if(!getParam("socJenisHakmilik").equals(""))
			idJenisHakmilik = Long.parseLong(getParam("socJenisHakmilik"));
		
		//myLog.info("command : " + submit);
		//myLog.info("mode : " + mode);
		
		if (selectedTab == null || "".equals(selectedTab)) {
			selectedTab = "0";
		}
		this.context.put("selectedTab", selectedTab);
		idUser = (String)session.getAttribute("_ekptg_user_id");		
		idPermohonan = getParam("idPermohonan");
		//2017/12/22
		gadaiandata = new FrmGadaianSemakanData();
		permohonandata = new FrmGadaianSenaraiPermohonanData();

		try{
			if(submit.equals("FailBaru")){				
				vm = PATH+"frmKJPGadaianSemakan2Ajax.jsp";
				String idFail = "0";
				if("view".equals(mode)){					
					readability = "readonly";
				    disability = "disabled";
				    idFail = getParam("idFail");
				    setFailBaru(session,idFail);
				    style2 = "none";
					ViewFailBaru(session,disability,readability,style1,style2);
				
				}else if("kemaskini".equals(mode)){
					idFail = getParam("idFail").toString();
				    setFailBaru(session,idFail);
				    style1 = "none";
					ViewFailBaru(session,disability,readability,style1,style2);
				
				}else if("simpan".equals(mode)){
					readability = "readonly";
				    disability = "disabled";
				    idFail = SimpanFailBaru(session);
	    			AuditTrail.logActivity("1", "3", this, session, "INS", "FAIL PERMOHONAN ["+getParam("txtNoFailSek")+"] DITAMBAH ");
					setFailBaru(session,idFail);
					style2 = "none";
					ViewFailBaru(session,disability,readability,style1,style2);
					this.context.put("SimpanStatus", "success");
					this.context.put("ResultSimpan", result);
				
				}else if(mode.equalsIgnoreCase("dochangeurusan")){
					DataFailBaru(session, disability, readability, style1, style2);
					
				}else{					
					style1 = "none";	
					DataFailBaru(session, disability, readability, style1, style2);

				}
				//myLog.info("GadaianProcess::FailBaru");
				
			}else if(submit.equals("SenaraiPermohonan")){		
				vm = PATH+"frmKJPGadaianSenaraiPermohonanAjax.jsp";
				String idFail = getParam("idFail");
				String carian = getParam("NamaPemohon");
				String noFailKJP = getParam("noFailKJP");
//				ListPermohonan(session, idFail,noFailKJP, carian);
//				list = FrmGadaianSenaraiPermohonanData.getListPermohonan();
				permohonandata = new FrmGadaianSenaraiPermohonanData();
				list = permohonandata.getSenaraiPermohonan(idFail,noFailKJP, carian);
			    this.context.put("senaraiList1", list);
				doListing(session,action,submit,mode,list);
				
			    this.context.put("SenaraiPermohonan", list);
			    this.context.put("NoFail", getParam("noFail"));
			    this.context.put("IdFail", getParam("idFail"));
			    this.context.put("carian", getParam("NamaPemohon"));
				//myLog.info("GadaianProcess::SenaraiPermohonan");
			    
			}else if(submit.equals("Semakan")){			
				vm = PATH+"frmKJPGadaianSemakanAjax.jsp";
				String idPermohonan = "0";
				this.context.put("NoFail", getParam("noFail"));
			    this.context.put("IdFail", getParam("idFail"));
			    this.context.put("IdPermohonan", getParam("idPermohonan"));
			    this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmGadaianSemakan"));
			    this.context.put("semakclass", new FrmSemakan());

				if("baru".equals(mode)){
					style1 = "none";
					ListSemakBaru(session);
				    DataSemakBaru(session,disability,readability,style1,style2);
				    //myLog.info("GadaianProcess::Semakan::baru");
				
				}else if("kemaskini".equals(mode)){
					style1 = "none";
					ListSemak(session,idPermohonan);
				    DataSemak(session,disability,readability,style1,style2);
				
				}else if(mode.equals("simpan")){
					vm = PATH+"frmKJPGadaianSemakanAjax.jsp";
					readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
					idPermohonan = SimpanSemak(idUser);					
					myLog.info("Semakan:mode=simpan::idpermohonan=" + idPermohonan );
					
					String[] cbsemaks = this.request.getParameterValues("cbsemaks");									
		        	FrmSemakan frmSemak = new FrmSemakan();
		        	frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
		        	if(cbsemaks!=null){
		        		for (int i = 0; i < cbsemaks.length; i++) { 
		        			frmSemak = new FrmSemakan();
		        			//myLog.info("GadaianProcess:Semakan::simpan::cbsemaks:::"+cbsemaks[i]);
		        			FrmSemakan.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan)); 
		        			
		        		}
		        		
		        	}
						        	
					ListSemak(session,idPermohonan);
				    DataSemak(session,disability,readability,style1,style2);

				    this.context.put("IdPermohonan", idPermohonan);
				    this.context.put("SimpanStatus", "success");
					this.context.put("ResultSimpan", result);			
					
				}else if(mode.equals("hapus")){
					
				}else{					
				    readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
				    
				    ListSemak(session,idPermohonan);
				    DataSemak(session,disability,readability,style1,style2);
				
				}
			    //myLog.info("GadaianProcess::SEMAKAN");
				
			}else if(submit.equals("PenHakmilik")){				
		    	myLog.info("PenHakmilik:mode="+mode);
				vm = PATH+"frmKJPGadaianMaklumatHakmilik.jsp";

				getPermohonanInfo();
			    this.context.put("NoFail", getParam("noFail"));
			    this.context.put("IdFail", getParam("idFail"));
			    this.context.put("IdPermohonan", getParam("idPermohonan"));
			    
			    if(mode.equals("baru")){
			    	
			    }else if(mode.equals("kemaskini")){
			    	myLog.info(mode);
			    	style1 = "none";
				    DataPenHamilik(session, disability, readability,style1,style2);
			    
			    }else if(mode.equals("simpan")){
			    	myLog.info(mode);
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
			    	SimpanPenHamilik(session);
				    DataPenHamilik(session, disability, readability,style1,style2);
				    this.context.put("SimpanStatus", "success");
					this.context.put("ResultSimpan", result);
			    
			    }else if(mode.equals("hapus")){
			    	
			    }else if("doChangeDaerah".equalsIgnoreCase(mode)){	
			    	myLog.info("mode="+mode);
			    	readability = "readonly";
				    disability = "disabled";
			    	doChangeDaerah(session,disability, readability, style1,style2);
			    	
			    }else if("doChangeDaerahKemaskini".equalsIgnoreCase(mode)){			
			    	myLog.info("mode=doChangeDaerahKemaskini");
			    	String negeri = getParam("selectNegeri");		
					String daerah = getParam("socDaerah");
					if(daerah =="")
						daerah = "00";
					String mukim = getParam("socMukim");
					if(mukim=="")
						mukim = "00";
			    	readability = "readonly";
				    disability = "disabled";
				    this.context.put("selectNegeri",HTML.SelectNegeri("selectNegeri", Long.parseLong(negeri), "", " onchange=\"doChangeDaerahKemaskini()\" class=\"mediumselect\" "));
				    this.context.put("IdNegeri",negeri);	
				    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(negeri, "socDaerah", Long.parseLong(daerah), "", " onchange=\"doChangeDaerahKemaskini()\" class=\"mediumselect\" "));
				    this.context.put("selectMukim",HTML.SelectMukimByDaerah(daerah, "socMukim", " class=\"mediumselect\" "));
			    	doChangeDaerahKemaskini(session,disability, readability, style1,style2);
			   
			    }
//			    	else if(mode.equals("hakmilikadd")){				
//					vm = PATH+"frmGadaianMaklumatHakmilikAdd.jsp";
//			    	myLog.info(mode);
//			    	style1 = "none";
//			    	mode = "kemaskini";
//				    DataPenHamilik(disability, readability,style1,style2);
//			    	
//			    }
			    	else{
			    	myLog.info("PenHakmilik view");
				    readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
				    DataPenHamilik(session, disability, readability,style1,style2);
			    }		    
			    
			    
			}else if (submit.equals("Hakmilik")){				
				vm = PATH+"frmKJPGadaianPemilikPeguam.jsp";
				getPermohonanInfo();
				idHTPGadaian = String.valueOf(htpPermohonan.getIdHtpPermohonan());
				idPermohonan = getParam("idPermohonan");
				this.context.put("IdPermohonan", idPermohonan);
			    this.context.put("NoFail", getParam("noFail"));
			    this.context.put("IdFail", getParam("idFail"));
			    this.context.put("IdHakmilikurusan", getParam("idHakmilikurusan"));
				list = FrmGadaianInfoData.getSemak(idPermohonan);
				this.context.put("Info", list);
				Hashtable<String,String> h = (Hashtable<String,String>) list.get(0);
				idFail = String.valueOf(h.get("idFail"));
				idSubUrusan = String.valueOf(h.get("idSuburusan"));
				
				this.context.put("selectKementerian",getKementerian(Long.parseLong(h.get("idKementerian").toString())));
			    this.context.put("selectAgensi",getAgensi(Long.parseLong(h.get("idAgensi").toString())));
			    this.context.put("selectNegeri",getNegeri(h.get("idNegeri").toString()));
				this.context.put("selectSuburusan",getSubUrusan(h.get("idSuburusan").toString()));
				this.context.put("idSuburusan", h.get("idSuburusan").toString());
				idNegeri = String.valueOf(h.get("idNegeri"));
			    if(mode.equals("simpanHakmilik")){
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
			    	SimpanHakmilik(session);
			    	ListHakmilik(session);
			    	DataHakmilik(session,disability,readability,style1,style2);
			    	this.context.put("SimpanStatus", "success");
					this.context.put("ResultSimpan", result);
			    
			    }else if(mode.equals("kemaskiniHakmilik")){
			    	style1 = "none";
			    	ListHakmilik(session);
			    	DataHakmilik(session,disability,readability,style1,style2);
		    	
			    }else if(mode.equals("kemaskiniHakmilikChangeNegeri")){
			    	style1 = "none";
			    	DataHakmilikChange(session,disability,readability,style1,style2);
		    	
			    }else if(mode.equals("hakmilikview")){				
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
					//ListHakmilik(session);
					DataHakmilik(session,disability,readability,style1,style2);
			    
			    }else if(mode.equals("hapusHakmilik")){
			    	
			    }else if(mode.equals("simpanPeguam")){
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
			    	SimpanPeguam(session);
			    	ListPeguam(session);
			    	DataPeguam(session,disability,readability,style1,style2);				
					this.context.put("SimpanStatus", "success");
					this.context.put("ResultSimpan", result);
					this.context.put("selectedTab", 1);
			    
			    }else if(mode.equals("kemaskiniPeguam")){
			    	style1 = "none";
			    	ListPeguam(session);
			    	DataPeguam(session,disability,readability,style1,style2);
			    	this.context.put("selectedTab", 1);
			    
			    }else if("hapusPeguam".equals(mode)){
			    	
			    }else if("peguamview".equals(mode)){
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
					ListPeguam(session);
					DataPeguam(session,disability,readability,style1,style2);
					this.context.put("selectedTab", 1);
			    
			    }else if("geranview".equalsIgnoreCase(mode)){
			    	GeranView(idHTPGadaian);
			    	this.context.put("selectedTab", 2);
			    
			    }else if("simpangeran".equalsIgnoreCase(mode)){
			    	GeranSimpan(session);
			    	GeranView(idHTPGadaian);
			    	this.context.put("selectedTab", 2);
			    	
			    }else if("kemaskinigeran".equalsIgnoreCase(mode)){
			    	GeranView(idHTPGadaian);
			    	 this.context.put("mode", "");
					 this.context.put("classDis", "");
			    	this.context.put("selectedTab", 2);
			    	
			    }else if("simpanupdategeran".equalsIgnoreCase(mode)){
			    	GeranUpdateSimpan(session);
			    	GeranView(idHTPGadaian);
			    	this.context.put("selectedTab", 2);
			    	
			    }else if("maklumat16AView".equalsIgnoreCase(mode)){
			    	maklumat16AView(idHTPGadaian);
			    	this.context.put("selectedTab", 3 );
			    
			    }else if("simpan16a".equalsIgnoreCase(mode)){
			    	maklumat16ASimpan(session);
			    	maklumat16AView(idHTPGadaian);
			    	this.context.put("selectedTab", 3);
			    	
			    }else if("kemaskini16a".equalsIgnoreCase(mode)){
			    	maklumat16AView(idHTPGadaian);
			    	 this.context.put("mode", "");
					 this.context.put("classDis", "");
			    	this.context.put("selectedTab", 3);
			    	
			    }else if("simpanupdate16a".equalsIgnoreCase(mode)){
			    	maklumat16AUpdateSimpan(session);
			    	maklumat16AView(idHTPGadaian);
			    	this.context.put("selectedTab", 3);
			    	
			    }else if("maklumat16NView".equalsIgnoreCase(mode)){
			    	maklumat16NView(idHTPGadaian);
			    	this.context.put("selectedTab", 4 );
			    
			    }else if("simpan16n".equalsIgnoreCase(mode)){
			    	maklumat16NSimpan(session);
			    	maklumat16NView(idHTPGadaian);
			    	this.context.put("selectedTab", 4);
			    	
			    }else if("kemaskini16n".equalsIgnoreCase(mode)){
			    	maklumat16NView(idHTPGadaian);
			    	 this.context.put("mode", "");
					 this.context.put("classDis", "");
			    	this.context.put("selectedTab", 4);
			    	
			    }else if("simpanupdate16n".equalsIgnoreCase(mode)){
			    	maklumat16NUpdateSimpan(session);
			    	maklumat16NView(idHTPGadaian);
			    	this.context.put("selectedTab", 4);
			    	
			    }else if("borangdview".equalsIgnoreCase(mode)){
			    	BorangDView(idHTPGadaian);
			    	this.context.put("selectedTab", 5 );
			    
			    }else if("simpanborangd".equalsIgnoreCase(mode)){
			    	BorangDSimpan(session);
			    	BorangDView(idHTPGadaian);
			    	this.context.put("selectedTab", 5);
			    	
			    }else if("kemaskiniborangd".equalsIgnoreCase(mode)){
					BorangDView(idHTPGadaian);
					this.context.put("mode", "");
					this.context.put("classDis", "");
					this.context.put("selectedTab", 5);
			    	
			    }else if("simpanupdateborangd".equalsIgnoreCase(mode)){
			    	BorangDUpdateSimpan(session);
			    	BorangDView(idHTPGadaian);
			    	this.context.put("selectedTab", 5);
			    	
			    }else if("selesaiview".equalsIgnoreCase(mode)){
			    	myLog.info("selesaiview");
			    	statusView(idPermohonan);
			    	this.context.put("selectedTab", 9);
			    
			    }else if("selesaisimpan".equalsIgnoreCase(mode)){
			    	myLog.info("selesaisimpan");
			    	myLog.info("selesaisimpan 1="+idFail);
			    	myLog.info("selesaisimpan 2="+idPermohonan);
			    	myLog.info("selesaisimpan 3="+idSubUrusan);
			    	kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,"99");
			    	statusView(idPermohonan);
			    	this.context.put("selectedTab", 9);
			    	
			    }else if("selesaikemaskini".equalsIgnoreCase(mode)){
			    	myLog.info("selesaikemaskini");
			    	statusView(idPermohonan);
			    	this.context.put("mode", "");
					this.context.put("classDis", "");
			    	this.context.put("selectedTab", 9);
			    	
			    }else if("selesaikemaskinisimpan".equalsIgnoreCase(mode)){
			    	myLog.info("selesaikemaskinisimpan");
			    	kemaskiniSimpanStatusSelesai(idFail,idPermohonan);
			    	statusView(idPermohonan);
			    	this.context.put("selectedTab", 9);
			    	
			    }
			    
//				if (selectedTab == null || "".equals(selectedTab)) {
//					selectedTab = "0";
//				}
//				this.context.put("selectedTab", selectedTab);
			    
				//myLog.info("GadaianProcess::Hakmilik");
			
			}else{	
		    	myLog.info("page senarai fail");
			    this.context.remove("lists");
				String page = getParam("namaskrin");
				vm = PATH+"frmKJPSenaraiFailGadaianAjax.jsp";
				
				String key_cari = getParam("NamaFail");
				String keyNo_cari = getParam("NoFail");
				String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
				
				/**Penambahbaikan Phase 4 (07102014) : Tiada field carian untuk carian nama pemilik / nama fail lain / No.IC */
				String keyNo_upt = getParam("NoFailUpt");
				String keyNo_ptg = getParam("NoFailPtg");
				String keyNo_ptd = getParam("NoFailPtd");
				String keyNo_pemilik = getParam("NamaPemilik");
				String keyNo_rujukan = getParam("NoRujukan");
				this.context.put("carianNoFailUpt", keyNo_upt);
				this.context.put("carianNoFailPtg", keyNo_ptg);
				this.context.put("carianNoFailPtd", keyNo_ptd);
				this.context.put("carianPemilik", keyNo_pemilik);
				this.context.put("carianNoRujukan", keyNo_rujukan);
				
				Long idNegeri = Long.parseLong(Negeri);
				list = ListFail(session, key_cari, keyNo_cari, idNegeri, keyNo_upt, keyNo_ptg, keyNo_ptd, keyNo_pemilik, keyNo_rujukan);
		    	//list = FrmSenaraiFailGadaianData.getList();
			    this.context.put("lists", list);
				//doListing(session,action,"",mode,list);
				setupPage(session,action,list);

		    	//this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,disability));
		    	this.context.put("selectNegeri",UtilHTML.SelectNegeri("socNegeri",idNegeri,disability));
			    this.context.put("carian", getParam("NamaFail"));
			    this.context.put("carianNoFail", getParam("NoFail"));
			    this.context.put("Negeri", idNegeri);
				//myLog.info("GadaianProcess::SenaraiFail");
			    
			    if (mode.equalsIgnoreCase("cancel")){
			    	this.context.put("clear", "clear");
			    }
			    if(!page.equals("")){
			    	myLog.info("page permohonan:"+page);
			    	vm = PATH+"frmKJPGadaianSenaraiPermohonanAjax.jsp";
					String idFail = getParam("idFail");
					String carian = getParam("NamaPemohon");
					String noFailKJP = getParam("noFailKJP");
//					ListPermohonan(session, idFail,noFailKJP, carian);
//					list = FrmGadaianSenaraiPermohonanData.getListPermohonan();
					permohonandata = new FrmGadaianSenaraiPermohonanData();
					list = permohonandata.getSenaraiPermohonan(idFail,noFailKJP, carian);
					doListing(session,action,submit,mode,list);
					
				    //this.context.put("SenaraiPermohonan", list);
				    this.context.put("NoFail", getParam("noFail"));
				    this.context.put("IdFail", getParam("idFail"));
				    this.context.put("carian", getParam("NamaPemohon"));
			    }
			}
						
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("[HTP GADAIAN] SILA LOGIN SEMULA");
		}
		
		//myLog.info("vm :" + vm);
		this.context.put("idHTPGadaian", idHTPGadaian);
		return vm;
		
    }//close do template
	
	/** Fail Baru Controller */
	private void DataFailBaru(HttpSession session, String disability, String readability, String style1, String style2) 
		throws Exception{
		try{
			//myLog.info("data file baru : mode :: " + mode);			
			if(mode.equalsIgnoreCase("doChangeUrusan")){
				String urusan = getParam("socSuburusan");		
				this.context.put("Semak", "");
			    this.context.put("selectSuburusan",getSubUrusanForGadaian("socSuburusan", Long.parseLong(urusan), "", "onchange=\"doChangeUrusan()\" "));
			    this.context.put("selectAgensi",getAgensiDanKementerianForGadaian("socAgensi", urusan,""));
			    this.context.put("selectNegeri",UtilHTML.SelectNegeri("socNegeri"));
			    this.context.put("selectTajuk", getTajukForGadaian("socTajuk",urusan, ""));
			    this.context.put("datenow", formatter.format(now));
			    this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			    this.context.put("pagemode", "new");
			    
			}else{	
				this.context.put("Semak", "");
				this.context.put("selectNegeri",UtilHTML.SelectNegeri("socNegeri"));
			    this.context.put("selectAgensi",getAgensiDanKementerianForGadaian("socAgensi"));  
			    this.context.put("selectSuburusan",getSubUrusanForGadaian("socSuburusan","onchange=\"doChangeUrusan()\" "));
			    this.context.put("selectTajuk", getTajukForGadaian("socTajuk"));
			    this.context.put("datenow", formatter.format(now));
			    this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			    this.context.put("pagemode", "new");
				
			}
						
		}catch(Exception ex){
			myLog.info("GadaianProcess::DataFailBaru::Exception = "+ex.getMessage());
			ex.printStackTrace();
		}

	}
	
	public void setFailBaru(HttpSession session, String idFail) throws Exception{
		try{
			FrmGadaianSemakan1Data.setSemak(idFail);	
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
	}
	
	private void ViewFailBaru(HttpSession session, String disability, String readability, String style1, String style2) 
		throws Exception{
		Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
		list.clear();
		try{
			if(this.mode.equalsIgnoreCase("kemaskini")){
				
				list = FrmGadaianSemakan1Data.getSemak();
			    this.context.put("Semak", list);
			    Hashtable<String,String> h = (Hashtable<String,String>) list.get(0);	
			    String urusan = h.get("idSuburusan").toString();
			    
			    this.context.put("selectSuburusan",getSubUrusanForGadaian("socSuburusan", Long.parseLong(urusan), "", "onchange=\"doChangeUrusan()\" "));
			    this.context.put("selectAgensi",getAgensiDanKementerianForGadaian("socAgensi", urusan, ""));
			    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()), "")); 		    
			    this.context.put("socKementerian", h.get("idKementerian").toString());

			    this.context.put("selectTajuk", getTajukForGadaian("socTajuk", urusan, ""));
			    this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			    this.context.put("pagemode", "kemaskini");
				
			}
			else{
				
				list = FrmGadaianSemakan1Data.getSemak();
			    this.context.put("Semak", list);
			    Hashtable<String,String> h = (Hashtable<String,String>) list.get(0);				    
			    myLog.info("GadaianA :: ViewFailBaru : " + h);			    
//			    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()), "disabled"));
			    this.context.put("selectNegeri",getNegeri((String)h.get("idNegeri")));			    		    
			    //fir
			    this.context.put("socKementerian", h.get("idKementerian").toString());
			    this.context.put("selectAgensi",getAgensiDanKementerianName(Long.parseLong((String)h.get("idKementerian"))));			    
//			    myLog.info("viewfailbaru : idkem : "  + h.get("idKementerian").toString());
//			    myLog.info("GadaianA :: VIewFaiBaru : " + getAgensiDanKementerianForGadaian("socAgensi", Long.parseLong(h.get("idKementerian").toString()), "disabled"));		    
//			    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()), "disabled"));
			    this.context.put("selectSuburusan",getSubUrusan((String)h.get("idSuburusan")));
			    this.context.put("selectTajuk", h.get("tajuk"));
			    
			    this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			    this.context.put("pagemode", "view");
				
			}
			
//			list = FrmGadaianSemakan1Data.getSemak();
//		    this.context.put("Semak", list);
//		    Hashtable h = (Hashtable) list.get(0);	
//		    
//		    myLog.info("GadaianA :: ViewFailBaru : " + h);
//		    
////		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()), "disabled"));
//		    this.context.put("selectNegeri",getNegeri((String)h.get("idNegeri")));
//		    		    
//		    //fir
//		    this.context.put("socKementerian", h.get("idKementerian").toString());
//		    this.context.put("selectAgensi",getAgensiDanKementerianName(Long.parseLong((String)h.get("idKementerian"))));
//		    
////		    myLog.info("viewfailbaru : idkem : "  + h.get("idKementerian").toString());
////		    myLog.info("GadaianA :: VIewFaiBaru : " + getAgensiDanKementerianForGadaian("socAgensi", Long.parseLong(h.get("idKementerian").toString()), "disabled"));
//		    
//		    
////		    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()), "disabled"));
//		    this.context.put("selectSuburusan",getSubUrusan((String)h.get("idSuburusan")));
//		    this.context.put("selectTajuk", h.get("tajuk"));
//		    
//		    this.context.put("modeSoc", disability);
//		    this.context.put("mode", readability);
//		    this.context.put("Style1", style1);
//		    this.context.put("Style2", style2);
//		    this.context.put("pagemode", "view");
		}
		catch(Exception ex){
			myLog.error("GadaianProcess::ListFailBaru::Exception = "+ex.getMessage());
		}
	}
	
	/* this method is used for automated id fail generation*/
//	private int SimpanFailBaru(HttpSession session) throws Exception
//	{
//		int idFail = 0;
//		try{
//			if(getParam("idFail") == ""){
//				//fail baru
//				Hashtable h = new Hashtable();			
//				h.put("idNegeri", Integer.parseInt(getParam("socNegeri")));
//								
//				//fir ganti id kementerian ngan cara baru
//				h.put("idKementerian", Integer.parseInt(getParam("socAgensi")));
//				h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));		
//				h.put("Tajuk", getParam("txtTajuk"));
//				
//				myLog.info("GadaianProcessA::SimpanSemak:: h = "+h);
//				idFail = FrmGadaianSemakan1Data.simpan(h);
//				result = "baru";
//			}
//			else{
//				
//				//kemaskini fail
//				Hashtable h = new Hashtable();
//				h.put("idFail", Integer.parseInt(getParam("idFail")));			
//				h.put("Tajuk", getParam("txtTajuk"));
//				////myLog.info("GadaianProcess::SimpanSemak:: h = "+h);
//				idFail = FrmGadaianSemakan1Data.update(h);
//				result = "kemaskini";
//			
//			}
//			
//		}
//		catch(Exception e){
//			myLog.error("Error : " + e.getMessage());
//		}
//		
//		return idFail;
//	}
	
	/*this method is for manual idfail*/
	private String SimpanFailBaru(HttpSession session) throws Exception {
		String idFail = "0";
		try{
			if(getParam("idFail") == ""){
				//fail baru
				Hashtable<String,String> h = new Hashtable<String,String>();
				h.put("idNegeri", getParam("socNegeri"));							
				//fir ganti id kementerian ngan cara baru
				h.put("idKementerian", getParam("socAgensi"));
				h.put("idSuburusan", getParam("socSuburusan"));		
				h.put("Tajuk", getParam("socTajuk"));
				h.put("noFailSek", getParam("txtNoFailSek"));
				h.put("tarikhBukaFail", getParam("txdTarikhBukaFail"));			
				h.put("idMasuk", idUser);			
				myLog.info("GadaianProcessA::SimpanSemak:: h = " + h);
				idFail = FrmGadaianSemakan1Data.simpan(h);
				result = "baru";
			
			}else{	
				//kemaskini fail
				Hashtable<String,String> h = new Hashtable<String,String>();
				h.put("idFail", getParam("idFail"));			
				h.put("Tajuk", getParam("txtTajuk"));
				h.put("idKemaskini", idUser);
				////myLog.info("GadaianProcess::SimpanSemak:: h = "+h);
				idFail = FrmGadaianSemakan1Data.update(h);
				result = "kemaskini";
			
			}
			
		}catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		return idFail;
		
	}
	
	/** Senarai Fail Gadaian Controller */
	public Vector<Hashtable<String,String>> ListFail(HttpSession session, String key_cari, String keyNo_cari, Long idNegeri, String keyNo_upt, 
		String keyNo_ptg, String keyNo_ptd, String keyNo_pemilik, String keyNo_rujukan) throws Exception {
		try{
			senarai = FrmSenaraiFailGadaianData.setList(key_cari,keyNo_cari,idNegeri,keyNo_upt,keyNo_ptg,keyNo_ptd,keyNo_pemilik,keyNo_rujukan);
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		return senarai;
	}
	
	//** Senarai Permohonan Controller */
	public void ListPermohonan(HttpSession session, String idFail,String noFail, String carian) throws Exception{
		try{
			FrmGadaianSenaraiPermohonanData.setListPermohonan(idFail,noFail,carian);		
		}catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
	}
	
	//*** Gadaian Semakan Controller
	private void ListSemakBaru(HttpSession session) throws Exception{
		try{
			String idFail = getParam("idFail");
			FrmGadaianSemakanData.setSemakBaru(idFail);
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
	}
	
	private void DataSemakBaru(HttpSession session, String disability, String readability, String style1, String style2) 
		throws Exception{
		Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
		list.clear();
		try{
			list = FrmGadaianSemakanData.getSemakBaru();
		    this.context.put("SemakBaru", list);
		    Hashtable<String,String> h = (Hashtable<String,String>) list.get(0);	
//		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()),"disabled"));
		    this.context.put("selectNegeri",getNegeri((String)h.get("idNegeri")));
	
//		    this.context.put("selectAgensi",getAgensiDanKementerianForGadaian("socAgensi", Long.parseLong(h.get("idKementerian").toString()), "disabled"));
		    this.context.put("selectAgensi",getAgensiDanKementerianName(Long.parseLong((String)h.get("idKementerian"))));
//		    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()),"disabled"));
		    this.context.put("selectSuburusan",getSubUrusan((String)h.get("idSuburusan")));
		    this.context.put("datenow", formatter.format(now));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		    this.context.put("pagemode", "baru");
		    this.context.put("classDis", "");
		}
		catch(Exception ex){
			myLog.error("GadaianProcess::DataSemak::Exception = "+ex.getMessage());
		}
	}
	
	private void ListSemak(HttpSession session,String id) throws Exception {
		try{			
			String idPermohonan = "0";
			if (getParam("idPermohonan") != ""){
				idPermohonan = String.valueOf(getParam("idPermohonan"));
			}
			else if (id != "0"){
				idPermohonan = id;
			}
			myLog.info("GadaianProcess::ListSemak::idPermohonan = 0");
			FrmGadaianSemakanData.setSemak(idPermohonan);
			
		}catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
	}
	
	private void DataSemak(HttpSession session, String disability, String readability, String style1, String style2) throws Exception {
		Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
		list.clear();
		try{
			if(mode.equalsIgnoreCase("kemaskini")){
				
				list = FrmGadaianSemakanData.getSemak();
			    this.context.put("Semak", list);
			    Hashtable<String,String> h = (Hashtable<String,String>) list.get(0);
			    
			    myLog.info("DataSemak Hashtable : " + h);
			    
//			    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()),"disabled"));
			    this.context.put("selectNegeri",getNegeri((String)h.get("idNegeri")));
			    //this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()),"disabled"));
			    //this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("idAgensi").toString()),disability));
//			    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()),"disabled"));
			    this.context.put("selectAgensi",getAgensiDanKementerianName(Long.parseLong(h.get("idKementerian").toString())));
			    
			    this.context.put("selectSuburusan",getSubUrusan((String)h.get("idSuburusan")));
			    this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			    this.context.put("pagemode", "kemaskini");
			    this.context.put("classDis", "");
			    
			    //fir test
			    //myLog.info("no fail lain: " + getParam("txtNoFailLain"));
			    
			    this.context.put("noRujukan", getParam("txtNoFailLain"));
				
			}
			
			else{
				
				list = FrmGadaianSemakanData.getSemak();
			    this.context.put("Semak", list);
			    Hashtable<String,String> h = (Hashtable<String,String>) list.get(0);
			    
			    myLog.info("DataSemak Hashtable : " + h);
			    
//			    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()),"disabled"));
			    this.context.put("selectNegeri",getNegeri((String)h.get("idNegeri")));
			    //this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()),"disabled"));
			    //this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("idAgensi").toString()),disability));
//			    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()),"disabled"));
			    this.context.put("selectAgensi",getAgensiDanKementerianName(Long.parseLong(h.get("idKementerian").toString())));
			    
			    this.context.put("selectSuburusan",getSubUrusan((String)h.get("idSuburusan")));
			    this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			    this.context.put("pagemode", "simpan");
			    String classDis = "class=\"disabled\"";
			    this.context.put("classDis", classDis);
			    
			    //fir test
			    //myLog.info("no fail lain: " + getParam("txtNoFailLain"));
			    
			    this.context.put("noRujukan", getParam("txtNoFailLain"));
				
			}

		}
		catch(Exception ex){
			////myLog.info("GadaianProcess::DataSemak::Exception = "+ex);
			ex.printStackTrace();
		}
	}
	
	//private String SimpanSemak(HttpSession session) throws Exception{
	private String SimpanSemak(String userId ) throws Exception{
		String idPermohonan = "0";
		Hashtable<String,String> h = new Hashtable<String,String>();
		try{
			if(getParam("idPermohonan") == ""){				
				//fail baru			
				h.put("IdFail", getParam("idFail"));
				h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
				h.put("Tajuk", getParam("txtTajuk"));
				h.put("socAgensi", getParam("socAgensi"));
				h.put("idSuburusan", getParam("idSuburusan"));
				h.put("NoFailLain", getParam("txtNoFailLain"));
				h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
				h.put("idMasuk", userId);
				myLog.info("GadaianProcess:SimpanSemak::FailBaru::: h = "+h);
				idPermohonan = gadaiandata.Simpan(h);
				result = "baru";
				
			}else{				
				//kemaskini fail
				h.put("idPermohonan", getParam("idPermohonan"));				
				//agensi x perlu kemaskini
				h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
				h.put("NoFailLain", getParam("txtNoFailLain"));
				h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
				h.put("idKemaskini", userId);
				myLog.info("GadaianProcess::SimpanSemak::KemaskiniFail:: h = "+h);
				idPermohonan = FrmGadaianSemakanData.update(h);
				result = "kemaskini";
				
			}
			//myLog.info("GadaianProcess::SimpanSemak::Fir IdPermohonan :: " + idPermohonan );
			
		}catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		return idPermohonan;
				
	}	
	private void DataPenHamilik(HttpSession session, String disability, String readability, String style1, String style2) 
		throws Exception {		
		Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
		Hashtable<String,String> h = null;
		list.clear();
		try{
			this.context.remove("PenHamilik");
			gadaianData = new FrmGadaianPenHamilikData();
			String idPermohonan = getParam("idPermohonan");
			list = gadaianData.getPendaftaranHakmilik(idPermohonan);

			this.context.remove("selectJenisHakmilik");
			if(list.size() != 0){
			//if(h != null){
				String luasLama = "";
				String luasBersamaan = "";
				String noLot = "";
				String NoJofa = "";
				h = (Hashtable<String,String>) list.get(0);
				if(this.mode.equalsIgnoreCase("kemaskini")){				
				    myLog.info("DataPenHamilik : kemaskini :: " + h);
					this.context.put("PenHamilik", list);				    
				    //fir 07022010
				    this.context.put("selectKementerian",getKementerian(Long.parseLong((String)h.get("IdKementerian"))));
				    this.context.put("selectAgensi",getAgensi(Long.parseLong((String)h.get("IdAgensi"))));
				    //this.context.put("selectNegeri",getNegeri((String)h.get("IdNegeri")));
				    this.context.put("selectNegeri",HTML.SelectNegeri("selectNegeri", Long.parseLong(h.get("IdNegeri").toString()) ,"", " onchange=\"doChangeDaerahKemaskini()\" class=\"mediumselect\" "));			    
				    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong(h.get("IdDaerah").toString()), null, " onchange=\"doChangeDaerahKemaskini()\" class=\"mediumselect\" "));
				    //this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong(h.get("IdDaerah").toString()), "style='width:200px;'"));
				    this.context.put("selectMukim",HTML.SelectMukimByDaerah(h.get("IdDaerah").toString(), "socMukim", Long.parseLong(h.get("IdMukim").toString()), "style='width:200px;'"));
				    
				    //this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong(h.get("IdJenishakmilik").toString()),"style='width:200px;'"));
				    if(Integer.parseInt(String.valueOf(h.get("IdNegeri")))==13 || Integer.parseInt(String.valueOf(h.get("IdNegeri")))==12){
						this.context.put("selectJenisHakmilik",utilHTML.PilihJenisHakmilikMengikutNegeri("socJenisHakmilik",String.valueOf(h.get("IdNegeri")),idJenisHakmilik," style='width:200px;'"));
				    
				    }else{
				    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",idJenisHakmilik," style='width:200px;'"));   
				    }			    
				    this.context.put("selectLot",HTML.SelectLot("socLot",Long.parseLong(h.get("IdLot").toString()),"style='width:200px;' "));
				    //this.context.put("selectLuas",HTML.SelectLuas("socLuas",Long.parseLong(h.get("IdLuas").toString()),"style='width:200px;' onchange\"javascript:doChangeKodLuasUpdate(this.value)\" "));
					String socLuas = getParam("socLuas");
		    		context.put("socLuas", socLuas);
		    		luasLama = getParam("Luas");
		    		luasBersamaan = getParam("luasBersamaan");
				    noLot = getParam("txtNoLot");	
				    NoJofa = (String)h.get("NoJofa");

				    this.context.put("selectRizab",HTML.SelectRizab("socRizab",Long.parseLong(h.get("IdRizab").toString()),"style='width:200px;'"));
				    this.context.put("selectKategori",HTML.SelectKategori("socKategori",Long.parseLong(h.get("IdKategori").toString()),"style='width:200px;'"));
				    
				    //fir test 04042010
//				    String tajukFail = h.get("TajukFail").toString();
//				    myLog.info("test1" + tajukFail);
//				    
//				    String SOC = "";
//				    
//				    if(tajukFail.contains("SATISFACTION OF CHARGE")){
//				    	SOC = "fileSOC";
//				    }
//				    
//				    this.context.put("SOC", SOC);
				    this.context.put("modeSoc", disability);
				    this.context.put("mode", readability);
				    this.context.put("Style1", style1);
				    this.context.put("Style2", style2);
				    this.context.put("pagemode", "kemaskini");
				    this.context.put("classDis", "");
				    
				    this.context.put("disabled", disability);
				    this.context.put("readonly", "");
					
				}else{					
					this.context.put("PenHamilik", list);
				    h = (Hashtable<String,String>) list.get(0);			    
				    myLog.info("DataPenHamilik : else kemaskini ::" + h);

				    this.context.put("selectKementerian",getKementerian(Long.parseLong((String)h.get("IdKementerian"))));
				    this.context.put("selectAgensi",getAgensi(Long.parseLong((String)h.get("IdAgensi"))));
				    //this.context.put("selectNegeri",HTML.SelectNegeri("selectNegeri", Long.parseLong(h.get("IdNegeri").toString()) ,"", " onchange=\"doChangeDaerahKemaskini()\" class=\"mediumselect\" "));
				    this.context.put("selectNegeri",HTML.SelectNegeri("selectNegeri", Long.parseLong(h.get("IdNegeri").toString()) ,"", " onchange=\"doChangeDaerahKemaskini()\" disabled=\"disabled\" class=\"disabled\" class=\"mediumselect\" "));
			    
				    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong(h.get("IdDaerah").toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
				    
				    this.context.put("selectMukim",HTML.SelectMukimByDaerah(h.get("IdDaerah").toString(), "socMukim", Long.parseLong(h.get("IdMukim").toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));				    
				    //this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong(h.get("IdJenishakmilik").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
				    if(Integer.parseInt(String.valueOf(h.get("IdNegeri")))==13 || Integer.parseInt(String.valueOf(h.get("IdNegeri")))==12){
						this.context.put("selectJenisHakmilik",utilHTML.PilihJenisHakmilikMengikutNegeri("socJenisHakmilik",String.valueOf(h.get("IdNegeri")),Long.parseLong(String.valueOf(h.get("IdJenishakmilik")))," disabled=\"disabled\" class=\"disabled\"  style='width:200px;'"));
				    
				    }else{
				    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", idJenisHakmilik, " disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));   
				    }
				    //this.context.put("selectLot",HTML.SelectLot("socLot",Long.parseLong(h.get("IdLot").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
				    this.context.put("selectLot",HTML.SelectLot("socLot",Long.parseLong(h.get("IdLot").toString())," disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
				    //this.context.put("selectLuas",HTML.SelectLuas("socLuas",Long.parseLong(h.get("IdLuas").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;' "));
				    this.context.put("selectRizab",HTML.SelectRizab("socRizab",Long.parseLong(h.get("IdRizab").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
				    this.context.put("selectKategori",HTML.SelectKategori("socKategori",Long.parseLong(h.get("IdKategori").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
				    luasLama = (String)h.get("luas");				    
				    luasBersamaan = (String)h.get("luasBersamaan");				    
				    noLot = (String)h.get("NoLot");		
				    NoJofa = (String)h.get("NoJofa");	
				    
				    this.context.put("modeSoc", disability);
				    this.context.put("mode", readability);
				    this.context.put("Style1", style1);
				    this.context.put("Style2", style2);
				    this.context.put("pagemode", "simpan");
				    String classDis = "class=\"disabled\" ";
				    this.context.put("classDis", classDis);					
				    this.context.put("disabled", disability);
				    this.context.put("readonly", classDis);
				    
				}
				this.context.put("txtLuas", luasBersamaan);
				this.context.put("txtLuas1", luasLama);
				this.context.put("NoLot", noLot);
				this.context.put("NoJofa", NoJofa);

			}else{
				//myLog.info("GadaianProcess::DataPenHamilik:: list.size() = "+list.size());
				//list.clear();
				//int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
				//FrmGadaianPenHamilikData.setPenHakmilikBaru(idPermohonan);
				list = gadaianData.getPendaftaranHakmilikBaru(idPermohonan);
				this.context.put("PenHamilik", list);
				myLog.info("DataPenHamilik:else:: list = "+list);
			    //Hashtable 
				h = (Hashtable<String,String>) list.get(0);
			    
			    //test fir
			    String tajuk = h.get("TajukFail").toString();
			    myLog.info("test12 : " + tajuk);
			    String SOC = "";
			    if(tajuk.contains("SATISFACTION OF CHARGE")){
			    	SOC = "SOC";
			    }
			    
			    this.context.put("SOC", SOC);
			    this.context.put("TarikhLepasGadai", "");			    
			    //myLog.info("fir DataPenHamilik list 0 : " + h);
			    this.context.put("selectKementerian",getKementerian(Long.parseLong(h.get("IdKementerian").toString())));
			    this.context.put("selectAgensi",getAgensi(Long.parseLong(h.get("IdAgensi").toString())));
			    //this.context.put("selectNegeri",getNegeri(h.get("IdNegeri").toString()));
			    this.context.put("selectNegeri",HTML.SelectNegeri("selectNegeri", " onchange=\"doChangeDaerahKemaskini()\" class=\"mediumselect\" "));
			    
			    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong("0"), null, " onchange=\"doChangeDaerah()\" class=\"mediumselect\" "));
			    this.context.put("selectMukim",HTML.SelectMukimByDaerah("", "socMukim", " class=\"mediumselect\" "));
			    
//			    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik"));
			    if(Integer.parseInt(String.valueOf(h.get("IdNegeri")))==13 || Integer.parseInt(String.valueOf(h.get("IdNegeri")))==12){
					this.context.put("selectJenisHakmilik",utilHTML.PilihJenisHakmilikMengikutNegeri("socJenisHakmilik",String.valueOf(h.get("IdNegeri")),0L," style='width:200px;'"));
			    
			    }else{
			    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong("0"), "style='width:200px;'"));   
			    }
//			    this.context.put("selectLot",HTML.SelectLot("socLot"));
			    this.context.put("selectLot",HTML.SelectLot("socLot", Long.parseLong("0"), "style='width:200px;'"));
//			    this.context.put("selectLuas",HTML.SelectLuas("socLuas"));
//			    this.context.put("selectLuas",HTML.SelectLuas("socLuas", Long.parseLong("0"), "style='width:200px;'"));
//			    this.context.put("selectRizab",HTML.SelectRizab("socRizab"));
			    this.context.put("selectRizab",HTML.SelectRizab("socLuas", Long.parseLong("0"), "style='width:200px;'"));
//			    this.context.put("selectKategori",HTML.SelectKategori("socKategori"));
			    this.context.put("selectKategori",HTML.SelectKategori("socKategori", Long.parseLong("0"), "style='width:200px;'"));
			    this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			    this.context.put("pagemode", "baru");
			    this.context.put("classDis", "");
			}
		
		}catch(Exception ex){
//			myLog.error("GadaianProcess::DataPenHamilik::Exception = "+ex);
			ex.printStackTrace();
		}
		
	}
	private void DataPenHamilik(String disability, String readability, String style1, String style2) 
		throws Exception {		
			//Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
			Hashtable<String,String> h = null;
			//list.clear();
			try{
				this.context.remove("PenHamilik");
				gadaianData = new FrmGadaianPenHamilikData();
				String idPermohonan = getParam("idPermohonan");
				//list = gadaianData.getPendaftaranHakmilik(idPermohonan);

				this.context.remove("selectJenisHakmilik");
				//if(list.size() != 0){
				//if(h != null){
					String luasLama = "";
					String luasBersamaan = "";
					String noLot = "";
					String NoJofa = "";
					//h = (Hashtable<String,String>) list.get(0);
					h = gadaianData.getHakmilik(idPermohonan);;
					this.context.put("PenHamilik", h);				    
					if(this.mode.equalsIgnoreCase("kemaskini")){				
					    myLog.info("DataPenHamilik : kemaskini :: " + h);
						//this.context.put("PenHamilik", list);				    
					    this.context.put("selectKementerian",getKementerian(Long.parseLong((String)h.get("IdKementerian"))));
					    this.context.put("selectAgensi",getAgensi(Long.parseLong((String)h.get("IdAgensi"))));
					    this.context.put("selectNegeri",HTML.SelectNegeri("selectNegeri", Long.parseLong(h.get("IdNegeri").toString()) ,"", " onchange=\"doChangeDaerahKemaskini()\" class=\"mediumselect\" "));			    
					    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong(h.get("IdDaerah").toString()), null, " onchange=\"doChangeDaerahKemaskini()\" class=\"mediumselect\" "));
					    this.context.put("selectMukim",HTML.SelectMukimByDaerah(h.get("IdDaerah").toString(), "socMukim", Long.parseLong(h.get("IdMukim").toString()), "style='width:200px;'"));
					    
					    //this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong(h.get("IdJenishakmilik").toString()),"style='width:200px;'"));
					    if(Integer.parseInt(String.valueOf(h.get("IdNegeri")))==13 || Integer.parseInt(String.valueOf(h.get("IdNegeri")))==12){
							this.context.put("selectJenisHakmilik",utilHTML.PilihJenisHakmilikMengikutNegeri("socJenisHakmilik",String.valueOf(h.get("IdNegeri")),idJenisHakmilik," style='width:200px;'"));
					    
					    }else{
					    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",idJenisHakmilik," style='width:200px;'"));   
					    }			    
					    this.context.put("selectLot",HTML.SelectLot("socLot",Long.parseLong(h.get("IdLot").toString()),"style='width:200px;' "));
					    //this.context.put("selectLuas",HTML.SelectLuas("socLuas",Long.parseLong(h.get("IdLuas").toString()),"style='width:200px;' onchange\"javascript:doChangeKodLuasUpdate(this.value)\" "));
						String socLuas = getParam("socLuas");
			    		context.put("socLuas", socLuas);
			    		luasLama = getParam("Luas");
			    		luasBersamaan = getParam("luasBersamaan");
					    noLot = getParam("txtNoLot");	
					    NoJofa = (String)h.get("NoJofa");

					    this.context.put("selectRizab",HTML.SelectRizab("socRizab",Long.parseLong(h.get("IdRizab").toString()),"style='width:200px;'"));
					    this.context.put("selectKategori",HTML.SelectKategori("socKategori",Long.parseLong(h.get("IdKategori").toString()),"style='width:200px;'"));
					    
					    //fir test 04042010
//					    String tajukFail = h.get("TajukFail").toString();
//					    myLog.info("test1" + tajukFail);
//					    
//					    String SOC = "";
//					    
//					    if(tajukFail.contains("SATISFACTION OF CHARGE")){
//					    	SOC = "fileSOC";
//					    }
//					    
//					    this.context.put("SOC", SOC);
					    this.context.put("modeSoc", disability);
					    this.context.put("mode", readability);
					    this.context.put("Style1", style1);
					    this.context.put("Style2", style2);
					    this.context.put("pagemode", "kemaskini");
					    this.context.put("classDis", "");
					    
					    this.context.put("disabled", disability);
					    this.context.put("readonly", "");
						
					}else{					
					//	this.context.put("PenHamilik", list);
					//    h = (Hashtable<String,String>) list.get(0);			    
					    myLog.info("DataPenHamilik : else kemaskini ::" + h);

					    this.context.put("selectKementerian",getKementerian(Long.parseLong((String)h.get("IdKementerian"))));
					    this.context.put("selectAgensi",getAgensi(Long.parseLong((String)h.get("IdAgensi"))));
					    this.context.put("selectNegeri",HTML.SelectNegeri("selectNegeri", Long.parseLong(h.get("IdNegeri").toString()) ,"", " onchange=\"doChangeDaerahKemaskini()\" disabled=\"disabled\" class=\"disabled\" class=\"mediumselect\" "));				    
					    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong(h.get("IdDaerah").toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));					    
					    this.context.put("selectMukim",HTML.SelectMukimByDaerah(h.get("IdDaerah").toString(), "socMukim", Long.parseLong(h.get("IdMukim").toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));				    
					    if(Integer.parseInt(String.valueOf(h.get("IdNegeri")))==13 || Integer.parseInt(String.valueOf(h.get("IdNegeri")))==12){
							this.context.put("selectJenisHakmilik",utilHTML.PilihJenisHakmilikMengikutNegeri("socJenisHakmilik",String.valueOf(h.get("IdNegeri")),Long.parseLong(String.valueOf(h.get("IdJenishakmilik")))," disabled=\"disabled\" class=\"disabled\"  style='width:200px;'"));					    
					    }else{
					    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", idJenisHakmilik, " disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));   
					    }
					    this.context.put("selectLot",HTML.SelectLot("socLot",Long.parseLong(h.get("IdLot").toString())," disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
					    this.context.put("selectRizab",HTML.SelectRizab("socRizab",Long.parseLong(h.get("IdRizab").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
					    this.context.put("selectKategori",HTML.SelectKategori("socKategori",Long.parseLong(h.get("IdKategori").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
					    luasLama = (String)h.get("luas");				    
					    luasBersamaan = (String)h.get("luasBersamaan");				    
					    noLot = (String)h.get("NoLot");		
					    NoJofa = (String)h.get("NoJofa");	
					    
					    this.context.put("modeSoc", disability);
					    this.context.put("mode", readability);
					    this.context.put("Style1", style1);
					    this.context.put("Style2", style2);
					    //this.context.put("pagemode", "simpan");
					    this.context.put("pagemode", "baru");
					    String classDis = "class=\"disabled\" ";
					    this.context.put("classDis", classDis);					
					    this.context.put("disabled", disability);
					    this.context.put("readonly", classDis);
					    
					}
					this.context.put("txtLuas", luasBersamaan);
					this.context.put("txtLuas1", luasLama);
					this.context.put("NoLot", noLot);
					this.context.put("NoJofa", NoJofa);
					this.context.put("noHakmilik", String.valueOf(h.get("NoHakmilik")));

//				}else{
//					//myLog.info("GadaianProcess::DataPenHamilik:: list.size() = "+list.size());
//					//list.clear();
//					//int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
//					//FrmGadaianPenHamilikData.setPenHakmilikBaru(idPermohonan);
//					list = gadaianData.getPendaftaranHakmilikBaru(idPermohonan);
//					this.context.put("PenHamilik", list);
//					myLog.info("DataPenHamilik:else:: list = "+list);
//				    //Hashtable 
//					h = (Hashtable<String,String>) list.get(0);
//				    
//				    //test fir
//				    String tajuk = h.get("TajukFail").toString();
//				    myLog.info("test12 : " + tajuk);
//				    String SOC = "";
//				    if(tajuk.contains("SATISFACTION OF CHARGE")){
//				    	SOC = "SOC";
//				    }
//				    
//				    this.context.put("SOC", SOC);
//				    this.context.put("TarikhLepasGadai", "");			    
//				    //myLog.info("fir DataPenHamilik list 0 : " + h);
//				    this.context.put("selectKementerian",getKementerian(Long.parseLong(h.get("IdKementerian").toString())));
//				    this.context.put("selectAgensi",getAgensi(Long.parseLong(h.get("IdAgensi").toString())));
//				    //this.context.put("selectNegeri",getNegeri(h.get("IdNegeri").toString()));
//				    this.context.put("selectNegeri",HTML.SelectNegeri("selectNegeri", " onchange=\"doChangeDaerahKemaskini()\" class=\"mediumselect\" "));
//				    
//				    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong("0"), null, " onchange=\"doChangeDaerah()\" class=\"mediumselect\" "));
//				    this.context.put("selectMukim",HTML.SelectMukimByDaerah("", "socMukim", " class=\"mediumselect\" "));
//				    
////				    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik"));
//				    if(Integer.parseInt(String.valueOf(h.get("IdNegeri")))==13 || Integer.parseInt(String.valueOf(h.get("IdNegeri")))==12){
//						this.context.put("selectJenisHakmilik",utilHTML.PilihJenisHakmilikMengikutNegeri("socJenisHakmilik",String.valueOf(h.get("IdNegeri")),0L," style='width:200px;'"));
//				    
//				    }else{
//				    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong("0"), "style='width:200px;'"));   
//				    }
////				    this.context.put("selectLot",HTML.SelectLot("socLot"));
//				    this.context.put("selectLot",HTML.SelectLot("socLot", Long.parseLong("0"), "style='width:200px;'"));
////				    this.context.put("selectLuas",HTML.SelectLuas("socLuas"));
////				    this.context.put("selectLuas",HTML.SelectLuas("socLuas", Long.parseLong("0"), "style='width:200px;'"));
////				    this.context.put("selectRizab",HTML.SelectRizab("socRizab"));
//				    this.context.put("selectRizab",HTML.SelectRizab("socLuas", Long.parseLong("0"), "style='width:200px;'"));
////				    this.context.put("selectKategori",HTML.SelectKategori("socKategori"));
//				    this.context.put("selectKategori",HTML.SelectKategori("socKategori", Long.parseLong("0"), "style='width:200px;'"));
//				    this.context.put("modeSoc", "");
//				    this.context.put("mode", "");
//				    style1 = "none";
//				    this.context.put("Style1", style1);
//				    this.context.put("Style2", "");
//				    this.context.put("pagemode", "baru");
//				    this.context.put("classDis", "");
//				}
			
			}catch(Exception ex){
//				myLog.error("GadaianProcess::DataPenHamilik::Exception = "+ex);
				ex.printStackTrace();
			}
			
		}
	
	private void doChangeDaerah(HttpSession session, String disability, String readability, String style1, String style2) throws Exception {
		Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
		list.clear();
		try{
			String selectNegeri  = getParam("selectNegeri");
			Long selectedValue  = Long.parseLong(getParam("socDaerah"));
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			String daerah = getParam("socDaerah");
			String negeri = null;
			
			//implement kat sini capture data from field
			
			
			FrmGadaianPenHamilikData.setPenHakmilikBaru(idPermohonan);
			list = FrmGadaianPenHamilikData.getPenHakmilikBaru();
			////myLog.info("GadaianProcess::DataPenHamilik:: list = "+list);
			this.context.put("PenHamilik", list);
		    Hashtable<String,String> h = (Hashtable<String,String>) list.get(0);
		    
		    negeri = (String)h.get("IdNegeri");
		    //String agensi = (String)h.get("IdAgensi");
		    
		    myLog.info("fir hashtable dochangeDaerah : " + h);
		    
//		    this.context.put("selectKementerian",getKementerian(Long.parseLong(h.get("IdKementerian").toString())));
//		    this.context.put("selectAgensi",getAgensi(Long.parseLong(h.get("IdAgensi").toString())));
//		    this.context.put("selectNegeri",getNegeri(h.get("IdNegeri").toString()));
//		    
//		    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", selectedValue, "", "onchange=\"doChangeDaerah()\""));
//		    this.context.put("selectMukim",HTML.SelectMukimByDaerah(getParam("socDaerah"), "socMukim"));
//		    //this.context.put("selectMukim",HTML.SelectMukimByDaerah(h.get("IdDaerah").toString(), "socMukim"));
//		    
//		    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik"));
//		    this.context.put("selectLot",HTML.SelectLot("socLot"));
//		    this.context.put("selectLuas",HTML.SelectLuas("socLuas"));
//		    this.context.put("selectRizab",HTML.SelectRizab("socRizab"));
//		    this.context.put("selectKategori",HTML.SelectKategori("socKategori"));
//		    this.context.put("modeSoc", "");
//		    this.context.put("mode", "");
//		    style1 = "none";
//		    this.context.put("Style1", style1);
//		    this.context.put("Style2", "");
		    
		    
		    this.context.put("selectKementerian",getKementerian(Long.parseLong(h.get("IdKementerian").toString())));
		    this.context.put("selectAgensi",getAgensi(Long.parseLong(h.get("IdAgensi").toString())));
		    this.context.put("selectNegeri",getNegeri(selectNegeri));
		    
		    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(negeri, "socDaerah", selectedValue, "", " onchange=\"doChangeDaerah()\" class=\"mediumselect\" "));
		    this.context.put("selectMukim",HTML.SelectMukimByDaerah(daerah, "socMukim", " class=\"mediumselect\" "));
		    
		    //this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong("0"), "style='width:190px;'"));
		    if(Integer.parseInt(String.valueOf(h.get("IdNegeri")))==13 || Integer.parseInt(String.valueOf(h.get("IdNegeri")))==12){
				this.context.put("selectJenisHakmilik",utilHTML.PilihJenisHakmilikMengikutNegeri("socJenisHakmilik",String.valueOf(h.get("IdNegeri")),0L," style='width:200px;'"));
		    
		    }else{
		    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong("0"), "style='width:200px;'"));   
		    }	
		    this.context.put("selectLot",HTML.SelectLot("socLot", Long.parseLong("0"), "style='width:200px;'"));
		    this.context.put("selectLuas",HTML.SelectLuas("socLuas", Long.parseLong("0"), "style='width:200px;'"));
		    this.context.put("selectRizab",HTML.SelectRizab("socRizab", Long.parseLong("0"), "style='width:200px;'"));
		    this.context.put("selectKategori",HTML.SelectKategori("socKategori", Long.parseLong("0"), "style='width:200px;'"));
		    this.context.put("modeSoc", "");
		    this.context.put("mode", "");
		    
		    this.context.put("pagemode", "baru");
			
		}
		catch(Exception ex){
			myLog.error("GadaianProcess::DataPenHamilik::doChangeDaerah::Exception = "+ex);
		}
	}
	
	private void doChangeDaerahKemaskini(HttpSession session, String disability, String readability, String style1, String style2) throws Exception {
		try{
			//String idPermohonan = getParam("idPermohonan");
			String txdTarikhTerima = getParam("txdTarikhTerima");
			String txdTarikhLuput = getParam("txdTarikhLuput");
			String txtCukaiPertama = getParam("txtCukaiPertama");
			String txtCukaiSemasa = getParam("txtCukaiSemasa");			
			String txtLokasi = getParam("txtLokasi");			
			String socLuas = getParam("socLuas");		
			String txtLuasGabung = getParam("txtLuasGabung");			
			String txtLuas = getParam("txtLuas");					
			String txtSyarat = getParam("txtSyarat");
			//String socJenisHakmilik = getParam("socJenisHakmilik");
			String txtNoHakmilik = getParam("txtNoHakmilik");					
			String socLot = getParam("socLot");
			String txtNoLot = getParam("txtNoLot");
			String txdTarikhWarta = getParam("txdTarikhWarta");
			String txtNoWarta = getParam("txtNoWarta");
			String socKategori = getParam("socKategori");					
			String txtNoPelan = getParam("txtNoPelan");
			String txtNoSyit = getParam("txtNoSyit");
			String txtNoPu = getParam("txtNoPu");
			String txtSekatan = getParam("txtSekatan");			
			
			//Long daerahSelectedValue  = Long.parseLong(daerah);
			Long socLuasSelectedValue  = Long.parseLong(socLuas==""?"0":socLuas);			
			//Long socJenisHakmilikSelectedValue  = Long.parseLong(socJenisHakmilik);
			Long socLotSelectedValue  = Long.parseLong(socLot);			
			Long socKategoriSelectedValue  = Long.parseLong(socKategori);			
			//int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			//FrmGadaianPenHamilikData.setPenHakmilikBaru(idPermohonan);
			//list = FrmGadaianPenHamilikData.getPenHakmilikBaru();
			//myLog.info("doChangeDaerahKemaskini::daerahSelectedValue = "+daerahSelectedValue);
			//myLog.info("doChangeDaerahKemaskini::daerahSelectedValue = "+daerahSelectedValue);			//this.context.put("PenHamilik", list);
		    //Hashtable h = (Hashtable) list.get(0);
		    
		    //negeri = (String)h.get("IdNegeri");
		    //String agensi = (String)h.get("IdAgensi");
		    		    
		    //this.context.put("selectKementerian",getKementerian(Long.parseLong(h.get("IdKementerian").toString())));
		    //this.context.put("selectAgensi",getAgensi(Long.parseLong(h.get("IdAgensi").toString())));
//		    this.context.put("selectNegeri",HTML.SelectNegeri("selectNegeri", Long.parseLong(negeri), "", " onchange=\"doChangeDaerahKemaskini()\" class=\"mediumselect\" "));
//		    this.context.put("IdNegeri",negeri);	
//		    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(negeri, "socDaerah", daerahSelectedValue, "", " onchange=\"doChangeDaerahKemaskini()\" class=\"mediumselect\" "));
//		    this.context.put("selectMukim",HTML.SelectMukimByDaerah(daerah, "socMukim", " class=\"mediumselect\" "));
		    this.context.put("TarikhMula",txdTarikhTerima);	    
		    this.context.put("TarikhLuput",txdTarikhLuput);
		    this.context.put("Cukai",txtCukaiPertama);	    
		    this.context.put("CukaiTerkini",txtCukaiSemasa);		    
		    this.context.put("Lokasi",txtLokasi);		    
		    this.context.put("selectLuas",HTML.SelectLuas("socLuas",socLuasSelectedValue, "style='width:200px;'"));    
		    this.context.put("txtLuas1",txtLuasGabung);		    
		    this.context.put("txtLuas",txtLuas);
		    this.context.put("Syarat",txtSyarat);
//		    if(Integer.parseInt(negeri)==13 || Integer.parseInt(negeri)==12){
//				this.context.put("selectJenisHakmilik",utilHTML.PilihJenisHakmilikMengikutNegeri("socJenisHakmilik",negeri,socJenisHakmilikSelectedValue," style='width:200px;'"));
//		    
//		    }else{
//		    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",socJenisHakmilikSelectedValue, "style='width:200px;'"));   
//		    
//		    }	
		    this.context.put("NoHakmilik",txtNoHakmilik);		    
		    this.context.put("selectLot",HTML.SelectLot("socLot",socLotSelectedValue, "style='width:200px;'"));
		    this.context.put("NoLot",txtNoLot);
		    this.context.put("Syarat",txtSyarat);
		    this.context.put("TarikhRizab",txdTarikhWarta);
		    this.context.put("NoRizab",txtNoWarta);
		    this.context.put("selectKategori",HTML.SelectKategori("socKategori",socKategoriSelectedValue, "style='width:200px;'"));
		    this.context.put("NoPelan",txtNoPelan);
		    this.context.put("NoSyit",txtNoSyit);
		    this.context.put("Noptk",txtNoPu);
		    this.context.put("Sekatan",txtSekatan);
		    this.context.put("modeSoc", "");
		    this.context.put("mode", "");		    
		    this.context.put("pagemode", "kemaskini");
			
		}catch(Exception ex){
			myLog.error("GadaianProcess::DataPenHamilik::doChangeDaerahKemaskini::Exception = "+ex);
		}
	}
	
	private void SimpanPenHamilik(HttpSession session) throws Exception{
		String userID = (String)session.getAttribute("_ekptg_user_id");
		try{
			String idLuasBersamaan = "2";
			String luasBersamaan = getParam("txtLuas");
			String noRizab = "1";
			if(getParam("socRizab") != null && getParam("socRizab") != ""){
				noRizab = getParam("socRizab");
			}
			
			String luas = "";
			if(getParam("txtLuasGabung") != null && getParam("txtLuasGabung") != ""){
				luas = getParam("txtLuasGabung");
			}
			
			if(getParam("idHakmilikurusan") == ""){				
				String socLuas = getParam("socLuas");
				String idFail = getParam("idFail");				
				//fail baru
				Hashtable<String,String> h = new Hashtable<String,String>();
				//fir add 19022010
				h.put("idFail", idFail);				
				h.put("idPermohonan", getParam("idPermohonan"));
				h.put("idNegeri", getParam("selectNegeri"));
				h.put("socDaerah", getParam("socDaerah"));
				h.put("socMukim", getParam("socMukim"));
				h.put("socJenisHakmilik", getParam("socJenisHakmilik"));
				h.put("NoHakmilik", getParam("txtNoHakmilik"));
				h.put("TarikhTerima", getParam("txdTarikhTerima"));
				h.put("NoLot", getParam("txtNoLot"));
				h.put("NoJofa", getParam("txtNoJofa"));
				h.put("socLot", getParam("socLot"));
				h.put("CukaiPertama", getParam("txtCukaiPertama"));
				h.put("Lokasi", getParam("txtLokasi"));
				h.put("NoJofa", getParam("txtNoJofa"));
				
				if(socLuas.equalsIgnoreCase("") && socLuas.equalsIgnoreCase(null)){
					socLuas = "0";
				}
				
				String noPelan = "";
				if(getParam("txtNoPelan") != null && getParam("txtNoPelan") != ""){
					noPelan = getParam("txtNoPelan");
				}
				
				if(getParam("socRizab") != null && getParam("socRizab") != ""){
					noRizab = getParam("socRizab");
				}
				
				String kategori = "";
				if (getParam("socKategori") != null && getParam("socKategori") != ""){
					kategori = getParam("socKategori");
				}
				
				String syarat = "";
				if (getParam("txtSyarat") != null && getParam("txtSyarat") != ""){
					syarat = getParam("txtSyarat");
				}
				
				String tarikhLuput = "";
				if(getParam("txdTarikhLuput") != null && getParam("txdTarikhLuput") != ""){
					tarikhLuput = getParam("txdTarikhLuput");
				}
				
				String cukaiSemasa = "";
				if (getParam("txtCukaiSemasa") != null && getParam("txtCukaiSemasa") != ""){
					cukaiSemasa = getParam("txtCukaiSemasa");
				}
					
				String tarikhWarta = "";
				if(getParam("txdTarikhWarta") != null && getParam("txdTarikhWarta") != ""){
					tarikhWarta = getParam("txdTarikhWarta");
				}
				
				String noWarta = "";
				if (getParam("txtNoWarta") != null && getParam("txtNoWarta") != ""){
					noWarta = getParam("txtNoWarta");
				}
				
				String noPU = "";
				if(getParam("txtNoPU") != null && getParam("txtNoPU") != ""){
					noPU = getParam("txtNoPU");
				}
				
				String noSyit = "";
				if (getParam("txtNoSyit") != null && getParam("txtNoSyit") != ""){
					noSyit = getParam("txtNoSyit");
				}
				
				String sekatan = "";
				if (getParam("txtSekatan") != null && getParam("txtSekatan") != ""){
					sekatan = getParam("txtSekatan");
				}
				
				String tarikhLepasGadai = getParam("txdTarikhLepasGadai");
				
				h.put("socLuas", socLuas);
				h.put("NoPelan", noPelan);
				h.put("socRizab", noRizab);
				h.put("socKategori", kategori);
				h.put("Syarat", syarat);
				h.put("TarikhLuput", tarikhLuput);
				h.put("CukaiSemasa", cukaiSemasa);
				h.put("Luas", luas);
				h.put("idLuasBersamaan", idLuasBersamaan);
				h.put("luasBersamaan", luasBersamaan);
				h.put("TarikhWarta", tarikhWarta);
				h.put("NoWarta", noWarta);
				h.put("NoPU", noPU);
				h.put("NoSyit", noSyit);
				h.put("Sekatan", sekatan);
				h.put("tarikhLepasGadai", tarikhLepasGadai);
				h.put("userID", userID);
				myLog.info("SimpanPenHamilik : " + h);				
				//FrmGadaianPenHamilikData.simpan(h);
				getIHakmilikUrusan().simpan(h);
				result = "baru";

			}else{
				String tarikhLepasGadai = getParam("txdTarikhLepasGadai");
				
				//kemaskini fail
				Hashtable<String,String> h = new Hashtable<String,String>();
				h.put("idPermohonan", getParam("idPermohonan"));
				h.put("idHakmilikurusan", getParam("idHakmilikurusan"));
				h.put("idNegeri", getParam("selectNegeri"));
				h.put("socDaerah", getParam("socDaerah"));
				h.put("socMukim", getParam("socMukim"));
				h.put("socJenisHakmilik", getParam("socJenisHakmilik"));
				h.put("NoHakmilik", getParam("txtNoHakmilik"));
				h.put("NoLot", getParam("txtNoLot"));
				h.put("NoJofa", getParam("txtNoJofa"));
				h.put("socLot", getParam("socLot"));
				h.put("TarikhTerima", getParam("txdTarikhTerima"));
				h.put("CukaiPertama", getParam("txtCukaiPertama"));
				h.put("Lokasi", getParam("txtLokasi"));
				h.put("NoPelan", getParam("txtNoPelan"));

				h.put("socRizab", noRizab);
				h.put("socKategori", getParam("socKategori"));
				h.put("Syarat", getParam("txtSyarat"));
				h.put("TarikhLuput", getParam("txdTarikhLuput"));
				h.put("CukaiSemasa", getParam("txtCukaiSemasa"));
				h.put("socLuas", getParam("socLuas"));
				h.put("Luas", luas);
				h.put("idLuasBersamaan", idLuasBersamaan);
				h.put("luasBersamaan", luasBersamaan);
				h.put("TarikhWarta", getParam("txdTarikhWarta"));
				h.put("NoWarta", getParam("txtNoWarta"));
				h.put("NoPU", getParam("txtNoPU"));
				h.put("NoSyit", getParam("txtNoSyit"));
				h.put("Sekatan", getParam("txtSekatan"));
				h.put("tarikhLepasGadai", tarikhLepasGadai);
				h.put("userID", userID);
				FrmGadaianPenHamilikData.update(h);
				result = "kemaskini";
			
			}
			
		}catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
	}
	
	//*** Gadaian Hakmilik Controller
	private void ListHakmilik(HttpSession session) throws Exception{
		try{
			//int idHakmilikurusan = Integer.parseInt(getParam("idHakmilikurusan"));
			FrmGadaianHakmilikData.setListHakmilik(getParam("idHakmilikurusan"));		
		}catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
	}
	
	private void DataHakmilikChange(HttpSession session, String disability, String readability, String style1, String style2) throws Exception {
		Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
		String negeri = null;
		String idHakmilikUrusan = getParam("idHakmilikurusan");
		String idNegeri = getParam("socANegeri");
		myLog.info("DataHakmilikChange()::idHakmilikUrusan="+idHakmilikUrusan);
       this.context.put("Nama", "");
		this.context.put("Alamat1", "");
		this.context.put("Alamat2", "");
		this.context.put("Alamat3", "");
		this.context.put("Poskod", "");
		this.context.put("NoTel", "");
		this.context.put("NoFax", "");
		this.context.put("NoPerserahan", "");
		this.context.put("IdPihakberkepentingan", "");
		this.context.put("IdBebanan", "");
		
		try{
			//Vector<Hashtable<String,String>> temp = new Vector<Hashtable<String,String>>();
			hakmilikData = new FrmGadaianHakmilikData();
			list = hakmilikData.getSenaraiHakmilik(idHakmilikUrusan);
			//Hashtable<String,String> hak = (Hashtable<String,String>) list.get(0);			
			this.context.put("Hakmilik", list);
			myLog.info("DataHakmilikChange()::"+list.size());
			myLog.info("DataHakmilikChange()::"+doChange);
			myLog.info("DataHakmilikChange()::"+getParam("socANegeri"));
			negeri = getParam("socANegeri");	
			this.context.put("selectANegeri",UtilHTML.SelectNegeri("socANegeri", Long.parseLong(String.valueOf(idNegeri)), "onchange=\"doChangeNegeriPeminjamKemaskini()\"",null));
			//2010/12/05
			//this.context.put("selectADaerah",HTML.SelectDaerahByNegeri(negeri, "socADaerah"));
			this.context.put("selectADaerah",HTML.SelectBandarByNegeri(negeri, "socADaerah"));

			this.context.put("modeSoc", "");
			this.context.put("mode", "");
			style1 = "none";
			this.context.put("Style1", style1);
			this.context.put("Style2", "");
			this.context.put("pagemode", "baru");
			this.context.put("classDis", "");
					    
		}catch(Exception ex){
			myLog.info("DataHakmilikChange:Exception = "+ex);
		}
	}
	
	private void DataHakmilik(HttpSession session, String disability, String readability, String style1, String style2) throws Exception {
		Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
		list.clear();
		String negeri = null;
		String idHakmilikUrusan = getParam("idHakmilikurusan");
        this.context.put("Nama", "");
		this.context.put("Alamat1", "");
		this.context.put("Alamat2", "");
		this.context.put("Alamat3", "");
		this.context.put("Poskod", "");
		this.context.put("NoTel", "");
		this.context.put("NoFax", "");
		this.context.put("NoPerserahan", "");
		this.context.put("IdPihakberkepentingan", "");
		this.context.put("IdBebanan", "");
		
		try{
			//Vector temp = new Vector();
			Hashtable<String,String> hashTemp = new Hashtable<String,String>();
			hakmilikData = new FrmGadaianHakmilikData();
			//list = FrmGadaianHakmilikData.getListHakmilik();
			list = hakmilikData.getSenaraiHakmilik(idHakmilikUrusan);
			myLog.info("DataHakmilik()::"+list);
			myLog.info("DataHakmilik()::"+doChange);
			
			if(list.size() != 0){
				
				if(this.mode.equalsIgnoreCase("kemaskiniHakmilik")){
					
					if(this.doChange.equalsIgnoreCase("doChangeNegeriPeminjam")){
						myLog.info("kemaskiniHakmilik::doChangeNegeriPeminjam"+list);			
						
						this.context.put("Hakmilik", list);
						hashTemp = (Hashtable<String,String>)list.get(0);
	
						String IdPihakberkepentingan = (String)hashTemp.get("IdPihakberkepentingan");
						String IdBebanan = (String)hashTemp.get("IdBebanan");
						negeri = getParam("socANegeri");	
						String nama = getParam("txtNama");
						String alamat1 = getParam("txtAlamat1");
						String alamat2 = getParam("txtAlamat2");
						String alamat3 = getParam("txtAlamat3");
						String poskod = getParam("txtPoskod");
						String tel = getParam("txtNoTelefon");
						String fax = getParam("txtNoFax");
						String perserahan = getParam("txtNoPerserahan");
						String suratGadaian = getParam("txdsuratgadaian");
						String jilid = getParam("txtjilid");
						String folio = getParam("txtfolio");
						
						hashTemp.clear();				
						hashTemp.put("Nama", nama);
						hashTemp.put("Alamat1", alamat1);
						hashTemp.put("Alamat2", alamat2);
						hashTemp.put("Alamat3", alamat3);
						hashTemp.put("Poskod", poskod);
						hashTemp.put("NoTel", tel);
						hashTemp.put("NoFax", fax);	
						hashTemp.put("NoPerserahan", perserahan);
						hashTemp.put("IdPihakberkepentingan", IdPihakberkepentingan);
						hashTemp.put("IdBebanan", IdBebanan);
						hashTemp.put("txdSuratGadaian", suratGadaian);
						hashTemp.put("txtJilid", jilid);
						hashTemp.put("txtFolio", folio);						
						list.clear();
						list.add(hashTemp);
						
						//this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri", Long.parseLong(negeri), "", "onchange=\"doChangeNegeriPeminjamKemaskini()\""));
						//this.context.put("selectANegeri",UtilHTML.SelectNegeri("socANegeri", Long.parseLong(negeri), "", "onchange=\"doChangeNegeriPeminjamKemaskini()\""));
						this.context.put("selectANegeri",UtilHTML.SelectNegeri("socANegeri", Long.parseLong(negeri), "onchange=\"doChangeNegeriPeminjam()\"",null));
						// 2010/12/05
						//this.context.put("selectADaerah",HTML.SelectDaerahByNegeri(negeri, "socADaerah"));
						this.context.put("selectADaerah",HTML.SelectBandarByNegeri(negeri, "socADaerah"));
						this.context.put("modeSoc", "");
					    this.context.put("mode", "");
					    style1 = "none";
					    this.context.put("Style1", style1);
					    this.context.put("Style2", "");
					    this.context.put("pagemode", "baru");
					    this.context.put("classDis", "");
					    this.context.put("txdSuratGadaian", suratGadaian);
					    this.context.put("txtJilid", folio);
					    
					}else{						
						//myLog.info("else kemaskiniHakmilik::doChangeNegeriPeminjam"+list);			
						this.context.put("Hakmilik", list);
						Hashtable<String,String> hak = (Hashtable<String,String>) list.get(0);			
						this.context.put("selectANegeri",UtilHTML.SelectNegeri("socANegeri", Long.parseLong(String.valueOf(hak.get("IdNegeri"))), "onchange=\"doChangeNegeriPeminjamKemaskini()\"",null));
						// 2010/12/1010
						//this.context.put("selectADaerah",HTML.SelectDaerahByNegeri(hak.get("IdNegeri").toString(), "socADaerah",Long.parseLong(hak.get("IdDaerah").toString()),""));
						this.context.put("selectADaerah",HTML.SelectBandarByNegeri(hak.get("IdNegeri").toString(), "socADaerah",Long.parseLong(hak.get("idBandar").toString()),""));
						this.context.put("modeSoc", disability);
					    this.context.put("mode", readability);
					    this.context.put("Style1", style1);
					    this.context.put("Style2", style2);
					    this.context.put("classDis", "");
					    this.context.put("pagemode", "kemaskini");
					    this.context.put("txdSuratGadaian", hak.get("tarikhDaftar"));
					    this.context.put("txtJilid", hak.get("jilid"));
					    this.context.put("txtFolio", hak.get("folio"));						
					}
					
				}else{
					
					myLog.info("GadaianProcess::DataHakmilik::list "+list);
					this.context.put("Hakmilik", list);
					//Hashtable hak = (Hashtable) list.get(0);			
					hashTemp = (Hashtable<String,String>) list.get(0);	
					this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri",Long.parseLong(hashTemp.get("IdNegeri").toString()),"disabled=\"disabled\" class=\"disabled\" "));
					// 2010/12/05
					//this.context.put("selectADaerah",HTML.SelectDaerah("socADaerah",Long.parseLong(hak.get("IdDaerah").toString()),"disabled=\"disabled\" class=\"disabled\" "));
					this.context.put("selectADaerah",HTML.SelectBandarByNegeri(hashTemp.get("IdNegeri").toString(), "socADaerah",Long.parseLong(hashTemp.get("idBandar").toString()),"disabled=\"disabled\" class=\"disabled\" "));
					this.context.put("modeSoc", disability);
				    this.context.put("mode", readability);
				    this.context.put("Style1", style1);
				    this.context.put("Style2", style2);
				    String classDis = "class=\"disabled\" ";
				    this.context.put("classDis", classDis);
				    this.context.put("pagemode", "simpan");
				    this.context.put("txdSuratGadaian", hashTemp.get("tarikhDaftar"));
				    this.context.put("txtJilid", hashTemp.get("jilid"));
				    this.context.put("txtFolio", hashTemp.get("folio"));
				    					
				}
 
			}else{	
				myLog.info("doChange : " + doChange);
				if(this.doChange.equalsIgnoreCase("doChangeNegeriPeminjam")){
					negeri = getParam("socANegeri");
					myLog.info("doChange : " + doChange);
					myLog.info("negeri : " + negeri);
					
					String nama = getParam("txtNama");
					String alamat1 = getParam("txtAlamat1");
					String alamat2 = getParam("txtAlamat2");
					String alamat3 = getParam("txtAlamat3");
					String poskod = getParam("txtPoskod");
					String tel = getParam("txtNoTelefon");
					String fax = getParam("txtNoFax");
					String perserahan = getParam("txtNoPerserahan");
					String suratGadaian = getParam("txdsuratgadaian");
					String jilid = getParam("txtjilid");
					String folio = getParam("txtfolio");
					
					this.context.put("Nama", nama);
					this.context.put("Alamat1", alamat1);
					this.context.put("Alamat2", alamat2);
					this.context.put("Alamat3", alamat3);
					this.context.put("Poskod", poskod);
					this.context.put("NoTel", tel);
					this.context.put("NoFax", fax);
					this.context.put("NoPerserahan", perserahan);
					hashTemp.put("txdSuratGadaian", suratGadaian);
					hashTemp.put("txtJilid", jilid);
					hashTemp.put("txtFolio", folio);
					this.context.put("selectANegeri",UtilHTML.SelectNegeri("socANegeri", Long.parseLong(negeri), "onchange=\"doChangeNegeriPeminjam()\"",null));
					//his.context.put("selectADaerah",HTML.SelectDaerahByNegeri(negeri, "socADaerah"));
					this.context.put("selectADaerah",HTML.SelectBandarByNegeri(negeri, "socADaerah"));
					this.context.put("modeSoc", "");
				    this.context.put("mode", "");
				    style1 = "none";
				    this.context.put("Style1", style1);
				    this.context.put("Style2", "");
				    this.context.put("pagemode", "baru");
				    this.context.put("classDis", "");
					
				}else{
					this.context.put("selectANegeri",UtilHTML.SelectNegeri("socANegeri",Long.parseLong(idNegeri),"onchange=\"doChangeNegeriPeminjam()\" "));
					//this.context.put("selectADaerah",HTML.SelectDaerahByNegeri(idNegeri, "socADaerah"));
					this.context.put("selectADaerah",HTML.SelectBandarByNegeri(idNegeri, "socADaerah"));
					this.context.put("modeSoc", "");
				    this.context.put("mode", "");
				    style1 = "none";
				    this.context.put("Style1", style1);
				    this.context.put("Style2", "");
				    this.context.put("pagemode", "baru");
				    this.context.put("classDis", "");
					
				}
				
			}
		}catch(Exception ex){
			myLog.info("DataHakmilik:Exception = "+ex);
		}
	}
	private void DataHakmilik(String disability, String readability, String style1, String style2) throws Exception {
		Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
		list.clear();
		String negeri = null;
		String idHakmilikUrusan = getParam("idHakmilikurusan");
        this.context.put("Nama", "");
		this.context.put("Alamat1", "");
		this.context.put("Alamat2", "");
		this.context.put("Alamat3", "");
		this.context.put("Poskod", "");
		this.context.put("NoTel", "");
		this.context.put("NoFax", "");
		this.context.put("NoPerserahan", "");
		this.context.put("IdPihakberkepentingan", "");
		this.context.put("IdBebanan", "");
		
		try{
			//Vector temp = new Vector();
			Hashtable<String,String> hashTemp = new Hashtable<String,String>();
			hakmilikData = new FrmGadaianHakmilikData();
			//list = FrmGadaianHakmilikData.getListHakmilik();
			list = hakmilikData.getSenaraiHakmilik(idHakmilikUrusan);
			myLog.info("DataHakmilik()::"+list);
			myLog.info("DataHakmilik()::"+doChange);
			
			if(list.size() != 0){
				
				if(this.mode.equalsIgnoreCase("kemaskiniHakmilik")){
					
					if(this.doChange.equalsIgnoreCase("doChangeNegeriPeminjam")){
						myLog.info("kemaskiniHakmilik::doChangeNegeriPeminjam"+list);			
						
						this.context.put("Hakmilik", list);
						hashTemp = (Hashtable<String,String>)list.get(0);
	
						String IdPihakberkepentingan = (String)hashTemp.get("IdPihakberkepentingan");
						String IdBebanan = (String)hashTemp.get("IdBebanan");
						negeri = getParam("socANegeri");	
						String nama = getParam("txtNama");
						String alamat1 = getParam("txtAlamat1");
						String alamat2 = getParam("txtAlamat2");
						String alamat3 = getParam("txtAlamat3");
						String poskod = getParam("txtPoskod");
						String tel = getParam("txtNoTelefon");
						String fax = getParam("txtNoFax");
						String perserahan = getParam("txtNoPerserahan");
						String suratGadaian = getParam("txdsuratgadaian");
						String jilid = getParam("txtjilid");
						String folio = getParam("txtfolio");
						
						hashTemp.clear();				
						hashTemp.put("Nama", nama);
						hashTemp.put("Alamat1", alamat1);
						hashTemp.put("Alamat2", alamat2);
						hashTemp.put("Alamat3", alamat3);
						hashTemp.put("Poskod", poskod);
						hashTemp.put("NoTel", tel);
						hashTemp.put("NoFax", fax);	
						hashTemp.put("NoPerserahan", perserahan);
						hashTemp.put("IdPihakberkepentingan", IdPihakberkepentingan);
						hashTemp.put("IdBebanan", IdBebanan);
						hashTemp.put("txdSuratGadaian", suratGadaian);
						hashTemp.put("txtJilid", jilid);
						hashTemp.put("txtFolio", folio);						
						list.clear();
						list.add(hashTemp);
						
						//this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri", Long.parseLong(negeri), "", "onchange=\"doChangeNegeriPeminjamKemaskini()\""));
						//this.context.put("selectANegeri",UtilHTML.SelectNegeri("socANegeri", Long.parseLong(negeri), "", "onchange=\"doChangeNegeriPeminjamKemaskini()\""));
						this.context.put("selectANegeri",UtilHTML.SelectNegeri("socANegeri", Long.parseLong(negeri), "onchange=\"doChangeNegeriPeminjam()\"",null));
						// 2010/12/05
						//this.context.put("selectADaerah",HTML.SelectDaerahByNegeri(negeri, "socADaerah"));
						this.context.put("selectADaerah",HTML.SelectBandarByNegeri(negeri, "socADaerah"));
						this.context.put("modeSoc", "");
					    this.context.put("mode", "");
					    style1 = "none";
					    this.context.put("Style1", style1);
					    this.context.put("Style2", "");
					    this.context.put("pagemode", "baru");
					    this.context.put("classDis", "");
					    this.context.put("txdSuratGadaian", suratGadaian);
					    this.context.put("txtJilid", folio);
					    
					}else{						
						//myLog.info("else kemaskiniHakmilik::doChangeNegeriPeminjam"+list);			
						this.context.put("Hakmilik", list);
						Hashtable<String,String> hak = (Hashtable<String,String>) list.get(0);			
						this.context.put("selectANegeri",UtilHTML.SelectNegeri("socANegeri", Long.parseLong(String.valueOf(hak.get("IdNegeri"))), "onchange=\"doChangeNegeriPeminjamKemaskini()\"",null));
						// 2010/12/1010
						//this.context.put("selectADaerah",HTML.SelectDaerahByNegeri(hak.get("IdNegeri").toString(), "socADaerah",Long.parseLong(hak.get("IdDaerah").toString()),""));
						this.context.put("selectADaerah",HTML.SelectBandarByNegeri(hak.get("IdNegeri").toString(), "socADaerah",Long.parseLong(hak.get("idBandar").toString()),""));
						this.context.put("modeSoc", disability);
					    this.context.put("mode", readability);
					    this.context.put("Style1", style1);
					    this.context.put("Style2", style2);
					    this.context.put("classDis", "");
					    this.context.put("pagemode", "kemaskini");
					    this.context.put("txdSuratGadaian", hak.get("tarikhDaftar"));
					    this.context.put("txtJilid", hak.get("jilid"));
					    this.context.put("txtFolio", hak.get("folio"));						
					}
					
				}else{
					
					myLog.info("GadaianProcess::DataHakmilik::list "+list);
					this.context.put("Hakmilik", list);
					//Hashtable hak = (Hashtable) list.get(0);			
					hashTemp = (Hashtable<String,String>) list.get(0);	
					this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri",Long.parseLong(hashTemp.get("IdNegeri").toString()),"disabled=\"disabled\" class=\"disabled\" "));
					// 2010/12/05
					//this.context.put("selectADaerah",HTML.SelectDaerah("socADaerah",Long.parseLong(hak.get("IdDaerah").toString()),"disabled=\"disabled\" class=\"disabled\" "));
					this.context.put("selectADaerah",HTML.SelectBandarByNegeri(hashTemp.get("IdNegeri").toString(), "socADaerah",Long.parseLong(hashTemp.get("idBandar").toString()),"disabled=\"disabled\" class=\"disabled\" "));
					this.context.put("modeSoc", disability);
				    this.context.put("mode", readability);
				    this.context.put("Style1", style1);
				    this.context.put("Style2", style2);
				    String classDis = "class=\"disabled\" ";
				    this.context.put("classDis", classDis);
				    this.context.put("pagemode", "simpan");
				    this.context.put("txdSuratGadaian", hashTemp.get("tarikhDaftar"));
				    this.context.put("txtJilid", hashTemp.get("jilid"));
				    this.context.put("txtFolio", hashTemp.get("folio"));
				    					
				}
 
			}else{	
				myLog.info("doChange : " + doChange);
				if(this.doChange.equalsIgnoreCase("doChangeNegeriPeminjam")){
					negeri = getParam("socANegeri");
					myLog.info("doChange : " + doChange);
					myLog.info("negeri : " + negeri);
					
					String nama = getParam("txtNama");
					String alamat1 = getParam("txtAlamat1");
					String alamat2 = getParam("txtAlamat2");
					String alamat3 = getParam("txtAlamat3");
					String poskod = getParam("txtPoskod");
					String tel = getParam("txtNoTelefon");
					String fax = getParam("txtNoFax");
					String perserahan = getParam("txtNoPerserahan");
					String suratGadaian = getParam("txdsuratgadaian");
					String jilid = getParam("txtjilid");
					String folio = getParam("txtfolio");
					
					this.context.put("Nama", nama);
					this.context.put("Alamat1", alamat1);
					this.context.put("Alamat2", alamat2);
					this.context.put("Alamat3", alamat3);
					this.context.put("Poskod", poskod);
					this.context.put("NoTel", tel);
					this.context.put("NoFax", fax);
					this.context.put("NoPerserahan", perserahan);
					hashTemp.put("txdSuratGadaian", suratGadaian);
					hashTemp.put("txtJilid", jilid);
					hashTemp.put("txtFolio", folio);
					this.context.put("selectANegeri",UtilHTML.SelectNegeri("socANegeri", Long.parseLong(negeri), "onchange=\"doChangeNegeriPeminjam()\"",null));
					//his.context.put("selectADaerah",HTML.SelectDaerahByNegeri(negeri, "socADaerah"));
					this.context.put("selectADaerah",HTML.SelectBandarByNegeri(negeri, "socADaerah"));
					this.context.put("modeSoc", "");
				    this.context.put("mode", "");
				    style1 = "none";
				    this.context.put("Style1", style1);
				    this.context.put("Style2", "");
				    this.context.put("pagemode", "baru");
				    this.context.put("classDis", "");
					
				}else{
					this.context.put("selectANegeri",UtilHTML.SelectNegeri("socANegeri",Long.parseLong(idNegeri),"onchange=\"doChangeNegeriPeminjam()\" "));
					//this.context.put("selectADaerah",HTML.SelectDaerahByNegeri(idNegeri, "socADaerah"));
					this.context.put("selectADaerah",HTML.SelectBandarByNegeri(idNegeri, "socADaerah"));
					this.context.put("modeSoc", "");
				    this.context.put("mode", "");
				    style1 = "none";
				    this.context.put("Style1", style1);
				    this.context.put("Style2", "");
				    this.context.put("pagemode", "baru");
				    this.context.put("classDis", "");
					
				}
				
			}
		}catch(Exception ex){
			myLog.info("DataHakmilik:Exception = "+ex);
		}
	}

	
	private void SimpanHakmilik(HttpSession session) throws Exception {
		Hashtable<String,String> h = new Hashtable<String,String>();
		try{
			if(getParam("idPihakberkepentingan") == "" || getParam("idBebanan") == ""){
				//fail baru
				h = new Hashtable<String,String>();
				h.put("idHakmilikurusan", getParam("idHakmilikurusan"));
				h.put("nama", getParam("txtNama"));
				h.put("alamat1", getParam("txtAlamat1"));
				h.put("alamat2", getParam("txtAlamat2"));
				h.put("alamat3", getParam("txtAlamat3"));
				h.put("poskod", getParam("txtPoskod"));
				h.put("idDaerah", getParam("socADaerah"));
				h.put("idBandar", getParam("socADaerah"));
				h.put("idNegeri", getParam("socANegeri"));
				h.put("noTelefon", getParam("txtNoTelefon"));
				h.put("noFax", getParam("txtNoFax"));
				h.put("noPerserahan", getParam("txtNoPerserahan"));
				h.put("suratGadaian", getParam("txdsuratgadaian"));
				h.put("jilid", getParam("txtjilid"));
			    h.put("folio", getParam("txtfolio"));
				//FrmGadaianHakmilikData.simpan(h);
				FrmGadaianHakmilikData.simpanPelepasan(h);
				result = "baru";
			
			}else {
				//kemaskini fail
				h = new Hashtable<String,String>();
				h.put("idPihakberkepentingan", getParam("idPihakberkepentingan"));
				h.put("nama", getParam("txtNama"));
				h.put("alamat1", getParam("txtAlamat1"));
				h.put("alamat2", getParam("txtAlamat2"));
				h.put("alamat3", getParam("txtAlamat3"));
				h.put("poskod", getParam("txtPoskod"));
				h.put("idDaerah", getParam("socADaerah"));
				h.put("idBandar", getParam("socADaerah"));
				h.put("idNegeri", (getParam("socANegeri")));
				h.put("noTelefon", getParam("txtNoTelefon"));
				h.put("noFax", getParam("txtNoFax"));
				h.put("idBebanan", getParam("idBebanan"));
				h.put("noPerserahan", getParam("txtNoPerserahan"));
				h.put("suratGadaian", getParam("txdsuratgadaian"));
				h.put("jilid", getParam("txtjilid"));
			    h.put("folio", getParam("txtfolio"));
				
				//FrmGadaianHakmilikData.update(h);
				FrmGadaianHakmilikData.updatePelepasan(h);				
				result = "kemaskini";
				
			}
			
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
				
	}
	
	//*** Gadaian Peguam Controller
	private void ListPeguam(HttpSession session) throws Exception{
		try{
			//int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			FrmGadaianPeguamData.setListPeguam(getParam("idPermohonan"));
		
		}catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
	}
	
	private void DataPeguam(HttpSession session, String disability, String readability, String style1, String style2) throws Exception {
		Vector list = new Vector();
		list.clear();
		String negeri = null;
 
        this.context.put("IdPeguam", "");
        this.context.put("pNama", "");
		this.context.put("pAlamat1", "");
		this.context.put("pAlamat2", "");
		this.context.put("pAlamat3", "");
		this.context.put("pPoskod", "");
		this.context.put("pNoTel", "");
		this.context.put("pNoFax", "");		
		
		try{
			Vector pTemp = new Vector();
			Hashtable hashPTemp = new Hashtable();
			negeri = getParam("socBNegeri");
			list = FrmGadaianPeguamData.getListPeguam();
			myLog.info("GadaianProcess::DataPeguam:: "+list);
			if(list.size() != 0){
				
				if(this.mode.equalsIgnoreCase("kemaskiniPeguam")){
					if(this.doChange.equalsIgnoreCase("doChangeNegeriPeguam")){
						
						hashPTemp = (Hashtable)list.get(0);
							
						String IdPeguam = (String)hashPTemp.get("IdPeguam");
						negeri = getParam("socBNegeri");	
						String pNama = getParam("txtNamaPeguam");
						String pAlamat1 = getParam("txtAlamat1Peguam");
						String pAlamat2 = getParam("txtAlamat2Peguam");
						String pAlamat3 = getParam("txtAlamat3Peguam");
						String pPoskod = getParam("txtPoskodPeguam");
						String pNoTel = getParam("txtNoTelefonPeguam");
						String pNoFax = getParam("txtNoFaxPeguam");
						
						hashPTemp.clear();
						
						hashPTemp.put("IdPeguam", IdPeguam);
						hashPTemp.put("Nama", pNama);
						hashPTemp.put("Alamat1", pAlamat1);
						hashPTemp.put("Alamat2", pAlamat2);
						hashPTemp.put("Alamat3", pAlamat3);
						hashPTemp.put("Poskod", pPoskod);
						hashPTemp.put("NoTel", pNoTel);
						hashPTemp.put("NoFax", pNoFax);
						
						list.clear();
						
						list.add(hashPTemp);

						this.context.put("Peguam", list);
						this.context.put("selectBNegeri",UtilHTML.SelectNegeri("socBNegeri",Long.parseLong(negeri),"onchange=\"doChangeNegeriPeguamKemaskini()\""));
						//this.context.put("selectBDaerah",HTML.SelectDaerahByNegeri(negeri, "socBDaerah"));
						this.context.put("selectBDaerah",HTML.SelectBandarByNegeri(negeri, "socBDaerah"));

						this.context.put("modeSoc", disability);
					    this.context.put("mode", readability);
					    this.context.put("Style1", style1);
					    this.context.put("Style2", style2);
					    this.context.put("classDis", "");
					    this.context.put("pagemode", "kemaskini");
						
					}else{
						this.context.put("Peguam", list);
						Hashtable peg = (Hashtable) list.get(0);
						////myLog.info("GadaianProcess::DataPeguam::list "+list);	
						this.context.put("selectBNegeri",UtilHTML.SelectNegeri("socBNegeri",Long.parseLong(peg.get("IdNegeri").toString()),"onchange=\"doChangeNegeriPeguamKemaskini()\""));
						//this.context.put("selectBDaerah",HTML.SelectDaerahByNegeri(peg.get("IdNegeri").toString(),"socBDaerah",Long.parseLong(peg.get("IdDaerah").toString()),""));
						this.context.put("selectBDaerah",HTML.SelectBandarByNegeri(peg.get("IdNegeri").toString(), "socBDaerah",Long.parseLong(peg.get("idBandar").toString()),""));
						this.context.put("modeSoc", disability);
					    this.context.put("mode", readability);
					    this.context.put("Style1", style1);
					    this.context.put("Style2", style2);
					    this.context.put("classDis", "");
					    this.context.put("pagemode", "kemaskini");
						
					}
					
				}else{
					this.context.put("Peguam", list);
					Hashtable peg = (Hashtable) list.get(0);
					////myLog.info("GadaianProcess::DataPeguam::list "+list);	
					this.context.put("selectBNegeri",UtilHTML.SelectNegeri("socBNegeri",Long.parseLong(peg.get("IdNegeri").toString()),"disabled=\"disabled\" class=\"disabled\" "));
					//this.context.put("selectBDaerah",HTML.SelectDaerah("socBDaerah",Long.parseLong(peg.get("IdDaerah").toString()),"disabled=\"disabled\" class=\"disabled\" "));
					this.context.put("selectBDaerah",HTML.SelectBandarByNegeri(peg.get("IdNegeri").toString(), "socBDaerah",Long.parseLong(peg.get("idBandar").toString())," disabled=\"disabled\" class=\"disabled\""));
					this.context.put("modeSoc", disability);
				    this.context.put("mode", readability);
				    this.context.put("Style1", style1);
				    this.context.put("Style2", style2);
				    String classDis = "class=\"disabled\"";
				    this.context.put("classDis", classDis);
				    this.context.put("pagemode", "simpan");
					
				}
					
			}else{
				if(this.doChange.equalsIgnoreCase("doChangeNegeriPeguam")){
					
					String nama = getParam("txtNamaPeguam");
					String alamat1 = getParam("txtAlamat1Peguam");
					String alamat2 = getParam("txtAlamat2Peguam");
					String alamat3 = getParam("txtAlamat3Peguam");
					String poskod = getParam("txtPoskodPeguam");
					String tel = getParam("txtNoTelefonPeguam");
					String fax = getParam("txtNoFaxPeguam");
					
					this.context.put("pNama", nama);
					this.context.put("pAlamat1", alamat1);
					this.context.put("pAlamat2", alamat2);
					this.context.put("pAlamat3", alamat3);
					this.context.put("pPoskod", poskod);
					this.context.put("pNoTel", tel);
					this.context.put("pNoFax", fax);
					
					
					this.context.put("selectBNegeri",UtilHTML.SelectNegeri("socBNegeri", Long.parseLong(negeri), "onchange=\"doChangeNegeriPeguam()\"",null));
					//this.context.put("selectBDaerah",HTML.SelectDaerahByNegeri(negeri, "socBDaerah"));
					this.context.put("selectBDaerah",HTML.SelectBandarByNegeri(negeri, "socBDaerah"));
					this.context.put("modeSoc", "");
				    this.context.put("mode", "");
				    style1 = "none";
				    this.context.put("Style1", style1);
				    this.context.put("Style2", "");
				    this.context.put("classDis", "");
				    this.context.put("pagemode", "baru");
					
				}else{
					
					this.context.put("selectBNegeri",UtilHTML.SelectNegeri("socBNegeri",Long.parseLong(idNegeri),"onchange=\"doChangeNegeriPeguam()\"",null));
					//this.context.put("selectBDaerah",HTML.SelectDaerahByNegeri(idNegeri, "socBDaerah"));
					this.context.put("selectBDaerah",HTML.SelectBandarByNegeri(idNegeri, "socBDaerah"));
	
					this.context.put("modeSoc", "");
				    this.context.put("mode", "");
				    style1 = "none";
				    this.context.put("Style1", style1);
				    this.context.put("Style2", "");
				    this.context.put("pagemode", "baru");
				    this.context.put("classDis", "");
					
				}
				
			}
		
		}catch(Exception ex){
			myLog.info("GadaianProcess::DataPeguam::Exception = "+ex);
		}
		
		
	}
	
	private void SimpanPeguam(HttpSession session) throws Exception{
		try{
			
			if(getParam("idPeguam") == ""){
				//fail baru
				Hashtable h = new Hashtable();
				h.put("idPermohonan", getParam("idPermohonan"));
				h.put("nama", getParam("txtNamaPeguam"));
				h.put("alamat1", getParam("txtAlamat1Peguam"));
				h.put("alamat2", getParam("txtAlamat2Peguam"));
				h.put("alamat3", getParam("txtAlamat3Peguam"));
				h.put("poskod", getParam("txtPoskodPeguam"));
				h.put("idDaerah", Integer.parseInt(getParam("socBDaerah")));
				h.put("idBandar", Integer.parseInt(getParam("socBDaerah")));
				h.put("idNegeri", Integer.parseInt(getParam("socBNegeri")));
				h.put("noTelefon", getParam("txtNoTelefonPeguam"));
				h.put("noFax", getParam("txtNoFaxPeguam"));
				FrmGadaianPeguamData.simpan(h);
				result = "baru";
			}
			else{
				//kemaskini fail
				Hashtable h = new Hashtable();
				h.put("idPeguam", getParam("idPeguam"));
				h.put("nama", getParam("txtNamaPeguam"));
				h.put("alamat1", getParam("txtAlamat1Peguam"));
				h.put("alamat2", getParam("txtAlamat2Peguam"));
				h.put("alamat3", getParam("txtAlamat3Peguam"));
				h.put("poskod", getParam("txtPoskodPeguam"));
				h.put("idDaerah", Integer.parseInt(getParam("socBDaerah")));
				h.put("idBandar", Integer.parseInt(getParam("socBDaerah")));
				h.put("idNegeri", Integer.parseInt(getParam("socBNegeri")));
				h.put("noTelefon", getParam("txtNoTelefonPeguam"));
				h.put("noFax", getParam("txtNoFaxPeguam"));
				FrmGadaianPeguamData.update(h);
				result = "kemaskini";
			}
			
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
	}
	
	//fir
	public String getSubUrusanForGadaian(String selectName){
		StringBuffer sb = new StringBuffer();
		
		try{
			
			sb.append("<select name='" + selectName + "'> ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector v = DB.getSubUrusan();
			Tblrujsuburusan f = null;
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(Long.parseLong("38")) || f.getIdSuburusan().equals(Long.parseLong("39")) || f.getIdSuburusan().equals(Long.parseLong("40")) || f.getIdSuburusan().equals(Long.parseLong("41"))) {
					sb.append("<option value= " + f.getIdSuburusan() + ">"
							+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
							+ "</option>\n");
				} 

			}
			sb.append("</select>");
			
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
		return sb.toString();
	}
	
	//fir
	public String getSubUrusanForGadaian(String selectName, String jsFunction){
		StringBuffer sb = new StringBuffer();
		
		try{
			
			sb.append("<select name='" + selectName + "'");
			if(jsFunction != null){
				sb.append(jsFunction);
			}
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector v = DB.getSubUrusan();
			Tblrujsuburusan f = null;
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(Long.parseLong("38")) || f.getIdSuburusan().equals(Long.parseLong("39")) || f.getIdSuburusan().equals(Long.parseLong("40")) || f.getIdSuburusan().equals(Long.parseLong("41"))) {
					sb.append("<option value= " + f.getIdSuburusan() + ">"
							+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
							+ "</option>\n");
				} 
	
			}
			sb.append("</select>");
			
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
		return sb.toString();
	}
	
	//fir 05022010
	public String getSubUrusanForGadaian(String selectName, Long selectedValue, String disabled, String jsFunction){
		StringBuffer sb = new StringBuffer();
		
		try{
			
			sb.append("<select name='" + selectName + "'");
			if(disabled != null){
				sb.append(disabled);
			}
			if(jsFunction !=null){
				sb.append(jsFunction);
			}
			
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector v = DB.getSubUrusan();
			Tblrujsuburusan f = null;
			String selected = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(Long.parseLong("38")) || f.getIdSuburusan().equals(Long.parseLong("39")) || f.getIdSuburusan().equals(Long.parseLong("40")) || f.getIdSuburusan().equals(Long.parseLong("41"))) {
					if(f.getIdSuburusan().equals(selectedValue)){
						selected = "selected";
					}
					else{
						selected="";
					}
					sb.append("<option " + selected + " value= " + f.getIdSuburusan() + ">"
							+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
							+ "</option>\n");
				} 
	
			}
			sb.append("</select>");
			
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
		return sb.toString();
	}

//	//fir yang ni akan kuarkan semua agensi semasa mula2
//	public String getAgensiDanKementerianForGadaian(String selectName ){
//		StringBuffer sb = new StringBuffer();
//		
//		try{
//			
//			sb.append("<select name='" + selectName + "'> ");
//			sb.append("<option value=>Sila Pilih Agensi </option>\n");
//			Vector v = DB.getAgensi();
//			Vector ve = DB.getKementerian();
//			Vector tempAgensi = new Vector<Tblrujagensi>();
//			Tblrujagensi a = new Tblrujagensi();
//			Tblrujkementerian k = new Tblrujkementerian();
//			
//			//363 Bahagian Pinjaman Perumahan - kementerian kewangan
//            //455 Jabatan Perumahan Negara - kementerian perumahan dan kerajaan tempatan(kena update id_kementerian kpd 19)
//            //751 suruhanjaya koperasi malaysia - kpdnhep
//            //X guna -750 kewangan
//
//            for (int i = 0; i < v.size(); i++) {
//                  a = (Tblrujagensi) v.get(i);
//                  if (a.getIdAgensi().equals(Long.parseLong("363")) || a.getIdAgensi().equals(Long.parseLong("455")) || a.getIdAgensi().equals(Long.parseLong("751"))) {
//                        tempAgensi.add(a);
//                  }
//
//            }
//			
//			
//			Iterator<Tblrujagensi> it = tempAgensi.iterator();
//			while (it.hasNext()){
//				a = it.next();
//				for(int x = 0; x < ve.size(); x++){
//					k = (Tblrujkementerian)ve.get(x);
//					if (a.getIdKementerian().equals(k.getIdKementerian())){
//						sb.append("<option value= " + k.getIdKementerian() + ">"
//								+ a.getKodAgensi() + " - " + a.getNamaAgensi() + " ( " 
//								+ k.getNamaKementerian() + " ) </option>\n");
//					}
//				}
//			}
//			sb.append("</select>");
//			
//		}
//		catch(Exception e){
//			myLog.error("Error : " + e.getMessage());
//		}
//		
//		return sb.toString();
//	}
	
	//fir 06022010
	public String getAgensiDanKementerianForGadaian(String selectName ){
		StringBuffer sb = new StringBuffer();
		
		try{
			
			sb.append("<select name='" + selectName + "'> ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			sb.append("</select>");
			
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
		return sb.toString();
	}
	
	public String getAgensiDanKementerianForGadaian(String selectName, Long selectedValue, String disabled ){
		StringBuffer sb = new StringBuffer();
		
		try{
			
			sb.append("<select name='" + selectName + "'");
			if(disabled != null){
				sb.append(disabled);
			}
			sb.append(" >\n ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector v = DB.getAgensi();
			Vector ve = DB.getKementerian();
			Vector tempAgensi = new Vector<Tblrujagensi>();
			Tblrujagensi a = new Tblrujagensi();
			Tblrujkementerian k = new Tblrujkementerian();
					
			//363 Bahagian Pinjaman Perumahan - kementerian kewangan
            //455 Jabatan Perumahan Negara - kementerian perumahan dan kerajaan tempatan(kena update id_kementerian kpd 19)
            //751 suruhanjaya koperasi malaysia - kpdnhep
            //X guna -750 kewangan

            for (int i = 0; i < v.size(); i++) {
                  a = (Tblrujagensi) v.get(i);
                  if (a.getIdAgensi().equals(Long.parseLong("363")) || a.getIdAgensi().equals(Long.parseLong("455")) || a.getIdAgensi().equals(Long.parseLong("751"))) {
                        tempAgensi.add(a);
                  }

            }
			
        	Iterator<Tblrujagensi> it = tempAgensi.iterator();
			String selected = "";
			while (it.hasNext()){
				a = it.next();
				for(int x = 0; x < ve.size(); x++){
					k = (Tblrujkementerian)ve.get(x);
					if (a.getIdKementerian().equals(k.getIdKementerian())){
//						myLog.info("getagensi : " + a.getIdKementerian() + "::" + k.getIdKementerian());
						if(k.getIdKementerian().equals(selectedValue)){
//							myLog.info("getagensi selectvalue : " + k.getIdKementerian() + ":: " + selectedValue );
							selected = "selected";
						}
						else{
							selected="";
						}
							sb.append("<option " + selected  + " value= " + a.getIdAgensi() + ">"
									+ a.getKodAgensi() + " - " + a.getNamaAgensi() + " ( " 
									+ k.getNamaKementerian() + " ) </option>\n");
						
					}
				}
			}
			sb.append("</select>");
			
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
		return sb.toString();
	}
	
	public String getAgensiDanKementerianForGadaian(String selectName, String urusan, String disabled ){
		StringBuffer sb = new StringBuffer();
		GadaianSubUrusanAgensi g = null;
		
		try{
			
			sb.append("<select name='" + selectName + "'");
			if(disabled != null){
				sb.append(disabled);
			}
			sb.append(" >\n ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector v = DB.getAgensi();
			Vector ve = DB.getKementerian();
			
			Vector agensiUrusan = getAgensiBySubUrusan();
			Vector tempAgensi = new Vector<Tblrujagensi>();
			Tblrujagensi a = new Tblrujagensi();
			Tblrujkementerian k = new Tblrujkementerian();
			
			//363 Bahagian Pinjaman Perumahan - kementerian kewangan
            //455 Jabatan Perumahan Negara - kementerian perumahan dan kerajaan tempatan(kena update id_kementerian kpd 19)
            //751 suruhanjaya koperasi malaysia - kpdnhep
            //X guna -750 kewangan
			
        	g = new GadaianSubUrusanAgensi();
            for (int i = 0; i < v.size(); i++) {
                a = (Tblrujagensi) v.get(i);
                for (int x = 0; x < agensiUrusan.size(); x++){
                	g = (GadaianSubUrusanAgensi)agensiUrusan.get(x);
                	 if ((a.getIdAgensi().equals(Long.parseLong(g.getId_agensi()))) && (g.getId_subUrusan().equalsIgnoreCase(urusan)) ) {
                         tempAgensi.add(a);
                   }
                	
                }
          }

			Iterator<Tblrujagensi> it = tempAgensi.iterator();
			String selected = "";
			myLog.info("urusan : " + urusan);
			while (it.hasNext()){
				a = it.next();
				for(int x = 0; x < ve.size(); x++){
					k = (Tblrujkementerian)ve.get(x);
					if (a.getIdKementerian().equals(k.getIdKementerian())){
						myLog.info("agensi id kem : " + a.getIdKementerian());
						myLog.info("kem id kem : " + k.getIdKementerian());
							sb.append("<option value= " + k.getIdKementerian() + ">"
									+ a.getKodAgensi() + " - " + a.getNamaAgensi() + " ( " 
									+ k.getNamaKementerian() + " ) </option>\n");
					}
				}
			}
			sb.append("</select>");
			
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
		return sb.toString();
	}
	
	public String getNegeri(String idnegeri){
		
	    Vector v = null;
	    Tblrujnegeri n = null;
		try {
			v = new Vector();
			v = DB.getNegeri(idnegeri);
			n = (Tblrujnegeri)v.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		
		return n.getNamaNegeri();
		
	}
	
	public String getAgensi(Long idagensi){
		
	    Vector v = null;
	    Tblrujagensi a = null;
	    String agensiName = "";
		try {
			v = new Vector();
			v = DB.getAgensi();
			for(int i = 0; i < v.size(); i++){
				a = (Tblrujagensi)v.get(i);
				if(a.getIdAgensi().equals(idagensi)){
					agensiName = a.getNamaAgensi();
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return agensiName;
		
	}
	
	public String getKementerian(Long idKementerian){
		
	    Vector v = null;
	    Tblrujkementerian k = null;
	    String namaKementerian = "";
		try {
			v = new Vector();
			v = DB.getKementerian();
			k = (Tblrujkementerian)v.get(0);
			
			for(int i = 0; i < v.size(); i++){
				k = (Tblrujkementerian)v.get(i);
				if(k.getIdKementerian().equals(idKementerian)){
					namaKementerian = k.getNamaKementerian();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		
		return namaKementerian;
		
	}
	
	public String getSubUrusan(String idSub){
		
	    Tblrujsuburusan s = null;
		try {
			s = DB.getSubUrusan(idSub);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    	
		return s.getNamaSuburusan();
		
	}
	
	public Vector getAgensiBySubUrusan(){
		Vector v =  null;
		Db db = null;
		GadaianSubUrusanAgensi g = null;
		String sql = "Select id_suburusanstatus,id_suburusan,id_agensi from "
				+ " TBLHTPRUJSUBURUSANAGENSI order by id_suburusanstatus";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			v = new Vector();
			while (rs.next()) {
				g = new GadaianSubUrusanAgensi();
				g.setId_agensi(rs.getString("id_agensi"));
				g.setId_subUrusan(rs.getString("id_suburusan"));
				g.setId_suburusanstatus(rs.getString("id_suburusanstatus"));
				v.add(g);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		finally {
			if (db != null)
				db.close();
		}

		return v;
	}
	
	//fir 06022010
	public String getTajukForGadaian(String selectName){
		StringBuffer sb = new StringBuffer();
		
		try{
			sb.append("<select name='" + selectName + "'> ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			sb.append("</select>");		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public String getTajukForGadaian(String selectName, String urusan , String jsFunction){
		StringBuffer sb = new StringBuffer();
		
		try{
			//TAJUK BAGI PINJAMAN PERUMAHAN PERBENDAHARAAN (id 38)
//			String tajukPP1 = "discharge of charge ".toUpperCase();
			String tajukPP2 = "memorandum of charge ".toUpperCase(); 
			String tajukPP3 = "satisfaction of charge ".toUpperCase();
			
			//TAJUK BAGI PINJAMAN TMP & SKM (id 40 )
//			String skm1 = "gadaian/cagaran bagi pembiayaan/pinjaman tabung modal pusingan jpk ".toUpperCase();
			String skm1 = "menandatangani borang gadaian 16a ".toUpperCase();
//			String skm2 = "pelepasan gadaian 16n ".toUpperCase();
			
			//TAJUK BAGI PINJAMAN KOS RENDAH JABATAN PERUMAHAN NEGERA (KPKT) (id 39)
			String kpkt1 = "menandatangani borang gadaian 16a ".toUpperCase();
//			String kpkt2 = "pelepasan gadaian 16n ".toUpperCase();
			
			//TAJUK BAGI PELETAKHAKAN HAK GADAIAN (id 41)
			String gadaian1 = "menandatangani borang gadaian 16a ".toUpperCase();
//			String gadaian2 = "pelepasan gadaian 16n ".toUpperCase();
			
			
			
			sb.append("<select name='" + selectName + "'");
			if(jsFunction != null){
				sb.append(jsFunction);
			}
			sb.append(" >\n ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			
			if(urusan.equalsIgnoreCase("38")){
//				sb.append("<option value=\""+ tajukPP1 + "\" >" + tajukPP1 + "  </option>\n");
				sb.append("<option value=\""+ tajukPP2 + "\" >" + tajukPP2 + "  </option>\n");
				sb.append("<option value=\""+ tajukPP3 + "\" >" + tajukPP3 + "  </option>\n");
				
			}
			if(urusan.equalsIgnoreCase("39")){
				sb.append("<option value=\""+ kpkt1 + "\" >" + kpkt1 + "  </option>\n");
//				sb.append("<option value=\""+ kpkt2 + "\" >" + kpkt2 + "  </option>\n");
				
			}
			if (urusan.equalsIgnoreCase("40")){
				sb.append("<option value=\""+ skm1 + "\" >" + skm1 + "  </option>\n");
				//sb.append("<option value=\""+ skm2 + "\" >" + skm2 + "  </option>\n");
				
			}
			if (urusan.equalsIgnoreCase("41")){
				sb.append("<option value=\""+ gadaian1 + "\" >" + gadaian1 + "  </option>\n");
//				sb.append("<option value=\""+ gadaian2 + "\" >" + gadaian2 + "  </option>\n");
				
			}
					
			sb.append("</select>");		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return sb.toString();
		
	}
	
	public String getAgensiDanKementerianName(Long selectedValue){

		String namaAgensi = "";
		
		try{
			
			Vector v = DB.getAgensi();
			Vector ve = DB.getKementerian();
			Vector tempAgensi = new Vector<Tblrujagensi>();
			Tblrujagensi a = new Tblrujagensi();
			Tblrujkementerian k = new Tblrujkementerian();
					
			//363 Bahagian Pinjaman Perumahan - kementerian kewangan
            //455 Jabatan Perumahan Negara - kementerian perumahan dan kerajaan tempatan(kena update id_kementerian kpd 19)
            //751 suruhanjaya koperasi malaysia - kpdnhep
            //X guna -750 kewangan

            for (int i = 0; i < v.size(); i++) {
                  a = (Tblrujagensi) v.get(i);
                  if (a.getIdAgensi().equals(Long.parseLong("363")) || a.getIdAgensi().equals(Long.parseLong("455")) || a.getIdAgensi().equals(Long.parseLong("751"))) {
                        tempAgensi.add(a);
                  }

            }
			
        	Iterator<Tblrujagensi> it = tempAgensi.iterator();
			while (it.hasNext()){
				a = it.next();
				for(int x = 0; x < ve.size(); x++){
					k = (Tblrujkementerian)ve.get(x);
					if (a.getIdKementerian().equals(k.getIdKementerian())){
						if(k.getIdKementerian().equals(selectedValue)){
							namaAgensi += a.getNamaAgensi() + " - (" + k.getNamaKementerian() + " )";
							//untuk capture id agensi value
							this.context.put("agensiVal", a.getIdAgensi());
							
						}
					}
				}
			}		
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
		return namaAgensi;
	}
	
	public void GeranView(String idHTPGadaian) throws Exception{		
		Vector geranInfo = null;
		Hashtable hashGeran = null; 		
		try{
			geranInfo = new Vector();
			hashGeran = new Hashtable();
			
			geranInfo = FrmGadaianHakmilikData.setGeranInfo(idHTPGadaian);			
			if(geranInfo.isEmpty()){				
				hashGeran.put("TarikhTerima", "");
				hashGeran.put("keterangan", "");
				geranInfo.addElement(hashGeran);
				
				this.context.put("GeranBean", geranInfo);
				this.context.put("mode", "");
			    this.context.put("classDis", "");
			    this.context.put("pagemode", "baru");
				
			}else if(this.mode.equalsIgnoreCase("kemaskinigeran")){
				this.context.put("GeranBean", geranInfo);
				this.context.put("mode", "");
				this.context.put("classDis", "");
				this.context.put("pagemode", "kemaskini");
					
			}else{
				this.context.put("GeranBean", geranInfo);
				this.context.put("mode", "disabled='disabled'");
				this.context.put("classDis", "class='disabled'");
				this.context.put("pagemode", "view");
							
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	public void GeranSimpan(HttpSession session)throws Exception{	
		try{
			Hashtable<String,String> hashGeran = new Hashtable<String,String>();			
			hashGeran.put("TarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
			hashGeran.put("keterangan",  getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
			hashGeran.put("idPermohonan",  getParam("idPermohonan") == null ? "" : getParam("idPermohonan"));
			hashGeran.put("userID", idUser);		
			idHTPGadaian = FrmGadaianHakmilikData.SimpanGeran(hashGeran);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void GeranUpdateSimpan(HttpSession session)throws Exception{		
		try{
			Hashtable<String,String> hashGeran = new Hashtable<String,String>();			
			hashGeran.put("TarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
			hashGeran.put("keterangan",  getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
			hashGeran.put("idPermohonan",  getParam("idPermohonan") == null ? "" : getParam("idPermohonan"));
			hashGeran.put("idHTPGadaian",  getParam("idHTPGadaian") == null ? "" : getParam("idHTPGadaian"));
			hashGeran.put("userID", idUser);		
			FrmGadaianHakmilikData.SimpanUpdateGeran(hashGeran);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void maklumat16AView(String idHTPGadaian) throws Exception{		
		Vector info16A = null;
		Hashtable hash16A = null; 		
		try{
			info16A = new Vector();
			hash16A = new Hashtable();
			
			info16A = FrmGadaianHakmilikData.setInfo16A(idHTPGadaian);			
			if(info16A.isEmpty()){				
				hash16A.put("TarikhTerima", "");
				hash16A.put("keterangan", "");
				info16A.addElement(hash16A);
				
				this.context.put("maklumat16ABean", info16A);
				this.context.put("mode", "");
			    this.context.put("classDis", "");
			    this.context.put("pagemode", "baru");
				
			}else if(this.mode.equalsIgnoreCase("kemaskini16a")){
				this.context.put("maklumat16ABean", info16A);
				this.context.put("mode", "");
				this.context.put("classDis", "");
				this.context.put("pagemode", "kemaskini");
					
			}else{
				this.context.put("maklumat16ABean", info16A);
				this.context.put("mode", "disabled='disabled'");
				this.context.put("classDis", "class='disabled'");
				this.context.put("pagemode", "view");
							
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	public void maklumat16ASimpan(HttpSession session)throws Exception{	
		try{
			Hashtable<String,String> hash16A = new Hashtable<String,String>();			
			hash16A.put("TarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
			hash16A.put("keterangan",  getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
			hash16A.put("idPermohonan",  getParam("idPermohonan") == null ? "" : getParam("idPermohonan"));
			hash16A.put("userID", idUser);		
			idHTPGadaian = FrmGadaianHakmilikData.Simpan16A(hash16A);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void maklumat16AUpdateSimpan(HttpSession session)throws Exception{		
		try{
			Hashtable<String,String> hash16A = new Hashtable<String,String>();			
			hash16A.put("TarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
			hash16A.put("keterangan",  getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
			hash16A.put("idPermohonan",  getParam("idPermohonan") == null ? "" : getParam("idPermohonan"));
			hash16A.put("idHTPGadaian",  getParam("idHTPGadaian") == null ? "" : getParam("idHTPGadaian"));
			hash16A.put("userID", idUser);		
			FrmGadaianHakmilikData.SimpanUpdate16A(hash16A);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void maklumat16NView(String idHTPGadaian) throws Exception{		
		Vector info16N = null;
		Hashtable hash16N = null; 		
		try{
			info16N = new Vector();
			hash16N = new Hashtable();
			
			info16N = FrmGadaianHakmilikData.setInfo16N(idHTPGadaian);			
			if(info16N.isEmpty()){				
				hash16N.put("TarikhTerima", "");
				hash16N.put("keterangan", "");
				info16N.addElement(hash16N);
				
				this.context.put("maklumat16NBean", info16N);
				this.context.put("mode", "");
			    this.context.put("classDis", "");
			    this.context.put("pagemode", "baru");
				
			}else if(this.mode.equalsIgnoreCase("kemaskini16n")){
				this.context.put("maklumat16NBean", info16N);
				this.context.put("mode", "");
				this.context.put("classDis", "");
				this.context.put("pagemode", "kemaskini");
					
			}else{
				this.context.put("maklumat16NBean", info16N);
				this.context.put("mode", "disabled='disabled'");
				this.context.put("classDis", "class='disabled'");
				this.context.put("pagemode", "view");
							
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	public void maklumat16NSimpan(HttpSession session)throws Exception{	
		try{
			Hashtable<String,String> hash16N = new Hashtable<String,String>();			
			hash16N.put("TarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
			hash16N.put("keterangan",  getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
			hash16N.put("idPermohonan",  getParam("idPermohonan") == null ? "" : getParam("idPermohonan"));
			hash16N.put("userID", idUser);		
			idHTPGadaian = FrmGadaianHakmilikData.Simpan16N(hash16N);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void maklumat16NUpdateSimpan(HttpSession session)throws Exception{		
		try{
			Hashtable<String,String> hash16N = new Hashtable<String,String>();			
			hash16N.put("TarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
			hash16N.put("keterangan",  getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
			hash16N.put("idPermohonan",  getParam("idPermohonan") == null ? "" : getParam("idPermohonan"));
			hash16N.put("idHTPGadaian",  getParam("idHTPGadaian") == null ? "" : getParam("idHTPGadaian"));
			hash16N.put("userID", idUser);		
			FrmGadaianHakmilikData.SimpanUpdate16N(hash16N);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void BorangDView(String idHTPGadaian) throws Exception{		
		Vector borangDInfo = null;
		Hashtable hashBorangD = null; 		
		try{
			borangDInfo = new Vector();
			hashBorangD = new Hashtable();
			
			borangDInfo = FrmGadaianHakmilikData.setBorangDInfo(idHTPGadaian);			
			if(borangDInfo.isEmpty()){				
				hashBorangD.put("TarikhTerima", "");
				hashBorangD.put("keterangan", "");
				borangDInfo.addElement(hashBorangD);
				
				this.context.put("borangDBean", borangDInfo);
				this.context.put("mode", "");
			    this.context.put("classDis", "");
			    this.context.put("pagemode", "baru");
				
			}else if(this.mode.equalsIgnoreCase("kemaskiniborangd")){
				this.context.put("borangDBean", borangDInfo);
				this.context.put("mode", "");
				this.context.put("classDis", "");
				this.context.put("pagemode", "kemaskini");
					
			}else{
				this.context.put("borangDBean", borangDInfo);
				this.context.put("mode", "disabled='disabled'");
				this.context.put("classDis", "class='disabled'");
				this.context.put("pagemode", "view");
							
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	public void BorangDSimpan(HttpSession session)throws Exception{	
		try{
			Hashtable<String,String> hashBorangD = new Hashtable<String,String>();			
			hashBorangD.put("TarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
			hashBorangD.put("keterangan",  getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
			hashBorangD.put("idPermohonan",  getParam("idPermohonan") == null ? "" : getParam("idPermohonan"));
			hashBorangD.put("userID", idUser);		
			idHTPGadaian = FrmGadaianHakmilikData.SimpanBorangD(hashBorangD);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void BorangDUpdateSimpan(HttpSession session)throws Exception{		
		try{
			Hashtable<String,String> hashBorangD = new Hashtable<String,String>();			
			hashBorangD.put("TarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
			hashBorangD.put("keterangan",  getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
			hashBorangD.put("idPermohonan",  getParam("idPermohonan") == null ? "" : getParam("idPermohonan"));
			hashBorangD.put("idHTPGadaian",  getParam("idHTPGadaian") == null ? "" : getParam("idHTPGadaian"));
			hashBorangD.put("userID", idUser);		
			FrmGadaianHakmilikData.SimpanUpdateBorangD(hashBorangD);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void doListing(HttpSession session,String action,String submit,String mode,Vector v) throws Exception {		
		this.context.put("senaraiList1", v); 
		this.context.put("submit", submit); 
		setupPage2(session,action,v);
		
	}
	
	public void setupPage2(HttpSession session,String action,Vector list) {
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
			this.context.put("SenaraiPermohonan",paging.getPage(page));
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
	 
		private void getPermohonanInfo()throws Exception{
			String idPermohonan = getParam("idPermohonan");
			String idHtpPermohonan = getParam("txtidHtpPermohonan");
			htpPermohonan = getIPembelian().findPermohonan(idPermohonan,idHtpPermohonan);
			context.put("htpPermohonan", htpPermohonan);
			
		}
		
		public void statusView(String idPermohonan) throws Exception{			
			Hashtable hInfo = null; 		
			try{
				hInfo = new Hashtable();
				hInfo = getIHTP().getInfoSelesaiPermohonan(idPermohonan);				
				if(hInfo == null){					
					//hInfo.put("tarikhSelesai", "");
					//hInfo.put("keterangan", "");
					//geranInfo.addElement(hashGeran);					
					this.context.put("selesaiBean", hInfo);
					this.context.put("mode", "");
				    this.context.put("classDis", "");
				    this.context.put("pagemode", "baru");
					
				}else if(this.mode.equalsIgnoreCase("selesaikemaskini")){
					this.context.put("selesaiBean", hInfo);
					this.context.put("mode", "");
					this.context.put("classDis", "");
					this.context.put("pagemode", "kemaskini");
						
				}else{					
					this.context.put("selesaiBean", hInfo);
					this.context.put("mode", "disabled='disabled'");
					this.context.put("classDis", "class='disabled'");
					this.context.put("pagemode", "view");
						
				}
				
			}catch(Exception e){
				e.printStackTrace();
				
			}
		
		}		
		
		private IPembelian getIPembelian(){
			if (iPembelian==null){
				iPembelian=new PembelianBean();
			}
			return iPembelian;
		} 

		private IHtp getIHTP(){
			if(iHTP== null)
				iHTP = new HtpBean();
			return iHTP;
		}	 
		
		private void kemaskiniSimpanStatusSelesai(String idFail,String idPermohonan,String idSubUrusan,String langkah)throws Exception {
			 try {
				myLog.info("idFail:"+idFail);
				myLog.info("idPermohonan:"+idPermohonan);
				subUrusanStatusFail = new Tblrujsuburusanstatusfail();
				subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
				subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
				subUrusanStatusFail.setAktif("0");
			
				Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
				long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,idSubUrusan,"=");
				myLog.info("setIdSuburusanstatus:"+setIdSuburusanstatus);
				subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
				subUrusanStatusFailN.setAktif("1");
				//myLog.info("getParam(\"txtKeterangan\"):"+getParam("txtKeterangan"));
				subUrusanStatusFailN.setUrl(getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
				subUrusanStatusFailN.setIdMasuk(Long.parseLong(idUser));
				//myLog.info("getParam(\"txdTarikhTerima\"):"+getParam("txdTarikhTerima"));
				subUrusanStatusFailN.setTarikhMasuk(new Date(getParam("txdTarikhTerima")));
				getIHTP().kemaskiniSimpanStatusPermohonanAktif(subUrusanStatusFail, subUrusanStatusFailN,getParam("txdTarikhTerima"));
				
			} catch (Exception e) {
				throw new Exception("Ralat FrmGadaian[554]:"+e.getCause());				
			}
			 
		}
		
		private void kemaskiniSimpanStatusSelesai(String idFail,String idPermohonan)throws Exception {
			 try {
				subUrusanStatusFail = new Tblrujsuburusanstatusfail();
				subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
				subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
				subUrusanStatusFail.setUrl(getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
				subUrusanStatusFail.setIdKemaskini(Long.parseLong(idUser));
				getIHTP().kemaskiniSimpanStatusPermohonanAktif(subUrusanStatusFail,getParam("txdTarikhTerima"));
				
			} catch (Exception e) {
				throw new Exception("Ralat FrmGadaian[2715]:"+e.getCause());
				
			}
		}		
	 
	private IHTPHakmilikUrusan getIHakmilikUrusan(){
		if(iHakmilikUrusan== null)
			iHakmilikUrusan = new HTPHakmilikUrusanGadaianBean();
		return iHakmilikUrusan;

	}
		
}//close class
