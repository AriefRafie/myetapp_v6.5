package etapp.entity.htp.rekod;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TBLHTPGAMBAR")
public class Imej {
	@Id @Column(name="ID_GAMBAR")
	private long id;
	@ManyToOne @JoinColumn(name="ID_HAKMILIK")
	private Hakmilik hakmilik;
	
	@Column(name="PERIHAL_IMEJ")
	private String perihal;
	@Column(name="RINGKASAN")
	private String ringkasan;
	@Column(name="CONTENT")
	private long content;
	@Column(name="JENIS_MIME")
	private String mimeType;
	@Column(name="NAMA_FAIL")
	private String namaFail;
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
	public Hakmilik getHakmilik() {
		return hakmilik;
	}
	public void setHakmilik(Hakmilik hakmilik) {
		this.hakmilik = hakmilik;
	}
	public String getPerihal() {
		return perihal;
	}
	public void setPerihal(String perihal) {
		this.perihal = perihal;
	}
	public String getRingkasan() {
		return ringkasan;
	}
	public void setRingkasan(String ringkasan) {
		this.ringkasan = ringkasan;
	}
	public long getContent() {
		return content;
	}
	public void setContent(long content) {
		this.content = content;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getNamaFail() {
		return namaFail;
	}
	public void setNamaFail(String namaFail) {
		this.namaFail = namaFail;
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
