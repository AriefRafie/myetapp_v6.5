package ekptg.ws.insolvensi;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.insolvensi.InsWebServiceStub.GetMaklumat;
import ekptg.ws.insolvensi.InsWebServiceStub.GetMaklumatResponse;
import ekptg.ws.insolvensi.InsWebServiceStub.InsForm;


public class InsManager {
	
	static Logger myLogger = Logger.getLogger(InsManager.class);
	//private static String url = "http://192.168.0.99:8080/axis2/services/InsWebService?wsdl";
	private static String kodIntegrasi = "INS01";

	private static String flagMsg = null;
	private static String outputMsg = null;
	
	public Hashtable<String, String> getMaklumat (String numIc) throws Exception {
		// TODO Auto-generated method stub
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		String url = DB.getUrlAgensi(kodIntegrasi);
		if (url.isEmpty()) {
			return hash;
		}
		InsForm ins = getMaklumatWartaFromAgency(numIc, url);
		myLogger.debug("url="+url);
		
		hash.put("numIc", ins.getNumIc() == null ? "" : ins.getNumIc());
		hash.put("nama", ins.getNama() == null ? "" : ins.getNama());
		hash.put("statusBangkrap", ins.getStatusBangkrap() == null ? "" : ins.getStatusBangkrap());
		hash.put("tarikhSebutanMahkamah", ins.getTarikhSebutanMahkamah() == null ? "" : ins.getTarikhSebutanMahkamah());
		hash.put("noInsolvensi", ins.getNoInsolvensi() == null ? "" : ins.getNoInsolvensi());
		hash.put("tarikhPerintahPenerimaan", ins.getTarikhSebutanMahkamah() == null ? "" : ins.getTarikhSebutanMahkamah());
		hash.put("cawangan", ins.getCawangan() == null ? "" : ins.getCawangan());
		hash.put("catatan", ins.getCatatan() == null ? "" : ins.getCatatan());
		return hash;
	}
	private static InsForm getMaklumatWartaFromAgency(String numIc, String url) {

		InsWebServiceStub stub;
		InsForm ins = new InsForm();

		try {
			stub = new InsWebServiceStub(url);

			GetMaklumat request = new GetMaklumat();
			request.setNumIc(numIc);
			
			GetMaklumat temp = new GetMaklumat();
			temp.setNumIc(numIc);
			GetMaklumatResponse response = stub.getMaklumat(temp);
			ins = response.get_return();
			if (ins != null && ins.getNumIc() != null) {
				//insertHakmilikFromEtanah(idPermohonanSimati, noResit, hakmilik);
			} else {
				setFlagMsg("N");
				setOutputMsg("HAKMILIK TIDAK DITEMUI");
			}

		} catch (Exception e) {
			e.printStackTrace();
			setFlagMsg("N");
			setOutputMsg(e.toString());
		}
		return ins;
	}

	public static String getFlagMsg() {
		return flagMsg;
	}

	public static void setFlagMsg(String flagMsg) {
		InsManager.flagMsg = flagMsg;
	}

	public static String getOutputMsg() {
		return outputMsg;
	}

	public static void setOutputMsg(String outputMsg) {
		InsManager.outputMsg = outputMsg;
	}
}