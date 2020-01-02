package ekptg.ws.identity;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.identity.IdentityWebServiceStub.Form;
import ekptg.ws.identity.IdentityWebServiceStub.GetMaklumat;
import ekptg.ws.identity.IdentityWebServiceStub.GetMaklumatResponse;



	public class IdentityManager {
		//jpn yang ke tiga
		static Logger myLogger = Logger.getLogger(IdentityManager.class);
		private static String kodIntegrasi = "JPN03";

		private static String flagMsg = null;
		private static String outputMsg = null;
		
		public Hashtable<String, String> getMaklumat(String myIdBaru) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			String url = DB.getUrlAgensi(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			Form ins = getMaklumatFromAgency(myIdBaru,url);
			myLogger.debug("url="+url);

			hash.put("myIdBaru", myIdBaru);
			hash.put("myIdLama", ins.getMyIdLama() == null ? "" : ins.getMyIdLama());
			hash.put("jenisMyIdLain", ins.getJenisMyIdLain() == null ? "" : ins.getJenisMyIdLain());
			hash.put("myIdLain", ins.getMyIdLain() == null ? "" : ins.getMyIdLain());
			hash.put("namaPemohon", ins.getNamaPemohon() == null ? "" : ins.getNamaPemohon());
			hash.put("namaLain", ins.getNamaLain() == null ? "" : ins.getNamaLain());
			hash.put("jantina", ins.getJantina() == null ? "" : ins.getJantina());
			hash.put("agama", ins.getAgama() == null ? "" : ins.getAgama());
			hash.put("warganegara", ins.getWarganegara() == null ? "" : ins.getWarganegara());
			hash.put("tarikhLahir", ins.getTarikhLahir() == null ? "" : ins.getTarikhLahir());
			hash.put("noSuratBeranak", ins.getNoSuratBeranak() == null ? "" : ins.getNoSuratBeranak());
			hash.put("umur", ins.getUmur() == null ? "" : ins.getUmur());
			hash.put("alamatTetap", ins.getAlamatTetap() == null ? "" : ins.getAlamatTetap());
			hash.put("poskod", ins.getPoskod() == null ? "" : ins.getPoskod());
			hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
			hash.put("bandar", ins.getBandar() == null ? "" : ins.getBandar());
			hash.put("catatan", ins.getCatatan() == null ? "" : ins.getCatatan());
			return hash;
			
			
		}
		private static Form getMaklumatFromAgency (String myIdBaru,String url) {

			IdentityWebServiceStub stub;
			Form ins = new Form();

			try {
				stub = new IdentityWebServiceStub(url);

				GetMaklumat request = new GetMaklumat();
				request.setMyIdBaru(myIdBaru);

				GetMaklumat temp = new GetMaklumat();
				temp.setMyIdBaru(myIdBaru);

				GetMaklumatResponse response = stub.getMaklumat(temp);
				ins = response.get_return();
				if (ins != null && ins.getMyIdBaru() != null) {
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
			IdentityManager.flagMsg = flagMsg;
		}

		public static String getOutputMsg() {
			return outputMsg;
		}

		public static void setOutputMsg(String outputMsg) {
			IdentityManager.outputMsg = outputMsg;
		}
	}

