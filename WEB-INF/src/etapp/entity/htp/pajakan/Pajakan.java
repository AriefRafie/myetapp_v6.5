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

@Entity
@Table(name = "TBLHTPPAJAKAN")
public class Pajakan {
	@Id @Column(name="ID_PAJAKAN")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	
	@Column(name="TUJUAN")
	private String tujuan;
	@Column(name="TEMPOH_PAJAKAN")
	private String tempohPajakan;
;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MULA_PAJAKAN")
	private Date tarikhMula;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TAMATPAJAKAN")
	private Date tarikhTamat;
	@Column(name="CARA_BAYAR")
	private String caraBayar;
	@Column(name="KATEGORI_CUKAI")
	private String kategoriCukai;
	@Column(name="KADAR_CUKAI") 
	private double cukai = 0.00;
	@Column(name="KADAR_PAJAKAN") 
	private double pajakan = 0.00;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TANDATANGAN")
	private Date tarikhTandatangan;
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
	public String getTujuan() {
		return tujuan;
	}
	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}
	public String getTempohPajakan() {
		return tempohPajakan;
	}
	public void setTempohPajakan(String tempohPajakan) {
		this.tempohPajakan = tempohPajakan;
	}
	public Date getTarikhMula() {
		return tarikhMula;
	}
	public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
	}
	public Date getTarikhTamat() {
		return tarikhTamat;
	}
	public void setTarikhTamat(Date tarikhTamat) {
		this.tarikhTamat = tarikhTamat;
	}
	public String getCaraBayar() {
		return caraBayar;
	}
	public void setCaraBayar(String caraBayar) {
		this.caraBayar = caraBayar;
	}
	public String getKategoriCukai() {
		return kategoriCukai;
	}
	public void setKategoriCukai(String kategoriCukai) {
		this.kategoriCukai = kategoriCukai;
	}
	public double getCukai() {
		return cukai;
	}
	public void setCukai(double cukai) {
		this.cukai = cukai;
	}
	public double getPajakan() {
		return pajakan;
	}
	public void setPajakan(double pajakan) {
		this.pajakan = pajakan;
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
	
	//
	
	
}
