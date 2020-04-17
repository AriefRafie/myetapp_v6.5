package integrasi.rest.etanah.wpkl.ppt;

import integrasi.rest.etanah.wpkl.RESTInvoker;
import integrasi.rest.etanah.wpkl.entities.DokumenPermohonan;
import integrasi.rest.etanah.wpkl.entities.HakmilikPermohonanD;
import integrasi.rest.etanah.wpkl.entities.HakmilikPermohonanK;
import integrasi.rest.etanah.wpkl.entities.HakmilikPermohonanPD;
import integrasi.rest.etanah.wpkl.entities.PermohonanDForm;
import integrasi.rest.etanah.wpkl.entities.PermohonanKForm;
import integrasi.rest.etanah.wpkl.entities.PermohonanPDForm;
import integrasi.rest.etanah.wpkl.entities.ResponseForm;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lebah.db.Db;

public class EtanahWPKLPPTManager {

	private static String flagMsg = null;
	private static String outputMsg = null;
	
	public static void hantarBorangD(String idPermohonan, String idPengguna, Db db) {
		//DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "MAKLUMAT PERMOHONAN BERJAYA DIHANTAR";
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		PermohonanDForm permohonan = null;
		
		try {
			permohonan = preparePermohonanD(idPermohonan, db);	
			ResponseForm response = RESTInvoker.hantarBorangD(idPermohonan, permohonan, cal, idPengguna);
			if (response != null) {
				updateFlagHantar(idPermohonan, cal.getTime(), response,db, idPengguna);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}	
	}
	
	public static void hantarBorangK(String idPermohonan, String idPengguna, Db db) {
		//DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "MAKLUMAT PERMOHONAN BERJAYA DIHANTAR";
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		PermohonanKForm permohonan = null;
		
		try {
			permohonan = preparePermohonanK(idPermohonan,db);	
			ResponseForm response = RESTInvoker.hantarBorangK(idPermohonan, permohonan, cal, idPengguna);
			if (response != null) {
				updateFlagHantar(idPermohonan, cal.getTime(), response, db, idPengguna);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}	
	}
	
	public static void hantarPenarikanBalik(String idPermohonan, String idPengguna, Db db) {
		//DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "MAKLUMAT PERMOHONAN BERJAYA DIHANTAR";
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		PermohonanPDForm permohonan = null;
		
		try {
			permohonan = preparePermohonanPD(idPermohonan,db);	
			ResponseForm response = RESTInvoker.hantarBorangPenarikanBalik(idPermohonan, permohonan, cal, idPengguna);
			if (response != null) {
				updateFlagHantar(idPermohonan, cal.getTime(), response, db, idPengguna);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}	
	}
	
	private static PermohonanDForm preparePermohonanD(String idPermohonan, Db db) {
		String sql = "";	
		PermohonanDForm permohonanForm = null;
		
		try {		
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM INT_PPTPERMOHONAN WHERE FLAG_HANTAR = 'T' AND ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				permohonanForm = new PermohonanDForm();
				permohonanForm.setNoFailPTG(rs.getString("NO_FAIL"));
				permohonanForm.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
				permohonanForm.setNamaProjek(rs.getString("NAMA_PROJEK"));			
				
				sql = "SELECT * FROM INT_PPTHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
				ResultSet rsHakmilik = stmt.executeQuery(sql);
				
				List listHakmilik = Collections.synchronizedList(new ArrayList());
				Map h = null;
				while (rsHakmilik.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("ID_HAKMILIKPERMOHONAN",rsHakmilik.getString("ID_HAKMILIKPERMOHONAN") == null ? "" : rsHakmilik.getString("ID_HAKMILIKPERMOHONAN"));
					listHakmilik.add(h);					
				}				
				List<HakmilikPermohonanD> listHakmilikPermohonan = null;
				for(int i = 0; i < listHakmilik.size();i++)
			    {			   
					Map map = (Map) listHakmilik.get(i);
					if(map!=null)
					{
						if (listHakmilikPermohonan == null) {
							listHakmilikPermohonan = new ArrayList<>();
						}
						String ID_HAKMILIKPERMOHONAN = (String) map.get("ID_HAKMILIKPERMOHONAN");
						listHakmilikPermohonan.add(getHakmilikPermohonanD(ID_HAKMILIKPERMOHONAN,db));
					}
			    }			
				permohonanForm.setBorangList(listHakmilikPermohonan);
				
				sql = "SELECT * FROM INT_PPTDOKUMENPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
				ResultSet rsDokumen = stmt.executeQuery(sql);
								
				List listDoc = Collections.synchronizedList(new ArrayList());
				Map hDoc = null;
				while (rsDokumen.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("ID_DOKUMENPERMOHONAN",rsDokumen.getString("ID_DOKUMENPERMOHONAN") == null ? "" : rsDokumen.getString("ID_DOKUMENPERMOHONAN"));
					listDoc.add(h);					
				}				
				List<DokumenPermohonan> listDokumenPermohonan = null;
				for(int i = 0; i < listDoc.size();i++)
			    {			   
					Map mapDoc = (Map) listDoc.get(i);
					if(mapDoc!=null)
					{
						if (listDokumenPermohonan == null) {
							listDokumenPermohonan = new ArrayList<>();
						}
						String ID_DOKUMENPERMOHONAN = (String) mapDoc.get("ID_DOKUMENPERMOHONAN");
						listDokumenPermohonan.add(getDokumenPermohonan(ID_DOKUMENPERMOHONAN,db));
					}
			    }			
				
				permohonanForm.setFailList(listDokumenPermohonan);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}	
		return permohonanForm;
	}
	
	private static HakmilikPermohonanD getHakmilikPermohonanD(String idHakmilikPermohonan,Db db) {
		String sql = "";
		HakmilikPermohonanD hakmilikPermohonan = null;
		
		try {	
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM INT_PPTHAKMILIKPERMOHONAN WHERE ID_HAKMILIKPERMOHONAN = '" + idHakmilikPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				hakmilikPermohonan = new HakmilikPermohonanD();
				hakmilikPermohonan.setIdHakmilik(rs.getString("ID_HAKMILIK"));				
				hakmilikPermohonan.setKodLot(rs.getString("KOD_LOT"));
				hakmilikPermohonan.setNoLot(rs.getString("NO_LOT"));
				hakmilikPermohonan.setUnitLuasAsal(rs.getString("KOD_LUAS_ASAL"));		
				hakmilikPermohonan.setLuasAsal(rs.getString("LUAS_ASAL"));	
				hakmilikPermohonan.setUnitLuasAmbil(rs.getString("KOD_LUAS_AMBIL"));					
				hakmilikPermohonan.setLuasAmbil(rs.getString("LUAS_AMBIL"));
				hakmilikPermohonan.setNoWarta(rs.getString("NO_WARTA"));
				if (rs.getString("TARIKH_WARTA") != null) {
					Date tarikhWarta = rs.getDate("TARIKH_WARTA");
					Calendar calWarta = new GregorianCalendar();
					calWarta.setTime(tarikhWarta);
					String tarikhWartaString = String.valueOf(calWarta.get(Calendar.YEAR)) + "-" 
							+ new DecimalFormat("00").format(calWarta.get(Calendar.MONTH) + 1) + "-" 
							+ new DecimalFormat("00").format(calWarta.get(Calendar.DATE))
							+ "T00:00:00";				
					
					hakmilikPermohonan.setTarikhWarta(tarikhWartaString);
				}				
				hakmilikPermohonan.setCatatan("");				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}
		
		return hakmilikPermohonan;
	}
	
	private static DokumenPermohonan getDokumenPermohonan(String idDokumenPermohonan, Db db) {
		String sql = "";
		DokumenPermohonan dokumenPermohonan = null;
		
		try {		
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM INT_PPTDOKUMENPERMOHONAN WHERE ID_DOKUMENPERMOHONAN = '" + idDokumenPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				dokumenPermohonan = new DokumenPermohonan();				
				dokumenPermohonan.setKodJenisDokumen(rs.getString("FLAG_DOKUMEN"));				
				dokumenPermohonan.setNamaFail(rs.getString("KOD_DOKUMEN"));		
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}
		
		return dokumenPermohonan;
	}
	
	private static PermohonanKForm preparePermohonanK(String idPermohonan,Db db) {
		String sql = "";	
		PermohonanKForm permohonanForm = null;
		
		try {		
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM INT_PPTPERMOHONAN WHERE FLAG_HANTAR = 'T' AND ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				permohonanForm = new PermohonanKForm();
				permohonanForm.setNoFailPTG(rs.getString("NO_FAIL"));
				permohonanForm.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
				permohonanForm.setNamaProjek(rs.getString("NAMA_PROJEK"));			
				permohonanForm.setNoWarta(rs.getString("NO_WARTA"));
				if (rs.getString("TARIKH_WARTA") != null) {
					Date tarikhWarta = rs.getDate("TARIKH_WARTA");
					Calendar calWarta = new GregorianCalendar();
					calWarta.setTime(tarikhWarta);
					String tarikhWartaString = String.valueOf(calWarta.get(Calendar.YEAR)) + "-" 
							+ new DecimalFormat("00").format(calWarta.get(Calendar.MONTH) + 1) + "-" 
							+ new DecimalFormat("00").format(calWarta.get(Calendar.DATE))
							+ "T00:00:00";				
					
					permohonanForm.setTarikhWarta(tarikhWartaString);
				}	
				
				sql = "SELECT * FROM INT_PPTHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
				ResultSet rsHakmilik = stmt.executeQuery(sql);
								
				List listHakmilikK = Collections.synchronizedList(new ArrayList());
				Map h = null;
				while (rsHakmilik.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("ID_HAKMILIKPERMOHONAN",rsHakmilik.getString("ID_HAKMILIKPERMOHONAN") == null ? "" : rsHakmilik.getString("ID_HAKMILIKPERMOHONAN"));
					listHakmilikK.add(h);					
				}				
				List<HakmilikPermohonanK> listHakmilikPermohonan = null;
				for(int i = 0; i < listHakmilikK.size();i++)
			    {			   
					Map mapK = (Map) listHakmilikK.get(i);
					if(mapK!=null)
					{
						if (listHakmilikPermohonan == null) {
							listHakmilikPermohonan = new ArrayList<>();
						}
						String ID_HAKMILIKPERMOHONAN = (String) mapK.get("ID_HAKMILIKPERMOHONAN");
						listHakmilikPermohonan.add(getHakmilikPermohonanK(ID_HAKMILIKPERMOHONAN,db));
					}
			    }				
				permohonanForm.setBorangList(listHakmilikPermohonan);
				
				sql = "SELECT * FROM INT_PPTDOKUMENPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
				ResultSet rsDokumen = stmt.executeQuery(sql);
				
				List listDoc = Collections.synchronizedList(new ArrayList());
				Map hDoc = null;
				while (rsDokumen.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("ID_DOKUMENPERMOHONAN", rsDokumen.getString("ID_DOKUMENPERMOHONAN") == null ? "" : rsDokumen.getString("ID_DOKUMENPERMOHONAN"));
					listDoc.add(h);					
				}				
				List<DokumenPermohonan> listDokumenPermohonan = null;
				for(int i = 0; i < listDoc.size();i++)
			    {			   
					Map mapDoc = (Map) listDoc.get(i);
					if(mapDoc!=null)
					{
						if (listDokumenPermohonan == null) {
							listDokumenPermohonan = new ArrayList<>();
						}
						String ID_DOKUMENPERMOHONAN = (String) mapDoc.get("ID_DOKUMENPERMOHONAN");
						listDokumenPermohonan.add(getDokumenPermohonan(ID_DOKUMENPERMOHONAN,db));
					}
			    }			
				
				permohonanForm.setFailList(listDokumenPermohonan);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}	
		return permohonanForm;
	}
	
	private static HakmilikPermohonanK getHakmilikPermohonanK(String idHakmilikPermohonan, Db db) {
		String sql = "";
		HakmilikPermohonanK hakmilikPermohonan = null;
		
		try {	
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM INT_PPTHAKMILIKPERMOHONAN WHERE ID_HAKMILIKPERMOHONAN = '" + idHakmilikPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				hakmilikPermohonan = new HakmilikPermohonanK();
				hakmilikPermohonan.setNoSubjaket(rs.getString("NO_SUBJAKET"));	
				hakmilikPermohonan.setIdHakmilik(rs.getString("ID_HAKMILIK"));				
				hakmilikPermohonan.setKodLot(rs.getString("KOD_LOT"));
				hakmilikPermohonan.setNoLot(rs.getString("NO_LOT"));
				hakmilikPermohonan.setUnitLuasAsal(rs.getString("KOD_LUAS_ASAL"));		
				hakmilikPermohonan.setLuasAsal(rs.getString("LUAS_ASAL"));	
				hakmilikPermohonan.setUnitLuasAmbil(rs.getString("KOD_LUAS_AMBIL"));					
				hakmilikPermohonan.setLuasAmbil(rs.getString("LUAS_AMBIL"));
				if (rs.getString("TARIKH_BORANGK") != null) {
					Date tarikhBorangK = rs.getDate("TARIKH_BORANGK");
					Calendar calBorangK = new GregorianCalendar();
					calBorangK.setTime(tarikhBorangK);
					String tarikhBorangKString = String.valueOf(calBorangK.get(Calendar.YEAR)) + "-" 
							+ new DecimalFormat("00").format(calBorangK.get(Calendar.MONTH) + 1) + "-" 
							+ new DecimalFormat("00").format(calBorangK.get(Calendar.DATE))
							+ "T00:00:00";				
					
					hakmilikPermohonan.setTarikhBorangK(tarikhBorangKString);
				}				
				hakmilikPermohonan.setStatusAmbil(rs.getString("STATUS_AMBIL"));				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}
		
		return hakmilikPermohonan;
	}
	
	private static PermohonanPDForm preparePermohonanPD(String idPermohonan, Db db) {
		String sql = "";
		PermohonanPDForm permohonanForm = null;
		
		try {
			db = new Db();			
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM INT_PPTPERMOHONAN WHERE FLAG_HANTAR = 'T' AND ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				permohonanForm = new PermohonanPDForm();
				permohonanForm.setNoFailPTG(rs.getString("NO_FAIL"));
				permohonanForm.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
				permohonanForm.setNamaProjek(rs.getString("NAMA_PROJEK"));			
				
				sql = "SELECT * FROM INT_PPTHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
				ResultSet rsHakmilik = stmt.executeQuery(sql);
				
				
				List listHakmilik = Collections.synchronizedList(new ArrayList());
				Map h = null;
				while (rsHakmilik.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("ID_HAKMILIKPERMOHONAN",rsHakmilik.getString("ID_HAKMILIKPERMOHONAN") == null ? "" : rsHakmilik.getString("ID_HAKMILIKPERMOHONAN"));
					listHakmilik.add(h);					
				}				
				List<HakmilikPermohonanPD> listHakmilikPermohonan = null;
				for(int i = 0; i < listHakmilik.size();i++)
			    {			   
					Map mapHakmilik = (Map) listHakmilik.get(i);
					if(mapHakmilik!=null)
					{
						if (listHakmilikPermohonan == null) {
							listHakmilikPermohonan = new ArrayList<>();
						}
						String ID_HAKMILIKPERMOHONAN = (String) mapHakmilik.get("ID_HAKMILIKPERMOHONAN");
						listHakmilikPermohonan.add(getHakmilikPermohonanPD(ID_HAKMILIKPERMOHONAN,db));
					}
			    }	
				permohonanForm.setBorangList(listHakmilikPermohonan);
				
				sql = "SELECT * FROM INT_PPTDOKUMENPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
				ResultSet rsDokumen = stmt.executeQuery(sql);
				List listDoc = Collections.synchronizedList(new ArrayList());
				Map hDoc = null;
				while (rsDokumen.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("ID_DOKUMENPERMOHONAN",rsDokumen.getString("ID_DOKUMENPERMOHONAN") == null ? "" : rsDokumen.getString("ID_DOKUMENPERMOHONAN"));
					listDoc.add(h);					
				}				
				List<DokumenPermohonan> listDokumenPermohonan = null;
				for(int i = 0; i < listDoc.size();i++)
			    {			   
					Map mapDoc = (Map) listDoc.get(i);
					if(mapDoc!=null)
					{
						if (listDokumenPermohonan == null) {
							listDokumenPermohonan = new ArrayList<>();
						}
						String ID_DOKUMENPERMOHONAN = (String) mapDoc.get("ID_DOKUMENPERMOHONAN");
						listDokumenPermohonan.add(getDokumenPermohonan(ID_DOKUMENPERMOHONAN,db));
					}
			    }			
				permohonanForm.setFailList(listDokumenPermohonan);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}	
		return permohonanForm;
	}
	
	private static HakmilikPermohonanPD getHakmilikPermohonanPD(String idHakmilikPermohonan,Db db) {
		String sql = "";
		HakmilikPermohonanPD hakmilikPermohonan = null;
		
		try {		
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM INT_PPTHAKMILIKPERMOHONAN WHERE ID_HAKMILIKPERMOHONAN = '" + idHakmilikPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				hakmilikPermohonan = new HakmilikPermohonanPD();
				hakmilikPermohonan.setNoSubjaket(rs.getString("NO_SUBJAKET"));		
				hakmilikPermohonan.setIdHakmilik(rs.getString("ID_HAKMILIK"));				
				hakmilikPermohonan.setKodLot(rs.getString("KOD_LOT"));
				hakmilikPermohonan.setNoLot(rs.getString("NO_LOT"));
				hakmilikPermohonan.setUnitLuasAsal(rs.getString("KOD_LUAS_ASAL"));		
				hakmilikPermohonan.setLuasAsal(rs.getString("LUAS_ASAL"));	
				hakmilikPermohonan.setUnitLuasAmbil(rs.getString("KOD_LUAS_AMBIL"));					
				hakmilikPermohonan.setLuasAmbil(rs.getString("LUAS_AMBIL"));
				hakmilikPermohonan.setNoWarta(rs.getString("NO_WARTA"));
				if (rs.getString("TARIKH_WARTA") != null) {
					Date tarikhWarta = rs.getDate("TARIKH_WARTA");
					Calendar calWarta = new GregorianCalendar();
					calWarta.setTime(tarikhWarta);
					String tarikhWartaString = String.valueOf(calWarta.get(Calendar.YEAR)) + "-" 
							+ new DecimalFormat("00").format(calWarta.get(Calendar.MONTH) + 1) + "-" 
							+ new DecimalFormat("00").format(calWarta.get(Calendar.DATE))
							+ "T00:00:00";				
					
					hakmilikPermohonan.setTarikhWarta(tarikhWartaString);
				}				
				hakmilikPermohonan.setCatatan(rs.getString("SEBAB_PENARIKANBALIK"));				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}
		
		return hakmilikPermohonan;
	}
	
	private static void updateFlagHantar(String idPermohonan, Date tarikhHantar, ResponseForm response, Db db, String idPengguna) {
		String sql = "";	
		try {
			Statement stmt = db.getStatement();
			
			sql = "UPDATE INT_PPTPERMOHONAN SET FLAG_HANTAR = 'Y', TARIKH_HANTAR = SYSDATE WHERE ID_PERMOHONAN = '" + idPermohonan + "' AND ID_PENGGUNA = '" + idPengguna + "'";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
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
