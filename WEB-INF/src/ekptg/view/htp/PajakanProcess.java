package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmPajakanDrafData;
import ekptg.model.htp.FrmPajakanMJMData;
import ekptg.model.htp.FrmPajakanPemohonData;
import ekptg.model.htp.FrmPajakanSemakanData;
import ekptg.model.htp.FrmPajakanUlasanData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailPajakanData;

public class PajakanProcess extends AjaxBasedModule  {
	private String result = new String();
	public String doTemplate2() throws Exception
    {
		String vm = "";
		HttpSession session = this.request.getSession();
		Vector list = new Vector();
		String disability = "";
    	String readability = "";
    	String style1 = "";
    	String style2 = "";
    	String style3 = "";
    	this.context.put("SimpanStatus", "");
    	list.clear();
		String submit = getParam("commander");
		this.context.put("commander", submit);
		String mode = getParam("mode");
		this.context.put("moded", mode);
		
		String selectedTab = new String();
        selectedTab = getParam("tabId").toString();
        if (selectedTab == null || "".equals(selectedTab) ) {
           selectedTab="0";
        }
		System.out.println("submit:"+submit);
		System.out.println("mode:"+mode);
		
		if("FailBaru".equals(submit)){
			vm = "app/htp/frmPajakanSemakan.jsp";
			int idPermohonan = 0;
			this.context.put("IdPermohonan", getParam("idPermohonan"));	
			
			this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPajakanSemakan"));
//		    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
			this.context.put("semakclass", new FrmSemakan());

			if("view".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    idPermohonan = Integer.parseInt(getParam("idPermohonan").toString());
			    setFailBaru(session,idPermohonan);
			    style2 = "none";
				ViewFailBaru(session,disability,readability,style1,style2);
			}else if("kemaskini".equals(mode)){
				idPermohonan = Integer.parseInt(getParam("idPermohonan").toString());
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
			}else if("baru".equals(mode)){
				style1 = "none";
				DataFailBaruBatal(session, disability, readability, style1, style2);
			}else{
				style1 = "none";
				DataFailBaru(session, disability, readability, style1, style2);
			}
			this.context.put("NoHakmilik","");
			System.out.println("PajakanProcess::FailBaru");
		}else if("Hakmilik".equals(submit)){
			vm = "app/htp/frmPajakanSemakan.jsp";
			this.context.put("selectedTab","1");
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			this.context.put("IdPermohonan", idPermohonan);
		    this.context.put("IdFail", getParam("idFail"));
		    
		    //header of page semakan under tab hakmilik
		    readability = "readonly";
		    disability = "disabled";
		    setFailBaru(session,idPermohonan);
		    style2 = "none";
			ViewFailBaru(session,disability,readability,style1,style2);
			if("cari".equals(mode)){
				style1 = "none";
				style2 = "";
				setCariHakmilik(idPermohonan);
				getCariHakmilik(session, disability, readability, style1, style2);
			}else{
				style1 = "";
				style2 = "none";
				setListHakmilik(idPermohonan);
				getListHakmilik(session, disability, readability, style1, style2);
			}
			System.out.println("PajakanProcess::Hakmilik");
		}else if ("Pemohon".equals(submit)){
			vm = "app/htp/frmPajakanMaklumat.jsp";
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			this.context.put("IdPermohonan", idPermohonan);
			
			if(getParam("idHakmilik") != ""){
				setCopyDataTable(idPermohonan, Integer.parseInt(getParam("idHakmilik").toString()));
				System.out.println("PajakanProcess::Pemohon::idHakmilik = "+getParam("idHakmilik").toString());
			}
			
			//info page
			readability = "readonly";
		    disability = "disabled";
		    setFailBaru(session,idPermohonan);
			ViewFailBaru(session,disability,readability,style1,style2);

			if("pemohonview".equals(mode)){
//				this.context.put("IdHakmilikurusan", getParam("idHakmilikurusan"));
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
				ListPemohon(session,idPermohonan);
				DataPemohon(session,disability,readability,style1,style2,style3);
			}else if("pemohonkemaskini".equals(mode)){
				readability = "";
			    disability = "";
			    style1 = "none";
				ListPemohon(session,idPermohonan);
				DataPemohon(session,disability,readability,style1,style2,style3);
			}else if("pemohonsimpan".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
			    SimpanPemohon(session);
				ListPemohon(session,idPermohonan);
				DataPemohon(session,disability,readability,style1,style2,style3);
			}else if("pemohonhapus".equals(mode)){
				
			}			
			System.out.println("PajakanProcess::Pemohon");
		}else if ("Ulasan".equals(submit)){
			vm = "app/htp/frmPajakanMaklumat.jsp";
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			this.context.put("IdPermohonan", idPermohonan);
			
			//info page
			readability = "readonly";
		    disability = "disabled";
		    setFailBaru(session,idPermohonan);
			ViewFailBaru(session,disability,readability,style1,style2);
			
			if("ulasankjpview".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
			    setUlasanKJP(session,idPermohonan);
				getUlasanKJP(session,disability,readability,style1,style2,style3);
//				setUlasanJPPH(session, idPermohonan);
				getUlasanJPPH(session, disability, readability, style1, style2, style3);				
			}else if("ulasankjpkemaskini".equals(mode)){
				readability = "";
			    disability = "";
			    style1 = "none";
				setUlasanKJP(session,idPermohonan);
				getUlasanKJP(session,disability,readability,style1,style2,style3);
//				setUlasanJPPH(session, idPermohonan);
//				getUlasanJPPH(session, disability, readability, style1, style2, style3);
			}else if("ulasankjpsimpan".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
			    SimpanKJP(session,idPermohonan);
				setUlasanKJP(session,idPermohonan);
				getUlasanKJP(session,disability,readability,style1,style2,style3);
//				setUlasanJPPH(session, idPermohonan);
//				getUlasanJPPH(session, disability, readability, style1, style2, style3);
			}else if("ulasankjphapus".equals(mode)){
				
			}else if("ulasanjpphview".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
				setUlasanJPPH(session, idPermohonan);
				getUlasanJPPH(session, disability, readability, style1, style2, style3);
			}else if("ulasanjpphkemaskini".equals(mode)){
				
			}else if("ulasanjpphsimpan".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
			    SimpanJPPH(session, idPermohonan);
//				setUlasanKJP(session,idPermohonan);
//				getUlasanKJP(session,disability,readability,style1,style2,style3);
				setUlasanJPPH(session, idPermohonan);
				getUlasanJPPH(session, disability, readability, style1, style2, style3);
			}else if("ulasanjpphhapus".equals(mode)){
				
			}
			System.out.println("PajakanProcess::Ulasan");
		}else if("Draf".equals(submit)){
			vm = "app/htp/frmPajakanMaklumat.jsp";
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			this.context.put("IdPermohonan", idPermohonan);
			
			//info page
			readability = "readonly";
		    disability = "disabled";
		    setFailBaru(session,idPermohonan);
			ViewFailBaru(session,disability,readability,style1,style2);
			
			if("drafview".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
				setListDraf(session,idPermohonan);
				getListDraf(session,disability,readability,style1,style2,style3);
			}else if("drafkemaskini".equals(mode)){
				
			}else if("drafsimpan".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
			    SimpanDraf(session,idPermohonan);
				setListDraf(session,idPermohonan);
				getListDraf(session,disability,readability,style1,style2,style3);
			}else if("drafhapus".equals(mode)){
				
			}
		}else if("MJM".equals(submit)){
			vm = "app/htp/frmPajakanMaklumat.jsp";
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			this.context.put("IdPermohonan", idPermohonan);
			
			//info page
			readability = "readonly";
		    disability = "disabled";
		    setFailBaru(session,idPermohonan);
			ViewFailBaru(session,disability,readability,style1,style2);
			
			if("mjmview".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
				setListMJM(session,idPermohonan);
				getListMJM(session,disability,readability,style1,style2,style3);
			}else if("mjmkemaskini".equals(mode)){
				readability = "";
			    disability = "";
			    style1 = "none";
				setListMJM(session,idPermohonan);
				getListMJM(session,disability,readability,style1,style2,style3);
			}else if("mjmsimpan".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
			    SimpanMJM(session,idPermohonan);
				setListMJM(session,idPermohonan);
				getListMJM(session,disability,readability,style1,style2,style3);
			}else if("mjmhapus".equals(mode)){
				
			}
		}else{
			vm = "app/htp/frmSenaraiFailPajakan.jsp";
			String key_cari = getParam("NamaFail");
			String keyNo_cari = getParam("NoFail");
			String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
			ListFail(session, key_cari, keyNo_cari, idNegeri);
	    	list = FrmSenaraiFailPajakanData.getList();
	    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,disability));
		    this.context.put("Senaraifail", list);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
		    this.context.put("Negeri", idNegeri);
		    System.out.println("PajakanProcess::SenaraiFail");
		}
		this.context.put("selectedTab",selectedTab);
        System.out.println("PajakanProcess");
		return vm;
    }
	
	//*** Senarai Fail Pajakan Controller
	public void ListFail(HttpSession session, String key_cari, String keyNo_cari, Long idNegeri) throws Exception
	  {
		FrmSenaraiFailPajakanData.setList(key_cari,keyNo_cari,idNegeri);
	  }
	
	private Date now = new Date();
    private SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
	
	//*** Fail Baru Controller
    private Vector tempDataBaru(){
    	Vector temp = new Vector();
    	Hashtable h;
    	try{
    		h = new Hashtable();
    		h.put("idNegeri", getParam("socNegeri")==""?"0":getParam("socNegeri"));
    		h.put("idKementerian", getParam("socKementerian")==""?"0":getParam("socKementerian"));
    		h.put("idAgensi", getParam("socAgensi")==""?"0":getParam("socAgensi"));
//    		h.put("idSuburusan", getParam("socSuburusan")==""?"0":getParam("socSuburusan"));
    		h.put("idJenisTanah", getParam("socJenisTanah")==""?"0":getParam("socJenisTanah"));
    		h.put("tajuk", getParam("txtTajuk"));
			h.put("noRujukanKJP", getParam("txtNoFailKJP"));
			h.put("tSurat", getParam("txdTarikhSurKJP"));
			h.put("noRujukan", getParam("txtNoFailLain"));
			h.put("tPermohonan", getParam("txdTarikhPermohonan"));
			System.out.println("tempDataBaru::h="+h);
    		temp.addElement(h);
    	}catch (Exception ex){
    		ex.printStackTrace();
    	}
    	return temp;
    }
    
	private void DataFailBaru(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		String submit = getParam("command");
		Vector temp = new Vector();
		try{
			temp = tempDataBaru();
			Hashtable h = (Hashtable) temp.get(0);	
			this.context.put("Semak", temp);
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(h.get("idNegeri").toString()),""));
		    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(h.get("idKementerian").toString()),""," onChange=\"doChangeKementerian();\" "));
		    if("doChangeKementerian".equals(submit))
		    	this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", h.get("idKementerian").toString(), Utils.parseLong(h.get("idAgensi").toString()), disability));
		    else
		    	this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi"));
		    this.context.put("selectUrusan",HTML.SelectUrusan("socSuburusan",Long.parseLong("3"),"disabled"));
		    this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3","socSuburusan",Long.parseLong("7"),"disabled"));
		    this.context.put("selectJenisTanah",HTML.SelectJenisTanah("socJenisTanah",Long.parseLong(h.get("idJenisTanah").toString()),disability));
		    this.context.put("datenow", formatter.format(now));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}catch(Exception ex){
			System.out.println("PajakanProcess::DataFailBaru::Exception = "+ex);
			ex.printStackTrace();
		}
	}
	
	private void DataFailBaruBatal(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		String submit = getParam("command");
		Vector temp = new Vector();
		try{
//			temp = tempDataBaru();
//			Hashtable h = (Hashtable) temp.get(0);	
			this.context.put("Semak", temp);
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,""));
		    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",null,""," onChange=\"doChangeKementerian();\" "));
		    if("doChangeKementerian".equals(submit))
		    	this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", "", null, disability));
		    else
		    	this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi"));
		    this.context.put("selectUrusan",HTML.SelectUrusan("socSuburusan",Long.parseLong("3"),"disabled"));
		    this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3","socSuburusan",Long.parseLong("7"),"disabled"));
		    this.context.put("selectJenisTanah",HTML.SelectJenisTanah("socJenisTanah",null,disability));
		    this.context.put("datenow", formatter.format(now));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}catch(Exception ex){
			System.out.println("PajakanProcess::DataFailBaru::Exception = "+ex);
			ex.printStackTrace();
		}
	}
	
	public void setFailBaru(HttpSession session, int idPermohonan) throws Exception
	  {
		//FrmPajakanSemakanData.setSemak(idPermohonan);
	  }
	
	private void ViewFailBaru(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPajakanSemakanData.getSemak();
		    this.context.put("Semak", list);
		    Hashtable h = (Hashtable) list.get(0);	
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()), "disabled"));
		    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()), "disabled"));
		    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("idAgensi").toString()), disability));
		    this.context.put("selectUrusan",HTML.SelectUrusan("socSuburusan",Long.parseLong("3"),"disabled"));
		    this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3","socSuburusan",Long.parseLong(h.get("idSuburusan").toString()), "disabled"));
		    this.context.put("selectJenisTanah",HTML.SelectJenisTanah("socJenisTanah",Long.parseLong(h.get("idJenistanah").toString()),disability));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}catch(Exception ex){
			System.out.println("PajakanProcess::ListFailBaru::Exception = "+ex);
			ex.printStackTrace();
		}
	}
	
	private int SimpanFailBaru(HttpSession session) throws Exception
	{
		int idPermohonan=0;
		if(getParam("idPermohonan") == ""){
			//fail baru
			Hashtable h = new Hashtable();			
			h.put("idNegeri", Integer.parseInt(getParam("socNegeri")));
			h.put("idKementerian", Integer.parseInt(getParam("socKementerian")));
			h.put("idJenistanah", getParam("socJenisTanah"));
			h.put("idAgensi", Integer.parseInt(getParam("socAgensi")));			
			h.put("Tajuk", getParam("txtTajuk"));
			h.put("NoFailKJP", getParam("txtNoFailKJP"));
			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
			h.put("NoFailLain", getParam("txtNoFailLain"));
			h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
			System.out.println("PajakanProcess::SimpanSemak:: h = "+h);
			//idPermohonan = FrmPajakanSemakanData.simpan(h);
			result = "baru";
			return idPermohonan;
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
//			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			h.put("idFail", Integer.parseInt(getParam("idFail")));
			h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
			h.put("idJenistanah", getParam("socJenisTanah"));
			h.put("idAgensi", Integer.parseInt(getParam("socAgensi")));			
			h.put("Tajuk", getParam("txtTajuk"));
			h.put("NoFailKJP", getParam("txtNoFailKJP"));
			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
			h.put("NoFailLain", getParam("txtNoFailLain"));
			h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
			System.out.println("PajakanProcess::SimpanSemak:: h = "+h);
			//idPermohonan = FrmPajakanSemakanData.update(h);
			result = "kemaskini";
			return idPermohonan;
		}
	}
	
	//*** Hakmilik tab's under semakan page
	private Vector tempDataCari(){
    	Vector temp = new Vector();
    	Hashtable h;
    	try{
    		String daerah="",mukim="",jenishakmilik="",nohakmilik="";
    		
    		daerah = getParam("socDaerah")==""?"":getParam("socDaerah");
    		mukim = getParam("socMukim")==""?"":getParam("socMukim");
    		jenishakmilik = getParam("socJenishakmilik")==""?"":getParam("socJenishakmilik");
    		nohakmilik = getParam("txtNoHakmilik")==""?"":getParam("txtNoHakmilik");
    		
    		h = new Hashtable();
    		h.put("idDaerah", daerah);
    		h.put("idMukim", mukim);
    		h.put("idJenishakmilik", jenishakmilik);
    		h.put("noHakmilik", nohakmilik);
			System.out.println("tempDataCari::h="+h);
    		temp.addElement(h);
    	}catch (Exception ex){
    		ex.printStackTrace();
    	}
    	return temp;
    }
	
	private void setListHakmilik(int idPermohonan){
		try{
			//FrmPajakanSemakanData.setListHakmilik(idPermohonan);
		}catch(Exception ex){
			System.out.println("PajakanProcess::setListHakmilik::Exception = "+ex);
			ex.printStackTrace();
		}
	}

	private void getListHakmilik(HttpSession session, String disability, String readability, String style1, String style2) throws Exception{
		String submit = getParam("command");
		Vector temp = new Vector();
		Vector tempNegeri = new Vector();
//		Vector carianHakmilik = new Vector();
		try{
			tempNegeri = FrmPajakanSemakanData.getSemak();
			Hashtable hNegeri = (Hashtable) tempNegeri.get(0);	
			temp = tempDataCari();
			Hashtable h = (Hashtable) temp.get(0);	
		    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(hNegeri.get("idNegeri").toString(), "socDaerah", Utils.parseLong(h.get("idDaerah").toString()), ""," onChange=\"doChangeDaerah();\" "));
		    if("doChangeDaerah".equals(submit))
		    	this.context.put("selectMukim",HTML.SelectMukimByDaerah(h.get("idDaerah").toString(), "socMukim", Utils.parseLong(h.get("idMukim").toString()), ""));
		    else
		    	this.context.put("selectMukim",HTML.SelectMukim("socMukim",Utils.parseLong(h.get("idMukim").toString()), ""));
		    this.context.put("selectJenishakmilik",HTML.SelectJenisHakmilik("socJenishakmilik", Utils.parseLong(h.get("idJenishakmilik").toString()), ""));
		    this.context.put("NoHakmilik",h.get("noHakmilik").toString());
		    temp = FrmPajakanSemakanData.getListHakmilik();
		    this.context.put("SenaraiHakmilik", temp);
		    this.context.put("style1", style1);
		    this.context.put("style2", style2);
		}catch(Exception ex){
			System.out.println("PajakanProcess::getListHakmilik::Exception = "+ex);
			ex.printStackTrace();
		}
	}
	
	private void setCariHakmilik(int idPermohonan){
		Vector temp = new Vector();
		try{
			temp = tempDataCari();
			Hashtable h = (Hashtable) temp.get(0);	
			int idDaerah,idMukim,idJenishakmilik;
			if(h.get("idDaerah") == "")
				idDaerah = 0;
			else
				idDaerah = Integer.parseInt(h.get("idDaerah").toString());
			if(h.get("idMukim") == "")
				idMukim = 0;
			else
				idMukim = Integer.parseInt(h.get("idMukim").toString());
			if(h.get("idJenishakmilik") == "")
				idJenishakmilik = 0;
			else
				idJenishakmilik = Integer.parseInt(h.get("idJenishakmilik").toString());
			
			//FrmPajakanSemakanData.setCariHakmilik(idPermohonan, idDaerah, idMukim, idJenishakmilik, h.get("noHakmilik").toString());
		}catch(Exception ex){
			System.out.println("PajakanProcess::setCariHakmilik::Exception = "+ex);
			ex.printStackTrace();
		}
	}

	private void getCariHakmilik(HttpSession session, String disability, String readability, String style1, String style2) throws Exception{
		String submit = getParam("command");
		Vector temp = new Vector();
		Vector tempNegeri = new Vector();
//		Vector carianHakmilik = new Vector();
		try{
			tempNegeri = FrmPajakanSemakanData.getSemak();
			Hashtable hNegeri = (Hashtable) tempNegeri.get(0);	
			temp = tempDataCari();
			Hashtable h = (Hashtable) temp.get(0);	
		    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(hNegeri.get("idNegeri").toString(), "socDaerah", Utils.parseLong(h.get("idDaerah").toString()), ""," onChange=\"doChangeDaerah();\" "));
		    if("doChangeDaerah".equals(submit))
		    	this.context.put("selectMukim",HTML.SelectMukimByDaerah(h.get("idDaerah").toString(), "socMukim", Utils.parseLong(h.get("idMukim").toString()), ""));
		    else
		    	this.context.put("selectMukim",HTML.SelectMukim("socMukim",Utils.parseLong(h.get("idMukim").toString()), ""));
		    this.context.put("selectJenishakmilik",HTML.SelectJenisHakmilik("socJenishakmilik", Utils.parseLong(h.get("idJenishakmilik").toString()), ""));
		    this.context.put("NoHakmilik",h.get("noHakmilik").toString());
		    temp = FrmPajakanSemakanData.getCariHakmilik();
		    this.context.put("SenaraiHakmilik", temp);
		    this.context.put("style1", style1);
		    this.context.put("style2", style2);
		}catch(Exception ex){
			System.out.println("PajakanProcess::getCariHakmilik::Exception = "+ex);
			ex.printStackTrace();
		}
	}
	
	//*** pemohon pajakan tab's
	private void setCopyDataTable(int idPermohonan, int idHakmilik) throws Exception{
		//FrmPajakanPemohonData.copySaveData(idPermohonan, idHakmilik);
	}
	
	private Vector tempPemohonData(){
    	Vector tempPemohon = new Vector();
    	Hashtable h;
    	try{
    		String daerah="",negeri="",namapemohon="",nopemohon="",alamat1="",alamat2="",alamat3="",poskod="",notel="",nofax="";
    		
    		namapemohon = getParam("txtNamaPemohon")==""?"":getParam("txtNamaPemohon");
    		nopemohon = getParam("txtNoPemohon")==""?"":getParam("txtNoPemohon");
    		alamat1 = getParam("txtAlamat1")==""?"":getParam("txtAlamat1");
    		alamat2 = getParam("txtAlamat2")==""?"":getParam("txtAlamat2");
    		alamat3 = getParam("txtAlamat3")==""?"":getParam("txtAlamat3");
    		poskod = getParam("txtPoskod")==""?"":getParam("txtPoskod");
    		daerah = getParam("socADaerah")==""?"":getParam("socADaerah");
    		negeri = getParam("socANegeri")==""?"":getParam("socANegeri");
    		notel = getParam("txtNoTelefon")==""?"":getParam("txtNoTelefon");
    		nofax = getParam("txtNoFax")==""?"":getParam("txtNoFax");
    		
    		h = new Hashtable();
    		h.put("NamaPemohon", namapemohon);
    		h.put("NoPemohon", nopemohon);
    		h.put("Alamat1", alamat1);
    		h.put("Alamat2", alamat2);
    		h.put("Alamat3", alamat3);
    		h.put("Poskod", poskod);
    		h.put("IdDaerah", daerah);
    		h.put("IdNegeri", negeri);
    		h.put("NoTel", notel);
    		h.put("NoFax", nofax);
			System.out.println("tempPemohonData::h="+h);
			tempPemohon.addElement(h);
    	}catch (Exception ex){
    		ex.printStackTrace();
    	}
    	return tempPemohon;
    }
	
	private void ListPemohon(HttpSession session, int idPermohonan) throws Exception
	{
		//FrmPajakanPemohonData.setListPemohon(idPermohonan);
	}
	
	private void DataPemohon(HttpSession session, String disability, String readability, String style1, String style2, String style3) throws Exception
	{
		String submit = getParam("command");
		Vector list = new Vector();
		Vector temp = new Vector();
		list.clear();
		try{
			list = FrmPajakanPemohonData.getListPemohon();
			System.out.println("PajakanProcess::list.size() "+list.size());			
			if(list.size() != 0){			    
				System.out.println("PajakanProcess::DataPemohon::list "+list);
				this.context.put("Pemohon", list);
				Hashtable hak = (Hashtable) list.get(0);
				this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri",Utils.parseLong(hak.get("IdNegeri").toString()),disability," onChange=\"doChangeNegeri();\" "));
				if("doChangeNegeri".equals(submit))
					this.context.put("selectADaerah",HTML.SelectDaerahByNegeri(hak.get("IdNegeri").toString(), "socADaerah",Utils.parseLong(hak.get("IdDaerah").toString()),disability));				
				else
					this.context.put("selectADaerah",HTML.SelectDaerah("socADaerah", Utils.parseLong(hak.get("IdDaerah").toString()), disability));
				this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			    this.context.put("Style3", style3);
			}else{
				temp = tempPemohonData();
				this.context.put("Pemohon", temp);
				Hashtable h = (Hashtable) temp.get(0);
//				if(h.get("idNegeri") != "")
					this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri",Utils.parseLong(h.get("IdNegeri").toString())," onChange=\"doChangeNegeri();\" "));
