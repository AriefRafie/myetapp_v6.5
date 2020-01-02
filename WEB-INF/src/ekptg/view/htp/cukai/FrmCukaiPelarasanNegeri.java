package ekptg.view.htp.cukai;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.InternalUserUtil;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.entities.InternalUser;
import ekptg.model.htp.FrmCukaiKemaskiniDataBaru;
import ekptg.model.htp.FrmCukaiSenaraiFailExcelUpload;
import ekptg.model.htp.cukai.CukaiBean;
import ekptg.model.htp.cukai.ICukai;

@SuppressWarnings("serial")
public class FrmCukaiPelarasanNegeri extends AjaxBasedModule{
	
	private final String PATH="app/htp/cukai/";
	private ICukai iCukai = null;
	static Logger myLog = Logger.getLogger(ekptg.view.htp.cukai.FrmCukaiPelarasanNegeri.class);
	String socNegeri = "";
	String socDaerah = "";
	String socMukim = "";
	String idnegeri = "";
 	String iddaerah = "";
 	String idmukim = "";
    String isCarian = "tidak";
	String userId = "";
	private InternalUser iu = null;
	private String year = "";
	private String socTahun = "";
	private String bil ="1";

	@Override
	public String doTemplate2() throws Exception {
		String template_name = PATH+"frmCukaiKemaskiniDataExcel2.jsp";
		
		try{
			
		HttpSession session = this.request.getSession();
		String action = getParam("action");
		String submit = getParam("command");
		Vector SenaraiFailTemp = null;
		Vector SenaraiFailOrig = null;
		context.put("UTIL", new ekptg.helpers.Utils());
		
		userId = (String)session.getAttribute("_ekptg_user_id");
		iu = InternalUserUtil.getSeksyenId(userId);
		idnegeri = iu.getIdNegeri();
		//idnegeri = getParam("socNegeri");
	 	iddaerah = getParam("socDaerah");
	 	idmukim = getParam("socMukim");
	 	socTahun = getParam("Form_tahun")==null||getParam("Form_tahun")==""?lebah.util.Util.getDateTime(new Date(), "yyyy"):getParam("Form_tahun");
		/**
		 * Simpan kemaskini data compare antara data Excel dng data Oracle			
		 */		
		if("CukaiKemaskini".equals(submit)){

			Hashtable data = null;
			data = new Hashtable();
			String tunggakan =""; 
			String denda =""; 
			String taliair2 =""; 
			String taliparit2  ="";
			String lebihan ="";
			String cukai_perlubayar ="";
			String pengurangan ="";
			String cukaiKenaBayarLama ="";
			String cukaiJumlah ="";
			
			bil = getParam("bil");
			tunggakan = getParam("cukaitunggakan"+bil);
			denda = getParam("cukaidenda"+bil);
			//taliair2 = getParam("txtcukaitaliair2");
			//taliparit2 = getParam("txtcukaiparit2");
			//cukai_kenabayar = getParam("txtJumBayaran2");
			taliair2 = getParam("cukaitaliair"+bil);
			taliparit2 = getParam("cukaiparit"+bil);
			pengurangan = getParam("cukaipengurangan"+bil);
			cukai_perlubayar = getParam("cukaiperlubayar"+bil);
			cukaiKenaBayarLama = getParam("cukaikenabayarlama"+bil);
			lebihan = getParam("cukaipengecualian"+bil);
			cukaiJumlah = getParam("cukaijumlah"+bil);
			//String senaraiID_HAKMILIKCUKAI = getParam("senaraiID_HAKMILIKCUKAI"+bil);
			String senaraiID_HAKMILIKCUKAI = getParam("idhakmilikcukai"+bil);
			String senaraiNolot = getParam("senaraiNolot");
			String senaraiNO_HAKMILIKUPLOAD = getParam("senaraiNO_HAKMILIKUPLOAD");
			String senaraiNO_HAKMILIK = getParam("senaraiNO_HAKMILIK");
			//String year = getParam("tahun_upload");
			myLog.info("bil:"+bil);
			myLog.info("cukai_kenabayar:"+cukai_perlubayar);
			myLog.info("senaraiID_HAKMILIKCUKAI:"+senaraiID_HAKMILIKCUKAI);
			
			data.put("tahun", socTahun);
			data.put("tunggakan", Utils.RemoveComma(tunggakan));
			data.put("denda", Utils.RemoveComma(denda));
			data.put("pengurangan", Utils.RemoveComma(pengurangan));
			data.put("lebihan", Utils.RemoveComma(lebihan));
			data.put("cukai_taliair", Utils.RemoveComma(taliair2));
			data.put("cukai_parit", Utils.RemoveComma(taliparit2));
			data.put("cukai_kenabayar", Utils.RemoveComma(cukaiJumlah));
			data.put("cukaiPerluBayar", Utils.RemoveComma(cukai_perlubayar));
			data.put("senaraiNolot", senaraiNolot);
			data.put("senaraiID_HAKMILIKCUKAI", senaraiID_HAKMILIKCUKAI);
			data.put("cukaiKenaBayarLama", cukaiKenaBayarLama);
			data.put("senaraiNO_HAKMILIKUPLOAD", senaraiNO_HAKMILIKUPLOAD);
			data.put("senaraiNO_HAKMILIK", senaraiNO_HAKMILIK);
			
			FrmCukaiKemaskiniDataBaru.kemaskiniData(data);
			
			//SenaraiFailOrig = FrmCukaiSenaraiFailExcelUpload.CukaiSenaraiKemaskiniFail();
			//SenaraiFailOrig = FrmCukaiSenaraiFailExcelUpload.CukaiSenaraiKemaskiniFailV1(idnegeri,iddaerah,idmukim);
			//this.context.put("SenaraiFailOrig", SenaraiFailOrig);
			SenaraiFailOrig = getICukai().CukaiSenaraiKemaskiniFail(idnegeri,iddaerah,idmukim,socTahun);
			template_name = PATH+"frmCukaiKemaskiniDataExcel2.jsp";
			
		}else if("CetakSenaraiKemaskini".equals(submit)){
			context.remove("SenaraiFailOrig");
    		//template_name = "app/htp/frmCukaiSenaraiTerperinci.jsp";
//    		this.context.put("pagemode",0);
    		Vector senaraikemaskini = FrmCukaiSenaraiFailExcelUpload.getCukaiKemaskiniList();
    		this.context.put("senaraikemaskini", senaraikemaskini);
    		
    		//___page temp view kemaskini_________________________________________________________________________________________________________
//    		if("tempKemaskini".equalsIgnoreCase(submit)){
//    			this.context.put("senaraikemaskini", senaraikemaskini);
//    			this.context.put("pagemode",1);
//    		}
    		template_name = PATH+"frmCukaiKemaskiniDataExcel2.jsp";
    		
		}else if("carian".equals(submit)){
			isCarian = "ya";		
			//SenaraiFailOrig = FrmCukaiSenaraiFailExcelUpload.CukaiSenaraiKemaskiniFailV1(idnegeri,iddaerah,idmukim);
			SenaraiFailOrig = getICukai().CukaiSenaraiKemaskiniFail(idnegeri,iddaerah,idmukim,socTahun);
			this.context.put("SenaraiFailOrig", SenaraiFailOrig);
		
		}else if("cukaikemaskinisemua".equals(submit)){

			Hashtable data = null;
			data = new Hashtable();
			String lebihan ="";
			String denda =""; 
			String tunggakan =""; 
			String taliair2 =""; 
			String taliparit2  ="";
			String cukai_kenabayar ="";
			
			String[] lebihans = this.request.getParameterValues("txtlebihan");
			String[] dendas = this.request.getParameterValues("txtdenda");
			String[] tunggakans = this.request.getParameterValues("txttunggakan");
			String[] taliair2s = this.request.getParameterValues("cukaitaliair");
			String[] taliparit2s = this.request.getParameterValues("cukaiparit");
			String[] cukaiKenaBayars = this.request.getParameterValues("cukaikenabayar");
			String[] senaraiNoLots = this.request.getParameterValues("senaraiNolot");
			String[] senaraiNoHakmilikUploads = this.request.getParameterValues("senaraiNO_HAKMILIKUPLOAD");			
			String[] senaraiIdHakmilikCukais = this.request.getParameterValues("senaraiID_HAKMILIKCUKAI");			
			String[] senaraiNoHakmiliks = this.request.getParameterValues("senaraiNO_HAKMILIK");	
			String[] cukaiJumlahs = this.request.getParameterValues("cukaijumlah");
			
			String[] cbsemaks = this.request.getParameterValues("cb");
			String[] cbsemaks_ = this.request.getParameterValues("cb_");
			bil = getParam("bil");
 			//myLog.info("bil:"+bil);
 			//myLog.info("length:"+cbsemaks_.length);
          	if(cbsemaks_!=null){
        		for (int i = 0; i < cbsemaks_.length; i++) { 
        			if(cbsemaks_[i].equals("true")){
        				data.put("tahun", socTahun);
         			//myLog.info("cukaikenabayar"+i+":"+getParam("cukaikenabayar"+i));
         			//myLog.info("idhakmilik"+i+":"+getParam("idhakmilikcukai"+i));
         			//if(cbsemaks_[i].equals("true")){
         				//myLog.info("cukaiJumlahs:"+cukaiJumlahs[i]+","+cbsemaks_[i]);
         				//myLog.info("cukaikenabayar:"+getParam("cukaikenabayar"+(i+1)));
         				
         			//}
         			//data.put("lebihan", Utils.RemoveComma(lebihan));
//        			//data.put("lebihan", lebihans[i]);
//        			data.put("denda", Utils.RemoveComma(denda));
//        			data.put("tunggakan", Utils.RemoveComma(tunggakan));
//        			//data.put("denda", dendas[i]);
//        			//data.put("tunggakan", tunggakans[i]);
//        			data.put("cukai_taliair", Utils.RemoveComma(taliair2s[i]));
//        			data.put("cukai_parit", Utils.RemoveComma(taliparit2s[i]));
//        			data.put("cukai_kenabayar", Utils.RemoveComma(cukaiKenaBayars[i]));
//        			data.put("senaraiNolot", senaraiNoLots[i]);
//        			data.put("senaraiNO_HAKMILIKUPLOAD", senaraiNoHakmilikUploads[i]);
//        			data.put("senaraiID_HAKMILIKCUKAI", senaraiIdHakmilikCukais[i]);
//        			data.put("senaraiNO_HAKMILIK", senaraiNoHakmiliks[i]);			

        				data.put("tunggakan", Utils.RemoveComma(getParam("cukaitunggakan"+(i+1))));
        				data.put("denda", Utils.RemoveComma(getParam("cukaidenda"+(i+1))));
        				data.put("pengurangan", Utils.RemoveComma(getParam("cukaipengurangan"+(i+1))));
        				data.put("lebihan", Utils.RemoveComma(getParam("cukaipengecualian"+(i+1))));
        				data.put("cukai_taliair", Utils.RemoveComma(getParam("cukaitaliair"+(i+1))));
        				data.put("cukai_parit", Utils.RemoveComma(getParam("cukaiparit"+(i+1))));
        				data.put("cukai_kenabayar", Utils.RemoveComma(getParam("cukaijumlah"+(i+1))));
        				data.put("cukaiPerluBayar", Utils.RemoveComma(getParam("cukaiperlubayar"+(i+1))));
        				data.put("senaraiNolot", senaraiNoLots[i]);
        				data.put("senaraiID_HAKMILIKCUKAI", Utils.RemoveComma(getParam("senaraiID_HAKMILIKCUKAI"+(i+1))));
        				data.put("cukaiKenaBayarLama", Utils.RemoveComma(getParam("cukaikenabayarlama"+(i+1))));
        				data.put("senaraiNO_HAKMILIKUPLOAD", senaraiNoHakmilikUploads[i]);
        				data.put("senaraiNO_HAKMILIK", senaraiNoHakmiliks[i]);
        				myLog.info("data="+data);
        				FrmCukaiKemaskiniDataBaru.kemaskiniData(data); 
        				
        			}
        		}
        	} 

			SenaraiFailOrig = getICukai().CukaiSenaraiKemaskiniFail(idnegeri,iddaerah,idmukim,socTahun);
			template_name = PATH+"frmCukaiKemaskiniDataExcel2.jsp";
			
		}else{
			isCarian = getParam("carian");
			if(isCarian.equals("ya")){
				//SenaraiFailOrig = FrmCukaiSenaraiFailExcelUpload.CukaiSenaraiKemaskiniFailV1(idnegeri,iddaerah,idmukim);
				
				SenaraiFailOrig = getICukai().CukaiSenaraiKemaskiniFail(idnegeri,iddaerah,idmukim,socTahun);
				isCarian = "ya";		
			}

			
		}
		socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," disabled=\"disabled\" ","onChange=doChangeNegeriX(); ");
		socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong(iddaerah),null, "onChange=\"doChangeDaerahX()\"");
		socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim) , "");
	    
		this.context.put("socDaerah", socDaerah);
		this.context.put("socNegeri", socNegeri);
		this.context.put("socMukim", socMukim);		
		setupPage(session,action,SenaraiFailOrig);
		this.context.put("iscarian", isCarian);
		year = lebah.util.Util.getDateTime(new Date(), "yyyy");
		this.context.put("tahuncukai", Integer.parseInt(year));
		this.context.put("tahunparam", Integer.parseInt(socTahun));
		this.context.put("count", SenaraiFailOrig);

		//System.out.println(template_name);
		return template_name;		
		
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception("[HTP CUKAI PELARASAN] SILA LOGIN SEMULA");
			
		}
	}
	
