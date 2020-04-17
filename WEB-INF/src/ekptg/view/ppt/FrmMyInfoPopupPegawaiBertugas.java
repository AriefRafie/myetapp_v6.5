package ekptg.view.ppt;


import java.util.Hashtable;
import java.util.Vector;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppt.FrmUPTSek8BorangFData;
import ekptg.model.ppt.FrmUPTSek8NotisAwamData;
import ekptg.model.ppt.MyInfoPPTData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */


public class FrmMyInfoPopupPegawaiBertugas extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmMyInfoPopupPegawaiBertugas.class);
	
	PPTHeader header = new PPTHeader();
	MyInfoPPTData model = new MyInfoPPTData();
	
	@SuppressWarnings("unchecked")
	Vector listPegawaiBertugas = new Vector();
	@SuppressWarnings("unchecked")
	Vector listHM = new Vector();
	@SuppressWarnings("unchecked")
	Vector listPB = new Vector();
	
	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public String doTemplate2() throws Exception{
		
		//HttpSession session = request.getSession();

    	//default
    	String vm = "";
    	String screenPopup = "";
    	String id_permohonan = getParam("id_permohonan");

		String flag_subjaket = "";
    	String idpermohonan = getParam("id_permohonan");
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			flag_subjaket = dh.get("flag_subjaket").toString();
		}
		
		//popup type (myinfo/notis awam)
    	String type = getParam("type");
    	
    	if(type.equals("notis")){
    		//untuk Skrin Notis Awam seksyen 8
        	String id_notisawam = getParam("id_notisawam");
        	context.put("id_notisawam",id_notisawam);
        	
        	//list hakmilik notis
        	listHM = FrmUPTSek8NotisAwamData.getListHM(id_notisawam);
        	context.put("listHM", listHM);
        	
        	//screen jsp
        	screenPopup = "app/ppt/frmNotisPopupListHakmilik.jsp";
        	
    	}else if(type.equals("borange")){
    		//untuk Skrin Borang E seksyen 8
        	String id_borange = getParam("id_borange");
    		
        	//list hakmilik borang e
        	listHM = FrmUPTSek8BorangFData.getListHM(id_borange);
        	context.put("listHM", listHM);
    		
        	//screen jsp
        	screenPopup = "app/ppt/frmNotisPopupListHakmilik.jsp";
        	
    	}else if(type.equals("borangf")){
    		//untuk Skrin Borang F seksyen 8
        	String id_borangf = getParam("id_borangf");
    		
        	//list hakmilik borang e
        	listPB = FrmUPTSek8BorangFData.getListPB(id_borangf);
        	context.put("listPB", listPB);
    		
        	//screen jsp
        	screenPopup = "app/ppt/BorangEdanF/frmNotisPopupListPB.jsp";
        	
    	}else{
    		
    		//item untuk my info
        	listPegawaiBertugas = model.getListPegawaiBertugas(id_permohonan);
    		context.put("listPegawaiBertugas", listPegawaiBertugas);	
    		
    		//screen jsp
    		screenPopup = "app/ppt/frmMyInfoPopupPegawaiBertugas.jsp";
    	}
    	
    	//screen
    	vm = screenPopup;
    
    	myLogger.info("vm : "+vm);
    	context.put("id_permohonan",id_permohonan);
    	context.put("flag_subjaket",flag_subjaket);
    	
    	return vm;
    		
	    }//close public template
	
	
//--METHOD--//	
	
}//close class
