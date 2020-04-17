package etapp.entity.htp.penswastaan;

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
@Table(name = "TBLHTPPERJANJIANSWASTA")
public class PerjanjianSwasta {
	@Id @Column(name="ID_PERJANJIANSWASTA")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	@ManyToOne @JoinColumn(name="ID_PERJANJIAN")
	private Perjanjian perjanjian;
	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEPUTUSAN")
	private Date tarikhKeputusan;
	@Column(name="NILAI_TANAH") 
	private double nilaiTanah = 0.00;
	@Column(name="NILAI_PROJEK") 
	private double nilaiProjek = 0.00;
	@Column(name="CARA_LAKSANAFEE")
	private String caraLaksanaBayaran;
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
	public Date getTarikhKeputusan() {
		return tarikhKeputusan;
	}
	public void setTarikhKeputusan(Date tarikhKeputusan) {
		this.tarikhKeputusan = tarikhKeputusan;
	}
	public double getNilaiTanah() {
		return nilaiTanah;
	}
	public void setNilaiTanah(double nilaiTanah) {
		this.nilaiTanah = nilaiTanah;
	}
	public double getNilaiProjek() {
		return nilaiProjek;
	}
	public void setNilaiProjek(double nilaiProjek) {
		this.nilaiProjek = nilaiProjek;
	}
	public String getCaraLaksanaBayaran() {
		return caraLaksanaBayaran;
	}
	public void setCaraLaksanaBayaran(String caraLaksanaBayaran) {
		this.caraLaksanaBayaran = caraLaksanaBayaran;
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
