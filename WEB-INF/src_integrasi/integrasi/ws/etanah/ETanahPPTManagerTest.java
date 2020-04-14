package integrasi.ws.etanah;

import integrasi.IntegrasiManager;
import integrasi.ws.etanah.ppt.ETanahPPTManager;
import integrasi.ws.etanah.ppt.HakmilikForm;
import integrasi.ws.etanah.ppt.PemilikForm;

public class ETanahPPTManagerTest {
	static ETanahPPTManager etanah = null;
	
	public static void main(String[] args)  throws Exception{
		etanah = new ETanahPPTManager("ETANAH");
		//System.out.println("Trying to start service at...[" + im.getURL() + "]");
		String noResit = "";
		String idHakmilik="";
		HakmilikForm hf = null;
		hf = etanah.carian(noResit, idHakmilik);
		
		if (hf != null && hf.getIdHakmilik() != null) {
			System.out.println("hf:NO.LOT="+hf.getNoPT());
			PemilikForm[] pf = hf.getListPemilik1();
			
		} else {
			etanah.setFlagMsg("N");
			etanah.setOutputMsg("HAKMILIK TIDAK DITEMUI");
		}	
		


		System.out.println("Trying to start service at...[" + etanah.getOutputMsg() + "]");

	}
	
	
}