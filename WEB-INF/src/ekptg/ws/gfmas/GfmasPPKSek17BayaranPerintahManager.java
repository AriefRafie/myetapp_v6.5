package ekptg.ws.gfmas;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.gfmas.PPKSek17BayaranPerintahServiceStub.GetNoResit;
import ekptg.ws.gfmas.PPKSek17BayaranPerintahServiceStub.GetNoResitResponse;
import ekptg.ws.gfmas.PPKSek17BayaranPerintahServiceStub.Sek17BayaranPerintah;

public class GfmasPPKSek17BayaranPerintahManager {
	
	static Logger myLogger = Logger.getLogger(GfmasPPKSek17BayaranPerintahManager.class);

//	private static String urlBayaranPerintah = "http://192.168.0.99:8080/axis2/services/PPKSek8BayaranPerintahService?wsdl";
	private static String kodIntegrasi = "INS04";

	private static String flagMsg = null;
	private static String outputMsg = null;

	public Hashtable<String, String> getSek17BayaranPerintah(String noResit) throws Exception {
		
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		String url = DB.getUrlAgensi(kodIntegrasi);
		if (url.isEmpty()){
			return hash;
		}
		
		Sek17BayaranPerintah form = getSek17BayaranPerintahFromAgency(noResit, url);
		hash.put("noResit", noResit);
		hash.put("jumBayaran", form.getJumBayaran() == null ? "" : form.getJumBayaran());
		hash.put("tarikhBayaran", form.getTarikhBayaran() == null ? "" : form.getTarikhBayaran());
		hash.put("caraBayaran", form.getCaraBayaran() == null ? "" : form.getCaraBayaran());

		return hash;
	}
	
	// START WEBSERVICES
	private static Sek17BayaranPerintah getSek17BayaranPerintahFromAgency(String noResit, String url) {

		PPKSek17BayaranPerintahServiceStub stub;
		Sek17BayaranPerintah form = new Sek17BayaranPerintah();

		try {
			stub = new PPKSek17BayaranPerintahServiceStub(url);

			GetNoResit request = new GetNoResit();
			request.setNoResit(noResit);
			
			GetNoResit temp = new GetNoResit();
			temp.setNoResit(noResit);
			GetNoResitResponse response = stub.getNoResit(temp);
			form = response.get_return();

			if (form != null && form.getNoResit() != null) {
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
		return form;
	}

	public static String getFlagMsg() {
		return flagMsg;
	}

	public static void setFlagMsg(String flagMsg) {
		GfmasPPKSek17BayaranPerintahManager.flagMsg = flagMsg;
	}

	public static String getOutputMsg() {
		return outputMsg;
	}

	public static void setOutputMsg(String outputMsg) {
		GfmasPPKSek17BayaranPerintahManager.outputMsg = outputMsg;
	}
	
}
