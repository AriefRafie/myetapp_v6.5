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
import ekptg.model.htp.FrmCukaiSenaraiFailExcelUpload;
import ekptg.model.htp.cukai.FrmCukaiPenyataBean;
import ekptg.model.htp.cukai.ICukai;

@SuppressWarnings("serial")
public class FrmCukaiPelarasanHapusNegeri extends AjaxBasedModule{
	
	private final String PATH="app/htp/cukai/";
	static Logger myLog = Logger.getLogger(ekptg.view.htp.cukai.FrmCukaiPelarasanHapusNegeri.class);
	String socNegeri = "";
	String socDaerah = "";
	String socMukim = "";
	String idnegeri = "";
 	String iddaerah = "";
 	String idmukim = "";
    String isCarian = "tidak";
	String userId = "";
	private InternalUser iu = null;
	private ICukai iCukai = null;
	private String year = "";
	private String socTahun = "";

	@Override
	public String doTemplate2() throws Exception {
		String template_name = PATH+"frmCukaiPelarasanHapusSenarai.jsp";
		
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
		context.remove("SenaraiFailOrig");

		if("CukaiKemaskini".equals(submit)){

			Hashtable data = null;
			data = new Hashtable();
			String lebihan ="";
			String denda =""; 
			String tunggakan =""; 
			String taliair2 =""; 
			String taliparit2  ="";
			String cukai_kenabayar ="";
			String idCukaiTerperinci = "";
			
			//idCukaiTerperinci = getParam("id_cukaiterperinci");
			idCukaiTerperinci = getParam("id_cukaiterperincitemp");
			lebihan = getParam("txtlebihan");
			denda = getParam("txtdenda");
			tunggakan = getParam("txttunggakan");
			//taliair2 = getParam("txtcukaitaliair2");
			//taliparit2 = getParam("txtcukaiparit2");
			//cukai_kenabayar = getParam("txtJumBayaran2");
			taliair2 = getParam("cukaitaliair");
			taliparit2 = getParam("cukaiparit");
			cukai_kenabayar = getParam("cukaikenabayar");
			String senaraiNolot = getParam("senaraiNolot");
			String senaraiNO_HAKMILIKUPLOAD = getParam("senaraiNO_HAKMILIKUPLOAD");
			String senaraiID_HAKMILIKCUKAI = getParam("senaraiID_HAKMILIKCUKAI");
			String senaraiNO_HAKMILIK = getParam("senaraiNO_HAKMILIK");
			
			String year = getParam("tahun_upload");
		  			
			data.put("tahun", year);
			data.put("lebihan", lebihan);
			data.put("denda", denda);
			data.put("tunggakan", tunggakan);
			data.put("cukai_taliair", taliair2);
			data.put("cukai_parit", taliparit2);
			data.put("cukai_kenabayar", cukai_kenabayar);
			data.put("senaraiNolot", senaraiNolot);
			data.put("senaraiNO_HAKMILIKUPLOAD", senaraiNO_HAKMILIKUPLOAD);
			data.put("senaraiID_HAKMILIKCUKAI", senaraiID_HAKMILIKCUKAI);
			data.put("senaraiNO_HAKMILIK", senaraiNO_HAKMILIK);
			
			//FrmCukaiKemaskiniDataBaru.kemaskiniData(data);
			getICukai().cukaiTerperinciHapus(idCukaiTerperinci);
			SenaraiFailOrig = (Vector<?>)getICukai().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
			this.context.put("SenaraiFailOrig", SenaraiFailOrig);
			//template_name = "app/htp/frmCukaiKemaskiniDataExcel2.jsp";
			
		}
		else if("CetakSenaraiKemaskini".equals(submit)){
    		//template_name = "app/htp/frmCukaiSenaraiTerperinci.jsp";
//    		this.context.put("pagemode",0);
    		Vector senaraikemaskini = FrmCukaiSenaraiFailExcelUpload.getCukaiKemaskiniList();
    		this.context.put("senaraikemaskini", senaraikemaskini);
    		
    		//___page temp view kemaskini_________________________________________________________________________________________________________
//    		if("tempKemaskini".equalsIgnoreCase(submit)){
//    			this.context.put("senaraikemaskini", senaraikemaskini);
//    			this.context.put("pagemode",1);
//    		}
    		template_name = "app/htp/frmCukaiKemaskiniDataExcel2.jsp";
    		
		}else if("carian".equals(submit)){
			isCarian = "ya";		
			//SenaraiFailOrig = FrmCukaiSenaraiFailExcelUpload.CukaiSenaraiKemaskiniFailV1(idnegeri,iddaerah,idmukim);
			SenaraiFailOrig = (Vector<?>)getICukai().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
			this.context.put("SenaraiFailOrig", SenaraiFailOrig);
		
		}else{
			isCarian = getParam("carian");
			if(isCarian.equals("ya")){
				SenaraiFailOrig = (Vector<?>)getICukai().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
				//this.context.put("SenaraiFailOrig", SenaraiFailOrig);
				isCarian = "ya";		
				//myLog.info(" 1st SenaraiFailOrig.size()="+SenaraiFailOrig)	;
			}
		}
		socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," disabled=\"disabled\" ","onChange=doChangeNegeriX();");
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

		return template_name;		
		
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
			iCukai = new FrmCukaiPenyataBean();
		}
		return iCukai;
		
	}	
}
