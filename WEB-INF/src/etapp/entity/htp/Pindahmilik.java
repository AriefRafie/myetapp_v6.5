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
@Table(name = "TBLHTPPINDAHMILIK")
public class Pindahmilik {
	@Id @Column(name="ID_PINDAHMILIK")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	@ManyToOne @JoinColumn(name="ID_HAKMILIKURUSAN")
	private HakmilikUrusan hakmilikUrusan;
	@ManyToOne @JoinColumn(name="ID_PIHAKBERKEPENTINGAN")
	private PihakBerkepentingan pihakBerkepentingan;

	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TANDATANGAN")
	private Date tarikhTandatangan;	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_HANTAR")
	private Date tarikhHantar;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_DAFTAR")
	private Date tarikhDaftar;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_BATAL_REKOD")
	private Date tarikhBatalRekod;
	@Column(name="NO_PERSERAHAN")
	private String noPerserahan;
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
	public Date getTarikhTandatangan() {
		return tarikhTandatangan;
	}
	public void setTarikhTandatangan(Date tarikhTandatangan) {
		this.tarikhTandatangan = tarikhTandatangan;
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
	public PihakBerkepentingan getPihakBerkepentingan() {
		return pihakBerkepentingan;
	}
	public void setPihakBerkepentingan(PihakBerkepentingan pihakBerkepentingan) {
		this.pihakBerkepentingan = pihakBerkepentingan;
	}
	public Date getTarikhDaftar() {
		return tarikhDaftar;
	}
	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}
	public Date getTarikhBatalRekod() {
		return tarikhBatalRekod;
	}
	public void setTarikhBatalRekod(Date tarikhBatalRekod) {
		this.tarikhBatalRekod = tarikhBatalRekod;
	}
	public String getNoPerserahan() {
		return noPerserahan;
	}
	public void setNoPerserahan(String noPerserahan) {
		this.noPerserahan = noPerserahan;
	}

	
}
