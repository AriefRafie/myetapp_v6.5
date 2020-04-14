/**
 * 
 */
package integrasi.rest.etanah.wpkl.ppk;

import integrasi.rest.etanah.wpkl.RESTInvoker;
import integrasi.rest.etanah.wpkl.entities.BorangET;
import integrasi.rest.etanah.wpkl.entities.BorangF;
import integrasi.rest.etanah.wpkl.entities.BorangH;
import integrasi.rest.etanah.wpkl.entities.Hakmilik;
import integrasi.rest.etanah.wpkl.entities.HakmilikPerintah;
import integrasi.rest.etanah.wpkl.entities.ParamForm;
import integrasi.rest.etanah.wpkl.entities.Perintah;
import integrasi.rest.etanah.wpkl.entities.PihakBerkepentinganList;
import integrasi.rest.etanah.wpkl.entities.ResponseForm;
import integrasi.rest.etanah.wpkl.entities.UrusanList;

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

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

/**
 * @author Mohd Faizal
 *
 */
public class EtanahWPKLPPKManager {
	
	
	private static String flagMsg = "";
	private static String outputMsg = "";
	private static String semakanPemilikMsg = "";
	
	//START 1ST POINT OF INTERGRATION - GET MAKLUMAT HAKMILIK FROM ETANAH
	public Hakmilik getMaklumatHakmilikFromEtanah(String idHakmilik, String noResit, String idPengguna, String idPermohonanSimati) {
		Hakmilik hakmilik = null;
		//DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "CAPAIAN HAKMILIK BERJAYA";
		try {			
			ParamForm param = new ParamForm();
			param.setIdHakmilik(idHakmilik);
			param.setNoResit(noResit);
			
			hakmilik = RESTInvoker.getMaklumatHakmilik(idPengguna, idHakmilik, noResit);
			if (hakmilik != null) {
				if ("BATAL".equalsIgnoreCase(hakmilik.getStatusHakmilik())) {
					flagMsg = "N";
					outputMsg = "STATUS HAKMILIK ADALAH BATAL DI SISTEM E-TANAH";
				}
				semakanPemilikHakmilik(hakmilik, idPermohonanSimati);
			} else {
				flagMsg = "N";
				outputMsg = "HAKMILIK TIDAK WUJUD DI SISTEM E-TANAH";
			}
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}
		
		return hakmilik;
	}

