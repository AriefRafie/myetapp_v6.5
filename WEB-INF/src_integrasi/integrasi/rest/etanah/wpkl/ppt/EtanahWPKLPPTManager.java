package integrasi.rest.etanah.wpkl.ppt;

import integrasi.rest.etanah.wpkl.RESTInvoker;
import integrasi.rest.etanah.wpkl.ppk.entities.ResponseForm;
import integrasi.rest.etanah.wpkl.ppt.entities.Fail;
import integrasi.rest.etanah.wpkl.ppt.entities.HakmilikPermohonan;
import integrasi.rest.etanah.wpkl.ppt.entities.PermohonanForm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import lebah.db.Db;

public class EtanahWPKLPPTManager {

	private static String flagMsg = null;
	private static String outputMsg = null;
	
	public static void hantarBorangD(String noFail, String idPengguna) {
		//DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "MAKLUMAT PERMOHONAN BERJAYA DIHANTAR";
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		PermohonanForm permohonan = null;
		
		try {
			permohonan = preparePermohonan(noFail, "D");	
			ResponseForm response = RESTInvoker.hantarBorangD(permohonan, cal, idPengguna);
			if (response != null) {
				System.out.println(response.getKodRalat());
				System.out.println(response.getCatatan());
				updateFlagHantar(noFail, "D", cal.getTime(), response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}	
	}
	
	public static void hantarBorangK(String noFail, String idPengguna) {
		//DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "MAKLUMAT PERMOHONAN BERJAYA DIHANTAR";
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		PermohonanForm permohonan = null;
		
		try {
			permohonan = preparePermohonan(noFail, "K");	
			ResponseForm response = RESTInvoker.hantarBorangK(permohonan, cal, idPengguna);
			if (response != null) {
				System.out.println(response.getKodRalat());
				System.out.println(response.getCatatan());
				updateFlagHantar(noFail, "K", cal.getTime(), response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}	
	}
	
	public static void hantarPenarikanBalik(String noFail, String idPengguna) {
		//DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "MAKLUMAT PERMOHONAN BERJAYA DIHANTAR";
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		PermohonanForm permohonan = null;
		
		try {
			permohonan = preparePermohonan(noFail, "PD");	
			ResponseForm response = RESTInvoker.hantarBorangK(permohonan, cal, idPengguna);
			if (response != null) {
				System.out.println(response.getKodRalat());
				System.out.println(response.getCatatan());
				updateFlagHantar(noFail, "PD", cal.getTime(), response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}	
	}
	
	private static PermohonanForm preparePermohonan(String noFail, String flagUrusan) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		Db db = null;		
		PermohonanForm permohonanForm = null;
		
		try {
			db = new Db();			
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM INT_PPTPERMOHONAN WHERE FLAG_HANTAR = 'T' AND FLAG_URUSAN = '" + flagUrusan + "' AND NO_FAIL = '" + noFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				permohonanForm = new PermohonanForm();
				permohonanForm.setIdTransaksi(rs.getString("ID_PERMOHONAN"));
				permohonanForm.setNoFailPTG(rs.getString("NO_FAIL"));
				permohonanForm.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
				permohonanForm.setNamaProjek(rs.getString("NAMA_PROJEK"));
				permohonanForm.setNoWarta(rs.getString("NO_WARTA"));
				Date tarikhWarta = sdf.parse(rs.getString("TARIKH_WARTA"));
				Calendar calWarta = new GregorianCalendar();
				calWarta.setTime(tarikhWarta);
				String tarikhWartaString = String.valueOf(calWarta.get(Calendar.YEAR)) + "-" 
						+ new DecimalFormat("00").format(calWarta.get(Calendar.MONTH) + 1) + "-" 
						+ new DecimalFormat("00").format(calWarta.get(Calendar.DATE))
						+ "T00:00:00";				
				
				permohonanForm.setTarikhWarta(tarikhWartaString);
				
				sql = "SELECT * FROM INT_PPTHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN = '" + rs.getString("ID_PERMOHONAN") + "'";
				ResultSet rsHakmilik = stmt.executeQuery(sql);
				
				List<HakmilikPermohonan> listHakmilikPermohonan = null;
				while (rsHakmilik.next()) {
					if (listHakmilikPermohonan == null) {
						listHakmilikPermohonan = new ArrayList<>();
					}
					listHakmilikPermohonan.add(getHakmilikPermohonan(rsHakmilik.getString("ID_HAKMILIKPERMOHONAN")));
				}
				permohonanForm.setHakmilikPermohonanList(listHakmilikPermohonan);
				
				sql = "SELECT * FROM INT_PPTDOKUMENPERMOHONAN WHERE ID_PERMOHONAN = '" + rs.getString("ID_PERMOHONAN") + "'";
				ResultSet rsFail = stmt.executeQuery(sql);
				
				List<Fail> listFail = null;
				while (rsFail.next()) {
					if (listFail == null) {
						listFail = new ArrayList<>();
					}
					listFail.add(getFail(rsFail.getString("ID_DOKUMENPERMOHONAN")));
				}
				permohonanForm.setFailList(listFail);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}	
		return permohonanForm;
	}
	
	private static HakmilikPermohonan getHakmilikPermohonan(String idHakmilikPermohonan) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		Db db = null;
		HakmilikPermohonan hakmilikPermohonan = null;
		
		try {
			
			db = new Db();			
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM INT_PPTHAKMILIKPERMOHONAN WHERE ID_HAKMILIKPERMOHONAN = '" + idHakmilikPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				hakmilikPermohonan = new HakmilikPermohonan();
				hakmilikPermohonan.setNoSubjaket(rs.getString("NO_SUBJAKET"));	
				hakmilikPermohonan.setIdHakmilik(rs.getString("ID_HAKMILIK"));				
				hakmilikPermohonan.setKodLot(rs.getString("KOD_LOT"));
				hakmilikPermohonan.setNoLot(rs.getString("NO_LOT"));
				hakmilikPermohonan.setUnitLuasAsal(getKeteranganLuasByKod(rs.getString("KOD_LUAS_ASAL")));					
				hakmilikPermohonan.setLuasAsal(rs.getString("LUAS_ASAL"));
				hakmilikPermohonan.setUnitLuasAmbil(getKeteranganLuasByKod(rs.getString("KOD_LUAS_AMBIL")));					
				hakmilikPermohonan.setLuasAmbil(rs.getString("LUAS_AMBIL"));
				hakmilikPermohonan.setNoWarta(rs.getString("NO_WARTA"));
				Date tarikhWarta = sdf.parse(rs.getString("TARIKH_WARTA"));
				Calendar calWarta = new GregorianCalendar();
				calWarta.setTime(tarikhWarta);
				String tarikhWartaString = String.valueOf(calWarta.get(Calendar.YEAR)) + "-" 
						+ new DecimalFormat("00").format(calWarta.get(Calendar.MONTH) + 1) + "-" 
						+ new DecimalFormat("00").format(calWarta.get(Calendar.DATE))
						+ "T00:00:00";				
				
				hakmilikPermohonan.setTarikhWarta(tarikhWartaString);
				hakmilikPermohonan.setCatatan(rs.getString("SEBAB_PENARIKANBALIK"));
				
				Date tarikhBorangK = sdf.parse(rs.getString("TARIKH_BORANGK"));
				Calendar calBorangK = new GregorianCalendar();
				calBorangK.setTime(tarikhBorangK);
				String tarikhBorangKString = String.valueOf(calBorangK.get(Calendar.YEAR)) + "-" 
						+ new DecimalFormat("00").format(calBorangK.get(Calendar.MONTH) + 1) + "-" 
						+ new DecimalFormat("00").format(calBorangK.get(Calendar.DATE))
						+ "T00:00:00";				
				
				hakmilikPermohonan.setTarikhBorangK(tarikhBorangKString);
				hakmilikPermohonan.setStatusAmbil(rs.getString("STATUS_AMBIL"));
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}
		
		return hakmilikPermohonan;
	}
	
	private static Fail getFail(String idDokumenPermohonan) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		Db db = null;
		Fail fail = null;
		
		try {
			
			db = new Db();			
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM INT_PPTDOKUMENPERMOHONAN WHERE ID_DOKUMENPERMOHONAN = '" + idDokumenPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				fail = new Fail();
				fail.setFailString(rs.getString("KOD_DOKUMEN"));					
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}
		
		return fail;
	}
	
	private static String getKeteranganLuasByKod(String kodLuas) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT KETERANGAN FROM TBLRUJLUAS WHERE KOD_LUAS = '" + kodLuas + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("KETERANGAN");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static void updateFlagHantar(String noFail, String flagUrusan, Date tarikhHantar, ResponseForm response) {
		String sql = "";
		Db db = null;		
		try {
			db = new Db();			
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			sql = "UPDATE INT_PPTPERMOHONAN SET FLAG_HANTAR = 'Y', TARIKH_HANTAR = SYSDATE, FLAG_TRANSAKSI = '" + response.getKodRalat() + "', , KETERANGAN_TRANSAKSI = '" + response.getCatatan() + "'"
					 + " WHERE NO_FAIL = '" + noFail + "' AND FLAG_URUSAN = '" + flagUrusan + "'";
			stmt.executeUpdate(sql);
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}	
	}

	public static String getFlagMsg() {
		return flagMsg;
	}

	public static void setFlagMsg(String flagMsg) {
		EtanahWPKLPPTManager.flagMsg = flagMsg;
	}

	public static String getOutputMsg() {
		return outputMsg;
	}

	public static void setOutputMsg(String outputMsg) {
		EtanahWPKLPPTManager.outputMsg = outputMsg;
	}
}
