package ekptg.model.htp.entity;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PajakanUlasan implements java.io.Serializable {
	// Fields
	private Long idUlasan;
	private Long idPermohonan;
	private String noRujukan;
	//private Pajakan pajakan;
	private String txtTarikhHantar;
	private String txtTarikhTerima;
	private String ulasan;
	private String keputusan;
	private Date tarikhHantar;
	private Date tarikhTerima;
	private String noHakmilik;
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
//	public Pajakan getPajakan() {
//		return pajakan;
//	}
//	public void setPajakan(Pajakan pajakan) {
//		this.pajakan = pajakan;
//	}

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

	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}
	public String getNoHakmilik() {
		return this.noHakmilik;
	}

}