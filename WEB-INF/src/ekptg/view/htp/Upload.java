package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmCukaiKemaskiniDataBaru;
import ekptg.model.htp.FrmCukaiSenaraiData;
import ekptg.model.htp.FrmCukaiSenaraiFailExcelUpload;
import ekptg.model.htp.FrmCukaiUploadList;
//import lebah.db.Db;

public class Upload extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(Upload.class);
	

	public String doTemplate2() throws Exception

    {
		HttpSession session = request.getSession();
		
		String vm = "";
		String action = getParam("action"); // Second Level

		context.put("completed",false);
		context.put("maxsize",false);
		context.put("wrongEXT",false);

		String submit = getParam("command");
		
		
    	myLogger.info("submitX : " + submit);
    	String NamaDoc = getParam("nama_dokumen");
		String keterangan = getParam("keterangan");
		String fileupload11 = getParam("fileupload11");
		
		String lblNegeri = "";
		String lblDaerah = "";
		
		
		
		Hashtable CukaiInfo = FrmCukaiUploadList.getCukaiNegeriInfo("");
		
		//lblNegeri = HTML.SelectNegeri("lblNegeri", Long.parseLong(CukaiInfo.get("lblNegeri").toString()), "", "onChange=\"doChangeDaerah()\"");
		
		lblNegeri = HTML.SelectNegeri("lblNegeri", "onChange=\"doChangeDaerah()\"");
		lblDaerah = HTML.SelectDaerah("lblDaerah");
		context.put("lblNegeri", lblNegeri);
		context.put("lblDaerah", lblDaerah);
		this.context.put("page", 0);
		
		if ("doChangeDaerah".equals(submit)){
    		
    		vm = "app/htp/uploadtest3.jsp";
    		this.context.put("page", 0);
			//vm="app/htp/frmCukaiBorangManual.jsp";
    		
    		String socDaerah = "";
    		String socMukim = "";
    		String idNegeri = getParam("lblNegeri");
    		String idDaerah = getParam("lblDaerah");
    		NamaDoc = getParam("nama_dokumen");
    		keterangan = getParam("keterangan");
    		fileupload11 = getParam("fileupload11");
    		
    		//System.out.println("doChangeDaerah::Id Negeri ::" + idNegeri);
    		//System.out.println("doChangeDaerah::Id Daerah ::" + idDaerah);
    		
    		lblNegeri = HTML.SelectNegeri("lblNegeri",Utils.parseLong(idNegeri),null, "onChange=\"doChangeDaerah()\" ");
    		//socDaerah = HTML.SelectDaerahByNegeri("socDaerah", null, Utils.parseLong(idNegeri), "");
    		context.put("lblNegeri", lblNegeri);
    		
    		if(idNegeri != ""){
    			context.put("lblDaerah",HTML.SelectDaerahByNegeri(idNegeri,"lblDaerah",null,""));
    			
    		}else {
    			//context.put("socDaerah",HTML.SelectDaerahByNegeri(idNegeri,"socDaerah",null,"onChange=\"doChangeMukim()\""));
    			context.put("lblDaerah", HTML.SelectDaerah("lblDaerah",null,null,""));
    		}		
    		
    		   		
    	}else if("SenaraiFailUpload".equals(submit)||("doChanges".equals(submit))){
    		/**
    		 * Senarai Fail Uplod selepas tamat Upload
    		 */
    		
    		Vector SenaraiFail = null;
    		String lblNegeri2 = "";
    		
    		vm = "app/htp/frmCukaiSenaraiFailUploadExcel.jsp";
    		
    		

    		this.context.put("lblNegeri2", HTML.SelectNegeri("lblNegeri2", "onChange=\"doChangeDaerah1()\""));
    		this.context.put("lblDaerah2", HTML.SelectDaerah("lblDaerah2"));
    		this.context.put("lblMukim2", HTML.SelectMukim("lblMukim2"));
    		
    		SenaraiFail = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel();
    		this.context.put("SenaraiFail", SenaraiFail);
    		
    		setupPage(session,action,SenaraiFail);
    		
    	}else if("doChangeDaerah1".equals(submit)||("doChanges".equals(submit))){
			
    		Vector SenaraiFail = null;
    		String lblNegeri2 = "";
    		String idNegeri2 = getParam("lblNegeri2");
    		String idDaerah2= getParam("lblDaerah2");
    		String idMukim2= getParam("lblMukim2");
    		
			vm = "app/htp/frmCukaiSenaraiFailUploadExcel.jsp";
			//vm="app/htp/frmCukaiSenaraiTapis.jsp";
			
			System.out.println("idNegeri2 ::" + idNegeri2);
			System.out.println("idDaerah2 ::" + idDaerah2);
			System.out.println("idMukim2 ::" + idMukim2);
			
			lblNegeri2 = HTML.SelectNegeri("lblNegeri2", Utils.parseLong(idNegeri2), null, "onChange=\"doChangeDaerah1()\" ");
			this.context.put("lblNegeri2", lblNegeri2);
			
			if(idNegeri2 != ""){
    			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2,"lblDaerah",null,""));
    			context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2, "lblDaerah2", Utils.parseLong(idDaerah2), null, "onChange=\"doChangeDaerah1()\""));
    			
    		}else {
    			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2,"lblDaerah2",null,"onChange=\"doChangeDaerah1()\""));
    			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2, "lblDaerah2", Utils.parseLong(idDaerah2), null, "onChange=\"doChangeDaerah1()\""));
    			context.put("lblDaerah2", HTML.SelectDaerah("lblDaerah2",null,null,""));
    		}
			
			if(idNegeri2 != "" && idDaerah2 != ""){
						
				this.context.put("lblMukim2", HTML.SelectMukimByDaerah(idDaerah2, "lblMukim2", Utils.parseLong(idMukim2), null, "onChange=\"doChangeDaerah1()\""));
				
			}else{
				
				this.context.put("lblMukim2", HTML.SelectMukimByDaerah(idDaerah2, "lblMukim2"));
				
			}
			
			SenaraiFail = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel();
    		this.context.put("SenaraiFail", SenaraiFail);
    		setupPage(session,action,SenaraiFail);
		}
    	else if("carianFailTahun".equalsIgnoreCase(submit)){
    		
    		vm="app/htp/frmCukaiSenaraiTahun.jsp";
    		
    		String tahun ="2010";
    		Vector tahunlist = null;
    		//tahun = getParam("tahun");
    		
    		tahunlist = FrmCukaiSenaraiData.getSenaraiTahun();
    		this.context.put("ListTahun", tahunlist);
    	}
		
    	else if ("carianFail".equals(submit)||("doChanges".equals(submit))){
    		
    		vm="app/htp/frmCukaiSenaraiTapis.jsp";
    		
    		Vector list = null;
    		String lblNegericari = "";
    		String lblDaerahcari = "";
    		String lblMukimcari = "";
    		String noHakmilikcari = "";
    		
    		lblNegericari = getParam("lblNegeri2");
    		lblDaerahcari = getParam("lblDaerah2");
    		lblMukimcari = getParam("lblMukim2");
    		noHakmilikcari = getParam("txtNoHakmilik").trim();
    		
//    		System.out.println("carianFail : Negeri ::"+ lblNegericari);
//    		System.out.println("carianFail : Daerah ::"+ lblDaerahcari);
//    		System.out.println("carianFail : Mukim ::"+ lblMukimcari);
//    		System.out.println("carianFail : NoHakmilik ::"+ noHakmilikcari);
    		
    		list = FrmCukaiSenaraiData.getSenaraiTapis(lblNegericari, lblDaerahcari, lblMukimcari, noHakmilikcari);
    		this.context.put("listtapis", list);
    		
//    		Vector vecNegeri=null;
//    	
//	    	vecNegeri = ekptg.helpers.DB.getNegeri(lblNegericari);
//	    	
//			Tblrujnegeri h = (Tblrujnegeri) vecNegeri.get(0);
//	    	this.context.put("infoNegeri", h);
    		
    		setupPage2(session,action,list);
    	}
		
    	else if("ViewTemp".equalsIgnoreCase(submit)){
    		vm = "app/htp/frmCukaiViewTempData.jsp";
    		Vector listtemp = null;
    		
    		String idNoHakmilik = "";
    		idNoHakmilik = getParam("idhakmilik");
    		this.context.put("IDHAKMILIK", idNoHakmilik);
    		System.out.println("No hakmilik :: "+idNoHakmilik);
    		
    		listtemp = FrmCukaiSenaraiData.getViewTempData(idNoHakmilik);
    		System.out.println(listtemp);
    		this.context.put("listtemp",listtemp);
    		
    	}
		
    	else if("TambahanManual".equals(submit)){
    		
    		//vm="app/htp/frmCukaiBorangManual.jsp";
    		
    		vm = "app/htp/uploadtest3.jsp";
    		this.context.put("page", 1);
    		this.context.put("tahun", 0);

    		this.context.put("manualNegeri",HTML.SelectNegeri("manualNegeri", "onChange=\"doChangeDaerahManual()\"") );
    		this.context.put("manualDaerah", HTML.SelectDaerah("manualDaerah"));
    		this.context.put("manualMukim", HTML.SelectMukim("manualMukim"));
    		this.context.put("jenisLot",HTML.SelectLot("jenisLot"));
    		this.context.put("JenisHakmilik",HTML.SelectJenisHakmilik("JenisHakmilik"));
    		
//    kemaskini		this.context.put("nohakmilik", "");
//    		this.context.put("noLot", "");
//    		this.context.put("KegunaanTanah", "");
//    		this.context.put("modeDisable", "");
    		
    		
    	}else if("doChangeDaerahManual".equals(submit)){
			
    		Vector SenaraiFail = null;
    		String lblNegeri3 = "";
    		String tahun = getParam("txttahun");
    		String idNegeri3 = getParam("manualNegeri");
    		String idDaerah3= getParam("manualDaerah");
    		String idMukim3= getParam("manualMukim");
    		
    		//vm="app/htp/frmCukaiBorangManual.jsp";
    		vm = "app/htp/uploadtest3.jsp";
    		this.context.put("page", 1);
    		this.context.put("tahun", 1);
			
			System.out.println("idNegeri3 ::" + idNegeri3);
			System.out.println("idDaerah3 ::" + idDaerah3);
			System.out.println("idMukim3 ::" + idMukim3);
			
			lblNegeri3 = HTML.SelectNegeri("manualNegeri", Utils.parseLong(idNegeri3), null, "onChange=\"doChangeDaerahManual()\" ");
			this.context.put("manualNegeri", lblNegeri3);
			
			if(idNegeri3 != ""){
    			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2,"lblDaerah",null,""));
    			context.put("manualDaerah",HTML.SelectDaerahByNegeri(idNegeri3, "manualDaerah", Utils.parseLong(idDaerah3), null, "onChange=\"doChangeDaerahManual()\""));
    			
    		}else {
    			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2,"lblDaerah2",null,"onChange=\"doChangeDaerah1()\""));
    			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2, "lblDaerah2", Utils.parseLong(idDaerah2), null, "onChange=\"doChangeDaerah1()\""));
    			context.put("manualDaerah", HTML.SelectDaerah("manualDaerah",null,null,""));
    		}
			
			if(idNegeri3 != "" && idDaerah3 != ""){
						
				this.context.put("manualMukim", HTML.SelectMukimByDaerah(idDaerah3, "manualMukim", Utils.parseLong(idMukim3), null, "onChange=\"doChangeDaerahManual()\""));
				
			}else{
				
				this.context.put("manualMukim", HTML.SelectMukimByDaerah(idDaerah3, "manualMukim"));
				
			}
			
			this.context.put("jenisLot",HTML.SelectLot("jenisLot"));
			this.context.put("tahun", tahun);
    		this.context.put("JenisHakmilik",HTML.SelectJenisHakmilik("JenisHakmilik"));
    		
			SenaraiFail = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel();
    		this.context.put("SenaraiFail", SenaraiFail);
    		setupPage(session,action,SenaraiFail);
		}
		
    	else if("SimpanManual".equals(submit)){
    		Hashtable h = null;
			h = new Hashtable();
			
			String Tahun = "";
    		String idNegeri2 = "";
    		String idDaerah2 = "";
    		String idMukim2 = "";
    		String noHakmilik = "";
    		String Luas = "";
    		String noLot = "";
    		String Tahunan = "";
    		String CukaiLain = "";
    		String Tungakan = "";
    		String Denda = "";
    		String Lebihan = "";
    		String JumBayaran = "";
    		String KegunaanTanah = "";
    		String JenisHakmilik ="";
    		String JenisLot = "";
    		
    		Tahun = getParam("txttahun")==""?"0":getParam("txttahun");
    		idNegeri2 = getParam("manualNegeri")==""?"0":getParam("manualNegeri");
    		idDaerah2 = getParam("manualDaerah")==""?"0":getParam("manualDaerah");
    		idMukim2 = getParam("manualMukim")==""?"0":getParam("manualMukim");
    		JenisHakmilik = getParam("JenisHakmilik")==""?"0":getParam("JenisHakmilik");
    		noHakmilik = getParam("txtNoHakmilik")==""?"0":getParam("txtNoHakmilik");
    		Luas = getParam("txtLuas")==""?"0":getParam("txtLuas");
    		JenisLot = getParam("jenisLot")==""?"0":getParam("jenisLot");
    		noLot = getParam("txtNoLot")==""?"0":getParam("txtNoLot");
    		Tahunan = getParam("txtTahunan")==""?"0":getParam("txtTahunan");
    		CukaiLain = getParam("txtCukaiLain")==""?"0":getParam("txtCukaiLain");
    		Tungakan = getParam("txtTungakan")==""?"0":getParam("txtTungakan");
    		Denda = getParam("txtDenda")==""?"0":getParam("txtDenda");
    		Lebihan = getParam("txtLebihan")==""?"0":getParam("txtLebihan");
    		JumBayaran = getParam("txtJumBayaran")==""?"0":getParam("txtJumBayaran"); 
    		KegunaanTanah = getParam("txtKegunaanTanah")==""?"0":getParam("txtKegunaanTanah");
    		
    		h.put("Tahun", Tahun);
    		h.put("idNegeri2", idNegeri2);
    		h.put("idDaerah2", idDaerah2);
    		h.put("idMukim2", idMukim2);
    		h.put("JenisHakmilik", JenisHakmilik);
    		h.put("noHakmilik", noHakmilik);
    		h.put("Luas", Luas);
    		h.put("JenisLot", JenisLot);
    		h.put("noLot", noLot);
    		h.put("Tahunan", Tahunan);
    		h.put("CukaiLain", CukaiLain);
    		h.put("Tungakan", Tungakan);
    		h.put("Denda", Denda);
    		h.put("Lebihan", Lebihan);
    		h.put("JumBayaran", JumBayaran);
    		h.put("KegunaanTanah", KegunaanTanah);
    		
    		FrmCukaiKemaskiniDataBaru.SimpanDataManual(h);
    		
    		vm = "app/htp/uploadtest3.jsp";
    		this.context.put("page", 1);
    		//vm = "app/htp/frmCukaiSenaraiFailUploadExcel.jsp";
    		
//   kemaskini 		this.context.put("manualNegeri", HTML.SelectNegeri("manualNegeri", Long.parseLong(idNegeri2), "disabled=\"disabled\" class=\"disabled\""));
//    		this.context.put("manualDaerah", HTML.SelectDaerah("manualDaerah",  Long.parseLong(idDaerah2), "disabled=\"disabled\" class=\"disabled\""));
//    		this.context.put("manualMukim", HTML.SelectMukim("manualMukim",  Long.parseLong(idMukim2), "disabled=\"disabled\" class=\"disabled\""));
//    		this.context.put("JenisHakmilik", HTML.SelectJenisHakmilik("JenisHakmilik",  Long.parseLong(JenisHakmilik), "disabled=\"disabled\" class=\"disabled\""));
//    		this.context.put("jenisLot", HTML.SelectLot("jenisLot",  Long.parseLong(JenisLot), "disabled=\"disabled\" class=\"disabled\""));
//    		this.context.put("noLot", noLot);
//    		this.context.put("nohakmilik", noHakmilik);
//    		this.context.put("KegunaanTanah", KegunaanTanah);
    		
    		
    		this.context.put("modeDisable", "class=\"disabled\" disabled=\"disabled\"");
    		
    	}
		
    	else if("uploadFile".equals(submit))
    	{
//    		String extension = getParam("extension");
//    		System.out.println("test 1");
//    		try{
//	    		System.out.println("test 2 ");
//			    File f = new File(extension);
//			    System.out.println("test 3");
//			    String ext = f.getName().substring(f.getName().lastIndexOf('.')+1);
//			    System.out.println("test 4");
//		    if(ext.equals("xls")){
//	    		FileInputStream fs = new FileInputStream(ext);
//	    		System.out.println("test 5");
//	    		xlReader = new ReadXLSheet();
//	    		System.out.println("test 2");
//	    		//xlReader.init("");
//	    		xlReader.contentReading(fs);
//				//xlReader.init(fs);
//				System.out.println("test 3");
//		    }
//    		} catch (Exception e) {
//   			e.printStackTrace();
// 			}

    	}
    	else{

    		vm = "app/htp/uploadtest3.jsp";
    	}

	
		return vm;
    }//close dotemplate
	
	
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
		this.context.put("listtapis",paging.getPage(page));
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

}//close class


