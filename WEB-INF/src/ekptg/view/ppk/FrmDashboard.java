/**
 * 
 */
package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.BicaraInteraktifData;
import ekptg.view.admin.Pengumuman;
import ekptg.view.online.FrmAduanPublic;

@SuppressWarnings("serial")
public class FrmDashboard extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmDashboard.class);
	private final String PATH="app/ppk/";//IL
	String userId = null;
	String role = null;
	String user_negeri_login = null;
	Pengumuman logic = new Pengumuman();
	String vm = "app/ppk/dashboard.jsp";
	
	List listFail = null;
	List listPermohonanTukarPegawai = null;
	List listPermohonanBicaraOnline = null;
	List listHelpDesk = null;
	List listFailBelumKemaskiniSek8 = null;
	List listFailTamatTempohKpi = null;
	List listFailTamatTempohKpiSek17 = null;
	
	BicaraInteraktif viewBI = new BicaraInteraktif();
	FrmAduanPublic viewAP = new FrmAduanPublic();
	BicaraInteraktifData modelBI = new BicaraInteraktifData();
	
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		userId = (String) session.getAttribute("_ekptg_user_id");
		String portal_role = (String) session.getAttribute("myrole");
		role = portal_role;

		
		user_negeri_login = (String) session.getAttribute("_ekptg_user_negeri");
		//System.out.println("user_negeri_login===="+user_negeri_login);
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		context.put("portal_role", portal_role);
		String command = getParam("command");
		
		Map getDetailUsers = modelBI.getDetailUsers(session, "", userId, "", null);
		String id_jawatan_login = "";
		String id_negeri_login = "";
		if(getDetailUsers!=null)
		{
			id_jawatan_login = (String)getDetailUsers.get("ID_JAWATAN");
			id_negeri_login = (String)getDetailUsers.get("ID_NEGERI");	
			context.put("id_jawatan_login", id_jawatan_login);
			context.put("id_negeri_login", id_negeri_login);
		}
		
		Hashtable get_stat = null;//aishahlatip edit

		//Carian 
		//remark do jap
		if (command.equals("doCarianFail")) {
			return doCarianFail(session);
		}else if (command.equals("doCloseCarianFail")) {
			return doCloseCarianFail(session);
		}else if (command.equals("doGetListFail")) {
			return doGetListFail(session);
		} else if (command.equals("doCloseListFail")) {
			return doCloseListFail(session);
		}else if (command.equals("doGetListFailBelumKemaskiniSek8")) {
			return doGetListFailBelumKemaskiniSek8(session);
		} else if (command.equals("doCloseListFailBelumKemaskiniSek8")) {
			return doCloseListFailBelumKemaskiniSek8(session);
		}else if (command.equals("doGetListFailTamatTempohKpi")) {
			return doGetListFailTamatTempohKpi(session);
		} else if (command.equals("doCloseListFailTamatTempohKpi")) {
			return doCloseListFailTamatTempohKpi(session);
		}else if (command.equals("doGetListFailTamatTempohKpiSek17")) {
			return doGetListFailTamatTempohKpiSek17(session);
		} else if (command.equals("doCloseListFailTamatTempohKpiSek17")) {
			return doCloseListFailTamatTempohKpiSek17(session);
		} else if (command.equals("getTotalMT")) {
			return getTotalMT();
		} else if (command.equals("getCountFailTempohKpi")) {
			return getCountFailTempohKpi();
		} else if (command.equals("getCountFailTempohKpiSek17")) {
			return getNotifikasiFailTempohKpiSek17();
		} else if (command.equals("getCountFailKemaskini")) {
			return getCountFailKemaskini();
		} else if (command.equals("getCheckNotifikasiOnline8")) {
			return getCheckNotifikasiOnline8();
		} else if (command.equals("getCheckNotifikasiOnline17")) {
			return getCheckNotifikasiOnline17();
		} else if (command.equals("getCheckNotifikasiEdit")) {
			return getCheckNotifikasiEdit();
		} else if (command.equals("getCheckNotifikasiPindah")) {
			return getCheckNotifikasiPindah();
		} else if (command.equals("getCheckNotifikasiAduan")) {
			return getCheckNotifikasiAduan();
		} else if (command.equals("getCheckNotifikasiInbox")) {
			return getCheckNotifikasiInbox();
		} else if (command.equals("getListCountBorangB")) {
			return getListCountBorangB();
		} else if (command.equals("getJumlahKeseluruhan")) {
			return getJumlahKeseluruhan();
		} else if (command.equals("getFailSek8")) {
			return getFailSek8();
		} else if (command.equals("getFailSek17")) {
			return getFailSek17();
		} else if (command.equals("getTabDashboard")) {
			
			get_stat = stat_fail(user_negeri_login);
			String fail_selesai = (String) get_stat.get("JUMLAH_SELESAI");
			String fail_xselesai = (String) get_stat.get("JUMLAH_XSELESAI");
			return getTabDashboard(role, user_negeri_login, "", "", userId,"NO", fail_selesai, fail_xselesai);
		}
		
		/*Integer count_fail_kemaskini = 0;
		Integer count_fail_tempoh_kpi = 0;
		Integer count_fail_tempoh_kpi_sek17 = 0;*/
		//Integer check_notifikasi_aduan = 0;
		//Integer check_notifikasi_online8 = 0;
		Integer check_carianFailBelumKemaskiniSek8 = 0;
		//Integer check_notifikasi_online17 = 0;
		//Integer check_notifikasi_pindah = 0;
		Integer check_notifikasi_inbox = 0;
		Integer getListCountBorangB = 0;
		Integer getListKiv = 0;
		Integer getListCountBorangKIV = 0;
		Integer getListCountBorangNotis = 0;
		Integer getMahkamahTinggi = 0;
		Vector list_memo_aktif = null;
		Vector ListBorangB = null;
		Hashtable notifikasi = null;
		Hashtable notifikasi_borangB = null;
//IL
		Hashtable notifikasi_failKemaskini = null;
		Hashtable notifikasi_failTempohKpi = null;


		/*getMahkamahTinggi = FrmMTBorangC.MTKeputusanCount(userId);
		context.put("TotalMT", getMahkamahTinggi);*/

//		get_stat = stat_fail(role, userId);
		context.put("bil_rekod", "0");
		//String negeri_sever = (String) getNegeriServer(userId); //IL tp aishahlatip tutup
		//String jumlah_keseluruhan = "0"; //(String) get_stat.get("JUMLAH_KESELURUHAN");
		//String fail_sek8 = "0"; //(String) get_stat.get("JUMLAH_KESELURUHAN_SEK8");
		//String fail_sek17 = "0"; //(String) get_stat.get("JUMLAH_KESELURUHAN_SEK17");
	//	String fail_hapus = "0"; //(String) get_stat.get("JUMLAH_FAIL_HAPUS");//IL tp aishahlatip tutup
		//String fail_selesai = "0"; //(String) get_stat.get("JUMLAH_SELESAI");
		//String fail_xselesai = "0"; //(String) get_stat.get("JUMLAH_XSELESAI");		

		myLogger.info("command dashboard ppk :"+command);
		
		
		if(command.equals("showCountMT"))
		{
			getMahkamahTinggi = FrmMTBorangC.MTKeputusanCount(userId);
			context.put("TotalMT", getMahkamahTinggi);
			vm=  "app/ppk/dashboard_showCountMT.jsp";
		}
		else if(command.equals("showCountTukarPegawai"))
		{
			
			listPermohonanTukarPegawai = viewBI.listPermohonanTukarPegawai(session,userId,id_jawatan_login,id_negeri_login,"","Y","","",null);
			context.put("TotalTukarPegawai", listPermohonanTukarPegawai.size());
			vm=  "app/ppk/dashboard_showCountTukarPegawai.jsp";
		}
		else if(command.equals("showCountBicaraOnline"))
		{
			
			listPermohonanBicaraOnline = viewBI.listPerbicaraan(session,userId,id_jawatan_login,id_negeri_login,"","Y",null);;
			context.put("TotalBicaraOnline", listPermohonanBicaraOnline.size());
			vm=  "app/ppk/dashboard_showCountBicaraOnline.jsp";
		}
		else if(command.equals("showCountHelpDesk"))
		{			
			listHelpDesk = viewAP.listNotifikasi(session,userId,"", null);
			int totalHelpDesk = 0;
			for(int i = 0; i < listHelpDesk.size();i++)
			{
				Map m = (Map) listHelpDesk.get(i);
				int CNT = (Integer) m.get("CNT");
				totalHelpDesk = totalHelpDesk + CNT;				
			}
			
			
			context.put("totalHelpDesk", totalHelpDesk);
			vm=  "app/ppk/dashboard_showCountHelpDesk.jsp";
		}
		else if(command.equals("show_mainstats"))
		{
			get_stat = stat_fail(user_negeri_login);
			String negeri_sever = (String) get_stat.get("NAMA_NEGERI_SERVER");
			String jumlah_keseluruhan = (String) get_stat.get("JUMLAH_KESELURUHAN");
			String fail_sek8 = (String) get_stat.get("JUMLAH_KESELURUHAN_SEK8");
			String fail_sek17 = (String) get_stat.get("JUMLAH_KESELURUHAN_SEK17");
			String fail_hapus = (String) get_stat.get("JUMLAH_FAIL_HAPUS");
			String fail_selesai = (String) get_stat.get("JUMLAH_SELESAI");
			String fail_xselesai = (String) get_stat.get("JUMLAH_XSELESAI");
			context.put("negeri_sever", negeri_sever);
			context.put("jumlah_keseluruhan", jumlah_keseluruhan);
			context.put("fail_sek8", fail_sek8);
			context.put("fail_sek17", fail_sek17);
			context.put("fail_hapus", fail_hapus);
			context.put("fail_selesai", fail_selesai);
			context.put("fail_xselesai", fail_xselesai);	
			vm=  "app/ppk/dashboard_mainstats.jsp";
			
		}
		else if (command.equals("getTabDashboard")) {
			
			//old code
			/*context.put("defaultTab", "0");
			vm= getTabDashboard(role, user_negeri_login, "", "", userId,
					"NO");*/
			
			//IL
			get_stat = stat_fail(role);
			String fail_selesai = (String) get_stat.get("JUMLAH_SELESAI");
			String fail_xselesai = (String) get_stat.get("JUMLAH_XSELESAI");
			return getTabDashboard(role, user_negeri_login, "", "", userId,"NO", fail_selesai, fail_xselesai);
			
			
		}
		else if(command.equals("showBorangB_stats"))
		{
			vm=getShowBorangB(role, user_negeri_login, "", "", userId,
					"NO");
			
		}		
		else if(command.equals("showMAIN_stats"))
		{
			get_stat = stat_fail(user_negeri_login);
			String negeri_sever = (String) get_stat.get("NAMA_NEGERI_SERVER");
			String jumlah_keseluruhan = (String) get_stat.get("JUMLAH_KESELURUHAN");
			String fail_sek8 = (String) get_stat.get("JUMLAH_KESELURUHAN_SEK8");
			String fail_sek17 = (String) get_stat.get("JUMLAH_KESELURUHAN_SEK17");
			String fail_hapus = (String) get_stat.get("JUMLAH_FAIL_HAPUS");
			String fail_selesai = (String) get_stat.get("JUMLAH_SELESAI");
			String fail_xselesai = (String) get_stat.get("JUMLAH_XSELESAI");
			context.put("negeri_sever", negeri_sever);
			context.put("jumlah_keseluruhan", jumlah_keseluruhan);
			context.put("fail_sek8", fail_sek8);
			context.put("fail_sek17", fail_sek17);
			context.put("fail_hapus", fail_hapus);
			context.put("fail_selesai", fail_selesai);
			context.put("fail_xselesai", fail_xselesai);	
			vm=  "app/ppk/dashboard_carta_main.jsp";
			
		}
		else if(command.equals("showKIV_stats"))
		{
			vm=getShowKIV(role, user_negeri_login, "", "", userId,
					"NO");
			
		}
		
		
		else if(command.equals("showCountFailLengkap"))
		{
		context.put("check_notifikasi_failLengkap", notifikasi_FailLengkapUntukBicara(role, user_negeri_login, "", "", userId, "NO"));
		vm=  "app/ppk/showNotifikasi_FailLengkap.jsp";
		}
		else if(command.equals("showNotifikasi_OT"))
		{
		context.put("check_notifikasi_ot", notifikasi_OT(userId));
		vm=  "app/ppk/showNotifikasi_OT.jsp";
		}
		else if(command.equals("showNotifikasi_BU"))
		{
		context.put("check_notifikasi_bu", notifikasi_BU(portal_role,USER_UNIT,USER_NEGERI));
		vm=  "app/ppk/showNotifikasi_BU.jsp";
		}
		
		else if(command.equals("showNotifikasi_PengesahanNilaian"))
		{
			myLogger.info("USER_UNIT :"+USER_UNIT);	
			myLogger.info("userId :"+userId);	
			myLogger.info("USER_NEGERI :"+USER_NEGERI);
		context.put("check_notifikasi_PengesahanNilaian", notifikasi_PengesahanNilaian(portal_role,userId,USER_NEGERI));
		vm=  "app/ppk/showNotifikasi_PengesahanNilaian.jsp";
		}
		
		else if(command.equals("showNotifikasi_Aduan"))
		{
		context.put("check_notifikasi_aduan", notifikasi_Aduan(role, user_negeri_login, "", "", userId, "NO"));
		vm=  "app/ppk/showNotifikasi_Aduan.jsp";
		}
		else if(command.equals("showNotifikasi_OnlineS8"))
		{
		context.put("check_notifikasi_online8", notifikasi_OnlineS8(role, user_negeri_login, "", "", userId, "NO"));
		vm=  "app/ppk/showNotifikasi_OnlineS8.jsp";
		}
		else if(command.equals("showNotifikasi_OnlineS17"))
		{
		context.put("check_notifikasi_online17", notifikasi_OnlineS17(role, user_negeri_login, "", "", userId, "NO"));
		vm=  "app/ppk/showNotifikasi_OnlineS17.jsp";
		}
		else if(command.equals("showNotifikasi_Pindah"))
		{
		context.put("check_notifikasi_pindah", notifikasi_Pindah(role, user_negeri_login, "", "", userId, "NO"));
		vm=  "app/ppk/showNotifikasi_Pindah.jsp";
		}
		else if(command.equals("showNotifikasi_Inbox"))
		{
		context.put("check_notifikasi_inbox", notifikasi_Inbox(role, user_negeri_login, "", "", userId, "NO"));
		vm=  "app/ppk/showNotifikasi_Inbox.jsp";
		}
		else if(command.equals("showNotifikasi_Edit"))
		{
		context.put("check_notifikasi_edit", notifikasi_Edit(role, user_negeri_login, "", "", userId, "NO"));
		vm=  "app/ppk/showNotifikasi_Edit.jsp";
		}else if(command.equals("showCountNotisPerbicaraan"))
		{
		context.put("check_notifikasi_notisPerbicaraan", notifikasi_FailNotisPerbicaraan(role, user_negeri_login, "", "", userId, "NO"));
		vm=  "app/ppk/showNotifikasi_NotisPerbicaraan.jsp";
		}
		else if (command.equals("doCarianFail")) {
			return doCarianFail(session);
		}else if (command.equals("doCloseCarianFail")) {
			return doCloseCarianFail(session);
		}else if (command.equals("doGetListFail")) {
			return doGetListFail(session);
		} else if (command.equals("doCloseListFail")) {
			return doCloseListFail(session);
		}
		
		//else
		//{
		/*
		notifikasi = notifikasi(role, user_negeri_login, "", "", userId, "NO");
		context.put("check_notifikasi_aduan", notifikasi.get("JUMLAH_ADUAN"));
		context.put("check_notifikasi_online8", notifikasi.get("ONLINE_SEK8"));
		context.put("check_notifikasi_online17", notifikasi.get("ONLINE_SEK17"));
		context.put("getListCountBorangB", notifikasi.get("TOTAL_BORANGB"));
		context.put("check_notifikasi_pindah", notifikasi.get("FAIL_PINDAH"));
		context.put("check_notifikasi_inbox", notifikasi.get("INBOX"));
		context.put("check_notifikasi_edit", notifikasi.get("KEBENARAN_EDIT"));
		*/		
		//}

		

		// check_notifikasi_aduan =
		// getListNotifikasi_main_list(role,user_negeri_login,"","",userId,"NO");
		// context.put("check_notifikasi_aduan",check_notifikasi_aduan);

		// check_notifikasi_online8 = getListNotifikasi_online8(userId,"8");
		// context.put("check_notifikasi_online8",check_notifikasi_online8);

		// check_notifikasi_online17 = getListNotifikasi_online8(userId,"17");
		// context.put("check_notifikasi_online17",check_notifikasi_online17);

		// getListCountBorangB = getListCountBorangB(userId);
		// context.put("getListCountBorangB",getListCountBorangB);

		// check_notifikasi_pindah = getListNotifikasi_pindah(userId);
		// context.put("check_notifikasi_pindah",check_notifikasi_pindah);

		// check_notifikasi_inbox = getListNotifikasi_inbox(userId);
		// context.put("check_notifikasi_inbox",check_notifikasi_inbox);

		// ListBorangB = getListBorangB(userId);
		// context.put("ListBorangB",ListBorangB);

		//IL
		//context.put("negeri_sever", negeri_sever);//aishahlatip close
		/*context.put("jumlah_keseluruhan", jumlah_keseluruhan);
		context.put("fail_sek8", fail_sek8);
		context.put("fail_sek17", fail_sek17);*/
		//context.put("fail_hapus", fail_hapus);//aishahlatip close
		//context.put("fail_selesai", fail_selesai);
		//context.put("fail_xselesai", fail_xselesai);

		context.put("defaultTab", "0");		

		list_memo_aktif = new Vector(); // logic.getMemo("", "Aktif", "1", "0");
		context.put("list_memo_aktif", list_memo_aktif);

		//end IL

		/*
		list_memo_aktif = logic.getMemo("", "Aktif", "1", "0");
		context.put("list_memo_aktif", list_memo_aktif);
		*/

		/*
		 * if(command.equals("changeTab")){
		 * myLogger.info("INDEX :"+getParam("tab_index"));
		 * context.put("tab_index",getParam("tab_index")); } else {
		 * context.put("tab_index",0); }
		 */

		

		// String vm = "RGraph/examples/horizontal-bar-charts.html";
		myLogger.info("V dashboard ppk :"+vm);
		return vm;
	}
	
	/*
	private String getMemo()
			throws Exception {
		Vector list_memo_aktif = null;
		list_memo_aktif = logic.getMemo("", "Aktif", "1", "0");
		context.put("list_memo_aktif", list_memo_aktif);
		return "app/ppk/dashboard_memo.jsp";
	}
	*/
