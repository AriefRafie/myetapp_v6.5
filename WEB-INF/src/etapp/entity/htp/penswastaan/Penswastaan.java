package etapp.entity.htp.penswastaan;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import etapp.entity.Permohonan;

@Entity
@Table(name = "TBLHTPPENSWASTAAN")
public class Penswastaan{
	// Fields
	@Id @Column(name="ID_PENSWASTAAN")
	private Long id;	
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;

	@Column(name="TINDAKAN_LANJUT")
	private String tindakan; 
	@Column(name="STATUS_PTP")
	private String statusPTP;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public String getTindakan() {
		return tindakan;
	}
	public void setTindakan(String tindakan) {
		this.tindakan = tindakan;
	}
	public String getStatusPTP() {
		return statusPTP;
	}
	public void setStatusPTP(String statusPTP) {
		this.statusPTP = statusPTP;
	}
	public Long getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}
	public Date getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	public Long getIdKemaskini() {
		return idKemaskini;
	}
	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}
	public Date getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	
	

}