package ekptg.model.htp.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import ekptg.model.entities.Tblrujsuburusanstatusfail;


public class KeputusanUlasan implements java.io.Serializable {
	// Fields
	private Long idUlasan;
	private Long idPermohonan;
	private String noRujukan;
	private String txtTarikhHantar;
	private String txtTarikhTerima;
	private String ulasan;
	private String keputusan;
	private Date tarikhHantar;
	private Date tarikhTerima;	
	private Tblrujsuburusanstatusfail rsusf;

	//Penambahbaikan phase3. Syaz. 24112014
	private String flagNotifikasi;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	// Constructors
//	public PajakanUlasan(Long idPermohonan) {
//		this.idPermohonan = idPermohonan;
//	}
	// Property accessors
	public Long getIdUlasan() {
		return this.idUlasan;
	}
	public void setIdUlasan(Long idUlasan) {
		this.idUlasan = idUlasan;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}
	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}
	
	public void setTarikhHantarTxt(String txtTarikhHantar) {
		this.txtTarikhHantar = txtTarikhHantar;
	}
	public String getTarikhHantarTxt() {
		return this.txtTarikhHantar;
	}
	public Date getTarikhHantar() {
		return new Date(this.txtTarikhHantar);
	}
	
	public void setTarikhTerima(String txtTarikhTerima) {
		this.txtTarikhTerima = txtTarikhTerima;
	}	
	public String getTarikhTerimaTxt() {
		return this.txtTarikhTerima;
	}
	public Date getTarikhTerima() {
		return new Date(this.txtTarikhTerima);
	}
	
	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}
	public String getUlasan() {
		return this.ulasan;
	}	

	public void setKeputusan(String keputusan) {
		this.keputusan = keputusan;
	}
	public String getKeputusan() {
		return this.keputusan;
	}
	
	public void setNo(String noRujukan) {
		this.noRujukan = noRujukan;
	}
	public String getNo() {
		return this.noRujukan;
	}
	
	public void setSubUrusanStatusFail(Tblrujsuburusanstatusfail rsusf) {
		this.rsusf = rsusf;
	}
	public Tblrujsuburusanstatusfail getSubUrusanStatusFail() {
		return this.rsusf;
	}
	
	public String getFlagNotifikasi() {
		return flagNotifikasi;
	}
	public void setFlagNotifikasi(String flagNotifikasi) {
		this.flagNotifikasi = flagNotifikasi;
	}
	
}