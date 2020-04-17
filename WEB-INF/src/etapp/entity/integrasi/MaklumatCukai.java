package etapp.entity.integrasi;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "TBLINTLHDNCUKAI")
public class MaklumatCukai {
	@Id @Column(name="ID_LHDNCUKAI")
	private long id;
	//@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	//private Permohonan permohonan;
	@Column(name="ID_SIMATI")
	private long idSimati;
	@Column(name="CUKAI")
	private Double cukai;
	@Column(name="TUNGGAKAN")
	private Double tunggakan;
	@Column(name="JUMLAH")
	private Double jumlah;
	@Column(name="BAKI")
	private Double baki;	
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;
	
	@Transient
	private int bil;

	
	public int getBil() {
		return bil;
	}
	public void setBil(int bil) {
		this.bil = bil;
	}
	//
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
//	public Permohonan getPermohonan() {
//		return permohonan;
//	}
//	public void setPermohonan(Permohonan permohonan) {
//		this.permohonan = permohonan;
//	}
	public Long getIdMasuk() {
		return idMasuk;
	}
	public long getIdSimati() {
		return idSimati;
	}
	public void setIdSimati(long idSimati) {
		this.idSimati = idSimati;
	}
	public Double getCukai() {
		return cukai;
	}
	public void setCukai(Double cukai) {
		this.cukai = cukai;
	}
	public Double getTunggakan() {
		return tunggakan;
	}
	public void setTunggakan(Double tunggakan) {
		this.tunggakan = tunggakan;
	}
	public Double getJumlah() {
		return jumlah;
	}
	public void setJumlah(Double jumlah) {
		this.jumlah = jumlah;
	}
	public Double getBaki() {
		return baki;
	}
	public void setBaki(Double baki) {
		this.baki = baki;
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
