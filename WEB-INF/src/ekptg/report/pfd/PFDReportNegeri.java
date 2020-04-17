package ekptg.report.pfd;







import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;
import lebah.db.DbException;
import ekptg.intergration.XEkptgEmailSender;
import ekptg.model.pfd.FrmModelMesyuarat;
import ekptg.report.EkptgReportServlet;


public class PFDReportNegeri extends EkptgReportServlet {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception, DbException {
		String TYPE_FAIL = (String) parameters.get("TYPE");
		
		
		if ("1".equalsIgnoreCase(TYPE_FAIL)) {
			// kulit fail
			super.setReportName("PFD_KulitFail");
			super.setFolderName("pfd");

			String KULITFAIL_ID = (String) parameters.get("KULITFAIL_ID");

			// check whether this ID exists
			String sql = "";
			Db db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			sql = "SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = " + KULITFAIL_ID;
			rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				KULITFAIL_ID = "";
			}
			rs.close();
			parameters.put("KULITFAIL_ID", KULITFAIL_ID);
		} else if ("2".equalsIgnoreCase(TYPE_FAIL)) {
			// senarai fail
			super.setReportName("PFD_SenaraiFail");
			super.setFolderName("pfd");
			
			String SENARAIFAIL_NOFAIL = (String) parameters.get("SENARAIFAIL_NOFAIL");
			String SENARAIFAIL_TAJUKFAIL = (String) parameters.get("SENARAIFAIL_TAJUKFAIL");
			String SENARAIFAIL_IDNEGERI = (String) parameters.get("SENARAIFAIL_IDNEGERI");
			String SENARAIFAIL_IDSEKSYEN = (String) parameters.get("SENARAIFAIL_IDSEKSYEN");
			String SENARAIFAIL_IDSTATUS = (String) parameters.get("SENARAIFAIL_IDSTATUS");
			String SENARAIFAIL_TARIKHDAFTAR = (String) parameters.get("SENARAIFAIL_TARIKHDAFTAR");
			
			SENARAIFAIL_NOFAIL = sanitizeReportField(SENARAIFAIL_NOFAIL);
			SENARAIFAIL_TAJUKFAIL = sanitizeReportField(SENARAIFAIL_TAJUKFAIL);
			
			parameters.put("SENARAIFAIL_NOFAIL", SENARAIFAIL_NOFAIL);
			parameters.put("SENARAIFAIL_TAJUKFAIL", SENARAIFAIL_TAJUKFAIL);
			parameters.put("SENARAIFAIL_IDNEGERI", SENARAIFAIL_IDNEGERI);
			parameters.put("SENARAIFAIL_IDSEKSYEN", SENARAIFAIL_IDSEKSYEN);
			parameters.put("SENARAIFAIL_IDSTATUS", SENARAIFAIL_IDSTATUS);
			parameters.put("SENARAIFAIL_TARIKHDAFTAR", SENARAIFAIL_TARIKHDAFTAR);
		}
		else if ("3".equalsIgnoreCase(TYPE_FAIL)) {
			// senarai mesyuarat
			super.setReportName("PFD_SenaraiMesyuarat");
			super.setFolderName("pfd");

			String SENARAIMESYUARAT_TAJUKMESYUARAT = (String) parameters.get("SENARAIMESYUARAT_TAJUKMESYUARAT");
			String SENARAIMESYUARAT_JENISMESYUARAT = (String) parameters.get("SENARAIMESYUARAT_JENISMESYUARAT");
			String SENARAIMESYUARAT_IDLOKASIMESYUARAT = (String) parameters.get("SENARAIMESYUARAT_IDLOKASIMESYUARAT");
			String SENARAIMESYUARAT_IDSEKSYEN = (String) parameters.get("SENARAIMESYUARAT_IDSEKSYEN");
			String SENARAIMESYUARAT_TARIKHMESYUARAT = (String) parameters.get("SENARAIMESYUARAT_TARIKHMESYUARAT");

			SENARAIMESYUARAT_TAJUKMESYUARAT = sanitizeReportField(SENARAIMESYUARAT_TAJUKMESYUARAT);

			parameters.put("SENARAIMESYUARAT_TAJUKMESYUARAT", SENARAIMESYUARAT_TAJUKMESYUARAT);
			parameters.put("SENARAIMESYUARAT_JENISMESYUARAT", SENARAIMESYUARAT_JENISMESYUARAT);
			parameters.put("SENARAIMESYUARAT_IDLOKASIMESYUARAT", SENARAIMESYUARAT_IDLOKASIMESYUARAT);
			parameters.put("SENARAIMESYUARAT_IDSEKSYEN", SENARAIMESYUARAT_IDSEKSYEN);
			parameters.put("SENARAIMESYUARAT_TARIKHMESYUARAT", SENARAIMESYUARAT_TARIKHMESYUARAT);
		} else if ("4".equalsIgnoreCase(TYPE_FAIL)) {
			super.setReportName("PFD_MinitMesyuaratNegeri");
			super.setFolderName("pfd");
			
			String MINITMESYUARAT_ID = (String) parameters.get("MINITMESYUARAT_ID");
			
			String sql = "";
			Db db = new Db();
			
			try {
				String NO_AGENDA = "", AGENDA_MESYUARAT = "", NO_MINIT = "", MINIT_MESYUARAT = "", NO_SUBMINIT = "", SUBMINIT_MESYUARAT = "", TINDAKAN_MESYUARAT = "", MAKLUMAN_MESYUARAT = "", MINIT_TINDAKAN = "", MINIT_MAKLUMAN = "";
				String PREV_NO_AGENDA = "", PREV_NO_MINIT = "", PREV_NO_SUBMINIT = "";
				String TEXT_BUILD = "";
				Boolean firstData = true;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				sql = "SELECT MA.NO_AGENDA, MA.AGENDA_MESYUARAT, MM.NO_MINIT, MM.MINIT_MESYUARAT, MS.NO_SUBMINIT, MS.SUBMINIT_MESYUARAT, MS.TINDAKAN, MS.MAKLUMAN, MM.TINDAKAN, MM.MAKLUMAN " +
					"FROM TBLPFDMESYUARATAGENDA MA, TBLPFDMESYUARATMINIT MM, TBLPFDMESYUARATSUBMINIT MS " +
					"WHERE MA.ID_AGENDAMESYUARAT = MM.ID_AGENDAMESYUARAT AND MM.ID_MINITMESYUARAT = MS.ID_MINITMESYUARAT(+) " +
					"AND MA.ID_MESYUARAT = " + MINITMESYUARAT_ID + 
					" ORDER BY MA.NO_AGENDA, MM.NO_MINIT, MS.NO_SUBMINIT";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					NO_AGENDA = rs.getString(1) == null ? "" : rs.getString(1);
					AGENDA_MESYUARAT = rs.getString(2) == null ? "" : rs.getString(2);
					NO_MINIT = rs.getString(3) == null ? "" : rs.getString(3);
					MINIT_MESYUARAT = rs.getString(4) == null ? "" : rs.getString(4);
					NO_SUBMINIT = rs.getString(5) == null ? "" : rs.getString(5);
					SUBMINIT_MESYUARAT = rs.getString(6) == null ? "" : rs.getString(6);
					TINDAKAN_MESYUARAT = rs.getString(7) == null ? "" : rs.getString(7);
					MAKLUMAN_MESYUARAT = rs.getString(8) == null ? "" : rs.getString(8);
					MINIT_TINDAKAN = rs.getString(9) == null ? "" : rs.getString(9);
					MINIT_MAKLUMAN = rs.getString(10) == null ? "" : rs.getString(10);
					
					if (!NO_AGENDA.equalsIgnoreCase(PREV_NO_AGENDA)) {
						// view agenda
						PREV_NO_AGENDA = NO_AGENDA;
						PREV_NO_MINIT = "";
						PREV_NO_SUBMINIT = "";
						if (!"".equalsIgnoreCase(AGENDA_MESYUARAT)) {
							if (!firstData) {
								TEXT_BUILD += "\r\n\r\n";
							}
							firstData = false;
							TEXT_BUILD += "\r\n<style isBold='true' pdfFontName='Helvetica-Bold'>" + NO_AGENDA + ".\t\t\t" + AGENDA_MESYUARAT + "</style>";
						}
					}
					if (!NO_MINIT.equalsIgnoreCase(PREV_NO_MINIT)) {
						// view minit
						PREV_NO_MINIT = NO_MINIT;
						if (!"".equalsIgnoreCase(MINIT_MESYUARAT)) {
							TEXT_BUILD += "\r\n\r\n\t\t\t\t\t\t" + NO_AGENDA + "." + NO_MINIT + " " + MINIT_MESYUARAT;
							if (!"".equalsIgnoreCase(MINIT_TINDAKAN)) {
								TEXT_BUILD += "\r\n\r\n\t\t\t\t\t\t<style isBold='true' pdfFontName='Helvetica-Bold'>Tindakan: " + MINIT_TINDAKAN + "</style>";
							} else if (!"".equalsIgnoreCase(MINIT_MAKLUMAN)) {
								TEXT_BUILD += "\r\n\r\n\t\t\t\t\t\t<style isBold='true' pdfFontName='Helvetica-Bold'>Makluman</style>";
							}
						}
					}
					if (!NO_SUBMINIT.equalsIgnoreCase(PREV_NO_SUBMINIT)) {
						// view minit
						PREV_NO_SUBMINIT = NO_SUBMINIT;
						if (!"".equalsIgnoreCase(SUBMINIT_MESYUARAT)) {
							TEXT_BUILD += "\r\n\r\n\t\t\t\t\t\t\t\t\t\t\t\t" + NO_AGENDA + "." + NO_MINIT + "." + NO_SUBMINIT + " " + SUBMINIT_MESYUARAT;
							if (!"".equalsIgnoreCase(TINDAKAN_MESYUARAT)) {
								TEXT_BUILD += "\r\n\t\t\t\t\t\t\t\t\t\t\t\t<style isBold='true' pdfFontName='Helvetica-Bold'>Tindakan: " + TINDAKAN_MESYUARAT + "</style>";
							} else if (!"".equalsIgnoreCase(MAKLUMAN_MESYUARAT)) {
								TEXT_BUILD += "\r\n\t\t\t\t\t\t\t\t\t\t\t\t<style isBold='true' pdfFontName='Helvetica-Bold'>Makluman</style>";
							}
						}
					}
				}
				rs.close();

				// check whether dlm TBLPFDMESYUARATTEMP dah ada ke belum ID ni utk user ni dlm masa 5 minit lepas
				sql = "SELECT ID_TEMP FROM TBLPFDMESYUARATTEMP WHERE ID_MESYUARAT = " + MINITMESYUARAT_ID;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					sql = "DELETE FROM TBLPFDMESYUARATTEMP WHERE ID_TEMP=" + rs.getInt(1);
				}
				rs.close();
				stmt.execute(sql);
				
				
				System.out.println("DATA_MESYUARAT:"+TEXT_BUILD);
				
