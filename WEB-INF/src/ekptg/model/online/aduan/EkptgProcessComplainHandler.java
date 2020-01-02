package ekptg.model.online.aduan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import lebah.db.Db;
import ekptg.model.entities.Tblrujnegeri;

public class EkptgProcessComplainHandler extends ComplaintHandler implements IEkptgManageComplaintHandler{

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Vector<Complaint> getComplaintByRole(String role) {
		Vector<Complaint> v = new Vector<Complaint>();
		Db db = null;
		try{
			db = new Db();
			String sql ="SELECT A.ID_EADUAN,A.NAMA_PENGADU,A.EMAIL_PENGADU,A.CATATAN,A.TARIKH_MASUK,A.ID_JENISADUAN,A.PHONE_PENGADU,A.STATUS,A.ID_RESPONSEKSYEN,A.ID_PEGAWAI,A.ULASAN_PENERIMAAN,A.TARIKH_KEMASKINI,C.KOD_JENIS_ADUAN, C.JENIS_ADUAN " +
					"FROM TBLONLINEEADUAN A,TBLRUJJENISADUAN C" +
					" WHERE C.ID_JENISADUAN = A.ID_JENISADUAN AND A.ID_RESPONSEKSYEN="+role;
			// AND A.STATUS='"+ComplainStatus.SEMAKKAN_SEKSYEN+"'
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
				comp.setNamaPengadu(rs.getString("NAMA_PENGADU"));
				comp.setEmailPengadu(rs.getString("EMAIL_PENGADU"));
				comp.setCatatan(rs.getString("CATATAN"));
				comp.setTarikhAduan(rs.getDate("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				comp.setPhonePengadu(rs.getString("PHONE_PENGADU"));
				//comp.setStatus(rs.getString("STATUS"));
				comp.setStatus(ComplainStatus.getDescription(rs.getString("STATUS")).getDesc());
				comp.setIdResponSeksyen(rs.getString("ID_RESPONSEKSYEN"));
				comp.setIdPegawai(rs.getString("ID_PEGAWAI"));
				comp.setUlasanPenerimaan(rs.getString("ULASAN_PENERIMAAN"));
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
	public Complaint processComplaint(Complaint complaint) {
		
		return complaint;
	}

	@Override
	public Vector<Tblrujnegeri> getAvailableNegeri() {
		Db db = null;
		Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
		try{
			db = new Db();
			Statement stat = db.getStatement();
			String sql ="SELECT ID_NEGERI,KOD_NEGERI,NAMA_NEGERI FROM TBLRUJNEGERI WHERE KOD_NEGERI NOT IN('0','99')";
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				Tblrujnegeri negeri = new Tblrujnegeri();
				negeri.setIdNegeri(rs.getLong("ID_NEGERI"));
				negeri.setKodNegeri(rs.getString("KOD_NEGERI"));
				negeri.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				
				v.addElement(negeri);
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
	public Vector<Complaint> search(String noAduan, String status, String tarikh) {
		Vector<Complaint> v = new Vector<Complaint>();
		Db db = null;
		try{
			db = new Db();
			Connection conn = db.getConnection();
			String sql ="SELECT * FROM TBLONLINEEADUAN WHERE ID_EADUAN IN(?) AND STATUS IN(?)";
			PreparedStatement prep= conn.prepareStatement(sql);
			prep.setString(1, noAduan);
			prep.setString(2, status);
			//prep.setString(3, noAduan);
			ResultSet rs = prep.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("ID_EADUAN"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return null;
	}

}
