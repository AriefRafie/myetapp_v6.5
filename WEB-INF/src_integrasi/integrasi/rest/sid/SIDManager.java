package integrasi.rest.sid;


import java.util.Hashtable;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class SIDManager {

	private static String baseUrl = "http://10.19.144.111/api/document/";
//	private static String baseUrl = "http://10.19.144.110/jkptg_sit/public/api/document/"; //url untuk testing sahaja
	private static String BPP = "BPP"; // BAHAGIAN PEMBAHAGIAN PUSAKA
	private static String BPT = "BPT"; // BAHAGIAN PENGAMBILAN TANAH
	private static String BPHP = "BPHP"; // BAHAGIAN PENGUATKUASA & HASIL PERSEKUTUAN
	private static String BHTP = "BHTP"; // BAHAGIAN HARTA TANAH PERSEKUTUAN

	private static String flagMsg = "T";
	private static String outputMsg = null;

	public static Vector getArkibDokumenPPK(String noFail) {
		Vector listArkibDokumen = new Vector();
		Hashtable h;

		try {
			
			noFail = StringUtils.replace(noFail, "/", "_").replace(" ", "_");
			
			RESTInvoker invoke = new RESTInvoker(baseUrl, null, null);
			JSONArray jsonArray = new JSONArray(invoke.getRESTResponse(BPP + "/" + noFail));
			if (jsonArray.length() > 0 && !jsonArray.toString().equals("[0]")) {
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject obj = jsonArray.getJSONObject(i);
					if (obj != null) {
						h = new Hashtable();
						h.put("noFail", obj.get("no_fail"));
						h.put("namaDokumen", obj.get("nama_dokumen"));
						h.put("dirDokumen", obj.get("document_path"));
						listArkibDokumen.addElement(h);
					}
				}
				flagMsg = "Y";
				outputMsg = "SUCCESS";
			} else {
				flagMsg = "T";
				outputMsg = "DOKUMEN TIDAK WUJUD";
			}
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "T";
			outputMsg = e.getMessage();
			
		}
		return listArkibDokumen;
	}
	
	public static Vector getArkibDokumenPPT(String noFail, String noHakmilik, String noLot){
		Vector listArkibDokumen = new Vector();
		Hashtable h;

		try {
			noFail = StringUtils.replace(noFail, "/", "_").replace(" ", "_");

			RESTInvoker invoke = new RESTInvoker(baseUrl, null, null);
			JSONArray jsonArray = new JSONArray(invoke.getRESTResponse(BPT + "/" + noFail + "/" + noHakmilik + "/" + noLot));
			if (jsonArray.length() > 0 && !jsonArray.toString().equals("[0]")) {
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject obj = jsonArray.getJSONObject(i);
					if (obj != null) {
						h = new Hashtable();
//						h.put("namaDokumen", obj.get("nama_dokumen"));
//						h.put("dirDokumen", obj.get("document_path"));
						h.put("noFail", obj.get("no_fail"));
						h.put("negeri", obj.get("negeri").toString().toUpperCase());
						h.put("daerah", obj.get("daerah").toString().toUpperCase());
						h.put("namaDokumen", obj.get("nama_dokumen"));
						h.put("jenisDokumen", obj.get("jenis_doc").toString().toUpperCase());
						h.put("dirDokumen", obj.get("document_path"));
						listArkibDokumen.addElement(h);
					}
				}
				flagMsg = "Y";
				outputMsg = "SUCCESS";
			} else {
				flagMsg = "T";
				outputMsg = "DOKUMEN TIDAK WUJUD";
			}
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "T";
			outputMsg = e.getMessage();
			
		}
		return listArkibDokumen;
	}
	
	public static Vector getArkibDokumenPHP(String noFail, String idNegeri, String idDaerah, String idJenisFail) {
		Vector listArkibDokumen = new Vector();
		Hashtable h;

		try {
			noFail = StringUtils.replace(noFail, "/", "_").replace(" ", "_");			
			
			RESTInvoker invoke = new RESTInvoker(baseUrl, null, null);
			JSONArray jsonArray = new JSONArray(invoke.getRESTResponse(BPHP + "/" + noFail + "/" + idNegeri + "/" + idDaerah + "/" + idJenisFail));
			if (jsonArray.length() > 0 && !jsonArray.toString().equals("[0]")) {
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject obj = jsonArray.getJSONObject(i);
					if (obj != null) {
						h = new Hashtable();
						h.put("noFail", obj.get("no_fail"));
						h.put("negeri", obj.get("negeri").toString().toUpperCase());
						h.put("daerah", obj.get("daerah").toString().toUpperCase());
						h.put("namaDokumen", obj.get("nama_dokumen"));
						h.put("jenisDokumen", obj.get("jenis_doc").toString().toUpperCase());
						h.put("dirDokumen", obj.get("document_path"));
						listArkibDokumen.addElement(h);
					}
				}
				flagMsg = "Y";
				outputMsg = "SUCCESS";
			} else {
				flagMsg = "T";
				outputMsg = "DOKUMEN TIDAK WUJUD";
			}
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "T";
			outputMsg = e.getMessage();
			
		}
		return listArkibDokumen;
	}
	
	public static Vector getArkibDokumenHTP(String noFail, String idNegeri, String idDaerah) {
		Vector listArkibDokumen = new Vector();
		Hashtable h;

		try {
			noFail = StringUtils.replace(noFail, "/", "_").replace(" ", "_");

			RESTInvoker invoke = new RESTInvoker(baseUrl, null, null);
			JSONArray jsonArray = new JSONArray(invoke.getRESTResponse(BHTP + "/" + noFail + "/" + idNegeri + "/" + idDaerah));
			if (jsonArray.length() > 0 && !jsonArray.toString().equals("[0]")) {				
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject obj = jsonArray.getJSONObject(i);
					if (obj != null) {
						h = new Hashtable();
						h.put("noFail", obj.get("no_fail"));
						h.put("negeri", obj.get("negeri").toString().toUpperCase());
						h.put("daerah", obj.get("daerah").toString().toUpperCase());
						h.put("namaDokumen", obj.get("nama_dokumen"));
						h.put("dirDokumen", obj.get("document_path"));
						listArkibDokumen.addElement(h);
					}
				}
				flagMsg = "Y";
				outputMsg = "SUCCESS";
			} else {
				flagMsg = "T";
				outputMsg = "DOKUMEN TIDAK WUJUD";
			}
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "T";
			outputMsg = e.getMessage();
			
		}
		return listArkibDokumen;
	}

	public static String getFlagMsg() {
		return flagMsg;
	}

	public static void setFlagMsg(String flagMsg) {
		SIDManager.flagMsg = flagMsg;
	}

	public static String getOutputMsg() {
		return outputMsg;
	}

	public static void setOutputMsg(String outputMsg) {
		SIDManager.outputMsg = outputMsg;
	}
}
