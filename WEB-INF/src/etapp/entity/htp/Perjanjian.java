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
import etapp.entity.rujukan.Daerah;
import etapp.entity.rujukan.Negeri;

@Entity
@Table(name = "TBLHTPPERJANJIAN")
public class Perjanjian {
	@Id @Column(name="ID_PERJANJIAN")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	@ManyToOne @JoinColumn(name="ID_NEGERI")
	private Negeri negeri;
	@ManyToOne @JoinColumn(name="ID_DAERAH")
	private Daerah daerah;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="perjanjian")
	private List<PerjanjianBorang> listPerjanjianBorang;

	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEPUTUSAN")
	private Date tarikhKeputusan;
	@Column(name="NAMA_PEGAWAI")
	private String namaPegawai;
	@Column(name="NO_RUJPERJANJIAN")
	private String noRujukan;
	@Column(name="NAMA")
	private String nama;
	@Column(name="ALAMAT1")
	private String alamat1;
	@Column(name="ALAMAT2")
	private String alamat2;
	@Column(name="ALAMAT3")
	private String alamat3;	
	@Column(name="POSKOD")
	private String poskod;	
	@Column(name="TEMPOH")
	private String tempoh;	
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
	public Date getTarikhKeputusan() {
		return tarikhKeputusan;
	}
	public void setTarikhKeputusan(Date tarikhKeputusan) {
		this.tarikhKeputusan = tarikhKeputusan;
	}
	public String getNamaPegawai() {
		return namaPegawai;
	}
	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
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
	public String getTempoh() {
		return tempoh;
	}
	public void setTempoh(String tempoh) {
		this.tempoh = tempoh;
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
	public List<PerjanjianBorang> getListPerjanjianBorang() {
		return listPerjanjianBorang;
	}
	public void setListPerjanjianBorang(List<PerjanjianBorang> listPerjanjianBorang) {
		this.listPerjanjianBorang = listPerjanjianBorang;
	}

	
	
}
