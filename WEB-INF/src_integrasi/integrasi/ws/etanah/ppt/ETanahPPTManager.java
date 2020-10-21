package integrasi.ws.etanah.ppt;

import integrasi.IntegrasiManager;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.BorangCdanMMK;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.BorangCdanMMKE;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.BorangCdanMMKResponse;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.DaftarPermohonanBorangAMMk;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.DaftarPermohonanBorangAMMkE;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.DaftarPermohonanBorangAMMkResponse;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.DaftarPermohonanSek8;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.DaftarPermohonanSek8E;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.DaftarPermohonanSek8Response;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.EndorsBorangDMaklumatWarta;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.EndorsBorangDMaklumatWartaE;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.EndorsBorangDMaklumatWartaResponse;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.EndorsBorangK;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.EndorsBorangKE;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.EndorsBorangKResponse;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikDetailByCarianResit;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikDetailByCarianResitE;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikDetailByCarianResitResponse;
//import integrasi.ws.etanah.melaka_ns.htp.EtanahHTPManager;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmi;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmiE;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmiResponse;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.Hakmilik;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikForm;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.LampiranForm;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MaklumanPenghantaranPU;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MaklumanPenghantaranPUE;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MaklumanPenghantaranPUResponse;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MaklumatHakmilikForm;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MaklumatPermohonanSek4Form;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MaklumatPermohonanSek8Form;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.SijilPembebasanUkur;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.SijilPembebasanUkurE;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.SijilPembebasanUkurResponse;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;

public class ETanahPPTManager {
	
	private static IntegrasiManager im = null;
	private static String userName = ""; 
	private static String password = ""; 
	private static String caseCode = "";
	private static String url = "";
	private static String source = "";
	private static String eventName = "";
	private static URL objURL = null;
	private static String msg = "";
	private static String msgDaftar = "";
	//private MyEtappPengambilanServiceStub stub = null;
	public static String flagMsg = null;
	public static String outputMsg = null;
	private static MyEtappPengambilanServiceStub stub = null;

	public ETanahPPTManager(String kod) throws Exception{
		im = new IntegrasiManager(kod);	
		eventName = im.geTujuan();
		password = im.getKataLaluan();
		source = im.getSumber();
		url = im.getURL();
		userName = im.getIdPengguna();
		
	}
	
