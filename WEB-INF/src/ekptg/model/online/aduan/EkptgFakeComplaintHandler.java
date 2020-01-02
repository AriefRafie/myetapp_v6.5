package ekptg.model.online.aduan;

import java.util.Vector;

import ekptg.helpers.DB;
import ekptg.intergration.FileUpload;
import ekptg.intergration.FileUploadProperty;
import ekptg.intergration.FileUploadUtility;
import ekptg.intergration.IFileUploadUtilityProperty;

public class EkptgFakeComplaintHandler extends ComplaintHandler implements IEkptgFakeComplaintHandler,IComplaintActivityHandler,IFileUploadUtilityProperty {
	private long complaintId = 0;
	private String loginName;
	
	@Override
	public Complaint processComplaint(Complaint temp) throws Exception {
		complaintId = temp.getId();
		loginName = temp.getLoginName();
		Complaint complaint = getComplaint(String.valueOf(temp.getId()));
		complaint.setStatus(ComplainStatus.PALSU.toString());
		complaint.setStatusPenyelesaian("SELESAI");
		complaint.setCatatanSelesai(temp.getCatatanSelesai());
		update(complaint);
		new ComplaintActivityBean().setActivity(this);
		storeUploadFiles();
		return complaint;
	}
	private void storeUploadFiles()throws Exception{
		FileUploadUtility f = new FileUploadUtility();
		f.storeUploadFile(this);
		f.deleteUploadFiles(this);
	}

	@Override
	public Vector<Complaint> getComplaintByRole(String role) {
		return null;
	}

	@Override
	public FileUpload getUploadInfo() throws Exception {
		FileUpload upload = new FileUpload();
		upload.setLoginName(loginName);
		upload.setProsesType("jawapanAduan");
		upload.setUploadPath(FileUploadProperty.getInstance().getJawapanAduanLampiranFolder());
		upload.setUploadFieldName("CONTENT");
		upload.setUploadTableName("TBLONLINELAMPIRAN");		
		upload.setFkValue(complaintId);
		upload.setFkField("ID_REKOD");
		upload.setPkField("ID_ONLINELAMPIRAN");
		upload.setPkValue(DB.getNextID("TBLONLINELAMPIRAN_SEQ"));
		upload.setMimeField("JENIS_MIME");
		return upload;
	}

	@Override
	public String getActivityType() {
		return "TUTUP_ADUAN";
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

}
