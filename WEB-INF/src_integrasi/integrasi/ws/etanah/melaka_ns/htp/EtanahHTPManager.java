package integrasi.ws.etanah.melaka_ns.htp;

import integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.HakmilikAgensi;
import integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.MaklumatBayaran;
import integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.MaklumatBayaranByHakmilik;
import integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.MaklumatBayaranByHakmilikE;
import integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.MaklumatBayaranByHakmilikResponse;
import integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.MaklumatCukai;
import integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.MaklumatCukaiByHakmilik;
import integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.MaklumatCukaiByHakmilikE;
import integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.MaklumatCukaiByHakmilikResponse;
import integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.UpdateHakmilikAgensi;
import integrasi.ws.etanah.melaka_ns.htp.EtappHasilPersekutuanServiceStub.UpdateHakmilikAgensiE;

import java.sql.Connection;
import java.sql.Statement;

import lebah.db.Db;

public class EtanahHTPManager {

	private static String urlMelaka = "http://etanah.melaka.gov.my/etanahwsa/EtappHasilPersekutuanService?wsdl";
//	private static String urlNSembilan = "http://etanah.n9.gov.my/etanahwsa/EtappHasilPersekutuanService?wsdl";
	private static String urlNSembilan = "http://218.208.26.234/etanahwsa/EtappHasilPersekutuanService?wsdl";
	

	private static String flagMsg = null;
	private static String outputMsg = null;

	// START 1ST POINT OF INTERGRATION - UPDATE MAKLUMAT AGENSI KE ETANAH
	public static void updateMaklumatAgensi(String idHakmilik,
			String kodKementerian, String namaKementerian, String kodAgensi,
			String namaAgensi, String kegunaanTanah, String kodNegeri)
			throws Exception {

		// DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "MAKLUMAT BERJAYA DIHANTAR";

		HakmilikAgensi hakmilik = new HakmilikAgensi();
		hakmilik.setIdHakmilik(idHakmilik);
		hakmilik.setKodKementerian(kodKementerian);
		hakmilik.setNamaKementerian(namaKementerian);
		hakmilik.setKodAgensi(kodAgensi);
		hakmilik.setNamaAgensi(namaAgensi);
		hakmilik.setKegunaanTanah(kegunaanTanah);

		if ("04".equals(kodNegeri)) {
			updateHakmilikAgensi(hakmilik, urlMelaka);
		} else if ("05".equals(kodNegeri)) {
			updateHakmilikAgensi(hakmilik, urlNSembilan);
		}
	}

	// END 1ST POINT OF INTERGRATION - UPDATE MAKLUMAT AGENSI KE ETANAH

