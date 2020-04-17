package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpindahmilik entity provides the base persistence definition of
 * the Tblhtpindahmilik entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpindahmilik implements java.io.Serializable {

	// Fields

	private Long idPindahmilik;
	private Long idPenswastaan;
	private Long idHtphakmilik;
	private Long idHakmilikpegangan;
	private Date tarikhTandatangan;
	private Date tarkhHantar;
	private Date tarikhDaftar;
	private String tarikhBatalrekod;
	private Long idMasuk;
	private Date tarikhMasuk;

	// Constructors

	/** default constructor */
	public AbstractTblhtpindahmilik() {
	}

	/** minimal constructor */
	public AbstractTblhtpindahmilik(Long idPindahmilik, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilikpegangan, Long idMasuk) {
		this.idPindahmilik = idPindahmilik;
		this.idPenswastaan = idPenswastaan;
		this.idHtphakmilik = idHtphakmilik;
		this.idHakmilikpegangan = idHakmilikpegangan;
		this.idMasuk = idMasuk;
	}

	/** full constructor */
	public AbstractTblhtpindahmilik(Long idPindahmilik, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilikpegangan,
			Date tarikhTandatangan, Date tarkhHantar, Date tarikhDaftar,
			String tarikhBatalrekod, Long idMasuk, Date tarikhMasuk) {
		this.idPindahmilik = idPindahmilik;
		this.idPenswastaan = idPenswastaan;
		this.idHtphakmilik = idHtphakmilik;
		this.idHakmilikpegangan = idHakmilikpegangan;
		this.tarikhTandatangan = tarikhTandatangan;
		this.tarkhHantar = tarkhHantar;
		this.tarikhDaftar = tarikhDaftar;
		this.tarikhBatalrekod = tarikhBatalrekod;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
	}

	// Property accessors

	public Long getIdPindahmilik() {
		return this.idPindahmilik;
	}

	public void setIdPindahmilik(Long idPindahmilik) {
		this.idPindahmilik = idPindahmilik;
	}

	public Long getIdPenswastaan() {
		return this.idPenswastaan;
	}

	public void setIdPenswastaan(Long idPenswastaan) {
		this.idPenswastaan = idPenswastaan;
	}

	public Long getIdHtphakmilik() {
		return this.idHtphakmilik;
	}

	public void setIdHtphakmilik(Long idHtphakmilik) {
		this.idHtphakmilik = idHtphakmilik;
	}

	public Long getIdHakmilikpegangan() {
		return this.idHakmilikpegangan;
	}

	public void setIdHakmilikpegangan(Long idHakmilikpegangan) {
		this.idHakmilikpegangan = idHakmilikpegangan;
	}

	public Date getTarikhTandatangan() {
		return this.tarikhTandatangan;
	}

	public void setTarikhTandatangan(Date tarikhTandatangan) {
		this.tarikhTandatangan = tarikhTandatangan;
	}

	public Date getTarkhHantar() {
		return this.tarkhHantar;
	}

	public void setTarkhHantar(Date tarkhHantar) {
		this.tarkhHantar = tarkhHantar;
	}

	public Date getTarikhDaftar() {
		return this.tarikhDaftar;
	}

	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}

	public String getTarikhBatalrekod() {
		return this.tarikhBatalrekod;
	}

	public void setTarikhBatalrekod(String tarikhBatalrekod) {
		this.tarikhBatalrekod = tarikhBatalrekod;
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

}