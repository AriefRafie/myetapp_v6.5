/**
 * 
 */
package ekptg.view.php2.kpi;





import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.util.DateUtil;

import org.apache.log4j.Logger;

import ekptg.model.php2.kpi.KPIData_PHP;
import ekptg.model.ppt.FrmPembatalanInternalData;

/**
 * @author Razman
 *
 */
public class FrmPopupKPIPHP extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(FrmPopupKPIPHP.class);
	private static final long serialVersionUID = 1L;
	FrmPembatalanInternalData logic = new FrmPembatalanInternalData();
	//KPIData logicKPI = new KPIData();
	KPIData_PHP logicKPI = new KPIData_PHP();
	
	@Override
	public String doTemplate2() throws Exception {
		
		
		HttpSession session = request.getSession();		
		String vm = "";
		//String main_command = getParam("command");			
		String tempat = getParam("tempat");		
		
		String txdTarikhMula = getParam("txdTarikhMula");
		String txdTarikhAkhir = getParam("txdTarikhAkhir");
		String id_urusan = getParam("id_urusan");
		String id_suburusan = getParam("id_suburusan");
		String kategori = getParam("kategori");
		String tajuk = getParam("tajuk");
		
		
		double range1 = Double.parseDouble(getParam("range1"));
		double range2 = Double.parseDouble(getParam("range2"));
		
		
		Vector list_KPI_POPUP = null;
		this.context.put("list_KPI_POPUP","");
		
		Vector list_status = null;
		this.context.put("list_status","");	
		
		
		context.put("DATEUTIL", new DateUtil());
		this.context.put("Util", new lebah.util.Util());
		
		myLogger.info("tempat:"+tempat);
	 	
		if(tempat.equals("1"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610199","32",txdTarikhMula,txdTarikhAkhir);	   
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);
		}
		
		if(tempat.equals("2"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610210","32",txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);		   		 
		}
		
		if(tempat.equals("3"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610201","32",txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);					 
		}
		
		if(tempat.equals("4"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610199","34",txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);					 
		}
		
		if(tempat.equals("5"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610202","34",txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);					 
		}
		
		if(tempat.equals("6"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610203","34",txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);					 
		}
		
		if(tempat.equals("7"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610206","34",txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);					 
		}
		
		if(tempat.equals("8"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610199","33",txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);					 
		}
		
		if(tempat.equals("9"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610201","33",txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);					 
		}
		
		if(tempat.equals("10"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610199",id_suburusan,txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);
		}
		
		if(tempat.equals("11"))
		{
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610201",id_suburusan,txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);
		}
		
		if(tempat.equals("12"))
		{       
        list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610214",id_suburusan,txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);
		}
		
		if(tempat.equals("13"))
		{       
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610198",id_suburusan,txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);
		}
		
		if(tempat.equals("14"))
		{       
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610235",id_suburusan,txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);
		}
		
		if(tempat.equals("15"))
		{       
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610213",id_suburusan,txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);
		}
		
		if(tempat.equals("16"))
		{       
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610206",id_suburusan,txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);
		}
		
		if(tempat.equals("17"))
		{       
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610237",id_suburusan,txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);
		}
		
		if(tempat.equals("18"))
		{       
		list_KPI_POPUP =logicKPI.list_KPI_MENUNGGU("1610238",id_suburusan,txdTarikhMula,txdTarikhAkhir);	    
		this.context.put("list_KPI_POPUP",list_KPI_POPUP);
		}
	
		
		this.context.put("range1",range1);
		this.context.put("range2",range2);
		this.context.put("kategori",kategori);
		this.context.put("tempat",tempat);
		this.context.put("tajuk",tajuk);
		
		list_status = logicKPI.list_status();
		this.context.put("list_status",list_status);
		
		vm = "app/php2/kpi/frmPopupKPI_PHP_NOFAIL.jsp";		
		
		return vm;
	}
}
