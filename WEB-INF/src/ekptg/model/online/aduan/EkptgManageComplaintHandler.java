package ekptg.model.online.aduan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.model.entities.Tblrujnegeri;

public class EkptgManageComplaintHandler extends ComplaintHandler implements IEkptgManageComplaintHandler,IComplaintActivityHandler {

	static Logger myLogger = Logger.getLogger(EkptgManageComplaintHandler.class);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private long complaintId = 0;
	private String loginName;
	@Override
	public Vector<Complaint> getComplaintByRole(String role) {
		Vector<Complaint> v = new Vector<Complaint>();
		Db db = null;
		try{
			db = new Db();
			String sql ="SELECT A.ID_EADUAN,A.NAMA_PENGADU,A.EMAIL_PENGADU,A.CATATAN,A.TARIKH_MASUK,A.ID_JENISADUAN,A.PHONE_PENGADU,A.STATUS,A.ID_RESPONSEKSYEN,A.ID_PEGAWAI,A.ULASAN_PENERIMAAN,A.TARIKH_KEMASKINI,C.KOD_JENIS_ADUAN, C.JENIS_ADUAN " +
					"FROM TBLONLINEEADUAN A, TBLRUJJENISADUAN C" +
					" WHERE C.ID_JENISADUAN = A.ID_JENISADUAN"+
					" ORDER BY TARIKH_MASUK DESC";
			// AND A.STATUS IN('"+ComplainStatus.DALAM_PROSES.toString()+"','"+ComplainStatus.SEMAKKAN_SEKSYEN.toString()+"')

			myLogger.info("SQL :::::::::::: " + sql);
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

				comp.setStatus(rs.getString("STATUS"));
				//comp.setStatus(getEnum(rs.getString("STATUS")).getDesc());
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

	public Vector<Complaint> getComplaintTanah() {
		Vector<Complaint> v = new Vector<Complaint>();
		Db db = null;
		try{
			db = new Db();
			String sql ="SELECT A.ID_EADUAN,A.NAMA_PENGADU,A.EMAIL_PENGADU,A.CATATAN,A.TARIKH_MASUK,A.ID_JENISADUAN,A.PHONE_PENGADU,A.STATUS,A.ID_RESPONSEKSYEN,A.ID_PEGAWAI,A.ULASAN_PENERIMAAN,A.TARIKH_KEMASKINI,C.KOD_JENIS_ADUAN, C.JENIS_ADUAN " +
					"FROM TBLONLINEEADUAN A, TBLRUJJENISADUAN C" +
					" WHERE C.ID_JENISADUAN = A.ID_JENISADUAN"+
					" ORDER BY TARIKH_MASUK DESC";
			// AND A.STATUS IN('"+ComplainStatus.DALAM_PROSES.toString()+"','"+ComplainStatus.SEMAKKAN_SEKSYEN.toString()+"')

			myLogger.info("SQL :::::::::::: " + sql);
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

				comp.setStatus(rs.getString("STATUS"));
				//comp.setStatus(getEnum(rs.getString("STATUS")).getDesc());
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
	public Complaint processComplaint(Complaint temp) {
		complaintId = temp.getId();
		loginName = temp.getLoginName();
		Complaint complaint = getComplaint(String.valueOf(complaintId));
		complaint.setStatus(ComplainStatus.SEMAKKAN_SEKSYEN.toString());
		complaint.setIdPegawai(temp.getIdPegawai());
		update(complaint);
		//new ComplaintActivityBean().setActivity(this);
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

		IEkptgManualComplaintHandler manualHandler = new EkptgManualComplaintHandler();
		Vector<Complaint> v = manualHandler.search(noAduan, status, tarikh);
		return v;
	}



	@Override
	public String getActivityType() {
		return "SEMAKKAN_SEKSYEN";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AduanPublic getComplaintTanahDetails(String idComplaint) {
			log.info("call get complaint details");

			Db db = null;
			AduanPublic comp = null;

			Date now = new Date();
	    	SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
	    	String today = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";

	    	log.info("ID COMPLAINT DIA:::" + idComplaint);
//	    	log.info("STATUS COMPLAINT DIA:::" + status);
//	    	if ( idComplaint != "" ) {
	    		log.info("MASUK ID COMPLAINT COMMAND:::");
	    		try{
	    			db = new Db();
	    			String sql ="SELECT aduan.NAMA_PENGADU, aduan.EMEL_PENGADU, aduan.TARIKH_MASUK, aduan.TEL_PENGADU, aduan.ID_STATUS,"
	    					+" aduan.ID_PENGADU, aduan.ID_PENGADU, aduan.ID_ADUANPUBLIC,aduan.NO_ADUAN, aduan.KETERANGAN_ADUAN, "
	    					+" aduan.TARIKH_ADUAN, tanah.ID_HAKMILIKADUAN, tanah.ID_ADUAN, tanah.ID_LUAS, tanah.NO_HAKMILIK, "
	    					+" tanah.NO_WARTA, tanah.TARIKH_WARTA, tanah.NO_LOT, tanah.LUAS, tanah.ID_DAERAH, tanah.ID_NEGERI, "
	    					+" tanah.ID_MUKIM, tanah.ID_LOT, tanah.ID_JENISHAKMILIK, N1.NAMA_NEGERI AS NAMA_NEGERITANAH, "
	    					+" D1.NAMA_DAERAH AS NAMA_DAERAHTANAH, M.NAMA_MUKIM AS NAMA_MUKIMTANAH "
	    					+" FROM TBLADUANPUBLIC aduan, TBLHTPHAKMILIKADUAN tanah, TBLRUJNEGERI N1, TBLRUJDAERAH D1, TBLRUJMUKIM M "
	    					+" WHERE aduan.ID_ADUANPUBLIC = tanah.ID_ADUAN AND tanah.ID_NEGERI = N1.ID_NEGERI(+) "
	    					+" AND tanah.ID_DAERAH = D1.ID_DAERAH(+) AND tanah.ID_MUKIM = M.ID_MUKIM(+) AND aduan.ID_ADUANPUBLIC ="+ idComplaint;
	    	    	log.info("sql:::" + sql);
	    			Statement stat = db.getStatement();
	    			ResultSet rs = stat.executeQuery(sql);
	    			if(rs.next()){
	    				comp = new AduanPublic();
	    				//ComplaintType type = new ComplaintType();
	    				//type.setId(rs.getLong("ID_JENISADUAN"));
	    				//type.setCode(rs.getString("KOD_JENIS_ADUAN"));
	    				//type.setDescription(rs.getString("JENIS_ADUAN"));
	    				//ComplaintSource source = complaintSource.getComplaintSource(rs.getLong("ID_SUMBERADUAN"));
	    				//comp.setType(type);
	    				//comp.setSource(source);
	    				comp.setId(rs.getLong("ID_ADUANPUBLIC"));
	    				comp.setNamaPengadu(rs.getString("NAMA_PENGADU"));
	    				comp.setEmailPengadu(rs.getString("EMEL_PENGADU"));
	    				comp.setPhonePengadu(rs.getString("TEL_PENGADU"));
	    				comp.setCatatan(rs.getString("KETERANGAN_ADUAN"));
	    				comp.setTarikhAduan(rs.getDate("TARIKH_ADUAN") == null ? "" : formatter.format(rs.getDate("TARIKH_ADUAN")));
//	    				log.info("STATUS COMPLAINT DIA" + getEnum((rs.getString("STATUS")).toString()).getDesc());
	    				comp.setStatus(rs.getString("ID_STATUS"));
	    				comp.setNoAduan(rs.getString("NO_ADUAN"));
	    				comp.setIdHakmilikAduan(rs.getString("ID_HAKMILIKADUAN"));
	    				comp.setIdLuas(rs.getString("ID_LUAS"));
	    				comp.setNoHakmilik(rs.getString("NO_HAKMILIK"));
	    				comp.setNoWarta(rs.getString("NO_WARTA"));
	    				comp.setTarikhWarta(rs.getDate("TARIKH_WARTA") == null ? "" : formatter.format(rs.getDate("TARIKH_WARTA")));
	    				comp.setNoLot(rs.getString("NO_LOT"));
	    				comp.setLuas(rs.getString("LUAS"));
	    				comp.setIdLot(rs.getString("ID_LOT"));
	    				comp.setIdJenisHakmilik(rs.getString("ID_JENISHAKMILIK"));
	    				comp.setNamaNegeriTanah(rs.getString("NAMA_NEGERITANAH"));
	    				comp.setNamaMukimTanah(rs.getString("NAMA_MUKIMTANAH"));
	    				comp.setNamaDaerahTanah(rs.getString("NAMA_DAERAHTANAH"));
	    				//comp.setIdResponSeksyen(rs.getString("ID_RESPONSEKSYEN"));
	    				//comp.setIdPegawai(rs.getString("ID_PEGAWAI"));
	    				//comp.setUlasanPenerimaan(rs.getString("ULASAN_PENERIMAAN"));
	    				//comp.setTarikhKemaskini(rs.getDate("TARIKH_KEMASKINI") == null ? "" : formatter.format(rs.getDate("TARIKH_KEMASKINI")));
	    				//comp.setUlasanPegawai(rs.getString("ULASAN_PEGAWAI"));
	    				//comp.setCatatanSelesai(rs.getString("CATATAN_SELESAI"));
	    				//comp.setFlagOnline(rs.getString("FLAG_ONLINE"));
	    				//comp.setStatusPenyelesaian(rs.getString("STATUS_PENYELESAIAN"));
	    				//comp.setIdPengadu(rs.getString("ID_PENGADU"));
	    				//comp.setFlagNotifiedPengadu(rs.getString("FLAG_NOTIFYPENGADU"));
	    				//comp.setTempStatus(rs.getString("STATUS"));

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
	    			log.error(e.getMessage(),e);
	    			e.printStackTrace();
	    		}
	    		finally{
	    			db.close();
	    		}
			return comp;
		}
}
