package ekptg.view.htp.online.aduan;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.action.AjaxModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
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
			vm = PATH+"view.jsp";
		}
		else if(command.equals("simpanComplaint")){
			simpanComplaint();
		}
		else if(command.equals("daftarAgih") || command.equals("doChangeTindakan")){
			mode ="update";
			String idComplaint = getParam("idComplaint");
			viewComplaint(idComplaint);
			context.put("categories",getCategoryAduan().getComplaintCategory());
			context.put("idCategory",idKategori);
			if (!"".equals(idKategori)){
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
			vm = PATH+"view.jsp";
		}
		else if(command.equals("viewAgihan")){
			mode = "view";
			String idComplaint = getParam("idComplaint");
			viewComplaint(idComplaint);
			displayAgihan();
			context.put("categories",getCategoryAduan().getComplaintCategory());
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

		Vector<ComplaintResponse> vectorResponse = getHandlerRB().getComplaintResponse(idAduan);
		context.put("responses", vectorResponse);

	}
	private void simpanAgih(String idComplaint) throws Exception {
		//String idComplaint = getParam("idComplaint");
		String idTindakan = getParam("idTindakan");
		String arahan = getParam("ulasan");
		ComplaintResponse response = new ComplaintResponse();
		ComplaintTindakan tindakan = new ComplaintTindakan();
		tindakan.setId(Long.parseLong(idTindakan));
		Complaint complaint = new Complaint();
		complaint.setId(Long.parseLong(idComplaint));
		complaint.setLoginName(userId);
		complaint.setIdPegawai(userId);
		response.setArahan(arahan);
		response.setJawapan("");
		response.setComplaint(complaint);
		response.setIdMasuk(userId);
		response.setTindakan(tindakan);
		getHandlerRB().doResponse(response);
		getResponseList(idComplaint);
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
}
