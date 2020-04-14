package ekptg.ws.gfmas;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.gfmas.PPKSek8BayaranPerintahServiceStub.GetNoResit;
import ekptg.ws.gfmas.PPKSek8BayaranPerintahServiceStub.GetNoResitResponse;
import ekptg.ws.gfmas.PPKSek8BayaranPerintahServiceStub.Sek8BayaranPerintah;

public class GfmasPPKSek8BayaranPerintahManager {

	static Logger myLogger = Logger.getLogger(GfmasPPKSek17BayaranPerintahManager.class);
	private static String kodIntegrasi = "INS02";

	private static String flagMsg = null;
	private static String outputMsg = null;

	public Hashtable<String, String> getSek8BayaranPerintah(String noResit) throws Exception {
		
		Hashtable<String, String> hash = new Hashtable<String, String>();
		String url = DB.getUrlAgensi(kodIntegrasi);
		if (url.isEmpty()){
			return hash;
		}
		
		Sek8BayaranPerintah form = getMaklumatFromAgency(noResit, url);
		myLogger.debug("url="+url);
		
		hash.put("noResit", noResit);
		hash.put("jumBayaran", form.getJumBayaran() == null ? "" : form.getJumBayaran());
		hash.put("tarikhBayaran", form.getTarikhBayaran() == null ? "" : form.getTarikhBayaran());
		hash.put("idCaraBayaran", form.getIdCaraBayaran() == null ? "" : form.getIdCaraBayaran());
		hash.put("caraBayaran", form.getCaraBayaran() == null ? "" : form.getCaraBayaran());
		return hash;
	}
	
	public Hashtable<String, String> getMaklumatHa(String noResit, String url) throws Exception {
		
		Hashtable<String, String> hash = new Hashtable<String, String>();
		if (url.isEmpty()){
			return hash;
		}
		
		Sek8BayaranPerintah form = getMaklumatFromAgency(noResit, url);
		myLogger.debug("url="+url);
		
		hash.put("noResit", noResit);
		hash.put("jumBayaran", form.getJumBayaran() == null ? "" : form.getJumBayaran());
		hash.put("tarikhBayaran", form.getTarikhBayaran() == null ? "" : form.getTarikhBayaran());
		hash.put("idCaraBayaran", form.getIdCaraBayaran() == null ? "" : form.getIdCaraBayaran());
		hash.put("caraBayaran", form.getCaraBayaran() == null ? "" : form.getCaraBayaran());
		return hash;
	}
	
	// START WEBSERVICES
	private static Sek8BayaranPerintah getMaklumatFromAgency(String noResit, String url) {

		PPKSek8BayaranPerintahServiceStub stub;
		Sek8BayaranPerintah form = new Sek8BayaranPerintah();

		try {
			stub = new PPKSek8BayaranPerintahServiceStub(url);

			GetNoResit request = new GetNoResit();
			request.setNoResit(noResit);
			
			GetNoResit temp = new GetNoResit();
			temp.setNoResit(noResit);
			
			GetNoResitResponse response = stub.getNoResit(temp);
			form = response.get_return();
			if (form != null && form.getNoResit() != null) {
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
		GfmasPPKSek8BayaranPerintahManager.flagMsg = flagMsg;
	}

	public static String getOutputMsg() {
		return outputMsg;
	}

	public static void setOutputMsg(String outputMsg) {
		GfmasPPKSek8BayaranPerintahManager.outputMsg = outputMsg;
	}
	
}
