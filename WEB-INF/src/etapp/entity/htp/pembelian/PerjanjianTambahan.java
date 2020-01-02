package etapp.entity.htp.pembelian;

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
import etapp.entity.htp.Perjanjian;

@Entity
@Table(name = "TBLHTPPERJANJIANTAMBAHAN")
public class PerjanjianTambahan {
	@Id @Column(name="ID_PINDAHMILIK")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	@ManyToOne @JoinColumn(name="ID_PERJANJIAN")
	private Perjanjian perjanjian;
	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMA")
	private Date tarikhTerima;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TANDATANGAN")
	private Date tarikhTandatangan;	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_PERJANJIAN")
	private Date tarikhPerjanjian;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_HANTAR")
	private Date tarikhHantar;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_AKHIRPERJANJIAN")
	private Date tarikhAkhirPerjanjian;
	@Column(name="SEBAB")
	private String sebab;
	@Column(name="TEMPOH")
	private String tempoh;
	@Column(name="ULASAN")
	private String ulasan;

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
	public Date getTarikhTerima() {
		return tarikhTerima;
	}
	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}
	public Date getTarikhTandatangan() {
		return tarikhTandatangan;
	}
	public void setTarikhTandatangan(Date tarikhTandatangan) {
		this.tarikhTandatangan = tarikhTandatangan;
	}
	public Date getTarikhPerjanjian() {
		return tarikhPerjanjian;
	}
	public void setTarikhPerjanjian(Date tarikhPerjanjian) {
		this.tarikhPerjanjian = tarikhPerjanjian;
	}
	public Date getTarikhHantar() {
		return tarikhHantar;
	}
	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}
	public Date getTarikhAkhirPerjanjian() {
		return tarikhAkhirPerjanjian;
	}
	public void setTarikhAkhirPerjanjian(Date tarikhAkhirPerjanjian) {
		this.tarikhAkhirPerjanjian = tarikhAkhirPerjanjian;
	}
	public String getSebab() {
		return sebab;
	}
	public void setSebab(String sebab) {
		this.sebab = sebab;
	}
	public String getTempoh() {
		return tempoh;
	}
	public void setTempoh(String tempoh) {
		this.tempoh = tempoh;
	}
	public String getUlasan() {
		return ulasan;
	}
	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
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
