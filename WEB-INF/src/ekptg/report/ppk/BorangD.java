package ekptg.report.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;



public class BorangD extends EkptgReportServlet{
	 FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	 private static Logger myLog = Logger.getLogger(ekptg.report.ppk.BorangD.class);
	
	public BorangD (){
		super.setReportName("BorangDPNMB") ;
		super.setFolderName("ppk");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
		HttpServletResponse response, ServletContext context, Map parameters)
		throws Exception {
		String idfail = "";
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		System.out.println("**************** BorangDPNMB check ID SESSION : "+user_id);
		request.setAttribute("user_id", user_id);
		
		if (parameters.get("idfail") != null) {
		      idfail = parameters.get("idfail").toString();
		      parameters.put("idfail", idfail);
		}
		String idperbicaraan = "";
		if (parameters.get("idperbicaraan") != null) {
			idperbicaraan = parameters.get("idperbicaraan").toString();
			parameters.put("idperbicaraan", idperbicaraan);
		}else{
			parameters.put("idperbicaraan", getIdBicara(idfail));
		}
		
	    String flagVersion = (String)parameters.get("flagVersion"); 
	    if(flagVersion.equals("popupPNB") || flagVersion.equals("hantarPNB")){
	    	String nofail = logic.getNoFailByIdFail(idfail);
	    	doHantarPNB("BorangD",idfail,nofail,idperbicaraan,flagVersion);
	    }
	    
	}
	
	public String getIdBicara(String idfail) throws Exception{
		String sql = "";
		String returnValue = "";
		Db db = null;
		try {
			  db = new Db();
			  sql = "SELECT PKP.ID_PERBICARAAN " +
			  		" from " +
			  		" Tblpfdfail f,Tblppkpermohonan p,Tblppkperbicaraan pkp,Tblppkkeputusanpermohonan pkkp " +
			  		" where p.id_fail = f.id_fail " +
			  		" and PKKP.ID_PERMOHONAN = P.ID_PERMOHONAN " +
			  		" and PKKP.ID_KEPUTUSANPERMOHONAN = PKP.ID_KEPUTUSANPERMOHONAN  " +
			  		" and f.id_fail= '" + 
			  		idfail + "'" + 
			  		" order by PKP.TARIKH_BICARA ";    
			  Statement stmt = db.getStatement();
			  myLog.info("sql:"+sql);
			  ResultSet rs = stmt.executeQuery(sql);
			  while (rs.next()) {
				  returnValue = rs.getString("ID_PERBICARAAN");     
			  }

			  return returnValue;
		  } finally {
			  if (db != null)
				  db.close();
		  }
	  
	  }
	

}
