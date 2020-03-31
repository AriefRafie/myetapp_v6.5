package integrasi.ws.mt.reg;

import integrasi.IntegrasiManager;

public class MTRegManagerTest {
	static MTRegManager im = null;
	
	public static void main(String[] args)  throws Exception{
		im = new MTRegManager("MT");
		//System.out.println("Trying to start service at...[" + im.getURL() + "]");
		
		
		//CauseofactionType causeofaction = new CauseofactionType();
        int array[] = new int[7];
        //16A	B8BA9758-E1CE-40A2-99FC-01FAD4462FB4	Rayuan di bawah undang-undang bertulis selain daripada rayuan di bawah kod-kod spesifik	A
        
        CauseofactionType cof = new CauseofactionType("16A"
        		,""
        		,"B8BA9758-E1CE-40A2-99FC-01FAD4462FB4"
        		);
        CauseofactionType[] causeofaction = null;
        causeofaction = new CauseofactionType[1];
        //causeofaction = cof;
        
        DocumentType[] document = new DocumentType[1];

        PartyType[] party = new PartyType[1];
        
        String strRes = im.PendaftaranBaharu(causeofaction
				, document
				, party
				, "RA" 	//MT Kangar
				, "3"	//Civil
				, "2"	//High Court
				, "20200331"
				, "0.00"
				, "");
		System.out.println("Trying to start service at...[" + strRes + "]");

	}
	
	
}