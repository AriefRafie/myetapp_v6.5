package etapp.entity.htp.perletakhakan;

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
@Table(name = "TBLHTPBORANG30A")
public class Borang30A {
	@Id @Column(name="ID_BORANG30A")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_SURAT")
	private Date tarikhSurat;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMA")
	private Date tarikhTerima;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_HANTAR")
	private Date tarikhHantar;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TANDATANGAN_PTP")
	private Date tarikhTandatanganPTP;	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_DAFTAR")
	private Date tarikhDaftar;		
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
	public Date getTarikhSurat() {
		return tarikhSurat;
	}
	public void setTarikhSurat(Date tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
	}
	public Date getTarikhTerima() {
		return tarikhTerima;
	}
	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}
	public Date getTarikhHantar() {
		return tarikhHantar;
	}
	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}
	public Date getTarikhTandatanganPTP() {
		return tarikhTandatanganPTP;
	}
	public void setTarikhTandatanganPTP(Date tarikhTandatanganPTP) {
		this.tarikhTandatanganPTP = tarikhTandatanganPTP;
	}
	public Date getTarikhDaftar() {
		return tarikhDaftar;
	}
	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}
	public String getNoPerserahan() {
		return noPerserahan;
	}
	public void setNoPerserahan(String noPerserahan) {
		this.noPerserahan = noPerserahan;
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
