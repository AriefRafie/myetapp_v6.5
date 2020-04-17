package etapp.entity.pfd;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lebah.template.UID;
import etapp.entity.rujukan.Daerah;
import etapp.entity.rujukan.Kementerian;
import etapp.entity.rujukan.Negeri;
import etapp.entity.rujukan.Seksyen;
import etapp.entity.rujukan.Status;
import etapp.entity.rujukan.SubUrusan;
import etapp.entity.rujukan.SubUrusanStatusFail;
import etapp.entity.rujukan.Urusan;

@Entity
@Table(name = "TBLPFDFAIL")
public class Fail {
	
	@Id @Column(name="ID_FAIL")
	private String id;
//	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="fail")
//	private List<FailLain> listFailLain;
//	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="failBaru")
//	private List<FailLain> listFailBaru;
//	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="fail")
//	private List<FailMapping> listFailMapping;
//	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="failLama")
//	private List<FailMapping> listFailLama;
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="fail")
	private List<SubUrusanStatusFail> listSubUrusanStatusFail;

	@ManyToOne @JoinColumn(name="ID_NEGERI")
	private Negeri negeri;
	@ManyToOne @JoinColumn(name="ID_DAERAH")
	private Daerah daerah;
	@ManyToOne @JoinColumn(name="ID_TARAFKESELAMATAN")
	private TarafKeselamatan tarafKeselamatan;
	@ManyToOne @JoinColumn(name="ID_URUSAN")
	private Urusan urusan;
	@ManyToOne @JoinColumn(name="ID_SUBURUSAN")
	private SubUrusan subUrusan;
	@ManyToOne @JoinColumn(name="ID_SEKSYEN")
	private Seksyen seksyen;
	@ManyToOne @JoinColumn(name="ID_LOKASIFAIL")
	private LokasiFail lokasiFail;
	@ManyToOne @JoinColumn(name="ID_KEMENTERIAN")
	private Kementerian kementerian;
	@ManyToOne @JoinColumn(name="ID_FAHARASAT")
	private Faharasat faharasat;
	@ManyToOne @JoinColumn(name="ID_STATUS")
	private Status status;		
	//HTP

	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_DAFTAR_FAIL")
	private Date tarikhDaftarFail;
	@Lob @Column(name="TAJUK_FAIL")
	private String tajukFail;
	@Column(name="NO_FAIL") 
	private String noFail;
	@Column(name="NO_FAIL_ROOT") 
	private String noFailRoot;
	@Column(name="FLAG_FAIL") 
	private int flagFail;
	@Column(name="CATATAN") 
	private String catatan;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TUKAR_TARAF")
	private Date tarikhTukarTaraf;	
	@Column(name="NO_PERSERAHAN") 
	private String noPerserahan;
	@Column(name="FLAG_JENIS_FAIL") 
	private String flagJenisFail;
	@Column(name="LOKASI_FAIL") 
	private String namaLokasiFail;
	@Column(name="FAHARASAT") 
	private String namaFaharasat;
	@Column(name="NAMA_PEGAWAI_SJ") 
	private String namaPegawai;
	@Column(name="FLAG_VIEW_FILE") 
	private int flagViewFail;
	@Column(name="NO_TURUTAN") 
	private String noTurutan;
	@Column(name="NO_TURUTAN_JLD") 
	private String noTurutanJld;
	@Column(name="NO_TURUTAN_SUBJAKET") 
	private String noTurutanSubjaket;

	@Column(name="FLAG_HAPUS_FAIL") 
	private int flagHapusFail;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;
	
	public Fail() {
		setId(UID.getUID());
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Negeri getNegeri() {
		return negeri;
	}
	public void setNegeri(Negeri negeri) {
		this.negeri = negeri;
	}
	public Daerah getDaerah() {
		return daerah;
	}
	public void setDaerah(Daerah daerah) {
		this.daerah = daerah;
	}
	public TarafKeselamatan getTarafKeselamatan() {
		return tarafKeselamatan;
	}
	public void setTarafKeselamatan(TarafKeselamatan tarafKeselamatan) {
		this.tarafKeselamatan = tarafKeselamatan;
	}
	public Urusan getUrusan() {
		return urusan;
	}
	public void setUrusan(Urusan urusan) {
		this.urusan = urusan;
	}
	public SubUrusan getSubUrusan() {
		return subUrusan;
	}
	public void setSubUrusan(SubUrusan subUrusan) {
		this.subUrusan = subUrusan;
	}
	public Seksyen getSeksyen() {
		return seksyen;
	}
	public void setSeksyen(Seksyen seksyen) {
		this.seksyen = seksyen;
	}
	public LokasiFail getLokasiFail() {
		return lokasiFail;
	}
	public void setLokasiFail(LokasiFail lokasiFail) {
		this.lokasiFail = lokasiFail;
	}
	public Kementerian getKementerian() {
		return kementerian;
	}
	public void setKementerian(Kementerian kementerian) {
		this.kementerian = kementerian;
	}
	public Faharasat getFaharasat() {
		return faharasat;
	}
	public void setFaharasat(Faharasat faharasat) {
		this.faharasat = faharasat;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getTarikhDaftarFail() {
		return tarikhDaftarFail;
	}
	public void setTarikhDaftarFail(Date tarikhDaftarFail) {
		this.tarikhDaftarFail = tarikhDaftarFail;
	}
	public String getTajukFail() {
		return tajukFail;
	}
	public void setTajukFail(String tajukFail) {
		this.tajukFail = tajukFail;
	}
	public String getNoFail() {
		return noFail;
	}
	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}
	public String getNoFailRoot() {
		return noFailRoot;
	}
	public void setNoFailRoot(String noFailRoot) {
		this.noFailRoot = noFailRoot;
	}
	public int getFlagFail() {
		return flagFail;
	}
	public void setFlagFail(int flagFail) {
		this.flagFail = flagFail;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public Date getTarikhTukarTaraf() {
		return tarikhTukarTaraf;
	}
	public void setTarikhTukarTaraf(Date tarikhTukarTaraf) {
		this.tarikhTukarTaraf = tarikhTukarTaraf;
	}
	public String getNoPerserahan() {
		return noPerserahan;
	}
	public void setNoPerserahan(String noPerserahan) {
		this.noPerserahan = noPerserahan;
	}
	public String getFlagJenisFail() {
		return flagJenisFail;
	}
	public void setFlagJenisFail(String flagJenisFail) {
		this.flagJenisFail = flagJenisFail;
	}
	public String getNamaLokasiFail() {
		return namaLokasiFail;
	}
	public void setNamaLokasiFail(String namaLokasiFail) {
		this.namaLokasiFail = namaLokasiFail;
	}
	public String getNamaFaharasat() {
		return namaFaharasat;
	}
	public void setNamaFaharasat(String namaFaharasat) {
		this.namaFaharasat = namaFaharasat;
	}
	public String getNamaPegawai() {
		return namaPegawai;
	}
	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
	}
	public int getFlagViewFail() {
		return flagViewFail;
	}
	public void setFlagViewFail(int flagViewFail) {
		this.flagViewFail = flagViewFail;
	}
	public String getNoTurutan() {
		return noTurutan;
	}
	public void setNoTurutan(String noTurutan) {
		this.noTurutan = noTurutan;
	}
	public String getNoTurutanJld() {
		return noTurutanJld;
	}
	public void setNoTurutanJld(String noTurutanJld) {
		this.noTurutanJld = noTurutanJld;
	}
	public String getNoTurutanSubjaket() {
		return noTurutanSubjaket;
	}
	public void setNoTurutanSubjaket(String noTurutanSubjaket) {
		this.noTurutanSubjaket = noTurutanSubjaket;
	}
	public int getFlagHapusFail() {
		return flagHapusFail;
	}
	public void setFlagHapusFail(int flagHapusFail) {
		this.flagHapusFail = flagHapusFail;
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
	public List<SubUrusanStatusFail> getListSubUrusanStatusFail() {
		return listSubUrusanStatusFail;
	}
	public void setListSubUrusanStatusFail(
			List<SubUrusanStatusFail> listSubUrusanStatusFail) {
		this.listSubUrusanStatusFail = listSubUrusanStatusFail;
	}
		
	

}
