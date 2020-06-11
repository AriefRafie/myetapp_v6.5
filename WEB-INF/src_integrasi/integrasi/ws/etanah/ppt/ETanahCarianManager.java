package integrasi.ws.etanah.ppt;

import integrasi.IntegrasiManager;
//import integrasi.ws.etanah.melaka_ns.htp.EtanahHTPManager;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmi;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmiE;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmiResponse;
//import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.Hakmilik;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.Hakmilik;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.Pemilik;
import lebah.db.Db;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.Statement;

import ekptg.helpers.Utils;

public class ETanahCarianManager {
	
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
	private static MyEtappPengambilanPortBindingStub stub = null;
	public static String flagMsg = null;
	public static String outputMsg = null;

	public ETanahCarianManager(String kod) throws Exception{
		im = new IntegrasiManager(kod);	
		eventName = im.geTujuan();
		password = im.getKataLaluan();
		source = im.getSumber();
		url = im.getURL();
		userName = im.getIdPengguna();
		
	}
	
	//START 1ST POINT OF INTERGRATION - GET MAKLUMAT HAKMILIK FROM ETANAH
	public static void getMaklumatHakmilikFromEtanah(String noResit, String idHakmilik
		,String idPermohonan,String kodNegeri) {			
		//DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "CAPAIAN HAKMILIK BERJAYA";
			
		Db db = null;
		try {
			db = new Db();			
			HakmilikForm mt = null;
			if ("04".equals(kodNegeri)){
				mt = carian(noResit, idHakmilik);
			}
//				Hakmilik hakmilik = null;
//				if ("04".equals(kodNegeri)){
//					hakmilik = getMaklumatHakmilik(noResit, idHakmilik, idPermohonanSimati, urlMelaka);
//				} else if ("05".equals(kodNegeri)){
//					hakmilik = getMaklumatHakmilik(noResit, idHakmilik, idPermohonanSimati, urlNSembilan);
//				} 

			if (mt != null && mt.getIdHakmilik() != null) {
				insertHakmilikFromEtanah(idPermohonan, noResit, mt);
			} else {
				flagMsg = "N";
				outputMsg = "HAKMILIK TIDAK DITEMUI";
			}

		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
			
		} finally {
			if (db != null)
				db.close();

		}
		
	}
		
