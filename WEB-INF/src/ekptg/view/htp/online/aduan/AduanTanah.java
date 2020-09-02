package ekptg.view.htp.online.aduan;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.portal.action.AjaxModule;
import lebah.template.DbPersistence;
import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.esaduan.EtappSupportAduanData;
import ekptg.model.online.aduan.Complaint;
import ekptg.model.online.aduan.ComplaintResponseBean;
import ekptg.model.online.aduan.EkptgProcessComplainHandler;
import ekptg.model.online.aduan.IComplaintResponseBean;
import ekptg.model.online.aduan.IEkptgManageComplaintHandler;
import ekptg.model.online.aduan.ResponseStatus;
import ekptg.model.online.aduan.entity.ComplaintResponse;
import etapp.data.OnlineEAduanMobile;
import etapp.data.OnlineLampiranEAduanMobile;
import etapp.data.RujJenisAduanMobile;
import etapp.data.RujSumberAduanMobile;
import javax.servlet.http.HttpSession;

public class AduanTanah extends AjaxModule {
	/**
	 *
	 */
	private static final long serialVersionUID = -2190442166765550249L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.online.aduan.AduanTanah.class);
	//DbPersistence db = new DbPersistence();
	HttpSession session = null;

	private final String PATH="app/htp/online/aduantanah/proses/";
	private String vm = PATH +"index.jsp";
	String userId = null;
	private IEkptgManageComplaintHandler handler;
	private IComplaintResponseBean responseBean;
	EtappSupportAduanData logic = new EtappSupportAduanData();
	Map view = null;
	List listAduan = null;
	List aduanDetails = null;
	Vector list_aduan = null;

	@Override
	public String doAction() throws Exception {
		HttpSession session = request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		userId = (String)session.getAttribute("_ekptg_user_id");
		String command = getParam("command");
		String hitButton = getParam("hitButton");

		String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		context.put("selectedTabUpper", selectedTabUpper);

		String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0) {
			idJenisTanah = "99999";
		}
		String idJenisHakmilik = getParam("socJenisHakmilik");
		if (idJenisHakmilik == null || idJenisHakmilik.trim().length() == 0) {
			idJenisHakmilik = "99999";
		}
		String idJenisLot = getParam("socJenisLot");
		if (idJenisLot == null || idJenisLot.trim().length() == 0) {
			idJenisLot = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idDaerahTanah = getParam("socDaerahTanah");
		if (idDaerahTanah == null || idDaerahTanah.trim().length() == 0) {
			idDaerahTanah = "99999";
		}
		String idMukimTanah = getParam("socMukimTanah");
		if (idMukimTanah == null || idMukimTanah.trim().length() == 0) {
			idMukimTanah = "99999";
		}


		String idAduan = getParam("idAduan");
		String no_fail = getParam("no_fail");
		String txtPeganganHakmilik = getParam("txtPeganganHakmilik");
		String txtNoHakmilikTanah = getParam("txtNoHakmilikTanah");
		String noWarta = getParam("noWarta");
		String tarikhWarta = getParam("tarikhWarta");
		String txtNoLot = getParam("txtNoLot");
		String txtLuas = getParam("txtLuas");

		Vector list_users = null;
		Vector list_aduan = null;
		context.put("list_users", "");

		// HITBUTTON
				if (postDB) {
					if ("daftarBaru".equals(hitButton)) {
						/*idAduan = logic.daftarBaru(idJenisTanah,
								getParam("tarikhTerima"), getParam("tarikhSurat"),
								getParam("txtNoRujukanSurat"), getParam("txtPerkara"),
								getParam("txtPeganganHakmilik"), session);*/
					}
				}

		if(command.equals("viewComplaint")){
			viewComplaint();
			getSeksyenList();
			vm = PATH+"view.jsp";
		}

		else if (command.equals("daftarBaru")) {
			vm = PATH + "editor.jsp";
			Db db = null;
			try {
				db = new Db();
				String user_id = getParam("userid");
				context.put("txtPeganganHakmilik",txtPeganganHakmilik);
				context.put("txtNoHakmilikTanah",txtNoHakmilikTanah);
				context.put("noWarta",noWarta);
				context.put("tarikhWarta",tarikhWarta);
				context.put("idJenisLot",idJenisLot);
				context.put("txtNoLot",txtNoLot);
				context.put("txtLuas",txtLuas);
				context.put("idJenisTanah",idJenisTanah);
				context.put("idJenisHakmilik",idJenisHakmilik);
				context.put("idNegeri",idNegeri);
				context.put("idDaerah",idDaerahTanah);
				context.put("idMukim",idMukimTanah);
				context.put("no_fail",no_fail);

				if (!userId.equals("")) {
					list_users = logic.getListUsers(userId, "", "", "", "",
							"", db);
					if (list_users.size() != 0) {
						Hashtable get_user = (Hashtable) list_users.get(0);
						context.put("user_id", (String) get_user.get("user_id"));
						context.put("nama_pengadu",
								(String) get_user.get("user_name"));
						context.put("no_tel", (String) get_user.get("user_id"));
						context.put("seksyen",
								(String) get_user.get("nama_seksyen"));
						context.put("pejabat",
								(String) get_user.get("nama_pejabat"));
						context.put("negeri",
								(String) get_user.get("nama_negeri"));
						context.put("daerah",
								(String) get_user.get("nama_daerah"));
						context.put("emel", (String) get_user.get("emel"));
						context.put("nama_jawatan",
								(String) get_user.get("nama_jawatan"));
						context.put("id_seksyen_user",
								(String) get_user.get("id_seksyen"));
						context.put("id_negeri_pengadu",
								(String) get_user.get("id_negeri_pengadu"));
						context.put("id_pejabat_pengadu",
								(String) get_user.get("id_pejabat_pengadu"));
						context.put("id_seksyen",
								(String) get_user.get("id_seksyen"));
						context.put("nama_kementerian",
								(String) get_user.get("nama_kementerian"));
						context.put("nama_agensi",
								(String) get_user.get("nama_agensi"));
					}
				}

				this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(idJenisHakmilik), "", "onChange=\"doChangeJenisHakmilik();\""));
				this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot", Long.parseLong(idJenisLot), "", "onChange=\"doChangeJenisLot();\""));
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Long.parseLong(idNegeri), "",
						" onChange=\"doChangeNegeri();\""));
				this.context.put("selectDaerahTanah", HTML.SelectDaerahByIdNegeri(idNegeri, "socDaerahTanah", Long.parseLong(idDaerahTanah), "", "onChange=\"doChangeDaerahTanah();\""));
				this.context.put("selectMukimTanah", HTML.SelectMukimNoKodByDaerah(idDaerahTanah, "socMukimTanah", Long.parseLong(idMukimTanah), "", "onChange=\"doChangeMukimTanah();\""));
			}finally {
			if (db != null)
				db.close();
		}
		}

		else if(command.equals("simpanComplaint")){
			//simpanComplaint();
			//displayComplaint();
			Db db = null;
			try {
				db = new Db();
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");
			if(ID_ADUANPUBLIC.equals(""))
			{
				this.context.put("flag_reset_list","yes");
			}
			ID_ADUANPUBLIC = saveAduan(session,ID_ADUANPUBLIC,command,db);
			//autosave before upload
			this.context.put("flagUpload", getParam("flagUpload"));
			//simpanDraf();
			}finally {
				if (db != null)
					db.close();
			}
			 displayComplaint();
			vm = PATH+"index.jsp";
		}

		else if(command.equals("simpanDraf")){

			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");
			Db db = null;
			try {
				db = new Db();
			if(ID_ADUANPUBLIC.equals(""))
			{
				this.context.put("flag_reset_list","yes");
			}
			ID_ADUANPUBLIC = saveAduan(session,ID_ADUANPUBLIC,command,db);
			//autosave before upload
			this.context.put("flagUpload", getParam("flagUpload"));
			//simpanDraf();
			}finally {
				if (db != null)
					db.close();
			}
			vm = PATH + "editor.jsp";
			db = null;
			try {
				db = new Db();
				String user_id = getParam("userid");
				list_aduan = aduanDetailsVector(ID_ADUANPUBLIC,user_id,session,db);
				if (list_aduan.size() != 0) {
					Hashtable get_aduan = (Hashtable) list_aduan.get(0);
					context.put("no_fail", (String) get_aduan.get("NO_FAIL"));
					context.put("ID_LUAS", (String) get_aduan.get("ID_LUAS"));
					context.put("noWarta", (String) get_aduan.get("NO_WARTA"));
					context.put("tarikhWarta", (String) get_aduan.get("TARIKH_WARTA"));
					context.put("txtNoLot", (String) get_aduan.get("NO_LOT"));
					context.put("txtLuas", (String) get_aduan.get("LUAS"));
					context.put("idDaerah", (String) get_aduan.get("ID_DAERAH"));
					context.put("idNegeri", (String) get_aduan.get("ID_NEGERI"));
					context.put("idMukim", (String) get_aduan.get("ID_MUKIM"));
					context.put("idJenisHakmilik", (String) get_aduan.get("ID_JENISHAKMILIK"));
					context.put("idJenisLot", (String) get_aduan.get("ID_LOT"));
					context.put("txtNoHakmilikTanah", (String) get_aduan.get("NO_HAKMILIK"));
					context.put("ID_ADUANPUBLIC", (String) get_aduan.get("ID_ADUANPUBLIC"));
					context.put("id_phphakmilikaduan", (String) get_aduan.get("ID_HAKMILIKADUAN"));

					idNegeri = (String) get_aduan.get("ID_NEGERI");
					idDaerahTanah = (String) get_aduan.get("ID_DAERAH");
					idMukimTanah = (String) get_aduan.get("ID_MUKIM");
					idJenisHakmilik = (String) get_aduan.get("ID_JENISHAKMILIK");
					idJenisLot = (String) get_aduan.get("ID_LOT");
				}

				if (!userId.equals("")) {
					list_users = logic.getListUsers(userId, "", "", "", "",
							"", db);
					if (list_users.size() != 0) {
						Hashtable get_user = (Hashtable) list_users.get(0);
						context.put("user_id", (String) get_user.get("user_id"));
						context.put("nama_pengadu",
								(String) get_user.get("user_name"));
						context.put("no_tel", (String) get_user.get("user_id"));
						context.put("seksyen",
								(String) get_user.get("nama_seksyen"));
						context.put("pejabat",
								(String) get_user.get("nama_pejabat"));
						context.put("negeri",
								(String) get_user.get("nama_negeri"));
						context.put("daerah",
								(String) get_user.get("nama_daerah"));
						context.put("emel", (String) get_user.get("emel"));
						context.put("nama_jawatan",
								(String) get_user.get("nama_jawatan"));
						context.put("id_seksyen_user",
								(String) get_user.get("id_seksyen"));
						context.put("id_negeri_pengadu",
								(String) get_user.get("id_negeri_pengadu"));
						context.put("id_pejabat_pengadu",
								(String) get_user.get("id_pejabat_pengadu"));
						context.put("id_seksyen",
								(String) get_user.get("id_seksyen"));
						context.put("nama_kementerian",
								(String) get_user.get("nama_kementerian"));
						context.put("nama_agensi",
								(String) get_user.get("nama_agensi"));
					}
				}

				this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(idJenisHakmilik), "", "onChange=\"doChangeJenisHakmilik();\""));
				this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot", Long.parseLong(idJenisLot), "", "onChange=\"doChangeJenisLot();\""));
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Long.parseLong(idNegeri), "",
						" onChange=\"doChangeNegeri();\""));
				this.context.put("selectDaerahTanah", HTML.SelectDaerahByIdNegeri(idNegeri, "socDaerahTanah", Long.parseLong(idDaerahTanah), "", "onChange=\"doChangeDaerahTanah();\""));
				this.context.put("selectMukimTanah", HTML.SelectMukimNoKodByDaerah(idDaerahTanah, "socMukimTanah", Long.parseLong(idMukimTanah), "", "onChange=\"doChangeMukimTanah();\""));
			}finally {
			if (db != null)
				db.close();
		}

		}
		else if("tidakLengkap".equals(command)){
			tidakLengkap();
			displayComplaint();
			vm = PATH+"index.jsp";

		}else if("tidakRelevan".equals(command)){
			tidakReleven();
			displayComplaint();
			vm = PATH+"index.jsp";

		}else if("selesai".equals(command)){
			selesai();
			displayComplaint();
			vm = PATH+"index.jsp";
		}
		else if("cariRespon".equals(command)){
			String responseStatus = getParam("responseStatus");
			Vector v =getRespone().getMyTaskList(userId, responseStatus);
			context.put("lists", v);
		}

		else if(command.equals("editComplaint")){

			vm = PATH+"editor.jsp";

			String idRespon = getParam("idRespon");
			Db db = null;
			try {
				db = new Db();
				String user_id = getParam("idPengadu");
				list_aduan = aduanDetailsVector(idRespon,user_id,session,db);
				if (list_aduan.size() != 0) {
					Hashtable get_aduan = (Hashtable) list_aduan.get(0);
					context.put("no_fail", (String) get_aduan.get("NO_FAIL"));
					context.put("ID_LUAS", (String) get_aduan.get("ID_LUAS"));
					context.put("noWarta", (String) get_aduan.get("NO_WARTA"));
					context.put("tarikhWarta", (String) get_aduan.get("TARIKH_WARTA"));
					context.put("txtNoLot", (String) get_aduan.get("NO_LOT"));
					context.put("txtLuas", (String) get_aduan.get("LUAS"));
					context.put("idDaerah", (String) get_aduan.get("ID_DAERAH"));
					context.put("idNegeri", (String) get_aduan.get("ID_NEGERI"));
					context.put("idMukim", (String) get_aduan.get("ID_MUKIM"));
					context.put("idJenisHakmilik", (String) get_aduan.get("ID_JENISHAKMILIK"));
					context.put("idJenisLot", (String) get_aduan.get("ID_LOT"));
					context.put("txtNoHakmilikTanah", (String) get_aduan.get("NO_HAKMILIK"));
					context.put("ID_ADUANPUBLIC", (String) get_aduan.get("ID_ADUANPUBLIC"));
					context.put("id_phphakmilikaduan", (String) get_aduan.get("ID_HAKMILIKADUAN"));

					idNegeri = (String) get_aduan.get("ID_NEGERI");
					idDaerahTanah = (String) get_aduan.get("ID_DAERAH");
					idMukimTanah = (String) get_aduan.get("ID_MUKIM");
					idJenisHakmilik = (String) get_aduan.get("ID_JENISHAKMILIK");
					idJenisLot = (String) get_aduan.get("ID_LOT");
				}

				if (!userId.equals("")) {
					list_users = logic.getListUsers(userId, "", "", "", "",
							"", db);
					if (list_users.size() != 0) {
						Hashtable get_user = (Hashtable) list_users.get(0);
						context.put("user_id", (String) get_user.get("user_id"));
						context.put("nama_pengadu",
								(String) get_user.get("user_name"));
						context.put("no_tel", (String) get_user.get("user_id"));
						context.put("seksyen",
								(String) get_user.get("nama_seksyen"));
						context.put("pejabat",
								(String) get_user.get("nama_pejabat"));
						context.put("negeri",
								(String) get_user.get("nama_negeri"));
						context.put("daerah",
								(String) get_user.get("nama_daerah"));
						context.put("emel", (String) get_user.get("emel"));
						context.put("nama_jawatan",
								(String) get_user.get("nama_jawatan"));
						context.put("id_seksyen_user",
								(String) get_user.get("id_seksyen"));
						context.put("id_negeri_pengadu",
								(String) get_user.get("id_negeri_pengadu"));
						context.put("id_pejabat_pengadu",
								(String) get_user.get("id_pejabat_pengadu"));
						context.put("id_seksyen",
								(String) get_user.get("id_seksyen"));
						context.put("nama_kementerian",
								(String) get_user.get("nama_kementerian"));
						context.put("nama_agensi",
								(String) get_user.get("nama_agensi"));
					}
				}

				this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(idJenisHakmilik), "", "onChange=\"doChangeJenisHakmilik();\""));
				this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot", Long.parseLong(idJenisLot), "", "onChange=\"doChangeJenisLot();\""));
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Long.parseLong(idNegeri), "",
						" onChange=\"doChangeNegeri();\""));
				this.context.put("selectDaerahTanah", HTML.SelectDaerahByIdNegeri(idNegeri, "socDaerahTanah", Long.parseLong(idDaerahTanah), "", "onChange=\"doChangeDaerahTanah();\""));
				this.context.put("selectMukimTanah", HTML.SelectMukimNoKodByDaerah(idDaerahTanah, "socMukimTanah", Long.parseLong(idMukimTanah), "", "onChange=\"doChangeMukimTanah();\""));
			}finally {
			if (db != null)
				db.close();
		}
		}

		else{
			vm = PATH+"index.jsp";
			displayComplaint();
		}
		System.out.println("<<<command>>"+command);

		getProsesStatus();
		return vm;

	}

	public String saveAduan(HttpSession session,String ID_ADUANPUBLIC,String command,Db db) throws Exception {
		Connection conn = null;
		Db db1 = null;
		String sql = "";
		String sql2 = "";
		long id = 0;
		long idTanah = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		String new_ID_STATUS = "";
		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			conn = db1.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			SQLRenderer r2 = new SQLRenderer();

			String ID_STATUS = getParam("ID_STATUS"+"_"+ID_ADUANPUBLIC);
			String NO_ADUAN = getParam("NO_ADUAN"+"_"+ID_ADUANPUBLIC);
			String ID_JENISADUAN = getParam("ID_JENISADUAN"+"_"+ID_ADUANPUBLIC);
			String FLAG_HIDE_INFO = getParam("FLAG_HIDE_INFO"+"_"+ID_ADUANPUBLIC);
			String ID_SUMBERADUAN = getParam("ID_SUMBERADUAN"+"_"+ID_ADUANPUBLIC);
			String ID_PENGADU = getParam("ID_PENGADU"+"_"+ID_ADUANPUBLIC);

			String NAMA_PENGADU = getParam("NAMA_PENGADU"+"_"+ID_ADUANPUBLIC);
			String TEL_PENGADU = getParam("TEL_PENGADU"+"_"+ID_ADUANPUBLIC);
			String EMEL_PENGADU = getParam("EMEL_PENGADU"+"_"+ID_ADUANPUBLIC);

			String ID_KATEGORIADUAN = getParam("ID_KATEGORIADUAN"+"_"+ID_ADUANPUBLIC);
			String ID_KATEGORIPERTANYAAN = getParam("ID_KATEGORIPERTANYAAN"+"_"+ID_ADUANPUBLIC);
			String SUBJEK_ADUAN = getParam("SUBJEK_ADUAN"+"_"+ID_ADUANPUBLIC);
			String TARIKH_KEJADIAN = getParam("TARIKH_KEJADIAN"+"_"+ID_ADUANPUBLIC);



			if(!TARIKH_KEJADIAN.equals(""))
			{
				TARIKH_KEJADIAN = "to_date('" +TARIKH_KEJADIAN+ "','dd/MM/yyyy')";
			}
			else
			{
				TARIKH_KEJADIAN = "''";
			}

			String JAM = getParam("JAM"+"_"+ID_ADUANPUBLIC);
			String MINIT = getParam("MINIT"+"_"+ID_ADUANPUBLIC);
			String JENIS_WAKTU = getParam("JENIS_WAKTU"+"_"+ID_ADUANPUBLIC);
			String LOKASI_KEJADIAN = getParam("LOKASI_KEJADIAN"+"_"+ID_ADUANPUBLIC);
			String ID_NEGERI = getParam("ID_NEGERI"+"_"+ID_ADUANPUBLIC);
			String ID_DAERAH = getParam("ID_DAERAH"+"_"+ID_ADUANPUBLIC);
			String NAMA_PIHAK_TERLIBAT = getParam("NAMA_PIHAK_TERLIBAT"+"_"+ID_ADUANPUBLIC);
			String KETERANGAN_ADUAN = getParam("KETERANGAN_ADUAN"+"_"+ID_ADUANPUBLIC);


			String ID_JENISTINDAKAN = getParam("ID_JENISTINDAKAN"+"_"+ID_ADUANPUBLIC);
			String ID_BAHAGIAN = getParam("ID_BAHAGIAN"+"_"+ID_ADUANPUBLIC);
			String ID_NEGERI_BAHAGIAN = getParam("ID_NEGERI_BAHAGIAN"+"_"+ID_ADUANPUBLIC);
			String ID_SUMBERTINDAKAN = getParam("ID_SUMBERTINDAKAN"+"_"+ID_ADUANPUBLIC);
			String MAKLUMBALAS_UI = getParam("MAKLUMBALAS_UI"+"_"+ID_ADUANPUBLIC);
			String ID_STATUS_UI = getParam("ID_STATUS_UI"+"_"+ID_ADUANPUBLIC);

			String EMEL_BAHAGIAN_1 = getParam("EMEL_BAHAGIAN_1"+"_"+ID_ADUANPUBLIC);
			String EMEL_BAHAGIAN_2 = getParam("EMEL_BAHAGIAN_2"+"_"+ID_ADUANPUBLIC);
			String EMEL_BAHAGIAN_3 = getParam("EMEL_BAHAGIAN_3"+"_"+ID_ADUANPUBLIC);
			String EMEL_BAHAGIAN_4 = getParam("EMEL_BAHAGIAN_4"+"_"+ID_ADUANPUBLIC);
			String FLAG_LUAR_TINDAKAN = getParam("FLAG_LUAR_TINDAKAN"+"_"+ID_ADUANPUBLIC);

			String ID_NEGERI_PT = getParam("ID_NEGERI_PT"+"_"+ID_ADUANPUBLIC);
			String ID_DAERAH_PT = getParam("ID_DAERAH_PT"+"_"+ID_ADUANPUBLIC);
			String ID_PEJABATTANAH = getParam("ID_PEJABATTANAH"+"_"+ID_ADUANPUBLIC);

			String MAKLUMBALAS_BAHAGIAN = getParam("MAKLUMBALAS_BAHAGIAN"+"_"+ID_ADUANPUBLIC);
			String ID_STATUS_BAHAGIAN = getParam("ID_STATUS_BAHAGIAN"+"_"+ID_ADUANPUBLIC);

			if(!ID_ADUANPUBLIC.equals(""))
			{
				r.update("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
			}
			else
			{
				id = DB.getNextID(db1, "TBLADUANPUBLIC_SEQ");
				r.add("ID_ADUANPUBLIC", id);
			}

			if(command.equals("simpanComplaint"))
			{
				if(NO_ADUAN.equals(""))//hantar log
				{
					Date now = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
					String tahun = formatter.format(now);
					SimpleDateFormat formatter_month = new SimpleDateFormat("MM");
					String bulan  = formatter_month.format(now);
					String NO_RUJUKAN_ADUAN = ""+tahun+"/"+bulan+"/"+ String.format("%06d", getSeqNo(tahun,bulan,db));
					r.add("NO_ADUAN", NO_RUJUKAN_ADUAN);
					r.add("TARIKH_ADUAN", r.unquote("sysdate"));
					//r.add("ID_STATUS",16121); //LOG BARU
					new_ID_STATUS = "16121";
					myLog.info("**** save new_ID_STATUS : "+new_ID_STATUS);
				}
			}
			else if(command.equals("simpanDraf"))
			{
				if(ID_ADUANPUBLIC.equals(""))
				{
					//r.add("ID_STATUS",16125); //DERAF
					new_ID_STATUS = "16125";
					myLog.info("**** saveDeraf new_ID_STATUS : "+new_ID_STATUS);
					r.add("ID_STATUS", new_ID_STATUS);
				}
			}

			if(ID_STATUS.equals("16125") || ID_STATUS.equals(""))
			{
				r.add("ID_JENISADUAN", ID_JENISADUAN);
				r.add("FLAG_HIDE_INFO", FLAG_HIDE_INFO);
				r.add("KETERANGAN_ADUAN", getParam("aduan").toUpperCase());
				r.add("NO_FAIL", getParam("no_fail").toUpperCase());
				r.add("ID_SUMBERADUAN", ID_SUMBERADUAN);
				r.add("ID_PENGADU", getParam("userid"));
				r.add("NAMA_PENGADU", getParam("nama_pengadu"));
				r.add("TEL_PENGADU", getParam("no_tel"));
				r.add("EMEL_PENGADU", getParam("emel"));
			}
			else
			{
				if(ID_STATUS_UI.equals("16122") || ID_STATUS_UI.equals("16123") || ID_STATUS_UI.equals("16126"))
				{
					r.add("ID_JENISTINDAKAN", ID_JENISTINDAKAN);
					r.add("ID_BAHAGIAN", ID_BAHAGIAN);
					r.add("ID_NEGERI_BAHAGIAN", ID_NEGERI_BAHAGIAN);
					r.add("ID_SUMBERTINDAKAN", ID_SUMBERTINDAKAN);
					r.add("MAKLUMBALAS_UI", MAKLUMBALAS_UI);

					r.add("ID_NEGERI_PT", ID_NEGERI_PT);
					r.add("ID_DAERAH_PT", ID_DAERAH_PT);
					r.add("ID_PEJABATTANAH", ID_PEJABATTANAH);

					r.add("EMEL_BAHAGIAN_1", EMEL_BAHAGIAN_1);
					r.add("EMEL_BAHAGIAN_2", EMEL_BAHAGIAN_2);
					r.add("EMEL_BAHAGIAN_3", EMEL_BAHAGIAN_3);
					r.add("EMEL_BAHAGIAN_4", EMEL_BAHAGIAN_4);
					myLog.info(" FLAG_LUAR_TINDAKAN ::: "+FLAG_LUAR_TINDAKAN);
					r.add("FLAG_LUAR_TINDAKAN", FLAG_LUAR_TINDAKAN);

					if(!ID_STATUS_UI.equals(ID_STATUS))
					{
						new_ID_STATUS = ID_STATUS_UI;
					}
				}
				else if(ID_STATUS_UI.equals("16127") || ID_STATUS_UI.equals("16123"))
				{
					r.add("MAKLUMBALAS_BAHAGIAN", MAKLUMBALAS_BAHAGIAN);
					if(!ID_STATUS_BAHAGIAN.equals(ID_STATUS))
					{
						new_ID_STATUS = ID_STATUS_BAHAGIAN;
					}
				}
			}

			if(!ID_ADUANPUBLIC.equals(""))
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLADUANPUBLIC");
			}
			else
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLADUANPUBLIC");
			}
			myLog.info("ADUAN : SAVE  : "+sql);
			stmt.executeUpdate(sql);
			conn.commit();
			if(!ID_ADUANPUBLIC.equals(""))
			{
				//hantarEmel(session,ID_PERMOHONANBANTUUNIT);
				//AuditTrail.logActivity(this,session,"UPD","TBLADUANPUBLIC [ID_ADUANPUBLIC : "+ID_ADUANPUBLIC+"] Updated");

			}
			else
			{
				//hantarEmel(session,id+"");
				//AuditTrail.logActivity(this,session,"INS","TBLADUANPUBLIC [ID_ADUANPUBLIC : "+id+"] Inserted");
				ID_ADUANPUBLIC = id+"";
			}

			String id_phphakmilikaduan = getParam("id_phphakmilikaduan");
			if (id_phphakmilikaduan == null || id_phphakmilikaduan.trim().length() == 0) {
				id_phphakmilikaduan = "0";
			}
			idTanah = Long.parseLong(id_phphakmilikaduan);

			if(idTanah!=0)
			{
				System.out.println("Masuk sini");
				r2.update("ID_HAKMILIKADUAN", idTanah);
			}
			else
			{
				idTanah = DB.getNextID(db1, "TBLHTPHAKMILIKADUAN_SEQ");
				r2.add("ID_HAKMILIKADUAN", idTanah);
			}

			r2.add("ID_ADUAN", ID_ADUANPUBLIC);
			r2.add("ID_NEGERI", getParam("idNegeri"));
			r2.add("ID_DAERAH", getParam("idDaerahTanah"));
			r2.add("ID_MUKIM", getParam("idMukimTanah"));
			r2.add("ID_JENISHAKMILIK", getParam("idJenisHakmilik"));
			r2.add("NO_HAKMILIK", getParam("txtNoHakmilikTanah"));
			r2.add("NO_WARTA", getParam("noWarta"));
			r2.add("TARIKH_WARTA", r.unquote("sysdate"));
			r2.add("ID_LOT", getParam("idJenisLot"));
			r2.add("NO_LOT", getParam("txtNoLot"));
			r2.add("ID_LUAS", getParam("txtLuas"));
			r2.add("LUAS", getParam("socLuas"));
			r2.add("ID_MASUK", USER_ID_SYSTEM);
			r2.add("TARIKH_MASUK", r.unquote("sysdate"));
			r2.add("ID_KEMASKINI", USER_ID_SYSTEM);
			r2.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

			if(idTanah!=0)
			{
				sql2 = r2.getSQLUpdate("TBLHTPHAKMILIKADUAN");
			}
			else
			{
				sql2 = r2.getSQLInsert("TBLHTPHAKMILIKADUAN");
			}

			myLog.info("TANAH : SAVE  : "+sql2);
			stmt.executeUpdate(sql2);
			conn.commit();
		}
		catch (SQLException se) {
			myLog.error(se);
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
		}
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
		return ID_ADUANPUBLIC;
	}

	public static synchronized int getSeqNo(String tahun,String bulan, Db db)
			throws DbException {

		Db db1 = null;
		Connection conn = null;
		StringBuffer sb = new StringBuffer();
		int seqno = 0;
		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}

			conn = db1.getConnection();
			conn.setAutoCommit(false);

			// f = new File();
			boolean found = false;

			sb.append("SELECT NO_TURUTAN,BULAN  FROM TBLRUJSEQNOADUANPUBLIC WHERE ");
			sb.append(" TAHUN = '" + tahun + "'   AND  BULAN = '" + bulan + "'");
			ResultSet rs = db1.getStatement().executeQuery(sb.toString());

			if (rs.next())
				found = true;

			myLog.info("found SEQ :"+found);


			if (found) {
				// f.increaseSeqAduan(id_jenisaduan);
				increaseNo(tahun,bulan,db);
			} else {
				// f.addNewAduan(id_jenisaduan);
				addNo(tahun,bulan,db);
			}
			ResultSet rs2 = db1.getStatement().executeQuery(sb.toString());
			if (rs2.next()) {

				seqno = rs2.getInt("NO_TURUTAN");

			}
			conn.commit();

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException localSQLException1) {
			}
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}

		return seqno;
	}

	public static void increaseNo(String tahun,String bulan,Db db) throws DbException {

		Db db1 = null;
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE TBLRUJSEQNOADUANPUBLIC  SET ");
		sb.append("no_turutan = no_turutan + 1 ");
		sb.append(" WHERE ");
		sb.append(" TAHUN = '" + tahun + "'");
		sb.append(" AND BULAN = '" + bulan + "'");
		myLog.info("increaseNo: "+sb.toString());
		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}

			try {
				db1.getStatement().executeUpdate(sb.toString());
			} catch (SQLException x) {
				x.printStackTrace();
			}
		} catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
	}

	 public static void addNo(String tahun,String bulan,Db db) throws DbException {

			Db db1 = null;
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO TBLRUJSEQNOADUANPUBLIC (TAHUN,BULAN,NO_TURUTAN)");
			sb.append(" VALUES (");
			sb.append("'" + tahun + "'");
			sb.append(",'" + bulan + "'");
			sb.append(",1)"); // initial value

			try {
				if(db==null)
				{
					db1 = new Db();
				}
				else
				{
					db1 = db;
				}
				db1.getStatement().executeUpdate(sb.toString());
			} catch (Exception ex) {
				throw new DbException(ex.getMessage() + ": " + sb.toString());
			} finally {
				if(db==null)
				{
					if (db1 != null)
						db1.close();
				}
			}
		}

	 public void hantarEmel(HttpSession session,String ID_ADUANPUBLIC, String[] PENERIMA, String ID_STATUS,Db db) throws Exception {
		 //hantarEmel(session,ID_ADUANPUBLIC, PENERIMA, ID_STATUS,db, "");
	 }

	 public void hantarEmel(HttpSession session,String ID_ADUANPUBLIC, String[] PENERIMA, String ID_STATUS,Db db, String Flag_Akuan_terima) throws Exception {
		    String selectedLanguage = (String) session.getAttribute("selectedLanguage");
		    String FLAG_CC_PENGADU = "";
		    String FLAG_CC_PENGARAH_BAHAGIAN = "";
		 	if(PENERIMA.length>0)
		 	{
		 		Map view = view(session, ID_ADUANPUBLIC,"","",selectedLanguage,db);

		 		String ID_BAHAGIAN = (String)view.get("ID_BAHAGIAN");
		 		String EMEL_PENGADU = (String)view.get("EMEL_PENGADU");
		 		String NO_ADUAN = (String)view.get("NO_ADUAN");
		 		String JENIS_ADUAN = (String)view.get("JENIS_ADUAN");
		 		String STATUS = (String)view.get("STATUS");
		 		String NAMA_BAHAGIAN = (String)view.get("NAMA_BAHAGIAN");
		 		String NEGERI_BAHAGIAN = (String)view.get("NEGERI_BAHAGIAN");
		 		//String EMEL_BAHAGIAN_1 = (String)view.get("EMEL_BAHAGIAN_1");
		 		//String EMEL_BAHAGIAN_2 = (String)view.get("EMEL_BAHAGIAN_2");
		 		//String EMEL_BAHAGIAN_3 = (String)view.get("EMEL_BAHAGIAN_3");
		 		//String EMEL_BAHAGIAN_4 = (String)view.get("EMEL_BAHAGIAN_4");


		 		//String content_maklumat_aduan = "*infoaduan - amik maklumat asas - buat dalam table lek lok*";
		 		String content_maklumat_aduan =  returnHTMLMaklumatAduan(session,view,db);



				myLog.info("MASUK FUNCTION EMEL ADUAN");
				EmailProperty pro = EmailProperty.getInstance();
				EmailSender email = EmailSender.getInstance();
				email.FROM = pro.getFrom();
				email.MULTIPLE_RECIEPIENT = new String[1];
				String penutup ="";
				if(selectedLanguage.equals("ENGLISH"))
				{
					//convert version melayu to english
					penutup = "<br><br>Note: This email generated by MyeTaPP that requires no response. ";
				}
				else
				{
					penutup = "<br><br>Nota : Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas.";
				}

				String subject = "";
				//String subject_terimaan = "";//untuk akuan terima
				String content = "";
				//String content_terimaan = "";

				if(ID_STATUS.equals("16121"))//log baru
				{
					if(Flag_Akuan_terima.equals("Y"))
					{
						String subject_akuan_terima = "LOG DITERIMA";
						if(selectedLanguage.equals("ENGLISH"))
						{
							subject_akuan_terima = "LOG RECEIVED";
						}
						subject += "LOG ["+NO_ADUAN+" - "+JENIS_ADUAN+"] : "+subject_akuan_terima+"";
					}
					else
					{
						subject += "LOG ["+NO_ADUAN+" - "+JENIS_ADUAN+"] : "+STATUS+"";
					}

				}
				else if(ID_STATUS.equals("16122"))// status : dalam tindakan unit intergriti
				{
					/*
					if(selectedLanguage.equals("ENGLISH"))
					{
						subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+"";
						//convert version melayu to english
					}
					else
					{
						subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+"";
						//content += content_maklumat_aduan;
					}
					*/
					subject += "LOG ["+NO_ADUAN+" - "+JENIS_ADUAN+"] : "+STATUS+"";
				}
				else if(ID_STATUS.equals("16126"))//hantar untuk tindakan bahagian
				{
					/*
					if(selectedLanguage.equals("ENGLISH"))
					{
						//convert version melayu to english
						subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+" ["+NAMA_BAHAGIAN+", "+NEGERI_BAHAGIAN+"]  ";
					}
					else
					{
						subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+" ["+NAMA_BAHAGIAN+", "+NEGERI_BAHAGIAN+"]  ";
						//content += content_maklumat_aduan;
					}
					*/
					subject += "LOG ["+NO_ADUAN+" - "+JENIS_ADUAN+"] : "+STATUS+" ["+NAMA_BAHAGIAN+", "+NEGERI_BAHAGIAN+"]  ";

					FLAG_CC_PENGADU = "Y";
					FLAG_CC_PENGARAH_BAHAGIAN = "Y";

				}
				else if(ID_STATUS.equals("16127"))// status : dalam tindakan BAHAGIAN
				{
					/*
					if(selectedLanguage.equals("ENGLISH"))
					{
						//convert version melayu to english
						subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+" ["+NAMA_BAHAGIAN+", "+NEGERI_BAHAGIAN+"]  ";
					}
					else
					{
						subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+" ["+NAMA_BAHAGIAN+", "+NEGERI_BAHAGIAN+"]  ";
						//content += content_maklumat_aduan;
					}
					*/
					subject += "LOG ["+NO_ADUAN+" - "+JENIS_ADUAN+"] : "+STATUS+" ["+NAMA_BAHAGIAN+", "+NEGERI_BAHAGIAN+"]  ";
				}
				else if(ID_STATUS.equals("16123"))// status : selesai
				{
					/*
					if(selectedLanguage.equals("ENGLISH"))
					{
						//convert version melayu to english
						subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+" ";
					}
					else
					{
						subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+" ";

					}
					*/
					subject += "LOG ["+NO_ADUAN+" - "+JENIS_ADUAN+"] : "+STATUS+"";
				}



				myLog.info(" EMEL PENERIMA : "+PENERIMA);
				content += content_maklumat_aduan;
				email.MULTIPLE_RECIEPIENT = PENERIMA;

				List<String> emel_cc = new ArrayList<String>();

				if(FLAG_CC_PENGADU.equals("Y"))
				{
					emel_cc.add(EMEL_PENGADU);
				}

				List listPengawaiIkutJawatan = null;
				if(FLAG_CC_PENGARAH_BAHAGIAN.equals("Y"))
				{
					//get pengarah bahagian di hq
					listPengawaiIkutJawatan = listPengawaiIkutJawatan(session,ID_BAHAGIAN,"16","4", db);
					for(int i = 0; i < listPengawaiIkutJawatan.size();i++)
					{
						Map m = (Map) listPengawaiIkutJawatan.get(i);
						String emel_peg = (String) m.get("EMEL");
						myLog.info("EMEL PENGARAH untuk DI CC : "+emel_peg);
						emel_cc.add(emel_peg);
					}
				}



				email.TO_CC = new String[emel_cc.size()];
				for(int i = 0; i < emel_cc.size();i++)
				{
					email.TO_CC[i] = emel_cc.get(i);
				}





				email.SUBJECT = subject;
				email.MESSAGE = content + penutup;
				email.sendEmail();



		 	}
		 }

	 public List listPengawaiIkutJawatan(HttpSession session,String ID_BAHAGIAN,String ID_NEGERI,String ID_JAWATAN, Db db)throws Exception {
			Db db1 = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listPengawaiIkutJawatan = null;
			String sql = "";
			try {
				if(db == null)
				{
					db1 = new Db();
				}
				else
				{
					db1 = db;
				}
				stmt = db1.getStatement();

				sql = " SELECT DISTINCT U.USER_ID,U.USER_LOGIN, UI.EMEL, U.USER_NAME, UI.ID_NEGERI, UI.ID_SEKSYEN, J.KETERANGAN, J.ID_JAWATAN,UI.FLAG_AKTIF   "+
						" FROM USERS U, USERS_INTERNAL UI, TBLRUJJAWATAN J   "+
						" WHERE U.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN  AND UI.EMEL IS NOT NULL  "+
						" AND UI.ID_NEGERI = '"+ID_NEGERI+"' AND UI.ID_SEKSYEN = '"+ID_BAHAGIAN+"'   "+
						" AND UI.ID_JAWATAN = '"+ID_JAWATAN+"' AND NVL(UI.FLAG_AKTIF,' ') IN (' ', '1') ";

				myLog.info(" SQL : listPengawaiIkutJawatan :"+ sql);
				rs = stmt.executeQuery(sql);
				listPengawaiIkutJawatan = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
					h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
					h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
					listPengawaiIkutJawatan.add(h);
				}

			} finally {
				if(db == null)
				{
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db1 != null)
						db1.close();
				}
			}
			return listPengawaiIkutJawatan;
		}

	 public Map view(HttpSession session, String ID_ADUANPUBLIC,String rowCss,String bil,String selectedLanguage,Db db) throws Exception {
			Db db1 = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
			String USER_ROLE = (String) session.getAttribute("myrole");
			String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
			String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
			try {
				if(db==null)
				{
					db1 = new Db();
				}
				else
				{
					db1 = db;
				}

				stmt = db1.getStatement();
				Map h = Collections.synchronizedMap(new HashMap());
				/*if(!ID_ADUANPUBLIC.equals(""))
				{
					//bila ada data
					sql = sqlAduanPublic(session,"");
					sql += "  AND T.ID_ADUANPUBLIC = '"+ID_ADUANPUBLIC+"' ";sini ros
					myLog.info(" SQL VIEW ADUAN :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						h = getHashMapAduan(session,rs,rowCss,bil,selectedLanguage,db1);
					}
				}
				else
				{
					//bila 1st time insert, kosongkan object
					h = getHashMapAduan(session,null,rowCss,bil,selectedLanguage,db1);
				}*/
				/*ros listAduan = listAduan(userId,session,db);
				context.put("lists", listAduan);*/
	            displayComplaint();
				return h;
			} finally {
				if(db==null)
				{
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db1 != null)
						db1.close();
				}
			}
		}

	 public Map getHashMapAduan(HttpSession session, ResultSet rs,String rowCss, String bil,String selectedLanguage, Db db) throws Exception
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
			String USER_ROLE = (String) session.getAttribute("myrole");
			Hashtable getUserOnline = null;
			Hashtable getSumber = null;
			String UO_FULLNAME = "";
			String UO_EMEL = "";
			String UO_TEL= "";
			String DEFAULT_NAMA_SUMBER= "";
			if(rs == null)
			{
				getUserOnline = getUserOnline(session, USER_ID_SYSTEM,db);
				UO_FULLNAME = (String)getUserOnline.get("FULLNAME");
				UO_EMEL = (String)getUserOnline.get("EMEL");
				UO_TEL = (String)getUserOnline.get("NO_HP");

				getSumber = getSumber(session,"16101",db);
				DEFAULT_NAMA_SUMBER = (String)getSumber.get("NAMA_SUMBER");
			}

			Map h = Collections.synchronizedMap(new HashMap());
			try {
				h.put("rowCss",rowCss);
				h.put("BIL",bil);

				//default jika public yg insert aduan

				String temp_id_sumberaduan = "16101";
				String temp_id_user = USER_ID_SYSTEM;
				if(rs==null && USER_ROLE.equals("user_unit_intergriti"))
				{
					temp_id_sumberaduan = "";
					temp_id_user = "";
					UO_FULLNAME = "";
					UO_EMEL = "";
					UO_TEL = "";
				}
				h.put("ID_MASUK",rs == null ? "" : (rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK")));
				h.put("ID_SUMBERADUAN",rs == null ? temp_id_sumberaduan : (rs.getString("ID_SUMBERADUAN") == null ? "" : rs.getString("ID_SUMBERADUAN")));
				h.put("ID_PENGADU",rs == null ? temp_id_user : (rs.getString("ID_PENGADU") == null ? "" : rs.getString("ID_PENGADU")));
				h.put("NAMA_PENGADU",rs == null ? UO_FULLNAME : (rs.getString("NAMA_PENGADU") == null ? "" : rs.getString("NAMA_PENGADU").toUpperCase()));
				h.put("EMEL_PENGADU",rs == null ? UO_EMEL : (rs.getString("EMEL_PENGADU") == null ? "" : rs.getString("EMEL_PENGADU")));
				h.put("TEL_PENGADU",rs == null ? UO_TEL : (rs.getString("TEL_PENGADU") == null ? "" : rs.getString("TEL_PENGADU")));

				String SUMBER_PENGADU = rs == null ? "" : (rs.getString("SUMBER_PENGADU") == null ? "" : rs.getString("SUMBER_PENGADU"));
				if(selectedLanguage.equals("ENGLISH"))
				{
					SUMBER_PENGADU = rs == null ? "" : (rs.getString("SUMBER_PENGADU_ENG") == null ? "" : rs.getString("SUMBER_PENGADU_ENG"));
				}
				h.put("SUMBER_PENGADU",rs == null ? DEFAULT_NAMA_SUMBER : SUMBER_PENGADU);


				h.put("NAMA_BAHAGIAN",rs == null ? "" : (rs.getString("NAMA_BAHAGIAN") == null ? "" : rs.getString("NAMA_BAHAGIAN").toUpperCase()));
				h.put("ID_ADUANPUBLIC", rs == null ? "" : (rs.getString("ID_ADUANPUBLIC") == null ? "" : rs.getString("ID_ADUANPUBLIC")));
				h.put("ID_STATUS",rs == null ? "" : (rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS")));
				h.put("NO_ADUAN",rs == null ? "" : (rs.getString("NO_ADUAN") == null ? "" : rs.getString("NO_ADUAN").toUpperCase()));
				h.put("ID_PEGAWAI_UI",rs == null ? "" : (rs.getString("ID_PEGAWAI_UI") == null ? "" : rs.getString("ID_PEGAWAI_UI")));
				h.put("ID_PEGAWAI_BAHAGIAN",rs == null ? "" : (rs.getString("ID_PEGAWAI_BAHAGIAN") == null ? "" : rs.getString("ID_PEGAWAI_BAHAGIAN")));
				h.put("TARIKH_ADUAN",rs == null ? "" : (rs.getString("TARIKH_ADUAN") == null ? "" : rs.getString("TARIKH_ADUAN")));
				h.put("TARIKH_TERIMA_ADUAN_UI",rs == null ? "" : (rs.getString("TARIKH_TERIMA_ADUAN_UI") == null ? "" : rs.getString("TARIKH_TERIMA_ADUAN_UI")));
				h.put("TARIKH_TERIMA_ADUAN_BAHAGIAN",rs == null ? "" : (rs.getString("TARIKH_TERIMA_ADUAN_BAHAGIAN") == null ? "" : rs.getString("TARIKH_TERIMA_ADUAN_BAHAGIAN")));
				h.put("TARIKH_ADUAN_SELESAI",rs == null ? "" : (rs.getString("TARIKH_ADUAN_SELESAI") == null ? "" : rs.getString("TARIKH_ADUAN_SELESAI")));
				h.put("FLAG_HIDE_INFO",rs == null ? "2" : (rs.getString("FLAG_HIDE_INFO") == null ? "" : rs.getString("FLAG_HIDE_INFO")));
				h.put("ID_JENISADUAN",rs == null ? "" : (rs.getString("ID_JENISADUAN") == null ? "" : rs.getString("ID_JENISADUAN")));
				h.put("ID_KATEGORIADUAN",rs == null ? "" : (rs.getString("ID_KATEGORIADUAN") == null ? "" : rs.getString("ID_KATEGORIADUAN")));
				h.put("ID_KATEGORIPERTANYAAN",rs == null ? "" : (rs.getString("ID_KATEGORIPERTANYAAN") == null ? "" : rs.getString("ID_KATEGORIPERTANYAAN")));


				h.put("JAM",rs == null ? "" : (rs.getString("JAM") == null ? "" : rs.getString("JAM")));
				h.put("MINIT",rs == null ? "" : (rs.getString("MINIT") == null ? "" : rs.getString("MINIT")));
				h.put("JENIS_WAKTU",rs == null ? "" : (rs.getString("JENIS_WAKTU") == null ? "" : rs.getString("JENIS_WAKTU")));
				h.put("SUBJEK_ADUAN",rs == null ? "" : (rs.getString("SUBJEK_ADUAN") == null ? "" : rs.getString("SUBJEK_ADUAN").toUpperCase()));
				h.put("TARIKH_KEJADIAN",rs == null ? "" : (rs.getString("TARIKH_KEJADIAN") == null ? "" : rs.getString("TARIKH_KEJADIAN")));
				h.put("LOKASI_KEJADIAN",rs == null ? "" : (rs.getString("LOKASI_KEJADIAN") == null ? "" : rs.getString("LOKASI_KEJADIAN").toUpperCase()));
				h.put("ID_NEGERI",rs == null ? "" : (rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI")));
				h.put("ID_NEGERI_BAHAGIAN",rs == null ? "" : (rs.getString("ID_NEGERI_BAHAGIAN") == null ? "" : rs.getString("ID_NEGERI_BAHAGIAN")));
				h.put("ID_DAERAH",rs == null ? "" : (rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH")));
				h.put("NAMA_PIHAK_TERLIBAT",rs == null ? "" : (rs.getString("NAMA_PIHAK_TERLIBAT") == null ? "" : rs.getString("NAMA_PIHAK_TERLIBAT").toUpperCase()));
				h.put("KETERANGAN_ADUAN",rs == null ? "" : (rs.getString("KETERANGAN_ADUAN") == null ? "" : rs.getString("KETERANGAN_ADUAN").toUpperCase()));
				h.put("MAKLUMBALAS_UI",rs == null ? "" : (rs.getString("MAKLUMBALAS_UI") == null ? "" : rs.getString("MAKLUMBALAS_UI").toUpperCase()));
				h.put("MAKLUMBALAS_BAHAGIAN",rs == null ? "" : (rs.getString("MAKLUMBALAS_BAHAGIAN") == null ? "" : rs.getString("MAKLUMBALAS_BAHAGIAN").toUpperCase()));
				h.put("ID_JENISTINDAKAN",rs == null ? "" : (rs.getString("ID_JENISTINDAKAN") == null ? "" : rs.getString("ID_JENISTINDAKAN")));
				h.put("ID_BAHAGIAN",rs == null ? "" : (rs.getString("ID_BAHAGIAN") == null ? "" : rs.getString("ID_BAHAGIAN")));
				h.put("ID_SUMBERTINDAKAN",rs == null ? "" : (rs.getString("ID_SUMBERTINDAKAN") == null ? "" : rs.getString("ID_SUMBERTINDAKAN")));
				h.put("NAMA_PEGAWAI_BAHAGIAN",rs == null ? "" : (rs.getString("NAMA_PEGAWAI_BAHAGIAN") == null ? "" : rs.getString("NAMA_PEGAWAI_BAHAGIAN").toUpperCase()));
				h.put("EMEL_PEGAWAI_BAHAGIAN",rs == null ? "" : (rs.getString("EMEL_PEGAWAI_BAHAGIAN") == null ? "" : rs.getString("EMEL_PEGAWAI_BAHAGIAN")));
				h.put("TEL_PEGAWAI_BAHAGIAN",rs == null ? "" : (rs.getString("TEL_PEGAWAI_BAHAGIAN") == null ? "" : rs.getString("TEL_PEGAWAI_BAHAGIAN")));

				h.put("NO_TEL_PEGAWAI_UI",rs == null ? "" : (rs.getString("NO_TEL_PEGAWAI_UI") == null ? "" : rs.getString("NO_TEL_PEGAWAI_UI").toUpperCase()));
				h.put("PEGAWAI_UI",rs == null ? "" : (rs.getString("PEGAWAI_UI") == null ? "" : rs.getString("PEGAWAI_UI").toUpperCase()));
				h.put("EMEL_PEGAWAI_UI",rs == null ? "" : (rs.getString("EMEL_PEGAWAI_UI") == null ? "" : rs.getString("EMEL_PEGAWAI_UI")));
				h.put("TARIKH_KEMASKINI",rs == null ? "" : (rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI").toUpperCase()));


				h.put("EMEL_BAHAGIAN_1",rs == null ? "" : (rs.getString("EMEL_BAHAGIAN_1") == null ? "" : rs.getString("EMEL_BAHAGIAN_1")));
				h.put("EMEL_BAHAGIAN_2",rs == null ? "" : (rs.getString("EMEL_BAHAGIAN_2") == null ? "" : rs.getString("EMEL_BAHAGIAN_2")));
				h.put("EMEL_BAHAGIAN_3",rs == null ? "" : (rs.getString("EMEL_BAHAGIAN_3") == null ? "" : rs.getString("EMEL_BAHAGIAN_3")));
				h.put("EMEL_BAHAGIAN_4",rs == null ? "" : (rs.getString("EMEL_BAHAGIAN_4") == null ? "" : rs.getString("EMEL_BAHAGIAN_4")));
				h.put("FLAG_LUAR_TINDAKAN",rs == null ? "" : (rs.getString("FLAG_LUAR_TINDAKAN") == null ? "" : rs.getString("FLAG_LUAR_TINDAKAN")));
				String FLAG_LUAR_TINDAKAN = rs == null ? "" : (rs.getString("FLAG_LUAR_TINDAKAN") == null ? "" : rs.getString("FLAG_LUAR_TINDAKAN"));
				String LUAR_TINDAKAN = "";

				if(FLAG_LUAR_TINDAKAN.equals("Y"))
				{
					LUAR_TINDAKAN = "YA";
					if(selectedLanguage.equals("ENGLISH"))
					{
						LUAR_TINDAKAN = "YES";
					}
				}
				else
				{
					LUAR_TINDAKAN = "TIDAK";
					if(selectedLanguage.equals("ENGLISH"))
					{
						LUAR_TINDAKAN = "NO";
					}
				}
				h.put("LUAR_TINDAKAN",LUAR_TINDAKAN);


				h.put("ID_PEJABATTANAH",rs == null ? "" : (rs.getString("ID_PEJABATTANAH") == null ? "" : rs.getString("ID_PEJABATTANAH").toUpperCase()));
				h.put("ID_NEGERI_PT",rs == null ? "" : (rs.getString("ID_NEGERI_PT") == null ? "" : rs.getString("ID_NEGERI_PT").toUpperCase()));
				h.put("ID_DAERAH_PT",rs == null ? "" : (rs.getString("ID_DAERAH_PT") == null ? "" : rs.getString("ID_DAERAH_PT").toUpperCase()));
				h.put("NEGERI_PT",rs == null ? "" : (rs.getString("NEGERI_PT") == null ? "" : rs.getString("NEGERI_PT").toUpperCase()));
				h.put("DAERAH_PT",rs == null ? "" : (rs.getString("DAERAH_PT") == null ? "" : rs.getString("DAERAH_PT").toUpperCase()));
				h.put("PEJABAT_PT",rs == null ? "" : (rs.getString("PEJABAT_PT") == null ? "" : rs.getString("PEJABAT_PT").toUpperCase()));



				//x;

				String mesej_notifikasi_tindakan = "";
				if(rs != null)
				{
					String tarikh_kemaskini = rs == null ? "" : (rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
					String id_status = (rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));

					if(USER_ROLE.equals("user_unit_intergriti"))
					{
						String id_pegawai_ui = (rs.getString("ID_PEGAWAI_UI") == null ? "" : rs.getString("ID_PEGAWAI_UI"));
						if(id_status.equals("16122") && id_pegawai_ui.equals(USER_ID_SYSTEM))
						{
							mesej_notifikasi_tindakan = "alert_masih_didalam_tindakan_ui";
						}
						else if(id_status.equals("16121"))
						{
							mesej_notifikasi_tindakan = "alert_perlu_tindakan_ui";
						}
						else if(id_status.equals("16125"))
						{
							Date actual_date = new Date();
							Date date_current = formatter.parse(tarikh_kemaskini);
							long duration  = actual_date.getTime() - date_current.getTime();
							long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
							if(diffInSeconds > 86400)//alert appear selepas 24 jam : 86400
							{
								mesej_notifikasi_tindakan = "alert_delete_log_deraf";
							}
						}
					}
					else if(USER_ROLE.equals("wakil_bahagian_aduan"))
					{
						String id_pegawai_bahagian = (rs.getString("ID_PEGAWAI_BAHAGIAN") == null ? "" : rs.getString("ID_PEGAWAI_BAHAGIAN"));
						if(id_status.equals("16127") && id_pegawai_bahagian.equals(USER_ID_SYSTEM))
						{
							mesej_notifikasi_tindakan = "alert_masih_didalam_tindakan_bahagian";
						}
						else if(id_status.equals("16126"))
						{
							mesej_notifikasi_tindakan = "alert_perlu_tindakan_bahagian";
						}
					}
					else
					{
						if(id_status.equals("16125"))
						{
							Date actual_date = new Date();
							Date date_current = formatter.parse(tarikh_kemaskini);
							long duration  = actual_date.getTime() - date_current.getTime();
							long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
							if(diffInSeconds > 86400)//alert appear selepas 24 jam : 86400
							{
								mesej_notifikasi_tindakan = "alert_delete_log_deraf";
							}
						}
					}
				}

				h.put("MESEJ_TINDAKAN",mesej_notifikasi_tindakan);

				String STATUS = rs == null ? "" : (rs.getString("STATUS") == null ? "" : rs.getString("STATUS").toUpperCase());
				if(selectedLanguage.equals("ENGLISH"))
				{
					STATUS = rs == null ? "" : (rs.getString("STATUS_ENG") == null ? "" : rs.getString("STATUS_ENG").toUpperCase());
				}
				h.put("STATUS",STATUS);




				String SUMBER_BAHAGIAN = rs == null ? "" : (rs.getString("SUMBER_BAHAGIAN") == null ? "" : rs.getString("SUMBER_BAHAGIAN"));
				if(selectedLanguage.equals("ENGLISH"))
				{
					SUMBER_BAHAGIAN = rs == null ? "" : (rs.getString("SUMBER_BAHAGIAN_ENG") == null ? "" : rs.getString("SUMBER_BAHAGIAN_ENG"));
				}
				h.put("SUMBER_BAHAGIAN",SUMBER_BAHAGIAN);

				String KATEGORITINDAKAN = rs == null ? "" : (rs.getString("KATEGORITINDAKAN") == null ? "" : rs.getString("KATEGORITINDAKAN"));
				if(selectedLanguage.equals("ENGLISH"))
				{
					KATEGORITINDAKAN = rs == null ? "" : (rs.getString("KATEGORITINDAKAN_ENG") == null ? "" : rs.getString("KATEGORITINDAKAN_ENG").toUpperCase());
				}
				h.put("KATEGORITINDAKAN",KATEGORITINDAKAN);


				String KATEGORIADUAN = rs == null ? "" : (rs.getString("KATEGORIADUAN") == null ? "" : rs.getString("KATEGORIADUAN").toUpperCase());
				if(selectedLanguage.equals("ENGLISH"))
				{
					KATEGORIADUAN = rs == null ? "" : (rs.getString("KATEGORIADUAN_ENG") == null ? "" : rs.getString("KATEGORIADUAN_ENG").toUpperCase());
				}
				h.put("KATEGORIADUAN",KATEGORIADUAN);

				String KATEGORIPERTANYAAN = rs == null ? "" : (rs.getString("KATEGORIPERTANYAAN") == null ? "" : rs.getString("KATEGORIPERTANYAAN").toUpperCase());
				if(selectedLanguage.equals("ENGLISH"))
				{
					KATEGORIPERTANYAAN = rs == null ? "" : (rs.getString("KATEGORIPERTANYAAN_ENG") == null ? "" : rs.getString("KATEGORIPERTANYAAN_ENG").toUpperCase());
				}
				h.put("KATEGORIPERTANYAAN",KATEGORIPERTANYAAN);




				String JENIS_ADUAN = rs == null ? "" : (rs.getString("JENIS_ADUAN") == null ? "" : rs.getString("JENIS_ADUAN").toUpperCase());
				if(selectedLanguage.equals("ENGLISH"))
				{
					JENIS_ADUAN = rs == null ? "" : (rs.getString("JENIS_ADUAN_ENG") == null ? "" : rs.getString("JENIS_ADUAN_ENG").toUpperCase());
				}
				h.put("JENIS_ADUAN",JENIS_ADUAN);



				h.put("DAERAH",rs == null ? "" : (rs.getString("DAERAH") == null ? "" : rs.getString("DAERAH").toUpperCase()));
				h.put("NEGERI",rs == null ? "" : (rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI").toUpperCase()));
				h.put("NEGERI_BAHAGIAN",rs == null ? "" : (rs.getString("NEGERI_BAHAGIAN") == null ? "" : rs.getString("NEGERI_BAHAGIAN").toUpperCase()));
				h.put("FLAG_SEMENTARA",rs == null ? "" : (rs.getString("FLAG_SEMENTARA") == null ? "" : rs.getString("FLAG_SEMENTARA").toUpperCase()));


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return h;
		}

	 public Hashtable getSumber(HttpSession session, String ID_SUMBERADUAN, Db db) throws Exception {
			Db db1 = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			String selectedLanguage = (String) session.getAttribute("selectedLanguage");
			try {
				if(db==null)
				{
					db1 = new Db();
				}
				else
				{
					db1 = db;
				}
				stmt = db1.getStatement();
				Hashtable h;
				h = new Hashtable();
				sql = " SELECT ID_SUMBERADUAN, KOD_SUMBER, NAMA_SUMBER, NAMA_SUMBER_ENG FROM TBLRUJSUMBERESADUAN WHERE ID_SUMBERADUAN = '"+ID_SUMBERADUAN+"'  ORDER BY KOD_SUMBER ";
					myLog.info(" getSumber :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						h.put("ID_SUMBERADUAN",rs.getString("ID_SUMBERADUAN") == null ? "" : rs.getString("ID_SUMBERADUAN"));
						h.put("KOD_SUMBER",rs.getString("KOD_SUMBER") == null ? "" : rs.getString("KOD_SUMBER"));
						String NAMA_SUMBER = rs == null ? "" : (rs.getString("NAMA_SUMBER") == null ? "" : rs.getString("NAMA_SUMBER"));
						if(selectedLanguage.equals("ENGLISH"))
						{
							NAMA_SUMBER = rs == null ? "" : (rs.getString("NAMA_SUMBER_ENG") == null ? "" : rs.getString("NAMA_SUMBER_ENG"));
						}
						h.put("NAMA_SUMBER",NAMA_SUMBER);
					}
				return h;
			} finally {
				if(db==null)
				{
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db1 != null)
						db1.close();
				}
			}
		}

	 public Hashtable getUserOnline(HttpSession session, String USER_ID, Db db) throws Exception {
			Db db1 = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				if(db==null)
				{
					db1 = new Db();
				}
				else
				{
					db1 = db;
				}
				stmt = db1.getStatement();
				Hashtable h;
				h = new Hashtable();
				sql = " SELECT UPPER(U.USER_NAME) AS FULLNAME, UO.EMEL, UO.NO_HP FROM USERS U, USERS_ONLINE UO "+
						" WHERE U.USER_ID = UO.USER_ID AND U.USER_ID = '"+USER_ID+"'  ";
					myLog.info(" getUserOnline :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						h.put("USER_ID",USER_ID);
						h.put("FULLNAME",rs.getString("FULLNAME") == null ? "" : rs.getString("FULLNAME"));
						h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
						h.put("NO_HP",rs.getString("NO_HP") == null ? "" : rs.getString("NO_HP"));
					}

				return h;
			} finally {
				if(db==null)
				{
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db1 != null)
						db1.close();
				}
			}
		}

	 public String sqlAduanPublic(HttpSession session,String FLAG_NOTIFIKASI)
		{

		 myLog.info("FLAG_NOTIFIKASI : "+FLAG_NOTIFIKASI);
			myLog.info("ID_TERIMA_NOTI : "+getParam("ID_TERIMA_NOTI"));
			myLog.info("ID_STATUS_NOTI : "+getParam("ID_STATUS_NOTI"));

			String sql = " SELECT T.ID_PEJABATTANAH, T.ID_NEGERI_PT, T.ID_DAERAH_PT, " +
					" NEGERI_PT.NAMA_NEGERI AS NEGERI_PT, DAERAH_PT.NAMA_DAERAH AS DAERAH_PT, PEJ_PT.NAMA_PEJABAT AS PEJABAT_PT," +
					"T.FLAG_LUAR_TINDAKAN,T.EMEL_BAHAGIAN_1,T.EMEL_BAHAGIAN_2,T.EMEL_BAHAGIAN_3,T.EMEL_BAHAGIAN_4," +
					"T.FLAG_SEMENTARA,T.ID_MASUK, TO_CHAR(T.TARIKH_KEMASKINI,'DD/MM/YYYY HH:MI:SS AM') AS TARIKH_KEMASKINI, T.FLAG_HIDE_INFO,T.ID_ADUANPUBLIC, T.ID_STATUS, T.NO_ADUAN, T.ID_SUMBERADUAN, T.ID_PENGADU, T.ID_PEGAWAI_UI, "+
					" T.ID_PEGAWAI_BAHAGIAN, TO_CHAR(T.TARIKH_ADUAN,'DD/MM/YYYY') AS TARIKH_ADUAN, " +
					" TO_CHAR(T.TARIKH_TERIMA_ADUAN_UI,'DD/MM/YYYY') AS TARIKH_TERIMA_ADUAN_UI,  "+
					" TO_CHAR(T.TARIKH_TERIMA_ADUAN_BAHAGIAN,'DD/MM/YYYY') AS TARIKH_TERIMA_ADUAN_BAHAGIAN, TO_CHAR(T.TARIKH_ADUAN_SELESAI,'DD/MM/YYYY') AS TARIKH_ADUAN_SELESAI, " +
					" (CASE WHEN T.ID_PENGADU IS NULL OR T.ID_PENGADU = '' THEN UPPER(T.NAMA_PENGADU) ELSE  (USERONLINE.NAMA_PENGADU) END) AS NAMA_PENGADU, " +
					" (CASE WHEN T.ID_PENGADU IS NULL OR T.ID_PENGADU = '' THEN (T.EMEL_PENGADU) ELSE (USERONLINE.EMEL) END) AS EMEL_PENGADU, " +
					" (CASE WHEN T.ID_PENGADU IS NULL OR T.ID_PENGADU = '' THEN UPPER(T.TEL_PENGADU) ELSE UPPER (USERONLINE.NO_HP ) END) AS TEL_PENGADU,   " +
					" (CASE WHEN T.ID_PEGAWAI_BAHAGIAN IS NULL OR T.ID_PEGAWAI_BAHAGIAN = '' THEN UPPER(T.NAMA_PEGAWAI_BAHAGIAN) ELSE UPPER (USERBAHAGIAN.NAMA_PEGAWAI_BAHAGIAN) END) AS NAMA_PEGAWAI_BAHAGIAN, " +
					" (CASE WHEN T.ID_PEGAWAI_BAHAGIAN IS NULL OR T.ID_PEGAWAI_BAHAGIAN = '' THEN (T.EMEL_PEGAWAI_BAHAGIAN) ELSE (USERBAHAGIAN.EMEL) END) AS EMEL_PEGAWAI_BAHAGIAN, " +
					" (CASE WHEN T.ID_PEGAWAI_BAHAGIAN IS NULL OR T.ID_PEGAWAI_BAHAGIAN = '' THEN UPPER(T.TEL_PEGAWAI_BAHAGIAN) ELSE UPPER (USERBAHAGIAN.NO_TEL) END) AS TEL_PEGAWAI_BAHAGIAN,  " +
					" T.ID_JENISADUAN,JA.JENIS_ADUAN, JA.JENIS_ADUAN_ENG,  "+
					" T.ID_KATEGORIADUAN, KAT.KATEGORIADUAN, KAT.KATEGORIADUAN_ENG, " +
					" T.ID_KATEGORIPERTANYAAN, KATP.KATEGORIPERTANYAAN, KATP.KATEGORIPERTANYAAN_ENG, " +
					" T.JAM, T.MINIT, T.JENIS_WAKTU, T.SUBJEK_ADUAN, " +
					" TO_CHAR(T.TARIKH_KEJADIAN,'DD/MM/YYYY') AS TARIKH_KEJADIAN, T.LOKASI_KEJADIAN, BAHAGIAN.NAMA_SEKSYEN AS NAMA_BAHAGIAN, "+
					" T.ID_NEGERI, T.ID_NEGERI_BAHAGIAN, T.ID_DAERAH, T.NAMA_PIHAK_TERLIBAT,  "+
					" T.KETERANGAN_ADUAN, T.MAKLUMBALAS_UI, T.MAKLUMBALAS_BAHAGIAN, " +
					" T.ID_JENISTINDAKAN,  "+
					" T.ID_BAHAGIAN, T.ID_SUMBERTINDAKAN, " +
					" UPPER(UUI.USER_NAME) AS PEGAWAI_UI,UIUI.EMEL AS EMEL_PEGAWAI_UI, PEJUI.NO_TEL AS NO_TEL_PEGAWAI_UI, "+
					" UPPER(STA.KETERANGAN) AS STATUS, UPPER(STA.KETERANGAN_ENG) AS STATUS_ENG, "+
					" (SUM_PENGADU.NAMA_SUMBER) AS SUMBER_PENGADU,(SUM_PENGADU.NAMA_SUMBER_ENG) AS SUMBER_PENGADU_ENG, "+
					" (SUM_BAHAGIAN.NAMA_SUMBER) AS SUMBER_BAHAGIAN, (SUM_BAHAGIAN.NAMA_SUMBER_ENG) AS SUMBER_BAHAGIAN_ENG, "+
					" UPPER(KATTINDAKAN.KATEGORITINDAKAN) AS KATEGORITINDAKAN, UPPER(KATTINDAKAN.KATEGORITINDAKAN_ENG) AS KATEGORITINDAKAN_ENG, "+
					" UPPER(DAERAH.NAMA_DAERAH) AS DAERAH, UPPER(NEGERI.NAMA_NEGERI) AS NEGERI, UPPER(NEGERI_BAHAGIAN.NAMA_NEGERI) AS NEGERI_BAHAGIAN "+
					" FROM TBLADUANPUBLIC T, ";
					if(FLAG_NOTIFIKASI.equals("Y"))
					{
						sql += " TBLADUANPUBLICNOTIFIKASI NOTIF,";
					}
					sql += "USERS UUI,USERS_INTERNAL UIUI,TBLRUJJENISESADUAN JA, TBLRUJSTATUSADUANPUBLIC STA,TBLRUJSUMBERESADUAN SUM_PENGADU,TBLRUJSUMBERESADUAN SUM_BAHAGIAN,TBLRUJSEKSYEN BAHAGIAN, "+
					" TBLRUJKATEGORIADUANPUBLIC KAT,TBLRUJKATEGORIPERTANYAAN KATP,TBLRUJKATEGORITINDAKAN KATTINDAKAN, " +
					" TBLRUJNEGERI NEGERI, TBLRUJNEGERI NEGERI_BAHAGIAN, TBLRUJDAERAH DAERAH, "+
					" TBLRUJNEGERI NEGERI_PT, TBLRUJDAERAH DAERAH_PT, TBLRUJPEJABAT PEJ_PT, TBLRUJPEJABATJKPTG PEJUI,"+
					" (SELECT SU.USER_ID, UPPER(SU.USER_NAME) AS NAMA_PENGADU, SUO.NO_HP, SUO.EMEL FROM USERS SU, USERS_ONLINE SUO WHERE SU.USER_ID = SUO.USER_ID) USERONLINE, "+
					" (SELECT SU2.USER_ID, UPPER(SU2.USER_NAME) AS NAMA_PEGAWAI_BAHAGIAN, PEJ.NO_TEL, SUI.EMEL FROM USERS SU2, USERS_INTERNAL SUI, TBLRUJPEJABATJKPTG PEJ  "+
					" WHERE SU2.USER_ID = SUI.USER_ID AND SUI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG) USERBAHAGIAN  "+
					" WHERE T.ID_ADUANPUBLIC IS NOT NULL  AND UIUI.ID_PEJABATJKPTG = PEJUI.ID_PEJABATJKPTG(+) AND T.ID_PENGADU = USERONLINE.USER_ID(+)  AND T.ID_JENISADUAN = JA.ID_JENISADUAN(+) "+
					" AND T.ID_PEGAWAI_BAHAGIAN = USERBAHAGIAN.USER_ID(+) AND T.ID_PEGAWAI_UI = UUI.USER_ID(+) AND UUI.USER_ID = UIUI.USER_ID(+) "+
					" AND T.ID_PEJABATTANAH = PEJ_PT.ID_PEJABAT(+) AND T.ID_NEGERI_PT = NEGERI_PT.ID_NEGERI(+) AND T.ID_DAERAH_PT = DAERAH_PT.ID_DAERAH(+)  "+
					" AND T.ID_STATUS = STA.ID_STATUS(+) AND T.ID_SUMBERADUAN = SUM_PENGADU.ID_SUMBERADUAN(+)  "+
					" AND T.ID_SUMBERTINDAKAN = SUM_BAHAGIAN.ID_SUMBERADUAN(+) AND T.ID_BAHAGIAN = BAHAGIAN.ID_SEKSYEN(+)  ";

					if(FLAG_NOTIFIKASI.equals("Y"))
					{
						String ID_TERIMA_NOTI = getParam("ID_TERIMA_NOTI");
						String ID_STATUS_NOTI = getParam("ID_STATUS_NOTI");
						sql += " AND NOTIF.ID_ADUANPUBLIC = T.ID_ADUANPUBLIC AND NOTIF.ID_STATUS =  T.ID_STATUS " +
							" AND T.ID_STATUS = '"+ID_STATUS_NOTI+"' " +
							" AND NOTIF.ID_TERIMA= '"+ID_TERIMA_NOTI+"' " +
							" AND NOTIF.FLAG_READ = 'N'   ";
					}

					sql += " AND T.ID_KATEGORIADUAN = KAT.ID_KATEGORIADUANPUBLIC(+) AND T.ID_KATEGORIPERTANYAAN = KATP.ID_KATEGORIPERTANYAAN(+) AND T.ID_JENISTINDAKAN = KATTINDAKAN.ID_KATEGORITINDAKAN(+)  "+
					" AND T.ID_DAERAH = DAERAH.ID_DAERAH(+) AND T.ID_NEGERI = NEGERI.ID_NEGERI(+) AND T.ID_NEGERI_BAHAGIAN = NEGERI_BAHAGIAN.ID_NEGERI(+)  ";
			return sql;
		}


	 public void saveTableNotifikasi(HttpSession session,String ID_ADUANPUBLIC,String ID_HANTAR,String ID_TERIMA, String ID_STATUS,Db db,String flag) throws Exception {
			Db db1 = null;
			String sql = "";
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");

			try {

				if(db==null)
				{
					db1 = new Db();
				}
				else
				{
					db1 = db;
				}

				Statement stmt = db1.getStatement();
				SQLRenderer r = new SQLRenderer();


				if(flag.equals("insert"))
				{
					r.clear();
					r.add("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
					r.add("FLAG_READ", "N");
					r.add("ID_HANTAR", ID_HANTAR);
					r.add("ID_TERIMA", ID_TERIMA);
					r.add("ID_STATUS", ID_STATUS);
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					sql = r.getSQLInsert("TBLADUANPUBLICNOTIFIKASI");
				}
				else if(flag.equals("update"))
				{
					//String id_current_status = getCurrentStatus(session, ID_ADUANPUBLIC, db1);
					r.clear();
					r.update("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
					r.update("FLAG_READ", "N");
					if(ID_STATUS.equals("16127") || ID_STATUS.equals("16122") || ID_STATUS.equals("16123"))
					{
						r.update("ID_TERIMA", USER_ID_SYSTEM);
					}
					r.add("FLAG_READ", "Y");
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					sql = r.getSQLUpdate("TBLADUANPUBLICNOTIFIKASI");
				}
				/*
				else if(flag.equals("flagNotifikasi"))
				{
					r.clear();
					r.update("FLAG_READ", "N");
					r.update("ID_TERIMA", ID_TERIMA);
					r.update("ID_STATUS", ID_STATUS);
					r.add("FLAG_READ", "Y");
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					sql = r.getSQLUpdate("TBLADUANPUBLICNOTIFIKASI");
				}
				*/
				myLog.info("ADD TBLADUANPUBLICNOTIFIKASI : "+sql);
				stmt.executeUpdate(sql);

			}
			catch (Exception re) {
				throw re;
			}finally {
				if(db==null)
				{
					if (db1 != null)
						db1.close();
				}
			}
		}

	 public List listPegawaiUI(HttpSession session, Db db)throws Exception {
			Db db1 = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listPegawaiUI = null;
			String sql = "";
			try {
				if(db == null)
				{
					db1 = new Db();
				}
				else
				{
					db1 = db;
				}
				stmt = db1.getStatement();

				sql = " SELECT DISTINCT U.USER_ID,UI.EMEL, U.USER_NAME FROM USERS U, USERS_INTERNAL UI, (  "+
						" SELECT ROLE_ID, USER_ID FROM USER_ROLE WHERE ROLE_ID = 'user_unit_intergriti'   "+
						" ) UR WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = UR.USER_ID(+)   "+
						" AND (U.USER_ROLE = 'user_unit_intergriti' OR UR.ROLE_ID = 'user_unit_intergriti' )  ";

				myLog.info(" SQL : listPegawaiUI :"+ sql);
				rs = stmt.executeQuery(sql);
				listPegawaiUI = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
					h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
					h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
					listPegawaiUI.add(h);
				}

			} finally {
				if(db == null)
				{
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db1 != null)
						db1.close();
				}
			}
			return listPegawaiUI;
		}


	 public List listPegawaiBahagian(HttpSession session,String ID_BAHAGIAN,String ID_NEGERI, Db db)throws Exception {
			Db db1 = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listPegawaiBahagian = null;
			String sql = "";
			try {
				if(db == null)
				{
					db1 = new Db();
				}
				else
				{
					db1 = db;
				}
				stmt = db1.getStatement();

				sql = " SELECT DISTINCT U.USER_ID,UI.EMEL, U.USER_NAME FROM USERS U, USERS_INTERNAL UI, (  "+
						" SELECT ROLE_ID, USER_ID FROM USER_ROLE WHERE ROLE_ID = 'wakil_bahagian_aduan'  "+
						" ) UR  "+
						" WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = UR.USER_ID(+)   "+
						" AND (U.USER_ROLE = 'wakil_bahagian_aduan' OR UR.ROLE_ID = 'wakil_bahagian_aduan') "+
						" AND UI.ID_NEGERI = '"+ID_NEGERI+"' AND UI.ID_SEKSYEN = '"+ID_BAHAGIAN+"'  ";

				myLog.info(" SQL : listPegawaiBahagian :"+ sql);
				rs = stmt.executeQuery(sql);
				listPegawaiBahagian = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
					h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
					h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
					listPegawaiBahagian.add(h);
				}

			} finally {
				if(db == null)
				{
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db1 != null)
						db1.close();
				}
			}
			return listPegawaiBahagian;
		}

	 public String returnHTMLMaklumatAduan(HttpSession session,Map MaklumatAduan,Db db)
	 {
	 	String selectedLanguage = (String) session.getAttribute("selectedLanguage");

	 	Map view = MaklumatAduan;
	 	String SUMBER_PENGADU = (String)view.get("SUMBER_PENGADU");
			String ID_STATUS = (String)view.get("ID_STATUS");
			String ID_SUMBERTINDAKAN = (String)view.get("ID_SUMBERTINDAKAN");
			String STATUS = (String)view.get("STATUS");
			String TARIKH_ADUAN = (String)view.get("TARIKH_ADUAN");
			String TARIKH_ADUAN_SELESAI = (String)view.get("TARIKH_ADUAN_SELESAI");
			String NO_ADUAN = (String)view.get("NO_ADUAN");
			String JENIS_ADUAN = (String)view.get("JENIS_ADUAN");
			String FLAG_HIDE_INFO = (String)view.get("FLAG_HIDE_INFO");
			String EMEL_PENGADU = (String)view.get("EMEL_PENGADU");
			String TEL_PENGADU = (String)view.get("TEL_PENGADU");
			String NAMA_PENGADU = (String)view.get("NAMA_PENGADU");
			String ID_JENISADUAN = (String)view.get("ID_JENISADUAN");
			String KATEGORIADUAN = (String)view.get("KATEGORIADUAN");
			String SUBJEK_ADUAN = (String)view.get("SUBJEK_ADUAN");
			String TARIKH_KEJADIAN = (String)view.get("TARIKH_KEJADIAN");
			String JAM = (String)view.get("JAM");
			String MINIT = (String)view.get("MINIT");
			String JENIS_WAKTU = (String)view.get("JENIS_WAKTU");
			String LOKASI_KEJADIAN = (String)view.get("LOKASI_KEJADIAN");
			String NEGERI = (String)view.get("NEGERI");
			String DAERAH = (String)view.get("DAERAH");
			String NAMA_PIHAK_TERLIBAT = (String)view.get("NAMA_PIHAK_TERLIBAT");
			String KETERANGAN_ADUAN = (String)view.get("KETERANGAN_ADUAN");

			String KATEGORITINDAKAN = (String)view.get("KATEGORITINDAKAN");
			String NEGERI_BAHAGIAN = (String)view.get("NEGERI_BAHAGIAN");
			String NAMA_BAHAGIAN = (String)view.get("NAMA_BAHAGIAN");
			String SUMBER_BAHAGIAN = (String)view.get("SUMBER_BAHAGIAN");
			String MAKLUMBALAS_UI = (String)view.get("MAKLUMBALAS_UI");

			String EMEL_BAHAGIAN_1 = (String)view.get("EMEL_BAHAGIAN_1");
			String EMEL_BAHAGIAN_2 = (String)view.get("EMEL_BAHAGIAN_2");
			String EMEL_BAHAGIAN_3 = (String)view.get("EMEL_BAHAGIAN_3");
			String EMEL_BAHAGIAN_4 = (String)view.get("EMEL_BAHAGIAN_4");

			String NEGERI_PT = (String)view.get("NEGERI_PT");
			String DAERAH_PT = (String)view.get("DAERAH_PT");
			String PEJABAT_PT = (String)view.get("PEJABAT_PT");


			/*
			h.put("LUAR_TINDAKAN",LUAR_TINDAKAN);


		h.put("ID_PEJABATTANAH",rs == null ? "" : (rs.getString("ID_PEJABATTANAH") == null ? "" : rs.getString("ID_PEJABATTANAH").toUpperCase()));
		h.put("ID_NEGERI_PT",rs == null ? "" : (rs.getString("ID_NEGERI_PT") == null ? "" : rs.getString("ID_NEGERI_PT").toUpperCase()));
		h.put("ID_DAERAH_PT",rs == null ? "" : (rs.getString("ID_DAERAH_PT") == null ? "" : rs.getString("ID_DAERAH_PT").toUpperCase()));
		h.put("NEGERI_PT",rs == null ? "" : (rs.getString("NEGERI_PT") == null ? "" : rs.getString("NEGERI_PT").toUpperCase()));
		h.put("DAERAH_PT",rs == null ? "" : (rs.getString("DAERAH_PT") == null ? "" : rs.getString("DAERAH_PT").toUpperCase()));
		h.put("PEJABAT_PT",rs == null ? "" : (rs.getString("PEJABAT_PT") == null ? "" : rs.getString("PEJABAT_PT").toUpperCase()));

			*/

			String FLAG_LUAR_TINDAKAN = (String)view.get("FLAG_LUAR_TINDAKAN");

			String MAKLUMBALAS_BAHAGIAN = (String)view.get("MAKLUMBALAS_BAHAGIAN");

	 	String maklumat = "";
	 	maklumat += "<table width=\"100%\" border=\"0\">";
	 	//maklumat pengadu
	 	maklumat += returnTDTR(session,"legend_pengadu","","tajuk");
	 	maklumat += returnTDTR(session,"label_sumber_pengadu",SUMBER_PENGADU,"content");
	 	if(FLAG_HIDE_INFO.equals("1"))
	 	{
		    	maklumat += returnTDTR(session,"label_nama_pengadu",returnTDTR(session,"label_maklumat_dilindungi","","justConvert"),"content");
		    	maklumat += returnTDTR(session,"label_tel_pengadu",returnTDTR(session,"label_maklumat_dilindungi","","justConvert"),"content");
		    	maklumat += returnTDTR(session,"label_emel_pengadu",returnTDTR(session,"label_maklumat_dilindungi","","justConvert"),"content");
	 	}
	 	else
	 	{
	 		maklumat += returnTDTR(session,"label_nama_pengadu",NAMA_PENGADU,"content");
	 		if(!TEL_PENGADU.equals(""))
	 		{
	 			maklumat += returnTDTR(session,"label_tel_pengadu",TEL_PENGADU,"content");
	 		}
	 		if(!EMEL_PENGADU.equals(""))
	 		{
	 			maklumat += returnTDTR(session,"label_emel_pengadu",EMEL_PENGADU+";","content");
	 		}
	 	}

	 	//maklumat aduan
	 	maklumat += returnTDTR(session,"legend_form_aduan_edit","","tajuk");
	 	maklumat += returnTDTR(session,"label_status_aduan",STATUS,"content");
	 	maklumat += returnTDTR(session,"label_tarikh_aduan",TARIKH_ADUAN,"content");
	 	if(!TARIKH_ADUAN_SELESAI.equals(""))
	 	{
	 		maklumat += returnTDTR(session,"label_tarikh_aduan_selesai",TARIKH_ADUAN_SELESAI,"content");
	 	}
	 	maklumat += returnTDTR(session,"label_no_aduan",NO_ADUAN,"content");
	 	maklumat += returnTDTR(session,"label_jenis_aduan",JENIS_ADUAN,"content");
	 	//maklumat += returnTDTR(session,"label_hide_personal_info",FLAG_HIDE_INFO,"content");

	 	if(!ID_JENISADUAN.equals(""))
	 	{
		    	if(ID_JENISADUAN.equals("16101"))
		    	{
		    		//content aduan
		    		maklumat += returnTDTR(session,"","","tajuk");
		    		if(!KATEGORIADUAN.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_kategori_aduan",KATEGORIADUAN,"content");
		    		}
		    		if(!SUBJEK_ADUAN.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_subjek",SUBJEK_ADUAN,"content");
		    		}
		    		if(!TARIKH_KEJADIAN.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_tarikh_kejadian",TARIKH_KEJADIAN,"content");
		    		}
		    		if(!JAM.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_masa_kejadian",JAM+":"+MINIT+" "+JENIS_WAKTU,"content");
		    		}
		    		if(!LOKASI_KEJADIAN.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_lokasi_kejadian",LOKASI_KEJADIAN,"content");
		    		}
		    		if(!NEGERI.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_negeri",NEGERI,"content");
		    		}
		    		if(!DAERAH.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_daerah",DAERAH,"content");
		    		}
		    		if(!NAMA_PIHAK_TERLIBAT.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_pihak_telibat",NAMA_PIHAK_TERLIBAT,"content");
		    		}
		    		maklumat += returnTDTR(session,"label_keterangan_aduan",KETERANGAN_ADUAN,"content");
		    	}
		    	else
		    	{
		    		maklumat += returnTDTR(session,"","","tajuk");
		    		maklumat += returnTDTR(session,"label_keterangan_cadangan",KETERANGAN_ADUAN,"content");
		    	}
	 	}

	 	if(ID_STATUS.equals("16123"))
	 	{

	 		if(!ID_SUMBERTINDAKAN.equals("16101"))
	 		{
			    	//maklumat skrin UI
			    	maklumat += returnTDTR(session,"legend_form_tindakan_ui","","tajuk");
		    		if(!KATEGORITINDAKAN.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_jenis_tindakan_aduan",KATEGORITINDAKAN,"content");
		    		}


		    		if(!NEGERI_PT.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_negeri_pt",NEGERI_PT,"content");
		    		}
		    		if(!DAERAH_PT.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_daerah_pt",DAERAH_PT,"content");
		    		}
		    		if(!PEJABAT_PT.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_pejabat_pt",PEJABAT_PT,"content");
		    		}


		    		if(!NEGERI_BAHAGIAN.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_negeri_bahagian",NEGERI_BAHAGIAN,"content");
		    		}
		    		if(!NAMA_BAHAGIAN.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_tindakan_bahagian",NAMA_BAHAGIAN,"content");
		    		}
		    		if(!NAMA_BAHAGIAN.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_tindakan_bahagian",NAMA_BAHAGIAN,"content");
		    		}

		    		if(!EMEL_BAHAGIAN_1.equals("") || !EMEL_BAHAGIAN_2.equals("") || !EMEL_BAHAGIAN_3.equals("") || !EMEL_BAHAGIAN_4.equals(""))
		    		{
		    			String listEmel = "";
		    			if(!EMEL_BAHAGIAN_1.equals(""))
		    			{
		    				listEmel += EMEL_BAHAGIAN_1+"; ";
		    			}
		    			if(!EMEL_BAHAGIAN_2.equals(""))
		    			{
		    				listEmel += EMEL_BAHAGIAN_2+"; ";
		    			}
		    			if(!EMEL_BAHAGIAN_3.equals(""))
		    			{
		    				listEmel += EMEL_BAHAGIAN_3+"; ";
		    			}
		    			if(!EMEL_BAHAGIAN_4.equals(""))
		    			{
		    				listEmel += EMEL_BAHAGIAN_4+"; ";
		    			}
		    			maklumat += returnTDTR(session,"label_emel_bahagian",listEmel,"content");
		    		}

		    		if(!SUMBER_BAHAGIAN.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_sumber_bahagian",SUMBER_BAHAGIAN,"content");
		    		}

		    		String LUAR_TINDAKAN = "";
		    		if(FLAG_LUAR_TINDAKAN.equals("Y"))
		    		{
				    	if(selectedLanguage.equals("ENGLISH"))
			      		{
				    		LUAR_TINDAKAN = "YES";
			      		}
			      		else
			      		{
			      			LUAR_TINDAKAN = "YA";
			      		}
		    		}
		    		else
		    		{
		    			if(selectedLanguage.equals("ENGLISH"))
			      		{
				    		LUAR_TINDAKAN = "NO";
			      		}
			      		else
			      		{
			      			LUAR_TINDAKAN = "TIDAK";
			      		}
		    		}
		    		maklumat += returnTDTR(session,"label_luar_tindakan",LUAR_TINDAKAN,"content");






		    		if(!MAKLUMBALAS_UI.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_keterangan_ui",MAKLUMBALAS_UI,"content");
		    		}
	 		}
	 		else
	 		{
		    		//maklumat skrin bahagian
		    		maklumat += returnTDTR(session,"legend_form_tindakan_bahagian","","tajuk");
		    		if(!MAKLUMBALAS_BAHAGIAN.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_keterangan_bahagian",MAKLUMBALAS_BAHAGIAN,"content");
		    		}
	 		}

	 	}

	 	maklumat += "</table>";
	 	return maklumat;
	 }

	 public String returnTDTR(HttpSession session,String label,String Content,String type)
	 {
	 	if(!label.equals(""))
	 	{
		    	String selectedLanguage = (String) session.getAttribute("selectedLanguage");
		    	ResourceBundle rb_lang = null;
				Enumeration bundleKeys = null;
				if(selectedLanguage.equals("ENGLISH"))
	   		{
	   			rb_lang = ResourceBundle.getBundle("eng_lang");
	   		}
	   		else
	   		{
	   			rb_lang = ResourceBundle.getBundle("malay_lang");
	   		}

	   		if(rb_lang!=null)
	   		{
	   			bundleKeys = rb_lang.getKeys();
	   			while (bundleKeys.hasMoreElements()) {
	   			    String key = (String)bundleKeys.nextElement();
	   			    String value = rb_lang.getString(key);
	   			    //myLogger.info("key : "+key+" value : "+value);
	   			    context.put(key,value);
	   			    if(label.equals(key))
	   			    {
	   			    	label = value;
	   			    	break;
	   			    }
	   			}
	   		}
	 	}


	 	String html = "";
	 	if(type.equals("content"))
	 	{
		    	html += "<tr>" +
		    			"<td valign=\"top\"  width=\"1%\"></td>" +
		    			"<td valign=\"top\"  width=\"28%\">"+label.toUpperCase()+"</td>" +
		    			"<td valign=\"top\"  width=\"1%\">:</td>" +
		    			"<td valign=\"top\"  width=\"70%\">"+Content+"</td>" +
		    			"</tr>";
	 	}
	 	else if(type.equals("tajuk"))
	 	{
	 		html += "<tr>" +
	 	            "<td colspan=\"4\" style=\"border-bottom: 1px solid black;\"><b>"+label.toUpperCase()+"</b></td> "+
		    			"</tr>";
	 	}
	 	else if(type.equals("justConvert"))
	 	{
	 		html += label.toUpperCase();
	 	}
	 	return html;
	 }

	private void uploadFiles(String idAduan) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		InputStream uploadData = null;
		String uploadName = "";
		String uploadType = "";
		long uploadSize = 0;

		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
		FileItem item = (FileItem)itr.next();
			if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
				saveData(item, idAduan);
			}
		}
	}

	private void saveData(FileItem item, String idAduan) throws Exception {
//		HttpSession session = request.getSession();
		Db db = null;
		try {
			long id_inboxattach = DB.getNextID("TBLONLINELAMPIRAN_SEQ");
			db = new Db();
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			SQLRenderer r = new SQLRenderer();
			PreparedStatement ps = con.prepareStatement("INSERT INTO TBLONLINELAMPIRAN " +
			"(ID_ONLINELAMPIRAN,ID_REKOD,REFERENCETABLE,NAMA_FAIL,JENIS_MIME,CONTENT,ID_MASUK,ID_KEMASKINI,TARIKH_MASUK,TARIKH_KEMASKINI) " +
			"VALUES(?,?,?,?,?,?,?,?,"+r.unquote("sysdate")+","+r.unquote("sysdate")+")");

			ps.setLong(1, id_inboxattach);
			ps.setLong(2, Long.parseLong(idAduan));
			ps.setString(3, "TBLONLINEEADUAN");
			ps.setString(4, item.getName());
			ps.setString(5, item.getContentType());
			ps.setBinaryStream(6, item.getInputStream(),(int)item.getSize());
			ps.setString(7, (String) session.getAttribute("_ekptg_user_id"));
			ps.setString(8, (String) session.getAttribute("_ekptg_user_id"));
			ps.executeUpdate();

			con.commit();
		} finally {
			if (db != null) db.close();
		}
	}

	private void simpanDraf() {
		String idComplaint = getParam("idComplaint");
		String jawapan = getParam("ulasanBalas");
		String idRespon = getParam("idRespon");
		ComplaintResponse response = new ComplaintResponse();
		Complaint complaint = new Complaint();
		complaint.setId(Long.parseLong(idComplaint));
		response.setComplaint(complaint);
		response.setIdMasuk(userId);
		response.setId(Long.parseLong(idComplaint));
		response.setJawapan(jawapan);
		response.setResponseStatus(ResponseStatus.DRAF);
		response.setId(Long.parseLong(idRespon));
		getRespone().doResponse(response);
		response = getRespone().getResponse(idRespon);
		context.put("response",response);

	}

	private void simpanComplaint() {
		String idComplaint = getParam("idComplaint");
		String status = getParam("tindakan");
		String ulasan = getParam("ulasanBalas");
		String negeri = getParam("idNegeri");
		String catatanSelesai = getParam("catatanSelesai");
		Complaint complaint = getHandler().getComplaint(idComplaint);
		complaint.setStatus(status);
		complaint.setIdPegawai(userId);
		complaint.setUlasanBalas(ulasan);
		complaint.setCatatanSelesai(catatanSelesai);
		getHandler().processComplaint(complaint);

	}
	private void viewComplaint()throws Exception{//masuk sini dr depan
		String idRespon = getParam("idRespon");
		String idPengadu = getParam("idPengadu");
		System.out.println("idPengadu >>>> "+idPengadu);

		Db db = null;
		try {
			db = new Db();
			aduanDetails = aduanDetails(idRespon,idPengadu,session,db);
		}
		catch (Exception ex) {
		throw new DbException(ex.getMessage());
		}
		finally {
			if (db != null)
				db.close();
		}
		context.put("complaints", aduanDetails);
		context.put("editable", false);
	}
	private void displayComplaint()throws Exception{
		Db db = null;
		try {
			db = new Db();
			listAduan = listAduan(userId,session,db);
		}
		catch (Exception ex) {
		throw new DbException(ex.getMessage());
		}
		finally {
			if (db != null)
				db.close();
		}
		context.put("lists", listAduan);

	}

	@SuppressWarnings("unchecked")
	public List listAduan(String USER_ID,HttpSession session,Db db)throws Exception {
		Db db1 = null;

		ResultSet rs = null;
		Statement stmt = null;
		List listJenisAduan = null;
		String sql = "";
		try {
			if(db==null)
			{
			db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			stmt = db1.getStatement();
			sql = "SELECT UPPER(aduan.NAMA_PENGADU) AS FULLNAME,aduan.NO_ADUAN,aduan.ID_PENGADU,tanah.NO_HAKMILIK,aduan.ID_ADUANPUBLIC FROM TBLHTPHAKMILIKADUAN tanah, TBLADUANPUBLIC aduan "+
					" WHERE tanah.ID_ADUAN = aduan.ID_ADUANPUBLIC AND tanah.ID_MASUK = '"+USER_ID+"'  ";
			myLog.info(" ADUAN : SQL listJenisAduan :"+ sql);
			rs = stmt.executeQuery(sql);
			listJenisAduan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID_ADUANPUBLIC") == null ? "" : rs.getString("ID_ADUANPUBLIC").toUpperCase());
				h.put("FULLNAME",rs.getString("FULLNAME") == null ? "" : rs.getString("FULLNAME").toUpperCase());
				h.put("NO_ADUAN",rs.getString("NO_ADUAN") == null ? "" : rs.getString("NO_ADUAN").toUpperCase());
				h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_PENGADU",rs.getString("ID_PENGADU") == null ? "" : rs.getString("ID_PENGADU"));
				listJenisAduan.add(h);
			}

		} finally {
			if(db==null)
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db1 != null)
					db1.close();
			}
		}
		return listJenisAduan;
	}

	@SuppressWarnings("unchecked")
	public List aduanDetails(String idAduan,String USER_ID,HttpSession session,Db db)throws Exception {
		Db db1 = null;

		ResultSet rs = null;
		Statement stmt = null;
		List listJenisAduan = null;
		String sql = "";
		try {
			if(db==null)
			{
			db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			stmt = db1.getStatement();
			sql = " SELECT UI.ID_SEKSYEN,U.USER_ID,U.USER_LOGIN,INITCAP(U.USER_NAME) AS USER_NAME_INIT,U.USER_NAME,U.USER_ROLE,S.NAMA_SEKSYEN," +
					" PEJ.NAMA_PEJABAT,N.KOD_NEGERI,N.NAMA_NEGERI,UI.ID_NEGERI,UI.ID_PEJABATJKPTG,D.NAMA_DAERAH,UI.EMEL,PEJ.NO_TEL,J.KETERANGAN AS NAMA_JAWATAN," +
					" k.NAMA_KEMENTERIAN,a.NAMA_AGENSI,aduan.ID_ADUANPUBLIC,aduan.ID_STATUS,aduan.NO_ADUAN,aduan.ID_PENGADU,aduan.TARIKH_ADUAN,aduan.NO_FAIL," +
					" aduan.KETERANGAN_ADUAN,tanah.ID_HAKMILIKADUAN,tanah.ID_ADUAN,tanah.ID_LUAS,tanah.NO_HAKMILIK,tanah.NO_WARTA,tanah.TARIKH_WARTA,tanah.NO_LOT," +
					" tanah.LUAS,tanah.ID_DAERAH,tanah.ID_NEGERI,tanah.ID_MUKIM,tanah.ID_LOT,tanah.ID_JENISHAKMILIK,N1.NAMA_NEGERI AS NAMA_NEGERITANAH," +
					" D1.NAMA_DAERAH AS NAMA_DAERAHTANAH,M.NAMA_MUKIM AS NAMA_MUKIMTANAH"+
					" FROM USERS U,USERS_INTERNAL UI,TBLRUJSEKSYEN S,TBLRUJJAWATAN J,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ," +
					" TBLRUJDAERAH D,USERS_KEMENTERIAN UK,TBLRUJKEMENTERIAN K,TBLRUJAGENSI A,TBLADUANPUBLIC aduan,TBLHTPHAKMILIKADUAN tanah,TBLRUJNEGERI N1," +
					" TBLRUJDAERAH D1,TBLRUJMUKIM M"+
					" WHERE U.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) AND UI.USER_ID = UK.USER_ID(+) AND UK.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+)" +
					" AND tanah.ID_NEGERI = N1.ID_NEGERI(+) AND tanah.ID_DAERAH = D1.ID_DAERAH(+) AND tanah.ID_MUKIM = M.ID_MUKIM(+)" +
					" AND UK.ID_AGENSI = A.ID_AGENSI(+) AND UI.ID_SEKSYEN = S.ID_SEKSYEN(+) AND UI.ID_NEGERI = N.ID_NEGERI(+) AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+)" +
					" AND UI.ID_DAERAH = D.ID_DAERAH(+)AND UI.USER_ID = '"+USER_ID+"' "+
					" AND aduan.ID_ADUANPUBLIC = '"+idAduan+"' "+
					" AND tanah.ID_ADUAN = '"+idAduan+"'  ";
			myLog.info(" ADUAN : SQL aduanDetails :"+ sql);
			rs = stmt.executeQuery(sql);
			listJenisAduan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID_ADUANPUBLIC") == null ? "" : rs.getString("ID_ADUANPUBLIC"));
				h.put("NO_ADUAN",rs.getString("NO_ADUAN") == null ? "" : rs.getString("NO_ADUAN").toUpperCase());
				h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_HAKMILIKADUAN",rs.getString("ID_HAKMILIKADUAN") == null ? "" : rs.getString("ID_HAKMILIKADUAN"));
				h.put("ID_ADUAN",rs.getString("ID_ADUAN") == null ? "" : rs.getString("ID_ADUAN"));
				h.put("ID_LUAS",rs.getString("ID_LUAS") == null ? "" : rs.getString("ID_LUAS"));
				h.put("NO_WARTA",rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("TARIKH_WARTA",rs.getString("TARIKH_WARTA") == null ? "" : rs.getString("TARIKH_WARTA"));
				h.put("NO_LOT",rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("LUAS",rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));
				h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_MUKIM",rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("ID_LOT",rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				h.put("ID_JENISHAKMILIK",rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));
				h.put("ID_ADUANPUBLIC",rs.getString("ID_ADUANPUBLIC") == null ? "" : rs.getString("ID_ADUANPUBLIC"));
				h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("NO_ADUAN",rs.getString("NO_ADUAN") == null ? "" : rs.getString("NO_ADUAN"));
				h.put("ID_PENGADU",rs.getString("ID_PENGADU") == null ? "" : rs.getString("ID_PENGADU"));
				h.put("TARIKH_ADUAN",rs.getString("TARIKH_ADUAN") == null ? "" : rs.getString("TARIKH_ADUAN"));
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("KETERANGAN_ADUAN",rs.getString("KETERANGAN_ADUAN") == null ? "" : rs.getString("KETERANGAN_ADUAN"));

				if (rs.getString("NAMA_NEGERITANAH") == null) {
					h.put("nama_negeritanah", "");
				} else {
					h.put("nama_negeritanah", rs.getString("NAMA_NEGERITANAH").toUpperCase());
				}
				if (rs.getString("NAMA_DAERAHTANAH") == null) {
					h.put("nama_daerahtanah", "");
				} else {
					h.put("nama_daerahtanah", rs.getString("NAMA_DAERAHTANAH").toUpperCase());
				}
				if (rs.getString("NAMA_MUKIMTANAH") == null) {
					h.put("nama_mukimtanah", "");
				} else {
					h.put("nama_mukimtanah", rs.getString("NAMA_MUKIMTANAH").toUpperCase());
				}
				if (rs.getString("ID_SEKSYEN") == null) {
					h.put("id_seksyen", "");
				} else {
					h.put("id_seksyen", rs.getString("ID_SEKSYEN"));
				}

				if (rs.getString("NAMA_KEMENTERIAN") == null) {
					h.put("nama_kementerian", "");
				} else {
					h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN"));
				}

				if (rs.getString("NAMA_AGENSI") == null) {
					h.put("nama_agensi", "");
				} else {
					h.put("nama_agensi", rs.getString("NAMA_AGENSI"));
				}

				if (rs.getString("USER_NAME") == null) {
					h.put("user_name", "");
				} else {
					h.put("user_name", rs.getString("USER_NAME").toUpperCase());
				}

				if (rs.getString("USER_NAME_INIT") == null) {
					h.put("user_name_init", "");
				} else {
					h.put("user_name_init", rs.getString("USER_NAME_INIT"));
				}

				if (rs.getString("USER_ROLE") == null) {
					h.put("user_role", "");
				} else {
					h.put("user_role", rs.getString("USER_ROLE").toUpperCase());
				}

				if (rs.getString("NAMA_SEKSYEN") == null) {
					h.put("nama_seksyen", "");
				} else {
					h.put("nama_seksyen", rs.getString("NAMA_SEKSYEN").toUpperCase());
				}

				if (rs.getString("NAMA_PEJABAT") == null) {
					h.put("nama_pejabat", "");
				} else {
					h.put("nama_pejabat", rs.getString("NAMA_PEJABAT").toUpperCase());
				}

				if (rs.getString("NAMA_NEGERI") == null) {
					h.put("nama_negeri", "");
				} else {
					h.put("nama_negeri", rs.getString("NAMA_NEGERI").toUpperCase());
				}

				if (rs.getString("ID_NEGERI") == null) {
					h.put("id_negeri", "");
				} else {
					h.put("id_negeri", rs.getString("ID_NEGERI").toUpperCase());
				}

				if (rs.getString("ID_NEGERI") == null) {
					h.put("id_negeri_pengadu", "");
				} else {
					h.put("id_negeri_pengadu", rs.getString("ID_NEGERI").toUpperCase());
				}

				if (rs.getString("ID_PEJABATJKPTG") == null) {
					h.put("id_pejabat_pengadu", "");
				} else {
					h.put("id_pejabat_pengadu", rs.getString("ID_PEJABATJKPTG").toUpperCase());
				}

				if (rs.getString("KOD_NEGERI") == null) {
					h.put("kod_negeri", "");
				} else {
					h.put("kod_negeri", rs.getString("KOD_NEGERI").toUpperCase());
				}

				if (rs.getString("NAMA_DAERAH") == null) {
					h.put("nama_daerah", "");
				} else {
					h.put("nama_daerah", rs.getString("NAMA_DAERAH").toUpperCase());
				}

				if (rs.getString("EMEL") == null) {
					h.put("emel", "");
				} else {
					h.put("emel", rs.getString("EMEL"));
				}

				if (rs.getString("NO_TEL") == null) {
					h.put("no_tel", "");
				} else {
					h.put("no_tel", rs.getString("NO_TEL"));
				}

				if (rs.getString("NAMA_JAWATAN") == null) {
					h.put("nama_jawatan", "");
				} else {
					h.put("nama_jawatan", rs.getString("NAMA_JAWATAN"));
				}
				listJenisAduan.add(h);
			}

		} finally {
			if(db==null)
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db1 != null)
					db1.close();
			}
		}
		return listJenisAduan;
	}

	public Vector aduanDetailsVector(String idAduan,String USER_ID,HttpSession session,Db db)throws Exception {
		Db db1 = null;

		ResultSet rs = null;
		Statement stmt = null;
		//List listJenisAduan = null;
		list_aduan = new Vector();
		list_aduan.clear();
		list_aduan.removeAllElements();
		System.out.println("list aduan >>>>> "+ list_aduan);
		String sql = "";
		try {
			db1 = new Db();
			stmt = db1.getStatement();
			sql = " SELECT UI.ID_SEKSYEN,U.USER_ID,U.USER_LOGIN,INITCAP(U.USER_NAME) AS USER_NAME_INIT,U.USER_NAME,U.USER_ROLE,S.NAMA_SEKSYEN," +
					" PEJ.NAMA_PEJABAT,N.KOD_NEGERI,N.NAMA_NEGERI,UI.ID_NEGERI,UI.ID_PEJABATJKPTG,D.NAMA_DAERAH,UI.EMEL,PEJ.NO_TEL,J.KETERANGAN AS NAMA_JAWATAN," +
					" k.NAMA_KEMENTERIAN,a.NAMA_AGENSI,aduan.ID_ADUANPUBLIC,aduan.ID_STATUS,aduan.NO_ADUAN,aduan.ID_PENGADU,aduan.TARIKH_ADUAN,aduan.NO_FAIL," +
					" aduan.KETERANGAN_ADUAN,tanah.ID_HAKMILIKADUAN,tanah.ID_ADUAN,tanah.ID_LUAS,tanah.NO_HAKMILIK,tanah.NO_WARTA,tanah.TARIKH_WARTA,tanah.NO_LOT," +
					" tanah.LUAS,tanah.ID_DAERAH,tanah.ID_NEGERI,tanah.ID_MUKIM,tanah.ID_LOT,tanah.ID_JENISHAKMILIK,N1.NAMA_NEGERI AS NAMA_NEGERITANAH," +
					" D1.NAMA_DAERAH AS NAMA_DAERAHTANAH,M.NAMA_MUKIM AS NAMA_MUKIMTANAH"+
					" FROM USERS U,USERS_INTERNAL UI,TBLRUJSEKSYEN S,TBLRUJJAWATAN J,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ," +
					" TBLRUJDAERAH D,USERS_KEMENTERIAN UK,TBLRUJKEMENTERIAN K,TBLRUJAGENSI A,TBLADUANPUBLIC aduan,TBLHTPHAKMILIKADUAN tanah,TBLRUJNEGERI N1," +
					" TBLRUJDAERAH D1,TBLRUJMUKIM M"+
					" WHERE U.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) AND UI.USER_ID = UK.USER_ID(+) AND UK.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+)" +
					" AND tanah.ID_NEGERI = N1.ID_NEGERI(+) AND tanah.ID_DAERAH = D1.ID_DAERAH(+) AND tanah.ID_MUKIM = M.ID_MUKIM(+)" +
					" AND UK.ID_AGENSI = A.ID_AGENSI(+) AND UI.ID_SEKSYEN = S.ID_SEKSYEN(+) AND UI.ID_NEGERI = N.ID_NEGERI(+) AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+)" +
					" AND UI.ID_DAERAH = D.ID_DAERAH(+)AND UI.USER_ID = '"+USER_ID+"' "+
					" AND aduan.ID_ADUANPUBLIC = '"+idAduan+"' "+
					" AND tanah.ID_ADUAN = '"+idAduan+"'  ";
			myLog.info(" ADUAN : SQL aduanDetails :"+ sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID",rs.getString("ID_ADUANPUBLIC") == null ? "" : rs.getString("ID_ADUANPUBLIC"));
				h.put("NO_ADUAN",rs.getString("NO_ADUAN") == null ? "" : rs.getString("NO_ADUAN").toUpperCase());
				h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_HAKMILIKADUAN",rs.getString("ID_HAKMILIKADUAN") == null ? "" : rs.getString("ID_HAKMILIKADUAN"));
				h.put("ID_ADUAN",rs.getString("ID_ADUAN") == null ? "" : rs.getString("ID_ADUAN"));
				h.put("ID_LUAS",rs.getString("ID_LUAS") == null ? "" : rs.getString("ID_LUAS"));
				h.put("NO_WARTA",rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("TARIKH_WARTA",rs.getString("TARIKH_WARTA") == null ? "" : rs.getString("TARIKH_WARTA"));
				h.put("NO_LOT",rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("LUAS",rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));
				h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_MUKIM",rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("ID_LOT",rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				h.put("ID_JENISHAKMILIK",rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));
				h.put("ID_ADUANPUBLIC",rs.getString("ID_ADUANPUBLIC") == null ? "" : rs.getString("ID_ADUANPUBLIC"));
				h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("NO_ADUAN",rs.getString("NO_ADUAN") == null ? "" : rs.getString("NO_ADUAN"));
				h.put("ID_PENGADU",rs.getString("ID_PENGADU") == null ? "" : rs.getString("ID_PENGADU"));
				h.put("TARIKH_ADUAN",rs.getString("TARIKH_ADUAN") == null ? "" : rs.getString("TARIKH_ADUAN"));
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("KETERANGAN_ADUAN",rs.getString("KETERANGAN_ADUAN") == null ? "" : rs.getString("KETERANGAN_ADUAN"));

				if (rs.getString("NAMA_NEGERITANAH") == null) {
					h.put("nama_negeritanah", "");
				} else {
					h.put("nama_negeritanah", rs.getString("NAMA_NEGERITANAH").toUpperCase());
				}
				if (rs.getString("NAMA_DAERAHTANAH") == null) {
					h.put("nama_daerahtanah", "");
				} else {
					h.put("nama_daerahtanah", rs.getString("NAMA_DAERAHTANAH").toUpperCase());
				}
				if (rs.getString("NAMA_MUKIMTANAH") == null) {
					h.put("nama_mukimtanah", "");
				} else {
					h.put("nama_mukimtanah", rs.getString("NAMA_MUKIMTANAH").toUpperCase());
				}
				if (rs.getString("ID_SEKSYEN") == null) {
					h.put("id_seksyen", "");
				} else {
					h.put("id_seksyen", rs.getString("ID_SEKSYEN"));
				}

				if (rs.getString("NAMA_KEMENTERIAN") == null) {
					h.put("nama_kementerian", "");
				} else {
					h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN"));
				}

				if (rs.getString("NAMA_AGENSI") == null) {
					h.put("nama_agensi", "");
				} else {
					h.put("nama_agensi", rs.getString("NAMA_AGENSI"));
				}

				if (rs.getString("USER_NAME") == null) {
					h.put("user_name", "");
				} else {
					h.put("user_name", rs.getString("USER_NAME").toUpperCase());
				}

				if (rs.getString("USER_NAME_INIT") == null) {
					h.put("user_name_init", "");
				} else {
					h.put("user_name_init", rs.getString("USER_NAME_INIT"));
				}

				if (rs.getString("USER_ROLE") == null) {
					h.put("user_role", "");
				} else {
					h.put("user_role", rs.getString("USER_ROLE").toUpperCase());
				}

				if (rs.getString("NAMA_SEKSYEN") == null) {
					h.put("nama_seksyen", "");
				} else {
					h.put("nama_seksyen", rs.getString("NAMA_SEKSYEN").toUpperCase());
				}

				if (rs.getString("NAMA_PEJABAT") == null) {
					h.put("nama_pejabat", "");
				} else {
					h.put("nama_pejabat", rs.getString("NAMA_PEJABAT").toUpperCase());
				}

				if (rs.getString("NAMA_NEGERI") == null) {
					h.put("nama_negeri", "");
				} else {
					h.put("nama_negeri", rs.getString("NAMA_NEGERI").toUpperCase());
				}

				if (rs.getString("ID_NEGERI") == null) {
					h.put("id_negeri", "");
				} else {
					h.put("id_negeri", rs.getString("ID_NEGERI").toUpperCase());
				}

				if (rs.getString("ID_NEGERI") == null) {
					h.put("id_negeri_pengadu", "");
				} else {
					h.put("id_negeri_pengadu", rs.getString("ID_NEGERI").toUpperCase());
				}

				if (rs.getString("ID_PEJABATJKPTG") == null) {
					h.put("id_pejabat_pengadu", "");
				} else {
					h.put("id_pejabat_pengadu", rs.getString("ID_PEJABATJKPTG").toUpperCase());
				}

				if (rs.getString("KOD_NEGERI") == null) {
					h.put("kod_negeri", "");
				} else {
					h.put("kod_negeri", rs.getString("KOD_NEGERI").toUpperCase());
				}

				if (rs.getString("NAMA_DAERAH") == null) {
					h.put("nama_daerah", "");
				} else {
					h.put("nama_daerah", rs.getString("NAMA_DAERAH").toUpperCase());
				}

				if (rs.getString("EMEL") == null) {
					h.put("emel", "");
				} else {
					h.put("emel", rs.getString("EMEL"));
				}

				if (rs.getString("NO_TEL") == null) {
					h.put("no_tel", "");
				} else {
					h.put("no_tel", rs.getString("NO_TEL"));
				}

				if (rs.getString("NAMA_JAWATAN") == null) {
					h.put("nama_jawatan", "");
				} else {
					h.put("nama_jawatan", rs.getString("NAMA_JAWATAN"));
				}
				list_aduan.add(h);
			}

			return list_aduan;
		} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db1 != null)
					db1.close();
		}
	}
	private void tidakLengkap(){

		String idComplaint = getParam("idComplaint");
		String jawapan = getParam("ulasanBalas");
		String idRespon = getParam("idRespon");
		ComplaintResponse response = new ComplaintResponse();
		Complaint complaint = new Complaint();
		complaint.setId(Long.parseLong(idComplaint));
		response.setJawapan(jawapan);
		response.setResponseStatus(ResponseStatus.TIDAK_LENGKAP);
		response.setComplaint(complaint);
		response.setIdMasuk(userId);
		response.setId(Long.parseLong(idRespon));
		response = getRespone().doResponse(response);
		response = getRespone().getResponse(idRespon);
		context.put("response",response);

	}
	private void tidakReleven(){
		String idComplaint = getParam("idComplaint");
		String jawapan = getParam("ulasanBalas");
		String idRespon = getParam("idRespon");
		ComplaintResponse response = new ComplaintResponse();
		Complaint complaint = new Complaint();
		complaint.setId(Long.parseLong(idComplaint));
		response.setJawapan(jawapan);
		response.setResponseStatus(ResponseStatus.TIDAK_RELEVAN);
		response.setComplaint(complaint);
		response.setIdMasuk(userId);
		response.setId(Long.parseLong(idRespon));
		response = getRespone().doResponse(response);
		response = getRespone().getResponse(idRespon);
		context.put("response",response);
	}
	private void selesai(){
		String idComplaint = getParam("idComplaint");
		String jawapan = getParam("ulasanBalas");
		String idRespon = getParam("idRespon");
		ComplaintResponse response = new ComplaintResponse();
		Complaint complaint = new Complaint();
		complaint.setId(Long.parseLong(idComplaint));
		response.setComplaint(complaint);
		response.setIdMasuk(userId);
		response.setId(Long.parseLong(idComplaint));
		response.setJawapan(jawapan);
		response.setResponseStatus(ResponseStatus.SELESAI);
		response.setId(Long.parseLong(idRespon));
		getRespone().doResponse(response);
		response = getRespone().getResponse(idRespon);
		context.put("response",response);
	}
	private void getSeksyenList()throws Exception{
		Vector v = DB.getSeksyen();
		context.put("sections", v);
		context.put("negeri",getHandler().getAvailableNegeri());
	}
	private void getProsesStatus(){

		context.put("statuses", ResponseStatus.values());
	}

	private IEkptgManageComplaintHandler getHandler(){
		if(handler == null)
			 handler = new EkptgProcessComplainHandler();
		return handler;
	}
	private IComplaintResponseBean getRespone(){
		if(responseBean == null)
			responseBean = new ComplaintResponseBean();
		return responseBean;
	}

}