	private static void semakanPemilikHakmilik(Hakmilik hakmilik, String idPermohonanSimati) {
		semakanPemilikMsg = "";
		boolean simatiIsPemilik = false;
		try {
			String noKPSimati = getNoKPSimati(idPermohonanSimati);
			if (noKPSimati != null) {
				if (hakmilik.getPihakBerkepentinganList() != null) {	
					for (PihakBerkepentinganList pb : hakmilik.getPihakBerkepentinganList()){
						if (noKPSimati.equalsIgnoreCase(Utils.RemoveDash(pb.getNoPengenalanPB()))) {
							//SIMATI ADALAH PEMILIK MELALUI IC
							simatiIsPemilik = true;
						} else {
							String namaSimati = getNamaSimati(idPermohonanSimati);
							if (namaSimati.toUpperCase().contains(pb.getNamaPB().toUpperCase()) || pb.getNamaPB().toUpperCase().contains(namaSimati.toUpperCase())) {
								//SIMATI ADALAH PEMILIK MELALUI NAMA
								simatiIsPemilik = true;
							}
						}
					}
					if (!simatiIsPemilik) {
						semakanPemilikMsg = "SIMATI TIDAK DIDAFTARKAN SEBAGAI PEMILIK HAKMILIK.";
					}
				} else {
					semakanPemilikMsg = "HAKMILIK TIADA PEMILIK / PIHAK BERKEPENTINGAN.";
				}
			} else {
				semakanPemilikMsg = "NO. PENGENALAN SIMATI TIDAK DIDAFTARKAN.";
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void insertMaklumatHakmilik(String idPermohonanSimati, Hakmilik hakmilik, String nama, String noPengenalan, String ba, String bb, String idHakmilik, String noResit, HttpSession session) {		
		String userId = (String) session.getAttribute("_ekptg_user_id");
		
		String gadaian = "";
		String noPerserahanGadaian = "";
		String pajakan = "";
		String noPerserahanPajakan = "";
		String kaveat = "";
		String noPerserahanKaveat = "";
		String sql = "";
		
		Db db = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();			
			
			if (hakmilik.getUrusanList() != null) {		
				for (int i = 0; i < hakmilik.getUrusanList().size(); i++){
					UrusanList urusan = hakmilik.getUrusanList().get(i);
					if (urusan.getNoPerserahan() != null){
						
						//GADAIAN
						if (isUrusan(urusan.getJenisUrusan().toUpperCase(), "G")) {
							if (gadaian.equals("")) {
								gadaian = urusan.getJenisUrusan();
								noPerserahanGadaian = urusan.getNoPerserahan();
							} else {
								gadaian = gadaian + ", " + urusan.getJenisUrusan();
								noPerserahanGadaian = noPerserahanGadaian + ", " + urusan.getNoPerserahan();
							}
						}
						
						//PAJAKAN
						if (isUrusan(urusan.getJenisUrusan().toUpperCase(), "P")) {
							if (pajakan.equals("")) {
								pajakan = urusan.getJenisUrusan();
								noPerserahanPajakan = urusan.getNoPerserahan();
							} else {
								pajakan = pajakan + ", " + urusan.getJenisUrusan();
								noPerserahanPajakan = noPerserahanPajakan + ", " + urusan.getNoPerserahan();
							}
						}
						
						//KAVEAT
						if (isUrusan(urusan.getJenisUrusan().toUpperCase(), "K")) {
							if (kaveat.equals("")) {
								kaveat = urusan.getJenisUrusan();
								noPerserahanKaveat = urusan.getNoPerserahan();
							} else {
								kaveat = kaveat + ", " + urusan.getJenisUrusan();
								noPerserahanKaveat = noPerserahanKaveat + ", " + urusan.getNoPerserahan();
							}
						}
					}
				}
			}
			
			//INSERT
			long idHTA = DB.getNextID("TBLPPKHTA_SEQ");
			r.add("ID_HTA", idHTA);
			r.add("ID_PERMOHONANSIMATI", idPermohonanSimati);
			r.add("ID_SIMATI", getIdSimati(idPermohonanSimati, db));
			r.add("NO_HAKMILIK", hakmilik.getNoHakmilik());
			r.add("NO_PT", hakmilik.getNoPTNoLot());
			r.add("ID_KATEGORI", getIdKategoriTanah(hakmilik.getIdKategoriTanah().toUpperCase()));
			r.add("ID_JENISHM", getIdJenisHakmilik(hakmilik.getIdJenisHakmilik().toUpperCase()));
			r.add("ID_JENISPB", 1);
			r.add("ID_NEGERI", getIdNegeri(hakmilik.getIdNegeri().toUpperCase()));
			r.add("ID_DAERAH", getIdDaerah(hakmilik.getIdNegeri().toUpperCase(), hakmilik.getIdDaerah().toUpperCase()));
			r.add("ID_MUKIM", getIdMukim(hakmilik.getIdNegeri().toUpperCase(), hakmilik.getIdDaerah().toUpperCase(), hakmilik.getIdMukim().toUpperCase()));	
			r.add("ID_LUAS", getIdLuas(hakmilik.getUnitLuas().toUpperCase()));
			r.add("LUAS", hakmilik.getLuas());
			r.add("LUAS_HMP", hakmilik.getLuas());
			r.add("NO_PERSERAHAN", cleanDataString(noPerserahanPajakan));	
			r.add("CATATAN", cleanDataString(hakmilik.getCatatan()));					
			r.add("BA_SIMATI", ba);
			r.add("BB_SIMATI", bb);
			r.add("JENIS_HTA", "Y");
			r.add("SYARAT_NYATA", cleanDataString(hakmilik.getSyaratNyata()));
			r.add("SEKATAN", cleanDataString(hakmilik.getSekatan()));
			r.add("NO_RESIT_CARIAN", noResit);
			r.add("ID_HAKMILIK_ETANAH", idHakmilik);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKHTA");
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
			long idHTAPermohonan = DB.getNextID("TBLPPKHTAPERMOHONAN_SEQ");
			r.add("ID_HTAPERMOHONAN", idHTAPermohonan);
			r.add("ID_HTA", idHTA);
			r.add("ID_PERMOHONANSIMATI", idPermohonanSimati);
			r.add("ID_SIMATI", getIdSimati(idPermohonanSimati, db));
			r.add("NO_HAKMILIK", hakmilik.getNoHakmilik());
			r.add("NO_PT", hakmilik.getNoPTNoLot());
			r.add("ID_KATEGORI", getIdKategoriTanah(hakmilik.getIdKategoriTanah().toUpperCase()));
			r.add("ID_JENISHM", getIdJenisHakmilik(hakmilik.getIdJenisHakmilik().toUpperCase()));
			r.add("ID_JENISPB", 1);
			r.add("ID_NEGERI", getIdNegeri(hakmilik.getIdNegeri().toUpperCase()));
			r.add("ID_DAERAH", getIdDaerah(hakmilik.getIdNegeri().toUpperCase(), hakmilik.getIdDaerah().toUpperCase()));
			r.add("ID_MUKIM", getIdMukim(hakmilik.getIdNegeri().toUpperCase(), hakmilik.getIdDaerah().toUpperCase(), hakmilik.getIdMukim().toUpperCase()));	
			r.add("ID_LUAS", getIdLuas(hakmilik.getUnitLuas().toUpperCase()));
			r.add("LUAS", hakmilik.getLuas());
			r.add("LUAS_HMP", hakmilik.getLuas());
			r.add("NO_PERSERAHAN", cleanDataString(noPerserahanPajakan));	
			r.add("CATATAN", cleanDataString(hakmilik.getCatatan()));					
			r.add("BA_SIMATI", ba);
			r.add("BB_SIMATI", bb);
			r.add("JENIS_HTA", "Y");
			r.add("SYARAT_NYATA", cleanDataString(hakmilik.getSyaratNyata()));
			r.add("SEKATAN", cleanDataString(hakmilik.getSekatan()));
			r.add("NO_RESIT_CARIAN", noResit);
			r.add("ID_HAKMILIK_ETANAH", idHakmilik);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKHTAPERMOHONAN");
			stmt.executeUpdate(sql);	
			con.commit();
			
			flagMsg = "Y";
			outputMsg = "MAKLUMAT HAKMILIK BERJAYA DIDAFTARKAN";
			
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private String getIdSimati(String idPermohonanSimati, Db db) throws Exception {
		String sql = "";

		Statement stmt = db.getStatement();
		
		sql = "SELECT * FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()){
			return rs.getString("ID_SIMATI");
		} else {
			return "";
		}	
	}
	
	public static boolean isUrusan(String jenisUrusan, String kategoriUrusan) {
		boolean isUrusan = false;
		Db db = null;
		try {		
			db = new Db();
			Statement stmt = db.getStatement();
			
			String sql = "SELECT ID_URUSAN FROM INT_PPKRUJURUSAN WHERE KATEGORI_URUSAN = '" + kategoriUrusan + "' AND KETERANGAN = '" + jenisUrusan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				isUrusan = true;
			}
		} catch (Exception ex) {
			 ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		return isUrusan;
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
	
	private static String getNamaSimati(String idPermohonanSimati) throws Exception {
		Db db = null;
		String sql = "";
		String namaSimati = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT SM.NAMA_SIMATI FROM TBLPPKPERMOHONANSIMATI PSM, TBLPPKSIMATI SM WHERE PSM.ID_SIMATI = SM.ID_SIMATI AND PSM.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				namaSimati = rs.getString("NAMA_SIMATI");
			} 
			
		} finally {
			if (db != null)
				db.close();
		}
		return namaSimati;
	}
	
	private static String getIdJenisHakmilik(String jenisHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_JENISHAKMILIK FROM TBLRUJJENISHAKMILIK WHERE UPPER(KETERANGAN) = '" + jenisHakmilik + "'";
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
	
	private static String getIdKategoriTanah(String kategoriTanah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_KATEGORI FROM TBLRUJKATEGORI WHERE UPPER(KETERANGAN) = '" + kategoriTanah + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_KATEGORI");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static String getIdNegeri(String negeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_NEGERI FROM TBLRUJNEGERI WHERE UPPER(NAMA_NEGERI) LIKE '%" + negeri + "%'";
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
	
	private static String getIdDaerah(String negeri, String daerah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE ID_NEGERI = '" + getIdNegeri(negeri) + "' AND UPPER(NAMA_DAERAH) LIKE '%" + daerah + "%'";
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
	
	private static String getIdMukim(String negeri, String daerah, String mukim) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_MUKIM FROM TBLRUJMUKIM WHERE ID_DAERAH = '" + getIdDaerah(negeri, daerah) + "' AND UPPER(NAMA_MUKIM) LIKE '%" + mukim + "%'";
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
	
	private static String getIdLuas(String unitLuas) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_LUAS FROM TBLRUJLUAS WHERE UPPER(KETERANGAN) = '" + unitLuas + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_LUAS");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
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
	
	//START 2ND POINT OF INTERGRATION - SEND MAKLUMAT PERINTAH TO ETANAH
	public static void sendPerintahToEtanah(String noFail, String idPengguna) {
		//DEFAULT MSG
		flagMsg = "Y";
		outputMsg = "MAKLUMAT PERINTAH BERJAYA DIHANTAR";
		
		Perintah perintah = null;
		
		try {
			perintah = preparePerintahForEtanah(noFail);	
			ResponseForm response = RESTInvoker.hantarPerintah(perintah, idPengguna);
			if (response != null) {
				updateTarikhHantarPerintah(noFail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		}	
	}
	
	private static void updateTarikhHantarPerintah(String noFail) {
		String sql = "";
		Db db = null;		
		try {
			db = new Db();			
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_PPKPERINTAH FROM INT_PPKPERINTAH WHERE NO_FAIL = '" + noFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				sql = "UPDATE INT_PPKHAKMILIKPERINTAH SET FLAG_HANTAR = 'Y', TARIKH_HANTAR = SYSDATE WHERE NEGERI = '14' AND ID_PPKPERINTAH = '" + rs.getString("ID_PPKPERINTAH") + "'";
				stmt.executeUpdate(sql);
				con.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}	
	}

	private static Perintah preparePerintahForEtanah(String noFail) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
				perintah.setNoFailPTG(rs.getString("NO_FAIL"));
				perintah.setNamaSiMati(rs.getString("NAMA_SIMATI"));
				perintah.setNoKpSiMati(rs.getString("NO_KPSIMATI"));
				
				Date tarikhMati = sdf.parse(rs.getString("TARIKH_MATI"));
				Calendar calMati = new GregorianCalendar();
				calMati.setTime(tarikhMati);
				String tarikhMatiString = String.valueOf(calMati.get(Calendar.YEAR)) + "-" 
						+ new DecimalFormat("00").format(calMati.get(Calendar.MONTH) + 1) + "-" 
						+ new DecimalFormat("00").format(calMati.get(Calendar.DATE))
						+ "T00:00:00";				
				
				perintah.setTarikhMati(tarikhMatiString);
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
				perintah.setNegeriBicara(getNegeriByIdNegeri(rs.getString("NEGERI_BICARA")));
				perintah.setPegawaiBicara(rs.getString("PEGAWAI_BICARA"));
				
				Date tarikhPerintah = sdf.parse(rs.getString("TARIKH_PERINTAH"));
				Calendar calPerintah = new GregorianCalendar();
				calPerintah.setTime(tarikhPerintah);
				String tarikhPerintahString = String.valueOf(calPerintah.get(Calendar.YEAR)) + "-" 
						+ new DecimalFormat("00").format(calPerintah.get(Calendar.MONTH) + 1) + "-" 
						+ new DecimalFormat("00").format(calPerintah.get(Calendar.DATE))
						+ "T00:00:00";	
				
				perintah.setTarikhPerintah(tarikhPerintahString);	
				
				sql = "SELECT * FROM INT_PPKHAKMILIKPERINTAH WHERE ID_PPKPERINTAH = '" + rs.getString("ID_PPKPERINTAH") + "' AND NEGERI = '14'";
				ResultSet rsHakmilik = stmt.executeQuery(sql);
				
				List<HakmilikPerintah> listHakmilikPerintah = new ArrayList<>();
				while (rsHakmilik.next()) {
					listHakmilikPerintah.add(getHakmilikPerintah(rsHakmilik.getString("ID_PPKHAKMILIKPERINTAH")));
				}
				perintah.setHakmilikPerintahList(listHakmilikPerintah);					
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
				hakmilikPerintah.setIdJenisHakmilik(getKodJenisHakmilikByIdJenisHakmilik(rs.getString("ID_JENISHAKMILIK")));
				hakmilikPerintah.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				hakmilikPerintah.setIdLuas(getLuasByIdLuas(rs.getString("ID_LUAS")));
				hakmilikPerintah.setLuas(rs.getString("LUAS"));					
				hakmilikPerintah.setIdNegeri(getNegeriByIdNegeri(rs.getString("NEGERI")));
				hakmilikPerintah.setIdDaerah(getDaerahByIdDaerah(rs.getString("DAERAH")));
				hakmilikPerintah.setIdMukim(getMukimByIdMukim(rs.getString("MUKIM")));
				hakmilikPerintah.setNoLot(rs.getString("NO_LOT"));							
				hakmilikPerintah.setBaSimati(rs.getString("BA_SIMATI"));
				hakmilikPerintah.setBbSimati(rs.getString("BB_SIMATI"));
				hakmilikPerintah.setJenisPembahagian(rs.getString("JENIS_PEMBAHAGIAN"));
				
				idPPKHakmilikPerintah = rs.getString("ID_PPKHAKMILIKPERINTAH");
				
				//BORANG E
				sql = "SELECT * FROM INT_PPKBORANGE WHERE ID_PPKHAKMILIKPERINTAH = '" + idPPKHakmilikPerintah + "'";
				ResultSet rsBorangET = stmt.executeQuery(sql);
				
				List<BorangET> listBorangET = new ArrayList<>();
				while (rsBorangET.next()) {
					listBorangET.add(getBorangET(rsBorangET.getString("ID_PPKBORANGE")));					 
				}
				hakmilikPerintah.setBorangETList(listBorangET);
				 
				//BORANG F
				sql = "SELECT * FROM INT_PPKBORANGF WHERE ID_PPKHAKMILIKPERINTAH = '" + idPPKHakmilikPerintah + "'";
				ResultSet rsBorangF = stmt.executeQuery(sql);
				
				List<BorangF> listBorangF = new ArrayList<>();
				while (rsBorangF.next()) {
						listBorangF.add(getBorangF(rsBorangF.getString("ID_PPKBORANGF")));	
					}
					hakmilikPerintah.setBorangFList(listBorangF); 
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

	private static BorangET getBorangET(String idBorangET) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		Db db = null;
		BorangET borangET = null;
		
		try {

			db = new Db();			
			Statement stmt = db.getStatement();
			
			sql = "SELECT * FROM INT_PPKBORANGE WHERE ID_PPKBORANGE  = '" + idBorangET + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				borangET = new BorangET();
				borangET.setNamaOB(rs.getString("NAMA_OB"));
				borangET.setJenisPengenalan(getJenisPengenalanByIdJenisPengenalan(rs.getString("JENIS_PENGENALAN")));
				borangET.setNoKpOB(rs.getString("NO_KPOB"));				
				borangET.setStatusOB(rs.getString("STATUS_OB"));
				borangET.setBa(rs.getString("BA_WARIS"));
				borangET.setBb(rs.getString("BB_WARIS"));				
				borangET.setAlamat1(rs.getString("ALAMAT1"));
				borangET.setAlamat2(rs.getString("ALAMAT2"));
				borangET.setAlamat3(rs.getString("ALAMAT3"));
				borangET.setPoskod(rs.getString("POSKOD"));
				borangET.setBandar(rs.getString("BANDAR").toUpperCase());
				borangET.setNegeri(getNegeriByKodNegeri(rs.getString("NEGERI")));				
				borangET.setWarganegara(rs.getString("WARGANEGARA"));
				borangET.setUmur(rs.getString("UMUR"));
				
				if(rs.getString("TARIKH_LAHIR") != null && !rs.getString("TARIKH_LAHIR").equals("")) {
					Date tarikhLahir = sdf.parse(rs.getString("TARIKH_LAHIR"));
					Calendar calLahir = new GregorianCalendar();
					calLahir.setTime(tarikhLahir);
					String tarikhLahirString = String.valueOf(calLahir.get(Calendar.YEAR)) + "-" 
							+ new DecimalFormat("00").format(calLahir.get(Calendar.MONTH) + 1) + "-" 
							+ new DecimalFormat("00").format(calLahir.get(Calendar.DATE))
							+ "T00:00:00";	
					
					borangET.setTarikhLahir(tarikhLahirString);
				}
				
				borangET.setJantina(rs.getString("JANTINA"));
				borangET.setIdHubungan(rs.getString("ID_TARAFKPTG"));
				
				//BORANG H
				sql = "SELECT * FROM INT_PPKBORANGH WHERE ID_PPKBORANGE = '" + rs.getString("ID_PPKBORANGE") + "'";
				ResultSet rsBorangH = stmt.executeQuery(sql);
				 
				List<BorangH> listBorangH = new ArrayList<>();
				while (rsBorangH.next()) {
					listBorangH.add(getBorangH(rsBorangH.getString("ID_PPKBORANGH")));	
				}	
				borangET.setBorangHList(listBorangH);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flagMsg = "N";
			outputMsg = e.toString();
		} finally {
			if (db != null)
				db.close();
		}
		
		return borangET;
	}
	
	private static BorangH getBorangH(String idBorangH) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
				borangH.setJenisPengenalan(getJenisPengenalanByIdJenisPengenalan(rs.getString("JENIS_PENGENALAN")));
				borangH.setNoKpOB(rs.getString("NO_KPOB"));				
				borangH.setAlamat1(rs.getString("ALAMAT1"));
				borangH.setAlamat2(rs.getString("ALAMAT2"));
				borangH.setAlamat3(rs.getString("ALAMAT3"));
				borangH.setPoskod(rs.getString("POSKOD"));
				borangH.setBandar(rs.getString("BANDAR"));
				borangH.setNegeri(getNegeriByKodNegeri(rs.getString("NEGERI")));		
				borangH.setWarganegara(rs.getString("WARGANEGARA"));
				borangH.setUmur(rs.getString("UMUR"));
				
				if(rs.getString("TARIKH_LAHIR") != null && !rs.getString("TARIKH_LAHIR").equals("")) {
					Date tarikhLahir = sdf.parse(rs.getString("TARIKH_LAHIR"));
					Calendar calLahir = new GregorianCalendar();
					calLahir.setTime(tarikhLahir);
					String tarikhLahirString = String.valueOf(calLahir.get(Calendar.YEAR)) + "-" 
							+ new DecimalFormat("00").format(calLahir.get(Calendar.MONTH) + 1) + "-" 
							+ new DecimalFormat("00").format(calLahir.get(Calendar.DATE))
							+ "T00:00:00";	
					
					borangH.setTarikhLahir(tarikhLahirString);
				}
				
				borangH.setJantina(rs.getString("JANTINA"));
				borangH.setIdHubungan(rs.getString("ID_TARAFKPTG"));
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
				borangF.setJenisPengenalan(getJenisPengenalanByIdJenisPengenalan(rs.getString("JENIS_PENGENALAN")));
				borangF.setNoKpOB(rs.getString("NO_KPOB"));				
				borangF.setAlamat1(rs.getString("ALAMAT1"));
				borangF.setAlamat2(rs.getString("ALAMAT2"));
				borangF.setAlamat3(rs.getString("ALAMAT3"));
				borangF.setPoskod(rs.getString("POSKOD"));
				borangF.setBandar(rs.getString("BANDAR"));
				borangF.setNegeri(getNegeriByKodNegeri(rs.getString("NEGERI")));			
				borangF.setWarganegara(rs.getString("WARGANEGARA"));
				borangF.setUmur(rs.getString("UMUR"));
				
				if(rs.getString("TARIKH_LAHIR") != null && !rs.getString("TARIKH_LAHIR").equals("")) {
					Date tarikhLahir = sdf.parse(rs.getString("TARIKH_LAHIR"));
					Calendar calLahir = new GregorianCalendar();
					calLahir.setTime(tarikhLahir);
					String tarikhLahirString = String.valueOf(calLahir.get(Calendar.YEAR)) + "-" 
							+ new DecimalFormat("00").format(calLahir.get(Calendar.MONTH) + 1) + "-" 
							+ new DecimalFormat("00").format(calLahir.get(Calendar.DATE))
							+ "T00:00:00";	
					
					borangF.setTarikhLahir(tarikhLahirString);
				}
				
				borangF.setJantina(rs.getString("JANTINA"));
				borangF.setIdHubungan(rs.getString("ID_TARAFKPTG"));
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
	
	private static String getNegeriByIdNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI = '" + idNegeri + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("NAMA_NEGERI");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static String getNegeriByKodNegeri(String kodNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT NAMA_NEGERI FROM TBLRUJNEGERI WHERE KOD_NEGERI = '" + kodNegeri + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("NAMA_NEGERI");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static String getDaerahByIdDaerah(String idDaerah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT NAMA_DAERAH FROM TBLRUJDAERAH WHERE ID_DAERAH = '" + idDaerah + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("NAMA_DAERAH");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static String getMukimByIdMukim(String idMukim) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT NAMA_MUKIM FROM TBLRUJMUKIM WHERE ID_MUKIM = '" + idMukim + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("NAMA_MUKIM");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static String getKodJenisHakmilikByIdJenisHakmilik(String idJenisHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK = '" + idJenisHakmilik + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("KOD_JENIS_HAKMILIK");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static String getLuasByIdLuas(String idLuas) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT KETERANGAN FROM TBLRUJLUAS WHERE ID_LUAS = '" + idLuas + "'";
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
	
	private static String getJenisPengenalanByIdJenisPengenalan(String idJenisPengenalan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT KETERANGAN FROM TBLRUJJENISNOPB WHERE ID_JENISNOPB = '" + idJenisPengenalan + "'";
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

	public String getFlagMsg() {
		return flagMsg;
	}

	public static void setFlagMsg(String flagMsg) {
		EtanahWPKLPPKManager.flagMsg = flagMsg;
	}

	public String getOutputMsg() {
		return outputMsg;
	}

	public static void setOutputMsg(String outputMsg) {
		EtanahWPKLPPKManager.outputMsg = outputMsg;
	}

	public String getSemakanPemilikMsg() {
		return semakanPemilikMsg;
	}

	public static void setSemakanPemilikMsg(String semakanPemilikMsg) {
		EtanahWPKLPPKManager.semakanPemilikMsg = semakanPemilikMsg;
	}		
}
