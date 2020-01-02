package etapp.entity.htp;

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
@Table(name = "TBLHTPKEPUTUSANMOHON")
public class KeputusanMohon {
	@Id @Column(name="ID_KEPUTUSANMOHON")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	
	@Column(name="NO_RUJUKAN_KEPUTUSAN")
	private String noRujukanKeputusan;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEPUTUSAN")
	private Date tarikhKeputusan;
	@Column(name="ULASAN")
	private String ulasan;
	@Column(name="STATUS")
	private String statusKeputusan;	
	@Column(name="ULASAN_KJP")
	private String ulasanKJP;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;
	//
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public String getNoRujukanKeputusan() {
		return noRujukanKeputusan;
	}
	public void setNoRujukanKeputusan(String noRujukanKeputusan) {
		this.noRujukanKeputusan = noRujukanKeputusan;
	}
	public Date getTarikhKeputusan() {
		return tarikhKeputusan;
	}
	public void setTarikhKeputusan(Date tarikhKeputusan) {
		this.tarikhKeputusan = tarikhKeputusan;
	}
	public String getUlasan() {
		return ulasan;
	}
	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}
	public String getStatusKeputusan() {
		return statusKeputusan;
	}
	public void setStatusKeputusan(String statusKeputusan) {
		this.statusKeputusan = statusKeputusan;
	}
	public String getUlasanKJP() {
		return ulasanKJP;
	}
	public void setUlasanKJP(String ulasanKJP) {
		this.ulasanKJP = ulasanKJP;
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