	public static HakmilikForm carian(String noResit, String idHakmilik) throws RemoteException,MalformedURLException {
		HakmilikForm hf = null;
		flagMsg = "Y";
		outputMsg = "MAKLUMAT BERJAYA DICAPAI";
		try {			
//			if(im.isUrlValid(url)) {
//				uel = new URL(url);
//			}
			
			stub = new MyEtappPengambilanServiceStub(url);	
			
			Options options1 = new Options();
			
			org.apache.axis2.client.Options options = new org.apache.axis2.client.Options();
			List<org.apache.axis2.context.NamedValue> namedValuePairs = new ArrayList<org.apache.axis2.context.NamedValue>();
			namedValuePairs.add(new org.apache.axis2.context.NamedValue("SOAPAction", "myetapp"));
			namedValuePairs.add(new org.apache.axis2.context.NamedValue("username", userName));
			namedValuePairs.add(new org.apache.axis2.context.NamedValue("password", password));
			namedValuePairs.add(new org.apache.axis2.context.NamedValue("Content-Type", "text/html"));
			options.setProperty(org.apache.axis2.transport.http.HTTPConstants.HTTP_HEADERS, namedValuePairs);
			stub._getServiceClient().setOptions(options);
			
			stub._getServiceClient().setTargetEPR(new EndpointReference(url));
//			System.out.println(stub._getServiceClient().getTargetEPR());
			
			HakmilikDetailByCarianResit request = new HakmilikDetailByCarianResit();
			request.setNoResit(noResit);
			request.setIdHakmilik(idHakmilik);
	//
			HakmilikDetailByCarianResitE temp = new HakmilikDetailByCarianResitE();
			temp.setHakmilikDetailByCarianResit(request);
	//
			HakmilikDetailByCarianResitResponse response = stub.hakmilikDetailByCarianResit(temp).getHakmilikDetailByCarianResitResponse();
			hf = response.get_return();
			
		} catch (Exception e) {
			//e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}
		return hf;
			
	}
	
	//public DaftarPermohonanSek8 hantar() {
	public String permohonanSek4( MaklumatPermohonanSek4Form form
		,MaklumatHakmilikForm[] hakmiliks
		,LampiranForm mmk
		,LampiranForm[] lampiran
		) throws Exception{
		//MaklumatPermohonanSek8Form permohonan = null;
		stub = getStub();
		DaftarPermohonanBorangAMMk request = new DaftarPermohonanBorangAMMk();
		
		request.setMaklumatPermohonan(form);
		request.setMaklumatHakmilik(hakmiliks);
		request.setDrafMMk(mmk);
		request.setAttachment(lampiran);
		
		DaftarPermohonanBorangAMMkE temp = new DaftarPermohonanBorangAMMkE();
		temp.setDaftarPermohonanBorangAMMk(request);
		
		DaftarPermohonanBorangAMMkResponse responseSek8 = stub.daftarPermohonanBorangAMMk(temp).getDaftarPermohonanBorangAMMkResponse();
		return responseSek8.get_return();
		
	}
	
	//public DaftarPermohonanSek8 hantar() {
	public String permohonanSek8( MaklumatPermohonanSek8Form form
		,MaklumatHakmilikForm[] hakmiliks
		,LampiranForm[] lampiran
		) throws Exception{
		//MaklumatPermohonanSek8Form permohonan = null;
		stub = getStub();
		DaftarPermohonanSek8 requestSek8 = new DaftarPermohonanSek8();
		
		requestSek8.setMaklumatPermohonan(form);
		requestSek8.setMaklumatHakmilik(hakmiliks);
		requestSek8.setAttachment(lampiran);
		
		DaftarPermohonanSek8E temp = new DaftarPermohonanSek8E();
		temp.setDaftarPermohonanSek8(requestSek8);
		
		DaftarPermohonanSek8Response responseSek8 = stub.daftarPermohonanSek8(temp).getDaftarPermohonanSek8Response();
		return responseSek8.get_return();
		
	}
	
	public String BorangC( String idPermohonan
		,LampiranForm lampiranMMK
		,LampiranForm[] lampiran
		) throws Exception{
		//MaklumatPermohonanSek8Form permohonan = null;
		stub = getStub();
		
		BorangCdanMMK request = new BorangCdanMMK();
		request.setIdPermohonan(idPermohonan);
		request.setDrafMMk(lampiranMMK);
		request.setAttachment(lampiran);
			
		BorangCdanMMKE temp = new BorangCdanMMKE();
		temp.setBorangCdanMMK(request);
			
		BorangCdanMMKResponse response = stub.borangCdanMMK(temp).getBorangCdanMMKResponse();
		return response.get_return();
			
	}
	
	public String endorsanD( Hashtable<String,String> hash,LampiranForm[] lampiran) throws Exception{
		//MaklumatPermohonanSek8Form permohonan = null;
		stub = getStub();
		EndorsBorangDMaklumatWarta request = new EndorsBorangDMaklumatWarta();
		//Hashtable<String,String> hash = new Hashtable<String,String>;
			
		request.setIdPermohonan(hash.get("idPermohonan"));
		request.setNoWarta(hash.get("noWarta"));
		request.setTarikhWarta(hash.get("tarikhWarta"));
		request.setAttachment(lampiran);
			
		EndorsBorangDMaklumatWartaE temp = new EndorsBorangDMaklumatWartaE();
		temp.setEndorsBorangDMaklumatWarta(request);
			
		EndorsBorangDMaklumatWartaResponse response = stub.endorsBorangDMaklumatWarta(temp).getEndorsBorangDMaklumatWartaResponse();
		return response.get_return();
			
	} 
	
	public String BorangK( Hashtable<String,String> hash,LampiranForm[] lampiran) throws Exception{
		stub = getStub();
		EndorsBorangK request = new EndorsBorangK();
		//Hashtable<String,String> hash = new Hashtable<String,String>;
			
		request.setIdPermohonan(hash.get("idPermohonan"));
		request.setNoWarta(hash.get("noWarta"));
		request.setTarikhWarta(hash.get("tarikhWarta"));
		request.setAttachment(lampiran);
			
		EndorsBorangKE temp = new EndorsBorangKE();
		temp.setEndorsBorangK(request);
			
		EndorsBorangKResponse response = stub.endorsBorangK(temp).getEndorsBorangKResponse();
		return response.get_return();
			
	} 
	
	public String PU( Hashtable<String,String> hash,LampiranForm[] lampiran) throws Exception{
		stub = getStub();
		MaklumanPenghantaranPU request = new MaklumanPenghantaranPU();			
		request.setIdPermohonan(hash.get("idPermohonan"));
		request.setNoRujukan(hash.get("noRujukan"));
		request.setTarikhHantar(hash.get("tarikh"));
		request.setAttachment(lampiran);
			
		MaklumanPenghantaranPUE temp = new MaklumanPenghantaranPUE();
		temp.setMaklumanPenghantaranPU(request);
			
		MaklumanPenghantaranPUResponse response = stub.maklumanPenghantaranPU(temp).getMaklumanPenghantaranPUResponse();
		return response.get_return();
			
	}
	
	public String pembebasanUkur(String idPermohonan,LampiranForm[] lampiran) throws Exception{
		//MaklumatPermohonanSek8Form permohonan = null;
		stub = getStub();
		SijilPembebasanUkur request = new SijilPembebasanUkur();
		//Hashtable<String,String> hash = new Hashtable<String,String>;
			
		request.setIdPermohonan(idPermohonan);
		request.setAttachment(lampiran);
			
		SijilPembebasanUkurE temp = new SijilPembebasanUkurE();
		temp.setSijilPembebasanUkur(request);
			
		SijilPembebasanUkurResponse response = stub.sijilPembebasanUkur(temp).getSijilPembebasanUkurResponse();
		return response.get_return();
			
	}
	
	private static MyEtappPengambilanServiceStub getStub() throws Exception {
		MyEtappPengambilanServiceStub stub = new MyEtappPengambilanServiceStub(url);	
				
		org.apache.axis2.client.Options options = new org.apache.axis2.client.Options();
		List<org.apache.axis2.context.NamedValue> namedValuePairs = new ArrayList<org.apache.axis2.context.NamedValue>();
		namedValuePairs.add(new org.apache.axis2.context.NamedValue("SOAPAction", "myetapp"));
		namedValuePairs.add(new org.apache.axis2.context.NamedValue("username", userName));
		namedValuePairs.add(new org.apache.axis2.context.NamedValue("password", password));
		namedValuePairs.add(new org.apache.axis2.context.NamedValue("Content-Type", "text/html"));
		options.setProperty(org.apache.axis2.transport.http.HTTPConstants.HTTP_HEADERS, namedValuePairs);
		stub._getServiceClient().setOptions(options);
		
		stub._getServiceClient().setTargetEPR(new EndpointReference(url));
		return stub;
		
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
