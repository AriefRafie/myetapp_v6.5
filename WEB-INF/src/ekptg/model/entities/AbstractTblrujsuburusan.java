package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujsuburusan entity provides the base persistence definition of
 * the Tblrujsuburusan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujsuburusan implements java.io.Serializable {

	// Fields

	private Long idSuburusan;
        private Long idUrusan;
	private String kodSuburusan;
	private String namaSuburusan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idSeksyen;

	// Constructors

	/** default constructor */
	public AbstractTblrujsuburusan() {
	}

	/** minimal constructor */
	public AbstractTblrujsuburusan(Long idUrusan, Long idSeksyen) {
		this.idUrusan = idUrusan;
		this.idSeksyen = idSeksyen;
	}

	/** full constructor */
	public AbstractTblrujsuburusan(Long idUrusan,
			String kodSuburusan, String namaSuburusan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idSeksyen) {
		this.idUrusan = idUrusan;
		this.kodSuburusan = kodSuburusan;
		this.namaSuburusan = namaSuburusan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idSeksyen = idSeksyen;
	}

	// Property accessors

	public Long getIdSuburusan() {
		return this.idSuburusan;
	}

	public void setIdSuburusan(Long idSuburusan) {
		this.idSuburusan = idSuburusan;
	}

        public Long getIdUrusan() {
                return this.idUrusan;
        }

        public void setIdUrusan(Long idUrusan) {
                this.idUrusan = idUrusan;
        }

	public String getKodSuburusan() {
		return this.kodSuburusan;
	}

	public void setKodSuburusan(String kodSuburusan) {
		this.kodSuburusan = kodSuburusan;
	}

	public String getNamaSuburusan() {
		return this.namaSuburusan;
	}

	public void setNamaSuburusan(String namaSuburusan) {
		this.namaSuburusan = namaSuburusan;
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

	public Long getIdSeksyen() {
		return this.idSeksyen;
	}

	public void setIdSeksyen(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
	}

}