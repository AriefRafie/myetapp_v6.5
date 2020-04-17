package etapp.entity.htp.rekod;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TBLHTPPERGERAKAN")
public class HakmilikPergerakan {
	@Id @Column(name="ID_PERGERAKAN")
	private long id;
	@ManyToOne @JoinColumn(name="ID_HAKMILIK")
	private Hakmilik hakmilik;

	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH")
	private Date tarikh;	
	@Column(name="KEPADA") 
	private String kepada ;	
	@Column(name="KETERANGAN") 
	private String keterangan ;	
	@Column(name="BIL_SALINAN")
	private int bilSalinan;
	@Column(name="STATSU") 
	private String status ;	
	@Column(name="CATATAN")
	private String catatan;	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMBALI")
	private Date tarikhKembali;	
	@Column(name="FLAG_PILIHAN")
	private String flagPilihan;	
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
	public Hakmilik getHakmilik() {
		return hakmilik;
	}
	public void setHakmilik(Hakmilik hakmilik) {
		this.hakmilik = hakmilik;
	}
	public Date getTarikh() {
		return tarikh;
	}
	public void setTarikh(Date tarikh) {
		this.tarikh = tarikh;
	}
	public String getKepada() {
		return kepada;
	}
	public void setKepada(String kepada) {
		this.kepada = kepada;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public int getBilSalinan() {
		return bilSalinan;
	}
	public void setBilSalinan(int bilSalinan) {
		this.bilSalinan = bilSalinan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public Date getTarikhKembali() {
		return tarikhKembali;
	}
	public void setTarikhKembali(Date tarikhKembali) {
		this.tarikhKembali = tarikhKembali;
	}
	public String getFlagPilihan() {
		return flagPilihan;
	}
	public void setFlagPilihan(String flagPilihan) {
		this.flagPilihan = flagPilihan;
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
