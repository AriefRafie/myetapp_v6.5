package ekptg.model.online.aduan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.model.online.aduan.entity.ComplaintResponse;
import ekptg.model.online.aduan.entity.ComplaintTindakan;
import ekptg.model.online.aduan.setup.ComplaintTindakanBean;
import ekptg.model.online.aduan.setup.IComplaintTindakanBean;

public abstract class ComplaintResponseFacade {
	private IComplaintTindakanBean tindakanBean = null;
	protected static Logger log = Logger.getLogger(ComplaintResponseFacade.class);
	
	public ComplaintResponseFacade(){
		if(tindakanBean == null)
			tindakanBean = new ComplaintTindakanBean();
	}
	public abstract ComplaintResponse doResponse(ComplaintResponse response);
	public Vector<ComplaintResponse> getMyResponsibility(){
		Db db = null;
		Vector<ComplaintResponse> v = new Vector<ComplaintResponse>();
		try{
			db = new Db();
			Statement stat = db.getStatement();
			String sql ="SELECT * FROM TBLONLINEADUANRESPON";
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				ComplaintResponse response = new ComplaintResponse();
				response.setId(rs.getLong("ID_ADUANRESPON"));
				response.setResponseStatus(ResponseStatus.getResponseStatus(rs.getString("STATUS")));
				response.setArahan(rs.getString("ARAHAN"));
				response.setJawapan(rs.getString("JAWAPAN"));
				Complaint complaint = new Complaint();
				complaint.setId(rs.getLong("ID_EADUAN"));
				response.setComplaint(complaint);
				v.addElement(response);
			}
			
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			
		}finally{
			db.close();
		}
		return v;
	}
	public ComplaintResponse save(ComplaintResponse response){
		Db db = null;
		Date now = new Date();
    	SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
    	String today = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		try{
			db = new Db();
			Statement stat = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			long idResponseAduan = DB.getNextID("TBLONLINEADUANRESPON_SEQ");
			response.setId(idResponseAduan);
			r.add("ID_ADUANRESPON",response.getId());
			r.add("ID_MASUK",response.getIdMasuk());
			r.add("TARIKH_MASUK",r.unquote(today));
			r.add("JAWAPAN",response.getJawapan());
			r.add("ARAHAN",response.getArahan());
			r.add("STATUS",response.getResponseStatus().toString());
			r.add("ID_EADUAN",response.getComplaint().getId());
			r.add("ID_ADUANTINDAKAN",response.getTindakan().getId());
			String sql = r.getSQLInsert("TBLONLINEADUANRESPON");
			log.info(sql);
			stat.executeUpdate(sql);
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			
		}finally{
			db.close();
		}
		return response;
	}
	public ComplaintResponse update(ComplaintResponse response){
		Db db = null;
		Date now = new Date();
    	SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
    	String today = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		try{
			db = new Db();
			Statement stat = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_KEMASKINI",response.getIdMasuk());
			r.add("TARIKH_KEMASKINI",r.unquote(today));
			r.add("JAWAPAN",response.getJawapan());
			r.add("STATUS",response.getResponseStatus().toString());
			r.add("ID_EADUAN",response.getComplaint().getId());
			r.update("ID_ADUANRESPON",response.getId());
			String sql = r.getSQLUpdate("TBLONLINEADUANRESPON");
			stat.executeUpdate(sql);
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			
		}finally{
			db.close();
		}
		return response;
	}
	public Vector<ComplaintResponse> getComplaintResponse(String idAduan){
		Db db = null;
		Vector<ComplaintResponse> v = new Vector<ComplaintResponse>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stat = db.getStatement();
			String sql ="SELECT A.ID_ADUANRESPON,A.STATUS,A.JAWAPAN,A.ARAHAN,A.TARIKH_MASUK,A.ID_ADUANTINDAKAN,B.NAMA_TINDAKAN FROM TBLONLINEADUANRESPON A,TBLONLINEADUANTINDAKAN B" +
					" WHERE A.ID_ADUANTINDAKAN=B.ID_ADUANTINDAKAN AND ID_EADUAN="+idAduan+
					" ORDER BY A.TARIKH_MASUK";
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				ComplaintResponse response = new ComplaintResponse();
				ComplaintTindakan tindakan = tindakanBean.getTindakan(rs.getString("ID_ADUANTINDAKAN"));
				response.setId(rs.getLong("ID_ADUANRESPON"));
				response.setResponseStatus(ResponseStatus.getResponseStatus(rs.getString("STATUS")));
				response.setArahan(rs.getString("ARAHAN"));
				response.setJawapan(rs.getString("JAWAPAN"));
				response.setTarikhMasuk(rs.getDate("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				response.setTindakan(tindakan);
				v.addElement(response);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			db.close();
		}
		return v;
	}
	public ComplaintResponse getResponse(String idResponse){
		Db db = null;
		ComplaintResponse response = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			
			db = new Db();
			Statement stat = db.getStatement();
			String sql ="SELECT A.ID_ADUANRESPON,A.ID_EADUAN,A.STATUS,A.JAWAPAN,A.ARAHAN,A.TARIKH_MASUK,A.ID_ADUANTINDAKAN,B.NAMA_TINDAKAN " +
					"FROM TBLONLINEADUANRESPON A,TBLONLINEADUANTINDAKAN B" +
					" WHERE A.ID_ADUANTINDAKAN=B.ID_ADUANTINDAKAN AND A.ID_ADUANRESPON ="+idResponse;
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				ComplaintTindakan tindakan = tindakanBean.getTindakan(rs.getString("ID_ADUANTINDAKAN"));
				Complaint complaint = new Complaint();
				complaint.setId(rs.getLong("ID_EADUAN"));
				
				response = new ComplaintResponse();
				response.setId(rs.getLong("ID_ADUANRESPON"));
				response.setResponseStatus(ResponseStatus.getResponseStatus(rs.getString("STATUS")));
				response.setJawapan(rs.getString("JAWAPAN"));
				response.setArahan(rs.getString("ARAHAN"));
				response.setTarikhMasuk(rs.getDate("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				response.setTindakan(tindakan);
				response.setComplaint(complaint);
			}
			
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			
		}finally{
			db.close();
		}
		return response;
	}
}
