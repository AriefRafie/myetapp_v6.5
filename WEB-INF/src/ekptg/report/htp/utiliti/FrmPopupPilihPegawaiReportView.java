package ekptg.report.htp.utiliti;

import java.util.Hashtable;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.IUtilHTML;
import ekptg.model.htp.UtilHTMLBean;
import ekptg.model.utils.IUserPegawai;
import ekptg.model.utils.UserPegawaiBean;


public class FrmPopupPilihPegawaiReportView extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
 	private IUserPegawai iUserPegawai = null;  
	private IUtilHTML iUtil = null;
	static Logger myLog = Logger.getLogger(ekptg.report.htp.utiliti.FrmPopupPilihPegawaiReportView.class);
	FrmPopupPilihPegawaiReportData dbReportHtp = new FrmPopupPilihPegawaiReportData();

	@Override
	public String doTemplate2() throws Exception {		
		//HttpSession session = request.getSession();		
		String vm = "";
//		Vector detailPegawai = new Vector();
//		detailPegawai.clear();
		
		String selectNoFail = getParam("selectNoFail");
		String report = getParam("report");
		String flagReport = getParam("flagReport");
		String idpermohonan = getParam("idpermohonan");
		String idpegawai = getParam("socPenolongDanPengarah");	
		String nama_pegawai = "";
		String id_jawatan = "";
		String jawatan = "";
		String emel = "";		
		//package helper
		context.put("Utils", new ekptg.helpers.Utils());
		
		if (idpegawai == null || idpegawai.trim().length() == 0 || idpegawai == ""){
			idpegawai = "0";
		}
		
		String submit = getParam("command");
    	myLog.info("submit=" + submit);
		if ("doChangePegawai".equals(submit)){		
			//dbReportHtp.setDetailPegawai(idpegawai);
    		//detailPegawai = dbReportHtp.getDetailPegawai();
    		//if(detailPegawai.size()!=0){
				//Hashtable dp = (Hashtable)detailPegawai.get(0);
				Hashtable<String, String> dp = getIUP().getSenaraiPegawai(idpegawai);
    			//nama_pegawai = (String)dp.get("nama_pegawai");
    			//id_jawatan = (String)dp.get("id_jawatan");
     			nama_pegawai = String.valueOf(dp.get("namapegawai"));
    			id_jawatan = String.valueOf(dp.get("idjawatan"));
     			jawatan = String.valueOf(dp.get("jawatan"));
    			emel = String.valueOf(dp.get("email"));
   			
    		//}
			
		}//doChangePegawai
		
		//context.put("selectPenolongDanPengarah",HTML.SelectPenolongDanPengarahHTP("socPenolongDanPengarah", Long.parseLong(idpegawai),null,"onChange=\"doChangePegawai();\""));	
		context.put("selectPenolongDanPengarah",getHTPUtil().SelectPegawaiMengikutSeksyen("3", "0", "socPenolongDanPengarah", Long.parseLong(idpegawai), null, "onChange=\"doChangePegawai();\""));
		context.put("idpermohonan", idpermohonan);
		context.put("report", report);
		context.put("flagReport", flagReport);
    	
		context.put("namaPegawai", nama_pegawai);
		context.put("idJawatan", id_jawatan);
		context.put("jawatanPegawai", jawatan);
		context.put("emelPegawai", emel);	
		//UNTUK PILIHAN JENIS NO FAIL
    	context.put("selectNoFail", selectNoFail);	
		//screen
		vm = "app/htp/6.0/utiliti/frmPopupCetakLaporan.jsp";
		//vm = "app/htp/utiliti/frmPopupCetakLaporan.jsp";
		myLog.info("tarikh kemaskini:20200923 0721");
		return vm;
		
	}//close template
	
	private IUserPegawai getIUP(){
		if(iUserPegawai== null)
			iUserPegawai = new UserPegawaiBean();
				
		return iUserPegawai;
			
	}		
	
	private IUtilHTML getHTPUtil(){
		if(iUtil==null){
			iUtil = new UtilHTMLBean();
		}
		return iUtil;
			
	}
	

}//close class
