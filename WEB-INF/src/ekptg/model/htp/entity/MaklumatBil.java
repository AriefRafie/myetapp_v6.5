package ekptg.model.htp.entity;

import java.text.SimpleDateFormat;

import ekptg.helpers.Utils;


public class MaklumatBil implements java.io.Serializable {

	// Fields

	private Long idPajakanKadar;
	private Pajakan pajakan;
//	private String tarikhMulaBayaran;
//	private String tarikhTamatBayaran;
//	private Date tarikhMulaBayaranDate;
//	private Date tarikhTamatBayaranDate;	
	private Double denda;
	private Double baki;
	private String noRujukan;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	// Constructors

	// Property accessors

	public Long getIdPajakanKadar() {
		return this.idPajakanKadar;
	}

	public void setIdPajakanKadar(Long idPajakanKadar) {
		this.idPajakanKadar = idPajakanKadar;
	}

	public Pajakan getPajakan() {
		return pajakan;
	}
	public void setPajakan(Pajakan pajakan) {
		this.pajakan = pajakan;
	}
	
//	public void setTarikhMulaBayaran(String tarikhMulaPajakan) {
//		this.tarikhMulaBayaran = tarikhMulaPajakan;
//	}
//
//	public String getTarikhMulaBayaran() {
//		return this.tarikhMulaBayaran;
//	}
//
//	public Date getTarikhMulaBayaranDate() {
//		return new Date(this.tarikhMulaBayaran);
//	}
//	
//	public void setTarikhTamatBayaran(String tarikhTamatpajakan) {
//		this.tarikhTamatBayaran = tarikhTamatpajakan;
//	}
//	
//	public String getTarikhTamatBayaran() {
//		return this.tarikhTamatBayaran;
//	}
//	
//	public Date getTarikhTamatBayaranDate() {
//		return new Date(this.tarikhTamatBayaran);
//	}

	public Double getBaki() {
		return this.baki;
	}

	public String getBakiString() {
		return Utils.format2Decimal(this.baki);
	}
	
	public void setBaki(Double baki) {
		this.baki = baki;
	}

	public Double getDenda() {
		return this.denda;
	}
	
	public String getDendaString() {
		return Utils.format2Decimal(this.denda);
	}
	
	public void setDenda(Double denda) {
		this.denda = denda;
	}	
	
	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}
	
	public String getNoRujukan() {
		return this.noRujukan;
	}
	
}