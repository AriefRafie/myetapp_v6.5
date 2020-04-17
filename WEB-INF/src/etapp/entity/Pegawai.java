package etapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import etapp.entity.rujukan.Bandar;
import etapp.entity.rujukan.Jawatan;
import etapp.entity.rujukan.Mukim;
import etapp.entity.rujukan.Negeri;
import etapp.entity.rujukan.Seksyen;
import etapp.entity.rujukan.Urusan;

@Entity
@Table(name = "TBLRUJPEGAWAI")
public class Pegawai{
	// Fields
	@Id @Column(name="ID_PEGAWAI")
	private Long id;	
//	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
//	private Permohonan permohonan;
	@ManyToOne @JoinColumn(name="ID_NEGERI")
	private Negeri negeri;
	//@ManyToOne @JoinColumn(name="ID_DAERAH")
	//private Daerah daerah;
	@ManyToOne @JoinColumn(name="ID_BANDAR")
	private Bandar bandar;
	@ManyToOne @JoinColumn(name="ID_MUKIM")
	private Mukim mukim;
	@ManyToOne @JoinColumn(name="ID_URUSAN")
	private Urusan urusan;
	@ManyToOne @JoinColumn(name="ID_SEKSYEN")
	private Seksyen seksyen;
	@ManyToOne @JoinColumn(name="ID_PEGAWAI")
	private Pegawai pegawai;
	@ManyToOne @JoinColumn(name="USER_ID")
	private User user;
	@ManyToOne @JoinColumn(name="ID_JAWATAN")
	private Jawatan rujJawatan;
	
	@Column(name="NAMA_PEGAWAI")
	private String nama;
	@Column(name="NO_PEKERJA")
	private String noPekerja;
	@Column(name="NO_KP")
	private String noKP;
	@Column(name="JAWATAN")
	private String jawatan;	
	@Column(name="ALAMAT1")
	private String alamat1;
	@Column(name="ALAMAT2")
	private String alamat2;
	@Column(name="ALAMAT3")
	private String alamat3;	
	@Column(name="POSKOD")
	private String poskod;	
	@Column(name="NO_TEL_PEJABAT")
	private String noTelPejabat;
	@Column(name="NO_TEL_RUMAH")
	private String noTelRumah;
	@Column(name="NO_TEL_BIMBIT")
	private String noTelBimbit;
	@Column(name="NO_FAKS")
	private String noFax;
	@Column(name="EMEL")
	private String emel;	
	@Column(name="FLAG_KONTRAK")
	private String kontrak;	
	@Column(name="FLAG_PETUGASKHAS")
	private String petugasKhas;	
	@Column(name="KOD_JAWATAN")
	private String kodJawatan;	
	@Column(name="FLAG_AKTIF")
	private String aktif;	
	@Column(name="UNIT")
	private String unit;	
	@Column(name="EXSIBIT")
	private String exsibit;	

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
	public Negeri getNegeri() {
		return negeri;
	}
	public void setNegeri(Negeri negeri) {
		this.negeri = negeri;
	}
	public Bandar getBandar() {
		return bandar;
	}
	public void setBandar(Bandar bandar) {
		this.bandar = bandar;
	}
	public Mukim getMukim() {
		return mukim;
	}
	public void setMukim(Mukim mukim) {
		this.mukim = mukim;
	}
	public Urusan getUrusan() {
		return urusan;
	}
	public void setUrusan(Urusan urusan) {
		this.urusan = urusan;
	}
	public Seksyen getSeksyen() {
		return seksyen;
	}
	public void setSeksyen(Seksyen seksyen) {
		this.seksyen = seksyen;
	}
	public Pegawai getPegawai() {
		return pegawai;
	}
	public void setPegawai(Pegawai pegawai) {
		this.pegawai = pegawai;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Jawatan getRujJawatan() {
		return rujJawatan;
	}
	public void setRujJawatan(Jawatan rujJawatan) {
		this.rujJawatan = rujJawatan;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getNoPekerja() {
		return noPekerja;
	}
	public void setNoPekerja(String noPekerja) {
		this.noPekerja = noPekerja;
	}
	public String getNoKP() {
		return noKP;
	}
	public void setNoKP(String noKP) {
		this.noKP = noKP;
	}
	public String getJawatan() {
		return jawatan;
	}
	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
	}
	public String getAlamat1() {
		return alamat1;
	}
	public void setAlamat1(String alamat1) {
		this.alamat1 = alamat1;
	}
	public String getAlamat2() {
		return alamat2;
	}
	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}
	public String getAlamat3() {
		return alamat3;
	}
	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}
	public String getPoskod() {
		return poskod;
	}
	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}
	public String getNoTelPejabat() {
		return noTelPejabat;
	}
	public void setNoTelPejabat(String noTelPejabat) {
		this.noTelPejabat = noTelPejabat;
	}
	public String getNoTelRumah() {
		return noTelRumah;
	}
	public void setNoTelRumah(String noTelRumah) {
		this.noTelRumah = noTelRumah;
	}
	public String getNoTelBimbit() {
		return noTelBimbit;
	}
	public void setNoTelBimbit(String noTelBimbit) {
		this.noTelBimbit = noTelBimbit;
	}
	public String getNoFax() {
		return noFax;
	}
	public void setNoFax(String noFax) {
		this.noFax = noFax;
	}
	public String getEmel() {
		return emel;
	}
	public void setEmel(String emel) {
		this.emel = emel;
	}
	public String getKontrak() {
		return kontrak;
	}
	public void setKontrak(String kontrak) {
		this.kontrak = kontrak;
	}
	public String getPetugasKhas() {
		return petugasKhas;
	}
	public void setPetugasKhas(String petugasKhas) {
		this.petugasKhas = petugasKhas;
	}
	public String getKodJawatan() {
		return kodJawatan;
	}
	public void setKodJawatan(String kodJawatan) {
		this.kodJawatan = kodJawatan;
	}
	public String getAktif() {
		return aktif;
	}
	public void setAktif(String aktif) {
		this.aktif = aktif;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getExsibit() {
		return exsibit;
	}
	public void setExsibit(String exsibit) {
		this.exsibit = exsibit;
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