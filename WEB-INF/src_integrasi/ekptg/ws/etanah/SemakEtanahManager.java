package ekptg.ws.etanah;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.etanah.SemakEtanahWebServiceStub.Form;
import ekptg.ws.etanah.SemakEtanahWebServiceStub.GetNoFail;
import ekptg.ws.etanah.SemakEtanahWebServiceStub.GetNoFailResponse;



public class SemakEtanahManager {
	
	static Logger myLogger = Logger.getLogger(SemakEtanahManager.class);
	private static String kodIntegrasi = "ETH02";

	private static String flagMsg = null;
	private static String outputMsg = null;
	
	public Hashtable<String, String> getMaklumat (String noFail) throws Exception {
		// TODO Auto-generated method stub
		Hashtable<String, String> hash = new Hashtable<String, String>();
		String url = DB.getUrlAgensi(kodIntegrasi);
		if (url.isEmpty()) {
			return hash;
		}
		Form ins = getMaklumatFromAgency(noFail, url);
		myLogger.debug("url="+url);
		
		hash.put("noFail",noFail);
		hash.put("idHakmilik", ins.getIdHakmilik() == null ? "" : ins.getIdHakmilik());
		hash.put("luas", ins.getLuas() == null ? "" : ins.getLuas());
		hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
		hash.put("daerah", ins.getDaerah() == null ? "" : ins.getDaerah());
		hash.put("mukim", ins.getMukim() == null ? "" : ins.getMukim());
		hash.put("noLot", ins.getNoLot() == null ? "" : ins.getNoLot());
		hash.put("bahagianSimati", ins.getBahagianSimati() == null ? "" : ins.getBahagianSimati());
		hash.put("jenisPembahagian", ins.getJenisPembahagian() == null ? "" : ins.getJenisPembahagian());
		return hash;
	}
	private static Form getMaklumatFromAgency(String noFail, String url) {

		SemakEtanahWebServiceStub stub;
		Form ins = new Form();

		try {
			stub = new SemakEtanahWebServiceStub(url);

			GetNoFail request = new GetNoFail();
			request.setNoFail(noFail);
			
			GetNoFail temp = new GetNoFail();
			request.setNoFail(noFail);
			GetNoFailResponse response = stub.getNoFail(temp);
			ins = response.get_return();
			if (ins != null && ins.getNoFail() != null) {
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
		SemakEtanahManager.flagMsg = flagMsg;
	}

	public static String getOutputMsg() {
		return outputMsg;
	}

	public static void setOutputMsg(String outputMsg) {
		SemakEtanahManager.outputMsg = outputMsg;
	}
}