
package ekptg.model.php2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
//test 13/8/2020
import ekptg.model.utils.emel.EmailConfig;

public class FrmPhpNotifikasiEmel {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	String tajukFail = "";

	public void sendEmailtoKJP(String idPermohonan, String idUlasanTeknikal, String idKementerian, HttpSession session) 
			throws Exception {
		Db db = null;
		Connection conn = null;
		Vector beanMaklumatEmail = null;
		EmailSender email = EmailSender.getInstance();
		String sql = "";
		String tajukFail = "";
		String noFail = "";
		String pemohon = "";
		String tempoh = "";
		String tarikhHantar = "";
		String urusan = "";
		String pemilikTanah = "";
		String tujuan = "";
		String kegunaanTanah = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT A.TARIKH_HANTAR, A.JANGKAMASA, C.NO_FAIL, C.TAJUK_FAIL, D.NAMA, E.NAMA_SUBURUSAN,"
				+ " F.FLAG_GUNA, F.CADANGAN_KEGUNAAN"
				+ " FROM TBLPHPULASANTEKNIKAL A, TBLPERMOHONAN B, TBLPFDFAIL C, TBLPHPPEMOHON D, TBLRUJSUBURUSAN E,"
				+ " TBLPHPPERMOHONANPELEPASAN F"
				+ " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SUBURUSAN = E.ID_SUBURUSAN"
				+ " AND B.ID_PERMOHONAN = F.ID_PERMOHONAN AND B.ID_PEMOHON = D.ID_PEMOHON AND A.ID_ULASANTEKNIKAL = "
				+ "'"+idUlasanTeknikal+"'";
			
			ResultSet rsEmel = stmt.executeQuery(sql);
			if (rsEmel.next()){
				tajukFail = rsEmel.getString("TAJUK_FAIL") == null ? "" :
								rsEmel.getString("TAJUK_FAIL");
				noFail = rsEmel.getString("NO_FAIL") == null ? "" :
							rsEmel.getString("NO_FAIL");
				pemohon = rsEmel.getString("NAMA") == null ? "" :
							rsEmel.getString("NAMA");
				tempoh = rsEmel.getString("JANGKAMASA") == null ? "" :
							rsEmel.getString("JANGKAMASA");
				tarikhHantar = sdf.format(rsEmel.getDate("TARIKH_HANTAR"));
				urusan = rsEmel.getString("NAMA_SUBURUSAN") == null ? "" :
							rsEmel.getString("NAMA_SUBURUSAN");
				tujuan = rsEmel.getString("CADANGAN_KEGUNAAN") == null ? "" :
							rsEmel.getString("CADANGAN_KEGUNAAN");
				if("1".equals(rsEmel.getString("FLAG_GUNA"))) {
					kegunaanTanah = "keseluruhan";
				} else if("2".equals(rsEmel.getString("FLAG_GUNA"))) {
					kegunaanTanah = "sebahagian";
				}
			}	
			
			//TO GET USER EMAIL BY ROLE idKementerian
//			sql = "SELECT UI.EMEL FROM USERS U, USERS_INTERNAL UI, USERS_KEMENTERIAN UK "
//				+ "WHERE U.USER_ID = UI.USER_ID AND U.USER_ID = UK.USER_ID "
//				+ "AND UK.ID_KEMENTERIAN IN ('"+idKementerian+"') ";
//			  
//			ResultSet rsPenerimaEmel = stmt.executeQuery(sql);
//			  
//			List<String> strPenerima = new ArrayList<String>(); 
//			while (rsPenerimaEmel.next()) {
//				if (rsPenerimaEmel.getString("EMEL") != null && rsPenerimaEmel.getString("EMEL").trim().length() > 0) {
//					strPenerima.add(rsPenerimaEmel.getString("EMEL")); 
//				}
//			}
//			email.MULTIPLE_RECIEPIENT = new String[strPenerima.size()];
//			  
//			for (int i = 0; i < strPenerima.size(); i++) { 
//				email.MULTIPLE_RECIEPIENT[i] = strPenerima.get(i); 
//			}
			 
			email.RECIEPIENT = "nurulain.siprotech@gmail.com"; //untuk testing sementara
			email.SUBJECT = "PERMOHONAN MENGEMUKAKAN LAPORAN TANAH URUSAN "+urusan+" BAGI NO. FAIL " + noFail;
			if (urusan.contains("PELEPASAN")) {
				email.MESSAGE =  "Tuan/ Puan,"
						+ "<br><br><u><b>"+tajukFail+"</b></u>"
						+ "<br><br>Dengan hormatnya saya diarah untuk menarik perhatian tuan kepada perkara di atas "
						+ "daripada " +pemohon+ "."
						+ "<br><br>2.	Dimaklumkan bahawa Jabatan ini telah menerima permohonan pelepasan lot tersebut di atas."
						+ "Tanah yang terlibat merupakan tanah di bawah kawalan " +pemilikTanah+ "."
						+ "<br><br>3.	Sehubungan dengan itu, pihak tuan dipohon untuk memberi ulasan mengenai "
						+ "permohonan tersebut dalam tempoh " +tempoh+ " hari sebelum " +tarikhHantar+ ". "
						+ "Kerjasama dan tindakan awal daripada pihak tuan/ puan amatlah dihargai."
						+ "<br><br>Sekian, terima kasih.<br><br><br>"			
						+ "Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
				
			} else {
				email.MESSAGE =  "Tuan/ Puan,"
						+ "<br><br><u><b>"+tajukFail+"</b></u>"
						+ "<br><br>Dengan hormatnya saya diarah untuk menarik perhatian tuan kepada perkara di atas"
						+ "<br><br>2.	Untuk makluman pihak tuan, lot tersebut di atas adalah tanah milik Persekutuan "
						+ "di bawah kawalan " +pemilikTanah+ ". " +pemohon+ " telah mengemukakan permohonan untuk "
						+ "menukarguna "+kegunaanTanah+" tanah tersebut bagi tujuan " +tujuan+ "."
						+ "<br><br>3.	Sehubungan dengan itu, pihak tuan dipohon untuk memberi ulasan mengenai "
						+ "permohonan tukarguna tersebut dalam tempoh " +tempoh+ " hari sebelum " +tarikhHantar+ ". "
						+ "Kerjasama dan tindakan awal daripada pihak tuan/ puan amatlah dihargai."
						+ "<br><br>Sekian, terima kasih.<br><br><br>"			
						+ "Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
			}
			email.sendEmail();
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void sendEmailtoPejabatJKPTG(String idUlasanTeknikal, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		Vector beanMaklumatEmail = null;
		EmailSender email = EmailSender.getInstance();
		String sql = "";
		String tajuk = "";
		String noFail = "";
		String penawar = "";
		String tempoh = "";
		String tarikhHantar = "";
		String urusan = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT A.TARIKH_HANTAR, A.JANGKAMASA, C.NO_FAIL, C.TAJUK_FAIL, D.NAMA, E.NAMA_SUBURUSAN"
				+ " FROM TBLPHPULASANTEKNIKAL A, TBLPERMOHONAN B, TBLPFDFAIL C, TBLPHPPEMOHON D, TBLRUJSUBURUSAN E"
				+ " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SUBURUSAN = E.ID_SUBURUSAN"
				+ " AND B.ID_PEMOHON = D.ID_PEMOHON AND A.ID_ULASANTEKNIKAL = '"+idUlasanTeknikal+"'";
			
			ResultSet rsEmel = stmt.executeQuery(sql);
			if (rsEmel.next()){
				tajuk = rsEmel.getString("TAJUK_FAIL") == null ? "" :
							rsEmel.getString("TAJUK_FAIL");
				noFail = rsEmel.getString("NO_FAIL") == null ? "" :
							rsEmel.getString("NO_FAIL");
				penawar = rsEmel.getString("NAMA") == null ? "" :
							rsEmel.getString("NAMA");
				tempoh = rsEmel.getString("JANGKAMASA") == null ? "" :
							rsEmel.getString("JANGKAMASA");
				tarikhHantar = sdf.format(rsEmel.getDate("TARIKH_HANTAR"));
				urusan = rsEmel.getString("NAMA_SUBURUSAN") == null ? "" :
					rsEmel.getString("NAMA_SUBURUSAN");
			}
			
			//TO GET USER EMAIL BY ROLE (PHP)PYWPenolongPegawaiTanahNegeri
//			sql = "SELECT U.USER_ID, U.USER_LOGIN, U.USER_ROLE AS USER_ROLE, UI.EMEL "
//				+ "FROM USERS U, USERS_INTERNAL UI "
//				+ "WHERE U.USER_ID = UI.USER_ID AND U.USER_ROLE = '(PHP)PYWPenolongPegawaiTanahNegeri' "
//				+ "UNION "
//				+ "SELECT U.USER_ID, U.USER_LOGIN, UR.ROLE_ID AS USER_ROLE, UI.EMEL "
//				+ "FROM USERS U, USER_ROLE UR, USERS_INTERNAL UI "
//				+ "WHERE U.USER_LOGIN = UR.USER_ID AND U.USER_ID = UI.USER_ID "
//				+ "AND UR.ROLE_ID = '(PHP)PYWPenolongPegawaiTanahNegeri'";
//			
//			ResultSet rsPenerimaEmel = stmt.executeQuery(sql);
//			
//			List<String> strPenerima = new ArrayList<String>(); 
//			while (rsPenerimaEmel.next()) {
//				if (rsPenerimaEmel.getString("EMEL") != null && rsPenerimaEmel.getString("EMEL").trim().length() > 0) {
//					strPenerima.add(rsPenerimaEmel.getString("EMEL")); 
//				} 			 
//			}
//			email.MULTIPLE_RECIEPIENT = new String[strPenerima.size()];
//			  
//			for (int i = 0; i < strPenerima.size(); i++) {
//				email.MULTIPLE_RECIEPIENT[i] = strPenerima.get(i); 
//			}
			email.RECIEPIENT = "nurulain.siprotech@gmail.com"; //untuk testing sementara
			email.SUBJECT = "PERMOHONAN MENGEMUKAKAN LAPORAN TANAH URUSAN "+urusan+" BAGI NO. FAIL " + noFail;
			email.MESSAGE =  "Tuan/ Puan,"
							 +"<br><br><u><b>"+tajuk+"</b></u>"
							 + "<br><br>2.	Adalah dimaklumkan bahawa "+penawar+" selaku Jabatan/Agensi pengawal "
							 + "lot di atas berhasrat untuk menawarkan tapak tersebut kepada mana-mana "
							 + "agensi yang berminat. Oleh yang demikian, pihak tuan dimohon untuk "
							 + "mengemukakan laporan tanah berhubung keadaan terkini tapak tersebut dalam tempoh "
							 + tempoh + " hari sebelum " + tarikhHantar + "."
							 + " <br><br>Sekian, terima kasih.<br><br><br>"			
							 + " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
			email.sendEmail();
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void sendEmailtoMOF(String idPermohonan, String idKementerian, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		Vector beanMaklumatEmail = null;
		EmailSender email = EmailSender.getInstance();
		EmailConfig conf = new EmailConfig();
		
		String sql = "";
		String emelUser = "nurulain.siprotech@gmail.com"; //untuk sementara
		String noFail = "";
		String tarikhHantar = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT A.TARIKH_HANTAR_KEWANGAN, D.NO_FAIL, D.TAJUK_FAIL "
				+ "FROM TBLPHPKERTASKERJAPELEPASAN A, TBLPERMOHONAN C, TBLPFDFAIL D "
				+ "WHERE A.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_FAIL = D.ID_FAIL "
				+ "AND A.FLAG_KERTAS = 2 AND C.ID_PERMOHONAN = '"+idPermohonan+"'";
			
			ResultSet rsEmel = stmt.executeQuery(sql);
			if (rsEmel.next()){
				noFail = rsEmel.getString("NO_FAIL");
				tajukFail = rsEmel.getString("TAJUK_FAIL");
				//tempoh = rsEmel.getString("JANGKAMASA");
				tarikhHantar = sdf.format(rsEmel.getDate("TARIKH_HANTAR_KEWANGAN"));
			}	
			
			String tajuk = "PERMOHONAN MENGEMUKAKAN ULASAN KERTAS CADANGAN URUSAN PELEPASAN BAGI NO. FAIL " + noFail;
			String kandungan = "Tuan/ Puan,"
					 		 +"<br><br><u><b>"+tajukFail+"</b></u>"
							 + "Adalah saya dengan hormatnya merujuk kepada perkara di atas."
							 + "<br><br>2.	Sukacita dimaklumkan bahawa pihak kami memerlukan kelulusan "
							 + "daripada Y.A.B Menteri Kewangan Malaysia sebelum " +tarikhHantar+ "."
							 + "<br>3.	Kerjasama dari pihak tuan amatlah diharapkan dan dihargai.";
			
			conf.sendByKJPPenyedia(idKementerian, "", emelUser, tajuk, kandungan);
			//email.sendEmail();
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void sendEmailtoJPPH(String idPermohonan, String idKementerian, HttpSession session) throws Exception {
		
	}
	
	public void sendEmailtoKJPAPB(String idUlasanTeknikal, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		Vector beanMaklumatEmail = null;
		EmailSender email = EmailSender.getInstance();
		String sql = "";
		String tajuk = "";
		String noFail = "";
		String namaPelesen = "";
		String namaJabatan = "";
		String tempoh = "";
		String tarikhHantar = "";
		String tarikhTerima = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT TBLPHPULASANTEKNIKAL.ID_ULASANTEKNIKAL, TBLPHPULASANTEKNIKAL.FLAG_KJP, TBLPHPULASANTEKNIKAL.JANGKAMASA, "
				+ "TBLPHPULASANTEKNIKAL.TARIKH_HANTAR, TBLPHPULASANTEKNIKAL.TARIKH_JANGKA_TERIMA, "
				+ "TBLPFDFAIL.NO_FAIL, TBLPFDFAIL.TAJUK_FAIL, TBLPHPPEMOHON.NAMA "
				+ "FROM TBLPHPULASANTEKNIKAL, TBLPERMOHONAN, TBLPHPPEMOHON, TBLPFDFAIL "
				+ "WHERE TBLPHPULASANTEKNIKAL.ID_PERMOHONAN = TBLPERMOHONAN.ID_PERMOHONAN "
				+ "AND TBLPERMOHONAN.ID_PEMOHON = TBLPHPPEMOHON.ID_PEMOHON AND TBLPERMOHONAN.ID_FAIL = TBLPFDFAIL.ID_FAIL "
				+ "AND TBLPHPULASANTEKNIKAL.ID_ULASANTEKNIKAL =  '"+idUlasanTeknikal+"'";
			
			ResultSet rsEmel = stmt.executeQuery(sql);
			if (rsEmel.next()){
				tajuk = rsEmel.getString("TAJUK_FAIL") == null ? "" :
							rsEmel.getString("TAJUK_FAIL");
				noFail = rsEmel.getString("NO_FAIL") == null ? "" :
							rsEmel.getString("NO_FAIL");
				namaPelesen = rsEmel.getString("NAMA") == null ? "" :
							rsEmel.getString("NAMA");
				namaJabatan = rsEmel.getString("FLAG_KJP") == null ? "" :
					rsEmel.getString("FLAG_KJP");
				tempoh = rsEmel.getString("JANGKAMASA") == null ? "" :
							rsEmel.getString("JANGKAMASA");
				tarikhHantar = sdf.format(rsEmel.getDate("TARIKH_HANTAR"));
				tarikhTerima = sdf.format(rsEmel.getDate("TARIKH_JANGKA_TERIMA"));
			}
			
			//TO GET USER EMAIL BY ROLE (PHP)PYWPenolongPegawaiTanahNegeri
//			sql = "SELECT U.USER_ID, U.USER_LOGIN, U.USER_ROLE AS USER_ROLE, UI.EMEL "
//				+ "FROM USERS U, USERS_INTERNAL UI "
//				+ "WHERE U.USER_ID = UI.USER_ID AND U.USER_ROLE = '(PHP)PYWPenolongPegawaiTanahNegeri' "
//				+ "UNION "
//				+ "SELECT U.USER_ID, U.USER_LOGIN, UR.ROLE_ID AS USER_ROLE, UI.EMEL "
//				+ "FROM USERS U, USER_ROLE UR, USERS_INTERNAL UI "
//				+ "WHERE U.USER_LOGIN = UR.USER_ID AND U.USER_ID = UI.USER_ID "
//				+ "AND UR.ROLE_ID = '(PHP)PYWPenolongPegawaiTanahNegeri'";
//			
//			ResultSet rsPenerimaEmel = stmt.executeQuery(sql);
//			
//			List<String> strPenerima = new ArrayList<String>(); 
//			while (rsPenerimaEmel.next()) {
//				if (rsPenerimaEmel.getString("EMEL") != null && rsPenerimaEmel.getString("EMEL").trim().length() > 0) {
//					strPenerima.add(rsPenerimaEmel.getString("EMEL")); 
//				} 			 
//			}
//			email.MULTIPLE_RECIEPIENT = new String[strPenerima.size()];
//			  
//			for (int i = 0; i < strPenerima.size(); i++) {
//				email.MULTIPLE_RECIEPIENT[i] = strPenerima.get(i); 
//			}
			email.RECIEPIENT = "nurulain.siprotech@gmail.com"; //untuk testing sementara
			email.SUBJECT = "Mohon Ulasan " +namaJabatan+ " untuk " +namaPelesen+ " (" +noFail+ ")";
			email.MESSAGE =  "Assalamualaikum dan Salam Sejahtera"
							 +"<br><br>Tuan/ Puan,"
							 +"<br><br><u><b>" +tajuk.toUpperCase()+ "</b></u>"
							 +"<br>Dengan hormatnya saya diarah merujuk berhubung perkara di atas."
							 +"<br><br>2.		Sukacita dipohon pihak tuan/puan mengemukakan maklum balas dan ulasan "
							 +"bagi permohonan tersebut kepada Jabatan ini melalui Sistem MyeTaPP "
							 +"sebelum atau pada <u>"+tarikhTerima+ "</u> untuk tindakan Jabatan selanjutnya."
							 +"<br><br>3.		Kerjasama dan maklum balas awal pihak tuan/puan berhubung perkara tersebut amat dihargai."
							 +"<br><br><br>Sekian, terima kasih.<br><br><br>"
							 +"Unit Maritim"
							 +"<br>Bahagian Penguatkuasa dan Hasil Persekutuan"
							 +"<br>Jabatan Ketua Pengarah Tanah dan Galian Persekutuan<br><br><br>"
							 +"Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
			email.sendEmail();
			
		} finally {
			if (db != null)
				db.close();
		}
	}
}