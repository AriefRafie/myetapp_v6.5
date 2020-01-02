package ekptg.model.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.Paging;

@SuppressWarnings("serial")
public class FrmCukaiKemaskiniDataExcel extends AjaxBasedModule{

	
	@Override
	public String doTemplate2() throws Exception {
		String template_name = "app/htp/frmCukaiKemaskiniDataExcel2.jsp";		
		HttpSession session = this.request.getSession();
		String action = getParam("action");
		String submit = getParam("command");
		Vector SenaraiFail = null;
		Vector SenaraiFailOrig = null;
		SenaraiFailOrig = FrmCukaiSenaraiFailExcelUpload.CukaiSenaraiKemaskiniFail();
		this.context.put("SenaraiFailOrig", SenaraiFailOrig);
		
		if("CukaiKemaskini".equals(submit)){
/**
 * Simpan kemaskini data compare antara data Excel dng data Oracle			
 */
			Hashtable data = null;
			data = new Hashtable();
			
			String cukai_taliair = getParam("cukaitaliair");
			String cukai_parit = getParam("cukaiparit");
			String cukai_kenabayar = getParam("cukaikenabayar");
			String senaraiNolot = getParam("senaraiNolot");
			String senaraiNO_HAKMILIKUPLOAD = getParam("senaraiNO_HAKMILIKUPLOAD");
			String senaraiID_HAKMILIKCUKAI = getParam("senaraiID_HAKMILIKCUKAI");
			String senaraiNO_HAKMILIK = getParam("senaraiNO_HAKMILIK");
			
		    String year = getParam("tahun_upload");
		     
		   /* System.out.println("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: Year ::: " + Year);
			System.out.println("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: Tali Air ::: " +cukai_taliair);
			System.out.println("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: Parit ::: " +cukai_parit);
			System.out.println("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: Kena Bayar ::: " +cukai_kenabayar);
			System.out.println("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: senaraiNolot ::: " +senaraiNolot);
			System.out.println("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: senaraiNO_HAKMILIKUPLOAD ::: " +senaraiNO_HAKMILIKUPLOAD);
			System.out.println("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: senaraiID_HAKMILIKCUKAI ::: " +senaraiID_HAKMILIKCUKAI);
			System.out.println("FrmCukaiKemaskiniDataExcel : CukaiKemaskini :: senaraiNO_HAKMILIK ::: " +senaraiNO_HAKMILIK);
		*/	
			data.put("tahun", year);
			data.put("cukai_taliair", cukai_taliair);
			data.put("cukai_parit", cukai_parit);
			data.put("cukai_kenabayar", cukai_kenabayar);
			data.put("senaraiNolot", senaraiNolot);
			data.put("senaraiNO_HAKMILIKUPLOAD", senaraiNO_HAKMILIKUPLOAD);
			data.put("senaraiID_HAKMILIKCUKAI", senaraiID_HAKMILIKCUKAI);
			data.put("senaraiNO_HAKMILIK", senaraiNO_HAKMILIK);
			
			FrmCukaiKemaskiniDataBaru.kemaskiniData(data);
			
			SenaraiFailOrig = FrmCukaiSenaraiFailExcelUpload.CukaiSenaraiKemaskiniFail();
			this.context.put("SenaraiFailOrig", SenaraiFailOrig);
		}
		else if("CetakSenaraiKemaskini".equals(submit)){
    		template_name = "app/htp/frmCukaiSenaraiTerperinci.jsp";
    		
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

}
