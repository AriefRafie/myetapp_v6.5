package integrasi.ws.etanah;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.xml.soap.SOAPElement;

import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.Stub;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.engine.AxisConfiguration;

import org.apache.commons.httpclient.Header;

import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikDetailByCarianResit;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikDetailByCarianResitE;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikDetailByCarianResitResponse;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikForm;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.PemilikForm;

public class ETanahPPTManagerTestAxis2 {
	//static ETanahPPTManager etanah = null;
	
	public static void main(String[] args)  throws Exception{
		//IntegrasiManager im = new IntegrasiManager("E-TANAH");	
//		eventName = im.geTujuan();
//		password = im.getKataLaluan();
//		source = im.getSumber();
//		url = im.getURL();
		String url = "http://etanah.melaka.gov.my/etanahwsastg/MyEtappPengambilanService?wsdl";
		//String url = "http://kjsb.zapto.org:6009/etanahwsa/MyEtappPengambilanService?wsdl";
//		userName = im.getIdPengguna();
//		etanah = new ETanahPPTManager("E-TANAH");
		String userName = "myetapp";
		String password = "etanah123";
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
		System.out.println("URL="+url);
		
		
		MyEtappPengambilanServiceStub stub = new MyEtappPengambilanServiceStub(url);
		
		
		org.apache.axis2.client.Options options = new org.apache.axis2.client.Options();
		List<org.apache.axis2.context.NamedValue> namedValuePairs = new ArrayList<org.apache.axis2.context.NamedValue>();
		namedValuePairs.add(new org.apache.axis2.context.NamedValue("SOAPAction", "myetapp"));
		namedValuePairs.add(new org.apache.axis2.context.NamedValue("username", "myetapp"));
		namedValuePairs.add(new org.apache.axis2.context.NamedValue("password", "etanah123"));
		namedValuePairs.add(new org.apache.axis2.context.NamedValue("Content-Type", "text/html"));
		options.setProperty(org.apache.axis2.transport.http.HTTPConstants.HTTP_HEADERS, namedValuePairs);
		stub._getServiceClient().setOptions(options);
		
		stub._getServiceClient().setTargetEPR(new EndpointReference(url));
		System.out.println(stub._getServiceClient().getTargetEPR());
		 
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
		
		String mesej = "";
		if (hf != null && hf.getIdHakmilik() != null) {
			//System.out.println("hf:NO.LOT="+hf.getNoPT());
			mesej = "hf:NO.LOT="+hf.isNoPTSpecified()+"-"+hf.getNoPT();

			PemilikForm[] pf = hf.getListPemilik();
			mesej += "\n pf:size="+pf.length;
			
			//PemilikForm[] pemilik = hakmilik.getListPemilik();				
			for (int i = 0; i < pf.length; i++){
				PemilikForm pemilik = pf[i];
				System.out.print(pemilik.getNamaPemilik());
		

			} 
		} else {
			//etanah.setFlagMsg("N");
			//etanah.setOutputMsg("HAKMILIK TIDAK DITEMUI");
			mesej = "HAKMILIK TIDAK DITEMUI";
		}	
		


		System.out.println("Trying to start service at...[" + mesej + "]");

	}
	
	
}