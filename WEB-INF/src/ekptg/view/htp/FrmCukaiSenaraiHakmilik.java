package ekptg.view.htp;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.htp.FrmCukaiSenaraiData;
import ekptg.model.htp.FrmPajakanKecilPendaftaranData;
import ekptg.model.htp.FrmSenaraiFailPajakanKecilData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.UtilHTML;


public class FrmCukaiSenaraiHakmilik extends AjaxBasedModule{
	
	Long idHakmilikUrusan = 0L;
	private final String PATH="app/htp/";
	static Logger myLog = Logger.getLogger(FrmUtilData.class);

	public String doTemplate2()throws Exception {
		 
	    HttpSession session = this.request.getSession();
    	String action = getParam("action");
      	String disability = "disabled";
    	String pembelian = "";
    	String pajakan = "";
    	String rizab = "";
      	String readability = "";
	    String socAgensi = "";
	    String socKementerian = "";
	    String socNegeri = "";
	    String socDaerah = "";
	    String socMukim = "";
	    String template_name =PATH+"frmCukaiSenaraiTerperinci.jsp";
	  	Vector list = new Vector();
		Vector semakanSenarai = new Vector();
	    Vector senaraiFail = null;

	    String langkah = getParam("langkah");
	    socNegeri = UtilHTML.SelectNegeri("idnegeri", " onChange=\"doChangeNegeri()\" ","tblhtphakmilik");
	    String pageMode = getParam("pagemode");
	    String submit = getParam("command");
	    String idNegeri = (getParam("idnegeri")=="") ? "0" : getParam("idnegeri");
		String idDaerah = (getParam("iddaerah")=="") ? "0" : getParam("iddaerah");
		String idMukim = (getParam("idmukim")=="") ? "0" : getParam("idmukim");	    

		myLog.info("FrmPajakanKecilA:submit::"+submit);
		myLog.info("FrmPajakanKecilA:pagemode::"+pageMode);
		myLog.info("FrmPajakanKecilA:langkah::"+langkah);	
	    socDaerah = HTML.SelectDaerahByNegeri(idNegeri,"iddaerah"," onChange=\"doChangeDaerah()\" ");
	    socMukim = HTML.SelectMukimByDaerah(idDaerah,"idmukim");
	    this.context.put("util", new lebah.util.Util());
		context.put("UTIL", new ekptg.helpers.Utils());

	    if (!("".equals(submit))) {
	    	this.context.put("idsuburusan","44");
	    	this.context.put("idurusan","309");

	    	if("pksenaraifailcari".equals(submit)){ //carian
	    	    System.out.println("FrmPajakanKecilA: equals(submit)::pksenaraifailcari");
	    		String nofail = getParam("nofail");
	    		template_name = "app/htp/frmSenaraiFailPajakanKecil.jsp";
	    		senaraiFail = FrmSenaraiFailPajakanKecilData.getList(nofail);
	    		this.context.put("senaraiList", senaraiFail);  

	    	}else if("pksenaraipermohonan".equals(submit)){
	    	    System.out.println("FrmPajakanKecilA: equals(submit)::pksenaraipermohonan");
			    //String fail = getParam("fail");
		    	Vector senaraiPermohonan = null;
		    	//senaraiPermohonan = FrmPajakanKecilSenaraiPermohonanData.getList(idFail);
		    	//this.context.put("senaraiList", senaraiPermohonan);	    	
		    	//this.context.put("idFail", idFail);	    	
		    	template_name = "app/htp/frmPajakanKecilSenaraiPermohonan.jsp";	
		    	
	    	}else if ("doChangeNegeri".equals(submit)){
		    	System.out.println(this.className+": equals("+submit+")::doChangeNegeri");
		    	template_name = "app/htp/frmCukaiSenarai.jsp";
			    String idnegeri = getParam("idnegeri");
			    if(idnegeri == ""){
				    socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");
				    //socNegeri = HTML.SelectNegeri("socNegeri");
			    }else{	
				    socNegeri = UtilHTML.SelectNegeri("idnegeri",Long.parseLong(idnegeri),"enabled"," onChange=\"doChangeNegeri()\" ","tblhtphakmilik");
			    }
		    	//socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeKementerian()\" ");
		    	//socAgensi = HTML.SelectAgensiByKementerian("socAgensi",id_kementerian,Long.parseLong("1"),"");
				
			    socDaerah = HTML.SelectDaerahByNegeri(idnegeri,"iddaerah"," onChange=\"doChangeDaerah()\" ");
		    	
			    //this.context.put("socUrusan",socUrusan);
		    	String strdate = "";
		    	strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
		    	this.context.put("sekarang",strdate);
			    this.context.put("nofail", "");  
				this.context.put("pageMode","0");

	    	}else if ("doChangeDaerah".equals(submit)){
		    	template_name = "app/htp/frmCukaiSenarai.jsp";
			    String idnegeri = getParam("idnegeri");
			    String iddaerah = getParam("iddaerah");
			    //String[] status = request.getParameterValues("status");
			    //String status1 = getParam("status1");
			    //String status2 = getParam("status2");
			    //String status3 = getParam("status3");
			    
			    //for(int i=0; i<status.length; i++){
			    //	 System.out.println("STATUS :: "+status[i]);
			  //  }
			   
			    if(idnegeri == ""){
				    socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");
				    //socNegeri = HTML.SelectNegeri("socNegeri");
			    }else{	
				    socNegeri = UtilHTML.SelectNegeri("idnegeri",Long.parseLong(idnegeri),"enabled"," onChange=\"doChangeNegeri()\" ","tblhtphakmilik");
			    }
			    if(iddaerah == ""){
				    socDaerah = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");
				    //socNegeri = HTML.SelectNegeri("socNegeri");
			    }else{	
				    socDaerah =HTML.SelectDaerahByNegeri(idnegeri, "iddaerah", Long.parseLong(iddaerah), "enabled", " onChange=\"doChangeDaerah()\" ");
			    }
			    socMukim = HTML.SelectMukimByDaerah(iddaerah,"idmukim");
		    	
			    //this.context.put("socUrusan",socUrusan);
		    	String strdate = "";
		    	strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
		    	this.context.put("sekarang",strdate);
			    this.context.put("nofail", "");  
				this.context.put("pageMode","0");

	    	}else if("cukaiterperinci".equals(submit)){
		    	myLog.info(this.className+":cukaiterperinci::");
		    	template_name = PATH+"frmCukaiSenaraiTerperinci.jsp";	
		    	//myLog.info(this.className+":idNegeri::"+idNegeri);
		    	
//		    	String[] statusq = new String[3];
		    	String[] statusq = this.request.getParameterValues("status");
		    	if(statusq!=null){
		    	//System.out.println("status :: "+statusq.length);
		    	
		    	 for(int i = 0; i < statusq.length; i++){
		    		 if (statusq[i].equalsIgnoreCase("pembelian")){
		    			 pembelian = statusq[i];
		    		 }
		    		 else if (statusq[i].equalsIgnoreCase("pajakan")){
		    			 pajakan = statusq[i];
		    		 }
		    		 else if (statusq[i].equalsIgnoreCase("rizab")){
		    			 rizab = statusq[i];
		    		 }
		    		 else {
		    			
		    			 Hashtable data = null;
		    			 list = (Vector)FrmCukaiSenaraiData.getSenaraiCukai("0","",Long.parseLong(idNegeri),data);
		    			 this.context.put("senaraiCukai", list);
		    		 } 
		    		 //template_name = "app/htp/frmSenaraiCukaiBaru.jsp";
		    	 	}
		    	 
		    	 
		    	}
		    	 
//		    	 PEMBELIAN = statusq[0];
//		    	 B = statusq[1];
		    	 //C = statusq[2];
		    	System.out.println("A ::::" +pembelian);
		    	System.out.println("B ::::" +pajakan);
		    	System.out.println("C ::::" +rizab);
		    	
		    	idNegeri = (getParam("idnegeri")=="") ? "0" : getParam("idnegeri");
				idDaerah = (getParam("iddaerah")=="") ? "0" : getParam("iddaerah");
				idMukim = (getParam("idmukim")=="") ? "0" : getParam("idmukim");

				Hashtable data = null;
				data = new Hashtable();				
				data.put("status1",pembelian);
				data.put("status2",pajakan);
				data.put("status3",rizab);
				
		    	Vector vecNegeri=null;
		    	vecNegeri = ekptg.helpers.DB.getNegeri(idNegeri);
				Tblrujnegeri h = (Tblrujnegeri) vecNegeri.get(0);

				//String carian = getParam("NamaPemohon");
				//ListPenyata(session, idFail, carian);
				list = (Vector)FrmCukaiSenaraiData.getSenaraiCukai("0","",Long.parseLong(idNegeri),data);
				//mylog.info(list);
		    	this.context.put("infoNegeri", h);
			    //this.context.put("senaraicukai", list);
				setupPage(session,action,list);
		    
		    }else{

		    	String[] statusq = this.request.getParameterValues("status");
		    	if(statusq!=null){
			    	 for(int i = 0; i < statusq.length; i++){
			    		 if (statusq[i].equalsIgnoreCase("pembelian")){
			    			 pembelian = statusq[i];
			    		 
			    		 }else if (statusq[i].equalsIgnoreCase("pajakan")){
			    			 pajakan = statusq[i];
			    		 
			    		 }else if (statusq[i].equalsIgnoreCase("rizab")){
			    			 rizab = statusq[i];
			    		 
			    		 }else {
			    			
			    			 Hashtable data = null;
			    			 list = (Vector)FrmCukaiSenaraiData.getSenaraiCukai("0","",Long.parseLong(idNegeri),data);
			    			 this.context.put("senaraiCukai", list);
			    		 
			    		 } 
			    		 
			    	 }
		    	 		    	 
		    	}
		    	// Double.parseDouble(arg0);
//		    	 PEMBELIAN = statusq[0];
//		    	 B = statusq[1];
		    	 //C = statusq[2];
		    	System.out.println("A ::::" +pembelian);
		    	System.out.println("B ::::" +pajakan);
		    	System.out.println("C ::::" +rizab);
		    	
		    	idNegeri = (getParam("idnegeri")=="") ? "0" : getParam("idnegeri");
				idDaerah = (getParam("iddaerah")=="") ? "0" : getParam("iddaerah");
				idMukim = (getParam("idmukim")=="") ? "0" : getParam("idmukim");

				Hashtable data = null;
				data = new Hashtable();				
				data.put("status1",pembelian);
				data.put("status2",pajakan);
				data.put("status3",rizab);
				
		    	Vector vecNegeri=null;
		    	vecNegeri = ekptg.helpers.DB.getNegeri(idNegeri);
				Tblrujnegeri h = (Tblrujnegeri) vecNegeri.get(0);

				list = (Vector)FrmCukaiSenaraiData.getSenaraiCukai("0","",Long.parseLong(idNegeri),data);
				this.context.put("infoNegeri", h);
			    setupPage(session,action,list);
		    	
		    }
			this.context.put("idNegeri", idNegeri);
			this.context.put("idDaerah", idDaerah);
			this.context.put("idMukim", idMukim);

	    	
	    }else{ // !=submit
//	    	if("0->0".equals(langkah)){ //carian
//	    	    System.out.println("FrmPajakanKecilA: !=submit::0->0");
//
//	    		String nofail = getParam("nofail");
//	    		template_name = "app/htp/frmPajakanKecilSenaraiFail.jsp";
//	    		senaraiFail = FrmSenaraiFailPajakanKecilData.getList(nofail);
//	    		this.context.put("senaraiList", senaraiFail);  
//
//	    	}else if("0->1".equals(langkah)){
//	    	    System.out.println("FrmPajakanKecilA: !=submit::0->1");   		
//		    	template_name = "app/htp/frmPajakanKecilPendaftaran.jsp";
//		    	
//			    this.context.put("socSeksyen","3");
//
//		    	//perjanjian 44
//		    	this.context.put("idsuburusan","44");
//		    	this.context.put("idurusan","309");
//		     	//socKementerian = HTML.SelectKementerian("sockementerian");
//		     	
//		    	String strdate = "";
//		    	strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
//		    	this.context.put("sekarang",strdate);
//				//pageMode = Integer.parseInt(getParam("pagemode"));
//			    this.context.put("pageMode", "0");  
//			    this.context.put("nofail", "");  
//
//			    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh","aktif");
//			    this.context.put("senaraiSemakan",semakanSenarai );
//
//	    	  socAgensi = HTML.SelectAgensiByKementerian("socAgensi",null,Long.parseLong("1"),"");
//			     // }
//		    
//		    
//			    this.context.put("socNegeri",socNegeri);
//			    this.context.put("socKementerian",socKementerian);
//			    this.context.put("socAgensi",socAgensi);
//	    		
//	    	}else if("0->-1".equals(langkah)){
//	    	    myLog.info("FrmPajakanKecilA: !=submit::0->-1");
//	
//		    }else{
//	    		template_name = "app/htp/frmCukaiSenarai.jsp";
//	    	}
    		template_name = PATH+"frmCukaiSenarai.jsp";
	    	
		}
    	this.context.put("selectNegeri",socNegeri);
    	this.context.put("selectDaerah",socDaerah);
    	this.context.put("selectMukim",socMukim);

	    return template_name;
	    
	  }

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
		this.context.put("senaraicukai",paging.getPage(page));
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

}
