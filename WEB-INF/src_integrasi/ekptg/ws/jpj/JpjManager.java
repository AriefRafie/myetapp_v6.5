package ekptg.ws.jpj;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.jpj.JpjWebServiceStub.GetMaklumat;
import ekptg.ws.jpj.JpjWebServiceStub.GetMaklumatResponse;
import ekptg.ws.jpj.JpjWebServiceStub.JpjForm;

	public class JpjManager {
		
		static Logger myLogger = Logger.getLogger(JpjManager.class);
		private static String kodIntegrasi = "JPJ01";

		private static String flagMsg = null;
		private static String outputMsg = null;
		
		public Hashtable<String, String> getMaklumat (String noPengenalan, String noKenderaan) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			String url = DB.getUrlAgensi(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			JpjForm ins = getMaklumatFromAgency(noPengenalan,noKenderaan, url);
			myLogger.debug("url="+url);

			hash.put("noPengenalan", noPengenalan);
			hash.put("noKenderaan", noKenderaan);
			hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
			hash.put("daerah", ins.getDaerah() == null ? "" : ins.getDaerah());
			hash.put("jenisdanjenama", ins.getJenisdanjenama() == null ? "" : ins.getJenisdanjenama());
			hash.put("noDaftar", ins.getNoDaftar() == null ? "" : ins.getNoDaftar());
			hash.put("nilaiTarikhMohon", ins.getNilaiTarikhMohon() == null ? "" : ins.getNilaiTarikhMohon());
			hash.put("nilaiTarikhMati", ins.getNilaiTarikhMati() == null ? "" : ins.getNilaiTarikhMati());
			hash.put("catatan", ins.getCatatan() == null ? "" : ins.getCatatan());
			return hash;
		}
		public Hashtable<String, String> getMaklumatHa (String noPengenalan, String noKenderaan, String url) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			//String url = DB.getUrlAgensiHartaAlih(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			JpjForm ins = getMaklumatFromAgency(noPengenalan,noKenderaan, url);
			myLogger.debug("url="+url);

			hash.put("noPengenalan", noPengenalan);
			hash.put("noKenderaan", noKenderaan);
			hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
			hash.put("daerah", ins.getDaerah() == null ? "" : ins.getDaerah());
			hash.put("jenisdanjenama", ins.getJenisdanjenama() == null ? "" : ins.getJenisdanjenama());
			hash.put("noDaftar", ins.getNoDaftar() == null ? "" : ins.getNoDaftar());
			hash.put("nilaiTarikhMohon", ins.getNilaiTarikhMohon() == null ? "" : ins.getNilaiTarikhMohon());
			hash.put("nilaiTarikhMati", ins.getNilaiTarikhMati() == null ? "" : ins.getNilaiTarikhMati());
			hash.put("catatan", ins.getCatatan() == null ? "" : ins.getCatatan());
			return hash;
		}
		private static JpjForm getMaklumatFromAgency (String noPengenalan, String noKenderaan,String url) {

			JpjWebServiceStub stub;
			JpjForm ins = new JpjForm();

			try {
				stub = new JpjWebServiceStub(url);

				GetMaklumat request = new GetMaklumat();
				request.setNoPengenalan(noPengenalan);
				request.setNoKenderaan(noKenderaan);

				GetMaklumat temp = new GetMaklumat();
				temp.setNoPengenalan(noPengenalan);
				temp.setNoKenderaan(noKenderaan);

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
			JpjManager.flagMsg = flagMsg;
		}

		public static String getOutputMsg() {
			return outputMsg;
		}

		public static void setOutputMsg(String outputMsg) {
			JpjManager.outputMsg = outputMsg;
		}
	}

