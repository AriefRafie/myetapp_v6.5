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

@Entity
@Table(name = "TBLHTPULASANNILAI")
public class UlasanNilai {
	@Id @Column(name="ID_ULASANNILAI")
	private long id;
	@ManyToOne @JoinColumn(name="ID_ULASANTEKNIKAL")
	private UlasanTeknikal UlasanTeknikal;

	@Column(name="TAHUN_NILAIAN") 
	private int tahunNilaian ;
	@Column(name="NILAI_TANAH") 
	private double nilaiTanah = 0.00;
	@Column(name="SYOR_BAYARAN") 
	private double syorBayaran = 0.00;
	@Column(name="KETERANGAN")
	private String keterangan;
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
	public UlasanTeknikal getUlasanTeknikal() {
		return UlasanTeknikal;
	}
	public void setUlasanTeknikal(UlasanTeknikal ulasanTeknikal) {
		UlasanTeknikal = ulasanTeknikal;
	}
	public int getTahunNilaian() {
		return tahunNilaian;
	}
	public void setTahunNilaian(int tahunNilaian) {
		this.tahunNilaian = tahunNilaian;
	}
	public double getNilaiTanah() {
		return nilaiTanah;
	}
	public void setNilaiTanah(double nilaiTanah) {
		this.nilaiTanah = nilaiTanah;
	}
	public double getSyorBayaran() {
		return syorBayaran;
	}
	public void setSyorBayaran(double syorBayaran) {
		this.syorBayaran = syorBayaran;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
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
