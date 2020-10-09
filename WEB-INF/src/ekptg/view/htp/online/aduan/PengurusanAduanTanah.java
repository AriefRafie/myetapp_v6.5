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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.portal.action.AjaxModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.InternalUserUtil;
import ekptg.helpers.Paging;
import ekptg.model.online.aduan.ComplainStatus;
import ekptg.model.online.aduan.Complaint;
import ekptg.model.online.aduan.ComplaintAgihanBean;
import ekptg.model.online.aduan.ComplaintEmailNotification;
import ekptg.model.online.aduan.EkptgCloseComplaintHandler;
import ekptg.model.online.aduan.EkptgFakeComplaintHandler;
import ekptg.model.online.aduan.EkptgManageComplaintHandler;
import ekptg.model.online.aduan.IComplaintEmailNotification;
import ekptg.model.online.aduan.IComplaintResponseBean;
import ekptg.model.online.aduan.IEkptgCloseComplaintHandler;
import ekptg.model.online.aduan.IEkptgFakeComplaintHandler;
import ekptg.model.online.aduan.IEkptgManageComplaintHandler;
import ekptg.model.online.aduan.IJawapanLampiran;
import ekptg.model.online.aduan.JawapanLampiranBean;
import ekptg.model.online.aduan.PTGResponseBean;
import ekptg.model.online.aduan.entity.ComplaintResponse;
import ekptg.model.online.aduan.entity.ComplaintTindakan;
import ekptg.model.online.aduan.setup.ComplaintCategoryBean;
import ekptg.model.online.aduan.setup.IComplaintCategoryBean;

