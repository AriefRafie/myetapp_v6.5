package ekptg.model.htp.entity;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PerakuanJawatankuasa implements java.io.Serializable {
	// Fields
	private Long idPerakuanjawatankuasa;
	private Long idPermohonan;
	private String txtTarikhPerakuan;
	private String ulasan;
	private String keputusan;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Long getIdPerakuanJawatankuasa() {
		return this.idPerakuanjawatankuasa;
	}
	public void setIdPerakuanJawatankuasa(Long idPerakuanjawatankuasa) {
		this.idPerakuanjawatankuasa = idPerakuanjawatankuasa;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}
	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public void setTarikhPerakuanTxt(String txtTarikhPerakuan) {
		this.txtTarikhPerakuan = txtTarikhPerakuan;
	}
	public String getTarikhPerakuanTxt() {
		return this.txtTarikhPerakuan;
	}
	public Date getTarikhPerakuan() {
		return new Date(this.txtTarikhPerakuan);
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

}