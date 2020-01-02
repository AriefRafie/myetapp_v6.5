package ekptg.ws.myidentity;

import java.rmi.RemoteException;
import java.util.Hashtable;

import ekptg.ws.myidentity.CapaianMyIdentityWebServiceStub.GetNoKadPengenalan;
import ekptg.ws.myidentity.CapaianMyIdentityWebServiceStub.GetNoKadPengenalanResponse;
import ekptg.ws.myidentity.CapaianMyIdentityWebServiceStub.NoKadPengenalan;

//import ekptg.ws.ewarta.CapaianEWartaServiceStub;
//import ekptg.ws.ewarta.EWartaManager;
//import ekptg.ws.ewarta.CapaianEWartaServiceStub.GetNoRujPtg;
//import ekptg.ws.ewarta.CapaianEWartaServiceStub.GetNoRujPtgResponse;

public class MyIdentityManager {
	private static String url = "http://192.168.0.99:8081/axis2/services/CapaianMyIdentityWebService?wsdl";

	private static String flagMsg = null;
	private static String outputMsg = null;

	public Hashtable<String, String> getMaklumatMyIdentity(String noKadPengenalan) throws RemoteException {
		// TODO Auto-generated method stub
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		NoKadPengenalan myIdentity = getMaklumatMyIdentityFromAgency(noKadPengenalan, url);    //NoKadPengenalan-class created in ekptg.ws.form
		hash.put("noKadPengenalan", noKadPengenalan);
		hash.put("namaPB", myIdentity.getNamaPB() == null ? "" : myIdentity.getNamaPB());
		hash.put("jenisNoPengenalanPB", myIdentity.getJenisNoPengenalanPB() == null ? "" : myIdentity.getJenisNoPengenalanPB());
		hash.put("noPengenalanPB", myIdentity.getNoPengenalanPB() == null ? "" : myIdentity.getNoPengenalanPB());
		hash.put("bangsa", myIdentity.getBangsa() == null ? "" : myIdentity.getBangsa());
		hash.put("warganegara", myIdentity.getWarganegara() == null ? "" : myIdentity.getWarganegara());
		hash.put("alamatPB", myIdentity.getAlamatPB() == null ? "" : myIdentity.getAlamatPB());
		hash.put("poskod", myIdentity.getPoskod() == null ? "" : myIdentity.getPoskod());
		hash.put("negeri", myIdentity.getNegeri() == null ? "" : myIdentity.getNegeri());
		hash.put("bandar", myIdentity.getBandar() == null ? "" : myIdentity.getBandar());

		return hash;
	}
	
	// START WEBSERVICES
	private static NoKadPengenalan getMaklumatMyIdentityFromAgency(String noKadPengenalan, String url) {

		CapaianMyIdentityWebServiceStub stub;
		NoKadPengenalan myIdentity = new NoKadPengenalan();

		try {
			stub = new CapaianMyIdentityWebServiceStub(url);

			GetNoKadPengenalan request = new GetNoKadPengenalan();
			request.setNoKadPengenalan(noKadPengenalan);
			
			GetNoKadPengenalan temp = new GetNoKadPengenalan();
			temp.setNoKadPengenalan(noKadPengenalan);
			GetNoKadPengenalanResponse response = stub.getNoKadPengenalan(temp);
			myIdentity = response.get_return();
			/*GetHakmilikByCarianRasmiE temp = new GetHakmilikByCarianRasmiE();
			temp.setGetHakmilikByCarianRasmi(request);*/

			/*GetHakmilikByCarianRasmiResponse response = stub
					.getHakmilikByCarianRasmi(temp)
					.getGetHakmilikByCarianRasmiResponse();
			hakmilik = response.get_return();*/
			if (myIdentity != null && myIdentity.getNoKadPengenalan() != null) {
				//insertHakmilikFromEtanah(idPermohonanSimati, noResit, hakmilik);
			} else {
				setFlagMsg("N");
				setOutputMsg("HAKMILIK TIDAK DITEMUI");
			}

		} catch (Exception e) {
			e.printStackTrace();
			setFlagMsg("N");
			setOutputMsg(e.toString());
		}
		return myIdentity;
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