//	public void setupPage(HttpSession session,String action,Vector list) {
//		try {
//		
//		this.context.put("totalRecords",list.size());
//		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
//		
//		int itemsPerPage;
//		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
//			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
//		} else {
//			itemsPerPage = (Integer)this.context.get("itemsPerPage");
//		}
//	    
//	    if ("getNext".equals(action)) {
//	    	page++;
//	    } else if ("getPrevious".equals(action)) {
//	    	page--;
//	    } else if ("getPage".equals(action)) {
//	    	page = getParamAsInteger("value");
//	    } else if ("doChangeItemPerPage".equals(action)) {
//	       itemsPerPage = getParamAsInteger("itemsPerPage");
//	    }
//	    	
//	    Paging paging = new Paging(session,list,itemsPerPage);
//		
//		if (page > paging.getTotalPages()) page = 1; //reset page number
//		this.context.put("SenaraiFail",paging.getPage(page));
//	    this.context.put("page", new Integer(page));
//	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
//	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
//	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
//	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
//	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
//	        
//		} catch (Exception e) {
//			e.printStackTrace();
//			this.context.put("error",e.getMessage());
//		}	
//	}
	
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
		this.context.put("SenaraiFailTemp",paging.getPage(page));
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
	
	private ICukai getICukai(){
		if(iCukai==null){
			iCukai = new CukaiBean();
		}
		return iCukai;
		
	}

}
