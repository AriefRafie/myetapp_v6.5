package integrasi.ws.etanah;

import javax.jws.WebService;
import javax.xml.soap.SOAPElement;

import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.Stub;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.engine.AxisConfiguration;

import integrasi.IntegrasiManager;
import integrasi.ws.etanah.ppt.ETanahPPTManager;
//import integrasi.ws.etanah.ppt.HakmilikForm;
//import integrasi.ws.etanah.ppt.PemilikForm;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikDetailByCarianResit;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikDetailByCarianResitE;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikDetailByCarianResitResponse;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikForm;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.PemilikForm;

public class ETanahPPTManagerTest {
	static ETanahPPTManager etanah = null;
	
	public static void main(String[] args)  throws Exception{
		IntegrasiManager im = new IntegrasiManager("E-TANAH");	
//		eventName = im.geTujuan();
//		password = im.getKataLaluan();
//		source = im.getSumber();
//		url = im.getURL();
//		userName = im.getIdPengguna();
//		etanah = new ETanahPPTManager("E-TANAH");
		//System.out.println("Trying to start service at...[" + im.getURL() + "]");
		String noResit = "202111601250030";
		String idHakmilik="040206GM00000091";
		HakmilikForm hf = null;
		
//		AxisConfiguration ac = new AxisConfiguration();
//		ac.addChildElement("Username");
//		ConfigurationContext cc = new ConfigurationContext(ac);
//		WebService service = new WSService(); 
//		WSPortTypePortType port = service.WSPortTypeSoap();
//		AuthenticationInfo auth = new AuthenticationInfo();
//		auth.setUserName(yourUsername);
//		auth.setPassword(yourPassword);
//		port.method(param1,param2,auth);
		
		MyEtappPengambilanServiceStub stub = new MyEtappPengambilanServiceStub(im.getURL());
		ServiceClient sc = new ServiceClient();
		OMElement e = null;
		OMAttribute a = null;
		a.setAttributeType("username");
		a.setAttributeValue("myetapp");
		a.setAttributeType("password");
		a.setAttributeValue("etanah123");
		e.addAttribute(a);
		sc.addHeader(e);
//		stub._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl); 
//		WebService service = (WebService) stub;
//		service.authenticateUser(username,password);
//		org.apache.axis.message.SOAPHeaderElement e = new org.apache.axis.message.SOAPHeaderElement(im.getURL(),"AuthHeader");
//		SOAPElement node = e.addChildElement("Username");
//		node.addTextNode("myetapp");
//		SOAPElement node2 = e.addChildElement("Password");
//		node2.addTextNode("etanah123");
//
//		((MyEtappPengambilanServiceStub)stub).setHeader(e);
	
		HakmilikDetailByCarianResit request = new HakmilikDetailByCarianResit();
		request.setNoResit(noResit);
		request.setIdHakmilik(idHakmilik);
//
		HakmilikDetailByCarianResitE temp = new HakmilikDetailByCarianResitE();
		temp.setHakmilikDetailByCarianResit(request);
//
		HakmilikDetailByCarianResitResponse response = stub.hakmilikDetailByCarianResit(temp).getHakmilikDetailByCarianResitResponse();
		hf = response.get_return();
		
		if (hf != null && hf.getIdHakmilik() != null) {
			System.out.println("hf:NO.LOT="+hf.getNoPT());
			PemilikForm[] pf = hf.getListPemilik();
			
		} else {
			etanah.setFlagMsg("N");
			etanah.setOutputMsg("HAKMILIK TIDAK DITEMUI");
		}	
		


		System.out.println("Trying to start service at...[" + etanah.getOutputMsg() + "]");

	}
	
	
}