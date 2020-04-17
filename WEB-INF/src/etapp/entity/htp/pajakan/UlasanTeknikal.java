package etapp.entity.htp.pajakan;

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
import etapp.entity.rujukan.Agensi;
import etapp.entity.rujukan.Daerah;
import etapp.entity.rujukan.JenisDokumen;
import etapp.entity.rujukan.Kementerian;
import etapp.entity.rujukan.Negeri;
import etapp.entity.rujukan.Pejabat;

@Entity
@Table(name = "TBLHTPULASANTEKNIKAL")
public class UlasanTeknikal{
	@Id @Column(name="ID_ULASANTEKNIKAL")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	@ManyToOne @JoinColumn(name="ID_NEGERI")
	private Negeri negeri;
	@ManyToOne @JoinColumn(name="ID_DAERAH")
	private Daerah daerah;
	@ManyToOne @JoinColumn(name="ID_KEMENTERIAN")
	private Kementerian kementerian;
	@ManyToOne @JoinColumn(name="ID_AGENSI")
	private Agensi agensi;
	@ManyToOne @JoinColumn(name="ID_JENISDOKUMEN")
	private JenisDokumen jenisDokumen;
	@ManyToOne @JoinColumn(name="ID_PEJABAT")
	private Pejabat pejabat;

	@Column(name="NO_RUJKJT")
	private String noRujukan;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_HANTAR")
	private Date tarikhHantar;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMA")
	private Date tarikhTerima;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_SURAT_KEPUTUSAN")
	private Date tarikhSuratKeputusan;	
	@Column(name="ULASAN")
	private String ulasan;
	@Column(name="STATUS_KEPUTUSAN")	
	private String status;
	@Column(name="NAMA_PEGAWAI")	
	private String namaPegawai;	
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
	public Date getTarikhHantar() {
		return tarikhHantar;
	}
	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}
	public Date getTarikhTerima() {
		return tarikhTerima;
	}
	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}
	public Date getTarikhSuratKeputusan() {
		return tarikhSuratKeputusan;
	}
	public void setTarikhSuratKeputusan(Date tarikhSuratKeputusan) {
		this.tarikhSuratKeputusan = tarikhSuratKeputusan;
	}
	public String getNoRujukan() {
		return noRujukan;
	}
	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}
	public String getUlasan() {
		return ulasan;
	}
	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Kementerian getKementerian() {
		return kementerian;
	}
	public void setKementerian(Kementerian kementerian) {
		this.kementerian = kementerian;
	}
	public Agensi getAgensi() {
		return agensi;
	}
	public void setAgensi(Agensi agensi) {
		this.agensi = agensi;
	}
	public JenisDokumen getJenisDokumen() {
		return jenisDokumen;
	}
	public void setJenisDokumen(JenisDokumen jenisDokumen) {
		this.jenisDokumen = jenisDokumen;
	}
	public Pejabat getPejabat() {
		return pejabat;
	}
	public void setPejabat(Pejabat pejabat) {
		this.pejabat = pejabat;
	}
	public String getNamaPegawai() {
		return namaPegawai;
	}
	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
	}
	
}
