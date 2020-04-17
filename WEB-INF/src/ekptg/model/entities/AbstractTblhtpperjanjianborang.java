package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpperjanjianborang entity provides the base persistence
 * definition of the Tblhtpperjanjianborang entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpperjanjianborang implements
		java.io.Serializable {

	// Fields

	private Long idPerjanjianborang;
	private Long idPerjanjian;
	private Date tarikhTerima;
	private Date tarikhTandatanganPtp;
	private Date tarikhHantar;
	private Date tarikhDaftar;
	private Date tarikhTerimaHakmilik;
	private String jenisBorang;
	private String noPeserahanSptb;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpperjanjianborang() {
	}

	/** minimal constructor */
	public AbstractTblhtpperjanjianborang(Long idPerjanjianborang,
			Long idPerjanjian) {
		this.idPerjanjianborang = idPerjanjianborang;
		this.idPerjanjian = idPerjanjian;
	}

	/** full constructor */
	public AbstractTblhtpperjanjianborang(Long idPerjanjianborang,
			Long idPerjanjian, Date tarikhTerima, Date tarikhTandatanganPtp,
			Date tarikhHantar, Date tarikhDaftar, Date tarikhTerimaHakmilik,
			String jenisBorang, String noPeserahanSptb, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idPerjanjianborang = idPerjanjianborang;
		this.idPerjanjian = idPerjanjian;
		this.tarikhTerima = tarikhTerima;
		this.tarikhTandatanganPtp = tarikhTandatanganPtp;
		this.tarikhHantar = tarikhHantar;
		this.tarikhDaftar = tarikhDaftar;
		this.tarikhTerimaHakmilik = tarikhTerimaHakmilik;
		this.jenisBorang = jenisBorang;
		this.noPeserahanSptb = noPeserahanSptb;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPerjanjianborang() {
		return this.idPerjanjianborang;
	}

	public void setIdPerjanjianborang(Long idPerjanjianborang) {
		this.idPerjanjianborang = idPerjanjianborang;
	}

	public Long getIdPerjanjian() {
		return this.idPerjanjian;
	}

	public void setIdPerjanjian(Long idPerjanjian) {
		this.idPerjanjian = idPerjanjian;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhTandatanganPtp() {
		return this.tarikhTandatanganPtp;
	}

	public void setTarikhTandatanganPtp(Date tarikhTandatanganPtp) {
		this.tarikhTandatanganPtp = tarikhTandatanganPtp;
	}

	public Date getTarikhHantar() {
		return this.tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}

	public Date getTarikhDaftar() {
		return this.tarikhDaftar;
	}

	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}

	public Date getTarikhTerimaHakmilik() {
		return this.tarikhTerimaHakmilik;
	}

	public void setTarikhTerimaHakmilik(Date tarikhTerimaHakmilik) {
		this.tarikhTerimaHakmilik = tarikhTerimaHakmilik;
	}

	public String getJenisBorang() {
		return this.jenisBorang;
	}

	public void setJenisBorang(String jenisBorang) {
		this.jenisBorang = jenisBorang;
	}

	public String getNoPeserahanSptb() {
		return this.noPeserahanSptb;
	}

	public void setNoPeserahanSptb(String noPeserahanSptb) {
		this.noPeserahanSptb = noPeserahanSptb;
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

}