package ekptg.ws.identity;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.identity.JpnWebServiceStub.Form;
import ekptg.ws.identity.JpnWebServiceStub.GetMaklumat;
import ekptg.ws.identity.JpnWebServiceStub.GetMaklumatResponse;


	public class JpnManager {
		//jpn yang ke tiga
		static Logger myLogger = Logger.getLogger(JpnManager.class);
		private static String kodIntegrasi = "JPN04";

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
			hash.put("tarikhLahirSimati", ins.getTarikhLahirSimati() == null ? "" : ins.getTarikhLahirSimati());
			hash.put("namaSimati", ins.getNamaSimati() == null ? "" : ins.getNamaSimati());
			hash.put("namaLain", ins.getNamaLain() == null ? "" : ins.getNamaLain());
			hash.put("jantina", ins.getJantina() == null ? "" : ins.getJantina());
			hash.put("agama", ins.getAgama() == null ? "" : ins.getAgama());
			hash.put("warganegara", ins.getWarganegara() == null ? "" : ins.getWarganegara());
			hash.put("buktiKematian", ins.getBuktiKematian() == null ? "" : ins.getBuktiKematian());
			hash.put("noSijilMati", ins.getNoSijilMati() == null ? "" : ins.getNoSijilMati());
			hash.put("tarikhMati", ins.getTarikhMati() == null ? "" : ins.getTarikhMati());
			hash.put("umurMati", ins.getUmurTarikhMati() == null ? "" : ins.getUmurTarikhMati());
			hash.put("waktuMati", ins.getWaktuMati() == null ? "" : ins.getWaktuMati());
			hash.put("tempatMati", ins.getTempatMati() == null ? "" : ins.getTempatMati());
			hash.put("sebabMati", ins.getSebabMati() == null ? "" : ins.getSebabMati());
			hash.put("alamatAkhir", ins.getAlamatTerakhir() == null ? "" : ins.getAlamatTerakhir());
			hash.put("poskod", ins.getPoskod() == null ? "" : ins.getPoskod());
			hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
			hash.put("bandar", ins.getBandar() == null ? "" : ins.getBandar());
			hash.put("catatan", ins.getCatatan() == null ? "" : ins.getCatatan());
			return hash;
			
			/*System.out.println("row="+row);
			hm.setMyIdBaru(data[row][0]);
			hm.setMyIdLama(data[row][1]);
			hm.setJenisMyIdLain(data[row][2]);
			hm.setTarikhLahirSimati(data[row][3]);
			hm.setNamaSimati(data[row][4]);
			hm.setNamaLain(data[row][5]);
			hm.setJantina(data[row][6]);
			hm.setAgama(data[row][7]);
			hm.setWarganegara(data[row][8]);
			hm.setBuktiKematian(data[row][9]);	
			hm.setNoSijilMati(data[row][10]);	
			hm.setTarikhMati(data[row][11]);	
			hm.setUmurTarikhMati(data[row][12]);
			hm.setWaktuMati(data[row][13]);
			hm.setTempatMati(data[row][14]);
			hm.setSebabMati(data[row][14]);
			hm.setAlamatTerakhir(data[row][15]);
			hm.setPoskod(data[row][16]);	
			hm.setNegeri(data[row][17]);
			hm.setBandar(data[row][18]);
			hm.setCatatan(data[row][19]);*/
		}
		private static Form getMaklumatFromAgency (String myIdBaru,String url) {

			JpnWebServiceStub stub;
			Form ins = new Form();

			try {
				stub = new JpnWebServiceStub(url);

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
			JpnManager.flagMsg = flagMsg;
		}

		public static String getOutputMsg() {
			return outputMsg;
		}

		public static void setOutputMsg(String outputMsg) {
			JpnManager.outputMsg = outputMsg;
		}
	}

