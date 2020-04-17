package ekptg.view.htp;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmCukaiSenaraiFailExcelUpload;
import ekptg.model.htp.FrmPajakanKecilPendaftaranData;
import ekptg.model.htp.FrmPajakanKecilSenaraiPermohonanData;
import ekptg.model.htp.FrmPembelianPemilikData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailPajakanKecilData;
import ekptg.model.htp.FrmUtilData;

public class FrmCukaiMuatnaik extends AjaxBasedModule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7485773936241682209L;

	Long idHakmilikUrusan = 0L;

	static Logger mylog = Logger.getLogger(ekptg.view.htp.FrmCukaiMuatnaik.class);
	public String doTemplate2()throws Exception {
		 
	    HttpSession session = this.request.getSession();
	    String template_name = "app/htp/frmCukaiMuatnaik.jsp";
		String action = getParam("action"); // Second Level
     	String tajuk = "";
      	String disability = "disabled";
    	String readability = "";
     	String style1 = "";
    	String style2 = "";
  
	    String socAgensi = "";
	    String socKementerian = "";
	    String socNegeri = "";
	    String socUrusan = "";
	    String socSuburusan = "";
		Vector semakanSenarai = new Vector();

	    this.context.put("util", new lebah.util.Util());

	    Vector senaraiFail = null;
		    
	    socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");
	    
        String id_kementerian = getParam("sockementerian");
	    System.out.println("FrmPajakanKecilA:id_kementerian::"+id_kementerian);
	    String submit = getParam("command");
	    System.out.println("FrmPajakanKecilA:submit::"+submit);
	    String idFail = getParam("fail");
	    System.out.println("FrmPajakanKecilA:fail::"+idFail);
	    String pageMode = getParam("pagemode");
	    System.out.println("FrmPajakanKecilA:pagemode::"+pageMode);
	    String langkah = getParam("langkah");
	    System.out.println("FrmPajakanKecilA:langkah::"+langkah);
	    //String fail = getParam("fail");
	    //System.out.println("FrmPajakanKecilA:fail::"+fail);
		Vector list = new Vector();

	    if (!("".equals(submit))) {
	    	this.context.put("idsuburusan","44");
	    	this.context.put("idurusan","309");

	
	    	
	    	if("pksenaraifailcari".equals(submit)){ //carian
	    	    System.out.println("FrmPajakanKecilA: equals(submit)::pksenaraifailcari");

	    		String nofail = getParam("nofail");
	    		template_name = "app/htp/frmSenaraiFailPajakanKecil.jsp";
	    		senaraiFail = FrmSenaraiFailPajakanKecilData.getList(nofail);
	    		this.context.put("senaraiList", senaraiFail);  

	    	}else if("cukaifailupload".equals(submit)){
	    		template_name = "app/htp/uploadtest.jsp";
    	    //this.context.put("SimpanStatus", modeFail);
			    ///this.context.put("ResultSimpan", getParam("idFail"));
    	    
	    		int idPermohonan = Integer.parseInt(getParam("idpermohonan"));
				Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(""+idPermohonan);
				
				String socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"iddaerah","onChange=\"doChangeDaerah)\" ");
			    this.context.put("selectDaerah", socDaerah);
						
	 		
				//if("pemilikview".equals(mode)){
				readability = "readonly";
				disability = "disabled";
				style2 = "none";

				String selectedTab = new String();
	            selectedTab = getParam("tabId").toString();
	            if (selectedTab == null || "".equals(selectedTab) ) {
	               selectedTab="0";
	            }
	            this.context.put("selectedTab",selectedTab);
				this.context.put("IdPermohonan", idPermohonan);

	    	}else if("simpancukainegeri".equals(submit)){
	    	    System.out.println("FrmCukaiMuatnaik: equals("+submit+")::simpancukainegeri");
	    	    System.out.println("FrmCukaiMuatnaik: equals("+submit+")::getParam(\"idpermohonan\"):::"+getParam("idpermohonan"));
	    	    System.out.println("FrmCukaiMuatnaik: equals("+submit+")::getParam(\"filecukai\"):::"+getParam("filecukai"));
	    	    
	    	    //this.context.put("SimpanStatus", modeFail);
			    ///this.context.put("ResultSimpan", getParam("idFail"));
    	    
	    		int idPermohonan = Integer.parseInt(getParam("idpermohonan"));
				Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(""+idPermohonan);
				
				String socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"iddaerah","onChange=\"doChangeDaerah)\" ");
			    this.context.put("selectDaerah", socDaerah);
						
	 		
				//if("pemilikview".equals(mode)){
				readability = "readonly";
				disability = "disabled";
				style2 = "none";

				String selectedTab = new String();
	            selectedTab = getParam("tabId").toString();
	            if (selectedTab == null || "".equals(selectedTab) ) {
	               selectedTab="0";
	            }
	            this.context.put("selectedTab",selectedTab);
	    		
	    	}else if("doChangeDaerah1".equals(submit)||("doChanges".equals(submit))){
				
	    		Vector SenaraiFail = null;
	    		String lblNegeri2 = "";
	    		String idNegeri2 = getParam("lblNegeri2");
	    		String idDaerah2= getParam("lblDaerah2");
	    		String idMukim2= getParam("lblMukim2");
	    		
	    		template_name = "app/htp/frmCukaiSenaraiFailUploadExcel.jsp";
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
			}else if("uploadFile".equals(submit))	{
	    		
	    		DiskFileItemFactory factory = new DiskFileItemFactory();
	 		    ServletFileUpload upload = new ServletFileUpload(factory);	 		    
	    		List items = upload.parseRequest(request);
	    		
	    		int maxMB = 1; //set maximum size in MB
	    		long maxSize = ((1024*1024) * maxMB );
	    		
			    Iterator itr = items.iterator();
			    while (itr.hasNext()) {
			    	
			      FileItem item = (FileItem)itr.next();
			      long itemSize = item.getSize();
			      
			      if(itemSize > maxSize){ //check size
			    	  context.put("maxsize",true);
			      }else{
			    	  
			    	  String extension = getParam("extension");
			    	  
			    	  File f = new File(extension);
			    	  String ext = f.getName().substring(f.getName().lastIndexOf('.')+1);	

			    	  if(ext.equals("xls")){
			    		  if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			    	  			//POIExcelReader poiExample = new POIExcelReader ();
			    	  			InputStream itemPath = item.getInputStream();
			    	  			//poiExample.displayFromExcel(itemPath);
			    	  			HSSFWorkbook workBook = new HSSFWorkbook (itemPath);
			    				HSSFSheet sheet = workBook.getSheetAt (0);
			    				Iterator rows = sheet.rowIterator ();
			    				int rows1 = sheet.getPhysicalNumberOfRows();
			    				mylog.info("rows1:"+rows1);
			    				//Workbook workbook 	= Workbook.getWorkbook(new File(fileFolder + filename));
			    				//Sheet sheet 		= workbook.getSheet(0);
			    				//int rows = sheet.Rows();
			    				//int colss = sheet.getColumns();

			    	  		}
			    	  }else{
			    		  context.put("wrongEXT",true);
			    	  }
			      }
			      
			    }//close uploadfile
	    		
	            context.put("completed",true);
	         	
	         	//vm = "app/upload/uploadtest.jsp";
	       	
	    	}//close upload file
	    	
	    }else{ // !=submit
	    	if("0->0".equals(langkah)){ //carian
	    	    System.out.println("FrmPajakanKecilA: !=submit::0->0");

	    		String nofail = getParam("nofail");
	    		template_name = "app/htp/frmPajakanKecilSenaraiFail.jsp";
	    		senaraiFail = FrmSenaraiFailPajakanKecilData.getList(nofail);
	    		this.context.put("senaraiList", senaraiFail);  

	    	}else if("0->1".equals(langkah)){
	    	    System.out.println("FrmPajakanKecilA: !=submit::0->1");
    		
		    	template_name = "app/htp/frmPajakanKecilPendaftaran.jsp";
			    this.context.put("socSeksyen","3");
				socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled class=disabled");
		    	this.context.put("socUrusan",socUrusan);

		    	//perjanjian 44
		    	this.context.put("idsuburusan","44");
		    	this.context.put("idurusan","309");
		     	//socKementerian = HTML.SelectKementerian("sockementerian");
		     	
		    	String strdate = "";
		    	strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
		    	this.context.put("sekarang",strdate);
				//pageMode = Integer.parseInt(getParam("pagemode"));
			    this.context.put("pageMode", "0");  
			    this.context.put("nofail", "");  

			    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh","aktif");
			    this.context.put("senaraiSemakan",semakanSenarai );


			    // by ajax
			     /* if (!("".equals(id_kementerian))) {
			     	socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeKementerian()\" ");
			    	socAgensi = HTML.SelectAgensiByKementerian("socAgensi",id_kementerian,Long.parseLong("1"),"");
		
			    	//if ("doChangeKementerian".equals(submit)){
			    		//this.context.put("registerUserStatus", "none");
			       // }
			    	
			    	
			      }else {*/
			        //this.context.put("registerUserStatus", "none");
			        //this.context.put("selectSeksyen", HTML.SelectSeksyen("id_seksyen"));
			        //this.context.put("selectNegeri", HTML.SelectNegeri("id_negeri", "onChange=\"doChangeNegeri()\" "));
			        //this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(null, "id_daerah"));
			    	
			    	  socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeKementerian()\" ");
			    	  socAgensi = HTML.SelectAgensiByKementerian("socAgensi",null,Long.parseLong("1"),"");
			     // }
		    
		    
			    this.context.put("socNegeri",socNegeri);
			    this.context.put("socKementerian",socKementerian);
			    this.context.put("socAgensi",socAgensi);
	    		
	    	}else if("0->-1".equals(langkah)){
	    	    System.out.println("FrmPajakanKecilA: !=submit::0->-1");

			    //String fail = getParam("fail");
		    	Vector senaraiPermohonan = null;
		    	senaraiPermohonan = FrmPajakanKecilSenaraiPermohonanData.getList(idFail);
		    	this.context.put("senaraiList", senaraiPermohonan);	    	
		    	this.context.put("idFail", idFail);	    	
		    	template_name = "app/htp/frmPajakanKecilSenaraiPermohonan.jsp";	
		    }else{
	    	    System.out.println("FrmCukaiMuatnaik: !=submit::else:::user_id="+session.getAttribute("_ekptg_user_id"));
				//String idPermohonan = getParam("idPermohonan");
				//session.setAttribute("permohonan", idPermohonan);
	    	    String id = "";
	    	    String modeFail = "TIADA";
	    	    id = (String)session.getAttribute("idpermohonan");
	    	    if (!("".equals(id))) {
		    	    System.out.println("FrmCukaiMuatnaik:!(\"\".equals("+id+"))");
		    	    tajuk = "Senarai Fail";
		    		template_name = "app/htp/frmCukaiSenaraiFailUpload.jsp";
		    		senaraiFail = FrmUtilData. getSenaraiFailByUrusan("11", "", "",Long.parseLong("20"));
		    		this.context.put("senaraiList", senaraiFail); 
		    	    
	    	    }else{
 
		    	    this.context.put("SimpanStatus", modeFail);
				    this.context.put("ResultSimpan", getParam("idFail"));
	    	    
		    		int idPermohonan = Integer.parseInt(getParam("idpermohonan"));
					this.context.put("IdPermohonan", idPermohonan);
				    /*this.context.put("IdFail", getParam("idFail"));
				     
				    list = FrmPembelianInfoData.getSemak(idPermohonan);
					this.context.put("Info", list);
					Hashtable h = (Hashtable) list.get(0);
					long idNegeri = Long.parseLong(h.get("idNegeri").toString());
					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,"disabled"));
					this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()),"disabled"));
					this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("idAgensi").toString()),"disabled"));
					this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()),"disabled"));
					*/
					//if("pemilikview".equals(mode)){
					readability = "readonly";
					disability = "disabled";
					style2 = "none";
						//ListPemilik(session);
						//DataPemilik(session,disability,readability,style1,style2);
					//}
		    	    //if(!id.equalsIgnoreCase(""))
		    	    	//modeFail = id;
		    	    
		    		//template_name = "app/htp/frmCukaiPeringkatBayar.jsp";
		        
		    		//senaraiFail = FrmSenaraiFailPajakanKecilData.getList("",(String)session.getAttribute("_ekptg_user_id"));
		    		//senaraiFail = FrmUtilData. getList("11", "", "",Long.parseLong("20"));
		    		//senaraiFail = FrmSenaraiFailPerletakhakanData.getList();

		    		//$session.getAttribute("_ekptg_user_id")<br>
		    		//Username : $session.getAttribute("_portal_username")<br>
		    		//Role: $session.getAttribute("_portal_role")<br>


		    		//this.context.put("senaraiList", senaraiFail);  
					String selectedTab = new String();
		            selectedTab = getParam("tabId").toString();
		            if (selectedTab == null || "".equals(selectedTab) ) {
		               selectedTab="0";
		            }
		            this.context.put("selectedTab",selectedTab);
	

	    	    }


	    	}
		 }
	    this.context.put("title", tajuk); 

	    return template_name;
	}

	//*** Pembelian Pemilik Controller
	private void ListPemilik(HttpSession session) throws Exception
	{
		String idHakmilikurusan = getParam("idHakmilikurusan");
		FrmPembelianPemilikData.setListPemilik(idHakmilikurusan);
	}
	
	private void DataPemilik(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
	{
		Vector list = new Vector();
		list.clear();
		try{
			list = FrmPembelianPemilikData.getListPemilik();
			System.out.println("PembelianProcess::list.size() "+list.size());			
			if(list.size() != 0){			    
				System.out.println("PembelianProcess::DataPemilik::list "+list);
				this.context.put("Hakmilik", list);
				Hashtable hak = (Hashtable) list.get(0);	
				this.context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Long.parseLong(hak.get("idJenisnopb").toString()),disability));
				this.context.put("selectADaerah",HTML.SelectDaerah("socADaerah",Long.parseLong(hak.get("IdDaerah").toString()),disability));
				this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri",Long.parseLong(hak.get("IdNegeri").toString()),disability));
				this.context.put("modeSoc", disability);
			    this.context.put("mode", readability);
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			}else{
				this.context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB"));
				this.context.put("selectADaerah",HTML.SelectDaerah("socADaerah"));
				this.context.put("selectANegeri",HTML.SelectNegeri("socANegeri"));
				this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			}
		}catch(Exception ex){
			System.out.println("PembelianProcess::DataPemilik::Exception = "+ex);
		}
	}


}
