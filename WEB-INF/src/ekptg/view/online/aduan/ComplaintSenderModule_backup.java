package ekptg.view.online.aduan;

import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.action.AjaxModule;
import lebah.template.DbPersistence;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import ekptg.engine.IPortalUtility;
import ekptg.engine.OnlineUser;
import ekptg.engine.PortalUtility;
import ekptg.model.online.aduan.ComplaintType;
import ekptg.model.online.aduan.EkptgPublicComplaintHandler;
import ekptg.model.online.aduan.IEkptgPublicComplaintHandler;
import etapp.data.OnlineEAduanMobile;
import etapp.data.OnlineLampiranEAduanMobile;
import etapp.data.RujJenisAduanMobile;
import etapp.data.RujSumberAduanMobile;
//import org.apache.openjpa.lib.log.Log;

public class ComplaintSenderModule_backup extends AjaxModule {
	private final String PATH ="app/online/aduan/user/";
	private String vm = PATH+"index.jsp";
	private IEkptgPublicComplaintHandler handler;
	private IPortalUtility portalUtility;
	private String user_login;
	DbPersistence db = new DbPersistence();
	
	@Override
	public String doAction() throws Exception {
		HttpSession session = request.getSession();
		String command = getParam("command");
		user_login = (String)session.getAttribute("_ekptg_user_id");
		getJenisAduan();
		getUserDetail(user_login);
		if("doAduan".equals(command)){
			 submitAduan();
			 vm = PATH+"confirm.jsp";
		 }
		else if("doAduanBaru".equals(command)){
			vm = PATH+"index.jsp";
		}else{
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
//		 
//			String name = getParam("name");
//			String email = getParam("email");
//			String idJenisAduan = getParam("idJenisAduan");
//			String catatan = getParam("catatan");
//			String phone = getParam("phone");
//			Complaint complaint = new Complaint();
//			ComplaintType type = new ComplaintType();
//			type.setId(idJenisAduan);
//			ComplaintSource source = new ComplaintSource();
//			source.setId("16101");
//			complaint.setCatatan(catatan);
//			complaint.setNamaPengadu(name);
//			complaint.setType(type);
//			complaint.setPhonePengadu(phone);
//			complaint.setEmailPengadu(email);
//			complaint.setLoginName(user_login);
//			complaint.setIdPengadu(user_login);
//			complaint =getHandler().processComplaint(complaint);
//			context.put("complaint", complaint);
	 }
	 
	 private void uploadData() throws Exception{
		 
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
}
