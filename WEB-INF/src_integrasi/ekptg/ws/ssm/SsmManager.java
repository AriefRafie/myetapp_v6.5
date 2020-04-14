package ekptg.ws.ssm;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.ssm.SsmWebServiceStub.GetMaklumat;
import ekptg.ws.ssm.SsmWebServiceStub.GetMaklumatResponse;
import ekptg.ws.ssm.SsmWebServiceStub.Ssm;


	public class SsmManager {
		
		static Logger myLogger = Logger.getLogger(SsmManager.class);
		private static String kodIntegrasi = "SSM01";

		private static String flagMsg = null;
		private static String outputMsg = null;
		
		public Hashtable<String, String> getMaklumat (String noPengenalan, String noAhli) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			String url = DB.getUrlAgensiHartaAlih(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			Ssm ins = getMaklumatFromAgency(noPengenalan,noAhli, url);
			myLogger.debug("url="+url);

			hash.put("noPengenalan", noPengenalan);
			hash.put("noAhli", noAhli);
			hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
			hash.put("daerah", ins.getDaerah() == null ? "" : ins.getDaerah());
			hash.put("butiranHarta", ins.getButiranHarta() == null ? "" : ins.getButiranHarta());
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
			Ssm ins = getMaklumatFromAgency(noPengenalan,noAhli, url);
			myLogger.debug("url="+url);

			hash.put("noPengenalan", noPengenalan);
			hash.put("noAhli", noAhli);
			hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
			hash.put("daerah", ins.getDaerah() == null ? "" : ins.getDaerah());
			hash.put("butiranHarta", ins.getButiranHarta() == null ? "" : ins.getButiranHarta());
			hash.put("nilaiTarikhMohon", ins.getNilaiTarikhMohon() == null ? "" : ins.getNilaiTarikhMohon());
			hash.put("nilaiTarikhMati", ins.getNilaiTarikhMati() == null ? "" : ins.getNilaiTarikhMati());
			hash.put("catatan", ins.getCatatan() == null ? "" : ins.getCatatan());
			return hash;
		}
		private static Ssm getMaklumatFromAgency (String noPengenalan, String noAhli,String url) {

			SsmWebServiceStub stub;
			Ssm ins = new Ssm();

			try {
				stub = new SsmWebServiceStub(url);

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
			SsmManager.flagMsg = flagMsg;
		}

		public static String getOutputMsg() {
			return outputMsg;
		}

		public static void setOutputMsg(String outputMsg) {
			SsmManager.outputMsg = outputMsg;
		}
	}

