package ekptg.ws.ewarta;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.ewarta.EwartaWebServiceStub.EwartaForm;
import ekptg.ws.ewarta.EwartaWebServiceStub.GetNoRujPtg;
import ekptg.ws.ewarta.EwartaWebServiceStub.GetNoRujPtgResponse;


	public class EwartaManager {
		
		static Logger myLogger = Logger.getLogger(EwartaManager.class);
		private static String kodIntegrasi = "EWT01";

		private static String flagMsg = null;
		private static String outputMsg = null;
		
		public Hashtable<String, String> getMaklumat (String noRujPtg) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			String url = DB.getUrlAgensi(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			EwartaForm ins = getMaklumatWartaFromAgency(noRujPtg, url);
			myLogger.debug("url="+url);

			hash.put("noRujPtg", noRujPtg);
			hash.put("noWarta", ins.getNoWarta() == null ? "" : ins.getNoWarta());
			hash.put("tarikhWarta", ins.getTarikhWarta() == null ? "" : ins.getTarikhWarta());
			

			return hash;
		}
		private static EwartaForm getMaklumatWartaFromAgency(String noRujPtg, String url) {

			EwartaWebServiceStub stub;
			EwartaForm ins = new EwartaForm();

			try {
				stub = new EwartaWebServiceStub(url);

				GetNoRujPtg request = new GetNoRujPtg();
				request.setNoRujPtg(noRujPtg);
				
				GetNoRujPtg temp = new GetNoRujPtg();
				temp.setNoRujPtg(noRujPtg);
				GetNoRujPtgResponse response = stub.getNoRujPtg(temp);
				ins = response.get_return();
				if (ins != null && ins.getNoRujPtg() != null) {
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
			EwartaManager.flagMsg = flagMsg;
		}

		public static String getOutputMsg() {
			return outputMsg;
		}

		public static void setOutputMsg(String outputMsg) {
			EwartaManager.outputMsg = outputMsg;
		}
	}
