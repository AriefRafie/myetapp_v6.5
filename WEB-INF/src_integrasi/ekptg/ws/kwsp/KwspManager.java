package ekptg.ws.kwsp;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.kwsp.KwspWebServiceStub.GetMaklumat;
import ekptg.ws.kwsp.KwspWebServiceStub.GetMaklumatResponse;
import ekptg.ws.kwsp.KwspWebServiceStub.Kwsp;
	public class KwspManager {
		
		static Logger myLogger = Logger.getLogger(KwspManager.class);
		private static String kodIntegrasi = "KWSP01";

		private static String flagMsg = null;
		private static String outputMsg = null;
		
		public Hashtable<String, String> getMaklumat (String noPengenalan, String noAkaun) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			String url = DB.getUrlAgensi(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			Kwsp ins = getMaklumatFromAgency(noPengenalan,noAkaun, url);
			myLogger.debug("url="+url);

			hash.put("noPengenalan", noPengenalan);
			hash.put("noAkaun", noAkaun);
			hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
			hash.put("daerah", ins.getDaerah() == null ? "" : ins.getDaerah());
			hash.put("nilaiTarikhMohon", ins.getNilaiTarikhMohon() == null ? "" : ins.getNilaiTarikhMohon());
			hash.put("nilaiTarikhMati", ins.getNilaiTarikhMati() == null ? "" : ins.getNilaiTarikhMati());
			hash.put("catatan", ins.getCatatan() == null ? "" : ins.getCatatan());
			return hash;
		}
		public Hashtable<String, String> getMaklumatHa (String noPengenalan, String noAkaun, String url) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			//String url = DB.getUrlAgensiHartaAlih(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			Kwsp ins = getMaklumatFromAgency(noPengenalan,noAkaun, url);
			myLogger.debug("url="+url);

			hash.put("noPengenalan", noPengenalan);
			hash.put("noAkaun", noAkaun);
			hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
			hash.put("daerah", ins.getDaerah() == null ? "" : ins.getDaerah());
			hash.put("nilaiTarikhMohon", ins.getNilaiTarikhMohon() == null ? "" : ins.getNilaiTarikhMohon());
			hash.put("nilaiTarikhMati", ins.getNilaiTarikhMati() == null ? "" : ins.getNilaiTarikhMati());
			hash.put("catatan", ins.getCatatan() == null ? "" : ins.getCatatan());
			return hash;
		}
		private static Kwsp getMaklumatFromAgency (String noPengenalan, String noAkaun,String url) {

			KwspWebServiceStub stub;
			Kwsp ins = new Kwsp();

			try {
				stub = new KwspWebServiceStub(url);

				GetMaklumat request = new GetMaklumat();
				request.setNoPengenalan(noPengenalan);
				request.setNoAkaun(noAkaun);

				GetMaklumat temp = new GetMaklumat();
				temp.setNoPengenalan(noPengenalan);
				temp.setNoAkaun(noAkaun);

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
			KwspManager.flagMsg = flagMsg;
		}

		public static String getOutputMsg() {
			return outputMsg;
		}

		public static void setOutputMsg(String outputMsg) {
			KwspManager.outputMsg = outputMsg;
		}
	}


