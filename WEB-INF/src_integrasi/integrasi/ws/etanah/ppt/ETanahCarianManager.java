package integrasi.ws.etanah.ppt;

import integrasi.IntegrasiManager;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.Hakmilik;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.Pemilik;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikDetailByCarianResit;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikDetailByCarianResitE;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikDetailByCarianResitResponse;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikForm;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.PemilikForm;
import lebah.db.Db;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.client.Options;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.log4j.Logger;

import ekptg.helpers.Utils;


public class ETanahCarianManager {
	
	static Logger myLog = Logger.getLogger(integrasi.ws.etanah.ppt.ETanahCarianManager.class);	
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
	private static MyEtappPengambilanServiceStub stub = null;
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
	public static void getMaklumatHakmilikFromEtanah(String noResit
		, String idHakmilik,String idPermohonan,String kodNegeri) {			
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
			myLog.info("mt="+mt);
//			Hakmilik hakmilik = null;
//			if ("04".equals(kodNegeri)){
//				hakmilik = getMaklumatHakmilik(noResit, idHakmilik, idPermohonanSimati, urlMelaka);
//			} else if ("05".equals(kodNegeri)){
//				hakmilik = getMaklumatHakmilik(noResit, idHakmilik, idPermohonanSimati, urlNSembilan);
//			} 

			if (mt != null && mt.getIdHakmilik() != null) {
				insertHakmilikFromeTanah(idPermohonan, noResit, mt);
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
	//START 1ST POINT OF INTERGRATION - GET MAKLUMAT HAKMILIK FROM ETANAH
	public static void getMaklumatHakmilikeTanahHTP(String noResit, String idHakmilik
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
				insertHakmilikFromeTanah(idPermohonan, noResit, mt);
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
	//START 1ST POINT OF INTERGRATION - GET MAKLUMAT HAKMILIK FROM ETANAH
	public static void getMaklumatHakmilikeTanahPPK(String idPermohonanSimati, String noResit, String idHakmilik, String kodNegeri) {
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
			myLog.info("mt="+mt);

			if (mt != null && mt.getIdHakmilik() != null) {
				insertHakmilikPPK(idPermohonanSimati, noResit, mt);
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
	private static void insertHakmilikPPK(String idPermohonan, String noResit, HakmilikForm hakmilik) {		
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
			
			noKPSimati = getNoKPSimati(idPermohonan);
			
			if (hakmilik.getIdHakmilik() != null) {
				PemilikForm[] pemilik = hakmilik.getListPemilik();				
				for (int i = 0; i < pemilik.length; i++){
					if (noKPSimati != null){
						if (noKPSimati.equalsIgnoreCase(Utils.RemoveDash(pemilik[i].getNoPengenalan()))) {
							pemilikHakmilik = true;
							BA_Simati = pemilik[i].getBA();
							BB_Simati = pemilik[i].getBB();
						
						}
					}

				} 
								
				if (pemilikHakmilik) {
					sql = "INSERT INTO TBLINTMAKLUMATANAH (ID_PERMOHONAN, NO_RESIT, ID_HAKMILIK,"
							+ " ID_JENISHM, NO_HAKMILIK, NO_PT,"
							+ " ID_KATEGORI"
							+ ", ID_NEGERI,"
							+ " ID_DAERAH, ID_MUKIM, ID_LUAS,"
							+ " LUAS, CATATAN, "
							+ " STATUS_PEMILIKAN, JENIS_TANAH,"
							+ " SYARAT_NYATA, SEKATAN"							
//							+ " ,GADAIAN"
							+ ", NO_PERSERAHAN_GADAIAN,"
							+ " PAJAKAN, NO_PERSERAHAN_PAJAKAN,"
							+ " KAVEAT, NO_PERSERAHAN_KAVEAT,"							
							+ " TARIKH_TERIMA, FLAG_AKTIF, FLAG_TERIMA,TARIKH_MASUK"
							+ " ,BA_SIMATI, BB_SIMATI "
							+ ")"
							+ " VALUES ( '" + idPermohonan + "', '" + noResit + "', '" + hakmilik.getIdHakmilik() + "',"
							+ " '" + IntegrasiManager.getIdJenisHakmilik(hakmilik.getIdJenisHakmilik()) + "', '" + hakmilik.getNoHakmilik() + "', '" + hakmilik.getNoPT()+ "',"
							+ " '" + hakmilik.getIdKategori() + "'"
									+ ", '" + IntegrasiManager.getIdNegeri(hakmilik.getIdNegeri()) + "',"
							+ " '" + IntegrasiManager.getIdDaerah(hakmilik.getIdNegeri(), hakmilik.getIdDaerah()) + "', '" + IntegrasiManager.getIdMukim(hakmilik.getIdNegeri(), hakmilik.getIdDaerah(), hakmilik.getIdMukim()) + "', '" + hakmilik.getIdLuas() + "',"
							+ " '" + hakmilik.getLuas() + "', '" + IntegrasiManager.cleanDataString(hakmilik.getCatatan()) + "',"
							+ " '" + IntegrasiManager.cleanDataString(hakmilik.getStatusPemilikan()) + "', '" + IntegrasiManager.cleanDataString(hakmilik.getJenisTanah()) + "',"
							+ " '" + IntegrasiManager.cleanDataString(hakmilik.getSyaratNyata()) + "', '" + IntegrasiManager.cleanDataString(hakmilik.getSekatan()) 
//							+ "',"+ " '" + IntegrasiManager.cleanDataString(hakmilik.getTANGGUNGAN()) 
							+ "', '" + IntegrasiManager.cleanDataString(hakmilik.getNO_PERSERAHAN_GADAIAN()) + "',"
							+ " '" + IntegrasiManager.cleanDataString(hakmilik.getPAJAKAN()) + "', '" + IntegrasiManager.cleanDataString(hakmilik.getNO_PERSERAHAN_PAJAKAN()) + "',"
							+ " '" + IntegrasiManager.cleanDataString(hakmilik.getKAVEAT()) + "', '" + IntegrasiManager.cleanDataString(hakmilik.getNO_PERSERAHAN_KAVEAT()) + "',"						
							+ " SYSDATE, 'Y', 'Y',SYSDATE"
							+ " ,'" + BA_Simati + "', '" + BB_Simati + "'"
							+ ")";
					stmt.executeUpdate(sql);
					con.commit();
					
					flagMsg = "Y";
					outputMsg = "CAPAIAN HAKMILIK BERJAYA";
					
				} else {
					flagMsg = "N";
					outputMsg = "SIMATI BUKAN PEMILIK";
				}
				
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
	
	private static void insertHakmilikFromeTanah(String idPermohonan, String noResit, HakmilikForm hakmilik) {		
		String sql = "";
//		String noKPSimati = "";
//		String BA_Simati = "";
//		String BB_Simati = "";
		boolean pemilikHakmilik = false;
		
		Db db = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			//noKPSimati = getNoKPSimati(idPermohonan);
			
			if (hakmilik.getListPemilik() != null) {
				PemilikForm[] pemilik = hakmilik.getListPemilik();				
				for (int i = 0; i < pemilik.length; i++){
					PemilikForm pf = pemilik[i];
					pemilikHakmilik = true;
					sql = "INSERT INTO TBLINTMAKLUMATANAHOB ("
//							+ "ID_HAKMILIK,NAMA_OB,JENIS_KP,NO_KP_BARU,NO_KP_LAMA,NO_KP_LAIN,BA,BB,TARIKH_MASUK"
						+ "ID_HAKMILIK,NAMA_OB,JENIS_KP";
						if(pf.getIdJenisPengenalan()!= null)							
							sql += ",NO_KP_BARU";
						else
							sql += ",NO_KP_LAMA";

						sql += ",BA,BB,JENIS_PB,TARIKH_MASUK"
						+ ") VALUES ("
						+ "'" + hakmilik.getIdHakmilik() + "'"
						+ ",'"+pf.getNamaPemilik()+"'"
						+ ",'"+pf.getIdJenisPengenalan()+"'"
						+ ",'"+pf.getNoPengenalan()+"'"
						+ ",'"+pf.getBA()+"'"
						+ ",'"+pf.getBB()+"'"
						+ ",'"+pf.getJenisPB()+"',SYSDATE"
						+ ")"
						+ "";
					stmt.executeUpdate(sql);
				} 
				
//				if (pemilikHakmilik) {
					sql = "INSERT INTO TBLINTMAKLUMATANAH (ID_PERMOHONAN, NO_RESIT, ID_HAKMILIK,"
							+ " ID_JENISHM, NO_HAKMILIK, NO_PT,"
							+ " ID_KATEGORI"
							+ ", ID_NEGERI,"
							+ " ID_DAERAH, ID_MUKIM, ID_LUAS,"
							+ " LUAS, CATATAN, "
							+ " STATUS_PEMILIKAN, JENIS_TANAH,"
							+ " SYARAT_NYATA, SEKATAN"							
//							+ " ,GADAIAN"
							+ ", NO_PERSERAHAN_GADAIAN,"
							+ " PAJAKAN, NO_PERSERAHAN_PAJAKAN,"
							+ " KAVEAT, NO_PERSERAHAN_KAVEAT,"							
							+ " TARIKH_TERIMA, FLAG_AKTIF, FLAG_TERIMA,TARIKH_MASUK)"
							+ " VALUES ( '" + idPermohonan + "', '" + noResit + "', '" + hakmilik.getIdHakmilik() + "',"
							+ " '" + IntegrasiManager.getIdJenisHakmilik(hakmilik.getIdJenisHakmilik()) + "', '" + hakmilik.getNoHakmilik() + "', '" + hakmilik.getNoPT()+ "',"
							+ " '" + hakmilik.getIdKategori() + "'"
									+ ", '" + IntegrasiManager.getIdNegeri(hakmilik.getIdNegeri()) + "',"
							+ " '" + IntegrasiManager.getIdDaerah(hakmilik.getIdNegeri(), hakmilik.getIdDaerah()) + "', '" + IntegrasiManager.getIdMukim(hakmilik.getIdNegeri(), hakmilik.getIdDaerah(), hakmilik.getIdMukim()) + "', '" + hakmilik.getIdLuas() + "',"
							+ " '" + hakmilik.getLuas() + "', '" + IntegrasiManager.cleanDataString(hakmilik.getCatatan()) + "',"
							+ " '" + IntegrasiManager.cleanDataString(hakmilik.getStatusPemilikan()) + "', '" + IntegrasiManager.cleanDataString(hakmilik.getJenisTanah()) + "',"
							+ " '" + IntegrasiManager.cleanDataString(hakmilik.getSyaratNyata()) + "', '" + IntegrasiManager.cleanDataString(hakmilik.getSekatan()) 
//							+ "',"+ " '" + IntegrasiManager.cleanDataString(hakmilik.getTANGGUNGAN()) 
							+ "', '" + IntegrasiManager.cleanDataString(hakmilik.getNO_PERSERAHAN_GADAIAN()) + "',"
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
	
	private static String getNoKPSimati(String idPermohonanSimati) throws Exception {
		Db db = null;
		String sql = "";
		String noKP = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT SM.NO_KP_BARU, SM.NO_KP_LAMA, SM.NO_KP_LAIN FROM TBLPPKPERMOHONANSIMATI PSM, TBLPPKSIMATI SM WHERE PSM.ID_SIMATI = SM.ID_SIMATI AND PSM.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				if (rs.getString("NO_KP_BARU") != null && rs.getString("NO_KP_BARU").trim().length() > 0) {
					noKP = rs.getString("NO_KP_BARU");
				} else if (rs.getString("NO_KP_LAMA") != null && rs.getString("NO_KP_LAMA").trim().length() > 0) {
					noKP = rs.getString("NO_KP_LAMA");
				} else if (rs.getString("NO_KP_LAIN") != null && rs.getString("NO_KP_LAIN").trim().length() > 0) {
					noKP = rs.getString("NO_KP_LAIN");
				}
			} 
			
		} finally {
			if (db != null)
				db.close();
		}
		return noKP;
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
