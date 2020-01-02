package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmPembelianInfoData;
import ekptg.model.htp.FrmPembelianPeguamData;
import ekptg.model.htp.FrmPembelianPemilikData;
import ekptg.model.htp.FrmPembelianSemakan1Data;
import ekptg.model.htp.FrmPembelianSemakanData;
import ekptg.model.htp.FrmPembelianTBangunData;
import ekptg.model.htp.FrmPembelianTanahData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailPembelianData;

public class PembelianProcess extends VTemplate {
	private String result = new String();
	public Template doTemplate() throws Exception
    {
    	HttpSession session = this.request.getSession();
    	System.out.println("PembelianProcess::");
    	String vm = "";
    	String disability = "";
    	String readability = "";
    	String style1 = "";
    	String style2 = "";
    	String style3 = "";
    	this.context.put("SimpanStatus", "");
    	Vector list = new Vector();
    	list.clear();
		String submit = getParam("command");
		String mode = getParam("mode");
		
		if("FailBaru".equals(submit)){
			vm = "app/htp/frmPembelianSemakan.jsp";
			Long  idPermohonan = 0L;
			this.context.put("IdPermohonan", getParam("idPermohonan"));	
			
			this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPembelianSemakan"));
//		    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
			this.context.put("semakclass", new FrmSemakan());

			if("view".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    idPermohonan = Long.parseLong(getParam("idPermohonan").toString());
			    setFailBaru(session,idPermohonan);
			    style2 = "none";
				ViewFailBaru(session,disability,readability,style1,style2);
			}else if("kemaskini".equals(mode)){
				idPermohonan = Long.parseLong(getParam("idPermohonan").toString());
			    setFailBaru(session,idPermohonan);
			    style1 = "none";
				ViewFailBaru(session,disability,readability,style1,style2);
			}else if("simpan".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    idPermohonan = SimpanFailBaru(session);
				
				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
	    	    //System.out.println("FrmPajakanKecilSenaraiPermohonan:id::"+id);
	        	FrmSemakan frmSemak = new FrmSemakan();
	        	frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
	        	if(cbsemaks!=null){
	        		for (int i = 0; i < cbsemaks.length; i++) { 
	        			frmSemak = new FrmSemakan();
	        			System.out.println("PembelianProcess:FailBaru::simpan::cbsemaks:::"+cbsemaks[i]);
	        			frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));           
	        		}
	        	}
				
				setFailBaru(session,idPermohonan);
				style2 = "none";
				ViewFailBaru(session,disability,readability,style1,style2);
				this.context.put("IdPermohonan", idPermohonan);
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);
			}else{
				style1 = "none";
				DataFailBaru(session, disability, readability, style1, style2);
			}
			System.out.println("PembelianProcess::FailBaru");
			
		}else if("Semakan".equals(submit)){
			vm = "app/htp/frmPembelianSemakan1.jsp";
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			this.context.put("IdPermohonan", idPermohonan);	
			
			this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPembelianSemakan"));
//		    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
			this.context.put("semakclass", new FrmSemakan());

			if("view".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    idPermohonan = Integer.parseInt(getParam("idPermohonan").toString());
			    setSemak(session,idPermohonan);
			    style2 = "none";
				ViewSemak(session,disability,readability,style1,style2);
			}else if("kemaskini".equals(mode)){
				idPermohonan = Integer.parseInt(getParam("idPermohonan").toString());
			    setSemak(session,idPermohonan);
			    style1 = "none";
				ViewSemak(session,disability,readability,style1,style2);
			}else if("simpan".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    SimpanSemak(session);
				
				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
	    	    //System.out.println("FrmPajakanKecilSenaraiPermohonan:id::"+id);
	        	FrmSemakan frmSemak = new FrmSemakan();
	        	frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
	        	if(cbsemaks!=null){
	        		for (int i = 0; i < cbsemaks.length; i++) { 
	        			frmSemak = new FrmSemakan();
	        			System.out.println("PembelianProcess:Semakan::simpan::cbsemaks:::"+cbsemaks[i]);
	        			frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));           
	        		}
	        	}
				
				setSemak(session,idPermohonan);
				style2 = "none";
				ViewSemak(session,disability,readability,style1,style2);
