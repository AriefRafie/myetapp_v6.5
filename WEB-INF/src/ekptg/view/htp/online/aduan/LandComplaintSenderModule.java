package ekptg.view.htp.online.aduan;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.action.AjaxModule;
import lebah.template.DbPersistence;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import ekptg.engine.IPortalUtility;
import ekptg.engine.OnlineUser;
import ekptg.engine.PortalUtility;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.online.aduan.ComplaintType;
import ekptg.model.online.aduan.EkptgPublicComplaintHandler;
import ekptg.model.online.aduan.IEkptgPublicComplaintHandler;
import ekptg.model.utils.IUtilHTMLPilihan;
import ekptg.model.utils.rujukan.UtilHTMLPilihanSeksyenUPI;
import etapp.data.OnlineEAduanMobile;
import etapp.data.OnlineLampiranEAduanMobile;
import etapp.data.RujJenisAduanMobile;
import etapp.data.RujSumberAduanMobile;
//import org.apache.openjpa.lib.log.Log;

public class LandComplaintSenderModule extends AjaxModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3858875812950929902L;
	private static Logger myLog = Logger.getLogger(LandComplaintSenderModule.class);
	private final String PATH ="app/online/aduantanah/user/";
	private String vm = PATH+"index.jsp";
	private IEkptgPublicComplaintHandler handler;
	private IPortalUtility portalUtility;
	private String user_login;
	private String readonly = " disabled class = \"disabled\"";
	DbPersistence db = new DbPersistence();
 	private IUtilHTMLPilihan iPilihan = null;

	@Override
	public String doAction() throws Exception {
		HttpSession session = request.getSession();
		String command = getParam("command");
		user_login = (String)session.getAttribute("_ekptg_user_id");
		getJenisAduan();
		getUserDetail(user_login);
		context.put("readOnly", readonly);
		myLog.info("command="+command);

		
		if("doAduan".equals(command)){
			 submitAduan();
			 vm = PATH+"confirm.jsp";
		
		}else if("doAduanBaru".equals(command)){

			vm = PATH+"index.jsp";
		
		}else{
			getSelectedVal();
			vm = PATH+"index.jsp";
		}
		return vm;
	}
	private void getUserDetail(String user_login){
		OnlineUser user = getUtility().getUserOnline(user_login);
		if(user == null)
			user = getUtility().getUserKJP(user_login);
		context.put("user", user);
		
	}
	private void getJenisAduan(){
		Vector<ComplaintType> v =getHandler().getComplaintType();
		context.put("types", v);
	}
	 private void submitAduan() throws Exception{
		 DiskFileItemFactory factory = new DiskFileItemFactory();
		 ServletFileUpload upload = new ServletFileUpload(factory);

		 List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    
		    Date now = new Date();
		    long idPengadu = 12345;
		    String namaPengadu = "";
		    String emailPengadu = "";
		    String phonePengadu = "";
		    Long idJenisAduan = null;
		    String status = "DALAM PROSES";
		    String statusPenyelesaian = "DALAM PROSES";
		    String flagOnline = "Y";
		    String catatan = "";
			InputStream uploadData = null;
			String uploadName = "";
			String uploadType = "";
			long uploadSize = 0;
		 
		    while (itr.hasNext()) {
				FileItem item = (FileItem)itr.next();
				if ( ((item.isFormField())) ) {
					if ( "name".equals((String)item.getFieldName())) namaPengadu = (String) item.getString();
					if ( "email".equals((String)item.getFieldName())) emailPengadu = (String) item.getString();
					if ( "phone".equals((String)item.getFieldName())) phonePengadu = (String) item.getString();
					if ( "idJenisAduan".equals(item.getFieldName())) idJenisAduan = Long.parseLong(item.getString());
					if ( "catatan".equals((String)item.getFieldName())) catatan = (String) item.getString();
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
				
				
				
				context.put("complaintID", idAduan);
			} catch(Exception e){
				e.getMessage();
			}

	 }
	 
	 public String aduanTanah(HttpSession session,String ID_ADUANPUBLIC,String command,Db db) throws Exception {
			Connection conn = null;

		 String ID_TANAH = getParam("id_phphakmilikaduan");
		 SQLRenderer r2 = new SQLRenderer();
		 String sql2 = "";
		long idTanah = 0;

		myLog.info("ID_TANAH >>>> "+ID_TANAH);
		try {
//			if(db==null)
//			{
//				db1 = new Db();
//			}
//			else
//			{
//				db1 = db;
//			}
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
		String idNegeri = getParam("socNegeri").equals("")?"-1":getParam("socNegeri");		
		String idDaerahTanah = getParam("socDaerahTanah").equals("")?"-1":getParam("socDaerahTanah");		
		String idMukimTanah = getParam("socMukimTanah").equals("")?"-1":getParam("socMukimTanah");		

		String idJenisHakmilik = getParam("socJenisHakmilik").equals("")?"-1":getParam("socJenisHakmilik");		
		String idJenisLot = getParam("socJenisLot").equals("")?"-1":getParam("socJenisLot");		

		r2.add("ID_ADUAN", ID_ADUANPUBLIC);
		r2.add("ID_NEGERI", getParam("socNegeri"));
		r2.add("ID_DAERAH", getParam("socDaerahTanah"));
		r2.add("ID_MUKIM", getParam("socMukimTanah"));
		r2.add("ID_JENISHAKMILIK", getParam("socJenisHakmilik"));
		r2.add("NO_HAKMILIK", getParam("nohakmilik"));
		//r2.add("NO_WARTA", getParam("noWarta"));
		String TW = "to_date('" + getParam("tarikhWarta") + "','dd/MM/yyyy')";
		myLog.info("TW >>>> "+TW);
		if(TW == "null"){
			//r2.add("TARIKH_WARTA", r.unquote("sysdate"));
		}else{
			//r2.add("TARIKH_WARTA", r.unquote(TW));
		}
		r2.add("ID_LOT", getParam("socJenisLot"));
		r2.add("NO_LOT", getParam("txtNoLot"));
		//r2.add("LUAS", getParam("txtLuas"));
		//r2.add("ID_LUAS", getParam("socLuas"));
		r2.add("ID_MASUK", user_login);
		r2.add("TARIKH_MASUK", r2.unquote("sysdate"));
		r2.add("ID_KEMASKINI", user_login);
		r2.add("TARIKH_KEMASKINI", r2.unquote("sysdate"));

		if(!ID_TANAH.equals(""))
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
				if (db!= null)
					db.close();
			}
		}
		return ID_TANAH;
	}
	 
	 private void uploadData() throws Exception{
		 
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

	 }
	 
	private IEkptgPublicComplaintHandler getHandler(){
		if(handler == null)
			handler = new EkptgPublicComplaintHandler();
		return handler;
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
