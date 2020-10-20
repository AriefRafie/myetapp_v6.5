package integrasi.ws.etanah;

import integrasi.rest.etanah.wpkl.RESTInvoker;
import integrasi.rest.etanah.wpkl.entities.DokumenPermohonan;
import integrasi.rest.etanah.wpkl.entities.HakmilikPermohonanD;
import integrasi.rest.etanah.wpkl.entities.HakmilikPermohonanK;
import integrasi.rest.etanah.wpkl.entities.HakmilikPermohonanPD;
import integrasi.rest.etanah.wpkl.entities.PermohonanDForm;
import integrasi.rest.etanah.wpkl.entities.PermohonanKForm;
import integrasi.rest.etanah.wpkl.entities.PermohonanPDForm;
import integrasi.rest.etanah.wpkl.entities.ResponseForm;
import integrasi.utils.IntegrationInternal;
import integrasi.ws.etanah.ppt.ETanahPPTManager;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.LampiranForm;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MaklumatHakmilikForm;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MaklumatPermohonanSek8Form;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.model.entities.Tblrujdokumen;
import ekptg.view.integrasi.etanah.PermohonanPengambilan;
import lebah.db.Db;
import lebah.db.SQLRenderer;

public class ETanahSek8 implements IntegrationInternal{

	private static Logger myLog = Logger.getLogger(ETanahSek8.class);
	private static String flagMsg = null;
	private static String outputMsg = null;
	private Vector vecHakmilik = null;
	MaklumatPermohonanSek8Form form = null;
	MaklumatHakmilikForm[] hakmiliks = null;
	LampiranForm[] lampirans = null;
	private String URUSAN = "C";
	private Calendar cal = new GregorianCalendar();

	
	public void hantar(ETanahPPTManager pptManager
		,Hashtable<String,String> permohonan_
		,Vector<Tblrujdokumen> vecDok
		,String idPengguna, Db db) {
		//DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "MAKLUMAT PERMOHONAN BERJAYA DIHANTAR";
		
		cal.setTime(new Date());
		//PermohonanDForm permohonan = null;
		
		try {
			form = getPermohonan(permohonan_);
			hakmiliks = getHakmilik();
			lampirans = getLampiran(vecDok);
			
			setPermohonan(permohonan_,idPengguna,db);
			
			String response = "";
			response = pptManager.permohonanSek8(form, hakmiliks, lampirans);
			myLog.info("response="+response);
			
			if (!response.equals("")) {
				updateFlagHantar(permohonan_.get("idPermohonan"), cal.getTime(), response,db, idPengguna);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}	
		
	}
	
	public void setHakmiliks(Vector<Hashtable<String,String>> vec) {
		this.vecHakmilik = vec;
	} 
	
	private LampiranForm[] getLampiran(Vector<Tblrujdokumen> vecDok) {
		LampiranForm[] lf = new LampiranForm[vecDok.size()];
		LampiranForm lampiran = null;
		Tblrujdokumen dokumen =null;
		for (int i = 0; i < vecDok.size(); i++) {
			dokumen = (Tblrujdokumen)vecDok.get(i);
			lampiran = new LampiranForm();
			lampiran.setBytes(dokumen.getKandungan());
			lampiran.setDocType(dokumen.getIdJenis());
			lampiran.setFilename(dokumen.getNamaDokumen());
			lampiran.setKodDokumen(dokumen.getIdDokumen()); //rujukan
			//myLog.info(i+"."+cbsemaks[i]);
			
			lf[i] = lampiran;
		}
		return lf;
		
	}

	private MaklumatHakmilikForm[] getHakmilik() {
		MaklumatHakmilikForm[] mhf = new MaklumatHakmilikForm[vecHakmilik.size()];
		MaklumatHakmilikForm hakmilik = null;
		Hashtable<String,String> tanah = null;
		for (int i = 0; i < vecHakmilik.size(); i++) {
			tanah = (Hashtable<String,String>)vecHakmilik.get(i);
			hakmilik = new MaklumatHakmilikForm();
			hakmilik.setId_hakmilik(tanah.get("idHakmilik"));
			hakmilik.setKod_luas_ambil(tanah.get("kodLuasAmbil"));
			hakmilik.setKod_luas_asal(tanah.get("kodLuasAsal"));
			hakmilik.setKod_unit_hakmilik(tanah.get("kodHakmilik"));
			hakmilik.setLuas_ambil(tanah.get("luasAmbil"));
			hakmilik.setLuas_asal(tanah.get("luasAsal"));
			//hakmilik.setNo_fail_jkptg(tanah.get("kodLuasAsal"));
			hakmilik.setNo_hakmilik(tanah.get("noHakmilik"));
			hakmilik.setNo_lot(tanah.get("noLot"));
			//hakmilik.setNo_warta(tanah.get("kodLuasAsal"));
//			hakmilik.sesetKod_luas_asal(tanah.get("kodLuasAsal"));
//			hakmilik.setKod_luas_asal(tanah.get("kodLuasAsal"));
//			hakmilik.setKod_luas_asal(tanah.get("kodLuasAsal"));
//			hakmilik.setKod_luas_asal(tanah.get("kodLuasAsal"));
//			hakmilik.setKod_luas_asal(tanah.get("kodLuasAsal"));
//			hakmilik.setKod_luas_asal(tanah.get("kodLuasAsal"));
//			hakmilik.setKod_luas_asal(tanah.get("kodLuasAsal"));
//			hakmilik.setKod_luas_asal(tanah.get("kodLuasAsal"));

			mhf[i] = hakmilik;
			
		}
		return mhf;
	}

	private MaklumatPermohonanSek8Form getPermohonan(Hashtable<String,String> permohonan) {
		MaklumatPermohonanSek8Form form = null;
		form = new MaklumatPermohonanSek8Form();
		form.setAlamat1(permohonan.get("alamat1"));
		form.setAlamat2(permohonan.get("alamat2"));
		form.setAlamat3(permohonan.get("alamat3"));
		//form.setAlamat4(permohonan.get("noFail"));
		form.setPoskod(permohonan.get("poskod"));
		form.setKodNegeri(permohonan.get("kodNegeriA"));

		form.setId_agensi_myetapp(permohonan.get("idAgensi"));
		form.setJenis_pengambilan(permohonan.get("jenisPengambilan"));
		form.setJenis_projek_pengambilan(permohonan.get("jenisProjek"));
		form.setKod_daerah_pengambilan(permohonan.get("kodDaerah"));
		form.setKod_negeri_pengambilan(permohonan.get("kodNegeri"));
		form.setKodAgensi(permohonan.get("kodAgensi"));
		form.setKodKementerian(permohonan.get("kodKementerian"));
		form.setNama_agensi(permohonan.get("namaAgensi"));
		form.setNama_daerah_pengambilan(permohonan.get("namaDaerah"));
		form.setNama_kementerian(permohonan.get("namaKementerian"));
		form.setNama_negeri_pengambilan(permohonan.get("namaNegeri"));
		//form.setNo_fail_jkptg("JKPTG(S).MLK/03/881/24/2019/5");
		form.setNo_fail_jkptg(permohonan.get("noFail"));
		form.setNo_rujukan_surat_kjp(permohonan.get("noRujukanSurat"));
		form.setTarikh_surat_kjp(permohonan.get("tarikhRujukanSurat"));

		//KJP ATAU JKPTG?
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		form.setTarikh_surat_kjp(sdf.format(cal.getTime()));
		
		form.setTarikh_permohonan(sdf.format((cal.getTime()))); 

		//form.setTarikh_permohonan(permohonan.get("tarikhPermohonan")); 
		form.setTujuan(permohonan.get("namaProjek"));
		form.setTujuan_dalam_english(permohonan.get("namaProjekBI"));
		//form.set
		return form;
		
	}
	
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
				//updateFlagHantar(idPermohonan, cal.getTime(), response,db, idPengguna);
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
				//updateFlagHantar(idPermohonan, cal.getTime(), response, db, idPengguna);
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
				//updateFlagHantar(idPermohonan, cal.getTime(), response, db, idPengguna);
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
	
	private void updateFlagHantar(String idPermohonan, Date tarikhHantar, String response, Db db, String idPengguna) {
		String sql = "";	
		try {
			Statement stmt = db.getStatement();
			
			sql = "UPDATE TBLINTANAHPERMOHONAN "
				+ "SET FLAG_HANTAR = 'Y'"
				+ ", NO_PERMOHONAN = '"+response+"'"
				+ ", ID_KEMASKINI = '"+idPengguna+"'"
				+ ", TARIKH_KEMASKINI = SYSDATE "
				+ ", TARIKH_HANTAR = SYSDATE "
				+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			stmt.executeUpdate(sql);
			
			SQLRenderer r = new SQLRenderer();
			String idRujukan = String.valueOf(DB.getNextID(db, "INTETANAHPPT_SEQ"));
			r.add("ID_ETANAHPPT", idRujukan);
			r.add("FLAG_URUSAN", URUSAN);
			r.add("NO_PERMOHONAN", response);
//			r.add("NAMA_KEMENTERIAN", permohonan.get("namaKementerian"));
//			r.add("NAMA_PROJEK", permohonan.get("namaProjek"));
			r.add("TARIKH_HANTAR", r.unquote("sysdate"));
			r.add("ID_MASUK", idPengguna);	
			r.add("TARIKH_MASUK", r.unquote("sysdate"));	
			sql = r.getSQLInsert("TBLINTANAHPPT");
			myLog.info("TBLINTANAHPPT:"+sql);
			stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static String getFlagMsg() {
		return flagMsg;
	}

//	public static void setFlagMsg(String flagMsg) {
//		EtanahWPKLPPTManager.flagMsg = flagMsg;
//	}

	public static String getOutputMsg() {
		return outputMsg;
	}

//	public static void setOutputMsg(String outputMsg) {
//		EtanahWPKLPPTManager.outputMsg = outputMsg;
//	}
	
	private void setPermohonan(Hashtable<String,String> permohonan,String idPengguna, Db db) {
		String sql = "";
		String idPermohonan = permohonan.get("idPermohonan");
		
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT * FROM TBLINTANAHPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next() == false) {
	
				String idRujukan = String.valueOf(DB.getNextID(db, "INTANAHPERMOHONAN_SEQ"));
				r.add("ID_PERMOHONANINT", idRujukan);
				r.add("FLAG_URUSAN", URUSAN);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("NAMA_KEMENTERIAN", permohonan.get("namaKementerian"));
				r.add("NAMA_PROJEK", permohonan.get("namaProjek"));
				r.add("TARIKH_HANTAR", r.unquote("sysdate"));
				r.add("ID_MASUK", idPengguna);	
				r.add("TARIKH_MASUK", r.unquote("sysdate"));	
				sql = r.getSQLInsert("TBLINTANAHPERMOHONAN");
				myLog.info("setPermohonan:"+sql);
				stmt.executeUpdate(sql);
			
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	
}