//				this.context.put("IdPermohonan", idPermohonan);
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);
			}
			
			System.out.println("PembelianProcess::Semakan");
		}else if("Maklumat".equals(submit)){
			vm = "app/htp/frmPembelianMaklumat.jsp";
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			this.context.put("IdPermohonan", idPermohonan);
		    this.context.put("IdFail", getParam("idFail"));
		    this.context.put("IdHakmilikurusan", "");
		    
		    //***tanah
		    String Daerah = getParam("socDaerah")==""?"0":getParam("socDaerah");
			Long idDaerah = Long.parseLong(Daerah);
			String Mukim = getParam("socMukim")==""?"0":getParam("socMukim");
			Long idMukim = Long.parseLong(Mukim);
			
			//***tbangun
			String DaerahB = getParam("socDaerahB")==""?"0":getParam("socDaerahB");
			Long idDaerahB = Long.parseLong(DaerahB);
			String MukimB = getParam("socMukimB")==""?"0":getParam("socMukimB");
			Long idMukimB = Long.parseLong(MukimB);
		     
		    list = FrmPembelianInfoData.getSemak(idPermohonan);
			this.context.put("Info", list);
			Hashtable h = (Hashtable) list.get(0);
			long idNegeri = Long.parseLong(h.get("idNegeri").toString());
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,"disabled"));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()),"disabled"));
			this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("idAgensi").toString()),"disabled"));
			this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()),"disabled"));
			
			if("pemilik".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style3 = "none";
			    setTanah(session,idPermohonan,idDaerah,idMukim);
				ViewTanah(session,idNegeri,idDaerah,idMukim);
				DataPemilikDepan(session,disability,readability,style1,style2,style3);
			}else if("pemilikview".equals(mode)){
				this.context.put("IdHakmilikurusan", getParam("idHakmilikurusan"));
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
				ListPemilik(session);
				DataPemilik(session,disability,readability,style1,style2,style3);
			}else if("simpanpemilik".equals(mode)){
				this.context.put("IdHakmilikurusan", getParam("idHakmilikurusan"));
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
		    	SimpanPemilik(session);
		    	ListPemilik(session);
		    	DataPemilik(session,disability,readability,style1,style2,style3);
		    	this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);
			}else if("kemaskinipemilik".equals(mode)){
				this.context.put("IdHakmilikurusan", getParam("idHakmilikurusan"));
				style1 = "none";
		    	ListPemilik(session);
		    	DataPemilik(session,disability,readability,style1,style2,style3);
			}else if("hapuspemilik".equals(mode)){
				
			}else if("peguamview".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
				ListPeguam(session);
				DataPeguam(session,disability,readability,style1,style2);
			}else if("peguamsimpan".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
		    	SimpanPeguam(session);
		    	ListPeguam(session);
		    	DataPeguam(session,disability,readability,style1,style2);				
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);
			}else if ("peguamkemaskini".equals(mode)){
				style1 = "none";
		    	ListPeguam(session);
		    	DataPeguam(session,disability,readability,style1,style2);
			}else if("peguamhapus".equals(mode)){
				
			}else if("tanahview".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
			    setTanah(session,idPermohonan,idDaerah,idMukim);
				ViewTanah(session,idNegeri,idDaerah,idMukim);
				ViewTanahUlasan(session,disability,readability,style1,style2);
				System.out.println("PembelianProcess::Maklumat:tanahview");
			}else if("tanahsimpan".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
			    SimpanTanahUlasan(session);
			    setTanah(session,idPermohonan,idDaerah,idMukim);
			    ViewTanah(session,idNegeri,idDaerah,idMukim);
				ViewTanahUlasan(session,disability,readability,style1,style2);
				System.out.println("PembelianProcess::Maklumat:tanahsimpan");
			}else if("tanahkemaskini".equals(mode)){
				style1 = "none";
				setTanah(session,idPermohonan,idDaerah,idMukim);
				ViewTanah(session,idNegeri,idDaerah,idMukim);
				ViewTanahUlasan(session,disability,readability,style1,style2);
			}else if("tanahhapus".equals(mode)){
				
			}else if("tbangunview".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
				setTBangun(session,idPermohonan,idDaerahB,idMukimB);
				ViewTBangun(session,idNegeri,idDaerahB,idMukimB);
				System.out.println("PembelianProcess::Maklumat:tbangunview");
			}else if("tbangunsimpan".equals(mode)){
				
			}else if("tbangunkemaskini".equals(mode)){
				
			}
			String selectedTab = new String();
            selectedTab = getParam("tabId").toString();
            if (selectedTab == null || "".equals(selectedTab) ) {
               selectedTab="0";
            }
            this.context.put("selectedTab",selectedTab);
            System.out.println("PembelianProcess::Maklumat");			
		}else{		
			vm = "app/htp/frmSenaraiFailPembelian.jsp";
			String key_cari = getParam("NamaFail");
			String keyNo_cari = getParam("NoFail");
			String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
			ListFail(session, key_cari, keyNo_cari, idNegeri);
	    	list = FrmSenaraiFailPembelianData.getList();
	    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,disability));
		    this.context.put("Senaraifail", list);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
		    this.context.put("Negeri", idNegeri);
			System.out.println("PembelianProcess::SenaraiFail");
		}
		Template template = this.engine.getTemplate(vm);
        return template;
    }
	
	//*** Senarai Fail Pembelian Controller
	public void ListFail(HttpSession session, String key_cari, String keyNo_cari, Long idNegeri) throws Exception
	  {
		FrmSenaraiFailPembelianData.setList(key_cari,keyNo_cari,idNegeri);
	  }
	
	private Date now = new Date();
    private SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
	
	//*** Fail Baru Controller
	private void DataFailBaru(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		try{
			this.context.put("Semak", "");
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));
		    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian"));
		    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi"));
		    this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("2","socSuburusan",null,disability));
		    this.context.put("datenow", formatter.format(now));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}catch(Exception ex){
			System.out.println("PembelianProcess::DataFailBaru::Exception = "+ex);
		}
	}
	
	public void setFailBaru(HttpSession session, Long idPermohonan) throws Exception
	  {
		FrmPembelianSemakanData.setSemak(""+idPermohonan);
	  }
	
	private void ViewFailBaru(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPembelianSemakanData.getSemak();
		    this.context.put("Semak", list);
		    Hashtable h = (Hashtable) list.get(0);	
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()), "disabled"));
		    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()), "disabled"));
		    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("idAgensi").toString()), disability));
		    this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("2","socSuburusan",Long.parseLong(h.get("idSuburusan").toString()), "disabled"));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}catch(Exception ex){
			System.out.println("PembelianProcess::ListFailBaru::Exception = "+ex);
		}
	}
	
	private long SimpanFailBaru(HttpSession session) throws Exception
	{
		Long idPermohonan;
		if(getParam("idPermohonan") == ""){
			//fail baru
			Hashtable h = new Hashtable();			
			h.put("idNegeri", Integer.parseInt(getParam("socNegeri")));
			h.put("idKementerian", Integer.parseInt(getParam("socKementerian")));
			h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
			h.put("idAgensi", Integer.parseInt(getParam("socAgensi")));			
			h.put("Tajuk", getParam("txtTajuk"));
			h.put("NoFailKJP", getParam("txtNoFailKJP"));
			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
			h.put("NoFailLain", getParam("txtNoFailLain"));
			h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
			System.out.println("PembelianProcess::SimpanSemak:: h = "+h);
			idPermohonan = FrmPembelianSemakanData.simpan(h);
			result = "baru";
			return idPermohonan;
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
//			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			h.put("idFail", Integer.parseInt(getParam("idFail")));
			h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
//			h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
			h.put("idAgensi", Integer.parseInt(getParam("socAgensi")));			
			h.put("Tajuk", getParam("txtTajuk"));
			h.put("NoFailKJP", getParam("txtNoFailKJP"));
			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
			h.put("NoFailLain", getParam("txtNoFailLain"));
			h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
			System.out.println("PembelianProcess::SimpanSemak:: h = "+h);
			idPermohonan = FrmPembelianSemakanData.update(h);
			result = "kemaskini";
			return idPermohonan;
		}
	}
	

	//*** Pembelian Pemilik Controller
	private void DataPemilikDepan(HttpSession session, String disability, String readability, String style1, String style2, String style3) throws Exception
	{
		try{	
			style3 = "none";
			System.out.println("PembelianProcess::DataPemilikDepan::list "+0000);
			this.context.put("Pemilik", "");
			this.context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,disability));
			this.context.put("selectADaerah",HTML.SelectDaerah("socADaerah",null,disability));
			this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri",null,disability));
			this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		    this.context.put("Style3", style3);
		}catch(Exception ex){
			System.out.println("PembelianProcess::DataPemilikDepan::Exception = "+ex);
		}
	}
	
	
	private void ListPemilik(HttpSession session) throws Exception
	{
		int idHakmilikurusan = Integer.parseInt(getParam("idHakmilikurusan"));
		FrmPembelianPemilikData.setListPemilik(""+idHakmilikurusan);
	}
	
	private void DataPemilik(HttpSession session, String disability, String readability, String style1, String style2, String style3) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPembelianPemilikData.getListPemilik();
			System.out.println("PembelianProcess::list.size() "+list.size());			
			if(list.size() != 0){			    
				System.out.println("PembelianProcess::DataPemilik::list "+list);
				this.context.put("Pemilik", list);
				Hashtable hak = (Hashtable) list.get(0);	
				this.context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Long.parseLong(hak.get("idJenisnopb").toString()),disability));
				this.context.put("selectADaerah",HTML.SelectDaerah("socADaerah",Long.parseLong(hak.get("IdDaerah").toString()),disability));
				this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri",Long.parseLong(hak.get("IdNegeri").toString()),disability));
				this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			    this.context.put("Style3", style3);
			}else{
				this.context.put("Pemilik", "");
				this.context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB"));
				this.context.put("selectADaerah",HTML.SelectDaerah("socADaerah"));
				this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri"));
				this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			    this.context.put("Style3", style3);
			}
		}catch(Exception ex){
			System.out.println("PembelianProcess::DataPemilik::Exception = "+ex);
		}
	}
	
	private void SimpanPemilik(HttpSession session) throws Exception
	{
		if(getParam("idPihakberkepentingan") == ""){
			//fail baru
			Hashtable h = new Hashtable();
			h.put("idHakmilikurusan", Integer.parseInt(getParam("idHakmilikurusan")));
			h.put("namaPemaju", getParam("txtNamaPemaju"));
			h.put("idJenisNoPB", getParamAsInteger("socJenisNoPB"));
			h.put("noRuj", getParam("txtNoRuj"));
			h.put("alamat1", getParam("txtAlamat1"));
			h.put("alamat2", getParam("txtAlamat2"));
			h.put("alamat3", getParam("txtAlamat3"));
			h.put("poskod", getParam("txtPoskod"));
			h.put("idDaerah", Integer.parseInt(getParam("socADaerah")));
			h.put("idNegeri", Integer.parseInt(getParam("socANegeri")));
			h.put("noTelefon", getParam("txtNoTelefon"));
			h.put("noFax", getParam("txtNoFax"));
			FrmPembelianPemilikData.simpan(h);
			result = "baru";
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
			h.put("idPihakberkepentingan", Integer.parseInt(getParam("idPihakberkepentingan")));
			h.put("namaPemaju", getParam("txtNamaPemaju"));
			h.put("idJenisNoPB", getParamAsInteger("socJenisNoPB"));
			h.put("noRuj", getParam("txtNoRuj"));
			h.put("alamat1", getParam("txtAlamat1"));
			h.put("alamat2", getParam("txtAlamat2"));
			h.put("alamat3", getParam("txtAlamat3"));
			h.put("poskod", getParam("txtPoskod"));
			h.put("idDaerah", Integer.parseInt(getParam("socADaerah")));
			h.put("idNegeri", Integer.parseInt(getParam("socANegeri")));
			h.put("noTelefon", getParam("txtNoTelefon"));
			h.put("noFax", getParam("txtNoFax"));
			FrmPembelianPemilikData.update(h);
			result = "kemaskini";
		}		
	}
	
	//*** Pembelian Peguam Controller
	private void ListPeguam(HttpSession session) throws Exception
	{
		int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
		FrmPembelianPeguamData.setListPeguam(""+idPermohonan);
	}
	
	private void DataPeguam(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPembelianPeguamData.getListPeguam();
			System.out.println("PembelianProcess::DataPeguam::list.size() "+list.size());
			if(list.size() != 0){
//				readability = "readonly";
//			    disability = "disabled";			    
			    
				this.context.put("Peguam", list);
				Hashtable peg = (Hashtable) list.get(0);
				System.out.println("PembelianProcess::DataPeguam::list "+list);	
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
			System.out.println("PembelianProcess::DataPeguam::Exception = "+ex);
		}
	}
	
	private void SimpanPeguam(HttpSession session) throws Exception
	{
		if(getParam("idPeguam") == ""){
			//fail baru
			Hashtable h = new Hashtable();
			h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
			h.put("nama", getParam("txtNamaPeguam"));
			h.put("kod", getParam("txtKodPeguam"));
			h.put("alamat1", getParam("txtAlamat1"));
			h.put("alamat2", getParam("txtAlamat2"));
			h.put("alamat3", getParam("txtAlamat3"));
			h.put("poskod", getParam("txtPoskod"));
			h.put("idDaerah", Integer.parseInt(getParam("socBDaerah")));
			h.put("idNegeri", Integer.parseInt(getParam("socBNegeri")));
			h.put("noTelefon", getParam("txtNoTelefon"));
			h.put("noFax", getParam("txtNoFax"));
			FrmPembelianPeguamData.simpan(h);
			result = "baru";
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
			h.put("idPeguam", Integer.parseInt(getParam("idPeguam")));
			h.put("nama", getParam("txtNamaPeguam"));
			h.put("kod", getParam("txtKodPeguam"));
			h.put("alamat1", getParam("txtAlamat1"));
			h.put("alamat2", getParam("txtAlamat2"));
			h.put("alamat3", getParam("txtAlamat3"));
			h.put("poskod", getParam("txtPoskod"));
			h.put("idDaerah", Integer.parseInt(getParam("socBDaerah")));
			h.put("idNegeri", Integer.parseInt(getParam("socBNegeri")));
			h.put("noTelefon", getParam("txtNoTelefon"));
			h.put("noFax", getParam("txtNoFax"));
			FrmPembelianPeguamData.update(h);
			result = "kemaskini";
		}
	}
	
	//*** Pembelian Semakan Controller
	public void setSemak(HttpSession session, int idPermohonan) throws Exception
	  {
		FrmPembelianSemakan1Data.setSemak(""+idPermohonan);
	  }
	
	private void ViewSemak(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPembelianSemakan1Data.getSemak();
		    this.context.put("Semak", list);
		    Hashtable h = (Hashtable) list.get(0);	
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()), "disabled"));
		    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()), "disabled"));
		    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("idAgensi").toString()), disability));
		    this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("2","socSuburusan",Long.parseLong(h.get("idSuburusan").toString()), "disabled"));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}catch(Exception ex){
			System.out.println("PembelianProcess::ListSemakan1::Exception = "+ex);
		}
	}
	
	private void SimpanSemak(HttpSession session) throws Exception
	{
//		int idPermohonan;
//		if(getParam("idPermohonan") == ""){
//			//fail baru
//			Hashtable h = new Hashtable();			
//			h.put("idNegeri", Integer.parseInt(getParam("socNegeri")));
//			h.put("idKementerian", Integer.parseInt(getParam("socKementerian")));
//			h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
//			h.put("idAgensi", Integer.parseInt(getParam("socAgensi")));			
//			h.put("Tajuk", getParam("txtTajuk"));
//			h.put("NoFailKJP", getParam("txtNoFailKJP"));
//			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
//			h.put("NoFailLain", getParam("txtNoFailLain"));
//			h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
//			System.out.println("PembelianProcess::SimpanSemak:: h = "+h);
//			idPermohonan = FrmPembelianSemakan1Data.simpan(h);
//			result = "baru";
//			return idPermohonan;
//		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
//			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			h.put("idFail", Integer.parseInt(getParam("idFail")));
			h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
//			h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
			h.put("idAgensi", Integer.parseInt(getParam("socAgensi")));			
			h.put("Tajuk", getParam("txtTajuk"));
			h.put("NoFailKJP", getParam("txtNoFailKJP"));
			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
			h.put("NoFailLain", getParam("txtNoFailLain"));
			h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
			System.out.println("PembelianProcess::SimpanSemak1:: h = "+h);
			FrmPembelianSemakan1Data.update(h);
			result = "kemaskini";
//			return idPermohonan;
//		}
	}
	
	//*** Pembelian Tanah Controller
	public void setTanah(HttpSession session, int idPermohonan, long idDaerah, long idMukim) throws Exception
	  {
		FrmPembelianTanahData.setTanah(idPermohonan,idDaerah,idMukim);
		FrmPembelianTanahData.setUlasan(idPermohonan);
	  }
	
	private void ViewTanah(HttpSession session, long idNegeri, long idDaerah, long idMukim) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPembelianTanahData.getTanah();
			if(list.size() == 0)
				this.context.put("Tanah", "");
			else
				this.context.put("Tanah", list);
		    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(""+idNegeri,"socDaerah",idDaerah,null));
		    this.context.put("selectMukim",HTML.SelectMukim("socMukim",idMukim,null));
		    this.context.put("Daerah", idDaerah);
		    this.context.put("Mukim", idMukim);
		}catch(Exception ex){
			System.out.println("PembelianProcess::Tanah::Exception = "+ex);
		}
	}
	
	private void ViewTanahUlasan(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector listUlasan = new Vector();
		listUlasan.clear();
		try{
		    listUlasan = FrmPembelianTanahData.getUlasan();
		    if(listUlasan.size() == 0){
		    	this.context.put("IdUlasankptg", "");
				this.context.put("TarikhHantar", "");
				this.context.put("Syarat", "");
				this.context.put("Ulasan", "");
			    this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			    System.out.println("PembelianProcess::TanahUlasan::null");
		    }else{
		    	Hashtable h = (Hashtable) listUlasan.get(0);
		    	this.context.put("IdUlasankptg", h.get("IdUlasankptg").toString());
				this.context.put("TarikhHantar", h.get("TarikhHantar").toString());
				this.context.put("Syarat", h.get("Syarat").toString());
				this.context.put("Ulasan", h.get("Ulasan").toString());
				this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			    System.out.println("PembelianProcess::TanahUlasan::not null");
		    }
		}catch(Exception ex){
			System.out.println("PembelianProcess::TanahUlasan::Exception = "+ex);
		}
	}
	
	private void SimpanTanahUlasan(HttpSession session) throws Exception
	{
		if(getParam("idUlasankptg") == ""){
//			//fail baru
			Hashtable h = new Hashtable();	
			h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
			h.put("tarikhHantar", getParam("txdTarikhHantar"));
			h.put("syarat", getParam("socPerakuan"));
			h.put("ulasan", getParam("Ulasan"));
			FrmPembelianTanahData.simpanUlasan(h);
			result = "baru";

		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
			h.put("idUlasankptg", Integer.parseInt(getParam("idUlasankptg")));
			h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
			h.put("tarikhHantar", getParam("txdTarikhHantar"));
			h.put("syarat", getParam("socPerakuan"));
			h.put("ulasan", getParam("Ulasan"));
			FrmPembelianTanahData.updateUlasan(h);
			result = "kemaskini";

		}
	}
	
	//*** Pembelian Tanah Bangunan Controller
	public void setTBangun(HttpSession session, int idPermohonan, long idDaerah, long idMukim) throws Exception
	  {
		FrmPembelianTBangunData.setTBangun(idPermohonan,idDaerah,idMukim);
	  }
	
	private void ViewTBangun(HttpSession session, long idNegeri, long idDaerah, long idMukim) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPembelianTBangunData.getTBangun();
			if(list.size() == 0)
				this.context.put("TBangun", "");
			else
				this.context.put("TBangun", list);
		    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(""+idNegeri,"socDaerahB",idDaerah,null));
		    this.context.put("selectMukim",HTML.SelectMukim("socMukimB",idMukim,null));
		    this.context.put("DaerahB", idDaerah);
		    this.context.put("MukimB", idMukim);
		}catch(Exception ex){
			System.out.println("PembelianProcess::TBangun::Exception = "+ex);
		}
	}
}
