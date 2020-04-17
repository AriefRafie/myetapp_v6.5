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
	private static String caseCode = "";
	private static String url = "";
	private static String source = "";
	private static String eventName = "";
	private static URL objURL = null;
	private static String msg = "";
	private static String msgDaftar = "";

	public MTRegManager(String kod) throws Exception{
		im = new IntegrasiManager(kod);	
		eventName = im.geTujuan();
		password = im.getKataLaluan();
		source = im.getSumber();
		url = im.getURL();
		userName = im.getIdPengguna();
		
	}
	
	public static String PendaftaranBaharu(String typeReg,String docID,String docName,String docContent
		,PartyType[] party
		,DeceaseInfoType deceaseInfo
		,String courtLocation,
		String division, String jurisdiction, String sourceReferenceNo,
		String valueInvolved, String transactionID){
		caseCode = typeReg;
		CauseofactionType cof = null;
		String docType = "";
		if(typeReg.equals("15")){
			cof = new CauseofactionType(typeReg
        							,"Rujukan Tanah"
        							,"00FF2689-FA1E-4332-A101-7D7FC9A11C4E");
			docType ="TSP113";
			
		}else if(typeReg.equals("16A")){
			cof = new CauseofactionType(typeReg
					,"Rayuan di bawah undang-undang bertulis selain daripada rayuan di bawah kod-kod spesifik"
					,"B8BA9758-E1CE-40A2-99FC-01FAD4462FB4");
			docType ="TSR013";
		
		}else if(typeReg.equals("31NCvC")){
			cof = new CauseofactionType(typeReg
					,"Surat Kuasa Mentadbir"
					,"9479101A-AB53-4E0A-948A-53C6C7577051");
			docType ="TSI034";
		
		}	
		
        CauseofactionType[] causeofaction = new CauseofactionType[1];
        causeofaction[0] = cof;
        DocumentType dt = new DocumentType();
        dt.setDocContent(docContent);	//64bit
        dt.setDocID(docID);
        dt.setDocEnlcNo("1");	//default 1
        dt.setDocName(docName);
        dt.setDocType(docType);
        DocumentType[] document = new DocumentType[1];
        document[0] = dt;
        
		PendaftaranBaharu(causeofaction
				,document
				,party
				,deceaseInfo
				,courtLocation,division,jurisdiction,sourceReferenceNo,valueInvolved,transactionID);
		return msgDaftar;
	}		

	public static String PendaftaranBaharu(CauseofactionType[] causeofaction
		,DocumentType[] document
		,PartyType[] party
		,DeceaseInfoType deceaseInfo
		,String courtLocation,
		String division, String jurisdiction, String sourceReferenceNo,
		String valueInvolved, String transactionID) {		
		try {			
			//DATA
			DataCreateReqType data = new DataCreateReqType();
			//CauseofactionType[] causeofaction = new CauseofactionType();
			data.setCauseofaction(causeofaction);
			//untuk case_code 31NCVC
			//DeceaseInfoType deceaseInfo = new DeceaseInfoType();
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
			data.setCaseCode(caseCode);
			
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
		request.setSource(source);

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
