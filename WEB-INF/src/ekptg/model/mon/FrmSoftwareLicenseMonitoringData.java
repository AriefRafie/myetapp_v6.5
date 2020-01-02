package ekptg.model.mon;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.intergration.XEkptgEmailSender;

public class FrmSoftwareLicenseMonitoringData {

	private Vector senaraiPerisian = null;	
	private Vector beanMaklumatPerisian = null;	
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void carian(String txtNamaPerisian, String txtNamaPengeluar, String txtNoSiri,
			String txdTarikhAktif, String txdTarikhLuput, String idKategori, String idStatus, String idNegeri, HttpSession session) throws Exception {

		Db db = null;		
		String sql = "";
		
		try {
			senaraiPerisian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PERISIAN, A.NAMA_PERISIAN, A.PENGELUAR, A.NO_SIRI, A.TARIKH_AKTIF, A.TARIKH_LUPUT, A.ID_KATEGORI,"
				+ " A.FLAG_AKTIF, A.ID_NEGERI, B.KETERANGAN AS NAMA_KATEGORI, C.NAMA_NEGERI"
				+ " FROM TBLMONPERISIAN A, TBLMONRUJKATEGORI B, TBLRUJNEGERI C WHERE A.ID_KATEGORI = B.ID_KATEGORI(+)"
				+ " AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_PERISIAN IS NOT NULL";
				
			//namaPerisian
			if (txtNamaPerisian != null) {
				if (!txtNamaPerisian.trim().equals("")) {
					sql = sql + " AND UPPER(A.NAMA_PERISIAN) LIKE '%' ||'"
							+ txtNamaPerisian.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//namaPengeluar
			if (txtNamaPengeluar != null) {
				if (!txtNamaPengeluar.trim().equals("")) {
					sql = sql + " AND UPPER(A.PENGELUAR) LIKE '%' ||'"
							+ txtNamaPengeluar.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//noSiri
			if (txtNoSiri != null) {
				if (!txtNoSiri.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_SIRI) LIKE '%' ||'"
							+ txtNoSiri.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			
			//tarikhAktif
			if (txdTarikhAktif != null) {
				if (!txdTarikhAktif.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_AKTIF,'dd-MON-YY') = '" + sdf1.format(sdf.parse(txdTarikhAktif)).toUpperCase() +"'";
				}
			}
			
			//tarikhLuput
			if (txdTarikhLuput != null) {
				if (!txdTarikhLuput.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_LUPUT,'dd-MON-YY') = '" + sdf1.format(sdf.parse(txdTarikhLuput)).toUpperCase() +"'";
				}
			}
			
			//idKategori
			if (idKategori != null) {
				if (!idKategori.trim().equals("") && !idKategori.trim().equals("99999")) {
					sql = sql + " AND A.ID_KATEGORI = '" + idKategori.trim() + "'";
				}
			}
			
			//idStatus
			if (idStatus != null) {
				if (!idStatus.trim().equals("") && !idStatus.trim().equals("99999")) {
					sql = sql + " AND A.FLAG_AKTIF = '" + idStatus.trim() + "'";
				}
			}
			
			//idNegeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("") && !idNegeri.trim().equals("99999")) {
					sql = sql + " AND A.ID_NEGERI = '" + idNegeri.trim() + "'";
				}
			}
			
			sql = sql + " ORDER BY NAMA_PERISIAN ASC NULLS LAST ";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPerisian", rs.getString("ID_PERISIAN") == null ? "" : rs.getString("ID_PERISIAN"));
				h.put("namaPerisian", rs.getString("NAMA_PERISIAN") == null ? "" : rs.getString("NAMA_PERISIAN").toUpperCase());
				h.put("namaPengeluar", rs.getString("PENGELUAR") == null ? "" : rs.getString("PENGELUAR").toUpperCase());
				h.put("noSiri", rs.getString("NO_SIRI") == null ? "" : rs.getString("NO_SIRI").toUpperCase());
				h.put("tarikhAktif", rs.getDate("TARIKH_AKTIF") == null ? "" : sdf.format(rs.getDate("TARIKH_AKTIF")));
				h.put("tarikhLuput", rs.getDate("TARIKH_LUPUT") == null ? "" : sdf.format(rs.getDate("TARIKH_LUPUT")));
				h.put("flagAktif", rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));				
				h.put("kategori", rs.getString("NAMA_KATEGORI") == null ? "" : rs.getString("NAMA_KATEGORI"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				
				//NOTIFICATION UNTUK LESEN HAMPIR LUPUT
				int bilHari = 0;
				Calendar currentDate = new GregorianCalendar();
				Calendar dateLuput = new GregorianCalendar();
				Date date = sdf.parse(sdf.format(rs.getDate("TARIKH_LUPUT")));
				dateLuput.setTime(date);

				currentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)); 
				dateLuput.set(dateLuput.get(Calendar.YEAR), dateLuput.get(Calendar.MONTH), dateLuput.get(Calendar.DATE) + 1);
				bilHari = daysBetween(dateLuput.getTime(),currentDate.getTime());
				
				if ("Y".equals(rs.getString("FLAG_AKTIF"))){
					h.put("status", "AKTIF");
					if (bilHari > 90){
						h.put("bilHari", "");
					} else {
						h.put("bilHari", bilHari);						
					}					
				} else if ("T".equals(rs.getString("FLAG_AKTIF"))){
					h.put("status", "LUPUT");
					h.put("bilHari", "");
				} else {
					h.put("status", "");
					h.put("bilHari", "");
				}
					
				senaraiPerisian.addElement(h);
				bil++;				
			}

		} finally {
			if (db != null)
				db.close();
		}		
	}
	
	private int daysBetween(Date date1, Date date2){
		return (int)( (date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
	}

	public String daftarBaru(String txtNamaPerisian, String txtNamaPengeluar, String txtKeterangan, String txtNoSiri, String txtHarga, String txdTarikhBeli,
			String txdTarikhAktif, String txtTempoh, String txdTarikhLuput, String txtBilPengguna, String txtCdKey, String idKategori, HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idPerisianString = "";
		int bilHari = 0;

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLMONPERISIAN
			long idPerisian = DB.getNextID("TBLMONPERISIAN_SEQ");
			idPerisianString = String.valueOf(idPerisian);
			r.add("ID_PERISIAN", idPerisian);
			r.add("ID_KATEGORI", idKategori);
			r.add("ID_NEGERI", getIdNegeriLookup());
			r.add("NAMA_PERISIAN", txtNamaPerisian);
			r.add("PENGELUAR", txtNamaPengeluar);
			r.add("KETERANGAN", txtKeterangan);
			r.add("NO_SIRI", txtNoSiri);
			r.add("NO_SIRI_CAKERA", txtCdKey);
			r.add("HARGA", Utils.RemoveSymbol(txtHarga));
			if (!txdTarikhBeli.isEmpty()){
				r.add("TARIKH_BELI", r.unquote("to_date('" + txdTarikhBeli + "','dd/MM/yyyy')"));
			}	
			if (!txdTarikhAktif.isEmpty()){
				r.add("TARIKH_AKTIF", r.unquote("to_date('" + txdTarikhAktif + "','dd/MM/yyyy')"));
			}
			r.add("TEMPOH", txtTempoh);
			if (!txdTarikhLuput.isEmpty()){
				r.add("TARIKH_LUPUT", r.unquote("to_date('" + txdTarikhLuput + "','dd/MM/yyyy')"));
			}
			r.add("BIL_PENGGUNA", txtBilPengguna);
			
			//SET FLAG AKTIF / LUPUT
			Calendar currentDate = new GregorianCalendar(); 
			Calendar cal = new GregorianCalendar(); 
			Date date = sdf.parse(txdTarikhLuput);
			cal.setTime(date);

			currentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE) + 1);
			bilHari = daysBetween(cal.getTime(),currentDate.getTime());
			
			if (currentDate.getTime().after(cal.getTime())){
				r.add("FLAG_AKTIF", "T");
			} else {
				r.add("FLAG_AKTIF", "Y");
			}
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLMONPERISIAN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	    
	    if (bilHari <= 90 && bilHari > 0){
	    	setEmail(txtNamaPerisian, txtNamaPengeluar, txtNoSiri, txdTarikhLuput, bilHari);
	    }
		return idPerisianString;
	}
	
	public String getIdNegeriLookup() throws Exception {
		Db db = null;
		String sql = "";
		String idNegeri = "";		
		try {
			
			db = new Db();
			Statement stmt = db.getStatement();			
			
			sql = "SELECT B.ID_NEGERI FROM TBLLOOKUPSTATE A, TBLRUJNEGERI B WHERE A.KOD_NEGERI = B.KOD_NEGERI";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				idNegeri = rs.getString("ID_NEGERI");
			}	
			
		} finally {
			if (db != null)
				db.close();
		}
		
		return idNegeri;
	}

	public void setMaklumatPerisian(String idPerisian) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPerisian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERISIAN, NAMA_PERISIAN, PENGELUAR, KETERANGAN, NO_SIRI, HARGA, TARIKH_BELI, TARIKH_AKTIF,"
				+ " TEMPOH, TARIKH_LUPUT, BIL_PENGGUNA, FLAG_AKTIF, NO_SIRI_CAKERA, ID_KATEGORI, ID_NEGERI"
				+ " FROM TBLMONPERISIAN WHERE ID_PERISIAN = '" + idPerisian + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPerisian", rs.getString("ID_PERISIAN") == null ? "" : rs.getString("ID_PERISIAN"));
				h.put("namaPerisian", rs.getString("NAMA_PERISIAN") == null ? "" : rs.getString("NAMA_PERISIAN").toUpperCase());
				h.put("namaPengeluar", rs.getString("PENGELUAR") == null ? "" : rs.getString("PENGELUAR").toUpperCase());
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("noSiri", rs.getString("NO_SIRI") == null ? "" : rs.getString("NO_SIRI").toUpperCase());
				h.put("cdKey", rs.getString("NO_SIRI_CAKERA") == null ? "" : rs.getString("NO_SIRI_CAKERA").toUpperCase());
				h.put("harga", rs.getString("HARGA") == null ? "" : Util.formatDecimal(Double.valueOf(rs.getString("HARGA"))));
				h.put("tarikhBeli", rs.getDate("TARIKH_BELI") == null ? "" : sdf.format(rs.getDate("TARIKH_BELI")));				
				h.put("tarikhAktif", rs.getDate("TARIKH_AKTIF") == null ? "" : sdf.format(rs.getDate("TARIKH_AKTIF")));
				h.put("tempoh", rs.getString("TEMPOH") == null ? "" : rs.getString("TEMPOH"));
				h.put("tarikhLuput", rs.getDate("TARIKH_LUPUT") == null ? "" : sdf.format(rs.getDate("TARIKH_LUPUT")));	
				h.put("bilPengguna", rs.getString("BIL_PENGGUNA") == null ? "" : rs.getString("BIL_PENGGUNA"));
				h.put("flagAktif", rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));				
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));

				//NOTIFICATION UNTUK LESEN HAMPIR LUPUT
				int bilHari = 0;
				Calendar currentDate = new GregorianCalendar();
				Calendar dateLuput = new GregorianCalendar();
				Date date = sdf.parse(sdf.format(rs.getDate("TARIKH_LUPUT")));
				dateLuput.setTime(date);

				currentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)); 
				dateLuput.set(dateLuput.get(Calendar.YEAR), dateLuput.get(Calendar.MONTH), dateLuput.get(Calendar.DATE) + 1);
				bilHari = daysBetween(dateLuput.getTime(),currentDate.getTime());
				
				if ("Y".equals(rs.getString("FLAG_AKTIF"))){
					h.put("status", "AKTIF");
					if (bilHari > 90){
						h.put("bilHari", "");
					} else {
						h.put("bilHari", bilHari);
					}					
				} else if ("T".equals(rs.getString("FLAG_AKTIF"))){
					h.put("status", "LUPUT");
					h.put("bilHari", "");
				} else {
					h.put("status", "");
					h.put("bilHari", "");
				}
				
				beanMaklumatPerisian.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void simpanKemaskini(String idPerisian, String txtNamaPerisian, String txtNamaPengeluar, String txtKeterangan, String txtNoSiri, String txtHarga, String txdTarikhBeli,
			String txdTarikhAktif, String txtTempoh, String txdTarikhLuput, String txtBilPengguna, String txtCdKey, String idKategori, HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLMONPERISIAN
			r.update("ID_PERISIAN", idPerisian);
			
			r.add("ID_KATEGORI", idKategori);
			r.add("ID_NEGERI", getIdNegeriLookup());
			r.add("NAMA_PERISIAN", txtNamaPerisian);
			r.add("PENGELUAR", txtNamaPengeluar);
			r.add("KETERANGAN", txtKeterangan);
			r.add("NO_SIRI", txtNoSiri);
			r.add("NO_SIRI_CAKERA", txtCdKey);
			r.add("HARGA", Utils.RemoveSymbol(txtHarga));
			if (!txdTarikhBeli.isEmpty()){
				r.add("TARIKH_BELI", r.unquote("to_date('" + txdTarikhBeli + "','dd/MM/yyyy')"));
			}	
			if (!txdTarikhAktif.isEmpty()){
				r.add("TARIKH_AKTIF", r.unquote("to_date('" + txdTarikhAktif + "','dd/MM/yyyy')"));
			}
			r.add("TEMPOH", txtTempoh);
			if (!txdTarikhLuput.isEmpty()){
				r.add("TARIKH_LUPUT", r.unquote("to_date('" + txdTarikhLuput + "','dd/MM/yyyy')"));
			}
			r.add("BIL_PENGGUNA", txtBilPengguna);
			
			//SET FLAG AKTIF / LUPUT
			Calendar currentDate = new GregorianCalendar(); 
			Calendar cal = new GregorianCalendar(); 
			Date date = sdf.parse(txdTarikhLuput);
			cal.setTime(date);

			currentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE) + 1);
			
			if (currentDate.getTime().after(cal.getTime())){
				r.add("FLAG_AKTIF", "T");
			} else {
				r.add("FLAG_AKTIF", "Y");
			}
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLMONPERISIAN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}
	
	public void hapus(String idPerisian) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPHPDOKUMEN
			r = new SQLRenderer();	
			r.add("ID_PERISIAN", idPerisian);
			sql = r.getSQLDelete("TBLMONDOKUMEN");
			stmt.executeUpdate(sql);
			
			//TBLMONPERISIAN
			r = new SQLRenderer();	
			r.add("ID_PERISIAN", idPerisian);
			sql = r.getSQLDelete("TBLMONPERISIAN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}	
	}
	
	public Vector getMaklumatImej(String idDokumen) throws Exception {
		Db db = null;
		Vector maklumatImejan = new Vector();
		String sql = "";

		try {
			db = new Db();			
			Statement stmt = db.getStatement();
			
			sql =	"SELECT ID_DOKUMEN, NAMA_DOKUMEN, NAMA_FAIL, CATATAN FROM TBLMONDOKUMEN WHERE ID_DOKUMEN = '" + idDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaImej", rs.getString("NAMA_DOKUMEN")==null ? "" :rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanImej", rs.getString("CATATAN")==null ? "" :rs.getString("CATATAN"));
				h.put("namaFail", rs.getString("NAMA_FAIL")==null ? "" :rs.getString("NAMA_FAIL"));
				maklumatImejan.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return maklumatImejan;
	}
	
	public Vector getSenaraiImejan(String idPerisian) throws Exception {
		Db db = null;
		String sql = "";
		Vector senaraiImejan = new Vector();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			
			db = new Db();
			Statement stmt = db.getStatement();			
			
			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, NAMA_FAIL FROM TBLMONDOKUMEN"
				+ " WHERE ID_PERISIAN = '" + idPerisian + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
		    int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaImej", rs.getString("NAMA_DOKUMEN")==null ? "" : rs.getString("NAMA_DOKUMEN"));
				h.put("catatan", rs.getString("CATATAN")==null ? "" : rs.getString("CATATAN"));
				h.put("namaFail", rs.getString("NAMA_FAIL")==null ? "" : rs.getString("NAMA_FAIL"));
				senaraiImejan.addElement(h);
		    	bil++;
			}	
			
		} finally {
			if (db != null)
				db.close();
		}
		
		return senaraiImejan;
	}
	
	public void simpanKemaskiniDokumen(String idDokumen, String txtNamaImej, String txtCatatan, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
			//TBLPHPDOKUMEN
			r.update("ID_DOKUMEN", idDokumen);
			r.add("NAMA_DOKUMEN", txtNamaImej);
			r.add("CATATAN", txtCatatan);
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLMONDOKUMEN");			
			stmt.executeUpdate(sql);			
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}
	
	public void hapusDokumen(String idDokumen) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			//TBLPHPDOKUMEN
			SQLRenderer r = new SQLRenderer();	
			r.add("ID_DOKUMEN", idDokumen);

			sql = r.getSQLDelete("TBLMONDOKUMEN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}					
	}
	
	/** START EMEL CODE **/
	public static void setEmail(String namaPerisian, String namaPengeluar, String noSiri, String tarikhLuput, int bilHari){
		
		String subject_emel = "eTaPP : Pemberitahuan Tarikh Luput Lesen " + namaPerisian.toUpperCase() + "." ;		
		
		String email_from = "mohdfaizal_hassan@hla-group.com";
		String email_to = "mohdfaizal_hassan@hla-group.com";
			
		XEkptgEmailSender email = XEkptgEmailSender.getInstance();
		//FROM
		email.FROM = email_from;
		//TO
		email.MULTIPLE_RECIEPIENT = new String[1];
		email.MULTIPLE_RECIEPIENT[0] = email_to;
		//CC
		email.TO_CC = new String[1];
		email.TO_CC[0]  = "raiyuzen@gmail.com";
		//SUBJECT
		email.SUBJECT = subject_emel;
		//CONTENT
		email.MESSAGE = setMessage(namaPerisian, namaPengeluar, noSiri, tarikhLuput, bilHari);	
		
		email.sendEmail();
	}
		
	public static String setMessage(String namaPerisian, String namaPengeluar, String noSiri, String tarikhLuput, int bilHari){
		
		StringBuffer bff = new StringBuffer();
		bff.append("<p>");
		bff.append("Assalamualaikum dan salam sejahtera,");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Dengan hormatnya perkara seperti di atas adalah dirujuk.");
		bff.append("<br/>");	
		bff.append("<br/>");	
		
		bff.append("Lesen <font><strong>" + namaPerisian.toUpperCase() + "</strong></font> akan luput dalam tempoh <font color='#FF0000'><strong>" + bilHari + "</strong></font> hari lagi. Berikut adalah maklumat terperinci bagi lesen tersebut :");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp; NAMA LESEN : " + namaPerisian.toUpperCase());
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp; NAMA PENGELUAR : " + namaPengeluar.toUpperCase());
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp; NOMBOR SIRI : " + noSiri.toUpperCase());
		bff.append("<br/>");
		bff.append("&nbsp;&nbsp;&nbsp; TARIKH LUPUT : " + tarikhLuput);
		
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sila <i>login</i> masuk ke www.etapp.gov.my untuk semakan dari pihak tuan/puan.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sekian, Terima Kasih.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Nota : Emel ini dijana oleh Sistem eTaPP dan tidak perlu dibalas.");
		bff.append("</p>");
		return bff.toString();
	}
	/** END EMEL CODE **/

	public Vector getSenaraiPerisian() {
		return senaraiPerisian;
	}

	public void setSenaraiPerisian(Vector senaraiPerisian) {
		this.senaraiPerisian = senaraiPerisian;
	}

	public Vector getBeanMaklumatPerisian() {
		return beanMaklumatPerisian;
	}

	public void setBeanMaklumatPerisian(Vector beanMaklumatPerisian) {
		this.beanMaklumatPerisian = beanMaklumatPerisian;
	}
}