//IL

private String getTotalMT() throws Exception {
		this.context.put("bil_rekod", FrmMTBorangC.MTKeputusanCount(userId));
		return "app/ppk/dashboard/div_bil.jsp";
	}
	private String getCountFailTempohKpi() throws Exception {
		this.context.put("bil_rekod", notifikasi_failTempohKpi(role, user_negeri_login, "", "", userId, "NO"));
		return "app/ppk/dashboard/div_bil.jsp";
	}
	private String getNotifikasiFailTempohKpiSek17() throws Exception {
		this.context.put("bil_rekod", notifikasi_failTempohKpiSek17(role, user_negeri_login, "", "", userId, "NO"));
		return "app/ppk/dashboard/div_bil.jsp";
	}
	private String getCountFailKemaskini() throws Exception {
		this.context.put("bil_rekod", notifikasi_failKemaskini(role, user_negeri_login, "", "", userId, "NO"));
		return "app/ppk/dashboard/div_bil.jsp";
	}
	private String getCheckNotifikasiInbox() throws Exception {
		this.context.put("bil_rekod", getCheckNotifikasiInbox(userId));
		return "app/ppk/dashboard/div_bil.jsp";
	}
	private String getListCountBorangB() throws Exception {
		this.context.put("bil_rekod", getListCountBorangB(userId));
		return "app/ppk/dashboard/div_bil.jsp";
	}
	private String getCheckNotifikasiOnline8() throws Exception {
		this.context.put("bil_rekod", getCheckNotifikasiOnline8(userId));
		return "app/ppk/dashboard/div_bil.jsp";
	}
	private String getCheckNotifikasiOnline17() throws Exception {
		this.context.put("bil_rekod", getCheckNotifikasiOnline17(userId));
		return "app/ppk/dashboard/div_bil.jsp";
	}
	private String getCheckNotifikasiEdit() throws Exception {
		this.context.put("bil_rekod", getCheckNotifikasiEdit(userId));
		return "app/ppk/dashboard/div_bil.jsp";
	}
	private String getCheckNotifikasiPindah() throws Exception {
		this.context.put("bil_rekod", getCheckNotifikasiPindah(userId));
		return "app/ppk/dashboard/div_bil.jsp";
	}
	private String getCheckNotifikasiAduan() throws Exception {
		this.context.put("bil_rekod", getCheckNotifikasiAduan(role, user_negeri_login, userId));
		return "app/ppk/dashboard/div_bil.jsp";
	}
	private String getJumlahKeseluruhan() throws Exception {
		this.context.put("bil_rekod", getJumlahKeseluruhan(role, userId));
		return "app/ppk/dashboard/div_bil.jsp";
	}
	private String getFailSek8() throws Exception {
		this.context.put("bil_rekod", getFailSek8(role, userId));
		return "app/ppk/dashboard/div_bil.jsp";
	}
	private String getFailSek17() throws Exception {
		this.context.put("bil_rekod", getFailSek17(role, userId));
		return "app/ppk/dashboard/div_bil.jsp";
	}
	//String count = "0"; //notifikasi_failKemaskini(role, user_negeri_login, "", "", userId, "NO");
	//String jumlah_keseluruhan = "0"; //(String) get_stat.get("JUMLAH_KESELURUHAN");
	//String fail_sek8 = "0"; //(String) get_stat.get("JUMLAH_KESELURUHAN_SEK8");
	//String fail_sek17 = "0"; //(String) get_stat.get("JUMLAH_KESELURUHAN_SEK17");
	

	private String getTabDashboard(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread, String fail_selesai, String fail_xselesai)
			throws Exception {
		// TODO Auto-generated method stub
		
		Hashtable notifikasi_borangB = null;
		notifikasi_borangB = notifikasi_borangB(role, id_negeri_user, "", "",
				user_terima, notread);
		context.put("getListCountBorangB",
				notifikasi_borangB.get("TOTAL_BORANGB"));
		Hashtable notifikasi_KIV = null;
		notifikasi_KIV = notifikasi_KIV(role, id_negeri_user, "", "",
				user_terima, notread);
		context.put("getListKiv", notifikasi_KIV.get("TOTAL_KIV"));
		
		
		
		Vector list_memo_aktif = null;
		list_memo_aktif = logic.getMemo("", "Aktif", "1", "0");
		context.put("list_memo_aktif", list_memo_aktif);

		context.put("fail_selesai", fail_selesai);
		context.put("fail_xselesai", fail_xselesai);

		return "app/ppk/dashboard_tab.jsp";
	}
	
	//ADD by aishahlatip 27/04/2017
	//Carian Kes Luar
	
	private String doCarianFail(HttpSession session) throws Exception {
		myLogger.info("doCarianFail : "+getParam("search"));
		this.context.put("div_carianFail_open", "Y");
		listFail = ListFail(session, "fail", getParam("search"));
		this.context.put("listFail", listFail);

		return PATH+"/div_carianFail.jsp";
	}
	
	private String doCloseCarianFail(HttpSession session) throws Exception {
		this.context.put("div_carianFail_open", "N");
		return PATH+"/div_carianFail.jsp";
	}
	
	private String doGetListFail(HttpSession session) throws Exception {
		if (!getParam("div_getListFail_open").equals("Y")) {
			this.context.put("div_getListFail_open", "Y");
			listFail = ListFail(session, "fail", getParam("search"));
			this.context.put("listFail", listFail);
			this.context.put("search", getParam("search"));
		} else {
			this.context.put("div_getListFail_open", "N");
		}
		return PATH+"/div_listFail.jsp";
	}
	
	private String doCloseListFail(HttpSession session) throws Exception {
		this.context.put("div_getListFail_open", "N");
		return PATH+"/div_listFail.jsp";
	}
	//end add
	
//IL
	
	private String doGetListFailBelumKemaskiniSek8(HttpSession session) throws Exception {
		if (!getParam("div_getListFailBelumKemaskiniSek8_open").equals("Y")) {
			this.context.put("div_getListFailBelumKemaskiniSek8_open", "Y");
			listFailBelumKemaskiniSek8 = ListFailBelumKemaskiniSek8(session, "fail", getParam("search"));
			this.context.put("listFailBelumKemaskiniSek8", listFailBelumKemaskiniSek8);
			this.context.put("search", getParam("search"));
		} else {
			this.context.put("div_getListFailBelumKemaskiniSek8_open", "N");
		}
		return PATH+"/div_listFailBelumKemaskiniSek8.jsp";
	}
	
	private String doCloseListFailBelumKemaskiniSek8(HttpSession session) throws Exception {
		this.context.put("div_getListFailBelumKemaskiniSek8_open", "N");
		return PATH+"/div_listFailBelumKemaskiniSek8.jsp";
	}
	
	
	private String doGetListFailTamatTempohKpi(HttpSession session) throws Exception {
		if (!getParam("div_getListFailTamatTempohKpi_open").equals("Y")) {
			this.context.put("div_getListFailTamatTempohKpi_open", "Y");
			listFailTamatTempohKpi = ListFailTamatTempohKpi(session, "fail", getParam("search"));
			this.context.put("listFailTamatTempohKpi", listFailTamatTempohKpi);
			this.context.put("search", getParam("search"));
		} else {
			this.context.put("div_getListFailTamatTempohKpi_open", "N");
		}
		return PATH+"/div_listFailTamatTempohKpi.jsp";
	}

	private String doCloseListFailTamatTempohKpi(HttpSession session) throws Exception {
		this.context.put("div_getListFailTamatTempohKpi_open", "N");
		return PATH+"/div_listFailTamatTempohKpi.jsp";
	}	


	private String doGetListFailTamatTempohKpiSek17(HttpSession session) throws Exception {
		if (!getParam("div_getListFailTamatTempohKpiSek17_open").equals("Y")) {
			this.context.put("div_getListFailTamatTempohKpiSek17_open", "Y");
			listFailTamatTempohKpiSek17 = ListFailTamatTempohKpiSek17(session, "fail", getParam("search"));
			this.context.put("listFailTamatTempohKpiSek17", listFailTamatTempohKpiSek17);
			this.context.put("search", getParam("search"));
		} else {
			this.context.put("div_getListFailTamatTempohKpiSek17_open", "N");
		}
		return PATH+"/div_listFailTamatTempohKpiSek17.jsp";
	}
	
	private String doCloseListFailTamatTempohKpiSek17(HttpSession session) throws Exception {
		this.context.put("div_getListFailTamatTempohKpiSek17_open", "N");
		return PATH+"/div_listFailTamatTempohKpiSek17.jsp";
	}

	private List ListFail(HttpSession session, String type, String search)
			throws Exception {
		listFail = DBcarianFail(session, type, search);
		this.context.put("listFail", listFail);
		return listFail;
	}
	
	private List ListFailBelumKemaskiniSek8(HttpSession session, String type, String search)
			throws Exception {
		listFailBelumKemaskiniSek8 = DBcarianFailBelumKemaskiniSek8(session, type, search);
		this.context.put("listFailBelumKemaskiniSek8", listFailBelumKemaskiniSek8);
		return listFailBelumKemaskiniSek8;
	}
	
	private List ListFailTamatTempohKpi(HttpSession session, String type, String search)
			throws Exception {
		listFailTamatTempohKpi = DBcarianFailTamatTempohKpi(session, type, search);
		this.context.put("listFailTamatTempohKpi", listFailTamatTempohKpi);
	return listFailTamatTempohKpi;
	}

	private List ListFailTamatTempohKpiSek17(HttpSession session, String type, String search)
			throws Exception {
		listFailTamatTempohKpiSek17 = DBcarianFailTamatTempohKpiSek17(session, type, search);
		this.context.put("listFailTamatTempohKpiSek17", listFailTamatTempohKpiSek17);
		return listFailTamatTempohKpiSek17;
	}
