package integrasi.ws.mt.reg;

import integrasi.IntegrasiManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;



public class MTRegManager {
	
	private static IntegrasiManager im = null;
	private static String userName = ""; 
	private static String password = ""; 
	//url sit/live 
	private static String url = "";
	private static String source = "";
	private static String eventName = "";
	private static URL objURL = null;
	private static String msg = "";

	public MTRegManager(String kod) throws Exception{
		im = new IntegrasiManager(kod);	
		eventName = im.geTujuan();
		password = im.getKataLaluan();
		source = im.getSumber();
		url = im.getURL();
		userName = im.getIdPengguna();
		
	}
	
	public static String PendaftaranBaharu(CauseofactionType[] causeofaction
		,DocumentType[] document
		,PartyType[] party
		,String courtLocation,
		String division, String jurisdiction, String sourceReferenceNo,
		String valueInvolved, String transactionID) {		
		try {			
			//DATA
			DataCreateReqType data = new DataCreateReqType();
			//CauseofactionType[] causeofaction = new CauseofactionType();
			data.setCauseofaction(causeofaction);
			
			DeceaseInfoType deceaseInfo = new DeceaseInfoType();
			data.setDeceaseInfo(deceaseInfo);
			
			//DocumentType document = new DocumentType();
			data.setDocument(document);
			
			//PartyType party = new PartyType();
			data.setParty(party);
			
			data.setCourtLocation(courtLocation);
			data.setDivision(division);
			data.setJurisdiction(jurisdiction);
			data.setSourceReferenceNo(sourceReferenceNo);
			data.setValueInvolved(valueInvolved);			
						
			Civilregistercaseresponse response = submitMT(data, transactionID);			
			if (response != null) {
				msg = response.getCode() + "," + response.getDescription() + " , " + response.getDetail();
				if (response.getData() != null) {
//					if (response.getData().getBlueCardID() != null) {
//						idKadBiru = response.getData().getBlueCardID();
//					}
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			msg = " " + "," + ex.getMessage();
		}	
		return msg;
		
	}
	
	private static Civilregistercaseresponse submitMT(DataCreateReqType data, String transactionID) 
		throws RemoteException,MalformedURLException {
		//CivilRegisterCaseSOAPStub stub;
		Civilregistercaseresponse response = new Civilregistercaseresponse();
			
		if(im.isUrlValid(url)) {
			objURL = new URL(url);
		}
		CivilRegisterCaseSOAPStub stub = new CivilRegisterCaseSOAPStub(objURL,null);
			
		Civilregistercaserequest request = new Civilregistercaserequest();
		//DataCreateReqType source = new DataCreateReqType();
		request.setTransactionID(transactionID);
		request.setUsername(userName);
		request.setPassword(password);

		CivilregistercaserequestEventName event = new CivilregistercaserequestEventName(eventName);
		request.setEventName(event);
		
		request.setData(data);
			
		response = stub.civilRegisterCase(request);		
		return response;
			
	}
	
	public static String getEventName() {
		return eventName;
	}

	public static void setEventName(String eventName) {
		MTRegManager.eventName = eventName;
	}
	
	
}
