package etapp.entity.htp.permohonan;

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
import etapp.entity.htp.HakmilikUrusan;

@Entity
@Table(name = "TBLHTPCHARTING")
public class Charting {
	@Id @Column(name="ID_CHARTING")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	@ManyToOne @JoinColumn(name="ID_HAKMILIKURUSAN")
	private HakmilikUrusan hakmilikUrusan;
	
	@Column(name="NO_RUJUKAN_SEKSYEN")
	private String noRujukanSeksyen;
	@Column(name="FLAG_PELAN")
	private String flagPelan;
	@Column(name="FLAG_CHARTING")
	private String flagCharting;
	@Column(name="ULASAN")
	private String keterangan;
	@Column(name="JUMLAH_BAYARAN_PROSES")
	private double jumlahBayaran;
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
	public HakmilikUrusan getHakmilikUrusan() {
		return hakmilikUrusan;
	}
	public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
		this.hakmilikUrusan = hakmilikUrusan;
	}
	public String getNoRujukanSeksyen() {
		return noRujukanSeksyen;
	}
	public void setNoRujukanSeksyen(String noRujukanSeksyen) {
		this.noRujukanSeksyen = noRujukanSeksyen;
	}
	public String getFlagPelan() {
		return flagPelan;
	}
	public void setFlagPelan(String flagPelan) {
		this.flagPelan = flagPelan;
	}
	public String getFlagCharting() {
		return flagCharting;
	}
	public void setFlagCharting(String flagCharting) {
		this.flagCharting = flagCharting;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public double getJumlahBayaran() {
		return jumlahBayaran;
	}
	public void setJumlahBayaran(double jumlahBayaran) {
		this.jumlahBayaran = jumlahBayaran;
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
