package ekptg.model.online.aduan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import lebah.db.Db;
import ekptg.helpers.DB;
import ekptg.intergration.FileUpload;
import ekptg.intergration.FileUploadProperty;
import ekptg.intergration.FileUploadUtility;
import ekptg.intergration.IFileUploadUtilityProperty;

public class EkptgManualComplaintHandler extends ComplaintHandler implements IEkptgManualComplaintHandler,IFileUploadUtilityProperty,IComplaintActivityHandler {
	private String loginName;
	private long complaintId = 0;
	private Vector fail = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	

	@Override
	public Vector<Complaint> getComplaintByRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complaint processComplaint(Complaint complaint) {
		complaint.setStatus(String.valueOf(ComplainStatus.DALAM_PROSES));
		complaint.setFlagOnline("N");
		complaint.setStatusPenyelesaian("AKTIF");
		complaint = save(complaint);
		
		loginName = complaint.getLoginName();
		complaintId = complaint.getId();
		try{
			storeploadFiles();
		}catch(Exception e){
			e.printStackTrace();
		}
		new ComplaintActivityBean().setActivity(this);
		return complaint;
	}
	
	public Complaint processUpdateComplaint(Complaint complaint) {
		//complaint.setStatus(String.valueOf(ComplainStatus.DALAM_PROSES));
		//complaint.setFlagOnline("N");
		//complaint.setStatusPenyelesaian("AKTIF");
		complaint = update(complaint);
		
		loginName = complaint.getLoginName();
		complaintId = complaint.getId();
		try{
			storeploadFiles();
		}catch(Exception e){
			e.printStackTrace();
		}
		new ComplaintActivityBean().setActivity(this);
		return complaint;
	}
	
	public Vector<Complaint> search(String noAduan, String statusAduan, String tarikhAduan) {
		Vector<Complaint> v = new Vector<Complaint>();
		Db db = null;
		
		SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
		String noAduanString = noAduan;

//		System.out.println(">>id::"+noAduan + ">>status::"+statusAduan+ "tarikh::"+tarikhAduan + ">>noaduanstring"+noAduanString);
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			String sql ="SELECT A.ID_EADUAN,A.STATUS,A.TARIKH_MASUK,A.NAMA_PENGADU,A.EMAIL_PENGADU,A.TARIKH_KEMASKINI,C.ID_JENISADUAN,C.KOD_JENIS_ADUAN,C.JENIS_ADUAN FROM TBLONLINEEADUAN A, TBLRUJJENISADUAN C WHERE A.ID_JENISADUAN IS NOT NULL AND C.ID_JENISADUAN = A.ID_JENISADUAN ";

			if (!noAduanString.trim().equals("")) {
				sql = sql + " AND UPPER(A.ID_EADUAN) LIKE '%' ||'"
				+ noAduanString.trim().toUpperCase() + "'|| '%'";
			}
			if (!statusAduan.trim().equals("")) {
				sql = sql + " AND UPPER(A.STATUS) LIKE '%' ||'"
				+ statusAduan.trim().toUpperCase() + "'|| '%'";
			}
			if (!tarikhAduan.trim().equals("")) {
				sql = sql + " AND TO_CHAR(A.TARIKH_MASUK,'dd/MM/yyyy') = '" + formatter.format(sdf.parse(tarikhAduan)).toUpperCase() +"'";
			}
			sql = sql + " ORDER BY A.ID_EADUAN";
			
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
			
				Complaint comp = new Complaint();
				ComplaintType type = new ComplaintType();
				type.setId(rs.getLong("ID_JENISADUAN"));
				type.setCode(rs.getString("KOD_JENIS_ADUAN"));
				type.setDescription(rs.getString("JENIS_ADUAN"));
				comp.setType(type);
				comp.setId(rs.getLong("ID_EADUAN"));
				comp.setStatus(getEnum(rs.getString("STATUS")).getDesc());
				//comp.setStatus(rs.getString("STATUS"));
				comp.setTarikhAduan(rs.getDate("TARIKH_MASUK") == null ? "" : formatter.format(rs.getDate("TARIKH_MASUK")));
				comp.setNamaPengadu(rs.getString("NAMA_PENGADU"));
				comp.setEmailPengadu(rs.getString("EMAIL_PENGADU"));
				comp.setTarikhKemaskini(rs.getDate("TARIKH_KEMASKINI") == null ? "" : sdf.format(rs.getDate("TARIKH_KEMASKINI")));
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
	private void storeploadFiles()throws Exception{
		FileUploadUtility f = new FileUploadUtility();
		f.storeUploadFile(this);
		f.deleteUploadFiles(this);
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

	@Override
	public String getActivityType() {
		return "ADUAN_BARU_OFFLINE";
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
