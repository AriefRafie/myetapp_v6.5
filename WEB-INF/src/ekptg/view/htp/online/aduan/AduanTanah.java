package ekptg.view.htp.online.aduan;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
import ekptg.engine.IPortalUtility;
import ekptg.engine.OnlineUser;
import ekptg.engine.PortalUtility;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.online.aduan.Complaint;
import ekptg.model.online.aduan.ComplaintResponseBean;
import ekptg.model.online.aduan.EkptgProcessComplainHandler;
import ekptg.model.online.aduan.IComplaintResponseBean;
import ekptg.model.online.aduan.IEkptgManageComplaintHandler;
import ekptg.model.online.aduan.ResponseStatus;
import ekptg.model.online.aduan.entity.ComplaintResponse;
import ekptg.model.utils.IUtilHTMLPilihan;
import ekptg.model.utils.rujukan.UtilHTMLPilihanSeksyenUPI;
import etapp.data.HTPHakmilikAduanMobile;
import etapp.data.OnlineEAduanMobile;
import etapp.data.OnlineLampiranEAduanMobile;
import etapp.data.RujJenisAduanMobile;
import etapp.data.RujJenisHakmilikMobile;
import etapp.data.RujSumberAduanMobile;

public class AduanTanah extends AjaxModule {
	/**
	 *
	 */
	private static final long serialVersionUID = -2190442166765550249L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.online.aduan.AduanTanah.class);

	private final String PATH="app/htp/online/aduantanah/proses/";
	private String vm = PATH +"index.jsp";
	String userId = null;
	private IEkptgManageComplaintHandler handler;
	private IPortalUtility portalUtility;
	private IComplaintResponseBean responseBean;
	DbPersistence db = new DbPersistence();
 	private IUtilHTMLPilihan iPilihan = null;
	List listAduan = null;
	HttpSession session = null;
	Db db1 = null;
	List aduanDetails = null;



	@Override
	public String doAction() throws Exception {
		HttpSession session = request.getSession();
		userId = (String)session.getAttribute("_ekptg_user_id");
		String command = getParam("command");
		String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		context.put("selectedTabUpper", selectedTabUpper);

		String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0) {
			idJenisTanah = "99999";
		}
		String idJenisHakmilik = getParam("socJenisHakmilikTanah");
		if (idJenisHakmilik == null || idJenisHakmilik.trim().length() == 0) {
			idJenisHakmilik = "99999";
		}
		String idJenisLot = getParam("socJenisLotTanah");
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

		getUserDetail(userId);


		if(command.equals("viewComplaint")){
			viewComplaint();
			getSeksyenList();
			vm = PATH+"view.jsp";
		}
		else if (command.equals("daftarBaru")) {
			getSelectedVal();
			vm = PATH + "editor.jsp";
			//submitAduan();
		}
		/*else if (command.equals("doAduan")) {

			submitAduan();
		}*/

		/*else if(command.equals("simpanComplaint")){
			//simpanComplaint();
			//displayComplaint();
			submitAduan();
			vm = PATH+"index.jsp";
		}*/
		else if(command.equals("simpanDraf")){
			//simpanDraf();
			simpanAduan();
			vm = PATH+"index.jsp";
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

		else{
			vm = PATH+"index.jsp";
			displayComplaint();
		}
		System.out.println("<<<command>>"+command);

		getProsesStatus();
		return vm;

	}

	private void getSelectedVal() throws Exception{
		String idNegeri = getParam("socNegeri").equals("")?"-1":getParam("socNegeri");
		String idDaerahTanah = getParam("socDaerahTanah").equals("")?"-1":getParam("socDaerahTanah");
		String idMukimTanah = getParam("socMukimTanah").equals("")?"-1":getParam("socMukimTanah");

		String idJenisHakmilik = getParam("socJenisHakmilik").equals("")?"-1":getParam("socJenisHakmilik");
		String idJenisLot = getParam("socJenisLot").equals("")?"-1":getParam("socJenisLot");

		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
				Long.parseLong(idNegeri), "",
				" onChange=\"doChangeNegeri();\""));
		this.context.put("selectDaerah", HTML.SelectDaerahByIdNegeri(idNegeri, "socDaerahTanah", Long.parseLong(idDaerahTanah), "", "onChange=\"doChangeDaerahTanah();\""));
		this.context.put("selectMukim", HTML.SelectMukimNoKodByDaerah(idDaerahTanah, "socMukimTanah", Long.parseLong(idMukimTanah), "", "onChange=\"doChangeMukimTanah();\""));
		this.context.put("selectSeksyen", getPilihan().Pilihan("socSeksyen", "",idMukimTanah));

		this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(idJenisHakmilik), "", "onChange=\"doChangeJenisHakmilik();\""));
		this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot", Long.parseLong(idJenisLot), "", "onChange=\"doChangeJenisLot();\""));

		myLog.info("getSelectedVal idNegeri>>>> "+idNegeri);
		myLog.info("getSelectedVal idJenisHakmilik>>>> "+idJenisHakmilik);
		this.context.put("idNegeriTanah", Long.parseLong(idNegeri));
		this.context.put("idDaerahTanah", Long.parseLong(idDaerahTanah));
		this.context.put("idMukimTanah", Long.parseLong(idMukimTanah));
		this.context.put("idJenisHakmilikTanah", Long.parseLong(idJenisHakmilik));
		this.context.put("idJenisLotTanah", Long.parseLong(idJenisLot));

	 }


	private void getUserDetail(String user_login){
		OnlineUser user = getUtility().getUserInternal(user_login);
		if(user == null)
			user = getUtility().getUserKJP(user_login);
		context.put("user", user);

	}

	private void simpanAduan() throws Exception{
		 DiskFileItemFactory factory = new DiskFileItemFactory();
		 ServletFileUpload upload = new ServletFileUpload(factory);
		 List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();

		    Date now = new Date();
		    long idPengadu =  Long.parseLong(userId);
		    String namaPengadu = "";
		    String emailPengadu = "";
		    String phonePengadu = "";
		    Long idJenisAduan = null;
		    String status = "DALAM PROSES";
		    String statusPenyelesaian = "";
		    String flagOnline = "Y";
		    String catatan = "";
			InputStream uploadData = null;
			String uploadName = "";
			String uploadType = "";
			long uploadSize = 0;
			String idNegeri = "";
			String idDaerah = "";
			String idHakmilikAduan = "";
			String noHakmilikAduan = "";
			String idJenisLotTanah = "";
			String nolotTanah = "";
			String idMukim = "";
			String idSekyen = "";

		    while (itr.hasNext()) {
				FileItem item = (FileItem)itr.next();
				if ( ((item.isFormField())) ) {
					if ( "name".equals((String)item.getFieldName())) namaPengadu = (String) item.getString();
					if ( "email".equals((String)item.getFieldName())) emailPengadu = (String) item.getString();
					if ( "phone".equals((String)item.getFieldName())) phonePengadu = (String) item.getString();
					if ( "idJenisAduan".equals(item.getFieldName())) idJenisAduan = Long.parseLong(item.getString());
					if ( "catatan".equals((String)item.getFieldName())) catatan = (String) item.getString();
					if ( "socNegeri".equals((String)item.getFieldName())) idNegeri = (String) item.getString();
					if ( "socDaerahTanah".equals((String)item.getFieldName())) idDaerah = (String) item.getString();
					if ( "socJenisHakmilik".equals((String)item.getFieldName())) idHakmilikAduan = (String) item.getString();
					if ( "nohakmilikTanah".equals((String)item.getFieldName())) noHakmilikAduan = (String) item.getString();
					if ( "socJenisLot".equals((String)item.getFieldName())) idJenisLotTanah = (String) item.getString();
					if ( "nolotTanah".equals((String)item.getFieldName())) nolotTanah = (String) item.getString();
					if ( "socMukimTanah".equals((String)item.getFieldName())) idMukim = (String) item.getString();
					if ( "socSeksyen".equals((String)item.getFieldName())) idSekyen = (String) item.getString();
				} else if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
					uploadData = item.getInputStream();
					uploadName = item.getName();
					uploadType = item.getContentType();
					uploadSize = item.getSize();
				}
			}

			RujJenisAduanMobile jenisAduan = db.find(RujJenisAduanMobile.class, idJenisAduan);

			Long idSumber = Long.parseLong("16101");
			RujSumberAduanMobile sumberAduan = db.find(RujSumberAduanMobile.class, idSumber);

			try {
				//db = new DbPersistence();
				db.begin();
				OnlineEAduanMobile aduan = new OnlineEAduanMobile();
				aduan.setIdPengadu(idPengadu);
				aduan.setNamaPengadu(namaPengadu);
				aduan.setEmailPengadu(emailPengadu);
				aduan.setPhonePengadu(phonePengadu);
				aduan.setJenisAduan(jenisAduan);
				aduan.setCatatan(catatan.toUpperCase());
				aduan.setStatus(status.toUpperCase());
				aduan.setFlagOnline(flagOnline.toUpperCase());
				aduan.setStatusPenyelesaian(statusPenyelesaian.toUpperCase());
				aduan.setSumberAduan(sumberAduan);
				aduan.setTarikhMasuk(now);
				db.persist(aduan);
				db.commit();

				Long idAduan = aduan.getId();

				try{

				OnlineEAduanMobile aduanOnline = db.find(OnlineEAduanMobile.class, idAduan);

				db.begin();
				OnlineLampiranEAduanMobile lampiran = new OnlineLampiranEAduanMobile();
				lampiran.setEAduan(aduanOnline);
				lampiran.setContent(IOUtils.toByteArray(uploadData));
				lampiran.setJenisMime(uploadType);
				lampiran.setTarikhMasuk(now);
				lampiran.setNamaFail(uploadName);
				db.persist(lampiran);
				db.commit();
				} catch(Exception e){
					e.getMessage();
				}

				aduanTanah(session,String.valueOf(idAduan),db1,idNegeri,idDaerah,idHakmilikAduan,noHakmilikAduan,idJenisLotTanah,nolotTanah,idMukim,idSekyen);
				context.put("complaintID", idAduan);
				context.put("pengaduID", idPengadu);
			} catch(Exception e){
				e.getMessage();
			}
	 }

	public String aduanTanah(HttpSession session,String ID_ADUANPUBLIC,Db db,String idNegeri,String idDaerah,String idHakmilikAduan,
			String noHakmilik,String idJenisLotTanah,String nolotTanah,String idMukim,String idSekyen) throws Exception {

		Connection conn = null;

		 String ID_TANAH = getParam("id_phphakmilikaduan");
		 SQLRenderer r2 = new SQLRenderer();
		 String sql2 = "";
		long idTanah = 0;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
		if(!ID_TANAH.equals(""))
		{
			r2.update("ID_HAKMILIKADUAN", ID_TANAH);
		}
		else
		{
			idTanah = DB.getNextID(db, "TBLHTPHAKMILIKADUAN_SEQ");
			r2.add("ID_HAKMILIKADUAN", idTanah);
		}

		r2.add("ID_ADUAN", ID_ADUANPUBLIC);
		r2.add("ID_NEGERI", idNegeri);
		r2.add("ID_DAERAH", idDaerah);
		r2.add("ID_MUKIM", idMukim);
		r2.add("ID_SEKSYEN", idSekyen);
		r2.add("ID_JENISHAKMILIK", idHakmilikAduan);
		r2.add("NO_HAKMILIK", noHakmilik);
		r2.add("ID_LOT", idJenisLotTanah);
		r2.add("NO_LOT", nolotTanah);
		r2.add("ID_MASUK", userId);
		r2.add("TARIKH_MASUK", r2.unquote("sysdate"));
		r2.add("ID_KEMASKINI", userId);
		r2.add("TARIKH_KEMASKINI", r2.unquote("sysdate"));

		if(!ID_TANAH.equals(""))
		{
			sql2 = r2.getSQLUpdate("TBLHTPHAKMILIKADUAN");
		}
		else
		{
			sql2 = r2.getSQLInsert("TBLHTPHAKMILIKADUAN");
		}

		myLog.info("aduanTanah :: sql >>>> "+sql2);
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
				if (db!= null)
					db.close();
			}
		}
		return ID_TANAH;
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
	private void viewComplaint()throws Exception{
		String idRespon = getParam("idRespon");
		System.out.println("idRespon >>>> "+idRespon);
		ComplaintResponse response =  getRespone().getResponse(idRespon);

		System.out.println("response >>>> "+response);
		context.put("response",response);
		Complaint complaint = getHandler().getComplaint(String.valueOf(idRespon));
		context.put("complaint", complaint);

		Db db = null;
		try {
			db = new Db();
			aduanDetails = aduanDetails(idRespon,idRespon,session,db);
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
		context.put("editable", false);

		/*if(response.getResponseStatus().equals(ResponseStatus.BARU)  || response.getResponseStatus().equals(ResponseStatus.DRAF)){
			context.put("editable", true);
		}else{
			context.put("editable", false);
		}*/
		//getHandler().processComplaint(complaint);
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
					" D1.NAMA_DAERAH AS NAMA_DAERAHTANAH,M.NAMA_MUKIM AS NAMA_MUKIMTANAH"+
					" FROM TBLONLINEEADUAN aduan,TBLHTPHAKMILIKADUAN tanah,TBLRUJNEGERI N1," +
					" TBLRUJDAERAH D1,TBLRUJMUKIM M"+
					" WHERE tanah.ID_NEGERI = N1.ID_NEGERI(+) AND tanah.ID_DAERAH = D1.ID_DAERAH(+) AND tanah.ID_MUKIM = M.ID_MUKIM(+)" +
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
	private void displayComplaint()throws Exception{
		/*if(userId == null)
			throw new Exception("Cannot find login id. Please check with system adminstrator");
		//InternalUserUtil.getSeksyenId(userId);
		//Vector<Complaint> v = getHandler().getComplaintByRole(InternalUserUtil.getSeksyenId(userId).getIdSeksyen());
		//Vector<Complaint> v = getHandler().getComplaintByRole("3");//for testing purpose;
		Vector v =getRespone().getMyTaskList(userId, ResponseStatus.BARU.toString());
		//v =getRespone().getMyResponsibility();//please remove this when in production. for testing purpose
		context.put("lists", v);*/
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

	public List listAduan(String USER_ID,HttpSession session,Db db)throws Exception {
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
			sql = "SELECT UPPER(aduan.NAMA_PENGADU) AS FULLNAME,aduan.ID_PENGADU,tanah.NO_HAKMILIK,aduan.ID_EADUAN,aduan.TARIKH_MASUK,aduan.STATUS FROM TBLHTPHAKMILIKADUAN tanah, TBLONLINEEADUAN aduan "+
					" WHERE tanah.ID_ADUAN = aduan.ID_EADUAN AND tanah.ID_MASUK = '"+USER_ID+"'  ";
			myLog.info(" ADUAN : SQL listJenisAduan :"+ sql);
			rs = stmt.executeQuery(sql);
			listJenisAduan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID_EADUAN") == null ? "" : rs.getString("ID_EADUAN").toUpperCase());
				h.put("FULLNAME",rs.getString("FULLNAME") == null ? "" : rs.getString("FULLNAME").toUpperCase());
				h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_PENGADU",rs.getString("ID_PENGADU") == null ? "" : rs.getString("ID_PENGADU"));
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
	private IPortalUtility getUtility(){
		if(portalUtility == null)
			portalUtility = new PortalUtility();
		return portalUtility;
	}
	private IUtilHTMLPilihan getPilihan(){
		if(iPilihan==null){
			iPilihan = new UtilHTMLPilihanSeksyenUPI();
		}
		return iPilihan;

	}

}
