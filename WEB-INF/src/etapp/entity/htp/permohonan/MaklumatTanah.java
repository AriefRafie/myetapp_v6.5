package etapp.entity.htp.permohonan;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import etapp.entity.htp.HakmilikUrusan;

@Entity
@Table(name = "TBLHTPMAKLUMATTANAH")
public class MaklumatTanah {
	@Id @Column(name="ID_MAKLUMATTANAH")
	private long id;
	@ManyToOne @JoinColumn(name="ID_HAKMILIKURUSAN")
	private HakmilikUrusan hakmilikUrusan;
	
	@Column(name="NOMBOR")
	private String nombor;
	@Column(name="JARAK_BANDAR")
	private String jarakBandar;
	@Column(name="KETERANGAN_BANDAR")
	private String keteranganBandar;
	@Column(name="JARAK_JALAN")
	private String jarakJalan;
	@Column(name="KETERANGAN_JALAN")
	private String keteranganJalan;
	@Column(name="JARAK_KERETAPI")
	private String jarakKeretapi;
	@Column(name="KETERANGAN_KERETAPI")
	private String keteranganKeretapi;
	@Column(name="JARAK_SUNGAI")
	private String jarakSungai;
	@Column(name="KETERANGAN_SUNGAI")
	private String keteranganSungai;
	@Column(name="SEMPADAN_UTARA")
	private String sempadanUtara;
	@Column(name="SEMPADAN_SELATAN")
	private String sempadanSelatan;
	@Column(name="SEMPADAN_TIMUR")
	private String sempadanTimur;
	@Column(name="SEMPADAN_BARAT")
	private String sempadanBarat;
	@Column(name="LAIN_LAIN")
	private String lainLain;
	@Column(name="KETERANGAN")
	private String keterangan;
	@Column(name="LOKASI")
	private String lokasi;
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
	public HakmilikUrusan getHakmilikUrusan() {
		return hakmilikUrusan;
	}
	public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
		this.hakmilikUrusan = hakmilikUrusan;
	}
	public String getNombor() {
		return nombor;
	}
	public void setNombor(String nombor) {
		this.nombor = nombor;
	}
	public String getJarakBandar() {
		return jarakBandar;
	}
	public void setJarakBandar(String jarakBandar) {
		this.jarakBandar = jarakBandar;
	}
	public String getKeteranganBandar() {
		return keteranganBandar;
	}
	public void setKeteranganBandar(String keteranganBandar) {
		this.keteranganBandar = keteranganBandar;
	}
	public String getJarakJalan() {
		return jarakJalan;
	}
	public void setJarakJalan(String jarakJalan) {
		this.jarakJalan = jarakJalan;
	}
	public String getKeteranganJalan() {
		return keteranganJalan;
	}
	public void setKeteranganJalan(String keteranganJalan) {
		this.keteranganJalan = keteranganJalan;
	}
	public String getJarakKeretapi() {
		return jarakKeretapi;
	}
	public void setJarakKeretapi(String jarakKeretapi) {
		this.jarakKeretapi = jarakKeretapi;
	}
	public String getKeteranganKeretapi() {
		return keteranganKeretapi;
	}
	public void setKeteranganKeretapi(String keteranganKeretapi) {
		this.keteranganKeretapi = keteranganKeretapi;
	}
	public String getJarakSungai() {
		return jarakSungai;
	}
	public void setJarakSungai(String jarakSungai) {
		this.jarakSungai = jarakSungai;
	}
	public String getKeteranganSungai() {
		return keteranganSungai;
	}
	public void setKeteranganSungai(String keteranganSungai) {
		this.keteranganSungai = keteranganSungai;
	}
	public String getSempadanUtara() {
		return sempadanUtara;
	}
	public void setSempadanUtara(String sempadanUtara) {
		this.sempadanUtara = sempadanUtara;
	}
	public String getSempadanSelatan() {
		return sempadanSelatan;
	}
	public void setSempadanSelatan(String sempadanSelatan) {
		this.sempadanSelatan = sempadanSelatan;
	}
	public String getSempadanTimur() {
		return sempadanTimur;
	}
	public void setSempadanTimur(String sempadanTimur) {
		this.sempadanTimur = sempadanTimur;
	}
	public String getSempadanBarat() {
		return sempadanBarat;
	}
	public void setSempadanBarat(String sempadanBarat) {
		this.sempadanBarat = sempadanBarat;
	}
	public String getLainLain() {
		return lainLain;
	}
	public void setLainLain(String lainLain) {
		this.lainLain = lainLain;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public String getLokasi() {
		return lokasi;
	}
	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
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
