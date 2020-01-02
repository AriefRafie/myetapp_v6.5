package ekptg.model.php2.modelTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class TblPhpHakmilikPermohonanModel {
	
	//DB columns
	private Long idHakmilikPermohonan;
	private Long idPermohonan;
	private Long idMasuk;
	private String tarikhMasuk;
	private Long idKemaskini;
	private String tarikhKemaskini;
	private Long idHakmilikAgensi;
	private Double nilaian;
	private Long idHakmilik;
	private String flagBorangK;
	private Long idBorangK;
	private Long idHakmilikUrusan;
	private Long idPhpBorangK;
	private String flagHakmilik = "U";
	private Long idPptBorangK;
	private Double nilaianBangunan;
	private Long idHakmilikSementara;
	
	//non-DB columns
	private String flagPost;

	public String save(TblPhpHakmilikPermohonanModel model, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		
		Long idHakmilikPermohonan = null;
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(model.getIdPermohonan()!=null){
				r.add("ID_PERMOHONAN", model.getIdPermohonan());
			}
			if(model.getIdHakmilikAgensi()!=null){
				r.add("ID_HAKMILIKAGENSI", model.getIdHakmilikAgensi());
			}
			if(model.getNilaian()!=null){
				r.add("NILAIAN", model.getNilaian());
			}
			if(model.getIdHakmilik()!=null){
				r.add("ID_HAKMILIK", model.getIdHakmilik());
			}
			if(model.getFlagBorangK()!=null){
				r.add("FLAG_BORANGK", model.getFlagBorangK());
			}
			if(model.getIdBorangK()!=null){
				r.add("ID_BORANGK", model.getIdBorangK());
			}
			if(model.getIdHakmilikUrusan()!=null){
				r.add("ID_HAKMILIKURUSAN", model.getIdHakmilikUrusan());
			}
			if(model.getIdPhpBorangK()!=null){
				r.add("ID_PHPBORANGK", model.getIdPhpBorangK());
			}
			if(model.getFlagHakmilik()!=null){
				r.add("FLAG_HAKMILIK", model.getFlagHakmilik());
			}
			if(model.getIdPptBorangK()!=null){
				r.add("ID_PPTBORANGK", model.getIdPptBorangK());
			}
			if(model.getNilaianBangunan()!=null){
				r.add("NILAIAN_BANGUNAN", model.getNilaianBangunan());
			}
			if(model.getIdHakmilikSementara()!=null){
				r.add("ID_HAKMILIKSEMENTARA", model.getIdHakmilikSementara());
			}
			
			if(model.getFlagPost().equals("insert")){
				idHakmilikPermohonan = DB.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ");
				r.add("ID_HAKMILIKPERMOHONAN", idHakmilikPermohonan);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				
				sql = r.getSQLInsert("TBLPHPHAKMILIKPERMOHONAN");
			} else if(model.getFlagPost().equals("update")){
				idHakmilikPermohonan = model.getIdHakmilikPermohonan();
				r.update("ID_HAKMILIKPERMOHONAN", idHakmilikPermohonan);
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				
				sql = r.getSQLUpdate("TBLPHPHAKMILIKPERMOHONAN");
			}
			stmt.executeUpdate(sql);
			
			conn.commit();
			
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
		
		return idHakmilikPermohonan.toString();
	}
	
	public Long getIdHakmilikPermohonan() {
		return idHakmilikPermohonan;
	}
	public void setIdHakmilikPermohonan(Long idHakmilikPermohonan) {
		this.idHakmilikPermohonan = idHakmilikPermohonan;
	}
	public Long getIdPermohonan() {
		return idPermohonan;
	}
	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
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
	public Long getIdHakmilikAgensi() {
		return idHakmilikAgensi;
	}
	public void setIdHakmilikAgensi(Long idHakmilikAgensi) {
		this.idHakmilikAgensi = idHakmilikAgensi;
	}
	public Double getNilaian() {
		return nilaian;
	}
	public void setNilaian(Double nilaian) {
		this.nilaian = nilaian;
	}
	public Long getIdHakmilik() {
		return idHakmilik;
	}
	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
	}
	public String getFlagBorangK() {
		return flagBorangK;
	}
	public void setFlagBorangK(String flagBorangK) {
		this.flagBorangK = flagBorangK;
	}
	public Long getIdBorangK() {
		return idBorangK;
	}
	public void setIdBorangK(Long idBorangK) {
		this.idBorangK = idBorangK;
	}
	public Long getIdHakmilikUrusan() {
		return idHakmilikUrusan;
	}
	public void setIdHakmilikUrusan(Long idHakmilikUrusan) {
		this.idHakmilikUrusan = idHakmilikUrusan;
	}
	public Long getIdPhpBorangK() {
		return idPhpBorangK;
	}
	public void setIdPhpBorangK(Long idPhpBorangK) {
		this.idPhpBorangK = idPhpBorangK;
	}
	public String getFlagHakmilik() {
		return flagHakmilik;
	}
	public void setFlagHakmilik(String flagHakmilik) {
		this.flagHakmilik = flagHakmilik;
	}
	public Long getIdPptBorangK() {
		return idPptBorangK;
	}
	public void setIdPptBorangK(Long idPptBorangK) {
		this.idPptBorangK = idPptBorangK;
	}
	public Double getNilaianBangunan() {
		return nilaianBangunan;
	}
	public void setNilaianBangunan(Double nilaianBangunan) {
		this.nilaianBangunan = nilaianBangunan;
	}
	public Long getIdHakmilikSementara() {
		return idHakmilikSementara;
	}
	public void setIdHakmilikSementara(Long idHakmilikSementara) {
		this.idHakmilikSementara = idHakmilikSementara;
	}
	public String getFlagPost() {
		return flagPost;
	}
	public void setFlagPost(String flagPost) {
		this.flagPost = flagPost;
	}

}
