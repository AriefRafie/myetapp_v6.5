package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpserahbalik entity provides the base persistence definition of
 * the Tblhtpserahbalik entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpserahbalik implements java.io.Serializable {

	// Fields

	private String idSerahbalik;
	private Long idPenswastaan;
	private Long idHtphakmilik;
	private Long idHakmilik;
	private Date tarikhTandatangan;
	private Date tarikhHantar;
	private Date tarikhDaftar;
	private Date tarikhBatalRekod;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpserahbalik() {
	}

	/** minimal constructor */
	public AbstractTblhtpserahbalik(String idSerahbalik, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilik) {
		this.idSerahbalik = idSerahbalik;
		this.idPenswastaan = idPenswastaan;
		this.idHtphakmilik = idHtphakmilik;
		this.idHakmilik = idHakmilik;
	}

	/** full constructor */
	public AbstractTblhtpserahbalik(String idSerahbalik, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilik, Date tarikhTandatangan,
			Date tarikhHantar, Date tarikhDaftar, Date tarikhBatalRekod,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idSerahbalik = idSerahbalik;
		this.idPenswastaan = idPenswastaan;
		this.idHtphakmilik = idHtphakmilik;
		this.idHakmilik = idHakmilik;
		this.tarikhTandatangan = tarikhTandatangan;
		this.tarikhHantar = tarikhHantar;
		this.tarikhDaftar = tarikhDaftar;
		this.tarikhBatalRekod = tarikhBatalRekod;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public String getIdSerahbalik() {
		return this.idSerahbalik;
	}

	public void setIdSerahbalik(String idSerahbalik) {
		this.idSerahbalik = idSerahbalik;
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

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
	}

	public Date getTarikhTandatangan() {
		return this.tarikhTandatangan;
	}

	public void setTarikhTandatangan(Date tarikhTandatangan) {
		this.tarikhTandatangan = tarikhTandatangan;
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

	public Date getTarikhBatalRekod() {
		return this.tarikhBatalRekod;
	}

	public void setTarikhBatalRekod(Date tarikhBatalRekod) {
		this.tarikhBatalRekod = tarikhBatalRekod;
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