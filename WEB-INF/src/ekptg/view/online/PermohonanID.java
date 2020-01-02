package ekptg.view.online;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.db.AuthenticateUser;
import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.DB;
import ekptg.model.online.aduan.Complaint;
import ekptg.model.online.aduan.ComplaintType;

public class PermohonanID implements IServlet2 {
	static Logger myLogger = Logger.getLogger(Portal.class);
	Hashtable viewPengguna = null;
	List listPenggunaMengikutRole = null;
	Hashtable viewStaff = null;
	
	@Override
	public void doService(HttpServletRequest request,
			HttpServletResponse response, ServletContext context)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String contextPath = request.getContextPath();
		String submit = request.getParameter("command");
		
		
		myLogger.info("submit:"+submit);
		if ("doRegister".equals(submit)) {
			String code = (String) session.getAttribute("verification.code");
			String attempt = request.getParameter("txtCaptChar");
			
			String script = "<script> resetSkrin(); </script>";
			
			
			if (code.equals(attempt)) {
				try {
					
					
					String NOKP1 = request.getParameter("NOKP1");
					String NOKP2 = request.getParameter("NOKP2");
					String NOKP3 = request.getParameter("NOKP3");
					String fullid = NOKP1+NOKP2+NOKP3;
					String getCheckFlag = checkPermohonanID(session, fullid);
					
					if(getCheckFlag.equals(""))
					{
					savePengguna(session,request);
					out.println("<font color='blue' class='blink'>Permohonan Anda Akan Dihantar Untuk Pengesahan. " +
							"Maklumbalas Akan Dihantar Kepada Emel Pemohon Untuk Tindakan Lanjut</a> </font>");
					out.println("<script>doEffect();</script>");
					out.println("<script>doHide();</script>"+script);
					}
					else
					{
						if(getCheckFlag.equals("1"))
						{
							out.println("<font color='blue' class='blink'>Permohonan ID ("+NOKP1+"-"+NOKP2+"-"+NOKP3+") Telah Wujud Dan Sedang Diproses!</font>");
							out.println("<script>doEffect();resetSkrin();</script>");
						}
						else if(getCheckFlag.equals("2"))
						{
							out.println("<font color='red' class='blink'>Permohonan ID ("+NOKP1+"-"+NOKP2+"-"+NOKP3+") Telah Ditolak, Notifikasi Telah Dihantar Ke Emel Anda!</font>");
							out.println("<script>doEffect();resetSkrin();</script>");
						}
						else if(getCheckFlag.equals("3"))
						{
							out.println("<font color='blue' class='blink'>Permohonan ID ("+NOKP1+"-"+NOKP2+"-"+NOKP3+") Telah Diluluskan, Notifikasi Telah Dihantar Ke Emel Anda!</font>");
							out.println("<script>doEffect();resetSkrin();</script>");
						}
					}
					
					
					
				} catch (Exception e) {
					out.println("Pendaftaran tidak berjaya:" + e.getMessage());
				}
			} else {				
				out.println("<font color='red' class='blink'>Kod Pengesahan Tidak Tepat. Sila Masukkan Kod Pengesahan Yang Sah!</font>");
				out.println("<script>doEffect();</script>");
			}
		}
		else if ("doGetNegeri".equals(submit)) {
			String selectNegeri = "<select style='width:100%' id='ID_NEGERI' name='ID_NEGERI'  onChange='getBahagian();' >";
			selectNegeri += "<option value='' >SILA PILIH</option>";
			try {
			
			   List listNegeri = listNegeri();
			   
			   for(int i = 0; i < listNegeri.size();i++)
			   {
				   Map m = (Map) listNegeri.get(i);
				   selectNegeri += "<option value='"+m.get("ID")+"' >"+m.get("KETERANGAN")+"</option>";
			   }
			   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			selectNegeri += "</select >";
				out.println(selectNegeri);
			
		}
		
		else if ("doGetJawatan".equals(submit)) {
			String selectNegeri = "<select style='width:100%' id='ID_JAWATAN' name='ID_JAWATAN'  onChange='getKumpKhidmat();getKlasifikasi();getGred();' >";
			selectNegeri += "<option value='' >SILA PILIH</option>";
			try {
			
			   List listJawatan = listJawatan();
			   
			   for(int i = 0; i < listJawatan.size();i++)
			   {
				   Map m = (Map) listJawatan.get(i);
				   selectNegeri += "<option value='"+m.get("ID")+"' >"+m.get("KETERANGAN")+"</option>";
			   }
			   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			selectNegeri += "</select >";
				out.println(selectNegeri);
			
		}
		
		
		
		else if ("doGetBahagian".equals(submit)) {
			String selectBahagian = "<select style='width:100%' id='ID_BAHAGIAN' name='ID_BAHAGIAN'  onChange='getUnit();' >";
			selectBahagian += "<option value='' >SILA PILIH</option>";
			try {
			   String ID_NEGERI = request.getParameter("ID_NEGERI");
			   List listBahagian = listBahagian(ID_NEGERI);
			   
			   for(int i = 0; i < listBahagian.size();i++)
			   {
				   Map m = (Map) listBahagian.get(i);
				   selectBahagian += "<option value='"+m.get("ID")+"' >"+m.get("KETERANGAN")+"</option>";
			   }
			   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			selectBahagian += "</select >";
				out.println(selectBahagian);
			
		}
		
		
		
		else if ("doGetUnit".equals(submit)) {
			String selectUnit = "<select style='width:100%' id='ID_PEJABAT' name='ID_PEJABAT' onChange='getAlamat();getTel();getFax();' >";
			selectUnit += "<option value='' >SILA PILIH</option>";
			try {
			   String ID_BAHAGIAN = request.getParameter("ID_BAHAGIAN");
			   String ID_NEGERI = request.getParameter("ID_NEGERI");
			   List listUnit = listPejabatJKPTG(ID_BAHAGIAN,ID_NEGERI);
			   
			   for(int i = 0; i < listUnit.size();i++)
			   {
				   Map m = (Map) listUnit.get(i);
				   selectUnit += "<option value='"+m.get("ID_PEJABATJKPTG")+"' >"+m.get("NAMA_UNIT")+"</option>";
			   }
			   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			selectUnit += "</select >";
				out.println(selectUnit);
			
		}
		
		else if ("doGetAlamatPejabat".equals(submit)) {			
					String display_pejabat = "";
					try {
						   String ID_PEJABAT = request.getParameter("ID_PEJABAT");
						   Hashtable pejabat = viewPejabatJKPTG(ID_PEJABAT);
						   String NAMA_UNIT = (String)pejabat.get("NAMA_UNIT");
						   String ALAMAT1 = (String)pejabat.get("ALAMAT1");
						   String ALAMAT2 = (String)pejabat.get("ALAMAT2");
						   String ALAMAT3 = (String)pejabat.get("ALAMAT3");
						   String POSKOD = (String)pejabat.get("POSKOD");
						   String BANDAR = (String)pejabat.get("BANDAR");
						   String NEGERI = (String)pejabat.get("NEGERI");
						   String NO_TEL = (String)pejabat.get("NO_TEL");
						   String NO_FAX = (String)pejabat.get("NO_FAX");
						   display_pejabat += (ALAMAT1 == null || ALAMAT1.equals("") ? "" : ALAMAT1+"<br>");	
						   display_pejabat += (ALAMAT2 == null || ALAMAT2.equals("") ? "" : ALAMAT2+"<br>");	
						   display_pejabat += (ALAMAT3 == null || ALAMAT3.equals("") ? "" : ALAMAT3+"<br>");
						   display_pejabat += (POSKOD == null || POSKOD.equals("") ? "" : POSKOD+"<br>");
						   display_pejabat += (BANDAR == null || BANDAR.equals("") ? "" : BANDAR+"<br>");
						   display_pejabat += (NEGERI == null || NEGERI.equals("") ? "" : NEGERI+"<br>");
						   //display_pejabat += (NO_TEL == null || NO_TEL.equals("") ? "" : "No. Tel : "+NO_TEL+"<br>");
						   //display_pejabat += (NO_FAX == null || NO_FAX.equals("") ? "" : "No. Fax : "+NO_FAX+"<br>");
						   
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					out.println(display_pejabat);
		}
		
		else if ("doGetNotelPejabat".equals(submit)) {			
			String display_notel = "";
			try {
				   String ID_PEJABAT = request.getParameter("ID_PEJABAT");
				   Hashtable pejabat = viewPejabatJKPTG(ID_PEJABAT);
				   /*String NAMA_UNIT = (String)pejabat.get("NAMA_UNIT");
				   String ALAMAT1 = (String)pejabat.get("ALAMAT1");
				   String ALAMAT2 = (String)pejabat.get("ALAMAT2");
				   String ALAMAT3 = (String)pejabat.get("ALAMAT3");
				   String POSKOD = (String)pejabat.get("POSKOD");
				   String BANDAR = (String)pejabat.get("BANDAR");
				   String NEGERI = (String)pejabat.get("NEGERI");*/
				   String NO_TEL = (String)pejabat.get("NO_TEL");
				   //String NO_FAX = (String)pejabat.get("NO_FAX");
				   /*display_pejabat += (ALAMAT1 == null || ALAMAT1.equals("") ? "" : ALAMAT1+"<br>");	
				   display_pejabat += (ALAMAT2 == null || ALAMAT2.equals("") ? "" : ALAMAT2+"<br>");	
				   display_pejabat += (ALAMAT3 == null || ALAMAT3.equals("") ? "" : ALAMAT3+"<br>");
				   display_pejabat += (POSKOD == null || POSKOD.equals("") ? "" : POSKOD+"<br>");
				   display_pejabat += (BANDAR == null || BANDAR.equals("") ? "" : BANDAR+"<br>");
				   display_pejabat += (NEGERI == null || NEGERI.equals("") ? "" : NEGERI+"<br>");*/
				   display_notel += (NO_TEL == null || NO_TEL.equals("") ? "-" : NO_TEL+"");
				   //display_pejabat += (NO_FAX == null || NO_FAX.equals("") ? "" : "No. Fax : "+NO_FAX+"<br>");
				   
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			out.println(display_notel);
		}
		
		else if ("doGetNoFax".equals(submit)) {			
			String display_noFax = "";
			try {
				   String ID_PEJABAT = request.getParameter("ID_PEJABAT");
				   Hashtable pejabat = viewPejabatJKPTG(ID_PEJABAT);
				   /*String NAMA_UNIT = (String)pejabat.get("NAMA_UNIT");
				   String ALAMAT1 = (String)pejabat.get("ALAMAT1");
				   String ALAMAT2 = (String)pejabat.get("ALAMAT2");
				   String ALAMAT3 = (String)pejabat.get("ALAMAT3");
				   String POSKOD = (String)pejabat.get("POSKOD");
				   String BANDAR = (String)pejabat.get("BANDAR");
				   String NEGERI = (String)pejabat.get("NEGERI");*/
				  // String NO_TEL = (String)pejabat.get("NO_TEL");
				   String NO_FAX = (String)pejabat.get("NO_FAX");
				   /*display_pejabat += (ALAMAT1 == null || ALAMAT1.equals("") ? "" : ALAMAT1+"<br>");	
				   display_pejabat += (ALAMAT2 == null || ALAMAT2.equals("") ? "" : ALAMAT2+"<br>");	
				   display_pejabat += (ALAMAT3 == null || ALAMAT3.equals("") ? "" : ALAMAT3+"<br>");
				   display_pejabat += (POSKOD == null || POSKOD.equals("") ? "" : POSKOD+"<br>");
				   display_pejabat += (BANDAR == null || BANDAR.equals("") ? "" : BANDAR+"<br>");
				   display_pejabat += (NEGERI == null || NEGERI.equals("") ? "" : NEGERI+"<br>");*/
				 //  display_notel += (NO_TEL == null || NO_TEL.equals("") ? "-" : NO_TEL+"");
				   display_noFax += (NO_FAX == null || NO_FAX.equals("") ? "-" : "No. Fax : "+NO_FAX+"<br>");
				   
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			out.println(display_noFax);
		}
		
		//diba tambah
		else if ("doGetGred".equals(submit)) {
			String selectGred = "<select style='width:100%' id='ID_GRED' name='ID_GRED' >";
			selectGred += "<option value='' >SILA PILIH</option>";
			
			String ID_JAWATAN = request.getParameter("ID_JAWATAN");
			
			try {
			
			   List listGred = listGred(ID_JAWATAN);
			   
			   for(int i = 0; i < listGred.size();i++)
			   {
				   Map m = (Map) listGred.get(i);
				   selectGred += "<option value='"+m.get("ID")+"' >"+m.get("KOD")+"</option>";
			   }
			   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			selectGred += "</select >";
				out.println(selectGred);
			
		}
		
		else if ("doGetKumpKhidmat".equals(submit)) {
			String selectKumpKhidmat = "<input type='hidden' id='ID_KUMPKHIDMAT' name='ID_KUMPKHIDMAT' ";
			//selectKumpKhidmat += "<option value='' >SILA PILIH</option>";
			
			String ID_JAWATAN = request.getParameter("ID_JAWATAN");
			 String ID_KHIDMAT = "";
			
			try {
				
			    ID_KHIDMAT = getID_KHIDMAT (session, ID_JAWATAN);
			    
			    System.out.println("ID_KHIDMAT :" + ID_KHIDMAT);
			
			   /*List listKumpKhidmat = listKumpKhidmat(ID_KHIDMAT);
			   
			   for(int i = 0; i < listKumpKhidmat.size();i++)
			   {
				   Map m = (Map) listKumpKhidmat.get(i);
				   selectKumpKhidmat += "<option value='"+m.get("ID")+"' >"+m.get("KETERANGAN")+"</option>";
			   }*/
			   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			selectKumpKhidmat += "value='"+ID_KHIDMAT+"'>";
				out.println(selectKumpKhidmat);
			
		}
		else if ("doGetKlasifikasi".equals(submit)) {
			String selectKlasifikasi = "<input type='hidden' id='ID_KLASIFIKASI' name='ID_KLASIFIKASI' ";
			//selectKlasifikasi += "<option value='' >SILA PILIH</option>";
			
			String ID_JAWATAN = request.getParameter("ID_JAWATAN");
			String ID_KLASIFIKASI = "";
			try {
				
			 ID_KLASIFIKASI = getID_KLASIFIKASI (session, ID_JAWATAN);
			
			  /* List listKlasifikasi = listKlasifikasi(ID_KLASIFIKASI);
			   
			   for(int i = 0; i < listKlasifikasi.size();i++)
			   {
				   Map m = (Map) listKlasifikasi.get(i);
				   selectKlasifikasi += "<option value='"+m.get("ID")+"' >"+m.get("KETERANGAN")+"</option>";
			   }*/
			   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			selectKlasifikasi += " value='"+ID_KLASIFIKASI+"'>";
				out.println(selectKlasifikasi);
			
		}
		
		//tambah checking
		else if ("checkUSER_LOGIN".equals(submit)) {
			
			 boolean checkUSER_LOGIN = false;
			 String USER_ID = request.getParameter("USER_LOGIN");
			
			try {
			   
			   System.out.println("USER_ID -- "+ USER_ID);
			   checkUSER_LOGIN = checkUSERINTERNAL(session, USER_ID);
			   
			   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(checkUSER_LOGIN == false){
			 out.println("<font color='red' class='blink' >User Login Pengguna Telah Wujud!</font>");
			} else {
			out.println("<font color='blue' class='blink' >User Login Pengguna Belum Wujud!</font>");
			}
			
		}
		
		
		/*
		else if ("doCheckUsername".equals(submit)) {
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
							NoKPBaru, id_negeri, emel, idKategori);
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
				doSendEmail(noKP, email);
			} catch (Exception e) {
				e.printStackTrace();
			}
			myLogger.info("email:" + email);
			out.println("Sila periksa email anda - " + email);
			out.println("<script>doHide();</script>");
		} else if ("doAduan".equals(submit)) {

			submitAduan(request, out);
		} else if ("doClearResult".equals(submit)) {
			out.println("");
		} else {
			out.println("Nothing to be done");
		}
		*/
		out.close();
	}
	
	public Hashtable viewPejabatJKPTG(String ID_PEJABATJKPTG) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = " SELECT PEJ.ID_PEJABATJKPTG, PEJ.KOD_JKPTG, UPPER(PEJ.NAMA_PEJABAT) AS NAMA_UNIT, "+
					" UPPER(PEJ.ALAMAT1) AS ALAMAT1, UPPER(PEJ.ALAMAT2) AS ALAMAT2, UPPER(PEJ.ALAMAT3) AS ALAMAT3, "+ 
					" PEJ.POSKOD, PEJ.ID_NEGERI, PEJ.ID_BANDAR, UPPER(BAN.KETERANGAN) AS BANDAR, UPPER(NEG.NAMA_NEGERI) AS NEGERI,  "+
					" PEJ.ID_SEKSYEN, PEJ.NO_TEL, PEJ.NO_FAX  "+
					" FROM TBLRUJPEJABATJKPTG PEJ,TBLRUJBANDAR BAN, TBLRUJNEGERI NEG  "+
					" WHERE PEJ.ID_NEGERI = NEG.ID_NEGERI(+)  "+
					" AND PEJ.ID_BANDAR = BAN.ID_BANDAR(+) ";
					sql += " AND PEJ.ID_PEJABATJKPTG = "+ID_PEJABATJKPTG+" ";					
					sql += " ORDER BY PEJ.KOD_JKPTG ";
			myLogger.info(" viewPejabatJKPTG :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			Hashtable h;
			h = new Hashtable();
			
			while (rs.next()) {
				h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
				h.put("KOD_JKPTG",rs.getString("KOD_JKPTG") == null ? "" : rs.getString("KOD_JKPTG"));
				h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT"));
				h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
				h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
				h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
				h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_BANDAR",rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
				h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
				h.put("BANDAR",rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR"));
				h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				h.put("NO_TEL",rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("NO_FAX",rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
			}
			return h;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
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
/*
	public void doSendEmail(String noKP, String email) throws Exception {

		StringBuffer msg = new StringBuffer();
		String newPassword = getPortalUtility().resetPassword(noKP, email);
		if (newPassword != null) {
			msg.append("Kata laluan anda telah di dipadamkan sila guna kata laluan baru seperti dibawah :<BR>");
			msg.append("<B>Kata Laluan Baru Anda :</B>" + newPassword);
			msg.append("<BR> Terima kasih kerana menggunakan Sistem eTaPP");
			EkptgEmailSender sendMail = EkptgEmailSender.getInstance();
			sendMail.MULTIPLE_RECIEPIENT = new String[1];
			sendMail.MULTIPLE_RECIEPIENT[0] = email;
			sendMail.FROM = "etapp_webmaster@ekptg.gov.my";
			sendMail.SUBJECT = "PERKHIDMATAN ONLINE eTaPP :- LUPA KATA LALUAN";
			sendMail.MESSAGE = msg.toString();
			sendMail.sendEmail();
		}
	}

	private IPortalUtility getPortalUtility() {
		IPortalUtility iPortalUtility = new PortalUtility();
		return iPortalUtility;
	}
	*/
	public void savePengguna(HttpSession session,HttpServletRequest request) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		long USER_ID = 0;
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			String NOKP1 = request.getParameter("NOKP1");
			String NOKP2 = request.getParameter("NOKP2");
			String NOKP3 = request.getParameter("NOKP3");
			String NAMA = request.getParameter("NAMA");
			String ID_JAWATAN = request.getParameter("ID_JAWATAN");
			String ID_BAHAGIAN = request.getParameter("ID_BAHAGIAN");
			String ID_NEGERI = request.getParameter("ID_NEGERI");
			String ID_PEJABAT = request.getParameter("ID_PEJABAT");
			String EMELPEMOHON = request.getParameter("EMELPEMOHON");
			String PENYELIA = request.getParameter("PENYELIA");
			String NO_HP = request.getParameter("NO_HP");
			
			//diba tambah
			//String NO_OFFICE = request.getParameter("NO_OFFICE");
			String ID_KHIDMAT = request.getParameter("ID_KUMPKHIDMAT");
			String ID_KLASIFIKASI = request.getParameter("ID_KLASIFIKASI");
			String ID_GRED = request.getParameter("ID_GRED");
			String CATATAN_PEMOHON = request.getParameter("CATATAN_PEMOHON");
			
			USER_ID = DB.getNextID(db, "PERMOHONANIDPENGGUNA_SEQ");
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("USER_ID", USER_ID);
			r.add("NOKP", NOKP1+NOKP2+NOKP3);
			r.add("USER_NAME", NAMA.toUpperCase());
			r.add("ID_JAWATAN", ID_JAWATAN);
			r.add("ID_SEKSYEN", ID_BAHAGIAN);
			r.add("ID_NEGERI", ID_NEGERI);
			r.add("ID_PEJABATJKPTG", ID_PEJABAT);
			r.add("EMEL", EMELPEMOHON);
			r.add("NO_HP", NO_HP);
			r.add("PEGAWAIPENYELIA", PENYELIA.toUpperCase());			
			r.add("TARIKH_PENDAFTARAN", r.unquote("sysdate"));
			//r.add("TARIKH_KEPUTUSAN", "");
			r.add("FLAG_STATUS", 1);//1-PERMOHONAN BARU 2-DITOLAK 3-DITERIMA
			
			//diba tambah
			//r.add("NO_OFFICE", NO_OFFICE);
			r.add("ID_GRED", ID_GRED);
			r.add("ID_KLASIFIKASI", ID_KLASIFIKASI);
			r.add("ID_KHIDMAT", ID_KHIDMAT);
			r.add("CATATAN_PEMOHON", CATATAN_PEMOHON);
			
			sql = r.getSQLInsert("PERMOHONANIDPENGGUNA");		
			myLogger.info("INSERT PERMOHONANIDPENGGUNA : "+sql);
			stmt.executeUpdate(sql);
			
			conn.commit();			
			hantarEmelPengguna(session,USER_ID+"",1);
			hantarEmelStaff(session,USER_ID+"",1);
			
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List listPejabatJKPTG(String ID_SEKSYEN, String ID_NEGERI)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPejabatJKPTG = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT PEJ.ID_PEJABATJKPTG, PEJ.KOD_JKPTG, UPPER(PEJ.NAMA_PEJABAT) AS NAMA_UNIT, "+
					" UPPER(PEJ.ALAMAT1) AS ALAMAT1, UPPER(PEJ.ALAMAT2) AS ALAMAT2, UPPER(PEJ.ALAMAT3) AS ALAMAT3, "+ 
					" PEJ.POSKOD, PEJ.ID_NEGERI, PEJ.ID_BANDAR, UPPER(BAN.KETERANGAN) AS BANDAR, UPPER(NEG.NAMA_NEGERI) AS NEGERI,  "+
					" PEJ.ID_SEKSYEN, PEJ.NO_TEL, PEJ.NO_FAX  "+
					" FROM TBLRUJPEJABATJKPTG PEJ,TBLRUJBANDAR BAN, TBLRUJNEGERI NEG  "+
					" WHERE PEJ.ID_NEGERI = NEG.ID_NEGERI(+)  "+
					" AND PEJ.ID_BANDAR = BAN.ID_BANDAR(+) ";
					if(!ID_NEGERI.equals(""))
					{
						sql += " AND PEJ.ID_NEGERI = "+ID_NEGERI+" ";
					}
					if(!ID_SEKSYEN.equals(""))
					{
						sql += " AND PEJ.ID_SEKSYEN = "+ID_SEKSYEN+" ";
					}
					sql += " ORDER BY PEJ.KOD_JKPTG ";
			myLogger.info(" V3: SQL listPejabatJKPTG :"+ sql);
			rs = stmt.executeQuery(sql);
			listPejabatJKPTG = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
				h.put("KOD_JKPTG",rs.getString("KOD_JKPTG") == null ? "" : rs.getString("KOD_JKPTG"));
				h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT"));
				h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
				h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
				h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
				h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_BANDAR",rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
				h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
				h.put("BANDAR",rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR"));
				h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				h.put("NO_TEL",rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("NO_FAX",rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
				listPejabatJKPTG.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listPejabatJKPTG;

	}
	
	
	
	public List listNegeri() throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTableRujukanV3 = null;
		String sql = "SELECT ID_NEGERI AS ID, KOD_NEGERI AS KOD, UPPER(NAMA_NEGERI) AS KETERANGAN FROM TBLRUJNEGERI ";
				sql += " WHERE ID_NEGERI IS NOT NULL"+
						" AND ID_NEGERI NOT IN (0,17)";
		
		try {
			db = new Db();
			stmt = db.getStatement();rs = stmt.executeQuery(sql);
			listTableRujukanV3 = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				listTableRujukanV3.add(h);
			}

		}
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listTableRujukanV3;

	}
	
	public List listJawatan() throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTableRujukanV3 = null;
		String sql = " SELECT ID_JAWATAN AS ID, KOD_JAWATAN AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJJAWATAN WHERE KOD_JAWATAN != '00' ORDER BY KOD_JAWATAN ";
		
		try {
			db = new Db();
			stmt = db.getStatement();rs = stmt.executeQuery(sql);
			listTableRujukanV3 = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				listTableRujukanV3.add(h);
			}

		}
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listTableRujukanV3;

	}
	
	//diba tambah
	public List listGred(String ID_JAWATAN) throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTableRujukanV3 = null;
		
		String sql = 	" SELECT G.ID_GRED AS ID, G.NAMA_GRED AS KOD, UPPER(G.KETERANGAN) AS KETERANGAN " +
						" FROM TBLRUJGRED G, TBLRUJGREDJAWATAN GJ WHERE GJ.ID_JAWATAN = " +ID_JAWATAN +
						" AND G.ID_GRED = GJ.ID_GRED ";
		
		myLogger.info("gred : "+ sql);
		
		try {
			db = new Db();
			stmt = db.getStatement();rs = stmt.executeQuery(sql);
			listTableRujukanV3 = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				listTableRujukanV3.add(h);
			}

		}

		
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listTableRujukanV3;

	} 
	
	public List listKumpKhidmat(String ID_KHIDMAT) throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTableRujukanV3 = null;
		
		String sql = 	" SELECT KH.ID_KHIDMAT AS ID,  KH.NAMA_KHIDMAT AS KETERANGAN FROM "+
			       		" TBLRUJKHIDMAT KH ";
		
		if (!ID_KHIDMAT.equals("")){
			sql+=	" WHERE ID_KHIDMAT = "+ID_KHIDMAT;
		}
		
		try {
			db = new Db();
			stmt = db.getStatement();rs = stmt.executeQuery(sql);
			listTableRujukanV3 = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				//h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				listTableRujukanV3.add(h);
			}

		}
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listTableRujukanV3;

	}
	
	public List listKlasifikasi(String ID_KLASIFIKASI) throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTableRujukanV3 = null;
		
		String sql = 	" SELECT ID_KLASIFIKASI AS ID, UPPER(NAMA_KLASIFIKASI) AS KETERANGAN " +
						" FROM TBLRUJKLASIFIKASI " +
						" WHERE ID_KLASIFIKASI IS NOT NULL ";
		
		if(!ID_KLASIFIKASI.equals("")){
			sql+= 	" AND ID_KLASIFIKASI = "+ ID_KLASIFIKASI;
		}
		
			sql+=	" ORDER BY NAMA_KLASIFIKASI" ;
		
		try {
			db = new Db();
			stmt = db.getStatement();rs = stmt.executeQuery(sql);
			listTableRujukanV3 = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				//h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				listTableRujukanV3.add(h);
			}

		}
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listTableRujukanV3;

	}
	
	/*
	public void hantarEmel(HttpSession session,String ID_PERMOHONAN,Integer STATUS) throws Exception {

			myLogger.info(" ID_PERMOHONAN : "+ID_PERMOHONAN);
			//UserListModuleV3 ULMV3 = new UserListModuleV3();
			viewPengguna = viewDataPenggunaMOHON(session, ID_PERMOHONAN);
			myLogger.info(" viewPengguna : "+viewPengguna);
			StringBuffer msg = new StringBuffer();		
			msg.append("Kata laluan anda telah di dipadamkan sila guna kata laluan baru seperti dibawah :<BR>");
			msg.append("<B>Kata Laluan Baru Anda :</B>");
			msg.append("<BR> Terima kasih kerana menggunakan Sistem eTaPP");
			EmailSender sendMail = EmailSender.getInstance();
			EmailProperty pro = EmailProperty.getInstance();
			sendMail.MULTIPLE_RECIEPIENT = new String[1];
			sendMail.MULTIPLE_RECIEPIENT[0] = "razman.zainal@gmail.com";
			sendMail.FROM = pro.getFrom();
			sendMail.SUBJECT = "PERKHIDMATAN ONLINE eTaPP :- LUPA KATA LALUAN";
			sendMail.MESSAGE = msg.toString();
			sendMail.sendEmail();
		
	}
	*/
	public void hantarEmelPengguna(HttpSession session,String ID_PERMOHONAN,Integer STATUS) throws Exception {
		
		myLogger.info("MASUK FUNCTION EMEL");	
		
		EmailProperty pro = EmailProperty.getInstance();
		EmailSender email = EmailSender.getInstance();
		email.FROM = pro.getFrom();
		viewPengguna = viewDataPenggunaMOHON(session, ID_PERMOHONAN);
		
		String subject = "";
		String content = "";
		
		if(STATUS==1)//permohonanan baru
		{
			/*subject = " PERMOHONAN BARU ID PENGGUNA ";
			content = " Permohonan Baru";*/
			
			subject = " Sistem MyeTaPP - Permohonan Akaun Pengguna Baru telah diterima. ";
			content = " Assalamualaikum dan Salam Sejahtera. <br><br>";
			content+= " Untuk makluman Ybhg. Dato/ Tuan/ Puan/ Cik, kami telah menerima permohonan anda untuk membuka akaun MyeTaPP. <br>";
			content+= " Maklumat lanjut adalah seperti berikut : <br><br>";
			content+= " Maklumat Permohonan : <br>";
			
			content+= "<table width='100%' border='0' cellpadding='2' cellspacing='2'>";
			
			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='14%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='84%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Nama Pejabat</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NAMA_UNIT")+"</td></tr>" ;
			
			/*content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Nama Unit</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NAMA_UNIT")+"</td></tr>" ;*/
			
			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>NO KP</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NOKP")+"</td></tr>" ;
			
			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Nama</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NAMA")+"</td></tr>" ;
			
			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Jawatan</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("JAWATAN")+"</td></tr>" ;
			
			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>No Tel</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NO_HP")+"</td></tr>" ;
			
			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Emel</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("EMEL")+"</td></tr>" ;
			
			content+= " </table> <br>";
			
			content+= " <strong>Status</strong> : Permohonan anda sedang diproses. <br>";
			
			content+= " Sila hubungi Pegawai IT di Jabatan anda jika memerlukan sebarang bantuan berkaitan perkara diatas. <br><br>";
			
			content+= " Sekian, terima kasih.<br><br><br>";
			
			content+= " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
		}
		
		email.SUBJECT = subject;
		email.MESSAGE = content;		
		
		String USER_ID_NEGERI = (String) viewPengguna.get("ID_NEGERI");
		
		if (USER_ID_NEGERI.equals("16")){
		//edit role adminekptg??
		listPenggunaMengikutRole = listPenggunaMengikutRole(session,"adminekptg",USER_ID_NEGERI);
		}else{
		listPenggunaMengikutRole = listPenggunaMengikutRole(session,"adminstate",USER_ID_NEGERI);		
		}
		//GET EMEL PEMOHON
		email.RECIEPIENT = (String) viewPengguna.get("EMEL");
		email.sendEmail();	
	}
	
public void hantarEmelStaff(HttpSession session,String ID_PERMOHONAN,Integer STATUS) throws Exception {
		
		myLogger.info("MASUK FUNCTION EMEL STAFF");	
		
		EmailProperty pro = EmailProperty.getInstance();
		EmailSender email = EmailSender.getInstance();
		email.FROM = pro.getFrom();
		viewPengguna = viewDataPenggunaMOHON(session, ID_PERMOHONAN);
		
		
		String subject = "";
		String content = "";
		
		if(STATUS==1)//permohonanan baru
		{
			/*subject = " PERMOHONAN BARU ID PENGGUNA ";
			content = " Permohonan Baru";*/
			
			subject = " Sistem MyeTaPP - Permohonan Akaun Pengguna Baru telah diterima untuk Tindakan. ";
			content = " Assalamualaikum dan Salam Sejahtera. <br><br>";
			content+= " Untuk makluman, satu permohonan telah diterima untuk tujuan pembukaan akaun MyeTaPP. <br>";
			content+= " Maklumat lanjut adalah seperti berikut : <br><br>";
			content+= " Maklumat Permohonan : <br>";
			
			content+= "<table width='100%' border='0' cellpadding='2' cellspacing='2'>";
			
			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='14%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='84%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Nama Pejabat</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NAMA_UNIT")+"</td></tr>" ;
			
			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>NO KP</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NOKP")+"</td></tr>" ;
			
			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Nama</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NAMA")+"</td></tr>" ;
			
			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Jawatan</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("JAWATAN")+"</td></tr>" ;
			
			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>No Tel</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NO_HP")+"</td></tr>" ;
			
			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Emel</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("EMEL")+"</td></tr>" ;
			
			content+= " </table> <br>";
			
			content+= " <strong>Sila buat tindakan selanjutnya. </strong>  <br><br>";
			
		//	content+= " Sila hubungi Pegawai IT di Jabatan anda jika memerlukan sebarang bantuan berkaitan perkara diatas. <br><br>";
			
			content+= " Sekian, terima kasih.<br><br><br>";
			
			content+= " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
		}
		
		email.SUBJECT = subject;
		email.MESSAGE = content;		
		
		String USER_ID_NEGERI = (String) viewPengguna.get("ID_NEGERI");
		
		if (!USER_ID_NEGERI.equals("16")){
		//edit role adminekptg!
		listPenggunaMengikutRole = listPenggunaMengikutRole(session,"adminstate",USER_ID_NEGERI);	
		}else{
		listPenggunaMengikutRole = listPenggunaMengikutRole(session,"adminekptg","16");	
		}

		
		//GET EMEL PEGAWAI ADMINEKPTG MENGIKUT NEGERI
		email.MULTIPLE_RECIEPIENT = new String[listPenggunaMengikutRole.size()];
		for(int i = 0; i < listPenggunaMengikutRole.size();i++)
		   {			   
			   Map m = (Map) listPenggunaMengikutRole.get(i);
			  // myLogger.info(" EMEL PENGGUNA :"+(String) m.get("EMEL"));
			   //EMEL UNTUK PENGGUNA
			   
			   String emel = (String) m.get("EMEL");
			   
			   if (!emel.equals("")){
			   email.MULTIPLE_RECIEPIENT[i] = emel;
			   }else {
			   email.MULTIPLE_RECIEPIENT[i] = "samsulsamsudin@jkptg.gov.my";//(String) m.get("EMEL");	
			   }
		   }
		
		/* SELECT   DISTINCT U.USER_ID,
         U.USER_LOGIN,
         U.USER_NAME,
         UI.EMEL,
         UI.ID_NEGERI
FROM   USERS U, USERS_INTERNAL UI, USER_ROLE UR
WHERE       U.USER_LOGIN = UR.USER_ID
AND U.USER_ID = UI.USER_ID
AND UR.ROLE_ID = 'admin' --userid=900706145580
AND UI.ID_NEGERI = '10'
AND UI.EMEL IS NOT NULL
ORDER BY   U.USER_NAME*/
		
		//email.TO_CC = new String[1];		
		//email.TO_CC[0] = "razman.zainal@gmail.com";
		email.sendEmail();	
		
		
	 }
	
	
	public List listBahagian(String id_negeri) throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTableRujukanV3 = null;
		//String sql = "SELECT ID_NEGERI AS ID, KOD_NEGERI AS KOD, UPPER(NAMA_NEGERI) AS KETERANGAN FROM TBLRUJNEGERI ";
			//	sql += " WHERE ID_NEGERI IS NOT NULL";
				
			//String	sql = " SELECT ID_SEKSYEN AS ID, KOD_SEKSYEN AS KOD, UPPER(NAMA_SEKSYEN) AS KETERANGAN FROM TBLRUJSEKSYEN ORDER BY ID_SEKSYEN";
			String sql = " SELECT DISTINCT S.ID_SEKSYEN AS ID, S.KOD_SEKSYEN AS KOD, " +
					" UPPER(S.NAMA_SEKSYEN) AS KETERANGAN FROM TBLRUJSEKSYEN S, " +
					" TBLRUJPEJABATJKPTG J WHERE S.ID_SEKSYEN = J.ID_SEKSYEN ";
			if(!id_negeri.equals(""))
			{
			sql += " AND J.ID_NEGERI = '"+id_negeri+"' "+
					" AND S.FLAG_AKTIF = 'Y' ";//+
					//" AND J.FLAG_AKTIF = 'Y' ";
			}
			sql += " ORDER BY S.ID_SEKSYEN ";
			System.out.println("sql bahagian : "+sql);
		try {
			db = new Db();
			stmt = db.getStatement();rs = stmt.executeQuery(sql);
			listTableRujukanV3 = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				listTableRujukanV3.add(h);
			}

		}
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listTableRujukanV3;

	}
	
	public Hashtable viewDataPenggunaMOHON(HttpSession session, String ID_PERMOHONAN) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
			if(!ID_PERMOHONAN.equals("") || !ID_PERMOHONAN.equals(""))
			{
				sql = queryUserMOHON(session,ID_PERMOHONAN, "");
				myLogger.info(" viewDataPenggunaMOHON :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);				
				
				while (rs.next()) {
					h.put("ID_PENYEMAK",rs.getString("ID_PENYEMAK") == null ? "" : rs.getString("ID_PENYEMAK"));
					h.put("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
					h.put("NAMA",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
					h.put("TARIKH_PENDAFTARAN",rs.getString("TARIKH_PENDAFTARAN") == null ? "" : rs.getString("TARIKH_PENDAFTARAN"));
					h.put("TARIKH_KEPUTUSAN",rs.getString("TARIKH_KEPUTUSAN") == null ? "" : rs.getString("TARIKH_KEPUTUSAN"));
					h.put("ID_JAWATAN",rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
					h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
					h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
					h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
					h.put("CATATAN",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
					h.put("NOKP",rs.getString("NOKP") == null ? "" : rs.getString("NOKP"));
					h.put("NOKP1",rs.getString("NOKP").substring(0, 6));
					h.put("NOKP2",rs.getString("NOKP").substring(6, 8));
					h.put("NOKP3",rs.getString("NOKP").substring(8, 12));
					h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
					h.put("NO_HP",rs.getString("NO_HP") == null ? "" : rs.getString("NO_HP"));
					h.put("FLAG_STATUS",rs.getString("FLAG_STATUS") == null ? "" : rs.getString("FLAG_STATUS"));
					h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT"));
					h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
					h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));
					h.put("BAHAGIAN",rs.getString("BAHAGIAN") == null ? "" : rs.getString("BAHAGIAN"));
					h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));					
					h.put("PEGAWAIPENYELIA",rs.getString("PEGAWAIPENYELIA") == null ? "" : rs.getString("PEGAWAIPENYELIA"));
					h.put("ID_TABLEUSERS",rs.getString("ID_TABLEUSERS") == null ? "" : rs.getString("ID_TABLEUSERS"));
					h.put("ID_PENYEMAK",rs.getString("ID_PENYEMAK") == null ? "" : rs.getString("ID_PENYEMAK"));
					h.put("PENYEMAK",rs.getString("PENYEMAK") == null ? "" : rs.getString("PENYEMAK"));
					h.put("NAMA_DOC",rs.getString("NAMA_DOC") == null ? "" : rs.getString("NAMA_DOC"));
					h.put("JENIS_MIME",rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME"));	
					
					//diba tambah
					h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));	
					
				}
			}
			else
			{			
				h.put("NAMA_DOC","");
				h.put("JENIS_MIME","");
				h.put("PENYEMAK","");
				h.put("PEGAWAIPENYELIA","");
				h.put("ID_TABLEUSERS","");
				h.put("ID_PENYEMAK","");
				h.put("ID_PERMOHONAN","");
				h.put("NAMA","");
				h.put("TARIKH_PENDAFTARAN","");
				h.put("TARIKH_KEPUTUSAN","");
				h.put("ID_JAWATAN","");
				h.put("ID_PEJABATJKPTG","");
				h.put("ID_NEGERI","");
				h.put("ID_SEKSYEN","");
				h.put("CATATAN","");
				h.put("NOKP","");
				h.put("NOKP1","");
				h.put("NOKP2","");
				h.put("NOKP3","");
				h.put("EMEL","");
				h.put("NO_HP","");
				h.put("FLAG_STATUS","");
				h.put("NAMA_UNIT","");
				h.put("NEGERI","");
				h.put("JAWATAN","");
				h.put("BAHAGIAN","");
				h.put("STATUS","");
			}
			return h;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
public String queryUserMOHON(HttpSession session,String ID_PERMOHONAN, String carianTerperinci) throws Exception {	
		
		
		String query = " SELECT PP.CONTENT, PP.NAMA_DOC, PP.JENIS_MIME, PP.PENYEMAK,PP.PEGAWAIPENYELIA,PP.ID_TABLEUSERS,PP.ID_PENYEMAK,PP.ID_PERMOHONAN, PP.NAMA, PP.TARIKH_PENDAFTARAN, PP.TARIKH_KEPUTUSAN, PP.ID_JAWATAN, PP.ID_PEJABATJKPTG, PP.ID_NEGERI, "+
				" PP.ID_SEKSYEN,PP.CATATAN,PP.NOKP,PP.EMEL,PP.NO_HP,PP.FLAG_STATUS,PP.NAMA_UNIT, PP.NAMA_PEJABAT, PP.NEGERI,PP.JAWATAN,PP.BAHAGIAN,PP.STATUS "+
				" FROM "+
				" ( "+
				" SELECT P.CONTENT, P.NAMA_DOC, P.JENIS_MIME, UPPER(PPTM.USER_NAME) AS PENYEMAK, UPPER(P.PEGAWAIPENYELIA) AS PEGAWAIPENYELIA, P.ID_TABLEUSERS,P.ID_PENYEMAK,P.USER_ID AS ID_PERMOHONAN, UPPER(P.USER_NAME) AS NAMA, TO_CHAR(P.TARIKH_PENDAFTARAN,'DD/MM/YYYY') AS TARIKH_PENDAFTARAN,  "+
				" TO_CHAR(P.TARIKH_KEPUTUSAN,'DD/MM/YYYY') AS TARIKH_KEPUTUSAN, P.ID_JAWATAN, "+
				" P.ID_PEJABATJKPTG, P.ID_NEGERI, P.ID_SEKSYEN, P.CATATAN, P.NOKP, P.EMEL, P.NO_HP, P.FLAG_STATUS, UPPER(PEJ.NAMA_PEJABAT) AS NAMA_UNIT, UPPER (PEJ.ALAMAT1) AS NAMA_PEJABAT, "+
				" UPPER(NEG.NAMA_NEGERI) AS NEGERI,UPPER(J.KETERANGAN) AS JAWATAN, UPPER(S.NAMA_SEKSYEN) AS BAHAGIAN, "+
				" (CASE WHEN P.FLAG_STATUS = 1 THEN 'PERMOHONAN BARU' "+
				" WHEN P.FLAG_STATUS = 2 THEN 'DITOLAK' "+
				" WHEN P.FLAG_STATUS = 3 THEN 'DITERIMA' ELSE '' END) AS STATUS "+
				" FROM USERS PPTM,PERMOHONANIDPENGGUNA P, TBLRUJPEJABATJKPTG PEJ, TBLRUJNEGERI NEG, TBLRUJJAWATAN J, TBLRUJSEKSYEN S "+
				" WHERE P.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG AND P.ID_NEGERI = NEG.ID_NEGERI "+
				" AND P.ID_PENYEMAK = PPTM.USER_ID(+) AND P.ID_JAWATAN = J.ID_JAWATAN AND P.ID_SEKSYEN = S.ID_SEKSYEN(+) "+
				" ORDER BY P.FLAG_STATUS ASC, P.TARIKH_PENDAFTARAN DESC, P.ID_JAWATAN ASC, P.USER_NAME ASC "+
				" ) PP WHERE PP.ID_PERMOHONAN IS NOT NULL ";
		
				if(!ID_PERMOHONAN.trim().equals(""))
				{
					query += " AND PP.ID_PERMOHONAN = '"+ID_PERMOHONAN.trim()+"' ";
				}
				
				return query;
	}
	
@SuppressWarnings("unchecked")
public List listPenggunaMengikutRole(HttpSession session,String ROLE_ID,String ID_NEGERI)throws Exception {
	Db db = null;
	ResultSet rs = null;
	Statement stmt = null;
	List listPengunaByRoleNegeri = null;
	String sql = "";
	
	try {
		db = new Db();
		stmt = db.getStatement();			
		
	    sql = " SELECT DISTINCT U.USER_ID,U.USER_LOGIN,U.USER_NAME, UI.EMEL, UI.ID_NEGERI " +
	    		" FROM USERS U,USERS_INTERNAL UI, USER_ROLE UR "+
	    		" WHERE U.USER_LOGIN = UR.USER_ID AND U.USER_ID = UI.USER_ID "+
	    		" AND U.USER_ROLE  = '"+ROLE_ID+"' "+
	    		" AND UI.ID_NEGERI = '"+ID_NEGERI+"' "+
	    		" AND UI.EMEL IS NOT NULL" +
	    		" ORDER BY U.USER_NAME ";				
		
		myLogger.info(" V3: SQL listPenggunaMengikutRole :"+ sql);			
		rs = stmt.executeQuery(sql);
		listPengunaByRoleNegeri = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			//U.USER_ID,U.USER_LOGIN,U.USER_NAME, UI.EMEL, UI.ID_NEGERI
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));	
			h.put("USER_LOGIN",rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
			h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
			h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
			h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
			listPengunaByRoleNegeri.add(h);
		}

	} finally {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (db != null)
			db.close();
	}
	return listPengunaByRoleNegeri;

}

public String checkPermohonanID(HttpSession session, String NOKP) throws Exception {
	Db db = null;
	String sql = "";
	ResultSet rs = null;
	Statement stmt = null;
	try {
		db = new Db();
		stmt = db.getStatement();
		String flag_status = "";
		
		sql = " SELECT FLAG_STATUS FROM PERMOHONANIDPENGGUNA WHERE NOKP = '"+NOKP+"'  ";
		
			rs = stmt.executeQuery(sql);				
			while (rs.next()) {
				flag_status = rs.getString("FLAG_STATUS") == null ? "" : rs.getString("FLAG_STATUS");
			}
		
		return flag_status;
	} finally {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (db != null)
			db.close();
	}
}

public boolean checkUSERINTERNAL(HttpSession session, String USER_ID) throws Exception {
	Db db = null;
	String sql = "";
	ResultSet rs = null;
	Statement stmt = null;
	boolean check = true;
	try {
		db = new Db();
		stmt = db.getStatement();
		Hashtable h;
		h = new Hashtable();
		
			sql = 	" SELECT U.USER_ID, U.USER_LOGIN, U.USER_NAME FROM USERS U " +
					" WHERE U.USER_ID IS NOT NULL " ;
			
			
			if(!USER_ID.equals(""))
			{
				sql += " AND U.USER_LOGIN = '"+USER_ID+"'" ;
			}
			
			myLogger.info(" checkUSERINTERNAL :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			
			String GET_USER_ID = "";
			while (rs.next()) {
				
				GET_USER_ID = rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID");
				if(!GET_USER_ID.equals(""))
				{
					check = false; //dah ada user
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

public String getID_KHIDMAT(HttpSession session, String ID_JAWATAN) throws Exception {
	
	Db db = null;
	String sql = "";
	ResultSet rs = null;
	Statement stmt = null;
	String ID_KHIDMAT = "";

	try {
		db = new Db();
		stmt = db.getStatement();
		Hashtable h;
		h = new Hashtable();
		
			sql = " SELECT ID_KHIDMAT FROM TBLRUJJAWATAN WHERE ID_JAWATAN = "+ID_JAWATAN;
			
			myLogger.info("FIND ID_KHIDMAT :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				ID_KHIDMAT = rs.getString("ID_KHIDMAT") == null ? "" : rs.getString("ID_KHIDMAT");
				
			}
		
		return ID_KHIDMAT;
		
	} finally {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (db != null)
			db.close();
	}
} 

public String getID_KLASIFIKASI(HttpSession session, String ID_JAWATAN) throws Exception {
	
	Db db = null;
	String sql = "";
	ResultSet rs = null;
	Statement stmt = null;
	String ID_KLASIFIKASI = "";

	try {
		db = new Db();
		stmt = db.getStatement();
		Hashtable h;
		h = new Hashtable();
		
			sql = " SELECT ID_KLASIFIKASI FROM TBLRUJJAWATAN WHERE ID_JAWATAN = "+ID_JAWATAN;
			
			myLogger.info("FIND ID_KLASIFIKASI :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				ID_KLASIFIKASI = rs.getString("ID_KLASIFIKASI") == null ? "" : rs.getString("ID_KLASIFIKASI");
				
			}
		
		return ID_KLASIFIKASI;
		
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
