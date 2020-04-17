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
@Table(name = "TBLINTRUJSTATUSMODULSEMASA")
public class StatusSemasa {
	@Id @Column(name="ID_STATUSMODULSEMASA")
	private long id;
	//@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	//private Permohonan permohonan;
	@Column(name="ID_SUMBER")
	private long idSumber;
	@Column(name="ID_STATUSMODUL")
	private long idStatusModul;
	@Column(name="AKTIF")
	private String aktif;
	@Column(name="CATATAN")
	private String catatan;
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
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
		public long getIdSumber() {
		return idSumber;
	}
	public void setIdSumber(long idSumber) {
		this.idSumber = idSumber;
	}
	public long getIdStatusModul() {
		return idStatusModul;
	}
	public void setIdStatusModul(long idStatusModul) {
		this.idStatusModul = idStatusModul;
	}
	public String getAktif() {
		return aktif;
	}
	public void setAktif(String aktif) {
		this.aktif = aktif;
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
