package ekptg.ppk.kpi;


import java.util.Vector;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmKPIPopupMenunggu extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmKPIPopupMenunggu.class);
	
	//model
	FrmKPIUtamaModel model = new FrmKPIUtamaModel();
	
	@Override
	public String doTemplate2() throws Exception{
		
		//HttpSession session = request.getSession();

    	//Utils helper
    	UtilsItem();
    	
    	//default
    	String vm = "";
		
    	//screen jsp
    	String screenPopup = "app/kpippk/frmKPIPopupMenunggu.jsp";

    	//anchor
    	anchor();
    	
    	//item
    	String id_suburusan = getParam("id_suburusan");
    	String tajukPopup = getParam("title");

    	//get list item
    	getListItem(id_suburusan);
    		
    	//screen
    	vm = screenPopup;
    
    	context.put("id_suburusan",id_suburusan);
    	context.put("tajukPopup",tajukPopup);
    	return vm;
    		
	    }//close public template
	
	
//--METHOD--//	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getListItem(String id_suburusan) throws Exception{
		
		Vector totalTunggu = new Vector();
		totalTunggu.clear();
		
		String jenisProsesTunggu = getParam("type");
		String bilHari1 = getParam("date1");
		String bilHari2 = getParam("date2");
		String tarikhMula = getParam("dateStart");
		String tarikhAkhir = getParam("dateEnd");
		String id_pejabatjkptg = getParam("id_pejabatjkptg");
		String level = getParam("level");
		
		model.setTotalTunggu(id_pejabatjkptg,id_suburusan,tarikhMula,tarikhAkhir,jenisProsesTunggu,bilHari1,bilHari2,"list",level);
		totalTunggu = model.getTotalTunggu();
		context.put("saiz_listFail", totalTunggu.size());
		context.put("listFail", totalTunggu);
		
	}//close getListItem
	
	private void anchor() throws Exception{
		String ScreenLocation = getParam("ScreenLocation");
    	String CursorPoint = getParam("CursorPoint");    	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
	}//close anchor
	
	private void UtilsItem() throws Exception{
		context.put("Util", new lebah.util.Util());
    	context.put("Utils", new ekptg.helpers.Utils());
	}//close UtilsItem
	
}//close class
