package ekptg.view.ppk;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
//import lebah.db.SQLRenderer;
import lebah.portal.db.AuthenticateUser;
import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmTukaranStatus;
import ekptg.model.ppk.PendaftaranCheckModel;

public class PendaftaranCheck implements IServlet2 {

	static Logger myLogger = Logger.getLogger(PendaftaranCheck.class);
//	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 
	public void doService(HttpServletRequest request
		,HttpServletResponse response
		,ServletContext context) throws IOException, ServletException {

		// String name="";
		// String value="";
		// Enumeration allparam = request.getParameterNames();
		// for (; allparam.hasMoreElements(); ) {
		// // Get the name of the request parameter
		// name = (String)allparam.nextElement();
		// // Get the value of the request parameter
		// value = request.getParameter(name);
		// myLogger.debug(name +"="+value);
		// }

		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
//		String contextPath = request.getContextPath();
//		String nama = request.getParameter("nama");
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		String submit = request.getParameter("command");
		myLogger.info("submit="+submit);
		String securityToken = (String) session.getAttribute("securityToken");
		String module = (String) session.getAttribute("_portal_module");
//		System.out.println("module ::::::::::::::: "+module);
 
		String no_kp_baru_simati = request.getParameter("check_no_kp_baru_simati");
		String no_kp_lama_simati = request.getParameter("check_no_kp_lama_simati");
		String no_kp_lain_simati = request.getParameter("check_no_kp_lain_simati");
		String no_kp_baru_pemohon = request.getParameter("check_no_kp_baru_pemohon");
		String no_kp_lama_pemohon = request.getParameter("check_no_kp_lama_pemohon");
		String no_kp_lain_pemohon = request.getParameter("check_no_kp_lain_pemohon");
		String txtNoSuratBeranakWaris = request.getParameter("txtNoSuratBeranakWaris");

		String id_permohonan = request.getParameter("id_permohonan");
//		System.out.println("***********id_permohonanDalamPendaftaranCheck************** :"+id_permohonan);
		String idPermohonan = request.getParameter("idPermohonan");

		String id_Orangberkepentingan = request.getParameter("id_Orangberkepentingan");
		String id_simati = request.getParameter("idSimati");
		String jenis_ob = request.getParameter("jenis_ob");
		String idFail = request.getParameter("id_Fail");
		String txtNoFail = request.getParameter("txtNoFail");

		String no_lot_hta = request.getParameter("no_lot_hta");
		String id_harta = request.getParameter("id_harta");
		String id_daerah = request.getParameter("id_daerah_harta");

		String jenis_pej = request.getParameter("jenis_pej");

		String umur_saksi_U = request.getParameter("txtUmurPentingU");
		String umur_saksi = request.getParameter("txtUmurPenting");

		int umursaksi = 0;

		if (umur_saksi_U != null && !"".equals(umur_saksi_U)) {
			umursaksi = Integer.parseInt(umur_saksi_U);
		} else if (umur_saksi != null && !"".equals(umur_saksi)) {
			umursaksi = Integer.parseInt(umur_saksi);
		}

		// String txtNoPTHtaamUp = request.getParameter("txtNoPTHtaamUp");
		Vector listfail = new Vector();
		// System.out.println("test_check no kp lama ::"+no_kp_lama_simati);
		String tik = "<img src='../portal/validyes.png' alt='' border='0'/>";
		// ##

		Vector<Hashtable<String,String>> mati1 = new Vector<Hashtable<String,String>> ();
		Vector<Hashtable<String,String>> mati2 = new Vector<Hashtable<String,String>>();
		Vector<Hashtable<String,String>> mati3 = new Vector<Hashtable<String,String>>();
//		Vector<Hashtable<String,String>> papar_list_ob = null;
		Vector<Hashtable<String,String>> papar_list_simati = null;
//		Vector<Hashtable<String,String>> papar_list_pemohon = null;

		if ("check_simati_kp_baru".equals(submit)) {
//			System.out.println("******####**** check_simati_kp_baru ******####****  ");
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			try {
				mati1 = userdata.List_KP_Baru_Simati(id_permohonan, no_kp_baru_simati,no_kp_lama_simati, no_kp_lain_simati);
//				System.out.println("mati1.size(a)" +mati1.size());
				if (mati1.size() > 0) {
					Hashtable<String,String> k = (Hashtable<String,String>) mati1.get(0);
					String nama_simati = k.get("NAMA_SIMATI").toString();
					String no_fail = k.get("NO_FAIL").toString();
					String nama_pejabat = k.get("NAMA_PEJABAT").toString();
//					String daerah_pejabat = k.get("DAERAH_PEJABAT").toString();
					String daerah_mohon = k.get("NAMA_DAERAH").toString();
					//String id_permohonan2 = k.get("ID_PERMOHONAN").toString();
					
//					String op = "";
//					String kp1 = "";
//					String kp2 = "";
//					String kp3 = "";
//
//					String no_kp_baru = k.get("NO_KP_BARU").toString();
//					String no_kp_lama = k.get("NO_KP_LAMA").toString();
//					String no_kp_lain = k.get("NO_KP_LAIN").toString();
//					String jenis_kp = k.get("JENIS_KP").toString();
//					String t_mati = k.get("TARIKH_MATI").toString();

//					if (!no_kp_baru.equals("")) {
//						kp1 = k.get("NO_KP_BARU").toString().substring(0, 6);
//						kp2 = k.get("NO_KP_BARU").toString().substring(6, 8);
//						kp3 = k.get("NO_KP_BARU").toString().substring(8, 12);
//					}

					if (no_kp_baru_simati.length() == 12) {						
//						displaySuratBatalAlert("No kad pengenalan baru",
//								out, nama_simati, nama_pejabat,
//								daerah_mohon, no_fail, no_kp_baru_simati,
//								"SuratBatalPermohonanKpBaru", securityToken);
						 
						//COMMENT BY PEJE - XTAU NAPE DIA KENE CHECK 2 KALI.
//						System.out.println("##Check duplicate IC##");
						if (userdata.checkKP_Baru_Simati(id_permohonan,no_kp_baru_simati, no_kp_lama_simati,no_kp_lain_simati) == true) {
					/*		op=
									 "<div>No kad pengenalan baru simati sudah wujud!</div> <input name='no_kp1' type='hidden' value='yes' /> <script type='text/javascript'>"
									 +
									 "message_box = confirm('Permohonan untuk simati "+nama_simati.toUpperCase()+" yang bernombor fail "+no_fail.toUpperCase()+" sudah wujud! "
									 +
									 "Permohonan telah dibuat di "+nama_pejabat.toUpperCase()+". "
									 +
									 " Sila lengkapkan permohonan ini terlebih dahulu sebelum mencetak surat pembatalan permohonan.'); "
									 +
									 "";
									 op += "</script>";
									 out.println(op);
							*/
							if(module.equals("ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon")){
//								System.out.println("ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon");
								displaySuratBatalAlertOnline(id_permohonan
									,"No. kad pengenalan baru"
									,out
									,nama_simati
									,nama_pejabat
									,daerah_mohon
									,no_fail
									,no_kp_baru_simati
									,"SuratBatalPermohonanKpBaru"
									, securityToken);
							}else{
//								System.out.println("**********ELSE**********");
								displaySuratBatalAlert(id_permohonan
									, "No. kad pengenalan baru"
									,out
									,nama_simati
									,nama_pejabat
									,daerah_mohon
									,no_fail
									,no_kp_baru_simati
									,"SuratBatalPermohonanKpBaru"
									, securityToken);
							}
														
						} else {
							out.println("<input name='no_kp1' type='hidden' value='' />");
						}
					} else {
						out.println("<input name='no_kp1' type='hidden' value='' />");
					}

				} else {
					out.println("<input name='no_kp1' type='hidden' value='' />");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if ("check_simati_kp_lama".equals(submit)) {
//			System.out.println("******####**** submit ******####**** "+submit+"******####**** check_simati_kp_lama ******####**** ");
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			try {
				mati2 = null;
				mati2 = userdata.List_KP_Lama_Simati(id_permohonan, no_kp_baru_simati,no_kp_lama_simati, no_kp_lain_simati);
//				System.out.println("mati2.size(b) = "+mati2.size());
				if (mati2.size() > 0) {
					Hashtable<String,String>  k = (Hashtable<String,String> ) mati2.get(0);
					String nama_simati = k.get("NAMA_SIMATI").toString();
					String no_fail = k.get("NO_FAIL").toString();
					String nama_pejabat = k.get("NAMA_PEJABAT").toString();
//					String daerah_pejabat = k.get("DAERAH_PEJABAT").toString();
					String daerah_mohon = k.get("NAMA_DAERAH").toString();
					//String id_permohonan2 = k.get("ID_PERMOHONAN").toString();
					//System.out.println("id permohonan >>> "+k.get("ID_PERMOHONAN").toString());
					
					if (no_kp_lama_simati != "") {
//						System.out.println("##Check duplicate IC Lama##");
						if (userdata.checkKP_Lama_Simati(id_permohonan,no_kp_baru_simati,no_kp_lama_simati,no_kp_lain_simati) == true) {
//							System.out.println("##Check duplicate IC Lama2222##");
							// op=
							// "<div>No kad pengenalan lama sudah wujud!</div> <input name='no_kp2' type='hidden' value='yes' /> <script type='text/javascript'>  "
							// +
							// "input_box = confirm('Permohonan untuk simati "+nama_simati.toUpperCase()+" yang bernombor fail "+no_fail.toUpperCase()+" sudah wujud! "
							// +
							// "Permohonan telah dibuat di "+nama_pejabat.toUpperCase()+". "
							// +
							// " Adakah anda ingin mencetak surat pembatalan permohonan ?'); "
							// +
							// "if (input_box == true) { " +
							// "alert('Maaf,Surat dalam proses..tunggu yer');"+
							// "}" +
							// "";
							// op += "</script>";
							// out.println(op);
							
							if(module.equals("ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon")){
//								System.out.println("**ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon**");
								displaySuratBatalAlertOnline(id_permohonan
									, "No. kad pengenalan lama"
									,out
									,nama_simati
									,nama_pejabat
									,daerah_mohon
									,no_fail
									,no_kp_lama_simati
									,"SuratBatalPermohonanKpLama"
									,securityToken);
							}else{
//								System.out.println("**********ELSE**********");
								displaySuratBatalAlert(id_permohonan
									,"No. kad pengenalan lama"
									,out
									,nama_simati
									,nama_pejabat
									,daerah_mohon
									,no_fail
									,no_kp_lama_simati
									,"SuratBatalPermohonanKpLama"
									,securityToken);
							}
						} else {
							out.println("<input name='no_kp2' type='hidden' value='' />");
						}
						
					} else {
						out.println("<input name='no_kp2' type='hidden' value='' />");
					}
				} else {
					out.println("<input name='no_kp2' type='hidden' value='' />");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("check_simati_kp_lain".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			try {
				mati3 = userdata.List_KP_Lain_Simati(id_permohonan, no_kp_baru_simati,no_kp_lama_simati, no_kp_lain_simati);
				if (mati3.size() > 0) {
					Hashtable<String,String> k = (Hashtable<String,String>) mati3.get(0);
					String nama_simati = k.get("NAMA_SIMATI").toString();
					String no_fail = k.get("NO_FAIL").toString();
					String nama_pejabat = k.get("NAMA_PEJABAT").toString();
//					String daerah_pejabat = k.get("DAERAH_PEJABAT").toString();
					String daerah_mohon = k.get("NAMA_DAERAH").toString();
					//String id_permohonan2 = k.get("ID_PERMOHONAN").toString();
//					String op = "";
//					String kp1 = "";
//					String kp2 = "";
//					String kp3 = "";

//					String no_kp_baru = k.get("NO_KP_BARU").toString();
//					String no_kp_lama = k.get("NO_KP_LAMA").toString();
//					String no_kp_lain = k.get("NO_KP_LAIN").toString();
//					String jenis_kp = k.get("JENIS_KP").toString();
//					String t_mati = k.get("TARIKH_MATI").toString();

//					if (!no_kp_baru.equals("")) {
//						kp1 = k.get("NO_KP_BARU").toString().substring(0, 6);
//						kp2 = k.get("NO_KP_BARU").toString().substring(6, 8);
//						kp3 = k.get("NO_KP_BARU").toString().substring(8, 12);
//					
//					} 

					if (no_kp_lain_simati != "") {
						if (userdata.checkKP_Lain_Simati(id_permohonan,no_kp_baru_simati, no_kp_lama_simati,no_kp_lain_simati) == true){
							// op=
							// "<div>No kad pengenalan lain sudah wujud!</div> <input name='no_kp3' type='hidden' value='yes' /> <script type='text/javascript'>  "
							// +
							// "input_box = confirm('Permohonan untuk simati "+nama_simati.toUpperCase()+" yang bernombor fail "+no_fail.toUpperCase()+" sudah wujud! "
							// +
							// "Permohonan telah dibuat di "+nama_pejabat.toUpperCase()+". "
							// +
							// " Adakah anda ingin mencetak surat pembatalan permohonan ?'); if (input_box == true) {}"
							// +
							// "";
							// op += "</script>";
							// out.println(op);
							
							if(module.equals("ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon")){
								displaySuratBatalAlertOnline(id_permohonan
									,"No. kad pengenalan lain"
									,out
									,nama_simati
									,nama_pejabat
									,daerah_mohon
									,no_fail
									,no_kp_lain_simati
									,"SuratBatalPermohonanKpLain"
									,securityToken);
							}else{
								displaySuratBatalAlert(id_permohonan
									,"No. kad pengenalan lain"
									,out
									,nama_simati
									,nama_pejabat
									,daerah_mohon
									,no_fail
									,no_kp_lain_simati
									,"SuratBatalPermohonanKpLain"
									, securityToken);
							
							}							
							
						} else {
							out.println("<input name='no_kp3' type='hidden' value='' />");
						}
					} else {
						out.println("<input name='no_kp3' type='hidden' value='' />");
					}
				} else {
					out.println("<input name='no_kp3' type='hidden' value='' />");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/*
		 * String no_kp_baru_simati =
		 * request.getParameter("check_no_kp_baru_simati"); String
		 * no_kp_lama_simati = request.getParameter("check_no_kp_lama_simati");
		 * String no_kp_lain_simati =
		 * request.getParameter("check_no_kp_lain_simati");
		 * 
		 * 
		 * String no_kp_baru_pemohon =
		 * request.getParameter("check_no_kp_baru_pemohon"); String
		 * no_kp_lama_pemohon =
		 * request.getParameter("check_no_kp_lama_pemohon"); String
		 * no_kp_lain_pemohon =
		 * request.getParameter("check_no_kp_lain_pemohon"); if
		 * ("check_simati_kp_baru".equals(submit)) {
		 * 
		 * }
		 */

		else if ("check_myid_kemaskinimyid".equals(submit)) {
			FrmTukaranStatus model = new FrmTukaranStatus();
			try {
//				papar_list_ob = model.papar_list_ob(request.getParameter("id_fail_carian"));
				papar_list_simati = model.papar_list_simati(request.getParameter("id_fail_carian"));
//				papar_list_pemohon = model.papar_list_pemohon(request.getParameter("id_fail_carian"));
				myLogger.info("PendaftaranCek:"+ request.getParameter("id_fail_carian"));
				if (papar_list_simati.size() > 0) {
					for (int i = 1; i < papar_list_simati.size() + 1; i++) {
						String id_simati_check = "id_simati" + i;
						String no_kp_baru = "no_kp_baru_simati" + i;
						String no_kp_lama = "no_kp_lama_simati" + i;
						String no_kp_lain = "no_kp_lain_simati" + i;

						if (checkKPSimati(id_simati_check, no_kp_baru,no_kp_lama, no_kp_lain) == false) {
//						} else {
							out.println("XXX"+ request.getParameter("id_fail_carian"));
						}

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if ("check_kpbaru_pemohon".equals(submit)) {
			if (!no_kp_baru_simati.equals("") && !no_kp_baru_pemohon.equals("")
					&& no_kp_baru_pemohon.length() == 12
					&& no_kp_baru_simati.length() == 12) {
				if (no_kp_baru_simati.equals(no_kp_baru_pemohon)) {
					out.println("Sila pastikan simati dan pemohon adalah individu yang berbeza <input name='pemohonsimatikp1' id='pemohonsimatikp1' type='hidden' value = '1' />");
				} else {
					out.println("<input name='pemohonsimatikp1' id='pemohonsimatikp1' type='hidden' value = '0' />");
				}
			} else {
				out.println("<input name='pemohonsimatikp1' id='pemohonsimatikp1' type='hidden' value = '0' />");
			}

		} else if ("check_kplama_pemohon".equals(submit)) {

			if (!no_kp_lama_simati.equals("TDK")
					&& !no_kp_lama_simati.equals("")
					&& !no_kp_lama_pemohon.equals("")
					&& no_kp_lama_pemohon.length() >= 7
					&& no_kp_lama_simati.length() >= 7) {
				if (no_kp_lama_simati.equals(no_kp_lama_pemohon)) {
					out.println("Sila pastikan simati dan pemohon adalah individu yang berbeza <input name='pemohonsimatikp2' id='pemohonsimatikp2' type='hidden' value = '2' /> ");
				} else {
					out.println("<input name='pemohonsimatikp2' id='pemohonsimatikp2' type='hidden' value = '0' />");
				}
			} else {
				out.println("<input name='pemohonsimatikp2' id='pemohonsimatikp2' type='hidden' value = '0' />");
			}

		} else if ("check_kplain_pemohon".equals(submit)) {

			if (!no_kp_lain_simati.equals("") && !no_kp_lain_pemohon.equals("")) {
				if (no_kp_lain_simati.equals(no_kp_lain_pemohon)) {
					out.println("Sila pastikan simati dan pemohon adalah individu yang berbeza <input name='pemohonsimatikp3' id='pemohonsimatikp3' type='hidden' value = '3' /> ");
				} else {
					out.println("<input name='pemohonsimatikp3' id='pemohonsimatikp3' type='hidden' value = '0' />");
				}
			} else {
				out.println("<input name='pemohonsimatikp3' id='pemohonsimatikp3' type='hidden' value = '0' />");
			}

		}else if ("check_simati_kp_baru_onload".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();

			try {

				if (no_kp_baru_simati.length() == 12) {
					if (userdata.checkKP_Baru_Simati(id_permohonan,
							no_kp_baru_simati, no_kp_lama_simati,
							no_kp_lain_simati) == true){
						out.println("<input name='no_kp1' type='hidden' value='' />");
						//out
						//		.println("<div>No kad pengenalan baru simati sudah wujud!</div> <input name='no_kp1' type='hidden' value='yes' /> ");
					} else {
						out.println("<input name='no_kp1' type='hidden' value='' />");

					}
				} else {
					out.println("<input name='no_kp1' type='hidden' value='' />");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if ("check_hutang".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			try {
				if (no_kp_lama_simati != "") {
					if (userdata.checkKP_Lama_Simati(id_permohonan,
							no_kp_baru_simati, no_kp_lama_simati,
							no_kp_lain_simati) == true){
						out.println("<div>No. kad pengenalan lama simati sudah wujud! xxx </div> <input name='no_kp2' type='hidden' value='yes' />");
					} else {
						out.println("<input name='no_kp2' type='hidden' value='' />");

					}
				} else {
					out.println("<input name='no_kp2' type='hidden' value='' />");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if ("check_simati_kp_lama_onload".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			try {
				if (no_kp_lama_simati != "") {
					if (userdata.checkKP_Lama_Simati(id_permohonan,
							no_kp_baru_simati, no_kp_lama_simati,
							no_kp_lain_simati) == true){
						out.println("<input name='no_kp1' type='hidden' value='' />");
						/*out
								.println("<div>No kad pengenalan lama simati sudah wujud! yyy </div> <input name='no_kp2' type='hidden' value='yes' />");*/
					} else {
						out.println("<input name='no_kp2' type='hidden' value='' />");

					}
				} else {
					out.println("<input name='no_kp2' type='hidden' value='' />");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if ("check_simati_kp_lain_onload".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			try {
				if (no_kp_lain_simati != "") {
					if (userdata.checkKP_Lain_Simati(id_permohonan,
							no_kp_baru_simati, no_kp_lama_simati,
							no_kp_lain_simati) == true){
						out.println("<input name='no_kp1' type='hidden' value='' />");
						/*out
								.println("<div>No kad pengenalan lain simati sudah wujud!</div> <input name='no_kp3' type='hidden' value='yes' />");*/
					} else {
						out.println("<input name='no_kp3' type='hidden' value='' />");
					}
				} else {
					out.println("<input name='no_kp3' type='hidden' value='' />");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if ("getbandar_daftar".equals(submit)) {
			// System.out.println("neg :"+request.getParameter("socNegeri"));
			FrmPrmhnnSek8DaftarSek8InternalData us = new FrmPrmhnnSek8DaftarSek8InternalData();
			String op = "";

			try {
				if (request.getParameter("socNegeri") == "") {
					op = "#set($negeri = '')" + " #set($daerah = '')";
					// this.context.put("selectNegeri",
					// HTML.SelectNegeri("socNegeri","class=autoselect"));
				} else {
					// this.context.put("negeri",getParam("socNegeri"));
					int id_negeri = Integer.parseInt(request.getParameter("socNegeri"));
					Vector s = us.getListBandarByNegeri(id_negeri);
					op = "#set($listBandarbyNegeri = " + s + ")"+ " #set($daerah = '')";

					// System.out.println("neg list :"+op);

					// this.context.put("listBandarbyNegeri",s);
					// this.context.put("daerah","");
					// this.context.put("selectNegeri",
					// HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("pmidnegeri").toString()),"class=\"autoselect\" disabled"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if ("check_nofail_onload".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			try {
				if (txtNoFail != "") {
					userdata.setListNofail(idFail, txtNoFail);
					listfail = userdata.getListNofail();
					if (listfail.size() > 0) {
						out.println("<div>No. fail sudah wujud!</div> ");
					} else {
						out.println("");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("check_nofail".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			try {
				if (txtNoFail != "") {
					userdata.setListNofail(idFail, txtNoFail);
					listfail = userdata.getListNofail();
					if (listfail.size() > 0) {
						out.println("<div>No. fail sudah wujud!</div>  <script type='text/javascript'>"
										+ "input_box = confirm('No. fail sudah wujud! Adakah anda ingin mencetak surat pembatalan permohonan ?'); if (input_box == true) {}"
										+ "</script>");
					} else {
						out.println("");

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if ("check_ob_kp_baru".equals(submit)) {
			if (no_kp_baru_simati.length() < 12) {
				return;
			}
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			try {

				if (no_kp_baru_simati.length() == 12) {

					Vector<Hashtable<String,String>> lb = userdata.checkKP_list_baru(id_simati
															,id_Orangberkepentingan
															,no_kp_baru_simati
															,no_kp_lama_simati
															,no_kp_lain_simati);
					if (userdata.checkKP_Baru_Ob(id_simati,
							id_Orangberkepentingan, no_kp_baru_simati,
							no_kp_lama_simati, no_kp_lain_simati) == true){
						out.println("<div>No. kad pengenalan baru "
										+ jenis_ob
										+ " sudah wujud!</div> <script type='text/javascript'> document.f1.flag_dup_1.value = 'yes' </script>");
					} else {
						String op = "";
						op += "<script type='text/javascript'> document.f1.flag_dup_1.value = ''; </script> ";
						if (lb.size() > 0) {
							Hashtable<String,String> k = (Hashtable<String,String>) lb.get(0);

							op += "<script type='text/javascript'> "
									+ "document.f1.txtNamaOBWaris.value = '"
									+ k.get("NAMA_OB").toString() + "'; "
									+ "document.f1.txtNoKPLamaWaris.value = '"
									+ k.get("NO_KP_LAMA").toString() + "'; ";

							if (k.get("NO_KP_BARU").toString() != ""
									&& k.get("NO_KP_BARU").toString().length() == 12) {
								op += "document.f1.txtNoKPBaru1Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(0, 6)
										+ "'; "
										+ "document.f1.txtNoKPBaru2Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(6, 8)
										+ "'; "
										+ "document.f1.txtNoKPBaru3Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(8, 12) + "'; ";
							} else {
								op += "document.f1.txtNoKPBaru1Waris.value = ''; "
										+ "document.f1.txtNoKPBaru2Waris.value = ''; "
										+ "document.f1.txtNoKPBaru3Waris.value = ''; ";
							}

							if (k.get("NO_KP_LAIN").toString() != "") {
								op += "document.f1.txtNoKPLainWaris.disabled = ''; ";
							}
							op += "document.f1.txtNoKPLainWaris.value = '"
									+ k.get("NO_KP_LAIN").toString()
									+ "'; "
									+ "document.f1.socJenisKPLainWaris.value = '"
									+ k.get("JENIS_KP").toString()
									+ "'; "
									+ "document.f1.socJantinaWaris.value = '"
									+ k.get("JANTINA").toString()
									+ "'; "
									+ "document.f1.socAgamaWaris.value = '"
									+ k.get("JENIS_AGAMA").toString()
									+ "'; "
									+ "document.f1.socWarganegaraWaris.value = '"
									+ k.get("JENIS_WARGA").toString()
									+ "'; "
									+ "document.f1.txdTarikhLahirWaris.value = '"
									+ k.get("TARIKH_LAHIR").toString()
									+ "'; "
									+ "document.f1.txtUmurWaris.value = '"
									+ k.get("UMUR").toString()
									+ "'; "
									+ "document.f1.socStatusOBWaris.value = '"
									+ k.get("STATUS_OB").toString()
									+ "'; "
									+ "document.f1.socSaudaraWaris.value = '"
									+ k.get("ID_SAUDARA").toString()
									+ "'; "
									+ "document.f1.txtNoSuratBeranakWaris.value = '"
									+ k.get("NO_SURAT_BERANAK").toString()
									+ "'; ";

							if (k.get("STATUS_HIDUP").toString().equals("1")) {
								op += "document.f1.checkHidupWaris.checked = true ; ";
							}

							op += "if(document.f1.txtWaktuKematianWaris!=null)"
									+ "{document.f1.txtWaktuKematianWaris.value = '"
									+ k.get("WAKTU_KEMATIAN").toString()
									+ "'; "
									+ "document.f1.txdTarikhMatiWaris.value = '"
									+ k.get("TARIKH_MATI").toString() + "'; } ";

							op += "document.f1.txtAlamatTerakhir1Waris.value = '"
									+ k.get("ALAMAT_1").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2Waris.value = '"
									+ k.get("ALAMAT_2").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3Waris.value = '"
									+ k.get("ALAMAT_3").toString()
									+ "'; "
									+ "document.f1.txtPoskodWaris.value = '"
									+ k.get("POSKOD").toString()
									+ "'; "
									+ "document.f1.socNegeriWaris.value = '"
									+ k.get("ID_NEGERI").toString()
									+ "'; "
									+ "document.f1.txtBandarWaris.value = '"
									+ k.get("ID_BANDAR").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir1WarisSurat.value = '"
									+ k.get("ALAMAT1_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2WarisSurat.value = '"
									+ k.get("ALAMAT2_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3WarisSurat.value = '"
									+ k.get("ALAMAT3_SURAT").toString()
									+ "'; "
									+ "document.f1.txtPoskodWarisSurat.value = '"
									+ k.get("POSKOD_SURAT").toString()
									+ "'; "
									+ "document.f1.socNegeriWarisSurat.value = '"
									+ k.get("ID_NEGERISURAT").toString()
									+ "'; "
									+ "document.f1.txtBandarWarisSurat.value = '"
									+ k.get("ID_BANDARSURAT").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponWaris.value = '"
									+ k.get("NO_TEL").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponBimbitWaris.value = '"
									+ k.get("NO_HP").toString() + "'; ";

							// Azam add here. auto reload for bandar
							// Load Bandar based on id negeri
							String list = request.getParameter("list");
							if ("true".equals(list)) {

								String bandars = getBandars((String) k
										.get("ID_NEGERI"), (String) k
										.get("ID_BANDAR"));
								op += " $jquery(\"#txtBandarWaris\").html(\""
										+ bandars + "\"); ";

								String bandarsSurat = getBandars((String) k
										.get("ID_NEGERISURAT"), (String) k
										.get("ID_BANDARSURAT"));
								op += " $jquery(\"#txtBandarWarisSurat\").html(\""
										+ bandarsSurat + "\"); ";

								// soc saudara waris
								myLogger.debug("id saudara:"
										+ k.get("ID_SAUDARA") + " - jantina:"
										+ k.get("JANTINA"));
								String listSaudara = getSaudaraLists((String) k
										.get("JANTINA"), (String) k
										.get("ID_SAUDARA"));
								op += " $jquery(\"#socSaudaraWaris\").html(\""
										+ listSaudara + "\"); ";

							}

							op += " </script>";

							// System.out.println("LB1 :"+lb);

						} else { }

						out.println(op);

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if ("check_ob_kp_lama".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			// System.out.println("RRR:"+no_kp_lama_simati);
			try {

				if (no_kp_lama_simati != "" && no_kp_lama_simati.length() >= 7) {
					Vector<Hashtable<String,String>> lb = userdata.checkKP_list_lama(id_simati,
							id_Orangberkepentingan, no_kp_baru_simati,
							no_kp_lama_simati, no_kp_lain_simati);

					if (userdata.checkKP_Lama_Ob(id_simati,
							id_Orangberkepentingan, no_kp_baru_simati,
							no_kp_lama_simati, no_kp_lain_simati) == true) {
						out.println("<div>No. kad pengenalan lama "
										+ jenis_ob
										+ " sudah wujud!</div> <script type='text/javascript'> document.f1.flag_dup_2.value = 'yes' </script> ");
					} else {
//						System.out.println("SIZE OB::" + lb.size());
						String op = "";
						op += "<script > document.f1.flag_dup_2.value = ''; </script>";
						if (lb.size() > 0) {
							// System.out.println("LA1");
							Hashtable<String,String> k = (Hashtable<String,String>) lb.get(0);

							// System.out.println("NAMA::"+k.get("NAMA_OB").toString());
							op += "<script type='text/javascript'>"
									+ " document.f1.txtNamaOBWaris.value = '"
									+ k.get("NAMA_OB").toString() + "'; "
									+ "document.f1.txtNoKPLamaWaris.value = '"
									+ k.get("NO_KP_LAMA").toString() + "';";

							if (k.get("NO_KP_BARU").toString() != ""
									&& k.get("NO_KP_BARU").toString().length() == 12) {
								op += "document.f1.txtNoKPBaru1Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(0, 6)
										+ "'; "
										+ "document.f1.txtNoKPBaru2Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(6, 8)
										+ "'; "
										+ "document.f1.txtNoKPBaru3Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(8, 12) + "'; ";
							} else {
								op += "document.f1.txtNoKPBaru1Waris.value = ''; "
										+ "document.f1.txtNoKPBaru2Waris.value = ''; "
										+ "document.f1.txtNoKPBaru3Waris.value = ''; ";

							}

							if (k.get("NO_KP_LAIN").toString() != "") {
								op += "document.f1.txtNoKPLainWaris.disabled = ''; ";
							}
							op += "document.f1.txtNoKPLainWaris.value = '"
									+ k.get("NO_KP_LAIN").toString()
									+ "'; "
									+ "document.f1.socJenisKPLainWaris.value = '"
									+ k.get("JENIS_KP").toString()
									+ "'; "
									+ "document.f1.socJantinaWaris.value = '"
									+ k.get("JANTINA").toString()
									+ "'; "
									+ "document.f1.socAgamaWaris.value = '"
									+ k.get("JENIS_AGAMA").toString()
									+ "'; "
									+ "document.f1.socWarganegaraWaris.value = '"
									+ k.get("JENIS_WARGA").toString()
									+ "'; "
									+ "document.f1.txdTarikhLahirWaris.value = '"
									+ k.get("TARIKH_LAHIR").toString()
									+ "'; "
									+ "document.f1.txtUmurWaris.value = '"
									+ k.get("UMUR").toString()
									+ "'; "
									+ "document.f1.socStatusOBWaris.value = '"
									+ k.get("STATUS_OB").toString()
									+ "'; "
									+ "document.f1.socSaudaraWaris.value = '"
									+ k.get("ID_SAUDARA").toString()
									+ "'; "
									+ "document.f1.txtNoSuratBeranakWaris.value = '"
									+ k.get("NO_SURAT_BERANAK").toString()
									+ "'; ";

							if (k.get("STATUS_HIDUP").toString().equals("1")) {
								op += "document.f1.checkHidupWaris.checked = true ; ";
							}

							op += "if(document.f1.txtWaktuKematianWaris!=null)"
									+ "{document.f1.txtWaktuKematianWaris.value = '"
									+ k.get("WAKTU_KEMATIAN").toString()
									+ "'; "
									+ "document.f1.txdTarikhMatiWaris.value = '"
									+ k.get("TARIKH_MATI").toString() + "'; } ";

							op += "document.f1.txtAlamatTerakhir1Waris.value = '"
									+ k.get("ALAMAT_1").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2Waris.value = '"
									+ k.get("ALAMAT_2").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3Waris.value = '"
									+ k.get("ALAMAT_3").toString()
									+ "'; "
									+ "document.f1.txtPoskodWaris.value = '"
									+ k.get("POSKOD").toString()
									+ "'; "
									+ "document.f1.socNegeriWaris.value = '"
									+ k.get("ID_NEGERI").toString()
									+ "'; "
									+ "document.f1.txtBandarWaris.value = '"
									+ k.get("ID_BANDAR").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir1WarisSurat.value = '"
									+ k.get("ALAMAT1_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2WarisSurat.value = '"
									+ k.get("ALAMAT2_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3WarisSurat.value = '"
									+ k.get("ALAMAT3_SURAT").toString()
									+ "'; "
									+ "document.f1.txtPoskodWarisSurat.value = '"
									+ k.get("POSKOD_SURAT").toString()
									+ "'; "
									+ "document.f1.socNegeriWarisSurat.value = '"
									+ k.get("ID_NEGERISURAT").toString()
									+ "'; "
									+ "document.f1.txtBandarWarisSurat.value = '"
									+ k.get("ID_BANDARSURAT").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponWaris.value = '"
									+ k.get("NO_TEL").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponBimbitWaris.value = '"
									+ k.get("NO_HP").toString() + "'; ";

							// Azam add here. auto reload for bandar
							// Load Bandar based on id negeri

							String bandars = getBandars((String) k
									.get("ID_NEGERI"), (String) k
									.get("ID_BANDAR"));
							op += " $jquery(\"#txtBandarWaris\").html(\""
									+ bandars + "\"); ";
							String bandarsSurat = getBandars((String) k
									.get("ID_NEGERISURAT"), (String) k
									.get("ID_BANDARSURAT"));
							op += " $jquery(\"#txtBandarWarisSurat\").html(\""
									+ bandarsSurat + "\"); ";

							op += " </script>";

							// System.out.println("LB1 :"+lb);

						} else {
							/*
							 * 
							 * if(no_kp_baru_simati== "" && no_kp_lain_simati==
							 * "" ){
							 * 
							 * 
							 * op += "<script type='text/javascript'>" +
							 * " document.f1.txtNamaOBWaris.value = ''; " + //
							 * "document.f1.txtNoKPLamaWaris.value = '"
							 * +no_kp_lama_simati+"'; " +
							 * "document.f1.txtNoKPBaru1Waris.value = ''; " +
							 * "document.f1.txtNoKPBaru2Waris.value = ''; " +
							 * "document.f1.txtNoKPBaru3Waris.value = ''; ";
							 * 
							 * 
							 * op +=
							 * "document.f1.txtNoKPLainWaris.disabled = ''; ";
							 * op +="document.f1.txtNoKPLainWaris.value = ''; "
							 * + "document.f1.socJenisKPLainWaris.value = ''; "
							 * + "document.f1.socJantinaWaris.value = '0'; " +
							 * "document.f1.socAgamaWaris.value = ''; " +
							 * "document.f1.socWarganegaraWaris.value = ''; " +
							 * "document.f1.txdTarikhLahirWaris.value = ''; " +
							 * "document.f1.txtUmurWaris.value = ''; " +
							 * "document.f1.socStatusOBWaris.value = ''; " +
							 * "document.f1.socSaudaraWaris.value = '0'; " +
							 * "document.f1.txtNoSuratBeranakWaris.value = ''; "
							 * ;
							 * 
							 * 
							 * 
							 * op +=
							 * "document.f1.checkHidupWaris.checked = false ; ";
							 * 
							 * 
							 * 
							 * op +=
							 * "if(document.f1.txtWaktuKematianWaris!=null)" +
							 * "{document.f1.txtWaktuKematianWaris.value = ''; "
							 * +
							 * "document.f1.txdTarikhMatiWaris.value = ''; } ";
							 * 
							 * op +=
							 * "document.f1.txtAlamatTerakhir1Waris.value = ''; "
							 * +
							 * "document.f1.txtAlamatTerakhir2Waris.value = ''; "
							 * +
							 * "document.f1.txtAlamatTerakhir3Waris.value = ''; "
							 * + "document.f1.txtPoskodWaris.value = ''; "+
							 * "document.f1.socNegeriWaris.value = '0'; "+
							 * "document.f1.txtBandarWaris.value = ''; "+
							 * "document.f1.txtAlamatTerakhir1WarisSurat.value = ''; "
							 * +
							 * "document.f1.txtAlamatTerakhir2WarisSurat.value = ''; "
							 * +
							 * "document.f1.txtAlamatTerakhir3WarisSurat.value = ''; "
							 * + "document.f1.txtPoskodWarisSurat.value = ''; "+
							 * "document.f1.socNegeriWarisSurat.value = '0'; "+
							 * "document.f1.txtBandarWarisSurat.value = ''; "+
							 * "document.f1.txtNoTeleponWaris.value = ''; "+
							 * "document.f1.txtNoTeleponBimbitWaris.value = ''; "
							 * ;
							 * 
							 * 
							 * op += "  " + "  " +
							 * 
							 * " </script>";
							 * 
							 * 
							 * 
							 * }
							 */
						}
						out.println(op);

					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("check_ob_kp_lain".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			try {
				if (no_kp_lain_simati != "" && no_kp_lain_simati.length() >= 7) {
					Vector<Hashtable<String,String>> lb = userdata.checkKP_list_lain(id_simati,id_Orangberkepentingan, no_kp_baru_simati,no_kp_lama_simati, no_kp_lain_simati);

					if (userdata.checkKP_Lain_Ob(id_simati,
							id_Orangberkepentingan, no_kp_baru_simati,
							no_kp_lama_simati, no_kp_lain_simati) == true){
						out.println("<div>No. kad pengenalan lain "
										+ jenis_ob
										+ " sudah wujud!</div> <script type='text/javascript'> document.f1.flag_dup_3.value = 'yes' </script> ");
					} else {
						String op = "";
						op += "<script type='text/javascript'> document.f1.flag_dup_3.value = '';</script> ";
						if (lb.size() > 0) {
							Hashtable<String,String> k = (Hashtable<String,String>) lb.get(0);

							op += "<script type='text/javascript'> "
									+ "document.f1.txtNamaOBWaris.value = '"
									+ k.get("NAMA_OB").toString() + "'; "
									+ "document.f1.txtNoKPLamaWaris.value = '"
									+ k.get("NO_KP_LAMA").toString() + "'; ";
							if (k.get("NO_KP_BARU").toString() != ""
									&& k.get("NO_KP_BARU").toString().length() == 12) {
								op += "document.f1.txtNoKPBaru1Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(0, 6)
										+ "'; "
										+ "document.f1.txtNoKPBaru2Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(6, 8)
										+ "'; "
										+ "document.f1.txtNoKPBaru3Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(8, 12) + "'; ";
							} else {
								op += "document.f1.txtNoKPBaru1Waris.value = ''; "
										+ "document.f1.txtNoKPBaru2Waris.value = ''; "
										+ "document.f1.txtNoKPBaru3Waris.value = ''; ";

							}
							if (k.get("NO_KP_LAIN").toString() != "") {
								op += "document.f1.txtNoKPLainWaris.disabled = ''; ";
							}
							op += "document.f1.txtNoKPLainWaris.value = '"
									+ k.get("NO_KP_LAIN").toString()
									+ "'; "
									+ "document.f1.socJenisKPLainWaris.value = '"
									+ k.get("JENIS_KP").toString()
									+ "'; "
									+ "document.f1.socJantinaWaris.value = '"
									+ k.get("JANTINA").toString()
									+ "'; "
									+ "document.f1.socAgamaWaris.value = '"
									+ k.get("JENIS_AGAMA").toString()
									+ "'; "
									+ "document.f1.socWarganegaraWaris.value = '"
									+ k.get("JENIS_WARGA").toString()
									+ "'; "
									+ "document.f1.txdTarikhLahirWaris.value = '"
									+ k.get("TARIKH_LAHIR").toString()
									+ "'; "
									+ "document.f1.txtUmurWaris.value = '"
									+ k.get("UMUR").toString()
									+ "'; "
									+ "document.f1.socStatusOBWaris.value = '"
									+ k.get("STATUS_OB").toString()
									+ "'; "
									+ "document.f1.socSaudaraWaris.value = '"
									+ k.get("ID_SAUDARA").toString()
									+ "'; "
									+ "document.f1.txtNoSuratBeranakWaris.value = '"
									+ k.get("NO_SURAT_BERANAK").toString()
									+ "'; ";

							if (k.get("STATUS_HIDUP").toString().equals("1")) {
								op += "document.f1.checkHidupWaris.checked = true ; ";
							}

							op += "if(document.f1.txtWaktuKematianWaris!=null)"
									+ "{document.f1.txtWaktuKematianWaris.value = '"
									+ k.get("WAKTU_KEMATIAN").toString()
									+ "'; "
									+ "document.f1.txdTarikhMatiWaris.value = '"
									+ k.get("TARIKH_MATI").toString() + "'; } ";

							op += "document.f1.txtAlamatTerakhir1Waris.value = '"
									+ k.get("ALAMAT_1").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2Waris.value = '"
									+ k.get("ALAMAT_2").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3Waris.value = '"
									+ k.get("ALAMAT_3").toString()
									+ "'; "
									+ "document.f1.txtPoskodWaris.value = '"
									+ k.get("POSKOD").toString()
									+ "'; "
									+ "document.f1.socNegeriWaris.value = '"
									+ k.get("ID_NEGERI").toString()
									+ "'; "
									+ "document.f1.txtBandarWaris.value = '"
									+ k.get("ID_BANDAR").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir1WarisSurat.value = '"
									+ k.get("ALAMAT1_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2WarisSurat.value = '"
									+ k.get("ALAMAT2_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3WarisSurat.value = '"
									+ k.get("ALAMAT3_SURAT").toString()
									+ "'; "
									+ "document.f1.txtPoskodWarisSurat.value = '"
									+ k.get("POSKOD_SURAT").toString()
									+ "'; "
									+ "document.f1.socNegeriWarisSurat.value = '"
									+ k.get("ID_NEGERISURAT").toString()
									+ "'; "
									+ "document.f1.txtBandarWarisSurat.value = '"
									+ k.get("ID_BANDARSURAT").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponWaris.value = '"
									+ k.get("NO_TEL").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponBimbitWaris.value = '"
									+ k.get("NO_HP").toString() + "'; ";

							// Azam add here. auto reload for bandar
							// Load Bandar based on id negeri

							String bandars = getBandars((String) k
									.get("ID_NEGERI"), (String) k
									.get("ID_BANDAR"));
							op += " $jquery(\"#txtBandarWaris\").html(\""
									+ bandars + "\"); ";
							String bandarsSurat = getBandars((String) k
									.get("ID_NEGERISURAT"), (String) k
									.get("ID_BANDARSURAT"));
							op += " $jquery(\"#txtBandarWarisSurat\").html(\""
									+ bandarsSurat + "\"); ";

							op += " </script>";

							// System.out.println("LB1 :"+lb);

						} else {

							if (no_kp_baru_simati == "" && no_kp_lain_simati == "") {
								op += "<script type='text/javascript'>"
										+ " document.f1.txtNamaOBWaris.value = ''; "
										+ "document.f1.txtNoKPLamaWaris.value = ''; "
										+ "document.f1.txtNoKPBaru1Waris.value = ''; "
										+ "document.f1.txtNoKPBaru2Waris.value = ''; "
										+ "document.f1.txtNoKPBaru3Waris.value = ''; ";

								op += "document.f1.txtNoKPLainWaris.disabled = ''; ";
								op += ""
										+
										// "document.f1.txtNoKPLainWaris.value = '"+no_kp_lain_simati+"'; "
										// +
										"document.f1.socJenisKPLainWaris.value = ''; "
										+ "document.f1.socJantinaWaris.value = ''; "
										+ "document.f1.socAgamaWaris.value = ''; "
										+ "document.f1.socWarganegaraWaris.value = ''; "
										+ "document.f1.txdTarikhLahirWaris.value = ''; "
										+ "document.f1.txtUmurWaris.value = ''; "
										+ "document.f1.socStatusOBWaris.value = ''; "
										+ "document.f1.socSaudaraWaris.value = ''; "
										+ "document.f1.txtNoSuratBeranakWaris.value = ''; ";

								op += "document.f1.checkHidupWaris.checked = false ; ";

								op += "if(document.f1.txtWaktuKematianWaris!=null)"
										+ "{document.f1.txtWaktuKematianWaris.value = ''; "
										+ "document.f1.txdTarikhMatiWaris.value = ''; } ";

								op += "document.f1.txtAlamatTerakhir1Waris.value = ''; "
										+ "document.f1.txtAlamatTerakhir2Waris.value = ''; "
										+ "document.f1.txtAlamatTerakhir3Waris.value = ''; "
										+ "document.f1.txtPoskodWaris.value = ''; "
										+ "document.f1.socNegeriWaris.value = ''; "
										+ "document.f1.txtBandarWaris.value = ''; "
										+ "document.f1.txtAlamatTerakhir1WarisSurat.value = ''; "
										+ "document.f1.txtAlamatTerakhir2WarisSurat.value = ''; "
										+ "document.f1.txtAlamatTerakhir3WarisSurat.value = ''; "
										+ "document.f1.txtPoskodWarisSurat.value = ''; "
										+ "document.f1.socNegeriWarisSurat.value = ''; "
										+ "document.f1.txtBandarWarisSurat.value = ''; "
										+ "document.f1.txtNoTeleponWaris.value = ''; "
										+ "document.f1.txtNoTeleponBimbitWaris.value = ''; ";

								op += "  " + "  " +

								" </script>";
							}

						}

						out.println(op);
					}
				} else {
					out.println("<script type='text/javascript'> document.f1.flag_dup_3.value = '';</script> ");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if ("check_ob_beranak".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();

			try {

				if (txtNoSuratBeranakWaris != "" && txtNoSuratBeranakWaris.length() >= 7) {
					Vector<Hashtable<String,String>> lb = userdata.checkKP_list_beranak(id_simati,id_Orangberkepentingan, txtNoSuratBeranakWaris);

					if (userdata.checkKP_Beranak_Ob(id_simati,id_Orangberkepentingan, txtNoSuratBeranakWaris) == true) {
						out.println("<div>No. surat beranak "
										+ jenis_ob
										+ " sudah wujud!</div> <script type='text/javascript'> document.f1.flag_dup_4.value = 'yes' </script> ");
					} else {
						String op = "";
						op += "<script > document.f1.flag_dup_4.value = ''; </script>";
						if (lb.size() > 0) {

							Hashtable<String,String> k = (Hashtable<String,String>) lb.get(0);

							op += "<script type='text/javascript'>"
									+ " document.f1.txtNamaOBWaris.value = '"
									+ k.get("NAMA_OB").toString() + "'; "
									+ "document.f1.txtNoKPLamaWaris.value = '"
									+ k.get("NO_KP_LAMA").toString() + "';";

							if (k.get("NO_KP_BARU").toString() != ""
									&& k.get("NO_KP_BARU").toString().length() == 12) {
								op += "document.f1.txtNoKPBaru1Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(0, 6)
										+ "'; "
										+ "document.f1.txtNoKPBaru2Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(6, 8)
										+ "'; "
										+ "document.f1.txtNoKPBaru3Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(8, 12) + "'; ";
							} else {
								op += "document.f1.txtNoKPBaru1Waris.value = ''; "
										+ "document.f1.txtNoKPBaru2Waris.value = ''; "
										+ "document.f1.txtNoKPBaru3Waris.value = ''; ";

							}

							if (k.get("NO_KP_LAIN").toString() != "") {
								op += "document.f1.txtNoKPLainWaris.disabled = ''; ";
							}
							op += "document.f1.txtNoKPLainWaris.value = '"
									+ k.get("NO_KP_LAIN").toString()
									+ "'; "
									+ "document.f1.socJenisKPLainWaris.value = '"
									+ k.get("JENIS_KP").toString()
									+ "'; "
									+ "document.f1.socJantinaWaris.value = '"
									+ k.get("JANTINA").toString()
									+ "'; "
									+ "document.f1.socAgamaWaris.value = '"
									+ k.get("JENIS_AGAMA").toString()
									+ "'; "
									+ "document.f1.socWarganegaraWaris.value = '"
									+ k.get("JENIS_WARGA").toString()
									+ "'; "
									+ "document.f1.txdTarikhLahirWaris.value = '"
									+ k.get("TARIKH_LAHIR").toString()
									+ "'; "
									+ "document.f1.txtUmurWaris.value = '"
									+ k.get("UMUR").toString()
									+ "'; "
									+ "document.f1.socStatusOBWaris.value = '"
									+ k.get("STATUS_OB").toString()
									+ "'; "
									+ "document.f1.socSaudaraWaris.value = '"
									+ k.get("ID_SAUDARA").toString()
									+ "'; "
									+ "document.f1.txtNoSuratBeranakWaris.value = '"
									+ k.get("NO_SURAT_BERANAK").toString()
									+ "'; ";

							if (k.get("STATUS_HIDUP").toString().equals("1")) {
								op += "document.f1.checkHidupWaris.checked = true ; ";
							}

							op += "if(document.f1.txtWaktuKematianWaris!=null)"
									+ "{document.f1.txtWaktuKematianWaris.value = '"
									+ k.get("WAKTU_KEMATIAN").toString()
									+ "'; "
									+ "document.f1.txdTarikhMatiWaris.value = '"
									+ k.get("TARIKH_MATI").toString() + "'; } ";

							op += "document.f1.txtAlamatTerakhir1Waris.value = '"
									+ k.get("ALAMAT_1").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2Waris.value = '"
									+ k.get("ALAMAT_2").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3Waris.value = '"
									+ k.get("ALAMAT_3").toString()
									+ "'; "
									+ "document.f1.txtPoskodWaris.value = '"
									+ k.get("POSKOD").toString()
									+ "'; "
									+ "document.f1.socNegeriWaris.value = '"
									+ k.get("ID_NEGERI").toString()
									+ "'; "
									+ "document.f1.txtBandarWaris.value = '"
									+ k.get("ID_BANDAR").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir1WarisSurat.value = '"
									+ k.get("ALAMAT1_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2WarisSurat.value = '"
									+ k.get("ALAMAT2_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3WarisSurat.value = '"
									+ k.get("ALAMAT3_SURAT").toString()
									+ "'; "
									+ "document.f1.txtPoskodWarisSurat.value = '"
									+ k.get("POSKOD_SURAT").toString()
									+ "'; "
									+ "document.f1.socNegeriWarisSurat.value = '"
									+ k.get("ID_NEGERISURAT").toString()
									+ "'; "
									+ "document.f1.txtBandarWarisSurat.value = '"
									+ k.get("ID_BANDARSURAT").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponWaris.value = '"
									+ k.get("NO_TEL").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponBimbitWaris.value = '"
									+ k.get("NO_HP").toString() + "'; ";

							// Azam add here. auto reload for bandar
							// Load Bandar based on id negeri

							String bandars = getBandars((String) k.get("ID_NEGERI"), (String) k.get("ID_BANDAR"));
							op += " $jquery(\"#txtBandarWaris\").html(\""
									+ bandars + "\"); ";
							String bandarsSurat = getBandars((String) k.get("ID_NEGERISURAT"), (String) k.get("ID_BANDARSURAT"));
							op += " $jquery(\"#txtBandarWarisSurat\").html(\""
									+ bandarsSurat + "\"); ";

							op += " </script>";

						} else {
							/*
							 * 
							 * if(no_kp_baru_simati== "" && no_kp_lain_simati==
							 * "" ){
							 * 
							 * 
							 * op += "<script type='text/javascript'>" +
							 * " document.f1.txtNamaOBWaris.value = ''; " + //
							 * "document.f1.txtNoKPLamaWaris.value = '"
							 * +no_kp_lama_simati+"'; " +
							 * "document.f1.txtNoKPBaru1Waris.value = ''; " +
							 * "document.f1.txtNoKPBaru2Waris.value = ''; " +
							 * "document.f1.txtNoKPBaru3Waris.value = ''; ";
							 * 
							 * 
							 * op +=
							 * "document.f1.txtNoKPLainWaris.disabled = ''; ";
							 * op +="document.f1.txtNoKPLainWaris.value = ''; "
							 * + "document.f1.socJenisKPLainWaris.value = ''; "
							 * + "document.f1.socJantinaWaris.value = '0'; " +
							 * "document.f1.socAgamaWaris.value = ''; " +
							 * "document.f1.socWarganegaraWaris.value = ''; " +
							 * "document.f1.txdTarikhLahirWaris.value = ''; " +
							 * "document.f1.txtUmurWaris.value = ''; " +
							 * "document.f1.socStatusOBWaris.value = ''; " +
							 * "document.f1.socSaudaraWaris.value = '0'; " +
							 * "document.f1.txtNoSuratBeranakWaris.value = ''; "
							 * ;
							 * 
							 * 
							 * 
							 * op +=
							 * "document.f1.checkHidupWaris.checked = false ; ";
							 * 
							 * 
							 * 
							 * op +=
							 * "if(document.f1.txtWaktuKematianWaris!=null)" +
							 * "{document.f1.txtWaktuKematianWaris.value = ''; "
							 * +
							 * "document.f1.txdTarikhMatiWaris.value = ''; } ";
							 * 
							 * op +=
							 * "document.f1.txtAlamatTerakhir1Waris.value = ''; "
							 * +
							 * "document.f1.txtAlamatTerakhir2Waris.value = ''; "
							 * +
							 * "document.f1.txtAlamatTerakhir3Waris.value = ''; "
							 * + "document.f1.txtPoskodWaris.value = ''; "+
							 * "document.f1.socNegeriWaris.value = '0'; "+
							 * "document.f1.txtBandarWaris.value = ''; "+
							 * "document.f1.txtAlamatTerakhir1WarisSurat.value = ''; "
							 * +
							 * "document.f1.txtAlamatTerakhir2WarisSurat.value = ''; "
							 * +
							 * "document.f1.txtAlamatTerakhir3WarisSurat.value = ''; "
							 * + "document.f1.txtPoskodWarisSurat.value = ''; "+
							 * "document.f1.socNegeriWarisSurat.value = '0'; "+
							 * "document.f1.txtBandarWarisSurat.value = ''; "+
							 * "document.f1.txtNoTeleponWaris.value = ''; "+
							 * "document.f1.txtNoTeleponBimbitWaris.value = ''; "
							 * ;
							 * 
							 * 
							 * op += "  " + "  " +
							 * 
							 * " </script>";
							 * 
							 * 
							 * 
							 * }
							 */
						}
						out.println(op);

					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if ("check_ob_beranak_onload".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();

			try {
				if (txtNoSuratBeranakWaris.length() >= 7) {
					Vector<Hashtable<String,String>> lb = userdata.checkKP_list_beranak(id_simati,id_Orangberkepentingan, txtNoSuratBeranakWaris);

					if (userdata.checkKP_Beranak_Ob(id_simati,id_Orangberkepentingan, txtNoSuratBeranakWaris) == true){
						out.println("<div>No. surat beranak "
										+ jenis_ob
										+ " sudah wujud!</div> <script type='text/javascript'> document.f1.flag_dup_4.value = 'yes' </script> ");
					} else {
						String op = "";
						op += "<script type='text/javascript'> document.f1.flag_dup_4.value = ''; </script>";
						if (lb.size() > 0) {
							Hashtable<String,String> k = (Hashtable<String,String>) lb.get(0);

							op += "<script type='text/javascript'> "
									+ "document.f1.txtNamaOBWaris.value = '"
									+ k.get("NAMA_OB").toString() + "'; "
									+ "document.f1.txtNoKPLamaWaris.value = '"
									+ k.get("NO_KP_LAMA").toString() + "'; ";

							if (k.get("NO_KP_BARU").toString() != ""
									&& k.get("NO_KP_BARU").toString().length() == 12) {
								op += "document.f1.txtNoKPBaru1Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(0, 6)
										+ "'; "
										+ "document.f1.txtNoKPBaru2Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(6, 8)
										+ "'; "
										+ "document.f1.txtNoKPBaru3Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(8, 12) + "'; ";
							} else {
								op += "document.f1.txtNoKPBaru1Waris.value = ''; "
										+ "document.f1.txtNoKPBaru2Waris.value = ''; "
										+ "document.f1.txtNoKPBaru3Waris.value = ''; ";

							}

							if (k.get("NO_KP_LAIN").toString() != "") {
								op += "document.f1.txtNoKPLainWaris.disabled = ''; ";
							}
							op += "document.f1.txtNoKPLainWaris.value = '"
									+ k.get("NO_KP_LAIN").toString()
									+ "'; "
									+ "document.f1.socJenisKPLainWaris.value = '"
									+ k.get("JENIS_KP").toString()
									+ "'; "
									+ "document.f1.socJantinaWaris.value = '"
									+ k.get("JANTINA").toString()
									+ "'; "
									+ "document.f1.socAgamaWaris.value = '"
									+ k.get("JENIS_AGAMA").toString()
									+ "'; "
									+ "document.f1.socWarganegaraWaris.value = '"
									+ k.get("JENIS_WARGA").toString()
									+ "'; "
									+ "document.f1.txdTarikhLahirWaris.value = '"
									+ k.get("TARIKH_LAHIR").toString()
									+ "'; "
									+ "document.f1.txtUmurWaris.value = '"
									+ k.get("UMUR").toString()
									+ "'; "
									+ "document.f1.socStatusOBWaris.value = '"
									+ k.get("STATUS_OB").toString()
									+ "'; "
									+ "document.f1.socSaudaraWaris.value = '"
									+ k.get("ID_SAUDARA").toString()
									+ "'; "
									+ "document.f1.txtNoSuratBeranakWaris.value = '"
									+ k.get("NO_SURAT_BERANAK").toString()
									+ "'; ";

							if (k.get("STATUS_HIDUP").toString().equals("1")) {
								op += "document.f1.checkHidupWaris.checked = true ; ";
							}

							op += "if(document.f1.txtWaktuKematianWaris!=null)"
									+ "{document.f1.txtWaktuKematianWaris.value = '"
									+ k.get("WAKTU_KEMATIAN").toString()
									+ "'; "
									+ "document.f1.txdTarikhMatiWaris.value = '"
									+ k.get("TARIKH_MATI").toString() + "'; } ";

							op += "document.f1.txtAlamatTerakhir1Waris.value = '"
									+ k.get("ALAMAT_1").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2Waris.value = '"
									+ k.get("ALAMAT_2").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3Waris.value = '"
									+ k.get("ALAMAT_3").toString()
									+ "'; "
									+ "document.f1.txtPoskodWaris.value = '"
									+ k.get("POSKOD").toString()
									+ "'; "
									+ "document.f1.socNegeriWaris.value = '"
									+ k.get("ID_NEGERI").toString()
									+ "'; "
									+ "document.f1.txtBandarWaris.value = '"
									+ k.get("ID_BANDAR").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir1WarisSurat.value = '"
									+ k.get("ALAMAT1_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2WarisSurat.value = '"
									+ k.get("ALAMAT2_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3WarisSurat.value = '"
									+ k.get("ALAMAT3_SURAT").toString()
									+ "'; "
									+ "document.f1.txtPoskodWarisSurat.value = '"
									+ k.get("POSKOD_SURAT").toString()
									+ "'; "
									+ "document.f1.socNegeriWarisSurat.value = '"
									+ k.get("ID_NEGERISURAT").toString()
									+ "'; "
									+ "document.f1.txtBandarWarisSurat.value = '"
									+ k.get("ID_BANDARSURAT").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponWaris.value = '"
									+ k.get("NO_TEL").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponBimbitWaris.value = '"
									+ k.get("NO_HP").toString() + "'; ";

							op += "  " + "  " +

							" </script>";

						} else {

						}
						out.println(op);

					}

					// System.out.println("LB2 "+lb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if ("check_ob_kp_baru_onload".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			try {
				if (no_kp_baru_simati.length() == 12) {

					Vector<Hashtable<String,String>> lb = userdata.checkKP_list_baru(id_simati
											,id_Orangberkepentingan
											,no_kp_baru_simati
											,no_kp_lama_simati
											,no_kp_lain_simati);

					if (userdata.checkKP_Baru_Ob(id_simati,id_Orangberkepentingan,no_kp_baru_simati,no_kp_lama_simati
									, no_kp_lain_simati) == true){
						out.println("<div>No. kad pengenalan baru "
										+ jenis_ob
										+ " sudah wujud!</div> <script type='text/javascript'> document.f1.flag_dup_1.value = 'yes' </script> ");
					} else {
						String op = "";
						op += "<script type='text/javascript'> document.f1.flag_dup_1.value = ''; </script>";
						if (lb.size() > 0) {
							Hashtable<String,String> k = (Hashtable<String,String>) lb.get(0);

							op += "<script type='text/javascript'> "
									+ "document.f1.txtNamaOBWaris.value = '"
									+ k.get("NAMA_OB").toString() + "'; "
									+ "document.f1.txtNoKPLamaWaris.value = '"
									+ k.get("NO_KP_LAMA").toString() + "'; ";

							if (k.get("NO_KP_BARU").toString() != ""
									&& k.get("NO_KP_BARU").toString().length() == 12) {
								op += "document.f1.txtNoKPBaru1Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(0, 6)
										+ "'; "
										+ "document.f1.txtNoKPBaru2Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(6, 8)
										+ "'; "
										+ "document.f1.txtNoKPBaru3Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(8, 12) + "'; ";
							} else {
								op += "document.f1.txtNoKPBaru1Waris.value = ''; "
										+ "document.f1.txtNoKPBaru2Waris.value = ''; "
										+ "document.f1.txtNoKPBaru3Waris.value = ''; ";

							}

							if (k.get("NO_KP_LAIN").toString() != "") {
								op += "document.f1.txtNoKPLainWaris.disabled = ''; ";
							}
							op += "document.f1.txtNoKPLainWaris.value = '"
									+ k.get("NO_KP_LAIN").toString()
									+ "'; "
									+ "document.f1.socJenisKPLainWaris.value = '"
									+ k.get("JENIS_KP").toString()
									+ "'; "
									+ "document.f1.socJantinaWaris.value = '"
									+ k.get("JANTINA").toString()
									+ "'; "
									+ "document.f1.socAgamaWaris.value = '"
									+ k.get("JENIS_AGAMA").toString()
									+ "'; "
									+ "document.f1.socWarganegaraWaris.value = '"
									+ k.get("JENIS_WARGA").toString()
									+ "'; "
									+ "document.f1.txdTarikhLahirWaris.value = '"
									+ k.get("TARIKH_LAHIR").toString()
									+ "'; "
									+ "document.f1.txtUmurWaris.value = '"
									+ k.get("UMUR").toString()
									+ "'; "
									+ "document.f1.socStatusOBWaris.value = '"
									+ k.get("STATUS_OB").toString()
									+ "'; "
									+ "document.f1.socSaudaraWaris.value = '"
									+ k.get("ID_SAUDARA").toString()
									+ "'; "
									+ "document.f1.txtNoSuratBeranakWaris.value = '"
									+ k.get("NO_SURAT_BERANAK").toString()
									+ "'; ";

							if (k.get("STATUS_HIDUP").toString().equals("1")) {
								op += "document.f1.checkHidupWaris.checked = true ; ";
							}

							op += "if(document.f1.txtWaktuKematianWaris!=null)"
									+ "{document.f1.txtWaktuKematianWaris.value = '"
									+ k.get("WAKTU_KEMATIAN").toString()
									+ "'; "
									+ "document.f1.txdTarikhMatiWaris.value = '"
									+ k.get("TARIKH_MATI").toString() + "'; } ";

							op += "document.f1.txtAlamatTerakhir1Waris.value = '"
									+ k.get("ALAMAT_1").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2Waris.value = '"
									+ k.get("ALAMAT_2").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3Waris.value = '"
									+ k.get("ALAMAT_3").toString()
									+ "'; "
									+ "document.f1.txtPoskodWaris.value = '"
									+ k.get("POSKOD").toString()
									+ "'; "
									+ "document.f1.socNegeriWaris.value = '"
									+ k.get("ID_NEGERI").toString()
									+ "'; "
									+ "document.f1.txtBandarWaris.value = '"
									+ k.get("ID_BANDAR").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir1WarisSurat.value = '"
									+ k.get("ALAMAT1_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2WarisSurat.value = '"
									+ k.get("ALAMAT2_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3WarisSurat.value = '"
									+ k.get("ALAMAT3_SURAT").toString()
									+ "'; "
									+ "document.f1.txtPoskodWarisSurat.value = '"
									+ k.get("POSKOD_SURAT").toString()
									+ "'; "
									+ "document.f1.socNegeriWarisSurat.value = '"
									+ k.get("ID_NEGERISURAT").toString()
									+ "'; "
									+ "document.f1.txtBandarWarisSurat.value = '"
									+ k.get("ID_BANDARSURAT").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponWaris.value = '"
									+ k.get("NO_TEL").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponBimbitWaris.value = '"
									+ k.get("NO_HP").toString() + "'; ";
							// }

							op += "  " + "  " +

							" </script>";

						} else { }
						out.println(op);

					}
					// System.out.println("LB2 "+lb);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if ("check_ob_kp_lama_onload".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			try {
				if (no_kp_lama_simati != "" && no_kp_lama_simati.length() >= 7) {
					Vector<Hashtable<String,String>> lb = userdata.checkKP_list_lama(id_simati
																	,id_Orangberkepentingan
																	,no_kp_baru_simati
																	,no_kp_lama_simati
																	,no_kp_lain_simati);

					if (userdata.checkKP_Lama_Ob(id_simati,id_Orangberkepentingan, no_kp_baru_simati,no_kp_lama_simati
						, no_kp_lain_simati) == true) {
						out.println("<div>No. kad pengenalan lama "
										+ jenis_ob
										+ " sudah wujud!</div> <script type='text/javascript'> document.f1.flag_dup_2.value = 'yes' </script> ");
					} else {
						String op = "";
						op += "<script type='text/javascript'> document.f1.flag_dup_2.value = ''; </script>";
						if (lb.size() > 0) {
							Hashtable<String,String> k = (Hashtable<String,String>) lb.get(0);

							op += "<script type='text/javascript'> "
									+ "document.f1.txtNamaOBWaris.value = '"
									+ k.get("NAMA_OB").toString() + "'; "
									+ "document.f1.txtNoKPLamaWaris.value = '"
									+ k.get("NO_KP_LAMA").toString() + "'; ";

							if (k.get("NO_KP_BARU").toString() != ""
									&& k.get("NO_KP_BARU").toString().length() == 12) {
								op += "document.f1.txtNoKPBaru1Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(0, 6)
										+ "'; "
										+ "document.f1.txtNoKPBaru2Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(6, 8)
										+ "'; "
										+ "document.f1.txtNoKPBaru3Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(8, 12) + "'; ";
							} else {
								op += "document.f1.txtNoKPBaru1Waris.value = ''; "
										+ "document.f1.txtNoKPBaru2Waris.value = ''; "
										+ "document.f1.txtNoKPBaru3Waris.value = ''; ";

							}
							if (k.get("NO_KP_LAIN").toString() != "") {
								op += "document.f1.txtNoKPLainWaris.disabled = ''; ";
							}
							op += "document.f1.txtNoKPLainWaris.value = '"
									+ k.get("NO_KP_LAIN").toString()
									+ "'; "
									+ "document.f1.socJenisKPLainWaris.value = '"
									+ k.get("JENIS_KP").toString()
									+ "'; "
									+ "document.f1.socJantinaWaris.value = '"
									+ k.get("JANTINA").toString()
									+ "'; "
									+ "document.f1.socAgamaWaris.value = '"
									+ k.get("JENIS_AGAMA").toString()
									+ "'; "
									+ "document.f1.socWarganegaraWaris.value = '"
									+ k.get("JENIS_WARGA").toString()
									+ "'; "
									+ "document.f1.txdTarikhLahirWaris.value = '"
									+ k.get("TARIKH_LAHIR").toString()
									+ "'; "
									+ "document.f1.txtUmurWaris.value = '"
									+ k.get("UMUR").toString()
									+ "'; "
									+ "document.f1.socStatusOBWaris.value = '"
									+ k.get("STATUS_OB").toString()
									+ "'; "
									+ "document.f1.socSaudaraWaris.value = '"
									+ k.get("ID_SAUDARA").toString()
									+ "'; "
									+ "document.f1.txtNoSuratBeranakWaris.value = '"
									+ k.get("NO_SURAT_BERANAK").toString()
									+ "'; ";

							if (k.get("STATUS_HIDUP").toString().equals("1")) {
								op += "document.f1.checkHidupWaris.checked = true ; ";
							}

							op += "if(document.f1.txtWaktuKematianWaris!=null)"
									+ "{document.f1.txtWaktuKematianWaris.value = '"
									+ k.get("WAKTU_KEMATIAN").toString()
									+ "'; "
									+ "document.f1.txdTarikhMatiWaris.value = '"
									+ k.get("TARIKH_MATI").toString() + "'; } ";

							op += "document.f1.txtAlamatTerakhir1Waris.value = '"
									+ k.get("ALAMAT_1").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2Waris.value = '"
									+ k.get("ALAMAT_2").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3Waris.value = '"
									+ k.get("ALAMAT_3").toString()
									+ "'; "
									+ "document.f1.txtPoskodWaris.value = '"
									+ k.get("POSKOD").toString()
									+ "'; "
									+ "document.f1.socNegeriWaris.value = '"
									+ k.get("ID_NEGERI").toString()
									+ "'; "
									+ "document.f1.txtBandarWaris.value = '"
									+ k.get("ID_BANDAR").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir1WarisSurat.value = '"
									+ k.get("ALAMAT1_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2WarisSurat.value = '"
									+ k.get("ALAMAT2_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3WarisSurat.value = '"
									+ k.get("ALAMAT3_SURAT").toString()
									+ "'; "
									+ "document.f1.txtPoskodWarisSurat.value = '"
									+ k.get("POSKOD_SURAT").toString()
									+ "'; "
									+ "document.f1.socNegeriWarisSurat.value = '"
									+ k.get("ID_NEGERISURAT").toString()
									+ "'; "
									+ "document.f1.txtBandarWarisSurat.value = '"
									+ k.get("ID_BANDARSURAT").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponWaris.value = '"
									+ k.get("NO_TEL").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponBimbitWaris.value = '"
									+ k.get("NO_HP").toString() + "'; ";

							op += "  " + "  " +

							" </script>";

							// System.out.println("LB1 :"+lb);

						} else {	}
						out.println(op);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("check_ob_kp_lain_onload".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			try {

				if (no_kp_lain_simati != "" && no_kp_lain_simati.length() >= 7) {
					Vector<Hashtable<String,String>> lb = userdata.checkKP_list_lain(id_simati
																	,id_Orangberkepentingan
																	, no_kp_baru_simati
																	,no_kp_lama_simati
																	, no_kp_lain_simati);

					if (userdata.checkKP_Lain_Ob(id_simati,id_Orangberkepentingan, no_kp_baru_simati,no_kp_lama_simati
						, no_kp_lain_simati) == true){
						out.println("<div>No. kad pengenalan lain "
										+ jenis_ob
										+ " sudah wujud!</div> <script type='text/javascript'> document.f1.flag_dup_3.value = 'yes' </script> ");
					} else {
						String op = "";
						op += "<script type='text/javascript'> document.f1.flag_dup_3.value = '';</script> ";
						if (lb.size() > 0) {
							Hashtable<String,String> k = (Hashtable<String,String>) lb.get(0);

							op += "<script type='text/javascript'> "
									+ "document.f1.txtNamaOBWaris.value = '"
									+ k.get("NAMA_OB").toString() + "'; "
									+ "document.f1.txtNoKPLamaWaris.value = '"
									+ k.get("NO_KP_LAMA").toString() + "'; ";

							if (k.get("NO_KP_BARU").toString() != ""
									&& k.get("NO_KP_BARU").toString().length() == 12) {
								op += "document.f1.txtNoKPBaru1Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(0, 6)
										+ "'; "
										+ "document.f1.txtNoKPBaru2Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(6, 8)
										+ "'; "
										+ "document.f1.txtNoKPBaru3Waris.value = '"
										+ k.get("NO_KP_BARU").toString()
												.substring(8, 12) + "'; ";
							} else {
								op += "document.f1.txtNoKPBaru1Waris.value = ''; "
										+ "document.f1.txtNoKPBaru2Waris.value = ''; "
										+ "document.f1.txtNoKPBaru3Waris.value = ''; ";

							}
							if (k.get("NO_KP_LAIN").toString() != "") {
								op += "document.f1.txtNoKPLainWaris.disabled = ''; ";
							}
							op += "document.f1.txtNoKPLainWaris.value = '"
									+ k.get("NO_KP_LAIN").toString()
									+ "'; "
									+ "document.f1.socJenisKPLainWaris.value = '"
									+ k.get("JENIS_KP").toString()
									+ "'; "
									+ "document.f1.socJantinaWaris.value = '"
									+ k.get("JANTINA").toString()
									+ "'; "
									+ "document.f1.socAgamaWaris.value = '"
									+ k.get("JENIS_AGAMA").toString()
									+ "'; "
									+ "document.f1.socWarganegaraWaris.value = '"
									+ k.get("JENIS_WARGA").toString()
									+ "'; "
									+ "document.f1.txdTarikhLahirWaris.value = '"
									+ k.get("TARIKH_LAHIR").toString()
									+ "'; "
									+ "document.f1.txtUmurWaris.value = '"
									+ k.get("UMUR").toString()
									+ "'; "
									+ "document.f1.socStatusOBWaris.value = '"
									+ k.get("STATUS_OB").toString()
									+ "'; "
									+ "document.f1.socSaudaraWaris.value = '"
									+ k.get("ID_SAUDARA").toString()
									+ "'; "
									+ "document.f1.txtNoSuratBeranakWaris.value = '"
									+ k.get("NO_SURAT_BERANAK").toString()
									+ "'; ";

							if (k.get("STATUS_HIDUP").toString().equals("1")) {
								op += "document.f1.checkHidupWaris.checked = true ; ";
							}

							op += "if(document.f1.txtWaktuKematianWaris!=null)"
									+ "{document.f1.txtWaktuKematianWaris.value = '"
									+ k.get("WAKTU_KEMATIAN").toString()
									+ "'; "
									+ "document.f1.txdTarikhMatiWaris.value = '"
									+ k.get("TARIKH_MATI").toString() + "'; } ";

							op += "document.f1.txtAlamatTerakhir1Waris.value = '"
									+ k.get("ALAMAT_1").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2Waris.value = '"
									+ k.get("ALAMAT_2").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3Waris.value = '"
									+ k.get("ALAMAT_3").toString()
									+ "'; "
									+ "document.f1.txtPoskodWaris.value = '"
									+ k.get("POSKOD").toString()
									+ "'; "
									+ "document.f1.socNegeriWaris.value = '"
									+ k.get("ID_NEGERI").toString()
									+ "'; "
									+ "document.f1.txtBandarWaris.value = '"
									+ k.get("ID_BANDAR").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir1WarisSurat.value = '"
									+ k.get("ALAMAT1_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir2WarisSurat.value = '"
									+ k.get("ALAMAT2_SURAT").toString()
									+ "'; "
									+ "document.f1.txtAlamatTerakhir3WarisSurat.value = '"
									+ k.get("ALAMAT3_SURAT").toString()
									+ "'; "
									+ "document.f1.txtPoskodWarisSurat.value = '"
									+ k.get("POSKOD_SURAT").toString()
									+ "'; "
									+ "document.f1.socNegeriWarisSurat.value = '"
									+ k.get("ID_NEGERISURAT").toString()
									+ "'; "
									+ "document.f1.txtBandarWarisSurat.value = '"
									+ k.get("ID_BANDARSURAT").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponWaris.value = '"
									+ k.get("NO_TEL").toString()
									+ "'; "
									+ "document.f1.txtNoTeleponBimbitWaris.value = '"
									+ k.get("NO_HP").toString() + "'; ";
							op += "  " + "  " +

							" </script>";

							// System.out.println("LB1 :"+lb);

						} else {	}
						out.println(op);
					}

				} else {
					out.println("<script type='text/javascript'> document.f1.flag_dup_3.value = '';</script> ");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if ("check_no_lot".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			try {
				if (no_lot_hta != "" && no_lot_hta.length() > 3) {
					if (userdata.check_nolot_pt(no_lot_hta, id_harta) == true){
						out.println("<div>No. lot / pt '" + no_lot_hta
								+ "' sudah wujud!</div>");
					} else {
						out.println("");

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if ("check_umur_saksi".equals(submit)) {
//			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			// System.out.println("ADA UMUR SAKSI");
			try {
				if (umursaksi > 0 && umursaksi < 18) {
					out.println("<div>Sila pastikan saksi berumur 18 tahun keatas!</div>");
				} else {
					out.println("");

				}
				// out.println("TEST");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if ("check_harta".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();

			try {
				// if(no_lot_hta!= ""){
				if (userdata.getDaerahByNegeriUser((String) session.getAttribute("_ekptg_user_id"), idPermohonan,id_daerah
					,id_harta) == false) {
					out.println("<div>Sila masukkan maklumat harta berdasarkan unit jagaan terlebih dahulu!</div> <script type='text/javascript'> document.f1.save_harta.value = 'yes' </script> ");
				} else {
					out.println("<script type='text/javascript'> document.f1.save_harta.value = 'no' </script> ");
				}

				// }
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if ("getalamat_raya_OB".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			Vector<Hashtable<String,String>> alamat_raya = new Vector<Hashtable<String,String>>();
			try {
				if (!jenis_pej.equals("")) {
					alamat_raya = userdata.getAlamatRaya(jenis_pej);

					//System.out.println("ALAMAT RAYA:"+alamat_raya);
					Hashtable<String,String> k = (Hashtable<String,String>) alamat_raya.get(0);
					out.println("   "
									+ "  <script type='text/javascript'>" +
									" document.f1.socNegeriPenting.value = '"
									+ removeSymbol(k.get("id_negeri")
											.toString())
									+ "'; "
									+ " document.f1.socNegeriPenting_D.value = '"
									+ removeSymbol(k.get("nama_negeri")
											.toString())
									+ "'; "
									+ " document.f1.txtBandarWarisSurat.value = '"
									+ removeSymbol(k.get("id_bandar")
											.toString())
									+ "'; "
									+ " document.f1.txtBandarWarisSurat_D.value = '"
									+ removeSymbol(k.get("nama_bandar")
											.toString())
									+ "'; "
									+

									" document.f1.txtNamaOBPenting.value = '"
									+ k.get("nama_pejabat").toString()
									+ "';"
									+ " document.f1.txtNamaOBPenting_D.value = '"
									+ k.get("nama_pejabat").toString()
									+ "';"
									+ " document.f1.txtAlamatTerakhir1Penting.value = '"
									+ removeSymbol(k.get("alamat1").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir2Penting.value = '"
									+ removeSymbol(k.get("alamat2").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir3Penting.value = '"
									+ removeSymbol(k.get("alamat3").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir1Penting_D.value = '"
									+ removeSymbol(k.get("alamat1").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir2Penting_D.value = '"
									+ removeSymbol(k.get("alamat2").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir3Penting_D.value = '"
									+ removeSymbol(k.get("alamat3").toString())
									+ "'; "
									+ " document.f1.txtPoskodPenting.value = '"
									+ removeSymbol(k.get("poskod").toString())
									+ "'; "
									+ " document.f1.txtPoskodPenting_D.value = '"
									+ removeSymbol(k.get("poskod").toString())
									+ "'; "
									+ " document.f1.txtBandarPenting_D.value = '"
									+ removeSymbol(k.get("nama_bandar")
											.toString())
									+ "'; "
									+

									" document.f1.txtAlamatTerakhir1WarisSurat.value = '"
									+ removeSymbol(k.get("alamat1").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir2WarisSurat.value = '"
									+ removeSymbol(k.get("alamat2").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir3WarisSurat.value = '"
									+ removeSymbol(k.get("alamat3").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir1WarisSurat_D.value = '"
									+ removeSymbol(k.get("alamat1").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir2WarisSurat_D.value = '"
									+ removeSymbol(k.get("alamat2").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir3WarisSurat_D.value = '"
									+ removeSymbol(k.get("alamat3").toString())
									+ "'; "
									+ " document.f1.txtPoskodWarisSurat.value = '"
									+ removeSymbol(k.get("poskod").toString())
									+ "'; "
									+ " document.f1.txtPoskodWarisSurat_D.value = '"
									+ removeSymbol(k.get("poskod").toString())
									+ "'; "
									+ " document.f1.socNegeriWarisSurat.value = '"
									+ removeSymbol(k.get("id_negeri")
											.toString())
									+ "'; "
									+ " document.f1.socNegeriWarisSurat_D.value = '"
									+ removeSymbol(k.get("nama_negeri")
											.toString())
									+ "'; "
									+

									" document.f1.txtNoTeleponPenting.value = '"
									+ removeSymbol(k.get("no_tel").toString())
									+ "'; "
									+ " document.f1.txtNoTeleponPenting_D.value = '"
									+ removeSymbol(k.get("no_tel").toString())
									+ "'; "
									+ " document.f1.txtNoFaxPenting.value = '"
									+ removeSymbol(k.get("no_fax").toString())
									+ "'; "
									+ " document.f1.txtNoFaxPenting_D.value = '"
									+ removeSymbol(k.get("no_fax").toString())
									+ "'; "
									+

									"</script>"
									+ "<input type='hidden' name = 'txtBandarPenting' value='"
									+ k.get("id_bandar").toString()
									+ "' />  "
									+ "<input type='hidden' name = 'txtBandarWarisSurat' value='"
									+ k.get("id_bandar").toString()
									+ "' />  "
									+ " <script>"
									+ " document.f1.txtBandarWarisSurat.value = '"
									+ k.get("id_bandar").toString()
									+ "'; "
									+ " document.f1.txtBandarPenting.value = '"
									+ k.get("id_bandar").toString()
									+ "'; "
									+ "</script");

				} else {
					out.println("");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if ("getalamat_raya_OBUP".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			Vector<Hashtable<String,String>> alamat_raya = new Vector<Hashtable<String,String>>();
			try {
				// jenis_pej = "311";
				if (!jenis_pej.equals("")) {
					alamat_raya = userdata.getAlamatRaya(jenis_pej);
					Hashtable<String,String> k = (Hashtable<String,String>) alamat_raya.get(0);
					out.println("   "
									+ "  <script type='text/javascript'>" +
									" document.f1.socNegeriPentingU.value = '"
									+ removeSymbol(k.get("id_negeri").toString())
									+ "'; "
									+ " document.f1.socNegeriPenting_D.value = '"
									+ removeSymbol(k.get("nama_negeri").toString())
									+ "'; "
									+ " document.f1.txtBandarWarisSurat.value = '"
									+ removeSymbol(k.get("id_bandar").toString())
									+ "'; "
									+ " document.f1.txtBandarWarisSurat_D.value = '"
									+ removeSymbol(k.get("nama_bandar")
											.toString())
									+ "'; "
									+" document.f1.txtNamaOBPentingU.value = '"
									+ k.get("nama_pejabat").toString()
									+ "';"
									+ " document.f1.txtNamaOBPenting_D.value = '"
									+ k.get("nama_pejabat").toString()
									+ "';"
									+ " document.f1.txtAlamatTerakhir1PentingU.value = '"
									+ removeSymbol(k.get("alamat1").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir2PentingU.value = '"
									+ removeSymbol(k.get("alamat2").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir3PentingU.value = '"
									+ removeSymbol(k.get("alamat3").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir1Penting_D.value = '"
									+ removeSymbol(k.get("alamat1").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir2Penting_D.value = '"
									+ removeSymbol(k.get("alamat2").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir3Penting_D.value = '"
									+ removeSymbol(k.get("alamat3").toString())
									+ "'; "
									+ " document.f1.txtPoskodPentingU.value = '"
									+ removeSymbol(k.get("poskod").toString())
									+ "'; "
									+ " document.f1.txtPoskodPenting_D.value = '"
									+ removeSymbol(k.get("poskod").toString())
									+ "'; "
									+ " document.f1.txtBandarPenting_D.value = '"
									+ removeSymbol(k.get("nama_bandar")
											.toString())
									+ "'; "
									+" document.f1.txtAlamatTerakhir1WarisSurat.value = '"
									+ removeSymbol(k.get("alamat1").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir2WarisSurat.value = '"
									+ removeSymbol(k.get("alamat2").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir3WarisSurat.value = '"
									+ removeSymbol(k.get("alamat3").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir1WarisSurat_D.value = '"
									+ removeSymbol(k.get("alamat1").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir2WarisSurat_D.value = '"
									+ removeSymbol(k.get("alamat2").toString())
									+ "'; "
									+ " document.f1.txtAlamatTerakhir3WarisSurat_D.value = '"
									+ removeSymbol(k.get("alamat3").toString())
									+ "'; "
									+ " document.f1.txtPoskodWarisSurat.value = '"
									+ removeSymbol(k.get("poskod").toString())
									+ "'; "
									+ " document.f1.txtPoskodWarisSurat_D.value = '"
									+ removeSymbol(k.get("poskod").toString())
									+ "'; "
									+ " document.f1.socNegeriWarisSurat.value = '"
									+ removeSymbol(k.get("id_negeri")
											.toString())
									+ "'; "
									+ " document.f1.socNegeriWarisSurat_D.value = '"
									+ removeSymbol(k.get("nama_negeri")
											.toString())
									+ "'; "
									+" document.f1.txtNoTeleponPentingU.value = '"
									+ removeSymbol(k.get("no_tel").toString())
									+ "'; "
									+ " document.f1.txtNoTeleponPenting_D.value = '"
									+ removeSymbol(k.get("no_tel").toString())
									+ "'; "
									+ " document.f1.txtNoFaxPenting.value = '"
									+ removeSymbol(k.get("no_fax").toString())
									+ "'; "
									+ " document.f1.txtNoFaxPenting_D.value = '"
									+ removeSymbol(k.get("no_fax").toString())
									+ "'; "
									+
									"</script>"
									+ "<input type='hidden' name = 'txtBandarPentingU' value='"
									+ k.get("id_bandar").toString()
									+ "' />  "
									+ "<input type='hidden' name = 'txtBandarWarisSurat' value='"
									+ k.get("id_bandar").toString()
									+ "' />  "
									+ " <script>"
									+ " document.f1.txtBandarWarisSurat_X.value = '"
									+ k.get("id_bandar").toString()
									+ "'; "
									+ " document.f1.txtBandarPentingU_X.value = '"
									+ k.get("id_bandar").toString() + "'; " +
									// " document.f1.txtBandarPentingU[1].disabled = true; "
									// +
									// " document.f1.txtBandarWarisSurat[1].disabled = true; "
									// +
									"</script>");

				} else {
					out.println("");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if ("getalamat_raya".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			Vector<Hashtable<String,String>> alamat_raya = new Vector<Hashtable<String,String>>();
			try {
				// jenis_pej = "311";
				if (!jenis_pej.equals("")) {
					alamat_raya = userdata.getAlamatRaya(jenis_pej);
//					System.out.println("JENIS PPP :" + alamat_raya);
					Hashtable<String,String> k = (Hashtable<String,String>) alamat_raya.get(0);
					// System.out.println("ALAMAT RAYA T :"+k.get("alamat1").toString());
					// k.get("nama_pejabat").toString();

					// //System.out.println("NAMA PEJABAT RAYA :"+k.get("nama_pejabat").toString());
					// if(jenis_pej != "3" && jenis_pej != "175" && jenis_pej !=
					// "999"){
					out.println("" + "  <script type='text/javascript'> "
									+ "  document.f1.txtPoskod[1].value = '"
									+ k.get("poskod").toString()
									+ "';"
									+ " document.f1.txtNamaPemohon[1].value = '"
									+ k.get("nama_pejabat").toString()
									+ "';"
									+ " document.f1.socNegeri_dis.value = '"
									+ k.get("nama_negeri").toString()
									+ "'; "
									+ " document.f1.socBandar_dis.value = '"
									+ k.get("nama_bandar").toString()
									+ "'; "
									+ " document.f1.socBandar[1].value = '"
									+ k.get("id_bandar").toString()
									+ "'; "
									+ " document.f1.socNegeri[1].value = '"
									+ k.get("id_negeri").toString()
									+ "'; "
									+ " document.f1.no_tel[1].value = '"
									+ k.get("no_tel").toString()
									+ "'; "
									+ " document.f1.txtAlamat3[1].value = '"
									+ removeSymbol(k.get("alamat3").toString())
									+ "'; "
									+ " document.f1.txtAlamat2[1].value = '"
									+ removeSymbol(k.get("alamat2").toString())
									+ "'; "
									+ " document.f1.txtAlamat1[1].value = '"
									+ removeSymbol(k.get("alamat1").toString())
									+ "'; "
									+" document.f1.txtNamaPemohon[0].disabled = 'disabled';"
									+ " document.f1.txtAlamat1[0].disabled = 'disabled';"
									+ " document.f1.txtAlamat2[0].disabled = 'disabled'; "
									+ " document.f1.txtAlamat3[0].disabled = 'disabled'; "
									+ " document.f1.txtPoskod[0].disabled = 'disabled'; "
									+ " document.f1.no_tel[0].disabled = 'disabled'; "
									+ " document.f1.socNegeri[0].disabled = 'disabled'; "
									+ " document.f1.socBandar[0].disabled = 'disabled'; "
									+

									"</script> ");
					// }

				} else {
					out.println("");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if ("getPejabat".equals(submit)) {
			PendaftaranCheckModel userdata = PendaftaranCheckModel.getInstance();
			Vector<Hashtable<String,String>> alamat_raya = new Vector<Hashtable<String,String>>();
			try {
				if (!jenis_pej.equals("")) {
					alamat_raya = userdata.getPejabat(jenis_pej);
					Hashtable<String,String> k = (Hashtable<String,String>) alamat_raya.get(0);
					out.println("" + "  <script type='text/javascript'> "
									//+ " alert('getPejabat'); "
									+ "document.f1.txtPoskod[1].value = '"
									+ k.get("poskod").toString()
									+ "';"
									+ " document.f1.txtNamaPemohon[1].value = '"
									+ k.get("nama_pejabat").toString()
									+ "';"
									+ " document.f1.socNegeri_dis.value = '"
									+ k.get("nama_negeri").toString()
									+ "'; "
									+ " document.f1.socBandar_dis.value = '"
									+ k.get("nama_bandar").toString()
									+ "'; "
									+ " document.f1.socBandar[1].value = '"
									+ k.get("id_bandar").toString()
									+ "'; "
									+ " document.f1.socNegeri[1].value = '"
									+ k.get("id_negeri").toString()
									+ "'; "
									+ " document.f1.no_tel[1].value = '"
									+ k.get("no_tel").toString()
									+ "'; "
									+ " document.f1.txtAlamat3[1].value = '"
									+ removeSymbol(k.get("alamat3").toString())
									+ "'; "
									+ " document.f1.txtAlamat2[1].value = '"
									+ removeSymbol(k.get("alamat2").toString())
									+ "'; "
									+ " document.f1.txtAlamat1[1].value = '"
									+ removeSymbol(k.get("alamat1").toString())
									+ "'; "
									+ " document.f1.txtEmelPemohon[1].value = '"
									+ k.get("emel").toString()
									+ "'; "
									+" document.f1.txtNamaPemohon[0].disabled = 'disabled';"
									+ " document.f1.txtAlamat1[0].disabled = 'disabled';"
									+ " document.f1.txtAlamat2[0].disabled = 'disabled'; "
									+ " document.f1.txtAlamat3[0].disabled = 'disabled'; "
									+ " document.f1.txtPoskod[0].disabled = 'disabled'; "
									+ " document.f1.no_tel[0].disabled = 'disabled'; "
									+ " document.f1.socNegeri[0].disabled = 'disabled'; "
									+ " document.f1.socBandar[0].disabled = 'disabled'; "
									+

									"</script> ");
					// }

				} else {
					out.println("");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	}

	public void doUserLogin(String username, String password,HttpSession session, PrintWriter out) throws Exception {
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
				out.println("<script>window.location='../online/c';</script>");
			
			} else {
				out.println("Hello " + username+ ", invalid login or password<br>");
				out.println("<script>");
				out.println("doEffect();");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void doSendEmail(String email) {
		String host = "ekptg.gov.my";
		String from = "admin@" + host;

		Properties props = System.getProperties();

		props.put("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("Password Reminder");
			message.setText("Hi,\nPlease confirm your password reminder here.\nregards - "+ from);
			Transport.send(message);
			
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
		
	}

	/*
	 * public static void main (String args[]) { String x = "testing ' xxx ";
	 * //System.out.println("xxx:"+removeSymbol(x)); }
	 */

	public String removeSymbol(String txt) {
		String x = txt.replaceAll("'", "");
		// System.out.println("testtt");
		// System.out.println(x);
		return x;
	}

	public void displaySuratBatalAlert(String id_permohonan, String a, PrintWriter out,
		String nama_simati, String nama_pejabat, String daerah_mohon,
		String no_fail, String nokp, String reportName, String securityToken) throws Exception {
		// ekptg.report.ppk.FrmPopupPilihPegawaiRepor
		//myLogger.info("no_fail = "+no_fail+"id_permohonan = "+id_permohona);
		String op = "";

		if(!no_fail.equals("")){
			System.out.println("no fail tidak kosong");
			op = "<div>"
				+ "<font color='red'>No. kad pengenalan simati sudah wujud pada permohonan yang bernombor fail </font>"
				+ no_fail.toUpperCase() +".<br/>"
				+ " Permohonan telah dibuat di "+nama_pejabat.toUpperCase()+". </div><br/> Sila lengkapkan permohonan ini untuk menjana Surat Batal Kerana Ada Permohonan Terdahulu." ;
				//+ a
			//	+ " sudah wujud!</div> <input name='no_kp3' type='hidden' value='yes' /> <script type='text/javascript'>  "
			//	+ " sudah wujud!</div> "
			//+ " </div><br/> " 
		    //+ "<input name='batal' type='button' value='Batal Permohonan' onClick='myFunctionBatal("+securityToken+",&#39;"+reportName+"&#39;,"+nokp+",&#39;"+no_fail+"&#39;)'/>&nbsp;"
			//    + "<input name='noFail' type='hidden' value='"
			//    + no_fail.toUpperCase()
			//    + "' />" 
			//    + "<script type='text/javascript'>  "
			//	+ "input_box = confirm('Permohonan untuk simati "
			//	+ nama_simati.toUpperCase()
			//	+ " yang bernombor fail "
			//	+ no_fail.toUpperCase()
			//	+ " sudah wujud! "
			//	+ "Permohonan telah dibuat di "
			//	+ nama_pejabat.toUpperCase()
			//	+ "("
			//	+ daerah_mohon.toUpperCase()
			//	+ ")"
			//	+ ". ";
				//+ " Adakah anda ingin mencetak surat pembatalan permohonan ?'); "
				//+ "if (input_box == true) {"
				//+
				// "alert('Maaf,Surat masih dalam proses pembikinan');"+
				// "var url = \"../servlet/ekptg.report.ppk.SuratBatalPermohonan?report="+reportName+"&noKP="+nokp+"\";"+
				//"var url = \"../x/"
				//+ securityToken
				//+ "/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?report="
				//+ reportName
				//+ "&noKP="
				//+ nokp
				//+ "&noFail="
				//+ no_fail
				//+ "\";"
				//+ "var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');"
				//+ "if ((document.window != null) && (!hWnd.opener))"
				//+ "hWnd.opener = document.window;"
				//+ "if (hWnd.focus != null) hWnd.focus();" + "}" + "";
		//op += "</script>";
		//String myurl = "../x/" + securityToken + "/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?report=" + reportName ;
		}
		
		String jenisFieldKP = ""; 
		if(reportName.equals("SuratBatalPermohonanKpBaru")){
			jenisFieldKP = "no_kp1"; 
		}else if(reportName.equals("SuratBatalPermohonanKpLama")){
			jenisFieldKP = "no_kp2"; 
		}else if(reportName.equals("SuratBatalPermohonanKpLain")){
			jenisFieldKP = "no_kp3"; 
		}
//		System.out.println("jenisFieldKP >>> "+jenisFieldKP);
		op += " <input id='"+jenisFieldKP+"' name='"+jenisFieldKP+"' type='hidden' value='yes' /> " +" ";
		out.print(op);
		
	}
	
	public void displaySuratBatalAlertOnline(String id_permohonan, String a, PrintWriter out,
		String nama_simati, String nama_pejabat, String daerah_mohon,
		String no_fail, String nokp, String reportName, String securityToken) throws Exception {
		// ekptg.report.ppk.FrmPopupPilihPegawaiRepor
//		myLogger.info("id_permohonan = "+id_permohonan);
		//myLogger.info("id_permohonan2 = "+id_permohonan2);
		String op;

		op = "<div>"+ "<font color='red'>MyID simati telah wujud!</font>";
				//+ "<input name=\"online_skrin\" type=\"hidden\" id=\"online_skrin\" value="$online_skrin"/>"
			//+ " </div> <script>alert('xxxxxxxxxxxxxxxxxxx');document.getElementById('nama_pelbagainegara').value = \"Y\";</script> " +
		String jenisFieldKP = ""; 
		if(reportName.equals("SuratBatalPermohonanKpBaru")){
			jenisFieldKP = "no_kp1"; 
		}else if(reportName.equals("SuratBatalPermohonanKpLama")){
			jenisFieldKP = "no_kp2"; 
		}else if(reportName.equals("SuratBatalPermohonanKpLain")){
			jenisFieldKP = "no_kp3"; 
		}
		
		op += " <input id='"+jenisFieldKP+"' name='"+jenisFieldKP+"' type='hidden' value='yes' /> " +" ";
		out.print(op);
	
	}

	public String myFunction(String test) throws Exception{
		String a = "myFunction(test)";
		//myLogger.info("Baca myFunction");
		return a;
		
	}
	
	public String getIdPemohon(String id_permohonan) throws Exception {
		String output = "";
		String sql = "";
		Db db = null;
		try {
			db = new Db();
			sql = "select id_pemohon from tblppkpermohonan where id_permohonan='"+ id_permohonan + "'";
//			myLogger.info(sql);
			ResultSet rs = db.getStatement().executeQuery(sql);
			if (rs.next()) {
				output = rs.getString("id_pemohon");
			}
		} catch (Exception e) {
			throw new Exception("error getting id_pemohon :" + e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
		return output;
	
	}

	public String getBandars(String idnegeri, String idbandar) throws Exception {
		StringBuffer sb = new StringBuffer("");
		String sql = "";
		Db db = null;
		sb.append("<option value=>SILA PILIH</option>");
		try {
			db = new Db();
			sql = "Select id_bandar,kod_bandar,keterangan from tblrujbandar";
			sql += " where id_negeri= '" + idnegeri + "'";
			sql += " ORDER BY lpad(id_Bandar,10)";
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String rsidbandar = "";
			while (rs.next()) {
				rsidbandar = rs.getString("id_bandar");
				if (rsidbandar.equals(idbandar)) {
					sb.append("<option selected value=" + rsidbandar + ">");
				} else {
					sb.append("<option value=" + rsidbandar + ">");
				}
				sb.append(rs.getString(2) + " - " + rs.getString(3));
				sb.append("</option>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return sb.toString();
	
	}

	public String getSaudaraLists(String idjantina, String idsaudara)
		throws Exception {
		StringBuffer sb = new StringBuffer("");
		String sql = "";
		Db db = null;
		sb.append("<option value=>SILA PILIH TALIAN PERSAUDARAAN</option>");
		try {
			db = new Db();
			sql = "Select id_saudara, kod, keterangan, jantina FROM Tblppkrujsaudara";
			sql += " where id_saudara NOT IN (24,25,27,29,30,34,35) "
					+ " AND jantina = " + idjantina;
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String rsidsaudara = "";
			while (rs.next()) {
				rsidsaudara = rs.getString("id_saudara");
				if (rsidsaudara.equals(idsaudara)) {
					sb.append("<option selected value=" + rsidsaudara + ">");
				} else {
					sb.append("<option value=" + rsidsaudara + ">");
				}
				sb.append(rs.getString(2) + " - " + rs.getString(3));
				sb.append("</option>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return sb.toString();
	
	}

	public boolean checkKPSimati(String id_simati, String kpbaru,String kplama, String kplain) throws Exception {
//		myLogger.info("NO KP LAMA SIMATI :" + kplama);
		Db db = null;
		boolean a = false;
//		String jumlah_baru = "0";
//		String jumlah_lama = "0";
//		String jumlah_lain = "0";
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		try {
			db = new Db();

			if (kpbaru != "") {
				Statement stmt = db.getStatement();
				//SQLRenderer r = new SQLRenderer();
				sql = " SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_BARU "
						+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
						+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
						+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
						// +" AND P.ID_PERMOHONAN <> '"+idp+"'"
						+ " AND SM.ID_SIMATI <> '" + id_simati + "'"
						+ " AND SM.NO_KP_BARU = '" + kpbaru + "'"
						+ " AND P.ID_STATUS <> '999'"
						+ " AND P.ID_STATUS <> '169'";

				myLogger.info("CHECK SQL 1:" + sql.toUpperCase());

				ResultSet rs = stmt.executeQuery(sql);
//				Hashtable h;
				/*
				 * while (rs.next()) { jumlah_baru =
				 * rs.getString("JUMLAH_BARU")==
				 * null?"":rs.getString("JUMLAH_BARU"); }
				 */
				if (rs.next()) {
					if (rs.getInt("JUMLAH_BARU") > 0) {
						a = true;
					}
				}
			}

			if (kplama != "" && !kplama.equals("TDK")) {
				Statement stmt1 = db.getStatement();
//				SQLRenderer r1 = new SQLRenderer();
				sql1 = " SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_LAMA "
						+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
						+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
						+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
						// +" AND P.ID_PERMOHONAN <> '"+idp+"'"
						+ " AND SM.ID_SIMATI <> '" + id_simati + "'"
						+ " AND SM.NO_KP_LAMA = '" + kplama + "'"
						+ " AND P.ID_STATUS <> '999'"
						+ " AND P.ID_STATUS <> '169'";

				myLogger.info("CHECK SQL 2:" + sql1.toUpperCase());

				ResultSet rs1 = stmt1.executeQuery(sql1);
//				Hashtable h1;
				/*
				 * while (rs1.next()) { jumlah_lama =
				 * rs1.getString("JUMLAH_LAMA"
				 * )==null?"":rs1.getString("JUMLAH_LAMA"); }
				 */
				if (rs1.next()) {
					if (rs1.getInt("JUMLAH_LAMA") > 0) {
						a = true;
					}
				}

			}

			if (kplain != "") {
				Statement stmt2 = db.getStatement();
//				SQLRenderer r2 = new SQLRenderer();
				sql2 = " SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_LAIN "
						+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
						+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
						+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
						// +" AND P.ID_PERMOHONAN <> '"+idp+"'"
						+ " AND SM.ID_SIMATI <> '" + id_simati + "'"
						+ " AND SM.NO_KP_LAIN = '" + kplain + "'"
						+ " AND P.ID_STATUS <> '999'"
						+ " AND P.ID_STATUS <> '169'";
//				myLogger.info("CHECK SQL 3:" + sql2.toUpperCase());

				ResultSet rs2 = stmt2.executeQuery(sql2);
//				Hashtable h2;

				if (rs2.next()) {
					if (rs2.getInt("JUMLAH_LAIN") > 0) {
						a = true;
					}
				}
			}

		} finally {
			if (db != null)
				db.close();

		}
		// return false;
		return a;
	}
	

}
