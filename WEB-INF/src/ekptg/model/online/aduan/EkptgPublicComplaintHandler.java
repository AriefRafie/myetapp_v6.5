package ekptg.model.online.aduan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import lebah.db.Db;
import ekptg.helpers.DB;
import ekptg.engine.EmailSender;
import ekptg.engine.EmailProperty;
import ekptg.intergration.FileUpload;
import ekptg.intergration.FileUploadProperty;
import ekptg.intergration.FileUploadUtility;
import ekptg.intergration.IFileUploadUtilityProperty;

public class EkptgPublicComplaintHandler extends ComplaintHandler implements IEkptgPublicComplaintHandler,IFileUploadUtilityProperty,IComplaintActivityHandler {
	private String loginName;
	private long complaintId = 0;
	@Override
	public Vector<Complaint> getComplaintByRole(String role) {
		return null;
	}

	@Override
	public Complaint processComplaint(Complaint complaint)throws Exception {
		ComplaintSource source = complaintSource.getComplaintSource("01");
		complaint.setStatus(String.valueOf(ComplainStatus.DALAM_PROSES));
		complaint.setFlagOnline("Y");
		complaint.setStatusPenyelesaian("DALAM PROSES");
		complaint.setSource(source);
		save(complaint);
		loginName = complaint.getLoginName();
		complaintId = complaint.getId();
		storeUploadFiles();
		new ComplaintActivityBean().setActivity(this);
		notifyAdmin(complaint);
		return complaint;
	}

