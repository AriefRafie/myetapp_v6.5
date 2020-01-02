package ekptg.model.php2.modelTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;

public class TblPhpPerjanjianModel {
	
	//DB columns
	private Long idPerjanjian;
	private Long idPermohonan;
	private String tarikhMulaPerjanjian;
	private String tempoh;
	private String tarikhTamatPerjanjian;
	private Double kadarSewa;
	private Double cagaran;
	private String noSiri;
	private String flagPerjanjian;
	private Long idMasuk;
	private String tarikhMasuk;
	private Long idKemaskini;
	private String tarikhKemaskini;
	private String tarikhMulaDasar;
	private String tarikhTamatDasar;
	private Double royalti;
	private String tarikhSuratNotifikasi;
	
	//non-DB columns
	private String flagPost;
	
	public void save(TblPhpPerjanjianModel model, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		
		Long idPerjanjian = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(model.getIdPermohonan()!=null){
				r.add("ID_PERMOHONAN", model.getIdPermohonan());
			}
			if(model.getTarikhMulaPerjanjian()!=null){
				r.add("TARIKH_MULA_PERJANJIAN", r.unquote(StringToDate(model.getTarikhMulaPerjanjian())));
			}
			if(model.getTempoh()!=null){
				r.add("TEMPOH", model.getTempoh());
			}
			if(model.getTarikhTamatPerjanjian()!=null){
				r.add("TARIKH_TAMAT_PERJANJIAN", r.unquote(StringToDate(model.getTarikhMulaPerjanjian())));
			}
			if(model.getKadarSewa()!=null){
				r.add("KADAR_SEWA", model.getKadarSewa());
			}
			if(model.getCagaran()!=null){
				r.add("CAGARAN", model.getCagaran());
			}
			if(model.getNoSiri()!=null){
				r.add("NO_SIRI", model.getNoSiri());
			}
			if(model.getFlagPerjanjian()!=null){
				r.add("FLAG_PERJANJIAN", model.getFlagPerjanjian());
			}
			if(model.getTarikhMulaDasar()!=null){
				r.add("TARIKH_MULA_DASAR", r.unquote(StringToDate(model.getTarikhMulaDasar())));
			}
			if(model.getTarikhTamatDasar()!=null){
				r.add("TARIKH_TAMAT_DASAR", r.unquote(StringToDate(model.getTarikhTamatDasar())));
			}
			if(model.getRoyalti()!=null){
				r.add("ROYALTI", model.getRoyalti());
			}
			if(model.getTarikhSuratNotifikasi()!=null){
				r.add("TARIKH_SURAT_NOTIFIKASI", r.unquote(StringToDate(model.getTarikhSuratNotifikasi())));
			}
			
			if(model.getFlagPost().equals("insert")){
				idPerjanjian = DB.getNextID("TBLPHPPERJANJIAN_SEQ");
				r.add("ID_PERJANJIAN", idPerjanjian);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				
				sql = r.getSQLInsert("TBLPHPPERJANJIAN");
			} else if(model.getFlagPost().equals("update")){
				idPerjanjian = model.getIdPerjanjian();
				r.update("ID_PERJANJIAN", idPerjanjian);
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				
				sql = r.getSQLUpdate("TBLPHPPERJANJIAN");
			}
			stmt.executeUpdate(sql);
			
			conn.commit();
			
			AuditTrail.logActivity("1610214", "4", null, session, "UPD",
					"FAIL [" + idPerjanjian
							+ "] DISIMPAN");
			
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private String StringToDate(String tarikh){
		return "to_date('" + tarikh + "','dd/MM/yyyy')";
	}

	public Long getIdPerjanjian() {
		return idPerjanjian;
	}

	public void setIdPerjanjian(Long idPerjanjian) {
		this.idPerjanjian = idPerjanjian;
	}

	public Long getIdPermohonan() {
		return idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public String getTarikhMulaPerjanjian() {
		return tarikhMulaPerjanjian;
	}

	public void setTarikhMulaPerjanjian(String tarikhMulaPerjanjian) {
		this.tarikhMulaPerjanjian = tarikhMulaPerjanjian;
	}

	public String getTempoh() {
		return tempoh;
	}

	public void setTempoh(String tempoh) {
		this.tempoh = tempoh;
	}

	public String getTarikhTamatPerjanjian() {
		return tarikhTamatPerjanjian;
	}

	public void setTarikhTamatPerjanjian(String tarikhTamatPerjanjian) {
		this.tarikhTamatPerjanjian = tarikhTamatPerjanjian;
	}

	public Double getKadarSewa() {
		return kadarSewa;
	}

	public void setKadarSewa(Double kadarSewa) {
		this.kadarSewa = kadarSewa;
	}

	public Double getCagaran() {
		return cagaran;
	}

	public void setCagaran(Double cagaran) {
		this.cagaran = cagaran;
	}

	public String getNoSiri() {
		return noSiri;
	}

	public void setNoSiri(String noSiri) {
		this.noSiri = noSiri;
	}

	public String getFlagPerjanjian() {
		return flagPerjanjian;
	}

	public void setFlagPerjanjian(String flagPerjanjian) {
		this.flagPerjanjian = flagPerjanjian;
	}

	public Long getIdMasuk() {
		return idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public String getTarikhMasuk() {
		return tarikhMasuk;
	}

	public void setTarikhMasuk(String tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Long getIdKemaskini() {
		return idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public String getTarikhKemaskini() {
		return tarikhKemaskini;
	}

	public void setTarikhKemaskini(String tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public String getTarikhMulaDasar() {
		return tarikhMulaDasar;
	}

	public void setTarikhMulaDasar(String tarikhMulaDasar) {
		this.tarikhMulaDasar = tarikhMulaDasar;
	}

	public String getTarikhTamatDasar() {
		return tarikhTamatDasar;
	}

	public void setTarikhTamatDasar(String tarikhTamatDasar) {
		this.tarikhTamatDasar = tarikhTamatDasar;
	}

	public Double getRoyalti() {
		return royalti;
	}

	public void setRoyalti(Double royalti) {
		this.royalti = royalti;
	}

	public String getTarikhSuratNotifikasi() {
		return tarikhSuratNotifikasi;
	}

	public void setTarikhSuratNotifikasi(String tarikhSuratNotifikasi) {
		this.tarikhSuratNotifikasi = tarikhSuratNotifikasi;
	}

	public String getFlagPost() {
		return flagPost;
	}

	public void setFlagPost(String flagPost) {
		this.flagPost = flagPost;
	}

}