//end IL

	private String getShowKIV(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread)
			throws Exception {
		Hashtable notifikasi_KIV = null;
		notifikasi_KIV = notifikasi_KIV(role, id_negeri_user, "", "",
				user_terima, notread);
		context.put("getListKiv", notifikasi_KIV.get("TOTAL_KIV"));
	return "app/ppk/dashboard_showKIV_stats.jsp";
	}

	private String getShowBorangB(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread)
			throws Exception {
	Hashtable notifikasi_borangB = null;
	notifikasi_borangB = notifikasi_borangB(role, id_negeri_user, "", "",
			user_terima, notread);
	context.put("getListCountBorangB",
			notifikasi_borangB.get("TOTAL_BORANGB"));
	return "app/ppk/dashboard_showBorangB_stats.jsp";
	}
	//user_negeri_login
	
	public Hashtable<String,String> stat_fail(String negeriUser) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable<String,String> get_negeri = kod_negeri();
		String kod_negeri = (String) get_negeri.get("KOD_NEGERI");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "";			
			//myLogger.info(" STATISTIK :" + sql.toUpperCase());
			
			sql = " SELECT INITCAP(N.NAMA_NEGERI) AS NAMA_NEGERI_SERVER, "+
				" MAIN.JUMLAH_KESELURUHAN, MAIN.JUMLAH_KESELURUHAN_SEK8, MAIN.JUMLAH_KESELURUHAN_SEK17, MAIN.JUMLAH_SELESAI, " +
				" MAIN.JUMLAH_XSELESAI, MAIN.JUMLAH_FAIL_HAPUS "+
				" FROM " +
				//" TBLLOOKUPSTATE S, " +
				" TBLRUJNEGERI N, "+
				" (SELECT "+
				" 	TO_CHAR (COUNT ((CASE WHEN P.ID_STATUS <> '999' THEN 1 END)), '999,999,999') AS JUMLAH_KESELURUHAN, "+
				" 	TO_CHAR (COUNT ((CASE WHEN P.SEKSYEN = 8 AND P.ID_STATUS <> '999' THEN 1 END)), '999,999,999') AS JUMLAH_KESELURUHAN_SEK8, "+
				" 	TO_CHAR (COUNT ((CASE WHEN P.SEKSYEN = 17 AND P.ID_STATUS <> '999' THEN 1 END)), '999,999,999') AS JUMLAH_KESELURUHAN_SEK17, "+
				" 	TO_CHAR (COUNT ((CASE WHEN (P.SEKSYEN = 8 OR P.SEKSYEN = 17) AND P.ID_STATUS = '21' THEN 1 END)), '999,999,999') AS JUMLAH_SELESAI, "+
				" 	TO_CHAR (COUNT ((CASE WHEN (P.SEKSYEN = 8 OR P.SEKSYEN = 17) AND P.ID_STATUS NOT IN (21,999) THEN 1 END)), '999,999,999') AS JUMLAH_XSELESAI, "+
				" 	TO_CHAR (COUNT ((CASE WHEN P.ID_STATUS = '999' THEN 1 END)), '999,999,999') AS JUMLAH_FAIL_HAPUS "+
                " ,P.ID_NEGERIMHN "+
				" FROM TBLPPKPERMOHONAN P, "+
				" TBLPFDFAIL F "+
				" WHERE P.ID_FAIL = F.ID_FAIL ";
			
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				sql += " AND P.ID_NEGERIMHN = '" + negeriUser + "'";
			}
			sql += " GROUP BY P.ID_NEGERIMHN) MAIN "+
				" WHERE " +
				" N.ID_NEGERI = MAIN.ID_NEGERIMHN  " +
				" AND n.id_negeri = '" + negeriUser + "' ";
				
			myLogger.info(" STATISTIK *** :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
			h = new Hashtable<String,String>();
			while (rs.next()) {
				if (rs.getString("NAMA_NEGERI_SERVER") == null) {
					h.put("NAMA_NEGERI_SERVER", "");
				} else {
					h.put("NAMA_NEGERI_SERVER",
							rs.getString("NAMA_NEGERI_SERVER"));
				}
				if (rs.getString("JUMLAH_KESELURUHAN") == null) {
					h.put("JUMLAH_KESELURUHAN", "");
				} else {
					h.put("JUMLAH_KESELURUHAN",
							rs.getString("JUMLAH_KESELURUHAN"));
				}
				if (rs.getString("JUMLAH_KESELURUHAN_SEK8") == null) {
					h.put("JUMLAH_KESELURUHAN_SEK8", "");
				} else {
					h.put("JUMLAH_KESELURUHAN_SEK8",
							rs.getString("JUMLAH_KESELURUHAN_SEK8"));
				}
				if (rs.getString("JUMLAH_KESELURUHAN_SEK17") == null) {
					h.put("JUMLAH_KESELURUHAN_SEK17", "");
				} else {
					h.put("JUMLAH_KESELURUHAN_SEK17",
							rs.getString("JUMLAH_KESELURUHAN_SEK17"));
				}
				if (rs.getString("JUMLAH_FAIL_HAPUS") == null) {
					h.put("JUMLAH_FAIL_HAPUS", "");
				} else {
					h.put("JUMLAH_FAIL_HAPUS",
							rs.getString("JUMLAH_FAIL_HAPUS"));
				}
				if (rs.getString("JUMLAH_SELESAI") == null) {
					h.put("JUMLAH_SELESAI", "");
				} else {
					h.put("JUMLAH_SELESAI", rs.getString("JUMLAH_SELESAI"));
				}
				if (rs.getString("JUMLAH_XSELESAI") == null) {
					h.put("JUMLAH_XSELESAI", "");
				} else {
					h.put("JUMLAH_XSELESAI", rs.getString("JUMLAH_XSELESAI"));
				}
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Hashtable stat_fail_xxxx_backupbyrazman(String role, String user_id) throws Exception {

		Db db = null;
		String sql = "";
		Hashtable get_negeri = kod_negeri();
		String kod_negeri = (String) get_negeri.get("KOD_NEGERI");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "";

			if (!kod_negeri.equals("16")) {
				sql += " SELECT (SELECT INITCAP(U.NAMA_PEJABAT) FROM TBLRUJPEJABATJKPTG U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"
						+ user_id + "' ) AS NAMA_NEGERI_SERVER,  ";
			} else {
				sql += " SELECT (SELECT INITCAP(N.NAMA_NEGERI) FROM TBLLOOKUPSTATE S,TBLRUJNEGERI N WHERE S.KOD_NEGERI = N.KOD_NEGERI ) AS NAMA_NEGERI_SERVER,  ";
			}

			sql += " (SELECT TO_CHAR(COUNT(*),'999,999,999') FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";

			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				sql += "AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "' ";
						
						 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_id+"  ";
						
						sql += " )";
			}
			sql += " ) AS JUMLAH_KESELURUHAN,  ";

			sql += " (SELECT TO_CHAR(COUNT(*),'999,999,999') FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				sql += "AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "' ";
						
						 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_id+"  ";
						
						sql += " )";
			}
			sql += " AND SEKSYEN = '8' AND P.ID_STATUS <> '999') AS JUMLAH_KESELURUHAN_SEK8,  ";

			sql += " (SELECT TO_CHAR(COUNT(*),'999,999,999') FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";

				sql += "AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "'";
						
						 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_id+"  ";
						
						sql += " )";
			}
			sql += " AND SEKSYEN = '17' AND P.ID_STATUS <> '999') AS JUMLAH_KESELURUHAN_SEK17,  ";

			sql += "(SELECT COUNT(*) FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				sql += "AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "'";
						
						 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_id+"  ";
						
						sql += " )";
			}
			sql += " AND P.ID_STATUS = '21' AND P.ID_STATUS <> '999' AND P.SEKSYEN IN (8,17)) AS JUMLAH_SELESAI,  ";

			sql += " (SELECT COUNT(*) FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				sql += "AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "'";
						
						 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_id+"  ";
						
						sql+=" )";
			}
			sql += " AND P.ID_STATUS <> '21' AND P.ID_STATUS <> '999' AND P.SEKSYEN IN (8,17)) AS JUMLAH_XSELESAI, ";

			sql += " (SELECT TO_CHAR(COUNT(*),'999,999,999') FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				sql += " AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "' ";
						
						 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_id+"  ";
						
						sql += " )";
			}
			sql += " AND P.ID_STATUS = '999') AS JUMLAH_FAIL_HAPUS  " +

			"FROM DUAL ";

			myLogger.info(" STATISTIK :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("NAMA_NEGERI_SERVER") == null) {
					h.put("NAMA_NEGERI_SERVER", "");
				} else {
					h.put("NAMA_NEGERI_SERVER",
							rs.getString("NAMA_NEGERI_SERVER"));
				}
				if (rs.getString("JUMLAH_KESELURUHAN") == null) {
					h.put("JUMLAH_KESELURUHAN", "");
				} else {
					h.put("JUMLAH_KESELURUHAN",
							rs.getString("JUMLAH_KESELURUHAN"));
				}
				if (rs.getString("JUMLAH_KESELURUHAN_SEK8") == null) {
					h.put("JUMLAH_KESELURUHAN_SEK8", "");
				} else {
					h.put("JUMLAH_KESELURUHAN_SEK8",
							rs.getString("JUMLAH_KESELURUHAN_SEK8"));
				}
				if (rs.getString("JUMLAH_KESELURUHAN_SEK17") == null) {
					h.put("JUMLAH_KESELURUHAN_SEK17", "");
				} else {
					h.put("JUMLAH_KESELURUHAN_SEK17",
							rs.getString("JUMLAH_KESELURUHAN_SEK17"));
				}
				if (rs.getString("JUMLAH_FAIL_HAPUS") == null) {
					h.put("JUMLAH_FAIL_HAPUS", "");
				} else {
					h.put("JUMLAH_FAIL_HAPUS",
							rs.getString("JUMLAH_FAIL_HAPUS"));
				}
				if (rs.getString("JUMLAH_SELESAI") == null) {
					h.put("JUMLAH_SELESAI", "");
				} else {
					h.put("JUMLAH_SELESAI", rs.getString("JUMLAH_SELESAI"));
				}
				if (rs.getString("JUMLAH_XSELESAI") == null) {
					h.put("JUMLAH_XSELESAI", "");
				} else {
					h.put("JUMLAH_XSELESAI", rs.getString("JUMLAH_XSELESAI"));
				}
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}