			/*	SQLRenderer r = new SQLRenderer();
				r.add("TARIKH_SIMPAN", r.unquote("SYSDATE"));
				r.add("ID_MESYUARAT", MINITMESYUARAT_ID);
				r.add("DATA_MESYUARAT", TEXT_BUILD);
				sql = r.getSQLInsert("TBLPFDMESYUARATTEMP");
				stmt.execute(sql);
				*/
				
			/*	sql = " INSERT INTO TBLPFDMESYUARATTEMP (TARIKH_SIMPAN,ID_MESYUARAT,DATA_MESYUARAT) "
				+"   VALUES (SYSDATE,'"+MINITMESYUARAT_ID+"', '')";
				stmt.execute(sql);

				*/
				
				
				
				//stmt.close();
			} finally {
				if (db != null) {
					db.close();
				}
			}
			
			MINITMESYUARAT_ID = sanitizeReportField(MINITMESYUARAT_ID);
			parameters.put("MESYUARAT_ID", MINITMESYUARAT_ID);
		} else if ("5".equalsIgnoreCase(TYPE_FAIL)) {
			super.setReportName("PFD_SuratTempahanMakananNegeri");
			super.setFolderName("pfd");
			
			String ID_MESYUARAT = (String) parameters.get("ID_MESYUARAT");
			Date date = new Date();
			String CURRENT_DATE = sdf.format(date);

			String sql = "";
			Db db = new Db();
			
			try {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				sql = "SELECT ID_MESYUARAT " +
					"FROM TBLPFDMESYUARAT " +
					"WHERE ID_MESYUARAT = " + ID_MESYUARAT;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_MESYUARAT = sanitizeReportField(ID_MESYUARAT);
					//CURRENT_DATE = sanitizeReportField(CURRENT_DATE);
					if (FrmModelMesyuarat.isNumeric(ID_MESYUARAT)) {
						parameters.put("ID_MESYUARAT", Integer.parseInt(ID_MESYUARAT));
					}
					parameters.put("CURRENT_DATE", CURRENT_DATE);
				}
				rs.close();
			} finally {
				if (db != null) {
					db.close();
				}
			}

		}else if ("6".equalsIgnoreCase(TYPE_FAIL)) {
			// surat panggilan mesyuarat
			super.setReportName("SuratPanggilanMesyuaratNegeri");
			super.setFolderName("pfd");

			String ID_MESYUARAT = (String) parameters.get("ID_MESYUARAT");
			Integer ID_MESYUARAT_int = Integer.parseInt(ID_MESYUARAT);
			parameters.put("ID_MESYUARAT", ID_MESYUARAT_int);
		}else if ("8".equalsIgnoreCase(TYPE_FAIL)) {
		//maklum balas
			super.setReportName("PFDMaklumbalasMesyuarat");
			super.setFolderName("pfd");
			String ID_MESYUARAT = (String) parameters.get("ID_MESYUARAT");
			parameters.put("ID_MESYUARAT", ID_MESYUARAT);
		}else if ("9".equalsIgnoreCase(TYPE_FAIL)) {
		//lampiran minit mesyuarat
			String ID_MESYUARAT = (String) parameters.get("ID_MESYUARAT");
			parameters.put("ID_MESYUARAT", ID_MESYUARAT);
			super.setReportName("Lampiran_A");
			super.setFolderName("pfd");
		}else if ("10".equalsIgnoreCase(TYPE_FAIL)) {
		//borang kehadiran
			String ID_MESYUARAT = (String) parameters.get("ID_MESYUARAT");
			parameters.put("ID_MESYUARAT", ID_MESYUARAT);
			super.setReportName("BorangKehadiranNegeri");
			super.setFolderName("pfd");
		}else if ("11".equalsIgnoreCase(TYPE_FAIL)) {
		//senarai kehadiran
			super.setReportName("");
			super.setFolderName("pfd");
		}else if ("66".equalsIgnoreCase(TYPE_FAIL)) {
			XEkptgEmailSender email = XEkptgEmailSender.getInstance();
	
			email.FROM ="etapp_webmaster@ekptg.gov.my";
			email.MESSAGE ="<B>TARIKH :?</B>" +
					"<BR><B>MASA : ?</B>"+
					"<BR><B>TEMPAT :?</B>";
			email.SUBJECT="PANGGILAN MESYUARAT :";
			email.MULTIPLE_RECIEPIENT = new String[3];
			email.MULTIPLE_RECIEPIENT[0]="saiful.bakhtiar@hla-group.com";
			email.MULTIPLE_RECIEPIENT[1]="wfaresh@gmail.com";
			email.MULTIPLE_RECIEPIENT[2]="cipon.it@gmail.com";
			
			email.TO_CC = new String[1];
			email.TO_CC[0]  = "cipon.it@gmail.com";
			email.sendEmail();

		}
	}
	
	private static String sanitizeReportField(String REPORT_FIELD) throws Exception {
		String returnValue = "";
		
		try {
			returnValue = REPORT_FIELD;
			returnValue = returnValue.replace("/", "&#47");
			returnValue = returnValue.replace("&", "&#38");
		} finally {
			
		}
		return returnValue;
	}
}