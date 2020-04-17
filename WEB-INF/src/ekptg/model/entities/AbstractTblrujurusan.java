package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujurusan entity provides the base persistence definition of the
 * Tblrujurusan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujurusan implements java.io.Serializable {

	// Fields

	private Long idUrusan;
	private String kodUrusan;
	private String namaUrusan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private String flagUrusan;

	// Constructors

	/** default constructor */
	public AbstractTblrujurusan() {
	}

	/** minimal constructor */
	public AbstractTblrujurusan(Long idUrusan) {
		this.idUrusan = idUrusan;
	}

	/** full constructor */
	public AbstractTblrujurusan(Long idUrusan, String kodUrusan,
			String namaUrusan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idUrusan = idUrusan;
		this.kodUrusan = kodUrusan;
		this.namaUrusan = namaUrusan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdUrusan() {
		return this.idUrusan;
	}

	public void setIdUrusan(Long idUrusan) {
		this.idUrusan = idUrusan;
	}

	public String getKodUrusan() {
		return this.kodUrusan;
	}

	public void setKodUrusan(String kodUrusan) {
		this.kodUrusan = kodUrusan;
	}

	public String getNamaUrusan() {
		return this.namaUrusan;
	}

	public void setNamaUrusan(String namaUrusan) {
		this.namaUrusan = namaUrusan;
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