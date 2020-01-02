package etapp.entity.htp;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import etapp.entity.Permohonan;
import etapp.entity.rujukan.Bandar;
import etapp.entity.rujukan.Daerah;
import etapp.entity.rujukan.JenisNoPB;
import etapp.entity.rujukan.JenisPB;
import etapp.entity.rujukan.Negeri;

@Entity
@Table(name = "TBLHTPPEMAJU")
public class Pemaju{
	// Fields
	@Id @Column(name="ID_PEMAJU")
	private Long id;	
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	@ManyToOne @JoinColumn(name="ID_JENISNOPB")
	private JenisNoPB jenisNoPB;
	@ManyToOne @JoinColumn(name="ID_JENISPB")
	private JenisPB jenisPB;
	@ManyToOne @JoinColumn(name="ID_NEGERI")
	private Negeri negeri;
	@ManyToOne @JoinColumn(name="ID_DAERAH")
	private Daerah daerah;
	@ManyToOne @JoinColumn(name="ID_BANDAR")
	private Bandar bandar;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="pemaju")
	private List<Pengarah> listPengarah;

	@Column(name="NO_OPB")
	private String noRujukan; //no syarikat
	@Column(name="NO_RUJUKAN_PEMAJU")
	private String noRujukanPemaju; // lesen
	@Column(name="NAMA_PEMAJU")
	private String nama;
	@Column(name="ALAMAT_PEMAJU1")
	private String alamat1;
	@Column(name="ALAMAT_PEMAJU2")
	private String alamat2;
	@Column(name="ALAMAT_PEMAJU3")
	private String alamat3;	
	@Column(name="POSKOD_PEMAJU")
	private String poskod;	
	@Column(name="NO_TEL")
	private String noTel;
	@Column(name="NO_FAX")
	private String noFax;
	@Column(name="EMEL")
	private String emel;	
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
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
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
	public Bandar getBandar() {
		return bandar;
	}
	public void setBandar(Bandar bandar) {
		this.bandar = bandar;
	}
	public String getNoRujukan() {
		return noRujukan;
	}
	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
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
	public String getNoTel() {
		return noTel;
	}
	public void setNoTel(String noTel) {
		this.noTel = noTel;
	}
	public String getNoFax() {
		return noFax;
	}
	public void setNoFax(String noFax) {
		this.noFax = noFax;
	}

	public JenisNoPB getJenisNoPB() {
		return jenisNoPB;
	}
	public void setJenisNoPB(JenisNoPB jenisNoPB) {
		this.jenisNoPB = jenisNoPB;
	}
	public JenisPB getJenisPB() {
		return jenisPB;
	}
	public void setJenisPB(JenisPB jenisPB) {
		this.jenisPB = jenisPB;
	}
	public String getNoRujukanPemaju() {
		return noRujukanPemaju;
	}
	public void setNoRujukanPemaju(String noRujukanPemaju) {
		this.noRujukanPemaju = noRujukanPemaju;
	}
	public String getEmel() {
		return emel;
	}
	public void setEmel(String emel) {
		this.emel = emel;
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
	
	public List<Pengarah> getListPengarah() {
		return listPengarah;
	}
	public void setListPengarah(List<Pengarah> listPengarah) {
		this.listPengarah = listPengarah;
	}

}