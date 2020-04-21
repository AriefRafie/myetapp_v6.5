package integrasi.ws.mt.reg;

import integrasi.IntegrasiManager;
import integrasi.ws.mt.MTManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;



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
	private static String referenceNo ="";
	
	public MTRegManager() throws Exception{	}
	
	public MTRegManager(String kod) throws Exception{
		im = new IntegrasiManager(kod);	
		eventName = im.geTujuan();
		password = im.getKataLaluan();
		source = im.getSumber();
		url = im.getURL();
		userName = im.getIdPengguna();
		
	}
	
	public static String PendaftaranBaharu(String typeReg
		,String docID,String docName,String docContent
		,PartyType[] party
		,DeceaseInfoType deceaseInfo
		,String courtLocation,
		String division, String jurisdiction, String sourceReferenceNo,
		String valueInvolved, String transactionID){
		caseCode = typeReg;
		CauseofactionType cof = null;
		String docType = "";
		String cft = "1";
		if(typeReg.equals("15")){
			cof = new CauseofactionType(cft
        							,"00FF2689-FA1E-4332-A101-7D7FC9A11C4E"
        							,"Rujukan Tanah");
			docType ="75559776-2C29-472F-9D96-818E4868D2AE"; //TSP113
			
		}else if(typeReg.equals("16A")){
			cof = new CauseofactionType(cft
					,"B8BA9758-E1CE-40A2-99FC-01FAD4462FB4"
					,"Rayuan di bawah undang-undang bertulis selain daripada rayuan di bawah kod-kod spesifik");
			docType ="48367E28-D18C-4A9D-B078-3D1ED2ED695E";	//TSR013
		
		}else if(typeReg.equals("31NCvC")){
			cof = new CauseofactionType(cft
					,"9479101A-AB53-4E0A-948A-53C6C7577051"
					,"Surat Kuasa Mentadbir");
			docType ="E7DD535E-187F-4D09-9278-EB3B50DD6926";	//TSI034
		
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
//		<docID>01</docID>
//		<DocEnlcNo>1</DocEnlcNo>
//		<docType>75559776-2C29-472F-9D96-818E4868D2AE</docType>
//		<docName>TestDocSamanPemula15.PDF</docName>

        msgDaftar = PendaftaranBaharu(causeofaction
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
			data.setCaseCode("15");
			//data.setCaseCode(caseCode);
	
			Civilregistercaseresponse response = submitMT(data, transactionID);			
			if (response != null) {
				msg = response.getCode() + "," + response.getDescription() + " , " + response.getDetail();
				if (response.getData() != null) {
					response.getData().getSourceReferenceNo();
					if (response.getData().getSourceReferenceNo() != null) {
						referenceNo = response.getData().getSourceReferenceNo();
					}
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
	
	public static PartyType getParty15(String idRuj
		,String name
		,String add,String add2,String add3,String postcode,String stateCode,String city){
	    PartyType pt = new PartyType();
	    pt.setPartyID(idRuj);
	    pt.setPartyAddr1(add);
	    pt.setPartyAddr2(add2);
	    pt.setPartyAddr3(add3);
	    pt.setPartyPostcode(postcode);
	    pt.setPartyAge("45"); //wajib
	    pt.setPartyCity(city); //tiada rujukan - Nama Bandar
	    pt.setPartyState(stateCode);
	    pt.setPartyNationality("MYS"); //tiada rujukan| MYS
	    pt.setPartyCountry("MYS");
	    pt.setPartyGender("U"); //M,F, U-UNDEFIND
	    pt.setPartyIDType("GA");	//IC = NRIC,GA = Government Agency
	    //pt.setPartyIDNo1("760908035336");	//Identity No of Party If PartyIDType !=IC
	    //NO IC
	   // pt.setPartyIDNo2("760908035336");	    
	    pt.setPartyTypeID("CA5A0F64-061E-423D-BB68-04DEC7D28609"); //Perayu (15)
	    pt.setPartyName(name);
	    
	    //31NCvC only
	    //pt.setPartyRelationship("");
		return pt;
	
	}
	
	public String getKodMT(String idPejabat) throws Exception{
	    Db db = null;
	    String sql = "";
	    String code = "";
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	SQLRenderer r = new SQLRenderer();
	    	//r.add("L.CODE");
	    	r.add("L.LOCATION");
	    	r.add("LM.ID_PEJABAT",idPejabat);
	  		r.relate("L.ID_LOCATION", "LM.ID_LOCATION");
	  		sql = r.getSQLSelect("TBLINTMTLOCATION L,TBLINTMTLOCATIONMAP LM");
	  		//myLog.info(sql);
	  		ResultSet rs = stmt.executeQuery(sql);
	      
	  		while (rs.next()) {
	  			code = rs.getString("LOCATION");
	  		}
	      
	    } catch (Exception e) {
	    	//myLog.info(e.getMessage());
	    	return null;
	    }finally {
	      if (db != null) db.close();
	    }
	    return code;
	    
	}
	public static String getReferenceNo() {
		return referenceNo;
	}

	public static void setReferenceNo(String referenceNo) {
		MTRegManager.referenceNo = referenceNo;
	}
	
}