//IL
public String getNegeriServer(String user_id) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String kod_negeri = (String) kod_negeri().get("KOD_NEGERI");
			String sql = "";

			if (!kod_negeri.equals("16")) {
				sql += "SELECT INITCAP(U.NAMA_PEJABAT) negeriServer FROM TBLRUJPEJABATJKPTG U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"
						+ user_id + "'";
			} else {
				sql += "SELECT INITCAP(N.NAMA_NEGERI) negeriServer FROM TBLLOOKUPSTATE S,TBLRUJNEGERI N WHERE S.KOD_NEGERI = N.KOD_NEGERI";
			}
			myLogger.info(" getNegeriServer :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			String negeriServer = "";
			while (rs.next()) {
				negeriServer = rs.getString("negeriServer");
			}

			return negeriServer;
		} finally {
			if (db != null)
				db.close();
		}
	}
	public String getJumlahKeseluruhan(String role, String user_id) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String kod_negeri = (String) kod_negeri().get("KOD_NEGERI");
			
			String sql = "SELECT TO_CHAR(COUNT(*),'999,999,999') cnt FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
	
			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				sql += "AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "')";
			}
			myLogger.info(" getJumlahKeseluruhan :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			String cnt = "";
			while (rs.next()) {
				cnt = rs.getString("cnt");
			}

			return cnt;
		} finally {
			if (db != null)
				db.close();
		}
	}
	public String getFailSek8(String role, String user_id) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String kod_negeri = (String) kod_negeri().get("KOD_NEGERI");
		
			String sql = "SELECT TO_CHAR(COUNT(*),'999,999,999') cnt FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				sql += "AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "')";
			}
			sql += " AND SEKSYEN = '8' AND P.ID_STATUS <> '999'";
			myLogger.info(" getFailSek8 :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			String cnt = "";
			while (rs.next()) {
				cnt = rs.getString("cnt");
			}

			return cnt;
		} finally {
			if (db != null)
				db.close();
		}
	}
	public String getFailSek17(String role, String user_id) throws Exception {

		Db db = null;
		String sql = "";
		Hashtable get_negeri = kod_negeri();
		String kod_negeri = (String) get_negeri.get("KOD_NEGERI");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();



			sql += "SELECT TO_CHAR(COUNT(*),'999,999,999') cnt FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";

				sql += "AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "')";
			}
			sql += " AND SEKSYEN = '17' AND P.ID_STATUS <> '999'";

			myLogger.info(" getFailSek17 :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			String cnt = "";
			while (rs.next()) {
				cnt = rs.getString("cnt");
			}

			return cnt;
		} finally {
			if (db != null)
				db.close();
		}
	}

	//Carian
		@SuppressWarnings("unchecked")
		public List DBcarianFail(HttpSession session, String type, String search)throws Exception {

			String userId = (String) session.getAttribute("_ekptg_user_id");
			String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");

			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SimpleDateFormat sdf = null;

			List senaraiFail = null;

			String sql = "";
			Integer count = 0;

			try {

				db = new Db();
				sdf = new SimpleDateFormat("dd/MM/yyyy");
				stmt = db.getStatement();
				
				sql = "SELECT G.TARIKH_BICARA,B.TARIKH_KEMASKINI,A.ID_FAIL,A.NO_FAIL ,A.ID_NEGERI, C.KETERANGAN, A.ID_URUSAN, A.ID_SUBURUSAN, " +
						" B.ID_PERMOHONAN,D.ID_SIMATI,E.ID_PERMOHONANSIMATI," +
						" B.TARIKH_MOHON,A.FLAG_JENIS_FAIL,B.SEKSYEN,B.ID_STATUS," +
						" n.NAMA_NEGERI negeri_mohon ,UPPER(pj.nama_pejabat) AS nama_pejabat,UPPER (d1.nama_daerah) AS daerah_pejabat " +
						" FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLRUJSTATUS C, TBLPPKSIMATI D,TBLPPKPERMOHONANSIMATI E ,TBLPPKKEPUTUSANPERMOHONAN F," +
						" TBLPPKPERBICARAAN G, tblrujnegeri n, tblrujpejabaturusan pu,  tblrujpejabatjkptg pj, tblrujdaerah d1 " +
						" WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND E.ID_SIMATI = D.ID_SIMATI AND E.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_PERMOHONAN = F.ID_PERMOHONAN(+) " +
						" AND F.ID_KEPUTUSANPERMOHONAN = G.ID_KEPUTUSANPERMOHONAN(+)  " +
						" AND B.ID_NEGERIMHN = n.ID_NEGERI" +
						" AND pu.id_pejabatjkptg = pj.id_pejabatjkptg " +
						" AND pj.id_jenispejabat = 22 " +
						" AND pj.id_seksyen = 2 " +
						" AND pu.id_daerahurus = B.id_daerahmhn " +
						" AND pj.id_daerah = d1.id_daerah " +
						" AND CONCAT(D.NO_KP_BARU,TO_CHAR(A.NO_FAIL)) LIKE '%"+search+"%'";
						//"AND (A.NO_FAIL LIKE '%"+search+"%' or D.NO_KP_BARU LIKE '%"+search+"%')";
				
					myLogger.info("carian dashboard : "+sql);
					stmt.setFetchSize(10);
					rs = stmt.executeQuery(sql);

					int bil = 1;
					senaraiFail = Collections.synchronizedList(new ArrayList());
					Map h = null;

					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						h.put("bil", bil);
						h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
						h.put("STATUS", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
						h.put("id_urusan", rs.getString("ID_URUSAN") == null ? "" : rs.getString("ID_URUSAN"));
						h.put("id_suburusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
						h.put("id_permohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
						h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
						h.put("idpermohonansimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
						h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
						h.put("jenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
						h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
						h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
						h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
						
//						String idNegeriLogin = findUser(userId);
						h.put("id_negeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
						h.put("idNegeriLogin", negeriUser);
						
						h.put("tarikhBicara", rs.getString("TARIKH_BICARA") == null ? "" : sdf.format(rs.getDate("TARIKH_BICARA")));
						h.put("tarikhSelesai", rs.getString("TARIKH_KEMASKINI") == null ? "" : sdf.format(rs.getDate("TARIKH_KEMASKINI")));
						h.put("nama_pejabat", rs.getString("nama_pejabat") == null ? "" : rs.getString("nama_pejabat"));
						h.put("daerah_pejabat", rs.getString("daerah_pejabat") == null ? "" : rs.getString("daerah_pejabat"));
						h.put("negeri_mohon", rs.getString("negeri_mohon") == null ? "" : rs.getString("negeri_mohon"));
			
						senaraiFail.add(h);
						bil++;
						count++;
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}

				return senaraiFail;

			}
		
		
		private String findUser(String userId) throws Exception {

			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			String idNegeriUserLogin = "";

			String sql = "";
			db = new Db();
			stmt = db.getStatement();
			sql = "SELECT A.ID_NEGERI " +
					"FROM USERS_INTERNAL A, USERS B "+
					"WHERE A.USER_ID = '"+userId+"'";
			
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				idNegeriUserLogin = rs.getString("ID_NEGERI");
			}
		return idNegeriUserLogin;
	}

		
		
		// Data Fail Belum Kemaskini 
		@SuppressWarnings("unchecked")
		public List DBcarianFailBelumKemaskiniSek8(HttpSession session, String type, String search)throws Exception {

			String userId = (String) session.getAttribute("_ekptg_user_id");
			String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");

			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SimpleDateFormat sdf = null;

			List listFailBelumKemaskiniSek8 = null;

			String sql = "";
			Integer count = 0;

			try {

				db = new Db();
				sdf = new SimpleDateFormat("dd/MM/yyyy");
				stmt = db.getStatement();
				
				sql = "SELECT A.NO_FAIL, A.ID_FAIL, C.KETERANGAN,C.ID_STATUS, E.TARIKH_BICARA, A.ID_URUSAN, A.ID_SUBURUSAN,D.ID_KEPUTUSANPERMOHONAN,B.ID_PERMOHONAN,F.ID_SIMATI,G.ID_PERMOHONANSIMATI," +
						"B.TARIKH_MOHON,A.FLAG_JENIS_FAIL,B.SEKSYEN,B.ID_STATUS FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLRUJSTATUS C, TBLPPKKEPUTUSANPERMOHONAN D, TBLPPKPERBICARAAN E,TBLPPKSIMATI F,TBLPPKPERMOHONANSIMATI G " +
						"WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND B.ID_PERMOHONAN = D.ID_PERMOHONAN AND D.ID_KEPUTUSANPERMOHONAN = E.ID_KEPUTUSANPERMOHONAN " +
						"AND E.TARIKH_BICARA - SYSDATE <= 5 AND E.TARIKH_BICARA - SYSDATE >= 0 AND c.keterangan not like '%SELESAI%' and c.keterangan not like '%BATAL%' " +
						"AND G.ID_SIMATI = F.ID_SIMATI AND G.ID_PERMOHONAN = B.ID_PERMOHONAN " +
						"and B.ID_DAERAHMHN in ( select distinct u.id_daerahurus from TBLRUJPEJABATURUSAN u, users_internal ur " +
						"where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+userId+"')";
						
					myLogger.info("carian dashboard : "+sql);
					stmt.setFetchSize(10);
					rs = stmt.executeQuery(sql);

					int bil = 1;
					listFailBelumKemaskiniSek8 = Collections.synchronizedList(new ArrayList());
					Map h = null;

					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());	
						h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
//						h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
//						h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
//						h.put("STATUS", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
						h.put("TARIKH_BICARA", rs.getString("TARIKH_BICARA") == null ? "" : sdf.format(rs.getDate("TARIKH_BICARA")));
//						h.put("ID_URUSAN", rs.getString("ID_URUSAN") == null ? "" : rs.getString("ID_URUSAN"));
//						h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
//						h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
						h.put("ID_KEPUTUSANPERMOHONAN", rs.getString("ID_KEPUTUSANPERMOHONAN") == null ? "" : rs.getString("ID_KEPUTUSANPERMOHONAN"));
						
						h.put("id_permohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
						h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
						h.put("idpermohonansimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
						h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
						h.put("jenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
						h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
						h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
						h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));

					
						listFailBelumKemaskiniSek8.add(h);
						bil++;
						count++;
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}

				return listFailBelumKemaskiniSek8;

			}
		
		// count fail belum kemaskini
		@SuppressWarnings("unchecked")
		public String notifikasi_failKemaskini(String role, String id_negeri_user,
				String id_esaduan, String flag_notifikasi, String user_terima,
				String notread) throws Exception {

			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql= "SELECT COUNT(A.ID_FAIL) as bill " +
						"FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLRUJSTATUS C, TBLPPKKEPUTUSANPERMOHONAN D, TBLPPKPERBICARAAN E WHERE A.ID_FAIL = B.ID_FAIL " +
						"AND B.ID_STATUS = C.ID_STATUS AND B.ID_PERMOHONAN = D.ID_PERMOHONAN AND D.ID_KEPUTUSANPERMOHONAN = E.ID_KEPUTUSANPERMOHONAN " +
						"AND E.TARIKH_BICARA - SYSDATE <= 5 AND E.TARIKH_BICARA - SYSDATE >= 0 AND c.keterangan not like '%SELESAI%' and c.keterangan not like '%BATAL%' " +
						"and B.ID_DAERAHMHN in ( select distinct u.id_daerahurus from TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+user_terima+"')";

				myLogger.info("notifikasi_failKemaskini : "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				
				String bill = "";
				while (rs.next()) {
					bill = rs.getString("bill");
				}

				return bill;
				
			} finally {
				if (db != null)
					db.close();
			}
			
		}
		
		
		

		// Data Fail TamatTempoh Sek8
		@SuppressWarnings("unchecked")
		public List DBcarianFailTamatTempohKpi(HttpSession session, String type, String search)throws Exception {

			String userId = (String) session.getAttribute("_ekptg_user_id");
			String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");

			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SimpleDateFormat sdf = null;

			List listFailTamatTempohKpi = null;

			String sql = "";
			Integer count = 0;

			try {

				db = new Db();
				sdf = new SimpleDateFormat("dd/MM/yyyy");
				stmt = db.getStatement();
				
				sql = "SELECT A.ID_NEGERI,A.ID_FAIL, A.NO_FAIL, C.KETERANGAN, B.TARIKH_MOHON, B.ID_STATUS, A.ID_URUSAN,A.ID_SUBURUSAN,B.ID_PERMOHONAN,D.ID_SIMATI,E.ID_PERMOHONANSIMATI,B.TARIKH_MOHON,A.FLAG_JENIS_FAIL,B.SEKSYEN,B.ID_STATUS " +
						"FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLRUJSTATUS C,TBLPPKSIMATI D,TBLPPKPERMOHONANSIMATI E " +
						"WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND A.ID_URUSAN = 382 and A.ID_SUBURUSAN =59 AND sysdate - B.TARIKH_MOHON>=115 " +
						"AND sysdate - B.TARIKH_MOHON<=135 AND c.keterangan not like '%SELESAI%' and c.keterangan not like '%BATAL%' AND E.ID_PERMOHONAN = B.ID_PERMOHONAN " +
						"AND E.ID_SIMATI = D.ID_SIMATI and B.ID_DAERAHMHN in ( select distinct u.id_daerahurus from TBLRUJPEJABATURUSAN u, users_internal ur " +
						"where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+userId+"') AND E.ID_PERMOHONAN = B.ID_PERMOHONAN and c.id_seksyen=2 ORDER BY B.TARIKH_MOHON";
				
					myLogger.info("carian dashboard : "+sql);
					stmt.setFetchSize(10);
					rs = stmt.executeQuery(sql);

					int bil = 1;
					listFailTamatTempohKpi = Collections.synchronizedList(new ArrayList());
					Map h = null;

					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());	
						h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
						h.put("STATUS", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
						h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
						h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
						h.put("ID_URUSAN", rs.getString("ID_URUSAN") == null ? "" : rs.getString("ID_URUSAN"));
						h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
						
						h.put("id_permohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
						h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
						h.put("idpermohonansimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
						h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
						h.put("jenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
						h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
						h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
						h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
						
//						String idNegeriLogin = findUser(userId);
						h.put("id_negeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
						h.put("idNegeriLogin", negeriUser);
						
						listFailTamatTempohKpi.add(h);
						bil++;
						count++;
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}

				return listFailTamatTempohKpi;

			}

		
		
	// count fail tempoh kpi
	@SuppressWarnings("unchecked")
	public String notifikasi_failTempohKpi(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread) throws Exception {

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT COUNT(A.ID_FAIL) as bill1 FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLRUJSTATUS C " +
					"WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = C.ID_STATUS " +
					"AND A.ID_URUSAN = 382 and A.ID_SUBURUSAN =59 AND sysdate - B.TARIKH_MOHON>=115 " +
					"AND sysdate - B.TARIKH_MOHON<=135 AND c.keterangan not like '%SELESAI%' and c.keterangan not like '%BATAL%' " +
					"and B.ID_DAERAHMHN in ( select distinct u.id_daerahurus from TBLRUJPEJABATURUSAN u, users_internal ur " +
					"where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+user_terima+"') and c.id_seksyen=2 ORDER BY B.TARIKH_MOHON";

			myLogger.info("notifikasi_failTempohKpi : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			String bill1 = "";
			while (rs.next()) {
				bill1 = rs.getString("bill1");
			}

			return bill1;
			
		} finally {
			if (db != null)
				db.close();
		}
		
	}
				
	// Data Fail TamatTempoh 17
	@SuppressWarnings("unchecked")
	public List DBcarianFailTamatTempohKpiSek17(HttpSession session, String type, String search)throws Exception {

		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");

		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;

		List listFailTamatTempohKpiSek17 = null;

		String sql = "";
		Integer count = 0;

		try {

			db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();
			
			sql = "SELECT A.ID_NEGERI,A.NO_FAIL, A.ID_FAIL, C.KETERANGAN, B.TARIKH_MOHON, B.ID_STATUS, A.ID_URUSAN,A.ID_SUBURUSAN,B.ID_PERMOHONAN,D.ID_SIMATI,E.ID_PERMOHONANSIMATI," +
					"B.TARIKH_MOHON,A.FLAG_JENIS_FAIL,B.SEKSYEN,B.ID_STATUS " +
					"FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLRUJSTATUS C ,TBLPPKSIMATI D,TBLPPKPERMOHONANSIMATI E " +
					"WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND A.ID_URUSAN = 382 and A.ID_SUBURUSAN =60 AND sysdate - B.TARIKH_MOHON>=70 " +
					"AND E.ID_SIMATI = D.ID_SIMATI AND E.ID_PERMOHONAN = B.ID_PERMOHONAN AND sysdate - B.TARIKH_MOHON<=90 AND c.keterangan not like '%SELESAI%' " +
					"and c.keterangan not like '%BATAL%' and B.ID_DAERAHMHN in ( select distinct u.id_daerahurus from TBLRUJPEJABATURUSAN u, users_internal ur " +
					"where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+userId+"') and c.id_seksyen=2 ORDER BY B.TARIKH_MOHON";
			
				myLogger.info("carian dashboard : "+sql);
				stmt.setFetchSize(10);
				rs = stmt.executeQuery(sql);

				int bil = 1;
				listFailTamatTempohKpiSek17 = Collections.synchronizedList(new ArrayList());
				Map h = null;

				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());	
					h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
					h.put("STATUS", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
					h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
					h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
					h.put("ID_URUSAN", rs.getString("ID_URUSAN") == null ? "" : rs.getString("ID_URUSAN"));
					h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));

					h.put("id_permohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
					h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
					h.put("idpermohonansimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
					h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
					h.put("jenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
					h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
					h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
					h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
					
//								String idNegeriLogin = findUser(userId);
					h.put("id_negeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
					h.put("idNegeriLogin", negeriUser);
					
					listFailTamatTempohKpiSek17.add(h);
					bil++;
					count++;
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return listFailTamatTempohKpiSek17;

		}
	
	// count fail tempoh kpi
	@SuppressWarnings("unchecked")
	public String notifikasi_failTempohKpiSek17(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread) throws Exception {

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT COUNT(A.ID_FAIL) as bill2 FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLRUJSTATUS C " +
					"WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND A.ID_URUSAN = 382 and A.ID_SUBURUSAN =60 AND sysdate - B.TARIKH_MOHON>=70 " +
					"AND sysdate - B.TARIKH_MOHON<=90 AND c.keterangan not like '%SELESAI%' and c.keterangan not like '%BATAL%' " +
					"and B.ID_DAERAHMHN in ( select distinct u.id_daerahurus from TBLRUJPEJABATURUSAN u, users_internal ur " +
					"where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+user_terima+"') and c.id_seksyen=2 ORDER BY B.TARIKH_MOHON";

			myLogger.info("notifikasi_failTempohKpiSek17 : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			String bill2 = "";
			while (rs.next()) {
				bill2 = rs.getString("bill2");
			}

			//myLogger.info("carian dashboard : bill2="+bill2);
			return bill2;
			
		} finally {
			if (db != null)
				db.close();
		}
		
	}

//end IL


	public Hashtable kod_negeri() throws Exception {

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT KOD_NEGERI FROM TBLLOOKUPSTATE S ";
			myLogger.info(" KOD_NEGERI :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("KOD_NEGERI") == null) {
					h.put("KOD_NEGERI", "");
				} else {
					h.put("KOD_NEGERI", rs.getString("KOD_NEGERI"));
				}

			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Hashtable notifikasi_borangB(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread) throws Exception {

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT COUNT(DISTINCT ID_FAIL) AS TOTAL_BORANGB FROM "+
					" ( "+
					" SELECT (CASE WHEN SUM(TABLE_BB.COUNT_LEBIH_30) > 0 AND  " +
					" SUM(TABLE_BB.COUNT_KEPUTUSAN_PERMOHONAN) = 0 THEN TABLE_BB.ID_FAIL END) AS ID_FAIL "+
					" FROM "+
					" (     "+
					" SELECT DISTINCT FF.ID_FAIL, (CASE WHEN  ST.ID_STATUS = 170 AND (SYSDATE -  MAX (TO_DATE (TO_CHAR (STA.TARIKH_MASUK,'DD/MM/YYYY'), 'DD/MM/YYYY')) > 30) "+
					" THEN 1 END) AS  COUNT_LEBIH_30, COUNT((CASE WHEN ST.ID_STATUS = 14 THEN 1 END )) AS COUNT_KEPUTUSAN_PERMOHONAN "+
					" FROM TBLPPKPERMOHONAN A,TBLPFDFAIL FF,TBLRUJSUBURUSANSTATUS ST,TBLRUJSUBURUSANSTATUSFAIL STA "+
					" WHERE A.ID_DAERAHMHN IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, "+
					" USERS_INTERNAL UR  WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"+user_terima+"' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_terima+"  ";
					
					sql += " ) "+
					" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS AND A.ID_FAIL = FF.ID_FAIL "+
					" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND A.ID_STATUS <> '999' "+
					" GROUP BY FF.ID_FAIL,ST.ID_STATUS     "+                 
					" )  TABLE_BB   "+
					" GROUP BY TABLE_BB.ID_FAIL) "+
					" UTAMA  "+
					" WHERE UTAMA.ID_FAIL IS NOT NULL ";
			
			/*
			sql += " SELECT ";
			sql += " (SELECT COUNT(*) AS TOTAL_FAIL  "
					+ " FROM TBLPPKPERMOHONAN PPP, TBLPFDFAIL FFF "
					+ " WHERE PPP.ID_FAIL = FFF.ID_FAIL AND "
					+ " (SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ user_terima
					+ "') "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "
					+ " AND A.ID_STATUS <> '999' "
					+ " AND S.ID_STATUS = '14' AND FFF.ID_FAIL = FF.ID_FAIL) = 0 AND "
					+ " ( "
					+ " SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ user_terima
					+ "')  "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "
					+ " AND A.ID_STATUS <> '999' "
					+ " AND S.ID_STATUS = '170' AND F.ID_FAIL = FFF.ID_FAIL "
					+ " ) > 0 "
					+ " AND "
					+ " (TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') -  "
					+ " (SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ user_terima
					+ "') "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "
					+ " AND A.ID_STATUS <> '999' "
					+ " AND S.ID_STATUS = '170' "
					+ " AND  FFF.ID_FAIL = FF.ID_FAIL))>30 ) AS TOTAL_BORANGB ";
			sql += " FROM DUAL";
			*/
			
			
			myLogger.info("--------------- LIST NOTIFICATION DASHBOARD LIST BORANG B:"
					+ sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				h.put("TOTAL_BORANGB",
						rs.getString("TOTAL_BORANGB") == null ? "" : rs
								.getInt("TOTAL_BORANGB"));
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	

	public Integer notifikasi_Aduan(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread) throws Exception {

		Db db = null;
		String sql = "";
		Integer jumlah_notifikasi = 0;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT COUNT (*) AS JUMLAH_ADUAN FROM TBLESADUAN A, "+
					 " USERS U,USERS_INTERNAL UI,TBLESNOTIFIKASI X,TBLRUJPEJABATJKPTG PEJ  "+             
					 " WHERE A.USER_ID = U.USER_ID AND X.ID_ESADUAN = A.ID_ESADUAN "+
					 " AND U.USER_ID = UI.USER_ID AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG "+
					 " AND A.ID_STATUS NOT IN ('16125') AND X.ID_ESADUAN = A.ID_ESADUAN "+
					 " AND A.ID_STATUS IS NOT NULL AND X.ID_ESNOTIFIKASI IS NOT NULL" ;
			
					if (!id_esaduan.equals("")) {
						sql += " AND X.ID_ESADUAN = '" + id_esaduan + "' ";
					}
					if (!id_negeri_user.equals("") && !role.equals("adminsuper_es")) {
						sql += " AND A.ID_NEGERI_PENGADU = '" + id_negeri_user + "' ";
					}
					if (!user_terima.equals("")) {
						sql += " AND X.ID_USER_NOTIFIKASI_TERIMA = '" + user_terima
								+ "' ";
					}
					if (!flag_notifikasi.equals("")) {
						sql += " AND X.FLAG_NOTIFIKASI = '" + flag_notifikasi + "'";
					}
					if (!notread.equals("")) {
						sql += " AND X.FLAG_READ = '" + notread + "'";
					}
				myLogger.info("notifikasi aduan : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				
				if(rs.getString("JUMLAH_ADUAN") != null)
				{
					jumlah_notifikasi = rs.getInt("JUMLAH_ADUAN");
				}
				
//				h.put("JUMLAH_ADUAN", rs.getString("JUMLAH_ADUAN") == null ? ""
//						: rs.getInt("JUMLAH_ADUAN"));
//				h.put("ONLINE_SEK8", rs.getString("ONLINE_SEK8") == null ? ""
//						: rs.getInt("ONLINE_SEK8"));
//				h.put("ONLINE_SEK17", rs.getString("ONLINE_SEK17") == null ? ""
//						: rs.getInt("ONLINE_SEK17"));
//				h.put("FAIL_PINDAH", rs.getString("FAIL_PINDAH") == null ? ""
//						: rs.getInt("FAIL_PINDAH"));
//				h.put("INBOX",
//						rs.getString("INBOX") == null ? "" : rs.getInt("INBOX"));
//				h.put("TOTAL_KIV",
//						rs.getString("TOTAL_KIV") == null ? "" : rs
//								.getInt("TOTAL_KIV"));
//				h.put("KEBENARAN_EDIT",
//						rs.getString("KEBENARAN_EDIT") == null ? "" : rs
//								.getInt("KEBENARAN_EDIT"));

			}
			return jumlah_notifikasi;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Integer notifikasi_OT(String user_id) throws Exception {

		Db db = null;
		String sql = "";
		Integer jumlah_notifikasi = 0;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT COUNT (*) AS JUMLAH_OT FROM TBLPERMOHONANOT P  " +
					" WHERE P.ID_STATUS = '1' AND P.ID_PELULUS = '"+user_id+"' " ;
				myLogger.info("notifikasi_OT : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				
				if(rs.getString("JUMLAH_OT") != null)
				{
					jumlah_notifikasi = rs.getInt("JUMLAH_OT");
				}
				


			}
			return jumlah_notifikasi;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Integer notifikasi_BU(String USER_ROLE,String USER_UNIT,String USER_NEGERI) throws Exception {

		Db db = null;
		String sql = "";
		String sqlOLD = "";
		Integer jumlah_notifikasi = 0;
		
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			myLogger.info("USER_NEGERI ::::::::::;: "+USER_NEGERI);
			sqlOLD = " SELECT COUNT (*) AS JUMLAH_BU FROM TBLPERMOHONANBANTUUNIT P  " +
					" WHERE P.ID_STATUS = '1' AND '"+USER_ROLE+"' = 'adminppk' AND P.ID_NEGERI = '"+USER_NEGERI+"' ";
			
			sql = " SELECT COUNT (*) AS JUMLAH_BU FROM TBLPERMOHONANBANTUUNIT P  " +
					" WHERE P.ID_STATUS = '1' " ;
					if(USER_NEGERI.equals("16")){
						sql += " AND (P.PERMOHONAN_NEGERI = 'Y') ";
					}else{
						sql += "AND '"+USER_ROLE+"' = 'adminppk' AND P.ID_NEGERI = '"+USER_NEGERI+"'";
					}
			//sql +=" AND '"+USER_ROLE+"' = 'adminppk' AND P.ID_UNIT = '"+USER_UNIT+"' ";
				myLogger.info("notifikasi_BU : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				
				if(rs.getString("JUMLAH_BU") != null)
				{
					jumlah_notifikasi = rs.getInt("JUMLAH_BU");
				}
				


			}
			return jumlah_notifikasi;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Integer notifikasi_PengesahanNilaian(String USER_ROLE,String USER_UNIT,String USER_NEGERI) throws Exception {

		Db db = null;
		String sql = "";
		String sqlOLD = "";
		Integer jumlah_notifikasi = 0;
		
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			myLogger.info("USER_NEGERI ::::::::::;: "+USER_NEGERI);
			
			
			sql = " SELECT COUNT (*) AS JUMLAH_PN FROM TBLPPKPERMOHONAN WHERE PENGESAHAN_NILAIANHARTA = '"+userId+"' AND FLAG_PENGESAHANNILAIANHARTA IS NULL";
				myLogger.info("notifikasi_PengesahanNilaian : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				
				if(rs.getString("JUMLAH_PN") != null)
				{
					jumlah_notifikasi = rs.getInt("JUMLAH_PN");
				}
				


			}
			myLogger.info("count PengesahanNilaian : "+jumlah_notifikasi);
			return jumlah_notifikasi;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Integer notifikasi_OnlineS8(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread) throws Exception {

		Db db = null;
		String sql = "";
		Integer jumlah_notifikasi = 0;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*sql = "  SELECT COUNT (*) AS NOTIFICATION FROM TBLPPKPERMOHONAN A,TBLPFDFAIL F "+
					"  WHERE A.ID_DAERAHMHN IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "+
					"  WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"+user_terima+"' ";
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_terima+"  ";
					sql+= " ) "+
					"  AND A.ID_FAIL = F.ID_FAIL " +
					" AND A.ID_STATUS <>  '171' " +		
					//" AND A.ID_STATUS =  '171' " +
					" AND A.SEKSYEN = '8' AND A.FLAG_JENIS_PERMOHONAN = 0 " ;*/
			
			sql = "SELECT count(F.ID_FAIL) AS NOTIFICATION "                 
	                +" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P," 
	                +" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D" 
	                +" WHERE" 
	                +" D.id_daerah in ( select distinct u.id_daerahurus from"
	                +" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+user_terima+"' ";
					
					 sql += " UNION "+                                                                            
						" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
						" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
						" WHERE ID_STATUS = 2  "+ 
						" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
						" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
						" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
						" AND PBU.ID_PEMOHON = "+user_terima+"  ";
					
					sql += " )"
	                +" AND ST.ID_STATUS = S.ID_STATUS(+)"
	                +" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)" 	
	                +" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)" 
	                +" AND A.ID_FAIL = F.ID_FAIL(+)" 
	                +" AND A.ID_DAERAHMHN = D.ID_DAERAH"
	                +" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN" 
	                +" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN" 
	                +" AND P.ID_SIMATI = MS.ID_SIMATI" 
	                +" AND A.ID_STATUS <> '999'"
	                +" AND STA.ID_SUBURUSANSTATUS = 614" 
	                +" AND A.SEKSYEN = 8"  
	                +" AND STA.AKTIF = 1" 
	                +" AND A.FLAG_JENIS_PERMOHONAN = 0" 
	               // +" AND ( TO_DATE(SYSDATE,'dd/mm/yyyy') -  TO_DATE(A.TARIKH_MOHON_ONLINE,'dd/mm/yyyy')) <= 21 " 
	                +" ORDER BY STA.ID_SUBURUSANSTATUSFAIL"
	                +"";
			
			
					
				myLogger.info("notifikasi ONLINE S8 : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				
				if(rs.getString("NOTIFICATION") != null)
				{
					jumlah_notifikasi = rs.getInt("NOTIFICATION");
				}


			}
			return jumlah_notifikasi;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Integer notifikasi_FailLengkapUntukBicara(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread) throws Exception {

		Db db = null;
		String sql = "";
		Integer jumlah_notifikasi = 0;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT COUNT(DISTINCT F.ID_FAIL) AS NOTIFICATION FROM TBLPPKPERMOHONAN P, "+
					" TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSUBURUSANSTATUSFAIL STA, "+
					" TBLRUJSUBURUSANSTATUS SUB WHERE  " +
					" P.ID_DAERAHMHN IN ( "+
					" SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "+
					" WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"+user_terima+"' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_terima+"  ";
					
					sql += " ) "+
					" AND " +
					" P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN "+
					" AND P.ID_STATUS IN (151) "+
					" AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "+
					" AND STA.AKTIF = '1' AND P.SEKSYEN = '8' AND P.FLAG_JENIS_PERMOHONAN = '1' "+
					" AND KP.KEPUTUSAN_PERMOHONAN = '151' AND F.NO_FAIL IS NOT NULL " ;
			
			
					
				myLogger.info("notifikasi_FailLengkapUntukBicara : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				
				if(rs.getString("NOTIFICATION") != null)
				{
					jumlah_notifikasi = rs.getInt("NOTIFICATION");
				}


			}
			return jumlah_notifikasi;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Integer notifikasi_OnlineS17(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread) throws Exception {

		Db db = null;
		String sql = "";
		Integer jumlah_notifikasi = 0;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "  SELECT COUNT (*) AS NOTIFICATION FROM TBLPPKPERMOHONAN A,TBLPFDFAIL F "+
					"  WHERE A.ID_DAERAHMHN IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "+
					"  WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"+user_terima+"' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_terima+"  ";
					
					sql += " ) "+
					"  AND A.ID_FAIL = F.ID_FAIL AND A.ID_STATUS =  '171' AND A.SEKSYEN = '17' AND A.FLAG_JENIS_PERMOHONAN = 0 " ;
			
					
				myLogger.info("notifikasi ONLINE S17 : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				
				if(rs.getString("NOTIFICATION") != null)
				{
					jumlah_notifikasi = rs.getInt("NOTIFICATION");
				}


			}
			return jumlah_notifikasi;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Integer notifikasi_Pindah(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread) throws Exception {

		Db db = null;
		String sql = "";
		Integer jumlah_notifikasi = 0;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "  SELECT COUNT (DISTINCT A.ID_PERMOHONAN) AS NOTIFICATION "+
					"  FROM TBLPPKPERMOHONAN A,TBLPPKBKE B, TBLPFDFAIL C,TBLPPKPERMOHONANSIMATI D,"+
					"  TBLPPKSIMATI E,TBLPPKPEMOHON F, TBLRUJNEGERI G,TBLRUJDAERAH H,"+
					"  TBLRUJPEJABATURUSAN I,TBLRUJPEJABATJKPTG J,"+
					"  TBLRUJSUBURUSANSTATUSFAIL M,TBLRUJSUBURUSANSTATUS N,"+
					"  TBLRUJSTATUS O,USERS_INTERNAL P,USERS Q,"+
					"  TBLRUJNEGERI R,TBLRUJDAERAH S"+
					"  WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_FAIL = C.ID_FAIL"+
					"  AND D.ID_PERMOHONAN = A.ID_PERMOHONAN AND D.ID_SIMATI = E.ID_SIMATI"+
					"  AND A.ID_PEMOHON = F.ID_PEMOHON AND B.ID_NEGERI = G.ID_NEGERI"+
					"  AND B.ID_DAERAH = H.ID_DAERAH AND I.ID_PEJABATJKPTG = J.ID_PEJABATJKPTG"+
					"  AND M.ID_PERMOHONAN = A.ID_PERMOHONAN AND M.AKTIF = 1"+
					"  AND M.ID_SUBURUSANSTATUS = N.ID_SUBURUSANSTATUS"+
					"  AND N.ID_STATUS = O.ID_STATUS AND J.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG"+
					"  AND P.USER_ID = Q.USER_ID AND A.ID_NEGERIMHN = R.ID_NEGERI"+
					"  AND A.ID_DAERAHMHN = S.ID_DAERAH AND Q.USER_ID = '"+user_terima+"' "+
					"  AND G.ID_NEGERI = P.ID_NEGERI AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL)"+
					"  AND A.ID_STATUS = 56";
					
				myLogger.info("notifikasi pindah : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				
				if(rs.getString("NOTIFICATION") != null)
				{
					jumlah_notifikasi = rs.getInt("NOTIFICATION");
				}


			}
			return jumlah_notifikasi;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	public Integer notifikasi_Inbox(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread) throws Exception {

		Db db = null;
		String sql = "";
		Integer jumlah_notifikasi = 0;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "  SELECT COUNT (*) AS NOTIFICATION FROM TBLMAININBOX A, TBLUSERSINBOX B,TBLINBOXNOTIFIKASI C "+
					"  WHERE A.ID_MAININBOX = B.ID_MAININBOX AND C.FLAG_READ = 'NO' AND A.FLAG_AKTIF = 'Y' "+
					"  AND A.ID_MAININBOX = C.ID_MAININBOX  AND B.USER_ID = '"+user_terima+"' AND C.ID_USER_NOTIFIKASI_TERIMA = B.USER_ID";
					
				myLogger.info("notifikasi inbox : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				
				if(rs.getString("NOTIFICATION") != null)
				{
					jumlah_notifikasi = rs.getInt("NOTIFICATION");
				}


			}
			return jumlah_notifikasi;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Integer notifikasi_Edit(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread) throws Exception {

		Db db = null;
		String sql = "";
		Integer jumlah_notifikasi = 0;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT COUNT (DISTINCT E.ID_EDITNOTIFIKASI) AS NOTIFICATION "+
					" FROM TBLEDITNOTIFIKASI E,TBLPFDFAIL F,TBLPPKPERMOHONAN P, "+
					" TBLPPKPERMOHONANSIMATI PS,TBLPPKSIMATI S, "+
					" TBLPPKPEMOHON PM,TBLRUJSTATUS ST "+
					" WHERE E.FLAG_READ = 'NO' AND P.ID_STATUS = ST.ID_STATUS "+
					" AND P.ID_PEMOHON = PM.ID_PEMOHON AND S.ID_SIMATI = PS.ID_SIMATI "+
					" AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN "+
					" AND P.ID_FAIL = F.ID_FAIL AND E.ID_FAIL = F.ID_FAIL "+
					" AND E.ID_USER_NOTIFIKASI_TERIMA = '"+user_terima+"' ";
					
				myLogger.info("notifikasi edit : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				
				if(rs.getString("NOTIFICATION") != null)
				{
					jumlah_notifikasi = rs.getInt("NOTIFICATION");
				}


			}
			return jumlah_notifikasi;
		} finally {
			if (db != null)
				db.close();
		}
	}
			
			

	public Hashtable notifikasi(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread) throws Exception {

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql += " SELECT ( ";
			sql += "  SELECT COUNT(*) FROM TBLESADUAN A,TBLRUJSUMBERESADUAN S,TBLRUJJENISESADUAN J,USERS U,TBLRUJSTATUSESADUAN ST,USERS_INTERNAL UI, "
					+ " TBLRUJSEKSYEN SK,TBLESNOTIFIKASI X,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ,TBLRUJDAERAH D,TBLRUJJENISMODULESADUAN JM "
					+ " WHERE A.ID_SUMBERADUAN = S.ID_SUMBERADUAN(+) "
					+ " AND A.ID_JENISADUAN = J.ID_JENISADUAN(+) "
					+ " AND A.ID_JENISMODULESADUAN = JM.ID_JENISMODULESADUAN(+) "
					+ " AND A.USER_ID = U.USER_ID "
					+ " AND X.ID_ESADUAN = A.ID_ESADUAN "
					+ " AND A.ID_STATUS = ST.ID_STATUSESADUAN(+) "
					+ " AND U.USER_ID = UI.USER_ID "
					+ " AND UI.ID_SEKSYEN = SK.ID_SEKSYEN(+) "
					+ " AND UI.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) "
					+ " AND UI.ID_DAERAH = D.ID_DAERAH(+)"
					+ " AND A.ID_STATUS NOT IN ('16125') AND A.ID_STATUS  IS NOT NULL   ";
			sql += " AND X.ID_ESNOTIFIKASI is not null ";
			if (!id_esaduan.equals("")) {
				sql += " AND X.ID_ESADUAN = '" + id_esaduan + "' ";
			}
			if (!id_negeri_user.equals("") && !role.equals("adminsuper_es")) {
				sql += " AND A.ID_NEGERI_PENGADU = '" + id_negeri_user + "' ";
			}
			if (!user_terima.equals("")) {
				sql += " AND X.ID_USER_NOTIFIKASI_TERIMA = '" + user_terima
						+ "' ";
			}
			if (!flag_notifikasi.equals("")) {
				sql += " AND X.FLAG_NOTIFIKASI = '" + flag_notifikasi + "'";
			}
			if (!notread.equals("")) {
				sql += " AND X.FLAG_READ = '" + notread + "'";
			}
			sql += " ) AS JUMLAH_ADUAN,";

			sql += " (SELECT COUNT(*) as notification FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
					+ " TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
					+ " WHERE"
					+ " D.id_daerah in ( select distinct u.id_daerahurus from"
					+ " TBLRUJPEJABATURUSAN u, users_internal ur "
					+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
					+ user_terima
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_terima+"  ";
					
					sql += " )"
					+ " AND ST.ID_STATUS = S.ID_STATUS(+)"
					+ " AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+)"
					+ " AND A.ID_FAIL = F.ID_FAIL(+)"
					+ " AND A.ID_DAERAHMHN = D.ID_DAERAH"
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
					+ " AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+ " AND P.ID_SIMATI = MS.ID_SIMATI"
					+ " AND A.ID_STATUS <> '999'"
					+ " AND A.SEKSYEN = '8'"
					+ " AND STA.AKTIF = 1"
					+ " AND A.FLAG_JENIS_PERMOHONAN = 0 ) AS ONLINE_SEK8, ";

			sql += " (SELECT COUNT(*) as notification FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
					+ " TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
					+ " WHERE"
					+ " D.id_daerah in ( select distinct u.id_daerahurus from"
					+ " TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
					+ user_terima
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_terima+"  ";
					
					sql += " )"
					+ " AND ST.ID_STATUS = S.ID_STATUS(+)"
					+ " AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+)"
					+ " AND A.ID_FAIL = F.ID_FAIL(+)"
					+ " AND A.ID_DAERAHMHN = D.ID_DAERAH"
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
					+ " AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+ " AND P.ID_SIMATI = MS.ID_SIMATI"
					+ " AND A.ID_STATUS <> '999'"
					+ " AND A.SEKSYEN = '17'"
					+ " AND STA.AKTIF = 1"
					+ " AND A.FLAG_JENIS_PERMOHONAN = 0 ) AS ONLINE_SEK17,"
					+ "";
			/*
			 * sql += " (SELECT COUNT(*) AS TOTAL_FAIL  "+
			 * " FROM TBLPPKPERMOHONAN PPP, TBLPFDFAIL FFF "+
			 * " WHERE PPP.ID_FAIL = FFF.ID_FAIL AND "+
			 * " (SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
			 * +
			 * " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
			 * +
			 * " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
			 * + " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+
			 * user_terima+"') "+
			 * " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
			 * +
			 * " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "
			 * +
			 * " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "
			 * + " AND A.ID_STATUS <> '999' "+
			 * " AND S.ID_STATUS = '14' AND FFF.ID_FAIL = FF.ID_FAIL) = 0 AND "+
			 * " ( "+
			 * " SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
			 * +
			 * " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
			 * +
			 * " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
			 * + " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+
			 * user_terima+"')  "+
			 * " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
			 * +
			 * " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "
			 * +
			 * " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "
			 * + " AND A.ID_STATUS <> '999' "+
			 * " AND S.ID_STATUS = '170' AND F.ID_FAIL = FFF.ID_FAIL "+
			 * " ) > 0 "+ " AND "+
			 * " (TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') -  "+
			 * " (SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
			 * +
			 * " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
			 * +
			 * " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
			 * + " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+
			 * user_terima+"') "+
			 * " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
			 * +
			 * " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "
			 * +
			 * " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "
			 * + " AND A.ID_STATUS <> '999' "+ " AND S.ID_STATUS = '170' "+
			 * " AND  FFF.ID_FAIL = FF.ID_FAIL))>30 ) AS TOTAL_BORANGB, ";
			 */

			sql += " (SELECT  COUNT(DISTINCT PP.ID_PERMOHONAN ) FROM TBLPPKPERBICARAAN PR,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERMOHONAN PP,TBLPPKPERINTAH ORD "
					+ " WHERE PR.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN AND ORD.ID_PERBICARAAN = PR.ID_PERBICARAAN "
					+ " AND KP.ID_PERMOHONAN = PP.ID_PERMOHONAN  "
					+ " AND ORD.CHECK_KIV = '1' "
					+ " AND TO_DATE(ORD.DATE_KIV) < SYSDATE "
					+ " AND PP.ID_DAERAHMHN IN "
					+ " (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, "
					+ " USERS_INTERNAL UR "
					+ " WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"
					+ user_terima + "'";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_terima+"  ";
					
					sql += " )) AS TOTAL_KIV, ";

			sql += " (SELECT COUNT(distinct A.id_permohonan) as notification from TBLPPKPERMOHONAN A, TBLPPKBKE B,  TBLPFDFAIL C, TBLPPKPERMOHONANSIMATI D, TBLPPKSIMATI E, "
					+ "TBLPPKPEMOHON F,  TBLRUJNEGERI G,  TBLRUJDAERAH H,  TBLRUJPEJABATURUSAN I, TBLRUJPEJABATJKPTG J, "
					+ "TBLRUJSUBURUSANSTATUSFAIL M, TBLRUJSUBURUSANSTATUS N, TBLRUJSTATUS O, USERS_INTERNAL P, USERS Q, "
					+ "TBLRUJNEGERI R, TBLRUJDAERAH S "
					+ "WHERE A.ID_PERMOHONAN =  B.ID_PERMOHONAN "
					+ "AND A.ID_FAIL  =  C.ID_FAIL "
					+ "AND D.ID_PERMOHONAN  = A.ID_PERMOHONAN "
					+ "AND D.ID_SIMATI = E.ID_SIMATI "
					+ "AND A.ID_PEMOHON = F.ID_PEMOHON "
					+ "AND B.ID_NEGERI = G.ID_NEGERI "
					+ "AND B.ID_DAERAH = H.ID_DAERAH "
					+
					// "and A.ID_NEGERIMHN = I.ID_NEGERIURUS "+
					// "AND a.ID_DAERAHmhn = I.ID_DAERAHURUS "+
					"AND I.ID_PEJABATJKPTG =  J.ID_PEJABATJKPTG "
					+ "AND M.ID_PERMOHONAN = A.ID_PERMOHONAN "
					+ "AND M.AKTIF = 1 "
					+ "AND M.ID_SUBURUSANSTATUS = N.ID_SUBURUSANSTATUS "
					+ "AND N.ID_STATUS = O.ID_STATUS "
					+ "AND J.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG "
					+ "AND P.USER_ID  =  Q.USER_ID "
					+ "AND A.ID_NEGERIMHN = R.ID_NEGERI "
					+ "AND A.ID_DAERAHMHN = S.ID_DAERAH "
					+ "AND Q.USER_ID = '"
					+ user_terima
					+ "' "
					+ "AND G.ID_NEGERI = P.ID_NEGERI "
					+ "AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL) "
					+ "AND A.ID_STATUS = 56) AS FAIL_PINDAH, ";

			sql += " (SELECT COUNT(*) as notification"
					+ " FROM TBLMAININBOX A,TBLUSERSINBOX B,TBLINBOXNOTIFIKASI C "
					+ " WHERE A.ID_MAININBOX = B.ID_MAININBOX AND C.FLAG_READ = 'NO' AND A.FLAG_AKTIF = 'Y' AND A.ID_MAININBOX = C.ID_MAININBOX "
					+ " AND B.USER_ID = '"
					+ user_terima
					+ "' AND C.ID_USER_NOTIFIKASI_TERIMA = B.USER_ID) AS INBOX, ";

			sql += "(SELECT COUNT(distinct E.ID_EDITNOTIFIKASI) as notification FROM TBLEDITNOTIFIKASI E,TBLPFDFAIL F,"
					+ " TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PS,TBLPPKSIMATI S,TBLPPKPEMOHON PM,TBLRUJSTATUS ST "
					+ " WHERE E.FLAG_READ = 'NO' AND P.ID_STATUS = ST.ID_STATUS(+) "
					+ " AND P.ID_PEMOHON = PM.ID_PEMOHON AND S.ID_SIMATI = PS.ID_SIMATI "
					+ " AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND E.ID_FAIL = F.ID_FAIL "
					+ " AND E.ID_USER_NOTIFIKASI_TERIMA = '"
					+ user_terima
					+ "') AS KEBENARAN_EDIT ";

			sql += " FROM DUAL";

			myLogger.info("--------------- LIST NOTIFICATION DASHBOARD LIST:"
					+ sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				h.put("JUMLAH_ADUAN", rs.getString("JUMLAH_ADUAN") == null ? ""
						: rs.getInt("JUMLAH_ADUAN"));
				h.put("ONLINE_SEK8", rs.getString("ONLINE_SEK8") == null ? ""
						: rs.getInt("ONLINE_SEK8"));
				h.put("ONLINE_SEK17", rs.getString("ONLINE_SEK17") == null ? ""
						: rs.getInt("ONLINE_SEK17"));
				// h.put("TOTAL_BORANGB",
				// rs.getString("TOTAL_BORANGB")==null?"":rs.getInt("TOTAL_BORANGB"));
				h.put("TOTAL_BORANGB", 0);
				h.put("FAIL_PINDAH", rs.getString("FAIL_PINDAH") == null ? ""
						: rs.getInt("FAIL_PINDAH"));
				h.put("INBOX",
						rs.getString("INBOX") == null ? "" : rs.getInt("INBOX"));
				h.put("TOTAL_KIV",
						rs.getString("TOTAL_KIV") == null ? "" : rs
								.getInt("TOTAL_KIV"));
				h.put("KEBENARAN_EDIT",
						rs.getString("KEBENARAN_EDIT") == null ? "" : rs
								.getInt("KEBENARAN_EDIT"));

			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
		/*
		 * QUERY UNTUK Jumlah fail dimana maklumat serahan notis yang tidak
		 * dikemaskini sehingga tarikh bicara
		 * 
		 * SELECT * FROM TBLPPKPERBICARAAN PR,TBLPPKKEPUTUSANPERMOHONAN
		 * KP,TBLPPKPERMOHONAN PP WHERE PR.ID_KEPUTUSANPERMOHONAN =
		 * KP.ID_KEPUTUSANPERMOHONAN AND KP.ID_PERMOHONAN = PP.ID_PERMOHONAN AND
		 * PR.BIL_BICARA IN ( SELECT MAX(PR.BIL_BICARA) FROM TBLPPKPERBICARAAN
		 * PR,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERMOHONAN P WHERE
		 * PR.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN AND
		 * KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_PERMOHONAN =
		 * PP.ID_PERMOHONAN AND P.ID_DAERAHMHN IN (SELECT DISTINCT
		 * U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE
		 * U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '293') ) AND
		 * PP.ID_DAERAHMHN IN (SELECT DISTINCT U.ID_DAERAHURUS FROM
		 * TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG =
		 * UR.ID_PEJABATJKPTG AND UR.USER_ID = '293') AND
		 * TO_DATE(PR.TARIKH_NOTIS) < SYSDATE AND TO_DATE(PR.TARIKH_BICARA) <
		 * SYSDATE AND (SELECT COUNT(*) FROM TBLPPKNOTISPERBICARAAN
		 * NP,TBLPPKNOTISOBMST MST, TBLPPKNOTISOBDTL DTL,TBLPPKPERBICARAAN PRR,
		 * TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERMOHONAN P WHERE
		 * MST.ID_NOTISOBMST = DTL.ID_NOTISOBMST AND NP.ID_NOTISOBMST =
		 * MST.ID_NOTISOBMST AND PRR.ID_PERBICARAAN = NP.ID_PERBICARAAN AND
		 * PRR.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN AND
		 * KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_PERMOHONAN =
		 * PP.ID_PERMOHONAN AND P.ID_DAERAHMHN IN (SELECT DISTINCT
		 * U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE
		 * U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '293') ) > 0
		 * -- KENA COMPARE NGN OB
		 */
	}

	public String getCheckNotifikasiInbox(String user_id) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			String sql = "SELECT COUNT(*) as cnt"
					+ " FROM TBLMAININBOX A,TBLUSERSINBOX B,TBLINBOXNOTIFIKASI C "
					+ " WHERE A.ID_MAININBOX = B.ID_MAININBOX AND C.FLAG_READ = 'NO' AND A.FLAG_AKTIF = 'Y' AND A.ID_MAININBOX = C.ID_MAININBOX "
					+ " AND B.USER_ID = '"
					+ user_id
					+ "' AND C.ID_USER_NOTIFIKASI_TERIMA = B.USER_ID";
			myLogger.info(" getCheckNotifikasiOnline8 :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			String cnt = "";
			while (rs.next()) {
				cnt = rs.getString("cnt");
			}

			return cnt;
		} finally {
			if (db != null)
				db.close();
		}
	}
	public String getCheckNotifikasiOnline8(String user_id) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			String sql = "SELECT COUNT(*) as cnt FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
					+ " TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
					+ " WHERE"
					+ " D.id_daerah in ( select distinct u.id_daerahurus from"
					+ " TBLRUJPEJABATURUSAN u, users_internal ur "
					+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='" + user_id + "')"
					+ " AND ST.ID_STATUS = S.ID_STATUS(+)"
					+ " AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+)"
					+ " AND A.ID_FAIL = F.ID_FAIL(+)"
					+ " AND A.ID_DAERAHMHN = D.ID_DAERAH"
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
					+ " AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+ " AND P.ID_SIMATI = MS.ID_SIMATI"
					+ " AND A.ID_STATUS <> '999'"
					+ " AND A.SEKSYEN = '8'"
					+ " AND STA.AKTIF = 1"
					+ " AND A.FLAG_JENIS_PERMOHONAN = 0";
			myLogger.info(" getCheckNotifikasiOnline8 :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			String cnt = "";
			while (rs.next()) {
				cnt = rs.getString("cnt");
			}

			return cnt;
		} finally {
			if (db != null)
				db.close();
		}
	}
	public String getCheckNotifikasiOnline17(String user_id) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			String sql = "SELECT COUNT(*) as cnt FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
					+ " TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
					+ " WHERE"
					+ " D.id_daerah in ( select distinct u.id_daerahurus from"
					+ " TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='" + user_id + "')"
					+ " AND ST.ID_STATUS = S.ID_STATUS(+)"
					+ " AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+)"
					+ " AND A.ID_FAIL = F.ID_FAIL(+)"
					+ " AND A.ID_DAERAHMHN = D.ID_DAERAH"
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
					+ " AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+ " AND P.ID_SIMATI = MS.ID_SIMATI"
					+ " AND A.ID_STATUS <> '999'"
					+ " AND A.SEKSYEN = '17'"
					+ " AND STA.AKTIF = 1"
					+ " AND A.FLAG_JENIS_PERMOHONAN = 0";
			myLogger.info(" getCheckNotifikasiOnline17 :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			String cnt = "";
			while (rs.next()) {
				cnt = rs.getString("cnt");
			}

			return cnt;
		} finally {
			if (db != null)
				db.close();
		}
	}
	public String getCheckNotifikasiEdit(String user_id) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String kod_negeri = (String) kod_negeri().get("KOD_NEGERI");
			
			String sql = "SELECT COUNT(distinct E.ID_EDITNOTIFIKASI) as cnt FROM TBLEDITNOTIFIKASI E,TBLPFDFAIL F,"
					+ " TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PS,TBLPPKSIMATI S,TBLPPKPEMOHON PM,TBLRUJSTATUS ST "
					+ " WHERE E.FLAG_READ = 'NO' AND P.ID_STATUS = ST.ID_STATUS(+) "
					+ " AND P.ID_PEMOHON = PM.ID_PEMOHON AND S.ID_SIMATI = PS.ID_SIMATI "
					+ " AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND E.ID_FAIL = F.ID_FAIL "
					+ " AND E.ID_USER_NOTIFIKASI_TERIMA = '" + user_id + "'";
			myLogger.info(" getCheckNotifikasiEdit :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			String cnt = "";
			while (rs.next()) {
				cnt = rs.getString("cnt");
			}

			return cnt;
		} finally {
			if (db != null)
				db.close();
		}
	}
	public String getCheckNotifikasiPindah(String user_id) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			String sql = "SELECT COUNT(distinct A.id_permohonan) as cnt from TBLPPKPERMOHONAN A, TBLPPKBKE B,  TBLPFDFAIL C, TBLPPKPERMOHONANSIMATI D, TBLPPKSIMATI E, "
					+ "TBLPPKPEMOHON F,  TBLRUJNEGERI G,  TBLRUJDAERAH H,  TBLRUJPEJABATURUSAN I, TBLRUJPEJABATJKPTG J, "
					+ "TBLRUJSUBURUSANSTATUSFAIL M, TBLRUJSUBURUSANSTATUS N, TBLRUJSTATUS O, USERS_INTERNAL P, USERS Q, "
					+ "TBLRUJNEGERI R, TBLRUJDAERAH S "
					+ "WHERE A.ID_PERMOHONAN =  B.ID_PERMOHONAN "
					+ "AND A.ID_FAIL  =  C.ID_FAIL "
					+ "AND D.ID_PERMOHONAN  = A.ID_PERMOHONAN "
					+ "AND D.ID_SIMATI = E.ID_SIMATI "
					+ "AND A.ID_PEMOHON = F.ID_PEMOHON "
					+ "AND B.ID_NEGERI = G.ID_NEGERI "
					+ "AND B.ID_DAERAH = H.ID_DAERAH "
					+
					// "and A.ID_NEGERIMHN = I.ID_NEGERIURUS "+
					// "AND a.ID_DAERAHmhn = I.ID_DAERAHURUS "+
					"AND I.ID_PEJABATJKPTG =  J.ID_PEJABATJKPTG "
					+ "AND M.ID_PERMOHONAN = A.ID_PERMOHONAN "
					+ "AND M.AKTIF = 1 "
					+ "AND M.ID_SUBURUSANSTATUS = N.ID_SUBURUSANSTATUS "
					+ "AND N.ID_STATUS = O.ID_STATUS "
					+ "AND J.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG "
					+ "AND P.USER_ID  =  Q.USER_ID "
					+ "AND A.ID_NEGERIMHN = R.ID_NEGERI "
					+ "AND A.ID_DAERAHMHN = S.ID_DAERAH "
					+ "AND Q.USER_ID = '" + user_id + "' "
					+ "AND G.ID_NEGERI = P.ID_NEGERI "
					+ "AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL) "
					+ "AND A.ID_STATUS = 56";
			myLogger.info(" getCheckNotifikasiPindah :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			String cnt = "";
			while (rs.next()) {
				cnt = rs.getString("cnt");
			}

			return cnt;
		} finally {
			if (db != null)
				db.close();
		}
	}
	public String getCheckNotifikasiAduan(String role, String id_negeri_user, String user_id) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String kod_negeri = (String) kod_negeri().get("KOD_NEGERI");
			
			String sql = "SELECT COUNT(*) cnt FROM TBLESADUAN A,TBLRUJSUMBERESADUAN S,TBLRUJJENISESADUAN J,USERS U,TBLRUJSTATUSESADUAN ST,USERS_INTERNAL UI, "
					+ " TBLRUJSEKSYEN SK,TBLESNOTIFIKASI X,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ,TBLRUJDAERAH D,TBLRUJJENISMODULESADUAN JM "
					+ " WHERE A.ID_SUMBERADUAN = S.ID_SUMBERADUAN(+) "
					+ " AND A.ID_JENISADUAN = J.ID_JENISADUAN(+) "
					+ " AND A.ID_JENISMODULESADUAN = JM.ID_JENISMODULESADUAN(+) "
					+ " AND A.USER_ID = U.USER_ID "
					+ " AND X.ID_ESADUAN = A.ID_ESADUAN "
					+ " AND A.ID_STATUS = ST.ID_STATUSESADUAN(+) "
					+ " AND U.USER_ID = UI.USER_ID "
					+ " AND UI.ID_SEKSYEN = SK.ID_SEKSYEN(+) "
					+ " AND UI.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) "
					+ " AND UI.ID_DAERAH = D.ID_DAERAH(+)"
					+ " AND A.ID_STATUS NOT IN ('16125') AND A.ID_STATUS  IS NOT NULL   ";
			sql += " AND X.ID_ESNOTIFIKASI is not null ";
			if (!id_negeri_user.equals("") && !role.equals("adminsuper_es")) {
				sql += " AND A.ID_NEGERI_PENGADU = '" + id_negeri_user + "' ";
			}
			if (!user_id.equals("")) {
				sql += " AND X.ID_USER_NOTIFIKASI_TERIMA = '" + user_id + "' ";
			}
			myLogger.info(" getCheckNotifikasiAduan :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			String cnt = "";
			while (rs.next()) {
				cnt = rs.getString("cnt");
			}

			return cnt;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Hashtable notifikasi_KIV(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread) throws Exception {

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql += " SELECT  COUNT(DISTINCT PP.ID_PERMOHONAN) AS TOTAL_KIV FROM TBLPPKPERBICARAAN PR,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERMOHONAN PP,TBLPPKPERINTAH ORD "
					+ " WHERE PR.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN AND ORD.ID_PERBICARAAN = PR.ID_PERBICARAAN "
					+ " AND KP.ID_PERMOHONAN = PP.ID_PERMOHONAN  "
					+ " AND ORD.CHECK_KIV = '1' "
					+ " AND TO_DATE(ORD.DATE_KIV) < SYSDATE "
					+ " AND PP.ID_DAERAHMHN IN "
					+ " (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, "
					+ " USERS_INTERNAL UR "
					+ " WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"
					+ user_terima + "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_terima+"  ";
					
					sql += " ) "
					+" ";

		

			myLogger.info("--------------- LIST NOTIFICATION DASHBOARD LIST KIV:"
					+ sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();

				h.put("TOTAL_KIV",
						rs.getString("TOTAL_KIV") == null ? "" : rs
								.getInt("TOTAL_KIV"));

			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Integer count_aduan = null;

	public Integer getListNotifikasi_main_list(String role,
			String id_negeri_user, String id_esaduan, String flag_notifikasi,
			String user_terima, String notread) throws Exception {

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT COUNT(*) as notification FROM TBLESADUAN A,TBLRUJSUMBERESADUAN S,TBLRUJJENISESADUAN J,USERS U,TBLRUJSTATUSESADUAN ST,USERS_INTERNAL UI, "
					+ " TBLRUJSEKSYEN SK,TBLESNOTIFIKASI X,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ,TBLRUJDAERAH D,TBLRUJJENISMODULESADUAN JM "
					+ " WHERE A.ID_SUMBERADUAN = S.ID_SUMBERADUAN(+) "
					+ " AND A.ID_JENISADUAN = J.ID_JENISADUAN(+) "
					+ " AND A.ID_JENISMODULESADUAN = JM.ID_JENISMODULESADUAN(+) "
					+ " AND A.USER_ID = U.USER_ID "
					+ " AND X.ID_ESADUAN = A.ID_ESADUAN "
					+ " AND A.ID_STATUS = ST.ID_STATUSESADUAN(+) "
					+ " AND U.USER_ID = UI.USER_ID "
					+ " AND UI.ID_SEKSYEN = SK.ID_SEKSYEN(+) "
					+ " AND UI.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) "
					+ " AND UI.ID_DAERAH = D.ID_DAERAH(+)"
					+ " AND A.ID_STATUS NOT IN ('16125') AND A.ID_STATUS  IS NOT NULL   ";

			sql += " AND X.ID_ESNOTIFIKASI is not null ";
			if (!id_esaduan.equals("")) {
				sql += " AND X.ID_ESADUAN = '" + id_esaduan + "' ";
			}
			if (!id_negeri_user.equals("") && !role.equals("adminsuper_es")) {
				sql += " AND A.ID_NEGERI_PENGADU = '" + id_negeri_user + "' ";
			}
			if (!user_terima.equals("")) {
				sql += " AND X.ID_USER_NOTIFIKASI_TERIMA = '" + user_terima
						+ "' ";
			}
			if (!flag_notifikasi.equals("")) {
				sql += " AND X.FLAG_NOTIFIKASI = '" + flag_notifikasi + "'";
			}
			if (!notread.equals("")) {
				sql += " AND X.FLAG_READ = '" + notread + "'";
			}

			// myLogger.info("LIST NOTIFICATION DASHBOARD LIST"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				count_aduan = rs.getInt("notification");

			}
			return count_aduan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Integer count_8 = null;

	public Integer getListNotifikasi_online8(String userid, String seksyen)
			throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT COUNT(*) as notification FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
					+ " TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
					+ " WHERE"
					+ " D.id_daerah in ( select distinct u.id_daerahurus from"
					+ " TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
					+ userid
					+ "' ";
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
					sql += " )"
					+ " AND ST.ID_STATUS = S.ID_STATUS(+)"
					+ " AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+)"
					+ " AND A.ID_FAIL = F.ID_FAIL(+)"
					+ " AND A.ID_DAERAHMHN = D.ID_DAERAH"
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
					+ " AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+ " AND P.ID_SIMATI = MS.ID_SIMATI"
					+ " AND A.ID_STATUS <> '999'"
					+ " AND A.SEKSYEN = '"
					+ seksyen
					+ "'"
					+ " AND STA.AKTIF = 1"
					+ " AND A.FLAG_JENIS_PERMOHONAN = 0"
					+ " ORDER BY STA.ID_SUBURUSANSTATUSFAIL" + "";

			myLogger.info("LIST NOTIFICATION ONLINE 8 DASHBOARD LIST"
					+ sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				count_8 = rs.getInt("notification");

			}
			return count_8;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Integer count_pindah = null;

	public Integer getListNotifikasi_pindah(String ekptg_user_id)
			throws Exception {
		Db db = null;

		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT COUNT(distinct A.id_permohonan) as notification from TBLPPKPERMOHONAN A, TBLPPKBKE B,  TBLPFDFAIL C, TBLPPKPERMOHONANSIMATI D, TBLPPKSIMATI E, "
					+ "TBLPPKPEMOHON F,  TBLRUJNEGERI G,  TBLRUJDAERAH H,  TBLRUJPEJABATURUSAN I, TBLRUJPEJABATJKPTG J, "
					+ "TBLRUJSUBURUSANSTATUSFAIL M, TBLRUJSUBURUSANSTATUS N, TBLRUJSTATUS O, USERS_INTERNAL P, USERS Q, "
					+ "TBLRUJNEGERI R, TBLRUJDAERAH S "
					+ "WHERE A.ID_PERMOHONAN =  B.ID_PERMOHONAN "
					+ "AND A.ID_FAIL  =  C.ID_FAIL "
					+ "AND D.ID_PERMOHONAN  = A.ID_PERMOHONAN "
					+ "AND D.ID_SIMATI = E.ID_SIMATI "
					+ "AND A.ID_PEMOHON = F.ID_PEMOHON "
					+ "AND B.ID_NEGERI = G.ID_NEGERI "
					+ "AND B.ID_DAERAH = H.ID_DAERAH "
					+
					// "and A.ID_NEGERIMHN = I.ID_NEGERIURUS "+
					// "AND a.ID_DAERAHmhn = I.ID_DAERAHURUS "+
					"AND I.ID_PEJABATJKPTG =  J.ID_PEJABATJKPTG "
					+ "AND M.ID_PERMOHONAN = A.ID_PERMOHONAN "
					+ "AND M.AKTIF = 1 "
					+ "AND M.ID_SUBURUSANSTATUS = N.ID_SUBURUSANSTATUS "
					+ "AND N.ID_STATUS = O.ID_STATUS "
					+ "AND J.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG "
					+ "AND P.USER_ID  =  Q.USER_ID "
					+ "AND A.ID_NEGERIMHN = R.ID_NEGERI "
					+ "AND A.ID_DAERAHMHN = S.ID_DAERAH "
					+ "AND Q.USER_ID = '"
					+ ekptg_user_id
					+ "' "
					+ "AND G.ID_NEGERI = P.ID_NEGERI "
					+ "AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL) "
					+ "AND A.ID_STATUS = 56 ";

			// myLogger.info("LIST NOTIFICATION PINDAH DASHBOARD LIST"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				count_pindah = rs.getInt("notification");

			}
			return count_pindah;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Integer count_inbox = null;

	public Integer getListNotifikasi_inbox(String userId) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = ""
					+ " SELECT COUNT(*) as notification"
					+ " FROM TBLMAININBOX A,TBLUSERSINBOX B,TBLINBOXNOTIFIKASI C "
					+ " WHERE A.ID_MAININBOX = B.ID_MAININBOX AND C.FLAG_READ = 'NO' AND A.FLAG_AKTIF = 'Y' AND A.ID_MAININBOX = C.ID_MAININBOX "
					+ " AND B.USER_ID = '" + userId
					+ "' AND C.ID_USER_NOTIFIKASI_TERIMA = B.USER_ID ";
			sql += "  ";
			myLogger.info("SQL COUNT LIST MAIN INBOX XX :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count_inbox = rs.getInt("notification");
			}
			return count_inbox;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Integer count_borangb = null;

	public Integer getListCountBorangB(String userId) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT COUNT(*) AS TOTAL_FAIL  "
					+ " FROM TBLPPKPERMOHONAN PPP, TBLPFDFAIL FFF "
					+ " WHERE PPP.ID_FAIL = FFF.ID_FAIL AND "
					+ " (SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ userId
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
					
					sql += " ) "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "
					+ " AND A.ID_STATUS <> '999' "
					+ " AND S.ID_STATUS = '14' AND FFF.ID_FAIL = FF.ID_FAIL) = 0 AND "
					+ " ( "
					+ " SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ userId
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
					
					sql += " )  "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "
					+ " AND A.ID_STATUS <> '999' "
					+ " AND S.ID_STATUS = '170' AND F.ID_FAIL = FFF.ID_FAIL "
					+ " ) > 0 "
					+ " AND "
					+ " (TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') -  "
					+ " (SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ userId
					+ "' ";
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
					sql += " ) "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "
					+ " AND A.ID_STATUS <> '999' "
					+ " AND S.ID_STATUS = '170' "
					+ " AND  FFF.ID_FAIL = FF.ID_FAIL))>30";

			myLogger.info("SQL COUNT BORANG B :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count_borangb = rs.getInt("TOTAL_FAIL");
			}
			return count_borangb;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector getListBorangB = new Vector();

	public Vector getListBorangB(String userId) throws Exception {
		Db db = null;
		getListBorangB.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT PPP.SEKSYEN,SSM.ID_SIMATI,PPP.ID_PERMOHONAN,FFF.ID_FAIL,FFF.NO_FAIL,TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') AS CURRENT_DATE, "
					+ " (SELECT TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ userId
					+ "' "; 
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
					
					sql += " ) "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "
					+ " AND A.ID_STATUS <> '999' "
					+ " AND S.ID_STATUS = '170' "
					+ " AND  FFF.ID_FAIL = FF.ID_FAIL"
					+ " AND STA.TARIKH_MASUK = ( "
					+ " SELECT MAX(STA.TARIKH_MASUK) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ userId
					+ "' ";
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
					
					sql += " ) "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH"
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI"
					+ " AND A.ID_STATUS <> '999' "
					+ " AND S.ID_STATUS = '170' AND F.ID_FAIL = FF.ID_FAIL"
					+ " )) AS TARIKH_BORANGB,"
					+ " (TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') -"
					+ " (SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ userId
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
					
					sql += " ) "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH"
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI"
					+ " AND A.ID_STATUS <> '999'"
					+ " AND S.ID_STATUS = '170' "
					+ " AND  FFF.ID_FAIL = FF.ID_FAIL)) AS DIFF"
					+ " FROM TBLPPKPERMOHONAN PPP, TBLPFDFAIL FFF,TBLPPKPERMOHONANSIMATI SSM"
					+ " WHERE PPP.ID_FAIL = FFF.ID_FAIL AND PPP.ID_PERMOHONAN = SSM.ID_PERMOHONAN AND"
					+ " (SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ userId
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
					
					sql += " ) "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH"
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI"
					+ " AND A.ID_STATUS <> '999' "
					+ " AND S.ID_STATUS = '14' AND FFF.ID_FAIL = FF.ID_FAIL) = 0 AND"
					+ " ("
					+ " SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ userId
					+ "'";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
					
					sql += " ) "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH"
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI"
					+ " AND A.ID_STATUS <> '999' "
					+ " AND S.ID_STATUS = '170' AND F.ID_FAIL = FFF.ID_FAIL"
					+ " ) > 0"
					+ " AND"
					+ " (TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') -"
					+ " (SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ userId
					+ "') "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "
					+ " AND A.ID_STATUS <> '999' "
					+ " AND S.ID_STATUS = '170' "
					+ " AND  FFF.ID_FAIL = FF.ID_FAIL))>30 AND ROWNUM < 50";

			myLogger.info("GET LIST FAIL BORANG B" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_SIMATI",
						rs.getString("ID_SIMATI") == null ? "" : rs
								.getString("ID_SIMATI"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("NO_FAIL",
						rs.getString("NO_FAIL") == null ? "" : rs
								.getString("NO_FAIL"));
				h.put("SEKSYEN",
						rs.getString("SEKSYEN") == null ? "" : rs
								.getString("SEKSYEN"));

				getListBorangB.addElement(h);
			}
			return getListBorangB;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	

		
				
				//aishahlatip 12052017
				public Integer notifikasi_FailNotisPerbicaraan(String role, String id_negeri_user,
						String id_esaduan, String flag_notifikasi, String userId,
						String notread) throws Exception {

					Db db = null;
					String sql = "";
					Integer jumlah_notifikasi = 0;
					try {
						db = new Db();
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						sql = "SELECT COUNT(*) AS NOTIFICATION FROM ( " ;
						sql += " SELECT DISTINCT F.NO_FAIL, SM.NAMA_SIMATI, S.KETERANGAN, " +
				" MAX(TO_CHAR(B.TARIKH_NOTIS,'DD/MM/YYYY')) AS TARIKH_NOTIS, MAX(B.BIL_BICARA) AS BIL_BICARA, COUNT(B.ID_PERBICARAAN) AS C_ID_PERBICARAAN ," +
				" P.ID_PERMOHONAN, P.ID_STATUS, sm.ID_SIMATI, p.SEKSYEN " +
				" FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKPERBICARAAN B, TBLPPKSIMATI SM, " +
				" TBLPPKPERMOHONANSIMATI PSM, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S " +
				" WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN " +
				" AND KP.ID_KEPUTUSANPERMOHONAN = B.ID_KEPUTUSANPERMOHONAN " +
				" AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND PSM.ID_SIMATI = SM.ID_SIMATI " +
				" AND P.ID_STATUS IN (18) AND P.ID_STATUS = S.ID_STATUS ";
								
						if(role.equals("user_ppk"))
						{
							//sql += " AND PM.USER_ID = '"+USER_ID_SYSTEM+"' ";
							sql += "AND  p.id_daerahmhn IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR " +
							" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
							+ userId
							+ "' ";
							
							 sql += " UNION "+                                                                            
							" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
							" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
							" WHERE ID_STATUS = 2  "+ 
							" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
							" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
							" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
							" AND PBU.ID_PEMOHON = '"+userId+"'  )";
						}
						else if(role.equals("adminppk"))
						{
							sql += "AND  f.ID_NEGERI in( SELECT DISTINCT u.ID_NEGERI " +
									" FROM tblrujpejabaturusan u, users_internal ur " +
									" WHERE u.id_pejabatjkptg = ur.id_pejabatjkptg " +
									" AND ur.user_id = '"+userId+"') ";
						}
						sql += " GROUP BY F.NO_FAIL, SM.NAMA_SIMATI, S.KETERANGAN, P.ID_PERMOHONAN, P.ID_STATUS, sm.ID_SIMATI, p.SEKSYEN ";
						sql += " ORDER BY P.ID_PERMOHONAN desc, TARIKH_NOTIS desc ";
						sql += " ) NOTIFICATION ";		
						
						
								
							myLogger.info("notifikasi_FailNotisPerbicaraan : "+sql);
							//System.out.println("notifikasi_FailNotisPerbicaraan : "+sql);
						ResultSet rs = stmt.executeQuery(sql);
						Hashtable h = null;

						while (rs.next()) {
							h = new Hashtable();
							
							if(rs.getString("NOTIFICATION") != null)
							{
								jumlah_notifikasi = rs.getInt("NOTIFICATION");
							}


						}
						return jumlah_notifikasi;
					} finally {
						if (db != null)
							db.close();
					}
				}

}
	
	

// SELECT FFF.NO_FAIL,TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') AS
// CURRENT_DATE,
// (SELECT TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY') FROM
// TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P,
// TBLPPKPEMOHON PM,
// TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL
// STA, TBLRUJDAERAH D
// WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM
// TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR
// WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"')
// AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS =
// ST.ID_SUBURUSANSTATUS(+)
// AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND
// A.ID_DAERAHMHN = D.ID_DAERAH
// AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN =
// STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI
// AND A.ID_STATUS <> '999'
// AND S.ID_STATUS = '170'
// AND FFF.ID_FAIL = FF.ID_FAIL
// AND STA.TARIKH_MASUK = (
// SELECT MAX(STA.TARIKH_MASUK) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F,
// TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,
// TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL
// STA, TBLRUJDAERAH D
// WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM
// TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR
// WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"')
// AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS =
// ST.ID_SUBURUSANSTATUS(+)
// AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND
// A.ID_DAERAHMHN = D.ID_DAERAH
// AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN =
// STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI
// AND A.ID_STATUS <> '999'
// AND S.ID_STATUS = '170' AND F.ID_FAIL = FF.ID_FAIL
// )) AS TARIKH_BORANGB,
// (TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') -
// (SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY'))
// FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P,
// TBLPPKPEMOHON PM,
// TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL
// STA, TBLRUJDAERAH D
// WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM
// TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR
// WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"')
// AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS =
// ST.ID_SUBURUSANSTATUS(+)
// AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND
// A.ID_DAERAHMHN = D.ID_DAERAH
// AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN =
// STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI
// AND A.ID_STATUS <> '999'
// AND S.ID_STATUS = '170'
// AND FFF.ID_FAIL = FF.ID_FAIL)) AS DIFF
// FROM TBLPPKPERMOHONAN PPP, TBLPFDFAIL FFF
// WHERE PPP.ID_FAIL = FFF.ID_FAIL AND
// (SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S,
// TBLPPKSIMATI P, TBLPPKPEMOHON PM,
// TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL
// STA, TBLRUJDAERAH D
// WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM
// TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR
// WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"')
// AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS =
// ST.ID_SUBURUSANSTATUS(+)
// AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND
// A.ID_DAERAHMHN = D.ID_DAERAH
// AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN =
// STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI
// AND A.ID_STATUS <> '999'
// AND S.ID_STATUS = '14' AND FFF.ID_FAIL = FF.ID_FAIL) = 0 AND
// (
// SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S,
// TBLPPKSIMATI P, TBLPPKPEMOHON PM,
// TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL
// STA, TBLRUJDAERAH D
// WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM
// TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR
// WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"')
// AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS =
// ST.ID_SUBURUSANSTATUS(+)
// AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND
// A.ID_DAERAHMHN = D.ID_DAERAH
// AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN =
// STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI
// AND A.ID_STATUS <> '999'
// AND S.ID_STATUS = '170' AND F.ID_FAIL = FFF.ID_FAIL
// ) > 0
// AND
// (TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') -
// (SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY'))
// FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P,
// TBLPPKPEMOHON PM,
// TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL
// STA, TBLRUJDAERAH D
// WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM
// TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR
// WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"')
// AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS =
// ST.ID_SUBURUSANSTATUS(+)
// AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND
// A.ID_DAERAHMHN = D.ID_DAERAH
// AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN =
// STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI
// AND A.ID_STATUS <> '999'
// AND S.ID_STATUS = '170'
// AND FFF.ID_FAIL = FF.ID_FAIL))>30