public class PengurusanAduanTanah extends AjaxModule {
	/**
	 *
	 */
	private static final long serialVersionUID = 8340475343566133197L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.online.aduan.PengurusanAduanTanah.class);

	//private final String PATH="app/online/aduan/manager/";
	private final String PATH="app/htp/online/aduantanah/manager/";
	private String vm = PATH +"index.jsp";
	String userId = null;
	private IEkptgManageComplaintHandler handler;
	private IComplaintResponseBean handlerRB;
	private IJawapanLampiran jawapan;
	private IEkptgCloseComplaintHandler closeBean;
	private IEkptgFakeComplaintHandler fakeBean;
	private IComplaintEmailNotification emailNotification;
	HttpSession session = null;
	String action = null;
	Db db1 = null;
	List aduanDetails = null;
	List listAgihan = null;

	@Override
	public String doAction() throws Exception {
		session = request.getSession();
		userId = (String)session.getAttribute("_ekptg_user_id");
		String command = getParam("command");
		String command01 = getParam("command01");
		context.put("test_ajax", "");
		context.put("upload_file", "");
		context.put("noAduan", "");
		System.out.println("COMMAND PILIHAN : " + command);
		System.out.println("COMMAND PILIHAN 02 : " + command01);
		String mode = getParam("mode");
		action = getParam("action");
		String idKategori = getParam("idCategory");
		String selectedTabUpper = (String) getParam("selectedTabUpper");
			if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
				selectedTabUpper = "0";
			}
			context.put("selectedTabUpper", selectedTabUpper);
		if(command.equals("viewComplaint")){
			String idComplaint = getParam("idComplaint");
			viewComplaint(idComplaint);
			getSeksyenList();
			getNotificationBean().notifyPengadu(idComplaint);//email notification to pengadu
			String uploadFileStatus = getParam("uploadFiles") != null ?
									(String) getParam("uploadFiles") : "";
			System.out.println(getParam("uploadFiles"));
			context.put("upload_file", uploadFileStatus);
			Db db = null;
			try {
				db = new Db();
				listAgihan = listAgihan(idComplaint,session,db);
			}
			catch (Exception ex) {
			throw new DbException(ex.getMessage());
			}
			finally {
				if (db != null)
					db.close();
			}
			context.put("categories", listAgihan);
			vm = PATH+"view.jsp";
		}
		else if(command.equals("simpanComplaint")){
			simpanComplaint();
		}
		else if(command.equals("daftarAgih") || command.equals("doChangeTindakan") || command.equals("doChangePegawai")){
			mode ="update";
			String idComplaint = getParam("idComplaint");
			viewComplaint(idComplaint);
			String idCategory = getParam("idCategory");
			myLog.info("idCategory >>> "+idCategory);

			String idPejabatJKPTGN = getParam("socPejabatJkptgn").equals("")?"-1":getParam("socPejabatJkptgn");
			String idKementerian = getParam("socKementerian").equals("")?"-1":getParam("socKementerian");
			String idSeksyen = getParam("socSeksyen").equals("")?"-1":getParam("socSeksyen");
			String idPtg = getParam("socPtg").equals("")?"-1":getParam("socPtg");
			String idPegawai = getParam("socPegawai").equals("")?"-1":getParam("socPegawai");
			if(idCategory.equals("1")){
				myLog.info("masuk sini tak 1 ");
				context.put("selectTindakan", HTML.SelectSeksyen("socSeksyen",
						Long.parseLong(idSeksyen), "",
						" onChange=\"doChangeTindakan();\""));
				context.put("selectPegawai", HTML.SelectPegawaiMengikutSeksyen(idSeksyen,"0","socPegawai", Long.parseLong(idPegawai), "",
						"onChange=\"doChangePegawai()\" style=\"width:240\""));

				this.context.put("idTindakan", Long.parseLong(idPegawai));
			}
			else if(idCategory.equals("2")){
				myLog.info("masuk sini tak 2 ");
				context.put("selectTindakan", HTML.SelectPejabatJKPTGN("socPejabatJkptgn",
						Long.parseLong(idPejabatJKPTGN), "",
						" onChange=\"doChangeTindakan();\""));
				context.put("selectPegawai", "");
				this.context.put("idTindakan", Long.parseLong(idPejabatJKPTGN));
			}
			else if(idCategory.equals("3")){
				myLog.info("masuk sini tak 3 ");
				context.put("selectTindakan", HTML.SelectPejabatPTG("socPtg",
						Long.parseLong(idPtg), "",
						" onChange=\"doChangeTindakan();\""));
				context.put("selectPegawai", "");
				this.context.put("idTindakan", Long.parseLong(idPtg));
			}
			else if(idCategory.equals("4")){
				myLog.info("masuk sini tak 4 ");
				context.put("selectTindakan", HTML.SelectKementerian("socKementerian",
						Long.parseLong(idKementerian), "",
						" onChange=\"doChangeTindakan();\""));
				context.put("selectPegawai", "");
				this.context.put("idTindakan", Long.parseLong(idKementerian));

			}else{
				myLog.info("masuk sini tak else");
				context.put("selectTindakan", "");
			}
			context.put("idCategory", idCategory);
			if ("1".equals(idCategory)) {
				this.context.put("selected", "");
				this.context.put("selectedL1", "selected");
				this.context.put("selectedL2", "");
				this.context.put("selectedL3", "");
				this.context.put("selectedL4", "");
	    	} else if ("2".equals(idCategory)) {
				this.context.put("selected", "");
				this.context.put("selectedL1", "");
				this.context.put("selectedL2", "selected");
				this.context.put("selectedL3", "");
				this.context.put("selectedL4", "");
	    	} else if ("3".equals(idCategory)) {
				this.context.put("selected", "");
				this.context.put("selectedL1", "");
				this.context.put("selectedL2", "");
				this.context.put("selectedL3", "selected");
				this.context.put("selectedL4", "");
	    	} else if ("4".equals(idCategory)) {
				this.context.put("selected", "");
				this.context.put("selectedL1", "");
				this.context.put("selectedL2", "");
				this.context.put("selectedL3", "");
				this.context.put("selectedL4", "selected");
	    	} else {
	    		this.context.put("selected", "selected");
				this.context.put("selectedL1", "");
				this.context.put("selectedL2", "");
				this.context.put("selectedL3", "");
				this.context.put("selectedL4", "");
	    	}

			context.put("categories",getCategoryAduan().getComplaintCategory());
			context.put("idCategory",idKategori);
			if (!"".equals(idKategori) && !"0".equals(idKategori)){
				context.put("aduanTindakan",getCategoryAduan().getCategory(idKategori).getActions());
			}

			vm = PATH+"agihan.jsp";
		}
		else if(command.equals("simpanAgih")){
			mode = "view";
			String idComplaint = getParam("idComplaint");
			simpanAgih(idComplaint);
			context.put("categories",getCategoryAduan().getComplaintCategory());
//			context.put("test_ajax", "OK");
//			context.put("noAduan", idComplaint);
			viewComplaint(idComplaint);
			Db db = null;
			try {
				db = new Db();
				listAgihan = listAgihan(idComplaint,session,db);
			}
			catch (Exception ex) {
			throw new DbException(ex.getMessage());
			}
			finally {
				if (db != null)
					db.close();
			}
			context.put("categories", listAgihan);
			vm = PATH+"view.jsp";
		}
		else if(command.equals("viewAgihan")){
			myLog.info("amsuk x sini");
			mode = "view";
			String idAgihan = getParam("idResponse");
			//viewComplaint(idComplaint);
			//displayAgihan();
			//context.put("categories",getCategoryAduan().getComplaintCategory());
			Db db = null;
			try {
				db = new Db();
				listAgihan = agihanDetails(idAgihan,session,db);
			}
			catch (Exception ex) {
			throw new DbException(ex.getMessage());
			}
			finally {
				if (db != null)
					db.close();
			}
			context.put("responses", listAgihan);
			vm = PATH+"agihanTask.jsp";
		}
		else if(command.equals("updateResponPTG")){
			updateResponse();
			String idComplaint = getParam("idComplaint");
			viewComplaint("");
			vm = PATH+"view.jsp";
		}
		else if(command.equals("tutupAduan")){
//			System.out.println("Tutup Aduan");
			//>>>>>>>>Response Aduan

			mode = "view";
			String catatanSelesai = getParam("catatanSelesai");
			String idComplaint = getParam("idComplaint");
			Complaint temp = new Complaint();
			temp.setCatatanSelesai(catatanSelesai);
			temp.setLoginName(userId);
			temp.setId(Long.parseLong(idComplaint));
			getCloseBean().processComplaint(temp);
			viewComplaint(idComplaint);
			context.put("categories",getCategoryAduan().getComplaintCategory());
			vm = PATH+"index.jsp";

			displayComplaint();
			getProsesStatus();
		}
		else if(command.equals("aduanPalsu")){
			mode = "view";
			String catatanSelesai = getParam("catatanSelesai");
			String idComplaint = getParam("idComplaint");
			//String idAduan = getParam("complaintId");
			Complaint temp = new Complaint();
			temp.setCatatanSelesai(catatanSelesai);
			temp.setLoginName(userId);
			temp.setId(Long.parseLong(idComplaint));
			getFakeBean().processComplaint(temp);
			viewComplaint(idComplaint);
			context.put("categories",getCategoryAduan().getComplaintCategory());
			vm = PATH+"view.jsp";
		}
		else if(command.equals("cariAduan") || command01.equals("cariAduan")){
			String noAduan = getParam("noAduan");
			String tarikhAduan = getParam("tarikhAduan");
			String statusAduan = getParam("responseStatus");
			Vector lists = getHandler().search(noAduan, statusAduan, tarikhAduan);
			setupPage(session, statusAduan, lists);
			context.put("lists", lists);
			context.put("statusPilihan", statusAduan);
			context.put("statuses", ComplainStatus.values());
			vm = PATH+"index.jsp";
		}
		else if(command.equals("simpanLampiran")) {
//			String noAduan = getParam("complaintId");
			String noAduan = getParam("noAduan");
			context.put("test_ajax", "OK");
			context.put("noAduan", noAduan);
			context.put("upload_file", "yes");
			uploadFiles(noAduan);
//			viewComplaint(noAduan);
//			vm = PATH+"view.jsp";
		}
		else if(command.equals("deleteLampiran")) {
			String idComplaint = getParam("idComplaint");
			//String noAduan = getParam("complaintId");
			String idLampiran = getParam("idLampiran");
//			System.out.println("NO ADUAN : " + noAduan);
//			System.out.println("ID LAMPIRAN : " + idLampiran);
			deleteDokumenLampiran(idLampiran);
			viewComplaint(idComplaint);
			vm = PATH+"view.jsp";
		}
		else{
			vm = PATH+"index.jsp";
			displayComplaint();
			getProsesStatus();
		}
		myLog.info("command >>> "+command);
		context.put("mode", mode);
		return vm;
	}
	private void updateResponse() {
		String ulasanRespon = getParam("ulasanRespon");
		String idResponse = getParam("idResponse");
		String idComplaint = getParam("idComplaint");
		Complaint complaint = new Complaint();
		complaint.setId(Long.parseLong(idComplaint));
		ComplaintResponse response = new ComplaintResponse();
		response.setComplaint(complaint);
		response.setId(Long.parseLong(idResponse));
		response.setIdKemaskini(userId);
		response.setJawapan(ulasanRespon);
		getPTGResponseHandler().doResponse(response);

	}
	private void simpanComplaint() {
		String idComplaint = getParam("idComplaint");
		String idSeksyen = getParam("idSeksyen");
		String ulasan = getParam("ulasan");
		String idNegeri = getParam("idNegeri");
		Complaint complaint = getHandler().getComplaint(idComplaint);
		complaint.setIdResponSeksyen(idSeksyen);
		complaint.setIdPegawai(userId);
		complaint.setUlasanPenerimaan(ulasan);
		complaint.setIdNegeri(idNegeri);
		getHandler().processComplaint(complaint);

	}
	private void viewComplaint(String noAduan)throws Exception{
		String idComplaint = noAduan;

//		System.out.println("Nombor Aduan Sepatutnya keluar ::::::::: " + idComplaint);
		Complaint complaint = getHandler().getComplaint(idComplaint);
		context.put("complaint", complaint);
		getResponseList(idComplaint);
		//ComplaintResponse response = getHandlerRB().getResponse(idResponse);
		//context.put("response", response);
		//getHandler().processComplaint(complaint);

		Db db = null;
		try {
			db = new Db();
			aduanDetails = aduanDetails(idComplaint,idComplaint,session,db);
		}
		catch (Exception ex) {
		throw new DbException(ex.getMessage());
		}
		finally {
			if (db != null)
				db.close();
		}
		System.out.println("aduanDetails >>> "+ aduanDetails);
		context.put("tanah", aduanDetails);
	}
	private void displayAgihan(){
		String idResponse = getParam("idResponse");
		ComplaintResponse response = getHandlerRB().getResponse(idResponse);
//		System.out.println( response.getTindakan().getGroupEmail());
		context.put("response", response);
	}
	private void displayComplaint()throws Exception{
		if(userId == null) throw new Exception("Cannot find login id. Please check with system administrator");
		InternalUserUtil.getSeksyenId(userId);
//		System.out.println("GET SEKSYEN BY ROLE:::" + InternalUserUtil.getSeksyenId(userId).getIdSeksyen());
		Vector<Complaint> v = getHandler().getComplaintByRole(InternalUserUtil.getSeksyenId(userId).getIdSeksyen());
//		Vector<Complaint> v = getHandler().getComplaintByRole("17");//for testing purpose;
		context.put("SenaraiFail", v);

		setupPage(session, action, v);
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

	public void deleteDokumenLampiran(String idLampiran) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLONLINELAMPIRAN WHERE ID_ONLINELAMPIRAN = "
					+ idLampiran;

			System.out.println("DELETE deleteDokumen_main :"+sql.toUpperCase());
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}

	private void getSeksyenList()throws Exception{
		Vector v = DB.getSeksyen();
//		context.put("sections", v);

		context.put("negeri",getHandler().getAvailableNegeri());
	}
	private void getResponseList(String idAduan)throws Exception{

		//Vector<ComplaintResponse> vectorResponse = getHandlerRB().getComplaintResponse(idAduan);


		Db db = null;
		try {
			db = new Db();
			listAgihan = listAgihan(idAduan,session,db);
		}
		catch (Exception ex) {
		throw new DbException(ex.getMessage());
		}
		finally {
			if (db != null)
				db.close();
		}
		myLog.info("listAgihan >>>> "+listAgihan);
		context.put("responses", listAgihan);
	}
	private void simpanAgih(String idComplaint) throws Exception {
		//String idComplaint = getParam("idComplaint");
		String idTindakan = getParam("idTindakan");
		String arahan = getParam("ulasan");
		String idPegawai = getParam("socPegawai");
		String idPejabatJkptgn =getParam("socPejabatJkptgn");
		String idPtg =getParam("socPtg");
		String idKementerian =getParam("socKementerian");
		String idCategory = getParam("idCategory");
		ComplaintResponse response = new ComplaintResponse();
		ComplaintTindakan tindakan = new ComplaintTindakan();
		tindakan.setId(Long.parseLong(idTindakan));
		Complaint complaint = new Complaint();
		complaint.setId(Long.parseLong(idComplaint));
		complaint.setLoginName(userId);
		complaint.setIdPegawai(idPegawai);
		response.setArahan(arahan);
		response.setJawapan("");
		response.setComplaint(complaint);
		response.setIdMasuk(userId);
		response.setTindakan(tindakan);
		getHandlerRB().doResponse(response);
		getResponseList(idComplaint);
		simpanAgihan(arahan,idComplaint,idTindakan,idPegawai,idCategory,idPejabatJkptgn,idPtg,idKementerian);
	}

	public String simpanAgihan(String arahan,String idComplaint,String idTindakan, String idPegawai,
			String idCategory, String idPejabatJkptgn, String idPtg, String idKementerian) throws Exception {

		Connection conn = null;

		 String ID_ADUANRESPON = getParam("id_aduanRespon");
		 SQLRenderer r2 = new SQLRenderer();
		 String sql2 = "";
		long idAduanRespon = 0;
		String namaPegawai = "";
		if("1".equals(idCategory)){
			namaPegawai = MaklumatPegawai(idPegawai);
		}else if("2".equals(idCategory)){
			namaPegawai = MaklumatPegawai(idPegawai);
		}else if("3".equals(idCategory)){
			namaPegawai = MaklumatPegawai(idPegawai);
		}else if("4".equals(idCategory)){
			namaPegawai = MaklumatKementerian(idKementerian);
		}
		myLog.info("namaPegawai >>>> "+namaPegawai);
		try {
			Db db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
		if(!ID_ADUANRESPON.equals(""))
		{
			r2.update("ID_ADUANRESPON", ID_ADUANRESPON);
		}
		else
		{
			idAduanRespon = DB.getNextID(db, "TBLONLINEADUANRESPON_SEQ");
			r2.add("ID_ADUANRESPON", idAduanRespon);
		}

		r2.add("ID_EADUAN", idComplaint);
		r2.add("ID_ADUANTINDAKAN", idTindakan);
		r2.add("STATUS", "BARU");
		r2.add("ARAHAN", arahan);
		r2.add("NAMA_PEGAWAI",namaPegawai);
		r2.add("ID_MASUK", userId);
		r2.add("TARIKH_MASUK", r2.unquote("sysdate"));
		r2.add("ID_KEMASKINI", userId);
		r2.add("TARIKH_KEMASKINI", r2.unquote("sysdate"));

		if(!ID_ADUANRESPON.equals(""))
		{
			sql2 = r2.getSQLUpdate("TBLONLINEADUANRESPON");
		}
		else
		{
			sql2 = r2.getSQLInsert("TBLONLINEADUANRESPON");
		}

		myLog.info("aduan Respon :: sql >>>> "+sql2);
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
		}
		return ID_ADUANRESPON;
	}

	public String MaklumatPegawai(String idPegawai) throws Exception {

		Connection conn = null;
		 SQLRenderer r2 = new SQLRenderer();
		long idAduanRespon = 0;
		String namaPegawai ="";
		try {
			Db db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			String sql = "SELECT DISTINCT(UPPER(U.USER_NAME)) USER_NAME,RP.NO_TEL_PEJABAT,RP.EMEL,RP.ID_PEGAWAI "+
					" ,RS.NAMA_SEKSYEN,RS.KOD_SEKSYEN "+
					" FROM TBLRUJPEGAWAI RP,USERS U,USERS_INTERNAL UI,TBLRUJSEKSYEN RS "+
					" WHERE "+
					" UI.USER_ID = U.USER_ID "+
					" AND U.USER_ID = RP.USER_ID " +
					" AND RS.ID_SEKSYEN = UI.ID_SEKSYEN"+
					" AND RP.FLAG_AKTIF = 'Y' "+
					" AND RP.ID_PEGAWAI = "+idPegawai;

		myLog.info("aduan Respon :: sql >>>> "+sql);
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable<String, String> h;
		while (rs.next()) {
			h = new Hashtable<String, String>();
			h.put("namapegawai", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
			namaPegawai = rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME");
		}
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
		}
		return namaPegawai;
	}
	public String MaklumatKementerian(String idPegawai) throws Exception {

		Connection conn = null;
		String namaPegawai ="";
		try {
			Db db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			String sql = "SELECT NAMA_KEMENTERIAN FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN = "+idPegawai;

		myLog.info("aduan Respon :: sql >>>> "+sql);
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable<String, String> h;
		while (rs.next()) {
			h = new Hashtable<String, String>();
			h.put("namapegawai", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN"));
			namaPegawai = rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN");
		}
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
		}
		return namaPegawai;
	}
	public String MaklumatPejabatJKPTGN(String idPegawai) throws Exception {

		Connection conn = null;
		String namaPegawai ="";
		try {
			Db db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			String sql = "SELECT NAMA_PEJABAT FROM TBLRUJPEJABATJKPTG WHERE ID_PEJABATJKPTG = "+idPegawai;

		myLog.info("aduan Respon :: sql >>>> "+sql);
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable<String, String> h;
		while (rs.next()) {
			h = new Hashtable<String, String>();
			h.put("namapegawai", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
			namaPegawai = rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT");
		}
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
		}
		return namaPegawai;
	}
	public String MaklumatPTG(String idPegawai) throws Exception {

		Connection conn = null;
		String namaPegawai ="";
		try {
			Db db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			String sql = "SELECT NAMA_PEJABAT FROM TBLRUJPEJABAT WHERE ID_PEJABAT = "+idPegawai;

		myLog.info("aduan Respon :: sql >>>> "+sql);
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable<String, String> h;
		while (rs.next()) {
			h = new Hashtable<String, String>();
			h.put("namapegawai", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
			namaPegawai = rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT");
		}
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
		}
		return namaPegawai;
	}
	private IComplaintCategoryBean getCategoryAduan(){
		IComplaintCategoryBean categoryBean = new ComplaintCategoryBean();
		return categoryBean;
	}
	private IEkptgManageComplaintHandler getHandler(){
		if(handler == null)
			 handler = new EkptgManageComplaintHandler();
		return handler;
	}
	private IComplaintResponseBean getHandlerRB(){
		if(handlerRB == null)
			handlerRB = new ComplaintAgihanBean();
		return handlerRB;
	}
	private IJawapanLampiran getJawapan(){
		if(jawapan == null)
			jawapan = new JawapanLampiranBean();
		return jawapan;
	}
	private IComplaintResponseBean getPTGResponseHandler(){
		handlerRB = new PTGResponseBean();
		return handlerRB;
	}
	private IEkptgCloseComplaintHandler getCloseBean(){
		if(closeBean == null){
			closeBean = new EkptgCloseComplaintHandler();
		}
		return closeBean;
	}
	private IEkptgFakeComplaintHandler getFakeBean(){
		if(closeBean == null){
			fakeBean = new EkptgFakeComplaintHandler();
		}
		return fakeBean;
	}
	private IComplaintEmailNotification getNotificationBean(){
		if(emailNotification == null){
			emailNotification = new ComplaintEmailNotification();
		}
		return emailNotification;
	}

	private void getProsesStatus(){

		context.put("statuses", ComplainStatus.values());
	}
	public void setupPagexxx(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10: getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage");
			}

			if ("getNext".equals(action)) {
				page++;
			} else if ("getPrevious".equals(action)) {
				page--;
			} else if ("getPage".equals(action)) {
				page = getParamAsInteger("value");
			} else if ("doChangeItemPerPage".equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage");
			}

			Paging paging = new Paging(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("SenaraiAduan", paging.getPage(page));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
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
			sql = " SELECT aduan.NO_FAIL," +
					" tanah.ID_HAKMILIKADUAN,tanah.ID_ADUAN,tanah.ID_LUAS,tanah.NO_HAKMILIK,tanah.NO_WARTA,tanah.TARIKH_WARTA,tanah.NO_LOT," +
					" tanah.LUAS,tanah.ID_DAERAH,tanah.ID_NEGERI,tanah.ID_MUKIM,tanah.ID_LOT,tanah.ID_JENISHAKMILIK,N1.NAMA_NEGERI AS NAMA_NEGERITANAH," +
					" D1.NAMA_DAERAH AS NAMA_DAERAHTANAH,M.NAMA_MUKIM AS NAMA_MUKIMTANAH, J.KETERANGAN AS NAMA_HAKMILIK, L.KETERANGAN AS NAMA_LOT, "+
					" tanah.ID_SEKSYEN, S.NAMA_SEKSYENUPI AS NAMA_SEKSYENTANAH "+
					" FROM TBLONLINEEADUAN aduan,TBLHTPHAKMILIKADUAN tanah,TBLRUJNEGERI N1," +
					" TBLRUJDAERAH D1,TBLRUJMUKIM M,TBLRUJJENISHAKMILIK J,TBLRUJLOT L ,TBLRUJSEKSYENUPI S"+
					" WHERE tanah.ID_NEGERI = N1.ID_NEGERI(+) AND tanah.ID_DAERAH = D1.ID_DAERAH(+) AND tanah.ID_MUKIM = M.ID_MUKIM(+) AND tanah.ID_SEKSYEN = S.ID_SEKSYENUPI(+)" +
					" AND tanah.ID_JENISHAKMILIK = J.ID_JENISHAKMILIK(+) AND tanah.ID_LOT = L.ID_LOT(+) "+
					" AND aduan.ID_EADUAN = '"+idAduan+"' "+
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
				h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_HAKMILIKADUAN",rs.getString("ID_HAKMILIKADUAN") == null ? "" : rs.getString("ID_HAKMILIKADUAN"));
				h.put("NO_LOT",rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("ID_LOT",rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));

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
				if (rs.getString("NAMA_SEKSYENTANAH") == null) {
					h.put("nama_seksyentanah", "");
				} else {
					h.put("nama_seksyentanah", rs.getString("NAMA_SEKSYENTANAH").toUpperCase());
				}
				if (rs.getString("NAMA_HAKMILIK") == null) {
					h.put("nama_hakmilik", "");
				} else {
					h.put("nama_hakmilik", rs.getString("NAMA_HAKMILIK").toUpperCase());
				}
				if (rs.getString("NAMA_LOT") == null) {
					h.put("nama_lot", "");
				} else {
					h.put("nama_lot", rs.getString("NAMA_LOT").toUpperCase());
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

	public List listAgihan(String idAduan,HttpSession session,Db db)throws Exception {
		Db db1 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
			sql ="SELECT A.ID_ADUANRESPON,A.STATUS,A.JAWAPAN,A.ARAHAN,A.TARIKH_MASUK,A.ID_ADUANTINDAKAN,A.NAMA_PEGAWAI FROM TBLONLINEADUANRESPON A" +
					" WHERE ID_EADUAN="+idAduan+
					" ORDER BY A.TARIKH_MASUK";
			myLog.info(" ADUAN : SQL listAgihan :"+ sql);
			rs = stmt.executeQuery(sql);
			listJenisAduan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID_ADUANRESPON") == null ? "" : rs.getString("ID_ADUANRESPON").toUpperCase());
				h.put("JAWAPAN",rs.getString("JAWAPAN") == null ? "" : rs.getString("JAWAPAN").toUpperCase());
				h.put("ARAHAN",rs.getString("ARAHAN") == null ? "" : rs.getString("ARAHAN").toUpperCase());
				h.put("NAMA_PEGAWAI",rs.getString("NAMA_PEGAWAI") == null ? "" : rs.getString("NAMA_PEGAWAI").toUpperCase());
				h.put("TARIKH_MASUK",rs.getDate("TARIKH_MASUK") == null ? "": sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS").toUpperCase());
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

	public List agihanDetails(String idAgihan,HttpSession session,Db db)throws Exception {
		Db db1 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
			sql ="SELECT A.ID_ADUANRESPON,A.STATUS,A.JAWAPAN,A.ARAHAN,A.TARIKH_MASUK,A.ID_ADUANTINDAKAN,A.NAMA_PEGAWAI FROM TBLONLINEADUANRESPON A" +
					" WHERE ID_ADUANRESPON="+idAgihan+
					" ORDER BY A.TARIKH_MASUK";
			myLog.info(" ADUAN : SQL listAgihan :"+ sql);
			rs = stmt.executeQuery(sql);
			listJenisAduan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID_ADUANRESPON") == null ? "" : rs.getString("ID_ADUANRESPON").toUpperCase());
				h.put("JAWAPAN",rs.getString("JAWAPAN") == null ? "" : rs.getString("JAWAPAN").toUpperCase());
				h.put("ARAHAN",rs.getString("ARAHAN") == null ? "" : rs.getString("ARAHAN").toUpperCase());
				h.put("NAMA_PEGAWAI",rs.getString("NAMA_PEGAWAI") == null ? "" : rs.getString("NAMA_PEGAWAI").toUpperCase());
				h.put("TARIKH_MASUK",rs.getDate("TARIKH_MASUK") == null ? "": sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS").toUpperCase());
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
}
