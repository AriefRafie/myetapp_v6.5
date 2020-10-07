package etapp.data;

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
@Table(name = "TBLONLINEEADUAN")
public class OnlineEAduanMobile {

	@Id @Column(name="ID_EADUAN")
	private Long id;
	@Column(name="ID_PENGADU")
	private Long idPengadu;
	@Column(name="NAMA_PENGADU")
	private String namaPengadu;
	@Column(name="EMAIL_PENGADU")
	private String emailPengadu;
	@Column(name="PHONE_PENGADU")
	private String phonePengadu;
	@Column(name="CATATAN")
	private String catatan;

	@ManyToOne
	@JoinColumn(name="ID_JENISADUAN")
	private RujJenisAduanMobile jenisAduan;

	@Column(name="TARIKH_MASUK")
	@Temporal(TemporalType.DATE)
	private Date tarikhMasuk;
	@Column(name="STATUS")
	private String status;
	@Column(name="TARIKH_KEMASKINI")
	@Temporal(TemporalType.DATE)
	private Date tarikhKemaskini;

	@ManyToOne
	@JoinColumn(name="ID_PEGAWAI")
	private RujPegawaiMobile pegawai;
	@ManyToOne
	@JoinColumn(name="ID_SUMBERADUAN")
	private RujSumberAduanMobile sumberAduan;

	@Column(name="TARIKH_SELESAI")
	@Temporal(TemporalType.DATE)
	private Date tarikhSelesai;
	@Column(name="CATATAN_SELESAI")
	private String catatanSelesai;

	@Column(name="FLAG_ONLINE")
	private String flagOnline;
	@Column(name="STATUS_PENYELESAIAN")
	private String statusPenyelesaian;

	@ManyToOne
	@JoinColumn(name="ID_NEGERI")
	private RujNegeriMobile negeri;


	@Column(name="NO_FAIL")
	private String noFail;

	public OnlineEAduanMobile() {
		setId(lebah.db.UniqueID.get());
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdPengadu() {
		return idPengadu;
	}
	public void setIdPengadu(Long idPengadu) {
		this.idPengadu = idPengadu;
	}
	public String getNamaPengadu() {
		return namaPengadu;
	}
	public void setNamaPengadu(String namaPengadu) {
		this.namaPengadu = namaPengadu;
	}
	public String getEmailPengadu() {
		return emailPengadu;
	}
	public void setEmailPengadu(String emailPengadu) {
		this.emailPengadu = emailPengadu;
	}
	public String getPhonePengadu() {
		return phonePengadu;
	}
	public void setPhonePengadu(String phonePengadu) {
		this.phonePengadu = phonePengadu;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public RujJenisAduanMobile getJenisAduan() {
		return jenisAduan;
	}
	public void setJenisAduan(RujJenisAduanMobile jenisAduan) {
		this.jenisAduan = jenisAduan;
	}
	public Date getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	public RujPegawaiMobile getPegawai() {
		return pegawai;
	}
	public void setPegawai(RujPegawaiMobile pegawai) {
		this.pegawai = pegawai;
	}
	public RujSumberAduanMobile getSumberAduan() {
		return sumberAduan;
	}
	public void setSumberAduan(RujSumberAduanMobile sumberAduan) {
		this.sumberAduan = sumberAduan;
	}
	public Date getTarikhSelesai() {
		return tarikhSelesai;
	}
	public void setTarikhSelesai(Date tarikhSelesai) {
		this.tarikhSelesai = tarikhSelesai;
	}
	public String getCatatanSelesai() {
		return catatanSelesai;
	}
	public void setCatatanSelesai(String catatanSelesai) {
		this.catatanSelesai = catatanSelesai;
	}
	public String getFlagOnline() {
		return flagOnline;
	}
	public void setFlagOnline(String flagOnline) {
		this.flagOnline = flagOnline;
	}
	public String getStatusPenyelesaian() {
		return statusPenyelesaian;
	}
	public void setStatusPenyelesaian(String statusPenyelesaian) {
		this.statusPenyelesaian = statusPenyelesaian;
	}
	public RujNegeriMobile getNegeri() {
		return negeri;
	}
	public void setNegeri(RujNegeriMobile negeri) {
		this.negeri = negeri;
	}
	public String getNoFail() {
		return noFail;
	}
	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}
}