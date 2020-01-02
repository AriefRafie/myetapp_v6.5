/**
 * 
 */
package integrasi.ws.etanah.melaka_ns.ppk;

import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.BorangE;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.BorangF;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.BorangH;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmi;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmiE;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.GetHakmilikByCarianRasmiResponse;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.Hakmilik;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.HakmilikPerintah;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.InsertMaklumatPerintah;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.InsertMaklumatPerintahE;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.Pemilik;
import integrasi.ws.etanah.melaka_ns.ppk.EtappPesakaServiceStub.Perintah;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import lebah.db.Db;
import ekptg.helpers.Utils;

/**
 * @author mohd faizal
 * 
 */
public class EtanahN9MelPPKManager {
	
	private static String urlMelaka = "http://etanah.melaka.gov.my/etanahwsa/EtappPesakaService?wsdl";
//	private static String urlNSembilan = "http://etanah.n9.gov.my/etanahwsa/EtappPesakaService?wsdl";
	private static String urlNSembilan = "http://218.208.26.234/etanahwsa/EtappPesakaService?WSDL";
	
	private static String flagMsg = null;
	private static String outputMsg = null;

	
	//START 1ST POINT OF INTERGRATION - GET MAKLUMAT HAKMILIK FROM ETANAH
	public static void getMaklumatHakmilikFromEtanah(String idPermohonanSimati, String noResit, String idHakmilik, String kodNegeri) {
		
		//DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "CAPAIAN HAKMILIK BERJAYA";
		
		Db db = null;

		try {

			db = new Db();			

			Hakmilik hakmilik = null;
			if ("04".equals(kodNegeri)){
				hakmilik = getMaklumatHakmilik(noResit, idHakmilik, idPermohonanSimati, urlMelaka);
			} else if ("05".equals(kodNegeri)){
				hakmilik = getMaklumatHakmilik(noResit, idHakmilik, idPermohonanSimati, urlNSembilan);
			} 

			if (hakmilik != null && hakmilik.getIdHakmilik() != null) {
				insertHakmilikFromEtanah(idPermohonanSimati, noResit, hakmilik);
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
	
	private static void insertHakmilikFromEtanah(String idPermohonanSimati, String noResit, Hakmilik hakmilik) {
		
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
			
			noKPSimati = getNoKPSimati(idPermohonanSimati);
			
			if (hakmilik.getListPemilik1() != null) {
				Pemilik[] pemilik = hakmilik.getListPemilik1();				
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
					sql = "INSERT INTO INT_PPKHTA (ID_PERMOHONANSIMATI, NO_RESIT, ID_HAKMILIK,"
							+ " ID_JENISHM, NO_HAKMILIK, NO_PT,"
							+ " ID_KATEGORI, ID_JENISPB, ID_NEGERI,"
							+ " ID_DAERAH, ID_MUKIM, ID_LUAS,"
							+ " LUAS, CATATAN, BA_SIMATI, BB_SIMATI, "
							+ " STATUS_PEMILIKAN, JENIS_TNH,"
							+ " SYARAT_NYATA, SEKATAN,"							
							+ " GADAIAN, NO_PERSERAHAN_GADAIAN,"
							+ " PAJAKAN, NO_PERSERAHAN_PAJAKAN,"
							+ " KAVEAT, NO_PERSERAHAN_KAVEAT,"							
							+ " TARIKH_TERIMA, FLAG_AKTIF, FLAG_TERIMA)"
							+ " VALUES ( '" + idPermohonanSimati + "', '" + noResit + "', '" + hakmilik.getIdHakmilik() + "',"
							+ " '" + getIdJenisHakmilik(hakmilik.getIdJenisHakmilik()) + "', '" + hakmilik.getNoHakmilik() + "', '" + hakmilik.getNoPT()+ "',"
							+ " '" + hakmilik.getIdKategori() + "', '" + cleanDataString(hakmilik.getIdJenisPB()) + "', '" + getIdNegeri(hakmilik.getIdNegeri()) + "',"
							+ " '" + getIdDaerah(hakmilik.getIdNegeri(), hakmilik.getIdDaerah()) + "', '" + getIdMukim(hakmilik.getIdNegeri(), hakmilik.getIdDaerah(), hakmilik.getIdMukim()) + "', '" + hakmilik.getIdLuas() + "',"
							+ " '" + hakmilik.getLuas() + "', '" + cleanDataString(hakmilik.getCatatan()) + "',"
							+ " '" + BA_Simati + "', '" + BB_Simati + "',"
							+ " '" + cleanDataString(hakmilik.getStatusPemilikan()) + "', '" + cleanDataString(hakmilik.getJenisTanah()) + "',"
							+ " '" + cleanDataString(hakmilik.getSyaratNyata()) + "', '" + cleanDataString(hakmilik.getSekatan()) + "',"
							
							+ " '" + cleanDataString(hakmilik.getTANGGUNGAN()) + "', '" + cleanDataString(hakmilik.getNO_PERSERAHAN_GADAIAN()) + "',"
							+ " '" + cleanDataString(hakmilik.getPAJAKAN()) + "', '" + cleanDataString(hakmilik.getNO_PERSERAHAN_PAJAKAN()) + "',"
							+ " '" + cleanDataString(hakmilik.getKAVEAT()) + "', '" + cleanDataString(hakmilik.getNO_PERSERAHAN_KAVEAT()) + "',"
							
							+ " SYSDATE, 'Y', 'Y')";
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
	
	private static String getIdJenisHakmilik(String kodJenisHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_JENISHAKMILIK FROM TBLRUJJENISHAKMILIK WHERE KOD_JENIS_HAKMILIK = '" + kodJenisHakmilik + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_JENISHAKMILIK");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static String getIdNegeri(String kodNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_NEGERI FROM TBLRUJNEGERI WHERE KOD_NEGERI = '" + kodNegeri + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_NEGERI");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static String getIdDaerah(String kodNegeri, String kodDaerah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE ID_NEGERI = '" + getIdNegeri(kodNegeri) + "' AND KOD_DAERAH = '" + kodDaerah + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_DAERAH");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static String getIdMukim(String kodNegeri, String kodDaerah, String kodMukim) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_MUKIM FROM TBLRUJMUKIM WHERE ID_DAERAH = '" + getIdDaerah(kodNegeri, kodDaerah) + "' AND KOD_MUKIM = '" + kodMukim + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_MUKIM");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//END 1ST POINT OF INTERGRATION - GET MAKLUMAT HAKMILIK FROM ETANAH
	
	//START 2ND POINT OF INTERGRATION - SEND MAKLUMAT PERINTAH TO ETANAH
	public static void sendPerintahToEtanah(String noFail) throws Exception {
		//DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "MAKLUMAT PERINTAH BERJAYA DIHANTAR";
		
		boolean flagHakmilikMelaka = false;
		boolean flagHakmilikNSembilan = false;
		
		String sql = "";
		Db db = null;
		String kodNegeri = null;

		try {

			db = new Db();			
			Statement stmt = db.getStatement();
			
			sql = "SELECT H.NEGERI FROM INT_PPKPERINTAH P, INT_PPKHAKMILIKPERINTAH H WHERE P.ID_PPKPERINTAH = H.ID_PPKPERINTAH AND P.NO_FAIL = '" + noFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				if (rs.getString("NEGERI") != null && rs.getString("NEGERI").length() > 0){
					kodNegeri = rs.getString("NEGERI");
					if ("04".equals(kodNegeri)){
						flagHakmilikMelaka = true;
					} else if ("05".equals(kodNegeri)){
						flagHakmilikNSembilan = true;
					}
				}
			}
			
			if (flagHakmilikMelaka){
				Perintah perintahMelaka = null;
				perintahMelaka = preparePerintahForEtanah(noFail, "04");
				if (perintahMelaka != null)
					insertMaklumatPerintah(perintahMelaka, urlMelaka);
			}
			if (flagHakmilikNSembilan){
				Perintah perintahNSembilan = null;
				perintahNSembilan = preparePerintahForEtanah(noFail, "05");
				if (perintahNSembilan != null)
					insertMaklumatPerintah(perintahNSembilan, urlNSembilan);
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
	
	private static Perintah preparePerintahForEtanah(String noFail, String kodNegeri) {
		String sql = "";
		Db db = null;
		Perintah perintah = null;

		try {

			db = new Db();			
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM INT_PPKPERINTAH WHERE NO_FAIL = '" + noFail + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				 perintah = new Perintah();
				 
				 perintah.setNoFail(rs.getString("NO_FAIL"));
				 perintah.setNamaSimati(rs.getString("NAMA_SIMATI"));
				 perintah.setNoKPSimati(rs.getString("NO_KPSIMATI"));
				 perintah.setTarikhMati(rs.getString("TARIKH_MATI"));
				 perintah.setNoSijilMati(rs.getString("NO_SIJILMATI"));
				 perintah.setNamaPemohon(rs.getString("NAMA_PEMOHON"));
				 perintah.setNoKPPemohon(rs.getString("NO_KPPEMOHON"));
				 perintah.setAlamatPemohon1(rs.getString("ALAMAT_PEMOHON1"));
				 perintah.setAlamatPemohon2(rs.getString("ALAMAT_PEMOHON2"));
				 perintah.setAlamatPemohon3(rs.getString("ALAMAT_PEMOHON3"));
				 perintah.setPoskodPemohon(rs.getString("POSKOD_PEMOHON"));
				 perintah.setTempatBicara(rs.getString("TEMPAT_BICARA"));				 
				 perintah.setAlamatBicara1(rs.getString("ALAMAT_BICARA1"));
				 perintah.setAlamatBicara2(rs.getString("ALAMAT_BICARA2"));
				 perintah.setAlamatBicara3(rs.getString("ALAMAT_BICARA3"));
				 perintah.setPoskodBicara(rs.getString("POSKOD_BICARA"));				 
				 perintah.setBandarBicara(rs.getString("BANDAR_BICARA"));
				 perintah.setNegeriBicara(rs.getString("NEGERI_BICARA"));
				 perintah.setPegawaiBicara(rs.getString("PEGAWAI_BICARA"));
				 perintah.setTarikhPerintah(rs.getString("TARIKH_PERINTAH"));	
				 
				 sql = "SELECT * FROM INT_PPKHAKMILIKPERINTAH WHERE ID_PPKPERINTAH = '" + rs.getString("ID_PPKPERINTAH") + "' AND NEGERI = '" + kodNegeri + "'";
				 ResultSet rsHakmilik = stmt.executeQuery(sql);
				 while (rsHakmilik.next()) {
					 perintah.addListHakmilik(getHakmilikPerintah(rsHakmilik.getString("ID_PPKHAKMILIKPERINTAH")));						 
				 }				 
			}			

		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}
		return perintah;
	}	

	private static HakmilikPerintah getHakmilikPerintah(String idHakmilikPerintah) {
		String sql = "";
		Db db = null;
		HakmilikPerintah hakmilikPerintah = null;
		String idPPKHakmilikPerintah = "";
		
		try {

			db = new Db();			
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM INT_PPKHAKMILIKPERINTAH WHERE ID_PPKHAKMILIKPERINTAH = '" + idHakmilikPerintah + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				hakmilikPerintah = new HakmilikPerintah();
				hakmilikPerintah.setIdHakmilik(rs.getString("ID_HAKMILIK"));				
				hakmilikPerintah.setIdJenisHakmilik(rs.getString("ID_JENISHAKMILIK"));
				hakmilikPerintah.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				hakmilikPerintah.setIdLuas(rs.getString("ID_LUAS"));
				hakmilikPerintah.setLuas(rs.getString("LUAS"));
				hakmilikPerintah.setIdNegeri(rs.getString("NEGERI"));
				hakmilikPerintah.setIdDaerah(rs.getString("DAERAH"));
				hakmilikPerintah.setIdMukim(rs.getString("MUKIM"));
				hakmilikPerintah.setNoLot(rs.getString("NO_LOT"));
				hakmilikPerintah.setBASimati(rs.getString("BA_SIMATI"));
				hakmilikPerintah.setBBSimati(rs.getString("BB_SIMATI"));
				hakmilikPerintah.setJenisPembahagian(rs.getString("JENIS_PEMBAHAGIAN"));
				
				idPPKHakmilikPerintah = rs.getString("ID_PPKHAKMILIKPERINTAH");
				
				//BORANG E
				sql = "SELECT * FROM INT_PPKBORANGE WHERE ID_PPKHAKMILIKPERINTAH = '" + idPPKHakmilikPerintah + "'";
				 ResultSet rsBorangE = stmt.executeQuery(sql);
				 while (rsBorangE.next()) {
					 hakmilikPerintah.addListBorangE(getBorangE(rsBorangE.getString("ID_PPKBORANGE")));					 
				 }
				 
				//BORANG F
				sql = "SELECT * FROM INT_PPKBORANGF WHERE ID_PPKHAKMILIKPERINTAH = '" + idPPKHakmilikPerintah + "'";
				 ResultSet rsBorangF = stmt.executeQuery(sql);
				 while (rsBorangF.next()) {
					 hakmilikPerintah.addListBorangF(getBorangF(rsBorangF.getString("ID_PPKBORANGF")));					 
				 }
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}
		
		return hakmilikPerintah;
	}	
	
	private static BorangE getBorangE(String idBorangE) {
		String sql = "";
		Db db = null;
		BorangE borangE = null;
		
		try {

			db = new Db();			
			Statement stmt = db.getStatement();
			
			sql = "SELECT * FROM INT_PPKBORANGE WHERE ID_PPKBORANGE  = '" + idBorangE + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				borangE = new BorangE();
				borangE.setNamaOB(rs.getString("NAMA_OB"));
				borangE.setJenisPengenalan(rs.getString("JENIS_PENGENALAN"));
				borangE.setNoKPOB(rs.getString("NO_KPOB"));
				
				borangE.setStatusOB(rs.getString("STATUS_OB"));
				borangE.setBA(rs.getString("BA_WARIS"));
				borangE.setBB(rs.getString("BB_WARIS"));
				
				borangE.setAlamat1(rs.getString("ALAMAT1"));
				borangE.setAlamat2(rs.getString("ALAMAT2"));
				borangE.setAlamat3(rs.getString("ALAMAT3"));
				borangE.setPoskod(rs.getString("POSKOD"));
				borangE.setBandar(rs.getString("BANDAR"));
				borangE.setNegeri(rs.getString("NEGERI"));
				
				borangE.setKewarganegaraan(rs.getString("WARGANEGARA"));
				borangE.setUmur(rs.getString("UMUR"));
				borangE.setTarikhLahir(rs.getString("TARIKH_LAHIR"));
				borangE.setJantina(rs.getString("JANTINA"));
				borangE.setHubungan(rs.getString("ID_SAUDARA"));
				
				//BORANG H
				sql = "SELECT * FROM INT_PPKBORANGH WHERE ID_PPKBORANGE = '" + rs.getString("ID_PPKBORANGE") + "'";
				 ResultSet rsBorangH = stmt.executeQuery(sql);
				 while (rsBorangH.next()) {
					 borangE.addListBorangH(getBorangH(rsBorangH.getString("ID_PPKBORANGH")));	
				 }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}
		
		return borangE;
	}
	
	private static BorangH getBorangH(String idBorangH) {
		String sql = "";
		Db db = null;
		BorangH borangH = null;
		
		try {

			db = new Db();			
			Statement stmt = db.getStatement();
			
			sql = "SELECT * FROM INT_PPKBORANGH WHERE ID_PPKBORANGH  = '" + idBorangH + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				borangH = new BorangH();
				borangH.setNamaOB(rs.getString("NAMA_OB"));
				borangH.setJenisPengenalan(rs.getString("JENIS_PENGENALAN"));
				borangH.setNoKPOB(rs.getString("NO_KPOB"));
				
				borangH.setAlamat1(rs.getString("ALAMAT1"));
				borangH.setAlamat2(rs.getString("ALAMAT2"));
				borangH.setAlamat3(rs.getString("ALAMAT3"));
				borangH.setPoskod(rs.getString("POSKOD"));
				borangH.setBandar(rs.getString("BANDAR"));
				borangH.setNegeri(rs.getString("NEGERI"));
				
				borangH.setKewarganegaraan(rs.getString("WARGANEGARA"));
				borangH.setUmur(rs.getString("UMUR"));
				borangH.setTarikhLahir(rs.getString("TARIKH_LAHIR"));
				borangH.setJantina(rs.getString("JANTINA"));
				borangH.setHubungan(rs.getString("ID_SAUDARA"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}
		
		return borangH;
	}

	private static BorangF getBorangF(String idBorangF) {
		String sql = "";
		Db db = null;
		BorangF borangF = null;
		
		try {

			db = new Db();			
			Statement stmt = db.getStatement();
			
			sql = "SELECT * FROM INT_PPKBORANGF WHERE ID_PPKBORANGF  = '" + idBorangF + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				borangF = new BorangF();
				borangF.setNamaOB(rs.getString("NAMA_OB"));
				borangF.setJenisPengenalan(rs.getString("JENIS_PENGENALAN"));
				borangF.setNoKPOB(rs.getString("NO_KPOB"));
				
				borangF.setAlamat1(rs.getString("ALAMAT1"));
				borangF.setAlamat2(rs.getString("ALAMAT2"));
				borangF.setAlamat3(rs.getString("ALAMAT3"));
				borangF.setPoskod(rs.getString("POSKOD"));
				borangF.setBandar(rs.getString("BANDAR"));
				borangF.setNegeri(rs.getString("NEGERI"));
				
				borangF.setKewarganegaraan(rs.getString("WARGANEGARA"));
				borangF.setUmur(rs.getString("UMUR"));
				borangF.setTarikhLahir(rs.getString("TARIKH_LAHIR"));
				borangF.setJantina(rs.getString("JANTINA"));
				borangF.setHubungan(rs.getString("ID_SAUDARA"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}
		
		return borangF;
	}
	
	public XMLGregorianCalendar setXMLGregorianCalendar(Date yourDate) throws DatatypeConfigurationException {
		GregorianCalendar c = new GregorianCalendar();
	    c.setTime(yourDate);
	    XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
	    return date2;
	}
	//END 2ND POINT OF INTERGRATION - SEND MAKLUMAT PERINTAH TO ETANAH

	// START ETANAH WEBSERVICES
	private static Hakmilik getMaklumatHakmilik(String noResit,
			String idHakmilik, String idPermohonanSimati, String url) {

		EtappPesakaServiceStub stub;
		Hakmilik hakmilik = new Hakmilik();

		try {
			stub = new EtappPesakaServiceStub(url);

			GetHakmilikByCarianRasmi request = new GetHakmilikByCarianRasmi();
			request.setNoResit(noResit);
			request.setIdHakmilik(idHakmilik);

			GetHakmilikByCarianRasmiE temp = new GetHakmilikByCarianRasmiE();
			temp.setGetHakmilikByCarianRasmi(request);

			GetHakmilikByCarianRasmiResponse response = stub
					.getHakmilikByCarianRasmi(temp)
					.getGetHakmilikByCarianRasmiResponse();
			hakmilik = response.get_return();

		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}
		return hakmilik;
	}

	private static void insertMaklumatPerintah(Perintah perintah, String url) throws Exception{
		
		EtappPesakaServiceStub stub;
	
		try {
			stub = new EtappPesakaServiceStub(url);

			InsertMaklumatPerintah request = new InsertMaklumatPerintah();
			request.setPerintah(perintah);

			InsertMaklumatPerintahE temp = new InsertMaklumatPerintahE();
			temp.setInsertMaklumatPerintah(request);
			
			stub.insertMaklumatPerintah(temp);

		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}
	}
	// END ETANAH WEBSERVICES
	
	private static String cleanDataString(String input) {
		String temp = input;
		if (input == null) {
			temp = "";
		} else if ("null".equalsIgnoreCase(input.trim())) {
			temp = input.trim().replaceAll("null", "");
			temp = temp.toUpperCase();
		} else {
			temp = input.trim().toUpperCase();
		}
		return temp;
	}

	public static String getFlagMsg() {
		return flagMsg;
	}

	public static void setFlagMsg(String flagMsg) {
		EtanahN9MelPPKManager.flagMsg = flagMsg;
	}

	public static String getOutputMsg() {
		return outputMsg;
	}

	public static void setOutputMsg(String outputMsg) {
		EtanahN9MelPPKManager.outputMsg = outputMsg;
	}

}
