package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmGadaianHakmilikData;
import ekptg.model.htp.FrmGadaianInfoData;
import ekptg.model.htp.FrmGadaianPeguamData;
import ekptg.model.htp.FrmGadaianPenHamilikData;
import ekptg.model.htp.FrmGadaianSemakan1Data;
import ekptg.model.htp.FrmGadaianSemakanData;
import ekptg.model.htp.FrmGadaianSenaraiPermohonanData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailGadaianData;

public class GadaianProcess extends VTemplate {
	private String result = new String();
	public Template doTemplate() throws Exception{
    	HttpSession session = this.request.getSession();
    	System.out.println("GadaianProcess::");
    	String vm = "";
    	String disability = "";
    	String readability = "";
    	String style1 = "";
    	String style2 = "";
    	this.context.put("SimpanStatus", "");
    	Vector list = new Vector();
    	list.clear();
		String submit = getParam("command");
		String mode = getParam("mode");
		if("FailBaru".equals(submit)){
			vm = "app/htp/frmGadaianSemakan1.jsp";
			String idFail = "0";
			
//			this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmGadaianSemakan"));
//		    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
//			this.context.put("semakclass", new FrmSemakan());

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
				
//				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
//	    	    //System.out.println("FrmPajakanKecilSenaraiPermohonan:id::"+id);
//	        	FrmSemakan frmSemak = new FrmSemakan();
//	        	frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
//	        	if(cbsemaks!=null){
//	        		for (int i = 0; i < cbsemaks.length; i++) { 
//	        			frmSemak = new FrmSemakan();
//	        			System.out.println("GadaianProcess:FailBaru::simpan::cbsemaks:::"+cbsemaks[i]);
//	        			frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));           
//	        		}
//	        	}
				
				setFailBaru(session,idFail);
				style2 = "none";
				ViewFailBaru(session,disability,readability,style1,style2);
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);
			}else{
				style1 = "none";
				DataFailBaru(session, disability, readability, style1, style2);
			}
			System.out.println("GadaianProcess::FailBaru");
		}else if("SenaraiPermohonan".equals(submit)){
			vm = "app/htp/frmGadaianSenaraiPermohonan.jsp";
			int idFail = Integer.parseInt(getParam("idFail"));
			String carian = getParam("NamaPemohon");
			ListPermohonan(session, idFail, carian);
			list = FrmGadaianSenaraiPermohonanData.getListPermohonan();
		    this.context.put("SenaraiPermohonan", list);
		    this.context.put("NoFail", getParam("noFail"));
		    this.context.put("IdFail", getParam("idFail"));
		    this.context.put("carian", getParam("NamaPemohon"));
			System.out.println("GadaianProcess::SenaraiPermohonan");
		}else if("Semakan".equals(submit)){
			vm = "app/htp/frmGadaianSemakan.jsp";
			String idPermohonan = "";
			this.context.put("NoFail", getParam("noFail"));
		    this.context.put("IdFail", getParam("idFail"));
		    this.context.put("IdPermohonan", getParam("idPermohonan"));	
		    
		    this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmGadaianSemakan"));
//		    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
		    this.context.put("semakclass", new FrmSemakan());

			if("baru".equals(mode)){
				style1 = "none";
				ListSemakBaru(session);
			    DataSemakBaru(session,disability,readability,style1,style2);
			    System.out.println("GadaianProcess::Semakan::baru");
			}else if("kemaskini".equals(mode)){
				style1 = "none";
				ListSemak(session,idPermohonan);
			    DataSemak(session,disability,readability,style1,style2);
			}else if("simpan".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
				idPermohonan = SimpanSemak(session);
				
				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
	    	    //System.out.println("FrmPajakanKecilSenaraiPermohonan:id::"+id);
	        	FrmSemakan frmSemak = new FrmSemakan();
	        	frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
	        	if(cbsemaks!=null){
	        		for (int i = 0; i < cbsemaks.length; i++) { 
	        			frmSemak = new FrmSemakan();
	        			System.out.println("GadaianProcess:Semakan::simpan::cbsemaks:::"+cbsemaks[i]);
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
		    System.out.println("GadaianProcess::SEMAKAN");
		}else if("PenHakmilik".equals(submit)){
			vm = "app/htp/frmGadaianPenHamilik.jsp";			
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
		    	
		    }else{
			    readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
			    ListPenHamilik(session);
			    DataPenHamilik(session, disability, readability,style1,style2);
		    }		    
		    System.out.println("GadaianProcess::PenHakmilik");
		}else if ("Hakmilik".equals(submit)){
			vm = "app/htp/frmGadaianHakmilik.jsp";
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
		    }else if("kemaskiniHakmilik".equals(mode)){
		    	style1 = "none";
		    	ListHakmilik(session);
		    	DataHakmilik(session,disability,readability,style1,style2);
//				this.context.put("modeSoc", disability);
//			    this.context.put("mode", readability);		    	
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
		    }else if("kemaskiniPeguam".equals(mode)){
		    	style1 = "none";
		    	ListPeguam(session);
		    	DataPeguam(session,disability,readability,style1,style2);				
//				this.context.put("modeSoc", disability);
//			    this.context.put("mode", readability);		    	
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
		    }
		    
		    String selectedTab = new String();
            selectedTab = getParam("tabId").toString();
            if (selectedTab == null || "".equals(selectedTab) ) {
               selectedTab="0";
            }
            this.context.put("selectedTab",selectedTab);
			System.out.println("GadaianProcess::Hakmilik");
		}else{
			vm = "app/htp/frmSenaraiFailGadaian.jsp";
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
			System.out.println("GadaianProcess::SenaraiFail");
		}

        Template template = this.engine.getTemplate(vm);
        return template;
    }
	
	private Date now = new Date();
    private SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
	
	//*** Fail Baru Controller
	private void DataFailBaru(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
//		Date now = new Date();
//	    SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
		try{
			this.context.put("Semak", "");
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));
		    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian"));
		    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi"));
		    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan"));
		    this.context.put("datenow", formatter.format(now));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}catch(Exception ex){
			System.out.println("GadaianProcess::DataFailBaru::Exception = "+ex);
		}
	}
	
	public void setFailBaru(HttpSession session, String idFail) throws Exception{
		FrmGadaianSemakan1Data.setSemak(idFail);
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
		    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()), "disabled"));
