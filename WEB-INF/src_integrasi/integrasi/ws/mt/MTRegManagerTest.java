package integrasi.ws.mt;

import my.gov.kehakiman.eip.services.CauseofactionType;
import my.gov.kehakiman.eip.services.DeceaseInfoType;
import my.gov.kehakiman.eip.services.DocumentType;
import my.gov.kehakiman.eip.services.PartyType;

//import integrasi.IntegrasiManager;

public class MTRegManagerTest {
	static MTManagerReg im = null;
	
	public static void main(String[] args)  throws Exception{
		im = new MTManagerReg("MTREG");
		System.out.println("Trying to start service at...[" + im + "]");
		
		
		//CauseofactionType causeofaction = new CauseofactionType();
        //16A	B8BA9758-E1CE-40A2-99FC-01FAD4462FB4	Rayuan di bawah undang-undang bertulis selain daripada rayuan di bawah kod-kod spesifik	A
        
        CauseofactionType cof = new CauseofactionType("16A"
        		,""
        		,"B8BA9758-E1CE-40A2-99FC-01FAD4462FB4"
        		);
//        CauseofactionType cof = new CauseofactionType();
//		cof.setCauseOfActionRefID("1");
//		cof.setCauseOfActionID("00FF2689-FA1E-4332-A101-7D7FC9A11C4E");
//		cof.setCauseOfActionDesc("Rujukan Tanah"); 
        CauseofactionType[] causeofaction = null;
        causeofaction = new CauseofactionType[1];
        causeofaction[0] = cof;
        
        DocumentType dt = new DocumentType();
        //dt.setDocContent(docContent);	//64bit
        //dt.setDocID(docID);
        dt.setDocEnlcNo("1");	//default 1
        dt.setDocName("nama_dokumen");
        dt.setDocType("TSR013");
        DocumentType[] document = new DocumentType[1];
        document[0] = dt;
        
        PartyType pt = new PartyType();
        pt.setPartyID("12345");
        pt.setPartyAddr1("partyAddr1");
        pt.setPartyAddr2("partyAddr2");
        pt.setPartyAddr3("partyAddr3");
        pt.setPartyPostcode("47100");
        pt.setPartyAge("45");
        pt.setPartyCity("00"); //tiada rujukan
        pt.setPartyState("10");
        pt.setPartyNationality("00"); //tiada rujukan
        pt.setPartyCountry("MYS");
        pt.setPartyGender("M"); //F, U-UNDEFIND
        //pt.setPartyID("CA5A0F64-061E-423D-BB68-04DEC7D28609"); //Perayu (15)
        pt.setPartyIDType("IC");	//new IC
        pt.setPartyIDNo1("760908035336");
        //NO IC
       // pt.setPartyIDNo2("760908035336");
        
        pt.setPartyTypeID("CA5A0F64-061E-423D-BB68-04DEC7D28609"); //Perayu (15)
        //pt.setPartyGender(partyGender)
        pt.setPartyName("Nama saya");
        //31NCvC only
        //pt.setPartyRelationship("");
        
        PartyType[] party = new PartyType[1];
        party[0] = pt;
        
        DeceaseInfoType deceaseInfo = new DeceaseInfoType("mohamad rosli"
        								,"" //info
        								,"IC"
        								,"760908-03-5335"
        								,""
        								,""
        								,""
        								,""		//deceaseInfoGender,
        								,""		//deceaseInfoAge,
                  						,"02/02/2019"		// dateOfDeath,
                  						,"no. 12"	// deceaseInfoAddr1,
                  						,"jln pp8"	//deceaseInfoAddr2,
                  						,""			//deceaseInfoAddr3,
                  						,"47100"	//java.lang.String deceaseInfoPostcode,
                  						,"0"		//deceaseInfoCity,
                  						,"10"		//deceaseInfoState,
                  						,"MYS"		//deceaseInfoCountry
                  						);
            
        
//        String strRes = im.PendaftaranBaharu(causeofaction
//				, document
//				, party
//				, "RA" 	//MT Kangar
//				, "3"	//Civil
//				, "2"	//High Court
//				, "20200331"
//				, "0.00"
//				, "");
 
//        String strRes = im. PendaftaranBaharu("15","12345","nama_fail",""
//    			,party
//    			,deceaseInfo
//    			, "RA" 	//MT Kangar
//				, "3"	//Civil
//				, "2"	//High Court
//				, "20200331"
//				, "0.00"
//				, "");
//
//		System.out.println("Trying to start service at...[" + strRes + "]");

	}
	
	
}