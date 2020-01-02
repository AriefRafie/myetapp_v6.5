package ekptg.view.meps;

import java.util.Hashtable;
import java.util.Vector;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.meps.PPK_modeldata;


public class PPK_LaporanPermohonanByUPK extends AjaxBasedModule{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6823560098383039788L;
	static Logger myLogger = Logger.getLogger(PPK_LaporanPermohonanByUPK.class);	
	// MODEL
	PPK_modeldata model = new PPK_modeldata();	
	
	@Override
	public String doTemplate2() throws Exception{		
		// DEFAULT
		String vm = "";       	
    	// VECTOR
    	Vector<Hashtable<String,String>> listPageDepan = new Vector<Hashtable<String,String>>();  	
    	listPageDepan.clear();
    	
    	// DECLARE VARIABLES
    	//String id_tahun = "";
    	//String id_bulan = "";
    	String id_tahunDari = "";
    	String id_tahunHingga = "";
    	String id_bulanDari = "";
    	String id_bulanHingga = "";  	
    	
    	
    	
    	// SKRIN JSP
    	String skrinDepanSenaraiLaporan = "app/meps/ppk/frmLaporanPermohonanByUPK.jsp";
   			
       	//GET LIST DATA
		listPageDepan = model.getListTotalKeseluruhanFail(id_tahunDari, id_tahunHingga, id_bulanDari ,id_bulanHingga);				
		context.put("upklist", model.getUpk());
   		context.put("PermohonanList", listPageDepan);
   		context.put("list_size", listPageDepan.size()); 
   		context.put("EkptgUtil", new ekptg.helpers.Utils());
    		
   		vm = skrinDepanSenaraiLaporan;    		 		
    	return vm;
     		
	}// close doTemplate2	
// METHOD --------------

	 
}// close class
