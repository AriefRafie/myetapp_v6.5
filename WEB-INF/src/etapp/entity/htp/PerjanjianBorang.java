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
import etapp.entity.rujukan.JenisDokumen;

@Entity
@Table(name = "TBLHTPPERJANJIANBORANG")
public class PerjanjianBorang {
	@Id @Column(name="ID_PERJANJIANBORANG")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	@ManyToOne @JoinColumn(name="ID_PERJANJIAN")
	private Perjanjian perjanjian;
	@ManyToOne @JoinColumn(name="ID_JENISDOKUMEN")
	private JenisDokumen jenisDokumen;

	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMA")
	private Date tarikhTerima;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TANDATANGAN_PTP")
	private Date tarikhTandatanganPTP;	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_HANTAR")
	private Date tarikhHantar;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_DAFTAR")
	private Date tarikhDaftar;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMA_HAKMILIK")
	private Date tarikhTerimaHakmilik;
	@Column(name="NO_PERSERAHAN_SPTB")
	private String noPerserahan;
	@Column(name="JENIS_BORANG")
	private String jenisBorang;
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
	public Perjanjian getPerjanjian() {
		return perjanjian;
	}
	public void setPerjanjian(Perjanjian perjanjian) {
		this.perjanjian = perjanjian;
	}
	public JenisDokumen getJenisDokumen() {
		return jenisDokumen;
	}
	public void setJenisDokumen(JenisDokumen jenisDokumen) {
		this.jenisDokumen = jenisDokumen;
	}
	public Date getTarikhTerima() {
		return tarikhTerima;
	}
	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}
	public Date getTarikhTandatanganPTP() {
		return tarikhTandatanganPTP;
	}
	public void setTarikhTandatanganPTP(Date tarikhTandatanganPTP) {
		this.tarikhTandatanganPTP = tarikhTandatanganPTP;
	}
	public Date getTarikhHantar() {
		return tarikhHantar;
	}
	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}
	public Date getTarikhDaftar() {
		return tarikhDaftar;
	}
	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}
	public Date getTarikhTerimaHakmilik() {
		return tarikhTerimaHakmilik;
	}
	public void setTarikhTerimaHakmilik(Date tarikhTerimaHakmilik) {
		this.tarikhTerimaHakmilik = tarikhTerimaHakmilik;
	}
	public String getNoPerserahan() {
		return noPerserahan;
	}
	public void setNoPerserahan(String noPerserahan) {
		this.noPerserahan = noPerserahan;
	}
	public String getJenisBorang() {
		return jenisBorang;
	}
	public void setJenisBorang(String jenisBorang) {
		this.jenisBorang = jenisBorang;
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
