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
import etapp.entity.rujukan.Bandar;
import etapp.entity.rujukan.Daerah;
import etapp.entity.rujukan.Negeri;

@Entity
@Table(name = "TBLHTPPEMOHON")
public class Pemohon{
	// Fields
	@Id @Column(name="ID_PEMOHON")
	private Long id;	
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	@ManyToOne @JoinColumn(name="ID_NEGERI")
	private Negeri negeri;
	@ManyToOne @JoinColumn(name="ID_DAERAH")
	private Daerah daerah;
	@ManyToOne @JoinColumn(name="ID_BANDAR")
	private Bandar bandar;

	@Column(name="NO_PEMOHON")
	private String noRujukan; 
	@Column(name="NAMA_PEMOHON")
	private String nama;
	@Column(name="ALAMAT_PEMOHON1")
	private String alamat1;
	@Column(name="ALAMAT_PEMOHON2")
	private String alamat2;
	@Column(name="ALAMAT_PEMOHON3")
	private String alamat3;	
	@Column(name="POSKOD")
	private String poskod;	
	@Column(name="NO_TEL")
	private String noTel;
	@Column(name="NO_FAX")
	private String noFax;
	@Column(name="NO_PA")
	private String noPA; 
	@Column(name="FLAG_PENJUALPEMILIK")
	private String flagPenjual; 
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

	public String getNoPA() {
		return noPA;
	}
	public void setNoPA(String noPA) {
		this.noPA = noPA;
	}
	public String getFlagPenjual() {
		return flagPenjual;
	}
	public void setFlagPenjual(String flagPenjual) {
		this.flagPenjual = flagPenjual;
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
	

}