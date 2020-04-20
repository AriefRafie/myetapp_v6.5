package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujsuburusan entity provides the base persistence definition of
 * the Tblrujsuburusan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujsubsuburusan implements java.io.Serializable {

	// Fields

	private Long idSubsuburusan;
    private Long idSuburusan;
	private String kodSubsuburusan;
	private String namaSubsuburusan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujsubsuburusan() {
	}

	/** minimal constructor */
	public AbstractTblrujsubsuburusan(Long idSuburusan) {
		this.idSuburusan = idSuburusan;
	}

	/** full constructor */
	public AbstractTblrujsubsuburusan(Long idSuburusan,
			String kodSubsuburusan, String namaSubsuburusan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idSuburusan = idSuburusan;
		this.kodSubsuburusan = kodSubsuburusan;
		this.namaSubsuburusan = namaSubsuburusan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdSubsuburusan() {
		return this.idSubsuburusan;
	}

	public void setIdSubsuburusan(Long idSubsuburusan) {
		this.idSubsuburusan = idSubsuburusan;
	}

        public Long getIdSuburusan() {
                return this.idSuburusan;
        }

        public void setIdSuburusan(Long idSuburusan) {
                this.idSuburusan = idSuburusan;
        }

	public String getKodSubsuburusan() {
		return this.kodSubsuburusan;
	}

	public void setKodSubsuburusan(String kodSubsuburusan) {
		this.kodSubsuburusan = kodSubsuburusan;
	}

	public String getNamaSubsuburusan() {
		return this.namaSubsuburusan;
	}

	public void setNamaSubsuburusan(String namaSubsuburusan) {
		this.namaSubsuburusan = namaSubsuburusan;
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