package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.entities.Tblrujagensi;
import ekptg.model.entities.Tblrujkementerian;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.htp.FrmGadaianHakmilikData;
import ekptg.model.htp.FrmGadaianInfoData;
import ekptg.model.htp.FrmGadaianPeguamData;
import ekptg.model.htp.FrmGadaianPenHamilikData;
import ekptg.model.htp.FrmGadaianSemakan1Data;
import ekptg.model.htp.FrmGadaianSemakanData;
import ekptg.model.htp.FrmGadaianSenaraiPermohonanData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailGadaianData;



public class XFrmPenswastaan extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FrmGadaianA.class);
	private String result = new String();
	private Date now = new Date();
    private SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
    
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
    	//log.info("GadaianProcess::");
    	String vm = "";
    	String disability = "";
    	String readability = "";
    	String style1 = "";
    	String style2 = "";
    	Vector list = new Vector();
    	list.clear();
		String submit = getParam("command");
		String mode = getParam("mode");
		String selectedTab = getParam("tabId");
		
		log.info("command : " + submit);
		log.info("mode : " + mode);
		
		
		try{
			if("FailBaru".equals(submit)){
				
				//fir
				/*********************************************
				 * template : app/htp/frmGadaianSemakan2Ajax.jsp
				 * *******************************************/
				
				vm = "app/htp/frmGadaianSemakan2Ajax.jsp";
				
				String idFail = "0";

				if("view".equals(mode)){
					
					readability = "readonly";
				    disability = "disabled";
				    idFail = getParam("idFail");
				    setFailBaru(session,idFail);
				    style2 = "none";
					ViewFailBaru(session,disability,readability,style1,style2);
				}
				else if("kemaskini".equals(mode)){
					idFail = getParam("idFail");
				    setFailBaru(session,idFail);
				    style1 = "none";
					ViewFailBaru(session,disability,readability,style1,style2);
				}
				else if("simpan".equals(mode)){
					readability = "readonly";
				    disability = "disabled";
				    idFail = SimpanFailBaru(session);
					setFailBaru(session,idFail);
					style2 = "none";
					ViewFailBaru(session,disability,readability,style1,style2);
					this.context.put("SimpanStatus", "success");
					this.context.put("ResultSimpan", result);
				}

				else{
					
					style1 = "none";	
					DataFailBaru(session, disability, readability, style1, style2);

				}
				//log.info("GadaianProcess::FailBaru");
			}
			else if("SenaraiPermohonan".equals(submit)){
				
				/*********************************************
				 * template : app/htp/frmGadaianSenaraiPermohonanAjax.jsp
				 * *******************************************/
				
				vm = "app/htp/frmGadaianSenaraiPermohonanAjax.jsp";
				int idFail = Integer.parseInt(getParam("idFail"));
				String carian = getParam("NamaPemohon");
				ListPermohonan(session, idFail, carian);
				list = FrmGadaianSenaraiPermohonanData.getListPermohonan();
			    this.context.put("SenaraiPermohonan", list);
			    this.context.put("NoFail", getParam("noFail"));
			    this.context.put("IdFail", getParam("idFail"));
			    this.context.put("carian", getParam("NamaPemohon"));
				//log.info("GadaianProcess::SenaraiPermohonan");
			}
			else if("Semakan".equals(submit)){
				
				/*********************************************
				 * template : app/htp/frmGadaianSemakanAjax.jsp
				 * *******************************************/
				
				vm = "app/htp/frmGadaianSemakanAjax.jsp";
				int idPermohonan = 0;
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
				}
				else if("kemaskini".equals(mode)){
					style1 = "none";
					ListSemak(session,idPermohonan);
				    DataSemak(session,disability,readability,style1,style2);
				}
				else if("simpan".equals(mode)){
					//fir
					vm = "app/htp/frmGadaianSemakanAjax.jsp";
					readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
					idPermohonan = SimpanSemak(session);
					
					log.info("Semakan::simpan::idpermohonan: " + idPermohonan );
					
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
					
				}
				else if("hapus".equals(mode)){
					
				}
				else{
					
				    readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
				    
				    ListSemak(session,idPermohonan);
				    DataSemak(session,disability,readability,style1,style2);
				}
			    //log.info("GadaianProcess::SEMAKAN");
			}
			else if("PenHakmilik".equals(submit)){
				
				/*********************************************
				 * template : app/htp/frmGadaianPenHamilikAjax.jsp
				 * *******************************************/
				
				vm = "app/htp/frmGadaianPenHamilikAjax.jsp";			
			    this.context.put("NoFail", getParam("noFail"));
			    this.context.put("IdFail", getParam("idFail"));
			    this.context.put("IdPermohonan", getParam("idPermohonan"));
			    
			    if("baru".equals(mode)){
			    	
			    }
			    else if("kemaskini".equals(mode)){
			    	style1 = "none";
				    ListPenHamilik(session);
				    DataPenHamilik(session, disability, readability,style1,style2);
			    }
			    else if("simpan".equals(mode)){
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
			    	SimpanPenHamilik(session);
			    	ListPenHamilik(session);
				    DataPenHamilik(session, disability, readability,style1,style2);
				    this.context.put("SimpanStatus", "success");
					this.context.put("ResultSimpan", result);
			    }
			    else if("hapus".equals(mode)){
			    	
			    }
			    else if("doChangeDaerah".equalsIgnoreCase(mode)){
			    	
			    	readability = "readonly";
				    disability = "disabled";
			    	doChangeDaerah(session,disability, readability, style1,style2);
			    }
			    else{
				    readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
				    ListPenHamilik(session);
				    DataPenHamilik(session, disability, readability,style1,style2);
			    }		    
			    //log.info("GadaianProcess::PenHakmilik");
			}
			else if ("Hakmilik".equals(submit)){
				
				/*********************************************
				 * template : app/htp/frmGadaianHakmilikAjax.jsp
				 * *******************************************/
				
				vm = "app/htp/frmGadaianHakmilikAjax.jsp";
				String idPermohonan = getParam("idPermohonan");
				this.context.put("IdPermohonan", Integer.parseInt(idPermohonan));
			    this.context.put("NoFail", getParam("noFail"));
			    this.context.put("IdFail", getParam("idFail"));
			    this.context.put("IdHakmilikurusan", getParam("idHakmilikurusan"));
			    
				list = FrmGadaianInfoData.getSemak(idPermohonan);
				this.context.put("Info", list);
				Hashtable h = (Hashtable) list.get(0);
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()),"disabled"));
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()),"disabled"));
				this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("idAgensi").toString()),"disabled"));
				this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()),"disabled"));			
				
			    if("simpanHakmilik".equals(mode)){
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
			    	SimpanHakmilik(session);
			    	ListHakmilik(session);
			    	DataHakmilik(session,disability,readability,style1,style2);
			    	this.context.put("SimpanStatus", "success");
					this.context.put("ResultSimpan", result);
			    }
			    
			    else if("kemaskiniHakmilik".equals(mode)){
			    	style1 = "none";
			    	ListHakmilik(session);
			    	DataHakmilik(session,disability,readability,style1,style2);
		    	
			    }
			    else if("hapusHakmilik".equals(mode)){
			    	
			    }
			    else if("simpanPeguam".equals(mode)){
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
			    	SimpanPeguam(session);
			    	ListPeguam(session);
			    	DataPeguam(session,disability,readability,style1,style2);				
					this.context.put("SimpanStatus", "success");
					this.context.put("ResultSimpan", result);
			    }
			    else if("kemaskiniPeguam".equals(mode)){
			    	style1 = "none";
			    	ListPeguam(session);
			    	DataPeguam(session,disability,readability,style1,style2);					    	
			    }
			    else if("hapusPeguam".equals(mode)){
			    	
			    }
			    else if("hakmilikview".equals(mode)){				
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
					ListHakmilik(session);
					DataHakmilik(session,disability,readability,style1,style2);
			    }
			    else if("peguamview".equals(mode)){
			    	readability = "readonly";
				    disability = "disabled";
				    style2 = "none";
					ListPeguam(session);
					DataPeguam(session,disability,readability,style1,style2);
			    }
			    
				if (selectedTab == null || "".equals(selectedTab)) {
					selectedTab = "0";
				}
				this.context.put("selectedTab", selectedTab);
			    
				//log.info("GadaianProcess::Hakmilik");
			}

			else{
				
				/*********************************************
				 * template : app/htp/frmSenaraiFailGadaianAjax.jsp
				 * *******************************************/
				
				vm = "app/htp/frmSenaraiFailGadaianAjax.jsp";
				String key_cari = getParam("NamaFail");
				String keyNo_cari = getParam("NoFail");
				String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
				Long idNegeri = Long.parseLong(Negeri);
				ListFail(session, key_cari, keyNo_cari, idNegeri);
		    	list = FrmSenaraiFailGadaianData.getList();
		    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,disability));
			    this.context.put("Senaraifail", list);
			    this.context.put("carian", getParam("NamaFail"));
			    this.context.put("carianNoFail", getParam("NoFail"));
			    this.context.put("Negeri", idNegeri);
				//log.info("GadaianProcess::SenaraiFail");
			    
			    if ("cancel".equalsIgnoreCase(mode)){
			    	this.context.put("clear", "clear");
			    }
			}
						
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return vm;
		
    }//close do template
	
	//*** Fail Baru Controller
	private void DataFailBaru(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		try{
			
			this.context.put("Semak", "");
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));
		    this.context.put("selectAgensi",getAgensiDanKementerianForGadaian("socAgensi"));
		    
		    this.context.put("selectSuburusan",getSubUrusanForGadaian("socSuburusan"));
		    this.context.put("datenow", formatter.format(now));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}
		catch(Exception ex){
			log.info("GadaianProcess::DataFailBaru::Exception = "+ex.getMessage());
		}
	}
	
	public void setFailBaru(HttpSession session, String idFail) throws Exception{
		try{
			FrmGadaianSemakan1Data.setSemak(idFail);			
		}catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
	}
	
	private void ViewFailBaru(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmGadaianSemakan1Data.getSemak();
		    this.context.put("Semak", list);
		    Hashtable h = (Hashtable) list.get(0);	
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()), "disabled"));
		    		    
		    //fir
		    this.context.put("socKementerian", h.get("idKementerian"));
		    
		    this.context.put("selectAgensi",getAgensiDanKementerianForGadaian("socAgensi", Long.parseLong(h.get("idKementerian").toString()), "disabled"));
		    
		    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()), "disabled"));
		    
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}
		catch(Exception ex){
			log.error("GadaianProcess::ListFailBaru::Exception = "+ex.getMessage());
		}
	}
	
	private String SimpanFailBaru(HttpSession session) throws Exception{
		String idFail = "0";
		try{
			if(getParam("idFail") == ""){
				//fail baru
				Hashtable h = new Hashtable();			
				h.put("idNegeri", Integer.parseInt(getParam("socNegeri")));
								
				//fir ganti id kementerian ngan cara baru
				h.put("idKementerian", Integer.parseInt(getParam("socAgensi")));
				h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));		
				h.put("Tajuk", getParam("txtTajuk"));
				
				////log.info("GadaianProcess::SimpanSemak:: h = "+h);
				idFail = FrmGadaianSemakan1Data.simpan(h);
				result = "baru";
			}
			else{
				
				//kemaskini fail
				Hashtable h = new Hashtable();
				h.put("idFail", Integer.parseInt(getParam("idFail")));			
				h.put("Tajuk", getParam("txtTajuk"));
				////log.info("GadaianProcess::SimpanSemak:: h = "+h);
				idFail = FrmGadaianSemakan1Data.update(h);
				result = "kemaskini";
			
			}
			
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
		return idFail;
	}
	
	//*** Senarai Fail Gadaian Controller
	public void ListFail(HttpSession session, String key_cari, String keyNo_cari, Long idNegeri) throws Exception
	{
		try{
			FrmSenaraiFailGadaianData.setList(key_cari,keyNo_cari,idNegeri,null,null,null,null,null);
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
	}
	
	//*** Senarai Permohonan Controller
	public void ListPermohonan(HttpSession session, int idFail, String carian) throws Exception
	{
		try{
			//FrmGadaianSenaraiPermohonanData.setListPermohonan(idFail,carian);
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
	}
	
	//*** Gadaian Semakan Controller
	private void ListSemakBaru(HttpSession session) throws Exception{
		try{
			FrmGadaianSemakanData.setSemakBaru(getParam("idFail"));
		}catch(Exception e){
			log.error("Error : " + e.getMessage());
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
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()),"disabled"));
	
		    this.context.put("selectAgensi",getAgensiDanKementerianForGadaian("socAgensi", Long.parseLong(h.get("idKementerian").toString()), "disabled"));
		    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()),"disabled"));
		    this.context.put("datenow", formatter.format(now));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}
		catch(Exception ex){
			log.error("GadaianProcess::DataSemak::Exception = "+ex.getMessage());
		}
	}
	
	private void ListSemak(HttpSession session, int id) throws Exception{
		try{
//			int idPermohonan = 0;
//			if (getParam("idPermohonan") != "")
//				idPermohonan = Integer.parseInt(getParam("idPermohonan"));
//			else if (id != 0)
//				idPermohonan = id;
//			else
//				myLogger.info("GadaianProcess::ListSemak::idPermohonan = 0");
//			FrmGadaianSemakanData.setSemak(idPermohonan);
			
			int idPermohonan = 0;
			if (getParam("idPermohonan") != ""){
				idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			}
			else if (id != 0){
				idPermohonan = id;
			}
			log.info("GadaianProcess::ListSemak::idPermohonan = 0");
			FrmGadaianSemakanData.setSemak(String.valueOf(idPermohonan));
			
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
	}
	
	private void DataSemak(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmGadaianSemakanData.getSemak();
		    this.context.put("Semak", list);
		    Hashtable h = (Hashtable) list.get(0);
		    
		    log.info("DataSemak Hashtable : " + h);
		    
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()),"disabled"));
		    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()),"disabled"));
		    //this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("idAgensi").toString()),disability));
		    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()),"disabled"));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		    
		    //fir test
		    log.info("no fail lain: " + getParam("txtNoFailLain"));
		    
		    this.context.put("noRujukan", getParam("txtNoFailLain"));
		    
		    
		    
		}
		catch(Exception ex){
			////log.info("GadaianProcess::DataSemak::Exception = "+ex);
			ex.printStackTrace();
		}
	}
	
	private int SimpanSemak(HttpSession session) throws Exception
	{
		int idPermohonan = 0;
		try{
			if(getParam("idPermohonan") == ""){
				
				//fail baru
				
				Hashtable h = new Hashtable();
				h.put("IdFail", Integer.parseInt(getParam("idFail")));
				h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
				h.put("Tajuk", getParam("txtTajuk"));
				h.put("socAgensi", getParam("socAgensi"));
				h.put("idSuburusan", Integer.parseInt(getParam("idSuburusan")));
				h.put("NoFailLain", getParam("txtNoFailLain"));
				h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
					
				log.info("GadaianProcess::SimpanSemak::FailBaru:: h = "+h);
				idPermohonan = Integer.parseInt(FrmGadaianSemakanData.simpan(h));
				result = "baru";
			}
			else{
				
				//kemaskini fail
				Hashtable h = new Hashtable();
				h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
				
				//agensi x perlu kemaskini
				
				h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
				h.put("NoFailLain", getParam("txtNoFailLain"));
				h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
				log.info("GadaianProcess::SimpanSemak::KemaskiniFail:: h = "+h);
				idPermohonan = Integer.parseInt(FrmGadaianSemakanData.update(h));
				result = "kemaskini";
				
			}
			//log.info("GadaianProcess::SimpanSemak::Fir IdPermohonan :: " + idPermohonan );
			
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
		
		return idPermohonan;
		
		
	}
	
	//*** Gadaian Pendaftaran Hakmilik Controller
	private void ListPenHamilik(HttpSession session) throws Exception
	{
		try{
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			FrmGadaianPenHamilikData.setPenHakmilik(idPermohonan);
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
	}
	
	private void DataPenHamilik(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmGadaianPenHamilikData.getPenHakmilik();
			if(list.size() != 0){
				this.context.put("PenHamilik", list);
			    Hashtable h = (Hashtable) list.get(0);
			    
			    log.info("fir DataPenHamilik : " + h);
			    
			    
			    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("IdKementerian").toString()),"disabled"));
			    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("IdAgensi").toString()),"disabled"));
			    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("IdNegeri").toString()),"disabled"));
			    
			    //fir
			    
			    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong(h.get("IdDaerah").toString()), "disabled"));
			    this.context.put("selectMukim",HTML.SelectMukimByDaerah(h.get("IdDaerah").toString(), "socMukim", Long.parseLong(h.get("IdMukim").toString()), "disabled"));
			    
			    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong(h.get("IdJenishakmilik").toString()),disability));
			    this.context.put("selectLot",HTML.SelectLot("socLot",Long.parseLong(h.get("IdLot").toString()),disability));
			    this.context.put("selectLuas",HTML.SelectLuas("socLuas",Long.parseLong(h.get("IdLuas").toString()),disability));
			    this.context.put("selectRizab",HTML.SelectRizab("socRizab",Long.parseLong(h.get("IdRizab").toString()),disability));
			    this.context.put("selectKategori",HTML.SelectKategori("socKategori",Long.parseLong(h.get("IdKategori").toString()),disability));
			    this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			}
			else{
				////log.info("GadaianProcess::DataPenHamilik:: list.size() = "+list.size());
				list.clear();
				int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
				FrmGadaianPenHamilikData.setPenHakmilikBaru(idPermohonan);
				list = FrmGadaianPenHamilikData.getPenHakmilikBaru();
				////log.info("GadaianProcess::DataPenHamilik:: list = "+list);
				this.context.put("PenHamilik", list);
			    Hashtable h = (Hashtable) list.get(0);
			    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("IdKementerian").toString()),"disabled"));
			    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("IdAgensi").toString()),"disabled"));
			    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("IdNegeri").toString()),"disabled"));
			    
			    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong("0"), null, "onchange=\"doChangeDaerah()\""));
			    this.context.put("selectMukim",HTML.SelectMukimByDaerah("", "socMukim"));
			    
			    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik"));
			    this.context.put("selectLot",HTML.SelectLot("socLot"));
			    this.context.put("selectLuas",HTML.SelectLuas("socLuas"));
			    this.context.put("selectRizab",HTML.SelectRizab("socRizab"));
			    this.context.put("selectKategori",HTML.SelectKategori("socKategori"));
			    this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			}
		}
		catch(Exception ex){
			log.error("GadaianProcess::DataPenHamilik::Exception = "+ex);
		}
	}
	
	private void doChangeDaerah(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			Long selectedValue  = Long.parseLong(getParam("socDaerah"));
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			
			//implement kat sini capture data from field
			
			
			FrmGadaianPenHamilikData.setPenHakmilikBaru(idPermohonan);
			list = FrmGadaianPenHamilikData.getPenHakmilikBaru();
			////log.info("GadaianProcess::DataPenHamilik:: list = "+list);
			this.context.put("PenHamilik", list);
		    Hashtable h = (Hashtable) list.get(0);
		    
		    log.info("fir hashtable dochangeDaerah : " + h);
		    
		    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("IdKementerian").toString()),"disabled"));
		    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("IdAgensi").toString()),"disabled"));
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("IdNegeri").toString()),"disabled"));
		    
		    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", selectedValue, "", "onchange=\"doChangeDaerah()\""));
		    this.context.put("selectMukim",HTML.SelectMukimByDaerah(getParam("socDaerah"), "socMukim"));
		    //this.context.put("selectMukim",HTML.SelectMukimByDaerah(h.get("IdDaerah").toString(), "socMukim"));
		    
		    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik"));
		    this.context.put("selectLot",HTML.SelectLot("socLot"));
		    this.context.put("selectLuas",HTML.SelectLuas("socLuas"));
		    this.context.put("selectRizab",HTML.SelectRizab("socRizab"));
		    this.context.put("selectKategori",HTML.SelectKategori("socKategori"));
		    this.context.put("modeSoc", "");
		    this.context.put("mode", "");
		    style1 = "none";
		    this.context.put("Style1", style1);
		    this.context.put("Style2", "");
			
		}
		catch(Exception ex){
			log.error("GadaianProcess::DataPenHamilik::doChangeDaerah::Exception = "+ex);
		}
	}
	
	private void SimpanPenHamilik(HttpSession session) throws Exception
	{
		try{
			if(getParam("idHakmilikurusan") == ""){
				//fail baru
				Hashtable h = new Hashtable();
				h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
				h.put("idNegeri", getParam("idNegeri"));
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
				FrmGadaianPenHamilikData.simpan(h);
				result = "baru";
			}
			else{
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
				FrmGadaianPenHamilikData.update(h);
				result = "kemaskini";
			}
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
	}
	
	//*** Gadaian Hakmilik Controller
	private void ListHakmilik(HttpSession session) throws Exception
	{
		try{
			int idHakmilikurusan = Integer.parseInt(getParam("idHakmilikurusan"));
			FrmGadaianHakmilikData.setListHakmilik(""+idHakmilikurusan);
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
	}
	
	private void DataHakmilik(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmGadaianHakmilikData.getListHakmilik();
			////log.info("GadaianProcess::list.size() "+list.size());			
			if(list.size() != 0){			    
				////log.info("GadaianProcess::DataHakmilik::list "+list);
				this.context.put("Hakmilik", list);
				Hashtable hak = (Hashtable) list.get(0);			
				this.context.put("selectADaerah",HTML.SelectDaerah("socADaerah",Long.parseLong(hak.get("IdDaerah").toString()),disability));
				this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri",Long.parseLong(hak.get("IdNegeri").toString()),disability));
				this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			}
			else{
				this.context.put("selectADaerah",HTML.SelectDaerah("socADaerah"));
				this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri"));
				this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			}
		}catch(Exception ex){
			log.info("GadaianProcess::DataHakmilik::Exception = "+ex);
		}
	}
	
	private void SimpanHakmilik(HttpSession session) throws Exception
	{
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
				h.put("idNegeri", Integer.parseInt(getParam("socANegeri")));
				h.put("noTelefon", getParam("txtNoTelefon"));
				h.put("noFax", getParam("txtNoFax"));
				h.put("noPerserahan", getParam("txtNoPerserahan"));
				FrmGadaianHakmilikData.simpan(h);
				result = "baru";
			}
			else{
				//kemaskini fail
				Hashtable h = new Hashtable();
				h.put("idPihakberkepentingan", Integer.parseInt(getParam("idPihakberkepentingan")));
				h.put("nama", getParam("txtNama"));
				h.put("alamat1", getParam("txtAlamat1"));
				h.put("alamat2", getParam("txtAlamat2"));
				h.put("alamat3", getParam("txtAlamat3"));
				h.put("poskod", getParam("txtPoskod"));
				h.put("idDaerah", Integer.parseInt(getParam("socADaerah")));
				h.put("idNegeri", Integer.parseInt(getParam("socANegeri")));
				h.put("noTelefon", getParam("txtNoTelefon"));
				h.put("noFax", getParam("txtNoFax"));
				h.put("idBebanan", Integer.parseInt(getParam("idBebanan")));
				h.put("noPerserahan", getParam("txtNoPerserahan"));
				FrmGadaianHakmilikData.update(h);
				result = "kemaskini";
			}
			
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
				
	}
	
	//*** Gadaian Peguam Controller
	private void ListPeguam(HttpSession session) throws Exception
	{
		try{
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			FrmGadaianPeguamData.setListPeguam(""+idPermohonan);
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
	}
	
	private void DataPeguam(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		
		try{
			list = FrmGadaianPeguamData.getListPeguam();
			////log.info("GadaianProcess::DataPeguam::list.size() "+list.size());
			if(list.size() != 0){		        
				this.context.put("Peguam", list);
				Hashtable peg = (Hashtable) list.get(0);
				////log.info("GadaianProcess::DataPeguam::list "+list);	
				this.context.put("selectBDaerah",HTML.SelectDaerah("socBDaerah",Long.parseLong(peg.get("IdDaerah").toString()),disability));
				this.context.put("selectBNegeri",HTML.SelectNegeri("socBNegeri",Long.parseLong(peg.get("IdNegeri").toString()),disability));
				this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			}
			else{
				this.context.put("selectBDaerah",HTML.SelectDaerah("socBDaerah"));
				this.context.put("selectBNegeri",HTML.SelectNegeri("socBNegeri"));
				this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			}
		}
		catch(Exception ex){
			log.info("GadaianProcess::DataPeguam::Exception = "+ex);
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
				h.put("idNegeri", Integer.parseInt(getParam("socBNegeri")));
				h.put("noTelefon", getParam("txtNoTelefonPeguam"));
				h.put("noFax", getParam("txtNoFaxPeguam"));
				FrmGadaianPeguamData.update(h);
				result = "kemaskini";
			}
			
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
	}
	
	//fir
	public String getSubUrusanForGadaian(String selectName){
		StringBuffer sb = new StringBuffer();
		
		try{
			
			sb.append("<select name='" + selectName + "'> ");
			sb.append("<option value=>Sila Pilih Sub Urusan </option>\n");
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
			log.error("Error : " + e.getMessage());
		}
		
		return sb.toString();
	}
	
	//fir
	public String getSubUrusanForGadaian(String selectName, Long selectedValue, String disabled){
		StringBuffer sb = new StringBuffer();
		
		try{
			
			sb.append("<select name='" + selectName + "'");
			if(disabled != null){
				sb.append(disabled);
			}
			sb.append(" > ");
			sb.append("<option value=>Sila Pilih Sub Urusan </option>\n");
			Vector v = DB.getSubUrusan();
			Tblrujsuburusan f = null;
			String selected = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(Long.parseLong("38")) || f.getIdSuburusan().equals(Long.parseLong("39")) || f.getIdSuburusan().equals(Long.parseLong("40")) || f.getIdSuburusan().equals(Long.parseLong("41"))) {
					if(f.getIdSuburusan().equals(selectedValue)){
						selected = "selected";
					}
					sb.append("<option " + selected + " value= " + f.getIdSuburusan() + ">"
							+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
							+ "</option>\n");
				} 
	
			}
			sb.append("</select>");
			
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
		}
		
		return sb.toString();
	}

	//fir
	public String getAgensiDanKementerianForGadaian(String selectName ){
		StringBuffer sb = new StringBuffer();
		
		try{
			
			sb.append("<select name='" + selectName + "'> ");
			sb.append("<option value=>Sila Pilih Agensi </option>\n");
			Vector v = DB.getAgensi();
			Vector ve = DB.getKementerian();
			Vector tempAgensi = new Vector<Tblrujagensi>();
			Tblrujagensi a = new Tblrujagensi();
			Tblrujkementerian k = new Tblrujkementerian();
			
			
			for (int i = 0; i < v.size(); i++) {
				a = (Tblrujagensi) v.get(i);
				//if (a.getIdAgensi().equals(Long.parseLong("750")) || a.getIdAgensi().equals(Long.parseLong("751"))) {
				if (a.getIdAgensi().equals(Long.parseLong("363")) ||a.getIdAgensi().equals(Long.parseLong("455")) || a.getIdAgensi().equals(Long.parseLong("751"))) {
					tempAgensi.add(a);
				}
			}
			
			Iterator<Tblrujagensi> it = tempAgensi.iterator();
			while (it.hasNext()){
				a = it.next();
				for(int x = 0; x < ve.size(); x++){
					k = (Tblrujkementerian)ve.get(x);
					if (a.getIdKementerian().equals(k.getIdKementerian())){
						sb.append("<option value= " + k.getIdKementerian() + ">"
								+ a.getKodAgensi() + " - " + a.getNamaAgensi() + " ( " 
								+ k.getNamaKementerian() + " ) </option>\n");
					}
				}
			}
			sb.append("</select>");
			
		}
		catch(Exception e){
			log.error("Error : " + e.getMessage());
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
			sb.append("<option value=>Sila Pilih Agensi </option>\n");
			Vector v = DB.getAgensi();
			Vector ve = DB.getKementerian();
			Vector tempAgensi = new Vector<Tblrujagensi>();
			Tblrujagensi a = new Tblrujagensi();
			Tblrujkementerian k = new Tblrujkementerian();
			//363 Bahagian Pinjaman Perumahan
			//455 Jabatan Perumahan Negara (kena update id_kementerian kpd 19)
			//751 koperasi
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
						if(k.getIdKementerian().equals(selectedValue)){
							selected = "selected";
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
			log.error("Error : " + e.getMessage());
		}
		
		return sb.toString();
	}

	
}//close class
