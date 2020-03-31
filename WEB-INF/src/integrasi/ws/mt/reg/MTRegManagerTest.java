package integrasi.ws.mt.reg;

import integrasi.IntegrasiManager;

public class MTRegManagerTest {
	static MTRegManager im = null;
	
	public static void main(String[] args)  throws Exception{
		im = new MTRegManager("MT");
		//System.out.println("Trying to start service at...[" + im.getURL() + "]");
		
		
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