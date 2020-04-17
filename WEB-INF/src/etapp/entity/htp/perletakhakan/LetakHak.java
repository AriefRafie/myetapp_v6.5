package etapp.entity.htp.perletakhakan;

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
@Table(name = "TBLHTPLETAKHAK")
public class LetakHak {
	@Id @Column(name="ID_KEPUTUSANMOHON")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	
	@Column(name="NO_AKTA")
	private String noAkta;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KUATKUASA")
	private Date tarikhKuatkuasa;
	@Column(name="TAJUK_AKTA")
	private String tajukAkta;
	@Column(name="NO_SAMANMULA")
	private String noSaman;	
	@Column(name="NAMA_MAHKAMAH")
	private String namaMahkamah;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_PERINTAH")
	private Date tarikhPerintah;
	@Column(name="NAMA_ASAL")
	private String namaAsal;
	@Column(name="NAMA_BARU")
	private String namaBaru;
	@Column(name="ALAMAT")
	private String alamat;	
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
	public String getNoAkta() {
		return noAkta;
	}
	public void setNoAkta(String noAkta) {
		this.noAkta = noAkta;
	}
	public Date getTarikhKuatkuasa() {
		return tarikhKuatkuasa;
	}
	public void setTarikhKuatkuasa(Date tarikhKuatkuasa) {
		this.tarikhKuatkuasa = tarikhKuatkuasa;
	}
	public String getTajukAkta() {
		return tajukAkta;
	}
	public void setTajukAkta(String tajukAkta) {
		this.tajukAkta = tajukAkta;
	}
	public String getNoSaman() {
		return noSaman;
	}
	public void setNoSaman(String noSaman) {
		this.noSaman = noSaman;
	}
	public String getNamaMahkamah() {
		return namaMahkamah;
	}
	public void setNamaMahkamah(String namaMahkamah) {
		this.namaMahkamah = namaMahkamah;
	}
	public Date getTarikhPerintah() {
		return tarikhPerintah;
	}
	public void setTarikhPerintah(Date tarikhPerintah) {
		this.tarikhPerintah = tarikhPerintah;
	}
	public String getNamaAsal() {
		return namaAsal;
	}
	public void setNamaAsal(String namaAsal) {
		this.namaAsal = namaAsal;
	}
	public String getNamaBaru() {
		return namaBaru;
	}
	public void setNamaBaru(String namaBaru) {
		this.namaBaru = namaBaru;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
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
