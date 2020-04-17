package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.htp.FrmCukaiPelarasanData;
import ekptg.model.htp.FrmCukaiSenaraiPelarasanData;
import ekptg.model.htp.UtilHTML;

public class FrmCukaiPelarasan extends AjaxBasedModule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Long idHakmilikUrusan = 0L;
	private String result = new String();
	static Logger mylog = Logger.getLogger(FrmCukaiPelarasan.class);

	public String doTemplate2()throws Exception {
		 
	    HttpSession session = this.request.getSession();
	    String template_name = "";
      	String disability = "disabled";
    	String readability = "";
    	String style1 = "";
		String style2 = "";
	    this.context.put("util", new lebah.util.Util());

	    String submit = getParam("command");
	    this.context.put("command1", submit);
	    mylog.info("FrmCukaiPelarasan:submit::"+submit);
	    String pageMode = getParam("pagemode");
	    this.context.put("pagemode", pageMode);
	    mylog.info("FrmCukaiPelarasan:pageMode::"+pageMode);
	    String action1 = getParam("action1");
	    this.context.put("action1", action1);
	    mylog.info("FrmCukaiPelarasan:action1::"+action1);
		Vector lists = new Vector();
		
		FrmCukaiPelarasanData f = new FrmCukaiPelarasanData();
		
	    if("pelarasanCukai".equals(submit)){
	    	 mylog.info(this.className+":kemaskiniCukai::");
		    template_name = "app/htp/frmCukaiPelarasan.jsp";
		    int idHakmilikCukai;
		    int idCukaiTerperinci;
		    if (getParam("idCukaiTerperinci") != "")
		    	idCukaiTerperinci = Integer.parseInt(getParam("idCukaiTerperinci"));
		    else
		    	idCukaiTerperinci = 0;
		    this.context.put("idCukaiTerperinci", idCukaiTerperinci);
		    mylog.info("pelarasanCukai::idCukaiTerperinci:::"+idCukaiTerperinci);
		    
		    int intNoHakmilik = Integer.parseInt((getParam("noHakmilik")=="") ? "0" : getParam("noHakmilik"));
		    int intJenisHakmilik = Integer.parseInt((getParam("idJenisHakmilik")=="") ? "0" : getParam("idJenisHakmilik"));
		    int intKementerian = Integer.parseInt((getParam("idKementerian")=="") ? "0" : getParam("idKementerian"));
		    int intNegeri = Integer.parseInt((getParam("idNegeri")=="") ? "0" : getParam("idNegeri"));
		    int intDaerah = Integer.parseInt((getParam("idDaerah")=="") ? "0" : getParam("idDaerah"));
		    int intMukim = Integer.parseInt((getParam("idMukim")=="") ? "0" : getParam("idMukim"));
			
			this.context.put("noHakmilik", intNoHakmilik);
			this.context.put("idjenishakmilik", intJenisHakmilik);
			this.context.put("idKementerian", intKementerian);
			this.context.put("idNegeri", intNegeri);
			this.context.put("idDaerah", intDaerah);
			this.context.put("idMukim", intMukim);
			
			if("simpanPelarasan".equals(pageMode)){
				Long noHakmilik = Long.parseLong(getParam("noHakmilik"));
				Long idJenisHakmilik = Long.parseLong(getParam("idJenisHakmilik"));
				Long idKementerian = Long.parseLong(getParam("idKementerian"));
				Long idNegeri = Long.parseLong(getParam("idNegeri"));
				Long idDaerah = Long.parseLong(getParam("idDaerah"));
				Long idMukim = Long.parseLong(getParam("idMukim"));
				idHakmilikCukai = Integer.parseInt(getParam("idHakmilikCukai"));
				readability = "readonly";
			    disability = "disabled";
			    style1 = "none";		    
			    idCukaiTerperinci=SimpanPCukai(session,idHakmilikCukai);
			    this.context.put("idCukaiTerperinci", idCukaiTerperinci);
			    Long idCT = Long.parseLong(""+idCukaiTerperinci);
			    System.out.println("pelarasanCukai::simpanPelarasan:::idCukaiTerperinci::"+idCukaiTerperinci);
			    DataPCukai(session,idCT,noHakmilik,idJenisHakmilik,idKementerian,idNegeri,idDaerah,idMukim,disability,readability,style1,style2);
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result); 
			}else if("viewCukai".equals(pageMode)){
				Long idCT;
				Long noHakmilik = Long.parseLong(getParam("noHakmilik"));
				Long idJenisHakmilik = Long.parseLong(getParam("idJenisHakmilik"));
				Long idKementerian = Long.parseLong(getParam("idKementerian"));
				Long idNegeri = Long.parseLong(getParam("idNegeri"));
				Long idDaerah = Long.parseLong(getParam("idDaerah"));
				Long idMukim = Long.parseLong(getParam("idMukim"));
				
				Vector vecHash = null;
				vecHash = FrmCukaiPelarasanData.getListCukai(Long.parseLong(""+idCukaiTerperinci),noHakmilik,idJenisHakmilik,idKementerian,idNegeri,idDaerah,idMukim);
				Hashtable pen = (Hashtable)vecHash.get(0);
				System.out.println("pelarasanCukai::viewCukai:::idCukaiTerperinci::xxxx"+pen.get("idCukaiTerperinci"));
				if (!pen.get("idCukaiTerperinci").equals(""))
					idCT = Long.parseLong(pen.get("idCukaiTerperinci").toString());
			    else
			    	idCT = Long.parseLong(""+idCukaiTerperinci);
				
				System.out.println("pelarasanCukai::viewCukai:::idCT::"+idCT);
				this.context.put("noHakmilik", noHakmilik);
				this.context.put("idjenishakmilik", idJenisHakmilik);
				this.context.put("idKementerian", idKementerian);
				this.context.put("idNegeri", idNegeri);
				this.context.put("idDaerah", idDaerah);
				this.context.put("idMukim", idMukim);
				style2 = "none";
				ViewPCukai(session,idCT,noHakmilik,idJenisHakmilik,idKementerian,idNegeri,idDaerah,idMukim,disability,readability,style1,style2);	
			}else if("editCukai".equals(pageMode)){
				Long idCT = Long.parseLong(""+idCukaiTerperinci);
				Long noHakmilik = Long.parseLong(getParam("noHakmilik"));
				Long idJenisHakmilik = Long.parseLong(getParam("idJenisHakmilik"));
				Long idKementerian = Long.parseLong(getParam("idKementerian"));
				Long idNegeri = Long.parseLong(getParam("idNegeri"));
				Long idDaerah = Long.parseLong(getParam("idDaerah"));
				Long idMukim = Long.parseLong(getParam("idMukim"));
				System.out.println("pelarasanCukai::viewCukai:::idCT::"+idCT);
				this.context.put("noHakmilik", noHakmilik);
				this.context.put("idjenishakmilik", idJenisHakmilik);
				this.context.put("idKementerian", idKementerian);
				this.context.put("idNegeri", idNegeri);
				this.context.put("idDaerah", idDaerah);
				this.context.put("idMukim", idMukim);
				style2 = "none";
				DataPCukai(session,idCT,noHakmilik,idJenisHakmilik,idKementerian,idNegeri,idDaerah,idMukim,disability,readability,style1,style2);	
			}
	    }else{
	    	mylog.info(this.className+": !=submit::else:::user_id="+session.getAttribute("_ekptg_user_id"));
	    	template_name = "app/htp/frmCukaiSenaraiPelarasan.jsp";

	    	String nohakmilik = getParam("txtNoHakmilik")==""?"0":getParam("txtNoHakmilik");
	    	String idjenishakmilik = getParam("socJenisHakmilik")==""?"0":getParam("socJenisHakmilik");
	    	String idnegeri = getParam("socNegeri")==""?"0":getParam("socNegeri");
	    	String iddaerah = getParam("socDaerah")==""?"0":getParam("socDaerah");
	    	String idmukim = getParam("socMukim")==""?"0":getParam("socMukim");

	    	Long noHakmilik = Long.parseLong(nohakmilik);
	    	Long idJenisHakmilik = Long.parseLong(idjenishakmilik);
			Long idNegeri = Long.parseLong(idnegeri);
			Long idDaerah = Long.parseLong(iddaerah);
			Long idMukim = Long.parseLong(idmukim);
			
	    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",idJenisHakmilik,"onChange=\"doChangeHakmilik();\""));
	    	this.context.put("selectNegeri",UtilHTML.SelectNegeri("socNegeri",idNegeri,"onChange=\"doChangeNegeri();\"",null,"TBLHTPHAKMILIK"));
    		this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(idnegeri,"socDaerah",idDaerah,"onChange=\"doChangeDaerah();\"",null));
		    this.context.put("selectMukim",HTML.SelectMukimByDaerah(iddaerah,"socMukim",idMukim,"onChange=\"doChangeMukim();\"",null));
	    	
		    lists = FrmCukaiSenaraiPelarasanData.getList(noHakmilik,idJenisHakmilik,idNegeri,idDaerah,idMukim);
		    setupPage(session,action1,lists);
	    	this.context.put("carianNoHakmilik", getParam("txtNoHakmilik"));
	    	this.context.put("idjenishakmilik", idJenisHakmilik);
			this.context.put("idnegeri", idNegeri);
			this.context.put("iddaerah", idDaerah);
			this.context.put("idmukim", idMukim);
	    this.context.put("SenaraiCukai", lists);
	      }
	    
	    return template_name;
	
	
	}
	
	private int SimpanPCukai(HttpSession session, int idHakmilikCukai) throws Exception
	{
		int idCukaiTerperinci = 0;
		
		System.out.println("SimpanPCukai:::idCukaiTerperinci::"+getParam("idCukaiTerperinci"));
		if(getParam("idCukaiTerperinci") == ""){
			//baru
			System.out.println("SimpanPCukai::baru");
			Hashtable h = new Hashtable();
			h.put("idHakmilikCukai", idHakmilikCukai);
			h.put("amaunResit", getParam("txtAmaunResit"));
			h.put("noResit", getParam("txtNoResit"));
			h.put("tarikhResit", getParam("txdTarikhResit"));
			h.put("cukaiTahunan", getParam("txtCukaiTahunan"));
			h.put("cukaiParit", getParam("txtCukaiParit"));
			h.put("cukaiTaliAir", getParam("txtCukaiTaliAir"));
			h.put("pengurangan", getParam("txtPengurangan"));
			h.put("pengecualian", getParam("txtPengecualian"));
			h.put("catatan", getParam("txtCatatan"));
			h.put("tahun", Integer.parseInt(getParam("txtTahun")));
			h.put("denda", getParam("txtDenda"));
			h.put("bayaranLain", getParam("txtBayaranLain"));
			h.put("tunggakan", getParam("txtTunggakan"));
			h.put("jumlahBayaran", getParam("txtJumlahBayaran"));
			idCukaiTerperinci = FrmCukaiPelarasanData.simpan(h);
			result = "baru";
			System.out.println("SimpanPCukai::simpan:: h = "+h);
			return idCukaiTerperinci;
		}else{
			//kemaskini cukai
			System.out.println("SimpanPCukai::kemaskini");
			Hashtable h = new Hashtable();
			idCukaiTerperinci = Integer.parseInt(getParam("idCukaiTerperinci"));
			h.put("idCukaiTerperinci", idCukaiTerperinci);
			h.put("idHakmilikCukai", idHakmilikCukai);
			h.put("amaunResit", getParam("txtAmaunResit"));
			h.put("noResit", getParam("txtNoResit"));
			h.put("tarikhResit", getParam("txdTarikhResit"));
			h.put("cukaiTahunan", getParam("txtCukaiTahunan"));
			h.put("cukaiParit", getParam("txtCukaiParit"));
			h.put("cukaiTaliAir", getParam("txtCukaiTaliAir"));
			h.put("pengurangan", getParam("txtPengurangan"));
			h.put("pengecualian", getParam("txtPengecualian"));
			h.put("catatan", getParam("txtCatatan"));
			h.put("tahun", Integer.parseInt(getParam("txtTahun")));
			h.put("denda", getParam("txtDenda"));
			h.put("bayaranLain", getParam("txtBayaranLain"));
			h.put("tunggakan", getParam("txtTunggakan"));
			h.put("jumlahBayaran", getParam("txtJumlahBayaran"));
			FrmCukaiPelarasanData.update(h);
			result = "kemaskini";
			System.out.println("SimpanPCukai::kemaskini:: h = "+h);
			return idCukaiTerperinci;
		}
	}

	private void DataPCukai(HttpSession session,Long idCukaiTerperinci, Long noHakmilik, Long idJenisHakmilik, Long idKementerian, Long idNegeri, Long idDaerah, Long idMukim, String disability, String readability, String style1, String style2) throws Exception
	{
	  	Vector list = new Vector();
		list.clear();
				    
		try{
			list = FrmCukaiPelarasanData.getListPCukai(idCukaiTerperinci,noHakmilik,idJenisHakmilik,idKementerian,idNegeri,idDaerah,idMukim);
			System.out.println("CukaiProcess::DataPCukai::list.size():::"+list.size());
			this.context.put("ResultSimpan", "");
			
			if(list.size() != 0){			    
				this.context.put("cukaipelarasan", list);
				System.out.println("kemaskiniCukai::DataPCukai::list::"+list);
				Hashtable cHash = (Hashtable) list.get(0);
				
				System.out.println("CukaiPelarasan::DataPCukai::idCukaiTerperinci:::"+idCukaiTerperinci);
				System.out.println("CukaiPelarasan::DataPCukai::noHakmilik:::"+noHakmilik);
				System.out.println("CukaiPelarasan::DataPCukai::idJenisHakmilik:::"+idJenisHakmilik);
				System.out.println("CukaiPelarasan::DataPCukai::idKementerian:::"+idKementerian);
				System.out.println("CukaiPelarasan::DataPCukai::idNegeri:::"+idNegeri);
				System.out.println("CukaiPelarasan::DataPCukai::idDaerah:::"+idDaerah);
				System.out.println("CukaiPelarasan::DataPCukai::idMukim:::"+idMukim);
			    
				this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", idJenisHakmilik ,"disabled"));
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", idNegeri ,"disabled",null));
				this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah", idDaerah ,"disabled",null));
				this.context.put("selectMukim",HTML.SelectMukim("socMukim", idMukim ,"disabled"));
				this.context.put("idHakmilikCukai", cHash.get("idHakmilikCukai"));
				this.context.put("idCukaiTerperinci", cHash.get("idCukaiTerperinci"));
				this.context.put("idHakmilik", cHash.get("idHakmilik"));
				
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			}else{
				this.context.put("cukaipelarasan", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			}
		}catch(Exception ex){
			System.out.println("CukaiPelarasan::DataPCukai::Exception:: = "+ex);
			ex.printStackTrace();
		}
	}
	
	private void ViewPCukai(HttpSession session,Long idCukaiTerperinci, Long noHakmilik, Long idJenisHakmilik, Long idKementerian, Long idNegeri, Long idDaerah, Long idMukim, String disability, String readability, String style1, String style2) throws Exception
	{
	  	Vector list = new Vector();
		list.clear();
				    
		try{
			list = FrmCukaiPelarasanData.getListCukai(idCukaiTerperinci,noHakmilik,idJenisHakmilik,idKementerian,idNegeri,idDaerah,idMukim);
			System.out.println("CukaiProcess::DataPCukai::list.size():::"+list.size());
			this.context.put("ResultSimpan", "");
			
			if(list.size() != 0){			    
				this.context.put("cukaipelarasan", list);
				System.out.println("kemaskiniCukai::DataPCukai::list::"+list);
				Hashtable cHash = (Hashtable) list.get(0);
				
				System.out.println("CukaiPelarasan::DataPCukai::idCukaiTerperinci:::"+idCukaiTerperinci);
				System.out.println("CukaiPelarasan::DataPCukai::noHakmilik:::"+noHakmilik);
				System.out.println("CukaiPelarasan::DataPCukai::idJenisHakmilik:::"+idJenisHakmilik);
				System.out.println("CukaiPelarasan::DataPCukai::idKementerian:::"+idKementerian);
				System.out.println("CukaiPelarasan::DataPCukai::idNegeri:::"+idNegeri);
				System.out.println("CukaiPelarasan::DataPCukai::idDaerah:::"+idDaerah);
				System.out.println("CukaiPelarasan::DataPCukai::idMukim:::"+idMukim);
			    
				this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", idJenisHakmilik ,"disabled"));
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", idNegeri ,"disabled",null));
				this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah", idDaerah ,"disabled",null));
				this.context.put("selectMukim",HTML.SelectMukim("socMukim", idMukim ,"disabled"));
				this.context.put("idHakmilikCukai", cHash.get("idHakmilikCukai"));
				this.context.put("idCukaiTerperinci", cHash.get("idCukaiTerperinci"));
				this.context.put("idHakmilik", cHash.get("idHakmilik"));
				
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			}else{
				this.context.put("cukaipelarasan", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			}
		}catch(Exception ex){
			System.out.println("CukaiPelarasan::DataPCukai::Exception:: = "+ex);
			ex.printStackTrace();
		}
	}
	
	public void setupPage(HttpSession session,String action,Vector lists) {
		this.context.put("totalRecords",lists.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		System.out.println("CukaiPelarasan::setupPage::page:::"+page);
		System.out.println("CukaiPelarasan::setupPage::list.size():::"+lists.size());
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
		Paging paging = new Paging(session,lists,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("lists",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	}
	
}
