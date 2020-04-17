package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujjenishakmilik entity provides the base persistence definition
 * of the Tblrujjenishakmilik entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AbstractTblrujjenishakmilik implements
		java.io.Serializable {

	// Fields

	private Long idJenishakmilik;
	private String kodJenisHakmilik;
	private String keterangan;
	private String statusHakmilik;
	private String simpanan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujjenishakmilik() {
	}

	/** minimal constructor */
	public AbstractTblrujjenishakmilik(Long idJenishakmilik) {
		this.idJenishakmilik = idJenishakmilik;
	}

	/** full constructor */
	public AbstractTblrujjenishakmilik(Long idJenishakmilik,
			String kodJenisHakmilik, String keterangan, String statusHakmilik,
			String simpanan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idJenishakmilik = idJenishakmilik;
		this.kodJenisHakmilik = kodJenisHakmilik;
		this.keterangan = keterangan;
		this.statusHakmilik = statusHakmilik;
		this.simpanan = simpanan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJenishakmilik() {
		return this.idJenishakmilik;
	}

	public void setIdJenishakmilik(Long idJenishakmilik) {
		this.idJenishakmilik = idJenishakmilik;
	}

	public String getKodJenisHakmilik() {
		return this.kodJenisHakmilik;
	}

	public void setKodJenisHakmilik(String kodJenisHakmilik) {
		this.kodJenisHakmilik = kodJenisHakmilik;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getStatusHakmilik() {
		return this.statusHakmilik;
	}

	public void setStatusHakmilik(String statusHakmilik) {
		this.statusHakmilik = statusHakmilik;
	}

	public String getSimpanan() {
		return this.simpanan;
	}

	public void setSimpanan(String simpanan) {
		this.simpanan = simpanan;
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