//				else
//					this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri"," onChange=\"doChangeNegeri();\" "));
				if("doChangeNegeri".equals(submit))
					this.context.put("selectADaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socADaerah",Utils.parseLong(h.get("IdDaerah").toString()),""));				
				else
					this.context.put("selectADaerah",HTML.SelectDaerah("socADaerah"));				
				this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			    this.context.put("Style3", style3);
			}
		}catch(Exception ex){
			System.out.println("PajakanProcess::DataPmohon::Exception = "+ex);
			ex.printStackTrace();
		}
	}
	
	private void SimpanPemohon(HttpSession session) throws Exception
	{
		if(getParam("idPemohon") == ""){
			//fail baru
			Hashtable h = new Hashtable();
			h.put("idPermohonan", getParamAsInteger("idPermohonan"));
			h.put("noPemohon", getParam("txtNoPemohon"));
			h.put("Nama", getParam("txtNamaPemohon"));
			h.put("alamat1", getParam("txtAlamat1"));
			h.put("alamat2", getParam("txtAlamat2"));
			h.put("alamat3", getParam("txtAlamat3"));
			h.put("poskod", getParam("txtPoskod"));
			h.put("idDaerah", Integer.parseInt(getParam("socADaerah")));
			h.put("idNegeri", Integer.parseInt(getParam("socANegeri")));
			h.put("noTelefon", getParam("txtNoTelefon"));
			h.put("noFax", getParam("txtNoFax"));
			FrmPajakanPemohonData.simpan(h);
			result = "baru";
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
			h.put("idPemohon", getParamAsInteger("idPemohon"));
			h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
			h.put("noPemohon", getParam("txtNoPemohon"));
			h.put("Nama", getParam("txtNamaPemohon"));
			h.put("alamat1", getParam("txtAlamat1"));
			h.put("alamat2", getParam("txtAlamat2"));
			h.put("alamat3", getParam("txtAlamat3"));
			h.put("poskod", getParam("txtPoskod"));
			h.put("idDaerah", Integer.parseInt(getParam("socADaerah")));
			h.put("idNegeri", Integer.parseInt(getParam("socANegeri")));
			h.put("noTelefon", getParam("txtNoTelefon"));
			h.put("noFax", getParam("txtNoFax"));
			FrmPajakanPemohonData.update(h);
			result = "kemaskini";
		}		
	}
	
	//*** ulasan tab's 
	private void setUlasanKJP(HttpSession session, int idPermohonan) throws Exception
	{
		//FrmPajakanUlasanData.setUlasanKJP(idPermohonan);
	}
	
	private void getUlasanKJP(HttpSession session, String disability, String readability, String style1, String style2, String style3) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPajakanUlasanData.getUlasanKJP();
			System.out.println("PajakanProcess::listKJP.size() "+list.size());			
			if(list.size() != 0){			    
				System.out.println("PajakanProcess::getUlasanKJP::list "+list);
				this.context.put("UlasanKJP", list);
				this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			    this.context.put("Style3", style3);
			}else{
				this.context.put("UlasanKJP", "");
				this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			    this.context.put("Style3", style3);
			}
		}catch(Exception ex){
			System.out.println("PajakanProcess::getUlasanKJP::Exception = "+ex);
			ex.printStackTrace();
		}
	}
	
	private void SimpanKJP(HttpSession session, int idPermohonan) throws Exception
	{
		if(getParam("idUlasanKJP") == ""){
			//fail baru
			Hashtable h = new Hashtable();
			h.put("idPermohonan", idPermohonan);
			h.put("tarikhHantar", getParam("txdTarikhHantarKJP"));
			h.put("tarikhTerima", getParam("txdTarikhTerimaKJP"));
			h.put("ulasan", getParam("txtUlasanKJP"));
			FrmPajakanUlasanData.simpanKJP(h);
			result = "baru";
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
			h.put("idUlasankjp", getParamAsInteger("idUlasanKJP"));
			h.put("idPermohonan", idPermohonan);
			h.put("tarikhHantar", getParam("txdTarikhHantarKJP"));
			h.put("tarikhTerima", getParam("txdTarikhTerimaKJP"));
			h.put("ulasan", getParam("txtUlasanKJP"));
			FrmPajakanUlasanData.updateKJP(h);
			result = "kemaskini";
		}		
	}
	
	private void setUlasanJPPH(HttpSession session, int idPermohonan) throws Exception
	{
		//FrmPajakanUlasanData.setListUlasanJPPH(idPermohonan);
	}
	
	private void getUlasanJPPH(HttpSession session, String disability, String readability, String style1, String style2, String style3) throws Exception
	{
		Vector listJPPH = new Vector();
		listJPPH.clear();
		try{
			listJPPH = FrmPajakanUlasanData.getListUlasanJPPH();
			System.out.println("PajakanProcess::listJPPH.size() "+listJPPH.size());
			if(listJPPH.size() != 0){			
				System.out.println("PajakanProcess::getUlasanJPPH::list "+listJPPH);
				Hashtable HashJPPH = (Hashtable) listJPPH.get(0);
				this.context.put("ListJPPH", listJPPH);
				this.context.put("idUlasanteknikal", HashJPPH.get("idUlasanteknikal"));
//				this.context.put("modeSoc", disability);
//			    this.context.put("mode", readability);
//			    this.context.put("Style1", style1);
//			    this.context.put("Style2", style2);
//			    this.context.put("Style3", style3);
			}else{
				this.context.put("ListJPPH", "");
				this.context.put("idUlasanteknikal", "");
				this.context.put("senaraiPegawai",HTML.SelectPegawai("socPegawai"));
//				this.context.put("modeSoc", disability);
//			    this.context.put("mode", readability);
//			    this.context.put("Style1", style1);
//			    this.context.put("Style2", style2);
//			    this.context.put("Style3", style3);
			}
		}catch(Exception ex){
			System.out.println("PajakanProcess::getUlasanJPPH::Exception = "+ex);
			ex.printStackTrace();
		}
	}
	
	private void SimpanJPPH(HttpSession session, int idPermohonan) throws Exception
	{
		if(getParam("idUlasanteknikal") == ""){
			//fail baru
			Hashtable h = new Hashtable();
			h.put("idPermohonan", idPermohonan);
			h.put("noRujKJT", getParam("txtNoRujJPPH"));
			h.put("tarikhHantarJPPH", getParam("txdTarikhHantarJPPH"));
			h.put("tarikhTerimaJPPH", getParam("txdTarikhTerimaJPPH"));
			h.put("idPegawai", getParamAsInteger("socPegawai"));
			h.put("tahunNilai", getParamAsInteger("socTahunNilaian"));
			h.put("nilaiTanah", getParam("txtNilaiTanah"));
			h.put("syorBayaran", getParam("txtSyorBayaran"));
			h.put("keterangan", getParam("txtKeteranganJPPH"));
			FrmPajakanUlasanData.simpanFirstJPPH(h);
			result = "baru";			
		}else{
			if(getParam("idUlasannilai") == ""){
				//fail baru
				Hashtable h = new Hashtable();
				h.put("idPermohonan", idPermohonan);
				h.put("tahunNilai", getParamAsInteger("socTahunNilaian"));
				h.put("nilaiTanah", getParam("txtNilaiTanah"));
				h.put("syorBayaran", getParam("txtSyorBayaran"));
				h.put("keterangan", getParam("txtKeteranganJPPH"));
				FrmPajakanUlasanData.simpanJPPH(h);
				result = "baru";
			}else{
				//kemaskini fail
				Hashtable h = new Hashtable();
				h.put("idUlasannilai", getParamAsInteger("idUlasannilai"));
				h.put("idPermohonan", idPermohonan);
				h.put("tahunNilai", getParamAsInteger("socTahunNilaian"));
				h.put("nilaiTanah", getParam("txtNilaiTanah"));
				h.put("syorBayaran", getParam("txtSyorBayaran"));
				h.put("keterangan", getParam("txtKeteranganJPPH"));
				FrmPajakanUlasanData.updateJPPH(h);
				result = "kemaskini";
			}	
		}
	}
	
	//*** draf tab's
	private void setListDraf(HttpSession session, int idPermohonan) throws Exception
	{
		//FrmPajakanDrafData.setListDraf(idPermohonan);
	}
	
	private void getListDraf(HttpSession session, String disability, String readability, String style1, String style2, String style3) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPajakanDrafData.getListDraf();
			System.out.println("PajakanProcess::list.size() "+list.size());			
			if(list.size() != 0){			    
				System.out.println("PajakanProcess::getListDraf::list "+list);
				this.context.put("ListDraf", list);
				this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			    this.context.put("Style3", style3);
			}else{
				this.context.put("ListDraf", "");
				this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			    this.context.put("Style3", style3);
			}
		}catch(Exception ex){
			System.out.println("PajakanProcess::getListDraf::Exception = "+ex);
			ex.printStackTrace();
		}
	}
	
	private void SimpanDraf(HttpSession session, int idPermohonan) throws Exception
	{
		if(getParam("idDokumenPerjanjian") == ""){
			//fail baru
			Hashtable h = new Hashtable();
			h.put("idPermohonan", idPermohonan);
			h.put("tarikhHantar", getParam("txdTarikhHantarDraf"));
			h.put("tarikhTerima", getParam("txdTarikhTerimaDraf"));
			h.put("ulasan", getParam("txtUlasanDraf"));
			FrmPajakanDrafData.simpanDraf(h);
			result = "baru";
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
			h.put("idDokumenperjanjian", getParamAsInteger("idDokumenPerjanjian"));
			h.put("idPermohonan", idPermohonan);
			h.put("tarikhHantar", getParam("txdTarikhHantarDraf"));
			h.put("tarikhTerima", getParam("txdTarikhTerimaDraf"));
			h.put("ulasan", getParam("txtUlasanDraf"));
			FrmPajakanDrafData.updateDraf(h);
			result = "kemaskini";
		}		
	}
	
	//*** MJM tab's
	private void setListMJM(HttpSession session, int idPermohonan) throws Exception
	{
		//FrmPajakanMJMData.setListMJM(idPermohonan);
	}
	
	private void getListMJM(HttpSession session, String disability, String readability, String style1, String style2, String style3) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPajakanMJMData.getListMJM();
			System.out.println("PajakanProcess::list.size() "+list.size());			
			if(list.size() != 0){			    
				System.out.println("PajakanProcess::getListMJM::list "+list);
				this.context.put("ListMJM", list);
				this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			    this.context.put("Style3", style3);
			}else{
				this.context.put("ListMJM", "");
				this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			    this.context.put("Style3", style3);
			}
		}catch(Exception ex){
			System.out.println("PajakanProcess::getListMJM::Exception = "+ex);
			ex.printStackTrace();
		}
	}
	
	private void SimpanMJM(HttpSession session, int idPermohonan) throws Exception
	{
		if(getParam("idJemaahmenteri") == ""){
			//fail baru
			Hashtable h = new Hashtable();
			h.put("idPermohonan", idPermohonan);
			h.put("tarikhHantarksu", getParam("txdTHPTP"));
			h.put("tarikhTerimaksu", getParam("txdTTPTP"));
			h.put("tarikhHantarPemohon", getParam("txdTHTUP"));
			h.put("tarikhKeputusan", getParam("txdTTK"));
			h.put("tarikhMsyrtJemaah", getParam("txdTMJM"));
			h.put("statusKeputusan", getParam("txtKeputusan"));
			h.put("tindakanLanjut", getParam("txtKeterangan"));
			h.put("noMemorandum", getParam("txtNoMemorandum"));
			FrmPajakanMJMData.simpanMJM(h);
			result = "baru";
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
			h.put("idJemaahmenteri", getParamAsInteger("idJemaahMenteri"));
			h.put("idPermohonan", idPermohonan);
			h.put("tarikhHantarksu", getParam("txdTHPTP"));
			h.put("tarikhTerimaksu", getParam("txdTTPTP"));
			h.put("tarikhHantarPemohon", getParam("txdTHTUP"));
			h.put("tarikhKeputusan", getParam("txdTTK"));
			h.put("tarikhMsyrtJemaah", getParam("txdTMJM"));
			h.put("statusKeputusan", getParam("txtKeputusan"));
			h.put("tindakanLanjut", getParam("txtKeterangan"));
			h.put("noMemorandum", getParam("txtNoMemorandum"));
			FrmPajakanMJMData.updateMJM(h);
			result = "kemaskini";
		}		
	}
}
