package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmPerletakhakanHakmilikData;
import ekptg.model.htp.FrmPerletakhakanInfoData;
import ekptg.model.htp.FrmPerletakhakanSemakan1Data;
import ekptg.model.htp.FrmPerletakhakanSemakanData;
import ekptg.model.htp.FrmPerletakhakanSenaraiPermohonanData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailPerletakhakanData;

public class PerletakhakanProcess extends VTemplate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String result = new String();
	public Template doTemplate() throws Exception {

		HttpSession session = this.request.getSession();
		String title = "Perletakhakan";
		String vm = "";
		String disability = "";
		String readability = "";
		String style1 = "";
		String style2 = "";
		Vector list = new Vector();
		list.clear();
		String submit = getParam("command");
		String mode = getParam("mode");
		this.context.put("tajuk", title);

		if ("FailBaru".equals(submit)) {
			vm = "app/htp/frmPerletakhakanSemak1.jsp";
			int idFail = 0;
			int idPermohonan = 0;
			
			this.context.put("socSeksyen", "3");
			this.context.put("tajuk1", title);

			if ("view".equals(mode)) {
				readability = "readonly";
				disability = "disabled";
				idFail = Integer.parseInt(getParam("idFail").toString());
				setFailBaru(session, idFail);
				style2 = "none";
				ViewFailBaru(session, disability, readability, style1, style2);
			} 
			else if ("kemaskini".equals(mode)) {
				idFail = Integer.parseInt(getParam("idFail").toString());
				setFailBaru(session, idFail);
				style1 = "none";
				ViewFailBaru(session, disability, readability, style1, style2);
			} 
			else if ("simpan".equals(mode)) {
				readability = "readonly";
				disability = "disabled";
				idFail = SimpanFailBaru(session);
				
				
				setFailBaru(session, idFail);
				style2 = "none";
				ViewFailBaru(session, disability, readability, style1, style2);
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);
			}
			else {
				style1 = "none";
				DataFailBaru(session, disability, readability, style1, style2);
//				style1 = "none";
//				DataFailBaru(session, disability, readability, style1, style2);
//				Vector senaraiSemakan = null;
//				senaraiSemakan = FrmSemakan.getSenaraiSemakan("frmPerletakhakanSema");
//				this.context.put("senaraiSemakan", senaraiSemakan);
			}
			System.out.println("PerletakhakanProcess::FailBaru");
		}
		else if("SenaraiPermohonan".equals(submit)){
			vm = "app/htp/frmPerletakhakanSenaraiPermohonan.jsp";
			int idFail = Integer.parseInt(getParam("idFail"));
			String carian = getParam("NamaPemohon");
			ListPermohonan(session, idFail, carian);
			list = FrmPerletakhakanSenaraiPermohonanData.getListPermohonan();
		    this.context.put("SenaraiPermohonan", list);
		    this.context.put("NoFail", getParam("noFail"));
		    this.context.put("IdFail", getParam("idFail"));
		    this.context.put("carian", getParam("NamaPemohon"));
			System.out.println("PerletakhakanProcess::SenaraiHakmilik");			
		}
		else if("Semakan".equals(submit)){
			vm = "app/htp/frmPerletakhakanSemak.jsp";
			int idPermohonan = 0;
			this.context.put("NoFail", getParam("noFail"));
		    this.context.put("IdFail", getParam("idFail"));
		    this.context.put("IdPermohonan", getParam("idPermohonan"));	
		    
		    this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPerletakhakanSemak"));
		    this.context.put("semakclass", new FrmSemakan());

			if("baru".equals(mode)){
				style1 = "none";
				ListSemakBaru(session);
			    DataSemakBaru(session,disability,readability,style1,style2);
			    System.out.println("PerletakhakanProcess::Semakan::baru");
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
	        			System.out.println("PerletakhakanProcess:Semakan::simpan::cbsemaks:::"+cbsemaks[i]);
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
		    System.out.println("PerletakhakanProcess::SEMAKAN");	
		}
		else if("Hakmilik".equals(submit)){
			vm = "app/htp/frmPerletakhakanSenaraiHakmilik.jsp";			
		    this.context.put("NoFail", getParam("noFail"));
		    this.context.put("IdFail", getParam("idFail"));
		    this.context.put("IdPermohonan", getParam("idPermohonan"));
		    
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
		    list = FrmPerletakhakanInfoData.getSemak(idPermohonan);
			this.context.put("Info", list);
	
			Hashtable h = (Hashtable) list.get(0);
		    if("baru".equals(mode)){
		    	
		    }else if("kemaskini".equals(mode)){
		    	style1 = "none";
		    	ListHakmilik(session);
		    	DataHakmilik(session, disability, readability,style1,style2);
		    }else if("simpan".equals(mode)){
		    	readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
			    SimpanHakmilik(session);
		    	ListHakmilik(session);
		    	DataHakmilik(session, disability, readability,style1,style2);
			    this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);
		    }else if("hapus".equals(mode)){
		    	
		    }else{
			    readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
			    ListHakmilik(session);
			    DataHakmilik(session, disability, readability,style1,style2);
		    }		    
		    System.out.println("PerletakhakanProcess::Hakmilik");				
		}
			