	// START 2ND POINT OF INTERGRATION - GET MAKLUMAT CUKAI FROM ETANAH
	public static void getMaklumatCukai(String idHakmilik, String tahun,
			String kodNegeri) throws Exception {

		// DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "MAKLUMAT CUKAI BERJAYA DICAPAI";

		Db db = null;

		try {

			db = new Db();

			MaklumatCukai cukai = null;
			if ("04".equals(kodNegeri)) {
				cukai = getMaklumatCukaiByHakmilik(idHakmilik, tahun, urlMelaka);
			} else if ("05".equals(kodNegeri)) {
				cukai = getMaklumatCukaiByHakmilik(idHakmilik, tahun,
						urlNSembilan);
			}

			if (cukai != null) {
				insertCukaiFromEtanah(idHakmilik, tahun, cukai, kodNegeri);
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

	private static void insertCukaiFromEtanah(String idHakmilik, String tahun,
			MaklumatCukai cukai, String kodNegeri) {

		String sql = "";
		Db db = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// DELETE BAYARAN
			sql = "DELETE FROM INT_HTPMAKLUMATBAYARAN WHERE ID_HAKMILIK = '"
					+ idHakmilik + "' AND TAHUN = '" + tahun + "'";
			stmt.executeUpdate(sql);

			// DELETE CUKAI
			sql = "DELETE FROM INT_HTPMAKLUMATCUKAI WHERE ID_HAKMILIK = '"
					+ idHakmilik + "' AND TAHUN = '" + tahun + "'";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO INT_HTPMAKLUMATCUKAI (ID_HAKMILIK, TAHUN, CUKAI_TANAH,"
					+ " TUNGGAKAN_CUKAI_TANAH, CUKAI_TALI_AIR, TUNGGAKAN_CUKAI_TALI_AIR,"
					+ " DENDA_LEWAT_SEMASA, TUNGGAKAN_DENDA_LEWAT, NOTIS_6A,"
					+ " REMISYEN)" + " VALUES ('"
					+ idHakmilik
					+ "', '"
					+ tahun
					+ "', '"
					+ cleanDataCurrency(cukai.getCukai_tanah())
					+ "',"
					+ " '"
					+ cleanDataCurrency(cukai.getTunggakan_cukai_tanah())
					+ "', '"
					+ cleanDataCurrency(cukai.getCukai_tali_air())
					+ "', '"
					+ cleanDataCurrency(cukai.getTunggakan_tali_air())
					+ "',"
					+ " '"
					+ cleanDataCurrency(cukai.getDenda_lewat_semasa())
					+ "', '"
					+ cleanDataCurrency(cukai.getTunggakan_denda_lewat())
					+ "', '"
					+ cleanDataCurrency(cukai.getNotis6A())
					+ "',"
					+ " '" + cleanDataCurrency(cukai.getRemisyen()) + "')";
			stmt.executeUpdate(sql);
			con.commit();

			updateJumlahCukai(idHakmilik, tahun);
			updateJumlahBayaran(idHakmilik, tahun);
			updateBakiCukai(idHakmilik, tahun);

			// getMaklumatBayaranFromCukai(idHakmilik, tahun, kodNegeri);

		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}

	}

	public static void getMaklumatBayaranFromCukai(String idHakmilik,
			String tahun, String kodNegeri) throws Exception {

		Db db = null;

		try {

			db = new Db();

			MaklumatBayaran bayaran[] = null;
			if ("04".equals(kodNegeri)) {
				bayaran = getMaklumatBayaranByHakmilik(idHakmilik, tahun,
						urlMelaka);
			} else if ("05".equals(kodNegeri)) {
				bayaran = getMaklumatBayaranByHakmilik(idHakmilik, tahun,
						urlNSembilan);
			}

			if (bayaran != null) {
				deleteBayaran(idHakmilik, tahun);
				for (int i = 0 ; i < bayaran.length; i++){
					if (bayaran[i].getJumlahBayaran() != null
							&& Double.valueOf(bayaran[i].getJumlahBayaran()) > 0) {
						insertBayaranFromEtanah(idHakmilik, tahun, bayaran[i]);
					}
				}
			} 

			updateJumlahCukai(idHakmilik, tahun);
			updateJumlahBayaran(idHakmilik, tahun);
			updateBakiCukai(idHakmilik, tahun);

		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}
	}

	// END 2ND POINT OF INTERGRATION - GET MAKLUMAT CUKAI FROM ETANAH

	// START 3RD POINT OF INTERGRATION - GET MAKLUMAT BAYARAN FROM ETANAH
	public static void getMaklumatBayaran(String idHakmilik, String tahun,
			String kodNegeri) throws Exception {

		// DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "MAKLUMAT BAYARAN BERJAYA DICAPAI";

		Db db = null;

		try {

			db = new Db();

			MaklumatBayaran bayaran[] = null;
			if ("04".equals(kodNegeri)) {
				bayaran = getMaklumatBayaranByHakmilik(idHakmilik, tahun,
						urlMelaka);
			} else if ("05".equals(kodNegeri)) {
				bayaran = getMaklumatBayaranByHakmilik(idHakmilik, tahun,
						urlNSembilan);
			}
			
			if (bayaran != null) {
				deleteBayaran(idHakmilik, tahun);
				for (int i = 0 ; i < bayaran.length; i++){
					if (bayaran[i].getJumlahBayaran() != null
							&& Double.valueOf(bayaran[i].getJumlahBayaran()) > 0) {
						insertBayaranFromEtanah(idHakmilik, tahun, bayaran[i]);
					}
				}
				
			} else {
				flagMsg = "N";
				outputMsg = "TIADA BAYARAN YANG DIBUAT";
			}

			updateJumlahCukai(idHakmilik, tahun);
			updateJumlahBayaran(idHakmilik, tahun);
			updateBakiCukai(idHakmilik, tahun);

		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static void deleteBayaran(String idHakmilik, String tahun) {

		String sql = "";
		Db db = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			Statement stmt = db.getStatement();

			sql = "DELETE FROM INT_HTPMAKLUMATBAYARAN WHERE ID_HAKMILIK = '"
					+ idHakmilik + "' AND TAHUN = '" + tahun + "'";
			stmt.executeUpdate(sql);
			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void insertBayaranFromEtanah(String idHakmilik,
			String tahun, MaklumatBayaran bayaran) {

		String sql = "";
		Db db = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			Statement stmt = db.getStatement();

			sql = "INSERT INTO INT_HTPMAKLUMATBAYARAN (ID_HAKMILIK, TAHUN, ID_CARA_BAYAR,"
					+ " TARIKH_BAYARAN, NAMA_BANK, JUMLAH_BAYARAN,"
					+ " NO_RUJBAYARAN, TARIKH_TERIMA, TARIKH_RESIT,"
					+ " NO_RESIT)" + " VALUES ( '"
					+ idHakmilik
					+ "', '"
					+ tahun
					+ "', '"
					+ cleanDataString(bayaran.getIdCaraBayar())
					+ "',"
					+ " '"
					+ cleanDataString(bayaran.getTarikhBayaran())
					+ "', '"
					+ cleanDataString(bayaran.getNamaBank())
					+ "', '"
					+ cleanDataCurrency(bayaran.getJumlahBayaran())
					+ "',"
					+ " '"
					+ cleanDataString(bayaran.getNoRujBayaran())
					+ "', '"
					+ cleanDataString(bayaran.getTarikhTerimaBayaran())
					+ "', '"
					+ cleanDataString(bayaran.getTarikhResit())
					+ "',"
					+ " '" + cleanDataString(bayaran.getNoResit()) + "')";
			stmt.executeUpdate(sql);
			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}
	}

	// END 3RD POINT OF INTERGRATION - GET MAKLUMAT BAYARAN FROM ETANAH

	// START ETANAH WEBSERVICES
	private static MaklumatCukai getMaklumatCukaiByHakmilik(String idHakmilik,
			String tahun, String url) {

		EtappHasilPersekutuanServiceStub stub;
		MaklumatCukai maklumatCukai = new MaklumatCukai();

		try {
			stub = new EtappHasilPersekutuanServiceStub(url);

			MaklumatCukaiByHakmilik request = new MaklumatCukaiByHakmilik();

			request.setIdHakmilik(idHakmilik);
			request.setTahun(tahun);

			MaklumatCukaiByHakmilikE temp = new MaklumatCukaiByHakmilikE();
			temp.setMaklumatCukaiByHakmilik(request);

			MaklumatCukaiByHakmilikResponse response = stub
					.maklumatCukaiByHakmilik(temp)
					.getMaklumatCukaiByHakmilikResponse();
			maklumatCukai = response.get_return();

		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}
		return maklumatCukai;
	}

	private static MaklumatBayaran[] getMaklumatBayaranByHakmilik(
			String idHakmilik, String tahun, String url) {
		EtappHasilPersekutuanServiceStub stub;
		MaklumatBayaran[] maklumatBayaran = new MaklumatBayaran[10];

		try {
			stub = new EtappHasilPersekutuanServiceStub(url);
			MaklumatBayaranByHakmilik request = new MaklumatBayaranByHakmilik();
			request.setIdHakmilik(idHakmilik);
			request.setTahun(tahun);
			MaklumatBayaranByHakmilikE temp = new MaklumatBayaranByHakmilikE();
			temp.setMaklumatBayaranByHakmilik(request);
			MaklumatBayaranByHakmilikResponse response = stub
					.maklumatBayaranByHakmilik(temp)
					.getMaklumatBayaranByHakmilikResponse();
			maklumatBayaran = response.get_return();
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}
		return maklumatBayaran;
	}

	// private static MaklumatBayaran getMaklumatBayaranByHakmilik(String
	// idHakmilik, String tahun, String url) {
	//
	// EtappHasilPersekutuanServiceStub stub;
	// // MaklumatBayaran maklumatBayaran = new MaklumatBayaran();
	//
	// try {
	// stub = new EtappHasilPersekutuanServiceStub(url);
	//
	// MaklumatBayaranByHakmilik request = new MaklumatBayaranByHakmilik();
	//
	// request.setIdHakmilik(idHakmilik);
	// request.setTahun(tahun);
	//
	// MaklumatBayaranByHakmilikE temp = new MaklumatBayaranByHakmilikE();
	// temp.setMaklumatBayaranByHakmilik(request);
	//
	// MaklumatBayaranByHakmilikResponse response =
	// stub.maklumatBayaranByHakmilik(temp).getMaklumatBayaranByHakmilikResponse();
	// MaklumatBayaran [] maklumatBayaran = response.get_return();
	//
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// flagMsg = "N";
	// outputMsg = e.toString();
	// }
	// return maklumatBayaran;
	// }

	private static void updateHakmilikAgensi(HakmilikAgensi hakmilikAgensi,
			String url) throws Exception {

		EtappHasilPersekutuanServiceStub stub;

		try {
			stub = new EtappHasilPersekutuanServiceStub(url);

			UpdateHakmilikAgensi request = new UpdateHakmilikAgensi();
			request.setHakmilikAgensi(hakmilikAgensi);

			UpdateHakmilikAgensiE temp = new UpdateHakmilikAgensiE();
			temp.setUpdateHakmilikAgensi(request);

			stub.updateHakmilikAgensi(temp);

		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}
	}

	// END ETANAH WEBSERVICES

	private static void updateJumlahCukai(String idHakmilik, String tahun) {
		String sql = "";
		Db db = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// UPDATE JUMLAH CUKAI
			sql = "UPDATE INT_HTPMAKLUMATCUKAI A SET A.JUMLAH_CUKAI = (SELECT B.CUKAI_TANAH + B.TUNGGAKAN_CUKAI_TANAH + B.CUKAI_TALI_AIR + B.TUNGGAKAN_CUKAI_TALI_AIR + B.DENDA_LEWAT_SEMASA + B.TUNGGAKAN_DENDA_LEWAT + B.NOTIS_6A - B.REMISYEN AS TOTAL FROM INT_HTPMAKLUMATCUKAI B WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.TAHUN = A.TAHUN) WHERE A.ID_HAKMILIK = '"
					+ idHakmilik + "' AND A.TAHUN = '" + tahun + "'";
			stmt.executeUpdate(sql);

			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void updateJumlahBayaran(String idHakmilik, String tahun) {
		String sql = "";
		Db db = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// UPDATE JUMLAH CUKAI
			sql = "UPDATE INT_HTPMAKLUMATCUKAI A SET A.JUMLAH_CUKAI_DIBAYAR = NVL((SELECT SUM(B.JUMLAH_BAYARAN) AS TOTAL FROM INT_HTPMAKLUMATBAYARAN B WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.TAHUN = A.TAHUN),0) WHERE A.ID_HAKMILIK = '"
					+ idHakmilik + "' AND A.TAHUN = '" + tahun + "'";
			stmt.executeUpdate(sql);

			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void updateBakiCukai(String idHakmilik, String tahun) {
		String sql = "";
		Db db = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// UPDATE BAKI CUKAI
			sql = "UPDATE INT_HTPMAKLUMATCUKAI A SET A.BAKI = (SELECT B.JUMLAH_CUKAI - B.JUMLAH_CUKAI_DIBAYAR AS BAKI FROM INT_HTPMAKLUMATCUKAI B WHERE B.ID_HAKMILIK = A.ID_HAKMILIK AND B.TAHUN = A.TAHUN) WHERE A.ID_HAKMILIK = '"
					+ idHakmilik + "' AND A.TAHUN = '" + tahun + "'";
			stmt.executeUpdate(sql);

			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	private static String cleanDataCurrency(String input) {
		String temp = input;
		if (input == null)
			temp = "0";
		if (input == "null")
			temp = "0";
		if (input == "")
			temp = "0";
		return temp;
	}

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
		EtanahHTPManager.flagMsg = flagMsg;
	}

	public static String getOutputMsg() {
		return outputMsg;
	}

	public static void setOutputMsg(String outputMsg) {
		EtanahHTPManager.outputMsg = outputMsg;
	}
}
