/*Insert into TBLRUJAGENSI
   (ID_AGENSI, KOD_AGENSI, NAMA_AGENSI, ALAMAT1, ALAMAT2, 
    ALAMAT3, POSKOD, ID_NEGERI, JAWATAN, ID_KEMENTERIAN, 
    ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI)
 Values
   (751, '01', 'SURUHANJAYA KOPERASI MALAYSIA', 'Tingkat 5-7, Blok J,', 'Pusat Bandar Damansara,', 
    'TIADA', '50608', 14, 'TIADA', 25, 
    1, TO_DATE('01/20/0003 10:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, TO_DATE('01/20/0003 10:00:00', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;*/
package ekptg.view.htp;

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
import ekptg.model.htp.FrmSenaraiFailPelepasanGadaianData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.GadaianSubUrusanAgensi;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;

public class FrmGadaianPelepasanTerdahulu extends AjaxBasedModule {
	
	private final String PATH="app/htp/gadaian/";
	private Date now = new Date();
	private HtpPermohonan htpPermohonan = null;
	private IHtp iHTP = null;  
	private IPembelian iPembelian = null;
    private Long idJenisHakmilik = 0L;	
    private SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmGadaianPelepasanTerdahulu.class);
    private String idSubUrusan = "0";
    private String idHTPGadaian = "";
    private String idFail = "0";
    private String idPermohonan = "";
    private String idUser = "";    
    private String result = new String();
 	private Tblrujsuburusanstatusfail subUrusanStatusFail = null;
 	String doChange = "";
    String mode = "";
    String submit = "";

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String action = "";
     	String style1 = "";
    	String style2 = "";
    	String disability = "";
    	String readability = "";
		String vm = "";
    	Vector list = new Vector();
    	list.clear();

    	submit = getParam("command");
		mode = getParam("mode");
		action = getParam("action");
		String selectedTab = getParam("tabId");
		doChange = getParam("doChange");
		idHTPGadaian = getParam("idHTPGadaian");
		
		myLog.info("command : " + submit+",mode : " + mode);

	
		if (selectedTab == null || "".equals(selectedTab)) {
			selectedTab = "0";
		}
		this.context.put("selectedTab", selectedTab);
		idUser = (String)session.getAttribute("_ekptg_user_id");		
		idPermohonan = getParam("idPermohonan");

		try{
			if("FailBaru".equals(submit)){				
				/*********************************************
				 * template : app/htp/frmGadaianSemakan2Ajax.jsp
				 * *******************************************/				
				vm = PATH+"frmGadaianSemakan2Ajax.jsp";				
				String idFail = "0";

				if("view".equals(mode)){					
					readability = "readonly";
				    disability = "disabled";
				    idFail = getParam("idFail");
				    setFailBaru(session,idFail);
				    style2 = "none";
					ViewFailBaru(session,disability,readability,style1,style2);
					
				}else if("kemaskini".equals(mode)){
					idFail = getParam("idFail");
				    setFailBaru(session,idFail);
				    style1 = "none";
					ViewFailBaru(session,disability,readability,style1,style2);
					
				}else if("simpan".equals(mode)){
					readability = "readonly";
				    disability = "disabled";
					idFail = SimpanFailBaru(session);
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
			
			}else if("SenaraiPermohonan".equals(submit)){				
				/*********************************************
				 * template : app/htp/gadaian/frmGadaianSenaraiPermohonanAjax.jsp
				 * *******************************************/				
				//vm = PATH+"frmGadaianPelepasanGadaiSenaraiPermohonan.jsp";
				//2010/07/14
				vm = PATH+"frmGadaianSenaraiPermohonanAjax.jsp";

				String idFail = getParam("idFail");
				String noFailKJP = getParam("noFailKJP");
				String carian = getParam("NamaPemohon");
				ListPermohonan(session,idFail,noFailKJP, carian);
				list = FrmGadaianSenaraiPermohonanData.getListPermohonan();
				doListing(session,action,submit,mode,list);
			    this.context.put("SenaraiPermohonan", list);
			    this.context.put("NoFail", getParam("noFail"));
			    this.context.put("carianNoFail", getParam("noFailKJP"));
			    this.context.put("IdFail", getParam("idFail"));
			    this.context.put("carian", getParam("NamaPemohon"));
			    
			}else if("Semakan".equals(submit)){				
				/*********************************************
				 * template : app/htp/frmGadaianSemakanAjax.jsp
				 * *******************************************/
				
				vm = PATH+"frmGadaianSemakanAjax.jsp";
				getPermohonanInfo();

				String idPermohonan = "";
				this.context.put("NoFail", getParam("noFail"));
			    this.context.put("IdFail", getParam("idFail"));
			    this.context.put("IdPermohonan", getParam("idPermohonan"));
			    this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmGadaianSemakan"));
			    this.context.put("semakclass", new FrmSemakan());

				if("baru".equals(mode)){
					style1 = "none";
					ListSemakBaru(session);
				    DataSemakBaru(session,disability,readability,style1,style2);
				    //log.info("GadaianProcess::Semakan::baru");
				    
				}else if("kemaskini".equals(mode)){
					style1 = "none";
					ListSemak(session,idPermohonan);
				    DataSemak(session,disability,readability,style1,style2);
				    
				}else if("simpan".equals(mode)){
					//fir
					vm = PATH+"frmGadaianSemakanAjax.jsp";
					readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
					idPermohonan = SimpanSemak(session);					
					myLog.info("Semakan::simpan::idpermohonan: " + idPermohonan );
					
					String[] cbsemaks = this.request.getParameterValues("cbsemaks");									
		        	FrmSemakan frmSemak = new FrmSemakan();
		        	frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
		        	if(cbsemaks!=null){
		        		for (int i = 0; i < cbsemaks.length; i++) { 
		        			frmSemak = new FrmSemakan();
		        			//log.info("GadaianProcess:Semakan::simpan::cbsemaks:::"+cbsemaks[i]);
		        			frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));           
		        		}
		        	}
						        	
					ListSemak(session,idPermohonan);
				    DataSemak(session,disability,readability,style1,style2);

				    this.context.put("IdPermohonan", idPermohonan);
				    this.context.put("SimpanStatus", "success");
					this.context.put("ResultSimpan", result);			
					
				}else if("hapus".equals(mode)){
					
				}else{					
				    readability = "readonly";
				    disability = "disabled";
				    style2 = "none";				    
				    ListSemak(session,idPermohonan);
				    DataSemak(session,disability,readability,style1,style2);
				}

			}else if("PenHakmilik".equals(submit)){	
				/*********************************************
				 * template : app/htp/frmGadaianPelepasanHakmilik.jsp
				 * *******************************************/
				//vm = PATH+"frmGadaianPelepasanDaftarHakmilik.jsp";
				vm = PATH+"frmGadaianMaklumatHakmilik.jsp";
			    this.context.put("NoFail", getParam("noFail"));
			    this.context.put("IdFail", getParam("idFail"));
			    this.context.put("IdPermohonan", getParam("idPermohonan"));
			    
			    if("baru".equals(mode)){
			    	
			    }else if("kemaskini".equals(mode)){
			    	style1 = "none";
				    ListPenHamilik(session);
				    DataPenHamilik(session, disability, readability,style1,style2);
				    
			    }else if("simpan".equals(mode)){
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
			    	SimpanPenHamilik(session);
			    	ListPenHamilik(session);
				    DataPenHamilik(session, disability, readability,style1,style2);
				    this.context.put("SimpanStatus", "success");
					this.context.put("ResultSimpan", result);
					
			    }else if("hapus".equals(mode)){
			    	
			    }else if("doChangeDaerah".equalsIgnoreCase(mode)){			    	
			    	readability = "readonly";
				    disability = "disabled";
			    	doChangeDaerah(session,disability, readability, style1,style2);
			    
			    }else{
				    readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
				    ListPenHamilik(session);
				    DataPenHamilik(session, disability, readability,style1,style2);
			    
			    }		    
			
			}else if ("Hakmilik".equals(submit)){				
				/*********************************************
				 * template : app/htp/gadaian/frmGadaianPelepasanDaftarHakmilik.jsp
				 * *******************************************/			
				vm = PATH+"frmGadaianPelepasanPemilikPeguam.jsp";
				getPermohonanInfo();
				idPermohonan = getParam("idPermohonan");
				this.context.put("IdPermohonan", idPermohonan);
			    this.context.put("NoFail", getParam("noFail"));
			    this.context.put("IdFail", getParam("idFail"));
			    this.context.put("IdHakmilikurusan", getParam("idHakmilikurusan"));
			    
				list = FrmGadaianInfoData.getSemak(idPermohonan);
				this.context.put("Info", list);
				Hashtable h = (Hashtable) list.get(0);
				idFail = String.valueOf(h.get("idFail"));
				idSubUrusan = String.valueOf(h.get("idSuburusan"));
				this.context.put("selectKementerian",getKementerian(Long.parseLong(h.get("idKementerian").toString())));
			    this.context.put("selectAgensi",getAgensi(Long.parseLong(h.get("idAgensi").toString())));
			    this.context.put("selectNegeri",getNegeri(h.get("idNegeri").toString()));
				this.context.put("selectSuburusan",getSubUrusan(h.get("idSuburusan").toString()));
				this.context.put("idSuburusan", h.get("idSuburusan").toString());
				
			    if("simpanHakmilik".equals(mode)){
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
			    	SimpanHakmilik(session);
			    	ListHakmilik(session);
			    	DataHakmilik(session,disability,readability,style1,style2);
			    	this.context.put("SimpanStatus", "success");
					this.context.put("ResultSimpan", result);
					
			    }else if("kemaskiniHakmilik".equals(mode)){
			    	style1 = "none";
			    	ListHakmilik(session);
			    	DataHakmilik(session,disability,readability,style1,style2);
		    	
			    }else if("hapusHakmilik".equals(mode)){
			    	
			    }else if("simpanPeguam".equals(mode)){
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
			    	SimpanPeguam(session);
			    	ListPeguam(session);
			    	DataPeguam(session,disability,readability,style1,style2);				
					this.context.put("SimpanStatus", "success");
					this.context.put("ResultSimpan", result);
					this.context.put("selectedTab", 1);
			
			    }else if("kemaskiniPeguam".equals(mode)){
			    	style1 = "none";
			    	ListPeguam(session);
			    	DataPeguam(session,disability,readability,style1,style2);
			    	this.context.put("selectedTab", 1);
			    
			    }else if("hapusPeguam".equals(mode)){
			    	
			    }else if("hakmilikview".equals(mode)){				
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
					ListHakmilik(session);
					DataHakmilik(session,disability,readability,style1,style2);
			    
			    }else if("peguamview".equals(mode)){
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
					ListPeguam(session);
					DataPeguam(session,disability,readability,style1,style2);
					this.context.put("selectedTab", 1);
			    
//			    }else if("geranview".equalsIgnoreCase(mode)){
//			    	GeranView(idHTPGadaian);
//			    	this.context.put("selectedTab", 2);
//			    
//			    }else if("simpangeran".equalsIgnoreCase(mode)){
//			    	GeranSimpan(session);
//			    	GeranView(idHTPGadaian);
//			    	this.context.put("selectedTab", 2);
//			    	
//			    }else if("kemaskinigeran".equalsIgnoreCase(mode)){
//			    	GeranView(idHTPGadaian);
//			    	this.context.put("mode", "");
//					this.context.put("classDis", "");
//			    	this.context.put("selectedTab", 2);
//			    	
//			    }else if("simpanupdategeran".equalsIgnoreCase(mode)){
//			    	GeranUpdateSimpan(session);
//			    	GeranView(idHTPGadaian);
//			    	this.context.put("selectedTab", 2);
			    	
			    }else if("selesaiview".equalsIgnoreCase(mode)){
			    	myLog.info("selesaiview");
			    	statusView(idPermohonan);
			    	this.context.put("selectedTab",2);
			    
			    }else if("selesaisimpan".equalsIgnoreCase(mode)){
			    	myLog.info("selesaisimpan");
			    	kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,"99");
			    	statusView(idPermohonan);
			    	this.context.put("selectedTab",2);
			    	
			    }else if("selesaikemaskini".equalsIgnoreCase(mode)){
			    	myLog.info("selesaikemaskini");
			    	statusView(idPermohonan);
			    	this.context.put("mode", "");
					this.context.put("classDis", "");
			    	this.context.put("selectedTab", 2);
			    	
			    }else if("selesaikemaskinisimpan".equalsIgnoreCase(mode)){
			    	myLog.info("selesaikemaskinisimpan");
			    	kemaskiniSimpanStatusSelesai(idFail,idPermohonan);
			    	statusView(idPermohonan);
			    	this.context.put("selectedTab", 2);
			    	
			    }
			    
			    
			}else{				
			    this.context.remove("lists");
				String page = getParam("namaskrin");

				vm = PATH+"frmGadaianPelepasanGadaiSenaraiFail.jsp";
				String key_cari = getParam("NamaFail");
				String keyNo_cari = getParam("NoFail");
				String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
				Long idNegeri = Long.parseLong(Negeri);
				list =  FrmSenaraiFailPelepasanGadaianData.getSenaraiFailPelepasanGadaianLama(key_cari, keyNo_cari, Negeri);
				//doListing(session,action,"",mode,list);
				setupPage(session,action,list);

		    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,disability));
			    this.context.put("carian", getParam("NamaFail"));
			    this.context.put("carianNoFail", getParam("NoFail"));
			    this.context.put("Negeri", idNegeri);
				//log.info("GadaianProcess::SenaraiFail");
			    
			    if ("cancel".equalsIgnoreCase(mode)){
			    	this.context.put("clear", "clear");
			    }
			    if(!page.equals("")){
			    	myLog.info("page permohonan:"+page);
			    	vm = PATH+"frmGadaianSenaraiPermohonanAjax.jsp";
					String idFail = getParam("idFail");
					String carian = getParam("NamaPemohon");
					String noFailKJP = getParam("noFailKJP");
					ListPermohonan(session, idFail,noFailKJP, carian);
					list = FrmGadaianSenaraiPermohonanData.getListPermohonan();
					doListing(session,action,submit,mode,list);
				    this.context.put("NoFail", getParam("noFail"));
				    this.context.put("IdFail", getParam("idFail"));
				    this.context.put("carian", getParam("NamaPemohon"));
			    }
			    
			}
						
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		//log.info("vm :" + vm);
		this.context.put("idHTPGadaian", idHTPGadaian);
		return vm;
		
    }//close do template
	
	//*** Fail Baru Controller
	private void DataFailBaru(HttpSession session, String disability, String readability, String style1, String style2) 
		throws Exception	{
		try{
			myLog.info("data file baru : mode :: " + mode);			
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
			    //this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));
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
	
	public void setFailBaru(HttpSession session, String idFail) throws Exception
	{
		try{
			FrmGadaianSemakan1Data.setSemak(idFail);	
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
	}
	
	private void ViewFailBaru(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			if(this.mode.equalsIgnoreCase("kemaskini")){
				
				list = FrmGadaianSemakan1Data.getSemak();
			    this.context.put("Semak", list);
			    Hashtable h = (Hashtable) list.get(0);	
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
			    Hashtable h = (Hashtable) list.get(0);	
			    
			    myLog.info("GadaianA :: ViewFailBaru : " + h);
			    
//			    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()), "disabled"));
			    this.context.put("selectNegeri",getNegeri((String)h.get("idNegeri")));
			    		    
			    //fir
			    this.context.put("socKementerian", h.get("idKementerian").toString());
			    this.context.put("selectAgensi",getAgensiDanKementerianName(Long.parseLong((String)h.get("idKementerian"))));
			    
//			    log.info("viewfailbaru : idkem : "  + h.get("idKementerian").toString());
//			    log.info("GadaianA :: VIewFaiBaru : " + getAgensiDanKementerianForGadaian("socAgensi", Long.parseLong(h.get("idKementerian").toString()), "disabled"));
			    
			    
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
//		    log.info("GadaianA :: ViewFailBaru : " + h);
//		    
////		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()), "disabled"));
//		    this.context.put("selectNegeri",getNegeri((String)h.get("idNegeri")));
//		    		    
//		    //fir
//		    this.context.put("socKementerian", h.get("idKementerian").toString());
//		    this.context.put("selectAgensi",getAgensiDanKementerianName(Long.parseLong((String)h.get("idKementerian"))));
//		    
////		    log.info("viewfailbaru : idkem : "  + h.get("idKementerian").toString());
////		    log.info("GadaianA :: VIewFaiBaru : " + getAgensiDanKementerianForGadaian("socAgensi", Long.parseLong(h.get("idKementerian").toString()), "disabled"));
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
//				log.info("GadaianProcessA::SimpanSemak:: h = "+h);
//				idFail = FrmGadaianSemakan1Data.simpan(h);
//				result = "baru";
//			}
//			else{
//				
//				//kemaskini fail
//				Hashtable h = new Hashtable();
//				h.put("idFail", Integer.parseInt(getParam("idFail")));			
//				h.put("Tajuk", getParam("txtTajuk"));
//				////log.info("GadaianProcess::SimpanSemak:: h = "+h);
//				idFail = FrmGadaianSemakan1Data.update(h);
//				result = "kemaskini";
//			
//			}
//			
//		}
//		catch(Exception e){
//			log.error("Error : " + e.getMessage());
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
				Hashtable h = new Hashtable();
				h.put("idNegeri", Integer.parseInt(getParam("socNegeri")));							
				//fir ganti id kementerian ngan cara baru
				h.put("idKementerian", Integer.parseInt(getParam("socAgensi")));
				h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));		
				h.put("Tajuk", getParam("socTajuk"));
				h.put("tarikhBukaFail", getParam("txdTarikhBukaFail"));
				h.put("noFailSek", getParam("txtNoFailSek"));
				h.put("idMasuk", idUser);				
				idFail = FrmGadaianSemakan1Data.simpan(h);
				//log.info("GadaianProcessA::SimpanSemak:: h = " + h);
				result = "baru";
	
			}else{				
				//kemaskini fail
				Hashtable h = new Hashtable();
				h.put("idFail", Integer.parseInt(getParam("idFail")));			
				h.put("Tajuk", getParam("txtTajuk"));
				h.put("idKemaskini", idUser);
				idFail = FrmGadaianSemakan1Data.update(h);
				//log.info("GadaianProcess::SimpanSemak:: h = "+h);
				result = "kemaskini";
			
			}
			
		} catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
		return idFail;
		
	}
	
	//*** Senarai Fail Gadaian Controller
	public void ListFail(HttpSession session, String key_cari, String keyNo_cari, Long idNegeri) throws Exception{
		try{
			FrmSenaraiFailGadaianData.setList(key_cari,keyNo_cari,idNegeri,null,null,null,null,null);
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
	}
	
	//*** Senarai Permohonan Controller
	public void ListPermohonan(HttpSession session,String idFail,String noFail, String carian) throws Exception {
		try{
			FrmGadaianSenaraiPermohonanData.setListPermohonan(idFail,noFail,carian);
		
		}catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
	}
	
	//*** Gadaian Semakan Controller
	private void ListSemakBaru(HttpSession session) throws Exception{
		try{
			FrmGadaianSemakanData.setSemakBaru(getParam("idFail"));
		}catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
	}
	
	private void DataSemakBaru(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmGadaianSemakanData.getSemakBaru();
		    this.context.put("SemakBaru", list);
		    Hashtable h = (Hashtable) list.get(0);	
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
	
	private void ListSemak(HttpSession session, String id) throws Exception {
		try{
			String idPermohonan = "";
			if (getParam("idPermohonan") != ""){
				idPermohonan = (String)getParam("idPermohonan");
			}
			else if (id != ""){
				idPermohonan = id;
			}
			myLog.info("GadaianProcess::ListSemak::idPermohonan = 0");
			FrmGadaianSemakanData.setSemak(idPermohonan);
			
		}catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
	}
	
	private void DataSemak(HttpSession session, String disability, String readability, String style1, String style2) 
		throws Exception {
		Vector list = new Vector();
		list.clear();
		try{
			if(mode.equalsIgnoreCase("kemaskini")){
				
				list = FrmGadaianSemakanData.getSemak();
			    this.context.put("Semak", list);
			    Hashtable h = (Hashtable) list.get(0);
			    
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
			    //log.info("no fail lain: " + getParam("txtNoFailLain"));
			    
			    this.context.put("noRujukan", getParam("txtNoFailLain"));
				
			}else{				
				list = FrmGadaianSemakanData.getSemak();
			    this.context.put("Semak", list);
			    Hashtable h = (Hashtable) list.get(0);
			    
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
			    //log.info("no fail lain: " + getParam("txtNoFailLain"));
			    
			    this.context.put("noRujukan", getParam("txtNoFailLain"));
				
			}

		}
		catch(Exception ex){
			////log.info("GadaianProcess::DataSemak::Exception = "+ex);
			ex.printStackTrace();
		}
	}
	
	private String SimpanSemak(HttpSession session) throws Exception {
		String idPermohonan = "";
		try{
			if(getParam("idPermohonan") == ""){	//fail baru						
				Hashtable h = new Hashtable();
				h.put("IdFail", Integer.parseInt(getParam("idFail")));
				h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
				h.put("Tajuk", getParam("txtTajuk"));
				h.put("socAgensi", getParam("socAgensi"));
				//fir 06022010
				h.put("idSuburusan", Integer.parseInt(getParam("idSuburusan")));
				h.put("NoFailLain", getParam("txtNoFailLain"));
				h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
				h.put("idMasuk", idUser);					
				//log.info("SimpanSemak:FailBaru:: h = "+h);
				idPermohonan = FrmGadaianSemakanData.simpan(h);
				result = "baru";
			
			}else{ //kemaskini fail
				Hashtable h = new Hashtable();
				h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
				//agensi x perlu kemaskini			
				h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
				h.put("NoFailLain", getParam("txtNoFailLain"));
				h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
				h.put("idKemaskini", idUser);					
				//log.info("GadaianProcess::SimpanSemak::KemaskiniFail:: h = "+h);
				idPermohonan = FrmGadaianSemakanData.update(h);
				result = "kemaskini";
				
			}
			//log.info("GadaianProcess::SimpanSemak::Fir IdPermohonan :: " + idPermohonan );
			
		}catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		return idPermohonan;	
		
	}
	
	//*** Gadaian Pendaftaran Hakmilik Controller
	private void ListPenHamilik(HttpSession session) throws Exception
	{
		try{
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			FrmGadaianPenHamilikData.setPenHakmilik(idPermohonan);
			myLog.info("idPermohonan : ListPenHamilik " + id);
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
	}
	
	private void DataPenHamilik(HttpSession session, String disability, String readability, String style1, String style2) throws Exception {		
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmGadaianPenHamilikData.getPenHakmilik();
			if(list.size() != 0){
				String luasLama = "";
				String luasBersamaan = "";
				String noLot = "";
				if(this.mode.equalsIgnoreCase("kemaskini")){				
					this.context.put("PenHamilik", list);
				    Hashtable h = (Hashtable) list.get(0);
				    
				    myLog.info("DataPenHamilik : kemaskini :: " + h);

				    //fir 07022010
				    this.context.put("selectKementerian",getKementerian(Long.parseLong((String)h.get("IdKementerian"))));
				    this.context.put("selectAgensi",getAgensi(Long.parseLong((String)h.get("IdAgensi"))));
				    this.context.put("selectNegeri",getNegeri((String)h.get("IdNegeri")));
				    
				    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong(h.get("IdDaerah").toString()), "style='width:200px;'"));
				    this.context.put("selectMukim",HTML.SelectMukimByDaerah(h.get("IdDaerah").toString(), "socMukim", Long.parseLong(h.get("IdMukim").toString()), "style='width:200px;'"));
				    
				    //this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong(h.get("IdJenishakmilik").toString()),"style='width:200px;'"));
				    if(Integer.parseInt(String.valueOf(h.get("IdNegeri")))==13 || Integer.parseInt(String.valueOf(h.get("IdNegeri")))==12){
						this.context.put("selectJenisHakmilik",UtilHTML.SelectJenisHakmilikMengikutNegeri("socJenisHakmilik",String.valueOf(h.get("IdNegeri")),idJenisHakmilik," style='width:200px;'"));
				    
				    }else{
				    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",idJenisHakmilik," style='width:200px;'"));   
				    }			    
				    this.context.put("selectLot",HTML.SelectLot("socLot",Long.parseLong(h.get("IdLot").toString()),"style='width:200px;' "));
					String socLuas = getParam("socLuas");
		    		context.put("socLuas", socLuas);
		    		luasLama = getParam("Luas");
		    		luasBersamaan = getParam("luasBersamaan");
				    noLot = getParam("txtNoLot");				    

				    this.context.put("selectRizab",HTML.SelectRizab("socRizab",Long.parseLong(h.get("IdRizab").toString()),"style='width:200px;'"));
				    this.context.put("selectKategori",HTML.SelectKategori("socKategori",Long.parseLong(h.get("IdKategori").toString()),"style='width:200px;'"));
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
				    Hashtable h = (Hashtable) list.get(0);			    
				    myLog.info("DataPenHamilik : else kemaskini ::" + h);

				    this.context.put("selectKementerian",getKementerian(Long.parseLong((String)h.get("IdKementerian"))));
				    this.context.put("selectAgensi",getAgensi(Long.parseLong((String)h.get("IdAgensi"))));
				    this.context.put("selectNegeri",getNegeri((String)h.get("IdNegeri")));				    
				    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong(h.get("IdDaerah").toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
				    this.context.put("selectMukim",HTML.SelectMukimByDaerah(h.get("IdDaerah").toString(), "socMukim", Long.parseLong(h.get("IdMukim").toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));				    
				    //this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong(h.get("IdJenishakmilik").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
				    if(Integer.parseInt(String.valueOf(h.get("IdNegeri")))==13 || Integer.parseInt(String.valueOf(h.get("IdNegeri")))==12){
						this.context.put("selectJenisHakmilik",UtilHTML.SelectJenisHakmilikMengikutNegeri("socJenisHakmilik",String.valueOf(h.get("IdNegeri")),idJenisHakmilik," disabled=\"disabled\" class=\"disabled\"  style='width:200px;'"));
				    
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

			}else{
				//log.info("GadaianProcess::DataPenHamilik:: list.size() = "+list.size());
				list.clear();
				int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
				FrmGadaianPenHamilikData.setPenHakmilikBaru(idPermohonan);
				list = FrmGadaianPenHamilikData.getPenHakmilikBaru();
				myLog.info("GadaianProcess::DataPenHamilik:: list = "+list);
				this.context.put("PenHamilik", list);
			    Hashtable h = (Hashtable) list.get(0);
			    
			    //test fir
			    String tajuk = h.get("TajukFail").toString();
			    myLog.info("test12 : " + tajuk);
			    String SOC = "";
			    if(tajuk.contains("SATISFACTION OF CHARGE")){
			    	SOC = "SOC";
			    }
			    
			    this.context.put("SOC", SOC);
			    this.context.put("TarikhLepasGadai", "");
			    this.context.put("selectKementerian",getKementerian(Long.parseLong(h.get("IdKementerian").toString())));
			    this.context.put("selectAgensi",getAgensi(Long.parseLong(h.get("IdAgensi").toString())));
			    this.context.put("selectNegeri",getNegeri(h.get("IdNegeri").toString()));			    
			    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong("0"), null, " onchange=\"doChangeDaerah()\" class=\"mediumselect\" "));
			    this.context.put("selectMukim",HTML.SelectMukimByDaerah("", "socMukim", " class=\"mediumselect\" "));
			    
//			    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik"));
			    if(Integer.parseInt(String.valueOf(h.get("IdNegeri")))==13 || Integer.parseInt(String.valueOf(h.get("IdNegeri")))==12){
					this.context.put("selectJenisHakmilik",UtilHTML.SelectJenisHakmilikMengikutNegeri("socJenisHakmilik",String.valueOf(h.get("IdNegeri")),0L," style='width:200px;'"));
			    
			    }else{
			    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong("0"), "style='width:200px;'"));   
			    }
			    this.context.put("selectLot",HTML.SelectLot("socLot", Long.parseLong("0"), "style='width:200px;'"));
			    this.context.put("selectRizab",HTML.SelectRizab("socLuas", Long.parseLong("0"), "style='width:200px;'"));
			    this.context.put("selectKategori",HTML.SelectKategori("socKategori", Long.parseLong("0"), "style='width:200px;'"));
			    this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			    this.context.put("pagemode", "baru");
			    this.context.put("classDis", "");
			}
		}
		catch(Exception ex){
//			log.error("GadaianProcess::DataPenHamilik::Exception = "+ex);
			ex.printStackTrace();
		}
		
	}
	
	private void doChangeDaerah(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			Long selectedValue  = Long.parseLong(getParam("socDaerah"));
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			String daerah = getParam("socDaerah");
			String negeri = null;
			
			//implement kat sini capture data from field
			
			
			FrmGadaianPenHamilikData.setPenHakmilikBaru(idPermohonan);
			list = FrmGadaianPenHamilikData.getPenHakmilikBaru();
			////log.info("GadaianProcess::DataPenHamilik:: list = "+list);
			this.context.put("PenHamilik", list);
		    Hashtable h = (Hashtable) list.get(0);
		    
		    negeri = (String)h.get("IdNegeri");
		    String agensi = (String)h.get("IdAgensi");
		    
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
		    this.context.put("selectNegeri",getNegeri(negeri));
		    
		    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(negeri, "socDaerah", selectedValue, "", " onchange=\"doChangeDaerah()\" class=\"mediumselect\" "));
		    this.context.put("selectMukim",HTML.SelectMukimByDaerah(daerah, "socMukim", " class=\"mediumselect\" "));
		    
		    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong("0"), "style='width:190px;'"));
		    this.context.put("selectLot",HTML.SelectLot("socLot", Long.parseLong("0"), "style='width:190px;'"));
		    this.context.put("selectLuas",HTML.SelectLuas("socLuas", Long.parseLong("0"), "style='width:190px;'"));
		    this.context.put("selectRizab",HTML.SelectRizab("socRizab", Long.parseLong("0"), "style='width:190px;'"));
		    this.context.put("selectKategori",HTML.SelectKategori("socKategori", Long.parseLong("0"), "style='width:190px;'"));
		    this.context.put("modeSoc", "");
		    this.context.put("mode", "");
		    
		    this.context.put("pagemode", "baru");
			
		}
		catch(Exception ex){
			myLog.error("GadaianProcess::DataPenHamilik::doChangeDaerah::Exception = "+ex);
		}
	}
	
	private void SimpanPenHamilik(HttpSession session) throws Exception
	{
		String userID = (String)session.getAttribute("_ekptg_user_id");
		try{
			if(getParam("idHakmilikurusan") == ""){
				
				String socLuas = getParam("socLuas");
				//fir add 19022010
				String idFail = getParam("idFail");
				
				//fail baru
				Hashtable h = new Hashtable();
				//fir add 19022010
				h.put("idFail", idFail);
				
				h.put("idPermohonan", getParam("idPermohonan"));
				h.put("idNegeri", getParam("idNegeri"));
				h.put("socDaerah", getParam("socDaerah"));
				h.put("socMukim", getParam("socMukim"));
				h.put("socJenisHakmilik", getParam("socJenisHakmilik"));
				h.put("NoHakmilik", getParam("txtNoHakmilik"));
				h.put("TarikhTerima", getParam("txdTarikhTerima"));
				h.put("NoLot", getParam("txtNoLot"));
				h.put("socLot", getParam("socLot"));
				
				
				h.put("CukaiPertama", getParam("txtCukaiPertama"));
				h.put("Lokasi", getParam("txtLokasi"));
				
				
				
				
				if(socLuas.equalsIgnoreCase("") && socLuas.equalsIgnoreCase(null)){
					socLuas = "0";
				}
				
				String noPelan = "";
				if(getParam("txtNoPelan") != null && getParam("txtNoPelan") != ""){
					noPelan = getParam("txtNoPelan");
				}
				
				String noRizab = "";
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
				
				String luas = "";
				if(getParam("txtLuas") != null && getParam("txtLuas") != ""){
					luas = getParam("txtLuas");
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
				h.put("TarikhWarta", tarikhWarta);
				h.put("NoWarta", noWarta);
				h.put("NoPU", noPU);
				h.put("NoSyit", noSyit);
				h.put("Sekatan", sekatan);
				h.put("tarikhLepasGadai", tarikhLepasGadai);
				h.put("userID", userID);
				myLog.info("SimpanPenHamilik : " + h);
				
				FrmGadaianPenHamilikData.simpan(h);
				result = "baru";
			}
			else{
				String tarikhLepasGadai = getParam("txdTarikhLepasGadai");
				
				//kemaskini fail
				Hashtable h = new Hashtable();
				h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
				h.put("idHakmilikurusan", Integer.parseInt(getParam("idHakmilikurusan")));
				h.put("socDaerah", getParam("socDaerah"));
				h.put("socMukim", getParam("socMukim"));
				h.put("socJenisHakmilik", getParam("socJenisHakmilik"));
				h.put("NoHakmilik", getParam("txtNoHakmilik"));
				h.put("NoLot", getParam("txtNoLot"));
				h.put("socLot", getParam("socLot"));
				h.put("TarikhTerima", getParam("txdTarikhTerima"));
				h.put("CukaiPertama", getParam("txtCukaiPertama"));
				h.put("Lokasi", getParam("txtLokasi"));
				h.put("socLuas", getParam("socLuas"));
				h.put("NoPelan", getParam("txtNoPelan"));
				h.put("socRizab", getParam("socRizab"));
				h.put("socKategori", getParam("socKategori"));
				h.put("Syarat", getParam("txtSyarat"));
				h.put("TarikhLuput", getParam("txdTarikhLuput"));
				h.put("CukaiSemasa", getParam("txtCukaiSemasa"));
				h.put("Luas", getParam("txtLuas"));
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
		}
		catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
	}
	
	//*** Gadaian Hakmilik Controller
	private void ListHakmilik(HttpSession session) throws Exception{
		try{
			FrmGadaianHakmilikData.setListHakmilik(getParam("idHakmilikurusan"));
		
		}catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
	}
	
	private void DataHakmilik(HttpSession session, String disability, String readability, String style1, String style2) throws Exception {
		Vector list = new Vector();
		list.clear();
		String negeri = null;
		  
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
			Vector temp = new Vector();
			Hashtable hashTemp = new Hashtable();
			list = FrmGadaianHakmilikData.getListHakmilik();
			myLog.info("DataHakmilik()::"+list+",mode="+mode);			
			if(list.size() != 0){
				
				if(this.mode.equalsIgnoreCase("kemaskiniHakmilik")){
					
					if(this.doChange.equalsIgnoreCase("doChangeNegeriPeminjam")){
						
						hashTemp = (Hashtable)list.get(0);						
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
						
						this.context.put("Hakmilik", list);
						this.context.put("selectANegeri",UtilHTML.SelectNegeri("socANegeri", Long.parseLong(negeri), "", "onchange=\"doChangeNegeriPeminjamKemaskini()\""));
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
						this.context.put("Hakmilik", list);
						Hashtable hak = (Hashtable) list.get(0);			
						this.context.put("selectANegeri",UtilHTML.SelectNegeri("socANegeri",Long.parseLong(hak.get("IdNegeri").toString())," onchange=\"doChangeNegeriPeminjamKemaskini()\""));
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
					Hashtable hak = (Hashtable) list.get(0);			
					this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri",Long.parseLong(hak.get("IdNegeri").toString()),"disabled=\"disabled\" class=\"disabled\" "));
					//this.context.put("selectADaerah",HTML.SelectDaerah("socADaerah",Long.parseLong(hak.get("IdDaerah").toString()),"disabled=\"disabled\" class=\"disabled\" "));
					this.context.put("selectADaerah",HTML.SelectBandarByNegeri(hak.get("IdNegeri").toString(), "socADaerah",Long.parseLong(hak.get("idBandar").toString())," disabled=\"disabled\" class=\"disabled\" "));
					this.context.put("modeSoc", disability);
				    this.context.put("mode", readability);
				    this.context.put("Style1", style1);
				    this.context.put("Style2", style2);
				    String classDis = "class=\"disabled\" ";
				    this.context.put("classDis", classDis);
				    this.context.put("pagemode", "simpan");
				    this.context.put("txdSuratGadaian", hak.get("tarikhDaftar"));
				    this.context.put("txtJilid", hak.get("jilid"));
				    this.context.put("txtFolio", hak.get("folio"));
				    
					
				}
 
			}else{	
				if(this.doChange.equalsIgnoreCase("doChangeNegeriPeminjam")){
					negeri = getParam("socANegeri");
					myLog.info("doChange : " + doChange+",negeri : " + negeri);
					
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
					//this.context.put("selectADaerah",HTML.SelectDaerahByNegeri(negeri, "socADaerah"));
					this.context.put("selectADaerah",HTML.SelectBandarByNegeri(negeri, "socADaerah"));
					this.context.put("modeSoc", "");
				    this.context.put("mode", "");
				    style1 = "none";
				    this.context.put("Style1", style1);
				    this.context.put("Style2", "");
				    this.context.put("pagemode", "baru");
				    this.context.put("classDis", "");
					
				}else{
					this.context.put("selectANegeri",UtilHTML.SelectNegeri("socANegeri",0L,"onchange=\"doChangeNegeriPeminjam()\" "));
					//this.context.put("selectADaerah",HTML.SelectDaerahByNegeri("0", "socADaerah"));
					this.context.put("selectADaerah",HTML.SelectBandarByNegeri(negeri, "socADaerah"));
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
	
	private void SimpanHakmilik(HttpSession session) throws Exception{
		try{
			if(getParam("idPihakberkepentingan") == "" || getParam("idBebanan") == ""){
				//fail baru
				Hashtable h = new Hashtable();
				h.put("idHakmilikurusan", Integer.parseInt(getParam("idHakmilikurusan")));
				h.put("nama", getParam("txtNama"));
				h.put("alamat1", getParam("txtAlamat1"));
				h.put("alamat2", getParam("txtAlamat2"));
				h.put("alamat3", getParam("txtAlamat3"));
				h.put("poskod", getParam("txtPoskod"));
				h.put("idDaerah", Integer.parseInt(getParam("socADaerah")));
				h.put("idBandar", Integer.parseInt(getParam("socADaerah")));
				h.put("idNegeri", Integer.parseInt(getParam("socANegeri")));
				h.put("noTelefon", getParam("txtNoTelefon"));
				h.put("noFax", getParam("txtNoFax"));
				//Bebanan
				h.put("noPerserahan", getParam("txtNoPerserahan"));
				h.put("suratGadaian", getParam("txdsuratgadaian"));
				h.put("jilid", getParam("txtjilid"));
			    h.put("folio", getParam("txtfolio"));
				FrmGadaianHakmilikData.simpanPelepasan(h);
				result = "baru";
			
			}else{
				//kemaskini fail
				Hashtable h = new Hashtable();
				h.put("idPihakberkepentingan", Integer.parseInt(getParam("idPihakberkepentingan")));
				h.put("nama", getParam("txtNama"));
				h.put("alamat1", getParam("txtAlamat1"));
				h.put("alamat2", getParam("txtAlamat2"));
				h.put("alamat3", getParam("txtAlamat3"));
				h.put("poskod", getParam("txtPoskod"));
				h.put("idDaerah", Integer.parseInt(getParam("socADaerah")));
				h.put("idBandar", Integer.parseInt(getParam("socADaerah")));
				h.put("idNegeri", Integer.parseInt(getParam("socANegeri")));
				h.put("noTelefon", getParam("txtNoTelefon"));
				h.put("noFax", getParam("txtNoFax"));
				h.put("idBebanan", Integer.parseInt(getParam("idBebanan")));
				
				//Bebanan
				h.put("noPerserahan", getParam("txtNoPerserahan"));
				h.put("suratGadaian", getParam("txdsuratgadaian"));
				h.put("jilid", getParam("txtjilid"));
			    h.put("folio", getParam("txtfolio"));
				myLog.info("simpanhakmilik : kemaskini : " + h);
				
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
			myLog.info("DataPeguam:: "+list);
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
						this.context.put("selectBNegeri",HTML.SelectNegeri("socBNegeri",Long.parseLong(negeri),"onchange=\"doChangeNegeriPeguamKemaskini()\""));
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
						////log.info("GadaianProcess::DataPeguam::list "+list);	
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
					////log.info("GadaianProcess::DataPeguam::list "+list);	
					this.context.put("selectBNegeri",UtilHTML.SelectNegeri("socBNegeri",Long.parseLong(peg.get("IdNegeri").toString()),"disabled=\"disabled\" class=\"disabled\" "));
					//this.context.put("selectBDaerah",HTML.SelectDaerah("socBDaerah",Long.parseLong(peg.get("IdDaerah").toString()),"disabled=\"disabled\" class=\"disabled\" "));
					this.context.put("selectBDaerah",HTML.SelectBandarByNegeri(peg.get("IdNegeri").toString(), "socBDaerah",Long.parseLong(peg.get("idBandar").toString())," disabled=\"disabled\" class=\"disabled\" "));
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
					
					
					this.context.put("selectBNegeri",UtilHTML.SelectNegeri("socBNegeri", Long.parseLong(negeri), "onchange=\"doChangeNegeriPeguam()\"",null ));
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
					
					this.context.put("selectBNegeri",UtilHTML.SelectNegeri("socBNegeri","onchange=\"doChangeNegeriPeguam()\"",null ));
					//this.context.put("selectBDaerah",HTML.SelectDaerahByNegeri("0", "socBDaerah"));					
					this.context.put("selectBDaerah",HTML.SelectBandarByNegeri(negeri, "socBDaerah"));
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
	
	private void SimpanPeguam(HttpSession session) throws Exception
	{
		try{
			
			if(getParam("idPeguam") == ""){
				//fail baru
				Hashtable h = new Hashtable();
				h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
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
				h.put("idPeguam", Integer.parseInt(getParam("idPeguam")));
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
			sb.append("<option value=\"\">SILA PILIH URUSAN </option>\n");
			Vector v = DB.getSubUrusan();
			Tblrujsuburusan f = null;
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(Long.parseLong("45")) || f.getIdSuburusan().equals(Long.parseLong("46")) || f.getIdSuburusan().equals(Long.parseLong("47")) ) {
					sb.append("<option value= " + f.getIdSuburusan() + ">"
							+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
							+ "</option>\n");
				} 

			}
			sb.append("</select>");
			
		}catch(Exception e){
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
			sb.append("<option value=\"\">SILA PILIH URUSAN</option>\n");
			Vector v = DB.getSubUrusan();
			Tblrujsuburusan f = null;
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(Long.parseLong("45")) || f.getIdSuburusan().equals(Long.parseLong("46")) || f.getIdSuburusan().equals(Long.parseLong("47")) ) {
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
			sb.append("<option value=\"\">SILA PILIH URUSAN </option>\n");
			Vector v = DB.getSubUrusan();
			Tblrujsuburusan f = null;
			String selected = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(Long.parseLong("45")) || f.getIdSuburusan().equals(Long.parseLong("46")) || f.getIdSuburusan().equals(Long.parseLong("47")) ) {
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
			
		} catch(Exception e){
			myLog.error("Error : " + e.getMessage());
		}
		
		return sb.toString();
	}

	
	//fir 06022010
	public String getAgensiDanKementerianForGadaian(String selectName ){
		StringBuffer sb = new StringBuffer();		
		try{			
			sb.append("<select name='" + selectName + "'> ");
			sb.append("<option value=\"\">SILA PILIH AGENSI</option>\n");
			sb.append("</select>");
			
		} catch(Exception e){
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
			sb.append("<option value=\"\">SILA PILIH AGENSI</option>\n");
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
//						log.info("getagensi : " + a.getIdKementerian() + "::" + k.getIdKementerian());
						if(k.getIdKementerian().equals(selectedValue)){
//							log.info("getagensi selectvalue : " + k.getIdKementerian() + ":: " + selectedValue );
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
			
		}catch(Exception e){
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
			sb.append("<option value=\"\">SILA PILIH AGENSI </option>\n");
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
			//log.info("urusan : " + urusan);
			while (it.hasNext()){
				a = it.next();
				for(int x = 0; x < ve.size(); x++){
					k = (Tblrujkementerian)ve.get(x);
					if (a.getIdKementerian().equals(k.getIdKementerian())){
						//log.info("agensi id kem : " + a.getIdKementerian());
						//log.info("kem id kem : " + k.getIdKementerian());
							sb.append("<option value= " + k.getIdKementerian() + ">"
									+ a.getKodAgensi() + " - " + a.getNamaAgensi() + " ( " 
									+ k.getNamaKementerian() + " ) </option>\n");
					}
				}
			}
			sb.append("</select>");
			
		}catch(Exception e){
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
		String sql = "SELECT id_suburusanstatus,id_suburusan,id_agensi FROM "
				+ " TBLHTPRUJSUBURUSANAGENSI ORDER BY id_suburusanstatus";
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

		}catch(Exception e){
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
			sb.append("<option value=\"\">SILA PILIH TAJUK </option>\n");
			sb.append("</select>");		
		
		}catch(Exception e){
			e.printStackTrace();
		}	
		return sb.toString();
	
	}
	
	public String getTajukForGadaian(String selectName, String urusan , String jsFunction){
		StringBuffer sb = new StringBuffer();		
		try{
			//TAJUK BAGI PINJAMAN PERUMAHAN PERBENDAHARAAN (id 38)
			String tajukPP1 = "discharge of charge ".toUpperCase();
//			String tajukPP2 = "memorandum of charge ".toUpperCase(); 
			String tajukPP3 = "satisfaction of charge ".toUpperCase();
			
			//TAJUK BAGI PINJAMAN TMP & SKM (id 40 )
//			String skm1 = "gadaian/cagaran bagi pembiayaan/pinjaman tabung modal pusingan jpk ".toUpperCase();
//			String skm1 = "menandatangani borang gadaian 16a ".toUpperCase();
			String skm2 = "pelepasan gadaian 16n ".toUpperCase();
			
			//TAJUK BAGI PINJAMAN KOS RENDAH JABATAN PERUMAHAN NEGERA (KPKT) (id 39)
//			String kpkt1 = "menandatangani borang gadaian 16a ".toUpperCase();
			String kpkt2 = "pelepasan gadaian 16n ".toUpperCase();
			
			//TAJUK BAGI PELETAKHAKAN HAK GADAIAN (id 41)
//			String gadaian1 = "menandatangani borang gadaian 16a ".toUpperCase();
			String gadaian2 = "pelepasan gadaian 16n ".toUpperCase();
			
			
			
			sb.append("<select name='" + selectName + "'");
			if(jsFunction != null){
				sb.append(jsFunction);
			}
			sb.append(" >\n ");
			sb.append("<option value=\"\">SILA PILIH TAJUK </option>\n");
			
			if(urusan.equalsIgnoreCase("45")){
				sb.append("<option value=\""+ tajukPP1 + "\" >" + tajukPP1 + "  </option>\n");
//				sb.append("<option value=\""+ tajukPP2 + "\" >" + tajukPP2 + "  </option>\n");
				sb.append("<option value=\""+ tajukPP3 + "\" >" + tajukPP3 + "  </option>\n");
				
			}
			if(urusan.equalsIgnoreCase("46")){
//				sb.append("<option value=\""+ kpkt1 + "\" >" + kpkt1 + "  </option>\n");
				sb.append("<option value=\""+ kpkt2 + "\" >" + kpkt2 + "  </option>\n");
				
			}
			if (urusan.equalsIgnoreCase("47")){
//				sb.append("<option value=\""+ skm1 + "\" >" + skm1 + "  </option>\n");
				sb.append("<option value=\""+ skm2 + "\" >" + skm2 + "  </option>\n");
				
			}
//			if (urusan.equalsIgnoreCase("41")){
////				sb.append("<option value=\""+ gadaian1 + "\" >" + gadaian1 + "  </option>\n");
//				sb.append("<option value=\""+ gadaian2 + "\" >" + gadaian2 + "  </option>\n");
//				
//			}
					
			sb.append("</select>");		
		
		}catch(Exception e){
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
		
		}catch(Exception e){
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
			String userID = session.getAttribute("_ekptg_user_id").toString();
			Hashtable hashGeran = new Hashtable();			
			hashGeran.put("TarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
			hashGeran.put("keterangan",  getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
			hashGeran.put("idPermohonan",  getParam("idPermohonan") == null ? "" : getParam("idPermohonan"));
			hashGeran.put("userID", userID);			
			idHTPGadaian = FrmGadaianHakmilikData.SimpanGeran(hashGeran);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void GeranUpdateSimpan(HttpSession session)throws Exception{		
		try{
			String userID = session.getAttribute("_ekptg_user_id").toString();
			Hashtable hashGeran = new Hashtable();			
			hashGeran.put("TarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
			hashGeran.put("keterangan",  getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
			hashGeran.put("idPermohonan",  getParam("idPermohonan") == null ? "" : getParam("idPermohonan"));
			hashGeran.put("idHTPGadaian",  getParam("idHTPGadaian") == null ? "" : getParam("idHTPGadaian"));
			hashGeran.put("userID", userID);
			FrmGadaianHakmilikData.SimpanUpdateGeran(hashGeran);
			
		}catch(Exception e){
			e.printStackTrace();
		
		}
		
	}

	public void doListing(HttpSession session,String action,String submit,String mode,Vector v) throws Exception {		
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

		
		private void kemaskiniSimpanStatusSelesai(String idFail,String idPermohonan,String idSubUrusan,String langkah)throws Exception {
			 try {
				subUrusanStatusFail = new Tblrujsuburusanstatusfail();
				subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
				subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
				subUrusanStatusFail.setAktif("0");
			
				Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
				long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,idSubUrusan,"=");
				subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
				subUrusanStatusFailN.setAktif("1");
				subUrusanStatusFailN.setUrl(getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
				subUrusanStatusFailN.setIdMasuk(Long.parseLong(idUser));
				myLog.info("getParam(\"txdTarikhTerima\"):"+getParam("txdTarikhTerima"));
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
	
}//close class
