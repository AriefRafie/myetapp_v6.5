package ekptg.view.online.aduan;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.action.AjaxModule;
import ekptg.helpers.DB;
import ekptg.model.online.aduan.ComplainStatus;
import ekptg.model.online.aduan.Complaint;
import ekptg.model.online.aduan.ComplaintSource;
import ekptg.model.online.aduan.ComplaintSourceBean;
import ekptg.model.online.aduan.ComplaintType;
import ekptg.model.online.aduan.EkptgManualComplaintHandler;
import ekptg.model.online.aduan.IComplaintSource;
import ekptg.model.online.aduan.IEkptgManualComplaintHandler;

public class ComplaintManualModule extends AjaxModule {
	private final String PATH="app/online/aduan/manual/";
	private String vm = PATH +"index.jsp";
	String userId = null;
	private IEkptgManualComplaintHandler handler;
	private IComplaintSource complaintSource;
	HttpSession session = null;
	String action = null;
	@Override
	public String doAction() throws Exception {
		System.out.println("Jalan / lalu kt ComplaintManualModule.java nie");
		session = request.getSession();
		userId = (String)session.getAttribute("_ekptg_user_id");
		String command = getParam("command");
		String mode = getParam("mode");
		action = getParam("action");
		if(command.equals("search")){
			search();
			vm = PATH+"index.jsp";
		}
		else if(command.equals("viewComplaint")){
			vm = PATH+"view.jsp";
			String idComplaint = getParam("idComplaint");
			if(idComplaint.equals("")){
				mode = "simpanBaru";
				context.remove("complaint");
			} else {
				mode = "simpanKemasKini";
				viewComplaint();
			}
			this.context.put("mode", mode);

			getJenisAduan();

		}
		else if(command.equals("simpanComplaint")){
			simpanComplaint();
			vm = PATH+"view.jsp";
			mode = "simpanKemasKini";
			this.context.put("mode", mode);
		}
		else if(command.equals("simpanKemaskiniComplaint")){
			simpanKemaskiniComplaint();
			vm = PATH+"view.jsp";
			//search();
		}
		else{

			vm = PATH+"index.jsp";
			search();

			context.remove("UPDATEDMESSAGE");
		}
		return vm;
	}

	private void search(){
		Long noAduan = null;
		String noAduanString = getParam("noAduan");
		String statusAduan = getParam("statusAduan");
		String tarikhAduan = getParam("tarikhAduan");
		if(!"".equals(noAduanString)){
			noAduan = Long.parseLong(noAduanString);
		}
		Complaint complaint = new Complaint();
		System.out.println("no aduan " + noAduan);
		if(!"".equals(noAduan) && noAduan!=null){
		complaint.setId(noAduan);
		}
		complaint.setStatus(statusAduan);
		complaint.setTarikhAduan(tarikhAduan);
		Vector<Complaint> v = getHandler().search(noAduanString, statusAduan, tarikhAduan);
		context.put("SenaraiFail", v);
		setupPage(session, action, v);

	}
	private void simpanComplaint() {
		String name = getParam("name");
		String email = getParam("email");
		String idJenisAduan = getParam("idJenisAduan");
		String catatan = getParam("catatan");
		String phone = getParam("phone");
		String idSumberAduan = getParam("idSumberAduan");
		Complaint complaint = new Complaint();
		ComplaintType type = new ComplaintType();
		ComplaintSource source = new ComplaintSource();
		source.setId(idSumberAduan);
		complaint.setSource(source);
		type.setId(idJenisAduan);
		complaint.setCatatan(catatan);
		complaint.setNamaPengadu(name);
		complaint.setType(type);
		complaint.setPhonePengadu(phone);
		complaint.setEmailPengadu(email);
		complaint.setLoginName(userId);
		complaint =getHandler().processComplaint(complaint);
		context.put("complaint", complaint);


	}
	private void simpanKemaskiniComplaint() {
		String id = getParam("idComplaint");
		long idEaduan = Long.parseLong(id);
		String name = getParam("name");
		String email = getParam("email");
		String idJenisAduan = getParam("idJenisAduan");
		String catatan = getParam("catatan");
		String phone = getParam("phone");
		String idSumberAduan = getParam("idSumberAduan");
		Complaint temp = getHandler().getComplaint(id);
		Complaint complaint = new Complaint();
		ComplaintType type = new ComplaintType();
		ComplaintSource source = new ComplaintSource();
		source.setId(idSumberAduan);
		complaint.setSource(source);
		type.setId(idJenisAduan);
		complaint.setId(idEaduan);
		complaint.setCatatan(catatan);
		complaint.setNamaPengadu(name);
		complaint.setType(type);
		complaint.setPhonePengadu(phone);
		complaint.setEmailPengadu(email);
		complaint.setLoginName(userId);
		complaint.setStatus(temp.getTempStatus());
		complaint.setFlagOnline(temp.getFlagOnline());
		complaint.setStatusPenyelesaian(temp.getStatusPenyelesaian());
		complaint =getHandler().processUpdateComplaint(complaint);
		context.put("complaint", complaint);
		context.put("UPDATEDMESSAGE", "Aduan Berjaya Dikemaskini");

	}
	private void viewComplaint()throws Exception{
		String idComplaint = getParam("idComplaint");
		Complaint complaint = getHandler().getComplaint(idComplaint);
		context.put("complaint", complaint);
		//getHandler().processComplaint(complaint);
	}

	private void getSeksyenList()throws Exception{
		Vector v = DB.getSeksyen();
		context.put("sections", v);

	}
	private IEkptgManualComplaintHandler getHandler(){
		if(handler == null)
			 handler = new EkptgManualComplaintHandler();
		return handler;
	}
	private IComplaintSource getSourceHandler(){
		if(complaintSource == null){
			complaintSource = new ComplaintSourceBean();
		}
		return complaintSource;
	}
	private void getProsesStatus(){

		context.put("statuses", ComplainStatus.getList());
	}
	private void getJenisAduan(){
		Vector<ComplaintType> v =getHandler().getComplaintType();
		context.put("types", v);
		context.put("sources", getSourceHandler().getList());
	}
}

