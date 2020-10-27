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

public abstract class ComplaintHandler {
	protected ComplaintHandler successor;
	protected IComplaintSource complaintSource;
	protected IJawapanLampiran jawapanLampiran;
	protected static Logger myLog = Logger.getLogger(ComplaintHandler.class);
	public void setSuccessor(ComplaintHandler successor) {
		this.successor = successor;
	}
	protected ComplaintHandler(){
		getComplaintSource();
		getJawapanLampiran();
	}
	public abstract Complaint processComplaint(Complaint complaint)throws Exception;
	public abstract Vector<Complaint> getComplaintByRole(String role);
	
	protected IComplaintSource getComplaintSource(){
		if(complaintSource == null){
			complaintSource = new ComplaintSourceBean();
		}
		return complaintSource;
	}
	protected IJawapanLampiran getJawapanLampiran(){
		if(jawapanLampiran == null){
			jawapanLampiran = new JawapanLampiranBean();
		}
		return jawapanLampiran;
	}
	public Complaint getComplaint(String idComplaint){
		myLog.info("call get getComplaint");
		Db db = null;
		Complaint comp = null;
		
		Date now = new Date();
    	SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
    	String today = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
    	
    	myLog.info("ID COMPLAINT DIA:::" + idComplaint);
//    	log.info("STATUS COMPLAINT DIA:::" + status);
//    	if ( idComplaint != "" ) {
//    	myLog.info("MASUK ID COMPLAINT COMMAND:::");
    		try{
    			db = new Db();
    			String sql ="SELECT A.ID_EADUAN,A.NAMA_PENGADU,A.EMAIL_PENGADU,A.CATATAN,A.CATATAN_SELESAI"+
    					" ,A.TARIKH_MASUK,A.ID_JENISADUAN,A.PHONE_PENGADU,A.STATUS,A.ID_RESPONSEKSYEN"+
    					" ,A.ID_PEGAWAI,A.ULASAN_PENERIMAAN,A.ULASAN_PEGAWAI,A.TARIKH_KEMASKINI,A.FLAG_ONLINE"+
    					" ,A.CATATAN_SELESAI,A.STATUS_PENYELESAIAN,A.ID_SUMBERADUAN,A.FLAG_NOTIFYPENGADU,a.id_pengadu" +
    					" ,C.KOD_JENIS_ADUAN, C.JENIS_ADUAN " +
    					" FROM TBLONLINEEADUAN A, TBLRUJJENISADUAN C, tblrujsumberaduan d" +
    					" WHERE C.ID_JENISADUAN = A.ID_JENISADUAN and a.id_sumberaduan = d.id_sumberaduan "+
    					" AND A.ID_EADUAN="+idComplaint;  			
    			
    			Statement stat = db.getStatement();
    			ResultSet rs = stat.executeQuery(sql);
    			if(rs.next()){
    				comp = new Complaint();
    				ComplaintType type = new ComplaintType();
    				type.setId(rs.getLong("ID_JENISADUAN"));
    				type.setCode(rs.getString("KOD_JENIS_ADUAN"));
    				type.setDescription(rs.getString("JENIS_ADUAN"));
    				ComplaintSource source = complaintSource.getComplaintSource(rs.getLong("ID_SUMBERADUAN"));
    				comp.setType(type);
    				comp.setSource(source);
    				comp.setId(rs.getLong("ID_EADUAN"));
    				comp.setNamaPengadu(rs.getString("NAMA_PENGADU"));
    				comp.setEmailPengadu(rs.getString("EMAIL_PENGADU"));
    				comp.setPhonePengadu(rs.getString("PHONE_PENGADU"));
    				comp.setCatatan(rs.getString("CATATAN"));
    				comp.setTarikhAduan(rs.getDate("TARIKH_MASUK") == null ? "" : formatter.format(rs.getDate("TARIKH_MASUK")));
//    				log.info("STATUS COMPLAINT DIA" + getEnum((rs.getString("STATUS")).toString()).getDesc());
    				comp.setStatus(rs.getString("STATUS"));
    				comp.setIdResponSeksyen(rs.getString("ID_RESPONSEKSYEN"));
    				comp.setIdPegawai(rs.getString("ID_PEGAWAI"));
    				comp.setUlasanPenerimaan(rs.getString("ULASAN_PENERIMAAN"));
    				comp.setTarikhKemaskini(rs.getDate("TARIKH_KEMASKINI") == null ? "" : formatter.format(rs.getDate("TARIKH_KEMASKINI")));
    				comp.setUlasanPegawai(rs.getString("ULASAN_PEGAWAI"));
    				comp.setCatatanSelesai(rs.getString("CATATAN_SELESAI"));
    				comp.setFlagOnline(rs.getString("FLAG_ONLINE"));
    				comp.setStatusPenyelesaian(rs.getString("STATUS_PENYELESAIAN"));
    				comp.setIdPengadu(rs.getString("ID_PENGADU"));
    				comp.setFlagNotifiedPengadu(rs.getString("FLAG_NOTIFYPENGADU"));
    				comp.setTempStatus(rs.getString("STATUS"));
    				
    			}
    			
    			System.out.println(":::::::::::::::::::" + comp.getId());
    			//add lampiran
    			String s ="SELECT ID_LAMPIRANADUAN,CONTENT,NAMA_FAIL FROM TBLONLINELAMPIRANADUAN " +
    					"WHERE ID_EADUAN=" + comp.getId();
    			
    			ResultSet r = stat.executeQuery(s);
    			Vector<AduanLampiran> lampiran = new Vector<AduanLampiran>();
    			while(r.next()){
    				AduanLampiran l = new AduanLampiran();
    				l.setId(r.getLong("ID_LAMPIRANADUAN"));
    				l.setContent(r.getString("CONTENT"));
    				l.setFileName(r.getString("NAMA_FAIL"));
    				lampiran.addElement(l);
    				
    				
    			}
    			comp.setLampiran(lampiran);
    			//add jawapan lampiran
    			
    			comp.setJawapanLampiran(jawapanLampiran.getLampiranByRecord(String.valueOf(comp.getId())));
    			//add activity logs
    			
    			String t ="SELECT A.ID_AKTIVITIADUAN,A.TARIKH_MASUK,A.ID_MASUK,A.AKTIVITI FROM TBLONLINEAKTIVITIADUAN A WHERE A.ID_EADUAN="+comp.getId()+" ORDER BY A.TARIKH_MASUK";
    			//System.out.println(t);
    			ResultSet rSet = stat.executeQuery(t);
    			Vector<ComplaintActivity> logs = new Vector<ComplaintActivity>();
    			while(rSet.next()){
    				ComplaintActivity log = new ComplaintActivity();
    				log.setId(rSet.getLong("ID_AKTIVITIADUAN"));
    				log.setIdMasuk(rSet.getString("ID_MASUK"));
    				log.setTarikhMasuk(rSet.getDate("TARIKH_MASUK").toString());
    				log.setAktiviti(rSet.getString("AKTIVITI"));
    				logs.addElement(log);
    			}
    			comp.setLogs(logs);
    			
    		}
    		catch(Exception e){
    			myLog.error(e.getMessage(),e);
    			e.printStackTrace();
    		}
    		finally{
    			db.close();
    		}
//    	} else if ( status != "") {
//    		log.info("MASUK STATUS COMMAND:::");
//    		try{
//    			db = new Db();
//    			String sql ="SELECT A.ID_EADUAN,A.NAMA_PENGADU,A.EMAIL_PENGADU,A.CATATAN,A.CATATAN_SELESAI,A.TARIKH_MASUK,A.ID_JENISADUAN,A.PHONE_PENGADU,A.STATUS,A.ID_RESPONSEKSYEN,A.ID_PEGAWAI,A.ULASAN_PENERIMAAN,A.ULASAN_PEGAWAI,A.TARIKH_KEMASKINI,A.FLAG_ONLINE,A.CATATAN_SELESAI,A.STATUS_PENYELESAIAN,A.ID_SUMBERADUAN,A.FLAG_NOTIFYPENGADU," +
//    					"C.KOD_JENIS_ADUAN, C.JENIS_ADUAN,a.id_pengadu " +
//    			"FROM TBLONLINEEADUAN A, TBLRUJJENISADUAN C, tblrujsumberaduan d" +
//    			" WHERE C.ID_JENISADUAN = A.ID_JENISADUAN and a.id_sumberaduan = d.id_sumberaduan AND A.STATUS='"+status+"'";
//    			
//    			log.info("SQL COMMNAD UNTUK DAPATKAN ADUAN BERDASARKAN STATUS:::" + sql);
//    			//
//    			Statement stat = db.getStatement();
//    			ResultSet rs = stat.executeQuery(sql);
//    			if(rs.next()){
//    				comp = new Complaint();
//    				ComplaintType type = new ComplaintType();
//    				type.setId(rs.getLong("ID_JENISADUAN"));
//    				type.setCode(rs.getString("KOD_JENIS_ADUAN"));
//    				type.setDescription(rs.getString("JENIS_ADUAN"));
//    				ComplaintSource source = complaintSource.getComplaintSource(rs.getLong("ID_SUMBERADUAN"));
//    				comp.setType(type);
//    				comp.setSource(source);
//    				comp.setId(rs.getLong("ID_EADUAN"));
//    				comp.setNamaPengadu(rs.getString("NAMA_PENGADU"));
//    				comp.setEmailPengadu(rs.getString("EMAIL_PENGADU"));
//    				comp.setPhonePengadu(rs.getString("PHONE_PENGADU"));
//    				comp.setCatatan(rs.getString("CATATAN"));
//    				comp.setTarikhAduan(rs.getDate("TARIKH_MASUK") == null ? "" : formatter.format(rs.getDate("TARIKH_MASUK")));
//    				log.info("STATUS COMPLAINT DIA" + getEnum((rs.getString("STATUS")).toString()).getDesc());
//    				comp.setStatus(getEnum((rs.getString("STATUS")).toString()).getDesc());
//    				comp.setIdResponSeksyen(rs.getString("ID_RESPONSEKSYEN"));
//    				comp.setIdPegawai(rs.getString("ID_PEGAWAI"));
//    				comp.setUlasanPenerimaan(rs.getString("ULASAN_PENERIMAAN"));
//    				comp.setTarikhKemaskini(rs.getDate("TARIKH_KEMASKINI") == null ? "" : formatter.format(rs.getDate("TARIKH_KEMASKINI")));
//    				comp.setUlasanPegawai(rs.getString("ULASAN_PEGAWAI"));
//    				comp.setCatatanSelesai(rs.getString("CATATAN_SELESAI"));
//    				comp.setFlagOnline(rs.getString("FLAG_ONLINE"));
//    				comp.setStatusPenyelesaian(rs.getString("STATUS_PENYELESAIAN"));
//    				comp.setIdPengadu(rs.getString("ID_PENGADU"));
//    				comp.setFlagNotifiedPengadu(rs.getString("FLAG_NOTIFYPENGADU"));
//    				comp.setTempStatus(rs.getString("STATUS"));
//    				
//    			}
//    			//add lampiran
//    			String s ="SELECT ID_LAMPIRANADUAN,CONTENT,NAMA_FAIL FROM TBLONLINELAMPIRANADUAN WHERE ID_EADUAN="+comp.getId();
//    			//System.out.println(s);
//    			ResultSet r = stat.executeQuery(s);
//    			Vector<AduanLampiran> lampiran = new Vector<AduanLampiran>();
//    			while(r.next()){
//    				AduanLampiran l = new AduanLampiran();
//    				l.setId(r.getLong("ID_LAMPIRANADUAN"));
//    				l.setContent(r.getString("CONTENT"));
//    				l.setFileName(r.getString("NAMA_FAIL"));
//    				lampiran.addElement(l);
//    				
//    				
//    			}
//    			comp.setLampiran(lampiran);
//    			//add jawapan lampiran
//    			
//    			comp.setJawapanLampiran(jawapanLampiran.getLampiranByRecord(String.valueOf(comp.getId())));
//    			//add activity logs
//    			
//    			String t ="SELECT A.ID_AKTIVITIADUAN,A.TARIKH_MASUK,A.ID_MASUK,A.AKTIVITI FROM TBLONLINEAKTIVITIADUAN A WHERE A.ID_EADUAN="+comp.getId()+" ORDER BY A.TARIKH_MASUK";
//    			//System.out.println(t);
//    			ResultSet rSet = stat.executeQuery(t);
//    			Vector<ComplaintActivity> logs = new Vector<ComplaintActivity>();
//    			while(rSet.next()){
//    				ComplaintActivity log = new ComplaintActivity();
//    				log.setId(rSet.getLong("ID_AKTIVITIADUAN"));
//    				log.setIdMasuk(rSet.getString("ID_MASUK"));
//    				log.setTarikhMasuk(rSet.getDate("TARIKH_MASUK").toString());
//    				log.setAktiviti(rSet.getString("AKTIVITI"));
//    				logs.addElement(log);
//    			}
//    			comp.setLogs(logs);
//    			
//    		}
//    		catch(Exception e){
//    			log.error(e.getMessage(),e);
//    			e.printStackTrace();
//    		}
//    		finally{
//    			db.close();
//    		}
//    	}
		
		return comp;
	}
	
