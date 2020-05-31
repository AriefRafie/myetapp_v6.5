package integrasi.ws.etanah.ppt;

import integrasi.IntegrasiManager;
//import integrasi.ws.etanah.melaka_ns.htp.EtanahHTPManager;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmi;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmiE;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmiResponse;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.Hakmilik;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class ETanahPPTManager {
	
	private static IntegrasiManager im = null;
	private static String userName = ""; 
	private static String password = ""; 
	//url sit/live 
	private static String caseCode = "";
	private static String url = "";
	private static String source = "";
	private static String eventName = "";
	private static URL objURL = null;
	private static String msg = "";
	private static String msgDaftar = "";
	private MyEtappPengambilanPortBindingStub stub = null;
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
	
//	public static String PendaftaranBaharu(String typeReg,String docID,String docName,String docContent
//		,PartyType[] party
//		,DeceaseInfoType deceaseInfo
//		,String courtLocation,
//		String division, String jurisdiction, String sourceReferenceNo,
//		String valueInvolved, String transactionID){
//		caseCode = typeReg;
//		CauseofactionType cof = null;
//		String docType = "";
//		if(typeReg.equals("15")){
//			cof = new CauseofactionType(typeReg
//        							,"Rujukan Tanah"
//        							,"00FF2689-FA1E-4332-A101-7D7FC9A11C4E");
//			docType ="TSP113";
//			
//		}else if(typeReg.equals("16A")){
//			cof = new CauseofactionType(typeReg
//					,"Rayuan di bawah undang-undang bertulis selain daripada rayuan di bawah kod-kod spesifik"
//					,"B8BA9758-E1CE-40A2-99FC-01FAD4462FB4");
//			docType ="TSR013";
//		
//		}else if(typeReg.equals("31NCvC")){
//			cof = new CauseofactionType(typeReg
//					,"Surat Kuasa Mentadbir"
//					,"9479101A-AB53-4E0A-948A-53C6C7577051");
//			docType ="TSI034";
//		
//		}	
//		
//        CauseofactionType[] causeofaction = new CauseofactionType[1];
//        causeofaction[0] = cof;
//        DocumentType dt = new DocumentType();
//        dt.setDocContent(docContent);	//64bit
//        dt.setDocID(docID);
//        dt.setDocEnlcNo("1");	//default 1
//        dt.setDocName(docName);
//        dt.setDocType(docType);
//        DocumentType[] document = new DocumentType[1];
//        document[0] = dt;
//        
//		PendaftaranBaharu(causeofaction
//				,document
//				,party
//				,deceaseInfo
//				,courtLocation,division,jurisdiction,sourceReferenceNo,valueInvolved,transactionID);
//		return msgDaftar;
//	}		

//	public static String PendaftaranBaharu(CauseofactionType[] causeofaction
//		,DocumentType[] document
//		,PartyType[] party
//		,DeceaseInfoType deceaseInfo
//		,String courtLocation,
//		String division, String jurisdiction, String sourceReferenceNo,
//		String valueInvolved, String transactionID) {		
//		try {			
//			//DATA
//			DataCreateReqType data = new DataCreateReqType();
//			//CauseofactionType[] causeofaction = new CauseofactionType();
//			data.setCauseofaction(causeofaction);
//			//untuk case_code 31NCVC
//			//DeceaseInfoType deceaseInfo = new DeceaseInfoType();
//			data.setDeceaseInfo(deceaseInfo);
//			
//			//DocumentType document = new DocumentType();
//			data.setDocument(document);
//			
//			//PartyType party = new PartyType();
//			data.setParty(party);
//			
//			data.setCourtLocation(courtLocation);
//			data.setDivision(division);
//			data.setJurisdiction(jurisdiction);
//			data.setSourceReferenceNo(sourceReferenceNo);
//			data.setValueInvolved(valueInvolved);			
//			data.setCaseCode(caseCode);
//			
//			Civilregistercaseresponse response = submitMT(data, transactionID);			
//			if (response != null) {
//				msg = response.getCode() + "," + response.getDescription() + " , " + response.getDetail();
//				if (response.getData() != null) {
////					if (response.getData().getBlueCardID() != null) {
////						idKadBiru = response.getData().getBlueCardID();
////					}
//				}
//			}
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			msg = " " + "," + ex.getMessage();
//		}	
//		return msg;
//		
//	}
//	
	public HakmilikForm carian(String noResit, String idHakmilik) throws RemoteException,MalformedURLException {
		HakmilikForm hf = null;
		flagMsg = "Y";
		outputMsg = "MAKLUMAT BERJAYA DICAPAI";

		try {			
			if(im.isUrlValid(url)) {
				objURL = new URL(url);
			}
			
			stub = new MyEtappPengambilanPortBindingStub(objURL,null);		
			stub.setUsername(userName);
			stub.setPassword(password);
		
			hf = stub.hakmilikDetailByCarianResit(noResit, idHakmilik);
			
			

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
