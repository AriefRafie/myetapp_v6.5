package ekptg.model.online.aduan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import lebah.db.Db;
import ekptg.model.online.aduan.entity.ComplaintResponse;

public class ComplaintResponseBean extends ComplaintResponseFacade implements IComplaintResponseBean, IComplaintActivityHandler {
	private IEkptgManageComplaintHandler complaintHandler;
	private long idAduan;
	private String idMasuk;

	public ComplaintResponseBean(){
		if(complaintHandler == null)
			complaintHandler = new EkptgProcessComplainHandler();

	}
	@Override
	public ComplaintResponse doResponse(ComplaintResponse response) {
		idAduan = response.getComplaint().getId();
		idMasuk = response.getIdMasuk();
		update(response);
		complaintHandler.processComplaint(response.getComplaint());
		new ComplaintActivityBean().setActivity(this);
		return null;
	}
	private long getTindakanID(String loginID){
		Db db = null;
		int idNegeri =0;;
		int idSeksyen=0;
		long idTindakan = 0;
		String query ="";
		try{
			db = new Db();
			Statement stat = db.getStatement();
			String sql ="SELECT * FROM users a, users_internal b where a.user_id = b.user_id and a.user_id="+loginID;
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()){
				idNegeri = rs.getInt("id_negeri");
				idSeksyen = rs.getInt("id_seksyen");
			}

			switch(idNegeri){
				case 16://HQ
					query ="SELECT ID_ADUANTINDAKAN FROM TBLONLINEADUANTINDAKAN WHERE ID_NEGERI=16 AND ID_RUJSEKSYEN="+idSeksyen;
				break;
				default://STATE
					query ="SELECT ID_ADUANTINDAKAN FROM TBLONLINEADUANTINDAKAN WHERE ID_NEGERI="+idNegeri;
				break;

			}
			rs =stat.executeQuery(query);
			if(rs.next()){
				idTindakan = rs.getLong("ID_ADUANTINDAKAN");
			}

		}
		catch(Exception e){
			e.printStackTrace();

		}finally{
			db.close();
		}
		return idTindakan;
	}
	public Vector<ComplaintResponse> getMyTaskList(String loginID,String responseStatus){
		Db db = null;
		Vector<ComplaintResponse> v = new Vector<ComplaintResponse>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stat = db.getStatement();
			String sql ="SELECT * FROM TBLONLINEADUANRESPON WHERE STATUS ='"+responseStatus+"' AND ID_ADUANTINDAKAN ="+getTindakanID(loginID);
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				ComplaintResponse response = new ComplaintResponse();
				response.setId(rs.getLong("ID_ADUANRESPON"));
				response.setResponseStatus(ResponseStatus.getResponseStatus(rs.getString("STATUS")));
				response.setArahan(rs.getString("ARAHAN"));
				response.setJawapan(rs.getString("JAWAPAN"));
				response.setTarikhMasuk(rs.getDate("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				response.setStatus(rs.getString("STATUS"));
				response.setIdMasuk(rs.getString("ID_MASUK"));
				response.setIdKemaskini(rs.getString("ID_KEMASKINI"));
				Complaint complaint = complaintHandler.getComplaint(rs.getString("ID_EADUAN"));
				response.setComplaint(complaint);
				v.addElement(response);
			}

		}catch(Exception e){
			e.printStackTrace();

		}finally{
			db.close();
		}
		return v;
	}
	@Override
	public String getActivityType() {
		return "SEKSYEN_RESPON";
	}

	@Override
	public long getComplaintNo() {
		return idAduan;
	}

	@Override
	public String loginId() {
		return idMasuk;
	}
	@Override
	public String activityRemarks() {
		return "";
	}

}
