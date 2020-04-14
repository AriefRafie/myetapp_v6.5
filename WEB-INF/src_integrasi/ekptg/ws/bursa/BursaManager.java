package ekptg.ws.bursa;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.bursa.BSMWebServiceStub.Bursa;
import ekptg.ws.bursa.BSMWebServiceStub.GetMaklumat;
import ekptg.ws.bursa.BSMWebServiceStub.GetMaklumatResponse;

	public class BursaManager {
		
		static Logger myLogger = Logger.getLogger(BursaManager.class);
		private static String kodIntegrasi = "BSM01";

		private static String flagMsg = null;
		private static String outputMsg = null;
		
		public Hashtable<String, String> getMaklumat (String noPengenalan, String noAhli) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			String url = DB.getUrlAgensi(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			Bursa ins = getMaklumatFromAgency(noPengenalan,noAhli, url);
			myLogger.debug("url="+url);

			hash.put("noPengenalan", noPengenalan);
			hash.put("noAhli", noAhli);
			hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
			hash.put("daerah", ins.getDaerah() == null ? "" : ins.getDaerah());
			hash.put("namaSaham", ins.getNamaSaham() == null ? "" : ins.getNamaSaham());
			hash.put("noSijil", ins.getNoSijil() == null ? "" : ins.getNoSijil());
			hash.put("bilUnit", ins.getBilUnit() == null ? "" : ins.getBilUnit());
			hash.put("nilaiSeunit", ins.getNilaiSeunit() == null ? "" : ins.getNilaiSeunit());
			hash.put("nilaiTarikhMohon", ins.getNilaiTarikhMohon() == null ? "" : ins.getNilaiTarikhMohon());
			hash.put("nilaiTarikhMati", ins.getNilaiTarikhMati() == null ? "" : ins.getNilaiTarikhMati());
			hash.put("catatan", ins.getCatatan() == null ? "" : ins.getCatatan());
			return hash;
		}
		public Hashtable<String, String> getMaklumatHa (String noPengenalan, String noAhli, String url) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			//String url = DB.getUrlAgensiHartaAlih(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			Bursa ins = getMaklumatFromAgency(noPengenalan,noAhli, url);
			myLogger.debug("url="+url);

			hash.put("noPengenalan", noPengenalan);
			hash.put("noAhli", noAhli);
			hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
			hash.put("daerah", ins.getDaerah() == null ? "" : ins.getDaerah());
			hash.put("namaSaham", ins.getNamaSaham() == null ? "" : ins.getNamaSaham());
			hash.put("noSijil", ins.getNoSijil() == null ? "" : ins.getNoSijil());
			hash.put("bilUnit", ins.getBilUnit() == null ? "" : ins.getBilUnit());
			hash.put("nilaiSeunit", ins.getNilaiSeunit() == null ? "" : ins.getNilaiSeunit());
			hash.put("nilaiTarikhMohon", ins.getNilaiTarikhMohon() == null ? "" : ins.getNilaiTarikhMohon());
			hash.put("nilaiTarikhMati", ins.getNilaiTarikhMati() == null ? "" : ins.getNilaiTarikhMati());
			hash.put("catatan", ins.getCatatan() == null ? "" : ins.getCatatan());
			return hash;
		}
		private static Bursa getMaklumatFromAgency (String noPengenalan, String noAhli,String url) {

			BSMWebServiceStub stub;
			Bursa ins = new Bursa();

			try {
				stub = new BSMWebServiceStub(url);

				GetMaklumat request = new GetMaklumat();
				request.setNoPengenalan(noPengenalan);
				request.setNoAhli(noAhli);

				GetMaklumat temp = new GetMaklumat();
				temp.setNoPengenalan(noPengenalan);
				temp.setNoAhli(noAhli);

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
			BursaManager.flagMsg = flagMsg;
		}

		public static String getOutputMsg() {
			return outputMsg;
		}

		public static void setOutputMsg(String outputMsg) {
			BursaManager.outputMsg = outputMsg;
		}
	}

