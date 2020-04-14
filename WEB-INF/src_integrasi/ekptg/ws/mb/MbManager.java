package ekptg.ws.mb;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.ws.mb.MbWebServiceStub.GetNoIc;
import ekptg.ws.mb.MbWebServiceStub.GetNoIcResponse;
import ekptg.ws.mb.MbWebServiceStub.Mb;


public class MbManager {
	
	static Logger myLogger = Logger.getLogger(MbManager.class);
	private static String kodIntegrasi = "MP01";

	private static String flagMsg = null;
	private static String outputMsg = null;
	
	public Hashtable<String, String> getMaklumat (String noPengenalan) throws Exception {
		// TODO Auto-generated method stub
		Hashtable<String, String> hash = new Hashtable<String, String>();
		String url = DB.getUrlAgensi(kodIntegrasi);
		if (url.isEmpty()) {
			return hash;
		}
		Mb ins = getMaklumatWartaFromAgency(noPengenalan, url);
		myLogger.debug("url="+url);

		hash.put("noPengenalan", noPengenalan);
		hash.put("negeri", ins.getNegeri() == null ? "" : ins.getNegeri());
		hash.put("daerah", ins.getDaerah() == null ? "" : ins.getDaerah());
		hash.put("mukim", ins.getMukim() == null ? "" : ins.getMukim());
		hash.put("jenisHakmilik", ins.getJenisHakmilik() == null ? "" : ins.getJenisHakmilik());
		hash.put("noHakmilik", ins.getNoHakmilik() == null ? "" : ins.getNoHakmilik());
		hash.put("noPT", ins.getNoPT() == null ? "" : ins.getNoPT());
		hash.put("bahagianSimati", ins.getBahagianSimati() == null ? "" : ins.getBahagianSimati());
		hash.put("sekatan", ins.getSekatan() == null ? "" : ins.getSekatan());
		hash.put("syaratNyata", ins.getSyaratNyata() == null ? "" : ins.getSyaratNyata());
		hash.put("bahagianSimati2", ins.getBahagianSimati2() == null ? "" : ins.getBahagianSimati2());
		hash.put("noPajakan", ins.getNoPajakan() == null ? "" : ins.getNoPajakan());
		hash.put("noPerserahan", ins.getNoPerserahan() == null ? "" : ins.getNoPerserahan());
		hash.put("kategoriTanah", ins.getKategoriTanah() == null ? "" : ins.getKategoriTanah());
		hash.put("luasAsal", ins.getLuasAsal() == null ? "" : ins.getLuasAsal());
		hash.put("jenisLuas", ins.getJenisLuas() == null ? "" : ins.getJenisLuas());
		hash.put("luas", ins.getLuas() == null ? "" : ins.getLuas());
		hash.put("statusPemilikan", ins.getStatusPemilikan() == null ? "" : ins.getStatusPemilikan());
		hash.put("jenisTanah", ins.getJenisTanah() == null ? "" : ins.getJenisTanah());
		hash.put("idPermohonanSimati", ins.getIdPermohonanSimati() == null ? "" : ins.getIdPermohonanSimati());

		
		return hash;
	}
	private static Mb getMaklumatWartaFromAgency(String noPengenalan, String url) {

		MbWebServiceStub stub;
		Mb ins = new Mb();

		try {
			stub = new MbWebServiceStub(url);

			GetNoIc request = new GetNoIc();
			request.setNoPengenalan(noPengenalan);
			
			GetNoIc temp = new GetNoIc();
			temp.setNoPengenalan(noPengenalan);
			GetNoIcResponse response = stub.getNoIc(temp);
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
		MbManager.flagMsg = flagMsg;
	}

	public static String getOutputMsg() {
		return outputMsg;
	}

	public static void setOutputMsg(String outputMsg) {
		MbManager.outputMsg = outputMsg;
	}
}