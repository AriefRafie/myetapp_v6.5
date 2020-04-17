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
@Table(name = "TBLHTPHAKMILIKCUKAI")
public class HakmilikCukai {
	@Id @Column(name="ID_HAKMILIKCUKAI")
	private long id;
	@ManyToOne @JoinColumn(name="ID_HAKMILIK")
	private Hakmilik hakmilik;
	
	@Column(name="CUKAI") 
	private double cukai = 0.00;
	@Column(name="CUKAI_TERKINI") 
	private double cukaiTerkini = 0.00;
	@Column(name="STATUS_PELARASAN")
	private String statusPelarasan;	
	@Column(name="KOD_BAYARAN_CUKAI")
	private String kodBayaran;
	@Column(name="CUKAI_TALIAIR") 
	private double cukaiTaliair = 0.00;
	@Column(name="CUKAI_PARIT") 
	private double cukaiParit = 0.00;
	@Column(name="DENDA") 
	private double denda= 0.00;
	@Column(name="PENGECUALIAN") 
	private double pengecualian= 0.00;
	@Column(name="PENGURANGAN") 
	private double pengurangan= 0.00;
	
	@Column(name="LEBIHAN") 
	private double lebihan= 0.00;
	@Column(name="STATUS_PROSES_CUKAI")
	private String statusProses;	
	@Column(name="BAYARAN_LAIN") 
	private double bayaranLain= 0.00;
	@Column(name="STATUS_TERKINI")
	private String statusTerkini;	
	@Column(name="STATUS")
	private String status;	

	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;
	@Column(name="NAMA_MUKIM")
	private String namaMukim;
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
	public double getCukai() {
		return cukai;
	}
	public void setCukai(double cukai) {
		this.cukai = cukai;
	}
	public double getCukaiTerkini() {
		return cukaiTerkini;
	}
	public void setCukaiTerkini(double cukaiTerkini) {
		this.cukaiTerkini = cukaiTerkini;
	}
	public String getStatusPelarasan() {
		return statusPelarasan;
	}
	public void setStatusPelarasan(String statusPelarasan) {
		this.statusPelarasan = statusPelarasan;
	}
	public String getKodBayaran() {
		return kodBayaran;
	}
	public void setKodBayaran(String kodBayaran) {
		this.kodBayaran = kodBayaran;
	}
	public double getCukaiTaliair() {
		return cukaiTaliair;
	}
	public void setCukaiTaliair(double cukaiTaliair) {
		this.cukaiTaliair = cukaiTaliair;
	}
	public double getCukaiParit() {
		return cukaiParit;
	}
	public void setCukaiParit(double cukaiParit) {
		this.cukaiParit = cukaiParit;
	}
	public double getDenda() {
		return denda;
	}
	public void setDenda(double denda) {
		this.denda = denda;
	}
	public double getPengecualian() {
		return pengecualian;
	}
	public void setPengecualian(double pengecualian) {
		this.pengecualian = pengecualian;
	}
	public double getPengurangan() {
		return pengurangan;
	}
	public void setPengurangan(double pengurangan) {
		this.pengurangan = pengurangan;
	}
	public double getLebihan() {
		return lebihan;
	}
	public void setLebihan(double lebihan) {
		this.lebihan = lebihan;
	}
	public String getStatusProses() {
		return statusProses;
	}
	public void setStatusProses(String statusProses) {
		this.statusProses = statusProses;
	}
	public double getBayaranLain() {
		return bayaranLain;
	}
	public void setBayaranLain(double bayaranLain) {
		this.bayaranLain = bayaranLain;
	}
	public String getStatusTerkini() {
		return statusTerkini;
	}
	public void setStatusTerkini(String statusTerkini) {
		this.statusTerkini = statusTerkini;
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
	public String getNamaMukim() {
		return namaMukim;
	}
	public void setNamaMukim(String namaMukim) {
		this.namaMukim = namaMukim;
	}

	

	
}
