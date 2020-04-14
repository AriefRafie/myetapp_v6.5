package ekptg.ws.identity;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.identity.PPTSek8MobServiceStub.GetNoKadPengenalan;
import ekptg.ws.identity.PPTSek8MobServiceStub.GetNoKadPengenalanResponse;
import ekptg.ws.identity.PPTSek8MobServiceStub.PPTSek8Mob;





public class PPTSek8MobManager {
	static Logger myLogger = Logger.getLogger(PPTSek8MobManager.class);
	private static String kodIntegrasi = "JPN05";

	private static String flagMsg = null;
	private static String outputMsg = null;

	public Hashtable<String, String> getMaklumat(String noKadPengenalan) throws Exception {
		// TODO Auto-generated method stub
		Hashtable<String, String> hash = new Hashtable<String, String>();
		String url = DB.getUrlAgensi(kodIntegrasi);
		if (url.isEmpty()){
			return hash;
		}
		
		PPTSek8Mob form = getMaklumatFromAgency(noKadPengenalan, url);   
		myLogger.debug("url="+url);
		
		hash.put("noKadPengenalan", noKadPengenalan);
		hash.put("namaPB", form.getNamaPB() == null ? "" : form.getNamaPB());
		hash.put("jenisNoPengenalanPB", form.getJenisNoPengenalanPB() == null ? "" : form.getJenisNoPengenalanPB());
		hash.put("bangsa", form.getBangsa() == null ? "" : form.getBangsa());
		hash.put("warganegara", form.getWarganegara() == null ? "" : form.getWarganegara());
		hash.put("alamatPB", form.getAlamatPB() == null ? "" : form.getAlamatPB());
		hash.put("poskod", form.getPoskod() == null ? "" : form.getPoskod());
		hash.put("negeri", form.getNegeri() == null ? "" : form.getNegeri());
		hash.put("bandar", form.getBandar() == null ? "" : form.getBandar());
		return hash;
	}
	
	// START WEBSERVICES
	private static PPTSek8Mob getMaklumatFromAgency(String noKadPengenalan, String url) {

		PPTSek8MobServiceStub stub;
		PPTSek8Mob form = new PPTSek8Mob();

		try {
			stub = new PPTSek8MobServiceStub(url);

			GetNoKadPengenalan request = new GetNoKadPengenalan();
			request.setNoKadPengenalan(noKadPengenalan);
			
			GetNoKadPengenalan temp = new GetNoKadPengenalan();
			temp.setNoKadPengenalan(noKadPengenalan);
			
			GetNoKadPengenalanResponse response = stub.getNoKadPengenalan(temp);
			form = response.get_return();
			if (form != null && form.getNoKadPengenalan() != null) {
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
		PPTSek8MobManager.flagMsg = flagMsg;
	}

	public static String getOutputMsg() {
		return outputMsg;
	}

	public static void setOutputMsg(String outputMsg) {
		PPTSek8MobManager.outputMsg = outputMsg;
	}
}
