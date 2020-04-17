package ekptg.ws.arb;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.arb.AmanahRayaWebServiceStub.AmanahRaya;
import ekptg.ws.arb.AmanahRayaWebServiceStub.GetMaklumat;
import ekptg.ws.arb.AmanahRayaWebServiceStub.GetMaklumatResponse;


	public class AmanahRayaManager {
		
		static Logger myLogger = Logger.getLogger(AmanahRayaManager.class);
		private static String kodIntegrasi = "ARB01";

		private static String flagMsg = null;
		private static String outputMsg = null;
		
		public Hashtable<String, String> getMaklumat (String noPengenalan, String noRujukan) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			String url = DB.getUrlAgensi(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			AmanahRaya ins = getMaklumatFromAgency(noPengenalan,noRujukan, url);
			myLogger.debug("url="+url);

			hash.put("noPengenalan", noPengenalan);
			hash.put("noRujukan", noRujukan);
			hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
			hash.put("daerah", ins.getDaerah() == null ? "" : ins.getDaerah());
			hash.put("butiranNilaianHarta", ins.getButiranNilaianHarta() == null ? "" : ins.getButiranNilaianHarta());
			hash.put("nilaian", ins.getNilaian() == null ? "" : ins.getNilaian());
			hash.put("catatan", ins.getCatatan() == null ? "" : ins.getCatatan());
			return hash;
		}
		public Hashtable<String, String> getMaklumatHa (String noPengenalan, String noRujukan, String url) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			//String url = DB.getUrlAgensiHartaAlih(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			AmanahRaya ins = getMaklumatFromAgency(noPengenalan,noRujukan, url);
			myLogger.debug("url="+url);

			hash.put("noPengenalan", noPengenalan);
			hash.put("noRujukan", noRujukan);
			hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
			hash.put("daerah", ins.getDaerah() == null ? "" : ins.getDaerah());
			hash.put("butiranNilaianHarta", ins.getButiranNilaianHarta() == null ? "" : ins.getButiranNilaianHarta());
			hash.put("nilaian", ins.getNilaian() == null ? "" : ins.getNilaian());
			hash.put("catatan", ins.getCatatan() == null ? "" : ins.getCatatan());
			return hash;
		}
		private static AmanahRaya getMaklumatFromAgency (String noPengenalan, String noRujukan,String url) {

			AmanahRayaWebServiceStub stub;
			AmanahRaya ins = new AmanahRaya();

			try {
				stub = new AmanahRayaWebServiceStub(url);

				GetMaklumat request = new GetMaklumat();
				request.setNoPengenalan(noPengenalan);
				request.setNoRujukan(noRujukan);

				GetMaklumat temp = new GetMaklumat();
				temp.setNoPengenalan(noPengenalan);
				temp.setNoRujukan(noRujukan);
myLogger.debug("noPengenalan="+noPengenalan+", noRujukan="+noRujukan);
				GetMaklumatResponse response = stub.getMaklumat(temp);
				ins = response.get_return();
				if (ins != null && ins.getNoPengenalan() != null) {
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
			AmanahRayaManager.flagMsg = flagMsg;
		}

		public static String getOutputMsg() {
			return outputMsg;
		}

		public static void setOutputMsg(String outputMsg) {
			AmanahRayaManager.outputMsg = outputMsg;
		}
	}

