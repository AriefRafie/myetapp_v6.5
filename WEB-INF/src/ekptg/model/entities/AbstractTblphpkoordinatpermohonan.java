package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpkoordinatpermohonan entity provides the base persistence
 * definition of the Tblphpkoordinatpermohonan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpkoordinatpermohonan implements
		java.io.Serializable {

	// Fields

	private Long idKoordinatpermohonan;
	private Tblphpversikoordinat tblphpversikoordinat;
	private Long idStatuskoordinat;
	private String bilanganTitik;
	private Double darjahU;
	private Double darjahT;
	private Double minitU;
	private Double minitT;
	private Double saatU;
	private Double saatT;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphpkoordinatpermohonan() {
	}

	/** minimal constructor */
	public AbstractTblphpkoordinatpermohonan(Long idKoordinatpermohonan) {
		this.idKoordinatpermohonan = idKoordinatpermohonan;
	}

	/** full constructor */
	public AbstractTblphpkoordinatpermohonan(Long idKoordinatpermohonan,
			Tblphpversikoordinat tblphpversikoordinat, Long idStatuskoordinat,
			String bilanganTitik, Double darjahU, Double darjahT,
			Double minitU, Double minitT, Double saatU, Double saatT,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idKoordinatpermohonan = idKoordinatpermohonan;
		this.tblphpversikoordinat = tblphpversikoordinat;
		this.idStatuskoordinat = idStatuskoordinat;
		this.bilanganTitik = bilanganTitik;
		this.darjahU = darjahU;
		this.darjahT = darjahT;
		this.minitU = minitU;
		this.minitT = minitT;
		this.saatU = saatU;
		this.saatT = saatT;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdKoordinatpermohonan() {
		return this.idKoordinatpermohonan;
	}

	public void setIdKoordinatpermohonan(Long idKoordinatpermohonan) {
		this.idKoordinatpermohonan = idKoordinatpermohonan;
	}

	public Tblphpversikoordinat getTblphpversikoordinat() {
		return this.tblphpversikoordinat;
	}

	public void setTblphpversikoordinat(
			Tblphpversikoordinat tblphpversikoordinat) {
		this.tblphpversikoordinat = tblphpversikoordinat;
	}

	public Long getIdStatuskoordinat() {
		return this.idStatuskoordinat;
	}

	public void setIdStatuskoordinat(Long idStatuskoordinat) {
		this.idStatuskoordinat = idStatuskoordinat;
	}

	public String getBilanganTitik() {
		return this.bilanganTitik;
	}

	public void setBilanganTitik(String bilanganTitik) {
		this.bilanganTitik = bilanganTitik;
	}

	public Double getDarjahU() {
		return this.darjahU;
	}

	public void setDarjahU(Double darjahU) {
		this.darjahU = darjahU;
	}

	public Double getDarjahT() {
		return this.darjahT;
	}

	public void setDarjahT(Double darjahT) {
		this.darjahT = darjahT;
	}

	public Double getMinitU() {
		return this.minitU;
	}

	public void setMinitU(Double minitU) {
		this.minitU = minitU;
	}

	public Double getMinitT() {
		return this.minitT;
	}

	public void setMinitT(Double minitT) {
		this.minitT = minitT;
	}

	public Double getSaatU() {
		return this.saatU;
	}

	public void setSaatU(Double saatU) {
		this.saatU = saatU;
	}

	public Double getSaatT() {
		return this.saatT;
	}

	public void setSaatT(Double saatT) {
		this.saatT = saatT;
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