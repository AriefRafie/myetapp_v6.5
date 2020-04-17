package ekptg.ws.gfmas;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.gfmas.MemantauBayaranSewaServiceStub.GetMaklumat;
import ekptg.ws.gfmas.MemantauBayaranSewaServiceStub.GetMaklumatResponse;
import ekptg.ws.gfmas.MemantauBayaranSewaServiceStub.MaklumatDeposit;


	public class GfmasMemantauBayaranSewaManager {
		
		static Logger myLogger = Logger.getLogger(GfmasMemantauBayaranSewaManager.class);
		private static String kodIntegrasi = "GFM05";

		private static String flagMsg = null;
		private static String outputMsg = null;
		
		public Hashtable<String, String> getMaklumat (String noResit) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			String url = DB.getUrlAgensi(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			MaklumatDeposit form = getMaklumatFromAgency(noResit, url);
			myLogger.debug("url="+url);

			hash.put("noResit", noResit);
			hash.put("tarikhTransaksi", form.getTarikhTransaksi() == null ? "" : form.getTarikhTransaksi());
			hash.put("caraBayar", form.getCaraBayar() == null ? "" : form.getCaraBayar());
			hash.put("tarikhCek", form.getTarikhCek() == null ? "" : form.getTarikhCek());
			hash.put("noCekRujukan", form.getNoCekRujukan() == null ? "" : form.getNoCekRujukan());
			hash.put("amaunRM", form.getAmaunRM() == null ? "" : form.getAmaunRM());
			hash.put("tarikhResit", form.getTarikhResit() == null ? "" : form.getTarikhResit());
			return hash;
		}
		public Hashtable<String, String> getMaklumatUrl (String noResit, String url) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();

			if (url.isEmpty()) {
				return hash;
			}
			MaklumatDeposit form = getMaklumatFromAgency(noResit, url);
			myLogger.debug("url="+url);

			hash.put("noResit", noResit);
			hash.put("tarikhTransaksi", form.getTarikhTransaksi() == null ? "" : form.getTarikhTransaksi());
			hash.put("caraBayar", form.getCaraBayar() == null ? "" : form.getCaraBayar());
			hash.put("tarikhCek", form.getTarikhCek() == null ? "" : form.getTarikhCek());
			hash.put("noCekRujukan", form.getNoCekRujukan() == null ? "" : form.getNoCekRujukan());
			hash.put("amaunRM", form.getAmaunRM() == null ? "" : form.getAmaunRM());
			hash.put("tarikhResit", form.getTarikhResit() == null ? "" : form.getTarikhResit());
			return hash;
		}
		private static MaklumatDeposit getMaklumatFromAgency (String noResit,String url) {

			MemantauBayaranSewaServiceStub stub;
			MaklumatDeposit form = new MaklumatDeposit();

			try {
				stub = new MemantauBayaranSewaServiceStub(url);

				GetMaklumat request = new GetMaklumat();
				request.setNoResit(noResit);

				GetMaklumat temp = new GetMaklumat();
				temp.setNoResit(noResit);

				GetMaklumatResponse response = stub.getMaklumat(temp);
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
			GfmasMemantauBayaranSewaManager.flagMsg = flagMsg;
		}

		public static String getOutputMsg() {
			return outputMsg;
		}

		public static void setOutputMsg(String outputMsg) {
			GfmasMemantauBayaranSewaManager.outputMsg = outputMsg;
		}
	}

