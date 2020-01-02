package ekptg.model.online.aduan;

import java.sql.Statement;
import java.util.Vector;

import lebah.db.Db;

public class EkptComplaintHandler extends ComplaintHandler {

	@Override
	public Vector<Complaint> getComplaintByRole(String role) {
		Vector<Complaint> v = new Vector<Complaint>();
		Db db = null; ;
		try{
			db = new Db();
			Statement stat = db.getStatement();
			String sql = "SELECT * FROM TBLEADUAN WHERE ";
			
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return null;
	}

	@Override
	public Complaint processComplaint(Complaint complaint) {
		return null;
		
	}

	@Override
	public Complaint getComplaint(String idComplaint) {
		// TODO Auto-generated method stub
		return null;
	}

}