	private static void insertHakmilikFromEtanah(String idPermohonan, String noResit, HakmilikForm hakmilik) {		
		String sql = "";
		String noKPSimati = "";
		String BA_Simati = "";
		String BB_Simati = "";
		boolean pemilikHakmilik = false;
		
		Db db = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			//noKPSimati = getNoKPSimati(idPermohonan);
			
			if (hakmilik.getListPemilik1() != null) {
				PemilikForm[] pemilik = hakmilik.getListPemilik1();				
				for (int i = 0; i < pemilik.length; i++){
					pemilikHakmilik = true;

				} 
				
//				if (pemilikHakmilik) {
					sql = "INSERT INTO TBLINTMAKLUMATANAH (ID_PERMOHONAN, NO_RESIT, ID_HAKMILIK,"
							+ " ID_JENISHM, NO_HAKMILIK, NO_PT,"
							+ " ID_KATEGORI, ID_JENISPB, ID_NEGERI,"
							+ " ID_DAERAH, ID_MUKIM, ID_LUAS,"
							+ " LUAS, CATATAN, "
							+ " STATUS_PEMILIKAN, JENIS_TNH,"
							+ " SYARAT_NYATA, SEKATAN,"							
							+ " GADAIAN, NO_PERSERAHAN_GADAIAN,"
							+ " PAJAKAN, NO_PERSERAHAN_PAJAKAN,"
							+ " KAVEAT, NO_PERSERAHAN_KAVEAT,"							
							+ " TARIKH_TERIMA, FLAG_AKTIF, FLAG_TERIMA,TARIKH_MASUK)"
							+ " VALUES ( '" + idPermohonan + "', '" + noResit + "', '" + hakmilik.getIdHakmilik() + "',"
							+ " '" + IntegrasiManager.getIdJenisHakmilik(hakmilik.getIdJenisHakmilik()) + "', '" + hakmilik.getNoHakmilik() + "', '" + hakmilik.getNoPT()+ "',"
							+ " '" + hakmilik.getIdKategori() + "', '" + IntegrasiManager.cleanDataString(hakmilik.getIdJenisPB()) + "', '" + IntegrasiManager.getIdNegeri(hakmilik.getIdNegeri()) + "',"
							+ " '" + IntegrasiManager.getIdDaerah(hakmilik.getIdNegeri(), hakmilik.getIdDaerah()) + "', '" + IntegrasiManager.getIdMukim(hakmilik.getIdNegeri(), hakmilik.getIdDaerah(), hakmilik.getIdMukim()) + "', '" + hakmilik.getIdLuas() + "',"
							+ " '" + hakmilik.getLuas() + "', '" + IntegrasiManager.cleanDataString(hakmilik.getCatatan()) + "',"
							+ " '" + IntegrasiManager.cleanDataString(hakmilik.getStatusPemilikan()) + "', '" + IntegrasiManager.cleanDataString(hakmilik.getJenisTanah()) + "',"
							+ " '" + IntegrasiManager.cleanDataString(hakmilik.getSyaratNyata()) + "', '" + IntegrasiManager.cleanDataString(hakmilik.getSekatan()) + "',"
							+ " '" + IntegrasiManager.cleanDataString(hakmilik.getTANGGUNGAN()) + "', '" + IntegrasiManager.cleanDataString(hakmilik.getNO_PERSERAHAN_GADAIAN()) + "',"
							+ " '" + IntegrasiManager.cleanDataString(hakmilik.getPAJAKAN()) + "', '" + IntegrasiManager.cleanDataString(hakmilik.getNO_PERSERAHAN_PAJAKAN()) + "',"
							+ " '" + IntegrasiManager.cleanDataString(hakmilik.getKAVEAT()) + "', '" + IntegrasiManager.cleanDataString(hakmilik.getNO_PERSERAHAN_KAVEAT()) + "',"						
							+ " SYSDATE, 'Y', 'Y',SYSDATE)";
					stmt.executeUpdate(sql);
					con.commit();
					
					flagMsg = "Y";
					outputMsg = "CAPAIAN HAKMILIK BERJAYA";
//				} else {
//					flagMsg = "N";
//					outputMsg = "SIMATI BUKAN PEMILIK";
//				}
			} else {
				flagMsg = "N";
				outputMsg = "HAKMILIK TIDAK MEMPUNYAI PEMILIK";
			}					

		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
			
		} finally {
			if (db != null)
				db.close();
		}
		
	}
//	
	public static HakmilikForm carian(String noResit, String idHakmilik) throws RemoteException,MalformedURLException {
		HakmilikForm hf = null;
		flagMsg = "Y";
		outputMsg = "MAKLUMAT BERJAYA DICAPAI";

		try {			
			if(im.isUrlValid(url)) {
				objURL = new URL(url);
			}
			
			stub = new MyEtappPengambilanPortBindingStub(objURL,null);		
			stub.setUsername(userName);
			stub.setPassword(password);
		
			hf = stub.hakmilikDetailByCarianResit(noResit, idHakmilik);
			
			

//		EtappPesakaServiceStub stub = new EtappPesakaServiceStub(url);
//		GetHakmilikByCarianRasmi request = new GetHakmilikByCarianRasmi();
//		request.setNoResit(noResit);
//		request.setIdHakmilik(idHakmilik);
//
//		GetHakmilikByCarianRasmiE temp = new GetHakmilikByCarianRasmiE();
//		temp.setGetHakmilikByCarianRasmi(request);
//
//		GetHakmilikByCarianRasmiResponse response = stub.getHakmilikByCarianRasmi(temp).getGetHakmilikByCarianRasmiResponse();
//		Hakmilik hakmilik = response.get_return();
		} catch (Exception e) {
			//e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}
		return hf;
			
	}
	
	public static String getEventName() {
		return eventName;
	}

	public static void setEventName(String eventName) {
		ETanahCarianManager.eventName = eventName;
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
