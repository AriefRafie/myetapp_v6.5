package integrasi.ws.etanah.ppt;

import integrasi.IntegrasiManager;
//import integrasi.ws.etanah.melaka_ns.htp.EtanahHTPManager;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmi;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmiE;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmiResponse;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.Hakmilik;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikForm;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class ETanahPPTManager {
	
	private static IntegrasiManager im = null;
	private static String userName = ""; 
	private static String password = ""; 
	private static String caseCode = "";
	private static String url = "";
	private static String source = "";
	private static String eventName = "";
	private static URL objURL = null;
	private static String msg = "";
	private static String msgDaftar = "";
	private MyEtappPengambilanServiceStub stub = null;
	public static String flagMsg = null;
	public static String outputMsg = null;

	public ETanahPPTManager(String kod) throws Exception{
		im = new IntegrasiManager(kod);	
		eventName = im.geTujuan();
		password = im.getKataLaluan();
		source = im.getSumber();
		url = im.getURL();
		userName = im.getIdPengguna();
		
	}
	
	public HakmilikForm carian(String noResit, String idHakmilik) throws RemoteException,MalformedURLException {
		HakmilikForm hf = null;
		flagMsg = "Y";
		outputMsg = "MAKLUMAT BERJAYA DICAPAI";

		try {			
			if(im.isUrlValid(url)) {
				objURL = new URL(url);
			}
			
//			stub = new MyEtappPengambilanPortBindingStub(objURL,null);		
//			stub.setUsername(userName);
//			stub.setPassword(password);
//		
//			hf = stub.hakmilikDetailByCarianResit(noResit, idHakmilik);
			
			

//		EtappPesakaServiceStub stub = new EtappPesakaServiceStub(url);
//		GetHakmilikByCarianRasmi request = new GetHakmilikByCarianRasmi();
//		request.setNoResit(noResit);
//		request.setIdHakmilik(idHakmilik);
//
//		GetHakmilikByCarianRasmiE temp = new GetHakmilikByCarianRasmiE();
//		temp.setGetHakmilikByCarianRasmi(request);
//
//		GetHakmilikByCarianRasmiResponse response = stub.getHakmilikByCarianRasmi(temp).getGetHakmilikByCarianRasmiResponse();
//		Hakmilik hakmilik = response.get_return();
		} catch (Exception e) {
			//e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}
		return hf;
			
	}
	
	public static String getEventName() {
		return eventName;
	}

	public static void setEventName(String eventName) {
		ETanahPPTManager.eventName = eventName;
	}
	
	public static String getFlagMsg() {
		return flagMsg;
	}

	public static void setFlagMsg(String flagMsg) {
		ETanahPPTManager.flagMsg = flagMsg;
	}

	public static String getOutputMsg() {
		return outputMsg;
	}

	public static void setOutputMsg(String outputMsg) {
		ETanahPPTManager.outputMsg = outputMsg;
	}
	
}