//			if ("Hakmilik".equals(submit)){
//			vm = "app/htp/frmPerletakhakanSenaraiHakmilik.jsp";
//			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
//			this.context.put("IdPermohonan", idPermohonan);
//		    this.context.put("NoFail", getParam("noFail"));
//		    this.context.put("IdFail", getParam("idFail"));
//		    this.context.put("IdHakmilikurusan", getParam("idHakmilikurusan"));
//		    
//			list = FrmPerletakhakanInfoData.getSemak(idPermohonan);
//			this.context.put("Info", list);
//			Hashtable h = (Hashtable) list.get(0);
//			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()),"disabled"));
//			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()),"disabled"));
//			this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("idAgensi").toString()),"disabled"));
//			this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()),"disabled"));
//		    
//		    if("simpanHakmilik".equals(mode)){
//		    	readability = "readonly";
//			    disability = "disabled";
//			    style2 = "none";
//		    	SimpanHakmilik(session);
//		    	ListHakmilik(session);
//		    	DataHakmilik(session,disability,readability,style1,style2);
//		    	this.context.put("SimpanStatus", "success");
//				this.context.put("ResultSimpan", result);
//		    }else if("kemaskiniHakmilik".equals(mode)){
//		    	style1 = "none";
//		    	ListHakmilik(session);
//		    	DataHakmilik(session,disability,readability,style1,style2);
////				this.context.put("modeSoc", disability);
////			    this.context.put("mode", readability);		    	
//		    }else if("hapusHakmilik".equals(mode)){
//		    	
//		    }else if("hakmilikview".equals(mode)){				
//		    	readability = "readonly";
//			    disability = "disabled";
//			    style2 = "none";
//				ListHakmilik(session);
//				DataHakmilik(session,disability,readability,style1,style2);
//		    }
//		    String selectedTab = new String();
//            selectedTab = getParam("tabId").toString();
//            if (selectedTab == null || "".equals(selectedTab) ) {
//               selectedTab="0";
//            }
//            this.context.put("selectedTab",selectedTab);
//			System.out.println("PerletakhakanProcess::Hakmilik");				
//		}
			else{ 
			vm = "app/htp/frmPerletakhakanSenaraiFail.jsp";
			String key_cari = getParam("NamaFail");
			String keyNo_cari = getParam("NoFail");
			String Negeri = getParam("socNegeri") == "" ? "20": getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
			ListFail(session, key_cari, keyNo_cari, idNegeri);
			list = FrmSenaraiFailPerletakhakanData.getList();
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",idNegeri, disability));
			this.context.put("Senaraifail", list);
			this.context.put("carian", getParam("NamaFail"));
			this.context.put("carianNoFail", getParam("NoFail"));
			this.context.put("Negeri", idNegeri);
			System.out.println("PerletakhakkanProcess::SenaraiFail");
		}

		Template template = this.engine.getTemplate(vm);
		return template;
	}
	
	//*** Perletakhakan Hakmilik Controller
	private void ListHakmilik(HttpSession session) throws Exception
	{
		int idHakmilikurusan = Integer.parseInt(getParam("idHakmilikurusan"));
		FrmPerletakhakanHakmilikData.setListHakmilik(idHakmilikurusan);
	}
	
	private void DataHakmilik(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPerletakhakanHakmilikData.getListHakmilik();
			System.out.println("PerletakhakanProcess::list.size() "+list.size());			
			if(list.size() != 0){			    
				System.out.println("PerletakhakanProcess::DataHakmilik::list "+list);
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
			System.out.println("PerletakhakanProcess::DataHakmilik::Exception = "+ex);
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
			FrmPerletakhakanHakmilikData.simpan(h);
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
			FrmPerletakhakanHakmilikData.update(h);
			result = "kemaskini";
		}		
	}
	
	// *** Fail Baru Controller
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
			System.out.println("PerletakhakanProcess::DataFailBaru::Exception = "+ex);
		}
	}
	
	private int SimpanFailBaru(HttpSession session) throws Exception 
	{
		int idFail;
		if(getParam("idFail") == "") {
			//fail baru
			Hashtable h = new Hashtable();
			h.put("idNegeri", Integer.parseInt(getParam("socNegeri")));
			h.put("idKementerian", Integer.parseInt(getParam("socKementerian")));
			h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
			//h.put("socAgensi", Integer.parseInt(getParam("socAgensi")));
			h.put("Tajuk", getParam("txtTajuk"));
			//h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
			//h.put("NoFailLain", getParam("txtNoFailLain"));
			//h.put("TarikhBukaFail", getParam("txdTarikhBukaFail"));
			System.out.println("PerletakhakanProcess::SimpanSemak:: h = " + h);
			idFail = FrmPerletakhakanSemakan1Data.simpan(h);
			result = "baru";
			return idFail;
		} else {
			// kemaskini fail
			Hashtable h = new Hashtable();
			h.put("idFail", Integer.parseInt(getParam("idFail")));
			//h.put("idNegeri", Integer.parseInt(getParam("socNegeri")));
			//h.put("idKementerian", Integer.parseInt(getParam("socKementerian")));
			//h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
			//h.put("socAgensi", Integer.parseInt(getParam("socAgensi")));
			h.put("Tajuk", getParam("txtTajuk"));
			//h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
			//h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
			//h.put("NoFailLain", getParam("txtNoFailLain"));
			//h.put("TarikhBukaFail", getParam("txdTarikhBukaFail"));
			System.out.println("PerletakhakanProcess::SimpanSemak:: h = " + h);
			idFail = FrmPerletakhakanSemakan1Data.update(h);
			result = "kemaskini";
			return idFail;
		}
	}
	
	private Date now = new Date();
    private SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
	
	public void setFailBaru(HttpSession session, int idFail) throws Exception {
		FrmPerletakhakanSemakan1Data.setSemak(idFail);
	}
	
	private void ViewFailBaru(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPerletakhakanSemakan1Data.getSemak();
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
			System.out.println("PerletakhakanProcess::ListFailBaru::Exception = "+ex);
		}
	}
	
