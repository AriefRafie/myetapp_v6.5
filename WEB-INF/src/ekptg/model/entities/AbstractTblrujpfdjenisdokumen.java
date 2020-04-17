package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujpfdjenisdokumen entity provides the base persistence definition
 * of the Tblrujpfdjenisdokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujpfdjenisdokumen implements
		java.io.Serializable {

	// Fields

	private Long idJenisdokumen;
	private String kodJenisDokumen;
	private String kodSeksyen;
	private String namaDokumen;
	private String kodProgram;
	private String kodLaporan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujpfdjenisdokumen() {
	}

	/** minimal constructor */
	public AbstractTblrujpfdjenisdokumen(Long idJenisdokumen) {
		this.idJenisdokumen = idJenisdokumen;
	}

	/** full constructor */
	public AbstractTblrujpfdjenisdokumen(Long idJenisdokumen,
			String kodJenisDokumen, String kodSeksyen, String namaDokumen,
			String kodProgram, String kodLaporan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idJenisdokumen = idJenisdokumen;
		this.kodJenisDokumen = kodJenisDokumen;
		this.kodSeksyen = kodSeksyen;
		this.namaDokumen = namaDokumen;
		this.kodProgram = kodProgram;
		this.kodLaporan = kodLaporan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJenisdokumen() {
		return this.idJenisdokumen;
	}

	public void setIdJenisdokumen(Long idJenisdokumen) {
		this.idJenisdokumen = idJenisdokumen;
	}

	public String getKodJenisDokumen() {
		return this.kodJenisDokumen;
	}

	public void setKodJenisDokumen(String kodJenisDokumen) {
		this.kodJenisDokumen = kodJenisDokumen;
	}

	public String getKodSeksyen() {
		return this.kodSeksyen;
	}

	public void setKodSeksyen(String kodSeksyen) {
		this.kodSeksyen = kodSeksyen;
	}

	public String getNamaDokumen() {
		return this.namaDokumen;
	}

	public void setNamaDokumen(String namaDokumen) {
		this.namaDokumen = namaDokumen;
	}

	public String getKodProgram() {
		return this.kodProgram;
	}

	public void setKodProgram(String kodProgram) {
		this.kodProgram = kodProgram;
	}

	public String getKodLaporan() {
		return this.kodLaporan;
	}

	public void setKodLaporan(String kodLaporan) {
		this.kodLaporan = kodLaporan;
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