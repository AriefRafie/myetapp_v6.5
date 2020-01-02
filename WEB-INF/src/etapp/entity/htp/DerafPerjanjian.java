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
@Table(name = "TBLHTPDERAFPERJANJIAN")
public class DerafPerjanjian {
	@Id @Column(name="ID_DERAFPERJANJIAN")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMA")
	private Date tarikhTerima;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_HANTARPTP")
	private Date tarikhHantarPTP;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMAPTP")
	private Date tarikhTerimaPTP;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMA_PERJANJIAN")
	private Date tarikhTerimaPerjanjian;	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_HANTAR_DERAF")
	private Date tarikhHantarDeraf;	
	@Column(name="STATUS_SEMAKAN")
	private String status;
	@Column(name="FLAG_STD")
	private String flagSTD;	
	@Column(name="ULASAN_SEKSYEN")
	private String ulasanSeksyen;
	@Column(name="PEMBETULAN")
	private String ulasanPembetulan;
	@Column(name="JENIS_DOKUMEN")
	private String jenisDokumen;	
	@Column(name="MIMETYPE")
	private String mimeType;
	@Column(name="NAMA_FAIL")
	private String namaFail;
	@Column(name="CONTENT_DERAFPERJANJIAN")
	private long contentDerafPerjanjian;
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
	public Date getTarikhTerima() {
		return tarikhTerima;
	}
	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}
	public Date getTarikhHantarPTP() {
		return tarikhHantarPTP;
	}
	public void setTarikhHantarPTP(Date tarikhHantarPTP) {
		this.tarikhHantarPTP = tarikhHantarPTP;
	}
	public Date getTarikhTerimaPTP() {
		return tarikhTerimaPTP;
	}
	public void setTarikhTerimaPTP(Date tarikhTerimaPTP) {
		this.tarikhTerimaPTP = tarikhTerimaPTP;
	}
	public Date getTarikhTerimaPerjanjian() {
		return tarikhTerimaPerjanjian;
	}
	public void setTarikhTerimaPerjanjian(Date tarikhTerimaPerjanjian) {
		this.tarikhTerimaPerjanjian = tarikhTerimaPerjanjian;
	}
	public Date getTarikhHantarDeraf() {
		return tarikhHantarDeraf;
	}
	public void setTarikhHantarDeraf(Date tarikhHantarDeraf) {
		this.tarikhHantarDeraf = tarikhHantarDeraf;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFlagSTD() {
		return flagSTD;
	}
	public void setFlagSTD(String flagSTD) {
		this.flagSTD = flagSTD;
	}
	public String getUlasanSeksyen() {
		return ulasanSeksyen;
	}
	public void setUlasanSeksyen(String ulasanSeksyen) {
		this.ulasanSeksyen = ulasanSeksyen;
	}
	public String getUlasanPembetulan() {
		return ulasanPembetulan;
	}
	public void setUlasanPembetulan(String ulasanPembetulan) {
		this.ulasanPembetulan = ulasanPembetulan;
	}
	public String getJenisDokumen() {
		return jenisDokumen;
	}
	public void setJenisDokumen(String jenisDokumen) {
		this.jenisDokumen = jenisDokumen;
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
	public long getContentDerafPerjanjian() {
		return contentDerafPerjanjian;
	}
	public void setContentDerafPerjanjian(long contentDerafPerjanjian) {
		this.contentDerafPerjanjian = contentDerafPerjanjian;
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