//	private void ViewFailBaru(HttpSession session, String disability,String readability, String style1, String style2) throws Exception {
//		Vector list = new Vector();
//		list.clear();
//		try {
//			list = FrmPerletakhakanSemakan1Data.getSemak();
//			this.context.put("Semak", list);
//			Hashtable h = (Hashtable) list.get(0);
//			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()), "disabled"));
//			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Long.parseLong(h.get("idKementerian").toString()), "disabled"));
//			this.context.put("selectAgensi", HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("idAgensi").toString()), disability));
//			this.context.put("selectSuburusan", HTML.SelectSuburusan("socSuburusan", "5", Long.parseLong(h.get("idSuburusan").toString()), disability));
//			//this.context.put("urusan", "5");
//			this.context.put("modeSoc", disability);
//			this.context.put("mode", readability);
//			this.context.put("Style1", style1);
//			this.context.put("Style2", style2);
//		} catch (Exception ex) {
//			System.out.println("PerletakhakanProcess::ListFailBaru::Exception = "+ ex);
//		}
//	}
	
	// *** Senarai Fail Perletakhakan Controller
	public void ListFail(HttpSession session, String key_cari,String keyNo_cari, Long idNegeri) throws Exception {
		FrmSenaraiFailPerletakhakanData.setList("5", key_cari, keyNo_cari,idNegeri);
	}
	
//	private void seterus(HttpSession session) throws Exception {
//		int id = Integer.parseInt(getParam("id_permohonan"));
//	}
	
	//*** Senarai Permohonan Controller
	public void ListPermohonan(HttpSession session, int idFail, String carian) throws Exception
	  {
		FrmPerletakhakanSenaraiPermohonanData.setListPermohonan(idFail,carian);
	  }
	
	//*** Perletakhakan Semakan Controller
	private void ListSemakBaru(HttpSession session) throws Exception
	{
		int idFail = Integer.parseInt(getParam("idFail"));
		FrmPerletakhakanSemakanData.setSemakBaru(idFail);
	}
	
	private void DataSemakBaru(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
//		Date now = new Date();
//	    SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPerletakhakanSemakanData.getSemakBaru();
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
			System.out.println("PerletakhakanProcess::DataSemak::Exception = "+ex);
		}
	}
	
	private void ListSemak(HttpSession session, int id) throws Exception
	{
		int idPermohonan = 0;
		if(getParam("idPermohonan") != "")
			idPermohonan = Integer.parseInt(getParam("idPermohonan"));
		else if(id != 0)
			idPermohonan = id;
		else
			System.out.println("PerletakhakanProcess::ListSemak::idPermohonan = 0");
		FrmPerletakhakanSemakanData.setSemak(idPermohonan);
	}
	
	private void DataSemak(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPerletakhakanSemakanData.getSemak();
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
			System.out.println("PerletakhakanProcess::DataSemak::Exception = "+ex);
		}
	}
	
	private int SimpanSemak(HttpSession session) throws Exception
	{
		int idPermohonan;
		if(getParam("idPermohonan") == ""){
			//fail baru
			Hashtable h = new Hashtable();
			h.put("IdFail", Integer.parseInt(getParam("idFail")));
			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
			h.put("Tajuk", getParam("txtTajuk"));
			h.put("socAgensi", getParam("socAgensi"));
			h.put("idSuburusan", Integer.parseInt(getParam("idSuburusan")));
			h.put("NoFailLain", getParam("txtNoFailLain"));
			//h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
//			h.put("TarikhBukaFail", getParam("txdTarikhBukaFail"));
			System.out.println("PerletakhakanProcess::SimpanSemak:: h = "+h);
			idPermohonan = FrmPerletakhakanSemakanData.simpan(h);
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
			System.out.println("PerletakhakanProcess::SimpanSemak:: h = "+h);
			idPermohonan = FrmPerletakhakanSemakanData.update(h);
			result = "kemaskini";
			return idPermohonan;
		}
	}
	
}