	public Complaint update(Complaint complaint){
		myLog.info("updating aduan "+complaint.getId());
		Db db = null;
		Date now = new Date();
    	SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
    	String today = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
    	String tarikhSelesai = null;
		try{
			db = new Db();
			Statement stat = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("NAMA_PENGADU",complaint.getNamaPengadu());
			r.add("EMAIL_PENGADU",complaint.getEmailPengadu());
			r.add("CATATAN",complaint.getCatatan());
			r.add("ID_JENISADUAN",complaint.getType().getId());
			r.add("TARIKH_KEMASKINI",r.unquote(today));
			myLog.info("SEBELUM STATUS"+complaint.getId());
			r.add("STATUS",complaint.getStatus());
			myLog.info("SELEPAS STATUS "+complaint.getStatus());
			r.add("PHONE_PENGADU",complaint.getPhonePengadu());
//			r.add("ID_PEGAWAI",complaint.getIdPegawai());
			if(tarikhSelesai != null)
				r.add("TARIKH_SELESAI",r.unquote(tarikhSelesai));
			r.add("CATATAN_SELESAI",complaint.getCatatanSelesai());
			r.add("ULASAN_PENERIMAAN",complaint.getUlasanPenerimaan());
			r.add("ULASAN_PEGAWAI",complaint.getUlasanPegawai());
			r.add("FLAG_ONLINE",complaint.getFlagOnline());
			r.add("STATUS_PENYELESAIAN",complaint.getStatusPenyelesaian());
			if(complaint.getFlagNotifiedPengadu() != null)
				r.add("FLAG_NOTIFYPENGADU",complaint.getFlagNotifiedPengadu());
			
			r.update("ID_EADUAN", complaint.getId());
			String sql = r.getSQLUpdate("TBLONLINEEADUAN");
			myLog.info("SQL UPDATE TBLONLINEEADUAN"+sql);
//			
			stat.executeUpdate(sql);
			
		}catch(Exception e){
			myLog.error(e.getMessage(),e);
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		
		myLog.info("UPDATE FINISH");	
		return complaint;
		
	}
	
	public Complaint save(Complaint complaint){
		myLog.info("SAVING ADUAN.....");
//		log.debug("debug saving aduan ");
		Db db = null;
		Date now = new Date();
    	SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
    	String today = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		try{
			db = new Db();
			Statement stat = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			long complaintId = DB.getNextID("TBLONLINEEADUAN_SEQ");
			complaint.setId(complaintId);
			r.add("ID_EADUAN",complaintId);
			r.add("NAMA_PENGADU",complaint.getNamaPengadu());
			r.add("EMAIL_PENGADU",complaint.getEmailPengadu());
			r.add("CATATAN",complaint.getCatatan());
			r.add("ID_JENISADUAN",complaint.getType().getId());
			r.add("PHONE_PENGADU",complaint.getPhonePengadu());
			r.add("TARIKH_MASUK",r.unquote(today));
			r.add("STATUS",complaint.getStatus());
			r.add("FLAG_ONLINE",complaint.getFlagOnline());
			r.add("STATUS_PENYELESAIAN",complaint.getStatusPenyelesaian());
			r.add("ID_SUMBERADUAN",complaint.getSource().getId());
			if(complaint.getIdPengadu() != null)
				r.add("ID_PENGADU",complaint.getIdPengadu());
			String sql = r.getSQLInsert("TBLONLINEEADUAN");
			
			myLog.info(sql);
			stat.executeUpdate(sql);
			
		}catch(Exception e){
			myLog.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return complaint;
	}
	
	protected ComplainStatus getEnum(String code){
		return ComplainStatus.getDescription(code);
	}
	
	
}
