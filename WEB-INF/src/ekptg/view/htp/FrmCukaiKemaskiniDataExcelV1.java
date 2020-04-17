package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.htp.cukai.FrmCukaiKemaskiniDataBaru;
import ekptg.model.htp.cukai.FrmCukaiSenaraiFailExcelUpload;

@SuppressWarnings("serial")
public class FrmCukaiKemaskiniDataExcelV1 extends AjaxBasedModule{

	
	static Logger mylog = Logger.getLogger(ekptg.view.htp.FrmCukaiKemaskiniDataExcelV1.class);
	@Override
	public String doTemplate2() throws Exception {
		String template_name = "app/htp/cukai/frmCukaiKemaskiniDataExcel2V1.jsp";
		
		HttpSession session = this.request.getSession();
		String action = getParam("action");
		String submit = getParam("command");
		Vector SenaraiFailTemp = null;
		Vector SenaraiFailOrig = null;
		
		//SenaraiFail = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel();
		//this.context.put("SenaraiFail", SenaraiFail);
		
		SenaraiFailOrig = FrmCukaiSenaraiFailExcelUpload.CukaiSenaraiKemaskiniFail();
		this.context.put("SenaraiFailOrig", SenaraiFailOrig);
		/**
		 * senarai fail temp
		 */
		SenaraiFailTemp = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel();
		this.context.put("SenaraiFailTemp", SenaraiFailTemp);
		setupPage2(session,action,SenaraiFailTemp);
		
		
	/*	if("doChanges".equals(submit)){
		
		template_name = "app/htp/frmCukaiKemaskiniDataExcel.jsp";
			
		SenaraiFail = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel();
		this.context.put("SenaraiFail", SenaraiFail);
		
		//setupPage(session,action,SenaraiFail);
		}*/
		
		if("CukaiKemaskini".equals(submit)){
/**
 * Simpan kemaskini data compare antara data Excel dng data Oracle			
 */
			Hashtable data = null;
			data = new Hashtable();
			String lebihan ="";
			String denda =""; 
			String tunggakan =""; 
			String taliair2 =""; 
			String taliparit2  ="";
			String cukai_kenabayar ="";
			
			lebihan = getParam("txtlebihan");
			denda = getParam("txtdenda");
			tunggakan = getParam("txttunggakan");
			taliair2 = getParam("txtcukaitaliair2");
			taliparit2 = getParam("txtcukaiparit2");
			cukai_kenabayar = getParam("txtJumBayaran2");
			//String cukai_taliair = getParam("cukaitaliair");
			//String cukai_parit = getParam("cukaiparit");
			//String cukai_kenabayar = getParam("cukaikenabayar");
			String senaraiNolot = getParam("senaraiNolot");
			String senaraiNO_HAKMILIKUPLOAD = getParam("senaraiNO_HAKMILIKUPLOAD");
			String senaraiID_HAKMILIKCUKAI = getParam("senaraiID_HAKMILIKCUKAI");
			String senaraiNO_HAKMILIK = getParam("senaraiNO_HAKMILIK");
			
			 Date now = new Date();
		     SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
		     String Year = formatter.format(now);
		     
		     mylog.info("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: Year ::: " + Year);
		     mylog.info("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: Tali Air ::: " +taliair2);
		     mylog.info("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: Parit ::: " +taliparit2);
		     mylog.info("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: Kena Bayar ::: " +cukai_kenabayar);
		     mylog.info("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: senaraiNolot ::: " +senaraiNolot);
		     mylog.info("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: senaraiNO_HAKMILIKUPLOAD ::: " +senaraiNO_HAKMILIKUPLOAD);
		     mylog.info("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: senaraiID_HAKMILIKCUKAI ::: " +senaraiID_HAKMILIKCUKAI);
		     mylog.info("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: senaraiNO_HAKMILIK ::: " +senaraiNO_HAKMILIK);
			
			data.put("tahun", Year);
			data.put("lebihan", lebihan);
			data.put("denda", denda);
			data.put("tunggakan", tunggakan);
			data.put("cukai_taliair", taliair2);
			data.put("cukai_parit", taliparit2);
			data.put("cukai_kenabayar", cukai_kenabayar);
			data.put("senaraiNolot", senaraiNolot);
			//data.put("senaraiNO_HAKMILIKUPLOAD", senaraiNO_HAKMILIKUPLOAD);
			//data.put("senaraiID_HAKMILIKCUKAI", senaraiID_HAKMILIKCUKAI);
			//data.put("senaraiNO_HAKMILIK", senaraiNO_HAKMILIK);
			
			FrmCukaiKemaskiniDataBaru.kemaskiniData(data);
			
			SenaraiFailOrig = FrmCukaiSenaraiFailExcelUpload.CukaiSenaraiKemaskiniFail();
			this.context.put("SenaraiFailOrig", SenaraiFailOrig);
		}else if("CetakSenaraiKemaskini".equals(submit)){
    		template_name = "app/htp/cukai/frmCukaiSenaraiTerperinci.jsp";
    		Vector senaraikemaskini = FrmCukaiSenaraiFailExcelUpload.getCukaiKemaskiniList();
    		this.context.put("senaraikemaskini", senaraikemaskini);
 		}
		setupPage(session,action,SenaraiFailOrig);
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

}
