package ekptg.view.online;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.db.AuthenticateUser;
import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

import ekptg.engine.IPortalUtility;
import ekptg.engine.PortalUtility;
import ekptg.intergration.XEkptgEmailSender;
import ekptg.model.online.FrmKemaskiniPenggunaData;
import ekptg.model.online.aduan.Complaint;
import ekptg.model.online.aduan.ComplaintType;

public class Portal implements IServlet2 {
	static Logger myLogger = Logger.getLogger(Portal.class);

	@Override
	public void doService(HttpServletRequest request
		,HttpServletResponse response
		,ServletContext context) throws IOException, ServletException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String contextPath = request.getContextPath();
		String nama = request.getParameter("nama");
		String emel = request.getParameter("emel");
		String password = request.getParameter("password");
		String NoKPBaru = request.getParameter("txtNoKPBaru");
		String submit = request.getParameter("command");
		String id_negeri = request.getParameter("id_negeri");
		String idKategori = request.getParameter("idKategori");
		String username = "";
		
		//diba tambah
		String alamat1 = request.getParameter("alamat1");
		String alamat2 = request.getParameter("alamat2");
		String alamat3 = request.getParameter("alamat3");
		String poskod = request.getParameter("poskod");

		username = NoKPBaru;
//		myLogger.info("submit:"+submit);
		if ("doLogin".equals(submit)) {
			try {
//				myLogger.debug("doUserLogin=" + username);
				doUserLogin(username, password, session, out);
			} catch (Exception e) {
				out.println("error :" + e.getMessage());
			}
		} else if ("doCheckUsername".equals(submit)) {
			FrmKemaskiniPenggunaData userdata = FrmKemaskiniPenggunaData
					.getInstance();
			if (userdata.isUserNamePenggunaExist(username)) {
				out.println("Maaf, ID \"" + username + "\" sudah wujud!");
			}
		} else if ("doCheckNoKp".equals(submit)) {
			FrmKemaskiniPenggunaData userdata = FrmKemaskiniPenggunaData
					.getInstance();
			if (userdata.isNoKPPenggunaExist(NoKPBaru)) {
				out.println("Maaf, No.KP \"" + NoKPBaru + "\" sudah wujud!");
				session.setAttribute("doCheckNoKp", context);
			}
		} else if ("doRegister".equals(submit)) {
			String code = (String) session.getAttribute("verification.code");
			String attempt = request.getParameter("txtCaptChar");
			// myLogger.info("doRegister");
			if (code.equals(attempt)) {
				FrmKemaskiniPenggunaData userdata = FrmKemaskiniPenggunaData
						.getInstance();
				// myLogger.info("code match");
				try {
					userdata.addBasicPengguna(nama, username, password,
							NoKPBaru, id_negeri, emel, idKategori, alamat1, alamat2, alamat3, poskod);
					out.println("Pendaftaran berjaya,sila tunggu...");
					out.println("<script>doEffect();</script>");
					out.println("<script>doHide();</script>");
					doUserLogin(username, password, session, out);
				} catch (Exception e) {
					out.println("Pendaftaran tidak berjaya:" + e.getMessage());
				}
			} else {
				// myLogger.info("code pengesahan:"+code);
				// myLogger.info("attempt:"+attempt);
				out.println("Kod Pengesahan salah. Sila masukkan kod pengesahan dengan betul.");
				out.println("<script>doEffect();</script>");
			}

		} else if ("doReminderPassword".equals(submit)) {
			String email = request.getParameter("email");
			String noKP = request.getParameter("noKP");
			try {
				//checking email existing
			boolean emelExist = checkEmail (session,email,noKP);	
			
			if (emelExist == true){
				
				//out.println("emel ini wujud di dalam sistem " + email);
				doSendEmail(noKP, email);
				out.println("<br> Sila periksa email anda - " + email);
				//
			}else {
				out.println("Emel "+ email +" tidak wujud di dalam sistem. " );
				out.println("<br>Sila hubungi Pentadbir Sistem melalui emel etappsupport@jkptg.gov.my berhubung masalah anda. ");
			}
				//doSendEmail(noKP, email);
			} catch (Exception e) {
				e.printStackTrace();
			}
			myLogger.info("email:" + email);
			
			out.println("<script>doHide();</script>");
		} else if ("doAduan".equals(submit)) {

			submitAduan(request, out);
		} else if ("doClearResult".equals(submit)) {
			out.println("");
		} else {
			out.println("Nothing to be done");
		}
		out.close();
	}

	private void submitAduan(HttpServletRequest request, PrintWriter out) {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String idJenisAduan = request.getParameter("idJenisAduan");
		String catatan = request.getParameter("catatan");
		String phone = request.getParameter("phone");
		Complaint complaint = new Complaint();
		ComplaintType type = new ComplaintType();
		type.setId(idJenisAduan);
		complaint.setCatatan(catatan);
		complaint.setNamaPengadu(name);
		complaint.setType(type);
		complaint.setPhonePengadu(phone);
		complaint.setEmailPengadu(email);
		// IEkptgPublicComplaintHandler handler = new
		// EkptgPublicComplaintHandler();
		// handler.processComplaint(complaint);
		out.println("Pendaftaran aduan & cadangan berjaya, segala aduan anda akan diproses secepat mungkin");
		out.println("<script>doEffect();</script>");
		out.println("<script>doHide();</script>");
	}

	public void doUserLogin(String username, String password,
			HttpSession session, PrintWriter out) throws Exception {
		AuthenticateUser auth = new AuthenticateUser();
		try {
			if (auth.lookup(username, password, "online")) {
				//razman add
				session.setAttribute("afterLogin", "Y");
				out.println("<script>$jquery('#ajaxindicator').show();</script>");
				out.println("Welcome " + username + "..please wait");
				session.setAttribute("nickname", username);
				session.setAttribute("_portal_role", auth.getRole());
				session.setAttribute("_portal_username", auth.getUserName());
				session.setAttribute("_portal_login", auth.getUserLogin());
				session.setAttribute("_portal_islogin", "true");
				session.setAttribute("_ekptg_user_negeri", auth.getUserNegeri());
				session.setAttribute("_ekptg_user_id", auth.getUserID());
				session.setAttribute("_ekptg_loginType", "online");
				out.println("<script>window.parent.location='../online/c';</script>");
			} else {
				out.println("Sorry , invalid login or password<br>");
				out.println("<script>");
				out.println("doEffect();");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doSendEmail(String noKP, String email) throws Exception {

		StringBuffer msg = new StringBuffer();
		String newPassword = getPortalUtility().resetPassword(noKP, email);
		if (newPassword != null) {
			msg.append("Kata laluan anda telah di dipadamkan sila guna kata laluan baru seperti dibawah :<BR>");
			msg.append("<B>Kata Laluan Baru Anda :</B>" + newPassword);
			msg.append("<BR> Terima kasih kerana menggunakan Sistem eTaPP");
			XEkptgEmailSender sendMail = XEkptgEmailSender.getInstance();
			sendMail.MULTIPLE_RECIEPIENT = new String[1];
			sendMail.MULTIPLE_RECIEPIENT[0] = email;
			//TUKAR KPD ETAPP SUPPORT sendMail.FROM = "etapp_webmaster@ekptg.gov.my";
			sendMail.FROM = "etappsupport@jkptg.gov.my";
			sendMail.SUBJECT = "PERKHIDMATAN ONLINE eTaPP :- LUPA KATA LALUAN";
			sendMail.MESSAGE = msg.toString();
			sendMail.sendEmail();
		}
	}

	private IPortalUtility getPortalUtility() {
		IPortalUtility iPortalUtility = new PortalUtility();
		return iPortalUtility;
	}
	
	public boolean checkEmail(HttpSession session, String email, String nokp) throws Exception {
		
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		boolean check = true;
		
		String user_id = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
				sql = 	" SELECT UI.USER_ID FROM USERS_INTERNAL UI " +
						" WHERE UI.EMEL LIKE '%"+email+"%'" ;
				
				myLogger.info(" CHECK EMAIL EXIST :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					
					user_id = rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID");
					if(!user_id.equals(""))
					{
						check = true;
					}	
				}
			
			return check;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
}
