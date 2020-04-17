package ekptg.report.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;
import ekptg.report.EkptgReportServlet;

public class BorangS extends EkptgReportServlet{
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public BorangS (){
		super.setReportName("BorangS") ;
		super.setFolderName("ppk");
	}

	@Override
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, ServletContext context, Map parameters) throws Exception {
		String idfail = "";

		
		System.out.println("Read Here Borang S");

		String idsimati = "";

		if (parameters.get("idfail") != null){
			idfail = parameters.get("idfail").toString();
		}
		if (parameters.get("idsimati") != null){
			idsimati = parameters.get("idsimati").toString();
		}
		//System.out.println("wwwwwwww" +idfail +"simati=="+idsimati);
		logic.getPemohonTerdahulu(idfail);
		//System.out.println("cccccccc");
		Vector pemohon = new Vector(); 
		pemohon = logic.getBeanPemohonTerdahulu();	
		//System.out.println("pemohon===" +pemohon);
		Hashtable h = new Hashtable();
		if(pemohon.size() != 0){
			
			h = (Hashtable)pemohon.get(0);
			parameters.put("namaPemohon",h.get("namaPemohon"));
			parameters.put("alamatPemohon",h.get("alamatPemohon"));
			parameters.put("kpPemohon", h.get("kpPemohon"));
						
		}
			//System.out.println("11111111111111");
		if (logic.getSebab(idfail).isEmpty()){		
			super.setErrorMsg("Sila pastikan Senarai Semak lengkap. Sekiranya tujuan permohonan adalah harta ketinggalan sila pastikan harta telah didaftar.");
		}else{			
			parameters.put("sebab",logic.getSebab(idfail).replaceAll("&", "&#38;")); 
		}
		//System.out.println("222222222222222222222");
		String idperbicaraan = "";
		if (parameters.get("idperbicaraan") != null) {
			idperbicaraan = parameters.get("idperbicaraan").toString();
			parameters.put("idperbicaraan", idperbicaraan);
		}else{
			parameters.put("idperbicaraan", getIdBicara(idfail));
		}
		//System.out.println("333333333333");
		String flagVersion = (String)parameters.get("flagVersion");
		//System.out.println("flagVersion==="+flagVersion);
	    if(flagVersion.equals("popupPNB") || flagVersion.equals("hantarPNB")){
	    	String nofail = logic.getNoFailByIdFail(idfail);
	    doHantarPNB("BorangS",idfail,nofail,idperbicaraan,flagVersion);
	    }
	   // System.out.println("4444444444444");
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
			  //myLog.info("sql:"+sql);
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
