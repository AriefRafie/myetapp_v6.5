package ekptg.view.online.aduan;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.action.AjaxModule;
import ekptg.helpers.DB;
import ekptg.model.online.aduan.Complaint;
import ekptg.model.online.aduan.ComplaintResponseBean;
import ekptg.model.online.aduan.EkptgProcessComplainHandler;
import ekptg.model.online.aduan.IComplaintResponseBean;
import ekptg.model.online.aduan.IEkptgManageComplaintHandler;
import ekptg.model.online.aduan.ResponseStatus;
import ekptg.model.online.aduan.entity.ComplaintResponse;

public class ComplaintProcessModule extends AjaxModule {
	private final String PATH="app/online/aduan/proses/";
	private String vm = PATH +"index.jsp";
	String userId = null;
	private IEkptgManageComplaintHandler handler;
	private IComplaintResponseBean responseBean;
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
		
		if(command.equals("viewComplaint")){
			viewComplaint();
			getSeksyenList();
			vm = PATH+"view.jsp";
		}
		
		else if(command.equals("simpanComplaint")){
			simpanComplaint();
			displayComplaint();
			vm = PATH+"index.jsp";
		}
		else if(command.equals("simpanDraf")){
			simpanDraf();
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
		ComplaintResponse response =  getRespone().getResponse(idRespon);
		context.put("response",response);
		Complaint complaint = getHandler().getComplaint(String.valueOf(response.getComplaint().getId()));
		context.put("complaint", complaint);
		
		if(response.getResponseStatus().equals(ResponseStatus.BARU)  || response.getResponseStatus().equals(ResponseStatus.DRAF)){
			context.put("editable", true);
		}else{
			context.put("editable", false);
		}
		//getHandler().processComplaint(complaint);
	}
	private void displayComplaint()throws Exception{
		if(userId == null)
			throw new Exception("Cannot find login id. Please check with system adminstrator");
		//InternalUserUtil.getSeksyenId(userId);
		//Vector<Complaint> v = getHandler().getComplaintByRole(InternalUserUtil.getSeksyenId(userId).getIdSeksyen());
		//Vector<Complaint> v = getHandler().getComplaintByRole("3");//for testing purpose;
		Vector v =getRespone().getMyTaskList(userId, ResponseStatus.BARU.toString());
		//v =getRespone().getMyResponsibility();//please remove this when in production. for testing purpose
		context.put("lists", v);
		
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
