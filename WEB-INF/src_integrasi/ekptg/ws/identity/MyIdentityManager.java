package ekptg.ws.identity;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.identity.MyIdentityWebServiceStub.GetMaklumat;
import ekptg.ws.identity.MyIdentityWebServiceStub.GetMaklumatResponse;
import ekptg.ws.identity.MyIdentityWebServiceStub.Identity;


	public class MyIdentityManager {
		
		static Logger myLogger = Logger.getLogger(MyIdentityManager.class);
		private static String kodIntegrasi = "JPN01";

		private static String flagMsg = null;
		private static String outputMsg = null;
		
		public Hashtable<String, String> getMaklumat (String myIdBaru) throws Exception {
			// TODO Auto-generated method stub
			Hashtable<String, String> hash = new Hashtable<String, String>();
			String url = DB.getUrlAgensi(kodIntegrasi);
			if (url.isEmpty()) {
				return hash;
			}
			Identity ins = getMaklumatWartaFromAgency(myIdBaru,url);
			myLogger.debug("url="+url);

			hash.put("myIdBaru", myIdBaru);
			hash.put("myIdLama", ins.getMyIdLama() == null ? "" : ins.getMyIdLama());
			hash.put("jenisMyIdLain", ins.getJenisMyIdLain() == null ? "" : ins.getJenisMyIdLain());
			hash.put("myIdLain", ins.getMyIdLain() == null ? "" : ins.getMyIdLain());
			hash.put("namaOB", ins.getNamaOB() == null ? "" : ins.getNamaOB());
			hash.put("jantina", ins.getJantina() == null ? "" : ins.getJantina());
			hash.put("agama", ins.getAgama() == null ? "" : ins.getAgama());
			hash.put("warganegara", ins.getWarganegara() == null ? "" : ins.getWarganegara());
			hash.put("tarikhLahir", ins.getTarikhLahir() == null ? "" : ins.getTarikhLahir());
			hash.put("umur", ins.getUmur() == null ? "" : ins.getUmur());
			hash.put("noSuratBeranak", ins.getNoSuratBeranak() == null ? "" : ins.getNoSuratBeranak());
			hash.put("alamatTetap", ins.getAlamatTetap() == null ? "" : ins.getAlamatTetap());
			hash.put("poskod", ins.getPoskod() == null ? "" : ins.getPoskod());
			hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
			hash.put("bandar", ins.getBandar() == null ? "" : ins.getBandar());
			hash.put("catatan", ins.getCatatan() == null ? "" : ins.getCatatan());
			return hash;
		}
		private static Identity getMaklumatWartaFromAgency (String myIdBaru,String url) {

			MyIdentityWebServiceStub stub;
			Identity ins = new Identity();

			try {
				stub = new MyIdentityWebServiceStub(url);

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
			MyIdentityManager.flagMsg = flagMsg;
		}

		public static String getOutputMsg() {
			return outputMsg;
		}

		public static void setOutputMsg(String outputMsg) {
			MyIdentityManager.outputMsg = outputMsg;
		}
	}

