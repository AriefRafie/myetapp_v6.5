package ekptg.ws.etanah;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.etanah.EtanahServiceStub.Etanah;
import ekptg.ws.etanah.EtanahServiceStub.GetMaklumat;
import ekptg.ws.etanah.EtanahServiceStub.GetMaklumatResponse;



public class EtanahManager {
	
	static Logger myLogger = Logger.getLogger(EtanahManager.class);
	private static String kodIntegrasi = "HTP01";

	private static String flagMsg = null;
	private static String outputMsg = null;
	
	public Hashtable<String, String> getMaklumat (String idHakmilik) throws Exception {
		// TODO Auto-generated method stub
		Hashtable<String, String> hash = new Hashtable<String, String>();
		String url = DB.getUrlAgensi(kodIntegrasi);
		if (url.isEmpty()) {
			return hash;
		}
		Etanah ins = getMaklumatFromAgency(idHakmilik, url);
		myLogger.debug("url="+url);
		
		hash.put("idHakmilik", ins.getIdHakmilik() == null ? "" : ins.getIdHakmilik());
		hash.put("jenisLot", ins.getJenisLot() == null ? "" : ins.getJenisLot());
		hash.put("noLot", ins.getNoLot() == null ? "" : ins.getNoLot());
		hash.put("kegunaanTanah", ins.getKegunaanTanah() == null ? "" : ins.getKegunaanTanah());
		hash.put("cukaiSemasa", ins.getCukaiSemasa() == null ? "" : ins.getCukaiSemasa());
		hash.put("tunggakan", ins.getTunggakan() == null ? "" : ins.getTunggakan());
		hash.put("dendaLewat", ins.getDendaLewat() == null ? "" : ins.getDendaLewat());
		hash.put("cukaiLain", ins.getCukaiLain() == null ? "" : ins.getCukaiLain());
		hash.put("lebihan", ins.getLebihan() == null ? "" : ins.getLebihan());
		hash.put("cukaiKenaBayar", ins.getCukaiKenaBayar() == null ? "" : ins.getCukaiKenaBayar());
		return hash;
	}
	private static Etanah getMaklumatFromAgency(String idHakmilik, String url) {

		EtanahServiceStub stub;
		Etanah ins = new Etanah();

		try {
			stub = new EtanahServiceStub(url);

			GetMaklumat request = new GetMaklumat();
			request.setIdHakmilik(idHakmilik);
			
			GetMaklumat temp = new GetMaklumat();
			temp.setIdHakmilik(idHakmilik);
			GetMaklumatResponse response = stub.getMaklumat(temp);
			ins = response.get_return();
			if (ins != null && ins.getIdHakmilik() != null) {
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
		EtanahManager.flagMsg = flagMsg;
	}

	public static String getOutputMsg() {
		return outputMsg;
	}

	public static void setOutputMsg(String outputMsg) {
		EtanahManager.outputMsg = outputMsg;
	}
}