//		    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("idAgensi").toString()), disability));
		    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()), "disabled"));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}catch(Exception ex){
			System.out.println("GadaianProcess::ListFailBaru::Exception = "+ex);
		}
	}
	
	private String SimpanFailBaru(HttpSession session) throws Exception{
		String idFail;
		if(getParam("idFail") == ""){
			//fail baru
			Hashtable h = new Hashtable();			
			h.put("idNegeri", Integer.parseInt(getParam("socNegeri")));
			h.put("idKementerian", Integer.parseInt(getParam("socKementerian")));
			h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
//			h.put("socAgensi", Integer.parseInt(getParam("socAgensi")));			
			h.put("Tajuk", getParam("txtTajuk"));
//			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
//			h.put("NoFailLain", getParam("txtNoFailLain"));
//			h.put("TarikhBukaFail", getParam("txdTarikhBukaFail"));
			System.out.println("GadaianProcess::SimpanSemak:: h = "+h);
			idFail = FrmGadaianSemakan1Data.simpan(h);
			result = "baru";
			return idFail;
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
	//		int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			h.put("idFail", Integer.parseInt(getParam("idFail")));
//			h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
//			h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
//			h.put("socAgensi", Integer.parseInt(getParam("socAgensi")));			
			h.put("Tajuk", getParam("txtTajuk"));
//			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
//			h.put("NoFailLain", getParam("txtNoFailLain"));
//			h.put("TarikhBukaFail", getParam("txdTarikhBukaFail"));
			System.out.println("GadaianProcess::SimpanSemak:: h = "+h);
			idFail = FrmGadaianSemakan1Data.update(h);
			result = "kemaskini";
			return idFail;
		}
	}
	
	//*** Senarai Fail Gadaian Controller
	public void ListFail(HttpSession session, String key_cari, String keyNo_cari, Long idNegeri) throws Exception
	  {
		FrmSenaraiFailGadaianData.setList(key_cari,keyNo_cari,idNegeri,null,null,null,null,null);
	  }
	
	//*** Senarai Permohonan Controller
	public void ListPermohonan(HttpSession session, int idFail, String carian) throws Exception
	  {
		FrmGadaianSenaraiPermohonanData.setListPermohonan(String.valueOf(idFail),"",carian);
	  }
	
	//*** Gadaian Semakan Controller
	private void ListSemakBaru(HttpSession session) throws Exception{
		FrmGadaianSemakanData.setSemakBaru(getParam("idFail"));
	}
	
	private void DataSemakBaru(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
//		Date now = new Date();
//	    SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmGadaianSemakanData.getSemakBaru();
		    this.context.put("SemakBaru", list);
		    Hashtable h = (Hashtable) list.get(0);	
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()),"disabled"));
		    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()),"disabled"));
		    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi"));
		    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()),"disabled"));
		    this.context.put("datenow", formatter.format(now));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}catch(Exception ex){
			System.out.println("GadaianProcess::DataSemak::Exception = "+ex);
		}
	}
	
	private void ListSemak(HttpSession session, String id) throws Exception{
		String idPermohonan ="";
		if(getParam("idPermohonan") != "")
			idPermohonan = (String)getParam("idPermohonan");
		else if(id != "")
			idPermohonan = id;
		else
			System.out.println("GadaianProcess::ListSemak::idPermohonan = 0");
		FrmGadaianSemakanData.setSemak(idPermohonan);
	}
	
	private void DataSemak(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmGadaianSemakanData.getSemak();
		    this.context.put("Semak", list);
		    Hashtable h = (Hashtable) list.get(0);	
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()),"disabled"));
		    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()),"disabled"));
		    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("idAgensi").toString()),disability));
		    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()),"disabled"));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}catch(Exception ex){
			System.out.println("GadaianProcess::DataSemak::Exception = "+ex);
		}
	}
	
	private String SimpanSemak(HttpSession session) throws Exception {
		String idPermohonan = "";
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
//			h.put("TarikhBukaFail", getParam("txdTarikhBukaFail"));
			System.out.println("GadaianProcess::SimpanSemak:: h = "+h);
			idPermohonan = FrmGadaianSemakanData.simpan(h);
			result = "baru";
			return idPermohonan;
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
//			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
			h.put("socAgensi", getParam("socAgensi"));
			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
			h.put("NoFailLain", getParam("txtNoFailLain"));
			h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
			System.out.println("GadaianProcess::SimpanSemak:: h = "+h);
			idPermohonan = FrmGadaianSemakanData.update(h);
			result = "kemaskini";
			return idPermohonan;
		}
	}
	
	//*** Gadaian Pendaftaran Hakmilik Controller
	private void ListPenHamilik(HttpSession session) throws Exception
	{
		int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
		FrmGadaianPenHamilikData.setPenHakmilik(idPermohonan);
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
			    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("IdKementerian").toString()),"disabled"));
			    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("IdAgensi").toString()),"disabled"));
			    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("IdNegeri").toString()),"disabled"));
			    this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah",Long.parseLong(h.get("IdDaerah").toString()),disability));
			    this.context.put("selectMukim",HTML.SelectMukim("socMukim",Long.parseLong(h.get("IdMukim").toString()),disability));
			    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong(h.get("IdJenishakmilik").toString()),disability));
			    this.context.put("selectLot",HTML.SelectLot("socLot",Long.parseLong(h.get("IdLot").toString()),disability));
			    this.context.put("selectLuas",HTML.SelectLuas("socLuas",Long.parseLong(h.get("IdLuas").toString()),disability));
			    this.context.put("selectRizab",HTML.SelectRizab("socRizab",Long.parseLong(h.get("IdRizab").toString()),disability));
			    this.context.put("selectKategori",HTML.SelectKategori("socKategori",Long.parseLong(h.get("IdKategori").toString()),disability));
			    this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			}else{
				System.out.println("GadaianProcess::DataPenHamilik:: list.size() = "+list.size());
				list.clear();
				int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
				FrmGadaianPenHamilikData.setPenHakmilikBaru(idPermohonan);
				list = FrmGadaianPenHamilikData.getPenHakmilikBaru();
				System.out.println("GadaianProcess::DataPenHamilik:: list = "+list);
				this.context.put("PenHamilik", list);
			    Hashtable h = (Hashtable) list.get(0);
			    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("IdKementerian").toString()),"disabled"));
			    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("IdAgensi").toString()),"disabled"));
			    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("IdNegeri").toString()),"disabled"));
			    this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah"));
			    this.context.put("selectMukim",HTML.SelectMukim("socMukim"));
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
		}catch(Exception ex){
			System.out.println("GadaianProcess::DataPenHamilik::Exception = "+ex);
		}
	}
	
	private void SimpanPenHamilik(HttpSession session) throws Exception
	{
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
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
//			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
			h.put("idHakmilikurusan", Integer.parseInt(getParam("idHakmilikurusan")));
//			h.put("socNegeri", getParam("socNegeri"));
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
	
	//*** Gadaian Hakmilik Controller
	private void ListHakmilik(HttpSession session) throws Exception
	{
		int idHakmilikurusan = Integer.parseInt(getParam("idHakmilikurusan"));
		FrmGadaianHakmilikData.setListHakmilik(String.valueOf(idHakmilikurusan));
		
	}
	
	private void DataHakmilik(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmGadaianHakmilikData.getListHakmilik();
			System.out.println("GadaianProcess::list.size() "+list.size());			
			if(list.size() != 0){			    
				System.out.println("GadaianProcess::DataHakmilik::list "+list);
				this.context.put("Hakmilik", list);
				Hashtable hak = (Hashtable) list.get(0);			
				this.context.put("selectADaerah",HTML.SelectDaerah("socADaerah",Long.parseLong(hak.get("IdDaerah").toString()),disability));
				this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri",Long.parseLong(hak.get("IdNegeri").toString()),disability));
				this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			}else{
				this.context.put("selectADaerah",HTML.SelectDaerah("socADaerah"));
				this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri"));
				this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			}
		}catch(Exception ex){
			System.out.println("GadaianProcess::DataHakmilik::Exception = "+ex);
		}
	}
	
	private void SimpanHakmilik(HttpSession session) throws Exception
	{
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
			h.put("idNegeri", Integer.parseInt(getParam("socANegeri")));
			h.put("noTelefon", getParam("txtNoTelefon"));
			h.put("noFax", getParam("txtNoFax"));
			h.put("idBebanan", Integer.parseInt(getParam("idBebanan")));
			h.put("noPerserahan", getParam("txtNoPerserahan"));
			FrmGadaianHakmilikData.update(h);
			result = "kemaskini";
		}		
	}
	
	//*** Gadaian Peguam Controller
	private void ListPeguam(HttpSession session) throws Exception
	{
		int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
		FrmGadaianPeguamData.setListPeguam(String.valueOf(idPermohonan));
	}
	
	private void DataPeguam(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmGadaianPeguamData.getListPeguam();
			System.out.println("GadaianProcess::DataPeguam::list.size() "+list.size());
			if(list.size() != 0){
//				readability = "readonly";
//			    disability = "disabled";			    
			    
				this.context.put("Peguam", list);
				Hashtable peg = (Hashtable) list.get(0);
				System.out.println("GadaianProcess::DataPeguam::list "+list);	
				this.context.put("selectBDaerah",HTML.SelectDaerah("socBDaerah",Long.parseLong(peg.get("IdDaerah").toString()),disability));
				this.context.put("selectBNegeri",HTML.SelectNegeri("socBNegeri",Long.parseLong(peg.get("IdNegeri").toString()),disability));
				this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			}else{
				this.context.put("selectBDaerah",HTML.SelectDaerah("socBDaerah"));
				this.context.put("selectBNegeri",HTML.SelectNegeri("socBNegeri"));
				this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			}
		}catch(Exception ex){
			System.out.println("GadaianProcess::DataPeguam::Exception = "+ex);
		}
	}
	
	private void SimpanPeguam(HttpSession session) throws Exception
	{
		if(getParam("idPeguam") == ""){
			//fail baru
			Hashtable h = new Hashtable();
			h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
			h.put("nama", getParam("txtNama"));
			h.put("alamat1", getParam("txtAlamat1"));
			h.put("alamat2", getParam("txtAlamat2"));
			h.put("alamat3", getParam("txtAlamat3"));
			h.put("poskod", getParam("txtPoskod2"));
			h.put("idDaerah", Integer.parseInt(getParam("socBDaerah")));
			h.put("idNegeri", Integer.parseInt(getParam("socBNegeri")));
			h.put("noTelefon", getParam("txtNoTelefon"));
			h.put("noFax", getParam("txtNoFax"));
			FrmGadaianPeguamData.simpan(h);
			result = "baru";
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
			h.put("idPeguam", Integer.parseInt(getParam("idPeguam")));
			h.put("nama", getParam("txtNama"));
			h.put("alamat1", getParam("txtAlamat1"));
			h.put("alamat2", getParam("txtAlamat2"));
			h.put("alamat3", getParam("txtAlamat3"));
			h.put("poskod", getParam("txtPoskod2"));
			h.put("idDaerah", Integer.parseInt(getParam("socBDaerah")));
			h.put("idNegeri", Integer.parseInt(getParam("socBNegeri")));
			h.put("noTelefon", getParam("txtNoTelefon"));
			h.put("noFax", getParam("txtNoFax"));
			FrmGadaianPeguamData.update(h);
			result = "kemaskini";
		}
	}
}
