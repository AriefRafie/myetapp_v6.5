package etapp.entity.htp.cukai;

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
import etapp.entity.rujukan.Daerah;
import etapp.entity.rujukan.JenisHakmilik;
import etapp.entity.rujukan.Lot;
import etapp.entity.rujukan.Mukim;
import etapp.entity.rujukan.Negeri;

@Entity
@Table(name = "TBLHTPCUKAITEMP")
public class HakmilikCukaiTemp {
	@Id @Column(name="ID_CUKAITEMP")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	
	@ManyToOne @JoinColumn(name="ID_NEGERI")
	private Negeri negeri;
	@ManyToOne @JoinColumn(name="ID_DAERAH")
	private Daerah daerah;
	@ManyToOne @JoinColumn(name="ID_MUKIM")
	private Mukim mukim;
	@ManyToOne @JoinColumn(name="ID_JENISHAKMILIK")
	private JenisHakmilik jenisHakmilik;	
	@ManyToOne @JoinColumn(name="ID_LOT")
	private Lot lot;	

	@Column(name="NO_LOT")
	private String noLot;
	@Column(name="NO_LOTUPLOAD")
	private String noLotUpload;
	@Column(name="NO_HAKMILIK")
	private String noHakmilik;
	@Column(name="NO_HAKMILIKUPLOAD")
	private String noHakmilikUpload;
	@Column(name="NO_BANGUNAN")
	private String noBangunan;
	@Column(name="NO_TINGKAT")
	private String noTingkat;
	@Column(name="NO_PETAK")
	private String noPetak;
	@Column(name="KEGUNAAN_TANAH")
	private String kegunaanTanah;
	@Column(name="TAHUN")
	private String tahun;
	@Column(name="CUKAI_KENA_BAYAR") 
	private double cukaiKenaBayar = 0.00;
	@Column(name="CUKAI_PERLU_BAYAR") 
	private double cukaiPerluBayar = 0.00;
	@Column(name="CUKAI_TALIAIR") 
	private double cukaiTaliair = 0.00;
	@Column(name="CUKAI_PARIT") 
	private double cukaiParit = 0.00;
	@Column(name="CUKAI_LAIN") 
	private double cukaiLain= 0.00;
	@Column(name="BAYARAN_LAIN") 
	private double bayaranLain= 0.00;
	@Column(name="DENDA") 
	private double denda= 0.00;
	@Column(name="PENGECUALIAN") 
	private double pengecualian= 0.00;
	@Column(name="PENGURANGAN") 
	private double pengurangan= 0.00;
	@Column(name="LEBIHAN") 
	private double lebihan= 0.00;
	@Column(name="CUKAI_DIBAYAR") 
	private double cukaiDibayar= 0.00;
	@Column(name="NO_RESIT")
	private String noResit;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_RESIT")
	private Date tarikhResit;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMA_RESIT")
	private Date tarikhTerimaResit;
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
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public Negeri getNegeri() {
		return negeri;
	}
	public void setNegeri(Negeri negeri) {
		this.negeri = negeri;
	}
	public Daerah getDaerah() {
		return daerah;
	}
	public void setDaerah(Daerah daerah) {
		this.daerah = daerah;
	}
	public Mukim getMukim() {
		return mukim;
	}
	public void setMukim(Mukim mukim) {
		this.mukim = mukim;
	}
	public JenisHakmilik getJenisHakmilik() {
		return jenisHakmilik;
	}
	public void setJenisHakmilik(JenisHakmilik jenisHakmilik) {
		this.jenisHakmilik = jenisHakmilik;
	}
	public Lot getLot() {
		return lot;
	}
	public void setLot(Lot lot) {
		this.lot = lot;
	}
	public String getNoLot() {
		return noLot;
	}
	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}
	public String getNoLotUpload() {
		return noLotUpload;
	}
	public void setNoLotUpload(String noLotUpload) {
		this.noLotUpload = noLotUpload;
	}
	public String getNoHakmilik() {
		return noHakmilik;
	}
	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}
	public String getNoHakmilikUpload() {
		return noHakmilikUpload;
	}
	public void setNoHakmilikUpload(String noHakmilikUpload) {
		this.noHakmilikUpload = noHakmilikUpload;
	}
	public String getNoBangunan() {
		return noBangunan;
	}
	public void setNoBangunan(String noBangunan) {
		this.noBangunan = noBangunan;
	}
	public String getNoTingkat() {
		return noTingkat;
	}
	public void setNoTingkat(String noTingkat) {
		this.noTingkat = noTingkat;
	}
	public String getNoPetak() {
		return noPetak;
	}
	public void setNoPetak(String noPetak) {
		this.noPetak = noPetak;
	}
	public String getKegunaanTanah() {
		return kegunaanTanah;
	}
	public void setKegunaanTanah(String kegunaanTanah) {
		this.kegunaanTanah = kegunaanTanah;
	}
	public String getTahun() {
		return tahun;
	}
	public void setTahun(String tahun) {
		this.tahun = tahun;
	}
	public double getCukaiKenaBayar() {
		return cukaiKenaBayar;
	}
	public void setCukaiKenaBayar(double cukaiKenaBayar) {
		this.cukaiKenaBayar = cukaiKenaBayar;
	}
	public double getCukaiPerluBayar() {
		return cukaiPerluBayar;
	}
	public void setCukaiPerluBayar(double cukaiPerluBayar) {
		this.cukaiPerluBayar = cukaiPerluBayar;
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
	public double getCukaiLain() {
		return cukaiLain;
	}
	public void setCukaiLain(double cukaiLain) {
		this.cukaiLain = cukaiLain;
	}
	public double getBayaranLain() {
		return bayaranLain;
	}
	public void setBayaranLain(double bayaranLain) {
		this.bayaranLain = bayaranLain;
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
	public double getCukaiDibayar() {
		return cukaiDibayar;
	}
	public void setCukaiDibayar(double cukaiDibayar) {
		this.cukaiDibayar = cukaiDibayar;
	}
	public String getNoResit() {
		return noResit;
	}
	public void setNoResit(String noResit) {
		this.noResit = noResit;
	}
	public Date getTarikhResit() {
		return tarikhResit;
	}
	public void setTarikhResit(Date tarikhResit) {
		this.tarikhResit = tarikhResit;
	}
	public Date getTarikhTerimaResit() {
		return tarikhTerimaResit;
	}
	public void setTarikhTerimaResit(Date tarikhTerimaResit) {
		this.tarikhTerimaResit = tarikhTerimaResit;
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
