package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkborangp entity provides the base persistence definition of the
 * Tblppkborangp entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkborangp implements java.io.Serializable {

	// Fields

	private Long idBorangp;
	private Tblppkpermohonan tblppkpermohonan;
	private Date tarikhMohon;
	private String noFailTerdahulu;
	private Double jumlahHtaTarikhmohon;
	private Double jumlahHtaTarikhmati;
	private Double jumlahHaTarikhmohon;
	private Double jumlahHaTarikhmati;
	private Double jumlahHartaTarikhmohon;
	private Double jumlahHartaTarikhmati;
	private String catatan;
	private Long idNegerimhn;
	private Long idDaerahmhn;
	private String batalKuasaPentadbir;
	private String lantikPentadbirBaru;
	private String batalPAmanah;
	private String lantikPAmanah;
	private String hartaTinggal;
	private Double nilaiTerdahulu;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkborangpsimatis = new HashSet(0);
	private Set tblppkborangppemohons = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkborangp() {
	}

	/** minimal constructor */
	public AbstractTblppkborangp(Long idBorangp,
			Tblppkpermohonan tblppkpermohonan) {
		this.idBorangp = idBorangp;
		this.tblppkpermohonan = tblppkpermohonan;
	}

	/** full constructor */
	public AbstractTblppkborangp(Long idBorangp,
			Tblppkpermohonan tblppkpermohonan, Date tarikhMohon,
			String noFailTerdahulu, Double jumlahHtaTarikhmohon,
			Double jumlahHtaTarikhmati, Double jumlahHaTarikhmohon,
			Double jumlahHaTarikhmati, Double jumlahHartaTarikhmohon,
			Double jumlahHartaTarikhmati, String catatan, Long idNegerimhn,
			Long idDaerahmhn, String batalKuasaPentadbir,
			String lantikPentadbirBaru, String batalPAmanah,
			String lantikPAmanah, String hartaTinggal, Double nilaiTerdahulu,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppkborangpsimatis,
			Set tblppkborangppemohons) {
		this.idBorangp = idBorangp;
		this.tblppkpermohonan = tblppkpermohonan;
		this.tarikhMohon = tarikhMohon;
		this.noFailTerdahulu = noFailTerdahulu;
		this.jumlahHtaTarikhmohon = jumlahHtaTarikhmohon;
		this.jumlahHtaTarikhmati = jumlahHtaTarikhmati;
		this.jumlahHaTarikhmohon = jumlahHaTarikhmohon;
		this.jumlahHaTarikhmati = jumlahHaTarikhmati;
		this.jumlahHartaTarikhmohon = jumlahHartaTarikhmohon;
		this.jumlahHartaTarikhmati = jumlahHartaTarikhmati;
		this.catatan = catatan;
		this.idNegerimhn = idNegerimhn;
		this.idDaerahmhn = idDaerahmhn;
		this.batalKuasaPentadbir = batalKuasaPentadbir;
		this.lantikPentadbirBaru = lantikPentadbirBaru;
		this.batalPAmanah = batalPAmanah;
		this.lantikPAmanah = lantikPAmanah;
		this.hartaTinggal = hartaTinggal;
		this.nilaiTerdahulu = nilaiTerdahulu;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkborangpsimatis = tblppkborangpsimatis;
		this.tblppkborangppemohons = tblppkborangppemohons;
	}

	// Property accessors

	public Long getIdBorangp() {
		return this.idBorangp;
	}

	public void setIdBorangp(Long idBorangp) {
		this.idBorangp = idBorangp;
	}

	public Tblppkpermohonan getTblppkpermohonan() {
		return this.tblppkpermohonan;
	}

	public void setTblppkpermohonan(Tblppkpermohonan tblppkpermohonan) {
		this.tblppkpermohonan = tblppkpermohonan;
	}

	public Date getTarikhMohon() {
		return this.tarikhMohon;
	}

	public void setTarikhMohon(Date tarikhMohon) {
		this.tarikhMohon = tarikhMohon;
	}

	public String getNoFailTerdahulu() {
		return this.noFailTerdahulu;
	}

	public void setNoFailTerdahulu(String noFailTerdahulu) {
		this.noFailTerdahulu = noFailTerdahulu;
	}

	public Double getJumlahHtaTarikhmohon() {
		return this.jumlahHtaTarikhmohon;
	}

	public void setJumlahHtaTarikhmohon(Double jumlahHtaTarikhmohon) {
		this.jumlahHtaTarikhmohon = jumlahHtaTarikhmohon;
	}

	public Double getJumlahHtaTarikhmati() {
		return this.jumlahHtaTarikhmati;
	}

	public void setJumlahHtaTarikhmati(Double jumlahHtaTarikhmati) {
		this.jumlahHtaTarikhmati = jumlahHtaTarikhmati;
	}

	public Double getJumlahHaTarikhmohon() {
		return this.jumlahHaTarikhmohon;
	}

	public void setJumlahHaTarikhmohon(Double jumlahHaTarikhmohon) {
		this.jumlahHaTarikhmohon = jumlahHaTarikhmohon;
	}

	public Double getJumlahHaTarikhmati() {
		return this.jumlahHaTarikhmati;
	}

	public void setJumlahHaTarikhmati(Double jumlahHaTarikhmati) {
		this.jumlahHaTarikhmati = jumlahHaTarikhmati;
	}

	public Double getJumlahHartaTarikhmohon() {
		return this.jumlahHartaTarikhmohon;
	}

	public void setJumlahHartaTarikhmohon(Double jumlahHartaTarikhmohon) {
		this.jumlahHartaTarikhmohon = jumlahHartaTarikhmohon;
	}

	public Double getJumlahHartaTarikhmati() {
		return this.jumlahHartaTarikhmati;
	}

	public void setJumlahHartaTarikhmati(Double jumlahHartaTarikhmati) {
		this.jumlahHartaTarikhmati = jumlahHartaTarikhmati;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Long getIdNegerimhn() {
		return this.idNegerimhn;
	}

	public void setIdNegerimhn(Long idNegerimhn) {
		this.idNegerimhn = idNegerimhn;
	}

	public Long getIdDaerahmhn() {
		return this.idDaerahmhn;
	}

	public void setIdDaerahmhn(Long idDaerahmhn) {
		this.idDaerahmhn = idDaerahmhn;
	}

	public String getBatalKuasaPentadbir() {
		return this.batalKuasaPentadbir;
	}

	public void setBatalKuasaPentadbir(String batalKuasaPentadbir) {
		this.batalKuasaPentadbir = batalKuasaPentadbir;
	}

	public String getLantikPentadbirBaru() {
		return this.lantikPentadbirBaru;
	}

	public void setLantikPentadbirBaru(String lantikPentadbirBaru) {
		this.lantikPentadbirBaru = lantikPentadbirBaru;
	}

	public String getBatalPAmanah() {
		return this.batalPAmanah;
	}

	public void setBatalPAmanah(String batalPAmanah) {
		this.batalPAmanah = batalPAmanah;
	}

	public String getLantikPAmanah() {
		return this.lantikPAmanah;
	}

	public void setLantikPAmanah(String lantikPAmanah) {
		this.lantikPAmanah = lantikPAmanah;
	}

	public String getHartaTinggal() {
		return this.hartaTinggal;
	}

	public void setHartaTinggal(String hartaTinggal) {
		this.hartaTinggal = hartaTinggal;
	}

	public Double getNilaiTerdahulu() {
		return this.nilaiTerdahulu;
	}

	public void setNilaiTerdahulu(Double nilaiTerdahulu) {
		this.nilaiTerdahulu = nilaiTerdahulu;
	}

	public Long getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Long getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public Set getTblppkborangpsimatis() {
		return this.tblppkborangpsimatis;
	}

	public void setTblppkborangpsimatis(Set tblppkborangpsimatis) {
		this.tblppkborangpsimatis = tblppkborangpsimatis;
	}

	public Set getTblppkborangppemohons() {
		return this.tblppkborangppemohons;
	}

	public void setTblppkborangppemohons(Set tblppkborangppemohons) {
		this.tblppkborangppemohons = tblppkborangppemohons;
	}

}