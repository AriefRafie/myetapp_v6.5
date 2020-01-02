package ekptg.ws.borangd;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.borangd.BorangDWebServiceStub.BorangD;
import ekptg.ws.borangd.BorangDWebServiceStub.GetMaklumat;
import ekptg.ws.borangd.BorangDWebServiceStub.GetMaklumatResponse;
	public class BorangDManager {
		
		static Logger myLogger = Logger.getLogger(BorangDManager.class);
		private static String kodIntegrasi = "ETH01";

		private static String flagMsg = null;
		private static String outputMsg = null;
		
		public Hashtable<String, String> getMaklumat (String noWarta, String tarikhWarta, String idHakmilik, String kodUrusan) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			String url = DB.getUrlAgensi(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			BorangD ins = getMaklumatFromAgency(noWarta, tarikhWarta,idHakmilik, kodUrusan, url);
			myLogger.debug("url="+url);

			hash.put("noWarta", noWarta);
			hash.put("tarikhWarta", tarikhWarta);
			hash.put("idHakmilik", idHakmilik);
			hash.put("kodUrusan", kodUrusan);
			hash.put("noPerserahan", ins.getNoPerserahan() == null ? "" : ins.getNoPerserahan());
			hash.put("tarikhCatatanDibuat", ins.getTarikhCatatanDibuat() == null ? "" : ins.getTarikhCatatanDibuat());
			hash.put("tarikhTerima", ins.getTarikhTerima() == null ? "" : ins.getTarikhTerima());
			hash.put("maklumatEndorsan", ins.getMaklumatEndorsan() == null ? "" : ins.getMaklumatEndorsan());
			return hash;
		}
		
		public Hashtable<String, String> getMaklumatHa (String noWarta, String tarikhWarta, String idHakmilik, String kodUrusan, String url) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			//String url = DB.getUrlAgensiHartaAlih(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			BorangD ins = getMaklumatFromAgency(noWarta, tarikhWarta,idHakmilik, kodUrusan, url);
			myLogger.debug("url="+url);

			hash.put("noWarta", noWarta);
			hash.put("tarikhWarta", tarikhWarta);
			hash.put("idHakmilik", idHakmilik);
			hash.put("kodUrusan", kodUrusan);
			hash.put("noPerserahan", ins.getNoPerserahan() == null ? "" : ins.getNoPerserahan());
			hash.put("tarikhCatatanDibuat", ins.getTarikhCatatanDibuat() == null ? "" : ins.getTarikhCatatanDibuat());
			hash.put("tarikhTerima", ins.getTarikhTerima() == null ? "" : ins.getTarikhTerima());
			hash.put("maklumatEndorsan", ins.getMaklumatEndorsan() == null ? "" : ins.getMaklumatEndorsan());
			return hash;
		}
		
		private static BorangD getMaklumatFromAgency (String noWarta, String tarikhWarta, String idHakmilik, String kodUrusan, String url) {

			BorangDWebServiceStub stub;
			BorangD ins = new BorangD();

			try {
				stub = new BorangDWebServiceStub(url);

				GetMaklumat request = new GetMaklumat();
				request.setNoWarta(noWarta);
				request.setTarikhWarta(tarikhWarta);
				request.setIdHakmilik(idHakmilik);
				request.setKodUrusan(kodUrusan);

				GetMaklumat temp = new GetMaklumat();
				temp.setNoWarta(noWarta);
				temp.setTarikhWarta(tarikhWarta);
				temp.setIdHakmilik(idHakmilik);
				temp.setKodUrusan(kodUrusan);

				GetMaklumatResponse response = stub.getMaklumat(temp);
				ins = response.get_return();
				if (ins != null && ins.getNoWarta() != null) {
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
			BorangDManager.flagMsg = flagMsg;
		}

		public static String getOutputMsg() {
			return outputMsg;
		}

		public static void setOutputMsg(String outputMsg) {
			BorangDManager.outputMsg = outputMsg;
		}
	}