	@Override
	public Vector<ComplaintType> getComplaintType() {
		Db db = null;
		Vector<ComplaintType> v = new Vector<ComplaintType>();
		try{
			db = new Db();
			Statement stat = db.getStatement();
			String sql ="SELECT ID_JENISADUAN,KOD_JENIS_ADUAN,JENIS_ADUAN FROM TBLRUJJENISADUAN";
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				ComplaintType type = new ComplaintType();
				type.setId(rs.getString("ID_JENISADUAN"));
				type.setCode(rs.getString("KOD_JENIS_ADUAN"));
				type.setDescription(rs.getString("JENIS_ADUAN"));
				v.addElement(type);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return v;
	}

	
	private void storeUploadFiles()throws Exception{
		FileUploadUtility f = new FileUploadUtility();
		f.storeUploadFile(this);
		f.deleteUploadFiles(this);
	}

	@Override
	public FileUpload getUploadInfo() throws Exception {
		FileUpload upload = new FileUpload();
		upload.setLoginName(loginName);
		upload.setProsesType("aduan");
		upload.setUploadPath(FileUploadProperty.getInstance().getAduanFolder());
		upload.setUploadFieldName("CONTENT");
		upload.setUploadTableName("TBLONLINELAMPIRANADUAN");		
		upload.setFkValue(complaintId);
		upload.setFkField("ID_EADUAN");
		upload.setPkField("ID_LAMPIRANADUAN");
		upload.setPkValue(DB.getNextID("TBLONLINELAMPIRANADUAN_SEQ"));
		upload.setMimeField("JENIS_MIME");
		return upload;
	}
	private ComplaintType getComplaintType(long id){
		Db db = null;
		ComplaintType type = null;
		try{
			db = new Db();
			Statement stat = db.getStatement();
			String sql ="SELECT ID_JENISADUAN,KOD_JENIS_ADUAN,JENIS_ADUAN FROM TBLRUJJENISADUAN WHERE ID_JENISADUAN="+id;
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				type = new ComplaintType();
				type.setId(rs.getString("ID_JENISADUAN"));
				type.setCode(rs.getString("KOD_JENIS_ADUAN"));
				type.setDescription(rs.getString("JENIS_ADUAN"));
				
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return type;
	}
	private void notifyAdmin(Complaint complaint){
		myLog.debug("debug notify admin aduan aduan ");
		StringBuffer msg = new StringBuffer();
		msg.append("<p>Aduan baru telah diterima :-</p>");
		msg.append("<table width='80%'>");
		msg.append("<tr>");
		msg.append("<td>");
		msg.append("No Aduan :");
		msg.append("</td>");
		msg.append("<td>");
		msg.append(complaint.getId());
		msg.append("</td>");
		msg.append("</tr>");
		msg.append("<tr>");
		msg.append("<td>");
		msg.append("Nama Pengadu :");
		msg.append("</td>");
		msg.append("<td>");
		msg.append(complaint.getNamaPengadu()+" <i>"+complaint.getEmailPengadu()+"</i>");
		msg.append("</td>");
		msg.append("</tr>");
		msg.append("<tr>");
		msg.append("<td>");
		msg.append("Jenis:");
		msg.append("</td>");
		msg.append("<td>");
		msg.append(getComplaintType(complaint.getType().getId()).getDescription());
		msg.append("</td>");
		msg.append("</tr>");
		msg.append("<tr>");
		msg.append("<td valign='top'>");
		msg.append("Catatan :");
		msg.append("</td>");
		msg.append("<td>");
		msg.append(complaint.getCatatan());
		msg.append("</td>");
		msg.append("</tr>");
		msg.append("</table>");
		msg.append("<p>Sila login ke <a href='http://www.etapp.gov.my'>www.etapp.gov.my</a> untuk mendapatkan perincian.</p>");
		msg.append("<p>Terima kasih.</p>");
		EmailSender email = EmailSender.getInstance();
		EmailProperty prop = EmailProperty.getInstance();
		email.FROM = prop.getAduanFrom();
		email.SUBJECT = "Perkhidmatan Online eTaPP : Aduan Online";
		email.RECIEPIENT = prop.getAduanTo();
		String cc [] = new String[1];
		cc[0] = prop.getAduanCc();
		email.TO_CC = cc;
		email.MESSAGE = msg.toString();
		email.sendEmail();
		myLog.info("send email notification to admin aduan");
	}

	@Override
	public String getActivityType() {
		return "ADUAN_BARU_ONLINE";
	}

	@Override
	public long getComplaintNo() {
		return complaintId;
	}

	@Override
	public String loginId() {
		return loginName;
	}

	@Override
	public String activityRemarks() {
		return null;
	}

	public Vector<Complaint> getComplaintByUserId(String userId) {
		Vector<Complaint> v = new Vector<Complaint>();
		Db db = null;
		SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			
			String sql ="SELECT B.ID_EADUAN,B.TARIKH_MASUK,B.ID_JENISADUAN,B.STATUS,B.TARIKH_KEMASKINI,B.STATUS_PENYELESAIAN,C.KOD_JENIS_ADUAN,C.JENIS_ADUAN "
				+ " FROM USERS A, TBLONLINEEADUAN B, TBLRUJJENISADUAN C" 
				+ " WHERE A.USER_ID = B.ID_PENGADU AND C.ID_JENISADUAN = B.ID_JENISADUAN AND A.USER_ID="+userId
				+ " ORDER BY B.TARIKH_MASUK DESC";
				
			//System.out.println("cari>>"+sql);
			Statement stat = db.getStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				Complaint comp = new Complaint();
				ComplaintType type = new ComplaintType();
				type.setId(rs.getLong("ID_JENISADUAN"));
				type.setCode(rs.getString("KOD_JENIS_ADUAN"));
				type.setDescription(rs.getString("JENIS_ADUAN"));
				
				comp.setType(type);
				comp.setId(rs.getLong("ID_EADUAN"));
				comp.setTarikhAduan(rs.getDate("TARIKH_MASUK") == null ? "" : formatter.format(rs.getDate("TARIKH_MASUK")));
				comp.setStatus(ComplainStatus.getDescription(rs.getString("STATUS")).toString());
				comp.setStatusPenyelesaian(rs.getString("STATUS_PENYELESAIAN"));
				if(comp.getStatusPenyelesaian().equals("AKTIF")){
					comp.setStatusPenyelesaian("SEDANG PROSES");
				}
				comp.setTarikhKemaskini(rs.getDate("TARIKH_KEMASKINI") == null ? "" : formatter.format(rs.getDate("TARIKH_KEMASKINI")));
				v.addElement(comp);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return v;
	}
}
